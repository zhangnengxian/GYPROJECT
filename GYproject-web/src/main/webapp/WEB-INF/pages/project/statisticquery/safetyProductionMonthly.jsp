<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- projectChargeBackReport.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
		<%-- <div class="col-sm-2 col-xs-12">
			<div class="panel panel-inverse">
				<div class="panel-heading">
<!-- 					<div class="panel-heading-btn"> -->
<!-- 						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a> -->
<!-- 						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a> -->
<!-- 						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a> -->
<!-- 					</div> -->
					<h4 class="panel-title">检索条件</h4>
				</div>
				<div class="panel-body">
					<input class="hidden" id="staffName" value="${staffName}"/>
					<form class="" id="projectConditionForm" action="/" method="POST">
<!-- 						<div class="form-group"> -->
<!-- 				            <div> -->
<!-- 				                <input type="text" class="form-control input-sm" placeholder="受理年份" name="cuName" value=""/> -->
<!-- 				            </div> -->
<!-- 				      	</div> -->
<!-- 				      	<div class="form-group"> -->
<!-- 				            <div> -->
<!-- 				                <input type="text" class="form-control input-sm datepicker-default " placeholder="计划下达开始日期" name="cpArriveDateStart" value=""/> -->
<!-- 				            </div> -->
<!-- 				      	</div> -->
<!-- 				      	<div class="form-group"> -->
<!-- 				            <div> -->
<!-- 				                <input type="text" class="form-control input-sm datepicker-default " placeholder="计划下达截止日期" name="cpArriveDateEnd" value=""/> -->
<!-- 				            </div> -->
<!-- 				      	</div> -->
					</form>
					<div class="toolBtn pull-right m-t-5">
						<button type="reset" form="projectConditionForm" class="btn btn-sm btn-send reset">重置</button>
						<a href="javascript:;" class="btn btn-sm btn-confirm proj-condition-btn">查询</a>
					</div>
				</div>
			</div>
		</div> --%>
		<div class="col-sm-12 col-xs-12" >
			<div class="panel panel-inverse" id="content">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">报表</h4>
			    </div>
				<div class="panel-body">
					<div class="iframe-report-box">
						<iframe id="mainFrm" class="iframe-report" style="width: 1123px; height: 795px;border:0; overflow-y:auto;" scrolling="no"></iframe>
					</div>
			    </div>
			</div>
		</div>
	</div>
</div>
<!-- end #content -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
	App.setPageTitle('报表查询 - 工程项目报表 ');
	
	$('.datepicker-default').datepicker({
	    todayHighlight: true
	});
	
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	
	function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
	
	//查询按钮监听
	$(".proj-condition-btn").off().on("click",function(){
    	showReport();
    });
	
	
   var conditions = function(){
	  var formData =  $("#projectConditionForm").serializeJson();
	  var sql = '';
	  //分包公司
	  if(formData.cuName !== ''){
		  sql = sql+" and s.cu_name like '%"+formData.cuName+"%'";
	  }
 	  //计划下达开始日期
// 	  if(formData.cpArriveDateStart !== ''){
// 		  sql = sql+" and c.cp_arrive_date>="+sqlDateConveter("mysql",formData.cpArriveDateStart,1);
// 	  }
// 	  //计划下达结束日期
// 	  if(formData.cpArriveDateEnd !== ''){
// 		  sql = sql+" and c.cp_arrive_date<="+sqlDateConveter("mysql",formData.cpArriveDateEnd,1);
// 	  }
	  return sql;
   }
   //加载打印预览
   var showReport = function(){
	   var condition = encodeURIComponent(cjkEncode(conditions()));
	   var staffName = encodeURIComponent(cjkEncode($("#staffName").val()));
   	//console.log("showReport:"+condition);
   	src="<%=basePath%>/ReportServer?reportlet=statisticalquery/safetyProductionMonthly.cpt&condition="+condition+"&staffName="+staffName;
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


