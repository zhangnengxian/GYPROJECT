var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData={menuId :1104011},
    divisionalSearchData={},
    jointSearchData={}; //查询条件

jointSearchData.projId="-1";
divisionalSearchData.projId="-1";


/**
 * 加载工程列表
 */
var handlecontractReviewTable = function() {
	searchData.projNo=$("#waitHandleProjNo").val();
	"use strict";
	//  覆盖dataTable错误提示信息
	$('#contractReviewTable').dataTable.ext.errMode = function(s,h,m){
		errorPrompt();
	};
    if ($('#contractReviewTable').length !== 0) {
    	$('#contractReviewTable').on( 'init.dt',function(){
   			//加载页面
    		var len = $('#contractReviewTable').DataTable().ajax.json().data ? $('#contractReviewTable').DataTable().ajax.json().data.length : $('#contractReviewTable').DataTable().ajax.json().length;
    		if(len<1){
    			$(".tabs-mixin").cgetContent("contractReview/viewPage");
    		}
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#contractReviewTable').hideMask();
   			$("#contractReviewTable_filter input").attr("placeholder","合同编号");
       
   		    //绑定单击事件 弹出搜索框
   			searchMonitor();
   			//表格适应屏幕变化
   			setTimeout(function(){
   			    $("#contractReviewTable").DataTable().columns.adjust();
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
                { text: '签订', className: 'btn-sm btn-query business-tool-btn m-l-5 hidden signButton' }
                ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'contractReview/queryProject',
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
				{"data":"projStatusDes"},
				{"data":"workDayDto"},
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
				"targets":4,
				 "orderable":false,
				 "render":function(data,type,row,meta){
					 if(data!=null){
						 return data.haveDays;
					 }else{
						 return 0;
					 }
				 }
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
        var url = "#contractReview/projectSearchPopPage";
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});

	// 基础条件查询添加监听
	$('#contractReviewTable_filter input').on('change', function() {
		var projNo = $('#contractReviewTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#contractReviewTable').cgetData(true,tableCallBack); // 列表重新加载
	});
	// 基础条件查询添加回车事件
	$('#contractReviewTable_filter input').on('keyup', function(event) {
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
	var projNo = $('#contractReviewTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#contractReviewTable').cgetData(true,tableCallBack);  
},

/**
 * 选中行时，查询详述
 */
trSelectedBack = function(v, i, index, t, json){
     $(".tabs-mixin").cgetContent("contractReview/viewPage",{projId:json.projId,projectType:json.projectType,corpId:json.corpId,menuId:searchData.menuId});
},

tableCallBack = function(){
	var len = $('#contractReviewTable').DataTable().ajax.json().data ? $('#contractReviewTable').DataTable().ajax.json().data.length : $('#contractReviewTable').DataTable().ajax.json().length;
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
var contractReviewTable = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handlecontractReviewTable();
        	});
        }
    };
}();