<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- hiddenWorks.jsp -->
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
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
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表区</a></li>
		                <li class=""><a href="#default-tab-2" id="signTab"  data-toggle="tab">报验区</a></li>
		                <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" id="">
               		<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="hiddenWorksTable" width="100%">
	                			<thead>
		                			<tr>
		                				<th></th>
		                				<th>报验日期</th>
			                			<th>施工工序</th>
			                			<th>查验结果</th>
		                			</tr>
	                			</thead>
	                		</table>
	                	</div>
	                   	<div class="tab-pane fade in btn-top" id="default-tab-2">
	                       	<div class="toolBtn f-r p-b-10 editBtn">
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole saveBtn" >保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupBtn">放弃</a>
						    </div>
						    <div class="clearboth form-box">
								<form class="form-horizontal" id="hiddenWorksForm" data-parsley-validate="true" action="hiddenWorks/saveHiddenWorks">
									<input type="hidden" id="pcId"    name="pcId"    class="addClear">
								 	<input type="hidden" id="pcDesId" name="pcDesId" value="${pcDesId}">
								 	<input type="hidden" id="projNo"  name="projNo" >
								 	<input type="hidden" id="projId"  name="projId" >
									<div class="form-group  col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm" id="projName" name="projName"  />
										</div>
									</div>
									<div class="form-group  col-md-6 col-sm-12">
										<label class="control-label" for="process">隐蔽工序</label>
										<div> 
											<input type="text" class="form-control input-sm field-editable addClear"  id="process" name="process"  data-parsley-required="true"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="slResultPage">附件数量</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="slResultPage" name="slResultPage"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label " for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  id="constructionUnit" name="constructionUnit" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionPrincipal">项目经理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionPrincipal" name="constructionPrincipal" value="">
											<input type="hidden" id="constructionPrincipal_postType" name="constructionPrincipal_postType" value="${deputyDirector}">
											<img class="constructionPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectionDate">检验日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="inspectionDate" name="inspectionDate"  />
										</div>
									</div>
									
									
									<div class="form-group  col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="inspectionResult" id="inspectionResult" rows="3" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionQc">质检员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQc" name="constructionQc" value="">
											<input type="hidden" id="constructionQc_postType" name="constructionQc_postType" value="${qualitativeCheckMember}">
											<img class="constructionQc" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="selfCheckDate">自检日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="selfCheckDate" name="selfCheckDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionQae">质保师</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQae" name="constructionQae" value="">
											<input type="hidden" id="constructionQae_postType" name="constructionQae_postType" value="${constructionQaePost}" >
											<img class="constructionQae" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<!-- <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="">现场负责人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="sign_data_5" name="sign_data_5" value="">
											<img class="" alt="" src="images/sign-3.png" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="">检验日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable"  id="" name=""  />
										</div>
									</div> -->
									<div class="form-group  col-sm-12">
				                    	<label class="control-label" for="suOpinion">监理意见</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="suOpinion" id="suOpinion" rows="3" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group  col-sm-12">
										<label class="control-label" for="suName">监理公司</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"   id="suName" name="suName"  />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="suJgj">现场监理人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${sujgj}">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checkDate">日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="checkDate" name="checkDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="otherPerson">其他人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="otherPerson" name="otherPerson" value="">
											<img class="otherPerson" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="otherCheckDate">日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable"  id="otherCheckDate" name="otherCheckDate"  />
										</div>
									</div>
								</form>
								<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list" id="projectImagesList">
									     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a></h4>
									     <ul class="row">
									     </ul>
									</div>
								</div>
							</div>
	                   	</div>
	                   	<div class="tab-pane fade  btn-top" id="default-tab-3">
	                   		<div class="toolBtn f-r p-b-10 editbtn">
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole savebtnfile">保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupbtnfile">放弃</a>
						    </div>
                            <!-- <div class="toolBtn f-r p-b-10 editmodifybtn">
			                	<a href="javascript:;" class="btn btn-query btn-sm m-l-5 modifybtn">修改</a>
						    </div> -->
						    <div class="clearboth form-box">
		                   		<form class="form-horizontal" id="hiddenWorksRecordForm" action="hiddenWorks/saveHiddenWorksRecordFile" data-parsley-validate="true">
		                   			
		                   			<input type="hidden" id="hwId" name="hwId"/>
		                   			<input type="hidden" id="result" name="result">
									<input type="hidden" id="menuDes" name="menuDes">
									<input type="hidden" id="drawUrl" name="drawUrl">
									<input type="hidden" id="drawUrl1" name="drawUrl1" value="${drawUrl1}">
		                   			<div class="form-group  col-sm-12">
										<label class="control-label" for="hwItem">分项工程</label>
										<div>
											<input type="text" class="form-control input-sm field-editable "  id="hwItem" name="hwItem"  data-parsley-required="true"/>
										</div>
									</div>
							        <div class="form-group  col-sm-12 ">
							            <label class="control-label" for="hwHiding">隐蔽部位</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable"  id="hwHiding" name="hwHiding" />
							            </div>
							        </div>
							        <div class="form-group  col-sm-6 col-xs-12">
							            <label class="control-label" for="hwDrawNo">设计图号</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable"  id="hwDrawNo" name="hwDrawNo" />
							            </div>
							        </div>
							        
							        <div class="form-group  col-sm-6 col-xs-12">
							            <label class="control-label" for="hwMaterial">材质</label>
							            <div>
							            	<input type="text" class="form-control  input-sm field-editable"  id="hwMaterial" name="hwMaterial" />
							            </div>
							        </div>
							        <div class="form-group  col-sm-6 col-xs-12">
							            <label class="control-label" for="hwSpec">规格</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable"  id="hwSpec" name="hwSpec" />
							            </div>
							        </div>
							        <div class="form-group   col-sm-6 col-xs-12">
							            <label class="control-label" for="hwUnit">单位</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable"  id="hwUnit" name="hwUnit" />
							            </div>
							        </div>
							        <div class="form-group  col-sm-6 col-xs-12">
							            <label class="control-label" for="hwNum">数量</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable"  id="hwNum" name="hwNum" />
							            </div>
							        </div>
							        <div class="form-group col-sm-12 ">
							            <label class="control-label" for="hwCheckResult">检查结果</label>
							            <div> 
					                        <textarea class="form-control field-editable" name="hwCheckResult" id="hwCheckResult" rows="7" cols="" data-parsley-maxlength="300" ></textarea>
						                </div>
							        </div>
							        
							        <div class="form-group col-sm-12">
									<label class="control-label" for="">隐蔽简图</label>
									<div class="hidden hasVal"> 
					         			<input type="text" class="form-control input-sm" id="drawName" name="drawName"/>
				         		        <a target="_blank" class="btn btn-sm btn-primary Search_Button" href="javascript:">查看</a>
				         		        <a class="del_btn btn btn-sm btn-danger"><i class="fa fa-times"></i> 删除</a>
				         		    </div>
				         		    <div>
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
							        
							        <div class="form-group col-sm-12 ">
							            <label class="control-label" for="">文字简图</label>
							            <div> 
					                        <textarea class="form-control field-editable" name="hwExplain" id="hwExplain" rows="3" cols="" data-parsley-maxlength="200" ></textarea>
						                </div>
							        </div>
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
			    <div class="panel-body" id="hiddenWorks_audit_panel_box">
			    	 
	                <div class="clearboth form-box">
	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:1; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('隐蔽工程 - 工程项目管理系统 ');
    $.getScript('projectjs/data/form-multiple-upload.demo3.js').done(function() {
    	FormMultipleUpload.init($('#hiddenWorksRecordForm'));
    });
    $("#hiddenWorksForm").toggleEditState().styleFit();
    $("#hiddenWorksRecordForm").toggleEditState().styleFit();
    $(".editBtn").addClass("hidden");
   // $(".editbtn").addClass("hidden"); 
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	console.info(JSON.stringify(getProjectInfo()));
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	console.info('conUnit:'+projJson.managementOffice);
    	$('#constructionUnit').val(projJson.cuName); //施工单位
    	console.info('suname:'+projJson.suName);
    	$('#suName').val(projJson.suName);					   //监理公司
    }
    
    var cptPath = '<%=basePath%>';
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var hiddenWorks1 = function(){
    	var projName='',suName='',process='',slResultPage='',constructionUnit='',inspectionDate='',selfCheckDate='',inspectionResult='',suOpinion='',checkDate='';
    	var json=trSData.hiddenWorksTable.json;
    	if(json){
	   	    projName = json.projName,			   //工程名称
	   		suName=json.suName,					   //监理单位
	   		process=json.process,			       //隐蔽工序
	   		slResultPage=json.slResultPage,    	   //页数
	   		constructionUnit=json.constructionUnit,//施工单位
	   		inspectionDate=json.inspectionDate,	   //报验日期
	   		inspectionResult=json.inspectionResult,//查验结果
	   		selfCheckDate=json.selfCheckDate,	   //自检日期
	   		suOpinion=json.suOpinion,			   //监理单位意见
	   		checkDate=json.checkDate;			   //检查日期
    	}else{
   			projName =$('#projName').val(),			    //工程名称
       		suName=$('#suName').val(),				    //监理单位
       		process=$('#process').val(),			    //施工工序
       		slResultPage=$('#slResultPage').val(),      //页数
       		constructionUnit=$('#constructionUnit').val(),//施工单位
       		inspectionDate=$('#inspectionDate').val(),    //报验日期
       		inspectionResult=$('#inspectionResult').val(),//查验结果
       		selfCheckDate=$('#selfCheckDate').val(),	  //自检日期
       		suOpinion=$('#suOpinion').val(),			  //监理单位意见
       		checkDate=$('#checkDate').val();			  //检查日期
   		}
   		//解决乱码
       	projName = encodeURIComponent(cjkEncode(projName)),				//工程名称
       	suName=encodeURIComponent(cjkEncode(suName)),					//监理单位
       	process=encodeURIComponent(cjkEncode(process)),			    	//检验部位
       	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)),//施工单位
       	slResultPage=encodeURIComponent(cjkEncode(slResultPage)),       //页数
       	inspectionResult=encodeURIComponent(cjkEncode(inspectionResult)),//查验结果
       	suOpinion=encodeURIComponent(cjkEncode(suOpinion));		     //监理单位意见
    	
    	src = cptPath+"/ReportServer?reportlet=hiddenWorks/hiddenWorks.cpt&projName=" + projName+"&suName="+suName+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate;
    	src = src+"&inspectionDate="+inspectionDate+"&checkDate="+checkDate+"&suOpinion="+suOpinion+"&process="+process+"&slResultPage="+slResultPage+"&inspectionResult="+inspectionResult;
    	src = src+"&path=${path}"+"&pcId="+$('#pcId').val();
    	$("#mainFrm").attr("src",src);
    	
    	if($(".iframe-report").length > 0){
        	var fr = $(".iframe-report");
        	for(var i=0; i<fr.length; i++){
        		fr.eq(i).rescaleReportPanel();
        	}
        } 
    },
    
    hiddenWorks2 = function(){
    	
    	/* var projName='',suName='',process='',hwItem='',constructionUnit='',hwHiding='',hwDrawNo='',hwMaterial='',hwSpec='',hwUnit='',hwNum='',hwCheckResult='',hwExplain='',otherCheckDate='',checkDate='',inspectionDate='';
    	
    	projName =$('#projName').val(),			      //工程名称
   		suName=$('#suName').val(),				      //监理单位
   		constructionUnit=$('#constructionUnit').val(),//施工单位
   		hwItem=$('#hwItem').val(),			    	  //分部分项
   		hwHiding=$('#hwHiding').val(),      		  //隐蔽部位
   		hwDrawNo=$('#hwDrawNo').val(),    			  //设计图号
   		
   		hwMaterial=$('#hwMaterial').val(),			  //材质
   		hwSpec=$('#hwSpec').val(),	  			 	  //规格
   		hwUnit=$('#hwUnit').val(),			  		  //单位
   		
   		hwNum=$('#hwNum').val(),			  		  //数量
   		hwCheckResult=$('#hwCheckResult').val(),	  //检查内容结果
   		hwExplain=$('#hwExplain').val(),    		  //说明简图
   		inspectionDate=$('#inspectionDate').val();    //报验日期
   		checkDate=$('#checkDate').val(),			  //检查日期
   		otherCheckDate=$('#otherCheckDate').val();	  //其他人检查日期
   		projName = encodeURIComponent(cjkEncode(projName)),	
       	suName=encodeURIComponent(cjkEncode(suName)),
       	hwMaterial=encodeURIComponent(cjkEncode(hwMaterial)),
       	//hwSpec=encodeURIComponent(cjkEncode(hwSpec)),
       	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)),
       	hwItem=encodeURIComponent(cjkEncode(hwItem)),	
       	hwHiding=encodeURIComponent(cjkEncode(hwHiding)),	
       	hwDrawNo=encodeURIComponent(cjkEncode(hwDrawNo)),	
       	
       	hwUnit=encodeURIComponent(cjkEncode(hwUnit)),
       	hwNum=encodeURIComponent(cjkEncode(hwNum)),
       	hwCheckResult=encodeURIComponent(cjkEncode(hwCheckResult)),
       	hwExplain=encodeURIComponent(cjkEncode(hwExplain));
   		src = cptPath+"/ReportServer?reportlet=hiddenWorks/hiddenWorks1.cpt&projName=" + projName+"&suName="+suName+"&constructionUnit="+constructionUnit+"&hwItem="+hwItem+"&inspectionDate="+inspectionDate+"&checkDate="+checkDate;
    	src = src+"&hwHiding="+hwHiding+"&hwDrawNo="+hwDrawNo+"&hwMaterial="+hwMaterial+"&hwSpec="+hwSpec+"&hwUnit="+hwUnit+"&hwNum="+hwNum+"&hwCheckResult="+hwCheckResult+"&hwExplain="+hwExplain+"&otherCheckDate="+otherCheckDate;
    	src = src+"&path=${path}"+"&pcId="+$('#pcId').val();
    	$("#mainFrm").attr("src",src); 	
    	if($(".iframe-report").length > 0){
        	var fr = $(".iframe-report");
        	for(var i=0; i<fr.length; i++){
        		fr.eq(i).rescaleReportPanel();
        	}
        }  */
    	var projName='',imgUrl='',drawUrl1='',menuDes='',pcId='',constructionUnit='',suName='',selfCheckDate='',checkDate='',otherCheckDate='',inspectionDate='';
        imgUrl = encodeURIComponent(cjkEncode($(".imgUrl").val())),	
    	drawUrl1 = encodeURIComponent(cjkEncode($("#drawUrl1").val())),
    	menuDes=encodeURIComponent(cjkEncode($("#menuDes").val()));
        if(trSData.hiddenWorksTable.json){
	    	projName=trSData.hiddenWorksTable.json.projName;
	    	//pcId =trSData.trenchBackfillTable.json.pcId;
	    	constructionUnit= trSData.hiddenWorksTable.json.constructionUnit;
	    	suName= trSData.hiddenWorksTable.json.suName;
	    	selfCheckDate=trSData.hiddenWorksTable.json.selfCheckDate;
	    	checkDate = trSData.hiddenWorksTable.json.checkDate;
	    	otherCheckDate = trSData.hiddenWorksTable.json.otherCheckDate;
	    	inspectionDate=trSData.hiddenWorksTable.json.inspectionDate;
	    	//console.log(checkDate);
	    }else{
	    	pcId = $('#pcId').val();
	    	projName =$('#projName').val();			      //工程名称
    		suName=$('#suName').val();			      //监理单位
    		constructionUnit=$('#constructionUnit').val();//施工单位
    		selfCheckDate=$('#selfCheckDate').val();    //报验日期
    		checkData=$('#checkDate').val();			  //监理检查日期
    		inspectionDate=$('#inspectionDate').val();    //报验日期
    		otherCheckDate=$('#otherCheckDate').val();	  //其他人检查日期
	    }
   	    projName=encodeURIComponent(cjkEncode(projName));    	    
   	    constructionUnit=encodeURIComponent(cjkEncode(constructionUnit));
   	    suName=encodeURIComponent(cjkEncode(suName));
   		var src="<%=basePath%>/ReportServer?reportlet=hiddenWorks/hiddenWorks1.cpt&projName="+projName;
   			src=src+"&constructionUnit="+constructionUnit+"&suName="+suName+"&selfCheckDate="+selfCheckDate+"&checkDate="+checkDate+"&otherCheckDate="+otherCheckDate+"&inspectionDate="+inspectionDate+"&imgUrl="+imgUrl+"&drawUrl1="+drawUrl1+"&menuDes="+menuDes;
   			src = src+"&path=${path}"+"&pcId="+$('#pcId').val()+"&hwId="+$("#hwId").val();	
   			$("#mainFrm").attr("src",src);
    }
	
     var showReport3 = function(){
    	var projName='',constructionUnit='',suName='';
    	
   		projName =$('#projName').val(),			      //工程名称
   		suName=$('#suName').val(),				      //监理单位
   		constructionUnit=$('#constructionUnit').val();//施工单位
   		
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName)); 
    	var src = cptPath+"/ReportServer?reportlet=hiddenWorks/hiddenWorks.cpt&projName="+projName+"&suName="+suName+"&constructionUnit="+constructionUnit;
    	$("#mainFrm").attr("src",src); 
    } 
    
  	//签字加载方式
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4");
    	signatures.handleSignature();        	    	
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
    
    
    
    $.getScript('projectjs/inspection/alarm-hiddenWorks-audit.js').done(function () {
        hiddenWorksAndAudit.init();
	});
	
  //报验区点击放弃
	$('.giveupBtn').off().on('click',function(){
		//返回列表区
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
    })
    
    //报验区保存工程报验单
    $('.saveBtn').off().on("click",function(){
    	$('#hiddenWorksForm').cformSave('hiddenWorksTable',saveHiddenBack,false);
    })
    
    var saveHiddenBack=function(data){
		$('.editBtn').addClass("hidden");
		$('#hiddenWorksForm').toggleEditState(false);
		if(data!==false){
			$('#pcId').val(data);
		}
		
		var pcId = data,
		projNo = $("#projNo").val();
		projId = $("#projId").val();
		
		if(_inApk && $(".attach-images-list").find(".new-image").length){
			preImagesUpload($(".attach-images-list .uploadBtn"), projId, projNo, getStepId(), pcId);
		};

		//刷新帆软报表
    	hiddenWorks1();
  	}
  
  
  
	//图片查看功能
    $('.Search_Button').off("click").on("click",function(){  
    	console.info("图片查看功能 drawurl:"+$("#drawUrl").val());
    	$("body").cgetPopup({ 
			title: '查看图片',
			content: '<div class="preview-box"><img src="attachments/diagram/' +$("#drawUrl").val() + '" class="preview-image"></div>',
			accept: function(){},
			atext: '关闭',
			chide: true,
			size: 'large',
			icon: 'fa-pencil-square-o'
		});
    });
  
/* 	//记录区修改
    $('.modifybtn').off().on('click',function(){
    	var json=trSData.hiddenWorksTable.json;
    	if(json){
    		$('.editmodifybtn').addClass('hidden');
    	    $('.editbtn').removeClass("hidden");
    	    $('#hiddenWorksRecordForm').toggleEditState(true);
    	}else{
    		//提示没有选中报验单
			$("body").cgetPopup({
		       	title: "提示信息",
		       	content: "报验单不存在，不允许修改!",
		       	accept: successBack,
		       	chide: true,
		       	icon: "fa-check-circle",
		       	newpop: 'new'
		    });
	    	return false; 
    	}
    }); */
    
    function saveBack(data){
       	var content = "数据保存成功！";
       	if(data === "false"){
       		content = "数据保存失败！";
       	}else {
       		$("#hwId").val(data.operateId);
       		$("#drawUrl").val(data.drawUrl);
       		$(".editBtn").addClass("hidden");
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
    	
      	 if($("#drawName").val()){
      		 $(".hasVal").removeClass("hidden");
      		 $(".noVal").addClass("hidden");
      		 $(".noVal+#filePreviews tr").remove();
      	 }else{
      		 $(".noVal").removeClass("hidden");
      		 
      		 $(".hasVal").addClass("hidden");
      	 }
      	
      	$("#hiddenWorksRecordForm").toggleEditState(true).styleFit();
      	hiddenWorks2();
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
		console.info($("#drawName").val());
		$(".hasVal").addClass("hidden");
		$(".noVal").removeClass("hidden");
		
	}
	
    //记录区放弃
    $('.giveupbtnfile').off().on('click',function(){
	   /*  $('.editmodifybtn').removeClass('hidden'); */
	  /*   $('.editbtn').addClass("hidden"); */
	   /*  $('#hiddenWorksRecordForm').toggleEditState(true); */
    	if($('#hwId').val()!==''){
			var data={};
			data.id=$('#pcId').val();
			$('#hiddenWorksRecordForm').getFormDetail('',data,'');
		}else{
			$('#hiddenWorksRecordForm').formReset();
		}
    });
    
    //记录区保存
    $('.savebtnfile').off().on('click',function(){
    		if($(".noVal+#filePreviews tr").length){
    			
    		 	//序列化
    	       	var menuDesc = $('[data-mid="' + getStepId() + '"] span').text();
    	       	$("#menuDes").val(menuDesc);
    	       	var data=$("#hiddenWorksRecordForm").serializeJson();
    	         	data.pcId=$('#pcId').val();
    	       	$("#result").val(JSON.stringify(data));
    	       	$(".start").click();   
    		}else{
    			//$("#designAlterationForm").cformSave('designAlterationTable',saveSurveyBack,false);
    		 	var dataJson=$("#hiddenWorksRecordForm").serializeJson();
    		 	dataJson.pcId=$('#pcId').val();
    		 	var data={};
    		 	data.result=JSON.stringify(dataJson);
    			$.ajax({
    	               type: 'POST',
    	               url: 'hiddenWorks/saveHiddenWorksRecord',
    	               contentType: "application/json;charset=UTF-8",
    	               data: JSON.stringify(data),
    	               success: function (data) {
    	               	var content = "数据保存成功！";
    	               	if(data === "false"){
    	               	   content = "数据保存失败！";
    	               	}else if(data === "true"){
    	               		$(".editBtn").removeClass("hidden");
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
    	               },
    	               error: function (jqXHR, textStatus, errorThrown) {
    	                   console.warn("隐蔽信息保存失败！");
    	               }
    	           });
    		}
    		
    });
	var successBack=function(){}
	//移动端点击上传
    $(".uploadBtn").off("click").on("click",function(){
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
</script>

<!-- ================== END PAGE LEVEL JS ================== -->