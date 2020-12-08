<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- altimetricSurvey.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

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
						<li class="active"><a href="#default-tab-1" id="listTab"
											  data-toggle="tab">列表区</a></li>
						<li class="" id="addTab"><a href="#default-tab-2"
													id="signTab" data-toggle="tab">签字区</a></li>
						<li class="hidden"><a href="#default-tab-3" id="auditTab"
											  data-toggle="tab">记录区</a></li>
					</ul>
				</div>
				<div class="panel-body" id="">
					<div class="tab-content">
						<div class="tab-pane fade active in btn-top" id="default-tab-1">
							<input type="hidden" id="pcIdNew" class="addClear">
							<input type="hidden" id="loginPost" value="${loginPost }"/>
							<input type="hidden" id="builderPost" value="${builderPost }"/>
							<input type="hidden" id="sujgjPost" value="${sujgjPost }"/>
							<input type="hidden" id="insDate" name="insDate">
							<input type="hidden" id="status" name="status">
							<input type="hidden" id="sendDate" name="sendDate">
							<input type="hidden" id="upgradeDate" name="upgradeDate" value="${upgradeDate}">
							<input type="hidden" id="businessOrderId" name="businessOrderId">
							<input type="hidden" id="finishedDate" name="finishedDate">
									<input type="hidden" id="finishedOpr" name="finishedOpr">
									<input type="hidden" id="resetDate" name="resetDate">
									<input type="hidden" id="resetReason" name="resetReason">
									<input type="hidden" id="isFinishSign" name="isFinishSign">
							<!--<input type="hidden" id="addShow"> -->
							<table data-attach-table="all"
								   class="table table-hover table-striped table-bordered nowrap"
								   id="preservativeInpectTable" width="100%">
								<thead>
								<tr>
									<th></th>
									<th>报验日期</th>
									<th>施工工序</th>
									<th>查验结果</th>
									<th>完成状态</th>
									<th></th>
								</tr>
								</thead>
							</table>
						</div>
						<div class="tab-pane fade btn-top" id="default-tab-2">
							<div class="toolBtn f-r p-b-10 update-show">

								<a href="javascript:;"
								   class="btn btn-confirm btn-sm m-l-5 checkRole savebtn">保存</a>
								<a href="javascript:;"
								   class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">放弃</a>
							</div>
							<div class="clearboth form-box">
								<input type="hidden" class="imgUrl" value="${imgUrl}" />
								<form class="form-horizontal" id="preservativeInpectForm"
									  data-parsley-validate="true"
									  action="">
									<input type="hidden" id="pcDesId" name="pcDesId"
										   value="${checkType}" /> <input type="hidden" id="pcId"
																		  name="pcId" class="addClear"/> <input type="hidden" id="piId" name="piId"
																												class="addClear" />
									<input type="hidden" id="version" name="version" class="addClear"/>
									<input type="hidden" id="projId"
										   name="projId">
									<!-- 完成标记 -->
									<input type="hidden" id="flag" name="flag">
									<input type="hidden" id="flag1" class="addClear">
									<div class="form-group  col-sm-6">
										<label class="control-label" for="projNo">工程编号</label>
										<div>
											<input type="text"
												   class="form-control field-not-editable input-sm" id="projNo"
												   name="projNo" />
										</div>
									</div>
									<div class="form-group  col-sm-6">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text"
												   class="form-control field-not-editable input-sm"
												   id="projName" name="projName" />
										</div>
									</div>
									<div class="form-group col-md-12 col-sm-12">
										<label class="control-label" for="projAddr">工程地点</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projAddr" name="projAddr"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 preservativeType">
										<label class="control-label" for="preservativeType">防腐类型</label>
										<div>
											<select class="form-control field-editable input-sm allText construction"
													id="preservativeType" name="preservativeType"
													data-parsley-required="true">
												<c:forEach var="ptEnum" items="${preservativeTypeEnum }">
													<option value="${ ptEnum.value}">${ ptEnum.message}</option>
												</c:forEach>

											</select>
											<!-- <input type="text" class="form-control input-sm" id="jointConnect"   name="jointConnect"  data-parsley-required="true"/> -->
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label">报验日期</label>
										<div>
											<input type="text"
												   class="form-control input-sm datepicker-default field-not-editable addClear allText"
												   value="" id="inspectionDate" name="inspectionDate"
												   data-parsley-required="true" />
										</div>
									</div>
									<div class="form-group col-md-12 col-sm-12">
										<label class="control-label" for="inspectionResult">检查结果</label>
										<div>
											<input type="text"
												   class="form-control input-sm  field-editable addClear allText construction"  value=""
												   id="inspectionResult" name="inspectionResult"
												   data-parsley-maxlength="200" />
										</div>
									</div>

									<div class="form-group col-xs-12 selfcheckformtitle">
										<i class="fa fa-angle-double-right"></i>焊接接头防腐
									</div>
									<div class="form-group col-sm-6 clearboth">
										<label class="control-label" for="jointSpecification">接头规格及数量</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="jointSpecification" name="jointSpecification"
												   data-parsley-maxlength="100" />
										</div>
									</div>
									<div class="form-group col-sm-6">
										<label class="control-label" for="preservativeRange">防腐等级</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="preservativeRange" name="preservativeRange"
												   data-parsley-maxlength="200" />
										</div>
									</div>

									<div class="form-group col-sm-6 jointType">
										<label class="control-label" for="preservativeMeasure">补口防腐措施</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="preservativeMeasure" name="preservativeMeasure"
												   data-parsley-maxlength="200" />
										</div>
									</div>
									<div class="form-group col-sm-6 paintType hidden">
										<label class="control-label" for="primerRatio">防腐底漆配比</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="primerRatio" name="primerRatio"
												   data-parsley-maxlength="200" />
										</div>
									</div>
									<div class="form-group col-sm-6">
										<label class="control-label">除锈见金属光泽</label>
										<div>
											<label> <input type="radio" class="field-editable allText construction"
														   name="jointMetallicLuster" value="1" />是
											</label> <label> <input type="radio" class="field-editable allText construction"
																	name="jointMetallicLuster" value="0" checked="checked" />否
										</label>
										</div>
									</div>
									<div class="form-group col-sm-6">
										<label class="control-label">是否刷底漆</label>
										<div>
											<label> <input type="radio" class="field-editable allText construction"
														   name="jointIsPrimer" value="1" />是
											</label> <label> <input type="radio" class="field-editable allText construction"
																	name="jointIsPrimer" value="0" checked="checked" />否
										</label>
										</div>
									</div>
									<div class="form-group col-sm-6 jointType">
										<label class="control-label" for="jointPreservativeRange">接头防腐等级</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="jointPreservativeRange" name="jointPreservativeRange"
												   data-parsley-maxlength="200" />
										</div>
									</div>
									<div class="form-group col-sm-6 jointType">
										<label class="control-label" for="jointVshart">电火花检测电压</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="jointVshart" name="jointVshart"
												   data-parsley-maxlength="200" />
										</div>
									</div>
									<div class="form-group col-sm-6 jointType">
										<label class="control-label">有无电火花产生</label>
										<div>
											<label> <input type="radio" class="field-editable allText construction"
														   name="jointElectricSpark" value="1" />有
											</label> <label> <input type="radio" class="field-editable allText construction"
																	name="jointElectricSpark" value="0" checked="checked" />无
										</label>
										</div>
									</div>
									<div class="form-group col-sm-6 jointType">
										<label class="control-label" for="jointRepairNum">修补</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="jointRepairNum" name="jointRepairNum"
												   data-parsley-maxlength="200" />
										</div>
									</div>

									<div class="form-group col-xs-12 selfcheckformtitle">
										<i class="fa fa-angle-double-right"></i>管道防腐
									</div>
									<div class="form-group col-sm-6 clearboth">
										<label class="control-label">供货单位</label>
										<div>
											<label> <input type="radio" class="field-editable allText construction"
														   name="pipeSupplyUnit" value="1" />甲方
											</label> <label> <input type="radio" class="field-editable allText construction"
																	name="pipeSupplyUnit" value="0" checked="checked" />自购
										</label>
										</div>
									</div>
									<div class="form-group col-sm-6">
										<label class="control-label" for="pipePreservativeRange">防腐等级</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="pipePreservativeRange" name="pipePreservativeRange"
												   data-parsley-maxlength="200" />

										</div>
									</div>
									<div class="form-group col-sm-6">
										<label class="control-label" for="pipeSpecification">规格及数量</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="pipeSpecification" name="pipeSpecification"
												   data-parsley-maxlength="200" />
										</div>
									</div>
									<div class="form-group col-sm-6 jointType">
										<label class="control-label" for="pipeVshort">现场电火花电压</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="pipeVshort" name="pipeVshort"
												   data-parsley-maxlength="200" />
										</div>
									</div>
									<div class="form-group col-sm-6 jointType">
										<label class="control-label" for="pipeDamagedNum">破损</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="pipeDamagedNum" name="pipeDamagedNum"
												   data-parsley-maxlength="50" />
										</div>
									</div>
									<div class="form-group col-sm-6 jointType">
										<label class="control-label" for="pipeRepairNum">补休结果</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="pipeRepairNum" name="pipeRepairNum"
												   data-parsley-maxlength="50" />
										</div>
									</div>
									<div class="form-group col-sm-6 jointType">
										<label class="control-label" for="electricSparkNum">电火花检测</label>
										<div>
											<input type="text"
												   class="form-control field-editable input-sm addClear allText construction"
												   id="electricSparkNum" name="electricSparkNum"
												   data-parsley-maxlength="50" />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label">目视检查管道</label>
										<div>
											<label class="control-label">外观表面平整、无气泡、麻面、皱纹、瘸子</label>
											<label> <input type="radio" class="field-editable allText construction"
														   name="isLeakageWeld" value="1" />是
											</label> <label> <input type="radio" class="field-editable allText construction"
																	name="isLeakageWeld" value="0" checked="checked" />否
										</label>
										</div>
									</div>
									<div class="form-group col-sm-12 jointType">
										<label class="control-label">有无电火花</label>
										<div>
											<label> <input type="radio" class="field-editable allText construction"
														   name="pipeElectricSpark" value="1" />有
											</label> <label> <input type="radio" class="field-editable allText construction"
																	name="pipeElectricSpark" value="0" checked="checked" />无
										</label>
										</div>
									</div>

									<div class="form-group col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text"
												   class="form-control field-not-editable input-sm"
												   id="constructionUnit" name="constructionUnit" />
										</div>
									</div>

									<div class="form-group col-md-6 col-sm-12 allSign cuPm">
										<label class="control-label signature-tool sign-require sign-all"
											   for="projectLeader">项目负责人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white"
											   id="signBtn_3"><i class="fa fa-pencil"></i></a> <input
												type="hidden" class="sign-data-input" id="projectLeader"
												name="projectLeader" value=""> <input type="hidden"
																					  class="signPost"  id="projectLeader_postType" name="projectLeader_postType"
																					  value="${projectLeaderPost }"> <img
												class="projectLeader" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i
													class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text"
												   class="form-control input-sm datepicker-default field-editable addClear allText cuPm"
												   value="" id="plSignDate" name="plSignDate" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign qualitativeCheckMember">
										<label class="control-label signature-tool sign-require  sign-all"
											   for="constructionQc">质检人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white"
											   id="signBtn_5"><i class="fa fa-pencil"></i></a> <input
												type="hidden" class="sign-data-input" id="constructionQc"
												name="constructionQc" value=""> <input type="hidden"
																					   class="signPost"  id="constructionQc_postType" name="constructionQc_postType"
																					   value="${constructionQcPost}"> <img
												class="constructionQc" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i
													class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text"
												   class="form-control input-sm datepicker-default field-editable addClear allText qualitativeCheckMember"
												   value="" id="cuQcSignDate" name="cuQcSignDate" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign construction">
										<label class="control-label signature-tool sign-require  sign-all" for="builder">施工员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white"
											   id="signBtn_4"><i class="fa fa-pencil"></i></a> <input
												type="hidden" class="sign-data-input" id="builder"
												name="builder" value=""> <input type="hidden"
																				class="signPost"  id="builder_postType" name="builder_postType"
																				value="${construction }"> <img class="builder" alt=""
																											   src="" style="height: 30px"> <span class="clear-sign"><i
												class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text"
												   class="form-control input-sm datepicker-default field-editable addClear allText construction"
												   value="" id="builderSignDate" name="builderSignDate" />
										</div>
									</div>
									<div class="form-group  col-sm-12">
										<label class="control-label" for="suName">监理单位</label>
										<div>
											<input type="text"
												   class="form-control field-not-editable input-sm " value=""
												   id="suName" name="suName" />
										</div>
									</div>
									<%-- 	<div class="form-group col-md-6 col-sm-6 allSign suJgj">
                                            <label class="control-label signature-tool" for="suJgj">现场监理</label>
                                            <div>
                                                <a href="javascript:;" class="btn btn-sm btn-white"
                                                    id="signBtn_2"><i class="fa fa-pencil"></i></a> <input
                                                    type="hidden" class="sign-data-input" id="suJgj"
                                                    name="suJgj" value=""> <input type="hidden"
                                                    class="signPost" id="suJgj_postType" name="suJgj_postType" value="${sujgjPost }">
                                                <img class="suJgj" alt="" src="" style="height: 30px">
                                                <span class="clear-sign"><i
                                                    class="fa ion-close-circled"></i></span>
                                            </div>
                                        </div> --%>
									<div class="form-group col-md-6 col-sm-6 hidden selectSignDate strengDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text"
												   class="form-control input-sm datepicker-default field-editable addClear allText suJgj"
												   value="" id="suJgjSignDate" name="suJgjSignDate" />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="corpName">建设单位</label>
										<div>
											<input type="text"
												   class="form-control field-not-editable input-sm"
												   id="corpName" name="corpName" />
										</div>
									</div>
									<%-- <div class="form-group col-md-6 col-sm-6 allSign builder">
										<label class="control-label signature-tool"
											for="fieldsRepresent">现场代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white"
												id="signBtn_1"><i class="fa fa-pencil"></i></a> <input
												type="hidden" class="sign-data-input" id="fieldsRepresent"
												name="fieldsRepresent" value=""> <input
												type="hidden" id="fieldsRepresent_postType"
												name="fieldsRepresent_postType" value="${fieldsRepresentPost}">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i
												class="fa ion-close-circled"></i></span>
										</div>
									</div> --%>
									<div class="form-group col-sm-6 hidden selectSignDate strengDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text"
												   class="form-control field-editable datepicker-default input-sm addClear allText builder"
												   id="fieldsRepresentDate" name="fieldsRepresentDate" />
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

						<!-- <div class="tab-pane fade  btn-top hidden" id="default-tab-3">
	                   		<div class="toolBtn f-r p-b-10 editbtn" >
			                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole auditAddBtn">增加</a>
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole auditSavebtn">保存</a>
						   		 <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole deleteBtn">删除</a>
						    </div>
						    <div class="clearboth form-box">
		                   		<form class="form-horizontal" id="preservativeInpectForm2" data-parsley-validate="true" action="">
		                   		
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="peWeldLineNo">焊缝编号</label>
										<div>
							                <input type="text" class="form-control input-sm" id="peWeldLineNo"   name="peWeldLineNo"  data-parsley-required="true"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label">施工日期</label>
										<div>
							                <input type="text" class="form-control datepicker-default input-sm" id="cuDate"   name="cuDate" data-parsley-required="true" />
							            </div>
									</div>
									joint start
									<div class="form-group col-md-6 col-sm-12 joint">
										<label class="control-label" for="curlCheck">卷边检查</label>
										<div>
							                <input type="text" class="form-control input-sm" id="curlCheck"   name="curlCheck" data-parsley-required="true" />
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 joint">
										<label class="control-label" for="weldRingWidthB">焊环环宽度B</label>
										<div>
							                <input type="text" class="form-control input-sm" id="weldRingWidthB"   name="weldRingWidthB" data-parsley-required="true" />
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 joint">
										<label class="control-label" for="weldRingHeightH">焊环高度H</label>
										<div>
							                <input type="text" class="form-control input-sm" id="weldRingHeightH"   name="weldRingHeightH" data-parsley-required="true" />
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 joint">
										<label class="control-label" for="weldRingSeamH">焊环环缝高度h</label>
										<div>
							                <input type="text" class="form-control input-sm" id="weldRingSeamH"   name="weldRingSeamH" data-parsley-required="true" />
							            </div>
									</div>
		                   			
		                   			<div class="form-group col-md-12 col-sm-12 joint">
										<label class="control-label" for="wrongEdge">错边量</label>
										<div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 joint">
										<label class="control-label" for="isLeakageWeld">有无漏焊</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isLeakageWeld" value="1" />有
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isLeakageWeld" value="0" checked="checked"/>无
				      						 </label>
			   							</div>
										<div>
							                <input type="text" class="form-control input-sm" id="isLeakageWeld"   name="isLeakageWeld"  data-parsley-required="true" />
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 joint">
										<label class="control-label" for="isCurlDefect">是否有缺陷</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isCurlDefect" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isCurlDefect" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<div>
							                <input type="text" class="form-control input-sm" id="isCurlDefect"   name="isCurlDefect"  data-parsley-required="true"/>
							            </div>
									</div>
		                   			
									joint end
									
									paint start
									<div class="form-group col-md-6 col-sm-12 paint">
										<label class="control-label" for="wrongEdge">是否完整</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isBidding" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isBidding" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div>
									</div>
									
									<div class="form-group col-md-6 col-sm-12 paint">
										<label class="control-label" for="wrongEdge">是否同轴</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isBidding" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isBidding" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 paint">
										<label class="control-label" for="wrongEdge">是否有刮痕</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isBidding" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isBidding" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 paint">
										<label class="control-label" for="wrongEdge">观察孔冒料</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isBidding" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isBidding" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
			   	
										<div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 paint">
										<label class="control-label" for="wrongEdge">管材管件完整</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isBidding" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isBidding" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 paint">
										<label class="control-label" for="wrongEdge">是否有熔融物</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isBidding" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isBidding" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div>
									</div>
									
									
									paint end
		                   			
		                   			
			                       	<table id="preservativeInpectJointTable" class="table table-striped table-bordered nowrap jointTable" width="100%">
			                            <thead >
			                                <tr>
			                                    <th>焊缝编号</th>
			                                    <th>有无漏焊</th>
			                                    <th>是否有缺陷</th>
			                                    <th>卷边切除检查</th>
			                                    <th>焊环环的宽度B</th>
			                                    <th>环的高度H</th>
			                                    <th>环缝高度h</th>
			                                    <th>错边量</th>
			                                    <th>施工日期</th>
			                                </tr>
			                            </thead>
			                            
			                       	</table>
			                       	<table id="preservativeInpectPaintTable" class="table table-striped table-bordered nowrap paintTable " width="100%">
			                            <thead class="">
			                                <tr>
			                                    <th>焊缝编号</th>
			                                    <th>管材管件完整</th>
			                                    <th>是否有缺陷</th>
			                                    <th>是否有熔融物</th>
			                                    <th>是否同轴</th>
			                                    <th>是否有刮痕</th>
			                                    <th>观察孔冒料</th>
			                                    <th>施工日期</th>
			                                </tr>
			                            </thead>
			                            
			                       	</table>
		                       	</form>
		                    </div>
	                   	</div> -->
					</div>
				</div>
			</div>
		</div>
		<!-- begin col-6 -->
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" id="">
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
					<h4 class="panel-title">预览区</h4>
				</div>
				<div class="panel-body" id="altimetric_survey_audit_panel_box">
					<div class="clearboth form-box">
						<div class="iframe-report-box">
							<iframe id="mainFrm" class="iframe-report"
									style="width: 850px; height: 1150px; border: 1; overflow-y: auto;"
									scrolling="no"></iframe>
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
    App.setPageTitle('防腐检查- 工程项目管理系统 ');
    $("#preservativeInpectForm").toggleEditState().styleFit();
    // $("#preservativeInpectForm2").toggleEditState().styleFit();

    $('.update-show').addClass('hidden');
    $('.editBtn').addClass('hidden');

    if(!getProjectInfo()){
        loadProjectList();
    }else{
        var projJson = getProjectInfo();
        $('#projName').val(projJson.projName);				   //工程名称
        $('#projId').val(projJson.projId);					   //工程ID
        $('#projNo').val(projJson.projNo);     	 			   //工程编号
        $('#constructionUnit').val(projJson.cuName); 		   //施工单位
        $('#suName').val(projJson.suName);					   //监理公司
        $("#corpName").val(projJson.corpName);				   //燃气公司
        $("#projAddr").val(projJson.projAddr);				   //工程地点
    }

    //签字加载方式
    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5").handleSignature();

    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
        var fr = $(".iframe-report");
        for(var i=0; i<fr.length; i++){
            fr.eq(i).rescaleReportPanel();
        }
    }
    //签字区保存功能
    $(".savebtn").on("click",function(){
        $("#flag").val("0");
        var data={};
        if($("#preservativeInpectForm").parsley().isValid()){   //验证必填项是否已经填写，若未填写，则不提交表单
            data = $("#preservativeInpectForm").serializeJson();
            //报验单和防腐检查一一对应，重新组装json数据
            var preservativeInpect={};
            preservativeInpect.pcId = data.pcId;
            preservativeInpect.projId = data.projId;
            preservativeInpect.piId = data.piId;
            preservativeInpect.preservativeRange = data.preservativeRange;
            preservativeInpect.preservativeMeasure = data.preservativeMeasure;
            preservativeInpect.preservativeType = data.preservativeType;
            preservativeInpect.jointSpecification = data.jointSpecification;
            preservativeInpect.jointPreservativeRange = data.jointPreservativeRange;
            preservativeInpect.jointAsphaltgrade = data.jointAsphaltgrade;
            preservativeInpect.jointFiberglassCloth = data.jointFiberglassCloth;
            preservativeInpect.primerRatio = data.primerRatio;
            preservativeInpect.jointMetallicLuster = data.jointMetallicLuster;
            preservativeInpect.jointIsPrimer = data.jointIsPrimer;
            preservativeInpect.jointVshart = data.jointVshart;
            preservativeInpect.jointElectricSpark = data.jointElectricSpark;
            preservativeInpect.jointRepairNum = data.jointRepairNum;
            preservativeInpect.pipeSupplyUnit = data.pipeSupplyUnit;
            preservativeInpect.pipePreservativeRange = data.pipePreservativeRange;
            preservativeInpect.pipeSpecification = data.pipeSpecification;
            preservativeInpect.pipeVshort = data.pipeVshort;
            preservativeInpect.pipeDamagedNum = data.pipeDamagedNum;
            preservativeInpect.pipeRepairNum = data.pipeRepairNum;
            preservativeInpect.electricSparkNum = data.electricSparkNum;
            preservativeInpect.isLeakageWeld = data.isLeakageWeld;
            preservativeInpect.pipeElectricSpark = data.pipeElectricSpark;
            data.preservativeInpect = preservativeInpect;
            dataStr = JSON.stringify(data);


            //判断是否已完成签字
            //验证必签签字是否已签
            var signtools = $("#preservativeInpectForm").find(".signature-tool.sign-all"),
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
            if(sBlank){
                console.info("未完成签字");
                data.isFinishSign = "0";
            }else{
                console.info("已完成签字");
                data.isFinishSign = "1";
            }

            var t = $('#preservativeInpectTable');
            //开启表单验证
            if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
                //防止多次提交
                if(response){
                    response.abort();
                }
                $(".savebtn").parent().parent().loadMask("正在保存...", 1, 0.5);
                var response = $.ajax({
                    url: 'preservativeInpect/saveSign',
                    type: "POST",
                    timeout : 5000,
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(data),
                    success: function (resData) {
                        $(".savebtn").parent().parent().hideMask();
                        var content = "数据保存成功！";
                        if(resData === "false"){
                            content = "数据保存失败！";
                        }else if(resData === "already"){
                            content = "此信息已被修改，请刷新页面！";
                        }else {
                            $('#pcIdNew').val(resData);
                            $('#pcId').val(resData);
                            projNo = $("#projNo").val();
                            projId = $("#projId").val();
                            if(_inApk && $(".attach-images-list").find(".new-image").length){
                                preImagesUpload($(".attach-images-list .uploadBtn"), projId, projNo, getStepId(), resData);
                            };
                            saveBack();
//    	                        if(_inApk){
//    	                        	navigator.notification.alert('数据保存成功！', null, '提示信息', '确定');
//    	                        }else{
//    	                        	 $("body").cgetPopup({
//    			                        	title: "提示信息",
//    			                        	content: "数据保存成功!",
//    			                        	accept:  saveBack,
//    			                        	chide: true,
//    			                        	icon: "fa-check-circle",
//    			                        	newpop: 'new'
//    			                        });
//    	                        }


                        }
                        if(_inApk){
                            navigator.notification.alert(content, null, '提示信息', '确定');
                        }else {
                            $("body").cgetPopup({
                                title: "提示信息",
                                content: content,
                                accept: popClose,
                                chide: true,
                                icon: "fa-check-circle"
                            });
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        //判断超时
                        if(textStatus === 'timeout'){
                            response.abort();
                            saveBack && saveBack(textStatus);
                        }
                        printXHRError( "表单(" + t.attr("id") + ") ajax保存查询失败", jqXHR, textStatus, errorThrown);
                    }
                });
                //如果通过验证, 则移除验证UI
                t.parsley().validate();
            } else {
                //如果未通过验证, 则加载验证UI
                t.parsley().validate();
            };
        }else{
            //如果未通过验证, 则加载验证UI
            $("#preservativeInpectForm").parsley().validate();
        }
        //$("#preservativeInpectForm").gformSave(dataStr,"preservativeInpectTable",saveBack,"new");
    });

    function saveBack(data){
        $('.update-show').addClass("hidden");
        $('#preservativeInpectForm').toggleEditState(false);
        showReport();

    }
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    //连接类型默认为热熔连接
    // var preservativeType = "0";
    //页面初始
    $("#preservativeType").val("0");
    $(".jointType").removeClass("hidden");
    $("#preservativeType").change(function(){
        if($(this).val()=='0'){
            //preservativeType="0";
            $(".jointType").removeClass("hidden");
            $('.paintType').addClass("hidden");

        }else{
            //preservativeType="1";
            $(".jointType").addClass("hidden");
            $('.paintType').removeClass("hidden");
        }
        showReport();
    });
    $.getScript('projectjs/inspection/preservative-inpect.js?'+Math.random()).done(function () {
        preservativeInpect.init();
    });

    //加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var cptPath = '<%=basePath%>';
    //加载列表区和签字区的cpt文件
    var showReport = function() {
        var projName = '', suName = '', constructionUnit = '', inspectionDate = '', pcId = '', corpName = '';
        var json = trSData.preservativeInpectTable.json;
        // console.log(json);
        var pcId = $('#pcIdNew').val();
        //列表区的cpt文件数据从trSData中获取
        if (json) {
            projName = json.projName;
            suName = json.suName;
            constructionUnit = json.constructionUnit;
            corpName = json.corpName;
            inspectionDate = json.inspectionDate;
        } else {
            //签字区保存后不刷新页面,cpt文件中数据从签字区获取
            projName = $('#projName').val();
            suName = $("#suName").val();
            // process = $("#process").val();
            constructionUnit = $("#constructionUnit").val();
            corpName = $("#corpName").val();
            inspectionResult = $("#inspectionResult").val();
            inspectionDate = $("#inspectionDate").val();
            builder = $("#builder").val();
        }
        if(pcId==''){
            pcId='-1';
        }
        //以下处理为解决中文乱码
        projName = encodeURIComponent(cjkEncode(projName));
        suName = encodeURIComponent(cjkEncode(suName));
        corpName = encodeURIComponent(cjkEncode(corpName));
        //process = encodeURIComponent(cjkEncode(process));
        inspectionResult = encodeURIComponent(cjkEncode(inspectionResult));
        constructionUnit = encodeURIComponent(cjkEncode(constructionUnit));

        //cpt路径及参数
        var src = "";
        preservativeType = $("#preservativeType").val();
        if(preservativeType == '0') {
            var ur = "preservativeInpectJoint.cpt";
            var date = $("#insDate").val();
            var status = $("#status").val();
            console.info("1data---"+date);
            console.info("1status---"+status);
            console.info("1upgradeDate---"+$("#upgradeDate").val());
            /*if(date !=''){
				console.info("aaa---");
                if(Date.parse(date)>Date.parse($("#upgradeDate").val())){
					console.info("a---");
                    ur = "preservativeInpectJointNew.cpt";
                }
            }else{
				console.info("b---");
                if("1"!=status){
					console.info("c---");
                    ur = "preservativeInpectJointNew.cpt";
                }else{
                    var sendDate = $("#sendDate").val();//完成时间
                    var str= sendDate.split(",");
                    if(Date.parse(str[str.length-2])>Date.parse($("#upgradeDate").val())){
                        ur = "preservativeInpectJointNew.cpt";
                    }
                }
            }*/
            if(date !=''){
                if(Date.parse(date)>Date.parse($("#upgradeDate").val())){
                    ur = "preservativeInpectJointNew.cpt";
                }else{
                    if("1"!=status){
                        ur = "preservativeInpectJointNew.cpt";
                    }else{
                        var sendDate = $("#sendDate").val();//完成时间
                        console.info("2sendDate---"+$("#sendDate").val());
                        var str= sendDate.split(",");
                        if(Date.parse(str[str.length-2])>Date.parse($("#upgradeDate").val())){
                            ur = "preservativeInpectJointNew.cpt";
                        }
                    }
                }
            }





            src = cptPath
                + "/ReportServer?reportlet=inspection/"+ur;
            console.info("1---"+ur);
        } else {
            var ur = "preservativeInpectPaint.cpt";
            var date = $("#insDate").val();
            var status = $("#status").val();
            console.info("2data---"+date);
            console.info("2status---"+status);
            console.info("2upgradeDate---"+$("#upgradeDate").val());
            if(date !=''){
                if(Date.parse(date)>Date.parse($("#upgradeDate").val())){
                    ur = "preservativeInpectPaintNew.cpt";
                }else{
                    if("1"!=status){
                        ur = "preservativeInpectPaintNew.cpt";
                    }else{
                        var sendDate = $("#sendDate").val();//完成时间
                        console.info("2sendDate---"+$("#sendDate").val());
                        var str= sendDate.split(",");
                        if(Date.parse(str[str.length-2])>Date.parse($("#upgradeDate").val())){
                            ur = "preservativeInpectPaintNew.cpt";
                        }
                    }
                }
            }
            src = cptPath
                + "/ReportServer?reportlet=inspection/"+ur;
            console.info("2---"+ur);
        }
        src = src + "&projName=" + projName + "&suName=" + suName
            + "&constructionUnit=" + constructionUnit + "&corpName="
            + corpName + "&inspectionDate=" + inspectionDate;
        src = src + "&pcId=" + pcId + "&imgUrl=" + $(".imgUrl").val();
        $("#mainFrm").attr("src", src);
        console.info("fulw---"+ur);
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
    });
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