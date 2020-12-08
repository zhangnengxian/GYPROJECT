<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content" class="content">
	<div class="row">
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" >
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">列表区</h4>
				</div>
				<div class="panel-body">
					<form id="filterDataTableForm" class="form-horizontal" data-parsley-validate="true" action="">
						<div class="form-group col-md-6 col-sm-12">
							<label class="control-label" >表名搜索：</label>
							<div>
								<input type="text" id="tableNameSearch" class="form-control" placeholder="表名搜索">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-12">
							<label class="control-label" >字段名搜索：</label>
							<div>
								<input type="text" id="columnCommentSearch" class="form-control" placeholder="字段搜索">
							</div>
						</div>

						<div class="form-group col-md-6 col-sm-12">
							<label class="control-label" >数据表：</label>
							<div>
								<select id="table" name="tableName" class="form-control input-sm " onchange="loadColumn(this.value,null,null)" data-parsley-required="true">
									<c:forEach var="table" items="${tables}">
										<option value="${table.tableName}">${table.tableComment }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-12">
							<label class="control-label" >数据列：</label>
							<div>
								<select id="column" class="form-control input-sm " onchange="loadFilterColumn(this.value)">
									<c:forEach var="column" items="${columns}">
										<option value="${column.columnName}">${column.columnComment }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group col-md-12 col-sm-12" style="color: #ffbab5">&nbsp;&nbsp;&nbsp;请选择要检索的条件(<span style="color: #9ee5ff">数据列</span>)：</div>
						<table id="filterColumnTable" width="100%" style="border-collapse:separate; border-spacing:0px 10px;"></table>
						<div id="searchBtn" class="form-group col-md-12 col-sm-12">
							<button type="button" onclick="searchBtn(0,18)" class="btn btn-info btn-sm" style="width: 100%;border: 0px;background: -webkit-linear-gradient(left,white,white,#6cc4d3,white,white);">查询</button>
						</div>
					</form>
					<!--数据列表-->
					<div class="form-group col-md-12 col-sm-12">
						<table id="dataTable" border="1" style="border-color:#ffe5ba" width="100%" >
							<thead id="dataTableThead"></thead>
							<tbody id="dataTableTbody"></tbody>
						</table>
						<div style="float: right;margin-right: 30px;">
							<a style="cursor: pointer" onclick="pagingClick('firstPage')"><img src="images/first.jpg"></a>&nbsp;
							<a style="cursor: pointer" onclick="pagingClick('prePage')" ><img src="images/pre.jpg"></a>
							第&nbsp;<span id="NPage">1</span>&nbsp;页 共&nbsp;<span id="totalPage">1</span>&nbsp;页&nbsp;&nbsp;
							每页<span id="pageSize">0</span>条 &nbsp;共<span id="totalRecord">0</span>条记录
							<a style="cursor: pointer" onclick="pagingClick('nextPage')"><img src="images/next.jpg"></a>
							<a style="cursor: pointer" onclick="pagingClick('endPage')"><img src="images/end.jpg"></a>&nbsp;&nbsp;
							<a style="cursor: pointer" onclick="pagingClick('refresh')"><img src="images/refresh.jpg"></a>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!--右侧操作区-->
		<div class="col-sm-6 col-xs-12" >
			<div class="panel panel-inverse" >
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">修改/查看区</h4>
				</div>
				<div class="panel-body" id="dataTableInfo_panel_box"></div>
			</div>
		</div>
	</div>
</div>



<script>
	App.restartGlobalFunction();
	$("#filterDataTableForm").styleFit();
	$('.datepicker-default').datepicker({todayHighlight: true});

	var pageSize=18;   //每页显示条数
	var totalRecord=0; //总记录数
	var currentPage=1; //当前页
	var startPage=1; //起始页
	//显示表格列数
	var columnLength=5;
	var columnList=null;


	$(function () {
		$('#filterColumnTable').find("tr").remove();
		searchBtn(startPage,pageSize);
	})




	$('#columnCommentSearch').on('keyup', function(event) {
		if (event.keyCode == "13") {
			var columnComment = $('#columnCommentSearch').val();
			var tableName = $("#table").find("option:selected").val();
			loadColumn(tableName, columnComment,"keyup");
		}
	});





	function loadColumn(tableName,columnComment,flag) {
		if (columnComment==null) {$('#filterColumnTable').find("tr").remove();}
		Base.subimt("dataTableInfo/queryColumnsByTableName","POST",{tableName:tableName,comment:columnComment},function (data) {
			columnList=data;
			$("#column").find("option").remove();//加载时先清空option;还可以使用$("#level3Menu").empty();方法清空
			$.each(JSON.parse(data), function(n, v) {
				$("#column").append('<option selected=true value='+v.columnName+'>'+v.columnComment+'</option>');
			});
		})
		if (flag!="keyup") {searchBtn(startPage, pageSize);}
	}





	function loadFilterColumn(columnName) {
		var dataType = getDataTypeBycolumnName(columnName);//字段类型
		//是否重复添加
		if (isNameRepeat(columnName)) {
			var columnComment = $("#column").find("option:selected").text();

			var input="";
			if (dataType=='datetime' || dataType=='date'){//日期类型
				input="<input type=\"text\" name=\"" + columnName + "\"  style=\"width: 100%;\" class=\"form-control input-sm datepicker-default\" data-parsley-required=\"true\" >";
			}else {
				input="<input type=\"text\" name=\"" + columnName + "\"  style=\"width: 100%;\" data-parsley-required=\"true\" >";
			}

			var html = "<tr style=\"text-align:center;\">" +
					" <td width=\"30%\" style='text-align: right' >" + columnComment + "：</td> " +
					"<td>"+input+"</td> " +
					"<td width=\"10%\" style='text-align:left'><a onclick='delColumn(this)' style='cursor:pointer;display: block;margin: auto'>&nbsp;删除</a></td> " +
					"</tr>"

			$("#filterColumnTable").append(html);
			$('.datepicker-default').datepicker({ todayHighlight : true });//日期控件
		}
	}




	function getDataTypeBycolumnName(columnName){
		var dataType="";
		if (columnList!=null) {
			$.each(JSON.parse(columnList), function (n, v) {if (v.columnName == columnName) {dataType = v.dataType;return false;}})
		}
		return dataType;
	}








	$('#tableNameSearch').on('keyup', function(event) {
		if (event.keyCode == "13") {
			var tableComment = $('#tableNameSearch').val();
			Base.subimt("dataTableInfo/queryTableByComment", "POST", {tableComment: tableComment}, function (data) {
				$("#table").find("option").remove();//加载时先清空option;还可以使用$("#level3Menu").empty();方法清空
				$.each(JSON.parse(data), function (n, v) {
					$("#table").append('<option selected=true value=' + v.tableName + '>' + v.tableComment + '</option>');
					loadColumn(v.tableName,null,'keyup');
				});
			})
		}
	});




	function searchBtn(startPage,pageSize) {
		var limit={startPage:startPage,pageSize:pageSize};
		$('#dataTable').find("tr").remove();
		var pf = $("#filterDataTableForm");
		if (pf.parsley().isValid()) { //验证必填是否已填写
			var formData =pf.serializeJson();
			formData.limit=limit;
			Base.subimt("dataTableInfo/queryThisTableMapListData","POST",JSON.stringify(formData),function (data) {
				var dataList = data.dataList;
				totalRecord= data.total;
				if (dataList!="") {
					appendThead(dataList[0].primaryKey, function (theadIds) {
						$.each(dataList, function (n, v) {
							var tbodyHTML = "<tr style=\"line-height: 30px;cursor: pointer;\" onclick='tRonclick(this)'>";
							tbodyHTML += "<td id=\"" + dataList[0].primaryKey + "\" style='display: none'>" + v[dataList[0].primaryKey] + "</td>";
							for (var i = 0; i < theadIds.length; i++) {
								tbodyHTML += "<td id=\"" + theadIds[i] + "\"  style='text-align: center;padding: 5px;' >" + v[theadIds[i]] + "</td>";
							}
							tbodyHTML += "<tr>";
							$("#dataTableTbody").append(tbodyHTML)
						});
					});
					$('#dataTableTbody tr:eq(0)').each(function () {
						tRonclick(this);
					})
				}
				$('#totalPage').html(Math.ceil(totalRecord/pageSize));
				$('#totalRecord').html(totalRecord);
				$('#pageSize').html(pageSize);
			})
		}else {
			pf.parsley().validate();
		}
	}



	function  appendThead(primaryKey,f) {
		var theadIds=new Array();
		var tableName = $("#table").find("option:selected").val();
		Base.subimt("dataTableInfo/queryColumnsByTableName","POST",{tableName:tableName,comment:null},function (data) {
			var theadHTML="<tr style=\"line-height: 40px;color: #ffffff;background: -webkit-linear-gradient(left,"+getColor()+","+getColor()+","+getColor()+", "+getColor()+");\">";

			$.each(JSON.parse(data), function(n, v) {//将主键添加到第一行
				if (v.columnName==primaryKey){
					theadHTML+="<th style='display: none'>"+v.columnComment+"</th>"
				}else {}
			});
			var count=0;
			$.each(JSON.parse(data), function(n, v) {//加非主键4列(多了显示不下)
				if (v.columnName!=primaryKey){
					if (count<columnLength) {
						theadIds.push(v.columnName);
						theadHTML+="<th id=\""+v.columnName+"\"  style='text-align: center'>"+v.columnComment+"</th>";;
					}
				}
				count++;
			});
			theadHTML+="<tr>";
			$("#dataTableThead").append(theadHTML);
			return f(theadIds);
		});
	}


	function delColumn(nowTr) {$(nowTr).parent().parent().remove();}
	function tRonclick(t) {$("#dataTableTbody tr").css("background",'white');loadUpdatePage(t);}


	function loadUpdatePage(t) {
		var tableName = $("#table").find("option:selected").val();
		var primaryKey = t.cells[0].id;
		var id = t.cells[0].innerHTML;
		Base.subimtReturnHTML("dataTableInfo/loadUpdatePage","POST",{tableName:tableName,primaryKey:primaryKey,id:id},function (data) {
			$("#dataTableInfo_panel_box").html(data);
		});
		$(t).css('background', '-webkit-linear-gradient(left,'+color()+','+color()+','+color()+', '+color()+', '+color()+', '+color()+')');
		if (tableName=='abandoned_record') {
			$("#updateColumn,#updateColumnCommentSearch").attr('disabled', true);
		}
	}





	function isNameRepeat(columnName) {
		var fInput = $('#filterDataTableForm input');
		var isNameRepeat=true;
		$.each(fInput,function (n,v) {
			if (v.name==columnName){
				alertInfo("已重复添加！");
				isNameRepeat=false;
				return false;
			}
		})
		return isNameRepeat;
	}





	function pagingClick(param){
		var strArr = pagingFun(param,totalRecord,pageSize,currentPage,startPage).split(",");
		currentPage=strArr[0];
		startPage=strArr[1];
		$('#NPage').html(currentPage);
		if (param=='refresh') {searchBtn(startPage,pageSize);return;}
		searchBtn(startPage,pageSize);
	}

	function pagingFun(param,totalRecord,pageSize,currentPage,startPage) {
		var totalPage=(totalRecord%pageSize==0?totalRecord/pageSize:Math.ceil(totalRecord/pageSize));
		if (param=='firstPage') {
			currentPage = 1;
			startPage = 0;
		}else if (param=='prePage') {
			currentPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
			startPage=startPage-pageSize>0?startPage-pageSize:0;
		}else if (param=='nextPage') {
			currentPage = parseInt(currentPage)+ 1> totalPage ? totalPage : parseInt(currentPage) + 1;
			startPage=parseInt(startPage)+parseInt(pageSize)>(totalPage-1)*pageSize?(totalPage-1)*pageSize:parseInt(startPage)+parseInt(pageSize);
		}else if (param=='endPage') {
			currentPage = totalPage;
			startPage=(totalPage-1)*pageSize;
		}
		return currentPage+','+startPage;
	}










































	function getColor(){var r=Math.floor(Math.random()*120)+136,g=Math.floor(Math.random()*120)+136, b=Math.floor(Math.random()*120)+136;return "rgb("+r+','+g+','+b+")";};
	function color(){var r=Math.floor(Math.random()*36)+220,g=Math.floor(Math.random()*36)+220, b=Math.floor(Math.random()*36)+220;return "rgb("+r+','+g+','+b+")";};


</script>