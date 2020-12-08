<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse tabs-mixin">
                <div class="panel-heading">
                   	<div class="panel-heading-btn">
                       	<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                       	<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                       	<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                   	</div>
                   	<h4 class="panel-title">碰口记录</h4>
                </div>
                <div class="panel-body">
                    <div class="toolBtn f-r p-b-10 ">
                    	<a href="javascript:;" class="btn btn-confirm btn-sm checkRole saveBtn" >保存</a>
  	                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole updataBtn" >修改</a>
  	                    <a href="javascript:;" class="btn  btn-primary btn-sm m-l-5 hidden checkRole get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>
                    </div>
                    <div class="clearboth form-box">
                    	<form class="form-horizontal" id="touchRecordForm" action="" name="touchRecordForm">
                    		<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
                    	   	<input type="hidden" name ="projId" id="projId" value=""/>
						  	<input type="hidden" name ="croId"  id="croId"/>  <!-- 碰口记录单id -->
						   	<!-- <input type="hidden" name ="crId"  id="crId"/>     碰口记录id -->
							<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">工程名称</label>
								<div>
								<input type="text" class="form-control input-sm field-not-editable" value="" id="projName" name="projName"  />
								</div>
						 	</div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">工程地点</label>
								<div>
								<input type="text" class="form-control input-sm field-not-editable" value="" id="projAddr" name="projAddr"  />
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">分包单位</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable" value="" id="constructionUnit" name="constructionUnit"  />
								</div>
							</div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">项目经理</label>
								<div>
									<input type="text" class="form-control input-sm field-editable" value="" id="projManager" name="projManager"/>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">监理单位</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable" value="" id="suName" name="suName"  />
								</div>
						  	</div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label " for="">现场监理</label>
								<div>
									<input type="text" class="form-control input-sm field-editable" value="" id="fieldJgj" name="fieldJgj"/>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">碰口地点</label>
								<div>
									<input type="text" class="form-control input-sm field-editable" value="" id="touchAddr" name="touchAddr"  />
								</div>
						  	</div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">碰口日期</label>
								<div>
									<input type="text" class="form-control input-sm datepicker-default field-editable" value="" id="tpDate" name="tpDate" data-parsley-required="true"/>
								</div>
						    </div>
						    
						    <!-- <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">指令下达</label>
								<div>
									<input type="text" class="form-control input-sm datepicker-default field-editable" value="" id="instructionTime" name="instructionTime"  />
								</div>
						    </div> -->
						   
						    <div class="form-group col-md-6 col-sm-12 hidden mobileDiv">
								<label class="control-label">经度</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable" value="" id="longitude" name="longitude" />
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12 hidden mobileDiv">
								<label class="control-label">纬度</label>
								<div>
									<input type="text" class="form-control input-sm field-not-editable" value="" id="latitude" name="latitude" />
								</div>
							</div>
						</form>
                    </div>
                    
					<div class="clearboth form-box m-t-5">
						<form id="conContentForm">
							<table id="conContentTable" class="table table-striped table-bordered nowrap" width="100%">
								<thead>
									<tr>
										<th width="350px">施工单位碰口前确认内容</th>
										<th width="50px">是否合格</th>
										<th></th>
									</tr>
								</thead>
							</table>
						</form>
					</div>
					
					<form id="conForm" class="form-horizontal">
					
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label" for="">焊工证号1</label>
							<div>
								<input type="text" class="form-control input-sm field-editable" value="" id="welderId1" name="welderId1" form="touchRecordForm" />
							</div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label signature-tool" for="">焊工1</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="welder1" name="welder1" value="" form="touchRecordForm">
								<input type="hidden" id="welder1_postType" name="welder1_postType" value="${welder }" form="touchRecordForm">
								<img class="" alt="" src="images/sign-2.png" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
					    </div>
					    
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label" for="">焊工证号2</label>
							<div>
								<input type="text" class="form-control input-sm field-editable" value="" id="welderId2" name="welderId2" form="touchRecordForm" />
							</div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label signature-tool" for="">焊工2</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="welder2" name="welder2" value="" form="touchRecordForm">
								<input type="hidden" id="welder2_postType" name="welder2_postType" value="${welder }" form="touchRecordForm">
								<img class="" alt="" src="images/sign-2.png" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
					    </div>
					    
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label" for="">焊工证号3</label>
							<div>
								<input type="text" class="form-control input-sm field-editable" value="" id="welderId3" name="welderId3" form="touchRecordForm" />
							</div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label signature-tool" for="">焊工3</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="welder3" name="welder3" value="" form="touchRecordForm">
								<input type="hidden" id="welder3_postType" name="welder3_postType" value="${welder }" form="touchRecordForm">
								<img class="" alt="" src="images/sign-2.png" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
					    </div>
						    
						<div class="form-group col-md-6 col-sm-12">
							<label class="control-label signature-tool"  for="">项目经理</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="fieldPrincipal" name="fieldPrincipal" value="" form="touchRecordForm">
								<input type="hidden" id="fieldPrincipal_postType" name="fieldPrincipal_postType" value="${fieldPrincipal }" form="touchRecordForm">
								<img class="" alt="" src="images/sign-1.png" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
					    </div>
					    
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label signature-tool" for="">现场监理</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="fieldJgjC" name="fieldJgjC" form="touchRecordForm">
								<input type="hidden" id="fieldJgjC_postType" name="fieldJgjC_postType" value="${fieldJgjC}" form="touchRecordForm">
								<img class="" alt="" src="images/sign-3.png" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
					    </div>
					    
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label signature-tool" for="">甲方代表</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="constructionCustodian" name="constructionCustodian" form="touchRecordForm">
								<input type="hidden" id="constructionCustodian_postType" name="constructionCustodian_postType" form="touchRecordForm">
								<img class="" alt="" src="images/sign-3.png" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
					    </div>
					</form>
					
					<div class="clearboth form-box m-t-5">
						<form id="noContentForm">
							<table id="noContentTable"
								class="table table-striped table-bordered nowrap" width="100%">
								<thead>
									<tr>
										<th width="350px">天然气公司确认内容</th>
										<th width="50px">是否合格</th>
										<th></th>
									</tr>
								</thead>
							</table>
						</form>
					</div>
					
					<form id="noForm" class="form-horizontal">
						<div class="form-group col-md-6 col-sm-12">
							<label class="control-label signature-tool" for="">天然气公司</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="ongcDeputy" name="ongcDeputy" value="" form="touchRecordForm">
								<input type="hidden" id="ongcDeputy_postType" name="ongcDeputy_postType" value="${ongcDeputy }" form="touchRecordForm">
								<img class="" alt="" src="images/sign-3.png" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
						 	<label class="control-label">指令下达时间</label>
							<div>
							     <input type="text" class="form-control input-sm field-editable" id="instructionTime" name="instructTimeDes" form="touchRecordForm"/>
							</div>
						</div>
					</form>
					
					<div class="clearboth form-box3 m-t-5">
						<form id="suContentForm">
							<table id="suContentTable" class="table table-striped table-bordered nowrap" width="100%">
								<thead>
									<tr>
										<th width="350px">监理单位碰口完成后确认内容</th>
										<th width="50px">是否合格</th>
										<th></th>
									</tr>
								</thead>
							</table>
						</form>
					</div>
					
					<form id="suForm" class="form-horizontal">
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label signature-tool"  for="">项目经理</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="fieldPrincipal1" name="fieldPrincipal1" value="" form="touchRecordForm">
								<input type="hidden" id="fieldPrincipal1_postType" name="fieldPrincipal_postType" value="${fieldPrincipal }" form="touchRecordForm">
								<img class="" alt="" src="images/sign-1.png" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
					    </div>
					    
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label signature-tool" for="">甲方代表</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="constructionCustodian1" name="constructionCustodian1" form="touchRecordForm">
								<input type="hidden" id="constructionCustodian1_postType" name="constructionCustodian_postType" form="touchRecordForm">
								<img class="" alt="" src="images/sign-3.png" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
					    </div>
					    
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label signature-tool" for="">现场监理</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="fieldJgjC1" name="fieldJgjC1" form="touchRecordForm">
								<input type="hidden" id="fieldJgjC1_postType" name="fieldJgjC_postType" value="${fieldJgjC}" form="touchRecordForm">
								<img class="" alt="" src="images/sign-3.png" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
					    </div>
					    
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label signature-tool" for="">天然气公司</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="ongcDeputy1" name="ongcDeputy1" value="" form="touchRecordForm">
								<input type="hidden" id="ongcDeputy1_postType" name="ongcDeputy_postType" value="${ongcDeputy }" form="touchRecordForm">
								<img class="" alt="" src="images/sign-3.png" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
					    </div>
					    
					    <div class="form-group col-md-6 col-sm-12">
							<label class="control-label" for="">碰口完成时间</label>
							<div>
								<input type="text" class="form-control input-sm field-editable" value="" id="touchEndDate" name="touchEndDes" form="touchRecordForm" />
							</div>
					    </div>
					    
					</form>
					
				</div>
        	</div>
        </div>
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
	                 	<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:0; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('碰口记录 - 工程项目管理系统');
    
  //是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
	
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
    
	    $.getScript('projectjs/constructmanage/touch-record.js').done(function () {
	    	connectRecord.init();
	    });
	    
	    $("#touchRecordForm,#conForm,#noForm,#suForm").toggleEditState().styleFit();
	
	    //日期datepicker
	    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
	    	$('.datepicker-default').datepicker({
	            todayHighlight: true
	        });
	    });
	    $.getScript('assets/plugins/bootstrap-daterangepicker/moment.js').done(function() {
		    $.getScript('assets/plugins/bootstrap-eonasdan-datetimepicker/src/js/bootstrap-datetimepicker.js').done(function() {
		    	 $('#instructionTime').datetimepicker({
		    		 format: 'YYYY-MM-DD HH:mm'
		    	 });
		    	 $('#touchEndDate').datetimepicker({
		    		 format: 'YYYY-MM-DD HH:mm'
		    	 });
		    	 
		    });
	    });
	    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
	    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6");
	    	signatures.handleSignature();        	    	
	    });
	    
	    <%
	    String path = request.getContextPath();
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	    %>  
	    
	    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
	    
	    //加载打印预览
	    var showReport = function(){
	    	projId = encodeURIComponent(cjkEncode($("#projId").val())),                    //工程名称
	    	croId = encodeURIComponent(cjkEncode($("#croId").val())),                    //工程名称
	    	imgUrl = encodeURIComponent(cjkEncode($(".imgUrl").val())),                    //工程名称
	    	projName = encodeURIComponent(cjkEncode($("#projName").val())),                    //工程名称
	    	projAddr = encodeURIComponent(cjkEncode($("#projAddr").val())),                    //工程地点
	    	touchAddr = encodeURIComponent(cjkEncode($("#touchAddr").val())),                  //碰口地点
	    	tpDate = encodeURIComponent(cjkEncode($("#tpDate").val())),                        //碰口日期
	    	constructionUnit = encodeURIComponent(cjkEncode($("#constructionUnit").val())),    //施工单位
	    	suName = encodeURIComponent(cjkEncode($("#suName").val())),                        //监理单位
	    	projManager = encodeURIComponent(cjkEncode($("#projManager").val())),              //项目经理
	    	fieldJgj = encodeURIComponent(cjkEncode($("#fieldJgj").val())),                    //现场监理
	    	fieldPrincipal = encodeURIComponent(cjkEncode($("#fieldPrincipal").val())),        //现场负责人
	    	constructionCustodian = encodeURIComponent(cjkEncode($("#constructionCustodian").val()));    //施工部监护人员	    	
	    	fieldJgjC = encodeURIComponent(cjkEncode($("#fieldJgjC").val())),					//现场监理签字	    	
	    	ongcDeputy = encodeURIComponent(cjkEncode($("#ongcDeputy").val())),					//天然气分公司签字
	    	touchEndDate = encodeURIComponent(cjkEncode($("#touchEndDate").val())),			    //碰口完成 时间
	    	instructionTime = encodeURIComponent(cjkEncode($("#instructionTime").val())),	    //指令下达时间
	    	welderId1 = encodeURIComponent(cjkEncode($("#welderId1").val())),	    //指令下达时间
	    	welderId2 = encodeURIComponent(cjkEncode($("#welderId2").val())),	    //指令下达时间
	    	welderId3 = encodeURIComponent(cjkEncode($("#welderId3").val())),	    //指令下达时间
	    	
	    	src="<%=basePath%>/ReportServer?reportlet=constructmanage/touchRecord.cpt&projName="+projName+"&projAddr="+projAddr+"&touchAddr="+touchAddr+"&tpDate="+tpDate+"&constructionUnit="+constructionUnit+"&suName="+suName+"&welderId1="+welderId1+"&welderId2="+welderId2+"&welderId3="+welderId3+"&welderId3="+welderId3+"&projId="+projId+"&projManager="+projManager+"&fieldJgj="+fieldJgj+"&touchEndDate="+touchEndDate;
	    	src = src + "&projId=" + projId + "&croId="+croId + "&imgUrl="+imgUrl + "&instructionTime="+instructionTime;
	    	    	$("#mainFrm").attr("src",src); 
	    };
	    //打印预览窗口缩放调整
	    if($(".iframe-report").length > 0){
	    	var fr = $(".iframe-report");
	    	for(var i=0; i<fr.length; i++){
	    		fr.eq(i).rescaleReportPanel();
	    	}
	    }
	
	  //保存
		$(".saveBtn").off("click").on("click",function(){
	        //开启表单验证
	        if ($("#touchRecordForm").parsley().isValid()) {
	       	var tabledata = $("#conContentForm,#noContentForm,#suContentForm").getDTFormData();
	       	console.info(JSON.stringify(tabledata));
	       	var viewdata = $("#touchRecordForm").serializeJson();
	       	console.info(JSON.stringify(viewdata));
	       	//
	       	var resultData = [];
	       	for(var i=0;i<tabledata.length;i++){
	       		var data = tabledata[i];
	       		if(data!== undefined){
	       			data.croId = $("#croId").val();
	       			data.projId = $("#projId").val();
	       			data.welderId1=$("#welderId1").val();
	       			data.welder1=$("#welder1").val();
	       			data.welderId2=$("#welderId2").val();
	       			data.welder2=$("#welder2").val();
	       			data.welderId3=$("#welderId3").val();
	       			data.welder3=$("#welder3").val();
	       			resultData.push(data);
	       		}
	       	}
	       	console.info(JSON.stringify(resultData));
	       	if(resultData.length<1){
	       		alertInfo("请确认碰口内容项！");
	       		return false;
	       	}
	       	viewdata.connectRecords = resultData;
	       	//console.log(JSON.stringify(viewdata));
	       	$.ajax({
	               type: 'POST',
	               url: 'touchRecord/touchRecordSave',
	               contentType: "application/json;charset=UTF-8",
	               data: JSON.stringify(viewdata),
	               success: function (data) {
	               	var content = "数据保存成功！";
	               	if(data === "false"){
	               		content = "数据保存失败！";
	               	}else{
	               		var myoptions = {
	                           	title: "提示信息",
	                           	content: content,
	                           	accept: popClose,
	                           	chide: true,
	                           	icon: "fa-check-circle"
	                       }
	                       $("body").cgetPopup(myoptions);
	               		touchRecordDetail();
	//                		showReport();
	               	}
	               	
	               },
	               error: function (jqXHR, textStatus, errorThrown) {
	                   console.warn("记录保存失败！");
	               }
	           });
	    	//如果通过验证, 则移除验证UI
	       	$("#touchRecordForm").parsley().reset();
	       }else{
	       	//如果未通过验证, 则加载验证UI
	       	$("#touchRecordForm").parsley().validate();
	       }
	        $("#touchRecordForm,#noContentForm,#conContentForm,#suContentForm,#conForm,#noForm,#suForm").toggleEditState(false);
			   $(".saveBtn").addClass("hidden");
			   $(".updataBtn").removeClass("hidden");
		});
	//  点击修改按钮
		$(".updataBtn").off("click").on("click",function(){
			$(".updataBtn").addClass("hidden");
			$(".saveBtn").removeClass("hidden");
			$("#touchRecordForm,#noContentForm,#conContentForm,#suContentForm,#conForm,#noForm,#suForm").toggleEditState(true);
			
			
			//施工或竣工中
        	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#conForm,#noForm,#suForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#conForm,#noForm,#suForm").find(".sign-data-input").toggleSign(false);
        	}
		});
    }
  
</script>
<!-- ================== END PAGE LEVEL JS ================== -->