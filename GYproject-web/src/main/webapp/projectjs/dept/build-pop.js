
/**查询条件  默认待勘察*/
var deptData={};
var handleBuild = function() {
	deptData.deptId=$("#cuId").val();
	deptData.post=$("#buildPost").val();
	deptData.corpId=$("#corpId").val();
	console.info("corpId--"+corpId);
	console.info(deptData);
	"use strict";
    if ($('#staffTable').length !== 0) {
    	$('#staffTable').on( 'init.dt',function(){
   			$('#staffTable').hideMask();
   			//默认选中第一行
    		//$(this).bindDTSelected(trSelectedBack,true);
    		bindTableSelectRoleListRight();
    		//查询监听方法
    		searchMonitor();
    		
    		//设计院选择变更监听
    		changeInput();
    		
        }).DataTable({
        	language: language_CN,
            lengthMenu: [100],
            dom: 'Brt',
            buttons: [
                  { text: '查询', className: 'btn-sm btn-query searchBtn'}
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'popup/queryStaff',
                type:'post',
                data: function(d){
                   	$.each(deptData,function(i,k){
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
            select: {
                style: 'multi'
            },
            columns: [
                {"data":"staffId",className:"none never"}, //隐藏
	  			{"data":"staffNo"}, 
				{"data":"staffName"},
				{"data":"postName",className:"text-right"}
			],
			columnDefs: [],
			ordering:false
        });
    }

};

//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").on("click",function(){
		deptData = $("#staffForm").serializeJson();
	    $("#staffTable").cgetData();  //列表重新加载
	});
};

/**
 * 设计院修改监听事件
 */
var changeInput = function(){
	$("#allDesign").change(function(){
		deptData.allDesign = $(this).find(":selected").val();
		$("#duName").val($(this).find(":selected").text());
		$("#unitId").val(deptData.allDesign);
		$("#staffTable").cgetData();  //列表重新加载
	});
}


//绑定表格行选中事件
function bindTableSelectRoleListRight() {
	/*$('#staffTable').DataTable().on( 'select', function ( e, dt, type, indexes ) {
		var roleIds = '';
		var postNames='';
		var data = $('#staffTable').DataTable().rows('.selected').data();
		console.info("1----");
		console.info(data);
		
		$.each(data,function(key, val){
			roleIds=","
			if(roleIds.length > 0) {
				roleIds +=",";
				postNames+=",";
			}
			roleIds += val.id;
			postNames+=val.postName;
		});
		$("#testLeaderId").val(","+roleIds+",");
		$("#testLeader1").val(postNames);
   	});*/
}



var staff = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleBuild();
        }
    };
}();

