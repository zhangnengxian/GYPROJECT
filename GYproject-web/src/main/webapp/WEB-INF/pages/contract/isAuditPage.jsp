<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="content" class="content">
	<div class="row">
        <div class="col-sm-6 col-xs-12">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                   <div class="panel-heading-btn">
                       <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                       <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                       <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                   </div>
                   <h4 class="panel-title">补充协议</h4>
                </div>
                <div class="panel-body" id="isAudit_panelBox">

					<!--智能表合同补充协议详细区-->

				</div>
        	</div>
        </div>



        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <h4 class="panel-title">审批区</h4>
			    </div>
			    <div class="panel-body" >
			    	<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSaveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 auditCancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box auditFormDiv">
			    		<form class="form-horizontal" id="isAuditForm" action="">
			    			<input type="hidden" id="isAudit" name="isAudit" class="isAudit"  value = "${ isAudit}"/>
                    		<input type="hidden"  id="isId" name="isId" class="isId"  value = "${ isId}"/>
                    		<input type="hidden" id="businessOrderId" name="businessOrderId" value = "${ isId}"/>
                    		<input type="hidden" id="mrAuditLevel" name="mrAuditLevel"  value = "${ currentLevel}"/>

							<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
			        			<label class="control-label" for="">审批结果</label>
				            	<div>
						             <c:forEach var="enums" items="${mrResult}">
					             		 <label>
							            	<input type="radio" name="mrResult" value="${enums.value}"/>${enums.message}
							            </label>
						             </c:forEach>
						        </div>
		    				</div>
		    				<div class="form-group col-lg-12 col-md-12 col-sm-12">
						     	<label class="control-label" for="">审批意见</label>
						     	<div> 
		        					<textarea class="form-control field-editable"  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					</div>
		   					<div class="form-group col-lg-6 col-md-12 col-sm-6">
						        <label class="control-label" for="">审批人</label>
						        <div>
						           <input type="hidden"  id="mrCsr"  name="mrCsr" data-parsley-maxlength="100" value="${loginInfo.staffId}">
						           <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
						        </div>
						    </div>
						    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
						        <label class="control-label" for="">审批日期</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" data-parsley-maxlength="100" >
						        </div>
						    </div>
			    		</form>
			    	</div>




			    	<div >
		    		<h4 class="m-t-15 m-l-7"><strong>审批历史</strong></h4>
		    		</div>
		    		<table id="auditResultTable" class="table table-striped table-bordered nowrap" width="100%">
       					<thead>
			            	<tr>
			                <th>审批日期</th>
			                <th>审批结果</th>
			                <th>审批意见</th>
			                <th>审批人</th>
	            			</tr>
          				</thead>
					</table>
			    </div>
			</div>
        </div>
	</div>
</div>



<script>
    App.restartGlobalFunction();
    App.setPageTitle('补充协议审核 - 工程管理系统');
    var histSearchData={};
    $("#isAuditForm").styleFit();

    //表单不可编辑
    $("#supplementForm").toggleEditState(false);
    $("#isAuditForm").toggleEditState(true);
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });


    $(function () {
        $.getScript("js/ellipsis.js").done(function(){
            controlForm();
            contractDetailPage()

        })
    });

function controlForm(){
    var isAudit=$("#isAudit").val();
    if (isAudit=="1"){
        $("#isAuditForm").toggleEditState(false);//切换表单不可编辑

    }
}



    //保存
    $(".auditSaveBtn").off("click").on("click",function(){
        var pf = $("#isAuditForm");
        if (pf.parsley().isValid()) { //验证必填是否已填写
            $("#isAuditForm").loadMask("保存中···", 1, 0.5);
            var formData = $("#isAuditForm").serializeJson();
				formData.projId=$("#projId").val();
				formData.projNo=$("#projNo").val();

            Base.subimt('intelligentSupplementAudit/auditSave', 'POST', JSON .stringify(formData), function(data) {
                var content = data ? "保存成功！" : "保存失败！";
                tips(content);
                $("#isAuditForm").hideMask();
                $(".auditSaveBtn").addClass("hidden");//隐藏保存按钮
                $("#auditResultTable").cgetData(true);//列表重新加载
                $("#auditHistoryTable").cgetData(true);//列表重新加载
                $("#isAuditForm").addClass("hidden");//隐藏表单
                $("#isAuditForm").toggleEditState(false);//切换表单可编辑
            })
        } else {
            pf.parsley().validate();
        }
    });






	var contractDetailPage = function(){ //加载右侧页面查详细
        $('#isAudit_panelBox').cgetContent('intelligentSupplement/supplementViewPage',{},function () {
            trSData.t&&trSData.t.cgetDetail('supplementForm','intelligentSupplement/viewSupplementDetail','',function (data) {
                detailCallbackPayType(data.payType);

                if(!$.fn.DataTable.isDataTable('#auditResultTable')){
                    handleAuditHistory();
                }else{
                    $('#auditResultTable').cgetData(true);
                }

            });
        });
    }

    var handleAuditHistory = function() {
        histSearchData=$("#isAuditForm").serializeJson()

        $('#auditResultTable').on( 'init.dt',function(){
            $('#auditResultTable').hideMask();//隐藏遮罩
        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [],
            serverSide:true,//启用服务端模式，后台进行分段查询、排序
            ajax: {
                url: 'intelligentSupplementAudit/queryManageRecord',
                type:'post',
                data: function(d){$.each(histSearchData,function(i,k){d[i] = k;});},
                dataSrc: 'data'
            },
            responsive: {
                details: {
                    renderer: function ( api, rowIdx, columns ){
                        return renderChild(api, rowIdx, columns);
                    }
                }
            },
            select: true,  //支持多选
            columns: [
                {"data":"mrTime"},
                {"data":"mrResult"},
                {"data":"mrAopinion"},
                {"data":"mrCsr"}
            ],
            columnDefs: [{
                "targets": 0,
                "render": function ( data, type, row, meta ) {
                    if(type === "display"){
                        return format(data,'all');
                    }else{
                        return data;
                    }
                },
            },{
                "targets": 1,
                "render": function ( data, type, row, meta ) {
                    if(data === "1"){
                        return "通过";
                    }else if(data === "0"){
                        return "不通过";
                    }else{
                        return "";
                    }
                },
            }]
        });
    };

    //放弃
    $(".auditCancelBtn").off("click").on("click",function(){
		$("#ajax-content").cgetContent("intelligentSupplementAudit/main");
	});


</script>
