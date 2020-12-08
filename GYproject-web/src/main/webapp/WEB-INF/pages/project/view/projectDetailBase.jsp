<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="clearboth form-box">
		<div class="form-group col-md-6 ">
			<form class="form-horizontal" id="detail_projectForm">
				<h3><a href="javascript:;" class="btn btn-sm btn-primary pull-right view-location hidden" style="margin-top: -5px;"><i class="fa fa-map-marker"></i> 项目位置</a>基本信息</h3>
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
				
				
				
				<%-- <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					<label class="control-label" for="">区域</label>
					<div>
						<select class="form-control input-sm field-editable" id="area" name="area">
							<option value=""></option>
							<c:forEach var="enums" items="${area}">
								<option value="${enums.value}">${enums.message}</option>
							</c:forEach>
						</select>
					</div>
				</div> --%>
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
				<div class="form-group col-lg-6 col-md-12 col-sm-6 acceptAuditDate">
					<label class="control-label" for="">受理审核日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable timestamp" id="acceptAuditDate" name="acceptAuditDate"/>
					</div>
				</div>
				<div class="form-group col-md-6 col-sm-12 isBidding">
					<label class="control-label" for="">是否老小区</label>
					<div>
						<label>
							<input type="radio" class="field-editable" name="isBidding" value="1" />是
						</label>
						<label>
							<input type="radio" class="field-editable" name="isBidding" value="0" checked/>否
						</label>
					</div>
				</div>
				<!-- <div class="form-group col-lg-6 col-md-12 col-sm-6">
		        	<label class="control-label" for="paNo">受理单号</label>
		        	<div>
		        		<input type="text" class="form-control input-sm field-not-editable" id="paNo" name="paNo"/>
		        	</div>
      			</div> -->
				<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
					<label class="control-label" for="">规模描述</label>
					<div>
						<textarea class="form-control field-editable" name="projScaleDes" rows="4" cols="" ></textarea>
					</div>
				</div>
				<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
					<label class="control-label" for="">受理备注</label>
					<div>
						<textarea class="form-control field-editable" name="projectRemark" rows="4" cols="" ></textarea>
					</div>
				</div>
				<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
					<label class="control-label" for="">工程备注</label>
					<div>
						<textarea class="form-control field-editable" name="remark" rows="4" cols="" ></textarea>
					</div>
				</div>
			  </form>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<h3>业务信息</h3>
				<form class="form-horizontal" id="detail_projectForm1">
						<!-- <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
		            		<label class="control-label" for="designStation">设计所</label>
		            		<div >
		                		<input type="text" class="form-control input-sm field-editable"  name="designStation" value="" data-parsley-required="true"/>
		            		</div>
	        	    	</div>
			        	<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
			            	<label class="control-label" for="designStationDirector">负责人</label>
			            	<div >
			                	<input type="text" class="form-control input-sm field-editable"  name="designStationDirector"/>
			            	</div>
			        	</div> -->
			        <div class="form-group" >
						  <h4>踏勘信息：</h4>
					</div>
			        <div class="form-group col-lg-6 col-md-6 col-sm-6 ">
						<label class="control-label" for="">踏勘派工日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable timestamp" id="surveyDisDate"  name="surveyDisDate"/>
						</div>
					</div>	
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">勘察人</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="surveyer"/>
						</div>
					</div>
					 <div class="form-group col-md-12">
		     	      <label class="control-label " for="surveyDes">踏勘结果</label>
		     	     <div> 
		        	<textarea class="form-control field-not-editable" name="surveyDes" id="surveyDes" rows="4" cols="" data-parsley-maxlength="500" ></textarea></div>
		          </div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">勘察日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="surveyDate"/>
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
					<!-- <div class="form-group col-lg-12 col-md-12 col-sm-6 ">
						<label class="control-label" for="">方案反馈</label>
						<div>
							<textarea class="form-control field-editable" name="surveyFeedBack" rows="4" cols="" ></textarea>
						</div>
					</div> -->
					
					<div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth raiseMoneyApplyDate amountAbout hidden">
						<label class="control-label" for="">提资申请日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable timestamp "  id="raiseMoneyApplyDate" name="raiseMoneyApplyDate"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 raiseMoneyApplyDate hidden amountAbout">
						<label class="control-label" for="">审核完成日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable timestamp " id="raiseMoneyAuditDate" name="raiseMoneyAuditDate"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 raiseMoneyApplyDate hidden amountAbout">
						<label class="control-label" for="">用户回复日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="raiseMoneyResponseDate"/>
						</div>
					</div>
					<div class="form-group col-lg-12 col-md-12 col-sm-6">
						  <h4>设计信息：</h4>
					</div>
					 
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">设计人</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="designer"/>
						</div>
					</div>
					 <div class="form-group col-md-12">
		     	      <label class="control-label" for="technicalSuggestion">技术建议</label>
		     	     <div> 
		        	   <textarea class="form-control  field-editable" name="technicalSuggestion" id="technicalSuggestion" rows="4" cols="" data-parsley-maxlength="500" ></textarea>
	        	      </div>
		              </div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">开始日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="ocoDate"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">计划日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable datepicker-default timestamp" id="duPlCompleteDate" name="duPlCompleteDate"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">实际日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="duCompleteDate"/>
						</div>
					</div>
					<div class="form-group col-lg-12 col-md-12 col-sm-6">
						  <h4>其他信息：</h4>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">工程预算员</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="budgeter"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">预算日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="budgetDate"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout budgetTotalCost display-authority cuDisValide suDisValide duDisValide">
						<label class="control-label" for="">工程总造价</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="budgetTotalCost"/>
						</div>
					</div>
					<div class="form-group col-lg-12 col-md-12 col-sm-12 amountAbout budgetTotalCost display-authority cuDisValide suDisValide duDisValide">
						<label class="control-label" for="">预算备注</label>
						<div>
							<textarea class="form-control input-sm field-editable " rows="2" name="budgetMethodReMark"></textarea>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout confirmTotalCost display-authority cuDisValide suDisValide duDisValide">
						<label class="control-label" for="">确定总造价</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="confirmTotalCost"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 display-authority cuDisValide suDisValide duDisValide">
						<label class="control-label" for="">变动原因</label>
						<div>
							<select class="form-control input-sm field-editable"  name="changeReason">
								<option value=""></option>
								<c:forEach var="enums" items="${changeReason}">
									<option value="${enums.value}">${enums.message}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout contractAmount display-authority cuDisValide suDisValide duDisValide">
						<label class="control-label" for="">合同金额</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="contractAmount"/>
						</div>
					</div>
					
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">造价确认人</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="costMember"/>
						</div>
					</div>
					<%-- <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">价格形式</label>
						<div>
							<select class="form-control input-sm field-editable"  name="contractPricingType">
								<option value=""></option>
								<c:forEach var="enums" items="${contractPricingType}">
									<option value="${enums.value}">${enums.message}</option>
								</c:forEach>
							</select>
						</div>
					</div> --%>
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
					<div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
						<label class="control-label" for="">分包预算员</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="suBudgeter"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">预算日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable datepicker-default timestamp" id="suBudgeterDate" name="suBudgeterDate"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
						<label class="control-label" for="">分包预算审核人</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="budgeterAudit"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">审核日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable datepicker-default timestamp" id="budgeterAuditDate" name="budgeterAuditDate"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">验收负责人</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="acceptanceDirector"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">验收日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="acceptanceDate"/>
						</div>
					</div>
					<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
						<label class="control-label" for="">验收结果</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="acceptanceResult"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">结算人</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="settlementer"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">结算日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="settlementDate"/>
						</div>
					</div>
					<!--########################################################################  -->
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout budgetSettlementCost display-authority  suDisValide duDisValide">
						<label class="control-label" for="">分包预算价</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="budgetSettlementCost"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout budgetCost display-authority  suDisValide duDisValide">
						<label class="control-label" for="">分包预算审定价</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="budgetCost"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout sendSettlementCost display-authority  suDisValide duDisValide">
						<label class="control-label" for="">结算报审价</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="sendSettlementCost"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout endSettlementCost display-authority duDisValide">
						<label class="control-label" for="">结算审定价</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="endSettlementCost"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout govBudgetCost display-authority cuDisValide suDisValide duDisValide">
						<label class="control-label" for="">政府预算审定价</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="govBudgetCost"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 amountAbout govSettlementCost display-authority cuDisValide suDisValide duDisValide">
						<label class="control-label" for="">政府结算审定价</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="govSettlementCost"/>
						</div>
					</div>
						<div class="form-group col-lg-12 col-md-12 col-sm-6">
						  <h4>施工信息：</h4>
					</div>
					<!--#########################################################  -->
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
	$("#detail_projectForm,#detail_projectForm1").toggleEditState().styleFit();
	
	$(".view-location").off("click").on("click", function(){
		$("body").cgetPopup({
         	title: "项目位置",
         	content: '#projectView/projectLocation',
         	accept: innerClose,
         	chide: true,
         	icon: "fa-map-marker",
         	nocache: true,
         	size: "super",
         	callback: 'initProjLocation'
		});
	});
	//隐藏配置属性
	if($("#hiddenConfig").val()!='' && $("#hiddenConfig").val()!=undefined &&  $("#hiddenConfig").val()!=null ){
		$("."+$("#hiddenConfig").val()).addClass("hidden");
	}
	
	if($("#staffRemoveClass").val()!='' && $("#staffRemoveClass").val()!=undefined &&  $("#staffRemoveClass").val()!=null ){
		$("."+$("#staffRemoveClass").val()).removeClass("hidden");
	}
</script>
<!-- ================== END PAGE LEVEL JS ================== -->