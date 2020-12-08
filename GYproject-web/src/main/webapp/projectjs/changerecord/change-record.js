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
handleChangeRecord = function() {
	"use strict";
    if ($('#changeRecordTable').length !== 0) {
    	changeData.cmState=0;
    	$('#changeRecordTable').on( 'init.dt',function(){
    		
    		//搜索
    		$('#changeRecordTable_filter input').attr('placeholder','变更编号');   
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//隐藏遮罩
   			$('#changeRecordTable').hideMask();    			
   			$("#budgetAdjustbox").cgetContent("changeRecord/viewPage");
   			$("#suppContbox").cgetContent("changeRecord/suppCont");
   			
   	    	//查询监听
   	    	searchMonitor();
   	    	confirmBtn();
   	    	queryBtn();
   	    	//changeBack();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'fBrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn queryBtn'},
                { text: '确认', className: 'btn-sm btn-query business-tool-btn confirmBtn' }      
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
	  			{"data":"cmDate", responsivePriority: 3}, 
	  			{"data":"projName", responsivePriority: 2},
	  			{"data":"cuReason", responsivePriority: 4}/*,
	  			{"data":"overdue", className:"none never"}*/
	  			
			],
			/*隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉）*/
			columnDefs: [{
		        "targets": 0, 
		        "visible":false
			},{
				"targets":3,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
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
searchMonitor=function(){
	//基础条件查询添加监听
	$('#changeRecordTable_filter input').on('change',function(){
		var cmNo = $('#changeRecordTable_filter input').val();
		changeData = {};
		changeData.cmNo = cmNo;
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
		visaData.evState=0;
    	$('#visaRecordTable').on('init.dt', function(){

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
   	    	confirmBtn();
   	    	queryBtn1();
   	    	visaBack();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
              { text: '查询', className: 'btn-sm btn-query business-tool-btn queryBtn1'},
              { text: '确认', className: 'btn-sm btn-query business-tool-btn confirmBtn1'}       
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
          		{"data":"visaDate", responsivePriority: 3},
    			{"data":"projNo", responsivePriority: 2},
    			{"data":"projName", responsivePriority: 1}/*,
    			{"data":"overdue", className:"none never"}*/
			],
			columnDefs: [{
				"targets": 0, 
		        "visible":false
			},{
				"targets":3,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 15, 	//截取多少字符（或汉字）
					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
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
		
		$('#visaRecordTable').cgetData(true,visaBack);  //列表重新加载
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
	    mcData.mcType=$("#mcType").val();	
	    mcData.cmId=$("#cmId").val();	  
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
		        dom: 'fBrtip',
		        buttons: [
		            { text: '下载模板', className: 'btn-sm btn-query checkOut'},
		            { text: '导入', className: 'btn-sm btn-query business-tool-btn materialImportBtn mcBtn' },
	                { text: '调整', className: 'btn-sm btn-query mcSelBtn mcBtn' },
		            { text: '保存', className: 'btn-sm btn-query mcSaveBtn mcBtn'}
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
				},{
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
				}],
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
        	});
        }
    };
}();
function hiddenOpt(state){
   if(state==1)	{
	    $(".confirmBtn").addClass("hidden");	
	    $(".confirmBtn1").addClass("hidden");	
		$(".editbtn").addClass("hidden");		
   }else{
	    $(".confirmBtn").removeClass("hidden");
	    $(".confirmBtn1").removeClass("hidden");
		$(".editbtn").removeClass("hidden");	
   }
}



function trSelectedBack(v, i, index, t, json){
	materialData = null;
	console.info("*******************");
	if(json.evId!==undefined){
		$("#mcType").val("1");
		$("#cmId").val(json.evId);		
		state=json.evState;
		if(state=="-1"){
			state=1;
		}
		hiddenOpt(state);
	}else{
		//console.info("id===="+json.cmId);
		$("#mcType").val("0");	
		$("#cmId").val(json.cmId);
		hiddenOpt(json.cmState);
		state=json.cmState;
	}
	$("#projId").val(json.projId);
	$("#projId1").val(json.projId);
	if($("#materialChangeTab").parent().hasClass("active")){
		 materialdata.projId=$("#projId").val();
		 mcData.mcType=$("#mcType").val();
		 mcData.cmId=$("#cmId").val();
		$("#materialListTable").cgetData(true,mcBack("mcBtn"));
	}else if($("#budgetAdjustTab").parent().hasClass("active")){
		trSData.t.cgetDetail('budgetSumForm','changeRecord/queryBudge?projId='+json.projId+'&cmId='+$("#cmId").val()+'&mcType='+$("#mcType").val(),'',budgetCallback);//查详述
	}else if($("#suppContTab").parent().hasClass("active")){
		$("#agreementDetailform").formReset();
		trSData.t.cgetDetail('agreementDetailform','changeRecord/viewContract?projId='+json.projId+'&cmId='+$("#cmId").val()+'&mcType='+$("#mcType").val(),'',getDetailADBack); 
	}else if($("#quantitiesTab").parent().hasClass("active")){
		trSData.t.cgetDetail('budgetSumForm','changeRecord/queryBudge?projId='+json.projId+'&cmId='+$("#cmId").val()+'&mcType='+$("#mcType").val(),'',function(){
			conslole.info('1..'+$("#budgetId").val()=='');
			if($("#budgetId").val()==''){
				$("#budgetId").val(-1);
			}
			quantitiesData.budgetId=$("#budgetId").val();
			quantitiesData.projId=json.projId;
			quantitiesData.costType=$("#costType1").val();
			quantitiestable.ajax.reload(btnHidden("inportBtn"));
		});
		
	}
	
	
	
}
/**切换到变更记录*/
$("#changeRecordTab").on("shown.bs.tab",function(){
  $("#changeRecordTable").cgetData();
});
/**切换到签证记录*/
$("#visaRecordTab").on("shown.bs.tab",function(){
	if(!$.fn.DataTable.isDataTable('#visaRecordTable')){
	    handleVisaRecord();
	}else{
		$("#visaRecordTable").cgetData();
	}
});
/**预算总表*/
$("#budgetAdjustTab").on("shown.bs.tab",function(){
	
	trSData.t&&trSData.t.cgetDetail('budgetSumForm','changeRecord/queryBudge?projId='+$("#projId").val()+'&cmId='+$("#cmId").val()+'&mcType='+$("#mcType").val(),'',budgetCallback);//查详述
	
	});

/**切换到材料变更*/
$("#materialChangeTab").on("shown.bs.tab",function(){
	if((jsonLength(trSData.changeRecordTable) && $("#changeRecordTable").is(":visible")) || (jsonLength(trSData.visaRecordTable) && $("#visaRecordTable").is(":visible"))){
		if(!$.fn.DataTable.isDataTable('#materialListTable')){
			handleMChangeList();
		}else{
			 mcData.mcType=$("#mcType").val();
			 mcData.cmId=$("#cmId").val();
			$("#materialListTable").cgetData(true,mcBack("mcBtn"));
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
	   		  quantitiesTabInit();
	   		});
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
function confirmBtn(){
$(".confirmBtn,.confirmBtn1").off("click").on("click",function(){
	var type="cm";
	var id="";
	    type=$("#mcType").val();
	      id=$("#cmId").val();
	   var response = $.ajax({
           url: "changeRecord/updateChangeState?type="+type+"&id="+id,
           type: "POST",
           timeout : 5000,
           contentType: "application/json;charset=UTF-8",
           data: "",
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
       });
});
}
function saveChangeRecord(table){
	if($("#visaRecordTab").parent().hasClass("active")){
		  visaData.evState=0;
		$("#visaRecordTable").cgetData(true,visaBack);
	}else{
		  changeData.cmState=0;
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
	$("#visaRecordTable").cgetData(true,visaBack);  //列表重新加载
}
function searchChangeDone(){
	changeData = $("#searchForm_change").serializeJson();
	var cmNo = $("#changeRecordTable_filter input").val();
	changeData.cmNo = cmNo;
	$("#changeRecordTable").cgetData(true,changeBack);  //列表重新加载
}
//变更记录查询回调函数
function changeBack(){
	//console.info("99===");
	if(!trSData.changeRecordTable.t){
		//console.info("11===");
		$(".confirmBtn").addClass("hidden");		
		noData();
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

function saveImportBack(data){
	materialListTable.rows.add(data.result.materialList).draw();
	if(data.result.materialList !=null){
		materialData = data;
	}
}

var importM = function(){
	
}

function visaBack(){
	if(!trSData.visaRecordTable.t){
		$(".confirmBtn1").addClass("hidden");		
		noData();
      }
}


//$(".attach-panel").initAttachOper({
//	collection: {
//		tableid:'changeRecordTable'
//	}
//});