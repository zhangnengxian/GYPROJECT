<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editBtn hidden">
       <a href="javascript:;" class="btn btn-query btn-sm m-l-7 dispatchBtn" >派工</a>
	</div>
	<div class="clearboth form-box">
	
		<input class="deptType" type="hidden" name="deptType"  value="${deptType}"/>
		<form class="form-horizontal" id="startDispatchForm" data-parsley-validate="true" action="">
			<input type="hidden" name="duId"  id="duId" value=""/>
			<input type="hidden" name="designStationId"  id="designStationId" value=""/>
			<input type="hidden" name="unitId" id="unitId" value=""/>
			<input type="hidden" name="unitName" id="unitName" value=""/>
			
			<input type="hidden" class="form-control field-editable" id="projId" name="projId" value=""/>
    		 <input type="hidden" class="form-control field-editable" id="sdId" name="sdId" value=""/>
    		 <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
    		 <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2" data-parsley-maxlength="200"></textarea>
	            </div>
            </div>
            <div class="form-group col-md-6 ">
		    	<label class="control-label" for="conNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable" id="conNo" name="conNo"/>
		        </div>
		    </div>
        	<div class="form-group col-md-6 ">
		    	<label class="control-label" for="contractAmount">合同金额</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable" id="contractAmount" name="contractAmount"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		    	<label class="control-label" for="scNo">协议编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable" id="scNo" name="scNo"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		    	<label class="control-label" for="scAmount">协议金额</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable" id="scAmount" name="scAmount"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cmoName">施工管理处</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cmoName" name="cmoName" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="suName">监理单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="cuName">分包单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="cuPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuPhone" name="cuPhone"/>
		        </div>
		    </div>
        	<input type="hidden" name="surveyer" id="surveyer"/>
		</form>
    </div>
	<div>
    <h4 class="m-t-20"><strong>结算员</strong></h4>
    </div>
    <table id="designerTable" class="table table-hover table-bordered nowrap" width="100%">
        <thead>
            <tr>
            	<th>名称</th>
                <th>待初审任务</th>
<!--                 <th>待设计任务</th> -->
                <!-- <th></th> -->
                <th>总合同金额</th>
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
    $("#startDispatchForm").toggleEditState().styleFit();
    
    //查右侧工程详述
    //trSData.t.cgetDetail('startDispatchForm','startDispatch/viewDispatchResult','',detailBack);
    trSData.t&&trSData.t.cgetDetail('startDispatchForm','auditStartDispatch/viewDispatchResult','',detailBack);
   
    //点击派工按钮
    $(".dispatchBtn").on("click",function(){
    	var radioVal = $("#startDispatchForm input[type='radio']:checked").val();
    	if($("#startDispatchForm").parsley().isValid()){
    		//if(radioVal=="1") {   
       		    var len=$("#designerTable").find("tr.selected").length;
       		    if(len>0){
       		    	var surveyer = $("#designerTable").find("tr.selected td:eq(0)").text();
       		    	$("#surveyer").val(surveyer);//选择的设计人员
       		        $("body").cgetPopup({
	                 	    title: "提示信息",
	                    	content: '确认要派工给 <i class="fa fa-user"></i> '+surveyer+" 吗？",
	                    	accept: function(){
	                 		var data=$("#startDispatchForm").serializeJson();
	                 		console.info(data);
	           	        	$.ajax({
	           	                type: 'POST',
	           	                url: 'auditStartDispatch/updateProject',
	           	                contentType: "application/json;charset=UTF-8",
	           	                data: JSON.stringify(data),
	           	                success: function (data) {
	           	                	var content = "派工成功！";
	           	                	if(data === "false"){
	           	                		content = "派工失败！";
	           	                	}else if(data === "true"){
	           	                		$("#startDispatchForm").formReset();
	           	                		$("#startDispatchForm").toggleEditState();
	           	                		//updateDesignerBack();
	           	                		$("input[name='depositGet'][value='0']").attr("checked","checked");
	           	                		$('#designerTable').cgetData();
	           	                	 	$("#startDispatchTable").cgetData(true,startDispatchCallBack);
	           	                	    $('#designerTable').cgetData(true);
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
	           	                    console.warn("设计派遣区派工失败！");
	           	                }
	           	            }); 
	                 	},
	                 	icon: "fa-check-circle",
	                 	newpop: 'new'
                 });
       		    
       		    }else{					//未选设计人员
       		    	$("body").cgetPopup({
       					title: '提示',
       					content: '请选择结算人员！',
       					accept: ensureDone
       		    	});
       		    }
        	//}else{   //未缴设计定金
           		/* $("body").cgetPopup({     
       				title: '提示',
       				content: '请缴纳设计定金！',
       				accept: sureDone
       	    	}); */
        	//}
    		$("#startDispatchForm").parsley().reset();
//     		deptcallback();
    	}else{
    		$("#startDispatchForm").parsley().validate();
    	}  
    })
    
    
    //点击确定
    var sureDone=function(){}
    var ensureDone=function(){}
    
  	//选中设计
    $("#cuPop").off('click').on("click",function(){
    	var deptType = $(".deptType").val();
    	deptPopup({'unitName':'deptName','unitId':'deptId'},deptType,deptcallback);
  	});
    var deptcallback =function(){
    	console.info("==========deptcallback==========="+designerData.deptId);
    	designerData.deptId = $('#unitId').val();
    	//$('#designerTable').cgetData(true);
    };
    
    designertableinit();
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->