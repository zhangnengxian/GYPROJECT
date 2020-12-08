<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="clearboth form-box">
	<input type="hidden" id="projId" name="projId" value="${projId}">
	<form class="form-horizontal" id="abandonedRecordForm">
		<div class="form-group col-md-12 col-sm-12">
			<label class="control-label" >回退历史步骤：</label>
			<div>
				<select id="stepId" name="stepId" class="form-control input-sm " onchange="queryAbandonedRecord(this.value)" data-parsley-required="true">
					<c:forEach var="map" items="${mapList}">
						<option value="${map.stepId}">${map.stepName }</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group col-md-12 col-sm-12 ">
			<label class="control-label" >原因/备注：</label>
			<div>
				<textarea   class="form-control field-editable allText abandonedReason"  rows="7"> ${abandonedRecord.abandonedReason}</textarea>
			</div>
		</div>
		<div class="form-group col-md-6 col-sm-12 ">
			<label class="control-label">操作时间：</label>
			<div>
				<input type="text"  value="${abandonedRecord.operatingTime}" class="form-control field-editable allText input-sm datepicker-default operatingTime"  />
			</div>
		</div>

		<div class="form-group col-md-6 col-sm-12 ">
			<label class="control-label">操作人：</label>
			<div>
				<input type="text"  value="${abandonedRecord.operator}" class="form-control field-editable allText operator"  />
			</div>
		</div>
		<hr style="width: 100%"/>
		<c:choose >
			<c:when test="${loginPost.contains(adminPost)}">
				<div class="form-group col-md-12 col-sm-12 " style="color: #ffb394"> >>数据日志 </div>
				<div class="form-group col-md-12 col-sm-12 ">
					<textarea   class="form-control field-editable allText abandonedData"  rows=10> ${abandonedRecord.abandonedData}</textarea>
				</div>
				<hr style="width: 100%"/>
				<div class="form-group col-md-12 col-sm-12 "  style="color: #8a92ff">  >>SQL语句 </div>
				<div class="form-group col-md-12 col-sm-12 ">
					<textarea  class="form-control field-editable allText insertSql"  rows=12> ${abandonedRecord.insertSql}</textarea>
				</div>
			</c:when>
			<c:otherwise>
				<div class="form-group col-md-12 col-sm-12 " style="color: #ffb394"> >>数据日志 </div>
				<div class="form-group col-md-12 col-sm-12 ">
					<textarea  class="form-control field-editable allText abandonedData"  rows=15> ${abandonedRecord.abandonedData}</textarea>
				</div>
			</c:otherwise>
		</c:choose>
	</form>
</div>

<script>
    App.restartGlobalFunction();
    $("#abandonedRecordForm").styleFit();

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    $(function () {
        $("#abandonedRecordForm").toggleEditState(false);
    })


	function queryAbandonedRecord(stepId) {
    	var data={projId:$('#projId').val(),stepId:stepId};
    	Base.subimt("dataTableInfo/queryAbandonedRecord","POST",data,function (data) {
				data.replace(/[\r\n]/g,"");
				data = eval('(' + data + ')');
    		$('.abandonedReason').val(data.abandonedReason);
    		$('.operatingTime').val(data.operatingTime);
    		$('.operator').val(data.operator);
    		$('.abandonedData').val(data.abandonedData);
    		$('.insertSql').val(data.insertSql);
		})

	}

</script>
