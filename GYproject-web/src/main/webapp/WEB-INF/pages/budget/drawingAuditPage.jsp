<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
       	<div class="panel panel-inverse tabs-mixin" id="">
			<div class="panel-heading">
               	<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                </div>
                <h4 class="panel-title">图纸目录</h4>
			</div>
            <div class="panel-body">
	        	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
	              	<input type="hidden" id="projId" name = "projId" value = "${designInfo.projId}"/>
	              	<table id="drawingTable" class="table table-striped table-bordered nowrap" width="100%">
						<thead>
							<tr>
								<th>图纸名称</th>
                                <th>图号</th>
                          	    <th>图幅</th>
                                <th>数量</th>
                                <th>折合张数</th>
	                        </tr>
	                    </thead>
	                </table>
	            </div>
	        </div>
		</div>
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" id="">
			<div class="panel-heading">
				<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	            </div>
				<h4 class="panel-title">确认区</h4>
			</div>
			<div class="panel-body" id="drawing_audit_panel_box">
					<div id="drawingAuditTopBox">
						<div class="toolBtn f-r m-b-15  editbtn">
						<!-- <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 hidden dispatchBtnChange ">派工</a> -->
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
         				
					</div>
			    	<div class="clearboth form-box drawingAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<input type="hidden"  id="budgeterId" name="budgeterId" value="${project.budgeterId}">
			    		<form class="form-horizontal" id="drawingAuditForm" action="drawingSignAndAudit/auditSave">
			    			<input type="hidden" id="projId" name = "projId" value = "${designInfo.projId}"/>
			    			<input type="hidden"  name = "projNo" value = "${designInfo.projNo}"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" value = "${designInfo.diId}"/>
                    		<input type="hidden" name = "mrAuditLevel" value = "${currentLevel}"/>
			    			<div class="form-group col-md-12">
			        			<label class="control-label" for="">确认结果</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="1" />通过
				            	</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="0" />不通过
				            	</label>
		    				</div>
		    				<div class="form-group col-md-12">
						     	<label class="control-label" for="">确认意见</label>
						     	<div> 
		        					<textarea class="form-control "  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					</div>
		   					<div class="form-group col-md-6">
						        <label class="control-label" for="">确认人</label>
						        <div>
						           <input type="hidden"  id="mrCsr"  name="mrCsr" data-parsley-maxlength="100" value="${loginInfo.staffId}">
						           <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
						        </div>
						    </div>
						    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
						        <label class="control-label" for="">确认日期</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" data-parsley-maxlength="100" >
						        </div>
						    </div>
			    		</form>
			    	</div>
			    	<div >
		    		<h4 class="m-t-15 m-l-7"><strong>确认历史</strong></h4>
		    		</div>
		    		<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
       					<thead>
			            	<tr>
			                <th>确认日期</th>
			                <th>确认结果</th>
			                <th>确认意见</th>
			                <th>确认人</th>
	            			</tr>
          				</thead>
					</table>
					</div>
					<div id="drawingAuditBottomBox" class="hidden">
						<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 dispatchBtn" >派工</a>
    	 				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
					</div>
			    <div class="clearboth form-box">
			    	<form class="form-horizontal" id="budgetDispatchForm" data-parsley-validate="true" action="">
						<div class="form-group col-md-6">
			            	<label class="control-label" for="projNo">工程编号</label>
			            	<div >
			                	<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo" value="${project.projNo}"/>
			            	</div>
			        	</div>
			        	<div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="paNo">受理单号</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="paNo" name="paNo" value="${project.projNo}"/>
					        </div>
					    </div>
						<div class="form-group col-md-12">
			            	<label class="control-label" for="projName">工程名称</label>
			            	<div >
			                	<input type="text" class="form-control input-sm field-not-editable" id="projName" name="projName" value="${project.projName}"/>
			            	</div>
			        	</div>
			        	<div class="form-group col-md-6 col-sm-12">
			            	<label class="control-label" for="projAddr">工程地点</label>
			            	<div >
			                	<input type="text" class="form-control input-sm field-not-editable" id="projAddr" name="projAddr" value="${project.projAddr}"/>
			            	</div>
			        	</div>
			        	<div class="form-group col-md-6 col-sm-12">
			        		<label class="control-label" for="areaDes">区域</label>
			            	<div >
					             <input type="text" class="form-control input-sm field-not-editable" id="areaDes" name="areaDes" value="${areaDes}"/>
			            	</div>
			        	</div>
			        	<div class="form-group col-md-12">
			            	<label class="control-label" for="projScaleDes">工程规模</label>
			            	<div>
					            <textarea class="form-control field-not-editable"  id="projScaleDes" name="projScaleDes" rows="2" data-parsley-maxlength="200" >${project.projScaleDes}</textarea>
					        </div>
			        	</div>
			        	<div class="form-group col-md-12">
			            	<label class="control-label" for="custName">申报单位</label>
			            	<div >
			                	<input type="text" class="form-control input-sm field-not-editable" id="custName" name="custName" value="${project.custName}"/>
			            	</div>
			        	</div>
			        	<div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">联系人</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" value="${project.custContact}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">联系电话</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" value="${project.custPhone}"/>
					        </div>
					    </div>
			        	<input type="hidden" name="budgeter" id="budgeter"/>
					</form>
			    </div>
			    <div>
			    <h4 class="m-t-20"><strong>预算员</strong></h4>
			    </div>
			    <table id="budgeterTable" class="table table-hover table-bordered nowrap" width="100%">
			        <thead>
			            <tr>
			            	<th>名称</th>
			                <th>待审核任务</th>
			            </tr>
			        </thead>
				</table>
					</div>
					
					
			    </div>
	     	</div>
	     	<!-- <div class="panel-body hidden" id="budget_dispatch_panel_box">
	     		
	     	</div> -->
		</div>	    	
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('图纸确认 - 工程管理系统');
    
    $("#drawingAuditForm").toggleEditState();
    $("#drawingAuditForm").styleFit();
   
    $("#budgetDispatchForm").toggleEditState();
    $("#budgetDispatchForm").styleFit();
    
    
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/budget/drawing-audit-page.js?v=1000').done(function () {
        drawingAudit.init();
	});

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("drawingSignAndAudit/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
    	var val=$('#drawingAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
    		$("#drawingAuditForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".saveBtn"));
    	}
    });
    
    
    var ensureDone=function(){};
    
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.drawingAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#drawingAuditForm input:radio[name="mrResult"]:checked').val();
    		/* if(val=="0"){
    			$(".dispatchBtnChange").addClass("hidden");
    		}else{
    			$(".dispatchBtnChange").removeClass("hidden");
    		} */
    		$("#drawingAuditForm").toggleEditState(false); 
    	}
    	console.info("de..."+$("#budgeterId").val());
    	if($("#budgeterId").val()!=""){
    		//已派遣预算员
    		$(".dispatchBtnChange").addClass("hidden");
    	}
    }
    
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//确认过
    		auditSaveCallBack("true");
    	}
    }();
    
    //派工切换
    $(".dispatchBtnChange").off().on("click",function(){
    	$("#drawingAuditTopBox").addClass("hidden");
    	$("#drawingAuditBottomBox").removeClass("hidden");
    	//加载预算员表
    	budgetertableinit();
    })
    
    //点击派工
    $(".dispatchBtn").off().on("click",function(){
    	var len=$("#budgeterTable").find("tr.selected").length;
    	if(len>0){
    		var budgeter = $("#budgeterTable").find("tr.selected td:eq(0)").text();
    		$("#budgeter").val(budgeter);//选择的预算员
    		 $("body").cgetPopup({
          	    title: "提示信息",
             	content: '确认要派工给 <i class="fa fa-user"></i> '+budgeter+" 吗？",
             	accept: function(){
          		var data=$("#budgetDispatchForm").serializeJson();
          		data.projId=$("#projId").val();
    	        	$.ajax({
    	                type: 'POST',
    	                url: 'drawingSignAndAudit/updateProject',
    	                contentType: "application/json;charset=UTF-8",
    	                data: JSON.stringify(data),
    	                success: function (data) {
    	                	var content = "派工成功！";
    	                	if(data === "false"){
    	                		content = "派工失败！";
    	                	}else if(data === "true"){
    	                	    $('#budgeterTable').cgetData(true);
    	                	    $(".dispatchBtn").addClass("hidden");
    	                	}
    	                	var myoptions = {
    	                        	title: "提示信息",
    	                        	content: content,
    	                        	accept: popClose,
    	                        	chide: true,
    	                        	newpop: 'new',
    	                        	icon: "fa-check-circle"
    	                    }
    	                    $("body").cgetPopup(myoptions);
    	                },
    	                error: function (jqXHR, textStatus, errorThrown) {
    	                    console.warn("预算派遣区派工失败！");
    	                }
    	            }); 
	          	},
	          	icon: "fa-check-circle",
	          	newpop: 'new'
      		});
    	}else{
    		$("body").cgetPopup({
					title: '提示',
					content: '请选择预算员！',
					accept: popClose
		    });
    	}
    	
    })
        
</script>
<!-- ================== END PAGE LEVEL JS ================== -->