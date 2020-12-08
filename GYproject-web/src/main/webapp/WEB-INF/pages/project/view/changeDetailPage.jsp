<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<form id="changeForm" class="form-horizontal"  data-parsley-validate="true" action="">
		<div class="form-group col-md-6 col-sm-12 clearboth">
			<label class="control-label" for="">变更日期</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable datepicker-default timestamp addClear" id="cmDate" name="cmDate" data-parsley-required="true" />
			</div>
		</div>

		<div class="form-group col-md-6 col-sm-12">
			<label class="control-label" for="">变更编号</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable addClear" id="cmNo" name="cmNo" data-parsley-maxlength="30"/>
			</div>
		</div>

		<div class="form-group col-md-6 col-sm-12">
			<label class="control-label" for="changeType">变更类型</label>
			<div>
				<select class="form-control input-sm field-not-editable" id="changeType" name="changeType" data-parsley-required="true">
					<option value="1" >工程变更</option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-6 col-sm-12">
			<label class="control-label" for="">工程编号</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo"  />
			</div>
		</div>
		<div class="form-group col-sm-12">
			<label class="control-label" for="">工程名称</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable" id="projName" name="projName"  />
			</div>
		</div>
		<div class="form-group col-sm-12">
			<label class="control-label" for="">工程地点</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable" id="projAddr" name="projAddr"  >
			</div>
		</div>

		<div class="form-group col-sm-12">
			<label class="control-label" for="">工程规模</label>
			<div>
				<textarea class="form-control input-sm field-not-editable" id="projScaleDes" name="projScaleDes" rows="2" data-parsley-maxlength="200"></textarea>
			</div>
		</div>
		<div class="form-group col-sm-12">
			<label class="control-label" for="">变更提出单位</label>
			<div>
				<input type="text" class="form-control input-sm field-editable addClear allText builder construction cuPm" id="cmAdvanceUnit" name="cmAdvanceUnit"/>
			</div>
		</div>
		<div class="form-group col-sm-12">
			<label class="control-label" for="">变更原因</label>
			<div>
				<textarea class="form-control field-editable addClear allText builder construction cuPm" name="cuReason" id="cuReason" rows="2" cols="" data-parsley-maxlength="200" data-parsley-required="true"></textarea>
			</div>
		</div>
		<div class="form-group col-sm-12">
			<label class="control-label" for="changeContent">变更内容</label>
			<div>
				<textarea class="form-control field-editable addClear allText builder construction cuPm" name="changeContent" id="changeContent" rows="5" cols="" data-parsley-maxlength="500" data-parsley-required="true"></textarea>
			</div>
		</div>
		<div class="form-group col-sm-12  hidden cancelRemark">
			<label class="control-label" for="">废弃原因</label>
			<div>
				<textarea class="form-control field-editable addClear allText builder construction cuPm" name="cancelRemark" id="cancelRemark" required="required" rows="4" cols="" data-parsley-maxlength="500" ></textarea>
			</div>
		</div>
		<div class="form-group col-md-6 col-sm-12 hidden cancelStaffName">
			<label class="control-label" for="">废弃申请人</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable" id="cancelStaffName" name="cancelStaffName"/>
			</div>
		</div>
		<div class="form-group col-md-6 col-sm-12 hidden cancelDate">
			<label class="control-label" for="">废弃时间</label>
			<div>
				<input type="text" class="form-control input-sm field-not-editable" id="cancelDate" name="cancelDate"/ >
			</div>
		</div>

		<div class="form-group col-sm-12">
			<label class="control-label">附件类型</label>
			<div>
				<label> <input type="radio" class="field-editable allText builder construction cuPm"
							   name="drawFileType" value="1" />变更内容
				</label>
				<label> <input type="radio" class="field-editable allText builder construction cuPm"
							   name="drawFileType" value="2"  />变更设计图
				</label>
				<label> <input type="radio" class="field-editable allText builder construction cuPm"
							   name="drawFileType" value="3"  />相关会议纪要
				</label>
				<label> <input type="radio" class="field-editable allText builder construction cuPm"
							   name="drawFileType" value="4" checked="checked" />其他
				</label>
			</div>
		</div>
		<div class="form-group col-sm-12 allText">
			<label class="control-label projectChange" for="">变更附件</label>
			<div>
				<div class="hidden hasVal">
					<input type="text" class="form-control input-sm field-not-editable hasVal" id="drawName" name="drawName"/>
					<a class="btn btn-sm btn-primary searchButton " href="" target="_blank">查看</a>
					<a class="del_btn btn btn-sm btn-danger"><i class="fa fa-times"></i> 删除</a>
				</div>
				<div class="fileupload-buttonbar noVal hidden">
					<div class="pull-right toolBtn">
													<span class="btn btn-success btn-sm fileinput-button">
														<i class="fa fa-plus"></i>
														<span>浏览文件...</span>
														<input type="file" name="files[]" multiple/>
													</span>
						<button type="submit" class="btn btn-primary btn-sm start hidden">
							<i class="fa fa-upload"></i>
							<span>上传</span>
						</button>
						<a class="surplusDelBtn btn btn-sm btn-danger "><i class="fa fa-times"></i> 删除</a>
					</div>
				</div>
				<table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
			</div>
		</div>
		<div class="projectChange">
			<div class="form-group col-sm-12 clearboth">
				<label class="control-label" for="">工程数量增/减</label>
				<div>
					<input type="text" class="form-control input-sm field-editable addClear allText builder construction cuPm" id="cmProjQuantity" name="cmProjQuantity"  data-parsley-maxlength="50"/>
				</div>
			</div>
			<div class="form-group col-sm-12 clearboth">
				<label class="control-label" for="">费用量增/减</label>
				<div>
					<input type="text" class="form-control input-sm field-editable addClear allText builder construction cuPm" id="cmCost" name="cmCost"  data-parsley-maxlength="50"/>
				</div>
			</div>
			<div class="form-group col-sm-12 clearboth">
				<label class="control-label" for="cmTimeLimit">工期变化</label>
				<div>
					<input type="text" class="form-control input-sm field-editable addClear allText builder construction cuPm" id="cmTimeLimit" name="cmTimeLimit"  data-parsley-maxlength="50"/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 clearboth">
				<input type="hidden" class="form-control input-sm field-editable addClear" id="cmAdvanceStaffId" name="cmAdvanceStaffId"   data-parsley-maxlength="30"/>
				<label class="control-label" for="cmAdvanceStaffName">发起人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable addClear" id="cmAdvanceStaffName" name="cmAdvanceStaffName"   data-parsley-maxlength="50"/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 clearboth">
				 --><label class="control-label" for="">需设计变更材料</label>
				<div>
					<label>
						<input type="radio" class="field-editable allText builder construction cuPm" name="changeMaterialFlag" value="1"  />是
					</label>
					<label>
						<input type="radio" class="field-editable allText builder construction cuPm" name="changeMaterialFlag" value="0" checked="checked"/>否
					</label>
				</div>
			</div>

			<div class="form-group col-md-6 col-sm-12 clearboth allSign cuPm">
				<label class="control-label signature-tool" for="">项目经理</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="cuPm" name="cuPm" value="">
					<input type="hidden" class="signPost"  id="cuPm_postType" name="cuPm_postType" value="${cuPm}">
					<img class="cuPm" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-sm-12 ">
				<label class="control-label" for="duName">设计单位</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="duName" name="duName"  />
				</div>
			</div>
			<div class="form-group col-sm-12 clearboth">
				<label class="control-label" for="">设计人员</label>
				<div>
					<input type="hidden" class="form-control input-sm field-not-editable" id="designerId" name="designerId"  data-parsley-maxlength="50"/>
					<input type="text" class="form-control input-sm field-not-editable" id="designer" name="designer"  data-parsley-maxlength="50"/>
				</div>
			</div>

			<hr width="100%" style="height: 1px;border:none;border-bottom:1px dashed #acacac"/>
			<div class="form-group col-sm-12 ">
				<label class="control-label" for="suName">监理机构</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="suName" name="suName"/>
				</div>
			</div>
			<div class="form-group col-lg-12 col-md-12 col-sm-12">
				<label class="control-label" for="">监理意见</label>
				<div>
					<textarea class="form-control field-editable allText suCse" id="suOpinion" name="suOpinion"  rows="4" data-parsley-maxlength="200"></textarea>
				</div>
			</div>
			<div class="form-group col-lg-12 col-md-12 col-sm-6">
				<label class="control-label allText suCse" for="">审核结果</label>
				<div>
					<label>
						<input type="radio" class=" allText suCse" name="suAuditResult" value="1" checked/>通过
					</label>
					<label>
						<input type="radio" class=" allText suCse" name="suAuditResult" value="0"/>未通过
					</label>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 allSign suCse">
				<label class="control-label signature-tool" for="suCes">总监理工程师</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="suCes" name="suCes" value="">
					<input type="hidden" class="signPost"  id="suCes_postType" name="suCes_postType" value="${sucse}">
					<img class="suCes" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<hr width="100%" style="height: 1px;border:none;border-bottom:1px dashed #acacac"/>
			<div class="form-group col-sm-12">
				<label class="control-label" for="corpName">建设单位</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"
						   id="corpName" name="corpName"/>
				</div>
			</div>
			<div class="form-group col-lg-12 col-md-12 col-sm-12">
				<label class="control-label" for="">负责人意见</label>
				<div>
					<textarea class="form-control field-editable allText builder" id="custLeaderOpinion"  name="custLeaderOpinion" rows="4" data-parsley-maxlength="200"></textarea>
				</div>
			</div>
			<div class="form-group col-lg-12 col-md-12 col-sm-6">
				<label class="control-label allText builder" for="">审核结果</label>
				<div>
					<label>
						<input type="radio" class=" allText builder" name="custLeaderAuditResult" value="1" checked/>通过
					</label>
					<label>
						<input type="radio" class=" allText builder" name="custLeaderAuditResult" value="0"/>未通过
					</label>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 allSign builder">
				<label class="control-label signature-tool" for="custLeader">负责人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="custLeader" name="custLeader" value="">
					<input type="hidden" class="signPost"  id="custLeader_postType" name="custLeader_postType" value="${builderPost }">
					<img class="custLeader" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-sm-12  hidden duAudit">
				<label class="control-label" for="">设计院意见</label>
				<div>
					<textarea class="form-control field-editable addClear"  data-parsley-required="true" name="duOpinion" id="duOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 hidden duAudit">
				<label class="control-label signature-tool" for="">设计所长签字</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white " id="signBtn_5"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="duPrincipal" name="duPrincipal" >
					<input type="hidden" class="signPost"  id="duPrincipal_postType" name="duPrincipal_postType" value="" >
					<img class="duPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 hidden duAudit">
				<label class="control-label" for="">审核日期</label>
				<div>
					<input type="text" class="form-control input-sm datepicker-default field-editable" id="auditDate" name="auditDate"  />
				</div>
			</div>
		</div>
	</form>
</div>




<script>

    App.restartGlobalFunction();
    $(".infodetails").hideMask();//隐藏loading
    $("#changeForm").toggleEditState(false).styleFit();
    $('.datepicker-default').datepicker({ todayHighlight: true});//日期控件
    trSData.t && trSData.t.cgetDetail('changeForm','projectView/findChangeDetail','',detailCallback);//第一次加载显示详细

    var detailCallback=function (data) {};
</script>
