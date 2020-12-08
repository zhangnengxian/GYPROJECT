/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var stationProceduresTable;
var scaleTable;
/**查询条件  默认待勘察*/
var searchData={};
var accessoryData={};
var detailSearchData = {};
var handlestationAccept = function() {
	"use strict";
    if ($('#stationProceduresTable').length !== 0) {
    	stationProceduresTable= $('#stationProceduresTable').on( 'init.dt',function(){
    		//加载页面
    		$("#project_accept_panel_box").cgetContent("stationProcedures/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#stationProceduresTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#stationProceduresTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//修改监听方法
   			updateMonitor();
   			setTimeout(function(){
   			    $("#stationProceduresTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },         
                // { text: '增加', className: 'btn-sm btn-query  business-tool-btn addBtn' },
                { text: '建审', className: 'btn-sm btn-query business-tool-btn updateBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'stationAccept/queryStationProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            // ajax: 'projectjs/station/json/station_accept.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"projId",className:"none never"}, 
	  			{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"projStatusDes"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: showLength, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":3,
				 "orderable":false
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
    
};

//弹屏监听方法
var searchMonitor = function(){
	
	//查询按钮弹出屏查询
	$(".searchBtn").on("click",function(){
			var url = "#stationAccept/searchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#stateSelect,#stationProceduresTable_filter input").on("change",function(){
		var stateSelect = $("#stateSelect").find("option:selected");
		var projNo = $("#stationProceduresTable_filter input").val();
		searchData = {};
		searchData.projStatusId = stateSelect.val();
		searchData.projNo = projNo;
		$("#stationProceduresTable").cgetData(true);  //列表重新加载
		
	});
	//基础条件查询添加回车事件
	$('#stationProceduresTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	$("#project_accept_panel_box").removeClass("hidden");
	searchData = $("#searchForm_designDispatch").serializeJson();
	var projNo = $("#stationProceduresTable_filter input").val();
	searchData.projNo = projNo;
	var stateSelect = $("#stateSelect").val();
	searchData.projStatusId = stateSelect;
	var  projStatus= $('.projStatus :selected').val();
    $("#stationProceduresTable").cgetData(true,function(){
    	var len = $('#stationProceduresTable').DataTable().ajax.json().data ? $('#stationProceduresTable').DataTable().ajax.json().data.length : $('#stationProceduresTable').DataTable().ajax.json().length;
    	if(len<1){
    		 //清空右侧记录
    		 $("#proceduresForm")[0].reset();
    		 $(':input','#scaleTableForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
    		 //checkbox清除选中，相应的明细也清除掉
    		 $("input[name='projLtype']").attr("checked",false);
    		 $("input[name='projLtype']").change();
    		 $(".backReasonshow").addClass("hidden");
    	 }
    	if(projStatus=="3"){
    		$(".informBtn").removeClass("hidden");
    		//$(".printBtn").addClass("hidden");
    		$(".updateBtn").addClass("hidden");
    	}else if(projStatus=="2"){
    		//$(".printBtn").removeClass("hidden");
    		$(".informBtn").addClass("hidden");
    		$(".updateBtn").addClass("hidden");
    	}else{
    		//$(".printBtn").addClass("hidden");
    		$(".informBtn").addClass("hidden");
    		$(".updateBtn").removeClass("hidden");
    	}
    });  //列表重新加载
    
}

//修改监听方法
var updateMonitor = function(){
	$(".updateBtn").on("click",function(){
		//$(".iframe-big-box").addClass("hidden");
		$("#project_accept_panel_box").removeClass("hidden");
		if($("#stationProceduresTable").find("tr.selected").length>0){
			detailFlag = true;
			$("#projNo").addClass("field-not-editable");
			$("#projNo").removeClass("field-editable");
			//切换可编辑状态
			$("#proceduresForm,#scaleTableForm").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
};


var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	$(".informSaveBtn").addClass("hidden");
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	detailFlag = false;
	t.cgetDetail('proceduresForm','stationAccept/viewStationProject','',scaleDetailRefresh);
}

var scaleDetailRefresh = function(){
    var acceptData= $("#acceptData").val() || "",
        proceduresData = $("#proceduresData").val() || "",
    	acceptData = acceptData.split(","),
    	proceduresData = proceduresData.split(",");
    $("input[name='acceptDataDes']").attr("checked",false);
    for(var i=0;i<acceptData.length;i++){
        $("input[name='acceptDataDes'][value="+acceptData[i]+"]").attr("checked","checked");
    }
    //建审
    $("input[name='proceduresDataDes']").attr("checked",false);
    for(var i=0;i<proceduresData.length;i++){
        $("input[name='proceduresDataDes'][value="+proceduresData[i]+"]").attr("checked","checked");
    }
}

var scaleTableCallBack = function(){
	if(detailFlag){
   	 	$('#scaleTableForm').toggleEditState(true);
	}else{
		$('#scaleTableForm').toggleEditState(false);
	}
}

var stationProcedures = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handlestationAccept();
        	});
        }
    };
}();


