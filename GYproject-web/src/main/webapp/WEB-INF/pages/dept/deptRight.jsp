<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== END PAGE LEVEL STYLE ================== -->
<div class="toolBtn f-r p-b-10 scbtn">
    <a href="javascript:;" class="btn btn-query btn-sm m-l-5 saveBtn">保存</a>
    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
</div>
<div class="form-box clearboth">
	<form class="form-horizontal" id="deptManageform">
		<input type="hidden"  name="orgId" id="orgId">
		<input type="hidden" name="tenantId" id="tenantId"/>
		<div class="form-group col-md-6">
			<label class="control-label" for="deptOutCode">部门编号</label>
			<div>
				<input type="hidden"  name="deptOutCode" id="deptOutCode">
				<input class="form-control input-sm field-editable"  name="deptInnerCode" id="deptInnerCode">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="deptName">部门名称</label>
			<div>
				<input class="form-control input-sm field-editable"  name="deptName" id="deptName">
			</div>
		</div>
		<div class="form-group col-md-6" id="deptTypeDiv">
			<label class="control-label" for="deptTypeName">部门类型</label>
			<div>
				<input class="form-control input-sm field-editable"  name="deptTypeName" id="deptTypeName">
				<input type="text" class="form-control input-sm hidden"  id="deptType" name="deptType"  />
			</div>
		</div>
		<div class="form-group col-md-6" id="businessTypeDiv">
			<label class="control-label" for="businessType">业务类型</label>
			<div>
	    		<select class="form-control input-sm field-editable a2" id="businessType"  name="businessType" >
	 		      	<option></option>
	                <c:forEach var="enums" items="${businessType}">
						   	<option value="${enums.value}">${enums.message}</option>
	                </c:forEach>
	             </select>
	        </div>
		</div>
		<%-- <div class="form-group col-md-6" id="">
			<label class="control-label" for="projContructType">工程建设类型</label>
			<div>
	    		<select class="form-control input-sm field-editable" id="projContructType"  name="projContructType" >
	 		      	<option></option>
	                <c:forEach var="enums" items="${projContructType}">
						   	<option value="${enums.value}">${enums.message}</option>
	                </c:forEach>
	             </select>
	        </div>
		</div> --%>
		<div class="form-group col-md-6" id="">
			<label class="control-label" for="deptDivide">部门划分</label>
			<div>
	    		<select class="form-control input-sm field-editable" id="deptDivide"  name="deptDivide" >
	 		      	<option></option>
	                <c:forEach var="enums" items="${deptDivide}">
						   	<option value="${enums.value}">${enums.message}</option>
	                </c:forEach>
	             </select>
	        </div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="location">所在地点</label>
			<div>
				<input class="form-control input-sm field-editable"  name="location" id="location">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="principal">负责人</label>
			<div>
				<input class="form-control input-sm field-editable"  name="principal" id="principal">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="phone">联系电话</label>
			<div>
				<input class="form-control input-sm field-editable"  name="phone" id="phone" >
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="fax">传真号</label>
			<div>
				<input class="form-control input-sm field-editable"  name="fax" id="fax" data-parsley-maxlength='[8]'>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="fax">网络平台受理部门</label>
			<div>
				<select class="form-control input-sm field-editable" id="isAcceptDept"  name="isAcceptDept" >
	 		      	<option ></option>
	 		      	<option value="0">否</option>
	                <option value="1">是</option>
	             </select>
			</div>
		</div>
		<div class="form-group col-md-12">
			<div id="menuTree"></div>
		</div>
		 <label class="control-label hidden" for="deptId">部门id</label>
		 <input type="text" class="form-control input-sm hidden"  id="deptId" name="deptId"  />
		 <label class="control-label hidden" for="parentDeptId">父节点部门id</label>
		 <input type="text" class="form-control input-sm hidden"  id="parentDeptId" name="parentDeptId"  />
		 <label class="control-label hidden" for="parentDeptType">父节点部门类型</label>
		 <input type="text" class="form-control input-sm hidden"  id="parentDeptType" name="parentDeptType"  />
	</form>
</div>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    $("#deptManageform").hideMask();
    //参数传false时，表单不可编辑
    $("#deptManageform").toggleEditState(false);
    //表单样式适应
    $("#deptManageform").styleFit();
    //按钮隐藏
    $(".scbtn").addClass("hidden");
</script>
<!-- ================== END PAGE LEVEL JS ================== -->