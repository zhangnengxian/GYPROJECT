<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href='assets/plugins/fullcalendar/fullcalendar.css' rel='stylesheet' />
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	 	<a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 backBtn installBackBtn" id="installBackBtn">返回</a>
    	 	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole gaddbtn">增加</a>
			<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole gsavebtn">保存</a>
    	 	<a href="javascript:;" class="btn btn-info  btn-sm m-l-5 printBtn" >打印</a>
		</div>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="installRecordForm" action="">
			<!-- <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">安装类型</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50" value="普表"/>
		        </div>
		    </div> -->
		    <!-- <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">安装户数</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="projName" name="projName" data-parsley-maxlength="200" value="9"/>
		        </div>
		    </div> -->
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="pressureType">房间号</label>
				<div>
	                <select class="form-control input-sm" id="roomNum"   name="roomNum"  >
		                <option value="0" ></option>
		                <option value="1" >1001</option>
		                <option value="2" >1002</option>
		                <option value="3" >1003</option>
		                <option value="4" >1004</option>
		                <option value="5" >1005</option>
		                <option value="6" >1006</option>
		                <option value="7" >1007</option>
		                <option value="8" >1008</option>
		                <option value="9" >1009</option>
		                <option value="10" >1010</option>
		              </select>
	            </div>
			</div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="pressureType">表钢号</label>
				<div>
		        	<input type="text" class="form-control input-sm "  id="num" name="num" data-parsley-maxlength="200" value="T-001"/>
		        </div>
			</div>
		    
		    <table id="installRecordAuditTable" class="table table-striped table-bordered nowrap" width="100%" >
                <thead>
                    <tr>
                        <th>房间号</th>
                        <th>表钢号</th>
                    </tr>
                </thead>
           	</table>
		</form>
		
		<!-- <div id="planGridBox"></div>
		<style>
		#calendar .fc-left{
			margin-left:170px;
		}
		#calendar .fc-left button{
			height: 1.8em;
			line-height: 1.8em;
		}
		#calendar .fc-left button span{
			top:0;
		}</style> -->
	
	</div>
</div>
<script src='assets/plugins/bootstrap-daterangepicker/moment.min.js'></script>
<script src='assets/plugins/fullcalendar/fullcalendar.min.js'></script>
<script src="assets/plugins/fullcalendar/lang/zh-cn.js"></script>
<script>
App.restartGlobalFunction();
   //隐藏loading
$(".infodetails").hideMask(); 
   
   //参数传false时，表单不可编辑
$("#installRecordForm").toggleEditState(false).styleFit();
$('#installBackBtn').addClass('hidden');   
   
   
   
$(".printBtn").off().on("click",function(){
	//左移后会加上这个属性s
	if(!$(this).is(".slidetoleft")){ 
		$(".slide-panel").slidePanel().toleft(function(){
			$('#installBackBtn').removeClass('hidden'); 
			//帆软
			showReport1();
		})
		
	}
	return false;
});   
 
$('.backBtn').off().on('click',function(){
	$(".slide-panel").slidePanel().toright(function(){
		$('.installBackBtn').addClass('hidden'); 
		//$("#previewArea").addClass("hidden");
     });
}) 

//计划格子调用
$("#planGridBox").renderPlanGrid([], "", "", function(data){
    console.info(data);
	if(data === null){
		data = "1";
	} 
	$('#planDay').val(data);
});

$.getScript('projectjs/complete/install-record.js').done(function () {
	installRecord.init();
});
   
</script>