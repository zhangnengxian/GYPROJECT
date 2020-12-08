var searchData={}; //查询条件
searchData.isPrint=$("#haveNotPrint").val();
/**
 * 加载工程列表
 */
var handleConnectConfirm = function() {
	"use strict";
    if ($('#connectConfirmTable').length !== 0) {
    	$('#connectConfirmTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面 
//   			$("#iframe-report-box").cgetcontent("connectconfirmprint/viewpage");
   			//隐藏遮罩
   			$('#connectConfirmTable').hideMask();
   			$("#connectConfirmTable_filter input").attr("placeholder","工程编号");
       
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
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
                url: 'connectConfirmPrint/queryFilingData',
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
                {"data":"fdId",className:"none never"},
				{"data":"projNo"},
				{"data":"projName"},
				{"data":"fdConnectDate"},
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
			}]
        });
    }
}

//初始化列表回调
var trSelectedBack = function(v, i, index, t, json){

	//加载打印预览
	var projNo = encodeURIComponent(cjkEncode(json.projNo)),
	projName = encodeURIComponent(cjkEncode(json.projName)),
	projId = encodeURIComponent(cjkEncode(json.projId)),
	projScaleDes = encodeURIComponent(cjkEncode(json.projScaleDes)),
	projAddr = encodeURIComponent(cjkEncode(json.projAddr)),
	fdCmo = encodeURIComponent(cjkEncode(json.fdCmo)),//施工单位
	fdConnectOpinion = encodeURIComponent(cjkEncode(json.fdConnectOpinion));//交接信息
	fdConnectDate = encodeURIComponent(cjkEncode(json.fdConnectDate)), // 交接日期
	fdFileNo = encodeURIComponent(cjkEncode(json.fdFileNo)),//工程档号
	fdId = encodeURIComponent(cjkEncode(json.fdId)),
	imgUrl = $(".imgUrl").val();
	src = cptPath+"/ReportServer?reportlet=complete/completionTransfer.cpt&projNo=" + projNo + "&projName="+ projName+"&fdId="+ fdId+"&projId="+ projId+"&projScaleDes="+projScaleDes+"&projAddr="+projAddr+"&fdCmo="+fdCmo+"&fdFileNo="+fdFileNo+"&fdConnectOpinion="+fdConnectOpinion+"&fdConnectDate="+fdConnectDate;
	src = src + "&imgUrl="+imgUrl;
	$("#mainFrm").attr("src",src);
}

/**
 * 弹屏监听方法
 */
searchMonitor = function(){
	$('.searchBtn').on('click',function(){
		var url = "#connectConfirmPrint/projectSearchPopPage";
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});

	// 基础条件查询添加监听
	$('#connectConfirmTable_filter input').on('change', function() {
		var projNo = $('#connectConfirmTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#connectConfirmTable').cgetData(true); // 列表重新加载
	});
	// 基础条件查询添加回车事件
	$('#connectConfirmTable_filter input').on('keyup', function(event) {
		if (event.keyCode == '13') {
			$(this).change();
		}
	});
},

/**
 * 查询弹出屏，点击确定后 
 */
searchDone = function(){
	searchData = $('#filingDataSearch').serializeJson();
	var projNo = $('#connectConfirmTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#connectConfirmTable').cgetData(true);  
};

/**
 * 初始化工程列表
 */
var connectConfirm = function () {
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

//标记监听
var signMonitor=function(){
	$('.signBtn').off().on('click',function(){
		var len=$("#connectConfirmTable").find('tr.selected').length;
		if(len>0){
           $("body").cgetPopup({
               	title: "提示信息",
               	content: "是否将选中的交接单标记为已打印？",
               	accept: sureDone,
               	chide: true,
               	newpop: 'new',
               	icon: "fa-check-circle"
           });
		}else{
			alertInfo('请选择要标记打印的交接单！');
		}
	});
}

var sureDone=function(){
	var projId=trSData.connectConfirmTable.json.projId;
	$.ajax({
		type:'post',
		url:'connectConfirmPrint/signConnectConfirmPrint',
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
	$("#connectConfirmTable").cgetData();
}