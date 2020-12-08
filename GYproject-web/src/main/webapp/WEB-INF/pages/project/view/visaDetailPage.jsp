<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="infodetails">
    <form id="visaForm" class="form-horizontal" data-parsley-validate="true" action="">
        <div class="form-group col-md-6 col-sm-12">
            <label class="control-label" for="">工程编号</label>
            <div>
                <input type="text" class="form-control input-sm field-not-editable" value="" id="projNo" name="projNo">
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12">
            <label class="control-label" for="">工程名称</label>
            <div>
                <input type="text" class="form-control input-sm field-not-editable" value="" id="projName"
                       name="projName">
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12">
            <label class="control-label" for="evNo">签证编号</label>
            <div>
                <input type="text" class="form-control input-sm field-not-editable addClear" value="" id="evNo"
                       name="evNo">
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12">
            <label class="control-label" for="evPosition">签证部位</label>
            <div>
                <input type="text" class="form-control input-sm field-editable addClear allText construction" value=""
                       id="evPosition" name="evPosition" data-parsley-maxlength="500" data-parsley-required="true"/>
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12">
            <label class="control-label" for="drawingNo">施工图号</label>
            <div>
                <input type="text" class="form-control input-sm field-editable addClear allText construction" value=""
                       id="drawingNo" name="drawingNo" data-parsley-maxlength="500" data-parsley-required="true"/>
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
            <label class="control-label" for="">签证日期</label>
            <div>
                <input type="text" class="form-control input-sm  field-not-editable datepicker-default addClear "
                       value="" id="visaDate" name="visaDate" data-parsley-required="true"/>
            </div>
        </div>
        <div class="form-group col-sm-6">
            <label class="control-label" for="evType">签证类型</label>
            <div>
                <select class="form-control input-sm field-editable addClear allText construction" id="evType"
                        name="evType" data-parsley-required="true">
                    <c:forEach var="enums" items="${evType}">
                        <option value="${enums.value}">${enums.message}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12">
            <label class="control-label" for="quantitiesTotal">审核金额</label>
            <div>
                <input type="text" class="form-control input-sm field-not-editable addClear" value=""
                       id="quantitiesTotal" name="quantitiesTotal" data-parsley-maxlength="50"/>
            </div>
        </div>

        <div class="form-group col-sm-12">
            <label class="control-label" for="evReason">签证原因</label>
            <div>
                <textarea class="form-control field-editable addClear allText construction" name="evReason"
                          id="evReason" rows="4" cols="" data-parsley-maxlength="1000"></textarea>
            </div>
        </div>
        <div class="form-group col-sm-12">
            <label class="control-label" for="evContent">签证内容</label>
            <div>
                <textarea class="form-control field-editable addClear allText construction" name="evContent"
                          id="evContent" rows="7" cols="" data-parsley-maxlength="1000"
                          placeholder="签证内容及工程量"></textarea>
            </div>
        </div>
        <div class="form-group col-sm-12">
            <label class="control-label" for="constructionUnit">施工单位</label>
            <div>
                <input type="text" class="form-control field-not-editable input-sm" id="constructionUnit"
                       name="constructionUnit"/>
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 allSign cuPm">
            <label class="control-label signature-tool" for="suPrincipal">项目负责人</label>
            <div>
                <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
                <input type="hidden" class="sign-data-input" id="suPrincipal" name="suPrincipal" value="">
                <input type="hidden" class="signPost" id="suPrincipal_postType" name="suPrincipal_postType"
                       value="${CU_PM}">
                <img class="suPrincipal" alt="" src="" style="height: 30px">
                <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
            <label class="control-label">审核日期</label>
            <div>
                <input type="text" class="form-control input-sm addClear datepicker-default field-editable allText cuPm"
                       value="" id="custAuditDate" name="custAuditDate"/>
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 allSign construction">
            <label class="control-label signature-tool" for="builder">施工员</label>
            <div>
                <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
                <input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
                <input type="hidden" class="signPost" id="builder_postType" name="builder_postType"
                       value="${CONSTRUCTION}">
                <img class="builder" alt="" src="" style="height: 30px">
                <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
            </div>
        </div>

        <div class="form-group col-sm-12">
            <label class="control-label" for="suName">监理单位</label>
            <div>
                <input type="text" class="form-control field-not-editable input-sm" id="suName" name="suName"/>
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-12">
            <label class="control-label" for="">监理意见</label>
            <div>
                <textarea class="form-control field-editable addClear allText suJgj" name="suOpinion" id="suOpinion"
                          rows="4" cols="" data-parsley-maxlength="200"></textarea></div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">
            <label class="control-label allText suJgj" for="">审核结果</label>
            <div>
                <label>
                    <input type="radio" class=" allText suJgj" name="suResult" value="1" checked/>通过
                </label>
                <label>
                    <input type="radio" class=" allText suJgj" name="suResult" value="0"/>未通过
                </label>

            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 allSign suJgj">
            <label class="control-label signature-tool" for="suJgj">现场监理</label>
            <div>
                <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
                <input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
                <input type="hidden" class="signPost" id="suJgj_postType" name="suJgj_postType" value="${suJgj}">
                <img class="suJgj" alt="" src="" style="height: 30px">
                <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
            <label class="control-label" for="">审核日期</label>
            <div>
                <input type="text"
                       class="form-control input-sm addClear datepicker-default field-editable allText suJgj" value=""
                       id="suAuditDate" name="suAuditDate"/>
            </div>
        </div>
        <div class="form-group col-sm-12">
            <label class="control-label" for="custName">建设单位</label>
            <div>
                <input type="text" class="form-control input-sm field-not-editable" value="" id="custName"
                       name="custName" data-parsley-maxlength="50"/>
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-12">
            <label class="control-label" for="">现场代表意见</label>
            <div>
                <textarea class="form-control field-editable addClear allText builder" name="cmoPrincipalOpinion"
                          id="cmoPrincipalOpinion" rows="4" cols="" data-parsley-maxlength="200"></textarea></div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">
            <label class="control-label" for="">审核结果</label>
            <div>
                <label>
                    <input type="radio" class=" allText builder" name="cmoPrincipalResult" value="1" checked/>通过
                </label>
                <label>
                    <input type="radio" class=" allText builder" name="cmoPrincipalResult" value="0"/>未通过
                </label>

            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 allSign builder">
            <label class="control-label signature-tool" for=cmoPrincipal>现场代表</label>
            <div>
                <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
                <input type="hidden" class="sign-data-input" id="cmoPrincipal" name="cmoPrincipal" value="">
                <input type="hidden" class="signPost" id="cmoPrincipal_postType" name="cmoPrincipal_postType"
                       value="${builder}">
                <img class="cmoPrincipal" alt="" src="" style="height: 30px">
                <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
            <label class="control-label">审核日期</label>
            <div>
                <input type="text"
                       class="form-control input-sm addClear datepicker-default field-editable all allText builder"
                       value="" id="builderAuditDate" name="builderAuditDate"/>
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6 hidden cuReState">
            <label class="control-label allText budgetMember" for="">确认结果</label>
            <div>
                <label>
                    <input type="radio" class="field-editable allText budgetMember" name="cuReState" value="-1"
                           checked/>待确认
                </label>
                <label>
                    <input type="radio" class="field-editable allText budgetMember" name="cuReState" value="1" checked/>无异议
                </label>
                <label>
                    <input type="radio" class="field-editable allText budgetMember" name="cuReState" value="0"/>有异议
                </label>

            </div>
        </div>
        <div class="form-group col-md-12 col-sm-12 hidden cuReReason">
            <label class="control-label" for="">重审原因</label>
            <div>
                <input type="text" class="form-control input-sm field-editable allText budgetMember" value=""
                       id="cuReReason" name="cuReReason" data-parsley-required="true" data-parsley-maxlength="500"/>
            </div>
        </div>
    </form>
</div>


<script>

    App.restartGlobalFunction();
    $(".infodetails").hideMask();//隐藏loading
    $("#visaForm").toggleEditState(false).styleFit();
    $('.datepicker-default').datepicker({todayHighlight: true});//日期控件
    trSData.t && trSData.t.cgetDetail('visaForm', 'projectView/findVisaDetail', '', detailCallback);//第一次加载显示详细

    var detailCallback = function (data) {
        $("#visaDate").val(format($("#visaDate").val(),"all"));
        $("#custAuditDate").val(format($("#custAuditDate").val(),"all"));
        $("#suAuditDate").val(format($("#suAuditDate").val(),"all"));
        $("#builderAuditDate").val(format($("#builderAuditDate").val(),"all"));
    };
</script>
