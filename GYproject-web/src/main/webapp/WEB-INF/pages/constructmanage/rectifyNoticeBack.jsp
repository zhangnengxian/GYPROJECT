<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />

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
						<li class="active"><a href="#rectification_record" data-toggle="tab"id ="rectificationList">整改列表</a></li>
						<li class=""><a href="#rectification_info" data-toggle="tab" id="rectificationInfo">回复信息</a></li>
					</ul>
                </div>
                <div class="panel-body " id="box">
                	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                	<div class="tab-content">
	                	<div class="tab-pane fade active in btn-top active" id="rectification_record" >
	                		<table class="table table-hover table-striped table-bordered nowrap" id="rectifyNoticeTable" width="100%">
	                			<thead>
	                			<tr>
	                				<th></th>
	                				<th>整改编号</th>
	                				<th>整改期限</th>
	                				<th>通知日期</th>
	                				<th>状态</th>
	                			</tr>
	                			</thead>
	                		</table>
	                	</div>
	                	<div class="tab-pane fade in btn-top" id="rectification_info">
	                		<div class=" f-r p-b-10 editbtn">
	    					 	<a href="javascript:;" class="btn btn-default btn-confirm btn-sm m-l-5 checkRole saveCustomer" onclick="" >保存</a>
	        				 	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 checkRole giveUpSave">放弃</a>
						 	</div>
						 	 <div class="clearboth form-box p-b-10">
						 	 <input type="hidden" id="imgUrl" name="imgUrl" class="imgUrl" value="${imgUrl }">
						 	 	<form class="form-horizontal" id="rectifiyNoticeForm" action="rectifyNoticeBack/saveRectifiyNotice">
							 	 	<input type="hidden" value="" id="projId" name="projId"/>
							 	 	<input type="hidden" value="" id="corpId" name="corpId"/>
							 	 	<input type="hidden" value="" id="corpName" name="corpName"/>
							 	 	<input type="hidden" value="" id="tenantId" name="tenantId"/>
							 	 	<input type="hidden" value="" id="orgId" name="orgId"/>
							 	 	<input type="hidden" value="" id="deptId" name="deptId"/>
							 	 	<input type="hidden" value="" id="rnId" name="rnId" class="addClear"/>
							 	 	<input type="hidden" value="" id="rnNo" name="rnNo" class=""/>
							 	 	<input type="hidden" value="" id="rnStatus" name="rnStatus" class=""/>
							 	 	<input type="hidden" name="version"/>
							 		<div class="form-group col-xs-12">
								        <label class="control-label" for="projNo">工程编号</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"  value="" id="projNo" name="projNo" />
					        			</div>
			   						</div>
							 		<div class="form-group col-xs-12">
								        <label class="control-label" for="projId">工程名称</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"  value="" id="projName" name="projName" />
					        			</div>
			   						</div>
			   						<div class="form-group col-sm-12">
					                	<label class="control-label" for="">工程地点</label>
					                    <div> 
					                    	<input type="text" class="form-control input-sm field-not-editable" id="projAddr" name="projAddr"  >
					  				    </div> 
					  				</div>
					  				<div class="form-group col-md-12 col-sm-12">
								        <label class="control-label" for="constructionUnit">施工单位</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"  value="" id="constructionUnit" name="constructionUnit"  />
					        			</div>
			   						</div>
					  				<div class="form-group col-md-12 col-sm-12">
								        <label class="control-label" for="suName">监理单位</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"  value="" id="suName" name="suName"  />
					        			</div>
			   						</div>
					  				<!-- <div class="form-group col-md-12 col-sm-12">
								        <label class="control-label" for="duName">设计单位</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"  value="" id="duName" name="duName"  />
					        			</div>
			   						</div> -->
			   						 <div class="form-group col-md-12 col-sm-12">
								        <label class="control-label" for="rnQuestions">存在问题</label>
								        <div>
								        	<textarea class="form-control input-sm  field-not-editable addClear" row="10"  id="rnQuestions" name="rnQuestions" data-parsley-required="true" data-parsley-maxlength="500"></textarea>
								           
								        </div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
								        <label class="control-label" for="" >通知时间</label>
								        <div>
								            <input type="text" class="form-control input-sm datepicker-default field-not-editable addClear" value="" id="rnDate" name="rnDate"/>
								        </div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12 resident">
								        <label class="control-label" for="limitTime">整改期限</label>
								        <div>
								        	 <input type="number" class=" form-control input-sm field-not-editable addClear" min="0" id="limitTime"  name="limitTime" data-parsley-maxlength="10" value="" >
		        								<a href="javascript:;" class="btn btn-sm btn-default">天</a>
								        	 </div>
								    </div>
								     <%--<div class="form-group col-md-6 col-sm-12">--%>
								        <%--<label class="control-label" for="fieldRepresentName">现场代表</label>--%>
								        <%--<div>--%>
								            <%--<input type="text" class="form-control input-sm  field-not-editable addClear" value="" id="fieldRepresentName" name="fieldRepresentName" data-parsley-required="true"  data-parsley-maxlength="200"/>--%>
								        <%--</div>--%>
								    <%--</div>--%>
								    <div class="hidden" >
								    <div class="form-group col-md-6 col-sm-12 clearboth">
										<label class="control-label signature-tool" for="">质量安全组</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="qsGroup" name="qsGroup" value="" class="sign-data-input">
											<input type="hidden" id="qsGroup_postType" name="qsGroup_postType" value="${qsGroupPost }" >
											<img class="qsGroup" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="custService">客户服务中心</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="custService" name="custService" value="" class="sign-data-input">
											<input type="hidden" id="custService_postType" name="custService_postType" value="${custServicePost }" >
											<img class="custService" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="tdCompany">输配公司</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="tdCompany" name="tdCompany" value="" class="sign-data-input">
											<input type="hidden" id="tdCompany_postType" name="tdCompany_postType" value="${tdCompanyPost }" >
											<img class="" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="networkCenter">安发公司</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="networkCenter" name="networkCenter" value="" class="sign-data-input">
											<input type="hidden" id="networkCenter_postType" name="networkCenter_postType" value="${networkCenterPost }" >
											<img class="networkCenter" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <div class="form-group col-md-6 col-xs-12">
								        <label class="control-label signature-tool" for="metrologyOffice">计量所</label>
					        			<div>
					        				<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="metrologyOffice" name="metrologyOffice" value="" class="sign-data-input">
											<input type="hidden" id="metrologyOffice_postType" name="metrologyOffice_postType" value="${metrologyOfficePost }" >
											<img class="metrologyOffice" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					        			</div>
			   						</div>
			   						<div class="form-group col-md-6 col-sm-12  ">
								        <label class="control-label signature-tool" for="fieldRepresent">现场代表</label>
								       <div>
					        				<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="fieldRepresent" name="fieldRepresent" value="" class="sign-data-input">
											<input type="hidden" id="fieldRepresent_postType" name="fieldRepresent_postType" value="${fieldRepresentPost }" >
											<img class="fieldRepresent" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					        			</div>
								    </div>
								   <div class="form-group col-md-6 col-xs-12 ">
								        <label class="control-label  signature-tool" for="designer">设计</label>
					        			 <div>
					        				<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_7"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="designer" name="designer" value="" class="sign-data-input">
											<input type="hidden" id="designer_postType" name="designer_postType" value="${designerPost }" >
											<img class="designer" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					        			</div>
			   						</div>
			   						 <div class="form-group col-md-6 col-sm-12 ">
								        <label class="control-label signature-tool" for="suJgj">监理</label>
								        <div>
					        				<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_8"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="suJgj" name="suJgj" value="" class="sign-data-input">
											<input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${suJgjPost }" >
											<img class="suJgj" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					        			</div>
								    </div>
			   						<div class="form-group col-md-6 col-sm-12">
								        <label class="control-label signature-tool" for="cuPLeader">施工单位</label>
					        			 <div>
					        				<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_9"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="cuPLeader" name="cuPLeader" value="" class="sign-data-input">
											<input type="hidden" id="cuPLeader_postType" name="cuPLeader_postType" value="${projectLeaderPost }" >
											<img class="cuPLeader" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										
					        			</div>
			   						</div>
			   						<div class="form-group col-md-6 col-sm-12">
								        <label class="control-label signature-tool" for="builder">施工员</label>
					        			 <div>
					        				<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_10"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="builder" name="builder" value="" class="sign-data-input">
											<input type="hidden" id="builder_postType" name="builder_postType" value="${builderPost }" >
											<img class="builder" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					        			</div>
			   						</div>
			   						</div>
			   						<div>
			   						<div class="form-group col-md-12 col-sm-12">
								        <label class="control-label" for="rnExplain">整改说明</label>
								        <div>
								        	<textarea class="form-control input-sm  field-editable addClear allText cuPm" row="4"  id="rnExplain" name="rnExplain" data-parsley-required="true" data-parsley-maxlength="200"></textarea>
								        </div>
								    </div>
								    <div class="form-group col-md-12 col-sm-12">
								        <label class="control-label" for="selfCheckResult">自检结果</label>
								        <div>
								        	<textarea class="form-control input-sm  field-editable addClear allText cuPm" row="2"  id="selfCheckResult" name="selfCheckResult" data-parsley-required="true" data-parsley-maxlength="200"></textarea>
								        </div>
								    </div>
								     <div class="form-group col-md-6 col-sm-12 allSign cuPm">
								        <label class="control-label signature-tool" for="cuPleaderBack">项目经理</label>
					        			 <div>
					        				<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_11"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="cuPleaderBack" name="cuPleaderBack" value="" class="sign-data-input">
											<input type="hidden" class="signPost"  id="cuPleaderBack_postType" name="cuPleaderBack_postType" value="${cuPm}" >
											<img class="cuPleaderBack" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					        			</div>
			   						</div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
								        <label class="control-label" for="">日期</label>
								        <div>
								            <input type="text" class="form-control input-sm datepicker-default field-editable addClear allText cuPm" value="" id="selfCheckDate" name="selfCheckDate"/>
								        </div>
								    </div>
								    <div class="form-group col-md-12 col-sm-12">
								        <label class="control-label" for="reinspectionResult">复验结果</label>
								        <div>
								        	<textarea class="form-control input-sm  field-not-editable addClear allText suJgj" row="2" id="reinspectionResult" name="reinspectionResult"  data-parsley-maxlength="200"></textarea>
								        </div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12 allSign suJgj">
								        <label class="control-label signature-tool" for=suJgjBack>现场监理</label>
					        			 <div>
					        				<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_12"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="suJgjBack" name="suJgjBack" value="" class="sign-data-input">
											<input type="hidden" class="signPost" id="suJgjBack_postType" name="suJgjBack_postType" value="${suJuj}" >
											<img class="suJgjBack" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					        			</div>
			   						</div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
								        <label class="control-label" for="">日期</label>
								        <div>
								            <input type="text" class="form-control input-sm datepicker-default field-editable addClear allText suJgj" value="" id="reinspectionDate" name="reinspectionDate"/>
								        </div>
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
			        <div class="iframe-big-box ">
			        	<div class="iframe-report-box ">
		                 	<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:0; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('整改通知 - 工程施工管理系统 ');
    $('#rectifiyNoticeForm').toggleEditState(false);
    $("#rectifiyNoticeForm").styleFit();
    $(".editbtn").addClass("hidden");
  //是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	console.info(projJson);
    	$("#projId").val(projJson.projId);
    	$("#projAddr").val(projJson.projAddr);
    	$("#projName").val(projJson.projName);
    	$("#projNo").val(projJson.projNo);
    	$("#suName").val(projJson.suName);
    	$("#constructionUnit").val(projJson.cuName);
    	$("#tenantId").val(projJson.tenantId);
    	$("#deptId").val(projJson.deptId);
    	$("#orgId").val(projJson.orgId);
    	$("#corpId").val(projJson.corpId);
    	$("#corpName").val(projJson.corpName);
    }
	$("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6, #signBtn_7, #signBtn_8, #signBtn_9, #signBtn_10, #signBtn_11, #signBtn_12").handleSignature(); 
    
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });
     $.getScript('projectjs/constructmanage/rectify-notice-back.js?v=1000').done(function () {
       	rectifyNotice.init();
       }); 
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
   	 var cptPath = '<%=basePath%>';
   	 //右屏导入帆软报表
   	 var showReport = function(){
   		var rnId = $('#rnId').val();
   		//获取table的选种行信息，传递到报表
   		var projName = $('#projName').val();
   		var corpName = $('#corpName').val();
   		var projNo = $('#projNo').val();
   		var suName = $('#suName').val();
   		suName = encodeURIComponent(cjkEncode(suName));
   		projName = encodeURIComponent(cjkEncode(projName));
   		corpName = encodeURIComponent(cjkEncode(corpName));
   		src =  cptPath + "/ReportServer?reportlet=constructmanage/rectifyNoticeBack.cpt&corpName="+corpName+"&rnId="+rnId+"&projName="+projName+"&imgUrl="+$('.imgUrl').val();
   		src = src+"&projNo="+projNo+"&suName="+suName;
		$("#mainFrm").attr("src",src); 
   	 };
	 var showReport1 = function(){
   		 var json=trSData.rectifyNoticeTable.json;
   		 var rnId,projName,corpName,projNo,suName;
   		 //获取table的选种行信息，传递到报表
   		if(json){
   			projName = json.projName;
   			corpName = json.corpName;
   			rnId = json.rnId;
   		}else{
   			projName = $('#projName').val();
   			corpName = $('#corpName').val();
   		}
   		suName = $('#suName').val();
   	 	projNo = $('#projNo').val();
   		suName = encodeURIComponent(cjkEncode(suName));
   	 	projName = encodeURIComponent(cjkEncode(projName));
		corpName = encodeURIComponent(cjkEncode(corpName));
		src =  cptPath + "/ReportServer?reportlet=constructmanage/rectifyNoticeBack.cpt&corpName="+corpName+"&rnId="+rnId+"&projName="+projName+"&imgUrl="+$('.imgUrl').val();
		src = src+"&projNo="+projNo+"&suName="+suName;
		$("#mainFrm").attr("src",src); 
   	 }
</script>
<!-- ================== END PAGE LEVEL JS ================== -->