<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="subContractDetailform" data-parsley-validate="true" action="">
       		<input  type="hidden" id="cuUnitTypeId" name="cuUnitTypeId" value="${cuUnitTypeId}"/>
            <input  type="hidden" id="deptTypeId" name="deptTypeId" value="${deptTypeId}"/>
       		<input type="hidden" id="projId" name="projId"/>
       		<input type="hidden" id="projNo" name="projNo"/>
       		<input type="hidden" id="sscType" name="sscType"/>
       		<input type="hidden" id="sscAmount" name="sscAmount"/>
       		<input type="hidden" id="sscaName" name="sscaName"/>
       		<input type="hidden" id="sscbName" name="sscbName"/>
       		<input type="hidden" id="sscScope" name="sscScope"/>
       		<div class="form-group col-md-12">
		        <label class="control-label" for="sscNo">补充协议编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscNo" name="sscNo" data-parsley-maxlength="200" value=""  data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="deptName">工程承包人</label>
		        <div>
		       		<input type="hidden"  id="deptId" name="deptId" data-parsley-maxlength="20"  value="" />
		       		<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" data-parsley-maxlength="50"  value="" />
		        	<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="工程承包人"><i class="fa fa-search"></i></a>
		       		
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="cuName">劳务分包人</label>
		        <div>
		            <input type="hidden" id="cuId" name="cuId" data-parsley-maxlength="100"  value="" />
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" data-parsley-maxlength="200"  value="" />
		        	<a id="cuPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="劳务分包人"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projScaleDes">工程内容</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="scScope">承包范围</label>
	            <div>
	                <textarea class="form-control field-editable" name ="scScope" id="scScope" rows="3" data-parsley-maxlength="200"></textarea>
	            </div>
            </div>
            <div class="form-group col-md-12">
		        <label class="control-label" for="scType">承包方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="scType" name="scType" data-parsley-maxlength="200" data-parsley-required="true" value=''/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscPlannedStartDate">开工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="sscPlannedStartDate"  name="sscPlannedStartDate" data-parsley-maxlength="100" value=""  data-parsley-required="true">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscPlannedEndDate">竣工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="sscPlannedEndDate"  name="sscPlannedEndDate" data-parsley-maxlength="100" value=""  data-parsley-required="true">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscPlannedTotalDays">日历天数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="sscPlannedTotalDays"  name="sscPlannedTotalDays" data-parsley-maxlength="10"  value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscQualityStandar">工程质量标准</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscQualityStandar" name="sscQualityStandar" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="endDeclarationCost">金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable fixed-number"  id="endDeclarationCost" name="endDeclarationCost" data-parsley-maxlength="13" value=""  data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="sscaCompPm">甲方项目经理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscaCompPm" name="sscaCompPm" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="sscbCompPm">乙方工地代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscbCompPm" name="sscbCompPm" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscaLegalRepresent">甲方法定代表人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscaLegalRepresent" name="sscaLegalRepresent" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscbLegalRepresent">乙方法定代表人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscbLegalRepresent" name="sscbLegalRepresent" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscaAddr">甲方地址</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscaAddr" name="sscaAddr" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscbAddr">乙方地址</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscbAddr" name="sscbAddr" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscaDirector">甲方委托代理人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscaDirector" name="sscaDirector" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscbDirector">乙方委托代理人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscbDirector" name="sscbDirector" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscaSoptLeader">甲方现场负责人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscaSoptLeader" name="sscaSoptLeader" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscbSoptLeader">乙方现场负责人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscbSoptLeader" name="sscbSoptLeader" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscaPhone">甲方电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscaPhone" name="sscaPhone" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscbPhone">乙方电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscbPhone" name="sscbPhone" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="sscSignDate">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="sscSignDate"  name="sscSignDate" data-parsley-maxlength="100" value=""  data-parsley-required="true">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="sscConAgent">经办人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sscConAgent" name="sscConAgent" data-parsley-maxlength="20" value="${loginInfo.staffName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="remark">备注</label>
	            <div>
	                <textarea class="form-control field-editable" name ="remark" id="remark" rows="2" data-parsley-maxlength="200"></textarea>
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
    $('#subContractDetailform').toggleEditState().styleFit();
  	//查右侧工程详述
    trSData.t&&trSData.t.cgetDetail('subContractDetailform','subAgreement/viewSubContract','',cgetDetailBack); 
  	
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
  	
    var cuUnitTypeId=$('#cuUnitTypeId').val();
    //选中分包单位
    $("#cuPop").off('click').on("click",function(){
		businessPartnersPopup({
    		'cuId':'unitId',
    		'cuName':'unitName',
    		'cuLegalRepresent':'unitDirector',
    		'cuPm':'unitManager',
    		'cuPmPhone':'unitMobile',
    		'certificationName':'qualificationName',
    		'certificationNo':'qualificationNo'
    	},cuUnitTypeId)

  	});
    
    var deptTypeId=$('#deptTypeId').val();
    //选中甲方
    $("#managePop").off('click').on("click",function(){
    	deptPopup({
    		'deptId':'deptId',
    		'deptName':'deptName'
    	},deptTypeId)

  	});
    
    
    /**点击右侧维护区 保存 按钮时*/
    $('.saveBtn').on('click',function(){
        if($('#subContractDetailform').parsley().isValid()){
        	sscType = $("#scType").val();
        	sscAmount = $("#endDeclarationCost").val();
        	sscaName = $("#deptName").val();
        	sscbName = $("#cuName").val();
        	sscScope = $("#scScope").val();
        	$("#sscType").val(sscType);
        	$("#sscaName").val(sscaName);
        	$("#sscbName").val(sscbName);
        	$("#sscScope").val(sscScope);
        	$("#sscAmount").val(sscAmount);
        	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
        	var data=$('#subContractDetailform').serializeJson();
        	//console.log(data.payType);
        	console.info(data);
        	$.ajax({
                type: 'POST',
                url: 'subAgreement/saveSubAgreement',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                		alertInfo(content);
                	}else if(data === "true"){
                		$("#subContractDetailform input").val('');
                		$("#subContractDetailform textarea").val('');
                		$(".editbtn").addClass("hidden");
                		$("#subContractTable").cgetData(true,tbleCallBack);  //列表重新加载
                		/* $("#subContractDetailform").formReset();//表单重置
                		$(".editbtn").addClass("hidden");
                		
                		$("#subContractDetailform input").val('');
                    	trSData.t.cgetDetail('subContractDetailform','subAgreement/viewSubContract',''); 
                    	$("#subContractDetailform").toggleEditState(false);
                    	$(".editbtn").addClass("hidden");
                	   	selectTr["subContractTable"] = 0;
                	   	$('tbody tr:eq(0)').select();
                	   	//移除验证
                	   	$("#subContractDetailform").parsley().reset(); */
                		
                		var myoptions = {
    	                       	title: "提示信息",
    	                       	content: content,
    	                       	accept: popClose,
    	                       	chide: true,
    	                       	icon: "fa-check-circle"
    	                   }
    	                   $("body").cgetPopup(myoptions);
                	}else{
                		var myoptions = {
    	                       	title: "提示信息",
    	                       	content: content,
    	                       	accept: popClose,
    	                       	chide: true,
    	                       	icon: "fa-check-circle"
    	                   }
    	                   $("body").cgetPopup(myoptions);
                	}
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("分包补充协议签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#subContractDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#subContractDetailform").parsley().validate();
        }
        
    }); 
    
    var tbleCallBack = function(){
    	var len = $('#subContractTable').DataTable().ajax.json().data ? $('#subContractTable').DataTable().ajax.json().data.length : $('#subContractTable').DataTable().ajax.json().length;
    	console.info("len======="+len);
    	if(len<1){
		   		 //清空右侧记录
		   		 $("#subContractDetailform")[0].reset();
		   		 //清空表单
		   		 $(':input','#subContractDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
		   		 $(".editbtn").addClass("hidden");
		   		 $("#subContractDetailform").toggleEditState(false);
   		}else{
   				// $(".editbtn").removeClass("hidden");
   		}
    }
    
    
    //放弃
    $(".cancelBtn").on("click",function(){
    	$("#subContractDetailform input").val('');
    	trSData.t.cgetDetail('subContractDetailform','subAgreement/viewSubContract',''); 
    	$("#subContractDetailform").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["subContractTable"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#subContractDetailform").parsley().reset();
    });
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->