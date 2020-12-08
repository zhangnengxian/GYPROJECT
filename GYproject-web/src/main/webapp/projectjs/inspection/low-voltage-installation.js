var lowVoltageInstallationAuditTable,
allowableErrorThreadingPipeItemTable;
var lowVoltageInstallationTable;
var searchData={},	 //吹扫记录列表查询条件
basicSearchData={};
var checkListData={};//工程报验单列表查询条件
var projId=$('#projId').val();
var pcId=$('#pcId').val();
checkListData.projId=projId;

/**
 * 初始化信息记录区
 */
var handleLowVoltageInstallationAudit = function() {
	"use strict";
	if($('#pcIdNew').val()!==''){
		searchData.pcId=$('#pcIdNew').val();
	}else{
		searchData.pcId=-1;
	}
    if ($('#lowVoltageInstallationAuditTable').length !== 0) {
    	lowVoltageInstallationAuditTable=$('#lowVoltageInstallationAuditTable').on( 'init.dt',function(){
    		//默认选中第一行
    		$(this).bindDTSelected(purgeAuditSelectedBack,true);
   			//隐藏遮罩
   			$('#lowVoltageInstallationAuditTable').hideMask();
   			$(this).bindInputsChange();
   			showReport2();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:true,
            ajax: {
                url: 'lowVoltageInstallation/queryGuaranteeItemsList',
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
                {"data":"gilId",className:"none never"},
                {"data":"giId",className:"none never"},
                {"data":"tbType",className:"none never"},
	  			{"data":"giDes"},
				{"data":"qualityCondition"}
			],
			columnDefs: [{
					"targets":0,
					"visible":false
				},{
					"targets":1,
					"visible":false
				},{
					targets:2,
					"visible":false
				},{
					targets:3,
					render:function(data,type,row,meta){
						if(data === null){
							data = "";
						}
						if(type === "display"){
							var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " disabled="disabled" name="' + row.giId + '_giDes" id="' + row.giId + '_giDes" value="'+data+'"></div>';
							return tdcon;
						}else{
							return data;
						}
					}
				},{
				targets:4,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						/*var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " name='+row.giId+"_qualityCondition" +'id='+row.giId+"_qualityCondition" +'value='+data+'></div>';
						return tdcon;*/
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm "  name="qualityCondition" id="qualityCondition" value="'+data+'"></div>';
						return tdcon;
						/*var tdcon = '<div class="tdInnerInput">';
						tdcon+="<input class='form-control input-sm' name='"+row.giId+"_qualityCondition'" +"id='"+row.giId+"_qualityCondition" +"value="+data+"></div>";
						return tdcon;*/
					}else{
						return data;
					}
				}
			}]
        }).on("draw.dt",function(){
        	$(this).DataTable().responsive.recalc();
        });
    }
};
var purgeAuditSelectedBack=function(){
}
//基本项目
var handleBasicThreadingPipeItem=function(){
	"use strict";
	if($('#pcIdNew').val()!==''){
		basicSearchData.pcId=$('#pcIdNew').val();
	}else{
		basicSearchData.pcId=-1;
	}
    if ($('#basicLowVoltageInstallationTable').length !== 0) {
    	lowVoltageInstallationAuditTable=$('#basicLowVoltageInstallationTable').on( 'init.dt',function(){
    		//默认选中第一行
    		//$(this).bindDTSelected(purgeAuditSelectedBack,true);
   			//隐藏遮罩
   			$('#basicLowVoltageInstallationTable').hideMask();
   			$(this).bindInputsChange();
   			showReport2();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:true,
            ajax: {
                url: 'lowVoltageInstallation/queryLowVoltageInstallationBasic',
                type:'post',
                data: function(d){
                   	$.each(basicSearchData,function(i,k){
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
	  			{"data":"bpcId",className:"none never"},
	  			{"data":"itemType",className:"none never"},
				{"data":"item"},
				{"data":"qualitySituation1"},
				{"data":"qualitySituation2"},
				{"data":"qualitySituation3"},
				{"data":"qualitySituation4"},
				{"data":"qualitySituation5"},
				{"data":"qualitySituation6"},
				{"data":"qualitySituation7"},
				{"data":"qualitySituation8"},
				{"data":"qualitySituation9"},
				{"data":"qualitySituation10"},
				{"data":"rank", responsivePriority: 1},
				{"data":"bpciId",className:"none never"}
			],
			columnDefs: [{
				"targets":14,
				"visible":false
			},{
				"targets":0,
				"visible":false
			},{
				"targets":1,
				"visible":false
			},{
				targets:3,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm "  name="' + row.bpcId + '_qualitySituation1" id="' + row.bpcId + '_qualitySituation1" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets:4,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " name="' + row.bpcId + '_qualitySituation2" id="' + row.bpcId + '_qualitySituation2" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets:5,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " name="' + row.bpcId + '_qualitySituation3" id="' + row.bpcId + '_qualitySituation3" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets:6,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " name="' + row.bpcId + '_qualitySituation4" id="' + row.bpcId + '_qualitySituation4" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets:7,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm "  name="' + row.bpcId + '_qualitySituation5" id="' + row.bpcId + '_qualitySituation5" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets:8,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm "  name="' + row.bpcId + '_qualitySituation6" id="' + row.bpcId + '_qualitySituation6" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets:9,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " name="' + row.bpcId + '_qualitySituation7" id="' + row.bpcId + '_qualitySituation7" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets:10,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " name="' + row.bpcId + '_qualitySituation8" id="' + row.bpcId + '_qualitySituation8" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets:11,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " name="' + row.bpcId + '_qualitySituation9" id="' + row.bpcId + '_qualitySituation9" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets:12,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " name="' + row.bpcId + '_qualitySituation10" id="' + row.bpcId + '_qualitySituation10" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets:13,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " name="' + row.bpcId + '_rank" id="' + row.bpcId + '_rank" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			}]
        }).on("draw.dt",function(){
        	$(this).DataTable().responsive.recalc();
        });
    }
}






//初始化
var lowVoltageInstallation = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleLowVoltageInstallation();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab,#signTab,#auditTab").on("shown.bs.tab",function(){
        		if($(this).is($("#listTab,#signTab"))){
    				if($(this).is($('#listTab'))){
    					//列表区
    					if(!$.fn.DataTable.isDataTable('#lowVoltageInstallationTable')){
    						//初始化列表
    						handleLowVoltageInstallation();
            			}else{
            				//报验区
            				//lowVoltageInstallationTable.ajax.reload();
            				checkListData.pcId=$('#pcIdNew').val();
            				$('#lowVoltageInstallationTable').cgetData(true,'');
            				trSData.t&&trSData.t.cgetDetail('lowVoltageInstallationForm','','');
            				$(".editBtn").addClass("hidden");
            				$('#lowVoltageInstallationForm').toggleEditState(false);
            				showReport1();
            			}
    				}else{
    					if(trSData.lowVoltageInstallationTable.json && $('#pcId').val()!=""){
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
        			if(!$.fn.DataTable.isDataTable('#lowVoltageInstallationAuditTable')){
        				//初始化记录列表
        				handleLowVoltageInstallationAudit();
        			}else{
        				if($('#pcIdNew').val()!==''){
        					searchData.pcId=$('#pcIdNew').val();
	           				$('#lowVoltageInstallationAuditTable').cgetData();
	           				//showReport2();
         				}else{
         					searchData.pcId=-1;
         					$('#lowVoltageInstallationAuditTable').cgetData();
         					//showReport2();
         				}
        			}
        			if(!$.fn.DataTable.isDataTable('#basicLowVoltageInstallationTable')){
        				//基本项目表格
        				handleBasicThreadingPipeItem();
        			}else{
        				if($('#pcIdNew').val()!==''){
        					basicSearchData.pcId=$('#pcIdNew').val();
        					$('#basicLowVoltageInstallationTable').cgetData();
        					//showReport2();
        				}else{
        					basicSearchData.pcId=-1;
        					$('#basicLowVoltageInstallationTable').cgetData();
        					//showReport2();
        				}
        			}
        			showReport2();
        		}
        	});
        }
    };
}();


//初始化左侧报验单列表
var handleLowVoltageInstallation=function(){
	"use strict";
	if($('#lowVoltageInstallationTable').length !== 0){
		lowVoltageInstallationTable= $('#lowVoltageInstallationTable').on( 'init.dt',function(){
		$(this).bindDTSelected(trSelectedBack,true);
		$('#lowVoltageInstallationTable_filter input').attr('placeholder','施工工序');
		showReport1();
		//隐藏遮罩
		$("#lowVoltageInstallationTable").hideMask();
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
	            url: 'lowVoltageInstallation/queryProjectList',
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
	t.cgetDetail('lowVoltageInstallationForm','threadingPipe/viewThreadingPipe','',queryBack);
};

var queryBack=function(){
	showReport1();
}

//查询监听
var  searchMonitor=function(){
	//基础条件查询添加监听
	$('#lowVoltageInstallationTable_filter input').on('change',function(){
		var process = $('#lowVoltageInstallationTable_filter input').val();
		var pcDesId=$('#pcDesId').val();
		checkListData = {};
		checkListData={'process':process,'pcDesId':pcDesId,'projId':projId};
		$('#lowVoltageInstallationTable').cgetData(true,purgeCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#lowVoltageInstallationTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

var purgeCallBack=function(){
	var len = $('#lowVoltageInstallationTable').DataTable().ajax.json().data ? $('#lowVoltageInstallationTable').DataTable().ajax.json().data.length : $('#lowVoltageInstallationTable').DataTable().ajax.json().length;
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
		$('#lowVoltageInstallationForm').toggleEditState(true);
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
		var len=$("#lowVoltageInstallationTable").find("tr.selected").length;
		if(len>0){
			$('#lowVoltageInstallationForm').toggleEditState(true);
			$(".editBtn").removeClass("hidden");
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#signTab').tab("show");
			//施工或竣工中
        	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#lowVoltageInstallationForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#lowVoltageInstallationForm").find(".sign-data-input").toggleSign(false);
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

$('#lowVoltageInstallationAuditTable').on("draw.dt",function(){
	$(this).DataTable().responsive.recalc();
});