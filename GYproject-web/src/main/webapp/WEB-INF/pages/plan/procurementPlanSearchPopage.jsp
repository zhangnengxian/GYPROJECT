<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_sub" action="/" method="POST">
        <div class="form-group col-md-6">
            <label class="control-label" for="projName">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
         <!-- <div class="form-group col-md-6">
            <label class="control-label" for="managementOffice">施工管理处</label>
            <div >
                <input type="text" class="form-control input-sm"  name="managementOffice"/>
            </div>
        </div> -->
        <div class="form-group col-md-6">
            <label class="control-label" for="">施工管理处</label>
            <div>
                <select class="form-control input-sm field-editable" id="managementOffice"  name="managementOffice"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${manageOfficeList}">
						<option value="${enums.id}" >${enums.name}</option>
	                </c:forEach>
				</select>
            </div>
        </div>
         <div class="form-group col-md-6">
            <label class="control-label" for="createStaffName">创建人名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="createStaffName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="status">生成时操作</label>
            <div>
                <select class="form-control input-sm field-editable"   name="status"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${projectOperate}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	             </select>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="isExport">是否导出</label>
            <div>
            	<select class="form-control input-sm " id="isExport" name="isExport">
            		<option value=""></option>
            		<c:forEach items="${isExport}" var="enums">
            			<option value="${enums.value}">${enums.message}</option>
            		</c:forEach>
            	</select>
            </div>
        </div>
        <div class="form-group col-md-6 projType">
            <label class="control-label" for="projType">工程类型</label>
            <div>
            	<select class="form-control input-sm " id="projType" name="projType">
            		<option value=""></option>
            		<c:forEach items="${projType}" var="enums">
            			<option value="${enums.value}">${enums.message}</option>
            		</c:forEach>
            	</select>
            </div>
        </div>
        <div class="form-group col-md-6 hidden isFeedBack" >
            <label class="control-label" for="isFeedBack">是否反馈</label>
            <div>
            	<select class="form-control input-sm " id="isFeedBack" name="isFeedBack">
            		<option value=""></option>
            		<c:forEach items="${isFeedBack}" var="enums">
            			<option value="${enums.value}">${enums.message}</option>
            		</c:forEach>
            	</select>
            </div>
        </div>
<!--         <div class="form-group col-md-12">            -->
<!--             <label class="control-label" for="projStartDate">开工日期</label> -->
<!--             <div class="input-group input-daterange"> -->
<!-- 	            <input type="text" class="form-control datepicker-default input-sm" name="projStartDateStar" > -->
<!-- 	            <span class="input-group-addon">至</span>  -->
<!-- 	            <input type="text" class="form-control datepicker-default input-sm" name="projStartDateEnd" > -->
<!--             </div> -->
<!--         </div> -->
         <div class="form-group col-md-12">           
            <label class="control-label" for="createTime">创建日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="createTimeStar" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="createTimeEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_sub").styleFit();
    
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
    
    $('[name="status"]').on("change",function(){
    	if($(this).val()=="6"){
    		//设计出图
    		$(".isFeedBack").removeClass("hidden");//是否反馈
    		$(".projType").addClass("hidden");//工程类型
    		//$('[name="projType"]').val("");
    		$("#projType option:selected").val("");
    	}else{
    		$(".projType").removeClass("hidden");
    		$(".isFeedBack").addClass("hidden");
    		//$('[name="isFeedBack"]').val("");
    		$("#isFeedBack option:selected").val("");
    	}
    	$("#searchForm_sub").styleFit();
    })
    
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->