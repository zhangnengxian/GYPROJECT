var searchData={},shutdownAprovalTable={},selectData={};
//整改列表
var handleShutdownAproval = function() {
	"use strict";
	searchData.projId = $('#projId').val();
	//searchData.pushStatus=0;
    if ($('#shutdownAprovalTable').length !== 0) {
    	shutdownAprovalTable = $('#shutdownAprovalTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//隐藏遮罩
   			$('#shutdownAprovalTable').hideMask();
   			//弹屏查询
   	    	searchMonitor();
   			//增加监听
   			addMonitor();
   			//修改监听
   	    	modifyMonitor();
   	    	//加载打印屏
  		    showReport();
  		    //推送
  		    pushMpnitor();
  		    
  		    queryCheckRole();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '增加', className: 'btn-sm btn-query checkRole addBtn' },
                { text: '修改', className: 'btn-sm btn-query checkRole updateBtn business-tool-btn' },
                { text: '推送', className: 'btn-sm btn-query btn-info checkRole hidden  pushBtn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'shutdownAproval/queryShutdownRecordList',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/constructmanage/json/shutdown_aproval.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
      			{"data":"sdrId",className:"none never"},
      			{"data":"sdrNo"},
      			{"data":"sdrDate"},
				{"data":"sdrProcess"},		
				{"data":"sdrStatus"}		
				],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},{
				"targets": 3,
				"render":function(data,type,row,meta){
					return data;
				}
			},{
				"targets": 4,
				"render":function(data,type,row,meta){
					if(data=='1'){
						return "待下达停工令";
					}else if(data=='2'){
						return "待复工报审";
					}else if(data == '3'){
						return "复工报审中";
					}else if(data == '4'){
						return "待下达复工令";
					}else if(data=='5'){
						return "已复工";
					}
				}
			},{
				"targets":4,
				 "orderable":false
			}],
        });
        
    }
}

//增加监听
var addMonitor = function(){
	$(".addBtn").off("click").on("click",function(){
		var len=$("#shutdownAprovalTable").find("tr.selected").length;
		if(len>0){
			var json = trSData.shutdownAprovalTable.json;
			if(json.sdrStatus>2){
				alertInfo("该停工令已申报了，请选择未申报的停工令进行申报！");
				return;
			}
			$('#shutdownAprovalInfo').tab('show');
            //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"shutDownAprovalForm");
			// $('#shutDownAprovalForm').toggleEditState(true);
			$(".editbtn").removeClass("hidden");
		
			$("#signBtn_1,#signBtn_2,#signBtn_3").resetSign();
		
			$(".rework .addClear").val("");
			operate("add",null);
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要报审的停工条目!',
				ahide:true,
				atext:'确定'
			});
		}
	});
}
//修改监听
var modifyMonitor = function(){
	$(".updateBtn").off("click").on("click",function(){
		var len=$("#shutdownAprovalTable").find("tr.selected").length;
		if(len>0){
			var json = trSData.shutdownAprovalTable.json;
			if(json.sdrStatus=='5'){
				alertInfo("该停工已经复工,不可修改！");
				return;
			}
            //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"shutDownAprovalForm");
			// $('#shutDownAprovalForm').toggleEditState(true);
			$(".editbtn").removeClass("hidden");
		    
			$('#shutdownAprovalInfo').tab("show");
			
			
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的停工条目!',
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
		var url = "#shutdownAproval/viewSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
}
/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_shutdownAproval").serializeJson();
	searchData.projId = getProjectInfo().projId;
	//列表重新加载
    $("#shutdownAprovalTable").cgetData(true,rectifyNoticeCallBack);  
   
}
/**
 * 重新加载整改通知列表
 */
var rectifyNoticeCallBack = function(){
	var len = $('#shutdownAprovalTable').DataTable().ajax.json().data ? $('#shutdownAprovalTable').DataTable().ajax.json().data.length : $('#shutdownAprovalTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 //$('#shutdownRecordTable')[0].reset();
		 $('#shutDownAprovalForm .addClear').val('');
		 $(".editbtn").addClass("hidden");
	 }else{
		 $(".editbtn").addClass("hidden");
	 }
	//清空签字
	$('.clear-sign').click();
	 showReport();
}

//点击放弃
$('.giveUpSave').off().on('click',function(){
	$("#shutDownAprovalForm").toggleEditState(false);
	//$('ul.nav>li').removeClass("active");
	$('#shutdownAprovalList').click();
	operate();
});


/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	//将查询详述显示到相应的输入框内
	t.cgetDetail('shutDownAprovalForm','shutdownAproval/viewShutdownAproval','',queryBack);
	//传false表示不可编辑
	/*$("#shutDownAprovalForm").toggleEditState(false);
	$(".editbtn").addClass("hidden");*/
}

var queryBack=function(data){
	selectData = data;
	var shutDownRecord = data.shutDownRecord;
	if(shutDownRecord.sdrStatus==3){//复工报审中
		$(".pushBtn").removeClass("hidden");
	}else{
		$(".pushBtn").addClass("hidden");
	}
	
	//将数据填充到页面
	//f=$("#shutDownAprovalForm");
	//var selects = f.find('select[disabled],  [type="radio"][disabled]');
    //selects.removeAttr("disabled");
    $("#sdrNo").val(shutDownRecord.sdrNo);
    $("#sdrId").val(shutDownRecord.sdrId);
    $("#sdrReason").val(shutDownRecord.sdrReason);
    $("#sdrProcess").val(shutDownRecord.sdrProcess);
    $("#cuManagerDept").val(shutDownRecord.cuManagerDept);
    $("#custName").val(shutDownRecord.custName);
    $("#projId").val(shutDownRecord.projId);
	/*var total = jQuery.extend({}, trSData.json, shutDownRecord);
    f.deserialize(total);
    f.initFixedNumber();*/
   //有disabled属性的下拉菜单元素对象重新添加禁用属性
   // selects.attr("disabled","disabled");
    //去掉日期数据带微妙
    mysqlDateTimeStr();
	showReport(data);
}
/**
 * 根据操作修改
 * @param op 操作类型
 * @param data 整改信息对象
 */
var operate = function(op,data){
	if(op=="add"){
		/*$("#shutdownReason").text("");
		$("#step").val("");
		$("#startDate").val("");
		$("#requirement").text("");*/
	}/*else if(op=="update"){
	}else {
		$(".editbtn").addClass("hidden");
		
	}*/
}

//整改列表页签
$('#shutdownAprovalList').on('shown.bs.tab',function(){
	$(".editbtn").addClass("hidden");
	$('#shutdownAprovalTable').cgetData();
});

//推送监听
var pushMpnitor = function(){
	$(".pushBtn").off("click").on("click",function(){
		var len=$("#shutdownAprovalTable").find("tr.selected").length;
		if(len>0){
			var json = trSData.shutdownAprovalTable.json;
			console.info(json);
			var response = $.ajax({
				url: 'shutdownAproval/pushShutdownApprovl',
				type: "POST",
				timeout : 5000,
				contentType: "application/json;charset=UTF-8",
				data: JSON.stringify(json),
				success: function (data) {
					if(data === "false"){
						alertInfo("数据保存失败！");
					}else{
						alertInfo("数据保存成功！");
					}
					$('#shutdownAprovalTable').cgetData();
					//$("#drawingAuditForm").toggleEditState(false).styleFit();
				//	$(".drawingReviewSaveBtn").addClass("hidden");
				//	$(".drawingReviewUpdataBtn").removeClass("hidden");
				}
			});
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要推送的记录!',
				ahide:true,
				atext:'确定'
			});
		}
	});
}

//页面加载
var shutdownAproval =  function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleShutdownAproval();
        	});
        	
        	$('[href="#shutdown_aproval_info"]').on("show.bs.tab", function(){
        	    $("#shutdownAprovalTable").styleFit();
        	    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6").handleSignature();
        	});
        	$("#shutdownAprovalList,#shutdownAprovalInfo").on("shown.bs.tab",function(){
        		if($(this).is($("#shutdownAprovalList"))){
    				if(!$.fn.DataTable.isDataTable('#shutdownAprovalTable')){
    					handleShutdownAproval();
        			}else{
        				//加载打印屏
        				//showReport();
        			}
        		}else{
        			//加载打印屏
        			// showReport();
        		}
        	});
        }
    };
}();