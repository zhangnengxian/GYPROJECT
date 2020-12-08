<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_auditLevel" action="/" method="POST">
        <div class="form-group col-sm-6 col-xs-12">
	            <label class="control-label" for="">工程类型</label>
	             <div>
		    		<select class="form-control input-sm field-editable" id="projectType"  name="projectType" >
		 		      	<option value=""></option>
		 		      	<c:forEach var="enums" items="${projType}">
							   	<option value="${enums.projTypeId}">${enums.projConstructDes}</option>
		                </c:forEach>
		             </select>
		        </div>
	    </div>
	     <div class="form-group col-md-6">
            <label class="control-label" for="contributionMode">出资方式</label>
            <div>
            	<select class="form-control input-sm " id="contributionMode" name="contributionMode">
            		<option value=""></option>
            		<c:forEach items="${contributionMode}" var="enums">
            			<option value="${enums.id}">${enums.contributionDes}</option>
            		</c:forEach>
            	</select>
            </div>
        </div> 
        <div class="form-group col-md-6">
            <label class="control-label" for="stepId">操作步骤</label>
            <div>
            	<select class="form-control input-sm " id="stepId" name="stepId">
            		<option value=""></option>
            		<c:forEach items="${stepId}" var="enums">
            			<option value="${enums.value}">${enums.message}</option>
            		</c:forEach>
            	</select>
            </div>
        </div> 
         <div class="form-group col-md-6">
            <label class="control-label" for="operater">操作人</label>
            <div>
            	<input type = "text" class = "form-control input-sm" id = "operater" name = "operater"/>
            </div>
        </div>      
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_auditLevel").styleFit();
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->