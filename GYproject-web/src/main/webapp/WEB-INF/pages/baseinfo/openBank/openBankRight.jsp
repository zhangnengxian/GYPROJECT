<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-15  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	
    <div class="clearboth form-box">
    	<input class="deptType" type="hidden" name="deptType"  value="${deptType}"/>
    	<form class="form-horizontal" id="openBankDetailform" action="">
		    <input type="hidden" name="abId" id="abId"/>
		    <input type="hidden" name="corpId" id="corpId"/>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">开户账号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="bankNo" name="bankNo" data-parsley-required="true" data-parsley-maxlength="30" data-parsley-type="alphanum"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">开户名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="bankName" name="bankName" data-parsley-maxlength="50"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">开户行</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable "  id="bankAdress" name="bankAdress" data-parsley-required="true" data-parsley-maxlength="100"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">开户时间</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable datepicker-default"  id="bankDate" name="bankDate" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">行号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="bankNumber" name="bankNumber" data-parsley-maxlength="20"/>
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
    $("#openBankDetailform").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");

	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
  	//查右侧详述
    trSData.t && trSData.t.cgetDetail('openBankDetailform','openBank/viewOpenBank','');
    
    //点击保存
    $(".saveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	
        if($("#openBankDetailform").parsley().isValid()){
        	var data=$("#openBankDetailform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'openBank/saveOpenBank',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "此开户行已存在，请修改！";
                	}else{
                		$(".editbtn").addClass("hidden");
                		$("#openBankDetailform input").val('');
                		$("#openBankTable").cgetData();  //列表重新加载
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
           
        	$("#openBankDetailform").parsley().reset();
        	//如果通过验证, 则移除验证UI
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#openBankDetailform").parsley().validate();
        }
        
    }); 
    
    //放弃 按钮点击后
    $(".cancelBtn").on("click",function(){
    	 //清空表单
    	 $("#openBankDetailform input").val('');
    	 trSData.t.cgetDetail('openBankDetailform','openBank/viewOpenBank','');
    	 $("#openBankDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["openBankTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#openBankDetailform").parsley().reset();
    });
 
    
  //选择施工单位
    $("#corpNamePop").off('click').on("click",function(){
    	var deptType = $(".deptType").val();
    	$("body").cgetPopup({
    		title: '部门选择',
    		nocache:true,
    		content: '#popup/deptPop?deptType=' + deptType,
    	    accept: function(){
    	    	if($("#deptTablePop tr.selected").length < 1){
    	    		$("body").cgetPopup({
    	    			title: '提示', 
    	    			content: "请选择部门！", 
    	    			newpop: 'second', 
    	    			accept: innerClose
    	    		});
    	    		return false;
    	    	}else{
    	    		
    	    		var roleIds = '';
    	    		var postNames='';
    	    		var data = $('#deptTablePop').DataTable().rows('.selected').data();
    	    		$.each(data,function(key, val){
    	    			if(roleIds.length > 0) {
    	    				roleIds +=",";
    	    				postNames+=",";
    	    			}
    	    			roleIds += val.deptId;
    	    			postNames+=val.deptName;
    	    		});
    	    		$("#corpId").val(roleIds);
    	    		$("#corpName").val(postNames);
    	    		
    	    	}
    	    }
    	});
  	});
    
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->