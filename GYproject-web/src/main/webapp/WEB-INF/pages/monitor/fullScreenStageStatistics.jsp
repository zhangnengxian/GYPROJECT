<!-- <%=request.getRequestURI()%> -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<style>
/* .circleChoose{
	position:absolute;
	top:-30px;
} */
.dataTables_wrapper table.dataTable.dtr-inline.collapsed>tbody>tr>td:first-child:before, .dataTables_wrapper table.dataTable.dtr-inline.collapsed>tbody>tr>th:first-child:before{
background:#fff !important;
}
table.dataTable.dtr-inline.collapsed>tbody>tr>td:first-child:before, table.dataTable.dtr-inline.collapsed>tbody>tr>th:first-child:before{
color:#012841;
}
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span,
.pagination>.active>span:focus,
.pagination>.active>span:hover{
	background:#4c6f86 !important;
}
.dataTables_wrapper table.dataTable.dtr-inline.collapsed>tbody>tr>td:first-child:before, .dataTables_wrapper table.dataTable.dtr-inline.collapsed>tbody>tr>th:first-child:before{
	background:#fff !important;
}
td{
	position:relative;
}
label{
	/* position:absolute;
	top:37px;
	left:16px; */
}
.pagination>li>a{
	border:1px solid #fff !important;
}
.changeBackground0{
	background:url(/images/common/monitor/shown0.png) center;
}
.changeBackground1{
	background:url(/images/common/monitor/shown1.png) center;
}
.changeBackground2{
	background:url(/images/common/monitor/shown2.png) center;
}
.changeBackground3{
	background:url(/images/common/monitor/shown3.png) center;
}
.changeBackground4{
	background:url(/images/common/monitor/shown4.png) center;
}
.changeBackground5{
	background:url(/images/common/monitor/shown5.png) center;
}
.changeBackground6{
	background:url(/images/common/monitor/shown6.png) center;
}
.changeBackground7{
	background:url(/images/common/monitor/shown7.png) center;
}
.changeBackground8{
	background:url(/images/common/monitor/shown8.png) center;
}

.changeBackground0_0{
	background:url(/images/common/monitor/shown0_0.png) center;
}
.changeBackground0_1{
	background:url(/images/common/monitor/shown0_1.png) center;
}
.changeBackground0_2{
	background:url(/images/common/monitor/shown0_2.png) center;
}
.changeBackground0_3{
	background:url(/images/common/monitor/shown0_3.png) center;
}
.changeBackground0_4{
	background:url(/images/common/monitor/shown0_4.png) center;
}
.changeBackground0_5{
	background:url(/images/common/monitor/shown0_5.png) center;
}
.changeBackground0_6{
	background:url(/images/common/monitor/shown0_6.png) center;
}
.changeBackground0_7{
	background:url(/images/common/monitor/shown0_7.png) center;
}
.changeBackground0_8{
	background:url(/images/common/monitor/shown0_8.png) center;
}
.circleChoose .circle{
	background-size:65px 95px;
	width:70px;
	height:70px;
}
.circleChoose .line{
	width:53px;
}
.dataTables_length, div.dataTables_info{
	color:#fff;
}
</style>
<!-- begin #content -->

                    <div class="circleChoose">
	                    <table width="100%" border='0' style="margin-top:-34px">
	                    	<tr height='96px'>
	                    		<td  class="changeBackground0 circle" id='td_0' style='text-Align:center;background-repeat:no-repeat;'><label id='lab0'></label></td>
	                    		<td  class="line" style='background:url(/images/common/monitor/arrow.png) center;background-size:50px 60px;background-repeat:no-repeat;'></td>
	                    		<td  class="changeBackground1 circle"   id='td_1' style='text-Align:center;background-repeat:no-repeat;'><label id='lab1'></label></td>
	                    		<td class="line" style='background:url(/images/common/monitor/arrow.png) center;background-size:50px 60px;background-repeat:no-repeat;'></td>
	                    		<td  class="changeBackground2 circle" id='td_2' style='text-Align:center;background-repeat:no-repeat;'><label id='lab2'></label></td>
	                    		<td class="line"style='background:url(/images/common/monitor/arrow.png) center;background-size:50px 60px;background-repeat:no-repeat;'></td>
	                    		<td class="changeBackground3 circle" id='td_3' style='text-Align:center;background-repeat:no-repeat;'><label id='lab3'></label></td>
	                    		<td class="line"style='background:url(/images/common/monitor/arrow.png) center;background-size:50px 60px;background-repeat:no-repeat;'></td>
	                    		<td class="changeBackground4 circle" id='td_4' style='text-Align:center;background-repeat:no-repeat;'><label id='lab4'></label></td>
	                    		<td class="line"style='background:url(/images/common/monitor/arrow.png) center;background-size:50px 60px;background-repeat:no-repeat;'></td>
	                    		<td class="changeBackground5 circle" id='td_5' style='text-Align:center;background-repeat:no-repeat;'><label id='lab5'></label></td>
	                    		<td class="line" style='background:url(/images/common/monitor/arrow.png) center;background-size:50px 60px;background-repeat:no-repeat;'></td>
	                    		<td  class="changeBackground6 circle" id='td_6' style='text-Align:center;background-repeat:no-repeat;'><label id='lab6'></label></td>
	                    		<td class="line" style='background:url(/images/common/monitor/arrow.png) center;background-size:50px 60px;background-repeat:no-repeat;'></td>
	                    		<td  class="changeBackground7 circle" id='td_7' style='text-Align:center;'><label id='lab7'></label></td>
	                    		<td class="line" style='background:url(/images/common/monitor/arrow.png) center;background-size:50px 60px;background-repeat:no-repeat;'></td>
	                    		<td  class="changeBackground8 circle" id='td_8' style='text-Align:center;background-repeat:no-repeat;'><label id='lab8'></label></td>
	                    		<td>
	                    			<input type="hidden" id='status_0' value='0'/>
	                    			<input type='hidden' id='status_1' value='0'/>
	                    			<input type='hidden' id='status_2' value='0'/>
	                    			<input type='hidden' id='status_3' value='0'/>
	                    			<input type='hidden' id='status_4' value='0'/>
	                    			<input type='hidden' id='status_5' value='0'/>
	                    			<input type='hidden' id='status_6' value='0'/>
	                    			<input type='hidden' id='status_7' value='0'/>
	                    			<input type='hidden' id='status_8' value='0'/>
	                    		</td>
	                    	</tr>
	                    </table>
                    </div>
					<div>
						<table id="gasAuditTable" class="table table-striped table-bordered nowrap" width="100%" style="margin-top:15px;">
							<thead>
								<tr>
									<th >工程编号</th>
									<th >工程名称</th>
									<th >工程地点</th>
									<th >规模描述</th>
									<th >受理日期</th>
									<th >状态</th>
								</tr>
							</thead>
						</table>
					</div>
 

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
   /*  App.restartGlobalFunction();
    App.setPageTitle('工程阶段统计 - 工程管理系统'); */
    
    $.getScript('/projectjs/monitor/project-stage-statistics.js').done(function() {
        connectGasAudit.init();
	});
   if($.fn.DataTable.isDataTable('#gasAuditTable')){
       $("#gasAuditTable").cgetData();
   }else{
       handleConnectGasAudit();
   }
</script>
<!-- ================== END PAGE LEVEL JS ================== -->