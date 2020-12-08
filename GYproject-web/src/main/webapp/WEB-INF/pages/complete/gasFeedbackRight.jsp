<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn hidden">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSavetoBtn">保存</a>
    	<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 giveUpBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	
    	<form class="form-horizontal" id="gasConfirmRightform" action="" data-parsley-validate="true" >
    		<input type="hidden" id="projId" name = "projId" />
    		<input type="hidden" id="gaId" name = "gaId" />
    		<input type="hidden" id="applyCsrId" name = "applyCsrId" />
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
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="projName">大区</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="areaDes" name="areaDes"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="custName">建设单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 ">
		        <label class="control-label" for="custContact">业主代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 ">
		        <label class="control-label" for="custPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone"/>
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
		    
		   <!--  <div class="form-group col-md-6 ">
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
		    </div> -->
		    <div class="form-group col-md-6 isCharge">
				<label class="control-label" for="gasType">通气类型</label>
				<div>
					<label><input type="radio" id="charged1" name="gasType" value="1" class="field-not-editable" checked> 正常</label>
					<label><input type="radio"  id="charged2" name="gasType" value="0" class="field-not-editable"> 特殊</label>
				</div>
			</div>
		   <div class="form-group col-md-6">
		        <label class="control-label" for="gasApplyTime">申请时间</label>
		        <div>
		            <input type="text" class="form-control input-sm  datepicker-default field-not-editable"  id="gasApplyTime" name="gasApplyTime" data-parsley-required="true" />
		        </div>
		    </div>
		    <%-- <div class="form-group col-sm-12">
            	<label class="control-label" for="custOpinion">建设单位</label>
                <div> 
                	<textarea class="form-control field-not-editable" name="custOpinion" id="custOpinion" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool" for="custDeputy">建设单位</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white field-not-editable" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="custDeputy" name="custDeputy" value="">
					<img class="custDeputy" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-md-6 col-sm-12 clearboth">
				<label class="control-label signature-tool" for="cuPm">项目经理</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white field-not-editable" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="cuPmSign" name="cuPmSign" value="">
					<input type="hidden" id="cuPmSign_postType" name="cuPmSign_postType" value="${cuPm}">
					<img class="cuPmSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool" for="builderSign">甲方代表</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white field-not-editable" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="builderSign" name="builderSign" value="">
					<input type="hidden" id="builderSign_postType" name="builderSign_postType" value="${builder}">
					<img class="builderSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-sm-12">
            	<label class="control-label" for="managementOfficeOpinion">施工处意见</label>
                <div> 
                	<textarea class="form-control  field-not-editable" name="managementOfficeOpinion" id="managementOfficeOpinion" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool" for="managementOfficeOpinion">施工处长</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white field-not-editable" id="signBtn_4"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="managementOfficeChief" name="managementOfficeChief" value="">
					<input type="hidden" id="managementOfficeChief_postType" name="managementOfficeChief_postType" value="${chief}">
					<img class="managementOfficeChief" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-sm-12">
            	<label class="control-label" for="financeDept">财务处意见</label>
                <div> 
                	<textarea class="form-control  field-not-editable" name="financeDept" id="financeDept" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool" for="financeChief">财务处长</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white field-not-editable" id="signBtn_5"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="financeChief" name="financeChief" value="">
					<input type="hidden" id="financeChief_postType" name="financeChief_postType" value="${chief}">
					<img class="financeChief" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-sm-12">
            	<label class="control-label" for="businessManageDept">企管处意见</label>
                <div> 
                	<textarea class="form-control field-not-editable " name="businessManageDept" id="businessManageDept" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool" for="businessManageOhief">企管处长</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white field-not-editable" id="signBtn_6"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="businessManageOhief" name="businessManageOhief" value="">
					<input type="hidden" id="businessManageOhief_postType" name="businessManageOhief_postType" value="${chief}">
					<img class="businessManageOhief" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-sm-12">
            	<label class="control-label" for="projCom">工程公司</label>
                <div> 
                	<textarea class="form-control field-not-editable " name="projCom" id="projCom" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool" for="vicePresident">副总经理</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white field-not-editable" id="signBtn_7"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="vicePresident" name="vicePresident" value="">
					<input type="hidden" id="vicePresident_postType" name="vicePresident_postType" value="${vicePresident}">
					<img class="vicePresident" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div> --%>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="confirmGasTime">通气时间</label>
		        <div>
		            <input type="text" class="form-control input-sm  datepicker-default field-editable"  id="confirmGasTime" name="confirmGasTime" data-parsley-required="true" />
		        </div>
		    </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="gasDes">反馈信息</label>
		        <div>
		            <textarea rows="4" class="form-control input-sm field-editable"  id="gasDes" name="gasDes"></textarea>
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
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    trSData.gasApplyTable.t && trSData.gasApplyTable.t.cgetDetail('gasConfirmRightform','gasFeedback/gasApplyDetail','',tableCallBack);
    
    //日期
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    /**点击保存按钮时*/
    $('.auditSavetoBtn').on('click',function(){
        if($('#gasConfirmRightform').parsley().isValid()){
        	var data=$('#gasConfirmRightform').serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'gasFeedback/updateGasApply',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else{
                		$("#gasConfirmRightform input").val('');
                		$("#gasConfirmRightform textarea").val('');
                		$("#gasApplyTable").cgetData(true,tableCallBack);  //列表重新加载
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
                    console.warn("分包合同签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#gasConfirmRightform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#gasConfirmRightform").parsley().validate();
        }
        
    }); 
  	
	/**右侧维护区 放弃 按钮点击后*/
	 $(".giveUpBtn").off("click").on("click",function(){
		 $("#gasConfirmRightform input").val('');
		 	trSData.gasApplyTable.t.cgetDetail('gasConfirmRightform','gasFeedback/gasApplyDetail','',tableCallBack);
	    	$("#gasConfirmRightform").toggleEditState(false);
	    	$(".editbtn").addClass("hidden");
		   	selectTr["gasApplyTable"] = 0;
		   	$('tbody tr:eq(0)').select();
		   	//移除验证
		   	$("#gasConfirmRightform").parsley().reset();
			
	 });
  	
  	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->