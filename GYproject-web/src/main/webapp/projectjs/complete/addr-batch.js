var addrBatchTable;   //资料标准列表

var searchData={};
var type=$("#deptName option:selected").val();
/**
 * 初始化评分标准列表
 */
var handleAddrSet = function() {
	"use strict";
    if ($('#addrtable').length !== 0) {
    	addrBatchTable= $('#addrtable').on( 'init.dt',function(){
   			//默认选中第一行
    		//$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#addrtable').hideMask();
   			//下拉查询监听
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                //{ text: '保存', className: 'btn-sm btn-query business-tool-btn saveBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:true,
            /*ajax: {
                url: 'scoreStandard/queryGrade',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },*/
            ajax: 'projectjs/complete/json/addrSet.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data": "addrCodeId", className: "none never"},
				{"data": "addrCodeDes"},
				{"data": "addrCodeLevel"}
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
		    }]
        });
    }
};



/**
 * 初始化工程列表
 */
var addrBatch = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleAddrSet();
        	});
        }
    };
}();