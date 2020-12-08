var mytable, preservativeInpectTable,checkListData={},pipeLineData={};
var projId=$('#projId').val();
checkListData.projId=projId;
$("input").attr("required",true);
$("textarea").attr("required",true);  //页面上所有的input、textarea框必填
$("#suName").attr("required",false);  //因为有的部门没有监理公司，所以不用必填，清除必填
/**
 * 初始化信息，列表区列表
 */
var handlePreservativeInpect = function() {
	"use strict";
	preservativeInpectTable= $('#preservativeInpectTable').on( 'init.dt',function(){
	$(this).bindDTSelected(trSelectedBack,true);
	$('#preservativeInpectTable_filter input').attr('placeholder','施工工序');
	//隐藏遮罩
	$("#preservativeInpectTable").hideMask();
	addMonitor();
	updateMonitor();
	//完成监听
	completeMonitor();
	//查询监听
	searchMonitor();
	//删除监听
	deleteMonitor();
	//推送监听
	send();
	//重置监听
	resetMonitor();
	//queryCheckRole();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [ 15 ],
        dom: 'Bfrtip',
       //  dom: 'Bfrt',
        buttons: [
            { text: '推送', className: 'btn-sm btn-query  business-tool-btn checkRole  sendBtns' },
            { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
            { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' },
            { text: '删除', className: 'btn-sm btn-warn business-tool-btn checkRole hidden deleteBtn' },
            { text: '重置', className: 'btn-sm btn-warn business-tool-btn checkRole hidden resetBtn' },
            // { text: '完成', className: 'btn-sm btn-query business-tool-btn checkRole hidden completebtn' },
        ],
        serverSide: true, 
        ajax: {
            url: 'preservativeInpect/queryCheckList',
            type:'post',
            data: function(d){
               	$.each(checkListData,function(i,k){
               		d[i] = k;
               	});
               	
            },
            datasrc: 'data'
        },
       // ajax:'projectjs/inspection/json/preservative_inpect.json',
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
			{"data":"inspectionResult"},
			{"data":"flagDes"},
            {"data":"flag"},
		],
		order: [[ 1, "asc" ]],
		columnDefs: [{
			"targets":0,
			"visible":false
		},{
            "targets":5,
            className:"none never",
            "createdCell": function (td, cellData, rowData, row, col) {
                if(cellData=='3'){
                    $(td).parent().children().css("background", "rgb(255, 219, 219)");
                    //$(td).attr("id",row);
                }
            }
		}]
    });
};

/**
 * 列表区增加监听
 */
var addMonitor = function(){
	$(".addBtn").on("click",function(){
		$("#signTab").tab("show");
		$('.update-show').removeClass('hidden');
        //根据职务过滤可编辑项
        getSignStatusByPostId(getProjectInfo().post,"preservativeInpectForm");
		// $("#preservativeInpectForm").toggleEditState(true);
		//清空要增加的数据项
        $('.addClear,#flag,#finishedDate,#finishedOpr,#resetDate,#resetReason,#isFinishSign').val('');
        $('#inspectionDate').val(getSysDate());
        console.info($('#inspectionDate').val());
		//清空签字
		$('.clear-sign').click();
	});
}
/**
 * 列表区修改监听
 */
function updateMonitor(){
	$(".updateBtn").on("click",function(){
		var len=$("#preservativeInpectTable").find("tr.selected").length;
		if(len>0){
            //根据职务过滤可编辑项
			var data=$("#preservativeInpectForm").serializeJson();
			console.info("falg----"+data.flag);
        	if("2"==data.flag || "1"==data.flag ){
        		//审核中的 和已审核过的 不允许再修改
        		alertInfo("请选择未完成的报验单！");
        		return ;
        	}else{
        		getSignStatusByPostId(getProjectInfo().post,"preservativeInpectForm");
    			// $('#preservativeInpectForm').toggleEditState(true);
    			$(".update-show").removeClass("hidden");
    			$("#signTab").tab("show");
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
/**
 * 报验列表删除按钮监听
 */
var deleteMonitor=function(){
	  $(".deleteBtn").on("click",function(){
		  var len=$("#preservativeInpectTable").find("tr.selected").length;
			if(len>0){
			  $("body").cgetPopup({
					title: '提示',
					content: '点击删除后，该报验记录不可恢复，您确定要删除该报验吗？',
				    accept: delDone   //点击确认以后执行删除函数
			  });
			}else{
				alertInfo("请先选择要删除的报验记录！");
			}
	  })
};
//删除函数
var delDone = function(){
	var pcDesId= $("#pcDesId").val();
	var param = {};  //参数
	$("#preservativeInpectTable").loadMask("删除中···",1,0.5);
	var json = trSData.preservativeInpectTable.json;
	param.pcId = json.pcId;
	$.ajax({
		type:'post',
		url:'electrodeRecord/deleteList/'+pcDesId,
		data:param,
		success:function(data){
		var content = "数据删除成功！";
			if(data  == 'true'){
			    $('#preservativeInpectTable').cgetData(true,weldLineInpectCallBack);  //列表重新加载	
			    $("#preservativeInpectTable").hideMask();
			}else if (data == 'false'){
				  content = "数据删除失败！";
			}
			var myoptions = {
                   	title: "提示信息",
                   	content: content,
                   	accept: popClose,
                   	chide: true,
                   	icon: "fa-check-circle",
                   	newpop:'new'
               }
        $("body").cgetPopup(myoptions);
		if(data == 'true'){
			queryBack();   //回调函数
		}
		}
	});
	
}
var queryBack=function(){   //执行函数以后的回调方法，加载列表区和报表
	$("#preservativeInpectTable").hideMask();
	showReport();	
}

//推送
var send = function () {
    //推送事件sendBtn
    $(".sendBtns").off("click").on("click",function(){
        var len=$("#preservativeInpectTable").find("tr.selected").length;
        console.log(len);
        if(len>0){
        	var data=$("#preservativeInpectForm").serializeJson();
        	if("1"==data.flag || "2"==data.flag){
        		alertInfo("请选择未推送的报验单推送！");
        		return ;
        	}else{
        		$("body").cgetPopup({
                    title: '提示',
                    content: '你确定推送该报验记录吗？',
                    accept: completeBack
                });
        	}
        }else{
            alertInfo("请先选择要推送的记录！");
        }
    })
}
//完成事件
var completeMonitor = function(){
	//完成事件
	  $(".completebtn").on("click",function(){
		  var len=$("#preservativeInpectTable").find("tr.selected").length;
			if(len>0){
				var data=$("#preservativeInpectForm").serializeJson();
                if("1"==data.flag || "2"==data.flag){
                    alertInfo("请选择未推送的报验单推送！");
                    return ;
                }
                $("body").cgetPopup({
                    title: '提示',
                    content: '你确定推送该报验记录吗？',
                    accept: completeBack
                });
			}else{
				alertInfo("请先选择要完成的记录！");
			}
	  })
}

var completeBack = function(){
       $('#flag').val(1);
	   var dataJson=$("#preservativeInpectForm").serializeJson();
	 	var data={};
	 	data.pcId = dataJson.pcId;
	 	data.flag = 1;
		$.ajax({
             type: 'POST',
             url:'preservativeInpect/completed',
             contentType: "application/json;charset=UTF-8",
             data: JSON.stringify(data),
             success: function (data) {
	               	var content = "数据保存成功！";
	               	if(data === "false") {
                        content = "数据保存失败！";
                    }else if(data === "0"){
						//未完成签字
						$('#flag').val(0);
						content = "未完成签字，不允许推送！请检查记录区和签字区是否完成签字！";
                    }else{
	               		$('#preservativeInpectTable').cgetData(true,weldLineInpectCallBack);  //列表重新加载
	               	}
	               	var myoptions = {
	                       	title: "提示信息",
	                       	content: content,
	                       	accept: popClose,
	                       	chide: true,
	                       	icon: "fa-check-circle",
	                       	newpop:'new'
	                   }
            
             
                 $("body").cgetPopup(myoptions);
             },
             error: function (jqXHR, textStatus, errorThrown) {
                 console.warn("信息保存失败！");
             }
         });
}
//重置事件
var resetMonitor = function(){
	  $(".resetBtn").on("click",function(){
		  var len=$("#preservativeInpectTable").find("tr.selected").length;
			if(len>0){
                $('body').cgetPopup({
                    title:"提示",
                    content: '#inspectionData/inspectionResetPopPage',
                    accept:resetBack
                });
			}else{
				alertInfo("请先选择要重置的记录！");
			}
	  })
}

var resetBack = function(){
	  $('#flag').val(0);
	  $("#preservativeInpectTable").loadMask("重置中···",1,0.5);
	   var dataJson=$("#preservativeInpectForm").serializeJson();
	 	var data={};
	 	data.pcId = dataJson.pcId;
	 	data.flag = 0;
    	data.resetReason = $("#resetReason").val();
		$.ajax({
             type: 'POST',
             url:'checkList/resetCheck',
             contentType: "application/json;charset=UTF-8",
             data: JSON.stringify(data),
             success: function (data) {
	               	var content = "数据保存成功！";
	               	if(data === "false"){
	               	   content = "数据保存失败！";
	               	}else{
	               		$("#preservativeInpectTable").hideMask();
	               		$('#preservativeInpectTable').cgetData(true,weldLineInpectCallBack);  //列表重新加载
	               	}
	               	var myoptions = {
	                       	title: "提示信息",
	                       	content: content,
	                       	accept: popClose,
	                       	chide: true,
	                       	icon: "fa-check-circle",
	                       	newpop:'new'
	                   }
            
             
                 $("body").cgetPopup(myoptions);
             },
             error: function (jqXHR, textStatus, errorThrown) {
                 console.warn("信息保存失败！");
             }
         });
}

/**
 * 搜索监听
 */
var searchMonitor = function(){
	//基础条件查询添加监听
	$('#preservativeInpectTable_filter input').on('change',function(){
		//var process = $('#altimetricTable_filter input').val();
		var process = $('#preservativeInpectTable_filter input').val();
		var pcDesId=$('#pcDesId').val();
		var projId=$('#projId').val();
		checkListData = {};
		checkListData={'process':process,'pcDesId':pcDesId,'projId':projId};
		$('#preservativeInpectTable').cgetData(true,weldLineInpectCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#preservativeInpectTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

//列表区行点击
var trSelectedBack = function(v, i, index, t, json){
    $("#businessOrderId").val(json.pcId);
	t.cgetDetail("preservativeInpectForm",'preservativeInpect/viewPreservativeInpect','',queryBack);
	$("#preservativeInpectForm").toggleEditState(false);
	$(".editbtn").addClass("hidden");
}
//报验单和防腐检查一一对应
var queryBack=function(data){
	$('#pcIdNew').val(data.pcId);
	$('#pcId').val(data.pcId);
    $("#insDate").val(data.inspectionDate);
    $("#status").val(data.flag);
    $("#sendDate").val(data.finishedDate);
	//反序列化防腐记录信息
	var preservativeInpect = data.preservativeInpect;
	f=$("#preservativeInpectForm");
	 var selects = f.find('select[disabled],  [type="radio"][disabled]');
     selects.removeAttr("disabled");
	var total = jQuery.extend({}, trSData.json, preservativeInpect);
    f.deserialize(total);
    f.initFixedNumber();
  //有disabled属性的下拉菜单元素对象重新添加禁用属性
    selects.attr("disabled","disabled");
    
    if(preservativeInpect.preservativeType=='0'){
    	$(".jointType").removeClass("hidden");
		$('.paintType').addClass("hidden");
    }else{
    	$(".jointType").addClass("hidden");
		$('.paintType').removeClass("hidden");
    }
    
    $("#flag1").val(data.flag);
    
    var post=$("#loginPost").val();  //当前登录人职务
	console.info("post-"+post);
	var builder=$("#builderPost").val();
	console.info("builder-"+builder);
	var sujgj=$("#sujgjPost").val();
	console.info("sujgj-"+sujgj);
	//已完成的报验
	if(data.flag==1){
		$(".updateBtn,.completebtn").addClass("hidden");
	}else{
		$(".updateBtn,.completebtn").removeClass("hidden");
		//if(post==builder||post==sujgj){
		//if(post.indexOf(builder)>=0||post.indexOf(sujgj)>=0){	
		if(post.indexOf(builder)>=0 || post.indexOf(',999,')>=0){	//如果职务是现场代表和管理员完成按钮可用
			$(".completebtn").attr("disabled",false);
			console.info("1-"+$("#loginPost").val().indexOf($("#builderPost").val()));
			console.info("1-"+$("#loginPost").val().indexOf($("#sujgjPost").val()));
			completeMonitor();
		}else{
			$(".completebtn").attr("disabled",true);//不可点击
			$(".completebtn").off();
		}
	}

	if(post.indexOf(',999,')>=0 || post.indexOf(',93,')>=0){   //如果是管理员和数据管理员，显示删除按钮，否则隐藏删除按钮
		$(".deleteBtn").removeClass("hidden");
		if(data.flag==1){     //如果记录是完成状态，显示重置按钮，否则不显示重置按钮但显示完成按钮
			$(".resetBtn").removeClass("hidden");	
		}else{
			$(".completebtn").removeClass("hidden"); 
			$(".resetBtn").addClass("hidden");	 
		}	
	}else{
		$(".deleteBtn").addClass("hidden");	
	}
	
	showReport();
}
//列表重新加载
var weldLineInpectCallBack =function(){
	var len = $('#preservativeInpectTable').DataTable().ajax.json().data ? $('#preservativeInpectTable').DataTable().ajax.json().data.length : $('#preservativeInpectTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.addClear').val('');
		$('#pcIdNew').val('');
		$(".updateBtn,.completebtn").addClass("hidden");
	 }else{
		 var data = trSData.preservativeInpectTable.json;
		//已完成的报验
		if(data.flag==1){
			$(".updateBtn,.completebtn").addClass("hidden");
		}else{
			$(".updateBtn,.completebtn").removeClass("hidden");
		}
	 }
	//清空签字
	$(".clear-sign").click();
	showReport();
}
/**
 * 页面初始化
 */
var preservativeInpect = function(){
	"use strict";
    return {
        //main function
        init: function () {
        	handlePreservativeInpect();
        	showReport();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab, #auditTab, #signTab").off("shown.bs.tab").on("shown.bs.tab",function(){
        		
        		if($(this).is($("#listTab,#signTab"))){
    				if($(this).is($('#listTab'))){
    					if(!$.fn.DataTable.isDataTable('#preservativeInpectTable')){
    						//初始化列表
    						handlePreservativeInpect();
            			}else{
            				$('#preservativeInpectTable').cgetData(true,weldLineInpectCallBack);
            				$(".update-show").addClass("hidden");
            				$('#preservativeInpectForm').toggleEditState(false);
            				showReport();
            			}
    				}else{
    					if(trSData.preservativeInpectTable.json && $('#pcId').val()!=""){
    						$('.preservativeType').toggleEditState(false);
    						showReport();
        				}else{
        					// $('.addClear').val('');
        					//清空签字
        					$('.clear-sign').click();
        					showReport();
        				}
    					console.info($('#pcId').val()+"-----------pcId");
    					setTimeout(function(){
        				    $("#projectImagesList").getImagesList({
        				    	"projId": getProjectInfo().projId,
        				    	"step": getStepId(),
        				    	"projNo": getProjectInfo().projNo,
        				    	"busRecordId": $("#pcId").val() || '-1'
        				    });
        				},300);
    				}
        		}else if($(this).is($("#auditTab"))){
        			
        		}
        	});
        }
    };
}();



//签字区放弃功能
$(".giveupbtn").on("click",function(){
	if($("#pcId").val()==""){
		//返回列表区
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
		$('#preservativeInpectTable').cgetData(true,weldLineInpectCallBack);
	}else{
		//返回列表区
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
	}
});


