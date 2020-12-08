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
var openingPlanTable;
var scaleTable;
var gasPlanTable;
/**查询条件  默认待勘察*/
var searchData={},
	recordData={};
var accessoryData={};
var detailSearchData = {};
var handleOpeningPlan = function() {
	"use strict";
    if ($('#openingPlanTable').length !== 0) {
    	openingPlanTable= $('#openingPlanTable').on( 'init.dt',function(){
    		//加载页面
    		$("#project_accept_panel_box").cgetContent("openingPlan/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,false);
			$("#openingPlanTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#openingPlanTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//修改监听方法
   			openMonitor();
   			setTimeout(function(){
   			    $("#openingPlanTable").DataTable().columns.adjust();
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
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '开通', className: 'btn-sm btn-query business-tool-btn openBtn hidden' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'openingPlan/queryGasProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});

                },
                dataSrc: 'data'
            },
            // ajax: 'projectjs/complete/json/opening_plan.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"gprojId",className:"none never"},
	  			{"data":"projNo"},
				{"data":"projName"},
				{"data":"gasProjStatusDes"},
                {"data":"gasProjStatus",
                    className:"none never",
                    "createdCell": function (td, cellData, rowData, row, col) {
                        if(cellData==$("#notThrough").val()){
                            $(td).parent().find("td").css("background", "rgb(255, 219, 219)");
                        }
                    }
                },
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
			var url = "#openingPlan/searchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#openingPlanTable_filter input").on("change",function(){
		var projNo = $("#openingPlanTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;
		$("#openingPlanTable").cgetData(true);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#openingPlanTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });

};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
    searchData = $("#searchForm_gasProject").serializeJson();
    searchData.projNo = $("#openingPlanTable_filter input").val();
    //列表重新加载
    $("#openingPlanTable").cgetData(true);
}

//开通监听方法
var openMonitor = function(){
	$(".openBtn").on("click",function(){
		if($("#openingPlanTable").find("tr.selected").length>0){
			$("#preparDeptDes").val($("#deptDes").val());
            $("#preparerDes").val($("#prepareDes").val());
			//切换可编辑状态
			$("#openingPlanForm").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
};

/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$("#businessOrderId").val(json.gprojId);
	$(".editbtn").addClass("hidden");
    $("#openingPlanForm").toggleEditState(false);
	// //参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	// detailFlag = false;
	t.cgetDetail('openingPlanForm','','',scaleDetailRefresh);
}

function scaleDetailRefresh(json){
	if (json.gasProjStatus=='3' || json.gasProjStatus=='0'){
		$(".openBtn").removeClass("hidden");
	}else {
		$(".openBtn").addClass("hidden");
	}
}

//计划列表
var handlePlanTable = function(){
	"use strict";
	if ($('#gasPlanTable').length !== 0) {
		gasPlanTable=$('#gasPlanTable').on( 'init.dt',function(){
		$("#project_accept_panel_box1").cgetContent("openingPlan/viewOpenPage");
		$(this).bindDTSelected(trSelectedBack1,true);
		$("#gasPlanTable_filter input").attr("placeholder","计划编号");
   		$('#gasPlanTable').hideMask();
            //查询
            searchMonotor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brftip',
            buttons: [
				{ text: '查询', className: 'btn-sm btn-query business-tool-btn planSearchBtn' }
            ],
            ajax: {
                url: 'gasPlan/queryGasPlan',
                type:'post',
                data: function(d){
                   	$.each(recordData,function(i,k){
                   		d[i] = k;
                   	});

                },
                dataSrc: 'data'
            },
            // ajax: 'projectjs/complete/json/opening_plan_list.json',
            select: true,  //支持多选
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            columns: [
                {"data":"gpId",className:"none never"},
                {"data":"gpNo"},
                {"data":"gpName"},
                {"data":"createDate"}
			],
			columnDefs: [{
                "targets": 0,
                "visible":false
            },{
                "targets":2,
                //长字符串截取方法
                render: $.fn.dataTable.render.ellipsis({
                    length: 15, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            }],
            ordering:false
        });
	}
}

//点击查询按钮
var searchMonotor=function(){
    $(".planSearchBtn").off("click").on("click",function(){
        var url = "#gasPlan/gasPlanSearchPopPage";
        var popoptions = {
            title: '查询',
            content: url,
            accept: searchDone1
        };
        //加载弹屏
        $("body").cgetPopup(popoptions);
    });

    $("#gasPlanTable_filter input").on("change",function(){
        recordData.gpNo = $("#gasPlanTable_filter input").val();
        //传入false  则不选中行
        $("#gasPlanTable").cgetData(true,tableCallBack); //列表重新加载
    });
    //基础条件查询添加回车事件
    $('#gasPlanTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
            $(this).change();
        }
    });
}

//查询点击确定
var searchDone1=function(){
    recordData = $("#searchForm_gasPlan").serializeJson();
    recordData.gpNo = $("#gasPlanTable_filter input").val();
    // searchData.projId = getProjectInfo().projId;
    //列表重新加载
    $("#gasPlanTable").cgetData(true,tableCallBack);
}
var tableCallBack=function(){
}

var gasPlanBack = function (json) {
    searchData.gpId = json.gpId;
    if($.fn.DataTable.isDataTable("#openingPlanTable")){
        $("#openingPlanTable").cgetData(false);//列表重新加载
    }else{
        handleOpeningPlan();
    }
}

var trSelectedBack1=function(v, i, index, t, json){
    t.cgetDetail('gasPlanForm','','',gasPlanBack(json));
}

var openingPlan = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handlePlanTable();
        		$("#projectTab,#planTab").on("shown.bs.tab",function(){
	        		if($(this).is($('#planTab'))){
	        			//计划列表
	        			if(!$.fn.DataTable.isDataTable('#gasPlanTable')){
	        				handlePlanTable();
	        			}else{
	        				$('#gasPlanTable').cgetData(true);
	        			}
                        $("#project_accept_panel_box1").removeClass("hidden");
                        $("#project_accept_panel_box").addClass("hidden");
	        		}else{
	        			//工程列表
	        			if(!$.fn.DataTable.isDataTable('#openingPlanTable')){
	        				handleOpeningPlan();
	        			}else{
	        				$('#openingPlanTable').cgetData(true);
	        			}
                        $("#project_accept_panel_box").removeClass("hidden");
                        $("#project_accept_panel_box1").addClass("hidden");
	        		}
	        	})
        	});
        }
    };
}();


