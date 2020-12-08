/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
/**查询条件*/
var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData={},flag=1,
handleProjectSelfCheck = function() {
	"use strict";
    if ($('#projectSelfCheckListTable').length !== 0) {
    	$('#projectSelfCheckListTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
        	//隐藏遮罩
   			$('#projectSelfCheckListTable').hideMask();
   			//基础查询条件
   			$("#projectSelfCheckListTable_filter input").attr("placeholder","工程编号");
   			
    		
   			//弹屏查询
   			searchPop();
   			//自检监听方法
   			registerMonitor();
   			setTimeout(function(){
   			    $("#projectSelfCheckListTable").DataTable().columns.adjust();
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
            dom: 'fBrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '自检', className: 'btn-sm btn-query business-tool-btn registerBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
            ajax: {
              url: 'projectSelfCheck/queryProject',
              type:'post',
              data: function(d){
                 	$.each(searchData,function(i,k){
                 		d[i] = k;
                 	});
                 	
              },
              dataSrc: 'data'
           },
            //ajax: 'projectjs/complete/json/joint_acceptance.json',
            select: true,  //支持多选
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            columns: [
                {"data":"projId",class:'none never'},
      			{"data":"projNo"},
				{"data":"projName"},
				{"data":"projStatusDes"},
				{"data":"overdue",className:"none never" },
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
}

/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	flag=1;
	$('.editbtn').addClass('hidden');
	$("#checkListForm").formReset();
	trSData.t && trSData.t.cgetDetail('checkListForm','projectSelfCheck/viewSelfInspectionList','',dataBack);
	$("input:radio").removeAttr('checked');
	$(':input').not(":radio").val('');
	//$("#materialCheckForm").formReset();
	//trSData.t && trSData.t.cgetDetail('materialCheckForm','projectSelfCheck/selfInspectionRecordMaterial','',detailCallback);
	//$("#qualityCheckForm").formReset();
	trSData.t && trSData.t.cgetDetail('qualityCheckForm','projectSelfCheck/selfInspectionRecordQuqlity','',detailCallback);
	$("input:radio").attr("disabled","disabled");
}

var dataBack= function(data){
	if(data.isBack!=null && data.isBack!=''){
		$(".backInfo").removeClass("hidden");
	}else{
		$(".backInfo").addClass("hidden");
	}
}
var detailCallback = function(){
	$(".tab-content").hideMask();
}
var materialCheckCallback = function(){
	if(flag){
		$("input:radio").attr("disabled","disabled");
	}
	$("#materialCheckForm").formReset();
	trSData.t && trSData.t.cgetDetail('materialCheckForm','projectSelfCheck/selfInspectionRecordMaterial','',detailCallback);
}
var qualityCheckCallback = function(){
	if(flag){
		$("input:radio").attr("disabled","disabled");
	}
	$("#qualityCheckForm").formReset();
	trSData.t && trSData.t.cgetDetail('qualityCheckForm','projectSelfCheck/selfInspectionRecordQuqlity','',detailCallback);
}
/**
 * 弹屏监听方法
 */
var searchPop=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#projectSelfCheck/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone,
			cancel:clearForm
		});
	});
	//基础条件查询添加监听
	$('#projectSelfCheckListTable_filter input').on('change',function(){
		var projNo = $('#projectSelfCheckListTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$("#checkListForm").formReset();
		$("#qualityCheckForm").formReset();
		//$("#materialCheckForm").formReset();
		$('#projectSelfCheckListTable').cgetData(true,projectTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#projectSelfCheckListTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});			
}
/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchProjectSelfCheck").serializeJson();
	var projNo = $("#projectSelfCheckListTable_filter input").val();
	searchData.projNo=projNo;
	//列表重新加载
    $("#projectSelfCheckListTable").cgetData(true,projectTableCallBack);  
}
/**
 * 查询弹出屏，点击取消后
 */
var clearForm=function(){};
var projectTableCallBack = function(){
	var len = $('#projectSelfCheckListTable').DataTable().data().length;
	if(len<1){
		 //清空右侧记录
		$("#checkListForm").formReset();
		$("#qualityCheckForm").formReset();
		 
		 $("#checkListForm").toggleEditState(false);
		 $("#qualityCheckForm").toggleEditState(false);
		 //$("#materialCheckForm").toggleEditState(false);
		 $('.editbtn').addClass('hidden');
		 
	}
}
/**
 * 自检监听方法
 */
var registerMonitor = function(){
	$('.registerBtn').off('click').on('click',function(){
		flag=0;
		if($('#projectSelfCheckListTable').find('tr.selected').length>0){
            if(!$("#silDate").val()){
                var sysDate = timestamp($("#sysDate").val());
                $("#silDate").val(format(sysDate,"default"));
            }
			//维护按钮显示出来
			$('.editbtn').removeClass('hidden');
			//切换可编辑状态
			 $("#checkListForm").toggleEditState(true);
			 $("#qualityCheckForm").toggleEditState(true);
			 //$("#materialCheckForm").toggleEditState(true);
			//radio可编辑
			$("#qualityCheckForm input:radio").attr("disabled",false);
			$("#qualityCheckForm input:radio").removeClass("disabled")
			
		}else{
			alertInfo('请选择要登记的工程记录！');
		}
	});
}
projectSelfCheck = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        	$('[href="#materialCheck"]').on("shown.bs.tab", function(){
        		//加载资料自检页面
        		if(!$("#pre_materialCheck_panel_box").children().length){
        			$("#pre_materialCheck_panel_box").cgetContent("projectSelfCheck/materialCheck",'',materialCheckCallback);
        		}
        		
        	
//        		$(".tab-content").loadMask();
//        		$("#materialCheckForm").formReset();
//        		if(!trSData.projectSelfCheckListTable.t){
//        			$(".tab-content").hideMask();
//        		}else{
//        			trSData.projectSelfCheckListTable.t.cgetDetail('materialCheckForm','projectSelfCheck/selfInspectionRecordMaterial','',detailCallback);
//        		}
        	   /* setTimeout(function(){
        	    	$("#materialCheckForm").styleFit();
        	    },120);*/
        	});
        	$('[href="#qualityCheck"]').on("shown.bs.tab", function(){
        		//加载质量自检页面
        		if(!$("#pre_qualityCheck_panel_box").children().length){
        			console.info("corpId---"+trSData.projectSelfCheckListTable.json.corpId);
        			$("#pre_qualityCheck_panel_box").cgetContent("projectSelfCheck/qualityCheck",{corpId:trSData.projectSelfCheckListTable.json.corpId},qualityCheckCallback);
        		}
        		
//        		$(".tab-content").loadMask();
//        		$("#qualityCheckForm").formReset();
//        		if(!trSData.projectSelfCheckListTable.t){
//        			$(".tab-content").hideMask();
//        		}else{
//        			trSData.projectSelfCheckListTable.t.cgetDetail('qualityCheckForm','projectSelfCheck/selfInspectionRecordQuqlity','',detailCallback);
//        		}
        		
        		setTimeout(function(){
        	    	$("#qualityCheckForm").styleFit();
        	    },120);
        	});

        	$('[href="#checkList"]').on("shown.bs.tab", function(){
        		setTimeout(function(){
        	    	$("#checkListForm").styleFit();
        	    },120);
        		$("#checkListForm").hideMask();
        	});
    	    $("#checkListForm").styleFit();
    	    

    	    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5").handleSignature(); 
    	    
    	    handleProjectSelfCheck();
        	});
        }
    };
}();