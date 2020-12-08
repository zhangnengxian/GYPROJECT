<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/assets/css/style.min.css" rel="stylesheet" />
<style>
.form-box .control-label,.form-box .form-control{
	color:#fff !important;
	font-size:21px;
}
h3{
	font-size:25px;
	color:#fff !important;
}
.form-horizontal .control-label{
	padding-top:0px;
}
.form-control{
	padding:10px 35px 10px 22px;
}

[readonly], [disabled]{
	border:transparent 1px solid !important
}
</style>
<div class="infodetails">
    <div class="clearboth form-box">
    	<div class="form-group col-lg-12 col-md-12 col-sm-12">
    		<h3>实收实付信息</h3>
    		<form class="form-horizontal m-t-30" id="acceptApplyForm" action="">
	    		<div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="signMoney">签约金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="signMoney" name="signMoney"  value="0.00" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-6 " style="visibility:hidden;">
			    	<label class="control-label" for="numGrow">增长</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="numGrow" name="numGrow"  />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="shouldChargeMoney">应收金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="shouldChargeMoney" name="shouldChargeMoney"  value="0.00" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="alreadyChargeMoney">实收金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="alreadyChargeMoney" name="alreadyChargeMoney"  value="0.00" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="shouldPayMoney">应付金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="shouldPayMoney" name="shouldPayMoney"   value="0.00"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="alreadyPayMoney">实付金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="alreadyPayMoney" name="alreadyPayMoney"  value="0.00"/>
			        </div>
			    </div>
    		</form>
    	</div>
    </div>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
//参数传false时，表单不可编辑
$("#acceptApplyForm").toggleEditState(false);

//表单样式适应
$("#acceptApplyForm").styleFit();



//鼠标悬浮时第6个grid显示值
var projAmountShowAmount=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/querySignAndAladyCharge',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info('鼠标悬浮时第6个grid显示值------------------');
	    	console.log(data);
	    	var json=JSON.parse(data);
	    	$("#signMoney").val(new Number(json[0].signMoney/10000).toFixed(2)+"万");
	    	$("#shouldChargeMoney").val(new Number(json[0].shouldChargeMoney/10000).toFixed(2)+"万");
	    	$("#alreadyChargeMoney").val(new Number(json[0].alreadyChargeMoney/10000).toFixed(2)+"万");
	    	$("#shouldPayMoney").val(new Number(json[0].shouldPayMoney/10000).toFixed(2)+"万");
	    	$("#alreadyPayMoney").val(new Number(json[0].alreadyPayMoney/10000).toFixed(2)+"万");
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	})
}();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->