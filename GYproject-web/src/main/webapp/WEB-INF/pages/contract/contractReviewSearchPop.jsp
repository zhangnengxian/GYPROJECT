<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchAcceptancePop" action="/" method="POST">
        <div class="form-group col-md-12">
            <label class="control-label">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" >工程地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
         <div class="form-group col-md-6">
            <label class="control-label" for="isPrint">打印状态</label>
            <div>
            	<select class="form-control input-sm " id="isPrint" name="isPrint">
            		<option value="1">已打印</option>
            		<option value="0">未打印</option>
            	</select>
            </div>
        </div> 
         <div class="form-group col-md-6 col-md-6">
            <label class="control-label" for="pushTimeStart">完成时间</label>
            <div>
            	<input type = "text" class="form-control input-sm datepicker-default" id="pushTimeStart" name="pushTimeStart" value = ""/>
            </div>
        </div> 
        <div class="form-group col-md-6">
            <label class="control-label" for="pushTimeEnd">至</label>
            <div>
            	<input type = "text" class="form-control input-sm datepicker-default" id="pushTimeEnd" name="pushTimeEnd" value = ""/>
            </div>
        </div> 
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchAcceptancePop").styleFit();
    
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
 	
 	$("#pushTimeStart,#pushTimeEnd").on("change",function(){
 		var startTime = $("#pushTimeStart").val();  //开始时间
 		var endTime = $("#pushTimeEnd").val();   //结束时间
 		
 		if(startTime != ''){
 			var start=new Date(startTime.replace("-", "/").replace("-", "/"));
 		}
 		if(endTime != ''){
 			var end=new Date(endTime.replace("-", "/").replace("-", "/"));
 		}
 		 if(start >= end){
 			startTime = $("#pushTimeStart").val('');  //开始时间
 			alert("开始时间必须小于结束时间！");
 		}
 		 
 	})
</script>
<!-- ================== END PAGE LEVEL JS ================== -->