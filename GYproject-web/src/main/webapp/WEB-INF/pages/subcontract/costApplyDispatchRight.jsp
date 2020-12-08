<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editBtn hidden">
       <a href="javascript:;" class="btn btn-query btn-sm m-l-7 dispatchBtn" >派工</a>
	</div>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="costApplyAuditDispatchFrom" data-parsley-validate="true" action="">
			<input type="hidden"  name="paId"  id="paId" value=""/>
			<input type="hidden"  name="businessOrderId"  id="businessOrderId" value=""/>
			<input type="hidden"  name="applyDeptId"  id="applyDeptId" value=""/>
			<input type="hidden" name="surveyer"  id="surveyer" value=""/>
			<input type="hidden" name="surveyerId"  id="surveyerId" value=""/>
			<input type="hidden" name="devide"  id="devide" value=""/>
			<div class="form-group col-md-6">
            	<label class="control-label" for="applyNo">请款编号</label>
            	<div >
                	<input type="text" class="form-control input-sm field-editable" id="applyNo" name="applyNo"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 ">
            	<label class="control-label" for="feeType">费用类型</label>
            	<div>
		    		<select class="form-control input-sm field-not-editable" id="feeType"  name="feeType"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${feeType}">
							   	<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		             </select>
		        </div>
        	</div>
			<div class="form-group col-md-12">
            	<label class="control-label" for="applyDeptName">请款单位</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="applyDeptName" name="applyDeptName"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 col-sm-12">
            	<label class="control-label" for="applyer">请款人</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="applyer" name="applyer"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6">
            	<label class="control-label" for="applyAmount">请款金额</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable  fixed-number text-right" id="applyAmount" name="applyAmount"/>
            	</div>
        	</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="applyRemark">请款备注</label>
				<div>
					<textarea class="form-control field-editable" name="applyRemark" id="applyRemark" rows="2" cols="" data-parsley-maxlength="500"></textarea>
				</div>
			</div>
		</form>
    </div>
	<div>
    <h4 class="m-t-20"><strong>预算员</strong></h4>
    </div>
    <table id="auditerTable" class="table table-hover table-bordered nowrap" width="100%">
        <thead>
            <tr>
            	<th class="hidden"></th>
            	<th>名称</th>
                <th>待费用审核任务</th>
<!--                 <th>待设计任务</th> -->
                <!-- <th></th> -->
            </tr>
        </thead>
	</table>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    $("#costApplyAuditDispatchFrom").toggleEditState(false).styleFit();
    
    //查右侧工程详述
    //trSData.t.cgetDetail('costApplyAuditDispatchFrom','doneDispatch/viewDispatchResult','',detailBack);
    trSData.t&&trSData.t.cgetDetail('costApplyAuditDispatchFrom','costApplyDispatch/viewPaymentApply','',detailBack);
   
    //点击派工按钮
    $(".dispatchBtn").on("click",function(){
    	var radioVal = $("#costApplyAuditDispatchFrom input[type='radio']:checked").val();
    	if($("#costApplyAuditDispatchFrom").parsley().isValid()){
    		//if(radioVal=="1") {   
       		    var len=$("#auditerTable").find("tr.selected").length;
       		    if(len>0){
       		    	var surveyer = $("#auditerTable").find("tr.selected td:eq(1)").text();
       		    	var surveyerId = $("#auditerTable").find("tr.selected td:eq(0)").text();
       		    	$("#surveyer").val(surveyer);//选择的设计人员
       		    	$('#surveyerId').val(surveyerId);
       		    	console.info(surveyer);
       		    	console.info(surveyerId);
       		    	$("#businessOrderId").val($("#paId").val());
       		        $("body").cgetPopup({
	                 	    title: "提示信息",
	                    	content: '确认要派工给 <i class="fa fa-user"></i> '+surveyer+" 吗？",
	                    	accept: function(){
	                 		var data=$("#costApplyAuditDispatchFrom").serializeJson();
	                 		console.info("data---");
	                 		console.info(data);
	                 		
	           	        	$.ajax({
	           	                type: 'POST',
	           	                url: 'costApplyDispatch/updatePaymentApply',
	           	                contentType: "application/json;charset=UTF-8",
	           	                data: JSON.stringify(data),
	           	                success: function (data) {
	           	                	var content = "派工成功！";
	           	                	if(data === "false"){
	           	                		content = "派工失败！";
	           	                	}else if(data === "true"){
	           	                		$("#costApplyAuditDispatchFrom").formReset();
	           	                		$("#costApplyAuditDispatchFrom").toggleEditState();
	           	                	 	$("#costApplyDispatchTable").cgetData(true,doneDispatchCallBack);
	           	                	}
	           	                	var myoptions = {
	           	                        	title: "提示信息",
	           	                        	content: content,
	           	                        	accept: popClose,
	           	                        	chide: true,
	           	                        	newpop:'new',
	           	                        	icon: "fa-check-circle"
	           	                    }
	           	                    $("body").cgetPopup(myoptions);
	           	                },
	           	                error: function (jqXHR, textStatus, errorThrown) {
	           	                    console.warn("审核派遣区派工失败！");
	           	                }
	           	            }); 
	                 	},
	                 	icon: "fa-check-circle",
	                 	newpop: 'new'
                 });
       		    
       		    }else{					//未选设计人员
       		    	$("body").cgetPopup({
       					title: '提示',
       					content: '请选择预结算员！',
       					accept: popClose
       		    	});
       		    }
    		$("#costApplyAuditDispatchFrom").parsley().reset();
    	}else{
    		$("#costApplyAuditDispatchFrom").parsley().validate();
    	}  
    })
    
    
    auditerTableinit();
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->