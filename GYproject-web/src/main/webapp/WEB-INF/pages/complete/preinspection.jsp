<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-6 -->
		<div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">待预验收列表</h4>
                </div>
                <div class="panel-body">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>
                    <table id="preInspectionTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th>工程Id</th>
                            	<th>工程编号</th>
                                <th>工程名称</th>
                                <th>状态</th>
                                <th></th>
                                <!-- <th></th> -->
                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
        <!-- end col-6 -->
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse tabs-mixin" >
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <!-- <h4 class="panel-title">预验收信息</h4> -->
			        <ul class="nav nav-tabs ">
                		<li class="active"><a href="#checkList" id="checkListTab" data-toggle="tab">预验收信息</a></li>
						<li class=""><a href="#qualityCheck" id="qualityCheckTab" data-toggle="tab">质量预验</a></li>
						<li class=""><a href="#materialCheck" id="materialCheckTab" data-toggle="tab">资料预验</a></li>
						<!-- <li class=""><a href="#dataCheck" data-toggle="tab">材料预验</a></li> -->
					</ul>
			    </div>
			   	<div class="panel-body" id="pre_inspection_panel_box">
			   		<div class="tab-content">
				   		<div class="tab-pane fade in" id="materialCheck" >
				   			<div id="pre_materialCheck_panel_box"></div>
		                </div>
		                <div class="tab-pane fade in" id="qualityCheck" >
	                		<div id="pre_qualityCheck_panel_box"></div>
	                	</div>
	                	<div class="tab-pane fade in" id="dataCheck" >
	                		<div id="pre_dataCheck_panel_box"></div>
	                	</div>
		                <div class="tab-pane fade active in saveHiddenBox" id="checkList" >
		                	<div class="toolBtn f-r p-b-10  editbtn">
	                			<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5  temporarySaveBtn" >保存</a>
	    					 	<a href="javascript:;" class="btn btn-info btn-sm m-l-5  CheckSave">推送</a>
	        				 	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 CheckCancel">放弃</a>
						 	</div>
						 	<div class="clearboth form-box">
								<input type="hidden" id="sysDate" value="${sysDate}"/>
								<input type="hidden" id="post" value="${post}">
								<input type="hidden" id="sujgj" value="${sujgj}">
								<input type="hidden" id="suCsePost" value="13">
						 		<form class="form-horizontal" id="preinspectionForm" action="">
						 			<input type="hidden" id="flag" name="flag">
						 			<input type="hidden" name="projId" id="projId"/>
						 	 		<input type="hidden" name="piId" id="piId"/>
						 	 		<input type="hidden" name="isDel" id="isDel"/>
						 	 		<input type="hidden" id="isBuilderSign" name="isBuilderSign">
						 			<div class="form-group col-md-12 col-sm-12">
									     <label class="control-label" for="projName">工程名称</label>
									     <div>
									         <input class="form-control input-sm field-not-editable" name="projName" id="projName" value="">
									     </div>
									</div>
									<div class="form-group  col-md-6 col-sm-6">
								        <label class="control-label" for="projNo">工程编号</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" value=""/>
								        </div>
								    </div>
								    <div class="form-group col-md-6  col-sm-6">
								        <label class="control-label" for="projAddr">工程地点</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
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
								    <div class="form-group col-md-6">
								    	<!-- 新加字段 -->
								        <label class="control-label" for="">计划下达日期</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable datepicker-default " id="cpArriveDate"  name="cpArriveDate" value="">
								        </div>
								    </div>
								    <div class="form-group col-md-6">
								    	<!-- 新加字段 -->
								        <label class="control-label" for="">实际开工日期</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable datepicker-default timestamp" id="plannedStartDate"  name="plannedStartDate" value="">
								        </div>
								    </div>
								    <div class="form-group col-md-6">
								    	<!-- 新加字段 -->
								        <label class="control-label">计划竣工日期</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable datepicker-default timestamp" id="planCompleteDate"  name="planCompleteDate" >
								        </div>
								    </div>
								    <div class="form-group col-md-6">
								    	<!-- 新加字段 -->
								        <label class="control-label">实际竣工日期</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="actualCompleteDate"  name="actualCompleteDate" data-parsley-required="true">
								        </div>
								    </div>
								    <!-- <div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>延期说明</h4></div> -->
									<div class="form-group col-md-6 col-sm-12 ">
										<!-- 新加字段 -->
										<label class="control-label">延期真实、准确</label>
										<div>
											<label><input class="field-editable allText suJgj" type="radio" name="isDelay" value="1"  checked> 是</label>
											<label><input class="field-editable allText suJgj" type="radio" name="isDelay" value="0" > 否</label>
											<label><input class="field-editable allText suJgj" type="radio" name="isDelay" value="2" > 无</label>
										</div>
									</div>
									<div class="form-group col-md-6">
								    	<!-- 新加字段 -->
								        <label class="control-label" for="delayRemark">延期说明</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-editable allText suJgj" id="delayRemark"  name="delayRemark"  data-parsley-maxlength="200" >
								        </div>
								    </div>
									<!-- <div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>设计变更手续</h4></div> -->
									<div class="form-group col-md-6 col-sm-12 ">
										<!-- 新加字段 -->
										<label class="control-label">变更是否齐全</label>
										<div>
											<label><input class="field-editable allText suJgj" type="radio" name="isChange" value="1"  checked> 是</label>
											<label><input class="field-editable allText suJgj" type="radio" name="isChange" value="0" > 否</label>
											<label><input class="field-editable allText suJgj" type="radio" name="isChange" value="2" > 无</label>
										</div>
									</div>
								    <div class="form-group col-md-6">
								    	<!-- 新加字段 -->
								        <label class="control-label" for="changeRemark">变更说明</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-editable allText suJgj" id="changeRemark"  name="changeRemark"  data-parsley-maxlength="200"  >
								        </div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<!-- 新加字段 -->
										<label class="control-label">是否退回整改</label>
										<div>
											<label><input class="field-not-editable allText suJgj suCse" type="radio" name="isBack" value="1" > 是</label>
											<label><input class="field-not-editable allText suJgj suCse" type="radio" name="isBack" value="0" > 否</label>
											<label><input class="field-not-editable allText suJgj suCse" type="radio" name="isBack" value="" > 无</label>
										</div>
									</div>
								    <div class="form-group col-md-6 backRemark hidden">
								    	<!-- 新加字段 -->
								        <label class="control-label" for="backRemark">整改备注</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-editable allText suJgj suCse" id="backRemark"  name="backRemark"  data-parsley-maxlength="200" data-parsley-required="true">
								        </div>
								    </div>
								    <div class="form-group col-md-12">
								    	<!-- 新加字段 -->
								        <label class="control-label" for="sirOpinion">验收意见</label>
								        <div>
								           <textarea class=" form-control input-sm field-editable allText suJgj suCse" id="sirOpinion"  name="sirOpinion" rows="4" data-parsley-maxlength="200" ></textarea>
								        </div>
								    </div>
								    <div class="form-group col-md-12 col-sm-12">
									     <label class="control-label" for="cmoName">施工单位</label>
									     <div>
									         <input class="form-control input-sm field-not-editable" name="cmoName" id="cmoName" value="">
									     </div>
									</div>
									<div class="form-group col-md-12 col-sm-12">
									     <label class="control-label" for="suName">监理单位</label>
									     <div>
									         <input class="form-control input-sm field-not-editable" name="suName" id="suName" value="">
									     </div>
									</div>
									<!--施工员、项目经理都可填写-->
									 <div class="form-group  col-md-6 allSign cuPm construction">
								        <label class="control-label signature-tool sign-require" for="projManager">施工单位</label>
								        <div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="projManager" name="projManager" value="" class="sign-data-input">
											<input type="hidden" class="signPost"  id="projManager_postType" name="projManager_postType" value="${cuPm }">
											<img class="" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>

									<!--施工员-->
									<div class="form-group  col-md-6">
										<div>
											<input type="hidden" id="construction" name="construction" value="" class="sign-data-input">
											<input type="hidden" class="signPost"  id="construction_postType" name="construction_postType" value="${construction }">
										</div>
									</div>

								    <div class="form-group col-md-6 hidden selectSignDate">
								        <label class="control-label" >日期</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable datepicker-not-default  " id="pmDate"  name="pmDate" data-parsley-maxlength="100" value="">
								        </div>
								    </div>
									<!-- <div class="form-group  col-md-6">
										甲方代表改为现场代表
								        <label class="control-label signature-tool sign-require" for="cmoDirector">现场代表</label>
								        <div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="cmoDirector" name="cmoDirector" value="" class="sign-data-input">
											<img class="" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <div class="form-group col-md-6  hidden selectSignDate">
								        <label class="control-label" >日期</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-editable datepicker-default " id="comDate"  name="comDate" data-parsley-maxlength="100" value="">
								        </div>
								    </div> -->
									
								    <div class="form-group  col-md-6 allSign suJgj clearboth">
								        <label class="control-label signature-tool sign-require" for="suFieldJgj">现场监理</label>
								        <div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="suFieldJgj" name="suFieldJgj" value="" class="sign-data-input">
											<input type="hidden" class="signPost"  id="suFieldJgj_postType" name="suFieldJgj_postType" value="${sujgj }">
											<img class="" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
								    <div class="form-group col-md-6 hidden selectSignDate">
								        <label class="control-label" for="cesDate">日期</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable datepicker-not-default " id="cesDate"  name="cesDate" data-parsley-maxlength="100" value="">
								        </div>
								    </div>
									<!--现场代表签字-->
									<%-- <c:if test="${ !empty cmoDirectorSignPage}">
										<c:import url="${cmoDirectorSignPage}"></c:import>
									</c:if> --%>
									   <c:import url="cmoDirectorSignPage.jsp"></c:import>
						 		</form>
						 	</div>
		                </div>
	                </div> 	
			   	</div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>


    App.restartGlobalFunction();
    App.setPageTitle('预验收 - 工程管理系统');
    
    $("#preinspectionForm").toggleEditState(false);
    $('.editbtn').addClass('hidden');
    
    $.getScript('projectjs/complete/preinspection.js?'+Math.random()).done(function () {
    	preinspection.init();
	});
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $(".CheckCancel").off().on("click",function(){
    	flag=1;
    	$('.editbtn').addClass('hidden');
    	$("#preinspectionForm").toggleEditState(false);
		$("#materialCheckForm").toggleEditState(false);
		$("#qualityCheckForm").toggleEditState(false);
		$("#dataCheckForm").toggleEditState(false);
		$("input:radio").attr("disabled","disabled");
		$("input:radio").addClass("disabled");
    })
    
    //点击暂存
	 $(".temporarySaveBtn").on("click",function(){
		 if($('input[name="isBack"]:checked').val()=='1'){
	       	$("#actualCompleteDate").attr("data-parsley-required",false);
	      }
			if($("#preinspectionForm").parsley().isValid()){
				//加遮罩
				$(".saveHiddenBox").loadMask("正在保存...", 1, 0.5);
				$("#flag").val("0");
			   	var data=$("#preinspectionForm").serializeJson();
				var data2=$("#qualityCheckForm").getDTFormData();
				var data3=$("#materialCheckForm").getDTFormData();
			   /*  var data1=$("#dataCheckForm").getDTFormData();
			    console.info("dataCheck----------------");
			    console.info(data1);
				$.merge(data1,data2);
				console.info("data1----------------");
				console.info(data1); */
				$.merge(data3,data2);
				data.preInspectionRecords = data3;
				
				$.ajax({
			           type: 'POST',
			           url: 'preinspection/savePreinspection',
			           contentType: 'application/json;charset=UTF-8',
			           data:JSON.stringify(data),
			           success: function (data) {
			   			//取消遮罩
			   			$(".saveHiddenBox").hideMask();	
			           	var content = "数据保存成功！";
			           	if(data === "false"){
			           		content = "数据保存失败！";
			           	}else  if(data === "already"){
			           		content = "已有终端操作此页面，请刷新查看！";
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
			               console.warn("预验收记录保存失败！");
			           }
			       });
				$("#preinspectionForm").parsley().reset();
			}else{
				//如果未通过验证, 则加载验证UI
			       	$("#preinspectionForm").parsley().validate();
			}
	 })
   
	 
	  var acdone = function(){
    		flag = 1;
    		//清空radio选项
			$('input:radio:checked').attr('checked',false);
			//radio不可编辑
			$("input:radio").attr("disabled","disabled");
			$("input:radio").addClass("disabled");
			$("#preinspectionForm").formReset();
			$("#preInspectionTable").cgetData(true);
         	
			//切换不可编辑状态
			//$('#qualityCheckForm,#materialCheckForm,#preinspectionForm').toggleEditState(false);
			//维护按钮隐藏
			$('.editbtn').addClass('hidden');
			sureClose();
	 }
    
    $(".CheckSave").off().on("click",function(){
       		//日期添加校验
       		$("#comDate").attr("data-parsley-required",true);
       		$("#pmDate").attr("data-parsley-required",true);
       		$("#cesDate").attr("data-parsley-required",true);
       		$("#amDate").attr("data-parsley-required",true);
       	if($('input[name="isBack"]:checked').val()=='1'){
       	//验证整改意见
       		if($("#backRemark").val()==''){
       			alertInfo("清填写整改意见！");
       			return;
       		}
     	       backDone(this);
    	}else{
    		if($("#preinspectionForm").parsley().isValid()){
    			//验证必签签字是否已签
 	        //var signtools =$('#preinspectionForm').find(".signature-tool.sign-require"),
            var signtools =$('#preinspectionForm').find(".signature-tool.sign-require").parent().not(".hidden"),
 	        stl = signtools.length,
 	        sBlank = 0;
 	        /*for(var i=0; i<stl; i++){
 	        	var tstool = signtools.eq(i).next("div").find("a.btn-white"),
 	        	tsinput = tstool.siblings(".sign-data-input");
 	        	if(!tsinput.val() || tsinput.val().length < 312){
 	        		tstool.addClass("required-sign");
 	        		sBlank++;
 	        	}
 	        }*/
			$.each(signtools,function(i,e){
				var tstool = $(e).find("a.btn-white"),
					tsinput = tstool.siblings(".sign-data-input");
				if(!tsinput.val() || tsinput.val().length < 312){
					tstool.addClass("required-sign");
					sBlank++;
				}
			})
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
 	      //质量预验是否选择
 	        var qualityCheckFormRadios = $("#qualityCheckForm input:radio:checked");
 	        var materialCheckFormRadios = $("#materialCheckForm input:radio:checked");
 	        if(qualityCheckFormRadios.length<1||materialCheckFormRadios.length<1){
		        	$("body").cgetPopup({
		            	title: "提示信息",
		            	content: "请选择质量预验和资料预验内容!",
		            	accept: popClose,
		            	chide: true,
		            	icon: "fa-warning",
		            	newpop: 'new'
		            });
 	        	return false;
 	        }
 	        //通过验证，执行保存
 	        backDone();
    		}else{
            	//如果未通过验证, 则加载验证UI
            	$("#preinspectionForm").parsley().validate();
    		}
    	 }
    });
    
    var backDone=function(){
    	//推送
    	$("#flag").val("1");
    	var data=$('#preinspectionForm').serializeJson();
		var data2=$("#qualityCheckForm").getDTFormData();
		var data3=$("#materialCheckForm").getDTFormData();
		$.merge(data3,data2);
		data.preInspectionRecords = data3;
     	$.ajax({
             type: 'POST',
             url: 'preinspection/savePreinspection',
             contentType: "application/json;charset=UTF-8",
             data: JSON.stringify(data),
             success: function (data) {
             	var content = "数据保存成功！";
             	if(data === "false"){
             		content = "数据保存失败！";
             	}else  if(data === "already"){
	           		content = "已有终端操作此页面，请刷新查看！";
	           	}
             	if(data==="incompleteData"){
             		content="质量预验或资料预验未填写，数据保存失败！";
             	}
             	var myoptions = {
                     	title: "提示信息",
                     	content: content,
                     	accept: acdone,
                     	chide: true,
                     	icon: "fa-check-circle"
                 }
                 $("body").cgetPopup(myoptions);
             },
             error: function (jqXHR, textStatus, errorThrown) {
                 console.warn("预验收保存失败！");
             }
         });
     	//如果通过验证, 则移除验证UI
    	$("#preinspectionForm").parsley().reset();
    };
    
    
   	if(_inApk){
   		$("#checkListTab").text("信息区");
 		$("#qualityCheckTab").text("质量区");
 		$("#materialCheckTab").text("资料区");
   	 }else{
  		$("#checkListTab").text("预验收信息");
  		$("#qualityCheckTab").text("质量预验");
  		$("#materialCheckTab").text("资料预验");
   	 }
    
   	var sureClose=function(){
		var cwId=$("#piId").val();
		$.ajax({
			type:'post',
			url:'preinspection/saveSignNotice',
			contentType: "application/json;charset=UTF-8",
	        data: cwId,
	        success:function(data){
	        	console.info(data);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            console.warn("数据保存失败！");
	        }
		})
	}

    $('input[name="isBack"]').on("change",function(){
    	if($(this).val()=='1'){
    		$(".backRemark").removeClass("hidden");
    	}else{
    		$("#backRemark").val('');
    		$(".backRemark").addClass("hidden");
    	}
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->