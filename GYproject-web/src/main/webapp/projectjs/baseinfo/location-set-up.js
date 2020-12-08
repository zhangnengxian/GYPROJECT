var scoretable;   //定位设置信息列表

var searchData={};
/**
 * 初始化评分标准列表
 */
var handleScoreStandard = function() {
	"use strict";
    if ($('#locationSetUpTable').length !== 0) {
    	scoretable= $('#locationSetUpTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#accessory_item_panel_box").cgetContent("locationSetUp/viewPage");
   			//隐藏遮罩
   			$('#locationSetUpTable').hideMask();
   	    	//绑定单击事件 弹出搜索框
   			searchPop();
   			//回车监听
   			keySearch();
   			//编制监听
   			//plait();
   			//修改监听
   			signMonitor();
   			//增加监听
   			insertMonitor();
   			//删除监听
   			deleteMonitor();
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
               /* { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },*/
                { text: '增加', className: 'btn-sm btn-query business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                { text: '删除', className: 'btn-sm btn-query business-tool-btn deleteBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'locationSetUp/quertLocationSetUp',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/plan/json/plan_establish.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"lsuId",className:"none never"},
	  			{"data":"deptName"}, 
				{"data":"locationDuration",className:"text-right"}
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
			}]
        });
    }
};


/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	
	$(".editbtn").addClass("hidden");
	trSData.t && t.cgetDetail('locationSetUpRightform','','',ceshi);
}
function ceshi(data){
	/*$("#ssId").val(data.ssId);
    $("#departmentId").val(data.dept.deptId);*/
    
	
}


/**
 * 初始化定位设置信息列表
 */
var scoreStandard = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleScoreStandard();
        }
    };
}();

/**
 * 回车查询
 */
var keySearch=function(){
	//基础条件查询添加回车事件
	$('#locationSetUpTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	var deptId = $("#locationSetUpTable_filter input").val();
	    	searchData = {};
	    	searchData.deptId = deptId;
			$("#locationSetUpTable").cgetData();  //列表重新加载
	    }
	});
}

/**
 * 弹屏监听方法
 */
var searchPop = function(){
	$('.searchBtn').on("click",function(){
			var url = "#scoreStandard/scoreStandardPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
			//基础条件查询添加监听
	});
	$('#deptName').on('change',function(){
		var deptId = $('#deptName').val();
		searchData = {};
		searchData.deptId = deptId;
		$('#locationSetUpTable').cgetData(true,locationSetUpTableCallBack);  //列表重新加载
	});
};

var locationSetUpTableCallBack=function(){
	var len = $('#locationSetUpTable').DataTable().ajax.json().data ? $('#locationSetUpTable').DataTable().ajax.json().data.length : $('#locationSetUpTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#locationSetUpRightform')[0].reset();
		 $(':input','#locationSetUpRightform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}

/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_scoreStandard").serializeJson();
	//console.log(searchData);
	//列表重新加载
    $("#locationSetUpTable").cgetData();  
};

//修改监听
var signMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		if($('#locationSetUpTable').find('tr.selected').length>0){
			$('#locationSetUpRightform').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要修改的评分标准！');
		}
	});
};

//增加监听
var insertMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		//清除选中行样式
		$("#locationSetUpRightform input").val("");
		$("#deptId").val("");
		//切换可编辑状态
		$("#locationSetUpRightform").toggleEditState(true);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
};

//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#locationSetUpTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除定位设置信息？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的定位设置信息！',
				accept: searchDone
			});
		}
	});
};

//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	$("#locationSetUpTable").deleteRow(0,"locationSetUp/delLocationSetUp",$("#locationSetUpTable").DataTable());
}

