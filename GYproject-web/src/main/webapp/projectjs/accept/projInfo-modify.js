/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var projInfoModifyTable;
var scaleTable;
var dataPopTable;
var accessoryTable;
/**查询条件  默认待勘察*/
var searchData={};
var accessoryData={};
var detailSearchData = {};
var handleProjInfoModify = function() {
	"use strict";
    if ($('#projInfoModifyTable').length !== 0) {
    	projInfoModifyTable= $('#projInfoModifyTable').on( 'init.dt',function(){
    		//加载页面
    		$("#proj_info_modify_panel_box").cgetContent("projInfoModify/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#projInfoModifyTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#projInfoModifyTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//信息更正监听方法
   			modifyMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '更正', className: 'btn-sm btn-query confirmBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'projInfoModify/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/confirm/json/project_confirm.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"projId",className:"none never"}, 
	  			{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"projStatusDes"},
				{"data":"overdue",className:"none never"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":4,
				 "visible":false
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
    
};

//弹屏监听方法
var searchMonitor = function(){
	
	//查询按钮弹出屏查询
	$(".searchBtn").on("click",function(){
			var url = "#projectConfirm/projectConfirmSearch";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#stateSelect,#projInfoModifyTable_filter input").on("change",function(){
		var stateSelect = $("#stateSelect").find("option:selected");
		var projNo = $("#projInfoModifyTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;
		$("#projInfoModifyTable").cgetData(true);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#projInfoModifyTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	
	searchData = $("#confirmSearchForm").serializeJson();
	var projNo = $("#projInfoModifyTable_filter input").val();
	searchData.projNo = projNo;
	var stateSelect = $("#stateSelect").val();
	searchData.projStatusId = stateSelect;
    $("#projInfoModifyTable").cgetData(true,confirmTableCallBack);  //列表重新加载
    $(".iframe-big-box").addClass("hidden");
	$("#project_confirm_panel_box").removeClass("hidden");
    
}

var confirmTableCallBack = function(){
	var len = $('#projInfoModifyTable').DataTable().ajax.json().data ? $('#projInfoModifyTable').DataTable().ajax.json().data.length : $('#projInfoModifyTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $("#projInfoModifyForm")[0].reset();
		 $(':input','#scaleTableForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
		 $(".editbtn").addClass("hidden");
		 $("#projImage").attr("src", "");
		 $("#projPhoto").addClass("disabled");
		 $("#projPicture").addClass("disabled");
		 $("#projInfoModifyForm,#scaleTableForm").toggleEditState(false);
	}else{
		 $(".editbtn").removeClass("hidden");
		 $("#projPhoto").removeClass("disabled");
	}
}


//确认监听方法
var modifyMonitor = function(){
	$(".confirmBtn").on("click",function(){
		if($("#projInfoModifyTable").find("tr.selected").length>0){
			$(".iframe-big-box").addClass("hidden");
			$("#project_confirm_panel_box").removeClass("hidden");
			$("#projPicture").removeClass("disabled");
			//切换可编辑状态
			$("#projInfoModifyForm,#scaleTableForm").toggleEditState(true);
			//$("#backReason").attr("data-parsley-required","false");
			$(".backReason").addClass("hidden");
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要更正信息的记录！');
		}
	});
};
var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('projInfoModifyForm','projInfoModify/viewProjectInfoModify','',scaleDetailRefresh);
}

var scaleDetailRefresh = function(){
	$("#projPhoto").removeClass("disabled");
	$(".editbtn").addClass("hidden");
	$(".backReason").addClass("hidden");
	$("#projPicture").addClass("disabled");
	var ltypeId = $(".projLtypeId").val();
	if(!ltypeId){
		detailSearchData.projId = "-1";
		//$('#scaleTable').cgetData();
		$('#scaleTable').DataTable().ajax.reload();
		return;
	}
	ltypeId = ltypeId.split(",");
	$("input[name='projLtype']").attr("checked",false);
	for(var i=0;i<ltypeId.length;i++){
		$("input[name='projLtype'][value="+ltypeId[i]+"]").attr("checked","checked");
	}
	var len = $('#projInfoModifyTable').DataTable().ajax.json().data ? $('#projInfoModifyTable').DataTable().ajax.json().data.length : $('#projInfoModifyTable').DataTable().ajax.json().length;
	if(len>0){
		detailSearchData.projLtypeId = $(".projLtypeId").val();
		detailSearchData.projId = $("#projId").val();
		if($.fn.DataTable.isDataTable('#scaleTable')){
			//初始化过
			$("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
		}else{
			sacletableinit();
		}
	}else{
		detailSearchData.projId = "-1";
		$('#scaleTable').DataTable().ajax.reload();
		$("#projPhoto").addClass("disabled");
	}
	if($("#pictureUrl").val()){
		$('#projImage').attr("src","attachments/picture/"+$("#pictureUrl").val());
	}else{
		$('#projImage').attr("src","");
	}
	$("#projInfoModifyForm,#scaleTableForm").toggleEditState(false);
}

var scaleTableCallBack = function(){
	if(detailFlag){
   	 	$('#scaleTableForm').toggleEditState(true);
	}else{
		$('#scaleTableForm').toggleEditState(false);
	}
}

var projInfoModify = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handleProjInfoModify();
        	});	
        }
    };
}();

/**
 * 初始化规模列表
 */
var sacletableinit= function() {
	"use strict";
    if ($('#scaleTable').length !== 0) {
    	scaleTable= $('#scaleTable').on( 'init.dt',function(){
    		$('#scaleTable').hideMask();
	        inputMonitor();
	        scaleTableCallBack();
        }).DataTable({
           language: language_CN,
           lengthMenu: [18],
           dom: 'Brt',
           buttons: [
           ],
           /*ajax: 'projectjs/confirm/json/scale.json',*/
           //启用服务端模式，后台进行分段查询、排序
		   serverSide:false,
           ajax: {
               url: 'projInfoModify/queryScaleDetail',
               type:'post',
               data: function(d){
                  	$.each(detailSearchData,function(i,k){
                  		d[i] = k;
                  	});
                  	
               },
               dataSrc: 'data'
           },
           responsive: {
           	   details: {
        		   renderer: function ( api, rowIdx, columns ){
        			   return renderChild(api, rowIdx, columns);
        		   }
        	   }
           },
           columns: [
	  			{"data":"projStypeDes", responsivePriority: 1},
	  			{"data":"tonnage",className:"text-right", responsivePriority: 3},
	  			{"data":"searNum",className:"text-right", responsivePriority: 6},
	  			{"data":"num",className:"text-right", responsivePriority: 5},
	  			{"data":"houseNum",className:"text-right", responsivePriority: 4},
	  			{"data":"gasConsumption",className:"text-right", responsivePriority: 7},
	  			{"data":"scaleAmount",className:"text-right", responsivePriority: 2}
			],
			columnDefs: [{
				"targets":0,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 6, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				targets: 1,
				/*
				 * render属性
				 * 方法携带四个参数
				 * data: 该单元格的原始数据，也就是默认显示的那些数据
				 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
				 * row: 但前行的所有原始数据
				 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
				 */
				render: function ( data, type, row, meta ) {
					if(type === "display"){
						if(data === null){
							data = "";
						}
						var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_tonnage' id='" + row.projStypeId + "_tonnage'  data-parsley-maxlength='9' data-parsley-type='number' value="+data+"></div>"
						return tdcon;
					}else{
						return data;
					}}
			    },{
			    	targets: 2,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_searNum' id='" + row.projStypeId + "_searNum' data-parsley-maxlength='9' data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}	
			    },{
			    	targets: 3,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_num' id='" + row.projStypeId + "_num'  data-parsley-maxlength='9' data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}	}
			    },{
			    	targets: 4,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_houseNum' id='" + row.projStypeId + "_houseNum' data-parsley-maxlength='9' data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}
			    },{
			    	targets: 5,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}else if(data !==null && data!==""){
								data = data.toFixed(2);
							}
							var  tdcon="<div class='tdInnerInput'><input type='hidden' name='"+row.projStypeId+"_projStypeId' id='"+row.projStypeId+"_projStypeId' value="+row.projStypeId+">" +
									    "<input class='hidden' name='" + row.projStypeId + "_projLtypeId' id='" + row.projStypeId + "_projLtypeId' data-parsley-maxlength='9' data-parsley-type='number' value="+row.projLtypeId+">" +
									    "<input class='hidden' name='" + row.projStypeId + "_projStypeDes' id='" + row.projStypeId + "_projStypeDes'   value="+row.projStypeDes+">"+
									    "<input class='hidden' name='" + row.projStypeId + "_scaleId' id='" + row.projStypeId + "_scaleId'   value="+row.scaleId+">"+
									    "<input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_gasConsumption' id='" + row.projStypeId + "_gasConsumption'  data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}
			    },{
					targets: 6,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}else if(data !==null && data!==""){
								data = data.toFixed(2);
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-not-editable' name='" + row.projStypeId + "_scaleAmount' id='" + row.projStypeId + "_scaleAmount'  data-parsley-maxlength='17' data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}
				    }/*,{
			    	targets: 6,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
								var  tdcon="<input type='hidden' name='"+row.projStypeId+"_projStypeId' id='"+row.projStypeId+"_projStypeId' value="+row.projStypeId+"><input name='" + row.projStypeId + "_scaleId' id='" + row.projStypeId + "_scaleId' type='checkbox' value="+data+">";
								return tdcon;
							}
							if(data!==null){
								var  tdcon="<input type='hidden' name='"+row.projStypeId+"_projStypeId' id='"+row.projStypeId+"_projStypeId' value="+row.projStypeId+"><input name='" + row.projStypeId + "_scaleId' id='" + row.projStypeId + "_scaleId' type='checkbox' checked value="+data+">";
								return tdcon;
							}
							
						}else{
							return data;
						}}	
			    }*/],
			ordering:false
       });
   }
};

var inputMonitor = function(){
	$(".tdInnerInput input").on("change",function(){
		var name = $(this).attr("name");
		var checkBoxId = name.split("_")[0]+"_scaleId";
		if($(this).val()){
			$("#"+checkBoxId).attr("checked","checked");
		}else{
			 var input = $(this).parents("tr").find(".tdInnerInput input").not("[type='checkbox']");
			 for(var i=0;i<input.length;i++){
				 if(input.eq(i).val()){
					 return false;
				 }
			 }
			 $("#"+checkBoxId).removeAttr("checked");
		}
	});
}

