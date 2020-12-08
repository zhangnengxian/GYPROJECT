<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
       	<div class="panel panel-inverse tabs-mixin" id="">
			<div class="panel-heading">
               	<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                </div>
                <h4 class="panel-title">分部验收审核信息</h4>
			</div>
            <div class="panel-body">
	              	<form class="form-horizontal" id="divisionalAcceptanceApplyForm" action="">
			       		<input type="hidden" id="applyerId" name="applyerId"/>
			       		<div class="form-group col-md-6 col-sm-12">
					    	<label class="control-label" for="">工程编号</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"   value="" />
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<label class="control-label" for="conNo">合同编号</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="conNo" name="conNo"   value="" />
					        </div>
					    </div>
					    <div class="form-group col-md-12 col-sm-12">
					        <label class="control-label" for="">工程名称</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName"  value=""/>
					        </div>
					    </div>
					    <div class="form-group  col-md-12 col-sm-12">
					        <label class="control-label" for="">工程地点</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-12 col-sm-12">
					        <label class="control-label" for="projScaleDes">工程规模</label>
					        <div>
					            <textarea  class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes" ></textarea>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">施工单位</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"  value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">监理单位</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName"  value=""/>
					        </div>
					    </div>
					    
					    
					    <div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>工程项目概况</h4></div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">报建时间</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="acceptDate" name="acceptDate"  value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">设计委托时间</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="ocoDate" name="ocoDate"  value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">计划编制时间</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="cpArriveDate" name="cpArriveDate"  value=""/>
					        </div>
					    </div>
					    
						<div class="form-group col-md-6 col-sm-12 ">
					    	<!-- 新加字段 -->
							<label class="control-label" for="suReport">有无施工图</label>
							<div>
								<label><input class="field-editable" type="radio" name="consDrawingSituation" value="1"  checked> 有</label>
								<label><input class="field-editable" type="radio" name="consDrawingSituation" value="2" > 无</label>
							</div>
					    </div>
						<div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">计划竣工时间</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="plannedEndDate" name="plannedEndDate"  value=""/>
					        </div>
					    </div>
			
						<div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">进场时间</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="startDate" name="startDate"  value=""/>
					        </div>
					    </div>
					    <div class="form-group col-sm-12">
							<label class="control-label" for="thisAcceptanceContent">本次验收内容</label>
							<div>
								<textarea class="form-control field-not-editable" name="thisAcceptanceContent" id="thisAcceptanceContent" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">完成情况</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-editable"  id="finishedSituation" name="finishedSituation"  data-parsley-maxlength="50" value=""/>
					        </div>
					    </div>
						<div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">自检情况</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-editable"  id="selfCheckSituation" name="selfCheckSituation" data-parsley-maxlength="50" value=""/>
					        </div>
					    </div>
						<c:choose>
						<c:when  test="${!empty viewPageUrl}">
	   					       <!-- 如果不为空，则显示计划通气时间的页面，即需要配置分公司页面数据 -->
	   						<c:import url="${viewPageUrl }.jsp"></c:import>
	   					</c:when>
	   					<c:otherwise>
							<div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>监理情况说明</h4></div>
							<div class="form-group col-md-6 col-sm-12 ">
								<!-- 新加字段 -->
								<label class="control-label">情况说明</label>
								<div>
									<label><input class="field-editable" type="radio" name="supervisorOpinion" value="1"  checked> 有</label>
									<label><input class="field-editable" type="radio" name="supervisorOpinion" value="2" > 无</label>
								</div>
							</div>
							
							<div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>试压记录</h4></div>
						    <div class="form-group col-md-6 col-sm-12 ">
						    	<!-- 新加字段 -->
								<label class="control-label">试压记录</label>
								<div>
									<label><input class="field-editable" type="radio" name="testRecord" value="1"  checked> 有</label>
									<label><input class="field-editable" type="radio" name="testRecord" value="2" > 无</label>
								</div>
						    </div>
						    
						    <div class="form-group col-md-6 col-sm-12">
						    	<!-- 新加字段 -->
						    	<label class="control-label">检查情况</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-editable"  id="checkSituation" name="checkSituation"  data-parsley-maxlength="50"value=""/>
						        </div>
						    </div>
						
						    <div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>竣工图</h4></div>
						    <div class="form-group col-md-6 col-sm-12 ">
						    	<!-- 新加字段 -->
								<label class="control-label">竣工图</label>
								<div>
									<label><input class="field-editable" type="radio" name="consDrawingSituation" value="1" checked> 有</label>
									<label><input class="field-editable" type="radio" name="consDrawingSituation" value="2"> 无</label>
								</div>
						    </div>
						
							<div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>吹扫记录</h4></div>
							<div class="form-group col-md-6 col-sm-12 ">
								<!-- 新加字段 -->
								<label class="control-label">吹扫记录</label>
								<div>
									<label><input class="field-editable" type="radio" name="completedDrawingSituation" value="1" checked> 有</label>
									<label><input class="field-editable" type="radio" name="completedDrawingSituation" value="2" > 无</label>
								</div>
							</div>
							</c:otherwise>
						</c:choose>
						
						    <%--<div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>安全告知情况</h4></div>--%>
						    <%--<div class="form-group col-md-6 col-sm-12">--%>
						    	<%--<!-- 新加字段 -->--%>
						    	<%--<label class="control-label" for="">燃气器选型</label>--%>
						        <%--<div>--%>
						        	<%--<input type="text" class="form-control input-sm field-editable"  id="modelSelectSituation" name="modelSelectSituation"  data-parsley-maxlength="50"value=""/>--%>
						        <%--</div>--%>
						    <%--</div>--%>
						    <%--<div class="form-group col-md-6 col-sm-12">--%>
						    	<%--<!-- 新加字段 -->--%>
						    	<%--<label class="control-label" for="">协议签订</label>--%>
						        <%--<div>--%>
						        	<%--<input type="text" class="form-control input-sm field-editable"  id="signSituation" name="signSituation" data-parsley-maxlength="50" value=""/>--%>
						        <%--</div>--%>
						    <%--</div>--%>
						
						    <div class="form-group col-md-6 col-sm-12">
						    	<!-- 新加字段 -->
						        <label class="control-label" for="applyer">申请人</label>
						    	<div>
						    		<input type="text" class="form-control input-sm field-not-editable"  id="applyer" name="applyer"   value=""/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
						    	<%--<!-- 新加字段 -->--%>
						        <label class="control-label" for="applyDate">申请日期</label>
						    	<div>
						    		<input type="text" class="form-control input-sm field-editable datepicker-default"  id="applyDate" name="applyDate"  data-parsley-required="true" value=""/>
						        </div> 
						    </div>
						    <div class="form-group col-md-12 col-sm-12">
						    	<!-- 新加字段 -->
						        <label class="control-label" for="remark">备注</label>
						    	<div>
						    		<textarea  class="form-control input-sm field-editable"  id="remark" name="remark" data-parsley-maxlength="200" ></textarea>
						        </div>
						    </div>
					</form>
	        </div>
		</div>
		</div>
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" id="">
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
			    		
			    		<form class="form-horizontal" id="divisionalAcceptanceAuditForm" action="divisionalAcceptanceAudit/auditSave">
			    			<input type="hidden"id="projId" name = "projId" value = "${daa.projId}"/>
			    			<input type="hidden"  name = "projNo" value = "${daa.projNo}"/>
                    		<input type="hidden"id="businessOrderId" name = "businessOrderId" value = "${daa.daaId}"/>
                    		<input type="hidden" name = "mrAuditLevel" value = "${currentLevel}"/>
			    			<input type="hidden" id="menuId" name = "menuId" value="110720"/>
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
    App.setPageTitle('预算审核 - 工程管理系统');
    
    $("#divisionalAcceptanceAuditForm").toggleEditState();
    $("#divisionalAcceptanceAuditForm").styleFit();
   
    
    $("#divisionalAcceptanceApplyForm").toggleEditState();
    $("#divisionalAcceptanceApplyForm").styleFit();
    
    
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/complete/divisional-acceptance-audit-page.js?v='+Math.random()).done(function () {
    	auditHistory.init();
	});
    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("divisionalAcceptanceAudit/main");
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
    var queryDetail=function(){
    	var projId=$("#projId").val(),daaId=$("#businessOrderId").val();
    	var data={"projId":projId,"daaId":daaId};
    	var url = 'divisionalAcceptanceApply/viewDivisionalAcceptanceApply';
		var f = $("#divisionalAcceptanceApplyForm");
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
    }();
    
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//审核过
    		auditSaveCallBack("true");
    	}else{
    		$(".signPicture").attr("src","attachments/sign/"+$("#signPicture").val())
    	}
    }();
		
		
</script>
<!-- ================== END PAGE LEVEL JS ================== -->