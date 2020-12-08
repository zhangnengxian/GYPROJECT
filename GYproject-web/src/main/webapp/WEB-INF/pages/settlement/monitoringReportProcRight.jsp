<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  toolBtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 confirmBtn" >保存</a>
		<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="monitoringReportForm" data-parsley-validate="true" action="">
			<input type="hidden" id="projId" name="projId" />
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  />
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">工程名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" />
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr"  />
		        </div>
		    </div>
		    <div class="form-group col-lg-12 col-md-12 col-sm-6 ">
				<label class="control-label" for="">工程规模</label>
				<div>
					<textarea class="form-control field-not-editable" name="projScaleDes" rows="4" cols="" ></textarea>
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
		    
		    <div class="form-group col-sm-12 noUser">
		        <label class="control-label" for="">申报单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 noUser">
		        <label class="control-label" for="">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="200"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 noUser">
		        <label class="control-label" for="">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" />
		        </div>
		    </div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">设计人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="designer"/>
				</div>
			</div>
			<div class="form-group col-md-6 ">
		        <label class="control-label" for="uploadFlag">上传手续</label>
		        <div>
		        	<label>
		            	<input type="radio" class="field-editable" name="uploadFlag" value="1">已上传
		            </label>
		            <label>
		            	<input type="radio" class="field-editable" name="uploadFlag" value="0" checked>未上传
		            </label>
		        </div>
		    </div>
			<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
				<label class="control-label" for="">备注</label>
				<div>
					<textarea class="form-control input-sm field-editable" name="remark" data-parsley-maxlength="500" rows="4"></textarea>
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
    //参数传false时，表单不可编辑
    $("#monitoringReportForm").toggleEditState(false);
     //隐藏loading
    $(".infodetails").hideMask();
    $(".toolBtn").addClass("hidden");
	$("#monitoringReportForm").styleFit();
	trSData.t && trSData.t.cgetDetail('monitoringReportForm','whiteMapRegister/viewProject','',datailBack);
    
    //点击保存
    $('.confirmBtn').off().on('click',function(){
    	if($("#monitoringReportForm").parsley().isValid()){
    		var viewdata = $("#monitoringReportForm").serializeJson();
    		if(viewdata.uploadFlag!='1'){
   	    	    alertInfo("请确认是否已上传监检报告！");
   			    return false;
   	        }else{
   	        	saveDone();
   	        }
    	}else{
         	//如果未通过验证, 则加载验证UI
         	$("#monitoringReportForm").parsley().validate();
        }
	})
	
	var saveDone=function(){
    	  var data=$("#monitoringReportForm").serializeJson();
    	  data.stepId="110818";
     	  if(response){
               response.abort();
          }
     	  //加遮罩
      	  $(".infodetails").loadMask("正在保存...", 1, 0.5);
     	  var response = $.ajax({
               url: "monitoringReport/updateState",
               type: "POST",
               //timeout : 5000,
               contentType: "application/json;charset=UTF-8",
               data: JSON.stringify(data),
               success: function (data) {
            	   //取消遮罩
             	   $(".infodetails").hideMask();	 
            	   var content = "数据保存成功！";
                   if(data === "false"){
                       content = "数据保存失败！";
                   }else if(data === "true"){
                	   $("#monitoringReportForm")[0].reset();
                    	$('#monitoringReportTable').cgetData(true);
                    	$(".toolBtn").addClass("hidden");
                   }else{
                   		content = "请上传监检报告！";
                   }
                   var myoptions = {
                           title: "提示信息",
                           content: content,
                           accept: popClose,
                           chide: true,
                           icon: "fa-check-circle"
                       }
                   $("body").cgetPopup(myoptions);
        	}
 		}); 
    }
	

    
 	
 	 $(".cancelBtn").on("click",function(){
     	//清空表单
     	 $("#monitoringReportForm input,#monitoringReportForm textarea").val('');
    	 $("#monitoringReportTable").cgetData(true);
     });

</script>
<!-- ================== END PAGE LEVEL JS ================== -->