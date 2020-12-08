var meterTypeTable;   //资料标准列表
var searchData={};
/**
 * 初始化表具类型
 */
var handleMeterType = function() {
	"use strict";
    if ($('#mrTypePop').length !== 0) {
    	meterTypeTable= $('#mrTypePop').on( 'init.dt',function(){
   			//默认选中第一行
    		//$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#mrTypePop').hideMask();
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
            ajax: 'projectjs/complete/json/meterType.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data": "addrCodeId"},
				{"data": "addrCodeDes"},
				{"data": "addrCodeLevel"}
			],
			columnDefs: []
        });
    }
};



/**
 * 初始化工程列表
 */
var meterType = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleMeterType();
        	});
        }
    };
}();