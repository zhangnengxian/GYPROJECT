<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 acceptSaveBtn" >保存</a>
		<a href="javascript:;" class="btn btn-info btn-sm m-l-5 saveBtn" >推送</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="acceptApplyForm" action="stationSettlement/stationSettlementSave">
    		<!-- <button type="reset" class="hidden" id="reset"/> -->
    		<input type="hidden" id="projId" name="projId" />
    		<input type="hidden" id="corpId" name="corpId" />
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="projNo">工程编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50" value="" />
				</div>
			</div>
			<div class="form-group col-md-12 col-sm-12">
				<label class="control-label" for="projName">工程名称</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
				</div>
			</div>
			<div class="form-group  col-md-12 col-sm-12">
				<label class="control-label" for="projAddr">工程地点</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value=""/>
				</div>
			</div>
			<div class="form-group col-md-12 col-sm-12">
				<label class="control-label" for="projScaleDes">工程规模</label>
				<div>
					<textarea  class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes" data-parsley-maxlength="200" /></textarea>
				</div>
			</div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
			<div class="form-group col-md-6 col-sm-12">
				<!-- 新加字段 -->
				<label class="control-label" for="deptId">申请部门</label>
				<div>
					<select class="form-control input-sm field-not-editable" id="deptId"  name="deptId" >
						<option value=""></option>
						<c:forEach var="enums" items="${department}">
							<option value="${enums.correlatedInfoId}" >${enums.correlatedInfoDes}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<!-- 新加字段 -->
				<label class="control-label">申请日期</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="acceptDate"  name="acceptDate" data-parsley-required="true" value="" >
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<!-- 新加字段 -->
				<label class="control-label" for="projectType">工程类型</label>
				<div>
					<select class="form-control input-sm field-not-editable" id="projectType"  name="projectType" >
						<c:forEach var="enums" items="${projLtype}">
							<option value="${enums.correlatedInfoId}">${enums.correlatedInfoDes}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="contributionMode">出资方式</label>
				<div>
					<select class="form-control input-sm field-not-editable" id="contributionMode"  name="contributionMode" data-parsley-required="true" >
						<c:forEach var="enums" items="${contributionMode}">
							<option value="${enums.correlatedInfoId}">${enums.correlatedInfoDes}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<!-- 新加字段 -->
				<label class="control-label" for="totalInvestment">总投资(万元)</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="totalInvestment" name="totalInvestment"  />
				</div>
			</div>

			<div class="form-group col-md-6 col-sm-12 clearboth">
				<!-- 新加字段 -->
				<label class="control-label">拟开工日期</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="protocolStartingDate"  name="protocolStartingDate"  value="" >
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<!-- 新加字段 -->
				<label class="control-label" for="projectDuration">工期(天)</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable text-right"  id="projectDuration" name="projectDuration"  />
				</div>
			</div>
			<div class="form-group col-md-12 col-sm-12">
				<!-- 新加字段 -->
				<label class="control-label" for="acceptReason">立项理由</label>
				<div>
					<textarea  class="form-control input-sm field-not-editable"  id="acceptReason" name="acceptReason" /></textarea>
				</div>
			</div>

			<input type="hidden" class="projLtypeId" name="projLtypeId"/>
			<input type="hidden" name="acceptData" id="acceptData"/>
			<input type="hidden" name="proceduresData" id="proceduresData"/>
			<input type="hidden" name="contructionData" id="contructionData"/>
			<input type="hidden" name="settlementData" id="settlementData"/>
			<input type="hidden" name="finalAccountData" id="finalAccountData"/>

		    <div class="form-group col-md-6 col-sm-12 hidden">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="geologicalUnit">地勘单位</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-not-editable"  id="geologicalUnit" name="geologicalUnit"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 hidden">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="guNo">合同协议号</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-editable"  id="guNo" name="guNo"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 hidden">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="guAmount">合同金额</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-not-editable fixed-number"  id="guAmount" name="guAmount" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 hidden">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="guTerm">工作期限</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-not-editable"  id="guTerm" name="guTerm"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 hidden">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="cuName">完成时间</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-editable datepicker-default"  id="conNo" name="conNo"  value=""/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 col-sm-12 clearboth hidden">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="designUnit">设计单位</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-editable"  id="designUnit" name="designUnit"  value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 col-sm-12 hidden" >
		    	<!-- 新加字段 -->
		        <label class="control-label" for="duTerm">工作期限</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-editable"  id="duTerm" name="duTerm"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 hidden">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="duDate">完成时间</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-editable datepicker-default"   id="duDate" name="duDate"  value=""/>
		        </div>
		    </div>

		    <div class="form-group col-md-6 col-sm-12 clearboth">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="cuName">承建单位</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-not-editable" id="cuName" name="cuName" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="scAmount">合同金额</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="scAmount" name="scAmount" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="scPlannedTotalDays">建设周期</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-not-editable" id="scPlannedTotalDays" name="scPlannedTotalDays" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label">签订日期</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-not-editable datepicker-default" id="scSignDate" name="scSignDate"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label">验收日期</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-not-editable datepicker-default" id="jaDate" name="jaDate" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label">试运行日期</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-not-editable datepicker-default" id="pilotRunDate" name="pilotRunDate" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="endDeclarationCost">结算金额</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-editable fixed-number text-right" id="endDeclarationCost" name="endDeclarationCost" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label">结算时间</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-editable datepicker-default" id="settlementDate" name="settlementDate" value=""/>
		        </div>
		    </div>


			<div class="form-group col-sm-12">
				<h4><i class="fa fa-arrow-circle-o-right"></i>立项资料</h4>
			</div>
			<c:forEach var="enums" items="${acceptDatas}">
				<div class="form-group col-sm-12">
					<label class="control-label"></label>
					<div>
						<label>
							<input type="checkbox"  name="acceptDataDes" class="field-not-editable" value="${enums.sdId}"/>${enums.sdDes}
						</label>
					</div>
				</div>
			</c:forEach>

			<div class="form-group col-sm-12">
				<h4><i class="fa fa-arrow-circle-o-right"></i>建审资料</h4>
			</div>
			<c:forEach var="enums" items="${proceduresDatas}">
				<div class="form-group col-md-6 col-sm-12">
					<label class="control-label"></label>
					<div>
						<label>
							<input type="checkbox"  name="proceduresDataDes" class="field-not-editable" value="${enums.sdId}"/>${enums.sdDes}
						</label>
					</div>
				</div>
			</c:forEach>

			<div class="form-group col-sm-12">
				<h4><i class="fa fa-arrow-circle-o-right"></i>施工资料</h4>
			</div>
			<c:forEach var="enums" items="${contructionDatas}">
				<div class="form-group col-md-6 col-sm-12">
					<label class="control-label"></label>
					<div>
						<label>
							<input type="checkbox"  name="contructionDataDes" class="field-not-editable" value="${enums.sdId}"/>${enums.sdDes}
						</label>
					</div>
				</div>
			</c:forEach>

			<div class="form-group col-sm-12">
				<h4><i class="fa fa-arrow-circle-o-right"></i>结算资料</h4>
			</div>
			<c:forEach var="enums" items="${settlementDatas}">
				<div class="form-group col-md-6 col-sm-12">
					<label class="control-label"></label>
					<div>
						<label>
							<input type="checkbox"  name="settlementDataDes" class="field-editable" value="${enums.sdId}"/>${enums.sdDes}
						</label>
					</div>
				</div>
			</c:forEach>

			<div class="form-group col-sm-12">
				<h4><i class="fa fa-arrow-circle-o-right"></i>决算资料</h4>
			</div>
			<c:forEach var="enums" items="${finalAccountDatas}">
				<div class="form-group col-md-6 col-sm-12">
					<label class="control-label"></label>
					<div>
						<label>
							<input type="checkbox"  name="finalAccountDataDes" class="field-editable" value="${enums.sdId}"/>${enums.sdDes}
						</label>
					</div>
				</div>
			</c:forEach>

			<input type="hidden" name="flag" id="flag"/>
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
    //参数传false时，表单不可编辑
    $("#acceptApplyForm").toggleEditState(false);
    //表单样式适应
    $("#acceptApplyForm").styleFit();
    //时间插件
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('acceptApplyForm','stationAccept/viewStationProject','',scaleDetailRefresh);
    
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	 $("#acceptApplyForm,#scaleTableForm").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    });

    var getAccept = function(){
        var getAccept = $("input[name='acceptDataDes']:checked");
        var acceptDataDes="";
        getAccept.each(function(){
            acceptDataDes = acceptDataDes +$(this).val()+",";
        });
        if(acceptDataDes!==""){
            acceptDataDes = acceptDataDes.substring(0,acceptDataDes.length-1);
        }
        return acceptDataDes;
    };

    //建审资料
    var getProcedures = function(){
        var getProcedures = $("input[name='proceduresDataDes']:checked");
        var proceduresDataDes="";
        getProcedures.each(function(){
            proceduresDataDes = proceduresDataDes +$(this).val()+",";
        });
        if(proceduresDataDes!==""){
            proceduresDataDes = proceduresDataDes.substring(0,proceduresDataDes.length-1);
        }
        return proceduresDataDes;
    };

    //施工资料
    var getContruction = function(){
        var getContruction = $("input[name='contructionDataDes']:checked");
        var contructionDataDes="";
        getContruction.each(function(){
            contructionDataDes = contructionDataDes +$(this).val()+",";
        });
        if(contructionDataDes!==""){
            contructionDataDes = contructionDataDes.substring(0,contructionDataDes.length-1);
        }
        return contructionDataDes;
    };

    //结算资料
    var getSettlement = function(){
        var getSettlement = $("input[name='settlementDataDes']:checked");
        var settlementDataDes="";
        getSettlement.each(function(){
            settlementDataDes = settlementDataDes +$(this).val()+",";
        });
        if(settlementDataDes!==""){
            settlementDataDes = settlementDataDes.substring(0,settlementDataDes.length-1);
        }
        return settlementDataDes;
    };

    //决算资料
    var getFinalAccount = function(){
        var getFinalAccount = $("input[name='finalAccountDataDes']:checked");
        var finalAccountDataDes="";
        getFinalAccount.each(function(){
            finalAccountDataDes = finalAccountDataDes +$(this).val()+",";
        });
        if(finalAccountDataDes!==""){
            finalAccountDataDes = finalAccountDataDes.substring(0,finalAccountDataDes.length-1);
        }
        return finalAccountDataDes;
    };

    var dataBack = function () {
        var len = $('#stationSettlementTable').DataTable().ajax.json().data ? $('#stationSettlementTable').DataTable().ajax.json().data.length : $('#stationSettlementTable').DataTable().ajax.json().length;
        if(len<1){
            $(".editbtn").addClass("hidden");
            //清空右侧记录
            $("#acceptApplyForm").formReset();
            //表单样式适应
            $("#acceptApplyForm").styleFit();
            //参数传false时，表单不可编辑
            $("#acceptApplyForm").toggleEditState(false);
        }
    }
    //保存回调
    var saveBack = function () {
        //todo...
//        $(".editbtn").addClass("hidden");
        $("#stationSettlementTable").cgetData(true,dataBack);  //列表重新加载
    }
    //保存
    $(".acceptSaveBtn").on("click",function(){
        $(".projLtypeId").val($("#projectType").val());

        $("#acceptData").val(getAccept());//立项
        $("#proceduresData").val(getProcedures());//建审
        $("#contructionData").val(getContruction());//施工
        $("#settlementData").val(getSettlement());//结算
        $("#finalAccountData").val(getFinalAccount());//决算
        $("#acceptApplyForm").cformSave('stationSettlementTable',saveBack,true); //列表重新加载
    });

    //推送
    $(".saveBtn").on("click",function(){
        $("#flag").val(true);
        $(".projLtypeId").val($("#projectType").val());
        $("#acceptData").val(getAccept());//立项
        $("#proceduresData").val(getProcedures());//建审
        $("#contructionData").val(getContruction());//施工
        $("#settlementData").val(getSettlement());//结算
        $("#finalAccountData").val(getFinalAccount());//决算
        $("#acceptApplyForm").cformSave('stationSettlementTable',saveBack,true); //列表重新加载
    });
    //出资方式
    $("#projectType").change(function(){
        //radio选择
        var projLtypeId=$("#projectType option:selected").val();
        $("input[name='projLtype'][value="+projLtypeId+"]").attr("checked","checked");

        $("input[name='projLtype']").change();
        $("#contributionMode").empty();
        var data = projLtypeId;
        if(data==''){
            data='-1'
        }
        $.ajax({
            type: 'post',
            url: 'correlation/queryCorrelationList?corType=3&correlateInfoId='+data+'&acceptType=1',
            dataType: 'json',
            success: function(data){
                $.each(data,function(n, v){
                    $("#contributionMode").append('<option value='+v.correlatedInfoId+'>' + v.correlatedInfoDes + '</option>');
                });
            },
            error: function(data){
                console.warn("出资类型选框查询失败");
            }
        });
    });

    //切换业务部门
    $("#deptId").change(function(){
        //工程类型清空
        $("#projectType").empty();
        var selectEl = $("#deptId"),
            index = selectEl[0].selectedIndex,
            selectOption = selectEl[0].options[index];
        var data = $(selectOption).attr("value");
        if(data==''){
            data='-1'
        }
        $.ajax({
            type:'post',
            url :'correlation/queryCorrelationList?corType=2&correlateInfoId='+data,
            dataType:'json',
            success:function(data){
                $.each(data,function(n,v){
                    $("#projectType").append('<option value='+v.correlatedInfoId+'>' + v.correlatedInfoDes + '</option>');
                })
                $("#projectType").change();

            },
            error: function(data){
                console.warn("工程类型选框查询失败");
            }
        })
    })
</script>
<!-- ================== END PAGE LEVEL JS ================== -->