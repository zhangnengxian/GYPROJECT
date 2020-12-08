<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
	   	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 applySaveBtn" >保存</a>
	     <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 applyCancelBtn">放弃</a>
	</div>
	<div class="clearboth form-box">
		<input class = "hidden" name="fallStepId" id="fallStepId" value="${stepId}"/>
		<input type="hidden" name="tableId" id="tableId" value="${tableId}"/>
		<input type="hidden" name="isAudit" id="isAudit" value="${mbsList[0].isAudit}"/>

		<form class="form-horizontal" id="fallbackApplyForm" data-parsley-validate="true" action="" method="POST">
		    <input type="hidden" name="projId" id="projId" value=""/>
		    <input type="hidden" name="faId" id="faId" value=""/>
		    <input type="hidden" name="projNo" id="projNo" />
		    <input type="hidden" name="auditState" id="auditState" />
		    <input type="hidden"  name="originalStepId" id="originalStepId" />
		    <input type="hidden"  name="applyTime" id="applyTime" />
		    <input type="hidden"  name="fallbackTime" id="fallbackTime" />
			<div class="form-group col-sm-6">
	            <label class="control-label" for="fallbackStepId">回退步骤</label>
	            <div>
	                <select class="form-control input-sm  field-editable" id="fallbackStepId"  name="fallbackStepId"  >
		                <c:forEach var="enums" items="${mbsList}">
							<option value="${enums.backStepId}" >${enums.backStepDes}</option>
		                </c:forEach>
		              </select>
	            </div>
	        </div> 
	        <div class="form-group col-sm-6 hidden">
	            <label class="control-label" for="mbsId">回退标准</label>
	            <div>
	                <select class="form-control input-sm  field-editable" id="mbsId"  name="mbsId"  >
		                <c:forEach var="enums" items="${mbsList}">
							<option value="${enums.mbsId}">${enums.backStepId}</option>
		                </c:forEach>
		              </select>
	            </div>
	        </div>

			<div class="form-group col-sm-12">
				<label class="control-label" for="fallbackReason">退回原因</label>
			    <div >
	                <textarea row="4" class="form-control input-sm field-editable"  id="fallbackReason" name="fallbackReason"  data-parsley-required="true" data-parsley-maxlength="500" /></textarea>
	            </div>
			</div>
			<div class="form-group col-sm-12 hidden audit">
				<label class="control-label" for="auditInfo">审核意见</label>
			    <div >
	                <textarea row="4" class="form-control input-sm field-not-editable" id="auditInfo" name="auditInfo" data-parsley-maxlength="500" /></textarea>
	            </div>
			</div>
		</form>
	</div>
</div>
<div class="p-t-6 p-b-15 p-l-10">
	<table id="fallbackApplyTable" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
     			<th></th>
           		<th>工程名称</th>
           		<th>申请人</th>
           		<th>申请时间</th>
            	<th>审核状态</th>
            	<th>待办人</th>
           	</tr>
       	</thead>
	</table>
</div>
<script>
    App.restartGlobalFunction();
    //表单样式适应
    $("#fallbackApplyForm").toggleEditState(false).styleFit();
    var count = 0;
    var fallbackApplyForm = $("#fallbackApplyForm");
    $(".applySaveBtn").on("click",function(){
    	count = count +1;
    	$(".applySaveBtn").attr("disabled",true);
        $(".applySaveBtn").addClass("hidden");
    	if (fallbackApplyForm.parsley().isValid()) {
    		$("#originalStepId").val($("#fallStepId").val());
    		var viewdata = $('#fallbackApplyForm').serializeJson();
			viewdata.isAudit=$('#isAudit').val();
    		if (count == 1) {
    			Base.subimt('fallbackApply/saveFallbackApply',"POST",JSON.stringify(viewdata),function (data) {
        			$(".applySaveBtn").removeClass("hidden");
    				tips(data.ret_message,'confirm')
    			})
    		}
    		//如果通过验证, 则移除验证UI
    		fallbackApplyForm.parsley().reset();
    	}else{
   			//如果未通过验证, 则加载验证UI
       		fallbackApplyForm.parsley().validate();
    	}
    });


	function tips(content,acceptName) {
		var myoptions = {
			title: "提示信息",
			content: content,
			accept: acceptName,
			chide: true,
			icon: "fa-check-circle"
		}
		$("body").cgetPopup(myoptions);
	}

    var confirm = function () {
    	count =0;
        popClose();
        var tableId = $("#tableId").val();
		$("#"+tableId+"").cgetData(true);
    }






    $(".applyCancelBtn").on("click",function(){
    	popClose();
    });
    $(".editbtn").addClass('hidden');
    function saveBack(){
    	//$("[data-attach-table='all']").cgetData(true);
    	//console.info($("[data-attach-table='all']"));
    	$(".addApplyBtn").removeClass('hidden');
    	$('#fallbackApplyTable').cgetData();
    }
    var fallbackApplyTable;
    var handleFallbackApplyPop = function() {
    	'use strict';
    	var searchData={};
    	searchData.projId=$("#projId").val();
    	searchData.originalStepId=$("#fallStepId").val();
    	if ($('#fallbackApplyTable').length !== 0) {
    		fallbackApplyTable= $('#fallbackApplyTable').on( 'init.dt',function(){
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
                    url: 'fallbackApply/queryFallbackApply',
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
                    {"data":"faId",className:"none never"}, 
    	  			{"data":"projName"}, 
    				{"data":"applyOperator"},
    				{"data":"applyTime"},
    				{"data":"auditStateDes"},
    				{"data":"todoer"}
    			],
    			columnDefs: [{
    				"targets": 0,
    			    "visible":false
    			},{
    				"targets":1,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: 10, 	//截取多少字符（或汉字）
    					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
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
    	t.cgetDetail('fallbackApplyForm','fallbackApply/findById','',scaleDetailRefresh);
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
    		
    		var data = fallbackApplyTable.rows().data(),
            addFlag;
	        for(var i = 0;i<data.length;i++){
	        	//有审核中的 或审核不过的
	            if(data[i].auditState == "1" /* || data[i].auditState == "2" */ ){
	                addFlag="1";
	                break;
	            }
	        }
	        if(addFlag=="1"){//如果有审核中的工程，提示不能增加
	            //弹屏
	            $("body").cgetPopup({
	                title: '提示',
	                content: "该工程有回退信息正在审核中，不允许增加!",
	                chide: 'true',
	                accept: popClose
	            });
	        }else{
	        	$(".editbtn").removeClass('hidden');
	    		$(".addApplyBtn,.audit").addClass('hidden');
	    		$("#delayPeriod").val("");
	    		$("#faId").val("");
	    		$("#fallbackApplyForm").formReset();
	    		//$("#delayReason option:first").prop("selected", 'selected'); 
	    		$("#fallbackApplyForm").toggleEditState(true);
	        }
    	})
    }
    
    var updateBtnMonitor=function(){
    	$(".updateBtn").off("click").on("click",function(){
    		$(".editbtn").removeClass('hidden');
    		$(".updateBtn").addClass('hidden');
    		$("#fallbackApplyForm").toggleEditState(true);
    	})
    }
    $("#fallbackStepId").on("change",function(){
    	$("#mbsId option:contains('"+$(this).val()+"')").attr("selected", "selected");
    	console.info("回退步骤："+$(this).val()+"---"+$("#mbsId").find("option:selected").text()+"---"+$("#mbsId").find("option:selected").val());
    })
</script>
<!-- ================== END PAGE LEVEL JS ================== -->