var constractTable; //工程列表
var chargeTable;
var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData={};  //查询条件
/**
 * 加载智能表合同列表
 */
var handleImcContract = function() {
	'use strict';
    if ($('#intelligentMeterConTable').length !== 0) {
    	constractTable= $('#intelligentMeterConTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#construct_contract_panel_box').cgetContent('intelligentMeterConModify/viewPage');
   			//隐藏遮罩
   			$('#intelligentMeterConTable').hideMask();
   			$('#intelligentMeterConTable_filter input').attr('placeholder','工程编号');
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//修改监听
            modifyMonitor();
        	setTimeout(function(){
   			    $("#intelligentMeterConTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
        	//跳转链接
   			if (crossPageBus) {
   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   					//跳转后销毁对象
   				crossPageBus = null
   			}
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
                 { text: '查询', className: 'btn-sm btn-query searchBtn' },
                 { text: '修改', className: 'btn-sm btn-query modifyBtn' }
             ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'intelligentMeterConPrint/queryImcContract',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
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
                {'data':'projId',className:'none never'},
                {"data":"imcNo"},
                {"data":"projName"},
                {"data":"imcSignDate"},
                {"data":"totalCost",className:"text-right"},
                {'data':'modifyStatusDes'}
            ],
            columnDefs: [{
                "targets": 0,
                "visible":false
            },{
                "targets":2,
                //长字符串截取方法
                render: $.fn.dataTable.render.ellipsis({
                    length: showLength, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            },{
                'targets':4,
                "render": function ( data, type, row, meta ) {
                    if(data!=="" && data!==null){
                        return parseFloat(data).toFixed(2);
                    }else{
                        return data;
                    }
                },
            }]
        });
    }
};

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$('.searchBtn').off('click').on('click',function(){
		defaultValue();
		var url = '#intelligentMeterConModify/imcModifySearchPopPage';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#intelligentMeterConTable_filter input').on('change',function(){
		var projNo = $('#intelligentMeterConTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#intelligentMeterConTable').cgetData(true,contractCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#intelligentMeterConTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});			
};

/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	//查询条件
	searchData = $('#searchForm_imc').serializeJson();
	var projNo = $('#intelligentMeterConTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#intelligentMeterConTable').cgetData(true,contractCallBack);  
}

var contractCallBack=function(){
	var len = $('#intelligentMeterConTable').DataTable().ajax.json().data ? $('#intelligentMeterConTable').DataTable().ajax.json().data.length : $('#intelligentMeterConTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#imcDetailForm')[0].reset();
		 //保存按钮隐藏
		 $('.editbtn').addClass('hidden');
		 $('#imcDetailForm').toggleEditState(false);
		 $(':input','#imcDetailForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}

var changePayTypeFunc1 = function(){
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
};

/**详述回调*/
var cgetDetailBack = function(data){
    //审核中的不可修改
    if(data.modifyStatus=='0'){
        $(".modifyBtn").addClass("hidden");
    }else{
        $(".modifyBtn").removeClass("hidden");
    }

    //合同签订日期
    if(data.imcId==''||data.imcId==null){
        $("#imcSignDate").val(format(data.imcSignDate,"default"));
    }

    var src1=$("#projNo").val();
    if($("#imcNo").val()==""){
        $("#imcNo").val(src1.substring(0,4)+"02"+src1.substring(6,14));
    }
    //付款方式
    changePayTypeFunc1();
}

/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
    $('.editbtn').addClass('hidden');
    //参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
    t.cgetDetail('imcDetailForm','intelligentMeterCon/viewIntelligentMeterCon','',cgetDetailBack);
}

/**
 * 修改按钮监听方法
 */
var modifyMonitor=function(){
	$('.modifyBtn').off('click').on('click',function(){
		if($('#intelligentMeterConTable').find('tr.selected').length>0){
			$("#changeFlag").val("1");//使户数和每户金额change事件生效
			//表单可编辑
			$('#imcDetailForm').toggleEditState(true);
			//按钮显示
			$('.editbtn').removeClass('hidden');
            // if(!$("#signDate").val()){
            //     var sysDate = timestamp($("#sysDate").val());
            //     $("#signDate").val(format(sysDate,"default"));
            //     // $("#signDate").val($("#sysDate").val());
            // }
			defaultValue();
			if($("#isCashFlow").val()!=null && $("#isCashFlow").val()!=''){
				$("#payType").addAttr("disabled");
				$("#payType").addAttr("readonly");
		    }else{
		    	$("#payType").removeAttr("disabled");
				$("#payType").removeAttr("readonly");
		    }
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
};
//默认值
function defaultValue(){
// 	if($("#conScope").val()===""){
// 		$("#conScope").val("燃气图纸所包含的全部范围  包工包料");
// 	}
} 



/**
 * 初始化智能表合同列表
 */
var imcContract = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){       
        		handleImcContract();
        	});
        }
    };
}();

//$(".attach-panel").initAttachOper({
//	auditInformation: {
//		tableid:'intelligentMeterConTable'
//	}
//});
/*
//收款操作
function chargeDone(){
	console.info(1);
	var data={};
	data.arId=$('#arId').val();
	data.cashFlow=$('#chargeForm').serializeJson();
	var dataString = JSON.stringify(data);	
	$('#chargeForm').gformSave(dataString, '', saveBack, false);
	return false;
}

$('.attach-panel').initAttachOper({
	charge: {
		callback: chargeDone
	}
});*/
