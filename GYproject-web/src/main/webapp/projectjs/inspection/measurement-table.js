var mytable, measurementTable,checkListData={},pipeLineData={};
var projId=$('#projId').val();
checkListData.projId=projId;
var post = $("#post").val();
$('input[type="text"]').attr("required",true);
$("textarea").attr("required",true);  //页面上所有的input、textarea框必填

/**
 * 初始化信息，列表区列表
 */
var handlePreservativeInpect = function() {
	"use strict";
	measurementTable= $('#measurementTable').on( 'init.dt',function(){
	$(this).bindDTSelected(trSelectedBack,true);
	$('#measurementTable_filter input').attr('placeholder','计量表铭牌号');
	//隐藏遮罩
	$("#measurementTable").hideMask();
	addMonitor();
	updateMonitor();
	//完成监听
	//completeMonitor();
	//查询监听
	searchMonitor();
	//删除监听
	deleteMonitor();
	//重置监听
	//resetMonitor();
	queryCheckRole();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [ 15 ],
        dom: 'Bfrtip',
       //  dom: 'Bfrt',
        buttons: [
            { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
            { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' },
            { text: '删除', className: 'btn-sm btn-warn business-tool-btn checkRole  deleteBtn' },
          /*  { text: '重置', className: 'btn-sm btn-warn business-tool-btn checkRole  resetBtn' },
            { text: '完成', className: 'btn-sm btn-query business-tool-btn checkRole  completebtn' },*/
        ],
        serverSide: true, 
        ajax: {
            url: 'measurementTable/queryMeasurement',
            type:'post',
            data: function(d){
               	$.each(checkListData,function(i,k){
               		d[i] = k;
               	});
               	
            },
            datasrc: 'data'
        },
       // ajax:'projectjs/inspection/json/preservative_inpect.json',
        responsive: {
        	details: {
        		renderer: function ( api, rowIdx, columns ){
        			return renderChild(api, rowIdx, columns);
        		}
        	}
        },
        select: true,
        columns: [
            {"data":"msId",className:"none never"},       
			{"data":"tableNumber"},
			{"data":"tableType"},
			{"data":"tableTypeNumber"},
			{"data":"producers"},
			{"data":"producersNumber"},
			{"data":"firstCheckDate"}
		],
		order: [[ 1, "asc" ]],
		columnDefs: [{
			"targets":0,
			"visible":false
		}] 
    });
};

/**
 * 列表区增加监听
 */
var addMonitor = function(){
	$(".addBtn").on("click",function(){
		$("#signTab").tab("show");
		$('.update-show').removeClass('hidden');
        //根据职务过滤可编辑项
        getSignStatusByPostId(getProjectInfo().post,"measurementForm");
		 $("#measurementForm").toggleEditState(true);
		 //清空要增加的数据项
		$('.addClear').val('');
		$('.clear-sign').click();
	});
}
/**
 * 列表区修改监听
 */
function updateMonitor(){
	$(".updateBtn").on("click",function(){
		var len=$("#measurementTable").find("tr.selected").length;
		if(len>0){
            //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"measurementForm");
			$('#measurementForm').toggleEditState(true);
			$(".update-show").removeClass("hidden");
			$("#signTab").tab("show");
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的报验单信息!',
				ahide:true,
				atext:'确定'
			});
		}
		
	});
}
/**
 * 报验列表删除按钮监听
 */
var deleteMonitor=function(){
	  $(".deleteBtn").on("click",function(){
		  var len=$("#measurementTable").find("tr.selected").length;
			if(len>0){
			  $("body").cgetPopup({
					title: '提示',
					content: '您确定要删除该条记录吗？',
				    accept: delDone  //回调函数
			  });
			}else{
				alertInfo("请先选择一条记录！");
			}
	  })
};
//删除函数
var delDone = function(){
	  var msId = $("#msId").val();
	  $("#measurementTable").parent().parent().loadMask("正在删除...", 1, 0.5);
	     $.ajax({
	          type: 'POST',
	          url: 'measurementTable/byMsIdDeleteMeasurement',
	          data:{msId:msId},
              dataType:"json",
	          success: function (data) {
	           $("#measurementTable").parent().parent().hideMask();
	           var content = "数据删除成功！";
	            if(!data){
	             content = "数据删除失败！";
	            }else if(data){
	            	deleteCallBack();
	           }
	                },
	                error: function (jqXHR, textStatus, errorThrown) {
	                	//取消遮罩
	                	$("#measurementTable").parent().parent().hideMask();	
	                    console.warn("保存失败！");
	                }
	            });
}
var deleteCallBack=function(){   //执行函数以后的回调方法，加载列表区和报表
	$('#measurementTable').cgetData(true,confirmFunction);  //列表重新加载
    showReport();	
		
}
var confirmFunction = function(){
	
}

/**
 * 搜索监听
 */
var searchMonitor = function(){
	//基础条件查询添加监听
	$('#measurementTable_filter input').on('change',function(){
		var process = $('#measurementTable_filter input').val();
		var pcDesId=$('#pcDesId').val();
		var projId=$('#projId').val();
		checkListData = {};
		checkListData={'process':process,'pcDesId':pcDesId,'projId':projId};
		$('#measurementTable').cgetData(true,deleteCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#measurementTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

//列表区行点击
var trSelectedBack = function(v, i, index, t, json){
	t.cgetDetail("measurementForm",'measurementTable/viewMeasurement','',queryBack);
	$("#measurementForm").toggleEditState(false);
	$(".editbtn").addClass("hidden");
}
//回调函数
var queryBack=function(data){
	if(post.indexOf(',999,')>=0){   //如果是管理员，显示删除按钮，否则隐藏删除按钮
		$(".deleteBtn").removeClass("hidden");
	}else{
		$(".deleteBtn").addClass("hidden");	
	}
	showReport();
}

/**
 * 页面初始化
 */
var preservativeInpect = function(){
	"use strict";
    return {
        //main function
        init: function () {
        	handlePreservativeInpect();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab, #auditTab, #signTab").off("shown.bs.tab").on("shown.bs.tab",function(){
        		
        		if($(this).is($("#listTab,#signTab"))){
    				if($(this).is($('#listTab'))){
    					if(!$.fn.DataTable.isDataTable('#measurementTable')){
    						//初始化列表
    						handlePreservativeInpect();
            			}else{
            				$('#measurementTable').cgetData(true,deleteCallBack);
            				$(".update-show").addClass("hidden");
            				$('#measurementForm').toggleEditState(false);
            			}
    				}else{
    					if(trSData.measurementTable.json && $('#pcId').val()!=""){
    						$('.preservativeType').toggleEditState(false);
    						showReport();
        				}else{
        					// $('.addClear').val('');
        					//清空签字
        					$('.clear-sign').click();
        					showReport();
        				}
    					console.info($('#pcId').val()+"-----------pcId");
    					setTimeout(function(){
        				    $("#projectImagesList").getImagesList({
        				    	"projId": getProjectInfo().projId,
        				    	"step": getStepId(),
        				    	"projNo": getProjectInfo().projNo,
        				    	"busRecordId": $("#pcId").val() || '-1'
        				    });
        				},300);
    				}
        		}else if($(this).is($("#auditTab"))){
        			
        		}
        	});
        }
    };
}();



//签字区放弃功能
$(".giveupbtn").on("click",function(){
	if($("#pcId").val()==""){
		//返回列表区
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
		$('#measurementTable').cgetData(true,deleteCallBack);
	}else{
		//返回列表区
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
	}
});


