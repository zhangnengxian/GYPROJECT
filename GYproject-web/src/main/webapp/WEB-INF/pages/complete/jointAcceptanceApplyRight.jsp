<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 saveBtn" >保存</a>
    	 <a href="javascript:;" class="btn btn-info  btn-sm m-l-5 pushBtn" >推送</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	
    <div class="clearboth form-box">
		<input type="hidden" id="sysDate" value="${sysDate}"/>
    	<form class="form-horizontal" id="jointAcceptanceApplyForm" action="">
    		<!-- <button type="reset" class="hidden" id="reset"/> -->
    		<input type="hidden" id="projId" name="projId" />
    		<input type="hidden" id="jaId" name="jaId" />
    		<input type="hidden" id="flag" name="flag" />
    		<input type="hidden"  id="caiIds" name="caiIds"/>
    		<input type="hidden"  id="applyerId" name="applyerId"  value=""/>
    		<input type="hidden"  id="auditState" name="auditState"  value=""/>
    		<input type="hidden"  id="version" name="version"  value=""/>
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50" value="" />
		        </div>
		    </div>
		    
		    <div class="form-group col-md-12 col-sm-12">
		        <label class="control-label" for="">工程名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-12 col-sm-12">
		        <label class="control-label" for="">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		            <textarea  class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes" ></textarea>
		        </div>
		    </div>
		   	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  />
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
		    
			<div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>试压记录</h4></div>
		    <div class="form-group col-md-6 col-sm-12 ">
		    	<!-- 新加字段 -->
				<label class="control-label">试压记录</label>
				<div>
					<label><input class="field-editable" type="radio" name="isStrenthTest" value="1"  > 有</label>
					<label><input class="field-editable" type="radio" name="isStrenthTest" value="2" > 无</label>
				</div>
		    </div>
		    <div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>竣工资料</h4></div>
		    <div class="form-group col-md-6 col-sm-12 ">
		    	<!-- 新加字段 -->
				<label class="control-label">竣工资料</label>
				<div>
					<label><input class="field-editable" type="radio" name="isCompleteReport" value="1" > 有</label>
					<label><input class="field-editable" type="radio" name="isCompleteReport" value="2"> 无</label>
				</div>
		    </div>

			<div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>预验收记录</h4></div>
			<div class="form-group col-md-6 col-sm-12 ">
				<!-- 新加字段 -->
				<label class="control-label">预验收记录</label>
				<div>
					<label><input class="field-editable" type="radio" name="isPreInspection" value="1" > 有</label>
					<label><input class="field-editable" type="radio" name="isPreInspection" value="2" > 无</label>
				</div>
			</div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label">申请时间</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable datepicker-default" id="applyDate" name="applyDate"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label">计划验收日期</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable datepicker-default" id="planAcceptDate" name="planAcceptDate"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label">申请人</label>
		        <div>
		        	
		        	<input type="text" class="form-control input-sm field-not-editable datepicker-default" id="applyer" name="applyer"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for="applyRemark">申请备注</label>
		        <div>
		        	<textarea class="form-control input-sm field-editable "  id="applyRemark" name="applyRemark" data-parsley-maxlength="200"   rows="2"></textarea>
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
    $("#jointAcceptanceApplyForm").toggleEditState(false).styleFit();
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('jointAcceptanceApplyForm','jointAcceptanceApply/viewJointAcceptanceApply','',dataBack);
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    
//    if($.fn.DataTable.isDataTable('#completionDataTable')){
//		$("#completionDataTable").cgetData(false);//列表重新加载
//	}else{
//		completionDataTableInit();
//	};
    
    $(".cancelBtn").off().on("click",function(){
    	$("#jointAcceptanceApplyForm").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
    })
    
    
    //保存
    $(".saveBtn").off().on("click",function(){
    	if($("#jointAcceptanceApplyForm").parsley().isValid()){
    		$("#flag").val("0");	
        	saveDone();
    	}else{
    	//如果未通过验证, 则加载验证UI
    		$("#jointAcceptanceApplyForm").parsley().validate();
    	}
    	
    })    
    
    
    //推送
 	$(".pushBtn").off().on("click",function(){
 		if($("#jointAcceptanceApplyForm").parsley().isValid()){
 	    	$("#flag").val("1");	
 	    	saveDone();
 		}else{
    	//如果未通过验证, 则加载验证UI
    		$("#jointAcceptanceApplyForm").parsley().validate();
    	}
    	
    	
    }) 
    var saveDone=function(){
    	//加遮罩
    	$(".infodetails").loadMask("正在保存...", 1, 0.5);
    	var data = $("#jointAcceptanceApplyForm").serializeJson();
    	$.ajax({
            type: 'POST',
            url: 'jointAcceptanceApply/saveJointAcceptanceApply',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
            	//取消遮罩
            	$(".infodetails").hideMask();	
            	var content = "保存成功！";
            	if(data === "false"){
            		content = "保存失败！";
            	}else if(data === "true"){
            		$("#jointAcceptanceApplyForm").formReset();
            		$("#jointAcceptanceApplyTable").cgetData(true);  //列表重新加载
            		$('.editbtn').addClass('hidden');
            		$("#caiIds").val("");
            	}
            	var myoptions = {
                    	title: "提示信息",
                    	content: content,
                    	accept: popClose,
                    	chide: true,
                    	newpop:'new',
                    	icon: "fa-check-circle"
                }
                $("body").cgetPopup(myoptions);
            },
            error: function (jqXHR, textStatus, errorThrown) {
            	//取消遮罩
            	$(".infodetails").hideMask();	
                console.warn("资料验收申请失败！");
            }
        });
    }
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->