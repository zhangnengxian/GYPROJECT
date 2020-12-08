<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

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
                    <h4 class="panel-title">待资料归档工程列表</h4>
                </div>
                <div class="panel-body">
                    <input type="hidden" id="waitHandleProjNo" name="projNo"  value="${projNo }"/>
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    
                    <table id="connectConfirmTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th></th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>状态</th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" id="content">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <h4 class="panel-title">资料归档信息</h4>
			    </div>
			    <div class="panel-body" id="connect_confirm_panel_box"></div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('资料归档 - 工程管理系统');
   
    
    /* $.getScript('projectjs/complete/connect-confirm.js?v=1010').done(function () {
    	connectConfirm.init();
	}); */
    var searchData={menuId:"110707"}; //查询条件
    /**
     * 加载工程列表
     */
    var showLength; //显示长度
    //判断是否是手机端，如果是手机端显示8位，不是则显示10位
    if(_inApk){
    	showLength=8;
    }else{
    	showLength=10;
    }
    var handleConnectConfirm = function() {
        searchData.projNo=$("#waitHandleProjNo").val();
    	"use strict";
        if ($('#connectConfirmTable').length !== 0) {
        	$('#connectConfirmTable').on( 'init.dt',function(){
       			//默认选中第一行
        		$(this).bindDTSelected(trSelectedBack,true);
       			//加载页面 
       			$("#connect_confirm_panel_box").cgetContent("connectConfirm/viewPage");
       			//隐藏遮罩
       			$('#connectConfirmTable').hideMask();
       			$("#connectConfirmTable_filter input").attr("placeholder","工程编号");
           
       			//绑定单击事件 弹出搜索框
       			searchMonitor();
       			//交接确认事件监听
       			connectMonitor();
       			setTimeout(function(){
       			    $("#connectConfirmTable").DataTable().columns.adjust();
       			    //$("#workOrderTable").DataTable().responsive.recalc();
       			}, 0);
       			//跳转链接
       			if (crossPageBus) {
       				getSidebarMenu(11, true);
       				checkSidebarMenu(crossPageBus.hash)
       					//跳转后销毁对象
       				crossPageBus = null
       			}
        	}).DataTable({
            	language: language_CN,
                lengthMenu: [18],
                dom: 'Bfrtip',
                buttons: [
                    { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                    { text: '归档', className: 'btn-sm btn-query business-tool-btn connectBtn' }
                    ],
                //启用服务端模式，后台进行分段查询、排序
    			serverSide:true,
                ajax: {
                    url: 'connectConfirm/queryConnectConfirm',
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
                bStateSave: true,
                select: true,  //支持多选
                columns: [
                    {"data":"projId",className:"none never"},
    				{"data":"projNo"},
    				{"data":"projName"},
    				{"data":"projStatusDes"},
    				{"data":"overdue",className:"none never"}
    				],
    			columnDefs: [{
    				'targets':0,
    				 'visible':false
    			},{
    				"targets":2,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: showLength, 	//截取多少字符（或汉字）
    					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    				})
    			},{
    				"targets":3,
    				 "orderable":false
    			}],
    			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
    				if(aData.overdue){
    					$(nRow).addClass("expired-proect");
    				}
    			}
            });
        }
    }


    /**
     * 弹屏监听方法
     */
    searchMonitor = function(){
    	$('.searchBtn').on('click',function(){
    		var url = "#connectConfirm/projectSearchPopPage";
    		//加载弹屏
    		$('body').cgetPopup({
    			title: '查询',
    			content: url,
    			accept: searchDone
    		});
    	});

    	// 基础条件查询添加监听
    	$('#connectConfirmTable_filter input').on('change', function() {
    		var projNo = $('#connectConfirmTable_filter input').val();
    		searchData = {};
    		searchData.projNo = projNo;
    		$('#connectConfirmTable').cgetData(true,tableCallBack); // 列表重新加载
    	});
    	// 基础条件查询添加回车事件
    	$('#connectConfirmTable_filter input').on('keyup', function(event) {
    		if (event.keyCode == '13') {
    			$(this).change();
    		}
    	});
    },

    /**
     * 查询弹出屏，点击确定后 
     */
    searchDone = function(){
    	searchData = $('#searchProjectSelfCheck').serializeJson();
    	var projNo = $('#connectConfirmTable_filter input').val();
    	searchData.projNo = projNo;
    	//列表重新加载
        $('#connectConfirmTable').cgetData(true,tableCallBack);  
    },




    /**
     * 选中行时，查询详述
     */
    trSelectedBack = function(v, i, index, t, json){
    	 var menuId = getStepId();
         $("#connect_confirm_panel_box").cgetContent("connectConfirm/viewPage",{projId:json.projId,corpId:json.corpId,menuId:menuId},function(){
        	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
         	t.cgetDetail('connectConfirmRightform','connectConfirm/jointDetail','',tableCallBack);
     	});
    	
    }
  //交接确认监听
    var connectMonitor = function(){
    	$('.connectBtn').off('click').on('click',function(){
    		if($('#connectConfirmTable').find('tr.selected').length>0){
    			//维护按钮显示出来
    			$('.editbtn').removeClass('hidden');
    			//切换可编辑状态
    			$('#connectConfirmRightform').toggleEditState(true);
    		}else{
    			alertInfo('请选择要归档的工程记录！');
    		}
    	});
    }

    /**
     * 初始化工程列表
     */
    var connectConfirm = function () {
    	"use strict";
        return {
            //main function
            init: function () {
            	$.getScript("js/ellipsis.js").done(function(){   
            		handleConnectConfirm();
            	});
            }
        };
    }();
    
    $(function(){
    	connectConfirm.init();
    })
</script>
<!-- ================== END PAGE LEVEL JS ================== -->