<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <input type="hidden" id="actionName" value="${actionName }"/>
                    <input type="hidden" id="loginPost" value="${loginPost }"/>
					<input type="hidden" id="builderPost" value="${builderPost }"/>
					<input type="hidden" id="sujgjPost" value="${sujgjPost }"/>
                    <input type="hidden" id="pcIdNew" name="pcIdNew">
					<input type="hidden" id="businessOrderId" name="businessOrderId">
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表区</a></li>
		                <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
		                <li class="" id="addTab"><a href="#default-tab-2" id="signTab"  data-toggle="tab">签字区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" >
               		<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="projectCheckListTable" width="100%">
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
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole savebtn" >保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">放弃</a>
						    </div>
						    <div class="clearboth form-box">
						    	<input type="hidden" class="imgUrl" value="${imgUrl}"/>
								<form class="form-horizontal" id="signForm" data-parsley-validate="true" action="weldLineInpect/saveSign">
								    <input type="hidden" id="pcDesId" name="pcDesId" value="${checkType}"/> 
									<input type="hidden" id="pcId" name="pcId" class="addClear"/>
									<input type="hidden" id="projId" name="projId" >
									<input type="hidden" id="version" name="version" class="addClear">
									<input type="hidden" id="recordsId" name="recordsId">
									<input type="hidden" id="finishedDate" name="finishedDate">
									<input type="hidden" id="finishedOpr" name="finishedOpr">
									<input type="hidden" id="resetDate" name="resetDate">
									<input type="hidden" id="resetReason" name="resetReason">
									<input type="hidden" id="isFinishSign" name="isFinishSign">
									<input type="hidden" id="meltConnectType1" name="meltConnectType">
								 	<!-- 完成标记 -->
									<input type="hidden" id="flag" name="flag">
									<input type="hidden" id="insDate" name="insDate">
									<input type="hidden" id="status" name="status">
									<input type="hidden" id="sendDate" name="sendDate">
									<input type="hidden" id="upgradeDate" name="upgradeDate" value="${upgradeDate}">
								 	<div class="form-group col-md-6  col-sm-12">
										<label class="control-label" for="projNo">工程编号</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projNo" name="projNo"  />
										</div>
									</div>
									<div class="form-group col-md-12 col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projName" name="projName"  />
										</div>
									</div>
									<div class="form-group col-md-12 col-sm-12">
										<label class="control-label" for="projAddr">工程地点</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projAddr" name="projAddr"  />
										</div>
									</div>
								    <div class="form-group col-md-6 col-sm-12 clearboth">
										<label class="control-label" for="peWeldSpec">焊件规格</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear allText construction" id="peWeldSpec"   name="peWeldSpec"  data-parsley-required="true"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="welderName">焊工姓名</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear allText construction" id="welderName"   name="welderName"  data-parsley-required="true"/>
							            </div>
									</div>
		                   			<div class="form-group  col-sm-12">
										<label class="control-label" for="pipeManufactor">管材厂家</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear allText construction" id="pipeManufactor"   name="pipeManufactor"  data-parsley-required="true"/>
							            </div>
									</div>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label">报验日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-not-editable addClear" value="" id="inspectionDate" name="inspectionDate"/>
										</div>
									</div>
						    		<div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear allText construction" name="inspectionResult" id="inspectionResult" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div> 
					  			   <div class="form-group  col-sm-12">
										<label class="control-label" for="corpName">建设单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="corpName" name="corpName"  />
										</div>
									</div>
								    <%--<div class="form-group col-md-6 col-sm-12 allSign builder">--%>
										<%--<label class="control-label signature-tool" for="fieldsRepresent">现场代表</label>--%>
										<%--<div>--%>
											<%--<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>--%>
											<%--<input type="hidden" class="sign-data-input" id="fieldsRepresent" name="fieldsRepresent" value="">--%>
											<%--<input type="hidden" class="signPost"  id="fieldsRepresent_postType" name="fieldsRepresent_postType" value="${fieldsRepresentPost }">--%>
											<%--<img class="fieldsRepresent" alt="" src="" style="height: 30px">--%>
											<%--<span class="clear-sign"><i class="fa ion-close-circled"></i></span>--%>
										<%--</div>--%>
						    		<%--</div>--%>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate strengDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText builder" value="" id="fieldsRepresentDate" name="fieldsRepresentDate"  />
										</div>
									</div>
									 <div class="form-group  col-sm-12">
										<label class="control-label" for="suName">监理公司</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  value="" id="suName" name="suName"  />
										</div>
								    </div>
									<%--<div class="form-group col-md-6 col-sm-12 allSign suJgj">--%>
										<%--<label class="control-label signature-tool" for="suJgj">现场监理</label>--%>
										<%--<div>--%>
											<%--<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>--%>
											<%--<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">--%>
											<%--<input type="hidden" class="signPost"  id="suJgj_postType" name="suJgj_postType" value="${suJgjPost }">--%>
											<%--<img class="suJgj" alt="" src="" style="height: 30px">--%>
											<%--<span class="clear-sign"><i class="fa ion-close-circled"></i></span>--%>
										<%--</div>--%>
						    		<%--</div>--%>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate strengDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText suJgj" value="" id="suJgjSignDate" name="suJgjSignDate"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign cuPm">
										<label class="control-label signature-tool sign-require sign-all" for="projectLeader">项目负责人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="projectLeader" name="projectLeader" value="">
											<input type="hidden" class="signPost"  id="projectLeader_postType" name="projectLeader_postType" value="${projectLeaderPost }">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText cuPm" value="" id="plSignDate" name="plSignDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign construction">
										<label class="control-label signature-tool sign-require  sign-all" for="builder">施工员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
											<input type="hidden" class="signPost"  id="builder_postType" name="builder_postType" value="${construction }">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText construction" value="" id="builderSignDate" name="builderSignDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign qualitativeCheckMember">
										<label class="control-label signature-tool sign-require  sign-all" for="constructionQc">质检员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQc" name="constructionQc" value="">
											<input type="hidden" class="signPost"  id="constructionQc_postType" name="constructionQc_postType" value="${constructionQcPost }">

											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText qualitativeCheckMember" value="" id="cuQcSignDate" name="cuQcSignDate"  />
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
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole auditEditBtn auditSavebtn2">保存</a>
						   		 <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole auditEditBtn deleteBtn2">删除</a>
						    </div>
						    <div class="clearboth form-box">
						    	<!-- 报验完成状态 -->
						    	<input type="hidden" id="flag1"  class="addClear">
		                   		
		                   		<form class="form-horizontal" id="auditForm" data-parsley-validate="true" action="">
		                   			<input type="hidden" id="peWliId" name="peWliId" class="addClear">
		                   			<input type="hidden" id="recordPcId" name="pcId" class="addClear"/>
		                   			<input type="hidden" id="version" name="version" class="addClear">
		                   			<input type="hidden" id="isFinishSign" name="isFinishSign" class="addClear">
		                   			 <div class="form-group col-md-6 col-sm-12 meltConnectType">
										<label class="control-label" for="meltConnectType">连接类型</label>
										<div>
											<select class="form-control field-editable input-sm allText construction selectType" id="meltConnectType"   name="meltConnectType"  data-parsley-required="true">
												<c:forEach var="meltConnectType" items="${meltConnectTypeEnum }">
													<option value="${meltConnectType.value }">${meltConnectType.message }</option>
												</c:forEach>
											</select>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="peWeldLineNo">焊缝编号</label>
										<div>
							                <input type="text" class="form-control input-sm field-editable addClear allText construction" id="peWeldLineNo"   name="peWeldLineNo"  data-parsley-required="true" data-parsley-maxlength="200" />
							            </div>
									</div>
									<!-- hotMelt start -->
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label">施工日期</label>
										<div>
							                <input type="text" class="form-control datepicker-default input-sm field-not-editable sysDate addClear allText" id="cuDate"   name="cuDate" data-parsley-required="true" />
							            </div>
									</div>
									
									<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="curlCheck">卷边检查</label>
										<div>
				      						 <select class="form-control input-sm field-editable addClear allText construction" id="curlCheck"   name="curlCheck" data-parsley-maxlength="200">
				      						 	<option value=""></option>
				      						 	<option value="合格">合格</option>
				      						 	<option value="不合格">不合格</option>
				      						 </select>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="weldRingWidthB">焊环环宽度B</label>
										<div>
							                <input type="text" class="form-control input-sm field-editable addClear allText construction" id="weldRingWidthB"   name="weldRingWidthB"  data-parsley-maxlength="200" />
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="weldRingHeightH">焊环高度H</label>
										<div>
							                <input type="text" class="form-control input-sm field-editable addClear allText construction" id="weldRingHeightH"   name="weldRingHeightH" data-parsley-maxlength="200"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="weldRingSeamH">焊环环缝高度h</label>
										<div>
							                <input type="text" class="form-control input-sm field-editable addClear allText construction" id="weldRingSeamH"   name="weldRingSeamH"  data-parsley-maxlength="200"/>
							            </div>
									</div>
		                   			
		                   			<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="wrongEdge">错边量</label>
										<div>
							               <!--  <input type="text" class="form-control input-sm field-editable addClear allText construction" id="wrongEdge"   name="wrongEdge" data-parsley-maxlength="20"/>
							            	 -->
				      						 <select class="form-control input-sm field-editable addClear allText construction" id="wrongEdge"   name="wrongEdge" data-parsley-maxlength="200">
				      						 	<option value=""></option>
				      						 	<option value="有">有</option>
				      						 	<option value="无">无</option>
				      						 </select>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="isLeakageWeld">有无漏焊</label>
										<div>
											<label>
				       							<input type="radio" class="field-editable allText construction" name="isLeakageWeld" value="1" />有
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="field-editable allText construction" name="isLeakageWeld" value="0" checked="checked"/>无
				      						 </label>
			   							</div>
										<!-- <div>
							                <input type="text" class="form-control input-sm" id="isLeakageWeld"   name="isLeakageWeld"  data-parsley-required="true" />
							            </div> -->
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="isCurlDefect">是否有缺陷</label>
										<div>
											<label>
				       							<input type="radio" class="field-editable allText construction" name="isCurlDefect" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="field-editable allText construction" name="isCurlDefect" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 electricMelt">
										<label class="control-label" for="isComplete">管材管件完整</label>
										<div>
											<label>
				       							<input type="radio" class="field-editable allText construction" name="isComplete" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="field-editable allText construction" name="isComplete" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
									</div>
									
									<div class="form-group col-md-6 col-sm-12 electricMelt">
										<label class="control-label" for="isCoaxial">是否同轴</label>
										<div>
											<label>
				       							<input type="radio" class="field-editable allText construction" name="isCoaxial" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="field-editable allText construction" name="isCoaxial" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<!-- <div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div> -->
									</div>
									<div class="form-group col-md-6 col-sm-12 electricMelt">
										<label class="control-label" for="isScratch">是否有刮痕</label>
										<div>
											<label>
				       							<input type="radio" class="field-editable allText construction" name="isScratch" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="field-editable allText construction" name="isScratch" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 electricMelt">
										<label class="control-label" for="isHoleCharging">观察孔冒料</label>
										<div>
											<label>
				       							<input type="radio" class="field-editable allText construction" name="isHoleCharging" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="field-editable allText construction" name="isHoleCharging" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 electricMelt">
										<label class="control-label" for="isHaveMelts">是否有熔融物</label>
										<div>
											<label>
				       							<input type="radio" class="field-editable allText construction" name="isHaveMelts" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="field-editable allText construction" name="isHaveMelts" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
									</div>
									<!-- 1。电熔 -->
									<div id="electricMeltListTableDiv" class="recordTable">
			                       	<table id="weldLineInpectElectricMeltListTable" class="table table-striped table-bordered nowrap electricMeltTable " width="100%">
			                            <thead class="">
			                                <tr>
			                                    <th>操作</th>
			                                    <th>焊缝编号</th>
			                                    <th>管材管件完整</th>
			                                    <th>是否有熔融物</th>
			                                    <th>是否同轴</th>
			                                    <th>是否有刮痕</th>
			                                    <th>观察孔冒料</th>
			                                    <th>施工日期</th>
			                                </tr>
			                            </thead>
			                            
			                       	</table>
			                       	</div>
			                       	
									<!-- 2.热熔 -->
		                   			<div id="hotMeltListTableDiv"  class="recordTable">
		                   			
			                       	<table id="weldLineInpectHotMeltListTable" class="table table-striped table-bordered nowrap hotMeltTable" width="100%">
			                            <thead >
			                                <tr>
			                                    <th>操作</th>
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
			                       	</div>
			                       	
		                       	</form>
		                       	
		                       	 	<!-- 照片 -->
		                       	<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list hidden" id="recordImagesList">
										<input type="hidden" id="peWliId1" />
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
			    <div class="panel-body" id="altimetric_survey_audit_panel_box">
	                 <div class="clearboth form-box">
	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 1123px; height: 900px;border:1; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('PE管焊缝检查- 工程项目管理系统 ');
    $("#signForm").toggleEditState().styleFit();
    $("#auditForm").toggleEditState().styleFit();
    
    $('.update-show').addClass('hidden');
    $('.auditEditBtn').addClass('hidden');
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projAddr').val(projJson.projAddr);				   
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.cuName); 		   //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    	$('#corpName').val(projJson.corpName);				   //建设单位
    }
    
  	//签字加载方式
  	$.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	$("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5").handleSignature(); 
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
   
   //连接类型默认为热熔连接
    var meltConnectType = "1";
   //页面初始
    $("#meltConnectType").val("1");
   	$(".electricMelt").removeClass("hidden");
	$(".hotMelt").addClass("hidden");
	$("#hotMeltListTableDiv").addClass("hidden");
	$("#electricMeltListTableDiv").removeClass("hidden");
    $("#meltConnectType").on("change",function(){
    	$('#meltConnectType1').val($(this).val());
    	if($(this).val()=='2'){
    		meltConnectType="2";
    		$(".electricMelt").addClass("hidden");
    		
    		$(".hotMelt").removeClass("hidden");
    		
    		$("#hotMeltListTableDiv").removeClass("hidden");
    		$("#electricMeltListTableDiv").addClass("hidden");
    		
    	}else{
    		meltConnectType="1";
    		$(".electricMelt").removeClass("hidden");
    		$(".hotMelt").addClass("hidden");
    		$("#hotMeltListTableDiv").addClass("hidden");
    		$("#electricMeltListTableDiv").removeClass("hidden");
    	}
    	$("#auditForm .addClear").val("");
        $('.sysDate').val(getSysDate());//记录区日期
    	$("#auditForm .clear-sign").click();
    	handleRecord();
	
    	showReport();
    });
    
    $.getScript('projectjs/inspection/inspection-common.js?'+Math.random()).done(function() {
    	$.getScript('projectjs/inspection/pe-weld-line-inpect.js?'+Math.random()).done(function () {
    		peWeldLineInpect.init();
    		
		});
	});
   
  	//加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var cptPath = '<%=basePath%>';
  	//加载列表区和签字区的cpt文件
    var showReport = function(){
        var json=trSData.projectCheckListTable.json;
        var pcId = $("#pcIdNew").val();
        //列表区的cpt文件数据从trSData中获取
        if(json){
        	projName=json.projName;
       	    suName =json.suName;
            constructionUnit =json.constructionUnit;
            peWeldSpec=json.peWeldSpec;
            pipeManufactor = json.pipeManufactor;
            welderName = json.welderName;
        }else{
        	//签字区保存后不刷新页面,cpt文件中数据从签字区获取
        	projName=$('#projName').val();
    	    suName = $("#suName").val();
            constructionUnit = $("#constructionUnit").val();
            peWeldSpec= $('#peWeldSpec').val();
            pipeManufactor=$("#pipeManufactor").val(); 
            welderName = $('#welderName').val();
        }
        if(!pcId){
			pcId='-1';
		}
    	    //以下处理为解决中文乱码
    	    projName=encodeURIComponent(cjkEncode(projName));
        	suName = encodeURIComponent(cjkEncode(suName));
        	constructionUnit = encodeURIComponent(cjkEncode(constructionUnit));
        	welderName=encodeURIComponent(cjkEncode(welderName));
        	peWeldSpec=encodeURIComponent(cjkEncode(peWeldSpec));
        	pipeManufactor=encodeURIComponent(cjkEncode(pipeManufactor));
        
        var meltConnectType = $("#meltConnectType").val();
        //cpt路径及参数
        var src="";
        var status = $("#status").val();
        if(meltConnectType=='2'){
            var ur = "peWeldLineInpectHotMelt.cpt";
            //判断是否为新报验单
            if(isUpdate()){
                ur = "peWeldLineInpectHotMeltNew.cpt";
            }
        	src=cptPath+"/ReportServer?reportlet=inspection/"+ur;
        }else{
            var ur = "peWeldLineInpectElectricMelt.cpt";
            //判断是否为新报验单
            if(isUpdate()){
                ur = "peWeldLineInpectElectricMeltNew.cpt";
            }
        	src=cptPath+"/ReportServer?reportlet=inspection/"+ur;
        }
        src = src + "&pcId="+pcId+"&projName="+projName+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src); 
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
		var projId = $("#projId").val(),projNo=$('#peWliId1').val(),
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
		     if (!projNo && $("#peWliId1").length && !$("#peWliId1").val()) {
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