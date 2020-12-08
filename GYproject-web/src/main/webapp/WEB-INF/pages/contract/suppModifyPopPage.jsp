<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="clearboth form-box">
	<form class="form-horizontal" id="modifySupplementForm" action="/" method="POST">
		<div class="form-group col-md-12 col-sm-12">
			<label class="control-label">原因：</label>
			<div>
				<textarea id="modifyRemark" name="modifyRemark" data-parsley-required="true" class="form-control field-editable" rows="6"></textarea>
			</div>
		</div>
	</form>
</div>

<script>
    App.restartGlobalFunction();
    $("#modifySupplementForm").styleFit();

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

</script>
