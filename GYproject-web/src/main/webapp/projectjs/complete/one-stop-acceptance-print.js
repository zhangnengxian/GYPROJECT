var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData = {};
searchData.isPrint=$("#haveNotPrint").val();
var handleOneStopAcceptancePrint = function(){
	"use strict";
	 if ($('#oneStopAcceptancePrintTable').length !== 0) {
		 $('#oneStopAcceptancePrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#oneStopAcceptancePrintTable').hideMask();
   			//基础查询条件
   			$("#oneStopAcceptancePrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   			//标记监听
   			signMonitor();
   		    // showReport();
   			setTimeout(function(){
   			    $("#oneStopAcceptancePrintTable").DataTable().columns.adjust();
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
                url: 'oneStopAcceptancePrint/queryJointAcceptance',
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
  				{"data":"jaId",className:"none never"},
  				{"data":"projNo"},
  				{"data":"projName"},
  				{"data":"jaDate"},
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
	showReport();
}
//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#oneStopAcceptancePrint/acceptanceSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#oneStopAcceptancePrintTable_filter input").on("change",function(){
		searchData.projNo = $("#oneStopAcceptancePrintTable_filter input").val();
		$("#oneStopAcceptancePrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#oneStopAcceptancePrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
//弹屏确定回调
var searchDone = function(){
	searchData = $("#searchAcceptancePop").serializeJson();
	searchData.projNo = $("#oneStopAcceptancePrintTable_filter input").val();
	//列表重新加载
    $("#oneStopAcceptancePrintTable").cgetData(true, tableCallBack);  
}
//列表重加载回调
var tableCallBack = function(){
	var len = $('#oneStopAcceptancePrintTable').DataTable().ajax.json().data ? $('#oneStopAcceptancePrintTable').DataTable().ajax.json().data.length : $('#oneStopAcceptancePrintTable').DataTable().ajax.json().length;
	if(len<1){
		src = cptPath+"/ReportServer?reportlet=complete/oneStopAcceptancePrint.cpt"
		$("#mainFrm").attr("src",src);
	 }else{
	 }
}
//初始化列表
var oneStopAcceptancePrint = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handleOneStopAcceptancePrint();
        	});
        }
    };
}();

//标记监听
var signMonitor=function(){
	$('.signBtn').off().on('click',function(){
		var len=$("#oneStopAcceptancePrintTable").find('tr.selected').length;
		if(len>0){
           $("body").cgetPopup({
               	title: "提示信息",
               	content: "是否将选中的验收单标记为已打印？",
               	accept: sureDone,
               	chide: true,
               	newpop: 'new',
               	icon: "fa-check-circle"
           });
		}else{
			alertInfo('请选择要标记打印的验收单！');
		}
	});
}

var sureDone=function(){
	var jaId=trSData.oneStopAcceptancePrintTable.json.jaId;
	console.info(jaId);
	$.ajax({
		type:'post',
		url:'oneStopAcceptancePrint/signJointAcceptancePrint',
		contentType: "application/json;charset=UTF-8",
        data: jaId,
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
	$("#oneStopAcceptancePrintTable").cgetData();
}