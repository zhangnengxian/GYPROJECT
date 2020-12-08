var searchData = {};
searchData.isPrint=$("#haveNotPrint").val();
var handleSubAgreementPrint = function(){
	"use strict";
	 if ($('#subAgreementPrintTable').length !== 0) {
		 $('#subAgreementPrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#subAgreementPrintTable').hideMask();
   			$("#subAgreementPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
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
                url: 'subAgreementPrint/querySubSupplyContract',
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
      				{"data":"sscNo"},
      				{"data":"projName"},
      				{"data":"sscSignDate"},
      				{"data":"sscAmount",className:"text-right"},
      			],
  			columnDefs: [{
				"targets":1,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
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
  			}]
		 })
	 }
};

var trSelectedBack = function(v, i, index, t, json){
	//加载打印预览
	var sscNo = encodeURIComponent(cjkEncode(json.sscNo)),
	projName = encodeURIComponent(cjkEncode(json.projName)),
	projScaleDes = encodeURIComponent(cjkEncode(json.projScaleDes)),
	projAddr = encodeURIComponent(cjkEncode(json.projAddr)),
	sscaName = encodeURIComponent(cjkEncode(json.sscaName)),//甲方名称
	sscaCompPm = encodeURIComponent(cjkEncode(json.sscaCompPm)),//甲方项目经理
	sscaAddr = encodeURIComponent(cjkEncode(json.sscaAddr)),//甲方地址
	sscaLegalRepresent = encodeURIComponent(cjkEncode(json.sscaLegalRepresent)),//甲方法定代表人
	sscaDirector = encodeURIComponent(cjkEncode(json.sscaDirector)),//甲方委托代理人
	sscaSoptLeader = encodeURIComponent(cjkEncode(json.sscaSoptLeader)),//甲方现场负责人
	sscaPhone = encodeURIComponent(cjkEncode(json.sscaPhone)),//联系电话
	
	sscbName = encodeURIComponent(cjkEncode(json.sscbName)),			//乙方名称
	sscbCompPm = encodeURIComponent(cjkEncode(json.sscbCompPm)),//乙方驻地代表
	sscbAddr = encodeURIComponent(cjkEncode(json.sscbAddr)),			//乙方地址
	sscbLegalRepresent = encodeURIComponent(cjkEncode(json.sscbLegalRepresent)),	//乙方法定人
	sscbDirector = encodeURIComponent(cjkEncode(json.sscbDirector)),		//乙方委托人
	sscbSoptLeader = encodeURIComponent(cjkEncode(json.sscbSoptLeader)),		//现场负责人
	sscbPhone = encodeURIComponent(cjkEncode(json.sscbPhone)),		    //联系电话
	sscQualityStandar = encodeURIComponent(cjkEncode(json.sscQualityStandar)),//工程质量标准
	sscPlannedStartDate = encodeURIComponent(cjkEncode(json.sscQualityStandar)),//开工日期
	sscPlannedEndDate = encodeURIComponent(cjkEncode(json.sscQualityStandar)),//竣工日期
	sscPlannedTotalDays = encodeURIComponent(cjkEncode(json.sscPlannedTotalDays)),//竣工日期
	sscScope = encodeURIComponent(cjkEncode(json.sscScope)),//承包范围
	sscType = encodeURIComponent(cjkEncode(json.sscType)),//承包方式
	sscAmount = encodeURIComponent(cjkEncode(json.sscAmount)),//补充协议款价
	sscSignDate = encodeURIComponent(cjkEncode(json.sscSignDate)),//日期
	legalAmount = encodeURIComponent(cjkEncode(json.legalAmount)),//大写款价
	src = cptPath+"/ReportServer?reportlet=subContract/subAgreementPrint.cpt&sscNo=" + sscNo + "&projName="+ projName+"&projScaleDes="+projScaleDes+"&projAddr="+projAddr+"&sscaAddr="+sscaAddr+"&sscaName="+sscaName;
	src = src+"&sscSignDate="+sscSignDate+"&sscScope="+sscScope+"&sscType="+ sscType+"&sscAmount="+sscAmount+"&legalAmount="+legalAmount+"&sscbName="+sscbName+"&sscbAddr="+sscbAddr+"&sscaLegalRepresent="+sscaLegalRepresent;
	src = src+"&sscaDirector="+sscaDirector+"&sscaSoptLeader="+sscaSoptLeader+"&sscaPhone="+sscaPhone+"&sscbLegalRepresent="+sscbLegalRepresent+"&sscbDirector="+sscbDirector+"&sscbPhone="+sscbPhone+"&sscbSoptLeader="+sscbSoptLeader;
	src = src+"&sscQualityStandar="+sscQualityStandar+"&sscPlannedStartDate="+sscPlannedStartDate+"&sscPlannedEndDate="+sscPlannedEndDate+"&sscPlannedTotalDays="+sscPlannedTotalDays+"&sscaCompPm="+sscaCompPm+"&sscbCompPm="+sscbCompPm;
	$("#mainFrm").attr("src",src);
}
/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#subAgreementPrint/saSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#subAgreementPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#subAgreementPrintTable_filter input").val();
		//传入false  则不选中行
		$("#subAgreementPrintTable").cgetData(false, function(){
		}); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#subAgreementPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
};
var searchDone = function(){
	searchData = $("#searchForm_sub_agreement").serializeJson();
	searchData.projNo = $("#subAgreementPrintTable_filter input").val();
	//列表重新加载
    $("#subAgreementPrintTable").cgetData(false, function(){
	});  
    
}

/**
 * 初始化列表
 */
var subAgreementPrint = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleSubAgreementPrint();
        	});
        }
    };
}();

//标记监听
var signMonitor=function(){
	$('.signBtn').off().on('click',function(){
		var len=$("#subAgreementPrintTable").find('tr.selected').length;
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
	var projId=trSData.subAgreementPrintTable.json.projId;
	$.ajax({
		type:'post',
		url:'subAgreementPrint/signSubAgreementPrint',
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
	$("#subAgreementPrintTable").cgetData();
}