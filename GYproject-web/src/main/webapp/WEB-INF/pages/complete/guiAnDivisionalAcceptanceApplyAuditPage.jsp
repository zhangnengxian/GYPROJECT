<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

			<div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>试压记录</h4></div>
		    <div class="form-group col-md-6 col-sm-12 ">
		    	<!-- 新加字段 -->
				<label class="control-label">试压记录</label>
				<div>
					<label><input class="field-editable" type="radio" name="testRecord" value="1"  checked> 有</label>
					<label><input class="field-editable" type="radio" name="testRecord" value="2" > 无</label>
				</div>
		    </div>
		    <div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>竣工资料</h4></div>
		    <div class="form-group col-md-6 col-sm-12 ">
		    	<!-- 新加字段 -->
				<label class="control-label">竣工资料</label>
				<div>
					<label><input class="field-editable" type="radio" name="isCompleteReport" value="1" > 有</label>
					<label><input class="field-editable" type="radio" name="isCompleteReport" value="2"> 无</label>
				</div>
		    </div>

			<div class="clearboth"><h4><i class="fa fa-arrow-circle-o-right"></i>预验收记录</h4></div>
			<div class="form-group col-md-6 col-sm-12 ">
				<!-- 新加字段 -->
				<label class="control-label">预验收记录</label>
				<div>
					<label><input class="field-editable" type="radio" name="isPreInspection" value="1" > 有</label>
					<label><input class="field-editable" type="radio" name="isPreInspection" value="2" > 无</label>
				</div>
			</div>
		    
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label">计划验收日期</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable datepicker-default" id="planAcceptDate" name="planAcceptDate"  value=""/>
		        </div>
		    </div>