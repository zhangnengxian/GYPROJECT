<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

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
                <h4 class="panel-title">结算汇签审核信息</h4>
			</div>
            <div class="panel-body">
              	<form class="form-horizontal" id="settlementAuditForm" action="">
		       		<input type="hidden" id="projId1" name="projId"/>
					<div class="form-group col-md-6 ">
						<label class="control-label" for="">工程编号</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" />
						</div>
					</div>
					<div class="form-group col-md-12 ">
						<label class="control-label" for="">工程名称</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName"/>
						</div>
					</div>

					<div class="form-group col-md-12 ">
						<label class="control-label" for="">工程规模</label>
						<div>
							<textarea rows="2" class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes"></textarea>
						</div>
					</div>

					<div class="form-group col-md-6 col-sm-12">
						<label class="control-label" for="">燃气公司</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" />
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

					<div class="form-group col-md-12 ">
						<label class="control-label" for="">分包单位</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"/>
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="control-label" for="">建设范围</label>
						<div>
							<textarea class="form-control field-editable allText budgetMember" name="conContent" id="conContent" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
						</div>
					</div>

					<div class="form-group col-md-6 col-sm-12 public">
						<label class="control-label" for="">承包方式</label>
						<div>
							<select class="form-control input-sm field-editable allText budgetMember" id="contractMethod"  name="contractMethod"  data-parsley-required="true">
								<option value="">--请选择--</option>
								<option value="0">预算包干</option>
								<option value="1">预结算制</option>
								<option value="2">其他</option>
							</select>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12 public">
						<label class="control-label" for="">材料提供</label>
						<div>
							<select class="form-control input-sm field-editable allText budgetMember" id="materialProvide"  name="materialProvide"  data-parsley-required="true">
								<option value="">--请选择--</option>
								<option value="1">甲供主材</option>
								<option value="2">清包工</option>
								<option value="3">包工包料</option>
								<option value="4">其他</option>
							</select>
						</div>
					</div>

					<div class="form-group col-lg-6 col-md-12 col-sm-6 public">
						<label class="control-label" for="budgetCost">预算价</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable cost-i fixed-number text-right"  id="budgetCost" name="budgetCost" data-parsley-maxlength="16"  data-parsley-type="number" value=""/>
							<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 public">
						<label class="control-label" for="sendDeclarationCost">报审价</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable cost-i fixed-number text-right"  id="sendDeclarationCost" name="sendDeclarationCost" data-parsley-maxlength="16"  data-parsley-type="number" value=""/>
							<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
						<label class="control-label" for="endDeclarationCost">审定价</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable cost-i fixed-number text-right"  id="endDeclarationCost" name="endDeclarationCost" data-parsley-maxlength="16"  data-parsley-type="number" value=""/>
							<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						</div>
					</div>
					<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
						<label class="control-label" for="">备注</label>
						<div>
							<textarea class="form-control input-sm field-editable" id="remark" name="remark" data-parsley-maxlength="500" rows="4"></textarea>
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
			    	<div class="clearboth form-box paymentAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="settlementAuditSaveForm" action="auditSettlementJonintlySign/auditSave">
			    			<input type="hidden"id="projId" name = "projId" value = "${projId}"/>
							<input type="hidden" class="menuId" name = "menuId" value = ""/>
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
						           <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime"  data-parsley-maxlength="100" >
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
    
    $("#settlementAuditForm").toggleEditState();
    $("#settlementAuditForm").styleFit();

    $("#settlementAuditSaveForm").toggleEditState();
    $("#settlementAuditSaveForm").styleFit();
    
    
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/settlement/settlement-audit-page.js?'+Math.random()).done(function () {
    	auditHistory.init();
	});

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("auditSettlementJonintlySign/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
        $(".menuId").val(getStepId());
    	var val=$('#settlementAuditSaveForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
    		$("#settlementAuditSaveForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".saveBtn"));
    	}
    });
    
    
    var ensureDone=function(){};
    
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.paymentAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#settlementAuditSaveForm input:radio[name="mrResult"]:checked').val();
    		$("#settlementAuditSaveForm").toggleEditState(false);
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
    	var projId=$("#projId").val();
    	var data={"id":projId};
    	var url = 'settlementJonintlySign/viewSettlementJonintlySign';
		var f = $("#settlementAuditForm");
		 $.ajax({
	         type: 'POST',
	         url: url,
	         data: data,
	         dataType: 'json',
	         success: function (data) {
	        	 var selects = f.find('select[disabled]');
	             selects.removeAttr("disabled");
	             $("input:radio").attr("disabled",false);
	             //表单反序列化填充值
	             f.deserialize(fixNull(data));
	             selects.attr("disabled","disabled");
                 //applyProjectTableInit();
	         },
	         error: function (jqXHR, textStatus, errorThrown) {
	             console.warn("cgetDetail() -> 详情查询失败");
	         }
	     });
    }();



    var trListSelectedBack=function(v, i, index, t, json){
    }

    
    
	
		
		
</script>
<!-- ================== END PAGE LEVEL JS ================== -->