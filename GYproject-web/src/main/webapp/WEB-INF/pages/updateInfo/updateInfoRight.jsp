<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<link href="assets/plugins/jedate/skin/jedate.css" rel="stylesheet" />
 <div class="infodetails">
    <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 updateInfoSaveBtn" >保存</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="updateInfoForm" action="updateInfo/updateInfoSaveFile" method="POST" enctype="multipart/form-data">
    		<input type="hidden" id="updateId" name="updateId" />
    		<input type="hidden" id="result" name="result" />
    		<input type="hidden" name="alPath" id="alPath" />
    		<input type="hidden" id="corpName" name="corpName"/>
    		<input type="hidden" id="changeType" name="changeType" value = '2' />
    		<input type="hidden" id="operaterId" name="operaterId" />
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
		            <textarea class="form-control field-editable"  id="updateContent" name="updateContent" rows="14" data-parsley-maxlength="2000" data-parsley-required="true"></textarea>
		        </div>
        	</div>
        	<!-- <div class="form-group col-sm-12">
			<label class="control-label" for="">附件</label>
			<div>
			<div class="hidden hasVal"> 
        			<input type="text" class="form-control input-sm field-not-editable" id="drawName" name="drawName"/>
        			<a class="btn btn-sm btn-primary searchButton " href="" target="_blank">查看</a>
       		        <a class="del_btn btn btn-sm btn-danger"><i class="fa fa-times"></i> 删除</a>
       		    </div>
				<div class="fileupload-buttonbar noVal hidden">
			        <div class="pull-right editbtn">
			            <span class="btn btn-success btn-sm fileinput-button">
			                <i class="fa fa-plus"></i>
			                <span>浏览文件...</span>
			                <input type="file" name="files[]" multiple/>	             	          
			            </span>
			            <button type="submit" class="btn btn-primary btn-sm start hidden">
		                    <i class="fa fa-upload"></i>
		                    <span>上传</span>
		                </button>
		                <a class="surplusDelBtn btn btn-sm btn-danger hidden"><i class="fa fa-times"></i> 删除</a>
			        </div>
			    </div>
		    	The table listing the files available for upload/download
		    	<table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
			</div>
		</div> -->
		</form>
	</div>
</div>
<div class="clearboth p-t-15">
</div> 
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->

<script>

	App.restartGlobalFunction();
	$('.datepicker-default').datepicker({
	    todayHighlight: true
	});

  	
    //隐藏loading
	$(".infodetails").hideMask(); 
    
    //参数传false时，表单不可编辑
    $("#updateInfoForm").toggleEditState(false);
    
    //表单样式适应
    $("#updateInfoForm").styleFit();
    
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('updateInfoForm','updateInfo/viewUpdateInfo','',queryBack);
    
    
  	//保存
	$(".updateInfoSaveBtn").on("click",function(){
        var viewform = $("#updateInfoForm");
        //开启表单验证
        if (viewform.parsley().isValid()) {
        	$("#corpName").val($("#corpId option:selected").text());
	       	var data = viewform.serializeJson();
           	$.ajax({
				type: 'POST',
                url: 'updateInfo/updateInfoSave',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
					var content = "数据保存成功！";
	                if(data === "false"){
	                   	content = "数据保存失败！";
	                }else if(data === "true"){
	                	$("#updateInfoForm input, #updateInfoForm textarea").not(":radio, :checkbox").val('');
                   		$("#updateInfoTable").cgetData(true,queryTableBack);  //列表重新加载
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
                   console.warn("保存失败！");
               }
			}); 
            //如果通过验证, 则移除验证UI
           	viewform.parsley().validate();
       }else{
       	 //如果未通过验证, 则加载验证UI
       	 viewform.parsley().validate();
       }
	});
	
  	
  	var saveDone=function(){
  		if($("#drawName").val()){
  	 		 $(".hasVal").removeClass("hidden");
  	 		 $(".noVal").addClass("hidden");
  	 		 $(".noVal+#filePreviews tr").remove();
  	 	}else{
  	 		 $(".noVal").removeClass("hidden");
  	 		 
  	 		 $(".hasVal").addClass("hidden");
  	 	}
  		$(".editbtn").addClass("hidden");
		$("#updateInfoForm").toggleEditState(false);
  	}
  	
  	
  	/**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	//清空表单
    	$("#updateInfoForm input, #updateInfoForm textarea").not(":radio, :checkbox").val('');
        $("#updateInfoTable").cgetData(true,queryTableBack);  //列表重新加载
    });
  	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->