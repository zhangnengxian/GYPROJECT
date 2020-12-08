<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="clearboth form-box">
    <form class="form-horizontal" id="resetForm" >
        <div class="form-group col-md-12 col-sm-12">
            <label class="control-label" for="resetReason">重置原因</label>
            <div>
                <textarea id="resetReason" name="resetReason"class="form-control field-editable" data-parsley-required="true" rows="6"></textarea>
            </div>
        </div>
    </form>
</div>

<script>
	App.restartGlobalFunction();
    $("#resetForm").styleFit();
</script>
