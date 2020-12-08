<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_businessPartners" action="/" method="POST">
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">单位类型</label>
            <div>
                <select class="form-control input-sm field-editable" id="unitType"  name="unitType"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${unitType}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label" for="">单位名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="unitName"/>
            </div>
        </div>
       <div class="form-group col-md-6">
            <label class="control-label" for="">简称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="shortName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="">负责人</label>
            <div >
                <input type="text" class="form-control input-sm"  name="unitDirector"/>
            </div>
        </div>
<!--         <div class="form-group col-md-6"> -->
<!--             <label class="control-label" for="">电话</label> -->
<!--             <div > -->
<!--                 <input type="text" class="form-control input-sm"  name="unitPhone"/> -->
<!--             </div> -->
<!--         </div> -->
        
<!--         <div class="form-group col-md-6"> -->
<!--             <label class="control-label" for="">手机</label> -->
<!--             <div > -->
<!--                 <input type="text" class="form-control input-sm"  name="unitMobile"/> -->
<!--             </div> -->
<!--         </div> -->
        
        <div class="form-group  col-sm-12">           
            <label class="control-label" for="">成立日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="unitFoundDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="unitFoundDateEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_businessPartners").styleFit();
    
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->