<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-15  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="incrementDetailform" action="">
		    <input type="hidden" name="inId" id="inId"/>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">税率编码</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"   id="incrementCode" name="incrementCode" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">税率值</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="increment" name="increment"/>
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
    $("#incrementDetailform").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");

	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
  	//查右侧详述
    trSData.t && trSData.t.cgetDetail('incrementDetailform','increment/viewIncrementDetail',''); 
    
    //点击保存
    $(".saveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	
        if($("#incrementDetailform").parsley().isValid()){
        	var data=$("#incrementDetailform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'increment/saveIncrement',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "此税率已存在，请修改！";
                	}else if(data === "true"){
                		$("#incrementDetailform input").val('');
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
            $("#incrementTable").cgetData();  //列表重新加载
        	$("#incrementDetailform").parsley().reset();
        	//如果通过验证, 则移除验证UI
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#incrementDetailform").parsley().validate();
        }
        
    }); 
    
    //放弃 按钮点击后
    $(".cancelBtn").on("click",function(){
    	 //清空表单
    	 $("#incrementDetailform input").val('');
    	 trSData.t.cgetDetail('incrementDetailform','increment/viewIncrementDetail',''); 
    	 $("#incrementDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["incrementTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#incrementDetailform").parsley().reset();
    });
 
    
    
    
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->