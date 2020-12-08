<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 acceptSaveBtn" >保存</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">确定</a>
	</div>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="accrualsRecordForm">
			<input type="hidden" id="arId" name="arId" value="${accrualsRecord.arId}"/>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">款项类型</label>
				<div>
					<select class="form-control input-sm field-not-editable" id="arType" name="arType" value="${accrualsRecord.arType}">
						<option value=""></option>
						<c:forEach var="enums" items="${arType}">
							<option value="${enums.value}">${enums.message}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">收付标志</label>
				<div>
					<select class="form-control input-sm field-not-editable" id="arFlag" name="arFlag"  value="${accrualsRecord.arFlag}">
						<option value=""></option>
						<c:forEach var="enums" items="${arType}">
							<option value="${enums.value}">${enums.message}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">金额</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="300" id="amount" name="amount" value="${accrualsRecord.amount}"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">操作时间</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" value="" id="arDate" name="arDate"  value="${accrualsRecord.arDate}"/>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="">备注</label>
				<div>
					<textarea class="form-control field-editable" id="arNote" name="arNote" rows="4" cols="" >${accrualsRecord.arNote}</textarea>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
	//隐藏loading
	$(".infodetails").hideMask();
	//参数传false时，表单不可编辑
	$("#accrualsRecordForm").toggleEditState(true).styleFit();
	/* var arId = $("#arId").val();
	var url="";
	var f = $("#accrualsRecordForm");
	$.ajax({
        type: 'POST',
        url: url,
        data: "arId="+arId,
        dataType: 'json',
        success: function (data) {
            //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
            var selects = f.find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
            selects.removeAttr("disabled");
            //表单反序列化填充值
            f.deserialize(data);
            f.initFixedNumber();
            //有disabled属性的下拉菜单元素对象重新添加禁用属性
            selects.attr("disabled","disabled");
            f.fadeIn(200);
            $('html, body').animate({
                scrollTop: $("body").offset().tops
            }, 250);
            f.parents(".panel-inverse").find(".panel-heading").hideloading();
        }
    }); */
</script>
<!-- ================== END PAGE LEVEL JS ================== -->