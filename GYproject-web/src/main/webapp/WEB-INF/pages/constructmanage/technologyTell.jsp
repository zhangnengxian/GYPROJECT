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
<style>
 h4.alarmProj{
 	padding-top: 15px;
    margin-left:15px;
    clear: both;
 }
</style>
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
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">交底列表</a></li>
		                 <li class="" id="addTab"><a href="#default-tab-2" id="signTab"  data-toggle="tab">交底信息</a></li>
                	</ul>
                </div>
                <div class="panel-body">
                	<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >

               			    <!--<input type="hidden" id="addShow"> -->
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="listTable" width="100%">
	                			<thead>
		                			<tr>
		                				<th></th>
		                				<th>工程名称</th>
			                			<th>交底日期</th>
			                			<th>图号</th>
		                			</tr>
	                			</thead>
	                		</table>
	                	</div>
		               	<div class="tab-pane fade btn-top" id="default-tab-2">
			                <div class="toolBtn f-r p-b-10 editBtn">
			                	<a href="javascript:;" class="btn btn-confirm checkRole btn-sm m-l-5 saveBtn">保存</a>
			                    <a href="javascript:;" class="btn btn-warn checkRole btn-sm m-l-5 giveUpBtn">放弃</a>
			                    <a href="javascript:;" class="btn  btn-primary checkRole btn-sm m-l-5 hidden get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>
						    </div>
							<div class="clearboth form-box">
								<input type="hidden" class="imgUrl" value="${imgUrl}"/>
								<input type="hidden"  id="loginPost" value="${loginPost}"/>
								<input type="hidden"  id="dataAdmin" value="${dataAdmin}"/><!--数据管理员-->
								<input type="hidden"  id="corpId" value="${corpId}"/>
								<input type="hidden"  id="cuManager" value="${cuManager}"/><!--施工代表-->
								<input type="hidden" name="customerServiceCenter" id="customerServiceCenter"  value="${customerServiceCenter}"/>
								<form class="form-horizontal" id="technologyTellForm" action="">
									<input type="hidden" id="cwId" name ="cwId" class="addClear"/>
									<input type="hidden" id="projId"name="projId" value="">
									<input type="hidden" id="projNo"name="projNo" value="">
									<input type="hidden"id="deptDivide"name="deptDivide" value="">
									<input type="hidden" id="signState"name="signState" >
									<input type="hidden" id="version"name="version" class="addClear" value="">
									<input type="hidden" id="projAddr"name="projAddr" value="">
									<input type="hidden" id="sysDate"  value="${sysDate }">
									<input type="hidden" id="hiddenConfig" name="hiddenConfig" value="">

									<div class="form-group col-md-12  col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="projName" name="projName"  />
										</div>
									 </div>
									<div class="form-group col-md-12  col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="constructionUnit" name="constructionUnit"  />
										</div>
									 </div>
									<div class="form-group col-md-12  col-sm-12 suUnit">
										<label class="control-label" for="suName">监理单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="suName" name="suName"  />
										</div>
									 </div>
									<div class="form-group col-md-12  col-sm-12">
										<label class="control-label" for="custName">建设单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="custName" name="custName"  />
										</div>
									 </div>
									<div class="form-group col-md-12  col-sm-12">
										<label class="control-label" for="duName">设计单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="duName" name="duName"  />
										</div>
									 </div>
									 <div class="form-group col-md-6  col-sm-12">
									 	<!-- 新加字段 -->
										<label class="control-label" for="drawingNo">图号</label>
										<div>
											<input type="text" class="form-control input-sm field-editable  allText builder construction" value="" id="drawingNo" name="drawingNo"  data-parsley-maxlength="50"/>
										</div>
									 </div>
									<div class="form-group col-md-6  col-sm-12">
										<label class="control-label" for="">交底日期</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable allText datepicker-default addClear"  data-parsley-required="true" value="" id="cwDate" name="cwDate"  />
										</div>
									</div>
									<div class="form-group col-md-6  col-sm-12">
										<label class="control-label" for="">已具备开工条件</label>
										<div>
											<label> 
												<input type="radio" class="field-editable allText builder construction suJgj" name="isWorked" value="1"  checked="checked"/>是
											</label>
											<label> 
												<input type="radio" class="field-editable allText builder construction suJgj" name="isWorked" value="0"  />否
											</label>
											<!-- <label> 
												<input type="radio" class="field-editable allText builder construction " name="isWorked" value="-1" />无
											</label> -->
										</div>
									</div>
									<div class="form-group col-sm-12 ">
										<!-- 新加字段 -->
				                    	<label class="control-label" for="reviewContent">会审内容</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear allText builder construction designer" name="reviewContent" id="reviewContent" rows="4" cols="" data-parsley-maxlength="500" ></textarea>
					                    </div>
					  			    </div>
									<div class="form-group col-sm-12 ">
				                    	<label class="control-label" for="cwContent">交底内容</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear allText builder construction designer" name="cwContent" id="cwContent" rows="4" cols="" data-parsley-maxlength="500" placeholder="技术及HSE交底内容"></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group col-sm-12 ">
					  			    	<!-- 新加字段 -->
				                    	<label class="control-label" for="findQuestion">发现问题</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear allText builder construction designer" name="findQuestion" id="findQuestion" rows="4" cols="" data-parsley-maxlength="500" placeholder="发现问题及处理情况"></textarea>
					                    </div>
					  			    </div>
									 <div class="form-group col-md-6 col-sm-12 allSign builder">
									 <!-- 甲方代表-改为-现场代表 -->
										<label class="control-label signature-tool" for="fieldPrincipal">现场代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="fieldPrincipal" name="fieldPrincipal" value="">
											<input type="hidden" class="signPost"   id="fieldPrincipal_postType" name="fieldPrincipal_postType" value="${fieldRepresentPost}" >
											<img class="fieldPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								     <!-- <div class="form-group col-md-6  col-sm-12">
									 	新加字段
										<label class="control-label" for="fieldRepresentPost">职务</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear" value="" id="fieldRepresentPost" name="fieldRepresentPost"  data-parsley-maxlength="100"/>
										</div>
									 </div> -->
									<div class="form-group col-md-6 col-sm-12 allSign construction">
										<!-- 项目经理-改为-施工代表 -->
										<label class="control-label signature-tool" for="cuPm">施工代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="cuPm" name="cuPm" value="">
											<input type="hidden" class="signPost"  id="cuPm_postType" name="cuPm_postType" value="${cuManager}" >
											<img class="cuPm" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
								    <!-- <div class="form-group col-md-6  col-sm-12">
									 	新加字段
										<label class="control-label" for="cuPmPost">职务</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear" value="" id="cuPmPost" name="cuPmPost"  data-parsley-maxlength="100"/>
										</div>
									 </div> -->
									<div class="form-group col-md-6 col-sm-12 allSign designer">
										<label class="control-label signature-tool" for="duDeputy">设计人员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="duDeputy" name="duDeputy" value="">
											<input type="hidden" class="signPost"  id="duDeputy_postType" name="duDeputy_postType" value="${designer}" >
											<img class="duDeputy" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <!-- <div class="form-group col-md-6  col-sm-12">
									 	新加字段
										<label class="control-label" for="designerPost">职务</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear" value="" id="designerPost" name="designerPost"  data-parsley-maxlength="100"/>
										</div>
									 </div> -->
								    <div class="form-group col-md-6 col-sm-12 allSign suJgj suUnit">
										<label class="control-label signature-tool" for="suFieldJgj">现场监理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suFieldJgj" name="suFieldJgj" value="">
											<input type="hidden" class="signPost"  id="suFieldJgj_postType" name="suFieldJgj_postType" value="${sujgj}" >
											<img class="suFieldJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
									<!--用户签字-->
									<c:if test="${ !empty userSignPage}">
										<c:import url="${userSignPage}"></c:import>
									</c:if>
									<!--施工班组签字-->
									<c:if test="${ !empty testLeaderSignPage}">
										<c:import url="${testLeaderSignPage}"></c:import>
									</c:if>

								    <!--  <div class="form-group col-md-6  col-sm-12">
									 	新加字段
										<label class="control-label" for="suFieldJgjPost">职务</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear" value="" id="suFieldJgjPost" name="suFieldJgjPost"  data-parsley-maxlength="100"/>
										</div>
									 </div> -->
									<div class="form-group col-md-6 col-sm-12 mobileDiv hidden">
										<label class="control-label">经度</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="longitude" name="longitude" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12  mobileDiv hidden">
										<label class="control-label">纬度</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="latitude" name="latitude" />
										</div>
									</div>
								</form >
								<div class="clearboth form-box">
								<div class="form-group width-full attach-images-list" id="projectImagesList">
								     <h4><i class="fa fa-file-photo-o"></i> 照片列表
										 <a href="javascript:;" class="btn btn-primary btn-sm upload-images-btn pull-right"><i class="fa fa-upload"></i> 上传</a>
										 <a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a>
										 <!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
								     <ul class="row"></ul>
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
            <div class="panel panel-inverse tabs-mixin">
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
    App.setPageTitle('会审交底 - 工程项目管理系统 ');
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
        $('#corpId').val(projJson.corpId);
        $('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.cuName); 		   //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    	$("#custName").val(projJson.corpName);				   //建设单位-燃气集团
    	$("#duName").val(projJson.duName);						//设计单位
    	$("#projAddr").val(projJson.projAddr);					//工程地址
    	$("#drawingNo").val(projJson.designDrawingNo);			//设计图号
    	$("#deptDivide").val(projJson.deptDivide);				//部门划分
    	console.info("projJson==========");
    	console.info(projJson);
    	
    	var cptPath = '<%=basePath%>';
	   	$.getScript('projectjs/constructmanage/technologyTell.js?'+Math.random()).done(function () {
	   		technologyTell.init();
	    });
	   	
	    $("#technologyTellForm").toggleEditState().styleFit();
	    $('.editBtn').addClass("hidden");
	    
	    //日期datepicker
	    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
	    	$('.datepicker-default').datepicker({
	            todayHighlight: true
	        });
	    });
	
	    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
	    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6, #signBtn_7");
	    	signatures.handleSignature();        	    	
	    });
	    
	    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
	    //加载打印预览
		var showReport1 = function(){
			var json=trSData.listTable.json;
			var cwId="";
			if(json){
				cwId=json.cwId;
			}
	    	var projName = encodeURIComponent(cjkEncode(getProjectInfo().projName));
	    	var src=cptPath+'/ReportServer?reportlet=constructmanage/'+cptType+'&projName='+projName;
	    	src=src+'&cwId='+cwId+'&imgUrl='+$(".imgUrl").val();
	    	$("#mainFrm").attr("src",src);
		}
	    //打印预览窗口缩放调整
			if($(".iframe-report").length> 0){
	    	var fr = $(".iframe-report");
	    	for(var i=0; i<fr.length; i++){
	    		fr.eq(i).rescaleReportPanel();
	    	}
	    }
	    //点击保存按钮
	 	 $(".saveBtn").on("click",function(){
            var technologyTellForm = $("#technologyTellForm");
			//开启表单验证
	        if (technologyTellForm.parsley().isValid()) {
	        	var isworked =  $("input[name='isWorked']:checked").val(); 
	        	if(isworked==''){
	        		alertInfo("请选择是否具备开工条件！");
	        		return;
	        	}
	        	  $("#technologyTellForm").loadMask("正在保存...", 1, 0.5);
	        	if($("#deptDivide").val()==$("#customerServiceCenter").val()){
	        		if($("#fieldPrincipal").val()!="" && $("#cuPm").val()!="" && $("#duDeputy").val()!=""){
		        		//现场代表 施工代表 设计人员 均已签字
		        		$("#signState").val("1");
		        	}else{
		        		$("#signState").val("0");
		        	}
	        	}else{
                    $("#signState").val("1"); //默认置为均已签字
					$('.sign-data-input').each(function(){
                        if($(this).val()==""){//未签完字
                            $("#signState").val("0");return false;
						}
                    });
	        	}
	        	
	        	var data=$("#technologyTellForm").serializeJsonString();
                //加遮罩
               // $(".saveBtn").parent().parent().loadMask("正在保存...", 1, 0.5);
	        	$.ajax({
	             type: 'POST',
	             url: 'technologyTell/saveConstructionWork',
	             contentType: 'application/json;charset=UTF-8',
	             data:data,
	             success: function (data) {
	                // $(".saveBtn").parent().parent().hideMask();
	                 $("#technologyTellForm").hideMask();
	             	var content = "数据保存成功！";
	             	if(data === "false"){
	             		content = "数据保存失败！";
	             	}else if(data === "already"){
	               		content = "此信息已被修改，请刷新页面！";
	               	}else{
	             		$('#cwId').val(data);
	             		$("#listTable").cgetData(true, tableCallBack);  
	             		showReport1();
	             	}
	             	var myoptions = {
                         	title: "提示信息",
                         	content: content,
                         	accept: sureClose,
                         	chide: true,
                         	icon: "fa-check-circle"
                     }
                     $("body").cgetPopup(myoptions);
	             	
	             },
	             error: function (jqXHR, textStatus, errorThrown) {
	                 console.warn("交底记录保存失败！");
	             }
	         	});
	        	
	        	 $("#technologyTellForm").toggleEditState(false);
	        	 $('.editBtn').addClass("hidden");
		 		 //如果通过验证, 则移除验证UI
	            technologyTellForm.parsley().validate();
	        } else {
	            //如果未通过验证, 则加载验证UI
	            technologyTellForm.parsley().validate();
	        };
	    }); 
	    
	    var tableCallBack = function(){
	    	//空函数
	    }
	    
	//    点击修改按钮
		 $(".updataBtn").on("click",function(){
		
			 $(".updataBtn").addClass("hidden");
			 $(".saveBtn").removeClass("hidden");
			 $("#technologyTellForm").toggleEditState(true);
			 $("#projAddr").val(getProjectInfo().projAddr);
			 $("#technologyTellForm input[type='radio']").attr("disabled",false);
			/* //施工或竣工中
        	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#technologyTellForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#technologyTellForm").find(".sign-data-input").toggleSign(false);
        	} */
		 });

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
	
	var sureClose=function(){
		var cwId=$("#cwId").val();
		$.ajax({
			type:'post',
			url:'technologyTell/saveSignNotice',
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
	
	
	
};
//移动端点击上传
$("#projectImagesList .upload-images-btn").off("click").on("click",function(){
	    var busRecordId = $("#cwId").val(),
		projNo = $("#projNo").val(),
		projId = $("#projId").val(),
		stepId=getStepId();
		if(_inApk && $(".attach-images-list").find(".new-image").length){
				//photoUpload($(".attach-images-list .upload-images-btn"), projId, projNo, getStepId(), pcId);
				
			var images = $(".attach-images-list .upload-images-btn").parents(".attach-images-list").find(".new-image"),
		    files = [];
		    $.each(images, function (i, el) {
		        files.push($(el).find(".preview-btn").attr("src-orig"));
		    });
		    //navigator.notification.alert(files);
		    if (!busRecordId && $("#cwId").length && !$("#cwId").val()) {
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
	}); 
	
$("#projectImagesList").getImagesList({
    "projId": getProjectInfo().projId,
    "stepId": getStepId(),
    "projNo": getProjectInfo().projNo,
    "busRecordId": $("#cwId").val() || '-1'
});
	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->