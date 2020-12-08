<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="clearboth form-box">
	<form class="form-horizontal" id="reassignmentForm">
		<input type="hidden" id="staffId">
		<input type="hidden" id="staffName">
	</form>
	<div class="tab-content">
		<div class="tab-pane fade active in btn-top active">
			<table id="budgetTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
				<thead>
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>职务</th>
				</tr>
				</thead>
			</table>
		</div>
	</div>
</div>

<script>
    App.restartGlobalFunction();
    $("#reassignmentForm").styleFit();
    $('.datepicker-default').datepicker({todayHighlight: true});


	$(function(){
		$.getScript("js/ellipsis.js").done(function(){
			handlebudgetTable();
		});
	});

	var searchData={post:41,corpId:1101};

	function handlebudgetTable(){
		$('#budgetTable').on( 'init.dt',function(){
			$(this).bindDTSelected(trSelectedBack,true); //默认选中第一行
		}).DataTable({
			language: language_CN,
			lengthMenu: [18],
			dom: 'Brtip',
			buttons: [],
			serverSide:true,//启用服务端模式，后台进行分段查询、排序
			ajax: {
				url: 'staff/queryStaffList',
				type:'post',
				data: function(d){$.each(searchData,function(i,k){ d[i] = k;});},
				dataSrc: 'data'
			},
			select: true,  //支持多选
			columns: [
				{'data':'staffId',"visible":false},
				{"data":"staffName"},
				{"data":"postName"}
			],
			columnDefs: [],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {}
		});
	};


	var trSelectedBack = function(v, i, index, t, json){
		$("#staffId").val(json.staffId);
		$("#staffName").val(json.staffName);
	};

</script>
