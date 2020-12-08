<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton auditSavetoBtn">保存</a>
    	<a href="javascript:;" class="btn btn-info btn-sm m-l-5 pushButton entBtn" >推送</a>
    	<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<input type="hidden" id="proCntractType" name ="proCntractType"  value="${proCntractType}"/>
    	<form class="form-horizontal" id="settlementJonintlySignRightForm" action="" data-parsley-validate="true" >
    		<input type="hidden" id="projId" name = "projId" />
    		<input type="hidden"  id="sjsId" name = "sjsId" />
    		<input type="hidden"  id="projStatusId" name = "projStatusId" />
    		<input type="hidden" id="flag" name ="flag" />
    		<input type="hidden" id="contractType" name ="contractType" />
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
		            <textarea rows="2" class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes"></textarea>
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
		    
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="">分包单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"/>
		        </div>
		    </div>
		    <div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>市场发展部</h4></div>
		    <div class="form-group col-sm-12">
            	<label class="control-label" for="conContent">建设范围</label>
                <div> 
                	<textarea class="form-control field-editable allText budgetMember" name="conContent" id="conContent" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
		    </div>
		    
		    <div class="form-group col-md-6 col-sm-12 public">
		        <label class="control-label" for="">承包方式</label>
		    	<div>
		    		 <select class="form-control input-sm field-editable allText budgetMember" id="contractMethod"  name="contractMethod"  data-parsley-required="true">
		 		      	<option value="">--请选择--</option>
		 		      	<option value="0">预算包干</option>
		 		      	<option value="1">预结算制</option>
		 		      	<option value="2">其他</option>
		             </select>		        
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 public">
		        <label class="control-label" for="">材料提供</label>
		    	<div>
		    		 <select class="form-control input-sm field-editable allText budgetMember" id="materialProvide"  name="materialProvide"  data-parsley-required="true">
		 		      	<option value="">--请选择--</option>
		 		      	<option value="1">甲供主材</option>
		 		      	<option value="2">清包工</option>
		 		      	<option value="3">包工包料</option>
		 		      	<option value="4">其他</option>
		             </select>		        
		        </div>
		    </div>
		    
		    <div class="form-group col-lg-6 col-md-12 col-sm-6 public">
				<label class="control-label" for="budgetCost">预算价</label>
	            <div>
	            	<input type="text" class="form-control input-sm field-not-editable cost-i fixed-number text-right"  id="budgetCost" name="budgetCost" data-parsley-maxlength="16"  data-parsley-type="number" value=""/>
	            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 public">
				<label class="control-label" for="sendDeclarationCost">报审价</label>
	            <div>
	            	<input type="text" class="form-control input-sm field-not-editable cost-i fixed-number text-right"  id="sendDeclarationCost" name="sendDeclarationCost" data-parsley-maxlength="16"  data-parsley-type="number" value=""/>
	            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="endDeclarationCost">审定价</label>
	            <div>
	            	<input type="text" class="form-control input-sm field-not-editable cost-i fixed-number text-right"  id="endDeclarationCost" name="endDeclarationCost" data-parsley-maxlength="16"  data-parsley-type="number" value=""/>
	            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
			</div>
			
			 <div class="form-group col-sm-12">
            	<label class="control-label" for="budegterAuditOpinion">审核意见</label>
                <div> 
                	<textarea class="form-control field-editable  allText budgetMember" name="budegterAuditOpinion" id="budegterAuditOpinion" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
                </div>
		    </div>
			<div class="form-group col-md-6 col-sm-12 allSign budgetMember">
				<label class="control-label signature-tool sign-require" for="budegterSign">预算员</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="budegterSign" name="budegterSign" value="">
					<input type="hidden" id="budegterSign_postType" name="budegterSign_postType" value="${budgeterPost }">
					<img class="budegterSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-md-6">
		        <label class="control-label">审核日期</label>
		        <div>
		            <input type="text" class="form-control input-sm  field-editable datepicker-default allText budgetMember"  id="budegterSignDate" name="budegterSignDate"  />
		        </div>
		    </div>
    		<div class="form-group col-md-6 col-sm-12 civil allSign builder">
				<label class="control-label signature-tool sign-require civil" for="builderSign">现场代表</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="builderSign" name="builderSign" value="">
					<input type="hidden" id="builderSign_postType" name="builderSign_postType" value="${builder }">
					<img class="builderSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-md-6 col-sm-12 civil allSign budgetGroupLeader">
				<label class="control-label signature-tool sign-require civil" for="groupLeaderSign">预结算组长</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="groupLeaderSign" name="groupLeaderSign" value="">
					<input type="hidden" id="groupLeaderSign_postType" name="groupLeaderSign_postType" value="${budgeterLeaderPost }">
					<img class="groupLeaderSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-md-6 col-sm-12 clearboth allSign viceMinister">
				<label class="control-label signature-tool sign-require" for="viceMinisterSign">副部长</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="viceMinisterSign" name="viceMinisterSign" value="">
					<input type="hidden" id="viceMinisterSign_postType" name="viceMinisterSign_postType" value="${ viceMinisterPost}">
					<img class="viceMinisterSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		
    		<div class="clearboth supply"><h4><i class="fa fa-arrow-circle-o-right"></i>物资公司</h4></div>
    		
    		 <div class="form-group col-md-6 public supply">
		        <label class="control-label" for="">领料数</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable allText treasurer"  id="receiveNumber" name="receiveNumber" data-parsley-maxlength="30"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 public supply">
		        <label class="control-label" for="">核销数</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable allText treasurer"  id="writeOffNumber" name="writeOffNumber" data-parsley-maxlength="30"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 public supply">
		        <label class="control-label" for="">退（补）料数</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable allText treasurer"  id="supplementNumber" name="supplementNumber" data-parsley-maxlength="30"/>
		        </div>
		    </div>
    		<div class="form-group col-sm-12 supply">
            	<label class="control-label" for="materialOpinion">审核意见</label>
                <div> 
                	<textarea class="form-control field-editable allText treasurer" name="materialOpinion" id="materialOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
                </div>
		    </div>
    		
    		<div class="form-group col-md-6 col-sm-12 allSign treasurer supply">
				<label class="control-label signature-tool sign-require" for="materialFinanceSign">审核人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="materialFinanceSign" name="materialFinanceSign" value="">
					<input type="hidden" id="materialFinanceSign_postType" name="materialFinanceSign_postType" value="${treasurerPost }">
					<img class="materialFinanceSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-md-6 col-sm-12 allSign generalManager supply">
				<label class="control-label signature-tool sign-require" for="materialManagerSign">负责人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="materialManagerSign" name="materialManagerSign" value="">
					<input type="hidden" id="materialManagerSign_postType" name="materialManagerSign_postType" value="${generalPost}">
					<img class="materialManagerSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		
    		<div class="clearboth public"><h4><i class="fa fa-arrow-circle-o-right"></i>监理公司</h4></div>
    		
    		<div class="form-group col-sm-12 public">
            	<label class="control-label" for="suOpinion">审核意见</label>
                <div> 
                	<textarea class="form-control field-editable allText suJgj" name="suOpinion" id="suOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
                </div>
		    </div>
    		<div class="form-group col-md-6 col-sm-12 public allSign suJgj">
				<label class="control-label signature-tool sign-require public" for="sujgjSign">审核人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_7"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="sujgjSign" name="sujgjSign" value="">
					<input type="hidden" id="sujgjSign_postType" name="sujgjSign_postType" value="${ suJgjPost}">
					<img class="sujgjSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-md-6 col-sm-12 public allSign suCse">
				<label class="control-label signature-tool sign-require public" for="sucseSign">负责人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_8"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="sucseSign" name="sucseSign" value="">
					<input type="hidden" id="sucseSign_postType" name="sucseSign_postType" value="${suCesPost }">
					<img class="sucseSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		
    		 <div class="clearboth "><h4><i class="fa fa-arrow-circle-o-right"></i>安发公司</h4></div>
    		<div class="form-group col-sm-12 ">
            	<label class="control-label" for="dataCenterOpinion">审核意见</label>
                <div> 
                	<textarea class="form-control field-editable allText dataHandle" name="dataCenterOpinion" id="dataCenterOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
                </div>
		    </div>
    		<div class="form-group col-md-6 col-sm-12 allSign dataHandle ">
				<label class="control-label signature-tool sign-require" for="dataCenterSign">审核人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_9"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="dataCenterSign" name="dataCenterSign" value="">
					<input type="hidden" id="dataCenterSign_postType" name="dataCenterSign_postType" value="${ dataHandlePost}">
					<img class="dataCenterSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div> 
    		<div class="clearboth civil"><h4><i class="fa fa-arrow-circle-o-right"></i>财务</h4></div>
    		<div class="form-group col-sm-12 civil">
            	<label class="control-label" for="financeOpinion">审核意见</label>
                <div> 
                	<textarea class="form-control field-editable allText accounting" name="financeOpinion" id="financeOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
                </div>
		    </div>
    		<div class="form-group col-md-6 col-sm-12 civil allSign accounting">
				<label class="control-label signature-tool civil" for="financeSign">审核人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_10"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="financeSign" name="financeSign" value="">
					<input type="hidden" id="financeSign_postType" name="financeSign_postType" value="">
					<img class="financeSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		<div class="form-group col-md-6 col-sm-12 civil  allSign cashier">
				<label class="control-label signature-tool  civil" for="financeManageSign">负责人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_11"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="financeManageSign" name="financeManageSign" value="">
					<input type="hidden" id="financeManageSign_postType" name="financeManageSign_postType" value="">
					<img class="financeManageSign" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
    		</div>
    		
    		<div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>技术装备部</h4></div>
    		<div class="form-group col-sm-12">
            	<label class="control-label" for="archivesOpinion">资料是否齐全</label>
                <div> 
                	<textarea class="form-control field-editable allText equipmentTechnician" name="archivesOpinion" id="archivesOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
                </div>
		    </div>
    		<div class="form-group col-md-6 col-sm-12 allSign equipmentTechnician">
				<label class="control-label signature-tool sign-require" for="archivesSign">经办人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_8"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="archivesSign" name="archivesSign" value="">
					<input type="hidden" id="archivesSign_postType" name="archivesSign_postType" value="${ equipmentPost}">
					<img class="archivesSign" alt="" src="" style="height: 30px">
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
var queryBack=function(data){
	if($("#post").val().indexOf($("#equipmentTechnician").val())>=0){
		$(".entBtn").removeClass("hidden");
	}else{
		$(".entBtn").addClass("hidden");
	}
	
	if($("#proCntractType").val()==$("#contractType").val()){
		//民用的
		$(".public").addClass("hidden");
		$(".civil").removeClass("hidden");
	}else{
		$(".civil").addClass("hidden");
		$(".public").removeClass("hidden");
	}
	
	console.info("是否完成汇签---"+data.signStatus);
	if(data.signStatus=="1"){
		//已完成
		$(".applyBtn").addClass("hidden");
	}else{
		$(".applyBtn").removeClass("hidden");
	}
	
	if(data.nailMaterial=="1"){
		//甲供材
		$(".supply").removeClass("hidden");
	}else{
		$(".supply").addClass("hidden");
	}
	
	
	
}
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    //表单样式适应
    $("#settlementJonintlySignRightForm").toggleEditState(false).styleFit();
    $(".editbtn").addClass("hidden");
    trSData.settlementJonintlySignTable.t && trSData.settlementJonintlySignTable.t.cgetDetail('settlementJonintlySignRightForm','settlementJonintlySign/viewSettlementJonintlySign','',queryBack);
    
    //日期
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    //签字加载方式
    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4,#signBtn_5,#signBtn_6,#signBtn_7,#signBtn_8,#signBtn_9,#signBtn_10,#signBtn_11").handleSignature();
  	//点击确认
    $(".entBtn").off().on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	//表单验证
        if($("#settlementJonintlySignRightForm").parsley().isValid()){
        	
        	//验证必签签字是否已签
	        var signtools =$('#settlementJonintlySignRightForm').find(".signature-tool.sign-require").parent().not(".hidden"),
	        stl = signtools.length,
	        sBlank = 0;
        	console.info("lenth--"+signtools.length);
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
	        }else{
	        	sureSave();
	        }
        	
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#settlementJonintlySignRightForm").parsley().validate();
        }
         /* $("#gas_confirm_panel_box").cgetContent("gasConfirm/viewPage");   //重新加载页面
         $('#settlementJonintlySignRightForm')[0].reset();
		 $(".editbtn").addClass("hidden");
		 $("#settlementJonintlySignRightForm").toggleEditState(false); */
    });
    
  	//结算汇签单推送成功，调用资料归档的功能，如果相关步骤都做完，则自动进行资料归档
  	var callBackConnectConfirm = function(){
  		//json字符串
    	var sdata=$("#settlementJonintlySignRightForm").serializeJson();
  		var data={};
  		data.projId = sdata.projId;
  		data.fdId='';
  		data.corpId=sdata.corpId;
  		data.projNo = sdata.projNo;
  		data.projName = sdata.projName;
    	data.menuId = '110707';
    	$.ajax({
            type: 'POST',
            url: 'connectConfirm/auditSave',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
            	var content = "数据保存成功！";
            	if(data === "false"){
            		content = "数据保存失败！";
            	}else if(data === "true"){
            		$("#connectConfirmRightform").formReset();
            		$(".editbtn").addClass("hidden");
            		$("#connectConfirmTable").cgetData('','',true);  //列表重新加载
            	}else if(data === "notPass"){
            		content = "竣工图纸审核未完成,不可自动归档，请手动进行资料归档！";
            	}else if(data === "notSign"){
            		content = "结算汇签未完成,不可归档！";
            	}else if(data === "notAll"){
            		content = "竣工图审核及结算汇签未完成,不可归档！";
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
            	//取消遮罩
            	$(".infodetails").hideMask();
                console.warn("资料归档保存失败！");
            }
    	});
  	}
    //点击确认后保存
    var sureSave=function(){
    	$(".infodetails").loadMask("正在保存...", 1, 0.5);
    	$("#flag").val("1");
    	//json字符串
    	var data=$("#settlementJonintlySignRightForm").serializeJson();
    	console.info(data);
    	console.info("ssssssssssssss");
    	$.ajax({
            type: 'POST',
            url: 'settlementJonintlySign/saveSettlementJonintlySign',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            beforeSend: function () {
	              // 禁用按钮防止重复提交
	             $(".entBtn").attr({ disabled: "disabled" });
	        },
            complete: function () {
            	$(".entBtn").removeAttr("disabled");
	        },
            success: function (data) {
            	$(".infodetails").hideMask();
            	var content = "结算汇签保存成功！";
            	if(data === "false"){
            		content = "结算汇签保存失败！";
            		var myoptions = {
                        	title: "提示信息",
                        	content: content,
                        	accept: popClose,
                        	chide: true,
                        	newpop:'new',
                        	icon: "fa-check-circle"
                    }
                    $("body").cgetPopup(myoptions);
            	}else if(data === "true"){
            		//结算汇签单推送成功，调用资料归档的功能，如果相关步骤都做完，则自动进行资料归档
            		if($("#projStatusId").val()=='1035'){
            			callBackConnectConfirm();
            		}else{
            			var myoptions = {
                            	title: "提示信息",
                            	content: content,
                            	accept: popClose,
                            	chide: true,
                            	newpop:'new',
                            	icon: "fa-check-circle"
                        }
                        $("body").cgetPopup(myoptions);	
            		}
            		$("#settlementJonintlySignRightForm").formReset();
            		$("#settlementJonintlySignTable").cgetData(true);  //列表重新加载
            		$('.editbtn').addClass('hidden');
            		
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("结算汇签保存失败！");
            }
        });
    	//如果通过验证, 则移除验证UI
    	$("#settlementJonintlySignRightForm").parsley().reset();
    }
	
    //暂存
    $(".auditSavetoBtn").off().on("click",function(){
    	$("#flag").val("0");
    	if($("#settlementJonintlySignRightForm").parsley().isValid()){
    		$(".infodetails").loadMask("正在保存...", 1, 0.5);
    		//json字符串
        	var data=$("#settlementJonintlySignRightForm").serializeJson();
        	var sign=[];//页面显示的签字
        	$.each(data.sign,function(i,e){
        		if(!$("#"+e.fieldName).parent().parent().is(":hidden")){
        			sign = sign.concat(e);
        		}
        	});
        	data.sign = sign;
        	$.ajax({
                type: 'POST',
                url: 'settlementJonintlySign/saveSettlementJonintlySign',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                beforeSend: function () {
  	              // 禁用按钮防止重复提交
  	             	$(".auditSavetoBtn").attr({ disabled: "disabled" });
  	            },
                complete: function () {
                	$(".auditSavetoBtn").removeAttr("disabled");
  	            },
                success: function (data) {
                	$(".infodetails").hideMask();
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "true"){
                		$("#settlementJonintlySignRightForm").formReset();
                		$("#settlementJonintlySignTable").cgetData(true);  //列表重新加载
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
                    console.warn("结算汇签区记录保存失败！");
                    $(".infodetails").hideMask();
                }
            });
    		//如果通过验证, 则移除验证UI
        	$("#settlementJonintlySignRightForm").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#settlementJonintlySignRightForm").parsley().validate();
        }
    })
    
    
  //放弃
    $(".cancelBtn").on("click",function(){
    	$('#settlementJonintlySignRightForm').formReset();
    	$("#settlementJonintlySignTable").cgetData(true);                                  //列表重新加载
    	$(".clear-sign").click();                                               	       //签名清空
		$('#settlementJonintlySignRightForm').toggleEditState(false);                       //切换不可编辑状态
		$('.editbtn').addClass('hidden');										//维护按钮隐藏
    });
  	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->