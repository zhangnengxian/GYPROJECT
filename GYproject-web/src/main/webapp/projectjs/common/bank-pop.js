
var bankData={};
//console.log("customerForm.val..."+JSON.stringify(customerData));
var handlebank = function() {
	"use strict";
    if ($('#bankTable').length !== 0) {
    	$('#bankTable').on( 'init.dt',function(){
   			$('#bankTable').hideMask();
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//查询监听事件
    		searchMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                // { text: '查询', className: 'btn-sm btn-query searchBtn'}
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'popup/queryBank',
                type:'post',
                data: function(d){
                   	$.each(bankData,function(i,k){
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
	  			{"data":"bankAdress"},
				{"data":"bankName"},
				{"data":"bankNo"}
			],
			columnDefs: [{}],
			ordering:false
        });
    }
};

//弹屏监听方法
// var searchMonitor = function(){
// 	$(".searchBtn").on("click",function(){
// 		bankData = $("#bankForm").serializeJson();
// 	    $("#bankTable").cgetData();  //列表重新加载
// 	});
//
// };

var trSelectedBack = function(v, i, index, t, json){
	
}


var bank = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	// bankData=$("#bankForm").serializeJson();
            handlebank();
        }
    };
}();

