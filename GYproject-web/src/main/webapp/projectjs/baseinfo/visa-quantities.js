var accessorytable;   //资料标准列表

var searchData={};
/**
 * 初始化碰口内容列表
 */
var handleConnectContent = function() {
	"use strict";
    if ($('#visaQuantitiesTable').length !== 0) {
    	accessorytable= $('#visaQuantitiesTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#visa_quantities_panel_box").cgetContent("visaQuantities/viewPage");
   			//隐藏遮罩
   			$('#visaQuantitiesTable').hideMask();
   	    	
   			//基础查询监听
   			search();
   			
   			//回车监听
   			keySearch();
   			
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
                { text: '增加', className: 'btn-sm btn-query business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                { text: '删除', className: 'btn-sm btn-query business-tool-btn deleteBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'visaQuantities/queryVisaQuantitiesList',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/plan/json/plan_establish.json',
            responsive: true,//自适应
            select: true,  //支持多选
            columns: [
                {"data":"id",className:"none never"},
                {"data":"des"},
	  			{"data":"quantitiesNum",className:"text-right"}, 
	  			{"data":"unitDes"}
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},{
				 'targets':2,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							return parseFloat(data).toFixed(2);
						}else{
							return data;
						}
					},
			 	},]
        });
    }
};


/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	trSData.t && trSData.t.cgetDetail('visaQuantitiesForm');
}


/**
 * 初始化资料标准列表
 */
var connectContent = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleConnectContent();
        }
    };
}();

/**
 * 回车查询
 */
var keySearch=function(){
	//基础条件查询添加回车事件
	$('#visaQuantitiesTable_filter select').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	var id = $("#visaQuantitiesTable_filter select").val();
	    	searchData = {};
	    	searchData.id = id;
			$("#visaQuantitiesTable").cgetData();  //列表重新加载
	    }
	});
}

/**
 * 基础查询
 */
var search = function(){
	$('#connectContentDes').on('change',function(){
		var type = $('#connectContentDes').val();
		searchData = {};
		searchData.type = type;
		$('#visaQuantitiesTable').cgetData(true,visaQuantitiesTableCallBack);  //列表重新加载
	});
};

var visaQuantitiesTableCallBack=function(){
	var len = $('#visaQuantitiesTable').DataTable().ajax.json().data ? $('#visaQuantitiesTable').DataTable().ajax.json().data.length : $('#visaQuantitiesTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#visaQuantitiesForm')[0].reset();
		 $(':input','#visaQuantitiesForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}


/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_accessory").serializeJson();
	//console.log(searchData);
	//列表重新加载
    $("#accessoryItemTable").cgetData();  
};

//修改监听
var signMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		if($('#visaQuantitiesTable').find('tr.selected').length>0){
			$('#visaQuantitiesForm').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要修改的签证标准！');
		}
	});
};

//增加监听
var insertMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		//清除选中行样式
		$("#visaQuantitiesForm input,textarea").val("");
		//切换可编辑状态
		$("#visaQuantitiesForm").toggleEditState(true);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
};

//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#visaQuantitiesTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除签证标准？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的签证标准！',
				accept: searchDone
			});
		}
	});
};

//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	$("#visaQuantitiesTable").deleteRow(0,"visaQuantities/deleteVisaQuantities",$("#visaQuantitiesTable").DataTable(),callback);
	trSData.t && trSData.t.cgetDetail('visaQuantitiesForm');
}
var callback=function(){
	var myoptions = {
           	title: "提示信息",
           	content: "保存数据成功！",
           	accept: popClose,
           	chide: true,
           	newpop: 'new',
           	icon: "fa-check-circle"
      }
	$("body").cgetPopup(myoptions);
}



