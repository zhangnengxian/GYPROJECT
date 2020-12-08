<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
    <div class="clearboth form-box">
    	<!-- 施工合同 -->
    	<div class="form-group col-md-6 ">
    		<h3>施工合同</h3>
    		<form class="form-horizontal" id="detail_contractform"  data-parsley-validate="true" action="">
	       		<input type="hidden" name="projId" />
	       		<div class="form-group col-md-6 ">
			    	<label class="control-label" for="conNo">合同编号</label>
			        <div>
			        	<input type="text" class="form-control input-sm field-editable"   id="conNo" name="conNo" value=""/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 clearboth">
			        <label class="control-label" for="custName">甲方客户名称</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="custPhone">甲方联系方式</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" value=""/>
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="custLegalRepresent">甲方法定代表</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="custLegalRepresent" name="custLegalRepresent" value="" />
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="custEntrustRepresent">甲方委托代表</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="custEntrustRepresent" name="custEntrustRepresent" value=""/>
			        </div>
			    </div>
			    
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="gasComp">乙方燃气公司</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="gasComp" name="gasComp" value=""/>
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="gasCompPhone">乙方联系方式</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="gasCompPhone" name="gasCompPhone" value=""/>
			        </div>
			    </div>
			    <div class="form-group col-md-6 ">
			        <label class="control-label" for="gasComPlegalRepresent">乙方法定代表</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="gasComPlegalRepresent" name="gasComPlegalRepresent" value="" />
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="gasCompEntrustRepresent">乙方委托代表</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable"  id="gasCompEntrustRepresent" name="gasCompEntrustRepresent" value=""/>
			        </div>
			    </div>
			    
			    <div class="form-group col-md-12">
		            <label class="control-label" for="conScope">承包范围</label>
		            <div>
		                <textarea class="form-control field-editable" name ="conScope" id="conScope" rows="2" ></textarea>
		            </div>
	            </div>
			    <div class="form-group col-md-6">
			    	<label class="control-label" for="fundSource">资金来源</label>
	            	<div>
	                <select class="form-control input-sm field-editable" id="fundSource"  name="fundSource"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${fundSource}">
							<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		              </select>
		            </div>
				</div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="qualityStandar">质量标准</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable" id="qualityStandar"  name="qualityStandar" value="">
			        </div>
			    </div>
				<div class="form-group col-md-6">
		            <label class="control-label" for="payType">付款方式</label>
	            	<div>
	                <select class="form-control input-sm field-editable" id="payType"  name="payType"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${payType}">
							<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		              </select>
		            </div>
				</div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="contractAmount">合同金额</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable fixed-number" id="contractAmount"  name="contractAmount" value="" >
			        </div>
			    </div>
			    <!-- <div class="form-group col-md-6">
			        <label class="control-label" for="confirmTotalCost">确定造价</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-not-editable " id="confirmTotalCost"  name="confirmTotalCost" value="" >
			        </div>
			    </div> -->
			    <div class="form-group col-md-6">
			        <label class="control-label" for="conAgent">经办人</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable " id="conAgent"  name="conAgent" value="">
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="signDate">签订日期</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="signDate"  name="signDate" value="" >
			        </div>
			    </div>
				
			    <div class="form-group col-md-6 clearboth">
			        <label class="control-label" for="plannedStartDate">开工日期</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="plannedStartDate"  name="plannedStartDate" value="" >
			        </div>
			    </div>
			    <div class="form-group col-md-6">
			        <label class="control-label" for="plannedEndDate">竣工日期</label>
			        <div>
			           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="plannedEndDate"  name="plannedEndDate" value="" >
			        </div>
			    </div>
			    <div class="form-group col-md-6">
		        	<label class="control-label" for="contractPricingType">价格形式</label>
	            	<div>
	                <select class="form-control input-sm field-editable" id="contractPricingType"  name="contractPricingType"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${contractPricingType}">
							<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		              </select>
		            </div>
				</div>
				<div class="form-group col-md-12 riskRange">
	            <label class="control-label" for="riskRange">风险范围</label>
	            <div>
	                <textarea class="form-control field-editable" name ="riskRange" id="riskRange" rows="4" data-parsley-maxlength="1000"></textarea>
	            </div>
	            </div>
				<div class="form-group col-md-12 adjustmentMethod">
			        <label class="control-label" for="adjustmentMethod">调整方法</label>
			        <div>
			           <textarea class=" form-control  field-editable " id="adjustmentMethod" rows="2" name="adjustmentMethod" data-parsley-maxlength="200" ></textarea>
			        </div>
			    </div>
			    <!-- <div class="form-group col-md-12">
		            <label class="control-label" for="remark">备注</label>
		            <div>
		                <textarea class="form-control field-editable" name ="remark" id="remark" rows="3" ></textarea>
		            </div>
	            </div> -->
			</form>
    	</div>
    	<!-- 施工合同 end -->
        <!-- 分包协议 -->
        <div class="form-group col-md-6 ">
	        <h3>分包协议</h3>
        	<form class="form-horizontal" id="subContractDetailform" data-parsley-validate="true" action="">
				<div class="form-group col-md-6 ">
					<label class="control-label" for="scNo">协议编号</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="scNo" value="" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="control-label" for="managementOffice">甲方名称</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"name="deptName"value="" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="projCompDirector">甲方委托代表</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="projCompDirector" value="" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="projCompPm">甲方项目经理</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="projCompPm" value="" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="gasComLegalRepresent">甲方代表</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="gasComLegalRepresent" value="" />
					</div>
				</div>
				<div class="form-group col-md-6 ">
					<label class="control-label" for="gasComPhone">联系方式</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="gasComPhone" value="" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="control-label" for="cuName">分包单位</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="cuName"value="" /> 
					</div>
				</div>
				<div class="form-group col-md-6 ">
					<label class="control-label" for="cuLegalRepresent">乙方法定代表</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="cuLegalRepresent"value="" />
					</div>
				</div>
				<div class="form-group col-md-6 ">
					<label class="control-label" for="cuPm">乙方项目经理</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="cuPm" value="" />
					</div>
				</div>
				<div class="form-group col-md-6 ">
					<label class="control-label" for="cuDirector">乙方委托代表</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="cuDirector" value="" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="cuPmPhone">联系方式</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"name="cuPmPhone" value="" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="certificationName">资格证名称</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable"
							id="certificationName" name="certificationName"
							data-parsley-maxlength="100" value="" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="certificationNo">资格证编号</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="certificationNo" value="" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="control-label" for="scCope">承包范围</label>
					<div>
						<textarea class="form-control field-editable" name ="scScope" id="scScope" rows="3" ></textarea>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="scType">承包方式</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="scType"value="" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="scPlannedTotalDays">计划天数</label>
					<div>
						<input type="text" class=" form-control input-sm field-not-editable" name="scPlannedTotalDays" value="">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="scPlannedStartDate">开工日期</label>
					<div>
						<input type="text"
							class=" form-control input-sm field-not-editable datepicker-default" name="scPlannedStartDate" value="">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="scPlannedEndDate">竣工日期</label>
					<div>
						<input type="text"
							class=" form-control input-sm field-not-editable datepicker-default" name="scPlannedEndDate" value="">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="qualityStandar">质量标准</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="qualityStandar" value="" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="scAmount">合同价款</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable fixed-number" name="scAmount" value=""/>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="scSignDate">签订日期</label>
					<div>
						<input type="text"
							class=" form-control input-sm field-not-editable" name="scSignDate" value="">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="conAgent">经办人</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" name="conAgent" value="" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="control-label" for="remark">备注</label>
					<div>
						<textarea class="form-control field-not-editable" name="remark" rows="3"></textarea>
					</div>
				</div>
			</form>
        </div>
        <h3>收付流水</h3>
        <table class="table table-hover table-striped table-bordered nowrap" width="100%" id="accrualsTable" >
       			<thead>
       			<tr>
       			    <th></th>
       			    <th>客户名称</th>
       				<th>操作时间</th>
        			<th>款项类型</th>
        			<th>金额</th>
        			<th>收付标志</th>
        			<th>操作人</th>
        			<th>状态</th>
       			</tr>
       			</thead>
       	</table>
    	<!-- 分包协议 end -->
    </div>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    
    //隐藏loading
    $(".infodetails").hideMask();
    
    //表单样式适应
    $("#detail_contractform,#subContractDetailform").toggleEditState().styleFit();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    var m = "id="+$("#projId").val();
    $.ajax({
        type: 'POST',
        url: '/projectView/projectDetailContract',
        data: m,
        dataType: 'json',
        success: function (data) {
            //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
            var selects = $("#detail_contractform").find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
            selects.removeAttr("disabled");
            //表单反序列化填充值
            $("#detail_contractform").deserialize(data);
            $("#detail_contractform").initFixedNumber();
            //有disabled属性的下拉菜单元素对象重新添加禁用属性
            selects.attr("disabled","disabled");
            $("#detail_contractform").fadeIn(200);
            $('html, body').animate({
                scrollTop: $("body").offset().top
            }, 250);
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	printXHRError("getDetail", "施工合同详情查询失败", jqXHR, textStatus, errorThrown);
        }
    });
    $.ajax({
        type: 'POST',
        url: '/projectView/projectDetailSubContract',
        data: m,
        dataType: 'json',
        success: function (data) {
            //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
            var selects = $("#subContractDetailform").find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
            selects.removeAttr("disabled");
            //表单反序列化填充值
            $("#subContractDetailform").deserialize(data);
            $("#subContractDetailform").initFixedNumber();
            //有disabled属性的下拉菜单元素对象重新添加禁用属性
            selects.attr("disabled","disabled");
            $("#subContractDetailform").fadeIn(200);
            $('html, body').animate({
                scrollTop: $("body").offset().top
            }, 250);
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	printXHRError("getDetail", "分包协议详情查询失败", jqXHR, textStatus, errorThrown);
        }
    });
  //初始化收费信息(应付流水)
    var accrualsData = {};
    var chargedatainit= function() {
    	"use strict";
        if ($('#accrualsTable').length !== 0) {
        	var projId = $("#projId").val();
        	if(projId === ""){
        		projId="-1";
        	}
        	accrualsData.projId = projId;
        	$('#accrualsTable').on( 'init.dt',function(){
       		//默认选中第一行
      		$(this).bindDTSelected(trAccrualsBack,false);
        	$('#accrualsTable').hideMask();
            }).DataTable({
            	language: {
                    url: 'js/dt-chinese.json'
                },
               lengthMenu: [5],
               dom: 'rt',
               buttons: [
               ],
              //启用服务端模式，后台进行分段查询、排序
    		   serverSide:true,
    		   select: true,  //支持多选
    		   ordering:false,
    	       ajax: {
    	             url: '/charge/queryAllAccruRecord',
    	             type:'post',
    	             data: function(d){
    	                   $.each(accrualsData,function(i,k){
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
               columns: [
                    {"data":"arId",className:"none never"},
                    {"data":"projCustName"},
    	  			{"data":"arDate",render:changeDate},
    	  			{"data":"arTypeDes"},
    	  			{"data":"arCost",className:"text-right"},
    	  			{"data":"arFlagDes"},
    	  			{"data":"staff.staffName"},
    	  			{"data":"arStatusDes"}
    			],
    			columnDefs: [{
    				"targets": 0,
    			    "visible":false
    			},
    			{
    				"targets": 4,
    				 render: function ( data, type, row, meta ) {
    					if(type === "display"){
    						if(data === null){
    							data = "";
    						}else if(data !==null && data!==""){
    							data = data.toFixed(2);
    						}
    					}
    					return data;
    				}
    			}]
           });
       }
    };
    chargedatainit();
    //时间格式转换
    function changeDate(e){
    	return format(e,"all");
    }
    var trAccrualsBack = function(){
    	
    }
</script>
<!-- ================== END PAGE LEVEL JS ================== -->