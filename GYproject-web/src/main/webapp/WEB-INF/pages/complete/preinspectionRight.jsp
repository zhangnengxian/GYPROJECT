<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton temporarySaveBtn" >暂存</a>
    	<a href="javascript:;" class="btn btn-info  btn-sm m-l-5 pushButton entBtn" >保存</a>
        <a href="javascript:;" class="btn  btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="preInspectionDetailform" action="" data-parsley-validate="true">
       		<input type="hidden" id="projId" name="projId"/>
       		<input type="hidden" id="piId" name="piId"/>
       		<input type="hidden" name="flag" id="flag"/>
		    <div class="form-group  col-md-12 ">
		        <label class="control-label" for="">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cmoName">施工单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cmoName" name="cmoName" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="builder">甲方代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="builder" name="builder" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="">监理单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-lg-12 col-md-12 col-sm-6">
		        <label class="control-label" for="">竣工验收移交资料</label>
		    	<div>
		            <label>
		              	<input type="radio" name="sirTransfer" value="1" checked="checked"/> 已提交
		            </label>
		            <label>
		              	<input type="radio" name="sirTransfer" value="2" /> 未提交
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-lg-12 col-md-12 col-sm-6">
		        <label class="control-label" for="">竣工验收申请单</label>
		    	<div>
		            <label>
		              	<input type="radio" name="sirFinish" value="1" checked="checked"/> 已提交
		            </label>
		            <label>
		              	<input type="radio" name="sirFinish" value="2" /> 未提交
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-lg-12 col-md-12 col-sm-6">
		        <label class="control-label" for="">质量检查自检记录</label>
		    	<div>
		            <label>
		              	<input type="radio" name="sirQuality" value="1" checked="checked"/> 已提交
		            </label>
		            <label>
		              	<input type="radio" name="sirQuality" value="2" /> 未提交
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-lg-12 col-md-12 col-sm-6">
		        <label class="control-label" for="">资料检查自检记录</label>
		    	<div>
		            <label>
		              	<input type="radio" name="sirData" value="1" checked="checked"/> 已提交
		            </label>
		            <label>
		              	<input type="radio" name="sirData" value="2" /> 未提交
		            </label>
		        </div>
		    </div>
		   <div class="form-group  col-md-6">
		        <label class="control-label signature-tool sign-require" for="projManager">项目经理</label>
		       	<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" id="projManager" name="projManager" value="" class="sign-data-input">
					<img class="" alt="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="pmDate">申请日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default addclear" id="pmDate"  name="pmDate" data-parsley-maxlength="100" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		     	<label class="control-label" for="sirOpinion">验收意见</label>
		     	<div>
			     	<c:forEach var="enums" items="${inspectionOpinion}">
			     		<p class="m-t-4 m-b-5"><input type="checkbox" name="sirOpinionSec" data-text="${enums.message}" value="${enums.value }"/> ${enums.message}</p>
			     	</c:forEach>
		        </div>
		        <input type="hidden" class="sirOpinion" name="sirOpinion"/>
		    </div>
		    <div class="form-group  col-md-6">
		        <label class="control-label signature-tool sign-require" for="suFieldJgj">现场监理</label>
		        <div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" id="suFieldJgj" name="suFieldJgj" value="" class="sign-data-input">
					<img class="" alt="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
		    <div class="form-group  col-md-6">
		        <label class="control-label" for="receiptNo">通知单号</label>
		       <div>
		            <input type="text" class="form-control input-sm field-editable addclear"  id="receiptNo" name="receiptNo" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		     <div class="form-group  col-md-6">
		        <label class="control-label signature-tool sign-require" for="cmoDirector">甲方代表</label>
		        <div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" id="cmoDirector" name="cmoDirector" value="" class="sign-data-input">
					<img class="" alt="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="comDate">日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default addclear" id="comDate"  name="comDate" data-parsley-maxlength="100" value="">
		        </div>
		    </div>
		     <div class="form-group  col-md-6">
		        <label class="control-label signature-tool sign-require" for="suCes">总监理工程师</label>
		        <div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
					<input type="hidden" id="suCes" name="suCes" value="" class="sign-data-input">
					<img class="" alt="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cesDate">日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default addclear" id="cesDate"  name="cesDate" data-parsley-maxlength="100" value="">
		        </div>
		    </div>
		    <!--  <div class="form-group  col-md-6">
		        <label class="control-label signature-tool sign-require" for="areaManager">片区管理员</label>
		        <div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
					<input type="hidden" id="areaManager" name="areaManager" value="" class="sign-data-input">
					<img class="" alt="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="amDate">日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable  datepicker-default addclear" id="amDate"  name="amDate" data-parsley-maxlength="100" value="">
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
    //隐藏loading
    $(".infodetails").hideMask();
    //表单样式适应
    $("#preInspectionDetailform").toggleEditState().styleFit();
    changeAText();
    $('.editbtn').addClass('hidden');
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
  	//radio不可编辑
    $("input:radio").attr("disabled","disabled");
    $("input:checkbox").attr("disabled","disabled");
    
    trSData.t&&trSData.t.cgetDetail('preInspectionDetailform','preinspection/viewPreInspection','',callback);
    
    $("#signBtn_1,#signBtn_2,#signBtn_3,#signBtn_4,#signBtn_5").handleSignature(); 
    var getSirOpinion = function(){
    	var sirOpinionSec = $("input[name='sirOpinionSec']:checked");
    	var sirOpinion="";
    	sirOpinionSec.each(function(){
    		sirOpinion = sirOpinion +$(this).val()+",";
    	});
    	if(sirOpinion!==""){
    		sirOpinion = sirOpinion.substring(0,sirOpinion.length-1); 
    	}else{
    		sirOpinion = '-1';
    	}
    	return sirOpinion;
    }
    
    $(".entBtn").off().on("click",function(){
   		var sirOpinion = getSirOpinion();
       	//退回到自检
       	if(sirOpinion!=="-1"){
       		content = "验收意见不通过，确认将会退回，是否确认？";
        		var myoptions = {
                    	title: "提示信息",
                    	content: content,
                    	accept: backDone,
                    	icon: "fa-check-circle"
               }
               $("body").cgetPopup(myoptions);
       	}else{
       		//日期添加校验
       		$("#comDate").attr("data-parsley-required",true);
       		$("#pmDate").attr("data-parsley-required",true);
       		$("#cesDate").attr("data-parsley-required",true);
       		$("#amDate").attr("data-parsley-required",true);
       		if($("#preInspectionDetailform").parsley().isValid()){
       			//验证必签签字是否已签
    	        var signtools =$('#preInspectionDetailform').find(".signature-tool.sign-require"),
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
    	        //通过验证，执行保存
    	        backDone();
       		}else{
               	//如果未通过验证, 则加载验证UI
               	$("#preInspectionDetailform").parsley().validate();
       		}
       	}
    });
    
    var backDone=function(){
    	var sirOpinion = getSirOpinion();
    	$(".sirOpinion").val(sirOpinion);
    	//推送
    	var data=$('#preInspectionDetailform').serializeJson();
     	$.ajax({
             type: 'POST',
             url: 'preinspection/savePreinspection',
             contentType: "application/json;charset=UTF-8",
             data: JSON.stringify(data),
             beforeSend: function () {
	  	           	// 禁用按钮防止重复提交
	               	 $(".entBtn").attr({ disabled: "disabled" });
	  	       	 },
	  	         complete: function () {
             		//去掉禁用
             		 $(".entBtn").removeAttr("disabled");
              },
             success: function (data) {
             	var content = "数据保存成功！";
             	if(data === "false"){
             		content = "数据保存失败！";
             		alertInfo(content);
             	}else if(data === "true"){
             		var myoptions = {
                         	title: "提示信息",
                         	content: content,
                         	accept: acdone,
                         	chide: true,
                         	icon: "fa-check-circle"
                     }
                     $("body").cgetPopup(myoptions);
             	}
             },
             error: function (jqXHR, textStatus, errorThrown) {
                 console.warn("预验收保存失败！");
             }
         });
     	//如果通过验证, 则移除验证UI
    	$("#preInspectionDetailform").parsley().reset();
    }
    
    //暂存
    $(".temporarySaveBtn").off().on("click",function(){
    		//alert('1');
    	//if($("#preInspectionDetailform").parsley().isValid()){
    		$("#flag").val("0");
        	var data=$('#preInspectionDetailform').serializeJson();
         	$.ajax({
                 type: 'POST',
                 url: 'preinspection/savePreinspection',
                 contentType: "application/json;charset=UTF-8",
                 data: JSON.stringify(data),
                 beforeSend: function () {
  	  	           	// 禁用按钮防止重复提交
  	               	$(".temporarySaveBtn").attr({ disabled: "disabled" });
  	  	       	 },
  	  	         complete: function () {
                		//去掉禁用
                		$(".temporarySaveBtn").removeAttr("disabled");
                 },
                 success: function (data) {
                 	var content = "数据保存成功！";
                 	if(data === "false"){
                 		content = "数据保存失败！";
                 		alertInfo(content);
                 	}else if(data === "true"){
                 		var myoptions = {
                             	title: "提示信息",
                             	content: content,
                             	accept: acdone,
                             	chide: true,
                             	icon: "fa-check-circle"
                         }
                         $("body").cgetPopup(myoptions);
                 	}
                 },
                 error: function (jqXHR, textStatus, errorThrown) {
                     console.warn("预验收保存失败！");
                 }
             });
    	/* }
     	//如果通过验证, 则移除验证UI
    	$("#preInspectionDetailform").parsley().reset(); */
    });
    
    var acdone = function(){
    	$("#preInspectionTable").cgetData(true,preInspectionTableCallBack);		//列表重新加载
		/* $('.addclear').val('');													//日期清空
		$("#signBtn_1,#signBtn_2,#signBtn_3,#signBtn_4,#signBtn_5").resetSign();//签名清空
    	$("input:radio").attr("checked","checked");								//radio重置
    	$("input[name='sirOpinionSec']").attr("checked",false);					//checkbox重置
		$('.editbtn').addClass('hidden');										//维护按钮隐藏
		$("input:radio").attr("disabled","disabled");							//radio不可编辑
		$("input:checkbox").attr("disabled","disabled");						//checkbox不可编辑
		$('#preInspectionDetailform').toggleEditState(false);					//切换不可编辑状态 */
    }
    //放弃
    $(".cancelBtn").on("click",function(){
		/* $('.addclear').val('');													//日期清空
		$("#signBtn_1,#signBtn_2,#signBtn_3,#signBtn_4,#signBtn_5").resetSign();//签名清空
		$("input:radio").attr("checked","checked");								//radio重置
		$("input[name='sirOpinionSec']").attr("checked",false);					//checkbox重置
		$("input:radio").attr("disabled","disabled");							//radio不可编辑
		$("input:checkbox").attr("disabled","disabled");						//checkbox不可编辑
		$('#qualityCheckForm,#materialCheckForm,#checkListForm').toggleEditState(false);//切换不可编辑状态
		$('.editbtn').addClass('hidden');	 */									//维护按钮隐藏
		$("#preInspectionTable").cgetData();		//列表重新加载
		//如果通过验证, 则移除验证UI
		$("#preInspectionDetailform").parsley().validate();
		//表单样式适应
	    $("#preInspectionDetailform").toggleEditState().styleFit();
    });
 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->