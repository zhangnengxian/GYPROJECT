<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- pressure.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jedate/skin/jedate.css" rel="stylesheet" />
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
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表区</a></li>
		                <li class=""><a href="#default-tab-2" id="signTab"  data-toggle="tab">报验区</a></li>
		                <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" id="">
               		<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
               			   <input type='hidden' id='pcIdNew'>
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="pressureTable" width="100%">
	                			<thead>
		                			<tr>
		                				<th></th>
		                				<th>报验日期</th>
			                			<th>试压部位</th>
			                			<th>查验结果</th>
			                			<th>完成状态</th>
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
						    	<form class="form-horizontal" id="pressureForm" data-parsley-validate="true" action="pressure/savePressure">
						    		<input type="hidden" id="pcId" name="pcId" class="addClear">
								 	<input type="hidden" id="pcDesId" name="pcDesId" value="${pcDesId}">
								 	<input type="hidden" id="projId" name="projId" >
								 	<!-- 完成标记 -->
									<input type="hidden" id="flag" name="flag">
									<div class="form-group  col-sm-12">
										<label class="control-label" for="projNo">工程编号</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projNo" name="projNo"  />
										</div>
									</div>
						    		<div class="form-group  col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projName" name="projName"  />
										</div>
									</div>
									
									<div class="form-group  col-sm-12">
										<label class="control-label" for="process">试压部位</label>
										<div> 
											<input type="text" class="form-control input-sm field-editable addClear"  id="process" name="process" data-parsley-required="true" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="slResultPage">附件数量</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="slResultPage" name="slResultPage"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="meterial">材质</label>
							            <div >
							                <input type="text" class="form-control input-sm  field-editable addClear"  id="meterial" name="meterial" />
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="grCaliber">管径</label>
							            <div >
							                <input type="text" class="form-control input-sm field-editable addClear"   id="grCaliber" name="grCaliber" />
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="length">长度</label>
							            <div >
							                <input type="text" class="form-control input-sm field-editable addClear"  id="length" name="length" />
							            </div>
							        </div>
									 <!-- <div class="form-group col-md-6 col-sm-12">
					        			<label class="control-label" for="">组件安装</label>
					        			<div>
							            	<label>
							            		<input type="radio" name="" value="1" checked/>正确
							            	</label>
							            	<label>
							            		<input type="radio" name="" value="2" />错误
							            	</label>
							            </div>
		    						</div>
		    						<div class="form-group col-md-6 col-sm-12">
					        			<label class="control-label" for="">支架安装</label>
					        			<div>
							            	<label>
							            		<input type="radio" name="" value="1" checked/>正确
							            	</label>
							            	<label>
							            		<input type="radio" name="" value="2" />错误
							            	</label>
							            </div>
		    						</div>
									<div class="form-group col-md-6 col-sm-12">
					        			<label class="control-label" for="">焊接记录</label>
					        			<div>
							            	<label>
							            		<input type="radio" name="" value="1" checked/>合格
							            	</label>
							            	<label>
							            		<input type="radio" name="" value="2" />不合格
							            	</label>
							            </div>
		    						</div>
		    						<div class="form-group col-md-6 col-sm-12">
					        			<label class="control-label" for="">盲板标示</label>
					        			<div>
							            	<label>
							            		<input type="radio" name="" value="1" checked/>合格
							            	</label>
							            	<label>
							            		<input type="radio" name="" value="2" />不合格
							            	</label>
							            </div>
		    						</div> -->
		    						<div class="form-group  col-sm-12 ">
								        <label class="control-label" for="inspectionClum">查验结论</label>
								    	<div>
											<label>
												<input type="radio" name="inspectionClum" value="1" checked />
											检查合格          
											</label>
											<label>
												<input type="radio" name="inspectionClum" value="0"  />
											  纠错重报
											</label>
								        </div>
								   	</div>
									<div class="form-group col-sm-12">
										<label class="control-label " for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionPrincipal">项目经理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionPrincipal" name="constructionPrincipal" value="">
											<input type="hidden" id="constructionPrincipal_postType" name="constructionPrincipal_postType" value="${deputyDirector}">
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
									<div class="form-group  col-sm-12">
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
						    		<div class="form-group  col-md-6 col-sm-12">
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
											<input type="hidden" id="constructionQae_postType" name="constructionQae_postType" value="${managementqae}">
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
											<input type="text" class="form-control field-not-editable input-sm"  id="suName" name="suName" />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="suJgj">现场监理人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${sujgj}">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checkDate">日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="checkDate" name="checkDate"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="testUnit">监检单位</label>
										<div>
											<input type="text" class="form-control field-editable input-sm"  id="testUnit" name="testUnit"  />
										</div>
								    </div>
									 <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="tester">监检人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="tester" name="tester" value="">
											<img class="tester" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="testDate">日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="testDate" name="testDate"  />
										</div>
									</div>
						    	</form>
						    	<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list" id="projectImagesList">
									     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
									     <ul class="row">
									     </ul>
									</div>
								</div>
						    </div>
               			</div>
               		
               	        <div class="tab-pane fade in btn-top" id="default-tab-3">
	               	       	<div class=" f-r p-b-15 " >
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole addbtn">增加</a>
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole savebtn">保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">删除</a>
						    </div>
						    <div class="clearboth form-box">
						    	<!-- 完成标记 -->
									<input type="hidden" id="flag1" class="addClear">
						    	<form class="form-horizontal" id="pressureRecordForm" data-parsley-validate="true" action="">
						    		<div class="form-group col-sm-12 ">
							            <label class="control-label" for="pressureStarteEnd">起止点</label>
							            <div >
							                <input type="text" class="form-control input-sm"  id="pressureStarteEnd" name="pressureStarteEnd" data-parsley-required="true" value="碰口处至调压箱进口"/>
							            </div>
							        </div>
							        
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="designPressure">设计压力</label>
							            <div >
							                <input type="text" class="form-control input-sm" value="" id="designPressure" name="designPressure" />
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="designMedium">设计介质</label>
							            <div >
							                <input type="text" class="form-control input-sm"  id="designMedium" name="designMedium" />
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="strengthPa">强度压力</label>
							            <div >
							                <input type="text" class="form-control input-sm"  id="strengthPa" name="strengthPa" />
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="strengthMedium">试验介质</label>
							            <div >
							                <input type="text" class="form-control input-sm"  id="strengthMedium" name="strengthMedium" value="压缩空气"/>
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="strengthTime">开始时间</label>
							            <div >
							                <input type="text" class="form-control input-sm"  id="strengthTime" name="strengthTime" />
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="strengthEndTime">结束时间</label>
							            <div >
							                <input type="text" class="form-control input-sm"  id="strengthEndTime" name="strengthEndTime" />
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12 " >
							            <label class="control-label" for="strengthResult">试验结论</label>
							            <div >
							                <input type="text" class="form-control input-sm"  id="strengthResult" name="strengthResult" value="合格"/>
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12 clearboth">
							            <label class="control-label" for="rigorPa">严密压力</label>
							            <div >
							                <input type="text" class="form-control input-sm"  id="rigorPa" name="rigorPa" />
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="rigorMedium">试验介质</label>
							            <div >
							                <input type="text" class="form-control input-sm" id="rigorMedium" name="rigorMedium" value="压缩空气"/>
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="rigorTime">开始时间</label>
							            <div >
							                <input type="text" class="form-control input-sm"  id="rigorTime" name="rigorTime" />
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="rigorEndTime">结束时间</label>
							            <div >
							                <input type="text" class="form-control input-sm"  id="rigorEndTime" name="rigorEndTime" />
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="rigorResult">试验结论</label>
							            <div >
							                <input type="text" class="form-control input-sm" id="rigorResult" name="rigorResult" value="合格"/>
							            </div>
							        </div>
						    	</form>
						    </div>
						    <table id="pressureRecordTable" class="table table-striped table-bordered nowrap" width="100%">
	                            <thead>
	                                <tr>
	                                    <th>起止点</th>
	                                    <th>设计压力</th>
	                                    <th>介质</th>
	                                    <th>强度压力</th>
	                                    <th>试验介质</th>
	                                    <th>试验时间</th>
	                                    <th>试验结论</th>
	                                    <th>严密压力</th>
	                                    <th>试验介质</th>
	                                    <th>试验时间</th>
	                                    <th>试验结论</th>
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
			    <div class="panel-body" id="pressure_panel_box">
			    	<div class="clearboth form-box">

	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:1; overflow-y:auto;" scrolling="no"></iframe>
	                  	</div>
	                  	<!-- <div class="text-center">
	                  		<iframe id="mainFrm2" class="iframe-report" style="width: 1050px; height: 860px;border:1; overflow-y:auto;" scrolling="no"></iframe>
	                  	</div> -->
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
    App.setPageTitle('试压记录 - 工程项目管理系统 ');
    $('.editBtn').addClass("hidden");
	$("#pressureForm").toggleEditState().styleFit();
	$("#pressureRecordForm").styleFit();
	
	if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	console.info(JSON.stringify(getProjectInfo()));
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	console.info('conUnit:'+projJson.managementOffice);
    	$('#constructionUnit').val(projJson.cuName); //施工单位
    	console.info('suname:'+projJson.suName);
    	$('#suName').val(projJson.suName);					   //监理公司
    }
	
	  //日期datepicker
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });
    $.getScript('assets/plugins/jedate/jedate.js').done(function() {
    	jeDate({
    		dateCell:"#strengthTime",
    	    format:"YYYY-MM-DD hh:mm",
    	    isTime:true,
    	    festival: true
    	})
    	jeDate({
    		dateCell:"#strengthEndTime",
    	    format:"YYYY-MM-DD hh:mm",
    	    isTime:true,
    	    festival: true
    	})
    	jeDate({
    		dateCell:"#rigorTime",
    	    format:"YYYY-MM-DD hh:mm",
    	    isTime:true,
    	    festival: true
    	})
    	jeDate({
    		dateCell:"#rigorEndTime",
    	    format:"YYYY-MM-DD hh:mm",
    	    isTime:true,
    	    festival: true
    	})
    });
    //签字加载方式
    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5").handleSignature();        	    	
    
    var cptPath = '<%=basePath%>';
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
	//打印预览窗口缩放调整
	var showReport1 = function(){
		var projName='',suName='',process='',slResultPage='',path='',constructionUnit='',inspectionDate='',selfCheckDate='',inspectionResult='',suOpinion='',checkDate='';
		var json=trSData.pressureTable.json;
   		if(json){
    	    projName = json.projName,			   //工程名称
    		suName=json.suName,					   //监理单位
    		process=json.process,			    	   //检验部位
    		slResultPage=json.slResultPage,    	   //页数
    		constructionUnit=json.constructionUnit,//施工单位
    		inspectionDate=json.inspectionDate,	   //报验日期
    		selfCheckDate=json.selfCheckDate,	   //自检日期
    		inspectionResult=json.inspectionResult,//查验结果
    		suOpinion=json.suOpinion,			   //监理单位意见
    		checkDate=json.checkDate;			   //检查日期
    	}else{//报验区取值
    		projName =$('#projName').val(),			    //工程名称
    		suName=$('#suName').val(),				    //监理单位
    		process=$('#process').val(),					//检验部位
    		slResultPage=$('#slResultPage').val(),      //页数
    		constructionUnit=$('#constructionUnit').val(),//施工单位
    		inspectionDate=$('#inspectionDate').val(),    //报验日期
    		selfCheckDate=$('#selfCheckDate').val(),	  //自检日期
    		inspectionResult=$('#inspectionResult').val(),//查验结果
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
		path='${path}';
    	src = cptPath+"/ReportServer?reportlet=pressure/pressure.cpt&projName=" + projName+"&suName="+suName+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate;
    	src = src+"&inspectionDate="+inspectionDate+"&checkDate="+checkDate+"&suOpinion="+suOpinion+"&process="+process+"&slResultPage="+slResultPage+"&inspectionResult="+inspectionResult;
    	src = src+"&path="+path+"&pcId="+$('#pcIdNew').val();
    	$("#mainFrm").attr("src",src);
    	
    	if($(".iframe-report").length > 0){
        	var fr = $(".iframe-report");
        	for(var i=0; i<fr.length; i++){
        		fr.eq(i).rescaleReportPanel();
        	}
        } 
    };
    
    showReport2 = function(){
    	
    	var projName='',projNo='',constructionUnit='',suName='',selfCheckDate='',checkDate='',otherCheckDate='';  	
    	if(trSData.pressureTable.json){
    		pcId=trSData.pressureTable.json.pcId;
    		projName = trSData.pressureTable.json.projName;
    		projNo=trSData.pressureTable.json.projNo;
    		meterial=trSData.pressureTable.json.meterial;//材质
    		grCaliber=trSData.pressureTable.json.grCaliber;//管径
    		length=trSData.pressureTable.json.length;//长度
    		constructionUnit = trSData.pressureTable.json.constructionUnit;
    		suName = trSData.pressureTable.json.suName;
    		selfCheckDate = trSData.pressureTable.json.selfCheckDate;
    		checkDate = trSData.pressureTable.json.checkDate;
    		testDate=trSData.pressureTable.json.testDate;//监捡日期
    		testUnit=trSData.pressureTable.json.testUnit;//监检单位
    	}else{
    		projName =$('#projName').val(),			      //工程名称
    		projNo =$('#projNo').val(),					  //工程编号
    		suName=$('#suName').val(),				      //监理单位
    		constructionUnit=$('#constructionUnit').val(),//施工单位
    		inspectionDate=$('#inspectionDate').val(),    //报验日期
    		checkData=$('#checkDate').val(),			  //监理检查日期
    		testUnit=$('#testUnit').val(),				  //监捡单位
    		testDate=$('#testDate').val(),				  //监捡日期
    		meterial=$('#meterial').val(),				  //材质
    		grCaliber=$('#grCaliber').val(),			  //管径
    		length=$('#length').val();					  //长度
    	}
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	projNo=encodeURIComponent(cjkEncode(projNo)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName));
    	length=encodeURIComponent(cjkEncode(length));
    	testUnit=encodeURIComponent(cjkEncode(testUnit)); //监捡单位
    	meterial=encodeURIComponent(cjkEncode(meterial));
    	//length=encodeURIComponent(cjkEncode(length)); //长度
    	inspectionClum="";
	
		if($("input[name='inspectionClum']:checked").val()){				 //查验结论
			inspectionClum="检查合格";
		}else if($("input[name='inspectionClum']:checked").val()){
			inspectionClum="纠错重报";
		};
		inspectionClum=encodeURIComponent(cjkEncode(inspectionClum));
    	var src = cptPath+"/ReportServer?reportlet=pressure/pressure1.cpt&projName="+projName+"&projNo="+projNo+"&pcId="+pcId+"&suName="+suName+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate+"&checkDate="+checkDate+"&testUnit="+testUnit;
    	src=src+"&length="+length+"&inspectionClum="+inspectionClum+"&testDate="+testDate+"&grCaliber="+grCaliber+"&meterial="+meterial;
    	src = src+"&path=${path}"+"&pcId="+$('#pcIdNew').val();
    	$("#mainFrm").attr("src",src); 
    	
    	if($(".iframe-report").length > 0){
        	var fr = $(".iframe-report");
        	for(var i=0; i<fr.length; i++){
        		fr.eq(i).rescaleReportPanel();
        	}
        } 
    };
    
     var showReport3 = function(){
    	var projName='',constructionUnit='',suName='';
    	
   		projName =$('#projName').val(),			      //工程名称
   		suName=$('#suName').val(),				      //监理单位
   		constructionUnit=$('#constructionUnit').val();//施工单位
   		
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName)); 
    	var src = cptPath+"/ReportServer?reportlet=pressure/pressure.cpt&projName="+projName+"&suName="+suName+"&constructionUnit="+constructionUnit;
    	src = src+"&path=${path}"+"&pcId="+$('#pcId').val();
    	$("#mainFrm").attr("src",src); 
    } 
    
    
    $.getScript('projectjs/inspection/pressure.js?v=1011').done(function () {
        pressure.init();
	});
    
  	//报验区点击放弃
	$('.giveupBtn').off().on('click',function(){
		//返回列表区
		$('#listTab').tab("show");
    })
    
     //报验区保存工程报验单
    $('.saveBtn').off().on("click",function(){
    	$('#pressureForm').cformSave('pressureTable',savePressureBack,true);
    })
    var savePressureBack=function(data){
		$('.editBtn').addClass("hidden");
    	$('#pressureForm').toggleEditState(false);
    	if(data!==false){
    		$('#pcIdNew').val(data);
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
	//记录区增加试压记录
    $(".addbtn").off().on("click",function(){
    	var rowsPart=[];
    	var t=$("#pressureRecordForm");
		if($('#pcIdNew').val()!==''){
	    	//开启表单验证
			if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') { 
	        	//报验单id
				var id=$('#pcIdNew').val();
		   		var json=t.serializeJson();
		   		console.info('json....'+json);
		   		json.pcId=id;
		   		rowsPart.push(json);
		   		
		   		//试压记录表格添加一条记录
	    	    mytable.rows.add(rowsPart).draw();
		   		$('#pressureAuditTable').selectRow(0);
		    		/* console.info('rowsPart...'+rowsPart);
		    	rows=$.merge(rows, rowsPart);
		    		console.info('rows...'+rows);
		    	rowData=rows;
		    		console.info('rowData...'+rowData); */
	            //如果通过验证, 则移除验证UI
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
  	var successBack1=function(){};
  	var successBack2=function(){};
  	
	$('.savebtn').off().on('click',function(){
		var dataObj={};
    	var json=$("#pressureRecordTable").DataTable().rows().data();
    	dataObj.list = []
		$.each(json, function(k,v){
			dataObj.list.push(v);
		})
		var id=$("#pcIdNew").val();
		dataObj.pcId=id;
		var data=JSON.stringify(dataObj);
   		 
		if(response){
			response.abort();
  	    }
 		var response = $.ajax({
			url: "pressure/savePressureRecord",
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
	})
    function successBack(){
		showReport2();
	} ;
    
  	//记录区点击放弃
	$('.giveupbtn').off().on('click',function(){
		var len=$('#pressureRecordTable').find('tr.selected').length;
		 if(len>0){
			 var rows = $("#pressureRecordTable").DataTable().rows( '.selected' ).remove().draw();
			 $('#pressureRecordTable').selectRow(0);
		 }else{
			 $("body").cgetPopup({
			 	title: '提示信息',
				content: '请选择要删除的记录!',
				accept: delDataPressure,
				icon: 'fa-exclamation-circle',
			 });
		 }
    })
    function delDataPressure(){};
    
    
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