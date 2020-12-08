var searchData={}; //查询条件
/**
 * 加载工程列表
 */
var handleAuditLevel = function() {
	'use strict';
    if ($('#auditLevelTable').length !== 0) {
    	$('#auditLevelTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#auditLevel_panel_box').cgetContent('auditLevel/viewAuditLevelPage');
   			//隐藏遮罩
   			$('#auditLevelTable').hideMask();
   			//$('#auditLevelTable_filter input').attr('placeholder','单位名称');
   			
   			//修改监听
   			updateMonitor();
   			//增加监听
   			addMonitor();
   			//删除监听
   			deleteMonitor();
   			
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Brtip',
             buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '增加', className: 'btn-sm btn-query business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                {text : '删除',className : 'btn-sm btn-warn deleteBtn business-tool-btn'}
             ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'auditLevel/queryDocType',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
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
					{"data" : "id",className : "none never"}, 
					{"data" : "corpName"}, 
					{"data" : "projTypeDes"}, 
					{"data" : "contributionCodeDes"}, 
					{"data" : "des"}, 
					{"data" : "grade"}
			   ],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 }],
        });
    }
};



/**
 * 查询按钮监听方法
 */
var searchMonitor=function(){
	$('.searchBtn').on("click",function(){
		var url = "#auditLevel/auditLevelSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
		//基础条件查询添加监听
	});
}


var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_auditLevel").serializeJson();
	//console.log(searchData);
	//列表重新加载
    $("#auditLevelTable").cgetData();  
};

/**
 * 修改按钮监听方法
 */
var updateMonitor = function() {
	$(".updateBtn").off("click").on("click", function() {
		if($("#auditLevelTable").find("tr.selected").length>0){
		// 表单可编辑
		$("#auditLevelDetailform").toggleEditState(true);
		// 按钮显示
		$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
};


/**
 * 选中，查详述
 */
var trSelectedBack =function(v, i, index, t, json){
	//清空右侧
	$(".editbtn").addClass("hidden");
	// 参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('auditLevelDetailform','','',queryBack);
}

var queryBack=function(data){
	$(".contributionCode").val(data.contributionCode);
	//查询出资方式
	//$("#projType").change();
	
}


//增加监听
var addMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		//清除选中行样式
		$("#auditLevelDetailform input").val("");
		//切换可编辑状态
		$("#auditLevelDetailform").toggleEditState(true);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
}

//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#auditLevelTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除审核级别？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的审核级别！',
				accept: popClose
			});
		}
	});
};

/**
 * 初始化列表
 */
var auditLevel = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	handleAuditLevel();
        }
    };
}();


//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	$("#auditLevelTable").deleteRow(0,"auditLevel/deleteAuditLevel",$("#auditLevelTable").DataTable());
}






