<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10 hidden editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 savePlanBtn" >保存</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div> 
	<!-- <div class="toolBtn f-r p-b-10  informbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 hidden informSaveBtn" >确定</a>
	</div> -->
    <div class="clearboth form-box">
		<input id="createrName" class="hidden" value="${loginInfo.staffName}">
    	<form class="form-horizontal" id="gasPlanForm" action="" data-parsley-validate="true">
			<input class="hidden" name="gpId" id="gpId">
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="gpNo">计划编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="gpNo" name="gpNo"  data-parsley-maxlength="50" value="" data-parsley-required="true"/>
		        </div>
		    </div>
			<div class="form-group col-md-12">
				<label class="control-label" for="gpName">计划名称</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"  id="gpName" name="gpName"  data-parsley-maxlength="50" value=""/>
				</div>
			</div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="creater">编制人</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="creater" name="creater"  data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label">编制日期</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable datepicker-default"  id="createDate" name="createDate" value="" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
	            <label class="control-label" for="remark">备注</label>
	            <div>
	                <textarea class="form-control field-editable feedBackInputShow" name ="remark" id="remark" rows="4" data-parsley-maxlength="1000" data-parsley-required="true"></textarea>
	            </div>
            </div>
		</form>
	</div>
	<h4><i class="fa fa-arrow-circle-o-right"></i> 工程列表</h4>
    <div class="">
		<table id="gasProjectTable" class="table table-striped table-bordered nowrap" width="100%">
			<thead>
				<tr>
					<th></th>
					<th></th>
					<th width="50px">类型</th>
					<th width="50px">工程名称</th>
					<th width="50px">工程编号</th>
					<th width="50px">单位名称</th>
					<th width="50px">安装地址</th>
					<th width="50px">现场代表</th>
					<th width="50px">施工单位</th>
					<th width="50px">施工合同号</th>
					<th width="50px"></th>
					<%--<th width="50px">带气方式</th>--%>
					<%--<th width="50px">压力等级</th>--%>
					<%--<th width="50px">开通内容</th>--%>
					<%--<th width="50px">验收情况</th>--%>
				</tr>
		   </thead>
		</table>
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

    var len = $('#gasPlanTable').DataTable().data().length;
    if(len<1){
		//初始化带气工程表
		if($.fn.DataTable.isDataTable('#gasProjectTable')){
			$("#gasProjectTable").cgetData(false);//列表重新加载
		}else{
			handleProjectTable();
		};
	}

    trSData.t && trSData.t.cgetDetail('gasPlanForm','','',gasPlanBack);
    //参数传false时，表单不可编辑
    $("#gasPlanForm").toggleEditState(false);
  	//表单样式适应
    $("#gasPlanForm").styleFit();
  	$(".editbtn").addClass("hidden")

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    /**点击右侧维护区 保存 按钮时*/
    $('.savePlanBtn').on('click',function(){
        if($('#gasPlanForm').parsley().isValid()){
            var projectData = $('#gasProjectTable').DataTable().rows().data(),
                gasPlan=$('#gasPlanForm').serializeJson();
            //table数据转成list
			var data = {};
            data.gasPlan = gasPlan;
            data.gasProjects = [];
            $.each(projectData, function(k,v){
                data.gasProjects.push(v);
            })

            //增加的记录
            var data=JSON.stringify(data);

            //加遮罩
            $(".infodetails").loadMask("正在保存...", 1, 0.5);
            $.ajax({
                type: 'POST',
                url: 'gasPlan/saveGasPlan',
                contentType: "application/json;charset=UTF-8",
                data: data,
                success: function (data) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                    var content = "数据保存成功！";
                    if(data === "false"){
                        content = "数据保存失败！";
                        alertInfo(content);
                    }else if(data ===  "exist"){
                        content = "计划编号已存在，请修改！";
                        alertInfo(content);
                    }else if(data === "true"){
                        $(".addProjBtn,.delProjBtn").addClass("hidden");
                        $("#gasPlanForm").formReset();//表单重置
                        $("#gasPlanTable").cgetData(true);  //列表重新加载
                        alertInfo(content);
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                    console.warn("计划保存失败！");
                }
            });
            //如果通过验证, 则移除验证UI
            $("#gasPlanForm").parsley().reset();
        }else{
            //如果未通过验证, 则加载验证UI
            $("#gasPlanForm").parsley().validate();
        }

    });

    //点击放弃
    $(".cancelBtn").off().on("click",function(){
    	$(".editbtn").addClass("hidden");
    	$("#gasPlanForm").toggleEditState(false);
    	
		if($.fn.DataTable.isDataTable('#gasPlanTable')){
			$("#gasPlanTable").cgetData(false);//列表重新加载
		}else{
            handleGasPlan();
		}
    })
 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->