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

<div class="form-group col-md-6 col-sm-12 allSign builder">
    <label class="control-label signature-tool" for="fieldRepresent">现场代表</label>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="fieldRepresent" name="fieldRepresent" value="" class="sign-data-input">
        <input type="hidden" id="fieldRepresent_postType" name="fieldRepresent_postType" value="${fieldRepresentPost }">
        <img class="fieldRepresent" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>
<div class="form-group col-md-6 col-sm-12 allSign suJgj">
    <label class="control-label signature-tool" for="suJgj">监理</label>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="suJgj" name="suJgj" value="" class="sign-data-input">
        <input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${suJgjPost }">
        <img class="suJgj" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>

<script>
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function () {
        var signatures = $("#signBtn_1,#signBtn_2,#signBtn_3,#signBtn_4,#signBtn_5,#signBtn_6,#signBtn_7,#signBtn_8,#signBtn_9,#signBtn_10");
        signatures.handleSignature();
    });
</script>