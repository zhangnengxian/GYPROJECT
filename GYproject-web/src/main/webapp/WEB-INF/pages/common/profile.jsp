<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="image">
    <a href="javascript:;"><img src="attachments/${sessionScope.session_loginInfo.photoUrl}" alt="" /></a>
</div>
<div class="info">${sessionScope.session_loginInfo.staffName}</div>
<small>${sessionScope.session_loginInfo.deptName}</small>