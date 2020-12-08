var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData = {};
var menuId = "110311";
searchData.menuId = menuId;
searchData.isprint=$("#haveNotPrint").val();
var handleBudgetPrint = function(){
	"use strict";
	 if ($('#budgetPrintTable').length !== 0) {
		 $('#budgetPrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#budgetPrintTable').hideMask();
   			$("#budgetPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
             //标记监听
			signMonitor();
			setTimeout(function(){
   			    $("#budgetPrintTable").DataTable().columns.adjust();
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
                url: 'projectBudgetPrint/queryBudget',
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
  				{"data":"budgetId",className:"none never"},
 				{"data":"projNo"},
 				{"data":"projName"},
 				{"data":"budgetTotalCost"}
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
		var url = "#projectBudgetPrint/projectBudgetSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	$("#budgetPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#budgetPrintTable_filter input").val();
		searchData.menuId = menuId;
		//传入false  则不选中行
		$("#budgetPrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#budgetPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
var searchDone = function(){
	searchData = $("#searchBudgetPop").serializeJson();
	searchData.projNo = $("#budgetPrintTable_filter input").val();
	searchData.menuId = menuId;
	//列表重新加载
    $("#budgetPrintTable").cgetData(true, tableCallBack);  
}

var tableCallBack = function(){
	var len = $('#budgetPrintTable').DataTable().ajax.json().data ? $('#budgetPrintTable').DataTable().ajax.json().data.length : $('#budgetPrintTable').DataTable().ajax.json().length;
	if(len<1){
		var src1 = cptPath+"/ReportServer?reportlet=budget/projectBudgetPrint.cpt";
	    $("#mainFrm").attr("src",src1);
	 }else{
		 
	 }
}
//标记监听
var signMonitor=function(){
    $('.signBtn').off().on('click',function(){
        var len=$("#budgetPrintTable").find('tr.selected').length;
        if(len>0){
            $("body").cgetPopup({
                title: "提示信息",
                content: "是否将选中的预算标记为已打印？",
                accept: sureDone,
                chide: true,
                newpop: 'new',
                icon: "fa-check-circle"
            });
        }else{
            alertInfo('请选择要标记打印的预算记录！');
        }
    });
}

var sureDone=function(){
    var budgetId=trSData.budgetPrintTable.json.budgetId;
    $.ajax({
        type:'post',
        url:'projectBudgetPrint/signProjectBudgetPrint',
        contentType: "application/json;charset=UTF-8",
        data: budgetId,
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
    $("#budgetPrintTable").cgetData(true);
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
        url: 'projectBudgetPrint/viewBudgetReport',
        data: param,
        success: function (cptUrl) {
        	console.info("cptUrl ==== "+cptUrl);
        	if(cptUrl!='' && cptUrl !=null){
        		var src = cptPath+"/ReportServer?reportlet=budget/"+cptUrl+"&budgetId="+json.budgetId+"&projId="+json.projId+"&imgUrl="+$(".imgUrl").val();
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
var budgetPrint = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handleBudgetPrint();
        	});
        }
    };
}();