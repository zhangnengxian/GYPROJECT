<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- grooveRecord.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

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
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表区</a></li>
                         <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
		                <li class=""><a href="#default-tab-2" id="signTab"  data-toggle="tab">签字区</a></li>
		               
                	</ul>
                </div>
				<%--列表--%>
               	<div class="panel-body" id="">
               		<div class="tab-content">
                   <input type="hidden" id="loginPost" value="${loginPost }"/>
					<input type="hidden" id="builderPost" value="${builderPost }"/>
					<input type="hidden" id="sujgjPost" value="${sujgjPost }"/>
               			<input type="hidden" id="pcIdNew">
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
						<%--签字--%>
	                   	<div class="tab-pane fade btn-top" id="default-tab-2">
	                       	<div class="toolBtn f-r p-b-10 update-show" >
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole savebtn">保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">放弃</a>
						    </div>
						    <div class="clearboth form-box">
						    	<input type="hidden" class="imgUrl" value="${imgUrl}"/>
								<form class="form-horizontal" id="signForm" action="grooveRecord/saveSign">
								    <input type="hidden" id="pcDesId" name="pcDesId" value="${checkType}"/> 
									<input type="hidden" id="pcId" name="pcId" class="addClear"/>
									<input type="hidden" id="finishedDate" name="finishedDate">
									<input type="hidden" id="finishedOpr" name="finishedOpr">
									<input type="hidden" id="resetDate" name="resetDate">
									<input type="hidden" id="resetReason" name="resetReason">
									<input type="hidden" id="isFinishSign" name="isFinishSign">
									<%--<input type="hidden" id="version" name="version" class="addClear"/>
									<input type="hidden" id="projId" name="projId"/>
									<input type="hidden" id="recordsId" name="recordsId">
									<input type="hidden" id="insDate" name="insDate">
									<input type="hidden" id="status" name="status">
									<input type="hidden" id="sendDate" name="sendDate">
									<input type="hidden" id="upgradeDate" name="upgradeDate" value="${upgradeDate}">--%>
									<!-- 完成标记 -->
									<input type="hidden" id="flag" name="flag">
									<div class="form-group  col-sm-12">
										<label class="control-label" for="projNo">工程编号</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projNo" name="projNo"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm" id="projName" name="projName"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="projAddr">工程地点</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projAddr" name="projAddr"  />
										</div>
									</div>
									
									<div class="form-group col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									 <div class="form-group col-sm-12">
										<label class="control-label" for="suName">监理公司</label>
										<div>
											<input type="text" class="form-control field-editable input-sm field-not-editable"  id="suName" name="suName"  />
										</div>
								    </div>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label">报验日期</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable addClear datepicker-default"  id="inspectionDate" name="inspectionDate"  readonly="readonly"/>
										</div>
									</div> 
<%--						    		<div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear allText construction" name="inspectionResult" id="inspectionResult" rows="2" cols="" data-parsley-maxlength="200" data-parsley-required="true" ></textarea>
					                    </div>
					  			    </div>--%>
									<div class="form-group col-sm-12 col-xs-12 allSign construction">
										<label class="control-label signature-tool  sign-require" for="cuPm">复压人员</label><%--对应表中施工项目经理签字--%>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="cuPm" name="cuPm" value="">
											<input type="hidden" class="signPost" id="cuPm_postType" name="cuPm_postType" >
											<img class="grBuilder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
								</form>
								<!-- 照片 -->
<%--								<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list " id="projectImagesList">
									     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
									     <ul class="row">
									     </ul>
									</div>
								</div> --%>
							</div>
	                   	</div>
						<%--记录--%>
	                   	<div class="tab-pane fade  btn-top" id="default-tab-3">
	                   		<div class="toolBtn f-r p-b-10 auditEditBtn" >
			                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5    auditInpectBtn">报验</a>
			                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5    auditAddBtn2 ">新增</a>
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5  auditEditBtn auditSavebtn2">保存</a>
						   		 <a href="javascript:;" class="btn btn-warn btn-sm m-l-5  auditEditBtn deleteBtn2">删除</a>
						    </div>
						    <div class="clearboth form-box">
						    	<!-- 报验完成状态 -->
						    	<input type="hidden" id="flag1"  class="addClear">
		                   		<form class="form-horizontal" id="auditForm" data-parsley-validate="true" action="">
		                   			<input type="hidden" id="id" name="id" class="addClear">
		                   			<input type="hidden" id="recordPcId" name="pcId" class="addClear">
		                   			<input type="hidden" id="isFinishSign" name="isFinishSign" class="addClear">

							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="riserNumber">楼栋立管编号</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable addClear " value="" id="riserNumber" name="riserNumber" data-parsley-maxlength="200" />
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="record">复压记录</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable addClear " value="" id="record" name="record" data-parsley-maxlength="200" />
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="installTrouble">户内安装隐患</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable addClear " value="" id="installTrouble" name="installTrouble"  data-parsley-maxlength="200"/>
							            	<!-- <a href="javascript:;" class="btn btn-sm btn-default">M</a> -->
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="rectifyInfo">整改完成情况</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable addClear " value="" id="rectifyInfo" name="rectifyInfo" data-parsley-maxlength="200" />
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="rectifyTeam">整改班组</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable addClear " value="" id="rectifyTeam" name="rectifyTeam" data-parsley-maxlength="200"/>
							            </div>
							        </div>
									<div class="form-group col-sm-12 col-xs-12">
										<label class="control-label" for="remark">备注</label>
										<div>
											<textarea class="form-control  field-editable addClear " name="remark" id="remark" rows="3" cols="" data-parsley-maxlength="500" ></textarea>
										</div>
									</div>
<%--							        <div class="form-group col-sm-6 col-xs-12 allSign construction">
							            <label class="control-label signature-tool  sign-require" for="grBuilder">复压人员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="grBuilder" name="grBuilder" value="">
											<input type="hidden" class="signPost" id="grBuilder_postType" name="grBuilder_postType" >
											<img class="grBuilder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
							        </div>--%>
			                       	<table id="recordListTable" class="table table-striped table-bordered nowrap" width="100%">
			                            <thead>
			                                <tr>
			                                    <th>操作</th>
			                                    <th>楼栋立管编号</th>
			                                    <th>复压记录</th>
			                                    <th>户内安装隐患</th>
			                                    <th>整改完成情况</th>
			                                    <th>整改班组</th>
			                                    <th>备注</th>
			                                </tr>
			                            </thead>
			                       	</table>
		                       	</form>
		                       	
		                       		 	<!-- 照片 -->
<%--		                       	<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list hidden" id="recordImagesList">
										<input type="hidden" id="grId1" />
										<input type="hidden" id="pcId1" />
									     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
									     <ul class="row">
									     </ul>
									</div>
								</div> --%>
		                       	
		                    </div>
	                   	</div>
                    </div>
               	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" >
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <h4 class="panel-title">预览区</h4>
			    </div>
			    <div class="panel-body" id="">
	                 <div class="clearboth form-box">
	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 1180px; height: 850px; border:1;overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('复压记录- 工程项目管理系统 ');
    //表单样式
    $("#signForm").toggleEditState().styleFit();
    $("#auditForm").toggleEditState().styleFit();
    
    //$('.update-show').addClass('hidden');
    //$('.auditEditBtn').addClass('hidden');
	
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projAddr').val(projJson.projAddr);				   //
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#projAddr').val(projJson.projAddr); 				   //施工地点
    	$('#constructionUnit').val(projJson.cuName); 		   //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    }
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    
   //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 
	//签字加载方式
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	var signatures = $("#signBtn_1");
    	signatures.handleSignature();        	    	
    });
	var num=Math.random()*1000;
    $.getScript('projectjs/inspection/inspection-pressure-records.js?v='+num).done(function() {
   		$.getScript('projectjs/inspection/pressure-audit.js?'+num).done(function () {
      	 	pressureRecordAndAudit.init();
		});
    });
    
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    var cptPath = '<%=basePath%>';
    //加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    
    //加载记录区的cpt文件
    var showReport = function(){
    	var pcId=$('#pcIdNew').val(),projName='',projNo='',constructionUnit='',suName='',inspectionDate='';
    	var json=trSData.projectCheckListTable.json;
	    if(json){
	    	projNo = json.projNo;
	    	projName =json.projName;
	    	constructionUnit=json.constructionUnit;
	    	suName=json.suName;
	    	inspectionDate=json.inspectionDate;
	    }else{
	    	
	    	projNo = $("#projNo").val();
	    	projName=$('#projName').val();
	    	inspectionDate=$('#inspectionDate').val();
	    	constructionUnit=$('#constructionUnit').val();
	    	suName=$('#suName').val();
	     }
	    if(!pcId){
	    	pcId='-1';
	    	inspectionDate='';
	    }
	    //解决乱码
   	    projName=encodeURIComponent(cjkEncode(projName));    	    
   	    suName=encodeURIComponent(cjkEncode(suName));
   	    constructionUnit=encodeURIComponent(cjkEncode(constructionUnit));
   	    var ur = "pressureRecords.cpt";
    	var src = cptPath+"/ReportServer?reportlet=inspection/"+ur+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src);
    };

</script>
<!-- ================== END PAGE LEVEL JS ================== -->
