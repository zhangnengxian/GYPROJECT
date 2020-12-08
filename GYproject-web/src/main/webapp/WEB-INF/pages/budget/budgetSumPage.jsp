<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 saveBtn" >确认</a>
       <!--  <a href="javascript:;" class="btn  btn-warn btn-sm m-l-5  cancelBtn">放弃</a> -->
	</div>
	<div class="form-box clearboth">
		<!-- 增加表单 -->
		<input  type="hidden" name="postBudgeter" id="postBudgeter" value="${postBudgeter}"/>
		<form class="form-horizontal" id="budgetSumForm" data-parsley-validate="true">
			<input type="reset" class="hidden" id="formReset" />
			<input type="hidden" name="projId" id="projId" />
			<input type="hidden" name="budgetId" id="budgetId" />
			<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
				<label class="control-label" for="">总造价</label>
	            <div>
	            	<input type="text" class="form-control input-sm  fixed-number" data-parsley-required="true" data-parsley-min="0.01" id="budgetTotalCost" name="budgetTotalCost" value=""/>
	            </div>
			</div>
	        <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">民用土建</label>
	            <div>
	        		<input type="text" class="form-control input-sm  pro-val fixed-number"  id="civilCost" name="civilCost" value=""/>
	            </div>
	        </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">民用庭院</label>
	              <div>
	           <input type="text" class="form-control input-sm  pro-val fixed-number"    id="yardInstallCost" name="yardInstallCost" value=""/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">民用户内</label>
	              <div>
	           <input type="text" class="form-control input-sm  pro-val fixed-number"   id="homeInstallCost" name="homeInstallCost" value=""/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">仪表</label>
	              <div>
	           <input type="text" class="form-control input-sm  fixed-number pro-val"   id="boilerMeter" name="boilerMeter" value=""/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">工艺</label>
	              <div>
	           <input type="text" class="form-control input-sm  fixed-number pro-val"  id="technology" name="technology" value=""/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">土建</label>
	              <div>
	           <input type="text" class="form-control input-sm  fixed-number pro-val"  id="yardEarthwork" name="yardEarthwork" value=""/>
	             </div>
	          </div> 
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">监理费</label>
	              <div>
	              <!-- data-parsley-min="0.01" -->
	           	<input type="text" class="form-control input-sm  fixed-number"    id="suCost" name="suCost" value=""/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">监检费</label>
	              <div>
	           <input type="text" class="form-control input-sm  fixed-number"    id="inspectionCost" name="inspectionCost" value=""/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">储备金</label>
	              <div>
	           <input type="text" class="form-control input-sm  fixed-number"    id="storeCost" name="storeCost" value=""/>
	             </div>
	          </div>
	           <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">其他金额</label>
	              <div>
	              <input type="text" class="form-control input-sm pro-val"  id="otherCost1" data-parsley-type='number' name="otherCost1" value=""/>
	             </div>
	           </div>
	        	<div class="form-group col-md-6">
			        <label class="control-label" for="budgeter">预算员</label>
			        <div>
			           <input type="text" class=" form-control input-sm " id="budgeter"  name="budgeter">
			           <a id="budgetPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择预算员"><i class="fa fa-search"></i></a>
			        </div>
		    	</div>
		    	<input type="hidden" name="budgeterId" id="budgeterId" />
	        	<div class="form-group col-md-12">
	            <label class="control-label" for="supplementClause">备注</label>
	            <div>
	                <textarea class="form-control" name ="note" id="note" rows="2" data-parsley-maxlength="200"></textarea>
	            </div>
            </div>
	        	
		</form>
	</div>
</div>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
	$("#budgetSumForm").hideMask();
	$("#budgetSumForm").toggleEditState().styleFit();
	var changeVal;
	if(trSData.budgetRegisterTable.t){
		$(".editbtn").removeClass("hidden");
	   trSData.budgetRegisterTable.t.cgetDetail('budgetSumForm','budgetResultRegister/queryBudge','',budgetCallback);
	}else{
		$(".editbtn").addClass("hidden");
	}
	$(".saveBtn").on("click",function(){
		var myoptions = {
               	title: "保存确认",
               	content: "是否确认保存信息",
               	accept: acceptForm,
               	cancel: popClose,
               	chide: true,
               	icon: "fa-check-circle"
           }
		$("body").cgetPopup(myoptions);
	});

	function acceptForm(){
		$("#budgetSumForm").formSave("budgetResultRegister/updateBudge","budgetRegisterTable",budgetRegisterTable,saveBack);	
	}
	
	function saveBack(data){			
	 $("#budgetRegisterTable").cgetData("",budgetBack); 		
	}
	//其他费用改变时触发总金额改变
	$("#otherCost1").on("change",function(){
		budgetCallback();
	});
    //选择预算员
    
    var postBudgeter=$('#postBudgeter').val();
    $("#budgetPop").off().on("click",function(){
    	staffPopup({
    		'budgeter':'staffName',
    		'budgeterId':'staffId'
    	},postBudgeter);
  	});
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->