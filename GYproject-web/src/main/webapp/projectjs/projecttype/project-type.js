var projectTypeTable;   //资料标准列表

var searchData={};
/**
 * 初始化资料标准列表
 */
var handleProjectType = function() {
	"use strict";
    if ($('#projectTypeTable').length !== 0) {
    	projectTypeTable= $('#projectTypeTable').on( 'init.dt',function(){
    		$("#projectTypeTable_filter input").attr("placeholder","工程类型");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#pt_panel_box").cgetContent("projectType/projectTypeView");
   			//隐藏遮罩
   			$('#projectTypeTable').hideMask();
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
                { text: '删除', className: 'btn-sm btn-warn business-tool-btn deleteBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'projectType/queryProjectType',
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
                {"data":"projTypeId",className:"none never"},
	  			{"data":"projTypeDes"},
	  			{"data":"projConstructDes"}
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
	trSData.t && t.cgetDetail('projectTypeForm');
}




/**
 * 回车查询
 */
var keySearch=function(){
	//基础条件查询添加回车事件
	$('#projectTypeTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	var projTypeDes = $("#projectTypeTable_filter input").val();
	    	searchData.projectTypeDes = projTypeDes;
			$("#projectTypeTable").cgetData();  //列表重新加载
	    }
	});
}



var delBack=function(data){
	
	var len = $('#projectTypeTable').DataTable().ajax.json().data ? $('#projectTypeTable').DataTable().ajax.json().data.length : $('#projectTypeTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#projectTypeForm')[0].reset();
		 $(':input','#projectTypeForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}
var deleteRow = function(col, url, dtc, callback){
	
	"use strict";
	
    //请求路径为必填参数
    if(url === "" || url === undefined){
        console.error('deleteRow() -> 未设置请求路径 url!');
        return false;
    }
    //默认为第一列
    col = col === "" ? 0 : col;
    var t = $("#projectTypeTable");
    dtc = !dtc ? t.DataTable() : dtc;
    //获取选中行的对象
    var s = t.find("tr.selected");
    var m = s.length > 0 ? "id=" : "";
    //如果没有选中行弹出提示
    if (m === "") {
    	if(_inApk){
    		navigator.notification.alert('请选择要删除的数据！', null, '提示信息', '确定');
    	}else{
            $("body").cgetPopup({
        		title: '提示信息',
        		content: '请选择要删除的数据！',
        		accept: popClose,
        		icon: 'fa-exclamation-circle',
        		size: 'small'
        	});
        }
        return false;
    }
    //获取值
    for( i=0; i<s.length; i++ ){
        //获取当前选中行在表格中的索引
        var rindx = s.eq(i).index();
        //通过dtc获取指定行和列的单元格的值
        m += dtc.column(col).data()[rindx];
        //m = m + s.eq(i).find("td:eq("+ col +")").text();
        m += (s.length - 1 === i ? "" : ",");
    }
    $.ajax({
        type: 'POST',
        url: url,
        data: m,
        dataType: 'html',
        success: function (data) {
            if (data === "true") {
                //刷新表格
                dtc.ajax.reload(function (json) {
                    //第一行默认选中
                	t.selectRow(0);
                    callback && callback(json);
                });
            	if(_inApk){
            		navigator.notification.alert('数据删除成功！', null, '提示信息', '确定');
            	}else{
                    $("body").cgetPopup({
                    	title: '提示信息',
                    	content: '数据删除成功！',
                    	accept: popClose,
                    	icon: 'fa-check-circle'
                    });
                }
            }else if(data=="psExit"){
        		console.info("-------------");
        		alertInfo('工程类型已关联工程规模,不允许删除！');
        		if(_inApk){
            		navigator.notification.alert('工程类型已关联工程规模,不允许删除！', null, '提示信息', '确定');
            	}
        		return false;
        	}else if(data=="citExit"){
        		
        		alertInfo('工程类型已关联资料标准,不允许删除！');
        		if(_inApk){
            		navigator.notification.alert('工程类型已关联资料标准,不允许删除！', null, '提示信息', '确定');
            	}
        		return false;
        	}else{
            	if(_inApk){
            		navigator.notification.alert('数据删除失败, 请重试！', null, '提示信息', '确定');
            	}else{
                	$("body").cgetPopup({
                       	title: '提示信息',
                        content: '数据删除失败, 请重试!',
                        accept: popClose,
                        icon: 'fa-exclamation-circle'
                    });
            	}
            }
            $('html, body').animate({
                scrollTop: $("body").offset().top
            }, 250);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            printXHRError("deleteRow", "表格(" + t.attr("id") + ") ajax删除查询失败", jqXHR, textStatus, errorThrown);
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
    $("#projectTypeTable").cgetData();  
};

//修改监听
var signMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		if($('#projectTypeTable').find('tr.selected').length>0){
			$('#projectTypeForm').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要修改的工程类型！');
		}
	});
};

//增加监听
var insertMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		//清除选中行样式
		$("#projectTypeForm input").val("");
		$("#projLtypeId").val("");
		//切换可编辑状态
		$("#projectTypeForm").toggleEditState(true);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
};

//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#projectTypeTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除工程类型？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的工程类型！',
				accept: searchDone
			});
		}
	});
};

//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	deleteRow(0,"projectType/deleteProjectType",$("#projectTypeTable").DataTable(),delBack);
}

/**
 * 初始化资料标准列表
 */
var projectType = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleProjectType();
        }
    };
}();
