<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10 editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 acceptSaveBtn" >保存</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
		<input type="hidden"  id="deptName" name="deptName" value="${corpName}">
    	<form class="form-horizontal" id="acceptApplyForm" action="stationAccept/stationAcceptSave">
    		<input type="hidden" id="projId" name="projId" />
    		<input type="hidden" id="corpId" name="corpId" />
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="projName" name="projName" data-parsley-maxlength="200" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group  col-md-12 col-sm-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		            <textarea  class="form-control input-sm field-editable"  id="projScaleDes" name="projScaleDes" data-parsley-maxlength="200" data-parsley-required="true"/></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
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
					<select class="form-control input-sm field-editable" id="deptId"  name="deptId" >
						<option value=""></option>
						<c:forEach var="enums" items="${department}">
							<option value="${enums.correlatedInfoId}" >${enums.correlatedInfoDes}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<!-- 新加字段 -->
				<label class="control-label" >申请日期</label>
				<div>
					<input type="text" class=" form-control input-sm field-editable datepicker-default" id="acceptDate"  name="acceptDate" data-parsley-required="true" value="" >
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<!-- 新加字段 -->
				<label class="control-label" for="projectType">工程类型</label>
				<div>
					<select class="form-control input-sm field-editable" id="projectType"  name="projectType" >
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

		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="totalInvestment">总投资(万元)</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-editable fixed-number text-right"  id="totalInvestment" name="totalInvestment" data-parsley-type='number' data-parsley-maxlength="13"/>
		        </div>
		    </div>
		   
		    <div class="form-group col-md-6 col-sm-12 clearboth">
		    	<!-- 新加字段 -->
		        <label class="control-label">拟开工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="protocolStartingDate"  name="protocolStartingDate" value="" >
		        </div>
		    </div>
		     <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="projectDuration">工期(天)</label>
		    	<div>
		             <input type="text" class="form-control input-sm field-editable"  id="projectDuration" name="projectDuration" data-parsley-maxlength="50"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="acceptReason">立项理由</label>
		        <div>
		            <textarea  class="form-control input-sm field-editable"  id="acceptReason" name="acceptReason" data-parsley-maxlength="200"/></textarea>
		        </div>
		    </div>

			<input type="hidden" class="projLtypeId" name="projLtypeId"/>
			<input type="hidden" name="acceptData" id="acceptData"/>

			<div class="form-group col-sm-12">
		    	<h4><i class="fa fa-arrow-circle-o-right"></i>立项资料</h4>
		    </div>

			<c:forEach var="enums" items="${acceptDatas}">
				<div class="form-group col-sm-12">
					<label class="control-label"></label>
					<div>
						<label>
							<input type="checkbox"  name="acceptDataDes" class="field-editable" value="${enums.sdId}"/>${enums.sdDes}
						</label>
					</div>
			   </div>
			</c:forEach>
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
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    //査详述
    trSData.t && trSData.t.cgetDetail('acceptApplyForm','stationAccept/viewStationProject','',scaleDetailRefresh);
    //时间插件
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
        $("#stationAcceptTable").cgetData();  //列表重新加载
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

    //保存回调
    var saveBack = function () {
        //todo...
        $(".editbtn").addClass("hidden");
    }
	//保存
	$(".acceptSaveBtn").on("click",function(){
        $(".projLtypeId").val($("#projectType").val());

        var acceptData = getAccept();
        $("#acceptData").val(acceptData);
        $("#acceptApplyForm").cformSave('stationAcceptTable',saveBack,true); //列表重新加载
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