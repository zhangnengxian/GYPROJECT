<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
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
                  <h4 class="panel-title">签证信息</h4>
                </div>
                <div class="panel-body" >
	                <div class="tab-pane fade in btn-top active" id="visa_info">
	                	<div class=" clearboth form-box">
						 	<form class="form-horizontal" id="engineeringForm" data-parsley-validate="true">
						 		<input type="hidden" id="result" name="result">
						 		<input type="hidden" id="list" name="list">
								<input type="hidden" id="menuDes" name="menuDes">
								<input type="hidden" id="drawUrl" name="drawUrl">
								<input type="hidden" id="auditState" name="auditState" value = "2">
								<input type="hidden" id="evId" name="evId">
								<input type="hidden" id="drawUrl1" name="drawUrl1" value="${drawUrl1}">
						 		<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
						 	    <input type="hidden" class="form-control input-sm field-not-editable" id="projId" name="projId">
								<div class="form-group col-sm-12">
					                	<label class="control-label" for="">工程编号</label>
					                    <div> 
					                    	<input type="text" class="form-control input-sm field-not-editable" value="" id="projNo" name="projNo" data-parsley-maxlength="200">
					  				    </div> 
					  				</div>
								<div class="form-group col-sm-12">
					                	<label class="control-label" for="">工程名称</label>
					                    <div> 
					                    	<input type="text" class="form-control input-sm field-not-editable" value="" id="projName" name="projName" data-parsley-maxlength="200">
					  				    </div> 
					  				</div>
									<!-- <div class="form-group col-sm-12">
										<label class="control-label" for="">分包单位</label>
								    	<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="constructionUnit" name="constructionUnit"  data-parsley-maxlength="50"/>
									   </div>
									</div> -->
									<div class="form-group col-md-6 col-sm-12">
					                	<label class="control-label" for="evNo">签证编号</label>
					                    <div> 
					                    	<input type="text" class="form-control input-sm field-editable" value="" id="evNo" name="evNo" data-parsley-maxlength="30" data-parsley-required="true">
					  				    </div> 
					  				</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="evPosition">签证部位</label>
										<div>
											<input type="text" class="form-control input-sm field-editable" value="" id="evPosition" name="evPosition"  data-parsley-maxlength="50" data-parsley-required="true"/>
										</div>
						    		</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="drawingNo">施工图号</label>
										<div>
											<input type="text" class="form-control input-sm field-editable" value="" id="drawingNo" name="drawingNo"  data-parsley-maxlength="50" data-parsley-required="true"/>
										</div>
						    		</div>
						    		 <div class="form-group col-md-6 col-sm-12">
										<label class="control-label">签证日期</label>
										<div>
											<input type="text" class="form-control input-sm  field-editable timestamp all" value="" id="visaDate" name="visaDate" data-parsley-required="true"  />
										</div>
									</div>
									<div class="form-group col-sm-6">
										<label class="control-label" for="evType">签证类型</label>
										<div>
											<select class="form-control input-sm field-editable" id="evType" name="evType" data-parsley-required="true">
												 <c:forEach var="enums" items="${evType}">
													<option value="${enums.value}" >${enums.message}</option>
								                </c:forEach> 
											</select>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="submitAmount">报送金额</label>
										<div>
											<input type="text" class="form-control input-sm field-editable" value="" id="submitAmount" name="submitAmount"  data-parsley-maxlength="10" data-parsley-required="true"/>
										</div>
									</div>
						    		<div class="form-group col-sm-12 hidden">
					     				<label class="control-label" for="backReason">退回原因</label>
					    				<div> 
					         				<textarea class="form-control field-not-editable" name="backReason" id="backReason" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
				         				</div>
					    			</div>
									<div class="form-group col-sm-12">
					     				<label class="control-label" for="evReason">签证原因</label>
					    				<div> 
					         				<textarea class="form-control field-editable" name="evReason" id="evReason" rows="4" cols="" data-parsley-maxlength="255" ></textarea>
				         				</div>
					    			</div>
					    			<div class="form-group col-sm-12">
					     				<label class="control-label" for="evContent">签证内容及工程量</label>
					    				<div> 
					         				<textarea class="form-control field-editable" name="evContent" id="evContent" rows="7" cols="" data-parsley-maxlength="500" ></textarea>
				         				</div>
					    			</div>
					    			<div class="form-group col-md-6 col-sm-12 clearboth">
										<label class="control-label" for="quantitiesTotal">调整预算金额</label>
										<div>
											<input type="text" class="form-control input-sm fixed-number field-editable" value="" id="quantitiesTotal" name="quantitiesTotal" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-required="true"/>
											<a href="javascript:;" class="btn btn-sm btn-default">元</a>
										</div>
						    		</div>
					    			<div class="form-group col-md-6 col-sm-12 ">
										<label class="control-label" for="budgeter">预算人</label>
										<div>
											<input type="text" class="form-control input-sm  field-editable" value="" id="budgeter" name="budgeter" />
										</div>
						    		</div>  
						    		<div class="form-group col-md-12">
	                                    <label class="control-label" for="remark">备注</label>
	                                    <div>
	                                          <textarea class="form-control field-editable" name ="remark" id="remark" rows="3" data-parsley-maxlength="400" value=''></textarea>
	                                    </div>
                                      </div>
					    			<div class="form-group col-md-6 col-sm-12 ">
										<label class="control-label">预算日期</label>
										<div>
											<input type="text" class="form-control input-sm  field-editable timestamp all" value="" id="budgetAdjustDate" name="budgetAdjustDate"  />
										</div>
						    		</div>
					    			<div class="form-group col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"   id="constructionUnit" name="constructionUnit"  />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="suPrincipal">项目负责人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suPrincipal" name="suPrincipal" value="">
											<input type="hidden" id="suPrincipal_postType" name="suPrincipal_postType" value="${suPrincipal}">
											<img class="suPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label">审核日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable" value="" id="custAuditDate" name="custAuditDate"  />
										</div>
									</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="">施工员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
											<input type="hidden" id="builder_postType" name="builder_postType" value="${builder}">
											<img class="builder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="custName">建设单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="custName" name="custName"  data-parsley-maxlength="50"/>
									    </div>
									</div>
									 <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for=cmoPrincipal>现场代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="cmoPrincipal" name="cmoPrincipal" value="">
											<input type="hidden" id="cmoPrincipal_postType" name="cmoPrincipal_postType" value="${builder}">
											<img class="cmoPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
									<%--  <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="fieldPrincipal">现场代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="fieldPrincipal" name="fieldPrincipal" value="">
											<input type="hidden" id="fieldPrincipal_postType" name="fieldPrincipal_postType" value="${fieldPrincipalPost }">
											<img class="fieldPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div> --%>
									 <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">审核日期</label>
										<div>
											<input type="text" class="form-control input-sm  datepicker-default field-editable all" value="" id="builderAuditDate" name="builderAuditDate"   />
										</div>
									</div>
									<div class="form-group col-lg-12 col-md-12 col-sm-12">
										<label class="control-label" for="">现场代表意见</label>
										<div>
											<textarea class="form-control field-editable"  data-parsley-required="true" name="cmoPrincipalOpinion" id="cmoPrincipalOpinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
									</div>
								    <div class="form-group col-sm-12">
										<label class="control-label" for="suName">监理单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"   id="suName" name="suName"  />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="suJgj">现场监理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${suJgj}">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label">审核日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable" value="" id="suAuditDate" name="suAuditDate"  />
										</div>
									</div>
									<div class="form-group col-lg-12 col-md-12 col-sm-12">
										<label class="control-label" for="">现场监理意见</label>
										<div>
											<textarea class="form-control field-editable"  data-parsley-required="true" name="suOpinion" id="suOpinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
									</div>
							</form>
						</div>
						<div class = "clearboth"></div>
						<form id="fileupload" action="accessoryCollect/uploadFile" method="POST" enctype="multipart/form-data">
						    <input type="hidden" name="alPath" id="alPath" value="0104"/>
						    <input type="hidden" name="encryption" id="encryption" />
						    <input type="hidden" name="caiId" id="caiId" />
						    <input type="hidden" name="stepId" id="stepId" />
						    <input type="hidden" name="step" id="step" />
						    <input type="hidden"  class="projId" name = "projId" value = ""/>
			    			<input type="hidden"  class="projNo" name = "projNo" value = ""/>
						    <input type="hidden" name="sourceType" id="sourceType"  value="${accessorySource }"/>
						    <input type="hidden" class = "formClear" name="busRecordId" id="busRecordId" />
						   	<input type="hidden" id="loginId" name="loginId" value="${loginId}"/>
						   	<input type="hidden" id="aspectRatio" value="1.25" />
							<div class="fileupload-buttonbar">
						        <div class="pull-right toolBtn">
						            <span class="btn btn-success btn-sm fileinput-button">
						                <i class="fa fa-plus"></i>
						                <span>浏览文件...</span>
						                <input type="file" name="files[]" multiple/>	             	          
						            </span>
						            <button type="submit" class="btn btn-primary btn-sm start hidden">
					                </button>
					                <button type="button" class="btn btn-primary btn-sm upload-btn">
					                    <i class="fa fa-upload"></i>
					                    <span>上传</span>
					                </button>
						        </div>
						        <div class="col-md-12 fileupload-progress fade hidden">
						            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
						                <div class="progress-bar progress-bar-success" style="width:0%;"></div>
						            </div>
						            <div class="progress-extended">&nbsp;</div>
						        </div>
						    </div>
						    <table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
					    </form>
						<table id="dataPopTableSecond" class="table table-striped table-bordered nowrap" width="100%">
					   		<thead>
					     		<tr>
					     			<th></th>
					     		    <th></th>
					           		<th>资料名称</th>
					           		<th>资料类型</th>
					            	<th>签收日期</th>
					            	<th>签收人</th>
					            	<th width='40'>操作</th>
					           	</tr>
					       	</thead>
						</table>
						<div class="clearboth form-box">
							<div class="form-group width-full attach-images-list" id="projectImagesList">
								<h4><i class="fa fa-file-photo-o"></i> 照片列表
									<%--<a href="javascript:;" class="btn btn-primary btn-sm upload-image-btn pull-right"><i class="fa fa-upload"></i> 上传</a>--%>
									<%--<a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a>--%>
								</h4>
								<ul class="row"></ul>
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
			         <h4 class="panel-title">审批区</h4>
			    </div>
			    <div class="panel-body" id="stageProject_audit_panel_box">
			    	<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSaveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 auditCancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box auditFormDiv">
			    		<form class="form-horizontal" id="stageProjectAuditRightForm" action="stageProjectAudit/auditSave">
			    			<input type="hidden"  class="projId" name = "projId" value = ""/>
			    			<input type="hidden"  class="projNo" name = "projNo" value = ""/>
			    			<input type="hidden" class="isAudit" id="isAudit" name = "isAudit" value = "${ isAudit}"/>
                    		<input type="hidden"  class="evId" name = "evId" value = "${ evId}"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" value = "${ evId}"/>
                    		<input type="hidden" class="currentLevel" name = "mrAuditLevel" value = "${ currentLevel}"/>
			    			<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
			        			<label class="control-label" for="">审批结果</label>
				            	<div>
						             <c:forEach var="enums" items="${mrResult}">
					             		 <label>
							            	<input type="radio" name="mrResult" value="${enums.value}"/>${enums.message}
							            </label>
						             </c:forEach>
						        </div>
		    				</div>
		    				<div class="form-group col-lg-12 col-md-12 col-sm-12">
						     	<label class="control-label" for="">审批意见</label>
						     	<div> 
		        					<textarea class="form-control field-editable"  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					</div>
		   					<div class="form-group col-lg-6 col-md-12 col-sm-6">
						        <label class="control-label" for="">审批人</label>
						        <div>
						           <input type="hidden"  id="mrCsr"  name="mrCsr" data-parsley-maxlength="100" value="${loginInfo.staffId}">
						           <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
						        </div>
						    </div>
						    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
						        <label class="control-label" for="">审批日期</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" data-parsley-maxlength="100" >
						        </div>
						    </div>
			    		</form>
			    	</div>
			    	<div >
		    		<h4 class="m-t-15 m-l-7"><strong>审批历史</strong></h4>
		    		</div>
		    		<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
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
    App.setPageTitle('签证审核 - 工程管理系统');
    $("#stageProjectAuditRightForm").styleFit();
    $("#stageProjectAuditRightForm").toggleEditState(true);
    $("#engineeringForm").styleFit();
    $("#engineeringForm").toggleEditState(false);
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $("#stepId").val(getStepId());
    $("#step").val(getStepId());
    //初始化上传文件控件
    $.getScript('projectjs/data/form-multiple-upload.demo.js?v='+Math.random()).done(function() {
    	FormMultipleUpload.init();
    });
    $.getScript('projectjs/constructmanage/engineering-visa-audit-page.js?v='+Math.random()).done(function () {
        auditHistory.init();
	});
    //放弃
    $(".auditCancelBtn").off("click").on("click",function(){
		$("#ajax-content").cgetContent("engineeringVisaAudit/main");
	});
    
    $("#mrTime").change();
    
    //保存
    $(".auditSaveBtn").off("click").on("click",function(){
    	var val=$('#stageProjectAuditRightForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		alertInfo("请选择审批结果！");
    	}else{
    		$(".projId").val($("#projId").val());
        	$(".projNo").val($("#projNo").val());
        	if($("#stageProjectAuditRightForm").parsley().isValid()){
        		$("#stageProject_audit_panel_box").loadMask("正在保存...", 1, 0.5);
            	var data=$("#stageProjectAuditRightForm").serializeJson();
            	$.ajax({
                    type: 'POST',
                    url: 'engineeringVisaAudit/auditSave',
                    contentType: "application/json;charset=UTF-8",
                    dataType:"json",
                    data: JSON.stringify(data),
                    success: function (data) {
                    	$("#stageProject_audit_panel_box").hideMask();
                    	var content = "数据保存成功！";
                    	if(data.ret_message === "false"){
                    		content = "数据保存失败！";
                    		alertInfo(content);
                    	}else if(data.ret_message === "true"){
                    		$(".auditSaveBtn").addClass("hidden");
                    		$(".auditCancelBtn").text("返回");
                    		$("#stageProjectAuditRightForm").toggleEditState(false);
                    		$(".auditFormDiv").addClass("hidden");
                    		$("#auditHistoryTable").cgetData(false);  //列表重新加载
                    		alertInfo(content);
                    	}else{//接口失败
                    		alertInfo(data.ret_message);
                    	}
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.warn("审核记录保存失败！");
                    }
                });
            	//如果通过验证, 则移除验证UI
            	$("#stageProjectAuditRightForm").parsley().reset();
            }else{
            	//如果未通过验证, 则加载验证UI
            	$("#stageProjectAuditRightForm").parsley().validate();
            }
    	}
    });
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".auditSaveBtn").addClass("hidden");
    		$(".auditCancelBtn").text("返回");
    		$("#stageProjectAuditRightForm").toggleEditState(false);
    		$(".auditFormDiv").addClass("hidden");
    	}
    } 
    
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//审核过
    		auditSaveCallBack("true");
    	}
    }();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->