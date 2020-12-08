<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet"/>
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet"/>

<div class="form-group col-md-12 col-sm-12">
    <label class="control-label" for="rnQuestions">存在问题</label>
    <div>
        <textarea class="form-control input-sm  field-editable addClear " row="15" id="rnQuestions" name="rnQuestions"
                  data-parsley-required="true" data-parsley-maxlength="1000"></textarea>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12">
    <label class="control-label">通知时间</label>
    <div>
        <input type="text" class="form-control input-sm datepicker-default field-editable addClear" value="" id="rnDate"
               name="rnDate" data-parsley-required="true"/>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12 resident">
    <label class="control-label" for="limitTime">整改期限</label>
    <div>
        <input type="number" class=" form-control input-sm field-editable addClear " min="0" id="limitTime"
               name="limitTime" data-parsley-maxlength="10" value="">
        <a href="javascript:;" class="btn btn-sm btn-default">天</a>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12 clearboth allSign technician">
    <label class="control-label signature-tool" for="">质量安全组</label>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="qsGroup" name="qsGroup" value="" class="sign-data-input">
        <input type="hidden" id="qsGroup_postType" name="qsGroup_postType" value="${qsGroupPost }">
        <img class="qsGroup" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12 allSign inspector">
    <label class="control-label signature-tool" for="custService">客户服务中心</label>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="custService" name="custService" value="" class="sign-data-input">
        <input type="hidden" id="custService_postType" name="custService_postType" value="${custServicePost }">
        <img class="custService" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>
 <div class="form-group col-md-6 col-sm-12 allSign processTechnician">
    <label class="control-label signature-tool" for="tdCompany">输配公司</label>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="tdCompany" name="tdCompany" value="" class="sign-data-input">
        <input type="hidden" id="tdCompany_postType" name="tdCompany_postType" value="${tdCompanyPost }">
        <img class="" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>

<div class="form-group col-md-6 col-sm-12 allSign builder">
    <label class="control-label signature-tool" for="fieldRepresent">现场代表</label>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="fieldRepresent" name="fieldRepresent" value="" class="sign-data-input">
        <input type="hidden" id="fieldRepresent_postType" name="fieldRepresent_postType" value="${fieldRepresentPost }">
        <img class="fieldRepresent" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>
<div class="form-group col-md-6 col-xs-12 allSign designer">
    <label class="control-label  signature-tool" for="designer">设计</label>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_7"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="designer" name="designer" value="" class="sign-data-input">
        <input type="hidden" id="designer_postType" name="designer_postType" value="${designerPost }">
        <img class="designer" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12 allSign suJgj">
    <label class="control-label signature-tool" for="suJgj">监理</label>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_8"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="suJgj" name="suJgj" value="" class="sign-data-input">
        <input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${suJgjPost }">
        <img class="suJgj" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>

<div class="hidden rectifyNoticeBack">
    <div class="form-group col-md-12 col-sm-12">
        <label class="control-label" for="rnExplain">整改说明</label>
        <div>
            <textarea class="form-control input-sm  field-not-editable addClear" row="4" id="rnExplain" name="rnExplain"
                      data-parsley-required="true" data-parsley-maxlength="200"></textarea>
        </div>
    </div>
    <div class="form-group col-md-12 col-sm-12">
        <label class="control-label" for="selfCheckResult">自检结果</label>
        <div>
            <textarea class="form-control input-sm  field-not-editable addClear" row="2" id="selfCheckResult"
                      name="selfCheckResult" data-parsley-required="true" data-parsley-maxlength="200"></textarea>
        </div>
    </div>
    <div class="form-group col-md-6 col-sm-12">
        <label class="control-label signature-tool" for="cuPleaderBack">项目经理</label>
        <div>
            <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_11"><i class="fa fa-pencil"></i></a>
            <input type="hidden" id="cuPleaderBack" name="cuPleaderBack" value="" class="sign-data-input">
            <input type="hidden" id="cuPleaderBack_postType" name="cuPleaderBack_postType" value="">
            <img class="cuPleaderBack" alt="" style="height: 30px">
            <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
        </div>
    </div>
    <div class="form-group col-md-6 col-sm-12">
        <label class="control-label" for="selfCheckDate">日期</label>
        <div>
            <input type="text" class="form-control input-sm datepicker-default field-not-editable addClear" value=""
                   id="selfCheckDate" name="selfCheckDate" data-parsley-required="true"/>
        </div>
    </div>
    <div class="form-group col-md-12 col-sm-12">
        <label class="control-label" for="reinspectionResult">复验结果</label>
        <div>
            <textarea class="form-control input-sm  field-not-editable addClear" row="2" id="reinspectionResult"
                      name="reinspectionResult" data-parsley-required="true" data-parsley-maxlength="200"></textarea>
        </div>
    </div>
    <div class="form-group col-md-6 col-sm-12">
        <label class="control-label signature-tool" for=suJgjBack>现场监理</label>
        <div>
            <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_12"><i class="fa fa-pencil"></i></a>
            <input type="hidden" id="suJgjBack" name="suJgjBack" value="" class="sign-data-input">
            <input type="hidden" id="suJgjBack_postType" name="suJgjBack_postType" value="">
            <img class="suJgjBack" alt="" style="height: 30px">
            <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
        </div>
    </div>
    <div class="form-group col-md-6 col-sm-12">
        <label class="control-label" for="reinspectionDate">日期</label>
        <div>
            <input type="text" class="form-control input-sm datepicker-default field-not-editable addClear" value=""
                   id="reinspectionDate" name="reinspectionDate" data-parsley-required="true"/>
        </div>
    </div>
</div>
</form>
</div>
<script>
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function () {
        var signatures = $("#signBtn_1,#signBtn_2,#signBtn_3,#signBtn_4,#signBtn_5,#signBtn_6,#signBtn_7,#signBtn_8,#signBtn_9,#signBtn_10");
        signatures.handleSignature();
    });
</script>