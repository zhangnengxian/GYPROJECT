/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var mytable;
var mytable4;
var mytable6;
var mytable2;
var mytable3;
var searchData={};
var searchData1={};
var searchData3={};
var metersign="";

var handleAddress = function() {
	"use strict";
    if ($('#addrtable').length !== 0) {
    	 mytable= $('#addrtable').on( 'init.dt',function(){	
              if($("#addrtable_filter").find(".asBtn").length<1){       
                $("#addrtable_filter").append('<a id="addrListPop" class="asBtn btn btn-default btn-sm m-l-10"> <i class="fa fa-search-plus"></i></a>');
           
            }
        }).DataTable({
        	 language: {
                 url: 'expressjs/addr/json/dt-chinese.json'
             },
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
               /* { text: '批量创建', className: 'btn-sm creat business-tool-btn' },*/
                /*{ text: '删除',  className: 'btn-sm delete business-tool-btn' },*/
               
            ],
            serverSide:true,
            ajax: {
                url: 'batcreate/list',
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
						{"data":"userNo","searchable": false},
						{"data":"addrDes"},
						{"data":"addrStatusDes","searchable": false}						
					],
					order: [[ 1, "asc" ]],
					columnDefs: [ {
					      "targets": 0,
					      "visible":false
					    }]
        });
        
    }
    $('#addrtable').hideMask();
};
$(document).on("click","#addrListPop",function(e){
    var popOption = {
    		title: '地址列表检索',
    		content: '#batcreate/addcode',
    		accept: 'search_demo_ok'
    };
    $("body").cgetPopup(popOption);
});

var search_demo_ok = function(){	
	//$("#searchForm").getQData("advSearch/list", "table2", button, col,columnDefs,mytable);
	searchData = $("#addrpopform").serializeJson();
	searchData.addrCodeId=$(".currentLevel").val();
	$("#addrtable").cgetData();
}
var address = function () {
	"use strict";
    return {
        //main function
        init: function () {
            handleAddress();
        }
    };
}();

$(document).on("click",".aspop",function(){
    var id=$(this).attr('id');
    var url='#batcreate/meterTypePop';
    var meterType=$(".infodetails li.active").attr("data-mrtype");
    var popOption = {
    		title: '表具型号选择',
    		content: url+"?meterType="+meterType,
    		accept: 'getvalue2'
    };
    $("body").cgetPopup(popOption);
   
    }
);
$(document).on("click","#buildPop",function(e){
	
	var popOption = {
    		title: '地址编码选择',
    		content: '#batcreate/custBatPop',
    		accept: 'getvalue1',
    		ahide:true//隐藏确定按钮
    		
    };
    $("body").cgetPopup(popOption);
	
	//$("body").cgetPopup("地址编码选择","#batcreate/custBatPop","getvalue1","");   
});
function getvalue1(){
	var mindex=$('#addrcodepop tr.selected').index();
	
	$('#addrCodeDes').val($("#addrcodepop").cgetColumnValue("addrCodeDes", mindex));
	$('#addrCodeId').val($("#addrcodepop").cgetColumnValue("addrCodeId", mindex));
}
function getvalue2(){
	var popi=$(".infodetails li.active").attr("data-type");
	var lia=$('#mrTypePop').DataTable().column(0).data()[$('#mrTypePop tr.selected').index()];
	var lia1=$('#mrTypePop').DataTable().column(1).data()[$('#mrTypePop tr.selected').index()];
	var lia2=$('#mrTypePop').DataTable().column(2).data()[$('#mrTypePop tr.selected').index()];
	var lia3=$('#mrTypePop').DataTable().column(3).data()[$('#mrTypePop tr.selected').index()];
	$('#meterRange'+popi).val(lia);
	$('#meterModelId'+popi).val(lia2);
	$('#meterModelDes'+popi).val($('#mrTypePop tr.selected td:eq(0)').text());
	$('#manuDes'+popi).val($('#mrTypePop tr.selected td:eq(1)').text());
	$('#manuId'+popi).val(lia1);
	$('#meterTypeDes'+popi).val($('#mrTypePop tr.selected td:eq(2)').text());
	$('#meterType'+popi).val(lia3);
	
	
}
//var searchData1;
var meterTypedt = function() {	
	"use strict";
	var meterType=$(".infodetails li.active").attr("data-mrtype");
	searchData3.meterType=meterType;
    if ($('#mrTypePop').length !== 0) {
    	 mytable3= $('#mrTypePop').on( 'init.dt',function(){
    		
     	    $('#mrTypePop').hideMask();
        }).DataTable({
        	 language: {
                 url: 'js/dt-chinese.json'
             },
            lengthMenu: [10, 20, 30, 50, 100 ],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm meterModelSel' },              
               
            ],
            ajax: {
                url: 'batcreate/meterModelList',
                contentType:"application/json;charset=UTF-8",
                data: function(){
                	return searchData3;
                },
                dataSrc: ''
            },
            responsive: true,
            searching: false,
            select: true,
            columns: [
	  					{"data":"meterRange",className:"none never"},
	  					{"data":"manuId.manuId",className:"none never"},
	  					{"data":"meterModelId",className:"none never"},
	  					{"data":"meterType",className:"none never"},
						{"data":"meterModel"},
						{"data":"manuId.manuName"},
						{"data":"meterTypeDes"}
					],
					columnDefs: [ {
					      "targets": 0,
					      "visible":false
					    }],
					columnDefs: [ {
						      "targets": 1,
						      "visible":false
						    }],
                    columnDefs: [ {
				      "targets": 2,
				      "visible":false
				    }],
				    columnDefs: [ {
					      "targets": 3,
					      "visible":false
					    }]
        });
        
    }
};
$(document).on("click",".meterModelSel",function(){
	searchData3.meterType=$(".infodetails li.active").attr("data-mrtype");
	searchData3.manuId=$("#manuIdpop").val();
	searchData3.meterModel=$("#meterModel").val();
	$("#mrTypePop").cgetData();
});
var addcodeinit= function() {
	"use strict";
   if ($('#addrcodepop').length !== 0) {
   	  mytable4= $('#addrcodepop').on( 'init.dt',function(){
   		//默认选中第一行
  		$(this).bindDTSelected(trSelectedBack,true);
    	    $('#addrcodepop').hideMask();
       }).DataTable({
       	 language: {
                url: 'js/dt-chinese.json'
            },
           lengthMenu: [ 10, 20, 30, 50, 100 ],
           dom: 'Bfrtip',
           buttons: [
               /*{ text: '保存', className: 'btn-sm save' },
               { text: '放弃',  className: 'btn-sm cancle' },*/
              
           ],
          ajax: {
               url: 'batcreate/getListByCode',
               contentType:"application/json;charset=UTF-8",
               data: function(){
               	return searchData1;
               },
               dataSrc: ''
           },
          
          // ajax:"expressjs/cust/json/add_code.json",
           searching: false,
           responsive: true,
           select: true,
           columns: [
	  					{"data":"addrCodeId"},
						{"data":"addrCodeDes"}
						
					]
				
       });
       
   }
};
var trSelectedBack=function(){};
var deptCode = function() {
	"use strict";
    
    if ($('#deptTablePop').length !== 0) {
    	   mytable6= $('#deptTablePop').on( 'init.dt',function(){
    		   $(this).bindDTSelected(trSelectedBack,true);
        }).DataTable({
        	 language: {
                 url: 'js/dt-chinese.json'
             },
            lengthMenu: [ 10, 20, 30, 50, 100 ],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm deptSelect' },
               
               
            ],
            ajax: {
                url: 'batcreate/getDeptList',
               // contentType:"application/json;charset=UTF-8",
                data: function(){
                	return searchData1;
                },
                dataSrc: ''
            },
          //  ajax:"expressjs/cust/json/dept_code.json",
            responsive: true,
            searching: false,
            select: true,
            columns: [
                        {"data":"deptId",className:"none never"},
	  					/*{"data":"deptInnerCode"},*/
						{"data":"deptOutCode"},
						{"data":"deptName"},
						{"data":"deptTypeDes"},
						{"data":"principal"},
						{"data":"phone"}
					],
             columnDefs: [ {
			      "targets": 0,
			      "visible":false
			    }]
					
        });
        
    }
    $('#deptTablePop').hideMask();
};
$(document).on("click",".deptSelect",function(){
	searchData1.deptName=$("#deptName6").val();
	searchData1.deptType=$("#deptType").val();
	 $("#deptTablePop").cgetData();
	
});

$(document).on("click","#deptCodePop",function(e){
	
	var popOption = {
    		title: '部门选择',
    		content: '#batcreate/deptPop',
    		accept: 'getvalue3'
    };
    $("body").cgetPopup(popOption);
	
	//$("body").cgetPopup("地址编码选择","#batcreate/custBatPop","getvalue1","");   
});
function getvalue3(){
	var mindex=$('#deptTablePop tr.selected').index();
	$('#deptId').val($("#deptTablePop").cgetColumnValue("deptId", mindex));
	$('#deptOutCode').val($("#deptTablePop").cgetColumnValue("deptOutCode", mindex));
	$('#deptName').val($("#deptTablePop").cgetColumnValue("deptName", mindex));
	/*var deptId=$('#table6').DataTable().column(0).data()[$('#table6 tr.selected').index()];
	$('#deptOuterCode').val($('#table6 tr.selected td:eq(1)').text());
	$('#deptName').val($('#table6 tr.selected td:eq(2)').text());
	$('#deptId').val(deptId);*/
	$('#deptTablePop').DataTable().destroy();
}
$(document).on("click",".cancel",function(){
	var clears=$(".form-horizontal .clear");
	for(var i=0;i<clears.length;i++){
		var cl=clears[i];
		cl.click();
	}
	window.location.href="#batcreate/main";
});


$(document).on("click",".save",function(){
	
	
    //开启表单验证
    if ($("#addrform5").parsley().isValid()&$("#addrform6").parsley().isValid()) {
        //防止多次提交
       if(response){
            response.abort();
        }
        var meterValid="";
        var data1={};
        //表单序列化获取json字符串
        var data = $("#addrform5").serializeJson();
        var data2=$("#addrform6").serializeJson();
        var dataMap={};
        data1.validYes=true;
       
        var obj=$(".infodetails li.active");
        if(obj.hasClass("tradition")&&""!=$("#meterModelDes").val()){
        	data1=mrValidMethod($("#addrform2"),data1);
        	meterValid=$("#addrform2");
        }else if(obj.hasClass("card")&&""!=$("#meterModelDes1").val()){
        	data1=mrValidMethod($("#addrform7"),data1);
        	meterValid=$("#addrform7");
        	
        }else if(obj.hasClass("remotetrans")&&""!=$("#meterModelDes2").val()){
        	data1=mrValidMethod($("#addrform4"),data1);
        	meterValid=$("#addrform4");
        } 
       
        if(data1.validYes){ 
       
        dataMap = $.extend({}, data,data1,data2);   
       
        var response=$.ajax({
            url: "batcreate/barchNewAddrAndMeter",
            type: "post",
            data: dataMap,
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
        //如果通过验证, 则移除验证UI
        if(""!=meterValid){
        	meterValid.parsley().validate();
        }
       
    }
    }
    	$("#addrform5").parsley().validate();
    	$("#addrform6").parsley().validate();
    
    
});

function mrValidMethod(formid,data1){
	data1.validYes=false;
	if(formid.parsley().isValid()){
    	 data1=formid.serializeJson();
    	 data1.validYes=true;
    	 
    	}else{
    		formid.parsley().validate();   		
    	}
	return data1;
	
}
function saveBack(data){
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