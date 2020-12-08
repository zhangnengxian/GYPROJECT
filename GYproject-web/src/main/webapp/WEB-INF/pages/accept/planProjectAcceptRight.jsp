<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 acceptSaveBtn" >保存</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="toolBtn f-r p-b-10  informbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 hidden informSaveBtn" >确定</a>
	</div>
    <div class="clearboth form-box">
		<input type="hidden" id="deptName" name="deptName" value="${corpName}">
		<input type="hidden" id="surveyStatusId" name="surveyStatusId" value="${surveyStatusId}">
    	<form class="form-horizontal" id="acceptApplyForm" action="">
			<input type="hidden" id="planId" name="planId" />
    		<input type="hidden" id="projId" name="projId" />
    		<input type="hidden" id="corpId" name="corpId" />
    		<input type="hidden" name="tenantId" id="tenantId"/>
    		<input type="hidden" name="projStatusId" id="projStatusId"/>
    		<input type="hidden" id="projectTypeDes" name="projectTypeDes" />
    		<input type="hidden" id="contributionModeDes" name="contributionModeDes" />
    		<input type="hidden" class="deptName" name="deptName" />
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50" />
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="projName" name="projName" data-parsley-maxlength="200" data-parsley-required="true" />
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="deptId">业务部门</label>
				<div>
					<select class="form-control input-sm field-not-editable" id="deptId"  name="deptId"  >
						<option value=""></option>
						<c:forEach var="enums" items="${department}">
							<option value="${enums.correlatedInfoId}" >${enums.correlatedInfoDes}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="projectType">工程类型</label>
				<div>
					<select class="form-control input-sm field-not-editable" id="projectType"  name="projectType" data-parsley-required="true" >
						<c:forEach var="enums" items="${projLtype}">
							<option value="${enums.correlatedInfoId}">${enums.correlatedInfoDes}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="contributionMode">出资方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="contributionMode"  name="contributionMode" data-parsley-required="true" >
						<c:forEach var="enums" items="${contributionMode}">
							<option value="${enums.correlatedInfoId}">${enums.correlatedInfoDes}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label">是否招投标</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable" name="isBidding" value="1" />是
		            </label>
		            <label>
		            	<input type="radio" class="field-editable" name="isBidding" value="0" checked/>否
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" >是否募集资金</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable" name="isFunds" value="1" />是
		            </label>
		            <label>
		            	<input type="radio" class="field-editable" name="isFunds" value="0" checked/>否
		            </label>
		        </div>
		    </div>
		    <c:if  test="${ !empty viewUrl}">
			       <!-- 如果为空，则显示是否借料，否则要分公司配置流程 -->
				<c:import url="${viewUrl}"></c:import>
			</c:if>
		    <div class="form-group col-sm-12">
		    	<label class="control-label">用气规模</label>
		    	<div>
					<c:forEach var="enums" items="${projLtype}">
						<label>
							<input type="radio"  name="projLtype" class="field-not-editable" data-text='${enums.correlatedInfoDes}' value="${enums.correlatedInfoId}"/>${enums.correlatedInfoDes}
						</label>
					</c:forEach>
		        </div>
		    	<input type="hidden" class="projLtypeId" name="projLtypeId"/>
		   </div>
		   
		</form>
	</div>
    <div class="clearboth form-box m-t-5">
		<form id="scaleTableForm">
			<table id="scaleTable" class="table table-striped table-bordered nowrap" width="100%">
	        	<thead>
		            <tr>
		                <th width="80px">细类</th>
		                <th width="50px">管径</th>
		                <th width="50px">长度(千米)</th>
		                <th width="50px">完成(千米)</th>
		                <th width="50px">建设比例</th>
		                <!-- <th width="80px">预计用量(m³/h)</th> -->
		                <!-- <th width="30px"></th> -->
		            </tr>
	           </thead>
			</table>
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
	$("#acceptApplyForm").hideMask();

    //参数传false时，表单不可编辑
    $("#acceptApplyForm").toggleEditState(false);
    
    //表单样式适应
    $("#acceptApplyForm").styleFit();
    
    $(".backReasonshow").addClass("hidden");
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    if($.fn.DataTable.isDataTable('#scaleTable')){
        //初始化过
        $("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
    }else{
        sacletableinit();
    }
        trSData.t && trSData.t.cgetDetail('acceptApplyForm','','',scaleDetailRefresh);
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	 $("#acceptApplyForm,#scaleTableForm").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 
    	 var json=trSData.planProjectAcceptTable.json;
    		
   		if(json){
   			$("#projNo").val(json.projNo);
   			$("#projName").val(json.projName);
   			$("#projAddr").val(json.projAddr);
   		}
    	 detailFlag = false;
    });

    var getLtype = function(){
    	var projLtype = $("input[name='projLtype']:checked");
    	var projLtypeId="";
    	projLtype.each(function(){
    	    projLtypeId = projLtypeId +$(this).val()+",";
    	});
    	if(projLtypeId!==""){
    		projLtypeId = projLtypeId.substring(0,projLtypeId.length-1);
    	}else{
    		projLtypeId = '-1';
    	}
    	return projLtypeId;
    };

    $("input[name='projLtype']").on("change",function(){
        var projLtypeId=$("#projectType option:selected").val();
        detailSearchData.projLtypeId = projLtypeId;
        detailSearchData.projId = $("#projId").val();
        $('#scaleTable').cgetData(true);

    });

    var qback=function(){
    	var projLtypeId = $("input[name='projLtype']:checked").val();
		//选中
		detailSearchData.projLtypeId = projLtypeId;
    	detailSearchData.projId = $("#projId").val();
    	if($.fn.DataTable.isDataTable('#scaleTable')){
    		$.ajax({
                type: 'POST',
                url: 'projectAccept/queryScaleDetailAjax',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(detailSearchData),
                success: function (data) {
                	var len = JSON.parse(data).length;
                	for(var m=0;m<len;m++){
                    	var rows = [];
            			if(JSON.parse(data)[m]!=""){
                			var json = {};
                			json = JSON.parse(data)[m];
                			rows.push(json);
            		    }
            			$('#scaleTable').DataTable().rows.add(rows).draw();
            	    }
                	scaleTableCallBack2();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("ajax queryScaleDetail...fail");
                }
            });
    	}
    }

    var scaleTableCallBack2 = function(){
    	if(detailFlag){
	    	 $('#scaleTableForm').toggleEditState(true);
    	}else{
    		$('#scaleTableForm').toggleEditState(false);
    	}
    }
    
	//保存
	$(".acceptSaveBtn").on("click",function(){
		var tableform = $("#scaleTableForm");
	    var viewform = $("#acceptApplyForm");
		if (tableform.parsley().isValid() && viewform.parsley().isValid()) {
			
		//加遮罩
		$("#acceptApplyForm").loadMask("正在保存...", 1, 0.5);
		var projLtypeId = getLtype();
		$(".projLtypeId").val(projLtypeId);
        var tableform = $("#scaleTableForm").clone();
        tableform = tableform.find("tr.child").prev().children("td[style]").remove().end().end().end();
        var viewform = $("#acceptApplyForm");
        //开启表单验证
       /*  if (tableform.parsley().isValid() && viewform.parsley().isValid()) { */
    	   
    	   var projectTypeDes=$("#projectType option:selected").text();
       		var contributionModeDes=$("#contributionMode option:selected").text();
       		var deptName=$("#deptId option:selected").text();
       		$("#projectTypeDes").val(projectTypeDes);
       		$("#contributionModeDes").val(contributionModeDes);
       		$(".deptName").val(deptName);
    	   
	       	var mdata = tableform.getDTFormData();
	       	var viewdata = viewform.serializeJson();
	       	//选中的工程规模明细
	       	var resultData = [];
	       	for(var i=0;i<mdata.length;i++){
	       		var data = mdata[i];
	       		if(data.scaleId !== undefined){
	       			data.projNo = $("#projNo").val();
	       			data.projId = $("#projId").val();
//	       			if(data.tonnage!=="" || data.searNum!=="" || data.num!=="" || data.houseNum!=="" || data.gasConsumption!=="" ){
	       				//resultData.push(data);
//	       			}
	       			if((data.pipeDiameter!==null&&data.pipeDiameter!==""&& data.pipeDiameter!==undefined)||(data.pipeLength!==null&&data.pipeLength!==""&& data.pipeLength!==undefined) ){
	       				console.info("data.pipeLength--"+data.pipeLength);
	       				console.info("data.pipeDiameter--"+data.pipeDiameter);
	       				resultData.push(data);
	       			}
	       		}
	       	}
	       	if(resultData.length<1){
	       		$("#acceptApplyForm").hideMask();
	       		alertInfo("请填写用气规模明细记录！");
	       		return false;
	       	}
	       	//调用方法，用户选择用气规模大类是否与明细相对应
	       	var typeDes = getInfo(projLtypeId,resultData);
	       	if(typeDes!== ''){
		       	alertInfo(typeDes);
	       	}else{
	       		viewdata.scaleDetails = resultData;
	           	$.ajax({
	                   type: 'POST',
	                   url: 'planProjectAccept/totalSave',
	                   contentType: "application/json;charset=UTF-8",
	                   data: JSON.stringify(viewdata),
	                   success: function (data) {
	                	$("#acceptApplyForm").hideMask();   
	                   	var content = "数据保存成功！";
	                   	if(data === "false"){
	                   		content = "数据保存失败！";
	                   	}else if(data ===  "exist"){
	                   		content = "工程编号已存在，请修改！";
	                   	}else if(data === "true"){
	                   		$("#planProjectAcceptTable").cgetData();  //列表重新加载
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
	                	   $("#acceptApplyForm").hideMask();
	                       console.warn("立项申请受理区记录保存失败！");
	                   }
	               });
       	  }
	      //如果通过验证, 则移除验证UI
           	tableform.parsley().validate();
           	viewform.parsley().validate();
       }else{
       	 //如果未通过验证, 则加载验证UI
       	 tableform.parsley().validate();
       	 viewform.parsley().validate();
       } 
	});
	//确定
	$(".informSaveBtn").on("click",function(){
		var projId = $("#projId").val();
       	$.ajax({
               type: 'POST',
               url: 'projectAccept/informSave',
               contentType: "application/json;charset=UTF-8",
               data: projId,
               success: function (data) {
               	var content = "已通知客户";
               	if(data === "false"){
               		content = "通知失败！";
               	}else{
               		$("#projectAcceptTable").cgetData();  //列表重新加载
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
                   console.warn("退单通知客户失败！");
               }
           });
	});

	/*
	* 该方法用于判断用气规模大类与规模明细是否对应存在，例如：选中公用、车用，则必须填入了公用、车用明细的记录
	*/
	var getInfo = function(projLtypeId,resultData){
		var typeIds = projLtypeId.split(",");
       	for(var i=0;i<resultData.length;i++){
       		//console.log("typeIds.length:"+typeIds.length);
       		for(var k=0;k<typeIds.length;k++){
       			if(typeIds[k] === resultData[i].projLtypeId){
       				typeIds.splice(k, 1);  
       			}
       		}
     		if(typeIds.length<1){
     			break;
     		}
       	}
       	if(typeIds.length>0){
       		var typeDes = '';
       		for(var n=0;n<typeIds.length;n++){
       			$.each($('[name="projLtype"]:checked'),function(i, el){
       			    //console.log($(el).val()+"......"+$(el).attr("data-text"));
           		    if($(el).val() === typeIds[n]){
           		    	typeDes = typeDes+$(el).attr("data-text");
           		    	if(n<typeIds.length-1){
           		    		typeDes = typeDes+'、'
           		    	}
           		    }
           		})

       		}
	       	typeDes = "请输入用气规模为"+typeDes+"的明细记录！";
	        return typeDes;
       	}
       	return '';
	}
	//选择申报单位
    $("#custPop").off().on("click",function(){
    	$("body").cgetPopup({
    		title: '申报单位选择',
    		content: '#popup/custListPop',
    		size: 'large',
    		callback:function(){
    			customer.init();
    		},
    	    accept: function(){
    	    	if($("#customerTable tr.selected").length < 1){
    	    		$("body").cgetPopup({title:'提示',content:"请选择申报单位！",newpop:'second',accept:innerClose});
    	    		return false;
    	    	}else{
    		    	var data = trSData.customerTable.json;
    		    	$("#custName").val(data.custName);
    		    	$("#custId").val(data.custId);
    		    	//联系人、联系电话
    		    	$("#custPhone").val(data.custPhone);
    		    	$("#custContact").val(data.custContcat);
    	    	}
    	    }
      	});
  	});

    //切换工程类型
    $("#projectType").change(function(){
        //radio选择
        var projLtypeId=$("#projectType option:selected").val();
        $("input[name='projLtype'][value="+projLtypeId+"]").attr("checked","checked");

        $("input[name='projLtype']").change();
        $("#contributionMode").empty();
        var data = projLtypeId;
        if(data==''){
            data='-1'
        }
        $.ajax({
            type: 'post',
            url: 'correlation/queryCorrelationList?corType=3&correlateInfoId='+data+'&acceptType=1',
            dataType: 'json',
            success: function(data){
                $.each(data,function(n, v){
                    $("#contributionMode").append('<option value='+v.correlatedInfoId+'>' + v.correlatedInfoDes + '</option>');
                });
            },
            error: function(data){
                console.warn("出资类型选框查询失败");
            }
        });
    });

    //切换业务部门
    $("#deptId").change(function(){
        //工程类型清空
        $("#projectType").empty();
        var selectEl = $("#deptId"),
            index = selectEl[0].selectedIndex,
            selectOption = selectEl[0].options[index];
        var data = $(selectOption).attr("value");
        if(data==''){
            data='-1'
        }
        $.ajax({
            type:'post',
            url :'correlation/queryCorrelationList?corType=2&correlateInfoId='+data,
            dataType:'json',
            success:function(data){
                $.each(data,function(n,v){
                    $("#projectType").append('<option value='+v.correlatedInfoId+'>' + v.correlatedInfoDes + '</option>');

                })
                $("#projectType").change();
            },
            error: function(data){
                console.warn("工程类型选框查询失败");
            }
        })
    })
</script>
<!-- ================== END PAGE LEVEL JS ================== -->