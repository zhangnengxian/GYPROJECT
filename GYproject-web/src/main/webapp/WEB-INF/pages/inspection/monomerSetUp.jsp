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
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表区</a></li>
		                <li class="" id="addTab"><a href="#default-tab-2" id="signTab"  data-toggle="tab">报验区</a></li>
		                <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" id="">
               		<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
               			    <input type="hidden" id="pcIdNew">
               			    <!--<input type="hidden" id="addShow"> -->
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="altimetricTable" width="100%">
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
	                   	<div class="tab-pane fade btn-top" id="default-tab-2">
	                       	<div class="toolBtn f-r p-b-10 update-show">
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole savebtn" >保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">放弃</a>
						    </div>
						    <div class="clearboth form-box">
						    	<input type="hidden" class="imgUrl" value="${imgUrl}"/>
								<form class="form-horizontal" id="monomerSetUpForm" data-parsley-validate="true" action="altimetricSurvey/saveCheckList">							
								    <input type="hidden" id="pcDesId" name="pcDesId" value="${pcDesId}"/> 
									<input type="hidden" id="pcId" name="pcId" class="addClear"/>
									<input type="hidden" id="projId" name="projId" >
								 	<input type="hidden" id="projNo" name="projNo" > 
									<div class="form-group  col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projName" name="projName"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="process">施工工序</label>
										<div> 
											<input type="text" class="form-control input-sm field-editable addClear"  id="process" name="process"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="slResultPage">附件数量</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="slResultPage" name="slResultPage"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionPrincipal">项目经理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionPrincipal" name="constructionPrincipal" value="">
											<input type="hidden"  id="constructionPrincipal_postType" name="constructionPrincipal_postType" value="${subPrincipal }">
											<img class="constructionPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectionDate">报验日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default addClear"  id="inspectionDate" name="inspectionDate"  />
										</div>
									</div> 
						    		<div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="inspectionResult" id="inspectionResult" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionQc">质检员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQc" name="constructionQc" value="">
											<input type="hidden"  id="constructionQc_postType" name="constructionQc_postType" value="${constructionQcpost }">
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
										<label class="control-label signature-tool" for="technician">技术员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="technician" name="technician" value="">
											<input type="hidden"  id="technician_postType" name="technician_postType" value="${technician }">
											<img class="technician" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<div class="form-group  col-sm-12">
				                    	<label class="control-label" for="suOpinion">监理意见</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="suOpinion" id="suOpinion" rows="3" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group  col-sm-12">
										<label class="control-label" for="suName">监理公司</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  value="" id="suName" name="suName"  />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="">监理工程师</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden"  id="suJgj_postType" name="suJgj_postType" value="${suJgjpost }">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checkDate">日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear" value="" id="checkDate" name="checkDate"  />
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
	                   		<div class="toolBtn f-r p-b-10 editbtn" >
			                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole altimeAddbtn">增加</a>
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole altimeSavebtn">保存</a>
						   		 <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole deleteBtn">删除</a>
						    </div>
						    <div class="clearboth form-box">
		                   		<form class="form-horizontal" id="monomerSetUpForm2" data-parsley-validate="true" action="">
		                   			 <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="monomer">调校项目</label>
							            <div>
							                <select class="form-control input-sm field-editable" id="monomer" data-parsley-required="true"  name="monomer"  >
								                <c:forEach var="enums" items="${checkItems}">
													<option value="${enums.value}" >${enums.message}</option>
								                </c:forEach>
								              </select>
							            </div>
							        </div>
		                   			<div class="form-group col-md-6 col-sm-12 result">
							            <label class="control-label" for="result">调校结果</label>
							            <div>
							                <select class="form-control input-sm field-editable" id="result"  name="result"  >
								                <c:forEach var="enums" items="${result}">
													<option value="${enums.value}" >${enums.message}</option>
								                </c:forEach>
								              </select>
							            </div>
							        </div>
							        <div class="form-group col-md-12 hidden result2">
							            <label class="control-label" for="result2">调校结果</label>
							            <div>
							            	<textarea class="form-control field-editable addClear" name="result2" id="result2" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
							            </div>
							        </div>
		                       	</form>
		                       	<table id="monomerSetUpRecordTable" class="table table-striped table-bordered nowrap" width="100%">
	                            <thead>
	                                <tr>
	                                    <th>检查项目</th>
	                                    <th>检查结果</th>
	                                </tr>
	                            </thead>
	                       	</table>
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
			    <div class="panel-body" id="altimetric_survey_audit_panel_box">
	                 <div class="clearboth form-box">
	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 795px; height: 1123px;border:1; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('单体调校 - 工程项目管理系统 ');
    $("#monomerSetUpForm").toggleEditState().styleFit();
    $("#monomerSetUpForm2").toggleEditState(true).styleFit();
    
    $('.update-show').addClass('hidden');
    $('.editBtn').addClass('hidden');
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.cuName);		   //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    }
    
  	//签字加载方式
    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6").handleSignature();  
    
   //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 
   //报验区保存功能
    $(".savebtn").on("click",function(){
    	$("#monomerSetUpForm").cformSave("altimetricTable",saveBack,false);
    });
    
    function saveBack(data){
    	$('.update-show').addClass("hidden");
    	$('#monomerSetUpForm').toggleEditState(false);
        if(data!==false){
        	$('#pcIdNew').val(data);
        	$('#pcId').val(data);
        }

        var pcId = data,
   		projNo = $("#projNo").val();
   		projId = $("#projId").val();
   		
   		if(_inApk && $(".attach-images-list").find(".new-image").length){
   			preImagesUpload($(".attach-images-list .uploadBtn"), projId, projNo, getStepId(), pcId);
   		};
    	showReport1();
    }
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/inspection/monomer-set-up.js').done(function () {
        altimetricSurveyAndAudit.init();
	});
   
  	//加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var cptPath = '<%=basePath%>';
  	//加载列表区和报验区的cpt文件
    var showReport1 = function(){
        var projName='',suName='',process='',inspectionResult='',constructionUnit='',suOpinion='',suJgj='',projName='',slResultPage='',selfCheckDate='',checkDate='',inspectionDate='';
        var json=trSData.altimetricTable.json;
        var pcId;
        //列表区的cpt文件数据从trSData中获取
        if(json){
        	projName=json.projName;
       	    suName =json.suName;
    	    process =json.process;
            constructionUnit =json.constructionUnit;
            inspectionResult=json.inspectionResult;
            suOpinion=json.suOpinion;
            slResultPage=json.slResultPage;
        	checkDate=json.checkDate;
        	selfCheckDate=json.selfCheckDate;
        	inspectionDate=json.inspectionDate;
        	pcId = json.pcId;
        }else{
        	//报验区保存后不刷新页面，cpt文件中数据从报验区获取
        	projName=$('#projName').val();
    	    suName = $("#suName").val();
    	    process = $("#process").val();
            constructionUnit = $("#constructionUnit").val();
            inspectionResult=$("#inspectionResult").val();
            suOpinion=$("#suOpinion").val();
            slResultPage=$("#slResultPage").val();
    	    inspectionDate=$("#inspectionDate").val();    	   
    	    checkDate=$("#checkDate").val();
    	    selfCheckDate=$("#selfCheckDate").val();
    	    pcId = $("#pcId").val();
        }

    	    //以下处理为解决中文乱码
    	    projName=encodeURIComponent(cjkEncode(projName));
        	suName = encodeURIComponent(cjkEncode(suName));
        	process = encodeURIComponent(cjkEncode(process));
        	inspectionResult=encodeURIComponent(cjkEncode(inspectionResult));
        	constructionUnit = encodeURIComponent(cjkEncode(constructionUnit));
        	suOpinion= encodeURIComponent(cjkEncode(suOpinion));   	
        //cpt路径及参数
    	var src=cptPath+"/ReportServer?reportlet=altimetricSurvey/projectSurvey.cpt&suName="+suName+"&process="+process+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate;
    	src=src+"&checkDate="+checkDate+"&inspectionResult="+inspectionResult+"&inspectionDate="+inspectionDate+"&suOpinion="+suOpinion+"&slResultPage="+slResultPage+"&projName="+projName;
    	src = src+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src); 
    }; 
    //加载记录区的cpt文件
    showReport2 = function(){
    	var pcId='',projName='',asPoint='',asPerson='',constructionQc='',inspectionDate='',checkDate='',constructionUnit='';
	    if(trSData.altimetricTable.json){
	    	 pcId=trSData.altimetricTable.json.pcId;
	    	 projName =trSData.altimetricTable.json.projName;
	    	 constructionUnit=trSData.altimetricTable.json.constructionUnit;
	    }else{
	    	pcId=$('#pcId').val();
	    	projName=$('#projName').val();
	    	constructionUnit=$('#constructionUnit').val();//施工单位
	    }
    	projName=encodeURIComponent(cjkEncode(projName));
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	var src=cptPath+"/ReportServer?reportlet=monomerSetUp/monomerSetUp.cpt"+"&projName="+projName+"&constructionUnit="+constructionUnit;
    	src=src+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src);
    };
    
    var showReport3 = function(){
    	var projName='',constructionUnit='',suName='';
   		projName =$('#projName').val(),			      //工程名称
   		suName=$('#suName').val(),				      //监理单位
   		constructionUnit=$('#constructionUnit').val();//施工单位
   		var pcId = $("#pcId").val();
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName)); 
    	var src = cptPath+"/ReportServer?reportlet=altimetricSurvey/projectSurvey.cpt&projName="+projName+"&suName="+suName+"&constructionUnit="+constructionUnit+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src); 
    }
	//调校项目
    $("#monomer").change(function(){
            if($(this).val()=="7"){
            	//用户类型
	        	$('.result2').removeClass('hidden');
	        	$('.result').addClass('hidden');
            }else{
            	$('.result').removeClass('hidden');
            	$('.result2').addClass('hidden');
            }
            $("#monomerSetUpForm2").styleFit();
    	});
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