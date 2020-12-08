<!-- subContractLiuboRight.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="constructionUnitDetailform" data-parsley-validate="true" action="">
       		<input type="hidden" id="cuId" name="cuId"/>
       		<div class="form-group col-md-12 ">
		    	<label class="control-label" for="cuName">单位名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="cuName" name="cuName"  data-parsley-required="true" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="shortName">简称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="shortName" name="shortName" data-parsley-maxlength="100"  data-parsley-required="true" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cuDirector">负责人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuDirector" name="cuDirector" data-parsley-maxlength="100"  data-parsley-required="true" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cuPhone">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuPhone" name="cuPhone" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuMobile">手机</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuMobile" name="cuMobile" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cuFoundDate">成立日期</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable datepicker-default"  id="cuFoundDate" name="cuFoundDate" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    
		    
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cuTotalNum">总人数</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuTotalNum" name="cuTotalNum" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="cuQualification">资质</label>
		        <div>
		        	<textarea name="cuQualification" id="cuQualification" cols="120"></textarea>
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
    $('.infodetails').hideMask();
    $('.editbtn').addClass('hidden');
    //表单样式适应
    $('#constructionUnitDetailform').toggleEditState().styleFit();
    
  	//查右侧工程详述
    trSData.t.cgetDetail('constructionUnitDetailform','constructionUnit/viewConstructionUnit',''); 
    
	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    /**点击右侧维护区 保存 按钮时*/
    $('.saveBtn').on('click',function(){
        if($('#constructionUnitDetailform').parsley().isValid()){
        	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
        	var data=$('#constructionUnitDetailform').serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'constructionUnit/saveConstructionUnit',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "分包单位已存在，请修改！";
                	}else if(data === "true"){
                		$("#constructionUnitDetailform input").val('');
                		$("#constructionUnitDetailform textarea").val('');
                		$("#constructionUnitTable").cgetData();  //列表重新加载
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
                    console.warn("分包单位信息保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#constructionUnitDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#constructionUnitDetailform").parsley().validate();
        }
        
    }); 
    
    //放弃
    $(".cancelBtn").on("click",function(){
    	$("#constructionUnitDetailform input").val('');
    	trSData.t.cgetDetail('constructionUnitDetailform','constructionUnit/viewConstructionUnit',''); 
    	$("#constructionUnitDetailform").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["constructionUnitTable"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#constructionUnitDetailform").parsley().reset();
    });
 	
    //选中分包单位
    $("#cuPop").off().on("click",function(){
		var url='#constructionUnit/viewPageConstructionUnit';
  	    $("body").cgetPopup({
  	    	title: '分包单位选择',
  		    content: url,
  		    accept: 'chooseBack'}
		);
  	});
    
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->