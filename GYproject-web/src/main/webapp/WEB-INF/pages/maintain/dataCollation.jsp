<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/projectjs/maintain/wangEditor.js"></script>

<div id="content" class="content">
	<div class="row">
		<div class="col-sm-3 col-xs-12">
			<div class="panel panel-inverse" >
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">菜单树</h4>
				</div>
				<div class="panel-body">
					<form id="formId">
						<div class="form-group col-md-12 col-sm-12">
							<label class="control-label">菜单名:</label>
							<div>
								<input type="text" class="form-control input-sm " id="menuNameSearch" placeholder="菜单名搜索">
							</div>
						</div>
						<div class="form-group col-md-12 col-sm-12">
							<div id="menuTree"></div>
						</div>
					</form>
				</div>

			</div>
		</div>


		<!--右侧操作区-->
		<div class="col-sm-9 col-xs-12" >
			<div class="panel panel-inverse" >
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">操作区</h4>
				</div>
				<div class="panel-body" id="dataCollation_panel_box">
					<div class="form-group col-md-12 col-sm-12">
						<input type="hidden" id="menuId" name="menuId">
						<button id="menuName" onclick="saveBtn()" style="width: 100%;font-size: 25px;">保存</button>
						<div id="editorDocument" disabled="true" style="width: 100%"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>






<script>
	App.restartGlobalFunction();
	$("#formId").styleFit();

	var documentHtml="";
	var E = window.wangEditor;
	var editor = new E('#editorDocument');
	editor.customConfig.uploadImgServer = 'problemDocument/uploadImg';
	editor.customConfig.uploadFileName = 'img';
	editor.customConfig.uploadImgMaxSize = 5 * 1024 * 1024; // 将图片大小限制为 3
	editor.customConfig.onchange=function(html){documentHtml=html;if ($('#menuId').val()==""){alertInfo("请选择菜单！")}};
	editor.create();
	$(".w-e-text,.w-e-text-container").height(document.body.offsetHeight);



	$.getScript('assets/plugins/jstree/dist/jstree.min.js').done(function() {
		menuTree();
	});



	function menuTree() {
		$('#menuTree').jstree({
			"core": {
				"themes": { "responsive": false },
				"check_callback": true,
				'data': {"dataType": "json", 'url':'dataCollation/getMenuTreeData', 'data':{}}
			},
			"types": {
				"default": {
				//"icon": "fa fa-folder text-warning fa-lg"
				"icon": "${pageContext.request.contextPath}/images/folder.png"
				}
			},
			"plugins": [ "types"]
		}).one('loaded.jstree', function() {
			//$("#menuTree").jstree("open_all");
		}).bind('click.jstree', function(event) {});
		

	};






	$('#menuNameSearch').on('keyup',function (e) {
		$("#menuTree").jstree("open_all");
		$('.jstree-open').removeClass("jstree-open").addClass("jstree-closed");
		$('#menuTree a').children('i').each(function () {
			$(this).css("background-image","url(images/folder.png)");
		});
		var menuName = $('#menuNameSearch').val();
		$('#menuTree a').each(function () {
			if(menuName!='' && $(this).text().indexOf(menuName)>= 0 ) {
				$(this).css("color",'red');
				$(this).parents('li').each(function () {
					$(this).css("color",'red');
					$(this).removeClass("jstree-closed").addClass("jstree-open");
				})
				$(this).parents('li').children('a').children('i').each(function () {
					$(this).css("background-image","url(images/open_red.png)");
				})
			}else {
				$(this).css("color",'black');
			}
		})
	});




	var timmer=null;
	$(document).off("click", "#menuTree .jstree-node").on("click","#menuTree .jstree-node",function(){
		if($(this).attr("aria-selected")=="true"){
			$("#menuId").val(this.id);
			$("#menuName").html($("#"+this.id+">a").text());
			Base.subimt("dataCollation/queryThisTableData","POST",{menuId:$('#menuId').val()},function (data) {
				(data!="" && JSON.parse(data)!=null)?$(".w-e-text").html(JSON.parse(data).document):$(".w-e-text").html("");
			});
			window.clearInterval(timmer);
			timmer=window.setInterval(function () {$('#menuName').css('background','-webkit-linear-gradient(left,'+getColor()+','+getColor()+','+getColor()+','+getColor()+')');},5000);
		}
	});



	function saveBtn() {
		Base.subimt("dataCollation/saveUpdateThisTableData","POST",{menuId:$('#menuId').val(),document:documentHtml},function (data) {
			data=='true'?alertInfo("保存成功！"):alertInfo("保存失败！");
		});
	}


	var getColor =function(){var r=Math.floor(Math.random()*156)+100, g=Math.floor(Math.random()*156)+100,b=Math.floor(Math.random()*156)+100;return "rgb("+r+','+g+','+b+")";};

</script>