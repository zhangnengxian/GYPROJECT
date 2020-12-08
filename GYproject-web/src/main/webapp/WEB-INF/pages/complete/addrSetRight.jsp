<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
		<div class="toolBtn f-r p-b-10  editbtn">
    	 	<a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 returnBtn" id="returnBtn">返回</a>
		</div>
		<div class="toolBtn f-r p-b-10  editBtn">
    	 	<a href="javascript:;" class="btn btn-query  btn-sm m-l-5 installBtn" id="installBtn">登记</a>
		</div>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="addrBatchForm" action="">
			<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50" value="T-2016101929"/>
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">工程名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"  id="projName" name="projName" data-parsley-maxlength="200" value="百商小区"/>
		        </div>
		    </div>
		    <div class="form-group col-sm-12">
		        <label class="control-label" for="">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value="新医路"/>
		        </div>
		    </div>
		    <div class="form-group col-lg-6 col-md-12 col-sm-12">
		        <label class="control-label" >楼栋</label>
		        <div>
		            <input type="text" data-parsley-required="true" class="form-control input-sm disabled"  value="" id="" name="" />
	        	    <a href="javascript:;" id="buildingNum" class="asBtn btn btn-default btn-sm m-l-10" title="选择楼栋号"><i class="fa fa-search"></i></a>
		        </div>
	    	</div>
		    <div class="form-group col-lg-12 col-md-12 col-sm-12">
		        <label class="control-label" for="unit">单元</label>
		        <div id="innerform1" class="innerform">
				    <div class="form-group col-lg-4 col-md-4 col-sm-4">
				        <label class="control-label" for="unitNum">起始号</label>
				        <div>
				            <input type="text" class="form-control input-sm "   data-parsley-type="number"  id="unitNum" name="unitNum" value='1'/>
				        </div>
				    </div>
				    <div class="form-group col-lg-4 col-md-4 col-sm-4">
				        <label class="control-label" for="unitCount">数量</label>
				        <div>
				            <input type="text" class="form-control input-sm "  data-parsley-type="number"  id="unitCount" name="unitCount" value='2'/>
				        </div>
				    </div>
				    <div class="form-group col-lg-4 col-md-4 col-sm-4">
				        <label class="control-label" for="unitName">名称</label>
				        <div>
				            <input type="text" class="form-control input-sm " value="单元"   id="unitName" name="unitName" />
				        </div>
				    </div>
		        </div>
		    </div>
		    <div class="form-group col-lg-12 col-md-12 col-sm-12">
		        <label class="control-label" for="floor">楼层</label>
		        <div id="innerform2" class="innerform">
				    <div class="form-group col-lg-4 col-md-4 col-sm-4">
				        <label class="control-label" for="floorNum">起始号</label>
				        <div>
				            <input type="text" class="form-control input-sm "  data-parsley-type="number"  id="floorNum" name="floorNum" value='1'/>
				        </div>
				    </div>
				    <div class="form-group col-lg-4 col-md-4 col-sm-4">
				        <label class="control-label" for="floorCount">数量</label>
				        <div>
				            <input type="text" class="form-control input-sm "    data-parsley-type="number"  id="floorCount" name="floorCount" value='6'/>
				        </div>
				    </div>
				    <div class="form-group col-lg-4 col-md-4 col-sm-4">
				        <label class="control-label" for="floorName">名称</label>
				        <div>
				            <input type="text" class="form-control input-sm " value="层" id="floorName" name="floorName" />
				        </div>
				    </div>
		        </div>
		    </div>
		    <div class="form-group col-lg-12 col-md-12 col-sm-12">
		        <label class="control-label" for="room">房间</label>
		        <div id="innerform3" class="innerform">
				    <div class="form-group col-lg-4 col-md-4 col-sm-4">
				        <label class="control-label" for="roomNum">起始号</label>
				        <div>
				            <input type="text" class="form-control input-sm "  data-parsley-type="number"  id="roomNum" name="roomNum" value='1001'/>
				        </div>
				    </div>
				    <div class="form-group col-lg-4 col-md-4 col-sm-4">
				        <label class="control-label" for="roomCount">数量</label>
				        <div>
				            <input type="text" class="form-control input-sm "   data-parsley-type="number"  id="roomCount" name="roomCount" value='10'/>
				        </div>
				    </div>
				    <div class="form-group col-lg-4 col-md-4 col-sm-4">
				        <label class="control-label" for="roomName">名称</label>
				        <div>
				            <input type="text" class="form-control input-sm " value="户" id="roomName" name="roomName" />
				        </div>
				    </div>
		        </div>
		    </div>
		</form>
	</div>
	<div class="clearboth p-t-15">
	    <div class="iconTabs">
	       <%--  <ul class="nav nav-tabs p-t-2 p-l-5">
	            <li class="active tradition" data-mrtype="${tradition}" onclick="myfunc(this)"><a href="#" data-toggle="tab" ><span><img src="images/addr/pubiao.svg"></span>装普表</a></li>
	            <li class="card" data-mrtype="${card}" onclick="myfunc(this)"><a href="#" data-toggle="tab" ><span><img src="images/addr/kabiao.svg"></span>装卡表</a></li>
	            <li class="remotetrans" data-mrtype="${remotetrans}" onclick="myfunc(this)"><a href="#" data-toggle="tab" ><span><img src="images/addr/yuanchuanbiao.svg"></span>装远传表</a></li>
	        </ul> --%>
	    </div>`
	    <div class="tab-content">
	        <div class="tab-pane fade active in no-m-r-l-15" id="info-tab-tabtradition">
	        	<div class="form-box">
			        <form class="form-horizontal" id="tradition_form" data-parsley-validate="true">
			          	<input type="hidden"  id="meterModelId" name="meterModelId" />
			            <input type="hidden"  id="manuId" name="manuId" />
			            <input type="hidden"  id="meterType" name="meterType" />
			          	<div class="form-group  col-md-6 col-sm-6">
				  	 		<label class="control-label" for="meterModelDes">表型号</label>
					      	<div>
					         	<input type="text" class="form-control input-sm field-not-editable"   id="meterModelDes" name="meterModelDes" value='先锋'/>
					         	<a id="asPopType" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择表型号"><i class="fa fa-search"></i></a>
					        </div>
					    </div>
                        <div class="form-group col-md-6 col-sm-6">
                            <label class="control-label" for="meterTypeDes">表具类型</label>
                            <div>
                                <input type="text" class="form-control input-sm field-not-editable"  id="meterTypeDes" name="meterTypeDes" value='普表'/>
                            </div>
                        </div>
				      	<div class="form-group col-md-12 col-sm-6">
				         	<label class="control-label" for="manuDes">厂商</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="manuDes" name="manuName" value='先锋科技'/>
					        </div>
					    </div>
				     	<div class="form-group col-md-6 col-sm-6">
				         	<label class="control-label" for="meterRange">量程</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="meterRange" name="meterRange" value="0.01m³/h-6m³/h"/>
					        </div>
					    </div>
				     	<div class="form-group col-md-6 col-sm-6">
			         	<label class="control-label" for="useYears">使用年限</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"    data-parsley-type="number"  id="useYears" name="useYears" />
					        </div>
					    </div>
				     	<div class="form-group col-md-6 col-sm-6">
				         	<label class="control-label" for="installDate">安装日期</label>
					        <div>
					        	<input type="text" class="form-control input-sm datepicker-default "     id="installDate" name="installDate" />
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-6">
					    	<label class="control-label" for="installPosition">安装位置</label>
					        <div>
				 		      	<select class="form-control input-sm " id="installPosition"    name="installPosition" >
				               		<option value="">厨房</option>
			               			<option value="">过道</option>
			               			<option value="">阳台</option>
					            </select>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-6">
					   		<label class="control-label" for="fixedWay">固定方式</label>
					        <div>
				 		      	<select class="form-control input-sm " id="fixedWay" name="fixedWay" >
					               <option value="">有</option>
					               <option value="">无</option>
					               <option value="">不确定</option>
					            </select>
					        </div>
					    </div>
					   	<div class="form-group col-lg-6 col-md-12 col-sm-6">
					   		<label class="control-label" for="gasEnvironment">用气环境</label>
					        <div>
				 		      	<select class="form-control input-sm " id="gasEnvironment" name="gasEnvironment" >
					               <option value="">自然通风</option>
			               		   <option value="">强制通风</option>
					            </select>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-6">
					   		<label class="control-label" for="gasDirection">进气方向</label>
					        <div>
				 		      	<select class="form-control input-sm " id="gasDirection" name="gasDirection" >
					               <option value="">左</option>
					               <option value="">右</option>
					               <option value="">不确定</option>
					            </select>
					        </div>
					    </div>
				     	<div class="form-group col-md-6 col-sm-6 clearboth">
					   		<label class="control-label" for="pressCompeType">补偿类型</label>
					        <div>
				 		      	<select class="form-control input-sm " id="pressCompeType"  name="pressCompeType" >
					               <option value="">自动</option>
					               <option value="">非自动</option>
					               <option value="">不确定</option>
					            </select>
					        </div>
					    </div>
				     	<div class="form-group col-md-6 col-sm-6">
				         	<label class="control-label" for="pressCompeCoeff">补偿系数</label>
					        <div>
					        	<input type="text" value="" class="form-control input-sm field-editable"   id="pressCompeCoeff"  data-parsley-type="number"  name="pressCompeCoeff" />
					        </div>
					    </div>
						<div class="form-group col-md-6 col-sm-6 hidden" id="plugCardDirection_div">
							<label class="control-label" for="plugCardDirection1">插卡方向</label>
							<div>
								<select class="form-control input-sm " id="plugCardDirection1"  name="plugCardDirection" >
									<option value="">上</option>
									<option value="">下</option>
									<option value="">左</option>
									<option value="">右</option>
									<option value="">前</option>
								</select>
							</div>
						</div>
				     	<div class="form-group col-md-12 col-sm-12">
		                    <label class="control-label">备注</label>
		                    <div>
		                        <textarea class="form-control field-editable " name ="remark" rows="2" data-parsley-maxlength="200"></textarea>
		                    </div>
		              	</div>
	              	</form>
	            </div>
	        </div>

	    </div>
	</div>
</div>
<script>
App.restartGlobalFunction();
   //隐藏loading
$(".infodetails").hideMask(); 
$('.editbtn').addClass('hidden');   
   //参数传false时，表单不可编辑
$("#addrBatchForm").toggleEditState(false).styleFit();
$("#tradition_form").toggleEditState(true).styleFit();
    
$("#installBtn").off().on("click",function(){
	//左移后会加上这个属性
	if(!$(this).is(".slidetoleft")){ 
		$(".slide-panel").slidePanel().toleft(function(){
			//登记隐藏
			$(".installBtn").addClass("hidden");
			//返回显示
			$('.editbtn').removeClass('hidden');
			//加载记录
			$('#install_record_panel_box').cgetContent('addrSet/viewInstallPage');
		})
	}
	return false;
}); 
//弹出创建地址屏
$("#buildingNum").off().on("click",function(){
	var url = "#addrSet/addrBatchPopPage";
	var popoptions = {
		title: '地址批量创建',
		content: url,
		accept: searchDone
	};
	//加载弹屏
	$("body").cgetPopup(popoptions);
})

var searchDone=function(){}
var chooseDone=function(){}
//弹出表具类型
$("#asPopType").off().on("click",function(){
	var url = "#addrSet/meterTypePopPage";
	var popoptions = {
		title: '表具型号选择',
		content: url,
		accept: chooseDone
	};
	//加载弹屏
	$("body").cgetPopup(popoptions);
})

 $('.datepicker-default').datepicker({
        todayHighlight: true
 });






//返回主屏
$('.returnBtn').off().on('click',function(){
	$(".slide-panel").slidePanel().toright(function(){
		$('.editbtn').addClass('hidden'); 
		$('.installBtn').removeClass('hidden');
		$("#addrBatch").addClass("hidden");
     });
}) 




   
</script>