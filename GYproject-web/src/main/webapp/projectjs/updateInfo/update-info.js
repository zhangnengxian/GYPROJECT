/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var updateInfoTable;
var scaleTable;
/**查询条件  默认待勘察*/

var searchData={};   
var accessoryData={};
var detailSearchData = {};
var accessoryTable;
var handleUpdateInfo = function() {
	"use strict";
    if ($('#updateInfoTable').length !== 0) {
    	updateInfoTable= $('#updateInfoTable').on( 'init.dt',function(){
    		//加载页面
    		$("#update_info_panel_box").cgetContent("updateInfo/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#updateInfoTable_filter input").attr("placeholder","更新编号");
   			//隐藏遮罩
   			$('#updateInfoTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//修改监听方法
   		    updateMonitor();
   			//增加监听方法
   			insertMonitor();
   			//删除监听
   			deleteMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },         
                { text: '增加', className: 'btn-sm btn-query   business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query  business-tool-btn updateBtn' },
                { text: '删除', className: 'btn-sm btn-warn business-tool-btn deleteBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'updateInfo/queryPage',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
//            ajax: 'projectjs/updateInfo/json/update_info.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"updateId",className:"none never"}, 
	  			{"data":"updateNo"}, 
				{"data":"updateNumber"},
				{"data":"updateTime"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 15, 	//截取多少字符（或汉字）
					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":3,
				"render":function(data,type,row,meta){
					if(data !=="" && data!==null){
						return format(data,'all');
					}else{
						return data;
					}
				}
				 
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
			var url = "#updateInfo/UpdateInfoSearchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#updateInfoTable_filter input").on("change",function(){
		var updateNo = $("#updateInfoTable_filter input").val();
		searchData = {};
		searchData.updateNo = updateNo;
		$("#updateInfoTable").cgetData(true);  //列表重新加载
		
	});
	//基础条件查询添加回车事件
	$('#updateInfoTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	
	//$("#update_info_panel_box").removeClass("hidden");
	searchData = $("#searchForm_update").serializeJson();
	var updateNo = $("#updateInfoTable_filter input").val();
	searchData.updateNo = updateNo;
	//console.info("1111");
	//console.info(searchData);
	//列表重新加载
    $("#updateInfoTable").cgetData(true,queryTableBack);
    
};  


//修改监听方法
var updateMonitor = function(){
	$(".updateBtn").on("click",function(){
		//$(".iframe-big-box").addClass("hidden");
		$("#update_info_panel_box").removeClass("hidden");
		if($("#updateInfoTable").find("tr.selected").length>0){
			//切换可编辑状态
			$("#updateInfoForm").toggleEditState(true);
			//维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
};


//增加监听方法
var insertMonitor = function(){
	$(".addBtn").off("click").on("click",function(){
		$.ajax({
            type: 'POST',
            url: 'updateInfo/queryBaseInfo',
            data: '',
            dataType: 'json',
            success: function (data) {
            	
            	$("#updateInfoForm input,#updateInfoForm textarea").val("");
        		$(".editbtn").removeClass("hidden");
            	
            	//$("#updateTime").val(format(data.dlDate,"all"));
        		$("#updateTime").val(data.dlDate);
        		$("#updateTime").change();
        		
            	$('#operater').val(data.dlRecorder);
            	$("#operaterId").val(data.staffId);
            	$(".noVal").removeClass("hidden");		 
            	$(".hasVal").addClass("hidden");
            	$("#drawName").val("");
            	$("#updateInfoForm").toggleEditState(true);
            	
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

var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	t.cgetDetail('updateInfoForm','updateInfo/viewUpdateInfo','',queryBack);
}

var queryBack = function(data){
	$("#alPath").val($("#updateNo").val());
	if(data.drawName){
		$(".hasVal").removeClass("hidden");
		$(".noVal").addClass("hidden");
		$(".noVal+#filePreviews tr").remove();
		$(".searchButton").attr("href","/updateInfo/openupdateInfoFile?id="+$("#updateId").val());
	}else{
		$(".hasVal").addClass("hidden");
		$(".noVal").removeClass("hidden");
	}
}

var queryTableBack=function(){
	var len = $('#updateInfoTable').DataTable().ajax.json().data ? $('#updateInfoTable').DataTable().ajax.json().data.length : $('#updateInfoTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#updateInfoForm')[0].reset();
		 $(':input','#updateInfoForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
		 $("#updateInfoForm").toggleEditState(false);
	}else{
		 $(".editbtn").addClass("hidden");
		 $("#updateInfoForm").toggleEditState(false);
	 }
}

var updateInfo = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handleUpdateInfo();
        		$("#listTab,#signTab").on("shown.bs.tab",function(){
        				if($(this).is($('#listTab'))){
        					if(!$.fn.DataTable.isDataTable('#updateInfoTable')){
        						//初始化列表
        						handleUpdateInfo();
                			}else{
                				//purgetable.ajax.reload();
                				$('#updateInfoTable').cgetData(true);
                				trSData.t&&trSData.t.cgetDetail('updateInfoForm','','');
                			}
        				}else{
        					if(!$.fn.DataTable.isDataTable('#dataPopTableSecond')){
        						//初始化列表
        						seconddatainit();
                			}else{
                				accessoryData.projLtypeId = getStepId();
                				$('#dataPopTableSecond').cgetData(true);
                			}
        				}
            		
            	});
        	});
        }
    };
}();



//删除监听
var deleteMonitor=function(){
	$('.deleteBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#updateInfoTable').find('tr.selected').length>0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除更新信息？',
				accept: deleteDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的更新信息！',
				accept: searchDone
			});
		}
	});
};


//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	$("#updateInfoTable").deleteRow(0,"updateInfo/deleteUpdateInfo",$("#updateInfoTable").DataTable(),queryTableBack);
	trSData.t && trSData.t.cgetDetail('updateInfoForm');
}

function saveBack(data){
   	var content = "数据保存成功！";
   	if(data.result === "false"){
   		content = "数据保存失败！";
   	}else{
   		$("#updateInfoForm input, #updateInfoForm textarea").not(":radio, :checkbox").val('');
		$("#updateInfoTable").cgetData(true,queryTableBack);  //列表重新加载
   	}
   	var myoptions = {
           	title: "提示信息",
           	content: content,
           	accept: popClose,
           	chide: true,
           	icon: "fa-check-circle"
       }
       $("body").cgetPopup(myoptions);
}

var surplusAccept=function(){
	$(".surplusDelBtn").removeClass("hidden");
}

//上传多个照片后删除
$(document).off("click",".surplusDelBtn").on('click',".surplusDelBtn",function(){
	$("#filePreviews tbody").html("");
	$(".surplusDelBtn").addClass("hidden");
});
	//删除附件列表记录
/*$(document).off("click",".del_btn").on('click',".del_btn",function(){
	$("body").cgetPopup({
	title: '提示',
	content: '您确定删除该文件信息吗？',
    accept: {
		func: deleteDone1,	//函数名
		singleArgs: $(this)	//参数，用于向func传入多个参数，不设置该属性或设置为[]，则传入不带参数的函数
	}
});
	});*/
/*function deleteDone1(){
$("#drawName").val("");
$(".hasVal").addClass("hidden");
$(".noVal").removeClass("hidden");
}*/


/**
 * 初始化资料收集(下)
 */
var seconddatainit= function() {
	"use strict";
    if ($('#dataPopTableSecond').length !== 0) {
    	var accessoryData = {};
    	accessoryData.projLtypeId = getStepId();
        accessoryTable = $('#dataPopTableSecond').on( 'draw.dt',function(){
	   	//默认选中第一行
	  	//$(this).bindDTSelected(trSelectedBack,true);
	    $('#dataPopTableSecond').hideMask();
    	var popClose2 = function(){};
	    	//删除附件列表记录
	     	$(".del_btn").on("click",function(){
	    		$("body").cgetPopup({
    				title: '提示',
    				content: '您确定删除该文件信息吗？',
    			    accept: {
    					func: deleteDone2,	//函数名
    					singleArgs: $(this)	//参数，用于向func传入多个参数，不设置该属性或设置为[]，则传入不带参数的函数
    				}
    	    	});
	   		});
        }).DataTable({
        	language: language_CN,
           	lengthMenu: [8],
           	dom: 'Brtip',
           	buttons: [],
         	//ajax: 'projectjs/accept/json/data_pop.json',
          	ajax: {
               	url: 'accessoryCollect/queryAccessoryList',
               	type:'post',
               	data: function(d){
                  	$.each(accessoryData,function(i,k){
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
	  			{"data":"alTypeId",responsivePriority:5},
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
							if($("#loginId").val() === row.alOperateCsrId){
								var  tdcon1='<a class="m-l-5 del_btn btn btn-warn btn-xs" data-id="'+data+'" data-path="'+row.alPath+'"><i class="fa fa-times"></i> 删除</a>';
							}else{
								var  tdcon1 = '';
							}
						return tdcon+tdcon1;
					}else{
						return data;
					}
				}
			}/* ,{
				targets: 7,
				render: function ( data, type, row, meta ) {	
					if(type === "display"){
						if($("#loginId").val() === row.alOperateCsrId){
							var  tdcon='<a class="del_btn btn btn-warn btn-xs" data-id="'+data+'" data-path="'+row.alPath+'"><i class="fa fa-times"></i> 删除</a>';
						}else{
							var  tdcon = '';
						}
						return tdcon;
					}else{
						return data;
					}
				}
			} */]
       });
   }
};
function deleteDone2(t){
    var data={};
	data.path=t.attr("data-path");	    
	data.alId=t.attr("data-id");
	$.ajax({
		url:'accessoryCollect/delAccessoryList',
		type:'post',
		data:data,
		success:function(data){
			if(data=="true"){
				$("[name='accbox']:checkbox").attr("checked",false);
					accessoryTable.ajax.reload();	  				
				}	
		    else{
		    	$("body").cgetPopup({
			    	title: "提示信息",
			    	content: "删除失败! <br>",
			    	accept: failClose,
			    	chide: true,
			    	icon: "fa-exclamation-circle"
			    });  		    	
		    }
		}
	});
}
function failClose(){}


var saveBack=function(data){
	var content = "数据保存成功！";
   	if(data.result === "false"){
   		content = "数据保存失败！";
   	}
   	var myoptions = {
           	title: "提示信息",
           	content: content,
           	accept: savedone1,
           	chide: true,
           	icon: "fa-check-circle"
   	}
   	$("body").cgetPopup(myoptions);
}


var savedone1=function(){
	$('#dataPopTableSecond').cgetData(true);
}
