var projNoStates = function(ec) {
	var projNoStates = ec.init(document.getElementById('projNoStates'));
	$.ajax({
		type : 'POST',
		url : 'constructionStatisticalAnalysis/everySchedule',
		success : function(data) {
			var result = JSON.parse(data);
			console.info("施工进度----");
			console.info(data);
			var option = {
				tooltip : {
					trigger : 'axis',
					axisPointer : { // 坐标轴指示器，坐标轴触发有效
						type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				legend : {
					itemGap : 14,
					data : [ '0%-30%', '30%-50%', '50%-60%', '60%-80%',
							'80%-90%', '90%-100%' ]
				},
				grid : {
					x : 100,
					y : 37,
					y2 : 30,
					x2 : 20,
					containLabel : true
				},
				xAxis : {
					type : 'value'
				},
				yAxis : {
					type : 'category',
					data : [ '施工管理一处', '施工管理二处', '施工管理三处', '施工管理四处', '施工管理五处' ]
				},
				series : [ {
					name : '0%-30%',
					itemStyle : {
						normal : {
							color : '#ff7e50'
						}
					},
					type : 'bar',
					stack : '总量',
					label : {
						normal : {
							show : true,
							position : 'insideRight'
						}
					},
					data : result.A
				}, {
					name : '30%-50%',
					itemStyle : {
						normal : {
							color : '#8bb1f6'
						}
					},
					type : 'bar',
					stack : '总量',
					label : {
						normal : {
							show : true,
							position : 'insideRight'
						}
					},
					data : result.B
				}, {
					name : '50%-60%',
					itemStyle : {
						normal : {
							color : '#a38ff7'
						}
					},
					type : 'bar',
					stack : '总量',
					label : {
						normal : {
							show : true,
							position : 'insideRight'
						}
					},
					data : result.C
				}, {
					name : '60%-80%',
					itemStyle : {
						normal : {
							color : '#ffc65c'
						}
					},
					type : 'bar',
					stack : '总量',
					label : {
						normal : {
							show : true,
							position : 'insideRight'
						}
					},
					data : result.D
				}, {
					name : '80%-90%',
					itemStyle : {
						normal : {
							color : '#6494ed'
						}
					},
					type : 'bar',
					stack : '总量',
					label : {
						normal : {
							show : true,
							position : 'insideRight'
						}
					},
					data : result.E
				}, {
					name : '90%-100%',
					itemStyle : {
						normal : {
							color : '#ffdfa5'
						}
					},
					type : 'bar',
					stack : '总量',
					label : {
						normal : {
							show : true,
							position : 'insideRight'
						}
					},
					data : result.F
				} ]
			};

			projNoStates.setOption(option);
			$("#projNoStates").hideMask();
		}
	});

}, deduction = function(ec) {
	param = $("#param").val();
	$.ajax({
		type : 'POST',
		url : 'constructionStatisticalAnalysis/queryInspectionList',
		data :{param : param},
		success : function(data) {
			var result = JSON.parse(data);
			var year = new Array();
			var myDate = new Date();
			year[0] = (myDate.getFullYear() - 2) + "年";
			year[1] = (myDate.getFullYear() - 1) + "年";
			year[2] = myDate.getFullYear() + "年";
			var datet = new Array();
			var dates = new Array();
			var datef = new Array();
			for(var i=0;i<result.length;i++){
				var arr = new Array();
				var str = result[i].YEAR;
				arr = str.split('-');
				if(arr[0]==(myDate.getFullYear()-2)){
					datef[(parseInt(arr[1], 10)-1)] = result[i].COUNT;
				}
				if(arr[0]==(myDate.getFullYear()-1)){
					dates[(parseInt(arr[1], 10)-1)] = result[i].COUNT;
				}
				if(arr[0]==myDate.getFullYear()){
					datet[(parseInt(arr[1], 10)-1)] = result[i].COUNT;
				}
			}
			for(var f=0; f<datef.length; f++){
				if(datef[f]=="" || datef[f]==null){
					datef[f] = 0;
				}
			}
			for(var s=0; s<dates.length; s++){
				if(dates[s]=="" || dates[s]==null){
					dates[s] = 0;
				}
			}
			for(var t=0; t<datet.length; t++){
				if(datet[t]=="" || datet[t]==null){
					datet[t] = 0;
				}
			}
			var deduction = ec.init(document.getElementById('deduction'));
			var option = {
				title : {
					text : ''
				},
				tooltip : {
					trigger : 'axis',
					axisPointer : {
						show : true,
						type : 'cross'
					}
				},
				legend : {
					x : 'right',
					itemGap : 14,
					// data:['2014年','2015年','2016年']
					data : year
				},
				calculable : false,
				grid : {
					x : 40,
					y : 40,
					y2 : 40,
					x2 : 40
				},
				xAxis : [ {
					type : 'category',
					boundaryGap : false,
					data : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
							'9月', '10月', '11月', '12月' ]
				} ],
				yAxis : [ {
					type : 'value'
				} ],

				series : [ {
					// name:'2014年',
					name : year[0],
					type : 'line',
					itemStyle : {
						normal : {
							lineStyle : {
								width : 4
							}
						}
					},
					symbolSize : 3,
					//data : [ 11, 20, 15, 12, 18, 36, 16, 20, 19, 13, 23, 30 ]
					data : datef
				}, {
					// name:'2015年',
					name : year[1],
					type : 'line',
					itemStyle : {
						normal : {
							lineStyle : {
								width : 3
							}
						}
					},
					symbolSize : 3,
					//data : [ 21, 41, 29, 37, 40, 51, 35, 23, 45, 32, 42, 12 ]
					data : dates
				}, {
					// name:'2016年',
					name : year[2],
					type : 'line',
					itemStyle : {
						normal : {
							lineStyle : {
								width : 3
							}
						}
					},
					symbolSize : 3,
					//data : [ 16, 14, 20, 40, 28, 19, 27, 36, 18 ]
					data : datet
				} ]
			};
			deduction.setOption(option);
			$("#deduction").hideMask();
		}
	});
}, deductionPerMonth = function(ec) {
	$.ajax({
		type : 'POST',
		url : 'constructionStatisticalAnalysis/queryInspectionRecord',
		success : function(data) {
			var result = JSON.parse(data);
			var upId = [];
			var num = [];
			for (var i = 0; i < result.length; i++) {
				upId[i] = result[i].UPID;
				num[i] = result[i].NUM;
			}
			var deductionPerMonth = ec.init(document
					.getElementById('deductionPerMonth'));
			zrColor = require('zrender/tool/color'), colorList = [ '#ff7f50',
					'#87cefa', '#da70d6', '#32cd32', '#6495ed', '#ff69b4',
					'#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0' ], itemStyle = {
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
				title : {
					text : ''
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
					boundaryGap : [ 0, 0.01 ]
				} ],
				yAxis : [ {
					type : 'category',
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
			// deductionPerMonth.setOption(option);
			$("#deductionPerMonth").hideMask();
		}
	});
},
/**
 * 初始化工程列表
 */
constructionStatisticalAnalysis = function() {
	"use strict";
	return {
		// main function
		init : function() {
			require.config({
				paths : {
					echarts : 'assets/plugins/echarts/build/dist/'
				}
			});
			require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/line',	'echarts/chart/pie' ], function(ec) {
				projNoStates(ec);
				deduction(ec);
				deductionPerMonth(ec);
				$(".echart-box").off("chartrerender").on("chartrerender",
				function() {
					projNoStates(ec);
					deduction(ec);
					deductionPerMonth(ec);
				});

				$("#param").change(function(){
					deduction(ec);
				});
			});
		}
	};
}();