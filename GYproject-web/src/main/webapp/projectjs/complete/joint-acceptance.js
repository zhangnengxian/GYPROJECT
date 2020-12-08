var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData={menuId :110703},
    divisionalSearchData={},
    jointSearchData={}; //查询条件

jointSearchData.projId="-1";
divisionalSearchData.projId="-1";


/**
 * 加载工程列表
 */
var handleJointAcceptance = function() {
	searchData.projNo=$("#waitHandleProjNo").val();
	"use strict";
    if ($('#jointAcceptanceTable').length !== 0) {
    	$('#jointAcceptanceTable').on( 'init.dt',function(){
   			//加载页面
    		var len = $('#jointAcceptanceTable').DataTable().ajax.json().data ? $('#jointAcceptanceTable').DataTable().ajax.json().data.length : $('#jointAcceptanceTable').DataTable().ajax.json().length;
    		if(len<1){
    			$(".tabs-mixin").cgetContent("jointAcceptance/viewPage");
    		}
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#jointAcceptanceTable').hideMask();
   			$("#jointAcceptanceTable_filter input").attr("placeholder","工程编号");
       
   		//绑定单击事件 弹出搜索框
   			searchMonitor();
   		//推送单击事件监听
   			pushMonitor();
   			//表格适应屏幕变化
   			setTimeout(function(){
   			    $("#jointAcceptanceTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
   			//跳转链接
   			if (crossPageBus) {
   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   					//跳转后销毁对象
   				crossPageBus = null
   			}
    	}).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '推送', className: 'btn-sm btn-query business-tool-btn m-l-5 hidden pushButton' },
                { text: '验收', className: 'btn-sm btn-query business-tool-btn m-l-5 hidden jointButton' }
                ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'jointAcceptance/queryJointAcceptancer',
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
				{"data":"projNo"},
				{"data":"projName"},
                {"data":"projChangesDes"},
				{"data":"projStatusDes"},
				{"data":"overdue",className:"none never"},
				{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}

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
				"targets":3,
				 "orderable":false
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
},

/**
 * 弹屏监听方法
 */
searchMonitor = function(){
	$('.searchBtn').on('click',function(){
        var url = "#jointAcceptance/projectSearchPopPage";
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});

	// 基础条件查询添加监听
	$('#jointAcceptanceTable_filter input').on('change', function() {
		var projNo = $('#jointAcceptanceTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#jointAcceptanceTable').cgetData(true,tableCallBack); // 列表重新加载
	});
	// 基础条件查询添加回车事件
	$('#jointAcceptanceTable_filter input').on('keyup', function(event) {
		if (event.keyCode == '13') {
			$(this).change();
		}
	});
},

/**
 * 查询弹出屏，点击确定后 
 */
searchDone = function(){
	searchData = $('#searchJointPop').serializeJson();
	var projNo = $('#jointAcceptanceTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#jointAcceptanceTable').cgetData(true,tableCallBack);  
},

/**
 * 选中行时，查询详述
 */
trSelectedBack = function(v, i, index, t, json){
	 if($("#corpId").val().indexOf(1101) >= 0){
		 if(json.projChanges.indexOf(3) >= 0 || json.projChanges.indexOf(0) >= 0 || json.projChanges == '' || json.projChanges == null){
			 $(".pushButton").addClass("hidden");
		 }else{
			 $(".pushButton").removeClass("hidden");
		 }
	 }else{
		 $(".pushButton").addClass("hidden");
	 }
     $(".tabs-mixin").cgetContent("jointAcceptance/viewPage",{projectType:json.projectType,corpId:json.corpId,menuId:searchData.menuId,projId:json.projId,contributionMode:json.contributionMode});
},
tableCallBack = function(){
	var len = $('#jointAcceptanceTable').DataTable().ajax.json().data ? $('#jointAcceptanceTable').DataTable().ajax.json().data.length : $('#jointAcceptanceTable').DataTable().ajax.json().length;
	if(len<1){
        $(".addBtn").addClass("hidden");
        $(".updateBtn").addClass("hidden");
	}else{
        $(".addBtn").removeClass("hidden");
        $(".updateBtn").removeClass("hidden");
	}
    if($("#loginPost").val()==$("#inPost").val()){
        $(".addBtn,.updateBtn").addClass("hidden");
    }else{
        $(".addBtn,.updateBtn").removeClass("hidden");
    }

    if($("#deptDivide").val()==$("#qualitySafetyGroup").val()){
        $(".addBtn").removeClass("hidden");
    }else{
        $(".addBtn").addClass("hidden");
    }
};

/**
 * 右屏推送监听
 */
var pushMonitor = function(){
	  //点击推送
    $(".pushButton").off().on("click",function(){
    	if($('#jointAcceptanceTable').find('tr.selected').length>0){
    		acceptDone();
    	}else{
    		alertInfo("请选择验收工程！");
    	}
    	
    });
}
var acceptDone = function(){
    var len = $('#jointAcceptanceTable').DataTable().ajax.json().data ? $('#jointAcceptanceTable').DataTable().ajax.json().data.length : $('#jointAcceptanceTable').DataTable().ajax.json().length;
    if(len<1){
        alertInfo('请先完成验收记录！');
    }else{
        var myoptions = {
            title: "提示信息",
            content: "确认联合验收已完成？",
            accept: acceptPush,
            chide: false,
            icon: "fa-check-circle",
            newpop: 'new'
        }
        $("body").cgetPopup(myoptions);
    }
}
//判断页面签字之后调用
//获取form中可编辑的日期输入框，给其增加必填属性
var signDate = function(){
	var signDates = $(".signDate");
	$.each(signDates,function(i,e){
		if($(e).attr("readonly")!="readonly"){
			$(e).attr("required",true);
		}
	});
}

/**
 * 初始化工程列表
 */
var jointAcceptance = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handleJointAcceptance();
        	});
        }
    };
}();