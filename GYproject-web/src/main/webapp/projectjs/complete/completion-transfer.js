
var searchData = {};
var handleCompletionTransfer = function() {
	"use strict";
    if ($("#completionTransferTable").length !== 0) {
    	$("#completionTransferTable").on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//加载页面
   			$("#filing_data_panel_box").cgetContent("completionTransfer/viewPage");
   			//隐藏遮罩
   			$('#completionTransferTable').hideMask();
   			$("#completionTransferTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'completionTransfer/queryCompletionTransfer',
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
                {"data":"projNo",responsivePriority:1},
				{"data":"projName",responsivePriority:3},
				{"data":"projAddr",responsivePriority:6},
				{"data":"projStatusDes",responsivePriority:5},
				{"data":"overdue",className:"none never"}
			],
			columnDefs: [{
				"targets":1,
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
};

var trSelectedBack = function(v, i, index, t, json){
	$("#completionTransferTable").cgetDetail('completionTransferForm','','',tableCallBack);
}

tableCallBack = function(){
	var len = $('#completionTransferTable').DataTable().ajax.json().data ? $('#completionTransferTable').DataTable().ajax.json().data.length : $('#completionTransferTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#completionTransferForm').formReset();
		 $(".toolBtn").addClass("hidden");
		 $("#completionTransferForm").toggleEditState(false);
	 }else{
		 $(".toolBtn").removeClass("hidden");
		 $("#fdDate").val($(".baseTime").val());
		 $("#staffName1").val($(".staffName").val());
		 $("#fdAuditor").val($(".staffId").val());
		//当前日期
		 $("#fdDate").change();
	 }
}

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#completionTransfer/searchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	//基础条件查询添加监听
	$('#completionTransferTable_filter input').on('change',function(){
		var projNo = $('#completionTransferTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#completionTransferTable').cgetData(true,tableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#completionTransferTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
}

var searchDone = function(){
	searchData = $("#projectSearchPopPage").serializeJson();
	searchData.projNo = $("#completionTransferTable_filter input").val();
    $("#completionTransferTable").cgetData(true,tableCallBack);  //列表重新加载
}

/**
 * 初始化列表
 */
var completionTransfer = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){    
        		handleCompletionTransfer();
        	});
        }
    };
}();