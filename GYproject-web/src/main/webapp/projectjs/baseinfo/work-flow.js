
var workFlowTable,workFlowData={},workFlowRecordTable,workFlowRecordData={};
/**
 * 初始化信息，列表区流程列表
 */
var handleWorkFlowList = function() {
	"use strict";
	if ($('#workFlowTable').length !== 0) {
		workFlowTable= $('#workFlowTable').on( 'init.dt',function(){
		$(this).bindDTSelected(trCheckListBack,true);
		//隐藏遮罩
		$("#workFlowTable").hideMask();
	    }).DataTable({
	    	language: language_CN,
	        lengthMenu: [ 15 ],
	        dom: 'Brtip',
	        buttons: [
	              ],
	        //serverSide: true, 
	        ajax: {
	            url: 'workFlow/queryWorkFlowList',
	            type:'post',
	            data: function(d){
	               	$.each(workFlowData,function(i,k){
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
	            {"data":"wfId",className:"none never"},  
	            {"data":"projType",className:"none never"},
				{"data":"corpId",className:"none never"},
				{"data":"contributionCode",className:"none never"},
				{"data":"corp"},
				{"data":"projTypeDes"},
				{"data":"contribution"},
				{"data":"typeDes"},
				{"data":"assistTypeDes"}
			],
			order: [[ 1, "des" ]],
			columnDefs: [{
				"targets":0,
				"visible":false
			},{
				"targets":1,
				"visible":false
			}] 
	    });
	}
};
/**
 * 初始化流程步骤信息
 */
var handleWorkFlowRecord = function() {
	"use strict";
	if($('#wfId').val()!==''){
		workFlowRecordData.wfId=$('#wfId').val();
	}else{
		workFlowRecordData.wfId=-1;
	}
    if ($('#workFlowRecordTable').length !== 0) {
    	workFlowRecordTable= $('#workFlowRecordTable').on( 'init.dt',function(){
   			//默认选中第一行
    		/*$(this).bindDTSelected(trSelectedBack,true);*/
   			//隐藏遮罩
   			$('#workFlowRecordTable').hideMask();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
            ],
            //serverSide: true, 
            ajax: {
                url: 'workFlow/queryWorkFlowRecord',
                type:'post',
                data: function(d){
                   	$.each(workFlowRecordData,function(i,k){
                   		d[i] = k;
                   	});
                },
                datasrc: ''
            },
           // ajax: 'projectjs/inspection/json/groove_record.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
				{"data":"actionCode",className:"none never"},
				{"data":"actionNo"},
				{"data":"actionDes"}
			],
			order:[1, "asc" ],
			columnDefs: [{
				"targets":0,
				"visible":false
			}] 
        });
    }
};


//报验单列表点击行事件
function trCheckListBack(v, i, index, t, json){
	$("#wfId").val(json.wfId);
	if($('#wfId').val()!==''){
		workFlowRecordData.wfId=$('#wfId').val();
		$('#workFlowRecordTable').cgetData();
	}else{
		workFlowRecordData.pcId=-1;
		$('#workFlowRecordTable').cgetData();
	}
	
	var obj = $("#allType").get(0);
	if(json.typeId=="1"){
		//主流程
		var selectOprions = $("#actionCode option");
   	 	$.each(selectOprions,function(i,e){
   	 		//查询主流程的枚举 默认0为主流程
       		if($(this).attr("data-scaleType")=="0"){
       			//remove
       			$(this).removeClass("hidden");
       		}else{
       			$(this).addClass("hidden");
       		}
   		})
	}else{
        var assistTypeId=json.assistTypeId;
        var selectOprions = $("#actionCode option");
   	 	$.each(selectOprions,function(i,e){
	   		if($(this).attr("data-scaleType")==assistTypeId){
	   			//remove
	   			$(this).removeClass("hidden");
	   		}else{
	   			$(this).addClass("hidden");
	   		}
   		
   	 	})
	}
	
}
//流程列表添加页面记录
$(".addWfBtn").on("click",function(){
	$("#wfId").val("");
	var rowsPart=[];
	var t=$("#workFlowForm");
	var json1=t.serializeJson();
	json1.corp=$("#corpId option:selected").text();
	json1.contribution=$("#contributionCode option:selected").text();
	json1.projTypeDes=$("#projType option:selected").text();
	json1.typeDes=$("#typeId option:selected").text();
	json1.assistTypeDes=$("#assistTypeId option:selected").text();
	rowsPart.push(json1);
	if(json1.corpId=='-1'||json1.projType=='-1'){
		$("body").cgetPopup({
          	title: "提示信息",
          	content: "请正确填写信息",
          	accept: popClose,
          	chide: true,
          	icon: "fa-check-circle",
          	newpop: 'new'
    }); 
 	return false;
	}
	var json=$("#workFlowTable").DataTable().rows().data();
 	for(var i=0;i<json.length;i++){
		for(var j=0;j<rowsPart.length;j++){
			if(json[i].typeId=="1"){
				if(json[i].corpId == rowsPart[j].corpId 
						&& json[i].contributionCode == rowsPart[j].contributionCode 
						&& json[i].projType == rowsPart[j].projType
						&& json[i].typeId == rowsPart[j].typeId){ 
					$("body").cgetPopup({
		                  	title: "提示信息",
		                  	content: "该分公司的"+rowsPart[j].projTypeDes+rowsPart[j].contribution+"类工程流程已存在!",
		                  	accept: popClose,
		                  	chide: true,
		                  	icon: "fa-check-circle",
		                  	newpop: 'new'
		            }); 
	    		 	return false;
			 	}
			}else{
				if(json[i].corpId == rowsPart[j].corpId 
						&& json[i].contributionCode == rowsPart[j].contributionCode 
						&& json[i].projType == rowsPart[j].projType
						&& json[i].typeId == rowsPart[j].typeId
						&& json[i].assistTypeId == rowsPart[j].assistTypeId){ 
					$("body").cgetPopup({
		                  	title: "提示信息",
		                  	content: "该分公司的"+rowsPart[j].projTypeDes+rowsPart[j].contribution+"类工程流程已存在!",
		                  	accept: popClose,
		                  	chide: true,
		                  	icon: "fa-check-circle",
		                  	newpop: 'new'
		            }); 
	    		 	return false;
			 	}
			}
			
			
			
			
	 	}
 	}
	workFlowTable.rows.add(rowsPart).draw();
	//$('#workFlowTable').selectRow(0);
});

//流程列表保存功能
$(".saveWfBtn").on("click",function(){
	var workFlows=[];
	var json=$("#workFlowTable").DataTable().rows().data();
	//转成list
	$.each(json, function(k,v){
		if(v.wfId ==''){
			workFlows.push(v);
		}
	})
	var data=JSON.stringify(workFlows);
	workFlows = [];
	if(response){
        response.abort();
    }
    var response = $.ajax({
    	url: "workFlow/saveWorkFlows",
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
	        }else if(data === "noData"){
	        	$("body").cgetPopup({
                  	title: "提示信息",
                  	content: "请增加流程记录",
                  	accept: popClose,
                  	chide: true,
                  	icon: "fa-exclamation-circle",
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
var successBack=function(){
	$('#workFlowTable').cgetData(true);
}
//流程列表删除按钮
$(".deleteWfBtn").on("click",function(){
	var len=$('#workFlowTable').find('tr.selected').length;
	 if(len>0){
		 $("body").cgetPopup({
				title: '提示信息',
				content: '确定要删除选中数据吗?',
				accept: delWfData,
				icon: 'fa-exclamation-circle',
		  });
	 }else{
		 $("body").cgetPopup({
				title: '提示信息',
				content: '请选择要删除的记录!',
				accept: popClose,
				icon: 'fa-exclamation-circle',
		  });
	 }
});
//流程删除按钮
var delWfData =function(){
	 var json = $("#workFlowTable").DataTable().rows( '.selected' ).data();
	 var workFlows=[];
	//转成list
	$.each(json, function(k,v){
		workFlows.push(v);
	})
	var data=JSON.stringify(workFlows);
    var response = $.ajax({
    	url: "workFlow/deleWorkFlows",
    	type: "POST",
    	timeout : 5000,
    	contentType: "application/json;charset=UTF-8",
    	data: data,
    	success: function (data) {
    		if (data === "true") {   	        		  
    			$("body").cgetPopup({
    				title: "提示信息",
		          	content: "数据删除成功!",
		          	accept: successBack1,
		          	chide: true,
		          	icon: "fa-check-circle",
		          	newpop: 'new'
    			}); 
	        }else{
	        	$("body").cgetPopup({
                  	title: "提示信息",
                  	content: "数据删除失败, 请重试! <br>" + data,
                  	accept: popClose,
                  	chide: true,
                  	icon: "fa-exclamation-circle",
                  	newpop: 'new'
                });  
	        }
    	}
    });
}
var successBack1 = function(){
	$('#workFlowTable').cgetData(true);
}
//步骤列表添加页面记录
$(".addWfrBtn").on("click",function(){
	if($("#wfId").val()==""){
		$("body").cgetPopup({
          	title: "提示信息",
          	content: "请先保存流程",
          	accept: popClose,
          	chide: true,
          	icon: "fa-check-circle",
          	newpop: 'new'
		}); 
		return false;
	}
	var rowsPart=[];
	var t=$("#workFlowRecordForm");
	var json1=t.serializeJson();
	var actionNo;
	var len=$('#workFlowRecordTable').find('tr.selected').length;
	leng = $('#workFlowRecordTable').DataTable().rows().data().length;
	if(leng==0){
		actionNo = 1;
	}else{
		if(len==0){
			var json =  $('#workFlowRecordTable').DataTable().rows().data();
			actionNo = json.length+1;
		}else{
			var json =  $('#workFlowRecordTable').DataTable().rows().data();
			var json2 = $("#workFlowRecordTable").DataTable().rows( '.selected' ).data();
			var actionNoSelect;
			$.each(json2, function(k,v){
				if(k==0){
					actionNoSelect=v.actionNo;
					return false;
				}
			})
			actionNo=actionNoSelect;
			$.each(json, function(k,v){
				if(v.actionNo>=actionNoSelect){
					v.actionNo=v.actionNo+1;
				}
			})
			workFlowRecordTable.rows().remove();
			workFlowRecordTable.rows.add(json).draw();
		}
	}
	json1.actionNo=actionNo;
	json1.actionDes=$("#actionCode option:selected").text();
	rowsPart.push(json1);
	if(json1.actionCode=='-1'){
		$("body").cgetPopup({
          	title: "提示信息",
          	content: "请正确填写信息",
          	accept: popClose,
          	chide: true,
          	icon: "fa-check-circle",
          	newpop: 'new'
    }); 
 	return false;
	}
	var json=$("#workFlowRecordTable").DataTable().rows().data();
 	for(var i=0;i<json.length;i++){
		for(var j=0;j<rowsPart.length;j++){
			if(json[i].actionCode == rowsPart[j].actionCode){ 
				$("body").cgetPopup({
	                  	title: "提示信息",
	                  	content: "该步骤在工作流中已存在!",
	                  	accept: popClose,
	                  	chide: true,
	                  	icon: "fa-check-circle",
	                  	newpop: 'new'
	            }); 
    		 	return false;
		 	}
	 	}
 	}
 	workFlowRecordTable.rows.add(rowsPart).draw();
});
//流程步骤保存功能
$(".saveWfrbtn").on("click",function(){
	var dataObj={};
	var json=$("#workFlowRecordTable").DataTable().rows().data();
	dataObj.list = [];
	//转成list
	$.each(json, function(k,v){
		dataObj.list.push(v);
	})
	var id=$('#wfId').val();
	dataObj.wfId=id;
	var data=JSON.stringify(dataObj);
	dataObj.list = [];
	if(response){
        response.abort();
    }
    var response = $.ajax({
    	url: "workFlow/updateWorkFlowCode",
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
//流程步骤删除按钮
$(".deleteWfrBtn").on("click",function(){
	var len=$('#workFlowRecordTable').find('tr.selected').length;
	 if(len>0){
		 var rows = $("#workFlowRecordTable").DataTable().rows( '.selected' ).remove().draw();
		 var json =  $('#workFlowRecordTable').DataTable().rows().data();
		 $.each(json, function(k,v){
				v.actionNo=k+1;
			})
			workFlowRecordTable.rows().remove();
			workFlowRecordTable.rows.add(json).draw();
	 }else{
		 $("body").cgetPopup({
				title: '提示信息',
				content: '请选择要删除的记录!',
				accept: popClose,
				icon: 'fa-exclamation-circle',
		  });
	 }
});

var workFlow = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleWorkFlowList();
        	//初始化记录列表
        	if(!$.fn.DataTable.isDataTable('#workFlowRecordTable')){
				//初始化记录列表
				handleWorkFlowRecord();
			}else{
				if($('#wfId').val()!==''){
					workFlowRecordData.wfId=$('#wfId').val();
       				 $('#workFlowRecordTable').cgetData();
   				}else{
   					workFlowRecordData.pcId=-1;
       				 $('#workFlowRecordTable').cgetData();
   				}
			}
        }
    };
}();