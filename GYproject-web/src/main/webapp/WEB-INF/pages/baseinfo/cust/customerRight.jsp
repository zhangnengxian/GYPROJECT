<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-15  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="customerDetailform" action="">
		    <input type="hidden" name="custId" id="custId"/>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="">单位名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">简称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custShortName" name="custShortName"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">客户编码</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custCode" name="custCode"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">单位地址</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable "  id="unitAddress" name="unitAddress"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">税号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable "  id="dutyParagraph" name="dutyParagraph"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">开户行</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable "  id="openBank" name="openBank"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">账号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable "  id="account" name="account"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContcat" name="custContcat"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" />
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">手机</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custMobile" name="custMobile" data-parsley-min=11/>
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
    $("#customerDetailform").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");

	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
  	//查右侧详述
    trSData.t && trSData.t.cgetDetail('customerDetailform','customer/viewById',''); 
    
    //点击保存
    $(".saveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	
        if($("#customerDetailform").parsley().isValid()){
        	var data=$("#customerDetailform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'customer/saveCustomers',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "此单位名称或编码已存在，请修改！";
                	}else if(data === "true"){
                		$("#customerDetailform input").val('');
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
            $("#customerTable").cgetData();  //列表重新加载
        	$("#customerDetailform").parsley().reset();
        	//如果通过验证, 则移除验证UI
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#customerDetailform").parsley().validate();
        }
        
    }); 
    
    //放弃 按钮点击后
    $(".cancelBtn").on("click",function(){
    	 //清空表单
    	 $("#customerDetailform input").val('');
    	 trSData.t.cgetDetail('customerDetailform','businessPartners/viewBusinessPartners',''); 
    	 $("#customerDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["customerTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#customerDetailform").parsley().reset();
    });
 
    
    
    
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->