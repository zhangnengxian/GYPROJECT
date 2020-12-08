<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-6 -->
		<div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">通知列表</h4>
                </div>
                <div class="panel-body">
                	<input type="hidden" name="loginId" id="loginId" value="${loginId }"/>
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="businessCommunicationTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th>通知Id</th>
                                <th>发送人</th>
                                <th>接收人</th>
                                <th>通知日期</th>
                                <th>通知状态</th>
                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
        <!-- end col-6 -->
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" >
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <h4 class="panel-title">操作区</h4>
			    </div>
			    <div class="panel-body" >
				    <div id="business_communication_panel_box">
				    </div>
					<div class="toolBtn f-r p-b-10  editbtn">
		<a href="javascript:;" class="btn  btn-primary btn-sm m-l-5  hidden get-location" data-loading-text="正在定位..."><i class="fa fa-map-marker"></i> 定位</a>
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5  pushBtn" >推送</a>
	    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5  entBtn" >保存</a>
	    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5  saveBtn hidden" >回复保存</a>
    	<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="businessCommunicationForm" data-parsley-validate="true" action="">
		    <input type="hidden" name="projId" id="projId"/>
		    <input type="hidden" name="corpId" id="corpId"/>
		    <input type="hidden" class="formClear" name="bcId" id="bcId"/>
		    <input type="hidden" class="formClear" name="version" id="version"/>
		    <input type="hidden" class="formClear" name="bcStatus" id="bcStatus"/>
		    <input type="hidden" class="formClear" name="bcSendType" id="bcSendType"/>
		    <input type="hidden" name="bcInitiatorId" id="bcInitiatorId"/>
		    <input type="hidden" class="formClear" name="bcRecipientId" id="bcRecipientId"/>
		    <input type="hidden" name="bcInitiatorUnitId" id="bcInitiatorUnitId"/>
		    <input type="hidden" name="bcInitiatorUnitName" id="bcInitiatorUnitName"/>
		    <input type="hidden" class=""  id="bcTypeDetail1"/>
		    <input type="hidden" class=""  id="pushFlag" name="pushFlag"/>
		    <input type="hidden" class="formClear"  id="nrId" name="nrId"/>
		    <input type="hidden"   id="markOfTestResult" name="markOfTestResult"/>
		    <input type="hidden"   id="unitTypeOfInitiator" name="unitTypeOfInitiator"/>
		    <input type="hidden"   class="menuId ndeRecord" name="menuId"/>
		    
       		<div class="form-group col-md-6 col-sm-12 ">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
<!-- 		    <div class="form-group col-md-12  "> -->
<!-- 		        <label class="control-label" for="projAddr">工程地点</label> -->
<!-- 		        <div> -->
<!-- 		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/> -->
<!-- 		        </div> -->
<!-- 		    </div> -->
<!-- 		    <div class="form-group col-md-12"> -->
<!-- 	            <label class="control-label" for="projScaleDes">工程规模</label> -->
<!-- 	            <div> -->
<!-- 	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3"></textarea> -->
<!-- 	            </div> -->
<!--             </div> -->
<!--             <div class="form-group col-md-6 col-sm-12"> -->
<!-- 		    	<label class="control-label" for="corpName">燃气公司</label> -->
<!-- 		        <div> -->
<!-- 		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" /> -->
<!-- 		        </div> -->
<!-- 		    </div> -->
<!--         	<div class="form-group col-md-6 col-sm-12"> -->
<!-- 		        <label class="control-label" for="">工程类型</label> -->
<!-- 		    	<div> -->
<!-- 		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/> -->
<!-- 		        </div> -->
<!-- 		    </div> -->
<!-- 		    <div class="form-group col-md-6 col-sm-12"> -->
<!-- 		        <label class="control-label" for="">出资方式</label> -->
<!-- 		    	<div> -->
<!-- 		    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/> -->
<!-- 		        </div> -->
<!-- 		    </div> -->
<!-- 		    <div class="form-group col-md-6 col-sm-12"> -->
<!-- 		        <label class="control-label" for="">业务部门</label> -->
<!-- 		    	<div> -->
<!-- 		    		 <input type="text" class="form-control input-sm field-not-editable" name="deptName" value=""/>         -->
<!-- 		        </div> -->
<!-- 		    </div> -->
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="bcType">通知类型</label>
		        <div>
		            <select class="form-control input-sm field-editable" id="bcType"  name="bcType" data-parsley-required="true" >
		                <c:forEach var="enums" items="${bcType}">
							   	<option value="${enums.typeId}">${enums.typeDes}</option>
		                </c:forEach>
		             </select>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="bcTypeDetail">通知细类</label>
		        <div>
		            <select class="form-control input-sm field-editable" id="bcTypeDetail"  name="bcTypeDetail" data-parsley-required="true" >
		 		      	 <c:forEach var="enums" items="${bcTypeDetail}">
							   	<option value="${enums.typeId}">${enums.typeDes}</option>
		                </c:forEach>
		             </select>
		        </div>
		    </div>
		    <div class="form-group col-md-6 noUser">
		        <label class="control-label" for="bcInitiatorName">发起人姓名</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="bcInitiatorName" name="bcInitiatorName" value=""/>
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label">通知日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-not-default" id="noticeDate"  name="noticeDate" data-parsley-required="true" value="" >
		        </div>
		   	 </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="noticeContent">通知内容</label>
		         <div> 
		        	<textarea class="form-control field-editable formClear" name="noticeContent" id="noticeContent" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">检测项目</label>
 		    	<div>
		    		 <select class="form-control input-sm field-editable" id="testItem" name="testItem" data-parsley-maxlength="20">
		    		 	<c:forEach var="enums" items="${ndeTestItem }" varStatus="s">
		    		 		<option value="${enums.value }">${enums.message }</option>
		    		 	</c:forEach>
		    		 </select>   
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">管线编号</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="pipeLineNum" name="pipeLineNum" data-parsley-maxlength="500"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">表面状态</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="surfaceState" name="surfaceState" data-parsley-maxlength="500" value="合格"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">总焊口数</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="totalWeldsNum" name="totalWeldsNum" data-parsley-maxlength="500"/>   
		        </div>
		    </div>
		     <div class="form-group col-md-12 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">材质</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="tubing" name="tubing" data-parsley-maxlength="500"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">检测数量</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="nozzleNum" name="nozzleNum" value=""  data-parsley-maxlength="1000"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">规格</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="spec" name="spec" data-parsley-maxlength="1000"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">合格级别</label>
 		    	<div>
		    		 <select class="form-control input-sm field-editable" id="qualifiedLevel" name="qualifiedLevel" data-parsley-maxlength="200" >   
		    		 	<option ></option>
		    		 	<option value="1">Ⅰ</option>
		    		 	<option value="2">Ⅱ</option>
		    		 	<option value="3">Ⅲ</option>
		    		 	<option value="4">Ⅳ</option>
		    		 </select>
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">坡口形式</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="grooveForm" name="grooveForm" data-parsley-maxlength="1000"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">检测标准</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="acceptStandard" name="acceptStandard" data-parsley-maxlength="1000" value="NB/T47013-2015"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">检测等级</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="testLevel" name="testLevel" data-parsley-maxlength="1000" value="AB"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">检测比例</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="testRate" name="testRate" data-parsley-maxlength="1000"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">焊工证号</label>
 		    	<div>
		    		 <input type="text" class="form-control input-sm field-editable" id="welderNo" name="welderNo" data-parsley-maxlength="1000"/>   
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">焊接方法</label>
 		    	<div>
		    		 <select class="form-control  field-editable" id="weldingMethod" name="weldingMethod" data-parsley-maxlength="50">
		    		 	<c:forEach var="enums" items="${ndeWeldMethod}">
		    		 		<option value="${ enums.value}">${ enums.message}</option>
		    		 	</c:forEach>
		    		 	
		    		 </select>   
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12 ndeRecord hidden"> 
 		        <label class="control-label" for="">检测焊口位置备注</label>
 		    	<div>
		    		 <textarea class="form-control field-editable" name="ndeRemarks" id="ndeRemarks" rows="2" cols="" data-parsley-maxlength="1000" ></textarea> 
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
				<label class="control-label" for="email">从属单位类型</label>
				<div>
					<select id="unitType" name="unitType" class="form-control input-sm field-editable" data-parsley-required ='true'>
						<option value=""></option>
						<c:forEach var="enums" items="${unitTypes}">
							<option value="${enums.value}" data-c='${enums.type}'>${enums.message}</option>
						</c:forEach>
					</select>
					<!-- <input class="form-control input-sm field-editable"  name="unitType" id="unitType"> -->
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="bcRecipientDeptName">所属部门</label>
				<div>
					<input type="hidden" class="formClear" id="deptId" name="deptId">
		            <input type="text" class="form-control input-sm field-not-editable formClear" id="bcRecipientDeptName" name="bcRecipientDeptName" data-parsley-required ='true'/>
		            <a id="staffDeptTreeId" class="btn btn-default btn-sm m-l-10 " title="选择所属部门"><i class="fa fa-search"></i></a> 
	            </div>
			</div>
			<div class="form-group col-md-6 noUser">
		        <label class="control-label" for="">接收人姓名</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable formClear"  id="bcRecipientName" name="bcRecipientName" data-parsley-required ='true'/>
		            <a id="bcrPop" class="btn btn-default btn-sm m-l-10" title="选择施工员"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		        <div class="form-group col-md-6 ndeRecord hidden allSign suJgj  builder clearboth">
		                <label class="control-label signature-tool sign-require" for="suJgj">委托人签字</label>
				      <div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
					<input type="hidden" class="signPost" id="suJgj_postType" name="suJgj_postType" value="${sujgjPost}">
					<img class="suJgj" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				   </div>
		        </div>
		         <!-- 委托负责人签字 -->
		        <div class="form-group col-md-6 ndeRecord hidden allSign suCse  viceMinister projectLeader suJgj  builder">
		        <label class="control-label signature-tool  sign-require" for="suCse">委托负责人签字</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="suCse" name="suCse" value="">
					<input type="hidden" class="signPost" id="suCse_postType" name="suCse_postType" value="${suCsePost }">
					<img class="suCse" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				   </div>
		        </div>
		    <!-- 无损检测报告内容展示 -->
		    <div class="form-group col-md-6 clearboth ndeFormat hidden">
			        <label class="control-label" for="">通知编号</label>
			        <div>
			            <input type="text" class="form-control input-sm field-not-editable formClear"  id="noticeNo" name="noticeNo"  data-parsley-maxlength="500"/>
			           <!--  <a id="bcrPop" class="btn btn-default btn-sm m-l-10" title="选择施工员"><i class="fa fa-search"></i></a> -->
			        </div>
			    </div>
				<div class="form-group col-md-6 ndeFormat hidden ">
			        <label class="control-label" for="">报告编号</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable formClear allText checker"  id="reportNo" name="reportNo"  data-parsley-maxlength="500"/>
			           <!--  <a id="bcrPop" class="btn btn-default btn-sm m-l-10" title="选择施工员"><i class="fa fa-search"></i></a> -->
			        </div>
			    </div>
			    <div class="form-group col-md-6 ndeFormat hidden">
			        <label class="control-label" for="">检测结果</label>
			        <div>
			            <select class="form-control input-sm field-editable formClear allText checker"  id="testResult" name="testResult"  data-parsley-maxlength="500">
			           		<option></option>
			           		<c:forEach var="enums" items="${testResultEnum }">
			           			<option value="${enums.value }">${enums.message }</option>
			           		</c:forEach>
			           		
			            </select>
			          
			        </div>
			    </div>
				<div class="form-group col-md-6 ndeFormat hidden">
			        <label class="control-label" for="">缺陷性质</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable formClear allText checker"  id="defectNature" name="defectNature"  data-parsley-maxlength="1000"/>
			           <!--  <a id="bcrPop" class="btn btn-default btn-sm m-l-10" title="选择施工员"><i class="fa fa-search"></i></a> -->
			        </div>
			    </div>
				<div class="form-group col-md-6 ndeFormat hidden">
			        <label class="control-label" for="">缺陷长度</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable formClear allText checker"  id="defectLen" name="defectLen"  data-parsley-maxlength="1000"/>
			           
			        </div>
			    </div>
				<div class="form-group col-md-6 ndeFormat hidden">
			        <label class="control-label" for="">返修片数</label>
			        <div>
			            <input type="text" class="form-control input-sm field-editable formClear allText checker"  id="repairedPieces" name="repairedPieces"  data-parsley-maxlength="1000"/>
			          
			        </div>
			    </div>
				<div class="form-group col-md-6 ndeFormat hidden">
			        <label class="control-label">拍片日期</label>
			        <div>
			            <input type="text" class="form-control input-sm datepicker-default field-editable formClear allText checker"  id="shootDate" name="shootDate"  data-parsley-maxlength="1000"/>
			          
			        </div>
			    </div>
			    <div class="form-group col-md-12 ndeFormat hidden">
			        <label class="control-label" for="">检测情况</label>
			        <div>
			            <textarea class="form-control input-sm field-editable formClear allText checker"  id="testSituation" name="testSituation" data-parsley-maxlength="1000"></textarea>
			          
			        </div>
			    </div>
			    <div class="form-group col-md-12 ndeFormat hidden">
			        <label class="control-label" for="">返修部位编号</label>
			        <div>
			            <textarea class="form-control input-sm field-editable formClear allText checker"  id="repairPositionNo" name="repairPositionNo"  data-parsley-maxlength="3000"></textarea>
			          
			        </div>
			    </div>
			    <div class="form-group col-md-6 allSign checker ndeFormat hidden">
			        <label class="control-label signature-tool" for="bcInitiator">通知人签字</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="bcInitiator" name="bcInitiator" value="">
						<input type="hidden" class="signPost"  id="bcInitiator_postType" name="bcInitiator_postType" value="${receivePost }">
						<img class="bcInitiator" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
			    </div>								    
			    <div class="form-group col-md-6 allSign suJgj  builder ndeFormat hidden replyContent">
			        <label class="control-label signature-tool" for="bcRecipient">接收人签字</label>
					<div>
						<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
						<input type="hidden" class="sign-data-input" id="bcRecipient" name="bcRecipient" value="">
						<input type="hidden" class="signPost"  id="bcRecipient_postType" name="bcRecipient_postType" value="${bcRecipientPost }">
						<img class="bcRecipient" alt="" src="" style="height: 30px">
						<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
					</div>
			    </div>
									 
		   	<div class="form-group col-md-6">
		        <label class="control-label">回复日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default formClear" id="replyDate"  name="replyDate" value="" >
		        </div>
		   	 </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="replyContent">回复内容</label>
		        <div> 
		        	<textarea class="form-control field-not-editable formClear replyContent" name="replyContent" id="replyContent" rows="2" cols="" data-parsley-maxlength="500" ></textarea>
		        </div>
		    </div>
		</form>
						<div class = "clearboth"></div>
						<form id="fileupload" action="businessCommunication/saveUploadFile" method="POST" enctype="multipart/form-data">
						    <input type="hidden" name="alPath" id="alPath" value="0104"/>
						    <input type="hidden" name="encryption" id="encryption" />
						    <input type="hidden" name="caiId" id="caiId" />
						    <input type="hidden" name="step" id="stepId" />
						    <input type="hidden" class = "formClear" name="busRecordId" id="busRecordId" />
						   	<input type="hidden" id="loginId" name="loginId" value="${loginName.staffId}"/>
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
				    </div>
			    </div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
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
<script>
    App.restartGlobalFunction();
  //表单样式适应
   	$("#businessCommunicationForm").styleFit();
    trSData.businessCommunicationTable && trSData.businessCommunicationTable.cgetDetail('businessCommunicationForm','','',surveyBack); 
    //初始化上传文件控件
    $.getScript('projectjs/data/form-multiple-upload.demo.js').done(function() {
    	FormMultipleUpload.init();
    });
    App.setPageTitle('业务沟通 - 工程管理系统');
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	  $.getScript('assets/plugins/jstree/dist/jstree.min.js').done(function() {
	    	$.getScript('projectjs/constructmanage/business-communication.js?v='+Math.random()).done(function () {
	        	businessCommunication.init();
	    	});
    	});
    }
    //签字加载方式
	 $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
   	$("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4").handleSignature(); 
 	})
    $("#stepId").val(getStepId());
</script>
<!-- ================== END PAGE LEVEL JS ================== -->