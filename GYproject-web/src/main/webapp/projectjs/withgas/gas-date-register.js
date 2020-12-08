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
var gasDateRegisterTable;
var scaleTable;
/**查询条件  默认待勘察*/
var searchData={},
	recordData={};
var accessoryData={};
var detailSearchData = {};
var handlegasDateRegister = function() {
	"use strict";
    if ($('#gasDateRegisterTable').length !== 0) {
    	gasDateRegisterTable= $('#gasDateRegisterTable').on( 'init.dt',function(){
    		//加载页面
    		$("#gas_date_register_panel_box").cgetContent("gasDateRegister/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#gasDateRegisterTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#gasDateRegisterTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
            //增加监听方法
            addMonitor();
   			//修改监听方法
   			updateMonitor();
   			setTimeout(function(){
   			    $("#gasDateRegisterTable").DataTable().columns.adjust();
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
                { text: '登记', className: 'btn-sm btn-query  business-tool-btn modifyBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
            // serverSide:true,
            ajax: {
                url: 'gasDateRegister/queryProject',
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
                {"data":"acceptType"},
                {"data":"acceptDate"},
				{"data":"gasProjStatusDes"},
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
			var url = "#gasDateRegister/searchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#gasDateRegisterTable_filter input").on("change",function(){
		var projNo = $("#gasDateRegisterTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;
		$("#gasDateRegisterTable").cgetData(true);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#gasDateRegisterTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });

};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
    searchData = $("#searchForm_gasProject").serializeJson();
    searchData.projNo = $("#gasDateRegisterTable_filter input").val();
    //列表重新加载
    $("#gasDateRegisterTable").cgetData(true);
}

//增加监听方法
var addMonitor = function(){
    $(".addBtn").on("click",function(){
        if($("#gasDateRegisterTable").find("tr.selected").length>0){
            $("#preparDeptDes").val($("#deptDes").val());
            $("#preparerDes").val($("#prepareDes").val());
            //切换可编辑状态
            $("#gasDateRegisterForm").toggleEditState(true);
            //维护按钮显示出来
            $(".editbtn").removeClass("hidden");
        }else{
            alertInfo('请选择要修改的记录！');
        }
    });
};

//开通监听方法
var updateMonitor = function(){
	$(".modifyBtn").on("click",function(){
		if($("#gasDateRegisterTable").find("tr.selected").length>0){
			$("#preparDeptDes").val($("#deptDes").val());
            $("#preparerDes").val($("#prepareDes").val());
			//切换可编辑状态
			$("#gasDateRegisterForm").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择记录信息！');
		}
	});
};

/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
    $(".editbtn").addClass("hidden");
    $("#gasDateRegisterForm").toggleEditState(false);
	// //参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	// detailFlag = false;
	t.cgetDetail('gasDateRegisterForm','','',scaleDetailRefresh(json));
}

function scaleDetailRefresh(json){
	
}



var gasDateRegister = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
                handlegasDateRegister();
        	});
        }
    };
}();


