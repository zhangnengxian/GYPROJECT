<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link rel="stylesheet" href="assets/plugins/jQuery-Gantt/index_files/css/style.css">
<link rel="stylesheet" href="assets/plugins/jQuery-Gantt/index_files/css/prettify.css">
<!-- ================== END PAGE LEVEL STYLE ================== -->
<div class="panel-body p-t-0 p-b-0 p-l-0 p-r-0">
	<div class="infodetails">
		<input class="hidden projectId" value="${projId}">
		<input class="hidden code" id="code" value="${code}">
		<input class="hidden projectFlow" id="projectFlow" value="${projectFlow}">
		<select class ="form-control input-sm field-editable hidden" id="stepEnum">
			<c:forEach items="${stepEnum}" var="es">
				<option value="${es.value }">${es.message }</option>
			</c:forEach>
		 </select>
		<h3>工程进度</h3>
		<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>    
	    <div class="tab-pane fade in btn-top" id="">
		    <ul class="nav nav-tabs p-t-5 p-l-5">
				<li class="active"><a href="#tab-1" data-toggle="tab" id="ganttTab">甘特图</a></li>
				<li class=""><a href="#tab-2" data-toggle="tab" id="flowTab">流程图</a></li>
			</ul>
			<div class="tab-content p-l-0 p-r-0 p-t-5" style="box-shadow:0 0 0 0">
				<div class="tab-pane fade active in btn-top" id="tab-1" >
					<div class="gantt"></div>
				</div>
				<div class="tab-pane fade  in btn-top" id="tab-2" >
					<div class="project-flow-image-box01 project-flow-image-box">
						<ul class="flow-list">
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1101" title="受理申请"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1102" title="勘察派工"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1103" title="现场踏勘"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1104" title="踏勘审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1402" title="安装合同"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1403" title="合同审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1105" title="资料收集"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1201" title="设计出图"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1604" title="施工合同"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1603" title="施工预算审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1602" title="预算审核派工"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1601" title="施工预算"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1502" title="计划审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1501" title="工程计划"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1203" title="图纸签收"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1202" title="图纸审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1701" title="开工报告"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1702" title="试压完成"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1801" title="自检"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1802" title="预验收"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1803" title="资料验收申请"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1804" title="资料审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1805" title="联合验收"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1901" title="结算报审"></li>
							 <li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li> 
							 <li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li> 
							 <li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li> 
						    <li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="3001" title="已竣工"></li> 
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1907" title="资料归档"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1904" title="终审确认"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1903" title="终审派遣"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1902" title="结算初审"></li>
						</ul>
					</div>
					

					<!--  公建户工程 用户出资 -->
					<div class="project-flow-image-box03 project-flow-image-box">
						<ul class="flow-list">
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1101" title="受理申请"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1102" title="勘察派工"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1103" title="现场踏勘"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1104" title="踏勘审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1105" title="资料收集"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1201" title="设计出图"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1202" title="图纸审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1203" title="图纸签收"></li>
							
							
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1501" title="工程计划"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1403" title="合同审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1402" title="安装合同"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="14011" title="造价审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1401" title="造价确认"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1303" title="预算审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1302" title="预算记录"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1301" title="预算派工"></li>
							
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1502" title="计划审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1601" title="施工预算"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1602" title="预算审核派工"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1603" title="施工预算审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1604" title="施工合同"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1701" title="开工报告"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1702" title="试压完成"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1801" title="自检"></li>
							
							
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1904" title="终审确认"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1903" title="终审派遣"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1902" title="结算初审"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1901" title="结算报审"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1805" title="联合验收"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1804" title="资料审核"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1803" title="资料验收申请"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1802" title="预验收"></li>
							
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1907" title="资料归档"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="3001" title="已竣工"></li>
						</ul>
					</div>
					<!--  干线工程 公司出资 ok-->
					<div class="project-flow-image-box04 project-flow-image-box">
						<ul class="flow-list">
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1100" title="计划立项"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1102" title="勘察派工"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1103" title="现场踏勘"></li>
							<li class="row1"  data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1104" title="踏勘审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1105" title="资料收集"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1201" title="设计出图"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1202" title="图纸审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1203" title="图纸签收"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1702" title="试压完成"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1701" title="开工报告"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1604" title="施工合同"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1603" title="施工预算审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1602" title="预算审核派工"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1601" title="施工预算"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1502" title="计划审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1501" title="工程计划"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1801" title="自检"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1802" title="预验收"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1803" title="资料验收申请"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1804" title="资料审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1805" title="联合验收"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1901" title="结算报审"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1902" title="结算初审"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1903" title="终审派遣"></li>
							
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="3001" title="已竣工"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1907" title="资料归档"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1906" title="转固审核"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1905" title="转固申请"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1904" title="终审确认"></li>
						</ul>
					</div>
					
					
					
					
					<!--  改管工程 用户出资 -->
					<div class="project-flow-image-box05 project-flow-image-box">
						<ul class="flow-list">
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1101" title="受理申请"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1102" title="勘察派工"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1103" title="现场踏勘"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1104" title="踏勘审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1105" title="资料收集"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1201" title="设计出图"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1202" title="图纸审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1203" title="图纸签收"></li>
							
							
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1501" title="工程计划"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1403" title="合同审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1402" title="安装合同"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="14011" title="造价审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1401" title="造价确认"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1303" title="预算审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1302" title="预算记录"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1301" title="预算派工"></li>
							
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1502" title="计划审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1601" title="施工预算"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1602" title="预算审核派工"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1603" title="施工预算审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1604" title="施工合同"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1701" title="开工报告"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1702" title="试压完成"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1801" title="自检"></li>
							
							
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1904" title="终审确认"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1903" title="终审派遣"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1902" title="结算初审"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1901" title="结算报审"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1805" title="联合验收"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1804" title="资料审核"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1803" title="资料验收申请"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1802" title="预验收"></li>
							
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1907" title="资料归档"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="3001" title="已竣工"></li>
							
						</ul>
					</div>
					
					
					
					
					<!--  改管工程 政府平台 -->
					<div class="project-flow-image-box06 project-flow-image-box">
						<ul class="flow-list">
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1101" title="受理申请"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1102" title="勘察派工"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1103" title="现场踏勘"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1104" title="踏勘审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1105" title="资料收集"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1201" title="设计出图"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1202" title="图纸审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1203" title="图纸签收"></li>
							
							
							
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1403" title="合同审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1402" title="安装合同"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1304" title="政府预算评审"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="14011" title="造价审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1401" title="造价确认"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1303" title="预算审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1302" title="预算记录"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1301" title="预算派工"></li>
							
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1501" title="工程计划"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1502" title="计划审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1601" title="施工预算"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1602" title="预算审核派工"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1603" title="施工预算审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1604" title="施工合同"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1701" title="开工报告"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1702" title="试压完成"></li>
							
							
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1903" title="终审派遣"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1902" title="结算初审"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1901" title="结算报审"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1805" title="联合验收"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1804" title="资料审核"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1803" title="资料验收申请"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1802" title="预验收"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1801" title="自检"></li>
							
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1904" title="终审确认"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="19041" title="政府结算评审"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1907" title="资料归档"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="3001" title="已竣工"></li>
						</ul>
					</div>
					
					
					
					
					
					<!--  改管工程 政府专项基金 ok-->
					<div class="project-flow-image-box07 project-flow-image-box">
						<ul class="flow-list">
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1100" title="计划立项"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1102" title="勘察派工"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1103" title="现场踏勘"></li>
							<li class="row1"  data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1104" title="踏勘审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1105" title="资料收集"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1201" title="设计出图"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1202" title="图纸审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1203" title="图纸签收"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1702" title="试压完成"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1701" title="开工报告"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1604" title="施工合同"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1603" title="施工预算审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1602" title="预算审核派工"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1601" title="施工预算"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1502" title="计划审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1501" title="工程计划"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1801" title="自检"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1802" title="预验收"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1803" title="资料验收申请"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1804" title="资料审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1805" title="联合验收"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1901" title="结算报审"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1902" title="结算初审"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1903" title="终审派遣"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="3001" title="已竣工"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1907" title="资料归档"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1906" title="转固审核"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1905" title="转固申请"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1904" title="终审确认"></li>
						</ul>
					</div>
					
					
					
					<!--  改管工程 公司出资 ok-->
					<div class="project-flow-image-box08 project-flow-image-box">
						<ul class="flow-list">
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1100" title="计划立项"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1102" title="勘察派工"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1103" title="现场踏勘"></li>
							<li class="row1"  data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1104" title="踏勘审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1105" title="资料收集"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1201" title="设计出图"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1202" title="图纸审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1203" title="图纸签收"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1702" title="试压完成"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1701" title="开工报告"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1604" title="施工合同"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1603" title="施工预算审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1602" title="预算审核派工"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1601" title="施工预算"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1502" title="计划审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1501" title="工程计划"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1801" title="自检"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1802" title="预验收"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1803" title="资料验收申请"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1804" title="资料审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1805" title="联合验收"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1901" title="结算报审"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1902" title="结算初审"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1903" title="终审派遣"></li>
							
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="3001" title="已竣工"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1907" title="资料归档"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1906" title="转固审核"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1905" title="转固申请"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1904" title="终审确认"></li>
						</ul>
					</div>
					
					
					
					<!--  改管工程 小规模 ok-->
					<div class="project-flow-image-box10 project-flow-image-box">
						<ul class="flow-list">
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1101" title="受理申请"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1102" title="勘察派工"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1103" title="现场踏勘"></li>
							<li class="row1 m-l-2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1104" title="踏勘审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1105" title="资料收集"></li>
							<li class="row1 m-t-3" style="margin-bottom: -3px" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1201" title="设计出图"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1202" title="图纸审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1203" title="图纸签收"></li>	
							
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1501" title="工程计划"></li>
							<li class="row2" style="margin-left: -2px" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1403" title="合同审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1402" title="安装合同"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="14011" title="造价审核"></li>	
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1401" title="工程造价"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1303" title="预算审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1302" title="预算记录"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1301" title="预算派工"></li>
							
							
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1502" title="计划审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1601" title="施工预算"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1602" title="预算审核派工"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1603" title="施工预算审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1604" title="施工合同"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1701" title="开工报告"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1702" title="试压完成"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1801" title="自检"></li>
							
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="3001" title="已竣工"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1907" title="资料归档"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1904" title="终审确认"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1901" title="结算报审"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1806" title="一站式验收"></li>
							<!-- <li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1804" title="资料审核"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1803" title="资料验收申请"></li> -->
						</ul>
					</div>
					
					
					<!--  场站工程 公司出资 ok-->
					<div class="project-flow-image-box11 project-flow-image-box">
						<ul class="flow-list">
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="7001" title="场站立项"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="7102" title="建审手续"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="7203" title="工程施工"></li>
							<li class="row3 m-l-2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="7304" title="工程结算"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="3001" title="已竣工"></li>
						</ul>
					</div>
					
					
					
					<!--  公建户工程 公司出资 ok-->
					<div class="project-flow-image-box12 project-flow-image-box">
						<ul class="flow-list">
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1100" title="受理申请"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1102" title="勘察派工"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1103" title="现场踏勘"></li>
							<li class="row1"  data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1104" title="踏勘审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1105" title="资料收集"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1201" title="设计出图"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1202" title="图纸审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1203" title="图纸签收"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1702" title="试压完成"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1701" title="开工报告"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1604" title="施工合同"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1603" title="施工预算审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1602" title="预算审核派工"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1601" title="施工预算"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1502" title="计划审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1501" title="工程计划"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1801" title="自检"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1802" title="预验收"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1803" title="资料验收申请"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1804" title="资料审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1805" title="联合验收"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1901" title="结算报审"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1902" title="结算初审"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1903" title="终审派遣"></li>
							
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="" title=""></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="3001" title="已竣工"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1907" title="资料归档"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1906" title="转固审核"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1905" title="转固申请"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1904" title="终审确认"></li>
						</ul>
					</div>
				</div>
				
			</div>
		</div>
		
	</div>
	
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
	$(".infodetails").hideMask();
	
	$.getScript('assets/plugins/jQuery-Gantt/index_files/js/jquery.fn.gantt.js').done(function () {
		$.getScript('assets/plugins/jQuery-Gantt/index_files/js/bootstrap-tooltip.js').done(function () {
			$.getScript('assets/plugins/jQuery-Gantt/index_files/js/bootstrap-popover.js').done(function () {
				$.getScript('assets/plugins/jQuery-Gantt/index_files/js/prettify.js').done(function () {
					$(function() {
						"use strict";
						 var projId = $(".projectId").val();
						 $.ajax({
					            type: 'POST',
					            url: "projectView/querySchedule?projId="+projId,
					            data: {},
					            dataType:'json',
					            success: function(data) { 
					            	console.info("gantetu");
					            	console.info(data);
					            	var maxVal,scale="days",timeArr=[];
					            	for(var i=0;i<data.length;i++){
					            		var desc=data[i].desc.split("：");
					            		console.info(desc);
					            		var time=parseFloat(desc[1].substring(0,desc[1].length-1));
					            		timeArr.push(time);
					            		console.info(time);
					            		
					            	}
					            	for(var j=0;j<timeArr.length-1;j++){
					            		maxVal = Math.max(timeArr[j],timeArr[j+1])
					            		console.info(maxVal);
					            	}
					            	if(maxVal>46){
					            		scale="weeks"
					            	}else if(maxVal>46*7){
					            		scale="months"
					            	}
					            	//甘特图初始化
									$(".gantt").gantt({
										source: data,
										navigate: "scroll",
										months: ["1 月","2 月","3 月","4 月","5 月","6 月","7 月","8 月","9 月","10 月","11 月","12 月"],
										dow: ["日","一","二","三","四","五","六"], 
										scale: scale,
										maxScale: "months",
										minScale: "days",
									    itemsPerPage: 200,
							            waitText: "加载中...",
							            onRender:function() {
							            	for(var i=0;i<data.length;i++){
							            		if(i%2==0){
							            			$(".row"+i).addClass("del-bottom");
							            		}
							            		
							            	}
							        	}
									});
										$(".gantt").popover({
											selector: ".bar",
											title: "I'm a popover",
											content: "And I'm the content of said popover."
										});

										prettyPrint();
						 			
									
					            	}
					            });
					});
					
					
					
					
					$(function() {
						
						


					});
				
					//流程图
					$("#flowTab").on("shown.bs.tab",function(){
						
						var code=$("#code").val();
						if(code=='13'){
							code='06';
						}else if(code=='14'){
							code='12';
						}else if(code=='15'){
							code='08';
						}else if(code == '16'){
							code='04';
						}else if(code == '17'){
							code='01';
						}else if(code == '18'){
							code='01';
						}else if(code == '19'){
							code='03';
						}
						$(".project-flow-image-box").addClass("hidden");
						$(".project-flow-image-box"+code+"").removeClass("hidden");
						
						console.info("code-"+code);
						
						
						
						var projId = $(".projectId").val();
						//计划审核步骤操作信息
						var conPlanAuditInfo={};
						var subContractInfo={};
						var data={};
						data.projId=projId;
						   $.ajax({
				                type: 'POST',
				                url: "projectView/queryScheduleFlow",
				                data:data,
				                dataType: 'json',
				                success: function(data) {
				                	for(var i=0, l=data.length; i<l; i++){
				                		var info = data[i];
				                		if(info.step_id == '1502'){
				                			conPlanAuditInfo = info;//计划审核操作信息
				                		}
				                		if(info.step_id == '1604'){
				                			subContractInfo = info;//施工合同操作信息
				                		}
				                		
				                		
			                			var listItem = $('.flow-list [data-stepid="' + info.step_id + '"]');
			                			if(info.step_id == '1701' && subContractInfo!=null && info.operate_time<subContractInfo.operate_time){
			                				listItem.attr("data-endtime", format(info.operate_time, "all")).attr("data-starttime", (conPlanAuditInfo.operate_time ? format(conPlanAuditInfo.operate_time, "all") : "")).attr("data-stuffname", info.staff_name);
					                	}else{
			                				listItem.attr("data-endtime", format(info.operate_time, "all")).attr("data-starttime", (info.start_time ? format(info.start_time, "all") : "")).attr("data-stuffname", info.staff_name);
				                		}
		                				var title = listItem.data("original-title"),
		                				cont = "";
			                			cont += '<p>操作人员：' + listItem.data("stuffname");
			                			if(listItem.data("starttime")){
					                		cont += "</p><p>开始时间：" + listItem.data("starttime");
			                			}
			                			cont += '</p><p>结束时间：' + listItem.data("endtime");
			                			cont += '</p>';
			                			
			                			console.info("循环步骤id---"+info.step_id);
			                			
			                			if(info.step_id!='1104' &&  info.step_id!='1202'&& info.step_id!='1303'&& info.step_id!='14011'
			                				&& info.step_id!='1403'&& info.step_id!='1502'&& info.step_id!='1603'&& info.step_id!='1804'&& info.step_id!='1904'){
			                				console.info("循环步骤id2---"+info.step_id);
			                				listItem.attr("data-content", cont);
			                			}
			                			
			                			listItem.attr("title", title);
			                			
			                			if(info.photo_url !== ''){
				                			listItem.html('<img src="attachments/photo/' + info.photo_url + '">');
				                		}else{
				                			//没有头像使用默认头像
				                			listItem.html('<img src="images/common/flow/ferrinweb.png" class="opacity5">');
				                		}
				                	}
				                	
				                	console.info("flw---最后的id---"+data[data.length-1].step_id);
				                	
				                	var project=queryDispatcher(data[data.length-1].step_id,data[data.length-1].operate_time);
		                		    
		                			$(".flow-list").on("click", function(e){
		                				if($(e.target).is(".flow-list li img")){
		                					var li = $(e.target).parent();
		                					var stepId=li.attr('data-stepid');
		                					
		                					console.info("alert-stepId--"+stepId);
		                					if(stepId=='1104' ||  stepId=='1202'||stepId=='1303'|| stepId=='14011' || stepId=='1403'|| stepId=='1502'|| stepId=='1603'|| stepId=='1804'|| stepId=='1902'||stepId=='1904'){
		                						var url="projectView/manageRecordMain";
		                						$("body").cgetPopup({
		                		       				title: '提示',
		                		       				title: '审批历史',
		    			    						content: "#" + url + '?stepId=' + stepId+"&projId="+$(".projectId").val(),
		                		       				accept: popClose,
		                		       				size:"large",
		                		       				ahide:'true'
		                		       			});
				                				console.info("alert-stepId2--"+stepId);
				                			}else{
				                				if(!li.attr('data-content')) return;
				                				li.siblings().popover('hide');
				                				li.popover('show');
				                			}
		                				}else if($(e.target).is(".flow-list li")){
		                					var li = $(e.target);
			                				if(!li.attr('data-content')) return;
			                				li.siblings().popover('hide');
			                				li.popover('show');
		                				}else{
		                					$(this).children().popover('hide');
		                				}
				                	});
				                }
						   });
						   
						   var queryDispatcher=function(stepId,startTime){
							   var projId = $(".projectId").val();
							   var data={};
							   data.projId=projId;
							   data.stepId=stepId;
							   $.ajax({
								   type: 'POST',
					                url: "projectView/projectDispatchDetail",
					                data:data,
					                dataType: 'json',
					                success: function(data) {
					                	console.info(data);
			                		    console.info("pictureUrl--"+data.pictureUrl );
			                		    var operater='',nextStepId='';
			                		    
			                		    if(data){
			                		    	if(stepId=="1102"){
				                		    	//踏勘派工
				                		    	operater=data.surveyer;
				                		    	nextStepId="1103";
				                		    }else if(stepId=="1301"){
				                		    	//预算派工
				                		    	operater=data.budgeter;
				                		    	nextStepId="1302";
				                		    }else if(stepId=="1602"){
				                		    	//预算审核派工
				                		    	operater=data.budgeterAudit;
				                		    	nextStepId="1603";
				                		    }else if(stepId=="1903"){
				                		    	//终审派遣
				                		    	operater=data.settlementer;
				                		    	nextStepId="1904";
				                		    }
			                		    }
			                		    
			                		    if(stepId=="1102"||stepId=="1301"||stepId=="1602"||stepId=="1903"){
			                		    	var listItem = $('.flow-list [data-stepid="' + nextStepId + '"]');
				                			listItem.attr("data-endtime", format(startTime, "all")).attr("data-starttime", (startTime ? format(startTime, "all") : "")).attr("data-stuffname", operater);

			                				var title = listItem.data("original-title"),
			                				cont = "";
				                			cont += '<p>操作人员：' + listItem.data("stuffname");
				                			if(listItem.data("starttime")) cont += "</p><p>开始时间：" + listItem.data("starttime");
				                			cont += '</p>';
				                			listItem.attr("data-content", cont);
				                			listItem.attr("title", title);
				                			console.info("pictureUrl--"+data.pictureUrl );
				                			if(data.pictureUrl !== ''){
					                			listItem.html('<img src="attachments/photo/' + data.pictureUrl + '" style="border:2px solid #f1203d"> ');
					                		}else{
					                			//没有头像使用默认头像
					                			listItem.html('<img src="images/common/flow/ferrinweb.png" class="opacity5" style="border:2px solid #f1203d">');
					                		}
			                		    }
					                }
							   })
						   }
						   
					});
				});
			});
		});
	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->