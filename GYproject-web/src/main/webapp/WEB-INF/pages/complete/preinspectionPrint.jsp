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
                        <li class="active"><a href="#default-tab-1" id="listTab" data-toggle="tab">预验收列表</a></li>
                        <li class=""><a href="#default-tab-2"  id="modifyTab"  data-toggle="tab">详细信息</a></li>
                    </ul>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane fade active in btn-top" id="default-tab-1" >
                            <input type="hidden" class="imgUrl"  value="${imgUrl}"/>
                            <input type="hidden" id="piId"  name="pdId"/>
                            <input type="hidden" id="projId"  name="projId"/>
                            <input type="hidden" id="loginPost" value="${loginPost}"/>
                            <input type="hidden" id="dataAdmin"  value="${dataAdmin}"/>
                            <div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                            <table id="preinspectionPrintTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                                <thead>
                                    <tr>
                                        <th>自检单ID</th>
                                        <th>工程编号</th>
                                        <th>工程名称</th>
                                        <th>预验日期</th>
                                        <th>工程ID</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>



                        <div class="tab-pane fade btn-top" id="default-tab-2">
                            <div class="toolBtn f-r p-b-10">
                                <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 hidden savebtn" onclick="saveClick()" >保存</a>
                            </div>
                            <form class="form-horizontal" id="preinspectionForm" >
                                <input type="hidden" name="projId" />
                                <input type="hidden" name="piId"/>
                                <div class="form-group col-md-12 col-sm-12">
                                    <label class="control-label" for="projName">工程名称</label>
                                    <div>
                                        <input class="form-control input-sm field-not-editable" name="projName" id="projName" value="">
                                    </div>
                                </div>
                                <div class="form-group  col-md-6 col-sm-6">
                                    <label class="control-label" for="projNo">工程编号</label>
                                    <div>
                                        <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" value=""/>
                                    </div>
                                </div>
                                <div class="form-group col-md-6 col-sm-12">
                                    <label class="control-label" for="">工程类型</label>
                                    <div>
                                        <input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label " for="">计划开工日期</label>
                                    <div>
                                        <input type="text" class=" form-control input-sm field-not-editable allText datepicker-default " id="cpArriveDate"  name="cpArriveDate" value="">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label" for="">实际开工日期</label>
                                    <div>
                                        <input type="text" class=" form-control input-sm field-not-editable datepicker-default allText timestamp" id="plannedStartDate"  name="plannedStartDate" value="">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label">计划竣工日期</label>
                                    <div>
                                        <input type="text" class=" form-control input-sm field-not-editable allText datepicker-default timestamp" id="planCompleteDate"  name="planCompleteDate" >
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label">实际竣工日期</label>
                                    <div>
                                        <input type="text" class=" form-control input-sm field-not-editable allText datepicker-default" id="actualCompleteDate"  name="actualCompleteDate" >
                                    </div>
                                </div>
                                <div class="form-group col-md-12">
                                    <label class="control-label" for="sirOpinion">验收意见</label>
                                    <div>
                                        <textarea class=" form-control input-sm field-editable allText allText suJgj" id="sirOpinion"  name="sirOpinion" rows="4" data-parsley-maxlength="200" ></textarea>
                                    </div>
                                </div>

                                <div class="form-group  col-md-6 allSign cuPm">
                                    <label class="control-label signature-tool sign-require" for="projManager">项目经理</label>
                                    <div>
                                        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
                                        <input type="hidden" id="projManager" name="projManager" value="" class="sign-data-input">
                                        <input type="hidden" class="signPost"  id="projManager_postType" name="projManager_postType" value="${cuPm }">
                                        <img class="" alt="" style="height: 30px">
                                        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label">日期</label>
                                    <div>
                                        <input type="text" class=" form-control input-sm field-not-editable allText datepicker-default" id="pmDate"  name="pmDate" >
                                    </div>
                                </div>
                                <div class="form-group  col-md-6 allSign suJgj clearboth">
                                    <label class="control-label signature-tool sign-require" for="suFieldJgj">现场监理</label>
                                    <div>
                                        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
                                        <input type="hidden" id="suFieldJgj" name="suFieldJgj" value="" class="sign-data-input">
                                        <input type="hidden" class="signPost"  id="suFieldJgj_postType" name="suFieldJgj_postType" value="${sujgj }">
                                        <img class="" alt="" style="height: 30px">
                                        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
                                    </div>
                                </div>

                                <div class="form-group col-md-6">
                                    <label class="control-label">日期</label>
                                    <div>
                                        <input type="text" class=" form-control input-sm field-not-editable allText datepicker-default" id="cesDate"  name="cesDate" >
                                    </div>
                                </div>
                                <c:import url="cmoDirectorSignPage.jsp"></c:import>
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
	                 	<iframe id="mainFrm" class="iframe-report" style="width: 820px; height: 1200px;border:0; overflow-y:auto;" scrolling="no"></iframe>
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
	App.setPageTitle('预验收打印 - 工程管理系统');
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $("#preinspectionForm").toggleEditState(false);
    $("#preinspectionForm").styleFit();

    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
	
	var cptPath = '<%=basePath%>';
	
	$.getScript('projectjs/complete/preinspection-print.js?'+Math.random()).done(function () {
		preinspectionPrint.init();
	});

    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var showReport = function(){
    	var projId=$("#projId").val(),piId=$("#piId").val();
    	
    	src = cptPath+"/ReportServer?reportlet=complete/"+cptType;
    	src = src+"&path=${imgUrl}"+"&projId="+projId+"&piId="+piId;
        $("#mainFrm").attr("src",src);
    }
    var src1 = cptPath+"/ReportServer?reportlet=complete/preinspectionPrint.cpt";
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