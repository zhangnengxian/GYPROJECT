<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/projectjs/maintain/wangEditor.js"></script>


<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editBtn hidden">
		<button type="button" onclick="saveClick()"
			class="btn btn-success btn-sm">保存</button>
		<button type="button" onclick="cancelClick()"
			class="btn btn-danger btn-sm">取消</button>
	</div>

	<div class="clearboth form-box">

		<form id="problemDocumentForm" class="form-horizontal"
			data-parsley-validate="true" action="">
			<input type="hidden" id="pdId" name="pdId" value="" />

			<div class="form-group col-md-12 col-sm-12">
				<label class="control-label" for="corpId">公司名称：</label>
				<div>
					<select id="corpId" name="corpId"
						class="form-control input-sm field-editable controlEdit"
						data-parsley-required="true">
						<c:forEach var="department" items="${departmentList}">
							<option value="${department.deptId}">${department.deptName }</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group col-md-6 col-sm-12 ">
				<label class="control-label" for="problemTypeCode">问题类型：</label>
				<div>
					<select id="problemTypeCode" name="problemTypeCode"
						onchange="problemTypeChange(this.value)"
						class="form-control input-sm field-editable controlEdit"
						data-parsley-required="true">
						<option value=""></option>
						<c:forEach var="problemType" items="${problemTypeList}">
							<option value="${problemType.code}">${problemType.desc}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group col-md-6 col-sm-12 projNo hidden">
				<label class="control-label">工程编号：</label>
				<div>
					<input type="text"
						class="form-control input-sm field-editable controlEdit"
						name="projNo" />
				</div>
			</div>

			<div class="form-group col-md-6 col-sm-12 modularName hidden">
				<label class="control-label">模&nbsp;块：</label>
				<div>
					<select id="level2MenuId" name="level2MenuId"
						class="form-control input-sm field-editable controlEdit"
						onchange="loadLevel3Menu(this.value)" data-parsley-required="true">
						<option value=""></option>
						<c:forEach var="level2Menu" items="${level2MenuList}">
							<option value="${level2Menu.menuId}">${level2Menu.menuName}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group col-md-6 col-sm-12 modularName hidden">
				<label class="control-label" for="level3Menu">菜&nbsp;单：</label>
				<div>
					<select id="level3Menu" name="menuId"
						class="form-control input-sm field-editable controlEdit"
						data-parsley-required="true"></select>
				</div>
			</div>

			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="problemStateCode">问题状态：</label>
				<div>
					<select id="problemStateCode" name="problemStateCode"
						class="form-control field-editable input-sm"
						data-parsley-required="true">
						<option value=""></option>
						<c:forEach var="problemState" items="${problemStateList}">
							<option value="${problemState.code}">${problemState.desc}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group col-md-6 col-sm-12 custInfo">
				<label class="control-label">问题提出人：</label>
				<div>
					<input type="text" name="proposer"
						class="form-control input-sm field-editable controlEdit"
						data-parsley-maxlength="20" data-parsley-required="false" />
				</div>
			</div>

			<div class="form-group col-md-6 ">
				<label class="control-label">提出日期：</label>
				<div>
					<input type="text" name="proposeTime"
						class="form-control field-editable input-sm datepicker-default controlEdit" />
				</div>
			</div>

			<div class="form-group col-md-6 col-sm-12 custInfo">
				<label class="control-label" for="preprocessor">预处理人：</label>
				<div>
					<select id="preprocessor" name="preprocessor"
						class="form-control field-editable input-sm"
						data-parsley-required="true">
						<option value=""></option>
						<c:forEach var="handler" items="${handlerList}">
							<option value="${handler.code}">${handler.desc}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group col-md-6 ">
				<label class="control-label">预处理日期：</label>
				<div>
					<input type="text" name="pretreatmentTime"
						class="form-control field-editable input-sm datepicker-default controlEdit"
						data-parsley-required="true" />
				</div>
			</div>

			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="emergencyLevelCode">紧急程度：</label>
				<div>
					<select id="emergencyLevelCode" name="emergencyLevelCode"
						class="form-control input-sm field-editable controlEdit"
						data-parsley-required="true">
						<option value=""></option>
						<c:forEach var="emergencyLevel" items="${emergencyLevelList}">
							<option value="${emergencyLevel.code}">${emergencyLevel.desc}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group col-md-6 ">
				<label class="control-label">登记日期：</label>
				<div>
					<input type="text" id="registrationTime" name="registrationTime"
						class="form-control field-not-editable input-sm datepicker-default" />
				</div>
			</div>

			<div class="form-group col-md-12 col-sm-12">
				<label class="control-label" for="remarks">问题描述：</label>
				<div>
					<textarea id="remarks" name="remarks"
						class="form-control field-editable controlEdit" rows="6"
						></textarea>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 custInfo">
				<label class="control-label">实处理人：</label>
				<div>
					<select id="actualHandler" name="actualHandler"
						class="form-control field-editable input-sm">
						<option value=""></option>
						<c:forEach var="handler" items="${handlerList}">
							<option value="${handler.code}">${handler.desc}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group col-md-6 ">
				<label class="control-label">实处理日期：</label>
				<div>
					<input type="text" name="actualProcessingTime"
						class="form-control field-editable input-sm datepicker-default" />
				</div>
			</div>

			<div class="form-group col-md-12 col-sm-12">
				<label class="control-label" for="remarks">解决方案：</label>
				<div>
					<textarea id="solution" name="solution"
						class="form-control field-editable" rows="6"
						></textarea>
				</div>
			</div>
			<div class="form-group col-md-12 col-sm-12">
				<div id="editor" style="width: 100%"></div>
			</div>

		</form>

	</div>
</div>


<script>
	App.restartGlobalFunction();
	$(".infodetails").hideMask();//隐藏loading
	$("#problemDocumentForm").toggleEditState(false).styleFit();
	$('.datepicker-default').datepicker({
		todayHighlight : true
	});//日期控件

	trSData.t&&trSData.t.cgetDetail('problemDocumentForm','problemDocument/viewProblemDocumentDetail', '',detailCallback);//第一次加载显示详细

	var E = window.wangEditor;
	var editor = new E('#editor');
	editor.customConfig.uploadImgServer = 'problemDocument/uploadImg';
	editor.customConfig.uploadFileName = 'img';
	editor.customConfig.uploadImgMaxSize = 5 * 1024 * 1024; // 将图片大小限制为 3
	editor.customConfig.onchange = function(html) {
		$(".w-e-text,.w-e-text-container").height(document.body.offsetHeight);
	};
	editor.create();

	function loadLevel3Menu(value, menuId) {

		Base.subimt('problemDocument/findChildMenuList', 'POST', {'menuId' : value}, function(data) {

			$("#level3Menu").find("option").remove();//加载时先清空option;还可以使用$("#level3Menu").empty();方法清空
			$("#level3Menu").append('<option ></option>');

			$.each(JSON.parse(data), function(n, v) {
				if (v.menuId == menuId) {
					$("#level3Menu").append('<option selected=true value='+v.menuId+'>'+ v.menuName + '</option>');
				} else {
					$("#level3Menu").append('<option value='+v.menuId+'>' + v.menuName+ '</option>');
				}
			});
		})
	}

	function problemTypeChange(value) {
		if (value == 5) {//问题为数据修改
			$('.projNo,.modularName').removeClass("hidden");
		} else if (value == 6 || value == 7) {
			$('.modularName,.projNo').addClass("hidden");
		} else {
			$('.projNo').addClass("hidden");
			$('.modularName').removeClass("hidden");

		}
	}

	function cancelClick() {
		$("#problemDocumentForm").toggleEditState(false);
		$(".editbtn").addClass("hidden");
		$('#problemDocumentTable').cgetData(true);
	}

	function saveClick() {
		var pf = $("#problemDocumentForm");
		if (pf.parsley().isValid()) { //验证必填是否已填写
			$("#problemDocumentForm").loadMask("保存中···", 1, 0.5);
			Base.subimt('problemDocument/saveProblemDocument', 'POST', JSON.stringify(getformObj()), function(data) {
				var content = data ? "保存成功！" : "保存失败！";
				tips(content);
				$("#problemDocumentForm").hideMask();
				$("#problemDocumentTable").cgetData(true);//列表重新加载
			})
		} else {
			pf.parsley().validate();
		}
	}

	function getformObj() {
		$(".controlEdit").attr("disabled", false);
		var formData = $("#problemDocumentForm").serializeJson();
		//formData.corpName=$('#corpId option:selected').text();
		formData.editorField = $(".w-e-text").html();
		return formData;
	}

	function tips(content) {
		var myoptions = {
			title : "提示信息",
			content : content,
			accept : popClose,
			chide : true,
			icon : "fa-check-circle"
		};
		$("body").cgetPopup(myoptions);
	}

	var detailCallback = function(data) {
		loadLevel3Menu(data.level2MenuId, data.menuId);
		problemTypeChange($('#problemTypeCode').val());
		$('#level3Menu').val(data.menuId);
		$(".w-e-text").html(data.editorField);
		$(".w-e-text,.w-e-text-container").height(
				document.body.offsetHeight - 100);

	};
</script>
