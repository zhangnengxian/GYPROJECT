<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
		<div class="toolBtn f-r p-b-10  editBtn hidden">
			<button type="button" onclick="saveClick()" class="btn btn-success btn-sm">保存</button>
			<button type="button" onclick="cancelClick()" class="btn btn-danger btn-sm">取消</button>
		</div>
	</div>
    <div class="clearboth form-box">
		<input type="hidden" id="loginName" value="${loginName}" placeholder="登录人">
		<input type="hidden" id="sysDate" value="${sysDate}" placeholder="系统时间">

		<form class="form-horizontal" id="supplementForm"  data-parsley-validate="true" action="">
			<input type="hidden" name="isId" placeholder="协议ID">
			<input type="hidden" id="projId" name="projId" placeholder="工程ID">
			<input type="hidden" id="projNo" name="projNo" placeholder="工程编号">
			<input type="hidden" name="imcId" placeholder="智能表合同ID">
			<input type="hidden" id="flag" name="flag" value="1" placeholder="修改"/>
			<div class="form-group  col-md-6 ">
				<label class="control-label" for="imcNo">合同编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="imcNo" name="imcNo" value=""/>
				</div>
			</div>
			<div class="form-group  col-md-6 ">
				<label class="control-label" for="isNo">协议编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="isNo" name="isNo"/>
				</div>
			</div>
			<div class="form-group col-md-12 ">
				<label class="control-label" for="projName">工程名称</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="projAddr">工程地点</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="projectTypeDes">工程类型</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="contributionModeDes">出资方式</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="deptName">业务部门</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value=""/>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="control-label" for="projScaleDes">工程规模</label>
				<div>
					<textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2"></textarea>
				</div>
			</div>

			<div class="form-group col-md-12 clearboth ">
				<label class="control-label" for="corpName">燃气经营企业</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"  id="corpName" name="corpName" data-parsley-maxlength="50" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="gasCompPhone">联系方式</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"  id="gasCompPhone" name="gasCompPhone" data-parsley-maxlength="20" value=""/>
				</div>
			</div>

			<div class="form-group col-md-12">
				<label class="control-label" for="custName">燃气用户</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"  id="custName" name="custName" data-parsley-maxlength="200" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6 ">
				<label class="control-label" for="custPhone">联系方式</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" value=""/>
				</div>
			</div>

			<div class="form-group col-md-6 resident">
				<label class="control-label" for="houseAddr">地址</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"  id="houseAddr" name="houseAddr" data-parsley-maxlength="200" value=""/>
				</div>
			</div>

			<div class="form-group col-md-6">
				<label class="control-label" for="isAmount">协议价款</label>
				<div>
					<input type="text" id="isAmount"  name="isAmount" onchange="changeAmount(this.value)" data-parsley-required="true" class=" form-control input-sm field-editable fixed-number text-right"  data-parsley-maxlength="13" value="">
				</div>
			</div>

			<div class="form-group col-md-6 col-sm-12 col-sm-6  clearboth">
				<label class="control-label" for="payType">付款方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="payType"  name="payType" onchange="selectedPayType(this.value)" data-parsley-required="true">
						<option >--请选择付款方式--</option>
						<option value="1">一次付清</option>
						<option value="2">两次付清</option>
						<option value="3">三次付清</option>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6  paymentRatio1  clearboth hidden">
				<label class="control-label" for="paymentRatio1">首付比列</label>
				<div>
					<input type="text" class=" form-control input-sm  fixed-number text-right" id="paymentRatio1"  readonly="readonly" name="paymentRatio1" data-parsley-type="number"
						   data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例" onblur="loseBlur(this.value,this.id)"/>
					<a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
				</div>
			</div>
			<div class="form-group col-md-6 firstPayment hidden">
				<label class="control-label" for="firstPayment">首付款</label>
				<div>

					<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="firstPayment"  name="firstPayment" data-parsley-type="number"
						   data-parsley-maxlength="16" value="" data-parsley-required="true" size="10" />
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6 paymentRatio2 hidden">
				<label class="control-label" for="paymentRatio2 ">阶段款比列</label>
				<div>
					<input type="text" class=" form-control input-sm  fixed-number text-right" id="paymentRatio2" readonly="readonly" name="paymentRatio2" data-parsley-type="number"
						   data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例"  onblur="loseBlur(this.value,this.id)"/>
					<a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
				</div>
			</div>
			<div class="form-group col-md-6 secondPayment hidden ">
				<label class="control-label" for="secondPayment">阶段款</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right"  id="secondPayment"  name="secondPayment" data-parsley-type="number" data-parsley-maxlength="16" value="" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6 paymentRatio3 hidden">
				<label class="control-label" for="paymentRatio3">阶段款比列</label>
				<div>
					<input type="text" class=" form-control input-sm fixed-number text-right" id="paymentRatio3" readonly="readonly" name="paymentRatio3" data-parsley-type="number"
						   data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例"  onblur="loseBlur(this.value,this.id)">
					<a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
				</div>
			</div>
			<div class="form-group col-md-6 thirdPayment hidden">
				<label class="control-label" for="thirdPayment">阶段款</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="thirdPayment"   name="thirdPayment" data-parsley-type="number" data-parsley-maxlength="16" value="" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>

			<div class="form-group col-md-6">
				<label class="control-label" for="invoiceType">发票类型</label>
				<div>
					<select class=" form-control input-sm field-editable fixed-number text-right" id="invoiceType"  name="invoiceType" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-required="true">
						<option>请选择</option>
						<option value="1">增值税专用发票</option>
						<option value="2">增值税普通发票</option>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="increment">税率</label>
				<div>
					<select class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-required="true">
						<option></option>
						<c:forEach var="enums" items="${increment }">
							<option value="${ enums.increment}">${ enums.increment}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group col-md-12 col-sm-12">
				<label class="control-label">备注：</label>
				<div>
					<textarea id="remark" name="remark"  class="form-control field-editable" rows="4"></textarea>
				</div>
			</div>


			<div class="form-group col-md-6">
				<label class="control-label" for="agent">经办人</label>
				<div>
					<input type="text" id="agent" name="agent"  class="form-control input-sm  field-not-editable" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" >签订日期</label>
				<div>
					<input type="text" id="signDate" name="signDate"  class="form-control input-sm datepicker-default field-not-editable" />
				</div>
			</div>
		</form>

    </div>
</div>
<div class="clearboth p-t-15">
	<div >
		<h4 class="m-t-15 m-l-7"><strong>审批历史</strong></h4>
	</div>
	<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
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




<script>
    App.restartGlobalFunction();
    $(".infodetails").hideMask();
    $("#supplementForm").toggleEditState().styleFit();
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    var histSearchData={};
	$(function () {

    })

    trSData.t&&trSData.t.cgetDetail('supplementForm','intelligentSupplement/viewSupplementDetail','',supplementDetailCallback);

    function saveClick() {
        var pf = $("#supplementForm");
        if (pf.parsley().isValid()) { //验证必填是否已填写
            $("#supplementForm").loadMask("保存中···", 1, 0.5);
            var formData = $("#supplementForm").serializeJson();
            	formData.isStatus=1; //待推送
            Base.subimt('intelligentSupplement/saveIntelligentSupplement', 'POST', JSON .stringify(formData), function(data) {

                var content = data ? "保存成功！" : "保存失败！";
                tips(content);
                $("#supplementForm").hideMask();
                $("#supplementTable").cgetData(true);//列表重新加载
            })
        } else {
            pf.parsley().validate();
        }
    }




	/**选付款方式**/
	function selectedPayType(value) {
        $("#paymentRatio1").val('');
        $("#firstPayment").val('');
        $("#paymentRatio3").val('');
        $("#thirdPayment").val('');
        $("#paymentRatio2").val('');
        $("#secondPayment").val('');
        $("#paymentRatio1").attr("readonly",true);
        $("#paymentRatio2").attr("readonly",true);
        $("#paymentRatio3").attr("readonly",true);

        detailCallbackPayType(value);

	    if (value==1){//一次付清
            $("#paymentRatio1").val('100');   //首付比例直接为100%
            $("#firstPayment").val($("#isAmount").val());  //金额为全款，即是合同总金额

		}else if (value==2){//两次付清
            $("#paymentRatio1").attr("readonly",false);

		}else if (value==3){//三次付清
            $("#paymentRatio1").attr("readonly",false);
            $("#paymentRatio2").attr("readonly",false);
		}
	}


    /**选付款方式**/
    function detailCallbackPayType(value) {
        if (value==1){//一次付清
            $(".firstPayment").removeClass("hidden");//首付款比列
            $(".paymentRatio1").removeClass("hidden");//首付款

            $(".paymentRatio2").addClass("hidden");//阶段款比列
            $(".secondPayment").addClass("hidden");//阶段款
            $(".paymentRatio3").addClass("hidden");//阶段款比列
            $(".thirdPayment").addClass("hidden");//阶段款


        }else if (value==2){//两次付清
            $(".firstPayment").removeClass("hidden");//首付款比列
            $(".paymentRatio1").removeClass("hidden");//首付款
            $(".paymentRatio2").removeClass("hidden");//阶段款比列
            $(".secondPayment").removeClass("hidden");//阶段款

            $(".paymentRatio3").addClass("hidden");//阶段款比列
            $(".thirdPayment").addClass("hidden");//阶段款

        }else if (value==3){//三次付清
            $(".firstPayment").removeClass("hidden");//首付款比列
            $(".paymentRatio1").removeClass("hidden");//首付款
            $(".paymentRatio2").removeClass("hidden");//阶段款比列
            $(".secondPayment").removeClass("hidden");//阶段款
            $(".paymentRatio3").removeClass("hidden");//阶段款比列
            $(".thirdPayment").removeClass("hidden");//阶段款
        }
    }




    var loseBlur=function(value,id){
        var reg=/^[0-9]+\.[0]+$/; //正则表达式,验证是否是整数
        if((new Number(value))>100 || !reg.test(value)){  //判断是是否在1到100之间且为整数
            $("body").cgetPopup({
                title: '提示',
                content: '请输入1到100之间的整数!',
                ahide:true,
                atext:'确定'
            });
            $("#"+id).val("");
            return false;  //终止程序
        }
        calculationFn();  //调用函数计算付款比例和所付金额
    }


    function changeAmount(value) {
        calculationFn();
    }

    var  calculationFn=function(){
        var totaAmount = $("#isAmount").val();  //协议价款
        var payNumber = $("#payType").val();//付款方式


        if(payNumber=='1'){  //一次付清
            $("#firstPayment").val(totaAmount);  //金额为全款，即是合同总金额

        }else if(payNumber=='2'){  //分两期付款
            var paymentRatio1=new Number($("#paymentRatio1").val());  //得到首付比列
            if(paymentRatio1>0){
                $("#paymentRatio1").val(paymentRatio1);    //首付比列
                $("#firstPayment").val((totaAmount*(paymentRatio1/100.00)).toFixed(2));  //根据首付比列计算首付多少钱

                $("#paymentRatio2").val((100-paymentRatio1));    //计算第二次付款的比列
                $("#secondPayment").val((((100.00-paymentRatio1)/100.00)*totaAmount).toFixed(2));  //计算第二期付款多少钱
            }


        }else if(payNumber=='3'){ //分三期付清
            var paymentRatio1=new Number($("#paymentRatio1").val());  //得到首付比列
            var paymentRatio2=new Number($("#paymentRatio2").val());  //得到二期付款比例
            if(paymentRatio1>0){
                $("#firstPayment").val(totaAmount*(paymentRatio1/100.00));  //根据首付比列计算首付多少钱
            }
            if(paymentRatio1>0 && paymentRatio2>0){
                if((paymentRatio1+paymentRatio2)<=100.00){  //判断前两期首付比例是否小于或者等于100
                    $("#paymentRatio1").val(paymentRatio1);    //首付比列
                    $("#firstPayment").val((totaAmount*(paymentRatio1/100.00)).toFixed(2));  //根据首付比列计算首付多少钱

                    $("#paymentRatio2").val(paymentRatio2);    //二次付款的比列
                    $("#secondPayment").val((totaAmount*(paymentRatio2/100.00)).toFixed(2));  //计算第二期付款多少钱

                    $("#paymentRatio3").val((100-paymentRatio1-paymentRatio2));    //计算第三次付款的比列
                    $("#thirdPayment").val(((100.00-paymentRatio1-paymentRatio2)/100.00*totaAmount).toFixed(2));  //计算第三期付多少钱
                }else{
                    $("body").cgetPopup({
                        title: '提示',
                        content: '总的付款比例不得超过100%!',
                        ahide:true,
                        atext:'确定'
                    });
                    return false; //终止程序
                }
            }
        }
    }



    var handleIsAuditHistory = function(data) {
        histSearchData.businessOrderId=data.isId;
        $('#auditHistoryTable').on( 'init.dt',function(){
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



    function cancelClick() {
        $("#supplementForm").toggleEditState(false);
        $(".editbtn").addClass("hidden");
        $('#supplementTable').cgetData(true);
    }






    function tips(content) {
        var myoptions = {
            title : "提示信息",
            content : content,
            accept : popClose,
            chide : true,
            icon : "fa-check-circle"
        };
        $("body").cgetPopup(myoptions);
    }



</script>
