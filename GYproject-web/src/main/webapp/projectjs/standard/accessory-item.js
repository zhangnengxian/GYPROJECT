var accessorytable;   //资料标准列表

var searchData={};
/**
 * 初始化资料标准列表
 */
var handleAccessoryItem = function() {
	"use strict";
    if ($('#accessoryItemTable').length !== 0) {
    	accessorytable= $('#accessoryItemTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#accessory_item_panel_box").cgetContent("accessoryItem/accessoryItemView");
   			//隐藏遮罩
   			$('#accessoryItemTable').hideMask();
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
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '增加', className: 'btn-sm btn-query business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                { text: '删除', className: 'btn-sm btn-query business-tool-btn deleteBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'accessoryItem/queryItem',
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
                {"data":"caiId",className:"none never"},
	  			{"data":"caiId"}, 
	  			{"data":"dataTypeDes"}, 
				{"data":"accessoryName"}
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},{
		    	targets: 1,
				render: function ( data, type, row, meta ) {
					if(type === "display"){
						if(data === null){
							data = "";
						}else{
							if(row.projType&&row.projType.projTypeDes){
								return row.projType.projTypeDes;
							}
						}
						
					}
					return "";					
					}	
		    },{
				"targets":3,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}]
        });
    }
};


/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	
	$(".editbtn").addClass("hidden");
	trSData.t && t.cgetDetail('accessoryItemRightform','','',ceshi);
}
function ceshi(data){
	$("#caiId").val(data.caiId);
    $("#projTypeId").val(data.projType.projTypeId);
    
	
}


/**
 * 初始化资料标准列表
 */
var accessoryItem = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handleAccessoryItem();
        	});
        }
    };
}();

/**
 * 回车查询
 */
var keySearch=function(){
	//基础条件查询添加回车事件
	$('#accessoryItemTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	var projLtypeId = $("#accessoryItemTable_filter input").val();
	    	searchData = {};
	    	searchData.projLtypeId = projLtypeId;
			$("#accessoryItemTable").cgetData();  //列表重新加载
	    }
	});
}

/**
 * 弹屏监听方法
 */
var searchPop = function(){
	$('.searchBtn').on("click",function(){
			var url = "#accessoryItem/accessorySearchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
			//基础条件查询添加监听
	});
	$('#projLtypeIdDes').on('change',function(){
		var projLtypeId = $('#projLtypeIdDes').val();
		searchData = {};
		searchData.projLtypeId = projLtypeId;
		$('#accessoryItemTable').cgetData(true,accessoryItemTableCallBack);  //列表重新加载
	});
};

var accessoryItemTableCallBack=function(){
	var len = $('#accessoryItemTable').DataTable().ajax.json().data ? $('#accessoryItemTable').DataTable().ajax.json().data.length : $('#accessoryItemTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#accessoryItemRightform')[0].reset();
		 $(':input','#accessoryItemRightform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
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
		if($('#accessoryItemTable').find('tr.selected').length>0){
			$('#accessoryItemRightform').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要修改的资料标准！');
		}
	});
};

//增加监听
var insertMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		//清除选中行样式
		$("#accessoryItemRightform input").val("");
		$("#projLtypeId").val("");
		//切换可编辑状态
		$("#accessoryItemRightform").toggleEditState(true);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
};

//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#accessoryItemTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除资料标准？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的资料标准！',
				accept: searchDone
			});
		}
	});
};

//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	$("#accessoryItemTable").deleteRow(0,"accessoryItem/deleteCollectAccessoryItem",$("#accessoryItemTable").DataTable());
}

