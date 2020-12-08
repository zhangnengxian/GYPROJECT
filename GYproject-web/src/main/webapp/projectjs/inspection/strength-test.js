var recordListTable2,recordListTable1;
var recordListTable, projectCheckListTable,checkListData={},recordData={};
var projId=$('#projId').val();
checkListData.projId=projId;
var recordsIdArry= new Array();
var operate = 0;
var delRowData;
$("input").attr("required",true);
$("textarea").attr("required",true);  //页面上所有的input、textarea框必填
$("#suName").attr("required",false);  //因为有的部门没有监理公司，所以不用必填，清除必填
$("#inspectionResult").attr("required",false); //查验结果无需必填
var searchData = function(){
	if($('#pcIdNew').val()!==""){
		recordData.pcId=$('#pcIdNew').val();
	}else{
		recordData.pcId="-1";
	}
	recordData.stPipeType = $('#stPipeType').val();
	recordData.projId = $('#projId').val();
	recordData.flag = $("#flag1").val();//报验单完成标记
	//报验单中d的类型
	$('#stPipeType1').val($('#stPipeType').val());
}
/**
 * 庭院、干线、改管、公建
 * 初始化记录区列表
 */
var handleRecordList1 = function(){
	"use strict";
	searchData();
    if ($('#recordListTable1').length !== 0) {
    	recordListTable1= $('#recordListTable1').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBackRecord,true);
    		//隐藏遮罩
   			$('#recordListTable1').hideMask();
   			handleCheckBox1();
   			checkboxMonitor1();
   			showRecordPhoto();
   	    	//加载cpt文件
   			showReport();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [2],
            destroy:true,
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
            //ajax:'projectjs/inspection/json/strength_test_record.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	  			{"data":"stId"},
	  			{"data":"stDate"},
				{"data":"stPressure",className:"text-right"},
				{"data":"stRegulatorStartTime"},
				{"data":"stInspectMethod"},
				{"data":"stResult"},
				{"data":"gastDate"},
				{"data":"gasTPressure",className:"text-right"},
				{"data":"gastRegulatorStartTime"},
				{"data":"gastInspectMethod"},
				{"data":"gastResult"},
				{"data":"gasTtemperature"},
				{"data":"stInstruction"},
			],
			columnDefs: [{
				"targets":0,
				"render": function ( data, type, row, meta ) {
					return addCheckBox(data,row.pcId);

				}
			},{
				"targets":3,
				"render": function ( data, type, row, meta ) {
					return row.stRegulatorStartTime+"至"+row.stRegulatorEndTime;
				}
			},{
				"targets":4,
				"render": function ( data, type, row, meta ) {
					if(data=='1'){
						return "压差计";
					}else if(data=='0'){
						return "数字压力表";
					}else{
						return "";
					}
				}
			},{
				"targets":8,
				"render": function ( data, type, row, meta ) {
					return row.gastRegulatorStartTime+"至"+row.gastRegulatorEndTime;
				}
			},{
				"targets":9,
				"render": function ( data, type, row, meta ) {
					if(data=='1'){
						return "压差计";
					}else if(data=='0'){
						return "数字压力表";
					}else{
						return "";
					}
				}
			}],
			ordering:false
        });
    }
}
/**
 * 初始化记录区列表
 * 户内
 */
var handleRecordList2 = function(){
	"use strict";
	searchData();
    if ($('#recordListTable2').length !== 0) {
    	recordListTable2= $('#recordListTable2').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBackRecord,true);
    		//隐藏遮罩
   			$('#recordListTable2').hideMask();
   			//handleCheckBox();
   			//checkboxMonitor();
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
            //ajax:'projectjs/inspection/json/strength_test_record.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	  			{"data":"stId"}, 
	  			{"data":"riserNo"}, 
				{"data":"stRange"},
				{"data":"stPressure",className:"text-right"},
				{"data":"gasTRange"},
				{"data":"gasTPressure",className:"text-right"},
				{"data":"testMethod"},
				{"data":"testResult"},
				{"data":"testDate"}
			],
			columnDefs: [{
				"targets":0,
				"render": function ( data, type, row, meta ) {
					return addCheckBox(data,row.pcId);
					//return "";
				}
			}],
			ordering:false
        });
    }
}

//记录区显示照片列表
var showRecordPhoto = function(data){
	var t={};
	if($('#stPipeType').val()=='1'){
		t=$('#recordListTable1');
	}else{
		t=$('#recordListTable2');
	}
	 var len=t.find('tr.selected').length;
	 var recordId ='-1';
	 var pcId='';
	 var rows={};
	 if(len>0){
		 if($('#stPipeType').val()=='1'){
			 rows =trSData.recordListTable1.json;
		 }else{
			 rows =trSData.recordListTable2.json;
		 }
		 if(rows && rows.stId){
			 recordId = rows.stId;
			 pcId= rows.pcId;
			 $('#stId1').val(rows.stId);
			 $('#pcId1').val(rows.pcId);
		 }
		 console.info(recordId);
		// recordId = rows.stId;
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
	var type =  $("#stPipeType").val();
//alert(type);
	if(type=='1'){
		//初始化记录列表
		if(!$.fn.DataTable.isDataTable('#recordListTable1')){
			handleRecordList1();
		}else{
			if(operate==1){
				$("#recordListTable1").clearSelected();//去掉选中
				showRecordPhoto();
			}
			searchData();
			$('#recordListTable1').cgetData(true,recordListCallBack);
			showReport();
		}
	}else{
		//初始化记录列表
		if(!$.fn.DataTable.isDataTable('#recordListTable2')){
			handleRecordList2();
		}else{
			if(operate==1){
				$("#recordListTable2").clearSelected();//去掉选中
				showRecordPhoto();
			}
			searchData();
			$('#recordListTable2').cgetData(true,recordListCallBack);
			showReport();
		}
	}
}

/**
 * 页面初始化
 */
var strengthTest = function(){
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
             				$('#projectCheckListTable').cgetData(true,checkListCallBack);
             				$(".update-show").addClass("hidden");
             				$('#signForm').toggleEditState(false);
             				showReport();
             			}
     					operate=0;
     				}else{
     					$('#stPipeType').val($('#stPipeType1').val());
     					if(trSData.projectCheckListTable.json && $('#pcId').val()!=""){
     						showReport();
         				}else{
         					$('.addClear').val('');
         					//清空签字
         					$('.clear-sign').click();
         					showReport();
         				}
     					if($("#inspectionDate").val() == null || $("#inspectionDate").val() == ''){  //报验日期为空则取系统当前日期
         					var sysDate = $(".inspectionDate").val(getSysDate());
         				}
     					if($("#recordsId").val()==''  || operate==0){
      						$(".update-show").addClass("hidden");
      						$('#signForm').toggleEditState(false);
      					}else{
      						
      							$(".update-show").removeClass("hidden");
      							$('#signForm').toggleEditState(true);
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
         			$('#stId1').val('');
         			$('#recordImagesList').removeClass("hidden");
         			if(operate==2 || operate ==1){
         				$('.auditEditBtn').removeClass('hidden');
         				$("#auditForm").toggleEditState(true);
         				
         			}else{
         				$('.auditEditBtn').addClass('hidden');
         			}
         			//记录列表
        			handleRecord();
        			//修改时
    				if(operate==2){
    					$('.stPipeType').toggleEditState(false);
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
		
		/*if(getProjectInfo().projStatusId==$("#projStatus").val()){
			//待施工状态不允许试压
			alertInfo("请先完成开工报告，进入施工状态后方可进行试压！");
			return false;
		}*/
		
		
		var json1=t.serializeJson();
		json1.projId = $('#projId').val();
		console.info(json1);
		addDataRow = json1;
		$("#auditForm").loadMask("正在保存...", 1, 0.5);
		 var response = $.ajax({
			  	url: $("#actionName").val()+"/saveRecord",
			  	type: "POST",
			  	timeout : 5000,
			  	contentType: "application/json;charset=UTF-8",
			  	data: JSON.stringify(json1),
			  	success: function (data) {
			  		$("#auditForm").hideMask();	
			  		t.parent().parent().hideMask();
			  		var content = "数据保存成功！";
			  		if(data === "false"){
			  			content = "数据保存失败！";
			  		}else if(data === "already"){
	               		content = "此信息已被修改，请刷新页面！";
	               	}else {
			  			addDataRow.stId = data;
			  			$('#stId1').val(data);
			  			if(_inApk && $(".attach-images-list").find(".new-image").length){
			  				preImagesUpload($(".attach-images-list .uploadBtn"), $('#projId').val(), data, getStepId(), '');
			  			};
			  			showReport();
				        }
				  		$("body").cgetPopup({
			  				title: "提示信息",
			                	content: content,
			                	accept: successBack1,
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
	if($("#stPipeType").val()=='1'){
		t=$('#recordListTable1');
	}else{
		t=$('#recordListTable2');
	}
	var dataTable = t.DataTable();
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
	var len=t.find('tr.selected').length;
	var rows={};
	 if(len>0){
		 if($('#stPipeType').val()=='1'){
			 rows =trSData.recordListTable1.json;
		 }else{
			 rows =trSData.recordListTable2.json;
		 }
		 if(rows.stId){
			 delRowData = delRowData + rows.stId+',';
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
	var t={};
	if($("#stPipeType").val()=='1'){
		t=$('#recordListTable1');
	}else{
		t=$('#recordListTable2');
	}
	t.cgetData(true,recordListCallBack);
     //t.DataTable().rows( '.selected' ).remove().draw();
     //t.selectRow(0);
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
* 列表区选中行事件处理
*/
var trSelOtherInfo = function(data){
	$('.stPipeType').toggleEditState(false);
	$("#stPipeType").val(data.stPipeType);
	$("#stPipeType1").val(data.stPipeType);
	if(data.stPipeType=='1'){//庭院
		$(".indoorPipe").addClass("hidden");
		//清空值
		$(".indoorPipe input[type='text']").val("");
		
		$(".otherPipe").removeClass("hidden");
		
		$("#recordListTable1Div").removeClass("hidden");
		$("#recordListTable2Div").addClass("hidden");
	}else{//户内
		
		$(".otherPipe").addClass("hidden");
		$(".indoorPipe").removeClass("hidden");
		//清空热熔值
		$(".otherPipe input[type='text']").val("");
		$("#recordListTable1Div").addClass("hidden");
		$("#recordListTable2Div").removeClass("hidden");
	}
	if($("#suName").val()=='' || $("#suName").val()==null){
		$(".suUnit").addClass("hidden");
	}else{
		$(".suUnit").removeClass("hidden");
	}
	handleRecord();
	showReport();
};

//checkbox选中值组装到recordsId
var handleCheckBox1 = function(){
	var checkboxs=[];
	if($('#stPipeType').val()=='1'){
		checkboxs=$('#recordListTable1 .recordListTableCheckbox').not(".hidden");
	}else{
		checkboxs=$('#recordListTable2 .recordListTableCheckbox').not(".hidden");
	}
	$.each(checkboxs,function(i,e){
 		if($(e).is(":checked")){
 			//全局数组中不存在记录ID，则push到全局数组中去
 			if($(e).val()!='' && recordsIdArry.indexOf($(e).val()) == -1){
 				//recordsId +=$(e).val()+",";
 				recordsIdArry.push($(e).val());
 			}
		}else{
			//全局数组中存在记录ID，现在不选择，则从全局数组中删除
 			if($(e).val()!='' && recordsIdArry.indexOf($(e).val()) != -1){
 				//recordsId +=$(e).val()+",";
 				//删除该元素
 				recordsIdArry.splice(recordsIdArry.indexOf($(e).val()),1);
 			}
		}
 		
   	});	
	//var json = recordListTable
	if(recordsIdArry.length>0){
		$('.auditInpectBtn').removeClass('hidden');
	}else{
		$('.auditInpectBtn').addClass('hidden');
	}
	console.info(recordsIdArry+"===========recordsIdArry");
	//join()把数组的所有元素放入一个字符串。元素通过指定的分隔符进行分隔。
 	$("#recordsId").val(recordsIdArry.join(","));
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
	if($("#stPipeType").val()=='2'){
		t=$('#recordListTable2');
	}else{
		t=$('#recordListTable1');
	}
	t.cgetData(true,recordListCallBack);

    $(".allText").addClass("field-not-editable");
}

/**
 * 记录区列表重新加载
 */
var recordListCallBack = function(){
	recordBack();
	var t={};
	if($("#stPipeType").val()=='2'){
		t=$('#recordListTable2');
	}else{
		t=$('#recordListTable1');
	}
	var len = t.DataTable().ajax.json().data ? t.DataTable().ajax.json().data.length : t.DataTable().ajax.json().length;
	if(len<1){
		//隐藏新增按钮
		$(".auditAddBtn2").addClass("hidden");
	}else{
		if(operate==2 && $("#stPipeType").val()=='1'){
			//隐藏新增按钮
			$(".auditAddBtn2").addClass("hidden");
		}else{
			$(".auditAddBtn2").removeClass("hidden");
		}
	}
}

/**
 * 初始化信息，列表区列表
 */
var handleProjectCheckList = function() {
	"use strict";
	console.info('handleProjectCheckList');
	recordsIdArry.length=0;
	projectCheckListTable= $('#projectCheckListTable').on( 'init.dt',function(){
	$(this).bindDTSelected(trSelectedBack,true);
	$('#projectCheckListTable_filter input').attr('placeholder','施工工序');
	updateMonitor();
	//viewMonitor();
	//隐藏遮罩
	$("#projectCheckListTable").hideMask();
	addMonitor();
	//查询监听
	searchMonitor();
	showReport();
	
	//queryCheckRole();
	console.info(getProjectInfo().projStatusId+"-----------");
	if(getProjectInfo().projStatusId=='1022'){
		$(".finishBtn").removeClass("hidden");
	}else{
		$(".finishBtn").addClass("hidden");
	}
	//推送
	finishMonitor();
	//完成监听
	completeMonitor();
	//删除监听
	deleteMonitor();
	//重置监听按钮
	resetMonitor();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [ 15 ],
        dom: 'Bfrtip',
        buttons: [
            { text: '确认', className: 'btn-sm btn-query business-tool-btn checkRole finishBtn hidden' },
            { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
            { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' },
            { text: '删除', className: 'btn-sm btn-warn  business-tool-btn checkRole  hidden deleteBtn' },
            { text: '重置', className: 'btn-sm btn-warn  business-tool-btn checkRole  hidden resetBtn' },
            { text: '推送', className: 'btn-sm btn-query business-tool-btn checkRole  pushAuditBtn' },
           // { text: '详述', className: 'btn-sm btn-query business-tool-btn checkRole viewBtn' },
        ],
        serverSide: true, 
        ajax: {
            url: $("#actionName").val()+'/queryCheckList',
            type:'post',
            data: function(d){
               	$.each(checkListData,function(i,k){
               		d[i] = k;
               	});
               	
            },
            datasrc: 'data'
        },
       // ajax:'projectjs/inspection/json/trench_back_fill.json',
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
			{"data":"inspectionDate"},
			{"data":"process"},
			{"data":"inspectionResult"},
			{"data":"flagDes"},
		],
		order: [[ 0, "desc" ]],
		columnDefs: [{
			"targets":0,
			"visible":false
		},{
			"targets":4,
			"render": function ( data, type, row, meta ) {
				return data;
			}
		}] 
    });
};
//完成事件
var completeMonitor = function(){
	//完成事件
	  $(".pushAuditBtn").on("click",function(){
		  var len=$("#projectCheckListTable").find("tr.selected").length;
			if(len>0){
				  $("body").cgetPopup({
					  title: '提示',
					  content: '确认后进入审核中不可修改内容，您要确定吗？',
					  accept: completeBack
				  });
			}else{
				alertInfo("请先选择要完成的记录！");
			}
	  })
}

var completeBack = function(){

	   var dataJson=$("#signForm").serializeJson();
	   Base.subimt("strengthTest/pushAudit","POST",{pcId:dataJson.pcId,flag:2},function (data) {
           $('#projectCheckListTable').cgetData(true);  //列表重新加载
           var content = "确认成功！";
	       if (data==="0") { content="未完成签字不允许确认！"; }
		   if(data === "false"){ content = "确认失败！";}
           alertInfo(content);
	   })
};



//重置事件
var resetMonitor = function(){
	//重置事件
	  $(".resetBtn").on("click",function(){
		  recordsId = '';
		  var len=$("#projectCheckListTable").find("tr.selected").length;
			if(len>0){
                $('body').cgetPopup({
                    title:"提示",
                    content: '#inspectionData/inspectionResetPopPage',
                    accept:resetBack
                });
			}else{
				alertInfo("请先选择要重置的记录！");
			}
	  })
}

var resetBack = function(){
	 $('#flag').val(0);
	  $("#projectCheckListTable").loadMask("重置中···",1,0.5);
	    var dataJson=$("#signForm").serializeJson();
	 	var data={};
	 	data.pcId = dataJson.pcId;
	 	data.flag = 0;
	 	var resetData = $("#resetForm").serializeJson();
    	data.resetReason = resetData.resetReason;
	 	$.ajax({
         type: 'POST',
         url: 'checkList/resetCheck',
         contentType: "application/json;charset=UTF-8",
         data: JSON.stringify(data),
         success: function (data) {
        	 $("#projectCheckListTable").hideMask();
	               	var content = "数据保存成功！";
	               	if(data === "false"){
	               	   content = "数据保存失败！";
	               	}else{
	               		$('#projectCheckListTable').cgetData(true,checkListCallBack);  //列表重新加载
	               	}
	               	var myoptions = {
	                       	title: "提示信息",
	                       	content: content,
	                       	accept: popClose,
	                       	chide: true,
	                       	icon: "fa-check-circle",
	                       	newpop:'new'
	                   }
        
         
             $("body").cgetPopup(myoptions);
         },
         error: function (jqXHR, textStatus, errorThrown) {
             console.warn("信息保存失败！");
         }
     });
}
/**
 * 报验列表删除按钮监听
 */
var deleteMonitor=function(){
	  $(".deleteBtn").on("click",function(){
		  recordsId = '';
		  var len=$("#projectCheckListTable").find("tr.selected").length;
			if(len>0){
			  $("body").cgetPopup({
					title: '提示',
					content: '点击删除后，该报验记录不可恢复，您确定要删除该报验吗？',
				    accept: delDone   //点击确认以后执行删除函数
			  });
			}else{
				alertInfo("请先选择要删除的报验记录！");
			}
	  })
};
//删除函数
var delDone = function(){
	recordsIdArry.length=0;
	$("#projectCheckListTable").cdeleteRow("pcId","electrodeRecord/deleteList/"+"16",queryBack);
}
var queryBack=function(){   //执行函数以后的回调方法，加载列表区和报表
		showReport();	
}
/**
 * 列表区推送监听
 */
var finishMonitor = function(){
	$(".finishBtn").on("click",function(){
		recordsId = '';
		var len=$("#projectCheckListTable").find("tr.selected").length;
		if(len>0){
			 $("body").cgetPopup({
	               	title: "提示信息",
	               	content: "是否确认强度测试完成？完成后将进入待自检！",
	               	accept: finishDone,
	               	newpop: 'new',
	               	icon: "fa-check-circle"
	           });
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请先选中强度试验记录!',
				ahide:true,
				atext:'确定'
			});
		}
	});
}
var finishDone =function(){
	var projId=getProjectInfo().projId;
	$.ajax({
		type:'post',
		url:'strengthTest/finishStrengthTest',
		contentType: "application/json;charset=UTF-8",
        data: projId,
        success:function(data){
        	var content = "确认完成试验成功！";
        	if(data=="false"){
        		content = "确认完成试验失败！";
        	}else if(data=="noContract"){
        		content = "还没有完成施工合同,不能推送试压记录！";
        	}else{
        		$(".finishBtn").addClass("hidden");
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
 * 列表区增加监听
 */
var addMonitor = function(){
	$(".addBtn").on("click",function(){
		operate=1;
		//清空全局选中记录数组
		recordsIdArry.length=0;
		$("#recordsId").val("");
		$('.auditEditBtn').removeClass('hidden');
		$("#auditTab").tab("show");
		$('#pcIdNew').val('');
		$('.addClear,#flag,#finishedDate,#finishedOpr,#resetDate,#resetReason,#isFinishSign').val('');
		//清空签字
		$(".clear-sign").click();
		$("#auditForm").toggleEditState(true);
 		getSignStatusByPostId(getProjectInfo().post,"auditForm");
		if($(".drawDiv")){
			$("#drawName").val("");
			$(".hasVal").addClass("hidden");
			$(".noVal").removeClass("hidden");
		}
		
		/*if($("#recordImagesList")){
			$("#recordImagesList ul ").empty();
		}*/
		//$("#projectCheckListTable").clearSelected();//去掉选中列表区
		/*searchData();
		$('#recordListTable').cgetData(true,recordBack);*/
		//隐藏记录区删除
		//$('.deleteBtn').removeClass("hidden");
		//清空要增加的数据项
		//$('.addClear').val('');
		
		//$('#constructionUnit').val(getProjectInfo().cuName);
	});
}
/**
 * 列表区修改监听
 */
function updateMonitor(){
	$(".updateBtn").on("click",function(){
		//清空全局选中记录数组
		recordsIdArry.length=0;
		$("#recordsId").val("");
		var len=$("#projectCheckListTable").find("tr.selected").length;
		if(len>0){
			operate=2;
			var json=trSData.projectCheckListTable.json;
			$('#pcIdNew').val(json.pcId);
			$('#auditForm .addClear').val('');
			$('#auditForm').toggleEditState(true);

			$(".auditEditBtn").removeClass("hidden");
			$("#auditTab").tab("show");

			$(".auditAddBtn2").addClass("hidden");//隐藏新增按钮
			getSignStatusByPostId(getProjectInfo().post,"auditForm");
			//施工或竣工中
        	/*if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#signForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#signForm").find(".sign-data-input").toggleSign(false);
        	}*/

            if ($('#loginPost').val().indexOf($('#dataAdmin').val())!=-1){//数据管理员
                $(".allText").removeClass("field-not-editable");
                $(".allText").addClass("field-editable");
            }



        }else{
			$('#pcIdNew').val('');
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
 * 搜索监听
 */
var searchMonitor = function(){
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

// { text: '推送', className: 'btn-sm btn-query business-tool-btn checkRole finishBtn hidden' },
// { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
// { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' },
// { text: '删除', className: 'btn-sm btn-warn  business-tool-btn checkRole  hidden deleteBtn' },
// { text: '重置', className: 'btn-sm btn-warn  business-tool-btn checkRole  hidden resetBtn' },
// { text: '确认', className: 'btn-sm btn-query business-tool-btn checkRole  pushAuditBtn' },

//列表区行点击
var trSelectedBack = function(v, i, index, t, json){





	$("#pcIdNew").val(json.pcId);
	$("#businessOrderId").val(json.pcId);
	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
	if($("#menuDes")){
		$("#menuDes").val(menuDesc);
	}
	if($("#menuId")){
		$("#menuId").val(getStepId());
	}
	var menuId = getStepId();
	t.cgetDetail("signForm",$("#actionName").val()+'/viewProjectCheckList?menuId='+menuId,'',queryBack);


}
//报验单详述回调
var queryBack=function(data){
	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
	if($("#menuDes")){
		$("#menuDes").val(menuDesc);
	}
	if($("#menuId")){
		$("#menuId").val(getStepId());
	}
	$("#flag1").val(data.flag);
	var post=$("#loginPost").val();
	console.info("post-"+post);
	var builder=$("#builderPost").val();
	console.info("builder-"+builder);
	//已完成的只能现场代表推送
	//已完成的报验 不允许修改、完成和推送
	if(data.flag==1){
		$(".updateBtn,.completebtn").addClass("hidden");
		if(post.indexOf(builder)>=0){
			$(".finishBtn").removeClass("hidden");
		}
	}else{
		//未完成报验
        $(".finishBtn").addClass("hidden");
		if(post.indexOf(builder)>=0){	
			//是现场代表的
			$(".updateBtn,.completebtn").removeClass("hidden");
		}else{
			$(".completebtn,.finishBtn").addClass("hidden");
			$(".updateBtn").removeClass("hidden");
		}
		
	}

	if (data.isFinishSign!=1){
        $(".completebtn").addClass("hidden");
    }
	if(post.indexOf('999')>=0 || post.indexOf('93')>=0){   //如果是管理员或者数据管理员，显示删除按钮，否则隐藏删除按钮
		$(".deleteBtn").removeClass("hidden");
		if(data.flag==1){     //如果记录是完成状态，显示重置按钮，否则不显示重置按钮但显示完成按钮
			$(".resetBtn").removeClass("hidden");	
		}else{
			$(".completebtn").removeClass("hidden"); 
			$(".resetBtn").addClass("hidden");	 
		}	
	}else{
		$(".deleteBtn").addClass("hidden");	
	}

    $('.pushAuditBtn').removeClass('hidden');
    if (data.flag===2){//审核中
        $('.pushAuditBtn,.updateBtn,.resetBtn,.finishBtn,.deleteBtn').addClass('hidden');
    }else if (data.flag===1){
        $('.pushAuditBtn,updateBtn').addClass('hidden');
    }else {
        $('.finishBtn ,.resetBtn').addClass('hidden');
    }
	showDrawFile();
	trSelOtherInfo(data);
	showReport();
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
		//已完成的报验
		if(data.flag==1){
			$(".updateBtn,.completebtn").addClass("hidden");	
		}else{
			$(".updateBtn,.completebtn").removeClass("hidden");	
		}
	 }
	//清空签字
	$(".clear-sign").click();
	showReport();
}

//签字区放弃功能
$(".giveupbtn").on("click",function(){
	if($("#pcId").val()==""){
		//返回列表区
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
		
		$('#projectCheckListTable').cgetData(true,checkListCallBack);
	}else{
		//返回列表区
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
	}
});

/**
 * 传递记录id和报验单Id
 * 返回复选框html
 */
/*----------------------------------------------------------------------------------------------------------------------*/
var addCheckBox = function(recordId,pcId){

	var html='<input type="checkbox" class="recordListTableCheckbox"' ;
	if(recordId){
		html += 'value="'+recordId+'"';
	}else{
		html += 'disabled="disabled"';
	}
	if(pcId){
		//html += 'disabled="disabled"';
		html += 'data="'+pcId+'"';
		html += 'checked="checked" ';
		//已选中的记录组装到全局数组中
		if(recordsIdArry.indexOf(recordId) == -1){
			recordsIdArry.push(recordId);
		}
	}
	html += ' />';
	return html;
};


/**
 * 报验事件
 * 到签字区进行报验
 */
$(".auditInpectBtn").on("click",function(){
	var recordsId = $("#recordsId").val();
    var recordsIdLast=recordsId.charAt(recordsId.length-1);//获取字符串中的最后一位字符
	if(recordsIdLast== ','){//去掉最后一位逗号
		recordsId = recordsId.substr(0, recordsId.length - 1);
	}
	var stPipeType = $('#stPipeType').val();
	if(stPipeType=='1' && recordsId!=''){
		var rIds = recordsId.split(",");
		//去掉数组中重复的元素
		rIds= Array.from(new Set(rIds));
        if(rIds.length>1){
			alertInfo("只能选择一条记录进行报验！请重新选择！");
			return;
        }
	}
	$(".update-show").removeClass("hidden");
	//$('#signForm').toggleEditState(true);

	$("#signTab").tab("show");
	//$("#signForm").find(".sign-data-input").toggleSign(true);

	getSignStatusByPostId(getProjectInfo().post,"signForm");



	if($("#pcIdNew").val()){
		//已有报验
		$("#pcId").val($("#pcIdNew").val());
	}else{
		//未报验
		$("#pcId").val('');
		//清空要增加的数据项
		$('.addClear').val('');
		//清空签字
		//('.clear-sign').click();
	}
	if($(".drawDiv")){
		$(".searchButton").addClass("hidden");
		$(".Search_Button").removeClass("hidden");
		if($("#drawName").val()){
	 		 $(".hasVal").removeClass("hidden");
	 		 //显示附件删除按钮
	  		 $(".del_btn").removeClass("hidden");
	 		 $(".noVal").addClass("hidden");
	 		 $(".noVal+#filePreviews tr").remove();
	 	 }else{
	 		 $(".noVal").removeClass("hidden");

	 		 $(".hasVal").addClass("hidden");
	 	 }
	}

	
	if ($("#loginPost").val().indexOf($("#dataAdmin").val())==-1) {//非数据管理员
        $('#stRange1').removeClass("field-editable");
        $('#stRange1').addClass("field-not-editable");
    }

})


 //签字区保存功能
    $(".savebtn").on("click",function(){
    	$("#flag").val(0);
    	saveSignInfo();
    	//$("#signForm").cformSave("projectCheckListTable",saveBack,false);
    });
    var saveSignInfo = function(){
    	if($("#signForm").parsley().isValid()){

    		if($(".noVal+#filePreviews tr").length){
    			if($(".noVal+#filePreviews tr").length>1){
    				$("body").cgetPopup({
    					title: '提示',
	       				content: '一次只能上传一张简图或附件，请重新上传！',
    					accept:surplusAccept,
    					newpop:'second'
    					
    				})
    			}else{
    				$("#width").val("600");
        			$("#height").val("400");
        		 	//序列化
        	       	var menuDesc = $('[data-mid="' + getStepId() + '"] span').text();
        	       	$("#menuDes").val(menuDesc);
        	       	$("#menuId").val(getStepId());
        	       	var dataJson=$("#signForm").serializeJson();
        		 	dataJson.pcId=$('#pcIdNew').val();
        		 	var sign=[];//页面显示的签字
                	$.each(dataJson.sign,function(i,e){
                		if(!$("#"+e.fieldName).parent().parent().is(":hidden")){
                			sign = sign.concat(e);
                		}
                	});
                	dataJson.sign = sign;
        	       	$("#result").val(JSON.stringify(dataJson));
        	       	$(".start").click();   
    			}
    		}else{
    		 	var dataJson=$("#signForm").serializeJson();
    		 	dataJson.pcId=$('#pcIdNew').val();
    		 	dataJson.pcId=$('#pcIdNew').val();
    		 	var sign=[];//页面显示的签字
            	$.each(dataJson.sign,function(i,e){
            		if(!$("#"+e.fieldName).parent().parent().is(":hidden")){
            			sign = sign.concat(e);
            		}
            	});
            	dataJson.sign = sign;
    		 	var data={};
    		 	data = dataJson;

                var signCount=0;
                $(".allSign").each(function () {
                    if (!$(this).is(":hidden")){//不隐藏的签字
                        if ($(this).children().find(".sign-data-input").val()=="") {//统计没有签字的
                            signCount++;
                        }
                    }
                });
                if (signCount<1){//如果已签完字调用完成按钮方法
                    data.isFinishSign=1;
                }else{
                    console.info("未完成签字");
                    data.isFinishSign=0;
				}

    		 	//$("#signForm").parent().parent().loadMask("正在保存。。。",1,0.5);
    			$.ajax({
					    beforeSend: function(){
                            $('.update-show').addClass("hidden");
						},
    	               type: 'POST',
    	               url: $('#actionName').val()+'/saveSign',
    	               contentType: "application/json;charset=UTF-8",
    	               data: JSON.stringify(data),
    	               success: function (data) {
    	            	   $("#signForm").parent().parent().hideMask();
	    	               	var content = "数据保存成功！";
	    	               	if(data === "false"){
	    	               	   content = "数据保存失败！";
	    	               	   $('.update-show').removeClass("hidden");
	    	               	}else if(data === "already"){
	    	               		content = "此信息已被修改，请刷新页面！";
                                $('.update-show').removeClass("hidden");
	    	               	}else{
	    	               		$("#pcId").val(data);
	    	               		$("#pcIdNew").val(data);
	    	               	}
	    	               	var myoptions = {
	    	                       	title: "提示信息",
	    	                       	content: content,
	    	                       	accept: savedone,
	    	                       	chide: true,
	    	                       	icon: "fa-check-circle",
	    	                       	newpop:'new'
	    	               	}
    	                    $("body").cgetPopup(myoptions);
    	               },
    	               error: function (jqXHR, textStatus, errorThrown) {
    	                   console.warn("信息保存失败！");
    	               }
    	           });
    		}
    	}else{
        	//如果未通过验证, 则加载验证UI
        	$("#signForm").parsley().validate();
        }
    }
    // 附件保存回调1
    function saveBack(data){
        var content = "数据保存成功！";
        if(data.result === "false"){
            content = "数据保存失败！";
        }else {
            $("#pcId").val(data.operateId);
            $("#pcIdNew").val(data.operateId);
            $("#drawUrl").val(data.drawUrl);
        }
        var myoptions = {
            title: "提示信息",
            content: content,
            accept: savedone,
            chide: true,
            icon: "fa-check-circle"
        }
        $("body").cgetPopup(myoptions);
    }

    //附件保存回调2
    var savedone = function(){
    	$('.update-show').addClass("hidden");
     	$('#signForm').toggleEditState(false);
     	if($(".drawDiv")){
     		 if($("#drawName").val()){
           		 $(".hasVal").removeClass("hidden");
           		 $(".del_btn").addClass("hidden");
           		 $(".noVal").addClass("hidden");
           		 $(".noVal+#filePreviews tr").remove();
           	 }else{
           		 $(".noVal").removeClass("hidden");
           		 
           		 $(".hasVal").addClass("hidden");
           	 }
     	}
        console.info("pcId========="+ $("#pcId").val());
        var projId = $("#projId").val(); 
		if(_inApk && $(".attach-images-list").find(".new-image").length){
			preImagesUpload($(".attach-images-list .uploadBtn"), projId, '', getStepId(), $("#pcId").val());
		};
        showReport();
        
        sureClose();
    }
    
    /**
     * 记录区详述回调2
     */
  var recordBack = function(){
  	if(operate==2 || operate ==1){
  		$('.auditEditBtn').removeClass('hidden');
  		getSignStatusByPostId(getProjectInfo().post,"auditForm");
  	}else{
  		$('.auditEditBtn').addClass('hidden');
  		$("#auditForm").toggleEditState(false);
  	}
  	checkboxMonitor1();
  	handleCheckBox1();
  }
  
  /**
   * 记录区选中行事件
   */
  /*-------------------------------------------------------------------------------------------------------*/
  var trSelectedBackRecord = function(v, i, index, t, json){
  	//查询记录详述
	 recordsId=''; //每次行事件情况值
  	t.cgetDetail("auditForm",$("#actionName").val()+'/viewRecordById','',queryBackRecord);
  };

  //记录区选中行事件回调1
  var queryBackRecord = function(data){
  	//$('#erId1').val(data.erId);
  	//$('#pcId1').val(data.pcId);
	console.info("operate-----------"+operate+"  stPipeType--"+$("#stPipeType").val());
  	showRecordPhoto();
  	if(operate==2 || operate==1){  ////列表区修改时候不隐藏记录区增加按钮，否则隐藏
  			if($("#stPipeType").val()=='1'){   //若是干线、改管、庭院、公建管道则记录区只能有一条记录，故隐藏增加按钮
  				$(".auditAddBtn2").addClass("hidden");
  			}else{
  				$(".auditAddBtn2").removeClass("hidden");
  			}
	}else{
		$(".auditAddBtn2").addClass("hidden");
	}
  	recordBack();
  }
  
  /**
   * 记录区新增事件
  */
  $('.auditAddBtn2').on("click",function(){
	  	//清空录入框
		$("#auditForm .addClear").val('');
		$("#auditForm .clear-sign").click();
		$("#recordListTable").clearSelected();//去掉选中
		showRecordPhoto();
		//$("#auditForm").formReset();//清空记录区
  })
  /**
   * 页面附件或者缩略图显示
   */
  var showDrawFile = function(){
	  if($(".drawDiv")){
			$(".searchButton").addClass("hidden");
			$(".Search_Button").removeClass("hidden");
			if($("#drawName").val()){
		  		 $(".hasVal").removeClass("hidden");
		  		 //隐藏附件删除按钮
		  		 $(".del_btn").addClass("hidden");
		  		 $(".noVal").addClass("hidden");
		  		 $(".noVal+#filePreviews tr").remove();
		  	 }else{
		  		 $(".noVal").removeClass("hidden");
		  		 
		  		 $(".hasVal").addClass("hidden");
		  	 }
		}
  }
 
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
	//删除回调
  var deleteDone=function(){
	  	$("#drawName").val("");
		$(".hasVal").addClass("hidden");
		$(".noVal").removeClass("hidden");
  }
  
  
  var sureClose=function(){
		/*var cwId=$("#pcId").val();
		$.ajax({
			type:'post',
			url:'strengthTest/saveSignNotice',
			contentType: "application/json;charset=UTF-8",
	        data: cwId,
	        success:function(data){
	        	console.info(data);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            console.warn("数据保存失败！");
	        }
		})*/
	}