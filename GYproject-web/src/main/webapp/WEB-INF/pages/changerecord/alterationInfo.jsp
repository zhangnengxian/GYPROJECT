<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tab-pane fade in btn-top" id="alteration_record">
     <div class=" clearboth form-box">
		<form class="form-horizontal" action="designAlteration/saveDesignAlterationFile" method="POST" enctype="multipart/form-data" id="designAlterationForm" data-parsley-validate="true" >
			<input type="hidden" id="result" name="result">
			<input type="hidden" id="menuDes" name="menuDes">
			<input type="hidden" id="cmId" name="cmId" value="${cmId}" class="">
			<input type="hidden" id="drawUrl" name="drawUrl">
			<input type="hidden" id="drawUrl1" name="drawUrl1" value="${drawUrl1}">
			<input type="hidden" id="cmState" name="cmState" value="${cmState}" >
			<input type="hidden" name="projLtypeId" id="projLtypeId"/>
			<input type="hidden" name="stepId" id="stepId" />
			<input type="hidden" name="alPath" id="alPath" />
			<input type="hidden" name="isAudit" id="isAudit" value="${isAudit}">
			<input type="hidden" name="version"/>
			<input type="hidden" class="auditLevel" name="auditLevel"  value="${auditLevel}">
			<div class="form-group col-sm-6">
				<label class="control-label" for="changeType">变更类型</label>
				<div>
					<select class="form-control input-sm field-not-editable" id="changeType" name="changeType" >
						<c:forEach var="enums" items="${changeType}">
							<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
					</select>
				</div>
			</div>
			<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
	 	    <input type="hidden" class="form-control input-sm field-not-editable" id="projId" name="projId">
	 	    <div class="form-group col-md-6 col-sm-12 clearboth">
				<label class="control-label" for="">变更日期</label>
				<div>
					<input type="text" class="form-control input-sm datepicker-default field-not-editable" id="cmDate" name="cmDate"  />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="">变更编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable addClear" id="cmNo" name="cmNo"/>
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
				<div class="form-group col-sm-12 conChange">
					<label class="control-label" for="">变更提出单位</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable " id="cmAdvanceUnit" name="cmAdvanceUnit" />
					</div>
				</div>
				<!-- <div class="form-group col-md-6 col-sm-12 duChange">
					<label class="control-label" for="">联系电话</label>
					<div>
						<input type="text" class="form-control input-sm field-editable" id="cuPhone" name="cuPhone"  />
					</div>
				</div> -->
				
	    		<!-- <div class="form-group col-sm-12 duChange">
     				<label class="control-label" for="">申报单位</label>
    				<div> 
         				<input type="text" class="form-control input-sm field-not-editable" id="custName" name="custName"/>
        				</div>
    		    </div> -->
    		    
				<div class="form-group col-sm-12 conChange">
     				<label class="control-label" for="">变更原因</label>
    				<div> 
         				<textarea class="form-control field-not-editable " name="cuReason" id="cuReason" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
        				</div>
    		    </div>
    		    <div class="form-group col-sm-12 ">
     				<label class="control-label" for="changeContent">变更内容</label>
    				<div> 
         				<textarea class="form-control field-not-editable " name="changeContent" id="changeContent" rows="5" cols="" data-parsley-maxlength="500" ></textarea>
        				</div>
    			</div>
    		    <div class="form-group col-sm-12 projectChange conChange">
     				<label class="control-label" for="drawFileType">附件类型</label>
    				<div> 
         				<label> <input type="radio" class="field-not-editable "
						name="drawFileType" value="1" />变更内容
					</label>
					 <label> <input type="radio" class="field-not-editable"
							name="drawFileType" value="2"  />变更设计图
						</label>
					 <label> <input type="radio" class="field-not-editable"
							name="drawFileType" value="3"  />相关会议纪要
						</label>
						<label> <input type="radio" class="field-not-editable"
							name="drawFileType" value="4" />其他
						</label>
        				</div>
    		    </div>
    		    <!-- <div class="form-group col-sm-12 conChange">
					<label class="control-label proj_change" for="">变更方案简图</label>
					<label class="control-label projectChange" for="">变更附件</label>
					<div>
					<div class="hidden hasVal"> 
	         			<input type="text" class="form-control input-sm field-not-editable" id="drawName" name="drawName"/>
	         			<a class="btn btn-sm btn-primary searchButton " href="" target="_blank">查看</a>
         		        <a class="btn btn-sm btn-primary Search_Button" href="javascript:">查看</a>
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
					        </div>
					    </div>
				    	The table listing the files available for upload/download
				    	<table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
					</div>
					</div> -->
				<div class="form-group col-md-6 col-sm-12 clearboth">
						<input type="hidden" class="form-control input-sm field-editable" id="cmAdvanceStaffId" name="cmAdvanceStaffId"   data-parsley-maxlength="30"/>
						<label class="control-label" for="cmAdvanceStaffName">提出用户</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable" id="cmAdvanceStaffName" name="cmAdvanceStaffName"   data-parsley-maxlength="50"/>
						</div>
					</div>	
    		    <div class="projectChange conChange">
	    		    <div class="form-group col-sm-12 clearboth">
						<label class="control-label" for="">工程数量增/减</label>
						<div>
							<input type="text" class="form-control input-sm  field-not-editable" id="cmProjQuantity" name="cmProjQuantity"  data-parsley-maxlength="50"/>
						</div>
					</div>
					<div class="form-group col-sm-12 clearboth ">
						<label class="control-label" for="">费用量增/减</label>
						<div>
							<input type="text" class="form-control input-sm  field-not-editable" id="cmCost" name="cmCost"  data-parsley-maxlength="50"/>
						</div>
					</div>
					<div class="form-group col-sm-12 clearboth">
						<label class="control-label" for="cmTimeLimit">工期变化</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable" id="cmTimeLimit" name="cmTimeLimit"  data-parsley-maxlength="50"/>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12 clearboth">
						<label class="control-label" for="cmReceiveUnit">通知部门(单位)</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable" id="cmReceiveUnit" name="cmReceiveUnit"  data-parsley-maxlength="200"/>
						</div>
					</div>
					<div class="form-group  col-sm-12 clearboth">
						<label class="control-label" for="cuManagerDept">施工项目经理部</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable" id="cuManagerDept" name="cuManagerDept"  data-parsley-maxlength="200"/>
						</div>
					</div>
				
				<div class="form-group col-md-6 col-sm-12 clearboth otherSign">
					<label class="control-label signature-tool" for="">项目经理</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="cuPm" name="cuPm" >
						<input type="hidden" id="cuPm_postType" name="cuPm_postType" value="${cuPm }" >
						<img class="cuPm" alt="" src="images/sign-1.png" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
	    		</div>
    		    
				<!-- <div class="form-group  col-sm-12 ">
					<label class="control-label" for="duName">设计单位</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" id="duName" name="duName"  />
					</div>
				</div> -->
				<%-- <div class="form-group col-md-6 col-sm-12 ">
					<label class="control-label signature-tool" for="duLeader">设计负责人</label>
					<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="duLeader" name="duLeader" value="">
					<input type="hidden"  id="dduLeader_postType" name="duLeader_postType" value="${duLeaderPost }">
					<img class="duLeader" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
				</div> --%>
				<div class="form-group  col-sm-12 ">
					<label class="control-label" for="suName">监理机构</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" id="suName" name="suName"  />
					</div>
				</div>
				<div class="form-group col-md-6 col-sm-12 clearboth otherSign">
					<label class="control-label signature-tool" for="suCes">总监理工程师</label>
					<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="suCes" name="suCes" value="">
					<input type="hidden"  id="suCes_postType" name="suCes_postType" value="${suCesPost }">
					<img class="suCes" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
				</div>
    			<div class="form-group col-sm-12">
					<label class="control-label" for="corpName">建设单位</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" id="corpName" name="corpName"  />
					</div>
				</div>
				<div class="form-group col-md-6 col-sm-12 clearboth otherSign">
					<label class="control-label signature-tool" for="custLeader">负责人</label>
					<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="custLeader" name="custLeader" value="">
					<input type="hidden"  id="custLeader_postType" name="custLeader_postType" value="${custLeaderPost }">
					<img class="custLeader" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
	    		</div>
	    		<div class="form-group  col-sm-12 ">
					<label class="control-label" for="duName">设计单位</label>
					<div>
						<input type="text" class="form-control input-sm field-not-editable" id="duName" name="duName"  />
					</div>
				</div>
	    		<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
        			<label class="control-label" for="">审批结果</label>
	            	<div>
	             		 <label>
			            	<input type="radio" class="field-not-editable" name="auditState" value="1"/>不通过
			            </label>
			             <label>
			            	<input type="radio" class="field-not-editable" name="auditState" value="3"/>通过
			            </label>
			        </div>
   				</div>
	    		<div class="form-group col-sm-12">
					<label class="control-label" for="">设计院意见</label>
					<div>
						<textarea class="form-control field-editable" name="duOpinion" id="duOpinion" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					</div>
				</div>
				<div class="form-group col-md-6 col-sm-12">
					<label class="control-label signature-tool" for="">设计所长签字</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white " id="signBtn_8"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="duPrincipal" name="duPrincipal" >
						<input type="hidden" id="duPrincipal_postType" name="duPrincipal_postType" value="" >
						<img class="duPrincipal" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
	    		</div>
		    	<div class="form-group col-md-6 col-sm-12">
					<label class="control-label" for="">审核日期</label>
					<div>
						<input type="text" class="form-control input-sm datepicker-default field-editable" id="auditDate" name="auditDate"  />
					</div>
				</div>
    		</div>
    		<div class=" userChange">
			   <div class="form-group col-md-6 col-sm-12 ">
					<label class="control-label" for="">申请日期</label>
					<div>
						<input type="text" class="form-control input-sm datepicker-default field-not-editable" id="applyDate" name="applyDate"  />
					</div>
				</div>
  				<div class="form-group col-sm-12">
     				<label class="control-label" for="">申请原因</label>
    				<div> 
         				<textarea class="form-control field-not-editable" name="applyReason" id="applyReason" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
        				</div>
    		    </div>
    			<div class="form-group col-md-6 col-sm-12 ">
					<label class="control-label" for="">确认人</label>
					<div>
						<input type="text" class="form-control input-sm  field-not-editable" id="designer" name="designer"  />
					</div>
				</div>
    		</div>
		</form>
		<!-- 签证信息 -->
		<form class="form-horizontal hidden" id="engineeringForm" data-parsley-validate="true" action="engineering/saveEngineeringVisaFile">
			<input type="hidden" id="menuDes1" name="menuDes1" value="签证记录">
			<input type="hidden" id="evId" name="evId">
			<input type="hidden" id="drawUrl2" name="drawUrl">
			<!-- <input type="hidden" id="evId" name="evId"> -->
			<input type="hidden" id="drawUrl1" name="drawUrl1" value="${drawUrl1}">
	 		<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
			<div class="form-group col-sm-12">
               	<label class="control-label" for="">工程编号</label>
                   <div> 
                   	<input type="text" class="form-control input-sm field-not-editable" value="" id="projNo" name="projNo" data-parsley-maxlength="50">
 				    </div> 
 				</div>
			<div class="form-group col-sm-12">
               	<label class="control-label" for="">工程名称</label>
                   <div> 
                   	<input type="text" class="form-control input-sm field-not-editable" value="" id="projName" name="projName" data-parsley-maxlength="200">
 				    </div> 
 				</div>
			<!-- <div class="form-group col-sm-12">
				<label class="control-label" for="">分包单位</label>
		    	<div>
					<input type="text" class="form-control input-sm field-not-editable" value="" id="constructionUnit" name="constructionUnit"  data-parsley-maxlength="50"/>
			   </div>
			</div> -->
			<div class="form-group col-md-6 col-sm-12">
               	<label class="control-label" for="evNo">签证编号</label>
                   <div> 
                   	<input type="text" class="form-control input-sm field-editable" value="" id="evNo" name="evNo" data-parsley-maxlength="30" >
 				    </div> 
 				</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="evPosition">签证部位</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="evPosition" name="evPosition"  data-parsley-maxlength="50" />
				</div>
    		</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="drawingNo">施工图号</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="drawingNo" name="drawingNo"  data-parsley-maxlength="50" />
				</div>
    		</div>
    		 <div class="form-group col-md-6 col-sm-12">
				<label class="control-label">签证日期</label>
				<div>
					<input type="text" class="form-control input-sm  field-editable timestamp all" value="" id="visaDate" name="visaDate"   />
				</div>
			</div>
			<div class="form-group col-sm-6">
				<label class="control-label" for="evType">签证类型</label>
				<div>
					<select class="form-control input-sm field-editable" id="evType" name="evType" >
						 <c:forEach var="enums" items="${evType}">
							<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach> 
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="submitAmount">报送金额</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" value="" id="submitAmount" name="submitAmount"  data-parsley-maxlength="10" data-parsley-required="true"/>
				</div>
			</div>
			<div class="form-group col-sm-12">
    				<label class="control-label" for="evReason">签证原因</label>
   				<div> 
        				<textarea class="form-control field-editable" name="evReason" id="evReason" rows="4" cols="" data-parsley-maxlength="255" ></textarea>
       				</div>
   			</div>
   			<div class="form-group col-sm-12">
    				<label class="control-label" for="evContent">签证内容及工程量</label>
   				<div> 
        				<textarea class="form-control field-editable" name="evContent" id="evContent" rows="4" cols="" data-parsley-maxlength="500" ></textarea>
       				</div>
   			</div>
   			<div class="form-group col-sm-12">
				<label class="control-label" for="constructionUnit">施工单位</label>
				<div>
					<input type="text" class="form-control field-not-editable input-sm"   id="constructionUnit" name="constructionUnit"  />
				</div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool" for="suPrincipal">项目负责人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="suPrincipal" name="suPrincipal" value="">
					<input type="hidden" id="suPrincipal_postType" name="suPrincipal_postType" value="${suPrincipal}">
					<img class="suPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label">审核日期</label>
				<div>
					<input type="text" class="form-control input-sm datepicker-default field-editable" value="" id="custAuditDate" name="custAuditDate"  />
				</div>
			</div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool" for="">施工员</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
					<input type="hidden" id="builder_postType" name="builder_postType" value="${builder}">
					<img class="builder" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
			<div class="form-group col-sm-12">
				<label class="control-label" for="custName">建设单位</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" value="" id="custName" name="custName"  data-parsley-maxlength="50"/>
			    </div>
			</div>
			 <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool" for=cmoPrincipal>现场代表</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="cmoPrincipal" name="cmoPrincipal" value="">
					<input type="hidden" id="cmoPrincipal_postType" name="cmoPrincipal_postType" value="${builder}">
					<img class="cmoPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
			<div class="form-group col-lg-12 col-md-12 col-sm-12">
     	        <label class="control-label" for="">现场代表意见</label>  
     	        <div> 
    					        <textarea class="form-control field-editable addClear allText builder"  name="cmoPrincipalOpinion" id="cmoPrincipalOpinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
				        </div>
   			<div class="form-group col-lg-12 col-md-12 col-sm-6">
       			<label class="control-label" for="">审核结果</label>
            	<div>
					<input type="hidden" id="cmoPrincipalResult" value="">
            	 	<label><input type="radio" class=" allText builder" name="cmoPrincipalResult" value="1" />通过</label>
            	 	<label><input type="radio" class=" allText builder" name="cmoPrincipalResult" value="0" />未通过</label>
		        </div>
  				</div>
			 <div class="form-group col-md-6 col-sm-12">
				<label class="control-label">审核日期</label>
				<div>
					<input type="text" class="form-control input-sm  datepicker-default field-editable all" value="" id="builderAuditDate" name="builderAuditDate"   />
				</div>
			</div>
			
		   
		    <div class="form-group col-sm-12">
				<label class="control-label" for="suName">监理单位</label>
				<div>
					<input type="text" class="form-control field-not-editable input-sm"   id="suName" name="suName"  />
				</div>
		    </div>
		    <div class="form-group col-lg-12 col-md-12 col-sm-12">
     	        <label class="control-label" for="">监理意见</label>
     	        <div> 
    					        <textarea class="form-control field-editable addClear allText suJgj"  name="suOpinion" id="suOpinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
				        </div>
   			<div class="form-group col-lg-12 col-md-12 col-sm-6">
       			<label class="control-label allText suJgj" for="">审核结果</label>
            	<div>
            	 <label>
			            <input type="radio" class=" allText suJgj" name="suResult" value="1" checked/>通过
			            </label>
            	 <label>
			            <input type="radio" class=" allText suJgj" name="suResult" value="0" />未通过
			            </label>
		             
		        </div>
  				</div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool" for="suJgj">现场监理</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
					<input type="hidden" id="suJgj_postType" name="suJgj_postType" value="${suJgj}">
					<img class="suJgj" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label">审核日期</label>
				<div>
					<input type="text" class="form-control input-sm datepicker-default field-editable" value="" id="suAuditDate" name="suAuditDate"  />
				</div>
			</div>
			 <div class="form-group col-sm-12 backReason hidden">
    				<label class="control-label" for="backReason">不通过原因</label>
   				<div> 
        				<textarea class="form-control field-not-editable" name="backReason" id="backReason" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
       				</div>
   			</div>
		</form>
	</div>
	<div class = "clearboth"></div>
		<form id="fileupload" action="accessoryCollect/uploadFile" method="POST" enctype="multipart/form-data">
		    <input type="hidden" name="alPath" id="alPath" value="0104"/>
		    <input type="hidden" name="encryption" id="encryption" />
		    <input type="hidden" name="caiId" id="caiId" />
		    <input type="hidden" name="step" id="step" />
		    <input type="hidden" name="stepId" id="stepId" />
		    <input type="hidden"  class="projId" name = "projId" value = ""/>
			<input type="hidden"  class="projNo" name = "projNo" value = ""/>
		    <input type="hidden" name="sourceType" id="sourceType"  value="${accessorySource }"/>
		    <input type="hidden" class = "formClear" name="busRecordId" id="busRecordId" />
		   	<input type="hidden" id="loginId" name="loginId" value="${loginId}"/>
		   	<input type="hidden" id="aspectRatio" value="1.25" />
			<div class="fileupload-buttonbar">
		        <div class="pull-right toolBtn">
		            <span class="btn btn-success btn-sm fileinput-button">
		                <i class="fa fa-plus"></i>
		                <span>浏览文件...</span>
		                <input type="file" name="files[]" multiple/>	             	          
		            </span>
		            <button type="submit" class="btn btn-primary btn-sm start hidden">
	                </button>
	                <button type="button" class="btn btn-primary btn-sm upload-btn">
	                    <i class="fa fa-upload"></i>
	                    <span>上传</span>
	                </button>
		        </div>
		        <div class="col-md-12 fileupload-progress fade hidden">
		            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
		                <div class="progress-bar progress-bar-success" style="width:0%;"></div>
		            </div>
		            <div class="progress-extended">&nbsp;</div>
		        </div>
		    </div>
		    <table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
	    </form>
		<table id="dataPopTableSecond" class="table table-striped table-bordered nowrap" width="100%">
	   		<thead>
	     		<tr>
	     			<th></th>
	     		    <th></th>
	           		<th>资料名称</th>
	           		<th>资料类型</th>
	            	<th>签收日期</th>
	            	<th>签收人</th>
	            	<th width='40'>操作</th>
	           	</tr>
	       	</thead>
		</table>
	<div class="clearboth form-box">
		<div class="form-group width-full attach-images-list" id="projectImagesList">  
			<h4><i class="fa fa-file-photo-o"></i> 照片列表
				<%--<a href="javascript:;" class="btn btn-primary btn-sm upload-image-btn pull-right"><i class="fa fa-upload"></i> 上传</a>--%>
				<%--<a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a>--%>
			</h4>
			<ul class="row"></ul>
		</div>
	</div>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script id="template-upload" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-upload fade">
            <td class="col-md-1 hidden">
                <span class="preview"></span>
            </td>
            <td width="60%">
                <p class="name filename">{%=file.name%}</p>
                <strong class="error text-danger"></strong>
            </td>
            <td width="20%">
                <p class="size">Processing...</p>
            </td>
            <td width="20%">
                <div class="progress progress-striped active"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
            </td>
            <td class="hidden">
                {% if (!i && !o.options.autoUpload) { %}
                    <button class="btn btn-primary btn-sm start" disabled>
                        <i class="fa fa-upload"></i>
                        <span>Start</span>
                    </button>
                {% } %}
                {% if (!i) { %}
                    <button class="btn btn-white btn-sm cancel">
                        <i class="fa fa-ban"></i>
                        <span>Cancel</span>
                    </button>
                {% } %}
            </td>
        </tr>
    {% } %}
</script>
<script id="template-download" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-download fade">
            <td class="hidden">
                <span class="preview">
                    {% if (file.thumbnailUrl) { %}
                        <!--<a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>-->
                    {% } %}
                </span>
            </td>
            <td width="60%">
                <p class="name">
                    {% if (file.url) { %}
                        <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                    {% } else { %}
                        <span>{%=file.name%}</span>
                    {% } %}
                </p>
                {% if (file.error) { %}
                    <div><span class="label label-danger">Error</span> {%=file.error%}</div>
                {% } %}
            </td>
            <td width="20%">
                <span class="size">{%=o.formatFileSize(file.size)%}</span>
            </td>
            <td width="20%">
                <div class="progress progress-striped text-center"><div class="progress-bar progress-bar-success" style="width:100%;">已上传</div></div>
            </td>
            <td class="hidden">
                {% if (file.deleteUrl) { %}
                    <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                        <i class="glyphicon glyphicon-trash"></i>
                        <span>Delete</span>
                    </button>
                    <input type="checkbox" name="delete" value="1" class="toggle">
                {% } else { %}
                    <button class="btn btn-warning cancel">
                        <i class="glyphicon glyphicon-ban-circle"></i>
                        <span>Cancel</span>
                    </button>
                {% } %}
            </td>
        </tr>
    {% } %}
</script>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //初始化上传文件控件
    $.getScript('projectjs/data/form-multiple-upload.demo.js?'+Math.random()).done(function() {
    	FormMultipleUpload.init();
    });
    //隐藏loading
    $(".infodetails").hideMask();
    hiddenOpt(state);
    $("#stepId").val(getStepId());
    $("#step").val(getStepId());
    //表单样式适应
    $("#designAlterationForm").toggleEditState(false).styleFit();
    $("#engineeringForm").toggleEditState(false).styleFit();
    trSData.changeRecordTable.t && trSData.changeRecordTable.t.cgetDetail('designAlterationForm', 'designAlteration/viewChangeManagement?menuDes='+$("#menuDes").val(), '',rollback);
    //trSData.visaRecordTable.t && trSData.visaRecordTable.t.cgetDetail('engineeringFormengineeringForm', 'engineering/viewEngineeringVisa?menuDes='+$("#menuDes1").val(), '',rollback2);
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    $('.Search_Button').off("click").on("click",function(){  
		$("body").cgetPopup({
			title: '查看图片',
			content: '<div class="preview-box"><img src="attachments/' +$("#drawUrl").val() + '" class="preview-image"></div>',
			accept: function(){},
			atext: '关闭',
			chide: true,
			size: 'large',
			icon: 'fa-pencil-square-o'
		});
	
});
    
  //签字加载方式
 	 $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
  		 $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4").handleSignature(); 
 	 });
    //   变更类型
    $("#changeType").change(function(){
            if($(this).val()=="2"){
            	//用户类型
            	$(".cust_change").removeClass('hidden');
	    		$(".proj_change").addClass('hidden');
	        	$('.CnChange').addClass('hidden');
	        	$('.CuChange').removeClass('hidden');
            }else{
            	$(".cust_change").addClass('hidden');
    	    	$(".proj_change").removeClass('hidden');
            	$('.CuChange').addClass('hidden');
            	$('.CnChange').removeClass('hidden');
            }
            $("#designAlterationForm").styleFit();
            
    	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->