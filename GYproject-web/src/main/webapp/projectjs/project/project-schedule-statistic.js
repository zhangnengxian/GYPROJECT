var projNoStates = function(ec){
    var projNoStates = ec.init(document.getElementById('projNoStates')),
    zrColor = require('zrender/tool/color'),
    colorList = [
      '#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0',
      '#ff7f50','#87cefa','#da70d6','#32cd32','#6495ed'
    ],
    itemStyle = {
        normal: {
            color: function(params) {
              if (params.dataIndex < 0) {
                // for legend
                return zrColor.lift(
                  colorList[colorList.length - 1], params.seriesIndex * 0.1
                );
              }
              else {
                // for bar
                return zrColor.lift(
                  colorList[params.dataIndex], params.seriesIndex * 0.1
                );
              }
            }
        }
    };
    projNoStates.setOption({
        timeline:{
            data:[
                '2015-07','2015-08','2015-09','2015-10','2015-11','2015-12',
                '2016-01','2016-02','2016-03','2016-04','2016-05','2016-06'
            ],
            label : {
                formatter : function(s) {
                    return s.slice(0, 7);
                }
            },
            autoPlay : true,
            playInterval : 1000
        },
        options:[{
	        title: {
	            text: ''
	        },
	        tooltip: {
	            trigger: 'axis',
	            backgroundColor: 'rgba(255,255,255,0.7)',
	            axisPointer: {
	                type: 'shadow'
	            },
	            formatter: function(params) {
	                // for text color
	                var color = colorList[params[0].dataIndex];
	                var res = '<div style="color:' + color + '">';
	                res += '<strong>' + params[0].name + '数量</strong>'
	                for (var i = 0, l = params.length; i < l; i++) {
	                    res += '<br/>' + params[i].seriesName + ' : ' + params[i].value 
	                }
	                res += '</div>';
	                return res;
	            }
	        },
	        legend: {
	            x: 'right',
	            data:['民用','公用','车用']
	        },
	        toolbox: {
	            show: true,
	            orient: 'vertical',
	            y: 'center',
	            feature: {
	                mark: {show: true},
	                dataView: {show: true, readOnly: false},
	                restore: {show: true},
	                saveAsImage: {show: true}
	            }
	        },
	        calculable: true,
	        grid: {
	        	x: 60,
	            y: 50,
	            y2: 80,
	            x2: 40
	        },
	        xAxis: [
	            {
	                type: 'category',
	                itemStyle: itemStyle,
	                data: ['立项', '设计', '预算', '施工', '验收', '结算']
	            }
	        ],
	        yAxis: [
	            {
	                type: 'value'
	            }
	        ],
	        series: [
	            {
	                name: '民用',
	                type: 'bar',
	                data: [123,4,5,3,1,2]
	            },
	            {
	                name: '公用',
	                type: 'bar',
	                data: [3,4,5,3,1,2]
	            },
	            {
	                name: '车用',
	                type: 'bar',
	                data: [3,4,5,3,1,2]
	            }
	        ]
        },{
	        title: {
	            text: '2015年8月份工程项目数量统计'
	        },
	        series: [
 	            {
 	                name: '民用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '公用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '车用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            }
 	        ]
        },{
	        title: {
	            text: '2015年9月份工程项目数量统计'
	        },
	        series: [
 	            {
 	                name: '民用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '公用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '车用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            }
 	        ]
        },{
	        title: {
	            text: '2015年10月份工程项目数量统计'
	        },
	        series: [
 	            {
 	                name: '民用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '公用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '车用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            }
 	        ]
        },{
	        title: {
	            text: '2015年11月份工程项目数量统计'
	        },
	        series: [
 	            {
 	                name: '民用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '公用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '车用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            }
 	        ]
        },{
	        title: {
	            text: '2015年12月份工程项目数量统计'
	        },
	        series: [
 	            {
 	                name: '民用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '公用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '车用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            }
 	        ]
        },{
	        title: {
	            text: '2016年1月份工程项目数量统计'
	        },
	        series: [
 	            {
 	                name: '民用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '公用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '车用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            }
 	        ]
        },{
	        title: {
	            text: '2016年2月份工程项目数量统计'
	        },
	        series: [
 	            {
 	                name: '民用',
 	                type: 'bar',
 	                data: [0,4,7,3,1,2]
 	            },
 	            {
 	                name: '公用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '车用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            }
 	        ]
        },{
	        title: {
	            text: '2016年3月份工程项目数量统计'
	        },
	        series: [
 	            {
 	                name: '民用',
 	                type: 'bar',
 	                data: [63,24,35,33,26,32]
 	            },
 	            {
 	                name: '公用',
 	                type: 'bar',
 	                data: [52,28,25,53,21,42]
 	            },
 	            {
 	                name: '车用',
 	                type: 'bar',
 	                data: [3,4,3,3,6,2]
 	            }
 	        ]
        },{
	        title: {
	            text: '2016年4月份工程项目数量统计'
	        },
	        series: [
 	            {
 	                name: '民用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '公用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '车用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            }
 	        ]
        },{
	        title: {
	            text: '2016年5月份工程项目数量统计'
	        },
	        series: [
 	            {
 	                name: '民用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '公用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            },
 	            {
 	                name: '车用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,2]
 	            }
 	        ]
        },{
	        title: {
	            text: ''
	        },
	        series: [
 	            {
 	                name: '民用',
 	                type: 'bar',
 	                data: [3,4,5,3,1,1] 
 	            },
 	            {
 	                name: '公用',
 	                type: 'bar',
 	                data: [3,4,1,3,1,2]
 	            },
 	            {
 	                name: '车用',
 	                type: 'bar',
 	                data: [1,6,5,3,1,2]
 	            }
 	        ]
        }]
    });
    $("#projNoStates").hideMask();
},
deduction = function(ec){
    var deduction = ec.init(document.getElementById('deduction')),
    zrColor = require('zrender/tool/color'),
    colorList = [
      '#ff7f50','#87cefa','#da70d6','#32cd32','#6495ed',
      '#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0'
    ],
    itemStyle = {
        normal: {
            color: function(params) {
              if (params.dataIndex < 0) {
                // for legend
                return zrColor.lift(
                  colorList[colorList.length - 1], params.seriesIndex * 0.1
                );
              }
              else {
                // for bar
                return zrColor.lift(
                  colorList[params.dataIndex], params.seriesIndex * 0.1
                );
              }
            }
        }
    };
    deduction.setOption({
        title : {
            text: ''
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        grid: {
        	x: 90,
            y: 50,
            y2: 40,
            x2: 40
        },
        xAxis : [
            {
                type : 'value',
                boundaryGap : [0, 0.01]
            }
        ],
        yAxis : [
            {
                type : 'category',
                data : ['沟槽施工','阀井施工','报警工程','竣工资料','冬季施工','一般施工问题','PE管施工','消防安全','钢管防腐','施工准备']
            }
        ],
        series : [
            {
                name:'扣分次数',
                type:'bar',
	            itemStyle: itemStyle,
                data:[4, 7, 14, 18, 21, 28,31,45,51,61]
            }
        ]
    });
    $("#deduction").hideMask();
},
deductionPerMonth = function(ec){
    var deductionPerMonth = ec.init(document.getElementById('deductionPerMonth'));
    deductionPerMonth.setOption({
        title : {
            text: ''
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
        	x: 'right',
            data:['施工管理一部','施工管理二部','施工管理三部','施工管理四部','施工管理五部']
        },
        toolbox: {
            show : true,
            orient: 'vertical',
            y: 'center',
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : false,
        grid: {
        	x: 50,
            y: 50,
            y2: 40,
            x2: 40
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['2015-07','2015-08','2015-09','2015-10','2015-11','2015-12','2016-01','2016-02','2016-03','2016-04','2016-05','2016-06']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'施工管理一部',
                type:'line',
                stack: '总量',
                data:[11, 0, 5, 2, 10, 1, 10, 20, 10, 10, 3, 12]
            },
            {
                name:'施工管理二部',
                type:'line',
                stack: '总量',
                data:[51, 41, 49, 57, 40, 51, 35, 58, 45, 50, 42, 52]
            },
            {
                name:'施工管理三部',
                type:'line',
                stack: '总量',
                data:[16, 14, 20, 40, 18, 19, 27, 30, 20, 19, 20, 18]
            },
            {
                name:'施工管理四部',
                type:'line',
                stack: '总量',
                data:[11, 20, 13, 21, 14, 22, 23, 26, 18, 24, 19, 16]
            },
            {
                name:'施工管理五部',
                type:'line',
                stack: '总量',
                data:[31, 44, 36, 23, 35, 31, 50, 28, 26, 19, 20, 28]
            }
        ]
    });
    $("#deductionPerMonth").hideMask();
},
/**
 * 初始化工程列表
 */
projectScheduleStatistic = function () {
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
	                'echarts/chart/line'
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