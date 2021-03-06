
/**查询条件  默认待勘察*/
var staffData={};
var handleStaff = function() {
	staffData = $("#staffForm").serializeJson();
	"use strict";
    if ($('#staffTable').length !== 0) {
    	$('#staffTable').on( 'init.dt',function(){
   			$('#staffTable').hideMask();
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		
    		//查询监听方法
    		searchMonitor();
    		
        }).DataTable({
        	language: language_CN,
            lengthMenu: [10],
            dom: 'Brtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn'}
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'popup/queryStaff',
                type:'post',
                data: function(d){
                   	$.each(staffData,function(i,k){
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
                {"data":"staffId",className:"none never"}, //隐藏
	  			{"data":"staffNo"}, 
				{"data":"staffName"},
				{"data":"postName",className:"text-right"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			}],
			ordering:false
        });
    }
};

//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").on("click",function(){
		staffData = $("#staffForm").serializeJson();
	    $("#staffTable").cgetData();  //列表重新加载
	});
};

var trSelectedBack = function(v, i, index, t, json){
	
}

var staff = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleStaff();
        }
    };
}();

