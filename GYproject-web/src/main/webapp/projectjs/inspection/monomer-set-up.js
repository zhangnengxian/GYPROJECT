
var mytable, altimetable,checkListData={},altimeData={};
var projId=$('#projId').val();
checkListData.projId=projId;
/**
 * 初始化信息，记录区单体调校列表
 */
var handleAltiMetric = function() {
	"use strict";
    if($('#pcIdNew').val()!==""){
	    altimeData.pcId=$('#pcIdNew').val();
	}else{
		altimeData.pcId=-1;
	}
    if ($('#monomerSetUpRecordTable').length !== 0) {
    	mytable= $('#monomerSetUpRecordTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#monomerSetUpRecordTable').hideMask();
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
                url: 'monomerSetUp/queryMonomerSetUpSurvey',
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
	  			{"data":"monomerDes"},
				{"data":"result"}
			],
			columnDefs: [{
					targets: 1,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === "1"){
								data = "/";
							}else if(data === "2"){
								data = "符合设计规定";
							}else if(data === "3"){
								data = "正常";
							}
							return data;
						}else{
							return data;
						}}
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
        		//选中列表区和报验区
        		if($(this).is($("#listTab,#signTab"))){
    				if($(this).is($('#listTab'))){
    					if(!$.fn.DataTable.isDataTable('#altimetricTable')){
    						//初始化列表
    						handlealtimetric();
            			}else{
            				$('#altimetricTable').cgetData(true);
            				$(".update-show").addClass("hidden");
            				$('#monomerSetUpForm').toggleEditState(false);
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
        			if(!$.fn.DataTable.isDataTable('#monomerSetUpRecordTable')){
        				//初始化记录列表
        				handleAltiMetric();
        			}else{
        				if($("#pcIdNew").val()!==''){
        					 altimeData.pcId=$("#pcIdNew").val();
	           				 $('#monomerSetUpRecordTable').cgetData();
	           				 showReport2();
          				}else{
          					 altimeData.pcId=-1;
	           				 $('#monomerSetUpRecordTable').cgetData();
          					 showReport2();
          				}
        			}
        		}
        	});
        }
    };
}();
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
            url: 'monomerSetUp/queryCheckList',
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
		$("#monomerSetUpForm").toggleEditState(true);
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
	t.cgetDetail("monomerSetUpForm",'monomerSetUp/viewAltimetric','',queryBack);
}

var queryBack=function(){
	showReport1();
}
//报验单修改
function updateCheck(){
	$(".updateBtn").on("click",function(){
		var len=$("#altimetricTable").find("tr.selected").length;
		if(len>0){
			$('#monomerSetUpForm').toggleEditState(true);
			$(".update-show").removeClass("hidden");
			$("#signTab").tab("show");
			//施工或竣工中
        	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#monomerSetUpForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#monomerSetUpForm").find(".sign-data-input").toggleSign(false);
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
	var t=$("#monomerSetUpForm2");
	//判断报验单是否存在
	if($("#pcIdNew").val()!==""){
    	//开启表单验证
		if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') { 
        	//报验单id
			var id=$("#pcIdNew").val();
	   		var json=t.serializeJson();
	   		json.pcId=id;
	   		json.monomerDes=$('#monomer option:selected').text();
	   		json.monomer=$('#monomer option:selected').val();
	   		if($('#monomer option:selected').val()==="7"){
	   			json.result=$("#result2").val();
	   		}else{
	   			json.result=$('#result option:selected').val();
	   		}
	   		console.info(json);
	   		
	   		//判断重复
	   		var json1=$("#monomerSetUpRecordTable").DataTable().rows().data();
			for(var i=0;i<json1.length;i++){
				if(json1[i].monomerDes==json.monomerDes){
					$("body").cgetPopup({
		                  title: "提示信息",
		                  content: "调校项目重复!",
		                  accept: successBack1,
		                  chide: true,
		                  icon: "fa-check-circle",
		                  newpop: 'new'
		             }); 
		    		 return false;
				 }
		    }
	   		//吹扫记录表格添加一条记录
			mytable.row.add(json).draw();
    	    $('#monomerSetUpRecordTable').selectRow(0);
	        t.parsley().reset();
	    } else {
	        //如果未通过验证, 则加载验证UI
	        t.parsley().validate();
	    };
	}else{
		$("body").cgetPopup({
	       	title: "提示信息",
	       	content: "报验单不存在，不允许添加记录!",
	       	accept: successBack2,
	       	chide: true,
	       	icon: "fa-check-circle",
	       	newpop: 'new'
	    });
		return false; 
	}
});
var successBack1 = function(){}
var successBack2 = function(){}

//记录区保存功能
$(".altimeSavebtn").on("click",function(){
	var dataObj={};
	var json=$("#monomerSetUpRecordTable").DataTable().rows().data();
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
		url: "monomerSetUp/saveMonomerSetUpRecord",
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
	 var len=$('#monomerSetUpRecordTable').find('tr.selected').length;
	 if(len>0){
		 var rows = $("#monomerSetUpRecordTable").DataTable().rows( '.selected' ).remove().draw();
		 $('#monomerSetUpRecordTable').selectRow(0);
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
