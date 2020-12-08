
var searchData = {};
var handleDesignAlterationAudit = function() {
	"use strict";
	var projId = getProjectInfo().projId;
	$("#projId").val(projId);
	searchData.projId = projId;
    if ($("#designAlterationAuditTable").length !== 0) {
    	$("#designAlterationAuditTable").on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#designAlterationAuditTable').hideMask();
   			//基础查询
   			$("#designAlterationAuditTable_filter input").attr("placeholder","变更编号");
   			//搜索监听
   			searchMonitor();
   		//跳转链接
   			if (crossPageBus) {
   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   					//跳转后销毁对象
   				crossPageBus = null
   			}
        }).on( 'draw.dt', function () {
        	auditBtnMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
               /* { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  */
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'designAlterationAudit/queryDesignAlteration',
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
                {"data":"cmId",className:"none never"},
        		{"data":"cmNo", responsivePriority: 1},
  	  			{"data":"cmDate", responsivePriority: 4}, 
  	  			{"data":"projName", responsivePriority: 2},
				{"data":"mrAuditLevel",responsivePriority:2}
			],
			columnDefs: [{
				"targets": 4,
				 "render": function (data, type, row, meta){
					 
						if(type==="display"){
							var html = getAuditLevelHtml(data,'1',row.cmId,'120207');
							return html;
						}else{
							return data;
						}
				 }
			},{
				"targets":4,
				 "orderable":false
			}],
			
        });
    }
};

var trSelectedBack = function(){
	
}

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	/*$(".searchBtn").off("click").on("click",function(){
		var url = "#designAlterationAudit/searchPopPage?projId="+$("#projId").val();
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});*/
	
	//基础条件查询添加监听
	$('#designAlterationAuditTable_filter input').on('change',function(){
		var cmNo = $('#designAlterationAuditTable_filter input').val();
		searchData = {};
		searchData.cmNo = cmNo;
		searchData.projId = getProjectInfo().projId;
		$('#designAlterationAuditTable').cgetData(true);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#designAlterationAuditTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
}
var searchDone = function(){
	searchData = $("#searchForm_designAlterationAudit").serializeJson();
	searchData.projId = getProjectInfo().projId;
    $("#designAlterationAuditTable").cgetData(true);  //列表重新加载
}

/**
 * 加载审核屏
 */
var auditBtnMonitor = function(){
    $(document).off("click", ".level").on("click", ".level", function(){
		var isAudit = "0";//未审核过
		if($(this).is(".disabled")) return false;
		if($(this).is(".passed, .refused")){
			isAudit = "1";//已审核过
		}
		var levelDes = $(this).text();
		var auditLevel = "1";
		if(levelDes === "一级"){
			auditLevel = "1";
		}else if(levelDes === "二级"){
			auditLevel = "2";
		}
		var cmId = $(this).attr("data-rid");
		var data = {"isAudit":isAudit,"auditLevel":auditLevel,"cmId":cmId};
		$("#ajax-content").cgetContent("designAlterationAudit/auditPage",data);
	});
}

/**
 * 初始化列表
 */
var designAlterationAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleDesignAlterationAudit();
        }
    };
}();