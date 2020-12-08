<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-15  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="operateWorkFlowDetailform" action="operateWorkFlow/saveOperateWorkFlow">
		    <input type="hidden"  name="owfId" id="owfId" class = "addClear"/>
		    <input type="hidden" name = "stepName" id = "stepName"class = "addClear" />
		    <input type="hidden" id="corpName" name="corpName" class = "addClear"/>
		    <input type="hidden" id="opereaterId" name="opereaterId" class = "addClear"/>  
		    <div class="form-group col-sm-12 col-md-12">
				<label class="control-label" for="">分公司</label>
				<div>
					<select id="corpId" name="corpId" class="form-control input-sm field-editable addClear">
						<option value="-1">--请选择--</option>
						<option value="0">全局</option>
						<c:forEach var="enums" items="${corp}">
							<option value="${enums.deptId}" >${enums.deptName}</option>
						</c:forEach>
					</select>
			   </div>
		 	</div> 
		 	<div class="form-group col-sm-12 col-xs-12">
	            <label class="control-label" for="">工程类型</label>
	             <div>
		    		<select class="form-control input-sm field-editable addClear" id="projectType"  name="projectType" >
		 		      	<option value="-1">--请选择--</option>
		 		      	<option value="0">全局</option>
		 		      	<c:forEach var="enums" items="${projType}">
							   	<option value="${enums.projTypeId}">${enums.projConstructDes}</option>
		                </c:forEach>
		             </select>
		        </div>
	        </div>
	        <div class="form-group col-sm-12 col-xs-12">
	            <label class="control-label" for="">出资类型</label>
	            <div>
		            <select class="form-control input-sm field-editable addClear "  id="contributionMode"  name="contributionMode" >
		 		      	<option value="-1">--请选择--</option>
		 		      	<option value="0">全局</option>
		 		      	<c:forEach var="enums" items="${contributionMode}">
							   	<option value="${enums.id}">${enums.contributionDes}</option>
		                </c:forEach>
		             </select>
	             </div>
	        </div>
			<div class="form-group col-sm-12 col-xs-12">
				<label class="control-label" for="">操作步骤</label>
				<div>
					<select class="form-control input-sm field-editable addClear" id="stepId"  name="stepId" >
						<option value="-1">--请选择--</option>
						<c:forEach var="enums" items="${stepId}">
							<option value="${enums.value}">${enums.message}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="">流程类型</label>
				<div>
					<select id="opType" name="opType" class="form-control input-sm field-editable addClear">
						<option value="-1" >--请选择--</option>
						<c:forEach var="enums" items="${opType}">
							<option value="${enums.value}" >${enums.message}</option>
						</c:forEach>
					</select>
			   </div>
		    </div>
<%--			<div class="form-group col-md-12 hidden assistTypeId">
				<label class="control-label" for="">辅助流程类型</label>
				<div>
					<select class="form-control input-sm field-editable" id="assistTypeId"  name="assistTypeId" >
						<option value="-1">--请选择--</option>
						<c:forEach var="enums" items="${assistTypeId}">
							<option value="${enums.value}">${enums.message}</option>
						</c:forEach>
					</select>
				</div>
			</div>--%>
		    <input type="hidden" name="des" id="des"/>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">审核级别</label>
		        <div>
		            <select id="grade" name="grade" class="form-control input-sm field-editable addClear">
		                <option value="" selected = "selected"></option>
						<option value="1" >1级审核</option>
						<option value="2" >2级审核</option>
						<option value="3" >3级审核</option>
						<option value="4" >4级审核</option>
						<option value="5" >5级审核</option>
						<option value="6" >6级审核</option>
						<option value="7" >7级审核</option>
						<option value="8" >8级审核</option>
						<option value="9" >9级审核</option>
						<option value="10" >10级审核</option>
						<option value="11" >11级审核</option>
					</select>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">操作人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable addClear"  data-parsley-required="true" id="opereater" name="opereater"  />
		            <a id="opereaterPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择操作人"><i class="fa fa-search"></i></a>
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
    $("#operateWorkFlowDetailform").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");

	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
  	//查右侧详述
   trSData.t && trSData.t.cgetDetail('operateWorkFlowDetailform'); 
    
    //点击保存
    $(".saveBtn").on("click",function(){
    	//保存
    	$("#operateWorkFlowDetailform").cformSave('operateWorkFlowTable','',true);
    }); 
    
    //放弃 按钮点击后
    $(".cancelBtn").on("click",function(){
    	 //清空表单
    	 $("#operateWorkFlowDetailform input").val('');
    	 trSData.t.cgetDetail('operateWorkFlowDetailform'); 
    	 $("#operateWorkFlowDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["operateWorkFlowTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#operateWorkFlowDetailform").parsley().reset();
    });
 
    
    $("#projType").change(function(){
    	$("#contributionCode").empty();
    	var selectEl = $("#projType"),
        index = selectEl[0].selectedIndex,
        selectOption = selectEl[0].options[index];
    	var data = $(selectOption).attr("value");
    	$.ajax({
    		type: 'post',
    		url: 'workFlow/querySubcompany?id='+data,
    		dataType: 'json',
    		success: function(data){
    	        $("#contributionCode").append('<option value="-1">--请选择--</option>');
    			$.each(data,function(n, v){
    	            $("#contributionCode").append('<option value='+v.correlatedInfoId+'>' + v.correlatedInfoDes + '</option>');
    	        });
    			
    			var val=$(".contributionCode").val();
    			if($("#id").val()!=""){
    				console.info("flw----"+val);
    				$("#operateWorkFlowDetailform option[value='"+val+"']").attr("selected","selected");
    			}
    		},
    		error: function(data){
    			console.warn("出资类型选框查询失败");
    		}
    	});
    });
    
    //人员弹出框
    $("#opereaterPop").off("click").on("click",function(){
    	$("body").cgetPopup({
    		title: '人员选择',
    		nocache:false,
    		content: '#popup/operStaffPop',
     	    accept: function(){
    	    },
    	  chide		: 'true'
    	}); 
   	});
    
    

    
    
    opereaterPopup = function(param, post, unitType,deptId,corpId){
    	var url = '#popup/staffPop?post=' + post;
    	if(!unitType){
    		unitType = '';
    	}
    	if(!deptId){
    		deptId = '';
    	}
    	if(!corpId){
    		corpId = '';
    	}
    	url = url + '&unitType=' + unitType;
    	$("body").cgetPopup({
    		title: '人员选择',
    		nocache:true,
    	    content: '#popup/operateStaffPop?post=' + post + '&unitType=' + unitType + '&deptId=' + deptId+'&corpId='+corpId,
    	    accept: function(){
                alert($("#staffTable tr.selected").length);
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
    	    		/* $.each(param, function(k, v){
    	  		    	$('[id=' + k + ']').val(trSData.json[v]);
    		    	}); */
    		    	if($("#staffTable tr.selected").length == 1){
    		    		
    		    	}else{
    		    		
    		    	}
					if($("#opereater").val()==""){
						$("#opereater").val(trSData.json[staffName]);
					}
    	    	}
    	    },
    		size: 'large'
    	});
    }
   $("#corpId").change(function(){
	var corpIdLength =  $("#corpId").val().length;
	  if(corpIdLength > 3){  //当选中值时
		  $("#corpName").val($('#corpId option:selected').text());  //传入公司名称
	  }
   })
   
   //步骤名称
    $("#stepId").change(function(){
	var stepIdLength =  $("#stepId").val().length;
	  if(stepIdLength > 3){  //当选中值时
		  $("#stepName").val($('#stepId option:selected').text());  //传入步骤名称
	  }
   })

</script>
<!-- ================== END PAGE LEVEL JS ================== -->