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
   			//返回监听
   			backMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [13],
            dom: 'Brtip',
            buttons: [
                { text: '返回', className: 'btn-sm btn-query business-tool-btn backBtn' },
				{ text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  ,
                { text: '详述', className: 'btn-sm btn-query detailBtn' }  
            ],
             serverSide:true,
             ajax:{
            	 url:"/projectView/queryProject",
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
	         select: 'single',
	         columns: [
				{"data":"projId",className:"none never"}, 
				{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"projAddr"},
				{"data":"projScaleDes"},
				{"data":"projStatusDes"},
				{"data":"areaDes"},
				{"data":"custName"},
				{"data":"custContact"},
				{"data":"custPhone"},
				{"data":"acceptDate"},
				{"data":"surveyer"},
				{"data":"surveyDate"},
				{"data":"duName"},
				{"data":"designer"},
				{"data":"duCompleteDate"},
				{"data":"budgetTotalCost"},
				{"data":"budgetDate"},
				{"data":"budgeter"},
				{"data":"confirmTotalCost"},
				{"data":"costMember"},
				{"data":"contractAmount"},
				{"data":"signDate"},
				{"data":"acceptanceDate"},
				{"data":"acceptanceResult"},
				{"data":"acceptanceDirector"},
				{"data":"settlementDate"},
				{"data":"settlementer"}
		 	],
		 	columnDefs: [
		 	    {
		 	    	targets: 0,
		 	    	visible: false
		 	    },{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 15, 	//截取多少字符（或汉字）
						end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},{
					"targets":4,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 15, 	//截取多少字符（或汉字）
						end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},{
			    	targets: 16,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}else if(data !==null && data!==""){
								data = data.toFixed(2);
							}
							return data;
						}else{
							return data;
						}
					}
			    },{
			    	targets: 19,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}else if(data !==null && data!==""){
								data = data.toFixed(2);
							}
							return data;
						}else{
							return data;
						}
					}
			    },{
			    	targets: 21,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}else if(data !==null && data!==""){
								data = data.toFixed(2);
							}
							return data;
						}else{
							return data;
						}
					}
			    }
		 	 ]
        });
    }
},

trSelectedBack = function(v, i, index, t, json){
	/*var projId=trSData.projectGlobalViewListTable.json.projId;
	//alert(id);
	//window.location.href="http://localhost/projTS/viewPorjectDetail?id="+id;
	//1
	var data={projId:id};
	$.ajax({
		type:'post',
		url:'/projTS/viewPorjectDetail',
		data:data,
		success:function(data){
			$(".content").html(data);
		}
	})
	//加载首屏
	$(".content").cgetContent("/projTS/viewPorjectDetail?projId="+projId,'');*/
},

detailMonitor = function(){
	
	$(".detailBtn").on("click",function(){
		if($("#projectGlobalViewListTable").find("tr.selected").length>0 && trSData.projectGlobalViewListTable.t){
			var projId = trSData.projectGlobalViewListTable.json.projId;
			//var url = "#/projTS/viewPorjectDetail?projId="+projId;
			/*var popoptions = {
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
			$("body").cgetPopup(popoptions);*/
			$(".content").cgetContent("/projTS/viewPorjectDetail?projId="+projId,'');
			
			
			//$("#ajax-content").cgetContent("projectView/detailPage",{"projId":projId});
		}else{
			alertInfo('请选择需要查看详述的工程记录！');
		}
	});
},


//查询监听
searchMonitor=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#/projectView/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone,
			size:'large'
		});
	});
	//基础条件查询添加回车事件
	$('#projectGlobalViewListTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
			var projNo = $('#projectGlobalViewListTable_filter input').val();
			searchData = {};
			searchData.projNo = projNo;
			$("#projectGlobalViewListTable").cgetData();
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


backMonitor=function(){
	$(".backBtn").off("click").on("click",function(){
		var path=$("#path").val();
		window.location.href=path+"projInfoNew.jsp";
	})
};
	

/**
 * 初始化工程列表
 */
projectGlobalView = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("/js/ellipsis.js").done(function(){
        		handleProjectGlobalView();
        	});
        }
    };
}();
$('#projectGlobalViewListTable').on("draw.dt",function(){
	$(this).DataTable().responsive.recalc();
});


