/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var menuId = "1107031";
var jointAcceptanceApplyTable;
var completionDataTable;
/**查询条件  默认待勘察*/
var searchData={};
searchData.menuId = menuId;
var accessoryData={};
var listSearchData = {};
var handlejointAcceptanceApply = function() {
	"use strict";
    if ($('#jointAcceptanceApplyTable').length !== 0) {
    	jointAcceptanceApplyTable= $('#jointAcceptanceApplyTable').on( 'init.dt',function(){
    		//加载页面
    		$("#project_accept_panel_box").cgetContent("jointAcceptanceApply/viewPage",{projId:'',projectType:'',corpId:'',menuId:menuId});
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#jointAcceptanceApplyTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#jointAcceptanceApplyTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//修改监听方法
   			applyMonitor();
   			//右侧列表加载
   			//rightTableCgetData();
   		//跳转链接
    		if (crossPageBus) {
    			getSidebarMenu(11, true);
    			checkSidebarMenu(crossPageBus.hash)
    				//跳转后销毁对象
    			crossPageBus = null
    		}
   			setTimeout(function(){
   			    $("#jointAcceptanceApplyTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
   			
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },         
                { text: '申请', className: 'btn-sm btn-query business-tool-btn applyBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'jointAcceptanceApply/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/complete/json/completion_data_apply.json',
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
				{"data":"signBack",
					className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						console.info("cellData---"+cellData);
						if(cellData==$("#notThrough").val()){
							$(td).parent().children().css("background", "rgb(255, 219, 219)");
							//$(td).attr("id",row);
						}
					}
					},
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
    
};


//弹屏监听方法
var searchMonitor = function(){
	
	//查询按钮弹出屏查询
	$(".searchBtn").on("click",function(){
			var url = "#projectSelfCheck/projectSearchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#stateSelect,#jointAcceptanceApplyTable_filter input").on("change",function(){
		var stateSelect = $("#stateSelect").find("option:selected");
		var projNo = $("#jointAcceptanceApplyTable_filter input").val();
		searchData = {};
		searchData.projStatusId = stateSelect.val();
		searchData.projNo = projNo;
		searchData.menuId = menuId;
		$("#jointAcceptanceApplyTable").cgetData(true,dataApplyallBack);  //列表重新加载
		
	});
	//基础条件查询添加回车事件
	$('#jointAcceptanceApplyTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	searchData = $("#searchProjectSelfCheck").serializeJson();
	var projNo = $("#jointAcceptanceApplyTable_filter input").val();
	searchData.projNo=projNo;
	searchData.menuId = menuId;
	//列表重新加载
    $("#jointAcceptanceApplyTable").cgetData(true,dataApplyallBack);  
}

var dataApplyallBack = function(){
	//var len = $('#jointAcceptanceApplyTable').DataTable().ajax.json().data ? $('jointAcceptanceApplyTable').DataTable().ajax.json().data.length : $('#jointAcceptanceApplyTable').DataTable().ajax.json().length;
	var len = $('#jointAcceptanceApplyTable').DataTable().data().length;
	if(len<1){
		//清空右侧记录
		$("#jointAcceptanceApplyForm").formReset();
		$("#jointAcceptanceApplyForm").toggleEditState(false).styleFit();
		$(".editBtn").addClass("hidden");
		accessoryData.projNo="####";
	}
	 
}
//修改监听方法
var applyMonitor = function(){
	$(".applyBtn").on("click",function(){
		if($("#jointAcceptanceApplyTable").find("tr.selected").length>0){
			detailFlag = true;
			//切换可编辑状态
			$("#jointAcceptanceApplyForm").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
            if(!$("#applyDate").val()){
                var sysDate = timestamp($("#sysDate").val());
                $("#applyDate").val(format(sysDate,"default"));
            }
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
	$("#completionDataTable .checkbox").on("change",function(){
		handleCheckBox();
	})
};



var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	$(".informSaveBtn").addClass("hidden");
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	detailFlag = false;
	//加载右侧页面后，加载右侧页面数据
	$("#project_accept_panel_box").cgetContent("jointAcceptanceApply/viewPage",{projId:json.projId,projectType:json.projectType,corpId:json.corpId,menuId:menuId},function(){
		//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
		t.cgetDetail('jointAcceptanceApplyForm','jointAcceptanceApply/viewJointAcceptanceApply','',dataBack);
	});
};
var dataBack = function(data){
	if(data.applyDate){
		$("#applyDate").val(format(data.applyDate));
	}
}

var jointAcceptanceApply = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handlejointAcceptanceApply();
        	});
        }
    };
}();