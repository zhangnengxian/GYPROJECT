<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="clearboth form-box" style="margin-bottom:-36px">
	<form class="form-horizontal" id="staffForm" action="/" method="POST">
		<input type="hidden"  name="post" value="${post}"/>
		<input type="hidden"  name="deptId" value="${deptId}"/>
		<input type="hidden"  name="corpId" value="${corpId}"/>
		<div class="form-group col-lg-5 col-md-5 col-sm-5">
            <label class="control-label" for="staffNo">编号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="staffNo"/>
            </div>
        </div>
        <div class="form-group col-lg-5 col-md-5 col-sm-5">
			<label class="control-label" for="staffName">姓名</label>
		    <div >
                <input type="text" class="form-control input-sm"  name="staffName"/>
            </div>
		</div>
	</form>
</div>
<div class="p-t-6 p-b-15">
	<table id="staffTable" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
     			<th></th>
            	<th>编号</th>
           		<th>姓名</th>
            	<th>职务</th>
           	</tr>
       	</thead>
	</table>
</div> 
<script>
	App.restartGlobalFunction();
	$("#staffForm").styleFit();
	$.getScript('projectjs/dept/build-pop.js?v='+Math.random()).done(function () {
		staff.init();d
	});
</script>
