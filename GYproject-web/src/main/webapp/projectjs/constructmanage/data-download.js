
var accessoryTable;
var accessoryData={};
var handleDataDownLoad= function() {
	"use strict";
    if ($('#dataPopTableSecond').length !== 0) {
    	var accessoryData = {};
    	var projLtypeId = $("#projLtypeId").val();
    	if(projLtypeId==''){
    		projLtypeId = getStepId();
    	}
    	accessoryData.projLtypeId = projLtypeId;
    	accessoryData.projId=getProjectInfo().projId;
        accessoryTable = $('#dataPopTableSecond').on( 'draw.dt',function(){
	   	//默认选中第一行
	  	$(this).bindDTSelected(trSelectedBack,true);
	    $('#dataPopTableSecond').hideMask();
	    $("#dataPopTableSecond_filter input").attr("placeholder","资料名称1");
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
	     //绑定单击事件 弹出搜索框
   		searchMonitor();	
        }).DataTable({
        	language: language_CN,
           	lengthMenu: [8],
           	dom: 'Brtip',
           	buttons: [],
         	//ajax: 'projectjs/accept/json/data_pop.json',
          	ajax: {
               	url: 'accessoryCollect/queryQualificationAccessoryList',
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
						var type=row.alTypeId;
						console.info("type--"+type);
						var  tdcon='<a target="_blank" class="Search_Button btn btn-xs btn-primary" data-row="' + data + '" href="/accessoryCollect/openFile?id='+data+"&type="+type+'"><i class="fa fa-eye"></i> 查看</a>';
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
			} */,{
				"targets":6,
				 "orderable":false
			}]
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

var searchMonitor=function(){
	$('#dataPopTableSecond_filter input').on('change',function(){
		var alName = $('#dataPopTableSecond_filter input').val();
		accessoryData = {};
		var projLtypeId = $("#projLtypeId").val();
    	if(projLtypeId==''){
    		projLtypeId = getStepId();
    	}
		accessoryData.projLtypeId = projLtypeId;
		//accessoryData.projLtypeId = alName;
		$('#dataPopTableSecond').cgetData(true);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#dataPopTableSecond_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}


var trSelectedBack=function(){
	
}

function failClose(){}
//初始化表格
var dataDownLoad = function() {
	"use strict";
    return {
        init: function() {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleDataDownLoad();
        	})
        }
    };
}();