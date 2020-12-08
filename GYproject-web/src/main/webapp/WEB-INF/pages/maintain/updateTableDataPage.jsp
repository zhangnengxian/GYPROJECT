<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="clearboth form-box">
		<form id="updateTableDataForm" class="form-horizontal" data-parsley-validate="true" action="">
			<input type="hidden" id="tableName" name="tableName" value="${tableName}">
			<input type="hidden" id="primaryKey"  value="${primaryKey}">
			<input type="hidden" id="primaryKeyVal" value="${primaryKeyVal}">
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >数据列：</label>
				<div>
					<select id="updateColumn" class="form-control input-sm " onchange="updateLoadFilterColumn(this.value)" data-parsley-required="true">
						<c:forEach var="column" items="${columns}">
							<option value="${column.columnName}">${column.columnComment }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >字段名：</label>
				<div>
					<input type="text" id="updateColumnCommentSearch" class="form-control" placeholder="字段搜索">
				</div>
			</div>


			<hr width="100%"/>
			<div class="form-group col-md-12 col-sm-12" style="color: #ffbab5">请选择需要修改的数据列（<span style="color: #9fffc8">set字段值</span>）：</div>
			<table id="setColumnTable" width="100%" style="border-collapse:separate; border-spacing:0px 10px;"></table>
			<div id="saveBtn" class="form-group col-md-12 col-sm-12"></div>
		</form>

		<div><span style="color: #ff9bda">条件(<span style="color: #72ffe9">where</span>)为：</span>${primaryKeyComment}=${primaryKeyVal}</div>

		<form id="tableDataShowForm" class="form-horizontal" data-parsley-validate="true" action="">
			<hr width="100%"/>
		</form>
	</div>
</div>


<script>
	App.restartGlobalFunction();
	$(".infodetails").hideMask();//隐藏loading

	$("#updateTableDataForm").styleFit();

	var dataMap=null;
	var columnList=null;
	$(function () {
		columnList=getColumnList();
		dataMap=queryThisTableDataById();
		showTableDataInfo(dataMap);
	})



	 function getColumnList() {
		var columns=null;
		Base.subimt("dataTableInfo/queryColumnsByTableName","POST",{tableName:$("#tableName").val(),comment:""},function (data) {
			columns=data;
		});
		return columns;
	}



	function showTableDataInfo(dataMap) {
		$("#tableDataShowForm div").remove();
		if (columnList!=null && dataMap!=null) {
			$.each(JSON.parse(columnList), function (n, v) {
				for (var key in dataMap) {
					if (v.columnName == key) {
						var HTML = "";
						var dataType = getDataTypeBycolumnName(key);
						if (dataType == "text" || dataType == "longtext") {
							HTML = "<div class=\"form-group col-md-12 col-sm-12 \">" +
									"<label class=\"control-label\">" + v.columnComment + "</label>" +
									"<div><textarea name=\"" + key + "\"  class=\"form-control\" rows=\"6\" readonly=\"true\" >" + dataMap[key] + "</textarea></div>" +
									"</div>"
						} else {
							HTML = "<div class=\"form-group col-md-6 col-sm-12 \">" +
									"<label class=\"control-label\">" + v.columnComment + "</label>" +
									"<div><input type=\"text\" name=\"" + key + "\" value=\"" + dataMap[key] + "\" class=\"form-control input-sm\" readonly=\"true\" /></div>" +
									"</div>"
						}
						$("#tableDataShowForm").append(HTML);
					}
				}
			});
		}
		$("#tableDataShowForm").styleFit();
	}







	function updateLoadFilterColumn(columnName) {
		var columnComment = $("#updateColumn").find("option:selected").text();

		var englishStr = getEnglishStr(columnComment).replace(/,/g,'').toUpperCase();
		var ID = englishStr.indexOf('ID');
		var NO = columnComment.indexOf("编号");

		if (isNameRepeat(columnName) && columnName!=$('#primaryKey').val() && ID==-1 && NO==-1) {//不重复非主键不包含

			var dataType = getDataTypeBycolumnName(columnName);//字段类型

			var input = "";
			if (dataType=='date' || dataType=='datetime'){//日期格式
				input = "<input type=\"text\"name=\"" + columnName + "\" value=\"" + dataMap[columnName] + "\"  class=\"form-control input-sm datepicker-default\" data-parsley-required=\"true\" />"

			} else if (dataType=='text'|| dataType=='longtext') {//文本格式
				input="<textarea name=\"" + columnName + "\"  class=\"form-control\" rows=\"6\" data-parsley-required=\"true\">"+ dataMap[columnName] +"</textarea>"

			}else {
				input = "<input type=\"text\" name=\"" + columnName + "\" value=\"" + dataMap[columnName] + "\"  data-parsley-required=\"true\" style=\"width: 100%;\">";
			}


			var html = "<tr style=\"text-align:center;\">" +
					" <td width=\"30%\" style='text-align: right' >" + columnComment + "：</td> " +
					"<td>" + input + "</td> " +
					"<td width=\"10%\" style='text-align:left'><a onclick='delColumn(this)' style='cursor:pointer;display: block;margin: auto'>&nbsp;删除</a></td> " +
					"</tr>"

			$("#setColumnTable").append(html);
		}
		$('.datepicker-default').datepicker({ todayHighlight : true });//日期控件
		$("#saveBtn button").remove();
		$("#saveBtn").append("<button  type=\"button\" onmouseover='setColor(this)' onmouseout='removeColor(this)' onclick=\"saveBtn()\" class=\"btn  btn-sm\" style=\"width: 100%;border:0px;background: -webkit-linear-gradient(left,white,white,"+getColor()+","+getColor()+", "+getColor()+", "+getColor()+", "+getColor()+",white,white); \">保存</button>")
	}



	function getDataTypeBycolumnName(columnName){
		var dataType="";
		if (columnList!=null) {
			$.each(JSON.parse(columnList), function (n, v) {if (v.columnName == columnName) {dataType = v.dataType;return false;}})
		}
		return dataType;
	}



	function delColumn(nowTr) {
		var setColumnTableInput = $('#setColumnTable tr');
		i=0;
		$.each(setColumnTableInput,function (n,v) {i++;})
		if (i==1){$("#saveBtn button").remove();}
		$(nowTr).parent().parent().remove();
	}


	function getEnglishStr(str){
		if(/[a-z]/i.test(str)){
			return str.match(/[a-z]/ig)+"";
		}
		return "";
	}


	function isNameRepeat(columnName) {
		var formInput = $('#updateTableDataForm input');
		var isNameRepeat=true;
		$.each(formInput,function (n,v) {
			if (v.name==columnName){
				alertInfo("已重复添加！");
				isNameRepeat=false;
				return false;
			}
		})
		return isNameRepeat;
	}



	   function queryThisTableDataById(){
		var criteria={};
		criteria[$('#primaryKey').val()]=$('#primaryKeyVal').val()
		criteria.tableName=$("#tableName").val();
		var dataMap=null;
		Base.subimt("dataTableInfo/queryThisTableDataById","POST",JSON.stringify(criteria),function (data) {
			dataMap=data;
		});
		return dataMap;
	}



	$('#updateColumnCommentSearch').on('keyup', function(e) {
		//if (e.keyCode =="13") {
			var columnComment = $('#updateColumnCommentSearch').val();
			var tableName = $("#tableName").val();
			updateLoadColumn(tableName, columnComment)
		//}
	});



	function updateLoadColumn(tableName,columnComment) {
		Base.subimt("dataTableInfo/queryColumnsByTableName","POST",{tableName:tableName,comment:columnComment},function (data) {
			$("#updateColumn").find("option").remove();//加载时先清空option;还可以使用$("#level3Menu").empty();方法清空
			$.each(JSON.parse(data), function(n, v) {
				$("#updateColumn").append('<option selected=true value='+v.columnName+'>'+v.columnComment+'</option>');
			});
		})
	}


	function saveBtn() {
		var pf = $("#updateTableDataForm");
		if (pf.parsley().isValid()) { //验证必填是否已填写
			var where={};
			where[$("#primaryKey").val()]=$("#primaryKeyVal").val();
			var formData =pf.serializeJson();
			formData["where"]=where;
			Base.subimt("dataTableInfo/updateThisTableDataById","POST",JSON.stringify(formData),function (data) {
				alertInfo(data.msg);
				$('#setColumnTable').remove();
				$('#saveBtn').remove();
				showTableDataInfo(queryThisTableDataById());
			});
		}else {
			pf.parsley().validate();
		}
	}



















































	function setColor(button) {$(button).css('background', '-webkit-linear-gradient(left,white,white,'+getColor()+','+getColor()+','+getColor()+', '+getColor()+', '+getColor()+', '+getColor()+',white,white)');}
	function removeColor(button) {$(button).css('background', '-webkit-linear-gradient(left,white,white,'+getColor()+','+getColor()+','+getColor()+', '+getColor()+', '+getColor()+', '+getColor()+',white,white)');}
	function getColor(){var r=Math.floor(Math.random()*120)+136,g=Math.floor(Math.random()*120)+136,b=Math.floor(Math.random()*120)+136;return "rgb("+r+','+g+','+b+")";};

</script>
