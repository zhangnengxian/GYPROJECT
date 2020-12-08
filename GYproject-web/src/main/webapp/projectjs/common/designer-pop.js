var designertable;
/**
 * 加载设计员表格
 */
var designertableinit= function() {
	//designerData.deptId = '-1';
	"use strict";
	designertable= $('#designerTable').on( 'init.dt',function(){
	$('#designerTable').hideMask();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        //dom: 'Brt',
        dom: 'Brtip',
        buttons: [
        ],
        ajax: {
            url: 'designDrawing/queryDesigner',
            type:'post',
            data: function(d){
               	$.each(designerData,function(i,k){
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
             {"data":"staffId",className:"text-right"},
             {"data":"surveyer",className:"surveyer"},
             {"data":"surveyRegister",className:"text-right"},
	    ],
	    columnDefs: [{
	    	"targets":0,
	    	"visible":false
	    }]
   });  
};

var trSelectedBack = function(v, i, index, t, json){
	
}


var designer = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	designertableinit();
        }
    };
}();

