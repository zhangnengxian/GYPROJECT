var searchData={};//查询条件
/**
 * 加载工程列表
 */
var handleProjectDetail = function() {
	"use strict";
    if ($('#projectGlobalViewListTable').length !== 0) {
    	$('#projectGlobalViewListTable').on( 'init.dt',function(){
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [15,30,50,100,200],
            dom: 'lfBrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' }  
            ],
            ajax: {
            	url: 'projectjs/project/json/project-items-demo.json',
                dataSrc: 'projects'
            },
//             serverSide:true,
//             ajax:{
//            	 url:"designDispatch/queryProject",
//            	 type:'post',
//            	 data: function(d){
//                    	$.each(searchData,function(i,k){
//                    		d[i] = k;
//                    	});
//                    	
//                 },
//                 dataSrc: 'data'
//             },
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: 'single',
            columns: [
			    {"data":"projId",className:"none never"}, 
	  			{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"projScaleDes"},
				{"data":"projectSchedule.projStatusDes"},
				{"data":"custName"},
				{"data":"custContact"},
				{"data":"custPhone"},
				{"data":"duName"},
				{"data":"designer"},
				{"data":"managementOffice"},
				{"data":"managementQae"},
				{"data":"managementWacf"},
				{"data":"areaManager"},
				{"data":"saftyOfficer"},
				{"data":"technician"},
				{"data":"businessAssistant"},
				{"data":"suName"},
				{"data":"suCse"},
				{"data":"suJgj"}
		 	],
		 	columnDefs: [
		 	    {
		 	    	targets: 0,
		 	    	visible: false
		 	    }
		 	 ]
        });
    }
},

trSelectedBack = function(v, i, index, t, json){
	
},

/**
 * 初始化工程列表
 */
projectDetail = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	//handleProjectDetail();
        }
    };
}();