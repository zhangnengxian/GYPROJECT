<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="form-group col-md-6">
	<label class="control-label signature-tool" for="" style="width: 90px;">签字</label>
	<div>
		<input type="hidden" class="sign-data-input disabled" id="signPicturePath" name=signPicturePath value="${sessionScope.session_loginInfo.staffName}">
		<img class="signPicture" alt="" src="" style="height: 30px"> 
		<span class="clear-sign disabled"><i class="fa ion-close-circled"></i></span>
	</div>
</div>
<script>
//$(".signPicture").attr("src","attachments/sign/"+$("#signPicture").val())
</script>