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
                    <h4 class="panel-title">委托打印列表</h4>
                </div>
                <div class="panel-body">
                	<input type="hidden" id="alreadyPrint" value="${alreadyPrint}"/>
					<input type="hidden" id="haveNotPrint" value="${haveNotPrint}"/>

                    <input type="hidden" id="finishDate" name="finishDate"/>
                    <div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="supplementalContractPrintTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                        <tr>
                            <th>工程Id</th>
                            <th>工程编号</th>
                            <th>工程名称</th>
                            <th>委托日期</th>
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
			        <h4 class="panel-title">览区</h4>
			    </div>
			  	<div class="panel-body">
			        <div class="iframe-big-box ">
			        	<div class="iframe-report-box ">
		                 	<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:0; overflow-y:auto;" scrolling="no"></iframe>
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
	App.setPageTitle('委托打印 - 工程管理系统');

	var basePath=window.location.protocol + '//' + window.location.host;

    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
   
	$.getScript('projectjs/design/design-drawing-print.js?v='+Math.random()).done(function () {
	    supplementalContractPrint.init();
	});

    function queryCpt(json) {
        $.ajax({
            type:'post',
            url:'designDrawingPrint/queryCptUrl',
            data:{'projId':json.projId,'menuId':getStepId()},
            success:function (cptUrl) {
                console.info("cptUrl:--"+cptUrl);
                json.cptUrl=cptUrl;
                showReport(json);
            },
            error: function (XMLHttpRequest) {
                alert(XMLHttpRequest.status);
            }
        });
    }


    function showReport(json) {
        console.info(2019,json.diId);
        var src = basePath+"/ReportServer?reportlet=design/"+json.cptUrl+"&diId="+json.diId+"&finishDate="+json.duCompleteDate;
        console.info("src-",src);
        $("#mainFrm").attr("src",src);
    }


</script>
<!-- ================== END PAGE LEVEL JS ================== -->