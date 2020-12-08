/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var budgetAdjustTable;
var quantitiestable;
/**查询条件*/
var searchData={};
var quantitiesData={},rateData={};
var handleBudgetAdjust = function() {
	"use strict";
    if ($('#budgetAdjustTable').length !== 0) {
    	budgetAdjustTable= $('#budgetAdjustTable').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#budget_sum_box").cgetContent("budgetAdjust/viewPage");
   			//隐藏遮罩
   			$('#budgetAdjustTable').hideMask();
   			$("#budgetAdjustTable_filter input").attr("placeholder","工程编号");
   	    	//绑定单击事件 弹出搜索框
   			searchMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'budgetAdjust/queryChangeManagement',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax:'projectjs/budget/json/budget_adjust.json',
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
				{"data":"cuReason"},
				{"data":"cmDate"}
			],
			columnDefs: [{
				"targets":0,
				"visible":false
			}],
        });
    }
};

//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").on("click",function(){
		var url = "#budgetAdjust/projectSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$('#budgetAdjustTable_filter input').on('change',function(){
		var projNo = $('#budgetAdjustTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#budgetAdjustTable').cgetData(true,budgetAdjustCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#budgetAdjustTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	searchData = $('#searchForm_change').serializeJson();
	var projNo = $('#budgetAdjustTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#budgetAdjustTable').cgetData(true,budgetAdjustCallBack);  
}

var budgetAdjustCallBack=function(){
	var len = $('#budgetAdjustTable').DataTable().ajax.json().data ? $('#budgetAdjustTable').DataTable().ajax.json().data.length : $('#budgetAdjustTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#budgetSumForm')[0].reset();
		 $(':input','#budgetSumForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}



/** 选中行时，查询详述*/
var trSelectedBack = function(v, i, index, t, json){
	if(json!=undefined){
		$("#projId1").val(json.projId);
		//工程为民用类型
		console.info(json.projLtypeId==$("#civilVal").val());
	}
	if ($.fn.DataTable.isDataTable('#quanlitiesTable')) {
		quantitiesData.projId=json.projId;
		quantitiesData.costType=$("#costType1").val();
		quantitiestable.ajax.reload();
	}
	//清空表单
	$(':input','#budgetSumForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('budgetSumForm','budgetAdjust/queryBudge','',budgetCallback);	
}

var budgetAdjust = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleBudgetAdjust();
        }
    };
}();

$("#budgetSumTab").on("shown.bs.tab",function(){
	trSData.t.cgetDetail('budgetSumForm','budgetAdjust/queryBudge','',budgetCallback);//查详述
});

/**切换到工程量表*/
$("#quantitiesTab").off("shown.bs.tab").on("shown.bs.tab",function(){
	if(trSData.json!=undefined){
	$("#costType1").val($("#costTypeSelect select").val());
	quantitiesData.costType=$("#costType1").val();
	}else{
		$("#costTypeSelect select").hide();
	}
	quantitiesTabInit();
});


//单位工程费用汇总表
var quantitiesTabInit = function() {
	"use strict";
	if($("#budgetId").val()==""){
		quantitiesData.budgetId="-1";
		quantitiesData.projId="-1";
		quantitiesData.cmId="-1";
	}else{
		quantitiesData.budgetId=$("#budgetId").val();
		quantitiesData.projId=$("#projId").val();
		quantitiesData.cmId=$("#cmId").val();
	}
	if (!$.fn.DataTable.isDataTable('#quanlitiesTable')) {
		quantitiestable= $('#quanlitiesTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#quanlitiesTable').hideMask();
   			importBtn();
   			if($("#quanlitiesTable_wrapper #costTypeSelect").length < 1)$("#quanlitiesTable_wrapper").prepend($("#costTypeSelect").removeClass("hidden"));
   			$("#costTypeSelect select:visible").off().on("change",function(){
   				$("#costType1").val($(this).val());
   				quantitiesData.costType=$(this).val();
   				quantitiestable.ajax.reload();
   			});
   		//
   		 if(state!=undefined&&state==1){
   			 console.info("in===="+state);
	        	$(".importBtn").addClass("hidden");
	        }else{
	        	$(".importBtn").removeClass("hidden");
	        }
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                { text: '导入', className: 'btn-sm btn-query business-tool-btn importBtn' }
               /* { text: '放弃', className: 'btn-sm btn-warn business-tool-btn cancleBtn' }*/
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'budgetAdjust/queryBudgetTotalTable',
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
            select: true,    //支持多选
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
    	quantitiesData.projId=$("#projId").val();
		quantitiesData.costType=$("#costType1").val();
		console.info("in===="+quantitiesData.budgetId);
		quantitiestable.ajax.reload(function(){
			 if(state!=undefined&&state==1){
	   			 
		        	$(".importBtn").addClass("hidden");
		        }else{
		        	$(".importBtn").removeClass("hidden");
		        }
		});
    }
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
		//最后一列
		//$("#quanlitiesTable tr:last input:last").val(amount)
		var  trObj = $("#budgetAdjustTable tr");
		//alert(trObj.length);
		var totalRow=0;
        $("#budgetAdjustTable tr td:nth-child(4)").each(function (){
             totalRow+=parseFloat($(this).val());
             alert("totalRow"+totalRow);
        });
		
	})
};
function importBtn(){
$(".importBtn").off("click").on("click",function(){
	
	$("#projId1").val($("#projId").val());
	var popoptions = {
			title: '文件导入',
			content: "#budgetAdjust/importPop?url=budgetAdjust/ImportTotalExcel",
			accept: importOk,
			atext: '完成',
			adisabled: true,
			nocache:true
			
		};
		$("body").cgetPopup(popoptions);
});
}

function importOk(){
	 quantitiesData.costType=$("#costType").val();
	 console.info("budgetId===="+$("#budgetId").val());
	 if($("#budgetId").val()==-1||$("#budgetId").val()==''){
		 trSData.t && trSData.t.cgetDetail('budgetSumForm','changeRecord/queryBudge?projId='+$("#projId").val()+'&cmId='+$("#cmId").val()+'&mcType='+$("#mcType").val(),'',function(data){
			 $("#budgetId").val(data.budgetId);
			  quantitiesData.budgetId=data.budgetId;			
		      quantitiestable.ajax.reload(function(){
			  quantitiestable.responsive.recalc();
		    });
		}); 
	 }else{
		  quantitiesData.budgetId=$("#budgetId").val();			
	      quantitiestable.ajax.reload(function(){
		  quantitiestable.responsive.recalc();
	    });
	 }
	
	
	
}

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

/**
 * 计算费用
 * @returns {Boolean}
 */
function budgetCallback(){	
	if(isNaN($("#otherCost1").val())){
		console.info("===sdd==========");
		$("#otherCost1").val(0.00);
	}
	    $("#otherCost1").val(new Number($("#otherCost1").val()).toFixed(2));
		//$("#budgetTotalCost").val((new Number($("#budgetTotalCost").val())+new Number($("#otherCost1").val())).toFixed(2));	
	
	
	
/*	var total=new Number(0);	   
	var flag=0;
	$(".pro-val").each(function(){
		console.info("==="+$(this).val());
		if($(this).val() == "" || parseFloat($(this).val()) <= 0){
			flag=1
			
		}
		total=new Number($(this).val())+total;
	}); 
	   console.info("======"+total);
	  $("#inspectionCost").val((total*rateData.inspection).toFixed(2));	//监检费=XMHJ*1.998%
		$("#suCost").val(((parseFloat($("#inspectionCost").val())+total)*rateData.supervisor).toFixed(2));//监理费=(XMHJ+监检费)*3.663%
		//储备金=(XMHJ+监检费+监理费)*0.0555%
		$("#storeCost").val(((new Number(total)+new Number($("#suCost").val())+new Number($("#inspectionCost").val()))*rateData.store).toFixed(2));	
		$("#budgetTotalCost").val((new Number(total)+new Number($("#inspectionCost").val())+new Number($("#suCost").val())+new Number($("#storeCost").val())+ new Number($("#otherCost1").val())).toFixed(2));	
	   */
	   if(state!=undefined&&state==1){
	    	$(".editbtn").addClass("hidden");
	    	$("#budgetSumForm").toggleEditState(false);
	   }else{
		   $(".editbtn").removeClass("hidden");
			$("#budgetSumForm").toggleEditState(true);
	   }
		$(".editbtn").removeClass("hidden");
		$("#budgetSumForm").toggleEditState(true);
}

