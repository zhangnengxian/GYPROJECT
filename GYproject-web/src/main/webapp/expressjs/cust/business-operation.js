/*  
 *  @createtime  2016-04-06
 *  @Autoter ptt
 * 	客户业务操作记录
*/
var mytable2;
var mytable3;

//用于存放查询条件的全局变量
var searchData={};
var handleBusinessOperation = function() {
	
		mytable2 = $('#table2').on( 'init.dt',function(){
	         if($("#table2_filter").find(".asBtn").length<1){
	         	$("#table2_filter").append('<a id="asPop2" data-title="地址列表检索" data-post="#busiOpera/searchpage?type=addr"  data-type="addr"  class="asBtn btn btn-default btn-sm m-l-10" ><i class="fa fa-search-plus"></i></a>');
	         }
	         $(".openCust").attr("data-c","#panel_box=busiOpera/detailpage?type=openCust&addrId=");
	         $(".transCust").attr("data-c","#panel_box=busiOpera/detailpage?type=transCust&addrId=");
	         $(".delCust").attr("data-c","#panel_box=busiOpera/detailpage?type=delCust&addrId=");
	         $('#table2 tbody tr:eq(0)').click().addClass("selected");
	         $("#panel_box").cgetPart($("#table2"));
	     }).DataTable({
	            language: {
	                url: 'js/dt-chinese.json'
	            },
	            lengthMenu: [ 16, 20,20,20,20],
	            dom: 'Bfrtip',
	            select: true,
	            serverSide:true,
	            ajax: {
	                url: 'busiOpera/addrlist',
	                type:'post',
	                data: function(d){
	                   	$.each(searchData,function(i,k){
	                   		d[i] = k;
	                   	});
	                   	
	                },
	                dataSrc: 'data'
	            },
	            buttons: [
	                { text: '开户', className: 'btn-sm openCust business-tool-btn' },
	                { text: '过户',  className: 'btn-sm transCust business-tool-btn' },
	                { text: '销户', className: 'btn-sm delCust business-tool-btn' }
	            ],
	            responsive: true,
                /*返回数据为list，list里相应的属性值如下，对应列即可*/
	            columns: [
	                        {"data":"addrId",className:"none never"},
							{"data":"userNo"},
							{"data":"addrDes"},
							{"data":"addrStatusDes"}
						],
						order: [[ 1, "asc" ]],
						/*隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉）*/
						columnDefs: [{
						      "targets": 0,
						      "visible":false
						}]
	        
	     });
		 $('.tab-content .tab-pane').eq(0).addClass("active");
		 $("#table2").hideMask();
};

//点击地址列表初始化
$("#addresstab").on("shown.bs.tab",function(){
	$(".accout-box").removeClass("hidden");
	if ( !$.fn.DataTable.isDataTable('#table2')) {
		mytable2 = $('#table2').on( 'init.dt',function(){
			/*默认选中第一行*/
	         if($("#table2_filter").find(".asBtn").length<1){
	         	$("#table2_filter").append('<a id="asPop2" data-title="地址列表检索" data-post="#busiOpera/searchpage?type=addr"  data-type="addr"  class="asBtn btn btn-default btn-sm m-l-10" ><i class="fa fa-search-plus"></i></a>');
	         }
	         $("#panel_box").cgetPart($("#table2"));
	     }).DataTable({
		    	 language: {
	                 url: 'js/dt-chinese.json'
	             },
	            lengthMenu: [ 18, 20],
	            dom: 'Bfrtip',
	            select: true,
	            ajax: {
	                url: 'busiOpera/addrlist',
	                contentType:"application/json;charset=UTF-8",
	                dataSrc: '',
	                data:function(){
	                	return searchData;
	                }
	            },
	            buttons: [],
	            responsive: true,
	            /*返回数据为list，list里相应的属性值如下，对应列即可*/
	            columns: [
	                        {"data":"addrId",className:"none never"},
							{"data":"custNo"},
							{"data":"addrDes"},
							{"data":"householderName"},
							{"data":"openDate"},
							{"data":"addrStatusId.addrStatusDes"}
						],
						/*隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉）*/
						columnDefs: [ {
						      "targets": 0,
						      "visible":false
						    }]
	        
	     });
	}else{
		$("#panel_box").cgetPart($("#table2"));
	}
});

//点击业务操作记录表格初始化
$("#historytab").on("shown.bs.tab",function(){
	$(".accout-box").addClass("hidden");
	if ( ! $.fn.DataTable.isDataTable('#table3')) {
		
		mytable3 = $('#table3').on( 'init.dt',function(){
	         if($("#table3_filter").find(".asBtn").length<1){
	         	$("#table3_filter").append('<a id="asPop3" data-title="业务操作记录检索" data-post="#busiOpera/searchpage?type=hist"  data-type="hist" class="asBtn btn btn-default btn-sm m-l-10" ><i class="fa fa-search-plus"></i></a>');
	         }
	         $('#table3 tbody tr:eq(0)').click().addClass("selected");
	         $("#panel_box").cgetPart($("#table3"));
	     }).DataTable({
	    	 language: {
	                url: 'js/dt-chinese.json'
	         },
	         lengthMenu: [ 18, 30, 45, 60, 100 ],
	         dom: 'Bfrtip',
	         select: true,
	         buttons: [],
	         ajax: {
	                url: 'busiOpera/histlist',
	                contentType:"application/json;charset=UTF-8",
	                dataSrc: '',
	                data:function(){
	                	return searchData;
	                }
	            },
	         responsive: true,
	         columns: [
	  					{"data":"businessOptId",className:"none never"},
	  					{"data":"userNo"},
	  					{"data":"custName"},
						{"data":"optTypeS"},
						{"data":"optDate"}
					],
			/*隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉）*/
			columnDefs: [ {
			      "targets": 0,
			      "visible":false
			    }],
		    /*按下标为2的列进行降序排序*/
			order: [[ 0, 'asc' ]]
	     });
	}else{
		 
	}
});

$(document).on("click",".transCust,.delCust",function(e){
	$("#panel_box").cgetPart($(this));
});
$(document).on("click",".transCust",function(e){
	 var rindx = $('#table2 tr.selected').index();
	 $(".transCust").attr("data-c","#panel_box=busiOpera/detailpage?type=transCust&addrId="+mytable2.column(0).data()[rindx]);
	 $("#panel_box").cgetPart($(this));
});
$(document).on("click",".openCust",function(e){
	 var rindx = $('#table2 tr.selected').index();
	 $(".scbtn").removeClass("hidden");
	 $(".openCust").attr("data-c","#panel_box=busiOpera/detailpage?type=openCust&addrId="+mytable2.column(0).data()[rindx]);
	 $("#panel_box").cgetPart($(this));
	 
});

//查询弹出屏
$(document).on("click","#asPop1,#asPop2,#asPop3",function(e){
	var title = $(this).attr("data-title");
	var url = $(this).attr("data-post");
	var type = $(this).attr("data-type");
	var popOption = {
			title:'查询',
    		content: url,
    		accept: 'search_ok('+type+')'
    };
    $("body").cgetPopup(popOption);
});

//查询确定方法
var search_ok = function(type){
	var button = [];
	var col = [];
	var hidecol = [];
	console.log("search_ok:"+type);
	if(type == "addr"){
		 searchData = $("#searchForm_addr").serializeJson();
	     $("#table2").cgetData();
	}else if(type == "hist"){
		
		 searchData = $("#searchForm_hist").serializeJson();
	     $("#table3").cgetData();
	}
}

//关联操作按钮弹屏
$(document).on("click",".touchOperation",function(e){
	var title = $(this).attr("data-title");
	var url = $(this).attr("data-post");
	var type = $(this).attr("data-type");
	var atext = $(this).attr("data-text");
	var rindx = $('#table2').find(".selected").index();
	var m = $('#table2').DataTable().column(2).data()[rindx];
	if(m.indexOf("1402")>0){
		//IC卡表
		url = url+"&flag=IC";
	}else if(m.indexOf("1401")>0){
		//远传表
		url = url+"&flag=YQ";
	}
	var popOption = {
			title:title,
    		content: url,
    		accept: 'pop_ok('+type+')',
    		atext:title,
    		ctext:"放弃",
    		ccolor:"#607D8B",
    		acolor:"#607D8B"
    };
    $("body").cgetPopup(popOption);
});

//关联操作弹屏确定方法回调
var pop_ok = function(type){
	if(type == "backCard"){
		//退卡
		$(".backCard").removeClass("hidden");
		$("#delCustform").styleFit();
	}else if(type == "backMoney"){
		//退钱
		$(".backMoney").removeClass("hidden");
		$("#delCustform").styleFit();
	}else if(type == "buyGas"){
		//购气
		$(".buyGas").removeClass("hidden");
		$(".prestore").addClass("hidden");
		$(".printCard").addClass("hidden");
		$("#custform").styleFit();
	}else if(type == "printCard"){
		//制卡
		$(".printCard").removeClass("hidden");
		$(".buyGas").addClass("hidden");
		$(".prestore").addClass("hidden");
		$("#custform").styleFit();
	}else if(type == "prestore"){
		//预存
		$(".prestore").removeClass("hidden");
		$(".printCard").addClass("hidden");
		$(".buyGas").addClass("hidden");
		$("#custform").styleFit();
	}else if(type == "replenishment"){
		//补款
		$(".replenishment").removeClass("hidden");
		$("#delCustform").styleFit();
	}
}

/*监听行点击事件，与后台交互，查询相应的详述信息*/
$(document).on("click","#table2 tr",function(){
	
	$(".scbtn").addClass("hidden");
	$(".touchOperation").addClass("hidden");
	$("#panel_box").cgetPart($(this).parents("table"));
	//如果第一行有选中样式且当前行不是第一行
    if ($(this).parent().find('tr:eq(0)').hasClass("selected") && $(this).index() !== 0) {
        //移除第一行的选中样式
    	$(this).parent().find('tr:eq(0)').removeClass("selected");
    }
    var rindx = $(this).index();
    //选中行的地址状态
    var m = $(this).parents("table").DataTable().column(3).data()[rindx];
    if(m == "待用"){
    	$(".transCust").addClass("disabled");
        $(".delCust").addClass("disabled");
        $(".openCust").removeClass("disabled");
    }else if(m == "在用"){
    	$(".openCust").addClass("disabled");
    	$(".transCust").removeClass("disabled");
        $(".delCust").removeClass("disabled");
    }else if(m == "停用"){
    	$(".openCust").addClass("disabled");
    	$(".transCust").addClass("disabled");
        $(".delCust").addClass("disabled");
    }
    var custAddr = $(this).parents("table").DataTable().column(2).data()[rindx];
    $("#custAddr").val(custAddr);
});

$(document).on("click","#table3 tr",function(){
	//如果第一行有选中样式且当前行不是第一行
    if ($(this).parent().find('tr:eq(0)').hasClass("selected") && $(this).index() !== 0) {
        //移除第一行的选中样式
    	$(this).parent().find('tr:eq(0)').removeClass("selected");
    }
	var rindx = $(this).index();
    //选中行的地址状态
    var m = $(this).parents("table").DataTable().column(3).data()[rindx];
	console.log("业务操作类型："+m);
	
	if(m == "过户"){
		$("#table3").attr("data-c","#panel_box=busiOpera/detailpage?type=histTrans&addrId=");		
	}else{
		$("#table3").attr("data-c","#panel_box=busiOpera/detailpage?type=hist&addrId=");	
	}
	$("#panel_box").cgetPart($("#table3"));
	$(".touchOperation").addClass("hidden");
	/*var formName = $(this).parents("table").attr("data-form");
	var url = $(this).parents("table").attr("data-url");
    $(this).getDetail(url,formName,'');*/
	$("#printCard,#buyGas,#prestore,#replenishment,#backCard,#backMoney").addClass("hidden");
	$(".accout-box").addClass("hidden");
});

var saveType = "";
//保存方法
$(document).on("click",".save",function(){
	saveType = "";
	var type = $(this).attr("data-type");
	if(type == "openCust"){
		var custName = $("#custName").val();
		var custName2 = $("#custNamePers").val();
		if(custName == "" && custName2!=""){
			$("#custName").val(custName2);
		}else if(custName2 == "" && custName!=""){
			$("#custName2").val(custName);
		}
		saveType = "openCust";
		$("#custform").formSave("busiOpera/openCustSave",'table2',mytable2,saveBack,true);
	}else if(type == "transCust"){
		var custName = $("#custName").val();
		var custName2 = $("#custNamePers").val();
		if(custName == ""){
			$("#custName").val(custName2);
		}
		saveType = "transCust";
		$("#transCustform").formSave("busiOpera/transCustSave",'table2',mytable2,saveBack);
	}else if(type == "delCust"){
		saveType = "delCust";
		$("#delCustform").formSave("busiOpera/delCustSave",'table2',mytable2,saveBack);
	}
});
//保存成功后的回调函数
var saveBack = function(data){
	$(".touchOperation").addClass("hidden");
	if(data == "true" && saveType == "transCust"){
		$(".save,.cancel").addClass("hide");
		//数据保存成功
		$("#custform,#transCustform").toggleEditState(false);
		$("#custform,#transCustform").styleFit();
	}else if(data == "true" && saveType == "openCust"){
		$("#custform").toggleEditState(false);
		$("#custform").styleFit();
		$(".save,.cancel").addClass("hide");
		var meterModel = $("#meterModel").val();
		//以下用于判断表具型号
		if(meterModel=="12" || meterModel=="14"){
			//IC卡表
			$("#printCard,#buyGas").removeClass("hidden");
			$("#prestore,#replenishment,#backCard,#backMoney").addClass("hidden");
		}else if(meterModel == "130"){
			//预存气量表
			$("#buyGas").removeClass("hidden");
			$("#printCard,#prestore,#replenishment,#backCard,#backMoney").addClass("hidden");
		}else if(meterModel == "131"){
			//预存金额
			$("#prestore").removeClass("hidden");
			$("#printCard,#buyGas,#replenishment,#backCard,#backMoney").addClass("hidden");
		}else if(meterModel == "132"){
			//无预存
			 $("#printCard,#prestore,#backCard,#buyGas,#backMoney,#replenishment").addClass("hidden");
		}else{
			 $(".touchOperation").addClass("hidden");
		}
	}else if(data == "true" && saveType == "delCust"){
		$(".save,.cancel").addClass("hide");
		var meterModel = $("#meterModel").val();
		//以下用于判断表具型号
		if(meterModel=="12" || meterModel=="14"){
			 $("#backCard,#backMoney").removeClass("hidden");
			 $("#prestore,#replenishment,#printCard,#buyGas").addClass("hidden");
		}else if(meterModel == "130"){
			//预存气量表
			 $("#replenishment,#backMoney").removeClass("hidden");
			 $("#printCard,#prestore,#backCard,#buyGas").addClass("hidden");
		}else if(meterModel == "131"){
			//预存金额
			 $("#replenishment,#backMoney").removeClass("hidden");
			 $("#printCard,#prestore,#backCard,#buyGas").addClass("hidden");
		}else if(meterModel == "132"){
			//无预存
			$("#replenishment").removeClass("hidden");
			$("#printCard,#prestore,#backCard,#buyGas,#backMoney").addClass("hidden");
		}else if(meterModel=="11"){
			//普表
			 $("#replenishment").removeClass("hidden");
			 $("#printCard,#prestore,#backCard,#buyGas,#backMoney").addClass("hidden");
		}else{
			$("#prestore,#replenishment,#printCard,#buyGas,#backCard,#backMoney").addClass("hidden");
		}
	}else if(data == "true"){
		$(".save,.cancel").addClass("hide");
	}
	
}

//取消按钮
var cancel = function(type){
	$(".save").addClass("hide");
	$(".cancel").addClass("hide");
	/*if(type == "openCust"){
		//$("#custform").toggleEditState(false);
		$("#panel_box").cgetPart($("#table2"));
	}else{
		//$(".scbtn").addClass("hidden");
		$("#panel_box").cgetPart($("#table2"));
	}*/
	$("#panel_box").cgetPart($("#table2"));
}
//工商户与居民户切换时
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
	$("#transCustform").styleFit();
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

//地址列表详述查询成功回调函数
var addrDetailCBack = function(data){
	if(data!=undefined && data!=null && data!=""){
		var account = data.account;
		if(account!=null){
			//var balance = account.balance==null?0.00:account.balance;
		}
		$("#accountform").deserialize(account);
	}
}
//业务操作记录详述查询成功后的回调函数
var borDetailBack = function(data){
	if(data!=undefined && data!=null && data!=""){
		var custType = data.custType;
		var oldCustType = data.oldCustType;
		//工商户
		if(oldCustType =="1"){
			$(".oldunit").removeClass("hidden");
			$(".oldpersonal").addClass("hidden");
		}else{
			$(".oldunit").addClass("hidden");
			$(".oldpersonal").removeClass("hidden");
		}
		if(custType == "1"){
			$(".unit").removeClass("hidden");
			$(".personal").addClass("hidden");
		}else{
			$(".unit").addClass("hidden");
			$(".personal").removeClass("hidden");
		}
	}
}
var businessOperation = function () {
	"use strict";
    return {
        init: function () {
        	handleBusinessOperation();
        }
    };
}();