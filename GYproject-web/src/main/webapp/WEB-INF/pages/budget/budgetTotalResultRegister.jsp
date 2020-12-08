<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <h4 class="panel-title">待预算登记工程列表</h4>
                </div>
                <div class="panel-body">
                    <input type="hidden" id="waitHandleProjNo" name="projNo"  value="${projNo }"/>
                    <input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
                	<input type="hidden" id="stepId" name="stepId" value="${stepId }">
	            	<input type="hidden" id="curStepId" name="curStepId" value="${curStepId }">
                    <input type="hidden" id="civilVal" value="${civilVal}">
                    <input type="hidden" id="publicVal" value="${publicVal}">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="budgetRegisterTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th>工程Id</th>
                                <th>工程编号</th>
                                <th>工程名称</th>
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
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
       	 <div class="panel panel-inverse" id="content">
				    <div class="panel-heading">
				        <div class="panel-heading-btn">
				            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
				            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
				            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
				        </div>
				         <h4 class="panel-title">预算总表</h4>
				    </div>
				    <div class="panel-body" id="budget_register_panel_box"></div>
				</div>
        </div>
        <!-- end col-6 -->
		

    </div>
</div>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('预算结果登记 - 工程管理系统');
    
    /* $.getScript('projectjs/budget/budget-total-result-register.js?v=1002').done(function () {
        budgetResultRegister.init();
	}); */

    var budgetRegisterTable,quantitiestable,materialListTable;
    /**查询条件*/
    var searchData={},quantitiesData={},rateData={},materialdata={};
    var dataPopTable;
    var accessoryTable;
    var accessoryData={};
    var menuId = "110302";
    searchData.menuId = menuId;
    var handleBudgetResult = function() {
    	"use strict";
        searchData.projNo=$("#waitHandleProjNo").val();
        if ($('#budgetRegisterTable').length !== 0) {
        	budgetRegisterTable= $('#budgetRegisterTable').on( 'init.dt',function(){
       			//加载页面
       			$("#budget_register_panel_box").cgetContent("budgetResultRegister/viewPage");
       			//隐藏遮罩
       			$('#budgetRegisterTable').hideMask();
       			$("#budgetRegisterTable_filter input").attr("placeholder","工程编号");
       			$("#budgetRegisterTable_filter input").on("change",function(){
       				searchData={};
       				searchData.projNo=$("#budgetRegisterTable_filter input").val();

       			    searchData.menuId = menuId;
       				//budgetRegisterTable.ajax.reload(budgetBack);	
       			    $("#budgetRegisterTable").cgetData("",budgetBack);  
       			});
       		    //基础条件查询添加回车事件
       			$('#budgetRegisterTable_filter input').on('keyup', function(event) {
       		        if (event.keyCode == "13") {
       		        	$(this).change();
       		        }
       		    });
       			//点击行事件
       			$(this).bindDTSelected(trSelectedBack,true);
       	    	//绑定单击事件 弹出搜索框
       			searchPop();
       			//登记监听
       			registerMonitor();
       			//查询费率
       			//getRate();
       			
       			if (crossPageBus) {
   					getSidebarMenu('11', true);
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
                    { text: '登记', className: 'btn-sm btn-query business-tool-btn registerBtn' }
                ],
                //启用服务端模式，后台进行分段查询、排序
    			serverSide:true,
                ajax: {
                    url: 'budgetResultRegister/queryProject',
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
                select: true,    //支持多选
                columns: [
                    {"data":"projId",className:"none never"}, 
    	  			{"data":"projNo"}, 
    				{"data":"projName"},
    				{"data":"projStatusDes"},
    				{"data":"workDayDto"},
    				 {"data":"signBack",
    					className:"none never",
    					"createdCell": function (td, cellData, rowData, row, col) {
    						console.info(cellData);
    						if(cellData==$("#notThrough").val()){
    							$(td).parent().children().css("background", "rgb(255, 219, 219)");
    							//$(td).attr("id",row);
    						}
    					}
    					},
    				{"data":"overdue",className:"none never",},
    				
    			],
    			columnDefs: [{
    				"targets": 0,
    			    "visible":false
    			},{
    				"targets":2,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: 10, 	//截取多少字符（或汉字）
    					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    				})
    			},{
    				"targets":3,
    				 "orderable":false
    			},{
    				"targets":4,
    				"orderable":false,
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
    
    function budgetBack(){
    	var len = $('#budgetRegisterTable').DataTable().ajax.json().data ? $('#budgetRegisterTable').DataTable().ajax.json().data.length : $('#budgetRegisterTable').DataTable().ajax.json().length;
    	if(len<1){
    		 //清空右侧记录
    		$("#budgetSumForm").formReset();
    		$('.editbtn').addClass("hidden");
    		
    	 }else{
    		 $("#projId1").val(-1);
    		 $("#budgetSumForm").formReset();
    		 $('.editbtn').addClass("hidden");
    		
    	 }
    	
    }
    
    /**
     * 预算登记监听
     */
    var registerMonitor = function(){
    	$(".registerBtn").on("click",function(){
    		var len=$("#budgetRegisterTable").find("tr.selected").length;
    		if(len>0){
    			$('#budgetSumForm').toggleEditState(true);
    			$(".editbtn").removeClass("hidden");
    		}else{
    			alertInfo("请选中要预算的工程！");
    		}
    	});
    };
   
 //弹屏监听方法
   var searchPop = function(){
   	$(".searchBtn").on("click",function(){
   		var url = "#budgetResultRegister/projectSearchPopPage";
   		var popoptions = {
   			title: '查询',
   			content: url,
   			accept: searchDone
   		};
   		//加载弹屏
   		$("body").cgetPopup(popoptions);
   	});
   };
   /** 查询弹出屏，点击确定后 */
   var searchDone = function(){	
   	 searchData= $("#budgetSearchForm").serializeJson();

     searchData.menuId = menuId;
       $("#budgetRegisterTable").cgetData("",budgetBack);  //列表重新加载
   }
   /** 选中行时，查询详述*/
   var trSelectedBack = function(v, i, index, t, json){
   	if(json!=undefined){
   		$("#projId").val(json.projId);
   		$("#budgetId").val(json.budgetId);
   		
   	}
   	$('#budgetSumForm') && $('#budgetSumForm').formReset();
   	$('.editbtn').addClass('hidden');
   	var menuId = getStepId();
    $("#budget_register_panel_box").cgetContent("budgetResultRegister/viewPage",{projId:json.projId,corpId:json.corpId,menuId:menuId},function(){
    	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
       	trSData.budgetRegisterTable.t.cgetDetail('budgetSumForm','budgetResultRegister/queryPro','',queryBackView);
	});
   }
   var queryBackView =function(data){
//		 $("#stepId").val(getStepId());
		 $("#alPath").val($("#projNo").val()+"/预算");
		 $(".searchButton").attr("href","/accessoryCollect/openCoFile?id="+$("#budgetId").val());
	     $(".searchButton").removeClass("hidden");
	     $(".Search_Button").addClass("hidden");
		if(data.drawName){
			$(".hasVal").removeClass("hidden");
			$(".noVal").addClass("hidden");
			$(".noVal+#filePreviews tr").remove();
		}else{
			$(".hasVal").addClass("hidden");
			$(".noVal").removeClass("hidden");
		}
	}
   
   /*
    * 采集关联
    */
   $(".attach-panel").initAttachOper({
   	collection: {
   		tableid:'budgetRegisterTable'
   	}
   });
   var budgetResultRegister = function () {
		"use strict";
	    return {
	        //main function
	        init: function () {
	        	$.getScript("js/ellipsis.js").done(function(){ 
	        		handleBudgetResult();
	        	})
	        }
	    };
	}();
   $(function(){
	   budgetResultRegister.init();
   })
   
</script>
<!-- ================== END PAGE LEVEL JS ================== -->