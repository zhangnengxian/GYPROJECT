<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
		<div class="col-sm-6 col-xs-12" >
			<div class="panel panel-inverse" >
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">预览区</h4>
				</div>
				<div class="panel-body" id="">
					<input type="hidden" id="pcIdNew" value = "${pcid}">
					<input type="hidden" class="imgUrl" value="${imgUrl}"/>
					<div class="clearboth form-box">
						<div class="iframe-report-box">
							<iframe id="mainFrm" class="iframe-report" style="width: 1180px; height: 850px; border:1;overflow-y:auto;" scrolling="no"></iframe>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- col-sm-6 end  -->
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" id="">
			<div class="panel-heading">
				<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	            </div>
				<h4 class="panel-title">确认区</h4>
			</div>
			<div class="panel-body" id="drawing_audit_panel_box">
					<div id="budgetAuditTopBox">
						<div class="toolBtn f-r m-b-15  editbtn">
                        <a href="javascript:;" class="btn  btn-primary btn-sm m-l-5 checkRole hidden get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>    	 				
                        <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box acceptAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="acceptAuditForm" action="auditIns/auditSave">
			    			<input type="hidden" id="projId1" name = "projId" value = "${data.projId}"/>
			    			<input type="hidden" id="projNo1" name = "projNo" value = "${data.projNo}"/>
                    		<input type="hidden" id="pcId" name = "pcId"  value = "${data.pcId}"/>
                    		<input type="hidden" name = "mrAuditLevel" value = "${currentLevel}"/>
                            <input  type="hidden" id="signPicture" name = "signPicture" value = "${loginInfo.signPicture}"/>
							<input  type="hidden" id="menuId" name = "menuId" />
			    			<div class="form-group col-md-12">
			        			<label class="control-label" for="">确认结果</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="1" />通过
				            	</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="0" />不通过
				            	</label>
		    				</div>
		    				<div class="form-group col-md-12">
						     	<label class="control-label" for="">确认意见</label>
						     	<div> 
		        					<textarea class="form-control "  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					</div>
		   					<div class="form-group col-md-6">
						        <label class="control-label" for="">确认人</label>
						        <div>
						           <input type="hidden"  id="mrCsr"  name="mrCsr" data-parsley-maxlength="100" value="${loginInfo.staffId}">
						           <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
						        </div>
						    </div>

						    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
						        <label class="control-label" for="">确认日期</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" data-parsley-maxlength="100" >
						        </div>
						    </div>

                            <div class="form-group col-md-6">
                                <label class="control-label signature-tool" for="" style="width: 90px;">签字</label>
                                <div>
                                    <input type="hidden" class="sign-data-input disabled" id="signPicturePath" name=signPicturePath value="${sessionScope.session_loginInfo.staffName}">
                                    <img class="signPicture" alt="" src="" style="height: 30px">
                                    <span class="clear-sign disabled"><i class="fa ion-close-circled"></i></span>
                                </div>
                            </div>

							<div class="form-group col-md-6 col-sm-12 mobileDiv ">
								<label class="control-label">经度</label>
								<div>
									<input type="text" class="form-control input-sm field-editable" value="" id="longitude" name="longitude" />
								</div>
							</div>
							<div class="form-group col-md-6 col-sm-12 mobileDiv ">
								<label class="control-label">纬度</label>
								<div>
									<input type="text" class="form-control input-sm field-editable" value="" id="latitude" name="latitude" />
								</div>
							</div>
			    		</form>
			    	</div>
			    	<div >
		    		<h4 class="m-t-15 m-l-7"><strong>确认历史</strong></h4>
		    		</div>
		    		<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
       					<thead>
			            	<tr>
			                <th>确认日期</th>
			                <th>确认结果</th>
			                <th>确认意见</th>
			                <th>确认人</th>
	            			</tr>
          				</thead>
					</table>
					</div>
			    </div>
	     	</div>
		</div>	    	
	</div>
</div>


<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('报验审核 - 工程管理系统');
    
    $("#acceptForm").toggleEditState();
    $("#acceptForm").styleFit();
    
    $("#acceptAuditForm").toggleEditState();
    $("#acceptAuditForm").styleFit();

    //签字加载方式
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
        var signatures = $("#signBtn_2, #signBtn_3");
        signatures.handleSignature();
    });
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
        var fr = $(".iframe-report");
        for(var i=0; i<fr.length; i++){
            fr.eq(i).rescaleReportPanel();
        }
    }
    <%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   %>
    var cptPath = '<%=basePath%>';
    //加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}

    //加载记录区的cpt文件
    var showReportType3 = function(){
        var pcId=$('#pcIdNew').val();
        if(!pcId){
            pcId='-1';
            inspectionDate='';
        }

        //解决乱码
        var projName=encodeURIComponent(cjkEncode("${data.projName}"));
        // suName=encodeURIComponent(cjkEncode(suName));
        var constructionUnit=encodeURIComponent(cjkEncode("${data.constructionUnit}"));
        var src = cptPath+"/ReportServer?reportlet=grooveRecord/grooveRecordNew.cpt&pcId="+pcId+"&projName="+projName+"&constructionUnit="+constructionUnit+"&imgUrl="+$(".imgUrl").val();
        $("#mainFrm").attr("src",src);
    };



    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("auditIns/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
    	var val=$('#acceptAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
            $("#menuId").val(getStepId());
    		//由于返回值调整，自己写方法
    		//$("#acceptAuditForm").cformSave("auditHistoryTable",auditSaveCallBack);
            if($("#acceptAuditForm").parsley().isValid()){
        		$("#drawing_audit_panel_box").loadMask("正在保存...", 1, 0.5);
            	var data=$("#acceptAuditForm").serializeJson();
            	$.ajax({
                    type: 'POST',
                    url: 'auditIns/auditSave',
                    contentType: "application/json;charset=UTF-8",
                    dataType:"json",
                    data: JSON.stringify(data),
                    beforeSend: function () {
        	              // 禁用按钮防止重复提交
                        $(".saveBtn").attr({ disabled: "disabled" });
        	        },
                    complete: function () {
                    	//去掉禁用
                    	$(".saveBtn").removeAttr("disabled");
                    },
                    success: function (res) {
                        initShowCPT();
                    	$("#drawing_audit_panel_box").hideMask();
                    	var content = "数据保存成功！";
                    	if(res=="false"){
                    		content = "数据保存失败！";
                    		alertInfo(content);
                    	}else if(res=="-2"){
                    		$(".saveBtn").addClass("hidden");
                    		$(".cancelBtn").text("返回");
                    		$("#acceptAuditForm").toggleEditState(false);
                    		$(".acceptAuditFormDiv").addClass("hidden");
                    		$("#auditHistoryTable").cgetData(false);  //列表重新加载
                    		alertInfo("该数据已被审核，无需审核！");
                    	}else{//已被审核
                    		$(".saveBtn").addClass("hidden");
                    		$(".cancelBtn").text("返回");
                    		$("#acceptAuditForm").toggleEditState(false);
                    		$(".acceptAuditFormDiv").addClass("hidden");
                    		$("#auditHistoryTable").cgetData(false);  //列表重新加载
                    		alertInfo(content);
                    	}
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.warn("合同签订区记录保存失败！");
                    }
                });
            	//如果通过验证, 则移除验证UI
            	$("#acceptAuditForm").parsley().reset();
            }else{
            	//如果未通过验证, 则加载验证UI
            	$("#acceptAuditForm").parsley().validate();
            }
    	}
    });
    
    
    var ensureDone=function(){};
    
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.acceptAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#acceptAuditForm input:radio[name="mrResult"]:checked').val();
    		$("#acceptAuditForm").toggleEditState(false); 
    	}
    }
    
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//确认过
    		auditSaveCallBack("true");
    	}
    }();

    if(_inApk){
        $('.mobileDiv,.get-location').removeClass("hidden");
        $(".get-location").off("click").on("click", function(){
            var t = $(this);
            t.button("loading");
            cgetLocation(function(position) {
                $('[name="longitude"]').val(position.longitude);
                $('[name="latitude"]').val(position.latitude);
                t.button("reset");
            });
        });
    }else{
    	 $('.mobileDiv,.get-location').addClass("hidden");
    }



    /**
     * 初始化右侧审核列表
     */
    var insAuditHistoryInit = function(data) {
        "use strict";
        // histSearchData.businessOrderId=$("#pcIdNew").val();
        if ($('#auditHistoryTable').length !== 0) {
            $('#auditHistoryTable').on( 'init.dt',function(){
                //隐藏遮罩
                $('#auditHistoryTable').hideMask();
            }).DataTable({
                language: language_CN,
                lengthMenu: [18],
                retrieve: true,
                dom: 'Brt',
                buttons: [
                ],
                //启用服务端模式，后台进行分段查询、排序
                serverSide:true,
                ajax: {
                    url: 'auditIns/queryManageRecord',
                    type:'post',
                    data: {"businessOrderId":$("#pcIdNew").val()},
                    dataSrc: 'data'
                },
                responsive: {
                    details: {
                        renderer: function ( api, rowIdx, columns ){
                            return renderChild(api, rowIdx, columns);
                        }
                    }
                },
                select: true,  //支持多选
                columns: [
                    {"data":"mrTime"},
                    {"data":"mrResult"},
                    {"data":"mrAopinion"},
                    {"data":"mrCsr"}
                ],
                columnDefs: [{
                    "targets": 0,
                    "render": function ( data, type, row, meta ) {
                        if(type === "display"){
                            return format(data,'all');
                        }else{
                            return data;
                        }
                    },
                },{
                    "targets": 1,
                    "render": function ( data, type, row, meta ) {
                        if(data === "1"){
                            return "通过";
                        }else if(data === "0"){
                            return "不通过";
                        }else{
                            return "";
                        }
                    },
                }]
            });
        }
    };

    var showReportType3 = function(){
        var pcId=$('#pcIdNew').val();
        if(!pcId){
            pcId='-1';
            inspectionDate='';
        }

        //解决乱码
        var projName=encodeURIComponent(cjkEncode("${data.projName}"));
        // suName=encodeURIComponent(cjkEncode(suName));
        var constructionUnit=encodeURIComponent(cjkEncode("${data.constructionUnit}"));
        var src = cptPath+"/ReportServer?reportlet=grooveRecord/grooveRecordNew.cpt&pcId="+pcId+"&projName="+projName+"&constructionUnit="+constructionUnit+"&imgUrl="+$(".imgUrl").val();
        $("#mainFrm").attr("src",src);
    };

    showReportType6 = function(){
        var projName='',constructionUnit='',suName='';
        var pcId=$("#pcIdNew").val();
        if(!pcId){
            pcId='-1';
        }
        projName=encodeURIComponent(cjkEncode("${data.projName}"));
        constructionUnit=encodeURIComponent(cjkEncode("${data.constructionUnit}"));
        suName=encodeURIComponent(cjkEncode("${data.suName}"));
        var src="<%=basePath%>/ReportServer?reportlet=pePipeWelding/pePipeWeldingNew.cpt&pcId="+pcId+"&projName="+projName;
        src=src+"&constructionUnit="+constructionUnit+"&suName="+suName;
        src = src+"&imgUrl="+$(".imgUrl").val();
        $("#mainFrm").attr("src",src);
    };

    var showReportType7 = function(){
        var projName='',constructionUnit='';
        var pcId = $("#pcIdNew").val();
        //以下处理为解决中文乱码
        projName=encodeURIComponent(cjkEncode("${data.projName}"));
        constructionUnit = encodeURIComponent(cjkEncode("${data.constructionUnit}"));
        //cpt路径及参数
        var src="";
        if(!pcId){
            pcId='-1';
        }
        if("1"=="${data.preservativeType}"){
            src = cptPath+"/ReportServer?reportlet=preservative/preservativeJointNew.cpt";
        }else{
            src = cptPath+"/ReportServer?reportlet=preservative/preservativeNew.cpt";
        }
        src = src+"&projName=" + projName+"&pcId="+pcId+"&constructionUnit="+constructionUnit;

        src = src+"&imgUrl="+$(".imgUrl").val();
        $("#mainFrm").attr("src",src);
    };


    var showReportType8 = function() {
        var projName = '', suName = '', constructionUnit = '', inspectionDate = '', pcId = '', corpName = '';
        var pcId = $('#pcIdNew').val();
        if(pcId==''){
            pcId='-1';
        }
        //以下处理为解决中文乱码
        projName = encodeURIComponent(cjkEncode("${data.projName}"));
        suName = encodeURIComponent(cjkEncode("${data.suName}"));
        corpName = encodeURIComponent(cjkEncode("${data.corpName}"));
        constructionUnit = encodeURIComponent(cjkEncode("${data.constructionUnit}"));

        //cpt路径及参数
        var src = "";
        if("0"=="${data.preservativeType}") {
            src = cptPath
                + "/ReportServer?reportlet=inspection/preservativeInpectJointNew.cpt";
        } else {
            src = cptPath
                + "/ReportServer?reportlet=inspection/preservativeInpectPaintNew.cpt";
        }
        src = src + "&projName=" + projName + "&suName=" + suName
            + "&constructionUnit=" + constructionUnit + "&corpName="
            + corpName + "&inspectionDate=" + inspectionDate;
        src = src + "&pcId=" + pcId + "&imgUrl=" + $(".imgUrl").val();
        $("#mainFrm").attr("src", src);
    };


    var showReportType9 = function(){
        var projName='',imgUrl='',pcId='',constructionUnit='',suName='';
        imgUrl = encodeURIComponent(cjkEncode($(".imgUrl").val()));
        pcId = $('#pcIdNew').val();
        if(!pcId){
            pcId='-1';
        }
        projName=encodeURIComponent(cjkEncode("${data.projName}"));
        constructionUnit=encodeURIComponent(cjkEncode("${data.constructionUnit}"));
        suName=encodeURIComponent(cjkEncode("${data.suName}"));
        var src="<%=basePath%>/ReportServer?reportlet=hiddenWorks/hiddenWorksNew.cpt&projName="+projName;
        src = src+"&constructionUnit="+constructionUnit+"&suName="+suName+"&imgUrl="+imgUrl;
        src = src+"&pcId="+pcId;
        $("#mainFrm").attr("src",src);
    };


    var showReportType11 = function(){
        var projName='',imgUrl='',pcId='',projNo='',constructionUnit='',suName='',cuDate='',inspectionDate='';
        var  pcId = $("#pcIdNew").val();
        if(!pcId){
            pcId='-1';
            inspectionDate='';
        }
        //以下处理为解决中文乱码
        projName=encodeURIComponent(cjkEncode("${data.projName}"));
        projNo=encodeURIComponent(cjkEncode("${data.projNo}"));
        constructionUnit=encodeURIComponent(cjkEncode("${data.constructionUnit}"));
        suName=encodeURIComponent(cjkEncode("${data.suName}"));
        inspectionDate = "${data.inspectionDate}";
        cuDate = "${data.cuDate}";
        projNo = "${data.projNo}";
        //cpt路径及参数
        var src=cptPath+"/ReportServer?reportlet=inspection/undergroundInpectNew.cpt&projName="+projName+"&projNo="+projNo+"&pcId="+pcId+"&imgUrl="+$('.imgUrl').val();
        src = src + "&inspectionDate="+inspectionDate+"&cuDate="+cuDate;
        $("#mainFrm").attr("src",src);
    };

    var showReportType10 = function(){
        var projName='',custName='',pcId='',projNo='',constructionUnit='',suName='';
        pcId = $("#pcIdNew").val();
        if(!pcId){
            pcId='-1';
        }
        //以下处理为解决中文乱码
        custName = encodeURIComponent(cjkEncode("${data.custName}"));
        projNo = encodeURIComponent(cjkEncode("${data.projNo}"));
        projName=encodeURIComponent(cjkEncode("${data.projName}"));
        constructionUnit=encodeURIComponent(cjkEncode("${data.constructionUnit}"));
        suName=encodeURIComponent(cjkEncode("${data.suName}"));

        //cpt路径及参数
        var src=cptPath+"/ReportServer?reportlet=inspection/purgeRecordNew.cpt&projName="+projName+"&projNo="+projNo+"&suName="+suName+"&custName="+custName+"&constructionUnit="+constructionUnit;
        src = src + "&pcId="+pcId+"&imgUrl="+$('.imgUrl').val();
        $("#mainFrm").attr("src",src);
    };

    var showReportType12 = function(){
        var projName='',custName='',pcId='',projNo='',constructionUnit='',suName='',projId='';
        pcId = $("#pcIdNew").val();
        if(!pcId){
            pcId='-1';
        }
        //以下处理为解决中文乱码
        custName = encodeURIComponent(cjkEncode("${data.custName}"));
        projNo = encodeURIComponent(cjkEncode("${data.projNo}"));
        projName=encodeURIComponent(cjkEncode("${data.projName}"));
        constructionUnit=encodeURIComponent(cjkEncode("${data.constructionUnit}"));
        suName=encodeURIComponent(cjkEncode("${data.suName}"));
        projId = "${data.projId}";
        //cpt路径及参数
        var src=cptPath+"/ReportServer?reportlet=trenchBackfill/trenchBackfillNew.cpt&projName="+projName+"&suName="+suName+"&constructionUnit="+constructionUnit+"&custName="+custName;
        src = src +"&projId="+projId+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
        $("#mainFrm").attr("src",src);
    };


    var showReportType15 = function(){
        var pcId = $("#pcIdNew").val();
        if(!pcId){
            pcId=-1;
        }
        //以下处理为解决中文乱码
        var projName=encodeURIComponent(cjkEncode("${data.projName}"));
        var projAddr = encodeURIComponent(cjkEncode("${data.projAddr}"));
        //cpt路径及参数
        var src=cptPath+"/ReportServer?reportlet=inspection/ballRecordNew.cpt&projName="+projName+"&projAddr="+projAddr+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
        $("#mainFrm").attr("src",src);
    };

    var showReportType18 = function(){
        var pcId = $("#pcIdNew").val();
        if(!pcId){
            pcId='-1';
        }
        //以下处理为解决中文乱码
        var corpName=encodeURIComponent(cjkEncode("${data.corpName}"));
        var projName=encodeURIComponent(cjkEncode("${data.projName}"));
        var constructionUnit=encodeURIComponent(cjkEncode("${data.constructionUnit}"));
        var suName=encodeURIComponent(cjkEncode("${data.suName}"));
        //cpt路径及参数
        var src=cptPath+"/ReportServer?reportlet=inspection/equipmentInstallNew.cpt&pcId="+pcId+"&imgUrl="+$(".imgUrl").val()+
            "&projName="+projName+"&suName="+suName+"&corpName="+corpName+"&constructionUnit="+constructionUnit;
        $("#mainFrm").attr("src",src);
    };

    var showReportType20 = function(){
        var pcId = $("#pcIdNew").val();

        if(!pcId){
            pcId='-1';
        }
        //以下处理为解决中文乱码
        var projName=encodeURIComponent(cjkEncode("${data.projName}"));
        //cpt路径及参数
        var src=cptPath+"/ReportServer?reportlet=inspection/anodeInstallNew.cpt&projName="+projName+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
        $("#mainFrm").attr("src",src);
    };

    var showReportType22 = function(){
        var pcId = $("#pcIdNew").val();
        if(!pcId){
            pcId='-1';
        }
        //以下处理为解决中文乱码
        var projName=encodeURIComponent(cjkEncode("${data.projName}"));
        <%--var constructionUnit=encodeURIComponent(cjkEncode("${data.constructionUnit}"));--%>
        <%--var suName=encodeURIComponent(cjkEncode("${data.suName}"));--%>
        <%--var welderName=encodeURIComponent(cjkEncode("${data.welderName}"));--%>
        <%--var peWeldSpec=encodeURIComponent(cjkEncode("${data.peWeldSpec}"));--%>
        <%--pipeManufactor=encodeURIComponent(cjkEncode("${data.pipeManufactor}"));--%>

        var meltConnectType = "${data.meltConnectType}";
        //cpt路径及参数
        var src="";
        if(meltConnectType=='2'){
            src=cptPath+"/ReportServer?reportlet=inspection/peWeldLineInpectHotMeltNew.cpt";
        }else{
            src=cptPath+"/ReportServer?reportlet=inspection/peWeldLineInpectElectricMeltNew.cpt";
        }
        src = src + "&pcId="+pcId+"&projName="+projName+"&imgUrl="+$(".imgUrl").val();
        $("#mainFrm").attr("src",src);
    };

    var showReportType23 = function(){
        var pcId = $('#pcIdNew').val();
        if(!pcId){
            pcId='-1';
        }
        //以下处理为解决中文乱码
        var projName=encodeURIComponent(cjkEncode("${data.projName}"));
        var constructionUnit=encodeURIComponent(cjkEncode("${data.constructionUnit}"));
        var suName=encodeURIComponent(cjkEncode("${data.suName}"));
        var corpName=encodeURIComponent(cjkEncode("${data.corpName}"));
        var projNo=encodeURIComponent(cjkEncode("${data.projNo}"));
        //cpt路径及参数
        var src=cptPath+"/ReportServer?reportlet=inspection/clearRecordNew.cpt&projName="+projName+"&projNo="+projNo+"&suName="+suName+"&corpName="+corpName+"&constructionUnit="+constructionUnit;
        src = src + "&pcId="+pcId+"&imgUrl="+$('.imgUrl').val();
        $("#mainFrm").attr("src",src);
    };

    var showReportType17 = function(){
        var pcId = $('#pcIdNew').val();
        if(!pcId){
            pcId='-1';
        }
        //以下处理为解决中文乱码
        var projName=encodeURIComponent(cjkEncode("${data.projName}"));
        var suName = encodeURIComponent(cjkEncode("${data.suName}"));
        var custName = encodeURIComponent(cjkEncode("${data.custName}"));
        var constructionUnit = encodeURIComponent(cjkEncode("${data.constructionUnit}"));
        var corpName = encodeURIComponent(cjkEncode("${data.corpName}"));
        var projAddr = encodeURIComponent(cjkEncode("${data.projAddr}"));

        //cpt路径及参数
        var src = cptPath+"/ReportServer?reportlet=inspection/indoorPocketWatchNew.cpt";
        if(!pcId){
            pcId='-1';
        }
        src = src + "&projName="+projName+"&constructionUnit="+constructionUnit+"&custName="+custName+"&corpName="+corpName+"&projAddr="+projAddr;
        src = src +"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
        $("#mainFrm").attr("src",src);
    };


    var showReportType19 = function(){
        var pcId = $("#pcIdNew").val();
        if(!pcId){
            pcId='-1';
        }
        //以下处理为解决中文乱码
        var projName=encodeURIComponent(cjkEncode("${data.projName}"));
        var suName=encodeURIComponent(cjkEncode("${data.suName}"));
        var corpName=encodeURIComponent(cjkEncode("${data.corpName}"));
        var constructionUnit = encodeURIComponent(cjkEncode("${data.constructionUnit}"));
        var projAddr = encodeURIComponent(cjkEncode("${data.projAddr}"));
        //cpt路径及参数
        var src=cptPath+"/ReportServer?reportlet=inspection/hotMeltDockingNew.cpt&projName="+projName+"&projAddr="+projAddr+"&suName="+suName;
        src = src + "&pcId="+pcId+"&imgUrl="+$('.imgUrl').val();
        $("#mainFrm").attr("src",src);
    };

    var showReportType24 = function(){
        var pcId = $("#pcIdNew").val();
        if(!pcId){
            pcId='-1';
        }
        //以下处理为解决中文乱码
        var projName=encodeURIComponent(cjkEncode("${data.projName}"));
        var inspectionDate=encodeURIComponent(cjkEncode("${data.inspectionDate}"));

        //cpt路径及参数
        var src=cptPath+"/ReportServer?reportlet=inspection/pipeWeldRecordNew.cpt&projName="+projName+"&inspectionDate="+inspectionDate;
        src = src + "&pcId="+pcId+"&imgUrl="+$('.imgUrl').val();

        $("#mainFrm").attr("src",src);
    };

    $(function () {
        initShowCPT();
        insAuditHistoryInit();
    });



	 function showReportType16(){//试压记录
		var pcId = $("#pcIdNew").val();
		if(!pcId){ pcId='-1'; }
		//以下处理为解决中文乱码
		var projName=encodeURIComponent(cjkEncode("${data.projName}"));
		var suName=encodeURIComponent(cjkEncode("${data.suName}"));
		var corpName=encodeURIComponent(cjkEncode("${data.corpName}"));
		var constructionUnit = encodeURIComponent(cjkEncode("${data.constructionUnit}"));
		var stBuildingNo=encodeURIComponent(cjkEncode("${data.stBuildingNo}"));
		var stHouseHolds=encodeURIComponent(cjkEncode("${data.stHouseHolds}"));
		var stMedium=encodeURIComponent(cjkEncode("${data.stMedium}"));
		var stRange=encodeURIComponent(cjkEncode("${data.stRange}"));
		var stPipeType=encodeURIComponent(cjkEncode("${data.stPipeType}"));
		var projId=encodeURIComponent(cjkEncode("${data.projId}"));
		var stInstruction="";
		var gastInstruction="";

		 //cpt路径及参数
		 var src=cptPath;
		 var condition="&pcId="+pcId+"&imgUrl="+$(".imgUrl").val()+"&projName="+projName+"&suName="+suName+"&corpName="+corpName+"&constructionUnit="+constructionUnit+"&stInstruction="+stInstruction+"&gastInstruction="+gastInstruction+"&projId="+projId;
		 if(stPipeType=='2'){
			 src += "/ReportServer?reportlet=inspection/strengthTestIndoor1.cpt&stBuildingNo="+stBuildingNo+"&stHouseHolds="+stHouseHolds;
		 }else{
			 src += "/ReportServer?reportlet=inspection/strengthTest.cpt&stMedium="+stMedium+"&stRange="+stRange;
		 }
		 $("#mainFrm").attr("src",src+condition);

	};


    function initShowCPT() {
        var type = "${data.pcDesId}";
        if("3"==type){
            //管沟开挖
            showReportType3();
        }else if("6"==type){
            //PE管焊接
            showReportType6();
        }else if("7"==type){
            //防腐记录
            showReportType7();
        }else if("8"==type){
            //防腐检查
            showReportType8();
        }else if("9"==type){
            //隐蔽工程
            showReportType9();
        }else if("11"==type){
            //埋地检查
            showReportType11();
        }else if("10"==type){
            //吹扫记录
            showReportType10();
        }else if("12"==type){
            //沟槽回填
            showReportType12();
        }else if("15"==type){
            //通球记录
            showReportType15();
        }else if("18"==type){
            //设备安装
            showReportType18();
        }else if("20"==type){
            //阳极安装
            showReportType20();
        }else if("22"==type){
            //PE管焊缝检查
            showReportType22();
        }else if("23"==type){
            //清扫记录
            showReportType23();
        }else if("17"==type){
            //户内挂表
            showReportType17();
        }else if("19"==type){
            //热熔对接
            showReportType19();
        }else if("24"==type){
            //焊口记录
            showReportType24();
        }else if ("16"===type){
			//试压记录
			showReportType16();
		}

    }














    var isAuditFunction = function(){
        var isAudit = $("#isAudit").val();
        if(isAudit === "1"){
            //审核过
            auditSaveCallBack("true");
        }else{
            $(".signPicture").attr("src","attachments/sign/"+$("#signPicture").val())
        }
    }();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->