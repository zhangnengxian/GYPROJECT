projectConstructList = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("/js/ellipsis.js").done(function(){
	        	handleProjectConstructList();
	        	require.config({
					paths : {
						echarts : 'assets/plugins/echarts/build/dist/'
					}
				});
				require([ 'echarts', '/assets/plugins/echarts/build/dist/chart/bar', '/assets/plugins/echarts/build/dist/chart/line',	'/assets/plugins/echarts/build/dist/chart/pie' ], function(ec) {
					deductionPerMonth(ec);
				});
        	})
        }
    };
}();
var handleProjectConstructList=function(){
	//alert('1');
	deductionPerMonth();
}
var  deductionPerMonth = function(ec) {
	
	$.ajax({
		type : 'POST',
		url : '/constructionStatisticalAnalysis/queryInspectionRecord',
		success : function(data) {
			var result = JSON.parse(data);
			var upId = [];
			var num = [];
			for (var i = 0; i < result.length; i++) {
				upId[i] = result[i].UPID;
				num[i] = result[i].NUM;
			}
			var deductionPerMonth = echarts.init(document
					.getElementById('echartsBox'));
			zrColor = require('zrender/tool/color'), colorList = [ '#ff9824',
					'#0dbacd', '#ffb980', '#1dcc9f','rgba(255,144,128,1)', '#ff9824',
					'#0dbacd', '#ffb980', '#1dcc9f','rgba(255,144,128,1)' ], itemStyle = {
				normal : {
					color : function(params) {
						if (params.dataIndex < 0) {
							// for legend
							return zrColor.lift(
									colorList[colorList.length - 1],
									params.seriesIndex * 0.1);
						} else {
							// for bar
							return zrColor.lift(colorList[params.dataIndex],
									params.seriesIndex * 0.1);
						}
					}
				}
			};
			deductionPerMonth.setOption({
				title: {
    		        text: '工程项目安全质量检查项目排名TOP10',
    		        left: 'center',
    		        top: '94%',
    		        textStyle: {
    		            color: '#fff',
    		            fontSize:18,
    		            fontFamily: '黑体'
    		        }
    		    },
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '' ]
				},
				calculable : true,
				grid : {
					x : 90,
					y : 37,
					y2 : 40,
					x2 : 40
				},
				xAxis : [ {
					type : 'value',
					boundaryGap : [ 0, 0.01 ],
				    axisLine: {
                        lineStyle: {
                            color: '#fff'
                        }
                    }
				} ],
				yAxis : [ {
					type : 'category',
					axisLine: {
                        lineStyle: {
                            color: '#fff'
                        }
                    },
					// data :
					// [result[0].UPID,result[1].UPID,result[2].UPID,result[3].UPID,result[4].UPID,result[5].UPID,result[6].UPID,result[7].UPID,result[8].UPID,result[9].UPID]
					data : upId
				} ],
				series : [ {
					name : '扣分次数',
					type : 'bar',
					itemStyle : itemStyle,
					// data:[result[0].NUM, result[1].NUM, result[2].NUM,
					// result[3].NUM, result[4].NUM,
					// result[5].NUM,result[6].NUM,result[7].NUM,result[8].NUM,result[9].NUM]
					data : num
				} ]
			});
		}
	});
}
