var projectTable,projectChangeTable,materialListTable,materialListTablePop;;
var checkListData={};
var projId1='-1',projNo1,projName1,projAddr1;
var searchData={},mcData = {},materialData={},rowData={};
//init();
var projectChange = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){ 
        		handleProjectChangeList();
        	});
        }
    };
}();



//切换到变更列表
$('#changeListTab').on("show.bs.tab", function(){
	$("#flag").val("0");
	if ( $.fn.DataTable.isDataTable('#projectChangeTable')) {
		//列表重新加载
		$("#projectChangeTable").cgetData(true);
	}else{
		handleProjectChangeList();
	}
});

//切换到变更记录
$('#changeRecordTab').on("shown.bs.tab", function(){
	/*if($("#projectChangeTable").find("tr.selected").length>0){
		//如果点击的是页签切换到记录
		if(!$(".updateBtn").data("update")){
			$(".toolBtn").addClass("hidden");
			$("#peojectChangeForm").toggleEditState(false);
			$(".updateBtn").data("update", false);
		}
	}else{
		$("#peojectChangeForm").toggleEditState(true);
		//$(".toolBtn").removeClass("hidden");
		$('#peojectChangeForm').formReset();
		$("#projId2").val(projId1);
		$("#projNo").val(projNo1);
		$("#projName").val(projName1);
		$("#projAddr").val(projAddr1);
	}*/
	
	if($("#flag").val()=="1"){
		$(".toolBtn").removeClass("hidden");
		$("#peojectChangeForm").toggleEditState(true);
	}else{
		$(".toolBtn").addClass("hidden");
		$("#peojectChangeForm").toggleEditState(false);
	}
	
	
});

var handleProjectChangeList = function() {
	"use strict";
    if ($('#projectChangeTable').length !== 0) {
    	projectChangeTable=$('#projectChangeTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBackList,true);
    		if(!projectChangeTable.ajax.json().data.length) {
    			var src = cptPath+"/ReportServer?reportlet=design/designChange.cpt";
    		 	$("#mainFrm").attr("src",src);
    		}
   			//隐藏遮罩
   			$('#projectChangeTable').hideMask();
   			$('#projectChangeTable_filter input').attr('placeholder','工程编号');
   			//表单修改监听方法
   			updateChangeList();
   			//表单增加监听方法
   			insertChangeList();
   			//搜索监听
   			searchMonitor();
   			
   			setTimeout(function(){
   			    $("#projectChangeTable").DataTable().columns.adjust();
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
            dom: 'Brtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },     
                { text: '推送', className: 'btn-sm btn-query  business-tool-btn pushBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:true,
            ajax: {
                url: 'designChange/queryCheckList',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/accept/json/project_accept.json',
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
                {"data":"projNo"},
                {"data":"projName"},
                {"data":"applyDate"},
				{"data":"designChangeTypeDes"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 8, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}]
        });
    }else{
    	var src = cptPath+"/ReportServer?reportlet=design/designChange.cpt";
	 	$("#mainFrm").attr("src",src);
    }
}

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$('.searchBtn').on('click',function(){
		var url = '#designChange/projectSearchChangePage';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#projectChangeTable_filter input').on('change',function(){
		var projNo = $('#projectChangeTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#projectChangeTable').cgetData(true,subTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#projectChangeTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}


/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	console.info(searchData);
	searchData = $('#searchForm_change').serializeJson();
	var projNo = $('#projectChangeTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#projectChangeTable').cgetData(true,subTableCallBack); 
}

var subTableCallBack=function(){
	var len = $('#projectChangeTable').DataTable().ajax.json().data ? $('#projectChangeTable').DataTable().ajax.json().data.length : $('#projectChangeTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $(':input','#peojectChangeForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
         $('.updateAcceptanceBtn,.addBtn').addClass("hidden");
	}

}

//表单修改
var updateChangeList =function(){
	$(".updateBtn").off('click').on("click",function(){
		if($("#projectChangeTable").find("tr.selected").length>0){
			$("#flag").val("1");
			$('#changeRecordTab').tab('show');
			//切换可编辑状态
			$("#peojectChangeForm").toggleEditState(true);
			//维护按钮显示出来
			$(".toolBtn").removeClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
}
//推送
var insertChangeList =function(){
	$('.pushBtn').off('click').on('click',function(){
		if($("#projectChangeTable").find("tr.selected").length>0){
			var myoptions = {
                   	title: "提示信息",
                   	content: "是否确认推送该变更？",
                   	accept: pushSureDone,
                   	chide: false,
                   	icon: "fa-check-circle"
               }
               $("body").cgetPopup(myoptions);
		}else{
			alertInfo('请选择要推送的记录！');
		}

	});
}

var pushSureDone=function(){
	var cmId=trSData.projectChangeTable.json.cmId;
   	$.ajax({
           type: 'POST',
           url: 'designChange/updateChangeState',
           contentType: "application/json;charset=UTF-8",
           data: cmId,
           success: function (data) {
           	var content = "推送成功";
           	if(data === "false"){
           		content = "推送失败！";
           	}else{
           		$("#projectChangeTable").cgetData(true);  //列表重新加载
           	}
           	var myoptions = {
                   	title: "提示信息",
                   	content: content,
                   	accept: popClose,
                   	chide: true,
                   	newpop:'new',
                   	icon: "fa-check-circle"
               }
               $("body").cgetPopup(myoptions);
           },
           error: function (jqXHR, textStatus, errorThrown) {
               console.warn("推送失败！");
           }
       });
}

var changeCallBack=function(){
	
}
/** 选中行时
 */
var trSelectedBackList = function(v, i, index, t, json){
	//维护按钮
	/*if($("#projectChangeTable").find("tr.selected").length>0){
		$(".toolBtn").addClass("hidden");
		trSData.projectChangeTable.t && trSData.projectChangeTable.t.cgetDetail('peojectChangeForm', 'designChange/viewChangeManagement', '',rollback);
		//传false表示不可编辑
		$("#peojectChangeForm").toggleEditState(false);
	}else{
		$("#peojectChangeForm").toggleEditState(true);
		$(".toolBtn").removeClass("hidden");
		$('#peojectChangeForm').formReset();
	}*/
	projId1 = json.projId;
	//变更状态1未推送、2确认中、3待预算调整、4待签补充协议、5已签补充协议、6、已完成
	if(json.designChangeType=="2"){
		$(".pushBtn").removeClass("hidden");
		$(".updateBtn").removeClass("hidden");
	}else{
		$(".pushBtn").addClass("hidden");
		$(".updateBtn").addClass("hidden");
	}
	$(".toolBtn").addClass("hidden");
	trSData.projectChangeTable.t && trSData.projectChangeTable.t.cgetDetail('peojectChangeForm', 'designChange/viewChangeManagement', '',rollback);
};
var rollback = function(json){
	if($("#projectChangeTable").find("tr.projectChangeTable.selected").length<=0){
		var src = cptPath+"/ReportServer?reportlet=design/designChange.cpt&projId="+json.projId+"&cmId="+json.cmId;
	 	$("#mainFrm").attr("src",src);
	}
	//处理人
	 if($("#designer").val()==""){
		 $("#designer").val($("#loginName").val());
	 }
	 //处理时间
	 if($("#cmDate").val()==""){
	     var sysDate = timestamp($("#sysDate").val());
	     $("#cmDate").val(format(sysDate,"default"));
	 }
}



/**切换到材料变更*/
$("#materialChangeTab").on("shown.bs.tab",function(){
	$("#flag").val("0");
	//postLimit();
	//if($("#cmId").val() || $("#state").val()){
	if(trSData.projectChangeTable.json){	
		state=2;
		console.info("jsonId..."+trSData.projectChangeTable.json.cmId);
	}else{
		state=1;
	}
	if((jsonLength(trSData.projectChangeTable))){
		if(!$.fn.DataTable.isDataTable('#materialListTable')){
			handleMChangeList();
		}else{
			 mcData.mcType=$("#mcType").val();
			 mcData.cmId=$("#cmId").val();
			 mcData.projId=$("#projId").val();
			 $("#materialListTable").cgetData(true,mcBack("mcBtn"));
		}
	}else{
		 mcData.mcType="-1";	
 	     mcData.cmId="-1";	 
 	     handleMChangeList();
 	     btnHidden("mcBtn");
	}

});



/**
 * 材料变更(下)
 */
var handleMChangeList = function() {
	"use strict";
	if((jsonLength(trSData.projectChangeTable))){
		mcData.mcType=$("#mcType").val();	
	    mcData.cmId=$("#cmId").val();
	    mcData.projId=projId1;
	}else{
		 mcData.mcType="-1";	
 	     mcData.cmId="-1";	 
	}
	  
	    console.info(mcData);
	if ($('#materialListTable').length !== 0) {
		if (!$.fn.DataTable.isDataTable('#materialListTable')) {
	        materialListTable= $('#materialListTable').on( 'init.dt',function(){
		    	//默认选中第一行
	    		$(this).bindDTSelected("",true);
	    		rowData=materialListTable.data();
	    		//console.info("-++++---");
	    		//console.info(rowData);
		    	//隐藏遮罩
		    	$("#materialListTable").hideMask();
		    	saveMaterialList();
		    	//导入材料
		    	importMaterial();
		    	checkOut();
		    	btnHidden("mcBtn");
		        mcSelBtn();
		        $(this).bindInputsChange();
		       // materialEditLimits();
		        //postLimit();
		        delFun();//删除
		    }).DataTable({
		    	language: language_CN,
		        lengthMenu: [ 18 ],
		        dom: 'Brtip',
		        buttons: [
		            { text: '下载模板', className: 'btn-sm btn-query checkRole checkOut postLimit'},
		            { text: '导入', className: 'btn-sm btn-query business-tool-btn materialImportBtn checkRole mcBtn postLimit' },
	                { text: '调整', className: 'btn-sm btn-query mcSelBtn checkRole mcBtn postLimit' },
		            { text: '保存', className: 'btn-sm btn-query mcSaveBtn checkRole mcBtn postLimit'}/*,
		            { text: '删除', className: 'btn-sm  btn-warn business-tool-btn right-btn delBtn' }*/
		        ],
		        //serverSide: true, 
				ajax: {
				    url: 'changeRecord/queryMCList',
				    contenttype:"application/json;charset=utf-8",
				    data:function(d){
				        $.each(mcData,function(i,k){
				            d[i] = k;
				        }); 	
				    },
				    datasrc: ''
				},
				select: true,  //支持多选
		        columns: [
		            {"data":"mcId", className:"none never"},
		            {"data":"materialId", className:"none never"},
		  			{"data":"materialName",responsivePriority: 1},
		  			{"data":"materialSpec",responsivePriority: 4},
					{"data":"materialUnit",responsivePriority: 3},
					{"data":"flagDes",responsivePriority: 5},
					{"data":"adjustQuantity", "className": "text-right",responsivePriority: 2}
				],
				order: [[ 0, "asc" ]],
				columnDefs: [{
					targets: 0,
					"visible":false
				},{
					targets: 6,
					render:function(data,type,row,meta){
						if(type === "display"){
							var tdcon = '<div class="tdInnerInput">';
						        tdcon += '<input class="form-control input-sm text-right" data-parsley-type="number" value=' + data + '>';						 					    
						        tdcon += '</div>';
						        return tdcon;
						}else{
							return data;
						}
					}
				}],
				responsive: {
	            	details: {
	            		renderer: function ( api, rowIdx, columns ){
	            			return renderChild(api, rowIdx, columns);
	            		}
	            	}
	            }
		    }).on("draw.dt",function(){
		    	$("#materialListForm").toggleEditState(false).styleFit();
		    });
		}else{
			materialListTable.ajax.reload();
		}
		$('#materialListTable').on("draw.dt", function(){
			rowData=materialListTable.data();
		});
	}
}

/**
 * 下载模版
 */
var checkOut = function(){
	$(".checkOut").on("click",function(){
		$("#exportExcel").submit();
	})
}

function btnHidden(cla){
	 /*console.info("state--"+state);
	 if(state==1){
		 console.info("ca11---"+cla);
      	$("."+cla).addClass("hidden");
     }else{
    	console.info("ca12---"+cla);
      	$("."+cla).removeClass("hidden");
     }
	//alert(trSData.projectChangeTable.json.auditState);
	if(trSData.projectChangeTable.json.auditState==='3'){
		$(".mcBtn").removeClass("hidden");
	}else{
		$(".mcBtn").addClass("hidden");
	}*/
}

//材料导入
var importMaterial=function(){
	$(".materialImportBtn").off("click").on("click",function(){
		$("#projId1").val(projId1);
		console.info("projId1--"+projId1);
		console.info("projId11--"+$("#projId1").val());
		$("body").cgetPopup({
			title: '文件导入',
			content: "#officialDrawing/importPop?url=changeRecord/importExcel",
			accept: importM,
			nocache: true
		});
	});
}
var importM = function(){
	var mcData={};
	mcData.mcType=$("#mcType").val();	
	mcData.cmId=$("#cmId").val();
	mcData.projId=projId1;
	console.info("projId2--"+projId1);
	$("#materialListTable").cgetData();
}

function mcBack(cla){
	btnHidden(cla);
	//console.info("===---Back---");	
	rowData=materialListTable.data();
	//console.info(rowData);
}

//调整
function mcSelBtn(){
	$(".mcSelBtn").on("click",function(){
		materialdata={};
		var url = "#changeRecord/materialList";
		$("body").cgetPopup({
			title: '材料列表',
			content: url,
			accept: mcDone,
			size:"large"
		});
		
	});
}

//调整点击确定
function mcDone(){
	var jsons=trSData.materialListTablePop.jsons;
	//console.info("--------------");
	//console.info(rowData);
    if(jsons!=undefined){
		for(var i=0;i<rowData.length;i++){
			for(var j=0;j<jsons.length;j++){
				if(jsons[j].materialId == rowData[i].materialId){
					jsons.splice(j,1);
					break;
				}
			}
		}

		for(var i=0;i<jsons.length;i++){
			jsons[i].mcType=$("#mcType").val();
			jsons[i].cmId=$("#cmId").val();
			jsons[i].mcId="";
			jsons[i].adjustQuantity='';	
		}
		rowData=rowData.concat(jsons);
		materialListTable.rows.add(jsons).draw();
    }
}


var delFun=function(){
	$(".delBtn").on("click",function(){
		if($("#materialListTable").find("tr.selected").length>0){
			$("body").cgetPopup({
				title: '提示信息',
				content: '确认要删除数据吗？',
				accept: delData,
				icon: 'fa-exclamation-circle',
			});
		}else{
			alertInfo("请选择要删除的数据！");
		}
	});	
}

var delData=function(){
	$("#materialListTable").DataTable().rows( '.selected').remove().draw();// 删除本地数据
}


//弹出i屏
var rowIndex=[];
var handleMaterialList = function() {
	"use strict";

  if(!trSData.projectChangeTable.json){
      materialdata.projId=-1;	
  }else{
  	  materialdata.projId=projId1;
  }
	  
	if ($('#materialListTablePop').length !== 0) {
		rowIndex=[];
	    materialListTablePop= $('#materialListTablePop').on( 'init.dt',function(){
	    	//默认选中第一行
  		$(this).bindDTSelected(trCmSelectedBack,false);
	    	//隐藏遮罩
	    	$("#materialListTablePop").hideMask();
	    	//搜索
  		$('#materialListTablePop_filter input').attr('placeholder','设备材料汇总表'); 
  		materialListSearchData();
	    	//console.info(".....");
	    	//console.info(rowIndex);
	        for(var m=0;m<rowIndex.length;m++){
	        	materialListTablePop.row(rowIndex[m]).nodes().to$().addClass("selected");
	        }
	    	
	    }).DataTable({
	    	language: language_CN,
	        lengthMenu: [ 18 ],
	        dom: 'fBrtip',
	        buttons: [
              
	           
	        ],
	        serverSide: true, 
			ajax: {
			    url: 'changeRecord/queryMaterialList',
			    contenttype:"application/json;charset=utf-8",
			    data:function(d){
			        $.each(materialdata,function(i,k){
			            d[i] = k;
			        }); 	
			    },
			    datasrc: 'data'
			},
			select: true,  //支持多选
	        columns: [
	            {"data":"materialId", className:"none never"},
	  			{"data":"materialName"},
	  			{"data":"materialSpec"},
				{"data":"materialUnit"},
				{"data":"materialNum", "className": "text-right"}
				
			],
			order: [[ 0, "asc" ]],
			columnDefs: [{
				targets: 0,
				"visible":false,
				render:function(data,type,row,meta){
					//console.info(rowData);
					for(var i=0;i<rowData.length;i++){
						
						if(data==rowData[i].materialId){
							rowIndex.push(meta.row);
						}
					}
					return data;
				}
			}
			],
			responsive: {
          	details: {
          		renderer: function ( api, rowIdx, columns ){
          			return renderChild(api, rowIdx, columns);
          		}
          	}
          }
	    });
	}
};

function trCmSelectedBack(){
	
}


var saveMaterialList = function(){	
	$(".mcSaveBtn").on("click",function(){
		var t = $('#materialListTable');
		if(t.checkInputs()){
			var data = t.getInputsData();
			console.info("data--");
			console.info(data);
			if(data.length || materialData != null){
				resultData=[];
				for(var i=0;i<data.length;i++){
		       		var datam = data[i];
					if(datam.adjustQuantity!==""){
						datam.cmId=$("#cmId").val();
		   				resultData.push(datam);
		   			}
				  }
				/*if(materialData != null){
					for(var i=0;i<materialData.result.materialList.length;i++){
						materialData.result.materialList[i].cmId=$("#cmId").val();
						resultData.push(materialData.result.materialList[i]);
					}
				}*/
	       		$.ajax({
	       			type: 'POST',
	       			url: 'changeRecord/saveMaterialChange',
	       			contentType: "application/json;charset=UTF-8",
	       			data: JSON.stringify(resultData),
	       			success: function (data) {
	       				var content = "数据保存成功！";
	       				if(data === "false"){
	       					content = "数据保存失败！";
	       				}else if(data === "true"){
	       				    mcData.cmId=$("#cmId").val();
	       				    mcData.projId=projId1;
	       					$("#materialListTable").cgetData(false);  //列表重新加载	            
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
	                   console.warn("材料清单保存失败！");
	               }
	            });
			}else{
				alertInfo("无更新记录！");
			}
		}else{
			console.info("表单校验失败，请检查并修改未通过项目！");
		}
	});
}

var materialListSearchData=function(){
	//基础条件查询添加监听
	$('#materialListTablePop_filter input').on('change',function(){
		var materialName = $('#materialListTablePop_filter input').val();
		materialdata = {};
		if(!trSData.projectChangeTable.json){
	    	materialdata.projId=-1;	
	    }else{
	    	materialdata.projId=projId1;
	    }
		materialdata.materialName = materialName;
		console.info(materialdata);
		$('#materialListTablePop').cgetData();  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#materialListTablePop_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}