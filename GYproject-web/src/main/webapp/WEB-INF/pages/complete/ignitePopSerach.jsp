<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="igniteSearchForm" action="/" method="POST">
        <input type="hidden" class="form-control input-sm"  name="sideBarID"  value="110808"/>
        <div class="form-group col-md-12">
            <label class="control-label" for="projName">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        
        <div class="form-group col-md-12">
            <label class="control-label" for="projAddr">工程地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="">签订状态</label>
            <div>
                <select class="form-control input-sm field-editable projStatus" id="isSignIgnite"  name="isSignIgnite"  >
					<option value="" ></option>
					<option value="1" >未完成</option>
					<option value="0" >已完成</option>
	             </select>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="">上传竣工单</label>
            <div>
                <select class="form-control input-sm field-editable projStatus" id="isUploadAccessory"  name="isUploadAccessory"  >
                    <option value="" ></option>
                    <option value="1" >已上传</option>
                    <option value="0" >未上传</option>
                </select>
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#igniteSearchForm").styleFit();
    
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->