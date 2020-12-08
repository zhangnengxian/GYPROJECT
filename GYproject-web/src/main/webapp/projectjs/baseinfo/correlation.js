var correlationTable;   //资料标准列表

var searchData={};
/**
 * 关联设置列表
 */
var handleCorrelation = function() {
	"use strict";
    if ($('#correlationTable').length !== 0) {
    	correlationTable= $('#correlationTable').on( 'init.dt',function(){
    		$("#correlationTable_filter input").attr("placeholder","相关信息");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#correlation_panel_box").cgetContent("correlation/correlationView");
   			//隐藏遮罩
   			$('#correlationTable').hideMask();
   			//回车监听
   			keySearch();
   			//修改监听
   			signMonitor();
   			//增加监听
   			insertMonitor();
   			//删除监听
   			deleteMonitor();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'fBrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '增加', className: 'btn-sm btn-query business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                { text: '删除', className: 'btn-sm btn-warn business-tool-btn deleteBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'correlation/queryCorrelation',
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
                {"data":"corId",className:"none never"},
                {"data":"corTypeDes"},
	  			{"data":"correlateInfoDes"},
	  			{"data":"correlatedInfoDes"}
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
			}]
        });
    }
};
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	$("#corType").val(json.corType);
	trSData.t && t.cgetDetail('correlationForm','','',callBackabc);
}
var callBack2 = function(){
	if($("#corType").val()=='3'){
		$(".contributionCode").removeClass("hidden");
	}else{
		$(".contributionCode").addClass("hidden");
	}
	
	$("#correlationForm").styleFit();
}
var callBackabc = function(){
	$("#correlateInfoId").empty();
	$("#correlatedInfoId").empty();
	var data = $("#corType").val();
	$.ajax({
		type:'post',
		url :'correlation/queryCorrelateInfoId?corType='+data,
		dataType:'json',
		success:function(data){
			$.each(data.correlationInfo,function(n,v){
				if(n=0){
					$("#correlateInfoId").append('<option value='+v.correlationInfoId+'>' + v.correlationInfoDes + '</option>');
					$("#correlateInfoId option[value='"+v.correlationInfoId+"']").attr("selected","selected");
				}
				$("#correlateInfoId").append('<option value='+v.correlationInfoId+'>' + v.correlationInfoDes + '</option>');
			});
			$.each(data.correlationedInfo,function(n,v){
				if(n=0){
					$("#correlatedInfoId").append('<option value='+v.correlationedInfoId+'>' + v.correlationedInfoDes + '</option>');
					$("#correlatedInfoId option[value='"+v.correlationedInfoId+"']").attr("selected","selected");
				}
				$("#correlatedInfoId").append('<option value='+v.correlationedInfoId+'>' + v.correlationedInfoDes + '</option>');
			});
			trSData.t && trSData.t.cgetDetail('correlationForm','','');
		},
		error: function(data){
			console.warn("关联信息选框查询失败");
		}
	})
	if($("#corType").val()=='3'){
		$(".contributionCode").removeClass("hidden");
	}else{
		$(".contributionCode").addClass("hidden");
	}
	$("#correlationForm").styleFit();
}
/**
 * 回车查询
 */
var keySearch=function(){
	$("#correlationTable_filter input").on("change",function(){
		var correlateInfoDes = $("#correlationTable_filter input").val();
		searchData.correlateInfoDes = correlateInfoDes;
		$("#correlationTable").cgetData(true);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#correlationTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
}


/**
 * 查询按钮监听方法
 */
var searchMonitor=function(){
	$('.searchBtn').on("click",function(){
		var url = "#correlation/correlationSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
		//基础条件查询添加监听
	});
}
var delBack=function(data){
	var len = $('#correlationTable').DataTable().ajax.json().data ? $('#correlationTable').DataTable().ajax.json().data.length : $('#correlationTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#correlationForm')[0].reset();
		 $(':input','#correlationForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }else{
		 $("#correlationTable").cgetData(true);  //列表重新加载
	 }
}
/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_correlation").serializeJson();
	//console.log(searchData);
	//列表重新加载
    $("#correlationTable").cgetData();  
};

//修改监听
var signMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		if($('#correlationTable').find('tr.selected').length>0){
			$('#correlationForm').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要修改的关联关系！');
		}
	});
};

//增加监听
var insertMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		//清除选中行样式
		$("#correlationForm input").val("");
		$("#projLtypeId").val("");
		//切换可编辑状态
		$("#correlationForm").toggleEditState(true);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
};

//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#correlationTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除关联关系？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的关联关系！',
				chide: 'true',
				accept: popClose
			});
		}
	});
};

//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	$('#correlationTable').cdeleteRow("corId","correlation/deleteCorrelation",delBack);
}

/**
 * 初始关联关系列表
 */
var correlation = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleCorrelation();
        }
    };
}();
