<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- altimetricSurvey.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />

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
                    <input type="hidden" id="pcIdNew">
					<input type="hidden" id="businessOrderId" name="businessOrderId">
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表区</a></li>
		                <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
		                <li class="" id="addTab"><a href="#default-tab-2" id="signTab"  data-toggle="tab">签字区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" id="">
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
						    	<%--//值为2时文件格式支持多种（不影响其他页面功能写死）--%>
								<input type="hidden" id="changeType" value="2"/>
								<form class="form-horizontal" id="signForm"  enctype="multipart/form-data"  data-parsley-validate="true" action="${actionName }/saveSignFile">
									<input type="hidden" id="insDate" name="insDate">
									<input type="hidden" id="status" name="status">
									<input type="hidden" id="sendDate" name="sendDate">
									<input type="hidden" id="upgradeDate" name="upgradeDate" value="${upgradeDate}">
								     <input type="hidden" id="pcDesId" name="pcDesId" value="${checkType}"/>
									<input type="hidden" id="pcId" name="pcId" class="addClear"/>
									<input type="hidden" id="version" name="version" class="addClear"/>
									<input type="hidden" id="projId" name="projId" >
								 	<input type="hidden" id="recordsId" name="recordsId">
								 	<input type="hidden" id="finishedDate" name="finishedDate">
									<input type="hidden" id="finishedOpr" name="finishedOpr">
									<input type="hidden" id="resetDate" name="resetDate">
									<input type="hidden" id="resetReason" name="resetReason">
									<input type="hidden" id="isFinishSign" name="isFinishSign">
								 	<!-- 完成标记 -->
									<input type="hidden" id="flag" name="flag">
									<!-- 附件路径 -->
									<input type="hidden" id="alPath" name="alPath">
								    <input type="hidden" id="process" name="process" value="${process }">
								    <input type="hidden" id="result" name="result">
									<input type="hidden" id="menuDes" name="menuDes">
									<input type="hidden" id="menuId" name="menuId">
									 <!-- 缩略图路径 -->
									<input type="hidden" id="drawUrl" name="drawUrl" value="${drawUrl }">
									<input type="hidden" id="width" name="width"/>
									<input type="hidden" id="height" name="height"/>
									<input type="hidden" id="stepId" name="stepId"/>
									<input type="hidden" id="step" name="step"/>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="projNo">工程编号</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projNo" name="projNo"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projName" name="projName"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="projAddr">工程地点</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projAddr" name="projAddr"  />
										</div>
									</div>
									<!-- 
									<div class="form-group  col-sm-12">
										<label class="control-label" for="designUnit">设计单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  id="designUnit" name="designUnit"  />
										</div>
									</div> -->
									<div class="form-group col-sm-12 drawDiv">
										<label class="control-label " for="drawName">示意图</label>
										<div>
											<div class="hidden hasVal">
												<input type="text" class="form-control input-sm field-not-editable" id="drawName" name="layoutDiagram" class="addClear"/>
												 <a class="btn btn-sm btn-primary searchButton " href="" target="_blank">查看</a>
												 <!-- <a class="btn btn-sm btn-primary Search_Button" href="javascript:">查看</a>  -->
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
													<a class="surplusDelBtn btn btn-sm btn-danger hidden"><i class="fa fa-times"></i> 删除</a>
												</div>
											</div>
											<!-- The table listing the files available for upload/download -->
											<table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectionDate">报验日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText construction testLeader"  id="inspectionDate" name="inspectionDate"  data-parsley-required="true"/>
										</div>
									</div>
									
									
									<div class="form-group  col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear allText construction testLeader" name="inspectionResult" id="inspectionResult" rows="3" cols="" data-parsley-maxlength="200" ></textarea>
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
											<%--<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>--%>
											<%--<input type="hidden" class="sign-data-input" id="fieldsRepresent" name="fieldsRepresent" value="">--%>
											<%--<input type="hidden"  class="signPost" id="fieldsRepresent_postType" name="fieldsRepresent_postType" value="${fieldsRepresentPost }">--%>
											<%--<img class="fieldsRepresent" alt="" src="" style="height: 30px">--%>
											<%--<span class="clear-sign"><i class="fa ion-close-circled"></i></span>--%>
										<%--</div>--%>
						    		<%--</div>--%>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate strengDate">
										<label class="control-label" for="fieldsRepresentDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText builder" value="" id="fieldsRepresentDate" name="fieldsRepresentDate"  />
										</div>
									</div>
									<div class="form-group  col-sm-12">
										<label class="control-label" for="suName">监理单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm" id="suName" name="suName"  />
										</div>
									</div>
									<%--<div class="form-group col-md-6 col-sm-12 allSign suJgj">--%>
										<%--<label class="control-label signature-tool" for="suJgj">监理员</label>--%>
										<%--<div>--%>
											<%--<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>--%>
											<%--<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">--%>
											<%--<input type="hidden" class="signPost"  id="suJgj_postType" name="suJgj_postType" value="${suJgjPost }">--%>
											<%--<img class="suJgj" alt="" src="" style="height: 30px">--%>
											<%--<span class="clear-sign"><i class="fa ion-close-circled"></i></span>--%>
										<%--</div>--%>
						    		<%--</div>--%>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate strengDate">
										<label class="control-label" for="suJgjSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText suJgj" value="" id="suJgjSignDate" name="suJgjSignDate"  />
										</div>
									</div>
									
									<div class="form-group  col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm" id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign construction">
										<label class="control-label signature-tool sign-require sign-all" for="builder">施工员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
											<input type="hidden" class="signPost"  id="builder_postType" name="builder_postType" value="${construction }">
											<img class="builder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label" for="builderSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText construction" value="" id="builderSignDate" name="builderSignDate"  />
										</div>
									</div>
									
					  			    
									<div class="form-group col-md-6 col-sm-12 allSign testLeader">
										<label class="control-label signature-tool sign-require sign-all" for="operator">操作员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="operator" name="operator" value="">
											<input type="hidden" class="signPost"  id="operator_postType" name="operator_postType" value="${operatorPost }">
											<img class="operator" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label" for="operatorSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText testLeader"  id="operatorSignDate" name="operatorSignDate"  />
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
			                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole   auditInpectBtn">报验</a>
			                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole hidden  auditAddBtn2 ">新增</a> 
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole  auditSavebtn2">保存</a>
						   		 <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole  deleteBtn2">删除</a>
						    </div>
						    <div class="clearboth form-box">
						    	<!-- 报验完成状态 -->
						    	<input type="hidden" id="flag1"  class="addClear">
		                   		<form class="form-horizontal" id="auditForm" data-parsley-validate="true" action="">
		                   			<input type="hidden" name="crId" id="crId" class="addClear"/>
		                   			<input type="hidden" name="version" id="version" class="addClear"/>
		                   			<input type="hidden" id="recordPcId" name="pcId" class="addClear"/>
		                   			<input type="hidden" id="isFinishSign" name="isFinishSign" class="addClear">
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="pipeSectionNo">管段编号</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear  allText testLeader" id="pipeSectionNo"   name="pipeSectionNo"  data-parsley-required="true" data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="pileNo">桩号</label>
										<div>
											<input type="text" class="form-control  input-sm field-editable addClear  allText testLeader" id="pileNo"   name="pileNo"   data-parsley-maxlength="30"/>
							              </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="crSpec">规格</label>
										<div>
							                <input type="text" class="form-control input-sm field-editable addClear allText testLeader" id="crSpec"   name="crSpec"   data-parsley-maxlength="50"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="crLen">长度(M)</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable text-right addClear allText testLeader" id="crLen"   name="crLen"   data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-12 col-sm-12">
										<label class="control-label" for="crSituation">清扫情况</label>
										<div>
							                <textarea class="form-control  input-sm field-editable addClear allText testLeader" rows="1" id="crSituation"   name="crSituation"   data-parsley-maxlength="200"></textarea>
							            </div>
									</div>
									<!-- <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checker ">检查人</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear" name="checker" id="checker"  data-parsley-maxlength="200"/>
							             </div>
									</div> -->
										<div class="form-group col-md-12 col-sm-12 clearboth">
										<label class="control-label" for="remarks">备注</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear allText testLeader" id="remarks"  name="remarks"   data-parsley-maxlength="200"/>
							           </div>
									</div>
									  <div class="form-group col-sm-6 col-xs-12 allSign testLeader">
							            <label class="control-label signature-tool sign-require" for="checker">检查人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
										 	<input type="hidden" class="sign-data-input" id="checker" name="checker" value="">
											<input type="hidden"  id="checker_post" name="builder_post" value="${checkerPost }">
											<img class="checker" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
							        </div> 
		                   		
		                   			
		                   			<!-- <div class="form-group col-xs-12 "><i class="fa fa-angle-double-right"></i>强度试验 </div> -->
			                       	<table id="recordListTable" class="table table-striped table-bordered nowrap " width="100%">
			                            <thead >
			                                <tr>
			                                	<th>操作</th>
			                                    <th>管段编号</th>
			                                    <th>桩号</th>
			                                    <th>规格</th>
			                                    <th>长度(M)</th>
			                                    <th>清扫记录</th>
			                                    <th>备注</th>
			                                </tr>
			                            </thead>
			                            
			                       	</table>
			                      
		                       	</form>
		                       	
		                       	<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list hidden" id="recordImagesList">
										<input type="hidden" id="crId1"/>
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
            <div class="panel panel-inverse" id="content">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <h4 class="panel-title">预览区</h4>
			    </div>
			    <div class="panel-body " id="purge_panel_box">
	                 <div class="clearboth form-box " >
	                 	<!-- <div class="iframe-report-box hidden" id="RecordReportBox">
	                  		<iframe id="mainFrmRecord" class="iframe-report " style="width:1100px; height:795px;border:1; overflow-y:auto;" scrolling="no"></iframe>
	                  	</div> -->
	                 	<div class="iframe-report-box " id="">
	                  		<iframe id="mainFrm" class="iframe-report " style="width:1123px;height:794px;border:1; overflow-y:auto;" scrolling="no"></iframe>
	                  	</div>
	               </div>
			    </div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


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
        FormMultipleUpload.init($('#signForm'));
    });
   
    App.setPageTitle('清扫记录- 工程项目管理系统 ');
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
    	$('#constructionUnit').val(projJson.cuName); //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    	$("#corpName").val(projJson.corpName);
    	$("#designUnit").val(projJson.duName);
    	$("#projAddr").val(projJson.projAddr);
    	//附件路径
	   	 $("#alPath").val(getProjectInfo().projNo+"/"+getStepId());
	   	 $("#stepId").val($('[data-mid="' + getStepId() + '"]').text());
	   	 $("#step").val(getStepId());
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
    $.getScript('projectjs/inspection/inspection-common.js?'+Math.random()).done(function() {
 	   $.getScript('projectjs/inspection/clear-record.js?'+Math.random()).done(function () {
    		clearRecord.init();
 	   });
	});
    
  //图片查看功能
    $('.Search_Button').off("click").on("click",function(){  
    	console.info("图片查看功能 drawurl:"+$("#drawUrl").val());
    	$("body").cgetPopup({ 
			title: '查看图片',
			content: '<div class="preview-box"><img src="attachments/' +$("#drawUrl").val() + '" class="preview-image"></div>',
			accept: function(){},
			atext: '关闭',
			chide: true,
			size: 'large',
			icon: 'fa-pencil-square-o'
		});
    });
	
  	//加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var cptPath = '<%=basePath%>';
  	//加载列表区记录报表
    var showReport = function(){
  		var menuDesc = $("#menuDes").val();
        var json=trSData.projectCheckListTable.json;
        //console.log(json);
        var pcId = $('#pcIdNew').val();
        //列表区的cpt文件数据从trSData中获取
        if(json){
        	projNo = json.projNo;
        	projId = json.projId;
        	projName=json.projName;
       	    suName =json.suName;
            constructionUnit =json.constructionUnit;
            corpName = json.corpName;
        }else{
        	//签字区保存后不刷新页面,cpt文件中数据从签字区获取
        	projNo = $('#projNo').val();
        	projId = $('#projId').val();
        	projName=$('#projName').val();
    	    suName = $("#suName").val();
            constructionUnit = $("#constructionUnit").val();
            corpName = $("#corpName").val();
           // pcId = $("#pcId").val();
        }
		if(!pcId){
			pcId='-1';
		}
    	    //以下处理为解决中文乱码
    	    projName=encodeURIComponent(cjkEncode(projName));
    	    projNo=encodeURIComponent(cjkEncode(projNo));
        	suName = encodeURIComponent(cjkEncode(suName));
        	constructionUnit = encodeURIComponent(cjkEncode(constructionUnit));
        	corpName = encodeURIComponent(cjkEncode(corpName));
        	menuDes = encodeURIComponent(cjkEncode(menuDesc));
        	
       
        //cpt路径及参数
        var ur = "clearRecord.cpt";
		//判断是否为新报验单
        if(isUpdate()){
            ur = "clearRecordNew.cpt";
        }
        var src=cptPath+"/ReportServer?reportlet=inspection/"+ur+"&projName="+projName+"&projNo="+projNo+"&suName="+suName+"&corpName="+corpName+"&constructionUnit="+constructionUnit;
    	src = src + "&pcId="+pcId+"&imgUrl="+$('.imgUrl').val();
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
    	var recordJson = trSData.recordListTable.json;
    	var pcId,projNo;
    	if(recordJson.pcId){
    		pcId = recordJson.pcId;
    	}
	    if(recordJson.crId){
	    	//传递记录ID,用工程编号存储
	    	projNo = recordJson.crId;
	    }
		projId = $("#projId").val(),
		stepId=getStepId();
	    busRecordId=pcId;
	    console.info(projNo);
		if(_inApk && $(".attach-images-list").find(".new-image").length){
				//photoUpload($(".attach-images-list .upload-image-btn"), projId, projNo, getStepId(), pcId);
				
			var images = $(".attach-images-list .uploadBtn").parents(".attach-images-list").find(".new-image"),
		    files = [];
		    $.each(images, function (i, el) {
		        files.push($(el).find(".preview-btn").attr("src-orig"));
		    });
		    //navigator.notification.alert(files);
		     if (!projNo && $("#crId1").length && !$("#crId1").val()) {
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