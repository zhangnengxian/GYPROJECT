<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box" style="margin-bottom:-36px">
	<form class="form-horizontal" id="customerForm" action="/" method="POST">
		<div class="form-group col-sm-4">
            <label class="control-label" for="">客户名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="custName"/>
            </div>
        </div>
        <div class="form-group col-sm-4">
			<label class="control-label" for="custContcat">联系人</label>
		    <div >
                <input type="text" class="form-control input-sm"  name="custContcat"/>
            </div>
		</div>
	</form>
</div>
<div class="p-t-6">
    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
	<table id="customerTable" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
           		<th>单位ID</th>
           		<th>单位名称</th>
           		<th>开户行</th>
            	<th>联系人</th>
            	<th>联系电话</th>
           	</tr>
       	</thead>
	</table>
</div> 
<script>
    App.restartGlobalFunction();
    $('#customerForm').styleFit();
    $.getScript('projectjs/common/customer-pop.js?v=1000').done(function () {
		
	});
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->