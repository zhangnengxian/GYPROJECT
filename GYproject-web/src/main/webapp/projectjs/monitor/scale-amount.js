
var scaleAmountTable;




var handleScaleAmount=function(){
	'use strict';
	 if ($('#scaleAmountTable').length !== 0) {
		 scaleAmountTable=$('#scaleAmountTable').on('init.dt',function(){
			 
		 }).DataTable({
			 language: language_CN,
             lengthMenu: [18],
             dom: 'Brtip',
             buttons: [
             ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'projectTotalStatistics/scaleAmountStatistics',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
             },
             select: true,  //支持多选
             columns: [
				 {'data':'projId',className:'none never'}, 
	  			 {'data':'projNo'}, 
				 {'data':'projName'},
				 {'data':'projStatusDes'}
		     ]
		 })
	 }
}







/**
 * 初始化工程总体信息表
 */
var scaleAmount = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	handleScaleAmount();
        }
    };
}();