
var searchData = {};//查询条件
/**
 * 加载工程列表
 */
var handleProjectConstructList = function() {
	"use strict";
    if ($('#projectListTable').length !== 0) {
    	$('#projectListTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack);
   			$('#projectListTable').hideMask();
   			//选择施工单位
   			chooseUnitMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [9],
            dom: 'Brtip',
            buttons: [
            ],
            serverSide:true,
            ajax:{
            	url:"/projectConstructList/queryProject",
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
            select: true,
            columns: [
			    {"data":"projNo", responsivePriority: 2},
				{"data":"projName", responsivePriority: 1},
				{"data":"projAddr", responsivePriority: 3},
				{"data":"projScaleDes", responsivePriority: 6},
				{"data":"projStatusDes", responsivePriority: 4},
				{"data":"managementOffice", responsivePriority: 5},
				{"data":"cuName",responsivePriority: 7},
				{"data":"duName", responsivePriority: 8},
				{"data":"surveyer", responsivePriority: 9}
		 	],
		 	columnDefs: [{
					"targets":1,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 15, 	//截取多少字符（或汉字）
						end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},{
					"targets":3,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				}]
        });
    }
},

trSelectedBack = function(v, i, index, t, json){
	
};

var chooseUnitMonitor=function(){
	//单选radio change事件
    $("#chooseRadio input:radio[name='constructionUnit']").on('change',function(){
    	//alert($("input[name='constructionUnit']:checked").val());
    	searchData.managementOfficeId=$("input[name='constructionUnit']:checked").val();
    	console.info('val..'+$("input[name='constructionUnit']:checked").val());
    	console.info('searchId..'+searchData.managementOfficeId);
    	$("#projectListTable").cgetData();
    })
}


/**
 * 初始化工程列表
 */
projectList = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleProjectConstructList();
        }
    };
}();