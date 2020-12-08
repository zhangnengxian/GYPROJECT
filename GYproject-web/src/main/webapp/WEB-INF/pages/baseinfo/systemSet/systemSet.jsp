<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
        <div class="col-sm-5 col-xs-12">
       	<div class="panel panel-inverse tabs-mixin" id="">
			<div class="panel-heading">
               	<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                </div>
                <h4 class="panel-title">系统设置</h4>
			</div>
            <div class="panel-body">
               <!-- 	<div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div> -->
                     <div id="menuTreeRoleManage" class="m-l-20"></div>
            </div>
                     
           </div>
		</div>
		<div class="col-sm-7 col-xs-12">
			<div class="panel panel-inverse" id="">
            	<div class="panel-heading">
	                <div class="panel-heading-btn">
	                    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	                </div>
                    <h4 class="panel-title">操作区</h4>
              </div>
              <div class="panel-body" id="auditLevel_panel_box">
              <div class="infodetails">
			      <div class="toolBtn f-r p-b-10 hidden editbtn">
			    	 <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 sys-saveBtn" >保存</a>
			         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 sys-cancelBtn">放弃</a>
				  </div>
	              <div class="clearboth form-box">
		              <form class="form-horizontal" id="systemSetForm" action="systemSet/saveData">
				       		<input type="hidden" id="menuId" name="menuId"/>
				       		<input type="hidden" id="sysId" name="sysId"/>
				       		<div class="form-group col-md-6">
					          <label class="control-label" for="isPrint">分公司</label>
					          <div>
					          	<select class="form-control input-sm " id="corpId" name="corpId">
					          		<option value=""></option>
					          		<c:forEach items="${corp}" var="enums">
					          			<option value="${enums.deptId}">${enums.deptName}</option>
					          		</c:forEach>
					          	</select>
					          </div>
					      </div>
						    <div class="form-group  col-md-6 ">
						        <label class="control-label" for="nodName">节点名称</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="nodName" name="nodName" />
						        </div>
						    </div>
						    <div class="form-group  col-md-6 ">
						        <label class="control-label" for="parentName">父节点名称</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="parentName" name="parentName"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 timeSpan">
						        <label class="control-label nod" for="unit">设计时长</label>
						        <div>
						            <input type="text" class="form-control input-sm field-editable"  id="timeSpan" name="timeSpan" data-parsley-min="1" data-parsley-type="integer"/>
						            <a href="javascript:;" class="btn btn-sm btn-default disabled alwaysusable">天</a>
						            <input type="text" class="hidden"  id="stepId" name="stepId"/>						            
						        </div>
						    </div>
						     <div class="form-group col-md-12 associateType">
						      <input type="text" class="hidden"  id="associateType" name="associateType"/>
						        <label class="control-label" for="unit">关联类型</label>
						        <div class="border:1px">
						        <c:forEach var="enums" items="${associateTypeEnum}">
						           <label>
						             <input type="checkbox" class="field-editable" name="checkVal" value='${enums.value}'/>
						             ${enums.message}
						           </label>     		
        				        </c:forEach>
						        </div>
						    </div>
						   
						</form>
					</div>
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
    App.setPageTitle('系统设置 - 工程管理系统');    
    //表单样式适应
    $('#systemSetForm').toggleEditState().styleFit();
    
    $.getScript('assets/plugins/jstree/dist/jstree.min.js').done(function() {
	    $.getScript('projectjs/baseinfo/system-set.js?v='+Math.random()).done(function () {
	        menuTree.init();
	    });
    });

</script>
<!-- ================== END PAGE LEVEL JS ================== -->