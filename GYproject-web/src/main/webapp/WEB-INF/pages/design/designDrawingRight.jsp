<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="infodetails">
    <div class="clearboth form-box">
    	<form id="designDrawingForm">
    	</form>
    </div>
    
	<table id="drawingTable" class="table table-hover table-bordered nowrap m-t-40" width="100%">
		<thead>
         	<tr>
              <th>序号</th>
              <th>目录</th>
              <th>图号</th>
              <th>图幅</th>
              <th>实际张数</th>
              <th>折合张数</th>
              <th>备注</th>
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
    //表单样式适应
    $("#designDrawingForm").styleFit();
    drawingData.projId = 'No data returned...';
	if(trSData.designDrawingTable.json){
		$("#draw_projId").val(trSData.designDrawingTable.json.projId);
		$("#draw_projNo").val(trSData.designDrawingTable.json.projNo);
		//初始化图纸列表    
		drawingData.projId = trSData.designDrawingTable.json.projId;
	}
	drawingtableinit();



</script>
<!-- ================== END PAGE LEVEL JS ================== -->