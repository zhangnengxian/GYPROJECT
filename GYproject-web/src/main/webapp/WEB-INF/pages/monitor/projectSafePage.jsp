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
    /* border:1px solid green; */
}
</style>
<div class="infodetails" id="echartsBox">
	<div class="echartSmallLeftBox" id="echartSmallLeftBox"></div>
   	<div class="echartSmallRightBox" id="echartSmallRightBox"></div>
</div>
<script>
$.getScript('/assets/plugins/echarts/build/dist/echarts.js').done(function(){
	$.getScript('/projectjs/monitor/project-safe.js').done(function () {
	    projectConstructList.init();
	});
});
</script>