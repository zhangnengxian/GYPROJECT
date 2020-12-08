/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
/**查询条件*/
var searchData={},subSearchData = {},qualitiesTable,settlementAuditDoneTable,treeArr=[];
/**
 * 加载工程列表
 */
var handleSttlementAuditDone = function() {
	"use strict";
    if ($('#settlementAuditDoneTable').length !== 0) {
    	settlementAuditDoneTable= $('#settlementAuditDoneTable').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
    		if(settlementAuditDoneTable.data().length < 1) subSearchData.projId = "-1";
   			$("#settlement_auditDone_panel_box").cgetContent("settlementAuditDone/viewPage");
   			//隐藏遮罩
   			$('#settlementAuditDoneTable').hideMask();
   			$("#settlementAuditDoneTable_filter input").attr("placeholder","工程编号");
   	    	//绑定单击事件 弹出搜索框
   			searchMonitor();
   		    //终审按钮事件
   			auditMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '终审', className: 'btn-sm btn-query business-tool-btn m-l-5 auditBtn' }
                ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'settlementAuditDone/queryAuditDone',
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
                {"data":"projId",className:"none never"},
	  			{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"projStatusDes"}
			],
			columnDefs: [{
				'targets':0,
				 'visible':false
			}],
        });
    }
},

/**
 * 弹屏监听方法
 */
searchMonitor = function(){
	$('.searchBtn').on('click',function(){
		var url = '#settlementAuditDone/projectSearchDonePopPage';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});

	// 基础条件查询添加监听
	$('#settlementAuditDoneTable_filter input').on('change', function() {
		var projNo = $('#settlementAuditDoneTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#settlementAuditDoneTable').cgetData(true,tableCallBack); // 列表重新加载
	});
	// 基础条件查询添加回车事件
	$('#settlementAuditDoneTable_filter input').on('keyup', function(event) {
		if (event.keyCode == '13') {
			$(this).change();
		}
	});
},

/**
 * 查询弹出屏，点击确定后 
 */
searchDone = function(){
	searchData = $('#projectSearchPopPage').serializeJson();
	var projNo = $('#settlementAuditDoneTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#settlementAuditDoneTable').cgetData(true,tableCallBack);  
},

/**
 * 选中行时，查询详述
 */
trSelectedBack = function(v, i, index, t, json){
	//$("#settlementAuditDoneform").formReset();
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	trSData.t&&trSData.t.cgetDetail('settlementAuditDoneform','settlementAuditDone/auditDoneDetail','',tableCallBack);
	$(".editbtn").addClass("hidden");
	$(".right-btn").addClass("hidden");
	$("#settlementAuditDoneform").toggleEditState(false);
	
	subSearchData.projId = json.projId;
	if($.fn.DataTable.isDataTable('#qualitiesTable')){
		cancleFun();
		//初始化过
		$("#qualitiesTable").cgetData(false);//列表重新加载
	}else{
		qualitiesInit();
	}
},

tableCallBack = function(){
	delData();
	var len = $('#settlementAuditDoneTable').DataTable().ajax.json().data ? $('#settlementAuditDoneTable').DataTable().ajax.json().data.length : $('#settlementAuditDoneTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$(".editbtn").addClass("hidden");
		$("#settlementAuditDoneform").formReset();
		 $("#settlementAuditDoneform").toggleEditState(false);
	 }else{
//		 $(".editbtn").removeClass("hidden");
//		 $("#settlementAuditDoneform").toggleEditState(true);
	 }
	$("#init_quantitiesTotal").val($("#quantitiesTotal").val());
	$("#endDeclarationCost").val(new Number($("#quantitiesTotal").val()).toFixed(2));
	//计算核减
	$("#endAuditMinusCost").val(new Number($("#firstDeclarationCost").val())-(new Number($("#endDeclarationCost").val())).toFixed(2));
	if($("#endAuditMinusCost").val()==0){
		$("#endAuditMinusRate").val($("#endAuditMinusCost").val());
	}else{
		$("#endAuditMinusRate").val(((new Number($("#endAuditMinusCost").val())/new Number($("#firstDeclarationCost").val()))*100).toFixed(2)+''+'%');
	}
}

//终审监听
var auditMonitor = function(){
	$('.auditBtn').off('click').on('click',function(){
		if($('#settlementAuditDoneTable').find('tr.selected').length>0){
			//维护按钮显示出来
			$(".right-btn").removeClass("hidden");
			$('.editbtn').removeClass('hidden');
			//切换可编辑状态
			$('#settlementAuditDoneform').toggleEditState(true);
			
			$('#settlementInfo').tab('show');
		}else{
			alertInfo('请选择要审核的工程记录！');
		}
	});
};



/**
 * 增加工程量
 */
function addQualities(){
$(".addBtn").off("click").on("click",function(){
	var url = "#qualitiesDeclaration/standardPop";
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
			json.doneAmount = '';
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
    if ($('#qualitiesTable').length !== 0) {
    	if(!$.fn.DataTable.isDataTable('#qualitiesTable')){
    	    qualitiesTable= $('#qualitiesTable').on( 'init.dt',function(){
	    		//amountSum();//计算每行的金额及总金额
	    		addQualities();//添加行
	    		saveQualities();//保存
	    		delFun();//删除
	    		delData();
	    		$(".cancleBtn").on("click",function(){
	    			$("body").cgetPopup({
	    				title: '提示信息',
	    				content: '确认要放弃操作吗?',
	    				accept: cancleFun,
	    				icon: 'fa-exclamation-circle',
	    			});
	    			
	    		});
	    		if(trSData.settlementAuditDoneTable.t){
	    			$(".right-btn").removeClass("hidden");  
	    		}else{
	    			$(".right-btn").addClass("hidden");
	    		}
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
	        }).DataTable({
	        	language: language_CN,
//              lengthMenu: [18],
	            dom: 'Brtip',
	            buttons: [
	                { text: '增加', className: 'btn-sm  btn-query business-tool-btn right-btn addBtn  hidden' },
	                { text: '保存', className: 'btn-sm  btn-confirm business-tool-btn right-btn saveBtn  hidden' },
	                { text: '删除', className: 'btn-sm  btn-warn business-tool-btn right-btn delBtn  hidden' },
	                { text: '放弃', className: 'btn-sm  btn-warn business-tool-btn right-btn cancleBtn  hidden' }
	            ],
	            /*ajax: 'projectjs/subcontract/json/qualities.json',*/
	            //启用服务端模式，后台进行分段查询、排序
	//          serverSide:false,
	            ajax: {
	                url: 'settlementAuditDone/queryQuantityStandard',
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
					{"data":"doneAmount", className:"text-right", responsivePriority: 3},
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
							var tdcon='<div class="tdInnerInput"><input name="' + row.sqStandardId + '_doneAmount" class="form-control input-sm text-right numbers" data-parsley-type="number" value="' + data + '"></div>';
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
	    	if(trSData.settlementAuditDoneTable.t){
	  		  $(".right-btn").removeClass("hidden");   		
	  		}else{
	  		  $(".right-btn").addClass("hidden");
	  		  treeArr=[];
	  		}
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
    $("#endDeclarationCost").val(new Number($("#quantitiesTotal").val()).toFixed(2));
	//计算核减
	$("#endAuditMinusCost").val(new Number($("#firstDeclarationCost").val())-(new Number($("#endDeclarationCost").val())).toFixed(2));
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
    $("#endDeclarationCost").val(new Number($("#quantitiesTotal").val()).toFixed(2));
	//计算核减
	$("#endAuditMinusCost").val(new Number($("#firstDeclarationCost").val())-(new Number($("#endDeclarationCost").val())).toFixed(2));
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
			if(!v.doneAmount) return true;
			dataObj.list.push(v);
		})
	    dataObj.projId=trSData.json.projId;
	    dataObj.projNo=trSData.json.projNo;
	    dataObj.projName=trSData.json.projName;
	    dataObj.projScaleDes=trSData.json.projScaleDes;
//	    console.info('== '+$('.total-amount').text());
	    dataObj.submitAmount = $('.total-amount').text();  // 申报金额合计
//	    console.info(dataObj);
	  var data=JSON.stringify(dataObj);
	  if(response){
          response.abort();
      }
	  var response = $.ajax({
           url: "settlementAuditDone/insertSubQualities",
          type: "POST",
          timeout : 5000,
          contentType: "application/json;charset=UTF-8",
          data: data,
          success: function (data) {
        	  if (data === "true") {
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
	var len = $('#settlementAuditDoneTable').DataTable().data().length;
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
	}
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
        	handleSttlementAuditDone();
        }
    };
}();
$('.attach-panel').initAttachOper({
	collection: {
		tableid:'settlementAuditDoneTable'
	},
	settlementchange: {
	    tableid : 'settlementAuditDoneTable'
	}
});





