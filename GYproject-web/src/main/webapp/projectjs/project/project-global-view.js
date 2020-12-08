var searchData={};//查询条件
var menuId = '1003';
searchData.menuId=menuId;
/**
 * 加载工程列表
 */
var handleProjectGlobalView = function() {
	"use strict";
    if ($('#projectGlobalViewListTable').length !== 0) {
    	$('#projectGlobalViewListTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,false);
   			$('#projectGlobalViewListTable').hideMask();
   			$("#projectGlobalViewListTable_filter input").attr("placeholder","工程编号");
   			//console.info($('#projectGlobalViewListTable').DataTable().ajax.json());
   			//详述监听方法
   			detailMonitor();
   			//查询监听
   			searchMonitor();
   			//接口同步
   			serviceMonitor();
   			setTimeout(function(){
   			    $("#projectGlobalViewListTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [15,30,50,100,200],
            dom: 'lBfrtip',
            buttons: [
				{ text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  ,
                { text: '详述', className: 'btn-sm btn-query detailBtn' },
                { text: '接口同步', className: 'btn-sm btn-query serviceBtn hidden' }  
            ],
             serverSide:true,
             ajax:{
            	 url:"projectView/queryProject",
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
	         select: 'single',
	         columns: [
				{"data":"projId",className:"none never"}, 
				{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"projAddr"},
				{"data":"projScaleDes"},
				{"data":"projStatusDes"},
				{"data":"toDoer"},
				{"data":"projectTypeDes"},
				{"data":"contributionModeDes"},
				{"data":"deptName"},
				
				{"data":"custName"},
				{"data":"custContact"},
				{"data":"custPhone"},
				{"data":"acceptDate"},
				{"data":"surveyer"},
				{"data":"surveyDate"},
				{"data":"duName"},
				{"data":"designer"},
				{"data":"duCompleteDate"},
				{"data":"budgetDate"},
				{"data":"budgeter"},
				/*{"data":"confirmTotalCost"},*/
				{"data":"costMember"},
				/*{"data":"contractAmount"},*/
				{"data":"signDate"},
				{"data":"acceptanceDate"},
				{"data":"acceptanceResult"},
				{"data":"acceptanceDirector"},
				{"data":"settlementDate"},
				{"data":"settlementer"}
		 	],
		 	columnDefs: [
		 	    {
		 	    	targets: 0,
		 	    	visible: false
		 	    },{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 8, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},{
					"targets":3,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				}
		 	    ,{
					"targets":4,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				}/*,{
			    	targets: 18,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}else if(data !==null && data!==""){
								data = data.toFixed(2);
							}
							return data;
						}else{
							return data;
						}
					}
			    }*//*,{
			    	targets: 22,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}else if(data !==null && data!==""){
								data = data.toFixed(2);
							}
							return data;
						}else{
							return data;
						}
					}
			    }*//*,{
			    	targets: 24,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}else if(data !==null && data!==""){
								data = data.toFixed(2);
							}
							return data;
						}else{
							return data;
						}
					}
			    }*/,{
					"targets":5,
					 "orderable":false
				}
			    ,{
					"targets":7,
					 "orderable":false
				}
			    ,{
					"targets":8,
					 "orderable":false
				}

		 	 ]
        });
    }
},

trSelectedBack = function(v, i, index, t, json){
	if($(".corpId").val()==$(".groupCompany").val()){
		$(".serviceBtn").removeClass("hidden");
	}else{
		$(".serviceBtn").addClass("hidden");
	}
},

detailMonitor = function(){
	
	$(".detailBtn").on("click",function(){
		console.info("lenth--"+$("#projectGlobalViewListTable").find("tr.selected").length);
		console.info("trSData--"+trSData.projectGlobalViewListTable.t);
		
		if($("#projectGlobalViewListTable").find("tr.selected").length>0 && trSData.projectGlobalViewListTable.t){
			var projId = trSData.projectGlobalViewListTable.json.projId;
			var url = "#projectView/detailPage?projId="+projId;
			var popoptions = {
				title: trSData.projectGlobalViewListTable.json.projName + ' - 详述',
				content: url,
				ahide: true,
				ccolor: '#59727D',
				ctext: '确定',
				size:'detail',
				nocache: true,
				icon: 'fa-file-text'
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
			//$("#ajax-content").cgetContent("projectView/detailPage",{"projId":projId});
		}else{
			alertInfo('请选择需要查看详述的工程记录！');
		}
	});
},


//查询监听
searchMonitor=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#projectView/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone,
			size:'large'
		});
	});
	$('#projectGlobalViewListTable_filter input').on('change',function(){
		var projNo = $('#projectGlobalViewListTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		searchData.menuId=menuId;
		$("#projectGlobalViewListTable").cgetData();
	});
	//基础条件查询添加回车事件
	$('#projectGlobalViewListTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
},


/**
 * 查询弹出屏，点击确定后 
 */
searchDone = function(){
	searchData = $('#searchForm_globalView').serializeJson();
	var projNo = $('#projectGlobalViewListTable_filter input').val();
	searchData.projNo = projNo;
	searchData.menuId=menuId;
	//列表重新加载
    $('#projectGlobalViewListTable').cgetData(false);
},

reloadBack=function(){
	$('#projectGlobalViewListTable').DataTable().responsive.recalc();
},

	

/**
 * 初始化工程列表
 */
projectGlobalView = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleProjectGlobalView();
        	})
        }
    };
}();
$('#projectGlobalViewListTable').on("draw.dt",function(){
	$(this).DataTable().responsive.recalc();
});

$(".attach-panel").initAttachOper({
	collection: {
		tableid:'projectGlobalViewListTable',
		init: function(){
			//console.info(1);
		}
	}
});


var serviceMonitor=function(){
	$(".serviceBtn").on("click",function(){
		if($("#projectGlobalViewListTable").find("tr.selected").length>0 && trSData.projectGlobalViewListTable.t){
			var projId = trSData.projectGlobalViewListTable.json.projId;
			/*var url = "#projectView/serviceListPop?projId="+projId;
			var popoptions = {
				title: trSData.projectGlobalViewListTable.json.projName + ' - 详述',
				content: url,
				ahide: true,
				ccolor: '#59727D',
				ctext: '确定',
				size:'detail',
				nocache: true,
				icon: 'fa-file-text'
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);*/
			$("body").cgetPopup({
	    		title: '接口调用日志',
	    		content: '#projectView/serviceListPop?projId='+projId,
	    		size: 'large',
	    		callback:function(){
	    			//serviceLogData=$("#serviceLogForm").serializeJson();
	    			//serviceLog.init();
	    		},
	    	    accept: function(){
	    	    	/*if($("#customerTable tr.selected").length < 1){
	    	    		$("body").cgetPopup({title:'提示',content:"请选择申报单位！",newpop:'second',accept:innerClose});
	    	    		return false;
	    	    	}else{
	    		    	var data = trSData.customerTable.json;
	    		    	$("#custName").val(data.custName);
	    		    	$("#custId").val(data.custId);
	    		    	console.info(data.custId);
	    		    	//联系人、联系电话、单位地址
	    		    	$("#custPhone").val(data.custPhone);
	    		    	$("#custEntrustRepresent").val(data.custContcat);
	    		    	$("#unitAddress").val(data.unitAddress);
	    	    	}*/
	    	    }
	      });
			//$("#ajax-content").cgetContent("projectView/detailPage",{"projId":projId});
		}else{
			alertInfo('请选择需要查看详述的工程记录！');
		}
	});
}


