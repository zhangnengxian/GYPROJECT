<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="content" class="content">
    <div class="row">
        <div class="col-sm-6 col-xs-12">
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
                    <%--<input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
                    <input type="hidden" id="stepId" name="stepId" value="${stepId }"/>--%>
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="projectTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                    	<thead>
                            <tr>
                            	<th>工程ID</th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>状态</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
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
                        <iframe id="mainFrm" class="iframe-report" style="width: 1123px; height:1201px; border: 0; overflow-y: auto;" scrolling="no"></iframe>
                    </div>
                </div>
			</div>
        </div>
    </div>
</div>


<script>
    App.restartGlobalFunction();
    App.setPageTitle('受理单打印 - 工程项目管理系统 ');

    var basePath=window.location.protocol+'//'+window.location.host;
    var jsonObj={};
    $(function (){//加载页面时加载
        jsonObj.projId=null;
        jsonObj.menuId='110211';
        queryCptUrl(jsonObj);
    });

    //查询cpu
    function queryCptUrl(json) {
        $.ajax({
            type: 'POST',
            url: 'acceptancePrintController/queryCptUrl',
            data:{'projId':json.projId,'menuId':json.menuId},
            success: function (cptURL) {
                json.cptURL=cptURL;//将获取的cptULR添加到json对象属性中
                showReport(json)//加载报表
            },
            error: function (XMLHttpRequest) {
                alert(XMLHttpRequest.status);
            }
        });
    }


    //加载报表
    var showReport = function(json){
        var src = basePath + "/ReportServer?reportlet=accept/"+json.cptURL+"&projId="+json.projId;
        $("#mainFrm").attr("src",src);
    };



    $.getScript('projectjs/accept/acceptance-print.js?'+Math.random()).done(function () {
        acceptancePrint.init();
    });

function cjkEncode(D){
    if(typeof D!=="string"){
        return D
    }
    var C="";
    for(var A=0;A<D.length;A++){
        var B=D.charCodeAt(A);
        if(B>=128||B==91||B==93){
            C+="["+B.toString(16)+"]"
        }else{
            C+=D.charAt(A)
        }
    }
    return C
}
 //打印预览窗口缩放调整
if($(".iframe-report").length > 0){
    var fr = $(".iframe-report");
    for(var i=0; i<fr.length; i++){
        fr.eq(i).rescaleReportPanel();
    }
}
</script>
