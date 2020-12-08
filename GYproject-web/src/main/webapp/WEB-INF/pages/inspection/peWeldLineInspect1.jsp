<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- altimetricSurvey.jsp -->
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
                    <input type="hidden" id="actionName" value="${actionName }"/>
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表区</a></li>
		                <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
		                <li class="" id="addTab"><a href="#default-tab-2" id="signTab"  data-toggle="tab">签字区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" id="">
               		<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="projectCheckListTable" width="100%">
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
	                   	<div class="tab-pane fade btn-top" id="default-tab-2">
	                       	<div class="toolBtn f-r p-b-10 update-show">
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole savebtn" >保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">放弃</a>
						    </div>
						    <div class="clearboth form-box">
						    	<input type="hidden" class="imgUrl" value="${imgUrl}"/>
								<form class="form-horizontal" id="signForm" data-parsley-validate="true" action="weldLineInpect/saveSign">							
								    <input type="hidden" id="pcDesId" name="pcDesId" value="${checkType}"/> 
									<input type="hidden" id="pcId" name="pcId" class="addClear"/>
									<input type="hidden" id="projId" name="projId" >
								 	<div class="form-group  col-sm-12">
										<label class="control-label" for="projNoe">工程编号</label>
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
									<!-- 
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="process">施工工序</label>
										<div> 
											<input type="text" class="form-control input-sm field-editable addClear"  id="process" name="process"  />
										</div>
									</div>
									 -->
									<div class="form-group col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									  <div class="form-group  col-sm-12">
										<label class="control-label" for="suName">监理公司</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  value="" id="suName" name="suName"  />
										</div>
								    </div>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectionDate">验收日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear" value="" id="inspectionDate" name="inspectionDate" data-parsley-required="true" />
										</div>
									</div>
						    		<div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="inspectionResult" id="inspectionResult" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div> 
					  			    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="meltConnectType">连接类型</label>
										<div>
											<select class="form-control field-editable input-sm" id="meltConnectType"   name="meltConnectType"  data-parsley-required="true">
												<c:forEach var="meltConnectType" items="${meltConnectTypeEnum }">
													<option value="${meltConnectType.value }">${meltConnectType.message }</option>
												</c:forEach>
												<!-- <option value="0">热熔类型</option>
												<option value="1">电熔类型</option> -->
											</select>
							               <!--  <input type="text" class="form-control input-sm" id="hotMeltConnect"   name="hotMeltConnect"  data-parsley-required="true"/> -->
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="peWeldSpec">焊件规格</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm" id="peWeldSpec"   name="peWeldSpec"  data-parsley-required="true"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="welderName">焊工姓名</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm" id="welderName"   name="welderName"  data-parsley-required="true"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="pipeManufactor">管材厂家</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm" id="pipeManufactor"   name="pipeManufactor"  data-parsley-required="true"/>
							            </div>
									</div>
					  			  
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="fieldsRepresent">现场代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="fieldsRepresent" name="fieldsRepresent" value="">
											<input type="hidden"  id="fieldsRepresent_post" name="fieldsRepresent_post" value="${fieldsRepresentPost }">
											<img class="fieldsRepresent" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="fieldsRepresentDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear" value="" id="fieldsRepresentDate" name="fieldsRepresentDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="suJgj">现场监理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden"  id="suJgj_post" name="suJgj_post" value="${suJgjPost }">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="suJgjSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear" value="" id="suJgjSignDate" name="suJgjSignDate"  />
										</div>
									</div>
									
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="projectLeader">项目负责人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="projectLeader" name="projectLeader" value="">
											<input type="hidden"  id="projectLeader_post" name="projectLeader_post" value="
">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="plSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear" value="" id="plSignDate" name="plSignDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="builder">施工员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
											<input type="hidden"  id="builder_post" name="builder_post" value="
">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="builderSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear" value="" id="builderSignDate" name="builderSignDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionQc">质检员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQc" name="constructionQc" value="">
											<input type="hidden"  id="constructionQc_post" name="constructionQcr_post" value="${constructionQcPost }">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="cuQcSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear" value="" id="cuQcSignDate" name="cuQcSignDate"  />
										</div>
									</div>
								</form>
							</div>
	                   	</div>
	                   	<div class="tab-pane fade  btn-top" id="default-tab-3">
	                   		<div class="toolBtn f-r p-b-10 editbtn" >
			                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole auditAddBtn">增加</a>
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole auditSavebtn">保存</a>
						   		 <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole deleteBtn">删除</a>
						    </div>
						    <div class="clearboth form-box">
		                   		<form class="form-horizontal" id="weldLineInpectForm2" data-parsley-validate="true" action="">
		                   		
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="peWeldLineNo">焊缝编号</label>
										<div>
							                <input type="text" class="form-control input-sm" id="peWeldLineNo"   name="peWeldLineNo"  data-parsley-required="true"/>
							            </div>
									</div>
									<!-- hotMelt start -->
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="cuDate">施工日期</label>
										<div>
							                <input type="text" class="form-control datepicker-default input-sm" id="cuDate"   name="cuDate" data-parsley-required="true" />
							            </div>
									</div>
									
									<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="curlCheck">卷边检查</label>
										<div>
							                <input type="text" class="form-control input-sm" id="curlCheck"   name="curlCheck" data-parsley-required="true" />
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="weldRingWidthB">焊环环宽度B</label>
										<div>
							                <input type="text" class="form-control input-sm" id="weldRingWidthB"   name="weldRingWidthB" data-parsley-required="true" />
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="weldRingHeightH">焊环高度H</label>
										<div>
							                <input type="text" class="form-control input-sm" id="weldRingHeightH"   name="weldRingHeightH" data-parsley-required="true" />
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="weldRingSeamH">焊环环缝高度h</label>
										<div>
							                <input type="text" class="form-control input-sm" id="weldRingSeamH"   name="weldRingSeamH" data-parsley-required="true" />
							            </div>
									</div>
		                   			
		                   			<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="wrongEdge">错边量</label>
										<div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="isLeakageWeld">有无漏焊</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isLeakageWeld" value="1" />有
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isLeakageWeld" value="0" checked="checked"/>无
				      						 </label>
			   							</div>
										<!-- <div>
							                <input type="text" class="form-control input-sm" id="isLeakageWeld"   name="isLeakageWeld"  data-parsley-required="true" />
							            </div> -->
									</div>
		                   			<div class="form-group col-md-6 col-sm-12 hotMelt">
										<label class="control-label" for="isCurlDefect">是否有缺陷</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isCurlDefect" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isCurlDefect" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<!-- <div>
							                <input type="text" class="form-control input-sm" id="isCurlDefect"   name="isCurlDefect"  data-parsley-required="true"/>
							            </div> -->
									</div>
		                   			
									<!-- hotMelt end -->
									
									<!-- electricMelt start -->
									<div class="form-group col-md-6 col-sm-12 electricMelt">
										<label class="control-label" for="isComplete">管材管件完整</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isComplete" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isComplete" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<!-- <div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div> -->
									</div>
									
									<div class="form-group col-md-6 col-sm-12 electricMelt">
										<label class="control-label" for="isCoaxial">是否同轴</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isCoaxial" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isCoaxial" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<!-- <div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div> -->
									</div>
									<div class="form-group col-md-6 col-sm-12 electricMelt">
										<label class="control-label" for="isScratch">是否有刮痕</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isScratch" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isScratch" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<!-- <div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div> -->
									</div>
									<div class="form-group col-md-6 col-sm-12 electricMelt">
										<label class="control-label" for="isHoleCharging">观察孔冒料</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isHoleCharging" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isHoleCharging" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
			   	
										<!-- <div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div> -->
									</div>
									<!-- <div class="form-group col-md-6 col-sm-12 electricMelt">
										<label class="control-label" for="wrongEdge">管材管件完整</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isBidding" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isBidding" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div>
									</div> -->
									<div class="form-group col-md-6 col-sm-12 electricMelt">
										<label class="control-label" for="isHaveMelts">是否有熔融物</label>
										<div>
											<label>
				       							<input type="radio" class="" name="isHaveMelts" value="1" />是
				      						 </label>
				      						 <label>
				     						  	<input type="radio" class="" name="isHaveMelts" value="0" checked="checked"/>否
				      						 </label>
			   							</div>
										<!-- <div>
							                <input type="text" class="form-control input-sm" id="wrongEdge"   name="wrongEdge" data-parsley-required="true" />
							            </div> -->
									</div>
									
									
									<!-- electricMelt end -->
		                   			
		                   			
			                       	<table id="weldLineInpectHotMeltListTable" class="table table-striped table-bordered nowrap hotMeltTable" width="100%">
			                            <thead >
			                                <tr>
			                                    <th>焊缝编号</th>
			                                    <th>有无漏焊</th>
			                                    <th>是否有缺陷</th>
			                                    <th>卷边切除检查</th>
			                                    <th>焊环环的宽度B</th>
			                                    <th>环的高度H</th>
			                                    <th>环缝高度h</th>
			                                    <th>错边量</th>
			                                    <th>施工日期</th>
			                                </tr>
			                            </thead>
			                            
			                       	</table>
			                       	<table id="weldLineInpectElectricMeltListTable" class="table table-striped table-bordered nowrap electricMeltTable " width="100%">
			                            <thead class="">
			                                <tr>
			                                    <th>焊缝编号</th>
			                                    <th>管材管件完整</th>
			                                    <th>是否有熔融物</th>
			                                    <th>是否同轴</th>
			                                    <th>是否有刮痕</th>
			                                    <th>观察孔冒料</th>
			                                    <th>施工日期</th>
			                                </tr>
			                            </thead>
			                            
			                       	</table>
		                       	</form>
		                    </div>
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
			    <div class="panel-body" id="altimetric_survey_audit_panel_box">
	                 <div class="clearboth form-box">
	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 1123px; height: 800px;border:1; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('PE管焊缝检查- 工程项目管理系统 ');
    $("#weldLineInpectForm").toggleEditState().styleFit();
    $("#weldLineInpectForm2").toggleEditState().styleFit();
    
    $('.update-show').addClass('hidden');
    $('.editBtn').addClass('hidden');
    
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
    
  	//签字加载方式
    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5").handleSignature();  
    
   //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 
   //签字区保存功能
    $(".savebtn").on("click",function(){
    	$("#weldLineInpectForm").cformSave("peWeldLineInpectTable",saveBack,false);
    });
    
    function saveBack(data){
    	$('.update-show').addClass("hidden");
    	$('#weldLineInpectForm').toggleEditState(false);
    	//保存签字信息
    	
         if(data!==false){
        	$('#pcIdNew').val(data);
        	$('#pcId').val(data);
        }
        var pcId = data,
		projNo = $("#projNo").val();
		projId = $("#projId").val(); 
		
		if(_inApk && $(".attach-images-list").find(".new-image").length){
			preImagesUpload($(".attach-images-list .uploadBtn"), projId, projNo, getStepId(), pcId);
		};
        
		showReport();
    	
    }
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
   
   //连接类型默认为热熔连接
    var meltConnectType = "1";
   //页面初始
   $("#meltConnectType").val("1");
   	$(".electricMelt").addClass("hidden");
	$(".hotMelt").removeClass("hidden");
	$(".hotMeltTable").removeClass("hidden");
	$(".electricMeltTable").addClass("hidden");
    $("#meltConnectType").change(function(){
    	if($(this).val()=='1'){
    		meltConnectType="1";
    		$(".electricMelt").addClass("hidden");
    		//清空电熔值
    		$(".electricMelt input[type='text'").val("");
    		
    		$(".hotMelt").removeClass("hidden");
    		$(".hotMeltTable").removeClass("hidden");
    		$(".electricMeltTable").addClass("hidden");
    		$("#weldLineInpectHotMeltListTable_wrapper").removeClass("hidden");
    		
    		$("#weldLineInpectElectricMeltListTable_wrapper").addClass("hidden");
    		console.info($('#weldLineInpectElectricMeltListTable_wrapper'));
    		console.info($('#weldLineInpectElectricMeltListTable_wrapper'));
    	}else{
    		meltConnectType="2";
    		$(".electricMelt").removeClass("hidden");
    		$(".hotMelt").addClass("hidden");
    		//清空热熔值
    		$(".hotMelt input[type='text'").val("");
    		$(".hotMeltTable").addClass("hidden");
    		$(".electricMeltTable").removeClass("hidden");
    		$("#weldLineInpectHotMeltListTable_wrapper").addClass("hidden");
    		$("#weldLineInpectElectricMeltListTable_wrapper").removeClass("hidden");
    		console.info($('#weldLineInpectElectricMeltListTable_wrapper'));
    		console.info($('#weldLineInpectElectricMeltListTable_wrapper'));
    	}
    	handlePeWeldLineInpectList();
	
    	showReport();
    });
    
    $.getScript('projectjs/inspection/pe-weld-line-inpect.js?v=1011').done(function () {
    	weldLineInpect.init();
	});
   
  	//加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var cptPath = '<%=basePath%>';
  	//加载列表区和签字区的cpt文件
    var showReport = function(){
        var json=trSData.peWeldLineInpectTable.json;
        console.log(json);
        //列表区的cpt文件数据从trSData中获取
        if(json){
        	projName=json.projName;
       	    suName =json.suName;
            constructionUnit =json.constructionUnit;
            peWeldSpec=json.peWeldSpec;
            pipeManufactor = json.pipeManufactor;
            welderName = json.welderName;
        	pcId = json.pcId;
        }else{
        	//签字区保存后不刷新页面,cpt文件中数据从签字区获取
        	projName=$('#projName').val();
    	    suName = $("#suName").val();
            constructionUnit = $("#constructionUnit").val();
            peWeldSpec= $('#peWeldSpec').val();
            pipeManufactor=$("#pipeManufactor").val(); 
            welderName = $('#welderName').val();
    	    pcId = $("#pcId").val();
        }

    	    //以下处理为解决中文乱码
    	    projName=encodeURIComponent(cjkEncode(projName));
        	suName = encodeURIComponent(cjkEncode(suName));
        	constructionUnit = encodeURIComponent(cjkEncode(constructionUnit));
        	welderName=encodeURIComponent(cjkEncode(welderName));
        	peWeldSpec=encodeURIComponent(cjkEncode(peWeldSpec));
        	pipeManufactor=encodeURIComponent(cjkEncode(pipeManufactor));
        
        var meltConnectType = $("#meltConnectType").val();
        //cpt路径及参数
        var src="";
        if(meltConnectType=='1'){
        	src=cptPath+"/ReportServer?reportlet=inspection/peWeldLineInpectHotMelt.cpt";
        }else{
        	src=cptPath+"/ReportServer?reportlet=inspection/peWeldLineInpectElectricMelt.cpt"; 
        }
        src = src + "&pcId="+pcId+"&projName="+projName+"&peWeldSpec="+peWeldSpec+"&welderName="+welderName+"&pipeManufactor="+pipeManufactor+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src); 
    }; 
    
</script>

<!-- ================== END PAGE LEVEL JS ================== -->