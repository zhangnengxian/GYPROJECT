<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editBtn hidden">
		<button type="button" onclick="saveClick()" class="btn btn-success btn-sm">保存</button>
		<button type="button" onclick="cancelClick()" class="btn btn-danger btn-sm">取消</button>
	</div>

	<div class="clearboth form-box">

		<form id="bankAccountForm" class="form-horizontal"  data-parsley-validate="true" action="">
			<input type="hidden" id="abId" name="abId" value=""/>

			<div class="form-group col-md-12 col-sm-12">
				<label class="control-label" for="corpId">公司名称：</label>
				<div>
					<select id="corpId" name="corpId" class="form-control input-sm field-editable " data-parsley-required="true">
						<c:forEach var="department" items="${departmentList}">
							<option value="${department.deptId}" >${department.deptName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-sm-12">
				<label class="control-label">开户名称：</label>
				<div>
					<input type="text"  name="bankName" class="form-control input-sm field-editable notBank" data-parsley-required="true"/>
				</div>
			</div>

			<div class="form-group  col-sm-12">
				<label class="control-label">银行账号：</label>
				<div>
					<input type="text"  name="bankNo" class="form-control input-sm field-editable notBank" data-parsley-required="true"/>
				</div>
			</div>

			<div class="form-group col-sm-12">
				<label class="control-label">开户行：</label>
				<div>
					<input type="text"  name="bankAdress" class="form-control input-sm field-editable notBank" data-parsley-required="true" />
				</div>
			</div>

			<div class="form-group col-sm-12">
				<label class="control-label">行&nbsp;&nbsp;号：</label>
				<div>
					<input type="text"  name="bankNumber" class="form-control input-sm field-editable "/>
				</div>
			</div>

			<div style="height: 200px"></div>
			<div class="form-group col-md-6 col-sm-12"></div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label">登记日期：</label>
				<div>
					<input type="text"  name="bankDate" readonly="readonly" class="form-control input-sm datepicker-default" />
				</div>
			</div>

		</form>

	</div>
</div>


<script>

    App.restartGlobalFunction();
    $(".infodetails").hideMask();//隐藏loading
    $("#bankAccountForm").toggleEditState(false).styleFit();
    $('.datepicker-default').datepicker({ todayHighlight: true});//日期控件
    trSData.t && trSData.t.cgetDetail('bankAccountForm','bankAccount/viewBankAccountDetail','',detailCallback);//第一次加载显示详细





	function saveClick() {
	   if (verifyEmpty()){ return false;}
		$(".infodetails").hideMask("正在保存...");
		var formData=$("#bankAccountForm").serializeJson();

		Base.subimt('bankAccount/saveOrUpdateBankAccount','POST',JSON.stringify(formData),function (data) {
			var content =data?"保存成功！":"保存失败！";tips(content);
			$("#bankAccountTable").cgetData(true);//列表重新加载
		})

	}







	function tips(content) {
		var myoptions = {
			title: "提示信息",
			content: content,
			accept: popClose,
			chide: true,
			icon: "fa-check-circle"
		};
		$("body").cgetPopup(myoptions);
	}




	var detailCallback=function (data) {};


    function cancelClick() {
        $("#bankAccountForm").toggleEditState(false);
        $(".editbtn").addClass("hidden");
        $('#bankAccountTable').cgetData(true);
    }




    var verifyEmpty =function() {
        var countBlank=0;
        $(".notBank").each(function () {
            if($(this).val()==""){
                $(this).focus();
                $(this).css("border-color","red");
                countBank++;
            }
        });
        return countBlank>0?true:false;
    };

</script>
