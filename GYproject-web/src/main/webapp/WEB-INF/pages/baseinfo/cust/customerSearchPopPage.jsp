<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_customers" action="/" method="POST">
         <div class="form-group col-md-12">
            <label class="control-label" for="">单位编码</label>
            <div >
                <input type="text" class="form-control input-sm"  name="custCode"/>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label" for="">单位名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="custName"/>
            </div>
        </div>
     <!--   <div class="form-group col-md-6">
            <label class="control-label" for="">单位简称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="custShortName"/>
            </div>
        </div> -->
        <div class="form-group col-md-6">
            <label class="control-label" for="">联系人</label>
            <div >
                <input type="text" class="form-control input-sm"  name="custContcat"/>
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_customers").styleFit();
    
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->