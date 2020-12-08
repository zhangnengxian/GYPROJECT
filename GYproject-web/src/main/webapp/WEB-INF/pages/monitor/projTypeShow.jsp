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
	padding:10px 32px;
} 

[readonly], [disabled]{
	border:transparent 1px solid !important
}
</style>
<div class="infodetails">
    <div class="clearboth form-box">
    	<div class="form-group col-lg-12 col-md-12 col-sm-12">
    		<h3>工程类型信息</h3>
    		<form class="form-horizontal m-t-30" id="projTypeForm" action="">
	    		<div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="civilianNum">民用项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="civilianNum" name="civilianNum"  value="0"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="publicNum">金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="civilianAmount" name="civilianAmount"  value="0.00"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="publicNum">公用项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="publicNum" name="publicNum"  value="0"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="publicAmount">金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="publicAmount" name="publicAmount" value="0.00"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="vehicleUsed">车用项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="vehicleUsedNum" name="vehicleUsedNum" value="0"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="vehicleUsedAmount">金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="vehicleUsedAmount" name="vehicleUsedAmount"  value="0.00"/>
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
$("#projTypeForm").toggleEditState(false);

//表单样式适应
$("#projTypeForm").styleFit();



var thisYearShowRight=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/queryProjectTypeNum',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info('鼠标悬浮时第2个grid显示值------------------');
	    	console.info(data);
	    	var json=JSON.parse(data);
	    	for(var i=0;i<json.length;i++){
	    		if(json[i].type=="民用"){
	    			$("#civilianNum").val(json[i].thisYearNum);
	    			$("#civilianAmount").val(new Number(json[i].thisYearMoney/10000).toFixed(2)+"万");
	    			
	    		}
	    		if(json[i].type=="公用"){
	    			$("#publicNum").val(json[i].thisYearNum);
	    			$("#publicAmount").val(new Number(json[i].thisYearMoney/10000).toFixed(2)+"万");
	    		}
	    		if(json[i].type=="车用"){
	    			$("#vehicleUsedNum").val(json[i].thisYearNum);
	    			$("#vehicleUsedAmount").val(new Number(json[i].thisYearMoney/10000).toFixed(2)+"万");
	    		}
	    	}
	    	
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
	
}();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->