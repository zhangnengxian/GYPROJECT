var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var searchData={},
    jointSearchData={}; //查询条件
jointSearchData.projId="-1";
/**
 * 加载工程列表
 */
var handleOneStopAcceptance = function() {
	"use strict";
    if ($('#oneStopAcceptanceTable').length !== 0) {
    	$('#oneStopAcceptanceTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$(".tabs-mixin").cgetContent("oneStopAcceptance/viewPage");
   			
   			//隐藏遮罩
   			$('#oneStopAcceptanceTable').hideMask();
   			$("#oneStopAcceptanceTable_filter input").attr("placeholder","工程编号");
       
   		//绑定单击事件 弹出搜索框
   			searchMonitor();
   		//推送单击事件监听
   			pushMonitor();
   			setTimeout(function(){
   			    $("#oneStopAcceptanceTable").DataTable().columns.adjust();
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
                { text: '推送', className: 'btn-sm btn-query business-tool-btn m-l-5 pushButton' }
                ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'oneStopAcceptance/queryJointAcceptancer',
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
				{"data":"overdue",className:"none never"}
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
        var url = "#oneStopAcceptance/projectSearchPopPage";
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});

	// 基础条件查询添加监听
	$('#oneStopAcceptanceTable_filter input').on('change', function() {
		var projNo = $('#oneStopAcceptanceTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#oneStopAcceptanceTable').cgetData(true,tableCallBack); // 列表重新加载
	});
	// 基础条件查询添加回车事件
	$('#oneStopAcceptanceTable_filter input').on('keyup', function(event) {
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
	var projNo = $('#oneStopAcceptanceTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#oneStopAcceptanceTable').cgetData(true,tableCallBack);  
},

/**
 * 选中行时，查询详述
 */
trSelectedBack = function(v, i, index, t, json){
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('oneStopAcceptanceForm','','',tableCallBack);

    jointSearchData.projId=json.projId;
    if($.fn.DataTable.isDataTable('#oneStopAcceptanceListTable')){
        $('#oneStopAcceptanceListTable').cgetData(false);
    }else{
        handleOneStopAcceptanceList();
    }
},

tableCallBack = function(){
	var len = $('#oneStopAcceptanceTable').DataTable().ajax.json().data ? $('#oneStopAcceptanceTable').DataTable().ajax.json().data.length : $('#oneStopAcceptanceTable').DataTable().ajax.json().length;
};

/**
 * 加载右屏联合验收列表
 */
var handleOneStopAcceptanceList = function(){
	"use strict";
    if ($('#oneStopAcceptanceListTable').length !== 0) {
    	$('#oneStopAcceptanceListTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trListSelectedBack,true);
    		//隐藏遮罩
   			$('#oneStopAcceptanceListTable').hideMask();
   			//增加监听
   			addMonitor();
   			//修改监听
   	    	modifyMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
           // dom: 'Brtip',
            dom: 'Brt',
            buttons: [
                { text: '增加', className: 'btn-sm btn-query checkRole addBtn' },
                { text: '修改', className: 'btn-sm btn-query checkRole updateBtn business-tool-btn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'oneStopAcceptance/queryJointList',
                type:'post',
                data: function(d){
                   	$.each(jointSearchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            // ajax: 'projectjs/complete/json/joint-acceptance-list.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
      			{"data":"jaId",className:"none never"},
      			{"data":"jaDate"},
      			{"data":"jaClum"},			
				],
			columnDefs: [{
				"targets": 0,
				"visible":false
			}],
        });
    }
}

/**
 * 右屏列表选中行
 */
var trListSelectedBack = function(v, i, index, t, json){
	//将查询详述显示到相应的输入框内
	t.cgetDetail('oneStopAcceptanceForm','','',queryBack);
	//传false表示不可编辑
	$("#oneStopAcceptanceForm").toggleEditState(false);
	$(".editbtn").addClass("hidden");
}

var queryBack = function(){
	
}
/**
 * 右屏推送监听
 */
var pushMonitor = function(){
	  //点击推送
    $(".pushButton").off().on("click",function(){
    	if($('#oneStopAcceptanceTable').find('tr.selected').length>0){
    		acceptDone();
    	}else{
    		alertInfo("请选择验收工程！");
    	}
    	
    });
}
/**
 * 右屏增加监听
 */
var addMonitor = function(){
	$(".addBtn").off("click").on("click",function(){
        $("#oneStopAcceptanceForm").formReset()
		$('#jointAcceptanceInfo').tab('show');
		$('#oneStopAcceptanceForm').toggleEditState(true);
		$(".editbtn").removeClass("hidden");
        $('#oneStopAcceptanceListTable').clearSelected();
		$("#jaId").val("");
		$(".field-editable").val("");

	});
};

/**
 * 右屏修改监听
 */
var modifyMonitor = function(){
	$('.updateBtn').off('click').on('click',function(){
		if($('#oneStopAcceptanceListTable').find('tr.selected').length>0){
			//维护按钮显示出来
			$('.editbtn').removeClass('hidden');
			$('#jointAcceptanceInfo').tab("show");
			//切换可编辑状态
			$('#oneStopAcceptanceForm').toggleEditState(true);
		}else{
			alertInfo('请选择验收记录！');
		}
	});
}

//列表页签
$('#jointAcceptanceList').on('shown.bs.tab',function(){
	$(".editbtn").addClass("hidden");
	$('#oneStopAcceptanceListTable').cgetData();
});
$('#jointAcceptanceInfo').on('shown.bs.tab',function(){
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    //不可编辑
    $("#oneStopAcceptanceForm").toggleEditState(false);
});

/**
 * 初始化工程列表
 */
var oneStopAcceptance = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handleOneStopAcceptance();
        	});
        }
    };
}();

var jointAcceptanceList = function(){
    "use strict";
    return {
        //main function
        init: function () {
            $.getScript("js/ellipsis.js").done(function(){
                handleOneStopAcceptanceList();
            });
            $('[href="#jointAcceptance_info"]').on("show.bs.tab", function(){
                $("#oneStopAcceptanceForm").styleFit();
                $(".fieldPrincipal-a").handleSignature();
            });
            $("#jointAcceptanceList,#jointAcceptance_info").on("shown.bs.tab",function(){
                if(!$(this).is($("#jointAcceptanceList"))){
                    handleOneStopAcceptanceList();
                }else{
                    $('#oneStopAcceptanceListTable').cgetData(false);
				}
            });
        }
    };
}();