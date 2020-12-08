
var accessoryData={},accessoryTable;
var handleStaffCertificatePop= function() {
	"use strict";
    if ($('#staffCertificateTable').length !== 0) {
    	var busRecordId;
    	if(trSData.staffList.json){
    		busRecordId=trSData.staffList.json.staffId;
    	}else{
    		busRecordId="";
    	}
    	var step = $("#step").val();
    	if(step==''){
    		ste=getStepId();
    	}
    	accessoryData.step = step;
    	accessoryData.busRecordId=busRecordId;
    	console.info("初始话---");
    	console.info(accessoryData);
        accessoryTable = $('#staffCertificateTable').on( 'draw.dt',function(){
	   	//默认选中第一行
	  	//$(this).bindDTSelected(trSelectedBack,true);
	    $('#staffCertificateTable').hideMask();
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











var staffCertificatePop = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleStaffCertificatePop();
        }
    };
}();