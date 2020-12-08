<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
                    <h4 class="panel-title">安装任务工程列表</h4>
                </div>
                 <div class="panel-body">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                     <input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
                    <table id="insttasksListTable" class="table table-striped table-bordered nowrap" data-attach-table="all" width="100%">
                        <thead>
                            <tr>
                            	<th>id</th>
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
			        <h4 class="panel-title">安装任务下达</h4>
			    </div>
			    <div class="panel-body" id="supplemental_contract_panel_box"></div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程项目管理系统 ');
    
    /* $.getScript('projectjs/contract/supplemental-contract.js?'+Math.random()).done(function () {
        supplementalContract.init();
	}); */
	
    
    var constractTable; //工程列表
    var chargeTable;
    var searchData={};  //查询条件
    /**
     * 加载列表
     */
    var handleSupplementContract = function() {
    	'use strict';
        if ($('#insttasksListTable').length !== 0) {
        	constractTable= $('#insttasksListTable').on( 'init.dt',function(){
       			//加载右侧页面
       			$('#supplemental_contract_panel_box').cgetContent('insttasks/viewPage','',function () {
                    $('#insttasksListTable').bindDTSelected(trSelectedBack,true);
                });
       			//隐藏遮罩
       			$('#insttasksListTable').hideMask();
       			$('#insttasksListTable_filter input').attr('placeholder','工程编号');
       			//绑定单击事件 弹出搜索框
       			searchMonitor();
       			//签订监听
       			signMonitor();
       			setTimeout(function(){
       			    $("#insttasksListTable").DataTable().columns.adjust();
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
                    { text: '查询', className: 'btn-sm btn-query searchBtn' },
                    { text: '下达', className: 'btn-sm btn-query business-tool-btn signBtn' }
                ],
              //启用服务端模式，后台进行分段查询、排序
    			serverSide:true,
    			ajax: {
                    url: 'insttasks/queryInsttasks',
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
                        {"data":"projId",className:'none never'},
          				{"data":"projNo"},
          				{"data":"projName"},
          				{"data":"projStatusDes"},
                        {"data":"signBack",
                            className:"none never",
                            "createdCell": function (td, cellData, rowData, row, col) {
                                if(cellData==$("#notThrough").val()){
                                    $(td).parent().children().css("background", "rgb(255, 219, 219)");
                                    //$(td).attr("id",row);
                                }
                            }
                        },
          			],
      			columnDefs: [{
    			 'targets': 0,
    			 'visible':false
      			},{
    				"targets":2,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: 8, 	//截取多少字符（或汉字）
    					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    				})
    			}]
    		 });
        }
    };
    /**
     * 弹屏监听方法
     */
    var searchMonitor = function(){
    	$('.searchBtn').off('click').on('click',function(){
    		var url = '#insttasks/instTasksSearchPopPage';
    		//加载弹屏
    		$('body').cgetPopup({
    			title: '查询',
    			content: url,
    			accept: searchDone
    		});
    	});
    	//基础条件查询添加监听
    	$('#insttasksListTable_filter input').on('change',function(){
    		var projNo = $('#insttasksListTable_filter input').val();
    		searchData.projNo = $("#insttasksListTable_filter input").val();
    		$('#insttasksListTable').cgetData(false,contractCallBack);  //列表重新加载
    	});
    	//基础条件查询添加回车事件
    	$('#insttasksListTable_filter input').on('keyup', function(event) {
    	    if (event.keyCode == '13') {
    	    	$(this).change();
    	    }
    	});			
    };

    /**
     * 查询弹出屏，点击确定后 
     */
    var searchDone = function(){
    	//查询条件
    	searchData = $('#searchForm_contract').serializeJson();
    	var conNo = $('#insttasksListTable_filter input').val();
    	searchData.conNo = conNo;
    	//列表重新加载
        $('#insttasksListTable').cgetData(true,contractCallBack);
    }
    
    var contractCallBack=function(){
    	var len = $('#insttasksListTable').DataTable().ajax.json().data ? $('#insttasksListTable').DataTable().ajax.json().data.length : $('#insttasksListTable').DataTable().ajax.json().length;
    	if(len<1){
    		 //清空右侧记录
    		 $('#instTasksForm')[0].reset();
    		 $(':input','#instTasksForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
    	 }
    }
    
    /**
     *选中行时，查询详述
     */
    var trSelectedBack = function(v, i, index, t, json){
    	$(':input','#instTasksForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');

        t.cgetDetail('instTasksForm','insttasks/viewInsttasks','',getDetailBack);
    }
    var getDetailBack = function(data){
    }

    /**
     * 下达按钮监听方法
     */
    var signMonitor=function(){
    	$('.signBtn').off('click').on('click',function(){
            if($('#insttasksListTable').find('tr.selected').length>0){
                //表单可编辑
                $("#instTasksForm").toggleEditState(true);
                //按钮显示
                $(".editbtn").removeClass("hidden");
            }else{
                alertInfo('请选择工程计划！');
            }
    	});
    };

    /**
     * 初始化列表
     */
    var supplementalContract = function () {
    	'use strict';
        return {
            //main function
            init: function () {
            	$.getScript("js/ellipsis.js").done(function(){
            		handleSupplementContract();
            	});
            }
        };
    }();
    
    $(function(){
    	 supplementalContract.init();
    })
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->