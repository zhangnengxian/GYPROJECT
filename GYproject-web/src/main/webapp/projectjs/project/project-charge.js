
var projectChargeTable;
var searchData={};
var accrualsData={},cashFlowData={};
var cashFlowTable,accrualsTable;
var handleProjectCharge = function() {
	"use strict";
    if ($('#projectChargeTable').length !== 0) {
    	projectChargeTable= $('#projectChargeTable').on( 'init.dt',function(){
    	
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		
    		$("#charge_panel_box").cgetContent("charge/viewPage");
			$("#projectChargeTable_filter input").attr("placeholder","工程编号");
			$('#projectChargeTable').hideMask();	
			//绑定单击事件 弹出搜索框
   			searchPop();
   			//隐藏遮罩
   			   			
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
                url: 'charge/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/accept/json/project_accept.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            colReorder: true,//列拖动
            select: true,  //支持多选
            columns: [
                {"data":"projId",className:"none never"}, 
	  			{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"projStatusDes"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}]
        });
        
    }
};
/**
 * 弹屏监听方法
 */
var searchPop=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#charge/chargeSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone,
			cancel:clearForm
		});
	});
	
	//基础条件查询添加监听
	$('#projectChargeTable_filter input').on('change',function(){
		var projNo = $('#projectChargeTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#projectChargeTable').cgetData(false);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#projectChargeTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});			
};

var clearForm=function(){};
/**
 * 选中时加载详情
 */
var trSelectedBack = function(v, i, index, t, json){	
	/*$(".invoice").removeAttr("disabled");*/
	//加载页面
	$("#projId1").val(json.projId);
	$('#chargeForm').formReset(":radio");	
	/*if($('[name="invoice"]:checked').val()=='1'){
		$('[name="invoice"][value=0]').attr("checked",true);
		$('[name="invoice"]').change();
    //	$("#projId1").attr("checked");
     }
	
	$('[name="isCharge"][value=0]').attr("checked",true);*/
	if($("#accrualsTad").parent().hasClass("active")){
		changeFun(true);
	}else{
		changeFun(false);
	}
	 
	t.cgetDetail("chargeForm",'charge/viewDetail','',function(){
		openBank=$("#openBank").val();
	    account=$("#account").val();
	    $("#chargeForm").toggleEditState(true);
	});
	
	accrualsData.projId=json.projId;
	cashFlowData.projId=json.projId;
	$(".tab-pane.active table.dataTable").DataTable().ajax.reload();
}


/** 查询弹出屏，点击确定后 */
var searchDone = function(){	
	searchData = $("#searchForm_accept").serializeJson();
	searchData.projNo=$("#projectChargeTable_filter input").val();
    $("#projectChargeTable").cgetData(false);  //列表重新加载
    
}


/**
 * 初始化工程列表
 */
var projectCharge = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){    
        		handleProjectCharge();
        	});
        }
    };
}();

function changeFun(data){
	 $(".editbtn").addClass("hidden");
	 $(".cancelCharge").addClass("hidden");
	  reback();
	  $(".bill_type_btn").removeAttr("disabled");
	 if($('[name="billType"]:checked').val()=='0'){
		$('[name="billType"][value=1]').attr("checked",true);
		$('[name="billType"]').change();
	 }
	if(data){				
		 if($('[name="invoice"]:checked').val()=='1'){
			$('[name="invoice"][value=0]').attr("checked",true);
			$('[name="invoice"]').change();
	     }
	
		$('[name="isCharge"][value=0]').attr("checked",true);
		$(".is_Invoice_btn").removeAttr("disabled");		
		$(".is_Invoice").removeAttr("readonly");
		$("#chargeForm").toggleEditState(true).styleFit();
	}else{
		 if($('[name="invoice"]:checked').val()=='1'){
			$('[name="invoice"][value=0]').attr("checked",true);
			$('[name="invoice"]').change();
	     }
		$('[name="isCharge"][value=1]').attr("checked",true);
		$(".is_Invoice_btn").attr("disabled",true);
		$(".is_Invoice").attr("readonly",true);
		$(".bill_type_btn").attr("disabled",true);
		$("#chargeForm").toggleEditState(false).styleFit();
	}
	$(".is_Invoice_btn").removeClass("disabled");
}

function reback(){
	
	$("#cfType").val("");
	$("#cfTypeDes").val("");
	$("#cfAmount").val("");
	$("#arId").val("");
	$("input[type='text'].is_Invoice").val("");
}

$('.attach-panel').initAttachOper({
	collection: {
		tableid:'projectChargeTable'
	}
});