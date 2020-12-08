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
var completionDataApplyTable;
var completionDataTable;
/**查询条件  默认待勘察*/
var searchData={};
var accessoryData={};
var listSearchData = {};
var handleCompletionDataApply = function() {
	"use strict";
    if ($('#completionDataApplyTable').length !== 0) {
    	completionDataApplyTable= $('#completionDataApplyTable').on( 'init.dt',function(){
    		//加载页面
    		$("#project_accept_panel_box").cgetContent("completionDataApply/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#completionDataApplyTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#completionDataApplyTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//修改监听方法
   			applyMonitor();
   			//右侧列表加载
   			//rightTableCgetData();
   			setTimeout(function(){
   			    $("#completionDataApplyTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
   			//跳转链接
   			if (crossPageBus) {
   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   					//跳转后销毁对象
   				crossPageBus = null
   			}
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },         
                { text: '申请', className: 'btn-sm btn-query business-tool-btn applyBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'completionDataApply/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/complete/json/completion_data_apply.json',
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
				{"data":"projStatusDes"},
				{"data":"signBack",
					className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						console.info("cellData---"+cellData);
						if(cellData==$("#notThrough").val()){
							$(td).parent().children().css("background", "rgb(255, 219, 219)");
							//$(td).attr("id",row);
						}
					}
					},
			 {"data":"overdue",className:"none never"},
			 {"data":"isSpecialSign",className:"none never",
				 "createdCell": function (td, cellData, rowData, row, col) {
					 if(cellData=='1'){
						 $(td).parent().children().css("color", "rgb(255, 99, 95)");
					 }
				 }
			 }
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
			var url = "#projectSelfCheck/projectSearchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#stateSelect,#completionDataApplyTable_filter input").on("change",function(){
		var stateSelect = $("#stateSelect").find("option:selected");
		var projNo = $("#completionDataApplyTable_filter input").val();
		searchData = {};
		searchData.projStatusId = stateSelect.val();
		searchData.projNo = projNo;
		$("#completionDataApplyTable").cgetData(true,dataApplyallBack);  //列表重新加载
		
	});
	//基础条件查询添加回车事件
	$('#completionDataApplyTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	searchData = $("#searchProjectSelfCheck").serializeJson();
	var projNo = $("#completionDataApplyTable_filter input").val();
	searchData.projNo=projNo;
	//列表重新加载
    $("#completionDataApplyTable").cgetData(true,dataApplyallBack);  
}

var dataApplyallBack = function(){
	//var len = $('#completionDataApplyTable').DataTable().ajax.json().data ? $('completionDataApplyTable').DataTable().ajax.json().data.length : $('#completionDataApplyTable').DataTable().ajax.json().length;
	var len = $('#completionDataApplyTable').DataTable().data().length;
	if(len<1){
		//清空右侧记录
		$("#completionDataApplyForm").formReset();
		$("#completionDataApplyForm").toggleEditState(false).styleFit();
		$(".editBtn").addClass("hidden");
		accessoryData.projNo="####";
	}
	 
}
//修改监听方法
var applyMonitor = function(){
	$(".applyBtn").on("click",function(){
		if($("#completionDataApplyTable").find("tr.selected").length>0){
			detailFlag = true;
			//切换可编辑状态
			$("#completionDataApplyForm").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
            if(!$("#applyDate").val()){
                var sysDate = timestamp($("#sysDate").val());
                $("#applyDate").val(format(sysDate,"default"));
            }
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
	$("#completionDataTable .checkbox").on("change",function(){
		handleCheckBox();
	})
};



var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	$(".informSaveBtn").addClass("hidden");
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	detailFlag = false;
	t.cgetDetail('completionDataApplyForm','completionDataApply/viewProject','',scaleDetailRefresh);
}

var scaleDetailRefresh = function(data){
	if($.fn.DataTable.isDataTable('#completionDataTable')){
		listSearchData.projId=data.projId;
		//初始化过
		$("#completionDataTable").cgetData(false);//列表重新加载
	}else{
		completionDataTableInit();
	}
}

var completionDataTableInit = function() {
	"use strict";
	var json=trSData.completionDataApplyTable.json;
	if(json){
		listSearchData.projId=json.projId;
	}else{
		listSearchData.projId="-11";
	}
    if ($('#completionDataTable').length !== 0) {
    	completionDataTable= $('#completionDataTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#completionDataTable').hideMask();

   			checkboxMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [8],
            dom: 'Brtip',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:true,
            ajax: {
            	url: 'completionDataApply/queryDataAcceptanceRecord',
                type:'post',
                data: function(d){
                   	$.each(listSearchData,function(i,k){
                   		d[i] = k;
                   	});

                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/complete/json/completion_data_apply.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                    {"data":"caiId",className:"text-center"},
                    {"data":"dataName",className:"text-center"},
    	  			{"data":"dataName"},
    	  			{"data":"dataTypeDes"},
    	  			{"data":"caiId",className:"text-right"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets": 1,
			    "visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 15, 	//截取多少字符（或汉字）
					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":4,
				"render": function ( data, type, row, meta ) {
					return addCheckBox(data,row.daId);
				}
			}/*,{
				targets:3,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm text-right" '+' name="' + row.daId + '_isComplete" id="' + row.daId + '_isComplete" value="'+data+ ' type="checkbox""></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			}*/],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        })
        .on("draw.dt", function(){
        	checkboxMonitor();
    	});
    }

};


var completionDataApply = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handleCompletionDataApply();
        	});
        }
    };
}();

/**
 * 传递记录id和报验单Id
 * 返回复选框html
 */
var addCheckBox = function(caiId,daId){
	var html='<input type="checkbox" name="abox" class="checkbox"' ;
	if(caiId){
		html += 'value="'+caiId+'"';
	}else{
		//html += 'disabled="disabled"';
	}
	if(daId){
		//html += 'disabled="disabled"';
		html += 'data="'+daId+'"';
		html += 'checked="checked" ';
	}
	html += ' />';
	return html;
}

/**
 * checkbox选中事件
 */
var checkboxMonitor = function(){
	$("#completionDataTable .checkbox").on("change",function(){
		handleCheckBox();
	})
}


//checkbox选中值组装到recordsId
var handleCheckBox = function(){
	var checkboxs=$("[name='abox']:checkbox");
	
	console.info(checkboxs);
	console.info("-------");
	
	var caiId='';
 	$.each(checkboxs,function(i,e){
 		if($(e).is(":checked")){
 			if($(e).val()!=''){
 				caiId +=$(e).val()+",";
 			}
		}
   	});
 	/*var json = recordListTable
 	if(recordsId.length>0){
 		$('.auditInpectBtn').removeClass('hidden');
 	}else{
 		$('.auditInpectBtn').addClass('hidden');
 	}
 	console.info(recordsId+"===========recordsId");*/
 	$("#caiIds").val(caiId);
 	console.info(caiId+"===========caiIds");
}
