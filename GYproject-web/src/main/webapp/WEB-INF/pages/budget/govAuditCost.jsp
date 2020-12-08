<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">待审定登记工程列表</h4>
                </div>
                <div class="panel-body">
                    <input type="hidden" id="civilVal" value="${civilVal}">
                    <input type="hidden" id="publicVal" value="${publicVal}">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="govAuditCostTable"  class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th>工程Id</th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>状态</th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
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
				         <h4 class="panel-title">审定价信息</h4>
				    </div>
				    <div class="panel-body" id="budget_register_panel_box">
				    		<div class="toolBtn f-r p-b-10  editbtn hidden">
					    	<a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5  saveBtnGac" >保存</a>
					    	<a href="javascript:;" class="btn btn-info  btn-sm m-l-5  pushBtnGac" >推送</a>
					        <a href="javascript:;" class="btn  btn-warn btn-sm m-l-5  giveUpBtn">放弃</a> 
						</div>
						<div class="form-box clearboth">
						<input type="hidden" id ="gacType" name="gacType" value="${gacType }" />
							 <%--//值为2时文件格式支持多种（不影响其他页面功能写死）--%>
							<input type="hidden" id="changeType" value="2"/>
							<form class="form-horizontal" id="govAuditCostForm" action="govAuditCost/updateGovAUditCostFile" data-parsley-validate="true"  enctype="multipart/form-data" >
								<input type="hidden" name="projId" id="projId" />
								<input type="hidden" name="gacId" id="gacId" />
								<input type="hidden" id="result" name="result">
								<input type="hidden" id="menuDes" name="menuDes">
								<input type="hidden" name="stepId" id="stepId" />
								<input type="hidden" name="alPath" id="alPath" />
								<input type="hidden" id="drawUrl" name="drawUrl" value="${drawUrl}">
								<input type="hidden" id ="gacType1" name="gacType" value="${gacType }" />
								<div class="form-group col-md-6 col-sm-12 ">
							    	<label class="control-label" for="projNo">工程编号</label>
							        <div>
							        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
							        </div>
							    </div>
							    <div class="form-group col-md-12  ">
							        <label class="control-label" for="projName">工程名称</label>
							        <div>
							            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
							        </div>
							    </div>
							    <div class="form-group col-md-12  ">
							        <label class="control-label" for="projAddr">工程地点</label>
							        <div>
							            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
							        </div>
							    </div>
							    <div class="form-group col-md-12">
						            <label class="control-label" for="projScaleDes">工程规模</label>
						            <div>
						                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200" value=''></textarea>
						            </div>
					            </div>
					            <div class="form-group col-md-6 col-sm-12">
							    	<label class="control-label" for="">燃气公司</label>
							        <div>
							        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="200"/>
							        </div>
							    </div>
							    <div class="form-group col-md-6 col-sm-12">
							        <label class="control-label" for="">工程类型</label>
							    	<div>
							    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes"  data-parsley-maxlength="100"/>
							    		<!-- <select class="form-control input-sm field-not-editable" id="projectType"  name="projectType" data-parsley-required="true" >
											<option value="1" >居民户工程</option>
							             </select> -->
							        </div>
							    </div>
							    <div class="form-group col-md-6 col-sm-12">
							        <label class="control-label" for="">出资方式</label>
							    	<div>
							    	<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes"  data-parsley-maxlength="100"/>
							    		<!-- 
							    		<select class="form-control input-sm field-not-editable" id="investmentMethod"  name="investmentMethod" data-parsley-required="true" >
											<option value="1" >用户出资（自有资金）</option>
							             </select> -->
							        </div>
							    </div>
							    <div class="form-group col-md-6 col-sm-12">
							        <label class="control-label" for="">业务部门</label>
							    	<div>
							    	<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"  data-parsley-maxlength="200"/>
							    		
							    		<!-- <select class="form-control input-sm field-not-editable" id="deptName"  name="deptName" data-parsley-required="true" >
											<option value="1" >民用组</option>
							             </select> -->
							        </div>
							    </div>
							    <div class="form-group col-md-12 ">
							        <label class="control-label" for="custName">用户单位</label>
							        <div>
							            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""  data-parsley-maxlength="200"/>
							        </div>
							    </div>
							    <div class="form-group col-md-6">
							        <label class="control-label" for="custContact">联系人</label>
							        <div>
							            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="200" value=""/>
							        </div>
							    </div>
							    <div class="form-group col-md-6">
							        <label class="control-label" for="custPhone">联系电话</label>
							        <div>
							            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="13" value=""/>
							       
							        </div>
							    </div>
							    <!-- <div class="form-group col-md-6">
							        <label class="control-label" for="surveyer">勘察人</label>
							        <div>
							           <input type="text" class=" form-control input-sm field-not-editable" id="surveyer"  name="surveyer" data-parsley-maxlength="200" value="">
							        </div>
							    </div>
							    <div class="form-group col-md-6">
							        <label class="control-label" for="designer">设计人</label>
							        <div>
							           <input type="text" class=" form-control input-sm field-not-editable" id="designer"  name="designer" data-parsley-maxlength="200" value="">
							        </div>
							    </div>
								
									<div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
									<label class="control-label" for="">预算价</label>
						            <div>
						            	<input type="text" class="form-control input-sm  fixed-number field-editable text-right" data-parsley-required="true" data-parsley-min="0.01" id="budgetCost" name="budgetCost" value=""/>
						            </div>
								   </div>
								    <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
									<label class="control-label" for="">审定价</label>
						            <div>
						            	<input type="text" class="form-control input-sm  fixed-number field-editable text-right"  data-parsley-min="0.01" id="authorizedCost" name="authorizedCost" value=""/>
						            </div>
								   </div>
								<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						              <label class="control-label" for="">监理费</label>
						              <div>
						           		<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00" id="suCost" name="suCost" value="" />
						             	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						             </div>
						          </div>
						          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						              <label class="control-label" for="">监检费</label>
						              <div>
						           		<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00" id="inspectionCost" name="inspectionCost" value="" />
						             	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						             </div>
						          </div>
						          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						              <label class="control-label" for="unforeseenCost">不可预见费</label>
						              <div>
						           		<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="unforeseenCost" name="unforeseenCost" value=""/>
						             	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						             </div>
						          </div> 
						          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						              <label class="control-label" for="materialCost">主材费</label>
						              <div>
						           		<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="materialCost" name="materialCost" value=""/>
						             	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						             </div>
						          </div> 
						          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						              <label class="control-label" for="designCost">设计费</label>
						              <div>
						           		<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="designCost" name="designCost" value=""/>
						            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						             </div>
						          </div>   -->
						          <div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
									<label class="control-label" for="">预算价</label>
						            <div>
						            	<input type="text" class="form-control input-sm  fixed-number field-not-editable text-right" data-parsley-required="true" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.01" id="budgetTotalCost" name="budgetTotalCost" value=""/>
						            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						            </div>
								</div>
						          <div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
						              <label class="control-label" for="authorizedCost">审定价</label>
						              <div>
						           		<input type="text" class="form-control input-sm fixed-number field-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="authorizedCost" name="authorizedCost" value="" data-parsley-required="true"/>
						            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						             </div>
						          </div> 
						          
						        <div class="form-group col-md-12">
						            <label class="control-label" for="gacRemark">备注</label>
						            <div>
						                <textarea class="form-control  field-editable" name ="gacRemark" id="gacRemark" rows="5" data-parsley-maxlength="200" value=''></textarea>
						            </div>
					            </div>	
					            <input type="hidden" name="gacStaffId" id="gacStaffId" />
						           <div class="form-group col-md-6 clearboth">
							        <label class="control-label" for="gacStaffName">登记人</label>
							        <div>
							           <input type="text" class=" form-control input-sm field-not-editable" id="gacStaffName"  name="gacStaffName" data-parsley-maxlength="100" value="">
							        </div>
							    </div>
							    <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						              <label class="control-label" for="authorizedCostDate">登记日期</label>
						              <div>
						           		<input type="text" class="form-control input-sm field-not-editable timestamp"  id="authorizedCostDate" name="authorizedCostDate" value=""/>
						             </div>
						          </div>
					            <!-- <div class="form-group col-md-12">
							            <label class="control-label" for="">附件</label>
						           		<div>
											<div class="hidden hasVal"> 
							         			<input type="text" class="form-control input-sm field-not-editable" id="drawName" name="drawName"/>
							         			<a class="btn btn-sm btn-primary searchButton " href="" target="_blank">查看</a>
						         		        <a class="btn btn-sm btn-primary Search_Button" href="javascript:">查看</a>
						         		        <a class="del_btn btn btn-sm btn-danger"><i class="fa fa-times"></i> 删除</a>
						         		    </div>
												<div class="fileupload-buttonbar noVal hidden">
											        <div class="pull-right toolBtn">
											            <span class="btn btn-success btn-sm fileinput-button">
											                <i class="fa fa-plus"></i>
											                <span>浏览文件...</span>
											                <input type="file" name="files[]" multiple/>	             	          
											            </span>
											            <button type="submit" class="btn btn-primary btn-sm start hidden">
										                    <i class="fa fa-upload"></i>
										                    <span>上传</span>
										                </button>
											        </div>
											    </div>
										    	The table listing the files available for upload/download
										    	<table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
											</div>
						            </div> -->
							</form>
						</div>
				    </div>
				</div>
            
        </div>
        <!-- end col-6 -->
		

    </div>
</div>

<!-- end #content -->
<script id="template-upload" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-upload fade">
            <td class="col-md-1 hidden">
                <span class="preview"></span>
            </td>
            <td width="60%">
                <p class="name filename">{%=file.name%}</p>
                <strong class="error text-danger"></strong>
            </td>
            <td width="20%">
                <p class="size">Processing...</p>
            </td>
            <td width="20%">
                <div class="progress progress-striped active"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
            </td>
            <td class="hidden">
                {% if (!i && !o.options.autoUpload) { %}
                    <button class="btn btn-primary btn-sm start" disabled>
                        <i class="fa fa-upload"></i>
                        <span>Start</span>
                    </button>
                {% } %}
                {% if (!i) { %}
                    <button class="btn btn-white btn-sm cancel">
                        <i class="fa fa-ban"></i>
                        <span>Cancel</span>
                    </button>
                {% } %}
            </td>
        </tr>
    {% } %}
</script>
<script id="template-download" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-download fade">
            <td class="hidden">
                <span class="preview">
                    {% if (file.thumbnailUrl) { %}
                        <!--<a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>-->
                    {% } %}
                </span>
            </td>
            <td width="60%">
                <p class="name">
                    {% if (file.url) { %}
                        <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                    {% } else { %}
                        <span>{%=file.name%}</span>
                    {% } %}
                </p>
                {% if (file.error) { %}
                    <div><span class="label label-danger">Error</span> {%=file.error%}</div>
                {% } %}
            </td>
            <td width="20%">
                <span class="size">{%=o.formatFileSize(file.size)%}</span>
            </td>
            <td width="20%">
                <div class="progress progress-striped text-center"><div class="progress-bar progress-bar-success" style="width:100%;">已上传</div></div>
            </td>
            <td class="hidden">
                {% if (file.deleteUrl) { %}
                    <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                        <i class="glyphicon glyphicon-trash"></i>
                        <span>Delete</span>
                    </button>
                    <input type="checkbox" name="delete" value="1" class="toggle">
                {% } else { %}
                    <button class="btn btn-warning cancel">
                        <i class="glyphicon glyphicon-ban-circle"></i>
                        <span>Cancel</span>
                    </button>
                {% } %}
            </td>
        </tr>
    {% } %}
</script>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('预算审定登记 - 工程管理系统');
    //初始化上传文件控件
    $.getScript('projectjs/data/form-multiple-upload.demo3.js').done(function() {
      FormMultipleUpload.init($('#govAuditCostForm'));
  	});
    $("#govAuditCostForm").toggleEditState(false).styleFit();
    
   /*  console.info(trSData);
    if(trSData.govAuditCostTable.t){
 	   trSData.govAuditCostTable.t.cgetDetail('govAuditCostForm','govAuditCost/queryProjectBgac?id='+$('#projId').val()+'&gacType=0','',queryBackView);
 	}else{
 		$(".editbtn").addClass("hidden");
 	} */
    
    $(".editbtn").addClass("hidden");
    
    $.getScript('projectjs/budget/govAuditCost.js?v='+Math.random()).done(function () {
    	govAuditCost.init();
	});
	
    /*确认审定价*/
    $('.saveBtnGac').on("click",function(){
    	var t = $("#govAuditCostForm");
    	//开启表单验证
        if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
        	//验证必签签字是否已签
	        var signtools =$('#govAuditCostForm').find(".signature-tool.sign-require"),
	        stl = signtools.length,
	        sBlank = 0;
	        for(var i=0; i<stl; i++){
	        	var tstool = signtools.eq(i).next("div").find("a.btn-white"),
	        	tsinput = tstool.siblings(".sign-data-input");
	        	if(!tsinput.val() || tsinput.val().length < 312){
	        		tstool.addClass("required-sign");
	        		sBlank++;
	        	}
	        }
	        if(sBlank){
		        	$("body").cgetPopup({
		            	title: "提示信息",
		            	content: "请完成签字!",
		            	accept: popClose,
		            	chide: true,
		            	icon: "fa-warning",
		            	newpop: 'new'
		            });
	        	return false;
	        }
        	
        	
	        $("body").cgetPopup({
	        	title: "提示信息",
	        	content: "您确定要保存吗？",
	        	accept:saveBtnBack,
	        	icon: "fa-warning",
	        	//newpop: 'new'
	        });
        	
            //如果通过验证, 则移除验证UI
            t.parsley().validate();
        } else {
            //如果未通过验证, 则加载验证UI
            t.parsley().validate();
        };
    });
    var saveBtnBack = function(){

        var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
        $("#menuDes").val(menuDesc);
        var stepId = getStepId();
        $("#stepId").val(stepId);
        
        //表单序列化获取json字符串
        var data = $('#govAuditCostForm').serializeJsonString();
        $("#result").val(data);
        if($("#filePreviews tr").length) {     //有附件 
            $(".start").click();
        }else{                                        //无附件，无附件不能用有附件的方法， .start的click事件不会触发action。
        	// 防止多次提交
            if(response){
                response.abort();
            }
            var dataJson={};
            dataJson.result=data;
			var response = $.ajax({
				url: 'govAuditCost/updateGovAUditCosts',
				type: "POST",
				timeout : 5000,
				contentType: "application/json;charset=UTF-8",
				data: JSON.stringify(dataJson),
				success: function (data) {
					saveBack(data);
				}
			});
		}
	}
	/* function acceptForm(){
		$("#govAuditCostForm").formSave("budgetResultRegister/updateBudget","govAuditCostTable",govAuditCostTable,saveBack);	
	} */
	
	function saveBack(data){
		 var content = "数据保存成功！";
         if(data.result === "false"){
             content = "数据保存失败！";
         }else {
             $("#gacId").val(data.operateId);
             $("#govAuditCostTable").cgetData("",govAuditCostBack); 
     	 	 $("#govAuditCostForm").toggleEditState(false);
         }
         var myoptions = {
             title: "提示信息",
             content: content,
             accept: savedone,
             chide: true,
             icon: "fa-check-circle",
             newpop: 'new'
             
         }
         $("body").cgetPopup(myoptions);
	 	
	}
	//附件保存回调2
    var savedone = function(){
        if($("#drawName").val()){
            $(".hasVal").removeClass("hidden");
            $(".noVal").addClass("hidden");
            $("#filePreviews tr").remove();
        }else{
            $(".noVal").removeClass("hidden");
            $(".hasVal").addClass("hidden");
        }
//        console.info("~~~~~~");
//        showReport();
//        console.info("~~~~~~111111");
    }
    /*放弃*/
    $('.giveUpBtn').on("click",function(){
    	$("#govAuditCostForm").toggleEditState(false);
		$("#govAuditCostTable").cgetData("",govAuditCostBack);  //列表重新加载
		$(".editbtn").addClass("hidden");
    });
    
    /*推送*/
    $('.pushBtnGac').on("click",function(){
    	var t = $("#govAuditCostForm");
    	//开启表单验证
        if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
        	//验证必签签字是否已签
	        var signtools =$('#govAuditCostForm').find(".signature-tool.sign-require"),
	        stl = signtools.length,
	        sBlank = 0;
	        for(var i=0; i<stl; i++){
	        	var tstool = signtools.eq(i).next("div").find("a.btn-white"),
	        	tsinput = tstool.siblings(".sign-data-input");
	        	if(!tsinput.val() || tsinput.val().length < 312){
	        		tstool.addClass("required-sign");
	        		sBlank++;
	        	}
	        }
	        if(sBlank){
		        	$("body").cgetPopup({
		            	title: "提示信息",
		            	content: "请完成签字!",
		            	accept: popClose,
		            	chide: true,
		            	icon: "fa-warning",
		            	newpop: 'new'
		            });
	        	return false;
	        }
        	
        	
	        $("body").cgetPopup({
	        	title: "提示信息",
	        	content: "您确定要推送吗？",
	        	accept:pushBtnBack,
	        	icon: "fa-warning",
	        	//newpop: 'new'
	        });
        	
            //如果通过验证, 则移除验证UI
            t.parsley().validate();
        } else {
            //如果未通过验证, 则加载验证UI
            t.parsley().validate();
        };
    });
    var pushBtnBack = function(){

        //表单序列化获取json字符串
        var data = $('#govAuditCostForm').serializeJsonString();
        	// 防止多次提交
            if(response){
                response.abort();
            }
            var dataJson={};
            dataJson.result=data;
			var response = $.ajax({
				url: 'govAuditCost/pushGovAuditCost',
				type: "POST",
				timeout : 5000,
				contentType: "application/json;charset=UTF-8",
				data: data,
				success: function (data) {
					pushBack(data);
				}
			});
	}
	var pushBack = function(data){
			 var content = "数据推送成功！";
	         if(data === "false"){
	             content = "数据推送失败！";
	         }else {
	            // $("#gacId").val(data);
	             $("#govAuditCostTable").cgetData("",govAuditCostBack);   
	     	 	 $("#govAuditCostForm").toggleEditState(false);
	         }
	         var myoptions = {
	             title: "提示信息",
	             content: content,
	             accept: pushDone,
	             chide: true,
	             icon: "fa-check-circle",
	             newpop: 'new'
	             
	         }
	         $("body").cgetPopup(myoptions);
		 
	}
	var pushDone=function(){};
	
	 /* 	输入数字校验 */ 
    $('.fixed-number').on('keyup', function(){
    	  $(this).parsley().validate();
    	}); 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->