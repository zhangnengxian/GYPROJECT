<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="form-box" style="margin-bottom: -36px">
	<form class="form-horizontal " id="deptpopform">
		<input type="hidden" name="deptType" value="${deptType}"/>
		<%-- <div class="form-group col-lg-5 col-md-5 col-sm-5">
			<label class="control-label" for="province">部门类型</label>
			<div>
				<select class="form-control input-sm field-editable" name="deptType">
					<option value=""></option>
					<c:forEach var="enums" items="${deptType}">
						<option value="${enums.value}">${enums.message}</option>
					</c:forEach>
				</select>
			</div>
		</div> --%>
		<div class="form-group col-lg-5 col-md-5 col-sm-5">
			<label class="control-label" for="deptName6">部门名称</label>
			<div>
				<input type="text" class="form-control input-sm field-editable" name="deptName" />
			</div>
		</div>
		
		<div class="form-group col-lg-5 col-md-5 col-sm-5">
			<label class="control-label" for="deptName6">设计院</label>
			<div>
				<select class="form-control input-sm" id="allDesign"  name="allDesign"  >
					<c:forEach var="enums" items="${allDesign}">
					<option value="${enums.deptId}">${enums.deptName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
	</form>
</div>
<div>
	<div class="p-t-6">
		<div class="mask-html text-center">
			<div>
				<i class="fa fa-4x fa-spinner"></i><br>
				<p class="text-ellipsis">加载中</p>
			</div>
		</div>
		<table id="deptTablePop" class="table table-striped table-bordered nowrap" width="100%">
			<thead>
				<tr>
					<th>部门id</th>
					<th>内部编码</th>
					<th>部门名称</th>
					<th>部门类型</th>
					<th>负责人</th>
					<!-- <th>联系电话</th> -->
				</tr>
			</thead>
		</table>
	</div>
</div>
<script>
	App.restartGlobalFunction();
	$("#deptpopform").styleFit();
	$.getScript('projectjs/design/design-dept-pop.js').done(function () {
		dept.init();
	});
</script>
