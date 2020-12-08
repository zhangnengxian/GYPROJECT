<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<div class="infodetails">
    <div class="clearboth form-box">
    	<div class="form-group col-md-6 ">
    		<h3>计划信息</h3>
    		<form class="form-horizontal" id="detail_planEstablishform" action="">
				<div class="form-group  col-md-6 ">
			        <label class="control-label" for="">工程编号</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" />
			        </div>
			    </div>
			    
			    <div class="form-group col-md-12 ">
			        <label class="control-label" for="">工程名称</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" />
			        </div>
			    </div>
			    <%--<div class="form-group col-md-6 ">--%>
			        <%--<label class="control-label" for="">工程地点</label>--%>
			        <%--<div>--%>
			            <%--<input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" />--%>
			        <%--</div>--%>
			    <%--</div>--%>
			    <%--<div class="form-group col-md-12">--%>
			        <%--<label class="control-label" for="">工程规模</label>--%>
			        <%--<div>--%>
			            <%--<textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3"></textarea>--%>
			        <%--</div>--%>
			    <%--</div>--%>
			   <%--<div class="form-group col-md-6 ">--%>
			        <%--<label class="control-label" for="projectTypeDes">工程类型</label>--%>
			        <%--<div>--%>
			            <%--<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes"/>--%>
			        <%--</div>--%>
			    <%--</div>--%>
			    <%--<div class="form-group col-md-6 ">--%>
			        <%--<label class="control-label" for="contributionModeDes">出资方式</label>--%>
			        <%--<div>--%>
			            <%--<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes"/>--%>
			        <%--</div>--%>
			    <%--</div>--%>
			     <%--<div class="form-group col-md-6 ">--%>
			        <%--<label class="control-label" for="deptName">业务部门</label>--%>
			        <%--<div>--%>
			            <%--<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"/>--%>
			        <%--</div>--%>
			    <%--</div>--%>
			    <%--<div class="form-group col-md-6 clearboth">--%>
			        <%--<label class="control-label" for="custContact">用户联系人</label>--%>
			        <%--<div>--%>
			            <%--<input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact"/>--%>
			        <%--</div>--%>
			    <%--</div>--%>
			   	<%--<div class="form-group col-md-6">--%>
			        <%--<label class="control-label" for="custPhone">联系电话</label>--%>
			        <%--<div>--%>
			            <%--<input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone"/>--%>
			        <%--</div>--%>
			    <%--</div>--%>
			    <%--<div class="form-group col-md-6 clearboth">--%>
			        <%--<label class="control-label" for="duName">设计单位</label>--%>
			        <%--<div>--%>
			            <%--<input type="text" class="form-control input-sm field-not-editable"  id="duName" name="duName"/>--%>
			        <%--</div>--%>
			    <%--</div>--%>
			   	<%--<div class="form-group col-md-6">--%>
			        <%--<label class="control-label" for="duDesigner">设计员</label>--%>
			        <%--<div>--%>
			            <%--<input type="text" class="form-control input-sm field-not-editable"  id="duDesigner" name="duDesigner"/>--%>
			        <%--</div>--%>
			    <%--</div>--%>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">下达日期</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable datepicker-default timestamp" id="cpArriveDate"  name="" >
			        </div>
			    </div>
			     <div class="form-group col-md-6">
			        <label class="control-label" for="projTimeLimit">工期</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable " id="projTimeLimit"  name="projTimeLimit">
			           <a href="javascript:;" class="btn btn-sm btn-default">天</a>
			        </div>
			    </div>
			    <div class="form-group col-md-6 clearboth display-authority">
			        <label class="control-label" for="">合同金额</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="contractAmount" name="contractAmount"/>
						<a href="javascript:;" class="btn btn-sm btn-default">元</a>
					</div>
			    </div>
			    <div class="form-group col-md-6 display-authority">
			        <label class="control-label" for="firstPayment">应收首付款</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="firstPayment" name="firstPayment"/>
						<a href="javascript:;" class="btn btn-sm btn-default">元</a>
					</div>
			    </div>
			   	<div class="form-group col-md-6 display-authority">
			        <label class="control-label" for="">实收首付款</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="downPayment" name="downPayment"/>
						<a href="javascript:;" class="btn btn-sm btn-default">元</a>
					</div>
			    </div>
			   <div class="form-group col-md-6 clearboth">
			        <label class="control-label" for="">现场代表</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-not-editable" id="builder" name="builder" >
			        </div>
			    </div>
			    
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="">联系电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="bulTel" name="bulTel" data-parsley-maxlength="13" />
			        </div>
			    </div>
			    <div class="form-group col-md-12 ">
			        <label class="control-label" for="">监理单位</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName" data-parsley-maxlength="100" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="">监理负责人</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable"  id="suDirector" name="suDirector"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="">负责人电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="suPhone" name="suPhone" data-parsley-maxlength="13"/>
			        </div>
			    </div>
			    <div class="form-group col-md-12 col-sm-12 clearboth">
			        <label class="control-label" for="">施工单位</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" data-parsley-maxlength="100" />
			        </div>
			   </div>
			   <div class="form-group col-md-6">
			        <label class="control-label" for="">负责人</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable"  id="cuLegalRepresent" name="cuLegalRepresent"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">负责人电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="cuPhone" name="cuPhone" data-parsley-maxlength="20"/>
			        </div>
			    </div>
			    <div class="form-group col-md-12 ">
			        <label class="control-label" for="remark">备注</label>
			        <div>
			            <textarea rows="4" class="form-control input-sm field-editable"  id="remark" name="remark" data-parsley-maxlength="200"></textarea>
			        </div>
			    </div>
			</form>
    	</div>
    	<div class="form-group col-md-6 ">
    		<h3>人员信息</h3>
    		<form class="form-horizontal" id="detail_planEstablishform2" action="">
    			<h4>施工单位：</h4>
	    		<div class="form-group col-md-6 ">
					<label class="control-label" for="cuPm">项目经理</label>
					<div>
			           <input type="text" class=" form-control input-sm field-not-editable" id="cuPm" name="cuPm" >
			        </div>
				</div>
				 <div class="form-group col-md-6">
			        <label class="control-label" for="cuPmTel">联系电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="cuPmTel" name="cuPmTel" data-parsley-maxlength="13" />
			        </div>
			    </div>
				<div class="form-group col-md-6 clearboth">
			        <label class="control-label" for="managementQae">施工员</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-not-editable" id="mQae" name="managementQae" >
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="qaeTel">联系电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="qaeTel" name="qaeTel" data-parsley-maxlength="13"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 clearboth">
			        <label class="control-label" for="mWacf">材料员</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-not-editable" id="mWacf"  name="managementWacf">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="wacfTel">联系电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="wacfTel" name="wacfTel" data-parsley-maxlength="13"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="dcter">资料员</label>
		         <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="dcter"  name="documenter" >
		         </div>
		        </div>
		        <div class="form-group col-md-6">
		        <label class="control-label" for="documenterTel">联系电话</label>
		         <div>
		            <input type="text" class="form-control input-sm field-editable"  id="dcterTel" name="dcterTel" data-parsley-maxlength="13"/>
		         </div>
		        </div>
			    <div class="form-group col-md-6 clearboth">
			        <label class="control-label" for="sOfficer">安全员</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-not-editable " id="sOfficer"  name="saftyOfficer" >
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="saftyTel">联系电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="saftyTel" name="saftyTel" data-parsley-maxlength="13"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 clearboth">
			        <label class="control-label" for="tnician">质检员</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable"  id="tnician" name="technician"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="technicianTel">联系电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="technicianTel" name="technicianTel" data-parsley-maxlength="13"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 clearboth">
			        <label class="control-label" for="testLeader">班组长</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable"  id="testLeader" name="testLeader"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="welder">焊工</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable"  id="welder" name="welder"/>
			        </div>
			    </div>
			    <h4>监理单位：</h4>
				<div class="form-group col-md-6">
			        <label class="control-label" for="suCse">总监</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable" id="suCse" name="suCse" />
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="suCsePhone">联系电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="suCsePhone" name="suCsePhone" data-parsley-maxlength="13"/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 clearboth">
			        <label class="control-label" for="suJgj">现场监理师</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable" id="suJgj" name="suJgj" />
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="suJgjPhone">联系电话</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="suJgjPhone" name="suJgjPhone" data-parsley-maxlength="13"/>
			        </div>
			    </div>
	
				<div class="form-group col-md-6 clearboth">
					<label class="control-label" for="suProfessional">专业监理师</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" id="suProfessional" name="suProfessional" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="suProfessionalPhone">联系电话</label>
					<div>
						<input type="text" class="form-control input-sm field-editable"  id="suProfessionalPhone" name="suProfessionalPhone" data-parsley-maxlength="13"/>
					</div>
				</div>
	
				<div class="form-group col-md-6 clearboth">
					<label class="control-label" for="suRepresentative">总监代表</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" id="suRepresentative" name="suRepresentative" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="suRepresentativePhone">联系电话</label>
					<div>
						<input type="text" class="form-control input-sm field-editable"  id="suRepresentativePhone" name="suRepresentativePhone" data-parsley-maxlength="13"/>
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
    
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
		$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });
    
    var m = "id="+$("#projId").val();
    $.ajax({
        type: 'POST',
        url: 'projectView/projectDetailPlan',
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