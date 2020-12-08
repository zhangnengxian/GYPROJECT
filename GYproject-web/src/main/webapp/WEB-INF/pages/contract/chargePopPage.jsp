<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<div>
	<div class="clearboth form-box p-b-10">
		<form class="form-horizontal" id="chargeForm" action="">
			<div class="form-group col-md-12">
				<label class="control-label" for="">工程名称</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="projName" name="projName"  />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">工程编号</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="projNo" name="projNo"  />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">工程地点</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="projAddr" name="projAddr"  />
				</div>
			</div>
			<div class="form-group  col-md-6 ">
				<label class="control-label" for="">收款类型</label>
				<div>
					<label>
						<input type="radio" name="charge" value="1" checked/>首款
					</label>
					<label>
						<input type="radio" name="charge" value="2"/>尾款
					</label>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">金额</label>
				<div>
					<input type="text" class="form-control input-sm" value="300" id="" name=""  />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">操作人</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="黄翠芳" id="" name=""  />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">操作时间</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="chargeTime" name="chargeTime"  />
				</div>
			</div>
		</form>
	</div>
	<ul class="nav nav-tabs p-t-5 p-l-5">
		<li class="active"><a href="#tab-1" data-toggle="tab">应付流水</a></li>
		<li class=""><a href="#tab-2" data-toggle="tab">收费流水</a></li>
	</ul>
	<div class="tab-content p-l-0 p-r-0 p-t-5">
		<div class="tab-pane fade active in btn-top" id="tab-1" >
       		<table class="table table-hover table-striped table-bordered nowrap" width="100%" id="chargeTable" >
       			<thead>
       			<tr>
       				<th>操作时间</th>
        			<th>收款类型</th>
        			<th>金额</th>
        			<th>操作人</th>
        			<th>收款部门</th>
       			</tr>
       			</thead>
       		</table>
		</div>
		<div class="tab-pane fade  btn-top" id="tab-2" >
       		<table class="table table-hover table-striped table-bordered nowrap" width="100%" id="chargeTable1" >
       			<thead>
       			<tr>
       				<th>操作时间</th>
        			<th>收款类型</th>
        			<th>金额</th>
        			<th>操作人</th>
        			<th>收款部门</th>
       			</tr>
       			</thead>
       		</table>
		</div>
	</div>		
</div>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
$("#chargeForm").toggleEditState().styleFit();

//初始化收费信息
var chargedatainit= function() {
	"use strict";
    if ($('#chargeTable').length !== 0) {
  		$('#chargeTable').on( 'init.dt',function(){
	 		//默认选中第一行
			//$(this).bindDTSelected(trSelectedBack,true);
	  		$('#chargeTable').hideMask();
	    }).DataTable({
	      	language: language_CN,
	        lengthMenu: [18],
	        dom: 'Brt',
	        buttons: [],
	        ajax: 'projectjs/contract/json/chargeData.json',
	        responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
	        columns: [
		  		{"data":"date"},
		  		{"data":"type"},
		  		{"data":"many"},
		  		{"data":"maker"},
		  		{"data":"department"}
			]
	    });
	}
};
chargedatainit();
</script>