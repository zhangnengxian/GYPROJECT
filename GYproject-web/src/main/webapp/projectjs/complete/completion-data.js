/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var completionDataTable;
var scaleTable;
var menuId='110714';
/**查询条件  默认待勘察*/
var searchData={};
searchData.menuId = menuId;
var accessoryData={};
var detailSearchData = {};
var handleComplationData = function() {
	"use strict";
    if ($('#completionDataTable').length !== 0) {
    	completionDataTable= $('#completionDataTable').on( 'init.dt',function(){
    		//加载页面
    		$("#project_accept_panel_box").cgetContent("completionData/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		var selectHtml = '<label style="margin-left: 5px;"><select class="form-control input-sm hidden" name="stateSelect" id="stateSelect">';
    		var options = $("#projStatus").html();
    		selectHtml += options;
			selectHtml += '</select></label>';
			$("#completionDataTable_filter").append(selectHtml);	
			$("#completionDataTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#completionDataTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//修改监听方法
   			updateMonitor();
   			setTimeout(function(){
   			    $("#completionDataTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },         
                { text: '选择', className: 'btn-sm btn-query business-tool-btn chooseBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,  
			/* ajax: {
                url: 'stationAccept/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },*/
            ajax: {
                url:  'completionData/queryProject',
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
            select: true,  //支持多选
            columns: [
                {"data":"projId",className:"none never"}, 
	  			{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"projStatusDes"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: showLength, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":3,
				 "orderable":false
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
    
};

//弹屏监听方法
var searchMonitor = function(){
	
	//查询按钮弹出屏查询
	$(".searchBtn").on("click",function(){
			var url = "#completionData/searchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#stateSelect,#completionDataTable_filter input").on("change",function(){
		var stateSelect = $("#stateSelect").find("option:selected");
		var projNo = $("#completionDataTable_filter input").val();
		searchData = {};
		searchData.projStatusId = stateSelect.val();
		searchData.projNo = projNo;
		searchData.menuId = menuId;
		$("#completionDataTable").cgetData(true);  //列表重新加载
		
	});
	//基础条件查询添加回车事件
	$('#completionDataTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	searchData = $('#searchForm_stationAccept').serializeJson();
	var projNo = $('#completionDataTable_filter input').val();
	searchData.projNo = projNo;
	searchData.menuId = menuId;
	//列表重新加载
    $('#completionDataTable').cgetData(true,tableCallBack); 
}
var tableCallBack = function(){
	//var len = $('#completionDataApplyTable').DataTable().ajax.json().data ? $('completionDataApplyTable').DataTable().ajax.json().data.length : $('#completionDataApplyTable').DataTable().ajax.json().length;
	var len = $('#completionDataTable').DataTable().data().length;
	if(len<1){
		//清空右侧记录
		//$("#completionDataForm").formReset();
		//$("#completionDataForm").toggleEditState(false).styleFit();
		$(".editbtn").addClass("hidden");
		searchData.projNo="####";
	}
	 
}
//修改监听方法
var updateMonitor = function(){
	$(".chooseBtn").on("click",function(){
		if($("#completionDataTable").find("tr.selected").length>0){
			detailFlag = true;
			$("#projNo").addClass("field-not-editable");
			$("#projNo").removeClass("field-editable");
			//切换可编辑状态
			$("#completionDataForm").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
};



var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	$(".informSaveBtn").addClass("hidden");
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	detailFlag = false;
	 if(json.projectType=="11"){ //判断是否有计量表和工程类型是否为民用工程，若是则必须有计量表才能打印竣工资料
		  if(json.isMeasurement == "1"){
			  $(".chooseBtn").removeClass("disabled");
		  }else if(json.isMeasurement == "0"){
			  $(".chooseBtn").addClass("disabled");
			  $("body").cgetPopup({
					title: '提示',
					content: '民用工程必须录入计量表,否则不能打印竣工资料!',
					ahide:true,
					atext:'确定'
				});
		  }
	  }else{
		  $(".chooseBtn").removeClass("disabled");
	  }
	 //判断是否有计量表
	 if(json.isMeasurement =='1' || json.isMeasurement  =='0' ){
		 //alert(json.isMeasurement);
		 $("#measurementTable").attr("checked",true);
		 $(".measurementTable").removeClass("hidden"); 
	 }else{
		 $("#measurementTable").attr("checked",false);
		 $(".measurementTable").addClass("hidden");
	 }
	t.cgetDetail('completionDataForm','completionData/viewProject','',scaleDetailRefresh);
	
}
//附件table
var accessoryTables;
//附件查询参数
var accessoryDatas={};
var  projStatusId='';
var scaleDetailRefresh = function(data){
	 projStatusId=data.projStatusId;  //nondestructive
	 $("#nondestructive").attr("checked",false);
	//判断是否已做完联合验收---开始,未做完联合验收不能打印相关资料
	if(data.projStatusId<='1027'){ 
		$("#acceptanceDocument").attr("checked",false);
		$("#acceptanceDocument").removeClass("field-editable");
		$("#acceptanceDocument").addClass("field-not-editable");
	}else{
		$("#acceptanceDocument").attr("checked",true);
		$("#acceptanceDocument").addClass("field-editable");
		$("#acceptanceDocument").removeClass("field-not-editable");
	}//判断是否已做完联合验收---结束
	var json=trSData.completionDataTable.json;
	/*
	if(json){
		$("#projNo").val(json.projNo);
		$("#projName").val(json.projName);
		$("#projAddr").val(json.projAddr);
	}*/
	accessoryDatas.projId = json.projId;
	if($.fn.DataTable.isDataTable('#dataPopTableSeconds')){
		//初始化过
		$("#dataPopTableSeconds").cgetData(false,function(){
		
		});
	}else{
		seconddatainits();
	}
}

/**
 * 初始化资料
 */
var seconddatainits= function() {
	"use strict";
    if ($('#dataPopTableSeconds').length !== 0) {
        accessoryTables = $('#dataPopTableSeconds').on( 'init.dt',function(){
	   	//默认选中第一行
	    $('#dataPopTableSeconds').hideMask();
        }).DataTable({
        	language: language_CN,
           	lengthMenu: [8],
           	dom: 'Brtip',
           	buttons: [],
          	ajax: {
               	url: 'accessoryCollect/queryCompletionAccList',
               	type:'post',
               	data: function(d){
                  	$.each(accessoryDatas,function(i,k){
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
           	columns: [
                {"data":"caiId",className:"none never"},
                {"data":"alOperateCsrId",className:"none never"},
	  			{"data":"alName",responsivePriority:2},
	  			{"data":"stepDes",responsivePriority:5},
	  			{"data":"alOperateTime",responsivePriority:3},
	  			{"data":"alOperateCsr" ,responsivePriority:4},	  			
	  			{"data":"alId",responsivePriority: 1}
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},{
				targets: 0,
				render: function (data, type, row, meta) {
					$('[type="checkbox"][name="accbox"][data-box="' + data + '"]').attr("checked","checked");
					return data;
				}
			},{
				targets: 6,
				render: function (data, type, row, meta) {
					if(type === "display"){
						var  tdcon='<a target="_blank" class="Search_Button btn btn-xs btn-primary" data-row="' + data + '" href="/accessoryCollect/openFile?id='+data+'"><i class="fa fa-eye"></i> 查看</a>';
							
							/*if($("#loginId").val() === row.alOperateCsrId){
								var  tdcon1='<a class="m-l-5 del_btn btn btn-warn btn-xs" data-id="'+data+'" data-path="'+row.alPath+'"><i class="fa fa-times"></i> 删除</a>';
							}else{
								var  tdcon1 = '';
							}*/
						return tdcon;
					}else{
						return data;
					}
				}
			}]
       });
   }
}

var completionData = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handleComplationData();
        	});
        }
    };
}();


