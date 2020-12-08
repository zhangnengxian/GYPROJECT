<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
        <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">转固申请列表</h4>
                </div>
                <div class="panel-body">
                	<input type="hidden" class="imgUrl" value="${imgUrl}"/>
                	<input type="hidden" id="alreadyPrint" value="${alreadyPrint}"/>
					<input type="hidden" id="haveNotPrint" value="${haveNotPrint}"/>
                    <div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="turnFixedPrintTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th width="120"></th>
                            	<th width="120">工程编号</th>
                            	<th width="120">工程名称</th>
                            	<th width="120">工程类型</th>
                            	<th width="120">出资方式</th>
                                <th width="120">合同号</th>
                                <th width="160">转固时间</th>
                                <th width="160">工程总值</th>
                                <th width="120">工程款</th>
                            </tr>
                        </thead>
                    </table>
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
			    <div class="panel-body">
			      <div class="iframe-big-box ">
			    	<div class="iframe-report-box">
	                 	<iframe id="mainFrm" class="iframe-report" style="width: 1200px; height: 795px;border:0; overflow-y:auto;" scrolling="no"></iframe>
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
	App.setPageTitle('结算审核打印 - 工程管理系统');
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%> 
	var cptPath = '<%=basePath%>';
	$.getScript('projectjs/settlement/turn-fixed-print.js').done(function () {
		settlementAuditPrint.init();
	});
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var showReport = function(){
	    	var tfaId='',
			projId='-1';
		if(trSData.turnFixedPrintTable){
			tfaId = encodeURIComponent(cjkEncode(trSData.json.tfaId));
			projId = encodeURIComponent(cjkEncode(trSData.json.projId));
		}
		src = cptPath+"/ReportServer?reportlet=settlement/turnFixedApply.cpt&tfaId=" + tfaId + "&projId=" + projId+"&imgUrl="+$(".imgUrl").val();
	    $("#mainFrm").attr("src",src);
    }
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
showReport();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->