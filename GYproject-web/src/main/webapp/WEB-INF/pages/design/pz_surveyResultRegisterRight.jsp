
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
		<a href="javascript:;" class="btn  btn-primary btn-sm m-l-5  hidden get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>
	    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5  entBtn" >保存</a>
    	<a href="javascript:;" class="btn btn-info btn-sm m-l-5  saveBtn hidden" >推送</a>
    	<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<input type="hidden" name="unitType" id="unitType" value="${unitType}"/>
	<input type="hidden" name="designerPost" id="designerPost" value="${designerPost}"/>
	<input type="hidden" name="surveyPost" id="surveyPost" value="${surveyPost}"/>
	<input type="hidden" name="directorPost" id="directorPost" value="${directorPost}"/>
	<input type="hidden" name="surveyBuilderPost" id="surveyBuilderPost" value="${surveyBuilderPost}"/>
	<input type="hidden" name="post" id="post" value="${post}"/>
	<input type="hidden" name="marketPost" id="marketPost" value="${market}"/>
	<input type="hidden" name="deptDivide" id="deptDivide"  value="${deptDivide}"/>
	<input type="hidden" name="customerServiceCenter" id="customerServiceCenter"  value="${customerServiceCenter}"/>
	<input type="hidden" name="marketingCenter" id="marketingCenter"  value="${marketingCenter}"/>
    <div class="form-group col-md-6 col-sm-12 hidden">
		<label class="control-label" for="">业务部门</label>
		<div>
			<select class="form-control input-sm field-editable" id="projectType1"  name="projectType1"  >
				<option value=""></option>
					<c:forEach var="enums" items="${projectType}">
						<option data-department="${enums.contractType}"value="${enums.projTypeId}" >${enums.contractType}</option>
					</c:forEach>
			</select>		        
		</div>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="surveyDetailform" data-parsley-validate="true" action="">
		    <input type="hidden" name="projId" id="projId"/>
		    <input type="hidden" name="projectType" id="projectType"/>
		    <input type="hidden" name="deptId" id="deptId"/>
		    <input type="hidden" name="surveyId" id="surveyId"/>
		    <input type="hidden" name="surveyBuilderId" id="surveyBuilderId"/>
		    <input type="hidden" name="corpId" id="corpId"/>
		    <input type="hidden" name="version" id="version"/>
		    <input type="hidden" name="tenantId" id="tenantId"/>
		    <input type="hidden" name="designerId" id="designerId" />
		    <input type="hidden"  name="contractType" id="contractType" />
		    <input type="hidden"  name="divide" id="divide" />
		   	<!--  工程信息 -->
       		<div class="form-group col-md-6 col-sm-12 ">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value="" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value="" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3"></textarea>
	            </div>
            </div>
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
		    <div class="form-group col-md-6">
            	<label class="control-label" for="projSourceDes">受理来源</label>
            	<div>
		            <input type="text" class="form-control field-not-editable"  id="projSourceDes" name="projSourceDes" rows="2" data-parsley-maxlength="200"/>
		        </div>
        	</div>
          	 <!--  客户信息 -->
		    <div class="form-group col-md-12 noUser">
		        <label class="control-label" for="custName">客户名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 noUser">
		        <label class="control-label" for="custContact">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 noUser">
		        <label class="control-label" for="custPhone">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" value=""/>
		        </div>
		    </div>
			<div class="form-group col-md-12">
				<label class="control-label" for="projectRemark">受理备注</label>
				<div>
					<textarea class="form-control field-not-editable" name="projectRemark" id="projectRemark" rows="2" cols="" data-parsley-maxlength="200"></textarea>
				</div>
			</div>
		   	<!--  勘察信息 -->
			<div class="form-group col-md-6 ">
				<label class="control-label" >派工日期</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="disSurveyDate"  name="disSurveyDate" value="" >
				</div>
			</div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="surveyer">勘察人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="surveyer"  name="surveyer" data-parsley-maxlength="20" value="">
		        </div>
		    </div>
		    
		     <div class="form-group col-md-6 ">
		        <label class="control-label" for="">设计人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="designer" name="designer" data-parsley-required="true">
		           <a id="buPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择设计人"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		     <!-- <div class="form-group col-md-6 ">
		        <label class="control-label" for="">现场代表</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="surveyBuilder" name="surveyBuilder">
		           <a id="builderPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择现场代表"><i class="fa fa-search"></i></a>
		        </div>
		    </div> -->
		    <div class="form-group col-md-6 col-sm-12 mobileDiv hidden">
				<label class="control-label">经度</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" value="" id="projLongitude" name="projLongitude" data-parsley-required="true"/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 mobileDiv hidden">
				<label class="control-label">纬度</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" value="" id="projLatitude" name="projLatitude" data-parsley-required="true"/>
				</div>
			</div>
 			<div class="form-group col-md-6 isBack hidden">
		        <label class="control-label" for="isBack">退单</label>
		    	<div>
		    		<select class="form-control input-sm field-editable surveyContent" id="isBack"  name="isBack"  >
		 		      	<option value="0"></option>
						<option value="1" >退单</option>
		             </select>
		        </div>
		    </div>
		    <div class="form-group col-md-6 backReason">
		        <label class="control-label" for="">退单原因</label>
		    	<div>
		    		<select class="form-control input-sm field-editable a2" id="backReason"  name="backReason" data-parsley-required="true" >
		 		      	<option></option>
		                <c:forEach var="enums" items="${backReason}">
							   	<option value="${enums.value}">${enums.message}</option>
		                </c:forEach>
		             </select>
		        </div>
		    </div>
		     <div class="form-group col-md-6 backRemarks hidden">
		        <label class="control-label" for="">退单备注</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable a2" id="backRemarks"  name="backRemarks" data-parsley-required="true" data-parsley-maxlength="200" />
		 		      	
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 projectChange">
		        <label class="control-label" for="">工程移交</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable surveyContent" name="projectChange" value="1" />是
		            </label>
		            <label>
		            	<input type="radio" class="field-editable surveyContent" name="projectChange" value="0" checked/>否
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-md-12  remark hidden">
		        <label class="control-label" for="">备注</label>
		        <div>
		            <textarea type="text" class="form-control input-sm field-editable"  id="remark" name="remark" data-parsley-maxlength="200"  rows="2" value="" data-parsley-required="true"></textarea>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label">勘察日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default surveyContent" id="surveyDate"  name="surveyDate" data-parsley-required="true" value="" >
		        </div>
		    </div>
		    <!--  现场情况 -->
		    <div class="form-group col-md-6  civilType">
		        <label class="control-label" for="installNums">安装户数</label>
		        <div>
		            <input type="number" class="form-control input-sm field-editable surveyContent" min="0"  id="installNums" name="installNums" data-parsley-maxlength="20"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 trunkType">
		        <label class="control-label" for="trunkLength">干线长度</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable surveyContent"  id="trunkLength" name="trunkLength" data-parsley-maxlength="50"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="area">片区号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable surveyContent"   id="area" name="area" data-parsley-maxlength="50"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 publicType">
		        <label class="control-label" for="customerSituation">客户现状</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable surveyContent"  id="customerSituation" name="customerSituation" data-parsley-maxlength="50"  value=""/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 changeType">
		        <label class="control-label" for="withGasFrequency">带气次数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable surveyContent" id="withGasFrequency"  name="withGasFrequency" data-parsley-maxlength="20" value="" >
		        </div>
		    </div>
		    <div class="form-group col-md-6 changeType">
		        <label class="control-label" for="gasSourceUse">使用气源</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable surveyContent " id="gasSourceUse"  name="gasSourceUse" data-parsley-maxlength="20" value="" >
		        </div>
		    </div>
		     <div class="form-group col-md-6 changeType">
		        <label class="control-label" for="destroyRoadLength">破路长度</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable surveyContent" id="destroyRoadLength"  name="destroyRoadLength" data-parsley-maxlength="20" value="" >
		        </div>
		    </div>
			<div class="form-group col-md-6 changeType" >
		        <label class="control-label" for="estimateDeliverDate">交付时间</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default surveyContent" id="estimateDeliverDate"  name="estimateDeliverDate" value="" >
		        </div>
		    </div>
		    
		     <div class="form-group col-md-6 col-sm-12 changeType">
		        <label class="control-label" for="">调压设施</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable surveyContent" name="isNewBuild" value="1" checked/>新建
		            </label>
		            <label>
		            	<input type="radio" class="field-editable surveyContent" name="isNewBuild" value="0" />既有
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 changeType">
		        <label class="control-label" for="">电子资料</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable surveyContent" name="isElectronicData" value="1" checked/>已提供
		            </label>
		            <label>
		            	<input type="radio" class="field-editable surveyContent" name="isElectronicData" value="0" />未提供
		            </label>
		        </div>
		    </div>
		     <div class="form-group col-md-6 col-sm-12 civilType clearboth">
		        <label class="control-label" for="">是否是智能表</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable surveyContent" name="isIntelligentMeter" value="1" />是
		            </label>
		            <label>
		            	<input type="radio" class="field-editable surveyContent" name="isIntelligentMeter" value="0" checked/>否
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-md-6  hidden isIntelligentMeter generalMeterNum civilType">
		        <label class="control-label" for="">普通表</label>
		        <div>
		           <input type="number" class=" form-control input-sm field-editable surveyContent" min="0" id="generalMeterNum"  name="generalMeterNum" data-parsley-maxlength="20" value="" >
		       		<a href="javascript:;" class="btn btn-sm btn-default">户</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 hidden isIntelligentMeter intelligentMeterNum civilType">
		        <label class="control-label" for="intelligentMeterNum">智能表</label>
		        <div>
		           <input type="number" class=" form-control input-sm field-editable surveyContent" min="0" id="intelligentMeterNum"  name="intelligentMeterNum" data-parsley-maxlength="20" value="" >
		           <a href="javascript:;" class="btn btn-sm btn-default surveyContent">户</a>
		        </div>
		    </div>
		     <!-- <div class="form-group col-md-12 col-sm-12 changeType">
		        <label class="control-label" for="">必须有电子资料</label>
		    	<div>
		    		<label>
			        	<input type="checkbox"  name="mustElectronicDataId" class="field-editable"  value="0"/>道路施工图
			        	<input type="checkbox"  name="mustElectronicDataId" class="field-editable"  value="1"/>平面纵断图
			        	<input type="checkbox"  name="mustElectronicDataId" class="field-editable"  value="2"/>管综图
		        	</label>
		        </div>
		    </div>
		    <input type="hidden"  class="mustElectronicData" id="mustElectronicData"  name="mustElectronicData"/>
		     <div class="form-group col-md-12 col-sm-12 changeType">
		        <label class="control-label" for="">最好有电子资料</label>
		    	<div>
		    		<label>
			        	<input type="checkbox"  name="betterElectronicDataId" class="field-editable"  value="0"/>道路路网图
			        	<input type="checkbox"  name="betterElectronicDataId" class="field-editable"  value="1"/>片区规划图
			        	<input type="checkbox"  name="betterElectronicDataId" class="field-editable"  value="2"/>控制性规划图
		        	</label>
		        </div>
		    </div> -->
		    <input  type="hidden"  class="betterElectronicData" id="betterElectronicData" name="betterElectronicData"/>
		    <div class="form-group col-md-6 col-sm-12 changeType">
		        <label class="control-label" for="approachCondition">进场条件</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable surveyContent" name="approachCondition" value="1"  checked/>已具备
		            </label>
		            <label>
		            	<input type="radio" class="field-editable surveyContent" name="approachCondition" value="0" />未具备
		            </label>
		        </div>
		    </div>
		    
		    
		    
		    <div class="form-group col-md-12">
		     	<label class="control-label " for="surveyDes">踏勘结果</label>
		     	<div> 
		        	<textarea class="form-control field-editable surveyContent" name="surveyDes" id="surveyDes" rows="4" cols="" data-parsley-maxlength="500" data-parsley-required="true" ></textarea></div>
		    </div>
		    <div class="form-group col-md-6 ">
				<label class="control-label" >结果登记日期</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="surveyDesDate"  name="surveyDesDate" value="" >
				</div>
			</div>
		    <!-- <div class="form-group col-md-12">
		     	<label class="control-label" for="connectGasDes">方案说明</label>
		     	<div> 
		        	<textarea class="form-control  field-editable" name="connectGasDes" id="connectGasDes" rows="4" cols="" data-parsley-maxlength="500" ></textarea>
	        	</div>
		    </div> -->
		    <div class="form-group col-md-12">
		     	<label class="control-label" for="technicalSuggestion">技术建议</label>
		     	<div> 
		        	<textarea class="form-control  field-editable" name="technicalSuggestion" id="technicalSuggestion" rows="4" cols="" data-parsley-maxlength="500" ></textarea>
	        	</div>
		    </div>
		     <div class="form-group col-md-6 ">
				<label class="control-label" >建议填写日期</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="techSugDate"  name="techSugDate" value="" >
				</div>
			</div>
		</form>
		<div class="clearboth form-box m-t-5 scaleTableForm">
			<form id="scaleTableForm"  data-parsley-validate="true">
				<table id="scaleTable" class="table table-striped table-bordered nowrap" width="100%"">
		        	<thead>
			            <tr>
			                <th width="80px">细类</th>
			                <th width="50px">吨位</th>
			                <th width="50px">座数</th>
			                <th width="50px">台数</th>
			                <th width="50px">户数</th>
			                <th width="80px">预计用量(m³/h)</th>
			                <!-- <th width="30px"></th> -->
			            </tr>
		           </thead>
				</table>
				
			</form>
		</div>
		<div class="clearboth form-box m-t-5 scaleTableChangeForm hidden">
			<form id="scaleTableChangeForm"  data-parsley-validate="true">
				<table id="scaleChangeTable" class="table table-striped table-bordered nowrap" width="100%"">
		        	<thead>
			            <tr>
			                <th width="80px">细类</th>
			                <th width="50px">管径</th>
			                <th width="50px">长度(千米)</th>
			            </tr>
		           </thead>
				</table>
			</form>
		</div>
		<div class="clearboth form-box  photoBox">
			<div class="form-group width-full attach-images-list" id="projectImagesList">
			     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
			     <ul class="row">
			     </ul>
			</div>
		</div>
    </div>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>

function surveyBack(data){
	if($("#contractType").val()=='11'){
		//显示民用的 隐藏公建、改管、干线
		$(".changeType,.trunkType").addClass("hidden");
		$(".civilType,.publicType").removeClass("hidden");
		//智能表
		if(data.isIntelligentMeter==null||data.isIntelligentMeter==''){
			$('input:radio[name="isIntelligentMeter"]').removeAttr("checked");
		}
		showIntelligentMeter();
	}else if($("#contractType").val()=='21'){
		//显示公建的 隐藏民用、改管、干线
		$(".changeType,.civilType,.trunkType").addClass("hidden");
		$(".publicType").removeClass("hidden");
	}else if($("#contractType").val()=='31'){
		//显示改管的 隐藏民用、公建、干线
		$(".publicType,.civilType,.trunkType").addClass("hidden");
		$(".changeType").removeClass("hidden");
	}else{
		//显示干线的 隐藏民用、公建、改管
		$(".publicType,.civilType").addClass("hidden");
		$(".changeType,.trunkType").removeClass("hidden");
	}	
	
	//客服中心可以退到现场踏勘
	if($("#deptDivide").val()==$("#customerServiceCenter").val()){
		$(".projectChange").removeClass("hidden");
	}else{
		$(".projectChange").addClass("hidden");
	}
	//踏勘员或客户经理可以推送
	if($("#post").val()!=""){
		$(".saveBtn").removeClass("hidden");
		/* if($("#post").val().indexOf($("#surveyPost").val())>=0||$("#post").val().indexOf($("#directorPost").val())>=0){
			$(".saveBtn").removeClass("hidden");
		}else{
			$(".saveBtn").addClass("hidden");
		} */
		if($("#post").val().indexOf($("#designerPost").val())>=0){
			$("#technicalSuggestion").removeClass("field-not-editable");
			$("#technicalSuggestion").addClass("field-editable");
			
			$(".surveyContent").removeClass("field-editable");
			$(".surveyContent").addClass("field-not-editable");
			
		}else{
            //console.info(2018,11);
			$("#technicalSuggestion").removeClass("field-editable")
			$("#technicalSuggestion").addClass("field-not-editable");
			$(".surveyContent").removeClass("field-not-editable");
			$(".surveyContent").addClass("field-editable");
		}
	}else{
		$(".saveBtn").addClass("hidden");
	}
	if($("#custName").val()==""){
		$(".noUser").addClass("hidden");
	}else{
		$(".noUser").removeClass("hidden");
	}
	
	 //designerData.deptId=json.duId;
	 $(".editbtn").addClass("hidden");
	 $('.backReason').addClass('hidden');
	 $('.backRemarks').addClass('hidden');
	 $("#surveyDetailform").toggleEditState(false);
	 
		$("#projectType1 option[value='"+$("#projectType").val()+"']").attr("selected","selected");
		detailSearchData.projLtypeId = $("#projectType").val();
		detailSearchData.projId = $("#projId").val();
		var selectVal=$("#projectType1 option:selected").text();
		if(selectVal=="31"||selectVal=="41"){
			$(".scaleTableForm").addClass("hidden");
			$(".scaleTableChangeForm").removeClass("hidden");
			//加载下面的第2个table
			if($.fn.DataTable.isDataTable('#scaleChangeTable')){
				//初始化过
				$("#scaleChangeTable").cgetData(false,scaleChangeTableCallBack);//列表重新加载
			}else{
				sacletableinit2();
			}
		}else{
			$(".scaleTableChangeForm").addClass("hidden");
			$(".scaleTableForm").removeClass("hidden");
			//加载下面的第1个table
			if($.fn.DataTable.isDataTable('#scaleTable')){
				//初始化过
				$("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
			}else{
				sacletableinit();
			}
		}	 
	 
	 setTimeout(function(){
	    $("#projectImagesList").getImagesList({
	    	"projId": $("#projId").val(),
	    	"step": getStepId(),
	    	"projNo": $("#projNo").val(),
	    	"busRecordId": $("#surveyId").val() || '-1'
	    });
	 },300);
	 if($("#projName").val()==''){
		 $("#projName").attr("readonly",false);
		 $("#projName").removeAttr("disabled");
	 }
	 if(trSData.surveyResultTable.json.contributionMode == '5'){  //公司出资隐藏客户信息
     	$(".noUser").addClass("hidden");
     }else{
     	$("#noUser").removeClass("hidden");
     } 
}
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    $("#surveyDetailform").toggleEditState(false);
  	//表单样式适应
   	$("#surveyDetailform").styleFit();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $(".editbtn").addClass("hidden");
    
    changeAText();
    
    $('.backReason').addClass('hidden');
    //查右侧工程详述
    trSData.t && trSData.t.cgetDetail('surveyDetailform','surveyResultRegister/viewSurveyResult','',surveyBack); 
  
    /**点击右侧维护区 推送 按钮时*/
    $(".saveBtn").off("click").on("click",function(){
    	
    	$("#surveyDes").attr("data-parsley-required",true);
    	
    	
    	var data=$("#surveyDetailform").serializeJson();
    	if(data.isBack !== "1"){
    		$("#technicalSuggestion").attr("data-parsley-required",true);
    	}else{
    		$("#technicalSuggestion").removeAttr("data-parsley-required",true);
    	}
    	
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
        if($("#surveyDetailform").parsley().isValid()){
        	var data=$("#surveyDetailform").serializeJson();
        	if(data.isBack === "1"){
	       		var popoptions = {
	       				title: '提示',
	       				content: '您确定退单吗？',
	       				accept: backDone
	       		};
	       		$("body").cgetPopup(popoptions);
        	}else{
        		
        		if(data.projectChange==="1"){
        			$("body").cgetPopup({
	       				title: '提示',
	       				content: '您确定将工程转交至市场发展部吗？',
	       				accept: backDone
	       			});
        		}else{
        			backDone();
        		}
        	}
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#surveyDetailform").parsley().validate();
        }
    }); 
    var backDone = function(data){
    	$("#surveyDes").removeAttr("data-parsley-required",true);
    	var selectVal=$("#projectType1 option:selected").text();
		var tableform,scaleTable;
		if(selectVal=="31"||selectVal=="41"){
			tableform = $("#scaleTableChangeForm");
			scaleTable=$("#scaleChangeTable");
		}else{
			tableform = $("#scaleTableForm");
			scaleTable=$("#scaleTable");
		}
		if (tableform.parsley().isValid()) {

		//加遮罩
		$(".infodetails").loadMask("正在保存...", 1, 0.5);	
       	var mdata = tableform.getDTFormData();
       	var viewdata = $("#surveyDetailform").serializeJson();
       	//选中的工程规模明细
       	var resultData = [];
       	for(var i=0;i<mdata.length;i++){
       		var data = mdata[i];
       		if(data.scaleId !== undefined){
       			data.projNo = $("#projNo").val();
       			data.projId = $("#projId").val();
       			if((data.tonnage!==null&&data.tonnage!==""&& data.tonnage!==undefined) || (data.searNum!==null&&data.searNum!==""&& data.searNum!==undefined) || (data.num!==null&&data.num!==""&& data.num!==undefined) || (data.houseNum!==null&&data.houseNum!==""&& data.houseNum!==undefined) || (data.gasConsumption!==null&&data.gasConsumption!==""&& data.gasConsumption!==undefined)||(data.pipeDiameter!==null&&data.pipeDiameter!==""&& data.pipeDiameter!==undefined)||(data.pipeLength!==null&&data.pipeLength!==""&& data.pipeLength!==undefined) ){
       				resultData.push(data);
       			}
       		}
       	}
    	
       		if(resultData.length<1){
       			//取消遮罩
       			$(".infodetails").hideMask();	
	       		alertInfo("请填写用气规模明细记录！");
	       		return false;
	       	}
      		viewdata.scaleDetails = resultData;
      		
      		console.info(data);
    	$.ajax({
            type: 'POST',
            url: 'surveyResultRegister/saveSurveyInfo',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(viewdata),
            success: function (data) {
            	//取消遮罩
            	$(".infodetails").hideMask();	
            	var content = "数据保存成功！";
            	if(data === "false"){
            		content = "数据保存失败！";
            	}else if(data === "true"){
            		$("#surveyDetailform input, #surveyDetailform textarea").not(":radio, :checkbox").val('');
            		$("#backReason").val("");
            		$("#isBack").val("0");
            		$("#surveyResultTable").cgetData(true,surveyTableCallBack);  //列表重新加载
            	}else if(data === "already"){
            		content = "此信息已被修改，请刷新页面！";
            	}else if(data ===  "noneNumber"){
               		content = "改管组出资方式编码尚未配置，请先在后台配置！";
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
                console.warn("现场踏勘操作区记录保存失败！");
            }
        });
    	//如果通过验证, 则移除验证UI
    	$("#surveyDetailform").parsley().reset();
    	tableform.parsley().reset();
		}else{
			$("#surveyDetailform").parsley().validate();
			tableform.parsley().validate();
		}
    }


 		$("#isBack").change(function(){
            if($(this).val()=="1"){
            	$(".projectChange").addClass("hidden");
            	$(".back-hid").addClass("hidden");
         		$('.backReason').removeClass('hidden');
         		//$('#backReason').attr("data-parsley-required",true);
            }else{
            	$(".projectChange").removeClass("hidden");
            	$("#surveyDes").attr("data-parsley-required",true);
        		$(".back-hid").removeClass("hidden");
        		$('.backReason').addClass('hidden');
        		$("#backReason").val('');
            }
            $("#surveyDetailform").styleFit();
    	});
    	$("#backReason").change(function(){
    		if($(this).val()=='5'){//退单
    			$('.backRemarks').removeClass('hidden');
    		}else{
    			$('.backRemarks').addClass('hidden');
    			$('.backRemarks').val("");
    		}
    	});
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	//清空表单
    	//$("#surveyDetailform input,#surveyDetailform textarea").val('');
    	$("#surveyDetailform").formReset();
    	$("#backReason").val('');
   	 	$("#surveyResultTable").cgetData();
    });
    
    /**点击右侧维护区 保存 按钮时*/
    $(".entBtn").off("click").on("click",function(){
    	
		$("#surveyDes").attr("data-parsley-required",false);
		$("#technicalSuggestion").removeAttr("data-parsley-required",false);
    	
    	if($("#surveyDetailform").parsley().isValid()){
         	var data=$("#surveyDetailform").serializeJson();
         	if(data.isBack === "1"){
	       		var popoptions = {
	       				title: '提示',
	       				content: '您确定退单吗？',
	       				accept: backDone2
	       		};
	       		$("body").cgetPopup(popoptions);
        	}else{
        		
        		if(data.projectChange==="1"){
        			$("body").cgetPopup({
	       				title: '提示',
	       				content: '您确定将工程转交至市场发展部吗？',
	       				accept: backDone2
	       			});
        		}else{
        			backDone2();
        		}
        	}
         	
         }else{
         	//如果未通过验证, 则加载验证UI
         	$("#surveyDetailform").parsley().validate();
         }
    });
    var backDone2 = function(){
    	var selectVal=$("#projectType1 option:selected").text();
		var tableform,scaleTable;
		if(selectVal=="31"||selectVal=="41"){
			tableform = $("#scaleTableChangeForm");
			scaleTable=$("#scaleChangeTable");
		}else{
			tableform = $("#scaleTableForm");
			scaleTable=$("#scaleTable");
		}
		if (tableform.parsley().isValid()) {
			
		$(".infodetails").loadMask("正在保存...", 1, 0.5);
       	var mdata = tableform.getDTFormData();
       	var viewdata = $("#surveyDetailform").serializeJson();
       	//选中的工程规模明细
       	var resultData = [];
       	for(var i=0;i<mdata.length;i++){
       		var data = mdata[i];
       		if(data.scaleId !== undefined){
       			data.projNo = $("#projNo").val();
       			data.projId = $("#projId").val();
       			if((data.tonnage!==null&&data.tonnage!==""&& data.tonnage!==undefined) || (data.searNum!==null&&data.searNum!==""&& data.searNum!==undefined) || (data.num!==null&&data.num!==""&& data.num!==undefined) || (data.houseNum!==null&&data.houseNum!==""&& data.houseNum!==undefined) || (data.gasConsumption!==null&&data.gasConsumption!==""&& data.gasConsumption!==undefined)||(data.pipeDiameter!==null&&data.pipeDiameter!==""&& data.pipeDiameter!==undefined)||(data.pipeLength!==null&&data.pipeLength!==""&& data.pipeLength!==undefined) ){
       				resultData.push(data);
       			}
       		}
       	}
       	if(selectVal!=="31"){
       		if(resultData.length<1){
       			$(".infodetails").hideMask();	
           		alertInfo("请填写用气规模明细记录！");
           		return false;
           	}
       	}
       
      	viewdata.scaleDetails = resultData;
    	$.ajax({
            type: 'POST',
            url: 'surveyResultRegister/saveSurvey',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(viewdata),
            success: function (data) {
            	$(".infodetails").hideMask();	
            	var content = "数据保存成功！";
            	if(data === "false"){
            		content = "数据保存失败！";
            	}else if(data === "true"){
            		SaveCallBack();
            	}else if(data === "already"){
            		content = "此信息已被修改，请刷新页面！";
            	}else if(data ===  "noneNumber"){
               		content = "该类出资方式编码尚未配置，请先在后台配置！";
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
            	$(".infodetails").hideMask();	
                console.warn("接气方案操作区记录保存失败！");
            }
        });
    	//如果通过验证, 则移除验证UI
    	$("#surveyDetailform").parsley().reset();
    	tableform.parsley().reset();
		}else{
			$("#surveyDetailform").parsley().validate();
			tableform.parsley().validate();
		}
    }
    var SaveCallBack = function(data){
    	if(data === "true"){
    		//表单不可编辑
        	$("#surveyDetailform").toggleEditState(false);
        	//按钮隐藏
    		$(".editbtn").addClass("hidden");
    	}else{
    		$(".editbtn").removeClass("hidden");
    	}
    	var pcId = $("#surveyId").val(),
		projNo = $("#projNo").val();
		projId = $("#projId").val();
		
		if(_inApk && $(".attach-images-list").find(".new-image").length){
			preImagesUpload($(".attach-images-list .uploadBtn"), projId, projNo, getStepId(), pcId);
		};
		
		$("#surveyResultTable").cgetData(true,surveyTableCallBack);
		
    }
    
  //移动端点击上传
    $(".uploadBtn").off("click").on("click",function(){
	    var pcId = $("#surveyId").val(),
		projNo = $("#projNo").val(),
		projId = $("#projId").val(),
		stepId=getStepId();
	    busRecordId=pcId;
		if(_inApk && $(".attach-images-list").find(".new-image").length){
				//photoUpload($(".attach-images-list .upload-image-btn"), projId, projNo, getStepId(), pcId);
				
			var images = $(".attach-images-list .uploadBtn").parents(".attach-images-list").find(".new-image"),
		    files = [];
		    $.each(images, function (i, el) {
		        files.push($(el).find(".preview-btn").attr("src-orig"));
		    });
		    //navigator.notification.alert(files);
		    if (!busRecordId && $("#surveyId").length && !$("#surveyId").val()) {
		        navigator.notification.alert('请先保存该条记录，保存记录后会自动上传图片', null, "提醒", "确定");
		        return false;
		    }
		    if (files.length) {
		    	uploadImages(files, 0, files.length, projId, projNo, stepId, busRecordId);
		    } else {
		        navigator.notification.alert("没有需要上传的图片!", null, "提醒", "确定");
		    }
		}else{
			 navigator.notification.alert("没有需要上传的图片!", null, "提醒", "确定");
		};
	})
    
	 if(_inApk){
		$('.mobileDiv').removeClass("hidden");
		$(".get-location").removeClass("hidden").off("click").on("click", function(){
			var t = $(this);
			t.button("loading");
			cgetLocation(function(position) {
		        $('[name="projLongitude"]').val(position.longitude);
		        $('[name="projLatitude"]').val(position.latitude);
				t.button("reset");
			});
		});
	}
    var designerPost=$("#designerPost").val(),
    	unitType = $("#unitType").val(),
   		deptId;
	//选择设计员
    $("#buPop").off().on("click",function(){
    	if($("#divide").val()==$("#customerServiceCenter").val()){//客服中心
    		deptId = $("#deptId").val();//客服中心
      	}else if($("#divide").val()==$("#marketingCenter").val()){//营销中心
      		deptId=$("#deptId").val()+","+$("#designInstituteId").val();//设计院或营销中心
      	}else{//其他工程
      		deptId=$("#designInstituteId").val();//设计院
      		deptId="1112,1130,1131";
      	}
    	var corpId=$("#corpId").val();
    	staffPopup({
    		'designer':'staffName',
    		'designerId':'staffId'
    	},designerPost,unitType,deptId,corpId);
    });
	//选择现场代表
    /* $("#builderPop").off().on("click",function(){
    	var postBul=$("#surveyBuilderPost").val();
      	var corpId=$("#corpId").val(),unitType=$("#unitType").val(),deptId=$("#deptId").val();
      	console.info("#deptId==="+deptId);
      	deptId = '112001';
    	
    	staffPopup({
    		'surveyBuilder':'staffName',
    		'surveyBuilderId':'staffId'
    	},postBul,unitType,deptId)
    }); */
    
    $('input:radio[name="projectChange"]').change(function(){
    	if($(this).val()=="1"){
    		$(".remark").removeClass("hidden");
    		$(".isBack").addClass("hidden");
    		$("#remark").attr("data-parsley-required",true);
    	}else{
    		$(".remark").addClass("hidden");
    		$(".isBack").removeClass("hidden");
    		$("#remark").removeAttr("data-parsley-required",true);
    	}
    })
    
    //智能标记chang事件
    $('input:radio[name="isIntelligentMeter"]').change(function(){
    	showIntelligentMeter();
    });
    var showIntelligentMeter = function(){
    	if($('input:radio[name="isIntelligentMeter"]:checked').val()=='1'){//是智能表
    		//$(".generalMeterNum").addClass("hidden");
    		//$(".intelligentMeterNum").removeClass("hidden");
    		$(".isIntelligentMeter").removeClass("hidden");
    	}else{//
    		//$(".generalMeterNum").removeClass("hidden");
    		//$(".intelligentMeterNum").addClass("hidden");
    		$(".isIntelligentMeter").addClass("hidden");
    	
    	}
    	//表单样式适应
       	$("#surveyDetailform").styleFit();
    }
    showIntelligentMeter();
    var diffFunc = function(){
    	if($("#post").val().indexOf($("#marketPost").val())>=0||$("#post").val().indexOf($("#surveyPost").val())>=0){
        	$("#buPop").removeClass("disabled");
        	$("#buPop").removeClass("disabled");
            $("#scaleTableForm,#scaleTableChangeForm").toggleEditState(true);
    	}else{
    		$("#buPop").addClass("disabled");
        	$("#buPop").addClass("disabled");
    	}
    	var techSugDate = $("#techSugDate").val();
    	if(techSugDate=='' && $("#technicalSuggestion").attr("readonly")!="readonly"){
    		$("#techSugDate").val($("#sysDate").val().substr(0,10));
    	}
    	var surveyDesDate = $("#surveyDesDate").val();
    	if(surveyDesDate=='' && $("#surveyDes").attr("readonly")!="readonly"){
    		$("#surveyDesDate").val($("#sysDate").val().substr(0,10));
    	}
    }
    diffFunc();
    </script>
<!-- ================== END PAGE LEVEL JS ================== -->