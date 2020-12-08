
/**查询条件  默认待勘察*/
var deptData={};
var handlePost = function() {
	deptData = $("#deptpopform").serializeJson();
	var allDesign = $("#allDesign").val();
	$("#duName").val($("#allDesign").find(":selected").text());
	$("#unitId").val(allDesign);
	"use strict";
    if ($('#postTablePop').length !== 0) {
    	$('#postTablePop').on( 'init.dt',function(){
   			$('#postTablePop').hideMask();
   			//默认选中第一行
    		//$(this).bindDTSelected(trSelectedBack,true);
    		bindTableSelectRoleListRight();
    		$("#postTablePop_filter input").attr("placeholder","职务名称");	
    		$("#postTablePop_filter input").addClass("postName");
    		//查询监听方法
    		searchMonitor();		
    		//设计院选择变更监听
    		changeInput();
    		//基础条件查询添加监听
    		$('.postName').on('change',function(){
    			var postName = $('.postName').val();
    			deptData = {};
    			deptData.deptName = postName;
    			$('#postTablePop').cgetData(true,staffCallBack);  //列表重新加载
    		});
    		//基础条件查询添加回车事件
    		$('.postName').on('keyup', function(event) {
    		    	$(this).change();	
    		});
        }).DataTable({
        	language: language_CN,
            lengthMenu: [60],
            dom : 'Bfrtip',
            bStateSave:true,
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:false,
            ajax: {
                url: 'staff/queryPost',
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
                 {"data":"id"},
				 {"data":"postName"}
			],
			columnDefs: [],
			ordering:false
        });
    }

};

function staffCallBack(){
//空函数
}	

//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").on("click",function(){
		deptData = $("#deptpopform").serializeJson();
	    $("#postTablePop").cgetData();  //列表重新加载
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
		$("#postTablePop").cgetData();  //列表重新加载
	});
}


//绑定表格行选中事件
function bindTableSelectRoleListRight() {
	$('#postTablePop').DataTable().on( 'select', function ( e, dt, type, indexes ) {
		var data = $('#postTablePop').DataTable().rows('.selected').data();
		console.info("----");
		console.info(data);
		var roleIds = '';
		var postNames='';
		$.each(data,function(key, val){
			if(roleIds.length > 0) {
				roleIds +=",";
				postNames+=",";
			}
			roleIds += val.id;
			postNames+=val.postName;
		});
		$("#post").val(roleIds);
		$("#postName").val(postNames);
   	});
}



var post = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handlePost();
        }
    };
}();

