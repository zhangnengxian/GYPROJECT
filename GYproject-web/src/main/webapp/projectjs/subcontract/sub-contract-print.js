var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData = {};
var cptType = '';
searchData.isPrint=$("#haveNotPrint").val();
searchData.menuId="110604";
//合同模板
var showReport1 = function(scId,legalAmount){
    	src = cptPath+"/ReportServer?reportlet=subContract/"+cptType+"&scId="+scId+"&legalAmount="+legalAmount+"&imgUrl="+$("#imgUrl").val();
    },
	showReport2 = function(scId,legalAmount){
		src = cptPath+"/ReportServer?reportlet=subContract/"+cptType+"&scId="+scId+"&legalAmount="+legalAmount+"&imgUrl="+$("#imgUrl").val();
	},
	showReport3 = function(scId,legalAmount){
		src = cptPath+"/ReportServer?reportlet=subContract/"+cptType+"&scId="+scId+"&legalAmount="+legalAmount+"&imgUrl="+$("#imgUrl").val();
	},
    showReport4 = function(scId,legalAmount){
        src = cptPath+"/ReportServer?reportlet=subContract/"+cptType+"&scId="+scId+"&legalAmount="+legalAmount+"&imgUrl="+$("#imgUrl").val();
    };
var oldShowReport = function(json){
	var scId = encodeURIComponent(cjkEncode(json.scId)),
   	legalAmount = encodeURIComponent(cjkEncode(json.legalAmount));
   if(json.projLtypeId=='11'||json.projLtypeId=='15'){
       showReport1(scId,legalAmount);
   }else if(json.projLtypeId=='12'){
       showReport2(scId,legalAmount);
   }else if(json.projLtypeId=='13'){
       showReport3(scId,legalAmount);
   }else{
       showReport4(scId,legalAmount);
   }
   $("#mainFrm").attr("src",src);
}

var handleContractPrint = function(){
	"use strict";
	 if ($('#subContractPrintTable').length !== 0) {
		 $('#subContractPrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#subContractPrintTable').hideMask();
   			$("#subContractPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   			//修改监听
   			updateMonitor();
   			//打印监听
   			printMonitor();
             //标记监听
			signMonitor();
			setTimeout(function(){
   			    $("#subContractPrintTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
		 }).DataTable({
			language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
				{ text: '标记', className: 'btn-sm btn-query signBtn' }
                //{ text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                // { text: '打印', className: 'btn-sm btn-query printBtn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
			ajax: {
                url: 'subContractPrint/querySubContract',
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
  				{"data":"scNo"},
  				{"data":"projName"},
  				{"data":"scSignDate"},
  				{"data":"scAmount",className:"text-right"},
      		],
			 columnDefs: [{
					"targets":1,
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
			 	}
			 ]

		 })
	 }
}
/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#subContractPrint/subContractSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#subContractPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#subContractPrintTable_filter input").val();
		//传入false  则不选中行
		$("#subContractPrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#subContractPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
var searchDone = function(){
	searchData = $("#searchForm_sub").serializeJson();
	searchData.projNo = $("#subContractPrintTable_filter input").val();
	searchData.menuId="110604";
	//列表重新加载
    $("#subContractPrintTable").cgetData(false, tableCallBack);  
}

var tableCallBack = function(){
	var len = $('#subContractPrintTable').DataTable().ajax.json().data ? $('#subContractPrintTable').DataTable().ajax.json().data.length : $('#subContractPrintTable').DataTable().ajax.json().length;
	//console.log("len..."+len);
	if(len<1){
		$("#subContractDetailform").formReset();
		$("#subContractDetailform") && 	$("#subContractDetailform").toggleEditState(false).styleFit();
		$(".editbtn").addClass("hidden");
		src = cptPath+"/ReportServer?reportlet=subContract/subContractPrint.cpt"
		$("#mainFrm").attr("src",src);
	 }else{
		 $(".editbtn").removeClass("hidden");
	 }
}
//修改监听
var updateMonitor=function(){
	//修改
	$('.updateBtn').off().on('click',function(){
		if($("#subContractPrintTable").find("tr.selected").length>0){
			$('.fine_report').addClass('hidden');
			$('.editbtn').removeClass('hidden');
			//加载修改
			$('.subContract_print_box').removeClass('hidden');
			$(".subContract_print_box").cgetContent("subContractPrint/viewPage",'',callBack);
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
}
var callBack = function(){
	$('.editbtn').removeClass('hidden');
	$("#subContractDetailform").toggleEditState(true).styleFit();
}
//打印监听
var printMonitor=function(){
	//打印
	$('.printBtn').off().on('click',function(){
		$('.fine_report').removeClass('hidden');
		$('.subContract_print_box').addClass('hidden');
	});
}
//标记监听
var signMonitor=function(){
    $('.signBtn').off().on('click',function(){
        var len=$("#subContractPrintTable").find('tr.selected').length;
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
    var projId=trSData.subContractPrintTable.json.projId;
    console.info(projId);
    $.ajax({
        type:'post',
        url:'subContractPrint/signSubContractPrint',
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
    console.info(searchData.isPrint);
    $("#subContractPrintTable").cgetData();
}
var projectType,menuId,corp,projId,signDate,scId,legalAmount; //定义全局变量
var trSelectedBack = function(v, i, index, t, json){
	projectType=json.projectType;
	menuId=getStepId();
	corp=json.corpId;
	signDate=json.operateDate;
	projId=json.projId;
    scId = encodeURIComponent(cjkEncode(json.scId)),
	legalAmount = encodeURIComponent(cjkEncode(json.legalAmount));
        selectRV(json);
	//加载cpt
	queryCptUrl(json);

}
//遍历版本，显示相应公司工程类型的版本
var selectRV = function(json){
	var selectKeys = json.projectType+"."+json.corpId+"."+getStepId();
	var selectOprions = $("#contractVersion option");
	$.each(selectOprions,function(i,e){
		//分公司该工程类型施工合同版本
		if($(this).val().indexOf(selectKeys)>=0 || $(this).val()==null || $(this).val()==''){
			$(this).removeClass("hidden");
		}else{
			$(this).addClass("hidden");
		}
	});
	$("#contractVersion option:first").prop("selected", 'selected');
}
//获取cpt
var queryCptUrl = function(json){
	var scId = encodeURIComponent(cjkEncode(json.scId));
	//根据工程名称，菜单名称，签订日期，公司ID获取报表----开始
	var param={};
	param.projId=json.projId;
	projectType=json.projectType;
	param.menuId = getStepId();
	param.signDate = json.operateDate;
	param.corpId = json.corpId;
	if($("#contractVersion").val()!=null || $("#contractVersion").val() != ''){
		param.rvId=$("#contractVersion").val();
	}
	 $("#mainFrm").parent().parent().loadMask("读取中···",1,0.5);
	$.ajax({
		type:'POST',
		url:'subContractPrint/viewContractReport',
		data:param,
		success:function(cptUrl){
			console.info(2018,cptUrl);
			if(cptUrl != null && cptUrl !=''){
				cptType = cptUrl;
			var src = cptPath+"/ReportServer?reportlet=subContract/"+cptType+"&scId="+scId+"&legalAmount="+legalAmount+"&imgUrl="+$("#imgUrl").val();
       	 		$("#mainFrm").attr("src",src);
       	 	    $("#mainFrm").hideMask();
			}else{
				cptType = 'subContractPrint.cpt';
				oldShowReport(json);
			}
		}
	})
}

$("#contractVersion").on("change",function(){
	var json={};
	json.scId=scId;
	json.legalAmount=legalAmount;
	json.projectType=projectType;
	json.menuId=menuId;
	json.corp=corp;
	json.projId=projId;
	json.signDate=signDate;
	queryCptUrl(json);
});
var subContractPrintBack = function(){
	$("#subContractDetailform").toggleEditState(true).styleFit();
}

/**
 * 初始化列表
 */
var subContractPrint = function () {
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