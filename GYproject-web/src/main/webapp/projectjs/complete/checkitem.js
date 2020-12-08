var mytable;       //列表
var searchData={}; //查询条件
/**
 * 加载自检项列表
 */
var handelCheckItem = function(){
	'use strict';
	if($('#checkItemTable').length !== 0){
		mytable= $('#checkItemTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//加载右侧页面
    		$('#checkItems_establish_panel_box').cgetContent('checkItems/viewCheckItemPage');
    		//隐藏遮罩
   			$('#checkItemTable').hideMask();
    		//基础查询条件
   			$("#checkItemTable_filter input").attr("placeholder","描述");
   			//增加监听
   			insertMonitor();
   			//修改监听
   			updateMonitor();
   			//删除监听
   			delBtn();
   			//绑定单击事件 弹出搜索框
   			searchPop();
		}).DataTable({
			 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '增加', className: 'btn-sm btn-query  business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                { text: '删除', className: 'btn-sm btn-query business-tool-btn delBtn' }
             ],
             //启用服务端模式，后台进行分段查询、排序
             ajax: {
            	 url: 'checkItems/queryCheckItems',
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
                  { "data" : "id", className : "none never" },
                  { "data" : "des" },
                  { "data" : "checkTypeDes" },
                  { "data" : "typeDes" }
                    ],
              columnDefs :[{
                  'targets':0,
                  'visible':false
              }]
		});
	}
}
var trSelectedBack =function(v, i, index, t, json){
	//清空右侧
	$('#checkItemDetailform').formReset();
	$(".editbtn").addClass("hidden");
	// 参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('checkItemDetailform', 'checkItems/viewCheckItem', '',formChooseBack);
}

var searchPop = function(){
	$('.searchBtn').on("click",function(){
			var url = "#checkItems/checkItemSearchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
			//基础条件查询添加监听
	});
};


/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_checkItems").serializeJson();
	//console.log(searchData);
	//列表重新加载
    $("#checkItemTable").cgetData();  
};

var formChooseBack = function(){
	var val=$('input:radio[name="type"]:checked').val();
	if(val=="2"){
		$("#chooseCheckType").addClass("hidden");
	}else{
		$("#chooseCheckType").removeClass("hidden");
	}
}
/**
 * 删除按钮监听方法
 */
var delBtn = function(){
	$(".delBtn").on("click", function() {
		if($("#checkItemTable").find("tr.selected").length>0){
			// 加载弹屏
			$("body").cgetPopup({
				title : '删除',
				content : '确认删除自检项？',
				accept : delDone
			});
		}else{
			// 加载弹屏
			$("body").cgetPopup({
				title : '删除',
				content : '请选择要删除的自检项！',
				accept : deleteDone
			});
		}
	})
}

var delDone = function(){
	$("#checkItemTable").deleteRow("", "checkItems/delCheckItem",$("#checkItemTable").DataTable());
	// 列表重新加载
	$("#checkItemTable").cgetData();
}

var deleteDone = function(){
	
}

//增加监听方法
var insertMonitor = function(){
	$(".addBtn").on("click",function(){
		//移除选中
		$('#checkItemTable tr.selected').removeClass("selected");
		$("#checkItemDetailform")[0].reset();
		$("#id").val("");
		//切换可编辑状态
		$("#checkItemDetailform").toggleEditState(true,"all");
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	})
}

//修改监听方法
var updateMonitor = function(){
	$(".updateBtn").off("click").on("click", function() {
		if($("#checkItemTable").find("tr.selected").length>0){
		// 表单可编辑
		$("#checkItemDetailform").toggleEditState(true);
		// 按钮显示
		$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
}

var checkItems = function(){
	'use strict';
	return {
		//main function
		init : function(){
			handelCheckItem();
		}
	}
}();