var costApplyTable;  //费用申请列表
var applyProjectTable;   //清单列表
var searchData={};	 //查询条件
searchData.menuId = "110614";
var feeApplyListData={};
var histSearchData={};
/**
 * 加载费用申请列表
 */
var handleCostApply = function() {
	"use strict";
    if ($('#costApplyTable').length !== 0) {
    	costApplyTable= $('#costApplyTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#cost_apply_panel_box").cgetContent("costApply/viewPage");
   			//隐藏遮罩
   			$('#costApplyTable').hideMask();
   			$("#costApplyTable_filter input").attr("placeholder","申请编号");
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//下载模板
   			checkOutMonitor();
   			//增加
   			addBtnMonitor();
   			//推送
   			pushBtnMonitor();
   			//修改
   			updateBtnMonitor();
   			
   			//导出按钮监听事件
   			exportBtnMonitor();
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
                 { text: '下载模板', className: 'btn-sm btn-query business-tool-b checkOut'},
                 { text: '增加', className:'btn-sm btn-query business-tool-btn addBtn'},
                 { text: '修改', className:'btn-sm btn-query business-tool-btn updateBtn'},
                 { text: '导出', className:'btn-sm btn-query business-tool-btn exportBtn'},
                 { text: '推送', className:'btn-sm btn-query business-tool-btn pushBtn'},
				 { text: '作废', className:'btn-sm btn-danger business-tool-btn cancelBtn'}
             ],
             serverSide:true,
             ajax:{
            	 url:"costApply/queryPaymentApply",
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
             select: true,    //支持多选
             columns: [
			    {"data":"paId",className:"none never"},
	  			{"data":"applyNo"}, 
				{"data":"applyer"},
				{"data":"applyDate"},
				{"data":"auditStateDes"},
				{"data":"isDispatchDes"},
				{"data":"signBack", className:"none never", "createdCell": function (td, cellData, rowData, row, col) {
					console.info("notThrough"+$("#notThrough").val());
					console.info("cellData--"+$("#notThrough").val());
					if(cellData == $("#notThrough").val()){
						$(td).parent().find("td").css("background", "rgb(255, 219, 219)");
					}
				}
				},
		 	 ],
			 columnDefs: [{
				 "targets": 0,
				 "visible":false
			 },{
				 "targets":4,
				 "orderable":false
			},{
				 "targets":5,
				 "orderable":false
			}],
			//  fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
			// 	 if(aData.overdue){
			// 		 $(nRow).addClass("expired-proect");
			// 	}
			// }
        });
    }
};

/**
 * 弹屏监听方法
 */
var searchMonitor= function(){
	$(".searchBtn").on("click",function(){
		var url = "#costApply/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$("#costApplyTable_filter input").on("change",function(){
		var applyNo = $("#costApplyTable_filter input").val();
		searchData = {};
		searchData.applyNo = applyNo;
		$("#costApplyTable").cgetData(true,costApplyCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#costApplyTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
};

//导出监听
var checkOutMonitor=function(){
	$(".checkOut").off("click").on("click",function(){
		/*var str="4";
		alert(str.charAt(str.length-1));
		alert(removeLastOne(str));*/
		$("#exportExcel").submit();
	})
}


//截取除最后一位外其他的数字
var removeLastOne=function (str){
    return str.substring(0,str.length - 1);
}



//增加监听
var addBtnMonitor=function(){
	$(".addBtn").off("click").on("click",function(){

		tableInit();//初始化表格
		//右侧可编辑
		$(".editbtn").removeClass("hidden");
		$("#costApplyForm").formReset();
		$("#paId").val("");
		$("#costApplyForm").toggleEditState(true).styleFit();
		$("#applyDeptName").val($("#loginUnit").val());
		$("#applyDeptId").val($("#loginUnitId").val());
		$("#applyer").val($("#loginName").val());
		
		console.info("loginUnitType--"+$("#loginUnitType").val());
		console.info("designUnit--"+$("#designUnit").val());
		console.info("checkUnit--"+$("#checkUnit").val());
		console.info("suUnit--"+$("#suUnit").val());
		
		//设计单位
		if($("#designUnit").val()==$("#loginUnitType").val()){
			$("#costApplyForm option[value='2']").attr("selected","selected");
			$(".auditUnit").addClass("hidden");
		}else if($("#checkUnit").val()==$("#loginUnitType").val()){
			//检测单位
			$("#costApplyForm option[value='4']").attr("selected","selected");
			$(".auditUnit").removeClass("hidden");
		}else if($("#suUnit").val()==$("#loginUnitType").val()){
			//监理单位
			$("#costApplyForm option[value='3']").attr("selected","selected");
			$(".auditUnit").addClass("hidden");
		}else{
			$(".auditUnit").addClass("hidden");
		}
		
		
		feeApplyListData.paId="-111";
		//清单列表加载
		$("#applyProjectTable").cgetData(false);
		if($("#feeType").val()=='2'||$("#feeType").val()=='4'){
			$(".importBtn,.deleteBtn").removeClass("hidden");
			$(".addProjBtn").addClass("hidden");
		}else if($("#feeType").val()=='3'){
			$(".addProjBtn,.deleteBtn").removeClass("hidden");
			$(".importBtn").addClass("hidden");
		}
		console.info("feeType==="+$("#feeType").val());
	})
}

//推送监听
var pushBtnMonitor=function(){
	$(".pushBtn").off("click").on("click",function(){
		if($("#costApplyTable").find("tr.selected").length>0){
			
			var data = applyProjectTable.rows().data();
			if(data.length>0){
				var myoptions = {
                   	title: "提示信息",
                   	content: "是否确认推送到费用审核？",
                   	accept: pushDone,
                   	chide: true,
                   	icon: "fa-check-circle"
	            }
	            $("body").cgetPopup(myoptions);
			}else{
				alertInfo("请先导入工程费用清单！");
			}
		}else{
			alertInfo('请选择要推送的费用申请记录！');
		}
	})
}


var pushDone=function(){
	var paId=trSData.costApplyTable.json.paId;
   	$.ajax({
           type: 'POST',
           url: 'costApply/pushPaymentApply',
           contentType: "application/json;charset=UTF-8",
           data: paId,
           success: function (data) {
           	var content = "推送成功";
           	if(data === "false"){
           		content = "推送失败！";
           	}else{
           		$("#costApplyTable").cgetData(true,costApplyCallBack);  //列表重新加载
           	}
           	var myoptions = {
                   	title: "提示信息",
                   	content: content,
                   	accept: popClose,
                   	chide: true,
                   	icon: "fa-check-circle"
               }
               $("body").cgetPopup(myoptions);
           },
           error: function (jqXHR, textStatus, errorThrown) {
               console.warn("推送失败！");
           }
       });
}


//修改监听
var updateBtnMonitor=function(){
	$(".updateBtn").off("click").on("click",function(){
		if($("#costApplyTable").find("tr.selected").length>0){
			$(".editbtn").removeClass("hidden");
			$("#costApplyForm").toggleEditState(true).styleFit();
			if($("#feeType").val()=='2'||$("#feeType").val()=='4'){
				$(".importBtn,.deleteBtn").removeClass("hidden");
				$(".addProjBtn").addClass("hidden");
			}else if($("#feeType").val()=='3'){
				$(".addProjBtn,.deleteBtn").removeClass("hidden");
				$(".importBtn").addClass("hidden");
			}
		}else{
			alertInfo('请选择要修改的付款申请记录！');
		}
	})
}


//导出按钮监听事件
var exportBtnMonitor = function(){
	$(".exportBtn").off("click").on("click",function(){
		if($("#costApplyTable").find("tr.selected").length>0){
			$(".editbtn").addClass("hidden");
			$("#costApplyForm").toggleEditState(true).styleFit();
		    var paId = trSData.costApplyTable.json.paId;  //获取当前表格选中行的数据
		    $("#exportExcelFeeApply").attr("action", "costApply/exportExcelFeeApply/"+paId); //设置表单action并且传入参数
		    $("#exportExcelFeeApply").submit();
		}else{
			alertInfo('请选择一条记录！');
		}
	})
}

/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	searchData = $("#searchForm_paymentAudit").serializeJson();
	var applyNo=$('#costApplyTable_filter input').val();
	searchData.applyNo=applyNo;
    $("#costApplyTable").cgetData(true,costApplyCallBack);  //列表重新加载
}

var costApplyCallBack = function(){
	var len = $('#costApplyTable').DataTable().ajax.json().data ? $('#costApplyTable').DataTable().ajax.json().data.length : $('#costApplyTable').DataTable().ajax.json().length;
	if(len<1){
		//清空右侧记录
		$("#costApplyForm").formReset();
		$("#costApplyForm").toggleEditState(false).styleFit();
		$(".editBtn").addClass("hidden");
		feeApplyListData.paId = "-1";
		//初始化过
		$("#applyProjectTable").cgetData(false);//列表重新加载
	}
	 
}

/**
 * 查询弹出屏，点击取消后
 */
var clearForm=function(){
	//清空表单
    $("#searchForm_costApply input").val("");
}


/**
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	//0 未推送 1 审核中  2 未通过 3 通过
	if(json.auditState=="0"||json.auditState=="2"){
		$(".pushBtn").removeClass("hidden");
		$(".updateBtn").removeClass("hidden");
	}else{
		$(".pushBtn").addClass("hidden");
		$(".updateBtn").addClass("hidden");
	}
	$(".importBtn,.deleteBtn,.addProjBtn,.editbtn").addClass("hidden");
	$("#businessOrderId").val(json.paId);
	$("#paId").val(json.paId);
	console.info("applyAmount--"+json.applyAmount);


	feeApplyListData.paId = $("#paId").val()||"-1";
	tableInit();//初始化表格
	t.cgetDetail('costApplyForm','','',detailBack);
}


//请款工程列表初始化
function tableInit() {
	if($.fn.DataTable.isDataTable('#applyProjectTable')){
		//初始化过
		$("#applyProjectTable").cgetData(true,applyProjectTableCallBack);//列表重新加载
	}else{
		applyProjectTableInit();
	}
}
//审批历初始化
function auditHistoryInit(){
	console.info("paId===="+$('#paId').val());
	console.info("businessOrderId===="+$('#businessOrderId').val());
    histSearchData.businessOrderId=$('#paId').val();
	if($.fn.DataTable.isDataTable('#auditHistoryTable')){
        $("#auditHistoryTable").cgetData(true);//列表重新加载
    }else{
        handleIsAuditHistory();
    }
}
var handleIsAuditHistory = function() {
	
    $('#auditHistoryTable').on( 'init.dt',function(){
    }).DataTable({
        language: language_CN,
        lengthMenu: [18],
        dom: 'Brt',
        buttons: [],
        serverSide:true,//启用服务端模式，后台进行分段查询、排序
        ajax: {
            url: 'costApply/queryManageRecord',
            type:'post',
            data: function(d){$.each(histSearchData,function(i,k){d[i] = k;});},
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
};





var detailBack=function(data){
	$('#costApplyTable').parent().parent().hideMask();
	console.info("feeTyp1e---"+data.feeType);
	if(data.feeType=="4"){
		//检测费
		$(".auditUnit").removeClass("hidden");
	}else{
		console.info("feeType1---"+data.feeType);
		$(".auditUnit").addClass("hidden");
	}
	
	$("#businessOrderId").val(data.paId);
	Base.subimt("costApply/queryTodoer","POST",{businessOrderId:data.paId},function (data) {
		$('#todoer').val(data);
    });
	auditHistoryInit();
}



var applyProjectTableCallBack = function(){
	var len = $('#applyProjectTable').DataTable().ajax.json().data ? $('#applyProjectTable').DataTable().ajax.json().data.length : $('#applyProjectTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.editBtn').addClass("hidden");
	}else{
		$('.editBtn').removeClass("hidden");
	}
}

/**
 * 加载请款工程表
 */

var applyProjectTableInit = function(){
	"use strict";
	feeApplyListData.paId = $("#paId").val()||"-1";
    if ($('#applyProjectTable').length !== 0) {
    	applyProjectTable=$('#applyProjectTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trListSelectedBack,true);
    		//隐藏遮罩
   			$('#applyProjectTable').hideMask();
   			//导入监听
   			importBtnMonitor();
   			//删除监听
   			deleteBtnMonitor();
   			//增加监听
   			addProjMonitor();
   			
   			$(this).bindInputsChange(amountSum);
   			
   			var json=$("#applyProjectTable").DataTable().rows().data();
    		var val=0.00;
    		for(var i=0;i<json.length;i++){
    			if(json[i].veId==1){
    				val+=new Number(json[i].applyAmount);
    			}
    		}
    		$("#applyAmount").val(val.toFixed(2));
    		console.info("applyAmount2--"+val.toFixed(2));
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
           // dom: 'Brtip',
            dom: 'Brtip',
            buttons: [
                { text: '增加', className:'btn-sm btn-query business-tool-btn hidden addProjBtn'},      
                { text: '导入', className:'btn-sm btn-query business-tool-btn hidden importBtn'},
                { text: '删除', className:'btn-sm btn-warn business-tool-btn hidden deleteBtn'}
            ],
            //启用服务端模式，后台进行分段查询、排序
            //serverSide:true,
            ajax: {
                url: 'costApply/queryFeeApplyList',
                type:'post',
                data: function(d){
                   	$.each(feeApplyListData,function(i,k){
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
                {"data":"projId"},
      			{"data":"projNo", responsivePriority: 1},
	  			{"data":"projName", responsivePriority: 4},
	  			{"data":"projAddr", responsivePriority: 5,className:"hidden"},
	  			{"data":"projScaleDes", responsivePriority: 6,className:"hidden"},
	  			{"data":"applyAmount", responsivePriority: 2},
	  			{"data":"endAmount", responsivePriority: 3},
	  			{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}
				],
			columnDefs: [{
				 "targets": 0,
				 "visible":false
			 },{
				"targets":1,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 15, 	//截取多少字符（或汉字）
					end: false		//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			 },{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 15, 	//截取多少字符（或汉字）
					end: false		//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			 },{
				"targets":3,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false		//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			 },{
					"targets": 5,
					"render": function ( data, type, row, meta ) {
						if(type === "display"){
							data = data || 0;
							var tdcon='<div class="tdInnerInput"><input  class="form-control input-sm text-right numbers" data-parsley-type="number" value="' + data + '"></div>';
							return tdcon;
						}else{
							return data;
						}
					}
			    },{
					"targets": 6,
					"render": function ( data, type, row, meta ) {
						if(type === "display"){
							data = data || 0;
							var tdcon='<div class="tdInnerInput"><input  class="form-control input-sm text-right numbers" data-parsley-type="number" value="' + data + '"></div>';
							return tdcon;
						}else{
							return data;
						}
					}
			    }],
        });
    }
}



var trListSelectedBack=function(v, i, index, t, json){
}

//导入监听
var importBtnMonitor=function(){
	$(".importBtn").off("click").on("click",function(){
		$("body").cgetPopup({
			title: '文件导入',
			content: "#costApply/importPop?url=costApply/importExcel",
			accept: importApplyList,
			nocache: true
		});
	})
}

//删除监听
var deleteBtnMonitor=function(){
	$(".deleteBtn").off("click").on("click",function(){
		if($("#applyProjectTable").find("tr.selected").length>0){
			$("#applyProjectTable").DataTable().row( '.selected' ).remove().draw();
		}else{
			alertInfo('请选择要删除的费用清单记录！');
		}
	})
}

var queryBack=function(){
	$("#applyProjectTable").cgetData();
}

var importApplyList=function(){
	
}

//增加监听
var addProjMonitor=function(){
	//cancelClick();//作废
	$(".addProjBtn").off("click").on("click",function(){
		var url = "#costApply/payProjectPop";
        var popoptions = {
            title: '选择工程',
            content: url,
            accept: addProjDone,
            size:'super'
        };
        //加载弹屏
        $("body").cgetPopup(popoptions);
	})
}





function cancelClick() {
	$(".cancelBtn").off("click").on("click",function(){
		var len=$("#costApplyTable").find("tr.selected").length;
		if(len<1){
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要作废的信息!',
				ahide:true,
				atext:'确定'
			});
		}else{
			$('body').cgetPopup({//加载弹屏
				title: '修改',
				content: '#intelligentSupplement/suppModifyPopPage',
				accept: cancelDone
			});

		}
	});
}



var cancelDone = function(){//查询弹出屏，点击确定后
	var pf = $("#modifySupplementForm");
	if (pf.parsley().isValid()) { //验证必填是否已填写
		$("#modifySupplementForm").loadMask("保存中···", 1, 0.5);

		var json= trSData.costApplyTable.json;
		json.auditState=-1;//作废
		json.deleteReson=$("#modifyRemark").val();

		Base.subimt('costApply/cancelPaymentApply',"post",JSON .stringify(json),function (data) {
			$("#costApplyTable").cgetData(true);
			var content = data === "false"?"作废失败！":"作废成功！";
			var myoptions = {
				title: "提示信息",
				content: content,
				accept: popClose,
				chide: true,
				icon: "fa-check-circle"
			}
			$("body").cgetPopup(myoptions);
		})
	} else {
		pf.parsley().validate();
		return false;
	}
}









var addProjDone=function(){
	var data = costApplyTablePop.rows('.selected').data();
   var data1 = applyProjectTable.rows().data();
	//去除重复数据
	//得到2个数组中工程不同的数据
	for(var i = 0;i<data1.length;i++){
	    for(var j = 0;j<data.length;j++){
	    	if(data.length > 0){
	            if(data[j].projId == data1[i].projId){
	                data.splice(j,1);
	                j=j-1;
	            }
	        }
	    }
	}
	
	$("#applyProjectTable").DataTable().rows.add(data).draw();
}


/**
 * 表格计算
 */
var amountSum=function(v, input, t, dt){
//	
	var json=$("#applyProjectTable").DataTable().rows().data();
	var val=0.00;
	for(var i=0;i<json.length;i++){
		val+=new Number(json[i].applyAmount);
	}
	$("#applyAmount").val(val.toFixed(2));
};



/**
 * 初始化付款申请列表
 */
var costApply = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handleCostApply();
        	})
        }
    };
}();


