<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="form-group col-md-6 col-sm-12 allSign testLeader">
    <label class="control-label signature-tool" for="testLeaderSign">施工班组</label>
    <div>
        <a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
        <input type="hidden" class="sign-data-input" id="testLeaderSign" name="testLeaderSign" value="">
        <input type="hidden" class="signPost"  id="testLeaderSign_postType" name="testLeaderSign_postType" value="${testLeaderPost}" >
        <img class="testLeaderSign" alt="" src="" style="height: 30px">
        <span class="clear-sign"><i class="fa ion-close-circled"></i></span>
    </div>
</div>