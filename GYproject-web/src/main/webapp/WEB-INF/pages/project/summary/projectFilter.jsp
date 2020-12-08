<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth col-md-12 p-t-15 form-box project-filter">
	<h4>项目检索</h4>
	<form class="" id="projectFilterForm" name="projectFilterForm" action="/" method="POST">
		<div class="form-group">
            <div>
                <input type="text" class="form-control input-sm" placeholder="项目编号" name="projNo" value=""/>
            </div>
      	</div>
		<div class="form-group">
            <div>
                <input type="text" class="form-control input-sm" placeholder="客户名称" name="custName" value=""/>
            </div>
      	</div>
		<div class="form-group">
            <div>
                <input type="text" class="form-control input-sm" placeholder="项目名称" name="projName" value=""/>
            </div>
      	</div>
		<div class="form-group">
            <div>
                <input type="text" class="form-control input-sm" placeholder="项目地点" name="projAddr" value=""/>
            </div>
      	</div>
        <div class="form-group">
            <div>
                <select type="text" class="form-control input-sm select-filter" name="projectType">
                    <option value="">项目类型</option>
                    <c:forEach var="enums" items="${projectType}">
                        <option value="${enums.projTypeId }">${enums.projConstructDes }</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div>
                <select type="text" class="form-control input-sm select-filter" name="contributionMode">
                    <option value="">出资方式</option>
                    <c:forEach var="enums" items="${contributionMode}">
                        <option value="${enums.id}" >${enums.contributionDes}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
		<div class="form-group signStepInput hidden">
            <div>
                <select type="text" class="form-control input-sm select-filter sideBarID" name="sideBarID">
               		<option value="">工程阶段</option>
	                <c:forEach var="enums" items="${signStep}">
						   	<option value="${enums.MENUID}" >${enums.MENUDES}</option>
	                </c:forEach>
                </select>
            </div>
      	</div>
		<div class="form-group">
            <div>
                <select type="text" class="form-control input-sm select-filter projStatusId" name="projStatusId">
                	 <option value="-1">项目状态</option>
                	 <c:forEach var="enums" items="${projStatus}">
						<option value="${enums.value}" >${enums.message}</option>
		             </c:forEach>
                </select>
            </div>
      	</div>
		<div class="form-group">
            <div>
                <input type="text" class="form-control input-sm datepicker-default acceptDateStart" placeholder="受理开始日期" name="acceptDateStart" value=""/>
            </div>
      	</div>
      	<div class="form-group">
            <div>
                <input type="text" class="form-control input-sm datepicker-default acceptDateEnd" placeholder="受理截止日期" name="acceptDateEnd" value=""/>
            </div>
      	</div>
		<div class="form-group">
            <div>
                <input type="text" class="form-control input-sm datepicker-default projectStartData" placeholder="开工开始日期" name="startDateStart" value=""/>
            </div>
      	</div>
      	<div class="form-group">
            <div>
                <input type="text" class="form-control input-sm datepicker-default " placeholder="开工截止日期" name="startDateEnd" value=""/>
            </div>
      	</div>
		<div class="form-group">
            <div>
                <input type="text" class="form-control input-sm datepicker-default" placeholder="竣工开始日期" name="completeDateStart" value=""/>
            </div>
      	</div>
      	<div class="form-group">
            <div>
                <input type="text" class="form-control input-sm datepicker-default" placeholder="竣工截止日期" name="completeDateEnd" value=""/>
            </div>
      	</div>
      	<div class="form-group">
            <div>
                <input type="text" class="form-control input-sm datepicker-default finishedDateStart" placeholder="结束开始日期" name="finishedDateStart" value=""/>
            </div>
      	</div>
      	<div class="form-group">
            <div>
                <input type="text" class="form-control input-sm datepicker-default" placeholder="结束截止日期" name="finishedDateEnd" value=""/>
            </div>
      	</div>
	</form>
	<div class="toolBtn pull-right m-t-5">
		<button type="reset" form="projectFilterForm" class="btn btn-sm btn-send reset">重置</button>
		<a href="javascript:;" class="btn btn-sm btn-confirm proj-query-btn">查询</a>
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>

//日期datepicker
$('.datepicker-default').datepicker({
    todayHighlight: true,
    endDate:format(new Date().getTime())
});
$(".select-filter").off("change").on("change", function(){
	$(this).removeClass("selected");
	if($(this).find("option:selected").val() > 0) $(this).addClass("selected");
});
$("#projectFilterForm").on("reset", function(){
	$(".select-filter").removeClass("selected");
});
$(".finishedDateStart").val(new Date().getFullYear() + '-01-01');

$(".proj-query-btn").off("click").on("click",function(){
	$(this).projQuery();
});

if(location.hash === '#projectMap/main') $(".signStepInput").removeClass("hidden");


</script>
<!-- ================== END PAGE LEVEL JS ================== -->