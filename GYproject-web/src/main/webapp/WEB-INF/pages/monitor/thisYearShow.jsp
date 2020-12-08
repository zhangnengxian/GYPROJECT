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
.clearboth{
    clear:both;
}
</style>
<div class="infodetails">
    <div class="clearboth form-box">
    	<div class="form-group col-lg-12 col-md-12 col-sm-12">
    		<h3>工程总体信息</h3>
    		<form class="form-horizontal m-t-30" id="acceptApplyForm" action="" >
	    		<div class="form-group col-md-6 col-sm-12 ">
			    	<label class="control-label" for="lastYearNum">去年项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="lastYearNum" name="lastYearNum" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="thisYearNum">本年新增</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="thisYearNum" name="thisYearNum"   />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-6 clearboth">
			    	<label class="control-label" for="numGrow">同比增长</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="numGrow" name="numGrow"  />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-6 " style="visibility:hidden;">
			    	<label class="control-label" for="numGrow">增长</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="numGrow" name="numGrow"  />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="lastYearMoney">去年收入</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="lastYearMoney" name="lastYearMoney"  />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="thisYearMoney">今年收入</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="thisYearMoney" name="thisYearMoney"   />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-6 clearboth">
			    	<label class="control-label" for="moneyGrow">同比增长</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="moneyGrow" name="moneyGrow"  />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-6 " style="visibility:hidden;">
			    	<label class="control-label" for="numGrow">增长</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="numGrow" name="numGrow"  />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="turnGas">转天然气</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="turnGas" name="turnGas"  />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="chargeBack">退单</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="chargeBack" name="chargeBack"/>
			        </div>
			    </div>
			    <!-- <div class="form-group col-md-12">
			     	<label class="control-label" for=""></label>
			     	<div> 
			        	<textarea class="form-control  field-editable" name="" id="" rows="4" cols="" data-parsley-maxlength="200" ></textarea>
		        	</div>
		    	</div> -->
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

//鼠标悬浮时第一个grid显示值
var thisYearShowRight=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/queryLastYearAndThisYearNum',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info('鼠标悬浮时第一个grid显示值------------------');
	    	console.info(data);
	    	var data=JSON.parse(data);
	    	var thisYearNum=data[0].thisYearNum;
	    	$("#thisYearNum").val(thisYearNum);
	    	var thisYearMoney=data[0].thisYearMoney;
	    	$("#thisYearMoney").val(new Number(data[0].thisYearMoney/10000).toFixed(2)+"万");
	    	var lastYearNum=data[0].lastYearNum;
	    	$("#lastYearNum").val(lastYearNum);
	    	var lastYearMoney=data[0].lastYearMoney;
	    	$("#lastYearMoney").val(new Number(data[0].lastYearMoney/10000).toFixed(2)+"万");
	    	$("#numGrow").val(new Number((thisYearNum-lastYearNum)/lastYearNum*100).toFixed(2)+"%");
	    	$("#moneyGrow").val(new Number((thisYearMoney-lastYearMoney)/lastYearMoney*100).toFixed(2)+"%");
	    	$("#turnGas").val(data[0].alreadyFinishNum);
	    	$("#chargeBack").val(data[0].terminationNum);
	    	
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
	
}();


</script>
<!-- ================== END PAGE LEVEL JS ================== -->