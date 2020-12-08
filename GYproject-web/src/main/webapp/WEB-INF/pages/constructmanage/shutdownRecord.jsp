<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
						<li class="active"><a href="#shutdown_record" data-toggle="tab"id ="shutdownRecordList">停复工列表</a></li>
						<li class=""><a href="#shutdown_info" data-toggle="tab" id="shutdownInfo">停复工信息</a></li>
					</ul>
                </div>
                <div class="panel-body " id="box">
                	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                	<div class="tab-content">
	                	<div class="tab-pane fade active in btn-top active" id="shutdown_record" >
	                		<table class="table table-hover table-striped table-bordered nowrap" id="shutdownRecordTable" width="100%">
	                			<thead>
	                			<tr>
	                				<th></th>
	                				<th>停工编号</th>
	                				<th>停复工部位(工序)</th>
	                				<th>发起日期</th>
	                				<th>停复工类型</th>  
	                				<th>状态</th>
	                			</tr>
	                			</thead>
	                		</table>
	                	</div>
	                	<div class="tab-pane fade in btn-top" id="shutdown_info">
	                		<div class=" f-r p-b-10 editbtn">
	    					 	<a href="javascript:;" class="btn btn-default btn-confirm btn-sm m-l-5 checkRole saveBtn" >保存</a>
	        				 	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 checkRole giveUpSave">放弃</a>
						 	</div>
						 	 <div class="clearboth form-box p-b-10">
						 	 	<input type="hidden" class="imgUrl" value="${imgUrl }"/>
						 	 	<input type="hidden" id = "loginPost"  name = "loginPost" value="${loginPost }"/>
						 	 	<form class="form-horizontal" id="shutDownForm" action="shutdownRecord/saveSign">
						 	 	<input type="hidden" value="" id="sdrId" name="sdrId" class="addClear"/>
						 	 	<input type="hidden" value="" id="projId" name="projId"/>
						 	 		
			   						<div class="form-group col-md-6  col-xs-12">
								        <label class="control-label" for="projNo">工程编号</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"   id="projNo" name="projNo" />
					        			</div>
			   						</div>
			   						
							 		<div class="form-group col-md-6 col-xs-12">
								        <label class="control-label" for="projName">工程名称</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable" id="projName" name="projName" />
					        			</div>
			   						</div>
			   						<div class="form-group col-md-6 col-xs-12 sdrType">
								        <label class="control-label" for="sdrType">停复工类型</label>
					        			<div>
					        				<select class="form-control input-sm field-editable" id="sdrType" name="sdrType" data-parsley-required="true">
					        					 <c:forEach var="sdtEnum" items="${shutdownTypeEnum }" >
													 <option value="${sdtEnum.value}">${sdtEnum.message}</option>
												</c:forEach> 
						        				<%--
						        				<option value="0">停工令</option>
						        				<option value="1">复工令</option>
						        				--%>
					        				</select>
					        			</div>
			   						</div>
						 	 		<div class="form-group col-md-6 col-xs-12 hidden reWorkDate">
								        <label class="control-label" for="sdrNo">停工编号</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"   id="sdrNo" name="sdrNo" />
					        			</div>
			   						</div>
							 		<div class="form-group col-xs-12 clearboth">
								        <label class="control-label" for="corpName">建设单位</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable" id="corpName" name="corpName" />
					        			</div>
			   						</div>
							 		<div class="form-group col-xs-12 clearboth">
								        <label class="control-label" for="suName">监理单位</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable" id="suName" name="suName" />
					        			</div>
			   						</div>
							 		
			   						<div class="form-group col-md-12 col-sm-12 clearboth">
								        <label class="control-label" for="cuName">施工单位</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"   id="cuName" name="cuName"  />
					        			</div>
			   						</div>
								    <div class="form-group col-md-12 col-sm-12 cuManagerDept clearboth">
								        <label class="control-label" for="cuManagerDept">施工项目经理部</label>
					        			<div>
											<input type="text" class="form-control input-sm field-editable addClear"   id="cuManagerDept" name="cuManagerDept" data-parsley-maxlength="50" data-parsley-required="true"/>
										</div>
			   						</div>
			   						<!-- 复工不显示 -->
			   						<div class="form-group col-md-12 col-sm-12 shutdownfield clearboth" >
								        <label class="control-label" for="sdrReason" id="">停工原因</label>
					        			<div>
					        				<textarea class="form-control input-sm  field-editable addClear"  id="sdrReason" name="sdrReason" data-parsley-maxlength="200" data-parsley-required="true"></textarea>
					        			</div>
			   						</div>
			   						<div class="form-group col-md-6 col-sm-12 shutdownfield clearboth">
								        <label class="control-label" for="sdrProcess" >停工部位(工序)</label>
					        			<div>
					        				<input type="text" class="form-control input-sm  field-editable addClear"  id="sdrProcess" name="sdrProcess" data-parsley-maxlength="50" data-parsley-required="true"/>
					        			</div>
			   						</div>
			   						<div class="form-group col-md-6 col-sm-12 shutdownfield clearboth">
								        <label class="control-label"  class="">停工日期</label>
					        			<div>
					        				<input type="text" class="form-control input-sm  field-editable datetime-default addClear"  id="sdrDate" name="sdrDate" data-parsley-required="true"/>
					        			</div>
			   						</div>
			   						
								    <div class="form-group col-md-12 col-sm-12 shutdownfield clearboth" >
								        <label class="control-label" for="sdrRequire">要求</label>
								        <div>
								        	<textarea class="form-control input-sm  field-editable addClear"  id="sdrRequire" name="sdrRequire" data-parsley-maxlength="200"></textarea>
								        </div>
								    </div>
								   
								     <div class="form-group col-md-12 col-sm-12 hidden reWorkDate clearboth">
								        <label class="control-label">复工日期</label>
					        			<div>
					        				<input type="text" class="form-control input-sm datetime-default  field-not-editable"  id="reWorkDate" name="reWorkDate" data-parsley-required="true"/>
					        			</div>
			   						</div>
			   						
								    <div class="form-group col-md-6 col-sm-12 sign clearboth">
										<label class="control-label signature-tool sign-require" for="suCes">总监理工程师</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="suCes" name="suCes" value="" class="sign-data-input">
											<input type="hidden" id="suCes_postType" name="suCes_postType" value="${sucesPost}" >
											<img class="suCes" alt="" style="height: 30px">
											<span class="clear-sign "><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    
								     <div class="form-group col-md-6 col-sm-12 noticeDate hidden selectSignDate clearboth">
								        <label class="control-label">发起日期</label>
								        <div>
								            <input type="text" class="form-control input-sm datepicker-default field-editable addClear" value="" id="createDate" name="createDate" data-parsley-required="true"/>
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
    App.setPageTitle('停复工- 工程施工管理系统 ');
    $('#shutDownForm').toggleEditState(false);
    $("#shutDownForm").styleFit();
    //$('.update-show').addClass('hidden');
    $(".editbtn").addClass("hidden");
    
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
    	$('#corpName').val(projJson.corpName);			      //建设单位
    }
    
  //签字加载方式
    $("#signBtn_1, #signBtn_2").handleSignature(); 
  
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    }); 
    $.getScript('assets/plugins/jedate/jedate.js').done(function() {
    	jeDate({
    		dateCell:".datetime-default",
    	    format:"YYYY-MM-DD hh:mm:ss",
    	    isTime:true,
    	    festival: true
    	})
    });

     $.getScript('projectjs/constructmanage/shutdown-record.js?v='+Math.random()).done(function () {
       	shutdownRecord.init();
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
   	 
   	 $("#sdrType").val("1");
   	 $("#sdrType").on("change",function(){
   		changeSdrType(this);
   		showReport($(this).val());
   	 });
   	 
   	 var changeSdrType = function(o){
   		 if($(o).val()=='2'){
   			
   			 showShutdownData();
   		 }else{
   			 
   			clearRecordData();
   		 }
   		
   	 }
   	 
   	 /**增加复工令，显示选中的停工令信息*/
   var showShutdownData = function(data){
	   $(".reWorkDate").removeClass("hidden");
	   $(".cuManagerDept").toggleEditState(false);
	   $(".shutdownfield").toggleEditState(false);
	   console.info(recordData);
	   $("#sdrNo").val(recordData.sdrNo);
	   $("#sdrReason").val(recordData.sdrReason);
	   $("#sdrRequire").val(recordData.sdrRequire);
	   $("#sdrProcess").val(recordData.sdrProcess);
	   $("#sdrRequire").text(recordData.sdrRequire);
	   $("#sdrDate").val(recordData.sdrDate);
	   $("#reworkDate").val(recordData.reworkDate);
	   $("#cuManagerDept").val(recordData.cuManagerDept);
   }
   	 /**增加停工令，清除信息*/
   	 var clearRecordData = function(){
   		$(".reWorkDate").addClass("hidden");
		$(".cuManagerDept").toggleEditState(true);
		$('#shutDownForm').toggleEditState(true);
   		 //清空停工编号
   		$("#sdrNo").val('');
   		 //清空停工信息
   		 $(".addClear").val('');
   	 }
    	
   	 //签字区保存功能
     $(".saveBtn").on("click",function(){
     	$("#shutDownForm").cformSave("shutdownRecordTable",saveBack,false);
     	/* var tid = "shutdownRecordTable";
     	var flag=false;
     	 var t = $("#shutDownForm"), url = t.attr("action");
         
         //请求路径为必填参数
         if (!url) {
             console.error('表单未设置action属性');
             return false;
         }
         
         //验证必签签字是否已签
         var signtools = t.find(".signature-tool.sign-require"),
         stl = signtools.length,
         sBlank = 0;
         for(var i=0; i<stl; i++){
         	var tstool = signtools.eq(i).next("div").find("a.btn-white"),
         	tsinput = tstool.siblings(".sign-data-input");
         	if(!tsinput.val() || tsinput.val().length < 312){
         		tstool.addClass("required-sign");
         		sBlank++;
         	}
         }
         if(sBlank){
         	if(_inApk){
         		navigator.notification.alert('请完成签字！', null, '提示信息', '确定');
         	}else{
 	        	$("body").cgetPopup({
 	            	title: "提示信息",
 	            	content: "请完成签字!",
 	            	accept: popClose,
 	            	chide: true,
 	            	icon: "fa-warning",
 	            	newpop: 'new'
 	            });
         	}
         	return false;
         }
         
         //开启表单验证
         if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
             //防止多次提交
             if(response){
                 response.abort();
             }
             //表单序列化获取json字符串
             var data = t.serializeJsonString();
             var response = $.ajax({
                 url: url,
                 type: "POST",
                 timeout : 5000,
                 contentType: "application/json;charset=UTF-8",
                 data: data,
                 success: function (data) {
                     if (data !== "false") {
                    	 if(data ==  "exist"){
                    		$("body").cgetPopup({
	 		                        	title: "提示信息",
	 		                        	content: "该工程的此工序的停工令已存在!",
	 		                        	accept: popClose,
	 		                        	chide: true,
	 		                        	icon: "fa-check-circle",
	 		                        	newpop: 'new'
	 		                        });
                    	 }else{
	                         var table = $('#' + tid);
	                         if(table.length > 0){
	                         	var dtc = table.DataTable();
	                             if (rowLoop[tid] === '' || rowLoop[tid] === undefined) {
	                                 dtc.ajax.reload(function () {
	                                     if(flag !== false){
	                                 		if(flag === true || flag === "") flag = 0;
	                                     	table.selectRow(flag);
	                                     }
	                                 });
	                             }
	                         }
	                         $("body").cgetPopup({
		                        	title: "提示信息",
		                        	content: "数据保存成功!",
		                        	accept: popClose,
		                        	chide: true,
		                        	icon: "fa-check-circle",
		                        	newpop: 'new'
		                        });
                    	 }
                     } else {
                    	 $("body").cgetPopup({
	                        	title: "提示信息",
	                        	content: "数据保存失败, 请重试! <br>" + data,
	                        	accept: popClose,
	                        	chide: true,
	                        	icon: "fa-exclamation-circle",
		                        newpop: 'new'
	                        });
                         	
                     }
                     if(saveBack !== "" && saveBack !== undefined){
                         saveBack(data);
                     }
                 },
                 error: function (jqXHR, textStatus, errorThrown) {
                     //判断超时
                     if(textStatus === 'timeout'){
                         response.abort();
                         saveBack(textStatus);
                     }
                     printXHRError("表单(" + t.attr("id") + ") ajax保存查询失败", jqXHR, textStatus, errorThrown);
                 }
             });
             //如果通过验证, 则移除验证UI
             t.parsley().validate();
         } else {
             //如果未通过验证, 则加载验证UI
             t.parsley().validate();
         }; */
     });
     
     function saveBack(data){
     	$('.editbtn').addClass("hidden");
     	$('#shutDownForm').toggleEditState(false);
 		$('#sdrId').val(data);
 		
 		/* if(_inApk && $(".attach-images-list").find(".new-image").length){
 			preImagesUpload($(".attach-images-list .uploadBtn"), projId, projNo, getStepId(), pcId);
 		}; */
         
 		showReport();
     	
     }
     //右屏导入帆软报表
	  var showReport = function(data){
   		 var json=trSData.shutdownRecordTable.json;
   		 if(json){
   			projId = json.projId;
   			projName=json.projName;
       	    suName =json.suName;
       	    projNo = json.projNo;
       	    cuName =json.cuName;
            cuManagerDept = json.cuManagerDept;
            sdrReason = json.sdrReason;
            sdrProcess = json.sdrProcess;
            sdrDate = json.sdrDate;
            sdrRequire =json.sdrRequire;
            reWorkDate = json.reWorkDate;
            createDate =json.createDate;
            sdrNo = json.sdrNo;
   		 }else{
   		//签字区保存后不刷新页面,cpt文件中数据从签字区获取
   			projId = $('#projId').val();
         	projName=$('#projName').val();
         	projNo = $('#projNo').val();
     	    suName = $("#suName").val();
     	   // process = $("#process").val();
            cuName = $("#cuName").val();
           cuManagerDept = $("#cuManagerDept").val();
            sdrReason = $("#sdrReason").val();
            sdrProcess=$("#sdrProcess").val();
            sdrDate = $("#sdrDate").val();
            sdrRequire= $("#sdrRequire").val();
            reWorkDate = $("#reWorkDate").val();
            createDate = $("#createDate").val();
            sdrNo = $("#sdrNo").val();
   		 }
   		 var  sdrId = $("#sdrId").val();
   		 var  sdrType = $("#sdrType").val();
         
   		//以下处理为解决中文乱码
 	    projName=encodeURIComponent(cjkEncode(projName));
     	suName = encodeURIComponent(cjkEncode(suName));
     	cuName = encodeURIComponent(cjkEncode(cuName));
     	//停复工类型选择框改变时
     	if(data){
     		sdrType = data;
     		reWorkDate = $("#reWorkDate").val();
     		sdrId ='' ;
     	}
	 	if(sdrType=='1'){
	 		src = cptPath + "/ReportServer?reportlet=constructmanage/shutdownOrder.cpt";
	 	}else{
	 		src = cptPath + "/ReportServer?reportlet=constructmanage/reWorkOrder.cpt";
	 	}
	 	src = src + "&projId=" +projId+ "&projName=" + projName + "&projNo=" + projNo + "&suName=" + suName + "&cuName="+cuName;
	 	src = src + "&sdrId=" + sdrId + "&imgUrl=" + $(".imgUrl").val();
	 //	src = cptPath + "/ReportServer?reportlet=constructmanage/reWorkOrder.cpt";
  		$("#mainFrm").attr("src",src); 
   			
} 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->