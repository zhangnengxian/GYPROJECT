var searchData = {};
searchData.isPrint=$("#haveNotPrint").val();
searchData.menuId="110404";
var projName,conId,payType,legalAmount='',legalFirstPayment='',legalSecondPaymentAmount='',legalThirdPaymentAmount='',legalIncrementAmount='',signDate,projId,legalBudgetTotalCost='',legalHoseAmount='';
var legalFourPaymentAmount="";
var handleContractPrint = function(){
	"use strict";
	 if ($('#contractPrintTable').length !== 0) {
		 $('#contractPrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//$(".contract_print_box").cgetContent("contractPrint/viewPage",'',callBack);
   			//隐藏遮罩
   			$('#contractPrintTable').hideMask();
   			$("#contractPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   			//修改监听
   			updateMonitor();
   			//打印监听
   			printMonitor();
   			//标记监听
   			signMonitor();
   			setTimeout(function(){
   			    $("#contractPrintTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
   			//initCpt();
		 }).DataTable({
			language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '标记', className: 'btn-sm btn-query signBtn' },
                //{ text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                /*{ text: '打印', className: 'btn-sm btn-query printBtn' }*/
               
            ],
          //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
			ajax: {
                url: 'contractPrint/queryContract',
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
      				{"data":"conNo"},
      				{"data":"projName"},
      				{"data":"signDate"},
      				{"data":"contractAmount",className:"fixed-number text-right"},
    				{"data":"isSpecialSign",className:"none never",
    					"createdCell": function (td, cellData, rowData, row, col) {
    						if(cellData=='1'){
    							$(td).parent().children().css("color", "rgb(255, 99, 95)");
    						}
    					}
      				}
      			],
      			columnDefs: [{
      				"targets":0,
      				"visible":false
      			},{
    				"targets":1,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: 15, 	//截取多少字符（或汉字）
    					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    				})
  			},{
    				"targets":2,
	    				//长字符串截取方法
	    				render: $.fn.dataTable.render.ellipsis({
	    					length: 8, 	//截取多少字符（或汉字）
	    					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
	    				})
      			},{
	   				 'targets':4,
					 "render": function ( data, type, row, meta ) {
							if(data!=="" && data!==null){
								return parseFloat(data).toFixed(2);
							}else{
								return data;
							}
						},
				 	}
      			]
		 })
	 }
};

function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}


var showReport1 = function(projName,conId,legalAmount,legalFirstPayment,legalSecondPaymentAmount,projId,payType,legalBudgetTotalCost){
	 src = cptPath+"/ReportServer?reportlet=contract/constructCivil.cpt&projName="+projName+"&conId="+conId+"&legalAmount="+legalAmount+"&legalFirstPayment="+legalFirstPayment+"&legalSecondPaymentAmount="+legalSecondPaymentAmount+"&projId="+projId+"&payType="+payType+"&imgUrl = "+$(".imgUrl").val() +"&legalBudgetTotalCost="+legalBudgetTotalCost;
	 $("#mainFrm").attr("src",src);
}
var showReport2 = function(projName,conId,legalAmount,legalFirstPayment,legalSecondPaymentAmount,legalThirdPaymentAmount,projId,payType,legalBudgetTotalCost){
	 src = cptPath+"/ReportServer?reportlet=contract/constructPublic.cpt&projName="+projName+"&conId="+conId+"&legalAmount="+legalAmount+"&legalFirstPayment="+legalFirstPayment+"&legalSecondPaymentAmount="+legalSecondPaymentAmount+"&legalThirdPaymentAmount="+legalThirdPaymentAmount+"&projId="+projId+"&payType="+payType+"&imgUrl = "+$(".imgUrl").val()+"&legalBudgetTotalCost="+legalBudgetTotalCost;
	 $("#mainFrm").attr("src",src);
}
var showReport3 = function(projName,conId,legalAmount,legalFirstPayment,legalSecondPaymentAmount,projId,payType,legalBudgetTotalCost){
	 src = cptPath+"/ReportServer?reportlet=contract/constructPipe.cpt&projName="+projName+"&conId="+conId+"&legalAmount="+legalAmount+"&legalFirstPayment="+legalFirstPayment+"&legalSecondPaymentAmount="+legalSecondPaymentAmount+"&projId="+projId+"&payType="+payType+"&imgUrl = "+$(".imgUrl").val()+"&legalBudgetTotalCost="+legalBudgetTotalCost;
	 $("#mainFrm").attr("src",src);
}
var oldshowReport = function(json){
	if(json.contractType=='11'){
		showReport1(projName,conId,legalAmount,legalFirstPayment,legalSecondPaymentAmount,projId,payType,legalBudgetTotalCost);
	}else if(json.contractType=='21'){
		showReport2(projName,conId,legalAmount,legalFirstPayment,legalSecondPaymentAmount,legalThirdPaymentAmount,projId,payType,legalBudgetTotalCost);
	}else{
		showReport3(projName,conId,legalAmount,legalFirstPayment,legalSecondPaymentAmount,projId,payType,legalBudgetTotalCost);
	}
}




var trSelectedBack = function(v, i, index, t, json){
	console.info(json.contractType);
	console.info(json);
	//t.cgetDetail('contractDetailform','contractPrint/viewContract','',contractPrintBack);
	//加载打印预览
	projName = encodeURIComponent(cjkEncode(json.projName));
	conId = encodeURIComponent(cjkEncode(json.conId));
	projId = encodeURIComponent(cjkEncode(json.projId));
	payType = encodeURIComponent(cjkEncode(json.payType));
	legalAmount = encodeURIComponent(cjkEncode(json.legalAmount));
	legalFirstPayment = encodeURIComponent(cjkEncode(json.legalFirstPayment));
	legalSecondPaymentAmount = encodeURIComponent(cjkEncode(json.legalSecondPaymentAmount));
	legalThirdPaymentAmount = encodeURIComponent(cjkEncode(json.legalThirdPaymentAmount));
	legalIncrementAmount = encodeURIComponent(cjkEncode(json.legalIncrementAmount));
	legalFourPaymentAmount = encodeURIComponent(cjkEncode(json.legalFourPaymentAmount));
	legalBudgetTotalCost = encodeURIComponent(cjkEncode(json.legalBudgetTotalCost));
	legalHoseAmount = encodeURIComponent(cjkEncode(json.legalHoseAmount));
	selectRV(json);
	//加载cpt
	queryCptUrl(json);
	
	/*if(json.contractType=='11'){
		showReport1(projName,conId,legalAmount,legalFirstPayment,legalSecondPaymentAmount,projId,payType);
	}else if(json.contractType=='21'){
		showReport2(projName,conId,legalAmount,legalFirstPayment,legalSecondPaymentAmount,legalThirdPaymentAmount,projId,payType);
	}else{
		showReport3(projName,conId,legalAmount,legalFirstPayment,legalSecondPaymentAmount,projId,payType);
	}*/
//	 totalDays;
//	if(json.plannedEndDate){
//		totalDays=DateDiff(plannedStartDate.substr(0,10),plannedEndDate.substr(0,10)).toString();
//		console.info(plannedStartDate);
//		console.info(plannedEndDate);
//		console.info(totalDays);
//	}else{
//		totalDays=encodeURIComponent(cjkEncode("配合"));
//	}
}
//遍历版本，显示相应公司工程类型的版本
var selectRV = function(json){
	
	var selectKeys = json.projectType+"."+json.corpId+"."+getStepId();
	var selectOprions = $("#contractVersion option");
	$.each(selectOprions,function(i,e){
		//分公司该工程类型合同版本
		if($(this).val().indexOf(selectKeys)>=0 || $(this).val()==null || $(this).val()==''){
			$(this).removeClass("hidden");
		}else{
			$(this).addClass("hidden");
		}
	});
	$("#contractVersion option:first").prop("selected", 'selected');
}

var queryCptUrl = function(json){
	//var cptParam="&legalHoseAmount="+json.legalHoseAmount+"&legalContractAmount="+json.legalContractAmount+"&legalBudgetCost="+json.legalBudgetCost;

	//ajax请求加载不同的报表
	//根据工程名称，菜单名称，签订日期，公司ID获取报表----开始
	var param = {};
	param.projId = json.projId;
	param.menuId = getStepId();
	param.signDate = json.signDate;
	param.rvId=$("#contractVersion").val();
	$.ajax({
        type: 'POST',
        url: 'contractPrint/viewContractReport',
        data: param,
        //dataType:'json',
        //contentType: "application/json;charset=UTF-8",
        success: function (cptUrl) {
        	console.info("cptUrl ==== "+cptUrl);
        	if(cptUrl!='' && cptUrl !=null){
        		var src = cptPath+"/ReportServer?reportlet=contract/"+cptUrl+"&projName="+projName+"&conId="+conId+"&legalAmount="+legalAmount+"&legalFirstPayment="+legalFirstPayment+"&legalSecondPaymentAmount="+legalSecondPaymentAmount+"&legalThirdPaymentAmount="+legalThirdPaymentAmount+"&legalFourPaymentAmount="+legalFourPaymentAmount+"&projId="+projId+"&payType="+payType+"&imgUrl="+$(".imgUrl").val()+"&legalIncrementAmount="+legalIncrementAmount+"&legalBudgetTotalCost="+legalBudgetTotalCost+"&legalHoseAmount="+legalHoseAmount;
       	 		$("#mainFrm").attr("src",src);
        	}else{
        		oldshowReport(json);
        	}
        }
	});
}
//初始化cpt
var initCpt = function(){
	var json={};
	json.projId="";
	json.signDate = "";
	queryCptUrl(json);
}
//版本选择事件
$("#contractVersion").on("change",function(){
	var json={};
	json.projId=projId;
	json.signDate = signDate;
	queryCptUrl(json);
});
var contractPrintBack=function(){
	//$("#contractDetailform").toggleEditState(true);
	/*$('.iframe-big-box').addClass('hidden');
	//加载详述屏
	$('.contract_print_box').removeClass('hidden');*/
	
}

function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2002-12-18格式  
    var  aDate,  oDate1,  oDate2,  iDays;
    aDate  =  sDate1.split("-"); 
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);   //转换为12-18-2002格式  
    aDate  =  sDate2.split("-");
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);  
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24);    //把相差的毫秒数转换为天数  
    return  iDays;  
}

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#contractPrint/contractSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#contractPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#contractPrintTable_filter input").val();
		searchData.menuId="110404";
		//传入false  则不选中行
		$("#contractPrintTable").cgetData(false, function(){
		}); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#contractPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
};
var searchDone = function(){
	
	searchData = $("#searchForm_contract").serializeJson();
	searchData.projNo = $("#contractPrintTable_filter input").val();
	searchData.menuId="110404";
	//列表重新加载
    $("#contractPrintTable").cgetData();  
    
}

/**
 * 初始化列表
 */
var contractPrint = function () {
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

//修改监听
var updateMonitor=function(){
	/*//修改
	$('.updateBtn').off().on('click',function(){
		$('.iframe-big-box').addClass('hidden');
		$('.editbtn').removeClass('hidden');
		//加载修改
		$('.contract_print_box').removeClass('hidden');
		$(".contract_print_box").cgetContent("contractPrint/viewPage",'',callBack);
		
	});*/
}
var callBack=function(){
	$("#contractDetailform").toggleEditState(true);
}
//打印监听
var printMonitor=function(){
	//打印
	$('.printBtn').off().on('click',function(){
		$('.iframe-big-box').removeClass('hidden');
		$('.contract_print_box').addClass('hidden');
	});
}
//标记监听
var signMonitor=function(){
	$('.signBtn').off().on('click',function(){
		var len=$("#contractPrintTable").find('tr.selected').length;
		if(len>0){
           $("body").cgetPopup({
               	title: "提示信息",
               	content: "是否将选中的合同标记为已打印？",
               	accept: sureDone,
               	chide: true,
               	newpop: 'new',
               	icon: "fa-check-circle"
           });
		}else{
			alertInfo('请选择要标记打印的合同！');
		}
	});
}

var sureDone=function(){
	var projId=trSData.contractPrintTable.json.projId;
	$.ajax({
		type:'post',
		url:'contractPrint/signContractPrint',
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
	$("#contractPrintTable").cgetData();
}