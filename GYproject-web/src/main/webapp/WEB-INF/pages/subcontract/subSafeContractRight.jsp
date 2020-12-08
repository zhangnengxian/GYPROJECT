<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton temporarySaveBtn1" >暂存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn1">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="subSafeDetailform" data-parsley-validate="true" action="">
       		<input type="hidden" id="projId1" name="projId"/>
       		<input type="hidden" id="ssId" name="ssId"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="projNo1">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo1" name="projNo" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName1">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName1" name="projName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr1">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr1" name="projAddr" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projCompPm1">甲方名称</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="projCompPm1" name="projCompPm" data-parsley-maxlength="20"  value="" />
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projCompDirector1">甲方委托代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projCompDirector1" name="projCompDirector" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasComLegalRepresent1">甲方代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasComLegalRepresent1" name="gasComLegalRepresent" data-parsley-maxlength="20"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="gasComPhone1">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasComPhone1" name="gasComPhone" data-parsley-maxlength="13" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="cuName1">分包单位</label>
		        <div>
		            <input type="hidden" id="cuId1" name="cuId" data-parsley-maxlength="100"  value="" />
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName1" name="cuName" data-parsley-maxlength="200"  value="" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuLegalRepresent1">乙方法定代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuLegalRepresent1" name="cuLegalRepresent" data-parsley-maxlength="20"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuDirector1">乙方委托代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuDirector1" name="cuDirector" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuResponsiblePerson">现场负责人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuResponsiblePerson" name="cuResponsiblePerson" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cuPmPhone1">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuPmPhone1" name="cuPmPhone" data-parsley-maxlength="13" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="safeManager">安全管理员</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="safeManager" name="safeManager" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="ssAccount">注册账号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="ssAccount" name="ssAccount" data-parsley-maxlength="200" value=''/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="signAddr">签订地址</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="signAddr" name="signAddr" data-parsley-maxlength="200" value="乌鲁木齐市新医路8号"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="ssSignDate">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default timestamp" id="ssSignDate"  name="ssSignDate" data-parsley-maxlength="100" value="">
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
    $('.infodetails').hideMask();
    $('.editbtn').addClass('hidden');
    //表单样式适应
    $('#subSafeDetailform').toggleEditState().styleFit();
    changeAText();
  	//查右侧工程详述
    trSData.t&&trSData.t.cgetDetail('subSafeDetailform','subContract/viewSubSafe','',cgetDetailBack2); 
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    /**点击右侧维护区 暂存 按钮时*/
    $('.temporarySaveBtn1').on('click',function(){
        if($('#subSafeDetailform').parsley().isValid()){
        	var data=$('#subSafeDetailform').serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'subContract/saveSubSafe',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else{
                		 $(".editbtn").addClass("hidden");
                		 $('#subSafeDetailform').toggleEditState().styleFit();
                	}
                		alertInfo('数据保存成功！');
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("安全协议签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#subSafeDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#subSafeDetailform").parsley().validate();
        }
    }); 
    //放弃
    $(".cancelBtn1").on("click",function(){
    	$("#subSafeDetailform input").val('');
    	trSData.t.cgetDetail('subSafeDetailform','subContract/viewSubSafe','',cgetDetailBack2); 
    	$("#subSafeDetailform").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["subSafeDetailform"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#subSafeDetailform").parsley().reset();
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->