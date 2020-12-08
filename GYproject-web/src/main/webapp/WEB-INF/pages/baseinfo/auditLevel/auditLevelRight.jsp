<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
<div class="infodetails">
	<div class="toolBtn f-r p-b-15  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="auditLevelDetailform" action="auditLevel/saveDocType">
		    <input type="hidden" name="id" id="id"/>
		    <input type="hidden" class="contributionCode"/>
		     <input type="hidden" id="corpName" name="corpName"/>
		    <input type="hidden" id="projTypeDes" name="projTypeDes"/>
		    <input type="hidden" id="contributionCodeDes" name="contributionCodeDes"/>
			<input type="hidden" name="menuId" id="menuId"/>
		    <div class="form-group col-md-6">
				<label class="control-label" for="corpId">分公司</label>
				<div>
					<select id="corpId" name="corpId" class="form-control input-sm field-editable">
						<option value="" ></option>
						<c:forEach var="enums" items="${corp}">
							<option value="${enums.deptId}" >${enums.deptName}</option>
						</c:forEach>
					</select>
			   </div>
		 	</div> 
		 	<div class="form-group col-sm-6 col-xs-12">
	            <label class="control-label" for="projType">工程类型</label>
	             <div>
		    		<select class="form-control input-sm field-editable" id="projType"  name="projType" >
		 		      	<option value="-1">--请选择--</option>
		 		      	<c:forEach var="enums" items="${projType}">
							   	<option value="${enums.projTypeId}">${enums.projConstructDes}</option>
		                </c:forEach>
		             </select>
		        </div>
	        </div>
	        <div class="form-group col-sm-6 col-xs-12">
	            <label class="control-label" for="">出资类型</label>
	            <div>
		            <select class="form-control input-sm field-editable" id="contributionCode"  name="contributionCode" >
		 		      	<option value="-1">--请选择--</option>
		 		      	<c:forEach var="enums" items="${contributionMode}">
							   	<option value="${enums.id}">${enums.contributionDes}</option>
		                </c:forEach>
		             </select>
	             </div>
	        </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="corp">审核步骤</label>
				<div>
					<select id="stepId" name="stepId" class="form-control input-sm field-editable">
						<option value="" ></option>
						<c:forEach var="enums" items="${stepId}">
							<option value="${enums.stepId}" >${enums.stepDes}</option>
						</c:forEach>
					</select>
			   </div>
		    </div>
		    <input type="hidden" name="des" id="des"/>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">审核级别</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="grade" name="grade" data-parsley-max="6" data-parsley-type='integer' data-parsley-required="true"/>
		        </div>
		    </div>
			<ul class="nav nav-tabs p-t-5 p-l-5 clearboth">
				<li class="active"><a href="#tab-1" data-toggle="tab" id="workRole">菜单</a></li>
			</ul>
			<div class="tab-content p-l-0 p-r-0 p-t-5">
				<div class="tab-pane fade active in btn-top" id="tab-1" >
					<div class="form-group col-md-12">
						<label></label>
						<div id="menuTreeRoleManage">
						</div>
					</div>
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
    $("#auditLevelDetailform").toggleEditState().styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");

	$('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
  	//查右侧详述
   trSData.t && trSData.t.cgetDetail('auditLevelDetailform'); 
    
    //点击保存
    $(".saveBtn").on("click",function(){
    	//分公司
    	$("#corpName").val($("#corpId option:selected").text());
    	//审核单据类型
    	$("#des").val($("#stepId option:selected").text());
    	//工程类型
    	$("#projTypeDes").val($("#projType option:selected").text());
    	//出资方式
    	$("#contributionCodeDes").val($("#contributionCode option:selected").text());
    	//保存
    	$("#auditLevelDetailform").cformSave('auditLevelTable','',true);
    }); 
    
    //放弃 按钮点击后
    $(".cancelBtn").on("click",function(){
    	 //清空表单
    	 $("#auditLevelDetailform input").val('');
    	 trSData.t.cgetDetail('auditLevelDetailform'); 
    	 $("#auditLevelDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["auditLevelTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#auditLevelDetailform").parsley().reset();
    });
 
    
    $("#projType").change(function(){
    	$("#contributionCode").empty();
    	var selectEl = $("#projType"),
        index = selectEl[0].selectedIndex,
        selectOption = selectEl[0].options[index];
    	var data = $(selectOption).attr("value");
    	$.ajax({
    		type: 'post',
    		url: 'workFlow/querySubcompany?id='+data,
    		dataType: 'json',
    		success: function(data){
    	        $("#contributionCode").append('<option value="-1">--请选择--</option>');
    			$.each(data,function(n, v){
    	            $("#contributionCode").append('<option value='+v.correlatedInfoId+'>' + v.correlatedInfoDes + '</option>');
    	        });
    			
    			var val=$(".contributionCode").val();
    			if($("#id").val()!=""){
    				$("#auditLevelDetailform option[value='"+val+"']").attr("selected","selected");
    			}
    		},
    		error: function(data){
    			console.warn("出资类型选框查询失败");
    		}
    	});
    });

    var menuTreeInit = function() {
        $('#menuTreeRoleManage').jstree({
            "core": {
                "themes": { "responsive": false },
                "check_callback": true,
                'data': {
                    'url': 'menu/getMenuTreeForSet',
                    "dataType": "json"

                }
            },

            "types": {
                "default": { "icon": "fa fa-folder disabled text-warning fa-lg" },
                "file": { "icon": "fa fa-file text-warning fa-lg" }
            },
            "plugins": ["types"]
        });
        $('#menuTreeRoleManage').on("ready.jstree",function(){
            $("#menuTreeRoleManage").jstree("open_all");
            $('#menuTreeRoleManage').on("select_node.jstree", function (e, data) {
                var menuId = data.node.id.split("@@")[0];
                $("#menuId").val(menuId);
            });
        });

    };

    $(function () {
        menuTreeInit();
    })
</script>
<!-- ================== END PAGE LEVEL JS ================== -->