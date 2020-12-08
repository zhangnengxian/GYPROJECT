var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var settlementAuditPrintTable;
var searchData = {};
var menuId = '110804';
searchData.menuId=menuId;
searchData.auditState=1;//审核通过的
var handleSettlementAuditPrint = function(){
	"use strict";
	 if ($('#settlementAuditPrintTable').length !== 0) {
		 settlementAuditPrintTable = $('#settlementAuditPrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#settlementAuditPrintTable').hideMask();
   			//基础查询条件
   			$("#settlementAuditPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   			SETT.showReport();//按照之前的加载
   		    //标记监听
   			signMonitor();
   			setTimeout(function(){
   			    $("#settlementAuditPrintTable").DataTable().columns.adjust();
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
                url: 'settlementAuditPrint/querySettlementAudit',
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
  				{"data":"sdId",className:"none never"},
  				{"data":"projNo"},
  				{"data":"projName"},
  				{"data":"sendDeclarationCost",className:'text-right'},
  				{"data":"firstDeclarationCost",className:'text-right'},
  				{"data":"endDeclarationCost",className:'text-right'},
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
				},{
				 'targets':3,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							return parseFloat(data).toFixed(2);
						}else{
							return data;
						}
					},
			 	},{
					 'targets':4,
					 "render": function ( data, type, row, meta ) {
							if(data!=="" && data!==null){
								return parseFloat(data).toFixed(2);
							}else{
								return data;
							}
						},
				 	},{
						 'targets':5,
						 "render": function ( data, type, row, meta ) {
								if(data!=="" && data!==null){
									return parseFloat(data).toFixed(2);
								}else{
									return data;
								}
							},
					 	}]
		 });
	 }
}

var trSelectedJson;
//初始化列表回调
var trSelectedBack = function(v, i, index, t, json){
	trSelectedJson=json;
	loadReportVersion(json);
	loadReport(json);
}



function loadReportVersion(json) {
	Base.subimt("reportVersion/loadReportVersion","POST",{projId:json.projId,menuId:110804},function (data) {
		$("#reportVersion").find("option").remove();//加载时先清空option;还可以使用$("#level3Menu").empty();方法清空
		$.each(JSON.parse(data), function(n, v) {
			$("#reportVersion").append('<option value='+v.rvId+'>' + v.rvName+ '</option>');
		});
	});
}

function selectReportVersion(value){
	trSelectedJson.rvId=value;
	loadReport(trSelectedJson);
}



function loadReport(json) {
	var param = {};
	param.projId = json.projId;
	param.menuId = menuId;
	param.rvId=json.rvId;
	var legalAmount = encodeURIComponent(cjkEncode(json.legalAmount));
	Base.subimt("settlementAuditPrint/viewSettlementReport","POST",param,function (cptUrl) {
		if(cptUrl!='' && cptUrl !=null){
			var src = cptPath+"/ReportServer?reportlet=settlement/"+cptUrl+"&sdId="+json.sdId+"&projId="+json.projId+"&legalAmount="+legalAmount+ "&imgUrl=" + $(".imgUrl").val();
			$("#mainFrm").attr("src",src);
		}else{
			SETT.showReport();//按照之前的加载
		}
	});
}





//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#settlementAuditPrint/settlementSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#settlementAuditPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#settlementAuditPrintTable_filter input").val();
		searchData.menuId=menuId;
		$("#settlementAuditPrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#settlementAuditPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
//弹屏确定回调
var searchDone = function(){
	searchData = $("#searchSettlementPop").serializeJson();
	searchData.projNo = $("#settlementAuditPrintTable_filter input").val();
	searchData.menuId=menuId;
	console.info(searchData);
	//列表重新加载
    $("#settlementAuditPrintTable").cgetData(true, tableCallBack);  
}
//列表重加载回调
var tableCallBack = function(){
	var len = $('#settlementAuditPrintTable').DataTable().ajax.json().data ? $('#settlementAuditPrintTable').DataTable().ajax.json().data.length : $('#settlementAuditPrintTable').DataTable().ajax.json().length;
	loadReport(trSelectedJson);
	
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
		var len=$("#settlementAuditPrintTable").find('tr.selected').length;
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
	var projId=trSData.settlementAuditPrintTable.json.projId;
	$.ajax({
		type:'post',
		url:'settlementAuditPrint/signSettlementAuditPrint',
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
	$("#settlementAuditPrintTable").cgetData();
}