<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 confirmBtn" >保存</a>
    	<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="projectDetailform" data-parsley-validate="true" action="dataAcquisition/saveData">
		    <input type="hidden" name="projId" id="projId"/>
		    <input type="hidden" name="surveyId" id="surveyId"/>
       		<!--  工程信息 -->
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
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200" ></textarea>
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
          	 <!--  客户信息 -->
		    <div class="form-group col-md-12  noUser">
		        <label class="control-label" for="custName">客户名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 noUser">
		        <label class="control-label" for="custContact">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 noUser">
		        <label class="control-label" for="custPhone">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
			<div class="form-group col-md-12">
				<label class="control-label" for="projectRemark">受理备注</label>
				<div>
					<textarea class="form-control field-not-editable" name="projectRemark" id="projectRemark" rows="2" cols="" data-parsley-maxlength="200"></textarea>
				</div>
			</div>
		   	<!--  勘察信息 -->
		    <div class="form-group col-md-6">
		        <label class="control-label" for="surveyer">勘察人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="surveyer"  name="surveyer" data-parsley-maxlength="20" value="">
		        </div>
		    </div>
		     <div class="form-group col-md-6 ">
		        <label class="control-label" for="">设计人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="designer" name="designer">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" >勘察日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="surveyDate"  name="surveyDate" value="" >
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 clearboth">
		        <label class="control-label" for="dataCollection">资料情况</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable" name="dataCollection" value="1" />已收全
		            </label>
		            <label>
		            	<input type="radio" class="field-editable" name="dataCollection" value="0" checked/>未收全
		            </label>
		        </div>
		    </div>
			<div class="form-group col-md-6 ">
				<label class="control-label" for="acquisitionDays">天数</label>
				<div>
					<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="acquisitionDays" name="acquisitionDays" data-parsley-required="true" data-parsley-type="number" data-parsley-maxlength="16">
				</div>
			</div>
			<div class="form-group col-md-6 ">
				<label class="control-label" for="budgetType">预算方式</label>
				<div>
                    <select class="form-control input-sm field-editable" id="budgetType"  name="budgetType">
                        <option value="" ></option>
                        <c:forEach var="bu" items="${budgetType }">
                            <option value="${bu.CNVALUE}" >${bu.CNNAME}</option>
                        </c:forEach>
                    </select>
                </div>
			</div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="desginRemark">备注</label>
	            <div>
	                <textarea class="form-control field-editable" name ="desginRemark" id="desginRemark" rows="3" data-parsley-maxlength="500" ></textarea>
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
    $("#projectDetailform").toggleEditState(false);
  	//表单样式适应
   	$("#projectDetailform").styleFit();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $(".editbtn").addClass("hidden");
    
    //查右侧工程详述
    trSData.t && trSData.t.cgetDetail('projectDetailform','dataAcquisition/viewProject','',queryDetailBack); 
    
    
    
    
    /**点击右侧维护区 保存 按钮时*/
    $(".confirmBtn").off("click").on("click",function(){
    	if($("#projectDetailform").parsley().isValid()){
    		var dataCollection = $("#projectDetailform input[name='dataCollection']:checked").val();
        	if(dataCollection=="1"){
        		$("body").cgetPopup({
        			title:'提示',
        			content:'是否确认资料收集完毕？',
        			accept:saveData
        		});
        	}else{
        		$("body").cgetPopup({
        			title:'提示',
        			content:'资料未收集完毕，不能推送！',
        			accept:popClose
        		});
        	}
    	}else{
         	//如果未通过验证, 则加载验证UI
         	$("#projectDetailform").parsley().validate();
         }
    	
    }); 
    
    
    var saveData = function(data){
    	//加遮罩
    	$(".infodetails").loadMask("正在保存...", 1, 0.5);
    	var data=$("#projectDetailform").serializeJson();
    	$.ajax({
            type: 'POST',
            url: 'dataAcquisition/saveData',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {

            	//取消遮罩
            	$(".infodetails").hideMask();
            	var content = "数据保存成功！";
            	if(data === "false"){
            		content = "数据保存失败！";
            	}else if(data === "true"){
            		$("#projectDetailform input, #projectDetailform textarea").not(":radio, :checkbox").val('');
            		$("#dataAcquisitionTable").cgetData(true,dataAcquisitionBack);  //列表重新加载
            	}
            	var myoptions = {
                    	title: "提示信息",
                    	content: content,
                    	accept: saveDone,
                    	chide: true,
                    	icon: "fa-check-circle",
                    	newpop: 'new'
                    	
                }
                $("body").cgetPopup(myoptions);
            },
            error: function (jqXHR, textStatus, errorThrown) {
            	//取消遮罩
            	$(".infodetails").hideMask();
                console.warn("资料收集操作区记录保存失败！");
            }
        });
    	//如果通过验证, 则移除验证UI
    	$("#projectDetailform").parsley().reset();
    }
    var saveDone = function(){
    	
    }
   
 	
    
   
   /*  var len = $('#dataAcquisitionTable').DataTable().ajax.json().data ? $('#dataAcquisitionTable').DataTable().ajax.json().data.length : $('#dataAcquisitionTable').DataTable().ajax.json().length;
 	if (len!== 0) {
 		// 维护按钮显示出来
 		$(".editbtn").removeClass("hidden");
 	} else {
 		$(".editbtn").addClass("hidden");
 	}; */
 	
 	//放弃
 	 $(".cancelBtn").on("click",function(){
     	//清空表单
     	$(".editbtn").addClass("hidden");
     	$("#projectDetailform input,#projectDetailform textarea").val('');
    	$("#dataAcquisitionTable").cgetData(true);
     });
 	
    </script>
<!-- ================== END PAGE LEVEL JS ================== -->