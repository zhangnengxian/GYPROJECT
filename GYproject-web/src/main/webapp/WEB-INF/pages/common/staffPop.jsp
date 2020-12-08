<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box" style="margin-bottom:-36px">
	<form class="form-horizontal" id="staffForm" action="/" method="POST">
		<input type="hidden"   name="post" value="${post}"/>
		<input type="hidden"   name="unitType" value="${unitType}"/>
		<input type="hidden"   name="deptId" value="${deptId}"/>
		<input type="hidden"   name="corpId" value="${corpId}"/>
		<div class="form-group col-lg-5 col-md-5 col-sm-5">
            <label class="control-label" for="staffNo">编号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="staffNo"/>
            </div>
        </div>
        <div class="form-group col-lg-5 col-md-5 col-sm-5">
			<label class="control-label" for="staffName">姓名</label>
		    <div >
                <input type="text" class="form-control input-sm"  name="staffName"/>
            </div>
		</div>
	</form>
</div>
<div class="p-t-6 p-b-15">
	<table id="staffTable" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
     			<th></th>
            	<th>编号</th>
           		<th>姓名</th>
            	<th>职务</th>
           	</tr>
       	</thead>
	</table>
</div> 
<script>
    App.restartGlobalFunction();
    $('#staffForm').styleFit();
    $.getScript('projectjs/common/staff-pop.js?v='+Math.random()).done(function () {
		staff.init();
	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->