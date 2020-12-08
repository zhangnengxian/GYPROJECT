<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5  saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
		<input type="hidden" id="sysDate" value="${sysDate}"/>
    	<form class="form-horizontal" id="subContractDetailform" data-parsley-validate="true" action="">
       		<input type="hidden" id="projId" name="projId"/>
       		<input type="hidden" id="scId" name="scId"/>
       		<input type="hidden" id="flag" name="flag"/>
			<input type="hidden" id="projLtypeId" name="projLtypeId"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="scNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo"  data-parsley-required="true" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
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
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		        	<textarea class="form-control input-sm field-not-editable" name="projScaleDes" id = "projScaleDes" rows="" cols="" data-parsley-maxlength="200"></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" />
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
		    		 <input type="text" class="form-control input-sm field-not-editable"  id="departmentName" name="departmentName" value=""/>        
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="deptName">发包人</label>
		        <div>
		       		<input type="hidden"  id="deptId" name="deptId" data-parsley-maxlength="20"  value="" />
		       		<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" data-parsley-maxlength="50"  value="" />
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6">
		    	<!-- 委托代表改为"委托代理人" -->
		        <label class="control-label" for="projCompDirector">委托代理人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projCompDirector" name="projCompDirector" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasComLegalRepresent">甲方现场代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="gasComLegalRepresent" name="gasComLegalRepresent" data-parsley-maxlength="20"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		    	<!-- 乙方改为承包人 -->
		        <label class="control-label" for="cuName">承包人</label>
		        <div>
		            <input type="hidden" id="cuId" name="cuId" data-parsley-maxlength="100"  value="" />
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" data-parsley-maxlength="200"  value="" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		    	<!-- 乙方委托代表改为委托代理人 -->
		        <label class="control-label" for="cuDirector">委托代理人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuDirector" name="cuDirector" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuPm">项目经理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuPm" name="cuPm" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div> 
		    <div class="form-group col-md-6">
	            <label class="control-label" for="contractMethod">建筑服务</label>
            	<div>
               	   <select class="form-control input-sm field-editable" id="contractMethod"  name="contractMethod"  data-parsley-required="true">
	 		      	<c:forEach var="enums" items="${subContractMethod}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div>
		    <div class="form-group col-md-6">
		    	<!-- 现场负责人改为施工员 -->
		        <label class="control-label" for="cuResponsiblePerson">施工员</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuResponsiblePerson" name="cuResponsiblePerson" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
			<div class="form-group col-md-12 hidden artery">
				<label class="control-label" for="contractMode">承包方式</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"  id="contractMode" name="contractMode" data-parsley-maxlength="200" value=""/>
				</div>
			</div>
		    <div class="form-group col-md-12">
		    	<!-- 承包范围改为工程内容 -->
	            <label class="control-label" for="scScope">工程内容</label>
	            <div>
	                <textarea class="form-control field-editable" name ="scScope" id="scScope" rows="3" data-parsley-maxlength="2000"></textarea>
	            </div>
            </div>
             <div class="form-group col-md-6">
		        <label class="control-label" for="increment">税率</label>
		        <div>
		        	<select class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-required="true">
		        		<option></option>
		        		<c:forEach var="enums" items="${increment }">
		        			<option value="${ enums.increment}" >${ enums.increment}</option>
		        		</c:forEach>
		        	</select>
		         <!--   <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16">
		            -->
		            <a href="javascript:;" class="btn btn-sm btn-default">%</a>
		         </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" >开工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default timestamp" id="scPlannedStartDate"  name="scPlannedStartDate" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" >竣工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default timestamp" id="scPlannedEndDate"  name="scPlannedEndDate" value="">
		        </div>
		    </div>
			<div class="form-group col-md-6 payType">
				<label class="control-label" for="payType">预付款方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="payType"  name="payType">
						<option value="" ></option>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 payRate hidden">
				<label class="control-label" for="payType">预付款比例</label>
				<div>
					<input type="number" class="form-control input-sm field-editable" id="payRate" name="payRate"  data-parsley-required="true"/>
					<a href="javascript:;" class="btn btn-sm btn-default">%</a>
				</div>
			</div>
            <div class="form-group col-md-6">
            	<!-- 承包方式改为合同价款方式 -->
		        <label class="control-label" for="scType">合同价款方式</label>
		        <div>
		        	<select class="form-control input-sm field-editable" id="scType" name="scType">
						<option value=""></option>
					</select>
		        </div>
		    </div>
			<div class="form-group col-md-6">
				<!-- 承包方式改为合同价款方式 -->
				<label class="control-label" for="progressType">进度款方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="progressType"  name="progressType">
						<option value="" ></option>
					</select>
				</div>
			</div>
		    <div class="form-group col-md-6 hidden">
		        <label class="control-label" for="quAmount">工程造价</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="quAmount" name="quAmount" data-parsley-maxlength="16" value=""/>
		        </div>
		    </div>
			<div class="form-group col-md-6">
				<label class="control-label" for="scAmount">合同金额</label>
				<div>
					<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="scAmount" name="scAmount" data-parsley-maxlength="16" value=""  data-parsley-required="true"/>
				</div>
			</div>
		    <div class="form-group col-md-6">
		        <label class="control-label" >签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="scSignDate" name="scSignDate" value="" data-parsley-required="true">
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
    changeAText();
  	//查右侧工程详述
    trSData.t&&trSData.t.cgetDetail('subContractDetailform','subContractModify/viewSubContract','',cgetDetailBack);
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    /**点击右侧维护区 保存 按钮时*/
    $('.saveBtn').on('click',function(){
        if($('#subContractDetailform').parsley().isValid()){
        	//加遮罩
        	$(".infodetails").loadMask("正在保存...", 1, 0.5);
        	var data=$('#subContractDetailform').serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'subContractModify/modifySubContract',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {

                	//取消遮罩
                	$(".infodetails").hideMask();	
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                		alertInfo(content);
                	}else if(data ===  "exist"){
                		content = "合同编号已存在，请修改！";
                		alertInfo(content);
                	}else if(data === "true"){
                		$("#subContractDetailform input").val('');
                		$("#subContractDetailform textarea").val('');
                		$("#subContractDetailform").formReset();//表单重置
                		$("#subSafeDetailform").formReset();//表单重置
                		$("#subContractTable").cgetData(true,tableCallBack);  //列表重新加载
                		alertInfo(content);
                	}else{
                		alertInfo('数据保存成功！');
                	}
                },
                error: function (jqXHR, textStatus, errorThrown) {

                	//取消遮罩
                	$(".infodetails").hideMask();	
                    console.warn("分包合同签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#subContractDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#subContractDetailform").parsley().validate();
        }
        
    }); 
     
    
    var accrualsRecordDone = function(){
    	
    	$("#subContractDetailform input").val('');
		$("#subContractDetailform textarea").val('');
		$("#subContractTable").cgetData(true,tableCallBack);  //列表重新加载
		alertInfo('数据保存成功！');
    }
    var tableCallBack = function(){
    	var len = $('#subContractTable').DataTable().ajax.json().data ? $('#subContractTable').DataTable().ajax.json().data.length : $('#subContractTable').DataTable().ajax.json().length;
    	if(len<1){
    		 //清空右侧记录
    		 $("#subContractDetailform").formReset();
    		 $("#subSafeDetailform").formReset();
    		 $(".editbtn").addClass("hidden");
    		 $('#subContractDetailform').toggleEditState().styleFit();
    		 $('#subSafeDetailform').toggleEditState().styleFit();
		}
    }
    //放弃
    $(".cancelBtn").on("click",function(){
    	$("#subContractDetailform input").val('');
    	trSData.t.cgetDetail('subContractDetailform','subContractModify/viewSubContract',''); 
    	$("#subContractDetailform").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["subContractTable"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#subContractDetailform").parsley().reset();
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
  	//console.log(deptTypeId);
    //选中甲方
    $("#managePop").off('click').on("click",function(){
    	deptPopup({
    		'deptId':'deptId',
    		'deptName':'deptName'
    	},deptTypeId)

  	});
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->