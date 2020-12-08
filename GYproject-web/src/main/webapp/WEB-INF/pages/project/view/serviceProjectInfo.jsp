<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="clearboth form-box">
		<div class="form-group col-md-6 ">
			<form class="form-horizontal" id="service_projectForm">
				<h3>工程信息</h3>
				<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					<label class="control-label" for="">工程编号</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="projNo"/>
					</div>
				</div>
				<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					<label class="control-label" for="">工程状态</label>
					<div>
						<select class="form-control input-sm field-editable" id="area" name="projStatusId">
							<option value=""></option>
							<c:forEach var="enums" items="${projStatus}">
								<option value="${enums.value}">${enums.message}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					<label class="control-label" for="">工程名称</label>
					<div>
						<input type="text" class="form-control input-sm field-editable" name="projName"/>
					</div>
				</div>
				<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					<label class="control-label" for="">工程地点</label>
					<div>
						<input type="text" class="form-control input-sm field-editable" name="projAddr"/>
					</div>
				</div>
				<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" />
		        </div>
		    </div>
        	<div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    		 <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value=""/>        
		        </div>
		    </div>
				
				<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					<label class="control-label" for="">受理来源</label>
					<div>
						<select class="form-control input-sm field-editable"  name="projSource">
							<option value=""></option>
							<c:forEach var="enums" items="${projSource}">
								<option value="${enums.value}">${enums.message}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					<label class="control-label" for="">受理日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable" name="acceptDate"/>
					</div>
				</div>
				<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					<label class="control-label" for="">受理人</label>
					<div>
						<input type="text" class="form-control input-sm field-editable" name="accepter"/>
					</div>
				</div>
				<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					<label class="control-label" for="">联系电话</label>
					<div>
						<input type="text" class="form-control input-sm field-editable" name="accepterPhone"/>
					</div>
				</div>
				<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
					<label class="control-label" for="">规模描述</label>
					<div>
						<textarea class="form-control field-editable" name="projScaleDes" rows="4" cols="" ></textarea>
					</div>
				</div>
				<div class="form-group col-lg-6 col-md-12 col-sm-6 backReason hidden">
				        <label class="control-label" for="">退单原因</label>
				    	<div>
				    		<select class="form-control input-sm field-editable " id="backReason"  name="backReason"  >
				 		      	<option></option>
				                <c:forEach var="enums" items="${backReason}">
									   	<option value="${enums.value}">${enums.message}</option>
				                </c:forEach>
				             </select>
				        </div>
				    </div>
				     <div class="form-group col-lg-6 col-md-12 col-sm-6  backRemarks hidden">
				        <label class="control-label" for="">退单备注</label>
				    	<div>
				    		<input type="text" class="form-control input-sm field-editable a2" id="backRemarks"  name="backRemarks"  data-parsley-maxlength="200" />
				 		      	
				        </div>
				    </div>
				    <!--#########################################################  -->
				<div class="form-group col-lg-12 col-md-12 col-sm-6">
						  <h4>客户信息：</h4>
					</div>
				<div class="form-group col-lg-6 col-md-12 col-sm-12 ">
					<label class="control-label" for="">申请单位</label>
					<div>
						<input type="text" class="form-control input-sm field-editable" name="custName"/>
					</div>
				</div>
				<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					<label class="control-label" for="">联系人</label>
					<div>
						<input type="text" class="form-control input-sm field-editable" name="custContact"/>
					</div>
				</div>
				<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					<label class="control-label" for="">联系电话</label>
					<div>
						<input type="text" class="form-control input-sm field-editable" name="custPhone"/>
					</div>
				</div>
				<!--#########################################################  -->
				<div class="form-group col-lg-12 col-md-12 col-sm-6">
						  <h4>安装合同信息：</h4>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout contractAmount display-authority cuDisValide suDisValide duDisValide">
						<label class="control-label" for="">合同金额</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="contractAmount"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">合同经办人</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="operator"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">签订日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="signDate"/>
						</div>
					</div>
					<!--#########################################################  -->
				<div class="form-group col-lg-12 col-md-12 col-sm-6">
						  <h4>施工合同信息：</h4>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout contractAmount display-authority cuDisValide suDisValide duDisValide">
						<label class="control-label" for="">合同金额</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="contractAmount"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">合同经办人</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="operator"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">签订日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="signDate"/>
						</div>
					</div>
					
					<!--#########################################################  -->
					<div class="form-group col-lg-12 col-md-12 col-sm-6">
						  <h4>施工信息：</h4>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">计划开工日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="startDate"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">计划竣工日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable timestamp" id="planCompleteDate" name="planCompleteDate"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">实际开工日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="acStartDate"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">实际竣工日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="completedDate"/>
						</div>
					</div>
					<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
						<label class="control-label" for="">联合验收日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="jointAcceptanceDate"/>
						</div>
					</div>
					<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
						<label class="control-label" for="">验收结果</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="acceptanceResult"/>
						</div>
					</div>
					<!--#########################################################  -->
					<div class="form-group col-lg-12 col-md-12 col-sm-6">
						  <h4>结算信息：</h4>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">结算日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="settlementDate"/>
						</div>
					</div>
					
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout endSettlementCost display-authority duDisValide">
						<label class="control-label" for="">结算审定价</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="endSettlementCost"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout govSettlementCost display-authority cuDisValide suDisValide duDisValide">
						<label class="control-label" for="">政府结算审定价</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="govSettlementCost"/>
						</div>
					</div>
			  </form>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<h3>接口信息</h3>
				<form class="form-horizontal" id="service_projectForm1">
						
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">接口类型</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="serviceType"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">接口类型描述</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="serviceTypeDes"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">返回编码</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="resultType"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">返回信息</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="resultMsg"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">传递数据</label>
						<div>
							<textarea class="form-control field-editable" name="logParams" rows="8" cols="" ></textarea>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">调用日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="logTime"/>
						</div>
					</div>
					
				</form>
			</div>
				
	</div>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
	//隐藏loading
	$(".infodetails").hideMask();
	//参数传false时，表单不可编辑
	$("#service_projectForm,#service_projectForm1").toggleEditState().styleFit();
	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->