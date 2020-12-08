var recordListTable, projectCheckListTable,checkListData={},recordData={};
var projId=$('#projId').val();
checkListData.projId=projId;
var operate = 0;
var delRowData;

/**
 * 页面初始化
 */
var electionData = function(){
	"use strict";
    return {
        init: function () {
        	handleProjectCheckList();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab, #signTab").off("shown.bs.tab").on("shown.bs.tab",function(){
        		
        		if($(this).is($("#listTab,#signTab"))){
    				if($(this).is($('#listTab'))){
    					if(!$.fn.DataTable.isDataTable('#projectCheckListTable')){
    						//初始化列表
    						handleProjectCheckList();
            			}else{
            				$('#projectCheckListTable').cgetData(true);
            			}
    				}else{
    					setTimeout(function(){
        				    $("#projectImagesList").getImagesList({
        				    	"projId": getProjectInfo().projId,
        				    	//"stepId": "130121",
        				    	//"projNo": getProjectInfo().projNo,
        				    	"busRecordId": $("#pcId").val() || '-1'
        				    });
        				},300);
    				}
        		}
        	});
        }
    };
}();
/**
 * 初始化信息，列表区列表
 */
var handleProjectCheckList = function() {
	"use strict";
	projectCheckListTable= $('#projectCheckListTable').on( 'init.dt',function(){
	$(this).bindDTSelected(trSelectedBack,true);
	$('#projectCheckListTable_filter input').attr('placeholder','施工工序');
	//updateMonitor();
	//viewMonitor();
	//隐藏遮罩
	$("#projectCheckListTable").hideMask();
	//查询监听
	searchMonitor();
	showReport("inspection/electrodeRecord");
    }).DataTable({
    	language: language_CN,
        lengthMenu: [ 15 ],
        dom: 'Bfrtip',
        buttons: [
           // { text: '查询', className: 'btn-sm btn-query searchBtn' },
            //{ text: '修改', className: 'btn-sm btn-query business-tool-btn  updateBtn' },
            //{ text: '完成', className: 'btn-sm btn-query business-tool-btn checkRole hidden completebtn' },
            //{ text: '详述', className: 'btn-sm btn-query business-tool-btn checkRole viewBtn' },
        ],
        serverSide: true, 
        ajax: {
            url: 'inspectionData/queryList',
            type:'post',
            data: function(d){
               	$.each(checkListData,function(i,k){
               		d[i] = k;
               	});
               	
            },
            datasrc: 'data'
        },
        responsive: {
        	details: {
        		renderer: function ( api, rowIdx, columns ){
        			return renderChild(api, rowIdx, columns);
        		}
        	}
        },
        select: true,
        columns: [
            {"data":"pcId",className:"none never"},       
			{"data":"process"},
			{"data":"inspectionDate"},
			{"data":"inspectionResult"},
			{"data":"flagDes"},
		],
		order: [[ 0, "desc" ]],
		columnDefs: [{
			"targets":0,
			"visible":false
		},{
			"targets":4,
			 "orderable":false
		}] 
    });
};
/**
 * 搜索监听
 */

var searchMonitor = function(){
	//查询按钮弹出屏查询
/*	$(".searchBtn").on("click",function(){
			var url = "#inspectionData/search";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});*/
	/** 查询弹出屏，点击确定后 */
	/*var searchDone = function(){
		searchData = $("#projectCheckListTable").serializeJson();
		var menuId = $("#projectCheckListTable_filter input").val();
		//searchData.menuId = menuId;
	    $("#projectCheckListTable").cgetData(true,'');  //列表重新加载
	}*/
	//基础条件查询添加监听
	$('#projectCheckListTable_filter input').on('change',function(){
		var process = $('#projectCheckListTable_filter input').val();
		var pcDesId=$('#pcDesId').val();
		var projId=$('#projId').val();
		checkListData = {};
		checkListData={'process':process,'pcDesId':pcDesId,'projId':projId};
		$('#projectCheckListTable').cgetData(true,checkListCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#projectCheckListTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}
//列表区行点击
var trSelectedBack = function(v, i, index, t, json){
	console.log("1-------------------");
	$("#pcIdNew").val(json.pcId);
	$("#meltConnectType").val(json.meltConnectType);
	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
	if($("#menuDes")){
		$("#menuDes").val(menuDesc);
	}
	if($("#menuId")){
		$("#menuId").val(getStepId());
	}
	var menuId = getStepId();
	t.cgetDetail("signForm",$("#actionName").val()+'/viewCheckList?menuId='+menuId,'',queryBack);
}
//报验单详述回调
var queryBack=function(data){
	console.info("stepId--"+getStepId());
	
	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
	if($("#menuDes")){
		$("#menuDes").val(menuDesc);
	}
	if($("#menuId")){
		$("#menuId").val(getStepId());
	}
	$("#flag1").val(data.flag);
	$("#type").val(data.type);
	
	var post=$("#loginPost").val();
	console.info("post-"+post);
	var builder=$("#builderPost").val();
	console.info("builder-"+builder);
	var sujgj=$("#sujgjPost").val();
	console.info("sujgj-"+sujgj);
	
	
	if(data.flag==1){
		$(".updateBtn,.completebtn").addClass("hidden");
	}else{
		$(".updateBtn,.completebtn").removeClass("hidden");
		if(post==builder||post==sujgj){
			$(".completebtn").attr("disabled",false);
			completeMonitor();
		}else{
			$(".completebtn").attr("disabled",true);//不可点击
			$(".completebtn").off();
		}
	}
	
	
	
	
	
	
	
	//showDrawFile();
	//trSelOtherInfo(data);
	showReport(data['pcDesId']);
	
}
//列表重新加载
var checkListCallBack =function(){
	var len = $('#projectCheckListTable').DataTable().ajax.json().data ? $('#projectCheckListTable').DataTable().ajax.json().data.length : $('#projectCheckListTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.addClear').val('');
		$('#pcIdNew').val('');
		$(".updateBtn,.completebtn").addClass("hidden");
	 }else{
		 var data = trSData.projectCheckListTable.json;
	 }
	//清空签字
	//$(".clear-sign").click();
	//showReport();
}