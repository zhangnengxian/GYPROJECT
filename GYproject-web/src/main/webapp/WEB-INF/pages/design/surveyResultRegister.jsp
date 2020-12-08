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
                    <h4 class="panel-title">待现场踏勘工程列表</h4>
                </div>
                <div class="panel-body">
                	<input type="hidden" id="designInstituteId" name="designInstituteId" value="${designInstituteId }"/>
                	<input type="hidden" id="changeDispatch" name="changeDispatch" value="${changeDispatch }"/>
                    <input type="hidden" id="projNo" name="projNo" value="${projNo }"/>
                	<input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
					<input type="hidden" id="stepId" name="stepId" value="${stepId }">
					<input type="hidden" id="curStepId" name="curStepId" value="${curStepId }">
					<input type="hidden" id="sysDate" name="curStepId" value="${sysDate }">
					<input type="hidden" class="projId1" >
					<input type="hidden" class="corpId1" >
					<input type="hidden" class="projectType1" >
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>
                    <table id="surveyResultTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th>工程编号</th>
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
			    <div class="panel-body" id="survey_result_panel_box"></div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->

<script>
    /**
     * 初始化待勘察结果登记工程列表
     */
    var surveyResultRegister = function () {
        'use strict';
        return {
            init: function () {
                $.getScript("js/ellipsis.js").done(function(){
                    handleSurveyResult();
                })
            }
        };
    }();
    App.restartGlobalFunction();
    App.setPageTitle('现场踏勘 - 工程管理系统');
    
    /* $.getScript('projectjs/design/survey-result-register.js?v=17122').done(function () {
        surveyResultRegister.init();
	}); */
    var surveyResultMytable;       //工程列表
    var dataTableFirst;  //资料列表
    var dataTableSecond;
    var dataPopTable;
    var showLength; //显示长度
    //判断是否是手机端，如果是手机端显示8位，不是则显示10位
    if(_inApk){
    	showLength=8;
    }else{
    	showLength=10;
    }
    var searchData={}; //查询条件
    var accessoryData={};
    var designerData={};
    var changeData={};
    var detailSearchData={},
    	scaleChangeTable,
    	scaleTable;
    searchData.sideBarID = "110202";
    searchData.menuId="110202";
    var projNoLength,projNameLength;
    /**
     * 加载工程列表
     */
    var handleSurveyResult = function() {
    	'use strict';
        searchData.projNo=$("#projNo").val();
        if ($('#surveyResultTable').length !== 0) {
        	surveyResultMytable= $('#surveyResultTable').on( 'init.dt',function(){
       			//加载右侧页面
       			//默认选中第一行
        		$(this).bindDTSelected(trSelectedBack,true); 
       			console.info("flw--"+$(".projectType1").val());
       			$('#survey_result_panel_box').cgetContent('surveyResultRegister/viewPage',{projectTypeId:$(".projectType1").val(),projId:$(".projId1").val(),corpId:$(".corpId1").val(),menuId:"110202"});
       			console.info("flw1----"+$(".projId1").val());
       			//隐藏遮罩
       			$('#surveyResultTable').hideMask();
       			$('#surveyResultTable_filter input').attr('placeholder','工程编号');
       			//绑定单击事件 弹出搜索框
       			searchMonitor();
       			//登记监听方法
       			registerMonitor();
       			
       			setTimeout(function(){
       			    $("#surveyResultTable").DataTable().columns.adjust();
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
                     { text: '登记', className: 'btn-sm btn-query business-tool-btn registerBtn'}
                 ],
                 //启用服务端模式，后台进行分段查询、排序
    			 serverSide:true,
                 ajax: {
                    url: 'surveyResultRegister/queryProject',
                    type:'post',
                    data: function(d){
                       	$.each(searchData,function(i,k){
                       		d[i] = k;
                       	});
                    },
                    dataSrc: 'data'
                 },
                 //ajax: 'projectjs/design/json/survey_result_register.json',
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
    								$(td).parent().css("background", "rgb(255, 219, 219)");
    								//$(td).attr("id",row);
    							}
    						}
    						},
    				 {"data":"overdue",className:"none never"}
    			 ],
    			 columnDefs: [{
    				 'targets':0,
    				 'visible':false
    			 },{
    					"targets":1,
    					//长字符串截取方法
    					render: $.fn.dataTable.render.ellipsis({
    						length: 15, 	//截取多少字符（或汉字）
    						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    					})
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
    			},
                fnInitComplete:function(oSettings, json){
                	//将选择行放入初始化完成的回调方法中
                }
            });
        }
    };
    /**
     * 登记按钮监听方法
     */
    var registerMonitor = function(){
    	$('.registerBtn').off('click').on('click',function(){
            $("#surveyDate").addClass("field-not-editable");

    	    if ($('#surveyDate').val()==''){
    	        $('#surveyDate').val('${currentDate}');
            }

            if($("#projName").val()==""){
    			$("#projName").removeClass("field-not-editable");
				$("#projName").addClass("field-editable");
    		}
    		if($("#post").val().indexOf($("#surveyPost").val())>=0){  //若是踏勘员
    			$("#projName").removeClass("field-not-editable");
				$("#projName").addClass("field-editable");
				$("#projAddr").removeClass("field-not-editable");
				$("#projAddr").addClass("field-editable");
    		}else{
    			$("#projName").addClass("field-not-editable");
				$("#projName").removeClass("field-editable");
				$("#projAddr").addClass("field-not-editable");
				$("#projAddr").removeClass("field-editable");
    		}
    		if($('#surveyResultTable').find('tr.selected').length>0){
    			//$("#backReason").attr("data-parsley-required","false");
    			console.info("marketPost111--"+$("#marketPost").val());
    			if($("#post").val().indexOf($("#designerPost").val())>=0) {
    				//切换可编辑状态
    				$("#technicalSuggestion").removeClass("field-not-editable");
					$("#technicalSuggestion").addClass("field-editable");
					$(".surveyContent").removeClass("field-editable");
					$(".surveyContent").addClass("field-not-editable");
    				$('#surveyDetailform').toggleEditState(true);
    				getSignStatusByPostId($("#post").val(),"");
    				$("#buPop").addClass("disabled");
    			}else if($("#post").val().indexOf($("#marketPost").val())>=0){
    				$("#technicalSuggestion").removeClass("field-editable")
    				$("#technicalSuggestion").addClass("field-not-editable");
    				$(".surveyContent").removeClass("field-not-editable");
    				$(".surveyContent").addClass("field-editable");
    				
    				$("#connectGasDes").removeClass("field-editable")
    				$("#connectGasDes").addClass("field-not-editable");
    				$('#surveyDetailform').toggleEditState(true);
    				getSignStatusByPostId($("#post").val(),"");
    				$("#buPop").addClass("disabled");
    			}else{
    				//切换可编辑状态
    				$('#surveyDetailform,#scaleTableForm,#scaleTableChangeForm').toggleEditState(true);
    				getSignStatusByPostId($("#post").val(),"");
    				$("#buPop").removeClass("disabled");
    			}
    			//不同点
    			diffFunc();
    			//维护按钮显示出来
    			$('.editbtn').removeClass('hidden');
    			$('.backReason').addClass('hidden');
    		}else{
    			alertInfo('请选择要登记的工程记录！');
    		}
    	});


    };
    /**
     * 弹屏监听方法
     */
    var searchMonitor = function(){
    	$('.searchBtn').on('click',function(){
    		var url = '#surveyResultRegister/projectSearchPopPage';
    		//加载弹屏
    		$('body').cgetPopup({
    			title: '查询',
    			content: url,
    			accept: searchDone
    		});
    	});
    	//基础条件查询添加监听
    	$('#surveyResultTable_filter input').on('change',function(){
    		var projNo = $('#surveyResultTable_filter input').val();
    		searchData = {};
    		searchData.projNo = projNo;
    		searchData.menuId="110202";
    		$('#surveyResultTable').cgetData(true,surveyTableCallBack);  //列表重新加载
    	});
    	//基础条件查询添加回车事件
    	$('#surveyResultTable_filter input').on('keyup', function(event) {
    	    if (event.keyCode == '13') {
    	    	$(this).change();
    	    }
    	});
    };
    
    /**
     * 查询弹出屏，点击确定后 
     */
    var searchDone = function(){
    	searchData = $('#searchForm_surveyRegister').serializeJson();
    	var projNo = $('#surveyResultTable_filter input').val();
    	searchData.projNo = projNo;
    	searchData.menuId="110202";
    	//列表重新加载
        $('#surveyResultTable').cgetData(true,surveyTableCallBack);  
    }
    
    var surveyTableCallBack = function(){
    	var len = $('#surveyResultTable').DataTable().ajax.json().data ? $('#surveyResultTable').DataTable().ajax.json().data.length : $('#surveyResultTable').DataTable().ajax.json().length;
    	if(len<1){
    		 //清空右侧记录
    		 $('#surveyDetailform')[0].reset();
    		 $(':input','#surveyDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
    		 $(".backReason").addClass("hidden");
    		 $(".editbtn").addClass("hidden");
    		 $("#surveyDetailform").toggleEditState(false);
    	 }else{
    		 $(".editbtn").addClass("hidden");
    	 }
    }
    /**
     *选中行时，查询详述
     */
    var trSelectedBack = function(v, i, index, t, json){
        if($("#isBack").val()!="0"){
        	$("#isBack").val("0")
        }
        var menuId = "110202";
        $(".projId1").val(json.projId);
        $(".corpId1").val(json.corpId);
        $(".projectType1").val(json.projectType);
        $("#survey_result_panel_box").cgetContent("surveyResultRegister/viewPage",{projectTypeId:json.projectType,projId:json.projId,corpId:json.corpId,menuId:menuId},function(){
    		//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
        	t.cgetDetail('surveyDetailform','surveyResultRegister/viewSurveyResult','',surveyBack); 
    		
    	});
   	 if(trSData.surveyResultTable.json.contributionMode == '5'){  //公司出资隐藏客户信息
     	$(".noUser").addClass("hidden");
     }else{
     	//$("#noUser").removeClass("hidden");
    	 $(".noUser").addClass("hidden");
     }
    	 
    }
    

    
    /**
     * 初始化规模列表
     */
    var sacletableinit= function() {
    	"use strict";
        if ($('#scaleTable').length !== 0) {
        	scaleTable= $('#scaleTable').on( 'init.dt',function(){
        		$('#scaleTable').hideMask();
    	        $('#scaleTableForm').toggleEditState(false);
    	        inputMonitor();
            }).DataTable({
            	language: language_CN,
                lengthMenu: [18],
                dom: 'Brt',
                buttons: [],
                /*ajax: 'projectjs/accept/json/scale.json',*/
                //启用服务端模式，后台进行分段查询、排序
                serverSide:false,
                ajax: {
                	url: 'projectAccept/queryScaleDetail',
                	type:'post',
                	data: function(d){
                      	$.each(detailSearchData,function(i,k){
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
                },//自适应
                columns: [
    	  			{"data":"projStypeDes", responsivePriority: 1},
    	  			{"data":"tonnage",className:"text-right", responsivePriority: 3},
    	  			{"data":"searNum",className:"text-right", responsivePriority: 6},
    	  			{"data":"num",className:"text-right", responsivePriority: 5},
    	  			{"data":"houseNum",className:"text-right", responsivePriority: 4},
    	  			{"data":"gasConsumption",className:"text-right", responsivePriority: 7}
    			],
    			columnDefs: [{
    				"targets":0,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: 8, 	//截取多少字符（或汉字）
    					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    				})
    			},{
    				targets: 1,
    				/*
    				 * render属性
    				 * 方法携带四个参数
    				 * data: 该单元格的原始数据，也就是默认显示的那些数据
    				 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
    				 * row: 但前行的所有原始数据
    				 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
    				 */
    				render: function ( data, type, row, meta ) {
    					if(type === "display"){
    						if(data === null){
    							data = "";
    						}
    						var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_tonnage' id='" + row.projStypeId + "_tonnage'  data-parsley-maxlength='16' data-parsley-type='number' value="+data+"></div>"
    						return tdcon;
    					}else{
    						return data;
    					}}
    			    },{
    			    	targets: 2,
    					render: function ( data, type, row, meta ) {
    						if(type === "display"){
    							if(data === null){
    								data = "";
    							}
    							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_searNum' id='" + row.projStypeId + "_searNum' data-parsley-maxlength='16' data-parsley-type='integer' value="+data+"></div>"
    							return tdcon;
    						}else{
    							return data;
    						}}	
    			    },{
    			    	targets: 3,
    					render: function ( data, type, row, meta ) {
    						if(type === "display"){
    							if(data === null){
    								data = "";
    							}
    							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_num' id='" + row.projStypeId + "_num'  data-parsley-maxlength='16' data-parsley-type='integer' value="+data+"></div>"
    							return tdcon;
    						}else{
    							return data;
    						}	}
    			    },{
    			    	targets: 4,
    					render: function ( data, type, row, meta ) {
    						if(type === "display"){
    							if(data === null){
    								data = "";
    							}
    							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_houseNum' id='" + row.projStypeId + "_houseNum' data-parsley-maxlength='9' data-parsley-type='integer' value="+data+"></div>"
    							return tdcon;
    						}else{
    							return data;
    						}}
    			    },{
    			    	targets: 5,
    					render: function ( data, type, row, meta ) {
    						if(type === "display"){
    							if(data === null){
    								data = "";
    							}else if(data !==null && data!==""){
    								data = data.toFixed(2);
    							}
    							var  tdcon="<div class='tdInnerInput'><input type='hidden' name='"+row.projStypeId+"_projStypeId' id='"+row.projStypeId+"_projStypeId' value="+row.projStypeId+">" +
    									    "<input class='hidden' name='" + row.projStypeId + "_projLtypeId' id='" + row.projStypeId + "_projLtypeId' data-parsley-maxlength='9' data-parsley-type='number' value="+row.projLtypeId+">" +
    									    "<input class='hidden' name='" + row.projStypeId + "_projStypeDes' id='" + row.projStypeId + "_projStypeDes'   value="+row.projStypeDes+">"+
    									    "<input class='hidden' name='" + row.projStypeId + "_scaleId' id='" + row.projStypeId + "_scaleId'   value="+row.scaleId+">"+
    									    "<input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_gasConsumption' id='" + row.projStypeId + "_gasConsumption'  data-parsley-type='number' value="+data+"></div>"
    							return tdcon;
    						}else{
    							return data;
    						}}
    			    }],
    			ordering:false
           });
       }
    }
    
    /**
     * 初始化规模列表
     */
    var sacletableinit2= function() {
    	"use strict";
        if ($('#scaleChangeTable').length !== 0) {
        	scaleChangeTable= $('#scaleChangeTable').on( 'init.dt',function(){
        		$('#scaleChangeTable').hideMask();
    	        $('#scaleTableChangeForm').toggleEditState(false);
    	        scaleChangeTableCallBack();
    	        inputMonitor();
            }).DataTable({
            	language: language_CN,
                lengthMenu: [18],
                dom: 'Brt',
                buttons: [],
                /*ajax: 'projectjs/accept/json/scale.json',*/
                //启用服务端模式，后台进行分段查询、排序
                serverSide:false,
                ajax: {
                	url: 'projectAccept/queryScaleDetail',
                	type:'post',
                	data: function(d){
                      	$.each(detailSearchData,function(i,k){
                      		d[i] = k;
                      	});
                      	
                	},
                	dataSrc: 'data'
                },
                
                //ajax: 'projectjs/accept/json/proj_scale.json',
                responsive: {
                	details: {
                		renderer: function ( api, rowIdx, columns ){
                			return renderChild(api, rowIdx, columns);
                		}
                	}
                },//自适应
                columns: [
    	  			{"data":"projStypeDes", responsivePriority: 3},
    	  			{"data":"pipeDiameter",className:"text-right", responsivePriority: 2},
    	  			{"data":"pipeLength",className:"text-right", responsivePriority: 1}
    			],
    			columnDefs: [{
    				"targets":0,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: 8, 	//截取多少字符（或汉字）
    					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    				})
    			},{
    				targets: 1,
    				/*
    				 * render属性
    				 * 方法携带四个参数
    				 * data: 该单元格的原始数据，也就是默认显示的那些数据
    				 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
    				 * row: 但前行的所有原始数据
    				 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
    				 */
    				render: function ( data, type, row, meta ) {
    					if(type === "display"){
    						if(data === null){
    							data = "";
    						}
    						var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_pipeDiameter' id='" + row.projStypeId + "_pipeDiameter'  data-parsley-maxlength='50' value="+data+"></div>"
    						return tdcon;
    					}else{
    						return data;
    					}}
    			    }/*,{
    			    	targets: 2,
    					render: function ( data, type, row, meta ) {
    						if(type === "display"){
    							if(data === null){
    								data = "";
    							}
    							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_pipeLength' id='" + row.projStypeId + "_pipeLength' data-parsley-maxlength='13' data-parsley-type='number' value="+data+"></div>"
    							return tdcon;
    						}else{
    							return data;
    						}}	
    			    }*/,{
    			    	targets: 2,
    					render: function ( data, type, row, meta ) {
    						if(type === "display"){
    							if(data === null){
    								data = "";
    							}else if(data !==null && data!==""){
    								data = data;
    							}
    							var  tdcon="<div class='tdInnerInput'><input type='hidden' name='"+row.projStypeId+"_projStypeId' id='"+row.projStypeId+"_projStypeId' value="+row.projStypeId+">" +
    									    "<input class='hidden' name='" + row.projStypeId + "_projLtypeId' id='" + row.projStypeId + "_projLtypeId' data-parsley-maxlength='9' data-parsley-type='number' value="+row.projLtypeId+">" +
    									    "<input class='hidden' name='" + row.projStypeId + "_projStypeDes' id='" + row.projStypeId + "_projStypeDes'   value="+row.projStypeDes+">"+
    									    "<input class='hidden' name='" + row.projStypeId + "_scaleId' id='" + row.projStypeId + "_scaleId'   value="+row.scaleId+">"+
    									    "<input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_pipeLength' id='" + row.projStypeId + "_pipeLength'  data-parsley-type='number' value="+data+"></div>"
    							return tdcon;
    						}else{
    							return data;
    						}}
    			    }],
    			ordering:false
           });
       }
    }
    
    var inputMonitor = function(){
    	$(".tdInnerInput input").on("change",function(){
    		var name = $(this).attr("name");
    		var checkBoxId = name.split("_")[0]+"_scaleId";
    		if($(this).val()){
    			$("#"+checkBoxId).attr("checked","checked");
    		}else{
    			 var input = $(this).parents("tr").find(".tdInnerInput input").not("[type='radio']");
    			 for(var i=0;i<input.length;i++){
    				 if(input.eq(i).val()){
    					 return false;
    				 }
    			 }
    			 $("#"+checkBoxId).removeAttr("checked");
    		}
    	});
    }
    
var scaleChangeTableCallBack = function(){
		$('#scaleTableChangeForm').toggleEditState(false);
}
var scaleTableCallBack = function(){
		$('#scaleTableForm').toggleEditState(false);
}


$(function(){
	surveyResultRegister.init();
});


</script>
<!-- ================== END PAGE LEVEL JS ================== -->