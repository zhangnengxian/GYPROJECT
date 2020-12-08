<!-- accessoryItemRight.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="projectScaleForm" action="projectScale/saveOrUpdateProjectScale">
       		<input type="hidden" id="psId" name="psId">
		    <div class="form-group col-md-6  ">
		        <label class="control-label" for="projTypeDes">规模名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable "  data-parsley-required="true" id="psDes" name="psDes" data-parsley-maxlength="100"/>
		        </div>
		    </div>
       		<div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    		
			     <div>
                 <select class="form-control input-sm field-editable" id="projTypeId"  name="projTypeId">
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${projTypeList}">
							   	<option value="${enums.projTypeId}" >${enums.projTypeDes}</option>
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
   // $("#proType1").html($("#proType").html());
    //参数传false时，表单不可编辑
    $("#projectScaleForm").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('projectScaleForm','','',function(data){
    	$("#projTypeId").val(data.projType.projTypeId);
    });
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
    	$("#projectScaleForm").cformSave('projectScaleTable',"",true);
    	
    }); 
  
    
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	
    	$("#projectScaleForm input").val('');
    	trSData.t && trSData.t.cgetDetail('projectScaleForm'); 
    	$("#projectScaleForm").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	//移除验证
	   	$("#projectScaleForm").parsley().reset();
    });
    
    
    </script>
<!-- ================== END PAGE LEVEL JS ================== -->