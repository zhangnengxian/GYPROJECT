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
/*fuliwe*/
var projectAcceptTable;
var scaleTable,scaleChangeTable;
/**查询条件  默认待勘察*/
var searchData={};
var menuId = "110101";
searchData.menuId=menuId;
var accessoryData={};
var detailSearchData = {};
var handleProjectAccept = function() {
	"use strict";
    if ($('#projectAcceptTable').length !== 0) {
    	projectAcceptTable= $('#projectAcceptTable').on( 'init.dt',function(){
    		var data = {"menuId":menuId};
    		//加载页面
    		$("#project_accept_panel_box").cgetContent("projectAccept/viewPage",data);
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		/*var selectHtml = '<label style="margin-left: 5px;"><select class="form-control input-sm hidden" name="stateSelect" id="stateSelect">';
    		var options = $("#projStatus").html();
    		selectHtml += options;
			selectHtml += '</select></label>';
			$("#projectAcceptTable_filter").append(selectHtml);	*/
			$("#projectAcceptTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#projectAcceptTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//修改监听方法
   			updateMonitor();
   			//增加监听方法
   			insertMonitor();
   			//通知监听方法
   			informMonitor();
   			//打印方法
   			printMonitor();
   			setTimeout(function(){
   			    $("#projectAcceptTable").DataTable().columns.adjust();
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
                { text: '打印', className: 'btn-sm btn-query business-tool-btn printBtn hidden' },
                { text: '增加', className: 'btn-sm btn-query  business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                { text: '通知', className: 'btn-sm btn-query hidden business-tool-btn informBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'projectAccept/queryProject',
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
				{"data":"overdue",className:"none never" },
                {"data":"signBack",
                    className:"none never",
                    "createdCell": function (td, cellData, rowData, row, col) {
                        if(cellData==$("#notThrough").val()){
                            $(td).parent().children().css("background", "rgb(255, 219, 219)");
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
			var url = "#projectAccept/searchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#stateSelect,#projectAcceptTable_filter input").on("change",function(){
		var stateSelect = $("#stateSelect").find("option:selected");
		var projNo = $("#projectAcceptTable_filter input").val();
		searchData = {};
		searchData.projStatusId = stateSelect.val();
		searchData.projNo = projNo;
		searchData.menuId=menuId;
		$("#projectAcceptTable").cgetData(true);  //列表重新加载
		
	});
	//基础条件查询添加回车事件
	$('#projectAcceptTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	$("#project_accept_panel_box").removeClass("hidden");
	searchData = $("#searchForm_designDispatch").serializeJson();
	var projNo = $("#projectAcceptTable_filter input").val();
	searchData.projNo = projNo;
	searchData.menuId=menuId;
	/*var stateSelect = $("#stateSelect").val();
	searchData.projStatusId = stateSelect;
	var  projStatus= $('.projStatus :selected').val();*/
    $("#projectAcceptTable").cgetData(true,function(){
    	var len = $('#projectAcceptTable').DataTable().ajax.json().data ? $('#projectAcceptTable').DataTable().ajax.json().data.length : $('#projectAcceptTable').DataTable().ajax.json().length;
    	if(len<1){
    		 //清空右侧记录
    		 $("#acceptApplyForm")[0].reset();
    		 $(':input','#scaleTableForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
    		 //checkbox清除选中，相应的明细也清除掉
    		 $("input[name='projLtype']").attr("checked",false);
    		 $("input[name='projLtype']").change();
    		 $(".backReasonshow").addClass("hidden");
    	 }
    	/*if(projStatus=="3"){
    		$(".informBtn").removeClass("hidden");
    		//$(".printBtn").addClass("hidden");
    		$(".updateBtn").addClass("hidden");
    	}else if(projStatus=="2"){
    		//$(".printBtn").removeClass("hidden");
    		$(".informBtn").addClass("hidden");
    		$(".updateBtn").addClass("hidden");
    	}else{
    		//$(".printBtn").addClass("hidden");
    		$(".informBtn").addClass("hidden");
    		$(".updateBtn").removeClass("hidden");
    	}*/
    });  //列表重新加载
}

//修改监听方法
var updateMonitor = function(){
	$(".updateBtn").on("click",function(){
		$(".iframe-big-box").addClass("hidden");
		$("#project_accept_panel_box").removeClass("hidden");
		if($("#projectAcceptTable").find("tr.selected").length>0){
			detailFlag = true;
			$("#projNo").addClass("field-not-editable");
			$("#projNo").removeClass("field-editable");
			//切换可编辑状态
			$("#acceptApplyForm,#scaleTableForm,#scaleTableChangeForm").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
			$("#projectType").change();
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
};
//增加监听方法
var insertMonitor = function(){
	$(".addBtn").off("click").on("click",function(){
		//$("#deptId option:first").prop("selected", 'selected'); 
		//$("#deptId").change();
		$(".iframe-big-box").addClass("hidden");
		$("#project_accept_panel_box").removeClass("hidden");
		detailFlag = true;
		$('#projectAcceptTable').clearSelected();
		$("#acceptApplyForm")[0].reset();
		$("#custId").val("");
		$("#corpName").val($("#deptName").val());
		$("#projNo").val("");
		$("#projId").val("");
		
		$("#contributionMode").empty();
		$("#projectType").empty();
		
		//切换可编辑状态
		$("#acceptApplyForm").toggleEditState(true);
		$(".scaleTableChangeForm").addClass("hidden");
		$(".scaleTableForm").removeClass("hidden");
		//加载下面的第1个table
		if($.fn.DataTable.isDataTable('#scaleTable')){
			//初始化过
			$("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
		}else{
			sacletableinit();
		}
		//清除复选框选中
		/*$("input[name='projLtype']").attr('checked',false);
		//默认选中民用
		$("input[name='projLtype'][value=11]").attr("checked","checked");
		$(':input','#scaleTableForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected'); 
		//$("input[name='projLtype']").change();
		$(".projLtypeId").val('11');*/
		//表格初始化或重新加载
		scaleDetailRefreshSon();
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
	
	//资料查看文件
	$(".searchBtn1").on("click",function(){
	     var data = {};
	     data.fpath = $(this).attr("data-row");
		 $.ajax({
			url:'accessoryCollect/openFile',
			type:'post',
			data:data,
			success:function(data){
			    if(data == "nofile"){
			    	$("body").cgetPopup({
	    		    	title: "提示信息",
	    		    	content: "文件不存在! <br>",
	    		    	accept: popClose2,
	    		    	chide: true,
	    		    	icon: "fa-exclamation-circle"
	    		    });  		    	
			    }
		    }
		});
	});
}
//通知监听
var informMonitor = function(){
	$(".informBtn").off("click").on("click",function(){
	$(".informSaveBtn").removeClass("hidden");
	});
}

//打印监听
var printMonitor = function(){
	$(".printBtn").off("click").on("click",function(){
		if($("#projectAcceptTable").find("tr.selected").length>0){
		//trSelectedBack();
		$(".iframe-big-box").removeClass("hidden");
		$("#project_accept_panel_box").addClass("hidden");
		
		}else{
			alertInfo('请选择要打印的工程！');
		}
	});
}

var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	//$(".informSaveBtn").addClass("hidden");
	
	$(".iframe-big-box").addClass("hidden");
	$("#project_accept_panel_box").removeClass("hidden");
	$("#contributionMode1").val(json.contributionMode);
	//showReport();
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	detailFlag = false;
	t.cgetDetail('acceptApplyForm','projectAccept/viewProjectAccept','',scaleDetailRefresh);
}

var scaleDetailRefresh = function(data){
	$("#projectType").empty();
	var selectEl = $("#deptId");
	var index, selectOption, deptId ;
	if(selectEl && selectEl[0]){
    	index = selectEl[0].selectedIndex,
   	 	selectOption = selectEl[0].options[index];
    	deptId = $(selectOption).attr("value");
		console.info("deptId4========");
		console.info(deptId);
	}else if(data!='' && data.deptId!=''){
		deptId = data.deptId
	}
	if(deptId==''){
		deptId='-1';
	}
	console.info("deptId3========");
	console.info(deptId);
	$.ajax({
		type:'post',
		url :'correlation/queryCorrelationList?corType=2&correlateInfoId='+deptId+'&acceptType=2',
		dataType:'json',
		success:function(data1){
			$.each(data1,function(n,v){
				$("#projectType").append('<option value='+v.correlatedInfoId+'>' + v.correlatedInfoDes + '</option>');
			})
			//$("#projectType").change();
			$("#projectType option[value='"+data.projectType+"']").attr("selected","selected");
			 setTimeout(function(){
				 trSData.t && trSData.t.cgetDetail('acceptApplyForm','','');
			    },100);
			HIddCuIn();
			scaleDetailRefreshSon();
		},
		error: function(data1){
			console.warn("工程类型选框查询失败");
		}
	});
}

var scaleDetailRefreshSon = function(){
	/*var  backReasonshow= $('.backReasonshow :selected').val();
	if(backReasonshow==''){
		$(".backReasonshow").addClass("hidden");
	}else{
		$(".backReasonshow").removeClass("hidden");
		 $("#acceptApplyForm").styleFit();
	}*/
	
	if($("#remark").val()!==""){
		$(".remark").removeClass("hidden");
	}else{
		$(".remark").addClass("hidden");
	}
	
	
	var ltypeId = $(".projLtypeId").val() || "";
	
	console.info("flw---"+$(".projLtypeId").val());
	
	if(!ltypeId && $.fn.DataTable.isDataTable('#scaleTable')){
		detailSearchData.projId = "-1";
		$('#scaleTable').DataTable().ajax.reload();
		return;
	}
	ltypeId = ltypeId.split(",");
	$("input[name='projLtype']").attr("checked",false);
	for(var i=0;i<ltypeId.length;i++){
		$("input[name='projLtype'][value="+ltypeId[i]+"]").attr("checked","checked");
		$("#projectType1 option[value='"+ltypeId[i]+"']").attr("selected","selected");
	}
	detailSearchData.projLtypeId = $(".projLtypeId").val();
	detailSearchData.projId = $("#projId").val();
	
	var selectVal=$("#projectType1 option:selected").text();
	
	console.info("flw2---"+selectVal);
	
	if(selectVal=="31" ||selectVal=="41" ){
		$(".scaleTableForm").addClass("hidden");
		$(".scaleTableChangeForm").removeClass("hidden");
		//加载下面的第2个table
		if($.fn.DataTable.isDataTable('#scaleChangeTable')){
			//初始化过
			$("#scaleChangeTable").cgetData(false,scaleChangeTableCallBack);//列表重新加载
		}else{
			sacletableinit2();
		}
	}else{
		$(".scaleTableChangeForm").addClass("hidden");
		$(".scaleTableForm").removeClass("hidden");
		//加载下面的第1个table
		if($.fn.DataTable.isDataTable('#scaleTable')){
			//初始化过
			$("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
		}else{
			sacletableinit();
		}
	}
	if(selectVal=="11"){
		//居民
		$(".isBidding").removeClass("hidden");
	}else{
		$(".isBidding").addClass("hidden");
	}
	
}
var scaleTableCallBack = function(){
	if(detailFlag){
   	 	$('#scaleTableForm').toggleEditState(true);
	}else{
		$('#scaleTableForm').toggleEditState(false);
	}
}

var scaleChangeTableCallBack = function(){
	console.info(detailFlag);
	if(detailFlag){
   	 	$('#scaleTableChangeForm').toggleEditState(true);
	}else{
		$('#scaleTableChangeForm').toggleEditState(false);
	}
}


var projectAccept = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handleProjectAccept();
        	});
        }
    };
}();

/**
 * 初始化规模列表
 */
var sacletableinit= function() {
	"use strict";
    if ($('#scaleTable').length !== 0) {
    	scaleTable= $('#scaleTable').on( 'init.dt',function(){
    		$('#scaleTable').hideMask();
	        $('#scaleTableForm').toggleEditState(false);
	        inputMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [],
            /*ajax: 'projectjs/accept/json/scale.json',*/
            //启用服务端模式，后台进行分段查询、排序
            serverSide:false,
            ajax: {
            	url: 'projectAccept/queryScaleDetail',
            	type:'post',
            	data: function(d){
                  	$.each(detailSearchData,function(i,k){
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
            },//自适应
            columns: [
	  			{"data":"projStypeDes", responsivePriority: 1},
	  			{"data":"tonnage",className:"text-right", responsivePriority: 3},
	  			{"data":"searNum",className:"text-right", responsivePriority: 6},
	  			{"data":"num",className:"text-right", responsivePriority: 5},
	  			{"data":"houseNum",className:"text-right", responsivePriority: 4},
	  			{"data":"gasConsumption",className:"text-right", responsivePriority: 7}
			],
			columnDefs: [{
				"targets":0,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 6, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				targets: 1,
				/*
				 * render属性
				 * 方法携带四个参数
				 * data: 该单元格的原始数据，也就是默认显示的那些数据
				 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
				 * row: 但前行的所有原始数据
				 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
				 */
				render: function ( data, type, row, meta ) {
					if(type === "display"){
						if(data === null){
							data = "";
						}
						var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_tonnage' id='" + row.projStypeId + "_tonnage'  data-parsley-maxlength='16' data-parsley-type='number' value="+data+"></div>"
						return tdcon;
					}else{
						return data;
					}}
			    },{
			    	targets: 2,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_searNum' id='" + row.projStypeId + "_searNum' data-parsley-maxlength='16' data-parsley-type='integer' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}	
			    },{
			    	targets: 3,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_num' id='" + row.projStypeId + "_num'  data-parsley-maxlength='16' data-parsley-type='integer' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}	}
			    },{
			    	targets: 4,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_houseNum' id='" + row.projStypeId + "_houseNum' data-parsley-maxlength='9' data-parsley-type='integer' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}
			    },{
			    	targets: 5,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}else if(data !==null && data!==""){
								data = data.toFixed(2);
							}
							var  tdcon="<div class='tdInnerInput'><input type='hidden' name='"+row.projStypeId+"_projStypeId' id='"+row.projStypeId+"_projStypeId' value="+row.projStypeId+">" +
									    "<input class='hidden' name='" + row.projStypeId + "_projLtypeId' id='" + row.projStypeId + "_projLtypeId' data-parsley-maxlength='9' data-parsley-type='number' value="+row.projLtypeId+">" +
									    "<input class='hidden' name='" + row.projStypeId + "_projStypeDes' id='" + row.projStypeId + "_projStypeDes'   value="+row.projStypeDes+">"+
									    "<input class='hidden' name='" + row.projStypeId + "_scaleId' id='" + row.projStypeId + "_scaleId'   value="+row.scaleId+">"+
									    "<input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_gasConsumption' id='" + row.projStypeId + "_gasConsumption'  data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}
			    }],
			ordering:false
       });
   }
};

var inputMonitor = function(){
	$(".tdInnerInput input").on("change",function(){
		var name = $(this).attr("name");
		var checkBoxId = name.split("_")[0]+"_scaleId";
		if($(this).val()){
			$("#"+checkBoxId).attr("checked","checked");
		}else{
			 var input = $(this).parents("tr").find(".tdInnerInput input").not("[type='radio']");
			 for(var i=0;i<input.length;i++){
				 if(input.eq(i).val()){
					 return false;
				 }
			 }
			 $("#"+checkBoxId).removeAttr("checked");
		}
	});
}



/**
 * 初始化规模列表
 */
var sacletableinit2= function() {
	"use strict";
    if ($('#scaleChangeTable').length !== 0) {
    	scaleChangeTable= $('#scaleChangeTable').on( 'init.dt',function(){
    		$('#scaleChangeTable').hideMask();
	        $('#scaleTableChangeForm').toggleEditState(false);
	        scaleChangeTableCallBack();
	        inputMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [],
            /*ajax: 'projectjs/accept/json/scale.json',*/
            //启用服务端模式，后台进行分段查询、排序
            serverSide:false,
            ajax: {
            	url: 'projectAccept/queryScaleDetail',
            	type:'post',
            	data: function(d){
                  	$.each(detailSearchData,function(i,k){
                  		d[i] = k;
                  	});
                  	
            	},
            	dataSrc: 'data'
            },
            
            //ajax: 'projectjs/accept/json/proj_scale.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },//自适应
            columns: [
	  			{"data":"projStypeDes", responsivePriority: 3},
	  			{"data":"pipeDiameter",className:"text-right", responsivePriority: 2},
	  			{"data":"pipeLength",className:"text-right", responsivePriority: 1}
			],
			columnDefs: [{
				"targets":0,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 6, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				targets: 1,
				/*
				 * render属性
				 * 方法携带四个参数
				 * data: 该单元格的原始数据，也就是默认显示的那些数据
				 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
				 * row: 但前行的所有原始数据
				 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
				 */
				render: function ( data, type, row, meta ) {
					if(type === "display"){
						if(data === null){
							data = "";
						}
						var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_pipeDiameter' id='" + row.projStypeId + "_pipeDiameter'  data-parsley-maxlength='50' value="+data+"></div>"
						return tdcon;
					}else{
						return data;
					}}
			    }/*,{
			    	targets: 2,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_pipeLength' id='" + row.projStypeId + "_pipeLength' data-parsley-maxlength='13' data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}	
			    }*/,{
			    	targets: 2,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}else if(data !==null && data!==""){
								data = data;
							}
							console.info("row.scaleId---"+row.scaleId);
							
							var  tdcon="<div class='tdInnerInput'><input type='hidden' name='"+row.projStypeId+"_projStypeId' id='"+row.projStypeId+"_projStypeId' value="+row.projStypeId+">" +
									    "<input class='hidden' name='" + row.projStypeId + "_projLtypeId' id='" + row.projStypeId + "_projLtypeId' data-parsley-maxlength='9' data-parsley-type='number' value="+row.projLtypeId+">" +
									    "<input class='hidden' name='" + row.projStypeId + "_projStypeDes' id='" + row.projStypeId + "_projStypeDes'   value="+row.projStypeDes+">"+
									    "<input class='hidden' name='" + row.projStypeId + "_scaleId' id='" + row.projStypeId + "_scaleId'   value="+row.scaleId+">"+
									    "<input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_pipeLength' id='" + row.projStypeId + "_pipeLength'  data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}
			    }],
			ordering:false
       });
   }
};
