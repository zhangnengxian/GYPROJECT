<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-15  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="checkItemDetailform"  action="">
		    <input type="hidden" name="id" id="id"/>
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
			<div class="form-group col-md-12 col-sm-12">
		        <label class="control-label" for="">自检类型</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-not-editable" name="type" value="1" checked id="type1"/>质量
		            </label>
		            <label>
		            	<input type="radio" class="field-not-editable" name="type" value="2" id="type2" />资料
		            </label>
		        </div>
		    </div>
			
			<div class="form-group col-md-12 col-sm-12" id="chooseCheckType">
		        <label class="control-label" for="">检查类型</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable" name="checkType" value="1" checked/>燃气干管工程
		            </label>
		            <label>
		            	<input type="radio" class="field-editable" name="checkType" value="2" />室内燃气工程
		            </label>
		        </div>
		    </div>
			
		    <div class="form-group col-md-12">
		     	<label class="control-label" for="connectGasDes">描述</label>
		     	<div> 
		        	<textarea class="form-control  field-editable" name="des" id="des" rows="4" cols="" data-parsley-maxlength="200" data-parsley-required="true" ></textarea>
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
    $("#checkItemDetailform").toggleEditState().styleFit();
  	//按钮隐藏
    $(".editbtn").addClass("hidden");
  
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
  	
  	//查右侧详述
    trSData.t && trSData.t.cgetDetail('checkItemDetailform', 'checkItems/viewCheckItem', '');
	
  	//点击保存
    $(".saveBtn").on("click",function(){
    	if($("#checkItemDetailform").parsley().isValid()){
    		var data=$("#checkItemDetailform").serializeJson();
    		$.ajax({
                type: 'POST',
                url: 'checkItems/saveCheckItem',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "此单位已存在，请修改！";
                	}else if(data === "true"){
                		$("#checkItemTable").cgetData();
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
        	//列表重新加载
        	 $("#checkItemTable").cgetData();  
        	//如果通过验证, 则移除验证UI
         	 $("#checkItemDetailform").parsley().reset();
    	}else{
        	//如果未通过验证, 则加载验证UI
        	$("#checkItemDetailform").parsley().validate();
        }
    });
    
  	//放弃 按钮点击后
    $(".cancelBtn").on("click",function(){
    	 $("#chooseCheckType").css("display", "block");
    	 $("#checkItemTable").cgetData();
    	 $("#chooseCheckType").css("display", "block");
    	 trSData.t.cgetDetail('checkItemDetailform', 'checkItems/viewCheckItem', '');
    	 $("#checkItemDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["checkItemTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#checkItemDetailform").parsley().reset();
    });
  	
  	//点击资料
  	$("#type2").on("click",function(){
  		$("#chooseCheckType").addClass("hidden");
  	});
  	//点击质量
  	$("#type1").on("click",function(){
  		$("#chooseCheckType").removeClass("hidden");
  	});
  	
</script>