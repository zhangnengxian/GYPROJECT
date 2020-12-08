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
                    <h4 class="panel-title">待签分包工程列表</h4>
                </div>
                <div class="panel-body">
                	<input type="hidden" name="customerServiceCenter" id="customerServiceCenter"  value="${customerServiceCenter}"/>
                	<input  type="hidden" id="cuUnitTypeId" name="cuUnitTypeId" value="${cuUnitTypeId}"/>
                	<input  type="hidden" id="deptTypeId" name="deptTypeId" value="${deptTypeId}"/>
                	<input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
	            	<input type="hidden" id="stepId" name="stepId" value="${stepId }">
	            	<input type="hidden" class="stepIds" value="1605">
	            	<input type="hidden" id="curStepId" name="curStepId" value="${curStepId }">
	            	<input type="hidden" id="deptDivide" name="deptDivide" value="${deptDivide }">
	            	
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>
                    <table id="subContractTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th></th>
                            	<th>工程编号</th>
                                <th>工程名称</th>
                                <th>状态</th>
                                <th>剩余时限(天)</th>
                                <th></th>
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
            <div class="panel panel-inverse tabs-mixin" >
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
	                <h4 class="panel-title">操作区</h4>
			    </div>
			    <div class="panel-body" id="">
				    <div class="tab-content">
                        	<div id="sub_contract_panel_box"></div>
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
    App.setPageTitle('施工合同 - 工程管理系统');
    
   /*  $.getScript('projectjs/subcontract/sub-contract.js?'+Math.random()).done(function () {
        subContract.init();
	});
 */
    var showLength; //显示长度
  //判断是否是手机端，如果是手机端显示8位，不是则显示10位
  if(_inApk){
  	showLength=8;
  }else{
  	showLength=10;
  }
  var subConstractTable; //待签分包工程列表
  var searchData={};	   //查询条件
  searchData.menuId="110603";
  var constructionUnitData={};
  /**
   * 加载工程列表
   */
  var handleSubContract = function() {
  	'use strict';
      if ($('#subContractTable').length !== 0) {
      	subConstractTable=$('#subContractTable').on( 'init.dt',function(){
     			//默认选中第一行
      		$(this).bindDTSelected(trSelectedBack,true);
     			//加载右侧页面
     			//$('#sub_contract_panel_box').cgetContent('subContract/viewPage');
     			var len = $('#subContractTable').DataTable().ajax.json().data ? $('#subContractTable').DataTable().ajax.json().data.length : $('#subContractTable').DataTable().ajax.json().length;
                if(len<1){
                    $("#sub_contract_panel_box").cgetContent("subContract/viewPage");
                }
     			//隐藏遮罩
     			$('#subContractTable').hideMask();
     			$('#subContractTable_filter input').attr('placeholder','工程编号');
     			//搜索监听
     			searchMonitor();
     			//签订监听
     			signMonitor();
     			setTimeout(function(){
     			    $("#subContractTable").DataTable().columns.adjust();
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
                   { text: '签订', className: 'btn-sm btn-query business-tool-btn signBtn' }
                   ],
               //启用服务端模式，后台进行分段查询、排序
  			 serverSide:true,
               ajax: {
                  url: 'subContract/queryProject',
                  type:'post',
                  data: function(d){
                     	$.each(searchData,function(i,k){
                     		d[i] = k;
                     	});
                  },
                  dataSrc: 'data'
               },
               //ajax: 'projectjs/subcontract/json/sub_contract.json',
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
  				 {'data':'projStatusDes'},
  				 {"data":"workDayDto"},
  				 {"data":"signBack",
  						className:"none never",
  						"createdCell": function (td, cellData, rowData, row, col) {
  							if(cellData==$("#notThrough").val()){
  								$(td).parent().find("td").css("background", "rgb(255, 219, 219)");
  								//$(td).attr("id",row);
  							}
  						}
  						},
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
  				},{
  					"targets":4,
  					 "render":function(data,type,row,meta){
  						 if(data!=null){
  							 return data.haveDays;
  						 }else{
  							 return 0;
  						 }
  					 }
  				}],
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
  	$('.searchBtn').on('click',function(){
  		var url = '#subContract/projectSearchPopPage';
  		//加载弹屏
  		$('body').cgetPopup({
  			title: '查询',
  			content: url,
  			accept: searchDone
  		});
  	});
  	//基础条件查询添加监听
  	$('#subContractTable_filter input').on('change',function(){
  		var projNo = $('#subContractTable_filter input').val();
  		searchData = {};
  		searchData.projNo = projNo;
  	    searchData.menuId="110603";
  		$('#subContractTable').cgetData(true,subTableCallBack);  //列表重新加载
  	});
  	//基础条件查询添加回车事件
  	$('#subContractTable_filter input').on('keyup', function(event) {
  	    if (event.keyCode == '13') {
  	    	$(this).change();
  	    }
  	});
  };
  /**
   * 查询弹出屏，点击确定后 
   */
  var searchDone = function(){
  	searchData = $('#searchForm_sub').serializeJson();
  	var projNo = $('#subContractTable_filter input').val();
  	searchData.projNo = projNo;
  	searchData.menuId="110603";
  	//列表重新加载
      $('#subContractTable').cgetData(true,subTableCallBack); 
  }
  
  var subTableCallBack=function(){
		var len = $('#subContractTable').DataTable().ajax.json().data ? $('#subContractTable').DataTable().ajax.json().data.length : $('#subContractTable').DataTable().ajax.json().length;
		if(len<1){
			 //清空右侧记录
			 $('#subContractDetailform')[0].reset();
			 $(':input','#subContractDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
		 }
	}

	//签订监听
	var signMonitor=function(){
		$('.signBtn').off('click').on('click',function(){
			if($('#subContractTable').find('tr.selected').length>0){
				$('#subContractDetailform').toggleEditState(true);
				$('.editbtn').removeClass('hidden');
				if(!$("#contractMethodFlag").val()){
					$("#contractMethodFlag").toggleEditState(false);
				}
				if(!$("#operateDate").val()){
					var sysDate = timestamp($("#sysDate").val());
	                $("#operateDate").val(format(sysDate,"default"));
				}
				if($("#scSignDate").val()){
					$("#scSignDate").removeClass("hidden");
				}else{
					$("#scSignDate").addClass("hidden");
				}
				checkScPlanTotalDay();
			}else{
				alertInfo('请选择要签订分包的工程记录！');
			}
		});
	};
	
	
	/**
	 *选中行时，查询详述
	 */
	var trSelectedBack = function(v, i, index, t, json){
		$("#contractMethod").val(json.contractMethod);
			$('.editbtn').addClass('hidden');
			var menuId = getStepId();
	        $("#sub_contract_panel_box").cgetContent("subContract/viewPage",{projId:json.projId,corpId:json.corpId,menuId:menuId,projectType:json.projectType},function(){
	        	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
				t.cgetDetail('subContractDetailform','subContract/viewSubContract','',cgetDetailBack);
	    	});
			
	}
	/**
	 * 初始化工程列表
	 */
	var subContract = function () {
		'use strict';
	    return {
	        //main function
	        init: function () {
	        	$.getScript("js/ellipsis.js").done(function(){     
	        		handleSubContract();
	        	});
	        }
	    };
	}();
	
	$(function(){
		subContract.init();
	})
</script>
<!-- ================== END PAGE LEVEL JS ================== -->