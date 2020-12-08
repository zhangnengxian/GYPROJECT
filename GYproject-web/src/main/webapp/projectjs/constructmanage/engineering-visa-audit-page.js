/**
 * 初始化历史审批列表
 */
var projId = $("#projId").val();
var businessOrderId = $("#businessOrderId").val();
var histSearchData = {"projId":projId,"businessOrderId":businessOrderId};
var accessoryData={},accessoryTable;
var handleAuditHistory = function() {
	"use strict";
    if ($('#auditHistoryTable').length !== 0) {
    	$('#auditHistoryTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#auditHistoryTable').hideMask();
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'engineeringVisaAudit/queryManageRecord',
                type:'post',
                data: function(d){
                   	$.each(histSearchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/design/json/connect_gas_audit_page.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
				{"data":"mrTime"},
				{"data":"mrResult"},
				{"data":"mrAopinion"},
				{"data":"mrCsr"}
			],
			columnDefs: [{
				"targets": 0,
				"render": function ( data, type, row, meta ) {
					if(type === "display"){
						return format(data,'all');
					}else{
						return data;
					}
				},
			},{
				"targets": 1,
				"render": function ( data, type, row, meta ) {
					if(data === "1"){
						return "通过";
					}else if(data === "0"){
						return "不通过";
					}else{
						return "";
					}
				},
			}]
        });
    }
};

/**
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
}

//加载签证详述页面
var engineeringDetailPage = function(){
	var evId = $(".evId").val();
	var data = {"evId":evId};
	//$("#engineering_audit_panelBox").cgetContent("engineeringAudit/viewPage",data,handleAuditHistory);
}
var touchPlanDetail = function(){
	 var cmId = $(".evId").val();
	 
	 	//序列化
      	var menuId = "120206";
	 	data = "menuId="+menuId;
	 	f = $("#engineeringForm");
		$.ajax({
	         type: 'POST',
	         url: 'engineering/viewEngineeringVisa?id='+cmId,
	         data: data,
	         dataType: 'json',
	         success: function (data) {
	             //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
	            var selects = f.find('select[disabled]');
	             selects.removeAttr("disabled");
	             //表单反序列化填充值
	             f.deserialize(data);
				 //照片列表
                 $("#projectImagesList").getImagesList({
                     "projId": data.projId,
                     "step": "120206",
                     "projNo": data.projNo,
                     "busRecordId": data.evId || '-1'
                 });

	             //有disabled属性的下拉菜单元素对象重新添加禁用属性
	             selects.attr("disabled","disabled");
	             var auditLevel = $(".auditLevel").val();
	             console.log("auditLevel:"+auditLevel);
	             if(auditLevel === "1"){
	            	 $("#engineeringForm").toggleEditState(true).styleFit();
	            	 $(".otherSign").toggleEditState(false);
	            	 if(data.auditState==="2"){
	            		 $(".toolBtn").removeClass("hidden");
	            		 $(".returnBtn").addClass("hidden");
	            	 }else{
	            		 $(".toolBtn").addClass("hidden");
	            		 $(".returnBtn").removeClass("hidden");
	            	 }
	             }
	             //$("#touchPlanForm").toggleEditState(false).styleFit();
	             
	             $("#fileupload .projId").val(data.projId);
	             $("#fileupload .projNo").val(data.projNo);
	         },
	         error: function (jqXHR, textStatus, errorThrown) {
	             console.warn("cgetDetail() -> 详情查询失败");
	         }
	     });
		
		//查询附件列表
		$('#busRecordId').val(cmId);
		accessoryData.busRecordId =cmId==''||cmId==undefined?"-1":cmId;
		if($.fn.DataTable.isDataTable('#dataPopTableSecond')){
			//初始化过
			$("#dataPopTableSecond").cgetData(false,function(){
			
			});
		}else{
			seconddatainit1();
		}
}

/**
 * 初始化资料
 */
var seconddatainit1= function() {
	"use strict";
    if ($('#dataPopTableSecond').length !== 0) {
    	var sourceType = $("#sourceType").val()==''||$("#sourceType").val()==undefined?"-1":$("#sourceType").val();
    	var sourceTypes = sourceType+",2";
    	accessoryData.sourceTypes=sourceTypes;
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
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 8, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
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
/**
 * 初始化审批历史列表
 */
var auditHistory = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	touchPlanDetail();
        	handleAuditHistory();
        }
    };
}();


