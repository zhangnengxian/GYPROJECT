<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="changePassword" data-parsley-validate="true" action="" method="POST">
    	<input type="hidden" id="staffId" name="staffId" value="${staffId }"/>
		<div class="form-group col-lg-6 col-md-6 col-sm-6">
			<label class="control-label" for="oldPassword">旧密码</label>
          	<div >
               <input type="password" class="form-control input-sm" id='oldPassword' name="oldPassword" data-parsley-required="true"/>
          	</div>
       	</div>
       	<div class="form-group col-lg-6 col-md-6 col-sm-6">
        	<span class="warning1 hidden" style="color: red">*输入密码不正确</span>
       	</div>
       	<div class="form-group col-lg-6 col-md-6 col-sm-6 clearboth"> 
            <label class="control-label" for="newPassword">新密码</label>
            <div>
            	<input type="password" class="form-control input-sm" id='newPassword' name="newPassword" data-parsley-maxlength="20" data-parsley-required="true"/>
            </div>
       	</div>
       	<div class="form-group col-lg-6 col-md-6 col-sm-6"> 
	      	<span class="warning3" style="color: red">*请输入6-8位密码</span>
	      	<span class="warning4 hidden" style="color: red">*密码长度不符合要求</span>
       	</div>
       	<div class="form-group col-lg-6 col-md-6 col-sm-6 clearboth">
            <label class="control-label" for="confirmPassword">确认密码</label>
            <div>
            	<input type="password" class="form-control input-sm" id='confirmPassword' name="confirmPassword" data-parsley-maxlength="20" data-parsley-required="true"/>
            </div>
       	</div>
       	<div class="form-group col-lg-6 col-md-6 col-sm-6">
        	<span class="hidden warning2" style="color: red">*请输入相同密码</span>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#changePassword").styleFit();
    $("#oldPassword").on("change",function(){
       	$.ajax({
            type: 'POST',
            url: 'staff/getOldPassword',
            contentType: "application/json;charset=UTF-8",
            data: '',
            success: function (data) {
            	if($("#oldPassword").val()===data){
            		$(".warning1").addClass("hidden");
            	}else{
            		$(".warning1").removeClass("hidden");
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("旧密码比对失败！");
            }
		}); 
    	
    });
    $("#confirmPassword,#newPassword").on("change",function(){
    	if($("#confirmPassword").val()===$("#newPassword").val()){
    		$(".warning2").addClass("hidden");
    	}else{
    		$(".warning2").removeClass("hidden");
    	}
    });
    $("#newPassword").on("change",function(){
    	if($("#newPassword").val().length<6||$("#newPassword").val().length>8){
    		$(".warning3").addClass("hidden");
    		$(".warning4").removeClass("hidden");
    	}else{
    		$(".warning4").addClass("hidden");
    		$(".warning3").removeClass("hidden");
    	}
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->