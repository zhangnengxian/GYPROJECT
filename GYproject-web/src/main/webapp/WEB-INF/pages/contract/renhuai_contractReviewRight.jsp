<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel-heading">
	<div class="panel-heading-btn">
		<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
		<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
		<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	</div>
	 <h4 class="panel-title">合同评审</h4>
</div>
<div class="panel-body" id="box">
	<div class="infodetails">
		<div class="toolBtn f-r p-b-10 editbtn">
			<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton saveBtn">保存</a>
			<a href="javascript:;" class="btn btn-info btn-sm m-l-5 saveButton signPushButtn">推送</a>
			<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
			<a href="javascript:;" class="btn  btn-primary btn-sm m-l-5 hidden get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>
		</div>
		<div class="clearboth form-box">
			<input type="hidden" id="post" value="${loginInfo.post}">
			<form class="form-horizontal" id="contractReviewform" action="" data-parsley-validate="true">
				<input type="hidden" name="projId" id="projId"/>  <!-- 工程ID -->
				<input type=hidden  id="deptId" value="${loginInfo.deptId}">  <!-- 部门ID -->
				<input type="hidden" name="crId" id="crId" class="busOrderId"/>  <!-- 合同评审主键ID -->
				<input type="hidden" name="pushTime" id="pushTime"/> <!-- 推送时间 -->
				<input type="hidden" name="delStatus" id="delStatus"/>  <!-- 删除状态 -->
				<input type="hidden" name="delPersonal" id="delPersonal"/>  <!-- 删除人 -->
			    <input type="hidden" name="sysDate" id="sysDate"  value = "${sysDate}"/>   <!-- 当前数据库时间 -->
				<input type="hidden" name="version"/>  <!--版本号  -->
			    <input type="hidden" name="pushStatus" id = "pushStatus"/>  <!--推送标识符  -->
			    <input type="hidden" name="operatorId" id = "operatorId"/>  <!--经办人ID  -->
			    <input type="hidden" name="operator" id = "operator"/>  <!--经办人  -->
				<div class="form-group col-md-6 ">
					<label class="control-label" for="projNo">工程编号</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" value=""/>
					</div>
				</div>
				<div class="form-group col-md-6 ">
					<label class="control-label" for="scNo">合同号</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"  id="conNo" name="conNo" value=""/>
					</div>
				</div>

				<div class="form-group col-md-6 ">
					<label class="control-label" for="projName">工程名称</label>
					<div>
						<input type="text" class="form-control input-sm field-editable allText market"  id="projName" name="projName" value="" data-parsley-required="true"/>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="projAddr">工程地点</label>
					<div>
						<input type="text" class="form-control input-sm field-editable allText market"  id="projAddr" name="projAddr" value="" data-parsley-required="true"/>
					</div>
				</div>
				 <div class="form-group col-md-6">
	            <label class="control-label" for="contractType">合同类型</label>
            	<div>
               	   <select class="form-control input-sm field-editable allText market" id="contractType"  name="contractType"   data-parsley-required="true">
	               <option>==请选择工程类型==</option>
	               <option value  = "1">买卖合同</option>
	                <option value  = "2">工程合同</option>
	                <option value  = "3">经济合同</option>
	                 <option value  = "4">租赁合同</option>
	                 <option value  = "5">劳务合同</option>
	                   <option value  = "6">服务合同</option>
	                   <option value  = "7">运输合同</option>
	                    <option value  = "8">承包合同</option>
	                    <option value  = "9">其他合同</option>
	              </select>
	            </div>
			</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="projectTypeDes">工程类型</label>
					<div>
						<input type="text" class="form-control field-not-editable"  id="projectTypeDes" name="projectTypeDes"  value=""/>
					</div>
				</div>
			 <div class="form-group col-md-12">
		     	<label class="control-label " for="contractSummary">合同概述</label>
		     	<div> 
		        	<textarea class="form-control field-editable allText market " name="contractSummary" id="contractSummary" placeholder="请对合同进行概述" rows="4" cols="" data-parsley-maxlength="1000" ></textarea>
		    </div>
		     </div>
		      <div class="form-group col-md-12">
		     	<label class="control-label " for="contractOutline">合同概要</label>
		     	<div> 
		        	<textarea class="form-control field-editable allText market " name="contractOutline" id="contractOutline" placeholder="请对合同进行概要" rows="4" cols="" data-parsley-maxlength="1000" ></textarea>
		     </div>
		     </div>
		     <div class="form-group col-md-6 allSign market">
					<label class="control-label signature-tool sign-required" for="operatorSign">经办人</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_1"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="operatorSign" name="operatorSign" value="">
						<input type="hidden" class="signPost" id="operatorSign_postType" name="operatorSign_postType" value="${custCenterSign }" >  <!-- 营销员职务 -->
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
			    <div class="form-group col-md-6">
				<label class="control-label" for = "operatorTime">经办时间</label>
				  <div>
					<input type="text" class="form-control noIdea input-sm field-editable datepicker-default allText market"  id="operatorTime" name="operatorTime"/>
				 </div>
				</div>
			 <div class="form-group col-md-12">
					<label class="control-label" for = "operatorDeptOpinion">经办部门意见</label>
					<div>
					    <textarea class="form-control field-editable allText viceMinister " name="operatorDeptOpinion" id="operatorDeptOpinion" placeholder="经办部门意见" rows="4" cols="" data-parsley-maxlength="1000" ></textarea>
					</div>
				</div>
				<div class="form-group col-md-6 allSign viceMinister">
					<label class="control-label signature-tool sign-required" for="operatorDeptOpertor">经办部门负责人</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_2"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="operatorDeptOpertor" name="operatorDeptOpertor" value="">
						<input type="hidden" class="signPost" id="operatorDeptOpertor_postType" name="operatorDeptOpertor_postType" value="${viceMinister }" >   <!-- 部长助理 -->
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for = "operatorDeptOptime">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText viceMinister signDate"  id="operatorDeptOptime" name="operatorDeptOptime" />
					</div>
				</div>	
			 <div class="form-group col-md-12">
					<label class="control-label" for = "financialDeptOpinon">财务部门意见</label>
					<div>
					    <textarea class="form-control field-editable allText treasurer " name="financialDeptOpinon" id="financialDeptOpinon" placeholder="财务部门意见" rows="4" cols="" data-parsley-maxlength="1000" ></textarea>
					</div>
				</div> 
				 <div class="form-group col-md-6 allSign treasurer">
					<label class="control-label signature-tool sign-required" for="financialDeptSign">财务部负责人</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_3"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="financialDeptSign" name="financialDeptSign" value="">
						<input type="hidden" class="signPost" id="financialDeptSign_postType" name="financialDeptSign_postType" value="${treasurer }" >  <!-- 财务员职务 -->
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label " for = "financialDeptTime">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText treasurer signDate"  id="financialDeptTime" name="financialDeptTime" />
					</div>
				</div> 
				<div class="form-group col-md-12">
					<label class="control-label" for = "leaderDivisionOpinion">分管领导意见</label>
					<div>
					    <textarea class="form-control field-editable allText minister " name="leaderDivisionOpinion" id="leaderDivisionOpinion" placeholder="分管领导意见" rows="4" cols="" data-parsley-maxlength="1000" ></textarea>
					</div>
				</div> 
				<div class="form-group col-md-6 allSign minister">
					<label class="control-label signature-tool " for="leaderDivisionSign">分管领导</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_4"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="leaderDivisionSign" name="leaderDivisionSign" value="">
						<input type="hidden" class="signPost" id="leaderDivisionSign_postType" name="leaderDivisionSign_postType" value="${minister }" > <!-- 部长职务 -->
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for = "leaderDivisionTime">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText  minister signDate"  id="leaderDivisionTime" name="leaderDivisionTime" />
					</div>
				</div>
			   <div class="form-group col-md-12">
					<label class="control-label " for = "financialCfoOpinion">财务总监意见</label>
					<div>
					    <textarea class="form-control field-editable allText suCse " name="financialCfoOpinion" id="financialCfoOpinion" placeholder="财务总监意见" rows="4" cols="" data-parsley-maxlength="1000" ></textarea>
					</div>
				</div> 
				<div class="form-group col-md-6 allSign suCse">
					<label class="control-label signature-tool" for="financialCfoSign">财务总监</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_5"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="financialCfoSign" name="financialCfoSign" value="">
						<input type="hidden" class="signPost" id="financialCfoSign_postType" name="financialCfoSign_postType" value="${suCse }" >  <!-- 总监职务 -->
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for = "financialCfoTime">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText  suCse signDate"  id="financialCfoTime" name="financialCfoTime" />
					</div>
				</div>
				
				<div class="form-group col-md-12">
					<label class="control-label" for = "generalManagerOpintion">副总经理意见</label>
					<div>
					    <textarea class="form-control field-editable allText viceGeneralManager " name="generalManagerOpintion" id="generalManagerOpintion" placeholder="副总经理意见" rows="4" cols="" data-parsley-maxlength="1000" ></textarea>
					</div>
				</div> 
				<div class="form-group col-md-6 allSign viceGeneralManager">
					<label class="control-label signature-tool" for="generalManagerSign">副总经理</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_6"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="generalManagerSign" name="generalManagerSign" value="">
						<input type="hidden" class="signPost" id="generalManagerSign_postType" name="generalManagerSign_postType" value="${viceGeneralManager }" >   <!-- 副总经理职务 -->
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for = "generalManagerTime">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText  viceGeneralManager signDate"  id="generalManagerTime" name="generalManagerTime" />
					</div>
				</div>
				
				
				<div class="form-group col-md-12">
					<label class="control-label" for = "directManagerOperion">总经理理意见</label>
					<div>
					    <textarea class="form-control field-editable allText generalManager " name="directManagerOperion" id="directManagerOperion" placeholder="总经理意见" rows="4" cols="" data-parsley-maxlength="1000" ></textarea>
					</div>
				</div> 
				<div class="form-group col-md-6 allSign generalManager">
					<label class="control-label signature-tool sign-required" for="directManagerSign">总经理</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_7"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="directManagerSign" name="directManagerSign" value="">
						<input type="hidden" class="signPost" id="directManagerSign_postType" name="directManagerSign_postType" value="${generalManager }" > <!-- 总经理职务 -->
						<img class="fieldPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for = "directManagerTime">签字日期</label>
					<div>
						<input type="text" class="form-control input-sm field-editable noIdea datepicker-default allText  generalManager signDate"  id="directManagerTime" name="directManagerTime" />
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
    $("#contractReviewform").toggleEditState(false).styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    $(".signButton").removeClass("hidden");
    /* 签字时间插件 */
    $('.datepicker-default').datepicker({  
        todayHighlight: true
    });
   trSData.t && trSData.t.cgetDetail('contractReviewform','contractReview/contractReviewDetail',"");
    tableCallBack();
	//点击签订按钮触发事件
	 var json=trSData.contractReviewTable.json;
    $(".signButton").on("click",function(){
		if ($("#deptId").val().indexOf("110502")!=-1){//客服部可以推送
            $(".signPushButtn").removeClass("hidden")
		}else{
            $(".signPushButtn").addClass("hidden")
		}
        
    	$('.editbtn').removeClass('hidden');									//维护按钮隐藏
    	//根据职务过滤可编辑项
        getSignStatusByPostId($("#post").val(),"contractReviewform");
        signDate();
    });
    //放弃
      $(".cancelBtn").on("click",function(){
        $("#contractReviewTable").cgetData(true);                              //列表重新加载
        $(".clear-sign").click();                                               //签名清空
        $('#contractReviewform').toggleEditState(false);                       //切换不可编辑状态
        $('.editbtn').addClass('hidden');										//维护按钮隐藏
    });

    var SaveCallBack = function(data){
        if(data === "true"){
            //表单不可编辑
            $("#contractReviewform").toggleEditState(false);
            //按钮隐藏
            $(".editbtn").addClass("hidden");
            sureClose();
        }
    };
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
    	if($("#contractReviewform").parsley().isValid()){
    		//加遮罩
    		$(".infodetails").loadMask("正在保存...", 1, 0.5);
    		//json字符串
    		$("#pushStatus").val(0);  //推送标识符，0表示未推送，只是保存
        	var data=$("#contractReviewform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'contractReview/saveContractReview',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	//取消遮罩
                	var content = "数据保存失败！";
                	 if(data === "true"){
                		SaveCallBack(data);
                		$('#contractReviewTable').cgetData(true,tableCallBack);
                		var content = "数据保存成功！";
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
                	$(".infodetails").hideMask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	//取消遮罩
                	$(".infodetails").hideMask();
                    console.warn("合同评审保存失败！");
                    // 弹窗错误提示信息
                    errorPrompt(jqXHR); 
                }
            });
    		//如果通过验证, 则移除验证UI
        	$("#contractReviewform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#contractReviewform").parsley().validate();
        }
    });
    /**点击右侧维护区推送 按钮时*/
    $(".signPushButtn").on("click",function(){
            var signtools =$('#contractReviewform').find(".signature-tool.sign-required").parent().not(".hidden");
            var stl = signtools.length;
            var  sBlank = 0;
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
        	$(".infodetails").loadMask("正在保存...", 1, 0.5);
            //json字符串
            $("#pushStatus").val(1); //推送标识符，1表示推送，即流转到下一个状态
            var data=$("#contractReviewform").serializeJson();
            $.ajax({
                type: 'POST',
                url: 'contractReview/saveContractReview',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                    var content = "数据保存成功！";
                    if(data === "false"){
                        content = "数据保存失败！";
                    }else if(data === "true"){
                        $("#contractReviewform").formReset();
                        $("#contractReviewTable").cgetData(true,tableCallBack);  //列表重新加载
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
                    $(".infodetails").hideMask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	$(".infodetails").hideMask();
                    console.warn("合同评审记录推送失败！");
                    // 错误弹窗提示信息
                    errorPrompt(jqXHR);
                }
            });
    });
    $(".fieldPrincipal-a").handleSignature();
    var sureClose=function(){
        var cwId=$("#crId").val();
      /*   $.ajax({
            type:'post',
            url:'contractReview/saveSignNotice',
            contentType: "application/json;charset=UTF-8",
            data: cwId,
            success:function(data){
                console.info(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("数据保存失败！");
            }
        }) */
    } 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->