<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box" style="margin-bottom:-36px">
	<form class="form-horizontal" id="constructionUnitForm" action="/" method="POST">
		<div class="form-group col-lg-5 col-md-5 col-sm-5">
            <label class="control-label" for="">资质</label>
            <div >
                <input type="text" class="form-control input-sm"  name="cuQualification"/>
            </div>
        </div>
        <div class="form-group col-lg-5 col-md-5 col-sm-5">
			<label class="control-label" for="manuId">单位名称</label>
		    <div >
                <input type="text" class="form-control input-sm"  name="cuName"/>
            </div>
		</div>
	</form>
</div>
<div class="p-t-6 p-b-15">
	<table id="constructionUnitTable" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
           		<th>分包单位名称</th>
            	<th>资质</th>
            	<th>总人数</th>
           	</tr>
       	</thead>
	</table>
</div> 
<script>
    App.restartGlobalFunction();
    $('#constructionUnitForm').styleFit();
    $.getScript('projectjs/common/construction-unit-pop.js').done(function () {
		constructionUnit.init();
	});
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->