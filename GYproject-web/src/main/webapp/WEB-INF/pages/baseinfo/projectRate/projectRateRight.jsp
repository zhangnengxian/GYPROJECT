<!-- pricedBoq.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="projectRateDetailform" data-parsley-validate="true" action="">
		    <div class="form-group col-md-12">
		    	<label class="control-label" for="value" name="name"></label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="value" name="value" value=""  data-parsley-required="true"/>
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
    $('#projectRateDetailform').toggleEditState().styleFit();
    
  	//查右侧工程详述
    //trSData.t.cgetDetail('pricedBoqDetailform','pricedBoq/viewPricedBoq',''); 
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
     $("#projectRateDetailform label").text($("#projectRateTable").find("tbody tr:eq(0) td:eq(0)").text());
	 $("#projectRateDetailform input").val($("#projectRateTable").find("tbody tr:eq(0) td:eq(1)").text()); 
    
    /**点击右侧维护区 保存 按钮时*/
     $('.saveBtn').on('click',function(){
        if($('#projectRateDetailform').parsley().isValid()){
        	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
        	var data=$('#projectRateDetailform').serializeJson();
        	data.name=$("#projectRateDetailform label").text();
        	//console.log(data);
        	$.ajax({
                type: 'POST',
                url: 'projectRate/saveOrUpdateprojectRate',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "费率类型已存在，请修改！";
                	}else if(data === "true"){
                		$('.editbtn').addClass('hidden');
                		projectRateTable.ajax.reload();
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
                    console.warn("费率信息保存失败！");
                }
            });
        
        	 //如果通过验证, 则移除验证UI
        	$("#projectRateDetailform").parsley().validate();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#projectRateDetailform").parsley().validate();
        }
        
    });
    
    //放弃
    $(".cancelBtn").on("click",function(){
    	//trSData.t.cgetDetail('projectRateDetailform','/viewPricedBoq',''); 
    	$("#projectRateDetailform").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["projectRateTable"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#projectRateDetailform").parsley().reset();
    });
 	  
</script>
<!-- ================== END PAGE LEVEL JS ================== -->