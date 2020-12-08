<!-- <%=request.getRequestURI()%> -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-12 -->
        <div class="col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">工程阶段统计</h4>
                </div>
                <div class="panel-body">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <div class="clearboth">
	                    <table width="100%" border='0'>
	                    	<tr height='96px'>
	                    		<td width='60px' id='td_0' style='background:url(/images/common/monitor/show0.png);text-Align:center'><label id='lab0'></label></td>
	                    		<td width='40px' style='background:url(/images/common/monitor/arrow.png)'></td>
	                    		<td width='60px' id='td_1' style='background:url(/images/common/monitor/show1.png);text-Align:center'><label id='lab1'></label></td>
	                    		<td width='40px' style='background:url(/images/common/monitor/arrow.png)'></td>
	                    		<td width='60px' id='td_2' style='background:url(/images/common/monitor/show2.png);text-Align:center'><label id='lab2'></label></td>
	                    		<td width='40px' style='background:url(/images/common/monitor/arrow.png)'></td>
	                    		<td width='60px' id='td_3' style='background:url(/images/common/monitor/show3.png);text-Align:center'><label id='lab3'></label></td>
	                    		<td width='40px' style='background:url(/images/common/monitor/arrow.png)'></td>
	                    		<td width='60px' id='td_4' style='background:url(/images/common/monitor/show4.png);text-Align:center'><label id='lab4'></label></td>
	                    		<td width='40px' style='background:url(/images/common/monitor/arrow.png)'></td>
	                    		<td width='60px' id='td_5' style='background:url(/images/common/monitor/show5.png);text-Align:center'><label id='lab5'></label></td>
	                    		<td width='40px' style='background:url(/images/common/monitor/arrow.png)'></td>
	                    		<td width='60px' id='td_6' style='background:url(/images/common/monitor/show6.png);text-Align:center'><label id='lab6'></label></td>
	                    		<td width='40px' style='background:url(/images/common/monitor/arrow.png)'></td>
	                    		<td width='60px' id='td_7' style='background:url(/images/common/monitor/show7.png);text-Align:center'><label id='lab7'></label></td>
	                    		<td width='40px' style='background:url(/images/common/monitor/arrow.png)'></td>
	                    		<td width='60px' id='td_8' style='background:url(/images/common/monitor/show8.png);text-Align:center'><label id='lab8'></label></td>
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
                    <table id="gasAuditTable" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th width="100">工程编号</th>
                                <th width="120">工程名称</th>
                                <th>工程地点</th>
                                <th width="150">规模描述</th>
                                <th width="110">受理日期</th>
                                <th width="80">状态</th>
                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程阶段统计 - 工程管理系统');
    
    $.getScript('projectjs/project/project-stage-statistics.js').done(function() {
        connectGasAudit.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->