<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse tabs-mixin">
                <div class="panel-heading">
                   	<div class="panel-heading-btn">
                       	<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                       	<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                       	<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                   	</div>
                   	<h4 class="panel-title">开工报告</h4>
                </div>
                <div class="panel-body">
                    <div class="toolBtn f-r p-b-10 ">
                    	<a href="javascript:;" class="btn btn-confirm btn-sm checkRole saveBtn" >保存</a>
  	                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole updataBtn" >修改</a>
                    </div>
                    <div class="clearboth form-box">
                    	<input type="hidden" id="contractType"name="contractType" value="">
                    	<input type="hidden" class="imgUrl" value="${imgUrl}"/>
                    	<input type="hidden" id="loginPost" value="${loginPost}"/>
                    	<input type="hidden" id="dataAdmin" value="${dataAdmin}"/>
                    	<input type="hidden" name="customerServiceCenter" id="customerServiceCenter"  value="${customerServiceCenter}"/>
                    	<form class="form-horizontal" id="startingReportForm" action="">
                    		<input type="hidden" id="projId"name="projId" value="">
							<input type="hidden" id="projNo"name="projNo" value="">
							<input type="hidden" id="wrId"name="wrId" value="" class="addClear">
							<!-- <input type="hidden" id="suDate"name="suDate" value=""> -->
							<input type="hidden" id="corpId"name="corpId" value="">
							<input type="hidden" id="deptId"name="deptId" value="">
							<input type="hidden" id="signState"name="signState" value="">
							<input type="hidden"id="deptDivide"name="deptDivide" value="">
							<input type="hidden" id="tenantId"name="tenantId" value="">
							<input type="hidden" id="plannedEndDateOld"name="plannedEndDateOld" value="">
							<input type="hidden" id="version"name="version" value="">
							
							<div class="form-group col-sm-12">
								<label class="control-label" for="projName">工程名称</label>
								<div>
								<input type="text" class="form-control input-sm field-not-editable" value="" id="projName" name="projName"  />
								</div>
						 	</div>
						 	<div class="form-group col-sm-12">
								<label class="control-label" for="projAddr">安装地点</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable" value="" id="projAddr" name="projAddr"  />
								</div>
						    </div>
						    
						    <div class="form-group col-sm-12">
								<label class="control-label" for="projScaleDes">工程规模</label>
								<div>
									<textarea class="form-control field-not-editable" name="projScaleDes" id="projScaleDes" rows="2" cols="" ></textarea>
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12 conNo">
								<label class="control-label" for="conNo">合同编号</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable" value="" id="conNo" name="conNo"  />
								</div>
						    </div>
						    <div class="form-group col-sm-12 conNo">
								<label class="control-label" for="custName">用户名称</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable" value="" id="custName" name="custName"  />
								</div>
						    </div>
						    <div class="form-group col-sm-12 conNo">
								<label class="control-label" for="custName">交底日期</label>
								<div>
									<input type="text" class="form-control input-sm datepicker-default field-not-editable timestamp" value="" id="cwDate" name="cwDate"  />
								</div>
						    </div>
						    <div class=" form-group col-md-6 clearboth">
						    	<!-- 新加字段 -->
						        <label class="control-label" for="projType">用户类别</label>
						    	<div>
						             <input type="text" class="form-control input-sm field-not-editable" id="projType"  name="projType" data-parsley-maxlength="30"  />
						        </div>
						    </div>
						 	 <div class=" form-group col-md-6 civilType">
						        <label class="control-label" for="projQuantities">用户工程量</label>
						    	<div>
						    		<input type="text" class="form-control input-sm field-editable allText construction" id="projQuantities"  name="projQuantities" data-parsley-maxlength="100" data-parsley-required="true" />
						        </div>
						    </div>
						    <div class=" form-group col-md-6 publicType">
						        <label class="control-label" for="projQuantities1">用户工程量</label>
						    	<div>
						    		<input type="text" class="form-control input-sm field-editable allText construction" id="projQuantities1"  name="projQuantities1" data-parsley-maxlength="100"  data-parsley-required="true" />
						        </div>
						    </div>
						    <div class=" form-group col-md-6 changeType">
						        <label class="control-label" for="projQuantities">用户工程量</label>
						    	<div>
						    		<input type="text" class="form-control input-sm field-editable allText construction" id="projQuantities2"  name="projQuantities2" data-parsley-maxlength="100"  data-parsley-required="true" />
						        </div>
						    </div>
						    <div class=" form-group col-md-6 trunkType">
						        <label class="control-label" for="projQuantities">用户工程量</label>
						    	<div>
						    		<input type="text" class="form-control input-sm field-editable allText construction" id="projQuantities3"  name="projQuantities3" data-parsley-maxlength="100"  data-parsley-required="true" />
						        </div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="patchCode">片区号</label>
								<div>
									<input type="text" class="form-control input-sm field-editable allText construction" value="" id="patchCode" name="patchCode"  data-parsley-maxlength="50"/>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="courtyardNo">庭院管号</label>
								<div>
									<input type="text" class="form-control input-sm field-editable allText construction" value="" id="courtyardNo" name="courtyardNo" data-parsley-maxlength="50" />
								</div>
						    </div>
						     <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="drawingNo">施工图号</label>
								<div>
									<input type="text" class="form-control input-sm field-editable allText construction" value="" id="drawingNo" name="drawingNo"  data-parsley-maxlength="50"/>
								</div>
						    </div>
						     		     
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="timeLimit">工期</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable" id="timeLimit" name="timeLimit"  data-parsley-maxlength="30" data-parsley-required="true"/>
									<a href="javascript:;" class="btn btn-sm btn-default">天</a>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12 plannedStartDate">
								<label class="control-label">开工日期</label>
								<div>
									<input type="text" class="form-control input-sm datepicker-default allText field-not-editable timestamp"  id="plannedStartDate" name="plannedStartDate" data-parsley-required="true"/>
								</div>
							</div>
						    <div class="form-group col-md-6 col-sm-12 plannedEndDate">
								<label class="control-label">竣工日期</label>
								<div>
									<input type="text" class="form-control input-sm datepicker-default allText field-not-editable" id="plannedEndDate" name="plannedEndDate" data-parsley-required="true"/>
								</div>
						    </div>
						 	<div class="form-group col-sm-12 ">
								<label class="control-label" for="trunkInstall">干线安装</label>
								<div>
									<textarea class="form-control field-editable allText construction" name="trunkInstall" id="trunkInstall" rows="3" cols="" data-parsley-maxlength="1000" ></textarea>
								</div>
							</div>
						 	<div class="form-group col-sm-12">
								<label class="control-label" for="courtyardInstall">庭院安装</label>
								<div>
									<textarea class="form-control field-editable allText construction" name="courtyardInstall" id="courtyardInstall" rows="3" cols="" data-parsley-maxlength="1000" ></textarea>
								</div>
							</div>
							<div class="form-group col-sm-12">
						 		<!-- 新加字段 -->
								<label class="control-label" for="indoorInstall">户内安装</label>
								<div>
									<textarea class="form-control field-editable allText construction" name="indoorInstall" id="indoorInstall" rows="3" cols="" data-parsley-maxlength="1000" ></textarea>
								</div>
							</div>
							<div class="form-group col-sm-12">
						 		<!-- 新加字段 -->
								<label class="control-label" for="equipmentInstall">设备安装</label>
								<div>
									<textarea class="form-control field-editable allText construction" name="equipmentInstall" id="equipmentInstall" rows="3" cols="" data-parsley-maxlength="1000" ></textarea>
								</div>
							</div>
						 	<div class="form-group col-sm-12">
								<label class="control-label" for="projectStartCase">施工准备</label>
								<div>
									<textarea class="form-control field-editable allText construction" name="projectStartCase" id="projectStartCase" rows="4" cols="" data-parsley-maxlength="1000" ></textarea>
								</div>
							</div>
							<div class="form-group col-sm-12">
								<label class="control-label" for="remark">备注</label>
								<div>
									<textarea class="form-control field-editable allText construction" name="remark" id="remark" rows="4" cols="" data-parsley-maxlength="600" placeholder="1、此工令单是庭院、公建用户和简单道路改管工程指令，具有施工合同的法律效力，对甲、乙、丙三方法定的约束力。
   2、甲方在落实施工现场、主材配备、施工图纸已具备开工条件后开此工令单，即表示工程项目启动。
   3、工期是考核施工单位合同履行的依据，无特殊情况造成工程延误，按每天3‰工程结算扣罚。如遇非乙方原因造成工期延误应立即办理签证。
   4、此单一试四份，甲、乙、丙三方及经济部各一份。"></textarea>
								</div>
							</div>
							<div class="form-group col-sm-12">
								<label class="control-label" for="corpName">建设单位</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable" value="" id="corpName" name="corpName"  />
								</div>
						  	</div>
						  	<!-- 增加不同的签字 -->
						  	<%--<c:if test="${ !empty startingReportDiffPage}">
										<c:import url="${startingReportDiffPage}"></c:import>
									</c:if>--%>
						  	<div class="form-group col-md-6 col-sm-12 allSign projectLeader viceMinister clearboth">
								<label class="control-label signature-tool" for="projectLeader">项目负责人</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="projectLeader" name="projectLeader" value="">
									<input type="hidden" class="signPost"  id="projectLeader_postType" name="projectLeader_postType" value="${PROJECT_LEADER}" >
									<img class="projectLeader" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="projectLeaderTel">电话</label>
								<div>
									<input type="text" class="form-control input-sm field-editable allText projectLeader" value="" id="projectLeaderTel" name="projectLeaderTel"  />
								</div>
						  	</div>


						  	<div class="form-group col-md-6 col-sm-12 allSign builder clearboth">
							 	<!-- 新加字段 -->
								<label class="control-label signature-tool" for="fieldRepresent">现场代表</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="fieldRepresent" name="fieldRepresent" value="">
									<input type="hidden" class="signPost"  id="fieldRepresent_postType" name="fieldRepresent_postType" value="${BUILDER}" >
									<img class="fieldRepresent" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div>
						     <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="fieldRepresentTel">电话</label>
								<div>
									<input type="text" class="form-control input-sm field-editable allText builder" value="" id="fieldRepresentTel" name="fieldRepresentTel"  />
								</div>
						  	</div>
						     <!-- <div class="form-group col-md-6 col-sm-12">
								<label class="control-label">日期</label>
								<div>
									<input type="text" class="form-control input-sm datepicker-default field-editable allText builder" value="" id="corpDate" name="corpDate"  />
								</div>
						  	</div> -->
						    <div class="form-group col-sm-12 suUnit">
								<label class="control-label" for="suName">监理公司</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable" value="" id="suName" name="suName"  />
								</div>
						  	</div>
						    <div class="form-group col-md-6 col-sm-12 allSign suCse clearboth suUnit">
								<label class="control-label signature-tool" for="suCes">总监</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="suCes" name="suCes" value="">
									<input type="hidden" class="signPost"  id="suCes_postType" name="suCes_postType" value="${SUCSE}" >
									<img class="" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12 suUnit">
								<label class="control-label" for="suCesTel">电话</label>
								<div>
									<input type="text" class="form-control input-sm field-editable allText suCse" value="" id="suCesTel" name="suCesTel"  />
								</div>
						  	</div>
						  	
						    <div class="form-group col-md-6 col-sm-12 allSign suJgj clearboth suUnit">
						    	<!-- 新加字段 -->
								<label class="control-label signature-tool" for="suJgj">现场监理</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
									<input type="hidden" class="signPost"  id="suJgj_postType" name="suJgj_postType" value="${SUJGJ }" >
									<img class="suJgj" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12 suUnit">
								<label class="control-label" for="suJgjTel">电话</label>
								<div>
									<input type="text" class="form-control input-sm field-editable allText suJgj" value="" id="suJgjTel" name="suJgjTel"  />
								</div>
						  	</div>
							<!-- <div class="form-group col-md-6 col-sm-12">
								<label class="control-label">审批日期</label>
								<div>
									<input type="text" class="form-control input-sm datepicker-default field-editable allText suJgj"  id="suCheckDate" name="suCheckDate"  />
								</div>
							</div> -->
						    <div class="form-group col-sm-12">
								<label class="control-label" for="constructionUnit">施工单位</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable" value="" id="constructionUnit" name="constructionUnit"  />
								</div>
						  	</div>
						    <div class="form-group col-md-6 col-sm-12 allSign cuPm clearboth">
						    	<!-- 项目经理改为项目负责人 -->
								<label class="control-label signature-tool" for="cuPm">项目经理</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="cuPm" name="cuPm" value="">
									<input type="hidden" class="signPost"  id="cuPm_postType" name="cuPm_postType" value="${CU_PM}" >
									<img class="cuPm" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div>
						     <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="cuPmTel">电话</label>
								<div>
									<input type="text" class="form-control input-sm field-editable allText cuPm" value="" id="cuPmTel" name="cuPmTel"  />
								</div>
						  	</div>
						  	<div class="form-group col-md-6 col-sm-12 allSign construction clearboth">
						    	<!-- 新加字段 -->
								<label class="control-label signature-tool" for="builder">施工员</label>
								<div>
								
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_8"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
									<input type="hidden" class="signPost"  id="builder_postType" name="builder_postType" value="${CONSTRUCTION}" >
									<img class="builder" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
				    		</div>
				    		 <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="builderTel">电话</label>
								<div>
									<input type="text" class="form-control input-sm field-editable allText construction" value="" id="builderTel" name="builderTel"  />
								</div>
						  	</div>
						    <div class="form-group col-md-6 col-sm-12 allSign qualitativeCheckMember clearboth">
						    	<!-- 新加字段 -->
								<label class="control-label signature-tool" for="constructionQc">质检员</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="constructionQc" name="constructionQc" value="">
									<input type="hidden" class="signPost"  id="constructionQc_postType" name="constructionQc_postType" value="${QUALITATIVE_CHECK_MEMBER}">
									<img class="constructionQc" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
				    		</div>
				    		<div class="form-group col-md-6 col-sm-12 allSign safetyOfficer clearboth">
						    	<!-- 新加字段 -->
								<label class="control-label signature-tool" for="safetyOfficer">安全员</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_7"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="safetyOfficer" name="safetyOfficer" value="">
									<input type="hidden" class="signPost"  id="safetyOfficer_postType" name="safetyOfficer_postType" value="${SAFTYOFFICER}">
									<img class="safetyOfficer" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
				    		</div>
				    		  <div class="form-group col-md-6 col-sm-12 clearboth">
								<label class="control-label">填发日期</label>
								<div>
									<input type="text" class="form-control input-sm datepicker-default field-not-editable allText construction"  id="suDate" name="suDate"  />
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="custContact">工地用户联系人</label>
								<div>
									<input type="text" class="form-control input-sm field-editable allText construction"  id="custContact" name="custContact"  />
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="custContactTel">联系人电话</label>
								<div>
									<input type="text" class="form-control input-sm  field-editable allText construction" id="custContactTel" name="custContactTel"  />
								</div>
							</div>

							<hr style="width: 100%"/>
							<div class="form-group col-md-6">
								<label class="control-label ">审核结果</label>
								<div>
									<label>
										<input type="radio"  name="auditResult" onchange="showAbandonedReason(this.value)" value="1" class="form-control input-sm  field-editable allText builder suJgj" checked/>通过
									</label>
									<label>
										<input type="radio"  name="auditResult" onchange="showAbandonedReason(this.value)"  class="form-control input-sm  field-editable allText builder suJgj" value="0" />未通过
									</label>
								</div>
							</div>
							<div class="form-group col-md-6">
								<a style="cursor:pointer" onclick="showAbandonedRecord()">审核记录查看</a>
							</div>

							<div class="form-group col-md-12 col-sm-12 abandonedReason hidden">
								<label class="control-label">未通过原因：</label>
								<div>
									<textarea id="abandonedReason" name="abandonedReason" class="form-control"  rows="6" data-parsley-required="true"></textarea>
								</div>
							</div>
						</form>
                    </div>
				</div>
        	</div>
        </div>
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <h4 class="panel-title">预览区</h4>
			    </div>
			    <div class="panel-body">
			    	<div class="iframe-report-box">
	                 	<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:0; overflow-y:auto;" scrolling="no"></iframe>
	                </div>
			    </div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('开工报告 - 工程项目管理系统');
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	var projId=projJson.projId;
    	
    	$("#contractType").val(projJson.contractType);
    	$("#projType").val(projJson.projectTypeDes);
    	$("#deptDivide").val(projJson.deptDivide);
    }
    //
    $("#startingReportForm").styleFit();




	var showAbandonedRecord=function () {
		var param="?projId="+$("#projId").val()+"&businessId="+$("#wrId").val()+"&stepId=1701";

		$('body').cgetPopup({//加载弹屏
			title: '审核记录',
			content: '#dataTableInfo/abandonedRecordPopPageDetail'+param,
			accept: function () {},
			size:'large'
		});
	}

    function showAbandonedReason(data) {
		if (data=="0"){//未通过
			$(".abandonedReason").removeClass("hidden");
		}else {
			$(".abandonedReason").addClass("hidden");
		}
	}



    var startReportDetail = function(){
    	var url = 'startingReport/startReportDetail';
    	var projId = getProjectInfo().projId;
    	var data={"id":projId};
    	var f = $("#startingReportForm");
    	 $.ajax({
             type: 'POST',
             url: url,
             data: data,
             dataType: 'json',
             success: function (data) {
                 //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
                 var selects = f.find('select[disabled]');
                 selects.removeAttr("disabled");
                 //表单反序列化填充值
                 f.deserialize(fixNull(data));
                 //有disabled属性的下拉菜单元素对象重新添加禁用属性
                 selects.attr("disabled","disabled");
                 $("#plannedEndDateOld").val(data.plannedEndDate);
                 detailCallBack(data);
                 $("#constructionUnit").val(getProjectInfo().cuName);
                 $("#contractType").val(getProjectInfo().contractType);
                 $("#deptDivide").val(getProjectInfo().deptDivide);
                 if($("#contractType").val()=='11'){
                 	 //显示民用的 隐藏公建、改管、干线
                     $(".changeType,.trunkType,.publicType").addClass("hidden");
                 	 $(".civilType").removeClass("hidden");
                 }else if($("#contractType").val()=='21'){
                 	 //显示公建的 隐藏民用、改管、干线
                 	 $(".changeType,.civilType,.trunkType").addClass("hidden");
                 	 $(".publicType").removeClass("hidden");
                 }else if($("#contractType").val()=='31'){
                 	 //显示改管的 隐藏民用、公建、干线
                 	 $(".publicType,.civilType,.trunkType").addClass("hidden");
                 	 $(".changeType").removeClass("hidden");
                 }else{
                 	 //显示干线的 隐藏民用、公建、改管
                 	 $(".publicType,.civilType,.changeType").addClass("hidden");
                 	 $(".trunkType").removeClass("hidden");
                 }
                 
                 if($("#conNo").val()==""){
                	 $(".conNo").addClass("hidden");
                 }else{
                	 $(".conNo").removeClass("hidden");
                 }
                 //客服中心可以隐藏监理
             	if($("#deptDivide").val()==$("#customerServiceCenter").val()){
             		$(".suUnit").addClass("hidden");
             	}else{
             		$(".suUnit").removeClass("hidden");
             	}

             },
             error: function (jqXHR, textStatus, errorThrown) {
                 console.warn("cgetDetail() -> 详情查询失败");
             }
         });
    };
    var timeLimitFunc = function(){
		  //验证工期
		  	if($("#timeLimit").val()!='' && checkNumber($("#timeLimit").val())){
		  		var plannedEndDateOld = $("#plannedEndDateOld").val();
		  		//已有竣工日期
		  		if(!checkDateFormat(plannedEndDateOld)){
		  			var cwDate = $("#cwDate").val();
		  			//自动计算竣工日期
		  			if(cwDate!=''){
		  				$("#plannedEndDate").val(format(addDateFunc(timestamp(cwDate),$("#timeLimit").val())));
		  			}
		  		} else{
		  			$("#plannedEndDate").val(plannedEndDateOld);
		  		} 
		  	}else{
		  		$("#plannedEndDate").val($("#timeLimit").val());
		  	}
	  }
    var detailCallBack = function(data){
    	if(data.wrId !== "" && data.wrId!== null){
    		//将按钮隐藏掉，表单不可改
    		$("#startingReportForm").toggleEditState(false).styleFit();
    		$(".saveBtn").addClass("hidden");
    		$(".updataBtn").removeClass("hidden");
    		$("#wrId").val(data.wrId);
        	//验证工期如果不是数字，则工期和计划竣工日期可编辑
        	checkScPlanTotalDay();
    	}else{
    	    //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"startingReportForm");

    		$(".saveBtn").removeClass("hidden");
    		$(".updataBtn").addClass("hidden");
    		$("#wrId").val("");
    		//验证工期如果不是数字，则工期和计划竣工日期可编辑
        	checkScPlanTotalDay();
    	}
    	showReport(data);
    	queryCheckRole();
    	//如果计划竣工日期是时间戳，则转为日期格式
    	/* if(data.plannedEndDate!='' && checkNumber(data.plannedEndDate)){
    		$("#plannedEndDate").val(format(data.plannedEndDate));
    	} */ 

    }
    /**
     * 验证工期不为数字，则可修改调整
     */
    var checkScPlanTotalDay = function(wrId){
    	//验证工期如果为数字，则可编辑工期 和计划竣工日期
    	var timeLimit=$("#timeLimit").val();
    	if(timeLimit!='' && checkNumber(timeLimit)){
    		$("#timeLimit").attr("readonly",true);
    		$("#timeLimit").attr("disabled","disabled");
    		//$("#plannedEndDate").attr("readonly",true);
    		//$("#plannedEndDate").attr("disabled","disabled");
    	}else{
    		$("#timeLimit").attr("readonly",false);
    		$("#timeLimit").removeAttr("disabled");
    		//($"#plannedEndDate").attr("readonly",false);
    		//$("#plannedEndDate").removeAttr("disabled");
    	}
    	timeLimitFunc();
    	};
//    $("#startingReportForm").toggleEditState().styleFit();

    startReportDetail();
    //日期datepicker
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });

    //日期datepicker
   $.getScript('assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js').done(function() {
     	$('.datetimepicker-default').datetimepicker({
        	todayHighlight: true,
        	language:  'cn',
        	todayBtn:  1
         });
      });
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6,#signBtn_7,#signBtn_8");
    	signatures.handleSignature();
    	if($("#signBtn_9")){
    		$("#signBtn_9").handleSignature();
    	}
    });
    
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}

    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>    
    //加载打印预览
    var showReport = function(data){
    	var projName = encodeURIComponent(cjkEncode($("#projName").val())),
    	projScaleDes = encodeURIComponent(cjkEncode($("#projScaleDes").val())),
    	projAddr = encodeURIComponent(cjkEncode($("#projAddr").val())),
    	suName = encodeURIComponent(cjkEncode($("#suName").val())),
    	corpName = encodeURIComponent(cjkEncode($("#corpName").val())),
    	custName = encodeURIComponent(cjkEncode($("#custName").val())),
    	constructionUnit = encodeURIComponent(cjkEncode($("#constructionUnit").val()));
    	wrId=$("#wrId").val();
    	var cptUrl = data.cptUrl;
    	if(cptUrl==''|| cptUrl == undefined){
    		cptUrl = 'startingReport.cpt';
    	}
    	src="<%=basePath%>/ReportServer?reportlet=constructmanage/"+cptUrl+"&projName="+projName;
    	src=src+"&projAddr="+projAddr+"&suName="+suName+"&constructionUnit="+constructionUnit+"&custName="+custName+"&corpName="+corpName;
    	src=src+"&wrId="+wrId+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src); 
    };
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
    //点击确认按钮
	  $(".saveBtn").on("click",function(){
		  if($("#startingReportForm").parsley().isValid()){
			  
			  //先判断是否进行会审交底和施工组织
			  if($("#signState").val()=="0"){
				  alertInfo("请先完成会审交底和施工组织，再进行开工报告！");
          		  return false;
			  }
			  
			  
	    	  //要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
	    	  //$("#startingReportForm").cformSave('projectCostTable',saveProjectBack,true);
              $(".saveBtn").parent().parent().loadMask("正在保存...", 1, 0.5);
              setTimeout("",3000);
              
              
             	 //判断是否全签字
              if($("#deptDivide").val()==$("#customerServiceCenter").val()){
            	  if($("#projectLeader").val()!="" && $("#fieldRepresent").val()!="" && $("#cuPm").val()!="" && $("#builder").val()!="" && $("#constructionQc").val()!="" && $("#safetyOfficer").val()!="" ){
     	        	  //项目负责人 现场代表 总监 监理 项目经理 施工员 安全员 质检员
    	        	  $("#signState").val("1");
   	        	  }else{
   	        		  $("#signState").val("0");
   	        	  }
              }else{
            	  if($("#projectLeader").val()!="" && $("#fieldRepresent").val()!="" && $("#suCes").val()!="" && $("#suJgj").val()!="" && $("#cuPm").val()!="" && $("#builder").val()!="" && $("#constructionQc").val()!="" && $("#safetyOfficer").val()!="" ){
   	        		//项目负责人 现场代表 总监 监理 项目经理 施工员 安全员 质检员
   	        	      $("#signState").val("1");
  	        	  }else{
  	        		  $("#signState").val("0");
  	        	  }
              }
	    		var data=$("#startingReportForm").serializeJson();
	    		var sign=[];//页面显示的签字
	        	$.each(data.sign,function(i,e){
	        		if(!$("#"+e.fieldName).parent().parent().is(":hidden")){
	        			sign = sign.concat(e);
	        		}
	        	});
	        	data.sign = sign;
	        	//alert(data.timeLimit);
	        	data.timeLimit=$("#timeLimit").val();
  	            console.info(data);
	    		$.ajax({
	                type: 'POST',
	                url: 'startingReport/saveWorkReport',
	                contentType: 'application/json;charset=UTF-8',
	                data: JSON.stringify(data),
	                success: function (data) {
                        $(".saveBtn").parent().parent().hideMask();
	                	var content = "数据保存成功！";
	                	if(data === "false"){
	                		content = "数据保存失败！";
	                	}else if(data === "already"){
	                		console.info("---------------------");
		               		content = "此信息已被修改，请刷新页面！";
		               	}else{
	                		startReportDetail();
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
	                    console.warn("开工报告保存失败！");
	                }
	            });
	    		$("#startingReportForm").toggleEditState(false);
	    		$(".saveBtn").addClass("hidden");
	    		$(".updataBtn").removeClass("hidden");
	    		//如果通过验证, 则移除验证UI
	        	$("#startingReportForm").parsley().reset();
			  }else{
		  		//如果未通过验证, 则加载验证UI
		      	$("#startingReportForm").parsley().validate();
		  	}
	    		
	    });
//     点击修改按钮
	 $(".updataBtn").on("click",function(){
		 $(".updataBtn").addClass("hidden");
		 $(".saveBtn").removeClass("hidden");

         //根据职务过滤可编辑项
         getSignStatusByPostId(getProjectInfo().post,"startingReportForm");
         if($("#timeLimit").val()!='' && checkNumber($("#timeLimit").val())){
     		$("#timeLimit").attr("readonly",true);
     		$("#timeLimit").attr("disabled","disabled");
     	}else{
     		$("#timeLimit").attr("readonly",false);
     		$("#timeLimit").removeAttr("disabled");
     	}
//		 $("#startingReportForm").toggleEditState(true);
		 
		//施工或竣工中
	    /* 	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
	    		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
	    		$("#startingReportForm").find(".sign-data-input").toggleSign(true);
	    	}else{
	    		//结算中或资料交接中
	    		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
	    		$("#startingReportForm").find(".sign-data-input").toggleSign(false);
	    	} */
	    	//验证工期如果不是数字，则工期和计划竣工日期可编辑
	        //checkScPlanTotalDay($("#wrId").val());


         if ($('#loginPost').val().indexOf($('#dataAdmin').val())!=-1){//数据处理员
             $(".allText").attr("readonly",false);
         }
	    });
	
	  $('[name="dataType"]').on("change",function(){
		if($(this).val()=="2"){
			//$("#startingReportForm")[0].reset();//清空表单
			$("#startingReportForm input").val("");
			startReportDetail();
		}else{
			$("#startingReportForm input").val("");
			startReportDetail();
		}
	});
	  
	  
	  var sureClose=function(){
			var cwId=$("#wrId").val();
			$.ajax({
				type:'post',
				url:'startingReport/saveSignNotice',
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
	  //工期事件
	  $("#timeLimit").on("change",function(){
		  //验证工期
		  	if($("#timeLimit").val()!='' && checkNumber($("#timeLimit").val())){
		  		//实际开工日期
		  		var plannedStartDate = $("#plannedStartDate").val();
		  		//自动计算竣工日期
		  		if(plannedStartDate!=''){
		  			if(plannedStartDate.indexOf('-')>0){
		  				$("#plannedEndDate").val(format(addDateFunc(timestamp(plannedStartDate),$("#timeLimit").val())));
		  			}else{
		  				$("#plannedEndDate").val(format(addDateFunc(plannedStartDate,$("#timeLimit").val())));
		  			}
		  		}
		  	}else{
		  		$("#plannedEndDate").val($("#timeLimit").val());
		  	}
	  })	
	
	  //showReport();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->