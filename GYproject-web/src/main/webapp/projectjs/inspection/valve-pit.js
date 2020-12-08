var derustingtable;//除锈列表
var checkListData={};//查询条件
var projId=$('#projId').val();
    checkListData.projId=projId;
var searchData={},antSpecData={};
var derustingRecordTable,antSpecTable;

var handleDerusting = function() {
	"use strict";
	if($('#pcIdNew').val()!==''){
		searchData.pcId=$('#pcIdNew').val();
	}else{
		searchData.pcId=-1;
	}
	console.info("pcIdNew=========="+searchData.pcId);
    if ($('#derustingRecordTable').length !== 0) {
    	derustingRecordTable= $('#derustingRecordTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#derustingRecordTable').hideMask();
   	    	//绑定单击事件 弹出搜索框
   			showReport2();
   			handleAntSpec();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:true,
            /*ajax: {
                url: 'derusting/queryDerusting',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
            },*/
            ajax: 'projectjs/inspection/json/checkValvePit.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
				{"data":"dpCheckItemDes"},
				{"data":"dpMethodDes"},
				{"data":"dpResultDes"}
			],
			columnDefs: [{
			}]
        });
    }
};
//检查规格列表
var handleAntSpec = function() {
	"use strict";
	if($('#pcIdNew').val()!==''){
		antSpecData.pcId=$('#pcIdNew').val();
	}else{
		antSpecData.pcId=-1;
	}
	console.info("antSpecId=========="+antSpecData.pcId);
    if ($('#antSpecTable').length !== 0) {
    	antSpecTable= $('#antSpecTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#antSpecTable').hideMask();
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
                url: 'derusting/queryAntSpec',
                type:'post',
                data: function(){
                   return antSpecData;
                },
                dataSrc: ''
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
				{"data":"dpPipeSize"},
				{"data":"dpNum"}
				
			],
			columnDefs: [{
			}]
        });
    }
};

var handlederusting=function(){
	"use strict";
	if($('#derustingTable').length !== 0){
		derustingtable= $('#derustingTable').on( 'init.dt',function(){
		$(this).bindDTSelected(trSelectedBack,true);
		$('#derustingTable_filter input').attr('placeholder','施工工序');
		showReport1();
		//隐藏遮罩
		$("#derustingTable").hideMask();
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
	        /*serverSide: true, 
	        ajax: {
	            url: 'derusting/queryProjectList',
	            type:'post',
	            data: function(d){
	               	$.each(checkListData,function(i,k){
	               		d[i] = k;
	               	});
	               	
	            },
	            datasrc: 'data'
	        },*/
	        ajax: 'projectjs/inspection/json/valvePit.json',
	        responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
	        select: true,
	        columns: [
	          	{"data":"pcId"},      
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
	//t.cgetDetail('derustingForm','derusting/viewCheckList','',queryBack);
};

var queryBack=function(){
	showReport1();
}

//查询监听
var  searchMonitor=function(){
	//基础条件查询添加监听
	$('#derustingTable_filter input').on('change',function(){
		var process = $('#derustingTable_filter input').val();
		checkListData = {};
		var pcDesId=$('#pcDesId').val();
		checkListData={'process':process,'pcDesId':pcDesId,'projId':projId};
		$('#derustingTable').cgetData(true,derustingCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#derustingTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

var derustingCallBack=function(){
	var len = $('#derustingTable').DataTable().ajax.json().data ? $('#derustingTable').DataTable().ajax.json().data.length : $('#derustingTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.addClear').val('');
		$('#pcIdNew').val('');
		 //$(':input','#derustingForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
	//TO-DO
	//清空签字
	$(".clear-sign").click();
	showReport1();
}

//增加监听
var addMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		$('#derustingForm').toggleEditState(true);
		$(".editBtn").removeClass("hidden");
		//$('ul.nav-tabs>li.active').removeClass("active");
		$('#signTab').tab("show");
		//清空要增加的数据项
		$('.addClear').val('');
		//清空签字
		$('.clear-sign').click();
	});
}

//修改监听
var modifyMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		var len=$("#derustingTable").find("tr.selected").length;
		if(len>0){
			$('#derustingForm').toggleEditState(true);
			$(".editBtn").removeClass("hidden");
			//切换页签
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

var valvePit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handlederusting();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab,#auditTab,#signTab").on("shown.bs.tab",function(){
        		if($(this).is($("#listTab,#signTab"))){
        			if($(this).is($('#listTab'))){
        				if(!$.fn.DataTable.isDataTable('#derustingTable')){
            				handlederusting();
            			}else{
            				$('#derustingTable').cgetData(true);
            				//隐藏按钮
            				$(".editBtn").addClass("hidden");
            				//不可编辑
            				$('#derustingForm').toggleEditState(false);
            				showReport1();
            			}
        			}else{
        				if(trSData.derustingTable.json && $('#pcId').val()!=""){
        					showReport1();
        				}else{
        					//清空修改部分
        					$('.addClear').val('');
        					//清空签字
        					$('.clear-sign').click();
        					showReport3();
        				}
        			}
        		}else{
        			$("#derustingRecordForm").styleFit();
        			if(!$.fn.DataTable.isDataTable('#derustingRecordTable')){
        				handleDerusting();
        			}else{
           				$('#derustingRecordTable').cgetData();
           				showReport2();
        			}
        		}
        	});
        }
    };
}();




