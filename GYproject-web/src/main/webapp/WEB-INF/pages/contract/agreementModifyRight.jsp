<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<!-- <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >暂存</a> -->
    	<a href="javascript:;" class="btn btn-info btn-sm m-l-5  pushBtn " >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<input type="hidden" id="sysDate" value="${sysDate}"/>
    	<input type="hidden" id="resident" value="${resident}"/>
    	<input type="hidden" id="stepId" name="stepId" value="${stepId }">
		<input type="hidden" id="businessOrderId" name="businessOrderId"/>
    	<form class="form-horizontal" id="agreementModifyform"  data-parsley-validate="true" action="">
    		<input type="hidden" name="projId" id="projId"/>
       		<input type="hidden" name="scId" id="scId"/>
       		<input type="hidden" name="isAudit" id="isAudit"/>
       		<input type="hidden" name="conId" id="conId"/>
       		<input type="hidden" name="scType" id="scType"/>
       		<input type="hidden" name="flag" id="flag"/>
       		<input type="hidden" name="budgetId" id="budgetId"/>
       		<input type="hidden" name="projNo" id="projNo"/>
       		<input type="hidden" name="isPrint" id="isPrint"/>
       		<input type="hidden"   name="modifyStatus" id="modifyStatus"/><!--修改标记  -->
       		 <div class="form-group  col-md-6 ">
		        <label class="control-label" for="conNo">合同编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="conNo" name="conNo" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="scNo">协议编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="projectTypeDes">工程类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="contributionModeDes">出资方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="deptName">业务部门</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value=""/>
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
		            <input type="text" class="form-control input-sm field-editable"  id="custName" name="custName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="custPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 clearboth ">
		        <label class="control-label" for="gasComp">燃气经营企业</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasComp" name="gasComp" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasCompPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasCompPhone" name="gasCompPhone" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth resident">
		        <label class="control-label" for="houseNum">户数</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="houseNum" name="houseNum" data-parsley-maxlength="6" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 resident">
		        <label class="control-label" for="houseAmount">每户金额</label>
		        <div>
					<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="houseAmount"  name="houseAmount" data-parsley-maxlength="13" value="2230"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scAmount">协议价款</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="scAmount"  name="scAmount" data-parsley-maxlength="13" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6 resident">
		        <label class="control-label" for="houseAddr">地址</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="houseAddr" name="houseAddr" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		        <div class="form-group col-md-6 col-sm-12 col-sm-6  clearboth">
	            <label class="control-label" for="payType">付款方式</label>
            	<div>
               	   <select class="form-control input-sm field-editable" id="payType"  name="payType"  data-parsley-required="true">
	                    <option >--请选择付款方式--</option>
	                    <option value="1">一次付清</option>
	                    <option value="2">两次付清</option>
	                    <option value="3">三次付清</option>
	              </select>
	            </div>
			</div>
			 <div class="form-group col-md-6  paymentRatio1  clearboth hidden">
		        <label class="control-label" for="paymentRatio1">首付比列</label>
		        <div>
		           <input type="text" class=" form-control input-sm  fixed-number text-right" id="paymentRatio1"  readonly="readonly" name="paymentRatio1" data-parsley-type="number"
		            data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例" onblur="loseBlur(this.value)"/>
					 <a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
		        </div>
		    </div>
		    	<div class="form-group col-md-6 firstPayment hidden">
		        <label class="control-label" for="firstPayment">首付款</label>
		        <div>
		          
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="firstPayment"  name="firstPayment" data-parsley-type="number"
		            data-parsley-maxlength="16" value="" data-parsley-required="true" size="10" />
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		      <div class="form-group col-md-6 paymentRatio2 hidden">
		        <label class="control-label" for="paymentRatio2 ">阶段款比列</label>
		        <div>
		           <input type="text" class=" form-control input-sm  fixed-number text-right" id="paymentRatio2" readonly="readonly" name="paymentRatio2" data-parsley-type="number"
		            data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例"  onblur="loseBlur(this.value)"/>
					 <a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
		        </div>
		    </div>
		     <div class="form-group col-md-6 secondPayment hidden ">
		        <label class="control-label" for="secondPayment">阶段款</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right"  id="secondPayment"  name="secondPayment" data-parsley-type="number" data-parsley-maxlength="16" value="" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		         </div>
		    </div>
		     <div class="form-group col-md-6 paymentRatio3 hidden">
		        <label class="control-label" for="paymentRatio3">阶段款比列</label>
		        <div>
		           <input type="text" class=" form-control input-sm fixed-number text-right" id="paymentRatio3" readonly="readonly" name="paymentRatio3" data-parsley-type="number"
		            data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例"  onblur="loseBlur(this.value)">
					 <a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 thirdPayment hidden">
		        <label class="control-label" for="thirdPayment">阶段款</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="thirdPayment"   name="thirdPayment" data-parsley-type="number" data-parsley-maxlength="16" value="" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div> 
		    <div class="form-group col-md-6 resident">
		        <label class="control-label" for="priceDocument">价格文件</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="priceDocument" name="priceDocument" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 not-resident">
		        <label class="control-label" for="invoiceType">发票类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="invoiceType" name="invoiceType" data-parsley-maxlength="30" value=""/>
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
		           <!--  <input type="text" class="form-control input-sm field-editable"  id="increment" name="increment" data-parsley-maxlength="30" value=""/>
		       		 --><a href="javascript:;" class="btn btn-sm btn-default">%</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="conAgent">经办人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="conAgent"  name="conAgent" data-parsley-maxlength="100" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="signDate">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="signDate"  name="signDate" data-parsley-maxlength="100" value="" data-parsley-required="true">
		        </div>
		    </div>
		</form>
	<!-- 	<div class="clearboth modifyHistoryDiv">
		<div class="">
 		<h4 class="m-t-14 m-l-6"><strong>修改历史</strong></h4>
 		</div>
 		<table id="modifyHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
  		 <thead>
          	<tr>
              <th>工程名称</th>
              <th>工程编号</th>
              <th>修改日期</th>
              <th>修改人</th>
        	</tr>
     		</thead>
		</table>
	</div> -->
    </div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    //表单样式适应
    $("#agreementModifyform").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    //查右侧工程详述
    trSData.t&&trSData.t.cgetDetail('agreementModifyform','agreementModify/querySupplementalContractDetail','',getDetailBack); 
  
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
        if($("#agreementModifyform").parsley().isValid()){
        	$("#flag").val("0");
        	$("#modifyStatus").val("3");  //修改过的协议，但处于未推送状态
        	console.log($("#isPrint").val());
        	var data=$("#agreementModifyform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'agreementModify/saveSupplementalAgreement',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "true"){
                		$("#agreementModifyform input,#agreementModifyform textarea").val('');
                		$(".editbtn").addClass("hidden");
                		$("#agreementModifyTable").cgetData();  //列表重新加载
                	}
                	var myoptions = {
                        	title: "提示信息",
                        	content: content,
                        	accept: saveDone,
                        	chide: true,
                        	icon: "fa-check-circle"
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("协议签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#agreementModifyform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#agreementModifyform").parsley().validate();
        }
        
    }); 
 var saveDone = function(){
	 $(".editbtn").addClass("hidden");
	 /* 选定的行查询信息到详述区*/
	 trSData.t&&trSData.t.cgetDetail('agreementModifyform','agreementModify/querySupplementalContractDetail','',getDetailBack); 
 }  
 
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	 //清空表单
    	 $("#surveyDetailform input").val('');
    	 trSData.t.cgetDetail('agreementModifyform','constructContract/viewContract',''); 
    	 $("#agreementModifyform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["constructContractTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#agreementModifyform").parsley().reset();
    });
 	
    //推送
    $(".pushBtn").on("click",function(){
    	$('body').cgetPopup({
			title: '提示信息！',
			content: "确定要修改补充协议吗？",
			accept: sureDone,
			chide:false,
			icon: "fa-warning"
		});
    })
    
    var sureDone=function(){
    	if($("#agreementModifyform").parsley().isValid()){
        	$("#flag").val("1");
        	$("#modifyStatus").val("0");
        	var data=$("#agreementModifyform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'agreementModify/saveSupplementalAgreement',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "推送成功！";
                	 if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "补充协议编号已存在，请修改！";
                	}else if(data === "true"){ 
                		$("#agreementModifyform input,#agreementModifyform textarea").val('');
                		$("#agreementModifyTable").cgetData();  //列表重新加载
                	 } 
                	var myoptions = {
                        	title: "提示信息",
                        	content: content,
                        	accept: saveDone,
                        	chide: true,
                        	icon: "fa-check-circle",
                        	newpop:'new'
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("协议签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#agreementModifyform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#agreementModifyform").parsley().validate();
        }
    }
    $("#houseNum").on("change",function(){
    	if($("#resident").val()==$("#scType").val()){
    		$("#scAmount").val((new Number($("#houseNum").val())*new Number($("#houseAmount").val())).toFixed(2));
        }else{
        }
	});
    //付款方式监听---开始
	$("#payType").change(function(){
    if($(this).val()=="1"){  //一次付清
    	//首付款录入
    	//$('.firstPayment').show();
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').addClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    	$(".paymentRatio1").removeClass("hidden");
    	$(".paymentRatio2").addClass("hidden");
    	$(".paymentRatio3").addClass("hidden");
    //	$("#contractDetailform").styleFit();
    }else if($(this).val()=="2"){  //分二期
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    	$(".paymentRatio1").removeClass("hidden");
    	$(".paymentRatio2").removeClass("hidden");
    	$(".paymentRatio3").addClass("hidden");
    }if($(this).val()=="3"){  //分三期
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').removeClass("hidden");
    	$(".paymentRatio1").removeClass("hidden");
    	$(".paymentRatio2").removeClass("hidden");
    	$(".paymentRatio3").removeClass("hidden");
    }
     $("#paymentRatio1").val('');
     var totaAmount = $("#scAmount").val();  //协议价款
    //如果是一次性付清首付比列为100%
    if ($(this).val()=='1'){
    	$("#paymentRatio1").val('100');   //首付比例直接为100%
    	$("#firstPayment").val(totaAmount);  //金额为全款，即是合同总金额
    	$("#paymentRatio3").val('');
    	$("#paymentRatio2").val('');
    	$("#secondPayment").val('');
    	$("#thirdPayment").val("");
    	$("#paymentRatio1").attr("readonly","readonly");
    }else if($(this).val()=='2'){   //若是两次付清
    	$("#paymentRatio1").val('');
    	$("#firstPayment").val('');
    	$("#paymentRatio3").val('');
    	$("#thirdPayment").val('');
    	$("#paymentRatio2").val('');
    	$("#secondPayment").val('');
    	$("#firstPayment").attr("readonly",true);
    	$("#secondPayment").attr("readonly",true);
    	$("#paymentRatio1").attr("readonly",false);
    	$("#paymentRatio2").attr("readonly",true);
    }else if($(this).val()=='3'){   //若是三次付清
    	$("#paymentRatio1").val('');
    	$("#paymentRatio2").val('');
    	$("#firstPayment").val('');
    	$("#secondPayment").val('');
    	$("#paymentRatio3").val('');
    	$("#thirdPayment").val("");
    	$("#paymentRatio1").attr("readonly",false);
    	$("#paymentRatio2").attr("readonly",false);
    	$("#paymentRatio3").attr("readonly",true);         
    }
});//付款方式监听---结束
//失去焦点事件--开始，用于验证用户输入值的格式是否正确
var loseBlur=function(paymentRate){
	   var reg=/^[0-9]+\.[0]+$/; //正则表达式,验证是否是整数
	  if((new Number(paymentRate))>100 || !reg.test(paymentRate)){  //判断是是否在1到100之间且为整数
			$("body").cgetPopup({
				title: '提示',
				content: '请输入1到100之间的整数!',
				ahide:true,
				atext:'确定'
			});
	   return false;  //终止程序
	   }  
	  CalculationFunction();  //调用函数计算付款比例和所付金额
 }//失去焦点事件--结束
	
 //此函数用于计算付款比例和付款金额--开始
var  CalculationFunction=function(){
	   var totaAmount = new Number($("#scAmount").val());  //协议价款
	   var payNumber = $("#payType").val();    //分几期付款，1代表全款，2代表分两期，3代表分三期
	   /**根据分期比例和合同总金额自动计算每期付款多少*/
	   if(payNumber=='2'){  //分两期付款
			var paymentRatio1=new Number($("#paymentRatio1").val());  //得到首付比列
			if(paymentRatio1>0){
				$("#firstPayment").val((totaAmount*(paymentRatio1/100.00)).toFixed(2));  //根据首付比列计算首付多少钱
				$("#paymentRatio2").val((100.00-paymentRatio1)+'.00');    //计算第二次付款的比列
				$("#secondPayment").val((((100.00-paymentRatio1)/100.00)*totaAmount).toFixed(2));  //计算第二期付款多少钱
			}
	   }else if(payNumber=='3'){ //分三期付清
		   var paymentRatio1=new Number($("#paymentRatio1").val());  //得到首付比列
		   var paymentRatio2=new Number($("#paymentRatio2").val());  //得到二期付款比例
		   if(paymentRatio1>0){
			   $("#firstPayment").val(totaAmount*(paymentRatio1/100.00));  //根据首付比列计算首付多少钱 
		   }
		   if(paymentRatio1>0 && paymentRatio2>0){
			   if((paymentRatio1+paymentRatio2)<=100.00){  //判断前两期首付比例是否小于或者等于100
				   $("#firstPayment").val((totaAmount*(paymentRatio1/100.00)).toFixed(2));  //根据首付比列计算首付多少钱 
				   $("#secondPayment").val((totaAmount*(paymentRatio2/100.00)).toFixed(2));  //计算第二期付款多少钱
				   $("#paymentRatio3").val((100.00-paymentRatio1-paymentRatio2)+'.00');    //计算第三次付款的比列
				   $("#thirdPayment").val(((100.00-paymentRatio1-paymentRatio2)/100.00*totaAmount).toFixed(2));  //计算第三期付多少钱
			   }else{
				   $("body").cgetPopup({
						title: '提示',
						content: '总的付款比例不得超过100%!',
						ahide:true,
						atext:'确定'
					});
				   return false; //终止程序
			   }	  
		   }   
	   }
} //函数--结束

//协议价款监听--开始，协议价款改变时付款金额跟随改变
$("#scAmount").blur(function(){
	CalculationFunction(); //协议价款改变时付款金额跟随改变
})//协议价款监听--结束

//户数监听--开始，协议价款改变时付款金额跟随改变
$("#houseNum").blur(function(){
	CalculationFunction(); //协议价款改变时付款金额跟随改变
})//户数--结束
</script>
<!-- ================== END PAGE LEVEL JS ================== -->