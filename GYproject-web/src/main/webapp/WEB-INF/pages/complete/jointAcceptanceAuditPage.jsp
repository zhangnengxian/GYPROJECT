<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
       	<div class="panel panel-inverse tabs-mixin" >
			<div class="panel-heading">
               	<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                </div>
                <h4 class="panel-title">联合验收审核信息</h4>
			</div>
            <div class="panel-body">
	              	<form class="form-horizontal" id="jointAcceptanceApplyForm" action="">
    		<!-- <button type="reset" class="hidden" id="reset"/> -->
    		<input type="hidden" id="projId" name="projId" value="${project.projId }"/>
    		<input type="hidden" id="jaId" name="jaId" value="${ja.jaId }"/>
    		<input type="hidden" id="flag" name="flag" />
    		<input type="hidden"  id="applyerId" name="applyerId"  value="${ja.applyerId }"/>
    		<input type="hidden"  id="auditState" name="auditState"  value="${ja.auditState }"/>
    		<input type="hidden"  id="version" name="version"  value=""/>
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50"  value="${ja.projNo }" />
		        </div>
		    </div>
		    
		    <div class="form-group col-md-12 col-sm-12">
		        <label class="control-label" for="">工程名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value="${project.projName }" />
		        </div>
		    </div>
		    <div class="form-group  col-md-12 col-sm-12">
		        <label class="control-label" for="">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value="${project.projAddr }"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		            <textarea  class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes" >${project.projScaleDes }</textarea>
		        </div>
		    </div>
		   	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  value="${project.corpName }"/>
		        </div>
		    </div>
        	<div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value="${project.projectTypeDes }"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value="${project.contributionModeDes }"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    		 <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value="${project.deptName }"/>        
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for="">施工单位</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"  value="${project.cuName }"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for="">监理单位</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName"  value="${project.suName }"/>
		        </div>
		    </div>
		    
			<div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>试压记录</h4></div>
		    <div class="form-group col-md-6 col-sm-12 ">
		    	<!-- 新加字段 -->
				<label class="control-label">试压记录</label>
				<div>
					<label><input class="field-editable" type="radio" name="isStrenthTest" value="1" <c:if test="${ja.isStrenthTest==1 }"> checked</c:if> > 有</label>
					<label><input class="field-editable" type="radio" name="isStrenthTest" value="2" <c:if test="${ja.isStrenthTest==2 }"> checked</c:if>> 无</label>
				</div>
		    </div>
		    <div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>竣工资料</h4></div>
		    <div class="form-group col-md-6 col-sm-12 ">
		    	<!-- 新加字段 -->
				<label class="control-label">竣工资料</label>
				<div>
					<label><input class="field-editable" type="radio" name="isCompleteReport" value="1" <c:if test="${ja.isCompleteReport==1 }"> checked</c:if>> 有</label>
					<label><input class="field-editable" type="radio" name="isCompleteReport" value="2" <c:if test="${ja.isCompleteReport==2 }"> checked</c:if>> 无</label>
				</div>
		    </div>

			<div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>预验收记录</h4></div>
			<div class="form-group col-md-6 col-sm-12 ">
				<!-- 新加字段 -->
				<label class="control-label">预验收记录</label>
				<div>
					<label><input class="field-editable" type="radio" name="isPreInspection" value="1" <c:if test="${ja.isPreInspection==1 }"> checked</c:if> > 有</label>
					<label><input class="field-editable" type="radio" name="isPreInspection" value="2" <c:if test="${ja.isPreInspection==2 }"> checked</c:if> > 无</label>
				</div>
			</div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label">申请时间</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable " id="applyDate" name="applyDate"  value="${ja.applyDate}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label">计划验收日期</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable " id="planAcceptDate" name="planAcceptDate"  value="${ja.planAcceptDate }"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label">申请人</label>
		        <div>
		        	
		        	<input type="text" class="form-control input-sm field-not-editable datepicker-default" id="applyer" name="applyer"  value="${ja.applyer }"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for="applyRemark">申请备注</label>
		        <div>
		        	<textarea class="form-control input-sm field-editable "  id="applyRemark" name="applyRemark" data-parsley-maxlength="200"   rows="2">${ja.applyRemark }</textarea>
		        </div>
		    </div>
		</form>
	        </div>
		</div>
		</div>
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
					<div id="divisionalAcceptanceAuditTopBox">
						<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
         				
					</div>
			    	<div class="clearboth form-box divisionalAcceptanceAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<input  type="hidden" id="signPicture" name = "signPicture" value = "${loginInfo.signPicture}"/>
			    		<form class="form-horizontal" id="divisionalAcceptanceAuditForm" action="jointAcceptanceAudit/auditSave">
			    			<input type="hidden"id="projId" name = "projId" value = "${ja.projId}"/>
			    			<input type="hidden"  name = "projNo" value = "${ja.projNo}"/>
                    		<input type="hidden"id="businessOrderId" name = "businessOrderId" value = "${ja.jaId}"/>
                    		<input type="hidden" name = "mrAuditLevel" value = "${currentLevel}"/>
                    		<input type="hidden" id="menuId" name = "menuId" value="1107032"/>
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
						    <div class="form-group col-md-6">
								<label class="control-label signature-tool" for="" style="width: 90px;">签字</label>
								<div>
									<input type="hidden" class="sign-data-input disabled" id="firstSettlement" name="firstSettlement" value="">
									<img class="signPicture" alt="" src="" style="height: 30px"> 
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

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('联合验收审核 - 工程管理系统');
    
    $("#divisionalAcceptanceAuditForm").toggleEditState();
    $("#divisionalAcceptanceAuditForm").styleFit();
   
    
    $("#jointAcceptanceApplyForm").toggleEditState();
    $("#jointAcceptanceApplyForm").styleFit();
    
    
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/complete/joint-acceptance-audit-page.js?v='+Math.random()).done(function () {
    	auditHistory.init();
	});

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("jointAcceptanceAudit/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
    	var val=$('#divisionalAcceptanceAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
    		$("#divisionalAcceptanceAuditForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".saveBtn"));
    	}
    });
    
    
    var ensureDone=function(){};
    
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.divisionalAcceptanceAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#divisionalAcceptanceAuditForm input:radio[name="mrResult"]:checked').val();
    		$("#divisionalAcceptanceAuditForm").toggleEditState(false); 
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
    
    
    
    //查询详述
   /*  var queryDetail=function(){
    	var projId=$("#projId").val(),jaId=$("#businessOrderId").val();
    	var data={"projId":projId,"jaId":jaId};
    	var url = 'jointAcceptanceAudit/viewJointAcceptance';
		var f = $("#jointAcceptanceApplyForm");
		 $.ajax({
	         type: 'POST',
	         url: url,
	         data: data,
	         dataType: 'json',
	         success: function (data) {
	        	 //$("#divisionalAcceptanceApplyForm").formReset();
	        	 var selects = f.find('select[disabled]');
	             selects.removeAttr("disabled");
	             $("input:radio").attr("disabled",false);
	             //表单反序列化填充值
	             f.deserialize(fixNull(data));
	             selects.attr("disabled","disabled");
	         },
	         error: function (jqXHR, textStatus, errorThrown) {
	             console.warn("cgetDetail() -> 详情查询失败");
	         }
	     });
    }(); */
    
	
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//审核过
    		auditSaveCallBack("true");
    	}else{
    		$(".signPicture").attr("src","attachments/sign/"+$("#signPicture").val())
    	}
    	/*if($("#applyDate").val()!=""){
            var s = $("#applyDate").val();
            s=s.substr(0,10);
            console.info(s);
            $("#applyDate").val(s)
		}*/

    }();
		
</script>
<!-- ================== END PAGE LEVEL JS ================== -->