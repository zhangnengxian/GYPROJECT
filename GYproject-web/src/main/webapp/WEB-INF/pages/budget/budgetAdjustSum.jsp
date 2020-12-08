<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 saveBtn" >保存</a>
	</div>
	<div class="form-box clearboth">
		<!-- 增加表单 -->
		<form class="form-horizontal" id="budgetSumForm" data-parsley-validate="true">
		    <input type="reset" class="hidden" id="formReset" /> 
			<input type="hidden" name="projId" id="projId" />
			<input type="hidden" name="budgetId" id="budgetId" />
		<!-- 	<input type="hidden" name="projLtypeId" id="projLtypeId" /> -->
			<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
				<label class="control-label" for="">总造价</label>
	            <div>
	            	<input type="text" class="form-control input-sm field-editable fixed-number" data-parsley-required="true" id="budgetTotalCost" name="budgetTotalCost" value=""/>
	            </div>
			</div>
	        <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">民用土建</label>
	            <div>
	        		<input type="text" class="form-control input-sm field-editable pro-val fixed-number"  id="civilCost" name="civilCost" value=""/>
	            </div>
	        </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">民用庭院</label>
	              <div>
	           <input type="text" class="form-control input-sm field-editable pro-val fixed-number"    id="yardInstallCost" name="yardInstallCost" value=""/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">民用户内</label>
	              <div>
	           <input type="text" class="form-control input-sm field-editable pro-val fixed-number"   id="homeInstallCost" name="homeInstallCost" value=""/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">仪表</label>
	              <div>
	           <input type="text" class="form-control input-sm field-editable fixed-number pro-val"   id="boilerMeter" name="boilerMeter" value=""/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">工艺</label>
	              <div>
	           <input type="text" class="form-control input-sm field-editable fixed-number pro-val"  id="technology" name="technology" value=""/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">土建</label>
	              <div>
	           <input type="text" class="form-control input-sm field-editable fixed-number pro-val"  id="yardEarthwork" name="yardEarthwork" value=""/>
	             </div>
	          </div> 
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">监理费</label>
	              <div>
	           <input type="text" class="form-control input-sm field-editable fixed-number"  data-parsley-required="true" id="suCost" name="suCost" value=""/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">监检费</label>
	              <div>
	           <input type="text" class="form-control input-sm field-editable fixed-number"  data-parsley-required="true" id="inspectionCost" name="inspectionCost" value=""/>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">储备金</label>
	              <div>
	           <input type="text" class="form-control input-sm field-editable fixed-number"  data-parsley-required="true" id="storeCost" name="storeCost" value=""/>
	             </div>
	          </div>
	          
	           <div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
	              <label class="control-label" for="">其他金额</label>
	              <div>
	              <input type="text" class="form-control input-sm fixed-number"  id="otherCost1" name="otherCost1" value=""/>
	             </div>
	          </div>
	        
		</form>
	</div>
</div>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
	$("#budgetSumForm").hideMask().styleFit();
	
	var changeVal;
	
	trSData.t && trSData.t.cgetDetail('budgetSumForm','budgetAdjust/queryBudge','',budgetCallback);
	
	$(".saveBtn").on("click",function(){
		$("#budgetSumForm").formSave("budgetAdjust/updateBudge","budgetAdjustTable",saveBack);	
		 /* if($("#budgetSumForm").parsley().isValid()){
	        	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
	        	var data=$("#budgetSumForm").serializeJson();
	        	$.ajax({
	                type: 'POST',
	                url: 'budgetAdjust/updateBudget',
	                contentType: "application/json;charset=UTF-8",
	                data: JSON.stringify(data),
	                success: function (data) {
	                	var content = "数据保存成功！";
	                	if(data === "false"){
	                		content = "数据保存失败！";
	                	}else if(data === "true"){
	                		$("#budgetSumForm input").val('');
	                		$("#budgetAdjustTable").cgetData();  //列表重新加载
	                	}
	                	var myoptions = {
	                        	title: "提示信息",
	                        	content: content,
	                        	accept: popClose,
	                        	chide: true,
	                        	icon: "fa-check-circle"
	                    }
	                    $("body").cgetPopup(myoptions);
	                },
	                error: function (jqXHR, textStatus, errorThrown) {
	                    console.warn("预算调整区预算总表保存失败！");
	                }
	            });
	        	//如果通过验证, 则移除验证UI
	        	//$("#surveyDetailform").parsley().validate();
	        	$("#budgetSumForm").parsley().reset();
	        }else{
	        	//如果未通过验证, 则加载验证UI
	        	$("#budgetSumForm").parsley().validate();
	        } */
	});

	 function saveBack(data){
		console.info(1);
		$("#budgetAdjustTable").on("draw.dt",function(){
			console.info(2 + " / " + $("#budgetAdjustTable").getDTJson().length);
			if($("#").getDTJson().length<1){
				$("#budgetSumForm").formReset();
			}
		});		
	} 
	//其他费用改变时触发总金额改变
		$("#otherCost1").on("blur",function(){
			budgetCallback();
		});
	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->