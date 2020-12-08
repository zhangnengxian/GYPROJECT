<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
       	<div class="panel panel-inverse tabs-mixin">
			<div class="panel-heading">
               	<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                </div>
                <h4 class="panel-title">付款审核信息</h4>
			</div>
            <div class="panel-body">
            	<input type="text" class="hidden"  id="corpName"  value="${pa.corpName}"/>
            	<input type="text" class="hidden"  id="deptName"  value="${pa.deptName}"/>
            	<input type="text" class="consFeeType hidden"  value="${consFeeType}"/>
              	<form class="form-horizontal" id="paymentApplyAuditForm" action="">
		       		<input type="hidden" id="projId1" name="projId"/>
		       		<input type="hidden" id="paId" name="paId"/>
				    <div class="form-group  col-md-6  cons">
				        <label class="control-label" for="projNo">工程编号</label>
				        <div>
				            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" value=""/>
				        </div>
				    </div>
				     <div class="form-group col-md-12 cons">
				        <label class="control-label" for="projName">工程名称</label>
				        <div>
				            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value=""/>
				        </div>
				    </div>
				    <div class="form-group col-md-12 cons">
				        <label class="control-label" for="projAddr">工程地点</label>
				        <div>
				            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
				        </div>
				    </div>
				    <div class="form-group col-md-12 cons">
				        <label class="control-label" >工程规模</label>
				        <div>
				        	<textarea class="form-control input-sm field-not-editable" name="projScaleDes" rows="4" ></textarea>
				        </div>
				    </div>
					<div class="form-group col-md-6 col-sm-12 cons">
				    	<label class="control-label" for="corpName">燃气公司</label>
				        <div>
				        	<input type="text" class="form-control input-sm field-not-editable corpName"  name="corpName"  />
				        </div>
				    </div>
				    <div class="form-group col-md-6 col-sm-12 cons">
				        <label class="control-label" for="">工程类型</label>
				    	<div>
				    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
				        </div>
				    </div>
				    <div class="form-group col-md-6 col-sm-12 cons">
				        <label class="control-label" for="">出资方式</label>
				    	<div>
				    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
				        </div>
				    </div>
				    <div class="form-group col-md-6 col-sm-12 cons">
				        <label class="control-label" for="">业务部门</label>
				    	<div>
				    		 <input type="text" class="form-control input-sm field-not-editable deptName"   name="deptName" />        
				        </div>
				    </div>
				     <div class="form-group col-md-12 ">
				        <label class="control-label" for="applyDeptName">请款单位</label>
				        <div>
				            <input type="text" class="form-control input-sm field-editable"  id="applyDeptName" name="applyDeptName"  value=""/>
				        </div>
				    </div>
				    <div class="form-group col-md-6 ">
				    	<label class="control-label" for="applyNo">申请编号</label>
				        <div>
				        	<input type="text" class="form-control input-sm field-editable"  id="applyNo" name="applyNo"   data-parsley-maxlength="50" />
				        </div>
				    </div>
				    <div class="form-group col-md-6 noUser">
		            	<label class="control-label" for="feeType">费用类型</label>
		            	<div>
				    		<select class="form-control input-sm field-not-editable" id="feeType"  name="feeType"  >
				                <option value="1" >工程费</option>
				        		<option value="2" >设计费</option>
				        		<option value="3" >监理费</option>
				        		<option value="4" >探伤费</option>
				             </select>
				        </div>
		        	</div>
				    <div class="form-group col-md-6">
				        <label class="control-label">申请日期</label>
				        <div>
				           <input type="text" class=" form-control input-sm field-editable datepicker-default " id="applyDate"  name="applyDate"  value="" >
				        </div>
				    </div>
				     <div class="form-group col-md-6">
				        <label class="control-label" for="applyer">申请人</label>
				        <div>
				            <input type="text" class="form-control input-sm field-editable"  id="applyer" name="applyer" data-parsley-maxlength="200" value=""/>
				        </div>
				    </div>
				    <div class="form-group col-md-6 construction cons">
			            <label class="control-label" for="applyReason">申请事由</label>
			            <div>
			                <select class="form-control input-sm field-editable" id="applyReason"  name="applyReason"  >
				        		<option value="15" >预付款</option>
				        		<option value="19" >进度款</option>
				        		<option value="17" >结算款</option>
				        	</select>
			            </div>
		            </div>
				    <div class="form-group col-md-6 construction cons">
				        <label class="control-label" for="sdType">结算方式</label>
				        <div>
				        	<select class="form-control input-sm field-editable" id="sdType"  name="sdType"  >
				        		<option value="1" >预结算制</option>
				        		<option value="2" >合同制</option>
				        		<option value="3" >其他</option>
				        	</select>
				        </div>
				    </div>
				    <div class="form-group col-md-12 cons " >
				        <label class="control-label" for="contractDes">用户合同金额</label>
				        <div>
				            <textarea class="form-control field-not-editable" name="contractDes" id="contractDes" rows="3" cols="" ></textarea>
				        </div>
				    </div>
				    <div class="form-group col-md-12 cons">
				        <label class="control-label" for="receiveMoneyDes">收款描述</label>
				        <div>
				           <textarea class="form-control field-not-editable" name="receiveMoneyDes" id="receiveMoneyDes" rows="3" cols="" ></textarea>
				       </div>
				    </div>
				    <div class="form-group col-md-12 cons">
				        <label class="control-label" for="payMoneyDes">已登记付款描述</label>
				        <div>
				           <textarea class="form-control field-not-editable" name="payMoneyDes" id="payMoneyDes" rows="3" cols="" ></textarea>
				       </div>
				    </div>
				    
				    <div class="form-group col-md-6 cons">
				        <label class="control-label" for="payScAmount">已付工程款</label>
				        <div>
				            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="payScAmount" name="payScAmount" data-parsley-maxlength="17" />
				        </div>
				    </div>
				    <div class="form-group col-md-6 cons ">
				        <label class="control-label" for="scAmount">分包合同金额</label>
				        <div>
				            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="scAmount" name="scAmount" data-parsley-maxlength="17" />
				        </div>
				    </div>
				    <div class="form-group col-md-6 ">
				        <label class="control-label" for="applyAmount">请款金额</label>
				        <div>
				            <input type="text" class="form-control input-sm field-editable fixed-number text-right"  id="applyAmount" name="applyAmount" data-parsley-maxlength="17" data-parsley-type="number" />
				        </div>
				    </div>
					<div class="form-group col-md-12">
						<label class="control-label" for="applyRemark">请款备注</label>
						<div>
							<textarea class="form-control field-editable" name="applyRemark" id="applyRemark" rows="3" cols="" data-parsley-maxlength="500"></textarea>
						</div>
					</div>
				</form>
				<table id="applyProjectTable" class="table table-hover table-bordered nowrap applyProjectTable" width="100%">
			        <thead>
			            <tr>
			            	<th></th>
			            	<th>工程编号</th>
			                <th>工程名称</th>
			                <th>工程地点</th>
			                <th>工程规模</th>
			                <th>请款金额</th>
			                <th>质保金</th>
			            </tr>
			        </thead>
				</table>
	        </div>
			</div>
		</div>
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" >
			<div class="panel-heading">
				<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	            </div>
				<h4 class="panel-title">确认区</h4>
			</div>
			<div class="panel-body" id="drawing_audit_panel_box">
					<div id="divisionalAcceptanceAuditTopBox">
						<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
         				
					</div>
			    	<div class="clearboth form-box paymentAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="paymentAuditForm" action="paymentAudit/auditSave">
			    			<input type="hidden"id="projId" name = "projId" value = "${pa.projId}"/>
			    			<input type="hidden"  name = "projNo" value = "${pa.projNo}"/>
							<input type="hidden" class="menuId" name = "menuId" value = ""/>
                    		<input type="hidden"id="businessOrderId" name = "businessOrderId" value = "${pa.paId}"/>
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
						           <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime"  data-parsley-maxlength="100" >
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
			    </div>
	     	</div>
		</div>	    	
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('预算审核 - 工程管理系统');
    
    $("#paymentAuditForm").toggleEditState();
    $("#paymentAuditForm").styleFit();
   
    
    $("#paymentApplyAuditForm").toggleEditState();
    $("#paymentApplyAuditForm").styleFit();
    
    
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/subcontract/payment-audit-page.js?'+Math.random()).done(function () {
    	auditHistory.init();
	});

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("paymentAudit/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
        $(".menuId").val(getStepId());
    	var val=$('#paymentAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
    		$("#paymentAuditForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".saveBtn"));
    	}
    });
    
    
    var ensureDone=function(){};
    
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.paymentAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#paymentAuditForm input:radio[name="mrResult"]:checked').val();
    		$("#paymentAuditForm").toggleEditState(false); 
    	}
    }
    
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//确认过
    		auditSaveCallBack("true");
    	}
    }();
    
    
    
    //查询详述
    var queryDetail=function(){
    	var projId=$("#projId").val(),paId=$("#businessOrderId").val();
    	var data={"projId":projId,"paId":paId};
    	var url = 'paymentApply/viewPaymentApply';
		var f = $("#paymentApplyAuditForm");
		 $.ajax({
	         type: 'POST',
	         url: url,
	         data: data,
	         dataType: 'json',
	         success: function (data) {
	        	 
	        	 console.info(data);
	        	 
	        	 //$("#paymentApplyAuditForm").formReset();
	        	 var selects = f.find('select[disabled]');
	             selects.removeAttr("disabled");
	             $("input:radio").attr("disabled",false);
	             //表单反序列化填充值
	             f.deserialize(fixNull(data));
	             
	             $(".corpName").val($("#corpName").val());
	             $(".deptName").val($("#deptName").val());
	             
	             selects.attr("disabled","disabled");
	             
                 /* if(data.payType == "1"){
                     $(".construction").removeClass("hidden");
                     $(".detection").addClass("hidden");
                     $(".supervisor").addClass("hidden");
                 }else if(data.payType == "2"){
                     $(".supervisor").removeClass("hidden");
                     $(".construction").addClass("hidden");
                     $(".detection").addClass("hidden");
                 }else {
                     $(".detection").removeClass("hidden");
                     $(".construction").addClass("hidden");
                     $(".supervisor").addClass("hidden");
                 }

	             if($("#contractAmount").val()==""){
	            	 $(".con").addClass("hidden");
	             }else{
	            	 $(".con").removeClass("hidden");
	             } */
	             
	             $("#surplusAmount").val((new Number($("#contractAmount").val())-new Number($("#payAmount").val())).toFixed(2));
	             
	             console.info("1--"+$("#feeType option:selected").val());
	             console.info("2--"+$(".consFeeType").val());
	            if($("#feeType option:selected").val()===$(".consFeeType").val()){
	            	 //工程费 
	            	 $(".cons").removeClass("hidden");
	            	 $(".applyProjectTable").addClass("hidden");
	             }else{
	            	 $(".cons").addClass("hidden");
	            	 $(".applyProjectTable").removeClass("hidden");
	            	 
	            	 if($.fn.DataTable.isDataTable('#applyProjectTable')){
	            		var feeApplyListData={};     
            			feeApplyListData.paId = $("#paId").val()||"-1";
            			//初始化过
            			$("#applyProjectTable").cgetData(false);//列表重新加载
            		 }else{
            			applyProjectTableInit();
            		 }
	            	 
	             } 
	            
	         },
	         error: function (jqXHR, textStatus, errorThrown) {
	             console.warn("cgetDetail() -> 详情查询失败");
	         }
	     });
    }();
    
    
    var applyProjectTable,feeApplyListData={}
    
    var applyProjectTableInit = function(){
    	"use strict";
    	feeApplyListData.paId = $("#paId").val()||"-1";
        if ($('#applyProjectTable').length !== 0) {
        	applyProjectTable=$('#applyProjectTable').on( 'init.dt',function(){
       			//默认选中第一行
        		$(this).bindDTSelected(trListSelectedBack,true);
        		//隐藏遮罩
       			$('#applyProjectTable').hideMask();
            }).DataTable({
            	language: language_CN,
                lengthMenu: [18],
               // dom: 'Brtip',
                dom: 'Brtip',
                buttons: [
                ],
                //启用服务端模式，后台进行分段查询、排序
                serverSide:true,
                ajax: {
                    url: 'costApply/queryFeeApplyList',
                    type:'post',
                    data: function(d){
                       	$.each(feeApplyListData,function(i,k){
                       		d[i] = k;
                       	});
                       	
                    },
                    dataSrc: 'data'
                },
                // ajax: 'projectjs/complete/json/joint-acceptance-list.json',
                responsive: {
                	details: {
                		renderer: function ( api, rowIdx, columns ){
                			return renderChild(api, rowIdx, columns);
                		}
                	}
                },
                select: true,  //支持多选
                columns: [
                    {"data":"falId",className:"none never"},
          			{"data":"projNo"},
    	  			{"data":"projName"},
    	  			{"data":"projAddr",className:"hidden"},
    	  			{"data":"projScaleDes",className:"hidden"},
    	  			{"data":"applyAmount"},
    	  			{"data":"endAmount"}
    				],
    			columnDefs: [{
    				"targets": 0,
    			    "visible":false
    			},{
    				"targets":1,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: 15, 	//截取多少字符（或汉字）
    					end: false		//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    				})
    			 },{
    				"targets":2,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: 15, 	//截取多少字符（或汉字）
    					end: false		//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    				})
    			 },{
    				"targets":3,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: 10, 	//截取多少字符（或汉字）
    					end: false		//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    				})
    			 }],
            });
        }
    }



    var trListSelectedBack=function(v, i, index, t, json){
    }

    
    
	
		
		
</script>
<!-- ================== END PAGE LEVEL JS ================== -->