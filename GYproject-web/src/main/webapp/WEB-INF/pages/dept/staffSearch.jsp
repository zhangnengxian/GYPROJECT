<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="assets/plugins/select2/dist/css/select2.min.css" rel="stylesheet" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="form-box">
	<input type="hidden"  id="comp" value="${comp}">
	<form class="form-horizontal" id="staffListPopform" >
		    <div class="form-group col-lg-6 col-md-6">
				<label class="control-label" for="loginAccount">登录名</label>
				<div>
					<input class="form-control input-sm field-editable"  name="loginAccount" id="loginAccount">
				</div>
		    </div>
<!--              <div class="form-group col-lg-6 col-md-6"> -->
<!-- 				<label class="control-label" for="staffName">姓名</label> -->
<!-- 				<div> -->
<!-- 					<input class="form-control input-sm field-editable"  name="staffName" id="staffName"> -->
<!-- 				</div> -->
<!-- 		    </div> -->
		   
		   <div class="form-group col-md-6 ">
			<label class="control-label" for="email">单位类型</label>
			<div>
				<select id="unitTypePop" name="unitType" class="form-control input-sm field-editable">
					<option value="" ></option>
					<c:forEach var="enums" items="${unitTypes}">
						<option value="${enums.value}" data-c='${enums.type}'>${enums.message}</option>
					</c:forEach>
				</select>
				<!-- <input class="form-control input-sm field-editable"  name="unitType" id="unitType"> -->
		   </div>
		 </div>
		  <div class="form-group col-md-6 corp hidden">
			<label class="control-label" for="corp">分公司</label>
			<div>
				<select id="corpId" name="corpId" class="form-control input-sm field-editable">
					<option value="" ></option>
					<c:forEach var="enums" items="${corp}">
						<option value="${enums.deptId}" >${enums.deptName}</option>
					</c:forEach>
				</select>
		   </div>
		 </div> 
		 <div class="form-group col-lg-6 col-md-6 corp hidden">
				<label class="control-label" for="unitName">部门名称</label>
				<div>
					<input class="form-control input-sm field-editable"  name="unitName" id="unitName">
				</div>
		 </div>
		 <div class="form-group  col-md-6">
			<label class="control-label" for="post">职务</label>
			<div>
				<select id="post" name="post" class="form-control input-sm field-editable">
					<option value="" ></option>
					<c:forEach var="enums" items="${postType}">
						<option value="${enums.id}" >${enums.postName}</option>
					</c:forEach>
				</select>
		   </div>
		 </div>
		 <div class="form-group  col-md-6">
			<label class="control-label" for="markOfDelet">员工状态</label>
			<div>
				<select id="markOfDelet" name="markOfDelet" class="form-control input-sm field-editable">
					<option value="1" selected = "selected" >在职</option>
					<option value="0" >离职</option>
				</select>
		   </div>
		 </div>
	</form>
</div>
<script>
	App.restartGlobalFunction();
	$("#staffListPopform").styleFit();
	$("#markOfDelet option:first").prop("selected", 'selected');
	$('[name="unitType"]').on("change",function(){
		if($(this).val()==$("#comp").val()){
			$(".corp").removeClass("hidden");
		}else{
			$("#corpId").empty();
			$("#unitName").val("");
			$(".corp").addClass("hidden");
		}
	})
	
</script>
