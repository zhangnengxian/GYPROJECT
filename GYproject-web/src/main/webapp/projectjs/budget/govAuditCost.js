var govAuditCostTable,
searchData={};
var menuId = '110309';
searchData.menuId = menuId;
var handleGovAuditCost = function() {
	"use strict";
    if ($('#govAuditCostTable').length !== 0) {
    	govAuditCostTable= $('#govAuditCostTable').on( 'init.dt',function(){
   			//加载页面
   			//$("#budget_register_panel_box").cgetContent("govAuditCost/viewPage");
   			//隐藏遮罩
   			$('#govAuditCostTable').hideMask();
   			$("#govAuditCostTable_filter input").attr("placeholder","工程编号");
   			$("#govAuditCostTable_filter input").on("change",function(){
   				searchData={};
   				searchData.projNo=$("#govAuditCostTable_filter input").val();
   				searchData.menuId = menuId;
   			    $("#govAuditCostTable").cgetData("",govAuditCostBack);  
   			});
   		    //基础条件查询添加回车事件
   			$('#govAuditCostTable_filter input').on('keyup', function(event) {
   		        if (event.keyCode == "13") {
   		        	$(this).change();
   		        }
   		    });
   			//点击行事件
   			$(this).bindDTSelected(trSelectedBack,true);
   	    	//绑定单击事件 弹出搜索框
   			searchPop();
   			//登记监听
   			registerMonitor();
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
                { text: '登记', className: 'btn-sm btn-query business-tool-btn registerBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'govAuditCost/queryProjectBgac',
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
				{"data":"overdue"},
				
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false	
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets": 4,
			    "visible":false	
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
 * 查询事件
 */
var searchPop = function(){
	$('.searchBtn').on("click",function(){
		var url = "#govAuditCost/projectSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
}

var searchDone= function(){
	 searchData= $("#budgetSearchForm").serializeJson();searchData.menuId = menuId;
	 $("#govAuditCostTable").cgetData(true,govAuditCostBack);  //列表重新加载
}

/**
 * 登记事件
 */
var registerMonitor = function(){
	$(".registerBtn").on("click",function(){
		var len=$("#govAuditCostTable").find("tr.selected").length;
		if(len>0){
			$('#govAuditCostForm').toggleEditState(true);
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo("请选中要登记预算审定价的工程！");
		}
	});
}

/**
 * 工程列表选中行回调
 */
var trSelectedBack=function(v, i, index, t, json){
	console.info(json);
	var projId='-1';
	if(json!=undefined ||json!=''){
		$('#projId').val(json.projId);
		projId = json.projId;
	}
	$(".editbtn").addClass("hidden");
	trSData.govAuditCostTable.t.cgetDetail('govAuditCostForm','govAuditCost/queryGovAuditCost?gacType='+$('#gacType').val(),'',queryBackView);
}
/**
 * 详述回调
 */
var queryBackView = function(data){
	var f = $('#govAuditCostForm');
	console.info(data);
	//将工程信息显示页面
	 f.deserialize(data.project);
     f.initFixedNumber();
     $('#gacType1').val($('#gacType').val()); //填充审定价类型
	 $("#stepId").val(getStepId());
	 $("#alPath").val($("#projNo").val()+"/预算");
	 $(".searchButton").attr("href","/accessoryCollect/openCoFile?id="+$("#budgetId").val());
     $(".searchButton").removeClass("hidden");
     $(".Search_Button").addClass("hidden");
	if(data.drawName){
		$(".hasVal").removeClass("hidden");
		$(".noVal").addClass("hidden");
		$(".noVal+#filePreviews tr").remove();
	}else{
		$(".hasVal").addClass("hidden");
		$(".noVal").removeClass("hidden");
	}
}
/**
 * 列表重新加载回调
 */
var govAuditCostBack = function(data){
	var len = $('#govAuditCostTable').DataTable().ajax.json().data ? $('#govAuditCostTable').DataTable().ajax.json().data.length : $('#govAuditCostTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$("#govAuditCostForm").formReset();
		$('.editbtn').addClass("hidden");
		
	 }else{
		 $("#projId1").val(-1);
		 $("#govAuditCostForm").formReset();
		 $('.editbtn').addClass("hidden");
		
	 }
}

/**
 * 初始化页面
 */
var govAuditCost = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handleGovAuditCost();
        	});
        }
    };
}();