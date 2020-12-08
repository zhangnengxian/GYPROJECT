


<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_change" action="/" method="POST">

        <div class="form-group col-lg-6 col-md-6 col-sm-6">
            <label class="control-label" for="">工程编号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projNo"/>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-6 col-sm-6">
            <label class="control-label" for="">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-6 col-sm-6">
            <label class="control-label" for="">工程地点</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-6  col-sm-6">
     		<label class="control-label" for="">变更状态</label>
     			<div>	
				<select class="form-control input-sm " id="designChangeType" name="designChangeType">
                	<option value="3">待预算调整</option>
                	<option value="4">待签补充协议</option>
                	<option value="5">已签补充协议</option>
                	<option value="6">已完成</option>
			    </select>
			</div>
		</div>
        <div class="form-group col-lg-12 col-md-6 col-sm-6">
            <label class="control-label" for="">变更原因</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="cuReason"/>
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="">变更日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="cmDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="cmDateEnd" >
            </div>
        </div>
       
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_change").styleFit();

 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
 	$('#cmState option[value="-1"]').addClass("hidden");
</script>
<!-- ================== END PAGE LEVEL JS ================== -->