<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link rel="stylesheet" href="assets/plugins/jQuery-Gantt/index_files/css/style.css">
<link rel="stylesheet" href="assets/plugins/jQuery-Gantt/index_files/css/prettify.css">
<link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />

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

			<%--形象进度记录--%>
			<table id="graphicProgressTable" class="table table-bordered nowrap hidden" width="100%">
				<thead>
				<tr>
					<th>工程名称</th>
					<th>工序名称</th>
					<th>完成进度</th>
					<th>完成时间</th>
				</tr>
				</thead>
			</table>

			<div class="schedule-bar clearboth" style="margin-bottom: -10px;">
				<div class="progress progress-striped active">
					<div class="progress-bar totalProgress" style="width: 0%">工程进度 0%</div>
				</div>
			</div>
			<div class="form-group col-sm-6 col-xs-12 hidden">
				<!-- 新加字段 -->
				<label class="control-label" for="nuitProject1">工序名称</label>
				<div>
					<select class="form-control input-sm field-editable" id="nuitProject1"  name="nuitProject"  >
						<option value="1"></option>
						<c:forEach var="enums" items="${progressType}">
							<option value="${enums.gpId}">${enums.gpName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 hidden">
				<!-- 新加字段 -->
				<label class="control-label" for="finishProgress1">完成进度</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="finishProgress1" name="finishProgress" value="" />
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
	var projId = $(".projId").val();
	$("#projectScheduleTable").hideMask();

    var searchData = {};
        searchData.projId = projId;
    /**工程总进度查询*/
    var handleTotalProgress = function(){
//        searchData.registerDate = $(".endDate").val();
        $.ajax({
            type: 'POST',
            url: 'projectSchedule/getTotalProgress',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(searchData),
            success: function (data) {
                $(".totalProgress").html("工程进度"+data);
                $(".totalProgress").attr("style","width:"+data);
            }
        });
    }
    //定额计价
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
                    url: 'projectSchedule/queryProgress',
                    contenttype:"application/json;charset=utf-8",
                    data:function(d){
                        $.each(searchData,function(i,k){
                            d[i] = k;
                        });
                    },
                    datasrc: 'data'
                },
                columns: [
                    {"data":"nuitProject", responsivePriority: 1},
                    {"data":"sqUnit", "className": "text-center", responsivePriority: 5},
                    {"data":"allProgressNum", "className": "text-right", responsivePriority: 3},
                    {"data":"heapProgressNum", "className": "text-right", responsivePriority: 4},
                    {"data":"finishProgress", responsivePriority: 2}
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
        },
		graphicProgress = function() {
            "use strict";
            $('#graphicProgressTable').on( 'init.dt',function(){
                //隐藏遮罩
                $("#graphicProgressTable").hideMask();

                $("#graphicProgressTable").before($(".schedule-bar"));
            }).DataTable({
                language: language_CN,
                lengthMenu: [ 18 ],
                dom: 'Brtip',
                buttons: [],
				/*serverSide: true, */
                ajax: {
                    url: 'projectSchedule/queryProgress',
                    contenttype:"application/json;charset=utf-8",
                    data:function(d){
                        $.each(searchData,function(i,k){
                            d[i] = k;
                        });
                    },
                    datasrc: 'data'
                },
                columns: [
                    {"data":"projName", responsivePriority: 1},
                    {"data":"nuitProjectDes", responsivePriority: 2},
                    {"data":"finishProgress", responsivePriority: 3},
                    {"data":"registerDate", responsivePriority: 4}
                ],
                order: [[ 0, "asc" ]],
                columnDefs: [{
                    "targets": 3,
                    "render": function (data, type, row, meta) {
                        if (type === "display") {
                            if (data != null && data != "") {
                                return format(data, "default");
                            } else {
                                return "";
                            }
                        } else {
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
        };

    //形象进度||工程量进度
    var handleProjectStart = function(){
//        var projId = getProjectInfo().projId;
//        searchData.projId = projId;
        $.ajax({
            type: 'POST',
            url: 'projectSchedule/getProjStartDate',
            data: 'projId='+projId,
            dataType: 'json',
            success: function (data) {
                if(data.costType =="2"){//清单计价
                    //初始化进度详情
                    if (!$.fn.DataTable.isDataTable('#graphicProgressTable')) {
                    	graphicProgress();
                    }else{
                        $("#graphicProgressTable").cgetData(false);
                    }
                    $("#graphicProgressTable").removeClass("hidden");
                    $("#projectScheduleTable").addClass("hidden");
                    $(".totalProgress").html("工程进度"+data.progress.finishProgress ||"0%");
                    $(".totalProgress").attr("style","width:"+data.progress.finishProgress);
                }else{//定额计价
                    $("#projStartDate").val(format(data.startDate)||"");
					if (!$.fn.DataTable.isDataTable('#projectScheduleTable')) {
						progressViewInit();
					}else{
						$("#projectScheduleTable").cgetData(false);
					}
                    $("#graphicProgressTable").addClass("hidden");
                    $("#projectScheduleTable").removeClass("hidden");
                    handleTotalProgress();
                }
            }
        });
    }

    handleProjectStart();

//	if (!$.fn.DataTable.isDataTable('#projectScheduleTable')) progressViewInit();
	//
//	$.ajax({
//		type: 'POST',
//		url: 'projectSchedule/getTotalProgress',
//		contentType: "application/json;charset=UTF-8",
//		data: JSON.stringify(scheData),
//		success: function (data) {
//		  $(".totalProgress").html("工程进度"+data);
//		  $(".totalProgress").attr("style","width:"+data);
//		  $("#totalProgress").val(data);
//		}
//	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->