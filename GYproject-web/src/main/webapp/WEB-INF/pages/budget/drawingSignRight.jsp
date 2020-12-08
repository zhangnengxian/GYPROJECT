<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
   <div class="clearboth form-box">
   </div>
	<table id="drawingSignTable" class="table table-hover table-bordered nowrap" width="100%">
    	<thead>
	        <tr>
	            <th>图号</th>
	            <th>图纸目录</th>
	            <th>数量</th>
	            <th>签收</th>
	        </tr>
      </thead>
	</table>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    
    drawingsigntableinit();
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->