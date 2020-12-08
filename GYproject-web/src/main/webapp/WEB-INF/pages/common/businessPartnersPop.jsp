<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box" style="margin-bottom:-36px">
	<form class="form-horizontal" id="businessPartnersForm" action="/" method="POST">
		<input type="hidden" name="unitType" value="${unitType }"/>
		<div class="form-group col-lg-5 col-md-5 col-sm-5">
            <label class="control-label" for="">资质</label>
            <div >
                <input type="text" class="form-control input-sm"  name="unitQualification"/>
            </div>
        </div>
        <div class="form-group col-lg-5 col-md-5 col-sm-5">
			<label class="control-label" for="manuId">单位名称</label>
		    <div >
                <input type="text" class="form-control input-sm"  name="unitName"/>
            </div>
		</div>
	</form>
</div>
<div class="p-t-6 p-b-15">
	<table id="businessPartnersTable" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
           		<th>单位名称</th>
            	<th>简称</th>
            	<th>资质</th>
            	<th>负责人</th>
           	</tr>
       	</thead>
	</table>
</div> 
<script>
    App.restartGlobalFunction();
    $('#constructionUnitForm').styleFit();
    $.getScript('projectjs/common/business-partners-pop.js').done(function () {
		businessPartners.init();
	});
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->