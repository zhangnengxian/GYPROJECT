<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<div class="p-t-6">
		<div class="mask-html text-center">
			<div>
				<i class="fa fa-4x fa-spinner"></i><br>
				<p class="text-ellipsis">加载中</p>
			</div>
		</div>
		<table id="postTablePop" class="table table-striped table-bordered nowrap" width="100%">
			<thead>
				<tr>
					<th>职务编号</th>
					<th>职务名称</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<script>
	App.restartGlobalFunction();
	$("#deptpopform").styleFit();
	$.getScript('projectjs/dept/post-pop.js?'+Math.random()).done(function () {
		post.init();
	});
</script>
