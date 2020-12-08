<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
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
					 <h4 class="panel-title">碰口申请</h4>
				</div>
				<div class="panel-body">
					<div class="tab-content">
						<div class="tab-pane fade in btn-top active" id="touch-plan">
							<div class="toolBtn f-r p-b-10 ">
								<a href="javascript:;" class="btn btn-confirm btn-sm checkRole saveBtn">保存</a>
								<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole updataBtn" >修改</a>
								<a href="javascript:;" class="btn  btn-primary btn-sm m-l-5 checkRole hidden get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>
							</div>
							<div class="clearboth form-box">
								<form class="form-horizontal" id="touchPlanForm" action="touchPlan/saveTouchPlanFile" method="POST" enctype="multipart/form-data" data-parsley-validate="true">
									<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
									<input type="hidden" name ="projId" id="projId"/>
									<input type="hidden" name ="tpId"  id="tpId"/>
									<input type="hidden" name ="projNo"  id="projNo"/>
									<input type="hidden" id="result" name="result">
									<input type="hidden" id="drawUrl" name="drawUrl">
									<input type="hidden" id="menuDes" name="menuDes">
									<input type="hidden" id="drawUrl1" name="drawUrl1" value="${drawUrl1}">
									
									<div class="form-group col-xs-12">
										<label class="control-label" for="">工程名称</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="projName" name="projName" data-parsley-maxlength="200"/>
										</div>
									</div>
									<div class="form-group col-xs-12">
										<label class="control-label" for="">合同编号</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="conNo" name="conNo" data-parsley-maxlength="50"/>
										</div>
									</div>
									<div class="form-group col-xs-12">
										<label class="control-label" for="projScaleDes">工程规模</label>
										<div>
											<textarea class="form-control field-not-editable" name="projScaleDes" id="projScaleDes" rows="2" cols=""></textarea>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 col-xs-12">
										<label class="control-label" for="">分包单位</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="constructionUnit" name="constructionUnit" data-parsley-maxlength="50"/>
										</div>
									</div>
									
									<div class="form-group col-md-6 col-sm-12 col-xs-12">
										<label class="control-label" for="">施工地点</label>
										<div>
											<input type="text"
												class="form-control input-sm field-editable" value="" id="touchAddr" name="touchAddr" data-parsley-maxlength="200"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 col-xs-12">
										<label class="control-label" for="">项目经理</label>
										<div>
											<input type="text"
												class="form-control input-sm field-editable" value="" id="projManager" name="projManager" data-parsley-required="true" data-parsley-maxlength="20"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 col-xs-12">
										<label class="control-label" for="">联系电话</label>
										<div>
											<input type="text" class="form-control input-sm field-editable" value="" id="projManagerPh" name="projManagerPh" data-parsley-required="true" data-parsley-maxlength="11"/>
										</div>
									</div>
									<!-- <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">现场负责人</label>
										<div>
											<input type="text" class="form-control input-sm field-editable" value="" id="fieldPrincipal" name="fieldPrincipal" data-parsley-required="true" data-parsley-maxlength="20"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">联系电话</label>
										<div>
											<input type="text" class="form-control input-sm field-editable"	value="" id="fieldPrincipalPh" name="fieldPrincipalPh" data-parsley-required="true" data-parsley-maxlength="11"/>
										</div>
									</div> -->
									<div class="form-group col-md-6 col-sm-12 col-xs-12">
										<label class="control-label" for="">碰口部位</label>
										<div>
											<input type="text" class="form-control input-sm field-editable"	value="" id="touchPart" name="touchPart" data-parsley-required="true" data-parsley-maxlength="50"/>
										</div>
									</div>
									<div class="form-group col-sm-12 col-xs-12">
										<label class="control-label" for="">碰口方案简图</label>
										<div>
											<div class="hidden hasVal"> 
							         			<input type="text" class="form-control input-sm field-not-editable" id="drawName" name="drawName"/>
						         		        <a class="btn btn-sm btn-primary Search_Button" href="javascript:">查看</a>
						         		        <a class="del_btn btn btn-sm btn-danger"><i class="fa fa-times"></i> 删除</a>
						         		    </div>
											<div class="fileupload-buttonbar noVal hidden">
										        <div class="pull-right toolBtn">
										            <span class="btn btn-success btn-sm fileinput-button">
										                <i class="fa fa-plus"></i>
										                <span>浏览文件...</span>
										                <input type="file" name="files[]" multiple/>	             	          
										            </span>
										            <button type="submit" class="btn btn-primary btn-sm start hidden">
									                    <i class="fa fa-upload"></i>
									                    <span>上传</span>
									                </button>
										        </div>
										    </div>
										    <!-- The table listing the files available for upload/download -->
										    <table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
										</div>
									</div>
									
									<div class="form-group col-xs-12">
										<label class="control-label" for="">碰口说明</label>
										<div>
											<textarea class="form-control field-editable" name="touchPartAddtion" id="touchPartAddtion" rows="2" cols="" data-parsley-maxlength="200"></textarea>
										</div>
									</div>
									<!-- 四个长度 -->
									<div class="form-group col-md-6 col-sm-12 clearboth">
										<label class="control-label" for="">机动车道(米)</label>
										<div>
											<input type="text" class="form-control input-sm field-editable " id="vehicleRoad" name="vehicleRoad" data-parsley-type='number' />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">非机动车道(米)</label>
										<div>
											<input type="text" class="form-control input-sm field-editable "  id="nonVehicleRoad" name="nonVehicleRoad" data-parsley-type='number' />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">协调道路(米)</label>
										<div>
											<input type="text" class="form-control input-sm field-editable " id="coordinateRoad" name="coordinateRoad" data-parsley-type='number' />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">破坏绿地(米)</label>
										<div>
											<input type="text" class="form-control input-sm field-editable" id="greenRoad" name="greenRoad" data-parsley-type='number' />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">申请开挖时间</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default" data-parsley-required="true" value="" id="applyDigDate" name="applyDigDate" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">申请碰口时间</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default" data-parsley-required="true" value="" id="applyTpDate" name="applyTpDate" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool sign-require" for="">项目经理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white"	id="signBtn_4"><i class="fa fa-pencil"></i></a> 
											<input type="hidden" class="sign-data-input" id="constructionPrincipal" name="constructionPrincipal" value="">
											<input type="hidden" id="constructionPrincipal_postType" name="constructionPrincipal_postType" value="${constructionPrincipal}">
											<img class="" alt="" src="images/sign-1.png" style="height: 30px"> <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">申请日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default" id="constructionDate" name="constructionDate" />
										</div>
									</div>
									<div class="form-group col-xs-12">
										<label class="control-label" for="">其它损坏情况</label>
										<div>
											<textarea class="form-control field-editable" name="remark" id="remark" rows="2" cols="" data-parsley-maxlength="50"></textarea>
										</div>
									</div>
									
									<div class="form-group col-md-6 col-sm-12 hidden gpsDiv clearboth">
										<label class="control-label">经度</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="longitude" name="longitude" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 hidden gpsDiv">
										<label class="control-label">纬度</label>
										<div>
											<input type="text" class="form-control input-sm field-not-editable" value="" id="latitude" name="latitude" />
										</div>
									</div>
								</form>
								<!-- <form class="form-horizontal" id="dig5" data-parsley-validate="true" action="touchPlan/saveDigRoad" method="POST">
									<input type="hidden" class="tpId" id="tpId5" name="tpId" value="" />
									<input type="hidden" id="drId" name="drId" value="" />
										<div class="form-group col-sm-3 col-xs-12">
											<div>
												<input type="checkbox" name="drRoads" id="drRoads5" class="field-editable" value="花砖面" />花砖面：
											</div>
									    </div>
										<div class="form-group col-sm-3 col-xs-12">
											<label class="control-label" for="">（长）</label>
											<div>
												<input style="width: 40px;" type="text" class="form-control input-sm field-editable" id="drLength5" name="drLength" />
											</div>
									    </div>
									    <div class="form-group col-sm-3 col-xs-12">
											<label class="control-label" for="">（宽）</label>
											<div>
												<input type="text" style="width: 40px;" class="form-control input-sm field-editable" id="drWidth5" name="drWidth" />
											</div>
									    </div>
									    <div class="form-group col-sm-3 col-xs-12">
											<label class="control-label" for="">（深）</label>
											<div>
												<input type="text" style="width: 40px;" class="form-control input-sm field-editable" id="drDepth5" name="drDepth" />
											</div>
									    </div>
								</form> -->
							</div>
							<h4 class=" m-t-10">实际开挖道路及面积:</h4>
							<div class="clearboth form-box">
								<form id="digForm">
									<table id="digTable" class="table table-striped table-bordered nowrap" width="100%"">
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
<script id="template-upload" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-upload fade">
            <td class="col-md-1 hidden">
                <span class="preview"></span>
            </td>
            <td width="60%">
                <p class="name filename">{%=file.name%}</p>
                <strong class="error text-danger"></strong>
            </td>
            <td width="20%">
                <p class="size">Processing...</p>
            </td>
            <td width="20%">
                <div class="progress progress-striped active"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
            </td>
            <td class="hidden">
                {% if (!i && !o.options.autoUpload) { %}
                    <button class="btn btn-primary btn-sm start" disabled>
                        <i class="fa fa-upload"></i>
                        <span>Start</span>
                    </button>
                {% } %}
                {% if (!i) { %}
                    <button class="btn btn-white btn-sm cancel">
                        <i class="fa fa-ban"></i>
                        <span>Cancel</span>
                    </button>
                {% } %}
            </td>
        </tr>
    {% } %}
</script>
<script id="template-download" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-download fade">
            <td class="hidden">
                <span class="preview">
                    {% if (file.thumbnailUrl) { %}
                        <!--<a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>-->
                    {% } %}
                </span>
            </td>
            <td width="60%">
                <p class="name">
                    {% if (file.url) { %}
                        <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                    {% } else { %}
                        <span>{%=file.name%}</span>
                    {% } %}
                </p>
                {% if (file.error) { %}
                    <div><span class="label label-danger">Error</span> {%=file.error%}</div>
                {% } %}
            </td>
            <td width="20%">
                <span class="size">{%=o.formatFileSize(file.size)%}</span>
            </td>
            <td width="20%">
                <div class="progress progress-striped text-center"><div class="progress-bar progress-bar-success" style="width:100%;">已上传</div></div>
            </td>
            <td class="hidden">
                {% if (file.deleteUrl) { %}
                    <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                        <i class="glyphicon glyphicon-trash"></i>
                        <span>Delete</span>
                    </button>
                    <input type="checkbox" name="delete" value="1" class="toggle">
                {% } else { %}
                    <button class="btn btn-warning cancel">
                        <i class="glyphicon glyphicon-ban-circle"></i>
                        <span>Cancel</span>
                    </button>
                {% } %}
            </td>
        </tr>
    {% } %}
</script>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
  	//初始化上传文件控件
    $.getScript('projectjs/data/form-multiple-upload.demo3.js').done(function() {
    	FormMultipleUpload.init($('#touchPlanForm'));
    });
    App.setPageTitle('碰口方案 - 工程项目管理系统');
    $("#touchPlanForm").styleFit();

    //是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    }else{

		if(_inApk){
			$('.gpsDiv').removeClass("hidden");
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
	    $.getScript('projectjs/constructmanage/connect-plan.js').done(function () {
			connectPlan.init();
		});
	    
	    //日期datepicker
	    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
	    	$('.datepicker-default').datepicker({
	            todayHighlight: true
	        });
	    });
	
	  //图片查看功能
	    $('.Search_Button').off("click").on("click",function(){  
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
	    
	    
	    //签字
	    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
	    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_5, #signBtn_6");
	    	signatures.handleSignature(); 
	    	$("#signBtn_4").handleSignature(false);
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
	    		menuDes= encodeURIComponent(cjkEncode($("#menuDes").val())),
	    		drawUrl1 = encodeURIComponent(cjkEncode($("#drawUrl1").val())),
	    		imgUrl = encodeURIComponent(cjkEncode($(".imgUrl").val())),
		    	projName = encodeURIComponent(cjkEncode($("#projName").val())),                    //工程名称
		    	constructionUnit = encodeURIComponent(cjkEncode($("#constructionUnit").val())),    //施工单位
		    	projScaleDes = encodeURIComponent(cjkEncode($("#projScaleDes").val())),            //工程规模
		    	projManager = encodeURIComponent(cjkEncode($("#projManager").val())),              //项目经理
		    	projManagerPh = encodeURIComponent(cjkEncode($("#projManagerPh").val())),          //经理电话
		    	touchAddr = encodeURIComponent(cjkEncode($("#touchAddr").val())),                  //碰口地点
		    	fieldPrincipal = encodeURIComponent(cjkEncode($("#fieldPrincipal").val())),        //现场负责人
		    	fieldPrincipalPh = encodeURIComponent(cjkEncode($("#fieldPrincipalPh").val()));    //现场负责人电话	    	
		    	touchAddr = encodeURIComponent(cjkEncode($("#touchAddr").val())),					//施工地点	    	
		    	vehicleRoad = encodeURIComponent(cjkEncode($("#touchAddr").val())),					//机动车道(米)
		    	nonVehicleRoad = encodeURIComponent(cjkEncode($("#nonVehicleRoad").val())),			//非机动车道(米)
		    	coordinateRoad = encodeURIComponent(cjkEncode($("#coordinateRoad").val())),			//协调道路(米)
		    	greenRoad = encodeURIComponent(cjkEncode($("#greenRoad").val()));					//破坏绿地(米)
		    	var tpId = $("#tpId").val();
		    	if(tpId === '' || tpId === null || tpId === undefined){
		    		tpId = '#';
		    	}
		    	//console.log("tpId:"+tpId);
		    	var roads = '';
		    	var json = $("#digForm").getDTFormData();
				//console.log("...."+JSON.stringify(json));
				for(var i=0;i<json.length;i++){
					var str = " "+json[i].drRoads+"：长 "+(json[i].drLength===null?" ":json[i].drLength)+" 米  宽 "+json[i].drWidth+" 米  深 "+json[i].drDepth + " 米";
					if(i !== json.length){
						str=str+"，";
					}
					roads = roads+str;
				}
				//console.log("roads...."+roads);
				roads = encodeURIComponent(cjkEncode(roads));
	    	var src = "<%=basePath%>/ReportServer?reportlet=constructmanage/touchPlan.cpt&conNo="+conNo+"&projName="+projName+"&constructionUnit="+constructionUnit+"&projManager="+projManager+"&projManagerPh="+projManagerPh+"&fieldPrincipal="+fieldPrincipal+"&fieldPrincipalPh="+fieldPrincipalPh;
	    	
	    	src = src +"&vehicleRoad="+vehicleRoad+"&nonVehicleRoad="+nonVehicleRoad+"&coordinateRoad="+coordinateRoad+"&greenRoad="+greenRoad+"&touchAddr="+touchAddr+"&tpId="+tpId+"&roads="+roads+"&projScaleDes="+projScaleDes+"&projId="+projId+"&imgUrl="+imgUrl+"&drawUrl1="+drawUrl1+"&menuDes="+menuDes;
	    	console.log("drawUrl1...."+drawUrl1);
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
		    	
	//  点击修改按钮
		 $(".updataBtn").on("click",function(){
			 $(".updataBtn").addClass("hidden");
			 $(".saveBtn").removeClass("hidden");
			 $("#touchPlanForm,#digForm").toggleEditState(true);
			 
			//施工或竣工中
        	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#drawingAuditForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#drawingAuditForm").find(".sign-data-input").toggleSign(false);
        	}
			 
		    });
	    
		//删除附件列表记录
    	$(".del_btn").on("click",function(){
   		$("body").cgetPopup({
			title: '提示',
			content: '您确定删除该文件信息吗？',
		    accept: {
				func: deleteDone,	//函数名
				singleArgs: $(this)	//参数，用于向func传入多个参数，不设置该属性或设置为[]，则传入不带参数的函数
			}
    	});
  		});
	    function deleteDone(){
	    	$("#drawName").val("");
	    	$(".hasVal").addClass("hidden");
	    	$(".noVal").removeClass("hidden");
	    	
	    }
	    
	   function saveBack(data){
			var content = "数据保存成功！";
           	if(data.result === "false"){
           		content = "数据保存失败！";
           	}else {
           		touchPlanDetail();
           		$("#drawUrl").val(data.drawUrl);
              	
           	}
           	var myoptions = {
                   	title: "提示信息",
                   	content: content,
                   	accept: savedone,
                   	chide: true,
                   	icon: "fa-check-circle"
               }
           
               $("body").cgetPopup(myoptions);
           
 		} 
	    
	    var savedone = function(){
	     	 if($("#drawName").val()){
	     		 $(".hasVal").removeClass("hidden");
	     		 $(".noVal").addClass("hidden");
	     		 $(".noVal+#filePreviews tr").remove();
	     	 }else{
	     		 $(".noVal").removeClass("hidden");
	     		 
	     		 $(".hasVal").addClass("hidden");
	     	 }
	     	$("#touchPlanForm").styleFit();
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
		        	
		        	
		        	
		        	
		        	
		        	
		        	
		        	
		        	
		        	
		        	if($(".noVal+#filePreviews tr").length){
		        		//序列化
		    	       	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
		    	       	$("#menuDes").val(menuDesc);
		        		var mdata = tableform.getDTFormData();
			           	var viewdata = viewform.serializeJson();
			        	    viewdata.digRoads = mdata;
			        	var data= JSON.stringify(viewdata);
			        	
			        	
		    	       	//var data=$("#touchPlanForm").serializeJsonString();
			        	$("#result").val(data);
				       	$(".start").click(); 
				       	/* console.info("save "+data); */
		        	}else{
		        		console.info("===-=-0-9-00809");
		        		var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
		    	       	$("#menuDes").val(menuDesc);
		    	       	var mdata = tableform.getDTFormData();
			           	var viewdata = viewform.serializeJson();
			        	    viewdata.digRoads = mdata;
			        	var data= JSON.stringify(viewdata);
		        		
		        		$("#result").val(data);
		        		$(".start").click(); 
		        		console.info("data========="+data);
		        		var mdata = tableform.getDTFormData();
			           	var viewdata = viewform.serializeJson();
			        	    viewdata.digRoads = mdata;
			        	    console.info(viewdata);
			        	    console.info(JSON.stringify(viewdata));
		        		$.ajax({
			                type: 'POST',
			                url: 'touchPlan/saveTouchPlanTo',
			                contentType: "application/json;charset=UTF-8",
			                data: JSON.stringify(viewdata),
			                success: function (data) {
			                	var content = "数据保存成功！";
			                	if(data === "false"){
			                		content = "数据保存失败!!";
			                	}else{
			                		//alertInfo(content);
			                		touchPlanDetail();
			                	}
			                	var myoptions = {
		    	                       	title: "提示信息",
		    	                       	content: content,
		    	                       	accept: savedone,
		    	                       	chide: true,
		    	                       	icon: "fa-check-circle"
		    	                }
		    	                $("body").cgetPopup(myoptions);
			                	
			                },
			                error: function (jqXHR, textStatus, errorThrown) {
			                    console.warn("碰口方案保存失败！");
			                    console.info(jqXHR);
			                    console.info(errorThrown);
			                }
			            });
		        	}
		        	/* var mdata = tableform.getDTFormData();
		           	var viewdata = viewform.serializeJson();
		        	    viewdata.digRoads = mdata;
		        	var data= JSON.stringify(viewdata);
		        	  
		        	$("#result").val(data);
			       	$(".start").click(); 
			       	console.info("save "+data); */
		        	//console.log("result:"+JSON.stringify(viewdata));
		        	/* $.ajax({
		                type: 'POST',
		                url: 'touchPlan/saveTouchPlan',
		                contentType: "application/json;charset=UTF-8",
		                data: JSON.stringify(viewdata),
		                success: function (data) {
		                	var content = "数据保存成功！";
		                	if(data === "false"){
		                		content = "数据保存失败！";
		                	}else{
		                		alertInfo(content);
		                		touchPlanDetail();
		                	}
		                	
		                },
		                error: function (jqXHR, textStatus, errorThrown) {
		                    console.warn("碰口方案保存失败！");
		                    console.info(jqXHR);
		                    console.info(errorThrown);
		                }
		            }); */
		        	$("#touchPlanForm,#digForm").toggleEditState(false).styleFit();
	        		$(".saveBtn").addClass("hidden");
	        		$(".updataBtn").removeClass("hidden");
	        		showReport();
	              	//如果通过验证, 则移除验证UI
	              	tableform.parsley().reset();
	              	viewform.parsley().reset();
		        }else{
		          	 //如果未通过验证, 则加载验证UI
		          	 tableform.parsley().validate();
		          	 viewform.parsley().validate();
		          }
		    	
		    }); 
	    
		  
		/* //开挖路况保存
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
			}); */
	  
	   // 表单样式对齐
		  $("#touchPlanForm").toggleEditState(true).styleFit();
	}
</script>
<!-- ================== END PAGE LEVEL JS ================== -->