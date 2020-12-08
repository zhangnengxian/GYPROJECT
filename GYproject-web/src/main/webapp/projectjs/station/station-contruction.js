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
var stationContructionTable;
var scaleTable;
/**查询条件  默认待勘察*/
var searchData={};
var accessoryData={};
var detailSearchData = {};
var handleStationContruction = function() {
	"use strict";
    if ($('#stationContructionTable').length !== 0) {
    	stationContructionTable= $('#stationContructionTable').on( 'init.dt',function(){
    		//加载页面
    		$("#project_accept_panel_box").cgetContent("stationContruction/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#stationContructionTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#stationContructionTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//修改监听方法
   			updateMonitor();
   			//增加监听方法
   			insertMonitor();
   			setTimeout(function(){
   			    $("#stationContructionTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },         
                { text: '施工', className: 'btn-sm btn-query business-tool-btn updateBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'stationContruction/queryStationProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});

                },
                dataSrc: 'data'
            },
            // ajax: 'projectjs/station/json/station_construction.json',
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
			var url = "#stationContruction/searchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#stateSelect,#stationContructionTable_filter input").on("change",function(){
		var projNo = $("#stationContructionTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;
		$("#stationContructionTable").cgetData(true);  //列表重新加载
		
	});
	//基础条件查询添加回车事件
	$('#stationContructionTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	$("#project_accept_panel_box").removeClass("hidden");
	searchData = $("#searchForm_stationContruction").serializeJson();
	var projNo = $("#stationContructionTable_filter input").val();
	searchData.projNo = projNo;
    $("#stationContructionTable").cgetData(true);  //列表重新加载
    
}

//修改监听方法
var updateMonitor = function(){
	$(".updateBtn").on("click",function(){
		//$(".iframe-big-box").addClass("hidden");
		$("#project_accept_panel_box").removeClass("hidden");
		if($("#stationContructionTable").find("tr.selected").length>0){
			detailFlag = true;
			$("#projNo").addClass("field-not-editable");
			$("#projNo").removeClass("field-editable");
			//切换可编辑状态
			$("#acceptApplyForm,#scaleTableForm").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
};
//增加监听方法
var insertMonitor = function(){
	$(".addBtn").off("click").on("click",function(){
		$("#project_accept_panel_box").removeClass("hidden");
		detailFlag = true;
		
		$('#stationContructionTable').clearSelected();
		$("#acceptApplyForm")[0].reset();
		$("#custId").val("");
		$("#projId,#projLtypeId").val("");
		//切换可编辑状态
		$("#acceptApplyForm").toggleEditState(true);
		
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
	
	//资料查看文件
	$(".searchBtn1").on("click",function(){
	     var data = {};
	     data.fpath = $(this).attr("data-row");
		 $.ajax({
			url:'accessoryCollect/openFile',
			type:'post',
			data:data,
			success:function(data){
			    if(data == "nofile"){
			    	$("body").cgetPopup({
	    		    	title: "提示信息",
	    		    	content: "文件不存在! <br>",
	    		    	accept: popClose2,
	    		    	chide: true,
	    		    	icon: "fa-exclamation-circle"
	    		    });  		    	
			    }
		    }
		});
	});
}


var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	$(".informSaveBtn").addClass("hidden");
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	detailFlag = false;
	t.cgetDetail('acceptApplyForm','stationAccept/viewStationProject','',scaleDetailRefresh);
}

var scaleDetailRefresh = function(){

        var finisiProgress=$("#finishProgress").val();
        $(".totalProgress").html("工程进度"+finisiProgress);

        var bar_style = parseInt(finisiProgress) < 15 ? 'danger' : parseInt(finisiProgress) < 32 ? 'warning' : parseInt(finisiProgress) < 50 ? 'inverse' : parseInt(finisiProgress) < 75 ? 'default' : parseInt(finisiProgress) < 100 ? 'info' : 'success',
            animate = parseInt(finisiProgress) < 100 ? " active" : "";
        $(".totalProgress").removeClass("progress-bar-"+$("#barStyle").val());
        $(".totalProgress").addClass("progress-bar-"+bar_style);
        $("#barStyle").val(bar_style);
        $(".totalProgress").attr("style","width:"+finisiProgress);


    var acceptData= $("#acceptData").val() || "",
        acceptData = acceptData.split(",");
    $("input[name='acceptDataDes']").attr("checked",false);
    for(var i=0;i<acceptData.length;i++){
        $("input[name='acceptDataDes'][value="+acceptData[i]+"]").attr("checked","checked");
    }
    //建审
    var proceduresData = $("#proceduresData").val() || "",
        proceduresData = proceduresData.split(",");
    $("input[name='proceduresDataDes']").attr("checked",false);
    for(var i=0;i<proceduresData.length;i++){
        $("input[name='proceduresDataDes'][value="+proceduresData[i]+"]").attr("checked","checked");
    }
    //施工
    var contructionData = $("#contructionData").val() || "",
        contructionData = contructionData.split(",");
    $("input[name='contructionDataDes']").attr("checked",false);
    for(var i=0;i<contructionData.length;i++){
        $("input[name='contructionDataDes'][value="+contructionData[i]+"]").attr("checked","checked");
    }
}

var scaleTableCallBack = function(){
	if(detailFlag){
   	 	$('#scaleTableForm').toggleEditState(true);
	}else{
		$('#scaleTableForm').toggleEditState(false);
	}
}

var stationConstruction = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handleStationContruction();
        	});
        }
    };
}();


