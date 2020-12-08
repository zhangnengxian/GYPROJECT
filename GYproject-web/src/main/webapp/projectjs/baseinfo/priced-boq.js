var quantityStandardTable,pricedBoqTable1; //待签分包工程列表
var searchData={};	   //查询条件
var quantityStandardData={};
/**
 * 加载设计单位列表
 */
var handleSubContract = function() {
	'use strict';
    if ($('#pricedBoqTable').length !== 0) {
    	quantityStandardTable=$('#pricedBoqTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#priced-boq_panel_box').cgetContent('pricedBoq/viewPagePricedBoqRight');
   			//隐藏遮罩
   			$('#pricedBoqTable').hideMask();
   			//搜索监听
   			searchMonitor();
   			//修改监听
   			signMonitor();
   			//删除监听
   			deleteMonitor();
   			//增加监听
   			insertMonitor();
   			addMonitor();
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Brtip',
             buttons: [
                 { text: '新增版本', className: 'btn-sm btn-query business-tool-btn addBtn' },   
                 { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                 { text: '增加', className: 'btn-sm btn-query business-tool-btn insertBtn' },
                 { text: '修改', className: 'btn-sm btn-query business-tool-btn signBtn' },
                 { text: '删除', className: 'btn-sm btn-query business-tool-btn deleteBtn' }
                 ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:false,
             ajax: {
                url: 'pricedBoq/queryPricedBoq',
                type:'post',
                data: function(){
                   /*	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});*/
                	return searchData;
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
				 {'data':'qsId',className:'none never'}, 
	  			 {'data':'costTypeDes'}, 
				 {'data':'subitemName'}
			 ],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 }]
        });
    }
};
var handleSubContract1 = function() {
	'use strict';
    if ($('#pricedBoqTable1').length !== 0) {
    	pricedBoqTable1=$('#pricedBoqTable1').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#pricedBoqTable1').hideMask();
   			saveMonitor();
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [12],
             dom: 'Brtip',
             buttons: [
                 ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:false,
             responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
             select: true,  //支持多选
             columns: [
				 {'data':'qsId',className:'none never'}, 
	  			 {'data':'costTypeDes'}, 
				 {'data':'subitemName'},
				 {"data":"unitPrice", className:"text-right"},
				 {"data":"unit", className:"text-center"}
			 ],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 }
			 
			 ]
        });
    }
};

var addMonitor = function(){
	$('.addBtn').on('click',function(){
		$(".pricedBoqTable1").removeClass('hidden');
		$('.newEditBtn').removeClass("hidden");
		$('.insertBtn,.signBtn,.deleteBtn,.addBtn').addClass("hidden");
		$('.editbtn').addClass("hidden");
		if($.fn.DataTable.isDataTable('#pricedBoqTable1')){
//			$('#pricedBoqTable1').cgetData(false);  //列表重新加载
			pricedBoqTable1.rows().remove().draw();
			pricedBoqTable1.rows.add(quantityStandardTable.ajax.json()).draw();
		}else{
			handleSubContract1();
			pricedBoqTable1.rows.add(quantityStandardTable.ajax.json()).draw();
		}
		
	});
}
var saveMonitor = function(){
	$('.signBtn1').on('click',function(){	
	$('.insertBtn,.signBtn,.deleteBtn').removeClass("hidden");
	$('.insertBtn1,.signBtn1,.deleteBtn1').addClass("hidden");
	});
}

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$('.searchBtn').on('click',function(){
		var url = '#pricedBoq/viewPricedBoqPop';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#costType').on('change',function(){
		var costType = $('#costType').val();
		searchData = {};
		searchData.costType = costType;
		$('#pricedBoqTable').cgetData(true,pricedBoqTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#costType').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};

/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	searchData = $('#searchForm_pricedBoq').serializeJson();
	var subitemName = $('#searchForm_pricedBoq input').val();
	searchData.subitemName = subitemName;
	//列表重新加载
    $('#pricedBoqTable').cgetData(true,pricedBoqTableCallBack); 
}

var pricedBoqTableCallBack=function(){
	var len = $('#pricedBoqTable').DataTable().ajax.json().data ? $('#pricedBoqTable').DataTable().ajax.json().data.length : $('#pricedBoqTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#pricedBoqDetailform')[0].reset();
		 $(':input','#pricedBoqDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}

//修改监听
var signMonitor=function(){
	$('.signBtn').off('click').on('click',function(){
		if($('#pricedBoqTable').find('tr.selected').length>0){
			$('#pricedBoqDetailform').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要修改的工程量标准！');
		}
	});
};

//增加监听
var insertMonitor=function(){
	$('.insertBtn').off('click').on('click',function(){
		//清除选中行样式
		$("#pricedBoqDetailform").formReset("#costType");
		$("#qsId").val("");
//		$("#pricedBoqDetailform input, #pricedBoqDetailform textarea").val("");
		//切换可编辑状态
		$("#pricedBoqDetailform").toggleEditState(true);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
};

//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#pricedBoqTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除工程量标准？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的工程量标准！',
				accept: searchDone
			});
		}
	});
};

//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	$("#pricedBoqTable").deleteRow(0,"pricedBoq/deletePricedBoq",$("#pricedBoqTable").DataTable());
}

/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	trSData.t && t.cgetDetail('pricedBoqDetailform','',''); 
	$('.editbtn').addClass('hidden');
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	if(t.closest("table").attr("id") === "pricedBoqTable1"){
		$("#pricedBoqTable").DataTable().row('.selected').deselect();
		trSData.pricedBoqTable = {};
		//切换可编辑状态
		$("#pricedBoqDetailform").toggleEditState(true);
	}else{
		$("#pricedBoqDetailform").toggleEditState(false);
		$("#pricedBoqTable1:visible").DataTable().row('.selected').deselect();
		trSData.pricedBoqTable1 = {};
	}
}


/**
 * 初始化工程列表
 */
var subContract = function () {
	'use strict';
    return {
        init: function () {
        	handleSubContract();
        }
    };
}();