var searchData={};
searchData.menuId = '110501';  //菜单ID
var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
/**
 * 初始化待编制计划列表
 */
var handleProjectPlan = function() {
    searchData.projNo=$("#waitHandleProjNo").val();
    "use strict";
    if ($('#planEstablishTable').length !== 0) {
    	$('#planEstablishTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#plan_establish_panel_box").cgetContent("projectPlan/viewPlanningPage");
   			//隐藏遮罩
   			$('#planEstablishTable').hideMask();
   			$("#planEstablishTable_filter input").attr("placeholder","工程编号");
   	    	//绑定单击事件 弹出搜索框
   			searchPop();
   			//回车监听
   			keySearch();
   			//编制监听
   			plait();
   			setTimeout(function(){
   			    $("#planEstablishTable").DataTable().columns.adjust();
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
            bStateSave:true,
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '编制', className: 'btn-sm btn-query business-tool-btn signBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'projectPlan/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/plan/json/plan_establish.json',
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
				{"data":"contractAmount",className:"fixed-number text-right"},
				{"data":"signDate"},
				{"data":"projStatusDes"},
				{"data":"signBack",
					className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData==$("#notThrough").val()){
							$(td).parent().css("background", "rgb(255, 219, 219)");
							//$(td).attr("id",row);
						}
					}
					},
				{"data":"overdue", className:"none never"},
				{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}
					
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},{
				"targets":2,
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
			 	},{
					"targets":5,
					 "orderable":false
				}],
		 	fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
};

/**
 * 回车按工程编号查询
 */
var keySearch=function(){
	
	$("#stateSelect,#planEstablishTable_filter input").on("change",function(){		
		var projNo = $("#planEstablishTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;
		//列表重新加载
	    $('#planEstablishTable').cgetData(true,planCallBack);  
		
	});
	//基础条件查询添加回车事件
	$('#planEstablishTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};

/**
 * 弹屏监听方法
 */
var searchPop = function(){
	$(".searchBtn").on("click",function(){
			var url = "#projectPlan/projectSearchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	keySearch();
};
/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_plan").serializeJson();
	//console.log(searchData);
	//增加查询状态限制条件
	searchData.projStatusId="1011";
	//列表重新加载
	$('#planEstablishTable').cgetData(true,planCallBack);  
    //清空表单
    //$("#searchForm_plan input").val("");
    //清空下拉框
    //$("#projLtypeId  option[value=''] ").attr("selected",true);
};

var planCallBack=function(){
	var len = $('#planEstablishTable').DataTable().ajax.json().data ? $('#planEstablishTable').DataTable().ajax.json().data.length : $('#planEstablishTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#planEstablishDetailform')[0].reset();
		 //保存按钮隐藏
		 $('.editbtn').addClass('hidden');
		 $('#planEstablishDetailform').toggleEditState(false);
	 }
}

/**
 * 编制按钮监听方法
 */
var plait=function(){
	$(".signBtn").off("click").on("click",function(){
		if($('#planEstablishTable').find('tr.selected').length>0){
			//表单可编辑
			$("#planEstablishDetailform").toggleEditState(true);
			//按钮显示
			$(".editbtn").removeClass("hidden");
			$(".saveBtn").removeClass("hidden");
    		$(".cancelBtn").removeClass("hidden");
    		
		}else{
			alertInfo('请选择编制计划	！');
		}
	});
};


/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('planEstablishDetailform','projectPlan/viewPlan','',saveBack); 
};


function saveBack(data){
	var plannedStartDate=data.plannedStartDate;
	var plannedEndDate=data.plannedEndDate;
	/*var contractAmount=data.contractAmount;
	var payType=data.payType;
	
	var pay;
	if(payType == 1 ){
		pay = contractAmount;
	}else{
		pay = contractAmount*0.8;
	}
	$("#downPayment").val(pay);*/
	
	//$("#projTimeLimit").val((timestamp(plannedEndDate)-timestamp(plannedStartDate))/86400000);
	if($("#customerServiceCenter").val()==$("#deptDivide").val()){
		$("#suName").attr("data-parsley-required",false);
	}else{
		$("#suName").attr("data-parsley-required",true);
	}
	if($("#contractAmount").val()==""){
		$(".noUser").addClass("hidden");
	}else{
		$(".noUser").removeClass("hidden");
	}
	
	
}

/**
 * 初始化审核列表
 */
var projectPlan = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        	handleProjectPlan();
        	});
        }
    };
}();



/**
 * 加载审批历史列表
 */
var planHistoryInit=function (){
	if ($('#auditHistoryTable').length !== 0) {
    	$('#auditHistoryTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#auditHistoryTable').hideMask();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [
            ],
            ajax: 'projectjs/plan/json/plan_history.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
				{"data":"date"},
				{"data":"result"},
				{"data":"opinion"},
				{"data":"auditer"}
			],
			columnDefs: [{
			}]
        });
    }
};


//$(".attach-panel").initAttachOper({
//	dispatch: {
//		tableid:'planEstablishTable'
//	},
//	auditInformation: {
//		tableid:'planEstablishTable'
//	}
//});

