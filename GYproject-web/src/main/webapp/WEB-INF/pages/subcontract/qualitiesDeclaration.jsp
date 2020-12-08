<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
		<div class="col-sm-6 col-xs-12">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">待施工预算工程列表</h4>
                </div>
                <div class="panel-body">
                	<input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
                	<input type="hidden" id="customerServiceCenter" name="customerServiceCenter" value="${customerServiceCenter }"/>
					<input type="hidden" id="stepId" name="stepId" value="${stepId }">
					<input type="hidden" class="stepIds" value="${stepIds }">
					<input type="hidden" id="waitHandleProjNo" name="projNo"  value="${projNo }"/>
					<!-- <input type="hidden" class="stepIds" value="160201,1603"> -->
					<input type="hidden" id="curStepId" name="curStepId" value="${curStepId }">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>
                    <table id="qualitiesDeclarationTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th>工程Id</th>
                            	<th>工程编号</th>
                                <th>工程名称</th>
                                <th>状态</th>
                                <th>剩余时限(天)</th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                      
                    </table>
            	</div>
        	</div>
        </div>
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" >
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <h4 class="panel-title">工程量</h4>
			    </div>
			    <div class="panel-body saveHiddenBox">
					<div class="toolBtn f-r p-b-10  editbtn hidden">
				    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 acceptSaveBtn" >保存</a>
						<a href="javascript:;" class="btn btn-info btn-sm m-l-5 pushButton entBtn" >推送</a>
				        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
					</div>
				    <div class="clearboth form-box">
				     <input type="hidden" id="sysDate" name="" value="${sysDate }" >
				    	<form class="form-horizontal" id="planEstablishDetailform" action="">
				    		<input type="hidden" class="form-control field-editable" id="projId" name="projId" value=""/>
				    		<input type="hidden" class="form-control field-editable" id="sbId" name="sbId" value=""/>
				    		<input type="hidden" id="flag" name="flag" value="" >
				    		<input type="hidden" id="stepId" name="stepId" value="" />
				    		<input type="hidden" id="operaterId" name="operaterId" />
				    		<div class="form-group  col-md-6 ">
						        <label class="control-label" for="projNo">工程编号</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value=""/>
						        </div>
						    </div>
						    <div class="form-group col-md-6">
								<label class="control-label" for="costType">造价类型</label>
						        <div>
							        <select class="form-control input-sm field-editable" id="costType"  name="costType"  data-parsley-required="true">
								        <c:forEach var="enums" items="${costType}">
											<option value="${enums.value}" >${enums.message}</option>
								        </c:forEach>
							        </select>
						        </div>
							</div>
				    		<div class="form-group col-md-12 ">
						        <label class="control-label" for="projName">工程名称</label>
						        <div> 
						            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value=""/>
						        </div>
						    </div>
						    <div class="form-group col-md-12">
						        <label class="control-label" for="projAddr">工程地点</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
						        </div>
						    </div>
						    <div class="form-group col-md-12">
					            <label class="control-label" for="projScaleDes">工程规模</label>
					            <div>
					                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2"></textarea>
					            </div>
				            </div>
						     <div class="form-group col-md-12 ">
						        <label class="control-label" for="cuName">施工单位</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="cuLegalRepresent">负责人</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="cuLegalRepresent" name="cuLegalRepresent"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="cuPhone">联系方式</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="cuPhone" name="cuPhone"/>
						        </div>
						    </div>
						     <div class="form-group col-md-6">
						        <label class="control-label" >计划下达日期</label>
						        <div>
						        	<input type="text" class="form-control field-editable input-sm datepicker-default"  id="sbDate" name="sbDate"  data-parsley-required="true"/>
						        </div>
						    </div>
						     <div class="form-group col-md-6 ">
						        <label class="control-label" >预算日期</label>
						        <div>
						        	<input type="text" class="form-control field-editable input-sm datepicker-default"  id="operateDate" name="operateDate"  data-parsley-required="true"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="uploadFlag">上传预算书</label>
						        <div>
						        	<label>
						            	<input type="radio" class="field-editable" name="uploadFlag" value="1">已上传
						            </label>
						            <label>
						            	<input type="radio" class="field-editable" name="uploadFlag" value="0" checked>未上传
						            </label>
						        </div>
						    </div>
						    <div class="form-group col-md-6 contractAmount clearboth">
						    	<label class="control-label" for="contractAmount">合同价款</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="contractAmount" name="contractAmount" data-parsley-required="true"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 hidden DETAILED_LIST">
						    	<label class="control-label" for="totalAmount">合计</label>
						        <div>
						        	<input type="text" class="DETAILED_LIST form-control input-sm field-not-editable fixed-number text-right" id="totalAmount" name="totalAmount"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 hidden DETAILED_LIST">
						    	<label class="control-label" for="mainMaterialAmountList">主材费</label>
						        <div>
						        	<input type="text" class="DETAILED_LIST form-control input-sm field-editable fixed-number text-right" id="mainMaterialAmountList" name="mainMaterialAmountList" data-parsley-type="number"data-parsley-maxlength="16"/>
						        </div>
						    </div>
						     <div class="form-group col-md-6 hidden DETAILED_LIST">
						    	<label class="control-label" for="totalCost">总造价</label>
						        <div>
						        	<input type="text" class="DETAILED_LIST form-control input-sm field-editable fixed-number text-right" id="totalCost" name="totalCost" data-parsley-type="number"data-parsley-maxlength="16" data-parsley-required="true"/>
						        </div>
						    </div>
						    
						    <div class="form-group col-md-6 hidden QUOTA">
						    	<label class="control-label" for="mainMaterialAmount">主材费</label>
						        <div>
						        	<input type="text" class="QUOTA form-control input-sm field-editable fixed-number text-right" id="mainMaterialAmount" name="mainMaterialAmount" data-parsley-type="number" data-parsley-maxlength="16"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6">
						    	<label class="control-label" for="laborCost">工程费</label>
						        <div>
						        	<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="laborCost" name="laborCost" data-parsley-type="number" data-parsley-maxlength="16"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6">
						    	<label class="control-label" for="auxiliaryMaterialAmount">破路费</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="auxiliaryMaterialAmount" name="auxiliaryMaterialAmount" data-parsley-type="number" data-parsley-maxlength="16"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6">
						    	<label class="control-label" for="managementCost">协调费</label>
						        <div>
						        	<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="managementCost" name="managementCost" data-parsley-type="number" data-parsley-maxlength="16"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6">
						    	<label class="control-label" for="profit">其他费用</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="profit" name="profit" data-parsley-type="number" data-parsley-maxlength="16"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 hidden QUOTA">
						    	<label class="control-label" for="totalQuota">总造价</label>
						        <div>
						        	<input type="text" class="QUOTA form-control input-sm field-editable fixed-number text-right" id="totalQuota" name="totalQuota" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-required="true"/>
						        </div>
						    </div>
						     <div class="form-group col-md-6 clearboth allSign budgetMember ">
								<label class="control-label signature-tool sign-require" for="compilerSign">编制人签字</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="compilerSign" name="compilerSign" data-parsley-required="true">
									<input type="hidden" id="compilerSign_postType" name="compilerSign_postType" value="" >
									<img class="" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
							</div>
							 <div class="form-group col-md-6 ">
						        <label class="control-label" for="operaterr">施工预算员</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="operater" name="operater"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 clearboth allSign construction minister">
								<label class="control-label signature-tool " for="cuAudit">审核人</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="cuAudit" name="cuAudit" data-parsley-required="true">
									<input type="hidden" id="cuAudit_postType" name="cuAudit_postType" value="" >
									<img class="cuAudit" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
							</div>
							<div class="form-group col-md-6 allSign cuPm viceGeneralManager">
								<label class="control-label signature-tool " for="cuPrincipal">负责人</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="cuPrincipal" name="cuPrincipal" data-parsley-required="true">
									<input type="hidden" id="cuPrincipal_postType" name="cuPrincipal_postType" value="" >
									<img class="cuPrincipal" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
							</div>
						    <div class="form-group col-md-12">
					            <label class="control-label" for="remark">备注</label>
					            <div>
					                <textarea class="form-control field-editable" name ="remark" id="remark" rows="2" data-parsley-maxlength="200"></textarea>
					            </div>
				            </div>
						</form>
				    </div>
				    <div class="DETAILED_LIST" id="qualities_declaration_panel_box">
			    	</div>
				    <div class="hidden">
				    	<form method="post" action="qualitiesDeclaration/exportExcel" id="subQualitiesForm">
				    		<input type="hidden" class="form-control field-editable" id="sbId1" name="sbId" value=""/>
				    		<input type="hidden" class="form-control field-editable" id="projId1" name="projId" value=""/>
				    	</form>
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
    App.setPageTitle('工程量申报 - 工程管理系统');
    $("#planEstablishDetailform").toggleEditState(false).styleFit();
    $("#costType").change(function(){
   		if($("#costType").val()=='1'){
   			$(".QUOTA").addClass("hidden");
   			$(".QUOTA").val('');
   			$(".DETAILED_LIST").removeClass("hidden");
   			
   		}else{
   			$(".DETAILED_LIST").val('');
   			$(".QUOTA").removeClass("hidden");
   			$(".DETAILED_LIST").addClass("hidden");
   	    	$("#totalQuota").val((new Number($("#mainMaterialAmount").val())+new Number($("#profit").val())+new Number($("#auxiliaryMaterialAmount").val())+new Number($("#managementCost").val())+new Number($("#laborCost").val())).toFixed(2));	
   		}
    });
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $("#totalAmount").on("change",function(){
    	$("#totalCost").val($("#totalAmount").val());
    	
    });
    $("#mainMaterialAmount,#profit,#laborCost,#auxiliaryMaterialAmount,#managementCost").on("change",function(){
    	if($("#costType").val()=='2'){
    		$("#totalQuota").val((new Number($("#mainMaterialAmount").val())+new Number($("#profit").val())+new Number($("#auxiliaryMaterialAmount").val())+new Number($("#managementCost").val())+new Number($("#laborCost").val())).toFixed(2));	
    	}
    });
    $.getScript('projectjs/subcontract/qualities-declaration.js?v='+Math.random()).done(function () {
        $.getScript('assets/plugins/jstree/dist/jstree.min.js').done(function() {
            qualitiesDeclaration.init();
        });
	}); 
    
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
        var signatures = $("#signBtn_1");
        var signBtn_2 = $("#signBtn_2");
        var signBtn_3 = $("#signBtn_3");
        var signBtn_4 = $("#signBtn_4");
        signatures.handleSignature(false);
        signBtn_2.handleSignature(false);
        signBtn_3.handleSignature(false);
        signBtn_4.handleSignature(false);
    });
    
   /*  $.getScript('assets/plugins/jstree/dist/jstree.min.js').done(function() {
        qualitiesDeclaration.init(); 
    }); */
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->