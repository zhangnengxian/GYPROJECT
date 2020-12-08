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
		<input class = "hidden" name="curStep" id="curStep" value="${stepId}"/>
		<input class = "hidden" id="sysDate" value="${sysDate}"/>
		<input class = "hidden" id="busOrderIds" value="${busOrderId}"/>
		<form class="form-horizontal" id="rectifyForm" data-parsley-validate="true" action="" method="POST">
		    <input type="hidden" name="projId" id="projId" value=""/>
		    <input type="hidden" name="version" id="version" value=""/>
		    <input type="hidden" name="rnId" id="rnId" value=""  class="addClear"/>
		    <input type="hidden" name="projNo" id="projNo" />
		    <input type="hidden" name="busOrderId" id="busOrderId" value="${busOrderId }"/>
		    <input type="hidden" name="rectifyNoticeType"/>
			<div class="form-group col-sm-6">
	            <label class="control-label" for="rnNo">整改编号</label>
	            <div>
	                <input type="text" class="form-control input-sm  field-not-editable addClear" id="rnNo"  name="rnNo" >
	            </div>
	        </div> 
	        <div class="form-group col-sm-6">
	            <label class="control-label" for="mbsId">发起人</label>
	            <div>
	                <input type="hidden" class="form-control input-sm  field-not-editable" id="rnCreateStaffId"  name="rnCreateStaffId"  value="${loginInfo.staffId }"/>
	                <input type="text" class="form-control input-sm  field-not-editable" id="rnCreateStaff"  name="rnCreateStaff"   value="${loginInfo.staffName }"/>
	            </div>
	        </div> 
	        <div class="form-group col-sm-12">
				<label class="control-label" for="rnQuestions">整改内容</label>
			    <div >
	                <textarea row="4" class="form-control input-sm field-editable addClear"  id="rnQuestions" name="rnQuestions"  data-parsley-required="true" data-parsley-maxlength="500" /></textarea>
	            </div>
			</div>
	        <div class="form-group col-md-6">
				<label class="control-label" for="limitTime">整改期限</label>
			    <div >
	                <input type="text" class="form-control input-sm field-editable addClear"  id="limitTime" name="limitTime"  data-parsley-required="true" data-parsley-maxlength="200" />
	            </div>
			</div>
			<!-- <div class="form-group col-sm-12 ">
				<label class="control-label" for="rnCreateTime">发起日期</label>
			    <div >
	                <textarea row="4" class="form-control input-sm field-not-editable" id="rnCreateTime" name="rnCreateTime" data-parsley-maxlength="500" /></textarea>
	            </div>
			</div> -->
			<div class="form-group col-md-6">
		        <label class="control-label" for="">发起日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default " id="rnCreateTime"  name="rnCreateTime" data-parsley-maxlength="100" value="" data-parsley-required="true">
		        </div>
		    </div>
		</form>
	</div>
</div>
<div class="p-t-6 p-b-15 p-l-10">
	<table id="rectifyTable" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
     			<th></th>
     			<th></th>
           		<th>整改编号</th>
           		<th>整改内容</th>
           		<th>发起人</th>
            	<th>整改期限</th>
            	<th>整改日期</th>
           	</tr>
       	</thead>
	</table>
</div>
<script>
    App.restartGlobalFunction();
    //表单样式适应
    $("#rectifyForm").toggleEditState(false).styleFit();
    var rectifyForm = $("#rectifyForm");
    $(".applySaveBtn").on("click",function(){
    	if (rectifyForm.parsley().isValid()) {
    		var viewdata = $('#rectifyForm').serializeJson();
    		$(".infodetails").loadMask();	
    		$.ajax({
                  type: 'POST',
                  url: 'rectifyNotice/saveRectifiyNotice',
                  contentType: "application/json;charset=UTF-8",
                  dataType:"json",
                  data: JSON.stringify(viewdata),
                  success: function (data) {
               	//取消遮罩
               	$(".infodetails").hideMask();	
                  	var content = "数据保存成功！";
                  	if(data === "false"){
                  		content = "数据保存失败！";
                  	}
                  	var myoptions = {
                          	title: "提示信息",
                          	content: content,
                          	accept: popClose,
                          	chide: true,
                          //	newpop:"second",
                          	icon: "fa-check-circle"
                      }
                      $("body").cgetPopup(myoptions);
                  },
                  error: function (jqXHR, textStatus, errorThrown) {
               	   //取消遮罩
               	   $(".infodetails").hideMask();	
                      console.warn("整改信息保存失败！");
                  }
              });
    		//如果通过验证, 则移除验证UI
    		rectifyForm.parsley().reset();
    	}else{
   			//如果未通过验证, 则加载验证UI
       		rectifyForm.parsley().validate();
    	}
    });
    $(".applyCancelBtn").on("click",function(){
    	popClose();
    });
    $(".editbtn").addClass('hidden');
    function saveBack(){
    	//$("[data-attach-table='all']").cgetData(true);
    	//console.info($("[data-attach-table='all']"));
    	$(".addApplyBtn").removeClass('hidden');
    	$('#rectifyTable').cgetData();
    }
    var rectifyTable;
    var handleRectifyPop = function() {
    	'use strict';
    	var searchData={};
    	searchData.projId=$("#projId").val();
    	searchData.busOrderId=$("#busOrderId").val();
    	if ($('#rectifyTable').length !== 0) {
    		rectifyTable= $('#rectifyTable').on( 'init.dt',function(){
       			//默认选中第一行
        		$(this).bindDTSelected(trSelectedBackRectify,true);
       		    addBtnMonitor();
       		 	updateApplyBtnMonitor();
       		 	delBtnMonitor();
            }).DataTable({
            	language: language_CN,
                lengthMenu: [18],
                dom: 'Brtip',
                buttons: [
                     { text: '增加', className: 'btn-sm btn-query business-tool-btn  addApplyBtn' },
                     { text: '修改', className: 'btn-sm btn-query business-tool-btn  updateApplyBtn hidden' },
                     { text: '删除', className: 'btn-sm btn-warn business-tool-btn  delBtn hidden' },
                ],
                //启用服务端模式，后台进行分段查询、排序
    			serverSide:true,
                ajax: {
                    url: 'rectifyNotice/queryRectifyNotices',
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
                    {"data":"rnId",className:"none never"}, 
                    {"data":"busOrderId",className:"none never"}, 
    	  			{"data":"rnNo"}, 
    				{"data":"rnQuestions"},
    				{"data":"rnCreateStaff"},
    				{"data":"limitTime"},
    				{"data":"rnCreateTime"},
    			],
    			columnDefs: [{
    				"targets": 0,
    			    "visible":false
    			},{
    				"targets":1,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: 30, 	//截取多少字符（或汉字）
    					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    				})
    			},{
    				"targets": 6,
    				"render": function ( data, type, row, meta ) {
    					if(type === "display"){
    						return format(data);
    					}else{
    						return data;
    					}
    				},
    			}],
    			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
    				if(aData.overdue){
    					$(nRow).addClass("expired-proect");
    				}
    			}
            });
    	}	
    }


    var trSelectedBackRectify = function(v, i, index, t, json){
    	$(".editbtn").addClass('hidden');
    	t.cgetDetail('rectifyForm','rectifyNotice/viewRectifyNotice','',scaleDetailRefresh);
    }
    var scaleDetailRefresh=function(data){
    	//审核中 已审核过状态
    	$(".updateApplyBtn,.delBtn").removeClass("hidden");
    	$("#rnCreateTime").val(format(data.rnCreateTime));
    }
    
    //增加
    var addBtnMonitor=function(){
    	$(".addApplyBtn").off("click").on("click",function(){
    		
    		var data = rectifyTable.rows().data(),
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
	                chide: true,
	                accept: popClose,
	                newpop:"second",
	            });
	        }else{
	        	$(".editbtn").removeClass('hidden');
	    		$(".addApplyBtn,.audit").addClass('hidden');
	    		$("#delayPeriod").val("");
	    		$("#faId").val("");
	    		$("#rectifyForm .addClear").val("");
	    		//$("#delayReason option:first").prop("selected", 'selected'); 
	    		$("#rectifyForm").toggleEditState(true);
	    		//$("#rnCreateTime").val($("#sysDate").val());
	    		var sysDate = timestamp($("#sysDate").val());
                $("#rnCreateTime").val(format(sysDate,"default"));
	        }
    	})
    }
    
    var updateApplyBtnMonitor=function(){
    	$(".updateApplyBtn").off("click").on("click",function(){
    		$(".editbtn").removeClass('hidden');
    		$(".updateApplyBtn").addClass('hidden');
    		$("#rectifyForm").toggleEditState(true);
    	})
    }
    var delBtnMonitor=function(){
  	  $(".delBtn").on("click",function(){
  		  var len=$("#rectifyTable").find("tr.selected").length;
  			if(len>0){
  			  $("body").cgetPopup({
  					title: '提示',
  					content: '点击删除后，该报验记录不可恢复，您确定要删除该报验吗？',
  					chide: false,
  					cancel:popClose,
  				    accept: delBtnDone,   //点击确认以后执行删除函数
  					newpop:"second",
  			  });
  			}else{
  				alertInfo("请先选择要删除的报验记录！");
  			}
  	  })
  };
  //删除函数
  var delBtnDone = function(){
  	$("#rectifyTable").cdeleteRow("rnId","rectifyNotice/deletById",delBack);
  }
  var delBack = function(){
	  $("#rectifyTable").cgetData(true);
  }
</script>
<!-- ================== END PAGE LEVEL JS ================== -->