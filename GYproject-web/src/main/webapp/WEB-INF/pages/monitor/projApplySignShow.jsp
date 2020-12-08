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
	padding:10px 40px 10px 20px;
}

[readonly], [disabled]{
	border:transparent 1px solid !important
}
</style>
<div class="infodetails">
    <div class="clearboth form-box">
    	<div class="form-group col-lg-12 col-md-12 col-sm-12">
    		<h3>申请签约信息</h3>
    		<form class="form-horizontal m-t-30" id="stageForm" action="">
	    		<div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="acceptNum">新增项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="acceptNum" name="acceptNum"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="contractNum">签约项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="contractNum" name="contractNum"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="acceptMoney">签约金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="acceptMoney" name="acceptMoney"  value="0.00" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="avgMoney">平均金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="avgMoney" name="avgMoney"  value="0.00" />
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
$("#stageForm").toggleEditState(false);

//表单样式适应
$("#stageForm").styleFit();


//鼠标悬浮时第5个grid显示值
var projApplySignShowNum=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/queryProjectAcceptAndContractNum',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info('鼠标悬浮时第5个grid显示值------------------');
	    	console.log(data);
	    	var json=JSON.parse(data);
	    	$("#acceptNum").val(json[0].acceptNum);
	    	$("#contractNum").val(json[0].contractNum);
	    	$("#acceptMoney").val(new Number(json[0].acceptMoney/10000).toFixed(2)+"万");
	    	$("#avgMoney").val(new Number(json[0].acceptMoney/json[0].contractNum/10000).toFixed(2)+"万");
	    	/* $("span.acceptTotalNum").text(json[0].acceptNum);
	    	$("span.signTotalNum").text(json[0].contractNum); */
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	})
}();


</script>
<!-- ================== END PAGE LEVEL JS ================== -->