var mytable;
var purgetable;
var searchData={};	 //吹扫记录列表查询条件
var checkListData={};//工程报验单列表查询条件
var projId=$('#projId').val();
checkListData.projId=projId;
/**
 * 初始化信息记录区
 */
var handlePurge = function() {
	"use strict";
	if($('#pcIdNew').val()!==''){
		searchData.pcId=$('#pcIdNew').val();
	}else{
		searchData.pcId=-1;
	}
    if ($('#purgeAuditTable').length !== 0) {
    	mytable=$('#purgeAuditTable').on( 'init.dt',function(){
    		//默认选中第一行
    		$(this).bindDTSelected(purgeAuditSelectedBack,true);
   			//隐藏遮罩
   			$('#purgeAuditTable').hideMask();
   			showReport2();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:true,
           /* ajax: {
                url: 'purge/queryPurge',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
            },*/
            ajax: 'projectjs/inspection/json/standPipeAudit.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	  			{"data":"purgeNum"},
				{"data":"purgeStarte"}
			],
			columnDefs: [{
		    	targets: 1,
		    	/*
				 * render属性
				 * 方法携带四个参数
				 * data: 该单元格的原始数据，也就是默认显示的那些数据
				 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
				 * row: 当前行的所有原始数据
				 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
				 */
		    	render: function ( data, type, row, meta ) {
		    		if(type === "display"){
						if(data === null||data==="1"){
							var  tdcon="<div class='tdInnerInput'><input class='' type='hidden' name = '"+ row.connectContentId +"_connectContentId' id='"+ row.connectContentId +"_connectContentId' value="+row.connectContentId+">" +
									   "<label><input class='radio-checked field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='1'> 是</label>" +
							           "&nbsp;&nbsp;<label><input class='field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='0'> 否</label></div>";
							return tdcon;
						}
						if(data === "0"){
							var  tdcon="<div class='tdInnerInput'><input class='' type='hidden' name = '"+ row.connectContentId +"_connectContentId' id='"+ row.connectContentId +"_connectContentId' value="+row.connectContentId+">" +
									"<label><input class='field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='1'> 是</label>" +
					                "&nbsp;&nbsp;<label><input class='radio-checked field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='0'> 否</label></div>";
					        return tdcon;
						}
					}else{
						return data;
					}}
		    }]
        });
    }
};

var purgeAuditSelectedBack=function(){
}
//初始化
var purge = function () {
	"use strict";
    return {
        //main function
        init: function () {
			handlepurge();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab,#signTab,#auditTab").on("shown.bs.tab",function(){
        		if($(this).is($("#listTab,#signTab"))){
    				if($(this).is($('#listTab'))){
    					if(!$.fn.DataTable.isDataTable('#purgeTable')){
    						//初始化列表
            				handlepurge();
            			}else{
            				//purgetable.ajax.reload();
            				$('#purgetable').cgetData(true,'');
            				//trSData.t&&trSData.t.cgetDetail('purgeForm','','');
            				$(".editBtn").addClass("hidden");
            				$('#purgeForm').toggleEditState(false);
            				showReport1();
            			}
    				}else{
    					if(trSData.purgeTable.json && $('#pcId').val()!=""){
        					showReport1();
        				}else{
        					//$('.addClear').val('');
        					//清空签字
        					$('.clear-sign').click();
        					showReport3();
        				}
    				}
        		}else if($(this).is($("#auditTab"))){
        			if(!$.fn.DataTable.isDataTable('#purgeAuditTable')){
        				//初始化记录列表
        				handlePurge();
        			}else{
        				if($('#pcIdNew').val()!==''){
        					searchData.pcId=$('#pcIdNew').val();
	           				$('#purgeAuditTable').cgetData();
	           				showReport2();
         				}else{
         					searchData.pcId=-1;
	           				$('#purgeAuditTable').cgetData();
         					showReport2();
         				}
        			}
        		}
        	});
        }
    };
}();



var handlepurge=function(){
	"use strict";
	if($('#purgeTable').length !== 0){
		purgetable= $('#purgeTable').on( 'init.dt',function(){
		$(this).bindDTSelected(trSelectedBack,true);
		$('#purgeTable_filter input').attr('placeholder','施工工序');
		showReport1();
		//隐藏遮罩
		$("#purgeTable").hideMask();
		//增加监听
		addMonitor();
		//修改监听
		modifyMonitor();
		//查询监听
		searchMonitor();
	    }).DataTable({
	    	language: language_CN,
	        lengthMenu: [ 15 ],
	        dom: 'Bfrtip',
	        buttons: [
	                  { text: '增加', className: 'btn-sm btn-query business-tool-btn addBtn' },
	                  { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' }
	              ],
	        //serverSide: true, 
	        ajax: 'projectjs/inspection/json/standPipe.json',
	        /*ajax: {
	            url: 'purge/queryProjectList',
	            type:'post',
	            data: function(d){
	               	$.each(checkListData,function(i,k){
	               		d[i] = k;
	               	});
	               	
	            },
	            datasrc: 'data'
	        },*/
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
	//t.cgetDetail('purgeForm','purge/viewPurge','',queryBack);
};

var queryBack=function(){
	showReport1();
}

//查询监听
var  searchMonitor=function(){
	//基础条件查询添加监听
	$('#purgeTable_filter input').on('change',function(){
		var process = $('#purgeTable_filter input').val();
		var pcDesId=$('#pcDesId').val();
		checkListData = {};
		checkListData={'process':process,'pcDesId':pcDesId,'projId':projId};
		$('#purgeTable').cgetData(true,purgeCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#purgeTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

var purgeCallBack=function(){
	var len = $('#purgeTable').DataTable().ajax.json().data ? $('#purgeTable').DataTable().ajax.json().data.length : $('#purgeTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		//$('.addClear').val('');
		$('#pcIdNew').val('');
	 }
	//清空签字
	$(".clear-sign").click();
	showReport1();
}

//增加监听
var addMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		$('#purgeForm').toggleEditState(true);
		$(".editBtn").removeClass("hidden");
		//$('ul.nav-tabs>li.active').removeClass("active");
		$('#signTab').tab("show");
		//清空要增加的数据项
		//$('.addClear').val('');
		//清空签字
		$('.clear-sign').click();
		//showReport3();
	});
}

//修改监听
var modifyMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		var len=$("#purgeTable").find("tr.selected").length;
		if(len>0){
			$('#purgeForm').toggleEditState(true);
			$(".editBtn").removeClass("hidden");
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#signTab').tab("show");
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
