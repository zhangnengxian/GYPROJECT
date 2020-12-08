<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表区</a></li>
		                <li class=""><a href="#default-tab-2" id="signTab"  data-toggle="tab">报验区</a></li>
		                <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" id="">
               		<div class="tab-content">
               		    <input type="hidden" id="pcIdNew">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="derustingTable" width="100%">
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
	                       	<div class="toolBtn f-r p-b-10 editBtn">
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 giveupBtn">放弃</a>
						    </div>
						    <div class="clearboth form-box">
						    	<input type="hidden" class="imgUrl" value="${imgUrl}"/>
								<form class="form-horizontal" id="derustingForm" data-parsley-validate="true" action="derusting/saveDerusting">
									<input type="hidden" id="pcId" name="pcId" class="addClear">
								 	<input type="hidden" id="pcDesId" name="pcDesId" value="${pcDesId}">
								 	<input type="hidden" id="projId" name="projId" >
								 	<input type="hidden" id="projNo" name="projNo" >
									<div class="form-group  col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projName" name="projName"  value="乌鲁木齐恒大绿洲小区"/>
										</div>
									</div>
									<div class="form-group  col-md-6 col-sm-12">
										<label class="control-label" for="process">施工工序</label>
										<div> 
											<input type="text" class="form-control input-sm field-editable addClear"  id="process" name="process" data-parsley-required="true"  value="户内燃气表安装"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="slResultPage">附件数量</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="slResultPage" name="slResultPage"  value="1"/>
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label " for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm" id="constructionUnit" name="constructionUnit" value="施工管理一处" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionPrincipal">项目经理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionPrincipal" name="constructionPrincipal" value="">
											<input type="hidden" id="constructionPrincipal_postType" name="constructionPrincipal_postType" value="${constPCpost}" >
											<img class="constructionPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectionDate">检验日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="inspectionDate" name="inspectionDate"  value="2016-11-19"/>
										</div>
									</div>
							        <div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="inspectionResult" id="inspectionResult" rows="3" cols="" data-parsley-maxlength="200" >合格</textarea>
					                    </div>
					  			    </div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionQc">质检员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQc" name="constructionQc" value="">
											<input type="hidden" id="constructionQc_postType" name="constructionQc_postType" value="${constructionQcPost}" >
											<img class="constructionQc" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="selfCheckDate">自检日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="selfCheckDate" name="selfCheckDate"  value="2016-11-18"/>
										</div>
									</div>
									<div class="form-group col-sm-12">
				                    	<label class="control-label" for="suOpinion">监理意见</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="suOpinion" id="suOpinion" rows="3" cols="" data-parsley-maxlength="200" >同意报验</textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group col-sm-12">
										<label class="control-label" for="suName">监理公司</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"   id="suName" name="suName"  />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="suJgj">现场监理人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${suJgjPost }" >
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checkDate">日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="checkDate" name="checkDate"  value="2016-11-20"/>
										</div>
									</div>
									<div class="form-group col-sm-12">
				                    	<label class="control-label" for="">甲方意见</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="" id="" rows="3" cols="" data-parsley-maxlength="200" >同意</textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group col-sm-12">
										<label class="control-label" for="">建设单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"   id="" name=""  value="新疆景胜房地产开发有限公司"/>
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="">甲方代表</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="" name="suJgj" value="">
											<input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${suJgjPost }" >
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="">日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="" name=""  value="2016-11-23"/>
										</div>
									</div>
								</form>
							</div>
	                   	</div>
	                   	<div class="tab-pane fade  btn-top" id="default-tab-3">
	                   		<div class="toolBtn f-r p-b-10">
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 derustingsavebtn">保存</a>
						    </div>
						    <div class="clearboth form-box">
		                   		<form class="form-horizontal" id="derustingRecordForm" data-parsley-validate="true" action="">
							        <div class="form-group col-md-12">
								     	<label class="control-label " for="surveyDes">保证项目</label>
								     	<div> 
								        	<textarea class="form-control field-editable" name="surveyDes" id="surveyDes" rows="4" cols="" data-parsley-maxlength="200" data-parsley-required="true">1、燃气表安装在非封闭空间2、燃气表保持直立安装，严禁倾斜3、燃气表与灶具保持安全距离4、燃气表前安装闭气阀门</textarea></div>
								    </div>
								    <div class="form-group col-md-12">
								     	<label class="control-label " for="surveyDes">允许偏差</label>
								     	<div> 
								        	<textarea class="form-control field-editable" name="surveyDes" id="surveyDes" rows="4" cols="" data-parsley-maxlength="200" data-parsley-required="true">1、高位挂表距成品地面高度1800mm±(50mm).2、燃气表距墙面与燃气灶水平距离≦30mm。3、地位挂表距成品地面不低于100mm</textarea></div>
								    </div>
								    
		                       	</form>
		                    </div>
		                     <!-- <table id="derustingRecordTable" class="table table-striped table-bordered nowrap" width="100%">
	                            <thead>
	                                <tr>
	                                    <th>保证项目</th>
	                                </tr>
	                            </thead>
	                       	</table>
	                       	<div class='divider m-b-20'></div>
	                       	<div class="toolBtn f-r p-b-10">
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 addSpecbtn">增加</a>
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveSpecbtn">保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 giveupSpecbtn">删除</a>
						    </div>
						    <div class="clearboth form-box">
		                   		<form class="form-horizontal" id="antSpecForm" data-parsley-validate="true" action="">
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="dpPipeSize">允许偏差</label>												          				            
										<div>
											<input type="text" class="form-control field-not-editable input-sm" data-parsley-required="true" id="dpPipeSize" name="dpPipeSize"  value=""/>
										</div> 					           
							        </div>
		                       	</form>
		                    </div>
		                     <table id="antSpecTable" class="table table-striped table-bordered nowrap" width="100%">
	                            <thead>
	                                <tr>
	                                    <th>允许偏差</th>
	                                </tr>
	                            </thead>
	                       	</table> -->
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
			    <div class="panel-body" id="derusting_audit_panel_box">
			    	 
	                <div class="clearboth form-box">
	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 795px; height: 1123px;border:1; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('表具安装 - 工程项目管理系统 ');
    $("#derustingForm").toggleEditState().styleFit();
    $("#derustingRecordForm").styleFit();
    $("#antSpecForm").styleFit();
    $('.editBtn').addClass("hidden");
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	/* $('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.managementOffice); //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司 */
    }
    
    
    $('.datepicker-default').datepicker({
            todayHighlight: true
    });
    
    var cptPath = '<%=basePath%>';
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var showReport1 = function(){
    	var projName='',suName='',process='',slResultPage='',constructionUnit='',inspectionDate='',selfCheckDate='',inspectionResult='',suOpinion='',checkDate='';
    	var json=trSData.derustingTable.json;
    	var pcId;
    	if(json){
	   	    projName = json.projName,			   //工程名称
	   		suName=json.suName,					   //监理单位
	   		process=json.process,			       //除锈工序
	   		slResultPage=json.slResultPage,    	   //页数
	   		constructionUnit=json.constructionUnit,//施工单位
	   		inspectionDate=json.inspectionDate,	   //报验日期
	   		inspectionResult=json.inspectionResult,//查验结果
	   		selfCheckDate=json.selfCheckDate,	   //自检日期
	   		suOpinion=json.suOpinion,			   //监理单位意见
	   		checkDate=json.checkDate;			   //检查日期
	   		pcId = json.pcId;
    	}else{
   			projName =$('#projName').val(),			    //工程名称
       		suName=$('#suName').val(),				    //监理单位
       		process=$('#process').val(),			    //施工工序
       		slResultPage=$('#slResultPage').val(),      //页数
       		constructionUnit=$('#constructionUnit').val(),//施工单位
       		inspectionDate=$('#inspectionDate').val(),    //报验日期
       		inspectionResult=$('#inspectionResult').val(),//查验结果
       		selfCheckDate=$('#selfCheckDate').val(),	  //自检日期
       		suOpinion=$('#suOpinion').val(),			  //监理单位意见
       		checkDate=$('#checkDate').val();			  //检查日期
       		pcId = $('#pcId').val();
   		}
   		//解决乱码
       	projName = encodeURIComponent(cjkEncode(projName)),				//工程名称
       	suName=encodeURIComponent(cjkEncode(suName)),					//监理单位
       	process=encodeURIComponent(cjkEncode(process)),			    	//检验部位
       	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)),//施工单位
       	slResultPage=encodeURIComponent(cjkEncode(slResultPage)),       //页数
       	inspectionResult=encodeURIComponent(cjkEncode(inspectionResult)),//查验结果
       	suOpinion=encodeURIComponent(cjkEncode(suOpinion));		     //监理单位意见
    	
    	src = cptPath+"/ReportServer?reportlet=meterInstall/meterInstall.cpt&projName=" + projName+"&suName="+suName+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate;
    	src = src+"&inspectionDate="+inspectionDate+"&checkDate="+checkDate+"&suOpinion="+suOpinion+"&process="+process+"&slResultPage="+slResultPage+"&inspectionResult="+inspectionResult;
    	src = src+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src);
    },
    
    showReport2 = function(){
    	var pcId='',projName='',constructionUnit='',inspectionDate='',suName='',dpPipeSize='',dpNum='',dpReplayNum='',dpKv='';    	
    	var json=trSData.derustingTable.json;
    	if(json){
    		pcId=json.pcId;
    		projName = json.projName,			   //工程名称
    		suName=json.suName,					   //监理单位
    		constructionUnit=json.constructionUnit,//施工单位
    		inspectionDate=json.inspectionDate,	   //报验日期
    		dpPipeSize=json.dpPipeSize,			   //管道规格
    		dpNum=json.dpNum,					   //防腐数量
    		dpReplayNum=json.dpReplayNum,		   //补扣防腐数量
    		dpKv=json.dpKv;                        //检测结果
    	}else{
    		pcId=$('#pcId').val();
    		projName=$('#projName').val();
    		suName=$('#suName').val();
    		constructionUnit=$('#constructionUnit').val();
    		inspectionDate=$('#inspectionDate').val();
    		dpPipeSize=$('#dpPipeSize').val();
    		dpNum=$('#dpNum').val();
    		dpReplayNum=$('#dpReplayNum').val();
    		dpKv=$('#dpKv').val();
    	}
    	pcId = encodeURIComponent(cjkEncode(pcId));	
    	projName = encodeURIComponent(cjkEncode(projName));				//工程名称
    	suName=encodeURIComponent(cjkEncode(suName));					//监理单位
    	dpPipeSize=encodeURIComponent(cjkEncode(dpPipeSize));			
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit));//施工单位
    	dpPipeSize=encodeURIComponent(cjkEncode(dpPipeSize));
    	dpNum=encodeURIComponent(cjkEncode(dpNum));
    	dpReplayNum=encodeURIComponent(cjkEncode(dpReplayNum));
    	dpKv=encodeURIComponent(cjkEncode(dpKv));
    	
    	src = cptPath+"/ReportServer?reportlet=meterInstall/meterInstall1.cpt&projName=" + projName+"&pcId="+pcId+"&suName="+suName+"&constructionUnit="+constructionUnit+"&inspectionDate="+inspectionDate+"&dpPipeSize="+dpPipeSize+"&dpNum="+dpNum+"&dpReplayNum="+dpReplayNum+"&dpKv="+dpKv;
    	src = src+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src);
    }
	
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 
    
    var showReport3 = function(){
    	var projName='',constructionUnit='',suName='';
    	
   		projName =$('#projName').val(),			      //工程名称
   		suName=$('#suName').val(),				      //监理单位
   		constructionUnit=$('#constructionUnit').val();//施工单位
   		var pcId = $('#pcId').val();
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName)); 
    	var src = cptPath+"/ReportServer?reportlet=meterInstall/meterInstall.cpt&projName="+projName+"&suName="+suName+"&constructionUnit="+constructionUnit;
    	src = src+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src); 
    }
  	//签字加载方式
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6");
    	signatures.handleSignature();        	    	
    });

    
   //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 
    
    $.getScript('projectjs/inspection/meter-install.js').done(function () {
        meterInstall.init();
	});
	
  	//报验区点击放弃
	$('.giveupBtn').off().on('click',function(){
		//返回列表区
		//$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
    })
    
    //报验区保存工程报验单
    $('.saveBtn').off().on("click",function(){
    	$('#derustingForm').cformSave('derustingTable',saveDerustingBack,false);
    })
    var saveDerustingBack=function(data){
		$('.editBtn').addClass("hidden");
		$('#derustingForm').toggleEditState(false);
		console.info("data======"+data);
		if(data!==false){
			$("#pcIdNew").val(data);
			$("#pcId").val(data);
		}
		//刷新帆软报表
    	showReport1();
  	}
	var json=null;
	//记录区增加试压记录
    $(".addbtn").off().on("click",function(){
    	var rowsPart=[];
		var t=$("#derustingRecordForm");
		//判断报验单是否存在
		if($("#pcIdNew").val()!==""){
	    	//开启表单验证
			if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') { 
	        	//报验单id
				var id=$("#pcIdNew").val();
		   		var json=t.serializeJson();
		   		json.pcId=id;
		   		json.dpCheckItemDes=$('#dpCheckItem option:selected').text();
		   		json.dpCheckItem=$('#dpCheckItem option:selected').val();
		   		json.dpMethodDes=$('#dpMethod option:selected').text();
		   		json.dpMethod=$('#dpMethod option:selected').val();
		   		json.dpResultDes=$('#dpResult option:selected').text();
		   		json.dpResult=$('#dpResult option:selected').val();
		   		
		   		//判断重复
		   		var json1=$("#derustingRecordTable").DataTable().rows().data();
				for(var i=0;i<json1.length;i++){
					console.info('json1[i].dpCheckItemDes...'+json1[i].dpCheckItemDes);
					if(json1[i].dpCheckItemDes==json.dpCheckItemDes){
						$("body").cgetPopup({
			                  title: "提示信息",
			                  content: "检验项目重复!",
			                  accept: successBack1,
			                  chide: true,
			                  icon: "fa-check-circle",
			                  newpop: 'new'
			             }); 
			    		 return false;
					 }
			    }
		   		//吹扫记录表格添加一条记录
	    	    derustingRecordTable.row.add(json).draw();
	    	    $('#derustingRecordTable').selectRow(0);
	    	   /*  json=null;
		   		//console.log('json....'+json);//清空了 */
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
    var successBack1=function(){}
	var successBack2=function(){}
    
	//记录区保存
    $('.derustingsavebtn').off().on('click',function(){
		var dataObj={};
		var json=$("#derustingRecordTable").DataTable().rows().data();
		dataObj.list = [];
		//转成list
		$.each(json, function(k,v){
			dataObj.list.push(v);
		})
		var id=$('#pcIdNew').val();
		dataObj.pcId=id;
		var data=JSON.stringify(dataObj);
		dataObj.list = [];//清空了；
		if(response){
  	    	response.abort();
  	    } 
  		var response = $.ajax({
			url: "derusting/saveDerustingRecord",
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
	})
    function successBack(){
    	showReport2();
    } ;
	
	//记录区点击删除
	$('.deletebtn').off().on('click',function(){
		var len=$('#derustingRecordTable').find('tr.selected').length;
		 if(len>0){
			 var rows = $("#derustingRecordTable").DataTable().rows( '.selected' ).remove().draw();
			 $('#derustingRecordTable').selectRow(0);
		 }else{
			 $("body").cgetPopup({
					title: '提示信息',
					content: '请选择要删除的记录!',
					accept: delData,
					icon: 'fa-exclamation-circle',
			  });
		 }
    })
    var delData=function(){};
    
	//记录区增加管道规格 防腐数量
    $(".addSpecbtn").off().on("click",function(){
    	var rowsPart=[];
		var t=$("#antSpecForm");
		//判断报验单是否存在
		if($("#pcIdNew").val()!==""){
	    	//开启表单验证
	        if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') { 
	        	//报验单id
				var id=$("#pcIdNew").val();
		   		var json=t.serializeJson();
		   		json.pcId=id; 		   		
		   		//判断重复		   		
		   		var json1=$("#antSpecTable").DataTable().rows().data();
				 for(var i=0;i<json1.length;i++){
					 if(json1[i].dpPipeSize==json.dpPipeSize){
						 $("body").cgetPopup({
			                  	title: "提示信息",
			                  	content: "管道规格重复!",
			                  	accept: successBack1,
			                  	chide: true,
			                  	icon: "fa-check-circle",
			                  	newpop: 'new'
			                  }); 
			    		 return false;
					 }
			     }				 		   		
		   		//除锈记录区表格添加一条记录
	    	    antSpecTable.row.add(json).draw();
	    	    $('#antSpecTable').selectRow(0);
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

    
    $('.saveSpecbtn').off().on('click',function(){
		var dataObj={};
		//列表的值
    	var json=$("#antSpecTable").DataTable().rows().data();
    	dataObj.list = [];
		$.each(json, function(k,v){
			dataObj.list.push(v);
		})
		var id=$("#pcIdNew").val();
		dataObj.pcId=id;
		var data=JSON.stringify(dataObj);
	    dataObj.list = [];//清空了；
		 if(response){
  	          response.abort();
  	      	} 
  		  	 var response = $.ajax({
			url: "derusting/saveAntSpecList",
  	          	type: "POST",
  	          	timeout : 5000,
  	          	contentType: "application/json;charset=UTF-8",
  	          	data: data,
  	          	success: function (data) {
				if (data === "true") {   	        		  
					$("body").cgetPopup({
	                  	title: "提示信息",
	                  	content: "数据保存成功!",
	                  	accept: successBack3,
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
	})
    function successBack3(){
    	//$('#antSpecTable').cgetData(false);
    	showReport2();
    };
	
	//记录区除锈防腐管道规格列表删除
	$('.giveupSpecbtn').off().on('click',function(){
		 var len=$('#antSpecTable').find('tr.selected').length;
		 if(len>0){
			 var rows = $("#antSpecTable").DataTable().rows( '.selected' ).remove().draw();
			 $('#antSpecTable').selectRow(0);
		 }else{
			 $("body").cgetPopup({
			 	title: '提示信息',
				content: '请选择要删除的记录!',
				accept: delDataAnt,
				icon: 'fa-exclamation-circle',
			 });
		 }
    })
	function delDataAnt(){};
	
</script>

<!-- ================== END PAGE LEVEL JS ================== -->