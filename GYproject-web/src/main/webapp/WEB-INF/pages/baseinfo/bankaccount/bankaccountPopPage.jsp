<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="clearboth form-box">
	<form class="form-horizontal" id="searchForm">
		<div class="form-group col-md-12 col-sm-12">
			<label class="control-label" for="corpId">公司名称：</label>
			<div>
				<select id="corpId" name="corpId" class="form-control input-sm field-editable " data-parsley-required="true">
					<c:forEach var="department" items="${departmentList}">
						<option value="${department.deptId}" >${department.deptName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group  col-sm-12">
			<label class="control-label">银行账号：</label>
			<div>
				<input type="text" name="bankNo" class="form-control input-sm field-editable" data-parsley-maxlength="20" data-parsley-required="false"/>
			</div>
		</div>

		<div class="form-group  col-sm-12">
			<label class="control-label">银行名称：</label>
			<div>
				<input type="text" name="bankName" class="form-control input-sm field-editable" data-parsley-maxlength="20" data-parsley-required="false"/>
			</div>
		</div>

	</form>
</div>

<script>
    App.restartGlobalFunction();
    $("#searchForm").styleFit();

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

</script>
