<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<div>
    <div class="toolBtn f-r p-b-10">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 chargeDone hidden editbtn" >保存</a>
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 cancelCharge hidden ">取消</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancleBtn hidden editbtn">放弃</a>
	</div>
	<div class="clearboth form-box p-b-10">
		<form class="form-horizontal" id="chargeForm" action="charge/insertCashFlow" data-parsley-validate="true">
			<input type="hidden" id="projId1" name="projId"  />
			<input type="hidden" id="arId" name="arId"  />
			<input type="hidden" id="cfId" name="cfId"  />
			<input type="hidden" id="cfFlag" name="cfFlag" />
			<input type="hidden" id="isValid" name="isValid" value='1' />
			<input type="hidden" id="colTypeEnum" value='${colTypeEnum}'>
			<input type="hidden" id="custId" name="custId" value="${custId}"/>
			<input type="hidden" id="overChange" name="overChange"  />
			<div class="form-group col-md-12">
				<label class="control-label" for="">工程名称</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" value="" id="projName1" name="projName" data-parsley-required="true"  />

				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">工程编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="projNo1" name="projNo"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">工程地点</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" value="" id="projAddr1" name="projAddr"/>
				</div>
			</div>
			<div class="form-group col-md-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		        	<textarea class="form-control input-sm field-not-editable" name="projScaleDes" rows="" cols="" data-parsley-maxlength="200"></textarea>
		        </div>
		    </div>
			<div class="form-group col-md-6">
				<label class="control-label" for="conNo">分合同编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="conNo" name="conNo"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="contractAmount">分合同金额</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="contractAmount" name="contractAmount"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label">是否智能表</label>
				<div>
					<label><input type="radio" class="field-not-editable" disabled="disabled" id="contractType1" name="contractType" value="1" > 是</label>
					<label><input type="radio" class="field-not-editable" disabled="disabled" id="contractType2" name="contractType" value="0" checked> 否</label>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="">分包单位</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="cuName"  name="cuName"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="cuCsr">分包法人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="cuCsr"  name="cuCsr"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="cuPhone">联系方式</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="cuPhone"  name="cuPhone"/>
				</div>
			</div>
			<div class="form-group col-md-6 paymentApply" >
				<label class="control-label" for="applyer">申请人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="applyer"  name="applyer"/>
				</div>
			</div>
			<div class="form-group col-md-6 paymentApply">
				<label class="control-label" for="applyDate">申请时间</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="applyDate"  name="applyDate"/>
				</div>
			</div>
			<!-- <div class="form-group col-md-12">
				<label class="control-label" for="">客户名称</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="custName"  name="custName"/>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="">单位地址</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" id="unitAddress"  name="unitAddress"/>
				</div>
			</div>-->
			<div class="form-group col-md-6 hidden">
				<label class="control-label" for="">开户行</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" id="openBank"  name="openBank"/>
				</div>
			</div>
			<div class="form-group col-md-6 hidden">
				<label class="control-label" for="">账号</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" id="cashAccount" name="cashAccount"  data-parsley-type="digits"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="increment">税率</label>
				<div>
					<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16">
					<a href="javascript:;" class="btn btn-sm btn-default">%</a>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">收付操作人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="staffName" name="staffName" />
				    <input type="hidden" name="cfOperator" id="cfOperator">
				</div>
			</div>
			
			<div class="form-group  col-md-6 clearboth">
				<label class="control-label" for="">付款类型</label>
				<div>
				<input type="text" class="form-control input-sm field-not-editable" id="cfTypeDes" name="cfTypeDes"  data-parsley-required="true" />
				<input type="hidden" id="cfType" name="cfType"  />
				</div>
			</div>
			
			<div class="form-group col-md-6">
				<label class="control-label" for="">金额</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="cfAmount" name="cfAmount"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			
			<!-- <div class="form-group col-md-12 isCharge">
				<label class="control-label" for="isCharge">付款</label>
				<div>
					<label><input type="radio" class="field-editable" id="charged1" name="isCharge" value="1" > 已付</label>
					<label><input type="radio" class="field-editable" id="charged2" name="isCharge" value="0" checked> 未付</label>
				</div>
			</div> -->
			<div class="form-group col-md-6 isBill hidden">
				<label class="control-label" for="invoice">票据类型</label>
				<div>
					<label><input type="radio" class="bill_type_btn" id="billType1"  name="billType" value="1" checked> 发票</label>
					<label><input type="radio" class="bill_type_btn" id="billType2"  name="billType" value="0" > 收据</label>
				</div>
			</div>

			<div class="form-group invoiceBtn col-md-6 isBill hidden">
				<label class="control-label" for="invoice">发票状态</label>
				<div>
					<label><input type="radio" class="is_Invoice_btn" id="invoice1"  name="invoice" value="1" > 已开</label>
					<label><input type="radio" class="is_Invoice_btn" id="invoice2"  name="invoice" value="0" checked> 未开</label>
				</div>
			</div>
			<div class="form-group col-md-6 recInvoiceBtn hidden">
				<label class="control-label" for="receiveInvoice">收据状态</label>
				<div>
					<label><input type="radio"  class="is_recInvoice_btn"  name="receiveInvoice" value="1" > 已收</label>
					<label><input type="radio"  class="is_recInvoice_btn"  name="receiveInvoice" value="0" checked> 未收</label>
				</div> 
			</div>
			
			<div class="form-group col-md-6 isInvoice recInvoice hidden">
		        <label class="control-label" for="invoiceAmount">金额（含税）</label>
		        <div>
		           <input type="text" class="form-control input-sm is_pub" id="invoiceAmount" name="invoiceAmount">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		    <div class="form-group col-md-6 isInvoice recInvoice hidden" >
		        <label class="control-label" for="invoiceNo">发票号</label>
		        <div>
		           <input type="text" class="form-control input-sm is_pub" id="invoiceNo"  name="invoiceNo">
		        </div>
		    </div>
		    <div class="form-group col-md-6 isInvoice hidden" >
		        <label class="control-label" for="invoiceDate">开票日期</label>
		        <div>
		           <input type="text" class="form-control input-sm datepicker-default is_Invoice timestamp" id="invoiceDate"  name="invoiceDate">
		        </div>
		    </div>
		    
			<div class="form-group col-md-6 recInvoice hidden" >
		        <label class="control-label" for="recInvoiceDate">收票日期</label>
		        <div>
		           <input type="text" class="form-control input-sm datepicker-default is_recInvoice timestamp" id="recInvoiceDate"  name="recInvoiceDate">
		        </div>
		    </div>
		  
		</form>
	</div>
	<ul class="nav nav-tabs p-t-5 p-l-5">
		<li class="active"><a href="#tab-1" data-toggle="tab" id="accrualsTad">应付流水</a></li>
		<li class=""><a href="#tab-2" data-toggle="tab" id="cashFlowTab">实付流水</a></li>
	</ul>
	<div class="tab-content p-l-0 p-r-0 p-t-5">
		<div class="tab-pane fade active in btn-top" id="tab-1" >
       		<table class="table table-hover table-striped table-bordered nowrap" width="100%" id="accrualsTable" >
       			<thead>
       			<tr>
       			    <th></th>
       				<th>操作时间</th>
        			<th>款项类型</th>
        			<th>应付金额</th>
        			<th>实付金额</th>
        			<th>收付标志</th>
        			<th>是否已开票</th>
        			<th>流水操作人</th>
       			</tr>
       			</thead>
       		</table>
		</div>
		<div class="tab-pane fade  btn-top" id="tab-2" >
       		<table class="table table-hover table-striped table-bordered nowrap" width="100%" id="cashFlowTable" >
       			<thead>
       			<tr>
       			    <th></th>
       				<th>操作时间</th>
        			<th>款项类型</th>
        			<th>实付金额</th>
        			<th>收付标志</th>
        			<th>付款状态</th>
        			<th>收付操作人</th>
        			<th>收款部门</th>
       			</tr>
       			</thead>
       		</table>
		</div>
	</div>		
</div>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
$("#chargeForm").styleFit();
$("#proPop1").removeClass("disabled");
var openBank,account;
    
trSelOk();
function trSelOk(){
	trSData.payAmountTable.t && trSData.payAmountTable.t.cgetDetail("chargeForm",'receiveAmount/viewDetail','',function(){
		$("#chargeForm").toggleEditState(true);
	});
	if(trSData.payAmountTable.json){
		$("#projId1").val(trSData.payAmountTable.json.projId);
	}

}

//日期datepicker
	$('.datepicker-default').datepicker({
        todayHighlight: true
    });

//初始化应付流水
var chargedatainit= function() {
	"use strict";
    if ($('#accrualsTable').length !== 0) {
    	if($("#projId1").val()==""){
    		$("#projId1").val("-1");
    	}
    	accrualsData.projId=$("#projId1").val();
    	accrualsTable= $('#accrualsTable').on( 'init.dt',function(){
   		//默认选中第一行
  		$(this).bindDTSelected(trAccrualsBack,false);
    	$('#accrualsTable').hideMask();
        }).DataTable({
        	language: language_CN,
           lengthMenu: [5],
           dom: 'Brtip',
           buttons: [
           ],
          //启用服务端模式，后台进行分段查询、排序
		   serverSide:true,
		   select: true,  //支持多选
	       ajax: {
	             url: 'payAmount/queryAccruRecord',
	             type:'post',
	             data: function(d){
	                   $.each(accrualsData,function(i,k){
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
           columns: [
                {"data":"arId",className:"none never"},
	  			{"data":"arDate",render:changeDate},
	  			{"data":"arTypeDes"},
	  			{"data":"arCost",className:"text-right"},
	  			{"data":"receiveAmount",className:"text-right"},
	  			{"data":"arFlagDes"},
	  			{"data":"invoice"},
	  			{"data":"staff.staffName"},
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},
			{
				"targets": 3,
				 render: function ( data, type, row, meta ) {
					if(type === "display"){
						if(data === null){
							data = "";
						}else {
							data = data.toFixed(2);
						}
					}
					return data;
				}
			},{
				"targets": 4,
				 render: function ( data, type, row, meta ) {
					if(type === "display"){
						if(data === null||data ==""){
							data = '0.00';
						}else {
							data = data.toFixed(2);
						}
					}
					return data;
				}
			},{
				"targets": 6,
				 render: function ( data, type, row, meta ) {
					if(data =="1"){
						data="已开";
					}else{
						data="未开";
					}
					return data;
				}
			},{
				"targets":2,
				 "orderable":false
			},{
				"targets":5,
				 "orderable":false
			},{
				"targets":6,
				 "orderable":false
			},{
				"targets":7,
				 "orderable":false
			}]
       });
   }
};
//点击应付流水记录，展示收款类型、金额
function trAccrualsBack(){
	$(".is_Invoice_btn").removeClass("disabled");
	reback();
	if(trSData.accrualsTable.t){
		$("#cfType").val(trSData.accrualsTable.json.arType);
		$("#cfFlag").val(trSData.accrualsTable.json.cfFlag);
		$("#cfTypeDes").val(trSData.accrualsTable.json.arTypeDes);
		$("#cfAmount").val((new Number(trSData.accrualsTable.json.arCost)-new Number(trSData.accrualsTable.json.receiveAmount)).toFixed(2));
		$("#arId").val(trSData.accrualsTable.json.arId);
		var billType=trSData.accrualsTable.json.billType;
		var invoice=trSData.accrualsTable.json.invoice;
		var receiveInvoice=trSData.accrualsTable.json.receiveInvoice;
        var contractType=trSData.accrualsTable.json.contractType;//智能表
		$('input:radio[name="billType"][value="'+billType+'"]').attr("checked",true);
		$('input:radio[name="invoice"][value="'+invoice+'"]').attr("checked",true);
		$('input:radio[name="receiveInvoice"][value="'+receiveInvoice+'"]').attr("checked",true);
        $('input:radio[name="contractType"][value="'+contractType+'"]').attr("checked",true);
		console.info(trSData.accrualsTable.json.invoice);
		//请款单信息
		var paymentApplay = trSData.accrualsTable.json.paymentApply;
		if(paymentApplay!=null){
			$("#applyDate").val(paymentApplay.applyDate);
			$("#applyer").val(paymentApplay.applyer);
		}
		//合同号
		if(trSData.accrualsTable.json.conNo){
			$("#conNo").val(trSData.accrualsTable.json.conNo);
			$("#scAmount").val(trSData.accrualsTable.json.scAmount);
		}
		if(trSData.accrualsTable.json.contractAmount){
			$("#contractAmount").val(trSData.accrualsTable.json.contractAmount);
		}
		changefun();
	}
}
chargedatainit();

//初始化收付流水
$("#cashFlowTab").on("shown.bs.tab",function(){
	 //reback();
	 changeFun(false);
	 cashFlowInit();
	 $(".editbtn").addClass("hidden");
	 $(".cancelCharge").addClass("hidden");
});
var cashFlowInit=function(){
	   if($("#projId1").val()==""){
		   $("#projId1").val("-1");
	    }
	   cashFlowData.projId=$("#projId1").val();
	if ( !$.fn.DataTable.isDataTable('#cashFlowTable')) {
		    cashFlowTable = $('#cashFlowTable').on( 'init.dt',function(){
			$(this).bindDTSelected(trSelectedBack1,false);
	    	$("#cashFlowTable").hideMask();
	    	
	     }).DataTable({
		    	 language: language_CN,
	            lengthMenu: [5],
	            dom: 'Brtip',
	            select: true,
	            serverSide:true,
	            ajax: {
	                url: 'payAmount/queryCashFlow',
	                type:'post',
	                data: function(d){
	                	$.each(cashFlowData,function(i,k){
	                		d[i] = k;
	                	});
	                	
	                },
	                dataSrc: 'data'
	            },
	            buttons: [ ],
	            responsive: {
	            	details: {
	            		renderer: function ( api, rowIdx, columns ){
	            			return renderChild(api, rowIdx, columns);
	            		}
	            	}
	            },
	            /*返回数据为list，list里相应的属性值如下，对应列即可*/
	            columns: [
							{"data":"cfId",className:"none never"},
							{"data":"cfDate",render:changeDate},
							{"data":"cfTypeDes"},							
							{"data":"cfAmount",className:"text-right"},
							{"data":"cfFlagDes"},
							{"data":"invoice"},
							{"data":"staff.staffName"},
							{"data":"department.deptName"}
						],
	            order: [[ 1, "asc" ]],
	            columnDefs: [ {
				      "targets": 0,
				      "visible":false
				    },
					{
						"targets": 3,
						 render: function ( data, type, row, meta ) {
							if(type === "display"){
								if(data === null){
									data = "";
								}else if(data !==null && data!==""){
									data = data.toFixed(2);
								}
							}
							return data;
						}
					},{
						"targets": 5,
						 render: function ( data, type, row, meta ) {
							 if(row.arFlag=="-1"){
		    						if(row.resultFlag=="0"){
		    							data = "已付款";
		    						}else if(row.arStatus=="2"){
		    							data = "已登记付款";
		    						}else if(row.arStatus=="3"){
		    							data = "已付款";
		    						}else{
		    							data = "未付款";
		    						}
		    					}
		    					return data;
						}
					},{
						"targets":2,
						 "orderable":false
					},{
						"targets":4,
						 "orderable":false
					},{
						"targets":5,
						 "orderable":false
					},{
						"targets":6,
						 "orderable":false
					},{
						"targets":7,
						 "orderable":false
					}]
	     });
	}else{
		$('#cashFlowTable').cgetData(false);
	}

};
//时间格式转换
function changeDate(e){
	return format(e,"all");
}

//点击应付流水标签（非初始化）
$("#accrualsTad").on("shown.bs.tab",function(){
	//reback();
	changeFun(true);
	$(".editbtn").addClass("hidden");
	$(".cancelCharge").addClass("hidden");
	if (!$.fn.DataTable.isDataTable('#accrualsTable')) {
		chargedatainit();
	}else{
		accrualsData.projId=$("#projId1").val();
		$("#accrualsTable").cgetData(false);
	}
	
});
//保存成功后回调函数
function saveBack(){
	if($("#accrualsTad").parent().hasClass("active")){
		accrualsTable.ajax.reload(reback);
	}else{
		cashFlowTable.ajax.reload();
	}
	
// 	cashFlowTable.ajax.reload();
//  $('#cashFlowTable').cgetData();
	//$('#cashFlowTab').tab('show');
// 	$('ul.nav>li').removeClass("active");
// 	$('#cashFlowTab').click();
}


$(".chargeDone").off("click").on("click",function(){
 if($("#chargeForm").parsley().isValid()){
     $("body").cgetPopup({
         title: "提示信息",
         content: "请核对付款信息，点击确认继续！",
         accept: function () {
             var data = {};
             data.arId = $('#arId').val();
             if ($("#accrualsTad").parent().hasClass("active")) {
            	 //不存在已付
                 //if ($("input:radio[name='isCharge']:checked").val() == '1') {
                     $("#cfId").val("");
                     var receiveAmount = trSData.accrualsTable.json.receiveAmount;
                     if (receiveAmount == null || receiveAmount == '') {
                         receiveAmount = 0.00;
                     }
                     data.cashFlow = $('#chargeForm').serializeJson();
                     //实收金额累加
                     data.cashFlow.receiveAmount = (new Number(receiveAmount) + new Number($("#cfAmount").val())).toFixed(2);
                     console.info('data.cashFlow.receiveAmount1....' + data.cashFlow.receiveAmount);
                     //实收大于应收金额
                     console.info('trSData.accrualsTable.json.arCost....' + trSData.accrualsTable.json.arCost);
                     if (data.cashFlow.receiveAmount > trSData.accrualsTable.json.arCost) {
                         var myoptions = {
                             title: "提示信息",
                             //content: "金额大于应付金额,点击确定将超收部分计入尾款，是否继续?",
                             content: "金额大于应付金额,是否继续?",
                             accept: overcharge,
                             newpop:'new',
                             icon: "fa-check-circle"
                         }
                         $("body").cgetPopup(myoptions);
                     } else {
                         var dataString = JSON.stringify(data);
                         //$('#chargeForm').gformSave(dataString, '', saveBack, true);
                         payformSave(dataString);
                     }
                /*  } else {
                     data.accrualsRecord = $('#chargeForm').serializeJson();
                     var myoptions = {
                         title: "提示信息",
                         content: "未收款,是否继续？",
                         accept: function () {
                             var dataString = JSON.stringify(data);
                             $('#chargeForm').gformSave(dataString, '', saveBack, false, "new");
                         },
                         newpop:'new',
                         icon: "fa-check-circle"
                     }
                     $("body").cgetPopup(myoptions);
                 } */
             } else {
                 data.cashFlow = $('#chargeForm').serializeJson();
                 var dataString = JSON.stringify(data);
                 console.info(data);
                 //$('#chargeForm').gformSave(dataString, '', saveBack, true);
                 payformSave(dataString);
             }
         },
         chide: false,
         icon: "fa-check-circle"
     });
	 //如果通过验证, 则移除验证UI
		$("#chargeForm").parsley().reset();
	    return false;
	}else{
    	//如果未通过验证, 则加载验证UI
    	$("#chargeForm").parsley().validate();
    }
});

//收款取消操作
$(".cancleBtn").on("click",function(){
	reback();
	if($("#accrualsTad").parent().hasClass("active")){
		accrualsTable.row('.selected').deselect().to$().removeClass("selected");
	}else{
		cashFlowTable.row('.selected').deselect().to$().removeClass("selected");
	}
		
});

//超收操作
var overcharge=function(){
	var data={};
	$("#overChange").val('1');
    data.arId=$('#arId').val();
    data.overChange=$("#overChange").val();
    $("#cfId").val("");
	var receiveAmount=trSData.accrualsTable.json.receiveAmount;
	if(receiveAmount==null||receiveAmount==''){
		receiveAmount=0.00;
	}
	data.cashFlow=$('#chargeForm').serializeJson();
	//实收金额累加
	data.cashFlow.receiveAmount=(new Number(receiveAmount)+new Number($("#cfAmount").val())).toFixed(2);
	var dataString = JSON.stringify(data);	
	//$('#chargeForm').gformSave(dataString, '', saveBack, false,'new');
	 payformSave(dataString);
}






var trSelectedBack1=function(v, i, index, t, json){
	t.cgetDetail('chargeForm','','',trBack);
}
function trBack(){
	console.info("====="+trSData.cashFlowTable.json.invoice);
	$("#openBank").val(openBank);
    $("#account").val(account); 
    $(".cancelCharge").removeClass("hidden");
	  if(trSData.cashFlowTable.json.billType==1){	 
	     if(trSData.cashFlowTable.json.invoice==1){
	    	$(".editbtn").addClass("hidden");	
	    	$(".bill_type_btn").attr("disabled",true); 
		    $('[name="invoice"][value=1]').attr("checked",true);
		    $('[name="invoice"]').change();
		    $(".is_Invoice_btn").attr("disabled",true); 
	 		$(".is_Invoice").attr("readonly",true);
	 		$(".is_pub").attr("readonly",true);
			$("#invoiceAmount").val(trSData.accrualsTable.json.invoiceAmount);
			$("#invoiceNo").val(trSData.accrualsTable.json.invoiceNo);
			$("#invoiceDate").val(trSData.accrualsTable.json.invoiceDate).change();
		    }else{	
		    	 $('.recInvoice').addClass('hidden');
		    	 $('[name="invoice"][value=0]').attr("checked",true);
		    	 $('[name="invoice"]').change();
		    	 $(".bill_type_btn").removeAttr("disabled"); 
		 		 $(".is_Invoice_btn").removeAttr("disabled"); 
		 		 $(".is_Invoice").removeAttr("readonly");
		 		 $(".is_pub").removeAttr("readonly");
		 		 $(".editbtn").removeClass("hidden");
		       }
	 
    }else{	
	     if(trSData.cashFlowTable.json.receiveInvoice==1){
	    		$(".editbtn").addClass("hidden");	
	    		$(".bill_type_btn").attr("disabled",true); 
			    $('[name="receiveInvoice"][value=1]').attr("checked",true);
			    $('[name="receiveInvoice"]').change();			  
				$("#invoiceAmount").val(trSData.accrualsTable.json.invoiceAmount);
				$("#invoiceNo").val(trSData.accrualsTable.json.invoiceNo);
				$("#recInvoiceDate").val(trSData.accrualsTable.json.recInvoiceDate).change();
				$(".is_recInvoice_btn").attr("disabled",true); 
			    $(".is_recInvoice").attr("readonly",true);
			 	$(".is_pub").attr("readonly",true);
				$('[name="isCharge"][value=0]').attr("checked",true);
			     }else{		
			    	 $('.isInvoice').addClass('hidden');
			    	 $(".bill_type_btn").removeAttr("disabled"); 
			    	 $('[name="receiveInvoice"][value=0]').attr("checked",true);
			    	 $('[name="receiveInvoice"]').change();
			    	 $('[name="isCharge"][value=0]').attr("checked",true);
			 		 $(".is_recInvoice_btn").removeAttr("disabled"); 
			 		 $(".is_recInvoice").removeAttr("readonly");
			 		 $(".is_pub").removeAttr("readonly");
			 		 $(".editbtn").removeClass("hidden");
			       }
}
	 
	$(".cancelCharge").removeClass("hidden");	
	//请款单信息
	var paymentApplay = trSData.accrualsTable.json.paymentApply;
	if(paymentApplay!=null){
		$("#applyDate").val(paymentApplay.applyDate);
		$("#applyer").val(paymentApplay.applyer);
	}
	//合同号
	if(trSData.accrualsTable.json.conNo){
		$("#conNo").val(trSData.accrualsTable.json.conNo);
		$("#scAmount").val(trSData.accrualsTable.json.scAmount);
	}
	//
	if(trSData.accrualsTable.json.contractAmount){
		$("#contractAmount").val(trSData.accrualsTable.json.contractAmount);
	}
}


//取消收款
$(".cancelCharge").on("click",function(){	
	var myoptions = {
           	title: "提示信息",
           	content: "确定要取消收款？",
           	accept: function(){
           		var data={};
           		data.arId=trSData.cashFlowTable.json.arId;
           		data.cfId=trSData.cashFlowTable.json.cfId;
           		$.ajax({
        			type: 'post',
        	        url: 'charge/cancleCharge',
        	        dataType: 'json',
        	        data: data,
        	        success: function(data) {
        	        	if(data.ret_message=="false"){
        	        		alertInfo("操作失败！");
        	        	}else if(data.ret_message=="true"){
        	        		alertInfo("操作成功！");
        	        		$("#cashFlowTable").cgetData(false);
        	        	}else{
        	        		alertInfo(data.ret_message);
        	        	}
        	         }
        	        });
           	},
           	icon: "fa-check-circle"
       }
       $("body").cgetPopup(myoptions);
});
//是否开票
$('[name="invoice"],[name="receiveInvoice"]').off("change").on("change", function(){
	 console.info("--11--="+$(this).filter(":checked").val());
	 if($(this).filter(":checked").val()){
	   if($(this).filter(":checked").val()==1){
		   console.info("----="+$('[name="billType"]:checked').val());
		   if($('[name="billType"]:checked').val()==1){
	       	//开票录入
	       	$('.recInvoice').addClass('hidden'); 
	       	$('.isInvoice').removeClass('hidden');
	       	
		   }else{
			
			$('.isInvoice').addClass('hidden');
			$('.recInvoice').removeClass('hidden'); 
		 	
		   }
	       }else{
	       	$('.isInvoice').addClass('hidden');
	       	$('.recInvoice').addClass('hidden');
	       }
	   
	    $("#chargeForm").styleFit();
	 }
	});
$('[name="billType"]').off('change').on("change", function(){
	 if($('[name="billType"]:checked').val()==1){
		 $('.invoiceBtn').removeClass('hidden');
	     $('.recInvoiceBtn').addClass('hidden');  
	     $('[name="invoice"][value=0]').attr("checked",true);
	 }else{
		 $('.invoiceBtn').addClass('hidden');
	     $('.recInvoiceBtn').removeClass('hidden');  
	     $('[name="receiveInvoice"][value=0]').attr("checked",true);
	 }
	  
	    $('.recInvoice').addClass('hidden'); 
	 	$('.isInvoice').addClass('hidden');
	    $("#chargeForm").styleFit();
	});


//是否收款
$('[name="isCharge"]').off("change").on("change", function(){
	 if($('[name="isCharge"]:checked').val()==1){ 	     
	         $("#cfAmount").attr("data-parsley-required",true);
	         $("#cfAmount").addClass("fixed-number");
	         $("#cfAmount").attr("data-parsley-min","0.0001");
		 }else{
			 $("#cfAmount").removeAttr("data-parsley-required");
			 $("#cfAmount").parent().find("ul").remove(); 
			 $("#cfAmount").removeClass("parsley-error");
			 $("#cfAmount").removeClass("fixed-number");
			 $("#cfAmount").removeAttr("data-parsley-min");
		 }

	
  });
  
  function changefun(){
	  $(".editbtn").removeClass("hidden");
	  $('[name="isCharge"][value=0]').attr("checked",true);
	  if(trSData.accrualsTable.json.billType==1){	
	     if(trSData.accrualsTable.json.invoice==1){
	    	$("#invoiceAmount").val(trSData.accrualsTable.json.invoiceAmount);
			$("#invoiceNo").val(trSData.accrualsTable.json.invoiceNo);
			$("#invoiceDate").val(trSData.accrualsTable.json.invoiceDate).change();
	    	 
	        $('.recInvoiceBtn').addClass('hidden');
		    $('.invoiceBtn').removeClass('hidden');
		    $('[name="invoice"][value=1]').attr("checked",true);
		    $('[name="invoice"]').change();
		 /*    if(trSData.accrualsTable.json.arFlag==-1){
		    	$('.recInvoice').removeClass('hidden');
		    }else{
		    	$('.recInvoice').addClass('hidden');
		    } */
		    $(".bill_type_btn").attr("disabled",true); 
		    $(".is_Invoice_btn").attr("disabled",true); 
	 		$(".is_Invoice").attr("readonly",true);
	 		$(".is_pub").attr("readonly",true);
			
			
		     }else{	
		    	 $('.recInvoice').addClass('hidden');
		    	 $('[name="invoice"][value=0]').attr("checked",true);
		    	 $('[name="invoice"]').change();
		 		 $(".is_Invoice_btn").removeAttr("disabled"); 
		 		 $(".is_Invoice").removeAttr("readonly");
		 		 $(".is_pub").removeAttr("readonly");
		 		 $(".bill_type_btn").removeAttr("disabled"); 
		       }
	 
       }else{
	     console.info("+++++===+++"+trSData.accrualsTable.json.receiveInvoice);
	     if(trSData.accrualsTable.json.receiveInvoice==1){
	    	   $('.invoiceBtn').addClass('hidden');
		       $('.recInvoiceBtn').removeClass('hidden');
			    $('[name="receiveInvoice"][value=1]').attr("checked",true);
			    $('[name="receiveInvoice"]').change();
			   
			   /*  if(trSData.accrualsTable.json.arFlag==-1){
			    	$('.recInvoice').removeClass('hidden');
			    }else{
			    	$('.recInvoice').addClass('hidden');
			    } */
			    $(".is_recInvoice_btn").attr("disabled",true); 
		 		$(".is_recInvoice").attr("readonly",true);
		 		$(".is_pub").attr("readonly",true);
		 		$(".bill_type_btn").attr("disabled",true); 
				$("#invoiceAmount").val(trSData.accrualsTable.json.invoiceAmount);
				$("#invoiceNo").val(trSData.accrualsTable.json.invoiceNo);
				$("#recInvoiceDate").val(trSData.accrualsTable.json.recInvoiceDate).change();
			     }else{		
			    	 $('.isInvoice').addClass('hidden');
			    	 $('[name="receiveInvoice"][value=0]').attr("checked",true);
			    	 $('[name="receiveInvoice"]').change();
			 		 $(".is_recInvoice_btn").removeAttr("disabled"); 
			 		 $(".is_recInvoice").removeAttr("readonly");
			 		 $(".is_pub").removeAttr("readonly");
			 		 $(".bill_type_btn").removeAttr("disabled"); 
			       }
  }
	  $("#chargeForm").styleFit();
  }
  
  function payformSave(data){
	  if($("#chargeForm").parsley().isValid()){
	      	//var data=$("#chargeForm").serializeJson();
	      	$.ajax({
	              type: 'POST',
	              url: 'charge/insertCashFlow',
	              contentType: "application/json;charset=UTF-8",
	              dataType:"json",
	              data: data,
	              success: function (data) {
	              	var content = "数据保存成功！";
	              	if(data.ret_type==-1){
	              		content = "数据保存失败！";
	              		alertInfo(content);
	              	}else if(data.ret_type==0){
	              		if(data.ret_message=="true"){
	                  		$("#chargeForm").toggleEditState(false);
	                  		saveBack();
	                  		alertInfo(content);
	                  	}else{
	                  		
	                  	}
	              	}else{//接口异常
	              		alertInfo(data.ret_message);
	              	}
	              },
	              error: function (jqXHR, textStatus, errorThrown) {
	                  console.warn("付款登记保存失败！");
	              }
	          });
	      	//如果通过验证, 则移除验证UI
	      	$("#chargeForm").parsley().reset();
	      }else{
	      	//如果未通过验证, 则加载验证UI
	      	$("#chargeForm").parsley().validate();
	      }
  }
</script>