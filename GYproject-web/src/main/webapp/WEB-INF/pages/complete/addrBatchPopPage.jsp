<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<div class="p-t-6">
		<div class="mask-html text-center">
			<div>
				<i class="fa fa-4x fa-spinner"></i><br>
				<p class="text-ellipsis">加载中</p>
			</div>
		</div>
		<input type="hidden" id="projId" name="projId"/>
		<div id="caiIdDes" class=" pull-left">
			<select id="deptName" class="form-control input-sm field-editable width-70 m-b-5" name="deptName" >
				<option value="">区县</option>
				<option value="">天山区</option>
				<option value="">沙区</option>
				<option value="">新市区</option>
				<option value="">水区</option>
				<option value="">头区</option>
			</select>
		</div>
		<div id="caiIdDes" class=" pull-left">
			<select id="deptName" class="form-control input-sm field-editable width-70 m-l-5" name="deptName" >
				<option value="">街道</option>
				<option value="">裕华路</option>
				<option value="">中山路</option>
			</select>
		</div>
		<div id="caiIdDes" class=" pull-left">
			<select id="deptName" class="form-control input-sm field-editable width-70 m-l-10" name="deptName" >
				<option value="">小区</option>
				<option value="">乐汇城</option>
				<option value="">中山华府</option>
			</select>
		</div>
		<form id="scoreTableForm" data-parsley-validate="true">
			<table id="addrtable"	class="table table-striped table-bordered nowrap" width="100%">
				<thead>
				<tr>
					<th>编码</th>
					<th>地址编码描述</th>
					<th>编码等级</th>
				</tr>
				</thead>
			</table>
       	</form>
	</div>
</div>

<script>
	App.restartGlobalFunction();
	$.getScript('projectjs/complete/addr-batch.js').done(function () {
		addrBatch.init();
	});
</script>
