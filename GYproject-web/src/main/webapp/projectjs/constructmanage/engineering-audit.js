var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=12;
}else{
	showLength=15;
}
var num = 0;
var searchData = {};
var menuId="110613";
searchData.menuId=menuId;
var handleDesignAlterationAudit = function() {
    searchData.evId=$("#businessOrderId").val();
	"use strict";
    if ($("#designAlterationAuditTable").length !== 0) {
    	var table = $("#designAlterationAuditTable").on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#designAlterationAuditTable').hideMask();
   			//基础查询
   			$("#designAlterationAuditTable_filter input").attr("placeholder","签证编号");
   			//搜索监听
   			searchMonitor();
   			setTimeout(function(){
   			    $("#designAlterationAuditTable").DataTable().columns.adjust();
   			}, 0);
   		//跳转链接
   			if (crossPageBus) {
   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   					//跳转后销毁对象
   				crossPageBus = null
   			}
        }).on( 'draw.dt', function (e, settings, data) {
        	auditBtnMonitor();
        	pageMonitor();  //页面跳转监听，每次绘制完表格监听
        }).DataTable({
        	language: language_CN,
            lengthMenu: [15],
            iDisplayLength:15,//单页显示条数
            dom: 'Bfrtip',
            bStateSave:true,
            bScrollCollapse:true,
           bProcessing: true,
           sPaginationType:"full_numbers",
           deferRender: true, 
           oLanguage: {   
        	      "sLengthMenu": "每页显示 _MENU_ 条记录",    
        	      "sZeroRecords": "对不起，查询不到任何相关数据",    
        	      "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",    
        	      "sInfoEmtpy": "找不到相关数据",    
        	      "sInfoFiltered": "数据表中共为 _MAX_ 条记录)",    
        	      "sProcessing": "正在加载中,请稍等...",    
        	      "sSearch": "搜索：",    
        	      "sUrl": "", //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt    
        	      "oPaginate": {    
        	          "sFirst":    "第一页",    
        	          "sPrevious": " 上一页 ",    
        	          "sNext":     " 下一页 ",    
        	          "sLast":     " 最后一页 "   
        	      }    
        	  }, //多语言配置 
        	  bDeferRender: true,
        	  bLengthChange: false,
               buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'engineeringVisaAudit/queryVisaAudit',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            drawCallback: function( settings ) {  //绘制完表格回调函数
            	  var oTable = $('#designAlterationAuditTable').DataTable();  
            	   $("#designAlterationAuditTable_info").append('<i class="jump-page">，跳到 <input type="text" id="jump_page" min="0" placeholder = "pages" > 页</i>');
            	   $("#jump_page").val(parseInt(oTable.page())+1);  //只能使用大写DataTable，具体原因不知
                   var rows = $('#designAlterationAuditTable').dataTable().fnGetNodes();  //获取当前页table行数据
                   var oTable1 = $('#designAlterationAuditTable').dataTable();
                   if((parseInt(rows.length) == 0) && (parseInt(oTable.page()) > 0)){  //若行数等于0，即没有数据，跳转到上一页
                	   setTimeout(function(){
                		  var page = parseInt(oTable.page()); 
                		    page = parseInt(page) || 1; 
                		    page = page - 1; 
                   	        oTable1.fnPageChange(page);   //只能小写的dataTable,获取当前页数的行数，具体原因不知
              			}, 200);
                   }
            		
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
        		{"data":"evNo", responsivePriority: 1},
                {"data":"evId",className:"none never"},
        		{"data":"projNo", responsivePriority: 3},
  	  			{"data":"projName", responsivePriority: 4},
  	  			{"data":"quantitiesTotal", responsivePriority: 5},
  	  			{"data":"evTypeDesc", responsivePriority: 6},
  	  			{"data":"evOverDes", responsivePriority: 7},
				{"data":"mrAuditLevel", responsivePriority: 2}
			],
			columnDefs: [{
					"targets":1,
					"visible":false
           		 },{
                'targets':4,
                "render": function ( data, type, row, meta ) {
                    if(data!=="" && data!==null){
                        return parseFloat(data).toFixed(2);
                    }else{
                        return data;
                    }
                },
           		 },{
                "targets": 7,
                "render": function (data, type, row, meta){

                    if(type==="display"){
                        var html = getAuditLevelHtml(data,row.level,row.evId,menuId);
                        return html;
                    }else{
                        return data;
                    }
                }
            },{
                "targets":3,
                //长字符串截取方法
                render: $.fn.dataTable.render.ellipsis({
                    length: 10, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            },{
                "targets":2,
                //长字符串截取方法
                render: $.fn.dataTable.render.ellipsis({
                    length: showLength, 	//截取多少字符（或汉字）
                    end: true	//falst从后部开始截取，及从后部开始计数，并裁掉超出的内容,true则相反
                })
            },{
                "targets":6,
                "orderable":false
            },{
                "targets":7,
                "orderable":false
            }],
         
			
        });
    }
};

var trSelectedBack = function(){

	
}

//页数跳转监听
var pageMonitor = function(){
	$("#jump_page").on("change",function(){ 
		var page = $(this).val(); 
	    page = parseInt(page) || 1; 
	    page = page - 1; 
		var oTable = $('#designAlterationAuditTable').dataTable(); 
	    oTable.fnPageChange(page); 

	})
}
/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#engineeringVisaAudit/searchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	
	//基础条件查询添加监听
	$('#designAlterationAuditTable_filter input').on('change',function(){
		var evNo = $('#designAlterationAuditTable_filter input').val();
		searchData = {};
		searchData.evNo = evNo;
		searchData.menuId=menuId;
		//searchData.projId = getProjectInfo().projId;
		$('#designAlterationAuditTable').cgetData(true);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#designAlterationAuditTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
}
var searchDone = function(){
	searchData = $("#searchForm_engineerAudit").serializeJson();
	searchData.menuId=menuId;
	var evNo = $('#designAlterationAuditTable_filter input').val()
	searchData.evNo = evNo;
	//searchData.projId = getProjectInfo().projId;
    $("#designAlterationAuditTable").cgetData(true);  //列表重新加载
}
/**
 * 加载审核屏
 */
var auditBtnMonitor = function(){
	$(document).off("click", ".level").on("click", ".level", function(){
		var isAudit = "0";//未审核过
		if($(this).is(".disabled")) return false;
		if($(this).is(".passed, .refused")){
			isAudit = "1";//已审核过
		}
		var currentLevel = $(this).attr("data-level");
		var evId = $(this).attr("data-rid");
		var data = {"evId":evId,"currentLevel":currentLevel,"isAudit":isAudit};
		$("#ajax-content").cgetContent("engineeringVisaAudit/auditPage",data);
	});
	
}
/**
 * 初始化列表
 */
var designAlterationAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handleDesignAlterationAudit();
        	})
        }
    };
}();