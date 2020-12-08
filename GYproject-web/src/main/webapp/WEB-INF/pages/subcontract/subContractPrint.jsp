<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	                <h4 class="panel-title">分包合同列表</h4>
                </div>
                <div class="panel-body">
					<input type="hidden" id="alreadyPrint" value="${alreadyPrint}"/>
					<input type="hidden" id="haveNotPrint" value="${haveNotPrint}"/>
					<input type="hidden" id="imgUrl" value="${imgUrl}"/>
				<div id="costTypeDes" class="pull-left">
						 <select id="contractVersion" class="form-control input-sm field-editable width-250" name="contractVersion">
					        <option  value="" selected >选择合同打印版本</option>
       						<c:forEach var="enums" items="${contractVersion}">
            					<option value="${enums.rvId}" id="${enums.rvId}">${enums.rvName}</option>
        					</c:forEach>
    					</select> 
    				</div>
	                <div class="tab-content">
						<div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
		
						<table id="subContractPrintTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
							<thead>
								<tr>
									<th>分包合同编号</th>
									<th>工程名称</th>
									<th>签定日期</th>
									<th>协议价款</th>
								</tr>
							</thead>
						</table>
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
			    	<div class="fine_report">
				    	<div class="iframe-report-box">
				    	<div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">读取中···</p></div></div>
		                 	<iframe id="mainFrm" class="iframe-report" style="width: 810px; height: 1180px;border:0; overflow-y:auto;" scrolling="no"></iframe>
		                </div>
		            </div>
	                <!-- <div class="subContract_print_box">
	                </div> -->
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
	App.setPageTitle('分包合同打印 - 工程管理系统');
	
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%> 
	
	var cptPath = '<%=basePath%>';

    //日期datepicker
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
	src = cptPath+"/ReportServer?reportlet=subContract/subContractPrint.cpt";
	$("#mainFrm").attr("src",src);

    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
	
	$.getScript('projectjs/subcontract/sub-contract-print.js?'+Math.random()).done(function () {
	    subContractPrint.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->