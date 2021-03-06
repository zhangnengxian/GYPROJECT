<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- projectChargeBackReport.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
		<div class="col-sm-2 col-xs-12">
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
						<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm" placeholder="工程编号" name="projNo" value=""/>
				            </div>
				      	</div>
						<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm" placeholder="工程名称" name="projName" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="projectType">
									<option value="">项目类型</option>
									<c:forEach var="enums" items="${projectType}">
										<option value="${enums.projTypeId }">${enums.projConstructDes }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="contributionMode">
									<option value="">出资方式</option>
									<c:forEach var="enums" items="${contributionMode}">
										<option value="${enums.id}" >${enums.contributionDes}</option>
									</c:forEach>
								</select>
							</div>
						</div>
				      	<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="stepName">
									<option value="">操作步骤</option>
									<c:forEach var="enums" items="${setpName}">
										<option value="${enums.value}">${enums.message}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="deptName">
									<option value="">操作部门</option>
									<c:forEach var="enums" items="${department}">
										<option value="${enums.deptId}">${enums.deptName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
				      	<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm" placeholder="操作人" name="staffName" value=""/>
				            </div>
				      	</div>
						<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default " placeholder="操作开始日期" name="operateTimeStart" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default " placeholder="操作截止日期" name="operateTimeEnd" value=""/>
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
		<div class="col-sm-10 col-xs-12" >
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
	  //工程编号
	  if(formData.projNo !== ''){
		  sql=sql+" and opr.proj_no='"+formData.projNo+"'";
	  }
	  //工程名称
	  if(formData.projName !== ''){
		  sql = sql+" and pro.proj_name like'%"+formData.projName+"%'";
	  }
	//工程类型
      if(formData.projectType !== ''){
          sql = sql+" and pro.project_type ='"+formData.projectType+"'";
      }
      //出资方式
      if(formData.contributionMode !== ''){
          sql = sql+" and pro.contribution_mode = '"+formData.contributionMode+"'";
      }
	  //步骤名称
	  if(formData.stepName !== ''){
		  sql = sql+" and opr.step_id ='"+formData.stepName+"'";
	  }
	  
	  //操作部门
	  if(formData.deptName !== ''){
		  sql = sql+" and de.dept_id ='"+formData.deptName+"'";
	  }
	  //操作人
	  if(formData.staffName !== ''){
		  sql = sql+" and staff.staff_name like'%"+formData.staffName+"%'";
	  }
	  //操作开始日期
	  if(formData.operateTimeStart !== ''){
		  sql = sql+" and opr.operate_time>="+sqlDateConveter("mysql",formData.operateTimeStart,1);
	  }
	  //操作结束日期
	  if(formData.operateTimeEnd !== ''){
		  sql = sql+" and opr.operate_time<="+sqlDateConveter("mysql",formData.operateTimeEnd,1);
	  }
	  return sql;
   }
   //加载打印预览
   var showReport = function(){
	   var condition = encodeURIComponent(cjkEncode(conditions()));
	   var staffName = encodeURIComponent(cjkEncode($("#staffName").val()));
   	//console.log("showReport:"+condition);
   	src="<%=basePath%>/ReportServer?reportlet=statisticalquery/operateRecordSatistic.cpt&condition="+condition+"&staffName="+staffName;
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


