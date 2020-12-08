<!-- constructionUnitSearchPopPage.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_constructionUnit" action="/" method="POST">
        <div class="form-group col-md-12">
            <label class="control-label" for="cuName">单位名称</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="cuName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="shortName">简称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="shortName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="cuDirector">负责人</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="cuDirector"/>
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="">成立日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="cuFoundDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="cuFoundDateEnd" >
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="cuQualification">资质</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control input-sm"  name="cuQualification"/>
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_constructionUnit").styleFit();
    
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
 	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->