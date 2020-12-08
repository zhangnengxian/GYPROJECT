
var searchData={}; //查询条件
/**
 * 加载工程列表
 */
var handleAddrSet = function() {
	"use strict";
    if ($('#installRecordAuditTable').length !== 0) {
    	$('#installRecordAuditTable').on( 'init.dt',function(){
    		//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			//$('#addr_batch_panel_box').cgetContent('addrSet/viewPage');
   			//隐藏遮罩
   			$('#installRecordAuditTable').hideMask();
   			
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			
    	}).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
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
            ajax: 'projectjs/complete/json/install_record.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"roomNum"},
				{"data":"num"}
				
				],
			columnDefs: [],
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
var installRecord = function () {
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