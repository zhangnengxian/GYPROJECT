var controlDebuggingAuditTable;
var controlDebuggingTable;
var searchData={};	 //吹扫记录列表查询条件
var checkListData={};//工程报验单列表查询条件
var projId=$('#projId').val();
checkListData.projId=projId;
/**
 * 初始化信息记录区
 */
var handleControlDebuggingAudit = function() {
	"use strict";
	if($('#pcIdNew').val()!==''){
		searchData.pcId=$('#pcIdNew').val();
	}else{
		searchData.pcId=-1;
	}
    if ($('#controlDebuggingAuditTable').length !== 0) {
    	controlDebuggingAuditTable=$('#controlDebuggingAuditTable').on( 'init.dt',function(){
    		//默认选中第一行
    		$(this).bindDTSelected(purgeAuditSelectedBack,true);
   			//隐藏遮罩
   			$('#controlDebuggingAuditTable').hideMask();
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
                url: 'controlDebugging/queryControlDebugging',
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
	  			{"data":"equipmentNameType"},
				{"data":"cdNum",className:"text-right"},
				{"data":"cdNo"},
				{"data":"leaveFactoryDate"},
				{"data":"manufactureFactory"},
				{"data":"note"}
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
var controlDebugging = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleControlDebugging();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab,#signTab,#auditTab").on("shown.bs.tab",function(){
        		if($(this).is($("#listTab,#signTab"))){
    				if($(this).is($('#listTab'))){
    					//列表区
    					if(!$.fn.DataTable.isDataTable('#controlDebuggingTable')){
    						//初始化列表
    						handleControlDebugging();
            			}else{
            				//报验区
            				//controlDebuggingTable.ajax.reload();
            				checkListData.pcId=$('#pcIdNew').val();
            				$('#controlDebuggingTable').cgetData(true,'');
            				trSData.t&&trSData.t.cgetDetail('controlDebuggingForm','','');
            				$(".editBtn").addClass("hidden");
            				$('#controlDebuggingForm').toggleEditState(false);
            				showReport1();
            			}
    				}else{
    					if(trSData.controlDebuggingTable.json && $('#pcId').val()!=""){
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
        				    	"stepId": getStepId(),
        				    	"projNo": getProjectInfo().projNo,
        				    	"busRecordId": $("#pcId").val() || '-1'
        				    });
        				},300);
    				}
        		}else if($(this).is($("#auditTab"))){
        			if(!$.fn.DataTable.isDataTable('#controlDebuggingAuditTable')){
        				//初始化记录列表
        				handleControlDebuggingAudit();
        			}else{
        				if($('#pcIdNew').val()!==''){
        					searchData.pcId=$('#pcIdNew').val();
	           				$('#controlDebuggingAuditTable').cgetData();
	           				showReport2();
         				}else{
         					searchData.pcId=-1;
	           				$('#controlDebuggingAuditTable').cgetData();
         					showReport2();
         				}
        			}
        		}
        	});
        }
    };
}();


//初始化左侧报验单列表
var handleControlDebugging=function(){
	"use strict";
	if($('#controlDebuggingTable').length !== 0){
		controlDebuggingTable= $('#controlDebuggingTable').on( 'init.dt',function(){
		$(this).bindDTSelected(trSelectedBack,true);
		$('#controlDebuggingTable_filter input').attr('placeholder','施工工序');
		showReport1();
		//隐藏遮罩
		$("#controlDebuggingTable").hideMask();
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
	            url: 'controlDebugging/queryProjectList',
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
	t.cgetDetail('controlDebuggingForm','controlDebugging/viewControlDebugging','',queryBack);
};

var queryBack=function(){
	showReport1();
}

//查询监听
var  searchMonitor=function(){
	//基础条件查询添加监听
	$('#controlDebuggingTable_filter input').on('change',function(){
		var process = $('#controlDebuggingTable_filter input').val();
		var pcDesId=$('#pcDesId').val();
		checkListData = {};
		checkListData={'process':process,'pcDesId':pcDesId,'projId':projId};
		$('#controlDebuggingTable').cgetData(true,purgeCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#controlDebuggingTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

var purgeCallBack=function(){
	var len = $('#controlDebuggingTable').DataTable().ajax.json().data ? $('#controlDebuggingTable').DataTable().ajax.json().data.length : $('#controlDebuggingTable').DataTable().ajax.json().length;
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
		$('#controlDebuggingForm').toggleEditState(true);
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
		var len=$("#controlDebuggingTable").find("tr.selected").length;
		if(len>0){
			$('#controlDebuggingForm').toggleEditState(true);
			$(".editBtn").removeClass("hidden");
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#signTab').tab("show");
			//施工或竣工中
        	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#controlDebuggingForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#controlDebuggingForm").find(".sign-data-input").toggleSign(false);
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

$('#controlDebuggingAuditTable').on("draw.dt",function(){
	$(this).DataTable().responsive.recalc();
});