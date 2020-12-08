<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
		<a href="javascript:;" class="btn btn-info btn-sm m-l-5 pushBtn" >推送</a>
		<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="clearboth form-box">
	<input type="hidden" name="post" id="post" value="${post}"/>
	<input type="hidden" name="builderPost" id="builderPost" value="${builderPost}"/>
	<input type="hidden" name="groupLeacerPost" id="groupLeacerPost" value="${groupLeacerPost}"/>
		<form class="form-horizontal" id="igniteForm" data-parsley-validate="true" action="ignite/igniteSave">
			<input type="hidden" name="projId" id="projId"/>
			<input type="hidden" class="accBusRecordId" name="gprojId" id="gprojId"/>
			<input type="hidden" name="igniteId" id="igniteId"/>
			<input type="hidden" name="projLtypeId" id="projLtypeId"/>
			<input type="hidden" name="pushFlag" id="pushFlag"/>
			<input type="hidden" name="stepId" id="stepId"/>
			<input type="hidden" id="uploadFlag1"/>
			<!--  工程信息 -->
			<div class="form-group col-md-6 col-sm-12 ">
				<label class="control-label" for="projNo">工程编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="projName">工程名称</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="projAddr">工程地点</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="projScaleDes">工程规模</label>
				<div>
					<textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200" ></textarea>
				</div>
			</div>
			<div class="form-group col-md-12 col-sm-12">
				<label class="control-label" for="deptName">燃气公司</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"  data-parsley-maxlength="100" />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="projType">工程类型</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projType" name="projType" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="scNo">施工合同号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo" data-parsley-maxlength="20" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="builder">现场代表</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="builder" name="builder" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="buTel">联系电话</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="buTel" name="buTel" data-parsley-maxlength="20" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 ">
				<label class="control-label" for="cuName">施工单位</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"  data-parsley-maxlength="100" />
				</div>
			</div>
			<!--  点火信息 -->
			
			<div class="form-group col-md-6">
				<label class="control-label" for="custName">用户名称</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""/>
				</div>
			</div>
				<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="custName">营业执照</label>
				<div>
					<input type="text" class="form-control input-sm field-editable allText builder" placeholder="营业执照名称--选填" id="businessLicense" name="businessLicense" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="igniteNo">协议号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="igniteNo" name="igniteNo" data-parsley-maxlength="20" value=""/>
				</div>
			</div>   

			<div class="form-group col-md-6 noUser">
				<label class="control-label" for="households">协议户数</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable" id="households"  name="households" data-parsley-maxlength="20" value="">
				</div>
			</div>
			<%--<div class="form-group col-md-6 ">--%>
				<%--<label class="control-label" >计划通气日期</label>--%>
				<%--<div>--%>
					<%--<input type="text" class="form-control input-sm field-not-editable datepicker-default "  id="planGasDate" name="planGasDate"/>--%>
				<%--</div>--%>
			<%--</div>--%>
			<%--<div class="form-group col-md-6 ">--%>
				<%--<label class="control-label" >计划结束日期</label>--%>
				<%--<div>--%>
					<%--<input type="text" class="form-control input-sm field-not-editable datepicker-default "  id="planGasEndDate" name="planGasEndDate"/>--%>
				<%--</div>--%>
			<%--</div>--%>
			<div class="form-group col-md-6 noUser">
				<label class="control-label" for="doHouseholds">安装户数</label>
				<div>
					<input type="text" class=" form-control input-sm field-editable allText groupLeader builder" id="doHouseholds"  name="doHouseholds" data-parsley-maxlength="20" value="">
				</div>
			</div>
			<div class="form-group col-md-6 noUser">
				<label class="control-label" for="igniteHouseholds">点火户数</label>
				<div>
					<input type="text" class=" form-control input-sm field-editable allText groupLeader builder" id="igniteHouseholds"  name="igniteHouseholds" data-parsley-maxlength="20" value="">
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="agent">经办人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="agent" name="agent" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="agentPhone">联系电话</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="agentPhone" name="agentPhone" data-parsley-maxlength="20" value=""/>
				</div>
			</div>
			<div class="form-group col-md-12 ">
				<label class="control-label">交付时间</label>
				<div>
					<input type="text" class="form-control input-sm field-editable datepicker-default allText builder timestamp"  id="deliveryTime" name="deliveryTime"/>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="igniteContent">安装内容</label>
				<div>
					<textarea class="form-control field-editable allText builder" name ="igniteContent" id="igniteContent" rows="3" data-parsley-maxlength="1000" ></textarea>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="remark">备注</label>
				<div>
					<textarea class="form-control field-editable allText builder" name ="remark" id="remark" rows="3" data-parsley-maxlength="1000" ></textarea>
				</div>
			</div>
			 <div class="form-group col-md-6 ">
		        <label class="control-label">上传竣工确认单</label>
		        <div>
		        	<label>
		            	<input type="radio" class="field-editable allText builder" name="uploadFlag" value="1">已上传
		            </label>
		            <label>
		            	<input type="radio" class="field-editable allText builder" name="uploadFlag" value="0" checked>未上传
		            </label>
		        </div>
		    </div>
		    <div class="form-group col-md-6 allSign builder">
				<label class="control-label signature-tool sign-require" for="builderSign">现场代表</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="builderSign" name="builderSign" value="">
					<img class="fieldPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<%--签字信息--%>
			<div class="form-group col-md-6 ">
				<label class="control-label signature-tool sign-require" for="lister">制表人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="lister" name="lister" value="">	
				</div>
			</div>
			
			<div class="form-group col-md-12">
				<label class="control-label" for="treasurerView">财务核对意见</label>
				<div>
					<textarea class="form-control field-editable allText generalManager" name ="treasurerView" id="treasurerView" rows="2" data-parsley-maxlength="200" ></textarea>
				</div>
			</div>
			<div class="form-group col-md-6 allSign contract_manager">
				<label class="control-label signature-tool sign-require" for="treasurer">核对人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="treasurer" name="treasurer" value="">
					<img class="fieldPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-md-6 clearboth allSign viceMinister">
				<label class="control-label signature-tool sign-require" for="manager">部门经理签发</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_4"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="manager" name="manager" value="">
					<img class="fieldPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label">签发日期</label>
				<div>
					<input type="text" class="form-control input-sm field-editable datepicker-default allText viceMinister"  id="managerDate" name="managerDate"/>
				</div>
			</div>
			<div class="form-group col-md-12 noUser">
				<label class="control-label" for="custServiceView">客服核对情况</label>
				<div>
					<textarea class="form-control field-editable allText contract_manager" name ="custServiceView" id="custServiceView" rows="2" data-parsley-maxlength="200" ></textarea>
				</div>
			</div>
			<div class="form-group col-md-6 noUser allSign contract_manager">
				<label class="control-label signature-tool sign-require" for="custServiceSign">核对人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_5"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="custServiceSign" name="custServiceSign" value="">
					<img class="fieldPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label signature-tool" for="custSign">客户确认</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_6"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="custSign" name="custSign" value="">
					<img class="fieldPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
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
    $("#igniteForm").toggleEditState(false);
    //表单样式适应
    $("#igniteForm").styleFit();
    $(".fieldPrincipal-a").handleSignature();
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $(".editbtn").addClass("hidden");

    //查右侧工程详述
    trSData.t && trSData.t.cgetDetail('igniteForm','ignite/viewIgnite?stepId='+getStepId(),'',queryDetailBack);

    var igniteSaveBack = function(){
        //清空表单
//        $(".editbtn").addClass("hidden");
//        $("#igniteForm input,#igniteForm textarea").val('');
//        $("#igniteTable").cgetData(true);
	}

    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").off("click").on("click",function(){
    	if($('input:radio[name="uploadFlag"]:checked').val()!='1'){
        	  alertInfo("请确认是否已上传竣工确认单！");
    		  return false;
         }
    	$("#stepId").val(getStepId());
        //$("#igniteForm").cformSave("igniteTable",igniteSaveBack);
    	$(".infodetails").loadMask("正在保存...", 1, 0.5);
    	var data=$("#igniteForm").serializeJson();
    	$.ajax({
            type: 'POST',
            url: 'ignite/igniteSave',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
            	//取消遮罩
            	$(".infodetails").hideMask();
            	if(data=='no'){
                	alertInfo("请确认是否已上传竣工确认单！");
                }else if(data=='false'){
                	alertInfo("点火单签订推送失败！");
                }else{
                	alertInfo("点火单签订推送成功！");
                	$("#igniteTable").cgetData(true,igniteBack);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
            	//取消遮罩
            	$(".infodetails").hideMask();
                console.warn("点火签订保存失败！");
            }
        });
    	
    	
    	
    	
    });
    /**点击右侧维护区 推送 按钮时*/
    $(".pushBtn").off("click").on("click",function(){
    	
        $("#pushFlag").val(true);
        if($('input:radio[name="uploadFlag"]:checked').val()!='1'){
      	  alertInfo("请确认是否已上传竣工确认单！");
  		  return false;
        }
        $("#stepId").val(getStepId());
        
        var t = $("#igniteForm");
      //开启表单验证
        if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
            //防止多次提交
            if(response){
                response.abort();
            }
            t.parent().parent().loadMask("正在保存...", 1, 0.5);
            
            //表单序列化获取json字符串
            var data = t.serializeJsonString();
            var response = $.ajax({
                url: "ignite/igniteSave",
                type: "POST",
                timeout : 15000,
                contentType: "application/json;charset=UTF-8",
                data: data,
                success: function (data) {
                	t.parent().parent().hideMask();
                    if(data=='no'){
                    	alertInfo("请确认是否已上传竣工确认单！");
                    }else if(data=='false'){
                    	alertInfo("点火单签订推送失败！");
                    }else{
                    	alertInfo("点火单签订推送成功！");
                    	$("#igniteTable").cgetData(true,igniteBack);
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	//取消遮罩
                	t.parent().parent().hideMask();
                    console.warn("点火单签订推送失败 ！");
                }
            });
            //如果通过验证, 则移除验证UI
            t.parsley().validate();
        } else {
            //如果未通过验证, 则加载验证UI
            t.parsley().validate();
        };
        //$("#igniteForm").cformSave("igniteTable",igniteSaveBack);
    });

    //放弃
    $(".cancelBtn").on("click",function(){
        //清空表单
        $(".editbtn").addClass("hidden");
        $("#igniteForm input,#igniteForm textarea").val('');
        $("#igniteTable").cgetData(true);
    });

</script>
<!-- ================== END PAGE LEVEL JS ================== -->