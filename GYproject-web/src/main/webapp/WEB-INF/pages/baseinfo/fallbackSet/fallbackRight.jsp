<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-15  editbtn">
        <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
    </div>

    <div class="clearboth form-box">
        <form class="form-horizontal" id="fallBackForm" action="fallback/saveUpdateData">
            <input type="hidden" name="mbsId" id="mbsId"/>
            <input type="hidden" name="backStepId" id="backStepId"/>
            <input type="hidden" name="backStepDes" id="backStepDes"/>
            <input type="hidden" name="projectTypeDes" id="projectTypeDes"/>
            <div class="form-group col-sm-12 col-md-12">
                <label class="control-label" for="corpId">分公司</label>
                <div>
                    <select id="corpId" name="corpId" class="form-control input-sm field-editable">
                        <option value="-1">--请选择--</option>
                        <c:forEach var="enums" items="${corp}">
                            <option value="${enums.deptId}" >${enums.deptName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group col-sm-12 col-xs-12">
                <label class="control-label" for="">工程类型</label>
                <div>
                    <select class="form-control input-sm field-editable" id="projectType"  name="projectType" >
                        <option value="-1">--请选择--</option>
                        <c:forEach var="enums" items="${projType}">
                            <option value="${enums.projTypeId}">${enums.projConstructDes}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group col-sm-12 col-xs-12">
                <label class="control-label" for="contributionCode">出资方式</label>
                <div>
                    <select class="form-control input-sm field-editable" id="contributionCode"  name="contributionCode" >
                        <option value="-1">--请选择--</option>
                        <c:forEach var="enums" items="${contributionMode}">
                            <option value="${enums.id}">${enums.contributionDes}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group col-sm-12 col-xs-12">
                <label class="control-label" for="menuId">当前步骤</label>
                <div>
                    <select class="form-control input-sm field-editable" id="menuId"  name="menuId" >
                        <option value="-1">--请选择--</option>
                        <c:forEach var="enums" items="${stepId}">
                            <option value="${enums.value}">${enums.message}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group col-sm-12 col-xs-12">
                <label class="control-label" for="">预结算有关</label>
                <div>
                    <select class="form-control input-sm field-editable" id="flag"  name="flag" >
                        <option value="-1">--请选择--</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
            <div class="form-group col-sm-12 col-xs-12">
                <label class="control-label" for="">是否审核</label>
                <div>
                    <select class="form-control input-sm field-editable" id="isAudit"  name="isAudit" >
                        <option value="-1">--请选择--</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
            <div class="form-group col-sm-12 col-xs-12">
                <label class="control-label" for="">操作步骤</label>
                <div>
                    <select class="form-control input-sm field-editable" id="stepId"  name="stepId" >
                        <option value="-1">--请选择--</option>
                        <c:forEach var="enums" items="${stepId}">
                            <option value="${enums.value}">${enums.message}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </form>
        <table id="workFlowRecordTable" class="table table-striped table-bordered nowrap" width="100%">
            <thead>
            <tr>
                <th>步骤编码</th>
                <th>步骤名称</th>
            </tr>
            </thead>
        </table>
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
    //表单样式适应
    $("#fallBackForm").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    //查右侧详述
    trSData.t && trSData.t.cgetDetail('fallBackForm', 'fallback/getDataById', '',queryBack);

    //点击保存
    $(".saveBtn").on("click",function(){
        var json=$("#workFlowRecordTable").DataTable().rows().data();
        var stepIds = "";
        var stepNames = "";
        console.log(json)
        $.each(json, function(k,v){
            console.log(v);
            if(stepIds == ""){
                stepIds += v.backStepId;
                stepNames += v.backStepDes;
            }else{
                stepIds += ","+v.backStepId;
                stepNames += ","+v.backStepDes;
            }
        });
        $("#backStepId").val(stepIds);
        $("#backStepDes").val(stepNames);
        var projectTypeDes=$("#projectType option:selected").text();
        var contributionModeDes=$("#contributionCode option:selected").text();
        $("#projectTypeDes").val(projectTypeDes+"("+contributionModeDes+")");
        //保存
        $("#fallBackForm").cformSave('fallBackTable','',true);
    });

    //放弃 按钮点击后
    $(".cancelBtn").on("click",function(){
        //清空表单
        $("#fallBackForm input").val('');
        trSData.t.cgetDetail('fallBackForm');
        $("#fallBackForm").toggleEditState(false);
        $(".editbtn").addClass("hidden");
        selectTr["fallBackTable"] = 0;
        $('tbody tr:eq(0)').select();
        //移除验证
        $("#fallBackForm").parsley().reset();
    });

</script>
<!-- ================== END PAGE LEVEL JS ================== -->