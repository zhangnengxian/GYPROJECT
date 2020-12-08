<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="infodetails">
	<h3>分包工程量列表</h3>
	<div class="clearboth form-box" style="margin-bottom:-36px">
		<form class="form-horizontal" id="subQuantitiesSearchForm" action="/" method="POST">
	        <div class="form-group col-lg-5 col-md-5 col-sm-5">
				<label class="control-label" for="">分包合同编号</label>
				 <div>
	                <input type="text" class="form-control input-sm"  name="scNo"/>
	            </div>
			</div>
		</form>
	</div>
    <table id="subQuantitiesTable" class="table table-hover table-bordered nowrap m-t-40" width="100%">
		<thead>
         	<tr>
              <th>分项名称</th>
              <th>单位</th>
              <th>单价</th>
              <th>数量</th>
              <th>进度总量</th>
         	</tr>
    	</thead>
	</table>
	<h3>分包工程量详述</h3>
	<form class="form-horizontal" id="subQuantitiesDetailform" data-parsley-validate="true" action="">
		<div class="form-group col-md-6 ">
				<label class="control-label" for="">分包合同编号</label>
				 <div>
	                <input type="text" class="form-control input-sm  field-not-editable"  name="scNo"/>
	            </div>
			</div>
		<div class="form-group col-md-6 clearboth">
			<label class="control-label" for="">分项名称</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable" name="sqBranchProjName" value="" />
			</div>
		</div>
		<div class="form-group col-md-6 ">
			<label class="control-label" for="">造价类型</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable" name="sqCostType" value="" />
			</div>
		</div>
		<div class="form-group col-md-6 ">
			<label class="control-label" for="">单位</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable" name="sqUnit" value="" />
			</div>
		</div>
		<div class="form-group col-md-6 ">
			<label class="control-label" for="">单价</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable fixed-number" name="sqLabourPrice" value="" />
			</div>
		</div>
		<div class="form-group col-md-6 ">
			<label class="control-label" for="">数量</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable fixed-number" name="sqNum" value="" />
			</div>
		</div>
		<div class="form-group col-md-6 ">
			<label class="control-label" for="">金额</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable fixed-number" name="sqAmount" value="" />
			</div>
		</div>
		<div class="form-group col-md-6 ">
			<label class="control-label" for="">变更总量</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable fixed-number" name="changeAnum" value="" />
			</div>
		</div>
		<div class="form-group col-md-6 ">
			<label class="control-label" for="">进度总量</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable fixed-number" name="progressAnum" value="" />
			</div>
		</div>
		<div class="form-group col-md-6 ">
			<label class="control-label" for="">竣工总量</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable fixed-number" name="completeAnum" value="" />
			</div>
		</div>
		<div class="form-group col-md-6 ">
			<label class="control-label" for="">结算总量</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable fixed-number" name="settlementAnum" value="" />
			</div>
		</div>
		<div class="form-group col-md-12 ">
			<label class="control-label" for="sqWorkContent">工作内容及项目特征</label>
			<div>
				<textarea class="form-control field-not-editable" name="sqWorkContent" rows="3"></textarea>
			</div>
		</div>
	</form>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    $(".infodetails").hideMask();
    $("#subQuantitiesSearchForm,#subQuantitiesDetailform").toggleEditState(false).styleFit();
    subQuantitiesTableInitFunction();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->