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
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="insulationResistanceTestTable" width="100%">
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
						    	<form class="form-horizontal" id="insulationResistanceTestForm" data-parsley-validate="true" action="insulationResistanceTest/saveInsulationResistanceTest">
						    		<input type="hidden" id="pcId" name="pcId" class="addClear">
								 	<input type="hidden" id="pcDesId" name="pcDesId" value="${pcDesId}">
								 	<input type="hidden" id="projId" name="projId" >
								 	<input type="hidden" id="projNo" name="projNo" >
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
										<label class="control-label" for="systemCategory">系统类别</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="systemCategory" name="systemCategory"  data-parsley-maxlength="20" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="drawingNo">图号</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="drawingNo" name="drawingNo"  data-parsley-maxlength="20" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="model">型号</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="model" name="model"  data-parsley-maxlength="20" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="weatherCondition">天气情况</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="weatherCondition" name="weatherCondition" data-parsley-maxlength="20"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="roomTemperature">室温</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="roomTemperature" name="roomTemperature"  data-parsley-maxlength="20" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="voltage">电压</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="voltage" name="voltage" data-parsley-maxlength="20" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="qualityStandard">质量标准</label>
										<div>
											<input type="text" class="form-control input-sm field-editable "  id="qualityStandard" name="qualityStandard"  value="MΩ" data-parsley-maxlength="20" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="unitMeasurement">计量单位</label>
										<div>
											<input type="text" class="form-control input-sm field-editable "  id="unitMeasurement" name="unitMeasurement"  value="MΩ" data-parsley-maxlength="20" />
										</div>
									</div>
									<div class="form-group col-sm-12">
				                    	<label class="control-label" for="testResult">测试结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="testResult" id="testResult" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="testControler">测试员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="testControler" name="testControler" value="">
											<input type="hidden" id="testControler_postType" name="testControler_postType" value="">
											<img class="testControler" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checkoutDate">测试日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default addClear"  id="checkoutDate" name="checkoutDate"  />
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
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
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
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
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
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${sujgj}">
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
							    <form class="form-horizontal" id="insulationResistanceTestRecordFrom" action="" data-parsley-validate="true">
							   		<div class="form-group col-md-12 col-sm-12">
							            <label class="control-label" for="loop">回路</label>
							            <div>
							            	<input type="text" class="form-control input-sm"  id="loop"  name="loop" data-parsley-maxlength="20"/>
							            </div>
							        </div>
							   		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="phaseAB">相间A-B</label>
										<div>
							                <select class="form-control input-sm" id="phaseAB"   name="phaseAB"  data-parsley-maxlength="10">
								                <option value="" ></option>
								                <option value="∞" >∞</option>
								              </select>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="phaseBC">相间B-C</label>
										<div>
							                <select class="form-control input-sm" id="phaseBC"   name="phaseBC"  data-parsley-maxlength="10">
								                <option value="" ></option>
								                <option value="∞" >∞</option>
								              </select>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="phaseCA">相间C-A</label>
										<div>
							                <select class="form-control input-sm" id="phaseCA"   name="phaseCA"  data-parsley-maxlength="10">
								                <option value="" ></option>
								                <option value="∞" >∞</option>
								              </select>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="zeroAN">相对零A-N</label>
										<div>
							                <select class="form-control input-sm" id="zeroAN"   name="zeroAN"  data-parsley-maxlength="10">
								                <option value="" ></option>
								                <option value="∞" >∞</option>
								              </select>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="zeroBN">相对零B-N</label>
										<div>
							                <select class="form-control input-sm" id="zeroBN"   name="zeroBN"  data-parsley-maxlength="10">
								                <option value="" ></option>
								                <option value="∞" >∞</option>
								              </select>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="zeroCN">相对零C-N</label>
										<div>
							                <select class="form-control input-sm" id="zeroCN"   name="zeroCN"  data-parsley-maxlength="10">
								                <option value="" ></option>
								                <option value="∞" >∞</option>
								              </select>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="earthAE">相对地A-E</label>
										<div>
							                <select class="form-control input-sm" id="earthAE"   name="earthAE"  data-parsley-maxlength="10">
								                <option value="" ></option>
								                <option value="∞" >∞</option>
								              </select>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="earthBE">相对地B-E</label>
										<div>
							                <select class="form-control input-sm" id="earthBE"   name="earthBE"  data-parsley-maxlength="10">
								                <option value="" ></option>
								                <option value="∞" >∞</option>
								              </select>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="earthCE">相对地C-E</label>
										<div>
							                <select class="form-control input-sm" id="earthCE"   name="earthCE"  data-parsley-maxlength="10">
								                <option value="" ></option>
								                <option value="∞" >∞</option>
								              </select>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="zeroEarthNE">零对地N-E</label>
										<div>
							                <select class="form-control input-sm" id="zeroEarthNE"   name="zeroEarthNE"  data-parsley-maxlength="10">
								                <option value="" ></option>
								                <option value="∞" >∞</option>
								              </select>
							            </div>
									</div>
							    </form>
               				</div>
               				<table id="InsulationResistanceTestAuditTable" class="table table-striped table-bordered nowrap " width="100%">
	                            <thead>
	                            	<!-- <tr>
										<th class="text-left" width="180px"></th>
										<th class="text-center"  colspan="3">相间</th>
							            <th class="text-center"  colspan="3">相对零</th>
							            <th class="text-center"  colspan="3">相对地</th>
							            <th class="text-center"  >零对地</th>
									</tr> -->
	                                <tr>
	                                    <th>回路</th>
	                                    <th>相间A-B</th>
	                                    <th>相间B-C</th>
	                                    <th>相间C-A</th>
	                                    <th>相对零A-N</th>
	                                    <th>相对零B-N</th>
	                                    <th>相对零C-N</th>
	                                    <th>相对地A-E</th>
	                                    <th>相对地B-E</th>
	                                    <th>相对地C-E</th>
	                                    <th>零对地N-E</th>
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
    App.setPageTitle('绝缘电阻测试 - 工程项目管理系统 ');

    $("#insulationResistanceTestForm").toggleEditState().styleFit();
    $("#insulationResistanceTestRecordFrom").styleFit();
    $('.editBtn').addClass("hidden");
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.cuName); //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    }
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    var cptPath = '<%=basePath%>';
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var showReport1 = function(){
    	//定义数据项
    	var projName='',suName='',process='',slResultPage='',constructionUnit='',inspectionDate='',selfCheckDate='',inspectionResult='',suOpinion='',checkDate='';
    	var json=trSData.insulationResistanceTestTable.json;
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
    		slResultPage=$('#slResultPage').val(),      //页数
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
    	
    	src = cptPath+"/ReportServer?reportlet=insulationResistanceTest/insulationResistanceTest.cpt&projName=" + projName+"&suName="+suName+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate;
    	src = src+"&inspectionDate="+inspectionDate+"&checkDate="+checkDate+"&suOpinion="+suOpinion+"&process="+process+"&slResultPage="+slResultPage+"&inspectionResult="+inspectionResult;
    	src = src+"&path=${path}"+"&pcId="+$('#pcIdNew').val();
    	$("#mainFrm").attr("src",src);
    };
    
    var showReport2 = function(){
    	var projName='',targetCheck='',constructionUnit='',suName='',selfCheckDate='',checkDate='',systemCategory='',
    	drawingNo='',checkoutDate='',model='',weatherCondition='',roomTemperature='',voltage='',qualityStandard='',unitMeasurement='',testResult='';
    	var json=trSData.insulationResistanceTestTable.json;
    	if(json){
    		pcId=json.pcId;
    		projName = json.projName;
    		constructionUnit = json.constructionUnit;
    		suName = json.suName;
    		selfCheckDate = json.selfCheckDate;
    		checkDate = json.checkDate;
    		systemCategory=json.systemCategory;		//系统类别
    		drawingNo=json.drawingNo;				//图号
    		checkoutDate=json.checkoutDate;			//测试日期
    		model=json.model;						//型号
    		weatherCondition=json.weatherCondition;//天气情况
    		roomTemperature=json.roomTemperature;//室温
    		voltage=json.voltage;				 //电压
    		qualityStandard=json.qualityStandard;//质量标准
    		unitMeasurement=json.unitMeasurement;//计量单位
    		testResult=json.testResult;//测试结果
    	}else{
    		pcId=$('#pcId').val(),	
    		projName =$('#projName').val(),			      //工程名称
    		suName=$('#suName').val(),				      //监理单位
    		constructionUnit=$('#constructionUnit').val(),//施工单位
    		inspectionDate=$('#inspectionDate').val(),    //报验日期
    		checkData=$('#checkDate').val();			  //监理检查日期
    		systemCategory=$('#systemCategory').val();	//系统类别
    		drawingNo=$('#drawingNo').val();				//图号
    		checkoutDate=$('#checkoutDate').val();		//测试日期
    		model=$('#model').val();					//型号
    		weatherCondition=$('#weatherCondition').val();//天气情况
    		roomTemperature=$('#roomTemperature').val();//室温
    		voltage=$('#voltage').val();				 //电压
    		qualityStandard=$('#qualityStandard').val();//质量标准
    		unitMeasurement=$('#unitMeasurement').val();//计量单位
    		testResult=$('#testResult').val();//测试结果
    	}
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName)); 
    	systemCategory=encodeURIComponent(cjkEncode(systemCategory)); 	//系统类别
		drawingNo=encodeURIComponent(cjkEncode(drawingNo)); 				//图号
		checkoutDate=encodeURIComponent(cjkEncode(checkoutDate)); 		//测试日期
		model=encodeURIComponent(cjkEncode(model)); 				//型号
		weatherCondition=encodeURIComponent(cjkEncode(weatherCondition)); //天气情况
		roomTemperature=encodeURIComponent(cjkEncode(roomTemperature)); //室温
		voltage=encodeURIComponent(cjkEncode(voltage)); 				 //电压
		qualityStandard=qualityStandard;/* encodeURIComponent(cjkEncode(qualityStandard)) */; //质量标准
		unitMeasurement=unitMeasurement; //计量单位
		testResult=encodeURIComponent(cjkEncode(testResult)); //测试结果
    	var src = cptPath+"/ReportServer?reportlet=insulationResistanceTest/insulationResistanceTest1.cpt&projName="+projName+"&pcId="+pcId+"&suName="+suName+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate+"&checkDate="+checkDate;
    	src =src+"&systemCategory="+systemCategory+"&drawingNo="+drawingNo+"&checkoutDate="+checkoutDate+"&model="+model+"&weatherCondition="+weatherCondition+"&roomTemperature="+roomTemperature+"&voltage="+voltage+"&qualityStandard="+qualityStandard+"&unitMeasurement="+unitMeasurement;
    	src =src+"&testResult="+testResult;
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
    	var src = cptPath+"/ReportServer?reportlet=insulationResistanceTest/insulationResistanceTest.cpt&projName="+projName+"&suName="+suName+"&constructionUnit="+constructionUnit;
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
    
    $.getScript('projectjs/inspection/insulation-resistance-test.js').done(function () {
    	insulationResistanceTest.init();
	});
    
  	//报验区点击放弃
	$('.giveupBtn').off().on('click',function(){
		if($("#pcId").val()==""){
			//返回列表区
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#listTab').tab("show");
			$('#insulationResistanceTestTable').cgetData(true);
		}else{
			//返回列表区
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#listTab').tab("show");
		}
		
    })
    //报验区保存工程报验单
    $('.saveBtn').off().on("click",function(){
    	$('#insulationResistanceTestForm').cformSave('insulationResistanceTestTable',savePurgeBack,false);
    })
    var savePurgeBack=function(data){
		$('.editBtn').addClass("hidden");
    	$('#insulationResistanceTestForm').toggleEditState(false);
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
    	var t=$("#insulationResistanceTestRecordFrom");
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
	    	    InsulationResistanceTestAuditTable.rows.add(rowsPart).draw();
	    	    $('#InsulationResistanceTestAuditTable').selectRow(0);
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
    	var json=$("#InsulationResistanceTestAuditTable").DataTable().rows().data();
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
			url: "insulationResistanceTest/saveInsulationResistanceTestRecord",
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
		var len=$('#InsulationResistanceTestAuditTable').find('tr.selected').length;
		 if(len>0){
			 var rows = $("#InsulationResistanceTestAuditTable").DataTable().rows( '.selected' ).remove().draw();
			 $('#InsulationResistanceTestAuditTable').selectRow(0);
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