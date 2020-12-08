<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
		<div class="toolBtn f-r p-b-10  editBtn hidden">
			<button type="button" onclick="saveClick()" class="btn btn-success btn-sm">保存</button>
			<button type="button" onclick="cancelClick()" class="btn btn-danger btn-sm">取消</button>
		</div>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="supplementApplyForm" data-parsley-validate="true" >
       		<input type="hidden" id="projId" name="projId" placeholder="工程ID"/>
			<input type="hidden" id="isStatus" name="isStatus" value="0" placeholder="审核状态"/>
			<input type="hidden" id="flag" name="flag" value="0" placeholder="新增"/>
			<input type="hidden" id="imcId" name="imcId"  placeholder="智能表合同Id"/>

			<div class="form-group  col-md-6 ">
		        <label class="control-label" for="imcNo">合同编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="imcNo" name="imcNo" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" >工程规模</label>
		        <div>
		        	<textarea class="form-control input-sm field-not-editable" name="projScaleDes" rows="4" ></textarea>
		        </div>
		    </div>
			<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    		 <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
 				<label class="control-label" for="">变更原因</label>
				<div>
     				<textarea class="form-control field-editable" name="changeReasons" id="changeReasons" rows="4" data-parsley-required="true" ></textarea>
    			</div>
   		    </div>
			<div class="form-group col-md-6 col-sm-12  clearboth">
				<label class="control-label" for="">申请日期</label>
				<div>
					<input type="text" id="changeTime" name="changeTime" value="${changeTime}"  class="form-control input-sm datepicker-default field-not-editable" />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 ">
				<label class="control-label" for="">申请人</label>
				<div>
					<input type="text" id="agent" name="agent"  value="${agent}"   class="form-control input-sm  field-not-editable" />
				</div>
			</div>
		</form>
    </div>
</div>





<script>
    App.restartGlobalFunction();
    $('.infodetails').hideMask();
    $('#supplementApplyForm').toggleEditState(false).styleFit();//表单样式适应
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    trSData.t&&trSData.t.cgetDetail('supplementApplyForm','intelligentSupplement/viewContractDetail','',function () {});

    function cancelClick() {
        $("#supplementApplyForm").toggleEditState(false);
        $(".editbtn").addClass("hidden");
        $('#contractTable').cgetData(true);
    }


    function saveClick() {
        var pf = $("#supplementApplyForm");
        if (pf.parsley().isValid()) { //验证必填是否已填写
            $("#supplementApplyForm").loadMask("保存中···", 1, 0.5);
            var formData = $("#supplementApplyForm").serializeJson();
            Base.subimt('intelligentSupplement/saveIntelligentSupplement', 'POST', JSON .stringify(formData), function(data) {

                var content = data ? "保存成功！" : "保存失败！";
                tips(content);
                $("#supplementApplyForm").hideMask();
                $("#contractTable").cgetData(true);//列表重新加载
            })
        } else {
            pf.parsley().validate();
        }
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


</script>
