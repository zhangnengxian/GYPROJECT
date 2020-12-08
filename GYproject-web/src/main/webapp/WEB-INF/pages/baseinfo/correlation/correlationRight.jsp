<!-- accessoryItemRight.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="correlationForm" data-parsley-validate="true" action="">
       		<input type="hidden" id="corId" name="corId">
       		<input type="hidden" id="correlateInfoDes" name="correlateInfoDes">
       		<input type="hidden" id="correlatedInfoDes" name="correlatedInfoDes">
       		<div class="form-group col-md-12">
				<label class="control-label" for="corp">分公司</label>
				<div>
					<select id="corpId" name="corpId" class="form-control input-sm field-editable">
						<option value="" ></option>
						<c:forEach var="enums" items="${corp}">
							<option value="${enums.deptId}" >${enums.deptName}</option>
						</c:forEach>
					</select>
			   </div>
		 	</div> 
       		 <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="corType">关联类型</label>
		    	<div>
		    		 <select class="form-control input-sm field-editable" id="corType"  name="corType"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${correlationType}">
							   	<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		             </select>		        
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="correlateInfoId">相关信息</label>
		    	<div>
		    		 <select class="form-control input-sm field-editable" id="correlateInfoId"  name="correlateInfoId"  >
		 		      	<option value=""></option>
		             </select>		        
		        </div>
		    </div>
		     <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="correlatedInfoId">关联信息</label>
		    	<div>
		    		 <select class="form-control input-sm field-editable" id="correlatedInfoId"  name="correlatedInfoId"  >
		 		      	<option value=""></option>
		             </select>		        
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 hidden contributionCode">
		    	<label class="control-label" for="contributionCode">类型出资编码</label>
		        <div>
		        	<select class="form-control input-sm field-editable" id="contributionCode"  name="contributionCode"  >
		 		      	<option value=""></option>
		 		      	<option value="01">居民户工程（用户出资）</option>
		 		      	<option value="02">智能表工程</option>
		 		      	<option value="03">公建户工程（用户出资）</option>
		 		      	<option value="04">干线工程（公司出资）</option>
		 		      	<option value="05">改管工程（用户出资）</option>
		 		      	<option value="06">改管工程（政府出资）</option>
		 		      	<option value="07">改管工程（铸铁管-政府专项资金）</option>
		 		      	<option value="08">改管工程（危旧管-公司出资）</option>
		 		      	<option value="09">技术改造工程（公司出资）</option>
		 		      	<option value="10">改管工程（客服中心内部流转）</option>
		 		      	<option value="11">场站（高压管网）工程</option>
		 		      	<option value="12">公建户工程（公司出资）</option>
		 		      	<option value="13">公建户工程（政府出资）</option>
		 		      	<option value="14">公建户工程（募投项目）</option>
		 		      	<option value="15">改管工程（募投项目）</option>
		 		      	<option value="16">干线工程（募投项目）</option>
		 		      	<option value="17">居民户工程（公司出资）</option>
		 		      	<option value="18">干线工程（用户出资）</option>
		 		      	<option value="19">民用工程（别墅-用户出资）</option>
		             </select>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12   ">
		    	<label class="control-label" for="acceptType">立项方式</label>
		        <div>
		        	<select class="form-control input-sm field-editable" id="acceptType"  name="acceptType"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${acceptType}">
							   	<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		             </select>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ">
		    	<label class="control-label" for="scaleType">规模类型</label>
		        <div>
		        	<select class="form-control input-sm field-editable" id="scaleType"  name="scaleType"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${scaleType}">
							   	<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		             </select>
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
    $("#correlationForm").toggleEditState().styleFit();
   
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('correlationForm','','',callBackabc);
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
        if($("#correlationForm").parsley().isValid()){
        	var correlateInfoDes=$("#correlateInfoId option:selected").text();
       		var correlatedInfoDes=$("#correlatedInfoId option:selected").text();
       		$("#correlateInfoDes").val(correlateInfoDes);
       		$("#correlatedInfoDes").val(correlatedInfoDes);
        	var data=$("#correlationForm").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'correlation/saveOrUpdateCorrelation',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                		alertInfo(content);
                	}else if(data === "true"){
                		$("#correlationForm select").val('');
                		//隐藏按钮
                		$(".editbtn").addClass("hidden");
                		$("#correlationTable").cgetData(true);  //列表重新加载
                		$("#correlationForm").toggleEditState(false);
                		alertInfo(content);
                	}
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("关联记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#correlationForm").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#correlationForm").parsley().validate();
        }
    }); 
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	$("#correlationForm input").val('');
    	trSData.t && trSData.t.cgetDetail('correlationForm'); 
    	$("#correlationForm").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	//移除验证
	   	$("#correlationForm").parsley().reset();
    });
    //切换关联类型
    $("#corType").change(function(){
    	if($("#corType").val()=='3'){
    		$(".contributionCode").removeClass("hidden");
    	}else{
    		$(".contributionCode").addClass("hidden");
    	}
    	//工程类型清空
    	$("#correlateInfoId").empty();
    	$("#correlatedInfoId").empty();
    	var selectEl = $("#corType"),
        index = selectEl[0].selectedIndex,
        selectOption = selectEl[0].options[index];
    	var data = $(selectOption).attr("value");
    	$.ajax({
    		type:'post',
    		url :'correlation/queryCorrelateInfoId?corType='+data,
    		dataType:'json',
    		success:function(data){
    			$.each(data.correlationInfo,function(n,v){
    				if(n=0){
    					$("#correlateInfoId").append('<option value='+v.correlationInfoId+'>' + v.correlationInfoDes + '</option>');
    					$("#correlateInfoId option[value='"+v.correlationInfoId+"']").attr("selected","selected");
    				}
    				$("#correlateInfoId").append('<option value='+v.correlationInfoId+'>' + v.correlationInfoDes + '</option>');
    			});
    			$.each(data.correlationedInfo,function(n,v){
    				if(n=0){
    					$("#correlatedInfoId").append('<option value='+v.correlationedInfoId+'>' + v.correlationedInfoDes + '</option>');
    					$("#correlatedInfoId option[value='"+v.correlationedInfoId+"']").attr("selected","selected");
    				}
    				$("#correlatedInfoId").append('<option value='+v.correlationedInfoId+'>' + v.correlationedInfoDes + '</option>');
    			});
    		},
    		error: function(data){
    			console.warn("关联信息选框查询失败");
    		}
    	})
    });
    </script>
<!-- ================== END PAGE LEVEL JS ================== -->