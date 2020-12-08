var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var cptType = '';
var searchData = {};
searchData.isPrint=$("#haveNotPrint").val();
var handlepreinspectionPrint = function(){
	"use strict";
	 if ($('#preinspectionPrintTable').length !== 0) {
		 $('#preinspectionPrintTable').on( 'init.dt',function(){
			 var len = $('#preinspectionPrintTable').DataTable().ajax.json().data ? $('#preinspectionPrintTable').DataTable().ajax.json().data.length : $('#preinspectionPrintTable').DataTable().ajax.json().length;
	    		if(len<1){     //默认加载每一个公司下的第一种类型cpt
	    			 var json = {} ;
	            	 json.corpId = '';
	            	 json.menuId = '110722'; 
	            	 json.projectType = '';
	            	 queryCptType(json);   //加载报表类型
	    		}
			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#preinspectionPrintTable').hideMask();
   			//基础查询条件
   			$("#preinspectionPrintTable_filter input").attr("placeholder","工程编号");

   			 searchMonitor();//搜索监听
			 modifyMonitor();//修改监听
   			 signMonitor();//标记监听
   		     showReport();
   			setTimeout(function(){
   			    $("#preinspectionPrintTable").DataTable().columns.adjust();
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
                url: 'preinspectionPrint/queryPreinspectionPrint',
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
  				{"data":"piId",className:"none never"},
  				{"data":"projNo"},
  				{"data":"projName"},
  				{"data":"piDate"},
                {"data":"projId" ,className:"none never"},
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
	$("#piId").val(json.piId);
	$("#projId").val(json.projId);
	 queryCptType(json);
	//showReport();	
}

	function modifyFn(json){
        if ($('#loginPost').val().indexOf($('#dataAdmin').val())!=-1){
            $(".modifyBtn").removeClass("hidden");
        }
        trSData.t && trSData.t.cgetDetail('preinspectionForm','preinspectionPrint/viewDetail','4',detailCallback);
    }
    
    var detailCallback=function () {}

//根据公司ID、工程类型、菜单类型查找cpt类型--开始
var queryCptType = function(json){
	var piId = encodeURIComponent(cjkEncode(json.piId));    //解决中文乱码--联合验收Id
	var projId = encodeURIComponent(cjkEncode(json.projId));    //解决中文乱码--工程Id
	var projName = encodeURIComponent(cjkEncode(json.projName));    //解决中文乱码--工程名称
	var param = {} ;  //传递参数
	var menuId = getStepId();
	if(menuId == null || menuId == ''){   //判断菜单ID是否为空
		menuId = "110722";
	}
	param.projId = json.projId;
	param.corpId = json.corpId;  
	param.projectType = json.projectType;
	param.menuId = menuId ;
	$.ajax({
		type:'POST',
		url:'preinspectionPrint/queryCptType',
	    data:param,
	    success:function(cptUrl){
	    	if(cptUrl!='' && cptUrl !=null && cptUrl !=undefined){
	    		cptType = cptUrl;
	    	var	imgUrl = $(".imgUrl").val();
	    	var	src = cptPath+"/ReportServer?reportlet=complete/"+cptType+"&piId=" + piId + "&projId=" + projId + "&projName=" + projName;
	    		src = src + "&path="+imgUrl;
	    		$('#mainFrm').hideMask();   //隐藏罩
	    	    $("#mainFrm").attr("src",src);
	    	}else{
	    		cptType = 'preinspectionPrint.cpt'; //如果查不出cpt类型则默认贵州燃气集团的一个cpt
	    		showReport();
	    	}
	    }
	})
	  
}//根据公司ID、整改类型、菜单类型查找cpt类型--结束
//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#preinspectionPrint/viewSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#preinspectionPrintTable_filter input").on("change",function(){
		searchData.projNo = $("#preinspectionPrintTable_filter input").val();
		$("#preinspectionPrintTable").cgetData(true, tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#preinspectionPrintTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}
//弹屏确定回调
var searchDone = function(){
	searchData = $("#searchForm_preinsection").serializeJson();
	searchData.projNo = $("#preinspectionPrintTable_filter input").val();
	//列表重新加载
    $("#preinspectionPrintTable").cgetData(true, tableCallBack);  
}
//列表重加载回调
var tableCallBack = function(){
	var len = $('#preinspectionPrintTable').DataTable().ajax.json().data ? $('#preinspectionPrintTable').DataTable().ajax.json().data.length : $('#preinspectionPrintTable').DataTable().ajax.json().length;
	if(len<1){
		showReport();
	 }else{
	 }
}
//初始化列表
var preinspectionPrint = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handlepreinspectionPrint();

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
		var len=$("#preinspectionPrintTable").find('tr.selected').length;
		if(len>0){
           $("body").cgetPopup({
               	title: "提示信息",
               	content: "是否将选中的预验收表标记为已打印？",
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


//修改监听
var modifyMonitor=function(){
    $('.modifyBtn').off().on('click',function(){
    	$("#modifyTab").tab("show");
    	$(".savebtn").removeClass("hidden")
        $(".allText").attr("readonly",false);
    });
}

var sureDone=function(){
	var projId=trSData.preinspectionPrintTable.json.projId;
	$.ajax({
		type:'post',
		url:'preinspectionPrint/signPreInspectionPrint',
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
	$("#preinspectionPrintTable").cgetData();
}

function saveClick() {
    var formData = $("#preinspectionForm").serializeJson();
    Base.subimt('preinspectionPrint/modifyPreinspection', 'POST', JSON .stringify(formData), function(data) {
        $(".savebtn").addClass("hidden")
        var content = data ? "保存成功！" : "保存失败！";
        tips(content);
        $("#preinspectionPrintTable").cgetData(true);

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