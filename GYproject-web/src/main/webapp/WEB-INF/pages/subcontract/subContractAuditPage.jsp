<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
		<div class="col-sm-6 col-xs-12">
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">施工合同内容</h4>
				</div>
				<div class="panel-body" >
					<div class="clearboth form-box m-t-40">
						<input type="hidden" name="stepId" value="${stepId}"/>
						<form class="form-horizontal" id="connectGasAuditForm" action="">
							<input type="hidden" id="projId" name="projId" value="${data.PROJ_ID}"/>
							<input type="hidden" id="scId" name="scId" value="${data.SC_ID}"/>
							<input type="hidden" id="flag" name="flag"/>
							<input type="hidden" id="projLtypeId" name="projLtypeId"/>
							<div class="form-group col-md-6 ">
								<label class="control-label" for="scNo">合同编号</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo"  data-parsley-required="true" value="${data.SC_NO}"/>
								</div>
							</div>
							<div class="form-group  col-md-6 ">
								<label class="control-label" for="projNo">工程编号</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value="${data.PROJ_NO}"/>
								</div>
							</div>
							<div class="form-group col-md-12 ">
								<label class="control-label" for="projName">工程名称</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value="${data.PROJ_NAME}"/>
								</div>
							</div>
							<div class="form-group col-md-12">
								<label class="control-label" for="projAddr">工程地点</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value="${data.PROJ_ADDR}"/>
								</div>
							</div>
							<div class="form-group col-md-12">
								<label class="control-label" for="projScaleDes">工程规模</label>
								<div>
									<textarea class="form-control input-sm field-not-editable" name="projScaleDes" id = "projScaleDes" rows="" cols="" data-parsley-maxlength="200">${data.PROJ_SCALE_DES}</textarea>
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="corpName">燃气公司</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName" value="${data.CORP_NAME}" data-parsley-maxlength="100" />
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">工程类型</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value="${data.PROJECT_TYPE_DES}"/>
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">出资方式</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value="${data.CONTRIBUTION_MODE_DES}"/>
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">业务部门</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="departmentName" name="departmentName" value="${data.DEPT_NAMES}"/>
								</div>
							</div>
							<div class="form-group col-md-12">
								<label class="control-label" for="deptName">发包人</label>
								<div>
									<input type="hidden"  id="deptId" name="deptId" data-parsley-maxlength="20"  value="" />
									<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" data-parsley-maxlength="50"  value="${data.DEPT_NAME}" />
								</div>
							</div>
							<div class="form-group col-md-6">
								<!-- 委托代表改为"委托代理人" -->
								<label class="control-label" for="projCompDirector">委托代理人</label>
								<div>
									<input type="text" class="form-control input-sm field-editable"  id="projCompDirector" name="projCompDirector" data-parsley-maxlength="20" value="${data.PROJ_COMP_DIRECTOR}"/>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="control-label" for="gasComLegalRepresent">甲方现场代表</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="gasComLegalRepresent" name="gasComLegalRepresent" data-parsley-maxlength="20"  value="${data.GAS_COM_LEGAL_REPRESENT}"/>
								</div>
							</div>
							<div class="form-group col-md-12">
								<!-- 乙方改为承包人 -->
								<label class="control-label" for="cuName">承包人</label>
								<div>
									<input type="hidden" id="cuId" name="cuId" data-parsley-maxlength="100"  value="" />
									<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" data-parsley-maxlength="200"  value="${data.CU_NAME}" />
								</div>
							</div>
							<div class="form-group col-md-6 ">
								<!-- 乙方委托代表改为委托代理人 -->
								<label class="control-label" for="cuDirector">委托代理人</label>
								<div>
									<input type="text" class="form-control input-sm field-editable"  id="cuDirector" name="cuDirector" data-parsley-maxlength="20" value="${data.CU_DIRECTOR}"/>
								</div>
							</div>
							<div class="form-group col-md-6 ">
								<label class="control-label" for="cuPm">项目经理</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="cuPm" name="cuPm" data-parsley-maxlength="20" value="${data.CU_PM}"/>
								</div>
							</div>
							<div class="form-group col-md-6 clearboth">
								<label class="control-label" for="contractMethod">建筑服务</label>
								<div>
									<select class="form-control input-sm field-editable" id="contractMethod"  name="contractMethod"  data-parsley-required="true">
										<c:forEach var="enums" items="${subContractMethod}">
											<c:if test="${data.CONTRACT_METHOD eq enums.value}"><option value="${enums.value}" >${enums.message}</option></c:if>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group col-md-6">
								<!-- 现场负责人改为施工员 -->
								<label class="control-label" for="cuResponsiblePerson">施工员</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="cuResponsiblePerson" name="cuResponsiblePerson" data-parsley-maxlength="20" value="${data.CU_RESPONSIBLE_PERSON}"/>
								</div>
							</div>
							<div class="form-group col-md-12 hidden artery">
								<label class="control-label" for="contractMode">承包方式</label>
								<div>
									<input type="text" class="form-control input-sm field-editable"  id="contractMode" name="contractMode" data-parsley-maxlength="200" value=""/>
								</div>
							</div>
							<div class="form-group col-md-12">
								<!-- 承包范围改为工程内容 -->
								<label class="control-label" for="scScope">工程内容</label>
								<div>
									<textarea class="form-control field-editable" name ="scScope" id="scScope" rows="3" data-parsley-maxlength="200">${data.SC_SCOPE}</textarea>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="control-label" for="increment">税率</label>
								<div>
									<input type="text" class="form-control input-sm field-editable"  id="increment" name="increment" data-parsley-maxlength="200" value="${data.INCREMENT}"/>
									<a href="javascript:;" class="btn btn-sm btn-default">%</a>
								</div>
							</div>
							<div class="form-group col-md-6 clearboth">
								<label class="control-label" >开工日期</label>
								<div>
									<input type="text" class=" form-control input-sm field-not-editable datepicker-default timestamp" id="scPlannedStartDate"  name="scPlannedStartDate" value="${data.SC_PLANNED_START_DATE}">
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="control-label" >竣工日期</label>
								<div>
									<input type="text" class=" form-control input-sm field-not-editable datepicker-default timestamp" id="scPlannedEndDate"  name="scPlannedEndDate" value="${data.SC_PLANNED_END_DATE}" data-parsley-required="true">
								</div>
							</div>
							<%--<div class="form-group col-md-6 payRate hidden">
								<label class="control-label" for="payType">预付款比例</label>
								<div>
									<input type="number" class="form-control input-sm field-editable" id="payRate" name="payRate" value="${data.PAY_RATE}"/>
									<a href="javascript:;" class="btn btn-sm btn-default">%</a>
								</div>
							</div>
							<div class="form-group col-md-6">
								<!-- 承包方式改为合同价款方式 -->
								<label class="control-label" for="scType">合同价款方式</label>
								<div>
									<select class="form-control input-sm field-editable" id="scType" name="scType">
										<option value=""></option>
									</select>
								</div>
							</div>
							<div class="form-group col-md-6">
								<!-- 承包方式改为合同价款方式 -->
								<label class="control-label" for="progressType">进度款方式</label>
								<div>
									<select class="form-control input-sm field-editable" id="progressType"  name="progressType">
										<option value="" ></option>
									</select>
								</div>
							</div>--%>
							<div class="form-group col-md-6 hidden">
								<label class="control-label" for="quAmount">工程造价</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="quAmount" name="quAmount" data-parsley-maxlength="16" value=""/>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="control-label" for="scAmount">合同金额</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="scAmount" name="scAmount"  data-parsley-maxlength="16" value="${data.SC_AMOUNT}"  data-parsley-required="true"/>
								</div>
							</div>
							<div class="form-group col-md-6 hidden">
								<label class="control-label" >交底日期</label>
								<div>
									<input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="scSignDate" name="scSignDate" value="" data-parsley-required="true">
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="control-label" >签订日期</label>
								<div>
									<input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="operateDate" name="operateDate" value="${data.OPERATE_DATE}" data-parsley-required="true">
								</div>
							</div>
							<input type="hidden" class=" form-control input-sm field-editable " id="isNewBuild" value="" />
							<input type="hidden" class=" form-control input-sm field-editable " id="isElectronicData"   value="" />
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- begin col-6 -->
		<div class="col-sm-6 col-xs-12" >
			<div class="panel panel-inverse" id="content">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">确认区</h4>
				</div>
				<div class="panel-body" id="connect_gas_audit_panel_box">
					<div class="toolBtn f-r m-b-15  editbtn">
						<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSaveBtn" >保存</a>
						<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 auditCancelBtn">放弃</a>
					</div>
					<div class="clearboth form-box auditFormDiv">
						<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
						<form class="form-horizontal" id="connectGasAuditRightForm" action="subContractAudit/auditSave">
							<input type="hidden" id="projId" name = "projId" value = "${data.PROJ_ID}"/>
							<input type="hidden"  name = "projNo" value = "${data.PROJ_NO}"/>
							<input type="hidden" id="surveyId" name = "surveyId" value = "${data.SC_ID}"/>
							<input type="hidden" id="businessOrderId" name = "businessOrderId" value = "${data.SC_ID}"/>
							<input type="hidden" name = "mrAuditLevel" value = "${currentLevel}"/>
							<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
								<label class="control-label" for="">确认结果</label>
								<div>
									<c:forEach var="enums" items="${mrResult}">
										<label>
											<input type="radio" name="mrResult" value="${enums.value}"/> ${enums.message}
										</label>
									</c:forEach>
								</div>
							</div>
							<div class="form-group col-lg-12 col-md-12 col-sm-12">
								<label class="control-label" for="">确认意见</label>
								<div>
									<textarea class="form-control field-editable"  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
							</div>
							<div class="form-group col-lg-6 col-md-12 col-sm-6">
								<label class="control-label" for="">确认人</label>
								<div>
									<input type="hidden"  id="mrCsr"  name="mrCsr" data-parsley-maxlength="100" value="${loginInfo.staffId}">
									<input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
								</div>
							</div>
							<div class="form-group col-lg-6 col-md-12 col-sm-6 " >
								<label class="control-label" for="">确认日期</label>
								<div>
									<input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" data-parsley-maxlength="100" >
								</div>
							</div>
						</form>
					</div>
					<div >
						<h4 class="m-t-15 m-l-7"><strong>确认历史</strong></h4>
					</div>
					<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
						<thead>
						<tr>
							<th>确认日期</th>
							<th>确认结果</th>
							<th>确认意见</th>
							<th>确认人</th>
						</tr>
						</thead>
					</table>
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
    App.setPageTitle('施工合同审核 - 工程管理系统');
    $("#connectGasAuditForm").styleFit();
    $("#connectGasAuditRightForm").styleFit();

    //表单不可编辑
    $("#connectGasAuditForm,#connectGasAuditRightForm").toggleEditState(false);
    $("#connectGasAuditRightForm").toggleEditState(true);



    //当前日期
    $("#mrTime").change();

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    $.getScript('projectjs/subcontract/sub_contract_audit_page.js').done(function () {
        gasAuditHistory.init();
    });

    //放弃
    $(".auditCancelBtn").off("click").on("click",function(){
        $("#ajax-content").cgetContent("subContractAudit/main");
    });

    //保存
    $(".auditSaveBtn").off("click").on("click",function(){
        var val=$('#connectGasAuditRightForm input:radio[name="mrResult"]:checked').val();
        if(val==null){
            alertInfo("请选择确认结果！");
        }else{
            //$("#mrTime").val(timestamp($("#mrTime").val()));
            $("#connectGasAuditRightForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".auditSaveBtn"));
        }
    });

    var auditSaveCallBack = function(data){
        if(data === "true"){
            $(".auditSaveBtn").addClass("hidden");
            $(".auditCancelBtn").text("返回");
            $("#connectGasAuditRightForm").toggleEditState(false);
            $(".auditFormDiv").addClass("hidden");
        }
    }
    var isAuditFunction = function(){
        var isAudit = $("#isAudit").val();
        if(isAudit === "1"){
            //审核过
            auditSaveCallBack("true");
        }


        var isNewBuild=$("#isNewBuild").val();
        $("input[name='isNewBuild'][value="+isNewBuild+"]").attr("checked","checked");
        var isElectronicData=$("#isElectronicData").val();
        $("input[name='isElectronicData'][value="+isElectronicData+"]").attr("checked","checked");
        var approachCondition=$("#approachCondition").val();
        $("input[name='approachCondition'][value="+approachCondition+"]").attr("checked","checked");



        if($("#contractType").val()=='11'){
            //显示民用的 隐藏公建、改管、干线
            $(".changeType,.trunkType").addClass("hidden");
            $(".civilType,.publicType").removeClass("hidden");
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
            $(".publicType,.civilType").addClass("hidden");
            $(".changeType,.trunkType").removeClass("hidden");
        }
        if($("#custName").val()==""){
            $(".noUser").addClass("hidden");
        }else{
            $(".noUser").removeClass("hidden");
        }

        setTimeout(function(){
            $("#projectImagesList").getImagesList({
                "projId": $("#projId").val(),
                "stepId": $("#stepId").val(),
                "projNo": $("#projNo").val(),
                "busRecordId": $("#surveyId").val() || '-1'
            });
        },300);
    }();

</script>
<!-- ================== END PAGE LEVEL JS ================== -->