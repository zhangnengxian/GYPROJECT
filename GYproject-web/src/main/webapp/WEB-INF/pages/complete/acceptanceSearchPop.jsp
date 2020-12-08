<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchAcceptancePop" action="/" method="POST">
        <div class="form-group col-md-12">
            <label class="control-label">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" >工程地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
        <%-- <div class="form-group col-md-6">
            <label class="control-label" >是否验收</label>
            <div>
            	<select class="form-control input-sm " id="acceptanceState" name="acceptanceState">
            		<option value=""></option>
            		<c:forEach items="${acceptanceAtate}" var="enums">
            			<option value="${enums.value}">${enums.message}</option>
            		</c:forEach>
            	</select>
            </div>
        </div> --%>
         <div class="form-group col-md-6">
            <label class="control-label" for="acceptanceState">是否完成验收</label>
            <div>
            	<select class="form-control input-sm " id="acceptanceState" name="acceptanceState">
            		<option value="1">已完成</option>
            		<option value="0">未完成</option>
            		<%-- <c:forEach items="${isPrint}" var="enums">
            			<option value="${enums.value}">${enums.message}</option>
            		</c:forEach> --%>
            	</select>
            </div>
        </div> 
        <%--<div class="form-group col-md-6">--%>
            <%--<label class="control-label">合同编号</label>--%>
            <%--<div>--%>
            	<%--<input type="text" class="form-control input-sm"  name="conNo"/>--%>
            <%--</div>--%>
        <%--</div>--%>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchAcceptancePop").styleFit();
    
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->