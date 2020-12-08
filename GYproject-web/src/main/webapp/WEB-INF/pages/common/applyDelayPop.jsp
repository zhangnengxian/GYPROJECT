<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div>
<div class="toolBtn f-r p-b-10  editbtn">
   	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 applySaveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 applyCancelBtn">放弃</a>
</div>
<div class="clearboth form-box">
	<%-- <input name="stepId1" id="stepId1" value="${stepId}"/> --%>
	<form class="form-horizontal" id="applyDelayForm" data-parsley-validate="true" action="applyDelay/saveApplyDelay" method="POST">
	    <input type="hidden" name="projId" id="projId" value=""/>
	    <input type="hidden" name="adId" id="adId" value=""/>
	    <input type="hidden" name="projNo" id="projNo" />
	    <input type="hidden" name="auditState" id="auditState" />
	    <input type="hidden"  name="stepId" id="stepId" />
		<div class="form-group col-sm-6">
            <label class="control-label" for="">原定时长</label>
            <div >
                <input type="text" class="form-control field-not-editable input-sm" id="originalPeriod" name="originalPeriod" value="${originalPeriod}"/>
            </div>
        </div>
        <div class="form-group col-sm-6">
            <label class="control-label" for="overDay">已超时长</label>
            <div >
                <input type="text" class="form-control field-not-editable input-sm"  id="overDay" name="overDay" value="0"/>
            </div>
        </div>
        <div class="form-group col-sm-6">
			<label class="control-label" for="delayPeriod">延期时长</label>
		    <div >
                <input type="text" class="form-control input-sm field-editable" id="delayPeriod" name="delayPeriod" data-parsley-required="true"/>
            </div>
		</div>
		<div class="form-group col-sm-6">
            <label class="control-label" for="delayReason">延期原因</label>
            <div>
                <select class="form-control input-sm  field-editable" id="delayReason"  name="delayReason"  >
	                <c:forEach var="enums" items="${delayReason}">
						<option value="${enums.delayReasonId}" >${enums.delayReasonDes}</option>
	                </c:forEach>
	              </select>
            </div>
        </div> 
        <div class="form-group col-sm-12">
			<label class="control-label" for="delayRemark">备注</label>
		    <div >
                <textarea row="4" class="form-control input-sm field-editable"  id="delayRemark" name="delayRemark" data-parsley-maxlength="500" /></textarea>
            </div>
		</div>
		<div class="form-group col-sm-12 hidden audit">
			<label class="control-label" for="notPassReason">审核意见</label>
		    <div >
                <textarea row="4" class="form-control input-sm field-not-editable" id="notPassReason" name="notPassReason" data-parsley-maxlength="500" /></textarea>
            </div>
		</div>
	</form>
</div>
</div>
<div class="p-t-6 p-b-15 p-l-10">
		<table id="applyDelayTable" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
     			<th></th>
           		<th>申请人</th>
           		<th>延期天数</th>
           		<th>申请原因</th>
            	<th>审核状态</th>
           	</tr>
       	</thead>
	</table>
</div>

<script>
    App.restartGlobalFunction();
    
    //表单样式适应
    $("#applyDelayForm").toggleEditState(false).styleFit();
    
    var applyDelayForm = $("#applyDelayForm");
    $(".applySaveBtn").on("click",function(){
    	if (applyDelayForm.parsley().isValid()) {
    		$("#applyDelayForm").cformSave('applyDelayTable',saveBack,false,false);
    		//如果通过验证, 则移除验证UI
    		applyDelayForm.parsley().reset();
    	}else{
   			//如果未通过验证, 则加载验证UI
       		applyDelayForm.parsley().validate();
    	}
    });
    
    $(".applyCancelBtn").on("click",function(){
    	popClose();
    });
    
    $(".editbtn").addClass('hidden'); 
 
    function saveBack(){
    	$(".addApplyBtn").removeClass('hidden');
    	$('#applyDelayTable').cgetData();
    }
   
    var handleApplyDelayPop = function() {
    	'use strict';
    	var searchData={},applyDelayTable;
    	searchData.projId=$("#projId").val();
    	console.info("projId1-----"+$("#projId1").val());
    	if ($('#applyDelayTable').length !== 0) {
    		applyDelayTable= $('#applyDelayTable').on( 'init.dt',function(){
       			//默认选中第一行
        		$(this).bindDTSelected(trSelectedBack,true);
       		    addBtnMonitor();
       		 	updateBtnMonitor();
            }).DataTable({
            	language: language_CN,
                lengthMenu: [18],
                dom: 'Brtip',
                buttons: [
                     { text: '增加', className: 'btn-sm btn-query business-tool-btn  addApplyBtn' },
                     { text: '修改', className: 'btn-sm btn-query business-tool-btn  updateBtn hidden' },
                ],
                //启用服务端模式，后台进行分段查询、排序
    			serverSide:true,
                ajax: {
                    url: 'applyDelay/queryApplyDelay',
                    type:'post',
                    data: function(d){
                       	$.each(searchData,function(i,k){
                       		d[i] = k;
                       	});
                       	
                    },
                    dataSrc: 'data'
                },
                //ajax: 'projectjs/accept/json/attach.json',
                responsive: {
                	details: {
                		renderer: function ( api, rowIdx, columns ){
                			return renderChild(api, rowIdx, columns);
                		}
                	}
                },
                select: true,  //支持多选
                columns: [
                    {"data":"adId",className:"none never"}, 
    	  			{"data":"adOperator"}, 
    				{"data":"delayPeriod"},
    				{"data":"delayReasonDes"},
    				{"data":"auditStateDes"},
    			],
    			columnDefs: [{
    				"targets": 0,
    			    "visible":false
    			},{
    				"targets":3,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: 20, 	//截取多少字符（或汉字）
    					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    				})
    			}],
    			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
    				if(aData.overdue){
    					$(nRow).addClass("expired-proect");
    				}
    			}
            });
    	}	
    }


    var trSelectedBack = function(v, i, index, t, json){
    	$(".editbtn").addClass('hidden');
    	t.cgetDetail('applyDelayForm','applyDelay/findById','',scaleDetailRefresh);
    }
    var scaleDetailRefresh=function(){
    	//审核中 已审核过状态
    	if($("#auditState").val()=="1" ||$("#auditState").val()=="3" ){
    		$(".updateBtn").addClass("hidden");
    	}else{
    		$(".updateBtn").removeClass("hidden");
    	}
    	//审核通过 或审核不过
    	if($("#auditState").val()=="2"||$("#auditState").val()=="3" ){
    		$(".audit").removeClass('hidden');
    	}else{
    		$(".audit").addClass('hidden');
    	}
    	
    }
    
    //增加
    var addBtnMonitor=function(){
    	$(".addApplyBtn").off("click").on("click",function(){
    		$(".editbtn").removeClass('hidden');
    		$(".addApplyBtn,.audit").addClass('hidden');
    		$("#delayPeriod").val("");
    		$("#adId").val("");
    		$("#delayRemark").val("");
    		$("#auditStateDes").val("");
    		$("#notPassReason").val("");
    		console.info("stepId1---"+$("#stepId1").val());
    		console.info("stepId---"+$("#stepId").val());
    		/* $("#stepId").val("");
    		$("#stepId").val($("#stepId1").val()); */
    		$("#delayReason option:first").prop("selected", 'selected'); 
    		$("#applyDelayForm").toggleEditState(true);
    	})
    }
    
    var updateBtnMonitor=function(){
    	$(".updateBtn").off("click").on("click",function(){
    		$(".editbtn").removeClass('hidden');
    		$(".updateBtn").addClass('hidden');
    		$("#applyDelayForm").toggleEditState(true);
    	})
    }
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->