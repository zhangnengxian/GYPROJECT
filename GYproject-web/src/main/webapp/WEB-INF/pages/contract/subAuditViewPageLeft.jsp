<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<div class="infodetails">
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="contractDetailform"  data-parsley-validate="true" action="">
       		<input type="hidden" name="projId" id="projId" value="${subContract.projId}"/>
       		<input type="hidden" name="scId" id="scId" value="${subContract.scId}"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="conNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"   id="conNo" name="conNo" value="${subContract.scNo}"/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value="${subContract.projNo}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="100" value="${subContract.projName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value="${subContract.projAddr}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		        	<textarea class="form-control input-sm field-not-editable" name="projScaleDes" rows="" cols="" data-parsley-maxlength="200">${subContract.projScaleDes}</textarea>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-12">
		        <label class="control-label" for="managementOffice">甲方名称</label>
		        <div>
		       		<input type="hidden"  id="deptId" name="deptId" data-parsley-maxlength="20"  value="" />
		       		<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" data-parsley-maxlength="50"  value="${subContract.deptName}" />
		        </div>
		    </div>
		    
		    
		    <%-- <div class="form-group col-md-6">
		        <label class="control-label" for="projCompDirector">甲方委托代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projCompDirector" name="projCompDirector" data-parsley-maxlength="20" value="${subContract.projCompDirector}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projCompPm">甲方项目经理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projCompPm" name="projCompPm" data-parsley-maxlength="20" value="${subContract.projCompPm}"/>
		        </div>
		    </div> --%>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasComLegalRepresent">甲方代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasComLegalRepresent" name="gasComLegalRepresent" data-parsley-maxlength="20"  value="${subContract.gasComLegalRepresent}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="gasComPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasComPhone" name="gasComPhone" data-parsley-maxlength="13" value="${subContract.gasComPhone}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cuLegalRepresent">项目经理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuLegalRepresent" name="cuLegalRepresent" data-parsley-maxlength="20" value="${subContract.cuLegalRepresent}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cuPmPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuPmPhone" name="cuPmPhone" data-parsley-maxlength="13" value="${subContract.cuPmPhone}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="certificationName">资格证名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="certificationName" name="certificationName" data-parsley-maxlength="50" value="${subContract.certificationName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="certificationNo">资格证编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="certificationNo" name="certificationNo" data-parsley-maxlength="50" value="${subContract.certificationNo}"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-12">
		        <label class="control-label" for="cuName">乙方</label>
		        <div>
		            <input type="hidden" id="cuId" name="cuId" data-parsley-maxlength="100"  value="" />
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" data-parsley-maxlength="200"  value="${subContract.cuName}" />
		        </div>
		    </div>
		    
		    <%-- <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuLegalRepresent">乙方法定代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuLegalRepresent" name="cuLegalRepresent" data-parsley-maxlength="20"  value="${subContract.cuLegalRepresent}"/>
		        </div>
		    </div> --%>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuDirector">乙方委托代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuDirector" name="cuDirector" data-parsley-maxlength="20" value="${subContract.cuDirector}"/>
		        </div>
		    </div>
		    <%-- <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuPm">乙方项目经理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuPm" name="cuPm" data-parsley-maxlength="20" value="${subContract.cuPm}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cuPmPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuPmPhone" name="cuPmPhone" data-parsley-maxlength="13" value="${subContract.cuPmPhone}"/>
		        </div>
		    </div> --%>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="cuResponsiblePerson">现场负责人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuResponsiblePerson" name="cuResponsiblePerson" data-parsley-maxlength="20" value="${subContract.cuResponsiblePerson}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cuResponsiblePhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuResponsiblePhone" name="cuResponsiblePhone" data-parsley-maxlength="13" value="${subContract.cuResponsiblePhone}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="scScope">承包范围</label>
	            <div>
	                <textarea class="form-control field-editable" name ="scScope" id="scScope" rows="3" data-parsley-maxlength="200">${subContract.scScope}</textarea>
	            </div>
            </div>
            <div class="form-group col-md-12">
		        <label class="control-label" for="scType">承包方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="scType" name="scType" data-parsley-maxlength="200" data-parsley-required="true" value='${subContract.scType}'/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="qualityStandar">质量标准</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="qualityStandar" name="qualityStandar" data-parsley-maxlength="50" value="${subContract.qualityStandar}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scPlannedTotalDays">计划天数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="scPlannedTotalDays"  name="scPlannedTotalDays" data-parsley-maxlength="10"  value="${subContract.scPlannedTotalDays}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scPlannedStartDate">开工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="scPlannedStartDate"  name="scPlannedStartDate" data-parsley-maxlength="100" value="${subContract.scPlannedStartDate}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scPlannedEndDate">竣工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="scPlannedEndDate"  name="scPlannedEndDate" data-parsley-maxlength="100" value="${subContract.scPlannedEndDate}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
	            <label class="control-label" for="payType">付款方式</label>
            	<div>
               	   <select class="form-control input-sm field-editable" id="payType"  name="payType"  data-parsley-required="true">
						<option value="${payType}" >${payType}</option>
	              </select>
	            </div>
			</div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scAmount">协议价款</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number"  id="scAmount" name="scAmount" data-parsley-maxlength="13" value="${subContract.scAmount}"  data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scSignDate">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="scSignDate"  name="scSignDate" data-parsley-maxlength="100" value="${subContract.scSignDate}" data-parsley-required="true">
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="remark">备注</label>
	            <div>
	                <textarea class="form-control field-editable" name ="remark" id="remark" rows="3" data-parsley-maxlength="200">${subContract.remark}</textarea>
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
    $("#contractDetailform").styleFit();
    
    //参数传false时，表单不可编辑
    $("#contractDetailform").toggleEditState(false);
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->