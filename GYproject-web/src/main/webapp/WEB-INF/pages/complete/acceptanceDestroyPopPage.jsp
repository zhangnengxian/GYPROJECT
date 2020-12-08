<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="clearboth form-box">
    <form class="form-horizontal" id="destroyPopForm" action="/" method="POST">
        <div class="form-group col-md-12">
            <label class="control-label">作废原因</label>
            <div >
                <textarea  id="destroyRemark" name="remark" class="form-control field-editable controlEdit"  rows="6" data-parsley-maxlength="200"></textarea>
            </div>
        </div>
    </form>
</div>
<script>
	App.restartGlobalFunction();
    $("#destroyPopForm").styleFit();
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
