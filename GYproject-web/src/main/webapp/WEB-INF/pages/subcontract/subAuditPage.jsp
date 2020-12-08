<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css"
	rel="stylesheet" />
<link
	href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css"
	rel="stylesheet" />
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
		<div class="col-sm-6 col-xs-12">
			<!-- begin panel -->
			<input type="hidden" name="projId" id="projId" value="${projId}" />
			<input type="hidden" name="subAmount" id="subAmount" value="${subAmount}" />
			<div class="panel panel-inverse tabs-mixin">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-default"
							data-click="panel-expand"><i class="fa fa-expand"></i></a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-success"
							data-click="panel-reload"><i class="fa fa-repeat"></i></a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-warning"
							data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<ul class="nav nav-tabs">
						<li class="active"><a href="#contractAudit_panelBox" id=""
							data-toggle="tab">合同信息</a></li>
						<li class=""><a href="#sub_safe_audit" id=""
							data-toggle="tab">安全协议</a></li>
						<li class=""><a href="#contractAudit_quantities" id=""
							data-toggle="tab">工程量信息</a></li>
					</ul>
				</div>

				<div class="panel-body">
					<div class="tab-content">
						<div class="tab-pane fade in btn-top active"
							id="contractAudit_panelBox">
							<!-- 合同详述 -->
						</div>
						<div class="tab-pane fade in btn-top"
							id="sub_safe_audit">
							<!-- 安全协议 -->
						</div>
						<div class="tab-pane fade in btn-top"
							id="contractAudit_quantities">
							<!-- 工程量详述 -->
							<div class="mask-html text-center">
								<div>
									<i class="fa fa-3x fa-spinner"></i><br>
									<p class="text-ellipsis">加载中</p>
								</div>
							</div>
							<table id="qualitiesListTable"
								class="table table-striped table-bordered nowrap" width="100%">
								<thead>
									<tr>
										<th>分部分项名称</th>
										<th>单位</th>
										<th>单价</th>
										<th>数量</th>
										<th>金额</th>
									</tr>
								</thead>
								 <tfoot>
						      		 <tr>
						      		    
						      			<td>合计</td>
						      			<td class="total-amount"></td>
						      			<td class="total-amount"></td>
						      			<td class="total-amount"></td>
						      			<td class="total-amount"></td>
						      		</tr>
	      						</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- begin col-6 -->
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" id="content">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-default"
							data-click="panel-expand"><i class="fa fa-expand"></i></a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-success"
							data-click="panel-reload"><i class="fa fa-repeat"></i></a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-warning"
							data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">审批区</h4>
				</div>
				<div class="panel-body" id="contract_audit_panel_box">
					<div class="toolBtn f-r m-b-15  editbtn">
						<a href="javascript:;"
							class="btn btn-confirm btn-sm m-l-5 auditSaveBtn">保存</a> <a
							href="javascript:;"
							class="btn btn-warn btn-sm m-l-5 auditCancelBtn">放弃</a>
					</div>
					<div class="clearboth form-box auditFormDiv">
						<form class="form-horizontal" id="contractAuditRightForm"
							action="contractAudit/auditSave">
							<input type="hidden" class="isAudit" id="isAudit" name="isAudit"
								value="${ isAudit}" /> <input type="hidden" class="projId"
								name="projId" value="" /> <input type="hidden" class="projNo"
								name="projNo" value="" /> <input type="hidden" class="scId"
								name="scId" value="${ scId}" /> <input type="hidden"
								id="businessOrderId" name="businessOrderId" value="${ scId}" />
							<input type="hidden" class="currentLevel" name="mrAuditLevel"
								value="${ currentLevel}" />
							<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
								<label class="control-label" for="">审批结果</label>
								<div>
									<c:forEach var="enums" items="${mrResult}">
										<label> <input type="radio" name="mrResult"
											value="${enums.value}" />${enums.message}
										</label>
									</c:forEach>
								</div>
							</div>
							<div class="form-group col-lg-12 col-md-12 col-sm-12">
								<label class="control-label" for="">审批意见</label>
								<div>
									<textarea class="form-control field-editable" name="mrAopinion"
										id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200"></textarea>
								</div>
							</div>
							<div class="form-group col-lg-6 col-md-12 col-sm-6">
								<label class="control-label" for="">审批人</label>
								<div>
									<input type="hidden" id="mrCsr" name="mrCsr"
										data-parsley-maxlength="100" value="${loginInfo.staffId}">
									<input type="text"
										class=" form-control input-sm field-not-editable" id=""
										name="" data-parsley-maxlength="100"
										value="${loginInfo.staffName}">
								</div>
							</div>
							<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
								<label class="control-label" for="">审批日期</label>
								<div>
									<input type="text"
										class=" form-control input-sm field-not-editable timestamp all"
										id="mrTime" value="${auditTime}" name="mrTime"
										data-parsley-required="true" data-parsley-maxlength="100">
								</div>
							</div>
						</form>
					</div>
					<div>
						<h4 class="m-t-15 m-l-7">
							<strong>审批历史</strong>
						</h4>
					</div>
					<table id="auditHistoryTable"
						class="table table-striped table-bordered nowrap" width="100%">
						<thead>
							<tr>
								<th>审批日期</th>
								<th>审批结果</th>
								<th>审批意见</th>
								<th>审批人</th>
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
	App.setPageTitle('接气方案审核 - 工程管理系统');
	$("#contractAuditForm").styleFit();
	$("#contractAuditRightForm").styleFit();

	//表单不可编辑
	$("#contractAuditForm,#contractAuditRightForm").toggleEditState(false);
	$("#contractAuditRightForm").toggleEditState(true);

	$('.datepicker-default').datepicker({
		todayHighlight : true
	});

	$.getScript('projectjs/contract/sub-audit-page.js').done(function() {
		auditHistory.init();
	});

	$.getScript('projectjs/contract/subqualities-audit-page.js').done(
			function() {
				qualitiesJudgement.init();
			});

	//放弃
	$(".auditCancelBtn").off("click").on("click", function() {
		$("#ajax-content").cgetContent("subContract/audit");
	});

	$("#mrTime").change();

	//保存
	$(".auditSaveBtn").off("click").on("click",function() {
						var val = $(
								'#contractAuditRightForm input:radio[name="mrResult"]:checked')
								.val();
						if (val == null) {
							alertInfo("请选择审批结果！");
						} else {
							$(".projId").val($("#projId").val());
							$(".projNo").val($("#projNo").val());
							//$("#contractAuditRightForm").cformSave("auditHistoryTable",auditSaveCallBack);
							if ($("#contractAuditRightForm").parsley().isValid()) {
								var data = $("#contractAuditRightForm").serializeJson();
								$.ajax({
											type : 'POST',
											url : 'subContract/auditSave',
											contentType : "application/json;charset=UTF-8",
											data : JSON.stringify(data),
						                    beforeSend: function () {
						        	              // 禁用按钮防止重复提交
						                          $(".auditSaveBtn").attr({ disabled: "disabled" });
						        	        },
						                    complete: function () {
						                    	//去掉禁用
						                    	 $(".auditSaveBtn").removeAttr("disabled");
						                    },
											success : function(data) {
												var content = "数据保存成功！";
												if (data === "false") {
													content = "数据保存失败！";
													alertInfo(content);
												} else if (data === "true") {
													$(".auditSaveBtn").addClass("hidden");
													$(".auditCancelBtn").text("返回");
													$("#contractAuditRightForm").toggleEditState(false);
													$(".auditFormDiv").addClass("hidden");
													$("#auditHistoryTable").cgetData(false); //列表重新加载
													alertInfo(content);
												} else {
													//弹出收付流水确认屏   完成
													var url = "#constructContract/accrualsRecordPopPage?arId="+ data;
													var popoptions = {
														title : '应收流水',
														content : url,
														chide : true,
														nocache : true,
														accept : accrualsRecordDone
													};
													//加载弹屏
													$("body").cgetPopup(popoptions);
												}
											},
											error : function(jqXHR, textStatus,errorThrown) {
												console.warn("合同签订区记录保存失败！");
											}
										});
								//如果通过验证, 则移除验证UI
								$("#contractAuditRightForm").parsley().reset();
							} else {
								//如果未通过验证, 则加载验证UI
								$("#contractAuditRightForm").parsley()
										.validate();
							}
						}
					});

	var auditSaveCallBack = function(data) {
		if (data === "true") {
			$(".auditSaveBtn").addClass("hidden");
			$(".auditCancelBtn").text("返回");
			$("#contractAuditRightForm").toggleEditState(false);
			$(".auditFormDiv").addClass("hidden");
		}
	}

	var accrualsRecordDone = function() {
		$(".auditSaveBtn").addClass("hidden");
		$(".auditCancelBtn").text("返回");
		$("#contractAuditRightForm").toggleEditState(false);
		$(".auditFormDiv").addClass("hidden");
		$("#auditHistoryTable").cgetData(false); //列表重新加载
		alertInfo('合同审核完成！');
	}

	var isAuditFunction = function() {
		var isAudit = $("#isAudit").val();
		if (isAudit === "1") {
			//审核过
			auditSaveCallBack("true");
		}
	}();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->