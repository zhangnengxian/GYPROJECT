<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- purge.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse tabs-mixin">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab" data-toggle="tab">列表区</a></li>
		                <li class=""><a href="#default-tab-2" id="signTab"  data-toggle="tab">报验区</a></li>
		                <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" id="">
               		<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
               			   <input type='hidden' id='pcIdNew'>
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="purgeTable" width="100%">
	                			<thead>
		                			<tr>
		                				<th></th>
		                				<th>报验日期</th>
			                			<th>施工工序</th>
			                			<th>查验结果</th>
		                			</tr>
	                			</thead>
	                		</table>
	                	</div>
               			<div class="tab-pane fade in btn-top" id="default-tab-2">
               				<div class=" f-r p-b-15 editBtn" >
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 giveupBtn">放弃</a>
						    </div>
						    <div class="clearboth form-box">
						    	<form class="form-horizontal" id="purgeForm" data-parsley-validate="true" action="purge/savePurge">
						    		<input type="hidden" id="pcId" name="pcId" class="addClear">
								 	<input type="hidden" id="pcDesId" name="pcDesId" value="${pcDesId}">
								 	<input type="hidden" id="projId" name="projId" >
								 	<input type="hidden" id="projNo" name="projNo" >
						    		<div class="form-group  col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projName" name="projName"/>
										</div>
									</div>
									<!-- <div class="form-group  col-sm-12">
										<label class="control-label" for="">单位名称</label>
										<div>
											<input type="text" class="form-control input-sm"  id="" name=""  />
										</div>
									</div> -->
									<div class="form-group  col-md-6 col-sm-12">
										<label class="control-label" for="process">施工工序</label>
										<div> 
											<input type="text" class="form-control input-sm field-editable addClear"  id="process" name="process"  data-parsley-required="true" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="slResultPage">附件数量</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="slResultPage" name="slResultPage"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label " for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionPrincipal">项目经理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionPrincipal" name="constructionPrincipal" value="">
											<input type="hidden" id="constructionPrincipal_postType" name="constructionPrincipal_postType" value="${deputyDirector}">
											<img class="constructionPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectionDate">检验日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="inspectionDate" name="inspectionDate"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="inspectionResult" id="inspectionResult" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionQc">质检员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQc" name="constructionQc" value="">
											<input type="hidden" id="constructionQc_postType" name="constructionQc_postType" value="${qualitativeCheckMember}">
											<img class="constructionQc" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="selfCheckDate">自检日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="selfCheckDate" name="selfCheckDate"  />
										</div>
									</div>
									<div class="form-group  col-sm-12">
				                    	<label class="control-label" for="suOpinion">监理意见</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="suOpinion" id="suOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group  col-sm-12">
										<label class="control-label" for="suName">监理公司</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  id="suName" name="suName"  />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="suJgj">现场监理人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${sujgj}">
											<img class="suJgj" alt="" src="images/sign-2.png" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checkDate">日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="checkDate" name="checkDate"  />
										</div>
									</div>
									<div class="form-group  col-sm-12">
				                    	<label class="control-label" for="suOpinion">甲方意见</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="suOpinion" id="suOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group  col-sm-12">
										<label class="control-label" for="suName">建设单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  id="suName" name="suName"  />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="suJgj">甲方代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${sujgj}">
											<img class="suJgj" alt="" src="images/sign-2.png" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checkDate">日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="checkDate" name="checkDate"  />
										</div>
									</div>
									 
						    	</form>
						    </div>
               			</div>
               			<div class="tab-pane fade in btn-top" id="default-tab-3">
               				<div class=" f-r p-b-15 editbtn" >
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 savebtn">保存</a>
						    </div>
						    <div class="clearboth form-box">
							    <form class="form-horizontal" id="purgeRecordForm" action="" data-parsley-validate="true">
							        <div class="form-group  col-sm-12">
				                    	<label class="control-label" for="suOpinion">楼号</label>
					                    <div> 
				                        	<textarea class="form-control field-editable" name="suOpinion" id="suOpinion" rows="3" cols="" data-parsley-maxlength="200" >3#、5#、6#、7#、8#、9#、10#、11#、12#、17#、18#、19#、20#、21#、22#、23#、25#、26#、30#、31#、32#、33#、35#</textarea>
					                    </div>
					  			    </div>
							    </form>
               				</div>
               				<table id="purgeAuditTable" class="table table-striped table-bordered nowrap " width="100%">
	                            <thead>
									<tr>
										<th width="350px">验收内容</th>
										<th width="50px">是否合格</th>
									</tr>
								</thead>
	                       	</table>
               			</div>
              		</div>	
               	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" id="content">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <h4 class="panel-title">预览区</h4>
			    </div>
			    <div class="panel-body" id="purge_panel_box">
			    	<div class="clearboth form-box">
	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:1; overflow-y:auto;" scrolling="no"></iframe>
	                  	</div>
	                </div>
			    </div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('立管验收 - 工程项目管理系统 ');

    $("#purgeForm").toggleEditState().styleFit();
    $("#purgeRecordForm").styleFit();
    $('.editBtn').addClass("hidden");
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.managementOffice); //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    }
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    var cptPath = '<%=basePath%>';
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var showReport1 = function(){
    	//定义数据项
    	var projName='',suName='',process='',slResultPage='',constructionUnit='',inspectionDate='',selfCheckDate='',inspectionResult='',suOpinion='',checkDate='';
    	var json=trSData.purgeTable.json;
    	if(json){
    	    projName = json.projName,			   //工程名称
    		suName=json.suName,					   //监理单位
    		process=json.process,			    	   //检验部位
    		slResultPage=json.slResultPage,    	   //页数
    		constructionUnit=json.constructionUnit,//施工单位
    		inspectionDate=json.inspectionDate,	   //报验日期
    		selfCheckDate=json.selfCheckDate,	   //自检日期
    		inspectionResult=json.inspectionResult,
    		suOpinion=json.suOpinion,			   //监理单位意见
    		checkDate=json.checkDate;			   //检查日期
    	}else{
    		projName =$('#projName').val(),			    //工程名称
    		suName=$('#suName').val(),				    //监理单位
    		process=$('#process').val(),					//检验部位
    		slResultPage=$('#slResultPage').val(),      //页数
    		constructionUnit=$('#constructionUnit').val(),//施工单位
    		inspectionDate=$('#inspectionDate').val(),    //报验日期
    		selfCheckDate=$('#selfCheckDate').val(),	  //自检日期
    		inspectionResult=$('#inspectionResult').val(),
    		suOpinion=$('#suOpinion').val(),			  //监理单位意见
    		checkDate=$('#checkDate').val();			  //检查日期
    	}
    	
    	//解决乱码
    	projName = encodeURIComponent(cjkEncode(projName)),				//工程名称
    	suName=encodeURIComponent(cjkEncode(suName)),					//监理单位
    	process=encodeURIComponent(cjkEncode(process)),			    	//检验部位
    	slResultPage=encodeURIComponent(cjkEncode(slResultPage)),       //页数
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)),//施工单位
    	inspectionResult=encodeURIComponent(cjkEncode(inspectionResult)),
    	suOpinion=encodeURIComponent(cjkEncode(suOpinion));			     //监理单位意见
    	
    	src = cptPath+"/ReportServer?reportlet=standPipe/standPipe.cpt&projName=" + projName+"&suName="+suName+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate;
    	src = src+"&inspectionDate="+inspectionDate+"&checkDate="+checkDate+"&suOpinion="+suOpinion+"&process="+process+"&slResultPage="+slResultPage+"&inspectionResult="+inspectionResult;
    	src = src+"&path=${path}"+"&pcId="+$('#pcIdNew').val();
    	$("#mainFrm").attr("src",src);
    };
    
    var showReport2 = function(){
    	var projName='',targetCheck='',constructionUnit='',suName='',selfCheckDate='',checkDate='',otherCheckDate='';
    	if(trSData.purgeTable.json){
    		pcId=trSData.purgeTable.json.pcId;
    		projName = trSData.purgeTable.json.projName;
    		targetCheck = trSData.purgeTable.json.targetCheck;
    		constructionUnit = trSData.purgeTable.json.constructionUnit;
    		suName = trSData.purgeTable.json.suName;
    		selfCheckDate = trSData.purgeTable.json.selfCheckDate;
    		checkDate = trSData.purgeTable.json.checkDate;
    		otherCheckDate = trSData.purgeTable.json.otherCheckDate;
    	}else{
    		projName =$('#projName').val(),			      //工程名称
    		suName=$('#suName').val(),				      //监理单位
    		constructionUnit=$('#constructionUnit').val(),//施工单位
    		inspectionDate=$('#inspectionDate').val(),    //报验日期
    		targetCheck=$('#targetCheck').val(),		  //靶板检查
    		checkData=$('#checkDate').val();			  //监理检查日期
    		otherCheckDate=$('#otherCheckDate').val();	  //其他人检查日期
    	}
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	targetCheck=encodeURIComponent(cjkEncode(targetCheck)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName)); 
    	var src = cptPath+"/ReportServer?reportlet=standPipe/standPipe1.cpt&projName="+projName+"&pcId="+pcId+"&suName="+suName+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate+"&targetCheck="+targetCheck+"&otherCheckDate="+otherCheckDate+"&checkDate="+checkDate;
    	src = src+"&path=${path}"+"&pcId="+$('#pcIdNew').val();
    	$("#mainFrm").attr("src",src); 
    }
    
    
    var showReport3 = function(){
    	var projName='',constructionUnit='',suName='';
    	
   		projName =$('#projName').val(),			      //工程名称
   		suName=$('#suName').val(),				      //监理单位
   		constructionUnit=$('#constructionUnit').val();//施工单位
   		
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName)); 
    	var src = cptPath+"/ReportServer?reportlet=standPipe/standPipe.cpt&projName="+projName+"&suName="+suName+"&constructionUnit="+constructionUnit;
    	src = src+"&path=${path}"+"&pcId="+$('#pcIdNew').val();
    	$("#mainFrm").attr("src",src); 
    }
    
    //签字加载方式
    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5").handleSignature();
    
  	//打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 
    
    $.getScript('projectjs/inspection/stand-pipe.js').done(function () {
        purge.init();
	});
    
  	//报验区点击放弃
	$('.giveupBtn').off().on('click',function(){
		if($("#pcId").val()==""){
			//返回列表区
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#listTab').tab("show");
			$('#purgeTable').cgetData(true);
		}else{
			//返回列表区
			//$('ul.nav-tabs>li.active').removeClass("active");
			$('#listTab').tab("show");
		}
		
    })
    //报验区保存工程报验单
    $('.saveBtn').off().on("click",function(){
    	$('#purgeForm').cformSave('purgeTable',savePurgeBack,false);
    })
    var savePurgeBack=function(data){
		$('.editBtn').addClass("hidden");
    	$('#purgeForm').toggleEditState(false);
    	if(data!==false){
    		$('#pcIdNew').val(data);
    	}
    	//刷新帆软报表
    	showReport1();
  	}
	var rows=[];
	var rowData=[];
	//记录区增加吹扫记录
    $(".addbtn").off().on("click",function(){
    	var rowsPart=[];
    	var t=$("#purgeRecordForm");
		if($('#pcIdNew').val()!==''){
	    	//开启表单验证
	        if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') { 
	        	//报验单id
				var id=$('#pcIdNew').val();
		   		var json=t.serializeJson();
		   		json.pcId=id;
		   		rowsPart.push(json);
		   		
		   		var json1=$("#purgeAuditTable").DataTable().rows().data();
		   		console.info('json.purgeNum..'+json.purgeNum);
		   		console.info('rowsPart...'+rowsPart.length);
			   	 for(var i=0;i<json1.length;i++){
			   		for(var j=0;j<rowsPart.length;j++){
			   			if(json1[i].purgeNum==rowsPart[j].purgeNum){
			   				
							$("body").cgetPopup({
			                  	title: "提示信息",
			                  	content: "吹扫次数重复!",
			                  	accept: successBack1,
			                  	chide: true,
			                  	icon: "fa-check-circle",
			                  	newpop: 'new'
			                  }); 
			    			  return false;
						}
			   		}
			   	 }
		   		//吹扫记录表格添加一条记录
	    	    mytable.rows.add(rowsPart).draw();
	    	    $('#purgeAuditTable').selectRow(0);
		    		/* console.info('rowsPart...'+rowsPart);
		    	rows=$.merge(rows, rowsPart);
		    		console.info('rows...'+rows);
		    	rowData=rows;
		    		console.info('rowData...'+rowData); */
	            //如果通过验证, 则移除验证UI
		        t.parsley().reset();
		    } else {
		        //如果未通过验证, 则加载验证UI
		        t.parsley().validate();
		    };
		}else{
			$("body").cgetPopup({
		       	title: "提示信息",
		       	content: "报验单不存在，不允许添加记录!",
		       	accept: successBack2,
		       	chide: true,
		       	icon: "fa-check-circle",
		       	newpop: 'new'
		       });
			 return false;
		} 
    });
	var successBack1=function(){
	};
	var successBack2=function(){};
	//记录区批量保存
    $(".savebtn").on("click",function(){
        var dataObj={};
    	var json=$("#purgeAuditTable").DataTable().rows().data();
    	console.info('json.length...'+json.length);
    	dataObj.list = [];
		$.each(json, function(k,v){
			dataObj.list.push(v);
		})
		var id=$("#pcIdNew").val();
		dataObj.pcId=id;
		var data=JSON.stringify(dataObj);
		dataObj.list = [];//清空了;
		/* console.info('1...'+JSON.stringify(dataObj.list));
    	var data = dataObj.list;
    	console.info("data.."+data);
    	var result=[];
    	for(var n=0;n<data.length;n++){
    		if(!data[n].purgeId){
    			result.push(data[n]);
    		}
    	}
    	result=JSON.stringify(result);
		//console.log("result..."+result); */
		
		/* 
		var data=JSON.stringify(rowData);
		 rowData=[];
		 rows=[];
		 rowsPart=[]; */
    	
		if(response){
	    	response.abort();
  	    	}
  		  	var response = $.ajax({
			url: "purge/savePurgeRecord",
  	          	type: "POST",
  	          	timeout : 5000,
  	          	contentType: "application/json;charset=UTF-8",
  	          	data: data,
  	          	success: function (data) {
				if (data === "true") {   	        		  
					$("body").cgetPopup({
	                  	title: "提示信息",
	                  	content: "数据保存成功!",
	                  	accept: successBack,
	                  	chide: true,
	                  	icon: "fa-check-circle",
	                  	newpop: 'new'
                  	}); 
				}else{
  	        			$("body").cgetPopup({
   	                  	title: "提示信息",
   	                  	content: "数据保存失败, 请重试! <br>" + data,
   	                  	accept: popClose,
   	                  	chide: true,
   	                  	icon: "fa-exclamation-circle",
   	                  	newpop: 'new'
					});  
				}
			}
		});
	});
   	function successBack(){
   		showReport2();
   	} 
    
	//记录区点击删除
	$('.giveupbtn').off().on('click',function(){
		var len=$('#purgeAuditTable').find('tr.selected').length;
		 if(len>0){
			 var rows = $("#purgeAuditTable").DataTable().rows( '.selected' ).remove().draw();
			 $('#purgeAuditTable').selectRow(0);
		 }else{
			 $("body").cgetPopup({
			 	title: '提示信息',
				content: '请选择要删除的记录!',
				accept: delDataPurge,
				icon: 'fa-exclamation-circle',
			 });
		 }
    })
    function delDataPurge(){};
</script>
<!-- ================== END PAGE LEVEL JS ================== -->