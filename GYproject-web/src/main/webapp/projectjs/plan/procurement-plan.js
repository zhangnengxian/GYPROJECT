var searchData = {},detailSearchData = {};
searchData.isExport=$("#haveNotExport").val();
var handleProcurementPlan = function(){
	"use strict";
	 if ($('#procurementPlanTable').length !== 0) {
		 $('#procurementPlanTable').on( 'init.dt',function(){
			 handleProcurementPlanDetail();
			//加载页面
	   		$("#budget_sum_box").cgetContent("procurementPlan/viewPage");
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#procurementPlanTable').hideMask();
   			
   			$("#procurementPlanTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   			//标记监听
   			signMonitor();
		 }).DataTable({
			language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '标记', className: 'btn-sm btn-query signBtn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
			ajax: {
                url: 'procurementPlan/queryProcurementPlan',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
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
            select: true,  //支持多选
            columns: [
  				{"data":"procurPlanId",className:"none never"},
  				{"data":"projNo"},
  				{"data":"projName"},
  				{"data":"statusDes"},
  				{"data":"projTypeDes"},
//  			{"data":"projStartDate"},
  				{"data":"createTime"},
  				{"data":"projId",className:"none never"},
  				{"data":"status",className:"none never"}
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
					"targets": 5,
					"render":function(data,type,row,meta){
						if(data !=="" && data!==null){
							return format(data);
						}else{
							return data;
						}
					}
				},{
					"targets": 6,
					"visible":false
				},{
					"targets": 7,
					"visible":false
				}]

		 })
	 }
}
var handleProcurementPlanDetail = function(){
	"use strict";
	if(!$("#procurPlanId").val()==""){
		detailSearchData.procurPlanId=$("#procurPlanId").val();
	}else{
		detailSearchData.procurPlanId=-1;
	}
	
	var pagedLength = 0;
	
	if ($('#materialDetailTable').length !== 0) {
		$('#materialDetailTable').on( 'init.dt',function(){
   			var t = $('#materialDetailTable');
			//隐藏遮罩
   			t.hideMask();
   			//搜索监听
   			exportMonitor();
   			
   			t.on("page.dt", function(){
   				var dt = t.DataTable();
   				pagedLength = dt.page() * dt.page.len();
   			});
   			t.on("draw.dt", function(){
   				pagedLength = 0;
   			});
   			
		}).DataTable({
			language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                      { text: '导出', className: 'btn-primary btn-sm business-tool-btn hidden exportbtn' },
            ],
          //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
			ajax: {
                url: 'procurementPlan/queryProcurementPlanDetail',
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
            select: true,  //支持多选
            columns: [
  				{"data":"procurPlanDetailId"},
  				{"data":"materialName"},
  				{"data":"materialSpec"},
  				{"data":"materialUnit"},
//  			{"data":"materialPrice",className:'text-right'},
  				{"data":"materialNum",className:'text-right'}
      		],
			 columnDefs: [{
				 'targets':0,
				 "render": function ( data, type, row, meta ) {
							return pagedLength + meta.row + 1;
					},
				 
			 },{
					"targets":1,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				}
			 	/*,{
					 'targets':5,
					 "render": function ( data, type, row, meta ) {
							if(data!=="" && data!==null){
								return parseFloat(data).toFixed(2);
							}else{
								return data;
							}
						},
				 	}*/]
		 })
	 }
}

var trSelectedBack = function(v, i, index, t, json){
	$("#procurPlanId").val(json.procurPlanId);
	$("#procurPlanId1").val(json.procurPlanId);
	if($.fn.DataTable.isDataTable('#materialDetailTable')){
		detailSearchData.procurPlanId=json.procurPlanId;
		//初始化过
		$("#materialDetailTable").cgetData(false,function(){
				$(".exportbtn").removeClass("hidden");
		});
	}
	trSData.procurementPlanTable.t.cgetDetail('projectForm','procurementPlan/queryProject','6',noteCallBack);
}

function noteCallBack(data){
	//生成时为付款或计划时
	if($("#status").val()=="1"||$("#status").val()=="2"){
		$(".saveBtn").removeClass("hidden");
		$(".saveBtn2").addClass("hidden");
		$(".plan_note").removeClass("hidden");
		$(".procure_feedback").addClass("hidden");
		//生成时操作为设计出图时
	}else if($("#status").val()=="6"){
		$(".saveBtn2").removeClass("hidden");
		$(".saveBtn").addClass("hidden");
		$(".plan_note").addClass("hidden");
		$(".procure_feedback").removeClass("hidden");
	}else{
		$(".saveBtn").addClass("hidden");
		$(".plan_note").addClass("hidden");
		$(".procure_feedback").addClass("hidden");
		$(".saveBtn2").addClass("hidden");
	}
	$("#projectForm").styleFit();
	var projId = $("#projId").val();
	$.ajax({
		type: 'POST',
		url: 'procurementPlan/queryNote',
		data : {
			projId : projId
		},
		success : function(data){
			if(data != null){
				$("#note").val(data);
			}
		}
	})
	
	if($("#procureFeedback").val()!==""){
		$(".saveBtn2").addClass("hidden");
	}
	if($("#note").val()!==""){
		$(".saveBtn").addClass("hidden");
	}
	
}
/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#procurementPlan/procurementPlanSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#procurementPlanTable_filter input").on("change",function(){
		searchData.projNo = $("#procurementPlanTable_filter input").val();
		//传入false  则不选中行
		$("#procurementPlanTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#procurementPlanTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
};
var searchDone = function(){
	searchData = $("#searchForm_sub").serializeJson();
	searchData.projNo = $("#procurementPlanTable_filter input").val();
	//列表重新加载
    $("#procurementPlanTable").cgetData(false, tableCallBack);  
}
var exportMonitor = function(){
	$(".exportbtn").off("click").on("click",function(){
		/*console.info("+++++====++++++++");*/
		$("#procurementPlanForm").submit();
	});
	
}
//标签一
$("#procurPlanIdTab").on("shown.bs.tab",function(){
	if(!trSData.materialDetailTable.t){
		  $(".editbtn").addClass("hidden");
	  }
});
/*//标签二
$("#projectTeb").on("shown.bs.tab",function(){
	if(!trSData.materialDetailTable.t){
		$(".editbtn").addClass("hidden");
	}else{
		  trSData.t.cgetDetail('projectForm','procurementPlan/queryProject','');  
	}
});*/
var tableCallBack = function(){
	var len = $('#procurementPlanTable').DataTable().ajax.json().data ? $('#procurementPlanTable').DataTable().ajax.json().data.length : $('#procurementPlanTable').DataTable().ajax.json().length;
	//console.log("len..."+len);
	if(len<1){
		$(".exportbtn").addClass("hidden");
		detailSearchData.procurPlanId = "-1";
		 $("#materialDetailTable").cgetData(false);
	 }else{
		 $(".exportbtn").removeClass("hidden");
		 detailSearchData.procurPlanId = $("#procurPlanId").val();
		 $("#materialDetailTable").cgetData(false);
	 }
};
//标记监听
var signMonitor=function(){
	$('.signBtn').off().on('click',function(){
		var len=$("#procurementPlanTable").find('tr.selected').length;
		if(len>0){
           $("body").cgetPopup({
               	title: "提示信息",
               	content: "是否将选中的采购计划标记为已导出？",
               	accept: sureDone,
               	chide: true,
               	newpop: 'new',
               	icon: "fa-check-circle"
           });
		}else{
			alertInfo('请选择要标记的采购计划！');
		}
	});
}
var sureDone=function(){
	var procurPlanId=trSData.procurementPlanTable.json.procurPlanId;
	$.ajax({
		type:'post',
		url:'procurementPlan/signprocurementPlanExport',
		contentType: "application/json;charset=UTF-8",
        data: procurPlanId,
        success:function(data){
        	var content = "标记成功！";
        	if(data=="false"){
        		content = "标记失败！";
        	}
        	var myoptions = {
                	title: "提示信息",
                	content: content,
                	accept: tableReload,
                	chide: true,
                	icon: "fa-check-circle",
                	newpop: 'new'
                	
            }
            $("body").cgetPopup(myoptions);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("数据保存失败！");
        }
        
	})
}
var tableReload=function(){
	searchData.isPrint=$("#haveNotExport").val();
	$("#procurementPlanTable").cgetData();
}
/**
 * 初始化列表
 */
var procurementPlan = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){    
        		handleProcurementPlan();
        	});
        }
    };
}();

//关联操作

//$(".attach-panel").initAttachOper({
//	collection: {
//		tableid:'procurementPlanTable',
//		init: function(){
//			console.info(1);
//		}
//	}
//});
