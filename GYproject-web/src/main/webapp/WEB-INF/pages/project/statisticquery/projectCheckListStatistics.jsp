<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
		<div class="col-sm-2 col-xs-12">
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">检索条件</h4>
				</div>
				<div class="panel-body">
					<input class="hidden" id="staffName" value="${staffName}"/>
					<form class="" id="projectConditionForm" action="/" method="POST">
						<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm" placeholder="工程名称" name="projName" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm" placeholder="工程编号" name="projNo" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="projStatus">
									<option value="">工程状态</option>
									<c:forEach var="enums" items="${projStatus}">
										<option value="${enums.value}" >${enums.message}</option>
									</c:forEach>
								</select>
							</div>
						</div>
				      	<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="cuNameId">
									<option value="">分包单位</option>
									<c:forEach var="enums" items="${culist}">
										<option value="${enums.unitId}" >${enums.shortName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="suId">
									<option value="">监理公司</option>
									<c:forEach var="enums" items="${sulist}">
										<option value="${enums.unitId}" >${enums.shortName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="builder">
									<option value="">甲方代表</option>
									<c:forEach var="enums" items="${builderList}">
										<option value="${enums}" >${enums}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="flag">
									<option value="">完成状态</option>
										<option value="0" >未完成</option>
										<option value="1" >已完成</option>
									
								</select>
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
						<iframe id="mainFrm" class="iframe-report" style="width: 1123px; height: 794px;border:0; overflow-y:auto;"></iframe>
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
		  sql=sql+" and p.proj_no like '%"+formData.projNo+"%'";
	  }
	  //工程名称
	  if(formData.projName !== ''){
		  sql = sql+" and p.proj_name like'%"+formData.projName+"%'";
	  }
	  //工程状态
	  if(formData.projStatus !== ''){
		  sql = sql+" and p.PROJ_STATUS_ID ='"+formData.projStatus+"'";
	  }else{
		  //默认施工中-待自检的
		  sql = sql+"and p.PROJ_STATUS_ID in ('1022')";
	  }
	  //分包公司
	  if(formData.cuNameId !== ''){
		  sql = sql+" and cp.cu_id ='"+formData.cuNameId+"'";
	  }
	  //监理公司
	  if(formData.suId !== ''){
		  sql = sql+" and cp.su_id ='"+formData.suId+"'";
	  }
	  //甲方代表
	  if(formData.builder !== ''){
		  sql = sql+" and cp.builder like '%"+formData.builder+"%'";
	  }
	  //完成状态
	  if(formData.flag !== ''){
		  sql = sql+" and pc.flag ='"+formData.flag+"'";
	  }
	  return sql;
   }
   //加载打印预览
   var showReport = function(){
	   var condition = encodeURIComponent(cjkEncode(conditions()));
	   var staffName = encodeURIComponent(cjkEncode($("#staffName").val()));
   	src="<%=basePath%>/ReportServer?reportlet=statisticalquery/projectCheckListStatics.cpt&condition="+condition+"&staffName="+staffName;
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


