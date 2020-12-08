
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
 * 初始化记录区列表
 */
var handleRecord = function(){
	"use strict";
	searchData();//查询条件
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
            lengthMenu: [20],
            dom: 'Brtip',
            bStateSave:true,
            destroy:true,
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
           // ajax:'projectjs/inspection/json/electrode_baking_list.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	  			{"data":"brId"},
				{"data":"riserNo",className:"text-right"},
				{"data":"riserReducing",className:"text-right"},
				{"data":"segmentLen",className:"text-right"},
				{"data":"ballDiameter",className:"text-right"}
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
}

//记录区显示照片列表
var showRecordPhoto = function(){
	 var len=$('#recordListTable').find('tr.selected').length;
	 var recordId ='-1';
	 var pcId='';
	 if(len>0){
		 var rows =trSData.recordListTable.json;
		 $('#brId1').val(rows.brId);
		 $('#pcId1').val(rows.pcId);
		 recordId = rows.brId;
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
 * 页面初始化
 */
var ballRecord = function(){
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
      						// $('#signForm').toggleEditState(true);
                            getSignStatusByPostId(getProjectInfo().post,"signForm");
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
          			$('#brId1').val('');
          			$('#recordImagesList').removeClass("hidden");
          			/*if(operate==2 || operate ==1){
          				$('.auditEditBtn').removeClass('hidden');
          				$("#auditForm").toggleEditState(true);
          			}else{
          				$('.auditEditBtn').addClass('hidden');
          			}*/
          			//记录列表
        			if(!$.fn.DataTable.isDataTable('#recordListTable')){
        				handleRecord();
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
	               		addDataRow.brId = data;
			  			$('#brId1').val(data);
			  			if(_inApk && $(".attach-images-list").find(".new-image").length){
			  				preImagesUpload($(".attach-images-list .uploadBtn"), $('#projId').val(), data, getStepId(), '');
			  			};
			  			showReport();
			  			successBack();
				        }
				  		$("body").cgetPopup({
			  				title: "提示信息",
			                	content: content,
			                	accept: popClose,
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
		 if(rows.brId){
			 delRowData = delRowData + rows.brId+',';
		 }
		
		 $("body").cgetPopup({
				title: "提示信息",
             	content: "确定删除记录吗？",
             	accept: function(){
             		 //删除库中记录
           		 var response = $.ajax({
           			  	url: $("#actionName").val()+"/deleteRecord",
           			  	type: "POST",
           			  	timeout : 5000,
           			  	contentType: "application/json;charset=UTF-8",
           			  	data: JSON.stringify(rows),
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

