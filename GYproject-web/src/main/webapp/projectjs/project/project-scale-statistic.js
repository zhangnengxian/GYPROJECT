var projNumByYear = function(ec){
    var projNumByYear = ec.init(document.getElementById('projNumByYear')),
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
    projNumByYear.setOption({
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
	            text: '2015年7月份工程项目数量统计'
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
	            data:['立项','开工','验收','结算']
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
	                data: ['民用', '公用', '车用']
	            }
	        ],
	        yAxis: [
	            {
	                type: 'value'
	            }
	        ],
	        series: [
	            {
	                name: '立项',
	                type: 'bar',
	                data: [80,56,32]
	            },
	            {
	                name: '开工',
	                type: 'bar',
	                data: [235,167,80]
	            },
	            {
	                name: '验收',
	                type: 'bar',
	                data: [73,69,40]
	            },
	            {
	                name: '结算',
	                type: 'bar',
	                data: [73,51,40]
	            }
	        ]
        },{
	        title: {
	            text: '2015年8月份工程项目数量统计'
	        },
	        series: [
 	            {
	                name: '立项',
	                type: 'bar',
	                data: [80,56,32]
	            },
	            {
	                name: '开工',
	                type: 'bar',
	                data: [212,189,80]
	            },
	            {
	                name: '验收',
	                type: 'bar',
	                data: [73,51,40]
	            },
	            {
	                name: '结算',
	                type: 'bar',
	                data: [54,51,40]
	            }
 	        ]
        },{
	        title: {
	            text: '2015年9月份工程项目数量统计'
	        },
	        series: [
 	            {
	                name: '立项',
	                type: 'bar',
	                data: [103,97,32]
	            },
	            {
	                name: '开工',
	                type: 'bar',
	                data: [235,231,212]
	            },
	            {
	                name: '验收',
	                type: 'bar',
	                data: [54,51,40]
	            },
	            {
	                name: '结算',
	                type: 'bar',
	                data: [73,69,57]
	            }
 	        ]
        },{
	        title: {
	            text: '2015年10月份工程项目数量统计'
	        },
	        series: [
 	            {
	                name: '立项',
	                type: 'bar',
	                data: [80,56,32]
	            },
	            {
	                name: '开工',
	                type: 'bar',
	                data: [212,189,80]
	            },
	            {
	                name: '验收',
	                type: 'bar',
	                data: [73,51,40]
	            },
	            {
	                name: '结算',
	                type: 'bar',
	                data: [54,51,40]
	            }
 	        ]
        },{
	        title: {
	            text: '2015年11月份工程项目数量统计'
	        },
	        series: [
 	            {
	                name: '立项',
	                type: 'bar',
	                data: [103,97,32]
	            },
	            {
	                name: '开工',
	                type: 'bar',
	                data: [235,231,212]
	            },
	            {
	                name: '验收',
	                type: 'bar',
	                data: [54,51,40]
	            },
	            {
	                name: '结算',
	                type: 'bar',
	                data: [73,69,57]
	            }
 	        ]
        },{
	        title: {
	            text: '2015年12月份工程项目数量统计'
	        },
	        series: [
 	            {
	                name: '立项',
	                type: 'bar',
	                data: [80,56,32]
	            },
	            {
	                name: '开工',
	                type: 'bar',
	                data: [212,189,80]
	            },
	            {
	                name: '验收',
	                type: 'bar',
	                data: [73,51,40]
	            },
	            {
	                name: '结算',
	                type: 'bar',
	                data: [54,51,40]
	            }
 	        ]
        },{
	        title: {
	            text: '2016年1月份工程项目数量统计'
	        },
	        series: [
 	            {
	                name: '立项',
	                type: 'bar',
	                data: [103,97,32]
	            },
	            {
	                name: '开工',
	                type: 'bar',
	                data: [235,231,212]
	            },
	            {
	                name: '验收',
	                type: 'bar',
	                data: [54,51,40]
	            },
	            {
	                name: '结算',
	                type: 'bar',
	                data: [73,69,57]
	            }
 	        ]
        },{
	        title: {
	            text: '2016年2月份工程项目数量统计'
	        },
	        series: [
 	            {
	                name: '立项',
	                type: 'bar',
	                data: [80,56,32]
	            },
	            {
	                name: '开工',
	                type: 'bar',
	                data: [212,189,80]
	            },
	            {
	                name: '验收',
	                type: 'bar',
	                data: [73,51,40]
	            },
	            {
	                name: '结算',
	                type: 'bar',
	                data: [54,51,40]
	            }
 	        ]
        },{
	        title: {
	            text: '2016年3月份工程项目数量统计'
	        },
	        series: [
 	            {
	                name: '立项',
	                type: 'bar',
	                data: [103,97,32]
	            },
	            {
	                name: '开工',
	                type: 'bar',
	                data: [235,231,212]
	            },
	            {
	                name: '验收',
	                type: 'bar',
	                data: [54,51,40]
	            },
	            {
	                name: '结算',
	                type: 'bar',
	                data: [73,69,57]
	            }
 	        ]
        },{
	        title: {
	            text: '2016年4月份工程项目数量统计'
	        },
	        series: [
 	            {
	                name: '立项',
	                type: 'bar',
	                data: [80,56,32]
	            },
	            {
	                name: '开工',
	                type: 'bar',
	                data: [212,189,80]
	            },
	            {
	                name: '验收',
	                type: 'bar',
	                data: [73,51,40]
	            },
	            {
	                name: '结算',
	                type: 'bar',
	                data: [54,51,40]
	            }
 	        ]
        },{
	        title: {
	            text: '2016年5月份工程项目数量统计'
	        },
	        series: [
 	            {
	                name: '立项',
	                type: 'bar',
	                data: [103,97,32]
	            },
	            {
	                name: '开工',
	                type: 'bar',
	                data: [235,231,212]
	            },
	            {
	                name: '验收',
	                type: 'bar',
	                data: [54,51,40]
	            },
	            {
	                name: '结算',
	                type: 'bar',
	                data: [73,69,57]
	            }
 	        ]
        },{
	        title: {
	            text: '2016年6月份工程项目数量统计'
	        },
	        series: [
 	            {
	                name: '立项',
	                type: 'bar',
	                data: [80,56,32]
	            },
	            {
	                name: '开工',
	                type: 'bar',
	                data: [212,189,80]
	            },
	            {
	                name: '验收',
	                type: 'bar',
	                data: [73,51,40]
	            },
	            {
	                name: '结算',
	                type: 'bar',
	                data: [54,51,40]
	            }
 	        ]
        }]
    });
    $("#projNumByYear").hideMask();
},
projOnConstruction = function(ec){
    var projOnConstruction = ec.init(document.getElementById('projOnConstruction'));
    projOnConstruction.setOption({
        title : {
            text: '在建工程数量统计'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:[
                '民用类开工','公用类开工','车用类开工','',
                '民用类验收','公用类验收','车用类验收','',
                '民用类结算','公用类结算','车用类结算'
            ]
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        grid: {y: 70, y2:30, x2:20},
        xAxis : [
            {
                type : 'category',
                data : ['工程一部','工程二部','工程三部','工程四部','工程五部']
            },
            {
                type : 'category',
                axisLine: {show:false},
                axisTick: {show:false},
                axisLabel: {show:false},
                splitArea: {show:false},
                splitLine: {show:false},
                data : ['工程一部','工程二部','工程三部','工程四部','工程五部']
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel:{formatter:'{value}'}
            }
        ],
        series : [
            {
                name:'民用类开工',
                type:'bar',
                stack:'民用类',
                itemStyle: {normal: {color:'rgba(193,35,43,1)', label:{show:true}}},
                data:[40,55,95,75, 30]
            },
            {
                name:'公用类开工',
                type:'bar',
                stack:'公用类',
                itemStyle: {normal: {color:'rgba(181,195,52,1)', label:{show:true,textStyle:{color:'#27727B'}}}},
                data:[35,57,105,100,87]
            },
            {
                name:'车用类开工',
                type:'bar',
                stack:'车用类',
                itemStyle: {normal: {color:'rgba(252,206,16,1)', label:{show:true,textStyle:{color:'#E87C25'}}}},
                data:[104,99,89,56,67]
            },
            {
                name:'民用类验收',
                type:'bar',
                stack:'民用类',
                itemStyle: {normal: {color:'rgba(193,35,43,0.75)', label:{show:true}}},
                data:[96,56,79,65,99]
            },
            {
                name:'公用类验收',
                type:'bar',
                stack:'公用类',
                itemStyle: {normal: {color:'rgba(181,195,52,0.75)', label:{show:true}}},
                data:[94,123,68,85,83]
            },
            {
                name:'车用类验收',
                type:'bar',
                stack:'车用类',
                itemStyle: {normal: {color:'rgba(252,206,16,0.75)', label:{show:true}}},
                data:[46,87,93,91,30]
            },
            {
                name:'民用类结算',
                type:'bar',
                stack:'民用类',
                itemStyle: {normal: {color:'rgba(193,35,43,0.5)', label:{show:true}}},
                data:[96,78,99,124,70]
            },
            {
                name:'公用类结算',
                type:'bar',
                stack:'公用类',
                itemStyle: {normal: {color:'rgba(181,195,52,0.5)', label:{show:true}}},
                data:[91,35,89,55,47]
            },
            {
                name:'车用类结算',
                type:'bar',
                stack:'车用类',
                itemStyle: {normal: {color:'rgba(252,206,16,0.5)', label:{show:true}}},
                data:[60,30,87,30,50]
            }
        ]
    });
    $("#projOnConstruction").hideMask();
},
deductionPerMonth = function(ec){
    var deductionPerMonth = ec.init(document.getElementById('deductionPerMonth'));
    deductionPerMonth.setOption({
        title : {
            text: '近一年扣分情况对比'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
        	x: 'right',
            data:['分包公司1','分包公司2','分包公司3','分包公司4','分包公司5']
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
                name:'分包公司1',
                type:'line',
                stack: '总量',
                data:[120, 132, 101, 134, 90, 230, 210, 180, 132, 151, 134, 90]
            },
            {
                name:'分包公司2',
                type:'line',
                stack: '总量',
                data:[220, 182, 191, 234, 290, 330, 310, 182, 191, 234, 201, 154]
            },
            {
                name:'分包公司3',
                type:'line',
                stack: '总量',
                data:[150, 232, 201, 154, 190, 330, 410, 101, 134, 90, 230, 210]
            },
            {
                name:'分包公司4',
                type:'line',
                stack: '总量',
                data:[320, 332, 301, 334, 390, 330, 320, 182, 191, 234, 290, 330]
            },
            {
                name:'分包公司5',
                type:'line',
                stack: '总量',
                data:[201, 154, 190, 330, 410, 101, 230, 210, 180, 330, 320, 182]
            }
        ]
    });
    $("#deductionPerMonth").hideMask();
},
/**
 * 初始化工程列表
 */
projectScaleStatistic = function () {
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
	            	projNumByYear(ec);
	            	projOnConstruction(ec);
	            	deductionPerMonth(ec);
	    	        $(".echart-box").off("chartrerender").on("chartrerender",function(){
	    	        	projNumByYear(ec);
		            	projOnConstruction(ec);
		            	deductionPerMonth(ec);
	    	        });
	            }
	        );
        }
    };
}();