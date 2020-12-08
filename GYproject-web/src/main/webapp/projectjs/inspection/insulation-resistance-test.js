var InsulationResistanceTestAuditTable;
var insulationResistanceTestTable;
var searchData={};	 //吹扫记录列表查询条件
var checkListData={};//工程报验单列表查询条件
var projId=$('#projId').val();
checkListData.projId=projId;
/**
 * 初始化信息记录区
 */
var handleInsulationResistanceTestAudit = function() {
	"use strict";
	if($('#pcIdNew').val()!==''){
		searchData.pcId=$('#pcIdNew').val();
	}else{
		searchData.pcId=-1;
	}
    if ($('#InsulationResistanceTestAuditTable').length !== 0) {
    	InsulationResistanceTestAuditTable=$('#InsulationResistanceTestAuditTable').on( 'init.dt',function(){
    		//默认选中第一行
    		$(this).bindDTSelected(purgeAuditSelectedBack,true);
   			//隐藏遮罩
   			$('#InsulationResistanceTestAuditTable').hideMask();
   			showReport2();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:true,
            ajax: {
                url: 'insulationResistanceTest/queryInsulationResistanceTest',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/inspection/json/purge.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	  			{"data":"loop"},
				{"data":"phaseAB",className:"text-right"},
				{"data":"phaseBC",className:"text-right"},
				{"data":"phaseCA",className:"text-right"},
				{"data":"zeroAN",className:"text-right"},
				{"data":"zeroBN",className:"text-right"},
				{"data":"zeroCN",className:"text-right"},
				{"data":"earthAE",className:"text-right"},
				{"data":"earthBE",className:"text-right"},
				{"data":"earthCE",className:"text-right"},
				{"data":"zeroEarthNE",className:"text-right"},
			],
			columnDefs: [{
			}]
        }).on("draw.dt",function(){
        	$(this).DataTable().responsive.recalc();
        });
    }
};

var purgeAuditSelectedBack=function(){
}
//初始化
var insulationResistanceTest = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleInsulationResistanceTest();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab,#signTab,#auditTab").on("shown.bs.tab",function(){
        		if($(this).is($("#listTab,#signTab"))){
    				if($(this).is($('#listTab'))){
    					//列表区
    					if(!$.fn.DataTable.isDataTable('#insulationResistanceTestTable')){
    						//初始化列表
            				handleInsulationResistanceTest();
            			}else{
            				//报验区
            				//insulationResistanceTestTable.ajax.reload();
            				checkListData.pcId=$('#pcIdNew').val();
            				$('#insulationResistanceTestTable').cgetData(true,'');
            				trSData.t&&trSData.t.cgetDetail('insulationResistanceTestForm','','');
            				$(".editBtn").addClass("hidden");
            				$('#insulationResistanceTestForm').toggleEditState(false);
            				showReport1();
            			}
    				}else{
    					if(trSData.insulationResistanceTestTable.json && $('#pcId').val()!=""){
        					showReport1();
        				}else{
        					$('.addClear').val('');
        					//清空签字
        					$('.clear-sign').click();
        					showReport3();
        				}

    					setTimeout(function(){
        				    $("#projectImagesList").getImagesList({
        				    	"projId": getProjectInfo().projId,
        				    	"step": getStepId(),
        				    	"projNo": getProjectInfo().projNo,
        				    	"busRecordId": $("#pcId").val() || '-1'
        				    });
        				},300);
    				}
        		}else if($(this).is($("#auditTab"))){
        			if(!$.fn.DataTable.isDataTable('#InsulationResistanceTestAuditTable')){
        				//初始化记录列表
        				handleInsulationResistanceTestAudit();
        			}else{
        				if($('#pcIdNew').val()!==''){
        					searchData.pcId=$('#pcIdNew').val();
	           				$('#InsulationResistanceTestAuditTable').cgetData();
	           				showReport2();
         				}else{
         					searchData.pcId=-1;
	           				$('#InsulationResistanceTestAuditTable').cgetData();
         					showReport2();
         				}
        			}
        		}
        	});
        }
    };
}();


//初始化左侧报验单列表
var handleInsulationResistanceTest=function(){
	"use strict";
	if($('#insulationResistanceTestTable').length !== 0){
		insulationResistanceTestTable= $('#insulationResistanceTestTable').on( 'init.dt',function(){
		$(this).bindDTSelected(trSelectedBack,true);
		$('#insulationResistanceTestTable_filter input').attr('placeholder','施工工序');
		showReport1();
		//隐藏遮罩
		$("#insulationResistanceTestTable").hideMask();
		//增加监听
		addMonitor();
		//修改监听
		modifyMonitor();
		//查询监听
		searchMonitor();
		queryCheckRole();
	    }).DataTable({
	    	language: language_CN,
	        lengthMenu: [ 15 ],
	        dom: 'Bfrtip',
	        buttons: [
	                  { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
	                  { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' }
	              ],
	        serverSide: true, 
	        //ajax: 'projectjs/inspection/json/altimetric_survey2.json',
	        ajax: {
	            url: 'insulationResistanceTest/queryProjectList',
	            type:'post',
	            data: function(d){
	               	$.each(checkListData,function(i,k){
	               		d[i] = k;
	               	});
	               	
	            },
	            datasrc: 'data'
	        },
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
				{"data":"inspectionResult"}
			],
			order: [[ 1, "asc" ]],
			columnDefs: [{
				"targets":0,
				"visible":false
			}] 
	    });
	}	
}

//查详述
var  trSelectedBack=function(v, i, index, t, json){
	$('#pcIdNew').val(json.pcId);
	t.cgetDetail('insulationResistanceTestForm','insulationResistanceTest/viewInsulationResistanceTest','',queryBack);
};

var queryBack=function(){
	showReport1();
}

//查询监听
var  searchMonitor=function(){
	//基础条件查询添加监听
	$('#insulationResistanceTestTable_filter input').on('change',function(){
		var process = $('#insulationResistanceTestTable_filter input').val();
		var pcDesId=$('#pcDesId').val();
		checkListData = {};
		checkListData={'process':process,'pcDesId':pcDesId,'projId':projId};
		$('#insulationResistanceTestTable').cgetData(true,purgeCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#insulationResistanceTestTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

var purgeCallBack=function(){
	var len = $('#insulationResistanceTestTable').DataTable().ajax.json().data ? $('#insulationResistanceTestTable').DataTable().ajax.json().data.length : $('#insulationResistanceTestTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.addClear').val('');
		$('#pcIdNew').val('');
	 }
	//清空签字
	$(".clear-sign").click();
	showReport1();
}

//增加监听
var addMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		$('#insulationResistanceTestForm').toggleEditState(true);
		$(".editBtn").removeClass("hidden");
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#signTab').tab("show");
		//清空要增加的数据项
		$('.addClear').val('');
		$("#qualityStandard").val("MΩ");
		$("#unitMeasurement").val("MΩ");
		//清空签字
		$('.clear-sign').click();
		$('#constructionUnit').val(getProjectInfo().cuName);
		showReport3();
	});
}

//修改监听
var modifyMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		var len=$("#insulationResistanceTestTable").find("tr.selected").length;
		if(len>0){
			$('#insulationResistanceTestForm').toggleEditState(true);
			$(".editBtn").removeClass("hidden");
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#signTab').tab("show");
			//施工或竣工中
        	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#insulationResistanceTestForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#insulationResistanceTestForm").find(".sign-data-input").toggleSign(false);
        	}
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的报验单信息!',
				ahide:true,
				atext:'确定'
			});
		}
		
	});
}

$('#InsulationResistanceTestAuditTable').on("draw.dt",function(){
	$(this).DataTable().responsive.recalc();
});