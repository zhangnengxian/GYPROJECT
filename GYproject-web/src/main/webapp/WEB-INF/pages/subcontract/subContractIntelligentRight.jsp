<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton temporarySaveBtn" >保存</a> 
    	<a href="javascript:;" class="btn btn-info btn-sm m-l-5 saveButton saveBtn" >推送</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<input type="hidden" name="gasCompany" id="gasCompany" value="${gasCompany}"/>
    	<form class="form-horizontal" id="subContractDetailform" data-parsley-validate="true" action="">
       		<input type="hidden" id="projId" name="projId"/>
       		<input type="hidden" id="itScId" name="itScId"/>
       		<input type="hidden" id="flag" name="flag"/>
			<input type="hidden" id="projLtypeId" name="projLtypeId"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="itScNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="itScNo" name="itScNo"  data-parsley-required="true" value=""/>
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
		    		 <input type="text" class="form-control input-sm field-not-editable"  id="departmentName" name="departmentName" value="" />        
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">是否是智能表</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-not-editable" name="isIntelligentMeter" value="1" />是
		            </label>
		            <label>
		            	<input type="radio" class="field-not-editable" name="isIntelligentMeter" value="0" checked/>否
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="corpName">发包人</label>
		        <div>
		       		<input type="hidden"  id="corpId" name="corpId" data-parsley-maxlength="30"  value="" />
		       		<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName" data-parsley-maxlength="200"  value="" />
		        	
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6">
		    	<!-- 委托代表改为"委托代理人" -->
		        <label class="control-label" for="grantRepresent">委托代理人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="grantRepresent" name="grantRepresent" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="fPartyRepresent">甲方现场代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="fPartyRepresent" name="fPartyRepresent" data-parsley-maxlength="100"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="fPartySuJgj">甲方监理代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="fPartySuJgj" name="fPartySuJgj" data-parsley-maxlength="100"  value=""/>
		        </div>
		    </div>
		    <!-- <div class="form-group col-md-12">
		    	乙方改为承包人
		        <label class="control-label" for="sPartyName">承包人</label>
		        <div>
		            <input type="hidden" id="sPartyId" name="sPartyId" data-parsley-maxlength="30"  value="" />
		            <input type="text" class="form-control input-sm field-editable"  id="sPartyName" name="sPartyName" data-parsley-maxlength="200"  value="" data-parsley-required="true"/>
		        	
		        </div>
		    </div> -->
		     <div class="form-group col-md-12 col-sm-12 clearboth">
		        <label class="control-label" for="">承包人</label>
		        <div>
		        	<input type="hidden" id="sPartyId" name="sPartyId" data-parsley-maxlength="30"  value="" />
		            <input type="text" class="form-control input-sm field-not-editable"  id="sPartyName" name="sPartyName" data-parsley-maxlength="100" data-parsley-required="true"/>
		            <a id="subUnit" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择智能表公司"><i class="fa fa-search"></i></a>
		        </div>
		   </div>
		    <div class="form-group col-md-6 ">
		    	<!-- 乙方委托代表改为委托代理人 -->
		        <label class="control-label" for="sPartyGrantRepresent">委托代理人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sPartyGrantRepresent" name="sPartyGrantRepresent" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="sPartyCuPm">项目经理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sPartyCuPm" name="sPartyCuPm" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div> 
		    <div class="form-group col-md-6 clearboth">
		    	<!-- 现场负责人改为施工员 -->
		        <label class="control-label" for="sPartyBuilder">施工员</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="sPartyBuilder" name="sPartyBuilder" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
			
		    <div class="form-group col-md-12">
		    	<!-- 承包范围改为工程内容 -->
	            <label class="control-label" for="projContent">工程内容</label>
	            <div>
	                <textarea class="form-control field-editable" name ="projContent" id="projContent" rows="3" data-parsley-maxlength="200"></textarea>
	            </div>
            </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scPlannedStartDate">开工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="scPlannedStartDate"  name="scPlannedStartDate" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scPlannedEndDate">竣工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="scPlannedEndDate"  name="scPlannedEndDate" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="increment">税率</label>
		        <div>
		        	<select class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-required="true">
		        		<option></option>
		        		<c:forEach var="enums" items="${increment }">
		        			<option value="${ enums.increment}" <c:if test="${enums.increment=='3.00'}">selected="selected"</c:if> >${ enums.increment}</option>
		        		</c:forEach>
		        	</select>
		         <!--   <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16">
		            -->
		            <a href="javascript:;" class="btn btn-sm btn-default">%</a>
		         </div>
		    </div>
            <div class="form-group col-md-6 clearboth">
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
					<select class="form-control input-sm field-editable" id="progressType"  name="progressType" data-parsley-required="true">
						<option value="" ></option>
					</select>
				</div>
			</div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="installNums">安装户数</label>
		        <div>
		            <input type="number" class="form-control input-sm field-editable text-right" id="installNums" name="installNums" min="0" data-parsley-maxlength="11" value="" data-parsley-required="true"/>
		        	<a href="javascript:;" class="btn btn-sm btn-default">户</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="unitCost">每户金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable fixed-number text-right" id="unitCost" name="unitCost" data-parsley-maxlength="16" value="" data-parsley-required="true"/>
		       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		       		<%--  <select class="form-control input-sm field-editable text-right" id="unitCost" name="unitCost" data-parsley-required="true">
		       		 	<option value="" ></option>
						<c:forEach var="enums" items="${cashEnum }">
							<option value="${enums.value }" >${enums.message }</option>
						</c:forEach>
		       		 </select> --%>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="totalCost">工程总价</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="totalCost" name="totalCost" data-parsley-maxlength="16" value="" data-parsley-required="true"/>
		        </div>
		    </div>
			<div class="form-group col-md-6">
				<label class="control-label" for="payType">付款方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="payType"  name="payType" data-parsley-required="true">
						<option value="" ></option>
						<c:forEach var="enums" items="${payTypeSCIEnum }">
							<option value="${enums.value }" >${enums.message }</option>
						</c:forEach>
					</select>
				</div>
			</div>
		    <div class="form-group col-md-6 firstPayment hidden">
		        <label class="control-label" for="firstPayment">首付款</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="firstPayment"  name="firstPayment" data-parsley-type="number"
		            data-parsley-maxlength="16" value="" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 secondPayment hidden">
		        <label class="control-label" for="secondPayment">阶段款</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="secondPayment"  name="secondPayment" data-parsley-type="number" data-parsley-maxlength="16" value="" >
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		         </div>
		    </div>
		    <div class="form-group col-md-6 thirdPayment hidden">
		        <label class="control-label" for="thirdPayment">阶段款</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="thirdPayment"  name="thirdPayment" data-parsley-type="number" data-parsley-maxlength="16" value="" >
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		         </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="signDate">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default timestamp" id="signDate" name="signDate" value="" data-parsley-required="true">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="fPartyConAgent">经办人</label>
		        <div>
		           <input type="hidden" id="fPartyConAgentId" name="fPartyConAgentId" value="${loginInfo.staffId }"/>
		           <input type="text" class=" form-control input-sm field-not-editable " id="fPartyConAgent" name="fPartyConAgent"  data-parsley-required="true"  value="${loginInfo.staffName }">
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
    //changeAText();
  	//查右侧工程详述
    trSData.t&&trSData.t.cgetDetail('subContractDetailform','subContractIntelligent/viewSubConIntelligent','',cgetDetailBack);
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    /**点击右侧维护区 确认 按钮时*/
    $('.saveBtn').on('click',function(){
    	/* if($("#flag").val()=='1'){
    		alertInfo("该合同已不可修改！");
    		return;
    	} */
    	$("#flag").val("1");
        if($('#subContractDetailform').parsley().isValid()){
        	 $("body").cgetPopup({
  	        	title: "提示信息",
  	        	content: "推送后，将记录合同付款信息 ,您确定要推送合同吗？",
  	        	accept:saveBtnBack,
  	        	icon: "fa-warning",
  	        	//newpop: 'new'
  	        });
        	//如果通过验证, 则移除验证UI
        	$("#subContractDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#subContractDetailform").parsley().validate();
        }
        
    }); 
    
    var saveBtnBack = function(){
    	//加遮罩
    	$(".infodetails").loadMask("正在保存...", 1, 0.5);
    	var data=$('#subContractDetailform').serializeJson();
    	$.ajax({
            type: 'POST',
            url: 'subContractIntelligent/saveSubContractIntelligent',
            contentType: "application/json;charset=UTF-8",
            dataType:"json",
            data: JSON.stringify(data),
            success: function (data) {

            	//取消遮罩
            	$(".infodetails").hideMask();	
            	var content = "数据推送成功！";
            	if(data.ret_message === "false"){
            		content = "数据推送失败！";
            		alertInfo(content);
            	}else if(data.ret_message ===  "exist"){
            		content = "合同编号已存在，请修改！";
            		alertInfo(content);
            	}else if(data.ret_message=="true") {
            		$("#subContractDetailform").toggleEditState(false);
           	        $(".editbtn").addClass("hidden");
            		$("#subContractIntelligentTable").cgetData(true,tableCallBack);  //列表重新加载
            		alertInfo(content);
            	}else{//接口失败
            		alertInfo(data.ret_message);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {

            	//取消遮罩
            	$(".infodetails").hideMask();	
                console.warn("智能表分合同签订区记录保存失败！");
            }
        });
    }
    /**点击右侧维护区 保存  按钮时*/
    $('.temporarySaveBtn').on('click',function(){
    	$("#flag").val("0");
        if($('#subContractDetailform').parsley().isValid()){
        	var data=$('#subContractDetailform').serializeJson();
        	$('.infodetails').parent().parent().loadMask("正在保存。。。",1,0.5);
        	$.ajax({
                type: 'POST',
                url: 'subContractIntelligent/saveSubContractIntelligent',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	$('.infodetails').parent().parent().hideMask()
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                		alertInfo(content);
                	}else if(data ===  "exist"){
                		content = "合同编号已存在，请修改！";
                		alertInfo(content);
                	}else {
                		$("#subContractDetailform").toggleEditState(false);
               	        $(".editbtn").addClass("hidden");
                		$("#subContractIntelligentTable").cgetData(true,tableCallBack);  //列表重新加载
                		alertInfo(content);
                	}
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	$('#subContractDetailform').parent().parent().hideMask()
                    console.warn("智能表合同签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#subContractDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#subContractDetailform").parsley().validate();
        }
        
    }); 
    
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
    	trSData.t.cgetDetail('subContractDetailform','subContractIntelligent/viewSubConIntelligent',''); 
    	$("#subContractDetailform").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["subContractTable"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#subContractDetailform").parsley().reset();
    });
 	
    var cuUnitTypeId=$('#cuUnitTypeId').val();
   
  	var deptTypeId=$('#deptTypeId').val();
  	//console.log(deptTypeId);
   
    //安装户数和每户金额 
    $("#unitCost,#installNums").on("change",function(){
    	var unitCost = $('#unitCost').val();
    	var installNums = $('#installNums').val();
    	if(installNums==''){
    		installNums=0;
    	}
    	if(unitCost==''){
    		unitCost=0;
    	}
    	$("#totalCost").val(new Number(installNums*unitCost).toFixed(2));
    	var rate = new Number($("#firstRate").val()*0.01).toFixed(2);
    	$("#firstPayment").val(new Number(installNums*unitCost*rate).toFixed(2));
    	changePayTypeFunc();
    });
    //付款方式改变时 
    $("#payType").change(function(){
    	changePayTypeFunc();
	});
  //选中智能表公司
    $("#subUnit").off().on("click",function(){
    	var cuUnitType=$('#gasCompany').val();
    	businessPartnersPopup({
    		'sPartyId':'unitId',
    		'sPartyName':'unitName',   
    		'sPartyCuPm':'unitManager',
    		'cuPhone':'unitMobile',
    	},cuUnitType)
  	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->