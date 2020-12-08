<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
		<div class="col-sm-2 col-xs-12 hidden">
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">检索条件</h4>
				</div>
				<div class="panel-body">
					<input class="hidden" id="staffName" value="${staffName}"/>
					<form class="" id="projectConditionForm" action="/" method="POST">
						<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm" placeholder="姓名" name="staffName" value=""/>
				            </div>
				      	</div>
					</form>
					<div class="toolBtn pull-right m-t-5">
						<button type="reset" form="projectConditionForm" class="btn btn-sm btn-send reset">重置</button>
						<a href="javascript:;" class="btn btn-sm btn-confirm proj-condition-btn">查询</a>
					</div>
				</div>
			</div>
		</div>
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
						<iframe id="mainFrm" class="iframe-report" style="width: 1123px; height: 794px;border:0; overflow-y:auto;" scrolling="no"></iframe>
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
	App.setPageTitle('报表查询 - 通气工程竣工结算统计表 ');
	
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
	  //员工名称
	  if(formData.staffName !== ''){
		  sql=sql+" and s.staff_name like '%"+formData.staffName+"%'";
	  }
	  //合同范围
	  if(formData.budgetCostScale !== ''){
		  if(formData.budgetCostScale=="1"){
			  sql = sql+" and c.CONTRACT_AMOUNT<=20000";
		  }else if(formData.budgetCostScale=="2"){
			  sql = sql+" and c.CONTRACT_AMOUNT>20000 and c.CONTRACT_AMOUNT<=50000";
		  }else if(formData.budgetCostScale=="3"){
			  sql = sql+" and c.CONTRACT_AMOUNT>50000 and c.CONTRACT_AMOUNT<=200000";
		  }else if(formData.budgetCostScale=="4"){
			  sql = sql+" and c.CONTRACT_AMOUNT>200000 and c.CONTRACT_AMOUNT<=500000";
		  }else if(formData.budgetCostScale=="5"){
			  sql = sql+" and c.CONTRACT_AMOUNT>500000 and c.CONTRACT_AMOUNT<=2000000";
		  }else if(formData.budgetCostScale=="6"){
			  sql = sql+" and c.CONTRACT_AMOUNT>2000000";
		  }
	  }
	  //
	  if(formData.sendDeclaration !== ''){
		  if(formData.sendDeclaration=="1"){
			  sql = sql+" and c.SEND_DECLARATION<=20000";
		  }else if(formData.sendDeclaration=="2"){
			  sql = sql+" and c.SEND_DECLARATION>20000 and c.SEND_DECLARATION<=50000";
		  }else if(formData.sendDeclaration=="3"){
			  sql = sql+" and c.SEND_DECLARATION>50000 and c.SEND_DECLARATION<=200000";
		  }else if(formData.sendDeclaration=="4"){
			  sql = sql+" and c.SEND_DECLARATION>200000 and c.SEND_DECLARATION<=500000";
		  }else if(formData.sendDeclaration=="5"){
			  sql = sql+" and c.SEND_DECLARATION>500000 and c.SEND_DECLARATION<=2000000";
		  }else if(formData.sendDeclaration=="6"){
			  sql = sql+" and c.SEND_DECLARATION>2000000";
		  }
	  }
	  return sql;
   }
   //加载打印预览
   var showReport = function(){
	   var condition = encodeURIComponent(cjkEncode(conditions()));
	   var staffName = encodeURIComponent(cjkEncode($("#staffName").val())),
	   	   acceptDateStart = encodeURIComponent(cjkEncode($("#acceptDateStart").val())),
	  	   acceptDateEnd = encodeURIComponent(cjkEncode($("#acceptDateEnd").val()));
   	src="<%=basePath%>/ReportServer?reportlet=statisticalquery/firstAuditStatistics.cpt&condition="+condition+"&staffName="+staffName+"&acceptDateStart="+acceptDateStart+"&acceptDateEnd="+acceptDateEnd;
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


