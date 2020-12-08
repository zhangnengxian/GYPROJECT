<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="clearboth form-box">
	<form class="form-horizontal" id="abandonedRecordPopForm">
		<input type="hidden" id="abBusinessId" value="${_businessId}"/>
		<div class="form-group col-md-12 col-sm-12">
			<label class="control-label" >回退步骤：</label>
			<div>
				<select id="abStepId" class="form-control input-sm " data-parsley-required="true">
					<c:choose>
						<c:when test="${!empty _statusEnumList}">
							<c:forEach var="statusEnum" items="${_statusEnumList}">
								<option value="${statusEnum.code}">${statusEnum.name }</option>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<option value="">无</option>
						</c:otherwise>
					</c:choose>
				</select>
			</div>
		</div>

		<div class="form-group col-md-12 col-sm-12 ">
			<label class="control-label" >原因/备注：</label>
			<div>
				<textarea id="abReason"  class="form-control field-editable allText" data-parsley-required="true"  rows="6"></textarea>
			</div>
		</div>
		<div class="form-group col-md-6 col-sm-12 ">
			<label class="control-label">操作人：</label>
			<div>
				<input type="text"  value="${_operator}" class="form-control field-editable allText" readonly="readonly" />
			</div>
		</div>
		<div class="form-group col-md-6 col-sm-12 ">
			<label class="control-label">操作时间：</label>
			<div>
				<input type="text"   value="${_operatingTime}" class="form-control field-editable allText input-sm datepicker-default" readonly="readonly"  />
			</div>
		</div>
	</form>
</div>

<script>
    App.restartGlobalFunction();
    $("#abandonedRecordPopForm").styleFit();

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });


</script>
