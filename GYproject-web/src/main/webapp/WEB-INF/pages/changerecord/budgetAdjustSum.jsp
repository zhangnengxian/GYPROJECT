<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 saveBudgetBtn " >保存</a>
	</div>
	<div class="form-box clearboth">
		<!-- 增加表单 -->
		<form class="form-horizontal" id="budgetSumForm" data-parsley-validate="true" action="changeRecord/updateBudge">
		    <input type="reset" class="hidden" id="formReset" /> 
			<input type="hidden" name="projId" id="projIdBudget" />
			<input type="hidden" name="budgetId" id="budgetId" />
		<!-- 	<input type="hidden" name="projLtypeId" id="projLtypeId" /> -->
			<div class="form-group col-md-6 col-sm-12 ">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200" value=''></textarea>
	            </div>
            </div>
		   <!--  <div class="form-group col-md-12 ">
		        <label class="control-label" for="duName">申报单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""/>
		        </div>
		    </div> -->
		    <div class="form-group col-md-6">
		        <label class="control-label" for="surveyer">勘察人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="surveyer"  name="surveyer" data-parsley-maxlength="20" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="designer">设计人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="designer"  name="designer" data-parsley-maxlength="20" value="">
		        </div>
		    </div>
		    <!-- 预算人员信息 -->
		    <input type="hidden" id="budgeterId" name="budgeterId"/>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="budgeter">预算人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="budgeter"  name="budgeter" data-parsley-maxlength="20" value="">
		        </div>
		    </div>
		    <div class="form-group col-lg-12 col-md-12 col-sm-6 auditResult hidden">
       			<label class="control-label" for="">审批结果</label> 
            	<div>
             		 <label>
		            	<input type="radio" class="field-editable" name="auditResult" value="1" checked/>通过
		            </label>
		             <label>
		            	<input type="radio" class="field-editable" name="auditResult" value="2"/>不通过
		            </label>
		        </div>
  			</div>
  			<div class="form-group col-md-12 auditResult auditOpinion hidden ">
	            <label class="control-label" for="auditOpinion">审核意见</label>
	            <div>
	                <textarea class="form-control field-editable" name ="auditOpinion" id="auditOpinion" rows="3" data-parsley-maxlength="500" value=''data-parsley-required="true"></textarea>
	            </div>
            </div>
		    <!-- 总造价作为调整预算 -->
			<div class="form-group  col-md-6 clearboth">
				<label class="control-label" for="">调整预算金额</label>
	            <div>
	            	<input type="text" class="form-control input-sm  fixed-number field-editable" id="budgetTotalCost" name="budgetTotalCost" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-required="true"/>
	            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
			</div>
			
	        <div class="form-group col-md-12">
	            <label class="control-label" for="remark">备注</label>
	            <div>
	                <textarea class="form-control field-editable" name ="remark" id="remark" rows="5" data-parsley-maxlength="400" value=''></textarea>
	            </div>
            </div>
	        <input id="mcTypeBudget" name="mcType" type="hidden" />
	        <input type="hidden" id="cmIdBudget" name="cmId"/>
	        
		</form>
		<div class="clearboth auditHistoryDiv">
		<div class="">
 		<h4 class="m-t-15 m-l-7"><strong>审核历史</strong></h4>
 		</div>
 		<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
  		 <thead>
          	<tr>
              <th>确认日期</th>
              <th>确认结果</th>
              <th>确认意见</th>
              <th>确认人</th>
        	</tr>
     		</thead>
		</table>
	</div>
</div>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
	//$("#budgetSumForm").hideMask().styleFit();
	$("#budgetSumForm").toggleEditState(false).styleFit();
	if(!trSData.changeRecordTable.t){
		state=1;
      }
	var changeVal;
	
	trSData.t && trSData.t.cgetDetail('budgetSumForm','changeRecord/queryBudge?projId='+$("#projId").val()+'&cmId='+$("#cmId").val()+'&mcType='+$("#mcType").val(),'',budgetCallback);
	//hiddenOpt(state);
	$(".saveBudgetBtn").on("click",function(){
		$('#mcTypeBudget').val($("#mcType").val());
		$('#cmIdBudget').val($("#cmId").val());
		$("#projIdBudget").val($("#projId").val());
		console.info($('#budgetSumForm').serializeJsonString()+"------------");
		
		if($("#changeRecordTab").parent().hasClass("active")){
			//变更页签
			$("#budgetSumForm").cformSave('changeRecordTable',saveBack,true,'',true);	
		}else{
			$("#budgetSumForm").cformSave('visaRecordTable',saveBack,true,'',true);	
		}
	});
	function saveBack(data){
		 $('.saveBudgetBtn').addClass("hidden");
		 /*$("#budgetSumForm").toggleEditState(false);
		if($('#mcTypeBudget').val()=='1'){//签证
			$('#confirmBtn').removeClass("hidden");
			$('#confirmBtn1').addClass("hidden");
		}else{
			$('#confirmBtn1').removeClass("hidden");
			$('#confirmBtn').addClass("hidden");
		} */
		if($("#changeRecordTab").parent().hasClass("active")){
			$("#changeRecordTable").cgetData(true);
		}else{
			$("#visaRecordTable").cgetData(true,cgetDataBack,true);
		}
	} 
	function cgetDataBack(){
	};
	//其他费用改变时触发总金额改变
	$("#otherCost1").off("change").on("change",function(){
		budgetCallback();
	});
	
 /* 	输入数字校验 */ 
      $('.fixed-number').on('keyup', function(){
      	  $(this).parsley().validate();
      }); 
	
      $('input:radio[name="auditResult"]').change(function(){
    	  console.info("$(this).val()--"+$(this).val());
    	  if($(this).val()=="2"){//不通过
    		  $(".auditOpinion").removeClass("hidden");
    	  }else{
    		  $(".auditOpinion").addClass("hidden");
    	  }
      })
 	handleAuditHistory();
 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->