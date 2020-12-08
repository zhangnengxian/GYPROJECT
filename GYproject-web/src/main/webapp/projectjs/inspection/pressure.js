
var mytable;
var pressuretable;
var checkListData={};//报验单查询方法
var projId=$('#projId').val();
checkListData.projId=projId;
var searchData={};
/**
 * 初始化信息
 */
var handlePressure = function() {
	"use strict";
	if($('#pcIdNew').val()!==''){
		searchData.pcId=$('#pcIdNew').val();
	}else{
		searchData.pcId=-1;
	}
    if ($('#pressureRecordTable').length !== 0) {
    	mytable= $('#pressureRecordTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#pressureRecordTable').hideMask();
   	    	//绑定单击事件 弹出搜索框
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
                url: 'pressure/queryPressure',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/inspection/json/pressure.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	  			{"data":"pressureStarteEnd"}, 
				{"data":"designPressure"},
				{"data":"designMedium"},
				{"data":"strengthPa"},
				{"data":"strengthMedium"},
				{"data":"strengthTime"},
				{"data":"strengthResult"},
				{"data":"rigorPa"},
				{"data":"rigorMedium"},
				{"data":"rigorTime"},
				{"data":"rigorResult"}
			],
			columnDefs: [{
			}]
        });
    }
};


var  trSelectedBack=function(){};

var pressure = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handlepressure();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab,#auditTab, #signTab").on("shown.bs.tab",function(){
        		if($(this).is($("#listTab,#signTab"))){
        			if($(this).is($('#listTab'))){
        				if(!$.fn.DataTable.isDataTable('#pressureTable')){
        					//初始化报验单
            				handlepressure();
            			}else{
            				$(".editBtn").addClass("hidden");
            				$('#pressureForm').toggleEditState(false);
            				showReport1();
            			}
        			}else{
        				if(trSData.pressureTable.json && $('#pcId').val()!=""){
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
        		}else{
        			if(!$.fn.DataTable.isDataTable('#pressureRecordTable')){
        				//初始化记录列表
        				handlePressure();
        			}else{
        				if($('#pcIdNew').val()!==''){
        					searchData.pcId=$('#pcIdNew').val();
	           				$('#pressureRecordTable').cgetData();
	           				showReport2();
         				}else{
         					searchData.pcId=-1;
	           				$('#pressureRecordTable').cgetData();
         					showReport2();
         				}
        			}
        		}
        	});
        }
    };
}();


var handlepressure=function(){
	"use strict";
	if($('#pressureTable').length !== 0){
		pressuretable= $('#pressureTable').on( 'init.dt',function(){
		$(this).bindDTSelected(trSelectedBack,true);
		$('#pressureTable_filter input').attr('placeholder','试压部位');
		showReport1();
		//隐藏遮罩
		$("#pressureTable").hideMask();
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
	            url: 'pressure/queryProjectList',
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
	t.cgetDetail('pressureForm','pressure/viewCheckList','',queryBack);
};

var queryBack=function(){
	showReport1();
}

//查询监听
var  searchMonitor=function(){
	//基础条件查询添加监听
	$('#pressureTable_filter input').on('change',function(){
		var process = $('#pressureTable_filter input').val();
		var pcDesId=$('#pcDesId').val();
		checkListData = {};
		checkListData={'process':process,'pcDesId':pcDesId,'projId':projId};
		$('#pressureTable').cgetData(true,purgeCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#pressureTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

var purgeCallBack=function(){
	var len = $('#pressureTable').DataTable().ajax.json().data ? $('#pressureTable').DataTable().ajax.json().data.length : $('#pressureTable').DataTable().ajax.json().length;
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
		$('#pressureForm').toggleEditState(true);
		$(".editBtn").removeClass("hidden");
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#signTab').tab("show");
		//清空要增加的数据项
		$('.addClear').val('');
		//清空签字
		$(".clear-sign").click();
		$('#constructionUnit').val(getProjectInfo().cuName);
	});
}

//修改监听
var modifyMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		var len=$("#pressureTable").find("tr.selected").length;
		if(len>0){
			$('#pressureForm').toggleEditState(true);
			$(".editBtn").removeClass("hidden");
			$('ul.nav-tabs>li.active').removeClass("active");
			$('#signTab').tab("show");
			//施工或竣工中
        	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#pressureForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#pressureForm").find(".sign-data-input").toggleSign(false);
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







