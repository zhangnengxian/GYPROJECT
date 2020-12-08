<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="/assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<link href="/assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<div class="infodetails">
    <div class="clearboth form-box">
    	<div class="form-group col-md-6 ">
    		<h3>基础信息</h3>
    		<form class="form-horizontal" id="detail_planEstablishform" action="">
			    <!-- <div class="form-group col-md-6">
			        <label class="control-label" for="">编制部门</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable fixed-number"  name="cpDocumentDeptid"/>
			        </div>
			    </div> -->
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">编制时间</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable "   name="cpArriveDate">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">编制人</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable "   name="cpDocumenter">
			        </div>
			    </div> 
			    <!-- <div class="form-group col-md-6">
			        <label class="control-label" for="">合同金额</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable fixed-number"  name="contractAmount"/>
			        </div>
			    </div>
			   	<div class="form-group col-md-6">
			        <label class="control-label" for="">首付款</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable fixed-number"   name="downPayment"/>
			        </div>
			    </div> -->
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">下达日期</label>
			        <div>
			           <input type="text" class=" form-control input-sm datepicker-default field-editable timestamp " id="cpArriveDate" >
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">工期</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable "  name="projTimeLimit">
			        </div>
			    </div>
			    <!-- <div class="form-group col-md-12">
			        <label class="control-label" for="">甲方代表</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="custLegalRepresent" />
			        </div>
			    </div> -->
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">用户联系人</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="custContact" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="">联系电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="custPhone" />
			        </div>
			    </div>
			    <!-- <div class="form-group col-md-6 ">
			        <label class="control-label" for="">造价员</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="costMember" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="">合同管理员</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="costContract" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="">预算员</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="costBudgeter" />
			        </div>
			    </div> -->
			    <div class="form-group col-md-12">
			        <label class="control-label" for="">施工管理处</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"   name="managementOffice" />
			        </div>
			    </div>
			    <div class="form-group col-md-12 ">
			        <label class="control-label" for="">监理单位</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="suName" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="">监理负责人</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="suDirector"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="">联系电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="suPhone" />
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">现场监理师</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="suJgj" />
			        </div>
			    </div>
			   <!--  <div class="form-group col-md-6">
			        <label class="control-label" for="">监理总工</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="suCse"/>
			        </div>
			    </div> -->
			     <div class="form-group col-md-12 ">
			        <label class="control-label" for="">分包单位</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="cuName" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 clearboth">
			        <label class="control-label" for="">分包法人</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="cuLegalRepresent"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="">联系电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="cuPhone" />
			        </div>
			    </div>
			     <div class="form-group col-md-12 ">
			        <label class="control-label" for="">探伤单位</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="cdName" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 clearboth">
			        <label class="control-label" for="">负责人</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="cdResponsiblePerson"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="">联系电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="cdPhone" />
			        </div>
			    </div>
			    <!-- <div class="form-group col-md-12">
			        <label class="control-label" for="">设计单位</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="duName"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">设计负责人</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="duDirector"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">负责员</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  name="duDesigner"/>
			        </div>
			    </div> -->
			    
			</form>
    	</div>
    	<div class="form-group col-md-6 ">
    		<h3>人员信息</h3>
    		<form class="form-horizontal" id="detail_planEstablishform2" action="">
    			<div class="form-group col-md-6">
			        <label class="control-label" for="">质保师</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable"  name="managementQae">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">联系电话</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable"  name="qaeTel">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">材料员</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable"  name="managementWacf">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">联系电话</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable"  name="wacfTel">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">片区管理员</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable" name="areaManager">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">联系电话</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable"  name="managerTel">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">安全员</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable"  name="saftyOfficer" >
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">联系电话</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable"  name="saftyTel">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">质量技术员</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable" name="technician"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">联系电话</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable"  name="technicianTel">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">企管员</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable" name="businessAssistant" />
			        </div>
			    </div>
    			<div class="form-group col-md-6">
			        <label class="control-label" for="">联系电话</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable"  name="assistantTel">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">施工员</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable" name="builder" />
			        </div>
			    </div>
    			<div class="form-group col-md-6">
			        <label class="control-label" for="">联系电话</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable"  name="bulTel">
			        </div>
			    </div>
    		</form>
		    
    	</div>
    	
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
    $("#detail_planEstablishform,#detail_planEstablishform2").toggleEditState(false).styleFit();
    
    $.getScript('/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
		$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });
    
    var m = "id="+$("#projId").val();
    $.ajax({
        type: 'POST',
        url: '/projectView/projectDetailPlan',
        data: m,
        dataType: 'json',
        success: function (data) {
            //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
            var selects = $("#detail_planEstablishform").find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
            selects.removeAttr("disabled");
            //表单反序列化填充值
            $("#detail_planEstablishform,#detail_planEstablishform2").deserialize(data);
            $("#detail_planEstablishform,#detail_planEstablishform2").initFixedNumber();
            //有disabled属性的下拉菜单元素对象重新添加禁用属性
            selects.attr("disabled","disabled");
            $("#detail_planEstablishform,#detail_planEstablishform2").fadeIn(200);
            $('html, body').animate({
                scrollTop: $("body").offset().top
            }, 250);
            
            $("#cpArriveDate").val(data.cpArriveDate);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("getDetail() -> 详情查询失败");
            console.warn(jqXHR);
            console.warn(textStatus);
            console.warn(errorThrown);
        }
    });

 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->