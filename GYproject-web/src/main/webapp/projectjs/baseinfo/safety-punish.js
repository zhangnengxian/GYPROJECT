var mytable,       //大类列表
    searchData={},//大类查询条件
    safetyPunishMinTable,//大类列表
    mSearchData={};//小类查询条件
/**
 * 加载安全细则大类列表
 */
var handleSafetyPunish = function() {
	'use strict';
    if ($('#safetyPunishTable').length !== 0) {
    	mytable= $('#safetyPunishTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		$("#safetyPunishTable_filter input").attr("placeholder","细则编号");
   			//加载右侧页面
   			$('#safetyPunish_establish_panel_box').cgetContent('safetyPunish/viewSafetyPunishPage');
   			//隐藏遮罩
   			$('#safetyPunishTable').hideMask();
   			//增加监听
   			insertMonitor();
   			//修改监听
   			updateMonitor();
   			//删除监听
   			delMonitor();
   			//查询监听
   			searchPop();
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '增加', className: 'btn-sm btn-query  business-tool-btn addBtn ' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn ' },
                { text: '删除', className: 'btn-sm btn-query business-tool-btn delBtn' }
             ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'safetyPunish/querySafetyPunish',
                type:'POST',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
             },
             //ajax: 'projectjs/design/json/survey_result_register.json',
             responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
             select: true,  //支持多选
             columns: [
					{"data" : "id"}, 
					{"data" : "des"}
			   ]
        });
    }
},

/**
 * 选中，查详述
 */
 trSelectedBack =function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	$('#safetyPunishform,#safetyPunishMinform').toggleEditState(false);
	// 参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	trSData.t.cgetDetail('safetyPunishform', 'safetyPunish/viewSafetyPunish', '',queryBack);
	mSearchData.id=json.id;
	//是否初始化过
	if($.fn.DataTable.isDataTable('#safetyPunishMinTable')){
		//初始化过
		$("#safetyPunishMinTable").cgetData();//列表重新加载
	}else{
		SafetyPunishMin();
	}
};
var queryBack = function(data){
	$("#typeChild").val(data.type);
}
//增加监听方法
var insertMonitor = function(){
	
	$(".addBtn").on("click",function(){
//		 serverSide:false,
		$("#tempId").val("0");
		 $.ajax({
             type: 'POST',
             url: 'safetyPunish/querySafetyPunishAdd',
             data: 'id=',
             dataType: 'json',
             success: function (data) {
            	 $("#safetyPunishform")[0].reset();
            	 $('#spId').val(data);
            	 //可编辑
         		$('#safetyPunishform').toggleEditState(true);
         		//移除选中
         		$('#safetyPunishTable tr.selected').removeClass("selected");
         		$("#safetyPunishMinform")[0].reset();
         		//维护按钮显示出来
         		$(".editbtn").removeClass("hidden");
//         		$('#safetyPunishMinTable').rows().remove().draw();
         		mSearchData.id=data;
         		$("#safetyPunishMinTable").cgetData(true);//列表重新加载
        		$(".updateDetermine").addClass("hidden");
        		$(".addDetermine").addClass("hidden");
        		$(".cancelDetermine").addClass("hidden");
        		$(".addBtns").removeClass("hidden");
        		$(".updateBtns").removeClass("hidden");
        		$(".delBtns").removeClass("hidden");
             },
             error: function (jqXHR, textStatus, errorThrown) {
            	 console.warn("失败！");
             }
         });
		
	});
}


/**
 * 安全细则大类下属小类初始化
 */
var SafetyPunishMin = function() {
	"use strict";
	//
	if (!$.fn.DataTable.isDataTable('#safetyPunishMinTable')) {
		safetyPunishMinTable = $('#safetyPunishMinTable').on('init.dt',
				function() {
					// 默认选中第一行
					$(this).bindDTSelected(trSelectedBacks, true);
					// 隐藏遮罩
					$('#safetyPunishMinTable').hideMask();
					//修改监听
					updateSm();
					//增加监听
					addSmData();
					//删除监听
					delSm();
				}).DataTable({
			language : language_CN,
			lengthMenu : [ 18 ],
			dom : 'Brt',
			buttons : [ 
			       {text : '增加',className : 'btn-sm btn-query business-tool-btn addBtns'}, 
                   {text : '修改',className : 'btn-sm btn-query business-tool-btn updateBtns'}, 
			       {text : '删除',className : 'btn-sm btn-query business-tool-btn delBtns'} 
                      ],
			// 启用服务端模式，后台进行分段查询、排序
			//serverSide : true,
			ajax : {
				url : 'safetyPunish/querySafetyPunishMin',
				type : 'post',
				data : function(d){
                   	$.each(mSearchData,function(i,k){
                   		d[i] = k;
                   	});
                   	$(".addBtns").addClass("hidden");
            		$(".updateBtns").addClass("hidden");
            		$(".delBtns").addClass("hidden");
                },
				dataSrc : 'data'
			},
			responsive : { // 自适应
				details : {
					renderer : function(api, rowIdx, columns) {
						return renderChild(api, rowIdx, columns);
					}
				}
			},
			select : true, // 支持多选
			columns : [ 
			        {"data" : "id",responsivePriority : 1}, 
			        {"data" : "des",responsivePriority : 3},
			        {"data" : "deduction",responsivePriority : 2},
			        {"data" : "remark"}
			            ],
			columnDefs : [ {
					"targets":1,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
			} ]
		});
	}
};

/**
 * 小项增加按钮监听方法(1)
 */
var addSmData = function(){
	$(".addBtns").off("click").on("click", function() {
		$("#deduction").val("");
		$("#remark").val("");
		if($("#safetyPunishTable").find("tr.selected").length>0){
			var tempId = $("#tempId").val();
			if(tempId == "0"){
				var id = $("#spId").val();
				$.ajax({
					type: 'POST',
					url: 'safetyPunish/querySmId',
					data: id,
					contentType: "application/json;charset=UTF-8",
					success: function (data) {
						console.info(2018,data);
						$("#safetyPunishMinform")[0].reset();
						$('#ids').val(data);
						//可编辑
						$('#safetyPunishMinform').toggleEditState(true);
						//移除选中
		         		$('#safetyPunishMinTable tr.selected').removeClass("selected");
						//维护按钮显示出来
						$(".addDetermine").removeClass("hidden");
						$(".cancelDetermine").removeClass("hidden");
						//其余按钮隐藏
						$(".updateDetermine").addClass("hidden");
					},
					error: function (jqXHR, textStatus, errorThrown) {
		           	 console.warn("失败！");
		            }
				});
			}else{
				tempId++;
				$('#ids').val(tempId);
				//可编辑
				$('#safetyPunishMinform').toggleEditState(true);
				//移除选中
	     		$('#safetyPunishMinTable tr.selected').removeClass("selected");
				//维护按钮显示出来
				$(".addDetermine").removeClass("hidden");
				$(".cancelDetermine").removeClass("hidden");
				//其余按钮隐藏
				$(".updateDetermine").addClass("hidden");
			}
		}else{
			var tempId = $("#tempId").val();;
			if(tempId == "0"){
				tempId = $('#spId').val() + "00";
			}
			tempId++;
			$('#ids').val(tempId);
			//可编辑
			$('#safetyPunishMinform').toggleEditState(true);
			//移除选中
     		$('#safetyPunishMinTable tr.selected').removeClass("selected");
			//维护按钮显示出来
			$(".addDetermine").removeClass("hidden");
			//其余按钮隐藏
			$(".updateDetermine").addClass("hidden");
		}
	});
}
/*var addSmData = function(){
	$(".addBtns").off("click").on("click", function() {
		$("#safetyPunishMinform")[0].reset();
		$('#ids').val(data);
		//可编辑
		$('#safetyPunishMinform').toggleEditState(true);
		//移除选中
		$('#safetyPunishMinTable tr.selected').removeClass("selected");
		//维护按钮显示出来
		$(".addDetermine").removeClass("hidden");
	});
}*/

/**
 * 修改按钮监听方法
 */
var updateMonitor = function() {
	$(".updateBtn").off("click").on("click", function() {
		$("#tempId").val("0");
		if($("#safetyPunishTable").find("tr.selected").length>0){
		// 表单可编辑
		$("#safetyPunishform").toggleEditState(true);
		// 按钮显示
		$(".editbtn").removeClass("hidden");
		$(".updateDetermine").addClass("hidden");
		$(".addDetermine").addClass("hidden");
		$(".cancelDetermine").addClass("hidden");
		$(".addBtns").removeClass("hidden");
		$(".updateBtns").removeClass("hidden");
		$(".delBtns").removeClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
};


/**
 * 细则小类修改监听方法
 */
var updateSm = function(){
	$(".updateBtns").off("click").on("click", function() {
		if($("#safetyPunishMinTable").find("tr.selected").length>0){
			// 表单可编辑
			$("#safetyPunishMinform").toggleEditState(true);
			$(".updateDetermine").removeClass("hidden");
			$(".cancelDetermine").removeClass("hidden");
			//其余按钮隐藏
			$(".addDetermine").addClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
	}); 
}


/**
 * 删除按钮监听方法
 */
var delMonitor = function() {	
	$(".delBtn").on("click", function() {
		if($("#safetyPunishTable").find("tr.selected").length>0){
			// 加载弹屏
			$("body").cgetPopup({
				title : '删除',
				content : '确认删除？',
				accept : delDone
			});
		}else{
			// 加载弹屏
			$("body").cgetPopup({
				title : '删除',
				content : '请选择要删除的项！',
				accept : deleteDone
			});
		}
	})
};

/**
 * 小项删除
 */
var delSm = function(){
	$(".delBtns").on("click", function() {
		if($("#safetyPunishMinTable").find("tr.selected").length>0){
			// 加载弹屏
			$("body").cgetPopup({
				title : '删除',
				content : '确认删除？',
				accept : delSmDone
			});
		}else{
			// 加载弹屏
			$("body").cgetPopup({
				title : '删除',
				content : '请选择要删除的项！',
				accept : deleteDone
			});
		}
	})
};

var delDone=function(){
	var tableData = trSData.safetyPunishTable.json.id;
    $.ajax({
    	type: 'POST',
		url: 'safetyPunish/delSafetyPunish',
		data: {id : tableData},
		dataType: 'json',
		success: function (data) {
			// 列表重新加载
		    $("#safetyPunishTable").cgetData();
		},
		error: function (jqXHR, textStatus, errorThrown) {
          	 console.warn("失败！");
        }
    });
}

var delSmDone = function(){
	var row=$("#safetyPunishMinTable").DataTable().row( '.selected' ).remove().draw();	
	var arr=[];
	var json=$("#safetyPunishMinTable").DataTable().row().data();
	for(var i=0;i<json.length;i++){
		arr.push(json[i].id);
	}
}


var deleteDone=function(){
	alert('重新选中');
}



/**
 * 初始化列表
 */
var safetyPunish = function () {
	'use strict';
    return {
        //main function
        init: function () {

        	$.getScript("js/ellipsis.js").done(function(){        		
        		handleSafetyPunish();
        	});
        }
    };
}();

/**
 * 小类选中，查详述
 */
var trSelectedBacks =function(v, i, index, t, json){
	// 参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	//t.cgetDetail('safetyPunishMinform', '', '');
	//trSData.t.cgetDetail('safetyPunishMinform', '', '');
	var s = $("#safetyPunishMinTable").DataTable().row('.selected').data();
	$("#safetyPunishMinform").deserialize(s);
	$(".updateDetermine").addClass("hidden");
	$(".addDetermine").addClass("hidden");
	$(".cancelDetermine").addClass("hidden");
	//移除验证UI
	$("#safetyPunishMinform").parsley().reset();
}

var searchPop = function(){
	$('.searchBtn').on("click",function(){
		var url = "#safetyPunish/safetyPunishSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
		//基础条件查询添加监听
});
	
	//基础条件查询添加监听
	$('#safetyPunishTable_filter input').on('change', function() {
		//console.log("safetyPunishTable. input change...");
		var id = $('#safetyPunishTable_filter input').val();
		searchData = {};
		searchData.id = id;
		$('#safetyPunishTable').cgetData(true); // 列表重新加载
	});
	
	// 基础条件查询添加回车事件
	$('#safetyPunishTable_filter input').on('keyup', function(event) {
		if (event.keyCode == '13') {
			$(this).change();
		}
	});
}


var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_safetyPunish").serializeJson();
	//console.log(searchData);
	//列表重新加载
    $("#safetyPunishTable").cgetData();  
};

