/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var mytable, mytable2;
var searchData={};
var searchData1={};
var searchData4={};
var mrNoPopTable;
var handleMeterOperation = function() {
	"use strict";
	 
	mytable = $('#meterListTable').on( 'init.dt',function(){
		
		$(this).bindDTSelected(meterTrSelected, true);
		
        if($("#meterListTable_filter").find(".asBtn").length<1){
        	$("#meterListTable_filter").append('<a id="meterListPop" data-title="表具检索" class="asBtn btn btn-default btn-sm m-l-10" ><i class="fa fa-search-plus"></i></a>');
        }
        $("#meterListPop").off().on("click",function(){
			var popoptions = {
					title: '表具列表检索',
		    		content: '#meterOperation/metersearch',
		    		accept: 'meter_search_ok'
			};
			$("body").cgetPopup(popoptions);
		});
        
       // $(this).bindDTSelected(alertme);
        
        $(".changemeter").attr("data-c","#meterOperation/oper?formid=meterchangediv");
        $(".uninstall").attr("data-c","#meterOperation/oper?formid=remove");
        $(".stopmeter").attr("data-c","#meterOperation/oper?formid=stop");
        $(".returnmeter").attr("data-c","#meterOperation/oper?formid=resumed");
       
        $(".changemeter, .uninstall, .stopmeter, .returnmeter").on("click", function(){
        	
        	if($(this).hasClass("active"))return;
        	
        	$("#panel_box").cgetPart($(this),"",meterRightback);
        	
        });
       
    	$("#meterListTable").hideMask();
    }).DataTable({
           language: {
               url: 'js/dt-chinese.json'
           },
           lengthMenu: [18,20,20,20,20],
           dom: 'Bfrtip',
           select: true,
           serverSide:true,
          // ajax: 'expressjs/meter/json/meter_operation_demo.json',
           ajax: {
               url: 'meterOperation/list',
               type:'post',
               data: function(d){
               	$.each(searchData,function(i,k){
               		d[i] = k;
               	});
               	
               },
               dataSrc: 'data'
           },
           buttons: [
               { text: '换表', className: 'btn-sm changemeter business-tool-btn' },
               { text: '拆表',  className: 'btn-sm uninstall business-tool-btn' },
               { text: '停表', className: 'btn-sm stopmeter business-tool-btn' },
               { text: '复用', className: 'btn-sm returnmeter business-tool-btn' }
           ],
           responsive: true,
           /*返回数据为list，list里相应的属性值如下，对应列即可*/
           columns: [
                        {"data":"meterId",className:"none never"},
                        {"data":"meterStatus",className:"none never"},
                        {"data":"meterNo"},
						{"data":"meterModel.meterTypeDes"},
						{"data":"meterModel.manuId.manuName"},
						{"data":"meterModel.meterModel"},
						/*{"data":"meterStatus"}*/
						{"data":"meterStatusDes"}
					],
					//order: [[ 1, "asc" ]],
					columnDefs: [ {
					      "targets": 0,
					      "visible":false
					    }],
					 columnDefs: [ {
						      "targets": 1,
						      "visible":false
						    }]
       
    });
};

function meterTrSelected(v, i, index, t, json){
	console.info(index);
	 var lia=$('#meterListTable').DataTable().column(1).data()[$('#meterListTable tr.selected').index()];
	 if($("#installed").val()==lia){
		$(".changemeter, .uninstall, .stopmeter, .returnmeter").removeClass("disabled");
		
	  }else{
		$(".changemeter, .uninstall, .stopmeter, .returnmeter").addClass("disabled");
		$("#saveBtnDiv").addClass("hidden");
		$(".meter-hidden").addClass("hidden");
		
	}
	$("#panel_box").cgetPart($("#meterListTable"));
}
$(document).on("click",".cancelBtn",function(){
$(".changemeter, .uninstall, .stopmeter, .returnmeter").removeClass("active");
	$("#panel_box").cgetPart($("#meterListTable"));
});
function meter_search_ok(){
	searchData = $("#meteropt_meter").serializeJson();
	$("#meterListTable").cgetData();
}
/*var alertme = function(){
	alert(arguments[4].meterType);
}*/
function meterRightback(data){
	$("#saveBtnDiv").removeClass("hidden");
	loadRightback(data);
}
$("#addresstab").on("shown.bs.tab",function(){
	searchData4.addrStatusS=$("#addrStatusS").val();
	if ( !$.fn.DataTable.isDataTable('#addrListTable')) {
		mytable2 = $('#addrListTable').on( 'init.dt',function(){
	        if($("#addrListTable_filter").find(".asBtn").length<1){
	        	$("#addrListTable_filter").append('<a id="meterAddrPop" data-title="地址检索" class="asBtn btn btn-default btn-sm m-l-10" ><i class="fa fa-search-plus"></i></a>');
	        }
	        $("#meterAddrPop").on("click",function(){
				var popoptions = {
					title: '地址列表检索',
		    		content: '#meterOperation/searchpage',
		    		accept: 'addr_search_ok'
				};
				$("body").cgetPopup(popoptions);
			});
	        $("#panel_box").cgetPart($("#addrListTable"),"",addrRightBack);
	    	$("#addrListTable").hideMask();
	    	
	     }).DataTable({
		    	 language: {
	                 url: 'js/dt-chinese.json'
	             },
	            lengthMenu: [ 18, 20],
	            dom: 'Bfrtip',
	            select: true,
	            serverSide:true,
	           // ajax: 'expressjs/meter/json/meter_operation_demo2.json',
	            ajax: {
	                url: 'meterOperation/addrlist',
	                type:'post',
	                data: function(d){
	                	$.each(searchData4,function(i,k){
	                		d[i] = k;
	                	});
	                	
	                },
	                dataSrc: 'data'
	            },
	           //{ text: '装表', className: 'btn-sm installmeter business-tool-btn' }
	            buttons: [ ],
	            responsive: true,
	            /*返回数据为list，list里相应的属性值如下，对应列即可*/
	            columns: [
						/*	{"data":"addrCode"},*/
							{"data":"addrId",className:"none never"},
							{"data":"addrStatus",className:"none never"},
							{"data":"userNo"},
							{"data":"addrDes"},
							{"data":"addrStatusDes"}
						],
	            order: [[ 1, "asc" ]],
	            columnDefs: [ {
				      "targets": 0,
				      "visible":false
				    }],
			    columnDefs: [ {
					      "targets": 1,
					      "visible":false
					    }]
	     });
	}else{
		$("#panel_box").cgetPart($("#addrListTable"),"",function(){
			if($('#addrListTable').find("tr.selected").length>0){
				$('#addrListTable').find("tr.selected").removeClass("selected");
			}
			$('#addrListTable tbody tr:eq(0)').addClass("selected").click();
		});
	}
});
function addrRightBack(data){ 
	if($('#addrListTable').find("tr.selected").length<1){
		$('#addrListTable tbody tr:eq(0)').addClass("selected").click();
	}else{
		$('#addrListTable').find("tr.selected").click();	
	}
	

}
function addr_search_ok(){
	searchData4 = $("#meteropt_addr").serializeJson();
	searchData4.addrStatusS=$("#addrStatusS").val();
	$("#addrListTable").cgetData();
}


$("#metertab").on("shown.bs.tab",function(){
	if ( ! $.fn.DataTable.isDataTable('#meterListTable')) {
		handleMeterOperation();
	}else{
	        $("#panel_box").cgetPart($(".changemeter"),"",loadRightback);
	       // $(".changemeter").addClass("active");
	        $(".changemeter, .uninstall, .stopmeter, .returnmeter").on("click", function(){
	        	if($(this).hasClass("active")) return;
	        	$("#panel_box").cgetPart($(this),"",meterRightback);
	        });
		
	}
});

/*var myselected = function(v, i, index, t){
	console.info("v->" + v[0] + "/ i->" + i);
}*/

/*function myaccept(){
	alert("弹窗关闭");
}*/

var meterOperation = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleMeterOperation();
        }
    };
}();

$(document).on("click","#mrModelPop",function(){
   
   var url='#batcreate/meterTypePop';
   var formId=$(this).parents("form").attr("id");
    var meterModelPop = {
    		title: '表具型号选择',
    		content: url,
    		accept: 'meterModelBack('+formId+')'
    };
    $("body").cgetPopup(meterModelPop);
   
    });
function meterModelBack(formId){
	
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
	
	if($(".changemeter").hasClass("active")){
		if(undefined!=lia2){
			
			 $("#oldMeter").toggleEditState();
			 countIntoAmount();
		}else{
			 $("#oldMeter").toggleEditState(true);
			
		}
		
	}
	$("#"+formId).styleFit();
}


$(document).on("click",".install-save",function(){
	
    if ($("#installMeter").parsley().isValid()) {
      //防止多次提交
     if(response){
          response.abort();
      }
      var meterValid="";
      //表单序列化获取json字符串
      var data = $("#installMeter").serializeJson();
      data.remark=$("#meterRemark").val();
      var response=$.ajax({
          url: "meterOperation/installMeter",
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
  $("#installMeter").parsley().validate();

});
$(document).on("click",".remove-save",function(){
	
    if ($("#removeMeter").parsley().isValid()) {
      //防止多次提交
     if(response){
          response.abort();
      }
      var meterValid="";
      //表单序列化获取json字符串
      var data = $("#removeMeter").find("input, select, textarea").not(".optunit").serializeJson();
          data.optType=$("#" + formid+"-meter").attr("data-type");
         var dataStr= JSON.stringify(data);
      var response=$.ajax({
           url: "meterOperation/removeMeter",
          type: "post",
          contentType: "application/json;charset=UTF-8",
          data: dataStr,        
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
  $("#removeMeter").parsley().validate();

});

$(document).on("click",".saveBtn",function(){
    //开启表单验证
      if ($("#newMeter").parsley().isValid()&$("#oldMeter").parsley().isValid()) {
        //防止多次提交
       if(response){
            response.abort();
        }
        var meterValid="";
        //表单序列化获取json字符串
        var olddata = $("#oldMeter .new-meter").serializeJson();
        var newdata = $("#newMeter").serializeJson();
        var data= $.extend({}, olddata,newdata);     
           if($("input[name='transfer']:checked").val()=="1"){
        	   data.transfer=1;
             }
            data.arrearAmount=$("#arrearAmount").val();
            data.addAmount=$("#addAmount").val();
            data.startMeterNum=$("#startMeterNum1").val();
            data.initMeterNum=$("#initMeterNum1").val();
        var response=$.ajax({
            url: "meterOperation/changeMeter",
            type: "post",
            data: data,
            success: function (data) {
                if (data === "true") {
                    if (mytable !== "" && mytable !== undefined) {
                    	mytable.ajax.reload(function(){
                    		selectTr["meterListTable"] = trSData.i;
                        	$("#meterListTable tbody tr:eq(" + trSData.i + ")").select();
                        	$("#meterListTable_wrapper business-tool-btn").removeClass("active");
                    	});
                    	
                    }                  
               
            }
                saveBack(data);
           
        }
        
        }
        );
     
    }
    $("#oldMeter").parsley().validate();
    $("#newMeter").parsley().validate();
 
});


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
	    $("#panel_box").cgetPart($("#meterListTable"));
    } else {
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

var getDetailBack=function(data){
    if($(".changemeter").hasClass("active")){
    	if(trSData.json.meterType==$("#icCardMeter").val()){
         $("#icTransfer").removeClass("hidden");
         $("#newMeter").styleFit();
    	}
	      if(null!=data){
				//$('#oldMeter').deserialize(data.meter);
				$('#lastMeterNum').val(data.lastMeterNum);
				$('#oldMeterType').val(data.meter.meterType);
				$('#totalBuyCount').val(data.totalBuyCount);		
				$('#icHasAddAmount').val(data.hasAddAmount);
				$('#meterTypeDes1').val(data.meter.meterModel.meterTypeDes);
				$('#meterModelDes1').val(data.meter.meterModel.meterModel);
				$('#manuDes1').val(data.meter.meterModel.manuId.manuName);
				$('#oldMeterId').val(data.meter.meterId);
	
	}}else{
		  if(null!=data){
				 $(".ic-only").addClass("hidden");
	        	 if(data.icCardMeter!=undefined){
	             	 $(".ic-only").removeClass("hidden");
	             	$('#removeMeter').styleFit();
	             //	$('#removeMeter').deserialize(data.icCardMeter);
	             }
				 var selects = 	$('#removeMeter').find('select .disabled');
	                 selects.removeClass("disabled");
				//$('#removeMeter').deserialize(data.meter);
				$('#meterTypeDes').val(data.meter.meterModel.meterTypeDes);
				$('#meterModelDes').val(data.meter.meterModel.meterModel);
				$('#manuName').val(data.meter.meterModel.manuId.manuName);
				$('#meterModelr').val(data.meter.meterModel.meterModelId);
				$('#meterManu').val(data.meter.meterModel.manuId.manuId);
				 selects.addClass("disabled");
		}
	}
};
$(document).on("click","#selectModel1",function(){
	$("#reNewMeter").click();
});
$(document).on("click","#selectModel",function(){
	var formid=$(this).parents("form").attr("id");
	  var popOption = {
	    		title: '表具列表检索',
	    		content: '#meterOperation/getMeterPop',
	    		accept: 'getMeter('+formid+')'
	    };
	    $("body").cgetPopup(popOption);
});
function getMeter(formid){
	var meterId=$('#mrNoPopTable').DataTable().column(0).data()[$('#mrNoPopTable tr.selected').index()];
	$.ajax({
        url: "meterOperation/getMeterParam?meterId="+meterId,
        type: "post",
        dataType: 'json',
        success: function (data) {
        	 
        	 $(".ic-only").addClass("hidden");
            if (undefined!=data) {
            	if(undefined!=data.meter){
               $("#"+formid).find("input, select, textarea").not(".nochange").deserialize(data.meter);
               $('#meterTypeDes').val(data.meter.meterModel.meterTypeDes);
       		   $('#meterModelDes').val(data.meter.meterModel.meterModel);
       		   $('#manuName').val(data.meter.meterModel.manuId.manuName);
       		   $('#meterId').val(data.meter.meterId);
       		   $('#meterModelId').val(data.meter.meterModel.meterModelId);
       		   $("#oldMeter").toggleEditState();
			    countIntoAmount();
	
            	}else{
            		$("#reNewMeter").click();
            		 $("#oldMeter").toggleEditState(true);
            		
            	}
       		 if(undefined!=data.icCardMeter){
             	 $(".ic-only").removeClass("hidden");
             	$("#"+formid).styleFit();
             	$("#"+formid).deserialize(data.icCardMeter);
             }
       		
        }
           
           
       
    }
    
    }
    );
	
}

$(document).on("click","#addrListTable tr",function(){
	if($('#addrListTable tbody tr:eq(0)').hasClass("selected") && $(this).index()!=0){
		   $('#addrListTable tbody tr:eq(0)').removeClass("selected");
		}
	$(this).getDetail("meterOperation/getAddrDetail","installMeter","","");
});
function loadRightback(data){ 
	if($('#meterListTable').find("tr.selected").length<1){
		$('#meterListTable tbody tr:eq(0)').addClass("selected").click();
	}else{
		$('#meterListTable').find("tr.selected").click();	
	}
	

}
var mrNoPopOperation = function(meterStatus) {
	"use strict";
	 searchData1.meterStas=meterStatus;
	if(trSData.json.meterType==$("#icCardMeter").val()){
	    searchData1.meterTypes=	$("#icCardMeter").val()+","+$("#remoteTransMeter").val();
	}else if(trSData.json.meterType==$("#remoteTransMeter").val()){
		searchData1.meterTypes=	$("#remoteTransMeter").val();
	}else{
		searchData1.meterTypes="";
	}
	mrNoPopTable = $('#mrNoPopTable').on( 'init.dt',function(){
    $("#mrNoPopTable").hideMask();
    }).DataTable({
           language: {
               url: 'js/dt-chinese.json'
           },
           lengthMenu: [ 18, 20,20,20,20],
           dom: 'Bfrtip',
           select: true,
           searching: false,
           serverSide:true,
           ajax: {
               url: 'meterOperation/list',
               type:'post',
               data: function(d){
               	$.each(searchData1,function(i,k){
               		d[i] = k;
               	});
               	
               },
               dataSrc: 'data'
           },
           buttons: [
               { text: '查找', className: 'btn-sm selectBtn business-tool-btn' }
              
           ],
           responsive: true,
           /*返回数据为list，list里相应的属性值如下，对应列即可*/
           columns: [
                        {"data":"meterId",className:"none never"},
                        {"data":"meterNo"},
						{"data":"meterModel.meterTypeDes"},
						{"data":"meterModel.manuId.manuName"},
						{"data":"meterModel.meterModel"},
						{"data":"meterStatusDes"}
					],
					order: [[ 1, "asc" ]],
					columnDefs: [ {
					      "targets": 0,
					      "visible":false
					    }]
       
    });
};
//表具查询弹框点击表具类型后显示表具型号和厂商
$(document).on("change","#meterType",function(){
	if(""!=$(this).val()){
		$("#meteropt_meter").toggleEditState(true);
		var data={};
		$.ajax({
            url: "meterOperation/getMeterPart?meterType="+$(this).val(),
            type: "post",
            data: data,
            success: function (data) {
            	var dataJson=JSON.parse(data);
            	$("#meterModel").find("option").remove();
            	$("#meterModel").append('<option></option>');
            	$.each(dataJson.meterModel,function(key, val){
					$("#meterModel").append('<option value="' + val.meterModelId + '">' + val.meterModel + '</option>');
				});
            	$("#manuId").find("option").remove();
            	$("#manuId").append('<option></option>');
            	$.each(dataJson.manuId,function(key, val){           	
					$("#manuId").append('<option value="' + val.manuId + '">' + val.manuName + '</option>');
				});
    
           
        }
        
        }
        )
	}else{
		$("#meteropt_meter").toggleEditState();	
	}
});
//根据当前表读数计算量程
function countMeterRange(prenum)	
{  
  var lc=9;
  var x=(prenum+"").length;
     			if(x>=3)
     				{	
     				   	for(var i=0;i<x-1;i++)
     				   		{
     				   			lc=9+lc*10;
     				   		}
     				   		
     				 }else {
     				 	  return 99;
     				 }
     			
     		 
    //format float: 5.0000->5;5.1000->5.1;5.1200->5.12; 5.1230->5.123
   /* var tempNumber = parseInt(( lc * Math.pow(10,4)+0.5))/Math.pow(10,4);  
  	return tempNumber; */
     return lc;
} 
function countIntoAmount()
{
	//旧表类型
	var oldType=$("#oldMeterType").val();
	//获得最后表见数
	var lastMeterNum = $("#lastMeterNum").val()*1;
	//旧表量程
	var meterRange=$("#meterRange1").val();
	//拆表表见数
	var removeNum=$("#removeNum").val();
	//调节用气量
	var adjustAmount=$("#adjustAmount").val();
	var lc = 9;
	//首先对量程进行计算

	if (meterRange == 0||meterRange==""||null==meterRange) {
		lc = countMeterRange(lastMeterNum);
		meterRange = lc;
	}
	//是否过量程计算
	if($("input[name='pastRange']:checked").val()=="1"&&removeNum<lastMeterNum){
		readAmount = countPastRange(removeNum,lastMeterNum,meterRange);
	}else{
		readAmount = removeNum - lastMeterNum;
	}
	//表转入气量 = -(计费量)
	var intoAmount = -(Number(readAmount) + Number(adjustAmount));
	$("#usedAmount").val(readAmount);
	//如果旧表=ic卡表
	if(oldType == $("#icCardMeter").val())
	{
		//计量器具的换表补气量（已补气量）
		var icHasAddAmount=$('#icHasAddAmount').val();	
		//转入气量
		var meterInto = $("#newMeterInto").val();
		//累计购买量
		var totalBuyCount= $('#totalBuyCount').val();
		//已使用量，ic卡不抄表的情况下为0
		var hasUsedCount=0;
		
		//计算抄表记录中的本次用气量  
		var readAmount = 0;			
		var addAmount=0;
		if(intoAmount>0){
			addAmount=intoAmount;
		}else{
			hasUsedCount=Number(hasUsedCount)+(Math.abs(intoAmount)).toFixed(2);
		}
		//未补数量
		var wbql =Number(meterInto)+Number(addAmount)-icHasAddAmount*1;
		//转入新表量
		var icbiaoZrl = Number(totalBuyCount*1 - hasUsedCount*1) + Number(wbql*1);
		$("#startMeterNum1").val($("#initMeterNum1").val());
		if(icbiaoZrl > 0){
			$("#addAmount").val(icbiaoZrl);
			$("#arrearAmount").val(0.00);
		}else{
			$("#arrearAmount").val((Math.abs(icbiaoZrl)).toFixed(2));
			$("#addAmount").val(0.00);
		}
	}
	else if(oldType == $("#traditionMeter").val())
	{	
	    //普表
		if(intoAmount > 0){
		  $("#addAmount").val(intoAmount);
		  $("#arrearAmount").val(0.00);
		  $("#intoAmount").val(intoAmount);
		  var initMeterNum=$("#initMeterNum1").val();
		  var startMeterNum=Number(initMeterNum)+Number(intoAmount);
		  $("#startMeterNum1").val(startMeterNum);
	    }else{
		  $("#arrearAmount").val((Math.abs(intoAmount)).toFixed(2));
		  $("#addAmount").val(0.00);
		  $("#startMeterNum1").val($("#initMeterNum1").val());
	    }
		//
	}
}
/**
 * 
 * @param curnum 当前表见数
 * @param prenum 最后表见数
 * @param maxnum 量程
 * @returns {Number}
 */
function countPastRange(curnum,prenum,maxnum)	//过量程计算
{  
  var used=curnum-prenum;  
  if(used<0)
  {
  		if(maxnum>=prenum){  
  				used=Number(maxnum-prenum)+Number(curnum)+1;  
  		}
  		else if(maxnum<prenum){
     		  var lc=9;
     		  var x=(prenum+"").length; 							
     			if(x>=3)
     				{	
     				   	for(var i=0;i<x-1;i++)
     				   		{
     				   			lc=9+lc*10;
     				   		}
     				   		used=lc-prenum+curnum+1;
     				 }
     			else {
     				used=0;
     			}
     			}
     		 } 
  		
	//format float: 5.0000->5;5.1000->5.1;5.1200->5.12; 5.1230->5.123
    var tempNumber = parseInt(( used * Math.pow(10,4)+0.5))/Math.pow(10,4);  
  	return tempNumber;
  
}
//通过onblur事件改变欠费气量、应补气量等
function countAmount(){
/*	if(""==$("#meterModelDes").val()){
		 var popOption = {
				 title: "提示信息",
		         content: "请选择表型号",
		         accept: popClose,
		    };
		    $("body").cgetPopup(popOption);
		$("#removeNum").val("");
		$("#adjustAmount").val("");
	}else{*/
		countIntoAmount();
	/*}*/
}
//通过初始表见数onblur事件改变启用表见数
function countMeterNum(){
	var initNum=$("#initMeterNum1").val();
	var intoAmount=$("#intoAmount").val();
	var startNum=Number(initNum)+Number(intoAmount);
	 $("#startMeterNum1").val(startNum);
}

$(document).on("click","input[name='pastRange']",function(){
/*	if(""==$("#meterModelDes").val()){
		 var popOption = {
				 title: "提示信息",
		         content: "请选择表型号",
		         accept: popClose,
		    };
		    $("body").cgetPopup(popOption);
		$("input[name='pastRange']").attr("checked",'0');
	}else{*/
		countIntoAmount();
	/*}*/
});




