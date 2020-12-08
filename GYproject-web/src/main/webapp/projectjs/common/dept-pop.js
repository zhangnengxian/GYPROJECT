
/**查询条件  默认待勘察*/
var deptData={};
var handleDept = function() {
	deptData = $("#deptpopform").serializeJson();
	"use strict";
    if ($('#deptTablePop').length !== 0) {
    	$('#deptTablePop').on( 'init.dt',function(){
   			$('#deptTablePop').hideMask();
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		
    		//查询监听方法
    		searchMonitor();
    		
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn'}
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'popup/queryDept',
                type:'post',
                data: function(d){
                   	$.each(deptData,function(i,k){
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
                 {"data":"deptId",className:"none never"},
				 {"data":"deptInnerCode"},
				 {"data":"deptName"},
				 {"data":"deptTypeName"},
				 {"data":"principal"}/*,
				 {"data":"phone"}*/
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
		deptData = $("#deptpopform").serializeJson();
	    $("#deptTablePop").cgetData();  //列表重新加载
	});
};

var trSelectedBack = function(v, i, index, t, json){
	
}


var dept = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleDept();
        }
    };
}();

