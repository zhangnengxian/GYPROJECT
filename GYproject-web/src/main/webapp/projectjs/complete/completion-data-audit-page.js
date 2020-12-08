var searchData = {};
/**
 * 初始化历史审批列表
 */
var projId = $("#projId").val();
var businessOrderId = $("#businessOrderId").val();
var histSearchData = {"projId":projId,"businessOrderId":businessOrderId};
var completionDataTable;
var handleAuditHistory = function() {
	"use strict";
    if ($('#auditHistoryTable').length !== 0) {
    	$('#auditHistoryTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#auditHistoryTable').hideMask();
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'completionDataAudit/queryManageRecord',
                type:'post',
                data: function(d){
                   	$.each(histSearchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/design/json/delay-check-history.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
				{"data":"mrTime"},
				{"data":"mrResult"},
				{"data":"mrAopinion"},
				{"data":"mrCsr"}
			],
			columnDefs: [{
				"targets": 0,
				"render": function ( data, type, row, meta ) {
					if(type === "display"){
						return format(data,'all');
					}else{
						return data;
					}
				},
			},{
				"targets": 1,
				"render": function ( data, type, row, meta ) {
					if(data === "1"){
						return "通过";
					}else if(data === "0"){
						return "不通过";
					}else{
						return "";
					}
				},
			}]
        });
    }
};

/**
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
}




/**
 * 初始化审批历史列表
 */
var auditHistory = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleAuditHistory();
        	
        	if($.fn.DataTable.isDataTable('#completionDataTable')){
        		var data={};
        		data.projId=$("#projId").val();
        		$("#completionDataTable").cgetData(false);//列表重新加载
        	}else{
        		completionDataTableInit();
        	}
        	
        }
    };
}();


var completionDataTableInit=function(){
	"use strict";
	var data={};
	data.projId=projId;
	var pagedLength = 0;
    if ($('#completionDataTable').length !== 0) {
    	completionDataTable= $('#completionDataTable').on( 'init.dt',function(){
    	$('#completionDataTable').hideMask();
    	t.on("page.dt", function(){
			var dt = t.DataTable();
			pagedLength = dt.page() * dt.page.len();
		});
		t.on("draw.dt", function(){
			pagedLength = 0;
		});
        }).DataTable({
        	language: language_CN,
            lengthMenu: [8],
            dom: 'Brtip',
            buttons: [],
            ajax: {
                url: 'completionDataApply/queryDataAcceptanceRecord',
                type:'post',
                data: function(d){
                  	$.each(data,function(i,k){
                  		d[i] = k;
                  	});
               	},
                dataSrc: 'data'
            },
            //ajax: 'projectjs/complete/json/completion_data_list.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            columns: [
                {"data":"caiId",className:"text-center"},
                    {"data":"dataName",className:"text-center"},
    	  			{"data":"dataName"},
    	  			{"data":"dataTypeDes"},
    	  			{"data":"caiId",className:"text-right"}
			],
			columnDefs: [
					{
						"targets": 0,
					    "visible":false
					},{
						"targets": 1,
					    "visible":false
					},{
						"targets":2,
						//长字符串截取方法
						render: $.fn.dataTable.render.ellipsis({
							length: 10, 	//截取多少字符（或汉字）
							end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
						})
					},{
						"targets":4,
						"render": function ( data, type, row, meta ) {
							return addCheckBox(data,row.daId);
						}
					}        
			],
			ordering: false
       });
   }
}



/**
 * 传递记录id和报验单Id
 * 返回复选框html
 */
var addCheckBox = function(caiId,daId){
	var html='<input type="checkbox" class="checkbox"' ;
	if(caiId){
		html += 'value="'+caiId+'"';
	}else{
		//html += 'disabled="disabled"';
	}
	if(daId){
		//html += 'disabled="disabled"';
		html += 'data="'+daId+'"';
		html += 'checked="checked" ';
	}
	html += ' />';
	return html;
}