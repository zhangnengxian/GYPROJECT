
var searchData={};
var attachModifyTable;

/**
 * 初始化工程列表
 */
var modifyPop = function () {
	'use strict';
    return {
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){ 
        		handleModifyPop();
        	})
        }
    };
}();

var handleModifyPop = function() {
	'use strict';
	searchData.projId=$("#projId").val();
	console.info("projId1-----"+$("#projId1").val());
	var projId2= $('.modal-body [name="projId"]').val();
	console.info("projId2-----"+projId2);
	if ($('#attachModifyTable').length !== 0) {
		attachModifyTable= $('#attachModifyTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			addBtnMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'rtip',
            buttons: [
                 /* { text: '增加', className: 'btn-sm btn-query business-tool-btn  addBtn' },*/
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'projInfoModify/queryProModify',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/accept/json/attach.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"modifyId",className:"none never"}, 
	  			{"data":"proposer"}, 
				{"data":"proposeDate"},
				{"data":"modifyDes"},
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":3,
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


var trSelectedBack = function(v, i, index, t, json){
	t.cgetDetail('projModifyForm','','',scaleDetailRefresh);
}
var scaleDetailRefresh=function(){
	$(".editBtn").removeClass('hidden');
	$(".editbtn").addClass('hidden');
}

var addBtnMonitor=function(){
	
}