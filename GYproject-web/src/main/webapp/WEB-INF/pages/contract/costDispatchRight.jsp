<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editBtn hidden">
       <a href="javascript:;" class="btn btn-query btn-sm m-l-7 dispatchBtn" >派工</a>
	</div>
	<div class="clearboth form-box">
		<input class="deptType" type="hidden" name="deptType"  value="${deptType}"/>
		<form class="form-horizontal" id="designDispatchForm" data-parsley-validate="true" action="">
			<input type="hidden" name="deptId"  id="deptId" value=""/>
			<input type="hidden"  name="deptDivide"  id="deptDivide" value=""/>
			<input type="hidden" name="designStationId"  id="designStationId" value=""/>
			<input type="hidden" name="unitId" id="unitId" value=""/>
			<input type="hidden" name="unitName" id="unitName" value=""/>
			<div class="form-group col-md-6">
            	<label class="control-label" for="projNo">工程编号</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo"/>
            	</div>
        	</div>
			<div class="form-group col-md-12">
            	<label class="control-label" for="projName">工程名称</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="projName" name="projName"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 col-sm-12">
            	<label class="control-label" for="projAddr">工程地点</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="projAddr" name="projAddr"/>
            	</div>
        	</div>
        	<div class="form-group col-md-12">
            	<label class="control-label" for="projScaleDes">工程规模</label>
            	<div>
		            <textarea class="form-control field-not-editable"  id="projScaleDes" name="projScaleDes" rows="2" data-parsley-maxlength="200"></textarea>
		        </div>
        	</div>
        	<div class="form-group col-md-12">
            	<label class="control-label" for="custName">申报单位</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="custName" name="custName"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" >联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="200" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" >联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" >燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" >工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes"  data-parsley-maxlength="100"/>
		    		<!-- <select class="form-control input-sm field-not-editable" id="projectType"  name="projectType" data-parsley-required="true" >
						<option value="1" >居民户工程</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" >出资方式</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes"  data-parsley-maxlength="100"/>
		    		<!-- 
		    		<select class="form-control input-sm field-not-editable" id="investmentMethod"  name="investmentMethod" data-parsley-required="true" >
						<option value="1" >用户出资（自有资金）</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" >业务部门</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"  data-parsley-maxlength="100"/>
		        </div>
		    </div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >总造价费</label>
				<div>
					<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="budgetTotalCost" name="budgetTotalCost"  value=""/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<%--<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >工程费</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="CIVIL_COST" name="CIVIL_COST"  data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >主材费</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="materialCost" name="materialCost"  data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >监理费</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="suCost" name="suCost"  data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >设计费</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="designCost" name="designCost"  data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >不可预见费</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="unforeseenCost" name="unforeseenCost"  data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"/>
				</div>
			</div>--%>
        	<input type="hidden" name="projId" id="projId"/>
        	<input type="hidden" name="surveyer" id="surveyer"/>
        	<input type="hidden" name="surveyerId" id="surveyerId"/>
		</form>
    </div>
    <div class="clearboth form-box hidden">
    	<form class="form-horizontal" id="costDispatchForm" data-parsley-validate="true" action="">
    		<input type="hidden" name="budgeter" id="budgeter"/>
    	</form>
	</div>		    	
	<div>
	    <h4 class="m-t-20"><strong>造价员</strong></h4>
	    </div>
	    <table id="costerTable" class="table table-hover table-bordered nowrap" width="100%">
	        <thead>
	            <tr>
	            	<th class="">造价员ID</th>
	            	<th>名称</th>
	                <th>待造价任务</th>
	            </tr>
	        </thead>
		</table>
</div>
<div class="clearboth p-t-15">
   
</div>

<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    $("#designDispatchForm").toggleEditState().styleFit();
    
    //查右侧工程详述
    trSData.t&&trSData.t.cgetDetail('designDispatchForm','costDispatchingController/viewDispatchResult','',detailBack);
	costerTableinit();
   
    //点击派工
    $(".dispatchBtn").off().on("click",function(){
    	var projLen = $("#designDispatchTable").find("tr.selected").length;
    	if(projLen<1){
    		alertInfo("请先选择工程！");
    		return;
    	}
    	var len=$("#costerTable").find("tr.selected").length;
    	if(len>0){
    		var json = $("#costerTable").DataTable().rows( '.selected' ).data();
		    var surveyer=json[0].costMember;
		    var surveyerId=json[0].costMemberId;
    		//var budgeter = $("#costerTable").find("tr.selected td:eq(1)").text();
    		//var budgeterId = $("#costerTable").find("tr.selected td:eq(0)").text();
    		 $("body").cgetPopup({
          	    title: "提示信息",
             	content: '确认要派工给 <i class="fa fa-user"></i> '+surveyer+" 吗？",
             	accept: function(){
          		var data=$("#costDispatchForm").serializeJson();
          		data.projId=$("#projId").val();
          		data.surveyer=surveyer;
          		data.surveyerId=surveyerId;
          		console.info("data");
          		console.info(data);
    	        	$.ajax({
    	                type: 'POST',
    	                url: 'costDispatchingController/updateProject',
    	                contentType: "application/json;charset=UTF-8",
    	                data: JSON.stringify(data),
    	                success: function (data) {
    	                	var content = "派工成功！";
    	                	if(data === "false"){
    	                		content = "派工失败！";
    	                	}else if(data === "true"){
    	                	    $('#costerTable').cgetData(true);
    	                	    $(".editBtn").addClass("hidden");
    	                	    $("#designDispatchTable").cgetData(true,designDispatchCallBack);  //列表重新加载
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
    	                    console.warn("造价派遣区派工失败！");
    	                }
    	            }); 
	          	},
	          	icon: "fa-check-circle",
	          	newpop: 'new'
      		});
    	}else{
    		$("body").cgetPopup({
					title: '提示',
					content: '请选择造价员！',
					accept: popClose
		    });
    	}
    	
    })
    
    
    //点击确定
    var sureDone=function(){}
    var ensureDone=function(){}
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->