
var projId=$("#projId").val();
var drawingData={"projId":projId};	 	//图纸列表查询条件
var histSearchData={"projId":projId};	//历史列表查询条件
var budgeterTable,scaleTable,detailSearchData={};
var searchMonitor= function(){
	//基础条件查询添加监听
	$("#drawingTable_filter input").on("change",function(){
		var drawingNo = $("#drawingTable_filter input").val();
		drawingData.drawingNo = drawingNo;
		$("#drawingTable").cgetData(false);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#drawingTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
};
/**
 * 预算详述
 */
var handleProjCostDetial = function(){
	var url = 'projectCostAudit/queryPro';
	var projId =$('#projId1').val();
	var data = "id="+projId;
	var f = $("#projCostForm");
	 $.ajax({
         type: 'POST',
         url: url,
         data: data,
         dataType: 'json',
         success: function (data) {
             //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
             var selects = f.find('select[disabled]');
             selects.removeAttr("disabled");
             //表单反序列化填充值
            // f.deserialize(data);
             f.deserialize(data.project);
             //有disabled属性的下拉菜单元素对象重新添加禁用属性
             selects.attr("disabled","disabled");
             detailCallBack(data);
           //变动原因
            /* if($('#changeReason') && $('#changeReason').val()=='5'){
            	 $("#remark").val(data.remark);
            	$(".remark").removeClass("hidden");
            }else{
            	$(".remark").addClass("hidden");
            }*/
            // $("#remark").val(data.remark);
            // $(".remark").removeClass("hidden");
           //  $("#cuName").val(getProjectInfo().cuName);
         },
         error: function (jqXHR, textStatus, errorThrown) {
             console.warn("cgetDetail() -> 详情查询失败");
         }
     });
}
var detailCallBack =function(data){
	if(($("#mrAuditLevel").val()==$("#auditLevel").val()) && ($("#isDisplay").val()=='1')){  //后台配置是否显示保存按钮和表单可编辑
    	if($("#isAudit").val()=='1'){
    		$('#projCostForm').toggleEditState(false);
			$(".auditSaveBtn").addClass("hidden");
    	}else{
    		$('#projCostForm').toggleEditState(true);
    		$(".auditSaveBtn").removeClass("hidden");
    	}
	}else{
		$('#projCostForm').toggleEditState(false);
		$(".auditSaveBtn").addClass("hidden");
	}
	//加载工程规模
	// scaleDetailRefresh();
	//加载预算审批历史
	auditHistoryInit(data);
}
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
					length: 10, 	//截取多少字符（或汉字）
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

/**
 * 初始化审核列表
 */
var projectCostAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleProjCostDetial();
        }
    };
}();

/**
 * 初始化右侧审核列表
 */
var auditHistoryInit = function(data) {
	"use strict";
	$('#businessOrderId').val(data.pcId);
	$('#projId1').val(data.projId);
	$('#projNo1').val(data.projNo);
	histSearchData.businessOrderId=data.pcId;
	histSearchData.projId=data.projId;
	if ($('#auditHistoryTable').length !== 0) {
    	$('#auditHistoryTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#auditHistoryTable').hideMask();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'projectCostAudit/queryManageRecord',
                type:'post',
                data: function(d){
                   	$.each(histSearchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/budget/json/audit_history.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
				{"data":"mrTime"},
				{"data":"mrResult"},
				{"data":"mrAopinion"},
				{"data":"mrCsr"}
			],
			columnDefs: [{
				"targets": 0,
				"render": function ( data, type, row, meta ) {
					if(type === "display"){
						return format(data,'all');
					}else{
						return data;
					}
				},
			},{
				"targets": 1,
				"render": function ( data, type, row, meta ) {
					if(data === "1"){
						return "通过";
					}else if(data === "0"){
						return "不通过";
					}else{
						return "";
					}
				},
			}]
        });
    }
};