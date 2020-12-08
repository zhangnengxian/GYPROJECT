var projNoStates = function(ec){
    var projNoStates = ec.init(document.getElementById('projNoStates')),
    zrColor = require('zrender/tool/color'),
    colorList = [
      '#ff69b4','#8bb1f6','#a38ff7','#ffc65c','#6494ed','#54ebdb','#b9aaf7','#ffdfa5'
    ],
    itemStyle = {
        normal: {
            color: function(params) {
              if (params.dataIndex < 0) {
                return zrColor.lift(
                  colorList[colorList.length - 1], params.seriesIndex * 0.1
                );
              }
              else {
                return zrColor.lift(
                  colorList[params.dataIndex], params.seriesIndex * 0.1
                );
              }
            }
        }
    };
    $.ajax({
        type: 'POST',
        url: 'projectStatisticalAnalysis/paymentNum',
        success: function (data) {
        	var result = JSON.parse(data);
        	var xAxisData = result.xAxisData;
        	var collectData = result.collectData;
        	var payData = result.payData;
        	var option = {
            	    title : {
            	        text: '',
            	        subtext: ''
            	    },
            	    tooltip : {
            	        trigger: 'axis'
            	    },
            	    legend: {
            	    	itemGap: 14,
            	        data:['实收','实付']
            	    },
            	    toolbox: {
            	        show : false,
            	        feature : {
            	            dataView : {show: true, readOnly: false},
            	            magicType : {show: true, type: ['line', 'bar']},
            	            restore : {show: true},
            	            saveAsImage : {show: true}
            	        }
            	    },
            	    calculable : true,
            	    xAxis : [
            	        {
            	            type : 'category',
            	            data : xAxisData
            	        }
            	    ],
            	    yAxis : [
            	        {
            	            type : 'value',
            	            name:'金额（元）'
            	        }
            	    ],
            	    series : [
            	        {
            	            name:'实收',
            	            type:'bar',
            	            data:collectData,
            	            markPoint : {
            	                data : [
            	                    {type : 'max', name: '最大值'},
            	                    {type : 'min', name: '最小值'}
            	                ]
            	            }
            	        },
            	        {
            	            name:'实付',
            	            type:'bar',
            	            data:payData,
            	            markPoint : {
            	            	data : [
                	                    {type : 'max', name: '最大值'},
                	                    {type : 'min', name: '最小值'}
                	                ]
            	            }
            	        }
            	    ]
            	};

            projNoStates.setOption(option);
            $("#projNoStates").hideMask();
        }
    });
    
},
deduction = function(ec){
    var deduction = ec.init(document.getElementById('deduction'));
    $.ajax({
        type: 'POST',
        url: 'projectStatisticalAnalysis/everyAreaProjectNum',
        success: function (data) {
        	console.log(data);
        	var result = JSON.parse(data);
        	var series = result.series;
        	console.log(series);
        	var legendData = result.legendData;
        	console.log(legendData);
        	var yAxisData = result.yAxisData;
        	console.log(yAxisData);
        	var option = {
            	    tooltip : {
            	        trigger: 'axis',
            	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            	        }
            	    },
            	    legend: {
            	    	itemGap: 14,
            	        data: legendData
            	    },
            	    grid: {
            	    	x: 50,
                        y: 30,
                        y2: 30,
                        x2: 20,
            	        containLabel: true
            	    },
            	    xAxis:  {
            	        type: 'value' 
            	    },
            	    yAxis: {
            	        type: 'category',
            	        data: yAxisData
            	    },
            	    series: series
            };
        	deduction.setOption(option);
            $("#deduction").hideMask();
        }
    });
    /*var option = {
    	    tooltip : {
    	        trigger: 'axis',
    	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    	        }
    	    },
    	    legend: {
    	        data: ['立项', '设计','预算','合同','计划','分包','竣工','结算']
    	    },
    	    grid: {
    	    	x: 50,
                y: 30,
                y2: 30,
                x2: 20,
    	        containLabel: true
    	    },
    	    xAxis:  {
    	        type: 'value' 
    	    },
    	    yAxis: {
    	        type: 'category',
    	        data: ['新市区','水区','沙区','天山区','头区']
    	    },
    	    series: [
    	        {
    	            name: '立项',
    	            itemStyle:{
    	                    normal: {
    	                        color: '#ff7e50'
    	                    }
    	                },
    	            type: 'bar',
    	            stack: '总量',
    	            data: [22, 33, 28, 25, 23]
    	        },
    	        {
    	            name: '设计',
    	            itemStyle:{
	                    normal: {
	                        color: '#8bb1f6'
	                    }
	                },
    	            type: 'bar',
    	            stack: '总量',
    	            data: [15, 20, 13, 23, 19]
    	        },
    	        {
    	            name: '预算',
    	            itemStyle:{
	                    normal: {
	                        color: '#a38ff7'
	                    }
	                },
    	            type: 'bar',
    	            stack: '总量',
    	            data: [12, 18, 19, 24, 19]
    	        },
    	        {
    	            name: '合同',
    	            itemStyle:{
	                    normal: {
	                        color: '#ffc65c'
	                    }
	                },
    	            type: 'bar',
    	            stack: '总量',
    	            data: [25, 22, 21, 25, 22]
    	        },
    	        {
    	            name: '计划',
    	            itemStyle:{
	                    normal: {
	                        color: '#6494ed'
	                    }
	                },
    	            type: 'bar',
    	            stack: '总量',
    	            data: [22, 33, 21, 29, 30]
    	        },
    	        {
    	            name: '分包',
    	            itemStyle:{
	                    normal: {
	                        color: '#54ebdb'
	                    }
	                },
    	            type: 'bar',
    	            stack: '总量',
    	            data: [42, 33, 21, 21, 39]
    	        },
    	        {
    	            name: '竣工',
    	            itemStyle:{
	                    normal: {
	                        color: '#b9aaf7'
	                    }
	                },
    	            type: 'bar',
    	            stack: '总量',
    	            data: [23, 22, 15, 21, 16]
    	        },
    	        {
    	            name: '结算',
    	            itemStyle:{
	                    normal: {
	                        color: '#ffdfa5'
	                    }
	                },
    	            type: 'bar',
    	            stack: '总量',
    	            data: [12, 16, 15, 18, 14]
    	        }
    	    ]
    	};
    deduction.setOption(option);
    $("#deduction").hideMask();*/
},
deductionPerMonth = function(ec){
    var deductionPerMonth = ec.init(document.getElementById('deductionPerMonth'));
    $.ajax({
        type: 'POST',
        url: 'projectStatisticalAnalysis/backReasonProjectNum',
        success: function (data) {
        	console.log(data);
        	var result = JSON.parse(data);
        	var seriesData = result.seriesData;
        	var legendData = result.legendData;
        	var option = {
        		    title : {
        		        text: '',
        		        subtext: '',
        		        x:'center'
        		    },
        		    tooltip : {
        		        trigger: 'item',
        		        formatter: "{a} <br/>{b} : {c} ({d}%)"
        		    },
        		    legend: {
        		        orient : 'vertical',
        		        x : 'left',
        		        itemGap: 14,
        		        data:legendData
        		    },
        		    toolbox: {
        		        show : false,
        		        feature : {
        		            mark : {show: true},
        		            dataView : {show: true, readOnly: false},
        		            magicType : {
        		                show: true, 
        		                type: ['pie', 'bar'],
        		                option: {
        		                    funnel: {
        		                        x: '25%',
        		                        width: '50%',
        		                        funnelAlign: 'left',
        		                        max: 1548
        		                    }
        		                }
        		            },
        		            restore : {show: true},
        		            saveAsImage : {show: true}
        		        }
        		    },
        		    calculable : true,
        		    series : [
        		        {
        		            name:'访问来源',
        		            type:'pie',
        		            radius : '55%',
        		            center: ['50%', '60%'],
        		            data:seriesData
        		        }
        		    ]
        		};
           
            deductionPerMonth.setOption(option);
            $("#deductionPerMonth").hideMask();
        }
    });
},
/**
 * 初始化工程列表
 */
projectStatisticalAnalysis = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	require.config({
	            paths: {
	                echarts: 'assets/plugins/echarts/build/dist/'
	            }
	        });
	        require(
	            [
	                'echarts',
	                'echarts/chart/bar',
	                'echarts/chart/line',
	                'echarts/chart/pie'
	            ],
	            function (ec) {
	            	projNoStates(ec);
	            	deduction(ec);
	            	deductionPerMonth(ec);
	    	        $(".echart-box").off("chartrerender").on("chartrerender",function(){
	    	        	projNoStates(ec);
		            	deduction(ec);
		            	deductionPerMonth(ec);
	    	        });
	            }
	        );
        }
    };
}();