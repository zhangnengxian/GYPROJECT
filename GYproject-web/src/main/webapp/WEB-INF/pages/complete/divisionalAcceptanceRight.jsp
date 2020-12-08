<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5  temporarySaveBtn" >保存</a>
		<a href="javascript:;" class="btn btn-info btn-sm m-l-5 pushButton entBtn" >推送</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
		<input type="hidden" id="sysDate" value="${sysDate}"/>
		<input type="hidden" id="post" value="${post}">
    	<form class="form-horizontal" id="diviaionalAcceptanceDetailform" data-parsley-validate="true" action="">
       		<input type="hidden" id="projId" name="projId"/>
       		<input type="hidden" id="daId" name="daId"/>
       		<input type="hidden" id="daaId" name="daaId"/>
       		<input type="hidden" id="flag" name="flag"/>
       		 <input type="hidden" name="version"/>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label">工程规模</label>
		        <div>
		        	<textarea class="form-control input-sm field-not-editable" name="projScaleDes" rows="" cols="" data-parsley-maxlength="200"></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="suJgj">现场监理</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="suRepresentative" name="suRepresentative"  data-parsley-maxlength="100" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="duDesigner">设计代表</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="duDesigner" name="duDesigner"  data-parsley-maxlength="100" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="managementQae1">施工员</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="managementQae1" name="managementQae1"  data-parsley-maxlength="100" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="cuPm">项目经理</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="cuPm" name="cuPm"  data-parsley-maxlength="100" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="builder">现场代表</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="builder" name="builder"  data-parsley-maxlength="100" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="managementWacf">材料员</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="managementWacf" name="managementWacf"  data-parsley-maxlength="100" />
		        </div>
		    </div>
		    <!--！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！  -->
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
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
		    
			<div class="form-group col-md-6 ">
		    	<label class="control-label" for="conNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="conNo" name="conNo"   value=""/>
		        </div>
		    </div>
			<div class="form-group col-md-6 ">
		    	<label class="control-label" for="scNo">协议编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo"   value=""/>
		        </div>
		    </div>
			<div class="form-group col-md-6 ">
		    	<label class="control-label" for="cuName">施工单位</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"   value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 clearboth">
				<label class="control-label">监理报告</label>
				<div>
					<label><input type="radio" name="suReport" value="1" checked> 有</label>
					<label><input type="radio" name="suReport" value="2" > 无</label>
				</div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ">
				<label class="control-label">试压报告</label>
				<div>
					<label><input type="radio" name="strengthTest" value="1" checked> 有</label>
					<label><input type="radio" name="strengthTest" value="2" > 无</label>
				</div>
		    </div>
		    <%--<div class="form-group col-md-6 col-sm-12 ">--%>
				<%--<label class="control-label">工作联系函</label>--%>
				<%--<div>--%>
					<%--<label><input type="radio" name="workLetter" value="1" checked> 有</label>--%>
					<%--<label><input type="radio" name="workLetter" value="2" > 无</label>--%>
				<%--</div>--%>
		    <%--</div>--%>
			<div class="form-group col-md-6 col-sm-12 ">
				<label class="control-label">吹扫记录</label>
				<div>
					<label><input type="radio" name="purgeRecord" value="1" checked> 有</label>
					<label><input type="radio" name="purgeRecord" value="2" > 无</label>
				</div>
			</div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label">施工自检表</label>
				<div>
					<label><input type="radio" name="selfCheckTable" value="1" checked> 有</label>
					<label><input type="radio" name="selfCheckTable" value="2" > 无</label>
				</div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ">
				<label class="control-label">分部验收表</label>
				<div>
					<label><input type="radio" name="acceptanceTable" value="1" checked> 有</label>
					<label><input type="radio" name="acceptanceTable" value="2" > 无</label>
				</div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ">
				<label class="control-label">三份竣工图</label>
				<div>
					<label><input type="radio" name="completionDrawing" value="1" checked> 有</label>
					<label><input type="radio" name="completionDrawing" value="2" > 无</label>
				</div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ">
				<label class="control-label">是否整体完工</label>
				<div>
					<label><input type="radio" name="isWholeComplete" value="1" checked> 是</label>
					<label><input type="radio" name="isWholeComplete" value="2" > 否</label>
				</div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label">验收时间</label>
		        <div>
		            <input type="text" class="form-control input-sm  datepicker-default field-not-editable allSign technician"  id="acceptanceDate" name="acceptanceDate" data-parsley-required="true" />
		        </div>
		    </div>
			<div class="form-group col-sm-12">
				<label class="control-label" for="thisAcceptanceContent">本次验收内容</label>
				<div>
					<textarea class="form-control field-not-editable allSign technician projectLeader builder" name="thisAcceptanceContent" id="thisAcceptanceContent" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
				</div>
			</div>
		    <div class="form-group col-sm-12 craftWork">
               	<label class="control-label" for="transmissionOpinion">输配公司</label>
                <div> 
                   	<textarea class="form-control field-editable allText processTechnician  technician" name="transmissionOpinion" id="transmissionOpinion" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
	   		</div>
	   		<div class="form-group col-md-6 col-sm-12 civilType clearboth diviContent">
		        <label class="control-label" for="">验收情况</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable  allText processTechnician technician" name="trAcceptanceSituat" value="1" />整改
		            </label>
		            <label>
		            	<input type="radio" class="field-editable  allText processTechnician technician" name="trAcceptanceSituat" value="0" checked/>合格
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-sm-12 hidden trAcceptanceSituat trRectificationOpinion civilType">
		        <label class="control-label" for="trRectificationOpinion">整改意见</label>
		        <div>
		           <textarea  class=" form-control field-editable  allText processTechnician technician" id="trRectificationOpinion"  name="trRectificationOpinion" rows="2" cols="" data-parsley-maxlength="500" data-parsley-required="true" ></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6  hidden trAcceptanceSituat trRectificationDate civilType">
		        <label class="control-label" for="">整改日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm datepicker-default field-editable allText processTechnician technician timestamp"  id="trRectificationDate"  name="trRectificationDate" data-parsley-required="true" >
		        </div>
		    </div>		    
	   		<div class="form-group col-sm-12 craftWork">
               	<label class="control-label" for="customerServiceOpinion">客服中心</label>
                <div> 
                   	<textarea class="form-control field-editable allText inspector technician" name="customerServiceOpinion" id="customerServiceOpinion" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
	   		</div>
	   		<div class="form-group col-md-6 col-sm-12 civilType clearboth diviContent">
		        <label class="control-label" for="">验收情况</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable allText inspector technician" name="cuAcceptanceSituat" value="1" />整改
		            </label>
		            <label>
		            	<input type="radio" class="field-editable allText inspector technician" name="cuAcceptanceSituat" value="0" checked/>合格
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-sm-12 hidden cuAcceptanceSituat cuRectificationOpinion civilType">
		        <label class="control-label" for="cuRectificationOpinion">整改意见</label>
		        <div>
		           <textarea  class=" form-control field-editable allText inspector technician" id="cuRectificationOpinion"  name="cuRectificationOpinion" rows="2" cols="" data-parsley-maxlength="500" data-parsley-required="true" ></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6  hidden cuAcceptanceSituat cuRectificationDate civilType">
		        <label class="control-label" for="">整改日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm datepicker-default field-editable allText inspector technician timestamp"  id="cuRectificationDate"  name="cuRectificationDate" data-parsley-required="true" >
		        </div>
		    </div>
	   		<div class="form-group col-sm-12 craftWork">
               	<label class="control-label" for="metrologyOfficeOpinion">计量所</label>
                <div> 
                   	<textarea class="form-control field-editable allText productionStatisticians technician" name="metrologyOfficeOpinion" id="metrologyOfficeOpinion" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
	   		</div>
	   		<div class="form-group col-md-6 col-sm-12 civilType clearboth diviContent">
		        <label class="control-label" for="">验收情况</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable allText productionStatisticians technician" name="meAcceptanceSituat" value="1" />整改
		            </label>
		            <label>
		            	<input type="radio" class="field-editable allText productionStatisticians technician" name="meAcceptanceSituat" value="0" checked/>合格
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-sm-12 hidden meAcceptanceSituat meRectificationOpinion civilType">
		        <label class="control-label" for="meRectificationOpinion">整改意见</label>
		        <div>
		           <textarea  class=" form-control field-editable allText productionStatisticians technician" id="meRectificationOpinion"  name="meRectificationOpinion" rows="2" cols="" data-parsley-maxlength="500" data-parsley-required="true" ></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6  hidden meAcceptanceSituat meRectificationDate civilType">
		        <label class="control-label" for="">整改日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm datepicker-default field-editable allText productionStatisticians technician timestamp"  id="meRectificationDate"  name="meRectificationDate" data-parsley-required="true" >
		        </div>
		    </div>
	   		<div class="form-group col-sm-12 craftWork">
               	<label class="control-label" for="remark">备注</label>
                <div> 
                   	<textarea class="form-control field-editable" name="remark" id="remark" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
	   		</div>
		  	<div class="form-group col-md-6 col-sm-12 allSign projectLeader builder">
				<label class="control-label signature-tool sign-require" for="cuSpdPrincipal">现场代表</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="cuSpdPrincipal" name="cuSpdPrincipal" value="">
					<input type="hidden" class="signPost" id="cuSpdParticipant_postType" name="cuSpdParticipant_postType" value="${cuSpdPrincipal }" >
					<img class="ongcDeputy" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 allSign designer">
				<label class="control-label signature-tool sign-require" for="duDeputy">设计人员</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="duDeputy" name="duDeputy" value="">
					<input type="hidden" class="signPost" id="duDeputy_postType" name="duDeputy_postType" value="${duDeputy }" >
					<img class="duDeputy" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
		     <div class="form-group col-md-6 col-sm-12 allSign suJgj">
				<label class="control-label signature-tool sign-require" for="suFieldJgj">现场监理</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="suFieldJgj" name="suFieldJgj" value="">
					<input type="hidden" class="signPost" id="suFieldJgj_postType" name="suFieldJgj_postType" value="${suFieldJgj }" >
					<img class="suFieldJgj" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 allSign construction">
				<label class="control-label signature-tool sign-require" for="managementQae">施工员</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="managementQae" name="managementQae" value="">
					<input type="hidden" class="signPost" id="managementQae_postType" name="managementQae_postType" value="${managementQae }" >
					<img class="managementQae" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 allSign technician">
				<label class="control-label signature-tool sign-require" for="organizationMan">组织人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="organizationMan" name="organizationMan" value="">
					<input type="hidden"class="signPost"  id="organizationMan_postType" name="organizationMan_postType" value="${organizationMan }" >
					<img class="organizationMan" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
		    <div class="form-group col-md-6 allSign productionStatisticians">
				<label class="control-label signature-tool" for="measurementSign">计量所</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="measurementSign" name="measurementSign" value="">
					<input type="hidden" class="signPost" id="measurementSign_postType" name="measurementSign_postType" value="${measurementSign }" >
					<img class="fieldPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-md-6 allSign processTechnician">
				<label class="control-label signature-tool" for="transCompanySign">输配公司</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="transCompanySign" name="transCompanySign" value="">
					<input type="hidden"class="signPost"  id="transCompanySign_postType" name="transCompanySign_postType" value="${transCompanySign }" >
					<img class="fieldPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-md-6 allSign inspector">
				<label class="control-label signature-tool" for="custCenterSign">客服中心</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="custCenterSign" name="custCenterSign" value="">
					<input type="hidden" class="signPost" id="custCenterSign_postType" name="custCenterSign_postType" value="${custCenterSign }" >
					<img class="fieldPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
		</form>
    </div>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $('.infodetails').hideMask();
    $('.editbtn').addClass('hidden');
    //表单样式适应
    $('#diviaionalAcceptanceDetailform').toggleEditState(false).styleFit();
  	//查右侧工程详述
    trSData.t && trSData.t.cgetDetail('diviaionalAcceptanceDetailform','divisionalAcceptance/viewDivisionalAcceptance','',queryDetailBack);
  	
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5");
    	signatures.handleSignature();        	    	
    }); 
  	
  	
   
    //放弃
    $(".cancelBtn").off().on("click",function(){
    	$("#divisionalAcceptanceTable").cgetData();  //列表重新加载
		$("#diviaionalAcceptanceDetailform").toggleEditState(false);
		$("input[name='trAcceptanceSituat'][value='0']").attr("checked","checked");
		$("input[name='trAcceptanceSituat'][value='0']").change();
		$("input[name='cuAcceptanceSituat'][value='0']").attr("checked","checked");
		$("input[name='cuAcceptanceSituat'][value='0']").change();
		$("input[name='meAcceptanceSituat'][value='0']").attr("checked","checked");
		$("input[name='meAcceptanceSituat'][value='0']").change();
	   	//移除验证
	   	$("#diviaionalAcceptanceDetailform").parsley().reset();
    });
    
    
    
    /**点击右侧维护区 保存 按钮时*/
    $(".temporarySaveBtn").on("click",function(){
    	if($("#diviaionalAcceptanceDetailform").parsley().isValid()){
    		//加遮罩
    		$(".infodetails").loadMask("正在保存...", 1, 0.5);
    		$("#flag").val("0");
    		//json字符串
        	var data=$("#diviaionalAcceptanceDetailform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'divisionalAcceptance/saveDivisionalAcceptance',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "true"){
                		$("#diviaionalAcceptanceDetailform").formReset();
                		$("#divisionalAcceptanceTable").cgetData();  //列表重新加载
               		    $("#diviaionalAcceptanceDetailform").toggleEditState(false);
                	}else if(data === "already"){
                   		content = "此信息已被修改，请刷新页面！";
                   	}
                	var myoptions = {
                        	title: "提示信息",
                        	content: content,
                        	accept: sureClose,
                        	chide: true,
                        	icon: "fa-check-circle"
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                    console.warn("分部验收保存失败！");
                }
            });
    		//如果通过验证, 则移除验证UI
        	$("#diviaionalAcceptanceDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#diviaionalAcceptanceDetailform").parsley().validate();
        }
    	 
    });
    
    //点击推送
    $(".entBtn").off().on("click",function(){
        var radios = $(".diviContent input:radio:checked");
        for(var i=0;i<radios.length;i++){
            if(radios[i].value=="1"){
                $("body").cgetPopup({
                    title: "提示信息",
                    content: "验收未通过!",
                    accept: popClose,
                    chide: true,
                    icon: "fa-warning"
                });
                return;
			}
        }
    	if($("#diviaionalAcceptanceDetailform").parsley().isValid()){
        	//验证必签签字是否已签
	        var signtools =$('#diviaionalAcceptanceDetailform').find(".signature-tool.sign-require"),
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
		            	newpop: 'second'
		            });
	        	return false;
	        }
	        $("#flag").val("1");
        	//json字符串
        	var data=$("#diviaionalAcceptanceDetailform").serializeJson();
        	//加遮罩
        	$(".infodetails").loadMask("正在保存...", 1, 0.5);
        	$.ajax({
                type: 'POST',
                url: 'divisionalAcceptance/saveDivisionalAcceptance',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "true"){
                		$("#diviaionalAcceptanceDetailform").formReset();
                		$("#divisionalAcceptanceTable").cgetData();  //列表重新加载
                		$(".editbtn").addClass("hidden");
                	}else if(data === "already"){
                   		content = "此信息已被修改，请刷新页面！";
                   	}
                	var myoptions = {
                        	title: "提示信息",
                        	content: content,
                        	accept: sureClose,
                        	chide: true,
                        	icon: "fa-check-circle",
                        	newpop: 'new'
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                    console.warn("分部验收记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#diviaionalAcceptanceDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#diviaionalAcceptanceDetailform").parsley().validate();
        }
    });
 
    
    //输配验收情况chang事件
    $('input:radio[name="trAcceptanceSituat"]').change(function(){
    	showTr();
    });
    var showTr = function(){
    	if($('input:radio[name="trAcceptanceSituat"]:checked').val()=='1'){//整改
    		$(".trAcceptanceSituat").removeClass("hidden");
    	}else{
    		$(".trAcceptanceSituat").addClass("hidden");
    	
    	}
    	//表单样式适应
       	$("#surveyDetailform").styleFit();
    }
 
    //客服验收情况chang事件
    $('input:radio[name="cuAcceptanceSituat"]').change(function(){
    	showCu();
    });
    var showCu = function(){
    	if($('input:radio[name="cuAcceptanceSituat"]:checked').val()=='1'){//整改
    		$(".cuAcceptanceSituat").removeClass("hidden");
    	}else{
    		$(".cuAcceptanceSituat").addClass("hidden");
    	
    	}
    	//表单样式适应
       	$("#surveyDetailform").styleFit();
    }
    
    //计量所验收情况chang事件
    $('input:radio[name="meAcceptanceSituat"]').change(function(){
    	showMe();
    });
    var showMe = function(){
    	if($('input:radio[name="meAcceptanceSituat"]:checked').val()=='1'){//整改
    		$(".meAcceptanceSituat").removeClass("hidden");
    	}else{
    		$(".meAcceptanceSituat").addClass("hidden");
    	
    	}
    	//表单样式适应
       	$("#surveyDetailform").styleFit();
    }  
    
    var sureClose=function(){
		var cwId=$("#daId").val();
		$.ajax({
			type:'post',
			url:'divisionalAcceptance/saveSignNotice',
			contentType: "application/json;charset=UTF-8",
	        data: cwId,
	        success:function(data){
	        	console.info(data);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            console.warn("数据保存失败！");
	        }
		})
	}
</script>
<!-- ================== END PAGE LEVEL JS ================== -->