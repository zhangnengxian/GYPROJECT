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
					 <input class="hidden" id="unitType" value="${unitType}"/>
                    <input class="hidden" id="deptId" value="${deptId}"/>
					<input class="hidden" id="corpId" value="${corpId}"/>

					<form class="" id="projectConditionForm" action="/" method="POST">
						<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter department" onchange="departmentChange(this.value)" name="department">
									<option value="">公 司</option>
									<c:forEach var="department" items="${departmentList}">
										<option value="${department.deptId}" >${department.deptName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
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
				                <input type="text" class="form-control input-sm datepicker-default " placeholder="立项开始日期" name="acceptDateStart" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default " placeholder="立项截止日期" name="acceptDateEnd" value=""/>
				            </div>
				      	</div> 
				      	<div class="form-group"> 
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default " placeholder="签订开始日期" name="signDateStart" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default " placeholder="签订截止日期" name="signDateEnd" value=""/>
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


    //选择分公司时改变默认的分公司ID
    function departmentChange(paramValue) {
        if(paramValue!=''){//选择的分公司
            $('#corpId').val(paramValue);
        }else {//默认的分公司
            $('#corpId').val('${corpId}');
        }
    }
	
   var conditions = function(){
	  var formData =  $("#projectConditionForm").serializeJson();
	  var sql = '';
	  if($("#unitType").val()=='1'){
		  sql=sql+" and PRO.dept_id like '"+$("#deptId").val()+"%'";
	  }else if($("#unitType").val()=='4'){
		  sql=sql+" and CP.su_id ='"+$("#deptId").val()+"'";
	  }else if($("#unitType").val()=='5'){
		  sql=sql+" and CP.cu_id='"+$("#deptId").val()+"'";
	  }
	  //工程编号
	  if(formData.projNo !== ''){
		  sql=sql+" and PRO.proj_no like '%"+formData.projNo+"%'";
	  }
       //公司ID
       if($('#corpId').val() != ''){
           sql=sql+" and PRO.corp_id like '"+$('#corpId').val()+"%'";
       }
	  //工程名称
	  if(formData.projName !== ''){
		  sql = sql+" and PRO.proj_name like'%"+formData.projName+"%'";
	  }
	  //工程类型
      if(formData.projectType !== ''){
          sql = sql+" and PRO.project_type ='"+formData.projectType+"'";
      }
      //出资方式
      if(formData.contributionMode !== ''){
          sql = sql+" and PRO.contribution_mode = '"+formData.contributionMode+"'";
      }
	  //分包公司
	  if(formData.cuNameId !== ''){
		  sql = sql+" and CP.cu_id ='"+formData.cuNameId+"'";
	  }
	  //监理公司
	  if(formData.suId !== ''){
		  sql = sql+" and CP.su_id ='"+formData.suId+"'";
	  }
	  //甲方代表
	  if(formData.builder !== ''){
		  sql = sql+" and CP.builder like '%"+formData.builder+"%'";
	  }
 	  //立项开始日期
 	  if(formData.acceptDateStart !== ''){
 		  sql = sql+" and PRO.accept_date>= "+sqlDateConveter("mysql",formData.acceptDateStart,1);
 	  }
 	  //立项结束日期
 	  if(formData.acceptDateEnd !== ''){
 		  sql = sql+" and PRO.accept_date<= "+sqlDateConveter("mysql",formData.acceptDateEnd,1);
 	  }
 	 //合同签订开始日期
 	  if(formData.signDateStart !== ''){
 		  sql = sql+" and PRO.sign_date>= "+sqlDateConveter("mysql",formData.signDateStart,1);
 	  }
 	  //合同签订结束日期
 	  if(formData.signDateEnd !== ''){
 		  sql = sql+" and PRO.sign_date<= "+sqlDateConveter("mysql",formData.signDateEnd,1);
 	  }
	  return sql;
   }
   //加载打印预览
   var showReport = function(){
	   var condition = encodeURIComponent(cjkEncode(conditions()));
	   var staffName = encodeURIComponent(cjkEncode($("#staffName").val()));
   	src="<%=basePath%>/ReportServer?reportlet=statisticalquery/financialStatistics.cpt&__bypagesize__=false&condition="+condition+"&staffName="+staffName;
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


