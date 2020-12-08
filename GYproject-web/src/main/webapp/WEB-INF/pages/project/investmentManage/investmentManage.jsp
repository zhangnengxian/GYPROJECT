<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<style>
.nvestmentManageBox{
}
/* .status{
float:right;
} */

.statusLabel{
	width:102px;
}
</style>
<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
		 <!-- <div class="panel">
		 	<div id="allmap" style="width: 100%;height: 100%;margin:0px;padding:0px"></div> 
		 </div> -->
        <div class="col-md-12 col-sm-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">年度投资项目列表</h4>
                </div>
                <div class="panel-body">
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
			      	<div class="nvestmentManageBox">
			      	<form class="form-horizontal " id="investmentManageForm" data-parsley-validate="true" action="">
			      		<!-- <div class="form-group col-md-2 "style="visibility:hidden"; >
					        <label class="control-label" for="surveyDate">年度</label>
					        <div>
					           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="surveyDate"  name="surveyDate"  value="2017" >
					        </div>
					    </div> -->
			      		<div class="form-group col-md-2">
					        <label class="control-label" for="surveyDate">年度</label>
					        <div>
					           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="surveyDate"  name="surveyDate"  value="2017" >
					        </div>
					    </div>
			      		<div class="form-group col-md-3 backReason">
					        <label class="control-label" for="">公司</label>
					    	<div>
					    		<select class="form-control input-sm field-editable a2" id="backReason"  name="backReason"  >
					 		      	<option>全部</option>
					 		      	<option value="0" selected>贵州燃气集团股份有限公司</option>
					 		      	<option value="1">贵州燃气集团安顺市燃气有限责任公司</option>
					 		      	<option value="2" >贵州燃气集团毕节市燃气有限责任公司</option>
					 		      	<option value="3" >贵州燃气（集团）修文县燃气有限公司</option>
					             </select>
					        </div>
					    </div>
					    <div class="form-group col-md-2 backReason">
					        <label class="control-label" for="">类型</label>
					    	<div>
					    		<select class="form-control input-sm field-editable a2" id="backReason"  name="backReason"  >
					 		      	<option value="0">干管</option>
					 		      	<option value="1" selected>改管</option>
					             </select>
					        </div>
					    </div>
					    <!-- <div class="form-group col-md-6  status" >
			      			<label class="control-label" for=""></label>
							<label class="statusLabel">
								<input class="field-editable statusLabel" type="radio" name="depositGet" value="1" checked/> 待审批
			           		</label>
			           		<label class="statusLabel">
			           			<input class="field-editable statusLabel" type="radio"  name="depositGet" value="0" /> 已通过
			           		</label>
						</div> -->
						
						 	<!-- <a href="javascript:;" class="btn btn-query btn-sm  saveButton entBtn" >查询</a> -->
			      	</form>
			      	<div class="toolBtn f-r p-b-10  editbtn">
						    <a href="javascript:;" class="btn btn-query btn-sm m-l-5 saveButton entBtn" >查询</a>
						</div>
			      	</div>
			      	<table class="table table-hover table-bordered nowrap m-t-20" width="100%" style="margin:auto;">
			      		<tr>
							<td class="text-center" width="100px">总计</td>
				            <td class="text-left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;16  &nbsp;&nbsp;项目    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投资总额  &nbsp;&nbsp;3624 万 </td>
						</tr>
			      	</table>
			        <table id="projectGlobalViewListTable" data-attach-table="all" class="table table-striped table-bordered nowrap " width="100%">
	            		<thead>
	                		<tr>
	                		    <th>序号</th>
	                		    <th>项目名称</th>
	                		    <th>管径</th>
				                <th>项目长度(千米)</th>
				                <th>完成长度(千米)</th>
				                <th>完成进度</th>
				                <th>项目类别</th>
				                <th>预算价(万元)</th>
				                <th>已投资(万元)</th>
				                <th>今年计划投资(万元)</th>
				                <th>建设比例</th>
				                <th>燃气公司</th>
	                		</tr>
		               	</thead>
	    	  	  	</table>
                </div>
             </div>
        </div>
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('投资管理 - 工程项目管理系统 ');
   	$("#investmentManageForm").styleFit();
    $.getScript('projectjs/project/investment-manage.js').done(function () {
    	investmentManage.init();
	});
	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->