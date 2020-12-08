<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- ================== END PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jedate/skin/jedate.css" rel="stylesheet" />
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
					<h4 class="panel-title">碰口申请</h4>
				</div>
				<div class="panel-body">
					<div class="tab-content">
						<div class="tab-pane fade in btn-top active" id="touch-plan">
							<div class="toolBtn f-r p-b-10 hidden">
								<a href="javascript:;" class="btn btn-confirm btn-sm saveBtn">保存</a>
								<a href="javascript:;" class="btn btn-warn btn-sm giveupBtn">放弃</a>
							</div>
							<div class="returnBtn f-r p-b-10 hidden">
								<a href="javascript:;" class="btn btn-warn btn-sm giveupBtn">返回</a>
							</div>
							<div class="clearboth form-box m-r-25">
								<form class="form-horizontal" id="touchPlanForm">
									<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
									<input type="hidden" name ="projId" id="projId" value=""/>
									<input type="hidden" name ="tpId"  id="tpId"/>
									<input type="hidden" name ="projNo"  id="projNo"/>
									<input type="hidden" name="isAudit" id="isAudit" value="${isAudit}">
									<input type="hidden" class="auditLevel" name="auditLevel"  value="${auditLevel}">
									<input type="hidden" id="menuDes" name="menuDes">
									<input type="hidden" id="drawUrl" name="drawUrl">
									<input type="hidden" id="drawUrl1" name="drawUrl1" value="${drawUrl1}">
									<div class="form-group col-xs-12">
										<label class="control-label" for="">工程名称</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="projName" name="projName" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">合同编号</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="conNo" name="conNo" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">分包单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="constructionUnit" name="constructionUnit" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">工程规模</label>
										<div>
											<input type="text"
												class="form-control input-sm field-not-editable" id="projScaleDes" name="projScaleDes" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">施工地点</label>
										<div>
											<input type="text"
												class="form-control input-sm field-not-editable" value="" id="touchAddr" name="touchAddr" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">项目经理</label>
										<div>
											<input type="text"
												class="form-control input-sm field-not-editable" value="" id="projManager" name="projManager"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">联系电话</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="projManagerPh" name="projManagerPh"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">现场负责人</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="fieldPrincipal" name="fieldPrincipal"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">联系电话</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable"	value="" id="fieldPrincipalPh" name="fieldPrincipalPh"/>
										</div>
									</div>
									<div class="form-group col-xs-12">
										<label class="control-label" for="">碰口部位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable"	value="" id="touchPart" name="touchPart"/>
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="">碰口方案简图</label>
										<div class=""> 
						         			<input type="text" class="form-control input-sm " readonly="readonly" id="drawName" name="drawName"/>
					         		        <a class="btn btn-sm btn-primary Search_Button" href="javascript:" id="Search_Button">查看</a>
					         		    </div>
									</div>
									<div class="form-group col-xs-12">
										<label class="control-label" for="">碰口说明</label>
										<div>
											<textarea class="form-control field-not-editable" name="touchPartAddtion" id="touchPartAddtion" rows="3" cols="" data-parsley-maxlength="200"></textarea>
										</div>
									</div>
									<!-- 四个长度 -->
									<div class="form-group col-sm-6 col-xs-12">
										<label class="control-label" for="">机动车道(米)</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable " id="vehicleRoad" name="vehicleRoad" data-parsley-type='number' />
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12">
										<label class="control-label" for="">非机动车道(米)</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable "  id="nonVehicleRoad" name="nonVehicleRoad" data-parsley-type='number' />
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12">
										<label class="control-label" for="">协调道路(米)</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable " id="coordinateRoad" name="coordinateRoad" data-parsley-type='number' />
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12">
										<label class="control-label" for="">破坏绿地(米)</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" id="greenRoad" name="greenRoad" data-parsley-type='number' />
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12">
										<label class="control-label" for="">申请开挖时间</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable datepicker-default" value="" id="applyDigDate" name="applyDigDate" />
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12">
										<label class="control-label" for="">申请碰口时间</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable datepicker-default" value="" id="applyTpDate" name="applyTpDate" />
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12">
										<label class="control-label signature-tool" for="">负责人签字</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white"	id="signBtn_4"><i class="fa fa-pencil"></i></a> 
											<input type="hidden" class="sign-data-input " id="constructionPrincipal" name="constructionPrincipal" value="">
											<img class="" alt="" src="images/sign-1.png" style="height: 30px"> <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12">
										<label class="control-label" for="">申请日期</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" id="constructionDate" name="constructionDate" />
										</div>
									</div>
									<div class="form-group col-xs-12">
										<label class="control-label" for="">其它损坏</label>
										<div>
											<textarea class="form-control field-editable" name="remark" id="remark" rows="1" cols="" data-parsley-maxlength="200"></textarea>
										</div>
									</div>
									<div class="form-group col-xs-12 show">
										<label class="control-label" for="">监理意见</label>
										<div>
											<textarea class="form-control field-editable" data-parsley-required="true" name="suView" id="suView" rows="2" cols="" data-parsley-maxlength="50"></textarea>
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12 show">
										<label class="control-label signature-tool  suJgjLabel" for="">负责人签字</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white"	id="signBtn_3"><i class="fa fa-pencil"></i></a> 
											<input type="hidden" class="sign-data-input show" id="suJgj" name="suJgj" value="">
											<input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${suJgj}">
											<img class="" alt="" src="images/sign-1.png" style="height: 30px"> <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12 show">
										<label class="control-label" for="">批准日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default" data-parsley-required="true" id="suJgjDate" name="suJgjDate" />
										</div>
									</div>
									
									<div class="form-group col-sm-12 col-xs-12 show2">
										<label class="control-label" for="">甲方代表意见</label>
										<div>
											<textarea class="form-control field-editable" data-parsley-required="true" name="acceptanceView" id="acceptanceView" rows="2" cols="" data-parsley-maxlength="50"></textarea>
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12 show2">
										<label class="control-label signature-tool  appeptanceJgjLabel" for="">负责人签字</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white"	id="signBtn_4"><i class="fa fa-pencil"></i></a> 
											<input type="hidden" class="sign-data-input show2" id="appeptanceJgj" name="appeptanceJgj" value="">
											<input type="hidden" id="appeptanceJgj_postType" name="appeptanceJgj_postType" value="${director}">
											<img class="" alt="" src="images/sign-1.png" style="height: 30px"> <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12 show2">
										<label class="control-label" for="">批准日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default" data-parsley-required="true" id="acceptanceDate" name="acceptanceDate" />
										</div>
									</div>
									<div class="form-group col-xs-12 show3">
										<label class="control-label" for="">生产处意见</label>
										<div>
											<textarea class="form-control field-editable" data-parsley-required="true" name="cuSpdView" id="cuSpdView" rows="2" cols="" data-parsley-maxlength="50"></textarea>
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12 show3">
										<label class="control-label signature-tool cuSpdPrincipalLabel" for="">负责人签字</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white"	id="signBtn_2"><i class="fa fa-pencil"></i></a> 
											<input type="hidden" class="sign-data-input btn-white" id="cuSpdPrincipal" name="cuSpdPrincipal" value="">
											<input type="hidden" id="cuSpdPrincipal_postType" name="cuSpdPrincipal_postType" value="${director}">
											<img class="" alt="" src="images/sign-1.png" style="height: 30px"> <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12 show3">
										<label class="control-label" for="">批准日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default" data-parsley-required="true" id="cuSpdDate" name="cuSpdDate" />
										</div>
									</div>
									<div class="form-group col-xs-12 show4">
										<label class="control-label" for="">燃气公司意见</label>
										<div>
											<textarea class="form-control field-editable" data-parsley-required="true" name="ongcView" id="ongcView" rows="2" cols="" data-parsley-maxlength="50"></textarea>
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12 show4">
										<label class="control-label signature-tool ongcPrincipalLabel" for="">负责人签字</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white"	id="signBtn_1"><i class="fa fa-pencil"></i></a> 
											<input type="hidden" class="sign-data-input btn-white" id="ongcPrincipal" name="ongcPrincipal" value="">
											<input type="hidden" id="ongcPrincipal_postType" name="ongcPrincipal_postType" value="${ongcPrincipal}">
											<img class="" alt="" src="images/sign-1.png" style="height: 30px"> <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-sm-6 col-xs-12 show4">
										<label class="control-label" for="">批准日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default" data-parsley-required="true" id="ongcDate" name="ongcDate" />
										</div>
									</div>
									 <div class="form-group col-sm-6 col-xs-12 show4">
										<label class="control-label" for="">确定碰口时间</label>
										<div>
											<input type="text" class="form-control input-sm field-editable "  id="changeTpDate" name="changeTpDate"  data-parsley-required="true" />
										</div>
									</div> 
									<div class="form-group col-sm-6 col-xs-12 show4">
										<label class="control-label" for="">确定开挖时间</label>
										<div>
											<input type="text" class="form-control input-sm field-editable all"  id="changeDigDate" name="changeDigDate" />
										</div>
									</div>  
								</form>
							</div>
							<h4>实际开挖道路及面积:</h4>
							<div class="clearboth form-box m-t-5">
								<form id="digForm">
									<table id="digTable" class="table table-striped table-bordered nowrap field-not-editable" width="100%">
							        	<thead>
								            <tr>
								                <th width="80px">路况</th>
								                <th width="50px">长（米）</th>
								                <th width="50px">宽（米）</th>
								                <th width="50px">深（米）</th>
								                <th width="30px"></th>
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
    App.setPageTitle('碰口方案 - 工程项目管理系统');
    //是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    };
    $.getScript('projectjs/constructmanage/touch_plan_audit_page.js').done(function () {
		connectPlan.init();
	});
    
    //日期datepicker
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });

    //签字
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6");
    	signatures.handleSignature(false);        	    	
    });
    
    //反转
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %> 
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}

    //加载打印预览
    var showReport = function(){
    	
    	var conNo = encodeURIComponent(cjkEncode($("#conNo").val())),                          //合同编号
	    	projId = encodeURIComponent(cjkEncode($("#projId").val())),
			imgUrl = encodeURIComponent(cjkEncode($(".imgUrl").val())),
    		drawUrl1 = encodeURIComponent(cjkEncode($("#drawUrl1").val())),
	    	projName = encodeURIComponent(cjkEncode($("#projName").val())),                    //工程名称
	    	constructionUnit = encodeURIComponent(cjkEncode($("#constructionUnit").val())),    //施工单位
	    	projScaleDes = encodeURIComponent(cjkEncode($("#projScaleDes").val())),            //工程规模
	    	projManager = encodeURIComponent(cjkEncode($("#projManager").val())),              //项目经理
	    	projManagerPh = encodeURIComponent(cjkEncode($("#projManagerPh").val())),          //经理电话
	    	touchAddr = encodeURIComponent(cjkEncode($("#touchAddr").val())),                  //碰口地点
	    	fieldPrincipal = encodeURIComponent(cjkEncode($("#fieldPrincipal").val())),        //现场负责人
	    	fieldPrincipalPh = encodeURIComponent(cjkEncode($("#fieldPrincipalPh").val()));    //现场负责人电话	    	
	    	touchAddr = encodeURIComponent(cjkEncode($("#touchAddr").val())),					//施工地点	    	
	    	vehicleRoad = encodeURIComponent(cjkEncode($("#vehicleRoad").val())),					//机动车道(米)
	    	nonVehicleRoad = encodeURIComponent(cjkEncode($("#nonVehicleRoad").val())),			//非机动车道(米)
	    	coordinateRoad = encodeURIComponent(cjkEncode($("#coordinateRoad").val())),			//协调道路(米)
	    	menuDes=encodeURIComponent(cjkEncode("碰口申请")),
	    	greenRoad = encodeURIComponent(cjkEncode($("#greenRoad").val()));					//破坏绿地(米)
	    	var tpId = $("#tpId").val();
	    	if(tpId === '' || tpId === null || tpId === undefined){
	    		tpId = '#';
	    	}
	    	var roads = '';
	    	var json = $("#digForm").getDTFormData();
			for(var i=0;i<json.length;i++){
				var str = " "+json[i].drRoads+"：长 "+(json[i].drLength===null?" ":json[i].drLength)+" 米  宽 "+json[i].drWidth+" 米  深 "+json[i].drDepth + " 米";
				if(i !== json.length){
					str=str+"，";
				}
				roads = roads+str;
			}
			roads = encodeURIComponent(cjkEncode(roads));
    	var src = "<%=basePath%>/ReportServer?reportlet=constructmanage/touchPlan.cpt&conNo="+conNo+"&projName="+projName+"&constructionUnit="+constructionUnit+"&projManager="+projManager+"&projManagerPh="+projManagerPh+"&fieldPrincipal="+fieldPrincipal+"&fieldPrincipalPh="+fieldPrincipalPh;
    	
    	src = src +"&vehicleRoad="+vehicleRoad+"&nonVehicleRoad="+nonVehicleRoad+"&coordinateRoad="+coordinateRoad+"&greenRoad="+greenRoad+"&touchAddr="+touchAddr+"&tpId="+tpId+"&roads="+roads+"&projScaleDes="+projScaleDes+"&projId="+projId+"&imgUrl="+imgUrl+"&drawUrl1="+drawUrl1+"&menuDes="+menuDes;
    	$("#mainFrm").attr("src",src); 
    };
    //showReport(); 
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
	    	
    //碰口方案保存按钮
	  $(".saveBtn").off("click").on("click",function(){
		    var viewform = $("#touchPlanForm");
	        var tableform = $("#digForm");
	        //开启表单验证
	        if (tableform.parsley().isValid() && viewform.parsley().isValid()) {
	        	
	        	
	        	//验证必签签字是否已签
    	        var signtools =$('#touchPlanForm').find(".signature-tool.sign-require"),
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
   		        	$("body").cgetPopup({
   		            	title: "提示信息",
   		            	content: "请完成签字!",
   		            	accept: popClose,
   		            	chide: true,
   		            	icon: "fa-warning",
   		            	newpop: 'new'
   		            });
    	        	return false;
    	        }
            	
	        	var mdata = tableform.getDTFormData();
	           	var viewdata = viewform.serializeJson();
	        	console.info("----");
	           	console.info(viewdata);
	           	
	        	viewdata.digRoads = mdata;
	        	console.info("1----");
	        	console.log(viewdata);
	        	$.ajax({
	                type: 'POST',
	                url: 'touchPlan/saveTouchPlanAll',
	                contentType: "application/json;charset=UTF-8",
	                dataType : "json",
	                data: JSON.stringify(viewdata),
	                success: function (data) {
	                	console.info("========="+data);
	                	var content = "数据保存成功！";
	                	if(data === "false"){
	                		content = "数据保存失败！";
	                	}else{
	                		//刷新帆软报表
		                	showReport();
		                	$("#touchPlanForm,#digForm").toggleEditState(false).styleFit();
		            		$(".toolBtn").addClass("hidden");
		            		$(".returnBtn").removeClass("hidden");
		            		if($("#tpDate") && $("#tpDate").val()!==null && $("#tpDate").val()!==''){
		            			$('#sidebar-nav [data-mid="120404"]').parent().removeClass("disabled");
		            		}else{
		            			$('#sidebar-nav [data-mid="120404"]').parent().addClass("disabled");
		            		}
	                	}
	                	alertInfo(content);
	                },
	                error: function (jqXHR, textStatus, errorThrown) {
	                    console.warn("碰口方案保存失败！");
	                    console.info(jqXHR);
	                    console.info(errorThrown);
	                }
	            });
              	//如果未通过验证, 则加载验证UI
              	tableform.parsley().validate();
              	viewform.parsley().validate();
	        }else{
	          	 //如果未通过验证, 则加载验证UI
	          	 tableform.parsley().validate();
	          	 viewform.parsley().validate();
	          }
	    	
	    }); 
    
	  
	//开挖路况保存
	  $(".digRoadSaveBtn").off("click").on("click",function(){
		  if($("#tpId1").val()===""){
	    		alertInfo("请先保存碰口方案！");
	    		return;
	    	};

	       var totalData = [];
	       for(var i=1;i<6;i++){
	    	   //判断复选框是否选中
	    	   if($("#drRoads"+i).attr("checked")){
		    	   var data = $("#dig"+i).serializeJson();
		    	   totalData.push(data);
	    	   }
	       }
	       if(totalData.length<1){
	    	   alertInfo("未填写开挖路况");
	    	   return false;
	       }else{
	    	   $.ajax({
			          url: "touchPlan/saveDigRoad",
			          type: "POST",
			          timeout : 5000,
			          contentType: "application/json;charset=UTF-8",
			          data: JSON.stringify(totalData),
			          success: function (data) {
			        	  if (data) {   	        		  
			        		  $("body").cgetPopup({
			                  	title: "提示信息",
			                  	content: "数据保存成功!",
			                  	accept: popClose,
			                  	chide: true,
			                  	icon: "fa-check-circle",
			                  	newpop: 'new'
			                  }); 
			        		  
			        	  }else{
			        		  $("body").cgetPopup({
			                  	title: "提示信息",
			                  	content: "数据保存失败, 请重试! " ,
			                  	accept: popClose,
			                  	chide: true,
			                  	icon: "fa-exclamation-circle",
			                  	newpop: 'new'
			                  });  
			        	    }
			        	  }
			          });  
	                }
	       $("#dig-road").toggleEditState(false).styleFit();
   		   $(".digRoadSaveBtn").addClass("hidden");
	       showReport(); 
	       console.info(JSON.stringify(totalData));
		});
  
   // 表单样式对齐
	/*   $("#touchPlanForm").toggleEditState(true).styleFit();
	  $("#digRoadRorm").toggleEditState(true)//.styleFit(); */
	  
	  
	//图片查看功能
    $('.Search_Button').off("click").on("click",function(){  
    	console.info($("#drawUrl").val());
    	$("body").cgetPopup({ 
			title: '查看图片',
			content: '<div class="preview-box"><img src="attachments/' +$("#drawUrl").val() + '" class="preview-image"></div>',
			accept: function(){},
			atext: '关闭',
			chide: true,
			size: 'large',
			icon: 'fa-pencil-square-o'
		});
    });
	  
  	$.getScript('assets/plugins/jedate/jedate.js').done(function() {
    	jeDate({
    		dateCell:"#changeTpDate",
    	    format:"YYYY-MM-DD hh:mm:ss",
    	    isTime:true,
    	    festival: true
    	})
    	jeDate({
    		dateCell:"#changeDigDate",
    	    format:"YYYY-MM-DD hh:mm:ss",
    	    isTime:true,
    	    festival: true
    	})
    }); 
  	
  	$("#tpDate").change();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->