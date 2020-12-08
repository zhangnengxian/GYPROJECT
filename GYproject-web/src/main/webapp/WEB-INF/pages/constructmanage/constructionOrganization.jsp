<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
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
					<h4 class="panel-title">施工组织</h4>
                </div>
                <div class="panel-body">
                	<%--<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>--%>
					<div class="toolBtn f-r p-b-10 drEditBtn">
						<a href="javascript:;" class="btn btn-confirm btn-sm checkRole drawingReviewUpdataBtn" >修改</a>
						<a href="javascript:;" class="btn btn-confirm btn-sm checkRole drawingReviewSaveBtn" >保存</a>
						<a href="javascript:;" class="btn  btn-primary btn-sm m-l-5 checkRole hidden get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>
					</div>
					<div class="clearboth form-box">
						<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
						<input type="hidden" id="loginPost"  value="${loginPost}"/>
						<input type="hidden" id="dataAdmin"  value="${dataAdmin}"/>

						<input type="hidden" name="customerServiceCenter" id="customerServiceCenter"  value="${customerServiceCenter}"/>
						<%--//值为2时文件格式支持多种（不影响其他页面功能写死）--%>
						<input type="hidden" id="changeType" value="2"/>
						<form class="form-horizontal" id="drawingAuditForm" action="constructionOrganization/saveFileConstructionOrganization"  enctype="multipart/form-data">
							<input type="hidden" id="auditState" name="auditState">
							<input type="hidden" id="result" name="result">
							<input type="hidden" id="accListId" name="accListId">
							<input type="hidden" id="menuDes" name="menuDes">
							<input type="hidden" name="stepId" id="stepId" />
							<input type="hidden" name="step" id="step" />
							<input type="hidden" name="alPath" id="alPath" />
							<input type="hidden" id="signState" name="signState">
							<input type="hidden"  id="cwSignState" name="cwSignState">
							<input type="hidden"id="deptDivide"name="deptDivide" value="">
							<input type="hidden" id="coId" name ="coId"  class="accBusRecordId" placeholder="施工组织表ID"/>
							<input type="hidden" id="operateId" name ="operateId"  />
							<input type="hidden" id="projId" name ="projId" placeholder="工程ID" />
							<input type="hidden" name ="corpId" />
							<input type="hidden" name ="corpName" />
							<input type="hidden" name ="tenantId" />
							<input type="hidden" name ="version" />
							<div class="form-group col-md-6 col-sm-12 clearboth">
								<label class="control-label" for="projNo">工程编号</label>
								<div>
								<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo" />
								</div>
							</div>
							<div class="form-group col-sm-12">
								<label class="control-label" for="projName">工程名称</label>
								<div>
								<input type="text" class="form-control input-sm field-not-editable" id="projName" name="projName" />
								</div>
							</div>
							<div class="form-group col-sm-12">
								<label class="control-label" for="projAddr">工程地点</label>
								<div>
								<input type="text" class="form-control input-sm field-not-editable" id="projAddr" name="projAddr" />
								</div>
							</div>
							<div class="form-group col-sm-12">
								<label class="control-label" for="custName">建设单位</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName"  />
								</div>
							</div>
							<div class="form-group col-sm-12">
								<label class="control-label" for="cuName">施工单位</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"  />
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="coType">类型</label>
								<div>
								<input type="text" class="form-control input-sm field-editable allText construction" id="coType" name="coType" data-parsley-maxlength="30"/>
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="bookNumber">册数</label>
								<div>
								<input type="text" class="form-control input-sm field-editable allText construction" id="bookNumber" name="bookNumber" data-parsley-maxlength="11"/>
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="pagesNumber">页数</label>
								<div>
								<input type="text" class="form-control input-sm field-editable allText construction" id="pagesNumber" name="pagesNumber" data-parsley-maxlength="11" />
								</div>
							</div>
							
							<div class="form-group col-md-12 col-sm-12">
								<label class="control-label proj_change" for="drawName">附件</label>
								<%--<label class="control-label cust_change" for="">变更方案附件</label>--%>
								<div>
									<div class="hidden hasVal">
										<input type="text" class="form-control input-sm field-not-editable allText" id="drawName" name="drawName"/>
										<a class="btn btn-sm btn-primary searchButton " href="" target="_blank">查看</a>
										<a class="btn btn-sm btn-primary Search_Button" href="javascript:">查看</a>
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
							<!-- <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="editor">编制人</label>
								<div>
								<input type="text" class="form-control input-sm field-editable" id="editor" name="editor" />
								</div>
							</div> -->
							<div class="form-group col-md-6 col-sm-12 allSign construction">
								<label class="control-label signature-tool" for="editor">编制人</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="editor" name="editor" data-parsley-required="true">
									<input type="hidden" class="signPost" id="editor_postType" name="editor_postType" value="${cuManager}" >
									<img class="editor" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label">编制日期</label>
								<div>
								<input type="text" class="form-control input-sm datepicker-default field-editable timestamp allText construction" id="editDate" name="editDate" />
								</div>
							</div>
							<!-- <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="checker">审核人</label>
								<div>
								<input type="text" class="form-control input-sm field-editable" id="checker" name="checker" />
								</div>
							</div> -->
							<div class="form-group col-md-6 col-sm-12 allSign cuPm">
								<label class="control-label signature-tool" for="checker">项目经理</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="checker" name="checker" data-parsley-required="true">
									<input type="hidden" class="signPost" id="checker_postType" name="checker_postType" value="${auditOfficerPost}" >
									<img class="checker" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
							</div>
							<div class="form-group col-sm-12">
								<label class="control-label" for="applicateDescription">申报简述</label>
								<div>
								<textarea class="form-control input-sm field-editable allText construction" row="2" id="applicateDescription" name="applicateDescription" data-parsley-maxlength="2000"></textarea>
								</div>
							</div>
							
							<div class="form-group col-sm-12">
								<label class="control-label" for="applicateDept">申报部门</label>
								<div>
								<input type="text" class="form-control input-sm field-editable allText construction" id=applicateDept name="applicateDept" data-parsley-maxlength="200"/>
								</div>
							</div>
							<!-- <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="applicant">申报人</label>
								<div>
								<input type="text" class="form-control input-sm field-editable" id="applicant" name="applicant" />
								</div>
							</div> -->
							<div class="form-group col-md-6 col-sm-12 allSign construction">
								<label class="control-label signature-tool" for="applicant">申报人</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="applicant" name="applicant" data-parsley-required="true">
									<input type="hidden" class="signPost" id="applicant_postType" name="applicant_postType" value="${cuManager}" >
									<img class="applicant" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
							</div>
							
							<!-- <div class="form-group col-md-6 col-sm-12">
								<label class="control-label signature-tool" for="projectLeader">项目负责人</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="projectLeader" name="projectLeader" data-parsley-required="true">
									<input type="hidden" id="projectLeader_postType" name="projectLeader_postType" value="" >
									<img class="projectLeader" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
							</div> -->
							
							<div class="form-group col-sm-12 ">
								<label class="control-label" for="technicalDept">技术部门</label>
								<div>
									<input type="text" class="form-control input-sm field-editable allText ceneralEngineer" id="technicalDept" name="technicalDept"  data-parsley-maxlength="200"/>
								</div>
							</div>
							<div class="form-group col-sm-12">
								<label class="control-label" for="techDeptAdvice">审批意见</label>
								<div>
									<textarea class="form-control input-sm field-editable allText ceneralEngineer" row="2" id="techDeptAdvice" name="techDeptAdvice"  data-parsley-maxlength="255"></textarea>
								</div>
							</div>
							<div class="form-group col-sm-12">
								<label class="control-label">有无附页</label>
								<div>
									<label> 
										<input type="radio" class="field-editable allText ceneralEngineer" name="isAttachPages" value="1"  checked="checked" />有
									</label>
									<label> 
										<input type="radio" class="field-editable allText ceneralEngineer" name="isAttachPages" value="0"/>无
									</label>
								</div>
							</div>
							
							<div class="form-group col-md-6 col-sm-12 allSign ceneralEngineer">
								<label class="control-label signature-tool" for="techDeptChecker">总工程师</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="techDeptChecker" name="techDeptChecker" data-parsley-required="true">
									<input type="hidden" class="signPost" id="techDeptChecker_postType" name="techDeptChecker_postType" value="${technialLeaderPost}" >
									<img class="techDeptChecker" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
							</div>
							
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label">审核日期</label>
								<div>
									<input type="text" class="form-control input-sm datepicker-default field-editable allText ceneralEngineer" id="techDeptCheckDate" name="techDeptCheckDate"  />
								</div>
							</div>
							<div class="form-group col-sm-12 reState hidden">
								<label class="control-label">是否已重报</label>
								<div>
									<label> <input type="radio" class="field-editable allText construction"
												name="reState" value="1"/>是
										</label>
										 <label> <input type="radio" class="field-editable allText construction"
												name="reState" value="0" />否
											</label>
								</div>
							</div>
							<div class="form-group col-sm-12 suUnit">
								<label class="control-label" for="suName">监理单位</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable" id="suName" name="suName" />
								</div>
							</div>
							
							<div class="form-group col-sm-12 suUnit">
								<label class="control-label" for="suAdvice">审核意见</label>
								<div>
									<textarea class="form-control field-editable allText suCse suJgj" name="suAdvice" id="suAdvice" rows="2" cols="" data-parsley-maxlength="199" ></textarea>
								</div>
							</div>
							
							<div class="form-group col-sm-12 suUnit">
								<label class="control-label">审核结论</label>
								<div>
								 <label>
									 <input type="radio" class="field-editable allText suCse suJgj" name="checkResult" value="1" checked/>通过
								 </label>
								 <label>
									 <input type="radio" class="field-editable allText suCse suJgj" name="checkResult" value="2" />修改后报
								 </label>
								 <label>
									 <input type="radio" class="field-editable allText suCse suJgj" name="checkResult" value="3" />重新编制
								 </label>
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12 allSign suJgj suUnit">
								<label class="control-label signature-tool" for="suJgjSign">现场监理</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_7"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="suJgjSign" name="suJgjSign" data-parsley-required="true">
									<input type="hidden" class="signPost" id="suJgjSign_postType" name="suJgjSign_postType" value="${suJgjPost}" >
									<img class="suChecker" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
							</div>

							<div class="form-group col-md-6 col-sm-12 suUnit">
								<label class="control-label">审核日期</label>
								<div>
									<input type="text" class="form-control input-sm datepicker-default field-editable allText suJgj" id="suJgjSignDate" name="suJgjSignDate"  />
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12 clearboth allSign suCse suUnit">
								<label class="control-label signature-tool" for="suChecker">总监</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="suChecker" name="suChecker" data-parsley-required="true">
									<input type="hidden" class="signPost" id="suChecker_postType" name="suChecker_postType" value="${suCseost}" >
									<img class="suChecker" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
							</div>

							<div class="form-group col-md-6 col-sm-12 suUnit">
								<label class="control-label">审核日期</label>
								<div>
									<input type="text" class="form-control input-sm datepicker-default field-editable allText ceneralEngineer" id="suCheckDate" name="suCheckDate"  />
								</div>
							</div>
							<hr style="width: 100%"/>
							<div class="form-group col-sm-12">
								<a style="cursor:pointer;display: block;margin: auto" onclick="showAbandonedRecord()">回退记录查看</a>
							</div>
						</form>



						<!-- 隐藏用于采集获取工程信息 -->
						<div class="hidden">
						<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="constructionOrganizationTable" width="100%">
	                			<thead>
		                			<tr>
		                			    <th>工程ID</th>
		                			    <th>工程编号</th>
		                			    <th>工程类型</th>
		                			</tr>
	                			</thead>
	                		</table>
	                		</div>
					</div>
				</div>
			</div>
		</div>
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
			    	<div class="iframe-report-box">
	                 	<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:0; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('施工组织 - 工程项目管理系统');
    //  //初始化上传文件控件
    $.getScript('projectjs/data/form-multiple-upload.demo3.js?'+Math.random()).done(function() {
        FormMultipleUpload.init($('#drawingAuditForm'));
    });
    //日期控件
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    if(!getProjectInfo()){
    	loadProjectList();
    }else{
        //表单格式化
		$("#drawingAuditForm").styleFit();

    	var projJson = getProjectInfo();
    	
    	 /* datatable按钮不加载
    	 $.getScript('assets/plugins/DataTables/media/js/jquery.dataTables.js').done(function () {
    	 	$.getScript('assets/plugins/DataTables/media/js/dataTables.bootstrap.min.js').done(function () {
    	 		$.getScript('assets/plugins/DataTables/extensions/Select/js/dataTables.select.min.js').done(function() {*/
    	    		$.getScript('js/ellipsis.js').done(function() { 
    					$.getScript('projectjs/constructmanage/construction_organization.js?'+Math.random()).done(function () {
           					constructionOrganization.init();
    					});
    				 });/*
    			});
    		});
    	}); */
        <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        %>
        function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}

        //加载打印预览
        var showReport = function(){
        	var src = getSrc();
        	$("#mainFrm").attr("src",src);
        };

        var getSrc = function(){
        	var projNo = $("#projNo").val(),
        	suName = $('#suName').val(),
        	projName=$('#projName').val(),
        	imgUrl = $('.imgUrl').val(),
            coId=$("#coId").val();
        	projName=encodeURIComponent(cjkEncode(projName));
        	suName=encodeURIComponent(cjkEncode(suName));
            coId = encodeURIComponent(cjkEncode(coId));
        	src="<%=basePath%>/ReportServer?reportlet=constructmanage/constructionOrganization.cpt";
        	src = src + "&projName="+projName +"&suName=" + suName + "&imgUrl="+imgUrl+"&coId="+coId;
        	console.log(imgUrl);
        	return src;
        }
        //打印预览窗口缩放调整
        if($(".iframe-report").length > 0){
        	var fr = $(".iframe-report");
        	for(var i=0; i<fr.length; i++){
        		fr.eq(i).rescaleReportPanel();
        	}
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
       // 附件保存回调1
        function saveBack(data){
            $(".drawingReviewSaveBtn").parent().parent().hideMask();
            var content = "数据保存成功！";
            if(data.result === "false"){
                content = "数据保存失败！";
            }else if(data.result === "already"){
           		content = "此信息已被修改，请刷新页面！";
           	}else {
                $("#coId").val(data.operateId);
				//$(".toolBtn").addClass("hidden");
                //$("#designAlterationTable").cgetData();  //列表重新加载
                drawingReviewDetail();
                $("#drawingAuditForm").toggleEditState(false).styleFit();
                $(".drawingReviewSaveBtn").addClass("hidden");
                $(".drawingReviewUpdataBtn").removeClass("hidden");
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

        //附件保存回调2
        var savedone = function(){
        	sureClose();
            if($("#drawName").val()){
                $(".hasVal").removeClass("hidden");
                $(".noVal").addClass("hidden");
                $(".noVal+#filePreviews tr").remove();
            }else{
                $(".noVal").removeClass("hidden");
                $(".hasVal").addClass("hidden");
            }
            showReport();
        }
        //点击保存
        $(".drawingReviewSaveBtn").off("click").on("click",function(){
        	//$("#drawingAuditForm").cformSave('',reviewSaveBack,false);
        	var t = $("#drawingAuditForm"),url = t.attr("action");
        	//开启表单验证
            if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
            	
            	if($("#cwSignState").val()=="0"){
            		alertInfo("请先完成交底会审，再进行施工组织！");
            		return false;
            	}
            	if($(".reState").not(".hidden").length>0 && ($('input[type="radio"][name="reState"]:checked').val()=='' || $('input[type="radio"][name="reState"]:checked').val()=='undefined' || $('input[type="radio"][name="reState"]:checked').val()==undefined)){
            		if($('input[type="radio"][name="checkResult"]:checked').val()!='1' && !($('input[type="radio"][name="reState"]').attr("readonly")=="readonly")){
            			alertInfo("请确认是否已重新编制施工组织！");
            			return false;
            		}
            	}
            	if($('input[type="radio"][name="checkResult"]:checked').val()!='1' && !($('input[type="radio"][name="checkResult"]').attr("readonly")=="readonly")){
            		var myoptions = {
	                         	title: "提示信息",
	                         	content: "您确定施工组织审核不通过吗？",
	                         	accept: saveDonFuc,
	                         	//chide: false,
	                         	icon: "fa-check-circle",
	                         	newpop:"second"
	                     }
	                     $("body").cgetPopup(myoptions);
            	}else{
            		saveDonFuc();
            	}
              //  drawingReviewDetail();
                //如果通过验证, 则移除验证UI
                t.parsley().validate();
            } else {
                //如果未通过验证, 则加载验证UI
                t.parsley().validate();
            };
         
        });
        //保存
        var saveDonFuc = function(){
        	var t = $("#drawingAuditForm"),url = t.attr("action");
        	$(".drawingReviewSaveBtn").parent().parent().loadMask("正在保存...", 1, 0.5);
       		if($("#deptDivide").val()==$("#customerServiceCenter").val()){
       		 if($("#editor").val()!="" && $("#checker").val()!="" && $("#applicant").val()!="" && $("#techDeptChecker").val()!=""){
   	        		//编制人 审核人 申报人 审核人 总监 现场监理
   	        		$("#signState").val("1");
   	           }else{
   	        		$("#signState").val("0");
   	       	   }
       		}else{
       		   if($("#editor").val()!="" && $("#checker").val()!="" && $("#applicant").val()!="" && $("#techDeptChecker").val()!="" && $("#suChecker").val()!="" && $("#suJgjSign").val() !="" && $('input[type="radio"][name="checkResult"]:checked').val()=='1'){
   	        		//编制人 审核人 申报人 审核人 总监 现场监理
   	        		$("#signState").val("1");
   	           }else{
   	        		$("#signState").val("0");
   	       	   }
       		}

       		var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
				$("#menuDes").val(menuDesc);
				$("#stepId").val(menuDesc);
				$("#step").val(getStepId());
				//工程
	               if($('input[type="radio"][name="checkResult"]:checked').val()=='2'||$('input[type="radio"][name="checkResult"]:checked').val()=='3'){
	            	   if(!($('input[type="radio"][name="checkResult"]').attr("readonly")=="readonly")){
	            		   $('input[type="radio"][name="reState"]:checked').val('');
	            	   }
	               }
               //表单序列化获取json字符串
               var data = t.serializeJsonString();
               
               $('#operateId').val(data.coId);
               $("#result").val(data);
               if($(".noVal+#filePreviews tr").length) {     //有附件
            	   if($(".noVal+#filePreviews tr").length>1){
            		   var popoptions = {
       	       				title: '提示',
       	       				content: '一次只能上传一张简图或附件，请重新上传！',
       	       				accept: surplusAccept,
       	       				chide: true,
       	                 	newpop: 'new'
           	       		};
           	       		$("body").cgetPopup(popoptions);
            	   }else{
            		   $(".start").click();
            	   }
               }else{                                        //无附件，无附件不能用有附件的方法， .start的click事件不会触发action。
                  // 防止多次提交
                   if(response){
                       response.abort();
                   }
                   var dataJson={};
                   dataJson.result=data;
					var response = $.ajax({
						url: 'constructionOrganization/saveConstructionOrganization',
						type: "POST",
						//timeout : 5000,
						contentType: "application/json;charset=UTF-8",
						data: JSON.stringify(dataJson),
						success: function (data) {
                           $(".drawingReviewSaveBtn").parent().parent().hideMask();
							var content = "数据保存成功！";
							if(data===false){
								content = "数据保存失败！";
							}else if(data === "already"){
			               		content = "此信息已被修改，请刷新页面！";
			               	}else{
								$('#coId').val(data);
								drawingReviewDetail();
							}
							var myoptions = {
		                         	title: "提示信息",
		                         	content: content,
		                         	accept: sureClose,
		                         	chide: true,
		                         	icon: "fa-check-circle",
		                            newpop: 'new'
		                     }
		                     $("body").cgetPopup(myoptions);
							 $("#drawingAuditForm").toggleEditState(false).styleFit();
				             $(".drawingReviewSaveBtn").addClass("hidden");
				             $(".drawingReviewUpdataBtn").removeClass("hidden");
						}
					});
				}
        };
      //点击修改按钮
        $(".drawingReviewUpdataBtn").off("click").on("click",function(){
        	$(".drawingReviewSaveBtn,.drEditForm,.drEditBtn2").removeClass("hidden");
        	$(".drawingReviewUpdataBtn").addClass("hidden");
            //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"drawingAuditForm");
//        	$("#drawingAuditForm").toggleEditState(true)

            if ($('#loginPost').val().indexOf($('#dataAdmin').val())!=-1){//数据处理员
                $(".allText").attr("readonly",false);
            }
        });

        var reviewSaveBack = function(){
        	showReport();
        }




		var showAbandonedRecord=function () {
			var param="?projId="+$("#projId").val()+"&stepId=1700";

			$('body').cgetPopup({//加载弹屏
				title: '审核记录',
				content: '#dataTableInfo/abandonedRecordPopPageDetail'+param,
				accept: function () {},
				size:'large'
			});
		}












        
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
    	})
        
    	
    	var sureClose=function(){
    		var cwId=$("#coId").val();
			$.ajax({
				type:'post',
				url:'constructionOrganization/saveSignNotice',
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
    	
        
        
//        $("#drawingAuditForm").toggleEditState(true).styleFit();
    }
   /* 定位
   if(_inApk){
		$('.mobileDiv').removeClass("hidden");
		$(".get-location").removeClass("hidden").off("click").on("click", function(){
			var t = $(this);
			t.button("loading");
			cgetLocation(function(position) {
		        $('[name="longitude"]').val(position.longitude);
		        $('[name="latitude"]').val(position.latitude);
				t.button("reset");
			});
		});
	} */
</script>
<!-- ================== END PAGE LEVEL JS ================== -->