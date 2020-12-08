<!-- scoreStandardRight.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="locationSetUpRightform" action="">
       		<div class="form-group col-md-6  ">
       			<input type="hidden" id="lsuId" name="lsuId"/>
       			<label class="control-label" for="">部门</label>
		        <div>
		        	<select id="departmentId" class="form-control input-sm field-editable" name="deptType" >
						<option value=""></option>
		                <c:forEach var="enums" items="${department}">
							   	<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
    				</select>
		        </div>
		    </div>
       		<div class="form-group col-md-6  ">
		        <label class="control-label" for="locationDuration">时长</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable "  data-parsley-required="true" id="locationDuration" name="locationDuration" data-parsley-type="number"/>
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
    $("#locationSetUpRightform").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('locationSetUpRightform','','',function(data){
    	/* $("#departmentId").val(data.dept.deptId); */
    });
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	 if($('#locationSetUpRightform').parsley().isValid()){
         	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
         	var data=$('#locationSetUpRightform').serializeJson();
         	$.ajax({
                 type: 'POST',
                 url: 'locationSetUp/saveLocationSetUp',
                 contentType: "application/json;charset=UTF-8",
                 data: JSON.stringify(data),
                 success: function (data) {
                 	var content = "数据保存成功！";
                 	if(data === "false"){
                 		content = "数据保存失败！";
                 	}else if(data === "exist"){
                 		content = "部门已设置，请选择其他部门！";
                 	}else if(data === "true"){
                 		$("#locationSetUpRightform input").val('');
                 		$("#locationSetUpTable").cgetData();  //列表重新加载
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
         	$("locationSetUpRightform").parsley().reset();
         }else{
         	//如果未通过验证, 则加载验证UI
         	$("#locationSetUpRightform").parsley().validate();
         }
    }); 
  
    
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	
    	$("#locationSetUpRightform input").val('');
    	$("#locationSetUpTable").cgetData();
    	$("#locationSetUpRightform").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	//移除验证
	   	$("#locationSetUpRightform").parsley().reset();
    });
    
    
    </script>
<!-- ================== END PAGE LEVEL JS ================== -->