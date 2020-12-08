<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_globalView" action="/" method="POST">
        <%-- <div class="form-group col-md-6">
            <label class="control-label" for="">工程大类</label>
            <div>
                <select class="form-control input-sm field-editable" id="projLtypeId"  name="projLtypeId"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${projLtype}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
				</select>
            </div>
        </div> --%>
       <%--  <div class="form-group col-md-6">
            <label class="control-label" for="area">区域</label>
            <div >
                <select class="form-control input-sm field-editable" id="area"  name="area"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${area}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
				</select>
            </div>
        </div> --%>
        <div class="form-group col-md-6 ">
            <label class="control-label" for="projName">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="custName">申报单位</label>
            <div >
                <input type="text" class="form-control input-sm"  name="custName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="projLtypeId">工程类型</label>
            <div >
               <!--  <input type="text" class="form-control input-sm"  name="projLtypeId"/> -->
                <select class="form-control input-sm" id="projLtypeId" name="projLtypeId">
                	<option></option>
                	<c:forEach var="enums" items="${projLtype }">
                		<option value="${enums.projTypeId }">${enums.projConstructDes }</option>
                	</c:forEach>
                </select>
            </div>
        </div>
            <div class="form-group col-md-6">
                <label class="control-label" for="contributionMode">出资方式</label>
                <div >
                    <select class="form-control input-sm" id="contributionMode" name="contributionMode">
                        <option></option>
                        <c:forEach var="enums" items="${contributionMode}">
                            <option value="${enums.id }">${enums.contributionDes }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        <div class="form-group col-md-6 hidden isIntelligentMeter">
            <label class="control-label" for="isIntelligentMeter">是否是智能表</label>
            <div >
                <select class="form-control input-sm" id="isIntelligentMeter" name="isIntelligentMeter">
                	<option value=""></option>
                	<option value="1">是</option>
                	<option value="0">否</option>
                </select>
            </div>
        </div>
       <!--  <div class="form-group col-md-6">
            <label class="control-label" for="paNo">受理单号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="paNo"/>
            </div>
        </div> -->
        <div class="form-group col-md-6">
            <label class="control-label" for="conNo">合同编号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="conNo"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="projAddr">工程地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="projScaleDes">工程规模</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projScaleDes"/>
            </div>
        </div>

        <%-- <div class="form-group col-md-6 hidden feedbackState">
            <label class="control-label" for="projStatusId">反馈状态</label>
            <div>
                <select class="form-control input-sm field-editable" id="feedbackState"  name="feedbackState"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${feedbackState}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="designStationId">设计所</label>
            <div>
                <select class="form-control input-sm field-editable" id="designStationId"  name="designStationId"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${designStationId}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
            </div>
        </div> --%>
        <div class="form-group col-md-6">
            <label class="control-label" for="surveyer">勘察人</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="surveyer"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="designer">设计人</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="designer"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="budgeter">预算员</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="budgeter"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="costMember">造价员</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="costMember"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="operator">合同员</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="operator"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="settlementer">结算员</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="settlementer"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="builder">现场代表</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="builder"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="suJgj">现场监理</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="suJgj"/>
            </div>
        </div>
       <%--  <div class="form-group col-md-6">
            <label class="control-label" for="">施工管理处</label>
            <div>
                <select class="form-control input-sm field-editable" id="managementOfficeId"  name="managementOfficeId"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${manageOfficeList}">
						<option value="${enums.id}" >${enums.name}</option>
	                </c:forEach>
				</select>
            </div>
        </div> --%>
        <div class="form-group col-md-6">
            <label class="control-label" for="">施工单位</label>
            <div>
                <select class="form-control input-sm field-editable" id="cuId"  name="cuId"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${culist}">
						<option value="${enums.unitId}" >${enums.shortName}</option>
	                </c:forEach>
				</select>
            </div>
        </div>
         <div class="form-group col-md-6">
            <label class="control-label" for="budgeterAudit">分包预算员</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="budgeterAudit"/>
            </div>
        </div>

        <div class="form-group col-md-12">
            <label class="control-label" for="">工程状态</label>
            <div class="input-group input-daterange">
                <div style="width: 48%;">
                    <select class="form-control input-sm field-editable" id="startStatusId"  name="startStatusId"  >
                        <option value=""></option>
                        <c:forEach var="enums" items="${projStatusId}">
                            <option value="${enums.value}" >${enums.message}</option>
                        </c:forEach>
                    </select>
                </div>
                <span class="input-group-addon">至</span>
                <div style="width: 48%;">
                    <select class="form-control input-sm field-editable" id="endStatusId"  name="endStatusId"  >
                        <option value=""></option>
                        <c:forEach var="enums" items="${projStatusId}">
                            <option value="${enums.value}" >${enums.message}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group col-md-12">           
            <label class="control-label" for="">受理日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="acceptDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="acceptDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="">勘察日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="surveyDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="surveyDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="">设计日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="duCompleteDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="duCompleteDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="">预算日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="budgetDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="budgetDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="">合同签订日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="contractSignDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="contractSignDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="">验收日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="acceptanceDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="acceptanceDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="">结算日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="settlementDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="settlementDateEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $('#searchForm_globalView').styleFit();
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
    $("#projStatusId").change(function(){
		if($(this).val()=="1005"){
		   	$(".feedbackState").removeClass("hidden");
		    $('#searchForm_globalView').styleFit();
		}else{
			$("#feedbackState").val("");
			$(".feedbackState").addClass("hidden");
			$('#searchForm_globalView').styleFit();
		}
	})
	$("#projLtypeId").change(function(){
		if($(this).val()=='11'){
			$(".isIntelligentMeter").removeClass("hidden");
		}else{
			$("#isIntelligentMeter").val("");
			$(".isIntelligentMeter").addClass("hidden");
		}
	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->