<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 saveBtn" >保存</a>
    	  <a href="javascript:;" class="btn btn-info  btn-sm m-l-5 pushBtn" >推送</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<input type="hidden" id="loginPost" name="loginPost" value="${loginPost }"/>
    	<input type="hidden" id="treasurer" name="treasurer" value="${treasurer }"/>
    	<form class="form-horizontal" id="turnFixedApplyForm" data-parsley-validate="true" action="">
    		<input type="hidden" id="projId" name="projId" />
    		<input type="hidden" id="tfaId" name="tfaId" />
    		<input type="hidden" id="corpId" name="corpId" />
    		<input type="hidden" id="flag" name="flag" />
    		<input type="hidden" id="version" name="version" />
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" value="" />
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12">
		        <label class="control-label" for="">工程名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-12 col-sm-12">
		        <label class="control-label" for="">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
		        </div>
		    </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName" />
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
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">施工单位</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">施工合同号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo"  value=""/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6">
		        <label class="control-label" for="tfaDate">申请日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default allText construction" id="tfaDate"  name="tfaDate" data-parsley-required="true" value="" >
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="projTotalCost">工程总值</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable fixed-number text-right allText treasurer"  data-parsley-type="number" id="projTotalCost" name="projTotalCost"   value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="projCost">工程款</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="projCost" name="projCost"   value=""/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="brokenCost">破路费</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" data-parsley-type="number" id="brokenCost" name="brokenCost"   value=""/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="materialCost">材料费</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" data-parsley-type="number" id="materialCost" name="materialCost"   value=""/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="materialCost">工程检测费</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable fixed-number text-right allText treasurer" data-parsley-type="number" id="inspectionCost" name="inspectionCost"   value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="interest">利息</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable fixed-number text-right allText treasurer" data-parsley-type="number" id="interest" name="interest"   value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="constructionCost">建管费</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable fixed-number text-right allText treasurer" data-parsley-type="number" id="constructionCost" name="constructionCost"   value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="otherCost">其他费用</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable fixed-number text-right allText treasurer" data-parsley-type="number" id="otherCost" name="otherCost"   value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="materialQuality">管道材质</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable allText construction"  id="materialQuality" name="materialQuality"   value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="diameter">管径大小</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable allText construction"  id="diameter" name="diameter"   value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="thickness">壁厚</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable allText construction"  id="thickness" name="thickness"   value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="length">长度</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable allText construction"  id="length" name="length"   value=""/>
		        </div>
		    </div>
			<div class="form-group col-md-12 col-sm-12">
		        <label class="control-label" for="remark">备注</label>
		    	<div>
		    		<textarea  class="form-control input-sm field-editable allText construction"  id="remark" name="remark"   rows="3"></textarea>
		        </div>
		    </div>
		     <div class="form-group col-md-6 clearboth allSign treasurer">
				<label class="control-label signature-tool sign-require" for="treasurer">财务部</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="treasurer" name="treasurer" data-parsley-required="true">
					<input type="hidden" id="treasurert_postType" name="treasurer_postType" value="" >
					<img class="treasurer" alt="" src="" style="height: 30px">
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
	$(".infodetails").hideMask();
    //参数传false时，表单不可编辑
    $("#turnFixedApplyForm").toggleEditState(false).styleFit();
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('turnFixedApplyForm','turnFixedApply/viewTurnFixedApply','',scaleDetailRefresh);
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
        var signatures = $("#signBtn_1");
        signatures.handleSignature(false);
    });
    //点击保存
    $(".saveBtn").off("click").on("click",function(){
    	$("#flag").val('0');
    	saveTurnFixed();
    }); 
    //点击推送
    $(".pushBtn").off("click").on("click",function(){
    	$("#flag").val('1');
        //验证必签签字是否已签
        var signtools = $("#turnFixedApplyForm").find(".signature-tool.sign-require"),
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
        	if(_inApk){
        		navigator.notification.alert('请完成签字！', null, '提示信息', '确定');
        	}else{
	        	$("body").cgetPopup({
	            	title: "提示信息",
	            	content: "请完成签字!",
	            	accept: popClose,
	            	chide: true,
	            	icon: "fa-warning",
	            	newpop: 'new'
	            });
        	}
        	return false;
        }
    	saveTurnFixed();
    }); 
    var saveTurnFixed = function(){
        if($("#turnFixedApplyForm").parsley().isValid()){
        	//加遮罩
        	$(".infodetails").loadMask("正在保存...", 1, 0.5);
        	var data=$("#turnFixedApplyForm").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'turnFixedApply/saveTurnFixedApply',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	//取消遮罩
                	$(".infodetails").hideMask();
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "already"){
                        content = "此信息已被修改，请刷新页面！";
                    }else if(data === "true"){
                		$("#turnFixedApplyForm input, #turnFixedApplyForm textarea").not(":radio, :checkbox").val('');
                		$("#turnFixedApplyTable").cgetData(true,turnFixedApplyTableCallBack);  //列表重新加载
                	}
                	var myoptions = {
                        title: "提示信息",
                        content: content,
                        accept: popClose,
                        chide: true,
                        icon: "fa-check-circle",
                    	newpop: 'new'
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {

                	//取消遮罩
                	$(".infodetails").hideMask();
                    console.warn("转固申请操作区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#turnFixedApplyForm").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#turnFixedApplyForm").parsley().validate();
        }
    }
    //点击放弃
    $(".cancelBtn").off().on("click",function(){
    	$("#turnFixedApplyForm input,#turnFixedApplyForm textarea").val('');
    	$('#turnFixedApplyTable').cgetData(true,turnFixedApplyTableCallBack);
    })
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->