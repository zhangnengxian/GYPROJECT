<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton auditSavetoBtn">暂存</a>
    	<a href="javascript:;" class="btn btn-info btn-sm m-l-5 pushButton entBtn" >保存</a>
    	<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	
    	<form class="form-horizontal" id="gasConfirmRightform" action="" data-parsley-validate="true" >
    		<input type="hidden" id="projId" name = "projId" />
    		<input type="hidden" id="gaId" name = "gaId" />
    		<input  type="hidden" id="flag" name ="flag" />
    		<!-- <input type="hidden" id="subFlag" name = "subFlag" /> 
    		<input type="hidden" id="businessOrderId" name = "businessOrderId" /> -->
       		<div class="form-group col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		            <textarea rows="4" class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes"></textarea>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="">分包单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">项目经理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuPm" name="cuPm"/>
		        </div>
		    </div>
		    
		     <div class="form-group col-md-6 ">
		        <label class="control-label" for="">甲方代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="builder" name="builder"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">协议价款</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable  fixed-number"  id="scAmount" name="scAmount"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">本次付款</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable "  id="payment" name="" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="payGasAmount">付款金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number"  id="payGasAmount" name="payGasAmount"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 isCharge">
				<label class="control-label" for="gasType">通气类型</label>
				<div>
					<label><input type="radio" id="charged1" name="gasType" value="1"  checked> 正常</label>
					<label><input type="radio"  id="charged2" name="gasType" value="0" > 特殊</label>
				</div>
			</div>
		   <div class="form-group col-md-6">
		        <label class="control-label" for="gasApplyTime">申请时间</label>
		        <div>
		            <input type="text" class="form-control input-sm  field-editable datepicker-default"  id="gasApplyTime" name="gasApplyTime" data-parsley-required="true" />
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
            	<label class="control-label" for="custOpinion">建设单位</label>
                <div> 
                	<textarea class="form-control field-editable" name="custOpinion" id="custOpinion" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool sign-require" for="custDeputy">建设单位</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="custDeputy" name="custDeputy" value="">
					<img class="custDeputy" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-md-6 col-sm-12 clearboth">
				<label class="control-label signature-tool sign-require" for="cuPm">项目经理</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="cuPmSign" name="cuPmSign" value="">
					<input type="hidden" id="cuPmSign_postType" name="cuPmSign_postType" value="${cuPm}">
					<img class="cuPmSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool sign-require" for="builderSign">甲方代表</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="builderSign" name="builderSign" value="">
					<input type="hidden" id="builderSign_postType" name="builderSign_postType" value="${builder}">
					<img class="builderSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-sm-12">
            	<label class="control-label" for="managementOfficeOpinion">施工处意见</label>
                <div> 
                	<textarea class="form-control field-editable" name="managementOfficeOpinion" id="managementOfficeOpinion" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool sign-require" for="managementOfficeOpinion">施工处长</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="managementOfficeChief" name="managementOfficeChief" value="">
					<input type="hidden" id="managementOfficeChief_postType" name="managementOfficeChief_postType" value="${chief}">
					<img class="managementOfficeChief" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-sm-12">
            	<label class="control-label" for="financeDept">财务处意见</label>
                <div> 
                	<textarea class="form-control field-editable " name="financeDept" id="financeDept" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool sign-require"  for="financeChief">财务处</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="financeChief" name="financeChief" value="">
					<input type="hidden" id="financeChief_postType" name="financeChief_postType" value="${chief}">
					<img class="financeChief" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-sm-12">
            	<label class="control-label" for="businessManageDept">企管处意见</label>
                <div> 
                	<textarea class="form-control field-editable " name="businessManageDept" id="businessManageDept" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool sign-require" for="businessManageOhief">企管处长</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="businessManageOhief" name="businessManageOhief" value="">
					<input type="hidden" id="businessManageOhief_postType" name="businessManageOhief_postType" value="${chief}">
					<img class="businessManageOhief" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-sm-12">
            	<label class="control-label" for="projCom">工程公司</label>
                <div> 
                	<textarea class="form-control field-editable " name="projCom" id="projCom" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool sign-require" for="vicePresident">副总经理</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_7"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="vicePresident" name="vicePresident" value="">
					<input type="hidden" id="vicePresident_postType" name="vicePresident_postType" value="${vicePresident}">
					<img class="vicePresident" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
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
    //表单样式适应
    $("#gasConfirmRightform").toggleEditState(false).styleFit();
    $(".editbtn").addClass("hidden");
    changeAText();
    trSData.gasConfirmTable.t && trSData.gasConfirmTable.t.cgetDetail('gasConfirmRightform','gasConfirm/jointDetail','',tableCallBack);
    
    //日期
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    //签字加载方式
    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4,#signBtn_5,#signBtn_6,#signBtn_7").handleSignature();
  	//点击确认
    $(".entBtn").off().on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	//表单验证
        if($("#gasConfirmRightform").parsley().isValid()){
        	//验证必签签字是否已签
	        var signtools =$('#gasConfirmRightform').find(".signature-tool.sign-require"),
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
        	var val=$("#gasConfirmRightform input[type='radio']:checked").val();
        	if(val=="0"){
        		var myoptions = {
                    	title: "提示信息",
                    	content: "是否标记该工程为特殊通气工程？否则请重新选择！",
                    	accept: sureSave,
                    	chide: false,
                    	icon: "fa-check-circle"
                }
                $("body").cgetPopup(myoptions);
        	}else{
        		sureSave();
        	}
        	
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#gasConfirmRightform").parsley().validate();
        }
         /* $("#gas_confirm_panel_box").cgetContent("gasConfirm/viewPage");   //重新加载页面
         $('#gasConfirmRightform')[0].reset();
		 $(".editbtn").addClass("hidden");
		 $("#gasConfirmRightform").toggleEditState(false); */
    });
    
    //点击确认后保存
    var sureSave=function(){
    	$("#flag").val("1");
    	//json字符串
    	var data=$("#gasConfirmRightform").serializeJson();
    	$.ajax({
            type: 'POST',
            url: 'gasConfirm/saveGasApply',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
            	var content = "通气申请成功！";
            	if(data === "false"){
            		content = "通气申请失败！";
            	}else if(data === "true"){
            		$("#gasConfirmRightform").formReset();
            		$("#gasConfirmTable").cgetData(true);  //列表重新加载
            		$('.editbtn').addClass('hidden');
            	}
            	var myoptions = {
                    	title: "提示信息",
                    	content: content,
                    	accept: popClose,
                    	chide: true,
                    	newpop:'new',
                    	icon: "fa-check-circle"
                }
                $("body").cgetPopup(myoptions);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("通气申请失败！");
            }
        });
    	//如果通过验证, 则移除验证UI
    	$("#gasConfirmRightform").parsley().reset();
    }
	
    //暂存
    $(".auditSavetoBtn").off().on("click",function(){
    	$("#flag").val("0");
    	if($("#gasConfirmRightform").parsley().isValid()){
    		
    		//json字符串
        	var data=$("#gasConfirmRightform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'gasConfirm/saveGasApply',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "true"){
                		$("#gasConfirmRightform").formReset();
                		$("#gasConfirmTable").cgetData(true);  //列表重新加载
                		$('.editbtn').addClass('hidden');	
                	}
                	var myoptions = {
                        	title: "提示信息",
                        	content: content,
                        	accept: popClose,
                        	chide: true,
                        	icon: "fa-check-circle"
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("通气申请区记录保存失败！");
                }
            });
    		//如果通过验证, 则移除验证UI
        	$("#gasConfirmRightform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#gasConfirmRightform").parsley().validate();
        }
    })
    
    
  //放弃
    $(".cancelBtn").on("click",function(){
    	$('#gasConfirmRightform').formReset();
    	$("#gasConfirmTable").cgetData(true);                                  //列表重新加载
    	$(".clear-sign").click();                                               //签名清空
		$('#gasConfirmRightform').toggleEditState(false);                       //切换不可编辑状态
		$('.editbtn').addClass('hidden');										//维护按钮隐藏
    });
  	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->