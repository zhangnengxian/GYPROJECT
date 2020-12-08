

var hiddenWorksTable;
var checkListData={};
var projId=$('#projId').val();
checkListData.projId=projId;
/**
 * 初始化信息
 */
var handlehidden=function(){
	"use strict";
	if($('#hiddenWorksTable').length !== 0){
		hiddenWorksTable= $('#hiddenWorksTable').on( 'init.dt',function(){
		$(this).bindDTSelected(trSelectedBack,true);
		$('#hiddenWorksTable_filter input').attr('placeholder','隐蔽部位');
		hiddenWorks1();
		//隐藏遮罩
		$("#hiddenWorksTable").hideMask();
		//增加监听
		addMonitor();
		//修改监听
		modifyMonitor();
		//查询监听
		searchMonitor();
		queryCheckRole();
	    }).DataTable({
	    	language: language_CN,
	        lengthMenu: [ 15 ],
	        dom: 'Bfrtip',
	        buttons: [
	                  { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
	                  { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' }
	              ],
	        serverSide: true, 
	        ajax: {
	            url: 'alarmHiddenWorks/queryProjectList',
	            type:'post',
	            data: function(d){
	               	$.each(checkListData,function(i,k){
	               		d[i] = k;
	               	});
	               	
	            },
	            datasrc: 'data'
	        },
	        responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
	        select: true,
	        columns: [
	          	{"data":"pcId",className:"none never"},      
	  			{"data":"inspectionDate"}, 
				{"data":"process"},
				{"data":"inspectionResult"}
			],
			order: [[ 1, "asc" ]],
			columnDefs: [{
				"targets":0,
				"visible":false
			}] 
	    });
	}	
}

//查详述
var  trSelectedBack=function(v, i, index, t, json){
	 $('#pcId').val(json.pcId);
	 t.cgetDetail('hiddenWorksForm','hiddenWorks/viewhiddenWorks','',queryHiddenWorksBack);
};

var queryHiddenWorksBack=function(){
	hiddenWorks1();
	/*//清空记录表单值后再查详述
	if(trSData.hiddenWorksTable.json){
		$('#hiddenWorksRecordForm input,#hiddenWorksRecordForm textarea').val('');
		trSData.t.cgetDetail('hiddenWorksRecordForm','hiddenWorks/viewhiddenWorksRecord','',queryHiddenWorkRecordsBack);
		alert('1');
	}else{
		$('#hiddenWorksRecordForm input,#hiddenWorksRecordForm textarea').val('');
		alert('2');
	}*/
	//$('#hiddenWorksRecordForm input,#hiddenWorksRecordForm textarea').val('');

}


//查询监听
var  searchMonitor=function(){
	//基础条件查询添加监听
	$('#hiddenWorksTable_filter input').on('change',function(){
		var process = $('#hiddenWorksTable_filter input').val();
		checkListData = {};
		checkListData.process = process;
		var pcDesId=$('#pcDesId').val();
		checkListData={'process':process,'pcDesId':pcDesId,'projId':projId};
		$('#hiddenWorksTable').cgetData(true,hiddenCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#hiddenWorksTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

var hiddenCallBack=function(){
	var len = $('#hiddenWorksTable').DataTable().ajax.json().data ? $('#hiddenWorksTable').DataTable().ajax.json().data.length : $('#hiddenWorksTable').DataTable().ajax.json().length;
	if(len<1){
		//清空右侧记录
		$('.addClear').val('');
		$('#hwId').val('');
	 }
	//清空签字
	$(".clear-sign").click();
	hiddenWorks1();
	/*$('#hiddenWorksRecordForm input,#hiddenWorksRecordForm textarea').val('');*/
}

//增加监听
var addMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		$('#hiddenWorksForm').toggleEditState(true);
		$(".editBtn").removeClass("hidden");
		//$('ul.nav-tabs>li.active').removeClass("active");
		$('#signTab').tab("show");
		//清空要增加的数据项
		$('.addClear').val('');
		//清空签字
		$('.clear-sign').click();
		$('#constructionUnit').val(getProjectInfo().cuName);
	});
}

//修改监听
var modifyMonitor=function(){
	
	$('.updateBtn').off('click').on('click',function(){
		var len=$("#hiddenWorksTable").find("tr.selected").length;
		if(len>0){
			$('#hiddenWorksForm').toggleEditState(true);
			$(".editBtn").removeClass("hidden");
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#signTab').tab("show");
			//施工或竣工中
        	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#hiddenWorksForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#hiddenWorksForm").find(".sign-data-input").toggleSign(false);
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



var hiddenWorksAndAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handlehidden();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab,#auditTab, #signTab").on("shown.bs.tab",function(){
        		if($(this).is($("#listTab,#signTab"))){
    				if($(this).is($('#listTab'))){
        				if(!$.fn.DataTable.isDataTable('#hiddenWorksTable')){
        					handlehidden();
            			}else{
            				$('#hiddenWorksTable').cgetData(true);
            				$(".editBtn").addClass("hidden");
            				/*$(".editbtn").addClass("hidden");
            				$('.editmodifybtn').removeClass('hidden');*/
            				$('#hiddenWorksForm').toggleEditState(false);
            				/*$('#hiddenWorksRecordForm').toggleEditState(false);*/
            			}
        			}else{
        				if($('#pcId').val()!=""){
	        				$('#hiddenWorksRecordForm').toggleEditState(true);
	        				hiddenWorks1();
        				}else{
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
        		}else{
        			if($('#pcId').val()!==''){
        				//先清空再详述
        				//$('#hiddenWorksRecordForm input,#hiddenWorksRecordForm textarea').val('');
        				//trSData.t.cgetDetail('hiddenWorksRecordForm','hiddenWorks/viewhiddenWorksRecord','');
        				var data={};
						data.id=$('#pcId').val();
						var menuDes = $('[data-mid="' + getStepId() + '"]').text();
						$('#hiddenWorksRecordForm').getFormDetail('hiddenWorks/viewhiddenWorksRecord?menuDes='+menuDes,data,function(){
							$("#hiddenWorksRecordForm").toggleEditState(true);
							if($("#drawName").val()){
   					   		 $(".hasVal").removeClass("hidden");
   					   		 $(".noVal").addClass("hidden");
   					   		 $(".noVal+#filePreviews tr").remove();
   					   	 }else{
   					   		 $(".noVal").removeClass("hidden");
   					   		 
   					   		 $(".hasVal").addClass("hidden");
   					   	 }
							$(".editbtn").removeClass("hidden");
	        				hiddenWorks2();
						});
        				
        			}else{
        				$("#hiddenWorksRecordForm").formReset();
        				$("#hiddenWorksRecordForm").toggleEditState(false);
        				$(".editbtn").addClass("hidden");
        				$("body").cgetPopup({
        			       	title: "提示信息",
        			       	content: "请选择报验单信息!",
        			       	accept: successBack,
        			       	chide: true,
        			       	icon: "fa-check-circle",
        			       	newpop: 'new'
        			    });
        		    	return false;
        				/*$('#hiddenWorksRecordForm input,#hiddenWorksRecordForm textarea').val('');
        				hiddenWorks2();*/
        			}
        		}
        	});
        }
    };
}();

var successBack=function(){
	//返回列表区
	//$('ul.nav-tabs>li.active').removeClass("active");
	$('#listTab').tab("show");
};