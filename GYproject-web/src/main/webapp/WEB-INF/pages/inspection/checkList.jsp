<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="clearboth form-box">
		<form class="form-horizontal" id="qualityCheckForm" action="" data-parsley-validate="true">
	 			<c:forEach var="enums" items="${checkType}">
					<div class="form-group col-xs-12 selfcheckformtitle"><i class="fa fa-angle-double-right"></i> ${enums.des }</div>
					<div class="form-group col-md-3 col-sm-6 col-xs-12">
						<div>
							<input type="hidden" name="${enums.id }_checkTypeId" value="${enums.id}">
							<input type="hidden" class="check-list-projId" name="${enums.id }_projId" value="">
							<input type="hidden" class="check-list-clId" name="${enums.id }_clId" value="">
							<label><input type="radio" name="${enums.id }_isCheck" value="1"> ${cRQuality[0].message}</label>
							<label><input type="radio" name="${enums.id }_isCheck" value="0"> ${cRQuality[1].message}</label>
						</div>
					</div>
					<div class="form-group col-md-9 col-sm-6 col-xs-12">
						<label>备注</label>
						<div>
							<input class="form-control input-sm " data-parsley-maxlength="200" name="${enums.id }_remark" id="${enums.id }_remark" value="">
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
    $('#qualityCheckForm').styleFit();
    $('#qualityCheckForm').toggleEditState(false);
 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->