<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel-heading">
	<div class="panel-heading-btn">
		<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
		<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
		<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	</div>
	<!--  <h4 class="panel-title">验收信息</h4> -->
	<ul class="nav nav-tabs ">
		<li class="active"><a href="#jointAcceptance_List" data-toggle="tab"id ="jointAcceptanceList">一站式验收列表</a></li>
		<li class=""><a href="#jointAcceptance_info" data-toggle="tab" id="jointAcceptanceInfo">签字区</a></li>
	</ul>
</div>
<div class="panel-body " id="box">
	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
	<div class="tab-content">
		<div class="tab-pane fade active in btn-top active" id="jointAcceptance_List" >
			<table class="table table-hover table-striped table-bordered nowrap" id="oneStopAcceptanceListTable" width="100%">
				<thead>
					<tr>
						<th>验收ID</th>
						<th>验收日期</th>
						<th>开通意见</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tab-pane fade in btn-top infodetails" id="jointAcceptance_info">
			<div class="toolBtn f-r p-b-10 editbtn">
				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton saveBtn">保存</a>
				<!-- <a href="javascript:;" class="btn btn-info btn-sm m-l-5 pushButton entBtn" >保存</a> -->
				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
				<a href="javascript:;" class="btn  btn-primary btn-sm m-l-5 hidden get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>
			</div>
			<div class="clearboth form-box">
				<form class="form-horizontal" id="oneStopAcceptanceForm" action="" data-parsley-validate="true">
					<input type="hidden" name="projId" id="projId"/>
					<input type="hidden" name="jaId" id="jaId"/>
					<input type="hidden" name="alarmProj" id="alarmProj"/>
					<input type="hidden" name="version"/>
					<div class="form-group col-md-6 ">
						<label class="control-label" for="projNo">工程编号</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" value=""/>
						</div>
					</div>
					<div class="form-group col-md-6 ">
						<label class="control-label" for="scNo">分合同号</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo" value=""/>
						</div>
					</div>

					<div class="form-group col-md-6 ">
						<label class="control-label" for="projName">工程名称</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value=""/>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label" for="projAddr">工程地点</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label" for="projectTypeDes">工程类型</label>
						<div>
							<input type="text" class="form-control field-not-editable"  id="projectTypeDes" name="projectTypeDes"  value=""/>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label" for="cuName">施工单位</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" value=""/>
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="control-label signature-tool">验收日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable datepicker-default"  id="jaDate" name="jaDate" data-parsley-required="true"/>
						</div>
					</div>
					<!-- <div class="form-group col-xs-12 selfcheckformtitle"><i class="fa fa-angle-double-right"></i> 现场代表 </div>
                    -->
					<div class="form-group col-md-12">
						<label class="control-label" for="fieldPrincipalDevice"> 施工管理组</label>
						<div>
							<textarea  class="form-control field-editable" name ="fieldPrincipalDevice" id="fieldPrincipalDevice" data-parsley-maxlength="100" rows="2"></textarea>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool" for="fieldPrincipalSign">验收人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_5"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="fieldPrincipalSign" name="fieldPrincipalSign" value="">
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6 hidden selectSignDate">
						<label class="control-label signature-tool">签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable datepicker-default"  id="fieldPrincipalSignDate" name="fieldPrincipalSignDate" />
						</div>
					</div>
					<!--  <div class="form-group col-xs-12 selfcheckformtitle"><i class="fa fa-angle-double-right"></i></div>
                   -->
					<div class="form-group col-md-12">
						<label class="control-label" for="transCompanyDevice"> 公建站</label>
						<div>
							<textarea  class="form-control field-editable" name ="transCompanyDevice" id="transCompanyDevice" data-parsley-maxlength="100" rows="2"></textarea>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool" for="transCompanySign">验收人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_2"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="transCompanySign" name="transCompanySign" value="">
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6 hidden selectSignDate">
						<label class="control-label signature-tool" >签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable datepicker-default"  id="transCompanySignDate" name="transCompanySignDate" />
						</div>
					</div>
					
					<div class="form-group col-md-12">
						<label class="control-label" for="marketDevDevice">验收组</label>
						<div>
							<textarea  class="form-control field-editable" name ="marketDevDevice" id="marketDevDevice" data-parsley-maxlength="100" rows="2"></textarea>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool" for="marketDevSign">验收人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_10"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="marketDevSign" name="marketDevSign" value="">
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6 hidden selectSignDate">
						<label class="control-label signature-tool" >签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable datepicker-default"  id="marketDevSignDate" name="marketDevSignDate" />
						</div>
					</div>
					
					<div class="form-group col-md-12">
						<label class="control-label" for="techEquipmentDevice">安技组</label>
						<div>
							<textarea  class="form-control field-editable" name ="techEquipmentDevice" id="techEquipmentDevice" data-parsley-maxlength="100" rows="2"></textarea>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label signature-tool" for="techEquipmentSign">验收人</label>
						<div>
							<a href="javascript:;" class="btn btn-sm btn-white fieldPrincipal-a" id="signBtn_8"><i class="fa fa-pencil"></i></a>
							<input type="hidden" class="sign-data-input" id="techEquipmentSign" name="techEquipmentSign" value="">
							<img class="fieldPrincipal" alt="" src="" style="height: 30px">
							<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
						</div>
					</div>
					<div class="form-group col-md-6 hidden selectSignDate">
						<label class="control-label signature-tool">签字日期</label>
						<div>
							<input type="text" class="form-control input-sm field-editable datepicker-default"  id="techEquipmentSignDate" name="techEquipmentSignDate" />
						</div>
					</div>
					
					<div class="form-group col-md-12">
						<label class="control-label signature-tool" for="jaClum">开通意见</label>
						<div>
							<textarea rows="2" class="form-control input-sm field-editable"  id="jaClum" name="jaClum" data-parsley-maxlength="200"></textarea>
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label" for="custCenterDevice">中心领导意见</label>
						<div>
							<textarea rows="2" class="form-control field-editable" name ="custCenterDevice" id="custCenterDevice" data-parsley-maxlength="100"></textarea>
						</div>
					</div>
				</form>
			</div>
		</div>
		</div>
	</div>
</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    //表单样式适应
    $("#oneStopAcceptanceForm").toggleEditState(true).styleFit();
//    changeAText();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    //不可编辑
    $("#oneStopAcceptanceForm").toggleEditState(false);

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    trSData.t && trSData.t.cgetDetail('oneStopAcceptanceForm','','',queryBack);
    if($.fn.DataTable.isDataTable('#oneStopAcceptanceListTable')){
        $('#oneStopAcceptanceListTable').cgetData(false);
    }else{
    	handleOneStopAcceptanceList();
    }

	var acceptPush = function () {
        //表单验证
        if($("#oneStopAcceptanceForm").parsley().isValid()){
            //验证必签签字是否已签
            var signtools =$('#oneStopAcceptanceForm').find(".signature-tool"),
                stl = signtools.length,
                sBlank = 0;
            for(var i=0; i<stl; i++){
                var tstool = signtools.eq(i).next("div").find("a.btn-white"),
                    tsinput = tstool.siblings(".sign-data-input");
                if(!tsinput.val() || tsinput.val().length < 312){
                    tstool.addClass("required-sign");
                    sBlank++;
                }
            }
            //json字符串
            var data=$("#oneStopAcceptanceForm").serializeJson();
            $.ajax({
                type: 'POST',
                url: 'oneStopAcceptance/entOneStopAcceptance',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                    var content = "数据保存成功！";
                    if(data === "false"){
                        content = "数据保存失败！";
                    }else if(data === "true"){
                        $("#oneStopAcceptanceForm").formReset();
                        $("#oneStopAcceptanceTable").cgetData(true);  //列表重新加载
                        jointSearchData.projId="-1";
                        $("#oneStopAcceptanceListTable").cgetData();  //列表重新加载
                        $(".editbtn").addClass("hidden");
                    }
                    var myoptions = {
                        title: "提示信息",
                        content: content,
                        accept: popClose,
                        chide: true,
                        icon: "fa-check-circle",
                        newpop: 'new'
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("一站式验收记录保存失败！");
                }
            });
            //如果通过验证, 则移除验证UI
            $("#oneStopAcceptanceForm").parsley().reset();
        }else{
            //如果未通过验证, 则加载验证UI
            $("#oneStopAcceptanceForm").parsley().validate();
        }
    }

    var acceptDone = function(){
        var len = $('#oneStopAcceptanceListTable').DataTable().ajax.json().data ? $('#oneStopAcceptanceListTable').DataTable().ajax.json().data.length : $('#oneStopAcceptanceListTable').DataTable().ajax.json().length;
		if(len<1){
            alertInfo('请先完成验收记录！');
        }else{
            var myoptions = {
                title: "提示信息",
                content: "确认一站式验收已完成？",
                accept: acceptPush,
                chide: true,
                icon: "fa-check-circle",
                newpop: 'new'
            }
            $("body").cgetPopup(myoptions);
		}
    }

    //放弃
    $(".cancelBtn").on("click",function(){
        //$('#oneStopAcceptanceForm').formReset();
        $("#oneStopAcceptanceTable").cgetData();                                  //列表重新加载
        $("#oneStopAcceptanceListTable").cgetData();                              //列表重新加载
        $(".clear-sign").click();                                               //签名清空
        $('#oneStopAcceptanceForm').toggleEditState(false);                       //切换不可编辑状态
        $('.editbtn').addClass('hidden');										//维护按钮隐藏
        $("#jointAcceptanceList").tab('show');
        //如果通过验证, 则移除验证UI
        //$("#oneStopAcceptanceForm").parsley().validate();
    });

    var SaveCallBack = function(data){
        if(data === "true"){
            //表单不可编辑
            $("#oneStopAcceptanceForm").toggleEditState(false);
            //按钮隐藏
            $(".editbtn").addClass("hidden");
            $('#oneStopAcceptanceTable').cgetData(false);
        }
    };
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
        //$('#oneStopAcceptanceForm').cformSave('oneStopAcceptanceListTable',SaveCallBack);
        if($("#oneStopAcceptanceForm").parsley().isValid()){
    		//加遮罩
    		$(".infodetails").loadMask("正在保存...", 1, 0.5);
    		//json字符串
        	var data=$("#oneStopAcceptanceForm").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'oneStopAcceptance/saveJoint',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "true"){
                		SaveCallBack(data);
                	}else if(data === "already"){
                   		content = "此信息已被修改，请刷新页面！";
                   	}
                	var myoptions = {
                        	title: "提示信息",
                        	content: content,
                        	accept: sureClose,
                        	chide: true,
                        	icon: "fa-check-circle"
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                    console.warn("联合验收保存失败！");
                }
            });
    		//如果通过验证, 则移除验证UI
        	$("#oneStopAcceptanceForm").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#oneStopAcceptanceForm").parsley().validate();
        }
    });

    $(".fieldPrincipal-a").handleSignature();


</script>
<!-- ================== END PAGE LEVEL JS ================== -->