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
            dom: 'Brtip',
            destroy:true,
            bStateSave:true,
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
			        {"data":"ipwId"},
			{"data":"riserNo"}, 
			{"data":"installNum",className:"text-right"},
			{"data":"floor1"},
			{"data":"floor2"},
			{"data":"floor3"},
			{"data":"floor4"},
			{"data":"floor5"},
			{"data":"floor6"},
			{"data":"floor7"},
			{"data":"floor8"},
			{"data":"floor9"},
			{"data":"floor10"},
			{"data":"floor11"},
			{"data":"floor12"},
			{"data":"floor13"},
			{"data":"floor14"},
			{"data":"floor15"},
			{"data":"floor16"},
			{"data":"floor17"},
			{"data":"floor18"},
			{"data":"floor19"},
			{"data":"floor20"},
			{"data":"floor21"},
			{"data":"floor22"},
			{"data":"floor23"},
			{"data":"floor24"},
			{"data":"floor25"},
			{"data":"floor26"},
			{"data":"floor27"},
			{"data":"floor28"},
			{"data":"floor29"},
			{"data":"floor30"},
			{"data":"floor31"},
			{"data":"floor32"},
			{"data":"floor33"},
			{"data":"floor34"},
			{"data":"floor35"},
			  ],
			  order: [[ 0, "desc" ]],
			  columnDefs: [{
				  "targets":0,
			  "render": function ( data, type, row, meta ) {
					  return addCheckBox(data,row.pcId);
				  }
			  },{
				  "targets":3,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":4,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":5,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":6,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":7,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":8,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":9,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":10,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":11,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":12,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":13,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":14,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":15,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":16,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":17,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":18,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":19,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":20,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":21,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":22,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":23,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":24,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":25,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":26,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":27,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":28,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":29,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":30,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":31,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":32,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":33,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":34,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":35,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":36,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
			  }else{
				  return '';
					  }
				  }
			  },{
				  "targets":37,
			  "render": function ( data, type, row, meta ) {
			  if(data=='1'){
				  return '√';
			  }else if(data=='0'){
				  return '×';
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
		 $('#ebId1').val(rows.ebId);
		 $('#pcId1').val(rows.pcId);
		 recordId = rows.ebId;
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
var indoorPocketWatch = function(){
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
         					$('.addClear').val('');
         					//清空签字
         					$('.clear-sign').click();
         					showReport();
         				}
     					if($("#recordsId").val()==''  || operate==0){
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
         			$('#ebId1').val('');
         			$('#recordImagesList').removeClass("hidden");
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
		var jsonData = {};
		jsonData = json1;
		jsonData.projId = $('#projId').val();
		var json1=t.serializeJson();
		var floorStart =parseInt( $('#floorStart').val());
		var floorEnd = parseInt($('#floorEnd').val());
		
		for(var i=1;i<36;i++){
			if(i>=floorStart && i<= floorEnd){
				jsonData['floor'+i] = '1';
			}else{
				jsonData['floor'+i] = '';
			}
		}
		addDataRow = jsonData;
		t.parent().parent().loadMask("正在保存。。。",1,0.5);
		 var response = $.ajax({
			  	url: $("#actionName").val()+"/saveRecord",
			  	type: "POST",
			  	timeout : 5000,
			  	contentType: "application/json;charset=UTF-8",
			  	data: JSON.stringify(jsonData),
			  	success: function (data) {
			  		t.parent().parent().hideMask();
			  		var content = "数据保存成功！";
			  		if(data === "false"){
			  			content = "数据保存失败！";
			  		}else if(data === "already"){
	               		content = "此信息已被修改，请刷新页面！";
	               	}else {
	               		addDataRow.ebId = data;
			  			$('#ebId1').val(data);
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
		 if(rows.ebId){
			 delRowData = delRowData + rows.ebId+',';
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
      // $('#recordListTable').selectRow(0);
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