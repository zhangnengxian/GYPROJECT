<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<div class="infodetails">
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="auditCompleteDrawingDetailForm"  data-parsley-validate="true" action="">
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value="T-1234567"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="100" value="贵阳XXXX"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value="贵阳"/>
		        </div>
		    </div>
		    <div class="form-group  col-md-12 ">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes" data-parsley-maxlength="100" value="民用"/>
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
             <div class="form-group col-md-12  ">
		        <label class="control-label" for="cuName">施工单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"   id="cuName" name="cuName" value=""/>
		        </div>
		    </div>
             <div class="form-group col-md-12  ">
		        <label class="control-label" for="suName">监理单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable" id="suName" name="suName" value=""/>
		        </div>
		    </div>
             <div class="form-group col-md-12  ">
		        <label class="control-label" for="duName">设计单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"   id="duName" name="duName" value=""/>
		        </div>
		    </div>
             <div class="form-group col-md-6  ">
		        <label class="control-label" for="applyer">申请人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="applyer" name="applyer" value=""/>
		        </div>
		    </div>
            
		    
 			
		    <div class="form-group col-md-6">
		        <label class="control-label">申请日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="applyDate"  name="applyDate" data-parsley-required="true" value="" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="applyNo">申请编号</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="applyNo"  name="applyNo" data-parsley-required="true" value="" data-parsley-maxlength="500">
		        </div>
		    </div>
		    
		     
		   <div class="form-group col-md-12 ">
		        <label class="control-label" for="">申请事由</label>
		    	<div>
		    		<textarea class="form-control input-sm field-editable" id="applyReason"  name="applyReason"  rows="4" data-parsley-maxlength="500"></textarea>  
		 		      
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
    
    //表单样式适应
    $("#auditCompleteDrawingDetailForm").styleFit();
    
    //参数传false时，表单不可编辑
    $("#auditCompleteDrawingDetailForm").toggleEditState(false);
   
    //查询详述
    var drawingReviewDetail = function(){
    	var url = 'applyCompleteDrawing/viewDrawingApply';
    	var projId = $("#projId").val();
    	var data = "id="+projId;
    	var f = $("#auditCompleteDrawingDetailForm");
    	 $.ajax({
             type: 'POST',
             url: url,
             data: data,
             dataType: 'json',
             success: function (data) {
                 //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
                var selects = f.find('select[disabled]');
                 selects.removeAttr("disabled");
                 //表单反序列化填充值
                 f.deserialize(data);
             },
             error: function (jqXHR, textStatus, errorThrown) {
                 console.warn("cgetDetail() -> 详情查询失败");
             }
         });
    }(); 
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->