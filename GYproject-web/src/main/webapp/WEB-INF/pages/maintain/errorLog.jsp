<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/projectjs/maintain/wangEditor.js"></script>

<div id="content" class="content">
	<div class="row">
		<div class="col-sm-2 col-xs-12">
			<div class="panel panel-inverse" >
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">条件区</h4>
				</div>
				<div class="panel-body">
					<form id="errorLogForm">
						<div class="form-group col-md-12 col-sm-12">
							<select id="charset" name="charset" onchange="loadContent(this.value)" class="form-control">
								<option value="GBK">GBK</option>
								<option value="UTF-8">UTF-8</option>
							</select>
						</div>
						<div class="form-group col-md-12 col-sm-12">
							<select id="fileName" name="fileName" onchange="loadContent(this.value)" class="form-control">
								<option value="project-web-error.log">project-web-error.log</option>
								<option value="project-web-debug.log">project-web-debug.log</option>
								<option value="project-web-info.log">project-web-info.log</option>
								<option value="project-web-warn.log">project-web-warn.log</option>
								<option value="project-web-interface-info.log">project-web-interface-info.log</option>
							</select>
						</div>
						<div class="form-group col-md-12 col-sm-12">
							<input type="text" id="numRead" name="numRead" placeholder="读取最新N行，默认200行" class="form-control">
						</div>
						<div class="form-group col-md-12 col-sm-12"><a class="btn btn-info btn-sm" style="width: 100%" onclick="readErrorLog()">查看</a></div>
					</form>
				</div>
			</div>
		</div>

		<div class="col-sm-10 col-xs-12" >
			<div class="panel panel-inverse" >
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title" id="rightTitle">内容区</h4>
				</div>
				<div class="panel-body">
					<div class="form-group col-md-12 col-sm-12">
						<div id="editorErrorLog" disabled="true" style="width: 100%"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>






<script>
	App.restartGlobalFunction();
	$("#formId").styleFit();

	var E = window.wangEditor;
	var editor = new E('#editorErrorLog');
	editor.create();
	$(".w-e-text,.w-e-text-container").height(document.body.offsetHeight);

	$(function () {
		Base.subimt("errorLog/readErrorLog","POST",{charset:null,fileName:null,numRead:null},function (data) {
			$(".w-e-text").html(data);
		})
	});


function readErrorLog() {
	$('#rightTitle').html($('#fileName').val());
	Base.subimt("errorLog/readErrorLog","POST",{charset:$('#charset').val(),fileName:$('#fileName').val(),numRead:$('#numRead').val()},function (data) {
		$(".w-e-text").html(data);
	})
}


</script>