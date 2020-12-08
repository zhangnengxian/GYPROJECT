<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div  class="content">
    <div class="row">
        <div class="col-sm-6 col-xs-12">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">补充协议</h4>
                </div>
                <div class="panel-body" id="supplementAudit_panelBox">
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">审批区</h4>
                </div>
                <div class="panel-body" >
                    <div class="toolBtn f-r m-b-15  editbtn">
                        <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSaveBtn" >保存</a>
                        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 auditCancelBtn">放弃</a>
                    </div>
                    <div class="clearboth form-box auditFormDiv">
                        <form class="form-horizontal" id="contractAuditRightForm" action="">
                            <input type="hidden" class="isAudit" id="isAudit" name = "isAudit" value = "${ isAudit}"/>
                            <input type="hidden"  class="projId" name = "projId" value = ""/>
                            <input type="hidden"  class="projNo" name = "projNo" value = ""/>
                            <input type="hidden"  class="isId" name = "isId" value = "${ isId}" placeholder="协议ID"/>
                            <input type="hidden" id="businessOrderId" name = "businessOrderId" value = "${ isId}"/>
                            <input type="hidden" class="currentLevel" name = "mrAuditLevel" value = "${ currentLevel}"/>
                            <div class="form-group col-lg-12 col-md-12 col-sm-6 ">
                                <label class="control-label" for="">审批结果</label>
                                <div>
                                    <c:forEach var="enums" items="${mrResult}">
                                        <label>
                                            <input type="radio" name="mrResult" value="${enums.value}"/>${enums.message}
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 col-md-12 col-sm-12">
                                <label class="control-label" for="">审批意见</label>
                                <div>
                                    <textarea class="form-control field-editable"  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
                            </div>
                            <div class="form-group col-lg-6 col-md-12 col-sm-6">
                                <label class="control-label" for="">审批人</label>
                                <div>
                                    <input type="hidden"  id="mrCsr"  name="mrCsr" data-parsley-maxlength="100" value="${loginInfo.staffId}">
                                    <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
                                </div>
                            </div>
                            <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
                                <label class="control-label" for="">审批日期</label>
                                <div>
                                    <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" data-parsley-maxlength="100" >
                                </div>
                            </div>
                        </form>
                    </div>
                    <div >
                        <h4 class="m-t-15 m-l-7"><strong>审批历史</strong></h4>
                    </div>
                    <table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                        <tr>
                            <th>审批日期</th>
                            <th>审批结果</th>
                            <th>审批意见</th>
                            <th>审批人</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    App.restartGlobalFunction();
    App.setPageTitle('智能表合同补充协议审核 - 工程管理系统');


</script>
