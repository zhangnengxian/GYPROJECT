var businessCommunicationTable;//通知列表
var searchData={}; //查询条件
var projId = getProjectInfo().projId;
var accessoryTable;
var accessoryData = {};
searchData.projId = projId;
/**
 * 加载通知列表
 */
var handleBusinessCommunication = function() {
	'use strict';
    if ($('#businessCommunicationTable').length !== 0) {
    	businessCommunicationTable= $('#businessCommunicationTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#business_communication_panel_box').cgetContent('businessCommunication/viewPage');
   			//隐藏遮罩
   			$('#businessCommunicationTable').hideMask();
   			//$('#businessCommunicationTable_filter input').attr('placeholder','工程编号');
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//增加监听方法
   			addMonitor();
   			//修改监听方法
   			updateMonitor();
   			//回复监听方法
   			replyMonitor();
   			//删除监听方法
   			delMonitor();
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Brtip',
             buttons: [
                 { text: '增加', className: 'btn-sm btn-query business-tool-btn addBtn' },
                 { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn'},
                 { text: '回复', className: 'btn-sm btn-query business-tool-btn replyBtn' },
                 { text: '删除', className: 'btn-sm btn-warn business-tool-btn delBtn' }
             ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'businessCommunication/queryBusinessCommunication',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
             },
             //ajax: 'projectjs/design/json/survey_result_register.json',
             responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
             select: true,  //支持多选
             columns: [
				 {'data':'bcId',className:'none never'}, 
	  			 {'data':'bcInitiatorName'}, 
				 {'data':'bcRecipientName'},
				 {'data':'noticeDate'},
				 {"data":"bcStatusDes"}
			 ],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 },{
				 "targets":2,
				 //长字符串截取方法
				 render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				 })
			 },{
					"targets":4,
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

/**
 * 增加按钮监听方法
 */
var addMonitor = function(){
	$('.addBtn').off('click').on('click',function(){
		$("#bcType").change();
		$("#bcTypeDetail1").val('');//清空细类
		//$("#businessCommunicationForm").formReset();
		$('#businessCommunicationTable tr.selected').removeClass("selected");
		$.ajax({
            type: 'POST',
            url: 'businessCommunication/findByProjId',
            data: 'projId='+projId,
            dataType: 'json',
            success: function (data) {
            	$("#businessCommunicationForm")[0].reset();
            	$("#businessCommunicationForm").deserialize(data.project);
            	$("#noticeDate").val(data.dlDate);
            	$('#bcInitiatorName').val(data.dlRecorder);
            	$('#bcInitiatorId').val(data.staffId);
            	$(".formClear").val("");
            	$("#businessCommunicationForm").toggleEditState(true);
            	 getSignStatusByPostId(getProjectInfo().post,"businessCommunicationForm");
            	$("#businessCommunicationForm").styleFit();
            	$(".editbtn").removeClass("hidden");
            	$(".entBtn").removeClass("hidden");
    			$(".saveBtn").addClass("hidden");
    			accessoryData.busRecordId ="aaa";  //作为查询条件
    			$("#dataPopTableSecond").cgetData(false,function(){
    			});
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("addMonitor() -> 增加通知查询失败");
                console.warn(jqXHR);
                console.warn(textStatus);
                console.warn(errorThrown);
            }
        });
	});
};
/**
 * 修改按钮监听方法
 */
var updateMonitor = function(){
	$('.updateBtn').off('click').on('click',function(){
		if($("#businessCommunicationTable").find("tr.selected").length>0){
			$("#bcType").change()
			//切换可编辑状态
			$("#businessCommunicationForm").toggleEditState(true);
			 getSignStatusByPostId(getProjectInfo().post,"businessCommunicationForm");
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
			$(".entBtn").removeClass("hidden");
			$(".saveBtn").addClass("hidden");
		}else{
			alertInfo('请选择要修改的通知记录！');
		}
	});
}
/**
 * 删除按钮监听方法
 */
var delMonitor = function(){
	$('.delBtn').off('click').on('click',function(){
		if($("#businessCommunicationTable").find("tr.selected").length>0){
			var content = "确定要删除该记录吗？";
			var myoptions = {
                    title: "提示信息",
                    content: content,
                    accept: delBc,
                    icon: "fa-check-circle",
                	newpop: 'new'
                }
                $("body").cgetPopup(myoptions);
	}else{
		alertInfo('请选择要删除的通知记录！');
		}
	});
}
var delBc = function(){
	var bcId= trSData.businessCommunicationTable.json.bcId;
	$.ajax({
        type: 'POST',
        url: 'businessCommunication/delBusinessCommunication',
        data: 'bcId='+bcId,
        success: function (data) {
        	console.info(data);
        	var content = "数据删除成功！";
        	if(data == "false"){
        		content = "数据删除失败！";
        	}else if(data == "true"){
        		$("#businessCommunicationTable").cgetData(true);
        	}else if(data == "already"){
        		content = "此信息已被修改，请刷新页面！";
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
            console.warn("delMonitor() -> 删除通知失败");
            console.warn(jqXHR);
            console.warn(textStatus);
            console.warn(errorThrown);
        }
    });

}
var replyMonitor = function(){
	$('.replyBtn').off('click').on('click',function(){
		if($("#businessCommunicationTable").find("tr.selected").length>0){
			$.ajax({
	            type: 'POST',
	            url: 'businessCommunication/findReplyDate',
	            dataType: 'text',
	            success: function (data) {
	            	console.info(data);
	            	$("#replyDate").val(data);
	            	//维护按钮显示出来
	    			$(".editbtn").removeClass("hidden");
	    			$(".pushBtn").addClass("hidden");
	    			$(".entBtn").addClass("hidden");
	    			$(".saveBtn").removeClass("hidden");
	    			//$("#replyContent").attr('readonly',false);
	    			$(".replyContent").attr('readonly',false);
	    			$("#signBtn_4").removeClass('disabled');
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                console.warn("replyMonitor() -> 回复通知查询失败");
	                console.warn(jqXHR);
	                console.warn(textStatus);
	                console.warn(errorThrown);
	            }
	        });
		}else{
			alertInfo('请选择要回复的通知记录！');
		}
	});
}
/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$('.searchBtn').on('click',function(){
		var url = '#surveyResultRegister/projectSearchPopPage';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#businessCommunicationTable_filter input').on('change',function(){
		var projNo = $('#businessCommunicationTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#businessCommunicationTable').cgetData(true,bcTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#businessCommunicationTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};
/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	searchData = $('#searchForm_surveyRegister').serializeJson();
	var projNo = $('#businessCommunicationTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#businessCommunicationTable').cgetData(true,bcTableCallBack);  
}

var bcTableCallBack = function(){
	var len = $('#businessCommunicationTable').DataTable().ajax.json().data ? $('#businessCommunicationTable').DataTable().ajax.json().data.length : $('#businessCommunicationTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#businessCommunicationForm').formRest();
		 $(".editbtn").addClass("hidden");
		 $("#businessCommunicationForm").toggleEditState(false);
	 }else{
		 $(".editbtn").addClass("hidden");
	 }
}
/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	if(json.bcStatus=='1'){
		if(json.bcSendType=='1'){//发送
			$(".updateBtn").removeClass("hidden");
			$(".delBtn").removeClass("hidden");
			$(".replyBtn").addClass("hidden");
		}else if(json.bcSendType=='2'){//接收
			$(".updateBtn").addClass("hidden");
			$(".replyBtn").removeClass("hidden");
			$(".delBtn").addClass("hidden");
		}else{//既不是发送人，也不是接收人
			$(".updateBtn").removeClass("hidden");
			$(".delBtn").removeClass("hidden");
			$(".replyBtn").addClass("hidden");
		}
	}else if(json.bcStatus=='2') {//已回复
		$(".updateBtn").addClass("hidden");
		$(".delBtn").addClass("hidden");
		$(".replyBtn").addClass("hidden");
	}else{//待推送
		$(".updateBtn").removeClass("hidden");
		$(".delBtn").removeClass("hidden");
		$(".replyBtn").addClass("hidden");
	}
	getSignStatusByPostId(getProjectInfo().post,"businessCommunicationForm");
	t.cgetDetail('businessCommunicationForm','businessCommunication/viewByBcId','',surveyBack); 
}
function surveyBack(data){
	//序列化
	var f = $("#businessCommunicationForm");
	//探伤信息
	var ndeRecord = data.ndeRecord;
	if(ndeRecord){
		var selects = f.find('select[disabled],  [type="radio"][disabled]');
		selects.removeAttr("disabled");
		var total = jQuery.extend({}, trSData.json, ndeRecord);
		console.info(201,total);
		f.deserialize(total);
		f.initFixedNumber();
		//有disabled属性的下拉菜单元素对象重新添加禁用属性
		selects.attr("disabled","disabled");
	}
	//
	var bc = data.bCommunication;
	if(bc){
		var selects = f.find('select[disabled],  [type="radio"][disabled]');
	    selects.removeAttr("disabled");
		var total = jQuery.extend({}, trSData.json, bc);
        console.info(202,total);
		f.deserialize(total);
		f.initFixedNumber();
		//有disabled属性的下拉菜单元素对象重新添加禁用属性
		selects.attr("disabled","disabled");
		
		$("#bcTypeDetail1").val(bc.bcTypeDetail);
	}else{
		$("#bcTypeDetail1").val('');
	}
	//查询细类
	$("#bcType").change();
	if(bc){
		$("#version").val(bc.version);
	}
	//判断细类
	if($("#bcTypeDetail").val()=='2011'){
			$(".ndeRecord").removeClass("hidden");
			$(".ndeFormat").addClass("hidden");
		}else{
			$(".ndeRecord").addClass("hidden");
		}
	console.info("bcTypeDetail == "+$("#bcTypeDetail").val());
	//判断细类
	if($("#bcTypeDetail").val()=='2010'){
			$(".ndeFormat").removeClass("hidden");
			$(".ndeRecord").addClass("hidden");
		}else{
			$(".ndeFormat").addClass("hidden");
		}
	$(".editbtn").addClass("hidden");
	$('#bcRecipientDeptName').val(businessCommunicationTable.row(".selected").data().bcRecipientDeptName);
	$('#busRecordId').val($('#bcId').val());
	accessoryData.busRecordId = $("#bcId").val()==''||$("#bcId").val()==undefined?"-1":$("#bcId").val();
	if($.fn.DataTable.isDataTable('#dataPopTableSecond')){
		//初始化过
		$("#dataPopTableSecond").cgetData(false,function(){
		
		});
	}else{
		seconddatainit1();
	}
	
}
/**
 * 初始化资料
 */
var seconddatainit1= function() {
	"use strict";
    if ($('#dataPopTableSecond').length !== 0) {
        accessoryTable = $('#dataPopTableSecond').on( 'draw.dt',function(){
	   	//默认选中第一行
	    $('#dataPopTableSecond').hideMask();
    	var popClose2 = function(){};
	    	//删除附件列表记录
	     	$(".del_btn").on("click",function(){
	    		$("body").cgetPopup({
    				title: '提示',
    				content: '您确定删除该文件信息吗？',
    			    accept: {
    					func: deleteDone,	//函数名
    					singleArgs: $(this)	//参数，用于向func传入多个参数，不设置该属性或设置为[]，则传入不带参数的函数
    				}
    	    	});
	   		});
        }).DataTable({
        	language: language_CN,
           	lengthMenu: [8],
           	dom: 'Brtip',
           	buttons: [],
          	ajax: {
               	url: 'accessoryCollect/queryAccessoryList',
               	type:'post',
               	data: function(d){
                  	$.each(accessoryData,function(i,k){
                  		d[i] = k;
                  	});
               	},
               	dataSrc: 'data'
           	},
           	responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
           	columns: [
                {"data":"caiId",className:"none never"},
                {"data":"alOperateCsrId",className:"none never"},
	  			{"data":"alName",responsivePriority:2},
	  			{"data":"alTypeId",responsivePriority:5},
	  			{"data":"alOperateTime",responsivePriority:3},
	  			{"data":"alOperateCsr" ,responsivePriority:4},	  			
	  			{"data":"alId",responsivePriority: 1}
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},{
				targets: 0,
				render: function (data, type, row, meta) {
					$('[type="checkbox"][name="accbox"][data-box="' + data + '"]').attr("checked","checked");
					return data;
				}
			},{
				targets: 6,
				render: function (data, type, row, meta) {
					if(type === "display"){
						var  tdcon='<a target="_blank" class="Search_Button btn btn-xs btn-primary" data-row="' + data + '" href="/accessoryCollect/openFile?id='+data+'"><i class="fa fa-eye"></i> 查看</a>';
							if($("#loginId").val() === row.alOperateCsrId){
								var  tdcon1='<a class="m-l-5 del_btn btn btn-warn btn-xs" data-id="'+data+'" data-path="'+row.alPath+'"><i class="fa fa-times"></i> 删除</a>';
							}else{
								var  tdcon1 = '';
							}
						return tdcon+tdcon1;
					}else{
						return data;
					}
				}
			}]
       });
   }
}
function deleteDone(t){
    var data={};
	data.path=t.attr("data-path");	    
	data.alId=t.attr("data-id");
	$.ajax({
		url:'accessoryCollect/delAccessoryList',
		type:'post',
		data:data,
		success:function(data){
			if(data=="true"){
				$("[name='accbox']:checkbox").attr("checked",false);
					accessoryTable.ajax.reload();	  				
				}	
		    else{
		    	$("body").cgetPopup({
			    	title: "提示信息",
			    	content: "删除失败! <br>",
			    	accept: failClose,
			    	chide: true,
			    	icon: "fa-exclamation-circle"
			    });  		    	
		    }
		}
	});
}
function failClose(){
	$("#filePreviews tbody").html("");
}
var saveImportBack = function(data){
	console.info(data);
	if(data.result.result=='noBusiness'){
		$("body").cgetPopup({
	    	title: "提示信息",
	    	content: "请先保存通知信息! <br>",
	    	accept: failClose,
	    	chide: true,
	    	icon: "fa-exclamation-circle"
	    });  	
	}else{
		$("#filePreviews tbody").html("");
	}
}
/**
 * 初始化业务沟通列表
 */
var businessCommunication = function () {
	'use strict';
    return {
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handleBusinessCommunication();
        	})
        }
    };
}();
