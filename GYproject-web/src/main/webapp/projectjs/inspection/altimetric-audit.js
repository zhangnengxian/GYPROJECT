
var mytable, altimetable,checkListData={},altimeData={};
var projId=$('#projId').val();
checkListData.projId=projId;
/**
 * 初始化信息，记录区高程测量列表
 */
var handleAltiMetric = function() {
	"use strict";
    if($('#pcIdNew').val()!==""){
	    altimeData.pcId=$('#pcIdNew').val();
	}else{
		altimeData.pcId=-1;
	}
    if ($('#altimetricSurveyAuditTable').length !== 0) {
    	mytable= $('#altimetricSurveyAuditTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#altimetricSurveyAuditTable').hideMask();
   	    	//加载cpt文件
   			showReport2();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
            ],
            //serverSide:true,
            ajax: {
                url: 'altimetricSurvey/queryAltimSurvey',
                type:'post',
                data: function(d){
                   	$.each(altimeData,function(i,k){
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
            select: true,  //支持多选
            columns: [
	  			{"data":"pcStation",className:"text-right"}, 
				{"data":"pcOldElevation",className:"text-right"},
				{"data":"pcNewElevation",className:"text-right"},
				{"data":"pcFootElevation",className:"text-right"},
				{"data":"pcDepth",className:"text-right"}
			],
			columnDefs: [{
				
			}],
			ordering:false
        });
    }
};

/**切换到报验信息*/
$("#materialTab").on("shown.bs.tab",function(){
    materialTableInit();
});
/**页面初始化信息*/
var altimetricSurveyAndAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
			handlealtimetric();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab, #auditTab, #signTab").off("shown.bs.tab").on("shown.bs.tab",function(){
        		/*//选中列表区和报验区
        		if($(this).is($("#listTab, #signTab"))){
        			if($(this).is($("#signTab")) ){
        				//增加
        			  if($("#pcId").val() === ""){
        				    if(!trSData.checkListTable.json&&!$("#addShow").val()==true){
	          				    $(".update-show").hide();
	          				    $("#addShow").val(false);
	          				    showReport1();
	          				}else{
		        				$(".update-show").show();
		        				$("#altimetricSurveyForm").toggleEditState(true).styleFit();
		        				$("#updateShow").val('false');
		        				//$("#pcId").val(trSData.altimetricTable.json.pcId);
		        				//清空页面中元素值
		        				$("#altimetricSurveyForm").formReset("#projName","#suName","#constructionUnit");
		        				//清空签字内容
		        				$(".clear-sign").click();
		        				//增加时添加施工计划中默认项
		        				$("#addShow").val(false);
	        				}
        				    showReport1();
        			}else{
        				//修改
        				if($("#updateShow").val()=='true'){
        					$(".update-show").show();
        					trSData.altimetricTable.t.cgetDetail("altimetricSurveyForm");
        					$("#altimetricSurveyForm").toggleEditState(true).styleFit();
        					$("#updateShow").val('false');
        					$('.update-show').removeClass('hidden');
        				}else{
        					//直接点击tab
        					$(".update-show").hide();
        					trSData.altimetricTable.t.cgetDetail("altimetricSurveyForm");
        					$("#altimetricSurveyForm").toggleEditState(false).styleFit();
        				}
        				showReport1();
        			}
        		}else{
        			showReport1();
        		}
        		}else{
        			if(!$.fn.DataTable.isDataTable('#altimetricSurveyAuditTable')){
        				handleAltiMetric();
        			}else{
        				altimeData.pcId=trSData.altimetricTable.json.pcId;
        				mytable.ajax.reload(showReport2);
        			}
        		}*/
        		if($(this).is($("#listTab,#signTab"))){
    				if($(this).is($('#listTab'))){
    					if(!$.fn.DataTable.isDataTable('#altimetricTable')){
    						//初始化列表
    						handlealtimetric();
            			}else{
            				$('#altimetricTable').cgetData(true);
            				$(".update-show").addClass("hidden");
            				$('#altimetricSurveyForm').toggleEditState(false);
            				showReport1();
            			}
    				}else{
    					if(trSData.altimetricTable.json && $('#pcId').val()!=""){
        					showReport1();
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
        		}else if($(this).is($("#auditTab"))){
        			if(!$.fn.DataTable.isDataTable('#altimetricSurveyAuditTable')){
        				//初始化记录列表
        				handleAltiMetric();
        			}else{
        				if($("#pcIdNew").val()!==''){
        					 altimeData.pcId=$("#pcIdNew").val();
	           				 $('#altimetricSurveyAuditTable').cgetData();
	           				 showReport2();
          				}else{
          					 altimeData.pcId=-1;
	           				 $('#altimetricSurveyAuditTable').cgetData();
          					 showReport2();
          				}
        			}
        		}
        	});
        }
    };
}();
/**查询施工计划*/
/*function queryPlan(){
	var response=$.ajax({
        url: "altimetricSurvey/queryConstructPlan?projNo="+1,
        type: "post",
        dataType: 'json',
        success: function (data) {
            if(null!=data){
            	$("#projName").val(data.projName);
            	$("#constructionUnit").val(data.managementOffice);
            	$("#suName").val(data.suName);           	
            	showReport1();
            }                 
    }
    
    }
    );
}*/
/**
 * 初始化信息，列表区报验单列表
 */
var handlealtimetric = function() {
	"use strict";
	altimetable= $('#altimetricTable').on( 'init.dt',function(){
	$(this).bindDTSelected(trSelectedBack,true);
	$('#altimetricTable_filter input').attr('placeholder','施工工序');
	showReport1();
	updateCheck();
	//隐藏遮罩
	$("#altimetricTable").hideMask();
	addProCheck();
	//查询监听
	searchMonitor();
	queryCheckRole();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [ 15 ],
        dom: 'Bfrtip',
        buttons: [
            { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
            { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' },
        ],
        serverSide: true, 
        ajax: {
            url: 'altimetricSurvey/queryCheckList',
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
};

//查询监听
var  searchMonitor=function(){
	//基础条件查询添加监听
	$('#altimetricTable_filter input').on('change',function(){
		var process = $('#altimetricTable_filter input').val();
		var pcDesId=$('#pcDesId').val();
		var projId=$('#projId').val();
		checkListData = {};
		checkListData={'process':process,'pcDesId':pcDesId,'projId':projId};
		$('#altimetricTable').cgetData(true,altimetricCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#altimetricTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}
var altimetricCallBack=function(){
	var len = $('#altimetricTable').DataTable().ajax.json().data ? $('#altimetricTable').DataTable().ajax.json().data.length : $('#altimetricTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.addClear').val('');
		$('#pcIdNew').val('');
	 }
	//清空签字
	$(".clear-sign").click();
	showReport1();
}

//列表区增加按钮监听事件
function addProCheck(){
	$(".addBtn").on("click",function(){
		$("#signTab").tab("show");
		$('.update-show').removeClass('hidden');
		$("#altimetricSurveyForm").toggleEditState(true);
		//清空要增加的数据项
		$('.addClear').val('');
		//清空签字
		$('.clear-sign').click();
		$('#constructionUnit').val(getProjectInfo().cuName);
	});
}



//报验单列表点击行事件
var trSelectedBack=function(v, i, index, t, json){
	//$("#pcId").val(json.pcId);
	$("#pcIdNew").val(json.pcId);
	t.cgetDetail("altimetricSurveyForm",'altimetricSurvey/viewAltimetric','',queryBack);
}

var queryBack=function(){
	showReport1();
}
//报验单修改
function updateCheck(){
	$(".updateBtn").on("click",function(){
		var len=$("#altimetricTable").find("tr.selected").length;
		if(len>0){
			$('#altimetricSurveyForm').toggleEditState(true);
			$(".update-show").removeClass("hidden");
			$("#signTab").tab("show");	
			
			
			//施工或竣工中
        	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#altimetricSurveyForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#altimetricSurveyForm").find(".sign-data-input").toggleSign(false);
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




//记录区添加页面记录
var rows=[];
var rowData=[];
$(".altimeAddbtn").on("click",function(){
	var rowsPart=[];
	var t=$("#altimetricSurveyForm2");
	//判断报验单是否存在
	if($('#pcIdNew').val()!==""){
	  //开启表单验证
		if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {   
			//添加管道类型-中压/低压
			var val=$('#pressureType').val();
		    if(val=="1"){
			    val='D';
		    }else if(val=='2'){
			    val='Z'
		    }else{
		    	$("body").cgetPopup({
		    		title: "提示信息",
                	content: "选择管道压力类型!",
                	accept: pressureChoose,
                	chide: true,
                	icon: "fa-check-circle",
                	newpop: 'new'
                }); 
		    	return false;
		    }
		    //添加桩号
		    var id=$('#pcIdNew').val();
			var json1=t.serializeJson();
			var num=json1.pcStationEnd-json1.pcStationStart;
			for(var i=0;i<num+1;i++){
				var json=t.serializeJson();
				var numStart=json.pcStationStart;  
				json.pcId=id;
				json.pcStation=val+(parseInt(numStart)+i);
				rowsPart.push(json);
			}
			//判断桩号是否重复
			var json=$("#altimetricSurveyAuditTable").DataTable().rows().data();
			for(var i=0;i<json.length;i++){
				for(var j=0;j<rowsPart.length;j++){
					if(json[i].pcStation==rowsPart[j].pcStation){
						$("body").cgetPopup({
							title: "提示信息",
		                  	content: "桩号重复!",
		                  	accept: pcStationRepeat,
		                  	chide: true,
		                  	icon: "fa-check-circle",
		                  	newpop: 'new'
		                }); 
						return false;
				    }
				}
			}
			//列表添加
		    mytable.rows.add(rowsPart).draw();
		    $('#altimetricSurveyAuditTable').selectRow(0);
		    /*rows=$.merge(rows, rowsPart);
		    rowData=rows;*/
	        //如果通过验证, 则移除验证UI
	        t.parsley().validate();
	    } else {
	        //如果未通过验证, 则加载验证UI
	        t.parsley().validate();
	    };
	}else{
		$("body").cgetPopup({
			title: "提示信息",
	       	content: "报验单不存在，不允许添加记录!",
	       	accept: inexistence,
	       	chide: true,
	       	icon: "fa-check-circle",
	       	newpop: 'new'
	    });
		return false;
	}
});
var pressureChoose=function(){};
var pcStationRepeat=function(){};
var inexistence=function(){};
var inexistence=function(){};

//记录区保存功能
$(".altimeSavebtn").on("click",function(){
	var dataObj={};
	//列表的值
	var json=$("#altimetricSurveyAuditTable").DataTable().rows().data();
	dataObj.list = [];
	//转成list
	$.each(json, function(k,v){
		dataObj.list.push(v);
	})
	var id=$('#pcIdNew').val();
	dataObj.pcId=id;
	var data=JSON.stringify(dataObj);
	dataObj.list = [];//清空了；
	    
	    
	if(response){
		response.abort();
    }
    var response = $.ajax({
    	url: "altimetricSurvey/saveAltimSurvey",
    	type: "POST",
    	timeout : 5000,
    	contentType: "application/json;charset=UTF-8",
    	data: data,
    	success: function (data) {
    		if (data === "true") {
    			
    			$("body").cgetPopup({
    				title: "提示信息",
                  	content: "数据保存成功!",
                  	accept: successBack,
                  	chide: true,
                  	icon: "fa-check-circle",
                  	newpop: 'new'
                 }); 
	        }else{
	        	$("body").cgetPopup({
                  	title: "提示信息",
                  	content: "数据保存失败, 请重试! <br>" + data,
                  	accept: popClose,
                  	chide: true,
                  	icon: "fa-exclamation-circle",
                  	newpop: 'new'
                });  
	        }
    	}
    });
});

/**记录区保存回调*/
function successBack(){
	showReport2();
}

//报验区放弃功能
$(".giveupbtn").on("click",function(){
	if($("#pcId").val()==""){
		//返回列表区
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
		$('#altimetricTable').cgetData(altimetricCallBack);
	}else{
		//返回列表区
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
	}
});

//记录区删除按钮
$(".deleteBtn").on("click",function(){
	 var len=$('#altimetricSurveyAuditTable').find('tr.selected').length;
	 if(len>0){
		 var rows = $("#altimetricSurveyAuditTable").DataTable().rows( '.selected' ).remove().draw();
		 $('#altimetricSurveyAuditTable').selectRow(0);
	 }else{
		 $("body").cgetPopup({
				title: '提示信息',
				content: '请选择要删除的记录!',
				accept: delData,
				icon: 'fa-exclamation-circle',
		  });
	 }
});

function delData(){	}


