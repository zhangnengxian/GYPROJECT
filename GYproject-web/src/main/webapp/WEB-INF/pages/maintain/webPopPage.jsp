<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="clearboth form-box">
	<form class="form-horizontal" id="webSearchForm">
		<div class="form-group col-md-12">
			<label class="control-label">名 称</label>
			<div >
				<input type="text" class="form-control input-sm"  name="webServiceName"/>
			</div>
		</div>
		<div class="form-group col-md-12">
			<label class="control-label" for="webServiceFlag">状 态</label>
			<div>
				<select id="webServiceFlag" name="webServiceFlag"  class="form-control input-sm field-editable">
					<option value="" ></option>
					<option value="1" >开启</option>
					<option value="0" >关闭</option>
				</select>
			</div>
		</div>

	</form>
</div>

<script>
    App.restartGlobalFunction();
    $("#webSearchForm").styleFit();

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

</script>
