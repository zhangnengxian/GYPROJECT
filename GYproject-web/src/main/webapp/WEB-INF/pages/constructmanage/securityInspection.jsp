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
						<li class="active"><a href="#inspection_list" data-toggle="tab"id ="securityCheckList">检查列表</a></li>
						<li class=""><a href="#inspection_record" data-toggle="tab" id="securityCheckRecord">检查记录</a></li>
					</ul>
                </div>
                <div class="panel-body " id="box">
                	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                	<div class="tab-content">
	                	<div class="tab-pane fade active in btn-top active" id="inspection_list" >
	                		<input name="addBtnHide" id="addBtnHide" hidden value="${addBtnHide}"/>
	                		<table class="table table-hover table-striped table-bordered nowrap" id="securityInspectionTable" width="100%">
	                			<thead>
	                			<tr>
	                				<th>id</th>
		                			<th>日期</th>
		                			<th>扣分合计</th>
		                			<th>检查人</th>
	                			</tr>
	                			</thead>
	                		</table>
	                	</div>
	                	<div class="tab-pane fade in btn-top" id="inspection_record">
	                		<div class=" f-r p-b-10 editbtn">
	    					 	<a href="javascript:;" class="btn btn-default btn-confirm btn-sm m-l-5 checkRole saveCustomer" >保存</a>
	        				 	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 checkRole giveUpSave">放弃</a>
						 	</div>
						 	 <div class="clearboth form-box p-b-10">
						 	 	<input type="hidden" class="deptName" id="deptName"  value="${deptName}"/>
						 	 	<form class="form-horizontal" id="securityInspectionForm" action="">
						 	 	<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
						 	 	<input type="hidden" value="" id="ilId" name="ilId"/>
						 	 	<input type="hidden" value="" id="corpId" name="corpId"/>
						 	 	<input type="hidden" value="" id="district"name="district"/>
						 	 	<input type="hidden" value="" id="projId" name="projId"/>
						 	 	<input type="hidden" value="" id="projNo" name="projNo"/>
							 		<div class="form-group col-xs-12">
								        <label class="control-label" for="projId">工程名称</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"  value="" id="projName" name="projName" />
					        			</div>
			   						</div>
			   						<div class="form-group col-md-12 col-sm-12" style="clear:both">
								        <label class="control-label" for="projAddr">工程地点</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"  value="" id="projAddr" name="projAddr"  />
					        			</div>
			   						</div>
			   						<div class="form-group col-md-6 col-sm-12 hidden">
								        <label class="control-label" for="constructionDepartment">施工部门</label>
								        <div>
								        	<input type="text" class="form-control input-sm field-not-editable" value="" id="constructionDepartment" name="constructionDepartment"  />
								        </div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12 ">
								        <label class="control-label" for="cuName">分包单位</label>
								        <div>
								        	<input type="text" class="form-control input-sm field-not-editable" value="" id="cuName" name="cuName"  />
								        </div>
								    </div>
			   						<div class="form-group col-md-12 col-sm-12">
								        <label class="control-label" for="projScaleDes">工程规模</label>
								        <div>
								            <textarea type="text" class="form-control input-sm field-not-editable" value="" id="projScaleDes" name="projScaleDes" ></textarea>
								        </div>
								    </div>
								    <!-- <div class="form-group col-md-6 col-sm-12">
								        <label class="control-label" for="areaId">区域</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable" value="" id="areaId" name="areaId" />
								        </div>
								    </div> -->
			   						<div class="form-group col-md-6 col-sm-12">
								        <label class="control-label" for="fieldPrincipal">现场负责人</label>
								        <div>
								            <input type="text" class="form-control input-sm field-editable allText technician" value="" id="fieldPrincipal" name="fieldPrincipal" data-parsley-maxlength="20"/>
								        </div>
								    </div>
			   						<div class="form-group col-md-6 col-sm-12">
								        <label class="control-label" for="total">扣分合计</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable" value="" id="total" name="total" />
								        </div>
								    </div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checkDepartment">检查部门</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" id="checkDepartment" name="checkDepartment" "/>
										</div>
									</div>
			   						<div class="form-group col-md-6 col-sm-12">
								        <label class="control-label">检查日期</label>
								        <div>
								            <input type="text" class="form-control input-sm field-editable datepicker-default allText technician" value="" id="checkDate" name="checkDate"  data-parsley-required="true"/>
								        </div>
								    </div>
			   						<div class="form-group col-md-12 col-sm-12">
			   						<!-- 备注修改为 检查情况及其他问题 -->
								        <label class="control-label" for="remark">检查情况及问题</label>
								        <div>
								             <textarea class="form-control field-editable allText technician"" name="remark" id="remark" rows="4" cols="" data-parsley-maxlength="200" ></textarea>
								        </div>
								    </div>
			   						<div class="form-group col-md-6 col-sm-12 allSign technician">
										<label class="control-label signature-tool" for="">检查人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="inspector" name="inspector" value="" class="sign-data-input">
											<input type="hidden" id="inspector_postType" name="inspector_postType" value="${SAFTYOFFICER }" >
											<img class="" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
			   						<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="">受查人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="examinee" name="examinee" value="" class="sign-data-input">
											<input type="hidden" id="examinee_postType" name="examinee_postType" value="${SUB_FIELDPRINCIPAL }" >
											<img class="" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    	<div class="form-group col-md-6 col-sm-12 allSign technician">
										<label class="control-label signature-tool" for="">复查人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white disabled" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="reviewOfPeople" name="reviewOfPeople" value="" class="sign-data-input">
											<input type="hidden" id="reviewOfPeople_postType" name="reviewOfPeople_postType" value="${reviewOfPeoplePostion}" >
											<img class="" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <div class="form-group col-md-12 col-sm-12">
								        <label class="control-label" for="reviewOfOpinion">复查情况及问题</label>
								        <div>
								             <textarea class="form-control  field-not-editable allText technician" name="reviewOfOpinion"  id="reviewOfOpinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea>
								        </div>
								    </div>
							 	</form>
						 	</div>
						    <div class="toolbtn pull-right editbtn clearboth p-b-10 addBtnClass">
						    	<a href="javascript:;" class="btn btn-default btn-sm cancelSave" id="guifan"><i class="fa fa-navicon"></i> 违反条目</a>
						    	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 delReport">删除</a>
						    </div>
						 	<table id="securityInspectionAuditTable" class="table table-striped table-bordered nowrap" width="100%">
	                           <thead>
	                               <tr>
	                               	   <th>id</th>
	                               	   <th>saId</th>
		                               <th>检查单id</th>
		                               <th>工程id</th>
		                               <th>工程编号</th>
		                               <th>项目</th>
		                               <th>违反条目</th>
		                               <th>扣分</th>
	                               </tr>
	                           </thead>
		                    </table>
		                    <div class="clearboth form-box">
								<div class="form-group width-full attach-images-list" id="projectImagesList">
								     <h4><i class="fa fa-file-photo-o"></i> 照片列表
								     <a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right">
								     <i class="fa fa-upload"></i> 上传</a>
								     <a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5">
								     <i class="fa fa-camera"></i> 拍照</a></h4>
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
            <div class="panel panel-inverse tabs-mixin" id="">
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
						<iframe id="mainFrm" class="iframe-report" style="width: 794px; height: 1123px;border:1; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('安全检查 - 工程施工管理系统 ');
    $('#securityInspectionForm').toggleEditState(false);
    //是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    }
    $("#projId").val(getProjectInfo().projId);
    $("#corpId").val(getProjectInfo().corpId);
    $("#projName").val(getProjectInfo().projName);
    $("#projNo").val(getProjectInfo().projNo);
    $("#projAddr").val(getProjectInfo().projAddr);
    $("#constructionDepartment").val(getProjectInfo().managementOffice);
    $("#cuName").val(getProjectInfo().cuName);
    $("#projScaleDes").val(getProjectInfo().projScaleDes);
    $("#securityInspectionForm").styleFit();
    $(".editbtn").addClass("hidden");
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });
    $("#unqualityPointContent").linkSubSelect();
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
		 var src = getSrc();
   		$("#mainFrm").attr("src",src); 
   		
}
  var getSrc = function(){
	  var checkDate='',fieldPrincipal='',ilId = "-1";
	  //console.log(trSData);
	  if(trSData.securityInspectionTable){
		  if(trSData.securityInspectionTable.json && isAddRow != 1 ){
			  //ilId = trSData.securityInspectionTable.json.ilId;
			  ilId =  encodeURIComponent(cjkEncode($("#ilId").val()));
			  checkDate = encodeURIComponent(cjkEncode(trSData.securityInspectionTable.json.checkDate));			//检查日期
			  fieldPrincipal = encodeURIComponent(cjkEncode(trSData.securityInspectionTable.json.fieldPrincipal));	//现场负责人
		  }
	  }
 		var projName = encodeURIComponent(cjkEncode($("#projName").val())),											//工程名称
	 	projAddr = encodeURIComponent(cjkEncode($("#projAddr").val())),												//工程地点
	 	imgUrl = $('.imgUrl').val(),
	 	projScaleDes = encodeURIComponent(cjkEncode($("#projScaleDes").val())),										//工程规模
	 	cuName=encodeURIComponent(cjkEncode($("#cuName").val())),
	 	areaDes = encodeURIComponent(cjkEncode($("#areaId").val())),												//区域
		checkDepartment=encodeURIComponent(cjkEncode($("#checkDepartment").val())),					//检查单位
	 	remark = encodeURIComponent(cjkEncode($("#remark").val()));
 		var src= cptPath + "/ReportServer?reportlet=constructmanage/securitInspection.cpt&projName="+projName+"&projAddr="+projAddr+"&projScaleDes="+projScaleDes+"&imgUrl="+imgUrl;
			src=src+"&areaDes="+areaDes+"&checkDepartment="+checkDepartment+"&checkDate="+checkDate+"&fieldPrincipal="+fieldPrincipal+"&ilId="+ilId+"&cuName="+cuName;
			src = src + "&remark="+remark;
			return src;
  }
	showReport();
	$.getScript('projectjs/constructmanage/security_inspection_record.js?v='+Math.random()).done(function () {
    	securityInspection.init();
    });
    
  	//点击放弃
    $('.giveUpSave').off().on('click',function(){
    	/* $('ul.nav>li').removeClass("active");
		$('#securityCheckList').click(); */
    	$('#securityCheckList').tab("show");
    	$('#securityInspectionTable').cgetData(true);
    });
  	
  	
  	//移动端点击上传
    $(".uploadBtn").off("click").on("click",function(){
	    var ilId = $("#ilId").val(),
		projNo = $("#projNo").val(),
		projId = $("#projId").val(),
		stepId=getStepId();
	    busRecordId=ilId;
		if(_inApk && $(".attach-images-list").find(".new-image").length){
				//photoUpload($(".attach-images-list .upload-image-btn"), projId, projNo, getStepId(), ilId);
				
			var images = $(".attach-images-list .uploadBtn").parents(".attach-images-list").find(".new-image"),
		    files = [];
		    $.each(images, function (i, el) {
		        files.push($(el).find(".preview-btn").attr("src-orig"));
		    });
		    //navigator.notification.alert(files);
		    if (!busRecordId && $("#ilId").length && !$("#ilId").val()) {
		        navigator.notification.alert('请先保存该条记录，保存记录后会自动上传图片', null, "提醒", "确定");
		        return false;
		    }
		    if (files.length) {
		    	alert(1);
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