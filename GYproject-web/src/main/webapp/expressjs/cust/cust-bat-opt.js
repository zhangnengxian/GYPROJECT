/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var mytable;
var searchData={};
var addresslist = function() {
	"use strict"; 
    if ($('#addrBatTable').length !== 0) {
    	 mytable= $('#addrBatTable').on( 'init.dt',function(){
    		 
             if($("#addrBatTable_filter").find(".asBtn").length<1){
                 $("#addrBatTable_filter").append('<a id="asPop1"  class="asBtn btn btn-default btn-sm m-l-10" title="选择地址"><i class="fa fa-search-plus"></i></a>');
             }
             
             //初始化完成的动作写在这里
	    	 $(this).bindDTSelected(trSelected,true).bindLoopEdit();
             $(".deluser").attr("data-c","#panel_box=custbat/detailpage");
             $("#panel_box").cgetPart($("#panel_box"),"",partback);
             $(".import").attr("data-c","");
           
        }).DataTable({
            language: {
                url: 'js/dt-chinese.json'
            },
            lengthMenu: [ 18, 20, 30, 50, 100 ],
            dom: 'Bfrtip',
            buttons: [
                /*{ 
                	text: '导出',
                	extend: 'excel',
                	title: 'custImport',
                	exportOptions: {
                        stripHtml : false,
                        rows: '.selected',
                        columns: ':visible',
                        sheetName: 'sheet11',
                        title: ['客户批量开户信息表'],
                    	remark: ['列名上标有代码的，需要填入代码值','例如：客户类型  1：工商户，则输入内容时，客户类型为工商户，填入"1"即可'],
                    	dataExportProcess : function(dt, dc){
                	        if (dc.title.length){
                	        	dc.title = customTitle(dc.title);
                	        }
                	        if (dc.remark.length){
                	        	dc.remark = customRemark(dc.remark);
                	        }
                	        if (dc.body.length){
                	        	dc.body = customBody(dc.body);
                	        }
                	        if (dc.header.length){
                	        	dc.header = customHeader(dc.header);
                	        }
                	        return dc;
                	    }
                	},
                	className: 'btn-primary btn-sm business-tool-btn' 
                },*/
                { text: '导出', className: 'btn-sm export' },
                { text: '导入', className: 'btn-sm import' },
                { text: '开户', className: 'btn-sm newuser business-tool-btn' },
                { text: '销户', className: 'btn-sm deluser business-tool-btn' }
            ],
            serverSide:true,
            ajax: {
                url: 'custbat/list',
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
            columns: [
	  					{"data":"addrId",className:"none never"},
	  					{"data":"userNo"},
						{"data":"addrDes"},
						/*{"data":"openDate"},*/
						{"data":"addrStatusDes"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"},
						{"data":"exprotNull",className:"none never"}
					],
					order: [[ 1, "asc" ]],
					columnDefs: [ {
					      "targets": 0,
					      "visible":false
					    },{
					      "targets": 4,
					      "visible":false
					    },{
					      "targets": 5,
					      "visible":false
					    },{
					      "targets": 6,
					      "visible":false
					    },{
					      "targets": 7,
					      "visible":false
					    },{
					      "targets": 8,
					      "visible":false
					    },{
					      "targets": 9,
					      "visible":false
					    },{
					      "targets": 10,
					      "visible":false
					    },{
					      "targets": 11,
					      "visible":false
					    },{
					      "targets": 12,
					      "visible":false
					    },{
					      "targets": 13,
					      "visible":false
					    },{
					      "targets": 14,
					      "visible":false
					    },{
					      "targets": 15,
					      "visible":false
					    },{
					      "targets": 16,
					      "visible":false
					    },{
					      "targets": 17,
					      "visible":false
					    },{
					      "targets": 18,
					      "visible":false
					    },{
					      "targets": 19,
					      "visible":false
					    },{
					      "targets": 20,
					      "visible":false
					    },{
					      "targets": 21,
					      "visible":false
					    },{
					      "targets": 22,
					      "visible":false
					    },{
					      "targets": 23,
					      "visible":false
					    },{
					      "targets": 24,
					      "visible":false
					    },{
					      "targets": 25,
					      "visible":false
					    },{
					      "targets": 26,
					      "visible":false
					    }]
        });
        
    }
    $('#addrBatTable').hideMask();
};

function customTitle(t){
	//unshift()方法向数组前追加新的元素，并返回新的长度。该方法在ie8及以下版本浏览器无效，返回undefined;
	var title = [];
	title.push(t);
	return title;
}
function customRemark(t){
	return t;
}
function customBody(t){
	//unshift()方法向数组前追加新的元素，并返回新的长度。该方法在ie8及以下版本浏览器无效，返回undefined;
	for(var i=0; i<t.length; i++){
		t[i].push("第 " + i + " 条");
	}
	return t;
}
function customHeader(t){
	var data = [];
	$.each(t,function(i,v){
		data[i]=v;
		/*if(v==="用户号"){
		=1`ap[]\data[i]="用户号1";	q/.,mnbvc
		}else if(v==="地址描述"){
			data[i]="地址描述2";
		}else if(v==="状态"){
			data[i]='状态3';
		}else{
			data[i] = v;	 
		}*/
	});
	//data.push("序号");
	return data;
}

$(document).on("click",".import",function(){
	var popoptions = {
			title: '文件导入',
			content: '<iframe frameborder="0" scrolling="no" src="custbat/importPop" style="width:100%; height:150px;"></iframe>',
			accept: importOk
		};
		$("body").cgetPopup(popoptions);
});
$(document).on("click",".upload",function(){
	var fileName = $("#fileUpload").val();
    if(fileName!="" && fileName!=null){
    	
    	fileName = fileName.substr(fileName.lastIndexOf("\\")+1);
    	$("#popform").attr("action","custbat/importExcel?fileName="+fileName);
    	$("#popform").submit();
    }
});
var importOk = function(){
	mytable.ajax.reload();
	/*console.log("importOk  method ...");
	var fileName = $("#fileUpload").val();
	
	$("#popform").submit();*/
	
}

$(document).on("click",".newuser",function(){
	$("#panel_box").cgetPart($("#addrBatTable"),"",openCustback);	
	/*$(".scbtn").removeClass("hidden");
	
	$("#custform").toggleEditState(true); */
});

var openCustback = function(){
	
    $(".scbtn").removeClass("hidden");
	$("#custform").toggleEditState(true);

	
}
$(document).on("click",".deluser",function(){
	$("#panel_box").cgetPart($(".deluser"),"",deluserback);
});
var deluserback=function(){
	$("#custAddr").val($('#addrBatTable tr.selected td:eq(0)').text());
}
$(document).on("click","#asPop1",function(){
	//title    打开界面的请求路径         确定function   取消func   title图标  
	var popoptions = {
		title: '地址列表检索',
		content: '#custbat/custBatPop',
		accept: search_demo_ok
	};
	$("body").cgetPopup(popoptions);
});

var search_demo_ok = function(){	
	searchData = $("#searchForm").serializeJson();
	searchData.addrCodeId=$(".currentLevel").val();
	$("#addrBatTable").cgetData();
}

/*监听行点击事件，与后台交互，查询相应的详述信息*/
$(document).on("click","#addrBatTable tbody tr",function(){

/*	$("#panel_box").cgetPart($("#addrBatTable"),"",partback);	
*/
	$("#panel_box").cgetPart($("#panel_box"),"",partback);	

});

var indexS = [];
var trSelected = function(v, i, index, t, json){
	console.log(JSON.stringify(v) + "/" + i);
	if(i.length>1){
		
		$(".newuser").removeClass("disabled");
		$(".deluser").removeClass("disabled");
		indexS = i;
		
	}else{
		addrselect($("#addrBatTable"));
	}
	
}


var addrselect=function(datatable){
	 var rindx = datatable.find(".selected").index();
	 var m = datatable.DataTable().column(3).data()[rindx];
	 indexS[0]=rindx;
	 if(m=='待用'){
	 $(".newuser").removeClass("disabled");
	 $(".deluser").addClass("disabled");
	 }else if(m=='在用'){
	 $(".deluser").removeClass("disabled");
	 $(".newuser").addClass("disabled");
	 }else if(m=='停用'){
	 $(".deluser").addClass("disabled");
	 $(".newuser").addClass("disabled"); 
	 }	
}
var cancel=function(){
	$("#panel_box").cgetPart($("#addrBatTable"),"",partback);	
}
var partback=function(){
    $("#custform").toggleEditState(false); 
    $(".scbtn").addClass("hidden"); 
    $("#custAddr").val($('#addrBatTable tr.selected td:eq(0)').text());
}
var custbat = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	addresslist();
        }
    };
}();
var saveIndx='';
$(document).on("click",".formSave",function(){
	//所获取的行数，符合条件:状态不是"停用",也就是说：状态为"在用"的第一个下标
	saveIndx = $("#addrBatTable").loopnow(3,"停用","在用");
	var selectedLen = $("#addrBatTable").find("tr.selected").length;
	if(selectedLen>1){
		if(saveIndx!=='' && saveIndx!==null){
			var custName = $("#custName").val();
			var custName2 = $("#custNamePers").val();
			if(custName == "" && custName2!=""){
				$("#custName").val(custName2);
			}else if(custName2 == "" && custName!=""){
				$("#custName2").val(custName);
			}
			$("#custform").formSave("busiOpera/openCustSave",'addrBatTable',mytable,openSaveBack,true,false);
			
		}else{
			
			var cannotLen = $("#addrBatTable .cannotloop").length;
			var myoptions = {
	            	title: "提示信息",
	            	content: cannotLen+"条数据不能进行开户操作，原因：地址状态必须为待用！<br>",
	            	accept: savePopClose,
	            	chide: true,
	            	icon: "fa-check-circle"
	         }
	         $("body").cgetPopup(myoptions);
		}
	}else{
		$("#custform").formSave("busiOpera/openCustSave",'addrBatTable',mytable);
	}
	/**/
});
//开户回调
var openSaveBack = function(data){
	//标记下一个下标为哪个
	var mynext = $("#addrBatTable").loopnext(saveIndx,3,"停用","在用");
	$("#addrBatTable").attr("data-c","#panel_box=custbat/openCust");
	saveBackDone(data,mynext,"开户","待用");
}
//销户回调
var delSaveBack = function(data){
	//标记下一个下标为哪个
	var mynext = $("#addrBatTable").loopnext(saveIndx,3,"停用","待用");
	$("#addrBatTable").attr("data-c","#panel_box=custbat/detailpage");
	saveBackDone(data,mynext,"销户","在用");
}
var saveBackDone = function(data,mynext,optType,addrStatus){
	var flag = true;
		$("#panel_box").cgetPart($("#addrBatTable"),"",getpartback);
		if(data!="true"){
			flag = false;
		}
		//标记当前行
		$("#addrBatTable tbody tr:eq("+saveIndx+")").loopover(flag);
		if(mynext == ""){
			console.log("失败条数："+$("#addrBatTable .looperror").length);
			
			var overLen = $("#addrBatTable .loopover").length;
			var cannotLen = $("#addrBatTable .cannotloop").length;
			var errorLen = $("#addrBatTable .looperror").length;
			var contentStr = "";
			if(overLen>0){
				contentStr = overLen+"条数据保存成功！<br>";
			}
			if(errorLen>0){
				if(data == "timeout"){
					data = "服务器请求超时！";
				}
				contentStr = errorLen+"条数据保存失败，原因："+data+"<br>";
			}
			if(cannotLen>0){
				contentStr = contentStr+cannotLen+"条数据不能进行"+optType+"操作，原因：地址状态必须为"+addrStatus+"！<br>";
			}
			
			var myoptions = {
                	title: "提示信息",
                	content: contentStr,
                	accept: savePopClose,
                	chide: true,
                	icon: "fa-check-circle"
             }
             $("body").cgetPopup(myoptions);
			
		}
}

var savePopClose = function(){
	$("#addrBatTable").loopdone();
	saveIndx = "";
	mytable.ajax.reload();
	
}

$(document).on("click",".delsave",function(){
	saveIndx = $("#addrBatTable").loopnow(3,"停用","待用");
	if(saveIndx!="" && saveIndx != null){
		$("#delCustform").formSave("busiOpera/delCustSave",'addrBatTable',mytable,delSaveBack,true,false);
	}else{
		var cannotLen = $("#addrBatTable .cannotloop").length;
		var myoptions = {
            	title: "提示信息",
            	content: cannotLen+"条数据不能进行销户操作，原因：地址状态必须为在用！<br>",
            	accept: savePopClose,
            	chide: true,
            	icon: "fa-check-circle"
         }
         $("body").cgetPopup(myoptions);
	}
});
var save=function(){
	
		if(true){
			$("#panel_box").cgetPart($("#addrBatTable"),"",getpartback);
			
		}
	}
var getpartback=function(){
	  $("#custform").toggleEditState(true); 
	  $(".scbtn").removeClass("hidden"); 
	  $("#custAddr").val($('#addrBatTable tr.selected td:eq(0)').text());
}

$(document).on("change","#custType",function(){
	var custType = $(this).val();
	if(custType == "1"){
		//工商
		$(".unit").removeClass("hidden");
		$(".personal").addClass("hidden");
	}else{
		//居民户
		$(".unit").addClass("hidden");
		$(".personal").removeClass("hidden");
	}
	$("#custform").styleFit();
});

var detailSuccess = function(data){
	 console.log(data.custType);
	 var custType = data.custType;
	 if(custType == "1"){
		 //工商户
		 $(".unit").removeClass("hidden");
		 $(".personal").addClass("hidden");
	 }else if(custType == "2"){
		 //居民户
		 $(".unit").addClass("hidden");
		 $(".personal").removeClass("hidden");
	 }
	 $("#delCustform").styleFit();
}
/**详述查询完回调函数*/
var detailBack = function(data){
	$(".descrip").addClass("hidden");
}

$(document).on("click",".export",function(){
	var jsons=trSData.jsons;
	console.log("jsons:"+jsons);
	var data=JSON.stringify(jsons);
	$("#json").val(data);
	$("#custExportForm").submit();
});
