<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="clearboth form-box">
	<form class="form-horizontal" id="recoveryPopForm">
		<input type="hidden" id="_projId" value="${_projId}"/>
		<div class="form-group col-md-12 col-sm-12">
			<label class="control-label" >恢复到：</label>
			<div>
				<select id="_stepId" class="form-control input-sm " data-parsley-required="true">
						<c:if test="${!empty _stepList}">
							<c:forEach var="stepEnum" items="${_stepList}">
								<option value="${stepEnum.actionCode}">${stepEnum.actionDes }</option>
							</c:forEach>
						</c:if>
				</select>
			</div>
		</div>

		<div class="form-group col-md-12 col-sm-12 ">
			<label class="control-label" >原因/备注：</label>
			<div>
				<textarea id="abandonedReason" name="abandonedReason"  class="form-control field-editable allText" data-parsley-required="true"  rows="6"></textarea>
			</div>
		</div>
		<div class="form-group col-md-6 col-sm-12 ">
			<label class="control-label">操作人：</label>
			<div>
				<input type="text"  name="operator" value="${_operator}" class="form-control field-editable allText" readonly="readonly" />
			</div>
		</div>
		<div class="form-group col-md-6 col-sm-12 ">
			<label class="control-label">操作时间：</label>
			<div>
				<input type="text" name="operatingTime"  value="${_operatingTime}" class="form-control field-editable allText input-sm datepicker-default" readonly="readonly"  />
			</div>
		</div>
	</form>
</div>

<script>
    App.restartGlobalFunction();
    $("#recoveryPopForm").styleFit();

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });


</script>
