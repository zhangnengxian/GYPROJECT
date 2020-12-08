var searchData = {};
var showLength; //显示长度
//var menuId='110411';//智能表合同打印
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
searchData.isPrint=$("#haveNotPrint").val();

var handleContractPrint = function(){
	"use strict";
	 if ($('#intelligentMeterConPrintTable').length !== 0) {
		 $('#intelligentMeterConPrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#intelligentMeterConPrintTable').hideMask();
   			$("#intelligentMeterConPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   			//修改监听
   			//updateMonitor();
   			//打印监听
   			printMonitor();
             //标记监听
			signMonitor();
			//showReport('','','');
			setTimeout(function(){
   			    $("#intelligentMeterConPrintTable").DataTable().columns.adjust();
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
                url: 'intelligentMeterConPrint/queryImcContract',
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
  				{"data":"imcNo"},
  				{"data":"projName"},
  				{"data":"imcSignDate"},
  				{"data":"totalCost",className:"text-right"},
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
		var url = "#intelligentMeterConPrint/imcSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#intelligentMeterConPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#intelligentMeterConPrintTable_filter input").val();
		//传入false  则不选中行
		$("#intelligentMeterConPrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#intelligentMeterConPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
var searchDone = function(){
	searchData = $("#searchForm_imc").serializeJson();
	searchData.projNo = $("#intelligentMeterConPrintTable_filter input").val();
	//列表重新加载
    $("#intelligentMeterConPrintTable").cgetData(false, tableCallBack);  
}

var tableCallBack = function(){
	var len = $('#intelligentMeterConPrintTable').DataTable().ajax.json().data ? $('#intelligentMeterConPrintTable').DataTable().ajax.json().data.length : $('#intelligentMeterConPrintTable').DataTable().ajax.json().length;
	if(len<1){
		$("#subContractDetailform").formReset();
		$("#subContractDetailform") && 	$("#subContractDetailform").toggleEditState(false).styleFit();
		$(".editbtn").addClass("hidden");
		//src = cptPath+"/ReportServer?reportlet=subContract/intelligentMeterConPrint.cpt"
		//$("#mainFrm").attr("src",src);
		showReport('');
	 }else{
		 $(".editbtn").removeClass("hidden");
	 }
}
//修改监听
var updateMonitor=function(){
	//修改
	$('.updateBtn').off().on('click',function(){
		if($("#intelligentMeterConPrintTable").find("tr.selected").length>0){
			$('.fine_report').addClass('hidden');
			$('.editbtn').removeClass('hidden');
			//加载修改
			$('.subContract_print_box').removeClass('hidden');
			$(".subContract_print_box").cgetContent("intelligentMeterConPrint/viewPage",'',callBack);
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
        var len=$("#intelligentMeterConPrintTable").find('tr.selected').length;
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
    var projId=trSData.intelligentMeterConPrintTable.json.projId;
    console.info(projId);
    $.ajax({
        type:'post',
        url:'intelligentMeterConPrint/signImcPrint',
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
    $("#intelligentMeterConPrintTable").cgetData();
};



var trSelectedBack = function(v, i, index, t, json){
	json.menuId=menuId;//将menuId添加到json对象属性中
	queryCptUrl(json);
};




var intelligentMeterConPrintBack = function(){
	$("#subContractDetailform").toggleEditState(true).styleFit();
}

/**
 * 初始化列表
 */
var intelligentMeterConPrint = function () {
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