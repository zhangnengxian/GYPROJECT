<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editBtn hidden">
       <a href="javascript:;" class="btn btn-query btn-sm m-l-7 dispatchBtn" >派工</a>
	</div>
	<div class="clearboth form-box">
		<input class="deptType" type="hidden" name="deptType"  value="${deptType}"/>
		<form class="form-horizontal" id="marketDispatchForm" data-parsley-validate="true" action="">
			<input type="hidden" name="duId"  id="duId" value=""/>
			<input type="hidden" name="deptId"  id="deptId" value=""/>
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
        	<div class="form-group col-md-6">
            	<label class="control-label" for="projSourceDes">受理来源</label>
            	<div>
		            <input type="text" class="form-control field-not-editable"  id="projSourceDes" name="projSourceDes" rows="2" data-parsley-maxlength="200"/>
		        </div>
        	</div>
        	<div class="form-group col-md-12 noUser">
            	<label class="control-label" for="custName">申报单位</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="custName" name="custName"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 col-sm-12 noUser">
		        <label class="control-label" for="">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="200" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 noUser">
		        <label class="control-label" for="">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" />
		        </div>
		    </div>
        	<div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
		        </div>
		    </div>
        	<input type="hidden" name="projId" id="projId"/>
        	<input type="hidden" name="market" id="market"/>
        	<input type="hidden" name="marketId" id="marketId"/>
		</form>
    </div>
	<div>
    <h4 class="m-t-20"><strong>市场营销员</strong></h4>
    </div>
    <table id="marketTable" class="table table-hover table-bordered nowrap" width="100%">
        <thead>
            <tr>
            	<th>staff_id</th>
            	<th>名称</th>
                <th>待派工任务</th>
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
    $("#marketDispatchForm").toggleEditState().styleFit();
    
    //查右侧工程详述
    trSData.t&&trSData.t.cgetDetail('marketDispatchForm','marketDispatch/viewDispatchResult','',detailBack);
   
    //点击派工按钮
    $(".dispatchBtn").on("click",function(){
    	var radioVal = $("#marketDispatchForm input[type='radio']:checked").val();
    	if($("#marketDispatchForm").parsley().isValid()){
       		    var len=$("#marketTable").find("tr.selected").length;
       		    if(len>0){
       		    	var market = $("#marketTable").find("tr.selected td:eq(0)").text();
       		    	var json = $("#marketTable").DataTable().rows( '.selected' ).data();
       		    	$("#market").val(market);    //选择的踏勘员
       		    	var marketId=json[0].staffId;
       		        $("body").cgetPopup({
	                 	    title: "提示信息",
	                    	content: '确认要派工给 <i class="fa fa-user"></i> '+market+" 吗？",
	                    	accept: function(){
	                 		var data=$("#marketDispatchForm").serializeJson();
	                 		data.marketId=marketId;
	           	        	$.ajax({
	           	                type: 'POST',
	           	                url: 'marketDispatch/updateProject',
	           	                contentType: "application/json;charset=UTF-8",
	           	                data: JSON.stringify(data),
	           	                success: function (data) {
	           	                	var content = "派工成功！";
	           	                	if(data === "false"){
	           	                		content = "派工失败！";
	           	                	}else if(data === "true"){
	           	                		$("#marketDispatchForm").formReset();
	           	                		$("#marketDispatchForm").toggleEditState();
	           	                	 	$("#marketDispatchTable").cgetData(true,marketDispatchCallBack);
	           	                	 	updateMarketBack();
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
	           	                    console.warn("市场派工区派工失败！");
	           	                }
	           	            }); 
	                 	},
	                 	icon: "fa-check-circle",
	                 	newpop: 'new'
                 });
       		    
       		    }else{					//未选设计人员
       		    	$("body").cgetPopup({
       					title: '提示',
       					content: '请选择踏勘人员！',
       					accept: ensureDone
       		    	});
       		    }
    		$("#marketDispatchForm").parsley().reset();
    		deptcallback();
    	}else{
    		$("#marketDispatchForm").parsley().validate();
    	}  
    })
    
    
    //点击确定
    var sureDone=function(){};
    var ensureDone=function(){};
    
  	//选中设计
    $("#cuPop").off('click').on("click",function(){
    	var deptType = $(".deptType").val();
    	deptPopup({'unitName':'deptName','unitId':'deptId'},deptType,deptcallback);
  	});
    var deptcallback =function(){
        marketData.deptId = $('#unitId').val();
    };

    marketTableinit();
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->