<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSaveBtn">保存</a>
    	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 auditCancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="dataFeedbackRightform" action="">
    		<input type="hidden" id="projId" name = "projId" />
    		<input type="hidden" id="fdId" name = "fdId" />
    		<input type="hidden" id="businessOrderId" name = "businessOrderId" />
    		<!-- <div class="form-group col-md-6 ">
		        <label class="control-label" for="fdId">主键编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="fdId" name="fdId"/>
		        </div>
		    </div> -->
       		<div class="form-group col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="paNo">受理单号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="paNo" name="paNo"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="managementOffice">施工管理处</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="fdCmo" name="fdCmo"/>
		        </div>
		    </div>
		    
  			<div class="form-group col-lg-12 col-md-12 col-sm-12">
		     	<label class="control-label" for="">档案信息</label>
		     	<div> 
      				<textarea class="form-control field-editable" name="fdFileOpinion" id="fdFileOpinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea>
      			</div>
 			</div>
 			<%-- <div class="form-group col-lg-6 col-md-12 col-sm-6">
		        <label class="control-label" for="">审核人</label>
		        <div>
		           <input type="hidden"  id="fdAuditor"  name="fdAuditor" data-parsley-maxlength="100" value="${loginInfo.staffId}">
		           <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
		        </div>
		    </div>
		    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
		        <label class="control-label" for="">归档日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="fdDate" value="${auditTime}"  name="fdDate" data-parsley-required="true" data-parsley-maxlength="100" >
		        </div>
		    </div> --%>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="managementOffice">工程档号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="fdFileNo" name="fdFileNo"/>
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
    $("#dataFeedbackRightform").toggleEditState(true).styleFit();
  	//按钮隐藏
    $(".editbtn").addClass("hidden");
  	//表单不可编辑
    $("#dataFeedbackRightform").toggleEditState(true);
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    //trSData.dataFeedbackTable.t && trSData.dataFeedbackTable.t.cgetDetail('dataFeedbackRightform','','',tableCallBack);
    trSData.dataFeedbackTable.t && trSData.dataFeedbackTable.t.cgetDetail('dataFeedbackRightform','dataFeedback/jointDetail','',tableCallBack);
  	
    /* $.getScript('projectjs/complete/data_feedback_right.js').done(function (){
    	dataAuditHistory.init();
	}); */
  	//放弃
    $(".auditCancelBtn").on("click",function(){
    	$('#dataFeedbackRightform').formReset();
    	$("#dataFeedbackTable").cgetData();                                  //列表重新加载
		$('#dataFeedbackRightform').toggleEditState(false);                  //切换不可编辑状态
		$('.editbtn').addClass('hidden');									//维护按钮隐藏
    });
  	
	//点击保存
    $(".auditSaveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	//表单验证
        if($("#dataFeedbackRightform").parsley().isValid()){
        	//json字符串
        	var data=$("#dataFeedbackRightform").serializeJson();
        	console.info(data);
        	$.ajax({
                type: 'POST',
                url: 'dataFeedback/dataFeedbackSave',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "true"){
                		$("#dataFeedbackRightform").formReset();
                		$("#dataFeedbackTable").cgetData();  //列表重新加载
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
                    console.warn("资料反馈保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#dataFeedbackRightform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#dataFeedbackRightform").parsley().validate();
        }
         $('#dataFeedbackRightform')[0].reset();
		 $(".editbtn").addClass("hidden");
		 $("#dataFeedbackRightform").toggleEditState(false);
    });
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->