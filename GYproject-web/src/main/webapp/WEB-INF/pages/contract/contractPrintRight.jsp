<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<!-- <div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div> -->
	<%-- <input type="hidden" id="alreadyPrint" value="${alreadyPrint}"/>
	<input type="hidden" id="haveNotPrint" value="${haveNotPrint}"/> --%>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="contractDetailform"  data-parsley-validate="true" action="contractPrint/saveContract">
       		<input type="hidden" name="conId" id="conId"/>
       		<input type="hidden" name="projId" id="projId"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="conNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable conNo"  id="conNo" name="conNo" value="" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
	        	<label class="control-label" for="paNo">受理单号</label>
	        	<div>
	        		<input type="text" class="form-control input-sm field-not-editable" id="paNo" name="paNo"/>
	        	</div>
      	    </div>
      	    <div class="form-group col-md-6">
		    	<label class="control-label" for="isPrint">是否打印</label>
            	<div>
                <select class="form-control input-sm field-not-editable"  id="isPrint"  name="isPrint" >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${isPrint}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		    	<label class="control-label" for="areaDes">大区</label>
            	<div>
                	<input type="text" class="form-control input-sm field-not-editable"  id="areaDes" name="areaDes" data-parsley-maxlength="100" value=""/>
	            </div>
			</div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2" data-parsley-maxlength="200"></textarea>
	            </div>
            </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="custName">甲方名称(主)</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="custName">甲方名称(副)</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="secCustName" name="secCustName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="custName">甲方名称(三)</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="thirdCustName" name="thirdCustName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="custPhone">甲方联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="13" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custLegalRepresent">甲方法定代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custLegalRepresent" name="custLegalRepresent" data-parsley-maxlength="20" value="" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="custEntrustRepresent">甲方委托代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custEntrustRepresent" name="custEntrustRepresent" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 clearboth ">
		        <label class="control-label" for="gasComp">乙方名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="gasComp" name="gasComp" data-parsley-maxlength="50" value="新疆燃气(集团)有限公司 "/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasCompPhone">乙方联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="gasCompPhone" name="gasCompPhone" data-parsley-maxlength="13" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="gasComPlegalRepresent">乙方法定代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="gasComPlegalRepresent" name="gasComPlegalRepresent" data-parsley-maxlength="20" value="" />
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasCompEntrustRepresent">乙方委托代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="gasCompEntrustRepresent" name="gasCompEntrustRepresent" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-12">
	            <label class="control-label" for="conScope">承包范围</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="conScope" id="conScope" rows="2" data-parsley-maxlength="200">燃气图纸所包含的全部范围  包工包料</textarea>
	            </div>
            </div>
            <div class="form-group col-md-6">
		        <label class="control-label" for="plannedStartDate">计划开工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="plannedStartDate"  name="plannedStartDate" data-parsley-maxlength="100" value="" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="plannedEndDate">计划竣工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="plannedEndDate"  name="plannedEndDate" data-parsley-maxlength="100" value="" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="timeLimit">工期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="timeLimit"  name="timeLimit" data-parsley-maxlength="10" value="配合" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		    	<label class="control-label" for="fundSource">资金来源</label>
            	<div>
                <select class="form-control input-sm field-not-editable" id="fundSource"  name="fundSource"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${fundSource}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="qualityStandar">质量标准</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="qualityStandar"  name="qualityStandar" data-parsley-maxlength="50" value="合格" >
		        </div>
		    </div>
			<div class="form-group col-md-6">
	            <label class="control-label" for="payType">付款方式</label>
            	<div>
               	   <select class="form-control input-sm field-not-editable" id="payType"  name="payType"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${payType}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div>
			<div class="form-group col-md-6 clearboth firstPayment" style="display:none;">
		        <label class="control-label" for="firstPayment">首付款</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="firstPayment"  name="firstPayment" data-parsley-maxlength="100" >
		        </div>
		    </div>
		   
		    <div class="form-group col-md-6">
		        <label class="control-label" for="budgetCost">确定造价</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="budgetCost"  name="budgetCost" data-parsley-maxlength="100" value="" >
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="contractAmount">合同金额</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="contractAmount"  name="contractAmount" data-parsley-maxlength="13" value="" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="conAgent">经办人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="conAgent"  name="conAgent" data-parsley-maxlength="20" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="signDate">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="signDate"  name="signDate" data-parsley-maxlength="100" value="" data-parsley-required="true">
		        </div>
		    </div>
			<div class="form-group col-md-6">
		        <label class="control-label" for="contractCopies">合同份数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="contractCopies"  name="contractCopies" data-parsley-maxlength="2" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="partyACopies">甲方份数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="partyACopies"  name="partyACopies" data-parsley-maxlength="2" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="partyBCopies">乙方份数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="partyBCopies"  name="partyBCopies" data-parsley-maxlength="2" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
	        	<label class="control-label" for="contractPricingType">价格形式</label>
            	<div>
                <select class="form-control input-sm field-not-editable" id="contractPricingType"  name="contractPricingType"  data-parsley-required="true">
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${contractPricingType}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div>
			<div class="form-group col-md-12 riskRange">
	            <label class="control-label" for="riskRange">风险范围</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="riskRange" id="riskRange" rows="4" data-parsley-maxlength="1000"></textarea>
	            </div>
            </div>
			<div class="form-group col-md-12 adjustmentMethod">
		        <label class="control-label" for="adjustmentMethod">调整方法</label>
		        <div>
		           <textarea class=" form-control  field-not-editable " id="adjustmentMethod" rows="2" name="adjustmentMethod" data-parsley-maxlength="200" ></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-12 adjustMethod">
		        <label class="control-label" for="adjustMethod">调整方法</label>
		        <div>
		           <textarea class=" form-control  field-not-editable " id="adjustMethod" rows="2" name="adjustMethod" data-parsley-maxlength="200" ></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="supplementClause">补充条款</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="supplementClause" id="supplementClause" rows="2" data-parsley-maxlength="1000">
	                </textarea>
	            </div>
            </div>
		</form>
    </div>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    //表单样式适应
    $("#contractDetailform").toggleEditState(false).styleFit();
    //$('.editbtn').addClass('hidden');
    /* var s=function(){
    	if($("#contractDetailform").toggleEditState()){
    		$("#contractDetailform").toggleEditState(true);
    	}
    }(); */
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    //查右侧工程详述
    trSData.t && trSData.t.cgetDetail('contractDetailform','contractPrint/viewContract','',contractPrintBack2); 
    
    function contractPrintBack2(){
    	if($("#contractPricingType option:selected").val()==="2"){
    	    $('.adjustmentMethod').addClass('hidden');
    	    $('.riskRange').addClass('hidden');
    	    $(".adjustMethod").removeClass("hidden");
    	    $("#contractDetailform").styleFit();
    	}else{
    		$(".adjustMethod").addClass("hidden");
    		$('.adjustmentMethod').removeClass('hidden');
    		$('.riskRange').removeClass('hidden');
    		$("#contractDetailform").styleFit();
        } 
    }
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
    	$('#contractDetailform').cformSave('contractPrintTable',saveContractBack,true);
    }); 
    
    var saveContractBack=function(){
    	$('.editbtn').addClass('hidden');
    };
    
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	 /* //清空表单
    	 $("#surveyDetailform input").val('');
    	 trSData.t.cgetDetail('contractDetailform','','',contractPrintBack); */
    	$("#contractDetailform").toggleEditState(false);
    	$('.editbtn').addClass('hidden');
    });
 	
    //首付款其它形式
    var  changePayType= function(){
    	$("#payType").change(function(){
            if($(this).val()=="3"){
            	//首付款录入
            	$('.firstPayment').show();
            	$("#contractDetailform").styleFit();
            }else{
            	$('.firstPayment').hide();
            }
    	});
    }();
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->