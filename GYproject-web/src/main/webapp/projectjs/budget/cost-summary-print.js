var searchData = {};
var handleCostSummaryPrint = function(){
	"use strict";
	 if ($('#costSummaryPrintTable').length !== 0) {
		 $('#costSummaryPrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#costSummaryPrintTable').hideMask();
   			//基础查询条件
   			$("#costSummaryPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   		    showReport();
		 }).DataTable({
			language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
			ajax: {
                url: 'costSummarytPrint/queryProject',
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
  				{"data":"projStatusDes"},
  				{"data":"overdue", className:"none never"}
      		],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 },{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				}],
			 fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
					if(aData.overdue){
						$(nRow).addClass("expired-proect");
					}
				}
		 });
	 }
}
//初始化列表回调
var trSelectedBack = function(v, i, index, t, json){
	showReport();	
}
//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#costSummarytPrint/queryProjectPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#costSummaryPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#costSummaryPrintTable_filter input").val();
		$("#costSummaryPrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#costSummaryPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
//弹屏确定回调
var searchDone = function(){
	searchData = $("#costSummarySearchForm").serializeJson();
	searchData.projNo = $("#costSummaryPrintTable_filter input").val();
	//列表重新加载
    $("#costSummaryPrintTable").cgetData(true, tableCallBack);  
}
//列表重加载回调
var tableCallBack = function(){
	var len = $('#costSummaryPrintTable').DataTable().ajax.json().data ? $('#costSummaryPrintTable').DataTable().ajax.json().data.length : $('#costSummaryPrintTable').DataTable().ajax.json().length;
	if(len<1){
		src = cptPath+"/ReportServer?reportlet=budget/costSummaryPrint.cpt"
		$("#mainFrm").attr("src",src);
	 }else{
	 }
}
//初始化列表
var costSummaryPrint = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handleCostSummaryPrint();
        	});
        }
    };
}();