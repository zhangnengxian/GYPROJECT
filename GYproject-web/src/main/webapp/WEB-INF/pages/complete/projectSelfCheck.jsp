<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <div class="col-sm-6 col-xs-12">
            <div class="panel panel-inverse tabs-mixin ">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                	</div>
                    <h4 class="panel-title">工程列表</h4>
                </div>
                <div class="panel-body " id="box">
                	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
               		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" width="100%" id="projectSelfCheckListTable" >
               			<thead>
	               			<tr>
	               				<th>工程Id</th>
	               				<th>工程编号</th>
	                			<th>工程名称</th>
	                			<th>状态</th>
	                			<th></th>
	                			<th></th>
	               			</tr>
               			</thead>
               		</table>
            	</div>
			</div>
        </div>
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse tabs-mixin" id="">
			    <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>	
                	<ul class="nav nav-tabs ">
                		<li class="active"><a href="#checkList" data-toggle="tab">自检单</a></li>
						<li class=""><a href="#qualityCheck" data-toggle="tab">质量自检</a></li>
						<!-- <li class=""><a href="#materialCheck" data-toggle="tab">资料自检</a></li> -->
					</ul>
            	</div>
            	<div class="panel-body">
                	<div class="tab-content">
	                	<div class="tab-pane fade in" id="qualityCheck" >
	                		<div id="pre_qualityCheck_panel_box"></div>
	                	</div>
	                	<div class="tab-pane fade in" id="materialCheck" >
						 	<div id="pre_materialCheck_panel_box"></div>
	                	</div>
	                	<div class="tab-pane fade active in saveHiddenBox" id="checkList" >
	                		<div class="toolBtn f-r p-b-10  editbtn">
	                			<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton temporarySaveBtn" >保存</a>
	    					 	<a href="javascript:;" class="btn btn-info btn-sm m-l-5 pushButton CheckSave">推送</a>
	        				 	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 CheckCancel">放弃</a>
						 	</div>
						 	<div class="clearboth form-box">
								<input type="hidden" id="sysDate" value="${sysDate}"/>
						 	 	<form class="form-horizontal" id="checkListForm" action="" data-parsley-validate="true">
						 	 		<input type="hidden" name="projId" id="projId"/>
						 	 		<input type="hidden" name="silId" id="silId"/>
						 	 		<input type="hidden" name="flag" id="flag"/>
						 	 		<input type="hidden" name="isDel" id="isDel"/>
								    <div class="form-group col-md-12 col-sm-12">
									     <label class="control-label" for="projName">工程名称</label>
									     <div>
									         <input class="form-control input-sm field-not-editable" name="projName" id="projName" value="">
									     </div>
									</div>
									<div class="form-group  col-md-6 col-sm-6">
								        <label class="control-label" for="projNo">工程编号</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" />
								        </div>
								    </div>
								    <div class="form-group col-md-6  col-sm-6">
								        <label class="control-label" for="projAddr">工程地点</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" />
								        </div>
								    </div>
								    <div class="form-group col-md-12 col-sm-12" >
								        <label class="control-label" for="projScaleDes">工程规模</label>
								        <div>
								            <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" ></textarea>
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
								    <div class="form-group col-md-12 col-sm-12">
									     <label class="control-label" for="cmoName">施工单位</label>
									     <div>
									         <input class="form-control input-sm field-not-editable" name="cmoName" id="cmoName" value="">
									     </div>
									</div>
									<div class="form-group col-sm-12">
										<!-- 施工单位意见改为检查结果 -->
									    <label class="control-label" for="projManagerOpinion">检查结果</label>
									     <div> 
									         <textarea class="form-control field-editable" name="projManagerOpinion" id="projManagerOpinion" rows="4" cols="" data-parsley-maxlength="200"></textarea>
									     </div>
									</div>
									 <div class="form-group col-md-6 col-sm-12 backInfo hidden">
										<!-- 新加字段 -->
										<label class="control-label">退回整改</label>
										<div>
											<label><input class="field-not-editable allText suJgj" type="radio" name="isBack" value="1" > 是</label>
											<label><input class="field-not-editable allText suJgj" type="radio" name="isBack" value="0" > 否</label>
										</div>
									</div>
								    <div class="form-group col-md-6 backInfo hidden">
								    	<!-- 新加字段 -->
								        <label class="control-label" for="backRemark">预验收整改备注</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable allText suJgj" id="backRemark"  name="backRemark"  data-parsley-maxlength="200" >
								        </div>
								    </div>
								    <div class="form-group col-md-6 col-sm-6">
								    	<!-- 项目经理改为检查人 -->
										<label class="control-label signature-tool sign-require" for="projManager">检查人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="projManager" name="projManager" value="" class="sign-data-input">
		                               		<img class="projManager" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-6  col-sm-6" >
								        <label class="control-label">自检日期</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable datepicker-default " id="silDate"  name="silDate" >
								        </div>
								    </div>
									<!-- <div class="form-group col-sm-12">
									     <label class="control-label" for="managementOpinion">甲方代表意见</label>
									     <div> 
									        <textarea class="form-control field-editable" name="managementOpinion" id="managementOpinion" rows="3" cols="" ></textarea>
									     </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool sign-require" for="comPrincipal">甲方代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="comPrincipal" name="comPrincipal" value="" class="sign-data-input">
		                               		<img class="comPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>
									<div class="form-group col-md-12 col-sm-12">
									     <label class="control-label" for="suName">监理单位</label>
									     <div>
									         <input class="form-control input-sm field-not-editable" name="suName" id="suName" value="">
									     </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool sign-require" for="suPrincipal">现场监理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="suPrincipal" name="suPrincipal" value="" class="sign-data-input">
		                               		<img class="suPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div> -->
									
									
									
									<!-- <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool sign-require" for="suPrincipal">现场监理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="suPrincipal" name="suPrincipal" value="" class="sign-data-input">
		                               		<img class="suPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div>  -->
									
									<!-- 
									<div class="form-group col-md-6 col-sm-12">
									     <label class="control-label" for="inspector">检查人</label>
									     <div>
									         <input class="form-control input-sm field-editable" name="inspector" id="inspector" value="">
									     </div>
									</div>
									 <div class="form-group col-sm-12">
									     <label class="control-label" for="managementOpinion">管理部意见</label>
									     <div> 
									        <textarea class="form-control field-editable" name="managementOpinion" id="managementOpinion" rows="3" cols="" ></textarea>
									     </div>
									</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool sign-require" for="comPrincipal">施工管理处</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="comPrincipal" name="comPrincipal" value="" class="sign-data-input">
		                               		<img class="comPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
									</div> -->
							 	</form>
						 	</div>
	                	</div>
	                </div>
				</div>
       		</div>
 		 </div>
	</div>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程自检 - 工程项目管理系统 ');
   	// $("#box table").hideMask();
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
 	 //radio不可编辑
	$("input:radio").attr("disabled","disabled");
	$('#checkListForm').styleFit();
	$("#checkListForm").toggleEditState(false);
    $('.editbtn').addClass('hidden');
	$.getScript('projectjs/complete/project-self-check.js?v='+Math.random()).done(function () {
		projectSelfCheck.init();
    });
	/**右侧维护区 放弃 按钮点击后*/
	 $(".CheckCancel").off("click").on("click",function(){
			$('#sirNum').val('');
			$('#inspector').val('');
			$('#projManagerOpinion').val('');
			$('#managementOpinion').val('');
			$("#silDate").val("");
			$("#materialCheckForm input").not(":radio, :checkbox").val('');
			$("#qualityCheckForm input").not(":radio, :checkbox").val('');
			$("#signBtn_5,#signBtn_2,#signBtn_3").resetSign();
			//清空radio选项
			$('input:radio:checked').attr('checked',false);
			//radio不可编辑
			$("input:radio").attr("disabled","disabled");
			//切换不可编辑状态
			$("#checkListForm").toggleEditState(false);
			$("#qualityCheckForm").toggleEditState(false);
			$("#materialCheckForm").toggleEditState(false);
			//维护按钮隐藏
			$('.editbtn').addClass('hidden');
			
	 });
	 /**右侧维护区 保存 按钮点击后*/
	 $(".CheckSave").off("click").on("click",function(){
		//验证必签签字是否已签
	        var signtools =$('#checkListForm').find(".signature-tool.sign-require"),
	        stl = signtools.length,
	        sBlank = 0;
	        for(var i=0; i<stl; i++){
	        	var tstool = signtools.eq(i).next("div").find("a.btn-white"),
	        	tsinput = tstool.siblings(".sign-data-input");
	        	if(!tsinput.val() || tsinput.val().length < 312){
	        		tstool.addClass("required-sign");
	        		sBlank++;
	        	}
	        }
	        if(sBlank){
	        	$("body").cgetPopup({
	            	title: "提示信息",
	            	content: "请完成签字!",
	            	accept: popClose,
	            	chide: true,
	            	icon: "fa-warning",
	            	newpop: 'new'
	            });
	        	return false;
	        }
		 var myoptions = {
              	title: "提示信息",
              	content: "是否确认质量自检，点击确定推送！",
              	accept: savedone,
              	icon: "fa-check-circle"
          }
         $("body").cgetPopup(myoptions);
	 });
	var savedone = function(){
		 $("#flag").val("1");
		 var data=$("#checkListForm").serializeJson();
		 var data2=$("#qualityCheckForm").getDTFormData();
		 var data3=$("#materialCheckForm").getDTFormData();
		 $.merge(data3,data2);
		 data.selfInspectionRecords = data3;
 		$.ajax({
             type: 'POST',
             url: 'projectSelfCheck/saveSelfInspectionRecord',
             contentType: 'application/json;charset=UTF-8',
             data:JSON.stringify(data),
             success: function (data) {
             	var content = "数据保存成功！";
             	if(data === "false"){
             		content = "数据保存失败！";
             	}
             	if(data==="incompleteData"){
             		content="质量自检未填写，推送失败！";
             	}
             	var myoptions = {
                     	title: "提示信息",
                     	content: content,
                     	accept: acdone,
                     	chide: true,
                     	icon: "fa-check-circle",
                     	newpop: 'new'
                 }
             	
                $("body").cgetPopup(myoptions);
             },
             error: function (jqXHR, textStatus, errorThrown) {
                 console.warn("工程自验记录保存失败！");
             }
         });
		 
	 }
	 var acdone = function(){
			//$('#inspector').val('');
			$('#projManagerOpinion').val('');
			$('#managementOpinion').val('');
			//$("#signBtn_5,#signBtn_2").resetSign();
			$('#projName').val('');
			$('#cmoName').val('');
			$("#materialCheckForm input").not(":radio, :checkbox").val('');
			$("#projectSelfCheckListTable").cgetData(true);
         	//清空radio选项
			$('input:radio:checked').attr('checked',false);
			//radio不可编辑
			$("input:radio").attr("disabled","disabled");
			//切换不可编辑状态
			$('#qualityCheckForm,#materialCheckForm,#checkListForm').toggleEditState(false);
			//维护按钮隐藏
			$('.editbtn').addClass('hidden');
	 }
	 //点击暂存
	 $(".temporarySaveBtn").on("click",function(){
		 if($("#checkListForm").parsley().isValid() /* && $("#qualityCheckForm").parsley().isValid() */){
			 
			//加遮罩
			 $(".saveHiddenBox").loadMask("正在保存...", 1, 0.5);
			 $("#flag").val("0");
		    	var data=$("#checkListForm").serializeJson();
		    	console.info("data--");
		    	console.info(data);
				var data2=$("#qualityCheckForm").getDTFormData();
				console.info("data2--");
			    console.info(data2);
				/* var data3=$("#materialCheckForm").getDTFormData();
				console.info("data3--");
			    console.info(data3);
				$.merge(data3,data2); */
				data.selfInspectionRecords = data2;
				console.info("data4--");
			    console.info(data);
				$.ajax({
		            type: 'POST',
		            url: 'projectSelfCheck/saveSelfInspectionRecord',
		            contentType: 'application/json;charset=UTF-8',
		            data:JSON.stringify(data),
		            success: function (data) {
		            	//取消遮罩
		            	$(".saveHiddenBox").hideMask();	
		            	var content = "数据保存成功！";
		            	if(data === "false"){
		            		content = "数据保存失败！";
		            	}
		            	var myoptions = {
		                    	title: "提示信息",
		                    	content: content,
		                    	accept: acdone,
		                    	chide: true,
		                    	icon: "fa-check-circle",
		                    	newpop: 'new'
		                }
		            	
		               $("body").cgetPopup(myoptions);
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		            	//取消遮罩
		            	$(".saveHiddenBox").hideMask();	
		                console.warn("工程自验记录保存失败！");
		            }
		        });
			 $("#checkListForm").parsley().reset();
		 }else{
	         //如果未通过验证, 则加载验证UI
	         $("#checkListForm").parsley().validate();
	     }
	 })
	 
	 
	 
	 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->