<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.echartSmallLeftBox{
	position:absolute;
	width:560px;
    height:400px;
	/* border:1px solid #fff;  */
}
.echartSmallRightBox{
	position:absolute;
	width:560px;
    height:400px;
/* 	border:1px solid green; */
}
</style>
<div class="infodetails">
	<div class="echartSmallLeftBox" id="echartSmallLeftBox"></div>
   	<div class="echartSmallRightBox" id="echartSmallRightBox"></div>
	 <div class="clearboth form-box m-t-5" id="listBox">
		<form id="projectListForm">
			<table id="projectListTable" class="table table-striped table-bordered nowrap" width="100%"">
	        	<thead>
		            <tr>
               		    <th>工程编号</th>
	               		    <th>工程名称</th>
	               		    <th>工程地点</th>
			                <th>工程规模</th>
			                <th>工程状态</th>
			                <th>施工单位</th>
			                <th>分包单位</th>
	               		    <th>设计单位</th>
			                <th>勘察人</th>
		            </tr>
	           </thead>
			</table>
		</form>
	</div>
	<div class="chooseRadio" id="chooseRadio">
	    	<input type="radio" value="" name="constructionUnit"/><span style="margin-left:4px;">全部</span>
	    	<input type="radio" value="110201" name="constructionUnit" /><span style="margin-left:4px;">一处</span>
	    	<input type="radio" value="110202" name="constructionUnit" /><span style="margin-left:4px;">二处</span>
	    	<input type="radio" value="110203" name="constructionUnit" /><span style="margin-left:4px;">三处</span>
	    	<input type="radio" value="110204" name="constructionUnit" /><span style="margin-left:4px;">四处</span>
	    	<input type="radio" value="110205" name="constructionUnit" /><span style="margin-left:4px;">五处</span>
	</div>
	
</div>
<script>
	$.getScript('/projectjs/monitor/project-list.js').done(function () {
		projectList.init();
	});
</script>	