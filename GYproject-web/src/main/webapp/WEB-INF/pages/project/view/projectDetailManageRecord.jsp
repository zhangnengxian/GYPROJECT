<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="clearboth form-box" style="margin-bottom:-36px">
		<form class="form-horizontal" id="manageRecordSearchForm" action="/" method="POST">
	        <div class="form-group col-lg-5 col-md-5 col-sm-5">
				<label class="control-label" for="">操作类型</label>
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
    <table id="manageRecordTable" class="table table-hover table-bordered nowrap m-t-40" width="100%">
		<thead>
         	<tr>
              <th>操作类型</th>
              <th>单据类型</th>
              <th>审核时间</th>
              <th>审核级别</th>
              <th>审核结果</th>
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
    $("#manageRecordSearchForm").styleFit();
    manageRecordTableInitFunction();
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->