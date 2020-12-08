/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/

/**查询条件*/
var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var settlementAuditStartTable, searchData={},subSearchData = {},qualitiesTable,treeArr=[];
/**
 * 加载工程列表
 */
var handleSttlementAuditStart = function() {
	"use strict";
    if ($('#settlementAuditStartTable').length !== 0) {
    	settlementAuditStartTable= $('#settlementAuditStartTable').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
    		if(settlementAuditStartTable.data().length < 1) subSearchData.projId = "-1";
   			$("#settlement_auditStart_panel_box").cgetContent("settlementAuditStart/viewPage");
   			//隐藏遮罩
   			$('#settlementAuditStartTable').hideMask();
   			$("#settlementAuditStartTable_filter input").attr("placeholder","工程编号");
   	    	//绑定单击事件 弹出搜索框
   			searchMonitor();
   		   //初审按钮事件
   			auditMonitor();
   			setTimeout(function(){
   			    $("#settlementAuditStartTable").DataTable().columns.adjust();
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
                { text: '初审', className: 'btn-sm btn-query business-tool-btn m-l-5 auditBtn' }
                ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'settlementAuditStart/queryAuditStart',
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
				{"data":"projStatusDes"},
				{"data":"workDayDto"},
				{"data":"signBack",
					className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData==$("#notThrough").val()){
							$(td).parent().css("background", "rgb(255, 219, 219)");
							//$(td).attr("id",row);
						}
					}
					},
				{"data":"overdue",className:"none never"}
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
			},{
				"targets":4,
				 "render":function(data,type,row,meta){
					 if(data!=null){
						 return data.haveDays;
					 }else{
						 return 0;
					 }
				 }
			}
			],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
},

/**
 * 弹屏监听方法
 */
searchMonitor = function(){
	$('.searchBtn').on('click',function(){
		var url = '#settlementAuditStart/projectSearchPopPage';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});

	// 基础条件查询添加监听
	$('#settlementAuditStartTable_filter input').on('change', function() {
		var projNo = $('#settlementAuditStartTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#settlementAuditStartTable').cgetData(true,tableCallBack); // 列表重新加载
	});
	// 基础条件查询添加回车事件
	$('#settlementAuditStartTable_filter input').on('keyup', function(event) {
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
	var projNo = $('#settlementAuditStartTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#settlementAuditStartTable').cgetData(true,tableCallBack);  
},

/**
 * 选中行时，查询详述
 */
trSelectedBack = function(v, i, index, t, json){
	console.info(123);
	$("#settlementAuditStartform").formReset();
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	trSData.t&&trSData.t.cgetDetail('settlementAuditStartform','settlementAuditStart/auditStartDetail','',tableCallBack);
	$(".editbtn").addClass("hidden");
	$(".right-btn").addClass("hidden");
	$("#settlementAuditStartform").toggleEditState(false);
	 
	 subSearchData.projId = json.projId;
		if($.fn.DataTable.isDataTable('#qualitiesTable')){
			cancleFun();
			//初始化过
			$("#qualitiesTable").cgetData(false,function(){
				var totalRow = 0;
				if($("#qualitiesTable").DataTable().column(6).data().length){
					totalRow = $("#qualitiesTable").DataTable().column(6).data().reduce( function (a,b) {
						a = a || 0;
						b = b || 0;
				        return parseFloat(a) + parseFloat(b);
				    });
				}
			    $(".total-amount").text(parseFloat(totalRow).toFixed(2));
			    $("#quantitiesTotal").val($(".total-amount").text());
			});//列表重新加载
		}else{
			qualitiesInit();
			//console.log("qualitiesTable....init...");
		}
		
},

tableCallBack = function(data){
	//delData();
	if($("#qualitiesTable").DataTable().column(6).data().length){
		totalRow = $("#qualitiesTable").DataTable().column(6).data().reduce( function (a,b) {
			a = a || 0;
			b = b || 0;
	        return parseFloat(a) + parseFloat(b);
	    });
	}
	$("#init_quantitiesTotal").val($("#quantitiesTotal").val());
	/*if(data.firstDeclarationCost){
		$("#firstDeclarationCost").val(new Number($("#firstDeclarationCost").val())).toFixed(2);
	}else{*/
		$("#firstDeclarationCost").val(new Number($("#sendDeclarationCost").val()).toFixed(2));
	//}
	//计算核减
	$("#auditMinusCost").val((new Number($("#sendDeclarationCost").val())-(new Number($("#firstDeclarationCost").val()))).toFixed(2));
	if($("#auditMinusCost").val()==0){
		$("#auditMinusRate").val($("#auditMinusCost").val());
	}else{
		$("#auditMinusRate").val(((new Number($("#auditMinusCost").val())/new Number($("#sendDeclarationCost").val()))*100).toFixed(2)+''+'%');
	}
	
	
	var len = $('#settlementAuditStartTable').DataTable().ajax.json().data ? $('#settlementAuditStartTable').DataTable().ajax.json().data.length : $('#settlementAuditStartTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
//		 $('#settlementAuditStartform')[0].reset();
		$(".editbtn").addClass("hidden");
//		 $('.clear-sign').click();
		 $("#settlementAuditStartform").formReset();
		 $("#settlementAuditStartform").toggleEditState(false);
	 }else{
//		 $(".editbtn").removeClass("hidden");
//		 $("#settlementAuditStartform").toggleEditState(true);
	 }
}

//初审监听
var auditMonitor = function(){
	$('.auditBtn').off('click').on('click',function(){
		if($('#settlementAuditStartTable').find('tr.selected').length>0){
			//维护按钮显示出来
			$(".right-btn").removeClass("hidden");
			$('.editbtn').removeClass('hidden');
			//切换可编辑状态
			$('#settlementAuditStartform').toggleEditState(true);
			$("#signBtn_3").toggleSign(false);
			$("#signBtn_4").toggleSign(false);
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
	var url = "#settlementAuditStart/standardPop";
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
	    		if(trSData.settlementAuditStartTable.t){
	    			//$(".right-btn").removeClass("hidden");  
	    		}else{
	    			//$(".right-btn").addClass("hidden");
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
               // lengthMenu: [18],
	            dom: 'Brtip',
	            buttons: [
	                { text: '增加', className: 'btn-sm  btn-query business-tool-btn right-btn addBtn  hidden' },
	               // { text: '导入', className: 'btn-sm  btn-default business-tool-btn right-btn importBtn  hidden' },
	                { text: '保存', className: 'btn-sm  btn-confirm business-tool-btn right-btn saveBtn  hidden' },
	                { text: '删除', className: 'btn-sm  btn-warn business-tool-btn right-btn delBtn  hidden' },
	                { text: '放弃', className: 'btn-sm  btn-warn business-tool-btn right-btn cancleBtn  hidden' }
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
	    	if(trSData.settlementAuditStartTable.t){
	  		  //$(".right-btn").removeClass("hidden");   		
	  		}else{
	  		  //$(".right-btn").addClass("hidden");
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
   // $("#firstDeclarationCost").val(new Number($("#quantitiesTotal").val()).toFixed(2));
	//计算核减
	$("#auditMinusCost").val((new Number($("#sendDeclarationCost").val())-(new Number($("#firstDeclarationCost").val()))).toFixed(2));
	if($("#auditMinusCost").val()==0){
		$("#auditMinusRate").val($("#auditMinusCost").val());
	}else{
		$("#auditMinusRate").val(((new Number($("#auditMinusCost").val())/new Number($("#sendDeclarationCost").val()))*100).toFixed(2)+''+'%');
	}
};

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
	$(".total-amount").text(parseFloat(totalRow).toFixed(2));
    $("#quantitiesTotal").val(totalRow.toFixed(2));
    //
   // $("#firstDeclarationCost").val(new Number($("#sendDeclarationCost").val()).toFixed(2));
	//计算核减
	$("#auditMinusCost").val((new Number($("#sendDeclarationCost").val())-(new Number($("#firstDeclarationCost").val()))).toFixed(2));
	if($("#auditMinusCost").val()==0){
		$("#auditMinusRate").val($("#auditMinusCost").val());
	}else{
		$("#auditMinusRate").val(((new Number($("#auditMinusCost").val())/new Number($("#sendDeclarationCost").val()))*100).toFixed(2)+''+'%');
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
//	$("#settlementAuditStartTable").cgetData(true,function(){
//		dataBack();
//	});
	$("#qualitiesTable").cgetData(false,delData);
}
function dataBack(){
	var len = $('#settlementAuditStartTable').DataTable().data().length;
	if(len<1){
		subSearchData.projId = '-1';
		if($.fn.DataTable.isDataTable('#qualitiesTable')){
			//初始化过
			$("#qualitiesTable").cgetData(false);//列表重新加载
		}else{
			qualitiesInit();
		}	
		$(".total-amount").text("");	
		//$(".right-btn").addClass("hidden");
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


var sttlementAuditStart = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){       
        		$("#signBtn_1").handleSignature(); 
        		handleSttlementAuditStart();
        	});
        }
    };
}();

//$('.attach-panel').initAttachOper({
//	settlementchange: {
//	    tableid : 'settlementAuditStartTable'
//	},
//	auditInformation: {
//		tableid:'settlementAuditStartTable'
//	}
//});



