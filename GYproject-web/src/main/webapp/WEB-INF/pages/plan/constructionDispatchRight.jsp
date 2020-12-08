<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<input type="hidden" name="unitType" id="unitType" value="${unitType}"/>
    	<input type="hidden" name="cuPm" id="cuPm" value="${cuPm}"/>
   		<input type="hidden" name="managementQae" id="managementQae" value="${managementQae}"/>
   		<input type="hidden" name="managementWacf" id="managementWacf" value="${managementWacf}"/>
   		<input type="hidden" name="documenter" id="documenter" value="${documenter}"/>
   		<input type="hidden" name="saftyOfficer" id="saftyOfficer" value="${saftyOfficer}"/>
   		<input type="hidden" name="technician" id="technician" value="${technician}"/>
   		<input type="hidden" name="testLeader" id="testLeader" value="${testLeader}"/>
   		<input type="hidden" name="welder" id="welder" value="${welder}"/>
   		<input type="hidden" name="buildPost" id="buildPost" "/>
    	<form class="form-horizontal" id="planEstablishDetailform" action="">
		    <input type="hidden" name="cpId" id="cpId"/>
		    <input type="hidden" name="menuId" id="menuId"/>
		    <input type="hidden" name="projId" id="projId"/>
		    <input type="hidden"  id="corpId" name="corpId" />
		    <input type="hidden" name="cpArriveDate"/>  
		    <input type="hidden" name="cpDocumentDeptid"/>  
		    <input type="hidden" name="cpDocumentDeptName"/>  
		    <input type="hidden" name="cpDocumenterId"/>  
		    <input type="hidden" name="cpDocumenter"/> 
		    <%--<input type="hidden" name="remark"/>--%>
		    <input type="hidden" name="contractAmount"/> 
		    <input type="hidden" name="firstPayment"/>  
		    <input type="hidden" name="downPayment"/>  
		    <input type="hidden" name="isinitialPayment" id="isinitialPayment"/>
		    <input type="hidden" name="version"/>
		    <input type="hidden" name="suId" id="suId"/>
		    <input type="hidden" name="builder"/>
			<input type="hidden" name="builderId"/>
			<input type="hidden" name="bulTel"/>
		    <input type="hidden" name="cuId" id="cuId" />
		    <input type="hidden" name="cuPmId" id="cuPmId" />
		    <input type="hidden" name="suCseId" id="suCseId"/>
		    <input type="hidden" name="suJgjId" id="suJgjId"/>
		    <input type="hidden" name="managementQaeId" id="managementQaeId"/>
		    <input type="hidden" name="managementWacfId" id="managementWacfId"/>
		    <input type="hidden" name="documenterId" id="documenterId"/>
		    <input type="hidden" name="saftyOfficerId" id="saftyOfficerId"/>
		    <input type="hidden" name="technicianId" id="technicianId"/>
		    <input type="hidden" name="testLeaderId" id="testLeaderId"/>
			<input type="hidden" name="welderId" id  ="welderId"/>
		    <input type="hidden" name="isPrint" id="isPrint"/>
		    <input type="hidden" name="suIsPrint" id="suIsPrint"/>
		    <input type="hidden" name="cuIsDispatch" id="cuIsDispatch"/>
		    <input type="hidden" name="suIsDispatch" id="suIsDispatch"/>
		    <input type="hidden" name="firstPartyProvide" id="firstPartyProvide"/>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" />
		        </div>
		    </div>

		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" />
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="corpName">燃气公司</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName" />
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
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		            <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200"></textarea>
		        </div>
		    </div>
		   <!--  <div class="form-group col-md-6">
		        <label class="control-label" for="plannedStartDate">开工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default timestamp" id="plannedStartDate"  name="plannedStartDate" >
		        </div>
		    </div> -->
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projTimeLimit">工期</label>
		        <div>
		        	<input type="text" class=" form-control input-sm field-not-editable " id="projTimeLimit"  name="projTimeLimit">
		        	<a href="javascript:;" class="btn btn-sm btn-default">天</a>
		        </div>
		    </div>
			<div class="form-group col-md-12 hidden">
				<label class="control-label" for="remark">备注</label>
				<div>
					<textarea rows="2" class="form-control input-sm field-not-editable" id="remark" name="remark" data-parsley-maxlength="500"></textarea>
				</div>
			</div>
			<div class="form-group col-md-12 ">
				<label class="control-label" for="viewRemark">备注</label>
				<div>
					<textarea rows="2" class="form-control input-sm field-not-editable" id="viewRemark" name="viewRemark"></textarea>
				</div>
			</div>
		    <div class="form-group col-md-12 hidden">
		        <label class="control-label" for="suName">监理单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable" id="suName" name="suName" data-parsley-maxlength="100"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 hidden">
		        <label class="control-label" for="suDirector">监理负责人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable" id="suDirector" name="suDirector"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 hidden">
		        <label class="control-label" for="suPhone">负责人电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable" id="suPhone" name="suPhone" />
		        </div>
		    </div>
		     <div class="form-group col-md-6 hidden">
		        <label class="control-label" for="suCse">总监理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable" id="suCse" name="suCse" />
		            <a id="suCsePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择总监理师"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 hidden">
		        <label class="control-label" for="suCsePhone">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="suCsePhone" name="suCsePhone" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 hidden">
		        <label class="control-label" for="suJgj">现场监理师</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable" id="suJgj" name="suJgj" />
		            <a id="suPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择现场监理师"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		     <div class="form-group col-md-6 hidden">
		        <label class="control-label" for="suJgjPhone">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="suJgjPhone" name="suJgjPhone" />
		        </div>
		    </div>
		    <div class="form-group col-md-12">
				<label class="control-label" for="cuName">施工单位</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="cuName" name="cuName" />
				</div>
			</div>
			 <div class="form-group col-md-6">
		        <label class="control-label" for="cuLegalRepresent">负责人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuLegalRepresent" name="cuLegalRepresent"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="">负责人电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuPhone" name="cuPhone" data-parsley-maxlength="13"/>
		        </div>
		    </div>
			<div class="form-group col-md-6 ">
				<label class="control-label" for="cuPm">项目经理</label>
				<div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="cuPm" name="cuPm" data-parsley-required="true">
		           <a id="cuPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择项目负责人"><i class="fa fa-search"></i></a>
		        </div>
			</div>
			 <div class="form-group col-md-6">
		        <label class="control-label" for="cuPmTel">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuPmTel" name="cuPmTel" data-parsley-maxlength="13" />
		        </div>
		    </div>
			<div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="managementQae">施工员</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="mQae" name="managementQae" data-parsley-required="true">
		           <a id="mqPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择施工员"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="qaeTel">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="qaeTel" name="qaeTel" data-parsley-maxlength="13"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="mWacf">材料员</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="mWacf"  name="managementWacf">
		           <a id="mwPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择材料员"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="wacfTel">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="wacfTel" name="wacfTel" data-parsley-maxlength="13"/>
		        </div>
		    </div>
		      <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="dcter">资料员</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="dcter"  name="documenter" data-parsley-required="true">
		           <a id="dmPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择资料员"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="documenterTel">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="dcterTel" name="dcterTel" data-parsley-maxlength="13"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="sOfficer">安全员</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="sOfficer"  name="saftyOfficer" data-parsley-required="true">
		           <a id="soPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择安全员"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="saftyTel">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="saftyTel" name="saftyTel" data-parsley-maxlength="13"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="tnician">质检员</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="tnician" name="technician" data-parsley-required="true"/>
		            <a id="tcPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择质检员"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="technicianTel">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="technicianTel" name="technicianTel" data-parsley-maxlength="13"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12">
			<label class="control-label" for="testLeader">班组长</label>
				<div>
			           <input type="text" class=" form-control input-sm field-not-editable " id="testLeader1" name="testLeader" data-parsley-maxlength="200">
			           <a id="testLeaderPop" class=" btn btn-default btn-sm m-l-10 " title="选择班组长"><i class="fa fa-search"></i></a>
			    </div>
			</div>
		    <div class="form-group col-md-12 col-sm-12">
			<label class="control-label" for="welder">焊工</label>
				<div>
			           <input type="text" class=" form-control input-sm field-not-editable " id="welder1" name="welder"  data-parsley-maxlength="200">
			           <a id="welderPop" class=" btn btn-default btn-sm m-l-10 " title="选择焊工"><i class="fa fa-search"></i></a>
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
    trSData.t && trSData.t.cgetDetail('planEstablishDetailform','constructionDispatch/viewPlan','',queryBack);

    //点击保存
    $(".saveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
		if($("#planEstablishDetailform").parsley().isValid()){
            var content = "确认派工？";
            if(!$("#tnician").val()){
                content = "未选定质检员，是否继续？"
            }
            if(!$("#sOfficer").val()){
                content = "未选定安全员，是否继续？"
            }
            if(!$("#mWacf").val()){
                content = "未选定材料员，是否继续？"
			}
            if(!$("#testLeader1").val()){
                content = "未选定班组长，是否继续？"
			}
            if(!$("#welder1").val()){
                content = "未选定焊工，是否继续？"
			}
            if(!$("#dcter").val()){
                content = "未选定资料员，是否继续？"
			}
            $("body").cgetPopup({
                title: "提示信息",
                content: content,
                accept: doSave,
                chide: false,
                icon: "fa-check-circle"
            });

	        //如果通过验证, 则移除验证UI
	        $("#planEstablishDetailform").parsley().reset();
		}else{
           	//如果未通过验证, 则加载验证UI
           	$("#planEstablishDetailform").parsley().validate();
        }
    }); 
	var doSave = function(){
		//加遮罩
		$(".infodetails").loadMask("正在保存...", 1, 0.5);
		$("#menuId").val("110507");
   		var data=$("#planEstablishDetailform").serializeJson();
   		$.ajax({
           type: 'POST',
           url: 'constructionDispatch/saveConstructionDispatch',
           contentType: "application/json;charset=UTF-8",
           data: JSON.stringify(data),
           success: function (data) {
        	//取消遮罩
        	$(".infodetails").hideMask();	   
           	var content = "数据保存成功！";
           	if(data === "false"){
           		content = "数据保存失败！";
           	}else if(data === "already"){
           		content = "此信息已被修改，请刷新页面！";
           	}else if(data === "true"){
           		$(".editbtn").addClass("hidden");
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
               console.warn("施工派工保存失败！");
           }
       });
	}
    //放弃 按钮点击后
    $(".cancelBtn").on("click",function(){
    	 //清空表单
    	 $("#planEstablishDetailform input").val('');
    	 trSData.planEstablishTable.t.cgetDetail('planEstablishDetailform','constructionDispatch/viewPlan','');
    	 $("#planEstablishDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["planEstablishTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#planEstablishDetailform").parsley().reset();
    }); 	
  	var postQae=$('#managementQae').val();
  	var postWacf=$('#managementWacf').val();
  	var postSafty=$('#saftyOfficer').val();
	var postDocumenter=$('#documenter').val();
  	var postTech=$('#technician').val();
  	var cuPm=$("#cuPm").val();
  	var unitType = $("#unitType").val();
  	//选择施工员
    $("#mqPop").off().on("click",function(){
       var deptId = $('#cuId').val(),
		   sid = this.__id;
       var corpId=$("#corpId").val();
    	staffPopup({
    		'mQae':'staffName',
    		'qaeTel':'mobile',
    		'managementQaeId':'staffId'
    	},postQae,unitType,deptId,corpId);
  	});
  	 
  	//选择材料员
    $("#mwPop").off().on("click",function(){
        var deptId = $('#cuId').val();
        var corpId=$("#corpId").val();
    	staffPopup({
    		'mWacf':'staffName',
    		'wacfTel':'mobile',
    		'managementWacfId':'staffId'
    	},postWacf,unitType,deptId,corpId);
  	});
  	
  	
  //选择资料员
    $("#dmPop").off().on("click",function(){
        var deptId = $('#cuId').val();
        var corpId=$("#corpId").val();
    	staffPopup({
    		'dcter':'staffName',
    		'dcterTel':'mobile',
    		'documenterId':'staffId'
    	}, postDocumenter,unitType,deptId,corpId);
  	});
 	//选择安全员
    $("#soPop").off().on("click",function(){
        var deptId = $('#cuId').val();
        var corpId=$("#corpId").val();
    	staffPopup({
    		'sOfficer':'staffName',
    		'saftyTel':'mobile',
    		'saftyOfficerId':'staffId'
    	},postSafty,unitType,deptId,corpId);
  	});
    
  	//选择质检员
    $("#tcPop").off().on("click",function(){
        var deptId = $('#cuId').val();
        var corpId=$("#corpId").val();
    	staffPopup({
    		'tnician':'staffName',
    		'technicianTel':'mobile',
    		'technicianId':'staffId'
    	},postTech,unitType,deptId,corpId);
  	});	
  	//选择项目经理
    $("#cuPop").off().on("click",function(){
        var deptId = $('#cuId').val();
        var corpId=$("#corpId").val();
    	staffPopup({
    		'cuPm':'staffName' ,
    		'cuPmTel':'mobile',
    		'cuPmId':'staffId'
    	},cuPm,unitType,deptId,corpId);
  	});
  	
  	
  	//班组长
    $("#testLeaderPop").off("click").on("click",function(){
    	var deptId=$("#cuId").val();
    	var post=$("#testLeader").val();
    	console.info("$#testLeader).val()----"+$("#testLeader").val());
    	$("#buildPost").val(post);
    	$("body").cgetPopup({
    		title: '班组长选择',
    		nocache:true,
    		content: '#staff/buildPop?post='+post+'&deptId='+deptId+'&corpId='+corpId,
    	    accept: function(){
    	    	if($("#staffTable tr.selected").length < 1){
    	    		$("body").cgetPopup({
    	    			title: '提示', 
    	    			content: "请选择班组长！", 
    	    			newpop: 'second', 
    	    			accept: innerClose
    	    		});
    	    		return false;
    	    	}else{
    	    		var roleIds = '';
    	    		var postNames='';
    	    		var data = $('#staffTable').DataTable().rows('.selected').data();
    	    		console.info("2----");
    	    		console.info(data);
    	    		$.each(data,function(key, val){
    	    			if(roleIds.length > 0) {
    	    				roleIds +=",";
    	    				postNames+=",";
    	    			}
    	    			roleIds += val.staffId;
    	    			postNames+=val.staffName;
    	    			console.info("val.staffName----"+val.staffName);
    	    			
    	    		});
    	    		$("#testLeaderId").val(","+roleIds+",");
    	    		console.info("postNames----"+postNames);
    	    		$("#testLeader1").val(postNames);
    	    	}
    	    },
    	  chide		: 'true'
    	});
   	});
  	
   	
  	//焊工
    $("#welderPop").off("click").on("click",function(){
    	var deptId=$("#cuId").val();
    	var post=$("#welder").val();
    	$("#buildPost").val(post);
    	$("body").cgetPopup({
    		title: '焊工选择',
    		nocache:true,
    		content: '#staff/buildPop?post='+post+'&deptId='+deptId,
    	    accept: function(){
    	    	if($("#staffTable tr.selected").length < 1){
    	    		$("body").cgetPopup({
    	    			title: '提示', 
    	    			content: "请选择焊工！", 
    	    			newpop: 'second', 
    	    			accept: innerClose
    	    		});
    	    		return false;
    	    	}else{
    	    		var roleIds = '';
    	    		var postNames='';
    	    		var data = $('#staffTable').DataTable().rows('.selected').data();
    	    		$.each(data,function(key, val){
    	    			if(roleIds.length > 0) {
    	    				roleIds +=",";
    	    				postNames+=",";
    	    			}
    	    			roleIds += val.staffId;
    	    			postNames+=val.staffName;
    	    		});
    	    		$("#welderId").val(","+roleIds+",");
    	    		$("#welder1").val(postNames);
    	    	}
    	    },
    	  chide		: 'true'
    	});
   	});
  	
  	
  	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->