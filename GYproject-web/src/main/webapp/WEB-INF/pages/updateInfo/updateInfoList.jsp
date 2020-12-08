<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <!-- <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 custSaveBtn" >保存</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div> -->
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="updateInfoForm" action="">
    		<input type="hidden" id="updateId" name="updateId" />
    		<input type="hidden" id="operaterId" name="operaterId" />
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="updateNo">更新编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="updateNo" name="updateNo"  data-parsley-maxlength="50" data-parsley-required="true"/>
		        </div>
		    </div>  
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">更新标题</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="updateNumber" name="updateNumber" data-parsley-maxlength="50" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="updateTime">更新时间</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable timestamp all"  id="updateTime" name="updateTime"  />
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="operater">操作人</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable all"  id="operater" name="operater"  data-parsley-maxlength="20" />
		        </div>
		    </div>
		    <div class="form-group col-md-12">
            	<label class="control-label" for="updateContent">更新内容</label>
            	<div>
		            <textarea class="form-control field-editable"  id="updateContent" name="updateContent" rows="8" data-parsley-maxlength="2000" data-parsley-required="true"></textarea>
		        </div>
        	</div>
		</form>
	</div>
	<div class=" panel-body ">
		<ul class="nav nav-tabs">
	       	<li class="active"><a href="#default-tab-1-1" id="listTab-1" data-toggle="tab">更新列表</a></li>
	     	<li class=""><a href="#default-tab-2-2" id="signTab-1"  data-toggle="tab">附件列表</a></li>
	   	</ul>
	</div>
	<div class="tab-content">
       	<div class="tab-pane fade active in btn-top" id="default-tab-1-1" >
       		<div class="clearboth form-box m-t-5">
				<form id="scaleTableForm">
					<table id="updateInfoTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
		            	<thead>
		                    <tr>
		                    	<th>工程ID</th>
		                        <th>更新编号</th>
		                        <th>更新标题</th>
		                        <th>更新时间</th>
		                    </tr>
		                </thead>
		            </table>
				</form>
			</div>
		</div>
		<div class="tab-pane fade in btn-top" id="default-tab-2-2">
				<table id="dataPopTableSecond-1" class="table table-striped table-bordered nowrap" width="100%">
			  		<thead>
			    		<tr>
			    			<th></th>
			    		    <th></th>
			          		<th>资料名称</th>
			          		<th>资料类型</th>
			           		<th>签收日期</th>
			           		<th>签收人</th>
			           		<th width='40'>操作</th>
			          	</tr>
			      	</thead>
				</table>
			</div>
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
	
  //参数传false时，表单不可编辑
    $("#updateInfoForm").toggleEditState(false);
    
    //表单样式适应
    $("#updateInfoForm").styleFit();
    $.getScript('projectjs/updateInfo/update-info-list.js').done(function () {
    	updateInfo.init();
    });
	
    
	//保存
	$(".custSaveBtn").on("click",function(){
		$("#custForm").cformSave('',saveCustBack,'');
	});
	
	var saveCustBack = function(data){
		
		var content = "保存成功！";
		if(data === "false"){
			content = "保存失败！";
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