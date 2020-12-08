<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSavetoBtn">保存</a>
    	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 auditCancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	
    	<form class="form-horizontal" id="connectConfirmRightform" action="connectConfirm/auditSave" data-parsley-validate="true" >
    		<input type="hidden" id="projId" name = "projId" />
    		<input type="hidden" id="fdId" name = "fdId" />
    		<input type="hidden" id="corpId" name = "corpId" />
       		<div class="form-group col-md-6 col-sm-12 ">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3"></textarea>
	            </div>
            </div>
            <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" />
		        </div>
		    </div>
        	<div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    		 <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value=""/>        
		        </div>
		    </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="managementOffice">工程档号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="fdFileNo" name="fdFileNo" data-parsley-maxlength="50"/>
		        </div>
		    </div>
  			<div class="form-group col-lg-12 col-md-12 col-sm-12">
		     	<label class="control-label" for="">档案信息</label>
		     	<div> 
      				<textarea class="form-control input-sm field-editable" name="fdFileOpinion" id="fdFileOpinion" rows="4" cols="" data-parsley-maxlength="500" ></textarea>
      			</div>
 			</div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label">归档日期</label>
		        <div>
		            <input type="text" class="form-control datepicker-default input-sm field-not-editable timestamp"  id="fdConnectDate" name="fdConnectDate" data-parsley-required="true"/>
		        </div>
		    </div>
		   
		</form>
    </div>
    <!-- <h4 class="m-t-15 m-l-7"><strong>确认历史</strong></h4>
   	</div>
  	<table id="auditHistoryTable2" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
           	<tr>
            <th>确认日期</th>
            <th>资料确认</th>
            <th>资料详情</th>
            <th>确认人</th>
       		</tr>
      		</thead>
	</table> -->
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
tableCallBack = function(){
	$(".editbtn").addClass("hidden");
	var len = $('#connectConfirmTable').DataTable().ajax.json().data ? $('#connectConfirmTable').DataTable().ajax.json().data.length : $('#jointAcceptanceTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#connectConfirmRightform')[0].reset();
		 $(".editbtn").addClass("hidden");
		 $("#connectConfirmRightform").toggleEditState(false);
	 }else{
		 $("#businessOrderId").val($("#projId").val());
//		 $(".editbtn").removeClass("hidden");
//		 $("#jointAcceptanceform").toggleEditState(true);
	 }
}
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    //表单样式适应
    $("#connectConfirmRightform").toggleEditState(true).styleFit();
  	//按钮隐藏
    $(".editbtn").addClass("hidden");
  	//表单不可编辑
    $("#connectConfirmRightform").toggleEditState(true);
  	
  	
  	//人员签字
    $("#fdCheck-a").handleSignature();
    $("#fdSpotLeader-a").handleSignature();
    
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    trSData.connectConfirmTable.t && trSData.connectConfirmTable.t.cgetDetail('connectConfirmRightform','connectConfirm/jointDetail','',tableCallBack);
    
  	//当前日期
/*     $("#mrTime").change(); */
  	
  	//放弃
    $(".auditCancelBtn").off("click").on("click",function(){
    	$('#connectConfirmRightform').formReset();
    	$("#connectConfirmTable").cgetData();                                  //列表重新加载
    	$(".clear-sign").click();                                              //签名清空
		$('#connectConfirmRightform').toggleEditState(false);                  //切换不可编辑状态
		$('.editbtn').addClass('hidden');	
	});
  	//点击保存
    $(".auditSavetoBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	//表单验证
        if($("#connectConfirmRightform").parsley().isValid()){
        	//加遮罩
        	$(".infodetails").loadMask("正在保存...", 1, 0.5);
        	//json字符串
        	var data=$("#connectConfirmRightform").serializeJson();
        	data.menuId = '110707';
        	$.ajax({
                type: 'POST',
                url: 'connectConfirm/auditSave',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	//取消遮罩
                	$(".infodetails").hideMask();
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "true"){
                		$("#connectConfirmRightform").formReset();
                		$(".editbtn").addClass("hidden");
                		$("#connectConfirmTable").cgetData('','',true);  //列表重新加载
                	}else if(data === "notPass"){
                		content = "竣工图纸审核未完成,不可归档！";
                	}else if(data === "notSign"){
                		content = "结算汇签未完成,不可归档！";
                	}else if(data === "notAll"){
                		content = "竣工图审核及结算汇签未完成,不可归档！";
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
                	//取消遮罩
                	$(".infodetails").hideMask();
                    console.warn("资料归档保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#connectConfirmRightform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#connectConfirmRightform").parsley().validate();
        }
    });
    


  	
  	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->