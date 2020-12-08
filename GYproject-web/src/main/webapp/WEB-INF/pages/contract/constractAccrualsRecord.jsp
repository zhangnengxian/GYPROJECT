<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="ar_contract_Form" action="/" method="POST">
        <div class="form-group col-md-12">
            <label class="control-label">客户名称</label>
            <div >
                <input type="text" class="form-control input-sm field-not-editable"  name="projCustName" value="${accrualsRecord.projCustName}"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label">收付标志</label>
            <div>
                <input type="text" class="form-control input-sm field-not-editable"  name="arFlagDes" value="${accrualsRecord.arFlagDes}"/>
            </div>
        </div>
         <div class="form-group col-md-6">
            <label class="control-label">款项类型</label>
            <div >
                <input type="text" class="form-control input-sm field-not-editable"  name="arTypeDes" value="${accrualsRecord.arTypeDes}"/>
            </div>
        </div>
         <div class="form-group col-md-6">
            <label class="control-label" for="arCost">金额</label>
            <div >
                <input type="text" class="form-control input-sm field-not-editable text-right fixed-number"  id="arCost" name="arCost" value="${accrualsRecord.arCost}"/>
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#ar_contract_Form").styleFit();
    $("#ar_contract_Form").toggleEditState(false);
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->