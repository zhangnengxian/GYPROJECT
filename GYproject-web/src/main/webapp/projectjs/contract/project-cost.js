var projectCostMytable; //工程列表
var scaleTable;
var searchData={};//查询条件
var detailSearchData = {};//工程规模查询条件
var menuId = '110401';
searchData.menuId=menuId;
/**
 * 加载工程列表
 */
var handleProjectCost = function() {
    searchData.projNo=$("#waitHandleProjNo").val();

    "use strict";
    if ($('#projectCostTable').length !== 0) {
    	projectCostMytable= $('#projectCostTable').on( 'init.dt',function(){
    		var len = $('#projectCostTable').DataTable().ajax.json().data ? $('#projectCostTable').DataTable().ajax.json().data.length : $('#projectCostTable').DataTable().ajax.json().length;
    		if(len<1){
    			//加载右侧页面
    			$("#project_cost_panel_box").cgetContent("projectCost/viewPage");
    		}
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#projectCostTable').hideMask();
   			$("#projectCostTable_filter input").attr("placeholder","工程编号");
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//修改监听方法
   			updateMonitor();
   			//退单监听方法
   			//backMonitor();
   			setTimeout(function(){
   			    $("#projectCostTable").DataTable().columns.adjust();
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
                 { text: '造价确认', className: 'btn-sm btn-query business-tool-btn updateBtn' }
             ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'projectCost/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
             },
             //ajax: "projectjs/contract/json/project_cost.json",
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
      				 {"data":"budgetDate"},
      				 {"data":"projStatusDes"},
      				 {"data":"overdue", className:"none never"},
					 {"data":"signBack", className:"none never", "createdCell": function (td, cellData, rowData, row, col) {
							 if(cellData == $("#notThrough").val()){
                                 $(td).parent().find("td").css("background", "rgb(255, 219, 219)");
                                 // $(td).parent().css("background", "rgb(255, 219, 219)");
								 //$(td).attr("id",row);
							 }
						 }
					 },
			 ],
			 columnDefs: [{
				 "targets": 0,
				 "visible":false
			 },{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 8, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			 },{
					"targets":4,
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

var updateMonitor = function(){
	$(".updateBtn").on("click",function(){
		if($("#projectCostTable").find("tr.selected").length>0){
			detailFlag = true;
			$("#confirmTotalCost").attr("data-parsley-required","true");
			//$("#backReason").attr("data-parsley-required","false");
			//切换可编辑状态
			$("#sureBudgetForm,#scaleTableForm").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
			$(".backReason").addClass("hidden");
		}else{
			alertInfo('请选择要确定造价的记录！');
		}
	});
};

/**
 * 修改按钮监听方法
 *//*
var backMonitor = function(){
	$(".backBtn").on("click",function(){
		if($("#projectCostTable").find("tr.selected").length>0){
			
			$("#backReason").attr("data-parsley-required","true");
			$("#confirmTotalCost").attr("data-parsley-required","false");
			//切换可编辑状态
			$("#sureBudgetForm").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn,.backReason").removeClass("hidden");
			$("#sureBudgetForm").styleFit();
			$(".back-hid").addClass("hidden");
			
		}else{
			alertInfo('请选择要退单的工程记录！');
		}
	});
}*/
/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").on("click",function(){
		var url = "#projectCost/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#projectCostTable_filter input').on('change',function(){
		var projNo = $('#projectCostTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		searchData.menuId = menuId;
		
		$('#projectCostTable').cgetData(true,projectCoseCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#projectCostTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};

/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_project_cost").serializeJson();
	var projNo = $("#projectCostTable_filter input").val();
	searchData.projNo=projNo;
	searchData.menuId = menuId;
	//列表重新加载
    $("#projectCostTable").cgetData(true,projectCoseCallBack);  
}

//
var projectCoseCallBack = function(){
	 var len = $('#projectCostTable').DataTable().ajax.json().data ? $('#projectCostTable').DataTable().ajax.json().data.length : $('#projectCostTable').DataTable().ajax.json().length;	 
	 if(len<1){
		 $("#sureBudgetForm")[0].reset();
		 //清空右侧记录
		 $(".editbtn").addClass("hidden");		
		 $(".back-hid").removeClass("hidden");
 		 $(".backReason").addClass("hidden");
 		 $(".remark").addClass("hidden");
 		 //表单样式适应
 	     $("#sureBudgetForm").styleFit();
	    		
		 //参数传false时，表单不可编辑
		 $("#sureBudgetForm").toggleEditState(false);
		 $('.adjustMethod').addClass('hidden');
	 }else{
		 $(".editbtn").removeClass("hidden");
		 $(".remark").removeClass("hidden");
		 //参数传false时，表单不可编辑
		 //  $("#sureBudgetForm").toggleEditState(true);
		 $('.adjustMethod').addClass('hidden');
		 $(".backReason").addClass("hidden");
		 /*if($("#adjustmentMethod").val()===""){
			 $("#adjustmentMethod").val("设计变更或经济签证参照包干预算书中定额套用 标准及取费标准，人工费调整执行政府最新文件。");
		 }*/
	 }
}
var detailFlag = false;
/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	detailFlag = false;
	if($('[name="isBack"]:checked').val()==1){
		$('[name="isBack"]').not(":checked").attr("checked",true);
		$('[name="isBack"]').change();
	}
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	$("#project_cost_panel_box").cgetContent("projectCost/viewPage",{corpId:json.corpId,projectType:json.projectType,menuId:menuId});
	t.cgetDetail('sureBudgetForm','projectCost/viewCost?menuId='+menuId,'',sureBudgetBack); 
	
}

function sureBudgetBack(data){
	$(".editbtn").addClass("hidden");
  	//参数传false时，表单不可编辑
  	$("#sureBudgetForm").toggleEditState(false);

    $("#confirmTotalCost").val($("#budgetTotalCost").val());
    $('[name="isBack"]').on("change",function(){
    	$("#sureBudgetForm select[id='changeReason']").attr("data-parsley-required",false);
    	if($('[name="isBack"]:checked').val()==1){
    		$(".back-hid").addClass("hidden");
    		$(".backReason").removeClass("hidden");
    		console.log("1234");
    	}else{
    		$(".back-hid").removeClass("hidden");
    		$(".backReason").addClass("hidden");
    		$("#backReason").val('');
    	}
    	//表单样式适应
        $("#sureBudgetForm").styleFit();
    });
  //变动原因
    if($('#changeReason') && $('#changeReason').val()=='5'){
   		$(".remark").removeClass("hidden");
   	}else{
   		$(".remark").addClass("hidden");
   	}
    
    
    //客服中心可以隐藏审批原因
	if($("#deptDivide").val()==$("#customerServiceCenter").val()){
		$(".auditReason").addClass("hidden");
	}else{
		$(".auditReason").removeClass("hidden");
	}
    /*if($("#adjustmentMethod").val()===""){
		$("#adjustmentMethod").val("设计变更或经济签证参照包干预算书中定额套用 标准及取费标准，人工费调整执行政府最新文件。");
	}*/
    //加载工程规模
    //scaleDetailRefresh();
  	}

var projectCost = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handleProjectCost();
        	});
        }
    };
}();

//$(".attach-panel").initAttachOper({
//	collection: {
//		tableid:'projectCostTable'
//	}
//});

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
	  			{"data":"gasConsumption",className:"text-right", responsivePriority: 7},
	  			{"data":"scaleAmount",className:"text-right", responsivePriority: 2}
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
						var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_tonnage' id='" + row.projStypeId + "_tonnage'  data-parsley-maxlength='9' data-parsley-type='number' value="+data+"></div>"
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
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_searNum' id='" + row.projStypeId + "_searNum' data-parsley-maxlength='9' data-parsley-type='number' value="+data+"></div>"
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
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_num' id='" + row.projStypeId + "_num'  data-parsley-maxlength='9' data-parsley-type='number' value="+data+"></div>"
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
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_houseNum' id='" + row.projStypeId + "_houseNum' data-parsley-maxlength='9' data-parsley-type='number' value="+data+"></div>"
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
			    },{
			    	targets: 6,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							console.info(row.tonnage);
							if(row.tonnage==null&&row.searNum==null&&row.num==null&&row.houseNum==null&&row.gasConsumption==null){
								var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_scaleAmount' id='" + row.projStypeId + "_scaleAmount' data-parsley-maxlength='11' data-parsley-type='number' value="+data+"></div>"
							}else{
								var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_scaleAmount' id='" + row.projStypeId + "_scaleAmount' data-parsley-maxlength='11' data-parsley-type='number' data-parsley-required='true' value="+data+"></div>"	
							}
							return tdcon;
						}else{
							return data;
						}}	
			    }],
			ordering:false
       });
   }
};

var scaleDetailRefresh=function(){
	var ltypeId = $(".projLtypeId").val() || "";
	if(!ltypeId && $.fn.DataTable.isDataTable('#scaleTable')){
		detailSearchData.projId = "-1";
		$('#scaleTable').DataTable().ajax.reload();
		return;
	}
	ltypeId = ltypeId.split(",");
	$("input[name='projLtype']").attr("checked",false);
	for(var i=0;i<ltypeId.length;i++){
		$("input[name='projLtype'][value="+ltypeId[i]+"]").attr("checked","checked");
	}
	detailSearchData.projLtypeId = $(".projLtypeId").val();
	detailSearchData.projId = $("#projId").val();
	if($.fn.DataTable.isDataTable('#scaleTable')){
		//初始化过
		$("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
	}else{
		sacletableinit();
	}
}


var scaleTableCallBack = function(){
	if(detailFlag){
   	 	$('#scaleTableForm').toggleEditState(true);
	}else{
		$('#scaleTableForm').toggleEditState(false);
	}
}

var inputMonitor = function(){
	$(".tdInnerInput input").on("change",function(){
		var name = $(this).attr("name");
		var checkBoxId = name.split("_")[0]+"_scaleId";
		if($(this).val()){
			$("#"+checkBoxId).attr("checked","checked");
		}else{
			 var input = $(this).parents("tr").find(".tdInnerInput input").not("[type='checkbox']");
			 for(var i=0;i<input.length;i++){
				 if(input.eq(i).val()){
					 return false;
				 }
			 }
			 $("#"+checkBoxId).removeAttr("checked");
		}
	});
}
