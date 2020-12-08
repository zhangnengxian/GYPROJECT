<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link rel="stylesheet" href="/assets/plugins/jQuery-Gantt/index_files/css/style.css">
<link rel="stylesheet" href="/assets/plugins/jQuery-Gantt/index_files/css/prettify.css">
<!-- ================== END PAGE LEVEL STYLE ================== -->
<style>
	.project-flow-image-box li img{
		width:72px;
		height:72px;
		left:2;
	}
</style>
<div class="panel-body p-t-0 p-b-0 p-l-0 p-r-0">
	<div class="infodetails" id="in">
		<input class="hidden projectId" value="${projId}">
		<h3>工程进度</h3>
		<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>    
	    <div class="tab-pane fade in btn-top" id="">
		    <ul class="nav nav-tabs p-t-5 p-l-5">
				<li class="active"><a href="#tab-1" data-toggle="tab" id="ganttTab">甘特图</a></li>
				<li class="" id="flowTabLi"><a href="#tab-2" data-toggle="tab" id="">流程图</a></li>
			</ul>
			<div class="tab-content p-l-0 p-r-0 p-t-5" style="box-shadow:0 0 0 0">
				<div class="tab-pane fade active in btn-top" id="tab-1" >
					<div class="gantt"></div>
				</div>
				<div class="tab-pane fade  in btn-top" id="tab-2" >
					<div class="project-flow-image-box">
						<ul class="flow-list">
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1101" title="受理"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1201" title="一级派遣"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="12011" title="二级派遣"></li>
							<li class="row1 m-l-2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1202" title="现场勘查"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1203" title="方案审核"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1204" title="设计出图"></li>
							<li class="row1" style="margin-left: -2px" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1301" title="图纸确认"></li>
							<li class="row1" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1302" title="预算记录"></li>
							<li class="row2 m-t-3" style="margin-bottom: -3px" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1601" title="工程量申报"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1502" title="计划审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1501" title="下达计划"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1403" title="合同审核"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1402" title="施工合同签订"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1103" title="立项确认"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1401" title="造价确认"></li>
							<li class="row2" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1205" title="正式发图"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1602" title="工程量审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1603" title="分包合同"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1604" title="分包合同审核"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1701" title="施工"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1801" title="自检"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1802" title="预验收"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1803" title="联合验收"></li>
							<li class="row3" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1901" title="结算报审"></li>
							<li class="row4 indent" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1806" title="资料交接确认"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1805" title="资料反馈"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1804" title="资料发放"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="19031" title="终审确认"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="19021" title="初审确认"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="1902" title="结算初审"></li>
							<li class="row4" data-toggle="popover" data-trigger="focus" data-html="true" data-placement="top" data-stepid="19011" title="报审确认"></li>
						</ul>
					</div>
				</div>
				
			</div>
		</div>
		
	</div>
	
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
	$(".infodetails").hideMask();
	
	$.getScript('/assets/plugins/jQuery-Gantt/index_files/js/jquery.fn.gantt.js').done(function () {
		$.getScript('/assets/plugins/jQuery-Gantt/index_files/js/bootstrap-tooltip.js').done(function () {
			$.getScript('/assets/plugins/jQuery-Gantt/index_files/js/bootstrap-popover.js').done(function () {
				$.getScript('/assets/plugins/jQuery-Gantt/index_files/js/prettify.js').done(function () {
					$(function() {
						"use strict";
						 var projId = $(".projectId").val();
						 $.ajax({
					            type: 'POST',
					            url: "/projectView/querySchedule?projId="+projId,
					            data: {},
					            dataType:'json',
					            success: function(data) { 
					            	var maxVal,scale="days",timeArr=[];
					            	for(var i=0;i<data.length;i++){
					            		var desc=data[i].desc.split("：");
					            		console.info(desc);
					            		var time=parseFloat(desc[1].substring(0,desc[1].length-1));
					            		timeArr.push(time);
					            		console.info(time);
					            		
					            	}
					            	for(var j=0;j<timeArr.length-1;j++){
					            		maxVal = Math.max(timeArr[j],timeArr[j+1])
					            		console.info(maxVal);
					            	}
					            	if(maxVal>46){
					            		scale="weeks"
					            	}else if(maxVal>46*7){
					            		scale="months"
					            	}
					            	//甘特图初始化
									$(".gantt").gantt({
										source: data,
										navigate: "scroll",
										months: ["1 月","2 月","3 月","4 月","5 月","6 月","7 月","8 月","9 月","10 月","11 月","12 月"],
										dow: ["日","一","二","三","四","五","六"], 
										scale: scale,
										maxScale: "months",
										minScale: "days",
									    itemsPerPage: 200,
							            waitText: "加载中...",
							            onRender:function() {
							            	for(var i=0;i<data.length;i++){
							            		if(i%2==0){
							            			$(".row"+i).addClass("del-bottom");
							            		}
							            		
							            	}
							        	}
									});
										$(".gantt").popover({
											selector: ".bar",
											title: "I'm a popover",
											content: "And I'm the content of said popover."
										});

										prettyPrint();
						 			
									
					            	}
					            });
					});
					
					
					
					
					$(function() {
						
						


					});
				
					//初始化流程图
					$("#flowTabLi").off().on("click",function(){
						var projId = $(".projectId").val();
						console.info('1.......'+projId);
						var data={};
						data.projId=projId;
						   $.ajax({
				                type: 'POST',
				                url: "/projectView/queryScheduleFlow",
				                data:data,
				                dataType: 'json',
				                success: function(data) {
				                	console.info(data);
				                	for(var i=0, l=data.length; i<l; i++){
				                		var info = data[i];
				                		var listItem = $('.flow-list [data-stepid="' + info.STEP_ID + '"]');
			                			listItem.attr("data-endtime", format(info.OPERATE_TIME, "all")).attr("data-starttime", (listItem.prev().data("endtime") ? listItem.prev().data("endtime") : "")).attr("data-stuffname", info.STAFF_NAME);

		                				var title = listItem.data("original-title"),
		                				cont = "";
			                			cont += '<p>操作人员：' + listItem.data("stuffname");
			                			if(listItem.data("starttime")) cont += "</p><p>开始时间：" + listItem.data("starttime");
			                			cont += '</p><p>结束时间：' + listItem.data("endtime");
			                			cont += '</p>';
			                			listItem.attr("data-content", cont);
			                			listItem.attr("title", title);
			                			console.info(i+"..."+info.PHOTO_URL)
			                			if(info.PHOTO_URL !== null){
				                			listItem.html('<img src="/attachments/photo/' + info.PHOTO_URL + '">');
				                		}else if(info.STAFF_NAME!==null && info.PHOTO_URL==null){
				                			listItem.html('<img src="/images/common/flow/user-13.jpg" class="opacity5">');
				                			
				                			//没有头像使用默认头像
				                			//listItem.html('<img src="images/common/flow/ferrinweb.png" class="opacity5">');
				                		}else{
				                			listItem.html('<img src="images/common/flow/ferrinweb.png" class="opacity5">');
				                		}
				                	}
		                			$(".flow-list").on("click", function(e){
		                				if($(e.target).is(".flow-list li img")){
		                					var li = $(e.target).parent();
			                				if(!li.attr('data-content')) return;
			                				li.siblings().popover('hide');
			                				li.popover('show');
		                				}else if($(e.target).is(".flow-list li")){
		                					var li = $(e.target);
			                				if(!li.attr('data-content')) return;
			                				li.siblings().popover('hide');
			                				li.popover('show');
		                				}else{
		                					$(this).children().popover('hide');
		                				}
				                	});
				                }
						   });
					});
				});
			});
		});
	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->