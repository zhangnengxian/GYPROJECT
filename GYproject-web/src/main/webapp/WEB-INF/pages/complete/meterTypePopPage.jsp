
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->

<div class="p-t-6 p-b-15">
	<!-- <div id="caiIdDes" class=" pull-left m-b-25 form-group col-lg-6 col-md-12 col-sm-6">
			<label class="control-label" for="pressCompeCoeff">表型号</label>
					        <div>
					        	<input type="text" value="1" class="form-control input-sm field-editable" data-parsley-required="true"  id="pressCompeCoeff"  data-parsley-type="number"  name="pressCompeCoeff" />
					        </div>
	</div> -->
	<div id="caiIdDes" class=" pull-left m-b-10">
			<select id="manuIdpop" class="form-control input-sm field-editable " style="width:200px;" name="manuIdpop" >
				<option value="">厂商</option>
				<option value="">丹热</option>
				<option value="">先锋</option>
			</select>
	</div>
	<table id="mrTypePop" class="table table-striped table-bordered nowrap" width="100%">
        	<thead>
            	<tr>
              		
          			<th>表具型号</th>
	              	<th>厂商</th>
	              	<th>表类型</th>
          		</tr>
			</thead>
		</table>
</div> 



<script>
	App.restartGlobalFunction();
	$("#meterpopform").styleFit();
	$.getScript('projectjs/complete/meter-type.js').done(function () {
		meterType.init();
	}); 
</script>