<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10 ">
		<a href="javascript:;" class="btn btn-query btn-sm m-l-7 dispatchBtn" >派工</a>
	</div>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="designDispatchForm" data-parsley-validate="true" action="">
			<input type="hidden" id="designerId" name="designerId"/>
			<input type="hidden" id="designer" name="designer"/>
			<input type="hidden" id="projId" name="projId"/>
			<input type="hidden" id="isFlag" name="isFlag" value="${isFlag}"/><!--true:走流程；flase：不走流程-->
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
				<label class="control-label" >联系人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="200" />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 noUser">
				<label class="control-label" >联系电话</label>
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
				<label class="control-label" >工程类型</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >出资方式</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >业务部门</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >受理人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="accepter" name="accepter" value=""/>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="projectRemark">受理备注</label>
				<div>
					<textarea class="form-control field-editable" name="projectRemark" id="projectRemark" rows="2" cols="" data-parsley-maxlength="200"></textarea>
				</div>
			</div>
		</form>
	</div>

	<div>
		<h4 class="m-t-20"><strong>设计员</strong></h4>
	</div>
	<table id="designerTable" class="table table-hover table-bordered nowrap" width="100%">
		<thead>
		<tr >
			<th>人员ID</th>
			<th>名称</th>
			<th>任务</th>
		</tr>
		</thead>
	</table>
</div>

<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    $("#designDispatchForm").toggleEditState().styleFit();

    //查右侧工程详述
    //trSData.t&&trSData.t.cgetDetail('designDispatchForm','designDispatchWorkers/queryProjectDetail','',detailBack);

    //点击派工按钮
    $(".dispatchBtn").on("click",function(){

        var len=$("#designerTable").find("tr.selected").length;
        if(len<1){
            $("body").cgetPopup({ title: '提示',  content: '请选择设计员！', accept: popClose }); return false;
        }
        var designer= $("#designer").val();
		$("body").cgetPopup({
			title: "提示信息",
			content: '确认要派工给 <i class="fa fa-user"></i> '+designer+" 吗？",
			accept: function(){
				var data=$("#designDispatchForm").serializeJson();
				$.ajax({
					type: 'POST',
					url: 'designDispatchWorkers/updateProject',
					contentType: "application/json;charset=UTF-8",
					data: JSON.stringify(data),
					success: function (data) {
						var content = "派工成功！";
						if(data === "false"){
							content = "派工失败！";
						}else if(data === "true"){
							$("#designDispatchForm").formReset();
							$("#designDispatchForm").toggleEditState();
							$("#designDispatchTable").cgetData(true,designDispatchCallBack);
							updateDesignerBack();
						}
						var myoptions = {
							title: "提示信息",
							content: content,
							accept: popClose,
							chide: true,
							newpop: 'new',
							icon: "fa-check-circle"
						};
						$("body").cgetPopup(myoptions);
					},
					error: function (jqXHR, textStatus, errorThrown) {
						console.warn("派工失败！");
					}
				});
			},
			icon: "fa-check-circle",
			newpop: 'new'
		});
    });


    designertableinit();

</script>
