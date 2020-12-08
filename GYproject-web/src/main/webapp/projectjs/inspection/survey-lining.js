/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var surveyTable;
/**查询条件*/
var searchData={};
var projId=$('#projId').val();
searchData.projId=projId;
var handlesurveyLining = function() {
	"use strict";
    if ($('#surveyTable').length !== 0) {
    	surveyTable=$('#surveyTable').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBack,true);
    		$('#surveyTable_filter input').attr('placeholder','报验部位');
    		showReport();
   			//隐藏遮罩
   			$('#surveyTable').hideMask();
   			//增加监听
   	    	addMonitor();
   	    	//修改监听
   	    	modifyMonitor();
   	    	//查询监听
   	    	searchMonitor();
   	    	queryCheckRole();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'surveyLining/queryProjectList',
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
                {"data":"pcId",className:"none never"},
                {"data":"inspectionDate"}, 
				{"data":"slPart"},
                {"data":"inspectionResult"}
			],
			order: [[ 1, "asc" ]],
			columnDefs: [{
				"targets":0,
				"visible":false
			}]
        });
    }
};

/** 选中行时，查询详述*/
var trSelectedBack = function(v, i, index, t, json){
	t.cgetDetail('surveyLiningForm','surveyLining/viewSurveyLining','',queryBack);
}
var queryBack=function(){
	showReport();
}
//查询监听
var  searchMonitor=function(){
	//基础条件查询添加监听
	$('#surveyTable_filter input').on('change',function(){
		var slPart = $('#surveyTable_filter input').val();
		var pcDesId=$('#pcDesId').val();
		searchData={'slPart':slPart,'pcDesId':pcDesId,'projId':projId};
		$('#surveyTable').cgetData(true,surveyLiningCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#surveyTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

var surveyLiningCallBack=function(){
	var len = $('#surveyTable').DataTable().ajax.json().data ? $('#surveyTable').DataTable().ajax.json().data.length : $('#surveyTable').DataTable().ajax.json().length;
	if(len<1){
		$('.addClear').val('');
		//清空签字
		$(".clear-sign").click();
	}
	showReport();
}



//增加监听
var addMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		$('#surveyLiningForm').toggleEditState(true);
		$(".editbtn").removeClass("hidden");
		//切换页签
		//$('ul.nav-tabs>li.active').removeClass("active");
		$('#liningInfoTab').tab("show");
		//清空表单
		$('.addClear').val('');
		$(".clear-sign").click();
		$("#qrCodePath").val(baseUrl);
		$('#constructionUnit').val(getProjectInfo().cuName);
	});
}
//修改监听
var modifyMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		var len=$("#surveyTable").find("tr.selected").length;
		if(len>0){
			$(".editbtn").removeClass("hidden");
			//切换页签
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#liningInfoTab').tab("show");
			$("#qrCodePath").val(baseUrl);
			$('#surveyLiningForm').toggleEditState(true);
			
			//施工或竣工中
        	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#surveyLiningForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#surveyLiningForm").find(".sign-data-input").toggleSign(false);
        	}
			
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的报验单信息!',
				ahide:true,
				atext:'确定'
			});
		}
	});
}

//初始化表格
var surveyLining = function () {
	"use strict";
    return {
        init: function () {
        	handlesurveyLining();
        	$("#liningListTab,#liningInfoTab").on("shown.bs.tab",function(){
    			if($(this).is($('#liningListTab'))){
    				if(!$.fn.DataTable.isDataTable('#surveyTable')){
    					handlesurveyLining();
        			}else{
        				$('#surveyTable').cgetData(true);
        				$(".editbtn").addClass("hidden");
        				$('#surveyLiningForm').toggleEditState(false);
        				showReport();
        			}
    			}else{
    				if(trSData.surveyTable.json && $('#pcId').val()!=""){//修改
    					showReport();
    				}else{//增加
    					$('.addClear').val('');
    					//清空签字
    					$('.clear-sign').click();
    					showReport3();
    				}
    				setTimeout(function(){
    				    $("#projectImagesList").getImagesList({
    				    	"projId": getProjectInfo().projId,
    				    	"stepId": getStepId(),
    				    	"projNo": getProjectInfo().projNo,
    				    	"busRecordId": $("#pcId").val() || '-1'
    				    });
    				},300);
    			}
        	});
        }
    };
}();

