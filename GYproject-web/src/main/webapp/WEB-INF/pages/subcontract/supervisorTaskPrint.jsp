<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
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
						<li class="active"><a href="#default-tab-1" id="listTab" data-toggle="tab">监理任务列表</a></li>
						<li class=""><a href="#default-tab-2"  id="modifyTab"  data-toggle="tab">详细信息</a></li>
					</ul>
                </div>
                <div class="panel-body">
					 <input type="hidden" id="alreadyPrint" value="${alreadyPrint}"/>
					<input type="hidden" id="haveNotPrint" value="${haveNotPrint}"/>
					<input type="hidden" id="loginName" value="${loginName}"/>
					<input type="hidden" id="loginPhone" value="${loginPhone}"/>
					<input type="hidden" id="loginPost" value="${loginPost}"/>
					<input type="hidden" id="dataAdmin"  value="${dataAdmin}"/>
	                <div class="tab-content">
						<div class="tab-pane fade active in btn-top" id="default-tab-1" >
							<div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
							<table id="supervisorTaskPrintTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
								<thead>
									<tr>
										<th>工程ID</th>
										<th>cpId</th>
										<th>工程编号</th>
										<th>工程名称</th>
										<th>下达日期</th>
										<th>工程状态</th>
										<th></th>
									</tr>
								</thead>
							</table>
						</div>


						<div class="tab-pane fade btn-top" id="default-tab-2">
							<div class="toolBtn f-r p-b-10">
								<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 hidden savebtn" onclick="saveClick()" >保存</a>
							</div>
							<form class="form-horizontal" id="detaileForm" action="" data-parsley-validate="true">
								<input type="hidden" name="projId" id="projId"/>
								<div class="form-group col-md-12 col-sm-12">
									<label class="control-label" for="projName">工程名称</label>
									<div>
										<input class="form-control input-sm field-not-editable" name="projName" id="projName" value="">
									</div>
								</div>
								<div class="form-group  col-md-6 col-sm-6">
									<label class="control-label" for="projNo">工程编号</label>
									<div>
										<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" />
									</div>
								</div>

								<div class="form-group col-md-6 col-sm-12">
									<label class="control-label">工程类型</label>
									<div>
										<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
									</div>
								</div>

								<div class="form-group col-md-12 ">
									<label class="control-label" >监理单位</label>
									<div>
                                        <input type="hidden"  id="suId" name="suId"  class="form-control input-sm field-not-editable" data-parsley-required="true"/>
										<input type="text"  id="suName" name="suName"  class="form-control input-sm field-not-editable" data-parsley-required="true"/>
										<a id="suPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择监理单位"><i class="fa fa-search"></i></a>
									</div>
								</div>
								<div class="form-group col-md-6 ">
									<label class="control-label" >负责人</label>
									<div>
										<input type="text" class="form-control input-sm field-not-editable"  id="suDirector" name="suDirector"/>
									</div>
								</div>
								<div class="form-group col-md-6 ">
									<label class="control-label" >电话</label>
									<div>
										<input type="text" class="form-control input-sm field-editable"  id="suPhone" name="suPhone" data-parsley-maxlength="13"/>
									</div>
								</div>
                                <div class="form-group col-md-6 col-sm-12">
                                    <label class="control-label">下达日期</label>
                                    <div>
                                        <input type="text" name="cpArriveDate" class="form-control field-editable  input-sm datepicker-default" data-parsley-required="true" />
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label" for="projTimeLimit">工期</label>
                                    <div>
                                        <input type="text" class=" form-control input-sm field-editable " id="projTimeLimit"  name="projTimeLimit" data-parsley-required="true">
                                        <a href="javascript:;" class="btn btn-sm btn-default">天</a>
                                    </div>
                                </div>
							</form>
						</div>

					</div>
            	</div>
        	</div>
        </div>
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <h4 class="panel-title">预览区</h4>
			    </div>
			    <div class="panel-body">
			    	<div class="fine_report">
				    	<div class="iframe-report-box">
		                 	<iframe id="mainFrm" class="iframe-report" style="width: 790px; height: 1120px;border:0; overflow-y:auto;" scrolling="no"></iframe>
		                </div>
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
	App.setPageTitle('监理任务书打印 - 工程管理系统');
	$("#detaileForm").toggleEditState(false);
	$("#detaileForm").styleFit();
	$('.datepicker-default').datepicker({
		todayHighlight: true
	});


	//选中监理单位
	$("#suPop").off().on("click",function(){
		businessPartnersPopup({
			'suId':'unitId',
			'suName':'unitName',
			'suDirector':'unitDirector',
			'suPhone':'unitMobile'
		},4)
	});



	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%> 
	
	var cptPath = '<%=basePath%>';

    //日期datepicker
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
	src = cptPath+"/ReportServer?reportlet=subContract/supervisorTaskPrint.cpt";
	$("#mainFrm").attr("src",src);

    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
	
	$.getScript('projectjs/subcontract/supervisor-task-print.js?v='+Math.random()).done(function () {
        supervisorTaskPrint.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->