
var sqStandardId=$('#sqStandardId').val();
var projId=$("#projId").val();
var quantitiesData={"projId":projId};	//工程量清单列表
/**
 * 初始化工程量列表
 */
var handleQualitiesAudit = function() {
	"use strict";
    if ($('#qualitiesListTable').length !== 0) {
    	$('#qualitiesListTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#qualitiesListTable').hideMask();
   			$("#qualitiesListTable_filter input").attr("placeholder","分部分项名称");
   			//查询监听
   			searchMonitor();
   			//$(".total-amount").text($("#amount").val());
   			
   			console.info("projId============="+projId);
   			console.info("quantitiesData============="+quantitiesData);
   			console.info("#scAmount============="+$("#subAmount").val());
   			$("tfoot .total-amount:visible").last().text(parseFloat($("#subAmount").val()).toFixed(2));
    	}).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
            ],
            serverSide:true,
            ajax: {
                url: 'qualitiesJudgement/querySubQuantities',
                type:'post',
                data: function(d){
                   	$.each(quantitiesData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/subcontract/json/qualities_audit.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	  			{"data":"sqBranchProjName"}, 
				{"data":"sqUnit",className:"text-center"},
				{"data":"sqLabourPrice",className:"text-right",render:numFix},
				{"data":"sqNum",className:"text-right",render:numFix},
				{"data":"sqAmount",className:"text-right",render:numFix}
			],
			columnDefs: [{
				"targets":0,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}],
			ordering:false
        });
    }
};
function numFix(e){
	return new Number(e).toFixed(2);
}
var searchMonitor=function(){
	$('#qualitiesListTable_filter input').on("change",function(){
		var sqBranchProjName=$('#qualitiesListTable_filter input').val();
		quantitiesData.sqBranchProjName=sqBranchProjName;
		$('#qualitiesListTable').cgetData(false);
	})
	$('#qualitiesListTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
}
/**
 * 初始化工程量列表
 */
var qualitiesJudgement = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){      
        		handleQualitiesAudit();
        	});
        }
    };
}();

$('#qualitiesListTable').on("page.dt",function(){
	$("tfoot .total-amount").text('');
	setTimeout(function(){
		$("tfoot .total-amount:visible").last().text(parseFloat($("#subAmount").val()).toFixed(2));
	},200);
});
$('#qualitiesListTable').on("responsive-resize.dt",function(){
	$("tfoot .total-amount").text('');
	setTimeout(function(){
		$("tfoot .total-amount:visible").last().text(parseFloat($("#subAmount").val()).toFixed(2));
	},200);
});

