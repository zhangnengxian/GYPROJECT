<!-- scoreStandardRight.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="scoreStandardRightform" action="">
       		<div class="form-group col-md-6  ">
       			<input type="hidden" id="ssId" name="ssId"/>
       			<label class="control-label" for="">评分部门</label>
		        <div>
		        	<select id="departmentId" class="form-control input-sm field-editable" name="departmentId" >
						<option value=""></option>
		                <c:forEach var="enums" items="${department}">
							   	<option value="${enums.deptId}" >${enums.deptName}</option>
		                </c:forEach>
    				</select>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projName">评分项</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable "  data-parsley-required="true" id="ssDes" name="ssDes" data-parsley-maxlength="100"/>
		        </div>
		    </div>
       		<div class="form-group col-md-12  ">
		        <label class="control-label" for="projName">分值</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable "  data-parsley-required="true" id="ssScore" name="ssScore" data-parsley-type="number"/>
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
    $("#scoreStandardRightform").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('scoreStandardRightform','','',function(data){
    	$("#departmentId").val(data.dept.deptId);
    });
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	 if($('#scoreStandardRightform').parsley().isValid()){
         	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
         	var data=$('#scoreStandardRightform').serializeJson();
         	$.ajax({
                 type: 'POST',
                 url: 'scoreStandard/saveOrUpdateScoreStandard',
                 contentType: "application/json;charset=UTF-8",
                 data: JSON.stringify(data),
                 success: function (data) {
                 	var content = "数据保存成功！";
                 	if(data === "false"){
                 		content = "数据保存失败！";
                 	}else if(data ===  "exist"){
                 		content = "资料标准已存在，请修改！";
                 	}else if(data==="greater"){
                 		content = "总分值不能大于100分，请修改！";
                 	}else if(data === "true"){
                 		$("#scoreStandardRightform input").val('');
                 		$("#scoreStandardTable").cgetData();  //列表重新加载
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
         	$("scoreStandardRightform").parsley().reset();
         }else{
         	//如果未通过验证, 则加载验证UI
         	$("#scoreStandardRightform").parsley().validate();
         }
    }); 
  
    
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	
    	$("#scoreStandardRightform input").val('');
    	trSData.t && trSData.t.cgetDetail('scoreStandardRightform','/queryItem',''); 
    	$("#scoreStandardRightform").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["scoreStandardTable"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#scoreStandardRightform").parsley().reset();
    });
    
    
    </script>
<!-- ================== END PAGE LEVEL JS ================== -->