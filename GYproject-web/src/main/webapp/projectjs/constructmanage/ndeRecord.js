var searchData={},ndeRecordTable={},selectData={};
var accessoryTable;
var accessoryData = {};
//整改列表
var handleNdeRecord = function() {
	"use strict";
	searchData.projId = $('#projId').val();
	searchData.bcTypeDetail = $("#bcTypeDetail").val();
	searchData.menuId = getStepId();
    if ($('#ndeRecordTable').length !== 0) {
    	ndeRecordTable = $('#ndeRecordTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//隐藏遮罩
   			$('#ndeRecordTable').hideMask();
   			//弹屏查询
   	    	searchMonitor();
   			//修改监听
   	    	modifyMonitor();
   	    	//加载打印屏
  		    showReport();
  		    queryCheckRole();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                /*{ text: '查询', className: 'btn-sm btn-query searchBtn' },*/
                { text: '修改', className: 'btn-sm btn-query checkRole updateBtn business-tool-btn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'ndeRecord/queryBusinessCommunication',
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
      			{'data':'bcId',className:'none never'}, 
	  			{'data':'bcInitiatorName'}, 
				{'data':'bcRecipientName'},
				{'data':'noticeDate'},
				{"data":"ndeRecord"}		
			],
			columnDefs: [{
				 'targets':0,
				 'visible':false
			 },{
				 "targets":2,
				 //长字符串截取方法
				 render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				 })
			 },{
					"targets":4,
					//长字符串截取方法
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data && data.testResultDes){
								return data.testResultDes;
							}else{
								return '';
							}
						}else{
							return '';
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
}

//修改监听
var modifyMonitor = function(){
	$(".updateBtn").off("click").on("click",function(){
		var len=$("#ndeRecordTable").find("tr.selected").length;
		if(len>0){
			var json = trSData.ndeRecordTable.json;
			$('#ndeRecord_info').tab("show");
			// $('#ndeRecordForm').toggleEditState(true);
			$(".editbtn").removeClass("hidden");
			 //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"ndeRecordForm");
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的停工条目!',
				ahide:true,
				atext:'确定'
			});
		}
	});
};

/**
 * 查询弹屏监听方法
 */
var searchMonitor=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#ndeRecord/viewSearchPopPage";
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
	searchData = $("#searchForm_ndeRecord").serializeJson();
	searchData.projId = getProjectInfo().projId;
	searchData.menuId = getStepId();
	//列表重新加载
    $("#ndeRecordTable").cgetData(true,rectifyNoticeCallBack);  
   
}
/**
 * 重新加载整改通知列表
 */
var rectifyNoticeCallBack = function(){
	var len = $('#ndeRecordTable').DataTable().ajax.json().data ? $('#ndeRecordTable').DataTable().ajax.json().data.length : $('#ndeRecordTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 //$('#shutdownRecordTable')[0].reset();
		 $('#ndeRecordForm .addClear').val('');
		 $(".editbtn").addClass("hidden");
	 }else{
		 $(".editbtn").addClass("hidden");
	 }
	//清空签字
	$('.clear-sign').click();
	 showReport();
}

//点击放弃
$('.giveUpSave').off().on('click',function(){
	//列表重新加载
    $("#ndeRecordTable").cgetData(true,rectifyNoticeCallBack);
	//$("#ndeRecordForm").toggleEditState(false);
	$('ul.nav>li').removeClass("active");
	//$('#ndeRecordList').click();
	
});


/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$('#bcId').val(json.bcId);
	$("#nrId").val(json.ndeRecord.nrId);
	//将查询详述显示到相应的输入框内
	t.cgetDetail('ndeRecordForm','businessCommunication/viewByBcId','',queryBack);
	
}

var queryBack=function(data){
	selectData = data;
	//将数据填充到页面
	var f=$("#ndeRecordForm");
	var bc = data.bCommunication;
	if(bc){
		var selects = f.find('select[disabled],  [type="radio"][disabled]');
	    selects.removeAttr("disabled");
		var total = jQuery.extend({}, trSData.json, ndeRecord);
		f.deserialize(total);
		f.initFixedNumber();
		//有disabled属性的下拉菜单元素对象重新添加禁用属性
		selects.attr("disabled","disabled");
		
		$("#bcTypeDetail1").val(bc.bcTypeDetail);
	}else{
		$("#bcTypeDetail1").val('');
	}
	//查询细类
	$("#bcType").change();
	//探伤信息
	var ndeRecord = data.ndeRecord;
	if(ndeRecord){
		var selects = f.find('select[disabled],  [type="radio"][disabled]');
		selects.removeAttr("disabled");
		var total = jQuery.extend({}, trSData.json, ndeRecord);
		f.deserialize(total);
		f.initFixedNumber();
		//有disabled属性的下拉菜单元素对象重新添加禁用属性
		selects.attr("disabled","disabled");
		
		if(ndeRecord.suJgj!=null&&ndeRecord.suCse!=null){
			$(".ndeFormat").removeClass("hidden");
		}else{
			$(".ndeFormat").addClass("hidden");
		}
	}
	$('#busRecordId').val($('#bcId').val());
	accessoryData.busRecordId = $("#bcId").val()==''||$("#bcId").val()==undefined?"-1":$("#bcId").val();
	if($.fn.DataTable.isDataTable('#dataPopTableSecond')){
		//初始化过
		$("#dataPopTableSecond").cgetData(false,function(){
		
		});
	}else{
		seconddatainit1();
	}
	showReport();
	console.info("unitType-"+$(".unitType").val());
	console.info("checkUnit-"+$(".checkUnit").val());
	console.info("suUnit-"+$(".suUnit").val());
	//判断单位类型 是否显示修改按钮
	if($(".unitType").val()==$(".checkUnit").val()||$(".unitType").val()==$(".suUnit").val() || $(".unitType").val() == "1"){
		$(".updateBtn").removeClass("hidden");
	}else{
		$(".updateBtn").addClass("hidden");
	}
	if($("#testResult").val() == '2'){
		$(".updateBtn").addClass("disabled");
		 $("body").cgetPopup({
				title: '提示',
				content: '该条记录已处于返修状态，请勿修改!',
				ahide:true,
				atext:'确定'
			});
		
	}else{
		$(".updateBtn").removeClass("disabled");
	}
	if(bc.mark.indexOf("1")>=0 ){  //若是现场代表发起，委托负责人为工程负责人
		$("#suJgj_postType").val(",54,");
		$("#suCse_postType").val(",116,");
		/*$("#bcRecipient_postType").val(",54,");*/
	};
	
}

//列表页签
$('#ndeRecordList').on('shown.bs.tab',function(){
	$(".editbtn").addClass("hidden");
	$('#ndeRecordTable').cgetData();
});


//页面加载
var ndeRecord =  function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleNdeRecord();
        	});
        	$('[href="#ndeRecordInfo"]').on("show.bs.tab", function(){
        	    $("#ndeRecordTable").styleFit();
        	    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4").handleSignature();
        	});
        	$("#ndeRecordList,#ndeRecordInfo").on("shown.bs.tab",function(){
        		if($(this).is($("#ndeRecordList"))){
    				if(!$.fn.DataTable.isDataTable('#ndeRecordTable')){
    					handleNdeRecord();
        			}else{
        				//加载打印屏
        				//showReport();
        			}
        		}else{
        			//加载打印屏
        			// showReport();
        		}
        	});
        }
    };
}();


/**
 * 初始化资料
 */
var seconddatainit1= function() {
	"use strict";
    if ($('#dataPopTableSecond').length !== 0) {
        accessoryTable = $('#dataPopTableSecond').on( 'draw.dt',function(){
	   	//默认选中第一行
	    $('#dataPopTableSecond').hideMask();
    	var popClose2 = function(){};
	    	//删除附件列表记录
	     	$(".del_btn").on("click",function(){
	    		$("body").cgetPopup({
    				title: '提示',
    				content: '您确定删除该文件信息吗？',
    			    accept: {
    					func: deleteDone,	//函数名
    					singleArgs: $(this)	//参数，用于向func传入多个参数，不设置该属性或设置为[]，则传入不带参数的函数
    				}
    	    	});
	   		});
        }).DataTable({
        	language: language_CN,
           	lengthMenu: [8],
           	dom: 'Brtip',
           	buttons: [],
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
			}]
       });
   }
}
function deleteDone(t){
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
function failClose(){
	$("#filePreviews tbody").html("");
}
var saveImportBack = function(data){
	console.info(data);
	if(data.result.result=='noBusiness'){
		$("body").cgetPopup({
	    	title: "提示信息",
	    	content: "请先保存通知信息! <br>",
	    	accept: failClose,
	    	chide: true,
	    	icon: "fa-exclamation-circle"
	    });  	
	}else{
		$("#filePreviews tbody").html("");
	}
}