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
                    <h4 class="panel-title">工程列表</h4>
                </div>
                <div class="panel-body">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <input type="hidden" id="post" value ="${post }" />
                    <input type="hidden" id="projNo" name="projNo" value ="${projNo }" />
                    <input type="hidden" id="equipmentTechnician" value ="${equipmentTechnician }" />

                    <table id="settlementJonintlySignTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th></th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>工程状态</th>
                                <th>汇签状态</th>
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
			         <h4 class="panel-title">结算汇签信息</h4>
			    </div>
			    <div class="panel-body" id="gas_confirm_panel_box"></div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('结算汇签 - 工程管理系统');
   
    
    /* $.getScript('projectjs/settlement/settlement-jonintly-sign.js?v=1112').done(function () {
    	settlementJonintlySign.init();
	}); */
    var showLength; //显示长度
  //判断是否是手机端，如果是手机端显示8位，不是则显示10位
  if(_inApk){
  	showLength=8;
  }else{
  	showLength=10;
  }
  var searchData={}; //查询条件
  searchData.menuId = "110815";
  /**
   * 加载工程列表
   */
  var handleConnectConfirm = function() {
      searchData.projNo=$("#projNo").val();
  	"use strict";
      if ($('#settlementJonintlySignTable').length !== 0) {
      	$('#settlementJonintlySignTable').on( 'init.dt',function(){
      	//加载页面
    		var len = $('#settlementJonintlySignTable').DataTable().ajax.json().data ? $('#settlementJonintlySignTable').DataTable().ajax.json().data.length : $('#settlementJonintlySignTable').DataTable().ajax.json().length;
    		if(len<1){
    			$("#gas_confirm_panel_box").cgetContent("settlementJonintlySign/viewPage");
    		}
     			//默认选中第一行
      		$(this).bindDTSelected(trSelectedBack,true);
     			//加载页面 
     			
     			//隐藏遮罩
     			$('#settlementJonintlySignTable').hideMask();
     			$("#settlementJonintlySignTable_filter input").attr("placeholder","工程编号");
         
     		    //绑定单击事件 弹出搜索框
     			searchMonitor();
     			//通气申请
     			applyMonitor();
     			setTimeout(function(){
     			    $("#settlementJonintlySignTable").DataTable().columns.adjust();
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
                  { text: '汇签', className: 'btn-sm btn-query business-tool-btn applyBtn' }
                  ],
              //启用服务端模式，后台进行分段查询、排序
  			serverSide:true,
              ajax: {
                  url: 'settlementJonintlySign/queryProject',
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
                  {"data":"projId",className:"none never"},
  				{"data":"projNo"},
  				{"data":"projName"},
  				{"data":"projStatusDes"},
  				{"data":"projectRemark"}
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
  				targets: 4,
  				render: function ( data, type, row, meta ) {
  					console.info("data---"+row.projectRemark);
  					console.info("dataprojName---"+row.projName);
  					if(type === "display"){
  						if(data == '1'){
  							data = '已完成';
  						}else{
  							data = '未完成'
  						}
  						return data;
  					}else{
  						return data;
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
  }


  /**
   * 弹屏监听方法
   */
  searchMonitor = function(){
  	$('.searchBtn').on('click',function(){
  		var url = "#settlementJonintlySign/settlementJonintlySignPopPage";
  		//加载弹屏
  		$('body').cgetPopup({
  			title: '查询',
  			content: url,
  			accept: searchDone
  		});
  	});

  	// 基础条件查询添加监听
  	$('#settlementJonintlySignTable_filter input').on('change', function() {
  		var projNo = $('#settlementJonintlySignTable_filter input').val();
  		searchData = {};
  		searchData.projNo = projNo;
  		searchData.menuId = "110815";
  		$('#settlementJonintlySignTable').cgetData(true,tableCallBack); // 列表重新加载
  	});
  	// 基础条件查询添加回车事件
  	$('#settlementJonintlySignTable_filter input').on('keyup', function(event) {
  		if (event.keyCode == '13') {
  			$(this).change();
  		}
  	});
  },



  /**
   * 查询弹出屏，点击确定后 
   */
  searchDone = function(){
  	searchData = $('#settlementSignForm').serializeJson();
  	var projNo = $('#settlementJonintlySignTable_filter input').val();
  	searchData.projNo = projNo;
  	searchData.menuId = "110815";
  	//列表重新加载
      $('#settlementJonintlySignTable').cgetData(true,tableCallBack);  
  }
  
//申请
  var applyMonitor=function(){
  	$(".applyBtn").off().on("click",function(){
  		if($("#settlementJonintlySignTable").find("tr.selected").length>0){
  			//切换可编辑状态
  			 //根据职务过滤可编辑项
  	        //$('#settlementJonintlySignRightForm').toggleEditState(true);
  	        getSignStatusByPostId($("#post").val(),"settlementJonintlySignRightForm");
  			//维护按钮显示出来
  			$(".editbtn").removeClass("hidden");
  			
  			console.info("post--"+$("#post").val());
  			console.info("equipmentTechnician--"+$("#equipmentTechnician").val());
  			console.info($("#post").val().indexOf($("#equipmentTechnician").val()));
  			console.info($("#post").val().indexOf("qqq"));
  			if($("#post").val().indexOf($("#equipmentTechnician").val())>=0){
  				$(".entBtn").removeClass("hidden");
  			}else{
  				$(".entBtn").addClass("hidden");
  			}
  			
  		}else{
  			alertInfo('请选择要修改的记录！');
  		}
  	})
  }


  /**
   * 选中行时，查询详述
   */
  trSelectedBack = function(v, i, index, t, json){
  	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
  	$(".editbtn").addClass("hidden");
  	var menuId = getStepId();
  	 $("#gas_confirm_panel_box").cgetContent("settlementJonintlySign/viewPage",{projId:json.projId,corpId:json.corpId,menuId:menuId,projectType:json.projectType,contributionMode:json.contributionMode},function(){
 		//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
  		$("#settlementJonintlySignTable").cgetDetail('settlementJonintlySignRightForm','settlementJonintlySign/viewSettlementJonintlySign','',queryBack); 
 	});
  	
  }
  
  tableCallBack = function(){
		var len = $('#settlementJonintlySignTable').DataTable().ajax.json().data ? $('#settlementJonintlySignTable').DataTable().ajax.json().data.length : $('#settlementJonintlySignTable').DataTable().ajax.json().length;
		if(len<1){
			 //清空右侧记录
			 $('#settlementJonintlySignRightForm').formReset();
			 $("#settlementJonintlySignRightForm").toggleEditState(false);
		 }
	}


	/**
	 * 初始化工程列表
	 */
	var settlementJonintlySign = function () {
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
		settlementJonintlySign.init();
	})

</script>
<!-- ================== END PAGE LEVEL JS ================== -->