<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <!-- <div class="toolBtn f-r p-b-10 hidden editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 savePlanBtn" >保存</a>
    	 <a href="javascript:;" class="btn btn-info btn-sm m-l-5  entBtn" >推送</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div> -->
	<!-- <div class="toolBtn f-r p-b-10  informbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 hidden informSaveBtn" >确定</a>
	</div> -->
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="materialPlanForm" action="" data-parsley-validate="true">
    		<input type="hidden" id="projId" name="projId" />
    		<input type="hidden"id="mpId" name="mpId" />
    		<input type="hidden" id="flag" name="flag" />
    		<input type="hidden"  id="isFeedBack" name="isFeedBack" />
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50" />
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">工程名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" />
		        </div>
		    </div>
		   <div class="form-group col-sm-12">
		        <label class="control-label" for="">分包单位</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" data-parsley-maxlength="200" />
		        </div>
		    </div>
		   <div class="form-group col-sm-6">
		        <label class="control-label" for="cuLegalRepresent">项目经理</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="cuLegalRepresent" name="cuLegalRepresent" data-parsley-maxlength="200" />
		        </div>
		    </div>
		    <div class="form-group col-sm-6">
		        <label class="control-label" for="">甲方代表</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="builder" name="builder" data-parsley-maxlength="200" />
		        </div>
		    </div>
		    <div class="form-group col-sm-6  ">
		        <label class="control-label" for="feedBackDate">反馈日期</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable timestamp feedBackInputShow"  id="feedBackDate" name="feedBackDate" />
		        </div>
		    </div>
		    <div class="form-group col-sm-6  ">
		        <label class="control-label" for=" modifyReceiveDate">反馈领用日期</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable datepicker-default feedBackInputShow"  id="modifyReceiveDate" name="modifyReceiveDate" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-sm-6 feedBackShow ">
		        <label class="control-label" for="feedBacker">反馈人</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable feedBackInputShow"  id="feedBacker" name="feedBacker" data-parsley-maxlength="200" />
		        </div>
		    </div>
		    <input type="hidden" id="feedBackerId" name="feedBackerId" class="feedBackInputShow">
		    <div class="form-group col-md-12  ">
	            <label class="control-label" for="feedBackDes">反馈信息</label>
	            <div>
	                <textarea class="form-control field-editable feedBackInputShow" name ="feedBackDes" id="feedBackDes" rows="4" data-parsley-maxlength="1000" value='' data-parsley-required="true"></textarea>
	            </div>
            </div>
		</form>
	</div>
    <div class="clearboth form-box m-t-5">
		<form id="materialPlanDetailForm" data-parsley-validate="true">
			<table id="materialPlanDetailTable" class="table table-striped table-bordered nowrap" width="100%"">
	        	<thead>
		            <tr>
               		    <th width="50px">名称</th>
                		<th width="50px">规格</th>
		                <th width="50px">单位</th>
		                <th width="50px">设计总量</th>
		                <th width="50px">领用总量</th>
		                <!-- <th width="50px">计划总量</th> -->
		                <th width="50px">欠量</th>
		                <th width="50px">可领用量</th>
		                <th width="50px">合格证</th>
		                <th width="50px">到货时间</th>
		            </tr>
	           </thead>
			</table>
		</form>
	</div>
	<div class="">
    	<form method="post" action="materialPlan/exportExcel" id="exprotPlanDetailForm">
    		<input type="hidden" id="mpId1" name="mpId"/>
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
    //参数传false时，表单不可编辑
    $("#materialPlanForm,#materialPlanDetailForm").toggleEditState(false);
  	//表单样式适应
    $("#materialPlanForm").styleFit();
  	$(".editbtn").addClass("hidden")
    trSData.t && trSData.t.cgetDetail('materialPlanForm','materialFeedBack/viewMaterial','',materialPlanBack);
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
  	//初始化材料计划表格
    if(!trSData.materialPlanTable.t){
    	detailSearchData.projId="-1";
    	materialPlanDetailTableInit();
    }
    
  	
  	//保存材料计划申请
  	$(".savePlanBtn").off().on("click",function(){
  		var materialPlanDetailForm=$("#materialPlanDetailForm");
  		var materialPlanForm = $("#materialPlanForm");
  		var saveAble = true;
  		if ( materialPlanForm.parsley().isValid() && materialPlanDetailForm.parsley().isValid()) {
  			
  			$("#feedBacker").val("");
  			$("#feedBackerId").val("");
  			$("#feedBackDate").val("");
  			$("#modifyReceiveDate").val("");
  			$("#feedBackDes").val("");
  			$("#flag").val("0");
  			var formData = materialPlanForm.serializeJsonString();
  			var dataJson=JSON.parse(formData);
  			var t = $('#materialPlanDetailTable');
  			/* var data = t.getInputsData(
  					{"materialName": "materialName", 
  					"projId": "projId", 
  					"materialNo": "materialNo",
  					"materialId": "materialId",
  					"materialSpec": "materialSpec",
  					"materialUnit": "materialUnit", 
  					"materialPrice": "materialPrice",
					"materialNum":"materialNum",
					"oweNum":"oweNum",
					"planTotalNum":"planTotalNum",
					"planNum":"planNum",
					"getAnum":"getAnum"}); */
			var json=$("#materialPlanDetailTable").DataTable().rows().data();		
  			console.info("保存json---");
  			console.info(json);
  			var dataObj={};
  			mpList = [];
  			$.each(json, function(k,v){
  				mpList.push(v);
  			})
  			dataJson.mpList=mpList;
  			console.info("dataJson----");
	       	console.info(JSON.stringify(dataJson));
			$.each(json,function(){
				var getNumber,
					totalNumber,
					content;
				if(this.planTotalNum){
					getNumber=parseFloat(this.planNum)+parseFloat(this.planTotalNum);
				}else{
					getNumber=this.planNum;
				}
				
				if(this.materialUnit=="米"){
					totalNumber = this.materialNum*1.05;
					if(getNumber>totalNumber){
						content="领用材料已超设计总量5%";
						saveAble = false;
					}
				}else{
					totalNumber = this.materialNum;
					if(getNumber>totalNumber){
		                content="领用材料已超设计总量";
						saveAble = false;
					}
	           }
					if(!saveAble){
						var myoptions = {
		                       	title: "提示信息",
		                       	content: content,
		                       	accept: popClose,
		                       	chide: true,
		                       	icon: "fa-check-circle"
					}
						 $("body").cgetPopup(myoptions);
						return false;
				}
				
			});
			if(saveAble){
				//viewdata.mpList=data;
	  			
	  			$.ajax({
	                type: 'POST',
	                url: 'materialPlan/saveMaterialPlan',/* materialPlan/saveMaterialPlan */
	                contentType: "application/json;charset=UTF-8",
	                data: JSON.stringify(dataJson),
	                success: function (data) {
	                	var content = "数据保存成功！";
	                	if(data === "false"){
	                		content = "数据保存失败！";
	                	}else if(data === "true"){
	                		$(".editbtn").addClass("hidden");
	                		$("#materialPlanTable").cgetData(true,queryBack);  //列表重新加载
	                		rowData=[];
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
	                    console.warn("材料计划申请保存失败！");
	                }
	            });
			}
  		
  			$("#materialPlanDetailForm").parsley().reset();
  			$("#materialPlanForm").parsley().reset();
  		}else{
	       	 //如果未通过验证, 则加载验证UI
	       	materialPlanDetailForm.parsley().validate();
	       	materialPlanForm.parsley().validate();
	    }
  	})
   
  	//点击放弃
    $(".cancelBtn").off().on("click",function(){
    	$(".editbtn").addClass("hidden");
    	
    	$("#materialPlanTable").cgetData(true,queryBack);  //列表重新加载
    	rowData=[];
    	tableCallBack();
    })
    
    //推送
    $(".entBtn").off().on("click",function(){
    	var len =$("#materialPlanDetailTable").DataTable().rows().data().length;
    	
    	if(len>0){
	    	var materialPlanDetailForm=$("#materialPlanDetailForm");
	  		var materialPlanForm = $("#materialPlanForm");
	  		var saveAble = true;
	  		if ( materialPlanForm.parsley().isValid() && materialPlanDetailForm.parsley().isValid()) {
	  			$("#feedBacker").val("");
	  			$("#feedBackerId").val("");
	  			$("#feedBackDate").val("");
	  			$("#modifyReceiveDate").val("");
	  			$("#feedBackDes").val("");
	  			$("#flag").val("1");
	  			var formData = materialPlanForm.serializeJsonString();
	  			var dataJson=JSON.parse(formData);
	  			var t = $('#materialPlanDetailTable');
	  			/* var data = t.getInputsData(
	  					{"materialName": "materialName", 
	  					"projId": "projId", 
	  					"materialNo": "materialNo",
	  					"materialId": "materialId",
	  					"materialSpec": "materialSpec",
	  					"materialUnit": "materialUnit", 
	  					"materialPrice": "materialPrice",
						"materialNum":"materialNum",
						"oweNum":"oweNum",
						"planTotalNum":"planTotalNum",
						"planNum":"planNum",
						"getAnum":"getAnum"}); */
				var json=$("#materialPlanDetailTable").DataTable().rows().data();		
	  			console.info("保存json---");
	  			console.info(json);
	  			var dataObj={};
	  			mpList = [];
	  			$.each(json, function(k,v){
	  				mpList.push(v);
	  			})
	  			dataJson.mpList=mpList;
	  			console.info("dataJson----");
		       	console.info(JSON.stringify(dataJson));
	  			
				$.each(json,function(){
					var getNumber,
						totalNumber,
						content;
					if(this.planTotalNum){
						getNumber=parseFloat(this.planNum)+parseFloat(this.planTotalNum);
					}else{
						getNumber=this.planNum;
					}
					
					if(this.materialUnit=="米"){
						totalNumber = this.materialNum*1.05;
						if(getNumber>totalNumber){
							content="领用材料已超设计总量5%";
							saveAble = false;
						}
					}else{
						totalNumber = this.materialNum;
						if(getNumber>totalNumber){
			                content="领用材料已超设计总量";
							saveAble = false;
						}
		           }
						if(!saveAble){
							var myoptions = {
			                       	title: "提示信息",
			                       	content: content,
			                       	accept: popClose,
			                       	chide: true,
			                       	icon: "fa-check-circle"
						}
							 $("body").cgetPopup(myoptions);
							return false;
					}
					
				});
				if(saveAble){
					//viewdata.mpList=data;
		  			
		  			$.ajax({
		                type: 'POST',
		                url: 'materialPlan/saveMaterialPlan',
		                contentType: "application/json;charset=UTF-8",
		                data: JSON.stringify(dataJson),
		                success: function (data) {
		                	var content = "数据保存成功！";
		                	if(data === "false"){
		                		content = "数据保存失败！";
		                	}else if(data === "true"){
		                		$(".editbtn").addClass("hidden");
		                		$("#materialPlanTable").cgetData(true,queryBack);  //列表重新加载
		                		rowData=[];
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
		                    console.warn("材料计划申请保存失败！");
		                }
		            });
				}
	  		
	  			$("#materialPlanDetailForm").parsley().reset();
	  			$("#materialPlanForm").parsley().reset();
	  		}else{
		       	 //如果未通过验证, 则加载验证UI
		       	materialPlanDetailForm.parsley().validate();
		       	materialPlanForm.parsley().validate();
		    }
		}else{
    		alertInfo("请添加材料计划明细！");
    	}
    })
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->