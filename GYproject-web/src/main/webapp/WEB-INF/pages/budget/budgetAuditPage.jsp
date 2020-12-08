<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
       <div class="col-sm-6 col-xs-12">
       	<div class="panel panel-inverse tabs-mixin">
			<div class="panel-heading">
               	<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                </div>
                <h4 class="panel-title">预算信息</h4>
			</div>
            <div class="panel-body">
	        	 <div class="toolBtn f-r p-b-10  hidden">
					<a href="javascript:;" class="btn btn-confirm btn-sm checkRole drawingReviewUpdataBtn" >修改</a>
					<a href="javascript:;" class="btn btn-confirm btn-sm checkRole drawingReviewSaveBtn" >保存</a>
				</div> 
	            <!-- 预算详述 -->
				<div class="clearboth form-box">
					<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
					<%--//值为2时文件格式支持多种（不影响其他页面功能写死）--%>
					<input type="hidden" id="changeType" value="2"/>
					<form class="form-horizontal" id="budgetSumForm" action="budgetResultRegister/updateBudgetFile"  enctype="multipart/form-data" >
						<input type="hidden" name="projId" id="projId" />
						<input type="hidden" name="budgetId" id="budgetId" />
						<input type="hidden" id="result" name="result">
						<input type="hidden" id="menuDes" name="menuDes">
						<input type="hidden" name="stepId" id="stepId" />
						<input type="hidden" name="alPath" id="alPath" />
						<input type="hidden" id="drawUrl" name="drawUrl" value="${drawUrl1}">
						<input type="hidden" name ="corpId" />
						<input type="hidden" name ="deptId" />
						<input type="hidden" name ="tenantId" />
						<input type="hidden" name ="budgeterId" />
						<div class="form-group col-md-6">
					   	<label class="control-label" for="projNo">工程编号</label>
					       <div>
					       	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
					       </div>
					   </div>
					   <div class="form-group col-sm-12  ">
					       <label class="control-label" for="projName">工程名称</label>
					       <div>
					           <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
					       </div>
					   </div>
					   <div class="form-group col-sm-12  ">
					       <label class="control-label" for="projAddr">工程地点</label>
					       <div>
					           <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
					       </div>
					   </div>
					   <div class="form-group col-sm-12">
					          <label class="control-label" for="projScaleDes">工程规模</label>
					          <div>
					              <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200" value=''></textarea>
					          </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100"/>
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
		    	<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"  data-parsley-maxlength="100"/>
		    		
		    		<!-- <select class="form-control input-sm field-not-editable" id="deptName"  name="deptName" data-parsley-required="true" >
						<option value="1" >民用组</option>
		             </select> -->
		        </div>
		    </div>
					   <div class="form-group col-sm-12 ">
					       <label class="control-label" for="custName">申报单位</label>
					       <div>
					           <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""/>
					       </div>
					   </div>
					   <div class="form-group col-md-6 col-sm-12">
					       <label class="control-label" for="custContact">联系人</label>
					       <div>
					           <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="20" value=""/>
					       </div>
					   </div>
					   <div class="form-group col-md-6 col-sm-12">
					       <label class="control-label" for="custPhone">联系电话</label>
					       <div>
					           <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="12" value=""/>
					      
					       </div>
					   </div>
					   <div class="form-group col-md-6 col-sm-12">
					       <label class="control-label" for="surveyer">勘察人</label>
					       <div>
					          <input type="text" class=" form-control input-sm field-not-editable" id="surveyer"  name="surveyer" data-parsley-maxlength="20" value="">
					       </div>
					   </div>
					   <div class="form-group col-md-6 col-sm-12">
					       <label class="control-label" for="designer">设计人</label>
					       <div>
					          <input type="text" class=" form-control input-sm field-not-editable" id="designer"  name="designer" data-parsley-maxlength="20" value="">
					       </div>
					   </div>
						<div class="form-group col-md-6 col-sm-12 clearboth">
						<label class="control-label" for="">预算总造价</label>
					          <div>
					          	<input type="text" class="form-control input-sm  fixed-number field-editable text-right" data-parsley-required="true" data-parsley-min="0.01" id="budgetTotalCost" name="budgetTotalCost" value=""/>
					          	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
					          </div>
						</div>
						<div class="form-group col-lg-6 col-md-12 col-sm-6">
							<label class="control-label" for="">工程费</label>
					          <div>
					          	<input type="text" class="form-control input-sm  fixed-number field-editable text-right" data-parsley-min="0.01" id="civilCost" name="civilCost" value=""/>
					          	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
					          </div>
					   </div>
					    <!-- <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="">审定价</label>
					          <div>
					          	<input type="text" class="form-control input-sm  fixed-number field-editable text-right"  data-parsley-min="0.01" id="authorizedCost" name="authorizedCost" value=""/>
					          </div>
					   </div> -->
				          <div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
				              <label class="control-label" for="materialCost">主材费</label>
				              <div>
				           		<input type="text" class="form-control input-sm fixed-number field-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="materialCost" name="materialCost" value=""/>
				            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				             </div>
				          </div>
						<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					            <label class="control-label" for="">监理费</label>
					            <div>
					       			<input type="text" class="form-control input-sm  fixed-number field-editable text-right"    id="suCost" name="suCost" value=""/>
					       			<a href="javascript:;" class="btn btn-sm btn-default">元</a>
					         </div>
					      </div>
					      <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
					          <label class="control-label" for="">带气作业费</label>
					          <div>
					       		<input type="text" class="form-control input-sm  fixed-number field-editable text-right"    id="inspectionCost" name="inspectionCost" value=""/>
					         	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
					         </div>
					      </div>
						<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
							<label class="control-label" for="gasTimes">带气次数</label>
							<div>
								<input type="text" class="form-control input-sm field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="6" id="gasTimes" name="gasTimes" value=""/>
								<a href="javascript:;" class="btn btn-sm btn-default">次</a>
							</div>
						</div>
				          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				              <label class="control-label" for="designCost">设计费</label>
				              <div>
				           		<input type="text" class="form-control input-sm fixed-number field-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="designCost" name="designCost" value=""/>
				            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				             </div>
				          </div>
					      <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				              <label class="control-label" for="unforeseenCost">不可预见费</label>
				              <div>
				           		<input type="text" class="form-control input-sm fixed-number field-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="unforeseenCost" name="unforeseenCost" value=""/>
				            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				             </div>
				          </div>
						<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
							<label class="control-label" for="annunciatorCost">报警器费用</label>
							<div>
								<input type="text" class="form-control input-sm fixed-number field-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="annunciatorCost" name="annunciatorCost" value=""/>
								<a href="javascript:;" class="btn btn-sm btn-default">元</a>
							</div>
						</div>
					    <div class="form-group col-md-12">
					        <label class="control-label" for="remark">备注</label>
					        <div>
					            <textarea class="form-control  field-editable" name ="remark" id="remark" rows="5" data-parsley-maxlength="400" value=''></textarea>
					        </div>
					       </div>	
					       <!-- <div class="form-group col-md-12">
					        <label class="control-label" for="">附件</label>
					      		<div>
									<div class="hidden hasVal"> 
					     			<input type="text" class="form-control input-sm field-not-editable" id="drawName" name="drawName"/>
					     			<a class="btn btn-sm btn-primary searchButton " href="" target="_blank">查看</a>
					    		        <a class="btn btn-sm btn-primary Search_Button" href="javascript:">查看</a>
					    		        <a class="del_btn btn btn-sm btn-danger hidden"><i class="fa fa-times"></i> 删除</a>
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
					      <div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
					           <label class="control-label" for="budgeter">预算员</label>
					           <div>
					        		<input type="text" class="form-control input-sm field-not-editable"    id="budgeter" name="budgeter" value=""/>
					          </div>
					       </div>
					       <div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
					           <label class="control-label" >预算日期</label>
					           <div>
					        		<input type="text" class="form-control input-sm field-not-editable timestamp all"    id="budgetDate" name="budgetDate" value=""/>
					          </div>
					       </div>
					        <%-- <div class="form-group col-md-6"> 
					      	<label class="control-label signature-tool sign-require" for="budgeterSign">签字</label>
							<div>
							<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="budgeterSign" name="budgeterSign" data-parsley-required="true">
							<input type="hidden" id="budgeterSign_postType" name="budgeterSign_postType" value="${budgeterPost }" >
							<img class="budgeterSign" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
					    	</div>  --%>
						</form>
					</div>
	            </div>
	        </div>
		</div>
		<!-- col-sm-6 end  -->
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" >
			<div class="panel-heading">
				<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	            </div>
				<h4 class="panel-title">确认区</h4>
			</div>
			<div class="panel-body" id="drawing_audit_panel_box">
					<div id="budgetAuditTopBox">
						<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
         				
					</div>
			    	<div class="clearboth form-box budgetAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="budgetAuditForm" action="budgetAudit/auditSave">
			    			<input type="hidden" id="projId1" name = "projId" value = "${projId}"/>
			    			<input type="hidden" id="projNo1" name = "projNo"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" />
                    		<input type="hidden" name = "mrAuditLevel" value = "${currentLevel}"/>
			    			<div class="form-group col-md-12">
			        			<label class="control-label" for="">确认结果</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="1" />通过
				            	</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="0" />不通过
				            	</label>
		    				</div>
		    				<div class="form-group col-md-12">
						     	<label class="control-label" for="">确认意见</label>
						     	<div> 
		        					<textarea class="form-control "  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					</div>
		   					<div class="form-group col-md-6">
						        <label class="control-label" for="">确认人</label>
						        <div>
						           <input type="hidden"  id="mrCsr"  name="mrCsr" data-parsley-maxlength="100" value="${loginInfo.staffId}">
						           <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
						        </div>
						    </div>
						    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
						        <label class="control-label" for="">确认日期</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" data-parsley-maxlength="100" >
						        </div>
						    </div>
			    		</form>
			    	</div>
			    	<div >
		    		<h4 class="m-t-15 m-l-7"><strong>确认历史</strong></h4>
		    		</div>
		    		<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
       					<thead>
			            	<tr>
			                <th>确认日期</th>
			                <th>确认结果</th>
			                <th>确认意见</th>
			                <th>确认人</th>
	            			</tr>
          				</thead>
					</table>
					</div>
			    </div>
	     	</div>
		</div>	    	
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
    App.setPageTitle('预算审核 - 工程管理系统');
    
    $("#budgetSumForm").toggleEditState();
    $("#budgetSumForm").styleFit();
    
    $("#budgetAuditForm").toggleEditState();
    $("#budgetAuditForm").styleFit();
   
    $("#budgetDispatchForm").toggleEditState();
    $("#budgetDispatchForm").styleFit();
    
    
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/budget/budget-audit-page.js').done(function () {
        budgetAudit.init();
	});

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("budgetAudit/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
    	var val=$('#budgetAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
    		$("#budgetAuditForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','', $(".saveBtn"));
    	}
    });
    
    
    var ensureDone=function(){};
    
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.budgetAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#budgetAuditForm input:radio[name="mrResult"]:checked').val();
    		$("#budgetAuditForm").toggleEditState(false); 
    	}
    	console.info("de..."+$("#budgeterId").val());
    	if($("#budgeterId").val()!=""){
    		//已派遣预算员
    		$(".dispatchBtnChange").addClass("hidden");
    	}
    }
    
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//确认过
    		auditSaveCallBack("true");
    	}
    }();
    
    
    //点击派工
    $(".dispatchBtn").off().on("click",function(){
    	var len=$("#budgeterTable").find("tr.selected").length;
    	if(len>0){
    		var budgeter = $("#budgeterTable").find("tr.selected td:eq(0)").text();
    		$("#budgeter").val(budgeter);//选择的预算员
    		 $("body").cgetPopup({
          	    title: "提示信息",
             	content: '确认要派工给 <i class="fa fa-user"></i> '+budgeter+" 吗？",
             	accept: function(){
          		var data=$("#budgetDispatchForm").serializeJson();
          		data.projId=$("#projId").val();
    	        	$.ajax({
    	                type: 'POST',
    	                url: 'drawingSignAndAudit/updateProject',
    	                contentType: "application/json;charset=UTF-8",
    	                data: JSON.stringify(data),
    	                success: function (data) {
    	                	var content = "派工成功！";
    	                	if(data === "false"){
    	                		content = "派工失败！";
    	                	}else if(data === "true"){
    	                	    $('#budgeterTable').cgetData(true);
    	                	    $(".dispatchBtn").addClass("hidden");
    	                	}
    	                	var myoptions = {
    	                        	title: "提示信息",
    	                        	content: content,
    	                        	accept: popClose,
    	                        	chide: true,
    	                        	newpop: 'new',
    	                        	icon: "fa-check-circle"
    	                    }
    	                    $("body").cgetPopup(myoptions);
    	                },
    	                error: function (jqXHR, textStatus, errorThrown) {
    	                    console.warn("预算派遣区派工失败！");
    	                }
    	            }); 
	          	},
	          	icon: "fa-check-circle",
	          	newpop: 'new'
      		});
    	}else{
    		$("body").cgetPopup({
					title: '提示',
					content: '请选择预算员！',
					accept: popClose
		    });
    	}
    	
    })

    $("#budgetAuditForm input[name='mrResult']").on("change",function(){
        if($('#budgetAuditForm input:radio[name="mrResult"]:checked').val() == "1"){
			$("#mrAopinion").val("同意");
		}else{
            $("#mrAopinion").val("不同意");
		}
	})
        
</script>
<!-- ================== END PAGE LEVEL JS ================== -->