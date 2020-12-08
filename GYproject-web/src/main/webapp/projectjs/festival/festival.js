var mytable;       //列表
var dataTableFirst;  //资料列表
var dataTableSecond;
var dataPopTable;
var searchData={}; //查询条件
var accessoryData={};
/**
 * 加载工程列表
 */
var handlefestival = function() {
	'use strict';
    if ($('#festivalTable').length !== 0) {
    	mytable= $('#festivalTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#festival_establish_panel_box').cgetContent('festival/viewPage'); 
   			//隐藏遮罩
   			$('#festivalTable').hideMask();
   			$('#festivalTable_filter input').attr('placeholder','税率编码');
   			//查询监听
   			searchMonitor();
   			//增加监听
   			insertMonitor();
   			//修改监听
   			updateMonitor();
   			//删除监听
   			delMonitor();
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
             /*   { text: '查询', className: 'btn-sm btn-query searchBtn' },*/
                { text: '增加', className: 'btn-sm btn-query  business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                { text: '删除', className: 'btn-sm btn-query business-tool-btn delBtn' }
             ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'festival/queryList',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
             },
             //ajax: 'projectjs/design/json/survey_result_register.json',
             responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
             select: true,  //支持多选
             columns: [
					{
						"data" : "id",
							className: "none never"
					}, {
						"data" : "festivalName"
					}, {
						"data" : "festivalStartDate"
					}, {
						"data" : "festivalEndDate"
					}, {
						"data" : "isValid"
					}, {
						"data" : "dayTypeDes"
					}
			   ],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 },{
				 'targets':4,
				 "render": function ( data, type, row, meta ) {
						if(data==="1"){
							return "启用";
						}else{
							return "禁用";
						}
					},
			 }],
        });
    }
};



/**
 * 查询按钮监听方法
 */
var searchMonitor=function(){
//	$(".searchBtn").on("click", function() {
//		var url = "#festival/festivalPopPage";
//		var popoptions = {
//			title : '查询',
//			content : url,
//			accept : searchDone
//		};
//		// 加载弹屏
//		$("body").cgetPopup(popoptions);
//	});
	//基础条件查询添加监听
	$("#festivalTable_filter input").on("change",function(){
		var unitName = $("#festivalTable_filter input").val();
		searchData = {};
		searchData.unitName = unitName;
		console.info(unitName)
		$("#festivalTable").cgetData();  //列表重新加载
		
	});
	//基础条件查询添加回车事件
//	$('#festivalTable_filter input').on('keyup', function(event) {
//	    if (event.keyCode == "13") {
//	    	$(this).change();
//	    }
//	});
}


/**
 * 查询弹出屏，点击确定后
 */
//var searchDone = function() {
//	// 查询条件
//	searchData = $("#searchForm_festival").serializeJson();
//	//console.log(searchData);
//	// 列表重新加载
//	$("#festivalTable").cgetData();
//}此页面不需要

/**
 * 修改按钮监听方法
 */
var updateMonitor = function() {
	$(".updateBtn").off("click").on("click", function() {
		if($("#festivalTable").find("tr.selected").length>0){
		// 表单可编辑
		$("#festivalDetailform").toggleEditState(true);
		// 按钮显示
		$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
};

//增加监听方法
var insertMonitor = function(){
	$(".addBtn").on("click",function(){
		//移除选中
		$('#festivalTable tr.selected').removeClass("selected");
		$("#festivalDetailform")[0].reset();
		$("#inId").val("");
		//切换可编辑状态
		$("#festivalDetailform").toggleEditState(true);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
}

/**
 * 删除按钮监听方法
 */
var delMonitor = function() {	
	$(".delBtn").on("click", function() {
		if($("#festivalTable").find("tr.selected").length>0){
			// 加载弹屏
			$("body").cgetPopup({
				title : '删除',
				content : '确认删除该记录吗？',
				accept : delDone
			});
		}else{
			// 加载弹屏
			$("body").cgetPopup({
				title : '删除',
				content : '请选择要删除的记录！',
				accept : deleteDone
			});
		}
	})
};

var delDone=function(){
	$("#festivalTable").deleteRow("", "festival/delFestival",$("#festivalTable").DataTable());
	// 列表重新加载
	$("#festivalTable").cgetData();
}

var deleteDone=function(){
	
}

/**
 *分别选中，查详述
 */
var trSelectedBack =function(v, i, index, t, json){
	//清空右侧
	$('#festivalDetailform').formReset();
	$(".editbtn").addClass("hidden");
	// 参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('festivalDetailform', 'festival/viewDetail', '');
}

/**
 * 初始化列表
 */
var festival = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	handlefestival();
        }
    };
}();