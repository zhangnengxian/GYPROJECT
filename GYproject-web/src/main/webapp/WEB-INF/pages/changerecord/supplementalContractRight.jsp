<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn1">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="agreementDetailform"  data-parsley-validate="true" action="">
    		<input type="hidden" name="projId" id="projId"/>
       		<input type="hidden" name="conId" id="conId"/>
       		<input type="hidden" name="scId" id="scId"/>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="conNo">合同编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="conNo" name="conNo" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
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
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200" value=''></textarea>
	            </div>
            </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custName">甲方客户名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="custPhone">甲方联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custLegalRepresent">甲方法定代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custLegalRepresent" name="custLegalRepresent" data-parsley-maxlength="20" value="" />
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custEntrustRepresent">甲方委托代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custEntrustRepresent" name="custEntrustRepresent" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="custAddr">甲方地址</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custAddr" name="custAddr" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="gasComp">乙方燃气公司</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasComp" name="gasComp" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasCompPhone">乙方联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasCompPhone" name="gasCompPhone" data-parsley-maxlength="13" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="gasComPlegalRepresent">乙方法定代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasComPlegalRepresent" name="gasComPlegalRepresent" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasCompEntrustRepresent">乙方委托代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasCompEntrustRepresent" name="gasCompEntrustRepresent" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-12">
		        <label class="control-label" for="gasCompAddr">乙方地址</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasCompAddr" name="gasCompAddr" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="conScope">承包范围</label>
	            <div>
	                <textarea class="form-control field-editable" name ="conScope" id="conScope" rows="3" data-parsley-maxlength="200"></textarea>
	            </div>
            </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="qualityStandar">质量标准</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="qualityStandar"  name="qualityStandar" data-parsley-maxlength="50" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="totalDays">总日历天数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="totalDays"  name="totalDays" data-parsley-maxlength="20" value="">
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="adjustment">图纸编号</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="drawingNo"  name="drawingNo" data-parsley-maxlength="50" value="">
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="adjustment">调整事项</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="adjustment"  name="adjustment" data-parsley-maxlength="50" value="">
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="addition">其他约定</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="addition"  name="addition" data-parsley-maxlength="50" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scAmount">协议价款</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="scAmount"  name="scAmount" data-parsley-maxlength="100" value="" data-parsley-required="true">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="conAgent">经办人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="conAgent"  name="conAgent" data-parsley-maxlength="20" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default timestamp" id="signDate"  name="signDate" data-parsley-maxlength="100" value="" >
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="remark">备注</label>
	            <div>
	                <textarea class="form-control field-editable" name ="remark" id="remark" rows="3" data-parsley-maxlength="200"></textarea>
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
    hiddenOpt(state);
    //表单样式适应
    $("#agreementDetailform").toggleEditState().styleFit();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
   
    
    
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
        if($("#agreementDetailform").parsley().isValid()){
        	var data=$("#agreementDetailform").serializeJson();
        	    data.cmId=$("#cmId").val();
        	    data.mcType=$("#mcType").val();
        	$.ajax({
                type: 'POST',
                url: 'supplementalContract/saveSupplementalContract',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "补充协议编号已存在，请修改！";
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
                    console.warn("协议签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#agreementDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#agreementDetailform").parsley().validate();
        }
        
    }); 
    
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	
    	 trSData.t.cgetDetail('agreementDetailform','changeRecord/viewContract','');   
    	//移除验证
    	 $("#agreementDetailform").parsley().reset();
    });

</script>
<!-- ================== END PAGE LEVEL JS ================== -->