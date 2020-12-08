<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 saveBtn" >保存</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
		<input type="text" class="form-control input-sm hidden" id="deptName" name="deptName"value="${login.corpName}"/>
    	<form class="form-horizontal" id="annualPlanForm" action="annualPlan/saveAnnualPlan">
    		<!-- <button type="reset" class="hidden" id="reset"/> -->
    		<input type="hidden" id="planId" name="planId" />
			<input type="hidden" id="projectTypeDes" name="projectTypeDes" />
			<input type="hidden" id="contributionModeDes" name="contributionModeDes" />
			<input type="hidden" class="deptName" name="deptName" />

        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="planNo">计划编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="planNo" name="planNo"  value="" data-parsley-required="true" data-parsley-maxlength="30"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12">
		        <label class="control-label" for="planName">计划名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="planName" name="planName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-12 col-sm-12">
		        <label class="control-label" for="planAddr">项目地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="planAddr" name="planAddr" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-12 col-sm-12">
		        <label class="control-label" for="corpName">燃气公司</label>
		        <div>
					<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName" data-parsley-maxlength="200" value="${login.corpName}"/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 col-sm-12">
		        <label class="control-label" for="pipeDiameter">管径</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="pipeDiameter" name="pipeDiameter"  value="" data-parsley-maxlength="50"/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 col-sm-12">
		        <label class="control-label" for="pipeLength">项目长度(千米)</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable text-right"  id="pipeLength" name="pipeLength" data-parsley-type="number" data-parsley-maxlength="13" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 col-sm-12">
		        <label class="control-label" for="finishLength">完成长度(千米)</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable text-right"  id="finishLength" name="finishLength" data-parsley-type="number" data-parsley-maxlength="13" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 col-sm-12">
		        <label class="control-label" for="completionSchedule">完成进度</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="completionSchedule" name="completionSchedule"  value="" data-parsley-maxlength="20"/>
		        </div>
		    </div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="deptId">业务部门</label>
				<div>
					<select class="form-control input-sm field-editable" id="deptId"  name="deptId"  data-parsley-required="true">
						<option value=""></option>
						<c:forEach var="enums" items="${department}">
							<option value="${enums.correlatedInfoId}" >${enums.correlatedInfoDes}</option>
						</c:forEach>

					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="projectType">工程类型</label>
				<div>
					<select class="form-control input-sm field-editable" id="projectType"  name="projectType" data-parsley-required="true" >
						<c:forEach var="enums" items="${projLtype}">
							<option value="${enums.correlatedInfoId}">${enums.correlatedInfoDes}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="contributionMode">出资方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="contributionMode"  name="contributionMode" data-parsley-required="true" >
						<c:forEach var="enums" items="${contributionMode}">
							<option value="${enums.correlatedInfoId}">${enums.correlatedInfoDes}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		    <div class="form-group  col-md-6 col-sm-12">
		        <label class="control-label" for="investmentScale">预算价(万元)</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable text-right"  id="investmentScale" name="investmentScale" data-parsley-type="number" data-parsley-maxlength="13" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 col-sm-12">
		        <label class="control-label" for="alradyInvestment">已投资(万元)</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable text-right"  id="alradyInvestment" name="alradyInvestment" data-parsley-type="number" data-parsley-maxlength="13" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 col-sm-12">
		        <label class="control-label" for="planInvestment">计划投资(万元)</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable text-right"  id="planInvestment" name="planInvestment" data-parsley-type="number" data-parsley-maxlength="13" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 col-sm-12">
		        <label class="control-label" for="constructionRatio">建设比例</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="constructionRatio" name="constructionRatio"  value="" data-parsley-maxlength="20"/>
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
    //参数传false时，表单不可编辑
    $("#annualPlanForm").toggleEditState(false).styleFit();
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('annualPlanForm','','',scaleDetailRefresh);
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    var saveBack = function () {
		
    }
    //保存
    $(".saveBtn").off().on("click",function(){
        var projectTypeDes=$("#projectType option:selected").text();
        var contributionModeDes=$("#contributionMode option:selected").text();
        var deptName=$("#deptId option:selected").text();
        $("#projectTypeDes").val(projectTypeDes);
        $("#contributionModeDes").val(contributionModeDes);
        $(".deptName").val(deptName);

        $("#annualPlanForm").cformSave('annualPlanTable',saveBack,true);
    });
	//放弃
    $(".cancelBtn").off().on("click",function(){
    	$(".editbtn").addClass("hidden");
    	$("#annualPlanTable").cgetData(true);
    })

	//切换工程类型
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
            url :'correlation/queryCorrelationList?corType=2&correlateInfoId='+data+'&acceptType=1',
            dataType:'json',
            success:function(data){
                console.info(data);
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