<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_visa" action="/" method="POST">

        <div class="form-group col-sm-6">
            <label class="control-label" for="">工程编号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projNo"/>
            </div>
        </div>
        <div class="form-group col-sm-6">
            <label class="control-label" for="">签证编号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="evNo"/>
            </div>
        </div>
         <div class="form-group col-sm-6">
      			<label class="control-label" for="">签证状态</label>	
      			 <div >
					<select class="form-control input-sm " name="evState">
					 <option value=""></option>
				        <c:forEach var="enums" items="${cmState}">
				            <option value="${enums.value}">${enums.message}</option>
				        </c:forEach>
				       
				    </select>
				 </div>
		      
			</div>
         <div class="form-group col-sm-6">
      			<label class="control-label" for="">审核状态</label>	
      			 <div >
					<select class="form-control input-sm " name="auditStatus">
					 <option value=""></option>
				        <c:forEach var="enums" items="${auditState}">
				            <option value="${enums.value}">${enums.message}</option>
				        </c:forEach>
				       
				    </select>
				 </div>
		      
			</div>
        <div class="form-group col-sm-12">
            <label class="control-label" for="">签证事由</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="evContent"/>
            </div>
        </div>
        
        
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="">签证日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="visaDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="visaDateEnd" >
            </div>
        </div>
       
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_visa").styleFit();

 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->
