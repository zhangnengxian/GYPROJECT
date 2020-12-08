var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData = {};
searchData.isPrint=$("#haveNotPrint").val();
var handleProjectSelfCheckPrint = function(){
	"use strict";
	 if ($('#projectSelfCheckPrintTable').length !== 0) {
		 $('#projectSelfCheckPrintTable').on( 'init.dt',function(){
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#projectSelfCheckPrintTable').hideMask();
   			//基础查询条件
   			$("#projectSelfCheckPrintTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   			//标记监听
   			signMonitor();
   		    showReport();
   		    modifyMonitor();//修改监听
   			setTimeout(function(){
   			    $("#projectSelfCheckPrintTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
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
                url: 'projectSelfCheckPrint/queryProjectSelfCheckPrint',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
			//ajax:'projectjs/complete/json/project_selfcheck_print.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
  				{"data":"silId",className:"none never"},
  				{"data":"projNo"},
  				{"data":"projName"},
  				{"data":"silDate"},
                {"data":"projId",className:"none never"},
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
    modifyFn(json);
	$("#sirId").val(json.sirId);
	showReport();	
}
//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#projectSelfCheckPrint/viewSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#projectSelfCheckPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#projectSelfCheckPrintTable_filter input").val();
		$("#projectSelfCheckPrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#projectSelfCheckPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
//弹屏确定回调
var searchDone = function(){
	searchData = $("#search_projectSlefCheck").serializeJson();
	searchData.projNo = $("#projectSelfCheckPrintTable_filter input").val();
	//列表重新加载
    $("#projectSelfCheckPrintTable").cgetData(true, tableCallBack);  
}
//列表重加载回调
var tableCallBack = function(){
	var len = $('#projectSelfCheckPrintTable').DataTable().ajax.json().data ? $('#projectSelfCheckPrintTable').DataTable().ajax.json().data.length : $('#projectSelfCheckPrintTable').DataTable().ajax.json().length;
	if(len<1){
		showReport();
	 }else{
	 }
}
//初始化列表
var projectSelfCheckPrint = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handleProjectSelfCheckPrint();
                /**
                 * 切换报验页签
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

//标记监听
var signMonitor=function(){
	$('.signBtn').off().on('click',function(){
		var len=$("#projectSelfCheckPrintTable").find('tr.selected').length;
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
	var projId=trSData.projectSelfCheckPrintTable.json.projId;
	$.ajax({
		type:'post',
		url:'projectSelfCheckPrint/signSelInspectionPrint',
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
	$("#projectSelfCheckPrintTable").cgetData();
}

//修改监听
var modifyMonitor=function(){
    $('.modifyBtn').off().on('click',function(){
        $("#modifyTab").tab("show");
        $(".savebtn").removeClass("hidden")
        $(".allText").attr("readonly",false);
    });
}

function modifyFn(json){
    if ($('#loginPost').val().indexOf($('#dataAdmin').val())!=-1){
        $(".modifyBtn").removeClass("hidden");
    }

    trSData.t && trSData.t.cgetDetail('checkListForm','projectSelfCheckPrint/viewDetail','4',detailCallback);
}

var detailCallback=function () {}


function saveClick() {
    var formData = $("#checkListForm").serializeJson();
    Base.subimt('projectSelfCheckPrint/modifySelfCheck', 'POST', JSON .stringify(formData), function(data) {
        $(".savebtn").addClass("hidden")
        var content = data ? "保存成功！" : "保存失败！";
        tips(content);
        $("#projectSelfCheckPrintTable").cgetData(true);
    })
}

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