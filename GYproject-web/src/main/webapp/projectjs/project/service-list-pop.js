
var serviceLogData=$("#serviceLogForm").serializeJson();
serviceLogData.projId = $("#projId").val();
//console.log("serviceLogForm.val..."+JSON.stringify(serviceLogData));
var handleserviceLog = function() {
	"use strict";
    if ($('#serviceLogTable').length !== 0) {
    	$('#serviceLogTable').on( 'init.dt',function(){
   			$('#serviceLogTable').hideMask();
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//查询监听事件
    		searchMonitor();
    		//接口同步
    		serviceTypeMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn'},
                { text: '同步数据', className: 'btn-sm btn-query serviceTypeBtn hidden'}
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'projectView/queryServiceList',
                type:'post',
                data: function(d){
                   	$.each(serviceLogData,function(i,k){
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
	  			{"data":"logId",className:"none never"}, 
	  			{"data":"serviceTypeDes"}, 
	  			{"data":"resultType"}, 
				{"data":"logParams"},
				{"data":"resultMsg"},
				{"data":"logTime"},
				{"data":"logId"}
			],
			columnDefs: [{
				"targets": 1,
				"render": function ( data, type, row, meta ) {
					if(type === "display" && row!==null && row!==""){
						data = data + "(" + row.serviceType + ")";
						return data;
					}else{
						return data;
					}
				},
			},{
				"targets":3,
				//长字符串截取方法
				"render": $.fn.dataTable.render.ellipsis({
					length: 15, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				}),
			},{
				"targets": 5,
				"render": function ( data, type, row, meta ) {
					if(type === "display" && data!==null && data!==""){
						//console.log("日期转换:"+format(data,'all'));
						return format(data,'all');
					}else{
						return data;
					}
				},
			},{
				"targets": 6,
				"render": function ( data, type, row, meta ) {
					if(type === "display" && row.resultType == "1"){
						//console.log("日期转换:"+format(data,'all'));
						var html = "<a target='_blank' class='Search_Button' data-row='" + data + "' href='/accessoryCollect/openFile?id="+data+"'>手动同步</a>";
						return html;
					}else{
						return "";
					}
				},
			}/*,{
				"targets": 6,
				"render": function (data, type, row, meta) {
					if(type === "display" && row.resultType == '1'){
						var  tdcon='<a target="_blank" class="Search_Button" data-row="' + data + '" href="/accessoryCollect/openFile?id='+data+'">手动同步</a>';
						return tdcon;
					}
					return data;
				}),
			}*/]
        });
    }
};

//弹屏监听方法
var searchMonitor = function(){
	$(".searchBtn").on("click",function(){
		serviceLogData = $("#serviceLogForm").serializeJson();
		serviceLogData.projId = $("#projId").val();
	    $("#serviceLogTable").cgetData();  //列表重新加载
	});
	
};
//弹屏监听方法
var serviceTypeMonitor = function(){
	$(".serviceTypeBtn").on("click",function(){
		var serviceType = $("#serviceLogForm").serializeJson();
		if(serviceType.serviceType==''){
			alertInfo("请选择要同步的接口！");
			return;
		}
		if($("#projId").val()==''){
			alertInfo("请选择要同步的工程！");
			return;
		}
		serviceType.projId = $("#projId").val();
		$.ajax({
            type: 'POST',
            url: 'synchroService/synchroServiceInfo',
            contentType: "application/json;charset=UTF-8",
            dataType:"json",
            data: JSON.stringify(serviceType),
            success: function (data) {
                //取消遮罩
                $("#acceptApplyForm").hideMask();
                var content = "数据保存成功！";
                if(data.ret_type === "-1"){
                    content = "数据保存失败！";
                }else if(data.ret_message ===  "exist"){
                    content = "工程编号已存在，请修改！";
                }else if(data.ret_message ===  "noneNumber"){
                    content = "该类出资方式编码尚未配置，请先在后台配置！";
                }else if(data.ret_message === "true"){
                    if($("#projId").val()==""){
                        $("#projectAcceptTable").cgetData();  //列表重新加载
                        $(".editbtn").addClass("hidden");
                		$("#acceptApplyForm").toggleEditState(false);
                    }else{
                        $("#projectAcceptTable").cgetData(true,saveProjectBack,true);  //列表重新加载
                    }
                }else{//接口异常
                    content =  data.ret_message;
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
                //取消遮罩
                $("#acceptApplyForm").hideMask();
                console.warn("立项申请受理区记录保存失败！");
            }
        });
	});
	
};

var trSelectedBack = function(v, i, index, t, json){
	
};


var serviceLog = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	serviceLogData=$("#serviceLogForm").serializeJson();
        	serviceLogData.projId = $("#projId").val();
        	handleserviceLog();
        }
    };
}();

