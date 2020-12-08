<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
   <div class="clearboth form-box">
   </div>
   <form id="qualitiesForm" data-parsley-validate="true">
	<table id="qualitiesTable" class="table table-hover table-bordered nowrap" width="100%">
    	<thead>
	        <tr>
	            <th>id</th>
	            <th>id</th>
	            <th>分部分项工程名称</th>
	            <th width="60">单位</th>
	            <th width="60">单价</th>
	            <th width="60">数量</th>
	            <th width="60">金额</th>
	        </tr>
      </thead>
      <tfoot>
      		<tr>
      		    <td></td>
      		    <td></td>
      			<td>合计</td>
      			<td></td>
      			<td></td>
      			<td></td>
      			<td class="total-amount"></td>
      		</tr>
      </tfoot>
	</table>
	</form>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    $("#qualitiesForm").toggleEditState(false).styleFit();
    var firstInit = function(){
    	if($.fn.DataTable.isDataTable('#qualitiesTable')){
    		dataBack();
    	}else{
    		qualitiesInit();
    	}	
    }();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->