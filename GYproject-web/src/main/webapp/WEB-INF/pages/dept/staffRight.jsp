<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== END PAGE LEVEL STYLE ================== -->
<div class="toolBtn f-r p-b-10 scbtn">
    <a href="javascript:;" class="btn btn-default btn-sm m-l-5 btn-query saveBtn">保存</a>
    <a href="javascript:;" class="btn btn-default btn-sm m-l-5 btn-warn cancelBtn">放弃</a>
</div>
<div class="form-box clearboth">
	<input type="hidden"  id="loginId"  name="loginId"  value="${staffId}"/>
	<input class="deptType" type="hidden" name="deptType"  value="${deptType}"/>
	<input class="loginStaff" type="hidden" id="loginStaffPost"  value="${sessionScope.session_loginInfo.post}"/>
	<form class="form-horizontal" id="staffManageform">
	<input type="hidden" id="aspectRatio" value="1" />
	<input type="hidden" id="markOfDelet"  name="markOfDelet" />
		<input type="hidden" id="post"  name="post" />
		<input  type="hidden" id="orgId"  name="orgId" />
		<input  type="hidden" id="belongCorpId"  name="belongCorpId" />
		<input type="hidden"  name="tenantId" id="tenantId"/>
		<div class="form-group col-md-6">
			<label class="control-label" for="staffNo">员工编号</label>
			<div>
				<input class="form-control input-sm field-not-editable"  name="staffNo" id="staffNo" data-parsley-required ='true'>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="staffName">员工姓名</label>
			<div>
				<input class="form-control input-sm field-editable"  name="staffName" id="staffName">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="loginAccount">登录账号</label>
			<div>
				<input class="form-control input-sm field-editable"  name="loginAccount" id="loginAccount" data-parsley-required ='true'>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="password">登录密码</label>
			<div>
				<input class="form-control input-sm field-editable" type ='password'  name="password" id="password" data-parsley-required ='true'>
			</div>
		</div>
		<div class="form-group col-md-6 hidden password1">
			<label class="control-label" for="password1">明文密码</label>
			<div>
				<input class="form-control input-sm field-not-editable" type ="text"  name="password1" id="password1" >
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="idCardNo">身份证号</label>
			<div>
				<input class="form-control input-sm field-editable"  name="idCardNo" id="idCardNo" data-parsley-maxlength ='18'>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="postName">职务</label>
			<div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="postName" name="postName" data-parsley-required="true" >
		           <a id="postPop" class=" btn btn-default btn-sm m-l-10 " title="选择职务"><i class="fa fa-search"></i></a>
		    </div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="addr">地址</label>
			<div>
				<input class="form-control input-sm field-editable"  name="addr" id="addr">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="homePhone">家庭电话</label>
			<div>
				<input class="form-control input-sm field-editable"  name="homePhone" id="homePhone">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="phone">办公电话</label>
			<div>
				<input class="form-control input-sm field-editable"  name="phone" id="phone">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="mobile">手机</label>
			<div>
				<input class="form-control input-sm field-editable"  name="mobile" id="mobile" data-parsley-maxlength ='11'>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="qq">QQ</label>
			<div>
				<input class="form-control input-sm field-editable"  name="qq" id="qq">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="wechat">微信号</label>
			<div>
				<input class="form-control input-sm field-editable"  name="wechat" id="wechat">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="email">电子邮件</label>
			<div>
				<input class="form-control input-sm field-editable"  name="email" id="email">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="email">从属单位类型</label>
			<div>
				<select id="unitType" name="unitType" class="form-control input-sm field-editable">
					<option value=""></option>
					<c:forEach var="enums" items="${unitTypes}">
						<option value="${enums.value}" data-c='${enums.type}'>${enums.message}</option>
					</c:forEach>
				</select>
				<!-- <input class="form-control input-sm field-editable"  name="unitType" id="unitType"> -->
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="deptName">所属部门</label>
			<div>
				<input type="hidden"  id="deptId" name="deptId">
	            <input type="text" class="form-control input-sm field-not-editable" id="deptName"  data-parsley-required ='true'/>
	            <a id="staffDeptTreeId" class="btn btn-default btn-sm m-l-10 " title="选择所属部门"><i class="fa fa-search"></i></a> 
            </div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="staffCertificate">资质证书</label>
			<div>
	            <input type="text" class="form-control input-sm field-not-editable" id="staffCertificate" />
	            <a id="staffCertificateId" class="btn btn-default btn-sm m-l-10 " title="选择资质证书"><i class="fa fa-search"></i></a> 
            </div>
		</div>
		<div class="form-group col-md-6 ">
			<label class="control-label">头像</label>
			<div>
	          <!--   <img type="text" class="form-control input-sm field-not-editable" id="upPhoto"  data-parsley-required ='true'/>  -->
	           <img id="image11" src="" width="100px" height="100px">
	           <input type="text" class="hidden" name=photoStr id="photoStr"/>
	           <input type="text" class="hidden" name=photoUrl id="photoUrl"/>
	           <a id="staffPhoto" class="meterModelBtn btn btn-default btn-sm m-l-10 " title="设置头像"><i class="fa fa-user"></i> 设置头像</a> 
            </div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label signature-tool" for="signPicturePath" style="width: 90px;">签名</label>
			<div>
				<!-- <img class="imageSign" src="" width="50px" height="50px"> -->
				<a href="javascript:;" class="btn btn-sm btn-white disabled" id="signBtn_1"><i class="fa fa-pencil"></i></a>
				<input type="hidden" class="sign-data-input disabled" id="signPictureStr" name=signPictureStr value="">
				<input type="hidden" id="signPicturePath" name="signPicturePath" value="">
				<input type="hidden" class="sign-data-input disabled" id='constructionQaeUrlPath' name='constructionQaeUrlPath'/>
				<input type="hidden" id="signPictureStr_postType" name="signPictureStr_postType" value="" class="disabled">
				<!-- <img class="imageSign" alt="" src="" style="height: 30px"> -->
				<span class="clear-sign disabled"><i class="fa ion-close-circled"></i></span>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="control-label" for="deptName">变更后的部门</label>
			<div>
				<input type="hidden"  id="deptTransfer" name="deptTransfer">
				<input type="text" class="form-control input-sm field-not-editable" id="deptTransferName" name="deptTransferName"  placeholder="默认为所属部门的值"/>
				<a id="selectDeptTreeId" class="btn btn-default btn-sm m-l-10 " title="选择所属部门"><i class="fa fa-search"></i></a>
			</div>
		</div>
		<div class="form-group col-md-12 col-sm-12 clearboth">
		        <label class="control-label">燃气公司</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="belongCorpName" name="belongCorpName" data-parsley-maxlength="1000" data-parsley-required="true"/>
		            <a id="corpNamePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择燃气公司"><i class="fa fa-search"></i></a>
		        </div>
		   </div>
		<ul class="nav nav-tabs p-t-5 p-l-5 clearboth">
			<li class="active"><a href="#tab-1" data-toggle="tab" id="workRole">工作角色</a></li>
			<li class=""><a href="#tab-2" data-toggle="tab" id="noticeRole">通知角色</a></li>
		</ul>
		<div class="tab-content p-l-0 p-r-0 p-t-5">
			<div class="tab-pane fade active in btn-top" id="tab-1" >
				<input type="text" id="searchId" placeholder="角色搜索" onkeydown="onchangeSearch(this.value,event)" style="width: 100%;border:1px solid #def1d1;padding-left: 20px">
				<table id="roleList" class="table table-striped table-bordered nowrap" width="100%">
					<thead>
		            	<tr>
		                	<th>角色ID</th>
		                    <th>角色编号</th>
		                    <th>角色名称</th>
		                </tr>
		            </thead>
		         </table>
			</div>
			<div class="tab-pane fade  btn-top" id="tab-2" >
				<table id="noticeRoleList" class="table table-striped table-bordered nowrap" width="100%">
					<thead>
		            	<tr>
		                	<th>角色ID</th>
		                    <th>角色编号</th>
		                    <th>角色名称</th>
		                </tr>
		            </thead>
		         </table>
			</div>
		</div>
		 <label class="control-label hidden" for="staffId">员工id</label>
		 <input type="text" class="form-control input-sm hidden"  id="staffId" name="staffId"  />
		 <label class="control-label hidden" for="corpId">公司id</label>
		 <input type="text" class="form-control input-sm hidden"  id="corpId" name="corpId"  />
		 <label class="control-label hidden" for="createStaffId">创建者id</label>
		 <input type="text" class="form-control input-sm hidden"  id="createStaffId" name="createStaffId"  />
		 <label class="control-label hidden" for="createTime">创建时间</label>
		 <input type="text" class="form-control input-sm hidden"  id="createTime" name="createTime"  />
		 <label class="control-label hidden" for="roleIds">角色ids</label>
		 <input type="text" class="form-control input-sm hidden"  id="roleIds" name="roleIds"  />
 			<label class="control-label hidden" for="noticeRoleIds">通知角色ids</label>
		 <input type="text" class="form-control input-sm hidden"  id="noticeRoleIds" name="noticeRoleIds"  />

	</form>
</div>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    $("#staffManageform").hideMask();
    //参数传false时，表单不可编辑
    $("#staffManageform").toggleEditState(false).styleFit();
    $("#staffPhoto").addClass("disabled");
    $("#signPicture").addClass("disabled");
    
    //表单样式适应
    $("#staffManageform").styleFit();
    //按钮隐藏
    $(".scbtn").addClass("hidden");
    
    
	trSData.t && trSData.t.cgetDetail('staffManageform','staff/queryStaffRole','',detailBack);
    
    if(!trSData.staffList.t){
    	handleRoleList();
    }
    
    /* //加载角色列表
    $('#roleList').on("init.dt", function(){
    	bindTableSelectRoleListRight();
    }).DataTable({
		language : language_CN,
		dom : 'Bfrtip',
		buttons : [],
		paging : false,
		info: false,
		// 启用服务端模式
		serverSide : true,
		ajax : {
			url : 'auth/queryAllRole',
			type : 'post',
			data : function(d) {
				$.each(function(i, k) {
					d[i] = k;
				});
			},
			dataSrc : 'data'
		},
		select : true, // 支持多选
		select: {
            style: 'multi'
        },
		searching: false,
		columns: [
				{"data":"roleId",className:"none never"},
				{"data":"roleCode"},
				{"data":"roleName"}
		],
		columnDefs : [ {
			"targets" : 0,
			"visible" : false
		} ]
	}); */
  
   /*  $('#roleList').on( 'draw.dt', function () {
    	$('#staffList').bindDTSelected(trSelectedBack,true);
    	return false;
    }); */
    
  	
    $("#staffDeptTreeId").on("click",function(){
		var url = '#staff/staffDeptTree';
		var popoptions = {
			title: '所属部门',
			content: url,
			accept: staffDeptTreeDone
		};
		$("body").cgetPopup(popoptions);
	});



    $("#selectDeptTreeId").on("click",function(){
		var url = '#staff/deptTreePop';
		var popoptions = {
			title: '所属部门',
			content: url,
			accept: selectDeptTreeDone
		};
		$("body").cgetPopup(popoptions);
	});

    function selectDeptTreeDone(){
        var deptId=$("#selDeptId").val();
        var detpName = $("#selDeptName").val();
        $("#deptTransfer").val(deptId);
        $("#deptTransferName").val(detpName);
    }






    $("#staffPhoto").on("click",function(){
		var url = '#staff/staffPhoto';
		var popoptions = {
			title: '设置头像',
			content: url,
			accept: staffPhotoDone,
			size:'super'
		};
		$("body").cgetPopup(popoptions);
	});
    
    
    
    function staffPhotoDone(){
    	 var $image = $('.img-container > img');
    	 var dataURL = $image.cropper("getCroppedCanvas", {
    		  width: 100,
    		  height: 100
    		});
    	 var imgurl=dataURL.toDataURL("image/png",1.0);//这里转成base64 image，img的src可直接   	 
    	 $("#photoStr").val(imgurl);
  
    	 $("#image11").attr("src", imgurl);
    }
    function staffDeptTreeDone(){
    	var deptId=$("#selDeptId").val();
    	var detpName = $("#selDeptName").val();
    	$("#deptId").val(deptId);
    	$("#deptName").val(detpName);
    }
    
  /* //绑定表格行选中事件
    function bindTableSelectRoleListRight() {
    	$('#roleList').DataTable().on( 'select', function ( e, dt, type, indexes ) {
    		var roleIds = '';
    		var data = $('#roleList').DataTable().rows('.selected').data();
    		$.each(data,function(key, val){
    			if(roleIds.length > 0) roleIds +=",";
    			roleIds += val.roleId;
			});
    		$("#roleIds").val(roleIds);
       	});
    } */
  
    var roleIds = '';
	var postNames='';
    $("#postPop").off("click").on("click",function(){
    	$("body").cgetPopup({
    		title: '职务选择',
    		nocache:false,
    		content: '#staff/postPop',
     	    accept: function(){
    	/*      	if($("#postTablePop tr.selected").length < 1){
    	    		$("body").cgetPopup({
    	    			title: '提示', 
    	    			content: "请选择职务！", 
    	    			newpop: 'second', 
    	    			accept: innerClose
    	    		});
    	    		return false;
    	    	}else{
    	    		var data = $('#postTablePop').DataTable().rows('.selected').data();
    	    		$.each(data,function(key, val){
    	    			if(roleIds.length > 0) {
    	    				roleIds +=",";
    	    				postNames+=",";
    	    			}
    	    			roleIds += val.id;
    	    			postNames+=val.postName;
    	    		});
    	    		$("#post").val(roleIds);
    	    		$("#postName").val(postNames);
    	    		alert(roleIds) ;
    	    	}  */
    	    },
    	  chide		: 'true'
    	});
   	});
	
    //资质证书选择
    $("#staffCertificateId").off("click").on("click",function(){
    	var url = '#staff/staffCertificatePop';
		var popoptions = {
			title: '资质证书选择',
			content: url,
			accept: staffDeptTreeDone
			/* ahide: 'true',						//是否隐藏确定按钮
		    chide: 'true',							//是否隐藏取消按钮 */
		};
		$("body").cgetPopup(popoptions);
    })
    
  
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	var signatures = $("#signBtn_1");
    	signatures.handleSignature(false);        	    	
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
    	    		$("#belongCorpId").val(roleIds);
    	    		$("#belongCorpName").val(postNames);
    	    		
    	    	}
    	    }
    	});
  	});
    
    
<!-- ================== END PAGE LEVEL JS ================== -->