var subConstractTable; //待签分包工程列表
var searchData={};	   //查询条件
var constructionUnitData={};
/**
 * 加载分包单位列表
 */
var handleSubContract = function() {
	'use strict';
    if ($('#constructionUnitTable').length !== 0) {
    	subConstractTable=$('#constructionUnitTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#construction-unit_panel_box').cgetContent('constructionUnit/viewPageConstructionUnit');
   			//隐藏遮罩
   			$('#constructionUnitTable').hideMask();
   			$('#constructionUnitTable_filter input').attr('placeholder','分包单位名称');
   			//搜索监听
   			searchMonitor();;
   			//修改监听
   			signMonitor();
   			//删除监听
   			deleteMonitor();
   			//增加监听
   			insertMonitor();
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
                 { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                 { text: '修改', className: 'btn-sm btn-query business-tool-btn signBtn' },
                 { text: '增加', className: 'btn-sm btn-query business-tool-btn insertBtn' },
                 { text: '删除', className: 'btn-sm btn-query business-tool-btn deleteBtn' }
                 ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'constructionUnit/queryConstructionUnit',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
             },
             //ajax: 'projectjs/subcontract/json/sub_contract.json',
             responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
             select: true,  //支持多选
             columns: [
				 {'data':'cuId',className:'none never'}, 
	  			 {'data':'cuName'}, 
				 {'data':'cuDirector'},
				 {'data':'cuPhone'}
			 ],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 }]
        });
    }
};

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$('.searchBtn').on('click',function(){
		var url = '#pricedBoq/viewConstructionUnitPop';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#constructionUnitTable_filter input').on('change',function(){
		var cuName = $('#constructionUnitTable_filter input').val();
		searchData = {};
		searchData.cuName = cuName;
		$('#constructionUnitTable').cgetData(true,constructionUnitTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#constructionUnitTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};

/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	searchData = $('#searchForm_constructionUnit').serializeJson();
	var cuNo = $('#constructionUnitTable_filter input').val();
	searchData.cuNo = cuNo;
	//列表重新加载
    $('#constructionUnitTable').cgetData(true,constructionUnitTableCallBack); 
}

var constructionUnitTableCallBack=function(){
	var len = $('#constructionUnitTable').DataTable().ajax.json().data ? $('#constructionUnitTable').DataTable().ajax.json().data.length : $('#constructionUnitTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#constructionUnitDetailform')[0].reset();
		 $(':input','#constructionUnitDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}

//修改监听
var signMonitor=function(){
	$('.signBtn').off('click').on('click',function(){
		if($('#constructionUnitTable').find('tr.selected').length>0){
			$('#constructionUnitDetailform').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要修改的分包单位！');
		}
	});
};

//增加监听
var insertMonitor=function(){
	$('.insertBtn').off('click').on('click',function(){
		//清除选中行样式
		$("#constructionUnitDetailform input, #constructionUnitDetailform textarea").val("");
		//切换可编辑状态
		$("#constructionUnitDetailform").toggleEditState(true);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
};

//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		var url = '#constructionUnit/deleteConstructionUnit';
		//加载弹屏
		
		if($('#constructionUnitTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除分包单位？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的分包单位！',
				accept: searchDone
			});
		}
	});
};

//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	$("#constructionUnitTable").deleteRow(0,"constructionUnit/deleteConstructionUnit",$("#constructionUnitTable").DataTable());
}

/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$('.editbtn').addClass('hidden');
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	trSData.t && t.cgetDetail('constructionUnitDetailform','constructionUnit/viewConstructionUnit',''); 
}


/**
 * 初始化工程列表
 */
var subContract = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	handleSubContract();
        }
    };
}();