var imcTable; //待签分包工程列表
var searchData={};	   //查询条件
var projNameLength;   //显示工程名的长度
//判断是否是手机端，如果是手机端工程名字显示8位，如果是电脑则显示10位
if(_inApk){
	projNameLength=8;
}else{
	projNameLength=10;
}
var constructionUnitData={};
/**
 * 加载工程列表
 */
var handleSubContract = function() {
	'use strict';
	searchData.flag="0";//未推送的
    if ($('#imcTable').length !== 0) {
    	imcTable=$('#imcTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#sub_contract_panel_box').cgetContent('intelligentMeterCon/viewPage');
   			//隐藏遮罩
   			$('#imcTable').hideMask();
   			$('#imcTable_filter input').attr('placeholder','工程编号');
   			//搜索监听
   			searchMonitor();
   			//签订监听
   			signMonitor();
   			setTimeout(function(){
   			    $("#imcTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
                 { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                 { text: '签订', className: 'btn-sm btn-query business-tool-btn signBtn' }
                 ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'intelligentMeterCon/queryImc',
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
				 {'data':'projStatusDes'},
				 {'data':'flag'}
			 ],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 },{
				 'targets':2,
				 //长字符串截取方法
				 render: $.fn.dataTable.render.ellipsis({
					 length: projNameLength, 	//截取多少字符（或汉字）
					 end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				 })
			},{
				 'targets':4,
				 'render': function ( data, type, row, meta ){
					 if(data=='1'){
						 return "已推送";
					 }else{
						 return "未推送";
					 }
				}
			},{
				"targets":3,
				 "orderable":false
			},{
				"targets":4,
				 "orderable":false
			}],
			 fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
					if(aData.overdue){
						$(nRow).addClass("expired-proect");
					}
			}
        });
    }
};

var changePayTypeFunc = function(){
	if($("#payType").val()=="1"||$("#payType").val()=="3"||$("#payType").val()=="7"){
    	//首付款录入
    	//$('.firstPayment').show();
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').addClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    	$("#firstPayment").val($("#totalCost").val());
    	$("#secondPayment").val("");
    	$("#thirdPayment").val("");
    	$("#imcDetailForm").styleFit();
    }else if($("#payType").val()=='2' || $("#payType").val()=="4"){
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    	$("#firstPayment").val((new Number($("#totalCost").val())*0.5).toFixed(2));
    	$("#secondPayment").val((new Number($("#totalCost").val())*0.5).toFixed(2));
    	$("#thirdPayment").val("");
    	$("#imcDetailForm").styleFit();
    }else if($("#payType").val()=="6"){
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    	$("#thirdPayment").val("");
    	$("#imcDetailForm").styleFit();
    }else {
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').removeClass("hidden");
    	$("#firstPayment").val((new Number($("#totalCost").val())*0.5).toFixed(2));
    	$("#secondPayment").val((new Number($("#totalCost").val())*0.45).toFixed(2));
    	$("#thirdPayment").val((new Number($("#totalCost").val())*0.05).toFixed(2));
    	$("#imcDetailForm").styleFit();
    }
}
/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$('.searchBtn').on('click',function(){
		var url = '#intelligentMeterCon/projectSearchPopPage';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#imcTable_filter input').on('change',function(){
		var projNo = $('#imcTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#imcTable').cgetData(true,subTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#imcTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};
/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	searchData = $('#searchForm_imc').serializeJson();
	var projNo = $('#imcTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#imcTable').cgetData(true,subTableCallBack); 
}

var subTableCallBack=function(){
	var len = $('#imcTable').DataTable().ajax.json().data ? $('#imcTable').DataTable().ajax.json().data.length : $('#imcTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#imcDetailForm')[0].reset();
		 $(':input','#imcDetailForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}

//签订监听
var signMonitor=function(){
	$('.signBtn').off('click').on('click',function(){
		if($('#imcTable').find('tr.selected').length>0){
            $(".selectDisabled").removeClass("disabled")
			$('#imcDetailForm').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要签订分包的工程记录！');
		}
	});
};

/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$('.editbtn').addClass('hidden');
	$(".selectDisabled").addClass("disabled")
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('imcDetailForm','intelligentMeterCon/viewIntelligentMeterCon','',cgetDetailBack);
}

/**详述回调*/
var cgetDetailBack = function(data){
	//合同签订日期
	if(data.imcId==''||data.imcId==null){
		$("#imcSignDate").val(format(data.imcSignDate,"default"));
	}
	
	var src1=$("#projNo").val();
	if($("#imcNo").val()==""){
		$("#imcNo").val(src1.substring(0,4)+"02"+src1.substring(6,14));
	}
	//付款方式
	changePayTypeFunc();
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