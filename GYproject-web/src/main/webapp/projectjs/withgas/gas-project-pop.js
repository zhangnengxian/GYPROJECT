
/**查询条件  默认待勘察*/
var searchProjectData={},
    gasProjectTablePop;
var handleGasProject = function() {
	// searchProjectData = $("#deptpopform").serializeJson();
	"use strict";
    if ($('#gasProjectTablePop').length !== 0) {
        gasProjectTablePop = $('#gasProjectTablePop').on( 'init.dt',function(){
   			$('#gasProjectTablePop').hideMask();
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//查询监听方法
    		searchMonitor();

        }).DataTable({
        	language: language_CN,
            lengthMenu: [10],
            dom: 'Brtip',
            buttons: [
                // { text: '查询', className: 'btn-sm btn-query searchBtn'}
            ],
            //启用服务端模式，后台进行分段查询、排序
            // serverSide:true,
            ajax: {
                url: 'gasPlan/queryGasProject',
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
                {"data":"gprojId",className:"none never"},
                {"data":"projLtypeId",className:"none never"},
                {"data":"projectTypeDes", responsivePriority: 3},
                {"data":"projName", responsivePriority: 1},
                {"data":"projNo", responsivePriority: 2},
                {"data":"custName", responsivePriority: 4},
                {"data":"projAddr", responsivePriority: 5},
                {"data":"gasComLegalRepresent", "className": "text-right", responsivePriority: 6},
                {"data":"cuName", "className": "text-right", responsivePriority: 7},
                {"data":"scNo", "className": "text-right", responsivePriority:8}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
                "targets": 1,
                "visible":false
            },{
				"targets":3,
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

//基础条件查询添加回车事件,当有键盘按下时即可查找
$('#projNo').on('keyup', function(event) {
  $(this).change();
});

//当值改变时查找
$("#projNo").on("change",function(){
	var projNo = $("#projNo").val();
	searchProjectData = {};
	searchProjectData.projNo = projNo;
	$("#gasProjectTablePop").cgetData(true);  //列表重新加载
});
//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").on("click",function(){
		searchProjectData = $("#deptpopform").serializeJson();
	    $("#gasProjectTablePop").cgetData();  //列表重新加载
	});
};

var trSelectedBack = function(v, i, index, t, json){
	
}


var dept = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleGasProject();
        	})
        }
    };
}();

