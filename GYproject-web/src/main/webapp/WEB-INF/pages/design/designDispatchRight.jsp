<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editBtn">
       <a href="javascript:;" class="btn btn-query btn-sm m-l-7 dispatchBtn" >派工</a>
	</div>
	<div class="clearboth form-box">
	
		<input class="deptType" type="hidden" name="deptType"  value="${deptType}"/>
		<form class="form-horizontal" id="designDispatchForm" data-parsley-validate="true" action="">
			<input type="hidden" name="duName" id="duName" value=""/>
			<input type="hidden" name="unitId" id="unitId" value=""/>
			<div class="form-group col-md-6">
            	<label class="control-label" for="projNo">工程编号</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="paNo">受理单号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="paNo" name="paNo" data-parsley-maxlength="50" />
		        </div>
		    </div>
			<div class="form-group col-md-12">
            	<label class="control-label" for="projName">工程名称</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="projName" name="projName"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 col-sm-12">
        		<label class="control-label" for="projAddr">工程地点</label>
            	<div >
		             <input type="text" class="form-control input-sm field-not-editable" id="projAddr" name="projAddr"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 col-sm-12">
        		<label class="control-label" for="areaDes">区域</label>
            	<div >
		             <input type="text" class="form-control input-sm field-not-editable" id="areaDes" name="areaDes"/>
            	</div>
        	</div>
        	<div class="form-group col-md-12">
            	<label class="control-label" for="projScaleDes">工程规模</label>
            	<div>
		            <textarea class="form-control field-not-editable"  id="projScaleDes" name="projScaleDes" rows="2" data-parsley-maxlength="200"></textarea>
		        </div>
        	</div>
        	<div class="form-group col-md-12">
            	<label class="control-label" for="custName">申报单位</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="custName" name="custName"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="200" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" data-parsley-required="true"/>
		        </div>
		    </div>
        	
			
			<div class="form-group col-md-6">
      			<label class="control-label" for="">设计定金</label>
				<label>
					<input class="field-editable" type="radio" name="depositGet" value="1"/> 已缴
           		</label>
           		<label>
           			<input class="field-editable" type="radio"  name="depositGet" value="0" /> 未缴
           		</label>
			</div>
			
			<div class="form-group col-md-12">
            	<label class="control-label" for="depositGetRemark">备注</label>
            	<div>
		            <textarea class="form-control field-editable"  id="depositGetRemark" name="depositGetRemark" rows="2" data-parsley-maxlength="200"></textarea>
		        </div>
        	</div>
			<div class="form-group col-md-12">
            	<label class="control-label" for="designStation">设计所</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="designStation" name="designStation" value="" data-parsley-required="true"/>
            		<a id="cuPop" class="asBtn btn btn-default btn-sm m-l-5 aspop" title="选择设计所"><i class="fa fa-search"></i></a>
            	</div>
        	</div>
        	<div class="form-group col-md-6">
            	<label class="control-label" for="designStationDirector">负责人</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="designStationDirector" name="designStationDirector"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6">
            	<label class="control-label" for="phone">联系电话</label>
            	<div >
                	<input type="text" class="form-control input-sm field-editable" id="phone" name="phone"/>
            	</div>
        	</div>
        	<input type="hidden" name="designStationId" id="designStationId"/>
        	<input type="hidden" name="projId" id="projId"/>
        	<!-- <input type="hidden" name="surveyer" id="surveyer"/> -->
		</form>
    </div>
    <!-- <div>
    	<h4><strong>资料收集情况</strong></h4>
    </div>
	<table id="dataTable" class="table table-hover table-bordered nowrap" width="100%">
       <thead>
           <tr>
               <th></th>
               <th>应收资料名称</th>
               <th>签收日期</th>
               <th>签收人</th>
           </tr>
       </thead>
      </table> -->
	<!-- <div>
    <h4 class="m-t-20"><strong>设计员</strong></h4>
    </div>
    <table id="designerTable" class="table table-hover table-bordered nowrap" width="100%">
        <thead>
            <tr>
            	<th>名称</th>
                <th>待勘察任务</th>
                <th>待设计任务</th>
                <th></th>
            </tr>
        </thead>
	</table> -->
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    $("#designDispatchForm").toggleEditState().styleFit();
    
    //查右侧工程详述
    //trSData.t.cgetDetail('designDispatchForm','designDispatch/viewDispatchResult','',detailBack);
    trSData.t&&trSData.t.cgetDetail('designDispatchForm','designDispatch/viewDispatchResult','',detailBack);
   
    
    //点击派工按钮
    $(".dispatchBtn").on("click",function(){
    	var radioVal = $("#designDispatchForm input[type='radio']:checked").val();
    	if($("#designDispatchForm").parsley().isValid()){
    		if(radioVal=="1") {   
       		    	saveData();
       		}else{   //未缴设计定金
           		$("body").cgetPopup({     
       				title: '提示',
       				content: '未缴纳设计定金，继续派工请点击确定',
       				accept: sureDone
       	    	}); 
       		}
    		$("#designDispatchForm").parsley().reset();
    	}else{
    		$("#designDispatchForm").parsley().validate();
    	}  
    })
    
    var sureDone=function(){
    	saveData();
    }
    
    function saveData(){
        var data=$("#designDispatchForm").serializeJson();
       	$.ajax({
            type: 'POST',
            url: 'designDispatch/updateProject',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
            	var content = "一级派遣成功！";
            	if(data === "false"){
            		content = "一级派遣失败！";
            	}else if(data === "true"){
            		$("#designDispatchForm").formReset();
            		$("input[name='depositGet'][value='0']").attr("checked","checked");
            		$("#designDispatchTable").cgetData();
            		updateDesignerBack();
            	}
            	var myoptions = {
                    	title: "提示信息",
                    	content: content,
                    	accept: popClose,
                    	chide: true,
                    	newpop:  'new',
                    	icon: "fa-check-circle"
                }
                $("body").cgetPopup(myoptions);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("设计派遣区派工失败！");
            }
		}); 
    }
    
    
    
    
    //点击确定
    var ensureDone=function(){}
    
  	//选中设计所
    $("#cuPop").off('click').on("click",function(){
    	var deptType = $(".deptType").val();
    	designDeptPopup({'designStationDirector':'principal','phone':'phone','designStation':'deptName','designStationId':'deptId'},deptType,deptcallback);
  	});
    var deptcallback =function(){
    	designerData.deptId = $('#unitId').val();
    	//$('#designerTable').cgetData(true);
    };
    //designertableinit();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->