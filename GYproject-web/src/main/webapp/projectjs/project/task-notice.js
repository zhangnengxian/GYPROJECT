var workNoticeTable,disNoticeTable,noticeWorkData={};
var nameLength,signLength;
if(_inApk){
	nameLength=8;
	signLength=8;
}else{
	nameLength=18;
	signLength=18;
}


//工作通知
var handleTaskNotice = function() {
	"use strict";
	//初始化table
	if($.fn.DataTable.isDataTable('#workNoticeTable')){
		$("#workNoticeTable").cgetData(true,queryWorkMonitorAgain);//列表重新加载
	}else{
		workNoticeTableInit();
	}
};


//工作通知
$("#workNoticeTab").on("shown.bs.tab",function(){
	if($.fn.DataTable.isDataTable('#workNoticeTable')){
		$("#workNoticeTable").cgetData(true,queryWorkMonitorAgain);//列表重新加载
	}else{
		workNoticeTableInit();
	}
});

var queryWorkMonitorAgain=function(){
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



//签字通知
$("#signNoticeTab").on("shown.bs.tab",function(){
	if($.fn.DataTable.isDataTable('#signNoticeTable')){
		$("#signNoticeTable").cgetData(true,querySignMonitorAgain);//列表重新加载
	}else{
		signNoticeTableInit();
	}
});

var querySignMonitorAgain=function(){
	$(".Search_Button1").on("click",function(){
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


//施工任务通知
$("#conTaskNoticeTab").on("shown.bs.tab",function(){
	if($.fn.DataTable.isDataTable('#disNoticeTable')){
		$("#disNoticeTable").cgetData(true);//列表重新加载
	}else{
		disNoticeTableInit();
	}
});

function testCross () {
	//mid:要跳转的菜单的父id，如工程施工是12
	//projectNo:工程编号
	//要跳转的页面的菜单id 如签证记录的id是120206
	crossPageOperate({
		mid: '12',
		projectNo: '52020318090050',
		pageUrl: 'engineering/main'
	})
}



//初始化工作通知列表
var workNoticeTableInit= function() {
	"use strict";
    if ($('#workNoticeTable').length !== 0) {
    	workNoticeTable= $('#workNoticeTable').on( 'init.dt',function(){
    		$('#workNoticeTable').hideMask();
    		
    		//跳转
    		queryWorkMonitor();
    	}).on( 'draw.dt', function () {
    		queryWorkMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [10],
            dom: 'Brtip',
            bStateSave:true,
            buttons: [],
            //ajax: 'projectjs/accept/json/fulw.json',
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
					{"data":"stepName", responsivePriority: 3},
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
				targets: 3,
				render: function (data, type, row, meta) {
					var stepName=(data=="" || data==null)?row.stepName:data;
					if (stepName!=null && stepName.indexOf('(回退)')!=-1){
						return stepName+'-><a style="cursor:pointer" onclick="showBackRecord(\''+row.projId+'\')">回退记录查看</a>'
					}else {
						return stepName;
					}
				}
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
						var  tdcon='<a class="m-l-5 Search_Button btn btn-primary btn-xs" data-projNo="'+row.projNo+'" data-id="'+row.menuId+'" onclick="toTargetClick(\''+row.businessOrderId+'\',\''+row.projNo+'\')" data-path="'+data+'">查看</a>';
                        return tdcon;
					}else{
						return data;
					}
				}
			},{
				"targets": 7,
			    "visible":false
			},{
				targets: 3,
				render: function (data, type, row, meta) {
					console.info(2018,row.menuId);
					
					if(type === "display"){
						if(row.menuId=="120213"){
							data="业务沟通";
							return data;
						}else{
							return data;
						}
					}else{
						return data;
					}
				}
			}],
			ordering:false
       });
   }
};



/**
 *工程编号搜索
 */
function projNoSearch (value,event) {
	for(var key in noticeWorkData){//清空查询条件赋予对象值
		delete noticeWorkData[key];
	}
	noticeWorkData.projNo=value;
	$("#workNoticeTable").cgetData(true);
}


function showBackRecord(projId) {
	var param="?projId="+projId;
	$('body').cgetPopup({//加载弹屏
		title: '回退记录',
		content: '#dataTableInfo/abandonedRecordPopPageDetail'+param,
		accept: function () {},
		size:'large'
	});
}


function toTargetClick(data,projNo) {
	Base.subimt('taskNotice/saveProjNoSession','POST',{"projNo":projNo,businessOrderId:data},function (data) {
		window.setTimeout(function () {//五秒后清空
			Base.subimt('taskNotice/saveProjNoSession','POST',{"projNo":null,businessOrderId:null},function (data) {});
		},3000);
	});
}



//初始化审核通知列表
var signNoticeTableInit= function() {
	"use strict";
    if ($('#signNoticeTable').length !== 0) {
    	workNoticeTable= $('#signNoticeTable').on( 'init.dt',function(){
    		$('#signNoticeTable').hideMask();
            $(this).bindDTSelected(trSelectedBackSign,true);
    		querySignMonitor();
    	}).on( 'draw.dt', function () {
    		querySignMonitor();
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
            	url: 'taskNotice/querySignNotice',
            	type:'post',
            	data: function(d){
                  	$.each(noticeWorkData,function(i,k){
                  		d[i] = k;
                  	});
                  	
            	},
            	dataSrc: 'data'
            },
            select: true,  //支持多选
            //ajax: 'projectjs/accept/json/proj_scale.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },//自适应
            columns: [
                {"data":"signNoticeId"},
	  			{"data":"projNo", responsivePriority: 1},
	  			{"data":"projName", responsivePriority: 5},
	  			{"data":"postName", responsivePriority: 4},
	  			{"data":"dataTypeName", responsivePriority: 3},
	  			{"data":"url", responsivePriority: 2},
				{"data":"menuId", responsivePriority: 6}
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
			},{
				targets: 5,
				render: function (data, type, row, meta) {
					if(type === "display"){
						var  tdcon='<a class="m-l-5 Search_Button1 btn btn-primary btn-xs" data-projNo="'+row.projNo+'" data-id="'+row.menuId+'" onclick="toTargetClick(\''+data+'\',\''+row.projNo+'\')" data-path="'+data+'">查看</a>';
                        return tdcon;
					}else{
						return data;
					}
				}
			},{
				"targets": 6,
			    "visible":false
			}],
			ordering:false
       });
   }
};


var trSelectedBackSign=function(v, i, index, t, json){
	console.info("通知id:---"+json.signNoticeId);

}
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

//签字通知
var querySignMonitor=function(){
	$(".Search_Button1").on("click",function(){
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



//初始化工作通知列表
var disNoticeTableInit= function() {
	"use strict";
    if ($('#disNoticeTable').length !== 0) {
    	disNoticeTable= $('#disNoticeTable').on( 'init.dt',function(){
    		$('#disNoticeTable').hideMask();
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
            	url: 'notice/queryDispatchNotice',
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
                {"data":"noticeId"},       
	  			{"data":"signType"},
	  			{"data":"noticeContent"},
	  			{"data":"noticeState"}/*,
	  			{"data":"url", responsivePriority: 4}     */
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
			}/*,{
				targets: 3,
				render: function (data, type, row, meta) {
					console.info("data--"+data);
					if(type === "display"){
						var  tdcon='<a class="m-l-5 Search_Button btn btn-primary btn-xs" data-id="'+data+'" data-path="'+data+'"><i class="fa fa-eye"></i>查看</a>';
						return tdcon;
					}else{
						return data;
					}
				}
			}*/],
			ordering:false
       });
   }
};


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