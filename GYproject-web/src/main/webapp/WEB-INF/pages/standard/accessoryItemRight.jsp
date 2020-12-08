<!-- accessoryItemRight.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<input type="hidden" class="corpId" value="${corpId}">
    	<form class="form-horizontal" id="accessoryItemRightform" action="surveyResultRegister/saveSurveyInfo">
       		<div class="form-group col-md-6">
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
       		<div class="form-group col-md-6  ">
       			<input type="hidden" id="caiId" name="caiId"/>
       			<label class="control-label" for="">工程类型</label>
		        <div>
		        	<select id="projTypeId" class="form-control input-sm field-editable" name="projTypeId" >
						<option value=""></option>
		                <c:forEach var="enums" items="${projTypeList}">
							   	<option value="${enums.projTypeId}" >${enums.projTypeDes}</option>
		                </c:forEach>
    				</select>
		        </div>
		    </div>
		     <div class="form-group col-md-6  ">
       			<label class="control-label" for="dataType">资料类型</label>
		        <div>
		        	<select id="dataType" class="form-control input-sm field-editable" name="dataType" >
						<option value=""></option>
		                <c:forEach var="enums" items="${dataType}">
							   	<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
    				</select>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="accessoryName">资料名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable "  data-parsley-required="true" id="accessoryName" name="accessoryName" data-parsley-maxlength="100"/>
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
    $("#accessoryItemRightform").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('accessoryItemRightform','','',function(data){
    	$("#projTypeId").val(data.projType.projTypeId);
    });
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	 if($('#accessoryItemRightform').parsley().isValid()){
         	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
         	var data=$('#accessoryItemRightform').serializeJson();
         	$.ajax({
                 type: 'POST',
                 url: 'accessoryItem/saveOrUpdateCollectAccessoryItem',
                 contentType: "application/json;charset=UTF-8",
                 data: JSON.stringify(data),
                 success: function (data) {
                 	var content = "数据保存成功！";
                 	if(data === "false"){
                 		content = "数据保存失败！";
                 	}else if(data ===  "exist"){
                 		content = "资料标准已存在，请修改！";
                 	}else if(data === "true"){
                 		$("#accessoryItemRightform input").val('');
                 		$("#accessoryItemTable").cgetData();  //列表重新加载
                 	}
                 	var myoptions = {
                         	title: "提示信息",
                         	content: content,
                         	accept: popClose,
                         	chide: true,
                         	icon: "fa-check-circle"
                     }
                     $("body").cgetPopup(myoptions);
                 },
                 error: function (jqXHR, textStatus, errorThrown) {
                     console.warn("资料标准信息保存失败！");
                 }
             });
         	//如果通过验证, 则移除验证UI
         	$("accessoryItemRightform").parsley().reset();
         }else{
         	//如果未通过验证, 则加载验证UI
         	$("#accessoryItemRightform").parsley().validate();
         }
    }); 
  
    
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	
    	$("#accessoryItemRightform input").val('');
    	trSData.t && trSData.t.cgetDetail('accessoryItemRightform','/queryItem',''); 
    	$("#accessoryItemRightform").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["accessoryItemTable"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#accessoryItemRightform").parsley().reset();
    });
    
    
    if($(".corpId").val()!=""){
    	var corpId=$(".corpId").val();
    	$("#accessoryItemRightform option[value='"+corpId+"']").attr("selected","selected");
    }
    
    </script>
<!-- ================== END PAGE LEVEL JS ================== -->