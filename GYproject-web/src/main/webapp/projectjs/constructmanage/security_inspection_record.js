/**查询条件*/
var searchData={},
	recordData={},
	treeArr=[],
	securityInspectionAuditTable,
    rowData=[],
    isAddRow = 0,
    ilIdBack,
	savestus=0;
	searchData.projId = getProjectInfo().projId;
var handleSecurityInspection = function() {
	"use strict";
    if ($('#securityInspectionTable').length !== 0) {
    	$('#securityInspectionTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//隐藏遮罩
   			$('#securityInspectionTable').hideMask();
   			//增加按钮显示
   			addBtn();
   			//增加监听
   			addMonitor();
   			//修改监听
   	    	modifyMonitor();
   	    	//弹屏查询
   	    	searchPop();
   	    	//queryCheckRole();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '增加', className: 'btn-sm btn-query checkRole addBtn' },
                { text: '修改', className: 'btn-sm btn-query checkRole updateBtn business-tool-btn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'securityInspection/queryInspectionList',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            
            //ajax: 'projectjs/constructmanage/json/security_inspection_record.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
      			{"data":"ilId",className:"none never"},
				{"data":"checkDate"},
				{"data":"total"},
				{"data":"inspector"}
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},{
				"targets":3,
				"render":function(data,type,row,meta){
					if(type === "display"){
						var tdcon = '<img alt="" src="'+data+'" style="height: 30px">';
						return tdcon;
					}else{
						return data;
					}
				}
			}],
        });
        
    }
}
/**
 * 根据单位隐藏按钮
 */
function addBtn(){
	if($("#addBtnHide").val()){
		$(".addBtn,.addBtnClass").addClass("hidden");
	}
}
/**
 * 弹屏监听方法
 */
var searchPop=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#securityInspection/inspectionRecordSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
}
/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_inspection").serializeJson();
	searchData.projId = getProjectInfo().projId;
	//列表重新加载
    $("#securityInspectionTable").cgetData(true,securityInspectionCallBack);  
}
var callback = function(){}
//重新加载列表回调方法
var securityInspectionCallBack=function(){
	var len = $('#securityInspectionTable').DataTable().data().length;
	if(len<1){
		 //清空右侧记录
		 $('#securityInspectionForm')[0].reset();
		 $(".clear-sign").click();
		 $(':input','#securityInspectionForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
	showReport();
}
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	t.cgetDetail('securityInspectionForm','securityInspection/viewInspectionRecord','',queryBack);
	//传false表示不可编辑
	//$("#securityInspectionForm").toggleEditState(false);
	$(".editbtn").addClass("hidden");
	
}

var queryBack=function() {
	$("#cuName").val(getProjectInfo().cuName);
	showReport();
}
//增加按钮绑定监听事件
var addMonitor=function(){
	$(".addBtn").off("click").on("click",function(){
		isAddRow = 1;
		savestus = 1;
		$('#securityCheckRecord').tab('show');
		//$('#securityInspectionForm').toggleEditState(true);
		getSignStatusByPostId(getProjectInfo().post,"securityInspectionForm");
		$(".editbtn").removeClass("hidden");
		$("#projId").val(getProjectInfo().projId);
	    $("#projName").val(getProjectInfo().projName);
	    $("#projNo").val(getProjectInfo().projNo);
	    $("#projAddr").val(getProjectInfo().projAddr);
	    $("#cuName").val(getProjectInfo().cuName);
	    $("#checkDepartment").val($("#deptName").val());
	    $("#projScaleDes").val(getProjectInfo().projScaleDes);
		$('#ilId').val('');
		$('#checkDate').val('');
		$('#total').val('0');
		$('#remark').val('');
		$('#fieldPrincipal').val('');
		$("#reviewOfOpinion").val('')
		$("#signBtn_1,#signBtn_2,#signBtn_3").resetSign();
		$("#reviewOfOpinion").attr('readonly',true);
		$("#signBtn_3").addClass("disabled");
		recordData = {"ilId": "#"};
		$('#securityInspectionAuditTable').DataTable().ajax.reload(function(){
		});
		//trSData.securityInspectionTable={};
		
		showReport();
	});
}
//修改监听方法
var modifyMonitor = function(){
	$(".updateBtn").on("click",function(){
		var len=$("#securityInspectionTable").find("tr.selected").length;
		if(len>0){
			
			//$('ul.nav>li').removeClass("active");
			$('#securityCheckRecord').tab("show");
			//$('#securityInspectionForm').toggleEditState(true);
			getSignStatusByPostId(getProjectInfo().post,"securityInspectionForm");
			$(".editbtn").removeClass("hidden");
			$("#cuName").val(getProjectInfo().cuName);
			if($("#addBtnHide").val()){
				$(".addBtn,.addBtnClass").addClass("hidden");
			}
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的检查!',
				ahide:true,
				atext:'确定'
			});
		}
		// 必须检查人签字完成复核人才能进行进行签字和填写复核情况
		if (($("#inspector").val()!='')) {
			$("#reviewOfOpinion").removeAttr('readonly',false);
			$("#signBtn_3").removeClass("disabled");
		} else {
			$("#reviewOfOpinion").attr('readonly',true);
			$("#signBtn_3").addClass("disabled");
		}
	});
}
//添加处罚记录
function addRecord(){
//绑定违反项目点击监听事件
$("#guifan").off("click").on("click",function(){
	var url = "#securityInspection/securityTopPage";
	//加载弹屏
	$("body").cgetPopup({
		title: '处罚条例细则',
		content: url,
		accept: addData,
		size:"large"
	});
});
}
//点击确定回调函数
function addData(){
	var rows = [],arr=[],addArr=[],removeArr=[];
	 arr=$('#jstreeSafe').jstree().get_selected();
	 addArr=$('#jstreeSafe').jstree().get_selected();	
	   for(var i=0;i<arr.length;i++){			
			if(treeArr.length>0){
				for(var j=0;j<treeArr.length;j++){
					if(treeArr[j]==arr[i]){
						treeArr.splice(j,1,'');
					    arr.splice(i,1,'');
					}
				}
			}
	   }
	   //添加行
	  if(arr.length>0){
		for(var m=0;m<arr.length;m++){
			if(arr[m]!=""){
			if(arr[m].indexOf("@@") === -1) {
				continue;
			}
			var json = {};
			json.id = arr[m];
			json.ilId = $('#ilId').val();
			json.projId=$('#projId').val();
			json.projNo=$('#projNo').val();
			arr[m] = arr[m].split("@@");
			json.saId = arr[m][0];
			json.unqualityPointId = arr[m][3];
			json.unqualityPointContent = arr[m][1];
			json.fraction = new Number(arr[m][2]);
			rows.push(json);
		}
		}
	  }
	   treeArr=addArr;	
	   //console.info(treeArr);
	   securityInspectionAuditTable.rows.add(rows).draw();
	  //rowData=rows;
	   var score = 0;
		 if($("#securityInspectionAuditTable").DataTable()!=null){
			 var json=$("#securityInspectionAuditTable").DataTable().rows().data();
			 for(var i=0;i<json.length;i++){
				 score = parseFloat(json[i].fraction)+score;
			 }
		 }
		 $("#total").val(score.toString());
}
//保存检查记录
$('.saveCustomer').off("click").on("click",function(){
    $(".saveCustomer").parent().parent().loadMask("正在保存...", 1, 0.5);
	var json=$("#securityInspectionAuditTable").DataTable().rows().data();
	var data=$("#securityInspectionForm").serializeJson();
	var resultData = [];
   	for(var i=0;i<json.length;i++){
   		var tdata = json[i];
   			resultData.push(tdata);
   	}
   	ilIdBack = data.ilId;
	data.inspectionRecords=resultData;
	
	$.ajax({
        type: 'POST',
        url: 'securityInspection/saveInspectionRecord',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(data),
        success: function (data) {
            $(".saveCustomer").parent().parent().hideMask();
        	var data=JSON.parse(data);
        	var content = "数据保存成功！";
        	if(data.result === "false"){
        		content = "数据保存失败！";
        	}else{
        		//data=JSON.parse(data);
        		$('.editbtn').addClass("hidden");
        		$('#securityInspectionTable').cgetData();
        		//$("#securityInspectionForm").cgetDetail();
        		 //console.info(data);
        		$("#securityInspectionForm").toggleEditState(false);
        		var ilId = data.objectId,
        		projNo = getProjectInfo().projNo,
        		projId = getProjectInfo().projId;
        		if(_inApk && $(".attach-images-list").find(".new-image").length){
        			preImagesUpload($(".attach-images-list .uploadBtn"), projId, projNo, getStepId(), ilId);
        		}else{
        			
        		};
        		showReport();
        	}
        	var myoptions = {
                	title: "提示信息",
                	content: content,
                	accept: savedone,
                	chide: true,
                	icon: "fa-check-circle"
            }
            $("body").cgetPopup(myoptions);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("记录保存失败！");
        }
    });
});
var savedone = function(){
	showReport();
}
//删除处罚记录
function delRecord(){
	$(".delReport").on("click",function(){
	    $("body").cgetPopup({
			title: '提示信息',
			content: '确认要删除数据吗？',
			accept: delData,
			icon: 'fa-exclamation-circle',
			size: 'small'
		});
});	
}
//点击确定回调函数	
function delData(){
	var rows = $("#securityInspectionAuditTable").DataTable().rows( '.selected' ).remove().draw();
	
	var arr=[];
	var json=$("#securityInspectionAuditTable").DataTable().rows().data();
	for(var i=0;i<json.length;i++){
		arr.push(json[i].id);
	}
	treeArr=arr;
	//console.info(treeArr);
	//rowData=rows;
	
	var score = 0;
	 if($("#securityInspectionAuditTable").DataTable()!=null){
		 var json=$("#securityInspectionAuditTable").DataTable().rows().data();
		 for(var i=0;i<json.length;i++){
			 score = parseFloat(json[i].fraction)+score;
		 }
	 }
	 $("#total").val(score.toString());
}

//违反条目列表
var securityRecord = function(){
	"use strict";
	var json=trSData.securityInspectionTable.json;
	if(json){
		recordData.ilId=trSData.securityInspectionTable.json.ilId;
	}else{
		recordData.ilId=-1;
	}
	if ($('#securityInspectionAuditTable').length !== 0) {
		securityInspectionAuditTable=$('#securityInspectionAuditTable').on( 'init.dt',function(){
    		//增加记录
			addRecord();
			//删除记录
    		delRecord();
    		delData();
   		$('#securityInspectionAuditTable').hideMask();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                
            ],
            ajax: {
                url: 'securityInspection/queryInspectionRecord',
                type:'post',
                data: function(d){
                   	$.each(recordData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/constructmanage/json/inspection_record.json',
            select: true,  //支持多选
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            columns: [
                {"data":"id",className:"none never"},
                {"data":"saId",className:"none never"},
                {"data":"ilId",className:"none never"},
                {"data":"projId",className:"none never"},
                {"data":"projNo",className:"none never"},
      			{"data":"unqualityPointId"},
				{"data":"unqualityPointContent"},
				{"data":"fraction"}
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},
			{
				"targets": 1,
				"visible":false
			},
			{
				"targets": 2,
				"visible":false
			},
			{
				"targets": 3,
				"visible":false
			},
			{
				"targets": 4,
				"visible":false
			},{
				"targets":6,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}],
            ordering:false
        });
	}
	delData();
}

$('#securityCheckList').on('shown.bs.tab',function(){
	$(".editbtn").addClass("hidden");
	$('#securityInspectionTable').cgetData();
});
$('#securityCheckRecord').on('shown.bs.tab',function(){
	setTimeout(function(){
	    $("#projectImagesList").getImagesList({
	    	"projId": getProjectInfo().projId,
	    	"step": getStepId(),
	    	"projNo": getProjectInfo().projNo,
	    	"busRecordId": $("#ilId").val() || '-1'
	    });
	},300);
});
var securityInspection = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        	
        	handleSecurityInspection();
        	if(!$.fn.DataTable.isDataTable('#securityInspectionAuditTable')){
				//trSelectedBack();
				securityRecord();
				}
        	$('[href="#inspection_record"]').on("show.bs.tab", function(){
        	    $("#securityInspectionForm").styleFit();
        	    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6").handleSignature();
        	    setTimeout(function(){
        		    $("#projectImagesList").getImagesList({
        		    	"projId": getProjectInfo().projId,
        		    	"step": getStepId(),
        		    	"projNo": getProjectInfo().projNo,
        		    	"busRecordId": $("#ilId").val() || '-1'
        		    });
        		},300);
        	});
        	$("#securityCheckList,#securityCheckRecord").on("shown.bs.tab",function(){
        		if($(this).is($("#securityCheckList"))){
    				if(!$.fn.DataTable.isDataTable('#securityInspectionTable')){
    					handleSecurityInspection();
        			}else{
        				showReport();
        			}
        		}else{
        			 if(isAddRow){
        				 isAddRow = 0;
        			 }else{
             			if(trSData.securityInspectionTable.json){
        					recordData.ilId=trSData.securityInspectionTable.json.ilId;
        				}else{
        					recordData.ilId="#";
        				}
        				$('#securityInspectionAuditTable').cgetData(false,delData);
        				//$('#securityInspectionForm').deserialize(trSData.securityInspectionTable.json);
        				//传false表示不可编辑
        				//$("#securityInspectionForm").toggleEditState(false);
        				showReport();
        			}
        		}
        	});
        }
        )}
    };
}();