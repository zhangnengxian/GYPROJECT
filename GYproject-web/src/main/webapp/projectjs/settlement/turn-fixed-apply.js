/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var turnFixedApplyTable;
var searchData={};
var handelTurnFixedApply = function() {
	"use strict";
    if ($('#turnFixedApplyTable').length !== 0) {
    	turnFixedApplyTable= $('#turnFixedApplyTable').on( 'init.dt',function(){
    		//加载页面
    		$("#project_accept_panel_box").cgetContent("turnFixedApply/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#turnFixedApplyTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#turnFixedApplyTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//申请监听方法
   			applyMonitor();
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
                url: 'turnFixedApply/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/settlement/json/turn_fixed.json',
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
				{'data':'projStatusDes'},
				{"data":"settlementDate"},
				{"data":"signBack",
					className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData==$("#notThrough").val()){
							$(td).parent().children().css("background", "rgb(255, 219, 219)");
							//$(td).attr("id",row);
						}
					}
					},
			 {"data":"overdue",className:"none never"}
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
			var url = "#turnFixedApply/projectSearchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#turnFixedApplyTable_filter input").on("change",function(){
		var projNo = $("#turnFixedApplyTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;
		$("#turnFixedApplyTable").cgetData(true,turnFixedApplyTableCallBack);  //列表重新加载
		
	});
	//基础条件查询添加回车事件
	$('#turnFixedApplyTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	searchData = $('#searchForm_turnFixedApply').serializeJson();
	var projNo = $('#turnFixedApplyTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#turnFixedApplyTable').cgetData(true,turnFixedApplyTableCallBack);  
}
var turnFixedApplyTableCallBack = function(){
	var len = $('#turnFixedApplyTable').DataTable().ajax.json().data ? $('#turnFixedApplyTable').DataTable().ajax.json().data.length : $('#turnFixedApplyTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#turnFixedApplyForm').formReset();
		 //$(':input','#turnFixedApplyForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
		 $(".editbtn").addClass("hidden");
		 $("#turnFixedApplyForm").toggleEditState(false);
	 }else{
		 $(".editbtn").addClass("hidden");
	 }
}
//申请监听方法
var applyMonitor = function(){
	$(".applyBtn").on("click",function(){
		if($("#turnFixedApplyTable").find("tr.selected").length>0){
			//切换可编辑状态
			//$("#turnFixedApplyForm").toggleEditState(true);
			getSignStatusByPostId($("#loginPost").val(),"turnFixedApplyForm")
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要申请的记录！');
		}
	});
};
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('turnFixedApplyForm','turnFixedApply/viewTurnFixedApply','',scaleDetailRefresh);
}
var scaleDetailRefresh = function(data){
	//施工员登录，隐藏推送按钮
	if($("#loginPost").val().indexOf($("#treasurer").val())>=0){
		$(".pushBtn").removeClass("hidden");
	}else{
		$(".pushBtn").addClass("hidden");
	}
}
var turnFixedApply = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handelTurnFixedApply();
        	});
        }
    };
}();