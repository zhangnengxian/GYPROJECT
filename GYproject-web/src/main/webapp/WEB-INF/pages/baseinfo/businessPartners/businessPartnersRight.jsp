<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-15  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	
    <div class="clearboth form-box">
    	<input class="deptType" type="hidden" name="deptType"  value="${deptType}"/>
    	<form class="form-horizontal" id="businessPartnersDetailform" action="">
		    <input type="hidden" name="unitId" id="unitId"/>
		    <input type="hidden" name="corpId" id="corpId"/>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="">单位名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="unitName" name="unitName" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">简称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="shortName" name="shortName"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">成立日期</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable datepicker-default"  id="unitFoundDate" name="unitFoundDate"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">单位资质</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="unitQualification" name="unitQualification"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">项目经理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="unitManager" name="unitManager" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">资格证名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="qualificationName" name="qualificationName"/>
		        </div>
		    </div><div class="form-group col-md-6 ">
		        <label class="control-label" for="">资格证编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="qualificationNo" name="qualificationNo"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">负责人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="unitDirector" name="unitDirector"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="unitPhone" name="unitPhone" />
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">手机</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="unitMobile" name="unitMobile" data-parsley-min=11/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">单位类型</label>
		    	<div>
		    		<select class="form-control input-sm field-editable" id="unitType"  name="unitType"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${unitType}">
    						<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		             </select>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">单位编码</label>
		    	<div>
		    		<input class="form-control input-sm field-editable" id="unitCode"  name="unitCode"  />
		 		      	
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 clearboth">
		        <label class="control-label" for="">燃气公司</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName" data-parsley-maxlength="1000" data-parsley-required="true"/>
		            <a id="corpNamePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择燃气公司"><i class="fa fa-search"></i></a>
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
    $("#businessPartnersDetailform").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");

	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
  	//查右侧详述
    trSData.t && trSData.t.cgetDetail('businessPartnersDetailform','businessPartners/viewBusinessPartners',''); 
    
    //点击保存
    $(".saveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	
        if($("#businessPartnersDetailform").parsley().isValid()){
        	var data=$("#businessPartnersDetailform").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'businessPartners/saveBusinessPartners',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "此单位已存在，请修改！";
                	}else{
                		$(".editbtn").addClass("hidden");
                		$("#businessPartnersDetailform input").val('');
                		$("#businessPartnersTable").cgetData();  //列表重新加载
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
                    console.warn("保存失败！");
                }
            });
           
        	$("#businessPartnersDetailform").parsley().reset();
        	//如果通过验证, 则移除验证UI
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#businessPartnersDetailform").parsley().validate();
        }
        
    }); 
    
    //放弃 按钮点击后
    $(".cancelBtn").on("click",function(){
    	 //清空表单
    	 $("#businessPartnersDetailform input").val('');
    	 trSData.t.cgetDetail('businessPartnersDetailform','businessPartners/viewBusinessPartners',''); 
    	 $("#businessPartnersDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["businessPartnersTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#businessPartnersDetailform").parsley().reset();
    });
 
    
  //选择施工单位
    $("#corpNamePop").off('click').on("click",function(){
    	var deptType = $(".deptType").val();
    	$("body").cgetPopup({
    		title: '部门选择',
    		nocache:true,
    		content: '#popup/deptPop?deptType=' + deptType,
    	    accept: function(){
    	    	if($("#deptTablePop tr.selected").length < 1){
    	    		$("body").cgetPopup({
    	    			title: '提示', 
    	    			content: "请选择部门！", 
    	    			newpop: 'second', 
    	    			accept: innerClose
    	    		});
    	    		return false;
    	    	}else{
    	    		
    	    		var roleIds = '';
    	    		var postNames='';
    	    		var data = $('#deptTablePop').DataTable().rows('.selected').data();
    	    		$.each(data,function(key, val){
    	    			if(roleIds.length > 0) {
    	    				roleIds +=",";
    	    				postNames+=",";
    	    			}
    	    			roleIds += val.deptId;
    	    			postNames+=val.deptName;
    	    		});
    	    		$("#corpId").val(roleIds);
    	    		$("#corpName").val(postNames);
    	    		
    	    	}
    	    }
    	});
  	});
    
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->