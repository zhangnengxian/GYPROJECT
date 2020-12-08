/**查询条件*/
var changeData = {},
visaData = {},
materialdata = {},
materialListTable,
materialListTablePop,
state,
mcData = {},
rowData = {},
materialData,
changeRecordTable,visaRecordTable,
accessoryData={},accessoryTable,histSearchData={};
var menuId="110303";
changeData.menuId=menuId;
visaData.menuId=menuId;
var handleChangeRecord = function() {
	"use strict";
    if ($('#changeRecordTable').length !== 0) {
    	changeData.cmState=0;
    	changeRecordTable=$('#changeRecordTable').on( 'init.dt',function(){
    		//搜索
    		$('#changeRecordTable_filter input').attr('placeholder','变更编号');   
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//隐藏遮罩
   			$('#changeRecordTable').hideMask();    			
   			$("#budgetAdjustbox").cgetContent("changeRecord/viewPage");//预算调整页面
   			$("#suppContbox").cgetContent("changeRecord/suppCont");//
   			$("#alterationInfobox").cgetContent("changeRecord/alterationInfo");//记录详情页面
   	    	//查询监听
   	    	searchMonitor();
   	    	confirmBtn();
			reassignmentBtn();
   	    	queryBtn();
   	    	//changeBack();
   	   //跳转链接
   			if (crossPageBus) {
   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   					//跳转后销毁对象
   				crossPageBus = null
   			}
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'fBrtip',
            bStateSave:true,
            buttons: [
				{ text: '改派', className: 'btn-sm btn-query business-tool-btn reassignmentBtn'},
                { text: '查询', className: 'btn-sm btn-query business-tool-btn queryBtn'},
                { text: '推送', className: 'btn-sm btn-query business-tool-btn confirmBtn hidden' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'changeRecord/queryDesignAlteration',
                type:'post',
                data: function(d){
                   	$.each(changeData,function(i,k){
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
            select: true,  //支持多选
            columns: [
                {"data":"cmId",className:"none never"},
      			{"data":"cmNo", responsivePriority: 1},
      			{"data":"changeTypeDes", responsivePriority: 5},
	  			{"data":"cmDate", responsivePriority: 4}, 
	  			{"data":"projName", responsivePriority: 2},
	  			{"data":"designChangeTypeDes"},
	  			{"data":"projId"}/*,
	  			{"data":"cuReason", responsivePriority: 6},
	  			{"data":"overdue", className:"none never"},
	  			{"data":"auditState", responsivePriority: 3}*/
	  			
			],
			/*隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉）*/
			columnDefs: [{
		        "targets": 0, 
		        "visible":false
			},{
				"targets":4,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":2,
				 "orderable":false
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
},

//查询监听
searchMonitor=function(){
	//基础条件查询添加监听
	$('#changeRecordTable_filter input').on('change',function(){
		var cmNo = $('#changeRecordTable_filter input').val();
		changeData = {};
		changeData.cmNo = cmNo;changeData.menuId=menuId;
		$('#changeRecordTable').cgetData(true, changeBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#changeRecordTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

/**
 * 签证记录
 */
var handleVisaRecord = function() {
	"use strict";
    if ($('#visaRecordTable').length !== 0) {
    	//visaData.evState=0;
		visaRecordTable=$('#visaRecordTable').on('init.dt', function(){
    		//搜索
    		/*$('#visaRecordTable_filter input').attr('placeholder','签证日期');*/
    		$("#visaRecordTable_wrapper").prepend('<div class="dateFilter pull-left"><input type="text" class="form-control input-sm datepicker-default" placeholder="签证日期" value=""></div>');
    		$('.datepicker-default').datepicker({
    			autoclose: true
    		});
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack, true);
    		//隐藏遮罩
   			$('#visaRecordTable').hideMask();
   		
   	    	//查询监听
   	    	visaSearch();
   	    	confirmBtn1();
   	    	queryBtn1();
   	    	visaBack();
   	    	voidMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            bStateSave:true,
            buttons: [
              { text: '查询', className: 'btn-sm btn-query business-tool-btn queryBtn1'},
              { text: '确认', className: 'btn-sm btn-query business-tool-btn confirmBtn1 hidden '},
              { text: '作废', className: 'btn-sm btn-warn business-tool-btn  voidBtn hidden'}      
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'changeRecord/queryEngineeringVisa',
                type:'post',
                data: function(d){
                   	$.each(visaData,function(i,k){
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
            select: true,  //支持多选
            columns: [
                {"data":"evId",className:"none never"},
          		{"data":"visaDate", responsivePriority: 4},
    			{"data":"projNo", responsivePriority: 3},
    			{"data":"projName", responsivePriority: 2},
    			{"data":"flag",className:"none never","createdCell": function (td, cellData, rowData, row, col) {
					if(cellData=='1'){
						console.info(cellData);
						$(td).parent().children().css("background", "rgb(255, 219, 219)");
						//$(td).attr("id",row);
						}
					}
				},
				{"data":"evNo", responsivePriority: 1},
				{"data":"projId",className:"none never"}
			],
			columnDefs: [{
				"targets": 0, 
		        "visible":false
			},{
				"targets": 1,
				"render":function(data,type,row,meta){
					if(data !=="" && data!==null){
						console.info("d.."+data);
						return format(data);
					}else{
						return data;
					}
				}
			},{
				"targets":3,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 15, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
        
    }
},

//查询监听
visaSearch=function(){
	//基础条件查询添加回车事件
	$('.dateFilter input').off("change").on("change",function(){
		var visaDate = $('.dateFilter input').val();
		visaData = {};
		visaData.visaDate = visaDate;
		visaData.menuId=menuId;
		$('#visaRecordTable').cgetData(false,visaBack,true);  //列表重新加载
	});
	
}


searchData=function(){
	//基础条件查询添加监听
	$('#materialListTablePop_filter input').on('change',function(){
		var materialName = $('#materialListTablePop_filter input').val();
		materialdata = {};
		if($("#projId").val()==""){
	    	materialdata.projId=-1;	
	    }else{
	    	materialdata.projId=$("#projId").val();
	    }
		materialdata.materialName = materialName;
		console.info(materialdata);
		$('#materialListTablePop').cgetData();  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#materialListTablePop_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}
/**
 * 材料变更(上)
 */
var rowIndex=[];
var handleMaterialList = function() {
	"use strict";

	if($("#projId").val()==""){
    	materialdata.projId=-1;	
    }else{
    	materialdata.projId=$("#projId").val();
    }
	  
	if ($('#materialListTablePop').length !== 0) {
		rowIndex=[];
	    materialListTablePop= $('#materialListTablePop').on( 'init.dt',function(){
	    	//默认选中第一行
    		$(this).bindDTSelected(trCmSelectedBack,false);
	    	//隐藏遮罩
	    	$("#materialListTablePop").hideMask();
	    	//搜索
    		$('#materialListTablePop_filter input').attr('placeholder','设备材料汇总表'); 
    		searchData();
	    	//console.info(".....");
	    	//console.info(rowIndex);
	        for(var m=0;m<rowIndex.length;m++){
	        	materialListTablePop.row(rowIndex[m]).nodes().to$().addClass("selected");
	        }
	    	
	    }).DataTable({
	    	language: language_CN,
	        lengthMenu: [ 18 ],
	        dom: 'fBrtip',
	        buttons: [
                
	           
	        ],
	        serverSide: true, 
			ajax: {
			    url: 'changeRecord/queryMaterialList',
			    contenttype:"application/json;charset=utf-8",
			    data:function(d){
			        $.each(materialdata,function(i,k){
			            d[i] = k;
			        }); 	
			    },
			    datasrc: 'data'
			},
			select: true,  //支持多选
	        columns: [
	            {"data":"materialId", className:"none never"},
	  			{"data":"materialName"},
	  			{"data":"materialSpec"},
				{"data":"materialUnit"},
				{"data":"materialNum", "className": "text-right"}
				
			],
			order: [[ 0, "asc" ]],
			columnDefs: [{
				targets: 0,
				"visible":false,
				render:function(data,type,row,meta){
					//console.info(rowData);
					for(var i=0;i<rowData.length;i++){
						
						if(data==rowData[i].materialId){
							rowIndex.push(meta.row);
						}
					}
					return data;
				}
			}
			],
			responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            }
	    });
	}
};

function trCmSelectedBack(){
	
}
/**
 * 材料变更(下)
 */
var handleMChangeList = function() {
	"use strict";	   
	    if($("#cmId").val()==""){
	    	mcData.cmId="-1";
	    }else{
	    	 mcData.mcType=$("#mcType").val();	
	 	     mcData.cmId=$("#cmId").val();
	    }
	if ($('#materialListTable').length !== 0) {
		if (!$.fn.DataTable.isDataTable('#materialListTable')) {
	        materialListTable= $('#materialListTable').on( 'init.dt',function(){
		    	//默认选中第一行
	    		$(this).bindDTSelected("",false);
	    		rowData=materialListTable.data();
	    		//console.info("-++++---");
	    		//console.info(rowData);
		    	//隐藏遮罩
		    	$("#materialListTable").hideMask();
		    	saveMaterialList();
		    	importMaterial();
		    	checkOut();
		    	btnHidden("mcBtn");
		        mcSelBtn();
		        $(this).bindInputsChange();
		    }).DataTable({
		    	language: language_CN,
		        lengthMenu: [ 18 ],
		        dom: 'Brtip',
		        buttons: [
		           /* { text: '下载模板', className: 'btn-sm btn-query checkOut'},
		            { text: '导入', className: 'btn-sm btn-query business-tool-btn materialImportBtn mcBtn' },
	                { text: '调整', className: 'btn-sm btn-query mcSelBtn mcBtn' },
		            { text: '保存', className: 'btn-sm btn-query mcSaveBtn mcBtn'}*/
		        ],
		        //serverSide: true, 
				ajax: {
				    url: 'changeRecord/queryMCList',
				    contenttype:"application/json;charset=utf-8",
				    data:function(d){
				        $.each(mcData,function(i,k){
				            d[i] = k;
				        }); 	
				    },
				    datasrc: ''
				},
		        columns: [
		            {"data":"mcId", className:"none never"},
		  			{"data":"materialName",responsivePriority: 1},
		  			{"data":"materialSpec",responsivePriority: 4},
					{"data":"materialUnit",responsivePriority: 3},
					{"data":"adjustQuantity", "className": "text-right",responsivePriority: 2}
				],
				order: [[ 0, "asc" ]],
				columnDefs: [{
					targets: 0,
					"visible":false
				}/*,{
					targets: 4,
					render:function(data,type,row,meta){
						if(type === "display"){
							var tdcon = '<div class="tdInnerInput">';
						        tdcon += '<input class="form-control input-sm text-right" data-parsley-type="number" value=' + data + '>';						 					    
						        tdcon += '</div>';
						        return tdcon;
						}else{
							return data;
						}
					}
				}*/],
				responsive: {
	            	details: {
	            		renderer: function ( api, rowIdx, columns ){
	            			return renderChild(api, rowIdx, columns);
	            		}
	            	}
	            }
		    }).on("draw.dt",function(){
		    	$("#materialListForm").toggleEditState(false).styleFit();
		    });
		}else{
			materialListTable.ajax.reload();
		}
		$('#materialListTable').on("draw.dt", function(){
			rowData=materialListTable.data();
		});
	}
};
/*function trMCSelectedBack(){
	rowData=materialListTable.ajax.json().data;
	console.info("-++++---");
	console.info(rowData);
	//trSData.materialListTable.jsons;
}*/

var checkOut = function(){
	$(".checkOut").on("click",function(){
		console.info("xiazai");
		$("#exportExcel").submit();
	})
}

var saveMaterialList = function(){	
	$(".mcSaveBtn").on("click",function(){
		var t = $('#materialListTable');
		if(t.checkInputs()){
			var data = t.getInputsData();
			if(data.length || materialData != null){
				resultData=[];
				for(var i=0;i<data.length;i++){
		       		var datam = data[i];
					if(datam.adjustQuantity!==""){
						datam.cmId=$("#cmId").val();
						datam.mcType=$("#mcType").val();
		   				resultData.push(datam);
		   			}
				  }
				if(materialData != null){
					for(var i=0;i<materialData.result.materialList.length;i++){
						materialData.result.materialList[i].cmId=$("#cmId").val();
						resultData.push(materialData.result.materialList[i]);
					}
				}
	       		$.ajax({
	       			type: 'POST',
	       			url: 'changeRecord/saveMaterialChange',
	       			contentType: "application/json;charset=UTF-8",
	       			data: JSON.stringify(resultData),
	       			success: function (data) {
	       				var content = "数据保存成功！";
	       				if(data === "false"){
	       					content = "数据保存失败！";
	       				}else if(data === "true"){
	       					$("#materialListTable").cgetData(false);  //列表重新加载	            
	       				}
	       				var myoptions = {
	                       	title: "提示信息",
	                       	content: content,
	                       	accept: popClose,
	                       	chide: true,
	                       	icon: "fa-check-circle"
	                   }
	                   $("body").cgetPopup(myoptions);
	               },
	               error: function (jqXHR, textStatus, errorThrown) {
	                   console.warn("材料清单保存失败！");
	               }
	            });
			}else{
				alertInfo("无更新记录！");
			}
		}else{
			console.info("表单校验失败，请检查并修改未通过项目！");
		}
	});
}

function hiddenOpt(state){
	state="0";
   if(state==1)	{
	    $(".confirmBtn").addClass("hidden");	
	    //$(".confirmBtn1").addClass("hidden");	
		$(".editbtn").addClass("hidden");	
   }else{
	    $(".confirmBtn").removeClass("hidden");
	    $(".confirmBtn1").removeClass("hidden");
		$(".editbtn").removeClass("hidden");	
   }
  
}



function trSelectedBack(v, i, index, t, json){
	$("input[name='auditResult']:eq(0)").attr("checked",'checked');   //默认为通过
	$("#cmId1").val(json.cmId);
	materialData = null;
	if(json.evId!==undefined){//签证
		$("#mcType").val("1");
		$("#cmId").val(json.evId);	
		$(".auditResult").removeClass("hidden");
		if(json.auditState=="5"){
			$(".voidBtn,.confirmBtn1,.saveBudgetBtn").removeClass("hidden");
		}else{
			$(".voidBtn,.confirmBtn1,.saveBudgetBtn").addClass("hidden");
		}
	}else{//变更
		//console.info("id===="+json.cmId);
		$(".auditResult").addClass("hidden");
		$("#mcType").val("0");	
		$("#cmId").val(json.cmId);
		hiddenOpt(json.cmState);
		state=json.cmState;
		
		if(json.designChangeType=="3"){
			$(".confirmBtn").removeClass("hidden");
			$(".toolBtn").removeClass("hidden");
		}else{
			$(".confirmBtn").addClass("hidden");
			$(".toolBtn").addClass("hidden");
		}
	}
	if($('#budgetSumForm input:radio[name="auditResult"]:checked').val()=="1"){
		//是
		 $(".auditOpinion").addClass("hidden");
	}else{
		$(".auditOpinion").removeClass("hidden");
	}
	
	
	$("#cmId").addClass("accBusRecordId");
	$("#projId").val(json.projId);
	$("#projId1").val(json.projId);
	if($("#materialChangeTab").parent().hasClass("active")){
		 mcData.projId=$("#projId").val();
		 mcData.mcType=$("#mcType").val();
		 mcData.cmId=$("#cmId").val();
		 $("#materialListTable").cgetData(false,mcBack("mcBtn"));
	}else if($("#budgetAdjustTab").parent().hasClass("active")){
		//$('.saveBudgetBtn').removeClass("hidden");
		trSData.t.cgetDetail('budgetSumForm','changeRecord/queryBudge?projId='+json.projId+'&cmId='+$("#cmId").val()+'&mcType='+$("#mcType").val(),'',budgetCallback);//查详述
		
		histSearchData.projId = $("#projId").val(); 
		var len =  "";
		if($("#changeRecordTab").parent().hasClass("active")){
			len=$("#changeRecordTable").find("tr.selected").length;
			console.info("len1-"+len);
		}else{
			len=$("#visaRecordTable").find("tr.selected").length;
			console.info("len2-"+len);
		}
		
		if(len>0){
			histSearchData.businessOrderId=$("#cmId").val();
		}else{
			histSearchData.businessOrderId="-1";
		}
		if($.fn.DataTable.isDataTable('#auditHistoryTable')){
			//初始化过
			$("#auditHistoryTable").cgetData(false,function(){
			
			});
		}else{
			handleAuditHistory();
		}
	}else if($("#suppContTab").parent().hasClass("active")){
		$("#agreementDetailform").formReset();
		trSData.t.cgetDetail('agreementDetailform','changeRecord/viewContract?projId='+json.projId+'&cmId='+$("#cmId").val()+'&mcType='+$("#mcType").val(),'',getDetailADBack); 
	}else if($("#alterationInfo").parent().hasClass("active")){
		$("#agreementDetailform").formReset();
		$("#engineeringForm").formReset();
		if($("#mcType").val()=='0'){
			//变更
			if($("#changeType").val()=="2"){
				//用户变更
				$(".userChange").removeClass("hidden");
				$(".conChange").addClass("hidden");
			}else{
				$(".userChange").addClass("hidden");
				$(".conChange").removeClass("hidden");
			}
			
			$("#designAlterationForm").removeClass("hidden");
			$("#engineeringForm").addClass("hidden");
			trSData.changeRecordTable.t && trSData.changeRecordTable.t.cgetDetail('designAlterationForm', 'designAlteration/viewChangeManagement?menuDes='+$("#menuDes").val(), '',rollback); 
		}else{
			$("#designAlterationForm").addClass("hidden");
			$("#engineeringForm").removeClass("hidden");
			trSData.visaRecordTable.t && trSData.visaRecordTable.t.cgetDetail('engineeringForm', 'engineering/viewEngineeringVisa?menuDes='+$("#menuDes1").val(), '',rollback2); 
		}
	}else if($("#quantitiesTab").parent().hasClass("active")){
		trSData.t.cgetDetail('budgetSumForm','changeRecord/queryBudge?projId='+json.projId+'&cmId='+$("#cmId").val()+'&mcType='+$("#mcType").val(),'',function(){
			if($("#budgetId").val()==''){
				$("#budgetId").val(-1);
				quantitiesData.projId="-1";
			}else{
				quantitiesData.budgetId=$("#budgetId").val();
				quantitiesData.projId=json.projId;
				quantitiesData.cmId=$("#cmId").val();
			}
			
			quantitiesData.costType=$("#costType1").val();
			quantitiestable.ajax.reload(btnHidden("inportBtn"));
		});
		
	}
	
	//hiddenOpt();
}
/**切换到变更记录*/
$("#changeRecordTab").on("shown.bs.tab",function(){
   $('.addClear').val('');
   $("#mcType").val('0')
  $("#changeRecordTable").cgetData(true);
  $('#designAlterationForm').removeClass("hidden");
  $('#engineeringForm').addClass("hidden");
   $('#budgetAdjustTab').tab("show");
   $('#budgetSumForm').formReset();
   $('#budgetSumForm').toggleEditState(true);
   hiddenOpt("0");
   $("#changeRecordTable").attr("data-attach-table","all");
   //collectionTable用于采集获取tableID
   $("#changeRecordTable").addClass("collectionTable");
   $("#visaRecordTable").removeAttr("data-attach-table");
   $("#visaRecordTable").removeClass("collectionTable");
   $(".auditResult").addClass("hidden");
   	histSearchData.projId = $("#projId").val();
	 if($("#cmId").val()==''){
		 histSearchData.businessOrderId='-1';
	 }else{
		 histSearchData.businessOrderId=$("#cmId").val();
	 }
	if($.fn.DataTable.isDataTable('#auditHistoryTable')){
		//初始化过
		$("#auditHistoryTable").cgetData(false,function(){
		
		});
	}else{
		handleAuditHistory();
	}
});
/**切换到签证记录*/
$("#visaRecordTab").on("shown.bs.tab",function(){
	$('.addClear').val('');
	$("#mcType").val('1')
	if(!$.fn.DataTable.isDataTable('#visaRecordTable')){
	    handleVisaRecord();
	}else{
		$("#visaRecordTable").cgetData(true,'',true);
	}
	$('#designAlterationForm').addClass("hidden");
    $('#engineeringForm').removeClass("hidden");
	$('#budgetAdjustTab').tab("show");
	$('#budgetSumForm').formReset();
	$('#budgetSumForm').toggleEditState(true);
	 $("#changeRecordTable").removeAttr("data-attach-table");
	 $(".auditResult").removeClass("hidden");
	//collectionTable用于采集获取tableID
	 $("#changeRecordTable").removeClass("collectionTable");
	 $("#visaRecordTable").attr("data-attach-table","all");
	 $("#visaRecordTable").addClass("collectionTable");
	 histSearchData={};
	 histSearchData.projId = $("#projId").val();
	 if($("#cmId").val()==''){
		 histSearchData.businessOrderId='-1';
	 }else{
		 histSearchData.businessOrderId=$("#cmId").val();
	 }
	 console.info("查询历史1---");
		console.info(histSearchData);
		if($.fn.DataTable.isDataTable('#auditHistoryTable')){
			console.info("查询历史2---");
			console.info(histSearchData);
			//初始化过
			$("#auditHistoryTable").cgetData(false,function(){
			
			});
		}else{
			handleAuditHistory();
		}
});
/**切换到记录详情*/
$("#alterationInfo").on("shown.bs.tab",function(){
	$("#agreementDetailform").formReset();
	$("#engineeringForm").formReset();
	if($("#mcType").val()=='0'){//变更
		/*$("#designAlterationForm").removeClass("hidden");
		$("#engineeringForm").addClass("hidden");*/
		//变更
		if($("#changeType").val()=="2"){
			//用户变更
			$(".userChange").removeClass("hidden");
			$(".conChange").addClass("hidden");
		}else{
			$(".userChange").addClass("hidden");
			$(".conChange").removeClass("hidden");
		}
		trSData.changeRecordTable.t && trSData.changeRecordTable.t.cgetDetail('designAlterationForm', 'designAlteration/viewChangeManagement?menuDes='+$("#menuDes").val(), '',rollback); 
	}else{//签证
		/*$("#designAlterationForm").addClass("hidden");
		$("#engineeringForm").removeClass("hidden");*/
		trSData.visaRecordTable.t && trSData.visaRecordTable.t.cgetDetail('engineeringForm', 'engineering/viewEngineeringVisa?menuDes='+$("#menuDes1").val(), '',rollback2); 
	}
});
/**预算总表*/
$("#budgetAdjustTab").on("shown.bs.tab",function(){
	trSData.t&&trSData.t.cgetDetail('budgetSumForm','changeRecord/queryBudge?projId='+$("#projId").val()+'&cmId='+$("#cmId").val()+'&mcType='+$("#mcType").val(),'',budgetCallback);//查详述
	histSearchData.projId = $("#projId").val(); 
	histSearchData.businessOrderId=$("#cmId").val();
	if($.fn.DataTable.isDataTable('#auditHistoryTable')){
		//初始化过
		$("#auditHistoryTable").cgetData(false,function(){
		
		});
	}else{
		handleAuditHistory();
	}
});

/**切换到材料变更*/
$("#materialChangeTab").on("shown.bs.tab",function(){
	if((jsonLength(trSData.changeRecordTable) && $("#changeRecordTable").is(":visible")) || (jsonLength(trSData.visaRecordTable) && $("#visaRecordTable").is(":visible"))){
		if(!$.fn.DataTable.isDataTable('#materialListTable')){
			handleMChangeList();
		}else{
			 mcData.mcType=$("#mcType").val();
			 mcData.cmId=$("#cmId").val();
			$("#materialListTable").cgetData(false,mcBack("mcBtn"));
		}
	}else{
		
		 mcData.mcType="-1";	
 	     mcData.cmId="-1";	 
 	     state=1;
 	     handleMChangeList();
	}
});

/**切换到补充协议*/
$("#suppContTab").on("shown.bs.tab",function(){
	//console.info("Oooooo=="+trSData.t);
	if((jsonLength(trSData.changeRecordTable) && $("#changeRecordTable").is(":visible")) || (jsonLength(trSData.visaRecordTable) && $("#visaRecordTable").is(":visible"))){
		$("#agreementDetailform").formReset();
		trSData.t.cgetDetail('agreementDetailform','changeRecord/viewContract?projId='+$("#projId").val()+'&cmId='+$("#cmId").val()+'&mcType='+$("#mcType").val(),'',getDetailADBack); 
	}else{
	  $("#agreementDetailform").formReset();
   	  $(".editbtn1").addClass("hidden");
   	  $("#agreementDetailform").toggleEditState(false);
	}
	});
/**切换到工程量表*/
$("#quantitiesTab").off("shown.bs.tab").on("shown.bs.tab",function(){
	if((jsonLength(trSData.changeRecordTable) && $("#changeRecordTable").is(":visible")) || (jsonLength(trSData.visaRecordTable) && $("#visaRecordTable").is(":visible"))){
	  $("#costType1").val($("#costTypeSelect select").val());
	  //quantitiesData.costType=$("#costType1").val();
	    quantitiesData.mcType=$("#mcType").val();
	    quantitiesData.cmId=$("#cmId").val();
	    if($("#budgetId").val()==''||$("#budgetId").val()==-1){
	    	trSData.t && trSData.t.cgetDetail('budgetSumForm','changeRecord/queryBudge?projId='+$("#projId").val()+'&cmId='+$("#cmId").val()+'&mcType='+$("#mcType").val(),'',function(data){
	   		  $("#budgetId").val(data.budgetId);
	   		  if($("#budgetId").val()==''||$("#budgetId").val()==null){
	   			  $("#budgetId").val(-1);
	   		  }
	   		  quantitiesData.budgetId=$("#budgetId").val();
	   		  quantitiesData.projId=$("#projId").val();
	   		  quantitiesData.cmId=$("#cmId").val();
	   		  quantitiesTabInit();
	   		});
	    	//alert($("#projId").val());
	    }else{
	    	 quantitiesData.budgetId=$("#budgetId").val();
	   		 quantitiesTabInit();
	    }
	   
	  
	
	}else{
		$("#projId1").val(-1);
		$("#costTypeSelect select").hide();
		 state=1;
		 quantitiesTabInit();
		 
	}
	 
});
getDetailADBack = function(){
	if(state!=undefined&&state==1){
		 $(".editbtn1").addClass("hidden");
		$("#agreementDetailform").toggleEditState(false).styleFit();
	}else{
		 $(".editbtn1").removeClass("hidden");
		$("#agreementDetailform").toggleEditState(true).styleFit();
	}
	if(!$("#conNo").val()){
		 $("body").cgetPopup({
            	title: "提示信息",
            	content: "该工程还未签合同",
            	accept: popClose,
            	chide: true,
            	icon: "fa-exclamation-circle",
            });
	}
}
//变更确认事件
function confirmBtn(){
	$(".confirmBtn").off("click").on("click",function(){

        var dataJson=$('#budgetSumForm').serializeJson();
        if(dataJson.budgetTotalCost =='' || dataJson.budgetTotalCost == null){
            alertInfo("请先填写调整预算金额！");
            return;
        }

		if($("#changeRecordTable").find("tr.selected").length>0){
			$("body").cgetPopup({
		       	title: "提示信息",
		       	content: "是否确认完成预算调整？",
		       	accept: saveBudgetAdjust,
		       	chide: false,
		       	icon: "fa-check-circle",
		     });
		}else{
			alertInfo("请选择要确认的变更记录！");
		}
	});
	
}

function reassignmentBtn() {
	$(".reassignmentBtn").off("click").on("click",function(){
		var data= trSData.changeRecordTable.json;
		if (data===undefined){ alertInfo("请选择要改派的数据!"); return false; }

		var tipsHtml="请选择预算员";
		$('body').cgetPopup({
			title: tipsHtml,
			content: '#changeRecord/budgetPop?projId='+data.projId,
			size: 'large',
			accept:confirmDone
		});

	})
}

var confirmDone=function(){
	var staffId = $("#staffId").val();
	var data= trSData.changeRecordTable.json;
	Base.subimt("changeRecord/reassignment","POST",{projId:data.projId,staffId:staffId},function (data) { alertInfo(data); });
	$("#changeRecordTable").cgetData(true);
};




//签证确认事件
function confirmBtn1(){
	$(".confirmBtn1").off("click").on("click",function(){
		if($("#visaRecordTable").find("tr.selected").length>0){
			$("body").cgetPopup({
		       	title: "提示信息",
		       	content: "是否确认完成预算调整？",
		       	accept: saveBudgetAdjust1,
		       	chide: false,
		       	icon: "fa-check-circle",
		    });
		}else{
			alertInfo("请选择要确认的签证！");
		}
	})	
}


//签证确认回调
var saveBudgetAdjust1 = function(){
	
	var type="cm";
	var id="";
	var type=$("#mcType").val();
	var id=$("#cmId").val();
	var dataJson="";
	if($('#budgetSumForm')){
		dataJson=$('#budgetSumForm').serializeJson();
	}
	dataJson.mcType = type;
	dataJson.cmId = id;
	dataJson.menuId= menuId;
	if(dataJson.budgetTotalCost =='' || dataJson.budgetTotalCost == null){
		alertInfo("请先填写预算金额！");
		return;
	}
	   var response = $.ajax({
           url: "changeRecord/updateChangeState?type="+type+"&id="+id,
           type: "POST",
           dataType:'json',
           timeout : 5000,
           contentType: "application/json;charset=UTF-8",
           data: JSON.stringify(dataJson),
           success: function (data) {
               if (data.ret_message === "true") {
                  
                       //table.cgetData();        
                       $("body").cgetPopup({
                       	title: "提示信息",
                       	content: "数据保存成功!",
                       	accept: saveChangeRecord,
                       	chide: true,
                       	icon: "fa-check-circle",
                       	newpop: 'new'
                       });
                   
               } else if(data.ret_message==="false") {
                  
                       $("body").cgetPopup({
                       	title: "提示信息",
                       	content: "数据保存失败, 请重试! <br>" + data,
                       	accept: popClose,
                       	chide: true,
                       	icon: "fa-exclamation-circle",
	                        newpop: 'new'
                       });
                   
               }else if(data.ret_message==="pass"){
            	   var content='';
            	   if(type=='1'){
            		   content = "此类签证金额较少无需上级领导审核，已通过！";
            	   }
            	   $("body").cgetPopup({
                      	title: "提示信息",
                      	content: content,
                      	accept: saveChangeRecord,
                      	chide: true,
                      	icon: "fa-exclamation-circle",
	                        newpop: 'new'
                      });
               }else{//接口异常
            	   $("body").cgetPopup({
                     	title: "提示信息",
                     	content: data.ret_message,
                     	accept: popClose,
                     	chide: true,
                     	icon: "fa-exclamation-circle",
	                        newpop: 'new'
                     });
               }
     
           },
           error: function (jqXHR, textStatus, errorThrown) {
               //判断超时
               if(textStatus === 'timeout'){
                   response.abort();
                   callback(textStatus);
               }
               printXHRError("cformSave", "表单(" + t.attr("id") + ") ajax保存查询失败", jqXHR, textStatus, errorThrown);
           }
       });
}
//
var saveBudgetAdjust=function(){
	var cmId=$("#cmId").val();
   	$.ajax({
       type: 'post',
       url: 'changeRecord/updateChange',
       contentType: "application/json;charset=UTF-8",
       data: cmId,
       success: function (data) {
       	var content = "推送成功";
       	if(data === "false"){
       		content = "推送失败！";
       	}else{
       		$("#changeRecordTable").cgetData(true);  //列表重新加载
       	}
       	var myoptions = {
               	title: "提示信息",
               	content: content,
               	accept: popClose,
               	chide: true,
               	newpop:'new',
               	icon: "fa-check-circle"
           }
           $("body").cgetPopup(myoptions);
       },
       error: function (jqXHR, textStatus, errorThrown) {
           console.warn("推送失败！");
       }
   });
	/*var type="cm";
	var id="";
	    type=$("#mcType").val();
	      id=$("#cmId").val();
	      var dataJson="";
	  	if($('#budgetSumForm')){
	  		dataJson=$('#budgetSumForm').serializeJson();
	  	}
	  	dataJson.mcType = type;
	  	dataJson.cmId = id;
	   var response = $.ajax({
           url: "changeRecord/updateChangeState",
           type: "POST",
           timeout : 5000,
           contentType: "application/json;charset=UTF-8",
           data: JSON.stringify(dataJson),
           success: function (data) {
               if (data === "true") {
                  
                       //table.cgetData();        
                       $("body").cgetPopup({
                       	title: "提示信息",
                       	content: "数据保存成功!",
                       	accept: saveChangeRecord,
                       	chide: true,
                       	icon: "fa-check-circle",
                       	newpop: 'new'
                       });
                   
               } else {
                  
                       $("body").cgetPopup({
                       	title: "提示信息",
                       	content: "数据保存失败, 请重试! <br>" + data,
                       	accept: popClose,
                       	chide: true,
                       	icon: "fa-exclamation-circle",
	                        newpop: 'new'
                       });
                   
               }
     
           },
           error: function (jqXHR, textStatus, errorThrown) {
               //判断超时
               if(textStatus === 'timeout'){
                   response.abort();
                   callback(textStatus);
               }
               printXHRError("cformSave", "表单(" + t.attr("id") + ") ajax保存查询失败", jqXHR, textStatus, errorThrown);
           }
       });*/
}

function saveChangeRecord(table){
	if($("#visaRecordTab").parent().hasClass("active")){
		  //visaData.evState=0;
		$("#visaRecordTable").cgetData(true,visaBack,true);
	}else{
		  //changeData.cmState=0;
		$("#changeRecordTable").cgetData(true,changeBack);
	}
	 
}
function mcBack(cla){
	btnHidden(cla);
	//console.info("===---Back---");	
	rowData=materialListTable.data();
	//console.info(rowData);
	
}
function btnHidden(cla){
		 if(state==1){
			 //console.info("cla==="+cla);
	        	$("."+cla).addClass("hidden");
	        }else{
	        	 //console.info("clah==="+cla);
	        	$("."+cla).removeClass("hidden");
	  }
	
}

function mcSelBtn(){
$(".mcSelBtn").on("click",function(){
		materialdata={};
		var url = "#changeRecord/materialList";
		$("body").cgetPopup({
			title: '材料列表',
			content: url,
			accept: mcDone,
			size:"large"
		});
	
});
}

function mcDone(){
	var jsons=trSData.materialListTablePop.jsons;
	//console.info("--------------");
	//console.info(rowData);
    if(jsons!=undefined){
		for(var i=0;i<rowData.length;i++){
			for(var j=0;j<jsons.length;j++){
				if(jsons[j].materialId == rowData[i].materialId){
					jsons.splice(j,1);
					break;
				}
			}
		}

		for(var i=0;i<jsons.length;i++){
			jsons[i].mcType=$("#mcType").val();
			jsons[i].cmId=$("#cmId").val();
			jsons[i].mcId="";
			jsons[i].adjustQuantity='';	
		}
		rowData=rowData.concat(jsons);
		materialListTable.rows.add(jsons).draw();
    }
}
function queryBtn(){
//查询按钮弹出屏查询
$(".queryBtn").on("click",function(){
		var url = "#changeRecord/queryChangeData";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchChangeDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
});
}
function queryBtn1(){
//查询按钮弹出屏查询
$(".queryBtn1").on("click",function(){
		var url = "#changeRecord/queryVisaData";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchVisaDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
});
}
function searchVisaDone(){
	visaData = $("#searchForm_visa").serializeJson();
	var visaDate = $("#visaRecordTable_filter input").val();
	visaData.visaDate = visaDate;
	visaData.menuId=menuId;
	$("#visaRecordTable").cgetData(true,visaBack,false);  //列表重新加载
}
function searchChangeDone(){
	changeData = $("#searchForm_change").serializeJson();
	var cmNo = $("#changeRecordTable_filter input").val();
	changeData.cmNo = cmNo;
	changeData.menuId=menuId;
	$("#changeRecordTable").cgetData(true,changeBack);  //列表重新加载
}
//变更记录查询回调函数
function changeBack(){
	//console.info("99===");
	if(!trSData.changeRecordTable.t){
		//console.info("11===");
		$(".confirmBtn").addClass("hidden");		
		noData();
	}else{
	  $(".toolBtn").addClass("hidden");
	}
}
function noData(){
	if($("#budgetAdjustTab").parent().hasClass("active")){
		$("#budgetSumForm").formReset();
		$(".editbtn").addClass("hidden");
		$("#budgetSumForm").toggleEditState(false);
      }else if($("#materialChangeTab").parent().hasClass("active")){
    	  mcData.mcType="-1";	
  	      mcData.cmId="-1";	 
  	      $(".mcBtn").addClass("hidden");
  	      materialListTable.ajax.reload(); 
      }else if($("#suppContTab").parent().hasClass("active")){
    	  $("#agreementDetailform").formReset();
    	  $(".editbtn1").addClass("hidden");
    	  $("#agreementDetailform").toggleEditState(false);
      }else if($("#quantitiesTab").parent().hasClass("active")){
    	  quantitiesData.budgetId=-1;			
	      quantitiestable.ajax.reload()
    	  $(".inportBtn").addClass("hidden");
    	   
      }
}

//材料导入
var importMaterial=function(){
	$(".materialImportBtn").off("click").on("click",function(){
		$("body").cgetPopup({
			title: '文件导入',
			content: "#officialDrawing/importPop?url=changeRecord/importExcel",
			accept: importM,
			nocache: true
		});
	});
}

/*function saveImportBack(data){
	materialListTable.rows.add(data.result.materialList).draw();
	if(data.result.materialList !=null){
		materialData = data;
	}
}*/

var importM = function(){
	
}

function visaBack(){
	if(!trSData.visaRecordTable.t){
		$(".confirmBtn1").addClass("hidden");		
		noData();
      }
}
var rollback = function(data){
 	$("#stepId").val(getStepId());
    //$("#alPath").val($("#projNo").val()+"/"+$(".has-sub.active > a span").text());
 	$("#alPath").val($("#projNo").val()+"/过程");
    $(".searchButton").attr("href","/accessoryCollect/openChangeFile?id="+$("#cmId").val());
    if($('#changeType').val()=="2"){
    	$(".cust_change").removeClass("hidden");
    	$(".proj_change").addClass("hidden");
    	$(".searchButton").removeClass("hidden");
    	$(".Search_Button").addClass("hidden");
    }else{
    	$(".cust_change").addClass("hidden");
    	$(".proj_change").removeClass("hidden");
    	$(".Search_Button").removeClass("hidden");
    	$(".searchButton").addClass("hidden");
    }
    if($("#auditState").val()==='1'){
    	$(".duInfo").removeClass("hidden");
    }else{
    	$(".duInfo").addClass("hidden");
    }
    
if(data.drawName){
	$(".hasVal").removeClass("hidden");
	$(".noVal").addClass("hidden");
	$(".noVal+#filePreviews tr").remove();
	$("#Search_Button_cm").removeClass("disabled");
	$(".searchButton").removeClass("disabled");
}else{
	$(".hasVal").addClass("hidden");
	$(".noVal").removeClass("hidden");
	}
$(".Search_Button").removeClass("disabled");
if($("#changeType").val()=="2"){
	//用户类型
	$(".cust_change").removeClass('hidden');
	$(".proj_change").addClass('hidden');
	$('.CnChange').addClass('hidden');
	$('.CuChange').removeClass('hidden');
}else{
	$(".cust_change").addClass('hidden');
	$(".proj_change").removeClass('hidden');
	$('.CuChange').addClass('hidden');
	$('.CnChange').removeClass('hidden');
}
$("#designAlterationForm").styleFit();
//查询附件列表
$("#fileupload .projId").val(data.projId);
$("#fileupload .projNo").val(data.projNo);
$('#busRecordId').val($('#cmId').val());
accessoryData.busRecordId = $("#cmId").val()==''||$("#cmId").val()==undefined?"-1":$("#cmId").val();
if($.fn.DataTable.isDataTable('#dataPopTableSecond')){
	//初始化过
	$("#dataPopTableSecond").cgetData(false,function(){
	
	});
}else{
	seconddatainit1();
}
}
//详述回调
var rollback2=function(data){
	$("#custAuditDate").val(format(data.custAuditDate));
	$("#builderAuditDate").val(format(data.builderAuditDate));
	$("#suAuditDate").val(format(data.suAuditDate));

	if(data.flag=='1'){//审核被退回
		$('.backReason').removeClass("hidden");
	}else{
		$('.backReason').addClass("hidden");
	}
	if(data.drawName){
		$(".hasVal1").removeClass("hidden");
		$(".noVal+#filePreviews tr").remove();
		$("#Search_Button_Evg").removeClass("disabled");
		$("#Search_Button_Evg").attr("disabled",false);
	}else{
		$(".hasVal1").addClass("hidden");
	
	}
    $("#projectImagesList").getImagesList({
       "projId": data.projId,
       "step": "120206",
       "projNo": data.projNo,
       "busRecordId": data.evId || '-1'
   });
	$("#engineeringForm").styleFit();
	
	//查询附件列表
	 $("#fileupload .projId").val(data.projId);
     $("#fileupload .projNo").val(data.projNo);
	$('#busRecordId').val($('#cmId').val());
	accessoryData.busRecordId = $("#cmId").val()==''||$("#cmId").val()==undefined?"-1":$("#cmId").val();
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
    	var sourceType = $("#sourceType").val()==''||$("#sourceType").val()==undefined?"-1":$("#sourceType").val();
    	var sourceTypes = sourceType+",2";
    	accessoryData.sourceTypes=sourceTypes;
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
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 8, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
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
/**
 * js初始加载方法
 */
var changeRecord = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
	        	//初始化变更记录
	        	handleChangeRecord();
	        	$('#engineeringForm').addClass("hidden");
	        	$('#designAlterationForm').removeClass("hidden");
        	});
        }
    };
}();
//$(".attach-panel").initAttachOper({
//	collection: {
//		tableid:'changeRecordTable'
//	}
//});


var voidMonitor=function(){
	$(".voidBtn").off("click").on("click",function(){
		if($('#visaRecordTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '作废',
				content: '确定要作废签证？',
				accept: voidDone
			});
		}else{
			$('body').cgetPopup({
				title: '作废',
				content: '请选择要作废的签证记录！',
				accept: popClose
			});
		}
	})
}

//确定作废
var voidDone=function(){
	var type="cm";
	var id="";
	var type=$("#mcType").val();
	var id=$("#cmId").val();
	var dataJson="";
	if($('#budgetSumForm')){
		dataJson=$('#budgetSumForm').serializeJson();
	}
	dataJson.mcType = type;
	dataJson.cmId = id;
	   var response = $.ajax({
           url: "changeRecord/deleteEV?type="+type+"&id="+id,
           type: "POST",
           timeout : 5000,
           contentType: "application/json;charset=UTF-8",
           data: JSON.stringify(dataJson),
           success: function (data) {
               if (data === "true") {
                  
                       //table.cgetData();        
                       $("body").cgetPopup({
                       	title: "提示信息",
                       	content: "签证已作废!",
                       	accept: visaBackSure,
                       	chide: true,
                       	icon: "fa-check-circle",
                       	newpop: 'new'
                       });
                   
               } else if(data==="false") {
                  
                       $("body").cgetPopup({
                       	title: "提示信息",
                       	content: "数据保存失败, 请重试! <br>" + data,
                       	accept: popClose,
                       	chide: true,
                       	icon: "fa-exclamation-circle",
	                        newpop: 'new'
                       });
                   
               }
     
           },
           error: function (jqXHR, textStatus, errorThrown) {
               //判断超时
               if(textStatus === 'timeout'){
                   response.abort();
                   callback(textStatus);
               }
               printXHRError("cformSave", "表单(" + t.attr("id") + ") ajax保存查询失败", jqXHR, textStatus, errorThrown);
           }
       });
	//$("#visaRecordTable").cdeleteRow("evId","changeRecord/deleteEV",visaBack);
}

var visaBackSure=function(){
	$("#visaRecordTable").cgetData(true,"",true);
}


/**
 * 审核历史
 */
var handleAuditHistory = function() {
	"use strict";
	/*histSearchData.projId = getProjectInfo().projId; 
	histSearchData.businessOrderId=$("#evId").val();*/
	console.info("--查询条件--");
	console.info(histSearchData);
    if ($('#auditHistoryTable').length !== 0) {
    	$('#auditHistoryTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#auditHistoryTable').hideMask();
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'engineering/queryManageRecord',
                type:'post',
                data: function(d){
                   	$.each(histSearchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/design/json/delay-check-history.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
				{"data":"mrTime"},
				{"data":"mrResult"},
				{"data":"mrAopinion"},
				{"data":"mrCsr"}
			],
			columnDefs: [{
				"targets": 0,
				"render": function ( data, type, row, meta ) {
					if(type === "display"){
						return format(data,'all');
					}else{
						return data;
					}
				},
			},{
				"targets": 1,
				"render": function ( data, type, row, meta ) {
					if(data === "1"){
						return "通过";
					}else if(data === "0"){
						return "不通过";
					}else{
						return "";
					}
				},
			}]
        });
    }
};


