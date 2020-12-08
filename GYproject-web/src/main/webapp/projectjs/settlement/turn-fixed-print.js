var searchData = {};
searchData.isPrint=$("#haveNotPrint").val();
var handleSettlementAuditPrint = function(){
	"use strict";
	 if ($('#turnFixedPrintTable').length !== 0) {
		 $('#turnFixedPrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#turnFixedPrintTable').hideMask();
   			//基础查询条件
   			$("#turnFixedPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   		    showReport();
   		    //标记监听
   			signMonitor();
		 }).DataTable({
			language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '标记', className: 'btn-sm btn-query signBtn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
			ajax: {
                url: 'turnFixedPrint/queryTurnFixedApply',
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
                      {"data":"projNo",responsivePriority:2},
                      {"data":"projName",responsivePriority:3}, 
                      {"data":"projectTypeDes",responsivePriority:4},
                      {"data":"contributionModeDes",responsivePriority:5}, 
                      {"data":"scNo",responsivePriority:6}, 
                      {"data":"tfaDate",responsivePriority:7},
                      {"data":"projTotalCost",className:"text-right",responsivePriority:8},
                      {"data":"projCost",className:"text-right",responsivePriority:9},
      		],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 },{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 15, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},{
				 'targets':7,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							return parseFloat(data).toFixed(2);
						}else{
							return data;
						}
					},
			 	},{
					 'targets':8,
					 "render": function ( data, type, row, meta ) {
							if(data!=="" && data!==null){
								return parseFloat(data).toFixed(2);
							}else{
								return data;
							}
						},
				 	},{
						"targets":3,
						 "orderable":false
					},{
						"targets":4,
						 "orderable":false
					},{
						"targets":2,
						//长字符串截取方法
						render: $.fn.dataTable.render.ellipsis({
							length: 10, 	//截取多少字符（或汉字）
							end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
						})
					}]
		 });
	 }
}
//初始化列表回调
var trSelectedBack = function(v, i, index, t, json){
	showReport();	
}
//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#turnFixedPrint/turnFixedSearchPop";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#turnFixedPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#turnFixedPrintTable_filter input").val();
		$("#turnFixedPrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#turnFixedPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
//弹屏确定回调
var searchDone = function(){
	searchData = $("#searchTurnFixedPop").serializeJson();
	searchData.projNo = $("#turnFixedPrintTable_filter input").val();
	//列表重新加载
    $("#turnFixedPrintTable").cgetData(true, tableCallBack);  
}
//列表重加载回调
var tableCallBack = function(){
	var len = $('#turnFixedPrintTable').DataTable().ajax.json().data ? $('#turnFixedPrintTable').DataTable().ajax.json().data.length : $('#turnFixedPrintTable').DataTable().ajax.json().length;
	if(len<1){
		src = cptPath+"/ReportServer?reportlet=settlement/turnFixedApply.cpt"
		$("#mainFrm").attr("src",src);
	 }else{
		 showReport();
	 }
	
}
//初始化列表
var settlementAuditPrint = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleSettlementAuditPrint();
        	});
        }
    };
}();

//标记监听
var signMonitor=function(){
	$('.signBtn').off().on('click',function(){
		var len=$("#turnFixedPrintTable").find('tr.selected').length;
		if(len>0){
           $("body").cgetPopup({
               	title: "提示信息",
               	content: "是否将选中项标记为已打印？",
               	accept: sureDone,
               	chide: true,
               	newpop: 'new',
               	icon: "fa-check-circle"
           });
		}else{
			alertInfo('请选择要标记打印的项目！');
		}
	});
}

var sureDone=function(){
	var projId=trSData.turnFixedPrintTable.json.projId;
	$.ajax({
		type:'post',
		url:'turnFixedPrint/signTurnFixedPrint',
		contentType: "application/json;charset=UTF-8",
        data: projId,
        success:function(data){
        	var content = "标记成功！";
        	if(data=="false"){
        		content = "标记失败！";
        	}
        	var myoptions = {
                	title: "提示信息",
                	content: content,
                	accept: tableReload,
                	chide: true,
                	icon: "fa-check-circle",
                	newpop: 'new'
                	
            }
            $("body").cgetPopup(myoptions);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("数据保存失败！");
        }
        
	})
}
var tableReload=function(){
	searchData.isPrint=$("#haveNotPrint").val();
	$("#turnFixedPrintTable").cgetData();
}