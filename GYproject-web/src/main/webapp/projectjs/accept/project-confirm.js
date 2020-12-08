/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var projectConfirmTable;
var scaleTable;
var dataPopTable;
var accessoryTable;
/**查询条件  默认待勘察*/
var searchData={};
var accessoryData={};
var detailSearchData = {};
var handleProjectConfirm = function() {
	"use strict";
    if ($('#projectConfirmTable').length !== 0) {
    	projectConfirmTable= $('#projectConfirmTable').on( 'init.dt',function(){
    		//加载页面
    		$("#project_confirm_panel_box").cgetContent("projectConfirm/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		var selectHtml = '<label style="margin-left: 5px;"><select class="form-control input-sm hidden" name="stateSelect" id="stateSelect">';
    		var options = $("#projStatus").html();
    		selectHtml += options;
			selectHtml += '</select></label>';
			$("#projectConfirmTable_filter").append(selectHtml);	
			$("#projectConfirmTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#projectConfirmTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//立项确认监听方法
   			confirmMonitor();
   			//打印方法
   			printMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '打印', className: 'btn-sm btn-query business-tool-btn printBtn' },
                { text: '立项确认', className: 'btn-sm btn-query confirmBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'projectConfirm/queryProject',
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
	$("#stateSelect,#projectConfirmTable_filter input").on("change",function(){
		var stateSelect = $("#stateSelect").find("option:selected");
		var projNo = $("#projectConfirmTable_filter input").val();
		searchData = {};
		searchData.projStatusId = stateSelect.val();
		searchData.projNo = projNo;
		$("#projectConfirmTable").cgetData(true);  //列表重新加载
		$(".iframe-big-box").addClass("hidden");
		$("#project_confirm_panel_box").removeClass("hidden");
		
	});
	//基础条件查询添加回车事件
	$('#projectConfirmTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	
	searchData = $("#confirmSearchForm").serializeJson();
	var projNo = $("#projectConfirmTable_filter input").val();
	searchData.projNo = projNo;
	var stateSelect = $("#stateSelect").val();
	searchData.projStatusId = stateSelect;
    $("#projectConfirmTable").cgetData(true,confirmTableCallBack);  //列表重新加载
    $(".iframe-big-box").addClass("hidden");
	$("#project_confirm_panel_box").removeClass("hidden");
    
}

var confirmTableCallBack = function(){
	var len = $('#projectConfirmTable').DataTable().ajax.json().data ? $('#projectConfirmTable').DataTable().ajax.json().data.length : $('#projectConfirmTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $("#confirmApplyForm")[0].reset();
		 $(':input','#scaleTableForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
		 $(".editbtn").addClass("hidden");
		 $("#projImage").attr("src", "");
		 $("#projPhoto").addClass("disabled");
		 $("#projPicture").addClass("disabled");
		 $("#confirmApplyForm,#scaleTableForm").toggleEditState(false);
	}else{
		 $(".editbtn").removeClass("hidden");
		 $("#projPhoto").removeClass("disabled");
	}
}
//打印监听
var printMonitor = function(){
	$(".printBtn").off("click").on("click",function(){
		if($("#projectConfirmTable").find("tr.selected").length>0){
		//trSelectedBack();
		$(".iframe-big-box").removeClass("hidden");
		$("#project_confirm_panel_box").addClass("hidden");
		
		}else{
			alertInfo('请选择要打印的记录！');
		}
	});
}

//确认监听方法
var confirmMonitor = function(){
	$(".confirmBtn").on("click",function(){
		if($("#projectConfirmTable").find("tr.selected").length>0){
			$(".iframe-big-box").addClass("hidden");
			$("#project_confirm_panel_box").removeClass("hidden");
			$("#projPicture").removeClass("disabled");
			//切换可编辑状态
			$("#confirmApplyForm,#scaleTableForm").toggleEditState(true);
			//$("#backReason").attr("data-parsley-required","false");
			$(".backReason").addClass("hidden");
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要立项确认的记录！');
		}
	});
};
var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	if($('[name="isBack"]:checked').val()==1){
		$('[name="isBack"]').not(":checked").attr("checked",true);
		$('[name="isBack"]').change();
	}
	var projId = json.projId;
	 src = cptPath+"/ReportServer?reportlet=accept/acceptSurvey.cpt&projId=" + projId;
	 $("#mainFrm").attr("src",src)
	
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('confirmApplyForm','projectConfirm/viewProjectConfirm','',scaleDetailRefresh);
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
	var len = $('#projectConfirmTable').DataTable().ajax.json().data ? $('#projectConfirmTable').DataTable().ajax.json().data.length : $('#projectConfirmTable').DataTable().ajax.json().length;
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
	$("#confirmApplyForm,#scaleTableForm").toggleEditState(false);
}

var scaleTableCallBack = function(){
	if(detailFlag){
   	 	$('#scaleTableForm').toggleEditState(true);
	}else{
		$('#scaleTableForm').toggleEditState(false);
	}
}

var projectConfirm = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handleProjectConfirm();
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
               url: 'projectConfirm/queryScaleDetail',
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
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_scaleAmount' id='" + row.projStypeId + "_scaleAmount'  data-parsley-maxlength='17' data-parsley-type='number' value="+data+"></div>"
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


//$(".attach-panel").initAttachOper({
//	collection: {
//		tableid:'projectConfirmTable'
//	}
//});
