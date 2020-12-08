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
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">联系单列表</a></li>
		                <li class="" id="addTab"><a href="#default-tab-2" id="signTab"  data-toggle="tab">联系单记录</a></li>
                	</ul>
                </div>
                <div class="panel-body" id="box">
                	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                	<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="projectContactsTable" width="100%">
	                			<thead>
		                			<tr>
		                				<th></th>
			                			<th>工程编号</th>
			                			<th>工程名称</th>
			                			<th>联系部门</th>
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
								<form class="form-horizontal" id="projectContactsForm" action="">
									<input type="hidden"  id="pcsId" name ="pcsId"  />
									<input type="hidden" id="projId"name="projId" value="">
									<input type="hidden" id="version" name="version" class="addClear">
									<div class="form-group col-sm-6">
										<label class="control-label" for="projNo">工程编号</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="projNo" name="projNo"  />
										</div>
									</div>
									<div class="form-group col-sm-6">
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
										<label class="control-label" for="duName">单位名称</label>
										<div>
											<input type="text" class="form-control input-sm field-editable "  id="duName" name="duName"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="contactDate">联系日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable "  id="contactDate" name="contactDate"  />
										</div>
									</div>
								    <div class="form-group col-sm-12">
										<label class="control-label" for="contactContent">联系内容</label>
										<div>
											<textarea class="form-control field-editable" name="contactContent" id="contactContent" rows="5" cols="" data-parsley-maxlength="500" ></textarea>
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="disposeOpinion">处理意见</label>
										<div>
											<textarea class="form-control field-editable" name="disposeOpinion" id="disposeOpinion" rows="3" cols="" data-parsley-maxlength="300" ></textarea>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label signature-tool" for="contacter">联系人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="contacter" name="contacter" value="">
											<%--<input type="hidden" id="cuPm_postType" name="cuPm_postType" value="${DEPUTY_DIRECTOR }" >--%>
		                               		<img class="contacter" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="disposer">处理人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="disposer" name="disposer" value="">
											<%--<input type="hidden" id="suCes_postType" name="suCes_postType" value="${SUPERVISOR }" >--%>
											<img class="disposer" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6 allSign builder">
										<label class="control-label signature-tool" for="builder">现场代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
											<%--<input type="hidden" id="cuPm_postType" name="cuPm_postType" value="${DEPUTY_DIRECTOR }" >--%>
											<img class="builder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>

									<div class="form-group col-md-6 col-sm-12 allSign suJgj">
										<label class="control-label signature-tool" for="suJgj">现场监理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<%--<input type="hidden" id="suCes_postType" name="suCes_postType" value="${SUPERVISOR }" >--%>
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign cuPm">
										<label class="control-label signature-tool" for="cuPm">项目经理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="cuPm" name="cuPm" value="">
											<%--<input type="hidden" id="suCes_postType" name="suCes_postType" value="${SUPERVISOR }" >--%>
											<img class="cuPm" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign construction">
										<label class="control-label signature-tool" for="construction">施工员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_8"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="construction" name="construction" value="">
											<%--<input type="hidden" id="suCes_postType" name="suCes_postType" value="${SUPERVISOR }" >--%>
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
        $('#projAddr').val(projJson.projAddr);
    }
	    $("#projectContactsForm").styleFit();
	    //日期datepicker
	    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
	    	$('.datepicker-default').datepicker({
	            todayHighlight: true
	        });
	    });
	
	    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
	    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6, #signBtn_7, #signBtn_8");
	    	signatures.handleSignature(false);        	    	
	    });
	    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
	    //加载打印预览
	    var showReport = function(data){
	    	var projId = $("#projId").val(),						//工程名称
	    	pcsId = $("#pcsId").val();
	    	projId = encodeURIComponent(cjkEncode(projId));
	    	src="<%=basePath%>/ReportServer?reportlet=constructmanage/projectContacts.cpt&";
	    	src=src+"&pcsId="+pcsId+"&projId="+projId+"&imgUrl="+$(".imgUrl").val();
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
    $.getScript('projectjs/constructmanage/project-contacts.js').done(function () {
        projectContacts.init();
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->