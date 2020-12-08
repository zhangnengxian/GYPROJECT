<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<input type="hidden" id="flag" name="flag"/> 
    	<form class="form-horizontal" id="divisionalAcceptanceApplyForm" data-parsley-validate="true" action="divisionalAcceptanceApply/saveDivisionalAcceptanceApply">
       		<input type="hidden" id="projId" name="projId"/>
       		<input type="hidden"  id="daaId" name="daaId"/>
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
			<%--<div class="form-group col-md-6 col-sm-12 ">--%>
		    	<%--<!-- 新加字段 -->--%>
				<%--<label class="control-label" for="suReport">有无施工图</label>--%>
				<%--<div>--%>
					<%--<label><input class="field-editable" type="radio" name="consDrawingSituation" value="1"  checked> 有</label>--%>
					<%--<label><input class="field-editable" type="radio" name="consDrawingSituation" value="2" > 无</label>--%>
				<%--</div>--%>
		    <%--</div>--%>
			<div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for="">计划竣工时间</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable timestamp"  id="plannedEndDate" name="plannedEndDate"  value=""/>
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
					<textarea class="form-control field-editable" name="thisAcceptanceContent" id="thisAcceptanceContent" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
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

			<div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>试压记录</h4></div>
		    <div class="form-group col-md-6 col-sm-12 ">
		    	<!-- 新加字段 -->
				<label class="control-label">试压记录</label>
				<div>
					<label><input class="field-editable" type="radio" name="testRecord" value="1"  checked> 有</label>
					<label><input class="field-editable" type="radio" name="testRecord" value="2" > 无</label>
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
		        <label class="control-label" for="applyer">申请人</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="applyer" name="applyer"   value=""/>
		        </div>
		    </div>
		    <%--<div class="form-group col-md-6 col-sm-12">--%>
		    	<%--<!-- 新加字段 -->--%>
		        <%--<label class="control-label" for="applyDate">申请日期</label>--%>
		    	<%--<div>--%>
		    		<%--<input type="text" class="form-control input-sm field-editable datepicker-default"  id="applyDate" name="applyDate"  data-parsley-required="true" value=""/>--%>
		        <%--</div>--%>
		    <%--</div>--%>
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
    $('#divisionalAcceptanceApplyForm').toggleEditState(false).styleFit();
  	//查右侧工程详述
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    //保存
    $(".saveBtn").off().on("click",function(){
    	$('#divisionalAcceptanceApplyForm').cformSave('divisionalAcceptanceApplyTable',saveAcceptanceApplyBack,false);
    })
  	
    var saveAcceptanceApplyBack=function(){
    	$(".editbtn").addClass("hidden");
    	$('#divisionalAcceptanceApplyForm').toggleEditState(false);
    }
  	
   
    //放弃
    $(".cancelBtn").on("click",function(){
    	$("#flag").val("");
    	
    	if($("#daaId").val()==""){
    		//返回列表区
    		$('ul.nav-tabs>li.active').removeClass("active");
    		$('#listTab').tab("show");
    		$('#"divisionalAcceptanceApplyTable"').cgetData(true,queryTableBack);
    	}else{
    		//返回列表区
    		$('ul.nav-tabs>li.active').removeClass("active");
    		$('#listTab').tab("show");
    	}
	   	//移除验证
	   	$("#divisionalAcceptanceApplyForm").parsley().reset();
    });
 	
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->