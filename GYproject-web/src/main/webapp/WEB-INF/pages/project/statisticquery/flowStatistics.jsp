<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- projectChargeBackReport.jsp -->
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
					<input class="hidden" id="staffName" value="${staffName}" />
					<input class="hidden" id="unitType" value="${unitType}"/>
                    <input class="hidden" id="deptId" value="${deptId}"/>
					<input class="hidden" id="corpId" value="${corpId}"/>

					<form class="" id="flowStatisticsForm" action="/" method="POST">
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
								<select type="text" class="form-control input-sm select-filter" name="costScale">
									<option value="">收款范围</option>
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
							<label class="control-label" for="">是否开票</label>
							<div>
								<label> <input type="radio" class="field-not-editable"
									name="invoice" value="1" checked/>已开票
								</label> <label> <input type="radio" class="field-not-editable"
									name="invoice" value="0"/>未开票
								</label>
							</div>
						</div>
						<div class="form-group">
							<div>
								<input type="text"
									class="form-control input-sm datepicker-default"
									placeholder="收款日期(起)" name="cfDateStart" id="cfDateStart" value="" />
							</div>
						</div>
						<div class="form-group">
							<div>
								<input type="text"
									class="form-control input-sm datepicker-default"
									placeholder="收款日期(止)" name="cfDateEnd" id="cfDateEnd" value="" />
							</div>
						</div>
						<div class="form-group">
							<div>
								<input type="text"
									class="form-control input-sm datepicker-default"
									placeholder="开票日期(起)" name="invoiceDateStart" id="invoiceDateStart" value="" />
							</div>
						</div>
							<div class="form-group">
							<div>
								<input type="text"
									class="form-control input-sm datepicker-default"
									placeholder="开票日期(止)" name="invoiceDateEnd" id="invoiceDateEnd" value="" />
							</div>
						</div>
					<!-- 	<div class="form-group">
							<div>
								<input type="text"
									class="form-control input-sm datepicker-default projectAcceptData"
									placeholder="受理开始日期" name="acceptDateStart" value="" />
							</div>
						</div>
						<div class="form-group">
							<div>
								<input type="text"
									class="form-control input-sm datepicker-default projectAcceptData"
									placeholder="受理截止日期" name="acceptDateEnd" value="" />
							</div>
						</div> -->
					</form>
					<div class="toolBtn pull-right m-t-5">
						<button type="reset" form="flowStatisticsForm"
							class="btn btn-sm btn-send reset">重置</button>
						<a href="javascript:;"
							class="btn btn-sm btn-confirm proj-condition-btn">查询</a>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-10 col-xs-12">
			<div class="panel panel-inverse" id="content">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-default"
							data-click="panel-expand"><i class="fa fa-expand"></i></a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-success"
							data-click="panel-reload"><i class="fa fa-repeat"></i></a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-warning"
							data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">报表</h4>
				</div>
				<div class="panel-body">
					<div class="iframe-report-box">
						<iframe id="mainFrm" class="iframe-report"
							style="width: 1123px; height: 794px; border: 0; overflow-y: auto;"
							scrolling="no"></iframe>
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
	App.setPageTitle('报表查询 - 收款开票明细报表 ');
	
	$('.datepicker-default').datepicker({
	    todayHighlight: true
	});
	
	<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";%>
	
	function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
	
	//查询按钮监听
	$(".proj-condition-btn").off().on("click",function(){
		var cfDateStart = $("#cfDateStart").val();
		var cfDateEnd = $("#cfDateEnd").val();
		var invoiceDateStart = $("#invoiceDateStart").val();
		var invoiceDateEnd = $("#invoiceDateEnd").val();
        var pattern = /[\u4E00-\u9FA5]+/;  //正则表达式验证是否是汉字
      //判断起始日期是否小于截止日期
    	if((cfDateEnd.length >0)){
    		if(pattern.test(cfDateEnd)){
    			$("#cfDateEnd").attr("value","请选择日期").css("color","blue");
    	    	return '';
    		}else  if(cfDateStart >= cfDateEnd){
    	    	$("#cfDateEnd").attr("value","截止日期需大于起始日期").css("color","red");
    	    	return '';
    	    }}
        if(invoiceDateEnd.length > 0){
    	    	if(pattern.test(invoiceDateEnd)){
        			$("#invoiceDateEnd").attr("value","请选择日期").css("color","blue");
        	    	return '';
        		}else  if(invoiceDateStart >= invoiceDateEnd){
        	    	$("#invoiceDateEnd").attr("value","截止日期需大于起始日期").css("color","red");
        	    	return '';
        	    }}
	       showReport();
  });
	//重置按钮监听
	$(".reset").off().on("click",function(){
		$("#cfDateEnd").attr("value","");
		$("#invoiceDateEnd").attr("value","");
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
	  var formData =  $("#flowStatisticsForm").serializeJson();
	  var sql = '';
	  sql=sql+" and p.dept_id like '"+$("#deptId").val()+"%'";
	  //收款范围
	  if(formData.costScale !== ''){
		  if(formData.costScale=="1"){
			  sql = sql+" and C.CF_AMOUNT<=20000";
		  }else if(formData.costScale=="2"){
			  sql = sql+" and C.CF_AMOUNT>20000 and C.CF_AMOUNT<=50000";
		  }else if(formData.costScale=="3"){
			  sql = sql+" and C.CF_AMOUNT>50000 and C.CF_AMOUNT<=200000";
		  }else if(formData.costScale=="4"){
			  sql = sql+" and C.CF_AMOUNT>200000 and C.CF_AMOUNT<=500000";
		  }else if(formData.costScale=="5"){
			  sql = sql+" and C.CF_AMOUNT>500000 and C.CF_AMOUNT<=2000000";
		  }else if(formData.costScale=="6"){
			  sql = sql+" and C.CF_AMOUNT>2000000";
		  }
	  }
	  //工程类型
      if(formData.projectType !== ''){
          sql = sql+" and p.project_type ='"+formData.projectType+"'";
      }
       //公司ID
       if($('#corpId').val() != ''){
           sql=sql+" and p.corp_id like '"+$('#corpId').val()+"%'";
       }
      //出资方式
      if(formData.contributionMode !== ''){
          sql = sql+" and p.contribution_mode = '"+formData.contributionMode+"'";
      }
	  //是否开票
	  if(formData.invoice !== ''){
		  sql = sql + " AND C.INVOICE = '"+formData.invoice+"'";
	  }
	  //收款日期(起)
	  if(formData.cfDateStart !== ''){
		  sql = sql + " AND C.cf_date >= "+sqlDateConveter("mysql",formData.cfDateStart,1);
	  }
	  //收款日期（止）
	  if(formData.cfDateEnd !== ''){
		  sql = sql + " AND C.cf_date <="+sqlDateConveter("mysql",formData.cfDateEnd,1);
	  }
	  //开票日期（起）
	  if(formData.invoiceDateStart !== ''){
		  sql = sql + " AND c.invoice_date >="+sqlDateConveter("mysql",formData.invoiceDateStart,1);
	  } 
	  //开票日期（止）
	  if(formData.invoiceDateEnd !== ''){
		  sql = sql + " And c.invoice_date <="+sqlDateConveter("mysql",formData.invoiceDateEnd,1);
	  }
	/*   //受理开始日期
	  if(formData.acceptDateStart !== ''){
		  sql = sql+" and "+sqlDateConveter("mysql","P.accept_date",1) + ">="+sqlDateConveter("mysql",formData.acceptDateStart,1);
	  }
	  //受理结束日期
	   if(formData.acceptDateEnd !== ''){
		   sql = sql+" and "+sqlDateConveter("mysql","P.accept_date",1) + "<="+sqlDateConveter("mysql",formData.acceptDateEnd,1);
	   } */
	  return sql;
   }
   //加载打印预览
   var showReport = function(){
	   var condition = encodeURIComponent(cjkEncode(conditions()));
	   var staffName = encodeURIComponent(cjkEncode($("#staffName").val()));
	   src="<%=basePath%>/ReportServer?reportlet=statisticalquery/flowStatistics.cpt&condition="+ condition + "&staffName=" + staffName;
		$("#mainFrm").attr("src", src);
	}
	//打印预览窗口缩放调整
	if ($(".iframe-report").length > 0) {
		var fr = $(".iframe-report");
		for (var i = 0; i < fr.length; i++) {
			fr.eq(i).rescaleReportPanel();
		}
	}
	showReport();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->


