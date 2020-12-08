<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<%--<div class="form-box" style="margin-bottom: -36px">--%>
    <%--<form class="form-horizontal " id="deptpopform">--%>
        <%--<input type="hidden" name="deptType" value="${deptType}"/>--%>
        <%--<div class="form-group col-lg-5 col-md-5 col-sm-5">--%>
            <%--<label class="control-label" for="">部门名称</label>--%>
            <%--<div>--%>
                <%--<input type="text" class="form-control input-sm field-editable" name="deptName" />--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</form>--%>
<%--</div>--%>
<div>
    <div class="p-t-6">
        <div class="mask-html text-center">
            <div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
        </div>
       </div>
			<div class="form-group col-md-3">
				<div>
					<input type="text" class="form-control input-sm "  id="projNo" name="projNo" placeholder="请输入工程编号"  value=""/>
				</div>
			</div>
        <table id="gasProjectTablePop" class="table table-striped table-bordered nowrap " width="100%">
            <thead>
                <tr>
                    <th></th>
                    <th></th>
                    <th width="50px">类型</th>
                    <th width="50px">工程名称</th>
                    <th width="50px">工程编号</th>
                    <th width="50px">单位名称</th>
                    <th width="50px">安装地址</th>
                    <th width="50px">现场代表</th>
                    <th width="50px">施工单位</th>
                    <th width="50px">施工合同号</th>
                    <%--<th width="50px">带气方式</th>--%>
                    <%--<th width="50px">压力等级</th>--%>
                    <%--<th width="50px">开通内容</th>--%>
                    <%--<th width="50px">验收情况</th>--%>
                </tr>
            <thead>
        </table>
    </div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程列表 - 工程施工管理系统 ');
    $("#deptpopform").styleFit();
    $.getScript('projectjs/withgas/gas-project-pop.js?'+Math.random()).done(function () {
        dept.init();
    });
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->