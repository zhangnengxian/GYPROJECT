<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="clearboth form-box">
    	<!-- 施工合同 -->
    	<div class="form-group col-md-6 cuDisValide suDisValide duDisValide">
    		<h3>安装合同</h3>
    		<form class="form-horizontal" id="detail_contractform"  action="">
		       	<div class="form-group col-md-6 ">
			    	<label class="control-label" for="conNo">合同编号</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-editable conNo"  id="conNo" name="conNo" value=""/>
			        </div>
			    </div>
			     <div class="form-group col-md-6">
		            <label class="control-label" for="contractType">合同类型</label>
	            	<div>
	               	   <select class="form-control input-sm field-not-editable" id="contractType"  name="contractType" >
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
			            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="100" value=""/>
			        </div>
			    </div>
			    <%--<div class="form-group col-md-6">--%>
			        <%--<label class="control-label" for="projAddr">工程地点</label>--%>
			        <%--<div>--%>
			            <%--<input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value=""/>--%>
			        <%--</div>--%>
			    <%--</div>--%>
			     <%--<div class="form-group col-md-6">--%>
			        <%--<label class="control-label" for="projectTypeDes">工程类型</label>--%>
			        <%--<div>--%>
			            <%--<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" data-parsley-maxlength="100" value=""/>--%>
			        <%--</div>--%>
			    <%--</div>--%>
			     <%--<div class="form-group col-md-6">--%>
			        <%--<label class="control-label" for="contributionModeDes">出资方式</label>--%>
			        <%--<div>--%>
			            <%--<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" data-parsley-maxlength="100" value=""/>--%>
			        <%--</div>--%>
			    <%--</div>--%>
			     <%--<div class="form-group col-md-6">--%>
			        <%--<label class="control-label" for="deptName">业务部门</label>--%>
			        <%--<div>--%>
			            <%--<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" data-parsley-maxlength="100" value=""/>--%>
			        <%--</div>--%>
			    <%--</div>--%>
			    <%--<div class="form-group col-md-12">--%>
		            <%--<label class="control-label" for="projScaleDes">工程规模</label>--%>
		            <%--<div>--%>
		                <%--<textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2"></textarea>--%>
		            <%--</div>--%>
	            <%--</div>--%>
			    <div class="form-group col-md-12">
			        <label class="control-label" for="custName">燃气用户</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="custName" name="custName" data-parsley-maxlength="200" value=""/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="custPhone">联系方式</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="13" value=""/>
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="custEntrustRepresent">甲方法定代表</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="custEntrustRepresent" name="custEntrustRepresent" data-parsley-maxlength="20" value="" />
			        </div>
			    </div>
			    <div class="form-group col-md-6 clearboth ">
			        <label class="control-label" for="gasComp">燃气经营企业</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="gasComp" name="gasComp" data-parsley-maxlength="50" value=""/>
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="gasCompPhone">联系方式</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="gasCompPhone" name="gasCompPhone" data-parsley-maxlength="13" value=""/>
			        </div>
			    </div>
			    
			    <div class="form-group col-md-12">
		            <label class="control-label" for="conScope">承包范围</label>
		            <div>
		                <textarea class="form-control field-editable" name ="conScope" id="conScope" rows="2" data-parsley-maxlength="200"></textarea>
		            </div>
	            </div>
	            <div class="form-group col-md-6">
		            <label class="control-label" for="contractMethod">承包方式</label>
	            	<div>
	               	   <select class="form-control input-sm field-editable" id="contractMethod"  name="contractMethod" >
		 		      	<c:forEach var="enums" items="${contractMethod}">
							<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		              </select>
		            </div>
				</div>
				 <div class="form-group col-md-6">
			        <label class="control-label" for="timeLimit">工期</label>
			        <div>
			        	<input type="text" class=" form-control input-sm field-editable" id="timeLimit"  name="timeLimit" data-parsley-maxlength="10" value="" >
			        	<a href="javascript:;" class="btn btn-sm btn-default">天</a>
			        </div>
			    </div>
			    <div class="form-group col-md-12 REMARK hidden">
		            <label class="control-label" for="remark">备注</label>
		            <div>
		                <textarea class="form-control field-editable" name ="remark" id="remark" rows="2" data-parsley-maxlength="1000">
		                </textarea>
		            </div>
	            </div>
				 <div class="form-group col-md-6 resident">
			        <label class="control-label" for="houseDate">交房日期</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="houseDate"  name="houseDate" data-parsley-maxlength="100"  value="">
			        </div>
			    </div>
			    <div class="form-group col-md-6 not-resident amountAbout">
			        <label class="control-label" for="budgetCost">预算造价</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="budgetCost"  name="budgetCost" data-parsley-maxlength="100" value="" >
			        </div>
			    </div>
			    <div class="form-group col-md-6 resident">
			        <label class="control-label" for="household">安装户数</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable text-right" id="household"  name="household" data-parsley-type="number" data-parsley-maxlength="10" value="">
			        </div>
			    </div>
			    <div class="form-group col-md-6 resident amountAbout">
			        <label class="control-label" for="hoseAmount">每户金额</label>
			        <div>
			           	<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="hoseAmount"  name="hoseAmount" data-parsley-type="number" data-parsley-maxlength="16" value=""  >
						<a href="javascript:;" class="btn btn-sm btn-default">元</a>
					</div>
			    </div>
			     <div class="form-group col-md-6 amountAbout">
			        <label class="control-label" for="contractAmount">合同金额</label>
			        <div>
			           	<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="contractAmount"  name="contractAmount" data-parsley-type="number" data-parsley-maxlength="16" value="">
						<a href="javascript:;" class="btn btn-sm btn-default">元</a>
					</div>
			    </div>
			    <div class="form-group col-md-6 amountAbout">
		            <label class="control-label" for="payType">付款方式</label>
	            	<div>
	               	   <select class="form-control input-sm field-editable" id="payType"  name="payType" >
		                <c:forEach var="enums" items="${payType}">
							<option value="${enums.ptId}" >${enums.payTypeDes}</option>
		                </c:forEach>
		              </select>
		            </div>
				</div>
				<div class="form-group col-md-6 firstPayment hidden amountAbout">
			        <label class="control-label" for="firstPayment">首付款</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="firstPayment"  name="firstPayment" data-parsley-type="number"
			            data-parsley-maxlength="16" value="">
						<a href="javascript:;" class="btn btn-sm btn-default">元</a>
			        </div>
			    </div>
			    <div class="form-group col-md-6 secondPayment hidden amountAbout">
			        <label class="control-label" for="secondPayment">阶段款</label>
			        <div>
			           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="secondPayment"  name="secondPayment" data-parsley-type="number" data-parsley-maxlength="16" value="">
						<a href="javascript:;" class="btn btn-sm btn-default">元</a>
			         </div>
			    </div>
				<div class="form-group col-md-6 thirdPayment hidden amountAbout">
			        <label class="control-label" for="thirdPayment">阶段款</label>
			        <div>
			           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="thirdPayment"  name="thirdPayment" data-parsley-type="number" data-parsley-maxlength="16" value="">
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
			           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16">
			           <a href="javascript:;" class="btn btn-sm btn-default">%</a>
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="invoiceType">发票类型</label>
			        <div>
			           <!-- <input type="text" class=" form-control input-sm field-editable " id="invoiceType" name="invoiceType" data-parsley-maxlength="20" value="" data-parsley-maxlength="20">
			       		 -->
			       		 <select class=" form-control input-sm field-editable  text-right" id="invoiceType"  name="invoiceType" data-parsley-maxlength="16" >
						<option value=""></option>
						<option value="1">增值税专用发票</option>
						<option value="2">增值税普通发票</option>
					</select>
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="openBank">开户行</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable" id="openBank"  name="openBank"  value="" data-parsley-maxlength="100">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="account">开户帐号</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable" id="account"  name="account"  value="" data-parsley-maxlength="100">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="unitAddress">单位地址</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable " id="unitAddress"  name="unitAddress"  value="" data-parsley-maxlength="200">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="conAgent">经办人</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable " id="conAgent"  name="conAgent" data-parsley-maxlength="20" value="${loginInfo.staffName}">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="">签订日期</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="signDate"  name="signDate" data-parsley-maxlength="100" value="">
			        </div>
			    </div>
			    <div class="form-group col-md-12">
		            <label class="control-label" for="supplementClause">补充条款</label>
		            <div>
		                <textarea class="form-control field-editable" name ="supplementClause" id="supplementClause" rows="2" data-parsley-maxlength="1000">
		                </textarea>
		            </div>
	            </div>
			</form>
    	</div>
    	<!-- 施工合同 end -->
        <!-- 分包协议 -->
        <div class="form-group col-md-6 suDisValide duDisValide">
	        <h3>施工合同</h3>
        	<form class="form-horizontal" id="subContractDetailform" action="">
				<input type="hidden" id="projId" name="projId"/>
	       		<input type="hidden" id="scId" name="scId"/>
	       		<input type="hidden" id="flag" name="flag"/>
	       		<div class="form-group col-md-6 ">
			    	<label class="control-label" for="scNo">合同编号</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo"  value=""/>
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
			    
			    <div class="form-group col-md-12">
			        <label class="control-label" for="deptName">发包人</label>
			        <div>
			       		<input type="hidden"  id="deptId" name="deptId" data-parsley-maxlength="20"  value="" />
			       		<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" data-parsley-maxlength="50"  value="" />
			        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>
			       		
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
			        <label class="control-label" for="gasComLegalRepresent">现场代表</label>
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
			        	<%--<a id="cuPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择分包单位"><i class="fa fa-search"></i></a>--%>
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
			    <div class="form-group col-md-6 clearboth">
			    	<!-- 现场负责人改为施工员 -->
			        <label class="control-label" for="cuResponsiblePerson">施工员</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable"  id="cuResponsiblePerson" name="cuResponsiblePerson" data-parsley-maxlength="20" value=""/>
			        </div>
			    </div>
			    <div class="form-group col-md-12">
			    	<!-- 承包范围改为工程内容 -->
		            <label class="control-label" for="scScope">工程内容</label>
		            <div>
		                <textarea class="form-control field-editable" name ="scScope" id="scScope" rows="3" data-parsley-maxlength="200"></textarea>
		            </div>
	            </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="scPlannedStartDate">开工日期</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable datepicker-default timestamp" id="scPlannedStartDate"  name="scPlannedStartDate" value="">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="scPlannedEndDate">竣工日期</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="scPlannedEndDate"  name="scPlannedEndDate" value="">
			        </div>
			    </div>
				<div class="form-group col-md-6 payType">
					<label class="control-label" for="payType">预付款方式</label>
					<div>
						<select class="form-control input-sm field-editable" id="payType1"  name="payType">
							<option value="" ></option>
						</select>
					</div>
				</div>
	            <div class="form-group col-md-6">
	            	<!-- 承包方式改为合同价款方式 -->
			        <label class="control-label" for="scType">价款方式</label>
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
			    <div class="form-group col-md-6">
			        <label class="control-label" for="quAmount">工程造价</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="quAmount" name="quAmount" data-parsley-maxlength="13" value=""/>
			        </div>
			    </div>
				<div class="form-group col-md-6">
					<label class="control-label" for="scAmount">合同金额</label>
					<div>
						<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="scAmount" name="scAmount" data-parsley-maxlength="13" value="" />
					</div>
				</div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="scSignDate">签订日期</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="scSignDate" name="scSignDate" value="">
			        </div>
			    </div>
			</form>
        </div>
        <div class="clearboth cuDisValide suDisValide duDisValide amountAbout">
	        <h3 class="textCetent">收付流水</h3>
	        <table class="table table-hover table-striped table-bordered nowrap" width="100%" id="accrualsTable" >
	       			<thead>
	       			<tr>
	       			    <th></th>
	       			    <th>客户名称</th>
	       				<th>操作时间</th>
	        			<th>款项类型</th>
						<th>已收款</th>
	        			<th>金额</th>
	        			<th>收付标志</th>
	        			<th>流水操作人</th>
	        			<th>状态</th>
	       			</tr>
	       			</thead>
	       	</table>
       	</div>
    	<!-- 分包协议 end -->
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
    $("#detail_contractform,#subContractDetailform").toggleEditState().styleFit();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
  //隐藏配置属性
	if($("#hiddenConfig").val()!='' && $("#hiddenConfig").val()!=undefined &&  $("#hiddenConfig").val()!=null ){
		$("."+$("#hiddenConfig").val()).addClass("hidden");
	}
    var m = "id="+$("#projId").val();
    $.ajax({
        type: 'POST',
        url: 'projectView/projectDetailContract',
        data: m,
        dataType: 'json',
        success: function (data) {
            //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
            var selects = $("#detail_contractform").find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
            selects.removeAttr("disabled");
            //表单反序列化填充值
            $("#detail_contractform").deserialize(data);
            $("#detail_contractform").initFixedNumber();
            //有disabled属性的下拉菜单元素对象重新添加禁用属性
            selects.attr("disabled","disabled");
            $("#detail_contractform").fadeIn(200);
            var payType = data.payType;
        	if($("#contractType").val()=='11'){
        		$(".not-resident").addClass("hidden");
        		$(".resident").removeClass("hidden");
        	}else{
        		$(".resident").addClass("hidden");
        		$(".not-resident").removeClass("hidden");
        	}
        	if(payType=="1"||payType=="3"||payType=="7"){
            	//首付款录入
            	$('.firstPayment').removeClass("hidden");
            	$('.secondPayment').addClass("hidden");
            	$('.thirdPayment').addClass("hidden");
            	$("#secondPayment").val("");
            	$("#thirdPayment").val("");
            }else if(payType=="2"||payType=="4"||payType=="6"){
            	$('.firstPayment').removeClass("hidden");
            	$('.secondPayment').removeClass("hidden");
            	$('.thirdPayment').addClass("hidden");
            	$("#thirdPayment").val("");
            }else{
            	$('.firstPayment').removeClass("hidden");
            	$('.secondPayment').removeClass("hidden");
            	$('.thirdPayment').removeClass("hidden");
            }
        	if($("#contractMethod").val()=='4'){
        		$(".REMARK").removeClass("hidden");
        	}else{
        		$(".REMARK").addClass("hidden");
        	}
        	$("#detail_contractform").styleFit();
            
            $('html, body').animate({
                scrollTop: $("body").offset().top
            }, 250);

            //隐藏配置属性
            console.info("hiddenConfig",$("#hiddenConfig").val());
            if($("#hiddenConfig").val()!='' && $("#hiddenConfig").val()!=undefined &&  $("#hiddenConfig").val()!=null ){
                $("."+$("#hiddenConfig").val()).addClass("hidden");
            }

            if($("#staffRemoveClass").val()!='' && $("#staffRemoveClass").val()!=undefined &&  $("#staffRemoveClass").val()!=null ){
                $("."+$("#staffRemoveClass").val()).removeClass("hidden");
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
        	printXHRError("getDetail", "施工合同详情查询失败", jqXHR, textStatus, errorThrown);
        }
    });
    $.ajax({
        type: 'POST',
        url: 'projectView/projectDetailSubContract',
        data: m,
        dataType: 'json',
        success: function (data) {
            //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
            var selects = $("#subContractDetailform").find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
            selects.removeAttr("disabled");
            //表单反序列化填充值
            $("#subContractDetailform").deserialize(data);
            $("#subContractDetailform").initFixedNumber();
            
            $("#payType1,#scType,#progressType").empty();    //reset
            $("#payType1,#scType,#progressType").append('<option value=""></option>');//加空行
        	$.each(data.payTypes,function(n, v){
        		if (v.payType) {
        			$("#payType1").append('<option value=' + v.ptId + '>' + v.payType + '</option>');
        		} else {
        			$(".payType").addClass("hidden");
        		}
        		 $("#payType1").val(data.payType);  //赋值
        		if(v.scType){
        			$("#scType").append('<option value='+v.ptId+'>' + v.scType + '</option>');
        		}
        		$("#scType").val(data.scType);     //赋值
        		if (v.progressType) {
        			$("#progressType").append('<option value=' + v.ptId + '>' + v.progressType + '</option>');
        		}
        		$("#progressType").val(data.progressType);  //赋值
            });
            
            //有disabled属性的下拉菜单元素对象重新添加禁用属性
            selects.attr("disabled","disabled");
            $("#subContractDetailform").fadeIn(200);
            $('html, body').animate({
                scrollTop: $("body").offset().top
            }, 250);
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	printXHRError("getDetail", "分包协议详情查询失败", jqXHR, textStatus, errorThrown);
        }
    });
  //初始化收费信息(应付流水)
    var accrualsData = {};
    var chargedatainit= function() {
    	"use strict";
        if ($('#accrualsTable').length !== 0) {
        	var projId = $("#projId").val();
        	if(projId === ""){
        		projId="-1";
        	}
        	accrualsData.projId = projId;
        	$('#accrualsTable').on( 'init.dt',function(){
       		//默认选中第一行
      		$(this).bindDTSelected(trAccrualsBack,false);
        	$('#accrualsTable').hideMask();
            }).DataTable({
            	language: {
                    url: 'js/dt-chinese.json'
                },
               lengthMenu: [5],
               dom: 'rt',
               buttons: [
               ],
              //启用服务端模式，后台进行分段查询、排序
    		   serverSide:true,
    		   select: true,  //支持多选
    		   ordering:false,
    	       ajax: {
    	             url: 'charge/queryAllAccruRecord',
    	             type:'post',
    	             data: function(d){
    	                   $.each(accrualsData,function(i,k){
    	                   		d[i] = k;
    	                   	});
    	                   	
    	                },
    	                dataSrc: 'data'
    	            },        
               responsive: {
               	   details: {
            		   renderer: function ( api, rowIdx, columns ){
            			   return renderChild(api, rowIdx, columns);
            		   }
            	   }
               },
               columns: [
                    {"data":"arId",className:"none never"},
                    {"data":"projCustName"},
    	  			{"data":"arDate",render:changeDate},
    	  			{"data":"arTypeDes"},
    	  			{"data":"receiveAmount"},
    	  			{"data":"arCost",className:"text-right"},
    	  			{"data":"arFlagDes"},
    	  			{"data":"staff.staffName"},
    	  			{"data":"arStatusDes"}
    			],
    			columnDefs: [{
    				"targets": 0,
    			    "visible":false
    			},
    			{
    				"targets": 4,
    				 render: function ( data, type, row, meta ) {
    					if(type === "display"){
    						if(data === null){
    							data = "";
    						}else if(data !==null && data!==""){
    							data = data.toFixed(2);
    						}
    					}
    					return data;
    				}
    			},
    			{
    				"targets": 7,
    				 render: function ( data, type, row, meta ) {
    					if(row.arFlag=="-1"){
    						if(row.resultFlag=="0"){
    							data = "已付款";
    						}else if(row.arStatus=="2"){
    							data = "已登记付款";
    						}else if(row.arStatus=="3"){
    							data = "已付款";
    						}else{
    							data = "未付款";
    						}
    					}
    					return data;
    				}
    			}]
           });
       }
    };
    chargedatainit();
    //时间格式转换
    function changeDate(e){
    	return format(e,"all");
    }
    var trAccrualsBack = function(){
    	
    }



</script>
<!-- ================== END PAGE LEVEL JS ================== -->