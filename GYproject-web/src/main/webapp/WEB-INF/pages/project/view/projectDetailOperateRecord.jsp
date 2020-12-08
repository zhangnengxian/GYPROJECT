<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="clearboth form-box" style="margin-bottom:-36px">
		<form class="form-horizontal" id="operateRecordSearchForm" action="/" method="POST">
	        <div class="form-group col-lg-5 col-md-5 col-sm-5">
				<label class="control-label" for="manuId">操作类型</label>
		         <div>
		         	<select class="form-control input-sm "  name="stepId"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${step}">
							<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		            </select>
		         </div>
			</div>
		</form>
	</div>
    <table id="operateRecordTable" class="table table-hover table-bordered nowrap m-t-40" width="100%">
		<thead>
         	<tr>
              <th>操作类型</th>
              <th>操作时间</th>
              <th>操作人</th>
              <th>操作部门</th>
         	</tr>
    	</thead>
	</table>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    $(".infodetails").hideMask();
    $("#operateRecordSearchForm").styleFit();
    operateRecordTableInitFunction();
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->