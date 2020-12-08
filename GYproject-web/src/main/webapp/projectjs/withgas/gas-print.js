var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData = {};
var menuId = "111209";
searchData.menuId = menuId;
searchData.isprint=$("#haveNotPrint").val();
var handlegasPrint = function(){
	"use strict";
	 if ($('#gasPrintTable').length !== 0) {
		 $('#gasPrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#gasPrintTable').hideMask();
   			$("#gasPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
             //标记监听
			signMonitor();
			setTimeout(function(){
   			    $("#gasPrintTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
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
                url: 'gasPrint/queryGasProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
			 //ajax:'projectjs/budget/json/budget_print.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
  				{"data":"gprojId",className:"none never"},
 				{"data":"projNo"},
 				{"data":"projName"},
 				{"data":"gasProjStatusDes"}
      		],
			 columnDefs: [{
   				"targets":0,
  				"visible":false
  				},{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: showLength, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},{
					"targets":3,
					 "orderable":false
				}]

		 })
	 }
}
/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#gasPrint/gasPrintSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	$("#gasPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#gasPrintTable_filter input").val();
		searchData.menuId = menuId;
		//传入false  则不选中行
		$("#gasPrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#gasPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
var searchDone = function(){
	searchData = $("#searchForm_gasProject").serializeJson();
	searchData.projNo = $("#gasPrintTable_filter input").val();
	searchData.menuId = menuId;
	//列表重新加载
    $("#gasPrintTable").cgetData(true, tableCallBack);  
}

var tableCallBack = function(){
	var len = $('#gasPrintTable').DataTable().ajax.json().data ? $('#gasPrintTable').DataTable().ajax.json().data.length : $('#gasPrintTable').DataTable().ajax.json().length;
	if(len<1){
		var src1 = cptPath+"/ReportServer?reportlet=withgas/anshun_gasPrint.cpt";
	    $("#mainFrm").attr("src",src1);
	 }else{
		 
	 }
}
//标记监听
var signMonitor=function(){
    $('.signBtn').off().on('click',function(){
        var len=$("#gasPrintTable").find('tr.selected').length;
        if(len>0){
            $("body").cgetPopup({
                title: "提示信息",
                content: "是否将选中的通气单标记为已打印？",
                accept: sureDone,
                chide: true,
                newpop: 'new',
                icon: "fa-check-circle"
            });
        }else{
            alertInfo('请选择要标记打印的通气单！');
        }
    });
}

var sureDone=function(){
    var gprojId=trSData.gasPrintTable.json.gprojId;
    $.ajax({
        type:'post',
        url:'gasPrint/signGasPrint',
        contentType: "application/json;charset=UTF-8",
        data: gprojId,
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
    searchData.isprint=$("#haveNotPrint").val();
    $("#gasPrintTable").cgetData(true);
}
var trSelectedBack = function(v, i, index, t, json){
	//通过后台获取施工预算书报表模板
	//ajax请求加载不同的报表
	var param = {};
	param.projId = json.projId;
	param.menuId = menuId;
	//param.signDate = json.signDate;
	$.ajax({
        type: 'POST',
        url: 'gasPrint/viewGasPrintReport',
        data: param,
        success: function (cptUrl) {
        	console.info("cptUrl ==== "+cptUrl);
        	if(cptUrl!='' && cptUrl !=null){
        		var src = cptPath+"/ReportServer?reportlet=withgas/"+cptUrl+"&gprojId="+json.gprojId+"&projId="+json.projId+"&imgUrl="+$(".imgUrl").val();
       	 		$("#mainFrm").attr("src",src);
        	}else{
        		//按照之前的加载
        		showReport(json);
        	}
        }
	});
}
/**
 * 初始化列表
 */
var gasPrint = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handlegasPrint();
        	});
        }
    };
}();