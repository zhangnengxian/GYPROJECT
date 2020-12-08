<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="altimetricForm" action="/" method="POST">
        <div class="form-group col-sm-12">
            <label class="control-label" for="">桩号</label>
            <div >
                <input type="text" class="form-control input-sm" id="" name= />
            </div>
        </div>
        <div class="form-group col-sm-12">
            <label class="control-label" for="">原地面标高</label>
            <div >
                <input type="text" class="form-control input-sm" id="" name="" />
            </div>
        </div>
        <div class="form-group col-sm-12">
            <label class="control-label" for="">现地面标高</label>
            <div>
            	<input type="text" class="form-control input-sm" id="" name="" />
            </div>
        </div>
        <div class="form-group col-sm-12">
            <label class="control-label" for="">沟底标高</label>
            <div>
            	<input type="text" class="form-control input-sm" id="" name="" />
            </div>
        </div>
        <div class="form-group  col-sm-12">
            <label class="control-label" for="">埋深</label>
            <div>
            	<input type="text" class="form-control input-sm" id="" name="" />
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#altimetricForm").styleFit();
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->