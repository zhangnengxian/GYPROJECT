<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
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
                   	<h4 class="panel-title">碰口审批</h4>
                </div>
                <div class="panel-body">
                    <div class="toolBtn f-r p-b-10 ">
                    	<a href="javascript:;" class="btn btn-confirm btn-sm addBtn" >保存</a>
  	                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn" >取消</a>
                    </div>
                    <div class="clearboth form-box">
                    	<form class="form-horizontal" id="touchAuditForm" action="">
							<div class="form-group col-xs-12">
								<label class="control-label" for="">工程名称</label>
								<div>
								<input type="text" class="form-control input-sm field-editable" value="新疆立通通用设备制造有限公司天然气工程" id="" name=""  />
								</div>
						 	</div>
						    <div class="form-group col-xs-12">
								<label class="control-label" for="">工程地点</label>
								<div>
								<input type="text" class="form-control input-sm field-editable" value="西湖路" id="" name=""  />
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">施工负责人</label>
								<div>
									<input type="text" class="form-control input-sm field-editable" value="李建军" id="" name=""  />
								</div>
							</div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">联系电话</label>
								<div>
									<input type="text" class="form-control input-sm field-editable" value="15831232266" id="" name=""  />
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">现场负责人</label>
								<div>
									<input type="text" class="form-control input-sm field-editable" value="王杰" id="" name=""  />
								</div>
						  	</div>
						  	 <div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">联系电话</label>
								<div>
									<input type="text" class="form-control input-sm field-editable" value="15831232265" id="" name=""  />
								</div>
						    </div>
						 	<div class="form-group col-md-6 col-sm-12">
								<label class="control-label" for="">碰口时间</label>
								<div>
									<input type="text" class="form-control input-sm field-editable datepicker-default" value="2016-04-06" id="" name=""  />
								</div>
							</div>
						 	<div class="form-group col-md-6 col-sm-12">
								<label class="control-label signature-tool" for="">现场监理</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
									<input type="hidden" id="sign_data_6" name="sign_data_6" value="">
									<img class="" alt="" src="images/sign-1.png" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
							</div>
						    <div class="form-group col-xs-12">
					            <label class="control-label" for="">监理时间</label>
						        <div class="input-group">
							        <input type="text" class="form-control datepicker-default input-sm" value="2016-04-02" name="">
							        <span class="input-group-addon">至</span> 
							        <input type="text" class="form-control datepicker-default input-sm" value="2016-04-07" name="">
						        </div>
						    </div>
						 	<div class="form-group col-sm-12">
								<label class="control-label" for="">存在问题</label>
								<div>
									<textarea class="form-control" name="" id="" rows="3" cols="" data-parsley-maxlength="200" >无</textarea>
								</div>
							</div>
						</form>
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
    App.setPageTitle('碰口审批 - 工程项目管理系统');
    
    $("#touchAuditForm").toggleEditState().styleFit();

    //日期datepicker
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });

    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6");
    	signatures.handleSignature();        	    	
    });
    
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>    
    //加载打印预览
    var showReport = function(){
    	var name = $("#name").val(),
    	des = $("#des").val(),
    	src="<%=basePath%>/ReportServer?reportlet=constructmanage/touchAudit.cpt";
    	$("#mainFrm").attr("src",src); 
    }();
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }

</script>
<!-- ================== END PAGE LEVEL JS ================== -->