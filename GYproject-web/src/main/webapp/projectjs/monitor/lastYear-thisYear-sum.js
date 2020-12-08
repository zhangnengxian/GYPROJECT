var projectStatics = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	thisAndLastYearSum();//去年结转 本年新增
        	alreadyFinishedSum();//结单 转天然气
        	constructingProjectNum();//在建工程
        	managementOfficeNum();//每个施工处施工数
        	projectItemEcharts(); //每月立项
        }
    };
}();


var thisAndLastYearSum = function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/lastYearAndThisYearInfo',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	var data=JSON.parse(data);
	    	var lastYearNum=data[0].lastYearNum;
	    	var lastYearMoney=new Number(data[0].lastYearMoney/10000).toFixed(0)+"万";
	    	var thisYearNum=data[0].thisYearNum;
	    	var thisYearMoney=new Number(data[0].thisYearMoney/10000).toFixed(0)+"万";
	    	$("#lastYearSum").val(lastYearNum);
	    	$("#lastYearAmount").val(lastYearMoney);
	    	$("#thisYearSum").val(thisYearNum);
	    	$("#thisYearAmount").val(thisYearMoney);
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
};

var alreadyFinishedSum = function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/alreadyFinishedSum',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	var data=JSON.parse(data);
	    	var alreadyFinishNum=data[0].alreadyFinishNum;
	    	var terminationNum=data[0].terminationNum;
	    	$("#alreadyFinishNum").val(alreadyFinishNum);
	    	$("#terminationNum").val(terminationNum);
	    	
	    	
	    	
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
}

var constructingProjectNum=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/constructingProject',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	var data=JSON.parse(data);
	    	var conProjectNum=data[0].conProjectNum;
	    	var conProjectAmount=new Number(data[0].conProjectAmount/10000).toFixed(0)+"万";
	    	$("#conProjectNum").val(conProjectNum);
	    	$("#conProjectAmount").val(conProjectAmount);
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
}
var managementOfficeNum=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/queryManagementOffice',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	var data=JSON.parse(data);
	    	var firstNum=data[0].fitstNum;
	    	var secondNum=data[0].secondNum;
	    	var thirdNum=data[0].thirdNum;
	    	var fourthNum=data[0].fourthNum;
	    	var fifthNum=data[0].fifthNum;
            var sixthNum=data[0].sixthNum;
	    	if(firstNum!=undefined){
	    		//第一行
		    	$("#unitTable tr:eq(0)").children().eq(1).html(firstNum+"个");
	    	}else{
	    		firstNum=0;
	    		$("#unitTable tr:eq(0)").children().eq(1).html(0+"个");
	    	}

	    	if(secondNum!=undefined){
	    		//2
		    	$("#unitTable tr:eq(1)").children().eq(1).html(secondNum+"个");
	    	}else{
	    		secondNum=0;
	    		//2
		    	$("#unitTable tr:eq(1)").children().eq(1).html(0+"个");
	    	}

	    	if(thirdNum!=undefined){
	    		//3
		    	$("#unitTable tr:eq(2)").children().eq(1).html(thirdNum+"个");
	    	}else{
	    		//2
	    		thirdNum=0;
		    	$("#unitTable tr:eq(2)").children().eq(1).html(0+"个");
	    	}

	    	if(fourthNum!=undefined){
	    		//3
		    	$("#unitTable tr:eq(3)").children().eq(1).html(fourthNum+"个");
	    	}else{
	    		//2
	    		fourthNum=0;
		    	$("#unitTable tr:eq(3)").children().eq(1).html(0+"个");
	    	}

	    	if(fifthNum!=undefined){
	    		//3
		    	$("#unitTable tr:eq(4)").children().eq(1).html(fifthNum+"个");
	    	}else{
	    		fifthNum=0;
	    		//2
		    	$("#unitTable tr:eq(4)").children().eq(1).html(0+"个");
	    	}
            if(sixthNum!=undefined){
                //3
                $("#unitTable tr:eq(5)").children().eq(1).html(sixthNum+"个");
            }else{
                sixthNum=0;
                //2
                $("#unitTable tr:eq(5)").children().eq(1).html(0+"个");
            }
	    	constructionUnitNum(firstNum,secondNum,thirdNum,fourthNum,fifthNum,sixthNum);
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
}




var constructionUnitNum=function(firstNum,secondNum,thirdNum,fourthNum,fifthNum,sixthNum){
	option = {
		    /* color:['#51dfc6'], */
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    /*legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:['一处','二处','三处','四处','五处']
		    },*/
		    series: [
		        {
		            name:'工程类型占比',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '65%'],
		            label: {
 		                normal: {
 		                    textStyle: {
 		                        color: '#fff'
 		                    }
 		                }
 		            },
        			labelLine: {
 		                normal: {
 		                    lineStyle: {
 		                        color: '#fff'
 		                    },
 		                    smooth: 0.2,
 		                    length: 20,
 		                    length2: 15
 		                }
 		            },
		            data:[
		                {value:firstNum, name:'民用户工程', selected:true},
		                {value:secondNum, name:'公建户工程'},
		                {value:thirdNum, name:'改管工程'},
		                {value:fourthNum, name:'干线工程'},
		                /*{value:fifthNum, name:'智能表工程'},*/
						{value:sixthNum, name:'场站工程'}
		            ],
		            itemStyle: {
 		                normal: {
 		                    color: '#51dfc6',
 		                    shadowBlur: 50,
 		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
 		                }
 		            }
		        }
		    ]
		};
	var areaAmount = echarts.init(document.getElementById('leftEchartBottomBox'),'shine');
	areaAmount.setOption(option);
}

//每月立项数
var projectItemEcharts=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/acceptNum',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	var result=JSON.parse(data);
	    	var xAxisData = result.xAxisData;
        	var collectData = result.collectData;
        	//测试假数据
        	//collectData = new Array('16', '156', '50', '123', '14', '111', '145', '161', '149', '92', '74', '12');
        	var option = {
       			 color : ['#ff9824','#ffb980','#0dbacd','#1dcc9f'],
                   tooltip :false,
                  /* tooltip: {
                       trigger: 'axis'
                   },*/
                   grid: {
                       top:'20%',
                       left: '3%',
                       right: '4%',
                       bottom: '3%',
                       containLabel: true
                   },
                   xAxis: [{
                       type: 'category',
                      /* data: [{value:'1月',textStyle: {
       		    		            color: '#fff',
       		    		            fontSize:12
       		    		        }},'2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],*/
                       data:xAxisData,
                       splitLine: {
                           show: false
                       },
                       axisTick: {
                           alignWithLabel: true
                       },
                       axisLine: {
                           show: true,
                           lineStyle: {
                             color: "#fff",
                           }
                       },
                       axisLabel:{
                           show:true,
                           interval:2
                       }
                   }],
                   yAxis: [{
                       type: 'value',
                       name: '',
                          nameLocation: 'middle',
                          nameGap: 28,
                          nameTextStyle: {
                              fontSize: '10',
                          },
                       splitLine: {
                           show: false,
                       },
                       splitArea: {
                           show: false,
                                                   
                       },
                       axisLine: {
                           show: true,
                           lineStyle: {
                             color: "#fff",
                           }
                       },
                       min:0,
                       splitNumber:2
                   }],

                   series: [{
                       name: '立项数目',
                       type: 'bar',
                       label: {
                           normal: {
                               show: true,
                               position: 'top'
                           }
                       },
                       itemStyle: {
                           normal: {
                               color: '#51dfc6'
                           }
                       },
                       //data: [12,24,22,29,56,67,58,99,122,82,50,23],
                       data:collectData,//要取真实数据取消该注释
                   }]
               };
        	var areaAmount = echarts.init(document.getElementById('leftEchartTopBox'),'shine');
           areaAmount.setOption(option);	
        	
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
}


