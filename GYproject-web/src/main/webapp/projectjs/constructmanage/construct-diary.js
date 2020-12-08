/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var searchData={};
var projId = getProjectInfo().projId;
var handleconstructDiary = function() {
	searchData.projId=projId;
	"use strict";
    	$('#diaryTable').on( 'init.dt',function(){
    		//项目负责人、现场代表、施工员
    		if($("#loginPost").val().indexOf('116')<0&&$("#loginPost").val().indexOf($("#BUILDER").val())<0&&$("#loginPost").val().indexOf($("#CONSTRUCTION").val())<0){
    			$(".addBtn").addClass("hidden");
    			$(".updateBtn").addClass("hidden");
    		}else{
    			$(".addBtn").removeClass("hidden");
    			$(".updateBtn").removeClass("hidden");
    		}
	    	$(this).bindDTSelected(trSelectedBack,true);
	    	//隐藏遮罩
	    	$("#diaryTable").hideMask();
	    	//增加监听方法
	    	addMonitor();
	    	//修改监听方法
	    	updateMonitor();
	    	queryCheckRole();
	    	detletMonitor();  //删除监听
        }).DataTable({
        	language: language_CN,
            lengthMenu: [ 15 ],
            dom: 'Brtip',
            buttons: [
                { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' },
                { text: '删除', className: 'btn-sm btn-warn business-tool-btn checkRole detletBtn' },
            ],
            serverSide: true, 
            ajax: {
                url: 'constructDiary/queryDailyLog',
                contenttype:"application/json;charset=utf-8",
                data:function(d){
                   	$.each(searchData,function(i,k){
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
      	  		{"data":"dlId", className:"none never"}, 
	  			{"data":"dlRecorder"}, 
				{"data":"dlDate"},
				{"data":"dlWeather"},
				{"data":"fieldPrincipal"}
			],
			order: [[ 0, "desc" ]],
			columnDefs: [{
				targets:0,
				visible: false
			},{
				targets:4,
				render:function(data,type,row,meta){
					if(type === "display"){
						var tdcon = '<img alt="" src="'+data+'" style="height: 30px">';
						return tdcon;
					}else{
						return data;
					}
				}
			}] 
        });
};

var trSelectedBack = function(v, i, index, t, json){
	$(".dailyLogFormEditbtn").addClass("hidden");
	t.cgetDetail('contractDiaryForm','','',detailBack);
	$("#contractDiaryForm").toggleEditState(false);
	showDetailReport();
};

//详述回调
var detailBack=function(){
	if($("#fieldPrincipal").val()!==""){
		$(".fieldPrincipal").attr("src",$("#fieldPrincipal").val());
		$(".fieldPrincipal").removeClass("hidden");
		$('#constructionUnit').val(getProjectInfo().cuName);
	}else{
		$(".fieldPrincipal").addClass("hidden");
	}
}
//增加监听方法 
var addMonitor = function(){
	$(".addBtn").on("click",function(){
		$("#contractDiaryForm").formReset();
        $("#contractDiaryForm").styleFit();
		showReport();
		$("#dlWeather").val("");
		$("#flag").val("2");
		//清除选中行样式
		$('#diaryTable tr.selected').removeClass("selected");
		$.ajax({
            type: 'POST',
            url: 'constructDiary/findByProjId',
            data: 'projId='+projId,
            dataType: 'json',
            success: function (data) {
            	$("#contractDiaryForm").deserialize(data.project);
            	$("#dlDate").val(data.dlDate);
            	$('#dlRecorder').val(data.dlRecorder);
            	var cp = data.constructionPlan;
            	if(cp){
            		$("#constructionUnit").val(cp.cuName);
            	}
            	$(".formClear,#dlId,#fieldPrincipal").val("");
            	//$(".fieldPrincipal").attr("src",$("#fieldPrincipal").val());
            	$('.clear-sign').click();
            	addMonitorDone(data);
            	$("#constructionUnit").val(getProjectInfo().cuName);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("cgetDetail() -> 详情查询失败");
                console.warn(jqXHR);
                console.warn(textStatus);
                console.warn(errorThrown);
            }
        });
		
		
	});
}

var addMonitorDone = function(data){
	$("#flag").val("2");
	$("form").toggleEditState(true);
	$(".dailyLogFormEditbtn").removeClass("hidden");
	$('[href="#dailyLogFormtab"]').tab("show");
	$("#contractDiaryForm2 :input").val('');
}

//修改监听方法 
var updateMonitor = function(){
	$(".updateBtn").on("click",function(){
		if($("#diaryTable").find("tr.selected").length>0){
			$("#flag").val("1");
			//切换可编辑状态
			$("form").toggleEditState(true);
			//维护按钮显示出来
			$(".dailyLogFormEditbtn").removeClass("hidden");
			$('[href="#dailyLogFormtab"]').tab("show");
		}else{
			alertInfo('请选择要修改的日志记录！');
		}
	});
}
var detletMonitor = function(){
		$(".detletBtn").on("click",function(){
			if($("#diaryTable").find("tr.selected").length>0){
			 $("body").cgetPopup({
					title: '提示',
					content: '点击删除后，工程日志记录不可恢复，您确定要删除该记录吗？',
				    accept: delDone   //点击确认以后执行删除函数
			  });
			}else{
				alertInfo('请选择要修改的日志记录！');
			}
	})
}
var delDone = function(){
	 var json = trSData.diaryTable.json;
     $("#diaryTable").loadMask("保存中···",1,0.5);
	 $("#diaryTable").cdeleteRow("dlId","constructDiary/byDlIdDeletedialy",queryBack);
}
var queryBack = function(){
	 //do something
	  $("#diaryTable").hideMask();
}
//点击日志记录页签时
$('[href="#dailyLogFormtab"]').on("show.bs.tab", function(){
	if($("#flag").val()==="0"){
		//是否有选中行
		if($("#diaryTable").find("tr.selected").length>0){
			$(".dailyLogFormEditbtn").addClass("hidden");
			trSData.t.cgetDetail('contractDiaryForm','','',detailBack);
			$("form").toggleEditState(false);

		}else if(!$("#diaryTable").find('tbody tr:eq(0) td').hasClass("dataTables_empty")){
			$("#diaryTable").selectRow(0);
			$(".dailyLogFormEditbtn").addClass("hidden");
			trSData.t.cgetDetail('contractDiaryForm','','',detailBack);
			$("form").toggleEditState(false);
		}else{
			$('#contractDiaryForm').deserialize(getProjectInfo());
			$("form").toggleEditState(false);
			$(".dailyLogFormEditbtn").addClass("hidden");
			$('#constructionUnit').val(getProjectInfo().cuName);
			$('.field-editable').val('');
		}
	}
	setTimeout(function(){
	    $("#projectImagesList").getImagesList({
	    	"projId": getProjectInfo().projId,
	    	"step": getStepId(),
	    	"projNo": getProjectInfo().projNo,
	    	"busRecordId": $("#dlId").val() || '-1'
	    });
	},300);
});

$('[href="#tab-1"]').on("show.bs.tab", function(){
	$("#flag").val("0");
});

/**日志记录保存*/
$(".saveDailyLog").on("click",function(){
	$("#contractDiaryForm").cformSave('diaryTable',formSaveBack,false);
});

//保存成功后的回调
var formSaveBack = function(data){
	var data = JSON.parse(data);
	var dlId = data.dlId,
	projNo = getProjectInfo().projNo,
	projId = getProjectInfo().projId;
	//图片上传      未完成
	$("form").toggleEditState(false);
	if(_inApk && $(".attach-images-list").find(".new-image").length){
		preImagesUpload($(".attach-images-list .upload-image-btn"), projId, projNo, getStepId(), dlId);
	};
	$(".dailyLogFormEditbtn").addClass("hidden");
	showDetailReport(data);
}

/**日志记录取消btn*/
$(".cancelDailyLog").on("click",function(){
	$('[href="#tab-1"]').tab("show");
	$("#diaryTable").cgetData(true);
});


//初始化表格
var constructDiary = function () {
	"use strict";
    return {
        init: function () {
        	handleconstructDiary();
        }
    };
}();