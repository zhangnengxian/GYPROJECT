<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jedate/skin/jedate.css" rel="stylesheet" />
<!-- <link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" /> -->

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
						<li class="active"><a href="#shutdown_aproval" data-toggle="tab"id ="shutdownAprovalList">复工报审列表</a></li>
						<li class=""><a href="#shutdown_aproval_info" data-toggle="tab" id="shutdownAprovalInfo">复工报审信息</a></li>
					</ul>
                </div>
                <div class="panel-body " id="box">
                	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                	<div class="tab-content">
	                	<div class="tab-pane fade active in btn-top active" id="shutdown_aproval" >
	                		<table class="table table-hover table-striped table-bordered nowrap" id="shutdownAprovalTable" width="100%">
	                			<thead>
	                			<tr>
	                				<th></th>
	                				<th>停工编号</th>
	                				<th>停工日期</th>
	                				<th>停工部位(工序)</th>
	                				<th>报审状态</th>
	                			</tr>
	                			</thead>
	                		</table>
	                	</div>
	                	<div class="tab-pane fade in btn-top" id="shutdown_aproval_info">
	                		<div class=" f-r p-b-10 editbtn">
	    					 	<a href="javascript:;" class="btn btn-default btn-confirm btn-sm m-l-5 checkRole saveBtn">保存</a>
	        				 	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 checkRole giveUpSave">放弃</a>
						 	</div>
						 	 <div class="clearboth form-box p-b-10">
						 	 	<input type="hidden" class="imgUrl" value="${imgUrl }"/>
						 	 	<form class="form-horizontal" id="shutDownAprovalForm" action="shutdownAproval/saveSign">
						 	 		<input type="hidden" value="" id="projId" name="projId"/>
						 	 		<input type="hidden" value="" id="sdrId" name="sdrId" class="addClear">
						 	 		<input type="hidden" value="" id="sdaId" name="sdaId" class="addClear">
						 	 		<input type="hidden" id="version" name="version" class="addClear">
			   						<div class="form-group col-md-6 col-sm-12">
								        <label class="control-label" for="projNo">工程编号</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" />
								        </div>
								    </div>
							 		<div class="form-group col-md-6 col-xs-12">
								        <label class="control-label" for="projId">工程名称</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"   id="projName" name="projName" />
					        			</div>
			   						</div>

								  <div class="form-group col-md-6 col-xs-12">
								        <label class="control-label" for="sdrNo">停工编号</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable addClear"   id="sdrNo" name="sdrNo" />
					        			</div>
			   						</div>
			   						
			   						<div class="form-group col-md-6 col-sm-12 clearboth">
								        <label class="control-label" for="sdrProcess">停工部位(工序)</label>
					        			<div>
					        				<input type="text" class="form-control input-sm  field-not-editable addClear"   id="sdrProcess" name="sdrProcess" />
					        			</div>
			   						</div>
			   						<div class="form-group col-md-12 col-sm-12 clearboth">
								        <label class="control-label" for="shutdownReason">停工原因</label>
					        			<div>
					        				<textarea class="form-control input-sm  field-not-editable addClear"  id="shutdownReason" name="shutdownReason" >停工原因</textarea>
					        			</div>
			   						</div>
			   						<div class="form-group col-md-6 col-sm-12 rework clearboth">
								        <label class="control-label" for="">复工日期</label>
					        			<div>
					        				<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText cuPm"  id="reworkDate" name="reworkDate" data-parsley-required="true"/>
					        			</div>
			   						</div>
			   						
								 
								    <div class="form-group col-md-12 col-sm-12 clearboth">
										<label class="control-label" for="cuManagerDept">施工项目经理部</label>
										<div>
											<input type="text" class="form-control input-sm  field-not-editable addClear" id="cuManagerDept" name="cuManagerDept" />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12 rework allSign cuPm clearboth">
										<label class="control-label signature-tool sign-require" for="">项目经理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="cuManager" name="cuManager" value="" class="sign-data-input">
											<input type="hidden" class="signPost" id="cuManager_postType" name="cuManager_postType" value="${cuManagerPost }" >
											<img class="cuManager" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12 rework hidden selectSignDate clearboth">
								        <label class="control-label" for="">报审日期</label>
								        <div>
								            <input type="text" class="form-control input-sm datepicker-default addClear field-editable allText cuPm" value="" id="cuApprovalDate" name="cuApprovalDate" />
								        </div>
								    </div>
								     <div class="form-group col-md-12 col-sm-12 rework clearboth">
								        <label class="control-label" for="suAdvice">监理公司审查意见</label>
								        <div>
								            <textarea class="form-control input-sm  field-editable addClear allText suCes suJgj" value="" id="suAdvice" name="suAdvice" data-parsley-maxlength="200"></textarea>
								        </div>
								    </div>
								    <div class="form-group col-md-12 col-sm-12 clearboth">
										<label class="control-label signature-tool" for="suName">项目监理机构</label>
										<div>
											<input type="text" class="form-control input-sm  field-not-editable" id="suName" name="suName" value="项目监理机构"/>
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12 allSign suCse clearboth">
										<label class="control-label signature-tool" for="suCes">总监理工程师</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="suCes" name="suCes" value="" class="sign-data-input">
											<input type="hidden" class="signPost" id="suCes_postType" name="suCes_postType" value="${sucesPost }" >
											<img class="suCes" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								     <div class="form-group col-md-6 col-sm-12 rework hidden selectSignDate clearboth">
								        <label class="control-label" for="">审查日期</label>
								        <div>
								            <input type="text" class="form-control input-sm datepicker-default addClear field-editable allText suCse" value="" id="suCesReviewDate" name="suCesReviewDate" />
								        </div>
								    </div>
								    <div class="form-group col-md-12 col-sm-12 rework clearboth">
								        <label class="control-label" for="custAdvice">建设单位审批意见</label>
								        <div>
								            <textarea class="form-control input-sm  field-editable addClear allText builder" value="" id="custAdvice" name="custAdvice" data-parsley-maxlength="200"></textarea>
								        </div>
								    </div>
								    <div class="form-group col-md-12 col-sm-12 clearboth">
										<label class="control-label signature-tool" for="corpName">建设单位</label>
										<div>
											<input type="text" class="form-control input-sm  field-not-editable" id="corpName" name="corpName" />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12 allSign builder clearboth">
										<label class="control-label signature-tool" for="custRepresent">建设单位代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="custRepresent" name="custRepresent" value="" class="sign-data-input">
											<input type="hidden"class="signPost"  id="custRepresent_postType" name="custRepresent_postType" value="${fieldsRepresentPost }" >
											<img class="custRepresent" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								     <div class="form-group col-md-6 col-sm-12 rework hidden selectSignDate clearboth">
								        <label class="control-label" for="">审批日期</label>
								        <div>
								            <input type="text" class="form-control input-sm addClear datepicker-default field-editable allText builder" value="" id="custAuditDate" name="custAuditDate" />
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
    App.setPageTitle('复工报审- 工程施工管理系统 ');
    $('#shutDownAprovalForm').toggleEditState(false);
    $("#shutDownAprovalForm").styleFit();
    $(".editbtn").addClass("hidden");
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });
//     $.getScript('assets/plugins/jedate/jedate.js').done(function() {
//     	jeDate({
//     		dateCell:".datetime-default",
//     	    format:"YYYY-MM-DD hh:mm:ss",
//     	    isTime:true,
//     	    festival: true
//     	})
//     });
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	console.info(projJson);
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#cuName').val(projJson.cuName);					   //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    	$('#corpName').val(projJson.corpName);				   //建设公司
    }
    
  //签字加载方式
    $("#signBtn_1, #signBtn_2, #signBtn_2").handleSignature(); 
    
     $.getScript('projectjs/constructmanage/shutdown-aproval.js?v='+Math.random()).done(function () {
       	shutdownAproval.init();
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
   	 
   	 //签字区保存功能
     $(".saveBtn").on("click",function(){
     	$("#shutDownAprovalForm").cformSave('shutdownAprovalTable',saveBack,false,false);
     });
     
     function saveBack(data){
    	 var content = "数据保存成功！";
    	 	if(data === "false"){
    	 		content = "数据保存失败！";
    	 	}else if(data === "already"){
    	   		content = "此信息已被修改，请刷新页面！";
    	   	}else{
         	$('.editbtn').addClass("hidden");
         	$('#shutDownAprovalForm').toggleEditState(false);
     		showReport();
    	 	}
    	 	var myoptions = {
    	         	title: "提示信息",
    	         	content: content,
    	         	accept: sureClose,
    	         	chide: true,
    	         	icon: "fa-check-circle"
    	     }
    	     $("body").cgetPopup(myoptions);
    	 	
     }
   
   	 var showReport = function(data){
   		var json = {};
   		json= trSData.shutdownAprovalTable.json;
   		 //获取table的选种行信息，传递到报表
   		if(json){
   			projNo = json.projNo;
   			projName =json.projName;
   			cuName = json.cuName;
   			suName = json.suName;
   			corpName = json.corpName;
   			sdrNo = json.sdrNo;
   			sdrProcess = json.sdrProcess;
   			cuManagerDept = json.cuManagerDept;
   			
   			sdaId = json.sdaId;
   			reworkDate = json.reworkDate;
   		}else{
   			projName = $("#projName").val();
   			cuName = $("#cuName").val();
   			suName = $("#suName").val();
   			corpName = $("#corpName").val();
   			projNo = $("#projNo").val();
   			sdrNo = $("#sdrNo").val();
   			sdrProcess = $("#sdrProcess").val();
   			sdaId = $("#sdaId").val();
   			cuManagerDept = $("#cuManagerDept").val();
   			reworkDate = $("#reworkDate").val();
   			suCesReviewDate = $("#suCesReviewDate").val();
   		}
   		console.info("corpName=============="+corpName);
	 //以下处理为解决中文乱码
	    projName=encodeURIComponent(cjkEncode(projName));
    	suName = encodeURIComponent(cjkEncode(suName));
    	cuName = encodeURIComponent(cjkEncode(cuName));
    	corpName = encodeURIComponent(cjkEncode(corpName));
    	cuManager = encodeURIComponent(cjkEncode(cuManager));
    	sdrProcess = encodeURIComponent(cjkEncode(sdrProcess));
    	cuManagerDept = encodeURIComponent(cjkEncode(cuManagerDept));
    	
   		src = cptPath + "/ReportServer?reportlet=constructmanage/shutdownAproval.cpt";
   		src = src + "&projNo=" + projNo + "&projName="+projName+"&cuName="+cuName+"&cuManagerDept="+cuManagerDept+"&suName="+suName+"&corpName="+corpName+"&sdrNo="+sdrNo+"&sdrProcess="+sdrProcess;
   		//src = src + "&reworkDate="+reworkDate+"&suCesReviewDate="+suCesReviewDate+"&suAdvice="+suAdvice+"&cuApprovalDate="+cuApprovalDate+"&custAdvice="+custAdvice+"&custAuditDate="+custAuditDate;
   		src = src + "&sdaId="+sdaId + "&imgUrl="+$(".imgUrl").val();
   		console.info(src+"==========src");
   		$("#mainFrm").attr("src",src);
   	 };
   	 
   	if(_inApk){
   		$("#shutdownAprovalList").text("列表区");
 		$("#shutdownAprovalInfo").text("记录区");
   	 }else{
  		$("#shutdownAprovalList").text("复工报审列表");
  		$("#shutdownAprovalInfo").text("复工报审信息");
   	 }
   	 
   	 var sureClose=function(){
   		var cwId=$("#sdaId").val();
		$.ajax({
			type:'post',
			url:'shutdownAproval/saveSignNotice',
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
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->