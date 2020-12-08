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
								<select type="text" class="form-control input-sm select-filter" name="budgeter">
									<option value="">编制人</option>
									<c:forEach var="enums" items="${budgetList}">
										<option value="${enums}">${enums}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="budgetCostScale">
									<option value="">造价范围</option>
									<option value="1">2万以下</option>
									<option value="2">2万至5万</option>
									<option value="3">5万至20万</option>
									<option value="4">20万至50万</option>
									<option value="5">50万至200万</option>
									<option value="6">200万以上</option>
								</select>
							</div>
						</div>
						<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default " placeholder="接图开始日期" name="duCompleteDateStart" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default " placeholder="接图截止日期" name="duCompleteDateEnd" value=""/>
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
	  //工程名称
	  if(formData.projName !== ''){
		  sql = sql+" and pro.proj_name like'%"+formData.projName+"%'";
	  }
       //公司ID
       if($('#corpId').val() != ''){
           sql=sql+" and pro.corp_id like '"+$('#corpId').val()+"%'";
       }

	  //工程编号
	  if(formData.projNo !== ''){
		  sql = sql+" and pro.proj_no like '%"+formData.projNo+"%'";
	  }

	  //编制人
	  if(formData.budgeter !== ''){
		  sql = sql+" and pro.BUDGETER like '%"+formData.budgeter+"%'";
	  }
	  //预算范围
	  if(formData.budgetCostScale !== ''){
		  if(formData.budgetCostScale=="1"){
			  sql = sql+" and pro.CONFIRM_TOTAL_COST<=20000";
		  }else if(formData.budgetCostScale=="2"){
			  sql = sql+" and pro.CONFIRM_TOTAL_COST>20000 and pro.CONFIRM_TOTAL_COST<=50000";
		  }else if(formData.budgetCostScale=="3"){
			  sql = sql+" and pro.CONFIRM_TOTAL_COST>50000 and pro.CONFIRM_TOTAL_COST<=200000";
		  }else if(formData.budgetCostScale=="4"){
			  sql = sql+" and pro.CONFIRM_TOTAL_COST>200000 and pro.CONFIRM_TOTAL_COST<=500000";
		  }else if(formData.budgetCostScale=="5"){
			  sql = sql+" and pro.CONFIRM_TOTAL_COST>500000 and pro.CONFIRM_TOTAL_COST<=2000000";
		  }else if(formData.budgetCostScale=="6"){
			  sql = sql+" and pro.CONFIRM_TOTAL_COST>2000000";
		  }
	  }
	
	  //接图开始日期
	  if(formData.duCompleteDateStart !== ''){
		 // sql = sql+" and di.DU_COMPLETE_DATE>=to_date('"+formData.duCompleteDateStart+"','YYYY-MM-DD')";
		  sql = sql+" and di.DU_COMPLETE_DATE>= "+sqlDateConveter("mysql",formData.duCompleteDateStart,1);
	  }
	  //接图结束日期
	  if(formData.duCompleteDateEnd !== ''){
		  //sql = sql+" and di.DU_COMPLETE_DATE<=to_date('"+formData.duCompleteDateEnd+"','YYYY-MM-DD')";
		  sql = sql+" and di.DU_COMPLETE_DATE<= "+sqlDateConveter("mysql",formData.duCompleteDateEnd,1);
	  }
	  //工程类型
      if(formData.projectType !== ''){
          sql = sql+" and pro.project_type ='"+formData.projectType+"'";
      }
      //出资方式
      if(formData.contributionMode !== ''){
          sql = sql+" and pro.contribution_mode = '"+formData.contributionMode+"'";
      }
	  return sql;
   }
   //加载打印预览
   var showReport = function(){
	   var condition = encodeURIComponent(cjkEncode(conditions()));
	   var staffName = encodeURIComponent(cjkEncode($("#staffName").val()));
   	src="<%=basePath%>/ReportServer?reportlet=statisticalquery/budgetCostStatistics.cpt&__bypagesize__=false&condition="+condition+"&staffName="+staffName;
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


