/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var annualPlanTable;
var scaleTable;
/**查询条件  默认待勘察*/
var searchData={};
var accessoryData={};
var detailSearchData = {};
var handleAnnualPlan = function() {
	"use strict";
    if ($('#annualPlanTable').length !== 0) {
    	annualPlanTable= $('#annualPlanTable').on('init.dt',function(){
    		//加载页面
    		$("#project_accept_panel_box").cgetContent("annualPlan/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#annualPlanTable_filter input").attr("placeholder","计划编号");
   			//隐藏遮罩
   			$('#annualPlanTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//修改监听方法
   			updateMonitor();
   			addMonitor();
   			//下载模板
   			checkOut();
   			//导入
   			importMonitor();
   			//删除监听
   			deleteMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '下载模板', className: 'btn-sm btn-query checkOut' }, 
                { text: '导入', className: 'btn-sm btn-query importBtn' }, 
                { text: '增加', className: 'btn-sm btn-query business-tool-btn addBtn'},
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn'},
                { text: '删除', className: 'btn-sm btn-warn business-tool-btn deleteBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'annualPlan/queryAnnualPlan',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});

                },
                dataSrc: 'data'
            },
            // ajax: 'projectjs/project/json/investment_manage.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
				{"data":"planId",className:"none never"},
                {"data":"planNo",responsivePriority: 1},
				{"data":"planName",responsivePriority: 2},
				{"data":"pipeDiameter"},
				{"data":"pipeLength",className:"text-right"},
				{"data":"finishLength",className:"text-right"},
				{"data":"completionSchedule"},
				{"data":"projectTypeDes"},
				{"data":"investmentScale",className:"text-right"},
				{"data":"alradyInvestment",className:"text-right"},
				{"data":"planInvestment",className:"text-right"},
				{"data":"constructionRatio"},
				{"data":"corpName"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":7,
				 "orderable":false
			}]
			// fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
			// 	if(aData.overdue){
			// 		$(nRow).addClass("expired-proect");
			// 	}
			// }
        });
    }
    
};

//弹屏监听方法
var searchMonitor = function(){
	
	//查询按钮弹出屏查询
	$(".searchBtn").on("click",function(){
			var popoptions = {
				title: '查询',
				content: "#annualPlan/searchPopPage",
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#annualPlanTable_filter input").on("change",function(){
		var planNo = $("#annualPlanTable_filter input").val();
		searchData = {};
		searchData.planNo = planNo;
		$("#annualPlanTable").cgetData(true);  //列表重新加载
		
	});
	//基础条件查询添加回车事件
	$('#annualPlanTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
    searchData = $('#searchForm_annualPlan').serializeJson();
    var planNo = $('#annualPlanTable_filter input').val();
    searchData.planNo = planNo;
    //列表重新加载
    $('#annualPlanTable').cgetData(true);

}

var addMonitor=function(){
	$(".addBtn").off().on("click",function(){
        $("#deptId option:first").prop("selected", 'selected');
        $("#deptId").change();

        $('#annualPlanTable').clearSelected();
		$("#annualPlanForm").formReset();
        $("#planId").val("");
        $("#corpName").val($("#deptName").val());
		$("#annualPlanForm").toggleEditState(true);
		$(".editbtn").removeClass("hidden");
	})
}

//修改监听方法
var updateMonitor = function(){
	$(".updateBtn").on("click",function(){
		if($("#annualPlanTable").find("tr.selected").length>0){
			//切换可编辑状态
			$("#annualPlanForm").toggleEditState(true);
            $("#deptName").val($("#corpName").val());
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
			$("#deptId").change();
		}else{
			alertInfo('请选择要修改的计划！');
		}
	});
};

var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	$(".informSaveBtn").addClass("hidden");
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	detailFlag = false;
	t.cgetDetail('annualPlanForm','','',scaleDetailRefresh);
}

var scaleDetailRefresh = function(data){
	// var json=trSData.annualPlanTable.json;
	// if(json){
	// 	$("#projNo").val(json.projNo);
	// 	$("#projName").val(json.projName);
	// 	$("#projAddr").val(json.projAddr);
	// }
}

//下载模板监听
var checkOut = function(){
	$(".checkOut").on("click",function(){
		console.info("xiazai");
		$("#exportExcel").submit();
	})
}


//年度计划导入
var importMonitor = function(){
	$(".importBtn").off("click").on("click",function(){
		$("body").cgetPopup({
			title: '文件导入',
			content: "#annualPlan/importPop?url=annualPlan/importExcel",
			accept: importOk2,
			nocache: true
		});
	});	
}


var importOk2 = function (){
	annualPlanTable.ajax.reload();
}

var annualPlan = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handleAnnualPlan();
        	});
        }
    };
}();



//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#annualPlanTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除年度计划？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的年度计划！',
				accept: popClose
			});
		}
	});
};


//删除---确定后
var deleteDone = function(){
	var planId=trSData.annualPlanTable.json.planId;
	$.ajax({
		type:'post',
		url:'annualPlan/deleteAnnualPlan',
		contentType: "application/json;charset=UTF-8",
        data: planId,
        success:function(data){
        	var content = "年度计划删除成功！";
        	if(data=="false"){
        		content = "年度计划删除失败！";
        	}else if(data=="already"){
        		content = "该计划已完成工程立项，无法删除！";
        	}
        	var myoptions = {
                	title: "提示信息",
                	content: content,
                	accept: importOk2,
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