/**
 * 记录区查询条件
 */
var searchData=function(){
	  if($('#pcIdNew').val()!==''){
			recordData.pcId=$('#pcIdNew').val();
		}else{
			recordData.pcId=-1;
		}
		recordData.projId = $('#projId').val();
		recordData.flag = $("#flag1").val();//报验单完成标记
}
/**
 * 初始化信息,记录区列表信息
 */
var handleRecord = function() {
	"use strict";
	searchData();
    if ($('#recordListTable').length !== 0) {
    	recordListTable= $('#recordListTable').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBackRecord,true);
   			//隐藏遮罩
   			$('#recordListTable').hideMask();
   			pageMonitor();  //页码监听事件
   			handleCheckBox();
   			checkboxMonitor();
   			showRecordPhoto();
   			showReport();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [16],
            destroy:true,
            dom: 'Brtip',
            buttons: [
            ],
            serverSide: false, 
            ajax: {
                url: $("#actionName").val()+'/queryRecords',
                type:'post',
                data: function(d){
                   	$.each(recordData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                datasrc: ''
            },
          //  ajax: 'projectjs/inspection/json/groove_record.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	  			{"data":"grId"}, 
	  			{"data":"grDate"}, 
				{"data":"pipePosition"},
				{"data":"pipeBedding"},
				{"data":"pipeLength",className:"text-right"},
				{"data":"pipeDepth",className:"text-right"},
				{"data":"grSlope",className:"text-right"},
				{"data":"grBackfill"},
				{"data":"hinderSituation"}
			],
			columnDefs: [{
				"targets":0,
				"render": function ( data, type, row, meta ) {
					return addCheckBox(data,row.pcId);
				}
			}],
			ordering:false
        });
    }
};


var pageMonitor = function(){
	//alert("点击");
	$("#recordListTable_paginate a").on("click", function() { 
		  handleCheckBox();
	});
}
//记录区显示照片列表
var showRecordPhoto = function(){
	 var len=$('#recordListTable').find('tr.selected').length;
	 var recordId ='-1';
	 var pcId='';
	 if(len>0){
		 var rows =trSData.recordListTable.json;
		 $('#grId1').val(rows.grId);
		 $('#pcId1').val(rows.pcId);
		 recordId = rows.grId;
		 pcId = rows.pcId;
	 }
	if(recordId){
		$("#recordImagesList").removeClass("hidden");
		setTimeout(function(){
		    $("#recordImagesList").getImagesList({
		    	"projId": getProjectInfo().projId,
		    	"step": getStepId(),
		    	"projNo": recordId,
		    	"busRecordId": pcId
		    });
		},300);
	}else{
		$("#recordImagesList").addClass("hidden");
	}
}

/**
 * 初始化页面
 */
var grooveRecordAndAudit = function () {
	"use strict";
    return {
        //main function
    	init: function () {
        	handleProjectCheckList();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab, #auditTab, #signTab").off("shown.bs.tab").on("shown.bs.tab",function(){
        		
        		if($(this).is($("#listTab,#signTab"))){
    				if($(this).is($('#listTab'))){
    					if(!$.fn.DataTable.isDataTable('#projectCheckListTable')){
    						//初始化列表
    						handleProjectCheckList();
            			}else{
            				$('#projectCheckListTable').cgetData(true,function(){showReport();});
            				$(".update-show").addClass("hidden");
            				$('#signForm').toggleEditState(false);
            				
            			}
    					operate=0;
    				}else{
    					if(trSData.projectCheckListTable.json && $('#pcId').val()!=""){
    						
    						showReport();
        				}else{
        					// $('.addClear').val('');
        					//清空签字
        					$('.clear-sign').click();
        					showReport();
        				}
    					if($("#recordsId").val()=='' || operate==0){
      						$(".update-show").addClass("hidden");
      						$('#signForm').toggleEditState(false);
      					}else{
      						$(".update-show").removeClass("hidden");
      						$('#signForm').toggleEditState(true);
      						getSignStatusByPostId(getProjectInfo().post,"auditForm");
      					}
    					setTimeout(function(){
        				    $("#projectImagesList").getImagesList({
        				    	"projId": getProjectInfo().projId,
        				    	"step": getStepId(),
        				    	//"projNo": getProjectInfo().projNo,
        				    	"busRecordId": $("#pcId").val() || '-1'
        				    });
        				},300);
    				}
        		}else if($(this).is($("#auditTab"))){
        			$('#grId1').val('');
        			$('#recordImagesList').removeClass("hidden");
        			if(operate==2 || operate ==1){
        				$('.auditEditBtn').removeClass('hidden');
        				$("#auditForm").toggleEditState(true);
        				getSignStatusByPostId(getProjectInfo().post,"auditForm");
        			}else{
        				$('.auditEditBtn').addClass('hidden');
        			}
        			
        			//记录列表
        			if(!$.fn.DataTable.isDataTable('#recordListTable')){
        				handleRecord();
        			}else{
        				if(operate==1){
        					$("#recordListTable").clearSelected();//去掉选中
        					showRecordPhoto();
        				}
        				searchData();//查询条件
        				$('#recordListTable').cgetData(true,recordListCallBack);
        				showReport();//加载报表
        			}
        		}
        	});
        }
    };
}();

var addDataRow;
$(".auditSavebtn2").on("click",function(){
	//获取记录区form表单的数据
	var t=$("#auditForm");
	
	var signtools = $("#auditForm").find(".signature-tool.sign-require"),
    stl = signtools.length,
    sBlank = 0;
	
	console.info("stl--"+stl);
	
    for(var i=0; i<stl; i++){
    	var tstool = signtools.eq(i).next("div").find("a.btn-white"),
    	tsinput = tstool.siblings(".sign-data-input");
    	if(!tsinput.val() || tsinput.val().length < 312){
    		tstool.addClass("required-sign");
    		sBlank++;
    	}
    }
    if(sBlank){
    	if(_inApk){
    		navigator.notification.alert('请完成签字！', null, '提示信息', '确定');
    	}else{
        	$("body").cgetPopup({
            	title: "提示信息",
            	content: "请完成签字!",
            	accept: popClose,
            	chide: true,
            	icon: "fa-warning",
            	newpop: 'new'
            });
    	}
    	return false;
    }
	
	//开启表单验证
	if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
		var json1=t.serializeJson();
		json1.projId = $('#projId').val();
        //是否签字
        if(json1.grBuilder != ""){
            //已签
            console.info("已完成签字");
            json1.isFinishSign = "1";
        }else{
            console.info("未完成签字");
            json1.isFinishSign = "0";
        }
		addDataRow = json1;
		t.parent().parent().loadMask("正在保存。。。",1,0.5);
		$.ajax({
			  	url: $("#actionName").val()+"/saveRecord",
			  	type: "POST",
			  	timeout : 5000,
			  	contentType: "application/json;charset=UTF-8",
	            data: JSON.stringify(json1),
			  	success: function (data) {
			  		t.parent().parent().hideMask();
                    var content = "数据保存成功！";
                    if(data === "false"){
                        content = "数据保存失败！";
                    }else if(data === "already"){
                        content = "此信息已被修改，请刷新页面！";
                    }else {
			  			addDataRow.grId = data;
                		$('#grId1').val(data);
			  			if(_inApk && $(".attach-images-list").find(".new-image").length){
			  				preImagesUpload($(".attach-images-list .uploadBtn"), $('#projId').val(), data, getStepId(), '');
			  			};
                        showReport();
                        successBack();
                    }
                    $("body").cgetPopup({
                        title: "提示信息",
                        content: content,
                        accept: sureClose,
                        chide: true,
                        icon: "fa-check-circle"
                    });
			  	}
			  });
		
	    //如果通过验证, 则移除验证UI
	    t.parsley().validate();
	   } else {
	        //如果未通过验证, 则加载验证UI
	        t.parsley().validate();
	   };
	
})
$(".deleteBtn2").on("click",function(){
	var len=$('#recordListTable').find('tr.selected').length;
	var dataTable = $('#recordListTable').DataTable();
	var info = dataTable.page.info();
	var dataRows=info.recordsTotal;
	 if(dataRows<=1){
		 $("body").cgetPopup({
				title: '提示',
				content: '必须保留一条数据，若你想全部删除你可以删除此条报验记录!',
				ahide:true,
				atext:'确定'
			});
		 return false;
	 }
	 if(len>0){
		 var rows =trSData.recordListTable.json;
		 var json1={};
		 
		 if(rows.grId){
			 delRowData = delRowData + rows.grId+',';
			 json1.grId = rows.grId;
			 json1.projId = rows.projId;
			 json1.pcId = rows.pcId;
			 json1.menuId = getStepId();
		 }
         var pcDesId = $("#pcDesId").val();
		console.info(json1);
		 $("body").cgetPopup({
				title: "提示信息",
             	content: "确定删除记录吗？",
             	accept: function(){
             		 //删除库中记录
           		 $.ajax({
           			  	url: $("#actionName").val()+"/deleteRecord/"+pcDesId,
           			  	type: "POST",
           			  	timeout : 5000,
           			  	contentType: "application/json;charset=UTF-8",
           			  	data: JSON.stringify(json1),
           			  	success: function (data) {
	           			  	if (data !== "false") {
		   			  			deleteRecordBack();
		   			  			showReport();
		   			  		    alertInfo("数据删除成功！");
		   				    }else{
		   				    	alertInfo("数据删除失败！");
		   				    }
           			  	}
           		});
           		 
             	},
             	icon: "fa-check-circle",
             	newpop: 'new'
            });
		
		 $('#auditForm').toggleEditState(true);
		// $('#recordListTable').selectRow(0);
	 }else{
		 $("body").cgetPopup({
				title: '提示信息',
				content: '请选择要删除的记录!',
				accept: delData,
				icon: 'fa-exclamation-circle',
		  });
	 }
});
var delData = function(){};
/**
 * 删除记录回调函数
 */
var deleteRecordBack = function(){
		$("#auditForm .addClear").val('');
		$("#auditForm .clear-sign").click();
		$('#recordListTable').cgetData(true,recordListCallBack);
        //删除照片
       var photos = $("#recordImagesList ul .attach-image-item");
       console.info(photos);
       if(photos.length>0){
    	   $.each(photos,function(i,e){
    		   deleteImage($(e));
    	   });
       }
}
/**
 * 记录区列表重新加载
 */
var recordListCallBack = function(){
	recordBack();
	var len = $('#recordListTable').DataTable().ajax.json().data ? $('#recordListTable').DataTable().ajax.json().data.length : $('#recordListTable').DataTable().ajax.json().length;
	if(len<1){
		//隐藏新增按钮
		$(".auditAddBtn2").addClass("hidden");
	}else{
		if(operate==2){
			//隐藏新增按钮
			$(".auditAddBtn2").addClass("hidden");
		}else{
			$(".auditAddBtn2").removeClass("hidden");
		}
	}
} 
/**
 * 列表区选中行事件处理
 */
var trSelOtherInfo = function(data){
	//$("#preservativeType").val(data.preservativeType);
	//$("#preservativeType1").val(data.preservativeType);
};





var sureClose=function(){
	var cwId=$("#grId").val();
	$.ajax({
		type:'post',
		url:'grooveRecord/saveSignNotice',
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
