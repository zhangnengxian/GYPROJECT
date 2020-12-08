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
	padding:10px 55px;
}

[readonly], [disabled]{
	border:transparent 1px solid !important
}
</style>
<div class="infodetails">
    <div class="clearboth form-box">
    	<div class="form-group col-lg-12 col-md-12 col-sm-12">
    		<h3>工程阶段信息</h3>
    		<form class="form-horizontal m-t-30" id="stageForm" action="">
    			<div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="acccept0">立项项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="acccept0" name="acccept0"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="acccept1">设计项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="acccept1" name="acccept1"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="acccept2">预算项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="acccept2" name="acccept2"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="acccept3">合同项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="acccept3" name="acccept3"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="acccept4">计划项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="acccept4" name="acccept4"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="acccept5">分包项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="acccept5" name="acccept5"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="acccept6">施工项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="acccept6" name="acccept6"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="acccept7">结算项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="acccept7" name="acccept7"  value="0" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 col-sm-12">
			    	<label class="control-label" for="acccept8">竣工项目</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable text-right"  id="acccept8" name="acccept8"  value="0" />
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

var stageNum=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projectStageStatistics/projectStageSum',
	    async: false,
	    contentType: "application/json;charset=UTF-8",
	    success: function (data) {
	    	console.info('鼠标悬浮时第4个grid显示值------------------');
	    	var stageData = JSON.parse(data);
	    	console.info(stageData);
	    	$('#gasAuditTable').hideMask();
	    	$.each(stageData,function(index,obj){
	    		if(index.length > 4 && index.substring(0,4) == 'num_')
	    		{
	    			document.getElementById("acccept" + index.substring(4,index.length)).value = obj;
	    		}
	    	});
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	})
}(); 

</script>
<!-- ================== END PAGE LEVEL JS ================== -->