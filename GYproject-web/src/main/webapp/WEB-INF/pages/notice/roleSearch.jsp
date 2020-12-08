<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="assets/plugins/select2/dist/css/select2.min.css" rel="stylesheet" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="form-box">
	<form class="form-horizontal" id="roleListPopform" >
<!-- 		    <div class="form-group col-lg-6 col-md-6"> -->
<!-- 				<label class="control-label" for="roleCode">角色编号</label> -->
<!-- 				<div> -->
<!-- 					<input class="form-control input-sm field-editable"  name="roleCode" id="roleCode"> -->
<!-- 				</div> -->
<!-- 		    </div> -->
             <div class="form-group col-lg-12 col-md-12">
				<label class="control-label" for="nrName">角色名称 </label>
				<div>
					<input class="form-control input-sm field-editable"  name="nrName" id="nrName">
				</div>
		    </div>
	</form>
</div>
<script>
	App.restartGlobalFunction();
	$("#roleListPopform").styleFit();
</script>
