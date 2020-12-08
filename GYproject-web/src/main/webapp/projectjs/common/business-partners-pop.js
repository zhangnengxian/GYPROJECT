
var businessPartnersData=$("#businessPartnersForm").serializeJson();;
var handleBusinessPartners = function() {
	"use strict";
    if ($('#businessPartnersTable').length !== 0) {
    	$('#businessPartnersTable').on( 'init.dt',function(){
   			$('#businessPartnersTable').hideMask();
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//查询监听事件
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
                url: 'popup/queryBusinessPartners',
                type:'post',
                data: function(d){
                   	$.each(businessPartnersData,function(i,k){
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
	  			{"data":"unitName"}, 
				{"data":"shortName"},
				{"data":"unitQualification"},
				{"data":"unitDirector"}
			],
			columnDefs: [{
			}],
			ordering:false
        });
    }
};

//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").on("click",function(){
		businessPartnersData = $("#businessPartnersForm").serializeJson();
	    $("#businessPartnersTable").cgetData();  //列表重新加载
	});
	
};

var trSelectedBack = function(v, i, index, t, json){
	
}


var businessPartners = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleBusinessPartners();
        }
    };
}();

