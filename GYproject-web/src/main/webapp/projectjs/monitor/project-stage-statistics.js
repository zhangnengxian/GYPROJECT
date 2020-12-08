
var searchData = {};
var dt;
var handleConnectGasAudit = function() {
	"use strict";
	console.info(searchData);
	// var proj_status_id=$("#status_0").val();
	// searchData.projStatusId=proj_status_id;
    if ($('#gasAuditTable').length !== 0) {
    	dt = $('#gasAuditTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#gasAuditTable').hideMask();
   			//$("#gasAuditTable_filter input").attr("placeholder","工程编号1");
   			//搜索监听
   	/*		//searchMonitor();
        }).on( 'draw.dt', function () { 
        	//auditBtnMonitor();
*/        }).DataTable({
        	language: language_CN,
            lengthMenu: [10],
            dom: 'trip',
            
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: '/projectStageStatistics/projectStageDetail',
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
				// {"data":"areaDes",responsivePriority:4},
				{"data":"projAddr",responsivePriority:5},
				{"data":"projScaleDes",responsivePriority:6},
				{"data":"acceptDate",responsivePriority:2},
				{"data":"projStatusDes",responsivePriority:7}
			],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			},
			columnDefs: [{
					"targets":1,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 15, 	//截取多少字符（或汉字）
						end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
			}]
        });
    }
};

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	/*$(".searchBtn").off("click").on("click",function(){
		var url = "#connectGasAudit/surveySearchPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#gasAuditTable_filter input").on("change",function(){
		searchData.projNo = $("#gasAuditTable_filter input").val();
		//传入false  则不选中行
		$("#gasAuditTable").cgetData(false, function(){
	   		//跳转到审核页面
			auditBtnMonitor();
		});  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#gasAuditTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });*/
	
	// for(var i = 0;i < 9;i ++){
		$(".circle").off('click').on('click',function(){
			//点击切换效果  以下不知道有啥用我给注释了-cui-2017-9-21
			// for(var j=0;j<9;j++){
			// 	$("#td_"+j).removeClass('changeBackground0_'+j);
			// 	$("#td_"+j).addClass('changeBackground'+j);
			// }
			// $("#"+this.id).removeClass('changeBackground'+this.id.substring(3));
			// $("#"+this.id).addClass('changeBackground0_'+this.id.substring(3))
			//更换背景图片
			searchData.projStatusId = $('#status_' + this.id.substring(3)).val();
			if($.fn.DataTable.isDataTable('#gasAuditTable')){
				$("#gasAuditTable").cgetData();
			}else{
				handleConnectGasAudit();
			}
		});
	// }
};

// var searchDone = function(){
// 	searchData = $("#searchForm_survey").serializeJson();
// 	searchData.projNo = $("#gasAuditTable_filter input").val();
//     $("#gasAuditTable").cgetData(false, function(){
//    		//跳转到审核页面
//     	auditBtnMonitor();
// 	});  //列表重新加载
// }

/**
 * 加载审核屏
 */
var auditBtnMonitor = function(){
	
	$(".level").off("click").on("click",function(){
		var isAudit = "0";//未审核过
		if($(this).is(".disabled")) return false;
		if($(this).is(".passed, .refused")){
			isAudit = "1";//已审核过
		}
		var surveyId = $(this).attr("data-rid");
		var currentLevel = $(this).attr("data-level");
		var data = {"surveyId":surveyId,"currentLevel":currentLevel,"isAudit":isAudit};
		$("#ajax-content").cgetContent("connectGasAudit/auditPage",data);
	});
	
};


/** 
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
}

/**
 * 初始化列表
 */
var connectGasAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("/js/ellipsis.js").done(function(){    
        		projectStageStatistics();
        	});
        }
    };
}();



var projectStageStatistics = function()
{
	"use strict";
	
	$.ajax({
	    type: 'POST',
	    url: '/projectStageStatistics/projectStageSum',
	    async: false,
	    contentType: "application/json;charset=UTF-8",
	    success: function (data) {
	    	var stageData = JSON.parse(data);
	    	$('#gasAuditTable').hideMask();
	    	$.each(stageData,function(index,obj){
	    		if(index.length > 4 && index.substring(0,4) == 'num_'){
	    			document.getElementById("lab" + index.substring(4,index.length)).innerHTML = '<font color="white">' + obj + '</font>';
	    		}
	    		
	    		if(index.length > 6 && index.substring(0,6) == 'where_'){
	    			document.getElementById("status_" + index.substring(6,index.length)).value = obj;
	    		}
	    	});
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	}); 

	searchMonitor();
	
};
