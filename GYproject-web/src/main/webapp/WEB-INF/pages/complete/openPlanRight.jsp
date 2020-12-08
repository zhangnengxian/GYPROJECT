<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <%--<div class="toolBtn f-r p-b-10  editbtn">--%>
    	 <%--<a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 saveBtn" >保存</a>--%>
         <%--<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>--%>
	<%--</div>--%>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="gasPlanForm" action="" data-parsley-validate="true">
			<input class="hidden" name="gpId" id="gpId">
			<input class="hidden" name="corpId" id="corpId">
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="gpNo">计划编号</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"  id="gpNo" name="gpNo"  data-parsley-maxlength="50" value=""/>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="gpName">计划名称</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"  id="gpName" name="gpName"  data-parsley-maxlength="50" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="creater">编制人</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"  id="creater" name="creater"  data-parsley-maxlength="50" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label">编制日期</label>
				<div>
					<input type="text" class="form-control input-sm field-editable datepicker-default"  id="createDate" name="createDate" data-parsley-validate="true"/>
				</div>
			</div>
			<div class="form-group col-md-12  ">
				<label class="control-label" for="remark">备注</label>
				<div>
					<textarea class="form-control field-editable feedBackInputShow" name ="remark" id="remark" rows="4" data-parsley-maxlength="1000"></textarea>
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
    $("#gasPlanForm").toggleEditState(false);
    //表单样式适应
    $("#gasPlanForm").styleFit();
    $(".editbtn").addClass("hidden")

    trSData.gasPlanTable.t && trSData.gasPlanTable.t.cgetDetail('gasPlanForm','','',gasPlanBack);

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->