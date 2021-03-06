var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData = {};
searchData.isPrint = "1";  //默认加载未打印的
var cptType = '';  //声明cpt类型
var handledivisionalAcceptanceApplyList = function(){
	"use strict";
	 if ($('#divisionalAcceptanceApplyPrintTable').length !== 0) {
		 $('#divisionalAcceptanceApplyPrintTable').on( 'init.dt',function(){
			 var len = $('#divisionalAcceptanceApplyPrintTable').DataTable().ajax.json().data ? $('#divisionalAcceptanceApplyPrintTable').DataTable().ajax.json().data.length : $('#divisionalAcceptanceApplyPrintTable').DataTable().ajax.json().length;
	    		if(len<1){     //默认加载每一个公司下的第一种类型cpt
	    			 var json = {} ;
	            	 json.corpId = '';
	            	 json.menuId = '1107201'; 
	            	 json.projectType = '';
	            	 queryCptType(json);   //默认加载不同公司下的第一个cpt 
	    		}
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#divisionalAcceptanceApplyPrintTable').hideMask();
   			//基础查询条件
   			$("#divisionalAcceptanceApplyPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   			//标记监听
   			signMonitor();
   		    showReport();
   			setTimeout(function(){
   			    $("#divisionalAcceptanceApplyPrintTable").DataTable().columns.adjust();
   			}, 0);
		 }).DataTable({
			language: language_CN,
            lengthMenu: [18],
           // dom: 'Bfrtip',
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '标记', className: 'btn-sm btn-query signBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
			ajax: {
                url: 'divisionalAcceptanceApplyPrint/queryDivisionalAcceptanceApply',
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
  				{"data":"daaId",className:"none never"},
  				{"data":"projNo"},
  				{"data":"projName"},
  				{"data":"applyDate"},
      		],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 },{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: showLength, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				}
			 ]
		 });
	 }
}
//初始化列表回调
var trSelectedBack = function(v, i, index, t, json){
	$("#daaId").val(json.daaId);
	$("#projId").val(json.projId);
	showReport();
	queryCptType(json);
}
//根据公司ID、工程类型、菜单类型查找cpt类型--开始
var queryCptType = function(json){
	var daaId = encodeURIComponent(cjkEncode(json.daaId));    //解决中文乱码
	var projId = encodeURIComponent(cjkEncode(json.projId));    //解决中文乱码
	var param = {} ;  //传递参数
	var menuId = getStepId();
	if(menuId == null || menuId == ''){   //判断菜单ID是否为空
		menuId = "1107201";
	}
	param.corpId = json.corpId;  
	param.projectType = json.projectType;
	param.menuId = menuId ;
	$.ajax({
		type:'POST',
		url:'divisionalAcceptanceApplyPrint/queryCptType',
	    data:param,
	    success:function(cptUrl){
	    	if(cptUrl != "undefinde"){
	    		cptType = cptUrl;
	    	var	src = cptPath+"/ReportServer?reportlet=complete/"+cptType+"&daaId=" + daaId + "&projId=" +projId+"&imgUrl="+$(".imgUrl").val();
	    	    $('#mainFrm').hideMask();   //隐藏罩
	            $("#mainFrm").attr("src",src);
	    	}else{
	    	     $("body").cgetPopup({
	                	title: "提示信息",
	                	content: "该公司没有相关报表被配置，请联系管理员进行配置？系统因找不到指定文件，显示默认报表!",
	                	accept: confirmFunction,
	                	chide: true,
	                	newpop: 'new',
	                	icon: "fa-check-circle"
	            });
	    	}
	    }
	})
	  
}
var confirmFunction = function(){
	cptType = "guianDivisionalAcceptanceApply.cpt";
	showReport();
}
//根据公司ID、工程类型、菜单类型查找cpt类型--结束
//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#divisionalAcceptanceApplyPrint/researchPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#divisionalAcceptanceApplyPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#divisionalAcceptanceApplyPrintTable_filter input").val();
		$("#divisionalAcceptanceApplyPrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#divisionalAcceptanceApplyPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
//弹屏确定回调
var searchDone = function(){
	searchData = $("#searchAcceptanceApplyPrintPop").serializeJson();
	searchData.projNo = $("#divisionalAcceptanceApplyPrintTable_filter input").val();
	$("#divisionalAcceptanceApplyPrintTable").parent().parent().loadMask("加载中····",1,0.5);  //加载摘罩
	//列表重新加载
    $("#divisionalAcceptanceApplyPrintTable").cgetData(true, tableCallBack);  
}
//列表重加载回调
var tableCallBack = function(){
	$("#divisionalAcceptanceApplyPrintTable").parent().parent().hideMask();  //隐藏摘罩
	var len = $('#divisionalAcceptanceApplyPrintTable').DataTable().ajax.json().data ? $('#divisionalAcceptanceApplyPrintTable').DataTable().ajax.json().data.length : $('#divisionalAcceptanceApplyPrintTable').DataTable().ajax.json().length;
	if(len<1){
		$("#daaId").val("-1");
		showReport();
	 }else{
	 }
}
//初始化列表
var divisionalAcceptanceApplyList = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handledivisionalAcceptanceApplyList();
        	});
        }
    };
}();

//标记监听
var signMonitor=function(){
	$('.signBtn').off().on('click',function(){
		var len=$("#divisionalAcceptanceApplyPrintTable").find('tr.selected').length;
		if(len>0){
           $("body").cgetPopup({
               	title: "提示信息",
               	content: "是否将选中的记录标记为已打印？",
               	accept: sureDone,
               	chide: true,
               	newpop: 'new',
               	icon: "fa-check-circle"
           });
		}else{
			alertInfo('请选择记录！');
		}
	});
}

var sureDone=function(){
	var daaId=$("#daaId").val();
	$.ajax({
		type:'post',
		url:'divisionalAcceptanceApplyPrint/signDivisionalAcceptancePrint',
        data: {daaId:daaId},
        dateType:"JSON",
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
	$("#divisionalAcceptanceApplyPrintTable").cgetData();
}