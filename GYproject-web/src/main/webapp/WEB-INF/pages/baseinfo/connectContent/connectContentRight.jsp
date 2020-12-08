<!-- subContractLiuboRight.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="connectContentDetailform" data-parsley-validate="true" action="">
       		<input type="hidden" id="id" name="id"/>
       		<div class="form-group col-md-12 ">
		    	<label class="control-label" for="type">单位类型</label>
		        <div>
		        	<select id="type" class="form-control input-sm field-editable" name="type" >
						<option value=""></option>
						<c:forEach var="enums" items="${typeDeses}">
            				<option value="${enums.value}">${enums.message}</option>
        				</c:forEach>
    				</select>
		        	<!-- <input type="text" class="form-control input-sm field-editable"  id="costTypeDes" name="costTypeDes" value=""/> -->
		        </div>
		    </div>
		    <div class="form-group  col-md-12 ">
		        <label class="control-label" for="des">描述</label>
		        <div>
		            <textarea class="form-control input-sm field-editable"  id="des" name="des" data-parsley-maxlength="100"  data-parsley-required="true"></textarea>
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
    $('.infodetails').hideMask();
    $('.editbtn').addClass('hidden');
    //表单样式适应
    $('#connectContentDetailform').toggleEditState().styleFit();
    
  	 //查右侧工程详述
    trSData.t && trSData.t.cgetDetail('connectContentDetailform'); 
    
	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    //点击右侧维护区 保存 按钮时
    $('.saveBtn').on('click',function(){
        if($('#connectContentDetailform').parsley().isValid()){
        	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
        	var data=$('#connectContentDetailform').serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'connectContent/saveConnectContent',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "分包单位已存在，请修改！";
                	}else if(data === "true"){
                		$("#connectContentDetailform input").val('');
                		$("#connectContentDetailform textarea").val('');
                		$("#connectContentTable").cgetData();  //列表重新加载
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
                    console.warn("碰口内容信息保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#connectContentDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#connectContentDetailform").parsley().validate();
        }
        
    }); 
    
    //放弃
    $(".cancelBtn").on("click",function(){
    	$("#connectContentDetailform input").val('');
    	trSData.t && trSData.t.cgetDetail('connectContentDetailform'); 
    	$("#connectContentDetailform").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["connectContentTable"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#connectContentDetailform").parsley().reset();
    });
  
</script>
<!-- ================== END PAGE LEVEL JS ================== -->