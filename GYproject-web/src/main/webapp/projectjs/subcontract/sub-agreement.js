var subConstractTable; //待签分包工程列表
var searchData={};	   //查询条件
var constructionUnitData={};

/**
 * 加载工程列表
 */
var handleSubContract = function() {
	'use strict';
    if ($('#subContractTable').length !== 0) {
    	subConstractTable=$('#subContractTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#sub_contract_panel_box').cgetContent('subAgreement/viewPage');
   			//隐藏遮罩
   			$('#subContractTable').hideMask();
   			$('#subContractTable_filter input').attr('placeholder','工程编号');
   			//搜索监听
   			searchMonitor();
   			//签订监听
   			signMonitor();
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
                 //{ text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                 { text: '签订', className: 'btn-sm btn-query business-tool-btn signBtn' }
                 ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'subAgreement/queryProject',
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
				 {'data':'projId',className:'none never'}, 
	  			 {'data':'projNo'}, 
				 {'data':'projName'},
				 {'data':'projStatusDes'}
			 ],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
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
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('subContractDetailform','subAgreement/viewSubContract','',cgetDetailBack); 
}

/**详述回调*/
var cgetDetailBack = function(){
	scScope = $('#scScope').val();
	scType = $('#scType').val();
	if(scScope===null || scScope==""){
		//承包范围默认值
		$('#scScope').val('沟槽开挖、回填、余土外运等，管线安装、管件连接、阀门安装、调压、计量设备安装、试压、吹扫、竣工交验、配合通气等设计图纸所涉及的全部工作内容');
	}
	if(scType===null || scType==""){
		//承包方式默认值
		$('#scType').val('包人工、包安全、包质量、包工期和发包人临时指定的工作等');
	}
}

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$('.searchBtn').on('click',function(){
		var url = '#subContract/projectSearchPopPage';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#subContractTable_filter input').on('change',function(){
		var projNo = $('#subContractTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#subContractTable').cgetData(true,subTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#subContractTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};

/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	searchData = $('#searchForm_sub').serializeJson();
	var projNo = $('#subContractTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#subContractTable').cgetData(true,subTableCallBack); 
}

//签订监听
var signMonitor=function(){
	$('.signBtn').off('click').on('click',function(){
		if($('#subContractTable').find('tr.selected').length>0){
			$('#subContractDetailform').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要签订分包的工程记录！');
		}
	});
};

var subTableCallBack = function(){
	var len = $('#subContractTable').DataTable().ajax.json().data ? $('#subContractTable').DataTable().ajax.json().data.length : $('#subContractTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#subContractDetailform')[0].reset();
		 $(':input','#subContractDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	}
}


/**
 * 初始化工程列表
 */
var subContract = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handleSubContract();
        	});
        }
    };
}();

