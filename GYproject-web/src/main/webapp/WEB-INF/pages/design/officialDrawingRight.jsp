<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  toolBtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 confirmBtn" >保存</a>
		<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="officeDrawingForm" data-parsley-validate="true" action="">
			<input type="hidden" id="projId" name="projId" />
			<input type="hidden"  id="diId" name="diId" />
			<input type="hidden"  id="corpId" name="corpId" />
			<input type="hidden"  id="tenantId" name="tenantId" />
			<input type="hidden" id="orgId" name="orgId" />
			<input type="hidden" id="duId" name="duId" />
			<input type="hidden" id="duName" name="duName" />
			<input type="hidden" id="designerId" name="designerId" />
			<input type="hidden" id="ocoDate" name="ocoDate" />
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  />
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">工程名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" />
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr"  />
		        </div>
		    </div>
		    <div class="form-group col-lg-12 col-md-12 col-sm-6 ">
				<label class="control-label" for="">工程规模</label>
				<div>
					<textarea class="form-control field-not-editable" name="projScaleDes" rows="4" cols="" ></textarea>
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
		    
		    <div class="form-group col-sm-12 noUser">
		        <label class="control-label" for="">申报单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 noUser">
		        <label class="control-label" for="">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="200"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 noUser">
		        <label class="control-label" for="">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" />
		        </div>
		    </div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">设计人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" name="designer"/>
				</div>
			</div>
			<div class="form-group col-md-12">
      			<label class="control-label" for="">图纸签收</label>
      			<div>
					<label>
						<input type="radio" class="field-editable" name="earnest2" value="1"/> 已签收
	           		</label>
	           		<label>
	           			<input type="radio" class="field-editable" name="earnest2" value="0" checked/> 未签收
	           		</label>
           		</div>
			</div>
			  <div class="form-group col-md-6 hidden">
                    <label class="control-label" for="designer">设计天数</label>
					   <div>
						 <input type="text" class=" form-control input-sm field-not-editable" data-parsley-required="true" id="acquisitionDays"  name="acquisitionDays"  value="">
						</div>
			 </div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="designNo">协议号</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" id="designNo" name="designNo" data-parsley-maxlength="50"/>
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">设计图号</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" name="designDrawingNo" data-parsley-maxlength="50"/>
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">图纸份数</label>
				<div>
					<input id="designDrawingCopies" type="text" class="form-control input-sm field-editable text-right" name="designDrawingCopies" data-parsley-type="number" onkeyup="limtLength(this.value,this.id)" data-parsley-maxlength="16"/>
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">每份张数</label>
				<div>
					<input id="designDrawingSheets" type="text" class="form-control input-sm field-editable text-right" name="designDrawingSheets" data-parsley-type="number" onkeyup="limtLength(this.value,this.id)" data-parsley-maxlength="16"/>
				</div>
			</div>
			<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
				<label class="control-label" for="">备注</label>
				<div>
					<textarea class="form-control input-sm field-editable" name="desginRemark" data-parsley-maxlength="500" rows="4"></textarea>
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
    //参数传false时，表单不可编辑
    $("#officeDrawingForm").toggleEditState(false);
    //trSData.officialDrawingTable.json && $("#totalCost").val(trSData.officialDrawingTable.json.budgetTotalCost);
     //隐藏loading
    $(".infodetails").hideMask();
    $(".toolBtn").addClass("hidden");
	$("#officeDrawingForm").styleFit();
	trSData.t && trSData.t.cgetDetail('officeDrawingForm','officialDrawing/viewProject','',datailBack);
    //materialtableinit();
    $('.confirmBtn').off().on('click',function(){
    	if($("#officeDrawingForm").parsley().isValid()){
    		var earnest2 = $("#officeDrawingForm input[name='earnest2']:checked").val();
        	if(earnest2==0){
        		 $("body").cgetPopup({
                 	  title: "提示信息",
                 	  content: "图纸未签收!",
                 	  accept: popClose,
                 	  chide: true,
                 	  icon: "fa-check-circle",
                 	  newpop: 'new'
                 }); 
        	}else{
        		$("body").cgetPopup({
               	  title: "提示信息",
               	  content: "是否确认图纸签收!",
               	  accept: saveDone,
               	  chide: false,
               	  icon: "fa-check-circle",
               	  newpop: 'new'
               }); 
        	}
    	}else{
         	//如果未通过验证, 则加载验证UI
         	$("#officeDrawingForm").parsley().validate();
        }
	})
	
	var saveDone=function(){
    	
    	var data=$("#officeDrawingForm").serializeJson();
     	  if(response){
               response.abort();
          }
     	  //加遮罩
      	  $(".infodetails").loadMask("正在保存...", 1, 0.5);
     	  var response = $.ajax({
               url: "officialDrawing/updateState",
               type: "POST",
               //timeout : 5000,
               contentType: "application/json;charset=UTF-8",
               data: JSON.stringify(data),
               success: function (data) {
            	  //取消遮罩
            	  $(".infodetails").hideMask();	 
             	  if (data === "true") {
             		  $("body").cgetPopup({
                       	  title: "提示信息",
                       	  content: "图纸签收成功!",
                       	  accept: successBack,
                       	  chide: true,
                       	  icon: "fa-check-circle",
                       	  newpop: 'new'
                       }); 
             		  
             	  }else{
             		  $("body").cgetPopup({
                       	  title: "提示信息",
                       	  content: "图纸签收失败! <br>" + data,
                       	  accept: popClose,
                       	  chide: true,
                       	  icon: "fa-exclamation-circle",
                       	  newpop: 'new'
                       });  
             	  }
        	}
 		}); 
    }
	
	
  	var ensureDone=function(){};
  	var successBack=function(){
     	$("#officeDrawingForm")[0].reset();
     	$('#officialDrawingTable').cgetData(true);
     	$(".toolBtn").addClass("hidden");
    };
    
 	
 	 $(".cancelBtn").on("click",function(){
     	//清空表单
     	 $("#officeDrawingForm input,#officeDrawingForm textarea").val('');
    	 $("#officialDrawingTable").cgetData(true);
     });

	//限制输入框的输入长度
 	 function limtLength(value,id) {
		if (value.length>16){
		    $("#"+id).val(value.substr(0,16));
		}
     }
</script>
<!-- ================== END PAGE LEVEL JS ================== -->