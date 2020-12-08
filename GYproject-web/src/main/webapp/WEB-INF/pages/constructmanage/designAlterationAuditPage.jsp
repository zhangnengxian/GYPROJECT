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
                    <h4 class="panel-title">变更记录</h4>
                </div>
                <div class="panel-body">
	                	<div class="tab-pane fade in btn-top" id="alteration_record">
		                     <div class="toolBtn f-r p-b-10" >
							     <a href="javascript:;" class="btn btn-confirm btn-sm saveBtn">保存</a>
							     <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 giveupBtn">放弃</a>
						     </div>
						     <div class="returnBtn f-r p-b-10 hidden">
								<a href="javascript:;" class="btn btn-warn btn-sm giveupBtn">返回</a>
							 </div>
							 <div class=" clearboth form-box">
							 	<form class="form-horizontal" action="designAlteration/saveDesignAlterationFile" method="POST" enctype="multipart/form-data" id="designAlterationForm" data-parsley-validate="true" >
									<input type="hidden" id="result" name="result">
									<input type="hidden" id="menuDes" name="menuDes">
									<input type="hidden" id="cmId" name="cmId" value="${cmId}" class="">
									<input type="hidden" id="drawUrl" name="drawUrl">
									<input type="hidden" id="drawUrl1" name="drawUrl1" value="${drawUrl1}">
									<input type="hidden" id="cmState" name="cmState" value="${cmState}" >
									<input type="hidden" name="projLtypeId" id="projLtypeId"/>
									<input type="hidden" name="stepId" id="stepId" />
									<input type="hidden" name="alPath" id="alPath" />
									<input type="hidden" name="isAudit" id="isAudit" value="${isAudit}">
									<input type="hidden" name="version"/>
									<input type="hidden" class="auditLevel" name="auditLevel"  value="${auditLevel}">
									<div class="form-group col-sm-6">
										<label class="control-label" for="changeType">变更类型</label>
										<div>
											<select class="form-control input-sm field-not-editable" id="changeType" name="changeType" data-parsley-required="true">
												<c:forEach var="enums" items="${changeType}">
													<option value="${enums.value}" >${enums.message}</option>
								                </c:forEach>
											</select>
										</div>
									</div>
									<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
							 	    <input type="hidden" class="form-control input-sm field-not-editable" id="projId" name="projId">
							 	    <div class="form-group col-md-6 col-sm-12 clearboth">
										<label class="control-label" for="">变更日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-not-editable" id="cmDate" name="cmDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">变更编号</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable addClear" id="cmNo" name="cmNo"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">工程编号</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo"  />
										</div>
									</div>
									
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
												<input type="text" class="form-control input-sm field-not-editable " id="cmAdvanceUnit" name="cmAdvanceUnit" />
											</div>
										</div>
										<!-- <div class="form-group col-md-6 col-sm-12 duChange">
											<label class="control-label" for="">联系电话</label>
											<div>
												<input type="text" class="form-control input-sm field-editable" id="cuPhone" name="cuPhone"  />
											</div>
										</div> -->
										
							    		<!-- <div class="form-group col-sm-12 duChange">
						     				<label class="control-label" for="">申报单位</label>
						    				<div> 
						         				<input type="text" class="form-control input-sm field-not-editable" id="custName" name="custName"/>
					         				</div>
						    		    </div> -->
						    		    
										<div class="form-group col-sm-12">
						     				<label class="control-label" for="">变更原因</label>
						    				<div> 
						         				<textarea class="form-control field-not-editable " name="cuReason" id="cuReason" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					         				</div>
						    		    </div>
						    		    <div class="form-group col-sm-12">
						     				<label class="control-label" for="changeContent">变更内容</label>
						    				<div> 
						         				<textarea class="form-control field-not-editable " name="changeContent" id="changeContent" rows="5" cols="" data-parsley-maxlength="500" ></textarea>
					         				</div>
						    			</div>
						    		    <div class="form-group col-sm-12 projectChange">
						     				<label class="control-label" for="drawFileType">附件类型</label>
						    				<div> 
						         				<label> <input type="radio" class="field-not-editable "
												name="drawFileType" value="1" />变更内容
											</label>
											 <label> <input type="radio" class="field-not-editable"
													name="drawFileType" value="2"  />变更设计图
												</label>
											 <label> <input type="radio" class="field-not-editable"
													name="drawFileType" value="3"  />相关会议纪要
												</label>
												<label> <input type="radio" class="field-not-editable"
													name="drawFileType" value="4" />其他
												</label>
					         				</div>
						    		    </div>
						    		    <div class="form-group col-sm-12">
											<!-- <label class="control-label proj_change" for="">变更方案简图</label> -->
											<label class="control-label projectChange" for="">变更附件</label>
											<div>
											<div class="hidden hasVal"> 
							         			<input type="text" class="form-control input-sm field-not-editable" id="drawName" name="drawName"/>
							         			<a class="btn btn-sm btn-primary searchButton " href="" target="_blank">查看</a>
						         		        <!-- <a class="btn btn-sm btn-primary Search_Button" href="javascript:">查看</a> -->
						         		        <!-- <a class="del_btn btn btn-sm btn-danger"><i class="fa fa-times"></i> 删除</a> -->
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
													<input type="text" class="form-control input-sm  field-not-editable" id="cmProjQuantity" name="cmProjQuantity"  data-parsley-maxlength="50"/>
												</div>
											</div>
											<div class="form-group col-sm-12 clearboth">
												<label class="control-label" for="">费用量增/减</label>
												<div>
													<input type="text" class="form-control input-sm  field-not-editable" id="cmCost" name="cmCost"  data-parsley-maxlength="50"/>
												</div>
											</div>
											<div class="form-group col-sm-12 clearboth">
												<label class="control-label" for="cmTimeLimit">工期变化</label>
												<div>
													<input type="text" class="form-control input-sm field-not-editable" id="cmTimeLimit" name="cmTimeLimit"  data-parsley-maxlength="50"/>
												</div>
											</div>
											<div class="form-group col-md-6 col-sm-12 clearboth">
												<input type="hidden" class="form-control input-sm field-editable" id="cmAdvanceStaffId" name="cmAdvanceStaffId"   data-parsley-maxlength="30"/>
												<label class="control-label" for="cmAdvanceStaffName">提出用户</label>
												<div>
													<input type="text" class="form-control input-sm field-not-editable" id="cmAdvanceStaffName" name="cmAdvanceStaffName"   data-parsley-maxlength="50"/>
												</div>
											</div>
											
											<div class="form-group col-md-6 col-sm-12 clearboth">
												<label class="control-label" for="cmReceiveUnit">通知部门(单位)</label>
												<div>
													<input type="text" class="form-control input-sm field-not-editable" id="cmReceiveUnit" name="cmReceiveUnit"  data-parsley-maxlength="200"/>
												</div>
											</div>
											<div class="form-group  col-sm-12 clearboth">
												<label class="control-label" for="cuManagerDept">施工项目经理部</label>
												<div>
													<input type="text" class="form-control input-sm field-not-editable" id="cuManagerDept" name="cuManagerDept"  data-parsley-maxlength="200"/>
												</div>
											</div>
										
										<div class="form-group col-md-6 col-sm-12 clearboth otherSign">
											<label class="control-label signature-tool" for="">项目经理</label>
											<div>
												<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
												<input type="hidden" class="sign-data-input" id="cuPm" name="cuPm" >
												<input type="hidden" id="cuPm_postType" name="cuPm_postType" value="${cuPm }" >
												<img class="cuPm" alt="" src="images/sign-1.png" style="height: 30px">
												<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
											</div>
							    		</div>
						    		    
										<!-- <div class="form-group  col-sm-12 ">
											<label class="control-label" for="duName">设计单位</label>
											<div>
												<input type="text" class="form-control input-sm field-not-editable" id="duName" name="duName"  />
											</div>
										</div> -->
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
										<div class="form-group  col-sm-12 ">
											<label class="control-label" for="suName">监理机构</label>
											<div>
												<input type="text" class="form-control input-sm field-not-editable" id="suName" name="suName"  />
											</div>
										</div>
										<div class="form-group col-md-6 col-sm-12 clearboth otherSign">
											<label class="control-label signature-tool" for="suCes">总监理工程师</label>
											<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suCes" name="suCes" value="">
											<input type="hidden"  id="suCes_postType" name="suCes_postType" value="${suCesPost }">
											<img class="suCes" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
										</div>
						    			<div class="form-group col-sm-12">
											<label class="control-label" for="corpName">建设单位</label>
											<div>
												<input type="text" class="form-control input-sm field-not-editable" id="corpName" name="corpName"  />
											</div>
										</div>
										<div class="form-group col-md-6 col-sm-12 clearboth otherSign">
											<label class="control-label signature-tool" for="custLeader">负责人</label>
											<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="custLeader" name="custLeader" value="">
											<input type="hidden"  id="custLeader_postType" name="custLeader_postType" value="${custLeaderPost }">
											<img class="custLeader" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
											</div>
							    		</div>
							    		<div class="form-group  col-sm-12 ">
											<label class="control-label" for="duName">设计单位</label>
											<div>
												<input type="text" class="form-control input-sm field-not-editable" id="duName" name="duName"  />
											</div>
										</div>
							    		<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
						        			<label class="control-label" for="">审批结果</label>
							            	<div>
							             		 <label>
									            	<input type="radio" name="auditState" value="1"/>不通过
									            </label>
									             <label>
									            	<input type="radio" name="auditState" value="3" checked/>通过
									            </label>
									        </div>
					    				</div>
							    		<div class="form-group col-sm-12">
											<label class="control-label" for="">设计院意见</label>
											<div>
												<textarea class="form-control field-editable"  data-parsley-required="true" name="duOpinion" id="duOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
											</div>
										</div>
										<div class="form-group col-md-6 col-sm-12">
											<label class="control-label signature-tool" for="">设计单位签字</label>
											<div>
												<a href="javascript:;" class="btn btn-sm btn-white " id="signBtn_8"><i class="fa fa-pencil"></i></a>
												<input type="hidden" class="sign-data-input" id="duPrincipal" name="duPrincipal" >
												<input type="hidden" id="duPrincipal_postType" name="duPrincipal_postType" value="" >
												<img class="duPrincipal" alt="" src="" style="height: 30px">
												<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
											</div>
							    		</div>
								    	<div class="form-group col-md-6 col-sm-12">
											<label class="control-label" for="">审核日期</label>
											<div>
												<input type="text" class="form-control input-sm datepicker-default field-editable" id="auditDate" name="auditDate"  />
											</div>
										</div>
						    		</div>
								</form>
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
						<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:1; overflow-y:auto;" scrolling="no"></iframe>
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
 	//是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	$("#projId").val(getProjectInfo().projId);
    }
 	function saveBack(data){
           	var content = "数据保存成功！";
           	if(data.result === "false"){
           		content = "数据保存失败！";
           	}else {
           		$("#cmId").val(data.operateId);
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
    $('.CnChange').addClass('hidden');
    //日期
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });
    //签字加载方式
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6,#signBtn_7,#signBtn_8");
    	signatures.handleSignature();        	    	
    });
    //帆软 路径
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %> 
    var cptPath = '<%=basePath%>';
    //加载打印预览/ 施工变更
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
		src = src+"&projAddr="+projAddr+"&projScaleDes="+projScaleDes+"&cmDate="+cmDate+"&cuPm="+cuPm+"&cuPhone="+cuPhone+"&cuReason="+cuReason+ "&imgUrl="+imgUrl;
		src = src+"&cuProposal="+cuProposal+"&managementOpinion="+managementOpinion+"&suOpinion="+suOpinion+"&custOpinion="+custOpinion+"&cmId="+cmId+"&projId="+projId+"&drawUrl1="+drawUrl1+"&menuDes="+menuDes;
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
	$.getScript('projectjs/constructmanage/design-alteration-audit-page.js?'+Math.random()).done(function () {
	 	designAlteration.init();
	});
   
    //保存
    $('.saveBtn').off("click").on("click",function(){       	
    	//验证
    	if($("#designAlterationForm").parsley().isValid()){
            	$("#alteration_record").parent().parent().loadMask("正在保存...", 1, 0.5);
    		 	var dataStr=$("#designAlterationForm").serializeJsonString();
    		 	var data={};
    		 	data.result=dataStr
    			$.ajax({
    	               type: 'POST',
    	               url: 'designAlteration/saveDesignAlterationAudit',
    	               contentType: "application/json;charset=UTF-8",
    	               dataType:"json",
    	               data: JSON.stringify(data),
    	               success: function (data) {
                        $("#alteration_record").parent().parent().hideMask();
    	               	var content = "数据保存成功！";
    	               	if(data.ret_message === "false"){
                            alertInfo("数据保存失败！");
    	               	}else if(data.ret_message =="already"){
                            alertInfo("版本冲突，需刷新页面！");
    	               	}else{
                            showReport();
                            if($("#drawName").val()){
                                $(".hasVal").removeClass("hidden");
                                $(".noVal+#filePreviews tr").remove();
                            }else{
                                $(".hasVal").addClass("hidden");
                            }
                            $("#designAlterationForm").styleFit();
                            $(".toolBtn").addClass("hidden");
                            $(".returnBtn").removeClass("hidden");
                            alertInfo("保存数据成功!");
						}},
    	               error: function (jqXHR, textStatus, errorThrown) {
    	                   console.warn("变更信息保存失败！");
    	               }
    	           });
        	//如果通过验证, 则移除验证UI
       		$("#designAlterationForm").parsley().reset();
       }else{
       	//如果未通过验证, 则加载验证UI
       	$("#designAlterationForm").parsley().validate();
       }  
    	$(".daEditBtn").addClass("hidden");
		$("#designAlterationForm").toggleEditState(false);
    });
    //点击放弃
  	var savedone = function(){
      		showReport();
     	 if($("#drawName").val()){
     		 $(".hasVal").removeClass("hidden");
     		 $(".noVal+#filePreviews tr").remove();
     	 }else{
     		 $(".hasVal").addClass("hidden");
     	 }
     	  $("#designAlterationForm").styleFit();
     	 $(".toolBtn").addClass("hidden");
		 $(".returnBtn").removeClass("hidden");
     	}
 // 变更类型
    $("#changeType").change(function(){
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
            $("#designAlterationForm").styleFit();
            
    	});
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->