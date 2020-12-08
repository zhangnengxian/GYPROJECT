<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <ul class="nav nav-tabs ">
						<li class="active"><a href="#tab-1" id="liningListTab" data-toggle="tab">放线列表</a></li>
						<li class=""><a href="#tab-2" id="liningInfoTab" data-toggle="tab">放线信息</a></li>
					</ul>
                </div>
               	<div class="panel-body" id="box">
               		<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
               		<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="tab-1" >
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="surveyTable" width="100%">
	                			<thead>
		                			<tr>
		                				<th></th>
		                				<th>报验日期</th>
			                			<th>报验部位</th>
			                			<th>查验结果</th>
		                			</tr>
	                			</thead>
	                		</table>
	                	</div>
               			<div class="tab-pane fade in btn-top" id="tab-2">
			               	<div class=" f-r p-b-15 editbtn" >
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole savebtn" >保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">放弃</a>
						    </div>
						    <div class="clearboth form-box">
						    	<input type="hidden" class="imgUrl" value="${imgUrl}"/>
								<form class="form-horizontal" id="surveyLiningForm" data-parsley-validate="true" action="surveyLining/saveSurveyLining">
									 <input  type="hidden" id="pcId" name="pcId" class="addClear">
									 <input  type="hidden" id="pcDesId" name="pcDesId" value="${pcDesId}">
									 <input  type="hidden" id="projId" name="projId" >
									 <input  type="hidden" id="projNo" name="projNo" >
									 <input  type="hidden" id="qrCodePath" name="qrCodePath" >
									 <div class="form-group  col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable"   id="projName" name="projName" />
										</div>
									 </div>
									 <div class="form-group  col-sm-12">
										<label class="control-label" for="slPart">放线部位</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="slPart" name="slPart" data-parsley-required="true" />
										</div>
									 </div>
									 <div class="form-group  col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable"  id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label signature-tool" for="constructionQae">质保师</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQae" name="constructionQae" >
											<input type="hidden" id="constructionQae_postType" name="constructionQae_postType" value="${constructionQaePost}" >
											<img class="" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
							    	</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectionDate">质保日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="inspectionDate" name="inspectionDate"  />
										</div>
									</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="asPerson">测量员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="asPerson" name="asPerson" >
											<input type="hidden" id="asPerson_postType" name="asPerson_postType" value="${surveryorPost }" >
											<img class="asPerson" alt="" src=""" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
							    	</div>
									<div class="form-group col-md-6">
										<label class="control-label" for="liningDate">测量日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="liningDate" name="liningDate"  />
										</div>
									</div>
									
									<div class="form-group  col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="inspectionResult" id="inspectionResult" rows="4" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <%-- <div class="form-group col-sm-6">
								        <label class="control-label" >检查结论</label>
								        <div>
							          	    <select class="form-control input-sm field-editable addClear" id="inspectionClum"   name="inspectionClum" >
								 		      	<option value=""></option>
								                <c:forEach var="enums" items="${inspectionClumEnum}">
													<option value="${enums.value}" >${enums.message}</option>
								                </c:forEach>
							                </select>
								        </div>
								    </div> --%>
								   	 <div class="form-group col-md-6 col-sm-12 clearboth">
									        <label class="control-label" for="">检查结论</label>
									    	<div>
									            <label>
									              	<input type="radio" class="field-editable" name="inspectionClum" value="1" checked/>合格
									            </label>
									            <label>
									              	<input type="radio" class="field-editable" name="inspectionClum" value="2" />纠错重报
									            </label>
									        </div>
									    </div>
					  			    <div class="form-group  col-sm-12">
										<label class="control-label" for="suName">监理公司</label>
										<div>
											<input type="text" class="form-control field-editable input-sm field-not-editable"  id="suName" name="suName"  />
										</div>
								    </div>
								    
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="suJgj">监理人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" >
											<input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${suJgjPost }" >
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
							    	</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checkDate">日期</label>
										<div>
											<input type="text" class="form-control  input-sm datepicker-default field-editable addClear" value="" id="checkDate" name="checkDate"  />
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
			    <div class="panel-body" id="survey_Lining_panel_box">
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

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('测量放线 - 工程项目管理系统 ');
    $(".editbtn").addClass("hidden");
    $("#surveyLiningForm").toggleEditState().styleFit();
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.cuName); //施工单位-由施工管理处改为分包单位
    	$('#suName').val(projJson.suName);					   //监理公司
    }
    
    
    var  cptPath= '<%=basePath%>';
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var showReport = function(){
    	var projName='',suName='',slPart='',constructionUnit='',inspectionDate='',liningDate='',inspectionResult='',inspectionClum='',checkDate='';
    	if(trSData.surveyTable.json){
    		var json=trSData.surveyTable.json;
    		projName = json.projName,			   //工程名称
	   		suName=json.suName,					   //监理单位
	   		slPart=json.slPart,			       	   //检验部位
	   		constructionUnit=json.constructionUnit,//施工单位
	   		liningDate=json.liningDate,	   		   //测量日期
	   		inspectionDate=json.inspectionDate,	   //报验日期
	   		checkDate=json.checkDate;			   //检查日期
	   		inspectionResult=json.inspectionResult,//查验结果
	   		inspectionClum=json.inspectionClum;	   //检验结论
	   		pcId = json.pcId;
    	}else{
    		projName =$('#projName').val(),			    //工程名称
       		suName=$('#suName').val(),				    //监理单位
       		slPart=$('#slPart').val(),			    	//测量部位
       		constructionUnit=$('#constructionUnit').val(),//施工单位
       		inspectionDate=$('#inspectionDate').val(),    //报验日期
       		inspectionResult=$('#inspectionResult').val(),//查验结果
       		liningDate=$('#liningDate').val(),	  		  //自检日期
       		inspectionClum=$('#inspectionClum').val(),	  //检验结论
       		checkDate=$('#checkDate').val();			  //检查日期
    		pcId = $('#pcId').val();	
    	}
    	
    	projName = encodeURIComponent(cjkEncode(projName)),		  		  //工程名称
		suName=encodeURIComponent(cjkEncode(suName)),					  //监理单位
		slPart=encodeURIComponent(cjkEncode(slPart)),			    	  //检验部位
		constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)), //施工单位
		inspectionResult=encodeURIComponent(cjkEncode(inspectionResult)), //检验结果
		
		qualified="",
		unqualified="";
	
		if($("input[name='inspectionClum']:checked").val()=="1"){				 //查验结论 
			qualified="√";
		}else if($("input[name='inspectionClum']:checked").val()=="2"){
			unqualified="√";
		}
		qualified=encodeURIComponent(cjkEncode(qualified));
		unqualified=encodeURIComponent(cjkEncode(unqualified));
		
		src = cptPath+"/ReportServer?reportlet=surveyLining/surveyLining.cpt&projName=" + projName+"&suName="+suName+"&constructionUnit="+constructionUnit+"&liningDate="+liningDate;
		src = src+"&inspectionDate="+inspectionDate+"&checkDate="+checkDate+"&inspectionResult="+inspectionResult+"&slPart="+slPart+"&qualified="+qualified+"&unqualified="+unqualified;
		src = src+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
		$("#mainFrm").attr("src",src);
    };
    
    var showReport3 = function(){
    	var projName='',constructionUnit='',suName='';
    	
   		projName =$('#projName').val(),			      //工程名称
   		suName=$('#suName').val(),	
   		pcId = $('#pcId').val(),	//监理单位
   		constructionUnit=$('#constructionUnit').val();//施工单位
   		
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName)); 
    	var src = cptPath+"/ReportServer?reportlet=surveyLining/surveyLining.cpt&projName="+projName+"&suName="+suName+"&constructionUnit="+constructionUnit;
    	src = src+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src); 
    }
    
    
    
    //签字加载方式
    $("#signBtn_1, #signBtn_2, #signBtn_3").handleSignature();        	    	
    
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
    //日期
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    var cptPath = '<%=basePath%>';
    
    $.getScript('projectjs/inspection/survey-lining.js').done(function () {
		surveyLining.init();
    });
    
	//点击放弃
    $('.giveupbtn').off().on('click',function(){
		if($("#pcId").val()==""){
			//返回列表区
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#liningListTab').tab("show");
			$('#surveyTable').cgetData();
		}else{
			//返回列表区
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#liningListTab').tab("show");
		}
    })
    
    //保存
    $('.savebtn').off().on("click",function(){
    	console.info($("#qrCodePath").val());
     	$("#surveyLiningForm").cformSave('surveyTable',saveSurveyLiningBack,false);
    })
    var saveSurveyLiningBack=function(data){
    	$(".editbtn").addClass("hidden");
    	$("#pcId").val(data);
    	$('#surveyLiningForm').toggleEditState(false);
		//trSData.surveyTable.t.cgetDetail("surveyLiningForm");
		var pcId = data,
		projNo = $("#projNo").val();
		projId = $("#projId").val();
    	if(_inApk && $(".attach-images-list").find(".new-image").length){
			preImagesUpload($(".attach-images-list .uploadBtn"), projId, projNo, getStepId(), pcId);
		};
    	//刷新帆软报表
    	showReport();
	}
	
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