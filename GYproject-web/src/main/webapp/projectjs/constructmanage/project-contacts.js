var projectContactsTable;
/**查询条件*/
var searchData={};
var projId=$('#projId').val();
searchData.projId=projId;
var handleProjectContacts = function() {
	"use strict";
    if ($('#projectContactsTable').length !== 0) {
    	projectContactsTable=$('#projectContactsTable').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBack,true);
    		if(!trSData.projectContactsTable.json){
    			showReport();
    		}
   			//隐藏遮罩
   			$('#projectContactsTable').hideMask();
   			//增加监听
   	    	addMonitor();
   	    	//修改监听
   	    	modifyMonitor();
   	    	queryCheckRole();//????
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' },
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'projectContacts/queryProjectContacts',
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
                {"data":"pcsId",className:"none never"}, 
                {"data":"projNo"}, 
				{"data":"projName"},
                {"data":"duName"}
			],
			order: [[ 1, "desc" ]],
			columnDefs: [{
				"targets":0,
				"visible":false
			}]
        });
    }
};

/** 选中行时，查询详述*/
var trSelectedBack = function(v, i, index, t, json){
	$("#pcsId").val(json.pcsId);
	t.cgetDetail('projectContactsForm','','',queryBack);
}
var queryBack=function(){
	// if($("#projectContactsForm option:selected").val()=="1"){
	// 	//工艺--隐藏报警
	// 	$(".alarmProj").addClass("hidden");
	//    	$(".craftWork").removeClass("hidden");
	// 	showReport();
	// }else{
	// 	//报警-隐藏工艺
	//    	$(".craftWork").addClass("hidden");
	//    	$(".alarmProj").removeClass("hidden");
		showReport();
	// }
}
//增加监听
var addMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		$("#flag").val("1");
		// $('#projectContactsForm').formReset();
		$(".editbtn").removeClass("hidden");
        //根据职务过滤可编辑项
        getSignStatusByPostId(getProjectInfo().post,"projectContactsForm");
		// $('#projectContactsForm').toggleEditState(true);
		$('#signTab').tab("show");
	});
}
//修改监听
var modifyMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		var len=$("#projectContactsTable").find("tr.selected").length;
		if(len>0){
			$("#flag").val("2");
			$(".editbtn").removeClass("hidden");
			//切换页签
			$('#signTab').tab("show");
			// $('#projectContactsForm').toggleEditState(true);
            getSignStatusByPostId(getProjectInfo().post,"projectContactsForm");
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的信息!',
				ahide:true,
				atext:'确定'
			});
		}
	});
}
//初始化表格
var projectContacts = function () {
	"use strict";
    return {
        init: function () {
        	handleProjectContacts();
        	$("#listTab,#signTab").on("shown.bs.tab",function(){
    			if($(this).is($('#listTab'))){
    				$("#flag").val("0");
    				if(!$.fn.DataTable.isDataTable('#projectContactsTable')){
    					handleProjectContacts();
        			}else{
        				$('#projectContactsTable').cgetData(true);
        					showReport();
        			}
    			}else{
    				if($("#flag").val()=="1"){//增加
    					$('.clear-sign').click();
    					$('#projectContactsForm').formReset("#projNo","#projName","#projAddr");
    					$(".editbtn").removeClass("hidden");
    					$('#pcsId').val("");
    				}else if($("#flag").val()=="2"){
    					$(".editbtn").removeClass("hidden");
    					// $('#projectContactsForm').toggleEditState(true);
    				}else{
    					$(".editbtn").addClass("hidden");
    					// $('#projectContactsForm').toggleEditState(false);
    				}
    			}
        	});
        }
    };
}();
//放弃
$(".giveupbtn").on("click",function(){
	$("#flag").val("1");
	$('#listTab').tab("show");
});
//保存
$(".saveBtn").on("click",function(){
	var data=$("#projectContactsForm").serializeJsonString();
	var projectContactsForm = $("#projectContactsForm");
	//开启表单验证
    if (projectContactsForm.parsley().isValid()) {
    	$(".saveHiddenBox").loadMask("正在保存...", 1, 0.5);
    	$.ajax({
         type: 'POST',
         url: 'projectContacts/saveProjectContacts',
         contentType: 'application/json;charset=UTF-8',
         data:data,
         success: function (data) {
        	 $(".saveHiddenBox").hideMask(); 
         	var content = "数据保存成功！";
         	if(data === "false"){
         		content = "数据保存失败！";
            }else if(data === "already"){
                content = "此信息已被修改，请刷新页面！";
         	}else{
         		$("#pcsId").val(data);
         			showReport();
         	}
	       	 $("#projectContactsForm").toggleEditState(false);
	       	 $(".editbtn").addClass("hidden");
         	var myoptions = {
                 	title: "提示信息",
                 	content: content,
                 	accept: popClose,
                 	chide: true,
                 	icon: "fa-check-circle"
             }
             $("body").cgetPopup(myoptions);
         },
         error: function (jqXHR, textStatus, errorThrown) {
             console.warn("工程联系单保存失败！");
         }
     	});
 		 //如果通过验证, 则移除验证UI
        projectContactsForm.parsley().validate();
    } else {
        //如果未通过验证, 则加载验证UI
        projectContactsForm.parsley().validate();
    };
});