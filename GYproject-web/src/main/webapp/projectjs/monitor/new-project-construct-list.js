var searchData = {};//查询条件
/**
 * 加载工程列表
 */
var handleProjectConstructList = function() {
	"use strict";
    if ($('#projectConstructListTable').length !== 0) {
    	$('#projectConstructListTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack);
   			$('#projectConstructListTable').hideMask();
   			//列表
   			//projectListMonitor();
   			//地图
   			//projectMapMonitor();
   			//部门
   			flowerLeftMonitor();
   			//部门列表
   			unitListMonitor();
   			//部门图表
   			unitChartMonitor();
   			//部门地图
   			unitMapMonitor();
   			//施工进度
   			flowerLeftBottomMonitor();
   			//承包方
   			flowerRightTopMonitor();
   			//承包商列表
   			cuUnitListMonitor();
   			//承包商图表
   			// cuEchartsMonitror();
   			//安全质量
   			flowerRightBottomMonitor();
   			//安全质量排行
   			safeQualitityTopTenMonitor();
   			//安全质量施工扣分
   			safeQualitityUnitLineMonitor();
   			//安全质量曲线
   			safeQualitityCuLineMonitor();
   			flowerHeartMonitor();
   			//选择施工单位
   			chooseUnitMonitor();
   			//返回报表首页
   			backHomeMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [10],
            dom: 'Brtip',
            buttons: [
            ],
            serverSide:true,
            ajax:{
            	url:"/projTS/queryProject",
            	type:'post',
            	data: function(d){
                	$.each(searchData,function(i,k){
                		d[i] = k;
                	});
                },
                dataSrc: 'data'
            },
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,
            columns: [
			    {"data":"projNo", responsivePriority: 2},
				{"data":"projName", responsivePriority: 1},
				{"data":"projAddr", responsivePriority: 3},
				{"data":"projScaleDes", responsivePriority: 6},
				{"data":"projStatusDes", responsivePriority: 4},
				// {"data":"managementOffice", responsivePriority: 5},
				{"data":"cuName",responsivePriority: 7},
				{"data":"duName", responsivePriority: 8},
				{"data":"surveyer", responsivePriority: 9}
		 	],
		 	columnDefs: [{
				"targets":1,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 15, 	//截取多少字符（或汉字）
					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":3,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}
		 	]
        });
    }
},

trSelectedBack = function(v, i, index, t, json){
	
};

var chooseUnitMonitor=function(){
	//单选radio change事件
    $("input:radio[name='constructionUnit']").on('change',function(){
    	//alert($("input[name='constructionUnit']:checked").val());
    	searchData.managementOfficeId=$("input[name='constructionUnit']:checked").val();
    	//console.info('val..'+$("input[name='constructionUnit']:checked").val());
    	//console.info('searchId..'+searchData.managementOfficeId);
    	$("#projectConstructListTable").cgetData();
    })
}

var backHomeMonitor=function(){
	
	$("#backHome").off().on('click',function(){
		 var path = $("#path").val();
		 window.location.href=path+"projInfoNew.jsp"
		})
	}
/*//加载列表
var projectListMonitor=function(){
	$("#projectList").off('click').on('click',function(){
		$("#echartsBox").cgetContent("/projTS/viewListPage");
	});
}*/

//加载地图
/*var projectMapMonitor=function(){
	$("#projectMap").off().on('click',function(){
		$("#echartsBox").cgetContent("/projTS/viewMapPage");
	});
}*/

//部门列表
var unitListMonitor=function(){
	// $("#unitList").off('click').on('click',function(){
	// 	$(".btn").removeClass('clickEffect');
	// 	$("#unitList").addClass('clickEffect');
	// 	$("#echartsBox").cgetContent("/projTS/viewListPage");
	// });
}

//点击'部门'
var flowerLeftMonitor=function(){
	$(".flower-left-top").off('click').on('click',function(){
        $("#caiIdDes").removeClass('hidden'); //分公司选择
        $("#listBox").removeClass('hidden'); //部门
        $("#scheduleBox").addClass('hidden'); //施工进度
        $('#safeQualitityBox').addClass('hidden');//安全质量
        $("#echartSmallLeftBox").addClass("hidden");  //db-承包方
        $("#echartSmallRightBox").addClass("hidden");  //db-承包方右

        $('.fourLeaf').removeClass('fourLeafClcik');
        $(".left-top-btn").addClass('fourLeafClcik');
        $(".btn").removeClass('clickEffect');
        $("#unitList").addClass('clickEffect');
        $("#echartsBox").removeClass('hidden');
        $("#echartsBoxTopLeft").addClass('hidden');
        $("#echartsBoxTopRight").addClass('hidden');
        //部门按钮
        $(".unit").removeClass('hidden');
        $('.cuName').addClass('hidden');
        $('.safeQualitity').addClass('hidden');
        $('.projectSchedule').addClass('hidden');
	});
}

//部门地图
var unitMapMonitor=function(){
	$("#unitMap").off('click').on('click',function(){
		$(".btn").removeClass('clickEffect');
		$("#unitMap").addClass('clickEffect');
		
		//列表屏
		$("#listBox").addClass('hidden');
		$('.chooseRadio').addClass('hidden');
		$("#mapBox").removeClass('hidden');
		//统计屏
		$('#echartSmallLRightBox').addClass('hidden');
		$('#echartSmallLeftBox').addClass('hidden');
		//alert('2');
		//
		loadProjectMap();
	});
}




//部门图表
var unitChartMonitor=function(){
	$("#unitChart").off('click').on('click',function(){
		$(".btn").removeClass('clickEffect');
		$("#unitChart").addClass('clickEffect');
		$("#listBox").addClass("hidden");
		//施工处选择
		$(".chooseRadio").addClass("hidden");
		//地图
		$("#mapBox").addClass('hidden');
		$("#echartSmallLeftBox").removeClass("hidden");
		/*$("#echartSmallLeftBox").removeClass('hidden');
		$("#echartSmallRightBox").removeClass('hidden');*/
		
		$.ajax({
		    type: 'POST',
		    url: '/projTS/queryjConstructionNumsAndAmount',
		    contentType: "application/json;charset=UTF-8",
		    data: '',
		    success: function (data) {
		    	console.info("施工屏部门pie图-----");
		    	console.info(data);
		    	var json=JSON.parse(data);
		    	var dataNum=json.dataNum;
		    	var dataAmount=json.dataAmount;
		    	//左侧
				optionLeft = {
						color : ['#ff9824','#0dbacd','#ffb980','#1dcc9f','rgba(255,144,128,1)'],
					    tooltip: {
					        trigger: 'item',
					        formatter: "{a} <br/>{b}: {c} ({d}%)"
					    },
					    title: {
		    		        text: '施工管理处工程数量、金额统计',
		    		        left: 'center',
		    		        top:'92%',
		    		        textStyle: {
		    		            color: '#fff',
		    		            fontSize:18,
		    		            fontFamily:'黑体'
		    		        }
		    		    },
					    legend: {
					        orient: 'vertical',
					        x: 'left',
					        data:['一处','二处','三处','四处','五处'],
					        textStyle: {
					            color: '#fff',
					            fontSize:12,
					            fontFamily: '黑体'
					        }
					    },
					    series: [
					        {
					            name:'数量',
					            type:'pie',
					            selectedMode: 'single',
					            radius: [0, '30%'],

					            label: {
					                normal: {
					                    position: 'inner'
					                }
					            },
					            labelLine: {
					                normal: {
					                    show: false
					                }
					            },
					            data:dataNum
					            	/*[
					                {value:345, name:'一处', selected:true},
					                {value:222, name:'二处'},
					                {value:345, name:'三处'},
					                {value:234, name:'四处'} ,
					                {value:124, name:'五处'}
					            ]*/
					        },
					        {
					            name:'金额',
					            type:'pie',
					            radius: ['42%', '55%'],

					            data:dataAmount
					            	/*[
					                {value:3345000.04, name:'一处'},
					                {value:3100098.20, name:'二处'},
					                {value:2344536.55, name:'三处'},
					                {value:1355456.99, name:'四处'},
					                {value:1048756.44, name:'五处'}
					            ]*/
					        }
					    ]
					};
			
			var unitOptionLeft = echarts.init(document.getElementById('echartSmallLeftBox'));
			unitOptionLeft.setOption(optionLeft);
			unitOptionRight();
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		        console.warn("ajax queryScaleDetail...fail");
		    }
		});
	});
}

//部门图表-右图工程类型
var unitOptionRight=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/queryContructionUnitScaleNums',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info('部门图表-右图工程类型------------------');
	    	console.info(data);
	    	var json=JSON.parse(data);
	    	/*{"高层壁挂(户)","多层壁挂(户)","别墅(户)","多层(户)","私宅(户)","高层(户)"};*/
	    	/*{"餐厅(座)","车用","其他","采暖锅炉(座)","工业(座)","门面房(座)"};*/
	    	var civildataNum0=json.civildataNum0;
	    	var civildataNum1=json.civildataNum1;
	    	var civildataNum2=json.civildataNum2;
	    	var civildataNum3=json.civildataNum3;
	    	var civildataNum4=json.civildataNum4;
	    	var civildataNum5=json.civildataNum5;
	    	var publicDataNum0=json.publicDataNum0;
	    	var publicDataNum1=json.publicDataNum1;
	    	var publicDataNum2=json.publicDataNum2;
	    	var publicDataNum3=json.publicDataNum3;
	    	var publicDataNum4=json.publicDataNum4;
	    	var publicDataNum5=json.publicDataNum5;
	    	optionRight = {
	    			title: {
	    		        text: '施工管理处工程类型统计',
	    		        left: 'center',
	    		        top:'92%',
	    		        textStyle: {
	    		            color: '#fff',
	    		            fontSize:18,
	    		            fontFamily:'黑体'
	    		        }
	    		    },
	    		    "tooltip": {
	    		        "trigger": "axis",
	    		        "axisPointer": {
	    		            "type": "shadow",
	    		            textStyle: {
	    		                color: "#fff"
	    		            }

	    		        },
	    		    },
	    		    "grid": {
	    		        "borderWidth": 0,
	    		        "top": 110,
	    		        "bottom": 95,
	    		        textStyle: {
	    		            color: "#fff"
	    		        }
	    		    },
	    		    "legend": {
	    		        x: '4%',
	    		        top: '6%',
	    		        textStyle: {
	    		            color: '#90979c',
	    		        },
	    		        "data": ["高层壁挂(户)","多层壁挂(户)","别墅(户)","多层(户)","私宅(户)","高层(户)","餐厅(座)","车用","其他","采暖锅炉(座)","工业(座)","门面房(座)" ]
	    		        ,textStyle: {
	    		            color: '#fff',
	    		            fontSize:12,
	    		            fontFamily: '黑体'
	    		        }
	    		        
	    		    },
	    		     

	    		    "calculable": true,
	    		    "xAxis": [{
	    		        "type": "category",
	    		        "nameGap":25,
	    		        "axisLine": {
	    		            lineStyle: {
	    		                color: '#90979c'
	    		            }
	    		        },
	    		        "splitLine": {
	    		            "show": false
	    		        },
	    		        "axisTick": {
	    		            "show": false
	    		        },
	    		        "splitArea": {
	    		            "show": false
	    		        },
	    		        "axisLabel": {
	    		            "interval": 0,

	    		        },
	    		        "data": ['一处','二处','三处','四处','五处' ],
	    		        axisLine: {
	    			                        lineStyle: {
	    			                            color: '#fff'
	    			                        }
	    			                    },
	    		    }],
	    		    "yAxis": [{
	    		        "type": "value",
	    		        "nameGap":55,
	    		        "splitLine": {
	    		            "show": false
	    		        },
	    		        "axisLine": {
	    		            lineStyle: {
	    		                color: '#90979c'
	    		            }
	    		        },
	    		        "axisTick": {
	    		            "show": false
	    		        },
	    		        "axisLabel": {
	    		            "interval": 0,

	    		        },
	    		        "splitArea": {
	    		            "show": false
	    		        },
	    		        axisLine: {
	    			                        lineStyle: {
	    			                            color: '#fff'
	    			                        }
	    			                    },
	    		    }],/*
	    		    "dataZoom": [{
	    		        "show": true,
	    		        "height": 30,
	    		        "xAxisIndex": [
	    		            0
	    		        ],
	    		        bottom: 30,
	    		        "start": 10,
	    		        "end": 80,
	    		        handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
	    		        handleSize: '110%',
	    		        handleStyle:{
	    		            color:"#fff",
	    		            
	    		        },
	    		           textStyle:{
	    		            color:"#fff"},
	    		           borderColor:"#90979c"
	    		        
	    		        
	    		    }, {
	    		        "type": "inside",
	    		        "show": true,
	    		        "height": 15,
	    		        "start": 1,
	    		        "end": 35
	    		    }],*/
	    		    "series": [
	    		        {
	    		            "name": "高层(户)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(0,191,183,1)",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": civildataNum5,
	    		        },{
	    		            "name": "高层壁挂(户)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "barMaxWidth": 35,
	    		            "barGap": "10%",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(255,144,128,1)",
	    		                    "label": {
	    		                        "show": true,
	    		                        "textStyle": {
	    		                            "color": "#fff"
	    		                        },
	    		                        "position": "insideTop",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data":civildataNum0
	    		            	/*[
	    		                {value:33, name:'一处'},
	    		                {value:31, name:'二处'},
	    		                {value:23, name:'三处'},
	    		                {value:13, name:'四处'},
	    		                {value:10, name:'五处'}
	    		            ]*/,
	    		        },{
	    		            "name": "多层壁挂(户)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "barMaxWidth": 35,
	    		            "barGap": "10%",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(255,193,0,1)",
	    		                    "label": {
	    		                        "show": true,
	    		                        "textStyle": {
	    		                            "color": "#fff"
	    		                        },
	    		                        "position": "insideTop",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data":civildataNum1,
	    		        },

	    		        {
	    		            "name": "别墅(户)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(0,192,22,1)",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": civildataNum2
	    		        },{
	    		            "name": "多层(户)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "#ffb980",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": civildataNum3,
	    		        },{
	    		            "name": "私宅(户)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "#ffb980",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": civildataNum4
	    		        },{
	    		            "name": "餐厅(座)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(0,255,234,1)",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": publicDataNum0
	    		        },
	    		        {
	    		            "name": "车用",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(95,192,123,1)",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": publicDataNum1
	    		        },
	    		        {
	    		            "name": "其他",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "#0dbacd",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": publicDataNum2
	    		        },
	    		        {
	    		            "name": "采暖锅炉(座)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "#0dbacd",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": publicDataNum3
	    		        },{
	    		            "name": "工业(座)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "#1dcc9f",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": publicDataNum4
	    		        },{
	    		            "name": "门面房(座)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "#1dcc9f",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": publicDataNum5
	    		        }, /*{
	    		            "name": "总",
	    		            "type": "line",
	    		            "stack": "总量",
	    		            symbolSize:20,
	    		            symbol:'circle',
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(252,230,48,1)",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "data": [
	    		                660,
	    		                799,
	    		                685,
	    		                600,
	    		                700
	    		               
	    		            ]
	    		        *//*}*//*, {
	    		            "name": "总数",
	    		            "type": "line",
	    		            "stack": "总量",
	    		            symbolSize:10,
	    		            symbol:'circle',
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(252,230,48,1)",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "data": [
	    		                1036,
	    		                3693,
	    		                2962,
	    		                3810,
	    		                2519,
	    		                1915,
	    		                1748,
	    		                4675,
	    		                6209,
	    		                4323,
	    		                2865,
	    		                4298
	    		            ]
	    		        },*/
	    		    ]
	    		};
	    	var unitOptionRight = echarts.init(document.getElementById('echartSmallRightBox'));
	    	unitOptionRight.setOption(optionRight);	
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
	
	
	
	
	
		
}


//施工进度
var flowerLeftBottomMonitor=function(){
	$(".flower-left-bottom").off('click').on('click',function(){
		$("#scheduleBox").removeClass('hidden'); //进度
		$('#safeQualitityBox').addClass('hidden');//安全质量
		$("#listBox").addClass('hidden'); //部门
		$("#caiIdDes").addClass('hidden'); //分公司选择隐藏
		$("#echartSmallLeftBox").addClass("hidden");  //db-承包方
		$("#echartSmallRightBox").addClass("hidden");  //db-承包方右

		$('.fourLeaf').removeClass('fourLeafClcik');
		$(".left-bottom-btn").addClass('fourLeafClcik');

		// $("#echartsBox").removeClass('hidden');
		$("#echartsBoxTopLeft").addClass('hidden');
		$("#echartsBoxTopRight").addClass('hidden');

		$(".unit").addClass('hidden');
		$('.cuName').addClass('hidden');
		$('.safeQualitity').addClass('hidden');
		$('.projectSchedule').removeClass('hidden');

		$(".btn").removeClass('clickEffect');
		$("#projectSchedule").addClass('clickEffect');

		schedule();
	});
};

//施工进度echarts
var schedule = function (corp) {
    corp = corp ||"1102";
    $.ajax({
        type: 'POST',
        url: '/projTS/queryProjectSchedule',
        contentType: "application/json;charset=UTF-8",
        // data: '',
        success: function (data) {
            var schedule=JSON.parse(data);
            var resultA=schedule.initdata.A;
            var resultB=schedule.initdata.B;
            var resultC=schedule.initdata.C;
            var resultD=schedule.initdata.D;
            var resultE=schedule.initdata.E;
            var resultF=schedule.initdata.F;

            var option = {
                color : ['#ff9824','#0dbacd','#ffb980','#1dcc9f','rgba(255,144,128,1)'],
                title: {
                    text: '施工进度统计表',
                    left: 'center',
                    top: '94%',
                    textStyle: {
                        color: '#fff',
                        fontSize:18
                    }
                },
                tooltip : {
                    trigger : 'axis',
                    axisPointer : { // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend : {
                    itemGap : 14,
                    data : [ '0%-30%', '30%-50%', '50%-60%', '60%-80%',
                        '80%-90%', '90%-100%' ],
                    textStyle: {
                        color: '#fff',
                        fontSize:12,
                        fontFamily: '黑体'
                    }
                },
                grid : {
                    x : 100,
                    y : 37,
                    y2 : 30,
                    x2 : 20,
                    containLabel : true
                },
                xAxis : {
                    type : 'value',
                    axisLine: {
                        lineStyle: {
                            color: '#fff'
                        }
                    }
                },
                yAxis : {

                    splitNumber: 10,
                    type : 'category',
                    data :schedule.departments,
                    axisLine: {
                        lineStyle: {
                            color: '#fff'
                        }
                    }

                },
                series : [ {
                    name : '0%-30%',
                    itemStyle : {
                        normal : {
                            color : '#ff9824'
                        }
                    },
                    type : 'bar',
                    stack : '总量',
                    label : {
                        normal : {
                            show : false,
                            position : 'insideRight'
                        }
                    },
                    barWidth:30,
                    data:resultA
					/*[12, 12, 15, 23, 10]*/
                }, {
                    name : '30%-50%',
                    itemStyle : {
                        normal : {
                            color : '#0dbacd'
                        }
                    },
                    type : 'bar',
                    stack : '总量',
                    label : {
                        normal : {
                            show : false,
                            position : 'insideRight'
                        }
                    },
                    data:resultB/*[13, 33, 24,  10, 0]*/
                }, {
                    name : '50%-60%',
                    itemStyle : {
                        normal : {
                            color : '#ffb980'
                        }
                    },
                    type : 'bar',
                    stack : '总量',
                    label : {
                        normal : {
                            show : false,
                            position : 'insideRight'
                        }
                    },
                    data:resultC/*[14, 26 , 33,  10, 20]*/
                }, {
                    name : '60%-80%',
                    itemStyle : {
                        normal : {
                            color : '#1dcc9f'
                        }
                    },
                    type : 'bar',
                    stack : '总量',
                    label : {
                        normal : {
                            show : false,
                            position : 'insideRight'
                        }
                    },
                    data:resultD/*[22, 23, 25,12, 22]*/
                }, {
                    name : '80%-90%',
                    itemStyle : {
                        normal : {
                            color : 'rgba(255,144,128,1)'
                        }
                    },
                    type : 'bar',
                    stack : '总量',
                    label : {
                        normal : {
                            show : false,
                            position : 'insideRight'
                        }
                    },
                    data:resultE/*[22 ,22, 21, 20, 30]*/
                }, {
                    name : '90%-100%',
                    itemStyle : {
                        normal : {
                            color : '#ffdfa4'
                        }
                    },
                    type : 'bar',
                    stack : '总量',
                    label : {
                        normal : {
                            show : false,
                            position : 'insideRight'
                        }
                    },
                    data:resultF/*[20, 12, 21, 4,   9]*/
                } ]
            };
            var areaAmount = echarts.init(document.getElementById('scheduleBox'),'shine');
            areaAmount.setOption(option);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("ajax queryScaleDetail...fail");
        }
    });
}

//点击'承包方'
var flowerRightTopMonitor=function(){
	$(".flower-right-top").off('click').on('click',function(){
        $("#echartSmallLeftBox").removeClass("hidden");  //db-承包方
        $("#echartSmallRightBox").removeClass("hidden");  //db-承包方右
        $("#caiIdDes").addClass('hidden'); //分公司select
        $("#listBox").addClass('hidden'); //部门
        $("#scheduleBox").addClass('hidden'); //施工进度
        $('#safeQualitityBox').addClass('hidden');//安全质量

        //点击样式
        $('.fourLeaf').removeClass('fourLeafClcik');
        $(".right-top-btn").addClass('fourLeafClcik');

        //按钮加点击样式
        $(".btn").removeClass('clickEffect');
        $("#cuNameList").addClass('clickEffect');

        $(".unit").addClass('hidden');
        $('.safeQualitity').addClass('hidden');
        $('.projectSchedule').addClass('hidden');
        //承包商对应按钮
        $('.cuName').removeClass('hidden');

        $("#echartsBox").removeClass('hidden');
        $("#echartsBoxTopLeft").addClass('hidden');
        $("#echartsBoxTopRight").addClass('hidden');

        cuEcharts();
        cuEchartsRight();
        // $("#echartsBox").cgetContent("/projTS/viewCuListPage");
    });
}

var cuEchartsRight = function (corp) {
    corp = corp || "";
    $.ajax({
        type: 'POST',
        url: '/projTS/queryCuUnitNumsAndAmountRight',
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            var json=JSON.parse(data);
            var dataNum=json.dataNum;
            var dataAmount=json.dataAmount;
            option = {
                color : ['#0dbacd','#ffb424','#1dcc9f','#ffb980','rgba(255,144,128,1)'],
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                title: {
                    text: '施工合同金额统计',
                    left: 'center',
                    top:'92%',
                    textStyle: {
                        color: '#fff',
                        fontSize:18,
                        fontFamily: '黑体'
                    }
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    // data:['聚泰鑫原','宇润天成','隆川元','顺兴德','昊菲达尔','凯睿特','豫世龙','大盛建筑','卓越','亿鸿祥'],
                    textStyle: {
                        color: '#fff',
                        fontSize:12,
                        fontFamily: '黑体'
                    }
                },
                series: [
                    {
                        name:'数量',
                        type:'pie',
                        selectedMode: 'single',
                        radius: [0, '30%'],

                        label: {
                            normal: {
                                position: 'inner'
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:dataNum
                    },
                    {
                        name:'金额',
                        type:'pie',
                        radius: ['42%', '55%'],

                        data:dataAmount
                    }
                ]
            };
            var cuOptionLeft = echarts.init(document.getElementById('echartSmallRightBox'));
            cuOptionLeft.setOption(option);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("ajax queryScaleDetail...fail");
        }
    });
}

//承包方Echarts
var cuEcharts = function (corp) {
    $.ajax({
        type: 'POST',
        url: '/projTS/queryCuUnitNumsAndAmount',
        contentType: "application/json;charset=UTF-8",
        // data: '',
        success: function (data) {
            var json=JSON.parse(data);
            var dataNum=json.dataNum;
            var dataAmount=json.dataAmount;
            option = {
                color : ['#ff9824','#0dbacd','#ffb980','#1dcc9f','rgba(255,144,128,1)'],
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                title: {
                    text: '发展合同金额统计',
                    left: 'center',
                    top:'92%',
                    textStyle: {
                        color: '#fff',
                        fontSize:18,
                        fontFamily: '黑体'
                    }
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data:['聚泰鑫原','宇润天成','隆川元','顺兴德','昊菲达尔','凯睿特','豫世龙','大盛建筑','卓越','亿鸿祥'],
                    textStyle: {
                        color: '#fff',
                        fontSize:12,
                        fontFamily: '黑体'
                    }
                },
                series: [
                    {
                        name:'数量',
                        type:'pie',
                        selectedMode: 'single',
                        radius: [0, '30%'],

                        label: {
                            normal: {
                                position: 'inner'
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:dataNum
                    },
                    {
                        name:'金额',
                        type:'pie',
                        radius: ['42%', '55%'],
                        data:dataAmount
                    }
                ]
            };
            var cuOptionLeft = echarts.init(document.getElementById('echartSmallLeftBox'));
            cuOptionLeft.setOption(option);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("ajax queryScaleDetail...fail");
        }
    });
}

//承包商列表
var cuUnitListMonitor=function(){
	$("#cuNameList").off('click').on('click',function(){
		$(".btn").removeClass('clickEffect');
		$("#cuNameList").addClass('clickEffect');
		$("#echartsBox").cgetContent("/projTS/viewCuListPage");
	})
}

//承包商图表
var cuEchartsMonitror=function(){
	$("#cuNameChart").off('click').on('click',function(){
		$(".btn").removeClass('clickEffect');
		$("#cuNameChart").addClass('clickEffect');
		$("#listBox").addClass("hidden");
		$("#caiIdDes").addClass("hidden");
		
		$.ajax({
		    type: 'POST',
		    url: '/projTS/queryCuUnitNumsAndAmount',
		    contentType: "application/json;charset=UTF-8",
		    data: '',
		    success: function (data) {
		    	var json=JSON.parse(data);
		    	var dataNum=json.dataNum;
		    	var dataAmount=json.dataAmount;
		    	option = {
						color : ['#ff9824','#0dbacd','#ffb980','#1dcc9f','rgba(255,144,128,1)'],
					    tooltip: {
					        trigger: 'item',
					        formatter: "{a} <br/>{b}: {c} ({d}%)"
					    },
					    title: {
		    		        text: '分包单位工程数量、金额统计',
		    		        left: 'center',
		    		        top:'92%',
		    		        textStyle: {
		    		            color: '#fff',
		    		            fontSize:18,
		    		            fontFamily: '黑体'
		    		        }
		    		    },
					    legend: {
					        orient: 'vertical',
					        x: 'left',
					        data:['聚泰鑫原','宇润天成','隆川元','顺兴德','昊菲达尔','凯睿特','豫世龙','大盛建筑','卓越','亿鸿祥'],
					        textStyle: {
					            color: '#fff',
					            fontSize:12,
					            fontFamily: '黑体'
					        }
					    },
					    series: [
					        {
					            name:'数量',
					            type:'pie',
					            selectedMode: 'single',
					            radius: [0, '30%'],

					            label: {
					                normal: {
					                    position: 'inner'
					                }
					            },
					            labelLine: {
					                normal: {
					                    show: false
					                }
					            },
					            data:dataNum
					            	/*[
					                {value:45, name:'聚泰鑫原', selected:true},
					                {value:22, name:'宇润天成'},
					                {value:45, name:'隆川元'},
					                {value:34, name:'顺兴德'} ,
					                {value:14, name:'昊菲达尔'},
					                {value:24, name:'凯睿特'},
					                {value:12, name:'豫世龙'},
					                {value:12, name:'大盛建筑'},
					                {value:12, name:'卓越'},
					                {value:42, name:'亿鸿祥'}
					            ]*/
					        },
					        {
					            name:'金额',
					            type:'pie',
					            radius: ['42%', '55%'],

					            data:dataAmount
					            	/*[
					                {value:200000.00, name:'聚泰鑫原', selected:true},
					                {value:222445.33, name:'宇润天成'},
					                {value:342352.45, name:'隆川元'},
					                {value:423234.23, name:'顺兴德'} ,
					                {value:124000.02, name:'昊菲达尔'},
					                {value:231424, name:'凯睿特'},
					                {value:100083, name:'豫世龙'},
					                {value:232435, name:'大盛建筑'},
					                {value:432435, name:'卓越'},
					                {value:632435, name:'亿鸿祥'}
					            ]*/
					        }
					    ]
					};
		    	var cuOptionLeft = echarts.init(document.getElementById('echartSmallLeftBox'));
				cuOptionLeft.setOption(option);
				cuSetRightOption();
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		        console.warn("ajax queryScaleDetail...fail");
		    }
		});
		
		/*var xData = function() {
	    var data = [];
	    for (var i = 1; i < 6; i++) {
	        data.push(i + "处");
	    }
	    return data;
	}();*/
	})
}

//承包商右侧
var cuSetRightOption=function(){
	$.ajax({
	    type: 'POST',
	    url: '/projTS/queryCuUnitScaleNums',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info("分包方工程类型----");
	    	console.info(data);
	    	var json=JSON.parse(data);
	    	/*{"高层壁挂(户)","多层壁挂(户)","别墅(户)","多层(户)","私宅(户)","高层(户)"};*/
	    	/*{"餐厅(座)","车用","其他","采暖锅炉(座)","工业(座)","门面房(座)"};*/
	    	var civildataNum0=json.civildataNum0;
	    	var civildataNum1=json.civildataNum1;
	    	var civildataNum2=json.civildataNum2;
	    	var civildataNum3=json.civildataNum3;
	    	var civildataNum4=json.civildataNum4;
	    	var civildataNum5=json.civildataNum5;
	    	var publicDataNum0=json.publicDataNum0;
	    	var publicDataNum1=json.publicDataNum1;
	    	var publicDataNum2=json.publicDataNum2;
	    	var publicDataNum3=json.publicDataNum3;
	    	var publicDataNum4=json.publicDataNum4;
	    	var publicDataNum5=json.publicDataNum5;

	    	optionRight = {
	    			 "title": {
	        "text": "分包单位工程类型统计",
	        "subtext": "",
	        x: "4%",
	        top:'92%',
	        left:'center',
	        textStyle: {
	            color: '#fff',
	            fontSize: '18',
	            fontFamily: '黑体'
	        },
	        subtextStyle: {
	            color: '#90979c',
	            fontSize: '16',

	        },
	    },
	    		    "tooltip": {
	    		        "trigger": "axis",
	    		        "axisPointer": {
	    		            "type": "shadow",
	    		            textStyle: {
	    		                color: "#fff"
	    		            }
	    		        },
	    		    },
	    		    "grid": {
	    		        "borderWidth": 0,
	    		        "top": 110,
	    		        "bottom": 95,
	    		        textStyle: {
	    		            color: "#fff"
	    		        }
	    		    },
	    		    "legend": {
	    		        x: '4%',
	    		        top: '6%',
	    		        textStyle: {
	    		            color: '#90979c',
	    		        },
	    		        "data": ["高层壁挂(户)","多层壁挂(户)","别墅(户)","多层(户)","私宅(户)","高层(户)","餐厅(座)","车用","其他","采暖锅炉(座)","工业(座)","门面房(座)" ]
	    		        ,textStyle: {
	    		            color: '#fff',
	    		            fontSize:12,
	    		            fontFamily: '黑体'
	    		        }
	    		    },

	    		    "calculable": true,
	    		    "xAxis": [{
	    		        "type": "category",
	    		        "nameGap":25,
	    		        "axisLine": {
	    		            lineStyle: {
	    		                color: '#90979c'
	    		            }
	    		        },
	    		        "splitLine": {
	    		            "show": false
	    		        },
	    		        "axisTick": {
	    		            "show": false
	    		        },
	    		        "splitArea": {
	    		            "show": false
	    		        },
	    		        "axisLabel": {
	    		            "interval": 0,

	    		        },
	    		        data:['聚泰鑫原','隆川元','宇润天成','顺兴德','昊菲达尔','凯睿特','豫世龙','大盛建筑','卓越','亿鸿祥'],
	    		        axisLine: {
							lineStyle: {
								color: '#fff'
							}
						},
	    		    }],
	    		    "yAxis": [{
	    		        "type": "value",
	    		        "nameGap":55,
	    		        "splitLine": {
	    		            "show": false
	    		        },
	    		        "axisLine": {
	    		            lineStyle: {
	    		                color: '#90979c'
	    		            }
	    		        },
	    		        "axisTick": {
	    		            "show": false
	    		        },
	    		        "axisLabel": {
	    		            "interval": 0,
	    		        },
	    		        "splitArea": {
	    		            "show": false
	    		        },
	    		        axisLine: {
							lineStyle: {
								color: '#fff'
							}
						},
	    		    }],/*
	    		    "dataZoom": [{
	    		        "show": true,
	    		        "height": 30,
	    		        "xAxisIndex": [
	    		            0
	    		        ],
	    		        bottom: 30,
	    		        "start": 10,
	    		        "end": 80,
	    		        handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
	    		        handleSize: '110%',
	    		        handleStyle:{
	    		            color:"#fff",
	    		        },
	    		           textStyle:{
	    		            color:"#fff"},
	    		           borderColor:"#90979c"
	    		    }, {
	    		        "type": "inside",
	    		        "show": true,
	    		        "height": 15,
	    		        "start": 1,
	    		        "end": 35
	    		    }],*/
	    		    "series": [
	    		        {
	    		            "name": "高层(户)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(0,191,183,1)",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": civildataNum5,
	    		        },{
	    		            "name": "高层壁挂(户)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "barMaxWidth": 35,
	    		            "barGap": "10%",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(255,144,128,1)",
	    		                    "label": {
	    		                        "show": true,
	    		                        "textStyle": {
	    		                            "color": "#fff"
	    		                        },
	    		                        "position": "insideTop",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data":civildataNum0
	    		            	/*[
	    		                {value:33, name:'一处'},
	    		                {value:31, name:'二处'},
	    		                {value:23, name:'三处'},
	    		                {value:13, name:'四处'},
	    		                {value:10, name:'五处'}
	    		            ]*/,
	    		        },{
	    		            "name": "多层壁挂(户)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "barMaxWidth": 35,
	    		            "barGap": "10%",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(255,193,0,1)",
	    		                    "label": {
	    		                        "show": true,
	    		                        "textStyle": {
	    		                            "color": "#fff"
	    		                        },
	    		                        "position": "insideTop",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data":civildataNum1,
	    		        },

	    		        {
	    		            "name": "别墅(户)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(0,192,22,1)",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": civildataNum2
	    		        },{
	    		            "name": "多层(户)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "#ffb980",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": civildataNum3,
	    		        },{
	    		            "name": "私宅(户)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "#ffb980",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": civildataNum4
	    		        },{
	    		            "name": "餐厅(座)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(0,255,234,1)",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": publicDataNum0
	    		        },
	    		        {
	    		            "name": "车用",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(95,192,123,1)",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": publicDataNum1
	    		        },
	    		        {
	    		            "name": "其他",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "#0dbacd",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": publicDataNum2
	    		        },
	    		        {
	    		            "name": "采暖锅炉(座)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "#0dbacd",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": publicDataNum3
	    		        },{
	    		            "name": "工业(座)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "#1dcc9f",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": publicDataNum4
	    		        },{
	    		            "name": "门面房(座)",
	    		            "type": "bar",
	    		            "stack": "总量",
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "#1dcc9f",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "label":{
	            		        "normal":{
	            		            show:false
	            		        }
	            		    },
	    		            "data": publicDataNum5
	    		        }, /*{
	    		            "name": "总",
	    		            "type": "line",
	    		            "stack": "总量",
	    		            symbolSize:20,
	    		            symbol:'circle',
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(252,230,48,1)",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "data": [
	    		                660,
	    		                799,
	    		                685,
	    		                600,
	    		                700
	    		               
	    		            ]
	    		        *//*}*//*, {
	    		            "name": "总数",
	    		            "type": "line",
	    		            "stack": "总量",
	    		            symbolSize:10,
	    		            symbol:'circle',
	    		            "itemStyle": {
	    		                "normal": {
	    		                    "color": "rgba(252,230,48,1)",
	    		                    "barBorderRadius": 0,
	    		                    "label": {
	    		                        "show": true,
	    		                        "position": "top",
	    		                        formatter: function(p) {
	    		                            return p.value > 0 ? (p.value) : '';
	    		                        }
	    		                    }
	    		                }
	    		            },
	    		            "data": [
	    		                1036,
	    		                3693,
	    		                2962,
	    		                3810,
	    		                2519,
	    		                1915,
	    		                1748,
	    		                4675,
	    		                6209,
	    		                4323,
	    		                2865,
	    		                4298
	    		            ]
	    		        },*/
	    		    ]
	    		};
	    			
		var cuOptionRight = echarts.init(document.getElementById('echartSmallRightBox'));
		cuOptionRight.setOption(optionRight);
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
	
}








//安全质量
var flowerRightBottomMonitor=function(){
	$(".flower-right-bottom").off('click').on('click',function(){
        $("#caiIdDes").removeClass('hidden'); //分公司选择隐藏
        $('#safeQualitityBox').removeClass('hidden');
        $('#scheduleBox').addClass('hidden');//进度
        $("#listBox").addClass('hidden'); //部门
        $("#echartSmallLeftBox").addClass("hidden");  //db-承包方左
        $("#echartSmallRightBox").addClass("hidden");  //db-承包方右

        $('.fourLeaf').removeClass('fourLeafClcik');
        $(".right-bottom-btn").addClass('fourLeafClcik');
        $(".btn").removeClass('clickEffect');
        $("#safeQualitityTopTen").addClass('clickEffect');
        $('.unit').addClass('hidden');
        $('.cuName').addClass('hidden');
        $('.projectSchedule').addClass('hidden');
        //安全质量对应按钮
        $('.safeQualitity').removeClass('hidden');
        deductionPerMonth();
	});
}

var  deductionPerMonth = function(corp) {
    corp = corp || "1102";
    $.ajax({
        type : 'POST',
        url : '/constructionStatisticalAnalysis/queryInspectionRecord',
        success : function(data) {
        	console.info(data);
            var result = JSON.parse(data);
            var upId = [];
            var num = [];
            for (var i = 0; i < result.length; i++) {
                upId[i] = result[i].upId;
                num[i] = result[i].num;
            }
            console.info(upId);
            console.info(num);
            var deductionPerMonth = echarts.init(document.getElementById('safeQualitityBox'));

            require.config({
                paths : {
                    echarts : 'assets/plugins/echarts/build/dist/'
                }
            });
            require([ 'echarts', '/assets/plugins/echarts/build/dist/chart/bar', '/assets/plugins/echarts/build/dist/chart/line',	'/assets/plugins/echarts/build/dist/chart/pie' ], function(ec) {
                // deductionPerMonth(ec);
            });

            zrColor = require('zrender/tool/color'), colorList = [ '#ff9824',
                '#0dbacd', '#ffb980', '#1dcc9f','rgba(255,144,128,1)', '#ff9824',
                '#0dbacd', '#ffb980', '#1dcc9f','rgba(255,144,128,1)' ],
                itemStyle = {
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

var safeOptionRight=function(){
	
	$.ajax({
	    type: 'POST',
	    url: '/projTS/queryContructionUnitSafetyNums',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info("施工处安全质量扣分统计----");
	    	console.info(data);
	    	var data=JSON.parse(data);
	    	var dataNum=data.dataNum;
	    	optionRight = {
	    			title: {
	    		        text: '施工管理处安全质量检查统计',
	    		        left: 'center',
	    		        top:'92%',
	    		        textStyle: {
	    		            color: '#fff',
	    		            fontSize:18,
	    		            fontFamily: '黑体'
	    		        }
	    		    },
	    			color : ['#ff9824','#0dbacd','#ffb980','#1dcc9f','rgba(255,144,128,1)'],
	    		    tooltip: {
	    		        trigger: 'item',
	    		        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    		    },
	    		    legend: {
	    		        orient: 'vertical',
	    		        x: 'left',
	    		        data:['一处','二处','三处','四处','五处'],
	    		        textStyle: {
	    		            color: '#fff',
	    		            fontSize:12,
	    		            fontFamily: '黑体'
	    		        }
	    		    },
	    		    series: [
	    		        {
	    		            name:'数量',
	    		            type:'pie',
	    		            selectedMode: 'single',
	    		            radius: [0, '60%'],

	    		            label: {
	    		                normal: {
	    		                    position: 'inner'
	    		                }
	    		            },
	    		            labelLine: {
	    		                normal: {
	    		                    show: false
	    		                }
	    		            },
	    		            data:dataNum
	    		            	/*[
	    		                {value:45, name:'一处', selected:true},
	    		                {value:22, name:'二处'},
	    		                {value:45, name:'三处'},
	    		                {value:34, name:'四处'} ,
	    		                {value:14, name:'五处'}
	    		            ]*/
	    		        }
	    		    ]
	    		};
	    	var safeOptionRight = echarts.init(document.getElementById('echartsBoxTopLeft'));
	    	safeOptionRight.setOption(optionRight);
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});	
}



//安全质量排行//Top10
var safeQualitityTopTenMonitor=function(){
	$("#safeQualitityTopTen").off('click').on('click',function(){
		$(".btn").removeClass('clickEffect');
		$("#safeQualitityTopTen").addClass('clickEffect');
		$("#echartsBoxTopLeft").addClass('hidden');
		$("#echartsBoxTopRight").addClass('hidden');
		$("#echartsBox").removeClass('hidden');
		//deductionPerMonth();
		// $("#echartsBox").cgetContent("/projTS/viewSafePage");
	});
}
//安全质量扣分 //分包单位饼图
var safeQualitityUnitLineMonitor=function(){
	
	$("#safeQualitityUnitLine").off('click').on('click',function(){
		$(".btn").removeClass('clickEffect');
		$("#safeQualitityUnitLine").addClass('clickEffect');
		$("#echartsBoxTopLeft").removeClass('hidden');
		$("#echartsBoxTopRight").removeClass('hidden');
		$("#echartsBox").addClass('hidden');
		
		$.ajax({
		    type: 'POST',
		    url: '/projTS/queryCuUnitSafetyNums',
		    contentType: "application/json;charset=UTF-8",
		    data: '',
		    success: function (data) {
		    	console.info("//安全质量扣分--分包单位饼图");
		    	console.info(data);
		    	var json=JSON.parse(data);
		    	var dataNum=json.dataNum;
				var option = {
						title: {
		    		        text: '分包单位安全质量检查统计',
		    		        left: 'center',
		    		        top:'92%',
		    		        textStyle: {
		    		            color: '#fff',
		    		            fontSize:18,
		    		            fontFamily: '黑体'
		    		        }
		    		    },
						color : ['#ff9824','#0dbacd','#ffb980','rgba(255,144,128,1)','#1dcc9f'],
					    tooltip: {
					        trigger: 'item',
					        formatter: "{a} <br/>{b}: {c} ({d}%)"
					    },
					    legend: {
					        orient: 'vertical',
					        x: 'left',
					        data:['聚泰鑫原','宇润天成','隆川元','顺兴德','昊菲达尔','凯睿特','豫世龙','大盛建筑','卓越','亿鸿祥'],
					        textStyle: {
					            color: '#fff',
					            fontSize:12,
					            fontFamily: '黑体'
					        }
					    },
					    series: [
					        {
					            name:'数量',
					            type:'pie',
					            selectedMode: 'single',
					            radius: [0, '60%'],

					            label: {
					                normal: {
					                    position: 'inner'
					                }
					            },
					            labelLine: {
					                normal: {
					                    show: false
					                }
					            },
					            data:dataNum
					            	/*[
					                {value:45, name:'聚泰鑫原', selected:true},
					                {value:22, name:'宇润天成'},
					                {value:45, name:'隆川元'},
					                {value:34, name:'顺兴德'} ,
					                {value:14, name:'昊菲达尔'},
					                {value:24, name:'凯睿特'},
					                {value:12, name:'豫世龙'},
					                {value:12, name:'大盛建筑'},
					                {value:12, name:'卓越'},
					                {value:42, name:'亿鸿祥'}
					            ]*/
					        }/*,
					        {
					            name:'金额',
					            type:'pie',
					            radius: ['42%', '55%'],

					            data:[
					                {value:200000.00, name:'聚泰鑫原', selected:true},
					                {value:222445.33, name:'宇润天成'},
					                {value:342352.45, name:'隆川元'},
					                {value:423234.23, name:'顺兴德'} ,
					                {value:124000.02, name:'昊菲达尔'},
					                {value:231424, name:'凯睿特'},
					                {value:100083, name:'豫世龙'},
					                {value:232435, name:'大盛建筑'},
					                {value:432435, name:'卓越'},
					                {value:632435, name:'亿鸿祥'}
					            ]
					        }*/
					    ]
					};
				var safeOptionLeft = echarts.init(document.getElementById('echartsBoxTopRight'));
				safeOptionLeft.setOption(option);
				safeOptionRight();
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		        console.warn("ajax queryScaleDetail...fail");
		    }
		});
		
		
	});
}
//安全质量曲线
var safeQualitityCuLineMonitor=function(){
	//曲线
	$('#safeQualitityCuLine').off('clcik').on('click',function(){
		$(".btn").removeClass('clickEffect');
		$("#safeQualitityCuLine").addClass('clickEffect');
		$("#echartsBox").removeClass('hidden');
		$("#echartsBoxTopLeft").addClass('hidden');
		$("#echartsBoxTopRight").addClass('hidden');
		
		
		$.ajax({
		    type: 'POST',
		    url: '/projTS/queryContructionUnitScore',
		    contentType: "application/json;charset=UTF-8",
		    data: '',
		    success: function (data) {
		    	console.info("安全质量曲线------------");
		    	console.info(data);
		    	var data=JSON.parse(data);
		    	var xAxisData=data.xAxisData,
		    	constructionNum0=data.constructionNum0,
		    	constructionNum1=data.constructionNum1,
		    	constructionNum2=data.constructionNum2,
		    	constructionNum3=data.constructionNum3,
		    	constructionNum4=data.constructionNum4;

				
				
				
				
				
				
				
				
				var symbolSize = 10;
				/*var data = [
				    ['一月', 2],
				    ['二月', 4],
				    ['三月', 6],
				    ['四月', 6],
				    ['五月', 12],
				    ['六月', 24],
				    ['七月', 24],
				    ['八月', 22],
				    ['九月', 20],
				    ['十月', 22],
				    ['十一月', 22],
				    ['十二月', 2]
				];*/
				// var data = [250, 300, 280, 200];

				option = {
					color : ['#ff9824','#0dbacd','#ffb980','#1dcc9f','rgba(255,144,128,1)'],
				    /*title: {
				        text: '施工各处安全质量检查',
				        left: 'center',
				        top: '96%',
				        textStyle: {
				            color: '#fff',
				            fontSize:18,
				            fontFamily: '黑体'
				        }
				    },*/
				    tooltip: {
				        trigger: 'axis'
				            // triggerOn: 'mousemove',
				            // triggerOn: 'none',
				            // formatter: function (params) {
				            //     return params.data[1].toFixed(0);
				            // }
				    },
				    legend: {
				        x: 'left',                         
				        y: 'middle',  
				        orient: 'vertical',
				        data: ['一处', '二处', '三处', '四处', '五处'],
				        textStyle: {
				            color: '#fff',
				            fontSize:12,
				            fontFamily: '黑体'
				        }
				    },
				    grid: {
				        left: '150px;',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        data: xAxisData
				        	/*['一月', '二月', '三月', '四月', '五月', '六月','七月','八月','九月','十月','十一月','十二月']*/,
				        axisLine: {
				            lineStyle: {
				                color: '#fff'
				            }
				        },
				        axisLabel:{
	                           show:true,
	                           interval:1
	                       },
				    },
				    yAxis: {
				        type: 'value',
				        axisLine: {
				            lineStyle: {
				                color: '#fff'
				            }
				        },
				    },
				    series: [{
				        id: 'a',
				        name: '一处',
				        type: 'line',
				        smooth: true,
				        symbolSize: symbolSize,
				        data: constructionNum0
				    }, {
				        name: '二处',
				        type: 'line',
				        smooth: true,
				        data:constructionNum1
				        	/* [
				            ['一月', 2],
				            ['二月', 4],
				            ['三月', 8],
				            ['四月', 8],
				            ['五月', 12],
				            ['六月', 22],
				            ['七月', 28],
				            ['八月', 20],
				            ['九月', 20],
				            ['十月', 18],
				            ['十一月', 25],
				            ['十二月', 2]
				        ]*/
				    }, {
				        name: '三处',
				        type: 'line',
				        smooth: true,
				        data:constructionNum2
				        	/* [
				            ['一月', 2],
				            ['二月', 4],
				            ['三月', 8],
				            ['四月', 8],
				            ['五月', 12],
				            ['六月', 12],
				            ['七月', 14],
				            ['八月', 10],
				            ['九月', 20],
				            ['十月', 18],
				            ['十一月', 18],
				            ['十二月', 2]
				        ]*/
				    }, {
				        name: '四处',
				        type: 'line',
				        smooth: true,
				        data: constructionNum3
				        	/*[
				            ['一月', 2],
				            ['二月', 4],
				            ['三月', 8],
				            ['四月', 18],
				            ['五月', 22],
				            ['六月', 22],
				            ['七月', 20],
				            ['八月', 20],
				            ['九月', 20],
				            ['十月', 18],
				            ['十一月', 22],
				            ['十二月', 12]
				        ]*/
				    }, {
				        name: '五处',
				        type: 'line',
				        smooth: true,
				        data: constructionNum4
				        	/*[
				           ['一月', 2],
				            ['二月', 4],
				            ['三月', 6],
				            ['四月', 12],
				            ['五月', 12],
				            ['六月', 24],
				            ['七月', 14],
				            ['八月', 20],
				            ['九月', 20],
				            ['十月', 16],
				            ['十一月', 20],
				            ['十二月', 6]
				        ]*/
				    }]
				};

		/*

				myChart.on('dataZoom', updatePosition);

				function updatePosition() {
				    myChart.setOption({
				        graphic: echarts.util.map(data, function(item, dataIndex) {
				            return {
				                position: myChart.convertToPixel('grid', item)
				            };
				        })
				    });
				}

				function showTooltip(dataIndex) {
				    myChart.dispatchAction({
				        type: 'showTip',
				        seriesIndex: 0,
				        dataIndex: dataIndex
				    });
				}

				function hideTooltip(dataIndex) {
				    myChart.dispatchAction({
				        type: 'hideTip'
				    });
				}

				function onPointDragging(dataIndex, dx, dy) {
				    var value = myChart.convertFromPixel('grid', this.position);
				    data[dataIndex][1] = value[1].toFixed(0);

				    // Update data
				    myChart.setOption({
				        series: [{
				            id: 'a',
				            data: data
				        }]
				    });
				}*/
				var cuOptionRight = echarts.init(document.getElementById('echartsBox'));
				cuOptionRight.setOption(option);
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		        console.warn("ajax queryScaleDetail...fail");
		    }
		});

		
	})	
}
var flowerHeartMonitor=function(){
	$(".flower-heart").off().on('click',function(){
		//alert('7');
	});
}








/**
 * 初始化工程列表
 */
projectConstructList = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("/js/ellipsis.js").done(function(){
	        	handleProjectConstructList();
	        	
        	})
        }
    };
}();

var loadProjectMap=function(){

    $("#mapBox").css({opacity: 0});

    // 百度地图API功能
    var map = new BMap.Map("mapBox");
    map.centerAndZoom("乌鲁木齐", 10);
    map.enableScrollWheelZoom();


    var b = new BMap.Bounds(new BMap.Point(87.365177, 43.969272), new BMap.Point(87.834308, 43.581793));
    try {
        BMapLib.AreaRestriction.setBounds(map, b);
    } catch (e) {
        alert(e);
    }

    map.setMapStyle({
        styleJson: [
            {
                "featureType": "land",
                "elementType": "geometry",
                "stylers"    : {
                    "color": "#e7f7fc"
                }
            },
            {
                "featureType": "water",
                "elementType": "all",
                "stylers"    : {
                    "color": "#96b5d6"
                }
            },
            {
                "featureType": "green",
                "elementType": "all",
                "stylers"    : {
                    "color": "#b0d3dd"
                }
            },
            {
                "featureType": "highway",
                "elementType": "geometry.fill",
                "stylers"    : {
                    "color": "#a6cfcf"
                }
            },
            {
                "featureType": "highway",
                "elementType": "geometry.stroke",
                "stylers"    : {
                    "color": "#7dabb3"
                }
            },
            {
                "featureType": "arterial",
                "elementType": "geometry.fill",
                "stylers"    : {
                    "color": "#e7f7fc"
                }
            },
            {
                "featureType": "arterial",
                "elementType": "geometry.stroke",
                "stylers"    : {
                    "color": "#b0d5d4"
                }
            },
            {
                "featureType": "local",
                "elementType": "labels.text.fill",
                "stylers"    : {
                    "color": "#7a959a"
                }
            },
            {
                "featureType": "local",
                "elementType": "labels.text.stroke",
                "stylers"    : {
                    "color": "#d6e4e5"
                }
            },
            {
                "featureType": "arterial",
                "elementType": "labels.text.fill",
                "stylers"    : {
                    "color": "#374a46"
                }
            },
            {
                "featureType": "highway",
                "elementType": "labels.text.fill",
                "stylers"    : {
                    "color": "#374a46"
                }
            },
            {
                "featureType": "highway",
                "elementType": "labels.text.stroke",
                "stylers"    : {
                    "color": "#e9eeed"
                }
            }
        ]
    });

    function renderPoints(){
        //工程位置绘制
        console.info("绘制坐标点");
        
        var post = {};
    	post.finishedDateStart = new Date().getFullYear() + '-01-01';
    	post = JSON.stringify(post);
    	//console.info(post);
    	$.ajax({
            type: 'POST',
            url:"/projectMap/queryProjectSign",
            data: post,
            dataType: 'json',
            contentType: "application/json;charset=UTF-8",
            success: function(data) {
            	handleMap(data);
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	printXHRError("projectQuery", "项目列表加载失败, 请重试", jqXHR, textStatus, errorThrown);
            }
    	});
    }
    function addPosMarker(map, point, text, style, content, dbContent ,projLtype){
    	
    	"use strict";
    	if(!point){
    		console.info("绘制Label标签却没有指定坐标点");
    		return false;
    	}
    	
    	style = style || {};
    	
    	text = text || '记录点';
    	
    	content = content || {};
    	
    	var getWidth = function(text){
            var text = text.replace("（","(").replace("）",")"),
            tarr = text.split("");
            return 14 * (text.indexOf("(") > -1 ? tarr.length - 1 : tarr.length);
        },
        cacuWidth = getWidth(text),
        offsetX = style.offsetX || 0 - cacuWidth / 2 + 3,
    	offsetY = style.offsetY || -24,
    	width = style.width || cacuWidth + 5 + "px",
    	height = style.height || '21px',
    	lineHeight = style.lineHeight || '16px',
    	color = style.color || 'red',
    	fontSize = style.fontSize || '14px',
    	padding = style.padding || '2px',
    	border = style.border || 'red 1px solid';/* ,
    	icon = style.icon || ''; */
        var myIcon;
    	var myIcon1 =  new BMap.Icon("/images/map/blue.png" , new BMap.Size(19,25)) ;
    	var myIcon2 =  new BMap.Icon("/images/map/green.png", new BMap.Size(19,25));
    	var myIcon3 =  new BMap.Icon("/images/map/yellow.png", new BMap.Size(19,25)) ;
    	//console.info('dbContentprojLtype...'+projLtype.projLtypeId);
    	var typeId = projLtype.projLtypeId.split(",");
    	for(var i=0;i<typeId.length;i++){
    		if(typeId[i] == "11"){
    			myIcon=myIcon1;
    			break;
        	}else if(typeId[i] == "12"){
        		myIcon=myIcon2;
    			break;
        	}else if(typeId[i] == "13"){
        		myIcon=myIcon2;
    			break;
        	}
    	}
    	
    	var marker = myIcon ? new BMap.Marker(point, {icon: myIcon}) : new BMap.Marker(point),

    	tipCon = '<hr/>' + (content.text || '暂无介绍'),
    	tipStyle = content.style || {
    		width: 200,
    		height: 120,
    		title: "<b>记录点详情</b>",
    		enableMessage: false
    	},
    	
    	label = new BMap.Label(text,{
    		position : point,
    		offset:new BMap.Size(offsetX, offsetY)
    	});
    	label.setStyle({
    		color : color,
    		fontSize : fontSize,
    		height : height,
    		width : width,
    		lineHeight : lineHeight,
    		padding: padding,
    		border: border
    	});
    	//marker.setLabel(label);
    	
    	label.addEventListener("mouseover", function(e){
    		marker.setTop(true);
    	});
    	label.addEventListener("mouseout", function(e){
    		marker.setTop(false);
    	});
    	label.addEventListener("dblclick", function(e){
    		map.setCenter(point);
    		map.zoomIn();
    	});
    	marker.addEventListener("mouseover", function(e){
    		marker.setTop(true);
    	});
    	marker.addEventListener("mouseout", function(e){
    		marker.setTop(false);
    	});
    	
        //添加鼠标点击监听
    	marker.addEventListener("click",function(e){
    		var p = e.target,
    		infoWindow = new BMap.InfoWindow(tipCon,tipStyle);  // 创建信息窗口对象
    		map.openInfoWindow(infoWindow,point); //开启信息窗口
    	});
    	
    	function openDetailWindow(){
    		var url = "#projectView/detailPage?projId=" + dbContent.pid;
    		//加载弹屏
    		$("body").cgetPopup({
    			title: '工程信息详述',
    			content: url,
    			ahide: true,
    			ccolor: '#59727D',
    			ctext: '确定',
    			size:'detail',
    			nocache: true,
    			icon: 'fa-file-text'
    		});
    	}
    	
    	if(dbContent && jsonLength(dbContent)){
    		if(dbContent.pid){
    			marker.addEventListener("rightclick",function(e){
    				openDetailWindow();
    			});
    			label.addEventListener("click", function(e){
    				openDetailWindow();
    			})
    		}
    	}
    	
    	map.addOverlay(marker);
    }
    
    var handleMap = function(data) {
    	"use strict";
    	
       //计算地图中心点、缩放级别
    	var dataLength = data.length;
    	if(!dataLength){
    		map.centerAndZoom(new BMap.Point(103.388611,35.563611), 5);
    		$(".panel-body").hideMask();
    	}else{
    		//drawFitArea(data);

    	    /*/百度地图API功能
    		//创建Map实例
    		//初始化地图,设置中心点坐标和地图级别43.8312840000,87.6235480000
    		map.centerAndZoom(new BMap.Point(centerX, centerY), getZoom(map, maxX, minX, maxY, minY));
    		//添加地图类型控件
    		map.addControl(new BMap.MapTypeControl());
    		map.addControl(new BMap.NavigationControl());
    		map.enableScrollWheelZoom(true);
    		 */
    		for(var i=0; i<dataLength; i++){
    			drawProjMarker(data[i]);
    		}
    		
    		$(".panel-body").hideMask();
    	} 
    	
    	function drawFitArea(data){
            var centerX = 0,
                centerY = 0,
                leftTop,
                rightTop,
                leftBottom,
                rightBottom,
                d0 = data[0],
                maxX = parseFloat(d0.projLongitude),
                maxY = parseFloat(d0.projLatitude),
                minX = parseFloat(d0.projLongitude),
                minY = parseFloat(d0.projLatitude);
            //console.info(d0.signs);
            for(var i = dataLength - 1; i >= 0; i--){
                var d = data[i];
                //console.info(d.projLatitude + " - " + i);
                if(!d.projLongitude) continue;
                maxX = maxX < parseFloat(d.projLongitude) ? parseFloat(d.projLongitude) : maxX;
                maxY = maxY < parseFloat(d.projLatitude) ? parseFloat(d.projLatitude) : maxY;
                minX = minX > parseFloat(d.projLongitude) ? parseFloat(d.projLongitude) : minX;
                minY = minY > parseFloat(d.projLatitude) ? parseFloat(d.projLatitude) : minY;
            }

            centerX = (maxX + minX) / 2;
            centerY = (maxY + minY) / 2;

            leftTop = new BMap.Point(minX, minY);
            rightTop = new BMap.Point(maxX, minY);
            leftBottom = new BMap.Point(minX, maxY);
            rightBottom = new BMap.Point(maxX, maxY);

            var polygon = new BMap.Polygon([leftTop, rightTop, rightBottom, leftBottom], {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});  //创建多边形
            polygon.addEventListener("click", function(){
                map.centerAndZoom(new BMap.Point(centerX, centerY), 12);
            });
            map.addOverlay(polygon);   //增加多边形
        }

    	
    	//绘制项目位置标记和签字标记
    	function drawProjMarker(pinfo){
    		if(!pinfo.projLongitude || !pinfo.projLatitude){
    			$("#projsallmap").html('<p class="m-t-20 m-l-20">该项目缺少坐标数据, 无法查看地图位置</p>');
    			console.warn("项目缺失坐标数据");
    			return false;
    		}
    		var point = new BMap.Point(pinfo.projLongitude, pinfo.projLatitude),
    		oval = new BMap.Circle(point, 100, {fillColor:"red", fillOpacity: 0.1, strokeColor:"red", strokeWeight:1, strokeOpacity:0.2});
    		var text = '<img style="float:right;margin:4px; margin-top: -5px;" src="images/summary/jhjd.jpg" width="139" height="104" /><p>项目名称：';
    		text += pinfo.projName;
    		text += '</p><p>项目地址：';
    		text += pinfo.projAddr;
    		text += '</p><p>项目规模：';
    		text += pinfo.projScaleDes;
    		text += '</p>';
    		
    		addPosMarker(map, point, '项目位置', '', {
    			text: text,
    			style: {
    				width: 500,
    				height: 180,
    				title: "<b>项目简介</b>",
    				enableMessage: false
    			}
    		},{
    			pid: pinfo.projId
    		},{
    			projLtypeId:pinfo.projLtypeId
    		});		
    		map.addOverlay(oval);

    		/* //绘制签字坐标点
    		var signMarker = pinfo.signs,
    		signMarkerLength = signMarker.length,
    		//定义图标和颜色库，供随机调用
    		icons = [
    		    {"icon":"1.png", "color":"#ed2d2d"},
    		    {"icon":"2.png", "color":"#00d000"},
    		    {"icon":"3.png", "color":"#16bfff"},
    		    {"icon":"4.png", "color":"#ff58ff"},
    		    {"icon":"5.png", "color":"#ccb400"},
    		    {"icon":"6.png", "color":"#ffad00"},
    		    {"icon":"7.png", "color":"#ff52ff"},
    		    {"icon":"8.png", "color":"#d24cff"},
    		    {"icon":"9.png", "color":"#00a4ff"},
    		    {"icon":"10.png", "color":"#0098d3"}
    		];		
    		
    		for(var j=0; j<signMarkerLength; j++){
    			var sd = signMarker[j];
    			if(sd.longitude && sd.latitude){
    				var title = sd.menuDes ? sd.menuDes + '签字地点' : '签字地点',
    				icon = icons[Math.floor(Math.random()*10)],
    				text = '<p><img style="max-width: 100%; height: auto;" src="attachments/sign/';
    				text += sd.imgUrl || 'blank.png';
    				text += '"></p><p>所属项目：<br>';
    				text += pinfo.projName;
    				text += '</p><p>签字时间：<br>';
    				text += format(sd.signTime, 'all');
    				text += '</p>';
    				addPosMarker(map, new BMap.Point(sd.longitude, sd.latitude), title, {
    					icon: icon.icon,
    					color: icon.color,
    					border: icon.color + " 1px solid"
    				},{
    					text: text,
    					style: {
    						width: 220,
    						height: 220,
    						title: sd.menuDes ? sd.menuDes : '签字地点',
    						enableMessage: false
    					}
    				});
    			}else{
    				console.warn("项目个别签字环节缺失坐标数据");
    			}
    		} */
    	}
    	
    };
    
    

    function getBoundary() {

        var hideArea = [
//            "乌鲁木齐米东区",
                "五家渠市",
                "乌鲁木齐达坂城",
                "新疆石河子",
                "新疆乌鲁木齐县",
//            "新疆博湖县",
//            "新疆库尔勒",
                "新疆吐鲁番地区",
//            "新疆奇台县",
//            "新疆阜康",
                "新疆哈密地区",
//            "新疆克拉玛依",
//            "新疆奎屯",
//            "新疆新源县",
//            "新疆轮台县",
                "新疆阿勒泰地区",
                "新疆博尔塔拉蒙古自治州",
                "新疆塔城地区",
                "新疆巴音郭楞蒙古自治州",
                "新疆伊犁哈萨克自治州",
                "新疆阿克苏地区",
//            "新疆和田地区",
                "新疆喀什地区",
                "新疆和田地区",
                "昌吉回族自治州",
                "甘肃省",
                "西藏",
                "青海省",
                "内蒙古",
                "乌鲁木齐米东区",
            ],

            drawLine = [
                "乌鲁木齐水磨沟区",
                "乌鲁木齐天山区",
                "乌鲁木齐沙依巴克区",
                "乌鲁木齐头屯河区",
                "乌鲁木齐新市区"
            ],

            points = [
                "87.365177,43.999672",
                "87.75037,43.961893",
                "87.457163,43.722621",
                "87.834308,43.731793"
            ]

        pointArray = [];

        function drawHideArea(area) {

            var bdary = new BMap.Boundary();
            bdary.get(area, function (rs) {
                var count = rs.boundaries.length;
                if (count === 0) {
                    console.warn(area);
                    return;
                }
                for (var i = 0; i < count; i++) {
                    var ply = new BMap.Polygon(rs.boundaries[i], {
                        strokeWeight: 1, strokeColor: "#012841", fillOpacity: 1, fillColor: "#012841"
                    });
                    map.addOverlay(ply);
                    pointArray = pointArray.concat(ply.getPath());
                }
            });
            map.setViewport(pointArray);    //调整视野
        }

        function drawLineArea(area) {

            var bdary = new BMap.Boundary();
            bdary.get(area, function (rs) {
                var count = rs.boundaries.length;
                if (count === 0) {
                    console.warn(area);
                    return;
                }
                for (var i = 0; i < count; i++) {
                    var ply = new BMap.Polygon(rs.boundaries[i], {
                        strokeWeight: 2, strokeColor: "#ff3300", fillOpacity: 0.0000001
                    });
                    map.addOverlay(ply);
                    pointArray = pointArray.concat(ply.getPath());
                }
            });
            map.setViewport(pointArray);    //调整视野
        }

        for (var i = hideArea.length - 1; i >= 0; i--) {
            drawHideArea(hideArea[i]);
        }

        for (var i = drawLine.length - 1; i >= 0; i--) {
            drawLineArea(drawLine[i]);
        }

        setTimeout(function(){
            $("#mapBox").css({opacity: 1});

            renderPoints();

        },2000)

    }

    setTimeout(function () {
        getBoundary();
    }, 500);
};

