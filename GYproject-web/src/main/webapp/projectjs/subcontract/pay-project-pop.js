var feeType=$("#feeType option:selected").val();
/**查询条件  默认待勘察*/
var searchProjectData={};
searchProjectData.menuId = "110614";
var costApplyTablePop;
var handlePayProject = function() {
	// searchProjectData = $("#deptpopform").serializeJson();
	"use strict";
	searchProjectData.feeType=feeType;
    if ($('#costApplyTablePop').length !== 0) {
        costApplyTablePop = $('#costApplyTablePop').on( 'init.dt',function(){
   			$('#costApplyTablePop').hideMask();
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//查询监听方法
    		searchMonitor();
    		//添加导出监听方法
    		exportMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn'},
                { text: '导出', className: 'btn-sm btn-query exportBtn'},
            ],
            //启用服务端模式，后台进行分段查询、排序
            // serverSide:true,
            ajax: {
                url: 'costApply/queryPayProject',
                type:'post',
                data: function(d){
                   	$.each(searchProjectData,function(i,k){
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
                {"data":"projId"},
                {"data":"projNo", responsivePriority: 1},
                {"data":"projName", responsivePriority: 2},
                {"data":"projScaleDes", responsivePriority: 3},
                {"data":"projAddr", responsivePriority: 4},
                {"data":"endSettlementCost", responsivePriority: 5},
                {"data":"endAmount", responsivePriority: 6,className:"hidden"},
                {"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":3,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":4,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}],
			ordering:false
        });
    }

};

//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").on("click",function(){
		searchProjectData = $("#projectForm").serializeJson();
		searchProjectData.feeType=$("#feeType option:selected").val();
	    $("#costApplyTablePop").cgetData();  //列表重新加载
	});
};


//添加导出按钮监听方法
var exportMonitor = function(){
	$(".exportBtn").on("click",function(){
		 var feeType=$("#feeType option:selected").val();  //费用类型
		 $("#exportExcelProjInfo").attr("action", "costApply/exportExcelProjInfo/"+feeType); //设置表单action并且传入参数
		 $("#exportExcelProjInfo").submit(); 
	});
};
var trSelectedBack = function(v, i, index, t, json){
	
}


var payProject = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handlePayProject();
        	})
        }
    };
}();

