var contributionModeTable;   //资料标准列表

var searchData={};
/**
 * 初始化资料标准列表
 */
var handleContributionMode = function() {
	"use strict";
    if ($('#contributionModeTable').length !== 0) {
    	contributionModeTable= $('#contributionModeTable').on( 'init.dt',function(){
    		$("#contributionModeTable_filter input").attr("placeholder","出资方式");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#ps_panel_box").cgetContent("contributionMode/projectScaleView");
   			//隐藏遮罩
   			$('#contributionModeTable').hideMask();
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
                url: 'contributionMode/queryContributionMode',
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
                {"data":"id",className:"none never"},
               /* {"data":"projTypeDes"},*/
	  			{"data":"contributionDes"},
	  			{"data":"code"}
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
	trSData.t && t.cgetDetail('contributionModeForm','','',function(data){
    	$("#projTypeId").val(data.projTypeId);
	});
}




/**
 * 回车查询
 */
var keySearch=function(){
	//基础条件查询添加回车事件
	$('#contributionModeTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	var projTypeDes = $("#contributionModeTable_filter input").val();
	    	searchData.contributionModeDes = projTypeDes;
			$("#contributionModeTable").cgetData();  //列表重新加载
	    }
	});
}



var contributionModeTableCallBack=function(){
	var len = $('#contributionModeTable').DataTable().ajax.json().data ? $('#contributionModeTable').DataTable().ajax.json().data.length : $('#contributionModeTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#contributionModeForm')[0].reset();
		 $(':input','#contributionModeForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
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
    $("#contributionModeTable").cgetData();  
};

//修改监听
var signMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		if($('#contributionModeTable').find('tr.selected').length>0){
			$('#contributionModeForm').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要修改的工程规模！');
		}
	});
};

//增加监听
var insertMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		//清除选中行样式
		$("#contributionModeForm input").val("");
		
		//切换可编辑状态
		$("#contributionModeForm").toggleEditState(true);
		$("#projTypeId").val("");
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
};

//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#contributionModeTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除工程规模？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的工程规模！',
				accept: searchDone
			});
		}
	});
};

//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	$("#contributionModeTable").deleteRow(0,"contributionMode/deleteContributionMode",$("#contributionModeTable").DataTable(),contributionModeTableCallBack);
}

/**
 * 初始化工程规模列表
 */
var contributionMode = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleContributionMode();
        }
    };
}();


