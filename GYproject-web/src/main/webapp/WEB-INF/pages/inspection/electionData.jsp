<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- altimetricSurvey.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- ================== END PAGE LEVEL STYLE ================== -->
	
<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse tabs-mixin">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                     <input type="hidden" id="actionName" value="${actionName }"/>
                     <input type="hidden" id="loginPost" value="${loginPost }"/>
					 <input type="hidden" id="builderPost" value="${builderPost }"/>
					 <input type="hidden" id="sujgjPost" value="${sujgjPost }"/>
                     <input type="hidden" id="pcIdNew">
                     <input type="hidden" id="type">
                     <input type="hidden" id="meltConnectType" name="meltConnectType">
                     <input type="hidden" id="preservativeType" name="preservativeType">
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表区</a></li>
		                <li class=""><a href="#default-tab-2" id="signTab"   data-toggle="tab">照片区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" id="">	
               		<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
               			    <input type="hidden" id="projId" name="projId" >
               			    <input type="hidden" id="projNo" name="projNo" >
               			    <input type="hidden" class="imgUrl" value="${imgUrl}"/>
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="projectCheckListTable" width="100%">
	                			<thead>
		                			<tr>
		                				<th></th>
		                				<th>施工工序</th>
			                			<th>报验日期</th>
			                			<th>查验结果</th>
			                			<th>完成状态</th>
		                			</tr>
	                			</thead>
	                		</table>
	                	</div>
						<div class="tab-pane fade btn-top" id="default-tab-2">	                   	
	                    	<div class="clearboth form-box">
						    	<input type="hidden" class="imgUrl" value="${imgUrl}"/>
						    	<form class="form-horizontal" id="signForm" data-parsley-validate="true" action="electrodeRecord/saveSign">							
								    <input type="hidden" id="pcDesId" name="pcDesId" value="${checkType}"/> 
									<input type="hidden" id="pcId" name="pcId" class="addClear"/>
									<input type="hidden" id="version" name="version" class="addClear"/>
									<input type="hidden" id="projId" name="projId" >
								</form>
								<!-- 照片 -->
								<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list " id="projectImagesList">
									     <h4><i class="fa fa-file-photo-o"></i> 照片列表</h4>
									     <ul class="row">
									     </ul>
									</div>
								</div> 
							</div>
	                   	</div>
                    </div>
               	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" id="">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <h4 class="panel-title">预览区</h4>
			    </div>
			    <div class="panel-body" id="altimetric_survey_audit_panel_box">
	                 <div class="clearboth form-box">
	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 850px; height: 1123px;border:1; overflow-y:auto;" scrolling="no"></iframe>
	                  	</div>
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
    App.setPageTitle('报验资料- 工程项目管理系统 ');
     if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projAddr').val(projJson.projAddr);				   //
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.cuName); //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    } 
     console.log(projJson);
   //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $.getScript('projectjs/inspection/election-data.js?'+Math.random()).done(function() {

    	electionData.init();
    });
  	//加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var cptPath = '<%=basePath%>';
  	//加载列表区和签字区的cpt文件
    var showReport = function showReport(url){
  		
  		var type=$("#type").val();
  		console.info("type---"+type);
  		
  		if(type=="7"){
  			if($("#preservativeType").val()=="1"){
  				url="preservative/preservativeJoint";
  			}else{
  				url="preservative/preservative1";
  			}
  		}else if(type=="8"){
  			if($("#preservativeType").val()=="0"){
  				url="inspection/preservativeInpectJoint";
  			}else{
  				url="inspection/preservativeInpectPaint";
  			}
  		}
  		
  		var meltConnectType=$("#meltConnectType").val();
  		if(meltConnectType!==""){
  			if(meltConnectType=="1"){
  				url="inspection/peWeldLineInpectElectricMelt";
  			}else{
  				url="inspection/peWeldLineInpectHotMelt";
  			}
  		}
  		
    	var pcId = $("#pcIdNew").val();
        var json=trSData.projectCheckListTable.json;
        var reviewerDate,projName,projNo,suName,constructionUnit;
        //列表区的cpt文件数据从trSData中获取
        if(json){
        	projName=json.projName;
       	    suName =json.suName;
       	    projNo = json.projNo;
            constructionUnit =json.constructionUnit;
        }else{
        	//签字区保存后不刷新页面,cpt文件中数据从签字区获取
        	projName=$('#projName').val();
        	projNo = $('#projNo').val();
    	    suName = $("#suName").val();
    	   // process = $("#process").val();
            constructionUnit = $("#constructionUnit").val();
           
        }
        if(!pcId){
        	pcId='-1';
        	reviewerDate="";
        }
    	    //以下处理为解决中文乱码
    	    projName=encodeURIComponent(cjkEncode(projName));
        	suName = encodeURIComponent(cjkEncode(suName));
        	//inspectionResult=encodeURIComponent(cjkEncode(inspectionResult));
        	//constructionUnit = encodeURIComponent(cjkEncode(constructionUnit));
        	//reviewerDate = encodeURIComponent(cjkEncode(reviewerDate));
        //cpt路径及参数
    	var src=cptPath+"/ReportServer?reportlet="+url;
        src = src + "&projName=" + projName + "&projNo=" + projNo;
        src = src+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
        
        console.info("src---"+src);
        
    	$("#mainFrm").attr("src",src); 
    };
</script>

<!-- ================== END PAGE LEVEL JS ================== -->