<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link rel="stylesheet" href="/assets/plugins/jQuery-Gantt/index_files/css/style.css">
<link rel="stylesheet" href="/assets/plugins/jQuery-Gantt/index_files/css/prettify.css">
<link href="/assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="/assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="/assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="/assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<link href="/assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="/assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- ================== END PAGE LEVEL STYLE ================== -->

<div class="infodetails">
	<input class="projId hidden" name="projId" value="${projId}"/>
    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
	<div class="panel-body">    
	    <div class="tab-pane fade in btn-top" id="">
		    <ul class="nav nav-tabs p-t-5 p-l-5">
				<li class="active"><a href="#tab-1" data-toggle="tab" id="progressView">进度详情</a></li>
				<!-- <li class=""><a href="#tab-2" data-toggle="tab" id="progressPoint">进度打印</a></li> -->
			</ul>
			<div class="tab-content p-l-0 p-r-0 p-t-5" style="box-shadow:0 0 0 0">
				<div class="tab-pane fade active in btn-top" id="tab-1" >
					<input type="hidden" id="totalProgress" name="totalProgress" value=""/>
					<table id="projectScheduleTable" class="table table-striped table-bordered nowrap " width="100%">
				         <thead>
				             <tr>
				             	<th>分项工程名称</th>
				             	<th width="50">单位</th>
				                <th>预计总工程量</th>
				                <th>累计完成工程量</th>
				                <th>完成百分比</th>
				             </tr>
				         </thead>
				 	</table>
				</div>
			
				<div class="tab-pane fade  in btn-top" id="tab-2" >
					<div class="clearboth form-box">
						<div class="iframe-report-box">
				       		<iframe id="mainFrm" class="iframe-report" style="width: 750px; height: 1060px;border:1; overflow-y:auto;" scrolling="no"></iframe>
				       	</div>
			       	</div>
				</div>
		    <!-- <table id="projectScheduleTable" class="table table-striped table-bordered nowrap " width="100%">
		         <thead>
		             <tr>
		             	<th>分项工程名称</th>
		             	<th width="50">单位</th>
		                <th>预计总工程量</th>
		                <th>累计完成工程量</th>
		                <th>完成百分比</th>
		             </tr>
		         </thead>
		 	</table> -->
				<div class="schedule-bar clearboth" style="margin-bottom: -10px;">
			         <div class="progress progress-striped active">
			              <div class="progress-bar totalProgress" style="width: 0%">工程进度 0%</div>
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
	$(".infodetails").hideMask();
	var scheData = {};
	var projId = scheData.projId;
	scheData.projId = $(".projId").val();
	$("#projectScheduleTable").hideMask();
	
	//打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 
	//打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
	//初始化进度详情
    $("#progressView").on("shown.bs.tab",function(){
    	if (!$.fn.DataTable.isDataTable('#projectScheduleTable')) {
    		progressViewInit();
    		
    	}else{
    		$("#projectScheduleTable").cgetData(false);
    	}
    });
	
	var progressViewInit = function(){
		$('#projectScheduleTable').on( 'init.dt',function(){
		    	
		    	//隐藏遮罩
		    	$("#projectScheduleTable").hideMask();
		    	
		    	$("#projectScheduleForm").toggleEditState();
		
			    $('.datepicker-default').datepicker({
			        todayHighlight: true
			    });
		        $("#projectScheduleTable").before($(".schedule-bar"));
		    	
		    }).DataTable({
		    	language: language_CN,
		        lengthMenu: [ 18 ],
		        dom: 'Brtip',
		        buttons: [
		        ],
		        //serverSide: true, 
		        ajax: {
		            url: '/projectSchedule/queryProgress',
		            contenttype:"application/json;charset=utf-8",
		            data:function(d){
		                $.each(scheData,function(i,k){
		                    d[i] = k;
		                });
		            },
		            datasrc: 'data'
		        },
		        columns: [
		  			{"data":"NUITPROJECT", responsivePriority: 1},
		  			{"data":"SQUNIT", "className": "text-center", responsivePriority: 5},
					{"data":"ALLPROGRESSNUM", "className": "text-right", responsivePriority: 3},
					{"data":"HEAPPROGRESSNUM", "className": "text-right", responsivePriority: 4},
					{"data":"FINISHPROGRESS", responsivePriority: 2}
				],
				order: [[ 0, "asc" ]],
				columnDefs: [{
					targets: 2,
					render: function( data, type, row, meta ){
						if(type === "display"){
							return parseFloat(data).toFixed(2);
						}else{
							return data;
						}
					}
				},{
					targets: 3,
					render: function( data, type, row, meta ){
						if(type === "display"){
							if(data !=="" && data!==null){
								return parseFloat(data).toFixed(2);
							}else{
								return "";
							}
						}else{
							return data;
						}
					}
				},{
					targets: 4,
					render: function ( data, type, row, meta ) {
						if(data === null){
							data = '0';
						}
						if(type === "display"){
							var bar_style = parseInt(data) < 15 ? 'danger' : parseInt(data) < 32 ? 'warning' : parseInt(data) < 50 ? 'inverse' : parseInt(data) < 75 ? 'default' : parseInt(data) < 100 ? 'info' : 'success',
							animate = parseInt(data) < 100 ? " active" : "",
							tdcon = '<div class="progress progress-striped m-b-0' + animate + '"><div class="progress-bar text-right p-r-5 progress-bar-' + bar_style + '" style="width: ' + data + '">' + data + '</div></div>';
							return tdcon;
						}else{
							return data;
						}
				    }
				}],
				responsive: {
		        	details: {
		        		renderer: function ( api, rowIdx, columns ){
		        			return renderChild(api, rowIdx, columns);
		        		}
		        	}
		        }
		    });
	}
	if (!$.fn.DataTable.isDataTable('#projectScheduleTable')) progressViewInit();
	//初始化进度打印
    $("#progressPoint").on("shown.bs.tab",function(){
    	
    		progressPointInit();
    });
    var cptPath = '<%=basePath%>';
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var progressPointInit = function(){
    	var projId= $(".projId").val(),
    		to= encodeURIComponent(cjkEncode($("#totalProgress").val()));
    	var src=cptPath+"/ReportServer?reportlet=altimetricSurvey/progressPoint.cpt&projId="+projId+"&to="+to;
    	$("#mainFrm").attr("src",src); 
    }
		
$.ajax({
    type: 'POST',
    url: '/projectSchedule/getTotalProgress',
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify(scheData),
    success: function (data) {
      $(".totalProgress").html("工程进度"+data);
      $(".totalProgress").attr("style","width:"+data);
      $("#totalProgress").val(data);
    }
});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->