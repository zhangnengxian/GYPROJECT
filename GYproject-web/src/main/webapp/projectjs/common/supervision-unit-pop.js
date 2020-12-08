
/**查询条件  默认待勘察*/
var supervisionUnitData={};
var handleSupervisionUnit = function() {
	"use strict";
    if ($('#supervisionUnitTable').length !== 0) {
    	$('#supervisionUnitTable').on( 'init.dt',function(){
   			$('#supervisionUnitTable').hideMask();
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
                url: 'popup/querySupervisionUnit',
                type:'post',
                data: function(d){
                   	$.each(supervisionUnitData,function(i,k){
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
	  			{"data":"suName"},
	  			{"data":"shortName"},
				{"data":"suDirector"},
				{"data":"suPhone"}
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
		supervisionUnitData = $("#supervisionUnitForm").serializeJson();
	    $("#supervisionUnitTable").cgetData();  //列表重新加载
	});
	
};

var trSelectedBack = function(v, i, index, t, json){
	
}


var supervisionUnit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleSupervisionUnit();
        }
    };
}();

