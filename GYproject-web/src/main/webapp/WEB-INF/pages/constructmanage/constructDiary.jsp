<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse tabs-mixin ">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                	</div>	
                	<ul class="nav nav-tabs ">
						<li class="active"><a href="#tab-1" data-toggle="tab">日志列表</a></li>
						<li class=""><a href="#dailyLogFormtab" data-toggle="tab">日志记录</a></li>
					</ul>
                </div>
                <div class="panel-body" id="box">
                	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                	<div class="tab-content">
	                	<div class="tab-pane fade active in btn-top" id="tab-1" >
	                		<input type="hidden" id="loginPost" value="${loginPost }"/>
						 	<input type="hidden" id="BUILDER" value="${BUILDER }"/>
						 	<input type="hidden" id="CONSTRUCTION" value="${CONSTRUCTION }"/>
	                		<table class="table table-hover table-striped table-bordered nowrap" id="diaryTable" width="100%">
	                			<thead>
	                			<tr>
		                			<th>业务记录ID</th>
		                			<th>记录人</th>
		                			<th>登记日期</th>
	                				<th>天气</th>
	                				<th>现场负责人</th>
	                			</tr>
	                			</thead>
	                		</table>
	                	</div>
	                	<div class="tab-pane fade in btn-top" id="dailyLogFormtab">
	                		<div class="toolBtn f-r p-b-10 dailyLogFormEditbtn">
	    					 	<a href="javascript:;" class="btn btn-default btn-confirm btn-sm m-l-5 checkRole saveDailyLog">保存</a>
	        				 	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 checkRole cancelDailyLog">放弃</a>
						 	</div>
						 	<div class="clearboth form-box p-b-10">
						 	    <input type="hidden" id="flag" value="0"/>
						 	 	<form class="form-horizontal" id="contractDiaryForm" action="constructDiary/saveDailyLog">
						 	 		<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
						 	 		<input type="hidden" name="projId" />
						 	 		<input type="hidden" name="projNo" />
						 	 		<input type="hidden" id="dlId" name="dlId" />
						 	 		<input type="hidden" id="dlRecorderId" name="dlRecorderId" />
						 	 		<input type="hidden" id="dlRecorderPost" name="dlRecorderPost" />
							 		<div class="form-group col-sm-12">
								        <label class="control-label" for="">工程名称</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable" name="projName" />
					        			</div>
			   						</div>
			   						<div class="form-group col-sm-12">
								        <label class="control-label" for="">工程地点</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable" name="projAddr"  />
					        			</div>
			   						</div>
			   						<div class="form-group col-sm-12">
								        <label class="control-label" for="">分包单位</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable" id="constructionUnit" name="constructionUnit" />
								        </div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12 clearboth">
								        <label class="control-label" for="">记录人</label>
								        <div>
								        	<input type="text" class="form-control input-sm field-not-editable" id="dlRecorder" name="dlRecorder" data-parsley-required="true"/>
								        </div>
								    </div>
									<div class="form-group col-md-6 col-sm-12">
								        <label class="control-label" for="">登记日期</label>
								        <div>
								        	<input type="text" class="form-control input-sm datepicker-default field-not-editable" id="dlDate" name="dlDate" data-parsley-required="true"/>
								        </div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
								        <label class="control-label" for="">气温</label>
								        <div>
							            	<input type="text" class="form-control input-sm field-editable formClear" name="dlTemperature"  data-parsley-required="true"/>
							            	<a href="javascript:;" class="btn btn-sm btn-default">℃</a>
							        	</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
								        <label class="control-label" for="">风力</label>
								        <div>
							            	<input type="text" class="form-control input-sm field-editable formClear" name="dlWind"/>
							            	<a href="javascript:;" class="btn btn-sm btn-default">级</a>
							        	</div>
								    </div>
								    <div class="form-group col-sm-12 col-xs-12 ">
								    	<label class="control-label" for="">天气</label>
								        <div class="weather-tool">
									        <label weather="晴天"><img src="images/weather/sunshine.png"><img class="weather-on" src="images/weather/sunshine-on.png"></label>
									        <label weather="阴天"><img src="images/weather/cloudy.png"><img class="weather-on" src="images/weather/cloudy-on.png"></label>
									        <label weather="下雨"><img src="images/weather/rain.png"><img class="weather-on" src="images/weather/rain-on.png"></label>
									        <label weather="下雪"><img src="images/weather/snow.png"><img class="weather-on" src="images/weather/snow-on.png"></label>
									        <input type="hidden" id="dlWeather" name="dlWeather" value="">
								        </div>
								    </div>
								     <div class="form-group col-md-6 col-sm-12 clearboth">
										<label class="control-label signature-tool sign-require" for="">现场负责人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="fieldPrincipal-a"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="fieldPrincipal" name="fieldPrincipal" data-parsley-required="true">
											<input type="hidden" id="fieldPrincipal_postType" name="fieldPrincipal_postType" value="${postType.value}">
											<img class="fieldPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
							 	</form>
						 	</div>
						 	<div class="clearboth form-box">
						 	 	<form class="" id="contractDiaryForm2" action="">
								    <div class="form-group col-sm-6 col-xs-12 p-r-0 p-l-10">
									     <label class="control-label" for="">施工记录</label>
									     <div> 
									         <textarea class="form-control field-editable" form="contractDiaryForm" name="constructOrganization" id="constructOrganization" rows="4" cols="" data-parsley-maxlength="200" ></textarea>
									     </div>
									</div>
									<div class="form-group col-sm-6 col-xs-12 p-r-0 p-l-10">
									    <label class="control-label" for=""> 质量记录</label>
									     <div> 
									         <textarea class="form-control field-editable" form="contractDiaryForm" name="quality" id="quality" rows="4" cols="" data-parsley-maxlength="200" ></textarea>
									     </div>
									</div>
									<div class="form-group col-sm-6 col-xs-12 p-r-0 p-l-10">
									     <label class="control-label" for="">安全记录</label>
									     <div> 
									        <textarea class="form-control field-editable" form="contractDiaryForm" name="safetyRecord" id="safetyRecord" rows="4" cols="" data-parsley-maxlength="200" ></textarea>
									     </div>
									</div>
									<div class="form-group col-sm-6 col-xs-12 p-r-0 p-l-10">
									     <label class="control-label" for="">检查记录</label>
									     <div> 
									         <textarea class="form-control field-editable" form="contractDiaryForm" name="checkRecord" id="checkRecord" rows="4" cols="" data-parsley-maxlength="200" ></textarea>
									     </div>
									</div>
							 	</form>
						 	</div>
						 	<div class="clearboth form-box">
								<div class="form-group width-full attach-images-list" id="projectImagesList">
								     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm upload-image-btn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
								     <ul class="row">
								     </ul>
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

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('施工日志 - 工程施工管理系统 ');
    //是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
	    $("#contractDiaryForm").styleFit();
	    
	   	$('.datepicker-default').datepicker({
	    	todayHighlight: true
	    });
	   	
	    
	    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
	
	    showDetailReport = function(data){
	    	var dlId = trSData.json ? trSData.json.dlId : '-1',
	    		imgUrl = $('.imgUrl').val(),
		   		fieldPrincipal = $('#fieldPrincipal').val();
	    	var projId1 = getProjectInfo().projId;
	    	if(data){
                dlId = data.dlId;
        		if(data.fieldPrincipal){
        			fieldPrincipal= data.fieldPrincipal;
        		}
			}
	    	var src="<%=basePath%>/ReportServer?reportlet=constructmanage/constructDiary.cpt&dlId="+dlId+"&imgUrl="+ imgUrl+"&projId="+ projId1;
	    	$("#mainFrm").attr("src",src); 	
	  }
	    showReport = function(data){
	    	var projId1 = getProjectInfo().projId;
	    	var src="<%=basePath%>/ReportServer?reportlet=constructmanage/constructDiary.cpt&projId="+ projId1;
	    	$("#mainFrm").attr("src",src); 	
	  }
	    showDetailReport();
	    //打印预览窗口缩放调整
	    if($(".iframe-report").length > 0){
	    	var fr = $(".iframe-report");
	    	for(var i=0; i<fr.length; i++){
	    		fr.eq(i).rescaleReportPanel();
	    	}
	    }
	 
	    $("#fieldPrincipal-a").handleSignature();
	    $.getScript('projectjs/constructmanage/construct-diary.js?'+Math.random()).done(function () {
	 		constructDiary.init();
	    });
    }
</script>
<!-- ================== END PAGE LEVEL JS ================== -->