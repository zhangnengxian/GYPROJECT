var searchData={}; //查询条件
/**
 * 加载工程列表
 */
var handleDataFeedback = function() {
	"use strict";
    if ($('#dataFeedbackTable').length !== 0) {
    	$('#dataFeedbackTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#data_feedback_panel_box").cgetContent("dataFeedback/viewPage");
   			//隐藏遮罩
   			$('#dataFeedbackTable').hideMask();
   			$("#dataFeedbackTable_filter input").attr("placeholder","工程编号");
       
   		//绑定单击事件 弹出搜索框
   			searchMonitor();
   		//资料反馈监听
   			dataMonitor();
    	}).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '资料反馈', className: 'btn-sm btn-query business-tool-btn dataBtn' }
                ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'dataFeedback/queryDataFeedback',
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
                {"data":"projId",className:"none never"},
				{"data":"projNo"},
				{"data":"projName"},
				{"data":"projStatusDes"},
				{"data":"overdue",className:"none never"}
				],
			columnDefs: [{
				'targets':0,
				 'visible':false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
}

/**
 * 弹屏监听方法
 */
searchMonitor = function(){
	$('.searchBtn').on('click',function(){
		var url = "#dataFeedback/projectSearchPopPage";
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});

	// 基础条件查询添加监听
	$('#dataFeedbackTable_filter input').on('change', function() {
		var projNo = $('#dataFeedbackTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#dataFeedbackTable').cgetData(true,tableCallBack); // 列表重新加载
	});
	// 基础条件查询添加回车事件
	$('#dataFeedbackTable_filter input').on('keyup', function(event) {
		if (event.keyCode == '13') {
			$(this).change();
		}
	});
},

/**
 * 查询弹出屏，点击确定后 
 */
searchDone = function(){
	searchData = $('#searchProjectSelfCheck').serializeJson();
	var projNo = $('#dataFeedbackTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#dataFeedbackTable').cgetData(true,tableCallBack);  
},



/**
 * 选中行时，查询详述
 */
trSelectedBack = function(v, i, index, t, json){
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	//t.cgetDetail('dataFeedbackRightform','','',tableCallBack);
	t.cgetDetail('dataFeedbackRightform','dataFeedback/jointDetail','',tableCallBack);
}

tableCallBack = function(){
	$(".editbtn").addClass("hidden");
	var len = $('#dataFeedbackTable').DataTable().ajax.json().data ? $('#dataFeedbackTable').DataTable().ajax.json().data.length : $('#jointAcceptanceTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#dataFeedbackRightform')[0].reset();
		 $(".editbtn").addClass("hidden");
		 $("#dataFeedbackRightform").toggleEditState(false);
	 }else{
		 $("#businessOrderId").val($("#projId").val());
//		 $(".editbtn").removeClass("hidden");
//		 $("#dataFeedbackRightform").toggleEditState(true);
	 }
}

//资料反馈监听
var dataMonitor = function(){
	$('.dataBtn').off('click').on('click',function(){
		if($('#dataFeedbackTable').find('tr.selected').length>0){
			//维护按钮显示出来
			$('.editbtn').removeClass('hidden');
			//切换可编辑状态
			$('#dataFeedbackRightform').toggleEditState(true);
		}else{
			alertInfo('请选择要验收的工程记录！');
		}
	});
}



/**
 * 初始化工程列表
 */
var dataFeedback = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){      
        		handleDataFeedback();
        	});
        }
    };
}();

