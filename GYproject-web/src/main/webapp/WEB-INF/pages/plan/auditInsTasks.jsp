<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-12 -->
        <div class="col-sm-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">待安装任务审批列表</h4>
                </div>
                <div class="panel-body">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="planAuditTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th></th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>下单时间</th>
                                <th>工程类型</th>
                                <th>出资方式</th>
                                <th>工程地点</th>
                                <th>审核</th>
                                <th></th>
                                <th></th>

                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('安装任务审批 - 工程管理系统');

    var searchData = {};
    var menuId = "110502";
    searchData.menuId = menuId;
    var planAuditTable;
    var handleProjectPlanAudit = function() {
    	"use strict";
        if ($('#planAuditTable').length !== 0) {
            planAuditTable=$('#planAuditTable').on( 'init.dt',function(){
                $(this).bindDTSelected(trSelectedBack,false);
       			//隐藏遮罩
       			$('#planAuditTable').hideMask();
       			$("#planAuditTable_filter input").attr("placeholder","工程编号");
       			//搜索监听
       			searchMonitor();
                //跳转链接
                if (crossPageBus) {
                    getSidebarMenu(11, true);
                    checkSidebarMenu(crossPageBus.hash)
                    //跳转后销毁对象
                    crossPageBus = null
                }


            }).on( 'draw.dt', function () {
            	auditBtnMonitor();
            }).DataTable({
            	language: language_CN,
                lengthMenu: [18],
                dom: 'Bfrtip',
                buttons: [
                    { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }
                ],
                //启用服务端模式，后台进行分段查询、排序
    			serverSide:true,
                ajax: {
                    url: 'auditInstTasks/getAuditDataList',
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
                    {"data":"projId"},
    				{"data":"projNo",responsivePriority:1},
    				{"data":"projName",responsivePriority:3},
    				{"data":"instTaskDate",responsivePriority:4},
    				{"data":"projectTypeDes",responsivePriority:5},
    				{"data":"contributionModeDes",responsivePriority:6},
    				{"data":"projAddr",responsivePriority:7},
    				{"data":"mrAuditLevel",responsivePriority:2},
    				{"data":"overdue",className:"none never" },
    				{"data":"isSpecialSign",className:"none never",
    					"createdCell": function (td, cellData, rowData, row, col) {
    						if(cellData=='1'){
    							$(td).parent().children().css("color", "rgb(255, 99, 95)");
    						}
    					}
      				}
    			],
    			columnDefs: [{
                    "targets":0,
                    "visible":false
                },{
    				"targets": 7,
    				"render": function ( data, type, row, meta ) {
    					if(type==="display"){
    						var html = getAuditLevelHtml(data,row.level,row.projId,"110513");
    						return html;
    					}else{
    						return data;
    					}
    				},
    			},{
                    'targets':3,
                    "render": function ( data, type, row, meta ) {
                        if(data!=="" && data!==null){
                            return format(data,"all");
                        }else{
                            return data;
                        }
                    },
                }],
    			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
    				if(aData.overdue){
    					$(nRow).addClass("expired-proect");
    				}
    			}
            });
        }
    };
    //查询监听
    var searchMonitor = function(){
    	$(".searchBtn").off("click").on("click",function(){
    		var url = "#auditInstTasks/instSearchPopPage";
    		var popoptions = {
    			title: '查询',
    			content: url,
    			accept: searchDone
    		};
    		//加载弹屏
    		$("body").cgetPopup(popoptions);
    	});

    	$("#planAuditTable_filter input").on("change",function(){
    		searchData.menuId = menuId;
    		searchData.projNo = $("#planAuditTable_filter input").val();
    		//传入false  则不选中行
    		$("#planAuditTable").cgetData(false, function(){
    	   		//跳转到审核页面
    			auditBtnMonitor();
    		}); //列表重新加载
    	});
    	//基础条件查询添加回车事件
    	$('#planAuditTable_filter input').on('keyup', function(event) {
    		 if (event.keyCode == "13") {
    			$(this).change();
    		 }
        });
    };

    var searchDone = function(){

    	searchData = $("#searchForm_planAudit").serializeJson();
    	searchData.projNo = $("#planAuditTable_filter input").val();
    	searchData.menuId = menuId;
        $("#planAuditTable").cgetData(false, function(){
       		//跳转到审核页面
        	auditBtnMonitor();
    	});  //列表重新加载
        
    }
    
    /**
     * 加载审核屏
     */
    var auditBtnMonitor = function(){
    	$(document).off("click", ".level").on("click", ".level", function(){
    		var isAudit = "0";//未审核过
    		if($(this).is(".disabled")) return false;
    		if($(this).is(".passed, .refused")){
    			isAudit = "1";//已审核过
    		}
    		var projId = $(this).attr("data-rid");
    		var currentLevel = $(this).attr("data-level");
    		var data = {"projId":projId,"currentLevel":currentLevel,"isAudit":isAudit};
    		$("#ajax-content").cgetContent("auditInstTasks/auditPage",data);
    	});
    };
    $(function(){
        $.getScript("js/ellipsis.js").done(function(){
            handleProjectPlanAudit();
        })
    })
    var trSelectedBack = function(v, i, index, t, json){
        $('#projId').val(json.projId);

    }
</script>
<!-- ================== END PAGE LEVEL JS ================== -->