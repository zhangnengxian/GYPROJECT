<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="infodetails">
	<%-- <div class="toolBtn f-r p-b-10  editbtn">
		<a href="javascript:;" class="btn  btn-primary btn-sm m-l-5  hidden get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>
	    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5  entBtn" >保存</a>
	    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5  saveBtn hidden" >回复保存</a>
    	<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="businessCommunicationForm" data-parsley-validate="true" action="">
		    <input type="hidden" name="projId" id="projId"/>
		    <input type="hidden" name="corpId" id="corpId"/>
		    <input type="hidden" class="formClear" name="bcId" id="bcId"/>
		    <input type="hidden" class="formClear" name="version" id="version"/>
		    <input type="hidden" class="formClear" name="bcStatus" id="bcStatus"/>
		    <input type="hidden" class="formClear" name="bcSendType" id="bcSendType"/>
		    <input type="hidden" name="bcInitiatorId" id="bcInitiatorId"/>
		    <input type="hidden" class="formClear" name="bcRecipientId" id="bcRecipientId"/>
		    <input type="hidden" name="bcInitiatorUnitId" id="bcInitiatorUnitId"/>
		    <input type="hidden" name="bcInitiatorUnitName" id="bcInitiatorUnitName"/>
		    <input type="hidden" class=""  id="bcTypeDetail1"/>
		    <input type="hidden" class="formClear"  id="nrId" name="nrId"/>
       		<div class="form-group col-md-6 col-sm-12 ">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
<!-- 		    <div class="form-group col-md-12  "> -->
<!-- 		        <label class="control-label" for="projAddr">工程地点</label> -->
<!-- 		        <div> -->
<!-- 		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/> -->
<!-- 		        </div> -->
<!-- 		    </div> -->
<!-- 		    <div class="form-group col-md-12"> -->
<!-- 	            <label class="control-label" for="projScaleDes">工程规模</label> -->
<!-- 	            <div> -->
<!-- 	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3"></textarea> -->
<!-- 	            </div> -->
<!--             </div> -->
<!--             <div class="form-group col-md-6 col-sm-12"> -->
<!-- 		    	<label class="control-label" for="corpName">燃气公司</label> -->
<!-- 		        <div> -->
<!-- 		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" /> -->
<!-- 		        </div> -->
<!-- 		    </div> -->
<!--         	<div class="form-group col-md-6 col-sm-12"> -->
<!-- 		        <label class="control-label" for="">工程类型</label> -->
<!-- 		    	<div> -->
<!-- 		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/> -->
<!-- 		        </div> -->
<!-- 		    </div> -->
<!-- 		    <div class="form-group col-md-6 col-sm-12"> -->
<!-- 		        <label class="control-label" for="">出资方式</label> -->
<!-- 		    	<div> -->
<!-- 		    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/> -->
<!-- 		        </div> -->
<!-- 		    </div> -->
<!-- 		    <div class="form-group col-md-6 col-sm-12"> -->
<!-- 		        <label class="control-label" for="">业务部门</label> -->
<!-- 		    	<div> -->
<!-- 		    		 <input type="text" class="form-control input-sm field-not-editable" name="deptName" value=""/>         -->
<!-- 		        </div> -->
<!-- 		    </div> -->
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="bcType">通知类型</label>
		        <div>
		            <select class="form-control input-sm field-editable" id="bcType"  name="bcType" data-parsley-required="true" >
		                <c:forEach var="enums" items="${bcType}">
							   	<option value="${enums.typeId}">${enums.typeDes}</option>
		                </c:forEach>
		             </select>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="bcTypeDetail">通知细类</label>
		        <div>
		            <select class="form-control input-sm field-editable" id="bcTypeDetail"  name="bcTypeDetail" data-parsley-required="true" >
		 		      	 <c:forEach var="enums" items="${bcTypeDetail}">
							   	<option value="${enums.typeId}">${enums.typeDes}</option>
		                </c:forEach>
		             </select>
		        </div>
		    </div>
		    <div class="form-group col-md-6 noUser">
		        <label class="control-label" for="bcInitiatorName">发起人姓名</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="bcInitiatorName" name="bcInitiatorName" value=""/>
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="noticeDate">通知日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-not-default" id="noticeDate"  name="noticeDate" data-parsley-required="true" value="" >
		        </div>
		   	 </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="noticeContent">通知内容</label>
		         <div> 
		        	<textarea class="form-control field-editable formClear" name="noticeContent" id="noticeContent" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">检测项目</label>
 		    	<div>
		    		 <select class="form-control input-sm field-editable" id="testItem" name="testItem" data-parsley-maxlength="20">
		    		 	<c:forEach var="enums" items="${ndeTestItem }" varStatus="s">
		    		 		<option value="${enums.value }">${enums.message }</option>
		    		 	</c:forEach>
		    		 </select>   
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">管线编号</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="pipeLineNum" name="pipeLineNum" data-parsley-maxlength="50"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">表面状态</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="surfaceState" name="surfaceState" data-parsley-maxlength="50"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">总焊口数</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="totalWeldsNum" name="totalWeldsNum" data-parsley-maxlength="255"/>   
		        </div>
		    </div>
		     <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">管材</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="tubing" name="tubing" data-parsley-maxlength="10"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">管口数</label>
 		    	<div>
		    		 <input type="number" class="form-control input-sm field-editable" id="nozzleNum" name="nozzleNum" value=""/>   
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">规格</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="spec" name="spec" data-parsley-maxlength="255"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">合格级别</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="qualifiedLevel" name="qualifiedLevel" data-parsley-maxlength="255"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">坡口形式</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="grooveForm" name="grooveForm" data-parsley-maxlength="255"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">验收标准</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="acceptStandard" name="acceptStandard" data-parsley-maxlength="255"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">焊工证号</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="welderNo" name="welderNo" data-parsley-maxlength="50"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">焊接方法</label>
 		    	<div>
		    		 <select class="form-control  field-editable" id="weldingMethod" name="weldingMethod" data-parsley-maxlength="50">
		    		 	<c:forEach var="enums" items="${ndeWeldMethod}">
		    		 		<option value="${ enums.value}">${ enums.message}</option>
		    		 	</c:forEach>
		    		 	
		    		 </select>   
		        </div>
		    </div>
		   
		   	<div class="form-group col-md-6">
				<label class="control-label" for="email">从属单位类型</label>
				<div>
					<select id="unitType" name="unitType" class="form-control input-sm field-editable" data-parsley-required ='true'>
						<option value=""></option>
						<c:forEach var="enums" items="${unitTypes}">
							<option value="${enums.value}" data-c='${enums.type}'>${enums.message}</option>
						</c:forEach>
					</select>
					<!-- <input class="form-control input-sm field-editable"  name="unitType" id="unitType"> -->
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="bcRecipientDeptName">所属部门</label>
				<div>
					<input type="hidden" class="formClear" id="deptId" name="deptId">
		            <input type="text" class="form-control input-sm field-not-editable formClear" id="bcRecipientDeptName" name="bcRecipientDeptName" data-parsley-required ='true'/>
		            <a id="staffDeptTreeId" class="btn btn-default btn-sm m-l-10 " title="选择所属部门"><i class="fa fa-search"></i></a> 
	            </div>
			</div>
			<div class="form-group col-md-6 noUser">
		        <label class="control-label" for="">接收人姓名</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable formClear"  id="bcRecipientName" name="bcRecipientName" data-parsley-required ='true'/>
		            <a id="bcrPop" class="btn btn-default btn-sm m-l-10" title="选择施工员"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="">回复日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default formClear" id="replyDate"  name="replyDate" value="" >
		        </div>
		   	 </div>
		     <div class="form-group col-md-12">
		        <label class="control-label" for="replyContent">回复内容</label>
		        <div> 
		        	<textarea class="form-control field-not-editable formClear" name="replyContent" id="replyContent" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
		        </div>
		    </div>
		</form>
		<div class = "clearboth"></div>
		<form id="fileupload" action="businessCommunication/uploadFile" method="POST" enctype="multipart/form-data">
		    <input type="hidden" name="alPath" id="alPath" value="0104"/>
		    <input type="hidden" name="encryption" id="encryption" />
		    <input type="hidden" name="caiId" id="caiId" />
		    <input type="hidden" name="stepId" id="stepId" />
		    <input type="hidden" class = "formClear" name="busRecordId" id="busRecordId" />
		   	<input type="hidden" id="loginId" name="loginId" value="${loginName.staffId}"/>
		   	<input type="hidden" id="aspectRatio" value="1.25" />
			<div class="fileupload-buttonbar">
		        <div class="pull-right toolBtn">
		            <span class="btn btn-success btn-sm fileinput-button">
		                <i class="fa fa-plus"></i>
		                <span>浏览文件...</span>
		                <input type="file" name="files[]" multiple/>	             	          
		            </span>
		            <button type="submit" class="btn btn-primary btn-sm start hidden">
	                </button>
	                <button type="button" class="btn btn-primary btn-sm upload-btn">
	                    <i class="fa fa-upload"></i>
	                    <span>上传</span>
	                </button>
		        </div>
		        <div class="col-md-12 fileupload-progress fade hidden">
		            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
		                <div class="progress-bar progress-bar-success" style="width:0%;"></div>
		            </div>
		            <div class="progress-extended">&nbsp;</div>
		        </div>
		    </div>
		    <table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
	    </form>
		<table id="dataPopTableSecond" class="table table-striped table-bordered nowrap" width="100%">
	   		<thead>
	     		<tr>
	     			<th></th>
	     		    <th></th>
	           		<th>资料名称</th>
	           		<th>资料类型</th>
	            	<th>签收日期</th>
	            	<th>签收人</th>
	            	<th width='40'>操作</th>
	           	</tr>
	       	</thead>
		</table> --%>
    </div>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    $('#businessCommunicationForm').deserialize(getProjectInfo());
    $("#businessCommunicationForm").toggleEditState(false);
  	
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $(".editbtn").addClass("hidden");
    //查右侧工程详述
    trSData.t && trSData.t.cgetDetail('businessCommunicationForm','businessCommunication/viewByBcId','',surveyBack); 

    accessoryData.busRecordId = $("#bcId").val()==''||$("#bcId").val()==undefined?"aaa":$("#bcId").val();
	if($.fn.DataTable.isDataTable('#dataPopTableSecond')){
		//初始化过
		$("#dataPopTableSecond").cgetData(false,function(){
		
		});
	}else{
		seconddatainit1();
	}
	//选择细类事件
	var changeBcTypeDetail = function(){
	  	//探伤
		//判断细类
		if($("#bcTypeDetail").val()=='2011'){
                 if($("#bcId").val()==""){
                     $('.clear-sign').click();
                 }
				$(".ndeRecord").removeClass("hidden");
				$(".ndeFormat").addClass("hidden");
				$(".menuId").val("120213");//业务沟通菜单ID
		}else if($("#bcTypeDetail").val()=='2010'){//判断细类
                console.info("切换-2010");
				$(".ndeFormat").removeClass("hidden");
				$(".ndeRecord").addClass("hidden");
				$(".menuId").val("120214");//无损检测菜单ID
		}else{
				$(".ndeRecord").addClass("hidden");
				$(".ndeFormat").addClass("hidden");
				$(".menuId").val("");
		}
	};
	//通知细类
  	$('#bcTypeDetail').on("change",function(){
  		changeBcTypeDetail();
  	})
  	
    $("#staffDeptTreeId").on("click",function(){
		var url = '#businessCommunication/staffDeptTree';
		var popoptions = {
			title: '所属部门',
			content: url,
			accept: staffDeptTreeDone
		};
		$("body").cgetPopup(popoptions);
	});
    function staffDeptTreeDone(){
    	var deptId=$("#selDeptId").val();
    	var detpName = $("#selDeptName").val();
    	$("#deptId").val(deptId);
    	$("#bcRecipientDeptName").val(detpName);
    }
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	//清空表单
    	$(".formClear").val('');
    	$("#businessCommunicationForm").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
   	 	$("#businessCommunicationTable").cgetData();
   	 	
    });
    /**点击右侧维护区 保存 按钮时*/
    $(".entBtn").off("click").on("click",function(){
    	if($("#businessCommunicationForm").parsley().isValid()){
    		$("#pushFlag").val("0");
        	backDone();
         }else{
         	//如果未通过验证, 则加载验证UI
         	$("#businessCommunicationForm").parsley().validate();
         }
    });
    /**点击右侧维护区 推送 按钮时*/
    $(".pushBtn").off("click").on("click",function(){
    	//验证签字
    	
    	if($("#businessCommunicationForm").parsley().isValid()){
    		//验证必签签字是否已签
	        var signtools =$('#businessCommunicationForm').find(".signature-tool.sign-require").parent().not(".hidden"),
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
    			$("#pushFlag").val("1");
        		backDone();
	        }
         }else{
         	//如果未通过验证, 则加载验证UI
         	$("#businessCommunicationForm").parsley().validate();
         }
    });
    var backDone = function(){
        $(".entBtn").parent().parent().loadMask("正在保存...", 1, 0.5);
     	var data=$("#businessCommunicationForm").serializeJson();
     	var ndeRecord = $("#businessCommunicationForm").serializeJson();
     	//去未隐藏的签字
     	//json字符串
        	var sign=[];//页面显示的签字
        	$.each(ndeRecord.sign,function(i,e){
        		if(!$("#"+e.fieldName).parent().parent().is(":hidden")){
        			sign = sign.concat(e);
        		}
        	});
        ndeRecord.sign = sign;
     	data.ndeRecord = ndeRecord;
    	$.ajax({
            type: 'POST',
            url: 'businessCommunication/saveBusinessCommunication',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
                $(".entBtn").parent().parent().hideMask();
            	var content = "数据保存成功！";
            	if(data === "false"){
            		content = "数据保存失败！";
            	}else if(data === "true"){
            		saveCallBack(data);
            	}else if(data === "already"){
            		content = "此信息已被修改，请刷新页面！";
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
                console.warn("业务沟通记录保存失败！");
            }
        });
    	//如果通过验证, 则移除验证UI
    	$("#businessCommunicationForm").parsley().reset();
    }
    $(".saveBtn").off("click").on("click",function(){
    	if($("#businessCommunicationForm").parsley().isValid()){
        	backDone1();
         }else{
         	//如果未通过验证, 则加载验证UI
         	$("#businessCommunicationForm").parsley().validate();
         }
    });
    var backDone1 = function(){
        $(".entBtn").parent().parent().loadMask("正在保存...", 1, 0.5);
     	var data=$("#businessCommunicationForm").serializeJson();
     	var ndeRecord = $("#businessCommunicationForm").serializeJson();
     	//去未隐藏的签字
     	//json字符串
        	var sign=[];//页面显示的签字
        	$.each(ndeRecord.sign,function(i,e){
        		if(!$("#"+e.fieldName).parent().parent().is(":hidden")){
        			sign = sign.concat(e);
        		}
        	});
        ndeRecord.sign = sign;
     	data.ndeRecord = ndeRecord;
    	$.ajax({
            type: 'POST',
            url: 'businessCommunication/replyBusinessCommunication',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
                $(".entBtn").parent().parent().hideMask();
            	var content = "数据保存成功！";
            	if(data === "false"){
            		content = "数据保存失败！";
            	}else if(data === "true"){
            		saveCallBack(data);
            	}else if(data === "already"){
            		content = "此信息已被修改，请刷新页面！";
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
                console.warn("业务沟通记录保存失败！");
            }
        });
    	//如果通过验证, 则移除验证UI
    	$("#businessCommunicationForm").parsley().reset();
    }
    var saveCallBack = function(data){
    	if(data === "true"){
    		//表单不可编辑
        	$("#businessCommunicationForm").toggleEditState(false);
        	//按钮隐藏
    		$(".editbtn").addClass("hidden");
    	}else{
    		$(".editbtn").removeClass("hidden");
    	}
    	var pcId = $("#surveyId").val(),
		projNo = $("#projNo").val();
		projId = $("#projId").val();
		$("#businessCommunicationTable").cgetData();
    }
    //切换通知细类
    $("#bcType").change(function(){
    	//工程类型清空
    	$("#bcTypeDetail").empty();
    	var selectEl = $("#bcType"),
        index = selectEl[0].selectedIndex,
        selectOption = selectEl[0].options[index];
    	var data = $(selectOption).attr("value");
    	$.ajax({
    		type:'post',
    		url :'businessCommunication/queryBcTypeDetail?bcType='+data,
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(n,v){
    				if(n=0){
    					$("#bcTypeDetail").append('<option value='+v.typeId+'>' + v.typeDes + '</option>');
    					$("#bcTypeDetail option[value='"+v.typeId+"']").attr("selected","selected");
    				}
    				$("#bcTypeDetail").append('<option value='+v.typeId+'>' + v.typeDes + '</option>');
    			});
    			//判断细类
    			$("#bcTypeDetail").val($("#bcTypeDetail1").val());
    			changeBcTypeDetail();
    		},
    		error: function(data){
    			console.warn("通知细类查询失败");
    		}
    	})
    });
  	//接收人
    $("#bcrPop").off().on("click",function(){
        var deptId = $('#deptId').val()==''?'-1':$('#deptId').val(),
        	unitType = $('#unitType').val()==''?'-1':$('#unitType').val();
    	staffPopup({
    		'bcRecipientName':'staffName',
    		'bcRecipientId':'staffId'
    	},'',unitType,deptId);
  	});
  	
  	
    </script>
<!-- ================== END PAGE LEVEL JS ================== -->