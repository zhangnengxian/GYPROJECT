<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
	    <input type="hidden" name="gasCompany" id="gasCompany" value="${gasCompany}"/>
	   	<input type="hidden" name="builderPost" id="builderPost" value="${builder}"/>
	   	<input type="hidden" name="corpId" id="corpId" value="${corpId}"/>
	   	<input type="hidden" name="unitType" id="unitType" value="${unitType}"/>
	   	<input type="hidden" name="customerServiceCenter" id="customerServiceCenter" value="${customerServiceCenter}"/>
    	<form class="form-horizontal" id="planEstablishDetailform" action="">
		    <input type="hidden" name="cpId" id="cpId"/>   
		    <input type="hidden" name="projId" id="projId"/>
		    <input type="hidden" name="corpId" />
		    <input type="hidden" name="version" id="version"/>
		    <input type="hidden" name="suId" id="suId" />
		    <input type="hidden" name="cuId" id="cuId" />
		    <input type="hidden" id="builderId" name="builderId">
		    <input type="hidden" id="deptId" name="deptId">
		     <input type="hidden" id="deptDivide" name="deptDivide">
		    <input type="hidden" name="isinitialPayment" id="isinitialPayment" />
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" />
		        </div>
		    </div>
		    
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" />
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="">工程规模</label>
		        <div>
		            <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3"></textarea>
		        </div>
		    </div>
		   <div class="form-group col-md-6 ">
		        <label class="control-label" for="projectTypeDes">工程类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="contributionModeDes">出资方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 ">
		        <label class="control-label" for="deptName">业务部门</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth noUser">
		        <label class="control-label" for="custContact">用户联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact"/>
		        </div>
		    </div>
		   	<div class="form-group col-md-6 noUser">
		        <label class="control-label" for="custPhone">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="duName">设计单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="duName" name="duName"/>
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="duDesigner">设计员</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="duDesigner" name="duDesigner"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="projTimeLimit">工期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="projTimeLimit"  name="projTimeLimit" data-parsley-required="true">
		           <a href="javascript:;" class="btn btn-sm btn-default">天</a>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="projectjReport">立项报告</label>
		    	<div>
		            <label>
		              	<input type="radio" class="field-editable"  name="projectjReport" value="0" checked/>未上传
		            </label>
		            <label>
		              	<input type="radio" class="field-editable" name="projectjReport" value="1" />已上传
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="firstPartyProvide">甲方供材</label>
		        <div>
		          	<select  class=" form-control input-sm field-editable " name="firstPartyProvide">
		          		<option value="1">是</option>
		          		<option value="0" selected>否</option>
		          	</select>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth noUser">
		        <label class="control-label" for="">合同金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="contractAmount" name="contractAmount"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		    <div class="form-group col-md-6 noUser">
		        <label class="control-label" for="firstPayment">应收首付款</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="firstPayment" name="firstPayment"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		   	<div class="form-group col-md-6 noUser">
		        <label class="control-label" for="">实收首付款</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="downPayment" name="downPayment"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		   <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="">现场代表</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="builder" name="builder" data-parsley-required="true">
		           <a id="buPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择施工员"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="bulTel" name="bulTel" data-parsley-maxlength="13" />
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="">监理单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName"/>
		            <a id="suPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择监理单位"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">监理负责人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="suDirector" name="suDirector"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">负责人电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="suPhone" name="suPhone" data-parsley-maxlength="13"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 clearboth">
		        <label class="control-label" for="">施工单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" data-parsley-maxlength="100" data-parsley-required="true"/>
		            <a id="subUnit" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择监理单位"><i class="fa fa-search"></i></a>
		        </div>
		   </div>
		   <div class="form-group col-md-6">
		        <label class="control-label" for="">负责人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuLegalRepresent" name="cuLegalRepresent"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="">负责人电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuPhone" name="cuPhone" data-parsley-maxlength="20"/>
		            <!-- <a id="sjPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择现场监理师"><i class="fa fa-search"></i></a> -->
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="remark">备注</label>
		        <div>
		            <textarea rows="4" class="form-control input-sm field-editable"  id="remark" name="remark" data-parsley-maxlength="200"></textarea>
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
    $(".infodetails").hideMask();
    //表单样式适应
    $("#planEstablishDetailform").toggleEditState().styleFit();
  	//按钮隐藏
    $(".editbtn").addClass("hidden");
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
  	//查右侧工程详述
    trSData.t && trSData.t.cgetDetail('planEstablishDetailform','projectPlan/viewPlan','',saveBack); 
    
    //点击保存
    $(".saveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
		if($("#planEstablishDetailform").parsley().isValid()){
			if($("#isinitialPayment").val() == "false"){
	        	$("body").cgetPopup({
	            	title: "提示信息",
	            	content: '首付款未交齐，是否要继续？',
	            	accept: doSave,
	            	icon: "fa-check-circle"
	        	});
	    	}else{
	    		doSave();
	    	}

	        //如果通过验证, 则移除验证UI
	        $("#planEstablishDetailform").parsley().reset();
		}else{
           	//如果未通过验证, 则加载验证UI
           	$("#planEstablishDetailform").parsley().validate();
        }
    	
    	function doSave(){
    		//加遮罩
    		$(".infodetails").loadMask("正在保存...", 1, 0.5);
       		var data=$("#planEstablishDetailform").serializeJson();
       		//console.log(data);
       		$.ajax({
               type: 'POST',
               url: 'projectPlan/savePlan',
               contentType: "application/json;charset=UTF-8",
               data: JSON.stringify(data),
               success: function (data) {
            	   //取消遮罩
                   $(".infodetails").hideMask();	   
               	   var content = "数据保存成功！";
	               if(data === "false"){
	               		content = "数据保存失败！";
	               }else if(data === "true"){
	               		$(".saveBtn").addClass("hidden");
	               		$(".cancelBtn").addClass("hidden");
	               		$("#planEstablishDetailform").formReset();
	               		$('#planEstablishDetailform').toggleEditState(false);
	               		$("#planEstablishTable").cgetData();  //列表重新加载
	               }
               	   var myoptions = {
                       	title: "提示信息",
                       	content: content,
                       	accept: popClose,
                       	chide: true,
                       	newpop: 'new',
                       	icon: "fa-check-circle"
                   }
                   $("body").cgetPopup(myoptions);
               },
               error: function (jqXHR, textStatus, errorThrown) {
            	   $(".infodetails").hideMask();	   
                   console.warn("计划签订区记录保存失败！");
               }
           });
    	}
        
    }); 
    
    //放弃 按钮点击后
    $(".cancelBtn").on("click",function(){
    	 //清空表单
    	 $("#planEstablishDetailform input").val('');
    	 trSData.t.cgetDetail('planEstablishDetailform','projectPlan/viewPlan',''); 
    	 $("#planEstablishDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["planEstablishTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#planEstablishDetailform").parsley().reset();
    }); 	
  	//选中监理单位
    $("#suPop").off().on("click",function(){
    	var suUnitTypeId=$('#suUnitTypeId').val();
    	businessPartnersPopup({
    		'suId':'unitId',
    		'suName':'unitName',
    		'suDirector':'unitDirector',
    		'suPhone':'unitMobile'
    	},suUnitTypeId)
  	});
	//选中分包单位
    $("#subUnit").off().on("click",function(){
    	var cuUnitType=$('#gasCompany').val();
    	businessPartnersPopup({
    		'cuId':'unitId',
    		'cuName':'unitName',   
    		'cuLegalRepresent':'unitDirector',
    		'cuPhone':'unitMobile',
    	},cuUnitType)
  	});
  	
    //选择施工员
    $("#buPop").off().on("click",function(){
    	var postBul=$("#builderPost").val();
      	var corpId=$("#corpId").val(),unitType=$("#unitType").val(),deptId=$("#deptId").val();
      	console.info("#deptId==="+deptId);
      	if(deptId.indexOf('11010104')>=0){
      		deptId = "11010104,11010103";
      	}
    	/* manageStaffPopup({
    		'builder':'staffName',
    		'bulTel':'mobile',
    		'builderId':'staffId'
    	},postBul,corpId); */
    	staffPopup({
    		'builder':'staffName',
    		'bulTel':'mobile',
    		'builderId':'staffId'
    	},postBul,unitType,deptId)
    	
    });
    
 var  manageStaffPopup = function(param, post, corpId){
    	$("body").cgetPopup({
    		title: '人员选择',
    		nocache:true,
    	    content: '#popup/manageStaffPop?post=' + post + '&corpId=' + corpId,
    	    accept: function(){
    	    	if($("#staffTable tr.selected").length < 1){
    	    		if(_inApk){
    	    			navigator.notification.alert('请选择人员！', null, '提示', '确定');
    	    		}else{
    		    		$("body").cgetPopup({
    		    			title: '提示', 
    		    			content: "请选择人员！", 
    		    			newpop: 'second', 
    		    			accept: innerClose
    		    		});
    	    		}
    	    		return false;
    	    	}else{
    	    		$.each(param, function(k, v){
    	  		    	$('[id=' + k + ']').val(trSData.json[v]);
    		    	});
    	    	}
    	    }
    	});
    }
    
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->