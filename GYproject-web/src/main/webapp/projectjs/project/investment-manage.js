var searchData={};//查询条件
/**
 * 加载工程列表
 */
var handleProjectGlobalView = function() {
	"use strict";
    if ($('#projectGlobalViewListTable').length !== 0) {
    	$('#projectGlobalViewListTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,false);
   			$('#projectGlobalViewListTable').hideMask();
   			$("#projectGlobalViewListTable_filter input").attr("placeholder","工程编号");
   			//console.info($('#projectGlobalViewListTable').DataTable().ajax.json());
   			//详述监听方法
   			detailMonitor();
   			//查询监听
   			searchMonitor();
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [15],
            dom: 'Brtip',
            buttons: [
				/*{ text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  ,
                { text: '详述', className: 'btn-sm btn-query detailBtn' }  */
            ],
             /*serverSide:true,
             ajax:{
            	 url:"projectView/queryProject",
            	 type:'post',
            	 data: function(d){
                    	$.each(searchData,function(i,k){
                    		d[i] = k;
                    	});
                    	
                 },
                 dataSrc: 'data'
             },*/
             ajax: 'projectjs/project/json/investment_manage.json',
             responsive: {
	           	 details: {
	        		 renderer: function ( api, rowIdx, columns ){
	        			 return renderChild(api, rowIdx, columns);
	        		 }
	        	 }
	         },
	         select: 'single',
	         columns: [
				{"data":"acceptNo"}, 
				{"data":"projName"}, 
				{"data":"pipeDiameter"},
				{"data":"pipeLength",className:"text-right"},
				{"data":"finishLength",className:"text-right"},
				{"data":"completionSchedule"},
				{"data":"projectType"},
				{"data":"investmentScale",className:"text-right"},
				{"data":"alradyInvestment",className:"text-right"},
				{"data":"planInvestment",className:"text-right"},
				{"data":"constructionRatio"},
				{"data":"affiliatedCompany"}
		 	],
		 	columnDefs: [
		 	 ]
        });
    }
},

trSelectedBack = function(v, i, index, t, json){
	
},

detailMonitor = function(){
	
	$(".detailBtn").on("click",function(){
		if($("#projectGlobalViewListTable").find("tr.selected").length>0 && trSData.projectGlobalViewListTable.t){
			var projId = trSData.projectGlobalViewListTable.json.projId;
			var url = "#projectView/detailPage?projId="+projId;
			var popoptions = {
				title: trSData.projectGlobalViewListTable.json.projName + ' - 详述',
				content: url,
				ahide: true,
				ccolor: '#59727D',
				ctext: '确定',
				size:'detail',
				nocache: true,
				icon: 'fa-file-text'
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
			//$("#ajax-content").cgetContent("projectView/detailPage",{"projId":projId});
		}else{
			alertInfo('请选择需要查看详述的工程记录！');
		}
	});
},


//查询监听
searchMonitor=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#projectView/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone,
			size:'large'
		});
	});
	$('#projectGlobalViewListTable_filter input').on('change',function(){
		var projNo = $('#projectGlobalViewListTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$("#projectGlobalViewListTable").cgetData();
	});
	//基础条件查询添加回车事件
	$('#projectGlobalViewListTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
},


/**
 * 查询弹出屏，点击确定后 
 */
searchDone = function(){
	searchData = $('#searchForm_globalView').serializeJson();
	var projNo = $('#projectGlobalViewListTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#projectGlobalViewListTable').cgetData(false);
},

reloadBack=function(){
	$('#projectGlobalViewListTable').DataTable().responsive.recalc();
},

	

/**
 * 初始化工程列表
 */
investmentManage = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleProjectGlobalView();
        	})
        }
    };
}();
$('#projectGlobalViewListTable').on("draw.dt",function(){
	$(this).DataTable().responsive.recalc();
});

$(".attach-panel").initAttachOper({
	collection: {
		tableid:'projectGlobalViewListTable',
		init: function(){
			//console.info(1);
		}
	}
});
