var searchData = {},detailSearchData = {};
searchData.isExport=$("#haveNotExport").val();
var materialPlanningTable,
materialDetailTable;
var handleMaterialPlanning = function(){
	"use strict";
	 if ($('#materialPlanningTable').length !== 0) {
		 materialPlanningTable=$('#materialPlanningTable').on( 'init.dt',function(){
			 handleMaterialPlanDetail();
			//加载页面
	   		$("#budget_sum_box").cgetContent("materialPlanning/viewPage");
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#materialPlanningTable').hideMask();
   			$("#materialPlanningTable_filter input").attr("placeholder","工程编号");
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
                url: 'materialPlanning/queryProject',
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
  				{"data":"projId",className:"none never"}, 
	  			{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"projStatusDes"}
      		],
			 columnDefs: [{
					"targets": 0,
					"visible":false
				},{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 8, 	//截取多少字符（或汉字）
						end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				}]

		 })
	 }
}
var handleMaterialPlanDetail = function(){
	"use strict";
	if(!$("#projId").val()==""){
		detailSearchData.projId=$("#projId").val();
	}else{
		detailSearchData.projId=-1;
	}
	var pagedLength = 0;
	if ($('#materialDetailTable').length !== 0) {
		materialDetailTable=$('#materialDetailTable').on( 'init.dt',function(){
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
                      { text: '导出', className: 'btn-primary btn-sm business-tool-btn  exportbtn' },
            ],
          //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
			ajax: {
                url: 'materialReceive/queryMaterialList',
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
  			{"data":"projId", "className":"none never"},
            {"data":"materialNo", "className":"none never"},
  			{"data":"materialName", responsivePriority: 2},
  			{"data":"materialSpec", responsivePriority: 9},
			{"data":"materialUnit", responsivePriority: 10},
			{"data":"materialPrice","className":"none never"},
			{"data":"materialNum", "className": "text-right", responsivePriority: 5},//设计总量
			{"data":"getAnum", "className": "text-right", responsivePriority: 4},//领用总量
			{"data":"oweNum", "className": "text-right", responsivePriority: 3},
      		],
			 columnDefs: [{
					targets:0,
					"visible":false
				},{
					targets:1,
					"visible":false
				},{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},
				{
					targets:8,
					render:function(data,type,row,meta){
						if(data === null){
							data = "";
						}
						if(type === "display"){
							data=row.materialNum-row.getAnum;
							return data;
						}else{
							return data;
						}
					}
				}]
			 	
		 })
	 }
}

var trSelectedBack = function(v, i, index, t, json){
	$("#projId").val(json.projId);
	$("#projId1").val(json.projId);
	trSData.t.cgetDetail('projectForm','materialPlanning/viewProject','',noteCallBack);
}

function noteCallBack(){
	if($.fn.DataTable.isDataTable('#materialDetailTable')){
		detailSearchData.projId=$("#projId").val();
		$("#materialDetailTable").cgetData(false);//列表重新加载
	}
}
/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#materialPlanning/constructSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#materialPlanningTable_filter input").on("change",function(){
		searchData.projNo = $("#materialPlanningTable_filter input").val();
		//传入false  则不选中行
		$("#materialPlanningTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#materialPlanningTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
};
var searchDone = function(){
	searchData = $("#searchForm_list").serializeJson();
	searchData.projNo = $("#materialPlanningTable_filter input").val();
	//列表重新加载
    $("#materialPlanningTable").cgetData(true, tableCallBack);  
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
	var len = $('#materialPlanningTable').DataTable().ajax.json().data ? $('#materialPlanningTable').DataTable().ajax.json().data.length : $('#materialPlanningTable').DataTable().ajax.json().length;
	//console.log("len..."+len);
	if(len<1){
		$(".exportbtn").addClass("hidden");
		detailSearchData.projId = "-1";
		 $("#materialDetailTable").cgetData(false);
		$("#projectForm input,#projectForm textarea").val("");
	 }else{
		 $(".exportbtn").removeClass("hidden");
		 detailSearchData.projId = $("#projId").val();
		 $("#materialDetailTable").cgetData(false);
	 }
};
//标记监听
var signMonitor=function(){
	$('.signBtn').off().on('click',function(){
		var len=$("#materialPlanningTable").find('tr.selected').length;
		if(len>0){
           $("body").cgetPopup({
               	title: "提示信息",
               	content: "是否将选中的工程标记为已发货完成？",
               	accept: sureDone,
               	chide: true,
               	newpop: 'new',
               	icon: "fa-check-circle"
           });
		}else{
			alertInfo('请选择要标记的工程！');
		}
	});
}
var sureDone=function(){
	var projId=trSData.materialPlanningTable.json.projId;
	$.ajax({
		type:'post',
		url:'materialPlanning/signProject',
		contentType: "application/json;charset=UTF-8",
        data: projId,
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
	$("#materialPlanningTable").cgetData(true);
}
/**
 * 初始化列表
 */
var materialPlanning = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){    
        		handleMaterialPlanning();
        	});
        }
    };
}();

