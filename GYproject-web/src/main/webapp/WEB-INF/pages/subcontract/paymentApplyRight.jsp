<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5  saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<input type="hidden" id="flag" name="flag"/>
			<input type="hidden" id="menuId" name="menuId" value="110609"/>
    	<form class="form-horizontal" id="paymentApplyForm" data-parsley-validate="true" action="paymentApply/savePaymentApply">
       		<input type="hidden" id="projId" name="projId"/>
       		<input type="hidden" id="paId" name="paId" class="bus"/>
       		<input type="hidden" id="signBack" name="signBack"/>
			<input type="hidden" id="payType" name="payType"/>
			<input type="hidden" id="menuId1" name="menuId"  value="110609"/>
			
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		        	<textarea class="form-control input-sm field-not-editable" name="projScaleDes" rows="4" ></textarea>
		        </div>
		    </div>
			<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    		 <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value=""/>        
		        </div>
		    </div>
		     <div class="form-group col-md-12">
		        <label class="control-label" for="requestFoundsUnit">请款单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="requestFoundsUnit" name="requestFoundsUnit"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		    	<label class="control-label" for="applyNo">申请编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="applyNo" name="applyNo" data-parsley-maxlength="50" />
		        </div>
		    </div>
		    <%--<div class="form-group col-md-6">--%>
		        <%--<label class="control-label" for="applyDate">申请日期</label>--%>
		        <%--<div>--%>
		           <%--<input type="text" class=" form-control input-sm field-not-editable datepicker-default " id="applyDate"  name="applyDate"  value="" data-parsley-required="true">--%>
		        <%--</div>--%>
		    <%--</div>--%>
		  <!--    <div class="form-group col-md-6">
		        <label class="control-label" for="applyDeptName">申请部门</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="applyDeptName" name="applyDeptName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div> -->
		    <div class="form-group col-md-6 construction">
	            <label class="control-label" for="applyReason">申请事由</label>
	            <div>
	                <select class="form-control input-sm field-editable" id="applyReason"  name="applyReason"  data-parsley-required="true">
		        		<option value="15" >预付款</option>
		        		<option value="19" >进度款</option>
		        		<option value="17" >结算款</option>
		        	</select>
	            </div>
            </div>
		    <div class="form-group col-md-6 construction">
		        <label class="control-label" for="sdType">结算方式</label>
		        <div>
		        	<select class="form-control input-sm field-editable" id="sdType"  name="sdType"  data-parsley-required="true">
		        		<option value="1" >预结算制</option>
		        		<option value="2" >合同制</option>
		        		<option value="3" >其他</option>
		        	</select>
		        </div>
		    </div>
		 <!--    <div class="form-group col-md-6">
		        <label class="control-label" for="contractAmount">用户合同金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="contractAmount" name="contractAmount" data-parsley-maxlength="17" />
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="payAmount">已付金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="payAmount" name="payAmount" data-parsley-maxlength="17" />
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="surplusAmount">剩余金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="surplusAmount" name="surplusAmount" data-parsley-maxlength="17" />
		        </div>
		    </div> -->
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scAmount">分包合同金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="scAmount" name="scAmount" data-parsley-maxlength="17" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 construction">
		        <label class="control-label" for="payScAmount">已付工程款</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="payScAmount" name="payScAmount" data-parsley-maxlength="17" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 construction">
		        <label class="control-label" for="applyAmount">申请工程款</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable fixed-number text-right"  id="applyAmount" name="applyAmount" data-parsley-maxlength="17" data-parsley-type="number" />
		        </div>
		    </div>
			<!-- <div class="form-group col-md-6 supervisor ">
				<label class="control-label" for="suAmount">申请监理费</label>
				<div>
					<input type="text" class="form-control input-sm field-editable fixed-number text-right"  id="suAmount" name="suAmount" data-parsley-maxlength="17" data-parsley-type="number" />
				</div>
			</div>
			<div class="form-group col-md-6 detection hidden">
				<label class="control-label" for="deAmount">申请检测费</label>
				<div>
					<input type="text" class="form-control input-sm field-editable fixed-number text-right"  id="deAmount" name="deAmount" data-parsley-maxlength="17" data-parsley-type="number" />
				</div>
			</div> -->
		    <!-- <div class="form-group col-md-6">
		        <label class="control-label" for="invoiceNo">发票号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="invoiceNo" name="invoiceNo" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div> -->
		    <div class="form-group col-md-12">
		        <label class="control-label" for="applyRemark">请款备注</label>
		        <div>
		            <textarea type="text" class="form-control input-sm field-editable"  id="applyRemark" name="applyRemark" data-parsley-maxlength="500" value="" rows="3"></textarea>
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
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $('.infodetails').hideMask();
    $('.editbtn').addClass('hidden');
    //表单样式适应
    $('#paymentApplyForm').toggleEditState(false).styleFit();
  	//查右侧工程详述
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });



    //保存
    $(".saveBtn").off().on("click",function(){
    	$("#menuId1").val($("#menuId").val());
    	$('#paymentApplyForm').cformSave('paymentApplyTable',saveAcceptanceApplyBack,true);
    })
  	
    var saveAcceptanceApplyBack=function(){
    	$(".editbtn").addClass("hidden");
    	$('#paymentApplyForm').toggleEditState(false);

    }
  	
   
    //放弃
    $(".cancelBtn").on("click",function(){
    	$("#flag").val("");
    	
    	if($("#paId").val()==""){
    		//返回列表区
    		$('ul.nav-tabs>li.active').removeClass("active");
    		$('#listTab').tab("show");
    		$('#paymentApplyTable').cgetData(true,queryTableBack);
    	}else{
    		//返回列表区
    		$('ul.nav-tabs>li.active').removeClass("active");
    		$('#listTab').tab("show");
    	}
    });





    var histSearchData={};
    var detailedCallback = function(data){
		histSearchData.projId=data.projId;
        histSearchData.businessOrderId=data.paId;
        if($.fn.DataTable.isDataTable('#auditHistoryTable')){ //初始化过
            $("#auditHistoryTable").cgetData(false);//列表重新加载
        }else{
            handleAuditHistory();
        }
	};

    var handleAuditHistory = function() {
        if ($('#auditHistoryTable').length !== 0) {
            $('#auditHistoryTable').on( 'init.dt',function(){
                //隐藏遮罩
                $('#auditHistoryTable').hideMask();

            }).DataTable({
                language: language_CN,
                lengthMenu: [18],
                dom: 'Brt',
                buttons: [
                ],
                //启用服务端模式，后台进行分段查询、排序
                serverSide:true,
                ajax: {
                    url: 'paymentAudit/queryManageRecord',
                    type:'post',
                    data: function(d){
                        $.each(histSearchData,function(i,k){
                            d[i] = k;
                        });

                    },
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
        }
    };


    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->