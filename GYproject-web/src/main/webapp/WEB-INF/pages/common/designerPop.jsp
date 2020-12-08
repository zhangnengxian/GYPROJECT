<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="p-t-6">
    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
	<table id="designerTable" class="table table-hover table-bordered nowrap" width="100%">
        <thead>
            <tr>
            	<th></th>
            	<th>名称</th>
                <th>待设计任务</th>
                <!-- <th></th> -->
            </tr>
        </thead>
	</table>
</div> 
<script>
    App.restartGlobalFunction();
    $('#customerForm').styleFit();
    $.getScript('projectjs/common/designer-pop.js').done(function () {
    	designer.init();
	});
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->