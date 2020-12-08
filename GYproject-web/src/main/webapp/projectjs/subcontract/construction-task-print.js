var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData = {};
searchData.isPrint=$("#haveNotPrint").val();
searchData.menuId="110509";
var menuId = '110509';
var cpId,projId,cpArriveDate;
//合同模板
var showReport = function(cpId){
    var loginName =encodeURIComponent(cjkEncode($("#loginName").val())),
        loginPhone = $("#loginPhone").val();
    	src = cptPath+"/ReportServer?reportlet=subContract/constructionTaskPrint.cpt&planId="+cpId+"&loginName=" + loginName+"&loginPhone=" + loginPhone;
    	$("#mainFrm").attr("src",src);
};
  //遍历版本，显示相应公司工程类型的版本
var selectRV = function(json){
  	//施工任务单cpt版本规则：分公司ID.菜单ID.版本ID
	var selectKeys = json.corpId+"."+menuId;
	var selectOprions = $("#contractVersion option");
	console.info("selectOprions======"+selectOprions);
	$.each(selectOprions,function(i,e){
		//分公司该工程类型合同版本
		if($(this).val().indexOf(selectKeys)>=0 || $(this).val()==null || $(this).val()==''){
			$(this).removeClass("hidden");
		}else{
			$(this).addClass("hidden");
		}
	});
	$("#contractVersion option:first").prop("selected", 'selected');
};
//ajax请求加载不同的报表
var queryCptUrl = function(json){

  	//根据工程名称，菜单名称，签订日期，公司ID获取报表----开始
	var param = {};
	param.projId = json.projId;
	param.menuId = menuId;
	param.signDate = json.cpArriveDate;
	param.rvId=$("#contractVersion").val();
	var loginName =encodeURIComponent(cjkEncode($("#loginName").val())),
    loginPhone = $("#loginPhone").val();
    var imgUrl="";
	$.ajax({
		type: 'POST',
		url: 'constructionTaskPrint/viewReportUrl',
		data: param,
      //dataType:'json',
      //contentType: "application/json;charset=UTF-8",
		success: function (cptUrl) {
			console.info("cptUrl ==== "+cptUrl);
      		if(cptUrl && cptUrl!='' && cptUrl !=null){
      			if(json.cpId!=''){
                    var src = cptPath+"/ReportServer?reportlet=subContract/"+cptUrl+"&planId="+json.cpId+"&loginName="+loginName+"&loginPhone="+loginPhone+"&imgUrl=/opt/gcglgas/upload/sign/"+"&projId="+json.projId;
                    console.log("src=="+src);
                    $("#mainFrm").attr("src",src);
      			}
      		}else{
          		showReport(json.cpId);
          	}
        }
  	});
};
  //版本选择事件
$("#contractVersion").on("change",function(){
  	var json={};
  	json.cpId=cpId;
  	json.cpArriveDate = cpArriveDate;;
  	json.projId = projId;
  	queryCptUrl(json);
});

var initCpt = function(){
	var json={};
	json.cpId="";
  	json.cpArriveDate = "";
  	json.projId = "";
  	queryCptUrl(json);
};
var handleContractPrint = function(){
	"use strict";
	 if ($('#constructionTaskPrintTable').length !== 0) {
		 $('#constructionTaskPrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#constructionTaskPrintTable').hideMask();
   			$("#constructionTaskPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
             //标记监听
			signMonitor();
			setTimeout(function(){
   			    $("#constructionTaskPrintTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
			initCpt();
		 }).DataTable({
			language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            bStateSave:true,
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
				{ text: '标记', className: 'btn-sm btn-query signBtn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
			ajax: {
                url: 'constructionTaskPrint/queryConstructionPlan',
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
                {"data":"cpId",className:"none never"},
                {"data":"projNo"},
                {"data":"projName"},
                {"data":"cpArriveDate"},
                {"data":"projStatusDes"},
				{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}
      		],
             columnDefs: [{
                 "targets": 0,
                 "visible":false
             },{
                 "targets":2,
                 //长字符串截取方法
                 render: $.fn.dataTable.render.ellipsis({
                     length: showLength, 	//截取多少字符（或汉字）
                     end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                 })
             }]
		 })
	 }
}
/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#constructionTaskPrint/projectSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#constructionTaskPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#constructionTaskPrintTable_filter input").val();
		searchData.menuId=menuId;
		//传入false  则不选中行
		$("#constructionTaskPrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#constructionTaskPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
var searchDone = function(){
	searchData = $("#searchForm_plan").serializeJson();
	searchData.projNo = $("#constructionTaskPrintTable_filter input").val();
	searchData.menuId=menuId;
	//列表重新加载
    $("#constructionTaskPrintTable").cgetData(true);
}

var tableCallBack = function(){
		// src = cptPath+"/ReportServer?reportlet=subContract/constructionTaskPrint.cpt"
		// $("#mainFrm").attr("src",src);
}
//标记监听
var signMonitor=function(){
    $('.signBtn').off().on('click',function(){
        var len=$("#constructionTaskPrintTable").find('tr.selected').length;
        if(len>0){
            $("body").cgetPopup({
                title: "提示信息",
                content: "是否标记为已打印？",
                accept: sureDone,
                chide: true,
                newpop: 'new',
                icon: "fa-check-circle"
            });
        }else{
            alertInfo('请选择要标记打印的记录！');
        }
    });
}

var sureDone=function(){
    var projId=trSData.constructionTaskPrintTable.json.projId;
    $.ajax({
        type:'post',
        url:'constructionTaskPrint/signConstructionTaskPrint',
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
    searchData.menuId=menuId;
    $("#constructionTaskPrintTable").cgetData();
}

var trSelectedBack = function(v, i, index, t, json){
	cpId = json.cpId;
	projId = json.projId;
	cpArriveDate = json.cpArriveDate;
    //根据当前工程去后台查询施工任务单cpt
    //选择版本
	selectRV(json);
	//后台获取cpt
	queryCptUrl(json);
}
/**
 * 初始化列表
 */
var constructionTaskPrint = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handleContractPrint();
        	});
        }
    };
}();