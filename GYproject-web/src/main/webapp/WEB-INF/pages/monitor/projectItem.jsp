<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<script src="/assets/plugins/echarts/echarts.min.js"></script>
 <script src="/assets/plugins/echarts/build/dist/chart/bar.js/"></script>
<script src="/assets/plugins/echarts/build/dist/chart/line.js/"></script>
<script src="/assets/plugins/echarts/build/dist/chart/pie.js/"></script>
<div id="content" class="content">
	<div class="echart-box" id="projNoStates">
	
	</div>
</div>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
   	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->
<script>
    var projNoStates = echarts.init(document.getElementById('projNoStates')),
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
    })
</script>