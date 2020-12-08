<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/jedate/skin/jedate.css" rel="stylesheet" />
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
<!-- ================== END PAGE LEVEL STYLE ================== -->

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
						<li class="active"><a href="#visa_list" id="visaTab" data-toggle="tab">签证记录</a></li>
						<li class=""><a href="#visa_info" id="visaInfo" data-toggle="tab">签证信息</a></li>
						<li class=""><a href="#default-tab-4" id="materialChangeTab"   data-toggle="tab">材料变更</a></li>
					</ul>
                </div>
                <div class="panel-body">
                	<input type="hidden" name="fieldAdministrator" id="fieldAdministrator" value="${fieldAdministrator }"/>
                	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                	<div class="tab-content">
	                	<div class="tab-pane fade active in btn-top active" id="visa_list" >
	                		<input type=hidden id="loginPost" value="${loginPost }">
							<input type=hidden id="dataAdminPost" value="${dataAdminPost }">
							<input type=hidden id="budgetGroupLeader" value="${budgetGroupLeader }">
	                		<input type="hidden" id="builderPostType" value="${builder}">
	                		<input type="hidden" id="suJgjPostType" value="${suJgj}">
	                		<input type="hidden" id="CONSTRUCTION" value="${CONSTRUCTION }">
	                		<input type="hidden" id="budgeterPost" value="${budgeter }">
	                		<input type="hidden" id="businessOrderId">
	                		<input type="hidden" id="stepId" value="${stepId}">
	                		<input type="hidden" id="cuUnitType" value="${cuUnitType}">
	                		<input type="hidden" id="unitType" value="${unitType}">
	                		<!-- 采集文件类型 -->
	                		<input type="hidden" id="sourceType" value="${accessorySource }" class="sourceType">
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="engineeringTable" width="100%">
	                			<thead>
		                			<tr>
		                			    <th></th>
		                				<th>签证日期</th>
		                			    <th>签证编号</th>
			                			<th>工程名称</th>
			                			<th>推送状态</th>
			                			<th>审核状态</th>
			                			<th>预算员</th>
			                			<th></th>
			                			<th></th>
		                			    <th></th>
		                			    <th></th>
		                			    <th>作废标记</th>
		                			    <th>签证金额</th>
		                			    <th>预算书</th>
		                			    <th>待办人</th>
		                			</tr>
	                			</thead>
	                		</table>
	                	</div>
	                	<div class="tab-pane fade in btn-top" id="visa_info">
		                     <div class="toolBtn f-r p-b-10 hidden" >
							     <a href="javascript:;" class="btn btn-confirm btn-sm checkRole saveBtn">保存</a>
							     <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveUpBtn">放弃</a>
						     </div>
							 <div class=" clearboth form-box">
							 	<input type="hidden" id="projId1" name="projId">
							 	<input type="hidden" id="cmId" name="cmId">
							 	<input type="hidden" id="constructionUnit1" name="constructionUnit1">
							 	<input type="hidden" id="conNo1" name="conNo1">
							 	<form class="form-horizontal" id="engineeringForm" data-parsley-validate="true" action="engineering/saveEngineeringVisaFile">
							 		<input type="hidden" id="result" name="result">
							 		<input type="hidden" id="list" name="list">
									<input type="hidden" id="menuDes" name="menuDes">
									<input type="hidden" id="drawUrl" name="drawUrl">
									<input type="hidden" id="orgId" name="orgId">
									<input type="hidden" id="corpId" name="corpId">
									<input type="hidden" id="tenantId" name="tenantId">
									<input type="hidden" id="evId" name="evId" class="addClear accBusRecordId">
									<input type="hidden" id="drawUrl1" name="drawUrl1" value="${drawUrl1}">
							 		<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
							 		<input type="hidden"  id="fileUrl" name="fileUrl"  />
							 	    <input type="hidden" class="form-control input-sm field-not-editable" id="projId" name="projId">
									<input type="hidden" name="stepId" id="stepId" />
									<input type="hidden" name="step" id="step" />
									<input type="hidden" name="alPath" id="alPath" />
									<input type="hidden" id="version" name="version">
									<input type="hidden" id="isUploadFile" name="isUploadFile">
									<input type="hidden" id="pushDate" name="pushDate">
							 	    <input type="hidden" id="isSignComplete" name="isSignComplete">
									<div class="form-group col-sm-12">
					                	<label class="control-label" for="">工程编号</label>
					                    <div> 
					                    	<input type="text" class="form-control input-sm field-not-editable" value="" id="projNo" name="projNo" data-parsley-maxlength="50">
					  				    </div> 
					  				</div>
									<div class="form-group col-sm-12">
					                	<label class="control-label" for="">工程名称</label>
					                    <div> 
					                    	<input type="text" class="form-control input-sm field-not-editable" value="" id="projName" name="projName" data-parsley-maxlength="200">
					  				    </div> 
					  				</div>
									<div class="form-group col-md-6 col-sm-12">
					                	<label class="control-label" for="evNo">签证编号</label>
					                    <div> 
					                    	<input type="text" class="form-control input-sm field-not-editable addClear" value="" id="evNo" name="evNo" data-parsley-maxlength="30">
					  				    </div> 
					  				</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="evPosition">签证部位</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear allText construction builder" value="" id="evPosition" name="evPosition"  data-parsley-maxlength="500" data-parsley-required="true"/>
										</div>
						    		</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="drawingNo">施工图号</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear allText construction builder" value="" id="drawingNo" name="drawingNo"  data-parsley-maxlength="500" data-parsley-required="true"/>
										</div>
						    		</div>
						    		 <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label" for="">签证日期</label>
										<div>
											<input type="text" class="form-control input-sm  field-not-editable datepicker-default addClear " value="" id="visaDate" name="visaDate" data-parsley-required="true"  />
										</div>
									</div>
									<div class="form-group col-sm-6">
										<label class="control-label" for="evType">签证类型</label>
										<div>
											<select class="form-control input-sm field-editable addClear allText construction" id="evType" name="evType" data-parsley-required="true">
												 <c:forEach var="enums" items="${evType}">
													<option value="${enums.value}" >${enums.message}</option>
								                </c:forEach> 
											</select>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="submitAmount">报送金额</label>
										<div>
											<input type="text" class="form-control input-sm field-editable fixed-number addClear allText construction  budgetMember" value="" id="submitAmount" name="submitAmount"  data-parsley-maxlength="16" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="quantitiesTotal">审核金额</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable addClear" value="" id="quantitiesTotal" name="quantitiesTotal"  data-parsley-maxlength="50"/>
										</div>
						    		</div>									

									<div class="form-group col-sm-12 allText">
										<label class="control-label">大样图(图片)</label>
										<div>
											<div class="hidden hasVal col-sm-12">
												<input type="text" class="form-control input-sm field-not-editable hasVal" id="drawName" name="drawName"/>
												 <a class="btn btn-sm btn-primary preview_Button" >预览</a> 
												 <a class="btn btn-sm btn-primary Load_Button" href="" target="_blank">下载</a>
												 <a class="btn btn-sm btn-danger del_btn ">删除</a>
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
											<table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
										</div>
									</div>

									<div class="form-group col-sm-12">
					     				<label class="control-label" for="evReason">签证原因</label>
					    				<div> 
					         				<textarea class="form-control field-editable addClear allText construction controlField" name="evReason" id="evReason" rows="4" cols="" data-parsley-maxlength="2000" ></textarea>
				         				</div>
					    			</div>
					    			<div class="form-group col-sm-12">
					     				<label class="control-label" for="evContent">签证内容</label>
					    				<div> 
					         				<textarea class="form-control field-editable addClear allText construction controlField" name="evContent" id="evContent" rows="7" cols="" data-parsley-maxlength="2000" placeholder= "签证内容及工程量"></textarea>
				         				</div>
					    			</div>
					    			<div class="form-group col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"   id="constructionUnit" name="constructionUnit"  />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12 allSign cuPm">
										<label class="control-label signature-tool" for="suPrincipal">项目负责人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suPrincipal" name="suPrincipal" value="">
											<input type="hidden" class="signPost"  id="suPrincipal_postType" name="suPrincipal_postType" value="${CU_PM}">
											<img class="suPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">审核日期</label>
										<div>
											<input type="text" class="form-control input-sm addClear datepicker-default field-editable allText cuPm" value="" id="custAuditDate" name="custAuditDate"  />
										</div>
									</div>
								    <div class="form-group col-md-6 col-sm-12 allSign construction">
										<label class="control-label signature-tool" for="builder">施工员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
											<input type="hidden" class="signPost"  id="builder_postType" name="builder_postType" value="${CONSTRUCTION}">
											<img class="builder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
									
								    <div class="form-group col-sm-12">
										<label class="control-label" for="suName">监理单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm" id="suName" name="suName"/>
										</div>
								    </div>
		    				        <div class="form-group col-lg-12 col-md-12 col-sm-12">
						     	        <label class="control-label" for="">监理意见</label>
						     	        <div> 
		        					        <textarea class="form-control field-editable addClear allText suJgj"  name="suOpinion" id="suOpinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					        </div>
					    			<div class="form-group col-lg-12 col-md-12 col-sm-6">
					        			<label class="control-label allText suJgj" for="">审核结果</label>
						            	<div>
						            	 <label>
									            <input type="radio" class=" allText suJgj" name="suResult" value="1" checked/>通过
									            </label>
						            	 <label>
									            <input type="radio" class=" allText suJgj" name="suResult" value="0" />未通过
									            </label>
								             
								        </div>
				    				</div>
								    <div class="form-group col-md-6 col-sm-12 allSign suJgj">
										<label class="control-label signature-tool" for="suJgj">现场监理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden" class="signPost" id="suJgj_postType" name="suJgj_postType" value="${suJgj}">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label" for="">审核日期</label>
										<div>
											<input type="text" class="form-control input-sm addClear datepicker-default field-editable allText suJgj" value="" id="suAuditDate" name="suAuditDate"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="custName">建设单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="custName" name="custName"  data-parsley-maxlength="50"/>
									    </div>
									</div>
		    				        <div class="form-group col-lg-12 col-md-12 col-sm-12">
						     	        <label class="control-label" for="">现场代表意见</label>  
						     	        <div> 
		        					        <textarea class="form-control field-editable addClear allText builder"  name="cmoPrincipalOpinion" id="cmoPrincipalOpinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					        </div>
					    			<div class="form-group col-lg-12 col-md-12 col-sm-6">
					        			<label class="control-label" for="">审核结果</label>
						            	<div>
											<input type="hidden" id="cmoPrincipalResult" value="">
						            	 	<label><input type="radio" class=" allText builder" name="cmoPrincipalResult" value="1" />通过</label>
						            	 	<label><input type="radio" class=" allText builder" name="cmoPrincipalResult" value="0" />未通过</label>
								        </div>
				    				</div>
									 <div class="form-group col-md-6 col-sm-12 allSign builder">
										<label class="control-label signature-tool" for=cmoPrincipal>现场代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="cmoPrincipal" name="cmoPrincipal" value="">
											<input type="hidden" class="signPost"  id="cmoPrincipal_postType" name="cmoPrincipal_postType" value="${builder}">
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
									 <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">审核日期</label>
										<div>
											<input type="text" class="form-control input-sm addClear datepicker-default field-editable all allText builder" value="" id="builderAuditDate" name="builderAuditDate"   />
										</div>
									</div>
									<div class="form-group col-lg-12 col-md-12 col-sm-6 hidden cuReState">
					        			<label class="control-label allText budgetMember" for="">确认结果</label>
						            	<div>
						            	 <label>
									            <input type="radio" class="field-editable allText budgetMember" name="cuReState" value="-1" checked/>待确认
									            </label>
						            	 <label>
									            <input type="radio" class="field-editable allText budgetMember" name="cuReState" value="1" checked/>无异议
									            </label>
						            	 <label>
									            <input type="radio" class="field-editable allText budgetMember" name="cuReState" value="0" />有异议
									            </label>
								             
								        </div>
				    				</div>
									<div class="form-group col-md-12 col-sm-12 hidden cuReReason">
										<label class="control-label" for="">重审原因</label>
										<div>
											<input type="text" class="form-control input-sm field-editable allText budgetMember" value="" id="cuReReason" name="cuReReason"  data-parsley-required="true" data-parsley-maxlength="500" />
										</div>
									</div>
								</form>
							</div>
								<div class="clearboth">
					    		<h4 class="m-t-15 m-l-7"><strong>审核历史</strong></h4>
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
							<div class="clearboth form-box">
								<div class="form-group width-full attach-images-list" id="projectImagesList">
								     <h4><i class="fa fa-file-photo-o"></i> 照片列表
										 <a href="javascript:;" class="btn btn-primary btn-sm upload-images-btn pull-right"><i class="fa fa-upload"></i> 上传</a>
										 <a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a>
										 <!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
								     <ul class="row"></ul>
								</div>
						 	</div>
						</div>
						<div class="tab-pane fade in btn-top" id="default-tab-4">
             		   <div id="materialChangebox">
				        	<form id="exportExcel" action="changeRecord/exportExcel">
				        	</form>
					        <form action="" id="materialListForm">
						         <table id="materialListTable" class="table table-striped table-bordered nowrap " width="100%">
				            		<thead>
				                		<tr>
					                		<th></th>
					                		<th></th>
					                		<th>材料名称</th>
					                		<th>材料规格</th>
							                <th>单位</th>
							                <th>是否由物资购买</th>
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
            <div class="panel panel-inverse" id="content">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <h4 class="panel-title">预览区</h4>
			    </div>
			    <div class="panel-body">
			    	<div class="iframe-report-box">
						<iframe id="mainFrm" class="iframe-report" style="width: 800px; height: 1200px;border:1; overflow-y:auto;" scrolling="no"></iframe>
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
  //初始化上传文件控件
    $.getScript('projectjs/data/form-multiple-upload.demo3.js?'+Math.random()).done(function() {
    	FormMultipleUpload.init($('#engineeringForm'));
    });
    App.setPageTitle('签证记录 - 工程管理系统');
    
    /* $(".preview_Button").on("click",function(){
    	$("#Load_Button").
    	alert("点击了预览按钮");
    }) */
  	//是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	$('#projNo').val(getProjectInfo().projNo);
    	$('#projName').val(getProjectInfo().projName);
    	$('#suName').val(getProjectInfo().suName);
    	$('#custName').val(getProjectInfo().corpName);
    	$('#projId').val(getProjectInfo().projId);
    	$('#corpId').val(getProjectInfo().corpId);
    	$('#constructionUnit').val(getProjectInfo().cuName);
    	$('#conNo1').val(getProjectInfo().projNo);
    	if(getProjectInfo().corpId!='1101'){
    		$(".controlField").addClass("builder");
    	}else{
    		$(".controlField").removeClass("builder");
    	}
    };

        $("#projectImagesList").getImagesList({
            "projId": getProjectInfo().projId,
            "stepId": getStepId(),
            "projNo": getProjectInfo().projNo,
            "busRecordId": $("#evId").val() || '-1'
        });

    function saveBack(data){
		$("#visa_info").hideMask();
       	var content = "数据保存成功！";
       	if(data.result === "false"){
       		content = "数据保存失败！";
       	}else {
       		$("#evId").val(data.operateId);
       		$("#fileUrl").val(data.accListId);
       		$(".Load_Button").attr("href","/accessoryCollect/openFile?id="+data.accListId);
       		$(".toolBtn").addClass("hidden");
       		//$("#designAlterationTable").cgetData();  //列表重新加载
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
    var savedone = function(){
    	$("#visaQualititiesTable").cgetData(true);  //列表重新加载
    	 showReport();
    	 if($("#drawName").val()){
    		 $(".hasVal").removeClass("hidden");
    		 $(".noVal,.del_btn").addClass("hidden");
    		 $(".noVal+#filePreviews tr").remove();
    	 }else{
    		 $(".noVal").removeClass("hidden");
    		 
    		 $(".hasVal").addClass("hidden");
    	 }
    	 sureClose();
    }
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

    $("#engineeringForm").styleFit();
    //日期
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    //签字
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	 $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6,#signBtn_7").handleSignature(); 
    });
    //路径
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %> 
    //加载打印预览
    var showReport = function(){
    	var projId= $("#projId").val(),
    		imgUrl= $(".imgUrl").val(),
    		evId= $("#evId").val(),
    		drawUrl1= $("#drawUrl1").val(),
    		menuDes= encodeURIComponent(cjkEncode($("#menuDes").val()));
    	var projName = encodeURIComponent(cjkEncode($('#projName').val()));
    	var custName= encodeURIComponent(cjkEncode($('#custName').val()));
    	var suName= encodeURIComponent(cjkEncode($('#suName').val()));
    	var constructionUnit= encodeURIComponent(cjkEncode($('#constructionUnit').val()));
    		
    	src="<%=basePath%>/ReportServer?reportlet=constructmanage/engineering.cpt&projId="+projId+"&evId="+evId+"&imgUrl="+imgUrl+"&drawUrl1="+drawUrl1+"&menuDes="+menuDes+"&projName="+projName;
    	src = src +"&custName="+custName+"&suName="+suName+"&constructionUnit="+constructionUnit;
    	$("#mainFrm").attr("src",src); 
    };
    showReport();
    var cptPath = '<%=basePath%>';
    //打印乱码解决
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
    
     //预览图片
     $('.preview_Button').off("click").on("click",function(){
    	 var alId = $("#fileUrl").val();  //文件id
    	 $.ajax({
    		 type:'post',
    		 url:'accessoryCollect/queryAccessoryList/'+alId,
    		 success:function(data){
    			if (data != null && data != ''){
    				fileTypeFunction(data,window.location); //预览文件方法
    			 }else if(data === 'false'){
   				  $("body").cgetPopup({
		               	title: "提示信息",
		               	content: "系统抛出异常！",
		               	accept: function(){},
		               	newpop: 'new',
		               	icon: "fa-check-circle"
		           });
				  return false;
			 }else{
				 $("body").cgetPopup({
		               	title: "提示信息",
		               	content: "没有相关附件！",
		               	accept: function(){},
		               	newpop: 'new',
		               	icon: "fa-check-circle"
		           });
			 }
    		 }, error: function (jqXHR, textStatus, errorThrown) {
 	            console.warn("文件ID获取失败！");
 	        }
    	 })
    }); 
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
	                                        		 $.getScript('projectjs/constructmanage/engineering-record.js?'+Math.random()).done(function () {
	                     		                    	engineeringRecord.init();
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

  	//保存
    $('.saveBtn').off().on("click",function(){
    	var formData=$("#engineeringForm").serializeJson();
    	//提示选择审核结果
    	var suResult = $('input[type="radio"][name="suResult"]:checked').val();
    	if((suResult ==''|| suResult=='undefined' || suResult==undefined) &&  $('input[type="radio"][name="suResult"]').attr("readonly") != "readonly"){
    		$("body").cgetPopup({
				title: '提示',
   				content: '请选择监理审核意见！',
				accept:surplusAccept,
				newpop:'second'
				
			});
    		return false;
    	}
    	var cmoPrincipalResult = $('input[type="radio"][name="cmoPrincipalResult"]:checked').val();
    	if((cmoPrincipalResult =='' || cmoPrincipalResult=='undefined' || cmoPrincipalResult==undefined) &&  $('input[type="radio"][name="cmoPrincipalResult"]').attr("readonly") != "readonly"){
    		$("body").cgetPopup({
				title: '提示',
   				content: '请选择现场代表审核意见！',
				accept:surplusAccept,
				newpop:'second'
			})
			return false;
    	}
    	//验证
    	if($("#engineeringForm").parsley().isValid()){
            
    		if($(".noVal+#filePreviews tr").length){
    			if($(".noVal+#filePreviews tr").length>1){
    				$("body").cgetPopup({
    					title: '提示',
	       				content: '一次只能上传一张简图或附件，请重新上传！',
    					accept:surplusAccept,
    					newpop:'second'
    					
    				})
    			}else{
		   		 	//序列化
		   	       	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
		   	       	$("#menuDes").val(menuDesc);
		               $("#stepId").val(menuDesc);
		               $("#step").val(getStepId());
		   	       	var data=$("#engineeringForm").serializeJsonString();
		   	       	var dataJson=JSON.parse(data);
		   	       	var json=$("#visaQualititiesTable").DataTable().data();
		   	       	var list = [];
		   		 	$.each(json, function(k,v){
		   		 		list.push(v);
		       		})
		       		dataJson.list=list;
		   	       	$("#result").val(JSON.stringify(dataJson));
		   	       	$(".start").click();
    			}
    		}else{
    		 	var formData=$("#engineeringForm").serializeJsonString();
     	       	var dataJson=JSON.parse(formData);
     	       	var json=$("#visaQualititiesTable").DataTable().data();
     	       	var list = [];
     		 	$.each(json, function(k,v){
     		 		list.push(v);
         		})
         		dataJson.list=list;
    	       	var data={};
    		 	data.result=JSON.stringify(dataJson);
    		 	$("#visa_info").loadMask("正在保存...", 1, 0.5);
    			$.ajax({
    	               type: 'POST',
    	               url: 'engineering/saveEngineeringVisa',
    	               contentType: "application/json;charset=UTF-8",
    	               data: JSON.stringify(data),
    	               success: function (data) {
                           $("#visa_info").hideMask();
    	               	var content = "数据保存成功！";
    	               	if(data === "false"){
    	               	   content = "数据保存失败！";
    	               	}else if(data === "already"){
    	               		content = "此信息已被修改，请刷新页面！";
    	               	}else{
    	               		$("#evId").val(data);
    	               		$(".toolBtn").addClass("hidden");
    	               		var projNo = getProjectInfo().projNo,
    	               		projId = getProjectInfo().projId;
    	               		//图片上传
    	               		if(_inApk && $(".attach-images-list").find(".new-image").length){
    	               			preImagesUpload($(".attach-images-list .upload-images-btn"), projId, projNo, getStepId(), data);
    	               		};
    	               		$("#engineeringForm").toggleEditState(false)
                            $("#visaQualititiesTable").cgetData();  //列表重新加载
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
    	                   console.warn("签证信息保存失败！");
    	               }
    	           });
    		}
       	//如果通过验证, 则移除验证UI
       	$("#engineeringForm").parsley().reset();
       	/* $('ul.nav>li').removeClass("active");
		$('#visaTab').click(); */
       }else{
       	//如果未通过验证, 则加载验证UI
       	$("#engineeringForm").parsley().validate();
       }    
    	//$("#engineeringForm").toggleEditState(false);
    });
  	
  	var surplusAccept=function(){
  		$(".surplusDelBtn").removeClass("hidden");
  	};
    //点击放弃
    $('.giveUpBtn').off().on('click',function(){
    	//$('ul.nav>li').removeClass("active");
		$('#visaTab').click();
		$('.toolBtn').addClass("hidden");
		$("#engineeringForm").toggleEditState(false)
    })
    //移动端点击上传
   $("#projectImagesList .upload-images-btn").off("click").on("click",function(){
	    var busRecordId = $("#evId").val(),
		projNo = $("#projNo").val(),
		projId = $("#projId").val(),
		stepId=getStepId();
		if(_inApk && $(".attach-images-list").find(".new-image").length){
				//photoUpload($(".attach-images-list .upload-images-btn"), projId, projNo, getStepId(), pcId);
				
			var images = $(".attach-images-list .upload-images-btn").parents(".attach-images-list").find(".new-image"),
		    files = [];
		    $.each(images, function (i, el) {
		        files.push($(el).find(".preview-btn").attr("src-orig"));
		    });
		    //navigator.notification.alert(files);
		    if (!busRecordId && $("#evId").length && !$("#evId").val()) {
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
	

	//上传多个照片后删除
	$(".surplusDelBtn").off().on("click",function(){
		if($(".noVal+#filePreviews tr").length>0){
			$("#filePreviews tbody").html("");
		}else{
			alertInfo("没有要删除的附件！");
		}
	});
	
	
   	if(_inApk){
   		$("#visaTab").text("记录区");
 		$("#visaInfo").text("信息区");
 		$("#materialChangeTab").text("变更区");
   	 }else{
  		$("#visaTab").text("签证记录");
  		$("#visaInfo").text("签证信息");
  		$("#materialChangeTab").text("材料变更");
   	 }
   	
   	

   	var sureClose=function(){
   			var cwId=$("#evId").val();
   			$.ajax({
   				type:'post',
   				url:'engineering/saveSignNotice',
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
   	$('input[type="radio"][name="cuReState"]').on("change",function(){
   		if($(this).val()=='0'){
			$(".cuReReason").removeClass("hidden");
		}else{
			$(".cuReReason").addClass("hidden");
		}
   	})
   	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->