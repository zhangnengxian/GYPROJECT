<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel-heading">
	<div class="panel-heading-btn">
		<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
		<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
		<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	</div>
	 <h4 class="panel-title">验收信息</h4>
</div>
<div class="panel-body" id="box">
	<div class="infodetails">
		<div class="toolBtn f-r p-b-10 editbtn">
			<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton saveBtn">保存</a>
			<a href="javascript:;" class="btn btn-info btn-sm m-l-5 saveButton jointPushButtn">推送</a>
			<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
			<a href="javascript:;" class="btn  btn-primary btn-sm m-l-5 hidden get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>
		</div>
		<div class="clearboth form-box">
			<input type="hidden" id="post" value="${post}">
			<input type="hidden" id="rectifyNoticeType"  name="rectifyNoticeType" value="${rectifyNoticeType}">
			<form class="form-horizontal" id="jointAcceptanceform" action="" data-parsley-validate="true">
				<input type="hidden" name="projId" id="projId"/>
				<input type="hidden" name="jaId" id="jaId" class="busOrderId"/>
				<input type="hidden" name="alarmProj" id="alarmProj"/>
				<input type="hidden" name="version"/>
				<div class="form-group col-md-6 ">
					<label class="control-label" for="projNo">工程编号</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" value=""/>
					</div>
				</div>
				<div class="form-group col-md-6 ">
					<label class="control-label" for="scNo">分合同号</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo" value=""/>
					</div>
				</div>

				<div class="form-group col-md-6 ">
					<label class="control-label" for="projName">工程名称</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value=""/>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="projAddr">工程地点</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="projectTypeDes">工程类型</label>
					<div>
						<input type="text" class="form-control field-not-editable"  id="projectTypeDes" name="projectTypeDes"  value=""/>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="cuName">施工单位</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" value=""/>
					</div>
				</div>
				<%--.联合验收时显示监理、设计代表、施工员、项目经理、现场代表等人员，方便联系，同时显示施工合同的开竣工日期--%>
				<div class="form-group col-md-6">
					<label class="control-label" for="builder">现场代表</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"  id="builder" name="builder" value=""/>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="suJgj">现场监理</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"  id="suJgj" name="suJgj" value=""/>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="cuPm">项目经理</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"  id="cuPm" name="cuPm" value=""/>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="managementQae">施工员</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"  id="managementQae" name="managementQae" value=""/>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="designer">设计员</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"  id="designer" name="designer" value=""/>
					</div>
				</div>
				<!-- <div class="form-group col-md-6">
					<label class="control-label">开工日期</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable datepicker-default"  id="startDate" name="startDate" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">竣工日期</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable datepicker-default"  id="completedDate" name="completedDate" />
					</div>
				</div> -->
					<div class="form-group col-md-6 clearboth">
					<label class="control-label">计划开工日期</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable datepicker-default"  id="scPlannedStartDate" name="scPlannedStartDate" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">实际开工日期</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable datepicker-default"  id="startDate" name="startDate" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">计划竣工日期</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable datepicker-default"  id="scPlannedEndDate" name="scPlannedEndDate" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">实际竣工日期</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable datepicker-default"  id="completedDate" name="completedDate" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label ">验收日期</label>
					<div>
						<input type="text" class="form-control noIdea input-sm field-editable datepicker-default allText technician signDate"  data-parsley-required="true" id="jaDate" name="jaDate"/>
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="control-label " for="jaClum">验收结论</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea allText technician"  id="jaClum" name="jaClum" data-parsley-maxlength="200"/>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">输配中心意见</label>
					<div>
						<input type="text" class="form-control field-editable  noIdea allText processTechnician" name ="transCompanyDeviceOpinion" id="transCompanyDeviceOpinion" data-parsley-maxlength="100"/>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">是否参与</label>
					<div>
						<label><input class="field-editable allText processTechnician" type="radio" name="whetherAcceptance" value="1"  checked = "checked"> 是</label>
						<label><input class="field-editable allText processTechnician" type="radio" name="whetherAcceptance" value="0" > 否</label>
					</div>
				</div>
				<div class="form-group col-md-6 allSign processTechnician">
					<label class="control-label signature-tool signature-required whetherAcceptance" for="transCompanySign">验收人</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_2"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="transCompanySign" name="transCompanySign" value="">
						<input type="hidden" class="signPost" id="transCompanySign_postType" name="transCompanySign_postType" value="${transCompanySign }" >
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label ">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText processTechnician signDate"  id="transCompanySignDate" name="transCompanySignDate" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="control-label">客户服务部意见</label>
					<div>
						<input type="text" class="form-control field-editable noIdea allText inspector" name ="custCenterDeviceOpinion" id="custCenterDeviceOpinion" data-parsley-maxlength="100"/>
					</div>
				</div>
				<div class="form-group col-md-6 allSign inspector">
					<label class="control-label signature-tool signature-required" for="custCenterSign">验收人</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_1"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="custCenterSign" name="custCenterSign" value="">
						<input type="hidden" class="signPost" id="custCenterSign_postType" name="custCenterSign_postType" value="${custCenterSign }" >
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText inspector signDate"  id="custCenterSignDate" name="custCenterSignDate" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="control-label">设计公司意见</label>
					<div>
						<input type="text" class="form-control field-editable noIdea allText designer" name ="pdUnitDeviceOpinion" id="pdUnitDeviceOpinion" data-parsley-maxlength="100"/>
					</div>
				</div>
				<div class="form-group col-md-6 allSign designer">
					<label class="control-label signature-tool signature-required" for="pdUnitSign" >验收人</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_4"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="pdUnitSign" name="pdUnitSign" value="">
						<input type="hidden" class="signPost" id="pdUnitSign_postType" name="pdUnitSign_postType" value="${duDeputy }" >
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText designer signDate"  id="pdUnitSignDate" name="pdUnitSignDate" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="control-label">监理公司意见</label>
					<div>
						<input type="text" class="form-control field-editable noIdea allText suJgj" name ="suNameDeviceOpinion" id="suNameDeviceOpinion" data-parsley-maxlength="100"/>
					</div>
				</div>
				<div class="form-group col-md-6 allSign suJgj">
					<label class="control-label signature-tool signature-required"  for="suNameSign">验收人</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_6"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="suNameSign" name="suNameSign" value="">
						<input type="hidden" class="signPost" id="suNameSign_postType" name="suNameSign_postType" value="${suFieldJgj }" >
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText suJgj signDate"  id="suNameSignDate" name="suNameSignDate" />
					</div>
				</div>
				<%-- <div class="form-group col-md-12">
					<label class="control-label">施工单位意见</label>
					<div>
						<input type="text" class="form-control field-editable noIdea allText construction" name ="cuNameDevice" id="cuNameDevice" data-parsley-maxlength="100"/>
					</div>
				</div>
				<div class="form-group col-md-6 allSign construction">
					<label class="control-label signature-tool sign-require" for="suNameSign">验收人</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_8"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="cuNameSign" name="cuNameSign" value="">
						<input type="hidden" class="signPost" id="cuNameSign_postType" name="cuNameSign_postType" value="${managementQae }" >
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label signature-tool">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText construction signDate"  id="cuNameSignDate" name="cuNameSignDate" />
					</div>
				</div>  --%>
				<div class="form-group col-md-12">
					<label class="control-label">工程技术部意见</label>
					<div>
						<input type="text" class="form-control field-editable noIdea allText builder" name ="fieldPrincipalDeviceOpinion" id="fieldPrincipalDeviceOpinion" data-parsley-maxlength="100"/>
					</div>
				</div>
				<div class="form-group col-md-6 allSign builder">
					<label class="control-label signature-tool signature-required" for="fieldPrincipalSign">验收人</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_5"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="fieldPrincipalSign" name="fieldPrincipalSign" value="">
						<input type="hidden" class="signPost" id="fieldPrincipalSign_postType" name="fieldPrincipalSign_postType" value="${cuSpdPrincipal }" >
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText builder signDate"  id="fieldPrincipalSignDate" name="fieldPrincipalSignDate" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="control-label">质量安全部结论</label>
					<div>
						<label><input class="field-editable allText technician" type="radio" name="marketDevDevice" value="1"  checked> 合格</label>
						<label><input class="field-editable allText technician" type="radio" name="marketDevDevice" value="0" > 整改</label>
						<label><input class="field-editable allText technician" type="radio" name="marketDevDevice" value="2" > 整改合格</label>
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="control-label">质量安全部意见</label>
					<div>
						<input type="text" class="form-control field-editable noIdea allText technician" name ="marketDevDeviceOpinion" id="marketDevDeviceOpinion" data-parsley-maxlength="100"/>
					</div>
				</div>
				<div class="form-group col-md-6 allSign technician">
					<label class="control-label signature-tool signature-required" for="marketDevSign">组织人</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_10"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="marketDevSign" name="marketDevSign" value="">
						<input type="hidden" class="signPost" id="marketDevSign_postType" name="marketDevSign_postType" value="${organizationMan }" >
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText technician signDate" id="marketDevSignDate" name="marketDevSignDate" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="control-label">备注</label>
					<div>
						<input type="text" class="form-control field-editable noIdea" name ="informCenterDeviceOpinion" id="informCenterDeviceOpinion" data-parsley-maxlength="100"/>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    //表单样式适应
    $("#jointAcceptanceform").toggleEditState(false).styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    $(".jointButton").removeClass("hidden");
    /* if($("#deptDivide").val()==$("#qualitySafetyGroup").val()){
		$(".pushButton").addClass("hidden");
		$(".jointButton").removeClass("hidden");
	}else{
		$(".pushButton").addClass("hidden");
		$(".jointButton").addClass("hidden");
	} */
    
	 //判断是否是质安部质量安全员
    var json=trSData.jointAcceptanceTable.json;
   if($("#post").val()==",56,"){
   	 if(json.projChanges.indexOf(3) >= 0 || json.projChanges.indexOf(0) >= 0 || json.projChanges == '' || json.projChanges == null){
			 $(".pushButton").addClass("hidden");
			 $(".jointPushButtn").addClass("hidden");
		 }else{
			 $(".pushButton").addClass("hidden");
			 $(".jointPushButtn").removeClass("hidden");
		 }
   }else{
   	$(".pushButton").addClass("hidden");
   	 $(".jointPushButtn").addClass("hidden");
   }


    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    trSData.t && trSData.t.cgetDetail('jointAcceptanceform','jointAcceptance/jointDetail','',callback);
    
    // 查询详述回调函数，不参与联合验收则将签字必签去掉验证
   var callback = function (data) {
    	var whetherAcceptance = data.whetherAcceptance;
    	if (whetherAcceptance == "0") {
    		$(".whetherAcceptance").removeClass("signature-required");
    	} else {
    		$(".whetherAcceptance").addClass("signature-required");
    	}
    }
    tableCallBack();
	//点击验收按钮触发事件
    $(".jointButton").on("click",function(){
    	$('.editbtn').removeClass('hidden');									//维护按钮隐藏
    	//根据职务过滤可编辑项
        getSignStatusByPostId($("#post").val(),"jointAcceptanceform");
        signDate();
        //$('#jointAcceptanceform').toggleEditState(true);                       //切换可编辑状态
        
    });
    //放弃
    $(".cancelBtn").on("click",function(){
        //$('#jointAcceptanceform').formReset();
        $("#jointAcceptanceTable").cgetData(true);                              //列表重新加载
        $(".clear-sign").click();                                               //签名清空
        $('#jointAcceptanceform').toggleEditState(false);                       //切换不可编辑状态
        $('.editbtn').addClass('hidden');										//维护按钮隐藏
    });

    var SaveCallBack = function(data){
        if(data === "true"){
            //表单不可编辑
            $("#jointAcceptanceform").toggleEditState(false);
            //按钮隐藏
            $(".editbtn").addClass("hidden");
            sureClose();
        }
    };
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
        //$('#jointAcceptanceform').cformSave('jointAcceptanceListTable',SaveCallBack);
    	if($("#jointAcceptanceform").parsley().isValid()){
    		//加遮罩
    		$(".infodetails").loadMask("正在保存...", 1, 0.5);
    		//json字符串
        	var data=$("#jointAcceptanceform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'jointAcceptance/saveJoint',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "true"){
                		SaveCallBack(data);
                		$('#jointAcceptanceTable').cgetData(true,tableCallBack);
                	}else if(data === "already"){
                   		content = "此信息已被修改，请刷新页面！";
                   	}
                	var myoptions = {
                        	title: "提示信息",
                        	content: content,
                        	accept: sureClose,
                        	chide: true,
                        	icon: "fa-check-circle"
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                    console.warn("联合验收保存失败！");
                }
            });
    		//如果通过验证, 则移除验证UI
        	$("#jointAcceptanceform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#jointAcceptanceform").parsley().validate();
        }
    });
    /**点击右侧维护区推送 按钮时*/
    $(".jointPushButtn").on("click",function(){
        //表单验证
      //  if($("#jointAcceptanceform").parsley().isValid()){
            //验证必签签字是否已签
            var signtools =$('#jointAcceptanceform').find(".signature-tool.signature-required"),
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
            
            //json字符串
            var data=$("#jointAcceptanceform").serializeJson();
            $.ajax({
                type: 'POST',
                url: 'jointAcceptance/entJoint',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                    var content = "数据保存成功！";
                    if(data === "false"){
                        content = "数据保存失败！";
                    }else if(data === "true"){
                        $("#jointAcceptanceform").formReset();
                        $("#jointAcceptanceTable").cgetData(true,tableCallBack);  //列表重新加载
                        $(".editbtn").addClass("hidden");
                    }
                    var myoptions = {
                        title: "提示信息",
                        content: content,
                        accept: popClose,
                        chide: true,
                        icon: "fa-check-circle",
                        newpop: 'new'
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("联合验收记录保存失败！");
                }
            });
            //如果通过验证, 则移除验证UI
      /*       $("#jointAcceptanceform").parsley().reset();
        }else{
            //如果未通过验证, 则加载验证UI
            $("#jointAcceptanceform").parsley().validate();
        } */
    });
    $(".fieldPrincipal-a").handleSignature();
    var sureClose=function(){
        var cwId=$("#jaId").val();
        $.ajax({
            type:'post',
            url:'jointAcceptance/saveSignNotice',
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
</script>
<!-- ================== END PAGE LEVEL JS ================== -->