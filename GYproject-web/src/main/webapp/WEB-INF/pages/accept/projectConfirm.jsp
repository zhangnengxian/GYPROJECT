<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- projectConfirm.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
   		<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">工程列表</h4>
                </div>
                <div class="panel-body">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="projectConfirmTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                    	<thead>
                            <tr>
                            	<th>工程ID</th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>状态</th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
                    <%-- <input type="hidden" id="projStatus" name="projStatus" value="${projStatus}"/> --%>
                    <select class="hidden" id="projStatus"  name="projStatus"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${projStatus}">
							   	<c:choose>  
								   <c:when test="${'1001' == enums.value}">
								   		<option value="${enums.value}"  selected>${enums.message}</option>
								   </c:when>  
								   <c:otherwise>
								   		<option value="${enums.value}" >${enums.message}</option>
								   </c:otherwise>  
								</c:choose> 
		                </c:forEach>
		            </select>
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
			        <h4 class="panel-title">受理区</h4>
			    </div>
			    <div class="panel-body" id="project_confirm_panel_box">
			    </div>
			    <div class="iframe-big-box hidden">
		        	<div class="iframe-report-box">
	                 	<iframe id="mainFrm" class="iframe-report" style="width: 795px; height: 1123px;border:0; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('立项确认 - 工程项目管理系统 ');

	$.getScript('projectjs/accept/project-confirm.js').done(function () {
        projectConfirm.init();
    });
	
	    <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	var cptPath = '<%=basePath%>';
	src = cptPath+"/ReportServer?reportlet=accept/acceptSurvey.cpt";
	$("#mainFrm").attr("src",src);
	 //打印预览窗口缩放调整
	    if($(".iframe-report").length > 0){
	    	var fr = $(".iframe-report");
	    	for(var i=0; i<fr.length; i++){
	    		fr.eq(i).rescaleReportPanel();
	    	}
	    }
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->