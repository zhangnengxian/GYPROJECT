/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var mytable;
var meterModelTable;
var searchData={};

var handleMeterBatInstall = function() {
	"use strict";
	searchData.addrStatusS=$("#addrStatusS").val();
    if ($('#meterbatinstalltable').length !== 0) {
    	mytable= $('#meterbatinstalltable').on( 'init.dt',function(){
    		
    		$(this).bindDTSelected(addrTrSelected, true);
    		
            if($("#meterbatinstalltable_filter").find(".asBtn").length<1){
            	$("#meterbatinstalltable_filter").append('<a id="addrCodePop" data-title="地址列表检索" class="asBtn btn btn-default btn-sm m-l-10" ><i class="fa fa-search-plus"></i></a>');
            }
    		//$("#meterBatInstallSelect").prependTo("#meterbatinstalltable_wrapper");
   			$("#meterBatInstallform").styleFit();
   			$("#province").linkSubSelect(changeOperLevel);
   			$("#city").linkSubSelect(changeOperLevel);
   			$("#town").linkSubSelect(changeOperLevel);
   			$("#street").linkSubSelect(changeOperLevel);
   			$("#subdistrict").linkSubSelect(changeOperLevel);
    		/*$("#add_bat_install_panel_box").cgetPart($("#add_bat_install_panel_box"));*/
    		$("#add_bat_install_panel_box").cgetPart($("#meterbatinstalltable"));
    		
    		
    		$('#meterbatinstalltable').hideMask();
   			
        }).DataTable({
        	 language: {
                 url: 'js/dt-chinese.json'
             },
            lengthMenu: [ 18 ],
            dom: 'Bfrtip',
            buttons: [
						/*{ 
							text: '导出',
							extend: 'excel',
							title: 'addrexport',
							exportOptions: {
						        stripHtml : false,
						        rows: '.selected',
						       // columns: ':visible',
						        title: ['表具批量安装'],
						    	//remark: ['列名上标有代码的，需要填入代码值','例如：客户类型  1：工商户，则输入内容时，客户类型为工商户，填入"1"即可'],
						    	dataExportProcess : function(dt, dc){
							        if (dc.title.length){
							        	dc.title =meterTitle(dc.title);
							        	
							        }
							        if (dc.remark.length){
							        	dc.remark = meterRemark(dc.remark);
							        }
							        if (dc.body.length){
							        	dc.body = meterBody(dc.body);
							        }
							        if (dc.header.length){
							        	dc.header = meterHeader(dc.header);
							        }
							        console.info(dc);
							        return dc;
							    }
							},
							className: 'btn-primary btn-sm business-tool-btn yesExcel' 
						},*/
						{ text: '导出', className: 'btn-sm noExcel' },
						{ text: '导入', className: 'btn-sm import' },
						{ text: '批装', className: 'btn-sm batInstall business-tool-btn' }
                      ],
            serverSide:true,
            ajax: {
                url: 'meterBatInstall/addrlist',
                type:'post',
                data: function(d){
                	$.each(searchData,function(i,k){
                		d[i] = k;
                	});
                	
                },
                dataSrc: 'data'
            },
            responsive: true,
            select: true,
           /* searching: false,*/
            columns: [
	  			            {"data":"addrId",className:"none never"},
							{"data":"userNo"},
							{"data":"addrDes"},
							{"data":"addrStatusDes"}
			],
			order: [[ 1, "asc" ]],
            columnDefs: [ {
			      "targets": 0,
			      "visible":false
			    }]
			

        });
        
    }
};

function meterTitle(t){
	//unshift()方法向数组前追加新的元素，并返回新的长度。该方法在ie8及以下版本浏览器无效，返回undefined;
	var title = [];
	title.push(t + '-成功');
	return title;
}
function meterRemark(t){
	return t;
}
function meterBody(t){
	//unshift()方法向数组前追加新的元素，并返回新的长度。该方法在ie8及以下版本浏览器无效，返回undefined;
	for(var i=0; i<t.length; i++){
		//t[i].push("第 " + i + " 条");
		t[i].unshift(i+1);
		t[i].push
	}
	return t;
}
function meterHeader(t){
	var dataAdd = ["表型号","表类型","量程","厂商","使用年限","进气方向","安装日期","安装位置","表箱号","固定方式","用气环境","温压补偿类型","补偿系数","合同模板","初始表见数","启用表见数","备注"];
	var data=[];
	$.each(t,function(i,v){
		data[i]=v;
	});
	data=data.concat(dataAdd);
	data.unshift("序号");
	//data.push("序号");
	return data;
}
$(document).on("click","#addrCodePop",function(e){
    var popOption = {
    		title: '地址列表检索',
    		content: '#batcreate/addcode?status=true',
    		accept: 'addr_search_ok'
    };
    $("body").cgetPopup(popOption);
});

var addr_search_ok = function(){	
	//$("#searchForm").getQData("advSearch/list", "table2", button, col,columnDefs,mytable);
	searchData = $("#addrpopform").serializeJson();
	searchData.addrCodeId=$(".currentLevel").val();
	$("#meterbatinstalltable").cgetData();
}
function addrTrSelected(v, i, index, t, json){
	console.info(index);
	}
function changeOperLevel(){
	console.info("当前选择-> " + $(".currentLevel").val() + " - " + $(".currentLevel").attr("data-value"));
}

var meterBatInstall = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleMeterBatInstall();
        }
    };
}();

$(document).on("click","#meterModelPop ",function(){
	   
	   var url='#batcreate/meterTypePop';
	    var meterModelPop = {
	    		title: '表具型号选择',
	    		content: url,
	    		accept: 'meterModelBack'
	    };
	    $("body").cgetPopup(meterModelPop);
	   
	    });
function meterModelBack(){
	$(".ic-only").addClass("hidden");
	var mindex=$('#mrTypePop tr.selected').index();
	var lia=$('#mrTypePop').DataTable().column(0).data()[mindex];
	var lia1=$('#mrTypePop').DataTable().column(1).data()[mindex];
	var lia2=$('#mrTypePop').DataTable().column(2).data()[mindex];
	var lia3=$('#mrTypePop').DataTable().column(3).data()[mindex];
	$('#meterRange').val(lia);
	$('#meterModelId').val(lia2);
	$('#meterModelDes').val($('#mrTypePop tr.selected td:eq(0)').text());
	$('#manuName').val($('#mrTypePop tr.selected td:eq(1)').text());
	$('#manuId').val(lia1);
	$('#meterTypeDes').val($('#mrTypePop tr.selected td:eq(2)').text());
	$('#meterType').val(lia3);
	if(lia3==$("#card").val()){
		$(".ic-only").removeClass("hidden");
	}else if(lia3==$("#remotetrans").val()){
		
	}
	$("#batInstallMeter").styleFit();
}
$(document).on("click",".batInstall",function(){
    var index=$("#meterbatinstalltable tbody").find("tr.selected");
    if(!index.length>0){
    	var myoptions = {
	        	title: "提示信息",
	        	content: "请选择地址信息!",
	        	accept: popClose,
	        	chide: true,
	        	icon: "fa-check-circle"
	        }
	        $("body").cgetPopup(myoptions);
    }else{
    	$("#add_bat_install_panel_box").cgetPart($("#add_bat_install_panel_box"));
    }
});
$(document).on("click",".bat-save",function(){
	var index=$("#meterbatinstalltable tbody").find("tr.selected");
	var arr=[];//选中多行时id数组
	for(var i=0;i<index.length;i++){
		var mindex=index.eq(i).index();
		var lia=$('#meterbatinstalltable').DataTable().column(0).data()[mindex];
		arr.push(lia);
		
	}
	if(!arr.length>0){
		 var myoptions = {
		        	title: "提示信息",
		        	content: "请选择地址信息!",
		        	accept: popClose,
		        	chide: true,
		        	icon: "fa-check-circle"
		        }
		        $("body").cgetPopup(myoptions);
	}else{
		   //开启表单验证
	      if ($("#batInstallMeter").parsley().isValid()) {
	        //防止多次提交
	       if(response){
	            response.abort();
	        }
	        var meterValid="";
	        //表单序列化获取json字符串
	        var data = $("#batInstallMeter").serializeJson();
	        var response=$.ajax({
	            url: "meterBatInstall/batSaveMeter?addrIds="+arr,
	            type: "post",
	            data: data,
	            success: function (data) {
	                if (data === "true") {
	                    if (mytable !== "" && mytable !== undefined) {
	                    	mytable.ajax.reload();
	                    }                  
	               
	            }
	                saveBack(data);
	           
	        }
	        
	        }
	        );
	     
	    }
	    $("#batInstallMeter").parsley().validate();
	}
});

function saveBack(data){
	console.info("saveBack+++++++++++++++++++++++++++++"+data)
	if("true"==data){
	  var myoptions = {
        	title: "提示信息",
        	content: "数据保存成功!",
        	accept: popClose,
        	chide: true,
        	icon: "fa-check-circle"
        }
        $("body").cgetPopup(myoptions);
	    $("#panel_body_box").cgetPart($("#panel_body_box"),"");
        } else if("insertError"==data){
        	var myoptions = {
                	title: "提示信息",
                	content: "地址信息重复! <br>",
                	accept: popClose,
                	chide: true,
                	icon: "fa-exclamation-circle"
                }
                $("body").cgetPopup(myoptions);
        }else {
        var myoptions = {
        	title: "提示信息",
        	content: "数据保存失败, 请重试! <br>",
        	accept: popClose,
        	chide: true,
        	icon: "fa-exclamation-circle"
        }
        $("body").cgetPopup(myoptions);
    }
}
//点击导出按钮展示的弹框
$(document).on("click",".noExcel",function(){
	   
	   var url='#batcreate/meterTypePop';
	    var meterModelPop = {
	    		title: '表具型号选择',
	    		content: url,
	    		accept: 'noExcelBack'
	    };
	    $("body").cgetPopup(meterModelPop);
	   
	    });

var noExcelBack=function(){
	var mindex=$('#mrTypePop tr.selected').index();
	var jsons=trSData.jsons;
	for (var i=0;i<jsons.length;i++){
		jsons[i].meterModelId=$("#mrTypePop").cgetColumnValue("meterModelId", mindex);
		jsons[i].meterType=$("#mrTypePop").cgetColumnValue("meterType", mindex);
		jsons[i].meterRange=$("#mrTypePop").cgetColumnValue("meterRange", mindex);		
		jsons[i].manuId=$("#mrTypePop").cgetColumnValue("manuId", mindex).manuId;
		
	}
	var data=JSON.stringify(jsons);

	 //location.href="meterBatInstall/export?jso="+jsons;
	$("#jso").val(data);
	alert($("#jso").val());
	$("#exportForm").submit();
/*	$.ajax({
        url: "meterBatInstall/export",
        type: "post",
        contentType: "application/json;charset=UTF-8",
        data: data,
        dataType:'html',
        success: function (data) {
        	alert(data);
         if("false"==data){
      
        	  var myoptions = {
        	        	title: "提示信息",
        	        	content: "导出失败, 请重试! <br>",
        	        	accept: popClose,
        	        	chide: true,
        	        	icon: "fa-exclamation-circle"
        	        };
        	        $("body").cgetPopup(myoptions);
        	    }
       
}
})*/
};

$(document).on("click",".import",function(){
	var popoptions = {
			title: '文件导入',
			content: "#meterBatInstall/importPop?url=meterBatInstall/importExcel",
			accept: importOk
		};
		$("body").cgetPopup(popoptions);
});

var importOk = function(){
	mytable.ajax.reload();
	
	
}
/**详述查询完回调函数*/
var detailBack = function(data){
	$(".descrip").addClass("hidden");
}

/*监听行点击事件，与后台交互，查询相应的详述信息*/
$(document).on("click","#meterbatinstalltable tbody tr",function(){
	$("#add_bat_install_panel_box").cgetPart($("#meterbatinstalltable"));

});
