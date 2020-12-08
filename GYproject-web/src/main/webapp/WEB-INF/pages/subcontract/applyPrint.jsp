<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

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
	                <h4 class="panel-title">付款申请列表</h4>
                </div>
                <div class="panel-body">
					<input type="hidden" id="imgUrl" name="imgUrl" value="${imgUrl}">
	                <div class="tab-content">
						<div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
						<table id="applyPrintTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
							<thead>
								<tr>
									<th>工程ID</th>
									<th>工程编号</th>
									<th>工程名称</th>
									<th>工程类型</th>
									<th>审核状态</th>
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


<script>
	App.restartGlobalFunction();
	App.setPageTitle('申请打印 - 工程管理系统');
    $.getScript('projectjs/subcontract/apply-print.js?'+Math.random()).done(function () {
        applyPrint.init();
    });

    //日期datepicker
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    var basePath=window.location.protocol+'//'+window.location.host;
    var jsonObj={};

    $(function (){//加载页面时加载
        jsonObj.projId=null;
        jsonObj.menuId=getStepId();
        queryCptUrl(jsonObj);
    });

    //查询cpt
    function queryCptUrl(json) {
        $.ajax({
            type: 'POST',
            url: 'applyPrintController/queryCptUrl',
            data:{'projId':json.projId,'menuId':json.menuId},
            success: function (cptURL) {
                json.cptURL=cptURL;//将获取的cptULR添加到json对象属性中
                showReport(json)
            },
            error: function (XMLHttpRequest) {
                alert(XMLHttpRequest.status);
            }
        });
    }


    //加载报表
    var showReport = function(json){
        var src = basePath + "/ReportServer?reportlet=subContract/"+json.cptURL+"&projId="+json.projId+"&imgUrl="+$('#imgUrl').val()+"&legalApplyAmount="+json.legalApplyAmount;;
        $("#mainFrm").attr("src",src);
    };



    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
	


</script>
