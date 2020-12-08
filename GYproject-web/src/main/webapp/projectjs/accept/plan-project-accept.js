/**查询条件*/
var searchData={},
	recordData={},
	treeArr=[],
	planTable,
    rowData=[],
    isAddRow = 0,
    ilIdBack,
	savestus=0;
searchData.planId="-1";
var detailSearchData = {};
detailSearchData.projId = "-1";
var scaleTable;
var handlePlanProjectAccept = function() {
	"use strict";
    if ($('#planProjectAcceptTable').length !== 0) {
    	$('#planProjectAcceptTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
            $("#planProjectAcceptTable_filter input").attr("placeholder","工程编号");
    		//隐藏遮罩
   			$('#planProjectAcceptTable').hideMask();
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
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '修改', className: 'btn-sm btn-query  updateBtn business-tool-btn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
            	url: 'planProjectAccept/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
            },
            
            // ajax: 'projectjs/accept/json/plan_accept.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
      			{"data":"projId",className:"none never"}, 
	  			{"data":"projNo",responsivePriority: 1}, 
				{"data":"projName",responsivePriority: 2},
				{"data":"projStatusDes",responsivePriority: 3},
				/*{"data":"overdue",className:"none never" }*/
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
				},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
				},{
					"targets":3,
					 "orderable":false
				}],
        });
    }
}
/**
 * 弹屏监听方法
 */
var searchPop=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#projectAccept/searchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
    //基础条件查询添加监听
    $("#planProjectAcceptTable_filter input").on("change",function(){
        var projNo = $("#planProjectAcceptTable_filter input").val();
        searchData = {};
        searchData.projNo = projNo;
        $("#planProjectAcceptTable").cgetData(true);  //列表重新加载
    });
    //基础条件查询添加回车事件
    $('#planProjectAcceptTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
            $(this).change();
        }
    });
}
/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_designDispatch").serializeJson();
	// searchData.projId = getProjectInfo().projId;
    var projNo = $("#planProjectAcceptTable_filter input").val();
    searchData.projNo = projNo;
    console.info(trSData.json.planId);
    searchData.planId = trSData.json.planId;
	//列表重新加载
    $("#planProjectAcceptTable").cgetData(true,securityInspectionCallBack);
    //showReport();
}
var callback = function(){}
//重新加载列表回调方法
var securityInspectionCallBack=function(){
	var len = $('#planProjectAcceptTable').DataTable().data().length;
	if(len<1){
		 //清空右侧记录
		 $('#acceptApplyForm').formReset();
        // //表格初始化或重新加载
        scaleDetailRefresh();
		 // $(':input','#acceptApplyForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}
var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
    $(".updateBtn").removeClass("hidden");
	$(".informSaveBtn").addClass("hidden");
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	detailFlag = false;
	t.cgetDetail('acceptApplyForm','','',scaleDetailRefresh);
	// scaleDetailRefresh();
}
var scaleDetailRefresh = function(data){
	 console.info("surveyStatusId---"+$("#surveyStatusId").val());
	 console.info("projStatusId---"+$("#projStatusId").val());
	if($("#surveyStatusId").val()==$("#projStatusId").val()){
		$(".updateBtn").removeClass("hidden");
	}else{
		//非勘察派工状态-隐藏修改按钮
		//$(".updateBtn").addClass("hidden");
	}


	var json=trSData.planProjectAcceptTable.json;
	var ltypeId = $("#projectType").val() || "";
	if(!ltypeId && $.fn.DataTable.isDataTable('#scaleTable')){
		detailSearchData.projId = "-1";
		$('#scaleTable').DataTable().ajax.reload();
		return;
	}
	ltypeId = ltypeId.split(",");
	$("input[name='projLtype']").attr("checked",false);
	for(var i=0;i<ltypeId.length;i++){
		$("input[name='projLtype'][value="+ltypeId[i]+"]").attr("checked","checked");
	}
	detailSearchData.projLtypeId = $("#projectType").val();
	detailSearchData.projId = $("#projId").val();
	if($.fn.DataTable.isDataTable('#scaleTable')){
		//初始化过
		$("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
	}else{
		sacletableinit();
	}
}


/**
 * 初始化规模列表
 */
var sacletableinit= function() {
	"use strict";
    if ($('#scaleTable').length !== 0) {
    	scaleTable= $('#scaleTable').on( 'init.dt',function(){
    		$('#scaleTable').hideMask();
	        $('#scaleTableForm').toggleEditState(false);
	        inputMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [],
            /*ajax: 'projectjs/accept/json/scale.json',*/
            //启用服务端模式，后台进行分段查询、排序
            serverSide:false,
            ajax: {
            	url: 'projectAccept/queryScaleDetail',
            	type:'post',
            	data: function(d){
                  	$.each(detailSearchData,function(i,k){
                  		d[i] = k;
                  	});
            	},
            	dataSrc: 'data'
            },
            
            //ajax: 'projectjs/accept/json/proj_scale.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },//自适应
            columns: [
	  			{"data":"projStypeDes", responsivePriority: 1},
	  			{"data":"pipeDiameter",className:"text-right", responsivePriority: 3},
	  			{"data":"pipeLength",className:"text-right", responsivePriority: 6},
	  			{"data":"finishLength",className:"text-right", responsivePriority: 5},
	  			{"data":"constructionRatio",className:"text-right", responsivePriority: 4}
			],
			columnDefs: [{
				"targets":0,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 6, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				targets: 1,
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
						if(data === null){
							data = "";
						}
						var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_pipeDiameter' id='" + row.projStypeId + "_pipeDiameter'  data-parsley-maxlength='50' value="+data+"></div>"
						return tdcon;
					}else{
						return data;
					}}
			    },{
			    	targets: 2,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_pipeLength' id='" + row.projStypeId + "_pipeLength' data-parsley-maxlength='13' data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}	
			    },{
			    	targets: 3,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_finishLength' id='" + row.projStypeId + "_finishLength'  data-parsley-maxlength='13' data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}	}
			    },{
			    	targets: 4,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}/*else if(data !==null && data!==""){
								console.info("data--"+data);
								data = data.toFixed(2);
							}*/
							var  tdcon="<div class='tdInnerInput'><input type='hidden' name='"+row.projStypeId+"_projStypeId' id='"+row.projStypeId+"_projStypeId' value="+row.projStypeId+">" +
									    "<input class='hidden' name='" + row.projStypeId + "_projLtypeId' id='" + row.projStypeId + "_projLtypeId' data-parsley-maxlength='9' data-parsley-type='number' value="+row.projLtypeId+">" +
									    "<input class='hidden' name='" + row.projStypeId + "_projStypeDes' id='" + row.projStypeId + "_projStypeDes'   value="+row.projStypeDes+">"+
									    "<input class='hidden' name='" + row.projStypeId + "_scaleId' id='" + row.projStypeId + "_scaleId'   value="+row.scaleId+">"+
									    "<input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_constructionRatio' id='" + row.projStypeId + "_constructionRatio' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}
			    }],
			ordering:false
       });
   }
};


var inputMonitor = function(){
	$(".tdInnerInput input").on("change",function(){
		var name = $(this).attr("name");
		var checkBoxId = name.split("_")[0]+"_scaleId";
		if($(this).val()){
			$("#"+checkBoxId).attr("checked","checked");
		}else{
			 var input = $(this).parents("tr").find(".tdInnerInput input").not("[type='checkbox']");
			 for(var i=0;i<input.length;i++){
				 if(input.eq(i).val()){
					 return false;
				 }
			 }
			 $("#"+checkBoxId).removeAttr("checked");
		}
	});
}

var queryBack=function(){
	$("#cuName").val(getProjectInfo().cuName);
	//showReport();
}
//增加按钮绑定监听事件
var addMonitor=function(){
	$(".addBtn").off("click").on("click",function(){
		isAddRow = 1;
		savestus = 1;
		$('#securityCheckRecord').tab('show');
		$('#securityInspectionForm').toggleEditState(true);
		$(".editbtn").removeClass("hidden");
		$('#ilId').val('');
		$('#checkDate').val('');
		$('#total').val('0');
		$("#cuName").val(getProjectInfo().cuName);
		$('#remark').val('');
		$('#fieldPrincipal').val('');
		$("#signBtn_1,#signBtn_2").resetSign();
		$('#planTable').DataTable().ajax.reload(function(){
		});
	});
}
//修改监听方法
var modifyMonitor = function(){
	$(".updateBtn").on("click",function(){
		var len=$("#planProjectAcceptTable").find("tr.selected").length;
		if($("#planProjectAcceptTable").find("tr.selected").length>0){
			detailFlag = true;
			$("#projNo").addClass("field-not-editable");
			$("#projNo").removeClass("field-editable");
			//切换可编辑状态
			$("#acceptApplyForm,#scaleTableForm").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
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
	   planTable.rows.add(rows).draw();
	  //rowData=rows;
	   var score = 0;
		 if($("#planTable").DataTable()!=null){
			 var json=$("#planTable").DataTable().rows().data();
			 for(var i=0;i<json.length;i++){
				 score = parseFloat(json[i].fraction)+score;
			 }
		 }
		 $("#total").val(score.toString());
}

var savedone = function(){
	//showReport();
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
}

//计划列表
var handlePlanTable = function(){
	"use strict";
	if ($('#planTable').length !== 0) {
		planTable=$('#planTable').on('init.dt',function(){
			var data1 = {"menuId":"110106"};
		$("#project_accept_panel_box").cgetContent("planProjectAccept/viewPage",data1);
		$(this).bindDTSelected(trSelectedBack1,true);
		$("#planTable_filter input").attr("placeholder","计划编号");
			//查询
            searchMonitor();
    		//增加记录
			addPlanMinotor();
			//删除记录
            // delRecord();
            // delData();
   		$('#planTable').hideMask();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
				{ text: '查询', className: 'btn-sm btn-query business-tool-btn planSearchBtn' }  ,
				{ text: '增加', className: 'btn-sm btn-query planAddBtn' }
            ],
            ajax: {
                url: 'annualPlan/queryAnnualPlan',
                type:'post',
                data: function(d){
                   	$.each(recordData,function(i,k){
                   		d[i] = k;
                   	});

                },
                dataSrc: 'data'
            },
            // ajax: 'projectjs/project/json/investment_manage.json',
            select: true,  //支持多选
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            columns: [
                {"data":"planId",className:"none never"},
                {"data":"planNo"},
				{"data":"planName"},
				{"data":"pipeDiameter"},
				{"data":"pipeLength",className:"text-right"},
				{"data":"finishLength",className:"text-right"},
				{"data":"completionSchedule"},
				{"data":"projectTypeDes"},
				{"data":"investmentScale",className:"text-right"},
				{"data":"alradyInvestment",className:"text-right"},
				{"data":"planInvestment",className:"text-right"},
				{"data":"constructionRatio"},
				{"data":"corpName"}
			],
			columnDefs: [{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 15, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}
				],
            //ordering:false
        });
	}
	// delData();
}

//弹屏监听方法
var searchMonitor = function(){

    //查询按钮弹出屏查询
    $(".planSearchBtn").on("click",function(){
        var popoptions = {
            title: '查询',
            content: "#annualPlan/searchPopPage",
            accept: searchDone1
        };
        //加载弹屏
        $("body").cgetPopup(popoptions);
    });
    //基础条件查询添加监听
    $("#planTable_filter input").on("change",function(){
        var planNo = $("#planTable_filter input").val();
        recordData = {};
        recordData.planNo = planNo;
        $("#planTable").cgetData(true);  //列表重新加载

    });
    //基础条件查询添加回车事件
    $('#planTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
            $(this).change();
        }
    });
};
/** 查询弹出屏，点击确定后 */
var searchDone1 = function(){
    recordData = $('#searchForm_annualPlan').serializeJson();
    var planNo = $('#planTable_filter input').val();
    recordData.planNo = planNo;
    //列表重新加载
    $('#planTable').cgetData(true);
}

var projectCallBack = function () {
    var len = $('#planProjectAcceptTable').DataTable().data().length;
    if(len<1){
        $(".editbtn").addClass("hidden");
        $(".informSaveBtn").addClass("hidden");
        detailFlag = false;
        $("#acceptApplyForm").formReset();
        $('#acceptApplyForm').toggleEditState(false);
        //
        var json=trSData.planTable.json;
        $("#corpName").val(json.corpName);
        $("#deptId").val(json.deptId);
        $("#projectType").val(json.projectType);
        $("#contributionMode").val(json.contributionMode);
        $("input[name='projLtype'][value="+json.projectType+"]").attr("checked","checked");
        detailSearchData.projId = "-1";
        if($.fn.DataTable.isDataTable('#scaleTable')){
            //初始化过
            $("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
        }else{
            sacletableinit();
        }
    }
}

var trSelectedBack1 = function(v, i, index, t, json){
    searchData.planId=json.planId;
    if(!$.fn.DataTable.isDataTable('#planProjectAcceptTable')){
        handlePlanProjectAccept();
    }else{
        $('#planProjectAcceptTable').cgetData(true,projectCallBack);
    }
	$("#projectType").val(json.projectType);
	$("#planId").val(json.planId);

    scaleDetailRefresh();
}

var addPlanMinotor=function(){
	$(".planAddBtn").on("click",function(){
		if($("#planTable").find("tr.selected").length>0){
            // $("#deptId option:first").prop("selected", 'selected');
            // $("#deptId").change();
            detailFlag = true;
            //切换可编辑状态
            $("#acceptApplyForm,#scaleTableForm").toggleEditState(true);
			//维护按钮显示出来
            $("#acceptApplyForm")[0].reset();
            var json=trSData.planTable.json;
            $("#corpName").val($("#deptName").val());
			$(".editbtn").removeClass("hidden");
            $("#projId").val("");
            $("#deptId").val(json.deptId);
            $("#projectType").val(json.projectType);
            $("#contributionMode").val(json.contributionMode);
            $("#projName").val(json.planName);
        	$("#projAddr").val(json.planAddr);
            $("#projectType").change();
            //表格初始化或重新加载
            scaleDetailRefresh();
		}else{
			alertInfo('请选择计划进行立项！');
		}
	})
}


$('#securityCheckList').on('shown.bs.tab',function(){
	$(".editbtn").addClass("hidden");
	$('#planProjectAcceptTable').cgetData();
});
$('#securityCheckRecord').on('shown.bs.tab',function(){
	setTimeout(function(){
	    $("#projectImagesList").getImagesList({
	    	"projId": getProjectInfo().projId,
	    	"stepId": getStepId(),
	    	"projNo": getProjectInfo().projNo,
	    	"busRecordId": $("#ilId").val() || '-1'
	    });
	},300);
});

//初始化
var planProjectAccept = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){       
	        	handlePlanProjectAccept();
                handlePlanTable();
	        	$("#projectTab,#planTab").on("shown.bs.tab",function(){
	        		if($(this).is($('#projectTab'))){
	        			if(!$.fn.DataTable.isDataTable('#planProjectAcceptTable')){
	        				handlePlanProjectAccept();
	        			}else{
	        				$('#planProjectAcceptTable').cgetData(true);
	        			}
	        		}else{
	        			if(!$.fn.DataTable.isDataTable('#planTable')){
	        				handlePlanTable();
	        			}else{
	        				$('#planTable').cgetData(true);
	        			}
	        		}
	        	})
        	})
        	
        }
    };
}();

var scaleTableCallBack = function(){
	if(detailFlag){
   	 	$('#scaleTableForm').toggleEditState(true);
	}else{
		$('#scaleTableForm').toggleEditState(false);
	}
}