<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 printBtn" >打印</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="toolBtn f-r p-b-10  informbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 hidden informSaveBtn" >确定</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="completionDataForm" action="">
    		<!-- <button type="reset" class="hidden" id="reset"/> -->
    		<input type="hidden" id="projId" name="projId" />
    		<input type="hidden" id="corpId" name="corpId" />
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50" value="T-20170514002" />
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
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="200"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes"  data-parsley-maxlength="100"/>
		    		<!-- <select class="form-control input-sm field-not-editable" id="projectType"  name="projectType" data-parsley-required="true" >
						<option value="1" >居民户工程</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes"  data-parsley-maxlength="100"/>
		    		<!-- 
		    		<select class="form-control input-sm field-not-editable" id="investmentMethod"  name="investmentMethod" data-parsley-required="true" >
						<option value="1" >用户出资（自有资金）</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"  data-parsley-maxlength="200"/>
		    		
		    		<!-- <select class="form-control input-sm field-not-editable" id="deptName"  name="deptName" data-parsley-required="true" >
						<option value="1" >民用组</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="duName">申报单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""  data-parsley-maxlength="200"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custContact">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custPhone">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="13" value=""/>
		       
		        </div>
		    </div>
		     <div class="form-group col-md-6 clearboth">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for="">是否全选资料</label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable allCheckbox" value="" checked/>&nbsp;&nbsp;&nbsp;&nbsp;全选资料
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-sm-12">
		    	<h4><i class="fa fa-arrow-circle-o-right"></i>项目资料</h4>
		    </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="110509" checked/>&nbsp;&nbsp;&nbsp;&nbsp;施工任务单
		            </label>
		        </div>
		   </div> 
		   <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="110510" checked/>&nbsp;&nbsp;&nbsp;&nbsp;监理任务单
		            </label>
		        </div>
		   </div> 
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="110604" checked/>&nbsp;&nbsp;&nbsp;&nbsp;施工合同
		            </label>
		        </div>
		   </div>
		   
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="110608" checked/>&nbsp;&nbsp;&nbsp;&nbsp;施工预算书
		            </label>
		        </div>
		   </div>
		   
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="110804" checked/>&nbsp;&nbsp;&nbsp;&nbsp;工程结算书
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-sm-12">
		    	<h4><i class="fa fa-arrow-circle-o-right"></i>施工资料</h4>
		    </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="120102" checked/>&nbsp;&nbsp;&nbsp;&nbsp;会审交底
		            </label>
		        </div>
		   </div>
		   
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="120104" checked/>&nbsp;&nbsp;&nbsp;&nbsp;施工组织
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="120103" checked/>&nbsp;&nbsp;&nbsp;&nbsp;开工报告
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="120201" checked/>&nbsp;&nbsp;&nbsp;&nbsp;施工日志
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="110208" checked/>&nbsp;&nbsp;&nbsp;&nbsp;设计变更
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="120206"checked/>&nbsp;&nbsp;&nbsp;&nbsp;签证记录
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="120502"checked/>&nbsp;&nbsp;&nbsp;&nbsp;竣工报告
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="120214"  id = "nondestructive"/>&nbsp;&nbsp;&nbsp;&nbsp;无损检测
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-sm-12">
		    	<h4><i class="fa fa-arrow-circle-o-right"></i>报验资料</h4>
		    </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130121"checked/>&nbsp;&nbsp;&nbsp;&nbsp;焊条领用
		            </label>
		        </div>
		   </div><div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130122" checked/>&nbsp;&nbsp;&nbsp;&nbsp;焊条烘烤
		            </label>
		        </div>
		   </div><div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130103" checked/>&nbsp;&nbsp;&nbsp;&nbsp;管沟开挖
		            </label>
		        </div>
		   </div><div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130123" checked/>&nbsp;&nbsp;&nbsp;&nbsp;管道安装
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12 hidden">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130106"/>&nbsp;&nbsp;&nbsp;&nbsp;钢管焊接
		            </label>
		        </div>
		   </div><div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130107" checked/>&nbsp;&nbsp;&nbsp;&nbsp;PE管焊接
		            </label>
		        </div>
		   </div>     
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130105" checked/>&nbsp;&nbsp;&nbsp;&nbsp;防腐记录
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130126" checked/>&nbsp;&nbsp;&nbsp;&nbsp;防腐检查
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130110" checked/>&nbsp;&nbsp;&nbsp;&nbsp;隐蔽工程
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130111" checked/>&nbsp;&nbsp;&nbsp;&nbsp;吹扫记录
		            </label>
		        </div>
		   </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130127" checked/>&nbsp;&nbsp;&nbsp;&nbsp;埋地检查
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130109" checked/>&nbsp;&nbsp;&nbsp;&nbsp;沟槽回填
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130130" checked/>&nbsp;&nbsp;&nbsp;&nbsp;通球记录
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130132" checked/>&nbsp;&nbsp;&nbsp;&nbsp;户内挂表
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130133" checked/>&nbsp;&nbsp;&nbsp;&nbsp;设备安装
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130134" checked/>&nbsp;&nbsp;&nbsp;&nbsp;热熔对接
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130135" checked/>&nbsp;&nbsp;&nbsp;&nbsp;阳极安装
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130124" checked/>&nbsp;&nbsp;&nbsp;&nbsp;管道焊缝检查
		            </label>
		        </div>
		   </div>
		  <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130125" checked/>&nbsp;&nbsp;&nbsp;&nbsp;PE管焊缝检查
		            </label>
		        </div>
		   </div>
		   
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="120405" checked/>&nbsp;&nbsp;&nbsp;&nbsp;强度试验
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130137" checked/>&nbsp;&nbsp;&nbsp;&nbsp;焊口记录
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="130136" checked/>&nbsp;&nbsp;&nbsp;&nbsp;清扫记录
		            </label>
		        </div>
		   </div>
		    <div class="form-group col-md-6 col-sm-12 measurementTable hidden">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" id = "measurementTable" value="130138" />&nbsp;&nbsp;&nbsp;&nbsp;计量记录
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-sm-12">
		    	<h4><i class="fa fa-arrow-circle-o-right"></i>竣工资料</h4>
		    </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="110724" checked/>&nbsp;&nbsp;&nbsp;&nbsp;一站式打印
		            </label>
		        </div>
		   </div>
		   
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="110711" checked/>&nbsp;&nbsp;&nbsp;&nbsp;自检表
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="110722" checked/>&nbsp;&nbsp;&nbsp;&nbsp;预验收单
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="110713" checked/>&nbsp;&nbsp;&nbsp;&nbsp;分部验收单
		            </label>
		        </div>
		   </div>
		   <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for=""></label>
		    	<div>
             		 <label>
		            	<input type="checkbox"  name="" class="field-editable" value="110704" id="acceptanceDocument" checked/>&nbsp;&nbsp;&nbsp;&nbsp;验收单
		            </label>
		        </div>
		   </div>  
		   
		</form>
		<div class="form-group col-sm-12">
		    	<h4><i class="fa fa-arrow-circle-o-right"></i>竣工资料附件</h4>
		    </div>
		<table id="dataPopTableSeconds" class="table table-striped table-bordered nowrap" width="100%">
	   		<thead>
	     		<tr>
	     			<th></th>
	     		    <th></th>
	           		<th>资料名称</th>
	           		<th>资料类型</th>
	            	<th>上传日期</th>
	            	<th>上传人</th>
	            	<th width='40'>操作</th>
	           	</tr>
	       	</thead>
		</table>
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
    $("#completionDataForm").toggleEditState(false).styleFit();
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('completionDataForm','','',scaleDetailRefresh);
    
    //路径
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %> 
    //批量打印
    //根据选择的打印项查询报表，
   
    $(".printBtn").on("click",function(){
    	var selLen=$('#completionDataTable').find('tr.selected').length;
   	 	if(selLen>0){
	   		var rows =trSData.completionDataTable.json;
	   		console.info(rows);
	    	
	    	var selStrs="";  
	    	var checkboxs = $("#completionDataForm input[type='checkbox']:checked");
	    	//选中的资料项
	    	if(checkboxs.length>0){
	    		for(var i=0;i<checkboxs.length;i++){
	    			selStrs = selStrs +checkboxs[i].value+ ",";
	    		}
	    	}
	    	  if(selStrs.length>0){
	    		  var datas={'projId':rows.projId,'dataTypes':selStrs};
	    		  var t = $("#completionDataForm");
	    		  t.parent().parent().loadMask("正在生成打印报表。。。",1,0.5);
	    			 var response = $.ajax({
	    				  	url: "completionData/printCompletionData",
	    				  	type: "POST",
	    				  	data: datas,
	    				  	dataType: 'json',
	    				  	success: function (data) {
	    				  		if(data === "false"){
	    	                        alertInfo("生成打印报表失败！");
	    	                    }else if(data.length>0) {
	    	                    	printFunc(data);
	    	                    }else{
	    	                    	alertInfo("没有所打印的资料！");
	    	                    }
	    				  		t.parent().parent().hideMask();
	    				  	}
	    				  });
				}else{
	    	        alertInfo("请选择需要打印的资料项！"); 
				}
   	 	}else{
   	 		alertInfo("请先选择工程！");
   	 	}
    	
    });
    var printFunc = function(data){
    	/*  $("body").cgetPopup({
             title: "提示信息",
             content: "打印资料生成成功!",
             accept: popClose,
             chide: true,
             icon: "fa-check-circle"
         }); */
    	var datas = JSON.stringify(data);
    	var p=[]; 
    	$.each(data,function(i,e){
    		p.push(e);
    	});
    	var printUrl="<%=basePath%>/ReportServer";
    	 
    	var rp=p.join(","); 
        //使用FineReport自带的方法cjkEncode进行转码    
        var reportlets=FR.cjkEncode("["+rp+"]");  
        console.info(reportlets);
        var config = {      
        	url : printUrl,      
            isPopUp : false,    
            data : {      
            	reportlets: reportlets      
            }      
        };      
        	FR.doURLPDFPrint(config,true); 
       //FR.doURLFlashPrint(config,true); 
    }
    
  //全选按钮事件
    $(".allCheckbox").on("click",function(){
    	if($(this).is(':checked')){
    		$("#completionDataForm input[type='checkbox']").attr("checked", true);
    	}else{
    		$("#completionDataForm input[type='checkbox']").attr("checked", false);
    	}
    })
</script>
<!-- ================== END PAGE LEVEL JS ================== -->