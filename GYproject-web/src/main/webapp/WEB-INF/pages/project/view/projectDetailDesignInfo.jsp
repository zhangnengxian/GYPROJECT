<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="infodetails">
    <h3>设计信息</h3>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="designInfoDetailform" data-parsley-validate="true" action="">
    		<input type="hidden" class="projId" value="${designInfo.projId}"/>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="ocoNo">委托单号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  name="ocoNo"  value="${designInfo.ocoNo}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="connectGasNo">设计院单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"   name="duName"  value="${designInfo.duName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
      			<label class="control-label" for="">是否已缴纳设计定金</label>
		        <div>
	           		<select class="form-control input-sm field-editable" id="depositGet" name="depositGet">
						<option value=""></option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 clearboth">
      			<label class="control-label" for="">是否已缴纳全款</label>
		        <div>
					<select class="form-control input-sm field-editable " id="designFull" name="designFull">
						<option value=""></option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6">
		        <label class="control-label" for="surveyDate">委托日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default"   name=""  value="${designInfo.ocoDate}" >
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="surveyer">设计人</label>
		        <div>
		           <input type="text" class="form-control input-sm field-not-editable"  name="surveyer"  value="${designInfo.designer}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="surveyDate">设计完成日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default"   name="duCompleteDate"  value="${designInfo.duCompleteDate}" >
		        </div>
		    </div>
		</form>
    </div>
	<h3>材料记录</h3>
	<table id="drawingTable" class="table table-hover table-bordered nowrap m-t-40" width="100%">
		<thead>
         	<tr>
              <th>图号</th>
              <th>图纸名称</th>
              <th>数量</th>
         	</tr>
    	</thead>
  		</table>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    $(".infodetails").hideMask();
    $("#designInfoDetailform").toggleEditState().styleFit();
    $("#earnest").val($(".earnest").val());
    $("#designFull").val($(".designFull").val());
   /*  $("input[name='earnest'][value="+$(".earnest").val()+"]").attr("checked","checked"); 
    $("input[name='designFull'][value="+$(".designFull").val()+"]").attr("checked","checked"); */
    drawingTableInitFunction();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->