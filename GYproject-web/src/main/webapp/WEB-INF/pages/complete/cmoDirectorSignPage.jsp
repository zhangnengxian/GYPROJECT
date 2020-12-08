<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="form-group  col-md-6 allSign builder builderSign clearboth hidden ">
    <label class="control-label signature-tool sign-require" for="cmoDirector">现场代表</label>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
        <input type="hidden" id="cmoDirector" name="cmoDirector" value="" class="sign-data-input">
        <input type="hidden" class="signPost"  id="cmoDirector_postType" name="cmoDirector_postType" value="${cmoDirectorSign}">
        <img class="" alt="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>

<div class="form-group col-md-6 hidden selectSignDate ">
    <label class="control-label" for="comDate">日期</label>
    <div>
        <input type="text" class=" form-control input-sm field-not-editable datepicker-not-default" id="comDate"  name="comDate" data-parsley-maxlength="100" value="">
    </div>
</div>
