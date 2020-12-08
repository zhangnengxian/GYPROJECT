<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- purge.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
                    	<li class="active"><a href="#default-tab-1" id="listTab" data-toggle="tab">列表区</a></li>
		                <li class=""><a href="#default-tab-2" id="signTab"  data-toggle="tab">报验区</a></li>
		                <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" id="">
               		<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
               			   <input  type="hidden" class="addClear" id='pcIdNew'>
               			   <input type="hidden" id="custName" name="custName" >
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="threadingPipeTable" width="100%">
	                			<thead>
		                			<tr>
		                				<th></th>
		                				<th>报验日期</th>
			                			<th>施工工序</th>
			                			<th>查验结果</th>
		                			</tr>
	                			</thead>
	                		</table>
	                	</div>
               			<div class="tab-pane fade in btn-top" id="default-tab-2">
               				<div class=" f-r p-b-15 editBtn" >
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole saveBtn" >保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupBtn">放弃</a>
						    </div>
						    <div class="clearboth form-box">
						    	<input type="hidden" class="imgUrl" value="${path}"/>
						    	<form class="form-horizontal" id="threadingPipeForm" data-parsley-validate="true" action="threadingPipe/saveThreadingPipe">
						    		<input type="hidden" id="pcId" name="pcId" class="addClear">
								 	<input type="hidden" id="pcDesId" name="pcDesId" value="${pcDesId}">
								 	<input type="hidden" id="projId" name="projId" >
								 	<input type="hidden" id="projNo" name="projNo" >
								 	<input type="hidden" id="projAddr" name="projAddr" >
						    		<div class="form-group  col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projName" name="projName"/>
										</div>
									</div>
									<div class="form-group  col-md-6 col-sm-12">
										<label class="control-label" for="process">施工工序</label>
										<div> 
											<input type="text" class="form-control input-sm field-editable addClear"  id="process" name="process"  data-parsley-maxlength="100"  data-parsley-required="true" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="slResultPage">附件数量</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="slResultPage" name="slResultPage" data-parsley-maxlength="2"  data-parsley-type="number"/>
										</div>
									</div>
									
									
									
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="standardItem">达标项</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="standardItem" name="standardItem" data-parsley-maxlength="2"  data-parsley-type="number"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="notMeet">未达标项</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="notMeet" name="notMeet" data-parsley-maxlength="2"  data-parsley-type="number"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="basicItem">检查项</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="basicItem" name="basicItem" data-parsley-maxlength="2"  data-parsley-type="number"/>
										</div>
									</div>
									
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="excellentItem">优良项</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="excellentItem" name="excellentItem" data-parsley-maxlength="2"  data-parsley-type="number"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="excellentRate">优良率</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable addClear"  id="excellentRate" name="excellentRate" data-parsley-maxlength="20" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="actualMeasurementPoint">实测点</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="actualMeasurementPoint" name="actualMeasurementPoint" data-parsley-maxlength="2"  data-parsley-type="number"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="qualifiedPoint">合格点</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="qualifiedPoint" name="qualifiedPoint" data-parsley-maxlength="2"  data-parsley-type="number"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="qualifiedRate">合格率</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable addClear"  id="qualifiedRate" name="qualifiedRate" data-parsley-maxlength="20"  />
										</div>
									</div>
									
									
									<div class="form-group col-sm-12">
										<label class="control-label " for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="constructionUnit" name="constructionUnit"  data-parsley-maxlength="200" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionPrincipal">项目经理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionPrincipal" name="constructionPrincipal" value="">
											<input type="hidden" id="constructionPrincipal_postType" name="constructionPrincipal_postType" value="${subPrincipal}">
											<img class="constructionPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectionDate">检验日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="inspectionDate" name="inspectionDate"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="inspectionResult" id="inspectionResult" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionQc">质检员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQc" name="constructionQc" value="">
											<input type="hidden" id="constructionQc_postType" name="constructionQc_postType" value="${qualitativeCheckMember}">
											<img class="constructionQc" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="selfCheckDate">自检日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="selfCheckDate" name="selfCheckDate"  />
										</div>
									</div>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionQae">质保师</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQae" name="constructionQae" value="">
											<input type="hidden" id="constructionQae_postType" name="constructionQae_postType" value="${constructionQaePost}" >
											<img class="constructionQae" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									
									<div class="form-group  col-sm-12">
				                    	<label class="control-label" for="suOpinion">监理意见</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="suOpinion" id="suOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group  col-sm-12">
										<label class="control-label" for="suName">监理公司</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  id="suName" name="suName"  />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="suJgj">现场监理人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden"  id="suJgj_postType" name="suJgj_postType" value="${sujgj}">
											<img class="suJgj" alt="" src="images/sign-2.png" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checkDate">日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="checkDate" name="checkDate"  />
										</div>
									</div>
						    	</form>
						    	<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list" id="projectImagesList">
									     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a></h4>
									     <ul class="row">
									     </ul>
									</div>
								</div>
						    </div>
               			</div>
               			<div class="tab-pane fade in btn-top" id="default-tab-3">
               				<div class=" f-r p-b-15 editbtn" >
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole savebtn">保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">放弃</a>
						    </div>
						    <div class="clearboth form-box">
							    <form class="form-horizontal" id="threadingPipeRecordForm" action="" data-parsley-validate="true">
							    </form>
               				</div>
               				<div>
						    <h4 class="m-t-20"><strong>保证项目</strong></h4>
						    </div>
						    <form id="threadingPipeAuditForm">
	               				<table id="threadingPipeAuditTable" class="table table-striped table-bordered nowrap " width="100%">
		                            <thead>
		                                <tr>
		                                	<th></th>
		                                	<th></th>
		                                	<th></th>
		                                    <th>项目名称</th>
		                                    <th>质量情况</th>
		                                </tr>
		                            </thead>
		                       	</table>
	                       	</form>
	                       	<div>
						    <h4 class="m-t-20"><strong>基本项目</strong></h4>
						    </div>
						    <form id="basicThreadingPipeItemForm">
		                       	<table id="basicThreadingPipeItemTable" class="table table-striped table-bordered nowrap " width="100%">
		                            <thead>
		                                <tr>
		                                	<th></th>
		                                	<th></th>
		                                    <th>项目名称</th>
		                                    <th>1</th>
		                                    <th>2</th>
		                                    <th>3</th>
		                                    <th>4</th>
		                                    <th>5</th>
		                                    <th>6</th>
		                                    <th>7</th>
		                                    <th>8</th>
		                                    <th>9</th>
		                                    <th>10</th>
		                                    <th>等级</th>
		                                    <th></th>
		                                </tr>
		                            </thead>
		                       	</table>
	                       	</form>
	                       	<div>
						    <h4 class="m-t-20"><strong>允许偏差项目</strong></h4>
						    </div>
						    <form id="allowableErrorThreadingPipeItemForm">
		                       	<table id="allowableErrorThreadingPipeItemTable" class="table table-striped table-bordered nowrap " width="100%">
		                            <thead>
		                                <tr>
		                                	<th></th>
		                                	<th></th>
		                                    <th>项目名称</th>
		                                    <th>1</th>
		                                    <th>2</th>
		                                    <th>3</th>
		                                    <th>4</th>
		                                    <th>5</th>
		                                    <th>6</th>
		                                    <th>7</th>
		                                    <th>8</th>
		                                    <th>9</th>
		                                    <th>10</th>
		                                </tr>
		                            </thead>
		                       	</table>
	                       	</form>
               			</div>
              		</div>	
               	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" id="content">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <h4 class="panel-title">预览区</h4>
			    </div>
			    <div class="panel-body" id="purge_panel_box">
			    	<div class="clearboth form-box">
	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:1; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('控制系统调试 - 工程项目管理系统 ');

    $("#threadingPipeForm").toggleEditState().styleFit();
    $("#threadingPipeRecordForm").styleFit();
    $('.editBtn').addClass("hidden");
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$("#projAddr").val(projJson.projAddr);//工程地点
    	$('#constructionUnit').val(projJson.cuName); //施工单位
    	$("#debugUnit").val(projJson.cuName);//调试单位
    	$('#suName').val(projJson.suName);					   //监理公司
    	$('#designUnit').val(projJson.duName);//设计单位
    	$('#custName').val(projJson.custName);     	 			   //工程编号
    }
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    var cptPath = '<%=basePath%>';
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var showReport1 = function(){
    	//定义数据项
    	var projName='',suName='',process='',slResultPage='',constructionUnit='',inspectionDate='',selfCheckDate='',inspectionResult='',suOpinion='',checkDate='';
    	var json=trSData.threadingPipeTable.json;
    	if(json){
    	    projName = json.projName,			   //工程名称
    		suName=json.suName,					   //监理单位
    		process=json.process,			       //检验部位
    		slResultPage=json.slResultPage,    	   //页数
    		constructionUnit=json.constructionUnit,//施工单位
    		inspectionDate=json.inspectionDate,	   //报验日期
    		selfCheckDate=json.selfCheckDate,	   //自检日期
    		inspectionResult=json.inspectionResult,
    		suOpinion=json.suOpinion,			   //监理单位意见
    		checkDate=json.checkDate;			   //检查日期
    	}else{
    		projName =$('#projName').val(),			    //工程名称
    		suName=$('#suName').val(),				    //监理单位
    		process=$('#process').val(),					//检验部位
    		slResultPage=$('#slResultPage').val(),        //页数
    		constructionUnit=$('#constructionUnit').val(),//施工单位
    		inspectionDate=$('#inspectionDate').val(),    //报验日期
    		selfCheckDate=$('#selfCheckDate').val(),	  //自检日期
    		inspectionResult=$('#inspectionResult').val(),
    		suOpinion=$('#suOpinion').val(),			  //监理单位意见
    		checkDate=$('#checkDate').val();			  //检查日期
    	}
    	
    	//解决乱码
    	projName = encodeURIComponent(cjkEncode(projName)),				//工程名称
    	suName=encodeURIComponent(cjkEncode(suName)),					//监理单位
    	process=encodeURIComponent(cjkEncode(process)),			    	//检验部位
    	slResultPage=encodeURIComponent(cjkEncode(slResultPage)),       //页数
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)),//施工单位
    	inspectionResult=encodeURIComponent(cjkEncode(inspectionResult)),
    	suOpinion=encodeURIComponent(cjkEncode(suOpinion));			     //监理单位意见
    	
    	src = cptPath+"/ReportServer?reportlet=threadingPipe/threadingPipe.cpt&projName=" + projName+"&suName="+suName+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate;
    	src = src+"&inspectionDate="+inspectionDate+"&checkDate="+checkDate+"&suOpinion="+suOpinion+"&process="+process+"&slResultPage="+slResultPage+"&inspectionResult="+inspectionResult;
    	src = src+"&path=${path}"+"&pcId="+$('#pcIdNew').val();
    	$("#mainFrm").attr("src",src);
    };
    
    var showReport2 = function(){
    	var projName='',targetCheck='',constructionUnit='',suName='',selfCheckDate='',checkDate=''
    		debugDate='',debugUnit='',designUnit='',debugDes='',remainingProblems='',projAddr='',custName='';
    	var json=trSData.threadingPipeTable.json;
    	if(json){
    		pcId=json.pcId;
    		projName = json.projName;
    		constructionUnit = json.constructionUnit;
    		suName = json.suName;
    		selfCheckDate = json.selfCheckDate;
    		checkDate = json.checkDate;
    		
    	}else{
    		pcId=$('#pcId').val(),	
    		projName =$('#projName').val(),			      //工程名称
    		suName=$('#suName').val(),				      //监理单位
    		projAddr=$("#projAddr").val();
    		constructionUnit=$('#constructionUnit').val(),//施工单位
    		inspectionDate=$('#inspectionDate').val(),    //报验日期
    		checkData=$('#checkDate').val();			  //监理检查日期
    	
    	}
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName)); 
    	
    	var src = cptPath+"/ReportServer?reportlet=threadingPipe/threadingPipe1.cpt&projName="+projName+"&pcId="+pcId+"&suName="+suName+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate+"&checkDate="+checkDate;
    	src = src+"&path=${path}"+"&pcId="+$('#pcIdNew').val()+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src); 
    }
    
    
    var showReport3 = function(){
    	var projName='',constructionUnit='',suName='';
    	
   		projName =$('#projName').val(),			      //工程名称
   		suName=$('#suName').val(),				      //监理单位
   		constructionUnit=$('#constructionUnit').val();//施工单位
   		
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName)); 
    	var src = cptPath+"/ReportServer?reportlet=threadingPipe/threadingPipe.cpt&projName="+projName+"&suName="+suName+"&constructionUnit="+constructionUnit;
    	src = src+"&path=${path}"+"&pcId="+$('#pcIdNew').val();
    	$("#mainFrm").attr("src",src); 
    }
    
    //签字加载方式
    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5").handleSignature();
    
  	//打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 
    
    $.getScript('projectjs/inspection/threading-pipe.js').done(function () {
    	threadingPipe.init();
	});
    
  	//报验区点击放弃
	$('.giveupBtn').off().on('click',function(){
		if($("#pcId").val()==""){
			//返回列表区
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#listTab').tab("show");
			$('#threadingPipeTable').cgetData(true);
		}else{
			//返回列表区
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#listTab').tab("show");
		}
		
    })
    //报验区保存工程报验单
    $('.saveBtn').off().on("click",function(){
    	$('#threadingPipeForm').cformSave('threadingPipeTable',saveThreadingPipeBack,false);
    })
    var saveThreadingPipeBack=function(data){
		$('.editBtn').addClass("hidden");
    	$('#threadingPipeForm').toggleEditState(false);
    	if(data!==false){
    		$('#pcIdNew').val(data);//返回pcId
    	}

    	var pcId = data,
		projNo = $("#projNo").val();
		projId = $("#projId").val();
		
		if(_inApk && $(".attach-images-list").find(".new-image").length){
			preImagesUpload($(".attach-images-list .uploadBtn"), projId, projNo, getStepId(), pcId);
		};
    	//刷新帆软报表
    	showReport1();
  	}
	
	//记录区批量保存
    $(".savebtn").on("click",function(){
    	if($('#pcIdNew').val()!==''){
    		var dataObj={};
    		dataObj.firstList = [],
    		dataObj.secondList = [],
    		dataObj.thirdList = [];
    		var firstJson=$("#threadingPipeAuditTable").DataTable().rows().data();
        	console.info('json.length...'+firstJson.length);
        	console.info(firstJson);
        	$.each(firstJson, function(k,v){
    			v.pcId=$("#pcIdNew").val();
    			dataObj.firstList.push(v);
    		})
        	console.info('1-----')
        	var secondJson=$("#basicThreadingPipeItemTable").DataTable().rows().data();
        	console.info('json2.length...'+secondJson.length);
        	console.info(secondJson);
        	$.each(secondJson, function(k,v){
    			v.pcId=$("#pcIdNew").val();
    			dataObj.secondList.push(v);
    		})
        	console.info('2-----'); 
        	var thirdJson=$("#allowableErrorThreadingPipeItemTable").DataTable().rows().data();
        	console.info('json3.length...'+thirdJson.length);
        	console.info(thirdJson);
        	$.each(thirdJson, function(k,v){
    			v.pcId=$("#pcIdNew").val();
    			dataObj.thirdList.push(v);
    		})
    		dataObj.pcId=$("#pcIdNew").val();
        	console.info("dataObj");
        	console.info(dataObj);
        	var data=JSON.stringify(dataObj);
			console.info("data...");
			console.info(data);
        	
        	
    		if(response){
    	    	response.abort();
      	    	}
      		  	var response = $.ajax({
    			url: "threadingPipe/saveThreadingPipeRecord",
      	          	type: "POST",
      	          	timeout : 5000,
      	          	contentType: "application/json;charset=UTF-8",
      	          	data:data ,
      	          	success: function (data) {
    				if (data === "true") {   	        		  
    					$("body").cgetPopup({
    	                  	title: "提示信息",
    	                  	content: "数据保存成功!",
    	                  	accept: successBack,
    	                  	chide: true,
    	                  	icon: "fa-check-circle",
    	                  	newpop: 'new'
                      	}); 
    				}else{
      	        			$("body").cgetPopup({
       	                  	title: "提示信息",
       	                  	content: "数据保存失败, 请重试! <br>" + data,
       	                  	accept: popClose,
       	                  	chide: true,
       	                  	icon: "fa-exclamation-circle",
       	                  	newpop: 'new'
    					});  
    				}
    			}
    		});
		}else{
			$("body").cgetPopup({
    	       	title: "提示信息",
    	       	content: "报验单不存在，不允许保存记录!",
    	       	accept: successBack2,
    	       	chide: true,
    	       	icon: "fa-check-circle",
    	       	newpop: 'new'
    	       });
    		 return false;
		}
		
	});
	function successBack2(){};
   	function successBack(){
   		showReport2();
   	} 
    
	//记录区点击删除
	$('.giveupbtn').off().on('click',function(){
		if(!$.fn.DataTable.isDataTable('#threadingPipeAuditTable')){
			//初始化记录列表
			handleThreadingPipeAudit();
		}else{
			if($('#pcIdNew').val()!==''){
				searchData.pcId=$('#pcIdNew').val();
   				$('#threadingPipeAuditTable').cgetData();
   				//showReport2();
				}else{
					searchData.pcId=-1;
					$('#threadingPipeAuditTable').cgetData();
					//showReport2();
				}
		}
		if(!$.fn.DataTable.isDataTable('#basicThreadingPipeItemTable')){
			//基本项目表格
			handleBasicThreadingPipeItem();
		}else{
			if($('#pcIdNew').val()!==''){
				basicSearchData.pcId=$('#pcIdNew').val();
				$('#basicThreadingPipeItemTable').cgetData();
				//showReport2();
			}else{
				basicSearchData.pcId=-1;
				$('#basicThreadingPipeItemTable').cgetData();
				//showReport2();
			}
		}
		if(!$.fn.DataTable.isDataTable('#allowableErrorThreadingPipeItemTable')){
			//允许偏差表格
			handleAllowableErrorThreadingPipeItem();
		}else{
			if($('#pcIdNew').val()!==''){
				errorSearchData.pcId=$('#pcIdNew').val();
				$('#allowableErrorThreadingPipeItemTable').cgetData();
				//showReport2();
			}else{
				errorSearchData.pcId=-1;
				$('#allowableErrorThreadingPipeItemTable').cgetData();
				//showReport2();
			}
		}
		showReport2();
    })
   
    //优良项
    $("#basicItem").off().on("change",function(){
    	var basicItemVal=$("#basicItem").val();
    	if($("#excellentItem").val()!="" && $("#basicItem").val()!=""){
    		var excellentItemVal=$("#excellentItem").val();
    		$("#excellentRate").val(new Number(new Number(excellentItemVal)/(new Number(basicItemVal))*100).toFixed(2)+"%");
    	}
    });
    $("#excellentItem").off().on("change",function(){
    	if($("#excellentItem").val()!="" && $("#basicItem").val()!=""){
    		var basicItemVal=$("#basicItem").val();
    		var excellentItemVal=$("#excellentItem").val();
    		$("#excellentRate").val(new Number(new Number(excellentItemVal)/(new Number(basicItemVal))*100).toFixed(2)+"%");
    	}
    });
    
    //实测点
    $("#actualMeasurementPoint").off().on("change",function(){
    	var basicItemVal=$("#actualMeasurementPoint").val();
    	if($("#qualifiedPoint").val()!="" && $("#actualMeasurementPoint").val()!=""){
    		var excellentItemVal=$("#qualifiedPoint").val();
    		//合格点/实测点
    		$("#qualifiedRate").val(new Number(new Number(excellentItemVal)/(new Number(basicItemVal))*100).toFixed(2)+"%");
    	}
    })
    $("#qualifiedPoint").off().on("change",function(){
    	if($("#qualifiedPoint").val()!="" && $("#actualMeasurementPoint").val()!=""){
    		var basicItemVal=$("#actualMeasurementPoint").val();
    		var excellentItemVal=$("#qualifiedPoint").val();
    		$("#qualifiedRate").val(new Number(new Number(excellentItemVal)/(new Number(basicItemVal))*100).toFixed(2)+"%");
    	}
    })
    //移动端点击上传
    $(".uploadBtn").off("click").on("click",function(){
	    var pcId = $("#pcId").val(),
		projNo = $("#projNo").val(),
		projId = $("#projId").val(),
		stepId=getStepId();
	    busRecordId=pcId;
		if(_inApk && $(".attach-images-list").find(".new-image").length){
				//photoUpload($(".attach-images-list .upload-image-btn"), projId, projNo, getStepId(), pcId);
				
			var images = $(".attach-images-list .uploadBtn").parents(".attach-images-list").find(".new-image"),
		    files = [];
		    $.each(images, function (i, el) {
		        files.push($(el).find(".preview-btn").attr("src-orig"));
		    });
		    //navigator.notification.alert(files);
		    if (!busRecordId && $("#pcId").length && !$("#pcId").val()) {
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
	})
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->