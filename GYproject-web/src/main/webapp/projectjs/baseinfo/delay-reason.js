var delayReasonTable;   //资料标准列表

var searchData={};
/**
 * 初始化资料标准列表
 */
var handleDelayReason = function() {
	"use strict";
    if ($('#delayReasonTable').length !== 0) {
    	delayReasonTable= $('#delayReasonTable').on( 'init.dt',function(){
    		$("#delayReasonTable_filter input").attr("placeholder","延期原因");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#pt_panel_box").cgetContent("delayReason/viewPage");
   			//隐藏遮罩
   			$('#delayReasonTable').hideMask();
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
            dom: 'fBrtip',
            buttons: [
                { text: '增加', className: 'btn-sm btn-query business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                { text: '删除', className: 'btn-sm btn-query business-tool-btn deleteBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'delayReason/queryDelayReasonList',
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
                {"data":"delayReasonId",className:"none never"},
	  			{"data":"delayReasonDes"}
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
	trSData.t && t.cgetDetail('delayReasonForm');
}




/**
 * 回车查询
 */
var keySearch=function(){
	//基础条件查询添加回车事件
	$('#delayReasonTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	var projTypeDes = $("#delayReasonTable_filter input").val();
	    	searchData.delayReasonDes = projTypeDes;
			$("#delayReasonTable").cgetData();  //列表重新加载
	    }
	});
}



var delBack=function(data){
	
	var len = $('#delayReasonTable').DataTable().ajax.json().data ? $('#delayReasonTable').DataTable().ajax.json().data.length : $('#delayReasonTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#delayReasonForm')[0].reset();
	 }else{
		 $('#delayReasonTable').cgetData();
	 }
}

//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#pricedBoqTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除工程量标准？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的工程量标准！',
				accept: searchDone
			});
		}
	});
};
/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_accessory").serializeJson();
	//console.log(searchData);
	//列表重新加载
    $("#delayReasonTable").cgetData();  
};

//修改监听
var signMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		if($('#delayReasonTable').find('tr.selected').length>0){
			$('#delayReasonForm').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选中要修改的延期原因！');
		}
	});
};

//增加监听
var insertMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		//清除选中行样式
		$("#delayReasonForm input").val("");
		$("#projLtypeId").val("");
		//切换可编辑状态
		$("#delayReasonForm").toggleEditState(true);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
};

//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#delayReasonTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除选中的延期原因？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的延期原因！',
				accept: searchDone
			});
		}
	});
};

//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	$("#delayReasonTable").deleteRow(0,"delayReason/delDelayReason",$("#delayReasonTable").DataTable(),delBack);
}
var searchDone=function(){}
/**
 * 初始化资料标准列表
 */
var delayReason = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleDelayReason();
        }
    };
}();
