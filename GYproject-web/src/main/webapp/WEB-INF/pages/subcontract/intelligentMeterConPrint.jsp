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
	                <h4 class="panel-title">智能表合同列表</h4>
                </div>
                <div class="panel-body">
					<input type="hidden" id="alreadyPrint" value="${alreadyPrint}"/>
					<input type="hidden" id="haveNotPrint" value="${haveNotPrint}"/>
	                <div class="tab-content">
						<div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
						<table id="intelligentMeterConPrintTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
							<thead>
								<tr>
									<th>合同编号</th>
									<th>工程名称</th>
									<th>签定日期</th>
									<th>共计工程款(元)</th>
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


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>

	App.restartGlobalFunction();
	App.setPageTitle('智能表合同打印 - 工程管理系统');

    var basePath=window.location.protocol + '//' + window.location.host;
    var menuId='110411';//智能表合同打印
	var jsonObj={};

	//日期datepicker
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

	$(function (){//加载页面时加载
        jsonObj.projId=null;
        jsonObj.menuId=menuId;
        queryCptUrl(jsonObj);
    });

	//查询cpu
	function queryCptUrl(json) {
        $.ajax({
            type: 'POST',
            url: 'intelligentMeterConPrint/queryCptUrl',
            data:{'projId':json.projId,'menuId':json.menuId},
            success: function (data) {
                json.cptURL=data;//将获取的cptULR添加到json对象属性中
                showReport(json)
            },
            error: function (XMLHttpRequest) {
                alert(XMLHttpRequest.status);
            }
        });
    }


    //加载报表
    var showReport = function(json){
        var imcId=json.imcId;
		var totalCostLegalAmount = encodeURIComponent(cjkEncode(json.totalCostLegalAmount));
        var unitCostLegalAmount = encodeURIComponent(cjkEncode(json.unitCostLegalAmount));
        var firstPayMentLeaglAmount = encodeURIComponent(cjkEncode(json.firstPayMentLeaglAmount));
        if(imcId==''){
			imcId='-1';
		}
		src = basePath+"/ReportServer?reportlet=intelligentMeterCon/"+json.cptURL+"&imcId="+imcId+"&totalCostLegalAmount="+totalCostLegalAmount+"&unitCostLegalAmount="+unitCostLegalAmount+"&firstPayMentLeaglAmount="+firstPayMentLeaglAmount;
        src = src + "&payType="+json.payType;
		$("#mainFrm").attr("src",src);
	};



    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}



    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
	$.getScript('projectjs/subcontract/intelligentMeterCon-print.js?v=1017').done(function () {
	    intelligentMeterConPrint.init();
	});
	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->