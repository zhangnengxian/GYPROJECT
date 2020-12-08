<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <h4>监理计划</h4>
	<table id="supervisorTable" class="table table-hover table-bordered nowrap" width="100%">
           <tr>
               <th class="text-left">监理公司</th>
               <th class="text-right" width="120">已建项目</th>
               <th class="text-right" width="120">在建项目</th>
               <th class="text-right" width="120">待建项目</th>
               <th class="text-right" width="120">合计</th>
           </tr>
           <tr>
               <td>新疆金石建设项目管理有限公司</td>
               <td class="text-right">20</td>
               <td class="text-right">5</td>
               <td class="text-right">1</td>
               <td class="text-right">26</td>
           </tr>
           <tr>
               <td>新疆昆仑建设项目管理有限公司</td>
               <td class="text-right">10</td>
               <td class="text-right">5</td>
               <td class="text-right">3</td>
               <td class="text-right">18</td>
           </tr>
           <tr>
               <td>新疆建科建设项目管理有限公司</td>
               <td class="text-right">15</td>
               <td class="text-right">3</td>
               <td class="text-right">1</td>
               <td class="text-right">19</td>
           </tr>
	</table>
	<h4>分包计划</h4>
	<table id="cuPlan" class="table table-hover table-bordered nowrap" width="100%">
           <tr>
               <th class="text-left">分包公司</th>
               <th class="text-right" width="120">已建项目</th>
               <th class="text-right" width="120">在建项目</th>
               <th class="text-right" width="120">待建项目</th>
               <th class="text-right" width="120">合计</th>
           </tr>
            <c:forEach var="dto" items="${cuList}">
              <tr>
               <td class="text-left">${dto.NAME}</td>
               <td class="text-right">${dto.BUILDED}</td>
               <td class="text-right">${dto.BEBUILD}</td>
               <td class="text-right">${dto.TOBEBUILD}</td>
               <td class="text-right">${dto.TOTAL}</td>
              </tr>	             	
		    </c:forEach>
          
	</table>
    <h4 class="m-t-15">施工处施工计划</h4>
	<table id="constructPlan" class="table table-hover table-bordered m-b-10" width="100%">
	      <tr>
               <th class="text-left">分部</th>
               <th class="text-right" width="120">已建项目</th>
               <th class="text-right" width="120">在建项目</th>
               <th class="text-right" width="120">待建项目</th>
               <th class="text-right" width="120">合计</th>
           </tr>
          <c:forEach var="dto" items="${constructList}">
              <tr>
               <td class="text-left"> ${dto.NAME}</td>
               <td class="text-right">${dto.BUILDED}</td>
               <td class="text-right">${dto.BEBUILD}</td>
               <td class="text-right">${dto.TOBEBUILD}</td>
               <td class="text-right">${dto.TOTAL}</td>
              </tr>	             	
		    </c:forEach>
	</table>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
   
</script>
<!-- ================== END PAGE LEVEL JS ================== -->