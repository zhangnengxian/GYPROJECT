<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 custSaveBtn" >保存</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="custForm" action="popup/saveCustomer">
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">单位名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"   name="custName"  data-parsley-maxlength="100" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">单位简称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"   name="custShortName"  data-parsley-maxlength="50" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"   name="custContcat" data-parsley-maxlength="20" data-parsley-required="true" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"   name="custPhone" data-parsley-maxlength="13" data-parsley-type="phone" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">手机</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"   name="custMobile" data-parsley-maxlength="100" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">身份证号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"   name="idNumber" data-parsley-maxlength="18" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 clearboth">
		        <label class="control-label" for="">开户行</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"   name="openBank" data-parsley-maxlength="50" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">账号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  name="account" data-parsley-maxlength="20" data-parsley-type="integer"/>
		        </div>
		    </div>
		    
		   <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">单位地址</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  name="unitAddress" />
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">税号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  name="dutyParagraph" />
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
    $("#custForm").styleFit();

	/**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	popClose();
    });
    
	//保存
	$(".custSaveBtn").on("click",function(){
		//$("#custForm").cformSave('',saveCustBack,'');
		if($("#custForm").parsley().isValid()){
        	var data=$("#custForm").serializeJson();
        	if(data.custName){
        		data.custName=removeAllSpace(data.custName);
        	}
        	if(data.custContcat){
        		data.custContcat=removeAllSpace(data.custContcat);
        	}
        	$.ajax({
                type: 'POST',
                url: 'popup/saveCustomer',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "此单位名称或编码已存在，请修改！";
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
        	$("#custForm").parsley().reset();
        	//如果通过验证, 则移除验证UI
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#custForm").parsley().validate();
        }
	});
	
	var saveCustBack = function(data){
		
		var content = "保存成功！";
		if(data === "false"){
			content = "保存失败！";
		}else if(data === "exist"){
			
		}else{
			var json = $("#custForm").serializeJson();
			
			if($("#custName") && !$("#custName").is('[readonly]')){
				if($("#custName")){
					$("#custName").val(json.custName);
				}
				if($("#custId")){
					$("#custId").val(data);
				}
				if($("#custContact")){
					$("#custContact").val(json.custContcat);
				}
				if($("#custPhone")){
					$("#custPhone").val(json.custPhone);
				}
			}
			$("#custForm").formReset();
		}
		var myoptions = {
              	title: "提示信息",
              	content: content,
              	accept: popClose,
              	newpop:'second',
              	chide: true,
              	icon: "fa-check-circle"
        }
        $("body").cgetPopup(myoptions);
	}
</script>
<!-- ================== END PAGE LEVEL JS ================== -->