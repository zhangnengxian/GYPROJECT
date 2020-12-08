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
var applyCompleteDrawingTable;
/**查询条件  默认待勘察*/
var searchData={};
var accessoryData={};
var detailSearchData = {};
var searchData={};
var handleApplyCompleteDrawing = function() {
	"use strict";
    if ($('#applyCompleteDrawingTable').length !== 0) {
    	applyCompleteDrawingTable= $('#applyCompleteDrawingTable').on( 'init.dt',function(){
    		//加载页面
    		$("#apply_complete_drawing_panel_box").cgetContent("applyCompleteDrawing/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#applyCompleteDrawingTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#applyCompleteDrawingTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//申请监听方法
   			applyMonitor();
   			setTimeout(function(){
   			    $("#applyCompleteDrawingTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
             dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },         
                { text: '申请', className: 'btn-sm btn-query  business-tool-btn applyBtn' }
//                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
//                { text: '通知', className: 'btn-sm btn-query hidden business-tool-btn informBtn' }
//                { text: '打印', className: 'btn-sm btn-query hidden business-tool-btn printBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'applyCompleteDrawing/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/settlement/json/apply_complete_drawing.json',
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
				{"data":"projStatusDes"},
				{"data":"auditStatusDes"},
				{"data":"signBack", className:"none never", "createdCell": function (td, cellData, rowData, row, col) {
					console.info("cellData--"+cellData);
					if(cellData == $("#notThrough").val()){
						$(td).parent().children().css("background", "rgb(255, 219, 219)");
						//$(td).attr("id",row);
					}
				}
				},
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
			},{
				"targets":4,
				 "orderable":false
			}]
        });
    }
    
};

//弹屏监听方法
var searchMonitor = function(){
	
	//查询按钮弹出屏查询
	$(".searchBtn").on("click",function(){
			var url = "#applyCompleteDrawing/searchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#stateSelect,#applyCompleteDrawingTable_filter input").on("change",function(){
		var projNo = $('#applyCompleteDrawingTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$("#applyCompleteDrawingTable").cgetData(true);  //列表重新加载
		
	});
	//基础条件查询添加回车事件
	$('#applyCompleteDrawingTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	searchData = $('#applyCompleteDrawingSearchPop').serializeJson();
	var projNo = $('#applyCompleteDrawingTable_filter input').val();
	searchData.projNo = projNo;
	console.info("+++++++++++++++++++++++++++++++++=");
	//$(".iframe-big-box").addClass("hidden");
	$("#apply_complete_drawing_panel_box").removeClass("hidden");
    $("#applyCompleteDrawingTable").cgetData(true,function(){
    	var len = $('#applyCompleteDrawingTable').DataTable().ajax.json().data ? $('#applyCompleteDrawingTable').DataTable().ajax.json().data.length : $('#applyCompleteDrawingTable').DataTable().ajax.json().length;
    	if(len<1){
    		 //清空右侧记录
    		 $("#applyform")[0].reset();
    		 //$(':input','#scaleTableForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
    		 //checkbox清除选中，相应的明细也清除掉
    		// $("input[name='projLtype']").attr("checked",false);
    		 //$("input[name='projLtype']").change();
    		 //$(".backReasonshow").addClass("hidden");
    	 }
    	
    });  
    
}

var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	
	if(json.auditStatus=="0" || json.auditStatus=="2" ){
		$(".applyBtn").removeClass("hidden");
	}else{
		$(".applyBtn").addClass("hidden");
	}
	//handleIsAuditHistory(json);
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('applyform','applyCompleteDrawing/viewDrawingApply','',queryDetailBack);
}
/**
 * 申请监听事件
 */
var applyMonitor = function(){
	$(".applyBtn").on("click",function(){
		$("#apply_complete_drawing_panel_box").removeClass("hidden");
		if($("#applyCompleteDrawingTable").find("tr.selected").length>0){
			//切换可编辑状态
			$("#applyform").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要申请的工程！');
		}
	});
}

var queryDetailBack = function(){
	$("#applyform").toggleEditState(false);
	var busId=$('#acdId').val()!=""?$('#acdId').val():"@";//当busId为空时会查询全部

	Base.subimt("queryManageRecord/queryTodoer","POST",{businessOrderId:busId},function (data) {
		$('#todoer').val(data);
	});

	searchData.businessOrderId=busId;
	ManageRecord.initTable("auditHistoryTable",searchData);//审核列表初始化
}
var applyCompleteDrawing = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handleApplyCompleteDrawing();
        	});
        }
    };
}();



