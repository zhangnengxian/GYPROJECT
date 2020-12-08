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
			<select id="deptName" class="form-control input-sm field-editable width-150" name="deptName" >
				<c:forEach var="enums" items="${department}">
				<option value="${enums.deptId}">${enums.deptName}</option>
				</c:forEach>
			</select>
		</div>
		<form id="scoreTableForm" data-parsley-validate="true">
			<table id="scoreTable" class="table table-striped table-bordered nowrap" width="100%">
	            <thead>
	                <tr>
	                	<th ></th>
	                    <th >部门</th>
	                    <th >评分项</th>
	                    <th >分值</th>
	                    <th width="60px">打分</th>
	                </tr>
	            </thead>
	            <tfoot>
		            <tr>
		      			<td></td>
		      			<td >合计</td>
		      			<td class="total-amount"></td>
		      			<td class="score-amount"></td>
		      			<td class="total-amount"></td>
		      		</tr>
	      		</tfoot>
	       	</table>
       	</form>
	</div>
</div>

<script>
	App.restartGlobalFunction();
	$.getScript('projectjs/standard/score-pop.js').done(function () {
		scoreStandard.init();
	});
</script>
