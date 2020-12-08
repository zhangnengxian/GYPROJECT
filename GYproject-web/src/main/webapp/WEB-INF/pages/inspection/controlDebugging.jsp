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
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="controlDebuggingTable" width="100%">
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
						    	<form class="form-horizontal" id="controlDebuggingForm" data-parsley-validate="true" action="insulationResistanceTest/saveInsulationResistanceTest">
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
										<label class="control-label" for="debugUnit">调试单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable "  id="debugUnit" name="debugUnit" data-parsley-maxlength="200"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="designUnit">设计单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable "  id="designUnit" name="designUnit" data-parsley-maxlength="200"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
				                    	<label class="control-label" for="remainingProblems">遗留问题</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="remainingProblems" id="remainingProblems" rows="3" cols="" data-parsley-maxlength="500" ></textarea>
					                    </div>
					  			    </div>
									<div class="form-group col-sm-12">
				                    	<label class="control-label" for="debugDes">调试情况</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="debugDes" id="debugDes" rows="3" cols="" data-parsley-maxlength="500" ></textarea>
					                    </div>
					  			    </div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionPrincipal">调试人员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="debugger" name="debugger" value="">
											<input type="hidden" id="debugger_postType" name="debugger_postType" value="">
											<img class="debugger" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="debugDate">调试日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default addClear"  id="debugDate" name="debugDate" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="technician">技术员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="technician" name="technician" value="">
											<input type="hidden" id="technician_postType" name="technician_postType" value="">
											<img class="technician" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
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
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole addbtn">增加</a>
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole savebtn">保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">删除</a>
						    </div>
						    <div class="clearboth form-box">
							    <form class="form-horizontal" id="controlDebuggingRecordForm" action="" data-parsley-validate="true">
							   		<div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="equipmentNameType">设备名称</label>
							            <div>
							            	<input type="text" class="form-control input-sm"  id="equipmentNameType"  name="equipmentNameType" data-parsley-maxlength="100"/>
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="cdNum">数量</label>
							            <div>
							            	<input type="text" class="form-control input-sm"  id="cdNum"  name="cdNum" data-parsley-type="number" data-parsley-maxlength="6"/>
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="leaveFactoryDate">出厂年月</label>
							            <div>
							            	<input type="text" class="form-control input-sm datepicker-default"  id="leaveFactoryDate"  name="leaveFactoryDate" />
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="cdNo">编号</label>
							            <div>
							            	<input type="text" class="form-control input-sm"  id="cdNo"  name="cdNo" data-parsley-maxlength="20"/>
							            </div>
							        </div>
							        
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="manufactureFactory">生产厂家</label>
							            <div>
							            	<input type="text" class="form-control input-sm"  id="manufactureFactory"  name="manufactureFactory" data-parsley-maxlength="200"/>
							            </div>
							        </div>
							   		<div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="note">备注</label>
							            <div>
							            	<input type="text" class="form-control input-sm"  id="note"  name="note" data-parsley-maxlength="200"/>
							            </div>
							        </div>
							    </form>
               				</div>
               				<table id="controlDebuggingAuditTable" class="table table-striped table-bordered nowrap " width="100%">
	                            <thead>
	                                <tr>
	                                    <th>设备名称型号</th>
	                                    <th>数量</th>
	                                    <th>编号</th>
	                                    <th>出厂年月</th>
	                                    <th>生产厂家</th>
	                                    <th>备注</th>
	                                </tr>
	                            </thead>
	                       	</table>
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

    $("#controlDebuggingForm").toggleEditState().styleFit();
    $("#controlDebuggingRecordForm").styleFit();
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
    	var json=trSData.controlDebuggingTable.json;
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
    	
    	src = cptPath+"/ReportServer?reportlet=controlDebugging/controlDebugging.cpt&projName=" + projName+"&suName="+suName+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate;
    	src = src+"&inspectionDate="+inspectionDate+"&checkDate="+checkDate+"&suOpinion="+suOpinion+"&process="+process+"&slResultPage="+slResultPage+"&inspectionResult="+inspectionResult;
    	src = src+"&path=${path}"+"&pcId="+$('#pcIdNew').val();
    	$("#mainFrm").attr("src",src);
    };
    
    var showReport2 = function(){
    	var projName='',targetCheck='',constructionUnit='',suName='',selfCheckDate='',checkDate=''
    		debugDate='',debugUnit='',designUnit='',debugDes='',remainingProblems='',projAddr='',custName='';
    	var json=trSData.controlDebuggingTable.json;
    	if(json){
    		pcId=json.pcId;
    		projName = json.projName;
    		constructionUnit = json.constructionUnit;
    		suName = json.suName;
    		selfCheckDate = json.selfCheckDate;
    		checkDate = json.checkDate;
    		debugDate=json.debugDate;				//调试日期
    		debugUnit=json.debugUnit;				//调试单位
    		designUnit=json.designUnit;				//设计单位
    		debugDes=json.debugDes;					//系统调试情况
    		remainingProblems=json.remainingProblems;//遗留问题
    		projAddr=json.projAddr;//
    		custName=$("#custName").val();//建设单位
    		console.info("custName1.."+custName);
    	}else{
    		pcId=$('#pcId').val(),	
    		projName =$('#projName').val(),			      //工程名称
    		suName=$('#suName').val(),				      //监理单位
    		projAddr=$("#projAddr").val();
    		constructionUnit=$('#constructionUnit').val(),//施工单位
    		inspectionDate=$('#inspectionDate').val(),    //报验日期
    		checkData=$('#checkDate').val();			  //监理检查日期
    		debugDate=$('#debugDate').val();			  //调试日期
    		debugUnit=$('#debugUnit').val();			  //调试单位
    		designUnit=$('#designUnit').val();			  //设计单位
    		debugDes=$('#debugDes').val();				  //系统调试情况
    		remainingProblems=$('#remainingProblems').val();;//遗留问题
    		custName=$("#custName").val();//建设单位
    		console.info("custName2.."+custName);
    	}
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName)); 
    	projAddr=encodeURIComponent(cjkEncode(projAddr)); 
    	debugDate=encodeURIComponent(cjkEncode(debugDate)); 			  //调试日期
		debugUnit=encodeURIComponent(cjkEncode(debugUnit)); 			  //调试单位
		designUnit=encodeURIComponent(cjkEncode(designUnit)); 			  //设计单位
		debugDes=encodeURIComponent(cjkEncode(debugDes)); 				  //系统调试情况
		remainingProblems=encodeURIComponent(cjkEncode(remainingProblems));//遗留问题
		custName=encodeURIComponent(cjkEncode(custName));//建设单位
		console.info("custName3.."+custName);
    	var src = cptPath+"/ReportServer?reportlet=controlDebugging/controlDebugging1.cpt&projName="+projName+"&pcId="+pcId+"&suName="+suName+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate+"&checkDate="+checkDate;
    	src =src+"&debugDate="+debugDate+"&debugUnit="+debugUnit+"&designUnit="+designUnit+"&debugDes="+debugDes+"&remainingProblems="+remainingProblems+"&projAddr="+projAddr;
    	src = src+"&path=${path}"+"&pcId="+$('#pcIdNew').val()+"&imgUrl="+$(".imgUrl").val()+"&custName="+custName;
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
    	var src = cptPath+"/ReportServer?reportlet=controlDebugging/controlDebugging.cpt&projName="+projName+"&suName="+suName+"&constructionUnit="+constructionUnit;
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
    
    $.getScript('projectjs/inspection/control-debugging.js').done(function () {
    	controlDebugging.init();
	});
    
  	//报验区点击放弃
	$('.giveupBtn').off().on('click',function(){
		if($("#pcId").val()==""){
			//返回列表区
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#listTab').tab("show");
			$('#controlDebuggingTable').cgetData(true);
		}else{
			//返回列表区
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#listTab').tab("show");
		}
		
    })
    //报验区保存工程报验单
    $('.saveBtn').off().on("click",function(){
    	$('#controlDebuggingForm').cformSave('controlDebuggingTable',saveControlDebuggingBack,false);
    })
    var saveControlDebuggingBack=function(data){
		$('.editBtn').addClass("hidden");
    	$('#controlDebuggingForm').toggleEditState(false);
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
	var rows=[];
	var rowData=[];
	//记录区增加记录
    $(".addbtn").off().on("click",function(){
    	var rowsPart=[];
    	var t=$("#controlDebuggingRecordForm");
		if($('#pcIdNew').val()!==''){
	    	//开启表单验证
	        if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') { 
	        	//报验单id
				var id=$('#pcIdNew').val();
		   		var json=t.serializeJson();
		   		console.info("增加---");
		   		console.info(json);
		   		json.pcId=id;
		   		rowsPart.push(json);
		   		
		   		//记录表格添加一条记录
	    	    controlDebuggingAuditTable.rows.add(rowsPart).draw();
	    	    $('#controlDebuggingAuditTable').selectRow(0);
		        t.parsley().reset();
		    } else {
		        //如果未通过验证, 则加载验证UI
		        t.parsley().validate();
		    };
		}else{
			$("body").cgetPopup({
		       	title: "提示信息",
		       	content: "报验单不存在，不允许添加记录!",
		       	accept: successBack2,
		       	chide: true,
		       	icon: "fa-check-circle",
		       	newpop: 'new'
		       });
			 return false;
		} 
    });
	var successBack1=function(){
	};
	var successBack2=function(){};
	//记录区批量保存
    $(".savebtn").on("click",function(){
        var dataObj={};
    	var json=$("#controlDebuggingAuditTable").DataTable().rows().data();
    	console.info('json.length...'+json.length);
    	dataObj.list = [];
		$.each(json, function(k,v){
			dataObj.list.push(v);
		})
		var id=$("#pcIdNew").val();
		dataObj.pcId=id;
		var data=JSON.stringify(dataObj);
		console.info("data.."+data);
		dataObj.list = [];//清空了;
		if(response){
	    	response.abort();
  	    	}
  		  	var response = $.ajax({
			url: "controlDebugging/saveControlDebuggingRecord",
  	          	type: "POST",
  	          	timeout : 5000,
  	          	contentType: "application/json;charset=UTF-8",
  	          	data: data,
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
	});
   	function successBack(){
   		showReport2();
   	} 
    
	//记录区点击删除
	$('.giveupbtn').off().on('click',function(){
		var len=$('#controlDebuggingAuditTable').find('tr.selected').length;
		 if(len>0){
			 var rows = $("#controlDebuggingAuditTable").DataTable().rows( '.selected' ).remove().draw();
			 $('#controlDebuggingAuditTable').selectRow(0);
		 }else{
			 $("body").cgetPopup({
			 	title: '提示信息',
				content: '请选择要删除的记录!',
				accept: delDataPurge,
				icon: 'fa-exclamation-circle',
			 });
		 }
    })
    function delDataPurge(){};
    
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