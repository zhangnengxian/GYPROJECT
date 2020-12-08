var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var menuId="110601",
declarationData={};
declarationData.menuId= menuId;
var declarationtable,
qualitiesTable,
treeArr=[],
subSearchData = {},
/**
 * 加载工程列表
 */
handleQualitiesDeclaration = function() {
    declarationData.projNo=$("#waitHandleProjNo").val();
    "use strict";
    if ($('#qualitiesDeclarationTable').length !== 0) {
    	declarationtable = $('#qualitiesDeclarationTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
    		if(declarationtable.data().length < 1) subSearchData.sbId = "-1";
   			$("#qualities_declaration_panel_box").cgetContent("qualitiesDeclaration/viewPage");
   			//隐藏遮罩
   			$('#qualitiesDeclarationTable').hideMask();
   			$("#qualitiesDeclarationTable_filter input").attr("placeholder","工程编号");
   			$("#qualitiesDeclarationTable_filter input").on("change",function(){
   				declarationData={};
   				declarationData.projNo=$("#qualitiesDeclarationTable_filter input").val();
   				declarationData.menuId= menuId;
   			 $("#qualitiesDeclarationTable").cgetData("",dataBack);  //列表重新加载
   			});
   		    //基础条件查询添加回车事件
   			$('#qualitiesDeclarationTable_filter input').on('keyup', function(event) {
   		        if (event.keyCode == "13") {
   		        	$(this).change();
   		        }
   		    });	   
   			//绑定单击事件 弹出搜索框
   			searchPop();
   			signMonitor();
   			setTimeout(function(){
   			    $("#qualitiesDeclarationTable").DataTable().columns.adjust();
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
                 { text: '预算', className: 'btn-sm btn-query business-tool-btn updateBtn' }
             ],
             //启用服务端模式，后台进行分段查询、排序
             serverSide:true,
             ajax: {
                 url: 'qualitiesDeclaration/queryProject',
                 type:'post',
                 data: function(d){
                    	$.each(declarationData,function(i,k){
                    		d[i] = k;
                    	});
                    	
                 },
                 dataSrc: 'data'
             },
           //  ajax: "projectjs/subcontract/json/qualities_declaration.json",
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
				 "targets":0,
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
searchPop = function(){
	$(".searchBtn").off("click").on("click",function(){
			var url = "#qualitiesDeclaration/projectSearchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
},

/**
 * 查询弹出屏，点击确定后 
 */
searchDone = function(){
	declarationData = $("#searchForm_quantity").serializeJson();
	declarationData.projStatusId=$("#projStatus").val();
	declarationData.projNo=$("#qualitiesDeclarationTable_filter input").val();
	declarationData.menuId= menuId;
    $("#qualitiesDeclarationTable").cgetData("",dataBack);  //列表重新加载
};
function addQualities(){
	$(".addBtn").off("click").on("click",function(){
		var projId = $("#projId").val();
		var url = "#qualitiesDeclaration/standardPop/"+projId;
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
		        }).remove().draw();
		}
		}
	}
   treeArr=addArr;	
   qualitiesTable.rows.add(rows).draw();
   $("#qualitiesForm").toggleEditState(false);
}
var signMonitor=function(){
	$(".updateBtn").off("click").on("click",function(){
		//表单可编辑
		$("#planEstablishDetailform").toggleEditState(true);
		//按钮显示
		$(".editbtn").removeClass("hidden");
		if(trSData.qualitiesDeclarationTable.t){
		  $(".right-btn").removeClass("hidden"); 
		}else{
		  $(".right-btn").addClass("hidden");
		  treeArr=[];
		}
		reloadBack();
	});
}
/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	
	t.cgetDetail('planEstablishDetailform','qualitiesDeclaration/viewCost?stepId='+getStepId(),'',budgetCallback);
}
var budgetCallback = function(data){
	$("#sbId1").val(data.sbId);
	$("#projId1").val(data.projId);
	if(data.deptDivide==$("#customerServiceCenter").val()){
		$(".contractAmount").removeClass("hidden");
	}else{
		$(".contractAmount").addClass("hidden");
	}
	if(data.sbId==''){
		subSearchData.sbId=-1;
	}else{
		subSearchData.sbId = data.sbId;
	}
	if($.fn.DataTable.isDataTable('#qualitiesTable')){
		//cancleFun();
		//初始化过
		$("#qualitiesTable").cgetData(true,selectData);//列表重新加载
	}else{
		qualitiesInit();
	}
	console.info(data);
	console.info("costType--"+$("#costType").val());
	console.info("造价--"+data.totalQuota);
	$(".editbtn").addClass("hidden");
	$(".right-btn").addClass("hidden");
	if($("#costType").val()=='1'){
		$(".QUOTA").addClass("hidden");
		$(".DETAILED_LIST").removeClass("hidden");
	}else{
		$("#totalQuota").val(new Number(data.totalQuota).toFixed(2));
		$(".QUOTA").removeClass("hidden");
		$(".DETAILED_LIST").addClass("hidden");
	}
	if($("#sysDate").val()!=''){
	    $("#operateDate").val($("#sysDate").val().substring(0,10));
	}
}
/**右侧维护区 放弃 按钮点击后*/
$(".cancelBtn").on("click",function(){
	 $("#planEstablishDetailform").toggleEditState(false);
	 $(".editbtn").addClass("hidden");
	 $(".right-btn").addClass("hidden");
	 $("#qualitiesDeclarationTable").cgetData("",dataBack);  //列表重新加载
});

var reloadBack=function(){
	var len=$("#qualitiesTable").DataTable().rows().data().length;
	if(len>0){
		$('.exportBtn').removeClass('hidden');
	}else{
		$('.exportBtn').addClass('hidden');
	}
}
/**
 * 初始化右侧工程量列表
 */
var qualitiesInit = function() {
	"use strict";
    if ($('#qualitiesTable').length !== 0) {
    	if(!$.fn.DataTable.isDataTable('#qualitiesTable')){
    	    qualitiesTable= $('#qualitiesTable').on( 'init.dt',function(){
	    		addQualities();//添加行
	    		delFun();//删除
	    		selectData();
	    		//导出监听
	    		exportBtnMonitor();
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
	    	    changeAText();
	    	    //导出按钮判断是否显示
	    	    //isExportShow();
	        }).DataTable({
	        	language: language_CN,
//              lengthMenu: [18],
	            dom: 'Brtip',
	            buttons: [
	                { text: '增加', className: 'btn-sm  btn-query business-tool-btn right-btn addBtn  hidden' },
	                { text: '删除', className: 'btn-sm  btn-warn business-tool-btn right-btn delBtn  hidden' },
	                { text: '导出', className: 'btn-sm  btn-default business-tool-btn right-btn exportBtn hidden' },
	            ],
	            /*ajax: 'projectjs/subcontract/json/qualities.json',*/
	            //启用服务端模式，后台进行分段查询、排序
	//          serverSide:false,
	            ajax: {
	                url: 'qualitiesDeclaration/queryQuantityStandard',
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
				}],
				ordering:false
	        });
	    }else{
	    	if(trSData.qualitiesDeclarationTable.t){
	  		  $(".right-btn").removeClass("hidden");   		
	  		}else{
	  		  $(".right-btn").addClass("hidden");
	  		  treeArr=[];
	  		}
	    }
    }
};
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
    $("#totalAmount").val($(".total-amount").text());
    $("#totalAmount").change();
};
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
	// var rows = $("#qualitiesTable").DataTable().rows( '.selected' ).remove().draw();
    $("#qualitiesTable").DataTable().rows( '.selected').remove().draw();// 删除本地数据
	var arr=[];
	var json=$("#qualitiesTable").DataTable().rows().data();
	for(var i=0;i<json.length;i++){
		arr.push(json[i].id);
	}
	var totalRow=0.00;
	if($("#qualitiesTable").DataTable().column(6).data().length){
		totalRow = $("#qualitiesTable").DataTable().column(6).data().reduce( function (a,b) {
			a = a || 0;
			b = b || 0;
	        return parseFloat(a) + parseFloat(b);
	    });
	}
    $(".total-amount").text(parseFloat(totalRow).toFixed(2));
    $("#totalAmount").val($(".total-amount").text());
    $("#totalAmount").change();
	treeArr=arr;
}
function selectData(){
	var arr=[];
	var json=$("#qualitiesTable").DataTable().rows().data();
	for(var i=0;i<json.length;i++){
		arr.push(json[i].id);
	}
	treeArr=arr;
	var totalRow = 0;
	if(qualitiesTable.column(6).data().length){
		totalRow = qualitiesTable.column(6).data().reduce( function (a,b) {
			a = a || 0;
			b = b || 0;
	        return parseFloat(a) + parseFloat(b);
	    });
	}
    $(".total-amount").text(parseFloat(totalRow).toFixed(2));
}
function dataBack(){
	var len = $('#qualitiesDeclarationTable').DataTable().data().length;
	if(len<1){
		$("#planEstablishDetailform").formReset();
		subSearchData.sbId = '-1';
		if($.fn.DataTable.isDataTable('#qualitiesTable')){
			//初始化过
			$("#qualitiesTable").cgetData(true);//列表重新加载
		}else{
			qualitiesInit();
		}	
		$(".total-amount").text("");
		$(".right-btn").addClass("hidden");
	}else{
		$(".right-btn").removeClass("hidden");
	}
}
function cancleFun(){
	treeArr=[];
	$(".total-amount").text("");
	$("#qualitiesTable").cgetData(false,delData);//列表重新加载
}
//点击导出
var exportBtnMonitor=function(){
	$('.exportBtn').off().on('click',function(){
		var len=$("#qualitiesTable").DataTable().rows().data().length;
		$('#subQualitiesForm').submit();
	})
}
//导出按钮是否显示
var isExportShow=function(){
	//$('.exportBtn').addClass('hidden');
	var len=$("#qualitiesTable").DataTable().rows().data().length;
	if(len>0){
		$('.exportBtn').removeClass('hidden');
	}
}
//点击保存
$(".acceptSaveBtn").off().on("click",function(){
	$("#flag").val("0");
    var viewform = $("#planEstablishDetailform");
    if(viewform.parsley().isValid()){
        var viewdata = viewform.serializeJson();
        if(viewdata.costType==1){
        	 var trs = $('#qualitiesTable tbody tr');
     		for(var i=0,l=trs.length; i<l; i++){
     			console.info(trs);
     			console.info(trs.eq(i));
     			console.info(trs.eq(i).find("input.numbers").val());
     			var v = trs.eq(i).find("input.numbers").val();
//     			if(v == 0||isNaN(v)) {
//     				alertInfo("请输入正确工程量数量！");
//     				return false;
//     			}
             };
             var json=$("#qualitiesTable").DataTable().data();
             viewdata.list = [];
     		$.each(json, function(k,v){
     			viewdata.list.push(v);
     		})
        }else{
        }
	  var data=JSON.stringify(viewdata);
	  if(response){
          response.abort();
      }
	  //加遮罩
	  $(".saveHiddenBox").loadMask("正在保存...", 1, 0.5);
	  var response = $.ajax({
            type : "POST",
            url : "qualitiesDeclaration/saveSubBudget",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(viewdata),
            success : function(data){
            	//取消遮罩
            	$(".saveHiddenBox").hideMask();	
                var content = "数据保存成功！";
                if(data === "false"){
                    content = "数据保存失败！";
                }else if(data === "true"){
                    $(".editbtn").addClass("hidden");
                    $(".right-btn").addClass("hidden");
                   // $("#planEstablishDetailform").formReset();
                    $("#planEstablishDetailform").toggleEditState(false);
                    $("#qualitiesDeclarationTable").cgetData(true);//列表重新加载
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
            error : function(){
            	//取消遮罩
            	$(".saveHiddenBox").hideMask();	
                console.warn("送审区记录保存失败 ！");
            }
        });
    }else{
        //如果未通过验证，则加载验证UI
        viewform.parsley().validate();
    }
});
//推送保存
$(".pushButton").off().on("click",function(){
	$("#flag").val("1");
    var viewform = $("#planEstablishDetailform");
    if(viewform.parsley().isValid()){
    	
    	var signtools =$('#planEstablishDetailform').find(".signature-tool.sign-require"),
        stl = signtools.length,
        sBlank = 0;
        for(var i=0; i<stl; i++){
        	var tstool = signtools.eq(i).next("div").find("a.btn-white"),
        	tsinput = tstool.siblings(".sign-data-input");
        	if(!tsinput.val() || tsinput.val().length < 312){
        		tstool.addClass("required-sign");
        		sBlank++;
        	}
        }
        if(sBlank){
	        	$("body").cgetPopup({
	            	title: "提示信息",
	            	content: "请完成签字!",
	            	accept: popClose,
	            	chide: true,
	            	icon: "fa-warning",
	            	newpop: 'new'
	            });
        	return false;
        }
    	
    	
        var viewdata = viewform.serializeJson();
        //菜单ID
        viewdata.stepId = getStepId();
        if(viewdata.costType==1){
       	 var trs = $('#qualitiesTable tbody tr');
    		for(var i=0,l=trs.length; i<l; i++){
    			console.info(trs);
    			console.info(trs.eq(i));
    			console.info(trs.eq(i).find("input.numbers").val());
    			var v = trs.eq(i).find("input.numbers").val();
//    			if(v == 0||isNaN(v)) {
//    				alertInfo("请输入正确的工程量数量！");
//    				return false;
//    			}
            };
            var json=$("#qualitiesTable").DataTable().data();
            viewdata.list = [];
    		$.each(json, function(k,v){
    			viewdata.list.push(v);
    		})
       }else{
       }
      if(viewdata.uploadFlag!='1'){
    	  alertInfo("请确认是否已上传分包预算书！");
		  return false;
      }
	  var data=JSON.stringify(viewdata);
	  if(response){
          response.abort();
      }
	 //加遮罩
	  $(".saveHiddenBox").loadMask("正在保存...", 1, 0.5);
	  var response = $.ajax({
            type : "POST",
            url : "qualitiesDeclaration/saveSubBudget",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(viewdata),
            success : function(data){
            	//取消遮罩
            	$(".saveHiddenBox").hideMask();	
                var content = "数据保存成功！";
                if(data === "false"){
                    content = "数据保存失败！";
                }else if(data === "true"){
                    $(".editbtn").addClass("hidden");
                    $(".right-btn").addClass("hidden");
                    $("#planEstablishDetailform").formReset();
                    $("#planEstablishDetailform").toggleEditState(false);
                    $("#qualitiesDeclarationTable").cgetData(true,dataBack);//列表重新加载
                }else{
                	content = "请上传分包预算书！";
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
            error : function(){
            	//取消遮罩
            	$(".saveHiddenBox").hideMask();	
                console.warn("送审区记录保存失败 ！");
            }
        });
    }else{
        //如果未通过验证，则加载验证UI
        viewform.parsley().validate();
    }
});
/**
 * 初始化工程列表
 */
var qualitiesDeclaration = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){ 
        		handleQualitiesDeclaration();
        	});
        }
    };
}();

