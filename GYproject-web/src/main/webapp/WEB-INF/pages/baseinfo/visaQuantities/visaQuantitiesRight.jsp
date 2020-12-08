<!-- subContractLiuboRight.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="visaQuantitiesForm" data-parsley-validate="true" action="">
       		<input type="hidden" id="id" name="id"/>
		    <div class="form-group  col-md-12 col-sm-12">
				<label class="control-label" for="des">分项名称</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"	id="des" name="des" data-parsley-required="true" value="" />
				</div>
			</div>
		    <div class="form-group  col-md-6 col-sm-12">
				<label class="control-label" for="quantitiesNum">标准数量</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"	id="quantitiesNum" name="quantitiesNum" data-parsley-required="true" data-parsley-type="number" value="" />
				</div>
			</div>
			
			<div class=" form-group col-md-6 col-sm-12">
		        <label class="control-label" for="unit">单位</label>
		    	<div>
		    		<select class="form-control input-sm field-editable" id="unit" data-parsley-required="true" name="unit"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${unit}">
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
    $('.infodetails').hideMask();
    $('.editbtn').addClass('hidden');
    //表单样式适应
    $('#visaQuantitiesForm').toggleEditState().styleFit();
    
  	 //查右侧工程详述
    trSData.t && trSData.t.cgetDetail('visaQuantitiesForm'); 
    
	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    //点击右侧维护区 保存 按钮时
    $('.saveBtn').on('click',function(){
        if($('#visaQuantitiesForm').parsley().isValid()){
        	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
        	var data=$('#visaQuantitiesForm').serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'visaQuantities/saveVisaQuantities',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "true"){
                		$("#visaQuantitiesForm input").val('');
                		$("#visaQuantitiesForm textarea").val('');
                		$("#visaQuantitiesTable").cgetData();  //列表重新加载
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
        	$("#visaQuantitiesForm").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#visaQuantitiesForm").parsley().validate();
        }
        
    }); 
    
    //放弃
    $(".cancelBtn").on("click",function(){
    	$("#visaQuantitiesForm input").val('');
    	$("#visaQuantitiesForm").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
    	$("#visaQuantitiesTable").cgetData();  //列表重新加载
	   	//移除验证
	   	$("#visaQuantitiesForm").parsley().reset();
    });
  
</script>
<!-- ================== END PAGE LEVEL JS ================== -->