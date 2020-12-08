var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var projectTable, 			//待签分包工程列表
searchData={},	   			//查询条件
paymentApplyTable,			//施工合同列表
searchSubContractData = {};	//施工合同列表查询条件	
/**
 * 加载工程列表
 */
var handlePaymentApplyTableTable = function() {
	'use strict';
    if ($('#projectTable').length !== 0) {
    	projectTable=$('#projectTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#sub_contract_panel_box').cgetContent('paymentApply/viewPage');
   			//隐藏遮罩
   			$('#projectTable').hideMask();
   			$('#projectTable_filter input').attr('placeholder','工程编号');
   			//搜索监听
   			searchMonitor();
   			//推送监听
   			pushMonitor();
   			//修改监听
   			updateMonitor();
   			//增加
   			addMonitor();
   			setTimeout(function(){
   			    $("#projectTable").DataTable().columns.adjust();
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
                 { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }
                 ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
            	 url: 'paymentApply/querySubContract',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){

                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
             },
             //ajax: 'projectjs/subcontract/json/sub_contract.json',
             responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
             select: true,  //支持多选
             columns: [
				 {'data':'projId',className:'none never'}, 
				 {"data":"scNo"},
	  			 {"data":"projName"},
	  			 {"data":"scSignDate"},
	  			 {"data":"scAmount",className:"text-right"},
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
			} ,{
				 'targets':4,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							return parseFloat(data).toFixed(2);
						}else{
							return data;
						}
					},
			 	}
			 ],
			 fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
					if(aData.overdue){
						$(nRow).addClass("expired-proect");
					}
			}
        });
    }
}
/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$('.searchBtn').on('click',function(){
		var url = '#paymentApply/projectSearchPopPage';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#projectTable_filter input').on('change',function(){
		var projNo = $('#projectTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#projectTable').cgetData(true,subTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#projectTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}
/**
 * 推送监听方法
 */
var pushMonitor = function(){
	$('.pushBtn').off().on('click',function(){
		if($("#paymentApplyTable").find("tr.selected").length>0){
			var paId=trSData.paymentApplyTable.json.paId;
	       	$.ajax({
	               type: 'POST',
	               url: 'paymentApply/pushPaymentApply',
	               contentType: "application/json;charset=UTF-8",
	               data: paId,
	               success: function (data) {
	               	var content = "推送成功";
	               	if(data === "false"){
	               		content = "推送失败！";
	               	}else{
	               		$("#paymentApplyTable").cgetData(true,queryTableBack);  //列表重新加载
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
			
		}else{
			alertInfo('请选择要推送的记录！');
		}
	});
}
/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	searchData = $('#searchForm_paymentApply').serializeJson();
	var projNo = $('#projectTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#projectTable').cgetData(true,subTableCallBack); 
}

var subTableCallBack=function(){
	var len = $('#projectTable').DataTable().ajax.json().data ? $('#projectTable').DataTable().ajax.json().data.length : $('#projectTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#paymentApplyForm')[0].reset();
		 $(':input','#paymentApplyForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
         $('.updateAcceptanceBtn,.addBtn').addClass("hidden");
	  }

}

/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$("#projId1").val(json.projId);
	//$("#scId1").val('');
    $('.updateAcceptanceBtn,.addBtn').removeClass("hidden");
	if(!$.fn.DataTable.isDataTable('#paymentApplyTable')){
		//初始化列表
		handlePaymentApplyTableTableList();
	}else{
		if($('#projId1').val()!==''){
			searchSubContractData.projId=$('#projId1').val();
		}else{
			searchSubContractData.projId=-1;
		}
		$('#paymentApplyTable').cgetData(true,queryTableBack);
	}
	
	if(trSData.paymentApplyTable.json){
		var projId=json.projId,paId=json.paId;
		var data={"projId":projId,"paId":paId};
		diviaionalAcceptanceDetail(data);
	}else{
		var projId=$("#projId1").val(),paId="";
		var data={"projId":projId,"paId":paId};
		diviaionalAcceptanceDetail(data);
	}
	
	
	$('.addClear').val('');
	$('.toolBtn').addClass('hidden');
	//diviaionalAcceptanceDetail();
}

/**
 * 付款申请列表
 */
var handlePaymentApplyTableTableList = function(){
	"use strict";
	if($('#projId1').val()!==''){
		searchSubContractData.projId=$('#projId1').val();
	}else{
		searchSubContractData.projId=-1;
	}
	console.info(searchSubContractData);
	
    if ($('#paymentApplyTable').length !== 0) {
    	paymentApplyTable= $('#paymentApplyTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack1,true);
   			//隐藏遮罩
   			$('#paymentApplyTable').hideMask();
   			//增加
   			//修改
   			//updateMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                  { text: '推送', className: 'btn-sm btn-query business-tool-btn pushBtn hidden' } ,
                  { text: '增加', className:'btn-sm btn-query business-tool-btn addBtn '},
                  { text: '修改', className:'btn-sm btn-query business-tool-btn updateAcceptanceBtn hidden'},
				  { text: '作废', className:'btn-sm btn-danger business-tool-btn deleteBtn hidden'},
            ],
            serverSide: true, 
            ajax: {
                url: 'paymentApply/queryPaymentApply',
                type:'post',
                data: function(d){
                   	$.each(searchSubContractData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                datasrc: ''
            },
            //ajax: 'projectjs/complete/json/divisional_acceptance.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	  			{"data":"paId",className:"none never"}, 
	  			{"data":"applyer"}, 
	  			{"data":"applyAmount",className:"text-right"}, 
				{"data":"applyDate"},
				{"data":"auditStateDes"},
				{"data":"signBack", className:"none never", "createdCell": function (td, cellData, rowData, row, col) {
					if(cellData == $("#notThrough").val()){
						$(td).parent().children().css("background", "rgb(255, 219, 219)");
					}
				}
				},
			],
			 columnDefs: [{
				 "targets": 0,
				 "visible":false
			 },{
				 'targets':2,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							return parseFloat(data).toFixed(2);
						}else{
							return data;
						}
					},
			 	},{
					"targets":4,
					 "orderable":false
				}],
			ordering:false
        });
    }
}
//增加
var addMonitor = function(){
	$(".addBtn").off().on("click",function(){
        var data = paymentApplyTable.rows().data(),
            addFlag;
        for(var i = 0;i<data.length;i++){
            if(data[i].auditState == "1"){
                addFlag="1";
                break;
            }
        }
        /*if(addFlag=="1"){//如果有审核中的工程，提示不能增加
            //弹屏
            $("body").cgetPopup({
                title: '提示',
                content: "有信息正在审核中，不允许增加",
                chide: 'true',
                accept: popClose
            });
        }else{*/
			$("#flag").val("1");
			$("#detailTab").tab("show");
			$('.toolBtn').removeClass('hidden');
			$("#paymentApplyForm").toggleEditState(true);
        /*}*/
	});
}
//修改
var updateMonitor = function(){
	deleteClick();//作废
	$(".updateAcceptanceBtn").off("click").on("click",function(){
		var len=$("#paymentApplyTable").find("tr.selected").length;
		if(len>0){
			$("#flag").val("2");
			$('#paymentApplyForm').toggleEditState(true);
			$(".toolBtn").removeClass("hidden");
			$("#detailTab").tab("show");
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的验收单申请信息!',
				ahide:true,
				atext:'确定'
			});
		}
	});
}


function deleteClick() {
	$(".deleteBtn").off("click").on("click",function(){
		var len=$("#paymentApplyTable").find("tr.selected").length;
		if(len<1){
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要作废的验收单信息!',
				ahide:true,
				atext:'确定'
			});
		}else{
			$('body').cgetPopup({//加载弹屏
				title: '修改',
				content: '#intelligentSupplement/suppModifyPopPage',
				accept: deleteDone
			});

		}
	});
}



var deleteDone = function(){//查询弹出屏，点击确定后
	var pf = $("#modifySupplementForm");
	if (pf.parsley().isValid()) { //验证必填是否已填写
		$("#modifySupplementForm").loadMask("保存中···", 1, 0.5);

		var json= trSData.paymentApplyTable.json;
		json.auditState=-1;//作废
		json.deleteReson=$("#modifyRemark").val();

		Base.subimt('paymentApply/deletePaymentApply',"post",JSON .stringify(json),function (data) {
			$("#paymentApplyTable").cgetData(true);
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





var trSelectedBack1 = function(v, i, index, t, json){
	$("#businessOrderId").val(json.paId);

	//0 未推送 1 审核中  2 未通过 3 通过
	if (json.auditState=="0"||json.auditState=="2"){//未推送或未通过
		$(".pushBtn,.deleteBtn,.updateAcceptanceBtn").removeClass("hidden")

	}else if (json.auditState=="1" || json.auditState=="3") {//审核中或通过
		$(".deleteBtn").removeClass("hidden")
		$(".pushBtn,.updateAcceptanceBtn").addClass("hidden")

	} else if (json.auditState=="-1"){//作废
		$(".pushBtn,.deleteBtn,.updateAcceptanceBtn").addClass("hidden")
	}


	var projId="",paId="";
	if(json){
		projId=json.projId;
		paId=json.paId;
	}else{
		projId=$("#projId1").val();
	}
	var data={"projId":projId,"paId":paId};
	//查询操作区详述

	diviaionalAcceptanceDetail(data);
}

//查询详述
var diviaionalAcceptanceDetail = function(data){
	var url = 'paymentApply/viewPaymentApply';
	var f = $("#paymentApplyForm");
	 $.ajax({
         type: 'POST',
         url: url,
         data: data,
         dataType: 'json',
         success: function (data) {
        	 //$("#paymentApplyForm").formReset();
        	 var selects = f.find('select[disabled]');
             selects.removeAttr("disabled");
             $("input:radio").attr("disabled",false);
             //表单反序列化填充值
             f.deserialize(fixNull(data));
             selects.attr("disabled","disabled");
           /*  if(data.payType == "1"){
                 $(".construction").removeClass("hidden");
                 $(".detection").addClass("hidden");
                 $(".supervisor").addClass("hidden");
			 }else if(data.payType == "2"){
                 $(".supervisor").removeClass("hidden");
                 $(".construction").addClass("hidden");
                 $(".detection").addClass("hidden");
			 }else {
                 $(".detection").removeClass("hidden");
                 $(".construction").addClass("hidden");
                 $(".supervisor").addClass("hidden");
			 }*/

     		var surplusAmount=(new Number($("#contractAmount").val())-new Number($("#payAmount").val())).toFixed(2);
     		$("#surplusAmount").val(surplusAmount);
             detailedCallback(data);
         },
         error: function (jqXHR, textStatus, errorThrown) {
             console.warn("cgetDetail() -> 详情查询失败");
         }
     });
}

var queryTableBack=function(){
	var len = $('#paymentApplyTable').DataTable().ajax.json().data ? $('#paymentApplyTable').DataTable().ajax.json().data.length : $('#paymentApplyTable').DataTable().ajax.json().length;
	if(len<1){
		$('.addClear').val('');
		$("#scId1").val('-1');
		diviaionalAcceptanceDetail();
	 }
	$("#paymentApplyForm").toggleEditState(false);
}

/**
 * 初始化工程列表
 */
var paymentApplyTable = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handlePaymentApplyTableTable();

                if(!$.fn.DataTable.isDataTable('#paymentApplyTable')){
                    //初始化列表
                    handlePaymentApplyTableTableList();
                }else{
                    $('#paymentApplyTable').cgetData(true,queryTableBack);
                }

        		/**
            	 * 切换页签
            	 */
            	$("#listTab, #detailTab").off("shown.bs.tab").on("shown.bs.tab",function(){
    				if($(this).is($('#listTab'))){
    					$("#flag").val("");
    					$(".toolBtn").addClass("hidden");
    					if(!$.fn.DataTable.isDataTable('#paymentApplyTable')){
    						//初始化列表
    						handlePaymentApplyTableTableList();
            			}else{
            				$('#paymentApplyTable').cgetData(true,queryTableBack);
            			}
    				}else{
    					if(trSData.paymentApplyTable.json){
    						var json=trSData.paymentApplyTable.json;
    						var projId=json.projId,paId;
    						if($("#flag").val()=="1"){//增加
    							paId="";
    						}else{
    							paId=json.paId
    						}
    						var data={"projId":projId,"paId":paId};
    						diviaionalAcceptanceDetail(data);
        				}else{
        					var projId=$("#projId1").val(),paId="";
    						var data={"projId":projId,"paId":paId};
        					diviaionalAcceptanceDetail(data);
        				}
    				}
            	});
        	});
        }
    };
}();

