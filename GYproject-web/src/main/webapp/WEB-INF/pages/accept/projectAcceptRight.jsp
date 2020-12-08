<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 acceptSaveBtn" onclick="save('acceptSave')">保存</a>
		 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 pushBtn" onclick="save('push')">推送</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="toolBtn f-r p-b-10  informbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 hidden informSaveBtn" >确定</a>
	</div>
	
   <div class="form-group col-md-6 col-sm-12 hidden">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    		 <select class="form-control input-sm field-editable" id="projectType1"  name="projectType1"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${projectType}">
							   	<option data-department="${enums.contractType}"value="${enums.projTypeId}" >${enums.contractType}</option>
		                </c:forEach>
		                
		             </select>		        
		        </div>
		    </div>
		   <input type="hidden" id="contributionMode1" name="contributionMode1" />
    <div class="clearboth form-box">
    	<input type="hidden"  id="deptName" name="deptName" value="${corpName}">
    	<form class="form-horizontal" id="acceptApplyForm" action="">
    		<input type="hidden" id="projId" name="projId" />
			<input type="hidden" id="saveType" name="saveType" />
    		<input type="hidden" id="corpId" name="corpId" />
    		<input type="hidden" name="tenantId" id="tenantId"/>
    		<input type="hidden" id="projectTypeDes" name="projectTypeDes" />
    		<input type="hidden" id="contributionModeDes" name="contributionModeDes" />
    		<input type="hidden" class="deptName" name="deptName" />
    		
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50"/>
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">工程名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="projName" name="projName" data-parsley-maxlength="200" data-parsley-required="true"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
            	<label class="control-label" for="projSourceDes">受理来源</label>
            	<div>
		            <input type="text" class="form-control field-not-editable"  id="projSourceDes" name="projSourceDes"  data-parsley-maxlength="200"/>
		        </div>
        	</div>
		    <div class="form-group col-sm-12 custInfo">
		        <label class="control-label" for="custName">申报单位</label>
		        <div>
		        	<input type="hidden" id="custId" name="custId"/>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" data-parsley-maxlength="200" data-parsley-required="true"/>
		        	 <a id="custPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择申报单位"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 custInfo">
		        <label class="control-label" for="">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custContact" name="custContact" data-parsley-maxlength="200" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 custInfo">
		        <label class="control-label" for="">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    		 <select class="form-control input-sm field-editable" id="deptId"  name="deptId"  data-parsley-required="true">
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${department}">
							   	<option data-department="${enums.scaleType}"value="${enums.correlatedInfoId}" >${enums.correlatedInfoDes}</option>
		                </c:forEach>
		                
		             </select>		        
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<select class="form-control input-sm field-editable" id="projectType" onchange="projectTypeChange(this.value)"  name="projectType" data-parsley-required="true" >
		 		      	<c:forEach var="enums" items="${projLtype}">
							<option value="${enums.correlatedInfoId}">${enums.correlatedInfoDes}</option>
		                </c:forEach>
		             </select>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    		<select class="form-control input-sm field-editable" id="contributionMode"  name="contributionMode" data-parsley-required="true" >
		 		      <c:forEach var="enums" items="${contributionMode}">
							<option value="${enums.correlatedInfoId}">${enums.correlatedInfoDes}</option>
		                </c:forEach>
		            </select>
		        </div>
		    </div>
		    
		    
		    <!-- <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">是否招投标</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable" name="isBidding" value="1" />是
		            </label>
		            <label>
		            	<input type="radio" class="field-editable" name="isBidding" value="0" checked/>否
		            </label>
		        </div>
		    </div> -->
		  <!--   <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">是否募集资金</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable" name="isFunds" value="1" />是
		            </label>
		            <label>
		            	<input type="radio" class="field-editable" name="isFunds" value="0" checked/>否
		            </label>
		        </div>
		    </div> -->
			<!--报装类型-->

			<c:choose>
				<c:when test="${empty innstalltype}">
					<div class="form-group col-md-6 col-sm-12 clearboth biJie">
						<label class="control-label" for="">主建设阶段</label>
						<div>
							<label>
								<input type="radio" class="field-editable" name="projSubConSta" value="1" />既有
							</label>
							<label>
								<input type="radio" class="field-editable" name="projSubConSta" value="2" checked/>新建
							</label>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:import url="${innstalltype}"></c:import>
				</c:otherwise>
			</c:choose>


		    <div class="form-group col-md-6 col-sm-12 biJie">
		        <label class="control-label" for="">告知书发放</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable" name="projInfoFlag" value="1" checked/>已发
		            </label>
		            <label>
		            	<input type="radio" class="field-editable" name="projInfoFlag" value="0" />未发
		            </label>
		        </div>
		    </div>
		    <!-- <div class="form-group col-md-6 col-sm-12">
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
		    </div> -->
		    <!-- <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">存在借料</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-editable" name="materialFlag" value="1" />是
		            </label>
		            <label>
		            	<input type="radio" class="field-editable" name="materialFlag" value="0" />否
		            </label>
		        </div>
		    </div> -->
		    <c:if  test="${ empty viewUrl}">
			       <!-- 如果为空，则显示是否借料，否则要分公司配置流程 -->
				<c:import url="guiyangAccept.jsp"></c:import>
			</c:if>
			<c:if  test="${ !empty viewUrl}">
			       <!-- 如果为空，则显示是否借料，否则要分公司配置流程 -->
				<c:import url="${viewUrl}"></c:import>
			</c:if>
			<!--如果是贵安则显示是否立项转办-->
			<c:if test="${ !empty transfeFlag}">
				<c:import url="${transfeFlag}"></c:import>
			</c:if>

			<div class="form-group col-md-12">
				<label class="control-label" for="projectRemark">受理备注</label>
				<div>
					<textarea class="form-control field-editable" name="projectRemark" id="projectRemark" rows="2" cols="" data-parsley-maxlength="200"></textarea>
				</div>
			</div>
		    <div class="form-group col-md-12 remark">
		     	<label class="control-label" for="remark">备注</label>
		     	<div> 
		        	<textarea class="form-control  field-not-editable" name="remark" id="remark" rows="2" cols="" data-parsley-maxlength="200"></textarea>
	        	</div>
		    </div>
		    <div class="form-group col-md-12 backReasonshow">
		        <label class="control-label" for="">退单原因</label>
		    	<div>
		    		<select class="form-control input-sm field-editable" id="backReason"  name="backReason" >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${backReason}">
							   	<option value="${enums.value}">${enums.message}</option>
		                </c:forEach>
		             </select>
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		    	<label class="control-label" for="">用气规模</label>
		    	<div>
		             <c:forEach var="enums" items="${listCheck}">
	             		 <label>
			            	<input type="radio"  name="projLtype" class="field-not-editable" data-text='${enums.projTypeDes}' value="${enums.projTypeId}"/>${enums.projConstructDes}
			            </label>
		             </c:forEach>
		        </div>
		    	<input type="hidden" class="projLtypeId" name="projLtypeId"/>
		   </div>
		   
		</form>
	</div>
    <div class="clearboth form-box m-t-5 scaleTableForm">
		<form id="scaleTableForm"  data-parsley-validate="true">
			<table id="scaleTable" class="table table-striped table-bordered nowrap" width="100%"">
	        	<thead>
		            <tr>
		                <th width="80px">细类</th>
		                <th width="50px">吨位</th>
		                <th width="50px">座数</th>
		                <th width="50px">台数</th>
		                <th width="50px">户数</th>
		                <th width="80px">预计用量(m³/h)</th>
		                <!-- <th width="30px"></th> -->
		            </tr>
	           </thead>
			</table>
			
		</form>
	</div>
	<div class="clearboth form-box m-t-5 scaleTableChangeForm hidden">
		<form id="scaleTableChangeForm"  data-parsley-validate="true">
			<table id="scaleChangeTable" class="table table-striped table-bordered nowrap" width="100%"">
	        	<thead>
		            <tr>
		                <th width="80px">细类</th>
		                <th width="50px">管径</th>
		                <th width="50px">长度(千米)</th>
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
    trSData.t && trSData.t.cgetDetail('acceptApplyForm','projectAccept/viewProjectAccept','',scaleDetailRefresh);


	//选择改管显示是否立项转办
    function projectTypeChange(projType) {
        if(projType==13){//改管显示
            $('#transfeFlag').removeClass('hidden');
        }else{//其他隐藏
            $('#transfeFlag').addClass('hidden');
            $("input[name='transfeFlag']").attr('checked',false);
        }
    }


    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	 $("#acceptApplyForm,#scaleTableForm,#scaleTableChangeForm").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 $('#projectAcceptTable').cgetData(true);
    	 //selectTr["projectAcceptTable"] = 0;
    	 /* $('tbody tr:eq(0)').select(); */
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
    
    
    //用气规模change事件
    $("input[name='projLtype']").on("change",function(){
    	var projLtypeId=$("#projectType option:selected").val();
    	detailSearchData.projLtypeId = projLtypeId;
    	detailSearchData.projId = $("#projId").val();
    	
    	
    	var selectVal=$("#projectType1 option:selected").text();
    	if(selectVal=="31"||selectVal=="41"){
    		$(".scaleTableForm").addClass("hidden");
    		$(".scaleTableChangeForm").removeClass("hidden");
    		if($.fn.DataTable.isDataTable('#scaleChangeTable')){
    			//初始化过
    			$("#scaleChangeTable").cgetData(false,scaleChangeTableCallBack);//列表重新加载
    		}else{
    			sacletableinit2();
    		}
    	}else{
    		$(".scaleTableChangeForm").addClass("hidden");
    		$(".scaleTableForm").removeClass("hidden");
    		$('#scaleTable').cgetData(true,scaleTableCallBack);
    	}
    	
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


    function save(type){
		$("#saveType").val(type);
        var selectVal=$("#projectType1 option:selected").text();
        var tableform,scaleTable;
        if(selectVal=="31" || selectVal=="41"){
            tableform = $("#scaleTableChangeForm");
            scaleTable=$("#scaleChangeTable");
            tableform = $("#scaleTableChangeForm").clone();
            tableform = tableform.find("tr.child").prev().children("td[style]").remove().end().end().end();
        }else{
            tableform = $("#scaleTableForm");
            scaleTable=$("#scaleTable");
            tableform = $("#scaleTableForm").clone();
            tableform = tableform.find("tr.child").prev().children("td[style]").remove().end().end().end();
        }
        var viewform = $("#acceptApplyForm");
        if (tableform.parsley().isValid() && viewform.parsley().isValid()) {
            //加遮罩
            $("#acceptApplyForm").loadMask("正在保存...", 1, 0.5);
            var projLtypeId = getLtype();
            $(".projLtypeId").val(projLtypeId);
            //tableform = tableform.clone();
            //tableform = tableform.find("tr.child").prev().children("td[style]").remove().end().end().end();

            //开启表单验证
            //if (tableform.parsley().isValid() && viewform.parsley().isValid()) {

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

                    if((data.tonnage!==null&&data.tonnage!==""&& data.tonnage!==undefined) || (data.searNum!==null&&data.searNum!==""&& data.searNum!==undefined) || (data.num!==null&&data.num!==""&& data.num!==undefined) || (data.houseNum!==null&&data.houseNum!==""&& data.houseNum!==undefined) || (data.gasConsumption!==null&&data.gasConsumption!==""&& data.gasConsumption!==undefined)||(data.pipeDiameter!==null&&data.pipeDiameter!==""&& data.pipeDiameter!==undefined)||(data.pipeLength!==null&&data.pipeLength!==""&& data.pipeLength!==undefined) ){
                        resultData.push(data);
                    }
                }
            }

            if(selectVal!=="31" && selectVal!=="41"){
                if(resultData.length<1){
                    //取消遮罩
                    $("#acceptApplyForm").hideMask();
                    alertInfo("请填写用气规模明细记录！");
                    return false;
                }
            }

            //调用方法，用户选择用气规模大类是否与明细相对应

            var typeDes;
            if(selectVal=="31" && selectVal!=="41"){
                typeDes="";
            }else{
                typeDes = getInfo(projLtypeId,resultData);
            }

            if(typeDes!== ''){
            	$("#acceptApplyForm").hideMask();
            	alertInfo(typeDes);
            }else{
                viewdata.scaleDetails = resultData;
                $.ajax({
                    type: 'POST',
                    url: 'projectAccept/totalSave',
                    contentType: "application/json;charset=UTF-8",
                    dataType:"json",
                    data: JSON.stringify(viewdata),
                    success: function (data) {
                        //取消遮罩
                        $("#acceptApplyForm").hideMask();
                        var content = "数据保存成功！";
                        if(data.ret_type === "-1"){
                            content = "数据保存失败！";
                        }else if(data.ret_message ===  "exist"){
                            content = "工程编号已存在，请修改！";
                        }else if(data.ret_message ===  "noneNumber"){
                            content = "该类出资方式编码尚未配置，请先在后台配置！";
                        }else if(data.ret_message === "true"){
                            if($("#projId").val()==""){
                                $("#projectAcceptTable").cgetData();  //列表重新加载
                                $(".editbtn").addClass("hidden");
                        		$("#acceptApplyForm").toggleEditState(false);
                            }else{
                                $("#projectAcceptTable").cgetData(true,saveProjectBack,true);  //列表重新加载
                            }
                        }else{//接口异常
                            content =  data.ret_message;
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
                        $("#acceptApplyForm").hideMask();
                        console.warn("立项申请受理区记录保存失败！");
                    }
                });
                //如果通过验证, 则移除验证UI
                tableform.parsley().validate();
                viewform.parsley().validate();
            }
        }else{
            //如果未通过验证, 则加载验证UI
            tableform.parsley().validate();
            viewform.parsley().validate();
        }
	}
	//保存
	/*$(".acceptSaveBtn").on("click",function(){
		var selectVal=$("#projectType1 option:selected").text();
		var tableform,scaleTable;
		if(selectVal=="31"){
			tableform = $("#scaleTableChangeForm");
			scaleTable=$("#scaleChangeTable");
		}else{
			tableform = $("#scaleTableForm");
			scaleTable=$("#scaleTable");
		}
	    var viewform = $("#acceptApplyForm");
		if (tableform.parsley().isValid() && viewform.parsley().isValid()) {
			//加遮罩
			$(".infodetails").loadMask("正在保存...", 1, 0.5);	
			var projLtypeId = getLtype();
			$(".projLtypeId").val(projLtypeId);
        //tableform = tableform.clone();
        //tableform = tableform.find("tr.child").prev().children("td[style]").remove().end().end().end();
        
        //开启表单验证
        //if (tableform.parsley().isValid() && viewform.parsley().isValid()) {
        	
        	var projectTypeDes=$("#projectType option:selected").text();
       		var contributionModeDes=$("#contributionMode option:selected").text();
       		var deptName=$("#deptId option:selected").text();
       		$("#projectTypeDes").val(projectTypeDes);
       		$("#contributionModeDes").val(contributionModeDes);
       		$(".deptName").val(deptName);
        	
	       	var mdata = tableform.getDTFormData();
	       	console.info("mdata----");
	       	console.info(mdata);
	       	
	       	var viewdata = viewform.serializeJson();
	       	//选中的工程规模明细
	       	var resultData = [];
	       	for(var i=0;i<mdata.length;i++){
	       		var data = mdata[i];
	       		if(data.scaleId !== undefined){
	       			data.projNo = $("#projNo").val();
	       			data.projId = $("#projId").val();
       				console.info("pipeDiameter---");
	       			console.info(data.pipeDiameter);
	       			
	       			if((data.tonnage!==null&&data.tonnage!==""&& data.tonnage!==undefined) || (data.searNum!==null&&data.searNum!==""&& data.searNum!==undefined) || (data.num!==null&&data.num!==""&& data.num!==undefined) || (data.houseNum!==null&&data.houseNum!==""&& data.houseNum!==undefined) || (data.gasConsumption!==null&&data.gasConsumption!==""&& data.gasConsumption!==undefined)||(data.pipeDiameter!==null&&data.pipeDiameter!==""&& data.pipeDiameter!==undefined)||(data.pipeLength!==null&&data.pipeLength!==""&& data.pipeLength!==undefined) ){
	       				console.info("data.pipeLength--"+data.pipeLength);
	       				console.info("data.pipeDiameter--"+data.pipeDiameter);
	       				resultData.push(data);
	       			}
	       		}
	       	}
	       	
	       	console.info("selectVal--"+selectVal);
	       	
	       	if(selectVal!=="31"){
	       		if(resultData.length<1){
	       			//取消遮罩
	       			$(".infodetails").hideMask();	
		       		alertInfo("请填写用气规模明细记录！");
		       		return false;
		       	}
	       	}
	       	
	       	//调用方法，用户选择用气规模大类是否与明细相对应
	       	
	       	var typeDes;
	       	if(selectVal=="31"){
	       		typeDes="";
	       	}else{
	       	    typeDes = getInfo(projLtypeId,resultData);
	       	}
	       	
	       	if(typeDes!== ''){
		       	alertInfo(typeDes);
	       	}else{
	       		viewdata.scaleDetails = resultData;
	           	$.ajax({
	                   type: 'POST',
	                   url: 'projectAccept/totalSave',
	                   contentType: "application/json;charset=UTF-8",
	                   dataType:"json",
	                   data: JSON.stringify(viewdata),
	                   success: function (data) {
	                	//取消遮罩
	                	$(".infodetails").hideMask();	
	                   	var content = "数据保存成功！";
	                   	if(data.ret_type === "-1"){
	                   		content = "数据保存失败！";
	                   	}else if(data.ret_message ===  "exist"){
	                   		content = "工程编号已存在，请修改！";
	                   	}else if(data.ret_message ===  "noneNumber"){
	                   		content = "该类出资方式编码尚未配置，请先在后台配置！";
	                   	}else if(data.ret_message === "true"){
	                   		if($("#projId").val()==""){
	                   			$("#projectAcceptTable").cgetData();  //列表重新加载
	                   		}else{
	                   			$("#projectAcceptTable").cgetData(true,saveProjectBack,true);  //列表重新加载
	                   		}
	                   	}else{//接口异常
	                   		content =  data.ret_message;
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
	                       console.warn("立项申请受理区记录保存失败！");
	                   }
	               });
	            //如果通过验证, 则移除验证UI
	           	tableform.parsley().validate();
	           	viewform.parsley().validate();
       	  }
       }else{
       	 //如果未通过验证, 则加载验证UI
       	 tableform.parsley().validate();
       	 viewform.parsley().validate();
       }
	});*/
	
	var saveProjectBack=function(){
		$(".editbtn").addClass("hidden");
		$("#acceptApplyForm").toggleEditState(false);
	}
	
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
		console.info(2020,resultData);
        console.info(2021,projLtypeId);
	    var typeIds = projLtypeId.split(",");
       	for(var i=0;i<resultData.length;i++){
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

    //初始化规模表格
    if(!trSData.projectAcceptTable.t){
    	detailSearchData.projId = "-1";
    	sacletableinit();
    }
    //sacletableinit();
    $("#projectType").change(function(){
    	//radio选择
    	var projLtypeId=$("#projectType option:selected").val();
    	$("input[name='projLtype'][value="+projLtypeId+"]").attr("checked","checked");
    	$("#projectType1 option[value='"+projLtypeId+"']").attr("selected","selected");
    	var contributionMode1 = $("#contributionMode1").val()
    	
    	/* var detailSearchData={};
    	detailSearchData.projLtypeId = "-2";
    	detailSearchData.projId = $("#projId").val();
    	if($.fn.DataTable.isDataTable('#scaleTable')){
    		//初始化过
    		$("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
    	}else{
    		sacletableinit();
    	} */
    	var scaleType=$("#deptId option:selected").attr("data-department");
    	
    	$("input[name='projLtype']").change();
    	$("#contributionMode").empty();
    	/* var selectEl = $("#projectType"),
        index = selectEl[0].selectedIndex,
        selectOption = selectEl[0].options[index]; */
    	var data = projLtypeId;
    	if(data==''){
    		data='-1'
    	}
    	
    	if(data=="11"){
    		//居民
    		$(".isBidding").removeClass("hidden");
    	}else{
    		$(".isBidding").addClass("hidden");
    	}
    	console.info("data1=====");
    	console.info(data);
    	$.ajax({
    		type: 'post',
    		url: 'correlation/queryCorrelationList?corType=3&correlateInfoId='+data+'&acceptType=2&scaleType='+scaleType,
    		dataType: 'json',
    		success: function(data){
//     	        $("#contributionMode").append('<option value=""></option>');
				//$("#scaleType").val(v.scaleType);
    			$.each(data,function(n, v){
    	            if(v.correlatedInfoId == contributionMode1){
    	            	$("#contributionMode").append('<option selected="selected" value="'+v.correlatedInfoId+'">' + v.correlatedInfoDes + '</option>');
    	            }else{
    					$("#contributionMode").append('<option value='+v.correlatedInfoId+'>' + v.correlatedInfoDes + '</option>');
    	            }
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
    	var selectEl = $("#deptId");
    	var index, selectOption, data ;
    	if(selectEl && selectEl[0]){
        	index = selectEl[0].selectedIndex,
       	 	selectOption = selectEl[0].options[index];
    		data = $(selectOption).attr("value");
    	}
    	if(data==''){
    		data='-1';
    	}
    	console.info("data22=====");
    	console.info(data);
    	$.ajax({
    		type:'post',
    		url :'correlation/queryCorrelationList?corType=2&correlateInfoId='+data+'&acceptType=2',
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(n,v){
    				/* if(n=0){
    					$("#projectType").append('<option value='+v.correlatedInfoId+'>' + v.correlatedInfoDes + '</option>');
    					$("#projectType option[value='"+v.correlatedInfoId+"']").attr("selected","selected");
    				} */
    				$("#projectType").append('<option value='+v.correlatedInfoId+'>' + v.correlatedInfoDes + '</option>');
    				
    			})
    			$("#projectType").change();
    		},
    		error: function(data){
    			console.warn("工程类型选框查询失败");
    		}
    	})
    	
    });
  
    $(document).ready(function() {
    //延时执行，因为选择业务部门和工程类型的时候公司出资没有值，所以需要延迟执行
    	 $('#projectType').change(function() {
    		 setTimeout('HIddCuIn()', 500);
         });
    	
    $('#deptId').change(function() {
    		 setTimeout('HIddCuIn()', 500);
         });
    	 
     $('#contributionMode').change(function() {
    	 HIddCuIn();
      }); 
     });
  
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->