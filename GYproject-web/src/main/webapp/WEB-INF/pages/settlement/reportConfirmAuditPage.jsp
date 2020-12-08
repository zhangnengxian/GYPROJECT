<!-- accept/projectAccept.jsp -->
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
                   <h4 class="panel-title">报审</h4>
                </div>
                <div class="panel-body" >
                <div class="clearboth form-box ">
                 	<form class="form-horizontal m-t-40" id="reportConfirmForm" action="">
                 	  
					    <div class="form-group  col-md-6 ">
				        <label class="control-label" for="projNo">工程编号</label>
				        <div>
				            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value=""/>
				        </div>
					    </div>
			    		 <div class="form-group col-md-12 ">
					        <label class="control-label" for="projName">工程名称</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="100" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-12">
					        <label class="control-label" for="projAddr">工程地点</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-12">
				            <label class="control-label" for="projScaleDes">工程规模</label>
				            <div>
				                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2" data-parsley-maxlength="200"></textarea>
				            </div>
			            </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="cmoName">施工管理处</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="cmoName" name="cmoName" data-parsley-maxlength="100" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="suName">监理单位</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName" data-parsley-maxlength="100" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-12 ">
					        <label class="control-label" for="cuName">分包单位</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="cuPhone">联系方式</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="cuPhone" name="cuPhone"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					    	<label class="control-label" for="scNo">合同编号</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable" id="scNo" name="scNo"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label">报审日期</label>
					        <div>
					        	<input type="text" class="form-control field-editable input-sm datepicker-default timestamp"  id="ocoDate" name="ocoDate"  data-parsley-required="true"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="quantitiesTotal">合计</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable fixed-number"  id="quantitiesTotal" name="quantitiesTotal"  data-parsley-type="number" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="sale">销售金额</label>
					        <div>
					            <input type="text" class="form-control input-sm field-editable fixed-number"  id="sale" name="sale"  data-parsley-type="number" data-parsley-required="true" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="purStuRate">采保费率</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="purStuRate" name="purStuRate" data-parsley-maxlength="100" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="purStuCost">采保费</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable fixed-number"  id="purStuCost" name="purStuCost"  data-parsley-type="number" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 clearboth">
					        <label class="control-label" for="costType1">费用类型</label>
					        <div>
					            <input type="text" class="form-control input-sm field-editable"  id="costType1" name="costType1" data-parsley-maxlength="100" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="cost1">费用金额</label>
					        <div>
					            <input type="text" class="form-control input-sm field-editable fixed-number"  id="cost1" name="cost1"  data-parsley-type="number" value=""/>
					        </div>
					    </div>
					     <div class="form-group col-md-6">
					        <label class="control-label" for="costType2">费用类型</label>
					        <div>
					            <input type="text" class="form-control input-sm field-editable"  id="costType2" name="costType2" data-parsley-maxlength="100" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="cost2">费用金额</label>
					        <div>
					            <input type="text" class="form-control input-sm field-editable fixed-number"  id="cost2" name="cost2"  data-parsley-type="number" value=""/>
					        </div>
					    </div>
					     <div class="form-group col-md-6">
					        <label class="control-label" for="costType3">费用类型</label>
					        <div>
					            <input type="text" class="form-control input-sm field-editable"  id="costType3" name="costType3" data-parsley-maxlength="100" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="cost3">费用金额</label>
					        <div>
					            <input type="text" class="form-control input-sm field-editable fixed-number"  id="cost3" name="cost3"  data-parsley-type="number" value=""/>
					        </div>
					    </div>
					     <div class="form-group col-md-6">
					        <label class="control-label" for="costType4">费用类型</label>
					        <div>
					            <input type="text" class="form-control input-sm field-editable"  id="costType4" name="costType4" data-parsley-maxlength="100" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="cost4">费用金额</label>
					        <div>
					            <input type="text" class="form-control input-sm field-editable fixed-number"  id="cost4" name="cost4"  data-parsley-type="number" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="sendDeclarationCost">送审金额</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable fixed-number"  id="sendDeclarationCost" name="sendDeclarationCost"  data-parsley-type="number" data-parsley-required="true" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
							<label class="control-label signature-tool" for="subBudgeter">编制人</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="subBudgeter" name="subBudgeter" data-parsley-required="true">
								<input type="hidden" id="subBudgeter_postType" name="subBudgeter_postType" value="${SUB_BUDGETER }" >
								<img class="" alt="" src="" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
						</div>
						<div class="form-group col-md-6">
					        <label class="control-label" for="compiler">编制人</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="compiler" name="compiler" data-parsley-maxlength="20" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="compilerPhone">联系方式</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="compilerPhone" name="compilerPhone" data-parsley-maxlength="13" value=""/>
					        </div>
					    </div>
			    	</form>
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
			         <h4 class="panel-title">审批区</h4>
			    </div>
			    <div class="panel-body" id="connect_gas_audit_panel_box">
			    	<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSaveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 auditCancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box auditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="reportAuditRightForm" action="settlementConfirm/auditSave">
			    			<input type="hidden" id="projId" name="projId" />
			    	     	<input type="hidden" name="projNo" />
			    	     	<input type="hidden" id="businessOrderId" name="businessOrderId" />			       	
                    		<input type="hidden" id="mrAuditLevel" name="mrAuditLevel" value="${currentLevel}" />
                    		<input type="hidden" id="confirmSort" name="confirmSort" value="${confirmSort}" />
                    		<input type="hidden" name="mrDeptId" value="${loginInfo.deptId}"/>
			    			<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
				    			<label class="control-label" for="">审批结果</label>
						    	<div>
						             <c:forEach var="enums" items="${mrResult}">
					             		 <label>
							            	<input type="radio" name="mrResult" value="${enums.value}"/> ${enums.message}
							            </label>
						             </c:forEach>
						        </div>
		    				</div> 
		    				<div class="form-group col-lg-12 col-md-12 col-sm-12">
						     	<label class="control-label" for="">审批意见</label>
						     	<div> 
		        					<textarea class="form-control field-editable"  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					</div>
		   					
		   					<div class="form-group col-lg-6 col-md-12 col-sm-6">
						        <label class="control-label" for="">审批人</label>
						        <div>
						            <input type="hidden"  id="mrCsr"  name="mrCsr" data-parsley-maxlength="100" value="${loginInfo.staffId}">
						           <%-- <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}"> --%>
						        	<input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
						        </div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12 sign1 hidden">
								<label class="control-label signature-tool " for="gasPrincipal">工程公司负责人</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="gasPrincipal" name="gasPrincipal" value="">
									<input type="hidden"  id="gasPrincipal_post" name="gasPrincipal_post" value="${constPCpost }">
									<img class="gasPrincipal" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12 sign2 hidden">
								<label class="control-label signature-tool " for="costContractPrincipal">造价合同处负责人</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="costContractPrincipal" name="costContractPrincipal" value="">
									<input type="hidden"  id="costContractPrincipal_post" name="costContractPrincipal_post" value="${constPCpost }">
									<img class="costContractPrincipal" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12 sign3 hidden">
								<label class="control-label signature-tool " for="cmoDirector" id="cmoDirectorLabel">施工管理处长</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="cmoDirector" name="cmoDirector" value="">
									<input type="hidden"  id="cmoDirector_post" name="cmoDirector_post" value="${constPCpost }">
									<img class="cmoDirector" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12 sign4 hidden">
								<label class="control-label signature-tool " for="suPrincipal" id="suPrincipalLabel">分包负责人</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="suPrincipal" name="suPrincipal" value="">
									<input type="hidden"  id="suPrincipal_post" name="suPrincipal_post" value="${constPCpost }">
									<img class="suPrincipal" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12 sign5 hidden">
								<label class="control-label signature-tool " for="costControlPrincipal" >成本控制部负责人</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="costControlPrincipal" name="costControlPrincipal" value="">
									<input type="hidden"  id="costControlPrincipal_post" name="costControlPrincipal_post" value="${constPCpost }">
									<img class="costControlPrincipal" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12 sign6 hidden">
								<label class="control-label signature-tool " for="costControlReAudit">成本控制部复核人</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="costControlReAudit" name="costControlReAudit" value="">
									<input type="hidden"  id="costControlReAudit_post" name="costControlReAudit_post" value="${constPCpost }">
									<img class="costControlReAudit" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div>
						    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
						        <label class="control-label" for="">审批日期</label>
						        <div>
 									<input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" >			
 								</div>
						    </div>
			    		</form>
			    	</div>
			    	<div >
		    		<h4 class="m-t-15 m-l-7"><strong>审批历史</strong></h4>
		    		</div>
		    		<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
       					<thead>
			            	<tr>
			                <th>审批日期</th>
			                <th>审批结果</th>
			                <th>审批意见</th>
			                <th>审批人</th>
			            
	            			</tr>
          				</thead>
					</table>
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
    App.setPageTitle('报审确认 - 工程管理系统');
        var obj=${settlementDeclaration};
        $("#projId").val(obj.projId);
        console.info(obj);
        $("#reportAuditRightForm [name='projNo']").val(obj.projNo);
        $("#businessOrderId").val(obj.sdId);
        $("#reportConfirmForm").deserialize(obj);
    	//签字加载方式
        $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6").handleSignature(false);  
    	if($("#confirmSort").val()=="reportConfirm"){
    		if($("#mrAuditLevel").val()=="1"){
    			//分包负责人
    			$(".sign4").removeClass("hidden");
    			$('#suPrincipalLabel').addClass('sign-require');
    		}else{
    			//施工处长
    			$(".sign3").removeClass("hidden");
    			$('#cmoDirectorLabel').addClass('sign-require');
    		}
    		  		
    	}else if($("#confirmSort").val()=="startConfirm"){
    		if($("#mrAuditLevel").val()=="1"){
    			$(".sign2").removeClass("hidden");
    		}else{
    			$(".sign1").removeClass("hidden");
    		}
    		
    	}else if($("#confirmSort").val()=="endConfirm"){
    		if($("#mrAuditLevel").val()=="1"){
    			$(".sign6").removeClass("hidden");
    		}else{
    			$(".sign5").removeClass("hidden");
    		}
    	}
    	$("#reportConfirmForm").styleFit();
        $("#reportAuditRightForm").styleFit();
    
    //表单不可编辑
    $("#reportConfirmForm").toggleEditState(false);
    $("#reportAuditRightForm").toggleEditState(true);
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    $("#mrTime").change();
    
    $.getScript('projectjs/settlement/confirm-audit-page.js').done(function () {
        gasAuditHistory.init("report");
	});
    
    //放弃
    $(".auditCancelBtn").off("click").on("click",function(){
		$("#ajax-content").cgetContent("settlementConfirm/reportConfirm");
	});
    
    //保存
    $(".auditSaveBtn").off("click").on("click",function(){
    	var val=$('#reportAuditRightForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		alertInfo("请选择审批结果！");
    	}else{
    		$("#reportAuditRightForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".auditSaveBtn"));
    	}
    	
    });
    
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".auditSaveBtn").addClass("hidden");
    		$(".auditCancelBtn").text("返回");
    		$("#reportAuditRightForm").toggleEditState(false);
    		$(".auditFormDiv").addClass("hidden");
    	}
    }
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//审核过
    		auditSaveCallBack("true");
    		
    	}
    }();
    

</script>
<!-- ================== END PAGE LEVEL JS ================== -->