
var searchData={}; //查询条件
/**
 * 加载工程列表
 */
var handleAddrSet = function() {
	"use strict";
    if ($('#addrSetTable').length !== 0) {
    	$('#addrSetTable').on( 'init.dt',function(){
    		//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#addr_batch_panel_box').cgetContent('addrSet/viewPage');
   			//隐藏遮罩
   			$('#addrSetTable').hideMask();
   			$("#addrSetTable_filter input").attr("placeholder","楼栋号");
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			
    	}).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '增加', className: 'btn-sm btn-query business-tool-btn searchBtn' }
                ],
            //启用服务端模式，后台进行分段查询、排序
			/*serverSide:true,
            ajax: {
                url: 'jointAcceptance/queryJointAcceptancer',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },*/
            ajax: 'projectjs/complete/json/addr_set.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"projNo"},
				{"data":"projName"},
				{"data":"lou"},
				{"data":"biao"}
				],
			columnDefs: [{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
}

var trSelectedBack=function(){
	
}

var searchMonitor=function(){
	
}

/**
 * 初始化工程列表
 */
var addrSet = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleAddrSet();
        	});
        }
    };
}();