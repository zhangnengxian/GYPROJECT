/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
/**查询条件*/
var subSearchData = {},qualitiesTable,settlementAuditDoneTable,treeArr=[],histSearchData = {};

subSearchData.projId = $("#projId").val();
subSearchData.settlementState = $("#mrAuditLevel").val();

var handleSettlementDetial = function(){
	var url = 'settlementConfirm/viewSettlement';
	var projId =$('#projId').val();
	var data = "id="+projId;
	var f = $("#reportConfirmForm");
	 $.ajax({
         type: 'POST',
         url: url,
         data: data,
         dataType: 'json',
         success: function (data) {
        	 data = fixNull(data);
             //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
             var selects = f.find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
             selects.removeAttr("disabled");
             //表单反序列化填充值
             f.deserialize(data);
             f.initFixedNumber();
             //有disabled属性的下拉菜单元素对象重新添加禁用属性
             selects.attr("disabled","disabled");
             detailCallBack(data);
           //  $("#cuName").val(getProjectInfo().cuName);
         },
         error: function (jqXHR, textStatus, errorThrown) {
             console.warn("cgetDetail() -> 详情查询失败");
         }
     });
	
}
var detailCallBack = function(data){
	//$("#businessOrderId").val(data.sdId);
	qualitiesInit();
	if(data.endDeclarationCost==null||data.endDeclarationCost==''){
		$('#endDeclarationCost').val(data.firstDeclarationCost);
	}
	//auditHistoryInit(data);
}
/**
 * 增加工程量
 */
function addQualities(){
$(".addBtn").off("click").on("click",function(){
	var url = "#settlementConfirm/standardPop";
	//加载弹屏
	$("body").cgetPopup({
		title: '工程量标准',
		content: url,
		accept: sureDone,
		size:"large"
	});
});
}
function sureDone(){
	var rows = [],arr=[],addArr=[],removeArr=[];
	 arr=$('#jstree-safe').jstree().get_selected();
	 addArr=$('#jstree-safe').jstree().get_selected();	
	   for(var i=0;i<arr.length;i++){			
			if(treeArr.length>0){
				for(var j=0;j<treeArr.length;j++){
					if(treeArr[j]==arr[i]){
						treeArr.splice(j,1,'');
					    arr.splice(i,1,'');
					}
				}
			}
	   }
	   //添加行
	  if(arr.length>0){
		for(var m=0;m<arr.length;m++){
			if(arr[m]!=""){
			if(arr[m].indexOf("@@") === -1) {				
				continue;
			}
			var json = {};
			json.id = arr[m];
			arr[m] = arr[m].split("@@");
			json.sqStandardId = arr[m][0];
			json.sqBranchProjName = arr[m][1];
			json.sqUnit = arr[m][2];
			json.sqLabourPrice = arr[m][3];
			json.sqNum = '';
			json.sqAmount = '';
			rows.push(json);
		}
		}
	  }
	  //删除行
	if(treeArr.length>0){
		for(var n=0;n<treeArr.length;n++){
			if(treeArr[n]!=""){
			if(treeArr[n].indexOf("@@") === -1) {				
				continue;
			}
			sqStandardId = treeArr[n];
			$("#qualitiesTable").DataTable().rows(function ( idx, data, node ) {
				
				return data.id==sqStandardId ? true : false;
					 console.info(idx);
					 console.info( data.id );
					
				
		       
		               
		        }).remove().draw();
			
		}
		}
	}
	   treeArr=addArr;	
	   qualitiesTable.rows.add(rows).draw();
	   $("#qualitiesForm").toggleEditState(false);
    }


/**
 * 初始化右侧工程量列表
 */
var qualitiesInit = function() {
	"use strict";
	//$("#init_quantitiesTotal").val($("#quantitiesTotal").val());
    if ($('#qualitiesTable').length !== 0) {
    	if(!$.fn.DataTable.isDataTable('#qualitiesTable')){
    	    qualitiesTable= $('#qualitiesTable').on( 'init.dt',function(){
	    		//amountSum();//计算每行的金额及总金额
	    		addQualities();//添加行
	    		saveQualities();//保存
	    		delFun();//删除
	    		delData();
	    		//隐藏遮罩
	   			$('#qualitiesTable').hideMask();
	    		$(".cancleBtn").on("click",function(){
	    			$("body").cgetPopup({
	    				title: '提示信息',
	    				content: '确认要放弃操作吗?',
	    				accept: cancleFun,
	    				icon: 'fa-exclamation-circle',
	    			});
	    			
	    		});
	    			$(".right-btn").removeClass("hidden");  
	    		$(this).bindInputsChange(amountSum);

	    		//计算总值
	    		var totalRow = 0;
	    		if(qualitiesTable.column(6).data().length){
	    			totalRow = qualitiesTable.column(6).data().reduce( function (a,b) {
		    			a = a || 0;
		    			b = b || 0;
		    	        return parseFloat(a) + parseFloat(b);
		    	    });
	    		}
	    	    $(".total-amount").text(parseFloat(totalRow).toFixed(2));
	    	    $("#init_quantitiesTotal").val($("#quantitiesTotal").val());
	    	    changeAText();
	    	    auditHistoryInit();
	    	    if($("#mrAuditLevel").val()=='1'){
	    	    	if($("#isAudit").val()=='1'){
	    	    		$('#reportConfirmForm').toggleEditState(false);
		    			$(".auditSaveBtn").addClass("hidden");
		    			$(".right-btn").addClass("hidden");
	    	    	}else{
	    	    		$('#reportConfirmForm').toggleEditState(true);
	    	    		
	    	    		$(".auditSaveBtn").removeClass("hidden");
		    			$(".right-btn").removeClass("hidden");
	    	    	}
	    		}else{
	    			$('#reportConfirmForm').toggleEditState(false);
	    			$(".auditSaveBtn").addClass("hidden");
	    			$(".right-btn").addClass("hidden");
	    		}
	        }).DataTable({
	        	language: language_CN,
//              lengthMenu: [18],
	            dom: 'Brtip',
	            buttons: [
	                { text: '增加', className: 'btn-sm  btn-query business-tool-btn right-btn addBtn  hidden' },
	                { text: '保存', className: 'btn-sm  btn-confirm business-tool-btn right-btn saveBtn  hidden' },
	                { text: '删除', className: 'btn-sm  btn-warn business-tool-btn right-btn delBtn  hidden' },
	                //{ text: '放弃', className: 'btn-sm  btn-warn business-tool-btn right-btn cancleBtn  hidden' }
	            ],
	            /*ajax: 'projectjs/subcontract/json/qualities.json',*/
	            //启用服务端模式，后台进行分段查询、排序
	//          serverSide:false,
	            ajax: {
	                url: 'settlementDeclaration/queryQuantityStandard',
	                type: 'post',
	                data: function(d){
	                   	$.each(subSearchData,function(i,k){
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
	            select: true,  //支持多选
	            columns: [
	                {"data":"id", className:"none never"},
	                {"data":"sqStandardId", className:"none never"},
		  			{"data":"sqBranchProjName", responsivePriority: 1}, 
					{"data":"sqUnit", className:"text-center", responsivePriority: 5},
					{"data":"sqLabourPrice", className:"text-right", responsivePriority: 4},
					{"data":"sqNum", className:"text-right", responsivePriority: 3},
					{"data":"sqAmount", className:"text-right amount", responsivePriority: 2}
				],
				columnDefs: [{
					"targets":0,
					"visible":false
				},{
					"targets":1,
					"visible":false
				},{
	  				 "targets":4,
					 "render": function ( data, type, row, meta ) {
						 if(type === "display"){
							if(data!=="" && data!==null){
								var pdata = parseFloat(data).toFixed(2);
							}else{
								var pdata = data;
							}
							var tdcon='<div class="tdInnerInput"><input name="' + row.sqStandardId + '_sqLabourPrice" class="form-control input-sm text-right" data-parsley-type="number" readonly value="' + pdata + '"></div>';
							return tdcon;
						}else{
							return data;
						}
					}
				},{
					"targets": 5,
					"render": function ( data, type, row, meta ) {
						if(type === "display"){
							data = data || 0;
							var tdcon='<div class="tdInnerInput"><input name="' + row.sqStandardId + '_sqNum" class="form-control input-sm text-right numbers" data-parsley-type="number" value="' + data + '"></div>';
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
							var tdcon='<div class="tdInnerInput"><input name="' + row.sqStandardId + '_sqAmount" class="form-control input-sm text-right" data-parsley-type="number" readonly value="' + data + '"></div>';
							return tdcon;
						}else{
							return data;
						}
					}
				}],
				ordering:false
	        });
	    }else{
	    	/*if(trSData.settlementAuditDoneTable.t){
	  		  $(".right-btn").removeClass("hidden");   		
	  		}else{
	  		  $(".right-btn").addClass("hidden");
	  		  treeArr=[];
	  		}*/
	    }
    }
};

function trQualitiesBack(){
	
}

/**
 * 表格计算
 */
var amountSum=function(v, input, t, dt){
	var td = $('[name=' + input.attr("name") + ']').closest("td"),
	//当前表格值
	qualities = v || 0,
	//上一个td值
	price = dt.cell(td.prev("td")).data() || 0,
	//计算值
	amount = (parseFloat(qualities) * parseFloat(price)).toFixed(2);
	//放入计算结果
	dt.cell(td.next("td")).data(amount);
	td.next("td").find("input").val(amount);
	
	//计算总值
	var totalRow = 0;
	if(dt.column(6).data().length){
		totalRow = dt.column(6).data().reduce( function (a,b) {
			a = a || 0;
			b = b || 0;
	        return parseFloat(a) + parseFloat(b);
	    });
	}
	
    $(".total-amount").text(parseFloat(totalRow).toFixed(2));
    $("#quantitiesTotal").val(totalRow.toFixed(2));
	//计算核减
	$("#endAuditMinusCost").val(new Number(($("#firstDeclarationCost").val())-(new Number($("#endDeclarationCost").val()))).toFixed(2));
	if($("#endAuditMinusCost").val()==0){
		$("#endAuditMinusRate").val($("#endAuditMinusCost").val());
	}else{
		$("#endAuditMinusRate").val(((new Number($("#endAuditMinusCost").val())/new Number($("#firstDeclarationCost").val()))*100).toFixed(2)+''+'%');
	}
};

/*
 * 核减计算
 */
function budgetCallback(){	
}

function saveQualities(){
	$(".saveBtn").on("click",function(){
		var t = $("#qualitiesTable");
		if(t.checkInputs()){
		  	 $("body").cgetPopup({
					title: '提示信息',
					content: '确认修改工程量?',
					accept: saveData,
					icon: 'fa-exclamation-circle',
				});
				
		}else{
			console.info("表单校验失败，请检查并修改未通过项目！");
		}
	});
}
function delFun(){
	$(".delBtn").on("click",function(){
	    $("body").cgetPopup({
			title: '提示信息',
			content: '确认要删除数据吗？',
			accept: delData,
			icon: 'fa-exclamation-circle',
		});
	});	
}
function delData(){
	var rows = $("#qualitiesTable").DataTable().rows( '.selected' ).remove().draw();
	var arr=[];
	var json=$("#qualitiesTable").DataTable().rows().data();
	for(var i=0;i<json.length;i++){
		arr.push(json[i].id);
	}
	var totalRow = 0;
	if($("#qualitiesTable").DataTable().column(6).data().length){
		totalRow = $("#qualitiesTable").DataTable().column(6).data().reduce( function (a,b) {
			a = a || 0;
			b = b || 0;
	        return parseFloat(a) + parseFloat(b);
	    });
	}
//    console.info("deldata:"+totalRow);
	 $(".total-amount").text(parseFloat(totalRow).toFixed(2));
    $("#quantitiesTotal").val(totalRow.toFixed(2));
	//计算核减
	$("#endAuditMinusCost").val((new Number($("#firstDeclarationCost").val())-(new Number($("#endDeclarationCost").val()))).toFixed(2));
	if($("#endAuditMinusCost").val()==0){
		$("#endAuditMinusRate").val($("#endAuditMinusCost").val());
	}else{
		$("#endAuditMinusRate").val(((new Number($("#endAuditMinusCost").val())/new Number($("#firstDeclarationCost").val()))*100).toFixed(2)+''+'%');
	}
	treeArr=arr;
	//console.log(treeArr);
}
function saveData(){
	
	var dataObj={};
	var data1=[];
	var json=$("#qualitiesTable").DataTable().data();
		dataObj.list = [];
		$.each(json, function(k,v){
			if(!v.sqNum) return true;
			dataObj.list.push(v);
		})
	    dataObj.projId=$("#projId").val();
	    dataObj.projNo=$("#projNo").val();
	    dataObj.projName=$("#projName").val();
	    dataObj.projScaleDes=$("#projScaleDes").val();
	    dataObj.settlementState=$("#mrAuditLevel").val();
//	    console.info('== '+$('.total-amount').text());
	    dataObj.submitAmount = $('.total-amount').text();  // 申报金额合计
	    console.info(dataObj.settlementState);
//	    console.info(dataObj);
	  var data=JSON.stringify(dataObj);
	  if(response){
          response.abort();
      }
	  var response = $.ajax({
           url: "settlementDeclaration/insertSubQualities",
          type: "POST",
          timeout : 5000,
          contentType: "application/json;charset=UTF-8",
          data: data,
          success: function (data) {
        	  if (data !== "false") {
        		  $(".total-amount").text("");
        		  $("body").cgetPopup({
                  	title: "提示信息",
                  	content: "数据保存成功!",
                  	accept: successBack,
                  	chide: true,
                  	icon: "fa-check-circle",
                  	newpop: 'new'
                  }); 
        		  $("#init_quantitiesTotal").val($("#quantitiesTotal").val());
        	  }else{
        		  $("body").cgetPopup({
                  	title: "提示信息",
                  	content: "数据保存失败, 请重试! <br>" + data,
                  	accept: popClose,
                  	chide: true,
                  	icon: "fa-exclamation-circle",
                  	newpop: 'new'
                  });  
        	  }
        	  }
          });
	

}

function successBack(){
//	$("#settlementAuditDoneTable").cgetData(true,function(){
//		dataBack();
//	});
	$("#qualitiesTable").cgetData(false,delData);//列表重新加载
}
function dataBack(){
	/*var len = $('#settlementAuditDoneTable').DataTable().data().length;
	console.info(len);
	if(len<1){
		subSearchData.projId = '-1';
		if($.fn.DataTable.isDataTable('#qualitiesTable')){
			//初始化过
			$("#qualitiesTable").cgetData(false);//列表重新加载
		}else{
			qualitiesInit();
		}	
		$(".total-amount").text("");	
		$(".right-btn").addClass("hidden");
	}else{
		//$(".right-btn").removeClass("hidden");
	}*/
}
function cancleFun(){
	treeArr=[];
	$(".total-amount").text("");
	//$("#qualitiesTable").DataTable().rows().remove().draw();
	$("#qualitiesTable").cgetData(false,delData);//列表重新加载
}

var sttlementAuditDone = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleSettlementDetial();
        	
        }
    };
}();


/**
 * 初始化右侧审核列表
 */
var auditHistoryInit = function() {
	"use strict";
	$('#businessOrderId').val($("#sdId").val());
	$('#projId1').val($("#projId").val());
	$('#projNo1').val($("#projNo").val());
	histSearchData.businessOrderId=$("#sdId").val();
	histSearchData.projId=$("#projId").val();
	//histSearchData.stepId=$("#projId").val();
	histSearchData.settlementState="end";
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
            destroy:true, //Cannot reinitialise DataTable,解决重新加载表格内容问题
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'settlementConfirm/queryManageRecord',
                type:'post',
               // timeout : 5000,
               // contentType: "application/json;charset=UTF-8",
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