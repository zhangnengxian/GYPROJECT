<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
		<input type="hidden" id="sysDate" value="${sysDate}"/>
		<input type="hidden" id="customerServiceSenter" value="${customerServiceSenter}"/><!-- 客服中心 -->
		<input type="hidden" id="modificationGroup" value="${modificationGroup}"/><!-- 改管组 -->
    	<form class="form-horizontal" id="contractDetailform"  data-parsley-validate="true" action="">
       		<input type="hidden" name="projId" id="projId"/>
       		<input type="hidden" name="conId" id="conId"/>
       		<input type="hidden" name="deptDivide" id="deptDivide"/>
       		<input type="hidden" name="changeFlag" id="changeFlag" value="0"/>
       		<input type="hidden" name="flag" id="flag"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="conNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable conNo"  id="conNo" name="conNo" value="" />
		        </div>
		    </div>
		     <div class="form-group col-md-6">
	            <label class="control-label" for="contractType">合同类型</label>
            	<div>
               	   <select class="form-control input-sm field-not-editable" id="contractType"  name="contractType"  >
	               <c:forEach var="enums" items="${contractType}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="surveyer">勘察员</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="surveyer" name="surveyer" />
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="100"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100"  value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="projectTypeDes">工程类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="contributionModeDes">出资方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="deptName">业务部门</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2"></textarea>
	            </div>
            </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="custName">燃气用户</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="custPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custEntrustRepresent">甲方法定代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custEntrustRepresent" name="custEntrustRepresent" data-parsley-maxlength="20" value="" />
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="unitAddress">单位地址</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="unitAddress"  name="unitAddress"  value="" data-parsley-maxlength="200">
		        </div>
		    </div>
		    <div class="form-group col-md-12 clearboth ">
		        <label class="control-label" for="gasComp">燃气经营企业</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="gasComp" name="gasComp" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasCompPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="gasCompPhone" name="gasCompPhone" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasCorpAddr">单位地址</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="gasCorpAddr"  name="gasCorpAddr"  value="" data-parsley-maxlength="200">
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="conScope">承包范围</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="conScope" id="conScope" rows="2" data-parsley-maxlength="200"></textarea>
	            </div>
            </div>
            <div class="form-group col-md-6">
	            <label class="control-label" for="contractMethod">承包方式</label>
            	<div>
               	   <select class="form-control input-sm field-not-editable" id="contractMethod"  name="contractMethod"  >
	 		      	<c:forEach var="enums" items="${contractMethod}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div>
			 <div class="form-group col-md-6">
		        <label class="control-label" for="timeLimit">工期</label>
		        <div>
		        	<input type="text" class=" form-control input-sm field-not-editable" id="timeLimit"  name="timeLimit" data-parsley-maxlength="10" value="" >
		        	<a href="javascript:;" class="btn btn-sm btn-default">天</a>
		        </div>
		    </div>
		    <div class="form-group col-md-12 REMARK hidden">
	            <label class="control-label" for="remark">备注</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="remark" id="remark" rows="2" data-parsley-maxlength="1000">
	                </textarea>
	            </div>
            </div>
			 <div class="form-group col-md-6 resident">
		        <label class="control-label">交房日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="houseDate"  name="houseDate" data-parsley-maxlength="100"  value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6 not-resident">
		        <label class="control-label" for="budgetCost">确定总造价</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="budgetCost"  name="budgetCost" data-parsley-maxlength="100" value="" >
		       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 resident">
		        <label class="control-label" for="household">安装户数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable text-right" id="household"  name="household" data-parsley-type="number" data-parsley-maxlength="10" value="" >
		        </div>
		    </div>
		    <div class="form-group col-md-6 resident">
		        <label class="control-label" for="hoseAmount">每户金额</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="hoseAmount"  name="hoseAmount" data-parsley-type="number" data-parsley-maxlength="16" value=""   >
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="contractAmount">合同金额</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="contractAmount"  name="contractAmount" data-parsley-type="number" data-parsley-maxlength="16" value="" >
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		    <div class="form-group col-md-6">
	            <label class="control-label" for="payType">付款方式</label>
            	<div>
               	   <select class="form-control input-sm field-not-editable" id="payType"  name="payType"  >
	                <c:forEach var="enums" items="${payType}">
						<option value="${enums.ptId}" >${enums.payTypeDes}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div>
			<div class="form-group col-md-6 firstPayment hidden">
		        <label class="control-label" for="firstPayment">首付款</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="firstPayment"  name="firstPayment" data-parsley-type="number"
		            data-parsley-maxlength="16" value="" >
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
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="incrementAmount">增值税</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="incrementAmount"  name="incrementAmount"  data-parsley-maxlength="16" value="" data-parsley-type="number">
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="increment">税率</label>
		        <div>
		        	<select class=" form-control input-sm field-not-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16">
		        		<option></option>
		        		<c:forEach var="enums" items="${increment }">
		        			<option value="${ enums.increment}">${ enums.increment}</option>
		        		</c:forEach>
		        	</select>
		         <!--   <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16">
		            -->
		            <a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
		         </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="invoiceType">发票类型</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="invoiceType" name="invoiceType" data-parsley-maxlength="20" value="" data-parsley-maxlength="20">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="openBank">开户行</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="openBank"  name="openBank"  value="" data-parsley-maxlength="100" >
					<a id="bankPop" class="asBtn btn btn-default btn-sm m-l-10 aspop disabled" title="选择开户行"><i class="fa fa-search"></i></a>
				</div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="account">开户帐号</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="account"  name="account"  value="" data-parsley-maxlength="100" >
		        </div>
		    </div>
		   
		    <div class="form-group col-md-6">
		        <label class="control-label" for="conAgent">经办人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="conAgent"  name="conAgent" data-parsley-maxlength="20" value="${loginInfo.staffName}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="signDate"  name="signDate" data-parsley-maxlength="100" value="" >
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="supplementClause">补充条款</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="supplementClause" id="supplementClause" rows="2" data-parsley-maxlength="1000">
	                </textarea>
	            </div>
            </div>
            <div class="form-group col-md-6 col-sm-12 col-sm-6" id="materialIsRegister">
	        	<label class="control-label" for="materialIsRegister">物资是否已收款</label>
	    		<div>
		            <label>
		              	<input type="radio" class="field-editable"  name="materialIsRegister"  value="1"/> 是
		            </label>
		            <label>
		              	<input type="radio" class="field-editable"  name="materialIsRegister"  value="0" checked = "checked"/> 否
		            </label>
	        	</div>
			</div>
             <div class="form-group col-md-12" id="materialRemark">
	            <label class="control-label" for="materialRemark">物资登记备注</label>
	            <div>
	                <textarea class="form-control field-editable" name ="materialRemark" id="materialRemark" rows="2" data-parsley-maxlength="1000" data-parsley-required="true">
	                </textarea>
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
    $(".infodetails").hideMask();
    //表单样式适应
    $("#contractDetailform").toggleEditState().styleFit();
    changeAText();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    //查右侧工程详述
     trSData.t && trSData.t.cgetDetail('contractDetailform','contractMaterial/viewContract','',dataBack); 
    /**点击右侧维护区 保存 按钮时*/
    $(".saveButton").on("click",function(){
        if($("#contractDetailform").parsley().isValid()){
        	//加遮罩
        	var data=$("#contractDetailform").serializeJson();
        	if(data.materialIsRegister==''){
        		alertInfo("请选择是否已收款");
        		return;
        	}
        	$(".infodetails").loadMask("正在保存...", 1, 0.5);
        	$.ajax({
                type: 'POST',
                url: 'contractMaterial/saveContract',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                		alertInfo(content);
                	}else if(data === "true"){
                		//$("#contractDetailform input,#contractDetailform textarea,#contractDetailform select").val('');
                		//隐藏按钮
                		$(".editbtn").addClass("hidden");
                		$("#constructContractTable").cgetData(true,tableCallBack);  //列表重新加载
                		$("#contractDetailform").toggleEditState(false);
                		alertInfo(content);
                	}/* else{
                		alertInfo('数据保存成功！');
                	} */
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("合同签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	 $("#contractDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#contractDetailform").parsley().validate();
        }
    }); 
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	$('#materialRemark input').val('');
    	$('#materialIsRegister textarea').val('');
    	$('#contractDetailform').toggleEditState(false);
    	$('.editbtn').addClass('hidden');
    	 selectTr["constructContractTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#contractDetailform").parsley().reset();
    	 $("#conNo").val($("#projNo").val());
    });
    var tableCallBack = function(){
    	var len = $('#constructContractTable').DataTable().ajax.json().data ? $('#constructContractTable').DataTable().ajax.json().data.length : $('#constructContractTable').DataTable().ajax.json().length;
    	if(len<1){
		   		 //清空右侧记录
		   		 $("#contractDetailform")[0].reset();
		   		 $(".editbtn").addClass("hidden");
		   		 $("#contractDetailform").toggleEditState(false);
   				}else{
   				// $(".editbtn").removeClass("hidden");
   		}
    }
</script>
<!-- ================== END PAGE LEVEL JS ================== -->