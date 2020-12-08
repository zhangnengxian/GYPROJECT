<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="content" class="content">
	<div class="row">
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">提资申请列表</h4>
				</div>
				<div class="panel-body">
					<input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
                    <input type="hidden" id="stepId" name="stepId" value="${stepId }"/>
					<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
					<table id="raiseMoneyProjectTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
					<thead>
						<tr>
							<th>工程ID</th>
							<th>工程编号</th>
							<th>工程名称</th>
							<th>状态</th>
							<th>工程类型</th>
							<th></th>
						</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>

		<div class="col-sm-6 col-xs-12" >
			<div class="panel panel-inverse" >
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">申请区</h4>
				</div>
				<div class="panel-body saveHiddenBox" style="height: 700px;">
					<div class="toolBtn f-r p-b-10  editbtn hidden">
						<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 acceptSaveBtn" >保存</a>
						<a href="javascript:;" class="btn btn-info btn-sm m-l-5 pushButton entBtn" >推送</a>
						<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
					</div>
					<div class="clearboth form-box">
						<form class="form-horizontal" id="raiseMoneyApplayDetailForm" action="">
							<input type="hidden" name="projId" placeholder="工程ID"/>
							<div class="form-group col-md-6 col-sm-12 ">
								<label class="control-label" for="projNo">工程编号</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
								</div>
							</div>
							<div class="form-group col-md-12  ">
								<label class="control-label" for="projName">工程名称</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value="" />
								</div>
							</div>
							<div class="form-group col-md-12  ">
								<label class="control-label" for="projAddr">工程地点</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value="" />
								</div>
							</div>
							<div class="form-group col-md-12">
								<label class="control-label" for="projScaleDes">工程规模</label>
								<div>
									<textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3"></textarea>
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="corpName">燃气公司</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" />
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" >工程类型</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" >出资方式</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" >业务部门</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value=""/>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="control-label" for="projSourceDes">受理来源</label>
								<div>
									<input type="text" class="form-control field-not-editable"  id="projSourceDes" name="projSourceDes" rows="2" data-parsley-maxlength="200"/>
								</div>
							</div>
							<!--  客户信息 -->
							<div class="form-group col-md-12 noUser">
								<label class="control-label" for="custName">客户名称</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""/>
								</div>
							</div>
							<div class="form-group col-md-6 noUser">
								<label class="control-label" for="custContact">联系人</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="20" value=""/>
								</div>
							</div>
							<div class="form-group col-md-6 noUser">
								<label class="control-label" for="custPhone">联系电话</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" value=""/>
								</div>
							</div>
							<div class="form-group col-md-12">
								<label class="control-label" for="projectRemark">受理备注</label>
								<div>
									<textarea class="form-control field-not-editable" name="projectRemark" id="projectRemark" rows="2" cols="" data-parsley-maxlength="200"></textarea>
								</div>
							</div>
							<!-- <div class="form-group col-md-6 ">
								<label class="control-label">上传资料</label>
								<div>
									<label>
										<input type="radio" class="field-editable" name="uploadFlag" value="1">已上传
									</label>
									<label>
										<input type="radio" class="field-editable" name="uploadFlag" value="0" checked>未上传
									</label>
								</div>
							</div> -->
							<div class="form-group col-md-12">
								<label class="control-label" for="remark">提资备注</label>
								<div>
									<textarea class="form-control field-editable" name="remark" id="remark" rows="2" cols="" data-parsley-maxlength="200"></textarea>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
    App.restartGlobalFunction();
    App.setPageTitle('提资申请 - 工程项目管理系统 ');
    $("#raiseMoneyApplayDetailForm").toggleEditState(false).styleFit();

    $.getScript('projectjs/accept/raiseMoney-application.js?'+Math.random()).done(function () {
        raiseMoneyProject.init();
    });

</script>
