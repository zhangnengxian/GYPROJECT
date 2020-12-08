<!-- accessoryItemRight.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="projectTypeForm" action="projectType/saveOrUpdateProjectType">
       		<input type="hidden" id="projTypeId" name="projTypeId">
       		<input type="hidden" id="scaleTypeDes" name="scaleTypeDes">
       		<input type="hidden" id="contractTypeDes" name="contractTypeDes">
		    <div class="form-group col-md-6  ">
		        <label class="control-label" for="projTypeDes">工程类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable "  data-parsley-required="true" id="projTypeDes" name="projTypeDes" data-parsley-maxlength="100"/>
		        </div>
		    </div>
       		<div class="form-group col-md-6 ">
		        <label class="control-label" for="projConstructDes">建设类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable "  data-parsley-required="true" id="projTypeDes" name="projConstructDes" data-parsley-maxlength="100"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="contractType">合同类型</label>
		    	<div>
		    		 <select class="form-control input-sm field-editable" id="contractType"  name="contractType"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${contractType}">
							   	<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		                
		             </select>		        
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
    //参数传false时，表单不可编辑
    $("#projectTypeForm").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('projectTypeForm','','');
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
    	$("#scaleTypeDes").val($("#scaleType option:selected").text());
    	$("#contractTypeDes").val($("#contractType option:selected").text());
    	$("#projectTypeForm").cformSave('projectTypeTable',"",true);
    	
    }); 
  
    
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	
    	$("#projectTypeForm input").val('');
    	trSData.t && trSData.t.cgetDetail('projectTypeForm'); 
    	$("#projectTypeForm").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	//移除验证
	   	$("#projectTypeForm").parsley().reset();
    });
    
    
    </script>
<!-- ================== END PAGE LEVEL JS ================== -->