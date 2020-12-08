<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%	
	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   %>    
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
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
                    <input type="hidden" id="loginPost" value="${loginPost}"/>
                     <input type="hidden" id="BUILDER" value="${BUILDER}"/>
                     <input type="hidden" id="unitType" value="${unitType}"/>
                     <input type="hidden" id="cuUnitType" value="${cuUnitType}"/>
                     <input type="hidden" id="dataAdmin" value="${dataAdmin}"/>
					<!-- 采集文件类型 -->
	                		<input type="hidden" id="sourceType" value="${accessorySource }" class="sourceType">
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">竣工报告列表</a></li>
		                <li class="" id="addTab"><a href="#default-tab-2" id="signTab"  data-toggle="tab">竣工报告记录</a></li>
                	</ul>
                </div>
                <div class="panel-body" id="box">
                	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                	<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
	                		<table  data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="completeReportTable" width="100%">
	                			<thead>
		                			<tr>
		                				<th></th>
			                			<th>工程编号</th>
			                			<th>工程名称</th>
			                			<th>施工合同价</th>
		                			</tr>
	                			</thead>
	                		</table>
	                	</div>
	                	<div class="tab-pane fade btn-top saveHiddenBox" id="default-tab-2">
			                <div class="toolBtn f-r p-b-10 editbtn">
			                	<a href="javascript:;" class="btn btn-confirm checkRole btn-sm m-l-5 saveBtn">保存</a>
			                	<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">放弃</a>
			                    <a href="javascript:;" class="btn  btn-primary checkRole btn-sm m-l-5 hidden get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>
						    </div>
							<div class="clearboth form-box">
								<input type="hidden" class="imgUrl" value="${imgUrl}"/>
								<input type="hidden" id="flag"name="flag" value="">
								<form class="form-horizontal" id="completeReportForm" action="">
									<input type="hidden"  id="crId" name ="crId"  class="accBusRecordId"/>
									<input type="hidden" id="projId"name="projId" value="">
									<input type="hidden" id="version" name="version" class="addClear">
									<div class="form-group col-sm-6">
										<label class="control-label" for="projNo">工程编号</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="projNo" name="projNo"  />
										</div>
									</div>
									<div class="form-group col-sm-6">
										<label class="control-label" for="scAmount">施工合同价</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="scAmount" name="scAmount"  />
										</div>
									</div>
									<div class="form-group col-sm-12 ">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="projName" name="projName"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="projAddr">工程地点</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="projAddr" name="projAddr"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label">计划开工日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default allText field-not-editable "  id="scPlannedStartDate" name="scPlannedStartDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">计划竣工日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default allText field-not-editable "  id="scPlannedEndDate" name="scPlannedEndDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">实际开工日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default allText field-not-editable "  id="realStartDate" name="realStartDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">实际竣工日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default allText field-not-editable" id="realEndDate" name="realEndDate"  data-parsley-required="true"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">计划工作天数</label>
										<div>
											<input type="text" class="form-control input-sm allText field-not-editable "  id="scPlannedTotalDays" name="scPlannedTotalDays"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="sealTotalDays">实际工作天数</label>
										<div>
											<input type="text" class="form-control input-sm allText field-not-editable  " id="sealTotalDays" name="sealTotalDays"  />
										</div>
									</div>
								    <div class="form-group col-sm-12">
										<label class="control-label" for="projContent">工程内容</label>
										<div>
											<!--去掉  builder   -->
											<textarea class="form-control field-editable allText construction projectLeader viceMinister" name="projContent" id="projContent" rows="5" cols="" data-parsley-maxlength="2000" data-parsley-required="true"></textarea>
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="crAttach">附件</label>
										<div>
											<textarea class="form-control field-editable allText construction" name="crAttach" id="crAttach" rows="5" cols="" data-parsley-maxlength="2000" ></textarea>
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="remark">备注</label>
										<div>
											<textarea class="form-control field-editable allText construction builder  projectLeader viceMinister" name="remark" id="remark" rows="3" cols="" data-parsley-maxlength="500" ></textarea>
										</div>
									</div>
									<div class="form-group col-sm-12 clearboth">
										<label class="control-label" for="corpName">建设单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="corpName" name="corpName"  />
										</div>
									</div>
									<div class="form-group col-md-6 allSign projectLeader viceMinister">
										<label class="control-label signature-tool" for="projectLeader">工程负责人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="projectLeader" name="projectLeader" value="">
											<input type="hidden" class="signPost"  id="projectLeader_postType" name="projectLeader_postType" value="${PROJECT_LEADER}">
		                               		<img class="projectLeader" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6 allSign builder">
										<label class="control-label signature-tool" for="builder">现场代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
											<input type="hidden" class="signPost"  id="builder_postType" name="builder_postType" value="${BUILDER}">
											<img class="builder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>

									<div class="form-group col-sm-12">
										<label class="control-label" for="suName">监理单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="suName" name="suName"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="suView">审查意见</label>
										<div>
											<textarea class="form-control field-editable allText suCse" name="suView" id="suView" rows="5" cols="" data-parsley-maxlength="2000" ></textarea>
										</div>
									</div>
								    <div class="form-group col-md-6 col-sm-12 allSign suCse">
										<label class="control-label signature-tool" for="suCse">总监</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suCse" name="suCse" value="">
											<input type="hidden" class="signPost"  id="suCse_postType" name="suCse_postType" value="${SUCSE}">
											<img class="suCse" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <!-- 签字日期class selectSignDate -->
									<div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable allText suCse"  id="suCseDate" name="suCseDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign suJgj">
										<label class="control-label signature-tool" for="suJgj">现场监理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden" class="signPost"  id="suJgj_postType" name="suJgj_postType" value="${SUPERVISOR}">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>

									<div class="form-group col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign cuPm">
										<label class="control-label signature-tool" for="cuPm">项目经理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="cuPm" name="cuPm" value="">
											<input type="hidden" class="signPost"  id="cuPm_postType" name="cuPm_postType" value="${CU_PM}">
											<img class="cuPm" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable allText cuPm"  id="cuPmDate" name="cuPmDate"  data-parsley-required="true"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign qualitativeCheckMember">
										<label class="control-label signature-tool" for="qualitativeCheckMember">质检员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="qualitativeCheckMember" name="qualitativeCheckMember" value="">
											<input type="hidden" class="signPost"  id="qualitativeCheckMember_postType" name="qualitativeCheckMember_postType" value="${QUALITATIVE_CHECK_MEMBER}">
											<img class="qualitativeCheckMember" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign safetyOfficer">
										<label class="control-label signature-tool" for="safetyOfficer">安全员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_7"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="safetyOfficer" name="safetyOfficer" value="">
											<input type="hidden" class="signPost"  id="safetyOfficer_postType" name="safetyOfficer_postType" value="${SAFTYOFFICER}">
											<img class="safetyOfficer" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign construction">
										<label class="control-label signature-tool" for="construction">施工员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_8"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="construction" name="construction" value="">
											<input type="hidden" class="signPost"  id="construction_postType" name="construction_postType" value="${CONSTRUCTION}">
											<img class="construction" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
								</form >
							</div>
						</div>
					</div>
            	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
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
	                 	<iframe id="mainFrm" class="iframe-report" style="width: 800px; height: 1200px;border:0; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('竣工报告 - 工程项目管理系统 ');
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projName').val(projJson.projName);	//工程名称
    	$('#projId').val(projJson.projId);		//工程ID
    	$('#projNo').val(projJson.projNo);
    }
    trSData.t && trSData.t.cgetDetail('completeReportForm','completeReport/detailById','',queryBack);
    	var projJson = getProjectInfo();
    	var projId=projJson.projId;
    	var crId = $("#crId").val();
	     //查详述
	    var constructionWorkDetail = function(){
	    	var url = 'completeReport/constructionWorkDetail';
	    	var projId = getProjectInfo().projId;
	    	var crId = $("#crId").val();
	    	var data = "id="+projId;
	    	 $.ajax({
	             type: 'POST',
	             url: url,
	             data: data,
	             dataType: 'json',
	             success: function (data) {
	                 $('#projName').val(data.projName);			//工程名称
                     $('#projAddr').val(data.projAddr);			//工程名称
	              	 $('#corpName').val(data.corpName);			//建设单位
	              	 $('#projId').val(data.projId);				//工程ID
	              	 $('#projNo').val(data.projNo);				//工程编号
	              	 $('#scAmount').val(data.scAmount);		//
	              	 $('#scPlannedStartDate').val(data.scPlannedStartDate);//开工日期

	              	 
                     $('#realStartDate').val(data.realStartDate);//开工日期
                     $('#realEndDate').val(data.realEndDate);//默认实际竣工日期
                     //$('#scPlannedTotalDays').val(data.scPlannedTotalDays);//天数

	            	 $("#constructionUnit").val(data.constructionUnit);		//施工单位
	            	 $("#suName").val(data.suName);					//监理单位
	            	 if(data.scPlannedEndDate!='' && checkNumber(data.scPlannedEndDate)){
	            		 //有计划竣工日期，则自动计算计划工期
	                     $('#scPlannedEndDate').val(format(data.scPlannedEndDate));//俊工日期
	                     	//计划工作天数
	   	            	 $("#scPlannedTotalDays").val((timestamp($("#scPlannedEndDate").val())-timestamp($("#scPlannedStartDate").val()))/(60*60*24)/1000);
	   	            	 
		             }else{
		            	 //否则
		            	 $('#scPlannedEndDate').val(data.scPlannedEndDate);
		            	 $('#scPlannedTotalDays').val(data.scPlannedTotalDays);
		             }
	            	 //实际工作天数
	            	 if($("#realEndDate").val()!=null && $("#realEndDate").val()!=''){
	            		 $("#sealTotalDays").val((timestamp($("#realEndDate").val())-timestamp($("#realStartDate").val()))/(60*60*24)/1000);
	            		 $("#realEndDate").removeAttr("disabled");
	            	 }else{
	            		 $("#realEndDate").attr("disabled","disabled");
	            	 }
	            	 showReport();
	             },
	             error: function (jqXHR, textStatus, errorThrown) {
	                 console.warn("cgetDetail() -> 详情查询失败");
	             }
	         });
	    };
	    constructionWorkDetail();
	    $("#completeReportForm").toggleEditState().styleFit();
	    //日期datepicker
	    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js?v=2250').done(function() {
	    	$('.datepicker-default').datepicker({
	            todayHighlight: true
	        });
	    });
	
	    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
	    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6, #signBtn_7, #signBtn_8");
	    	signatures.handleSignature(false);        	    	
	    });
	    var cptPath = '<%=basePath%>';
	    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
	    //加载打印预览
	    var showReport = function(data){
	    	var projId = $("#projId").val(),						//工程名称
	    	crId = $("#crId").val();
	    	projId = encodeURIComponent(cjkEncode(projId));
	    	src=cptPath+"/ReportServer?reportlet=constructmanage/"+cptType;
	    	src=src+"&crId="+crId+"&projId="+projId+"&imgUrl="+$(".imgUrl").val();
	    	$("#mainFrm").attr("src",src); 
	    };
	    //打印预览窗口缩放调整
		if($(".iframe-report").length> 0){
    		var fr = $(".iframe-report");
	    	for(var i=0; i<fr.length; i++){
	    		fr.eq(i).rescaleReportPanel();
	    	}
	    }
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
	}
    $.getScript('projectjs/constructmanage/complete-report.js?'+Math.random()).done(function () {
    	completeReport.init();
    });
    
    $("#realEndDate").on("change",function(){
        $("#sealTotalDays").val((timestamp($("#realEndDate").val())-timestamp($("#realStartDate").val()))/(60*60*24)/1000);
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->