<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
		<%--<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton temporarySaveBtn" >暂存</a>--%>
    	<a href="javascript:;" class="btn btn-info btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
		<input type="hidden" id="sysDate" value="${sysDate}"/>
    	<form class="form-horizontal" id="imcDetailForm" data-parsley-validate="true" action="">
       		<input type="hidden" id="projId" name="projId"/>
       		<input type="hidden" id="imcId" name="imcId"/>
       		<input type="hidden" id="isPrint" name="isPrint"/>
       		<input type="hidden" id="deptId" name="deptId"/>
       		<input type="hidden" id="deptName" name="deptName"/>
       		<input type="hidden" id="flag" name="flag"/>
       		<input type="hidden" id="isCashFlow" name="isCashFlow"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="imcNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="imcNo" name="imcNo"  data-parsley-required="true" value="" data-parsley-maxlength="30"/>
		        </div>
		    </div>
		   <div class="form-group col-md-12 ">
		        <label class="control-label" for="conName">合同名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="conName" name="conName" data-parsley-maxlength="200" data-parsley-required="true" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="30" value=""/>
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
		    <!-- <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">是否是智能表</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-not-editable" name="isIntelligentMeter" value="1" />是
		            </label>
		            <label>
		            	<input type="radio" class="field-not-editable" name="isIntelligentMeter" value="0" checked/>否
		            </label>
		        </div>
		    </div> -->
		     <div class="form-group col-md-6">
		        <label class="control-label" for="installNums">安装户数</label>
		        <div>
		        	<input type="number" class="form-control input-sm field-editable text-right" min="0" name="installNums" id = "installNums" rows="" cols="" data-parsley-maxlength="20" data-parsley-required="true"/>
		        	<a href="javascript:;" class="btn btn-sm btn-default">户</a>
		        </div>
		    </div>

		     <div class="form-group col-md-12">
		        <label class="control-label" for="gasCustName">用气单位</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="gasCustName" name="gasCustName" data-parsley-maxlength="50"  value="" data-parsley-required="true"/>
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>

		        </div>
		    </div>
		     <div class="form-group col-md-12">
		        <label class="control-label" for="gasConNo">用气合同编号</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-not-editable"  id="gasConNo" name="gasConNo" data-parsley-maxlength="50"  value="" data-parsley-required="true"/>
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>

		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="corpName">甲方</label>
		        <div>
		       		<input type="hidden"  id="corpId" name="corpId" data-parsley-maxlength="30"  value="" />
		       		<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName" data-parsley-maxlength="50"  value="" data-parsley-required="true"/>
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>

		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="grantRepresent">授权代表人</label>
		        <div>
		       		<input type="hidden"  id="grantRepresentId" name="grantRepresentId" data-parsley-maxlength="30"  value="" />
		       		<input type="text" class="form-control input-sm field-editable"  id="grantRepresent" name="grantRepresent" data-parsley-maxlength="50"  value="" />
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>

		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyConAgent">经办人</label>
		        <div>
		       		<input type="hidden"  id="fPartyConAgentId" name="fPartyConAgentId" data-parsley-maxlength="30"  value="" />
		       		<input type="text" class="form-control input-sm field-not-editable"  id="fPartyConAgent" name="fPartyConAgent" data-parsley-maxlength="50"  value="" />
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>

		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyBankName">开户行</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="fPartyBankName" name="fPartyBankName" data-parsley-maxlength="50"  value="">
		        	<a id="bankPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择开户行"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyBankAccount">开户行帐号</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="fPartyBankAccount" name="fPartyBankAccount" data-parsley-maxlength="50"  value=""/>

		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyTelNumber">联系电话</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="fPartyTelNumber" name="fPartyTelNumber" data-parsley-maxlength="20"  value="" />

		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyAddr">地址</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="fPartyAddr" name="fPartyAddr" data-parsley-maxlength="50"  value="" />

		        </div>
		    </div>
		   	<div class="form-group col-md-12 clearboth">
		        <label class="control-label" for="sPartyName">乙方</label>
		        <div>
		        	<input type="hidden" id="sPartyId" name="sPartyId"/>
		            <input type="text" class="form-control input-sm field-not-editable"  id="sPartyName" name="sPartyName" data-parsley-maxlength="200" data-parsley-required="true"/>
		        	 <a id="custPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择申报单位"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		   	<div class="form-group col-md-6 ">
		        <label class="control-label" for="sPartyLegalRepresent">法定代表人</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyLegalRepresent" name="sPartyLegalRepresent" data-parsley-maxlength="50"  value="" />

		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyAgent">经办人</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyAgent" name="sPartyAgent" data-parsley-maxlength="50"  value="" />

		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyBankName">开户行</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyBankName" name="sPartyBankName" data-parsley-maxlength="50"  value=""/>

		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyBankAccount">开户行帐号</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyBankAccount" name="sPartyBankAccount" data-parsley-maxlength="50"  value="" />

		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyTelNumber">联系电话</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyTelNumber" name="sPartyTelNumber" data-parsley-maxlength="20"  value="" />

		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyAddr">地址</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyAddr" name="sPartyAddr" data-parsley-maxlength="50"  value="" />

		        </div>
		    </div>
			<!-- <div class="form-group col-md-6 payType">
				<label class="control-label" for="payType">预付款方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="payType"  name="payType">
						<option value="" ></option>
					</select>
				</div>
			</div>
            <div class="form-group col-md-6">
            	承包方式改为合同价款方式
		        <label class="control-label" for="scType">合同价款方式</label>
		        <div>
		        	<select class="form-control input-sm field-editable" id="scType" name="scType">
						<option value=""></option>
					</select>
		        </div>
		    </div> -->
			<!-- <div class="form-group col-md-6">
				承包方式改为合同价款方式
				<label class="control-label" for="progressType">进度款方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="progressType"  name="progressType">
						<option value="" ></option>
					</select>
				</div>
			</div> -->

		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="unitCost">单价</label>
		        <div>
		        	<!-- <select class="form-control input-sm field-editable" id="unitCost"  name="unitCost" data-parsley-required="true">
						<option value="290" >290.00</option>
						<option value="630" >630.00</option>
					</select> -->
		            <input type="text" class="form-control input-sm field-editable fixed-number text-right" id="unitCost" name="unitCost" data-parsley-maxlength="16" value="" data-parsley-type="number" data-parsley-required="true" />
		        	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
			<div class="form-group col-md-6">
				<label class="control-label" for="totalCost">合同金额</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="totalCost" name="totalCost" data-parsley-maxlength="16" value="" data-parsley-type="number" data-parsley-required="true"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6 payType">
				<label class="control-label" for="payType">预付款方式</label>
				<div>
					<select class="form-control input-sm field-not-editable" id="payType"  name="payType" data-parsley-required="true">
						<c:forEach var="enumes" items="${payTypes }">
							<option value="${ enumes.ptId}">${enumes.payTypeDes }</option>
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
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="secondPayment"  name="secondPayment" data-parsley-type="number" data-parsley-maxlength="16" value="" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		         </div>
		    </div>
			<div class="form-group col-md-6 thirdPayment hidden">
		        <label class="control-label" for="thirdPayment">阶段款</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="thirdPayment"  name="thirdPayment" data-parsley-type="number" data-parsley-maxlength="16" value="" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="incrementAmount">增值税</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="incrementAmount"  name="incrementAmount"  data-parsley-maxlength="16" value="" data-parsley-type="number">
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="increment">税率</label>
		        <div>
		        <select class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-required="true">
		        		<option></option>
		        		<c:forEach var="enums" items="${increment }">
		        			<option value="${ enums.increment}">${ enums.increment}</option>
		        		</c:forEach>
		        	</select>
		            <a href="javascript:;" class="btn btn-sm btn-default">%</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="invoiceType">发票类型</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="invoiceType" name="invoiceType" placeholder="" data-parsley-maxlength="20" value="增值税普通发票" data-parsley-maxlength="20">
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="">楼盘名称</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="houseName" name="houseName" placeholder="" data-parsley-maxlength="200" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="">楼盘地址</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="houseAddr" name="houseAddr" placeholder="" data-parsley-maxlength="200">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="">预计完工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="predictCompleteDate" name="predictCompleteDate" placeholder="">
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" >签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="imcSignDate" name="imcSignDate" value="" data-parsley-required="true">
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
    $('#imcDetailForm').toggleEditState().styleFit();
    changeAText();
    
  	//查右侧工程详述
    trSData.t&&trSData.t.cgetDetail('imcDetailForm','intelligentMeterCon/viewIntelligentMeterCon','',cgetDetailBack);
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    var tableCallBack = function(){
        var len = $('#intelligentMeterConTable').DataTable().ajax.json().data ? $('#intelligentMeterConTable').DataTable().ajax.json().data.length : $('#intelligentMeterConTable').DataTable().ajax.json().length;
        if(len<1){
            //清空右侧记录
            $("#imcDetailForm").formReset();
            // $("#subSafeDetailform").formReset();
            $(".editbtn").addClass("hidden");
            $('#imcDetailForm').toggleEditState().styleFit();
            //$('#subSafeDetailform').toggleEditState().styleFit();
        }
    }

    /**点击右侧维护区 保存/推送 按钮时*/
    $('.saveBtn').on('click',function(){
    	$("#flag").val("1");
        if($('#imcDetailForm').parsley().isValid()){
        	 $("body").cgetPopup({
 	        	title: "提示信息",
 	        	content: "您确定修改智能表合同信息吗？",
 	        	accept:saveBtnBack,
 	        	icon: "fa-warning",
 	        	//newpop: 'new'
 	        });
        	
        	//如果通过验证, 则移除验证UI
        	$("#imcDetailForm").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#imcDetailForm").parsley().validate();
        }
        
    }); 
    var saveBtnBack = function(){
    	var data=$('#imcDetailForm').serializeJson();
    	$('#imcDetailForm').parent().parent().loadMask("正在保存。。。",1,0.5);
    	$.ajax({
            type: 'POST',
            url: 'intelligentMeterConModify/modifyImcContract',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
            	$('#imcDetailForm').parent().parent().hideMask();
            	var content = "数据保存成功！";
            	if(data === "false"){
            		content = "数据保存失败！";
            		alertInfo(content);
            	}else if(data ===  "exist"){
            		content = "合同编号已存在，请修改！";
            		alertInfo(content);
            	}else if(data === "true"){
            		$("#imcDetailForm input").val('');
            		$("#imcDetailForm textarea").val('');
            		$("#imcDetailForm").formReset();//表单重置
            		//$("#subSafeDetailform").formReset();//表单重置
            		$("#intelligentMeterConTable").cgetData(true,tableCallBack);  //列表重新加载
            		alertInfo(content);
            	}else{
            		//弹出收付流水确认屏
//         			var url = "#constructContract/accrualsRecordPopPage?arId="+data;
//         			var popoptions = {
//         				title: '应付流水',
//         				content: url,
//         				chide:true,
//         				nocache:true,
//         				accept: accrualsRecordDone
//         			};
//         			//加载弹屏
//         			$("body").cgetPopup(popoptions);
            		alertInfo('数据保存成功！');
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
            	$('#imcDetailForm').parent().parent().hideMask()
                console.warn("智能表合同签订区记录保存失败！");
            }
        });
    }
    /**点击右侧维护区 暂存 按钮时*/
    $('.temporarySaveBtn').on('click',function(){
    	$("#flag").val("0");
        if($('#imcDetailForm').parsley().isValid()){
        	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
        	var data=$('#imcDetailForm').serializeJson();
        	$('#imcDetailForm').parent().parent().loadMask("正在保存。。。",1,0.5);
        	$.ajax({
                type: 'POST',
                url: 'intelligentMeterCon/saveIMContract',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	$('#imcDetailForm').parent().parent().hideMask()
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                		alertInfo(content);
                	}else if(data ===  "exist"){
                		content = "合同编号已存在，请修改！";
                		alertInfo(content);
                	}else if(data === "true"){
                		$("#imcDetailForm input").val('');
                		$("#imcDetailForm textarea").val('');
                		$("#intelligentMeterConTable").cgetData(true,tableCallBack);  //列表重新加载
                		alertInfo(content);
                	}else{
                		/* //弹出收付流水确认屏
             			var url = "#constructContract/accrualsRecordPopPage?arId="+data;
             			var popoptions = {
             				title: '应付流水',
             				content: url,
             				chide:true,
             				nocache:true,
             				accept: accrualsRecordDone
             			};
             			//加载弹屏
             			$("body").cgetPopup(popoptions); */
                		alertInfo('数据保存成功！');
                	}
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	$('#imcDetailForm').parent().parent().hideMask()
                    console.warn("智能表合同签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#imcDetailForm").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#imcDetailForm").parsley().validate();
        }
        
    }); 
    
    var accrualsRecordDone = function(){
    	
    	$("#imcDetailForm input").val('');
		$("#imcDetailForm textarea").val('');
		$("#intelligentMeterConTable").cgetData(true,tableCallBack);  //列表重新加载
		alertInfo('数据保存成功！');
    }

    //放弃
    $(".cancelBtn").on("click",function(){
    	$("#imcDetailForm input").val('');
    	trSData.t.cgetDetail('imcDetailForm','intelligentMeterCon/viewIntelligentMeterCon',''); 
    	$("#imcDetailForm").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["intelligentMeterConTable"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#imcDetailForm").parsley().reset();
    });
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
    	changePayTypeFunc1();
    });
    //付款方式改变时 
    $("#payType").change(function(){
    	changePayTypeFunc1();
	});

    //选择开户行
    $("#bankPop").off().on("click",function(){
        $("body").cgetPopup({
            title: '开户行选择',
            content: '#popup/bankPop',
            size: 'large',
            callback:function(){
                bank.init();
            },
            accept: function(){
                if($("#bankTable tr.selected").length < 1){
                    $("body").cgetPopup({title:'提示',content:"请选择开户行！",newpop:'second',accept:innerClose});
                    return false;
                }else{
                    var data = trSData.bankTable.json;
                    //开户行
                    $("#fPartyBankName").val(data.bankAdress);
                    //开户账号
                    $("#fPartyBankAccount").val(data.bankNo);
                }
            }
        });
    });
  //选择申报单位
    $("#custPop").off().on("click",function(){
    	$("body").cgetPopup({
    		title: '申报单位选择',
    		content: '#popup/custListPop',
    		size: 'large',
    		callback:function(){
    			customer.init();
    		},
    	    accept: function(){
    	    	if($("#customerTable tr.selected").length < 1){
    	    		$("body").cgetPopup({title:'提示',content:"请选择申报单位！",newpop:'second',accept:innerClose});
    	    		return false;
    	    	}else{
    		    	var data = trSData.customerTable.json;
    		    	$("#gasCustName").val(data.custName);
    		    	$("#sPartyName").val(data.custName);
    		    	$("#sPartyId").val(data.custId);
    		    	//联系人、联系电话、单位地址
    		    	$("#sPartyTelNumber").val(data.custPhone);
    		    	$("#sPartyAgent").val(data.custContcat);
    		    	$("#sPartyAddr").val(data.unitAddress);
    	    	}
    	    }
      });
  	});
  
</script>
<!-- ================== END PAGE LEVEL JS ================== -->