var searchData={}; //查询条件
/**
 * 加载工程列表
 */
var handleoperateWorkFlow = function() {
	'use strict';
    if ($('#operateWorkFlowTable').length !== 0) {
    	$('#operateWorkFlowTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#operateWorkFlow_panel_box').cgetContent('operateWorkFlow/viewPage');
   			//隐藏遮罩
   			$('#operateWorkFlowTable').hideMask();
   			//$('#operateWorkFlowTable_filter input').attr('placeholder','单位名称');
   			
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
             bStateSave:true,
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
                url: 'operateWorkFlow/queryList',
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
					{"data" : "owfId",className : "none never"}, 
					{"data" : "corpName"}, 
					{"data" : "projectTypeDes"}, 
					{"data" : "contributionModeDes"}, 
					{"data" : "stepDes"}, 
					{"data" : "opereater"}
			   ],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 },{
                     "targets": 2,
                     render: function (data, type, row, meta) {
                         if (type === "display") {
                             if (data === null || data === "") {
                                 data = "全局参数";
                             } else {
                                 data = data;
                             }
                         }
                         return data;
                     }
                 },{
                 "targets": 3,
                 render: function (data, type, row, meta) {
                     if (type === "display") {
                         if (data === null || data === "") {
                             data = "全局参数";
                         } else {
                             data = data;
                         }
                     }
                     return data;
                 }
             },{
                 "targets": 4,
                 render: function (data, type, row, meta) {
                     if (type === "display") {
                     	console.info(222,data);
                         if (data === null || data === "") {
                             data = "全局参数";
                         } else {
                             data = data;
                         }
                     }
                     return data;
                 }
             }],
        });
    }
};



/**
 * 查询按钮监听方法
 */
var searchMonitor=function(){
	$('.searchBtn').on("click",function(){
		var url = "#operateWorkFlow/operateWorkFlowSearchPopPage";
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
    $("#operateWorkFlowTable").cgetData();  
};

/**
 * 修改按钮监听方法
 */
var updateMonitor = function() {
	$(".updateBtn").off("click").on("click", function() {
		if($("#operateWorkFlowTable").find("tr.selected").length>0){
		// 表单可编辑
		$("#corpId,#projectType,#contributionMode,#stepId,#opType").removeClass("field-editable");
		$("#corpId,#projectType,#contributionMode,#stepId,#opType").addClass("field-not-editable");
		$("#operateWorkFlowDetailform").toggleEditState(true);
		$("#opereaterPop").removeClass("disabled");
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
	t.cgetDetail('operateWorkFlowDetailform','','',queryBack);
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
		//$("#operateWorkFlowDetailform input").val("");
        $('#operateWorkFlowTable tr.selected').removeClass("selected");
        $("#operateWorkFlowDetailform")[0].reset();
        $("#owfId").val('');
        $(".addClear").val('');
		//切换可编辑状态
        $("#corpId,#projectType,#contributionMode,#stepId,#opType").removeClass("field-not-editable");
        $("#corpId,#projectType,#contributionMode,#stepId,#opType").addClass("field-editable");
		$("#operateWorkFlowDetailform").toggleEditState(true);
		$("#opereater").attr("readonly",false);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
}

//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		$('#operateWorkFlowTable').loadMask("正在删除···",1,0.5);
		if($('#operateWorkFlowTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除当前记录，删除后无法恢复？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的记录！',
				accept: popClose
			});
		}
	});
};

/**
 * 初始化列表
 */
var operateWorkFlow = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	handleoperateWorkFlow();
        }
    };
}();


//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	$("#operateWorkFlowTable").deleteRow(0,"operateWorkFlow/deleteByOwfId",$("#operateWorkFlowTable").DataTable());
	$('#operateWorkFlowTable').hideMask();
}






