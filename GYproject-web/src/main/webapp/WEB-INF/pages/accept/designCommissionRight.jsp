<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 commBtnRight" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancleBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="designCommissionform" data-parsley-validate="true" action="">
    		<input type="hidden" id="projId" name="projId" />
    		<input type="hidden" id="duId" name="duId" />            <!--  设计院Id -->
    		<input type="hidden" id="designer" name="designer" />    <!--  设计人 -->
       		<div class="form-group col-md-12">
		    	<label class="control-label" for="">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">设计院</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="duName" name="duName" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">委托编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable "  data-parsley-required="true" id="ocoNo" name="ocoNo" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">委托日期</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable datepicker-default"  data-parsley-required="true" id="ocoDate" name="ocoDate" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
      			<label class="control-label" for="">用气规模</label>
           		<div>
           			<input type="hidden" class="projLtypeId" name="projLtypeId"/>
		             <c:forEach var="enums" items="${projLtype}">
	             		 <label>
			            	<input type="checkbox" class="field-not-editable" name="projLtype"  class="" value="${enums.value}"/>${enums.message}
			            </label>
		             </c:forEach>
		        </div>
			</div>
		</form>
    </div>
    <form action="" id="designCommForm">
	    <table id="scaleCommTable" class="table table-striped table-bordered nowrap" width="100%"">
	       	<thead>
	            <tr>
	                <th width="50">细类</th>
	                <th width="50">吨位</th>
	                <th width="50">座数</th>
	                <th width="50">台数</th>
	                <th width="50">户数</th>
	                <th width="50">预计用量(m³/h)</th>
<!-- 	                <th width="50"></th> -->
	            </tr>
	          </thead>
		</table>
	</form>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    //参数传false时，表单不可编辑
    $("#designCommissionform").toggleEditState(false);
    $("#designCommissionform input[type='radio']").attr("disabled",true);
    //表单样式适应
    $("#designCommissionform").styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    
	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $('#ocoNo').val($('#projNo').val());
    
    trSData.t && trSData.t.cgetDetail('designCommissionform','','',scaleDetailRefresh);
    
    /**点击右侧维护区 保存 按钮时*/
    $(".commBtnRight").on("click",function(){
    	if($("#designCommissionform").parsley().isValid()){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	//$("#designCommissionform").cformSave('designCommissionTable',savedesignInfoBack,true);
    	
    		var data=$("#designCommissionform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'designCommission/saveDesignCommission',
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "委托编号已存在，请修改！";
                	}else if(data === "true"){
                		$(".projLtypeId").val('');
                		$("input[name='projLtype']").removeClass("field-not-editable");
                		$("#designCommissionform").toggleEditState(true);
                		$("#designCommissionform")[0].reset();
                		
                		$("input[name='projLtype']").attr("checked",false);
                		$("input[name='projLtype']").addClass("field-not-editable");
                		$("#designCommissionform").toggleEditState(false);
                		$("#designCommissionTable").cgetData(true,designTableCallBack);  //列表重新加载
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
                    console.warn("委托区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#designCommissionform").parsley().reset();
    	}else{
    		//如果未通过验证, 则加载验证UI
        	$("#designCommissionform").parsley().validate();
    	}
    	scaleDetailRefresh();
    }); 
    
//     var savedesignInfoBack=function(){
//     	//清空输入信息
//     	$("#designCommissionform input").val("");
//     	//列表重新加载
//     	$("#designCommissionTable").cgetData();
//     	//不可编辑
//     	$("#designCommissionform").toggleEditState(false);
//     	//隐藏按钮
//     	$(".editbtn").addClass("hidden"); 
    	
//     }
    
    var commissionCallBack = function(){
    	if(designCommissionMytable.ajax.json.length<1){
	   		//清空右侧记录
	   		$("#designCommForm").addClass("hidden");
	   		$("#designCommissionform")[0].reset();
   	 	}
    }
    
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancleBtn").on("click",function(){
    	 $("#designCommissionform,#designCommForm").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["designCommissionTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    });
    /**监听用气规模*/
    $("input[name='projLtypeId']").on('change',function(){
    	scaleDetailRefresh();
    });
    if(!trSData.designCommissionTable.t){
    	detailSearchData.projId = "-1";
    	sacletableinit();
    }
    //scaleCommTableInit();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->