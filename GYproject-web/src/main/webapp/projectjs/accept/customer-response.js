var offdrawing;      //工程列表
var materialtable; //图纸列表
var drawData={};//工程 
var menuId="110214";
drawData.menuId=menuId;
var drawingData={};

var handlecustomerResponse = function() {
	"use strict";
    if ($('#custResponseTable').length !== 0) {
    	offdrawing= $('#custResponseTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#cust_reponse_panel_box").cgetContent("customerResponse/viewPage");
   			//隐藏遮罩
   			$('#custResponseTable').hideMask();
   			$("#custResponseTable_filter input").attr("placeholder","工程编号");
   			$("#custResponseTable_filter input").on("change",function(){
   				drawData={};
   				drawData.projStatusId=$("#projStatus").val();
   				drawData.projNo=$("#custResponseTable_filter input").val();
//   				offdrawing.ajax.reload();	
   				$("#custResponseTable").cgetData(true,cgetDataCallBack);  //列表重新加载
   			});
   			//基础条件查询添加回车事件
   			$('#custResponseTable_filter input').on('keyup', function(event) {
   		        if (event.keyCode == "13") {
   		        	$(this).change();
   		        }
   		    });
   			//查询按钮弹出屏查询
   			$(".searchBtn").off("click").on("click",function(){
   					var url = "#customerResponse/projectSearchPopPage";
   					var popoptions = {
   						title: '查询',
   						content: url,
   						accept: searchDone
   					};
   					//加载弹屏
   					$("body").cgetPopup(popoptions);
   			});
   			//登记监听方法
   			registerMonitor();
   			setTimeout(function(){
   			    $("#custResponseTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
   			//跳转链接
   			if (crossPageBus) {
   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   					//跳转后销毁对象
   				crossPageBus = null
   			}
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '回复', className: 'btn-sm btn-query business-tool-btn registerBtn'}
            ],
           
            //ajax: 'projectjs/design/json/official_drawing.json',
            serverSide:true,
            ajax: {
                url: 'customerResponse/queryProject',
                type:'post',
                data: function(d){
                   	$.each(drawData,function(i,k){
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
				{"data":"projNo",responsivePriority: 2},
				{"data":"projName",responsivePriority: 1},
				{"data":"projStatusDes",responsivePriority: 3},
				{"data":"workDayDto",responsivePriority: 4},
				{"data":"overdue", className:"none never"}
			],
			columnDefs: [{
				"targets":0,
				"visible":false
			},
			{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 8, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":3,
				 "orderable":false
			},{
				"targets":4,
				 "render":function(data,type,row,meta){
					 if(data!=null){
						 return data.haveDays;
					 }else{
						 return 0;
					 }
				 }
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
};

var datailBack = function () {
    if($("#designNo").val()==''){
        $("#designNo").val($("#projNo").val());
    }
    
    if($("#custName").val()==""){
		$(".noUser").addClass("hidden");
	}else{
		$(".noUser").removeClass("hidden");
	}
    
}

/**
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	t.cgetDetail('custResponseForm','customerResponse/viewProject','',datailBack);
}
/**
 * 初始化工程列表
 */
var customerResponse = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){ 
        		handlecustomerResponse();
        	});
        }
    };
}();


/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	
	drawData = $("#searchForm_draw").serializeJson();
	drawData.menuId=menuId;
	drawData.projNo = $('#custResponseTable_filter input').val()
    $("#custResponseTable").cgetData(true,cgetDataCallBack);  //列表重新加载
    
}

var cgetDataCallBack = function(){
	var len = $('#custResponseTable').DataTable().ajax.json().data ? $('#custResponseTable').DataTable().ajax.json().data.length : $('#custResponseTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $("#custResponseForm")[0].reset();
		 $(".toolBtn").addClass("hidden");
	 }else{
		 $(".toolBtn").addClass("hidden");
	 }
}

/**
 * 登记按钮监听方法
 */
var registerMonitor = function(){
	$('.registerBtn').off('click').on('click',function(){
		if($('#custResponseTable').find('tr.selected').length>0){
			//切换可编辑状态
			$('#custResponseForm').toggleEditState(true);
			//维护按钮显示出来
			$('.toolBtn').removeClass('hidden');
		}else{
			alertInfo('请选择要登记的工程记录！');
		}
	});
};


