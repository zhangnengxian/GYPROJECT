<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="clearboth form-box">
		<form class="form-horizontal" id="materialCheckForm" action="">
		   <c:forEach var="enums" items="${checkItems3}">
				<div class="form-group col-xs-12 selfcheckformtitle"><i class="fa fa-angle-double-right"></i> ${enums.des }</div>
				<div class="form-group col-md-2 col-sm-6 col-xs-12">
					<label>份数</label>
					<div>
						<input class="form-control input-sm field-editable" name="${enums.id }_sirNum" id="${enums.id }_sirNum" value="">
					</div>
				</div>
				<div class="form-group col-md-3 col-sm-6 col-xs-12">
					<div>
						<input type="hidden" name="${enums.id }_sirId" value="">
						<input type="hidden" name="${enums.id }_ciId" value="${enums.id }">
						<input type="hidden" name="${enums.id }_sirType" value="${enums.type }">
						<label><input type="radio" name="${enums.id }_sirResult" value="1"> ${cRMaterial[0].message}</label>
						<label><input type="radio" name="${enums.id }_sirResult" value="0"> ${cRMaterial[1].message}</label>
					</div>
				</div>	
				<div class="form-group col-md-7 col-sm-12 col-xs-12">
					<label>备注</label>
					<div>
						<input class="form-control input-sm field-editable" name="${enums.id }_sirRemark" id="${enums.id }_sirRemark" value="">
					</div>
		    	</div>
	         </c:forEach>
	 	</form>
	</div>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    //表单样式适应
    $('#materialCheckForm').styleFit();
    $("#materialCheckForm").toggleEditState(false);
 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->