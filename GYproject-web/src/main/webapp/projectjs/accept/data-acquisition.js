
var dataAcquisitionTable;
/**查询条件*/
var searchData={};
var accessoryData={};
var detailSearchData = {};
var handleDataAcquisition = function() {
	"use strict";
    if ($('#dataAcquisitionTable').length !== 0) {
    	dataAcquisitionTable= $('#dataAcquisitionTable').on( 'init.dt',function(){
    		//加载页面
    		$("#data_acquisition_panel_box").cgetContent("dataAcquisition/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#dataAcquisitionTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#dataAcquisitionTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//登记监听方法
   			registerMonitor();
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
                { text: '登记', className: 'btn-sm btn-query business-tool-btn registerBtn'}
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'dataAcquisition/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/accept/json/project_accept.json',
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
				{"data":"overdue",className:"none never" }
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
				"targets":4,
				 "visible":false
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
			var url = "#dataAcquisition/searchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#stateSelect,#dataAcquisitionTable_filter input").on("change",function(){
		var stateSelect = $("#stateSelect").find("option:selected");
		var projNo = $("#dataAcquisitionTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;
		$("#dataAcquisitionTable").cgetData(true);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#dataAcquisitionTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	searchData = $("#searchForm_designCommission").serializeJson();
	var projNo=$('#dataAcquisitionTable_filter input').val();
	searchData.projNo=projNo;
    $("#dataAcquisitionTable").cgetData(true,dataAcquisitionBack);  //列表重新加载
}

var dataAcquisitionBack=function(){
	var len = $('#dataAcquisitionTable').DataTable().ajax.json().data ? $('#dataAcquisitionTable').DataTable().ajax.json().data.length : $('#dataAcquisitionTable').DataTable().ajax.json().length;
 	if (len<1) {
 		// 维护按钮显示出来
 		 $('#projectDetailform')[0].reset();
		 $(':input','#projectDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
		 $(".editbtn").addClass("hidden");
		 $("#projectDetailform").toggleEditState(false);
 	} else {
 		$(".editbtn").addClass("hidden");
 	};
}
/**
 * 登记按钮监听方法
 */
var registerMonitor = function(){
	$('.registerBtn').off('click').on('click',function(){
		if($('#dataAcquisitionTable').find('tr.selected').length>0){
			//$("#backReason").attr("data-parsley-required","false");
			//切换可编辑状态
			$('#projectDetailform').toggleEditState(true);
			//维护按钮显示出来
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要登记的工程记录！');
		}
	});
};
var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	t.cgetDetail('projectDetailform','dataAcquisition/viewProject','',queryDetailBack);
}

var queryDetailBack = function(data){
	if($("#custName").val()==""){
		$(".noUser").addClass("hidden");
	}else{
		$(".noUser").removeClass("hidden");
	}
}

var dataAcquisition = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handleDataAcquisition();
        	});
        }
    };
}();



