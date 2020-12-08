<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-15  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="festivalDetailform" action="">
		    <input type="hidden" name="id" id="id"/>
		    <input type="hidden" name="isDel" id="isDel"/>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">节假日名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"   id="festivalName" name="festivalName" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">节假日类型</label>
		        <div>
		           <select  class="form-control field-editable"   id="dayType" name="dayType" data-parsley-required="true">
		        		<c:forEach var ="enums" items="${FestivalTypeEnum }">
		        			<option value="${enums.value }">${enums.message }</option>
		        		</c:forEach>
		        	</select>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">开始日期</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable datepicker-default"  id="festivalStartDate" name="festivalStartDate" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">结束日期</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable datepicker-default"  id="festivalEndDate" name="festivalEndDate" data-parsley-required="true"/>
		        </div>
		    </div>
		   <div class="form-group col-md-6 ">
		        <label class="control-label" for="">是否启用</label>
		        <div>
		            <select class="form-control field-editable"  id="isValid" name="isValid" data-parsley-required="true">
		            <c:forEach var ="enums" items="${IsValidEnum }">
		        			<option value="${enums.value }">${enums.message }</option>
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
    //表单样式适应
    $("#festivalDetailform").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");

	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
  	//查右侧详述
    trSData.t && trSData.t.cgetDetail('festivalDetailform','festival/viewDetail',''); 
    
    //点击保存
    $(".saveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	
        if($("#festivalDetailform").parsley().isValid()){
        	var data=$("#festivalDetailform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'festival/saveFestival',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else{
                		//$("#festivalDetailform input").val('');
                		$("#festivalTable").cgetData(true);  //列表重新加载
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
                    console.warn("保存失败！");
                }
            });
            $("#festivalDetailform").parsley().reset();
        	//如果通过验证, 则移除验证UI
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#festivalDetailform").parsley().validate();
        }
        
    }); 
    
    //放弃 按钮点击后
    $(".cancelBtn").on("click",function(){
    	 //清空表单
    	 $("#festivalDetailform input").val('');
    	 trSData.t.cgetDetail('festivalDetailform','festival/viewDetail',''); 
    	 $("#festivalDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["festivalTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#festivalDetailform").parsley().reset();
    });
 
    
    
    
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->