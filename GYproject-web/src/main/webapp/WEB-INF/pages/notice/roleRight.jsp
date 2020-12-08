<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== END PAGE LEVEL STYLE ================== -->
<div class="toolBtn f-r p-b-10 scbtn">
    <a href="javascript:;" class="btn btn-query btn-sm m-l-5 saveBtn">保存</a>
    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
</div>
<div class="form-box clearboth">
	<input type="hidden" class="corpId" value="${corpId}">
	<form class="form-horizontal" id="roleManageform" data-parsley-validate="true">
		<div class="form-group col-md-6">
			<label class="control-label" for="corp">分公司</label>
			<div>
				<select id="corpId" name="corpId" class="form-control input-sm field-not-editable">
					<option value="" ></option>
					<c:forEach var="enums" items="${corp}">
						<option value="${enums.deptId}" >${enums.deptName}</option>
					</c:forEach>
				</select>
		   </div>
		 </div> 
		<div class="form-group col-md-6 clearboth">
			<label class="control-label" for="nrCode">角色编号</label>
			<div>
				<input class="form-control input-sm field-not-editable"  name="nrCode" id="nrCode" data-parsley-required="true">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="nrName">角色名称</label>
			<div>
				<input class="form-control input-sm field-editable"  name="nrName" id="nrName" data-parsley-required="true">
			</div>
		</div>
		<div class="form-group col-md-12">
			<label class="control-label" ></label>
			<div id="menuTreeRoleManage"></div>
		</div>
		 <label class="control-label hidden" for="nrId">角色id</label>
		 <input type="text" class="form-control input-sm hidden"  id="nrId" name="nrId"  />
	     <label class="control-label hidden" for="createTime">创建时间</label>
		 <input type="text" class="form-control input-sm hidden"  id="createTime" name="createTime"  />
		 <input type="text" class="form-control input-sm hidden"  id="createStaffId" name="createStaffId"  />
	</form>
</div>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    $("#roleManageform").hideMask();
    //参数传false时，表单不可编辑
    $("#roleManageform").toggleEditState(false);
    //表单样式适应
    $("#roleManageform").styleFit();
    //按钮隐藏
    $(".scbtn").addClass("hidden");
    
    if($(".corpId").val()!=""){
    	var corpId=$(".corpId").val();
    	$("#roleManageform option[value='"+corpId+"']").attr("selected","selected");
    }
</script>
<!-- ================== END PAGE LEVEL JS ================== -->