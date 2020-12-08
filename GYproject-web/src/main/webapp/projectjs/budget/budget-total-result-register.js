/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var budgetRegisterTable,quantitiestable,materialListTable;
/**查询条件*/
var searchData={},quantitiesData={},rateData={},materialdata={};
var dataPopTable;
var accessoryTable;
var accessoryData={};
var handleBudgetResult = function() {
	"use strict";
    if ($('#budgetRegisterTable').length !== 0) {
    	budgetRegisterTable= $('#budgetRegisterTable').on( 'init.dt',function(){
   			//加载页面
   			$("#budget_register_panel_box").cgetContent("budgetResultRegister/viewPage");
   			//隐藏遮罩
   			$('#budgetRegisterTable').hideMask();
   			$("#budgetRegisterTable_filter input").attr("placeholder","工程编号");
   			$("#budgetRegisterTable_filter input").on("change",function(){
   				searchData={};
   				searchData.projNo=$("#budgetRegisterTable_filter input").val();
   				//budgetRegisterTable.ajax.reload(budgetBack);	
   			    $("#budgetRegisterTable").cgetData("",budgetBack);  
   			});
   		    //基础条件查询添加回车事件
   			$('#budgetRegisterTable_filter input').on('keyup', function(event) {
   		        if (event.keyCode == "13") {
   		        	$(this).change();
   		        }
   		    });
   			//点击行事件
   			$(this).bindDTSelected(trSelectedBack,true);
   	    	//绑定单击事件 弹出搜索框
   			searchPop();
   			//登记监听
   			registerMonitor();
   			//查询费率
   			//getRate();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '登记', className: 'btn-sm btn-query business-tool-btn registerBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'budgetResultRegister/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
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
            select: true,    //支持多选
            columns: [
                {"data":"projId",className:"none never"}, 
	  			{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"projStatusDes"},
				{"data":"workDayDto"},
				 {"data":"signBack",
					className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						console.info(cellData);
						if(cellData==$("#notThrough").val()){
							$(td).parent().children().css("background", "rgb(255, 219, 219)");
							//$(td).attr("id",row);
						}
					}
					},
				{"data":"overdue",className:"none never",},
				
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false	
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets": 5,
			    "visible":false	
			},{
				"targets":3,
				 "orderable":false
			},{
				"targets":4,
				"orderable":false,
				 "render":function(data,type,row,meta){
					 if(data!=null){
						 return data.haveDays;
					 }else{
						 return 0;
					 }
				 }
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
};

function budgetBack(){
	var len = $('#budgetRegisterTable').DataTable().ajax.json().data ? $('#budgetRegisterTable').DataTable().ajax.json().data.length : $('#budgetRegisterTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$("#budgetSumForm").formReset();
		$('.editbtn').addClass("hidden");
		
	 }else{
		 $("#projId1").val(-1);
		 $("#budgetSumForm").formReset();
		 $('.editbtn').addClass("hidden");
		
	 }
	/*if(!trSData.budgetRegisterTable.t){
		$("#projId1").val(-1);
		if($("#budgetSumTab").parent().hasClass("active")){
			$("#budgetSumTab").formReset();
			$(".editbtn").addClass("hidden");
		}else if($("#quantitiesTab").parent().hasClass("active")){
			 $(".inportBtn").addClass("hidden");
			 $("#costTypeSelect select").hide();
			  quantitiesData.projId='-1';
			  quantitiestable.ajax.reload();
		}else if($("#materialListTab").parent().hasClass("active")){
			 $(".mcSaveBtn").addClass("hidden");
			 materialdata.projId='-1';
			 materialListTable.ajax.reload();
		}
		$("#budget_register_panel_box").formReset();
	}else{
		 $('.editBtn').addClass("hidden");
		 $("#budget_register_panel_box").formReset();
	}*/
	
}
/**
 * 预算登记监听
 */
var registerMonitor = function(){
	$(".registerBtn").on("click",function(){
		var len=$("#budgetRegisterTable").find("tr.selected").length;
		if(len>0){
			$('#budgetSumForm').toggleEditState(true);
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo("请选中要预算的工程！");
		}
	});
};
/*$("#budgetSumTab").on("shown.bs.tab",function(){
	  if(!trSData.budgetRegisterTable.t){
		  $(".editbtn").addClass("hidden");
	  }else{
		  $(".editbtn").removeClass("hidden");
		  trSData.budgetRegisterTable.t.cgetDetail('budgetSumForm','budgetResultRegister/queryPro','');  
	  }
	 
	  
	});*/
function getRate(){
	 $.ajax({
         type: 'POST',
         url: "budgetResultRegister/queryRateById",
         data: {},
         dataType: 'json',
         success: function (data) {       	 
        	 rateData=data;  
       	  }
       }); 
}
//弹屏监听方法
var searchPop = function(){
	$(".searchBtn").on("click",function(){
		var url = "#budgetResultRegister/projectSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){	
	 searchData= $("#budgetSearchForm").serializeJson();
    $("#budgetRegisterTable").cgetData("",budgetBack);  //列表重新加载
}
/** 选中行时，查询详述*/
var trSelectedBack = function(v, i, index, t, json){
	if(json!=undefined){
		$("#projId").val(json.projId);
		$("#budgetId").val(json.budgetId);
		
	}
	/*if($("#budgetSumTab").parent().hasClass("active")){
		$(".editbtn").addClass("hidden");
		//$(".saveBtnBudget").addClass("hidden");
	}else if($("#quantitiesTab").parent().hasClass("active")){
		quantitiesData.costType=$("#costType1").val();		
		quantitiesTabInit();
	}else if($("#materialListTab").parent().hasClass("active")){
		 handleMaterialList();
	}*/

	$('#budgetSumForm') && $('#budgetSumForm').formReset();
	$('.editbtn').addClass('hidden');
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	trSData.budgetRegisterTable.t.cgetDetail('budgetSumForm','budgetResultRegister/queryPro','',queryBackView);
}
var queryBackView =function(data){
	console.info(data);
	console.info("yyyyyyyyyy==="+data.drawUrl);
//	 $("#stepId").val(getStepId());
	 $("#alPath").val($("#projNo").val()+"/预算");
	 $(".searchButton").attr("href","/accessoryCollect/openCoFile?id="+$("#budgetId").val());
     $(".searchButton").removeClass("hidden");
     $(".Search_Button").addClass("hidden");
	if(data.drawName){
		console.info(1111);
		$(".hasVal").removeClass("hidden");
		$(".noVal").addClass("hidden");
		$(".noVal+#filePreviews tr").remove();
	}else{
		console.info(22222);
		$(".hasVal").addClass("hidden");
		$(".noVal").removeClass("hidden");
	}
}
var budgetResultRegister = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){ 
        		handleBudgetResult();
        	})
        }
    };
}();

/**切换到工程量表*/
$("#quantitiesTab").on("shown.bs.tab",function(){
	$("#costType1").val($("#costTypeSelect select").val());
	quantitiesData.costType=$("#costType1").val();
	quantitiesTabInit();
	
});

var quantitiesTabInit = function() {
	"use strict";
	if($("#projId1").val()==""){
		$("#projId1").val(-1);
	}
	quantitiesData.projId=$("#projId1").val();
    if (!$.fn.DataTable.isDataTable('#quanlitiesTable')) {
    	quantitiestable= $('#quanlitiesTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#quanlitiesTable').hideMask();
   			inportBtn();
   			if($("#quanlitiesTable_wrapper #costTypeSelect").length < 1)$("#quanlitiesTable_wrapper").prepend($("#costTypeSelect").removeClass("hidden"));
   			$("#costTypeSelect select:visible").off().on("change",function(){
   				$("#costType1").val($(this).val());
   				quantitiesData.costType=$(this).val();
   				quantitiestable.ajax.reload();
   			});
   			if(trSData.budgetRegisterTable.t){
   			   $(".inportBtn").removeClass("hidden");
   			   $("#costTypeSelect select").show();
   			}else{
   			   $("#costTypeSelect select").hide();
   			   $(".inportBtn").addClass("hidden");
   			}
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                { text: '导入', className: 'btn-sm btn-query business-tool-btn inportBtn' }
               /* { text: '放弃', className: 'btn-sm btn-warn business-tool-btn cancleBtn' }*/
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'budgetResultRegister/queryBudgetTotalTable',
                type:'post',
                data: function(d){
                   	$.each(quantitiesData,function(i,k){
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
	  			{"data":"serialNo"}, 
				{"data":"subitemName"},
				{"data":"amount",className:"text-right"}
			],
			columnDefs: [{
			
			    }],
			 ordering:false
        });
    }else{
    	if(trSData.budgetRegisterTable.t){
			   $(".inportBtn").removeClass("hidden");
			   $("#costTypeSelect select").show();
			}else{
			   $("#costTypeSelect select").hide();
			   $(".inportBtn").addClass("hidden");
			}
    	quantitiestable.ajax.reload();}
};
/**
 * 表格计算
 */
var amountSum=function(){
	$(".numbers").on("change",function(){
		//当前表格值
		var qualities=$(this).val();
		//上一个td值
		var price=$(this).parents("td").prev("td").text();
		//计算值
		var amount=(parseFloat(qualities)*parseFloat(price)).toFixed(2);
		//赋值
		$(this).parents("tr").find(".amount").val(amount);
		var trObj = $("#budgetRegisterTable tr");
		var totalRow=0;
        $("#budgetRegisterTable tr td:nth-child(4)").each(function (){
             totalRow+=parseFloat($(this).val());
        });
		
	})
};

function inportBtn(){
	$(".inportBtn").off("click").on("click",function(){
	var popoptions = {
			title: '文件导入',
			content: "#budgetResultRegister/importPop?url=budgetResultRegister/importTotalExcel",
			accept: importOk,
			atext: '完成',
			adisabled: true,
			nocache:true
			
		};
		$("body").cgetPopup(popoptions);
});
}

function importOk(){
	//quantitiesData.costType=$("#costType").val();
	quantitiestable.ajax.reload();
}

/*
 * 采集关联
 */
$(".attach-panel").initAttachOper({
	collection: {
		tableid:'budgetRegisterTable'
	}
});

/*
 * 计算费用
 * @returns {Boolean}
 */
/*function budgetCallback(){ 
	var total=new Number(0);	   
	var flag=0
	$(".pro-val").each(function(){
		if($(this).val() == "" || parseFloat($(this).val()) <= 0){
			flag=1
			
		}
		total=new Number($(this).val())+total;
	}); 
	   if(total!==0){
	    $("#inspectionCost").val((total*rateData.inspection).toFixed(2));	//监检费=XMHJ*1.998%
		$("#suCost").val(((parseFloat($("#inspectionCost").val())+total)*rateData.supervisor).toFixed(2));//监理费=(XMHJ+监检费)*3.663%
		//储备金=(XMHJ+监检费+监理费)*0.0555%
		$("#storeCost").val(((new Number(total)+new Number($("#suCost").val())+new Number($("#inspectionCost").val()))*rateData.store).toFixed(2));	
		if(isNaN($("#otherCost1").val())){
			$("#otherCost1").val(0.00);
		}
		$("#otherCost1").val(new Number($("#otherCost1").val()).toFixed(2));
		$("#budgetTotalCost").val((new Number(total)+new Number($("#inspectionCost").val())+new Number($("#suCost").val())+new Number($("#storeCost").val())+ new Number($("#otherCost1").val())).toFixed(2));	
		
	}
}*/
$("#materialListTab").on("shown.bs.tab",function(){	
	handleMaterialList();	
});
var handleMaterialList = function() {
	"use strict";
	if(trSData.budgetRegisterTable.t){
	    materialdata.projId=$("#projId1").val();
	}else{
		materialdata.projId=-1;
	}
	  // materialdata.projId='1001';
	if (!$.fn.DataTable.isDataTable('#materialListTable')) {
	    materialListTable= $('#materialListTable').on( 'init.dt',function(){
	    	$("#materialListForm").toggleEditState(false);
	    	//隐藏遮罩
	    	$("#materialListTable").hideMask();
	    	
	    	$("#materialListForm").toggleEditState();
	    	$(this).bindInputsChange();
	    	saveMaterialList();
	    	setValue();
	    	if(trSData.budgetRegisterTable.t){
	   			   $(".mcSaveBtn").removeClass("hidden");
	   			}else{
	   			   $(".mcSaveBtn").addClass("hidden");
	   			}
	    	
	    }).DataTable({
	    	language: language_CN,
	        lengthMenu: [ 18 ],
	        dom: 'fBrtip',
	        buttons: [
	            { text: '保存', className: 'btn-sm btn-query mcSaveBtn' }
	        ],
	       /* serverSide: true, */
	        //ajax: 'projectjs/constructmanage/json/material-use.json',
			ajax: {
			    url: 'materialReceive/queryMaterialList',
			    contenttype:"application/json;charset=utf-8",
			    data:function(d){
			        $.each(materialdata,function(i,k){
			            d[i] = k;
			        }); 	
			    },
			    datasrc: 'data'
			},
	        columns: [
	            {"data":"materialId", className:"none never"},
	            {"data":"materialNo",responsivePriority: 1},
	  			{"data":"materialName",responsivePriority: 4},
	  			{"data":"materialSpec",responsivePriority: 5},
				{"data":"materialUnit",responsivePriority: 3},
				{"data":"materialNum", className: "text-right", responsivePriority: 2},
				{"data":"remark",responsivePriority: 6}
			],
			order: [[ 1, "asc" ]],
			columnDefs: [{
				targets: 0,
				"visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				targets: 4,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput" ><input class="form-control input-sm" value="'+data+'"></div>';	
					    return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets: 5,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm text-right" data-parsley-type="number" value="'+data+'"><div>';
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
		if(trSData.budgetRegisterTable.t){
			   $(".mcSaveBtn").removeClass("hidden");
			}else{
			   $(".mcSaveBtn").addClass("hidden");
			}
		materialListTable.ajax.reload();
	}
};

var saveMaterialList = function(){	
	$(".mcSaveBtn").on("click",function(){
		var t = $('#materialListTable');
		if(t.checkInputs()){
			var data = t.getInputsData();
			console.info(data);
			if(data.length){
	       		$.ajax({
	       			type: 'POST',
	       			url: 'budgetResultRegister/updateMaterialList',
	       			contentType: "application/json;charset=UTF-8",
	       			data: JSON.stringify(data),
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
	                   console.warn("材料保存失败！");
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

function setValue(){
	$(document).off("change", "#materialListForm td input").on("change", "#materialListForm td input" ,function(){
		$(this).parents("tr").find('input[type="hidden"]').eq(1).val(1);
	});
}
