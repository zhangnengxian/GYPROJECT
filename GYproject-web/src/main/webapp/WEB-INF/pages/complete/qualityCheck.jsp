<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="clearboth form-box">
		<form class="form-horizontal" id="qualityCheckForm" action="" data-parsley-validate="true">
			<h4><i class="fa fa-arrow-circle-o-right"></i> ${checkType[0].message }</h4>
	 			<c:forEach var="enums" items="${checkItems1}">
					<div class="form-group col-xs-12 selfcheckformtitle"><i class="fa fa-angle-double-right"></i> ${enums.des }</div>
					<div class="form-group col-md-12 col-sm-6 col-xs-12">
						<div>
							<input type="hidden" name="${enums.id }_sirId" value="">
							<input type="hidden" name="${enums.id }_ciId" value="${enums.id }">
							<input type="hidden" name="${enums.id }_sirType" value="${enums.type }">
							<label><input type="radio" name="${enums.id }_sirResult" value="1"> ${cRQuality[0].message}</label>
							<label><input type="radio" name="${enums.id }_sirResult" value="0"> ${cRQuality[1].message}</label>
							<label><input type="radio" name="${enums.id }_sirResult" value="2"> ${cRQuality[2].message}</label>
						</div>
					</div>
					<div class="form-group col-md-12 col-sm-6 col-xs-12 clearboth">
						<label>问题及处理意见</label>
						<div>
							<input class="form-control input-sm field-editable" data-parsley-maxlength="200" name="${enums.id }_sirRemark" id="${enums.id }_sirRemark" value="">
						</div>
			    	</div>
				</c:forEach>
				<div class="clearboth">
	             <h4><i class="fa fa-arrow-circle-o-right"></i> ${checkType[1].message }</h4> 
	             </div>
	             	<c:forEach var="enums" items="${checkItems2}">
					<div class="form-group col-xs-12 selfcheckformtitle"><i class="fa fa-angle-double-right"></i> ${enums.des }</div>
					<div class="form-group col-md-12 col-sm-6 col-xs-12">
						<div>
							<input type="hidden" name="${enums.id }_sirId" value="">
							<input type="hidden" name="${enums.id }_ciId" value="${enums.id }">
							<input type="hidden" name="${enums.id }_sirType" value="${enums.type }">
							<label><input type="radio" name="${enums.id }_sirResult" value="1"> ${cRQuality[0].message}</label>
							<label><input type="radio" name="${enums.id }_sirResult" value="0"> ${cRQuality[1].message}</label>
							<label><input type="radio" name="${enums.id }_sirResult" value="2"> ${cRQuality[2].message}</label>
						</div>
					</div>
					<div class="form-group col-md-12 col-sm-6 col-xs-12 clearboth">
					<label>问题及处理意见</label>
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
    $('#qualityCheckForm').styleFit();
    $('#qualityCheckForm').toggleEditState(false);
 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->