<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />

<!-- ================== END PAGE LEVEL STYLE ================== -->
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn hidden">
    	<a href="javascript:;" class="btn btn-query  btn-sm m-l-5  saveBtnBudget" >保存</a>
        <a href="javascript:;" class="btn  btn-warn btn-sm m-l-5  giveUpBtn">放弃</a> 
	</div>
	<div class="form-box clearboth">
		<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
		 <%--//值为2时文件格式支持多种（不影响其他页面功能写死）--%>
		<input type="hidden" id="changeType" value="2"/>
		<form class="form-horizontal" id="budgetSumForm" action="budgetResultRegister/updateBudgetFile" data-parsley-validate="true"  enctype="multipart/form-data" >
			<input type="hidden" name="projId" id="projId" />
			<input type="hidden" name="budgetId" id="budgetId" />
			<input type="hidden" id="result" name="result">
			<input type="hidden" id="menuDes" name="menuDes">
<!-- 			<input type="hidden" name="stepId" id="stepId" /> -->
			<input type="hidden" name="alPath" id="alPath" />
			<input type="hidden" id="drawUrl" name="drawUrl" value="${drawUrl1}">
			<input type="hidden" name ="corpId" />
			<input type="hidden" name ="deptId" />
			<input type="hidden" name ="tenantId" />
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
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200" value=''></textarea>
	            </div>
            </div>
            <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="200"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes"  data-parsley-maxlength="100"/>
		    		<!-- <select class="form-control input-sm field-not-editable" id="projectType"  name="projectType" data-parsley-required="true" >
						<option value="1" >居民户工程</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes"  data-parsley-maxlength="100"/>
		    		<!-- 
		    		<select class="form-control input-sm field-not-editable" id="investmentMethod"  name="investmentMethod" data-parsley-required="true" >
						<option value="1" >用户出资（自有资金）</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"  data-parsley-maxlength="200"/>
		    		
		    		<!-- <select class="form-control input-sm field-not-editable" id="deptName"  name="deptName" data-parsley-required="true" >
						<option value="1" >民用组</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="custName">申报单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""  data-parsley-maxlength="200"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custContact">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custPhone">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="13" value=""/>
		       
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="surveyer">勘察人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="surveyer"  name="surveyer" data-parsley-maxlength="200" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="designer">设计人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="designer"  name="designer" data-parsley-maxlength="200" value="">
		        </div>
		    </div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
				<label class="control-label" for="">预算总造价</label>
	            <div>
	            	<input type="text" class="form-control input-sm  fixed-number field-editable text-right" data-parsley-required="true" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00" id="budgetTotalCost" name="budgetTotalCost" value=""/>
	            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6">
				<label class="control-label" for="">工程费</label>
	            <div>
	            	<input type="text" class="form-control input-sm  fixed-number field-editable text-right" data-parsley-min="0.00" data-parsley-maxlength="16" id="civilCost" name="civilCost" value=""/>
	            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
			 </div>
			    <!-- <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">审定价</label>
	            <div>
	            	<input type="text" class="form-control input-sm  fixed-number field-editable text-right"  data-parsley-min="0.01" id="authorizedCost" name="authorizedCost" value=""/>
	            </div>
			   </div> -->
			<div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
	            <label class="control-label" for="materialCost">主材费</label>
	            <div>
					<input type="text" class="form-control input-sm fixed-number field-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="materialCost" name="materialCost" value=""/>
	             	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
	        </div> 
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">监理费</label>
	              <div>
	           		<input type="text" class="form-control input-sm fixed-number field-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00" id="suCost" name="suCost" value="" />
	             	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	             </div>
	          </div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="">带气作业费</label>
	              <div>
	           		<input type="text" class="form-control input-sm fixed-number field-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00" id="inspectionCost" name="inspectionCost" value="" />
	             	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	             </div>
	          </div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">带气次数</label>
				<div>
					<input type="text" class="form-control input-sm field-editable text-right" data-parsley-type="number" data-parsley-maxlength="6" id="gasTimes" name="gasTimes" value=""/>
					<a href="javascript:;" class="btn btn-sm btn-default">次</a>
				</div>
			</div>
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="designCost">设计费</label>
	              <div>
	           		<input type="text" class="form-control input-sm fixed-number field-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="designCost" name="designCost" value=""/>
	            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	             </div>
	          </div> 
	          <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	              <label class="control-label" for="unforeseenCost">不可预见费</label>
	              <div>
	           		<input type="text" class="form-control input-sm fixed-number field-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="unforeseenCost" name="unforeseenCost" value=""/>
	             	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	             </div>
	          </div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="annunciatorCost">报警器费用</label>
				<div>
					<input type="text" class="form-control input-sm fixed-number field-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="annunciatorCost" name="annunciatorCost" value=""/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-12">
	            <label class="control-label" for="remark">备注</label>
	            <div>
	                <textarea class="form-control  field-editable" name ="remark" id="remark" rows="5" data-parsley-maxlength="400" value=''></textarea>
	            </div>
            </div>	
            <!-- <div class="form-group col-md-12">
	            <label class="control-label" for="">附件</label>
           		<div>
					<div class="hidden hasVal"> 
	         			<input type="text" class="form-control input-sm field-not-editable" id="drawName" name="drawName"/>
	         			<a class="btn btn-sm btn-primary searchButton " href="" target="_blank">查看</a>
         		        <a class="btn btn-sm btn-primary Search_Button" href="javascript:">查看</a>
         		        <a class="del_btn btn btn-sm btn-danger"><i class="fa fa-times"></i> 删除</a>
         		    </div>
						<div class="fileupload-buttonbar noVal hidden">
					        <div class="pull-right toolBtn">
					            <span class="btn btn-success btn-sm fileinput-button">
					                <i class="fa fa-plus"></i>
					                <span>浏览文件...</span>
					                <input type="file" name="files[]" multiple/>	             	          
					            </span>
					            <button type="submit" class="btn btn-primary btn-sm start hidden">
				                    <i class="fa fa-upload"></i>
				                    <span>上传</span>
				                </button>
					        </div>
					    </div>
				    	The table listing the files available for upload/download
				    	<table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
					</div>
            </div> -->
	         <div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
	              <label class="control-label" for="budgeter">预算员</label>
	              <div>
	           		<input type="text" class="form-control input-sm field-not-editable"    id="budgeter" name="budgeter" value=""/>
	             </div>
	          </div>
            <%-- <div class="form-group col-md-6"> 
 		       <label class="control-label signature-tool sign-require" for="budgeterSign">签字</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="budgeterSign" name="budgeterSign" data-parsley-required="true">
					<input type="hidden" id="budgeterSign_postType" name="budgeterSign_postType" value="${budgeterPost }" >
					<img class="budgeterSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
	    	</div>  --%>
		</form>
	</div>
</div>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //初始化上传文件控件
    /*   $.getScript('projectjs/data/form-multiple-upload.demo3.js').done(function() {
        FormMultipleUpload.init($('#budgetSumForm'));
    }); */
	$("#budgetSumForm").hideMask();
	$("#budgetSumForm").toggleEditState().styleFit();
	/* $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
        var signatures = $("#signBtn_1");
        signatures.handleSignature(false);
    }); */
	var changeVal;
	if(trSData.budgetRegisterTable.t){
	   trSData.budgetRegisterTable.t.cgetDetail('budgetSumForm','budgetResultRegister/queryPro','',queryBackView);
	}else{
		$(".editbtn").addClass("hidden");
	}
	
	$(".editbtn").addClass("hidden");
	
	$(".saveBtnBudget").on("click",function(){
		
		var t = $("#budgetSumForm");
    	//开启表单验证
        if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
            var tipsHtml="<span style='background: linear-gradient(to right, red, blue);-webkit-background-clip: text;color: transparent;'>预算审核</span>"
            $("body").cgetPopup({
	        	title: "提示信息",
	        	content: "确认后,将推送到下一步->"+tipsHtml+", 您确定要提交吗？",
	        	accept:saveBtnBack,
	        	icon: "fa-warning",
	        	//newpop: 'new'
	        });
        	
            //如果通过验证, 则移除验证UI
            t.parsley().validate();
        } else {
            //如果未通过验证, 则加载验证UI
            t.parsley().validate();
        };
	});

	var saveBtnBack = function(){
		// 防止多次提交
        if(response){
            response.abort();
        }
        var data = $('#budgetSumForm').serializeJsonString();
        //加遮罩
        $(".infodetails").loadMask("正在保存...", 1, 0.5);
        var dataJson={};
        dataJson.result=data;
		var response = $.ajax({
			url: 'budgetResultRegister/updateBudgets',
			type: "POST",
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify(dataJson),
			success: function (data) {
				console.info("data--"+data);
				//取消遮罩
				$(".infodetails").hideMask();	
				var content = "数据保存成功！";
		         if(data === "false"){
		             content = "数据保存失败！";
		         }else {
		        	 $("#budgetRegisterTable").cgetData("",budgetBack);  
		         }
		         var myoptions = {
		             title: "提示信息",
		             content: content,
		             accept: popCloseSure,
		             chide: true,
		             icon: "fa-check-circle",
		             newpop: 'new'
		         }
		         $("body").cgetPopup(myoptions);
			}
		});
	}
	
	
	var popCloseSure=function(){
		$("#budgetRegisterTable").cgetData("",budgetBack);  
	}
	
	//其他费用改变时触发总金额改变
	$("#otherCost1").on("change",function(){
		budgetCallback();
	});

    
   //信息区放弃事件 
    $('.giveUpBtn').off().on('click',function(){
    	//$('ul.nav>li').removeClass("active");
		//$('#AlterationTab').click();
		$("#budgetSumForm").toggleEditState(false);
		$("#budgetRegisterTable").cgetData("",budgetBack);  //列表重新加载
		$(".editbtn").addClass("hidden");
    })
    
        
        /* 	输入数字校验 */ 
        $('.fixed-number').on('keyup', function(){
        	  $(this).parsley().validate();
        	}); 

    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->