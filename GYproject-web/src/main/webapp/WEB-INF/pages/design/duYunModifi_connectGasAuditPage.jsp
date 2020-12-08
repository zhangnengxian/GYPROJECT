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
                   <h4 class="panel-title">现场踏勘内容</h4>
                </div>
                <div class="panel-body" >
                    <div class="clearboth form-box m-t-40">
                    <input type="hidden" name="stepId" value="${stepId}"/>
                    	<form class="form-horizontal" id="connectGasAuditForm" action="">
                    		<input type="hidden"  name="contractType" id="contractType" value="${surveyInfo.contractType}"/>
                    		<input type="hidden" name="corpId" id="corpId" value="${surveyInfo.corpId}"/>
							<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
		    					<label class="control-label" for="">工程编号</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-editable"  id="projNo" name="projNo" value="${surveyInfo.projNo}"/>
						        </div>
						    </div>
						    <div class="form-group col-lg-12 col-md-12 col-sm-6 ">
						        <label class="control-label" for="">工程名称</label>
						        <div>
						            <input type="text" class="form-control input-sm field-editable"  id="projName" name="projName"  value="${surveyInfo.projName}"/>
						        </div>
						    </div>
						    <div class="form-group col-lg-12 col-md-12 col-sm-6 ">
						        <label class="control-label" for="">工程地点</label>
						        <div>
						            <input type="text" class="form-control input-sm field-editable"  id="projAddr" name="projAddr"  value="${surveyInfo.projAddr}"/>
						        </div>
						    </div>
						    <div class="form-group col-lg-12 col-md-12 col-sm-6 ">
						        <label class="control-label" for="projScaleDes">工程规模</label>
						        <div>
						        	<textarea class="form-control field-editable" name="projScaleDes" id="projScaleDes"   rows="3" cols=""  >${surveyInfo.projScaleDes}</textarea>
						        </div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
						    	<label class="control-label" for="corpName">燃气公司</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"   value="${surveyInfo.corpName}"/>
						        </div>
						    </div>
						   <div class="form-group col-md-6 col-sm-12">
						        <label class="control-label" for="">工程类型</label>
						    	<div>
						    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value="${surveyInfo.projLtypeDes}"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
						        <label class="control-label" for="">出资方式</label>
						    	<div>
						    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value="${surveyInfo.contributionModeDes}"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
						        <label class="control-label" for="">业务部门</label>
						    	<div>
						    		 <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value="${surveyInfo.deptName}"/>        
						        </div>
						    </div>
				          	 <!--  客户信息 -->
						    <div class="form-group col-md-12 noUser">
						        <label class="control-label" for="custName">客户名称</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value="${surveyInfo.custName}"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 noUser">
						        <label class="control-label" for="custContact">联系人</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" value="${surveyInfo.custContact}"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 noUser">
						        <label class="control-label" for="custPhone">联系电话</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" value="${surveyInfo.custPhone}"/>
						        </div>
						    </div>
						   	<!--  勘察信息 -->
						    <div class="form-group col-md-6">
						        <label class="control-label" for="surveyer">勘察人</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-not-editable" id="surveyer"  name="surveyer" value="${surveyInfo.surveyer}"/>
						        </div>
						    </div>
						     <div class="form-group col-md-6 ">
						        <label class="control-label" for="">设计人</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-not-editable" id="designer" name="designer" value="${surveyInfo.designer}"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6">
						        <label class="control-label" >勘察日期</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="surveyDate"  name="surveyDate" value="${surveyInfo.surveyDate}"/>
						        </div>
						    </div>
						    <div class="form-group col-md-12">
						     	<label class="control-label " for="surveyDes">勘查内容</label>
						     	<div> 
						        	<textarea class="form-control field-editable surveyContent" name="surveyDes" id="surveyDes" rows="2" cols="" data-parsley-maxlength="500" >${surveyInfo.surveyDes}</textarea></div>
						    </div>
						    <input type="hidden"  name="installContent" id="installContent" value="${surveyInfo.installContent}"/>
				            <div class="form-group col-md-12 col-sm-12 ">
						        <label class="control-label" for="">报装内容</label>
						    	<div>
						    		<label>
							        	<input type="checkbox"  name="installContentCheck" class="field-editable surveyContent"  value="0"/>大锅灶
							        	<input type="checkbox"  name="installContentCheck" class="field-editable surveyContent"  value="1"/>壁挂炉
							        	<input type="checkbox"  name="installContentCheck" class="field-editable surveyContent"  value="2"/>锅炉
							        	<input type="checkbox"  name="installContentCheck" class="field-editable surveyContent"  value="4"/>生产设备
							        	<input type="checkbox"  name="installContentCheck" class="field-editable surveyContent"  value="5"/>其他设备
							        	<input type="checkbox"  name="installContentCheck" class="field-editable surveyContent"  value="6"/>预留管
						        	</label>
						        </div>
						    </div>
						    <input type="hidden" class=" form-control input-sm field-editable " id="gasContent"   value="${surveyInfo.gasContent}" />
						    <div class="form-group col-md-6 col-sm-12">
					             <label class="control-label" for="">用气情况</label>
					             <div>
					                 <label>
					                     <input type="radio" class="field-editable surveyContent" name="gasContent" value="1" />增量
					                 </label>
					                 <label>
					                     <input type="radio" class="field-editable surveyContent" name="gasContent" value="0" />存量
					                 </label>
					             </div>
				        	 </div>
				        	 <div class="form-group col-md-6 isBack ">
						        <label class="control-label" for="billingType">开票类型</label>
						    	<div>
						    		<select class="form-control input-sm field-editable surveyContent" id="billingType"  name="billingType"  >
						 		      	<option value="0">增值税普通发票</option>
										<option value="1" >增值税专用发票</option>
						             </select>
						        </div>
						    </div>
				        	 <div class="form-group col-md-6 clearboth">
				                 <label class="control-label" for="customerSituation">现使用能源</label>
				                 <div>
				                    <input type="text" class=" form-control input-sm field-editable surveyContent " id="customerSituation"  name="customerSituation" data-parsley-maxlength="20" value="${surveyInfo.customerSituation}"  >
				                 </div>
				             </div>
				             <div class="form-group col-md-6 ">
				                 <label class="control-label" for="energyContent">能耗情况</label>
				                 <div>
				                    <input type="text" class=" form-control input-sm field-editable surveyContent " id="energyContent"  name="energyContent" data-parsley-maxlength="20" value="${surveyInfo.energyContent}" >
				                 </div>
				             </div>
				        	 
				             <div class="form-group col-md-6 ">
				                 <label class="control-label" for="gasSourceUse">使用气源</label>
				                 <div>
				                    <input type="text" class=" form-control input-sm field-editable surveyContent " id="gasSourceUse"  name="gasSourceUse" data-parsley-maxlength="20" value="${surveyInfo.gasSourceUse}">
				                 </div>
				             </div>
							<div class="form-group col-md-6 ">
				                 <label class="control-label" for="withGasFrequency">带气次数</label>
				                 <div>
				                    <input type="text" class=" form-control input-sm field-editable surveyContent " id="withGasFrequency"  name="withGasFrequency" data-parsley-maxlength="50" value="${surveyInfo.withGasFrequency}">
				                 </div>
				             </div>
				             <input type="hidden" class=" form-control input-sm field-editable " id="isElectronicData"   value="${surveyInfo.isElectronicData}" />
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">电子资料</label>
								<div>
									<label>
										<input type="radio" class="field-editable surveyContent" name="isElectronicData" value="1" />有
									</label>
									<label>
										<input type="radio" class="field-editable surveyContent" name="isElectronicData" value="0" />没有
									</label>
								</div>
							</div>
							<input type="hidden" class=" form-control input-sm field-editable " id="isNewBuild"   value="${surveyInfo.isNewBuild}" />
				             <div class="form-group col-md-6 col-sm-12">
					             <label class="control-label" for="">建调压设施</label>
					             <div>
					                 <label>
					                     <input type="radio" class="field-editable surveyContent" name="isNewBuild" value="1" />是
					                 </label>
					                 <label>
					                     <input type="radio" class="field-editable surveyContent" name="isNewBuild" value="0" />否
					                 </label>
					             </div>
				        	 </div>
				        	 <input type="hidden" class=" form-control input-sm field-editable " id="approachCondition"   value="${surveyInfo.approachCondition}" />
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">主体工程完工</label>
								<div>
									<label>
										<input type="radio" class="field-editable surveyContent" name="approachCondition" value="1" />已完工
									</label>
									<label>
										<input type="radio" class="field-editable surveyContent" name="approachCondition" value="0" />未完工
									</label>
								</div>
							</div>
							<input type="hidden" class=" form-control input-sm field-editable " id="installNums"   value="${surveyInfo.installNums}" />
							<div class="form-group col-md-6 col-sm-12">
				                 <label class="control-label" for="">提资单</label>
				                 <div>
				                     <label>
				                         <input type="radio" class="field-editable surveyContent" name="installNums" value="1" />有
				                     </label>
				                     <label>
				                         <input type="radio" class="field-editable surveyContent" name="installNums" value="0" />无
				                     </label>
				                 </div>
				             </div>
				             <input type="hidden" class=" form-control input-sm field-editable " id="installaRequirements"   value="${surveyInfo.installaRequirements}" />
				             <div class="form-group col-md-6 col-sm-12">
				                 <label class="control-label" for="">安装要求</label>
				                 <div>
				                     <label>
				                         <input type="radio" class="field-editable surveyContent" name="installaRequirements" value="1" />已达到
				                     </label>
				                     <label>
				                         <input type="radio" class="field-editable surveyContent" name="installaRequirements" value="0" />未达到
				                     </label>
				                 </div>
				             </div>
				             <input type="hidden" class=" form-control input-sm field-editable " id="municipalRoad"   value="${surveyInfo.municipalRoad}" />
							<div class="form-group col-md-6 col-sm-12 clearboth">
				                 <label class="control-label" for="">是否市政破路</label>
				                 <div>
				                     <label>
				                         <input type="radio" class="field-editable surveyContent" name="municipalRoad" value="1" />是
				                     </label>
				                     <label>
				                         <input type="radio" class="field-editable surveyContent" name="municipalRoad" value="0" />否
				                     </label>
				                 </div>
				             </div>
				             <div class="form-group col-md-6">
								<label class="control-label" for="trunkLength">破路长度</label>
								<div>
									<input type="text" class="form-control input-sm field-editable surveyContent"  id="trunkLength" name="trunkLength" data-parsley-maxlength="20"  value="${surveyInfo.trunkLength}"/>
								</div>
							</div>
							<input type="hidden" class=" form-control input-sm field-editable " id="isTooLong"   value="${surveyInfo.isTooLong}" />
				             <div class="form-group col-md-6 col-sm-12">
				                 <label class="control-label" for="">是否超长</label>
				                 <div>
				                     <label>
				                         <input type="radio" class="field-editable surveyContent" name="isTooLong" value="1" />是
				                     </label>
				                     <label>
				                         <input type="radio" class="field-editable surveyContent" name="isTooLong" value="0" />否
				                     </label>
				                 </div>
				             </div>
				             <div class="form-group col-md-6">
								<label class="control-label" for="exceedLong">超长米数</label>
								<div>
									<input type="text" class="form-control input-sm field-editable surveyContent"  id="exceedLong" name="exceedLong" data-parsley-maxlength="20"  value="${surveyInfo.exceedLong}" />
								</div>
							</div>
							<input type="hidden" class=" form-control input-sm field-editable " id="highOverhead"   value="${surveyInfo.highOverhead}" />
							<div class="form-group col-md-6 col-sm-12">
				                 <label class="control-label" for="">高位架空</label>
				                 <div>
				                     <label>
				                         <input type="radio" class="field-editable surveyContent" name="highOverhead" value="1" />是
				                     </label>
				                     <label>
				                         <input type="radio" class="field-editable surveyContent" name="highOverhead" value="0" />否
				                     </label>
				                 </div>
				             </div>
				             <div class="form-group col-md-6">
								<label class="control-label" for="highOverheadMeters">高度及长度</label>
								<div>
									<input type="text" class="form-control input-sm field-editable surveyContent"  id="highOverheadMeters" name="highOverheadMeters" data-parsley-maxlength="20"  value="${surveyInfo.highOverheadMeters}"/>
								</div>
							</div>
							
							<div class="form-group col-md-6">
								<label class="control-label" for="conTimeLimit">施工工期</label>
								<div>
									<input type="text" class="form-control input-sm field-editable surveyContent"  id="conTimeLimit" name="conTimeLimit" data-parsley-maxlength="20"  value="${surveyInfo.conTimeLimit}"/>
								</div>
							</div>
				
						    <div class="form-group col-md-6 ">
				                 <label class="control-label">计划用气时间</label>
				                 <div>
				                    <input type="text" class=" form-control input-sm field-editable datepicker-default surveyContent" id="estimateDeliverDate"  name="estimateDeliverDate"  value="${surveyInfo.estimateDeliverDate}">
				                 </div>
				             </div>
						    <div class="form-group col-md-12">
						     	<label class="control-label " for="connectGasDes">踏勘人员意见</label>
						     	<div> 
						        	<textarea class="form-control field-editable surveyContent" name="connectGasDes" id="connectGasDes" rows="2" cols="" data-parsley-maxlength="500" >${surveyInfo.connectGasDes}</textarea></div>
						    </div>
						    <div class="form-group col-md-12">
						     	<label class="control-label " for="technicalSuggestion">设计人员意见</label>
						     	<div> 
						        	<textarea class="form-control field-editable designContent" name="technicalSuggestion" id="technicalSuggestion" rows="2" cols="" data-parsley-maxlength="500" >${surveyInfo.technicalSuggestion} </textarea></div>
						    </div>
						<div class="form-group col-md-6 col-sm-12 allSign designer">
							<label class="control-label signature-tool sign-require" for="duDeputy">设计人员</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="duDeputy" name="duDeputy" value="">
								<input type="hidden" class="signPost" id="duDeputy_postType" name="duDeputy_postType" value="${duDeputy}" >
								<img class="duDeputy" alt="" src="${surveyInfo.duDeputy}" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-12 allSign designer">
							<label class="control-label signature-tool sign-require" for="engineering">工程部人员</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="engineering" name="engineering" value="">
								<input type="hidden" class="signPost" id="engineering_postType" name="engineering_postType" value="${engineering }" >
								<img class="duDeputy" alt="" src="${surveyInfo.engineering}" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
						</div>
			
						<div class="form-group col-md-6 col-sm-12 allSign designer">
							<label class="control-label signature-tool sign-require" for="market">输配中心人员</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="market" name="market" value="">
								<input type="hidden" class="signPost" id="market_postType" name="market_postType" value="${market }" >
								<img class="duDeputy" alt="" src="${surveyInfo.market}" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
						</div>	
						</form>
						<div class="clearboth form-box  photoBox">
							<div class="form-group width-full attach-images-list" id="projectImagesList">
							     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
							     <ul class="row">
							     </ul>
							</div>
						</div>
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
			    		<form class="form-horizontal" id="connectGasAuditRightForm" action="connectGasAudit/auditSave">
			    			<input type="hidden" id="projId" name = "projId" value = "${surveyInfo.projId}"/>
			    			<input type="hidden"  name = "projNo" value = "${surveyInfo.projNo}"/>
                    		<input type="hidden" id="surveyId" name = "surveyId" value = "${surveyInfo.surveyId}"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" value = "${surveyInfo.surveyId}"/>
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
    App.setPageTitle('现场踏勘审核 - 工程管理系统');
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
    
    $.getScript('projectjs/design/connect_gas_audit_page.js').done(function () {
        gasAuditHistory.init();
	});
    
    //放弃
    $(".auditCancelBtn").off("click").on("click",function(){
		$("#ajax-content").cgetContent("connectGasAudit/main");
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
    	
        var checkBox =$("#installContent").val();
        if(checkBox!='' && checkBox!=null){
        	var checkBoxArray = checkBox.split(",");
        	for(var i=0;i<checkBoxArray.length;i++){
        		$("input[name='installContentCheck']").each(function(){
        			if($(this).val()==checkBoxArray[i]){
        				$(this).attr("checked","checked");
        			}
        		})
        	}	
        }
	   	 //用气情况
	   	 var gasContent=$("#gasContent").val();
	   	 $("input[name='gasContent'][value="+gasContent+"]").attr("checked","checked");
	   	 
	   	 //电子资料
	   	 var isElectronicData=$("#isElectronicData").val();
	   	 $("input[name='isElectronicData'][value="+isElectronicData+"]").attr("checked","checked");
	   	 
	   	 //建调压设施
	   	 var isNewBuild=$("#isNewBuild").val();installaRequirements
	   	 $("input[name='isNewBuild'][value="+isNewBuild+"]").attr("checked","checked");
	   	 
	   	 //主体工程完工
	     var approachCondition=$("#approachCondition").val();
	   	 $("input[name='approachCondition'][value="+approachCondition+"]").attr("checked","checked");
	   	 
	   	 
	   	 //提资单
	   	 var installNums=$("#installNums").val();
	   	 $("input[name='installNums'][value="+installNums+"]").attr("checked","checked");
	   	 
	   	 
	   	 //安装要求
	   	  var installaRequirements=$("#installaRequirements").val();
	   	 $("input[name='installaRequirements'][value="+installaRequirements+"]").attr("checked","checked");
	   	 
	   	 //是否市政破路
	   	 var municipalRoad=$("#municipalRoad").val();
	   	 $("input[name='municipalRoad'][value="+municipalRoad+"]").attr("checked","checked");
	   	 
	   	 //是否超长
	   	 var isTooLong=$("#isTooLong").val();
	   	 $("input[name='isTooLong'][value="+isTooLong+"]").attr("checked","checked");
	   	
	   	
	   	 //高位架空
	   	 var highOverhead=$("#highOverhead").val();
	   	 $("input[name='highOverhead'][value="+highOverhead+"]").attr("checked","checked");
	   	
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