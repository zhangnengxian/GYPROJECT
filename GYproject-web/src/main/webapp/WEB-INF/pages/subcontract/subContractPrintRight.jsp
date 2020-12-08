<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="subContractDetailform" data-parsley-validate="true" action="">
       		<input type="hidden" id="projId" name="projId"/>
       		<input type="hidden" id="scId" name="scId"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="scNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="scNo" name="scNo"  data-parsley-required="true" value=""/>
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
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		        	<textarea class="form-control input-sm field-not-editable" name="projScaleDes" rows="" cols="" data-parsley-maxlength="200"></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="managementOffice">施工单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="managementOffice" name="managementOffice" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    
		    
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projCompDirector">甲方委托代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projCompDirector" name="projCompDirector" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projCompPm">甲方项目经理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projCompPm" name="projCompPm" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasComLegalRepresent">甲方代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasComLegalRepresent" name="gasComLegalRepresent" data-parsley-maxlength="100"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="gasComPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasComPhone" name="gasComPhone" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="cuName">分包单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" data-parsley-maxlength="100"  value="" />
		        	<a id="cuPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择分包单位"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuLegalRepresent">乙方法定代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuLegalRepresent" name="cuLegalRepresent" data-parsley-maxlength="100"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuDirector">乙方委托代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuDirector" name="cuDirector" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuPm">乙方项目经理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuPm" name="cuPm" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cuPmPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuPmPhone" name="cuPmPhone" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="certificationName">资格证名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="certificationName" name="certificationName" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="certificationNo">资格证编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="certificationNo" name="certificationNo" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="scScope">承包范围</label>
	            <div>
	                <textarea class="form-control field-editable" name ="scScope" id="scScope" rows="3" data-parsley-maxlength="200"></textarea>
	            </div>
            </div>
            <div class="form-group col-md-6">
		        <label class="control-label" for="scType">承包方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="scType" name="scType" data-parsley-maxlength="100" data-parsley-required="true" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scPlannedTotalDays">计划天数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="scPlannedTotalDays"  name="scPlannedTotalDays" data-parsley-maxlength="10"  value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scPlannedStartDate">开工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="scPlannedStartDate"  name="scPlannedStartDate" data-parsley-maxlength="100" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scPlannedEndDate">竣工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="scPlannedEndDate"  name="scPlannedEndDate" data-parsley-maxlength="100" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="qualityStandar">质量标准</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="qualityStandar" name="qualityStandar" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scAmount">协议价款</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable fixed-number"  id="scAmount" name="scAmount" data-parsley-maxlength="100" value=""  data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scSignDate">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="scSignDate"  name="scSignDate" data-parsley-maxlength="100" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="conAgent">经办人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="conAgent" name="conAgent" data-parsley-maxlength="100" value=""/>
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
    $('.infodetails').hideMask();
    $('.editbtn').addClass('hidden');
    //表单样式适应
    $('#subContractDetailform').toggleEditState().styleFit();
    
  	//查右侧工程详述
    trSData.t&&trSData.t.cgetDetail('subContractDetailform','','',subContractPrintBack); 
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    /**点击右侧维护区 保存 按钮时*/
    $('.saveBtn').on('click',function(){
        if($('#subContractDetailform').parsley().isValid()){
        	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
        	var data=$('#subContractDetailform').serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'subContractPrint/saveSubContractPrint',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	if(data === "false"){
                		alertInfo("数据保存失败！");
                	}else if(data ===  "exist"){
                		alertInfo("合同编号已存在，请修改！");
                	}else if(data === "true"){
                		var myoptions = {
                            	title: "提示信息",
                            	content: "数据保存成功！",
                            	accept: saveDone,
                            	chide: true,
                            	icon: "fa-check-circle"
                        }
                        $("body").cgetPopup(myoptions);
                		
                	}else if(data === "not update"){
                		alertInfo("该协议的价款已结清，不能修改协议价款!");
                	}else{
                		//console.log("arId..data:"+data);
                		//弹出收付流水确认屏 
             			var url = "#constructContract/accrualsRecordPopPage?arId="+data;
             			var popoptions = {
             				title: '应付流水',
             				content: url,
             				chide:true,
             				nocache:true,
             				accept: accrualsRecordDone
             			};
             			//加载弹屏
             			$("body").cgetPopup(popoptions);
                	}
                	
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("分包合同签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#subContractDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#subContractDetailform").parsley().validate();
        }
        
    }); 
    
	var accrualsRecordDone = function(){
		
		$("#subContractDetailform input").val('');
		$("#subContractDetailform textarea").val('');
		$("#subContractPrintTable").cgetData(true,cgetDataCallBack);           //列表重新加载
		
    }
	
	var cgetDataCallBack = function(){
		alertInfo('数据保存成功！');
	}
	
    var saveDone = function(){
    	$("#subContractDetailform input").val('');
		$("#subContractDetailform textarea").val('');
    	$("#subContractPrintTable").cgetData(true);           //列表重新加载
    }
    
    //放弃
    $(".cancelBtn").on("click",function(){
    	$("#subContractDetailform input").val('');
    	trSData.t.cgetDetail('subContractDetailform','','',subContractPrintBack); 
    	$("#subContractDetailform").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["subContractPrintTable"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#subContractDetailform").parsley().reset();
    });
 	
    var cuUnitTypeId=$('#cuUnitTypeId').val();
    //选中分包单位
    $("#cuPop").off('click').on("click",function(){
		businessPartnersPopup({
    		'cuName':'unitName',
    		'cuDirector':'unitDirector'
    	},cuUnitTypeId)

  	});
    
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->