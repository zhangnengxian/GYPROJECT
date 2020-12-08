<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">待设计出图工程列表</h4>
                </div>
                <div class="panel-body">
					<input type="hidden" id="waitHandleProjNo" name="projNo"  value="${projNo }"/>
					<input id="projStatus" type="hidden" value="${projStatus}"/>
					<input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
					<input type="hidden" id="stepId" name="stepId" value="${stepId }">
					<input type="hidden" id="curStepId" name="curStepId" value="${curStepId }">
					<input type="hidden" id="projId" name="projId" value="">
					<input type="hidden" id="designer" name="designer" value="">
					<input type="hidden" id="changeDesigner" name="changeDesigner" value="${changeDesigner}">
					<input type="hidden" id="conformDesigner" name="conformDesigner" value="${conformDesigner}">
					<input type="hidden" id="importDesigner" name="importDesigner" value="${importDesigner}">
					<input type="hidden" id="customerServiceCenter" name="customerServiceCenter" value="${customerServiceCenter}">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="designDrawingTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">          
                        <thead>
                            <tr>
                                <th>工程Id</th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>勘察日期</th>
                                <th>状态</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse tabs-mixin">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <ul class="nav nav-tabs">
			        	<li class="active"><a href="#default-tab-0" id="projectInfoTab"  data-toggle="tab">工程信息</a></li>
		                <li class=""><a href="#default-tab-1" id="drawingListTab"  data-toggle="tab">图纸目录</a></li>
		                <li class=""><a href="#default-tab-2" id="materailListTab"   data-toggle="tab">材料目录</a></li>        
	                </ul>
			    </div>
				<div class="panel-body" >
					<input type="hidden" id="projId1" name="projId"/>
					<input type="hidden" id="feedbackState" name="feedbackState"/>
					<div class="tab-content">
						<div class="tab-pane fade active in btn-top saveHiddenBox" id="default-tab-0">
                       		<div class="toolBtn f-r p-b-10  editbtn">
							    <div class="toolBtn f-r p-b-10 ">
							    	<a href="javascript:;" class="btn btn-info btn-sm m-l-5 saveBtn" >保存</a>
							    	<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
								</div>
							</div>
                       		<div class="clearboth form-box">
                       			<form class="form-horizontal" id="projectInfoForm" data-parsley-validate="true" action="">
                       				<input type="hidden" id="projId" name="projId" >
                       				<input type="hidden" id="deptDivide" name="deptDivide" >
                       				<input type="hidden" id="acquisitionPlDays" name="acquisitionPlDays" >
                       				<div class="form-group col-md-6 col-sm-12">
								    	<label class="control-label" for="projNo">工程编号</label>
								        <div>
								        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
								        </div>
								    </div>
								    <div class="form-group col-md-12  ">
								        <label class="control-label" for="projName">工程名称</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
								        </div>
								    </div>
								    <div class="form-group col-md-12  ">
								        <label class="control-label" for="projAddr">工程地点</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
								        </div>
								    </div>
								    <div class="form-group col-md-12">
							            <label class="control-label" for="projScaleDes">工程规模</label>
							            <div>
							                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200" value=''></textarea>
							            </div>
						            </div>
						            <div class="form-group col-md-6 col-sm-12">
								    	<label class="control-label" for="corpName">燃气公司</label>
								        <div>
								        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" />
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
								    <div class="form-group col-md-12 noUser">
								        <label class="control-label" for="duName">申报单位</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""/>
								        </div>
								    </div>
								    <div class="form-group col-md-6 noUser">
								        <label class="control-label" for="custContact">联系人</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" value=""/>
								        </div>
								    </div>
								    <div class="form-group col-md-6 noUser">
								        <label class="control-label" for="custPhone">联系电话</label>
								        <div>
								            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" value=""/>
								       
								        </div>
								    </div>
								    <div class="form-group col-md-6">
								        <label class="control-label" for="surveyer">勘察人</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable" id="surveyer"  name="surveyer"  value="">
								        </div>
								    </div>
								    <div class="form-group col-md-12">
								     	<label class="control-label " for="surveyDes">踏勘内容</label>
								     	<div> 
								        	<textarea class="form-control field-not-editable" name="surveyDes" id="surveyDes" rows="3" cols="" ></textarea></div>
								    </div>
<!-- 								    <div class="form-group col-md-12"> -->
<!-- 								     	<label class="control-label" for="connectGasDes">方案说明</label> -->
<!-- 								     	<div>  -->
<!-- 								        	<textarea class="form-control  field-not-editable" name="connectGasDes" id="connectGasDes" rows="3" cols="" ></textarea> -->
<!-- 							        	</div> -->
<!-- 								    </div> -->
								    <div class="form-group col-md-12">
								     	<label class="control-label" for="technicalSuggestion">技术建议</label>
								     	<div> 
								        	<textarea class="form-control  field-not-editable" name="technicalSuggestion" id="technicalSuggestion" rows="3" cols="" ></textarea>
							        	</div>
								    </div>
								    <div class="form-group col-md-6">
								        <label class="control-label" for="ocoDate">设计委托日期</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="ocoDate"  name="ocoDate"  value="">
								        </div>
								    </div>
								    <div class="form-group col-md-6">
								        <label class="control-label" for="designer">设计天数</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable" id="acquisitionDays"  name="acquisitionDays"  value="">
								        </div>
								    </div>
								    <div class="form-group col-md-6">
								        <label class="control-label" for="duCompleteDate">预计完成日期</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable datepicker-default " id="duCompleteDate"  name="duCompleteDate"  value="">
								        </div>
								    </div>
								    <div class="form-group col-md-6">
								        <label class="control-label" for="designer">设计人</label>
								        <div>
								           <input type="text" class=" form-control input-sm field-not-editable" id="designer"  name="designer"  value="">
								        </div>
								    </div>
								    <!-- 注释掉 -->
								   <!--  <div class="form-group col-lg-6 col-md-12 col-sm-6  designFee">
										<label class="control-label" for="">设计费</label>
										<div>
											<input type="text" class="form-control input-sm field-editable text-right fixed-number" name="designFee" data-parsley-required="true" data-parsley-type="number" data-parsley-maxlength="16"/>
										</div>
									</div> -->
								    <div class="form-group col-md-12">
								     	<label class="control-label" for="remark">备注</label>
								     	<div> 
								        	<textarea class="form-control  field-editable" name="remark" id="remark" rows="3" cols="" data-parsley-maxlength="500" ></textarea>
							        	</div>
								    </div>
                       			</form>							
                       		</div>	
                        </div>
						<div class="tab-pane fade in btn-top" id="default-tab-1">
                        	<div id="drawing_list_box">
                        	</div>
                        </div>
                        <div class="tab-pane fade in btn-top" id="default-tab-2">
                        	<div id="material_list_box">
                        		<table id="materialTable" class="table table-striped table-bordered nowrap" width="100%">
			                        <thead>
			                            <tr>
			                                <th>序号</th>
			                                <th>材料名称</th>
			                                <th>规格</th>
			                                <th>单位</th>
			                                <th>数量</th>
			                                <th>备注</th>
			                                <th>从物资购买</th>
			                            </tr>
			                        </thead>
                    			</table>
                        	</div>
                        </div>
					</div>
				</div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('设计出图 - 工程项目管理系统');
    
    $("#projectInfoForm").toggleEditState(false);
  	//表单样式适应
   	$("#projectInfoForm").styleFit();
    $(".editbtn").addClass('hidden')
    $('.backReason').addClass('hidden');
    $.getScript('projectjs/design/design-drawing.js?v='+Math.random()).done(function () {
        designDrawing.init();
	});
    $("#isBack").change(function(){
        if($(this).val()=="1"){
     		$('.backReason').removeClass('hidden');
        }else{
    		$('.backReason').addClass('hidden');
    		$("#backReason").val('');
        }
        $("#projectInfoForm").styleFit();
	});
    
    $(".saveBtn").off().on("click",function(){
    	if($("#projectInfoForm").parsley().isValid()){
	    	//是否导入图纸目录
    		var len = $('#drawingTable').DataTable().ajax.json().data ? $('#drawingTable').DataTable().ajax.json().data.length : $('#drawingTable').DataTable().ajax.json().length;
    		var length = $('#materialTable').DataTable().ajax.json().data ? $('#materialTable').DataTable().ajax.json().data.length : $('#materialTable').DataTable().ajax.json().length;
    		
    		/* if(len<1 ){
    			alertInfo('请导入图纸目录');
    		}else */ if(length<1){
    			alertInfo('请导入材料目录');
    		}else{
    			$("body").cgetPopup({
  		    	  title: "提示信息",
  		    	  content: "相应文件是否导入正确已核实？点击确定后推送！",
  		    	  accept: successBack1,
  		    	  icon: "fa-check-circle",
  		    	  newpop: 'new'
    			});
    		}
		}else{
        	//如果未通过验证, 则加载验证UI
        	$("#projectInfoForm").parsley().validate();
        }
    })
    
    function successBack1(){
	    //加遮罩
	    $(".saveHiddenBox").loadMask("正在保存...", 1, 0.5);	
		var data=$("#projectInfoForm").serializeJson();
		  $.ajax({
	          url: "designDrawing/updateState",
	          type: "POST",
	          contentType: "application/json;charset=UTF-8",
	         	// contentType: "application/json;charset=UTF-8",
	          data: JSON.stringify(data),
	          beforeSend: function () {
	              // 禁用按钮防止重复提交
	              $(".saveBtn").attr({ disabled: "disabled" });
	          },
	          success: function (data) {
	        	 //取消遮罩
	        	  $(".saveHiddenBox").hideMask();	
	        	  if (data === "true") {
	        		  $("body").cgetPopup({
	                  	  title: "提示信息",
	                  	  content: "数据保存成功!",
	                  	  accept: successBack,
	                  	  chide: true,
	                  	  icon: "fa-check-circle",
	                  	  newpop: 'new'
	                  }); 
	        	  }else{
	        		  $("body").cgetPopup({
	                  	  title: "提示信息",
	                  	  content: "数据保存失败, 请重试! <br>" + data,
	                  	  accept: popClose,
	                  	  chide: true,
	                  	  icon: "fa-exclamation-circle",
	                  	  newpop: 'new'
	                  });  
	        	  }
	    	  },
	          complete: function () {
	              $(".saveBtn").removeAttr("disabled");
	          },
	      });
		//如果通过验证, 则移除验证UI
	  	$("#projectInfoForm").parsley().reset();
	}
    
    $(".cancelBtn").off().on("click",function(){
    	$(".editbtn").addClass("hidden");
		$("#projectInfoForm").toggleEditState(false);
		$('#designDrawingTable').cgetData(true,reback);
    })
    
 
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->