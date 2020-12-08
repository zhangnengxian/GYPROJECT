<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel-heading">
	<div class="panel-heading-btn">
		<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
		<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
		<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	</div>
	<!--  <h4 class="panel-title">验收信息</h4> -->
	<ul class="nav nav-tabs ">
		<li class="active"><a href="#jointAcceptance_List" data-toggle="tab"id ="jointAcceptanceList">联合验收列表</a></li>
		<li class=""><a href="#jointAcceptance_info" data-toggle="tab" id="jointAcceptanceInfo">签字区</a></li>
		<li class=""><a href="#divisionalAcceptance_list" data-toggle="tab" id="divisionalAcceptanceList">分部验收</a></li>
		<li class=""><a href="#divisionalAcceptance_info" data-toggle="tab" id="divisionalAcceptanceInfo">分部验收信息</a></li>
	</ul>
</div>
<div class="panel-body" id="box">
	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
	<div class="tab-content">
		<div class="tab-pane fade active in btn-top active" id="jointAcceptance_List" >
			<input type="hidden" id="projId1" value="${projId}">
			<table class="table table-hover table-striped table-bordered nowrap data-list-table" id="jointAcceptanceListTable" width="100%">
				<thead>
				<tr>
					<th>验收ID</th>
					<th>验收日期</th>
					<th>验收结论</th>
				</tr>
				</thead>
			</table>
		</div>
		<div class="tab-pane fade in btn-top infodetails" id="jointAcceptance_info">
			<div class="toolBtn f-r p-b-10 editbtn">
				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton saveBtn">保存</a>
				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
				<a href="javascript:;" class="btn  btn-primary btn-sm m-l-5 hidden get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>
			</div>
			<div class="clearboth form-box">
				<input type="hidden" id="flag" value="">
				<input type="hidden" id="post" value="${post}">
				<input type="hidden" id="rectifyNoticeType"  name="rectifyNoticeType" value="${rectifyNoticeType}">
				<form class="form-horizontal" id="jointAcceptanceform" action="" data-parsley-validate="true">
					<input type="hidden" name="projId" id="projId"/>
					<input type="hidden" name="jaId" id="jaId" class="busOrderId"/>
					<input type="hidden" name="alarmProj" id="alarmProj"/>
					<input type="hidden" name="version"/>
					<div class="form-group col-md-6 ">
						<label class="control-label" for="projNo">工程编号</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" value=""/>
						</div>
					</div>
					<div class="form-group col-md-6 ">
						<label class="control-label" for="scNo">分合同号</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo" value=""/>
						</div>
					</div>

					<div class="form-group col-md-6 ">
						<label class="control-label" for="projName">工程名称</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value=""/>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label" for="projAddr">工程地点</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label" for="projectTypeDes">工程类型</label>
						<div>
							<input type="text" class="form-control field-not-editable"  id="projectTypeDes" name="projectTypeDes"  value=""/>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label" for="cuName">施工单位</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" value=""/>
						</div>
					</div>
					<%--.联合验收时显示监理、设计代表、施工员、项目经理、现场代表等人员，方便联系，同时显示施工合同的开竣工日期--%>
					<div class="form-group col-md-6">
						<label class="control-label" for="builder">现场代表</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="builder" name="builder" value=""/>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label" for="suJgj">现场监理</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="suJgj" name="suJgj" value=""/>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label" for="cuPm">项目经理</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="cuPm" name="cuPm" value=""/>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label" for="managementQae">施工员</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="managementQae" name="managementQae" value=""/>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label" for="designer">设计员</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="designer" name="designer" value=""/>
						</div>
					</div>
					<!-- <div class="form-group col-md-6">
						<label class="control-label">开工日期</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable datepicker-default"  id="startDate" name="startDate" />
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label">竣工日期</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable datepicker-default"  id="completedDate" name="completedDate" />
						</div>
					</div> -->
					<div class="form-group col-md-6 clearboth">
					<label class="control-label">计划开工日期</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable datepicker-default"  id="scPlannedStartDate" name="scPlannedStartDate" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">实际开工日期</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable datepicker-default"  id="startDate" name="startDate" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">计划竣工日期</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable datepicker-default"  id="scPlannedEndDate" name="scPlannedEndDate" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">实际竣工日期</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable datepicker-default"  id="completedDate" name="completedDate" />
					</div>
				</div>
					
					<div class="form-group col-md-6">
						<label class="control-label signature-tool">验收日期</label>
						<div>
							<input type="text" class="form-control noIdea input-sm field-editable datepicker-default allText technician"  id="jaDate" name="jaDate" data-parsley-required="true"/>
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label signature-tool" for="jaClum">验收结论</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea allText technician"  id="jaClum" name="jaClum" data-parsley-maxlength="200"/>
						</div>
					</div>

					<div class="form-group col-md-12">
						<label class="control-label">客户服务中心</label>
						<div>
							<label><input class="field-editable allText inspector" type="radio" name="custCenterDevice" value="1"  checked> 合格</label>
							<label><input class="field-editable allText inspector" type="radio" name="custCenterDevice" value="0" > 整改</label>
							<label><input class="field-editable allText inspector" type="radio" name="custCenterDevice" value="2" > 整改合格</label>
						</div>
					</div>
					<div class="form-group col-md-12 hidden custCenterDevice">
						<label class="control-label">整改意见</label>
						<div>
							<input type="text" class="form-control field-editable noIdea allText inspector" name ="custCenterDeviceOpinion" id="custCenterDeviceOpinion" data-parsley-maxlength="100"/>
						</div>
					</div>
					<div class="form-group col-md-6 hidden custCenterDevice">
						<label class="control-label signature-tool">整改日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText inspector signDate"  id="custCenterDeviceDate" name="custCenterDeviceDate" />
						</div>
					</div>
					<div class="form-group col-md-6 allSign inspector">
						<label class="control-label signature-tool" for="custCenterSign">验收人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_1"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="custCenterSign" name="custCenterSign" value="">
							<input type="hidden" class="signPost" id="custCenterSign_postType" name="custCenterSign_postType" value="${custCenterSign }" >
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6 ">
						<label class="control-label signature-tool ">签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText inspector signDate"  id="custCenterSignDate" name="custCenterSignDate" />
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label"> 输配分公司</label>
						<div>
							<label><input class="field-editable allText processTechnician" type="radio" name="transCompanyDevice" value="1"  checked> 合格</label>
							<label><input class="field-editable allText processTechnician" type="radio" name="transCompanyDevice" value="0" > 整改</label>
							<label><input class="field-editable allText processTechnician" type="radio" name="transCompanyDevice" value="2" > 整改合格</label>
						</div>
					</div>
					<div class="form-group col-md-12 hidden transCompanyDevice">
						<label class="control-label">整改意见</label>
						<div>
							<input type="text" class="form-control field-editable noIdea allText processTechnician" name ="transCompanyDeviceOpinion" id="transCompanyDeviceOpinion" data-parsley-maxlength="100"/>
						</div>
					</div>
					<div class="form-group col-md-6 hidden transCompanyDevice">
						<label class="control-label signature-tool">整改日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText processTechnician signDate" id="transCompanyDeviceDate" name="transCompanyDeviceDate" />
						</div>
					</div>
					<div class="form-group col-md-6 allSign processTechnician">
						<label class="control-label signature-tool" for="transCompanySign">验收人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_2"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="transCompanySign" name="transCompanySign" value="">
							<input type="hidden" class="signPost" id="transCompanySign_postType" name="transCompanySign_postType" value="${transCompanySign }" >
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool">签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText processTechnician signDate"  id="transCompanySignDate" name="transCompanySignDate" />
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label">计量所</label>
						<div>
							<label><input class="field-editable allText productionStatisticians" type="radio" name="measurementDevice" value="1"  checked> 合格</label>
							<label><input class="field-editable allText productionStatisticians" type="radio" name="measurementDevice" value="0" > 整改</label>
							<label><input class="field-editable allText productionStatisticians" type="radio" name="measurementDevice" value="2" > 整改合格</label>
						</div>
					</div>
					<div class="form-group col-md-12 hidden measurementDevice">
						<label class="control-label">整改意见</label>
						<div>
							<input type="text" class="form-control field-editable noIdea allText productionStatisticians" name ="measurementDeviceOpinion" id="measurementDeviceOpinion" data-parsley-maxlength="100"/>
						</div>
					</div>
					<div class="form-group col-md-6 hidden measurementDevice">
						<label class="control-label">整改日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText productionStatisticians signDate" id="measurementDeviceDate" name="measurementDeviceDate"/>
						</div>
					</div>
					<div class="form-group col-md-6 allSign productionStatisticians">
						<label class="control-label signature-tool" for="measurementSign">验收人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_3"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="measurementSign" name="measurementSign" value="">
							<input type="hidden" class="signPost" id="measurementSign_postType" name="measurementSign_postType" value="${measurementSign }" >
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool">签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText productionStatisticians signDate"  id="measurementSignDate" name="measurementSignDate" />
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label">设计公司</label>
						<div>
							<label><input class="field-editable allText designer" type="radio" name="pdUnitDevice" value="1"  checked> 合格</label>
							<label><input class="field-editable allText designer" type="radio" name="pdUnitDevice" value="0" > 整改</label>
							<label><input class="field-editable allText designer" type="radio" name="pdUnitDevice" value="2" > 整改合格</label>
						</div>
					</div>
					<div class="form-group col-md-12 hidden pdUnitDevice">
						<label class="control-label">整改意见</label>
						<div>
							<input type="text" class="form-control field-editable noIdea allText designer" name ="pdUnitDeviceOpinion" id="pdUnitDeviceOpinion" data-parsley-maxlength="100"/>
						</div>
					</div>
					<div class="form-group col-md-6 hidden pdUnitDevice">
						<label class="control-label">整改日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText designer signDate"  id="pdUnitDeviceDate" name="pdUnitDeviceDate" />
						</div>
					</div>
					<div class="form-group col-md-6 allSign designer">
						<label class="control-label signature-tool" for="pdUnitSign">验收人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_4"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="pdUnitSign" name="pdUnitSign" value="">
							<input type="hidden" class="signPost" id="pdUnitSign_postType" name="pdUnitSign_postType" value="${duDeputy }" >
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool">签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText designer signDate"  id="pdUnitSignDate" name="pdUnitSignDate" />
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label">工程建设管理部</label>
						<div>
							<label><input class="field-editable allText builder" type="radio" name="fieldPrincipalDevice" value="1"  checked> 合格</label>
							<label><input class="field-editable allText builder" type="radio" name="fieldPrincipalDevice" value="0" > 整改</label>
							<label><input class="field-editable allText builder" type="radio" name="fieldPrincipalDevice" value="2" > 整改合格</label>
						</div>
					</div>
					<div class="form-group col-md-12 hidden fieldPrincipalDevice">
						<label class="control-label">整改意见</label>
						<div>
							<input type="text" class="form-control field-editable noIdea allText builder" name ="fieldPrincipalDeviceOpinion" id="fieldPrincipalDeviceOpinion" data-parsley-maxlength="100"/>
						</div>
					</div>
					<div class="form-group col-md-6 hidden fieldPrincipalDevice">
						<label class="control-label">整改日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText builder signDate" id="fieldPrincipalDeviceDate" name="fieldPrincipalDeviceDate" />
						</div>
					</div>
					<div class="form-group col-md-6 allSign builder">
						<label class="control-label signature-tool" for="fieldPrincipalSign">现场代表</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_5"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="fieldPrincipalSign" name="fieldPrincipalSign" value="">
							<input type="hidden" class="signPost" id="fieldPrincipalSign_postType" name="fieldPrincipalSign_postType" value="${cuSpdPrincipal }" >
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool">签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText builder signDate"  id="fieldPrincipalSignDate" name="fieldPrincipalSignDate" />
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label">监理公司</label>
						<div>
							<label><input class="field-editable allText suJgj" type="radio" name="suNameDevice" value="1"  checked> 合格</label>
							<label><input class="field-editable allText suJgj" type="radio" name="suNameDevice" value="0" > 整改</label>
							<label><input class="field-editable allText suJgj" type="radio" name="suNameDevice" value="2" > 整改合格</label>
						</div>
					</div>
					<div class="form-group col-md-12 hidden suNameDevice">
						<label class="control-label">整改意见</label>
						<div>
							<input type="text" class="form-control field-editable noIdea allText suJgj" name ="suNameDeviceOpinion" id="suNameDeviceOpinion" data-parsley-maxlength="100"/>
						</div>
					</div>
					<div class="form-group col-md-6 hidden suNameDevice">
						<label class="control-label signature-tool">整改日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText suJgj signDate" id="suNameDeviceDate" name="suNameDeviceDate" />
						</div>
					</div>
					<div class="form-group col-md-6 allSign suJgj">
						<label class="control-label signature-tool" for="suNameSign">验收人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_6"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="suNameSign" name="suNameSign" value="">
							<input type="hidden" class="signPost" id="suNameSign_postType" name="suNameSign_postType" value="${suFieldJgj }" >
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool">签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText suJgj signDate"  id="suNameSignDate" name="suNameSignDate" />
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label"> 安发公司</label>
						<div>
							<label><input class="field-editable allText dataHandle" type="radio" name="informCenterDevice" value="1"  checked> 合格</label>
							<label><input class="field-editable allText dataHandle" type="radio" name="informCenterDevice" value="0" > 整改</label>
							<label><input class="field-editable allText dataHandle" type="radio" name="informCenterDevice" value="2" > 整改合格</label>
						</div>
					</div>
					<div class="form-group col-md-12 hidden informCenterDevice">
						<label class="control-label">整改意见</label>
						<div>
							<input type="text" class="form-control field-editable noIdea allText dataHandle" name ="informCenterDeviceOpinion" id="informCenterDeviceOpinion" data-parsley-maxlength="100"/>
						</div>
					</div>
					<div class="form-group col-md-6 hidden informCenterDevice">
						<label class="control-label signature-tool">整改日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText dataHandle signDate" id="informCenterDeviceDate" name="informCenterDeviceDate"/>
						</div>
					</div>
					<div class="form-group col-md-6 allSign dataHandle">
						<label class="control-label signature-tool" for="informCenterSign">验收人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_7"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="informCenterSign" name="informCenterSign" value="">
							<input type="hidden" class="signPost" id="informCenterSign_postType" name="informCenterSign_postType" value="${informCenterSign }" >
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool">签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText dataHandle signDate"  id="informCenterSignDate" name="informCenterSignDate" />
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label">技装部</label>
						<div>
							<label><input class="field-editable allText equipmentTechnician" type="radio" name="techEquipmentDevice" value="1"  checked> 合格</label>
							<label><input class="field-editable allText equipmentTechnician" type="radio" name="techEquipmentDevice" value="0" > 整改</label>
							<label><input class="field-editable allText equipmentTechnician" type="radio" name="techEquipmentDevice" value="2" > 整改合格</label>
						</div>
					</div>
					<div class="form-group col-md-12 hidden techEquipmentDevice">
						<label class="control-label">整改意见</label>
						<div>
							<input type="text" class="form-control field-editable noIdea allText equipmentTechnician" name ="techEquipmentDeviceOpinion" id="techEquipmentDeviceOpinion" data-parsley-maxlength="100"/>
						</div>
					</div>
					<div class="form-group col-md-6 hidden techEquipmentDevice">
						<label class="control-label signature-tool">整改日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText equipmentTechnician signDate" id="techEquipmentDeviceDate" name="techEquipmentDeviceDate"/>
						</div>
					</div>
					<div class="form-group col-md-6 allSign equipmentTechnician">
						<label class="control-label signature-tool" for="techEquipmentSign">验收人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_8"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="techEquipmentSign" name="techEquipmentSign" value="">
							<input type="hidden" class="signPost" id="techEquipmentSign_postType" name="techEquipmentSign_postType" value="${techEquipmentDevice }" >
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool">签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText equipmentTechnician signDate"  id="techEquipmentSignDate" name="techEquipmentSignDate" />
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label">工程建设管理部</label>
						<div>
							<label><input class="field-editable allText technician" type="radio" name="marketDevDevice" value="1"  checked> 合格</label>
							<label><input class="field-editable allText technician" type="radio" name="marketDevDevice" value="0" > 整改</label>
							<label><input class="field-editable allText technician" type="radio" name="marketDevDevice" value="2" > 整改合格</label>
						</div>
					</div>
					<div class="form-group col-md-12 hidden marketDevDevice">
						<label class="control-label">整改意见</label>
						<div>
							<input type="text" class="form-control field-editable noIdea allText technician" name ="marketDevDeviceOpinion" id="marketDevDeviceOpinion" data-parsley-maxlength="100"/>
						</div>
					</div>
					<div class="form-group col-md-6 hidden marketDevDevice">
						<label class="control-label signature-tool">整改日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText technician signDate" id="marketDevDeviceDate" name="marketDevDeviceDate"/>
						</div>
					</div>
					<div class="form-group col-md-6 allSign technician">
						<label class="control-label signature-tool" for="marketDevSign">质量安全组</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_10"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="marketDevSign" name="marketDevSign" value="">
							<input type="hidden" class="signPost" id="marketDevSign_postType" name="marketDevSign_postType" value="${custCenterSign }" >
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool">签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText technician signDate" id="marketDevSignDate" name="marketDevSignDate" />
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label">其他部门</label>
						<div>
							<label><input class="field-editable" type="radio" name="economicDevice" value="1"  checked> 合格</label>
							<label><input class="field-editable" type="radio" name="economicDevice" value="0" > 整改</label>
							<label><input class="field-editable" type="radio" name="economicDevice" value="2" > 整改合格</label>
						</div>
					</div>
					<div class="form-group col-md-12 hidden economicDevice">
						<label class="control-label">整改意见</label>
						<div>
							<input type="text" class="form-control field-editable noIdea" name ="economicDeviceOpinion" id="economicDeviceOpinion" data-parsley-maxlength="100"/>
						</div>
					</div>
					<div class="form-group col-md-6 hidden economicDevice">
						<label class="control-label">整改日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default signDate" id="economicDeviceDate" name="economicDeviceDate"/>
						</div>
					</div>
					<div class="form-group col-md-6 ">
						<label class="control-label signature-tool" for="economicSign">验收人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_9"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="economicSign" name="economicSign" value="">
							<input type="hidden" class="signPost" id="custCenterSign_postType" name="custCenterSign_postType" value="${organizationMan }" >
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool">签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable noIdea datepicker-default "  id="economicSignDate" name="economicSignDate" />
						</div>
					</div>
				</form>
			</div>
		</div>
		<%--//分部验收信息--%>
		<div class="tab-pane fade in btn-top" id="divisionalAcceptance_list" >
			<table class="table table-hover table-striped table-bordered nowrap" id="divisionalAcceptanceListTable" width="100%">
				<thead>
				<tr>
					<th>验收ID</th>
					<th>验收日期</th>
					<th>验收内容</th>
				</tr>
				</thead>
			</table>
		</div>
		<div class="tab-pane fade in btn-top" id="divisionalAcceptance_info" >
			<div class="clearboth form-box">
				<form class="form-horizontal" id="diviaionalAcceptanceInfoForm" data-parsley-validate="true" action="">
					<input type="hidden" id="daId" name="daId"/>
					<input type="hidden" id="daaId" name="daaId"/>
					<div class="form-group  col-md-6 ">
						<label class="control-label" for="projNo">工程编号</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable" name="projNo" data-parsley-maxlength="50" value=""/>
						</div>
					</div>
					<div class="form-group col-md-12 ">
						<label class="control-label" for="projName">工程名称</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable" name="projName" data-parsley-maxlength="200" value=""/>
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label" for="projAddr">工程地点</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable" name="projAddr" data-parsley-maxlength="200" value=""/>
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label">工程规模</label>
						<div>
							<textarea class="form-control input-sm field-not-editable" name="projScaleDes" rows="" cols="" data-parsley-maxlength="200"></textarea>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12 clearboth">
						<label class="control-label">监理报告</label>
						<div>
							<label><input type="radio" class="field-not-editable" name="suReport" value="1" checked> 有</label>
							<label><input type="radio" class="field-not-editable" name="suReport" value="2" > 无</label>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12 ">
						<label class="control-label">试压报告</label>
						<div>
							<label><input type="radio" class="field-not-editable" name="strengthTest" value="1" checked> 有</label>
							<label><input type="radio" class="field-not-editable" name="strengthTest" value="2" > 无</label>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12 ">
						<label class="control-label">吹扫记录</label>
						<div>
							<label><input type="radio" class="field-not-editable" name="purgeRecord" value="1" checked> 有</label>
							<label><input type="radio" class="field-not-editable" name="purgeRecord" value="2" > 无</label>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12">
						<label class="control-label">施工自检表</label>
						<div>
							<label><input type="radio" class="field-not-editable" name="selfCheckTable" value="1" checked> 有</label>
							<label><input type="radio" class="field-not-editable" name="selfCheckTable" value="2" > 无</label>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12 ">
						<label class="control-label">分部验收表</label>
						<div>
							<label><input type="radio" class="field-not-editable" name="acceptanceTable" value="1" checked> 有</label>
							<label><input type="radio" class="field-not-editable" name="acceptanceTable" value="2" > 无</label>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12 ">
						<label class="control-label">三份竣工图</label>
						<div>
							<label><input type="radio" class="field-not-editable" name="completionDrawing" value="1" checked> 有</label>
							<label><input type="radio" class="field-not-editable" name="completionDrawing" value="2" > 无</label>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12 ">
						<label class="control-label">是否整体完工</label>
						<div>
							<label><input class="field-not-editable" type="radio" name="isWholeComplete" value="1" checked> 是</label>
							<label><input type="radio" class="field-not-editable" name="isWholeComplete" value="2" > 否</label>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label">验收时间</label>
						<div>
							<input type="text" class="form-control input-sm  datepicker-default field-not-editable"  id="acceptanceDate" name="acceptanceDate" />
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="control-label" for="thisAcceptanceContent">本次验收内容</label>
						<div>
							<textarea class="form-control field-not-editable" name="thisAcceptanceContent" id="thisAcceptanceContent" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
						</div>
					</div>
					<div class="form-group col-sm-12 craftWork">
						<label class="control-label" for="transmissionOpinion">输配公司</label>
						<div>
							<textarea class="form-control field-not-editable" name="transmissionOpinion" id="transmissionOpinion" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
						</div>
					</div>
					<div class="form-group col-sm-12 craftWork">
						<label class="control-label" for="customerServiceOpinion">客服中心</label>
						<div>
							<textarea class="form-control field-not-editable" name="customerServiceOpinion" id="customerServiceOpinion" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
						</div>
					</div>
					<div class="form-group col-sm-12 craftWork">
						<label class="control-label" for="metrologyOfficeOpinion">计量所</label>
						<div>
							<textarea class="form-control field-not-editable" name="metrologyOfficeOpinion" id="metrologyOfficeOpinion" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
						</div>
					</div>
					<div class="form-group col-sm-12 craftWork">
						<label class="control-label" for="remark">备注</label>
						<div>
							<textarea class="form-control field-not-editable" name="remark" id="remark" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12">
						<label class="control-label signature-tool sign-require" for="cuSpdPrincipal">现场代表</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="cuSpdPrincipal" name="cuSpdPrincipal" value="">
							<input type="hidden" id="cuSpdParticipant_postType" name="cuSpdParticipant_postType" value="" >
							<img class="ongcDeputy" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12">
						<label class="control-label signature-tool sign-require" for="duDeputy">设计人员</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="duDeputy" name="duDeputy" value="">
							<img class="duDeputy" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12">
						<label class="control-label signature-tool sign-require" for="suFieldJgj">现场监理</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="suFieldJgj" name="suFieldJgj" value="">
							<img class="suFieldJgj" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12">
						<label class="control-label signature-tool sign-require" for="managementQae">施工员</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" name="managementQae" value="">
							<img class="managementQae" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12">
						<label class="control-label signature-tool sign-require" for="organizationMan">组织人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="organizationMan" name="organizationMan" value="">
							<img class="organizationMan" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool" for="measurementSign">计量所</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" name="measurementSign" value="">
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool" for="transCompanySign">输配公司</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input"name="transCompanySign" value="">
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool" for="custCenterSign">客服中心</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" name="custCenterSign" value="">
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    //表单样式适应
    $("#diviaionalAcceptanceInfoForm").toggleEditState(false).styleFit();
    //表单样式适应
    $("#jointAcceptanceform").toggleEditState(true).styleFit();
    //    changeAText();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    var json=trSData.jointAcceptanceTable.json;
    if($("#deptDivide").val()==$("#qualitySafetyGroup").val()){
			 if(json.projChanges.indexOf(3) >= 0 || json.projChanges.indexOf(0) >= 0 || json.projChanges == '' || json.projChanges == null){
				 $(".pushButton").addClass("hidden");
			 }else{
				 $(".pushButton").removeClass("hidden");
			 }
		}else{
			$(".pushButton").addClass("hidden");
			$(".jointButton").addClass("hidden");
		}
    //不可编辑
    $("#jointAcceptanceform").toggleEditState(false);

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    var divisionalSearchData={},
    jointSearchData={}; //查询条件
	jointSearchData.projId="-1";
	divisionalSearchData.projId="-1";
	//列表页签
	$('#divisionalAcceptanceList').on('shown.bs.tab',function(){
		$("#flag").val("0");
	});
	$('#divisionalAcceptanceInfo').on('shown.bs.tab',function(){
		$("#flag").val("0");
	});
	$('#jointAcceptanceList').on('shown.bs.tab',function(){
		$("#flag").val("0");
		$(".editbtn").addClass("hidden");
		$('#jointAcceptanceListTable').cgetData();
	});
	$('#jointAcceptanceInfo').on('shown.bs.tab',function(){
		if($("#flag").val()=='1'){
			  //根据职务过滤可编辑项
	        $('#jointAcceptanceform').toggleEditState(true);
	        $(".economicDevice").find(".sign-data-input").toggleSign(true);	//自己对应的签字可编辑
	        $(".economicDevice").removeClass("field-not-editable");     //自己对应的文本可编辑,
	        $(".economicDevice").addClass("field-editable");
// 	        $("#jointAcceptanceform").formReset();
			$(".editbtn").removeClass("hidden");
	       // $('#jointAcceptanceListTable').clearSelected();
			$("#jaId").val("");
			$(".noIdea").val("");
	        getSignStatusByPostId($("#post").val(),"jointAcceptanceform");
			//清除签字
			$(".clear-sign").click();
	        radioBack();
	        signDate();
		}else if($("#flag").val()=='2'){
			  //根据职务过滤可编辑项
			//维护按钮显示出来
			$('.editbtn').removeClass('hidden');
			//切换可编辑状态
			// $('#jointAcceptanceform').toggleEditState(true);
            //根据职务过滤可编辑项
            getSignStatusByPostId($("#post").val(),"jointAcceptanceform");
            signDate();
		}else{
			 //按钮隐藏
		    $(".editbtn").addClass("hidden");
			// trSData.jointAcceptanceListTable.t && trSData.jointAcceptanceListTable.t.cgetDetail('jointAcceptanceform','','',queryBack);
		    //不可编辑
		    $("#jointAcceptanceform").toggleEditState(false);
		}
	});

	/**
	 * 加载右屏联合验收列表
	 */
	var handleJointAcceptanceList = function(){
		"use strict";
	    if ($('#jointAcceptanceListTable').length !== 0) {
	    	$('#jointAcceptanceListTable').on( 'init.dt',function(){
	   			//默认选中第一行
	    		$(this).bindDTSelected(trListSelectedBack,true);
	    		//隐藏遮罩
	   			$('#jointAcceptanceListTable').hideMask();
	   			//增加监听
	   			addMonitor();
	   			//修改监听
	   	    	modifyMonitor();
	            if($("#deptDivide").val()==$("#qualitySafetyGroup").val()){
	                $(".addBtn").removeClass("hidden");
	            }else{
	                $(".addBtn").addClass("hidden");
	            }
	        }).DataTable({
	        	language: language_CN,
	            lengthMenu: [18],
	           // dom: 'Brtip',
	            dom: 'Brt',
	            buttons: [
	                { text: '增加', className: 'btn-sm btn-query checkRole hidden addBtn' },
	                { text: '修改', className: 'btn-sm btn-query checkRole hidden updateBtn business-tool-btn' }
	            ],
	          //启用服务端模式，后台进行分段查询、排序
	            serverSide:true,
	            ajax: {
	                url: 'jointAcceptance/queryJointList',
	                type:'post',
	                data: function(d){
	                   	$.each(jointSearchData,function(i,k){
	                   		d[i] = k;
	                   	});
	                   	
	                },
	                dataSrc: 'data'
	            },
	            // ajax: 'projectjs/complete/json/joint-acceptance-list.json',
	            responsive: {
	            	details: {
	            		renderer: function ( api, rowIdx, columns ){
	            			return renderChild(api, rowIdx, columns);
	            		}
	            	}
	            },
	            select: true,  //支持多选
	            columns: [
	      			{"data":"jaId",className:"none never"},
	      			{"data":"jaDate"},
	      			{"data":"jaClum"}
					],
				columnDefs: [{
					"targets": 0,
					"visible":false
				}],
	        });
	    }
	};
	/**
	 * 右屏修改监听
	 */
	var modifyMonitor = function(){
		$('.updateBtn').off('click').on('click',function(){
			if($('#jointAcceptanceListTable').find('tr.selected').length>0){
				$("#flag").val('2');
				$('#jointAcceptanceInfo').tab("show");
			}else{
				alertInfo('请选择验收记录！');
			}
		});
	}
	var queryBack = function(data){
		console.info("queryBack=======");
		console.info(data);
	    $('input:radio[name="custCenterDevice"]').change();
	    $('input:radio[name="transCompanyDevice"]').change();
	    $('input:radio[name="measurementDevice"]').change();
	    $('input:radio[name="pdUnitDevice"]').change();
	    $('input:radio[name="fieldPrincipalDevice"]').change();
	    $('input:radio[name="suNameDevice"]').change();
	    $('input:radio[name="informCenterDevice"]').change();
	    $('input:radio[name="techEquipmentDevice"]').change();
	    $('input:radio[name="marketDevDevice"]').change();
	    $('input:radio[name="economicDevice"]').change();
	}
	/**
	 * 右屏列表选中行
	 */
	var trListSelectedBack = function(v, i, index, t, json){
		//将查询详述显示到相应的输入框内
		t.cgetDetail('jointAcceptanceform','','',queryBack);
		//传false表示不可编辑
		$("#jointAcceptanceform").toggleEditState(false);
		$(".editbtn").addClass("hidden");
	};
	/**
	 * 加载右屏分布验收列表
	 */
	var handleDivisionalAcceptanceList = function(){
	    "use strict";
	    if ($('#divisionalAcceptanceListTable').length !== 0) {
	        $('#divisionalAcceptanceListTable').on( 'init.dt',function(){
	            //默认选中第一行
	            $(this).bindDTSelected(daListSelectedBack,true);
	            //隐藏遮罩
	            $('#divisionalAcceptanceListTable').hideMask();
	            //增加监听
	            // addMonitor();
	        }).DataTable({
	            language: language_CN,
	            lengthMenu: [18],
	            // dom: 'Brtip',
	            dom: 'Brt',
	            buttons: [
	                // { text: '查看', className: 'btn-sm btn-query checkRole hidden addBtn' },
	            ],
	            //启用服务端模式，后台进行分段查询、排序
	            serverSide:true,
	            ajax: {
	                url: 'jointAcceptance/queryDivisionalList',
	                type:'post',
	                data: function(d){
	                    $.each(divisionalSearchData,function(i,k){
	                        d[i] = k;
	                    });

	                },
	                dataSrc: 'data'
	            },
	            responsive: {
	                details: {
	                    renderer: function ( api, rowIdx, columns ){
	                        return renderChild(api, rowIdx, columns);
	                    }
	                }
	            },
	            select: true,  //支持多选
	            columns: [
	                {"data":"daId",className:"none never"},
	                {"data":"acceptanceDate"},
	                {"data":"thisAcceptanceContent"}
	            ],
	            columnDefs: [{
	                "targets": 0,
	                "visible":false
	            }],
	        });
	    }
	};
	/**
	 * 右屏分部验收列表选中行事件
	 */
	var daListSelectedBack = function(v, i, index, t, json){
	    //将查询详述显示到相应的输入框内
	    t.cgetDetail('diviaionalAcceptanceInfoForm','','',"");
	}
	/**
	 * 右屏增加监听
	 */
	var addMonitor = function(){
		$(".addBtn").off("click").on("click",function(){
			$("#flag").val("1");
			$('#jointAcceptanceInfo').tab('show');
		});
	};
    trSData.t && trSData.t.cgetDetail('jointAcceptanceform','','',queryBack);
    getContentBack = function(){
   	 $("#jointAcceptanceform").formReset();
   	 //$(".field-editable").val("");
   		//清除签字
   	$(".clear-sign").click();
   	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
   	trSData.t && trSData.t.cgetDetail('jointAcceptanceform','','',tableCallBack);
		if($("#projId1").val()==''){
			jointSearchData.projId="-1";
			divisionalSearchData.projId="-1";
		}else{
			jointSearchData.projId=$("#projId1").val();
		    divisionalSearchData.projId=$("#projId1").val();
		}
       if($.fn.DataTable.isDataTable('#jointAcceptanceListTable')){
           $('#jointAcceptanceListTable').cgetData(false);
       }else{
           handleJointAcceptanceList();
       };
       
       if($.fn.DataTable.isDataTable('#divisionalAcceptanceListTable')){
           $('#divisionalAcceptanceListTable').cgetData(false);
       }else{
           handleDivisionalAcceptanceList();
       }
   }();
    tableCallBack();
    var acceptPush = function () {
        //表单验证
        if($("#jointAcceptanceform").parsley().isValid()){
            //验证必签签字是否已签
            var signtools =$('#jointAcceptanceform').find(".signature-tool"),
                stl = signtools.length,
                sBlank = 0;
            for(var i=0; i<stl; i++){
                var tstool = signtools.eq(i).next("div").find("a.btn-white"),
                    tsinput = tstool.siblings(".sign-data-input");
                if(!tsinput.val() || tsinput.val().length < 312){
                    tstool.addClass("required-sign");
                    sBlank++;
                }
            }
            //json字符串
            var data=$("#jointAcceptanceform").serializeJson();
             if(data.marketDevSignDate!=''){
    			data.jaDate = data.marketDevSignDate;
    		 }else{
    			 data.jaDate ='';
    		 }
            $.ajax({
                type: 'POST',
                url: 'jointAcceptance/entJoint',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                    var content = "数据保存成功！";
                    if(data === "false"){
                        content = "数据保存失败！";
                    }else if(data === "true"){
                        $("#jointAcceptanceform").formReset();
                        $("#jointAcceptanceTable").cgetData(true,tableCallBack);  //列表重新加载
                        jointSearchData.projId="-1";
                        $("#jointAcceptanceListTable").cgetData();  //列表重新加载
                        $(".editbtn").addClass("hidden");
                    }
                    var myoptions = {
                        title: "提示信息",
                        content: content,
                        accept: popClose,
                        chide: true,
                        icon: "fa-check-circle",
                        newpop: 'new'
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("计划签订区记录保存失败！");
                }
            });
            //如果通过验证, 则移除验证UI
            $("#jointAcceptanceform").parsley().reset();
        }else{
            //如果未通过验证, 则加载验证UI
            $("#jointAcceptanceform").parsley().validate();
        }
    };

    var radioBack = function () {
        $("input[name='custCenterDevice'][value='1']").attr("checked","checked");
        $("input[name='custCenterDevice'][value='1']").change();
        $("input[name='transCompanyDevice'][value='1']").attr("checked","checked");
        $("input[name='transCompanyDevice'][value='1']").change();
        $("input[name='measurementDevice'][value='1']").attr("checked","checked");
        $("input[name='measurementDevice'][value='1']").change();
        $("input[name='pdUnitDevice'][value='1']").attr("checked","checked");
        $("input[name='pdUnitDevice'][value='1']").change();
        $("input[name='fieldPrincipalDevice'][value='1']").attr("checked","checked");
        $("input[name='fieldPrincipalDevice'][value='1']").change();
        $("input[name='suNameDevice'][value='1']").attr("checked","checked");
        $("input[name='suNameDevice'][value='1']").change();
        $("input[name='informCenterDevice'][value='1']").attr("checked","checked");
        $("input[name='informCenterDevice'][value='1']").change();
        $("input[name='techEquipmentDevice'][value='1']").attr("checked","checked");
        $("input[name='techEquipmentDevice'][value='1']").change();
        $("input[name='marketDevDevice'][value='1']").attr("checked","checked");
        $("input[name='marketDevDevice'][value='1']").change();
        $("input[name='economicDevice'][value='1']").attr("checked","checked");
        $("input[name='economicDevice'][value='1']").change();
    };

    //放弃
    $(".cancelBtn").on("click",function(){
        //$('#jointAcceptanceform').formReset();
        $("#jointAcceptanceTable").cgetData();                                  //列表重新加载
        $("#jointAcceptanceListTable").cgetData();                              //列表重新加载
        $(".clear-sign").click();                                               //签名清空
        $('#jointAcceptanceform').toggleEditState(false);                       //切换不可编辑状态
        $('.editbtn').addClass('hidden');										//维护按钮隐藏
        $("#jointAcceptanceList").tab('show');
        //如果通过验证, 则移除验证UI
        //$("#jointAcceptanceform").parsley().validate();
        radioBack();
    });

    var SaveCallBack = function(data){
        if(data === "true"){
            //表单不可编辑
            $("#jointAcceptanceform").toggleEditState(false);
            //按钮隐藏
            $(".editbtn").addClass("hidden");
            $('#jointAcceptanceListTable').cgetData();
            $('#jointAcceptanceTable').cgetData(true,tableCallBack); // 列表重新加载
            sureClose();
        }
    };
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
        //$('#jointAcceptanceform').cformSave('jointAcceptanceListTable',SaveCallBack);
    	if($("#jointAcceptanceform").parsley().isValid()){
    		//加遮罩
    		$(".infodetails").loadMask("正在保存...", 1, 0.5);
    		//json字符串
        	var data=$("#jointAcceptanceform").serializeJson();
    		 if(data.marketDevSignDate!=''){
    			data.jaDate = data.marketDevSignDate;
    		 }else{
    			 data.jaDate ='';
    		 }
        	$.ajax({
                type: 'POST',
                url: 'jointAcceptance/saveJoint',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "true"){
                		SaveCallBack(data);
                	}else if(data === "already"){
                   		content = "此信息已被修改，请刷新页面！";
                   	}
                	var myoptions = {
                        	title: "提示信息",
                        	content: content,
                        	accept: sureClose,
                        	chide: true,
                        	icon: "fa-check-circle"
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                    console.warn("联合验收保存失败！");
                }
            });
    		//如果通过验证, 则移除验证UI
        	$("#jointAcceptanceform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#jointAcceptanceform").parsley().validate();
        }
    });

    $(".fieldPrincipal-a").handleSignature();
    //资料类型改变
    $("#dataType").change(function(){
        if($(this).val()=="2"){
            $("#startingReportForm input").val("");
            jointAcceptanceDetail();
        }else{
            $("#startingReportForm input").val("");
            jointAcceptanceDetail();
        }
    });


    if(_inApk){
        $("#jointAcceptanceList").text("列表区");
        $("#jointAcceptanceInfo").text("签字区");
        $("#divisionalAcceptanceList").text("验收区");
        $("#divisionalAcceptanceInfo").text("信息区");
    }else{
        $("#jointAcceptanceList").text("联合验收列表");
        $("#jointAcceptanceInfo").text("签字区");
        $("#divisionalAcceptanceList").text("分部验收");
        $("#divisionalAcceptanceInfo").text("分部验收信息");
    }

    /*  if(_inApk){
     $('.mobileDiv').removeClass("hidden");
     $(".get-location").removeClass("hidden").off("click").on("click", function(){
     var t = $(this);
     t.button("loading");
     cgetLocation(function(position) {
     $('[name="longitude"]').val(position.longitude);
     $('[name="latitude"]').val(position.latitude);
     t.button("reset");
     });
     });
     } */
    var sureClose=function(){
        var cwId=$("#jaId").val();
        $.ajax({
            type:'post',
            url:'jointAcceptance/saveSignNotice',
            contentType: "application/json;charset=UTF-8",
            data: cwId,
            success:function(data){
                console.info(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("数据保存失败！");
            }
        })
    }

    //客服验收情况chang事件
    $('input:radio[name="custCenterDevice"]').change(function(){
        showCc();
    });
    var showCc = function(){
        if($('input:radio[name="custCenterDevice"]:checked').val()=='0' || $("#custCenterDeviceOpinion").val()!=''){//整改
            $(".custCenterDevice").removeClass("hidden");
        }else{
            $(".custCenterDevice").addClass("hidden");
        }
        //表单样式适应
        $("#jointAcceptanceform").styleFit();
    }

    //输配验收情况chang事件
    $('input:radio[name="transCompanyDevice"]').change(function(){
        showTc();
    });
    var showTc = function(){
        if($('input:radio[name="transCompanyDevice"]:checked').val()=='0' || $("#transCompanyDeviceOpinion").val()!=''){//整改
            $(".transCompanyDevice").removeClass("hidden");
        }else{
            $(".transCompanyDevice").addClass("hidden");
        }
        //表单样式适应
        $("#jointAcceptanceform").styleFit();
    }

    //计量所验收情况chang事件
    $('input:radio[name="measurementDevice"]').change(function(){
        showMd();
    });
    var showMd = function(){
        if($('input:radio[name="measurementDevice"]:checked').val()=='0' || $("#measurementDeviceOpinion").val()!=''){//整改
            $(".measurementDevice").removeClass("hidden");
        }else{
            $(".measurementDevice").addClass("hidden");
        }
        //表单样式适应
        $("#jointAcceptanceform").styleFit();
    }

    //设计公司验收情况chang事件
    $('input:radio[name="pdUnitDevice"]').change(function(){
        showPd();
    });
    var showPd = function(){
        if($('input:radio[name="pdUnitDevice"]:checked').val()=='0' || $("#pdUnitDeviceOpinion").val()!=''){//整改
            $(".pdUnitDevice").removeClass("hidden");
        }else{
            $(".pdUnitDevice").addClass("hidden");
        }
        //表单样式适应
        $("#jointAcceptanceform").styleFit();
    }

    //工程建设管理部（现场代表）验收情况chang事件
    $('input:radio[name="fieldPrincipalDevice"]').change(function(){
        showFp();
    });
    var showFp = function(){
        if($('input:radio[name="fieldPrincipalDevice"]:checked').val()=='0' || $("#fieldPrincipalDeviceOpinion").val()!=''){//整改
            $(".fieldPrincipalDevice").removeClass("hidden");
        }else{
            $(".fieldPrincipalDevice").addClass("hidden");
        }
        //表单样式适应
        $("#jointAcceptanceform").styleFit();
    }

    //监理公司验收情况chang事件
    $('input:radio[name="suNameDevice"]').change(function(){
        showSu();
    });
    var showSu = function(){
        if($('input:radio[name="suNameDevice"]:checked').val()=='0'  || $("#suNameDeviceOpinion").val()!=''){//整改
            $(".suNameDevice").removeClass("hidden");
        }else{
            $(".suNameDevice").addClass("hidden");
        }
        //表单样式适应
        $("#jointAcceptanceform").styleFit();
    }

    //安发公司验收情况chang事件
    $('input:radio[name="informCenterDevice"]').change(function(){
        showIc();
    });
    var showIc = function(){
        if($('input:radio[name="informCenterDevice"]:checked').val()=='0' || $("#informCenterDeviceOpinion").val()!=''){//整改
            $(".informCenterDevice").removeClass("hidden");
        }else{
            $(".informCenterDevice").addClass("hidden");
        }
        //表单样式适应
        $("#jointAcceptanceform").styleFit();
    }

    //技装部验收情况chang事件
    $('input:radio[name="techEquipmentDevice"]').change(function(){
        showTe();
    });
    var showTe = function(){
        if($('input:radio[name="techEquipmentDevice"]:checked').val()=='0' || $("#techEquipmentDeviceOpinion").val()!=''){//整改
            $(".techEquipmentDevice").removeClass("hidden");
        }else{
            $(".techEquipmentDevice").addClass("hidden");
        }
        //表单样式适应
        $("#jointAcceptanceform").styleFit();
    }

    //工程建设管理部（质量安全组）验收情况chang事件
    $('input:radio[name="marketDevDevice"]').change(function(){
        showMdd();
    });
    var showMdd = function(){
        if($('input:radio[name="marketDevDevice"]:checked').val()=='0'|| $("#marketDevDeviceOpinion").val()!=''){//整改
            $(".marketDevDevice").removeClass("hidden");
        }else{
            $(".marketDevDevice").addClass("hidden");
        }
        //表单样式适应
        $("#jointAcceptanceform").styleFit();
    }

    //其他部门验收情况chang事件
    $('input:radio[name="economicDevice"]').change(function(){
        showEd();
    });
    var showEd = function(){
        if($('input:radio[name="economicDevice"]:checked').val()=='0' || $("#economicDeviceOpinion").val()!=''){//整改
            $(".economicDevice").removeClass("hidden");
        }else{
            $(".economicDevice").addClass("hidden");
        }
        //表单样式适应
        $("#jointAcceptanceform").styleFit();
    }
    var jointAcceptanceList = function(){
        "use strict";
        return {
            //main function
            init: function () {
                $.getScript("js/ellipsis.js").done(function(){
                    handleJointAcceptanceList();
                });
                $('[href="#jointAcceptance_info"]').on("show.bs.tab", function(){
                    $("#jointAcceptanceform").styleFit();
                    $(".fieldPrincipal-a").handleSignature();
                });
                $("#jointAcceptanceList ,#jointAcceptance_info").on("shown.bs.tab",function(){
                    if(!$(this).is($("#jointAcceptanceList"))){
                        handleJointAcceptanceList();
                    }else{
                        $('#jointAcceptanceListTable').cgetData(false);
    				}
                });
            }
        };
    }();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->