<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn  btn-warn btn-sm m-l-5  cancelBtn">放弃</a>
	</div>
	<div class="form-box clearboth">
		<!-- 增加表单 -->
		<form class="form-horizontal" id="budgetCostForm" >
			<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
				<label class="control-label" for="">人工费</label>
	            <div>
	            	<input type="text" class="form-control input-sm field-editable"  id="" name="" value="148835.29"/>
	            </div>
			</div>
	        <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">主材费</label>
	            <div>
	        		<input type="text" class="form-control input-sm field-editable"  id="" name="" value="150000.00"/>
	            </div>
	        </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">辅材费</label>
	              <div>
	           <input type="text" class="form-control input-sm field-editable"  id="" name="" value="99223.53"/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">机械使用费</label>
	              <div>
	           <input type="text" class="form-control input-sm field-editable"  id="" name="" value="89301.17"/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">其他间接费</label>
	              <div>
	           <input type="text" class="form-control input-sm field-editable"  id="" name="" value="19844.71"/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">间接成本</label>
	              <div>
	           <input type="text" class="form-control input-sm field-editable"  id="" name="" value="14883.53"/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">预算造价</label>
	              <div>
	              <input type="text" class="form-control input-sm field-editable"  id="" name="" value="522088.23"/>
	             </div>
	          </div>
	          
		</form>
	</div>
</div>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
	$("#budgetCostForm").hideMask().styleFit();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->