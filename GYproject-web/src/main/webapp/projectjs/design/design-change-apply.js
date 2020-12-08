var projectTable, 			//待签分包工程列表
searchData={},	   			//查询条件
designChangeApplyTable,			//施工合同列表
searchSubContractData = {};	//施工合同列表查询条件	
/**
 * 加载工程列表
 */
var handledesignChangeApplyTableTable = function() {
	'use strict';
    if ($('#projectTable').length !== 0) {
    	projectTable=$('#projectTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#sub_contract_panel_box').cgetContent('designChangeApply/viewPage');
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
   			//废弃监听
   			discardMonitor();
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
            	 url: 'designChangeApply/queryContract',
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
				 {"data":"conNo"},
	  			 {"data":"projName"},
	  			 {"data":"signDate"},
	  			 {'data':'contractAmount',className:'text-right'},
			 ],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 },{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 8, 	//截取多少字符（或汉字）
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
		var url = '#designChangeApply/designSearchPopPage';
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
		if($("#designChangeApplyTable").find("tr.selected").length>0){
			var cmId=trSData.designChangeApplyTable.json.cmId;
	       	$.ajax({
	               type: 'POST',
	               url: 'designChangeApply/updateChangeState',
	               contentType: "application/json;charset=UTF-8",
	               data: cmId,
	               success: function (data) {
	               	var content = "推送成功";
	               	if(data === "false"){
	               		content = "推送失败！";
	               	}else{
	               		$("#designChangeApplyTable").cgetData(true,queryTableBack);  //列表重新加载
	               	}
	               	var myoptions = {
	                       	title: "提示信息",
	                       	content: content,
	                       	accept: sureDone,
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

var sureDone=function(){
	$("#designChangeApplyTable").cgetData(true,queryTableBack);  //列表重新加载
}

/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	searchData = $('#searchForm_contract').serializeJson();
	var projNo = $('#projectTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#projectTable').cgetData(true,subTableCallBack); 
}

var subTableCallBack=function(){
	var len = $('#projectTable').DataTable().ajax.json().data ? $('#projectTable').DataTable().ajax.json().data.length : $('#projectTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#designChangeApplyForm')[0].reset();
		 $(':input','#designChangeApplyForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
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
	if(!$.fn.DataTable.isDataTable('#designChangeApplyTable')){
		//初始化列表
		handledesignChangeApplyTableTableList();
	}else{
		if($('#projId1').val()!==''){
			searchSubContractData.projId=$('#projId1').val();
		}else{
			searchSubContractData.projId=-1;
		}
		$('#designChangeApplyTable').cgetData(true,queryTableBack);
	}
	
	if(trSData.designChangeApplyTable.json){
		var projId=json.projId,cmId=json.cmId;
		var data={"projId":projId,"cmId":cmId};
		diviaionalAcceptanceDetail(data);
	}else{
		var projId=$("#projId1").val(),cmId="";
		var data={"projId":projId,"cmId":cmId};
		$(".audit").addClass("hidden");
		diviaionalAcceptanceDetail(data);
	}
	$('.addClear').val('');
	$('.toolBtn').addClass('hidden');
	//diviaionalAcceptanceDetail();
}

/**
 * 付款申请列表
 */
var handledesignChangeApplyTableTableList = function(){
	"use strict";
	if($('#projId1').val()!==''){
		searchSubContractData.projId=$('#projId1').val();
	}else{
		searchSubContractData.projId=-1;
	}
	
    if ($('#designChangeApplyTable').length !== 0) {
    	designChangeApplyTable= $('#designChangeApplyTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack1,true);
   			//隐藏遮罩
   			$('#designChangeApplyTable').hideMask();
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
                  { text: '废弃', className:'btn-sm btn-warn business-tool-btn  discardBtn hidden'},
                  
            ],
            serverSide: true, 
            ajax: {
                url: 'designChangeApply/queryChange',
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
	  			{"data":"cmId",className:"none never"}, 
	  			{"data":"cmNo"}, 
	  			{"data":"cmAdvanceStaffName"}, 
				{"data":"applyDate"},
				{"data":"designChangeTypeDes"},
				{"data":"toDoer"}
			],
			 columnDefs: [{
				 "targets": 0,
				 "visible":false
			 }],
			ordering:false
        });
    }
}
//增加
var addMonitor = function(){
	$(".addBtn").off().on("click",function(){
		$("#flag").val("1");
		$("#detailTab").tab("show");
		$('.toolBtn').removeClass('hidden');
		$("#designChangeTypeMark").val('');
		$("#designChangeApplyForm").toggleEditState(true);
		$(".cancelRemark").addClass("hidden"); 
		$("#cancelRemark").attr("readonly",true); 
		$(".cancelDate").addClass("hidden");
		$(".cancelStaffName").addClass("hidden");
	});
}
//修改
var updateMonitor = function(){
	$(".updateAcceptanceBtn").off("click").on("click",function(){
		var len=$("#designChangeApplyTable").find("tr.selected").length;
		if(len>0){
			$("#flag").val("2");
			$('#designChangeApplyForm').toggleEditState(true);
			$(".toolBtn").removeClass("hidden");
			$("#detailTab").tab("show");
		    $("#designChangeTypeMark").val('');
			$(".cancelRemark").addClass("hidden"); 
			$("#cancelRemark").attr("readonly",true); 
			$(".cancelDate").addClass("hidden");
			$(".cancelStaffName").addClass("hidden");
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
//废弃
var discardMonitor = function(){
	$(".discardBtn").off("click").on("click",function(){
		var len=$("#designChangeApplyTable").find("tr.selected").length;
		if(len>0){
			$("#flag").val("3");
			$('#designChangeApplyForm').toggleEditState(false);
			$(".toolBtn").removeClass("hidden");
		    $("#designChangeTypeMark").val("-1"); //废弃标志
			$(".cancelRemark").removeClass("hidden"); 
			$("#cancelRemark").attr("readonly",false); 
			$(".cancelDate").removeClass("hidden");
			$(".cancelStaffName").removeClass("hidden");
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
	//0 未推送 1 审核中  2 未通过 3 通过

	if(json.designChangeType=="1"){
		$(".pushBtn").removeClass("hidden");
		$(".updateAcceptanceBtn").removeClass("hidden");
	}else{
		$(".pushBtn").addClass("hidden");
		$(".updateAcceptanceBtn").addClass("hidden");
	}
	if(json.designChangeType=="-1"){
	
		$(".cancelRemark").removeClass("hidden"); 
		$(".cancelDate").removeClass("hidden");
		$(".cancelStaffName").removeClass("hidden");
	}else{
		$(".discardBtn").removeClass("hidden");
		$(".cancelRemark").addClass("hidden"); 
		$(".cancelDate").addClass("hidden");
		$(".cancelStaffName").addClass("hidden");
	}
	if(json.designChangeType=="" || json.designChangeType==null ){
		$(".discardBtn").addClass("hidden");
		$(".cancelRemark").addClass("hidden"); 
		$(".cancelDate").addClass("hidden");
		$(".cancelStaffName").addClass("hidden");
	}
	
	var projId="",cmId="";
	if(json){
		projId=json.projId;
		cmId=json.cmId;
	}else{
		projId=$("#projId1").val();
	}
	var data={"projId":projId,"cmId":cmId};
	//查询操作区详述
	diviaionalAcceptanceDetail(data);
}

//查询详述
var diviaionalAcceptanceDetail = function(data){
	var url = 'designChangeApply/viewChangeManagement';
	var f = $("#designChangeApplyForm");
	 $.ajax({
         type: 'POST',
         url: url,
         data: data,
         dataType: 'json',
         success: function (data) {
        	 var selects = f.find('select[disabled]');
             selects.removeAttr("disabled");
             $("input:radio").attr("disabled",false);
             //表单反序列化填充值
             f.deserialize(fixNull(data));
             selects.attr("disabled","disabled");
             if($("#cmAdvanceStaffName").val()==""){   //如果申请人为空，取当前登录人员
            	 $("#cmAdvanceStaffName").val($("#loginName").val());
             }
             if($("#applyDate").val()==""){            //如果申请时间为空，取当前登录时间
                 var sysDate = timestamp($("#sysDate").val());
                 $("#applyDate").val(format(sysDate,"default"));
             }  
             if($("#cancelStaffName").val()==""){   //当废弃申请人为空取当前登录人
            	 $("#cancelStaffName").val($("#loginName").val());
             }
             if($("#cancelDate").val()==""){    //废弃申请时间为空取当前系统时间
                 var sysDate = timestamp($("#sysDate").val());
                 $("#cancelDate").val(format(sysDate,"default"));
             }
         },
         error: function (jqXHR, textStatus, errorThrown) {
             console.warn("cgetDetail() -> 详情查询失败");
         }
     });
}

var queryTableBack=function(){
	var len = $('#designChangeApplyTable').DataTable().ajax.json().data ? $('#designChangeApplyTable').DataTable().ajax.json().data.length : $('#designChangeApplyTable').DataTable().ajax.json().length;
	if(len<1){
		$('.addClear').val('');
		$("#scId1").val('-1');
		//diviaionalAcceptanceDetail();
		$(".pushBtn").addClass("hidden");
		$(".updateAcceptanceBtn").addClass("hidden");
	}else{
		if(trSData.designChangeApplyTable.json){
			if(trSData.designChangeApplyTable.json.designChangeType == "1"){
				$(".pushBtn").removeClass("hidden");
				$(".updateAcceptanceBtn").removeClass("hidden");
			}else{
				$(".pushBtn").addClass("hidden");
				$(".updateAcceptanceBtn").addClass("hidden");
			}	
		}
	}
	$("#designChangeApplyForm").toggleEditState(false);
}

/**
 * 初始化工程列表
 */
var designChangeApplyTable = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handledesignChangeApplyTableTable();

                if(!$.fn.DataTable.isDataTable('#designChangeApplyTable')){
                    //初始化列表
                    handledesignChangeApplyTableTableList();
                }else{
                    $('#designChangeApplyTable').cgetData(true,queryTableBack);
                }

        		/**
            	 * 切换页签
            	 */
            	$("#listTab, #detailTab").off("shown.bs.tab").on("shown.bs.tab",function(){
    				if($(this).is($('#listTab'))){
    					$("#flag").val("");
    					$(".toolBtn").addClass("hidden");
    					if(!$.fn.DataTable.isDataTable('#designChangeApplyTable')){
    						//初始化列表
    						handledesignChangeApplyTableTableList();
            			}else{
            				$('#designChangeApplyTable').cgetData(true,queryTableBack);
            			}
    				}else{
    					if(trSData.designChangeApplyTable.json){
    						var json=trSData.designChangeApplyTable.json;
    						var projId=json.projId,cmId;
    						if($("#flag").val()=="1"){//增加
    							cmId="";
    						}else{
    							cmId=json.cmId
    						}
    						var data={"projId":projId,"cmId":cmId};
    						diviaionalAcceptanceDetail(data);
        				}else{
        					var projId=$("#projId1").val(),cmId="";
    						var data={"projId":projId,"cmId":cmId};
        					diviaionalAcceptanceDetail(data);
        				}
    				}
            	});
        	});
        }
    };
}();

