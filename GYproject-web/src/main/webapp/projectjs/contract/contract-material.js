var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var constractTable; //工程列表
var chargeTable;
var searchData={};  //查询条件
/**
 * 加载工程列表
 */
var handleConstructContract = function() {
	'use strict';
    if ($('#constructContractTable').length !== 0) {
    	constractTable= $('#constructContractTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#construct_contract_panel_box').cgetContent('contractMaterial/viewPage');
   			//隐藏遮罩
   			$('#constructContractTable').hideMask();
   			$('#constructContractTable_filter input').attr('placeholder','工程编号');
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//登记监听
   			signMonitor();
   			setTimeout(function(){
   			    $("#constructContractTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
                 { text: '查询', className: 'btn-sm btn-query searchBtn' },
                 { text: '登记', className: 'btn-sm btn-query signBtn' }
             ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'contractMaterial/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
             },
             //ajax: 'projectjs/contract/json/construct_contract.json',
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
				 {'data':'materialDes'},
				 {"data":"isSpecialSign",className:"none never",
					 "createdCell": function (td, cellData, rowData, row, col) {
						 if(cellData=='1'){
							 $(td).parent().children().css("color", "rgb(255, 99, 95)");
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
						length: showLength, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},
				{
					"targets":3,
					 "orderable":false
				}
			 ],
			 fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
					if(aData.overdue){
						$(nRow).addClass("expired-proect");
					}
				}
			 
        });
    }
};

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$('.searchBtn').off('click').on('click',function(){
		var url = '#contractMaterial/constractSearchPopPage';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#constructContractTable_filter input').on('change',function(){
		var projNo = $('#constructContractTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#constructContractTable').cgetData(true,contractCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#constructContractTable_filter input').on('keyup', function(event) {
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
	console.log(searchData);
	var projNo= $('#constructContractTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#constructContractTable').cgetData(true,contractCallBack);  
}

var contractCallBack=function(){
	var len = $('#constructContractTable').DataTable().ajax.json().data ? $('#constructContractTable').DataTable().ajax.json().data.length : $('#constructContractTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#contractDetailform')[0].reset();
		 //保存按钮隐藏
		 $('.editbtn').addClass('hidden');
		 $('#contractDetailform').toggleEditState(false);
		 $(':input','#contractDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}

/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$('#contractDetailform') && $('#contractDetailform').formReset();
    $(".selectDisabled").addClass("disabled");
	$('.editbtn').addClass('hidden');
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('contractDetailform','contractMaterial/viewContract','',dataBack); 
}
function dataBack(data){
	var payType = data.payType;
	if($("#contractType").val()=='11'){
		if($("#hoseAmount").val()==''){
			 $("#hoseAmount").val("2230.00");
		}
		$(".not-resident").addClass("hidden");
		$(".resident").removeClass("hidden");
	}else{
		$(".resident").addClass("hidden");
		$(".not-resident").removeClass("hidden");
	}
	if(payType=="1"||payType=="3"||payType=="7"){
    	//首付款录入
    	//$('.firstPayment').show();
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').addClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    	$("#secondPayment").val("");
    	$("#thirdPayment").val("");
    	$("#contractDetailform").styleFit();
    }else if(payType=="2"||payType=="4"||payType=="6"){
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    	$("#thirdPayment").val("");
    	$("#contractDetailform").styleFit();
    }else{
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').removeClass("hidden");
    	$("#contractDetailform").styleFit();
    }
	if($(".conNo").val()==''){
		$(".conNo").val($("#projNo").val());
	}
	$("#payType").empty();
	if($("#contractType").val()){
		var selectEl = $("#contractType"),
	    index = selectEl[0].selectedIndex,
	    selectOption = selectEl[0].options[index];
		var sedata = $(selectOption).attr("value");
	}else{
		var sedata = '-1';
	}
	$.ajax({
		type: 'post',
		url: 'constructContract/queryPayType?id='+sedata,
		dataType: 'json',
		success: function(data){
//				$("#payType").append('<option value=""></option>');
			$.each(data,function(n, v){
	            if(payType==v.ptId){
	            	$("#payType").append('<option value='+v.ptId+' selected >' + v.payTypeDes + '</option>');
	            }else{
	            	 $("#payType").append('<option value='+v.ptId+'>' + v.payTypeDes + '</option>');
	            }
	        });
			if($("#deptDivide").val()==$("#customerServiceSenter").val()){
				$("#payType option[value='6']").remove();
			}
			if($("#conId").val()==''){
				$("#payType").removeAttr("disabled");
				$("#payType option:first").prop("selected", 'selected');
				$("#payType").attr("disabled","disabled");
				$("#payType").change();
			}
		},
		error: function(data){
			console.warn("付款方式选框查询失败");
		}
	});
	if($("#contractMethod").val()=='4'){
		$(".REMARK").removeClass("hidden");
	}else{
		$(".REMARK").addClass("hidden");
	}
	if($("#deptDivide").val()==$("#modificationGroup").val()){
		$("#firstPayment").removeClass("field-not-editable");
		$("#firstPayment").addClass("field-editable");
		$("#secondPayment").removeClass("field-not-editable");
		$("#secondPayment").addClass("field-editable");
	}else{
		$("#secondPayment").removeClass("field-editable");
		$("#secondPayment").addClass("field-not-editable");
		$("#firstPayment").removeClass("field-editable");
		$("#firstPayment").addClass("field-not-editable");
	}
	$("#contractDetailform").styleFit();
}
/**
 * 登记按钮监听方法
 */
var signMonitor=function(){
	$('.signBtn').off('click').on('click',function(){
		if($('#constructContractTable').find('tr.selected').length>0){
			//表单可编辑
			$('#contractDetailform').toggleEditState(true);
			//按钮显示
			/*$('#materialRemark').toggleEditState(true);
			$('#materialIsRegister').toggleEditState(true);*/
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要签订合同的工程记录！');
		}
	});
};
/**
 * 初始化工程列表
 */
var constructContract = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){       
        		handleConstructContract();
        	});
        }
    };
}();