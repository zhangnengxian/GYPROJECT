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
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#default-tab-1" id="listTab" data-toggle="tab">工程自检列表</a></li>
                        <li class=""><a href="#default-tab-2"  id="modifyTab"  data-toggle="tab">详细信息</a></li>
                    </ul>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane fade active in btn-top" id="default-tab-1" >
                            <input type="hidden" class="imgUrl"  value="${imgUrl}"/>
                            <input type="hidden" id="alreadyPrint" value="${alreadyPrint}"/>
                            <input type="hidden" id="haveNotPrint" value="${haveNotPrint}"/>
                            <input type="hidden" id="loginPost" value="${loginPost}"/>
                            <input type="hidden" id="dataAdmin"  value="${dataAdmin}"/>
                            <div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                            <table id="projectSelfCheckPrintTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                                <thead>
                                    <tr>
                                        <th>自检单ID</th>
                                        <th>工程编号</th>
                                        <th>工程名称</th>
                                        <th>自检日期</th>
                                        <th>工程编号</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>

                        <div class="tab-pane fade btn-top" id="default-tab-2">
                            <div class="toolBtn f-r p-b-10">
                                <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 hidden savebtn" onclick="saveClick()" >保存</a>
                            </div>
                            <form class="form-horizontal" id="checkListForm" action="" data-parsley-validate="true">
                                <input type="hidden" name="projId" id="projId"/>
                                <div class="form-group col-md-12 col-sm-12">
                                    <label class="control-label" for="projName">工程名称</label>
                                    <div>
                                        <input class="form-control input-sm field-not-editable" name="projName" id="projName" value="">
                                    </div>
                                </div>
                                <div class="form-group  col-md-6 col-sm-6">
                                    <label class="control-label" for="projNo">工程编号</label>
                                    <div>
                                        <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" />
                                    </div>
                                </div>

                                <div class="form-group col-md-6 col-sm-12">
                                    <label class="control-label" for="">工程类型</label>
                                    <div>
                                        <input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
                                    </div>
                                </div>

                                <div class="form-group col-sm-12">
                                    <label class="control-label" for="projManagerOpinion">检查结果</label>
                                    <div>
                                        <textarea class="form-control field-editable allText" name="projManagerOpinion" id="projManagerOpinion" rows="4" cols="" data-parsley-maxlength="200"></textarea>
                                    </div>
                                </div>

                                <div class="form-group col-md-6 col-sm-6">
                                    <label class="control-label signature-tool sign-require" for="projManager">检查人</label>
                                    <div>
                                        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
                                        <input type="hidden" id="projManager" name="projManager" value="" class="sign-data-input">
                                        <img class="projManager" alt="" src="" style="height: 30px">
                                        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
                                    </div>
                                </div>
                                <div class="form-group col-md-6  col-sm-6" >
                                    <label class="control-label">自检日期</label>
                                    <div>
                                        <input type="text" class=" form-control input-sm field-not-editable allText datepicker-default " id="silDate"  name="silDate" >
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
            	</div>
        	</div>
        </div>
        <!-- end col-6 -->
        <!-- begin col-6 -->
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
			    <input type="hidden" id="sirId" name="sirId"/>
			    <div class="panel-body">
			    	<div class="iframe-report-box">
	                 	<iframe id="mainFrm" class="iframe-report" style="width: 795px; height: 1100px;border:0; overflow-y:auto;" scrolling="no"></iframe>
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
	App.setPageTitle('自检表打印 - 工程管理系统');
    $("#checkListForm").toggleEditState(false);
    $("#checkListForm").styleFit();
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
	
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%> 
	
	var cptPath = '<%=basePath%>';
	
	$.getScript('projectjs/complete/project-selfcheck-print.js?v=1017').done(function () {
		projectSelfCheckPrint.init();
	});

    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var showReport = function(){
    	
    	//console.info(trSData);
    	
    	var silId='-1';
    	if(trSData.json){
    		silId = encodeURIComponent(cjkEncode(trSData.json.silId));
    	}
    	imgUrl = $(".imgUrl").val();
    	
    	console.info("sirId----"+trSData.json.silId);
    	
    	src = cptPath+"/ReportServer?reportlet=complete/projectSelfCheckPrint.cpt";
    	src = src+"&path=${imgUrl}"+"&silId="+silId;
        $("#mainFrm").attr("src",src);
    }
    var src1 = cptPath+"/ReportServer?reportlet=complete/projectSelfCheckPrint.cpt";
    $("#mainFrm").attr("src",src1);
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
//showReport();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->