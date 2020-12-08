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
                    <input class="hidden" id="unitType" value="${unitType}"/>
                    <input class="hidden" id="deptId" value="${deptId}"/>
					<input class="hidden" id="corpId" value="${corpId}"/>

					<form class="" id="manageRecordForm" action="/" method="POST">
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
				                <input type="text" class="form-control input-sm" placeholder="审核人" name="mrCsr" value=""/>
				            </div>
				      	</div>
				      	<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="mrAuditLevel">
									<option value="">审核级别</option>
									<option value="1">一级</option>
									<option value="2">二级</option>
									<option value="3">三级</option>
									<option value="4">四级</option>
									<option value="5">五级</option>
								</select>
							</div>
						</div>
				      	<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="mrResult">
									<option value="">审核结果</option>
									<option value="1" >通过</option>
									<option value="0" >未通过</option>
								</select>
							</div>
						</div>
				      	<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="projStatusId">
									<option value="">工程状态</option>
									<c:forEach var="enums" items="${projStatus}">
										<option value="${enums.value}" >${enums.message}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div>
								<select type="text" class="form-control input-sm select-filter" name="docTypeId">
									<option value="">单据类型</option>
									<c:forEach var="enums" items="${docList}">
										<option value="${enums.id}" >${enums.des}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
					</form>
					<div class="toolBtn pull-right m-t-5">
						<button type="reset" form="manageRecordForm" class="btn btn-sm btn-send reset">重置</button>
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
    App.setPageTitle('报表查询 - 审核记录报表 ');
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
    	console.info(condition);
    	var staffName = encodeURIComponent(cjkEncode($("#staffName").val()));
    	src="<%=basePath%>/ReportServer?reportlet=statisticalquery/queryAuditRecord.cpt&condition="+condition+"&staffName="+staffName;
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
	  var formData =  $("#manageRecordForm").serializeJson();
	  var sql = '';
	  sql=sql+" and pro.dept_id like '"+$("#deptId").val()+"%'";
	  //工程编号
	  if(formData.projNo !== ''){
		  sql=sql+" and pro.proj_no='"+formData.projNo+"'";
	  }
       //公司ID
       if($('#corpId').val() != ''){
           sql=sql+" and pro.corp_id like '"+$('#corpId').val()+"%'";
       }
	  //工程名称
	  if(formData.projName !== ''){
		  sql = sql+" and pro.proj_name like'%"+formData.projName+"%'";
	  }
	  //工程地点
	  if(formData.projAddr !== ''){
		  sql = sql+" and pro.proj_addr like'%"+formData.projAddr+"%'";
	  }
	  //工程类型
      if(formData.projectType !== ''){
          sql = sql+" and pro.project_type ='"+formData.projectType+"'";
      }
      //出资方式
      if(formData.contributionMode !== ''){
          sql = sql+" and pro.contribution_mode = '"+formData.contributionMode+"'";
      }
	  //审核人
	  if(formData.mrCsr !== ''){
		  sql = sql+" and staff.staff_name like'%"+formData.mrCsr+"%'";
	  }
	  //审核结果
	  if(formData.mrResult !== ''){
		  sql = sql+" and mr.mr_result ='"+formData.mrResult+"'";
	  }
	  //工程状态
	  if(formData.projStatusId !== ''){
		  sql = sql+" and pro.proj_status_id ='"+formData.projStatusId+"'";
	  }
	  //单据类型
	  if(formData.docTypeId !== ''){
		  sql = sql+" and mr.doc_Type_Id ='"+formData.docTypeId+"'";
	  }
	  //审核级别
	  if(formData.mrAuditLevel !== ''){
		  sql = sql+" and mr.mr_Audit_Level ='"+formData.mrAuditLevel+"'";
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