var searchData={}; //查询条件


var projNoLength;

/**
 * 加载工程列表
 */
var handleProjectInspectList = function() {
	"use strict";
    if ($('#projectInspectListTable').length !== 0) {
    	$('#projectInspectListTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,false);
   			$('#projectInspectListTable').hideMask();
   			$("#projectInspectListTable_filter input").attr("placeholder","工程编号");
   			var thisdt = $(this).DataTable();
   			thisdt.on( 'deselect', function (e, dt, type, indexes) {
   				if(window.localStorage){
   					localStorage.setItem("curProjectInspectSet", '');   
   				}else{      
   					var curProjectInspectSet = '';
   				}
   				thisdt[ 'rows' ]( indexes ).nodes().to$().find('[name="selectedProj"]').attr("checked",false);
   				$("#sidebar-nav li, #sidebar-nav a").addClass("disabled");
   			});
   			searchMonitor();
   			if(_inApk){
    			projNoLength=11;
    		}else{
    			projNoLength=15;
    		}
   			docPopMonitor();
   		//是否存在跳转
   			if (crossPageBus) {
   				//获取唯一值
   				var projectNo = crossPageBus.projectNo;
   				//
   				$(this).one('select.dt', function() {
   					//判断是否传menuId或者url
	   				if (crossPageBus.pageId) {
	   					crossPageBus.mid = crossPageBus.pageId
	   					crossPageOperate(crossPageBus)
	   				} else if (crossPageBus.pageUrl) {
	   					crossPageBus.hash = crossPageBus.pageUrl
	   					crossPageOperate(crossPageBus)
	   				}

	   				getSidebarMenu(13, true);
	   				checkSidebarMenu(crossPageBus.hash)
	   				
   					//跳转后销毁对象
	   				crossPageBus = null
	   			})
	   			//输入框触发change事件
	   			setTimeout(function () {
	   				$("#projectInspectListTable_filter input").val(projectNo).trigger('change')
	   			}, 0)
   			}
        }).DataTable({
        	language: language_CN,
            lengthMenu: [15,30,50,100,200],
            bStateSave: true,
            dom: 'lfBrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn'},
                { text: '报验信息', className: 'btn-sm btn-query docBtn' }
            ],
            serverSide:true,
            ajax:{
            	url:"projectInspectList/queryProject",
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
			    {"data":"projNo", responsivePriority: 1},
				{"data":"projName", responsivePriority: 2},
				{"data":"projAddr", responsivePriority: 3},
				{"data":"projScaleDes", responsivePriority: 6},
				{"data":"projStatusDes", responsivePriority: 4},
				{"data":"budiler", responsivePriority: 7},
				{"data":"cuPm", responsivePriority: 8},
				{"data":"managementQae", responsivePriority: 9},
				{"data":"saftyOfficer", responsivePriority: 10},
				{"data":"managementWacf", responsivePriority: 11},
				{"data":"documenter", responsivePriority: 12},
				{"data":"testLeader", responsivePriority: 13},
				{"data":"welder", responsivePriority: 14},
				{"data":"technician", responsivePriority: 15},
				{"data":"suCse", responsivePriority: 16},
				{"data":"suJgj", responsivePriority: 17},
				{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}/*,
				{"data":null, className: "text-center", responsivePriority: -1}*/
		 	],
		 	columnDefs: [
		 	    {
		 	    	targets: 0,
		 	    	visible: false
		 	    },{
					"targets":1,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: projNoLength, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 8, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},
				{
					"targets":3,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 8, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},{
					"targets":4,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 15, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},{
					"targets":6,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 15, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					}),
					"orderable":false
				}/*,{
		 	    	targets: 14,
		 	    	render: function(a, b, c, d){
		 	    		return '<div class="tdInnerInput"><label><input type="radio" name="selectedProj" value="' + (d.row + 1) + '"/></label></div>';
		 	    	}
		 	    }*/,{
					"targets":5,
					 render:function(data,type,row,meta){
						 if(row.projConStatusDes){
								return data+' ('+row.projConStatusDes+')';
							}
							return data;
						}
				},{
					"targets":7,
					 "orderable":false
				},{
					"targets":8,
					 "orderable":false
				},{
					"targets":9,
					 "orderable":false
				},{
					"targets":10,
					 "orderable":false
				},{
					"targets":11,
					 "orderable":false
				},{
					"targets":12,
					 "orderable":false
				},{
					"targets":13,
					 "orderable":false
				}
		 	]
        });
    }
},

trSelectedBack = function(v, i, index, t, json){
	t.find('[name="selectedProj"]').attr("checked","checked");
	if(window.localStorage){
		localStorage.setItem("curProjectInspectSet", JSON.stringify(json));   
	}else{
		curProjectInspectSet = json;
	}
//  报验屏可能不需要判断工程状态	
//	if(json.projStatusId === "1018"){
//		//如果工程状态为待施工
//		$("#sidebar-nav li.disabled:eq(0), #sidebar-nav li:eq(0) a.disabled").removeClass("disabled");
//	}else{
//		//如果工程状态为施工中
//		$("#sidebar-nav li.disabled:not(:first-child), #sidebar-nav li:not(:first-child) a.disabled").removeClass("disabled");
//	}
	if(json.projStatusId ==="1021"
			||json.projStatusId==="1017"
			||json.projStatusId==="1018"
			||json.projStatusId==="101801"
			||json.projStatusId==="1019"
			||json.projStatusId==="1020"
			||json.projStatusId==="10201"){
		//已完成开工报告
    	if(json.projConStatus == "work_report" || json.projConStatus == "shutdownRecord"){
    		$("#sidebar-nav li.disabled, #sidebar-nav a.disabled").removeClass("disabled");
    	}else{
    		$("#sidebar-nav li, #sidebar-nav a").addClass("disabled");
    		alertInfo("该工程还没有完成开工报告，还不能进行工艺填写！");
    	}
	}else{
		$("#sidebar-nav li.disabled, #sidebar-nav a.disabled").removeClass("disabled");
	}
},
//弹屏监听方法
searchMonitor = function(){
	//查询按钮弹出屏查询
	$(".searchBtn").on("click",function(){
			var url = "#projectInspectList/inspectSearchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#stateSelect,#projectInspectListTable_filter input").on("change",function(){
		var projNo = $("#projectInspectListTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;
		$("#projectInspectListTable").cgetData(true);  //列表重新加载
		
	});
	//基础条件查询添加回车事件
	$('#projectInspectListTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
},
searchDone = function(){
	searchData = $("#searchForm_list").serializeJson();
	var projNo = $("#projectInspectListTable_filter input").val();
	searchData.projNo = projNo;
	//console.log("searchData:"+JSON.stringify(searchData));
    $("#projectInspectListTable").cgetData(false);  //列表重新加载
},
docPopMonitor = function(){
	//查询按钮弹出屏查询
	$(".docBtn").on("click",function(){
		if($("#projectInspectListTable").find("tr.selected").length>0){
			console.info("projId---"+trSData.projectInspectListTable.json.projId);
			var projId=trSData.projectInspectListTable.json.projId;
			var url = "#checkList/main?projId="+projId;
			var popoptions = {
				title: '报验信息',
				content: url,
				/*size:'large',*/
				ahide:false,
				chide:false,
				atext:"保存",
				callback:qualityCheckCallback,
				nocache:true,
				accept:docDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
		}else{
			alertInfo("请选择工程记录");
		}
	});
}
var qualityCheckCallback = function(){
	$(".check-list-projId").val("");
	$(".check-list-clId").val("");
	/*$(".remark").val("");*/
	/*$('input:radio:checked').val("");*/
	$("#qualityCheckForm").formReset();
	trSData.t && trSData.t.cgetDetail('qualityCheckForm','checkList/selfInspectionRecordQuqlity','',detailCallback);
}
var detailCallback = function(){
	$(".tab-content").hideMask();
}
docDone=function(){
	var data=$("#qualityCheckForm").getDTFormData();
	console.log("保存的信息————————————————————————————");
	console.log(data);
	$.ajax({
        type: 'POST',
        url: 'checkList/saveCheckList',
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data),
        success: function (data) {
        	var content = "保存成功！";
        	if(data === "false"){
        		content = "保存失败！";
        	}else if(data === "true"){
        	}
        	var myoptions = {
                	title: "提示信息",
                	content: content,
                	accept: popClose,
                	chide: true,
                	newpop: 'new',
                	icon: "fa-check-circle"
            }
            $("body").cgetPopup(myoptions);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("保存失败！");
        }
    });
},
/**
 * 初始化工程列表
 */
projectInspectList = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handleProjectInspectList();
        		if(_inApk){
        			projNoLength=11;
        		}else{
        			projNoLength=15;
        		}
        		console.info("projNoLength1--"+projNoLength);
        	})
        }
    };
}();