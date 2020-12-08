<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<h3>预算总表</h3>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="detail_budgetSumForm">
			<input type="hidden" class="budget_projId" value="${budget.projId}"/>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">总造价</label>
				<div>
					<input type="text" class="form-control input-sm field-editable fixed-number" id="" name="" value="${budget.budgetTotalCost }" />
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
				<label class="control-label" for="">土建工程费</label>
				<div>
					<input type="text" class="form-control input-sm field-editable fixed-number" id="" name="" value="${budget.civilCost }" />
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">庭院安装费</label>
				<div>
					<input type="text" class="form-control input-sm field-editable fixed-number" id="" name="" value="${budget.yardInstallCost }" />
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">户内安装费</label>
				<div>
					<input type="text" class="form-control input-sm field-editable fixed-number" id="" name="" value="${budget.homeInstallCost }" />
				</div>
			</div>
			<%-- <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">棚区安装费</label>
				<div>
					<input type="text" class="form-control input-sm field-editable fixed-number" id="" name="" value="${budget.shantyTownCost }" />
				</div>
			</div> --%>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">监理费</label>
				<div>
					<input type="text" class="form-control input-sm field-editable fixed-number" id="" name="" value="${budget.suCost }" />
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">监检费</label>
				<div>
					<input type="text" class="form-control input-sm field-editable fixed-number" id="" name="" value="${budget.inspectionCost }" />
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">储备金</label>
				<div>
					<input type="text" class="form-control input-sm field-editable fixed-number" id="" name="" value="${budget.storeCost }" />
				</div>
			</div>
		</form>
	</div>
</div>
<h3>单位工程费用记录</h3>
<div class="clearboth p-t-15">
	<div class="clearboth form-box" style="margin-bottom:-36px">
		<form class="form-horizontal" id="costSummarySearchForm" action="/" method="POST">
			<div class="form-group col-lg-5 col-md-5 col-sm-5">
	            <label class="control-label" for="">费用名称</label>
	            <div >
	                <input type="text" class="form-control input-sm"  name="costName"/>
	            </div>
	        </div>
	        <div class="form-group col-lg-5 col-md-5 col-sm-5">
				<label class="control-label" for="manuId">费用类型</label>
			    <div>
	                <select class="form-control input-sm"  name="costType">
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${costType}">
							<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
	              </select>
	            </div>
			</div>
		</form>
	</div>
	<table id="costSummaryTable" class="table table-hover table-bordered nowrap m-t-40" width="100%">
		<thead>
         	<tr>
              <!-- <th width="40">序号</th> -->
              <th>费用名称</th>
              <th>费用类型</th>
              <th>费率</th>
              <th>金额</th>
              <th>取费说明</th>
         	</tr>
    	</thead>
  	</table>

</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
	//隐藏loading
	$(".infodetails").hideMask();
	//参数传false时，表单不可编辑
	$("#detail_budgetSumForm,#costSummarySearchForm").toggleEditState().styleFit();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->