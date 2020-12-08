<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- projectAccept.jsp -->
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
                	<form class="" id="projectConditionForm" action="/" method="POST">
                		<input class="hidden" id="unitType" name="unitType" value="${unitType}"/>
                		<input class="hidden" id="deptId" name="deptId" value="${deptId}"/>
                        <input class="hidden" id="loginDeptId" name="loginDeptId" value="${loginDeptId}"/>
                		<input class="hidden" id="corpId"  name="corpId" value="${corpId}"/>
						<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter department" id="department" onchange="departmentChange(this.value)" name="department">
									<option value="">公 司</option>
									<c:forEach var="department" items="${departmentList}">
										<option value="${department.deptId}" >${department.deptName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter " id="corpDeptId" onchange="corpDeptChange(this.value)">
									<option value="">部 门</option>
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
				                <input type="text" class="form-control input-sm" placeholder="工程地点" name="projAddr" value=""/>
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
				                <input type="text" class="form-control input-sm" placeholder="申报单位" name="custName" value=""/>
				            </div>
				      	</div>
						<div class="form-group">
				            <div>
				                <select type="text" class="form-control input-sm select-filter projStatusId" name="projStatusId">
				                	 <option value="">工程状态</option>
				                	 <c:forEach var="enums" items="${projStatus}">
										<option value="${enums.value}" >${enums.message}</option>
						             </c:forEach>
				                </select>
				            </div>
				      	</div>至
				      	<div class="form-group">
				            <div>
				                <select type="text" class="form-control input-sm select-filter projStatusIdEnd" name="projStatusIdEnd">
				                	 <option value="">工程状态</option>
				                	 <c:forEach var="enums" items="${projStatus}">
										<option value="${enums.value}" >${enums.message}</option>
						             </c:forEach>
				                </select>
				            </div>
				      	</div>
						<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default projectAcceptData" placeholder="受理开始日期" name="acceptDateStart" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default projectAcceptData" placeholder="受理截止日期" name="acceptDateEnd" value=""/>
				            </div>
				      	</div>
						<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default projectStartData" placeholder="开工开始日期" name="startDateStart" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default " placeholder="开工截止日期" name="startDateEnd" value=""/>
				            </div>
				      	</div>
						<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default" placeholder="竣工开始日期" name="completeDateStart" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
				            <div>
				                <input type="text" class="form-control input-sm datepicker-default" placeholder="竣工截止日期" name="completeDateEnd" value=""/>
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
    App.setPageTitle('报表查询 - 工程项目报表 ');
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

    	console.log("showReport:"+conditions());
    	var condition = encodeURIComponent(cjkEncode(conditions()));
    	var staffName = encodeURIComponent(cjkEncode($("#staffName").val()));
    	console.log("showReport:"+condition);
        var formData =  $("#projectConditionForm").serializeJson();
        var cpt="queryProject.cpt";
        if(formData.loginDeptId =='11011301'){//智能表组
            cpt="smartTableStatistics.cpt";
        }

    	src="<%=basePath%>/ReportServer?reportlet=statisticalquery/"+cpt+"&condition="+condition+"&staffName="+staffName;
    	$("#mainFrm").attr("src",src); 
    };

    //选择分公司时改变默认的分公司ID
    function departmentChange(paramValue) {
        if(paramValue!=''){//选择的分公司
            $('#corpId').val(paramValue);
		}else {//默认的分公司
            $('#corpId').val('${corpId}');
		}

        var obj={deptId:paramValue!=''?paramValue:201909271111};
        Base.subimt("dept/geDepartmentList","POST",JSON.stringify(obj),function (data) {
            $("#corpDeptId").find("option").remove();
            $("#corpDeptId").append('<option value="" >部  门</option>');
            $.each(data, function(i, v) {
                if (i>0) {
                    if (v.deptId==$('#loginDeptId').val()){
                        $("#corpDeptId").append('<option selected=true value=' + v.deptId + '>' + v.deptName + '</option>');
                    }else {
                        $("#corpDeptId").append('<option value=' + v.deptId + '>' + v.deptName + '</option>');
                    }
                }
            });
        });
        $('#deptId').val($("#corpDeptId option:selected").val());
    }


    function corpDeptChange(deptId) {$('#deptId').val(deptId);}
    initDepartment();
    function initDepartment(){
        $("#department option:eq(1)").attr("selected", true);
        $('#corpId').val($("#department").val());
        departmentChange($("#department").val())
    };



    var conditions = function(){
	  var formData =  $("#projectConditionForm").serializeJson();
	  var sql = '';
	//公司ID
	  if(formData.corpId !== ''){
		  sql=sql+" and p.corp_id like '"+formData.corpId+"%'";
	  }else{
		  sql=sql+" and p.corp_id like '"+$("#department option:eq(1)").val()+"%'";
	  }
	//业务部门ID
	  if(formData.loginDeptId!='11011301' && formData.deptId !== '' && formData.unitType=='1'){
		  sql=sql+" and p.dept_id like '"+formData.deptId+"%'";
	  }
	  //工程编号
	  if(formData.projNo !== ''){
		  sql=sql+" and p.proj_no='"+formData.projNo+"'";
	  }
	  //工程名称
	  if(formData.projName !== ''){
		  sql = sql+" and p.proj_name like'%"+formData.projName+"%'";
	  }
	  //工程地点
	  if(formData.projAddr !== ''){
		  sql = sql+" and p.proj_Addr like '%"+formData.projAddr+"%'";
	  }
       //工程类型
       if(formData.projectType !== ''){
           sql = sql+" and p.project_type ='"+formData.projectType+"'";
       }
       //出资方式
       if(formData.contributionMode !== ''){
           sql = sql+" and p.contribution_mode = '"+formData.contributionMode+"'";
       }
	  //申报单位
	  if(formData.custName !== ''){
		  sql = sql+" and p.cust_name like '%"+formData.custName+"%'";
	  }
	  //工程状态
      if(formData.projStatusId !== '' && formData.projStatusIdEnd == ''){
          sql = sql+" and p.PROJ_STATUS_ID = '"+formData.projStatusId+"'";
      }else if(formData.projStatusId == '' && formData.projStatusIdEnd != ''){
    	  sql = sql+" and p.PROJ_STATUS_ID = '"+formData.projStatusIdEnd+"'";
      }else if(formData.projStatusId !== '' && formData.projStatusIdEnd != ''){
    	  sql = sql+" and p.PROJ_STATUS_ID  between '"+formData.projStatusId+"' and '" +formData.projStatusIdEnd+"'";
      }
	  //受理开始日期
	  if(formData.acceptDateStart !== ''){
		  sql = sql+" and p.accept_date>="+sqlDateConveter("mysql",formData.acceptDateStart,1);
	  }
	  //受理结束日期
	  if(formData.acceptDateEnd !== ''){
		  sql = sql+" and p.accept_date<="+sqlDateConveter("mysql",formData.acceptDateEnd,1);
	  }
	  //开工开始日期
	  if(formData.startDateStart !== ''){
		  sql = sql+" and p.start_date>="+sqlDateConveter("mysql",formData.startDateStart,1);
	  }
	  //开工结束日期
	  if(formData.startDateEnd !== ''){
		  sql = sql+" and p.start_date<="+sqlDateConveter("mysql",formData.startDateEnd,1);
	  }
	  //竣工开始日期
	  if(formData.completeDateStart !== ''){
		  sql = sql+" and p.completed_date>="+sqlDateConveter("mysql",formData.startDateStart,1);
	  }
	  //竣工结束日期
	  if(formData.completeDateEnd !== ''){
		  sql = sql+" and p.completed_date<="+sqlDateConveter("mysql",formData.startDateEnd,1);
	  }
	  return sql;
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