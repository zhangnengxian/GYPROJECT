var workNoticeTable,disNoticeTable,noticeWorkData={};
var nameLength,signLength;
if(_inApk){
	nameLength=18;
	signLength=18;
}else{
	nameLength=80;
	signLength=60;
}


//代办工作通知
var handleTaskNotice = function() {
	"use strict";
	//初始化table
	if($.fn.DataTable.isDataTable('#workNoticeTable')){
		queryWorkMonitor();
		$("#workNoticeTable").cgetData(true);//列表重新加载
	}else{
		workNoticeTableInit();
	}
};


//代办工作通知
$("#workNoticeTab").on("shown.bs.tab",function(){
	if($.fn.DataTable.isDataTable('#workNoticeTable')){
		queryWorkMonitor();
		$("#workNoticeTable").cgetData(true);//列表重新加载
	}else{
		workNoticeTableInit();
	}
});


//签字通知
$("#signNoticeTab").on("shown.bs.tab",function(){
	if($.fn.DataTable.isDataTable('#signNoticeTable')){
		querySignMonitor();
		$("#signNoticeTable").cgetData(true);//列表重新加载
	}else{
		signNoticeTableInit();
	}
});







//初始化工作通知列表
var workNoticeTableInit= function() {
	"use strict";
    if ($('#workNoticeTable').length !== 0) {
    	workNoticeTable= $('#workNoticeTable').on( 'init.dt',function(){
    		$('#workNoticeTable').hideMask();
    	}).on( 'draw.dt', function () {	
    		//跳转
    		queryWorkMonitor();
    		historyBtnMonitor();
    		manageRecordBtnMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [10],
            dom: 'Brtip',
            bStateSave:true,
            buttons: [
                { text: '操作记录1123-12', className: 'btn-sm btn-query historyBtn' },
                { text: '审核记录', className: 'btn-sm btn-query manageRecordBtn' },
                { text: '待办人', className: 'btn-sm btn-query operaterBtn' }
                      ],
            /*ajax: 'projectjs/accept/json/scale.json',*/
            //启用服务端模式，后台进行分段查询、排序
            serverSide:false,
            ajax: {
            	url: 'todoNotice/queryWaitNotice',
            	type:'post',
            	data: function(d){
                  	$.each(noticeWorkData,function(i,k){
                  		d[i] = k;
                  	});
                  	
            	},
            	dataSrc: 'data'
            },
            
            //ajax: 'projectjs/accept/json/proj_scale.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },//自适应
            columns: [
                {"data":"orId", responsivePriority: 3},       
	  			{"data":"projNo", responsivePriority: 2},
	  			{"data":"projName", responsivePriority: 4},
	  			{"data":"stepDes", responsivePriority: 3},
	  			{"data":"grade", responsivePriority: 3},
	  			{"data":"operater", responsivePriority: 5},
	  			{"data":"url", responsivePriority: 1} ,
                {"data":"menuId", responsivePriority: 6}    
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: nameLength, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				targets: 4,
				render: function (data, type, row, meta) {
					if(type === "display"){
						if(data=="" || data==null){
							data="无";
							return data;
						}else{
							return data+"级";
						}
					}else{
						return data+"级";
					}
				}
			},{
				targets: 6,
				render: function (data, type, row, meta) {
					if(type === "display"){
						var  tdcon='<a class="m-l-5 Search_Button btn btn-primary btn-xs" data-projNo="'+row.projNo+'" data-id="'+row.menuId+'" data-path="'+data+'">查看</a>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				"targets": 7,
			    "visible":false
			}],
			ordering:false
       });
   }
};


//初始化已办通知列表
var signNoticeTableInit= function() {
	"use strict";
    if ($('#signNoticeTable').length !== 0) {
    	workNoticeTable= $('#signNoticeTable').on( 'init.dt',function(){
    		$('#signNoticeTable').hideMask();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [10],
            dom: 'Brtip',
            bStateSave:true,
            buttons: [],
            /*ajax: 'projectjs/accept/json/scale.json',*/
            //启用服务端模式，后台进行分段查询、排序
            serverSide:false,
            ajax: {
            	url: 'todoNotice/queryHaveDoneNotice',
            	type:'post',
            	data: function(d){
                  	$.each(noticeWorkData,function(i,k){
                  		d[i] = k;
                  	});
                  	
            	},
            	dataSrc: 'data'
            },
            
            //ajax: 'projectjs/accept/json/proj_scale.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },//自适应
            columns: [
				{"data":"orId", responsivePriority: 3},       
				{"data":"projNo", responsivePriority: 2},
				{"data":"projName", responsivePriority: 5},
				{"data":"stepDes", responsivePriority: 1},
				{"data":"operater", responsivePriority: 4},
				{"data":"operateTime", responsivePriority: 6}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: signLength, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}],
			ordering:false
       });
   }
};



//跳转链接
var queryWorkMonitor=function(){
	$(".Search_Button").on("click",function(){
		var menuId=$(this).attr("data-id"); //110801
		console.info('menuId--'+menuId);
		var url=$(this).attr("data-path");
		console.info('url1--'+url);
		var projNo=$(this).attr("data-projNo");
		if(menuId!=''){
			var parentId=menuId.substring(0,2);//11
			if(url!=null){
				url=url.substring(1);
				console.info('url2--'+url);
			}
			console.info(parentId)
			if(parentId=="11"){
				crossPageOperate({
					mid: parentId,
					hash: url
				})
			}else if(parentId=="12" || parentId=="13"){
				console.info(2017, parentId)
				crossPageOperate({
					mid: parentId,
					projectNo:projNo,
					pageUrl: url
				})
			}
		}
	});
}


var historyBtnMonitor=function(){
	$(".historyBtn").off("click").on("click",function(){
		var projectType="12",corpId="1123";
		var data={"projectType":projectType,"corpId":corpId};
		
		/*$.ajax({
			type:'post',
			url:'todoNotice/updateHistoryOperateRecord',
	        data: data,
	        success:function(data){
	        	var content = "处理成功！";
	        	if(data=="false"){
	        		content = "处理失败！";
	        	}
	        	var myoptions = {
	                	title: "提示信息",
	                	content: content,
	                	accept: popClose,
	                	chide: true,
	                	icon: "fa-check-circle",
	                	newpop: 'new'
	                	
	            }
	            $("body").cgetPopup(myoptions);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            console.warn("数据保存失败！");
	        }
	        
		})*/
	})
}

var manageRecordBtnMonitor=function(){
	$(".manageRecordBtn").off("click").on("click",function(){
		var projectType="12",corpId="1123";
        var data={"projectType":projectType,"corpId":corpId};
		/*$.ajax({
			type:'post',
			url:'todoNotice/updateHistoryManagERecord',
	        data: data,
	        success:function(data){
	        	var content = "处理成功！";
	        	if(data=="false"){
	        		content = "处理失败！";
	        	}
	        	var myoptions = {
	                	title: "提示信息",
	                	content: content,
	                	accept: popClose,
	                	chide: true,
	                	icon: "fa-check-circle",
	                	newpop: 'new'
	                	
	            }
	            $("body").cgetPopup(myoptions);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            console.warn("数据保存失败！");
	        }
	        
		})*/
	})
}
/**
 * 初始化工程列表
 */
taskNotice = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){       
        		handleTaskNotice();
        	})
        }
    };
}();