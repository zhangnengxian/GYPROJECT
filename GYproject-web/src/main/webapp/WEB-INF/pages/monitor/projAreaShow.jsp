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
	padding:10px 50px 10px 10px;
}

[readonly], [disabled]{
	border:transparent 1px solid !important
}
</style>
<div class="infodetails">
    <div class="clearboth form-box">
    	<div class="form-group col-lg-12 col-md-12 col-sm-12">
    		<h3>工程区域信息</h3>
    		<form class="form-horizontal m-t-30" id="areaForm" action="">
    			
	    		<div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">天山区</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area0Num" name="area0Num"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-6 " style="visibility:hidden;">
			    	<label class="control-label" for="numGrow">增长</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="numGrow" name="numGrow"  />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">签约项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area0Sign" name="area0Sign"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area0Amount" name="area0Amount"  value="0.00" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">沙区</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area1Num" name="area1Num"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-6 " style="visibility:hidden;">
			    	<label class="control-label" for="numGrow">增长</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="numGrow" name="numGrow"  />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">签约项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area1Sign" name="area1Sign"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area1Amount" name="area1Amount"   value="0.00"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">新市区</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area2Num" name="area2Num"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-6 " style="visibility:hidden;">
			    	<label class="control-label" for="numGrow">增长</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="numGrow" name="numGrow"  />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">签约项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area2Sign" name="area2Sign"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area2Amount" name="area2Amount"   value="0.00"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">水区</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area3Num" name="area3Num"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-6 " style="visibility:hidden;">
			    	<label class="control-label" for="numGrow">增长</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="numGrow" name="numGrow"  />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">签约项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area3Sign" name="area3Sign"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area3Amount" name="area3Amount"   value="0.00"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="area4Num">头区</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area4Num" name="area4Num"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-6 " style="visibility:hidden;">
			    	<label class="control-label" for="numGrow">增长</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="numGrow" name="numGrow"  />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="">签约项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area4Sign" name="area4Sign"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="area4Amount">金额</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="area4Amount" name="area4Amount"  value="0.00"/>
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
$("#areaForm").toggleEditState(false);
//表单样式适应
$("#areaForm").styleFit();



var projAreaShowNum=function(){
	 $.ajax({
		    type: 'POST',
		    url: '/projTS/queryProjectAreaNum',
		    contentType: "application/json;charset=UTF-8",
		    data: '',
		    success: function (data) {
		    	console.info('鼠标悬浮时第3个grid显示值------------------');
		    	console.log(data);
		    	var json=JSON.parse(data);
		    	for(var i=0;i<json.length;i++){
		    		if(json[i].area=="天山区"){
		    			$("#area0Num").val(json[i].acceptNum);
		    			$("#area0Sign").val(json[i].signNum);
		    			$("#area0Amount").val(new Number(json[i].signMoney/10000).toFixed(2)+"万");
		    		}
		    		if(json[i].area=="沙区"){
		    			$("#area1Num").val(json[i].acceptNum);
		    			$("#area1Sign").val(json[i].signNum);
		    			$("#area1Amount").val(new Number(json[i].signMoney/10000).toFixed(2)+"万");
		    		}
		    		if(json[i].area=="新市区"){
		    			$("#area2Num").val(json[i].acceptNum);
		    			$("#area2Sign").val(json[i].signNum);
		    			$("#area2Amount").val(new Number(json[i].signMoney/10000).toFixed(2)+"万");
		    		}
		    		if(json[i].area=="水区"){
		    			$("#area3Num").val(json[i].acceptNum);
		    			$("#area3Sign").val(json[i].signNum);
		    			$("#area3Amount").val(new Number(json[i].signMoney/10000).toFixed(2)+"万");
		    		}
		    		if(json[i].area=="头区"){
		    			$("#area4Num").val(json[i].acceptNum);
		    			$("#area4Sign").val(json[i].signNum);
		    			$("#area4Amount").val(new Number(json[i].signMoney/10000).toFixed(2)+"万");
		    		}
		    	}
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		        console.warn("ajax queryScaleDetail...fail");
		    }
	 })
}();

</script>
<!-- ================== END PAGE LEVEL JS ================== -->