<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse tabs-mixin">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                	</div>
                	<ul class="nav nav-tabs">
						<li class="active"><a href="#rectification_record" data-toggle="tab"id ="rectificationList">延期申请列表</a></li>
						<li class=""><a href="#rectification_info" data-toggle="tab" id="rectificationInfo">延期申请记录</a></li>
					</ul>
                </div>
                <div class="panel-body" id="box">
                	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                	<div class="tab-content">
	                	<div class="tab-pane fade active in btn-top active" id="rectification_record" >
	                		<table class="table table-hover table-striped table-bordered nowrap" id="rectifyNoticeTable" width="100%">
	                			<thead>
	                			<tr>
	                				<th></th>
	                				<th>申请时间</th>
	                				<th>申请人</th>
	                				<th>延期天数</th>
	                				<th>状态</th>
	                			</tr>
	                			</thead>
	                		</table>
	                	</div>
	                	<div class="tab-pane fade in btn-top" id="rectification_info">
	                		<div class=" f-r p-b-10 editbtn">
	    					 	<a href="javascript:;" class="btn btn-default btn-confirm btn-sm m-l-5 checkRole saveCustomer" onclick="" >保存</a>
	        				 	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 checkRole giveUpSave">放弃</a>
						 	</div>
						 	 <div class="clearboth form-box p-b-10">
						 	 <input type="hidden" id="imgUrl" name="imgUrl" class="imgUrl" value="${imgUrl }">
						 	 <input type="hidden" id="IsSignature" name=IsSignature  value="${IsSignature }">
						 	 	<form class="form-horizontal" id="rectifiyNoticeForm" action="delay/saveDelay">
						 	 	<input type="hidden" value="" id="projId" name="projId"/>
						 	 	<input type="hidden" value="" id="corpId" name="corpId"/>
						 	 	<input type="hidden" value="" id="corpName" name="corpName"/>
						 	 	<input type="hidden" value="" id="tenantId" name="tenantId"/>
						 	 	<input type="hidden" value="" id="orgId" name="orgId"/>
						 	 	<input type="hidden" value="" id="deptId" name="deptId"/>
						 	 	<input type="hidden" value="" id="adId" name="adId" class="addClear"/>
							 		<div class="form-group col-xs-12">
								        <label class="control-label" for="projNo">工程编号</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"  value="" id="projNo" name="projNo" />
					        			</div>
			   						</div>
							 		<div class="form-group col-xs-12">
								        <label class="control-label" for="projId">工程名称</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"  value="" id="projName" name="projName" />
					        			</div>
			   						</div>
			   						<div class="form-group col-sm-12">
					                	<label class="control-label" for="">工程地点</label>
					                    <div> 
					                    	<input type="text" class="form-control input-sm field-not-editable" id="projAddr" name="projAddr"  >
					  				    </div> 
					  				</div>
					  				<div class="form-group col-md-12 col-sm-12">
								        <label class="control-label" for="constructionUnit">施工单位</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"  value="" id="constructionUnit" name="constructionUnit"  />
					        			</div>
			   						</div>
					  				<div class="form-group col-md-12 col-sm-12">
								        <label class="control-label" for="suName">监理单位</label>
					        			<div>
					        				<input type="text" class="form-control input-sm field-not-editable"  value="" id="suName" name="suName"  />
					        			</div>
			   						</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="" >计划开工</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-not-editable" value="" id="planStatus" name="planStatus" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="" >计划竣工</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-not-editable" value="" id="planEnd" name="planEnd" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="" >实际开工</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-not-editable" value="" id="statusDate" name="statusDate" />
										</div>
									</div>
									<div class="form-group col-xs-12">
										<label class="control-label" for="">当前进度</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  value="" id="progress"  name="progress" data-parsley-required="true"/>
										</div>
									</div>
			   						 <div class="form-group col-md-12 col-sm-12">
								        <label class="control-label" for="">延期原因</label>
								        <div>
								        	<textarea class="form-control input-sm  field-editable addClear " row="15"  id="why" name="why" data-parsley-required="true" data-parsley-maxlength="1000"></textarea>
								        </div>
								    </div>
									<div class="form-group col-md-12 col-sm-12">
										<label class="control-label" for="">保证措施</label>
										<div>
											<textarea class="form-control input-sm  field-editable addClear " row="15"  id="measures" name="measures" data-parsley-required="true" data-parsley-maxlength="1000"></textarea>
										</div>
									</div>
								    <div class="form-group col-md-6 col-sm-12 resident">
								        <label class="control-label" for="">计划延期</label>
								        <div>
								        	 <input type="number" class=" form-control input-sm field-editable addClear " min="0" id="numberday"  name="numberday" data-parsley-required="true" data-parsley-maxlength="10" value="" >
		        							  <a href="javascript:;" class="btn btn-sm btn-default">天</a>
								        </div>
								    </div>

								    <div class="form-group col-md-6 col-sm-12 allSign applicantsig">
										<label class="control-label signature-tool" for="applicantsig">申请人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" id="applicantsig" name="applicantsig" value="" class="sign-data-input">
											<img class="applicantsig" alt="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
								    </div>
							 	</form>
						 	</div>
	                   </div>
                	</div>
            	</div>
			</div>
                
        </div>
        <!-- begin col-6 -->
       <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <h4 class="panel-title">预览区</h4>
			    </div>
			    <div class="panel-body">
			        <div class="iframe-big-box ">
			        	<div class="iframe-report-box ">
		                 	<iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:0; overflow-y:auto;" scrolling="no"></iframe>
		                </div>
			        </div>
			    </div>
			</div>
        </div>
        <!-- end col-6 -->
	</div>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('延期申请 - 工程施工管理系统 ');
    $('#rectifiyNoticeForm').toggleEditState(false);
    $("#rectifiyNoticeForm").styleFit();
    $(".editbtn").addClass("hidden");
    var searchData={},
        recordData={},
        rectifyNoticeTable,
        savestus=0;
    //整改列表
    var cptType;  //定义类型
  //是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$("#projId").val(projJson.projId);
    	$("#projAddr").val(projJson.projAddr);
    	$("#projName").val(projJson.projName);
    	$("#projNo").val(projJson.projNo);
    	$("#suName").val(projJson.suName);
    	$("#constructionUnit").val(projJson.cuName);
    	$("#tenantId").val(projJson.tenantId);
    	$("#deptId").val(projJson.deptId);
    	$("#orgId").val(projJson.orgId);
    	$("#corpId").val(projJson.corpId);
    	$("#corpName").val(projJson.corpName);
    }

	$("#signBtn_1").handleSignature();
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    	
    });
	//初始化列表
    var handleRectifyNotice = function() {
        "use strict";
        var projId = $("#projId").val();
        if(projId=='' || projId==undefined ){
            projId="-1";
        }
        searchData.projId = projId;
        if ($('#rectifyNoticeTable').length !== 0) {
            rectifyNoticeTable=$('#rectifyNoticeTable').on( 'init.dt',function(){
                //默认选中第一行
                $(this).bindDTSelected(trSelectedBack,true);
                //隐藏遮罩
                $('#rectifyNoticeTable').hideMask();
                //增加监听
                addMonitor();
                //修改监听
                modifyMonitor();
                //加载打印屏
                showReport1();
                queryCheckRole();
                //推送监听
                pushRectifyNotice();
                //查询方法
                searchMonitor();
            }).DataTable({
                language: language_CN,
                lengthMenu: [18],
                // dom: 'Brtip',
                dom: 'Brt',
                buttons: [
                    // { text: '查询', className: 'btn-sm btn-query searchBtn' },
                    { text: '增加', className: 'btn-sm btn-query checkRole addBtn' },
                    { text: '修改', className: 'btn-sm btn-query checkRole updateBtn business-tool-btn' },
                    { text: '推送', className: 'btn-sm btn-query checkRole pushBtn' }
                ],
                //启用服务端模式，后台进行分段查询、排序
                serverSide:true,
                ajax: {
                    url: 'delay/getDateList',
                    type:'post',
                    data: function(d){
                        $.each(searchData,function(i,k){
                            d[i] = k;
                        });

                    },
                    dataSrc: 'data'
                },
                responsive: {
                    details: {
                        renderer: function ( api, rowIdx, columns ){
                            return renderChild(api, rowIdx, columns);
                        }
                    }
                },
                select: true,  //支持多选
                columns: [
                    {"data":"adId",className:"none never"},
                    {"data":"addtime"},
                    {"data":"applicant"},
                    {"data":"numberday"},
                    {"data":"state"}
                ],
                columnDefs: [{
                    "targets": 0,
                    "visible":false
                },{
                    "targets":4,
                    "render":function(data,type,row,meta){
                        if("1"==data){
                            return "待推送";
						}
                        if("2"==data){
                            return "待审核";
                        }
                        if("3"==data){
                            return "已审核";
                        }
                        if("4"==data){
                            return "不通过";
                        }
                        return data;
                    }
                }],
            });
        }
    }
    //增加
    var addMonitor = function(){
        $(".addBtn").off("click").on("click",function(){
            $(".addClear").val('');
            $('#rectificationInfo').tab('show');
            //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"rectifiyNoticeForm");
            $(".editbtn").removeClass("hidden");
            $("#signBtn_1").resetSign();

        });
    }
    //修改监听
    var modifyMonitor = function(){
        $(".updateBtn").off("click").on("click",function(){
            var len=$("#rectifyNoticeTable").find("tr.selected").length;
            if(len>0){
                //根据职务过滤可编辑项
                getSignStatusByPostId(getProjectInfo().post,"rectifiyNoticeForm");
                $(".editbtn").removeClass("hidden");
                $('#rectificationInfo').tab("show");
            }else{
                $("body").cgetPopup({
                    title: '提示',
                    content: '请选择要修改的整改条目!',
                    ahide:true,
                    atext:'确定'
                });
            }
        });
    };

    /**
     * 推送方法
     */
    var pushRectifyNotice = function(){
        $('.pushBtn').off().on('click',function(){
            console.info("click");
            var len=$("#rectifyNoticeTable").find('tr.selected').length;
            if(len>0){
                $("body").cgetPopup({
                    title: "提示信息",
                    content: "请确认推送选中延期申请单？",
                    accept: surePush,
                    newpop: 'new',
                    icon: "fa-check-circle"
                });
            }else{
                alertInfo('请选择要推送的延期申请单！');
            }
        });
    }
    var surePush=function(){
        var adId=trSData.rectifyNoticeTable.json.adId;
        $.ajax({
            type:'post',
            url:'delay/push',
            contentType: "application/json;charset=UTF-8",
            data: adId,
            success:function(data){
                var content = "推送成功！";
                if(data=="false"){
                    content = "推送失败！";
                }else{
                    $('#rectifyNoticeTable').cgetData(true);
                }
                var myoptions = {
                    title: "提示信息",
                    content: content,
                    accept: popClose,
                    chide: true,
                    icon: "fa-check-circle",
                    newpop: 'new'

                }
                $("body").cgetPopup(myoptions);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("数据保存失败！");
            }

        })
    }

    /**
     * 查询弹屏监听方法
     */
    var searchMonitor=function(){
        $(".searchBtn").off("click").on("click",function(){
            var url = "#delay/delaySearchPopPage";
            //加载弹屏
            $("body").cgetPopup({
                title: '查询',
                content: url,
                accept: searchDone
            });
        });
    }
    /**
     * 查询弹出屏，点击确定后
     */
    var searchDone = function(){
        //查询条件
        searchData = $("#searchForm_rectifyNotice").serializeJson();
        searchData.projId = $("#projId").val();
        //列表重新加载
        $("#rectifyNoticeTable").cgetData(true,rectifyNoticeCallBack);
    }
    //保存整改信息
    $('.saveCustomer').off().on("click",function(){
        $("#rectifiyNoticeForm").cformSave('rectifyNoticeTable',saveBack,false,false);

    });
    var saveBack = function(data){
        var content = "数据保存成功！";
        if(data === "false"){
            content = "数据保存失败！";
        }else{
            var adId = data;
            $('#adId').val(adId);
            $('#rectifiyNoticeForm').toggleEditState(false);
            $(".editbtn").addClass("hidden");
            showReport1();
        }
        var myoptions = {
            title: "提示信息",
            content: content,
            accept: popClose,
            chide: true,
            icon: "fa-check-circle"
        }
        $("body").cgetPopup(myoptions);
    };

    //点击放弃
    $('.giveUpSave').off().on('click',function(){
        $("#rectifiyNoticeForm").toggleEditState(false);
        $('#rectificationList').click();
    });


    /** 选中行时，查询详述
     */
    var trSelectedBack = function(v, i, index, t, json){
        //将查询详述显示到相应的输入框内
        t.cgetDetail('rectifiyNoticeForm','delay/viewDelay','',queryBack);
        //传false表示不可编辑
        $("#rectifiyNoticeForm").toggleEditState(false);
        $(".editbtn").addClass("hidden");
        showReport1();
    }
    var queryBack=function(json){
        if($("#rnStatus").val()=='3'){
            $(".rectifyNoticeBack").removeClass("hidden");
        }else{
            $(".rectifyNoticeBack").addClass("hidden");
        }
        $("#rectifiyNoticeForm").styleFit();
        showReport1();
    }




    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
   	 var cptPath = '<%=basePath%>';
   	 //右屏导入帆软报表
	 var showReport1 = function(){
	     var adId=$("#adId").val();
	 	var src="";

			src =  cptPath + "/ReportServer?reportlet=constructmanage/delay.cpt";
		    src += "&adId="+adId+"&imgUrl="+$('.imgUrl').val();
		$("#mainFrm").attr("src",src);  
   	 } 

	$(function(){
        $.getScript("js/ellipsis.js").done(function(){
            handleRectifyNotice();
        });
	})
</script>
<!-- ================== END PAGE LEVEL JS ================== -->