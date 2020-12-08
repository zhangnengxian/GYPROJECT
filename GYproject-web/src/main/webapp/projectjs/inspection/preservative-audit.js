
var searchData = function(){
	if($('#pcIdNew').val()!==""){
		recordData.pcId=$('#pcIdNew').val();
	}else{
		recordData.pcId=-1;
	}
	recordData.preservativeType = $('#preservativeType').val();
	recordData.projId = $('#projId').val();
	recordData.flag = $("#flag1").val();//报验单完成标记
	//报验单中的防腐类型
	$('#preservativeType1').val($('#preservativeType').val());
}
var handleRecordList2 = function(){
	"use strict";
	searchData();
    if ($('#recordListTable').length !== 0) {
    	recordListTable = $('#recordListTable').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBackRecord,true);
   			//隐藏遮罩
   			$('#recordListTable').hideMask();
   			handleCheckBox();
   			checkboxMonitor();
   	    	//加载cpt文件
   			showReport();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            destroy:true,
            bStateSave:true,
            dom: 'Brtip',
            buttons: [
            ],
            serverSide:false,
            ajax: {
                url: $("#actionName").val()+'/queryRecords',
                type:'post',
                data: function(d){
                   	$.each(recordData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax:'projectjs/inspection/json/trench_back_fill_record.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            
            columns: [
                    {"data":"dpId"},
      				{"data":"dpDate"}, 
      				{"data":"pipePosition"},
      				{"data":"pipeSectionLen",className:"text-right"},
      				{"data":"pipeShaftInpect"},
    				{"data":"pipeFittingInpect"},
    				{"data":"isMetallicLuster"},
      				{"data":"isPrimer"},
      				{"data":"otherInpect"},
      				{"data":"preservativeType"},
			],
			columnDefs: [{
				"targets":0,
				"render": function ( data, type, row, meta ) {
					return addCheckBox(data,row.pcId);
				}
			},{
				"targets":4,
				"render":function(data,type,row,meta){
					if(data==1){
						return "合格";
					}else if(data==0){
						return "不合格";
					}else{
						return '';
					}
				}
			},{
				"targets":5,
				"render":function(data,type,row,meta){
					if(data==1){
						return "合格";
					}else if(data==0){
						return "不合格";
					}else{
						return '';
					}
				}
			},{
				"targets":6,
				"render":function(data,type,row,meta){
					if(data==1){
						return "是";
					}else if(data==0){
						return "否";
					}else{
						return '';
					}
				},
				"visible": false
			},{
				"targets":7,
				"render":function(data,type,row,meta){
					if(data==1){
						return "是";
					}else if(data==0){
						return "否";
					}else{
						return '';
					}
				},
				"visible": false
			},{
				"targets":8,
				"render":function(data,type,row,meta){
					if(data==1){
						return "合格";
					}else if(data==0){
						return "不合格";
					}else{
						return '';
					}
				}
			},{
				"targets":9,
				"render":function(data,type,row,meta){
					if(data=='1'){
						return "钢管及接头防腐记录";
					}else if(data=="2"){
						return "钢管防腐记录";
					}else{
						return '';
					}
				}
			}],
			fnHeaderCallback:function(oSettings){
 				// $('#recordListTable thead th');
			},
			ordering:false
        });
    }
}
/**
 * 初始化记录区列表
 */
var handleRecordList1 = function(){
	"use strict";
	searchData();
    if ($('#recordListTable').length !== 0) {
    	recordListTable= $('#recordListTable').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBackRecord,true);
   			//隐藏遮罩
   			$('#recordListTable').hideMask();
   			handleCheckBox();
   			checkboxMonitor();
   			showRecordPhoto();
   	    	//加载cpt文件
   			showReport();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            destroy:true,
            dom: 'Brtip',
            buttons: [
            ],
            //serverSide:true,
            ajax: {
                url: $("#actionName").val()+'/queryRecords',
                type:'post',
                data: function(d){
                   	$.each(recordData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax:'projectjs/inspection/json/trench_back_fill_record.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                    {"data":"dpId"},
      				{"data":"dpDate"}, 
      				{"data":"pipePosition"},
      				{"data":"pipeSectionLen",className:"text-right"},
      				{"data":"pipeShaftInpect"},
    				{"data":"pipeFittingInpect"},
      				{"data":"isMetallicLuster"},
      				{"data":"isPrimer"},
      				{"data":"otherInpect"},
      				{"data":"preservativeType"},
			],
			columnDefs: [{
				"targets":0,
				"render": function ( data, type, row, meta ) {
					return addCheckBox(data,row.pcId);
				}
			},{
  				"targets":4,
  				"render":function(data,type,row,meta){
  					if(data==1){
  						return "合格";
  					}else if(data==0){
  						return "不合格";
  					}else{
  						return '';
  					}
  				},
  				"visible": false
  			},{
  				"targets":5,
  				"render":function(data,type,row,meta){
  					if(data==1){
  						return "合格";
  					}else if(data==0){
  						return "不合格";
  					}else{
  						return '';
  					}
  				},
  				"visible": false
  			},{
  				"targets":6,
  				"render":function(data,type,row,meta){
  					if(data==1){
  						return "是";
  					}else if(data==0){
  						return "否";
  					}else{
  						return '';
  					}
  				}
  			},{
  				"targets":7,
  				"render":function(data,type,row,meta){
  					if(data==1){
  						return "是";
  					}else if(data==0){
  						return "否";
  					}else{
  						return '';
  					}
  				}
  			},{
  				"targets":8,
  				"render":function(data,type,row,meta){
  					if(data==1){
  						return "合格";
  					}else if(data==0){
  						return "不合格";
  					}else{
  						return '';
  					}
  				}
  			},{
  				"targets":9,
  				"render":function(data,type,row,meta){
  					if(data=='1'){
  						return "钢管及接头防腐记录";
  					}else if(data=="2"){
  						return "钢管防腐记录";
  					}else{
  						return '';
  					}
  				}
  			}],
			ordering:false
        });
    }
}

//记录区显示照片列表
var showRecordPhoto = function(){
	 var len=$('#recordListTable').find('tr.selected').length;
	 var recordId ='-1';
	 var pcId='';
	 if(len>0){
		 var rows =trSData.recordListTable.json;
		 $('#dpId1').val(rows.dpId);
		 $('#pcId1').val(rows.pcId);
		 recordId = rows.dpId;
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

var handleRecord = function(){
	if($("#preservativeType").val()=='1'){
		if(!$.fn.DataTable.isDataTable('#recordListTable')){
			handleRecordList1();
		}else{
			if(operate==1){
				$("#recordListTable").clearSelected();//去掉选中
				showRecordPhoto();
			}
			searchData();
			$('#recordListTable').cgetData(true,recordListCallBack);
			showReport();
		}
	}else{
		if(!$.fn.DataTable.isDataTable('#recordListTable')){
			handleRecordList2();
		}else{
			if(operate==1){
				$("#recordListTable").clearSelected();//去掉选中
				showRecordPhoto();
			}
			searchData();
			$('#recordListTable').cgetData(true,recordListCallBack);
			showReport();
		}
	}
	//照片列表
	showRecordPhoto();
}

/**
 * 页面初始化
 */
var preservativeAndAudit = function(){
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
    					$('#preservativeType').val($('#preservativeType1').val());
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
      						//$('#signForm').toggleEditState(true);
      						getSignStatusByPostId(getProjectInfo().post,"auditForm");
      					}
    					setTimeout(function(){
        				    $("#projectImagesList").getImagesList({
        				    	"projId": getProjectInfo().projId,
        				    	"step": getStepId(),
        				    //	"projNo": getProjectInfo().projNo,
        				    	"busRecordId": $("#pcId").val() || '-1'
        				    });
        				},300);
    				}
        		}else if($(this).is($("#auditTab"))){
        			$('#dpId1').val('');
        			$('#recordImagesList').removeClass("hidden");
        			if(operate==2 || operate ==1){
        				$('.auditEditBtn').removeClass('hidden');
        				//$("#auditForm").toggleEditState(true);
        				getSignStatusByPostId(getProjectInfo().post,"auditForm");
        				//修改时
        				if(operate==2){
        					$(".selectType").attr("disabled", "disabled");
        				}
        			}else{
        				$('.auditEditBtn').addClass('hidden');
        			}
        			//记录列表
         			handleRecord();
        		}
        	});
        }
    };
}();

var addDataRow;
$(".auditSavebtn2").on("click",function(){
	//获取记录区form表单的数据
	var t=$("#auditForm");
	//开启表单验证
	if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
		var json1=t.serializeJson();
		json1.projId = $('#projId').val();
		addDataRow = json1;
        //是否签字
        if(json1.builder != ""){
            //已签
            console.info("已完成签字");
            json1.isFinishSign = "1";
        }else{
            console.info("未完成签字");
            json1.isFinishSign = "0";
        }
		t.parent().parent().loadMask("正在保存。。。",1,0.5);
		 var response = $.ajax({
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
			  			addDataRow.dpId = data;
			  			$('#dpId1').val(data);
			  			var projNo = data;
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
	
		 if(rows.dpId){
			 delRowData = delRowData + rows.dpId+',';
			 json1.dpId = rows.dpId;
			 json1.projId = rows.projId;
			 json1.pcId = rows.pcId;
			 json1.menuId = getStepId();
		 }
		 var pcDesId = $("#pcDesId").val();
		 $("body").cgetPopup({
				title: "提示信息",
             	content: "确定删除记录吗？",
             	accept: function(){
             		 //删除库中记录
           		 var response = $.ajax({
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
       //$("#recordListTable").DataTable().rows( '.selected' ).remove().draw();
       //$('#recordListTable').selectRow(0);
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
	
	var len = $("#recordListTable").DataTable().ajax.json().data ? $("#recordListTable").DataTable().ajax.json().data.length : $("#recordListTable").DataTable().ajax.json().length;
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
	$("#preservativeType").val(data.preservativeType);
	$("#preservativeType1").val(data.preservativeType);
};

var sureClose=function(){
	var cwId=$("#cwId").val();
	$.ajax({
		type:'post',
		url:'preservative/saveSignNotice',
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
