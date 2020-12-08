<!-- pricedBoq.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="toolBtn f-l p-b-10 btn-group newEditBtn m-l-10 hidden">
<!-- 	    <a href="javascript:;" class="btn btn-query btn-sm new-release" >发布</a> -->
    	<a href="javascript:;" class="btn btn-confirm btn-sm new-saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm new-cancelBtn">返回</a>
	</div>
	<div class="toolBtn f-r p-b-10 btn-group newEditBtn hidden">
    	<a href="javascript:;" class="btn btn-query btn-sm new-addBtn" >增加</a>
        <a href="javascript:;" class="btn btn-query btn-sm new-confirmBtn">确认</a>
        <a href="javascript:;" class="btn btn-warn btn-sm new-delBtn">删除</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="pricedBoqDetailform" data-parsley-validate="true" action="">
       		<input type="hidden" id="qsId" name="qsId"/>
       		<input type="hidden" id="veId" name="veId"/>
			<div class="form-group col-md-6">
				<label class="control-label" for="corp">分公司</label>
				<div>
					<select id="corpId" name="corpId" class="form-control input-sm field-editable">
						<option value="" ></option>
						<c:forEach var="enums" items="${corp}">
							<option value="${enums.deptId}" >${enums.deptName}</option>
						</c:forEach>
					</select>
			   </div>
		 	</div> 
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="costType">造价类型</label>
		        <div>
		        	<select id="costType" class="form-control input-sm field-editable" name="costType" >
						<option value="-1"><c:out value="${costType}"></c:out></option>
						<c:forEach var="enums" items="${costTypeDes}">
            				<option value="${enums.value}">${enums.message}</option>
        				</c:forEach>
    				</select>
		        	<!-- <input type="text" class="form-control input-sm field-editable"  id="costTypeDes" name="costTypeDes" value=""/> -->
		        </div>
		    </div>
		    <div class="form-group  col-md-12 ">
		        <label class="control-label" for="subitemName">分部分项工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="subitemName" name="subitemName" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="unit">单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="unit" name="unit" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="unitPrice">劳务单价</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable fixed-number"  id="unitPrice" name="unitPrice" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="subitemContent">工作内容及项目特征</label>
		        <div>
		        	<textarea rows="" cols="" class="form-control input-sm field-editable"  id="subitemContent" name="subitemContent" data-parsley-maxlength="800" value=""></textarea>
		        </div>
		    </div>
<!-- 		    <div class="form-group col-md-6"> -->
<!-- 		        <label class="control-label" for="surveyDate">发布日期</label> -->
<!-- 		        <div> -->
<!-- 		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="surveyDate"  name="surveyDate" data-parsley-required="true" value="" > -->
<!-- 		        </div> -->
<!-- 		    </div> -->
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
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    //表单样式适应
    $('#pricedBoqDetailform').toggleEditState().styleFit();
    
  	//查右侧工程详述
    trSData.t && trSData.t.cgetDetail('pricedBoqDetailform','',''); 
    
	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
	var newDeleteDone=function(){
		console.info($("#pricedBoqTable1").DataTable().rows('.selected' ).length);
		var rows = $("#pricedBoqTable1").DataTable().rows('.selected' ).remove().draw();
	};
	//删除监听
		$('.new-delBtn').off('click').on('click',function(){
			//加载弹屏
			if($('#pricedBoqTable1').find('tr.selected').length>0){
				$('body').cgetPopup({
					title: '删除',
					content: '确定要删除工程量标准？',
					accept: newDeleteDone
				});
			}else{
				$('body').cgetPopup({
					title: '删除',
					content: '请选择要删除的工程量标准！',
					accept: delDone
				});
			}
		});
	
    /**点击右侧维护区 保存 按钮时*/
    $('.saveBtn').on('click',function(){
        if($('#pricedBoqDetailform').parsley().isValid()){
        	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
        	var data=$('#pricedBoqDetailform').serializeJson();
        	console.info(data);
        	$.ajax({
                type: 'POST',
                url: 'pricedBoq/saveOrUpdatePricedBoq',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "工程量标准已存在，请修改！";
                	}else if(data === "true"){
                		$("#pricedBoqDetailform input").val('');
                		$("#pricedBoqDetailform textarea").val('');
                		$("#pricedBoqDetailform").formReset();
                		$("#pricedBoqTable").cgetData();  //列表重新加载
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
                    console.warn("工程量标准信息保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#pricedBoqDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#pricedBoqDetailform").parsley().validate();
        }
        
    }); 
    
    //放弃
    $(".cancelBtn").on("click",function(){
    	$("#pricedBoqDetailform input").val('');
    	trSData.t && trSData.t.cgetDetail('pricedBoqDetailform','',''); 
    	$("#pricedBoqDetailform").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["pricedBoqTable"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#pricedBoqDetailform").parsley().reset();
    });
    //新增版本的返回
    $(".new-cancelBtn").on("click",function(){
    	$('.newEditBtn').addClass("hidden");
		$('.insertBtn,.signBtn,.deleteBtn,.addBtn').removeClass("hidden");
		$('.pricedBoqTable1').addClass("hidden");		
    });
    $('.new-addBtn').off('click').on('click',function(){
// 		$("#pricedBoqDetailform input, #pricedBoqDetailform textarea").val("");
		$("#pricedBoqDetailform").formReset("#costType");
		//移除选中
   		$('#pricedBoqTable1 tr.selected').removeClass("selected");
		//切换可编辑状态
		$("#pricedBoqDetailform").toggleEditState(true);
	});
    //新增版本中新增记录（先存页面，不存库）
    $(".new-confirmBtn").off("click").on("click",function(){
    	var json={};
    	json=$('#pricedBoqDetailform').serializeJson();
    	console.log(json);
    	json.costTypeDes=$("#costType option:checked").text();
    	console.info(json);
    	if(json.qsId===""){
    	pricedBoqTable1.row.add(json).draw();  
    	}else{
    	pricedBoqTable1.row('.selected').remove().draw(); 
    	pricedBoqTable1.row.add(json).draw();  
    	}
    	$('#pricedBoqDetailform').toggleEditState(false);
    })
    
    
	//发布 保存 
	$(".new-saveBtn").off("click").on("click",function() {
		var url = '#pricedBoq/releasePricedBoq';
		//加载弹屏
		$('body').cgetPopup({
			title : '版本发布',
			content : url,
			accept : function() {
// 				var jsonArray = pricedBoqTable1.rows().data();
// 				console.info(jsonArray);
// 				JSON.parse(jsonArray);
// 				console.info(jsonArray);
				
				var jsonArray=[];
		    	 var arr=pricedBoqTable1.rows().data().each(function(k, v){
		    		 console.info(k);
		    		 jsonArray.push(k);
		    	 }); 
		    	 console.info(jsonArray);
// 				jsonArray.serializeJson();
				if (jsonArray.length > 0) {
					var data = {};
					var version = {};
					version = $('#releasePricedBoq').serializeJson();
					version.lastId = 1;
					data.vs = version;
					data.list = jsonArray;
					$.ajax({
						type : 'POST',
						url : 'pricedBoq/saveOrUpdatePricedBoqBat',
						contentType : "application/json;charset=UTF-8",
						data : JSON.stringify(data),
						success : function(data) {
							var content = "数据保存成功！";
							if (data === "false") {
								content = "数据保存失败！";
							} else if (data === "true") {
								/* $("#pricedBoqDetailform input").val('');
								$("#pricedBoqDetailform textarea").val(''); */
								$("#pricedBoqTable").cgetData(); //列表重新加载
								$(".pricedBoqTable1").addClass('hidden');
								$('.newEditBtn').addClass("hidden");
								$('.insertBtn,.signBtn,.deleteBtn,.addBtn').removeClass("hidden");
							}
							var myoptions = {
								title : "提示信息",
								content : content,
								accept : popClose,
								chide : true,
								newpop :'new',
								icon : "fa-check-circle"
							}
							$("body").cgetPopup(myoptions);
						},
						error : function(jqXHR,textStatus,errorThrown) {
							console.warn("工程量标准信息保存失败！");
						}
					});
				} else {
					var myoptions = {
						title : "提示信息",
						content : "没有要保存的数据",
						accept : popClose,
						chide : true,
						newpop :'new',
						icon : "fa-check-circle"
					}
					$("body").cgetPopup(myoptions);
				}
			}
		});
	});
	//    //发布
	// 	$('.new-release').on('click', function() {
	// 		var url = '#pricedBoq/releasePricedBoq';
	// 		//加载弹屏
	// 		$('body').cgetPopup({
	// 			title : '版本发布',
	// 			content : url,
	// 			accept : function() {
	// 					var releaseDate = $('#releasePricedBoq').serializeJson();

	// 			}
	// 		});
	// 	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->