<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="clearboth form-box">
	<form class="form-horizontal" id="problemDocumentSearchForm">
		<div class="form-group col-md-12 col-sm-12">
			<label class="control-label" for="corpId">公司名称：</label>
			<div>
				<select id="corpId" name="corpId" class="form-control input-sm field-editable" data-parsley-required="true">
					<option value="" ></option>
					<c:forEach var="department" items="${departmentList}">
						<option value="${department.deptId}" >${department.deptName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group col-md-6 col-sm-12 modularName ">
			<label class="control-label">模&nbsp;块：</label>
			<div>
				<select id="level2MenuIdPop" name="level2MenuId" class="form-control input-sm field-editable " onchange="loadLevel3MenuPop(this.value)" data-parsley-required="true">
					<option value="" ></option>
					<c:forEach var="level2Menu" items="${level2MenuList}">
						<option value="${level2Menu.menuId}" >${level2Menu.menuName}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group col-md-6 col-sm-12 modularName">
			<label class="control-label" >菜&nbsp;单：</label>
			<div>
				<select  id="level3MenuPop" name="menuId" class="form-control input-sm field-editable" data-parsley-required="true"></select>
			</div>
		</div>
		<div class="form-group col-md-6 col-sm-12">
			<label class="control-label" for="problemTypeCode">问题类型：</label>
			<div>
				<select id="problemTypeCode" name="problemTypeCode" onchange="problemTypeChange(this.value)" class="form-control input-sm field-editable"  data-parsley-required="true">
					<option value="" ></option>
					<c:forEach var="problemType" items="${problemTypeList}">
						<option value="${problemType.code}" >${problemType.desc}</option>
					</c:forEach>
				</select>
			</div>
		</div>


		<div class="form-group col-md-6 col-sm-12">
			<label class="control-label" for="problemStateCode">问题状态：</label>
			<div>
				<select id="problemStateCode" name="problemStateCode" class="form-control input-sm field-editable"  data-parsley-required="true">
					<option value="" ></option>
					<c:forEach var="problemState" items="${problemStateList}">
						<option value="${problemState.code}" >${problemState.desc}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group col-md-6 col-sm-12 custInfo">
			<label class="control-label">问题提出人：</label>
			<div>
				<input type="text" name="proposer" class="form-control input-sm field-editable" data-parsley-maxlength="20" data-parsley-required="false"/>
			</div>
		</div>

		<div class="form-group col-md-6 col-sm-12 custInfo">
			<label class="control-label">提出日期：</label>
			<div>
				<input type="text" name="proposeTime" class="form-control field-editable input-sm datepicker-default"  />
			</div>
		</div>

		<div class="form-group col-md-6 col-sm-12 custInfo">
			<label class="control-label" for="preprocessor">预处理人：</label>
			<div>
				<select id="preprocessor" name="preprocessor" class="form-control field-editable input-sm"  data-parsley-required="true">
					<option value="" ></option>
					<c:forEach var="handler" items="${handlerList}">
						<option value="${handler.code}" >${handler.desc}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group col-md-6 col-sm-12 custInfo">
			<label class="control-label">预处理日期：</label>
			<div>
				<input type="text" name="pretreatmentTime" class="form-control field-editable input-sm datepicker-default"  />
			</div>
		</div>

		<div class="form-group col-md-6 col-sm-12 custInfo">
			<label class="control-label">实处理人：</label>
			<div>
				<select id="actualHandler" name="actualHandler" class="form-control field-editable input-sm"  data-parsley-required="true">
					<option value="" ></option>
					<c:forEach var="handler" items="${handlerList}">
						<option value="${handler.code}" >${handler.desc}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group col-md-6 col-sm-12 custInfo">
			<label class="control-label">实处理日期：</label>
			<div>
				<input type="text" name="actualProcessingTime" class="form-control field-editable input-sm datepicker-default"  />
			</div>
		</div>

		<div class="form-group col-md-6 col-sm-12">
			<label class="control-label" for="emergencyLevelCode">紧急程度：</label>
			<div>
				<select id="emergencyLevelCode" name="emergencyLevelCode" class="form-control input-sm field-editable"  data-parsley-required="true">
					<option value="" ></option>
					<c:forEach var="emergencyLevel" items="${emergencyLevelList}">
						<option value="${emergencyLevel.code}" >${emergencyLevel.desc}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group col-md-6 col-sm-12">
			<label class="control-label">工程编号：</label>
			<div>
				<input type="text" name="projNo" class="form-control input-sm field-editable" data-parsley-maxlength="20" data-parsley-required="false"/>
			</div>
		</div>

		<div class="form-group col-md-12 col-sm-12">
			<label class="control-label">登记日期：</label>
			<div>
				<input type="text" name="registLtTime" class="form-control field-editable input-sm datepicker-default"  />
				&nbsp;至&nbsp;
				<input type="text" name="registGtTime" class="form-control field-editable input-sm datepicker-default" />
			</div>
		</div>

		<div class="form-group col-md-12 col-sm-12">
			<label class="control-label">预处理日期：</label>
			<div>
				<input type="text" name="preLtTime" class="form-control field-editable input-sm datepicker-default"  />
				&nbsp;至&nbsp;
				<input type="text" name="preGtTime" class="form-control field-editable input-sm datepicker-default" />
			</div>
		</div>

		<div class="form-group col-md-12 col-sm-12">
			<label class="control-label">实际处理日期：</label>
			<div>
				<input type="text" name="actualLtTime" class="form-control field-editable input-sm datepicker-default"  />
				&nbsp;至&nbsp;
				<input type="text" name="actualGtTime" class="form-control field-editable input-sm datepicker-default" />
			</div>
		</div>

	</form>
</div>

<script>
    App.restartGlobalFunction();
    $("#problemDocumentSearchForm").styleFit();

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });


    function loadLevel3MenuPop(value) {
        Base.subimt('problemDocument/findChildMenuList','POST',{'menuId':value},function (data) {
            $("#level3MenuPop").find("option").remove();//加载时先清空option;还可以使用$("#level3Menu").empty();方法清空
            $("#level3MenuPop").append('<option ></option>');
            $.each(JSON.parse(data),function(n,v){
				$("#level3MenuPop").append('<option value='+v.menuId+'>' + v.menuName + '</option>');
            });
        })
    }
</script>
