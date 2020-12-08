<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="infodetails">
	<h3>分包合同列表</h3>
	<div class="clearboth form-box" style="margin-bottom: -36px">
		<form class="form-horizontal" id="subcontractSearchForm" action="/"
			method="POST">
			<div class="form-group col-lg-5 col-md-5 col-sm-5">
				<label class="control-label" for="manuId">合同编号</label>
				<div>
					<input type="text" class="form-control input-sm" name="scNo" />
				</div>
			</div>
		</form>
	</div>
	<table id="subcontractTable"
		class="table table-hover table-bordered nowrap m-t-40" width="100%">
		<thead>
			<tr>
				<th>合同编号</th>
				<th>施工单位</th>
				<th>承包方式</th>
				<th>计划天数</th>
				<th>签订日期</th>
			</tr>
		</thead>
	</table>
	<h3>分包合同详述</h3>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="subContractDetailform" data-parsley-validate="true" action="">
			<div class="form-group col-md-6 ">
				<label class="control-label" for="scNo">合同编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="scNo" value="" />
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="managementOffice">施工单位</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"name="managementOffice"value="" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="projCompDirector">甲方委托代表</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="projCompDirector" value="" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="projCompPm">甲方项目经理</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="projCompPm" value="" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="gasComLegalRepresent">甲方代表</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="gasComLegalRepresent" value="" />
				</div>
			</div>
			<div class="form-group col-md-6 ">
				<label class="control-label" for="gasComPhone">联系方式</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="gasComPhone" value="" />
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="cuName">分包单位</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="cuName"value="" /> 
				</div>
			</div>
			<div class="form-group col-md-6 ">
				<label class="control-label" for="cuLegalRepresent">乙方法定代表</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="cuLegalRepresent"value="" />
				</div>
			</div>
			<div class="form-group col-md-6 ">
				<label class="control-label" for="cuPm">乙方项目经理</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="cuPm" value="" />
				</div>
			</div>
			<div class="form-group col-md-6 ">
				<label class="control-label" for="cuDirector">乙方委托代表</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="cuDirector" value="" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="cuPmPhone">联系方式</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"name="cuPmPhone" value="" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="certificationName">资格证名称</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"
						id="certificationName" name="certificationName"
						data-parsley-maxlength="100" value="" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="certificationNo">资格证编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="certificationNo" value="" />
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="scCope">承包范围</label>
				<div>
					<textarea class="form-control field-not-editable" name="scScope" rows="3"></textarea>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="scType">承包方式</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="scType"value="" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="scPlannedTotalDays">计划天数</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable" name="scPlannedTotalDays" value="">
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="scPlannedStartDate">开工日期</label>
				<div>
					<input type="text"
						class=" form-control input-sm field-not-editable datepicker-default" name="scPlannedStartDate" value="">
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="scPlannedEndDate">竣工日期</label>
				<div>
					<input type="text"
						class=" form-control input-sm field-not-editable datepicker-default" name="scPlannedEndDate" value="">
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="qualityStandar">质量标准</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="qualityStandar" value="" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="scAmount">合同价款</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable fixed-number" name="scAmount" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="scSignDate">签订日期</label>
				<div>
					<input type="text"
						class=" form-control input-sm field-not-editable" name="scSignDate" value="">
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="conAgent">经办人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="conAgent" value="" />
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="remark">备注</label>
				<div>
					<textarea class="form-control field-not-editable" name="remark" rows="3"></textarea>
				</div>
			</div>
		</form>
	</div>
</div>
<div class="clearboth p-t-15"></div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
	$(".infodetails").hideMask();
	$("#subcontractSearchForm,#subContractDetailform").toggleEditState(false).styleFit();
	subcontractTableInitFunction();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->