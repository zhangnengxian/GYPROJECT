var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var menuId = "110801";
var searchData={},subSearchData = {},qualitiesTable,settlementDeclarationTable,treeArr=[];
searchData.menuId = menuId;
/**
 * 加载工程列表
 */
var handleConstructContract = function() {
	"use strict";
    if ($('#settlementDeclarationTable').length !== 0) {
    	settlementDeclarationTable = $('#settlementDeclarationTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
    		if(settlementDeclarationTable.data().length < 1) subSearchData.projId = "-1";
   			$("#settlement_declaration_panel_box").cgetContent("settlementDeclaration/viewPage");
   			//隐藏遮罩
   			$('#settlementDeclarationTable').hideMask();
   			$("#settlementDeclarationTable_filter input").attr("placeholder","工程编号");
   		    //基础条件查询添加监听
   			$("#settlementDeclarationTable_filter input").on("change",function(){
   				searchData={};
   				searchData.projNo=$("#settlementDeclarationTable_filter input").val();
   				searchData.menuId = menuId;
   				//declarationSearchPopPage
   				$("#planEstablishDetailform").formReset();
   				$("#settlementDeclarationTable").cgetData("",dataBack);  //列表重新加载
   			});
   		    // 基础条件查询添加回车事件
   			$('#settlementDeclarationTable_filter input').on('keyup', function(event) {
   					$(this).change();
   			});
   		
   			//报审监听方法
   			signMonitor();
   			//查询监听方法
   			searchPop();
   			setTimeout(function(){
		    $("#settlementDeclarationTable").DataTable().columns.adjust();
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
                { text: '报审', className: 'btn-sm btn-query business-tool-btn updateBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'settlementDeclaration/querySettlementDeclarationProject',
            	type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/settlement/json/joint_acceptance.json',
            select: true,  //支持多选
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            columns: [
                {"data":"projId",className:"none never"},
				{"data":"projNo"},
				{"data":"projName"},
				{"data":"projStatusDes"},
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


/**
 * 弹屏监听方法
 */
var searchPop = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#settlementDeclaration/declarationSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone,
			cancel:clearForm
		});
	});
	// 基础条件查询添加监听
	$('#settlementDeclarationTable_filter input').on('change', function() {
		var projNo = $('#settlementDeclarationTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		searchData.menuId = menuId;
		$('#settlementDeclarationTable').cgetData(true,tableCallBack); // 列表重新加载
	});
	// 基础条件查询添加回车事件
	$('#settlementDeclarationTable_filter input').on('keyup', function(event) {
		if (event.keyCode == '13') {
			$(this).change();
		}
	});
};

function tableCallBack(){
	var len = $('#settlementDeclarationTable').DataTable().ajax.json().data ? $('#settlementDeclarationTable').DataTable().ajax.json().data.length : $('#settlementDeclarationTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#planEstablishDetailform')[0].reset();
		 $(".updateBtn,.editbtn").addClass("hidden");
		 $('.clear-sign').click();
		 $("#planEstablishDetailform").toggleEditState(false);
	 }else{
		 $(".updateBtn").removeClass("hidden");
	 }
	
}

/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	//查询条件
	searchData = $('#declarationSearchPopPage').serializeJson();
	var projNo = $('#settlementDeclarationTable_filter input').val();
	searchData.projNo = projNo;
	searchData.menuId = menuId;
	//列表重新加载
    $('#settlementDeclarationTable').cgetData(true,tableCallBack);  
	/*//console.log(searchData);
	//列表重新加载
	$("#planEstablishDetailform").formReset();
    $("#settlementDeclarationTable").cgetData();  
    //清空表单
    $("#searchForm_contract input").val("");
    //清空下拉框
    $("#projLtypeId  option[value=''] ").attr("selected",true);*/
}

var clearForm=function(){
	//清空表单
    $("#searchForm_contract input").val("");
    //清空下拉框
    $("#projLtypeId  option[value=''] ").attr("selected",true);
}

/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	if($('[name="isBack"]:checked').val()==1){
		$('[name="isBack"]').not(":checked").attr("checked",true);
		$('[name="isBack"]').change();
	}
	
	$(".editbtn").addClass("hidden");
	$(".right-btn").addClass("hidden");
	$("#planEstablishDetailform").toggleEditState(false);
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
		   // console.info($("#quantitiesTotal").val()+"----------3");
			//console.info($("#init_quantitiesTotal").val()+"----------3");
		});//列表重新加载
		
	}else{
		qualitiesInit();
		
		//console.log("qualitiesTable....init...");
	}
	//参数含义
	t.cgetDetail('planEstablishDetailform','settlementDeclaration/viewCost','',budgetCallback);
}

/**
 * 报审按钮监听方法
 */
var signMonitor=function(){
	$(".updateBtn").off("click").on("click",function(){
		//表单可编辑
		$("#planEstablishDetailform").toggleEditState(true);
		//按钮显示
		$(".editbtn").removeClass("hidden");
		
		$('#settlementInfo').tab('show');
		console.info("post"+$("#loginPost").val());
		getSignStatusByPostId($("#loginPost").val(),"planEstablishDetailform");
		if(trSData.settlementDeclarationTable.t){
		  $(".right-btn").removeClass("hidden");   		
		}else{
		  $(".right-btn").addClass("hidden");
		  treeArr=[];
		}
	});
};


/**
 * 增加工程量
 */
function addQualities(){
$(".addBtn").off("click").on("click",function(){
	var url = "#settlementDeclaration/standardPop";
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
					 /*console.info(idx);
					 console.info( data.id );*/
					
				
		       
		               
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
	    		/*if(trSData.settlementDeclarationTable.t){
	    			$(".right-btn").removeClass("hidden");  
	    		}else{
	    			$(".right-btn").addClass("hidden");
	    		}*/
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
	                //{ text: '导入', className: 'btn-sm  btn-default business-tool-btn right-btn importBtn  hidden' },
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
					{"data":"sqAmount", className:"text-right amount", responsivePriority: 2},
					{"data":"sqStatus", className:"none never"},
				],
				columnDefs: [{
					"targets":0,
					"visible":false
				},{
					"targets":1,
					"visible":false
				},{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
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
				},{
					"targets": 7,
					"render": function ( data, type, row, meta ) {
						$("#sqStatus").val(data);
						if(data=='1'){
							return "施工预算";
						}else{
							return "结算";
						}
						
					}
				}],
				ordering:false
	        });
	    }else{
	    	if(trSData.settlementDeclarationTable.t){
	  		 // $(".right-btn").removeClass("hidden");   		
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
    //console.info($("#quantitiesTotal").val()+"----------4");
	//console.info($("#init_quantitiesTotal").val()+"----------4");
   // $("#sendDeclarationCost").val(new Number($("#quantitiesTotal").val()).toFixed(2));
};

/*
 * 报审金额
 */
function budgetCallback(data){
	//delData();
	var totalRow = 0;
	if($("#qualitiesTable").DataTable().column(6).data().length){
		totalRow = $("#qualitiesTable").DataTable().column(6).data().reduce( function (a,b) {
			a = a || 0;
			b = b || 0;
	        return parseFloat(a) + parseFloat(b);
	    });
	}
    $("#quantitiesTotal").val(totalRow.toFixed(2));
	$("#purStuCost").val((new Number($("#sale").val())*parseFloat($("#purStuRate").val())*0.01).toFixed(2));
	$("#init_quantitiesTotal").val($("#quantitiesTotal").val());
	console.info("+++++++++++++++++++++++"+$("#quantitiesTotal").val());
	console.info("++++++++++++++++++++++"+$("#init_quantitiesTotal").val());
	
	if(data && data.sendDeclarationCost){
		console.info(data.sendDeclarationCost+"----------1");
		$("#sendDeclarationCost").val(new Number(data.sendDeclarationCost).toFixed(2));
	}//else{
		console.info($("#quantitiesTotal").val()+"----------2");
		console.info($("#init_quantitiesTotal").val()+"----------2");
		//$("#sendDeclarationCost").val(totalRow.toFixed(2));//默认为合计
	//}
	showSupContracts(data.supContract);	
	console.info(data.supContract);
}
/**
 * 显示补充协议
 * @param data
 */
function showSupContracts(data){
	if($(".supContracts")){
		$(".supContracts").remove();
	}
	if(data!=null){
		var info="";
		$.each(data,function(i,e){
			info +="<div class='form-group col-md-6 supContracts'>"
		         + "<label class='control-label' for='cuName'>补充协议号</label>"
		         +"<div>"
		         +"<input type='text' class='form-control input-sm ' readonly='readonly' value='"+e.scNo+"'  id='supCno"+i+"' name='supCno"+i+"'/>"
		         +"</div></div>";
			info +="<div class='form-group col-md-6 supContracts'>"
		         + "<label class='control-label' for='cuName'>协议金额</label>"
		         +"<div>"
		         +"<input type='text' class='form-control input-sm ' readonly='readonly' value='"+e.scAmount+"'  id='supCAmount"+i+"' name='supCAmount"+i+"'/>"
		         +"</div></div>";
			console.info("info"+info);
		});
		$(".cuNameDiv").after(info);
	}
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
//	var totalRow=0.00;
//    $('#qualitiesTable tbody tr').each(function() { 
//      //	var totalAmount=$(this).find(".amount").text();
//    	var totalAmount=$(this).neighborInpput("sqAmount").eq(0).val();
//      	if(totalAmount!==undefined&&totalAmount!==''){
//      		totalRow += parseFloat(totalAmount); 
//      	}
//      }); 
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
   // $("#sendDeclarationCost").val(new Number($("#quantitiesTotal").val()).toFixed(2));
	treeArr=arr;
	//console.log(treeArr);
}
function saveData(){
	
	var dataObj={};
	var data1=[];
	var json=$("#qualitiesTable").DataTable().data();
		dataObj.list = [];
		$.each(json, function(k,v){
			//if(!v.sqNum) return true;
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
        	 if(data === "false"){
        		  $("body").cgetPopup({
                  	title: "提示信息",
                  	content: "数据保存失败, 请重试! <br>" + data,
                  	accept: popClose,
                  	chide: true,
                  	icon: "fa-exclamation-circle",
                  	newpop: 'new'
                  });  
        	  }else{
        		  $(".total-amount").text("");
        		  $("#sdId").val(data);
        		  $("body").cgetPopup({
                  	title: "提示信息",
                  	content: "数据保存成功!",
                  	accept: successBack,
                  	chide: true,
                  	icon: "fa-check-circle",
                  	newpop: 'new'
                  }); 
        		  $("#init_quantitiesTotal").val($("#quantitiesTotal").val());
        	  }
        	  }
          });
	

}

function successBack(){
	
	$("#qualitiesTable").cgetData(false,delData);
}
function dataBack(){
	//$("#planEstablishDetailform").formReset();
	var len = $('#settlementDeclarationTable').DataTable().data().length;
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



/**
 * 初始化工程列表
 */
var settlementDeclaration = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handleConstructContract();
        	});
        }
    };
}();

//$(".attach-panel").initAttachOper({
//	auditInformation: {
//		tableid:'qualitiesTable'
//	}
//});

