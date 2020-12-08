
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet"/>
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet"/>

<div class="form-group col-md-12 col-sm-12">
    <label class="control-label" for="rnQuestions">存在问题</label>
    <div>
        <textarea class="form-control input-sm  field-editable addClear " row="15" id="rnQuestions" name="rnQuestions" data-parsley-required="true" data-parsley-maxlength="1000"></textarea>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12">
    <label class="control-label">通知时间</label>
    <div>
        <input type="text" class="form-control input-sm datepicker-default field-editable addClear" value="" id="rnDate" name="rnDate" data-parsley-required="true"/>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12 resident">
    <label class="control-label" for="limitTime">整改期限</label>
    <div>
        <input type="number" class=" form-control input-sm field-editable addClear " min="0" id="limitTime" name="limitTime" data-parsley-maxlength="10" value="">
        <a href="javascript:;" class="btn btn-sm btn-default">天</a>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12 clearboth allSign technician">
    <label class="control-label signature-tool" for="">质量安全部</label>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="qsGroup" name="qsGroup" value="" class="sign-data-input">
        <input type="hidden" id="qsGroup_postType" name="qsGroup_postType" value="${qsGroupPost }">
        <img class="qsGroup" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12 allSign inspector">
    <label class="control-label signature-tool" for="custService">客户部综合组</label><%--原客户服务中心--%>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="custService" name="custService" value="" class="sign-data-input">
        <input type="hidden" id="custService_postType" name="custService_postType" value="${custServicePost }">
        <img class="custService" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12 allSign construction">
    <label class="control-label signature-tool" for="builder">客户部巡线班</label><%--原施工员--%>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="builder" name="builder" value="" class="sign-data-input">
        <input type="hidden" id="builder_postType" name="builder_postType" value="${builderPost }">
        <img class="builder" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12 allSign cuPm">
    <label class="control-label signature-tool" for="networkCenter">工程技术部</label><%--原管网数据中心签字--%>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="networkCenter" name="networkCenter" value="" class="sign-data-input">
        <input type="hidden" id="networkCenter_postType" name="networkCenter_postType" value="${networkCenterPost }">
        <img class="networkCenter" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12 allSign cuPm">
    <label class="control-label signature-tool" for="cuPLeader">施工单位</label>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="cuPLeader" name="cuPLeader" value="" class="sign-data-input">
        <input type="hidden" id="cuPLeader_postType" name="cuPLeader_postType" value="${cuPLeaderPost }">
        <img class="cuPLeader" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12 allSign suJgj">
    <label class="control-label signature-tool" for="suJgj">监理公司</label><%--原监理--%>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="suJgj" name="suJgj" value="" class="sign-data-input">
        <input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${suJgjPost }">
        <img class="suJgj" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>
<div class="form-group col-md-6 col-xs-12 allSign designer">
    <label class="control-label  signature-tool" for="designer">设计公司</label><%--原设计--%>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_7"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="designer" name="designer" value="" class="sign-data-input">
        <input type="hidden" id="designer_postType" name="designer_postType" value="${designerPost }">
        <img class="designer" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>


<script>
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function () {
        var signatures = $("#signBtn_1,#signBtn_2,#signBtn_3,#signBtn_4,#signBtn_5,#signBtn_6,#signBtn_7");
        signatures.handleSignature();
    });
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
</script>