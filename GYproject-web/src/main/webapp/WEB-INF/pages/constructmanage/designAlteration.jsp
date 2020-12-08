<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
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
                	<ul class="nav nav-tabs ">
						<li class="active"><a href="#alteration_list" id="AlterationTab" data-toggle="tab">变更列表</a></li>
						<li class=""><a href="#alteration_record" id="AlterationInfo" data-toggle="tab">变更记录</a></li>
						<li class=""><a href="#default-tab-3" id="materialChangeTab"   data-toggle="tab">材料变更</a></li> 
					</ul>
                </div>
                <div class="panel-body">
                	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                	<div class="tab-content">
	                	<div class="tab-pane fade active in btn-top active" id="alteration_list" >
	                		<table class="table table-hover table-striped table-bordered nowrap" data-attach-table="all" id="designAlterationTable" width="100%">
	                			<thead>
		                			<tr>
		                			    <th></th>
		                				<th>变更编号</th>
		                				<th>变更类型</th>
			                			<th>日期</th>
			                			<th>变更状态</th>
			                			<th></th>
		                			    <th></th>
		                			    <th></th>
		                			</tr>
	                			</thead>
	                		</table>
	                	</div>
	                	<div class="tab-pane fade in btn-top" id="alteration_record">
		                     <div class="toolBtn f-r p-b-10 hidden" >
							     <a href="javascript:;" class="btn btn-confirm btn-sm saveBtn">保存</a>
							     <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 giveUpBtn">放弃</a>
						     </div>
							 <div class=" clearboth form-box">
								 <%--//值为2时文件格式支持多种（不影响其他页面功能写死）--%>
								<input type="hidden" id="changeType" value="2"/>
								<input type="hidden" id="loginPost" value="${post }"/>
						     	<input type="hidden" id="loginName" value="${staffName}"/> 
						     	<input type="hidden" id="sysDate" value="${sysDate}"/> 
								<input type="hidden" id="builderPost" value="${builderPost }"/>
								<input type="hidden" id="sucse" value="${sucse }"/>
								<input type="hidden" id="unitType" value="${unitType }"/>
								<input type="hidden" id="cuUnitType" value="${cuUnitType }"/>
							    <input type="hidden" type="text" id="designChangeTypeMark" name="designChangeTypeMark"/>		
							 	<form class="form-horizontal" action="designAlteration/saveDesignAlterationFile" method="POST" enctype="multipart/form-data" id="designAlterationForm" data-parsley-validate="true" >
							 		<input type="hidden" id="post" name="post" value="${post}">
							 		<input type="hidden" id="auditState" name="auditState" value = "">
									<input type="hidden" id="result" name="result">
									<input type="hidden" id="accListId" name="accListId">
									<input type="hidden" id="menuDes" name="menuDes">
									<input type="hidden" id="cmId" name="cmId" class="addClear accBusRecordId">
									<input type="hidden" id="drawUrl" name="drawUrl">
									<input type="hidden" id="drawUrl1" name="drawUrl1" value="${drawUrl1}">
									<input type="hidden" id="cmState" name="cmState" value="${cmState}" >
									<input type="hidden" name="projLtypeId" id="projLtypeId"/>
									<input type="hidden" name="stepId" id="stepId" />
									<input type="hidden" name="step" id="step" />
									<input type="hidden" name="alPath" id="alPath" />
									<input type="hidden" id="mcType"/>
									<input type="hidden" id="state"/>
									<input type="hidden" id="designChangeType" name="designChangeType"/>
									<input type="hidden" name="version"/>	<div class="form-group col-sm-6">
										<label class="control-label" for="changeType">变更类型</label>
										<div>
											<select class="form-control input-sm field-not-editable" id="changeType" name="changeType" data-parsley-required="true">
												<%--  <c:forEach var="enums" items="${changeType}">
													<option value="${enums.value}" >${enums.message}</option>
								                </c:forEach>  --%>
								                <option value="1" >工程变更</option>
											</select>
										</div>
									</div>
									<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
							 	    <input type="hidden" class="form-control input-sm field-not-editable" id="projId" name="projId">
							 	    <input type="hidden" class="form-control input-sm field-not-editable" id="projId1" name="projId1">
							 	    <div class="form-group col-md-6 col-sm-12 clearboth">
										<label class="control-label" for="">变更日期</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable datepicker-default timestamp addClear" id="cmDate" name="cmDate" data-parsley-required="true" />
										</div>
									</div>
							 	
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">变更编号</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable addClear" id="cmNo" name="cmNo" data-parsley-maxlength="30"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">工程编号</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo"  />
										</div>
									</div>
									<!-- <div class="form-group col-md-6 col-sm-12 duChange">
					     				<label class="control-label" for="">专业</label>
					    				<div> 
					         				<input type="text" class="form-control input-sm field-editable" id="major" name="major"/>
				         				</div>
						    		</div> -->
									<div class="form-group col-sm-12">
										<label class="control-label" for="">工程名称</label>
								    	<div>
											<input type="text" class="form-control input-sm field-not-editable" id="projName" name="projName"  />
									   </div>
									</div>
									<div class="form-group col-sm-12">
					                	<label class="control-label" for="">工程地点</label>
					                    <div> 
					                    	<input type="text" class="form-control input-sm field-not-editable" id="projAddr" name="projAddr"  >
					  				    </div> 
					  				</div>
					  				
									<div class="form-group col-sm-12">
											<label class="control-label" for="">工程规模</label>
											<div>
												<textarea class="form-control input-sm field-not-editable" id="projScaleDes" name="projScaleDes" rows="2" data-parsley-maxlength="200"></textarea>
										    </div>
										</div>
										<div class="form-group col-sm-12">
											<label class="control-label" for="">变更提出单位</label>
											<div>
												<input type="text" class="form-control input-sm field-editable addClear allText builder construction cuPm" id="cmAdvanceUnit" name="cmAdvanceUnit"/>
											</div>
										</div>
										<!-- <div class="form-group col-md-6 col-sm-12 duChange">
											<label class="control-label" for="">联系电话</label>
											<div>
												<input type="text" class="form-control input-sm field-editable" id="cuPhone" name="cuPhone"  />
											</div>
										</div> -->
						    		    
										<div class="form-group col-sm-12">
						     				<label class="control-label" for="">变更原因</label>
						    				<div> 
						         				<textarea class="form-control field-editable addClear allText builder construction cuPm" name="cuReason" id="cuReason" rows="2" cols="" data-parsley-maxlength="200" data-parsley-required="true"></textarea>
					         				</div>
						    		    </div>
						    		    <div class="form-group col-sm-12">
						     				<label class="control-label" for="changeContent">变更内容</label>
						    				<div> 
						         				<textarea class="form-control field-editable addClear allText builder construction cuPm" name="changeContent" id="changeContent" rows="5" cols="" data-parsley-maxlength="500" data-parsley-required="true"></textarea>
					         				</div>
						    			</div>
						    			  <div class="form-group col-sm-12  hidden cancelRemark">
 				                             <label class="control-label" for="">废弃原因</label>
		                                   <div> 
     				                         <textarea class="form-control field-editable addClear allText builder construction cuPm" name="cancelRemark" id="cancelRemark" required="required" rows="4" cols="" data-parsley-maxlength="500" ></textarea>
    			                           </div>
   		                                </div>
   		                                <div class="form-group col-md-6 col-sm-12 hidden cancelStaffName">
					                	     <label class="control-label" for="">废弃申请人</label>
					                      <div> 
					                    	<input type="text" class="form-control input-sm field-not-editable" id="cancelStaffName" name="cancelStaffName"/>
					  				      </div> 
					  				   </div>
					  				    <div class="form-group col-md-6 col-sm-12 hidden cancelDate">
					                	<label class="control-label" for="">废弃时间</label>
					                    <div> 
					                    	<input type="text" class="form-control input-sm field-not-editable" id="cancelDate" name="cancelDate"/ >
					  				    </div> 
					  				   </div>
				                  
						    		    <div class="form-group col-sm-12">
						     				<label class="control-label">附件类型</label>
						    				<div> 
						         				<label> <input type="radio" class="field-editable allText builder construction cuPm"
												name="drawFileType" value="1" />变更内容
											</label>
											 <label> <input type="radio" class="field-editable allText builder construction cuPm"
													name="drawFileType" value="2"  />变更设计图
												</label>
											 <label> <input type="radio" class="field-editable allText builder construction cuPm"
													name="drawFileType" value="3"  />相关会议纪要
												</label>
												<label> <input type="radio" class="field-editable allText builder construction cuPm"
													name="drawFileType" value="4" checked="checked" />其他
												</label>
					         				</div>
						    		    </div>
						    		    <div class="form-group col-sm-12 allText">
											<!-- <label class="control-label proj_change" for="">变更方案简图</label> -->
											<label class="control-label projectChange" for="">变更附件</label>
											<div>
											<div class="hidden hasVal"> 
							         			<input type="text" class="form-control input-sm field-not-editable hasVal" id="drawName" name="drawName"/>
							         			<a class="btn btn-sm btn-primary searchButton " href="" target="_blank">查看</a>
						         		        <!-- <a class="btn btn-sm btn-primary Search_Button" href="javascript:">查__看</a> -->
						         		        <a class="del_btn btn btn-sm btn-danger"><i class="fa fa-times"></i> 删除</a>
						         		    </div>
												<div class="fileupload-buttonbar noVal hidden">
											        <div class="pull-right toolBtn">
											            <span class="btn btn-success btn-sm fileinput-button">
											                <i class="fa fa-plus"></i>
											                <span>浏览文件...</span>
											                <input type="file" name="files[]" multiple/>	             	          
											            </span>
											            <button type="submit" class="btn btn-primary btn-sm start hidden">
										                    <i class="fa fa-upload"></i>
										                    <span>上传</span>
										                </button>
										                <a class="surplusDelBtn btn btn-sm btn-danger "><i class="fa fa-times"></i> 删除</a>
											        </div>
											    </div>
										    	<!-- The table listing the files available for upload/download -->
										    	<table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
											</div>
											</div>
						    		    <div class="projectChange">
							    		    <div class="form-group col-sm-12 clearboth">
												<label class="control-label" for="">工程数量增/减</label>
												<div> 
													<input type="text" class="form-control input-sm field-editable addClear allText builder construction cuPm" id="cmProjQuantity" name="cmProjQuantity"  data-parsley-maxlength="50"/>
												</div>
											</div>
											<div class="form-group col-sm-12 clearboth">
												<label class="control-label" for="">费用量增/减</label>
												<div>
													<input type="text" class="form-control input-sm field-editable addClear allText builder construction cuPm" id="cmCost" name="cmCost"  data-parsley-maxlength="50"/>
												</div>
											</div>
											<div class="form-group col-sm-12 clearboth">
												<label class="control-label" for="cmTimeLimit">工期变化</label>
												<div>
													<input type="text" class="form-control input-sm field-editable addClear allText builder construction cuPm" id="cmTimeLimit" name="cmTimeLimit"  data-parsley-maxlength="50"/>
												</div>
											</div>
											<div class="form-group col-md-6 col-sm-12 clearboth">
												<input type="hidden" class="form-control input-sm field-editable addClear" id="cmAdvanceStaffId" name="cmAdvanceStaffId"   data-parsley-maxlength="30"/>
												<label class="control-label" for="cmAdvanceStaffName">发起人</label>
												<div>
													<input type="text" class="form-control input-sm field-not-editable addClear" id="cmAdvanceStaffName" name="cmAdvanceStaffName"   data-parsley-maxlength="50"/>
												</div>
											</div>


											<div class="form-group col-md-6 col-sm-12 clearboth allSign construction">
												<label class="control-label signature-tool" for="">施工员</label>
												<div>
													<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
													<input type="hidden" class="sign-data-input signCheck" id="construction" name="construction" value="">
													<input type="hidden" class="signPost"  id="construction_postType" name="construction_postType" value="${constructionPost}">
													<img class="construction" alt="" src="" style="height: 30px">
													<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
												</div>
											</div>
											<div class="form-group col-md-6 col-sm-12 clearboth">
												<!-- <input type="hidden" class="form-control input-sm field-editable addClear" id="cmAdvanceStaffId" name="cmAdvanceStaffId"   data-parsley-maxlength="30"/>
												 --><label class="control-label" for="changeMaterialFlag">需设计变更材料</label>
												<div>
													<label> 
														<input type="radio" class="field-editable allText builder construction cuPm" name="changeMaterialFlag" value="1"  />是
													</label>
													<label> 
														<input type="radio" class="field-editable allText builder construction cuPm" name="changeMaterialFlag" value="0" checked="checked"/>否
													</label>
												</div>
											</div>
											<!-- div class="form-group col-md-6 col-sm-12 clearboth">
												<label class="control-label" for="cmReceiveUnit">通知部门(单位)</label>
												<div>
													<input type="text" class="form-control input-sm field-editable addClear allText" id="cmReceiveUnit" name="cmReceiveUnit"  data-parsley-maxlength="200"/>
												</div>
											</div>
											<div class="form-group  col-sm-12 clearboth">
												<label class="control-label" for="cuManagerDept">施工项目经理部</label>
												<div>
													<input type="text" class="form-control input-sm field-editable addClear allText" id="cuManagerDept" name="cuManagerDept"  data-parsley-maxlength="200"/>
												</div>
											</div> -->
										
										<div class="form-group col-md-6 col-sm-12 clearboth allSign cuPm">
											<label class="control-label signature-tool" for="">项目经理</label>
											<div>
												<%-- <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
												<input type="hidden" class="sign-data-input" id="cuPm" name="cuPm" >
												<input type="hidden" id="cuPm_postType" name="cuPm_postType" value="${cuPm }" >
												<img class="cuPm" alt="" src="" style="height: 30px">
												<span class="clear-sign"><i class="fa ion-close-circled"></i></span> --%>
												
												<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
												<input type="hidden" class="sign-data-input signCheck" id="cuPm" name="cuPm" value="">
												<input type="hidden" class="signPost"  id="cuPm_postType" name="cuPm_postType" value="${cuPm}">
												<img class="cuPm" alt="" src="" style="height: 30px">
												<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
											</div>
							    		</div>
						    		    <div class="form-group col-sm-12 ">
											<label class="control-label" for="duName">设计单位</label>
											<div>
												<input type="text" class="form-control input-sm field-not-editable" id="duName" name="duName"  />
											</div>
										</div>
										<div class="form-group col-sm-12 clearboth">
												<label class="control-label" for="">设计人员</label>
												<div>
													<input type="hidden" class="form-control input-sm field-not-editable" id="designerId" name="designerId"  data-parsley-maxlength="50"/>
													<input type="text" class="form-control input-sm field-not-editable" id="designer" name="designer"  data-parsley-maxlength="50"/>
												</div>
											</div>
										 <%-- <div class="form-group col-md-6 col-sm-12 ">
											<label class="control-label signature-tool" for="duLeader">设计负责人</label>
											<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="duLeader" name="duLeader" value="">
											<input type="hidden"  id="dduLeader_postType" name="duLeader_postType" value="${duLeaderPost }">
											<img class="duLeader" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
										</div> --%>
                                        <hr width="100%" style="height: 1px;border:none;border-bottom:1px dashed #acacac"/>
                                        <div class="form-group col-sm-12 ">
                                            <label class="control-label" for="suName">监理机构</label>
                                            <div>
                                                <input type="text" class="form-control input-sm field-not-editable" id="suName" name="suName"/>
                                            </div>
                                        </div>
                                        <div class="form-group col-lg-12 col-md-12 col-sm-12">
                                            <label class="control-label" for="">监理意见</label>
                                            <div>
                                                <textarea class="form-control field-editable allText suCse" id="suOpinion" name="suOpinion"  rows="4" data-parsley-maxlength="200"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group col-lg-12 col-md-12 col-sm-6">
                                            <label class="control-label allText suCse" for="">审核结果</label>
                                            <div>
                                                <label>
                                                    <input type="radio" class=" allText suCse" name="suAuditResult" value="1"/>通过
                                                </label>
                                                <label>
                                                    <input type="radio" class=" allText suCse" name="suAuditResult" value="0"/>未通过
                                                </label>
                                            </div>
                                        </div>
										<div class="form-group col-md-6 col-sm-12 allSign suCse">
											<label class="control-label signature-tool" for="suCes">总监理工程师</label>
											<div>
                                                <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
                                                <input type="hidden" class="sign-data-input signCheck" id="suCes" name="suCes" value="">
                                                <input type="hidden" class="signPost"  id="suCes_postType" name="suCes_postType" value="${sucse}">
                                                <img class="suCes" alt="" src="" style="height: 30px">
                                                <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										    </div>
										</div>
                                        <hr width="100%" style="height: 1px;border:none;border-bottom:1px dashed #acacac"/>
                                        <div class="form-group col-sm-12">
                                            <label class="control-label" for="corpName">建设单位</label>
                                            <div>
                                                <input type="text" class="form-control input-sm field-not-editable"
                                                       id="corpName" name="corpName"/>
                                            </div>
                                        </div>
                                        <div class="form-group col-lg-12 col-md-12 col-sm-12">
                                            <label class="control-label" for="">负责人意见</label>
                                            <div>
                                                <textarea class="form-control field-editable allText builder" id="custLeaderOpinion"  name="custLeaderOpinion" rows="4" data-parsley-maxlength="200"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group col-lg-12 col-md-12 col-sm-6">
                                            <label class="control-label allText builder" for="">审核结果</label>
                                            <div>
                                                <label>
                                                    <input type="radio" class=" allText builder" name="custLeaderAuditResult" value="1"/>通过
                                                </label>
                                                <label>
                                                    <input type="radio" class=" allText builder" name="custLeaderAuditResult" value="0"/>未通过
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 col-sm-12 allSign builder">
                                            <label class="control-label signature-tool" for="custLeader">负责人</label>
                                            <div>
                                                <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
                                                <input type="hidden" class="sign-data-input signCheck" id="custLeader" name="custLeader" value="">
                                                <input type="hidden" class="signPost"  id="custLeader_postType" name="custLeader_postType" value="${builderPost }">
                                                <img class="custLeader" alt="" src="" style="height: 30px">
                                                <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
                                            </div>
							    		</div>
							    		<div class="form-group col-sm-12  hidden duAudit">
											<label class="control-label" for="">设计院意见</label>
											<div>
												<textarea class="form-control field-editable addClear"  data-parsley-required="true" name="duOpinion" id="duOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
											</div>
										</div>
										<div class="form-group col-md-6 col-sm-12 hidden allSign duAudit">
											<label class="control-label signature-tool" for="">设计所长签字</label>
											<div>
												<a href="javascript:;" class="btn btn-sm btn-white " id="signBtn_5"><i class="fa fa-pencil"></i></a>
												<input type="hidden" class="sign-data-input signCheck" id="duPrincipal" name="duPrincipal" >
												<input type="hidden" class="signPost"  id="duPrincipal_postType" name="duPrincipal_postType" value="" >
												<img class="duPrincipal" alt="" src="" style="height: 30px">
												<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
											</div>
							    		</div>
								    	<div class="form-group col-md-6 col-sm-12 hidden duAudit">
											<label class="control-label" for="">审核日期</label>
											<div>
												<input type="text" class="form-control input-sm datepicker-default field-editable" id="auditDate" name="auditDate"  />
											</div>
										</div>
										</div>
						    		    <%-- <div class="duChange">
						    			<div class="form-group col-sm-12">
						     				<label class="control-label" for="">变更方案</label>
						    				<div> 
						         				<textarea class="form-control  field-editable" name="cuProposal" id="cuProposal" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
						         			</div>
						    			</div>
						    			<div class="form-group col-sm-12">
											<label class="control-label" for="">甲方单位意见</label>
											<div>
												<textarea class="form-control field-editable" name="custOpinion" id="custOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
											</div>
										</div>
										<div class="form-group col-md-6 col-sm-12">
											<label class="control-label signature-tool" for="">签字</label>
											<div>
												<a href="javascript:;" class="btn btn-sm btn-white " id="signBtn_2"><i class="fa fa-pencil"></i></a>
												<input type="hidden" class="sign-data-input" id="custPrincipal" name="custPrincipal" >
												<input type="hidden" id="custPrincipal_postType" name="custPrincipal_postType" value="" >
												<img class="" alt="" src="" style="height: 30px">
												<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
											</div>
							    		</div>
										
									    <div class="form-group col-sm-12">
											<label class="control-label" for="">监理意见</label>
											<div>
												<textarea class="form-control field-editable" name="suOpinion" id="suOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
											</div>
										</div>
										<div class="form-group col-md-6 col-sm-12">
											<label class="control-label signature-tool" for="">签字</label>
											<div>
												<a href="javascript:;" class="btn btn-sm btn-white " id="signBtn_1"><i class="fa fa-pencil"></i></a>
												<input type="hidden" class="sign-data-input" id="suPrincipal" name="suPrincipal" >
												<input type="hidden" id="suPrincipal_postType" name="suPrincipal_postType" value="${suPrincipal}" >
												<img class="suPrincipal" alt="" src="" style="height: 30px">
												<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
											</div>
							    		</div>
									    <div class="form-group col-sm-12">
											<label class="control-label" for="">甲方代表</label>
											<div>
												<textarea class="form-control field-editable" name="managementOpinion" id="managementOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
											</div>
										</div>
										<div class="form-group col-md-6 col-sm-12">
											<label class="control-label signature-tool" for="">签字</label>
											<div>
												<a href="javascript:;" class="btn btn-sm btn-white " id="signBtn_3"><i class="fa fa-pencil"></i></a>
												<input type="hidden" class="sign-data-input" id="cmoPrincipal" name="cmoPrincipal" >
												<input type="hidden" id="cmoPrincipal_postType" name="cmoPrincipal_postType" value="${cuPm }" >
												<img class="cmoPrincipal" alt="" src="" style="height: 30px">
												<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
											</div>
							    		</div>
						    		</div> --%>
								</form>
							</div>
						</div>
						 <div class="tab-pane fade in btn-top" id="default-tab-3">
					        <div id="materialChangebox">
					        	<form id="exportExcel" action="changeRecord/exportExcel">
					        	</form>
						        <form action="" id="materialListForm">
							         <table id="materialListTable" class="table table-striped table-bordered nowrap " width="100%">
					            		<thead>
					                		<tr>
						                		<th></th>			                		 
						                		<th>材料名称</th>
								                <th>材料型号</th>
								                <th>单位</th>
								                <th>是否有物资购买</th>
								                <th width="120">调整量</th>
					                		</tr>
						               	</thead>
					    	  	  	 </table>
				    	  	  	 </form>
	           	       		</div>
	           	       </div>
					</div>
            	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
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
			        <div class="iframe-big-box ">
			        	<div class="iframe-report-box ">
		                 	<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:0; overflow-y:auto;" scrolling="no"></iframe>
		                </div>
			        </div>
			    </div>
			</div>
        </div>
      <!-- end col-6 -->
    </div>
</div>

<!-- end #content -->
<!-- end #content -->
<script id="template-upload" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-upload fade">
            <td class="col-md-1 hidden">
                <span class="preview"></span>
            </td>
            <td width="60%">
                <p class="name filename">{%=file.name%}</p>
                <strong class="error text-danger"></strong>
            </td>
            <td width="20%">
                <p class="size">Processing...</p>
            </td>
            <td width="20%">
                <div class="progress progress-striped active"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
            </td>
            <td class="hidden">
                {% if (!i && !o.options.autoUpload) { %}
                    <button class="btn btn-primary btn-sm start" disabled>
                        <i class="fa fa-upload"></i>
                        <span>Start</span>
                    </button>
                {% } %}
                {% if (!i) { %}
                    <button class="btn btn-white btn-sm cancel">
                        <i class="fa fa-ban"></i>
                        <span>Cancel</span>
                    </button>
                {% } %}
            </td>
        </tr>
    {% } %}
</script>
<script id="template-download" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-download fade">
            <td class="hidden">
                <span class="preview">
                    {% if (file.thumbnailUrl) { %}
                        <!--<a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>-->
                    {% } %}
                </span>
            </td>
            <td width="60%">
                <p class="name">
                    {% if (file.url) { %}
                        <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                    {% } else { %}
                        <span>{%=file.name%}</span>
                    {% } %}
                </p>
                {% if (file.error) { %}
                    <div><span class="label label-danger">Error</span> {%=file.error%}</div>
                {% } %}
            </td>
            <td width="20%">
                <span class="size">{%=o.formatFileSize(file.size)%}</span>
            </td>
            <td width="20%">
                <div class="progress progress-striped text-center"><div class="progress-bar progress-bar-success" style="width:100%;">已上传</div></div>
            </td>
            <td class="hidden">
                {% if (file.deleteUrl) { %}
                    <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                        <i class="glyphicon glyphicon-trash"></i>
                        <span>Delete</span>
                    </button>
                    <input type="checkbox" name="delete" value="1" class="toggle">
                {% } else { %}
                    <button class="btn btn-warning cancel">
                        <i class="glyphicon glyphicon-ban-circle"></i>
                        <span>Cancel</span>
                    </button>
                {% } %}
            </td>
        </tr>
    {% } %}
</script>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
//  //初始化上传文件控件
    $.getScript('projectjs/data/form-multiple-upload.demo3.js').done(function() {
    	FormMultipleUpload.init($('#designAlterationForm'));
    });
    App.setPageTitle('设计变更 - 工程管理系统');
    $("#designAlterationForm").styleFit();
    $("#designAlterationForm").toggleEditState(false);
    $("#mcType").val('0');
 	//是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$("#projId").val(projJson.projId);
    	$("#projAddr").val(projJson.projAddr);
    	$("#projName").val(projJson.projName);
    	$("#projNo").val(projJson.projNo);
    	$("#corpName").val(projJson.corpName);
    	$("#duName").val(projJson.duName);
    	$("#suName").val(projJson.suName);
    	$("#constructionUnit").val(projJson.cuName);
    	$("#cmReceiveUnit").val(projJson.duName);
    	$("#cmAdvanceUnit").val(projJson.cuName);
    }
 	function saveBack(data){
        $(".saveBtn").parent().parent().hideMask();
		var content = "数据保存成功！";
		if(data.result === "false"){
			content = "数据保存失败！";
		}else if(data.result === "already"){
			content = "此信息已被修改，请刷新页面！";
		}else {
			$("#cmId").val(data.operateId);
			$(".toolBtn").addClass("hidden");
		}
		var myoptions = {
				title: "提示信息",
				content: content,
				accept: savedone,
				chide: true,
				icon: "fa-check-circle"
		   }
		$("body").cgetPopup(myoptions);
 	}
 
    $("#designAlterationForm").styleFit();
    $('.CnChange').addClass('hidden');
    //日期
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });
    //签字加载方式
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6,#signBtn_7");
    	signatures.handleSignature();        	    	
    });
    //帆软 路径
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %> 
    var cptPath = '<%=basePath%>';
    //加载打印预览/ 施工变更 改为工程变更
    var showReport = function(){
    	cmId =$("#cmId").val(),	
    	imgUrl = encodeURIComponent(cjkEncode($(".imgUrl").val())),	
    	drawUrl1 = encodeURIComponent(cjkEncode($("#drawUrl1").val())),
    	projId = encodeURIComponent(cjkEncode($("#projId").val())),
	    projNo = encodeURIComponent(cjkEncode($("#projNo").val())),		//工程编号
	    cmNo=encodeURIComponent(cjkEncode($("#cmNo").val())),				//变更编号
		projName=encodeURIComponent(cjkEncode($("#projName").val())),			    //工程名称
		constructionUnit=encodeURIComponent(cjkEncode($("#constructionUnit").val())),//施工单位
		projAddr=encodeURIComponent(cjkEncode($("#projAddr").val())),			  //施工地点
		projScaleDes=encodeURIComponent(cjkEncode($("#projScaleDes").val())),	  //工程规模
		cmDate=encodeURIComponent(cjkEncode($("#cmDate").val())),			  //变更日期
		cuPm=encodeURIComponent(cjkEncode($("#cuPm").val())),//项目经理
		cuPhone=encodeURIComponent(cjkEncode($("#cuPhone").val())),//联系电话
		cuReason=encodeURIComponent(cjkEncode($("#cuReason").val())),//变更原因
		cuProposal=encodeURIComponent(cjkEncode($("#cuProposal").val())),//变更方案
		managementOpinion=encodeURIComponent(cjkEncode($("#managementOpinion").val()));//项目部意见
		suOpinion=encodeURIComponent(cjkEncode($("#suOpinion").val()));//监理意见
		custOpinion=encodeURIComponent(cjkEncode($("#custOpinion").val()));//建议方意见
		menuDes=encodeURIComponent(cjkEncode($("#menuDes").val()));//建议方意见
		src = "<%=basePath%>/ReportServer?reportlet=constructmanage/designerAlterProject.cpt&projNo="+projNo+"&cmNo="+cmNo+"&projName="+projName+"&constructionUnit="+constructionUnit;
		src = src+"&projAddr="+projAddr+"&projScaleDes="+projScaleDes+"&cmDate="+cmDate+"&cuPhone="+cuPhone+"&cuReason="+cuReason+ "&imgUrl="+imgUrl;
		src = src+"&cuProposal="+cuProposal+"&managementOpinion="+managementOpinion+"&suOpinion="+suOpinion+"&custOpinion="+custOpinion+"&cmId="+cmId+"&projId="+projId+"&drawUrl1="+drawUrl1+"&menuDes="+menuDes;
		$("#mainFrm").attr("src",src);

    };
    showReport();
  //加载打印预览/ 用户变更  
    var showReportUser = function(){
    	cmId =$("#cmId").val(),	
    	drawUrl1 = encodeURIComponent(cjkEncode($("#drawUrl1").val())),
    	imgUrl = encodeURIComponent(cjkEncode($(".imgUrl").val())),	
    	projId = encodeURIComponent(cjkEncode($("#projId").val())),
	    projNo = encodeURIComponent(cjkEncode($("#projNo").val())),		        //工程编号
	    cmNo=encodeURIComponent(cjkEncode($("#cmNo").val())),				    //变更编号
		projName=encodeURIComponent(cjkEncode($("#projName").val())),	        //工程名称
		projAddr=encodeURIComponent(cjkEncode($("#projAddr").val())),			//施工地点
		projScaleDes=encodeURIComponent(cjkEncode($("#projScaleDes").val())),	        //工程规模
		cmDate=encodeURIComponent(cjkEncode($("#cmDate").val())),			    //变更日期
		cuReason=encodeURIComponent(cjkEncode($("#cuReason").val())),              //变更原因
		changeContent=encodeURIComponent(cjkEncode($("#changeContent").val())),        //变更内容
		designer=encodeURIComponent(cjkEncode($("#designer").val())),             //设计人
		corpName=encodeURIComponent(cjkEncode($("#corpName").val())),             //申报单位
		major=encodeURIComponent(cjkEncode($("#major").val())),                   //专业
		menuDes=encodeURIComponent(cjkEncode($("#menuDes").val()));//建议方意见
		console.info("cmiD..."+cmId);
    	//src = "<%=basePath%>/ReportServer?reportlet=constructmanage/designAlterationUser.cpt&projNo="+projNo+"&cmNo="+cmNo+"&projName="+projName+"&projAddr="+projAddr;
    	src = "<%=basePath%>/ReportServer?reportlet=constructmanage/designerAlterProject.cpt&projNo="+projNo+"&cmNo="+cmNo+"&projName="+projName+"&projAddr="+projAddr;
    	src = src+"&cmDate="+cmDate+"&cuReason="+cuReason+"&changeContent="+changeContent+"&corpName="+corpName+"&major="+major+"&cmId="+cmId+"&projId="+projId+ "&imgUrl="+imgUrl+"&drawUrl1="+drawUrl1+"&menuDes="+menuDes;
    	$("#mainFrm").attr("src",src); 
    };

    //打印乱码解决
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
    
    $.getScript('assets/plugins/DataTables/media/js/jquery.dataTables.js').done(function () {
        $.getScript('assets/plugins/DataTables/media/js/dataTables.bootstrap.min.js').done(function () {
            $.getScript('assets/plugins/DataTables/extensions/Buttons/js/dataTables.buttons.min.js').done(function () {
                $.getScript('assets/plugins/DataTables/extensions/Buttons/js/buttons.bootstrap.min.js').done(function () {
                    $.getScript('assets/plugins/DataTables/extensions/Buttons/js/buttons.print.min.js').done(function () {
                        $.getScript('assets/plugins/DataTables/extensions/Buttons/js/buttons.flash.js').done(function () {
                            $.getScript('assets/plugins/DataTables/extensions/Buttons/js/buttons.html5.min.js').done(function () {
                                $.getScript('assets/plugins/DataTables/extensions/Buttons/js/buttons.colVis.min.js').done(function () {
                                    $.getScript('assets/plugins/DataTables/extensions/Select/js/dataTables.select.min.js').done(function() {
                                        $.getScript('assets/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js').done(function () {
                                        	$.getScript('assets/plugins/jquery-deserialize-master/src/jquery.deserialize.min.js').done(function () {
	                                        	$.getScript('assets/plugins/parsley/dist/parsley.js').done(function () {
	                                        		 $.getScript('projectjs/constructmanage/design-alteration.js?'+Math.random()).done(function () {
	                     		                    	designAlteration.init();
	                                                 });
	                                        	 });
                                        	});
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });
        });
    });

    
  
   	//删除附件列表记录
    	$(".del_btn").on("click",function(){
   		$("body").cgetPopup({
			title: '提示',
			content: '您确定删除该文件信息吗？',
		    accept: {
				func: deleteDone,	//函数名
				singleArgs: $(this)	//参数，用于向func传入多个参数，不设置该属性或设置为[]，则传入不带参数的函数
			}
    	});
  		});
    function deleteDone(){
    	$("#drawName").val("");
    	$(".hasVal").addClass("hidden");
    	$(".noVal").removeClass("hidden");
    	
    }
    $('.Search_Button').off("click").on("click",function(){
		$("body").cgetPopup({
			title: '查看签名',
			content: '<div class="preview-box"><img src="attachments/' +$("#drawUrl").val() + '" class="preview-image"></div>',
			accept: function(){},
			atext: '关闭',
			chide: true,
			size: 'large',
			icon: 'fa-pencil-square-o'
		});
    });
    //保存
    $('.saveBtn').off("click").on("click",function(){ 
    	//当点击废弃，跳转保存
        if($("#designChangeTypeMark").val()=="-1"){
        	$('#designChangeType').val('-1');
    		$("body").cgetPopup({
    			title: '提示',
    			content: '你确定要废弃此条记录吗？',
    		    accept:discardSave
    		}); 
        }else{
        	 $('#designChangeType').val('1');
        	 $("#cancelDate").val("");
     		 $("#cancelStaffName").val("");  //不是废弃保存时，令废弃时间和废弃人姓名为空
     		saveDone();  //增加、修改保存
        }
    });
    var  saveDone = function(){
    	//判断是否需要设计变更材料
    	var changeMaterialFlag =$('input[type="radio"][name="changeMaterialFlag"]:checked').val();
    	var content = "你确定不需要设计变更材料吗？";
    	if(changeMaterialFlag=='1'){
    		content = "你确定需要设计变更材料吗？";
    	}
    	var popoptions = {
   				title: '提示',
   				content: content,
   				accept: discardSave,
   				//chide:true,
   				icon: "fa-check-circle",
   				newpop:'second',
       		};
       		$("body").cgetPopup(popoptions);
    }
    var discardSave = function(){
      	//验证
    	if($("#designAlterationForm").parsley().isValid()){
            $(".saveBtn").parent().parent().loadMask("正在保存...", 1, 0.5);
    		//序列化
	       	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
	       	$("#menuDes").val(menuDesc);
	       	$("#stepId").val(menuDesc);
	       	$("#step").val(getStepId());
	       	if($("#designChangeType").val()==='1'){
	       		$("#auditState").val('2');
	       	}else{
	       		$("#auditState").val('3');
	       	}
	        //表单序列化获取json字符串
            var data = $("#designAlterationForm").serializeJsonString();
            $("#result").val(data);
    		if($(".noVal+#filePreviews tr").length){
    			
    			if($(".noVal+#filePreviews tr").length>1){
         		   var popoptions = {
    	       				title: '提示',
    	       				content: '一次只能上传一张简图或附件，请重新上传！',
    	       				accept: surplusAccept
        	       		};
        	       		$("body").cgetPopup(popoptions);
         	   }else{
         		   $(".start").click();
         	   }
    		}else{
    			var dataJson={};
                dataJson.result=data;
    			$.ajax({
    	               type: 'POST',
    	               url: 'designAlteration/saveDesignAlteration',
    	               contentType: "application/json;charset=UTF-8",
    	               data:JSON.stringify(dataJson),
    	               success: function (data) {
                           $(".saveBtn").parent().parent().hideMask();
    	               	var content = "数据保存成功！";
    	               	if(data === "false"){
    	               	   content = "数据保存失败！";
    	               	}else if(data === "already"){
    	               		content = "此信息已被修改，请刷新页面！";
    	               	}else{
    	               		$("#cmId").val(data);
    	               		$(".toolBtn").addClass("hidden");
    	               		$("#designAlterationTable").cgetData();  //列表重新加载
    	               	}
    	               	var myoptions = {
    	                       	title: "提示信息",
    	                       	content: content,
    	                       	accept: savedone,
    	                       	chide: true,
    	                       	icon: "fa-check-circle"
    	                   }
    	              
    	                   $("body").cgetPopup(myoptions);
    	               },
    	               error: function (jqXHR, textStatus, errorThrown) {
    	                   console.warn("变更信息保存失败！");
    	               }
    	           });


    		}
            $(".saveBtn").parent().parent().hideMask();
        //如果通过验证, 则移除验证UI
       	$("#designAlterationForm").parsley().reset();
       }else{
       	//如果未通过验证, 则加载验证UI
       	$("#designAlterationForm").parsley().validate();
       }  
    	$(".daEditBtn").addClass("hidden");
    	$('#designAlterationTable tr.selected').removeClass("selected");
		$("#designAlterationForm").toggleEditState(false);
//     	$('ul.nav>li').removeClass("active");
     // $('#AlterationTab').click();
    //	$("#designAlterationForm").cformSave('designAlterationTable',saveSurveyBack,true);
    }
    //var saveSurveyBack=function(){}
    //点击放弃
  	var savedone = function(){
     	 if($('#designChangeType').val()=="2"){
      		showReportUser();
          }else{
      		showReport();
      	  }
     	 if($("#drawName").val()){
     		 $(".hasVal").removeClass("hidden");
     		 $(".noVal").addClass("hidden");
     		 $(".noVal+#filePreviews tr").remove();
     	 }else{
     		 $(".noVal").removeClass("hidden");
     		 $(".hasVal").addClass("hidden");
     	 }
     	  $("#designAlterationForm").styleFit();
     	 $(".searchButton").attr("href","/accessoryCollect/openCoFile?busRecordId="+$("#cmId").val());
 	    if($('#designChangeType').val()=="2"){
 	    	
 	    	$(".Search_Button").removeClass("hidden");
 	    	$(".searchButton").addClass("hidden");
 	    }else{
 	    	$(".searchButton").removeClass("hidden");
 	    	$(".Search_Button").addClass("hidden");
 	    }
 	    $("#state").val("2");
 	    
 	    sureClose();
 	    
     	}
    $('.giveUpBtn').off().on('click',function(){
    	//$('ul.nav>li').removeClass("active");
		$('#AlterationTab').click();
    })
 //   变更类型
    $("#designChangeType").change(function(){
            if($(this).val()=="2"){
            	//用户类型
            	$(".duChange").removeClass('hidden');
            	$(".projectChange").addClass('hidden');
	        	showReportUser();
            }else{
            	//工程变更
            	$(".duChange").addClass('hidden');
    	    	$(".projectChange").removeClass('hidden');
            	showReport();
            }
            $("#designAlterationForm").styleFit(true);
            
    	});
  	//限制上传多个附件
	var surplusAccept=function(){
		$(".surplusDelBtn").removeClass("hidden");
		$("#filePreviews tbody").html("");
	}
	
	//上传多个附件后删除
	$(".surplusDelBtn").off().on("click",function(){
		if($(".noVal+#filePreviews tr").length>0){
			$("#filePreviews tbody").html("");
		}else{
			alertInfo("没有要删除的附件！");
		}
	});
	
	
   	if(_inApk){
   		$("#AlterationTab").text("列表区");
 		$("#AlterationInfo").text("记录区");
 		$("#materialChangeTab").text("变更区");
   	 }else{
  		$("#AlterationTab").text("变更列表");
  		$("#AlterationInfo").text("变更记录");
  		$("#materialChangeTab").text("材料变更");
   	 }
   	
   	
   	var sureClose=function(){
   		var cwId=$("#cmId").val();
		$.ajax({
			type:'post',
			url:'designAlteration/saveSignNotice',
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
   	
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->