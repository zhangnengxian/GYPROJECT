<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- altimetricSurvey.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
 <link href="assets/plugins/jedate/skin/jedate.css" rel="stylesheet" />

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse tabs-mixin">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <input type="hidden" id="actionName" value="${actionName }"/>
                    <input type="hidden" id="pcIdNew" name="pcIdNew">
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表</a></li>
		                <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
		                <li class="" id="addTab"><a href="#default-tab-2" id="signTab"  data-toggle="tab">签字区</a></li>
                	</ul>
                </div>
               	<div class="panel-body">
               		<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
               			     <!--<input type="hidden" id="addShow"> -->
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="projectCheckListTable" width="100%">
	                			<thead>
		                			<tr>
		                				<th></th>
			                			<th>报验日期</th>
			                			<th>施工工序</th>
			                			<th>查验结果</th>
			                			<th>完成状态</th>
		                			</tr>
	                			</thead>
	                		</table>
	                	</div>
	                   	<div class="tab-pane fade btn-top" id="default-tab-2">
	                       	<div class="toolBtn f-r p-b-10 update-show">
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole savebtn" >保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">放弃</a>
						    </div>
						    <div class="clearboth form-box">
						    	<input type="hidden" class="imgUrl" value="${imgUrl}"/>
						    	<input type="hidden" class="projStatus" id="projStatus" value="${projStatus}"/>
						    	<input type="hidden" id="builderPost" value="${fieldsRepresentPost }"/>
						    	 <input type="hidden" id="loginPost" value="${loginPost }"/>
								 <input type="hidden" id="dataAdmin" value="${dataAdmin}">
								<form class="form-horizontal" id="signForm" data-parsley-validate="true" action="strengthTest/saveSign">							
								    <input type="hidden" id="pcDesId" name="pcDesId" value="${checkType}"/> 
									<input type="hidden" id="pcId" name="pcId" class="addClear"/>
									<input type="hidden" id="projId" name="projId" >
								 	<input type="hidden" id="recordsId" name="recordsId" >
								 	<input type="hidden" id="finishedDate" name="finishedDate">
									<input type="hidden" id="finishedOpr" name="finishedOpr">
									<input type="hidden" id="resetDate" name="resetDate">
									<input type="hidden" id="resetReason" name="resetReason">
									<input type="hidden" id="isFinishSign" name="isFinishSign">
									<input type="hidden" id="businessOrderId" name="businessOrderId">
								 	<!-- 完成标记 -->
									<input type="hidden" id="flag" name="flag">
								 	<input type="hidden" id="version" name="version">
								 	<input type="hidden" id="stPipeType1" name="stPipeType">
									<div class="form-group  col-sm-6">
										<label class="control-label" for="projNo">工程编号</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projNo" name="projNo"  />
										</div>
									</div>
									<div class="form-group  col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  id="projName" name="projName"  />
										</div>
									</div>
									<div class="form-group col-md-12 col-sm-12">
										<label class="control-label" for="projAddr">工程地点</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projAddr" name="projAddr"  />
										</div>
									</div>
									<div class="form-group  col-sm-6 otherPipe">
										<label class="control-label" for="stMedium">试验介质</label>
										<div>
											<input type="text" class="form-control  field-editable addClear input-sm allText construction" id="stMedium" name="stMedium" data-parsley-maxlength="100"/>
										</div>
									</div>
									<div class="form-group  col-sm-6 otherPipe">
										<label class="control-label" for="stRange">试验范围</label>
										<div>
											<input type="text" class="form-control field-editable addClear input-sm  construction" id="stRange1" name="stRange" stBuildingNo/>
										</div>
									</div>
									<div class="form-group  col-sm-6 indoorPipe ">
										<label class="control-label" for="stBuildingNo">楼栋号</label>
										<div>
											<input type="text" class="form-control field-editable addClear input-sm allText construction"  id="stBuildingNo" name="stBuildingNo" stBuildingNo/>
										</div>
									</div>
									<div class="form-group  col-sm-6 indoorPipe ">
										<label class="control-label" for="stHouseHolds">户数</label>
										<div>
											<input type="text" class="form-control field-editable addClear input-sm allText construction"  id="stHouseHolds" name="stHouseHolds" stBuildingNo/>
										</div>
									</div>
									<div class="form-group  col-sm-6 clearboth">
										<label class="control-label">报验日期</label>
										<div>
											<input type="text" class="form-control datepicker-default field-not-editable input-sm addClear allText inspectionDate "  id="inspectionDate" name="inspectionDate" />
										</div>
									</div>
									<div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear allText construction" name="inspectionResult" id="inspectionResult" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
									
									 <%--<div class="form-group  col-sm-12">--%>
										<%--<label class="control-label" for="corpName">建设单位</label>--%>
										<%--<div>--%>
											<%--<input type="text" class="form-control  field-not-editable input-sm"  id="corpName" name="corpName"  />--%>
										<%--</div>--%>
									<%--</div>--%>
									<%--<div class="form-group col-md-6 col-sm-12 indoorPipe otherPipe allSign builder">--%>
										<%--<label class="control-label signature-tool" for="fieldsRepresent">现场代表</label>--%>
										<%--<div>--%>
											<%--<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>--%>
											<%--<input type="hidden" class="sign-data-input" id="fieldsRepresent" name="fieldsRepresent" value="">--%>
											<%--<input type="hidden" class="signPost"  id="fieldsRepresent_postType" name="fieldsRepresent_postType" value="${fieldsRepresentPost}">--%>
											<%--<img class="fieldsRepresent" alt="" src="" style="height: 30px">--%>
											<%--<span class="clear-sign"><i class="fa ion-close-circled"></i></span>--%>
										<%--</div>--%>
						    		<%--</div>--%>
								    <%--<div class="form-group col-md-6 col-sm-12 hidden selectSignDate strengDate">--%>
										<%--<label class="control-label">签字日期</label>--%>
										<%--<div>--%>
											<%--<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText builder" value="" id="fieldsRepresentDate" name="fieldsRepresentDate"  />--%>
										<%--</div>--%>
									<%--</div>--%>
									<%--<div class="form-group  col-sm-12 suUnit">--%>
										<%--<label class="control-label" for="suName">监理单位</label>--%>
										<%--<div>--%>
											<%--<input type="text" class="form-control field-not-editable input-sm "  id="suName" name="suName"  />--%>
										<%--</div>--%>
									<%--</div>--%>
									<%--<div class="form-group col-md-6 col-sm-12 indoorPipe otherPipe allSign suJgj suUnit">--%>
										<%--<label class="control-label signature-tool" for="suJgj">现场监理</label>--%>
										<%--<div>--%>
											<%--<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_7"><i class="fa fa-pencil"></i></a>--%>
											<%--<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">--%>
											<%--<input type="hidden" class="signPost"  id="suJgj_postType" name="suJgj_postType" value="${suJgjPost}">--%>
											<%--<img class="suJgj" alt="" src="" style="height: 30px">--%>
											<%--<span class="clear-sign"><i class="fa ion-close-circled"></i></span>--%>
										<%--</div>--%>
						    		<%--</div>--%>
								    <%--<div class="form-group col-md-6 col-sm-12 hidden selectSignDate strengDate suUnit">--%>
										<%--<label class="control-label">签字日期</label>--%>
										<%--<div>--%>
											<%--<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText suJgj" value="" id="suJgjSignDate" name="suJgjSignDate"  />--%>
										<%--</div>--%>
									<%--</div>--%>
									
									<div class="form-group  col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 otherPipe allSign cuPm">
										<label class="control-label signature-tool" for="projectLeader">项目负责人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="projectLeader" name="projectLeader" value="">
											<input type="hidden" class="signPost"  id="projectLeader_postType" name="projectLeader_postType" value="${CU_PM}">
											<img class="projectLeader" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12  hidden selectSignDate strengDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText cuPm" value="" id="plSignDate" name="plSignDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 indoorPipe allSign cuPm">
										<label class="control-label signature-tool" for="cuPm">项目经理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="cuPm" name="cuPm" value="">
											<input type="hidden" class="signPost"  id="cuPm_postType" name="cuPm_postType" value="${CU_PM}">
											<img class="cuPm" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12 indoorPipe hidden selectSignDate strengDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText cuPm" value="" id="cuPmSignDate" name="cuPmSignDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 indoorPipe otherPipe allSign construction">
										<label class="control-label signature-tool" for="builder">施工员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
											<input type="hidden" class="signPost"  id="builder_postType" name="builder_postType" value="${builderPost}">
											<img class="builder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<div class="form-group col-md-6 col-sm-12 hidden selectSignDate strengDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText construction"  id="builderSignDate" name="builderSignDate"  />
										</div>
									</div> 
									<div class="form-group col-md-6 col-sm-12 otherPipe allSign qualitativeCheckMember">
										<label class="control-label signature-tool" for="constructionQc">质检员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQc" name="constructionQc" value="">
											<input type="hidden" class="signPost"  id="constructionQc_postType" name="constructionQc_postType" value="${constructionQcPost }">
											<img class="constructionQc" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<div class="form-group col-md-6 col-sm-12  hidden selectSignDate strengDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText qualitativeCheckMember"  id="cuQcSignDate" name="cuQcSignDate"  />
										</div>
									</div> 
									<div class="form-group col-md-6 col-sm-12 otherPipe allSign safetyOfficer">
										<label class="control-label signature-tool" for="safetyOfficer">安全员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="safetyOfficer" name="safetyOfficer" value="">
											<input type="hidden" class="signPost"  id="safetyOfficer_postType" name="safetyOfficer_postType" value="${saftyOfficerPost }">
											<img class="safetyOfficer" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<div class="form-group col-md-6 col-sm-12  hidden selectSignDate strengDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText safetyOfficer" id="soSignDate" name="soSignDate"  />
										</div>
									</div> 
								</form>
								<!-- 照片 -->
								<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list " id="projectImagesList">
									     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
									     <ul class="row">
									     </ul>
									</div>
								</div> 
							</div>
	                   	</div>
	                   	<div class="tab-pane fade  btn-top" id="default-tab-3">
	                   		<div class="toolBtn f-r p-b-10 auditEditBtn" >
	                   			<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole  hidden auditInpectBtn">报验</a>
	                   			<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole hidden  auditAddBtn2 ">新增</a>
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole  auditSavebtn2">保存</a>
						   		 <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole  deleteBtn2">删除</a>
						    </div>
						    <div class="clearboth form-box">
						    	<!-- 完成标记 -->
									<input type="hidden" id="flag1" class="addClear">
								 	
		                   		<form class="form-horizontal" id="auditForm"  action="">
		                   			<input type="hidden" id="stId" name="stId" class="addClear">
		                   			<input type="hidden" id="version" name="version" class="addClear">
		                   			<input type="hidden" id="recordPcId" name="pcId" class="addClear"/>
		                   			
									<input type="hidden" id="isFinishSign" name="isFinishSign" class="addClear">
		                   			<div class="form-group  col-sm-6 stPipeType">
										<label class="control-label" for="stPipeType">管道类型</label>
										<div>
											<select class="form-control field-editable input-sm  allText construction"  id="stPipeType" name="stPipeType" data-parsley-required="true" >
												 <c:forEach var="res" items="${StrTestPipeTypeEnum }">
													<option value="${res.value }" >${res.message }</option>
												</c:forEach> 
											</select>
											
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 indoorPipe">
										<label class="control-label" for="riserNo">立管</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="riserNo"   name="riserNo"   data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-xs-12 selfcheckformtitle"><i class="fa fa-angle-double-right"></i>强度试验</div>
									<div class="form-group col-md-12 col-sm-12 indoorPipe clearboth">
										<label class="control-label" for="stRange">试验范围</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="stRange"   name="stRange"   data-parsley-maxlength="400"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 clearboth otherPipe">
										<label class="control-label">试验日期</label>
										<div>
							                <input type="text" class="form-control datepicker-default input-sm field-editable addClear allText construction" id="stDate"   name="stDate" />
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 indoorPipe otherPipe">
										<label class="control-label" for="stPressure">试验压力</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="stPressure"   name="stPressure"   data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 otherPipe">
										<label class="control-label">稳压起始时间</label>
										<div>
							                <input type="text" class="form-control input-sm datetime-default field-editable addClear allText construction" id="stRegulatorStartTime"  name="stRegulatorStartTime"  />
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 otherPipe">
										<label class="control-label">稳压截止时间</label>
										<div>
							                <input type="text" class="form-control input-sm datetime-default field-editable addClear allText construction" id="stRegulatorEndTime"  name="stRegulatorEndTime" />
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 otherPipe">
										<label class="control-label" for="stInspectMethod">检测方法</label>
										<div>
											<label>
				       							<input type="radio" class="allText construction" name="stInspectMethod" value="1" checked>压差计
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="allText construction" name="stInspectMethod" value="0" >压力表
				      						 </label>
			   							</div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 otherPipe">
										<label class="control-label" for="stResult">试验结果</label>
										<div>
							                <input type="text" class="form-control input-sm field-editable addClear allText construction" id="stResult"   name="stResult"  data-parsley-maxlength="100"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 otherPipe hidden">
										<label class="control-label" for="stActualPressure">实际压力起</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="stActualPressure"   name="stActualPressure"  data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 otherPipe hidden">
										<label class="control-label" for="stActualPressureEnd">实际压力止</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="stActualPressureEnd"   name="stActualPressureEnd"  data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-12 col-sm-12 otherPipe hidden">
										<label class="control-label" for="stInstruction">试验说明</label>
										<div>
							                <textarea class="form-control input-sm field-editable addClear allText construction" id="stInstruction" rows="2"  name="stInstruction"  data-parsley-maxlength="200"></textarea>
							            </div>
									</div>
									<div class="form-group col-xs-12 selfcheckformtitle"><i class="fa fa-angle-double-right"></i>气密性试验</div>
									<div class="form-group col-md-6 col-sm-12 indoorPipe clearboth">
										<label class="control-label" for="gasTRange">试验范围</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="gasTRange"   name="gasTRange"   data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 clearboth otherPipe">
										<label class="control-label">试验日期</label>
										<div>
							                <input type="text" class="form-control datepicker-default input-sm field-editable addClear allText construction" id="gastDate"   name="gastDate"  />
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 indoorPipe otherPipe">
										<label class="control-label" for="gasTPressure">试验压力</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="gasTPressure"   name="gasTPressure"   data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 otherPipe">
										<label class="control-label">稳压起始时间</label>
										<div>
							                <input type="text" class="form-control input-sm datetime-default field-editable addClear allText construction" id="gastRegulatorStartTime"  name="gastRegulatorStartTime"  />
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 otherPipe">
										<label class="control-label">稳压截止时间</label>
										<div>
							                <input type="text" class="form-control input-sm datetime-default field-editable addClear allText construction" id="gastRegulatorEndTime"  name="gastRegulatorEndTime" />
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 otherPipe">
										<label class="control-label" for="gastInspectMethod">检测方法</label>
										<div>
											<label>
				       							<input type="radio" class="allText construction" name="gastInspectMethod" value="1" checked>压差计
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="allText construction" name="gastInspectMethod" value="0" >压力表
				      						 </label>
			   							</div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 otherPipe">
										<label class="control-label" for="gastResult">试验结果</label>
										<div>
							                <input type="text" class="form-control input-sm field-editable addClear allText construction" id="gastResult"   name="gastResult"  data-parsley-maxlength="100"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 otherPipe hidden">
										<label class="control-label" for="gastActualPressure">实际压力起</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="gastActualPressure"   name="gastActualPressure"  data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 otherPipe hidden">
										<label class="control-label" for="gastActualPressureEnd">实际压力止</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="gastActualPressureEnd"   name="gastActualPressureEnd"  data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 otherPipe">
										<label class="control-label" for="gasTtemperature">环境温度</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="gasTtemperature"   name="gasTtemperature"   data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 indoorPipe">
										<label class="control-label" for="testMethod">检测方法</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="testMethod"   name="testMethod"  data-parsley-maxlength="100"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 indoorPipe">
										<label class="control-label" for="testResult">试验结果</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction"  id="testResult"   name="testResult"  data-parsley-maxlength="100"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 indoorPipe">
										<label class="control-label">试验日期</label>
										<div>
							                <input type="text" class="form-control datepicker-default input-sm field-editable addClear allText construction" id="testDate"   name="testDate"  />
							            </div>
									</div>
									<div class="form-group col-md-12 col-sm-12 otherPipe hidden">
										<label class="control-label" for="gastInstruction">试验说明</label>
										<div>
							                <textarea class="form-control input-sm field-editable addClear allText construction" id="gastInstruction" rows="2"  name="gastInstruction"  data-parsley-maxlength="200"></textarea>
							            </div>
									</div>
									<!-- 庭院 -->
			                       	<div id="recordListTable1Div">
			                       	<table id="recordListTable1" class="table table-striped table-bordered nowrap " width="100%">
			                            <thead >
			                                <tr>
			                                    <th>操作</th>
			                                    <th>强度试验日期</th>
			                                    <th>强度试验压力</th>
			                                    <th>强度试验稳压起止时间</th>
			                                    <th>强度试验检测方法</th>
			                                    <th>强度试验试验结果</th>
			                                    <th>气密性试验日期</th>
			                                    <th>气密性试验压力</th>
			                                    <th>气密性试验稳压起止时间</th>
			                                    <th>气密性试验检测方法</th>
			                                    <th>气密性试验试验结果</th>
			                                     <th>气密性试验环境温度</th>
			                                    <th>说明</th>
			                                </tr>
			                            </thead>
			                            
			                       	</table>
			                       	</div>
									<!-- 户内 -->
		                   			<div id="recordListTable2Div">
			                       	<table id="recordListTable2" class="table table-striped table-bordered nowrap " width="100%">
			                            <thead >
			                                <tr>
			                                    <th>操作</th>
			                                    <th>立管</th>
			                                    <th>强度试验范围</th>
			                                    <th>强度试验压力</th>
			                                    <th>气密性试验范围</th>
			                                    <th>气密性试验压力</th>
			                                    <th>检测方法</th>
			                                    <th>试验结果</th>
			                                    <th>日期</th>
			                                    
			                                </tr>
			                            </thead>
			                            
			                       	</table>
			                       	</div>
			                       	
		                       	</form>
		                       		<!-- 照片 -->
		                       	<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list hidden" id="recordImagesList">
										<input type="hidden" id="stId1" />
										<input type="hidden" id="pcId1" />
									     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
									     <ul class="row">
									     </ul>
									</div>
								</div> 
		                    </div>
	                   	</div>
                    </div>
               	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" id="">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <h4 class="panel-title">预览区</h4>
			    </div>
			    <div class="panel-body " id="strengthTest_panel_box">
	                 <div class="clearboth form-box " >
	                 	
	                 	<div class="iframe-report-box " id="">
	                  		<iframe id="mainFrm" class="iframe-report " style="width:794px;height:1123px;border:1; overflow-y:auto;" scrolling="no"></iframe>
	                  	</div>
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
    App.setPageTitle('强度试验- 工程项目管理系统 ');
    $("#signForm").toggleEditState().styleFit();
    $("#auditForm").toggleEditState().styleFit();
   
    $('.update-show').addClass('hidden');
   
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.cuName); //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    	$("#corpName").val(projJson.corpName);
    	$("#designUnit").val(projJson.duName);
    	$("#projAddr").val(projJson.projAddr);
    	if(projJson.suName=='' || projJson.suName==null){
    		$(".suUnit").addClass("hidden");
    	}else{
    		$(".suUnit").removeClass("hidden");
    	}
    }
    
  	//签字加载方式
  	$.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	 $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6, #signBtn_7").handleSignature(); 
  	});
  	
  	//默认管道类型
  	$("#stPipeType").val("1")
  	var stPipeType =$("#stPipeType").val();
  	$(".indoorPipe").addClass("hidden");
  	
	$(".otherPipe").removeClass("hidden");
	$("#recordListTable1Div").removeClass("hidden");
	$("#recordListTable2Div").addClass("hidden");
  	$("#stPipeType").change(function(){
  	    recordsId = '';   //每次改变选择时清空值
  		stPipeType = $(this).val();
  		if(stPipeType=='1'){
  			$(".indoorPipe").addClass("hidden");
  			$(".otherPipe").removeClass("hidden");
  			$("#recordListTable1Div").removeClass("hidden");
  			$("#recordListTable2Div").addClass("hidden");
  		}else{  //户内
  			
  			$(".otherPipe").addClass("hidden");
  			$(".indoorPipe").removeClass("hidden");
  			$("#recordListTable1Div").addClass("hidden");
  			$("#recordListTable2Div").removeClass("hidden");
  		}
  		$("#auditForm").toggleEditState(true).styleFit();
  		$("#auditForm .addClear").val("");
  		handleRecord();
  		showReport();
  	});
  	
   //打印预览窗口缩放调整
   if($(".iframe-report").length > 0){
		var fr = $(".iframe-report");
		for(var i=0; i<fr.length; i++){
			fr.eq(i).rescaleReportPanel();
		}
	} 
   
    $('.datepicker-default').datepicker({
    	todayHighlight: true
     });
     $.getScript('assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js').done(function() {
     	$('.datetimepicker-default').datetimepicker({
        	todayHighlight: true,
        	language:  'cn',
        	todayBtn:  1
         });
      }); 
     $.getScript('assets/plugins/jedate/jedate.js').done(function() {
     	jeDate({
     		dateCell:"#stRegulatorStartTime",
     	    format:"YYYY-MM-DD hh:mm:ss",
     	    isTime:true,
     	    festival: true
     	});
     	 jeDate({
     		dateCell:"#stRegulatorEndTime",
     	    format:"YYYY-MM-DD hh:mm:ss",
     	    isTime:true,
     	    festival: true
     	});
     	jeDate({
     		dateCell:"#gastRegulatorStartTime",
     	    format:"YYYY-MM-DD hh:mm:ss",
     	    isTime:true,
     	    festival: true
     	});
     	 jeDate({
     		dateCell:"#gastRegulatorEndTime",
     	    format:"YYYY-MM-DD hh:mm:ss",
     	    isTime:true,
     	    festival: true
     	});
     });
   	 $.getScript('projectjs/inspection/strength-test.js?'+Math.random()).done(function () {
   		strengthTest.init();
   	 });
   
  	//加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var cptPath = '<%=basePath%>';
  	//加载列表区记录报表
    var showReport = function(){
        var json=trSData.projectCheckListTable.json;
        var pcId = $("#pcIdNew").val();
        //列表区的cpt文件数据从trSData中获取
        if(json){
        	projName=json.projName;
        	suName=json.suName;
        	constructionUnit=json.constructionUnit;
        	corpName=json.corpName;
        	stInstruction = json.stInstruction;
        	gastInstruction = json.gastInstruction;
        	inspectionDate=json.inspectionDate;
        	stMedium= json.stMedium;
        	stRange=json.stRange;
        	stBuildingNo = json.stBuildingNo;
        	stHouseHolds = json.stHouseHolds;
        }else{
        	//签字区保存后不刷新页面,cpt文件中数据从签字区获取
        	projName=$('#projName').val();
        	suName=$('#suName').val();
        	corpName=$('#corpName').val();
        	constructionUnit=$('#constructionUnit').val();
        	stInstruction = $('#stInstruction').val();
        	gastInstruction = $('#gastInstruction').val();
        	stMedium = $('#stMedium').val();
        	stRange = $('#stRange').val();
        	stBuildingNo = $('#stBuildingNo').val();
        	stHouseHolds = $('#stHouseHolds').val();
        }
        	stPipeType= $('#stPipeType').val();
    	    //以下处理为解决中文乱码
    	    projName=encodeURIComponent(cjkEncode(projName));
    	    suName=encodeURIComponent(cjkEncode(suName));
    	    corpName=encodeURIComponent(cjkEncode(corpName));
    	    stInstruction = encodeURIComponent(cjkEncode(stInstruction));
    	    gastInstruction = encodeURIComponent(cjkEncode(gastInstruction));
    	    stMedium = encodeURIComponent(cjkEncode(stMedium));
    	    stRange=encodeURIComponent(cjkEncode(stRange));
    	    stBuildingNo=encodeURIComponent(cjkEncode(stBuildingNo));
    	    stHouseHolds=encodeURIComponent(cjkEncode(stHouseHolds));
        	constructionUnit = encodeURIComponent(cjkEncode(constructionUnit)); 
        	if(!pcId){
        		pcId='-1';
        	}
        //cpt路径及参数
        	
        var src=cptPath;
        var condition="&pcId="+pcId+"&imgUrl="+$(".imgUrl").val()+"&projName="+projName+"&suName="+suName+"&corpName="+corpName+"&constructionUnit="+constructionUnit+"&stInstruction="+stInstruction+"&gastInstruction="+gastInstruction+"&projId="+$('#projId').val();
         if(stPipeType=='2'){
        	 src += "/ReportServer?reportlet=inspection/strengthTestIndoor1.cpt&stBuildingNo="+stBuildingNo+"&stHouseHolds="+stHouseHolds;
         }else{
        	 src += "/ReportServer?reportlet=inspection/strengthTest.cpt&stMedium="+stMedium+"&stRange="+stRange;
         }
    	$("#mainFrm").attr("src",src+condition);
    };
    //移动端点击上传
    $("#projectImagesList .uploadBtn").off("click").on("click",function(){
	    var pcId = $("#pcId").val(),
		projNo = $("#projNo").val(),
		projId = $("#projId").val(),
		stepId=getStepId();
	    busRecordId=pcId;
		if(_inApk && $(".attach-images-list").find(".new-image").length){
				//photoUpload($(".attach-images-list .upload-image-btn"), projId, projNo, getStepId(), pcId);
				
			var images = $(".attach-images-list .uploadBtn").parents(".attach-images-list").find(".new-image"),
		    files = [];
		    $.each(images, function (i, el) {
		        files.push($(el).find(".preview-btn").attr("src-orig"));
		    });
		    //navigator.notification.alert(files);
		    if (!busRecordId && $("#pcId").length && !$("#pcId").val()) {
		        navigator.notification.alert('请先保存该条记录，保存记录后会自动上传图片', null, "提醒", "确定");
		        return false;
		    }
		    if (files.length) {
		    	uploadImages(files, 0, files.length, projId, projNo, stepId, busRecordId);
		    } else {
		        navigator.notification.alert("没有需要上传的图片!", null, "提醒", "确定");
		    }
		}else{
			 navigator.notification.alert("没有需要上传的图片!", null, "提醒", "确定");
		};
	})
	
	 //移动端点击上传
    $("#recordImagesList .uploadBtn").off("click").on("click",function(){
    	var pcId = $('#pcId1').val();
		var projId = $("#projId").val(),projNo=$('#stId1').val(),
		stepId=getStepId();
	    var busRecordId=pcId;
		if(_inApk && $(".attach-images-list").find(".new-image").length){
				//photoUpload($(".attach-images-list .upload-image-btn"), projId, projNo, getStepId(), pcId);
				
			var images = $(".attach-images-list .uploadBtn").parents(".attach-images-list").find(".new-image"),
		    files = [];
		    $.each(images, function (i, el) {
		        files.push($(el).find(".preview-btn").attr("src-orig"));
		    });
		    //navigator.notification.alert(files);
		     if (!projNo && $("#stId1").length && !$("#stId1").val()) {
		        navigator.notification.alert('请先保存记录，保存记录后会自动上传图片', null, "提醒", "确定");
		        return false;
		    }
		    /* if (!busRecordId && $("#pcId").length && !$("#pcId").val()) {
		        navigator.notification.alert('请先保存记录，保存记录后会自动上传图片', null, "提醒", "确定");
		        return false;
		    } */
		    if (files.length) {
		    	uploadImages(files, 0, files.length, projId, projNo, stepId, busRecordId);
		    } else {
		        navigator.notification.alert("没有需要上传的图片!", null, "提醒", "确定");
		    }
		}else{
			 navigator.notification.alert("没有需要上传的图片!", null, "提醒", "确定");
		};
	})
	//限制上传多个照片
	var surplusAccept=function(){
		$(".surplusDelBtn").removeClass("hidden");
	}
	
	//上传多个照片后删除
	$(".surplusDelBtn").off().on("click",function(){
		$("#filePreviews tbody").html("");
		$(".surplusDelBtn").addClass("hidden");
	})
</script>

<!-- ================== END PAGE LEVEL JS ================== -->