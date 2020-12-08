
var constractTable; //工程列表
var chargeTable;
var searchData={};  //查询条件
/**
 * 加载合同列表
 */
var handleSupplementContract = function() {
	'use strict';
    if ($('#supplementalContractTable').length !== 0) {
    	constractTable= $('#supplementalContractTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#supplemental_contract_panel_box').cgetContent('supplementalContract/viewPage');
   			//隐藏遮罩
   			$('#supplementalContractTable').hideMask();
   			$('#supplementalContractTable_filter input').attr('placeholder','工程编号');
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//签订监听
   			signMonitor();
   			setTimeout(function(){
   			    $("#supplementalContractTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
        }).DataTable({
			language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '签订', className: 'btn-sm btn-query business-tool-btn signBtn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
			ajax: {
                url: 'supplementalContract/queryAdjustBudget',
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
                    {"data":"budgetId",className:'none never'}, 
      				{"data":"projNo"},
      				{"data":"projName"},
      				{"data":"budgetAdjustDate"},
      				{"data":"budgetTotalCost",className:"text-right"},
      				{"data":"isSignSuContrctDes"},
      				{"data":"isAudit",className:"none never",
						"createdCell": function (td, cellData, rowData, row, col) {
							if(cellData=='3'){
								$(td).parent().children().css("background", "rgb(255, 219, 219)");
							}
						}
      				}
      			],
  			columnDefs: [{
			 'targets': 0,
			 'visible':false
  			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 8, 	//截取多少字符（或汉字）
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
		var url = '#supplementalContract/supplementalContractSearchPopPage';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#supplementalContractTable_filter input').on('change',function(){
		var projNo = $('#supplementalContractTable_filter input').val();
		searchData.projNo = $("#supplementalContractTable_filter input").val();
		$('#supplementalContractTable').cgetData(false,contractCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#supplementalContractTable_filter input').on('keyup', function(event) {
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
	searchData = $('#searchForm_contract').serializeJson();
	var conNo = $('#supplementalContractTable_filter input').val();
	searchData.conNo = conNo;
	//列表重新加载
    $('#supplementalContractTable').cgetData(true,contractCallBack);  
}

var contractCallBack=function(){
	var len = $('#supplementalContractTable').DataTable().ajax.json().data ? $('#supplementalContractTable').DataTable().ajax.json().data.length : $('#supplementalContractTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#contractDetailform')[0].reset();
		 $(':input','#contractDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}

/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(':input','#contractDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	$('.editbtn').addClass('hidden');
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	trSData.t.cgetDetail('contractDetailform','supplementalContract/viewsSupplementalContract','',getDetailBack); 
}
var getDetailBack = function(json){
	var payType = json.payType
	$("#businessOrderId").val(json.scId);
	//时间
	 if($("#signDate").val()==""){
	     var sysDate = timestamp($("#sysDate").val());
	     $("#signDate").val(format(sysDate,"default"));
	 }
	if(json.isSignSuContrct=="1"){
		$(".signBtn").addClass("hidden");
	}else{
		$(".signBtn").removeClass("hidden");
	}
	 if($("#houseAmount").val()==""){
	     $("#houseAmount").val("2230");
	 }
	if($("#resident").val()==$("#scType").val()){
		$(".resident").removeClass("hidden");
		$(".not-resident").addClass("hidden");
	}else{
		$(".resident").addClass("hidden");
		$(".not-resident").removeClass("hidden");
	}
	$("#paymentRatio1").attr("readonly",true);
	$("#paymentRatio2").attr("readonly",true);
	$("#paymentRatio3").attr("readonly",true);       
	//判断付款方式显示相应的输入框--开始
	if(payType=='1'){ //一次付清
		$(".paymentRatio1").removeClass("hidden");
    	$(".paymentRatio2").addClass("hidden");
    	$(".paymentRatio3").addClass("hidden");
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').addClass("hidden");
    	$('.thirdPayment').addClass("hidden");
	}else if(payType=='2'){ //二次付清
		$(".paymentRatio1").removeClass("hidden");
    	$(".paymentRatio2").removeClass("hidden");
    	$(".paymentRatio3").addClass("hidden");
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').addClass("hidden");   
	}else if(payType=='3'){ //三次付清
		$(".paymentRatio1").removeClass("hidden");
    	$(".paymentRatio2").removeClass("hidden");
    	$(".paymentRatio3").removeClass("hidden");
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').removeClass("hidden");
	}if(payType==null || payType==''){
		$(".paymentRatio1").removeClass("hidden");
    	$(".paymentRatio2").addClass("hidden");
    	$(".paymentRatio3").addClass("hidden");
    	$('.firstPayment').addClass("hidden");
    	$('.secondPayment').addClass("hidden");
    	$('.thirdPayment').addClass("hidden");
	}	//判断付款方式显示相应的输入框--结束
}

/**
 * 签订按钮监听方法
 */
var signMonitor=function(){
	$('.signBtn').off('click').on('click',function(){
		if($('#supplementalContractTable').find('tr.selected').length>0){
			////--开始--*付款方式，分期付款1表示一次性付清，2表示二次付清，3表示三次付清,当点击签订按钮时若是两次付清，则第首付比列按钮可以编辑，若是三次付清则是首付比例按钮和阶段款比例1可以编辑
			var payType=$("#payType").val();
		    if(payType=='2'){
		    	$("#paymentRatio1").attr("readonly",false);
		    }else if(payType=='3'){
		    	$("#paymentRatio1").attr("readonly",false);
	        	$("#paymentRatio2").attr("readonly",false);
		    }//--结束--
			//表单可编辑
			$('#contractDetailform').toggleEditState(true);
			//按钮显示
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要签订补充协议的合同！');
		}
	});
};

/**
 * 初始化合同列表
 */
var supplementalContract = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){ 
        		handleSupplementContract();
        	});
        }
    };
}();
