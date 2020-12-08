<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="supervisionUnitDetailform" action="">
		    <input type="hidden" name="suId" id="suId"/>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="">监理单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="suName" name="suName" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">简称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="shortName" name="shortName" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">负责人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="suDirector" name="suDirector" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="suPhone" name="suPhone" />
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">手机</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="suMobile" name="suMobile" data-parsley-length=11/>
		        </div>
		    </div>
		</form>
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
    $("#supervisionUnitDetailform").toggleEditState().styleFit();
  //按钮隐藏
    $(".editbtn").addClass("hidden");
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
  	//查右侧工程详述
    trSData.t.cgetDetail('supervisionUnitDetailform','supervisionUnit/viewSupervision',''); 
    
    //点击保存
    $(".saveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	
        if($("#supervisionUnitDetailform").parsley().isValid()){
        	var data=$("#supervisionUnitDetailform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'supervisionUnit/saveSupervision',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "监理单位已存在，请修改！";
                	}else if(data === "true"){
                		$("#supervisionUnitDetailform input").val('');
                		$("#supervisionUnitTable").cgetData();  //列表重新加载
                	}
                	var myoptions = {
                        	title: "提示信息",
                        	content: content,
                        	accept: popClose,
                        	chide: true,
                        	icon: "fa-check-circle"
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("计划签订区记录保存失败！");
                }
            });
        	$("#supervisionUnitDetailform").parsley().reset();
        	//如果通过验证, 则移除验证UI
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#supervisionUnitDetailform").parsley().validate();
        }
        
    }); 
    
    //放弃 按钮点击后
    $(".cancelBtn").on("click",function(){
    	 //清空表单
    	 $("#supervisionUnitDetailform input").val('');
    	 trSData.t.cgetDetail('supervisionUnitDetailform','supervisionUnit/viewSupervision',''); 
    	 $("#supervisionUnitDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["supervisionUnitTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#supervisionUnitDetailform").parsley().reset();
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->