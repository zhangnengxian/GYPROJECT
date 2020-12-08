<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  toolBtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 confirmBtn" >保存</a>
		<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="custResponseForm" data-parsley-validate="true" action="">
			<input type="hidden" id="projId" name="projId" />
			<input type="hidden"  id="diId" name="diId" />
			<input type="hidden"  id="corpId" name="corpId" />
			<input type="hidden" id="applyDate" name="applyDate" />
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
			<%--<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">回复时间</label>
				<div>
					<input id="designDrawingSheets" type="text" class="form-control input-sm field-editable datepicker-default" name="custReposeDate" data-parsley-required="true" />
				</div>
			</div>--%>
			<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
				<label class="control-label" for="customerResponseInfo">客户回复信息</label>
				<div>
					<textarea class="form-control input-sm field-editable" name="customerResponseInfo" data-parsley-maxlength="500" rows="4"></textarea>
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
    $("#custResponseForm").toggleEditState(false);
    //trSData.custResponseTable.json && $("#totalCost").val(trSData.custResponseTable.json.budgetTotalCost);
     //隐藏loading
    $(".infodetails").hideMask();
    $(".toolBtn").addClass("hidden");
	$("#custResponseForm").styleFit();
	
	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
	
	trSData.t && trSData.t.cgetDetail('custResponseForm','customerResponse/viewProject','',datailBack);
    //materialtableinit();
    $('.confirmBtn').off().on('click',function(){
    	if($("#custResponseForm").parsley().isValid()){
       		$("body").cgetPopup({
              	  title: "提示信息",
              	  content: "是否确认已回复用户?",
              	  accept: saveDone,
              	  chide: false,
              	  icon: "fa-check-circle",
              	  newpop: 'new'
              }); 
    	}else{
         	//如果未通过验证, 则加载验证UI
         	$("#custResponseForm").parsley().validate();
        }
	})
	
	var saveDone=function(){
    	
    	var data=$("#custResponseForm").serializeJson();
     	  if(response){
               response.abort();
          }
     	  //加遮罩
      	  $(".infodetails").loadMask("正在保存...", 1, 0.5);
     	  var response = $.ajax({
               url: "customerResponse/saveRaiseMoney",
               type: "POST",
               //timeout : 5000,
               contentType: "application/json;charset=UTF-8",
               data: JSON.stringify(data),
               success: function (data) {
            	  //取消遮罩
            	  $(".infodetails").hideMask();	 
             	  if (data === "true") {
             		  $("body").cgetPopup({
                       	  title: "提示信息",
                       	  content: "保存成功!",
                       	  accept: successBack,
                       	  chide: true,
                       	  icon: "fa-check-circle",
                       	  newpop: 'new'
                       }); 
             		  
             	  }else{
             		  $("body").cgetPopup({
                       	  title: "提示信息",
                       	  content: "保存失败! <br>" + data,
                       	  accept: popClose,
                       	  chide: true,
                       	  icon: "fa-exclamation-circle",
                       	  newpop: 'new'
                       });  
             	  }
        	}
 		}); 
    }
	
	
  	var ensureDone=function(){};
  	var successBack=function(){
     	$("#custResponseForm")[0].reset();
     	$('#custResponseTable').cgetData(true);
     	$(".toolBtn").addClass("hidden");
    };
    
 	
 	 $(".cancelBtn").on("click",function(){
     	//清空表单
     	 $("#custResponseForm input,#custResponseForm textarea").val('');
    	 $("#custResponseTable").cgetData(true);
     });

</script>
<!-- ================== END PAGE LEVEL JS ================== -->