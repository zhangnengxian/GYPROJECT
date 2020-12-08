var constractTable; //工程列表
var chargeTable;
var searchData={};  //查询条件
var detailSearchData={};
var scaleTable;
/**
 * 加载工程列表
 */
var handleConstructContract = function(){
	'use strict';
    if ($('#constructContractTable').length !== 0) {
    	constractTable= $('#constructContractTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#construct_contract_panel_box').cgetContent('constructContract/viewPage');
   			//隐藏遮罩
   			$('#constructContractTable').hideMask();
   			$('#constructContractTable_filter input').attr('placeholder','工程编号');
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//签订监听
   			signMonitor();
   			
   			setTimeout(function(){
   			    $("#constructContractTable").DataTable().columns.adjust();
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
                 { text: '签订', className: 'btn-sm btn-query signBtn' }
             ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'constructContract/queryProject',
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
				 {'data':'confirmTotalCost',className:'text-right'},
				 {'data':'projStatusDes'},
				 {"data":"workDayDto"},
				 {"data":"signBack",
						className:"none never",
						"createdCell": function (td, cellData, rowData, row, col) {
							if(cellData==$("#notThrough").val()){
								$(td).parent().css("background", "rgb(255, 219, 219)");
								//$(td).attr("id",row);
							}
						}
						},
				{"data":"overdue", className:"none never"}		
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
				 'targets':3,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							return parseFloat(data).toFixed(2);
						}else{
							return data;
						}
					},
			 	},{
					"targets":4,
					 "orderable":false
				},{
					"targets":5,
					 "orderable":false,
					 "render":function(data,type,row,meta){
						 if(data!=null){
							 return data.haveDays;
						 }else{
							 return 0;
						 }
					 }
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
		defaultValue();
		var url = '#constructContract/constractSearchPopPage';
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
	var projNo = $('#constructContractTable_filter input').val();
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
	$("#changeFlag").val("0");//控制户数和每户金额的change事件，若没保存过合同金额change事件有效，若保存过则change事件无效
	//$(':input','#contractDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	// $('#contractDetailform').formReset();
    $(".selectDisabled").addClass("disabled");
	$('.editbtn').addClass('hidden');
	$("#paymentRatio1").attr("readonly",true);
	$("#paymentRatio2").attr("readonly",true);
	$("#paymentRatio3").attr("readonly",true);         
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('contractDetailform','constructContract/viewContract','',dataBack); 
}
function dataBack(data){
	var payType = data.payType,
		conType = data.contractType,
        isSpecial = data.isSpecial;
	var contractType = data.contractType;
	detailSearchData.projLtypeId = data.projectType;
	detailSearchData.projId = data.projId;
	//判断是否显示工程规模，如果是民用的则显示
	if(data.projectType == "11"){
		$(".scaleTableForm").removeClass("hidden");
	}else{
		$(".scaleTableForm").addClass("hidden");
	}
	if(!isSpecial){
        $("[name='isSpecial'][value='0']").attr("checked",true);
	}
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
        $(".payPlat").addClass("hidden");
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').addClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    	$("#secondPayment").val("");
    	$("#thirdPayment").val("");
    	$("#contractDetailform").styleFit();
    }else if(payType=="2"||payType=="4"||payType=="6"){
        if (isSpecial == "1"){
            $(".payPlat").removeClass("hidden");
            $('.firstPayment').removeClass("hidden");
            $('.secondPayment').removeClass("hidden");
            $('.thirdPayment').addClass("hidden");
            $("#thirdPayment").val("");
            $("#contractDetailform").styleFit();
        }else {
            $(".payPlat").addClass("hidden");
            $('.firstPayment').removeClass("hidden");
            $('.secondPayment').removeClass("hidden");
            $('.thirdPayment').addClass("hidden");
            $("#thirdPayment").val("");
            $("#contractDetailform").styleFit();
        }
    }else{
        $(".payPlat").addClass("hidden");
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').removeClass("hidden");
    	$("#contractDetailform").styleFit();
    }
	if($(".conNo").val()==''){
		$(".conNo").val($("#projNo").val());
	}
	$("#payType").empty();
	var sedata;
	if($("#contractType").val()){
		if(isSpecial=="1"){
            sedata ="21";
		}else{
			// var selectEl = $("#contractType"),
			// index = selectEl[0].selectedIndex,
			// selectOption = selectEl[0].options[index];
			// sedata = $(selectOption).attr("value");
            sedata = conType;
		}
	}else{
		sedata = '-1';
	}
	$.ajax({
		type: 'post',
		url: 'constructContract/queryPayType?id='+sedata,
		dataType: 'json',
		success: function(data){
				$("#payType").append('<option value=""></option>');
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
// 	defaultValue();
	/*if($("#contractMethod").val()=='4'){
		$(".REMARK").removeClass("hidden");
	}else{
		$(".REMARK").addClass("hidden");
	}*/
	if($("#contractType").val()=='21'||$("#contractType").val()=='31'){
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
	if($.fn.DataTable.isDataTable('#scaleTable')){
		//初始化过
		$("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
	}else{
		sacletableinit();
	
	}
	//判断付款方式显示相应的输入框--开始
	if(payType=='3' ||payType=='1'  || payType=='7'){ //一次付清
		$(".paymentRatio1").removeClass("hidden");
    	$(".paymentRatio2").addClass("hidden");
    	$(".paymentRatio3").addClass("hidden");
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').addClass("hidden");
    	$('.thirdPayment').addClass("hidden");
	}else if(payType=='4' || payType=='6' || payType=='2'){ //二次付清
		$(".paymentRatio1").removeClass("hidden");
    	$(".paymentRatio2").removeClass("hidden");
    	$(".paymentRatio3").addClass("hidden");
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').addClass("hidden");   
	}else if(payType=='5'){ //三次付清
		$(".paymentRatio1").removeClass("hidden");
    	$(".paymentRatio2").removeClass("hidden");
    	$(".paymentRatio3").removeClass("hidden");
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').removeClass("hidden");
	}	//判断付款方式显示相应的输入框--结束
}
var scaleTableCallBack = function(){
	$('#scaleTableForm').toggleEditState(false);
}
/**
 * 签订按钮监听方法
 */
var signMonitor=function(){
	$('.signBtn').off('click').on('click',function(){
		if($('#constructContractTable').find('tr.selected').length>0){
			$("#contractDetailform a").attr("disabled",false);
            $(".selectDisabled").removeClass("disabled");
			$("#changeFlag").val("1");//使户数和每户金额change事件生效
			//表单可编辑
			$('#contractDetailform').toggleEditState(true);
			$('#scaleTableForm').toggleEditState(true);
			var payType=$("#payType").val();
			//--开始--*付款方式，分期付款1、3、7表示一次性付清，2、6、4表示二次付清，5表示三次付清,当点击签订按钮时若是两次付清，则第首付比列按钮可以编辑，若是三次付清则是首付比例按钮和阶段款比例1可以编辑
		    if(payType=='2' || payType=='4' || payType=='6'){
		    	$("#paymentRatio1").attr("readonly",false);
		    }else if(payType=='5'){
		    	$("#paymentRatio1").attr("readonly",false);
	        	$("#paymentRatio2").attr("readonly",false);
		    }//--结束--
		    
			//按钮显示
			$('.editbtn').removeClass('hidden');
            if(!$("#signDate").val()){
                var sysDate = timestamp($("#sysDate").val());
                $("#signDate").val(format(sysDate,"default"));
            }
			defaultValue();
		}else{
			alertInfo('请选择要签订合同的工程记录！');
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
/**
 * 初始化规模列表
 */
var sacletableinit = function() {
	"use strict";
    if ($('#scaleTable').length !== 0) {
    	scaleTable= $('#scaleTable').on( 'init.dt',function(){
    		$('#scaleTable').hideMask();
	        $('#scaleTableForm').toggleEditState(false);
	        inputMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [],
            /*ajax: 'projectjs/accept/json/scale.json',*/
            //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
            	url: 'constructContract/queryScaleDetail',
            	type:'post',
            	data: function(d){
                  	$.each(detailSearchData,function(i,k){
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
            },//自适应
            columns: [
	  			{"data":"projStypeDes", responsivePriority: 1},
	  			{"data":"tonnage",className:"text-right", responsivePriority: 3},
	  			{"data":"searNum",className:"text-right", responsivePriority: 6},
	  			{"data":"num",className:"text-right", responsivePriority: 5},
	  			{"data":"houseNum",className:"text-right", responsivePriority: 4},
	  			{"data":"gasConsumption",className:"text-right", responsivePriority: 7}
			],
			columnDefs: [{
				"targets":0,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 8, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				targets: 1,
				/*
				 * render属性
				 * 方法携带四个参数
				 * data: 该单元格的原始数据，也就是默认显示的那些数据
				 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
				 * row: 但前行的所有原始数据
				 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
				 */
				render: function ( data, type, row, meta ) {
					if(type === "display"){
						if(data === null){
							data = "";
						}
						var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_tonnage' id='" + row.projStypeId + "_tonnage'  data-parsley-maxlength='16' data-parsley-type='number' value="+data+"></div>"
						return tdcon;
					}else{
						return data;
					}}
			    },{
			    	targets: 2,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_searNum' id='" + row.projStypeId + "_searNum' data-parsley-maxlength='16' data-parsley-type='integer' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}	
			    },{
			    	targets: 3,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_num' id='" + row.projStypeId + "_num'  data-parsley-maxlength='16' data-parsley-type='integer' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}	}
			    },{
			    	targets: 4,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_houseNum' id='" + row.projStypeId + "_houseNum' data-parsley-maxlength='9' data-parsley-type='integer' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}
			    },{
			    	targets: 5,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}else if(data !==null && data!==""){
								data = data.toFixed(2);
							}
							var  tdcon="<div class='tdInnerInput'><input type='hidden' name='"+row.projStypeId+"_projStypeId' id='"+row.projStypeId+"_projStypeId' value="+row.projStypeId+">" +
									    "<input class='hidden' name='" + row.projStypeId + "_projLtypeId' id='" + row.projStypeId + "_projLtypeId' data-parsley-maxlength='9' data-parsley-type='number' value="+row.projLtypeId+">" +
									    "<input class='hidden' name='" + row.projStypeId + "_projStypeDes' id='" + row.projStypeId + "_projStypeDes'   value="+row.projStypeDes+">"+
									    "<input class='hidden' name='" + row.projStypeId + "_scaleId' id='" + row.projStypeId + "_scaleId'   value="+row.scaleId+">"+
									    "<input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_gasConsumption' id='" + row.projStypeId + "_gasConsumption'  data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}
			    }],
			ordering:false
       });
   }
}
/**
 * 输入监听
 */
var inputMonitor = function(){
	$(".tdInnerInput input").on("change",function(){
		var name = $(this).attr("name");
		var checkBoxId = name.split("_")[0]+"_scaleId";
		if($(this).val()){
			$("#"+checkBoxId).attr("checked","checked");
		}else{
			 var input = $(this).parents("tr").find(".tdInnerInput input").not("[type='radio']");
			 for(var i=0;i<input.length;i++){
				 if(input.eq(i).val()){
					 return false;
				 }
			 }
			 $("#"+checkBoxId).removeAttr("checked");
		}
	});
}
//$(".attach-panel").initAttachOper({
//	auditInformation: {
//		tableid:'constructContractTable'
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
