var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var projectTable, 			//待签分包工程列表
searchData={},  			//查询条件
divisionalAcceptanceApplyTable,			//施工合同列表
searchSubContractData = {};	//施工合同列表查询条件	
var menuId = "110719";
searchData.menuId = menuId;
var histSearchData={businessOrderId:'1992100519931123'};
/**
 * 加载工程列表
 */
var handledivisionalAcceptanceApply = function() {
	'use strict';
    if ($('#projectTable').length !== 0) {
    	projectTable=$('#projectTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#sub_contract_panel_box').cgetContent('divisionalAcceptanceApply/viewPage',{projId:'',projectType:'',corpId:'',menuId:menuId});
   			//隐藏遮罩
   			$('#projectTable').hideMask();
   			$('#projectTable_filter input').attr('placeholder','工程编号');
   			//搜索监听
   			searchMonitor();
   			//推送监听
   			pushMonitor();
   			updateMonitor();
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
            	 url: 'divisionalAcceptanceApply/queryProject',
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
	  			 {'data':'projNo'}, 
				 {'data':'projName'},
				 {'data':'projStatusDes'},
				 {"data":"overdue",className:"none never" },
					{"data":"isSpecialSign",className:"none never",
						"createdCell": function (td, cellData, rowData, row, col) {
							if(cellData=='1'){
								$(td).parent().children().css("color", "rgb(255, 99, 95)");
							}
						}
	  				}
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
		var url = '#subContract/projectSearchPopPage';
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
		searchData.projNo = projNo;searchData.menuId = menuId;
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
		if($("#divisionalAcceptanceApplyTable").find("tr.selected").length>0){
			var daaId=trSData.divisionalAcceptanceApplyTable.json.daaId;
	       	$.ajax({
	               type: 'POST',
	               url: 'divisionalAcceptanceApply/pushDivisionalAcceptanceApply',
	               contentType: "application/json;charset=UTF-8",
	               data: daaId,
	               success: function (data) {
	               	var content = "推送成功";
	               	if(data === "false"){
	               		content = "推送失败！";
	               	}else{
	               		$("#divisionalAcceptanceApplyTable").cgetData(true,queryTableBack);  //列表重新加载
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
	searchData = $('#searchForm_sub').serializeJson();
	var projNo = $('#projectTable_filter input').val();
	searchData.projNo = projNo;searchData.menuId = menuId;
	//列表重新加载
    $('#projectTable').cgetData(true,subTableCallBack); 
}

var subTableCallBack=function(){
	var len = $('#projectTable').DataTable().ajax.json().data ? $('#projectTable').DataTable().ajax.json().data.length : $('#projectTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#divisionalAcceptanceApplyForm')[0].reset();
		 $(':input','#divisionalAcceptanceApplyForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
         $('.updateAcceptanceBtn,.addBtn').addClass("hidden");
	  }

}
var dataBack= function(json){
	$("#projId1").val(json.projId);
	$("#scId1").val('');
    $('.updateAcceptanceBtn,.addBtn').removeClass("hidden");
	if(!$.fn.DataTable.isDataTable('#divisionalAcceptanceApplyTable')){
		//初始化列表
		handledivisionalAcceptanceApplyList();
	}else{
		if($('#projId1').val()!==''){
			searchSubContractData.projId=$('#projId1').val();
		}else{
			searchSubContractData.projId=-1;
		}
		$('#divisionalAcceptanceApplyTable').cgetData(true,queryTableBack);
	}
	if(trSData.divisionalAcceptanceApplyTable.json){
		var projId=json.projId,daaId=json.daaId;
		var data={"projId":projId,"daaId":daaId};
		diviaionalAcceptanceDetail(data);
	}else{
		var projId=$("#projId1").val(),daaId="";
		var data={"projId":projId,"daaId":daaId};
		diviaionalAcceptanceDetail(data);
	}
	
	
	$('.addClear').val('');
	$('.toolBtn').addClass('hidden');
	//diviaionalAcceptanceDetail();
	
	initIsAuditHistory();
}
/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	//加载右侧页面后，加载右侧页面数据
	$("#sub_contract_panel_box").cgetContent("divisionalAcceptanceApply/viewPage",{projId:json.projId,projectType:json.projectType,corpId:json.corpId,menuId:menuId},function(){
		dataBack(json); 
	});
}

/**
 * 分部验收列表
 */
var handledivisionalAcceptanceApplyList = function(){
	"use strict";
	if($('#projId1').val()!==''){
		searchSubContractData.projId=$('#projId1').val();
	}else{
		searchSubContractData.projId=-1;
	}
    if ($('#divisionalAcceptanceApplyTable').length !== 0) {
    	divisionalAcceptanceApplyTable= $('#divisionalAcceptanceApplyTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack1,true);
   			//隐藏遮罩
   			$('#divisionalAcceptanceApplyTable').hideMask();
   			//增加
   			//修改
   			//updateMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                  { text: '推送', className: 'btn-sm btn-query business-tool-btn pushBtn' } ,   
                  { text: '增加', className:'btn-sm btn-query business-tool-btn addBtn hidden'},
                  { text: '修改', className:'btn-sm btn-query business-tool-btn updateAcceptanceBtn hidden'},
                  
            ],
            serverSide: true, 
            ajax: {
                url: 'divisionalAcceptanceApply/queryDivisionalAcceptance',
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
	  			{"data":"daaId",className:"none never"}, 
	  			{"data":"applyer"}, 
				{"data":"applyDate"},
				{"data":"auditStateDes"},
				 {"data":"signBack",
					className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData==$("#notThrough").val()){
							$(td).parent().find("td").css("background", "rgb(255, 219, 219)");
							//$(td).attr("id",row);
						}
					}
					},
			],
			 columnDefs: [{
				 "targets": 0,
				 "visible":false
			 }],
			ordering:false
        });
    }
}



var initIsAuditHistory = function(){
	var businessOrderId="";
	if(trSData.divisionalAcceptanceApplyTable.json){
		businessOrderId = json.daaId;
	}else{
		businessOrderId="-1";
	}
	//审核记录列表
	histSearchData.businessOrderId = businessOrderId;
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
            url: 'divisionalAcceptanceAudit/queryManageRecord',
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





//增加
var addMonitor = function(){
	$(".addBtn").off().on("click",function(){
		$("#isAdd").val("1"); // 修改标识符
        var data = divisionalAcceptanceApplyTable.rows().data(),
			addFlag;
        for(var i = 0;i<data.length;i++){
        	if(data[i].auditState == "1"){
                addFlag="1";
                break;
			}
        }
		if(addFlag=="1"){//如果有审核中的工程，提示不能增加
            //弹屏
            $("body").cgetPopup({
                title: '提示',
                content: "有信息正在审核中，不允许增加",
                chide: 'true',
                accept: popClose
            });
		}else{
			$("#flag").val("1");
			$("#detailTab").tab("show");
			$('.toolBtn').removeClass('hidden');
			$("#divisionalAcceptanceApplyForm").toggleEditState(true);
			$("#applyDate").val(format($("#sysDate").val()));
		}

	});

}
//修改
var updateMonitor = function(){
	$(".updateAcceptanceBtn").off("click").on("click",function(){
		$("#isAdd").val("0"); // 修改标识符
		var len=$("#divisionalAcceptanceApplyTable").find("tr.selected").length;
		if(len>0){
			$("#flag").val("2");
			$('#divisionalAcceptanceApplyForm').toggleEditState(true);
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
var trSelectedBack1 = function(v, i, index, t, json){

    histSearchData.businessOrderId=json.daaId;
    $("#auditHistoryTable").cgetData(true);


    $("#businessOrderId").val(json.daaId);
    //0 未推送 1 审核中  2 未通过 3 通过
	if(json.auditState=="0"||json.auditState=="2"){
		$(".pushBtn").removeClass("hidden");
	}else{
		$(".pushBtn").addClass("hidden");
	}
	
	if(json.auditState=="3" || json.auditState=="1"){
		$(".updateAcceptanceBtn").addClass("hidden");
	}else{
		$(".updateAcceptanceBtn").removeClass("hidden");
	}

	var projId="",daaId="";
	if(json){
		projId=json.projId;
		daaId=json.daaId;
	}else{
		projId=$("#projId1").val();
	}
	var data={"projId":projId,"daaId":daaId};
	//查询操作区详述
	diviaionalAcceptanceDetail(data);
}

//查询详述
var diviaionalAcceptanceDetail = function(data){
	var url = 'divisionalAcceptanceApply/viewDivisionalAcceptanceApply';
	var f = $("#divisionalAcceptanceApplyForm");
	 $.ajax({
         type: 'POST',
         url: url,
         data: data,
         dataType: 'json',
         success: function (data) {
        	 //$("#divisionalAcceptanceApplyForm").formReset();
        	 var selects = f.find('select[disabled]');
             selects.removeAttr("disabled");
             $("input:radio").attr("disabled",false);
             //表单反序列化填充值
             f.deserialize(fixNull(data));
             selects.attr("disabled","disabled");
         },
         error: function (jqXHR, textStatus, errorThrown) {
             console.warn("cgetDetail() -> 详情查询失败");
         }
     });
}



var queryTableBack=function(){
	var len = $('#divisionalAcceptanceApplyTable').DataTable().ajax.json().data ? $('#divisionalAcceptanceApplyTable').DataTable().ajax.json().data.length : $('#divisionalAcceptanceApplyTable').DataTable().ajax.json().length;
	if(len<1){
		$('.addClear').val('');
		$("#scId1").val('-1');
		//diviaionalAcceptanceDetail();
	 }
	$("#divisionalAcceptanceApplyForm").toggleEditState(false);
}


/**
 * 初始化工程列表
 */
var divisionalAcceptanceApply = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handledivisionalAcceptanceApply();

                if(!$.fn.DataTable.isDataTable('#divisionalAcceptanceApplyTable')){
                    //初始化列表
                    handledivisionalAcceptanceApplyList();
                }else{
                    $('#divisionalAcceptanceApplyTable').cgetData(true,queryTableBack);
                }
                initIsAuditHistory();

        		/**
            	 * 切换页签
            	 */
            	$("#listTab, #detailTab").off("shown.bs.tab").on("shown.bs.tab",function(){
    				if($(this).is($('#listTab'))){
    					$(".toolBtn").addClass("hidden");
    					if(!$.fn.DataTable.isDataTable('#divisionalAcceptanceApplyTable')){
    						//初始化列表
    						handledivisionalAcceptanceApplyList();
            			}else{
            				$('#divisionalAcceptanceApplyTable').cgetData(true,queryTableBack);
            			}
                        //审核记录列表
    					initIsAuditHistory();
    				}else{
    					if(trSData.divisionalAcceptanceApplyTable.json){
    						console.info("1---------");
    						
    						var json=trSData.divisionalAcceptanceApplyTable.json;
    						var projId=json.projId,daaId;
    						if($("#flag").val()=="1"){//增加
    							daaId="";
    						}else{
    							daaId=json.daaId
    						}
    						var data={"projId":projId,"daaId":daaId};
    						diviaionalAcceptanceDetail(data);
        				}else{
        					
        					console.info("2---------");
        					
        					var projId=$("#projId1").val(),daaId="";
    						var data={"projId":projId,"daaId":daaId};
        					diviaionalAcceptanceDetail(data);
        				}
    				}
            	});
        	});
        }
    };
}();

