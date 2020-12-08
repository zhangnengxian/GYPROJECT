var searchData={}; //查询条件
/**
 * 加载工程列表
 */
var handleConnectConfirm = function() {
	"use strict";
    if ($('#gasConfirmTable').length !== 0) {
    	$('#gasConfirmTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面 
   			$("#gas_confirm_panel_box").cgetContent("gasConfirm/viewPage");
   			//隐藏遮罩
   			$('#gasConfirmTable').hideMask();
   			$("#gasConfirmTable_filter input").attr("placeholder","工程编号");
       
   		    //绑定单击事件 弹出搜索框
   			searchMonitor();
   			//通气申请
   			applyMonitor();
    	}).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '申请', className: 'btn-sm btn-query business-tool-btn applyBtn' }
                ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'gasConfirm/queryGasConfirm',
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
		var url = "#gasConfirm/projectSearchPopPage";
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});

	// 基础条件查询添加监听
	$('#gasConfirmTable_filter input').on('change', function() {
		var projNo = $('#gasConfirmTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#gasConfirmTable').cgetData(true,tableCallBack); // 列表重新加载
	});
	// 基础条件查询添加回车事件
	$('#gasConfirmTable_filter input').on('keyup', function(event) {
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
	var projNo = $('#gasConfirmTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#gasConfirmTable').cgetData(true,tableCallBack);  
}

//申请
var applyMonitor=function(){
	$(".applyBtn").off().on("click",function(){
		if($("#gasConfirmTable").find("tr.selected").length>0){
			//切换可编辑状态
			$("#gasConfirmRightform").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
		
	})
}


/**
 * 选中行时，查询详述
 */
trSelectedBack = function(v, i, index, t, json){
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	$("#gasConfirmTable").cgetDetail('gasConfirmRightform','gasConfirm/jointDetail','',tableCallBack);
}

tableCallBack = function(){
	$("#payment").val("30%");
	//按钮隐藏
  	/*if($("#subFlag").val()=='true'){
  		$(".editbtn").addClass("hidden");
  	}else{
  		$(".editbtn").removeClass("hidden");
  	}*/
  	
	var len = $('#gasConfirmTable').DataTable().ajax.json().data ? $('#gasConfirmTable').DataTable().ajax.json().data.length : $('#gasConfirmTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#gasConfirmRightform')[0].reset();
		 $("#gasConfirmRightform").toggleEditState(false);
	 }else{
		 //$("#businessOrderId").val($("#projId").val());
		 var res = parseFloat($("#scAmount").val() || 0)*0.3;
		 $("#payGasAmount").val(res.toFixed(2));
		 $(".editbtn").addClass("hidden");
	 }
}


/**
 * 初始化工程列表
 */
var gasConfirm = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){    
        		handleConnectConfirm();
        	});
        }
    };
}();

