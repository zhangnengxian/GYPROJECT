var drawtable,     //工程列表
drawingtable,//图纸列表
materialTable,
drawData = {},//工程
drawingData = {},//图纸
materialData = {},
designerData={},
changeData={};
var menuId = "110204"
drawData.menuId=menuId;
/**
 * 初始化工程列表
 */
handledesignDrawing = function() {
	"use strict";
    drawData.projNo=$("#waitHandleProjNo").val();
    if ($('#designDrawingTable').length !== 0) {
    	drawData.projStatusId = $("#projStatus").val();
    	drawtable = $('#designDrawingTable').on('init.dt', function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack, true);
   			//隐藏遮罩
   			$('#designDrawingTable').hideMask();
   			$("#designDrawingTable_filter input").attr("placeholder","工程编号").on("change",function(){
   				drawData = {};
   				drawData.projStatusId = $("#projStatus").val();
   				drawData.projNo = $("#designDrawingTable_filter input").val();
   				drawData.menuId=menuId;
   					//drawtable.ajax.reload();	
   				$("#designDrawingTable").cgetData(true, reback);  //列表重新加载
   			});
   		    //基础条件查询添加回车事件
   			$('#designDrawingTable_filter input').on('keyup', function(event) {
   		        if (event.keyCode == "13") {
   		        	$(this).change();
   		        }
   		    });
   		   //查询按钮弹出屏查询
   			$(".searchBtn").off("click").on("click",function(){
				var url = "#designDrawing/projectSearchPopPage";
				//加载弹屏
				$("body").cgetPopup({
					title: '查询',
					content: url,
					accept: searchDone
				});
   			});
   			$("#drawing_list_box").cgetContent("designDrawing/viewPage");
   			//改派方法 弹出选择屏
   			changeMonitor();
   			//改派权限判断
   			changeDispatch();
   			
   			setTimeout(function(){
   			    $("#designDrawingTable").DataTable().columns.adjust();
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
            dom: 'Bftrip',
            buttons: [
                { text: '改派', className: 'btn-sm btn-query business-tool-btn hidden changeBtn' },
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '确认', className: 'btn-sm btn-query business-tool-btn hidden confirmBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'designDrawing/queryProject',
                type:'post',
                data: function(d){
                   	$.each(drawData,function(i,k){
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
                {"data":"projId", className:"none never", responsivePriority: 7},
				{"data":"projNo", responsivePriority: 1},
				{"data":"projName", responsivePriority: 2},
				{"data":"surveyDate", responsivePriority: 4},
				{"data":"projStatusDes", responsivePriority: 5},
				{"data":"overdue", className:"none never", responsivePriority: 6},
				{"data":"signBack", className:"none never", "createdCell": function (td, cellData, rowData, row, col) {
						if(cellData == $("#notThrough").val()){
							$(td).parent().css("background", "rgb(255, 219, 219)");
							//$(td).attr("id",row);
						}
					}
				},
			],
			columnDefs: [{
				"targets":0,
				 "visible":false
			},{
				 "targets":2,
				 //长字符串截取方法
				 render: $.fn.dataTable.render.ellipsis({
					length: 8, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				 })
			},{
				"targets":4,
				 "orderable":false
			},{
				"targets":5,
				 "visible":false
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
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$("#projectInfoForm input,#projectInfoForm textarea").val('');
	 $("#designer").val(json.designer);
	 $("#projId").val(json.projId);
	 $("#feedbackState").val(json.feedbackState)
	if(json!=undefined){
		$("#draw_projId").val(json.projId);
		$("#draw_projNo").val(json.projNo);
		$("#projId1").val(json.projId);
	}
	 $('.confirmBtn').removeClass('hidden');
	drawingData.projId = $("#projId1").val();
	if($.fn.DataTable.isDataTable('#drawingTable')){
		drawingtable.ajax.reload();
	}else{
		drawingtableinit();
	}
	materialData.projId = $("#projId1").val();
	if($.fn.DataTable.isDataTable('#materialTable')){
		materialTable.ajax.reload();
	}else{
		handlematerialList();
	}
	
	if($("#drawingListTab").parent().hasClass("active")){
		   drawingData.projId = $("#projId1").val();
		   if($.fn.DataTable.isDataTable('#drawingTable')){
			   drawingtable.ajax.reload();
		   }
	}else if($("#materailListTab").parent().hasClass("active")){
			  materialData.projId = $("#projId1").val();
			  materialTable.ajax.reload();
	}else if($("#projectInfoTab").parent().hasClass("active")){
		console.info("1----------");
		t.cgetDetail('projectInfoForm','designDrawing/viewProject','',proDetailBack);
	} 
	t.cgetDetail('projectInfoForm','','',detailBack);
};

var detailBack=function(data){
	if(typeof(data.duCompleteDate)){
		$("#duCompleteDate").addClass("timestamp");
	}else{
		$("#duCompleteDate").removeClass("timestamp");
	}
	if($.fn.DataTable.isDataTable('#materialTable')){
		//初始化过
		$("#materialTable").cgetData(false);//列表重新加载
	}else{
		handlematerialList();
	}
};

var proDetailBack=function(){
	if($("#deptDivide").val()==$("#customerServiceCenter").val()){
		$(".designFee").addClass("hidden");
	}else{
		$(".designFee").removeClass("hidden");
	}
	$("#projectInfoForm").styleFit();
	if($("#custName").val()==""){
		$(".noUser").addClass("hidden");
	}else{
		$(".noUser").removeClass("hidden");
	}
	$(".editbtn").addClass("hidden");
	$("#flag").val('0');
	$('.backReason').addClass('hidden');
}

var changeMonitor=function(){
	$(".changeBtn").off().on("click",function(){
    	$("body").cgetPopup({
    		title: '设计员选择',
    		content: '#popup/designerListPop',
    		size: 'large',
    	    accept: function(){
    	    	if($("#designerTable tr.selected").length < 1){
    	    		$("body").cgetPopup({title:'提示',content:"请选择设计员！",newpop:'second',accept:innerClose});
    	    		return false;
    	    	}else{
    		    	
    		    	var json = $("#designerTable").DataTable().rows( '.selected' ).data();
    		    	var designer=json[0].surveyer;//设计人
    		    	var designerId=json[0].staffId;
    		    	
    		    	changeData.surveyer=designer;
    		    	changeData.designerId=designerId;
    		    	changeData.projId=$("#projId").val();

    		    	$.ajax({
       	                type: 'POST',
       	                url: 'designDrawing/updateProject',
       	                contentType: "application/json;charset=UTF-8",
       	                data: JSON.stringify(changeData),
       	                success: function (data) {
       	                	var content = "改派成功！";
       	                	if(data === "false"){
       	                		content = "改派失败！";
       	                	}
       	                	var myoptions = {
       	                		title: "提示信息",
       	                        content: content,
       	                        accept: changeBack,
       	                        chide: true,
       	                        newpop: 'new',
       	                        icon: "fa-check-circle"
       	                    }
       	                    $("body").cgetPopup(myoptions);
       	                },
       	                error: function (jqXHR, textStatus, errorThrown) {
       	                    console.warn("改派失败！");
       	                }
       	            }); 
    	    	}
    	    }
      });
  	});
	
}
var changeBack=function(){
	$("#designDrawingTable").cgetData(true);
}


var changeDispatch = function(){
	if(getBtn($("#changeDesigner").val()).length>0){
		$('.changeBtn').removeClass('hidden');
	};
	
}
/**
 * 初始化设计图目录
 */
var drawingtableinit = function() {
	"use strict";
	if(trSData.designDrawingTable.json){
	  drawingData.projId = trSData.designDrawingTable.json.projId;
	}else{
		drawingData.projId = -1;
	}
	drawingtable = $('#drawingTable').on('init.dt', function(){
		$('#drawingTable').hideMask();
		//导入监听
		importMonitor();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [18],
        dom: 'Brtip',
        buttons: [
            { text: '导入', className: 'btn-sm btn-query business-tool-btn importBtn' }
        ],
        serverSide:true,
        ajax: {
            url: 'designDrawing/queryDrawDirectory',
            type:'post',
            data: function(d){
               	$.each(drawingData,function(i,k){
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
        columns: [
			{"data":"ddNum",className:"text-right"},
			{"data":"drawDirect"},
			{"data":"drawingNo"},
			{"data":"mapSheet"},
  			{"data":"drawQuantity",className:"text-right"},
  			{"data":"convertIntoNum",className:"text-right"},
			{"data":"remark"}
	    ],
	    ordering:false
   });  
	if($('#designDrawingTable').DataTable().data().length>0){
		if(getBtn($("#importDesigner").val()).length>0){
			$(".importBtn").removeClass("hidden");
			$(".import1btn").removeClass("hidden");
			if($("#feedbackState").val()==""||$("#feedbackState").val()=="1"){
				$(".saveProcbtn").removeClass("hidden");
			}else{
				$(".saveProcbtn").addClass("hidden");
			}
			
		};
	}
}


/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	drawData = $("#searchForm_draw").serializeJson();
	drawData.projStatusId=$("#projStatus").val();
	drawData.menuId=menuId;
	drawData.projNo=$("#designDrawingTable_filter input").val();
    $("#designDrawingTable").cgetData(true,reback);  //列表重新加载
    
}

/**
 * 初始化材料清单
 */
var handlematerialList = function(){
	"use strict";
	//关联projId
	if(!$("#projId1").val() == ""){
	    materialData.projId=$("#projId1").val();
	}else{
		materialData.projId = -1;
	}
	materialTable = $('#materialTable').on( 'init.dt', function(){
		$(".import1btn").off("click").on("click", function(){
			$("body").cgetPopup({
				title: '文件导入',
				content: "#officialDrawing/importPop?url=officialDrawing/importExcel",
				accept: importOk,
				nocache: true
			});
		});
		$('#materialTable').hideMask();
	}).DataTable({
	    	language: language_CN,
	        lengthMenu: [ 15 ],
	        dom: 'Brtip',
	        buttons: [
	            { text: '导入', className: 'btn-sm btn-query business-tool-btn  import1btn' }
	            
	        ],
	        serverSide: true,
	        ajax: {
	            url: 'officialDrawing/queryMaterialDirectory',
	            type:'post',
	            data: function(d){
	               	$.each(materialData,function(i,k){
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
	  			{"data":"dmNo"},
	  			{"data":"dmName"},
	  			{"data":"dmSpec"},
	  			{"data":"dmUnit"},
				{"data":"dmNum",className:"text-right"},
				{"data":"remark"},
				{"data":"flagDes"}
		    ],
			columnDefs: [{
			}],
			ordering:false
	    });
	if($('#designDrawingTable').DataTable().data().length>0){
		if(getBtn($("#importDesigner").val()).length>0){
			$(".importBtn").removeClass("hidden");
			$(".import1btn").removeClass("hidden");
		};
	}
		
}

//图纸目录导入
var importMonitor = function(){
	$(".importBtn").off("click").on("click",function(){
		$("body").cgetPopup({
			title: '文件导入',
			content: "#designDrawing/importPop?url=designDrawing/importExcel",
			accept: importOk2,
			nocache: true
		});
	});	
	$(".import1btn").off("click").on("click",function(){
		$("body").cgetPopup({
			title: '文件导入',
			content: "#officialDrawing/importPop?url=officialDrawing/importExcel",
			accept: importOk,
			nocache: true
		});
	});
	$('.confirmBtn').off().on('click',function(){
		 if($('#designDrawingTable').find('tr.selected').length>0){
			 $(".editbtn,.import1btn,.import1btn").removeClass("hidden");
			 $("#projectInfoForm").toggleEditState(true);
		 }else{
			 alertInfo('请选择要登记的工程记录！');
		 }
		
		
		 
		
		
		/*if($("#projectInfoForm").parsley().isValid()){
	    	//是否导入图纸目录
    		var len = $('#drawingTable').DataTable().ajax.json().data ? $('#drawingTable').DataTable().ajax.json().data.length : $('#drawingTable').DataTable().ajax.json().length;
    		var length = $('#materialTable').DataTable().ajax.json().data ? $('#materialTable').DataTable().ajax.json().data.length : $('#materialTable').DataTable().ajax.json().length;
    		
    		if(len<1 ){
    			alertInfo('请导入图纸目录');
    		}else if(length<1){
    			alertInfo('请导入材料目录');
    		}else{
    			$("body").cgetPopup({
  		    	  title: "提示信息",
  		    	  content: "相应文件是否导入正确已核实？点击确定后推送！",
  		    	  accept: successBack1,
  		    	  icon: "fa-check-circle",
  		    	  newpop: 'new'
    			});
    		}
		}else{
        	//如果未通过验证, 则加载验证UI
        	$("#projectInfoForm").parsley().validate();
        }*/
	});
}








function successBack(){
	$('#designDrawingTable').cgetData(true,reback);
}
function reback(){
	//console.info("==" + drawtable.ajax.json().data.length);
	if(drawtable.ajax.json().data.length<1){
		$("#projectInfoForm input,#projectInfoForm textarea").val('');
		$(".confirmBtn").addClass("hidden");
		$(".importBtn").addClass("hidden");
		$(".import1btn").addClass("hidden");
		$(".editbtn").addClass("hidden");
		if($("#drawingListTab").parent().hasClass("active")){
			drawingData.projId = -1;
			drawingtable.ajax.reload();
		}else if($("#materailListTab").parent().hasClass("active")){
			materialData.projId=-1;
			materialTable.ajax.reload();
		}
		$("#projId1").val('');
		$('#isBack').val('0');
		$('#backReason').val('');
	}else{
	/*	if(getBtn($("#conformDesigner").val()).length>0){
			if($("#feedbackState").val()=="3"){
				$('.confirmBtn').removeClass('hidden');
			}else{
				$('.confirmBtn').addClass('hidden');
			}
		};
		if(getBtn($("#importDesigner").val()).length>0){
			$(".importBtn").removeClass("hidden");
			$(".import1btn").removeClass("hidden");
			if($("#feedbackState").val()==""||$("#feedbackState").val()=="1"){
				$(".saveProcbtn").removeClass("hidden");
			}else{
				$(".saveProcbtn").addClass("hidden");
			}
		};*/
		if($("#drawingListTab").parent().hasClass("active")){
			 drawingData.projId = $("#projId1").val();
			 if($.fn.DataTable.isDataTable('#drawingTable')){
				 drawingtable.ajax.reload();
			 }
		}else if($("#materailListTab").parent().hasClass("active")){
			//console.info("tt==" + $("#projId1").val());
			materialData.projId=$("#projId1").val();
			materialTable.ajax.reload();
		} 
		$('#isBack').val('0');
		$('#backReason').val('');
	}
}
var importOk2 = function (){
	//console.info("====" + $("#projId1").val());
	drawingData.projId = $("#projId1").val();
	drawingtable.ajax.reload();
}

var importOk=function(){
	materialData = {};
	materialData.projId = $("#projId1").val();
	materialTable.ajax.reload();
}


/**
 * 初始化工程列表
 */
var designDrawing = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
	        	//console.info(1234);
	        	handledesignDrawing();
	        	/**
	        	 * 切换页签
	        	 */
	        	$("#drawingListTab,#materailListTab").on("shown.bs.tab",function(){
	        		if($(this).is($("#drawingListTab"))){
	        			
	        			if(!$.fn.DataTable.isDataTable('#drawingTable')){
	        				drawingtableinit();
	        			}else{
	        				if($("#projId1").val()){
	           				 drawingData.projId = $("#projId1").val();
	           			}else{
	           				 drawingData.projId = "-1";
	           			}
	           			$('#drawingTable').DataTable().ajax.reload();
	        			}
	        		}else{
	        			if(!$.fn.DataTable.isDataTable('#materialTable')){
	        				handlematerialList();
	        				//console.info('5...');
	        			}else{
	        				//console.info('6...');
	        				if($("#projId1").val()){
		        				materialData.projId = $("#projId1").val();
		           			}else{
		           				materialData.projId = "-1";
		           			}
	        				$('#materialTable').DataTable().ajax.reload();
	        			}
	        		}
	        	});
        	})
        }
    };
}();

