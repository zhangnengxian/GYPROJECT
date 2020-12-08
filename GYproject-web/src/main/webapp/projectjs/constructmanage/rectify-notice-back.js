var searchData={},
	recordData={},
	rectifyNoticeTable,
	savestus=0;
//整改列表
var handleRectifyNotice = function() {
	"use strict";
	searchData.projId = $("#projId").val();
    if ($('#rectifyNoticeTable').length !== 0) {
    	rectifyNoticeTable=$('#rectifyNoticeTable').on( 'init.dt',function(){
    		//搜索
    		//$('#rectifyNoticeTable_filter input').attr('placeholder','整改编号'); 
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//隐藏遮罩
   			$('#rectifyNoticeTable').hideMask();
   			//弹屏查询
   	    	searchMonitor();
   			//修改监听
   	    	modifyMonitor();
   	    	//加载打印屏
  		    showReport1();
   	    	queryCheckRole();
   	    	//回复监听
   	    	replyRectifyNotice();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
           // dom: 'Brtip',
            dom: 'Brt',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn'},
                { text: '整改', className: 'btn-sm btn-query checkRole updateBtn business-tool-btn'},
                { text: '回复', className: 'btn-sm btn-query checkRole replyBtn'}
            ],
          //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'rectifyNoticeBack/queryRectifyNotices',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/constructmanage/json/rectify_notice.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
      			{"data":"rnId",className:"none never"},
      			{"data":"rnNo"},
      			{"data":"limitTime"},
				{"data":"rnDate"},
				{"data":"rnStatusDes"}
				],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},{
				"targets":4,
				 "orderable":false
			}],
        });
        
    }
}

//修改监听
var modifyMonitor = function(){
	$(".updateBtn").off("click").on("click",function(){
		var len=$("#rectifyNoticeTable").find("tr.selected").length;
		if(len>0){
            //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"rectifiyNoticeForm");
			// $('#rectifiyNoticeForm').toggleEditState(true);
			$(".editbtn").removeClass("hidden");
			$('#rectificationInfo').tab("show");
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的整改条目!',
				ahide:true,
				atext:'确定'
			});
		}
	});
};

/**
 * 查询弹屏监听方法
 */
var searchMonitor=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#rectifyNoticeBack/rectifyNoticeSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	$('#rectifyNoticeTable_filter input').on('change',function(){
		var cmNo = $('#rectifyNoticeTable_filter input').val();
		searchData = {};
		searchData.rnId = rnId;
		searchData.projId = $("#projId").val();
		$('#rectifyNoticeTable').cgetData(true);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#rectifyNoticeTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}
/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_rectifyNotice").serializeJson();
	searchData.projId = $("#projId").val();
	//列表重新加载
    $("#rectifyNoticeTable").cgetData(true,rectifyNoticeCallBack);  
   
}
/**
 * 回复方法
 */
var replyRectifyNotice = function(){
	$('.replyBtn').off().on('click',function(){
		var len=$("#rectifyNoticeTable").find('tr.selected').length;
		if(len>0){
           $("body").cgetPopup({
               	title: "提示信息",
               	content: "是否回复选中的整改通知？",
               	accept: sureReply,
               	newpop: 'new',
               	icon: "fa-check-circle"
           });
		}else{
			alertInfo('请选择要回复的整改通知！');
		}
	});
}
var sureReply=function(){
	var rnId=trSData.rectifyNoticeTable.json.rnId;
	$.ajax({
		type:'post',
		url:'rectifyNoticeBack/replyRectifyNotice',
		contentType: "application/json;charset=UTF-8",
        data: rnId,
        success:function(data){
        	var content = "回复成功！";
        	if(data=="false"){
        		content = "回复失败！";
        	}else{
        		$('#rectifyNoticeTable').cgetData(true);
        	}
        	var myoptions = {
                	title: "提示信息",
                	content: content,
                	accept: popClose,
                	chide: true,
                	icon: "fa-check-circle",
                	newpop: 'new'
                	
            }
            $("body").cgetPopup(myoptions);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("数据保存失败！");
        }
	})
}
/**
 * 重新加载整改通知列表
 */
var rectifyNoticeCallBack = function(){
	var len = $('#rectifyNoticeTable').DataTable().ajax.json().data ? $('#rectifyNoticeTable').DataTable().ajax.json().data.length : $('#rectifyNoticeTable').DataTable().ajax.json().length;
	if(len<1){
		//清空信息页签
		$(".addClear").val('');
	}
	//清空签字
	$(".clear-sign").click();
	showReport1();
}

//保存整改信息
$('.saveCustomer').off().on("click",function(){
	$("#rectifiyNoticeForm").cformSave('rectifyNoticeTable',saveBack,false,false);
   	
});
var saveBack = function(data){
 	var content = "数据保存成功！";
 	if(data === "false"){
 		content = "数据保存失败！";
 	}else if(data === "already"){
   		content = "此信息已被修改，请刷新页面！";
   	}else{
   		var rnId = data;
   		$('#rnId').val(rnId);
   		projNo = getProjectInfo().projNo,
   		projId = getProjectInfo().projId;
   		$('#rectifiyNoticeForm').toggleEditState(false);
   		$(".editbtn").addClass("hidden");
   		showReport();
   		sureClose();
 	}
 	var myoptions = {
         	title: "提示信息",
         	content: content,
         	accept: popClose,
         	chide: true,
         	icon: "fa-check-circle"
     }
     $("body").cgetPopup(myoptions);
};
//点击放弃
$('.giveUpSave').off().on('click',function(){
	$("#rectifiyNoticeForm").toggleEditState(false);
	//$('ul.nav>li').removeClass("active");
	$('#rectificationList').click();
});

/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	if(json.rnStatus=='1'){//待推送
		$(".updateBtn").addClass("hidden");
		$(".replyBtn").addClass("hidden");
	}else if(json.rnStatus=='2'){//待回复
		$(".updateBtn").removeClass("hidden");
		$(".replyBtn").removeClass("hidden");
	}else if(json.rnStatus=='3'){//已回复
		$(".updateBtn").addClass("hidden");
		$(".replyBtn").addClass("hidden");
	}
	//将查询详述显示到相应的输入框内
	t.cgetDetail('rectifiyNoticeForm','rectifyNoticeBack/viewRectifyNotice','',queryBack);
	//传false表示不可编辑
	$("#rectifiyNoticeForm").toggleEditState(false);
	$(".editbtn").addClass("hidden");
}

var queryBack=function(){
	showReport1();
}
/**
 * 根据操作修改
 * @param op 操作类型
 * @param data 整改信息对象
 */
var operate = function(op,data){
	if(op=="add"){
		$("#question").html("");
		$("#noticeDate").val("");
		$("#rectificationPeriod").val("");
	}/*else if(op=="update"){
	}else{
		$(".editbtn").addClass("hidden");
	}*/
}
//整改列表页签
$('#rectificationList').on('shown.bs.tab',function(){
	$(".editbtn").addClass("hidden");
	$('#rectifyNoticeTable').cgetData();
});
$('#rectificationInfo').on('shown.bs.tab',function(){

});

//页面加载
var rectifyNotice =  function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleRectifyNotice();
        	});
        	$("#rectificationList,#rectificationInfo").on("shown.bs.tab",function(){
        		if($(this).is($("#rectificationList"))){
    				if(!$.fn.DataTable.isDataTable('#rectifyNoticeTable')){
    					handleRectifyNotice();
        			}else{
        				showReport1();
        			}
        		}else if($(this).is($("#rectificationInfo"))){
        			if(trSData.rectifyNoticeTable.json && $('#cwId').val()!=""){
        				showReport();
    				}else{
    					showReport();
    					$('.addClear').val('');
    					//清空签字
    					$('.clear-sign').click();
    				}
					/*
					setTimeout(function(){
    				    $("#projectImagesList").getImagesList({
    				    	"projId": getProjectInfo().projId,
    				    	"stepId": getStepId(),
    				    	"projNo": getProjectInfo().projNo,
    				    	"busRecordId": $("#pcId").val() || '-1'
    				    });
    				},300);*/
        		}
        	});
        }
    };
}();





var sureClose=function(){
	var cwId=$("#rnId").val();
	$.ajax({
		type:'post',
		url:'rectifyNoticeBack/saveSignNotice',
		contentType: "application/json;charset=UTF-8",
        data: cwId,
        success:function(data){
        	console.info(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("数据保存失败！");
        }
	})
}