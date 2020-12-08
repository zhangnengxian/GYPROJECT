<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

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
                    <h4 class="panel-title">待签合同工程列表</h4>
                </div>
                <div class="panel-body">
                    <input type="hidden" id="waitHandleProjNo" name="projNo"  value="${projNo }"/>
                    <input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
	            	<input type="hidden" id="stepId" name="stepId" value="${stepId }">
	            	<input type="hidden" id="curStepId" name="stepId" value="${curStepId }">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>
                    <table id="constructContractTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th>工程ID</th>
                            	<th>工程编号</th>
                                <th>工程名称</th>
                                <th>确定造价(元)</th>
                                <th>状态</th>
                                <th>剩余时限(天)</th>
                                <th></th>
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
			        <h4 class="panel-title">合同签订</h4>
			    </div>
			    <div class="panel-body" id="construct_contract_panel_box"></div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('安装合同 - 工程项目管理系统 ');
    var constractTable; //工程列表
    var chargeTable;
    var searchData={};  //查询条件
    var scaleTable;
    var menuId = "110402";
	searchData.menuId= menuId;
    /**
     * 签订按钮监听方法
     */
    var signMonitor=function(){
    	$('.signBtn').off('click').on('click',function(){
    		if($('#constructContractTable').find('tr.selected').length>0){
    			$("#contractDetailform a").attr("disabled",false);
                $(".selectDisabled").removeClass("disabled");
    			$("#changeFlag").val("1");//使户数和每户金额change事件生效
    			//表单可编辑
    			$('#contractDetailform').toggleEditState(true);
    			$('#scaleTableForm').toggleEditState(true);
    			//按钮显示
    			$('.editbtn').removeClass('hidden');
                if(!$("#signDate").val()){
                    var sysDate = timestamp($("#sysDate").val());
                    $("#signDate").val(format(sysDate,"default"));
                }
    			defaultValue();
    		}else{
    			alertInfo('请选择要签订合同的工程记录！');
    		}
    	});
    };
    /**
     * 加载工程列表
     */
    var handleConstructContract = function(){
        searchData.projNo=$("#waitHandleProjNo").val();
        'use strict';
        if ($('#constructContractTable').length !== 0) {
        	constractTable= $('#constructContractTable').on( 'init.dt',function(){
       			//默认选中第一行
        		$(this).bindDTSelected(trSelectedBack,true);
       			 //加载右侧页面
       			var menuId = getStepId();
       			/*$('#construct_contract_panel_box').cgetContent('constructContract/viewPage',{projId:'',menuId:menuId},function(){
       			//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
       	        	trSData.t &&trSData.t.cgetDetail('contractDetailform','constructContract/viewContract','',dataBack); 
       			});*/
                //加载页面
                var len = $('#constructContractTable').DataTable().ajax.json().data ? $('#constructContractTable').DataTable().ajax.json().data.length : $('#constructContractTable').DataTable().ajax.json().length;
                if(len<1){
                    $("#construct_contract_panel_box-mixin").cgetContent("constructContract/viewPage");
                }
       			//隐藏遮罩
       			$('#constructContractTable').hideMask();
       			$('#constructContractTable_filter input').attr('placeholder','工程编号');
       			//绑定单击事件 弹出搜索框
       			searchMonitor();
       			//签订监听
       			signMonitor();
       			
       			setTimeout(function(){
       			    $("#constructContractTable").DataTable().columns.adjust();
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
                     { text: '查询', className: 'btn-sm btn-query searchBtn' },
                     { text: '签订', className: 'btn-sm btn-query signBtn' }
                 ],
                 //启用服务端模式，后台进行分段查询、排序
    			 serverSide:true,
                 ajax: {
                    url: 'constructContract/queryProject',
                    type:'post',
                    data: function(d){
                       	$.each(searchData,function(i,k){
                       		d[i] = k;
                       	});
                       	
                    },
                    dataSrc: 'data'
                 },
                 //ajax: 'projectjs/contract/json/construct_contract.json',
                 responsive: {
                	details: {
                		renderer: function ( api, rowIdx, columns ){
                			return renderChild(api, rowIdx, columns);
                		}
                	}
                },
                 select: true,  //支持多选
                 columns: [
    				 {'data':'projId',className:'none never'}, 
    	  			 {'data':'projNo'}, 
    				 {'data':'projName'},
    				 {'data':'confirmTotalCost',className:'text-right'},
    				 {'data':'projStatusDes'},
    				 {"data":"workDayDto"},
    				 {"data":"signBack",
    						className:"none never",
    						"createdCell": function (td, cellData, rowData, row, col) {
    							if(cellData==$("#notThrough").val()){
    								$(td).parent().css("background", "rgb(255, 219, 219)");
    								//$(td).attr("id",row);
    							}
    						}
    						},
    				{"data":"overdue", className:"none never"}		
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
    				},{
    				 'targets':3,
    				 "render": function ( data, type, row, meta ) {
    						if(data!=="" && data!==null){
    							return parseFloat(data).toFixed(2);
    						}else{
    							return data;
    						}
    					},
    			 	},{
    					"targets":4,
    					 "orderable":false
    				},{
    					"targets":5,
    					 "orderable":false,
    					 "render":function(data,type,row,meta){
    						 if(data!=null){
    							 return data.haveDays;
    						 }else{
    							 return 0;
    						 }
    					 }
    				}
    			 ],
    			 fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
    					if(aData.overdue){
    						$(nRow).addClass("expired-proect");
    					}
    				}
    			 
            });
        }
    };
    
    
    /**
     * 弹屏监听方法
     */
    var searchMonitor = function(){
    	$('.searchBtn').off('click').on('click',function(){
    		defaultValue();
    		var url = '#constructContract/constractSearchPopPage';
    		//加载弹屏
    		$('body').cgetPopup({
    			title: '查询',
    			content: url,
    			accept: searchDone
    		});
    	});
    	//基础条件查询添加监听
    	$('#constructContractTable_filter input').on('change',function(){
    		var projNo = $('#constructContractTable_filter input').val();
    		searchData = {};
    		searchData.projNo = projNo;
    		searchData.menuId= menuId;
    		$('#constructContractTable').cgetData(true,contractCallBack);  //列表重新加载
    	});
    	//基础条件查询添加回车事件
    	$('#constructContractTable_filter input').on('keyup', function(event) {
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
    	var projNo = $('#constructContractTable_filter input').val();
    	searchData.projNo = projNo;
		searchData.menuId= menuId;
    	//列表重新加载
        $('#constructContractTable').cgetData(true,contractCallBack);  
    }
    
    
    var contractCallBack=function(){
    	var len = $('#constructContractTable').DataTable().ajax.json().data ? $('#constructContractTable').DataTable().ajax.json().data.length : $('#constructContractTable').DataTable().ajax.json().length;
    	if(len<1){
    		 //清空右侧记录
    		 $('#contractDetailform')[0].reset();
    		 //保存按钮隐藏
    		 $('.editbtn').addClass('hidden');
    		 $('#contractDetailform').toggleEditState(false);
    		 $(':input','#contractDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
    	 }
    }
    
    /**
     *选中行时，查询详述
     */
    var trSelectedBack = function(v, i, index, t, json){
    	$("#changeFlag").val("0");//控制户数和每户金额的change事件，若没保存过合同金额change事件有效，若保存过则change事件无效
    	//$(':input','#contractDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
    	// $('#contractDetailform').formReset();
        $(".selectDisabled").addClass("disabled");
    	$('.editbtn').addClass('hidden');
    	var menuId = "110402";
    	//加载右侧页面后，加载右侧页面数据
    	$("#construct_contract_panel_box").cgetContent("constructContract/viewPage",{projId:json.projId,projectType:json.projectType,corpId:json.corpId,menuId:menuId},function(){
    		//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
        	t.cgetDetail('contractDetailform','constructContract/viewContract','',dataBack); 
    	});
    	
    };
    /**
     * 初始化工程列表
     */
    var constructContract = function () {
    	'use strict';
        return {
            //main function
            init: function () {
            	$.getScript("js/ellipsis.js").done(function(){       
            		handleConstructContract();
            	});
            }
        };
    }();
    $(function(){
    	 constructContract.init();
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->