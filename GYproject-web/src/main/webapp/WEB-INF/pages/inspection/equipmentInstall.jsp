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
								<form class="form-horizontal" id="signForm" data-parsley-validate="true" action="equipmentInstall/saveSign">							
								    <input type="hidden" id="pcDesId" name="pcDesId" value="${checkType}"/> 
									<input type="hidden" id="pcId" name="pcId" class="addClear"/>
									<input type="hidden" id="projId" name="projId" >
									<input type="hidden" id="version" name="version" class="addClear"/>
									<input type="hidden" id="recordsId" name="recordsId">
									<input type="hidden" id="finishedDate" name="finishedDate">
									<input type="hidden" id="finishedOpr" name="finishedOpr">
									<input type="hidden" id="resetDate" name="resetDate">
									<input type="hidden" id="resetReason" name="resetReason">
									<input type="hidden" id="isFinishSign" name="isFinishSign">
									<!-- 完成标记 -->
									<input type="hidden" id="flag" name="flag">
									<input type="hidden" id="insDate" name="insDate">
									<input type="hidden" id="status" name="status">
									<input type="hidden" id="sendDate" name="sendDate">
									<input type="hidden" id="upgradeDate" name="upgradeDate" value="${upgradeDate}">
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
									<div class="form-group  col-sm-6">
										<label class="control-label">报验日期</label>
										<div>
											<input type="text" class="form-control datepicker-default field-not-editable input-sm addClear"  id="inspectionDate" name="inspectionDate" />
										</div>
									</div>
									<div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear allText construction" name="inspectionResult" id="inspectionResult" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
									 <div class="form-group col-sm-12">
										<label class="control-label" for="corpName">建设单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm" id="corpName" name="corpName"/>
										</div>
									</div>
									<%-- <div class="form-group col-md-6 col-sm-6 allSign builder">
										<label class="control-label signature-tool" for="fieldsRepresent">现场代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="fieldsRepresent" name="fieldsRepresent" value="">
											<input type="hidden" class="signPost" id="fieldsRepresent_postType" name="fieldsRepresent_postType" value="${fieldsRepresentPost}">
											<img class="fieldsRepresent" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div> --%>
									<div class="form-group col-sm-6 hidden selectSignDate strengDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control field-editable datepicker-default input-sm allText builder" id="fieldsRepresentDat" name="fieldsRepresentDat" />
										</div>
									</div>
									
									<div class="form-group  col-sm-12">
										<label class="control-label" for="suName">监理单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  value="" id="suName" name="suName"  />
										</div>
								    </div>
								    <%-- <div class="form-group col-md-6 col-sm-6 allSign suJgj">
										<label class="control-label signature-tool" for="suJgj">现场监理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden" class="signPost" id="suJgj_postType" name="suJgj_postType" value="${suJgjPost }">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div> --%>
								    <div class="form-group col-md-6 col-sm-6 hidden selectSignDate strengDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable allText suJgj" value="" id="suJgjSignDate" name="suJgjSignDate"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm" id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									 
									<div class="form-group col-md-6 col-sm-12 allSign cuPm">
										<label class="control-label signature-tool sign-require sign-all" for="constructionPrincipal">施工负责人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionPrincipal" name="constructionPrincipal" value="">
											<input type="hidden" class="signPost" id="constructionPrincipal_postType" name="constructionPrincipal_postType" value="${constructionPrincipalPost }">
											<img class="constructionPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable allText cuPm" value="" id="cupSignDate" name="cupSignDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign qualitativeCheckMember">
										<label class="control-label signature-tool sign-require sign-all" for="constructionQc">质检人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQc" name="constructionQc" value="">
											<input type="hidden" class="signPost" id="constructionQc_postType" name="constructionQc_postType" value="${constructionQcPost }">
											<img class="constructionQc" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable allText qualitativeCheckMember" value="" id="cuQcSignDate" name="cuQcSignDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign construction">
										<label class="control-label signature-tool sign-require sign-all" for="builder">施工员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
											<input type="hidden" class="signPost" id="builder_postType" name="builder_postType" value="${construction }">
											<img class="builder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable allText construction" value="" id="builderSignDate" name="builderSignDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign safetyOfficer">
										<label class="control-label signature-tool sign-require sign-all" for="safetyOfficer">安全员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="safetyOfficer" name="safetyOfficer" value="">
											<input type="hidden" class="signPost" id="safetyOfficer_postType" name="safetyOfficer_postType" value="${saftyOfficerPost }">
											<img class="safetyOfficer" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable allText safetyOfficer" value="" id="soSignDate" name="soSignDate"  />
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
		                   			<input type="hidden" id="eiId" name="eiId" class="addClear">
		                   			<input type="hidden" id="version" name="version" class="addClear"/>
		                   			<input type="hidden" id="recordPcId" name="pcId" class="addClear"/>
		                   			<input type="hidden" id="isFinishSign" name="isFinishSign" class="addClear">
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="pipeSectionNo">管段编号</label>
										<div>
							                <input type="text" class="form-control input-sm field-editable addClear allText construction" id="pipeSectionNo"   name="pipeSectionNo" data-parsley-required="true"  data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="pipingSpec">管道规格</label>
										<div>
							                <input type="text" class="form-control input-sm field-editable addClear allText construction" id="pipingSpec"   name="pipingSpec"  data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="pipeLen">长度(M)</label>
										<div>
							                <input type="text" class="form-control input-sm field-editable addClear allText construction" id="pipeLen"   name="pipeLen"   data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="installPosition">安装位置(桩号)</label>
										<div>
							                <input type="text" class="form-control input-sm field-editable addClear allText construction" id="installPosition"   name="installPosition"  data-parsley-maxlength="50"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="pipeTopLevel">管顶标高</label>
										<div>
											<input type="text" class="form-control  input-sm field-editable addClear allText construction" id="pipeTopLevel"   name="pipeTopLevel"   data-parsley-maxlength="30"/>
							           </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 ">
										<label class="control-label" for="buriedDepth">埋深</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="buriedDepth"   name="buriedDepth"   data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="equipmentName">设备名称</label>
										<div>
							                <input type="text" class="form-control input-sm field-editable addClear allText construction" id="equipmentName"   name="equipmentName"   data-parsley-maxlength="50"/>
							            </div>
									</div>
									
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="equipmentSpec">设备规格</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="equipmentSpec"   name="equipmentSpec"   data-parsley-maxlength="50"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="equipmentPileNo">设备位置桩号</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="equipmentPileNo"   name="equipmentPileNo"  data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="equipmentBuriedDepth">设备埋深</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear allText construction" id="equipmentBuriedDepth"   name="equipmentBuriedDepth"   data-parsley-maxlength="30"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label">日期</label>
										<div>
							                <input type="text" class="form-control datepicker-default input-sm field-not-editable sysDate addClear allText " id="installDate"   name="installDate"  data-parsley-required="true" data-parsley-maxlength="30"/>
							            </div>
									</div>
			                       	<table id="recordListTable" class="table table-striped table-bordered nowrap " width="100%">
			                            <thead >
			                                <tr>
			                                    <th>操作</th>
			                                    <th>管段编号</th>
			                                    <th>管道规格</th>
			                                    <th>长度(M)</th>
			                                    <th>安装位置（桩号）</th>
			                                    <th>管顶标高</th>
			                                    <th>埋深</th>
			                                    <th>设备名称</th>
			                                    <th>设备规格</th>
			                                    <th>设备位置桩号</th>
			                                    <th>设备埋深</th>
			                                    <th>日期</th>
			                                </tr>
			                            </thead>
			                       	</table>
		                       	</form>
		                       		<!-- 照片 -->
		                       	<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list hidden" id="recordImagesList">
										<input type="hidden" id="eiId1" />
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
			    <div class="panel-body " id="equipmentInstall_panel_box">
	                 <div class="clearboth form-box " >
	                 	
	                 	<div class="iframe-report-box " id="">
	                  		<iframe id="mainFrm" class="iframe-report " style="width:1150px;height:850px;border:1; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('设备安装- 工程项目管理系统 ');
    $("#signForm").toggleEditState().styleFit();
    $("#auditForm").toggleEditState().styleFit();
    
    $('.update-show').addClass('hidden');
    $('.auditEditBtn').addClass('hidden');
   
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projAddr').val(projJson.projAddr);				   //
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.cuName); //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    	$("#corpName").val(projJson.corpName); //燃气公司
    }
    
  	//签字加载方式
  	 $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	 $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6").handleSignature(); 
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
	    $.getScript('projectjs/inspection/equipment-install.js?'+Math.random()).done(function () {
	    	equipmentInstall.init();
		});
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
        }else{
        	//签字区保存后不刷新页面,cpt文件中数据从签字区获取
        	projName=$('#projName').val();
        	suName=$('#suName').val();
        	corpName=$('#corpName').val();
        	constructionUnit=$('#constructionUnit').val();
        }
        if(!pcId){
			pcId='-1';
		}
    	    //以下处理为解决中文乱码
    	     projName=encodeURIComponent(cjkEncode(projName));
    	    suName=encodeURIComponent(cjkEncode(suName));
    	    corpName=encodeURIComponent(cjkEncode(corpName));
        	constructionUnit = encodeURIComponent(cjkEncode(constructionUnit));
        	
        //cpt路径及参数
        var ur = "equipmentInstall.cpt";
        //判断是否为新报验单
        if(isUpdate()){
            ur = "equipmentInstallNew.cpt";
        }
        var src=cptPath+"/ReportServer?reportlet=inspection/"+ur+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val()+
        		"&projName="+projName+"&suName="+suName+"&corpName="+corpName+"&constructionUnit="+constructionUnit;
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
		var projId = $("#projId").val(),projNo=$('#eiId1').val(),
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
		     if (!projNo && $("#eiId1").length && !$("#eiId1").val()) {
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