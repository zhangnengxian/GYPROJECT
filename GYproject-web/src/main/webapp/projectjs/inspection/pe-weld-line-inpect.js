var recordListTable2,recordListTable1;

var searchData = function(){
	if($('#pcIdNew').val()!==""){
		recordData.pcId=$('#pcIdNew').val();
	}else{
		recordData.pcId=-1;
	}
	recordData.projId = $('#projId').val();
	recordData.meltConnectType = $('#meltConnectType').val();
	recordData.flag = $("#flag1").val();//报验单完成标记
	//报验单中d的类型
	$('#meltConnectType1').val($('#meltConnectType').val());
}
/**
 * 初始化记录区列表
 * 电熔连接
 */
var handleRecordList1 = function(){
	"use strict";
	searchData();
    if ($('#weldLineInpectElectricMeltListTable').length !== 0) {
    	recordListTable1= $('#weldLineInpectElectricMeltListTable').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBackRecord,true);
    		//隐藏遮罩
   			$('#weldLineInpectElectricMeltListTable').hideMask();
   			handleCheckBox1();
   			checkboxMonitor1();
   			showRecordPhoto();
   	    	//加载cpt文件
   			showReport();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            destroy:true,
            dom: 'Brtip',
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
           // ajax:'projectjs/inspection/json/pe_weld_line_inpect_electric_list.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	  			{"data":"peWliId"}, 
	  			{"data":"peWeldLineNo"}, 
				{"data":"isComplete"},
				{"data":"isHaveMelts"},
				{"data":"isCoaxial"},
				{"data":"isScratch"},
				{"data":"isHoleCharging"},
				{"data":"cuDate"}
			],
			columnDefs: [{
				"targets":0,
				"render": function ( data, type, row, meta ) {
					return addCheckBox(data,row.pcId);
				}
			},{
				"targets":2,
				"render": function ( data, type, row, meta ) {
					if(data=='1'){
						return "是";
					}else if(data=='0'){
						return "否";
					}else{
						return '';
					}
				}
			},{
				"targets":3,
				"render": function ( data, type, row, meta ) {
					if(data=='1'){
						return "是";
					}else if(data=='0'){
						return "否";
					}else{
						return '';
					}
				}
			},{
				"targets":4,
				"render": function ( data, type, row, meta ) {
					if(data=='1'){
						return "是";
					}else if(data=='0'){
						return "否";
					}else{
						return '';
					}
				}
			},{
				"targets":5,
				"render": function ( data, type, row, meta ) {
					if(data=='1'){
						return "是";
					}else if(data=='0'){
						return "否";
					}else{
						return '';
					}
				}
			},{
				"targets":6,
				"render": function ( data, type, row, meta ) {
					if(data=='1'){
						return "是";
					}else if(data=='0'){
						return "否";
					}else{
						return '';
					}
				}
			}],
			ordering:false
        });
    }
}

/**
 * 初始化记录区列表
 * 热熔连接
 */
var handleRecordList2 = function(){
	"use strict";
	searchData();
    if ($('#weldLineInpectHotMeltListTable').length !== 0) {
    	recordListTable2= $('#weldLineInpectHotMeltListTable').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBackRecord,true);
    		//隐藏遮罩
   			$('#weldLineInpectHotMeltListTable').hideMask();
   			handleCheckBox1();
   			checkboxMonitor1();
   			showRecordPhoto();
   	    	//加载cpt文件
   			showReport();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            destroy:true,
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
           // ajax:'projectjs/inspection/json/pe_weld_line_inpect_list.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	  			{"data":"peWliId"}, 
	  			{"data":"peWeldLineNo"}, 
				{"data":"isLeakageWeld"},
				{"data":"isCurlDefect"},
				{"data":"curlCheck"},
				{"data":"weldRingWidthB",className:"text-right"},
				{"data":"weldRingHeightH",className:"text-right"},
				{"data":"weldRingSeamH",className:"text-right"},
				{"data":"wrongEdge"},
				{"data":"cuDate"}
			],
			columnDefs: [{
				"targets":0,
				"render": function ( data, type, row, meta ) {
					return addCheckBox(data,row.pcId);
				}
			},{
				"targets":2,
				"render": function ( data, type, row, meta ) {
					if(data=='1'){
						return "有";
					}else if(data=='0'){
						return "无";
					}else{
						return '';
					}
				}
			},{
				"targets":3,
				"render": function ( data, type, row, meta ) {
					if(data=='1'){
						return "是";
					}else if(data=='0'){
						return "否";
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
var showRecordPhoto = function(data){
	var t={};
	if($('#meltConnectType').val()=='1'){
		t=$('#weldLineInpectHotMeltListTable');
	}else{
		t=$('#weldLineInpectElectricMeltListTable');
	}
	 var len=t.find('tr.selected').length;
	 var recordId ='-1';
	 var pcId='';
	 var rows={};
	 if(len>0){
		 if($('#meltConnectType').val()=='1'){
			 rows =trSData.weldLineInpectElectricMeltListTable.json;
		 }else{
			 rows =trSData.weldLineInpectHotMeltListTable.json;
		 }
		 console.info(rows);
		if(rows && rows.peWliId){
			recordId = rows.peWliId;
			$('#peWliId1').val(rows.peWliId);
			$('#pcId1').val(rows.pcId);
			pcId = rows.pcId;
		}
		 console.info(recordId);
		// recordId = rows.peWliId;
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
	var type =  $("#meltConnectType").val();

	if(type=='2'){
		if(!$.fn.DataTable.isDataTable('#weldLineInpectHotMeltListTable')){
			handleRecordList2();
		}else{
			if(operate==1){
				$("#weldLineInpectHotMeltListTable").clearSelected();//去掉选中
				showRecordPhoto();
			}
			searchData();
			$('#weldLineInpectHotMeltListTable').cgetData(true,recordListCallBack);
			showReport();
		}
	}else{
		if(!$.fn.DataTable.isDataTable('#weldLineInpectElectricMeltListTable')){
			handleRecordList1();
		}else{
			if(operate==1){
				$("#weldLineInpectElectricMeltListTable").clearSelected();//去掉选中
				showRecordPhoto();
			}
			searchData();
			$('#weldLineInpectElectricMeltListTable').cgetData(true,recordListCallBack);
			showReport();
		}
	}
	
}

/**
 * 页面初始化
 */
var peWeldLineInpect = function(){
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
     					$('#meltConnectType').val($('#meltConnectType1').val());
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
         			$('#peWliId1').val('');
         			$('#recordImagesList').removeClass("hidden");
         			//记录列表
         			handleRecord();
         			if(operate==2 || operate ==1){
         				$('.auditEditBtn').removeClass('hidden');
         				$("#auditForm").toggleEditState(true);
         				//修改时
        				if(operate==2){
        					$(".selectType").attr("disabled", "disabled");
        				}
         			}else{
         				$('.auditEditBtn').addClass('hidden');
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
	               		addDataRow.peWliId = data;
			  			$('#peWliId1').val(data);
			  			if(_inApk && $(".attach-images-list").find(".new-image").length){
			  				preImagesUpload($(".attach-images-list .uploadBtn"), $('#projId').val(), data, getStepId(), '');
			  			};
			  			showReport();
			  			successBack1();
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
	var t={};
	if($("#meltConnectType").val()=='2'){
		t=$('#weldLineInpectHotMeltListTable');
		var dataTable = t.DataTable();
	}else{
		t=$('#weldLineInpectElectricMeltListTable');
		var dataTable = t.DataTable();
	}
	var len=t.find('tr.selected').length;
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
	var rows={};
	 if(len>0){
		 if($('#meltConnectType').val()=='1'){
			 rows =trSData.weldLineInpectElectricMeltListTable.json;
		 }else{
			 rows =trSData.weldLineInpectHotMeltListTable.json;
		 }
		 var json1={};
		 
		 if(rows.peWliId){
			 delRowData = delRowData + rows.peWliId+',';
			 json1.peWliId = rows.peWliId;
			 json1.projId = rows.projId;
			 json1.pcId = rows.pcId;
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
	var t={};
	if($("#meltConnectType").val()=='2'){
		t=$('#weldLineInpectHotMeltListTable');
	}else{
		t=$('#weldLineInpectElectricMeltListTable');
	}
	t.cgetData(true,recordListCallBack);
       //t.DataTable().rows( '.selected' ).remove().draw();
      // t.selectRow(0);
        //删除照片
       var photos = $("#recordImagesList ul .attach-image-item");
       if(photos.length>0){
    	   $.each(photos,function(i,e){
    		   deleteImage($(e));
    	   });
       }
}
/**
 * 列表区选中行事件处理
 */
var trSelOtherInfo = function(data){
	$('.meltConnectType').toggleEditState(false);
	$("#meltConnectType").val(data.meltConnectType);
	$("#meltConnectType1").val(data.meltConnectType);
	if(data.meltConnectType=='2'){
		$(".electricMelt").addClass("hidden");
		//清空电熔值
		$(".electricMelt input[type='text']").val("");
		
		$(".hotMelt").removeClass("hidden");
		
		$("#hotMeltListTableDiv").removeClass("hidden");
		$("#electricMeltListTableDiv").addClass("hidden");
		
	}else{
		$(".electricMelt").removeClass("hidden");
		$(".hotMelt").addClass("hidden");
		//清空热熔值
		$(".hotMelt input[type='text']").val("");
		$("#hotMeltListTableDiv").addClass("hidden");
		$("#electricMeltListTableDiv").removeClass("hidden");
	}
	handleRecord();
	showReport();
};

//checkbox选中值组装到recordsId
var handleCheckBox1 = function(){
	var checkboxs=[];
	if($('#meltConnectType').val()=='2'){
		checkboxs=$('#weldLineInpectHotMeltListTable .recordListTableCheckbox');
	}else{
		checkboxs=$('#weldLineInpectElectricMeltListTable .recordListTableCheckbox');
	}
	var recordsId='';
	$.each(checkboxs,function(i,e){
		if($(e).is(":checked")){
			if($(e).val()!=''){
				recordsId +=$(e).val()+",";
			}
		}
 	});
	//var json = recordListTable
	if(recordsId.length>0){
		$('.auditInpectBtn').removeClass('hidden');
	}else{
		$('.auditInpectBtn').addClass('hidden');
	}
	$("#recordsId").val(recordsId);
}

/**
 * checkbox选中事件
 */
var checkboxMonitor1 = function(){
	$(".recordListTableCheckbox").on("click",function(){
		handleCheckBox1();
	})
}

var successBack1 = function(){
	var t={};
	console.info($("#meltConnectType").val()+"============meltConnectType");
	if($("#meltConnectType").val()=='2'){
		t=$('#weldLineInpectHotMeltListTable');
	}else{
		t=$('#weldLineInpectElectricMeltListTable');
	}
	t.cgetData(true,recordListCallBack);
}

/**
 * 记录区列表重新加载
 */
var recordListCallBack = function(){
	recordBack();
	var t={};
	if($("#meltConnectType").val()=='2'){
		t=$('#weldLineInpectHotMeltListTable');
	}else{
		t=$('#weldLineInpectElectricMeltListTable');
	}
	var len = t.DataTable().ajax.json().data ? t.DataTable().ajax.json().data.length : t.DataTable().ajax.json().length;
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