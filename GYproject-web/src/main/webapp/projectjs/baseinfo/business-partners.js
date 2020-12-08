var mytable;       //列表
var dataTableFirst;  //资料列表
var dataTableSecond;
var dataPopTable;
var searchData={}; //查询条件
var accessoryData={};
/**
 * 加载工程列表
 */
var handleBusinessPartners = function() {
	'use strict';
    if ($('#businessPartnersTable').length !== 0) {
    	mytable= $('#businessPartnersTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#businessPartners_establish_panel_box').cgetContent('businessPartners/viewBusinessPartnersPage');
   			//隐藏遮罩
   			$('#businessPartnersTable').hideMask();
   			$('#businessPartnersTable_filter input').attr('placeholder','单位名称');
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
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '增加', className: 'btn-sm btn-query  business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                { text: '删除', className: 'btn-sm btn-query business-tool-btn delBtn' }
             ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'businessPartners/queryBusinessPartners',
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
						"data" : "unitId",
						className : "none never"
					}, {
						"data" : "unitName"
					}, {
						"data" : "unitDirector"
					}, {
						"data" : "unitMobile"
					}
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
	$(".searchBtn").on("click", function() {
		var url = "#businessPartners/businessPartnersSearchPopPage";
		var popoptions = {
			title : '查询',
			content : url,
			accept : searchDone
		};
		// 加载弹屏
		$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#businessPartnersTable_filter input").on("change",function(){
		var unitName = $("#businessPartnersTable_filter input").val();
		searchData = {};
		searchData.unitName = unitName;
		console.info(unitName)
		$("#businessPartnersTable").cgetData();  //列表重新加载
		
	});
	//基础条件查询添加回车事件
	$('#businessPartnersTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
}


/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function() {
	// 查询条件
	searchData = $("#searchForm_businessPartners").serializeJson();
	//console.log(searchData);
	// 列表重新加载
	$("#businessPartnersTable").cgetData();
}

/**
 * 修改按钮监听方法
 */
var updateMonitor = function() {
	$(".updateBtn").off("click").on("click", function() {
		if($("#businessPartnersTable").find("tr.selected").length>0){
		// 表单可编辑
		$("#businessPartnersDetailform").toggleEditState(true);
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
		$('#businessPartnersTable tr.selected').removeClass("selected");
		$("#businessPartnersDetailform")[0].reset();
		$("#unitId").val("");
		//切换可编辑状态
		$("#businessPartnersDetailform").toggleEditState(true);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
}

/**
 * 删除按钮监听方法
 */
var delMonitor = function() {	
	$(".delBtn").on("click", function() {
		if($("#businessPartnersTable").find("tr.selected").length>0){
			// 加载弹屏
			$("body").cgetPopup({
				title : '删除',
				content : '确认删除选择的单位？',
				accept : delDone
			});
		}else{
			// 加载弹屏
			$("body").cgetPopup({
				title : '删除',
				content : '请选择要删除的单位！',
				accept : deleteDone
			});
		}
	})
};

var delDone=function(){
	$("#businessPartnersTable").deleteRow("", "businessPartners/delBusinessPartners",$("#businessPartnersTable").DataTable());
	// 列表重新加载
	$("#businessPartnersTable").cgetData();
}

var deleteDone=function(){
	
}

/**
 * 选中，查详述
 */
var trSelectedBack =function(v, i, index, t, json){
	//清空右侧
	$('#businessPartnersDetailform').formReset();
	$(".editbtn").addClass("hidden");
	// 参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('businessPartnersDetailform', 'businessPartners/viewBusinessPartners', '');
}

/**
 * 初始化列表
 */
var businessPartners = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	handleBusinessPartners();
        }
    };
}();









