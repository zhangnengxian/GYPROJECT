<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <h4 class="panel-title">工程造价信息</h4>
			</div>
            <div class="panel-body">
	        	 <div class="toolBtn f-r p-b-10 auditSaveBtn ">
					<a href="javascript:;" class="btn btn-confirm btn-sm checkRole auditSaveBtn" >保存</a>
				</div> 
				<div class="clearboth form-box">
					<form class="form-horizontal" id="projCostForm"  data-parsley-validate="true" action="">
						<input type="hidden" name="projId" id="projId"/>
						<input type="hidden" name="stepId" id="stepId" value="${stepId}"/>
						<input type="hidden" name="corpId" id="corpId"  value="${loginInfo.corpId}"/>
						<input type="hidden" name="isDisplay" id="isDisplay"  value="${buttonDisplay.isDisplay}"/>
						<input type="hidden" name="auditLevel" id="auditLevel"  value="${buttonDisplay.auditLevel}"/>
						
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
					        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" value="贵州燃气集团股份有限公司"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">工程类型</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-not-editable" name="projectTypeDes"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">出资方式</label>
					    	<div>
					    	<input type="text" class="form-control input-sm field-not-editable" name="contributionModeDes"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">业务部门</label>
					    	<div>
					    	<input type="text" class="form-control input-sm field-not-editable" name="deptName"/>
					        </div>
					    </div>
						<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
							<label class="control-label" for="budgetTotalCost">总造价</label>
				            <div>
				            	<input type="text" class="form-control input-sm field-not-editable cost-i fixed-number text-right"  id="budgetTotalCost" name="budgetTotalCost" value=""/>
				            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				            </div>
						</div>
						<div class="form-group col-lg-6 col-md-12 col-sm-6">
							<label class="control-label" for="storeCost">工程费</label>
							<div>
								<input type="text" class="form-control input-sm  fixed-number field-not-editable text-right" data-parsley-min="0.01" data-parsley-maxlength="16" id="storeCost" name="storeCost" value=""/>
								<a href="javascript:;" class="btn btn-sm btn-default">元</a>
							</div>
						</div>
				          <div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
				              <label class="control-label" for="materialCost">主材费</label>
				              <div>
				           		<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="materialCost" name="materialCost" value=""/>
				            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				             </div>
				          </div>
				        <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				        	<label class="control-label" for="suCost">监理费</label>
				            <div>
				            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="suCost" name="suCost" value=""/>
				            <a href="javascript:;" class="btn btn-sm btn-default">元</a>
				            </div>
				        </div>
				        <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				        	<label class="control-label" for="inspectionCost">带气作业费</label>
				            <div>
				            	<input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="inspectionCost" name="inspectionCost" value=""/>
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
				           		<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="designCost" name="designCost" value=""/>
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
							<label class="control-label" for="euAmount">报警器费用</label>
							<div>
								<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="euAmount" name="euAmount" value=""/>
								<a href="javascript:;" class="btn btn-sm btn-default">元</a>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-12 col-sm-6">
				        	<label class="control-label" for="">是否退单</label>
				    		<div>
					            <label>
					              	<input type="radio" class="field-not-editable"  name="isBack"  value="1" /> 是
					            </label>
					            <label>
					              	<input type="radio" class="field-not-editable"  name="isBack"  value="0" checked/> 否
					            </label>
				        	</div>
						</div>
				        <div class="form-group col-md-6 back-hid clearboth">
							<label class="control-label " for="confirmTotalCost">确定造价</label>
				            <div>
				            	<input type="text" class="form-control input-sm field-editable fixed-number text-right"  data-parsley-type='number' id="confirmTotalCost" name="confirmTotalCost"  data-parsley-required="true" value=""/>
				            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				            </div>
						</div>
						<div class="form-group col-md-6 back-hid">
				            <label class="control-label" for="changeReason">变动原因</label>
					        <div>
					        
					        	<select class="form-control input-sm field-editable" id="changeReason"   name="changeReason"  >
					 		      	<option ></option>
					 		      	<c:forEach var="cr" items="${changeReasonEnum}" >
										<option value="${cr.value}" >${cr.message}</option>
				                	</c:forEach>
					            </select>
					        </div>
						</div> 
						<div class="form-group col-md-12 projCostAuditType clearboth">
				            <label class="control-label" for="projCostAuditType">审批范围</label>
					        <div>
					        	<select class="form-control input-sm field-editable" id="projCostAuditType"   name="projCostAuditType"  >
					        		<option ></option>
					 		      	<c:forEach var="pct" items="${auditRange}">
										<option value="${pct.supSql}" >${pct.remark}</option>
				                	</c:forEach>
					            </select>
					        </div>
						</div>
						<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
							<label class="control-label" for="reduceGasTimes">减免次数</label>
							<div>
								<input type="text" class="form-control input-sm field-editable text-right" data-parsley-type="number" data-parsley-maxlength="6" id="reduceGasTimes" name="reduceGasTimes" value=""/>
								<a href="javascript:;" class="btn btn-sm btn-default">次</a>
							</div>
						</div>
						<div class="form-group col-md-12 back-hid ">
				            <label class="control-label" for="costRemark">造价备注</label>
					        <div>
					        	<textarea class="form-control input-sm field-editable" rows="2" id="costRemark"   name="costRemark" data-parsley-required="true"></textarea>
					        </div>
						</div>
					   <%--  <div class="form-group col-sm-12">
					    	<label class="control-label" for="">用气规模</label>
					    	<div>
					             <c:forEach var="plType" items="${projLtype}">
				             		 <label>
						            	<input type="radio"  name="projLtype" class="field-editable" data-text='${plType.projTypeDes}' value="${plType.projTypeId}"/>${plType.projTypeDes}
						            </label>
					             </c:forEach>
					        </div>
					    	<input type="hidden" class="projLtypeId" name="projLtypeId"/>
					   </div> --%>
					</form>
				</div>
					<div class="clearboth form-box m-t-5">
						<form id="scaleTableForm" data-parsley-validate="true">
							<!-- <table id="scaleTable" class="table table-striped table-bordered nowrap" width="100%"">
					        	<thead>
						            <tr>
						                <th width="60px">细类</th>
						                <th width="40px">吨位</th>
						                <th width="40px">座数</th>
						                <th width="40px">台数</th>
						                <th width="40px">户数</th>
						                <th width="40px">预计用量(m³/h)</th>
						                <th width="40px">规模造价</th> 
						            </tr>
					           </thead>
							</table> -->
						</form>
					</div>
	            </div>
	        </div>
		</div>
		<!-- col-sm-6 end  -->
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
					<div id="budgetAuditTopBox">
						<div class="toolBtn f-r m-b-15  editbtn">
						<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 hidden dispatchBtnChange ">派工</a>
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
         				
					</div>
			    	<div class="clearboth form-box budgetAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="projCostAuditForm" action="projectCostAudit/auditSave">
			    			<input type="hidden" id="projId1" name = "projId" value = "${projId}"/>
			    			<input type="hidden" id="projNo1" name = "projNo"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" />
                    		<input type="hidden" id="mrAuditLevel" name = "mrAuditLevel" value = "${currentLevel}"/>
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
</script>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程造价审核 - 工程管理系统');
    
    $("#projCostForm").toggleEditState();
    $("#projCostForm").styleFit();
    
    $("#projCostAuditForm").toggleEditState();
    $("#projCostAuditForm").styleFit();
   
    $("#scaleTableForm").toggleEditState();
    $("#scaleTableForm").styleFit();
    
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/contract/projectcost-audit-page.js?'+Math.random()).done(function () {
        projectCostAudit.init();
	});

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("projectCostAudit/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
    	if(!$(".auditSaveBtn").is(":hidden")){
    		$("body").cgetPopup({
				title: '提示',
				content: '请先保存造价信息！',
				accept: popClose
	    	});
    		return false;
    	}
    	var val=$('#projCostAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
    		$("#projCostAuditForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".saveBtn"));
    	}
    });
    
    
    var ensureDone=function(){};
    
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.budgetAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#projCostAuditForm input:radio[name="mrResult"]:checked').val();
    		$("#projCostAuditForm").toggleEditState(false); 
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

    /**点击右侧维护区 保存 按钮时*/
    $(".auditSaveBtn").on("click",function(){
    		
    	var confirmcost = $("#confirmTotalCost").val();
    	var budgecost = $("#budgetTotalCost").val();
    	//如果确定造价与工程总造价不同
		if(confirmcost != budgecost && confirmcost != ''){
			//添加变更结果验证
			$("#projCostForm select[id='changeReason']").attr("data-parsley-required",true);
			if($("#deptDivide").val()==$("#customerServiceCenter").val()){
				$("#projCostForm select[id='projCostAuditType']").attr("data-parsley-required",false);
			}else{
				$("#projCostForm select[id='projCostAuditType']").attr("data-parsley-required",true);
			}
		}else{
			$("#projCostForm select[id='changeReason']").attr("data-parsley-required",false);
			$("#projCostForm select[id='projCostAuditType']").attr("data-parsley-required",false);
		}
    	if($("#projCostForm").parsley().isValid()&&$("#scaleTableForm").parsley().isValid()){
    		//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    		var data=$("#projCostForm").serializeJson();
    		if(data.isBack === "1"){
    			var popoptions = {
	       				title: '提示',
	       				content: '您确定退单吗？',
	       				accept: backDone1
	       		};
	       		$("body").cgetPopup(popoptions);
    		}else{
    			var popoptions = {
    	   				title: '提示',
    	   				content: '您确定造价吗？',
    	   				accept: backDone1
    	   		};
    	   		$("body").cgetPopup(popoptions);
    		}
    		
    	}else{
    		//如果未通过验证, 则加载验证UI
        	$("#projCostForm").parsley().validate();
        	$("#scaleTableForm").parsley().validate();
    	}
    }); 
    //去掉用气规模
    var backDone1 = function(){
    	//加遮罩
    	$(".infodetails").loadMask("正在保存...", 1, 0.5);
    	var viewdata=$("#projCostForm").serializeJson();
		$.ajax({
            type: 'POST',
            url: 'projectCost/saveProject',
            contentType: 'application/json;charset=UTF-8',
            dataType:"json",
            data: JSON.stringify(viewdata),
            beforeSend: function () {
	              // 禁用按钮防止重复提交
                 $(".auditSaveBtn").attr({ disabled: "disabled" });
	        },
            success: function (data) {
            	//取消遮罩
            	$(".infodetails").hideMask();	
            	var content = "数据保存成功！";
            	if(data.ret_message === "false"){
            		content = "数据保存失败！";
            	}else if(data.ret_message === "true"){
            		$('#projCostForm').toggleEditState(false);
        			$(".auditSaveBtn").addClass("hidden");
            	}else{
            		alertInfo(data.ret_message);
            	}
            	var myoptions = {
                    	title: "提示信息",
                    	content: content,
                    	accept: popClose,
                    	chide: true,
                    	icon: "fa-check-circle",
                    	newpop:"new"
                }
                $("body").cgetPopup(myoptions);
            },
            complete: function () {
            	//去掉禁用
            	$(".auditSaveBtn").removeAttr("disabled");
            },
            error: function (jqXHR, textStatus, errorThrown) {
            	//取消遮罩
            	$(".infodetails").hideMask();	
                console.warn("造价确认记录保存失败！");
            }
        });
    	//如果通过验证, 则移除验证UI
    	$("#projCostForm").parsley().reset();
    	//$("#scaleTableForm").parsley().reset();
    }
    //变动原因
    $('#changeReason').on("change",function(){
   	 if($(this).val()=='5'){
   		 $(".remark").removeClass("hidden");
   	 }else{
   		 $(".remark").addClass("hidden");
   	 }
    })

$("#projCostAuditType,#reduceGasTimes").on("change",function(){
	var reduceGasTimes = new Number($("#reduceGasTimes").val() || 0);
	if($("#projCostAuditType").val()=="1"){
		$("#confirmTotalCost").val((new Number($("#budgetTotalCost").val()-
		(new Number($("#inspectionCost").val())*reduceGasTimes)-
		new Number($("#unforeseenCost").val()))).toFixed(2));
	}else{
		$("#confirmTotalCost").val($("#budgetTotalCost").val());
	}
});

$("#projCostAuditForm input[name='mrResult']").on("change",function(){
	if($('#projCostAuditForm input:radio[name="mrResult"]:checked').val() == "1"){
		$("#mrAopinion").val("同意");
	}else{
		$("#mrAopinion").val("不同意");
	}
})

</script>
<!-- ================== END PAGE LEVEL JS ================== -->