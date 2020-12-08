<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/projectjs/project/project-manage-record.js"></script>
<div class="clearboth form-box">
	<form class="form-horizontal" id="searchForm">
		<div class="form-group col-md-12 col-sm-12">
			<label class="control-label" >步骤：</label>
			<div>
				<select id="stepIds" onchange="changeStepIs(this.value)" class="form-control input-sm field-editable" data-parsley-required="true">
					<option value="" ></option>
					<c:forEach var="stepIds" items="${stepIds}">
						<option value="${stepIds.stepId}" >${stepIds.stepName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</form>
</div>

<div class="clearboth p-t-15">
	<input type="hidden" id="projId" value="${ projId }">
	<input type="hidden" id="stepId" value="${ stepId }">
	<div >
		<h4 class="m-t-15 m-l-7"><strong>审批历史</strong></h4>
	</div>
	<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
		<thead>
		<tr>
			<th>审批步骤</th>
			<th>审批级别</th>
			<th>审批结果</th>
			<th>审批意见</th>
			<th>审批人</th>
			<th>审批日期</th>
		</tr>
		</thead>
	</table>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
	$("#searchForm").styleFit();
    //隐藏loading
    $(".infodetails").hideMask();

    var searchData={};
	searchData.projId=$("#projId").val();
	searchData.stepId=$("#stepId").val();

	ManageRecord.initTable("auditHistoryTable",searchData);//审核列表初始化

	function changeStepIs(stepId) {
		searchData.stepId=stepId;
		ManageRecord.initTable("auditHistoryTable",searchData);
	}

</script>
