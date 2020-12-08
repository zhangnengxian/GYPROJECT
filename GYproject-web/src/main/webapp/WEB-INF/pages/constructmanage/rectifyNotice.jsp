<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
            <div class="panel panel-inverse tabs-mixin">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                	</div>
                	<ul class="nav nav-tabs">
						<li class="active"><a href="#rectification_record" data-toggle="tab"id ="rectificationList">整改列表</a></li>
						<li class=""><a href="#rectification_info" data-toggle="tab" id="rectificationInfo">整改信息</a></li>
					</ul>
                </div>
                <div class="panel-body" id="box">
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
								<a href="javascript:;" class="btn btn-default btn-confirm btn-sm m-l-5 checkRole saveCustomer" onclick="">保存</a>
								<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 checkRole giveUpSave">放弃</a>
							</div>
							<div class="clearboth form-box p-b-10">
								<input type="hidden" id="imgUrl" name="imgUrl" class="imgUrl" value="${imgUrl }">
								<input type="hidden" id="IsSignature" name=IsSignature value="${IsSignature }">
								<form class="form-horizontal" id="rectifiyNoticeForm" action="rectifyNotice/saveRectifiyNotice">
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
											<input type="text" class="form-control input-sm field-not-editable" value="" id="projNo" name="projNo"/>
										</div>
									</div>
									<div class="form-group col-xs-12">
										<label class="control-label" for="projId">工程名称</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="projName"  name="projName"/>
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="">工程地点</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" id="projAddr" name="projAddr">
										</div>
									</div>
									<div class="form-group col-md-12 col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="constructionUnit"  name="constructionUnit"/>
										</div>
									</div>
									<div class="form-group col-md-12 col-sm-12">
										<label class="control-label" for="suName">监理单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="suName" name="suName"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="rectifyNoticeType">整改类型</label>
										<div>
											<select class="form-control input-sm field-editable addClear" name="rectifyNoticeType" id="rectifyNoticeType" data-parsley-required="true">
												<c:forEach var="enums" items="${rectifyNoticeType }">
													<option value="${enums.value }">${enums.message }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div id="reviewDiv" class="form-group col-md-12 col-sm-12">
										<label class="control-label" for="">复查情况</label>
										<div>
											<textarea class="form-control input-sm  field-editable addClear " row="15" id="review" name="review" data-parsley-required="true" data-parsley-maxlength="1000"></textarea>
										</div>
									</div>
									<!--整改类型下不同内容-->
									<div id="differentContentsId"></div>
								</form>
							</div>
							<div class="clearboth form-box">
								<div class="form-group width-full attach-images-list" id="projectImagesList">
									<h4><i class="fa fa-file-photo-o"></i> 照片列表
										<a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a>
									</h4>
									<ul class="row"></ul>
								</div>
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

    if(!getProjectInfo()){ //是否已选工程项目
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
        $("#projId").val(projJson.projId);
        loadPartContent(projJson.projId,getStepId(),"1");
    }


   function loadPartContent(projId,menuId,rnType,f) {
       $('#differentContentsId').cgetContent("rectifyNotice/queryJsp",{"projId":projId,"menuId":menuId,"rnType":rnType},function () {
           $('#rectifiyNoticeForm').deserialize(getProjectInfo());
           f();
       });

   }




    $("#projectImagesList").getImagesList({
        "projId": getProjectInfo().projId,
        "stepId": getStepId(),
        "projNo": getProjectInfo().projNo,
        "busRecordId": $("#rnId").val() || '-1'
    });
	$("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6, #signBtn_7, #signBtn_8, #signBtn_9, #signBtn_10").handleSignature(); 
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    	
    });
     $.getScript('projectjs/constructmanage/rectify-notice.js?'+Math.random()).done(function () {
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
	 var showReport1 = function(){
   		 var rnId=$("#rnId").val(),projName,corpName;
   		 //获取table的选种行信息，传递到报表
   		if(trSData.rectifyNoticeTable.json){
   			projName = trSData.rectifyNoticeTable.json.projName;
   			corpName = trSData.rectifyNoticeTable.json.corpName;
   		}else{
   			projName = $('#projName').val();
   			corpName = $('#corpName').val();
   		}
   	 	projName = encodeURIComponent(cjkEncode(projName));
		corpName = encodeURIComponent(cjkEncode(corpName));
	 	var src="";
			src =  cptPath + "/ReportServer?reportlet=constructmanage/"+cptType;
		    src += "&corpName="+corpName+"&rnId="+rnId+"&projName="+projName+"&imgUrl="+$('.imgUrl').val();
		$("#mainFrm").attr("src",src);  
   	 } 
	 
	 $("#rectifyNoticeType").on("change",function(){
         loadPartContent($("#projId").val(),getStepId(),$(this).val());

         if($(this).val()=='2'){
			 $(".resident").addClass("hidden");
			 $("#limitTime").val('');
		 }else{
			 $(".resident").removeClass("hidden");
		 }
	 	 var json = {};     //整改类型改变时，报表进行改变
		 json.rnId = $("#rnId").val();
		 json.corpId = $("#corpId").val();
		 if($("#corpId").val() == null || $("#corpId").val() == ''){
			 json.corpId = '';
			 json.menuId = '120209'; 
		 }
		 queryCptType(json);
	 });


</script>
<!-- ================== END PAGE LEVEL JS ================== -->