var projectTotalStatics = function()
    {
    	console.info('------------------');
    	$.ajax({
    	    type: 'POST',
    	    url: '/projectTotalStatistics/projectTypeStatistics',
    	    contentType: "application/json;charset=UTF-8",
    	    data: '',
    	    success: function (data) {
    	    	parseReturnTotal(data);
    	    },
    	    error: function (jqXHR, textStatus, errorThrown) {
    	        console.warn("ajax queryScaleDetail...fail");
    	    }
    	});
    }
    

    function parseReturnTotal(data)
    {
    	var vJsonData = JSON.parse(data);
    	
    	option = {
    		    tooltip: {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b}: {c} ({d}%)"
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        x: 'right',
    		        data:vJsonData.title,
    		        right:'30px',
    		        top:'8%',
    		        itemGap:15
    		    },
    		    series: [
    		        {
    		            name:'区域',
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
    		            data:vJsonData.area
    		        },
    		        {
    		            name:'工程规模',
    		            type:'pie',
    		            radius: ['35%', '55%'],
    		            label: {
    		                normal: {
    		                	show: true
    		                }
    		            },
    		            data:vJsonData.scale
    		        }
    		    ]
    		};
    	
    	var projTotal = echarts.init(document.getElementById('projTotal'));
    	projTotal.setOption(option);
    	$("#projTotal").hideMask();
    };
    
    var projectSankey = function(){
    	$.ajax({
    	    type: 'POST',
    	    url: '/projectTotalStatistics/projectSankey',
    	    contentType: "application/json;charset=UTF-8",
    	    data: '',
    	    success: function (data) {
    	    	parseReturnSankey(data);
    	    },
    	    error: function (jqXHR, textStatus, errorThrown) {
    	        console.warn("ajax queryScaleDetail...fail");
    	    }
    	});
    };
    

    function parseReturnSankey(data)
    {
    	var vJsonData = JSON.parse(data);
    	//console.info(data);
        option = {
    	        
    	        tooltip: {
    	            trigger: 'item',
    	            triggerOn: 'mousemove'

    	        },
    	        series: [
    	            {
    	                type: 'sankey',
    	                layout:'none',
    	                data: vJsonData.nodes,
    	                links: vJsonData.links,
    	                itemStyle: {
    	                    normal: {
    	                        borderWidth: 1,
    	                        borderColor: '#aaa'
    	                    }
    	                },
    	                lineStyle: {
    	                    normal: {
    	                    	color: 'target',
    	                        curveness: 0.5
    	                        
    	                    }
    	                }
    	            }
    	        ]
    	    }
    	
    	var projSankey = echarts.init(document.getElementById('projSankey'));
    	
        projSankey.setOption(option);
        $("#projSankey").hideMask();
    }
    
    var projectPayment = function()
    {
    	$.ajax({
    	    type: 'POST',
    	    url: '/projectTotalStatistics/projectPaymenetDetail',
    	    contentType: "application/json;charset=UTF-8",
    	    data: '',
    	    success: function (data) {
    	    	parseReturnPayment(data);
    	    },
    	    error: function (jqXHR, textStatus, errorThrown) {
    	        console.warn("ajax queryScaleDetail...fail");
    	    }
    	});
    };
    

    function parseReturnPayment(data)
    {
    	var vJsonData = JSON.parse(data);
    	//console.info(data);
    	option = {
    			title: {
    	            text: ''
    	            
    	        },
    		    tooltip : {
    		        trigger: 'axis'
    		    },
    		    toolbox: {
    		        show : false,
    		        feature : {
    		            mark : {show: true},
    		            dataView : {show: true, readOnly: false},
    		            magicType: {show: true, type: ['line', 'bar']},
    		            restore : {show: true},
    		            saveAsImage : {show: true}
    		        }
    		    },
    		    calculable : true,
    		    legend: {
    		    	y:'top',
    		        data:['实收金额','实付金额','累计实收金额','累计实付金额']
    		    },
    		    xAxis : [
    		        {
    		            type : 'category',
    		            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
    		        }
    		    ],
    		    yAxis : [
    		        {
    		            type : 'value',
    		            name : '收付金额',
    		            axisLabel : {
    		                formatter: '{value}'
    		            }
    		        },
    		        {
    		            type : 'value',
    		            name : '累计金额',
    		            axisLabel : {
    		                formatter: '{value}'
    		            }
    		        }
    		    ],
    		    series : [

    		        {
    		            name:'实收金额',
    		            type:'bar',
    		            data:vJsonData.receive
    		        },
    		        {
    		            name:'实付金额',
    		            type:'bar',
    		            data:vJsonData.payement
    		        },
    		        {
    		            name:'累计实收金额',
    		            type:'line',
    		            yAxisIndex: 1,
    		            data:vJsonData.receiveSum,
    		            
    		            lineStyle: {
    		                normal: {
    		                    type: 'solid'
    		                }
    		            }
    		        },
    		        {
    		            name:'累计实付金额',
    		            type:'line',
    		            yAxisIndex: 1,
    		            data:vJsonData.payementSum,
    		            
    		            lineStyle: {
    		                normal: {
    		                    type: 'dashed'
    		                }
    		            }
    		        }
    		    ]
    		};
    	
    	var projPayment = echarts.init(document.getElementById('projPayment'));
    	//console.info("------------------------ projPayment");
    	//console.info(option);
        projPayment.setOption(option);
        $("#projPayment").hideMask();
    }
    
    


var projectStatics = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	projectTotalStatics();
            projectSankey();
            projectPayment();
        }
    };
}();