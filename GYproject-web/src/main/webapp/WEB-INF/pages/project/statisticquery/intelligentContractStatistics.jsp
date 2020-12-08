<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
   		<!-- begin col-6 -->
        <div class="col-lg-2 col-sm-3 col-xs-12">
            <!-- begin panel -->
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
				                <input type="text" class="form-control input-sm" placeholder="合同编号" name="conNo" value=""/>
				            </div>
				      	</div>
						<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm" placeholder="工程地点" name="projAddr" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm" placeholder="客户名称" name="custName" value=""/>
				            </div>
				      	</div>
						<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default" placeholder="签订开始日期" name="signDateStart" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default" placeholder="签订截止日期" name="signDateEnd" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
				            <div>
				            	<select class="form-control input-sm" placeholder="合同类型" id="contractType" name="contractType">
				            		<option value="1">安装合同</option>
				            		<option value="2">分包合同</option>
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
        <!-- begin col-6 -->
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
	                 	<iframe id="mainFrm" class="iframe-report" style="width: 1123px; height: 794px; border:0; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('智能表合同统计 - 工程项目报表 ');
  //日期datepicker
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
    //加载打印预览
    var showReport = function(){
    	var condition = encodeURIComponent(cjkEncode(conditions()));
    	var staffName = encodeURIComponent(cjkEncode($("#staffName").val()));
    	var src="";
    	if($('#contractType').val()=='1'){
    		src="<%=basePath%>/ReportServer?reportlet=statisticalquery/intelligentContracts.cpt&condition="+condition+"&staffName="+staffName;
    	}else{
    		//分包合同
    		src="<%=basePath%>/ReportServer?reportlet=statisticalquery/subIntelligentContracts.cpt&condition="+condition+"&staffName="+staffName;
    	}
    	$("#mainFrm").attr("src",src); 
    };

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
	  var sql1 = '';
	  var sql2 ='';
	  //工程编号
	  if(formData.projNo !== ''){
		  sql1=sql1+" and c.proj_no='"+formData.projNo+"'";
		  sql2=sql2+" and c.proj_no='"+formData.projNo+"'";
	  }
       //公司ID
       if($('#corpId').val() != ''){
           sql1=sql1+" and c.corp_id like '"+$('#corpId').val()+"%'";
           sql2=sql2+" and c.corp_id like '"+$('#corpId').val()+"%'";
       }
	  //工程名称
	  if(formData.projName !== ''){
		  sql1 = sql1+" and c.proj_name like'%"+formData.projName+"%'";
		  sql2 = sql2+" and c.proj_name like'%"+formData.projName+"%'";
	  }
	  //合同编号
	  if(formData.conNo !== ''){
		  sql1=sql1+" and c.IMC_NO='"+formData.conNo+"'";
		  sql2=sql2+" and c.IT_SC_NO='"+formData.conNo+"'";
	  }
	  //工程地点
	  if(formData.projAddr !== ''){
		  sql1 = sql1+" and c.PROJ_ADDR like '%"+formData.projAddr+"%'";
		  sql2 = sql2+" and c.PROJ_ADDR like '%"+formData.projAddr+"%'";
	  }
	  //客户名称
	  if(formData.custName !== ''){
		  sql1 = sql1+" and c.SECOND_PARTY_NAME like '%"+formData.custName+"%'";
		  sql2 = sql2+" and c.SPARTY_NAME like '%"+formData.custName+"%'";
	  }
	  //签订开始日期
	  if(formData.signDateStart !== ''){
		  sql1 = sql1+" and c.IMC_SIGN_DATE>= "+sqlDateConveter("mysql",formData.signDateStart,1);
		  sql2 = sql2+" and c.SIGN_DATE>= "+sqlDateConveter("mysql",formData.signDateStart,1);
	  }
	 //签订结束日期
	  if(formData.signDateEnd !== ''){
		  sql1 = sql1+" and c.IMC_SIGN_DATE<="+sqlDateConveter("mysql",formData.signDateEnd,1);
		  sql2 = sql2+" and c.SIGN_DATE<="+sqlDateConveter("mysql",formData.signDateEnd,1);
	  }
	 
	  if($('#contractType').val()=='2'){//分包合同
		  return sql2;
	  }else{
		  return sql1;
	  }
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