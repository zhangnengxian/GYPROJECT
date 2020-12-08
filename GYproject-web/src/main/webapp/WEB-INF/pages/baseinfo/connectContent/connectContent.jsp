<!-- accessoryItem.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

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
                <h4 class="panel-title">碰口内容列表</h4>
               
			</div>
            <div class="panel-body">
            	 <div id="connectContent" class=" pull-left">
                	<select id="connectContentDes" class="form-control input-sm field-editable width-150" name="connectContentDes" >
						<option value=""></option>
       					<c:forEach var="enums" items="${maintypeDeses}">
            				<option value="${enums.value}">${enums.message}</option>
        				</c:forEach>
    				</select>
    			</div>
               	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <div class="tab-content">
                   	<div class="tab-pane fade active in btn-top" id="default-tab-1">
                       	<table id="connectContentTable" class="table table-striped table-bordered nowrap" width="100%">
                            <thead>
                                <tr>
                                	<th></th>
                                    <th>单位类型</th>
                                    <th>碰口内容</th>
                                </tr>
                            </thead>
                       	</table>
                   	</div>
                   	</div>
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
                    <h4 class="panel-title">维护区</h4>
              </div>
              <div class="panel-body" id="connect_content_panel_box"></div>
          	</div>
		</div>	    	        
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('碰口内容 - 工程管理系统');
    
    $.getScript('projectjs/baseinfo/connect_content.js').done(function () {
    	connectContent.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->