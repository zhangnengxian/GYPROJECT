var showLength; //显示长度
var menuId="110510";
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData = {};
searchData.suIsPrint=$("#haveNotPrint").val();
searchData.menuId=menuId;
//任务单
var showReport = function(scId){
	console.info(2019,scId);
    var loginName =encodeURIComponent(cjkEncode($("#loginName").val())),
        loginPhone = $("#loginPhone").val();
    	src = cptPath+"/ReportServer?reportlet=subContract/supervisorTaskPrint.cpt&planId="+scId+"&loginName=" + loginName+"&loginPhone=" + loginPhone+"&imgUrl=/opt/gcglgas/upload/sign/";
    	$("#mainFrm").attr("src",src);
};
/*var initCpt = function(){
	var json={};
	json.cpId="";
  	json.cpArriveDate = "";
  	json.projId = "";
  	queryCptUrl(json);
};*/
//ajax请求加载不同的报表
var queryCptUrl = function(json){

  	//根据工程名称，菜单名称，签订日期，公司ID获取报表----开始
	var param = {};
	param.projId = json.projId;
	param.menuId = menuId;
	param.signDate = json.cpArriveDate;
	param.rvId=json.corpId;
	console.info(param);
	var loginName =encodeURIComponent(cjkEncode($("#loginName").val())),
    loginPhone = $("#loginPhone").val();

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
                    var src = cptPath+"/ReportServer?reportlet=subContract/"+cptUrl+"&planId="+json.cpId+"&loginName="+loginName+"&loginPhone="+loginPhone+"&imgUrl=/opt/gcglgas/upload/sign/";
                    console.log(src);
                    $("#mainFrm").attr("src",src);
      			}
      		}else{
          		showReport(json.cpId);
          	}
        }
  	});
};
var handleContractPrint = function(){
	"use strict";
	 if ($('#supervisorTaskPrintTable').length !== 0) {
		 $('#supervisorTaskPrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#supervisorTaskPrintTable').hideMask();
   			$("#supervisorTaskPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
             //标记监听
			signMonitor();
			modifyMonitor();//修改监听
			setTimeout(function(){
   			    $("#supervisorTaskPrintTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
			//initCpt();
		 }).DataTable({
			language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
				{ text: '修改', className: 'btn-sm btn-modify business-tool-btn hidden modifyBtn' },
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
				 { text: '标记', className: 'btn-sm btn-query signBtn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
			ajax: {
                url: 'constructionDispatch/queryConstructionPlan2',
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
                 "targets": 1,
                 "visible":false
             },{
                 "targets":3,
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
		var url = "#supervisorTaskPrint/projectSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#supervisorTaskPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#supervisorTaskPrintTable_filter input").val();
		//传入false  则不选中行
		$("#supervisorTaskPrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#supervisorTaskPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
var searchDone = function(){
	searchData = $("#searchForm_plan").serializeJson();
	searchData.projNo = $("#supervisorTaskPrintTable_filter input").val();
	searchData.menuId=menuId;
	//列表重新加载
    $("#supervisorTaskPrintTable").cgetData(true);
}

var tableCallBack = function(){
		// src = cptPath+"/ReportServer?reportlet=subContract/constructionTaskPrint.cpt"
		// $("#mainFrm").attr("src",src);
}
//标记监听
var signMonitor=function(){
    $('.signBtn').off().on('click',function(){
        var len=$("#supervisorTaskPrintTable").find('tr.selected').length;
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
    var projId=trSData.supervisorTaskPrintTable.json.projId;
    $.ajax({
        type:'post',
        url:'constructionTaskPrint/signSupervisorTaskPrint',
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
    searchData.suIsPrint=$("#haveNotPrint").val();
    console.log(searchData);
    $("#supervisorTaskPrintTable").cgetData();
}
var trSelectedBack = function(v, i, index, t, json){

	modifyFn(json);
	cpId = json.cpId;
	projId = json.projId;
	cpArriveDate = json.cpArriveDate;
	queryCptUrl(json);
}



//修改监听
var modifyMonitor=function(){
	$('.modifyBtn').off().on('click',function(){
		var len=$("#supervisorTaskPrintTable").find('tr.selected').length;
		if (len>0) {
			$("#modifyTab").tab("show");
			$(".savebtn").removeClass("hidden")
			$("#suPop").removeClass("disabled");
		}else {
			tips("请选择要修改的记录！");
		}
	});
}

function modifyFn(json){
	if ($('#loginPost').val().indexOf($('#dataAdmin').val())!=-1){
		$(".modifyBtn").removeClass("hidden");
	}
	trSData.t && trSData.t.cgetDetail('detaileForm','supervisorTaskPrint/viewDetail','0',detailCallback);
}

var detailCallback=function () {};




function saveClick() {
    var pf = $("#detaileForm");
    if (pf.parsley().isValid()) { //验证必填是否已填写
        var formData = pf.serializeJson();
        Base.subimt('supervisorTaskPrint/modifyInfo', 'POST', JSON .stringify(formData), function(data) {
            $(".savebtn").addClass("hidden")
            var content = data ? "保存成功！" : "保存失败！";
            tips(content);
            $("#supervisorTaskPrintTable").cgetData(true);
        })
    } else {
        pf.parsley().validate();
    }




}



/**
 * 初始化列表
 */
var supervisorTaskPrint = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handleContractPrint();
				/**
				 * 切换页签
				 */
				$("#listTab, #modifyTab").off("shown.bs.tab").on("shown.bs.tab",function(){
					if($(this).is($("#listTab"))){
						$(".savebtn").addClass("hidden")
						$(".allText").attr("readonly",true);
					}else if($(this).is($("#modifyTab"))){

					}
				});
        	});
        }
    };
}();



function tips(content) {
	var myoptions = {
		title : "提示信息",
		content : content,
		accept : popClose,
		chide : true,
		icon : "fa-check-circle"
	};
	$("body").cgetPopup(myoptions);
}