<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 saveButton modifySaveBtn" >暂存</a>
    	 <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="projInfoModifyForm" action="">
    		<!-- <button type="reset" class="hidden" id="reset"/> -->
    		<input type="hidden" id="projId" name="projId" />
    		<input type="hidden" id="projStatusId" name="projStatusId" />
    		<input type="hidden" id="flag" name="flag" />
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">内部工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="insideProjNo" name="insideProjNo"  data-parsley-maxlength="50"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">受理单号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="paNo" name="paNo" />
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">工程名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="projName" name="projName" data-parsley-maxlength="200" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">申报单位</label>
		        <div>
		        	<input type="hidden" id="custId" name="custId"/>
		            <input type="text" class="form-control input-sm field-editable"  id="custName" name="custName" data-parsley-maxlength="200" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custContact" name="custContact" data-parsley-maxlength="100" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" data-parsley-required="true"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">区域</label>
		    	<div>
		    		<select class="form-control input-sm field-editable" id="area"  name="area"  data-parsley-required="true">
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${area}">
							   	<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		             </select>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 col-sm-12 clearboth">
		        <label class="control-label" for="">主建设阶段</label>
		    	<div>
		            <label>
		              	<input type="radio" class="field-editable" name="projSubConSta" value="1" /> 既有
		            </label>
		            <label>
		              	<input type="radio" class="field-editable" name="projSubConSta" value="2" /> 在建
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">告知书发放</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-not-editable" name="projInfoFlag" value="1" /> 已发
		            </label>
		            <label>
		            	<input type="radio" class="field-not-editable" name="projInfoFlag" value="0" /> 未发
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">营改增项目</label>
		    	<div>
		            <label>
		              	<input type="radio" class="field-editable" name="deductible" value="1" /> 是
		            </label>
		            <label>
		              	<input type="radio" class="field-editable" name="deductible" value="0" /> 否
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		    	<label class="control-label" for="">用气规模</label>
		    	<div>
		             <c:forEach var="enums" items="${projLtype}">
	             		 <label>
			            	<input type="checkbox"  name="projLtype" class="field-editable" data-text='${enums.projTypeId}' value="${enums.projTypeId}"/> ${enums.projTypeDes}
			            </label>
		             </c:forEach>
		        </div>
		    	<input type="hidden" class="projLtypeId" name="projLtypeId"/>
		   </div>
		   
		</form>
	</div>
    <div class="clearboth form-box m-t-5">
		<form id="scaleTableForm">
			<table id="scaleTable" class="table table-striped table-bordered nowrap" width="100%"">
	        	<thead>
		            <tr>
		                <th width="50px">细类</th>
		                <th width="50px">吨位</th>
		                <th width="50px">座数</th>
		                <th width="50px">台数</th>
		                <th width="50px">户数</th>
		                <th width="50px">预计用量(m³/h)</th>
		                <th>造价</th>
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
	$(".infodetails").hideMask();
	$("#projPicture").addClass("disabled");
	$("#projPhoto").addClass("disabled");
    $("#projInfoModifyForm,scaleTableForm").toggleEditState(false);
    changeAText();
    //表单样式适应
    $("#projInfoModifyForm").styleFit();
    $(".editbtn").addClass("hidden");
    
    trSData.t && trSData.t.cgetDetail('projInfoModifyForm','projInfoModify/viewProjectInfoModify','',scaleDetailRefresh);
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	 $("#projInfoModifyForm,#scaleTableForm").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 $("#projInfoModifyTable").cgetData(true,trSelectedBack); 
    	 //selectTr["projInfoModifyTable"] = 0;
    	 $('tbody tr:eq(0)').select();
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
    	}
    	return projLtypeId;
    };
    $("input[name='projLtype']").on("change",function(){
    	//var projLtypeId = getLtype();
    	var projLtypeId = $(this).val();
    	if($(this).is(":checked")){
    		//选中
    		detailSearchData.projLtypeId = projLtypeId;
        	detailSearchData.projId = $("#projId").val();
        	if($.fn.DataTable.isDataTable('#scaleTable')){
        		//初始化过
        		//$("#scaleTable").cgetData(false,scaleTableCallBack2);//列表重新加载
        		//ajax请求
        		//queryScaleDetail
        		$.ajax({
                    type: 'POST',
                    url: 'projectAccept/queryScaleDetailAjax',
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(detailSearchData),
                    success: function (data) {
                    	detailFlag = true;
                    	//console.log("ajax queryScaleDetail..."+data+"...length:"+JSON.parse(data).length);
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
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.warn("ajax queryScaleDetail...fail");
                    }
                }); 
        	}
    	}else{
    		//去除
    		$('#scaleTable').DataTable().rows( function (idx, data, node ) {

    	        return data.projLtypeId === projLtypeId ? true : false;

    	    }).remove().draw();
    	}
    	
    });
    
	
	//推送
	$(".modifySaveBtn").on("click",function(){
		var projLtypeId = getLtype();
		$(".projLtypeId").val(projLtypeId);
        var tableform = $("#scaleTableForm");
        var viewform = $("#projInfoModifyForm");
        //开启表单验证
	    if (tableform.parsley().isValid() && viewform.parsley().isValid()) {
	    	backDone();
       }else{
       	 //如果未通过验证, 则加载验证UI
       	 tableform.parsley().validate();
       	 viewform.parsley().validate();
       }
	});
	
	//确定退单
	var backDone = function(){
		var projLtypeId = getLtype();
		$(".projLtypeId").val(projLtypeId);
        var tableform = $("#scaleTableForm").clone();
        tableform = tableform.find("tr.child").prev().children("td[style]").remove().end().end().end();
        var viewform = $("#projInfoModifyForm");
       	var mdata = tableform.getDTFormData();
       	var viewdata = viewform.serializeJson();
       	//选中的工程规模明细
       	var resultData = [];
       	for(var i=0;i<mdata.length;i++){
       		var data = mdata[i];
       		if(data.scaleId !== undefined){
       			data.projNo = $("#projNo").val();
       			data.projId = $("#projId").val();
       			if(data.tonnage!=="" || data.searNum!=="" || data.num!=="" || data.houseNum!=="" || data.gasConsumption!=="" ||data.scaleAmount!==""){
       				resultData.push(data);
       			}
       		}
       	}
       	if(resultData.length<1){
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
	               url: 'projInfoModify/totalSave',
	               contentType: "application/json;charset=UTF-8",
	               data: JSON.stringify(viewdata),
	               success: function (data) {
	               	var content = "数据保存成功！";
	               	if(data === "false"){
	               		content = "数据保存失败！";
	               	}else if(data ===  "exist"){
	               		content = "工程编号已存在，请修改！";
	               	}else if(data === "true"){
	               		$(".projLtypeId").val('');
	            		$("input[name='projLtype']").removeClass("field-not-editable");
	            		$("#projInfoModifyForm")[0].reset();
	            		
	            		$("input[name='projLtype']").attr("checked",false);
	            		$("input[name='projLtype']").addClass("field-not-editable");
	               		$("#projInfoModifyTable").cgetData(true,confirmTableCallBack);  //列表重新加载
	               		
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
	                   console.warn("立项确认受理区记录保存失败！");
	               }
	           });
	       	//如果通过验证, 则移除验证UI
	       	tableform.parsley().validate();
	       	viewform.parsley().validate();
       }
	}
	/*
	* 该方法用于判断用气规模大类与规模明细是否对应存在，例如：选中公用、车用，则必须填入了公用、车用明细的记录
	*/
	var getInfo = function(projLtypeId,resultData){
		console.log(projLtypeId);
		console.log(resultData);
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
	
	 if(!trSData.projInfoModifyTable.t){
	    	detailSearchData.projId = "-1";
	    	sacletableinit();
	    }
	 
	 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->