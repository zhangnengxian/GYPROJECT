/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/

var mytable;
var searchData={};
var handleMeterManage = function() {
	"use strict";
    if ($('#metermanagetable').length !== 0) {
    	mytable= $('#metermanagetable').on( 'init.dt',function(){   			
    		// 打开右侧详述页面
    		$(".updateMeterBtn").attr("data-c","#meterManage/meterManageRight");
    		
    		// 参数true：绑定默认选中第一行，  trSelectedBack：绑定行选中事件
    		$(this).bindDTSelected(trSelectedCallBack,true);
    		
    		$("#metermanagetable_filter").append('<a id="meterPop" class="asBtn btn btn-default btn-sm m-l-10"> <i class="fa  fa-search-plus"></i></a>');

   			
   			$('#metermanagetable').hideMask();
   			
   			$("#meterPop").on("click",function(){
   				var url = '#meterManage/meterSearchPop';
   				var popoptions = {
   					title: '查询',
   					content: url,
   					accept: searchDone
   				};
   				$("body").cgetPopup(popoptions);
   			});
   			
//   			$(this).siblings().find(".import").after('<input type="file" name="files[]" class="hidden">');
//            $(this).siblings().find(".import").click(function(){
//            	$(this).removeClass("active").next("input").click();
//            });
   			
        }).DataTable({
        	 language: {
                 url: 'js/dt-chinese.json'
             },
            lengthMenu: [ 18 ],
            dom: 'Bfrtip',
           // searching:false,   不显示查询框
            buttons: [
                { text: '模板', className: 'btn-sm download' },
                { text: '导入', className: 'btn-sm import' },
                { text: '增加', className: 'btn-sm insertMeterBtn business-tool-btn' },
                { text: '修改', className: 'btn-sm updateMeterBtn business-tool-btn' }
               
            ],
          //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
           // ajax: 'expressjs/meter/json/meter_manage_demo.json',
            ajax: {
                url: 'meterManage/queryMeter',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
            },
            responsive: true,
            select: true,
            columns: [
	  			{"data":"meterId",className:"none never"}, //隐藏
				{"data":"meterNo"},
				{"data":"meterType"},
				{"data":"meterModel.manuId.manuName"},
				{"data":"meterModel.meterModel"},
				{"data":"meterStatusDes"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false,
			},
			{
				"targets": 2,
				/*
				 * render属性
				 * 方法携带四个参数
				 * data: 该单元格的原始数据，也就是默认显示的那些数据
				 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
				 * row: 但前行的所有原始数据
				 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
				 */
				"render": function ( data, type, row, meta ) {
					if(type === "display"){
						var icon = 0, trans = '未知';
						if(data == 11){
							icon = 1;
							trans = '普表';
						}else if(data == 12){
							icon = 2;
							trans = '卡表';
						}else if(data == 13){
							icon = 3;
							trans = '远传表';
						}else if(data == 14){
							icon = 4;
							trans = '金额表';
						}else if(data == 15){
							icon = 5;
							trans = '工业表';
						}else if(data == 16){
							icon = 6;
							trans = '代码表';
						}
						return '<span class="grid-icon metertype-icon"><img src="images/meter/metertype-' + icon + '.png"></span> ' + trans;
					}else if(type === "search" || type === "filter"){
						var trans = '未知';
						if(data == 11){
							trans = '普表';
						}else if(data == 12){
							trans = '卡表';
						}else if(data == 13){
							trans = '远传表';
						}else if(data == 14){
							trans = '金额表';
						}else if(data == 15){
							trans = '工业表';
						}else if(data == 16){
							trans = '代码表';
						}
						return trans;
					}else{
						return data;
					}
			    }
			}],
			order: [[ 1, "asc" ]]
        });
        
    }
};


/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	searchData = $("#searchForm_meter").serializeJson();
    $("#metermanagetable").cgetData();  //表具列表重新加载
    
}


/** 选中行时，查询详述
v：选中行（如果选中多行则取索引最小行）的所有单元格值的数组——————类型（选中行的所有数据）数组
*   i：选中行在datatable中的索引，数组对象，长度为1则表示选中单行————  为当前talbe的第多少条
*   index：选中行（如果选中多行则取索引最小行）在当前页的索引——————  当前页的索引
*   t：选中行（如果选中多行则取索引最小行）的jQuery对象——————tr对象
*   json：选中行（如果选中多行则取索引最小行）的所有关联数据对象（json）——————后台方法查询返回的所有数据
*  */
var trSelectedCallBack = function(v, i, index, t, json){
	$("#metermanageform").toggleEditState(false);
	$(".confirmBtn").addClass("hidden");
	
	t.cgetDetail('metermanageform','meterManage/viewMeter','',detailCallBack); 
	if ($('#metermanageform').length <1) {
		$("#meter_manage_panel_box").cgetPart($(".updateMeterBtn"));
	}
}

/**
 * 表具【增加】按钮增加点击事件，打开右侧增加页面
 */
$(document).on('click','.insertMeterBtn',function(){
	// 打开右侧表具增加页面
	$(".insertMeterBtn").attr("data-c","#meterManage/toInsertMeterRight");
	$("#meter_manage_panel_box").cgetPart($(".insertMeterBtn"));
	
});

/**
 * 表具增加屏， 表型选择完毕
 */
var meterModelSelectDone = function (){
	/*var meterModeTable =$('#mrTypePop').DataTable();
	var selectIndex =$('#mrTypePop tr.selected').index();
	
	var meterRange = meterModeTable.column(0).data()[selectIndex];    // 量程
	var manuId = meterModeTable.column(1).data()[selectIndex];        // 厂家ID
	var meterModelId = meterModeTable.column(2).data()[selectIndex];  // 表型号ID
	var meterType = meterModeTable.column(3).data()[selectIndex];     // 表类型
	var meterModeDes = meterModeTable.column(4).data()[selectIndex];  // 表型号描述
	var manuName = meterModeTable.column(5).data()[selectIndex];      // 厂家描述
	var meterTypeDes = meterModeTable.column(6).data()[selectIndex];  // 表类型描述
	 */	
	
	var meterModeTable =$('#mrTypePop').DataTable();
	var selectIndex = $('#mrTypePop tr.selected').index();
	var meterModelSelJson = meterModeTable.ajax.json()[selectIndex];
	
	var meterRange = meterModelSelJson['meterRange'];       // 量程
	var manuId = meterModelSelJson['manuId']['manuId'];     // 厂家ID  
	var meterModelId = meterModelSelJson['meterModelId'];   // 表型号ID
	var meterType = meterModelSelJson['meterType'];         // 表类型
	var meterModeDes = meterModelSelJson['meterModel'];     // 表型号描述
	var manuName = meterModelSelJson['manuId']['manuName']; // 厂家描述 meterModelSelJson.manuId.manuName
	var meterTypeDes = meterModelSelJson['meterTypeDes'];   // 表类型描述
	
	$('#meterRange').val(meterRange);
	$('#manuId').val(manuId);
	$('#meterModelId').val(meterModelId);
	$('#meterType').val(meterType);
	$('#meterModelDes').val(meterModeDes);
	$('#manuName').val(manuName);
	$('#meterTypeDes').val(meterTypeDes);
	
	if(meterType == '11'){
		$(".ic-only").addClass("hidden");      // IC卡相关数据项隐藏
	}
	if(meterType == '12'){
		$(".ic-only").removeClass("hidden");   // IC卡相关数据项显示
		$('#meterInsertForm').styleFit();
	}
}


/**
 * 增加完成后，重置表钢号
 */
var insertCallBack = function (){
	$('#meterNo').val('');
}

/**
 * 表具修改按钮增加点击事件：将可编辑数据项改为编辑状态，并显示【保存】和【放弃】按钮
 */
$(document).on('click','.updateMeterBtn',function(){
	// 打开右侧详述页面
	if ($('#metermanageform').length <1) {
		$("#meter_manage_panel_box").cgetPart($(".updateMeterBtn"));
	}
	$("#metermanageform").toggleEditState(true);
	$(".confirmBtn").removeClass("hidden");
});

var detailCallBack = function(){
	//$("#metermanageform").toggleEditState(true);
	//$(".confirmBtn").removeClass("hidden");
	var meterType = $('#meterType').val();
	
	if(meterType == $('#tradition').val()){
		$(".tradition").removeClass("hidden");
		$(".ic-only").addClass("hidden");
		$(".remote-only").addClass("hidden");
	}
	if(meterType == $('#card').val()){
		$(".tradition").addClass("hidden");
		$(".ic-only").removeClass("hidden");
		$(".remote-only").addClass("hidden");
	}
	if(meterType == $('#remote').val()){
		$(".tradition").addClass("hidden");
		$(".ic-only").addClass("hidden");
		$(".remote-only").removeClass("hidden");
	}
}

/**
 * 点击修改【保存】按钮
 */
$(document).on('click','.saveBtn',function(){
	$('#metermanageform').formSave('meterManage/updateMeter','metermanagetable',mytable );
});


/**
 * 放弃按钮点击后，可编辑框回退到不可编辑状态；隐藏【保存】和【放弃】按钮
 */
$(document).on('click','cancleBtn',function(){
	$("#metermanageform").toggleEditState(false);
	$(".confirmBtn").addClass("hidden");
});


var meterManage = function () {
	"use strict";
    return {
        //main function
    	init: function () {
        	handleMeterManage();
        }
    };
}();

//点击导出按钮展示的弹框
$(document).on("click",".download",function(){
	   
	   var url='#batcreate/meterTypePop';
	    var meterModelPop = {
	    		title: '表具型号选择',
	    		content: url,
	    		accept: 'noExcelBack'
	    };
	    $("body").cgetPopup(meterModelPop);
	   
	    });

var noExcelBack=function(){
	var mindex=$('#mrTypePop tr.selected').index();
	    var meterModelId=$("#mrTypePop").cgetColumnValue("meterModelId", mindex);
		var meterModel=$("#mrTypePop").cgetColumnValue("meterModel", mindex);
		var manuName=$("#mrTypePop").cgetColumnValue("manuId", mindex).manuName;
		var meterRange=$("#mrTypePop").cgetColumnValue("meterRange", mindex);
		var meterTypeDes=$("#mrTypePop").cgetColumnValue("meterTypeDes", mindex);
		var modelStr="表型号："+meterModel+" 表类型："+meterTypeDes+" 厂商："+manuName+" 量程："+meterRange;
		$("#speValue").val(meterModelId);

	    $("#modelDes").val(modelStr);
	 $("#exportForm").submit();

};


$(document).on("click",".import",function(){
	var popoptions = {
			title: '文件导入',
			content: "#meterBatInstall/importPop?url=meterManage/importExcel",
			accept: importOk
		};
		$("body").cgetPopup(popoptions);
});

var importOk = function(){
	mytable.ajax.reload();
	
	
}