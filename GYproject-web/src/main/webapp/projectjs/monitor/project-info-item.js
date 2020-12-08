var projectInfoItem = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	thisYearShowNum();//第1个grid加载时显示
        	projTypeShowNum();//第2个grid加载时显示
        	projAreaShowNum();//第3个grid加载时显示
        	projStageShowNum();//第4个grid加载时显示
        	projApplySignShowNum();//第5个grid加载时显示
        	projAmountShowAmount();//第6个grid加载时显示
        }
    };
}();

//页面加载时第一个grid显示值
var thisYearShowNum=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/queryLastYearAndThisYearNum',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info('页面加载时第1个grid显示值------------------');
	    	console.info(data);
	    	var data=JSON.parse(data);
	    	var thisYearNum=data[0].thisYearNum;
	    	$("span.thisYearNum").text(thisYearNum);
	    	var thisYearMoney=data[0].thisYearMoney;
	    	$("span.thisYearMoney").text(new Number(thisYearMoney).toFixed(2));
	    	var lastYearNum=data[0].lastYearNum;
	    	$("span.lastYearNum").text(lastYearNum);
	    	var lastYearMoney=data[0].lastYearMoney;
	    	$("span.lastYearMoney").text(new Number(lastYearMoney).toFixed(2));
	    	
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
	
}

//页面加载时第2个grid显示值
var projTypeShowNum=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/queryProjectTypeNum',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info('页面加载时第2个grid显示值------------------');
	    	console.info(data);
	    	var json=JSON.parse(data);
	    	for(var i=0;i<json.length;i++){
	    		if(json[i].type=="民用"){
	    			$("span.civilianNum").text(json[i].thisYearNum);
	    		}
	    		if(json[i].type=="公用"){
	    			$("span.publicNum").text(json[i].thisYearNum);
	    		}
	    		if(json[i].type=="车用"){
	    			$("span.vehicleUsed").text(json[i].thisYearNum);
	    		}
	    	}
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
}
//页面加载时第3个grid显示值
var projAreaShowNum=function(){
	 $.ajax({
		    type: 'POST',
		    url: '/projTS/queryProjectAreaNum',
		    contentType: "application/json;charset=UTF-8",
		    data: '',
		    success: function (data) {
		    	console.info('页面加载时第3个grid显示值------------------');
		    	console.log(data);
		    	var json=JSON.parse(data);
		    	for(var i=0;i<json.length;i++){
		    		if(json[i].area=="天山区"){
		    			$("span.area0").text(json[i].acceptNum);
		    		}
		    		if(json[i].area=="沙区"){
		    			$("span.area1").text(json[i].acceptNum);
		    		}
		    		if(json[i].area=="新市区"){
		    			$("span.area2").text(json[i].acceptNum);
		    		}
		    		if(json[i].area=="水区"){
		    			$("span.area3").text(json[i].acceptNum);
		    		}
		    		if(json[i].area=="头区"){
		    			$("span.area4").text(json[i].acceptNum);
		    		}
		    	}
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		        console.warn("ajax queryScaleDetail...fail");
		    }
	 })
};

//页面加载时第4个grid显示值
var projStageShowNum=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/queryProjectStageNum',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info('页面加载时第4个grid显示值------------------');
	    	console.log(data);
	    	var json=JSON.parse(data);
	    	$("span.acceptNum").text(json[0].acceptNum);
	    	$("span.constructingNum").text(json[0].constructingNum);
	    	$("span.trunGasNum").text(json[0].trunGasNum);
	    	$("span.chargebackNum").text(json[0].chargebackNum);
	    	
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	})
};

//页面加载时第5个grid显示值
var projApplySignShowNum=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/queryProjectAcceptAndContractNum',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info('页面加载时第5个grid显示值------------------');
	    	console.log(data);
	    	var json=JSON.parse(data);
	    	$("span.acceptTotalNum").text(json[0].acceptNum);
	    	$("span.signTotalNum").text(json[0].contractNum);
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	})
};

//页面加载时第6个grid显示值
var projAmountShowAmount=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/querySignAndAladyCharge',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info('页面加载时第6个grid显示值------------------');
	    	console.log(data);
	    	var json=JSON.parse(data);
	    	$("span.alreadyChargeMoney").text(new Number(json[0].alreadyChargeMoney).toFixed(2));
	    	$("span.haveNotCharge").text(new Number(json[0].shouldChargeMoney-json[0].alreadyChargeMoney).toFixed(2));
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	})
}
