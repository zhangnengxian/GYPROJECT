/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var searchData={},
handleProjectSchedule = function() {
	"use strict";
    $('#projectScheduleTable').on( 'init.dt',function(){
    	
    	//隐藏遮罩
    	$("#projectScheduleTable").hideMask();
    	
    	$("#projectScheduleForm").toggleEditState();
    	
    	var dateRanger = '<div class="col-sm-6 col-xs-12 m-b-5">开工日期: <input type="text" class="form-control input-sm field-editable projStartDate" style="width: 120px" readonly value="'+ $("#projStartDate").val()+'" name=""></div><div class="col-sm-6 col-xs-12">截至日期: <input type="text" class="form-control datepicker-default input-sm endDate" style="width: 120px" name=""></div>';

    	$("#projectScheduleTable_filter").html(dateRanger);

	    $('.datepicker-default').datepicker({
	        todayHighlight: true
	    });
        $("#projectScheduleTable").before($(".schedule-bar"));
        
        //保存监听方法
        saveMonitor();
        //查询监听方法
        searchMonitor();
        $(this).bindInputsChange(foucsonclick);
        foucsonclick();
        queryCheckRole();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [ 18 ],
        dom: 'fBrtip',
        buttons: [
            { text: '查询', className: 'btn-sm btn-query  progressSearchBtn' },
            { text: '保存', className: 'btn-sm btn-query checkRole progressSaveBtn' }
        ],
        /*serverSide: true, */
        ajax: {
            url: 'projectSchedule/queryProgress',
            contenttype:"application/json;charset=utf-8",
            data:function(d){
                $.each(searchData,function(i,k){
                    d[i] = k;
                });
            },
            datasrc: 'data'
        },
        columns: [
  			{"data":"nuitProject", responsivePriority: 2},
  			{"data":"sqUnit", "className": "text-center", responsivePriority: 6},
			{"data":"allProgressNum", "className": "text-right", responsivePriority: 4},
			{"data":"heapProgressNum", "className": "text-right", responsivePriority: 5},
			{"data":"finishProgress", responsivePriority: 3},
			{"data":"thisProgressNumNvl", "className": "text-right", responsivePriority: 1}
		],
		order: [[ 0, "asc" ]],
		columnDefs: [{
			targets: 2,
			render: function( data, type, row, meta ){
				if(type === "display"){
					return parseFloat(data).toFixed(2);
				}else{
					return data;
				}
			}
		},{
			targets: 3,
			render: function( data, type, row, meta ){
				if(type === "display"){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return "0.00";
					}
				}else{
					return data;
				}
			}
		},{
			targets: 4,
			render: function ( data, type, row, meta ) {
				if(type === "display"){
					if(data === null || data === ''){
						data = '0%';
					}
					var bar_style = parseInt(data) < 15 ? 'danger' : parseInt(data) < 32 ? 'warning' : parseInt(data) < 50 ? 'inverse' : parseInt(data) < 75 ? 'default' : parseInt(data) < 100 ? 'info' : 'success',
					animate = parseInt(data) < 100 ? " active" : "",
					tdcon = '<div class="progress progress-striped m-b-0' + animate + '"><div class="progress-bar text-right p-r-5 progress-bar-' + bar_style + '" style="width: ' + data + '">' + data + '</div></div>';
					return tdcon;
				}else{
					return data;
				}
		    }
		},{
			targets: 5,
			/*
			 * render属性
			 * 方法携带四个参数
			 * data: 该单元格的原始数据，也就是默认显示的那些数据
			 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
			 * row: 但前行的所有原始数据
			 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
			 */
			render: function ( data, type, row, meta ) {
				if(type === "display"){
					if(data==null){
						data="";
					}
					var tdcon = '<input class="form-control input-sm text-right progress-id" progressId="'+row.progressId+'"total-val="'+row.allProgressNum+'" finish-val="'+row.heapProgressNum+'" data-parsley-type="number" value="' + data + '"></div>';
					return tdcon;
				}else{
					return data;
				}
		    },
		   /* "createdCell": function(td, cellData, rowData, row, col){
		    		if(rowData.THISPROGRESSNUMNVL+rowData.HEAPPROGRESSNUM > rowData.ALLPROGRESSNUM){
		    			$(td).attr("id",row);
		    			console.info("adasdasdadadad=============="+row);
			    		$(td).parent().css("background","red");
			    		alertInfo("本次完成量与累计完成量超过预计总量,请注意!!");
			    	}
		    }*/

		}],
		responsive: {
        	details: {
        		renderer: function ( api, rowIdx, columns ){
        			return renderChild(api, rowIdx, columns);
        		}
        	}
        }
    });
},

graphicProgress = function() {
    "use strict";
    $('#graphicProgressTable').on( 'init.dt',function(){

        //隐藏遮罩
        $("#graphicProgressTable").hideMask();

        // $("#projectScheduleForm").toggleEditState();   <div class="col-sm-6 col-xs-12 m-b-5"><input type="text" class="form-control input-sm field-editable projStartDate" style="width: 120px" readonly value="'+ $("#projStartDate").val()+'" name=""> <div class="col-sm-6 col-xs-12">截至日期: <input type="text" class="form-control datepicker-default input-sm endDate" style="width: 120px" name=""></div>';

        var dateRanger = '工序名称  <select class="form-control input-sm" style="width: 160px" id="nuitProject" name="nuitProject">';
        var options = $("#nuitProject1").html();
        dateRanger += options;
        dateRanger += '</select> 完成进度  <input type="text" class="form-control input-sm " readonly="readonly" id="finishProgress" style="width: 120px" name="finishProgress">';
        $("#graphicProgressTable_filter").html(dateRanger);

        // 工序change事件
        $("#nuitProject").change(function(){
            var gpId=$(this).val();
            if(gpId==-1){
                $("#finishProgress").val("");
            }else{
                $.ajax({
                    type: 'post',
                    url: 'stationContruction/viewGraphicProgress',
                    data: gpId,
                    contentType: "application/json;charset=UTF-8",
                    success: function(data){
                        var json=JSON.parse(data);
                        $("#finishProgress").val(new Number(json.gpVal).toFixed(2)+"%");
                        $("#quId").val(json.type);
                    },
                    error: function(data){
                        console.warn("形象进度查询失败");
                    }
                });
            }
        });

        $("#graphicProgressTable").before($(".schedule-bar"));

        //保存监听方法
        graphicSaveMonitor();
        // $(this).bindInputsChange(foucsonclick);
        // foucsonclick();
        // queryCheckRole();
    }).DataTable({
        language: language_CN,
        lengthMenu: [ 18 ],
        dom: 'fBrtip',
        buttons: [
            { text: '保存', className: 'btn-sm btn-query graphicProgressSave' }
        ],
		/*serverSide: true, */
        ajax: {
            url: 'projectSchedule/queryProgress',
            contenttype:"application/json;charset=utf-8",
            data:function(d){
                $.each(searchData,function(i,k){
                    d[i] = k;
                });
            },
            datasrc: 'data'
        },
        columns: [
            {"data":"projName", responsivePriority: 1},
            {"data":"nuitProjectDes", responsivePriority: 2},
            {"data":"finishProgress", responsivePriority: 3},
            {"data":"registerDate", responsivePriority: 4}
        ],
        order: [[ 0, "asc" ]],
        columnDefs: [{

            "targets": 3,
            "render": function (data, type, row, meta) {
                if (type === "display") {
                    if (data != null && data != "") {
                        return format(data, "default");
                    } else {
                        return "";
                    }
                } else {
                    return data;
                }
            }
        }],
        responsive: {
            details: {
                renderer: function ( api, rowIdx, columns ){
                    return renderChild(api, rowIdx, columns);
                }
            }
        }
    });
};

//查询
var searchMonitor = function(){
	
	$(".progressSearchBtn").on("click",function(){
		searchData.registerDate = $(".endDate").val();
		searchData.projId = getProjectInfo().projId;
		$("#projectScheduleTable").cgetData(false,handleTotalProgress);  //列表重新加载
	});
}

var foucsonclick = function(){
	$("#projectScheduleTable input").on('change', function(){
		var value = $(this).val(),
		totalVal=$(this).attr("total-val"),
		finishVal=$(this).attr("finish-val"),	
		progressId=$(this).attr("progressId");
		if(finishVal=="null"){
			finishVal=0;
		}
		if(new Number(value) + new Number(finishVal) > new Number(totalVal)){
			$("body").cgetPopup({
               	title: "提示信息",
               	content: "本次完成量与累计完成量之和超过预计总量,确定继续吗？",
               	accept: popClose,
               	cancel: {
               		func: popBack11,
               		singleArgs: progressId
               	},
               	icon: "fa-check-circle"
	        });
			/*$(this).closest("tr").css("background","red");*/
		}
		
	});
}

function popBack11(progressId){
	$("input.progress-id").each(function(i){
		if($(this).attr("progressId")==progressId){
			$(this).val("");
			return true;
		}
		
	})
	if($("input.progress-id").attr("progressId")==progressId){
		$("input.progress-id").val("");
	}
}
/*var cgetDataCallBack = function(){
	handleTotalProgress();
}*/

//形象进度保存监听方法
var graphicSaveMonitor = function(){
    $(".graphicProgressSave").on("click",function(){
        var data = {};
        data.nuitProject = $("#nuitProject").val();
        data.finishProgress = $("#finishProgress").val();
        data.projId = getProjectInfo().projId;
        data.projNo=getProjectInfo().projNo;
        data.projScaleDes=getProjectInfo().projScaleDes;
        data.projName=getProjectInfo().projName;
        $.ajax({
            type: 'POST',
            url: 'projectSchedule/saveGraphicProgress',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
                var content = "数据保存成功！";
                if(data === "false"){
                    content = "数据保存失败！";
                }else if(data === "rep"){
                    content = "进度需大于当前进度";
                }else{
                    $("#graphicProgressTable").cgetData(false);  //列表重新加载
                    $(".totalProgress").html("工程进度"+data);
                    $(".totalProgress").attr("style","width:"+data);
                }
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
                console.warn("材料批量领用保存失败！");
            }
        });
    });
};

//保存监听方法
var saveMonitor = function(){	
	$(".progressSaveBtn").on("click",function(){
	var t = $('#projectScheduleTable');
	if(t.checkInputs()){
		
		//console.info(t.getInputsData());
		/*var data = t.getInputsData({"nuitProject": "NUITPROJECT", "sqUnit": "SQUNIT", "allProgressNum": "ALLPROGRESSNUM","heapProgressNum": "HEAPPROGRESSNUM", "finishProgress": "FINISHPROGRESS",
			                        "thisProgressNum": "THISPROGRESSNUMNVL","progressId":"PROGRESSID","projId":"PROJID","projName":"PROJNAME","projScaleDes":"PROJSCALEDES","projNo":"PROJNO","quId":"QUID"});
		*/
		var data = t.getInputsData({"nuitProject": "nuitProject", "sqUnit": "sqUnit", "allProgressNum": "allProgressNum","heapProgressNum": "heapProgressNum", "finishProgress": "finishProgress",
            "thisProgressNum": "thisProgressNumNvl","progressId":"progressId","projId":"projId","projName":"projName","projScaleDes":"projScaleDes","projNo":"projNo","quId":"quId"});
		if(data.length){
       		$.ajax({
       			type: 'POST',
       			url: 'projectSchedule/saveProgress',
       			contentType: "application/json;charset=UTF-8",
       			data: JSON.stringify(data),
       			success: function (data) {
       				var content = "数据保存成功！";
       				if(data === "false"){
       					content = "数据保存失败！";
       				}else if(data === "true"){
       					$("#projectScheduleTable").cgetData(false);  //列表重新加载
       					//保存工程总进度
       					savePrjectTotalProgress();
	               		handleTotalProgress();
       				}
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
                   console.warn("材料批量领用保存失败！");
               }
            });
		}else{
			alertInfo("请填写本次完成量！");
		}
	}else{
		console.info("表单校验失败，请检查并修改未通过项目！");
	}
});

}
/**工程总进度查询*/
var handleTotalProgress = function(){
	var projId = getProjectInfo().projId;
	searchData.projId = projId;
	searchData.registerDate = $(".endDate").val();
	$.ajax({
        type: 'POST',
        url: 'projectSchedule/getTotalProgress',
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(searchData),
        success: function (data) {
          $(".totalProgress").html("工程进度"+data);
          $(".totalProgress").attr("style","width:"+data);
        }
    });
}

//保存工程量进度回调后保存工程表总进度
var savePrjectTotalProgress=function(){
	var projId = getProjectInfo().projId;
	$.ajax({
        type: 'POST',
        url: 'projectSchedule/savePrjectTotalProgress',
        contentType: "application/json;charset=UTF-8",
        data: projId,
        success: function (data) {
        }
    });
}

//获取开工日期
var handleProjectStart = function(){
	var projId = getProjectInfo().projId;
	searchData.projId = projId;
    $.ajax({
        type: 'POST',
        url: 'projectSchedule/getProjStartDate',
        data: 'projId='+projId,
        dataType: 'json',
        success: function (data) {
            if(data.costType =="2"){//清单计价
                graphicProgress();
                $("#graphicProgressTable").removeClass("hidden");
                $("#projectScheduleTable").addClass("hidden");
                $(".totalProgress").html("工程进度"+data.progress.finishProgress ||"0%");
                $(".totalProgress").attr("style","width:"+data.progress.finishProgress);
            }else{//定额计价
                $("#projStartDate").val(format(data.startDate)||"");
                handleProjectSchedule();
                $("#graphicProgressTable").addClass("hidden");
                $("#projectScheduleTable").removeClass("hidden");
                handleTotalProgress();
            }
        }
    });
}

//初始化表格
var projectSchedule = function() {
	"use strict";
    return {
        init: function() {
        	handleProjectStart();
        }
    };
}();