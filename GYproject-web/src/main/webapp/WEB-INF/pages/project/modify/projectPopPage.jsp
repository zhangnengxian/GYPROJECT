<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="clearboth form-box">
	<form class="form-horizontal" id="projectSearchForm">
		<div class="form-group col-md-12">
			<label class="control-label">工程名称</label>
			<div >
				<input type="text" class="form-control input-sm"  name="projName"/>
			</div>
		</div>
		<div class="form-group col-md-12">
			<label class="control-label">工程地点</label>
			<div>
				<input type="text" class="form-control input-sm"  name="projAddr"/>
			</div>
		</div>
		<div class="form-group col-md-12">
			<label class="control-label" for="projStatusId">工程状态</label>
			<div>
				<select id="projStatusId" name="projStatusId"  class="form-control input-sm field-editable"  data-parsley-required="true">
					<option value="" ></option>
					<c:forEach var="projStatus" items="${projStatusEnum}">
						<option value="${projStatus.value}" >${projStatus.message}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</form>
</div>

<script>
    App.restartGlobalFunction();
    $("#projectSearchForm").styleFit();

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

</script>
