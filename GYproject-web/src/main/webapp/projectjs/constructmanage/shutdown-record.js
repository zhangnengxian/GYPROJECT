var searchData={},shutdownRecordTable,
	recordData={};
//是否工程目前处于停工状态
var isShutdownStaus=1;
//整改列表
var handleShutdownRecord = function() {
	"use strict";
	searchData.projId = getProjectInfo().projId;
    if ($('#shutdownRecordTable').length !== 0) {
    	shutdownRecordTable=$('#shutdownRecordTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//隐藏遮罩
   			$('#shutdownRecordTable').hideMask();
   			//弹屏查询
   	    	searchMonitor();
   			//增加监听
   			addMonitor();
   			//修改监听
   	    	modifyMonitor();
   	    	//加载打印屏
  		    showReport();
  		    //推送
  		    pushMonitor();
  		//删除监听
   	    	delMonitor();
  		    queryCheckRole();
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
            dom: 'Brtip',
           // dom: 'Brt',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '增加', className: 'btn-sm btn-add checkRole addBtn  business-tool-btn' },
                { text: '修改', className: 'btn-sm btn-update checkRole updateBtn business-tool-btn' },
                { text: '推送', className: 'btn-sm btn-push checkRole pushBtn hidden business-tool-btn' },
                { text: '删除', className: 'btn-sm btn-warn checkRole business-tool-btn delBtn'}
            ],
          //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'shutdownRecord/queryShutdownRecord',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
           // ajax: 'projectjs/constructmanage/json/shutdown_record.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
      			{"data":"sdrId",className:"none never"},
      			{"data":"sdrNo"},
      			{"data":"sdrProcess"},
      			{"data":"createDate"},
				{"data":"sdrType"},
				{"data":"sdrStatus"}
				],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},{
				"targets": 4,
				"render":function(data,type,row,meta){
					if(data=='1'){
						return "停工";
					}else{
						return "复工";
					}
					/*if(data=='0'){
						var html = "<input type='button' class='btn btn-xs btn-primary' value='复工令' onclick='return shutdowmReWork(" +row.sdrId+ ");' />";
						html += "  <input type='button' class='btn btn-xs btn-primary' value='复工申报' onclick='return shutdowmAproval(" +row.sdrId+ ");' />";
						return html;
					}else{
						return "复工";
					}*/
				}
			},{
				"targets": 5,
				"render":function(data,type,row,meta){
					if(data=='1'){
						return "待下达停工令";
					}else if(data=='2'){
						return "待复工报审";
					}else if(data == '3'){
						return "复工报审中";
					}else if(data == '4'){
						return "待复工";
					}else if(data=='5'){
						return "待下达复工令";
					}else if(data=='6'){
						return "已复工";
					}
					
				}
			},{
				"targets":4,
				 "orderable":false
			},{
				"targets":5,
				 "orderable":false
			}],
        });
    	
    }
}
//删除监听
var delMonitor = function(){
	$(".delBtn").off("click").on("click",function(){
		var len=$("#shutdownRecordTable").find("tr.selected").length;
	if(len > 0){
		var sdrId = trSData.shutdownRecordTable.json.sdrId;
		var param = {};
		param.sdrId = sdrId;
		$("#shutdownRecordTable").loadMask("删除中···",1,0.5);
		$.ajax({
            url: "shutdownRecord/deleteSDRecordBySDRId",
            type: "POST",
            timeout : 5000,
            contentType: "application/json;charset=UTF-8",
            data:  JSON.stringify(param),
            success: function (data) {
            	$("#shutdownRecordTable").hideMask();
            	if(data === "false"){
					alertInfo("删除失败！");
				}else{
					alertInfo("删除成功！");
					$("#shutdownRecordTable").hideMask();
					$('#shutdownRecordTable').cgetData();
				}
            	
            }
        });
	}else{
		$("body").cgetPopup({
			title: '提示',
			content: '请选中记录!',
			ahide:true,
			atext:'确定'
		});
	}
	});
}

//增加监听
var addMonitor = function(){
	$(".addBtn").off("click").on("click",function(){
		$('#shutdownInfo').tab('show');
		$('#shutDownForm').toggleEditState(true);
		$(".editbtn").removeClass("hidden");
		var len=$("#shutdownRecordTable").find("tr.selected").length;
		if(len>0){
			//可选择增加停复工令
			recordData=trSData.shutdownRecordTable.json;
			console.info(recordData);
			if(recordData.sdrType==1 && recordData.sdrStatus==4){
				//增加复工令
				$("#sdrType").val("2");
				$(".reWorkDate").removeClass("hidden");
				$(".cuManagerDept").toggleEditState(false);
				$(".shutdownfield").toggleEditState(false);
				$("#createDate").val('');	 //清空
				$("#sdrId").val('');		//清空
			}else{
				//clearRecordData();
				$("#sdrType").val("1");
				$('.addClear').val("");
				$(".reWorkDate").addClass("hidden");
				$("#sdrNo").val('');
			}
			$(".sdrType").toggleEditState(false);
		}else{
			//只能增加停工令
			recordData={};
			$('.addClear').val("");
			$(".reWorkDate").addClass("hidden");
			$("#sdrNo").val('');
			
			$(".sdrType").toggleEditState(false);
		}
		
		$("#signBtn_1,#signBtn_2").resetSign();
		showReport();
		
	});
}
//修改监听
var modifyMonitor = function(){
	$(".updateBtn").off("click").on("click",function(){
		var len=$("#shutdownRecordTable").find("tr.selected").length;
		if(len>0){
			recordData=trSData.shutdownRecordTable.json;
			if(recordData.sdrType=='1' && recordData.sdrStatus>1){
				alertInfo("该停工令已下达,不可修改！");
				return;
			}
			$('#shutDownForm').toggleEditState(true);
			$(".editbtn").removeClass("hidden");
			if(recordData.sdrType=='2'){
				if(recordData.sdrStatus>5){
					alertInfo("该复工令已下达,不可修改！");
					return;
				}
				 $(".reWorkDate").removeClass("hidden");
	   			 $(".shutdownfield").toggleEditState(false);
	   			 showShutdownData();
			}else{
				$(".reWorkDate").addClass("hidden");
	   			$('#shutDownForm').toggleEditState(true);
			}
			$('#shutdownInfo').tab("show");
			$(".sdrType").toggleEditState(false);
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的整改条目!',
				ahide:true,
				atext:'确定'
			});
		}
	});
};
//推送监听
var pushMonitor = function(){
	$(".pushBtn").off("click").on("click",function(){
		var len=$("#shutdownRecordTable").find("tr.selected").length;
		if(len>0){
			var json=trSData.shutdownRecordTable.json;
			var dataJson={};
			dataJson.sdrId = json.sdrId;
			var response = $.ajax({
                url: "shutdownRecord/pushShutdownRecord",
                type: "POST",
                timeout : 5000,
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(dataJson),
                success: function (data) {
                	if(data === "false"){
						alertInfo("数据保存失败！");
					}else{
						alertInfo("数据保存成功！");
					}
					$('#shutdownRecordTable').cgetData();
                }
            });
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要推送的条目!',
				ahide:true,
				atext:'确定'
			});
		}
	});
}
/**
 * 查询弹屏监听方法
 */
var searchMonitor=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#shutdownRecord/viewSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
}
/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_shutdownRecord").serializeJson();
	searchData.projId = $('#projId').val();
	//列表重新加载
    $("#shutdownRecordTable").cgetData(true,refreshcallBack); 
}
/**
 * 重新加载列表
 */
var refreshcallBack = function(){
	var len = $('#shutdownRecordTable').DataTable().ajax.json().data ? $('#shutdownRecordTable').DataTable().ajax.json().data.length : $('#shutdownRecordTable').DataTable().ajax.json().length;
	console.info("refreshcallBack=="+len);
	if(len<1){
		 //清空右侧记录
		 //$('#shutdownRecordTable')[0].reset();
		 $('#shutDownForm .addClear').val('');
		 //$(':input','#shutdownRecordTable').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
		 $(".editbtn").addClass("hidden");
		// $("#shutDownForm").toggleEditState(false);
	 }else{
		 $(".editbtn").addClass("hidden");
	 }
	//清空签字
	$('.clear-sign').click();
	 showReport();
}

//保存信息
$('.saveCustomer').off().on("click",function(){
	//如果保存成功
   	alertInfo("数据保存成功！");
	
	$('.editbtn').addClass("hidden");
	
	$('#shutdownRecordTable').cgetData();
	
	$("#shutDownForm").toggleEditState(false);
	
});

//点击放弃
$('.giveUpSave').off().on('click',function(){
	$("#shutDownForm").toggleEditState(false);
	$('ul.nav>li').removeClass("active");
	$('#shutdownRecordList').click();
});

/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	t.cgetDetail('shutDownForm','shutdownRecord/viewShutdownRecord','',queryBack);
	//传false表示不可编辑
	$("#shutDownForm").toggleEditState(false);
	$(".editbtn").addClass("hidden");
}

var queryBack=function(data){
	if((data.sdrType==1 && data.sdrStatus<=1) || (data.sdrType==2 && data.sdrStatus<=5)){
		$('.pushBtn').removeClass("hidden");
	}else{
		$('.pushBtn').addClass("hidden");
	}
	//删除按钮对管理员可见，其他情况只有待推送停工令时可见
	var post = $("#loginPost").val();
	if(post.indexOf('999')>=0){
		$('.delBtn').removeClass("hidden");  //显示删除按钮
	}else{
		$('.delBtn').addClass("hidden");  //隐藏删除按钮
		if(data.sdrStatus == '1'){
			$('.delBtn').removeClass("hidden");  //显示删除按钮
		}
	}
		
	//解决日期带微妙问题
	mysqlDateTimeStr();
	showReport();
}

//列表页签
$('#shutdownRecordList').on('shown.bs.tab',function(){
	$(".editbtn").addClass("hidden");
	$('#shutdownRecordTable').cgetData();
});

//加载复工报审屏
function shutdowmAproval (data){
	$("#ajax-content").cgetContent("shutdownRecord/shutdownAproval",data);
}

/**
 * 下复工令监听
 */
function shutdowmReWork(data){
	$("#sdrType").val("1");
	$('#shutDownForm').toggleEditState(false);
	$(".editbtn").removeClass("hidden");
	$('#shutdownInfo').tab("show");
	$(".reWorkDate").show();
	$(".reWorkDate").toggleEditState(true);
	$(".sign").toggleEditState(true);
	$(".noticeDate").toggleEditState(true);
	$("#signBtn_1,#signBtn_2").resetSign();
}
//页面加载
var shutdownRecord =  function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleShutdownRecord();
        	});
        	
        	$('[href="#shutdown_info"]').on("show.bs.tab", function(){
        	    $("#shutdownRecordTable").styleFit();
        	    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4").handleSignature();
        	});
        	$("#shutdownRecordList,#shutdownInfo").on("shown.bs.tab",function(){
        		if($(this).is($("#shutdownRecordList"))){
    				if(!$.fn.DataTable.isDataTable('#shutdownRecordTable')){
    					handleRectifyNotice();
        			}else{
        				//加载打印屏
        				//showReport();
        			}
        		}else{
        			//加载打印屏
        			 showReport();
        		}
        	});
        }
    };
}();
