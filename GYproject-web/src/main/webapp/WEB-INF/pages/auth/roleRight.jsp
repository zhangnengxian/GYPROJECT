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
	<input type="hidden" class="groupCompany" value="${groupCompany}">
	<form class="form-horizontal" id="roleManageform" data-parsley-validate="true">
		 <label class="control-label hidden" for="roleId">角色id</label>
		 <input type="text" class="form-control input-sm hidden"  id="roleId" name="roleId"  />
	     <label class="control-label hidden" for="createTime">创建时间</label>
		 <input type="text" class="form-control input-sm hidden"  id="createTime" name="createTime"  />
		<div class="form-group col-md-6">
			<label class="control-label" for="corp">分公司</label>
			<div>
				<select id="corpId" name="corpId" class="form-control input-sm field-editable">
					<option value="0" >全局</option>
					<c:forEach var="enums" items="${corp}">
						<option value="${enums.deptId}" >${enums.deptName}</option>
					</c:forEach>
				</select>
		   </div>
		 </div> 
		<div class="form-group col-md-6 clearboth">
			<label class="control-label" for="roleCode">角色编号</label>
			<div>
				<input class="form-control input-sm field-not-editable"  name="roleCode" id="roleCode" data-parsley-required="true">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="roleName">角色名称</label>
			<div>
				<input class="form-control input-sm field-editable"  name="roleName" id="roleName" data-parsley-required="true">
			</div>
		</div>
		
		<ul class="nav nav-tabs p-t-5 p-l-5 clearboth">
			<li class="active"><a href="#tab-1" data-toggle="tab" id="workRole">菜单配置</a></li>
			<li class=""><a href="#tab-2" data-toggle="tab" id="noticeRole">部门配置</a></li>
		</ul>
		
		<div class="tab-content p-l-0 p-r-0 p-t-5">
			<div class="tab-pane fade active in btn-top" id="tab-1" >
				<div class="form-group col-md-12">
					<label></label>
					<div id="menuTreeRoleManage">
					</div>
				</div>
			</div>
			
			<div class="tab-pane fade  btn-top" id="tab-2" >
				<div class="form-group col-md-12">
					<label></label>
					 <div id="deptTree"></div>
					</div>
				</div>
			</div>
		</div>
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