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
				<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					<label class="control-label" for="">区域</label>
					<div>
						<select class="form-control input-sm field-editable" id="area" name="area">
							<option value=""></option>
							<c:forEach var="enums" items="${area}">
								<option value="${enums.value}">${enums.message}</option>
							</c:forEach>
						</select>
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
				<div class="form-group col-lg-6 col-md-12 col-sm-6">
		        	<label class="control-label" for="paNo">受理单号</label>
		        	<div>
		        		<input type="text" class="form-control input-sm field-not-editable" id="paNo" name="paNo"/>
		        	</div>
      			</div>
				<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
					<label class="control-label" for="">规模描述</label>
					<div>
						<textarea class="form-control field-editable" name="projScaleDes" rows="4" cols="" ></textarea>
					</div>
				</div>
				
				<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
					<label class="control-label" for="">备注</label>
					<div>
						<textarea class="form-control field-editable" name="remark" rows="4" cols="" ></textarea>
					</div>
				</div>
			  </form>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<h3>业务信息</h3>
				<form class="form-horizontal" id="detail_projectForm1">
						<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
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
			        	</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">勘察人</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="surveyer"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">勘察日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="surveyDate"/>
						</div>
					</div>
					
					<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
						<label class="control-label" for="">接气方案反馈 </label>
						<div>
							<textarea class="form-control field-editable" name="surveyFeedBack" rows="4" cols="" ></textarea>
						</div>
					</div>
					
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">设计人</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="designer"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">设计完成日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="duCompleteDate"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">预算员</label>
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
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">工程总造价</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="budgetTotalCost"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">确定总造价</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="confirmTotalCost"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
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
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">合同金额</label>
						<div>
							<input type="text" class="form-control input-sm field-editable fixed-number" name="contractAmount"/>
						</div>
					</div>
					
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">造价员</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="costMember"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">价格形式</label>
						<div>
							<select class="form-control input-sm field-editable"  name="contractPricingType">
								<option value=""></option>
								<c:forEach var="enums" items="${contractPricingType}">
									<option value="${enums.value}">${enums.message}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">经办人</label>
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
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">开工日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="startDate"/>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">竣工日期1</label>
						<div>
							<input type="text" class="form-control input-sm field-editable" name="completedDate"/>
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
         	content: '#/projTS/projectLocation',
         	accept: innerClose,
         	chide: true,
         	icon: "fa-map-marker",
         	nocache: true,
         	size: "super",
         	callback: 'initProjLocation'
		});
	});
	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->