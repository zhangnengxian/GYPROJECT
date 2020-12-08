<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
%>

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
       	<div class="panel panel-inverse tabs-mixin" id="">
			<div class="panel-heading">
               	<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                </div>
                <h4 class="panel-title">节假日、补班管理</h4>
			</div>
            <div class="panel-body">
               	<div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                     	<table id="festivalTable" class="table table-striped table-bordered nowrap" width="100%">
                          <thead>
                              <tr>
                                  <th>id</th>
                                  <th>假日名称</th>
                                  <th>开始日期</th>
                                  <th>结束日期</th>
                                  <th>是否启用</th>
                                  <th>类型</th>
                              </tr>
                          </thead>
                     	</table>
                   </div>
               </div>
		</div>
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" id="">
            	<div class="panel-heading">
	                <div class="panel-heading-btn">
	                    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	                </div>
                    <h4 class="panel-title">操作区</h4>
              </div>
              <div class="panel-body" id="festival_establish_panel_box"></div>
          	</div>
		</div>	  
	</div>
	      	        
	</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('节假日、补班管理 - 工程管理系统');
    
    $.getScript('projectjs/festival/festival.js?'+Math.random()).done(function () {
        festival.init();
    });

</script>
<!-- ================== END PAGE LEVEL JS ================== -->