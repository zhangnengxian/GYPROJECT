<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<div class="infodetails">
	<div class="clearboth form-box">
	<form class="form-horizontal" id="chargeAcForm" action="" data-parsley-validate="true">
			<div class="form-group col-md-6">
				<label class="control-label" for="">工程编号</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" id="projNo" name="projNo"/>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="">工程名称</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="projName" name="projName"/>

				</div>
			</div>
			
			<div class="form-group col-md-12">
				<label class="control-label" for="">工程地点</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="projAddr" name="projAddr"/>
				</div>
			</div>
			<div class="form-group col-md-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		        	<textarea class="form-control input-sm field-editable" name="projScaleDes" rows="" cols="" data-parsley-maxlength="200"></textarea>
		        </div>
		    </div>
			<div class="form-group col-md-12">
				<label class="control-label" for="">客户名称</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" id="custName"  name="custName"/>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="">单位地址</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" id="unitAddress"  name="unitAddress"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">开户行</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" id="openBank"  name="openBank"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">账号</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" id="account" name="account"  data-parsley-type="digits"/>
				</div>
			</div>
			
			<div class="form-group col-md-6">
				<label class="control-label" for="">税号</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" id="dutyParagraph" name="dutyParagraph"  data-parsley-type="digits"/>
				</div>
			</div>
			<div class="form-group col-md-6 clearboth">
				<label class="control-label" for="">操作人</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" id="staffName" name="staff.staffName" />		
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">操作时间</label>
				<div>
					<input type="text" class="form-control input-sm field-editable datepicker-default timestamp all ar" id="arDate" name="arDate" />
					<input type="text" class="form-control input-sm field-editable cf hidden" id="cfDate" name="cfDate" />
					
				</div>
			</div>
			<!-- <div class="form-group col-md-6 hidden cf">
				<label class="control-label" for="">操作时间</label>
				<div>				
					<input type="text" class="form-control input-sm field-editable datepicker-default timestamp all" id="cfDate" name="cfDate" />
				</div>
			</div> -->
			<div class="form-group  col-md-6 ">
				<label class="control-label" for="">收付款类型</label>
				<div>
				<input type="text" class="form-control input-sm field-editable hidden cf" id="cfTypeDes" name="cfTypeDes"  data-parsley-required="true" />
				<input type="text" class="form-control input-sm field-editable ar" id="arTypeDes" name="arTypeDes" />
				</div>
			</div>
			<!-- <div class="form-group col-md-6 ">
				<label class="control-label" for="isCharge">收付款状态</label>
				<div>
					<div>
					  <input type="text" class="form-control input-sm field-editable" value="" id="isChargeDes" name="isChargeDes"/>
				    </div>
				</div>
				
			</div> -->
			<div class="form-group col-md-6 ">
				<label class="control-label" for="isCharge">收付标志</label>
				<div>
					<div>
					  <input type="text" class="form-control input-sm field-editable ar" value="" id="arFlagDes" name="arFlagDes"/>
					  <input type="text" class="form-control input-sm field-editable hidden cf" value="" id="cfFlagDes" name="cfFlagDes"/>
				    </div>
				</div>			
			</div>
			<div class="form-group col-md-6 ">
				<label class="control-label" for="">金额</label>
				<div>
					<input type="text" class="form-control input-sm field-editable hidden cf" value="" id="cfAmount" name="cfAmount"/>
					<input type="text" class="form-control input-sm field-editable ar" value="" id="arCost" name="arCost"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6 cf hidden">
				<label class="control-label" for="cashDate">收款时间</label>
				<div>
					<input type="text" class="form-control input-sm field-editable datepicker-default" value="" id="cashDate" name="cashDate"/>
				</div>
			</div>
			<div class="form-group col-md-6 cf hidden">
				<label class="control-label" for="cashAccount">收款帐号</label>
				<div>
					<input type="text" class=" form-control input-sm field-editable" id="cashAccount"  name="cashAccount"  value="" data-parsley-maxlength="100">
				</div>
			</div>
			<div class="form-group col-md-6 cf hidden">
				<label class="control-label" for="increment">税率</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16">
					<a href="javascript:;" class="btn btn-sm btn-default">%</a>
				</div>
			</div>
			<div class="form-group col-md-6 ar fk">
				<label class="control-label" for="">实收金额</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="receiveAmount" name="receiveAmount"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6 ar fk">
				<label class="control-label" for="">剩余金额</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="balance" name="balance"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6 clearboth">
				<label class="control-label" for="invoice">票据类型</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="billTypeDes" name="billTypeDes"/>
				</div>
			</div>
			 <div class="form-group col-md-6 Invo" >
		        <label class="control-label" for="invoiceNo">发票号</label>
		        <div>
		           <input type="text" class="form-control input-sm field-editable" id="invoiceNo"  name="invoiceNo">
		        </div>
		    </div>

			<!-- <div class="form-group col-md-6 Invo">
				<label class="control-label" for="invoice">发票状态</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="invoiceDes" name="invoiceDes"/>
				</div>
			</div>
			<div class="form-group col-md-6 receipt">
				<label class="control-label" for="receiveInvoice">收据状态</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="receiveInvoiceDes" name="receiveInvoiceDes"/>
				</div>
			</div> -->
			
			<div class="form-group col-md-6 ">
		        <label class="control-label" for="invoiceAmount">金额(含税)</label>
		        <div>
		           <input type="text" class="form-control input-sm field-editable" id="invoiceAmount" name="invoiceAmount">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		   
		    <div class="form-group col-md-6 Invo" >
		        <label class="control-label" for="invoiceDate">开票日期</label>
		        <div>
		           <input type="text" class="form-control input-sm field-editable datepicker-default is_Invoice timestamp" id="invoiceDate"  name="invoiceDate">
		        </div>
		    </div>
		    
			<div class="form-group col-md-6 receipt" >
		        <label class="control-label" for="recInvoiceDate">收据日期</label>
		        <div>
		           <input type="text" class="form-control input-sm field-editable datepicker-default is_recInvoice timestamp" id="recInvoiceDate"  name="recInvoiceDate">
		        </div>
		    </div>
		  
		</form>
    </div>
    
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    $("#chargeAcForm").toggleEditState().styleFit();
    if(trSData.chargeArQueryTable.t){
    	var json=trSData.chargeArQueryTable.json;
    	trSData.chargeArQueryTable.t.cgetDetail('chargeAcForm','charge/queryChargeDetail','8',detailBack);
    	$("#balance").val((new Number(json.arCost)- new Number(json.receiveAmount)).toFixed(2));
        $("#staffName").val(json.staff.staffName);
        $("#billTypeDes").val(json.billTypeDes);
        //付款
        if(json.arFlag==-1){
        	$(".fk").addClass("hidden");
        }else{
        	$(".fk").removeClass("hidden");
        }
        if(json.billType!=''&&json.billType==0){
   		 $(".Invo").addClass("hidden");
   		 $(".receipt").removeClass("hidden");
   	    }else{
   		 $(".Invo").removeClass("hidden");
   		 $(".receipt").addClass("hidden");
   	    }
    }
    

    
    </script>
<!-- ================== END PAGE LEVEL JS ================== -->