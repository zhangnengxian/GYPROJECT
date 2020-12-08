<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
		<a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 saveBtn">保存</a>
		<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="toolBtn f-r p-b-10  informbtn">
		<a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 hidden informSaveBtn" >确定</a>
	</div>

	<div class="clearboth form-box">
		<input type="hidden" id="businessOrderId" name="businessOrderId">
		<input type="hidden" id="stepId" name="stepId" value="${stepId}">
		<form class="form-horizontal" id="instTasksForm" action="">
			<input type="hidden" id="projId" name="projId" />
			<input type="hidden" id="saveType" name="saveType" />
			<input type="hidden" id="corpId" name="corpId" />
			<input type="hidden" name="tenantId" id="tenantId"/>
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
					<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="projSourceDes">受理来源</label>
				<div>
					<input type="text" class="form-control field-not-editable"  id="projSourceDes" name="projSourceDes"  data-parsley-maxlength="200"/>
				</div>
			</div>
			<div class="form-group col-sm-12">
				<label class="control-label" for="custName">申报单位</label>
				<div>
					<input type="hidden" id="custId" name="custId"/>
					<input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" data-parsley-maxlength="200"/>
					<%--<a id="custPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择申报单位"><i class="fa fa-search"></i></a>--%>
				</div>
			</div>
			<div class="form-group col-sm-12">
				<label class="control-label" for="">工程地点</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="">联系人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="200" />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="">联系电话</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="">燃气公司</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="projectTypeDes">工程类型</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="contributionModeDes">出资方式</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="contractAmount">合同金额</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="contractAmount" name="contractAmount" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">应收首付款</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="arCost" name="arCost" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="">实收首付款</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="receiveAmount" name="receiveAmount" value=""/>
				</div>
			</div>
			<div class="form-group col-md-12 ">
				<label class="control-label" for="meterType">表具型号</label>
				<div>
					<textarea class="form-control input-sm field-editable" id="meterType" name="meterType" data-parsley-maxlength="500" ></textarea>
				</div>
			</div>
            <div class="form-group col-md-12 ">
                <label class="control-label" for="instTaskNote">备注</label>
                <div>
                    <textarea class="form-control input-sm field-editable" id="instTaskNote" name="instTaskNote" data-parsley-maxlength="200" ></textarea>
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

    //参数传false时，表单不可编辑
    $("#instTasksForm").toggleEditState(false);

    //表单样式适应
    $("#instTasksForm").styleFit();

    $(".backReasonshow").addClass("hidden");
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    //trSData.t && trSData.t.cgetDetail('instTasksForm','insttasks/viewInsttasks','',getDetailBack);


    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
        $("#instTasksForm,#scaleTableForm,#scaleTableChangeForm").toggleEditState(false);
        $(".editbtn").addClass("hidden");
        $('#insttasksListTable').cgetData(true);
        detailFlag = false;
    });

    //点击保存
    $(".saveBtn").on("click",function(){
        //要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
        if($("#instTasksForm").parsley().isValid()){
            var content = "确认保存？";
            if(!$("#instTaskNote").val()){
                content = "未填写备注，是否保存？"
            }
            $("body").cgetPopup({
                title: "提示信息",
                content: content,
                accept: doSave,
                chide: false,
                icon: "fa-check-circle"
            });

            //如果通过验证, 则移除验证UI
            $("#instTasksForm").parsley().reset();
        }else{
            //如果未通过验证, 则加载验证UI
            $("#instTasksForm").parsley().validate();
        }
    });
    var doSave = function(){
        //加遮罩
        $(".infodetails").loadMask("正在保存...", 1, 0.5);
        var data=$("#instTasksForm").serializeJson();
        $.ajax({
            type: 'POST',
            url: 'insttasks/saveInstTasks',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
                //取消遮罩
                $(".infodetails").hideMask();
                var content = "数据保存成功！";
                if(data === "false"){
                    content = "数据保存失败！";
                }else if(data === "already"){
                    content = "此信息已被修改，请刷新页面！";
                }else if(data === "true"){
                    $(".editbtn").addClass("hidden");
                    $("#instTasksForm").formReset();
                    $('#instTasksForm').toggleEditState(false);
                    $("#insttasksListTable").cgetData();  //列表重新加载
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
                console.warn("安装任务下达保存失败！");
            }
        });
    }






</script>
<!-- ================== END PAGE LEVEL JS ================== -->