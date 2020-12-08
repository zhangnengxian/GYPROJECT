<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box" style="margin-bottom:-36px">

	<input type="hidden" id="projId"  value="${projId }">
	<form class="form-horizontal" id="serviceLogForm" action="/" method="POST">
        <div class="form-group col-sm-4">
			<label class="control-label" for="serviceType">接口类型</label>
		    <div >
                <select  class="form-control input-sm"  name="serviceType">
                	<option></option>
                	<c:forEach var = "enums" items="${webServiceTypeEnum }">
                		<option value="${enums.value }">${enums.message }(${enums.value })</option>
                	</c:forEach>
                </select>
            </div>
		</div>
	</form>
</div>
<div class="p-t-6">
    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
	<table id="serviceLogTable" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
           		<th>接口LOGID</th>
           		<th>接口类型</th>
           		<th>返回值编码</th>
           		<th>传递参数</th>
           		<th>返回信息</th>
            	<th>同步时间</th>
            	<th>操作</th>
           	</tr>
       	</thead>
	</table>
</div> 
<!-- 工程明细 start -->
       	<div class="tab-pane fade active in" id="projectScale" >
       	</div>
<script>
    App.restartGlobalFunction();
    $('#serviceLogForm').styleFit();
    $.getScript('projectjs/project/service-list-pop.js?v='+Math.random()).done(function () {
    	serviceLog.init();
	});
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->