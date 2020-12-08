<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<!-- projectAccept.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

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
                    <ul class="nav nav-tabs">
		                <!-- <li class="active"><a href="#change-tab-1" id="projectTab"  data-toggle="tab">工程列表</a></li> -->
		                <li class="active"><a href="#change-tab-2" id="changeListTab" data-toggle="tab">变更列表</a></li>
		                <li class=""><a href="#change-tab-3" id="changeRecordTab" data-toggle="tab">变更记录</a></li>                
	               		<li class=""><a href="#default-tab-4" id="materialChangeTab"   data-toggle="tab">材料变更</a></li>    
	                </ul>
                </div>
                <div class="panel-body">
                	<div class="tab-content">
                		<!-- <div class="tab-pane fade in btn-top active" id="change-tab-1">
                			<input type="hidden" id="projId" name="projId" >
                			<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
		                    <table id="projectTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
		                    	<thead>
		                            <tr>
		                            	<th>工程ID</th>
		                                <th>工程编号</th>
		                                <th>工程名称</th>
		                                <th>状态</th>
		                                <th></th>
		                            </tr>
		                        </thead>
		                    </table>
                		</div> -->
                		<div class="tab-pane fade in btn-top active" id="change-tab-2">
                			<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
		                    <table id="projectChangeTable" class="table table-striped table-bordered nowrap" width="100%">
		                    	<thead>
		                            <tr>
		                            	<th></th>
		                            	<th>工程编号</th>
		                            	<th>工程名称</th>
		                            	<th>申请日期</th>
		                                <th>处理状态</th>
		                            </tr>
		                        </thead>
		                    </table>
                		</div>
                		<div class="tab-pane fade in btn-top saveHiddenBox" id="change-tab-3">
                			<div class="toolBtn f-r p-b-10 update-show">
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 giveUpBtn">放弃</a>
						    </div>
						    <input type="hidden" id="sysDate" value="${sysDate}"/>
						    <input type="hidden" id="flag" name="flag">
						    <input type="hidden" id="projId1" name="projId">
    						<input type="hidden" id="loginName" name="loginName" value="${loginInfo.staffName}"/> 
						    <form class="form-horizontal" id="peojectChangeForm" action="">
						    	<input type="hidden" id="cmId" name="cmId">
						    	<input type="hidden" type="hidden" id="projId2" name="projId" >
						    	<input type="hidden" id="version" name="version" >
					<!--     		<input type="hidden" id="designChangeType" name="designChangeType"/> -->
								<div class="form-group col-md-6 col-sm-12">
									<label class="control-label" for="">工程编号</label>
									<div>
										<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo"  />
									</div>
								</div>
								<div class="form-group col-sm-12">
									<label class="control-label" for="">工程名称</label>
							    	<div>
										<input type="text" class="form-control input-sm field-not-editable" id="projName" name="projName"  />
								   </div>
								</div>
								<div class="form-group col-sm-12">
				                	<label class="control-label" for="">工程地点</label>
				                    <div> 
				                    	<input type="text" class="form-control input-sm field-not-editable" id="projAddr" name="projAddr"  >
				  				    </div> 
				  				</div>
				  				<div class="form-group col-sm-12">
				                	<label class="control-label" for="projScaleDes">工程规模</label>
				                    <div> 
				                    	<input type="text" class="form-control input-sm field-not-editable" id="projScaleDes" name="projScaleDes"  >
				  				    </div> 
				  				</div>
				  				<div class="form-group col-md-6 col-sm-12">
							   	<label class="control-label" for="corpName">燃气公司</label>
							       <div>
							       	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  />
							       </div>
							   </div>
							   <div class="form-group col-md-6 col-sm-12">
							       <label class="control-label" for="">工程类型</label>
							   	<div>
							   		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
							       </div>
							   </div>
							   <div class="form-group col-md-6 col-sm-12">
							       <label class="control-label" for="">出资方式</label>
							   	<div>
							   		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
							       </div>
							   </div>
							   <div class="form-group col-md-6 col-sm-12">
							       <label class="control-label" for="">业务部门</label>
							   	<div>
							   		 <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value=""/>        
							       </div>
							   </div>
							   <div class="form-group col-md-6 col-sm-12 ">
									<label class="control-label" for="">申请人</label>
									<div>
										<input type="text" class="form-control input-sm  field-not-editable" id="cmAdvanceStaffName" name="cmAdvanceStaffName"  />
									</div>
								</div>
							   <div class="form-group col-md-6 col-sm-12 ">
									<label class="control-label" for="">申请日期</label>
									<div>
										<input type="text" class="form-control input-sm datepicker-default field-not-editable" id="applyDate" name="applyDate"  />
									</div>
								</div>
				  				<div class="form-group col-sm-12">
				     				<label class="control-label" for="">申请原因</label>
				    				<div> 
				         				<textarea class="form-control field-not-editable" name="applyReason" id="applyReason" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
			         				</div>
				    		    </div>
				    		    <div class="form-group col-md-6 col-sm-12">
									<label class="control-label" for="">变更编号</label>
									<div>
										<input type="text" class="form-control input-sm field-not-editable" id="cmNo" name="cmNo" />
									</div>
								</div>
								<div class="form-group col-md-6 col-sm-12 CuChange">
				     				<label class="control-label" for="">专业</label>
				    				<div> 
				         				<input type="text" class="form-control input-sm field-editable" id="major" name="major"/>
			         				</div>
					    		</div>
				    		    <div class="form-group col-sm-12">
				     				<label class="control-label" for="changeContent">变更内容</label>
				    				<div> 
				         				<textarea class="form-control field-editable" name="changeContent" id="changeContent" rows="5" cols="" data-parsley-maxlength="500" ></textarea>
			         				</div>
				    			</div>
				    			<div class="form-group col-md-6 col-sm-12 ">
									<label class="control-label" for="">确认人</label>
									<div>
										<input type="text" class="form-control input-sm  field-not-editable" id="designer" name="designer"  />
									</div>
								</div>
				    			<div class="form-group col-md-6 col-sm-12 ">
									<label class="control-label" for="">确认日期</label>
									<div>
										<input type="text" class="form-control input-sm datepicker-default field-not-editable" id="cmDate" name="cmDate"  />
									</div>
								</div>
						    </form>
                		</div>
                	<div class="tab-pane fade in btn-top" id="default-tab-4">
             		   <div id="materialChangebox">
				        	<form id="exportExcel" action="changeRecord/exportExcel">
				        	</form>
					        <form action="" id="materialListForm">
						         <table id="materialListTable" class="table table-striped table-bordered nowrap " width="100%">
				            		<thead>
				                		<tr>
					                		<th></th>
					                		<th></th>			                		 
					                		<th>材料名称</th>
							                <th>材料规格</th>
							                <th>单位</th>
							                <th>是否从物资购买</th>
							                <th width="120">调整量</th>
				                		</tr>
					               	</thead>
				    	  	  	 </table>
			    	  	  	 </form>
						</div>
					</div>
                	
                	</div>
             
            	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" id="content">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			        	<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <h4 class="panel-title">预览区</h4>
			    </div>
			    
			    <div class="panel-body">
			    	<div class="iframe-report-box">
	                 	<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:0; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('设计变更 - 工程项目管理系统 ');

    $.getScript('projectjs/design/design-change.js?v='+Math.random()).done(function () {
        projectChange.init();
    });
    $("#peojectChangeForm").styleFit();
    //加载帆软路径
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%> 
	var cptPath = '<%=basePath%>';
	//加载设计变更
	var showDesignChange = function(){
    	cmId =$("#cmId").val(),	
    	projId = encodeURIComponent(cjkEncode($("#projId2").val())),
	    projNo = encodeURIComponent(cjkEncode($("#projNo").val())),		        //工程编号
	    cmNo=encodeURIComponent(cjkEncode($("#cmNo").val())),				    //变更编号
		projName=encodeURIComponent(cjkEncode($("#projName").val())),	        //工程名称
		projAddr=encodeURIComponent(cjkEncode($("#projAddr").val())),           //工程地点
		major=encodeURIComponent(cjkEncode($("#major").val())),					//专业
		cmDate=encodeURIComponent(cjkEncode($("#cmDate").val())),				//变更日期
		custName=encodeURIComponent(cjkEncode($("#custName").val())),			//申报单位
		cuReason=encodeURIComponent(cjkEncode($("#cuReason").val())),			//变更原因
		changeContent=encodeURIComponent(cjkEncode($("#changeContent").val()));	//变更内容
		var src = cptPath+"/ReportServer?reportlet=design/designChange.cpt&projNo="+projNo+"&cmNo="+cmNo+"&projName="+projName+"&projAddr="+projAddr+"&projId="+projId+"&cmId="+cmId+"&major="+major+"&cmDate="+cmDate+"&custName="+custName+"&cuReason="+cuReason+"&changeContent="+changeContent;
	 	$("#mainFrm").attr("src",src);
	}
	 
	//打印乱码解决
	    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
	    
	 //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
	//日期
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });
	 
	//点击保存
	$('.saveBtn').off("click").on("click",function(){
		//加遮罩
		$(".saveHiddenBox").loadMask("正在保存...", 1, 0.5);
		//表单序列化
		var dataStr=$("#peojectChangeForm").serializeJsonString();
	 	$.ajax({
            type: 'POST',
            url: 'designChange/saveChangeAlteration',
            contentType: "application/json;charset=UTF-8",
            data: dataStr,
            success: function (data) {
            	//取消遮罩
            	$(".saveHiddenBox").hideMask();	

            	var content = "数据保存成功！";
            	if(data === "false"){
            	   content = "数据保存失败！";
            	}else if(data === "true"){
            		$(".toolBtn").addClass("hidden");
            		//$("#designAlterationTable").cgetData();  //列表重新加载
            		
            	}
            	var myoptions = {
                    	title: "提示信息",
                    	content: content,
                    	accept: savedone,
                    	chide: true,
                    	icon: "fa-check-circle"
                }
           
            
                $("body").cgetPopup(myoptions);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("变更信息保存失败！");
            }
        });
	 	$('#projectChangeTable tr.selected').removeClass("selected");
		$("#peojectChangeForm").toggleEditState(false);
	});
	
	var savedone = function(){
    	  $("#peojectChangeForm").styleFit();
    	  showDesignChange();
    	}
	
	//点击放弃
	$('.giveUpBtn').off().on('click',function(){
		$(".toolBtn").addClass("hidden");
    	$('ul.nav>li').removeClass("active");
		$('#changeListTab').click();
		showDesignChange();
    })

</script>