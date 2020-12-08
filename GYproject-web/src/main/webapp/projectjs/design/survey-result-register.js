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
searchData.menuId=getStepId();
var accessoryData={};
var designerData={};
var changeData={};
var detailSearchData={},
	scaleChangeTable,
	scaleTable;
searchData.sideBarID = "110202";
var projNoLength,projNameLength;
/**
 * 加载工程列表
 */
var handleSurveyResult = function() {
	'use strict';
    if ($('#surveyResultTable').length !== 0) {
    	surveyResultMytable= $('#surveyResultTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#survey_result_panel_box').cgetContent('surveyResultRegister/viewPage');
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
			}
        });
    }
};

/**
 * 登记按钮监听方法
 */
var registerMonitor = function(){
	$('.registerBtn').off('click').on('click',function(){
		if($('#surveyResultTable').find('tr.selected').length>0){
			//$("#backReason").attr("data-parsley-required","false");
			if($("#post").val().indexOf($("#designerPost").val())>=0){
				//切换可编辑状态
				$('#surveyDetailform').toggleEditState(true);
				$("#buPop").addClass("disabled");
			}else{
				
				//切换可编辑状态
				$('#surveyDetailform,#scaleTableForm,#scaleTableChangeForm').toggleEditState(true);
				$("#buPop").removeClass("disabled");
			}
			
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
var searchMonitor = function(){0.3
	22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222
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
		$('#surveyResultTable').cgetData(true,surveyTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#surveyResultTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};


//设计时取消派遣
var cancelDone=function(){
	$("#surveyResultTable").cgetData(true);
}


/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	searchData = $('#searchForm_surveyRegister').serializeJson();
	var projNo = $('#surveyResultTable_filter input').val();
	searchData.projNo = projNo;
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
	t.cgetDetail('surveyDetailform','surveyResultRegister/viewSurveyResult','',surveyBack); 
}

function surveyBack(data){
	if(data.projName=='' || data.projName==null){
		 $("#projName").removeClass("field-not-editable");
		 $("#projAddr").removeClass("field-not-editable");
		 $("#projName").addClass("field-editable");
		 $("#projAddr").addClass("field-editable");
	}
	if($("#contractType").val()=='11'){
		//显示民用的 隐藏公建、改管、干线
		$(".changeType,.trunkType").addClass("hidden");
		$(".civilType,.publicType").removeClass("hidden");
		//智能表
		if(data.isIntelligentMeter==null||data.isIntelligentMeter==''){
			$('input:radio[name="isIntelligentMeter"]').removeAttr("checked");
		}
		showIntelligentMeter();
	}else if($("#contractType").val()=='21'){
		//显示公建的 隐藏民用、改管、干线
		$(".changeType,.civilType,.trunkType").addClass("hidden");
		$(".publicType").removeClass("hidden");
	}else if($("#contractType").val()=='31'){
		//显示改管的 隐藏民用、公建、干线
		$(".publicType,.civilType,.trunkType").addClass("hidden");
		$(".changeType").removeClass("hidden");
	}else{
		//显示干线的 隐藏民用、公建、改管
		$(".publicType,.civilType").addClass("hidden");
		$(".changeType,.trunkType").removeClass("hidden");
	}	
	
	//客服中心可以退到现场踏勘
	if($("#deptDivide").val()==$("#customerServiceCenter").val()){
		$(".projectChange").removeClass("hidden");
	}else{
		$(".projectChange").addClass("hidden");
	}
	//踏勘员或客户经理可以推送
	if($("#post").val()!=""){
		if($("#post").val().indexOf($("#surveyPost").val())>=0||$("#post").val().indexOf($("#directorPost").val())>=0){
			$(".saveBtn").removeClass("hidden");
		}else{
			$(".saveBtn").addClass("hidden");
		}
		if($("#post").val().indexOf($("#designerPost").val())>=0){
			$("#technicalSuggestion").removeClass("field-not-editable");
			$("#technicalSuggestion").addClass("field-editable");
			
			$(".surveyContent").removeClass("field-editable");
			$(".surveyContent").addClass("field-not-editable");
			
		}else{
			$("#technicalSuggestion").removeClass("field-editable")
			$("#technicalSuggestion").addClass("field-not-editable");
			$(".surveyContent").removeClass("field-not-editable");
			$(".surveyContent").addClass("field-editable");
		}
	}else{
		$(".saveBtn").addClass("hidden");
	}
	if($("#custName").val()==""){
		$(".noUser").addClass("hidden");
	}else{
		$(".noUser").removeClass("hidden");
	}
	
	 //designerData.deptId=json.duId;
	 $(".editbtn").addClass("hidden");
	 $('.backReason').addClass('hidden');
	 $('.backRemarks').addClass('hidden');
	 $("#surveyDetailform").toggleEditState(false);
	 
		$("#projectType1 option[value='"+$("#projectType").val()+"']").attr("selected","selected");
		detailSearchData.projLtypeId = $("#projectType").val();
		detailSearchData.projId = $("#projId").val();
		var selectVal=$("#projectType1 option:selected").text();
		if(selectVal=="31"||selectVal==""){
			$(".scaleTableForm").addClass("hidden");
			$(".scaleTableChangeForm").removeClass("hidden");
			//加载下面的第2个table
			if($.fn.DataTable.isDataTable('#scaleChangeTable')){
				//初始化过
				$("#scaleChangeTable").cgetData(false,scaleChangeTableCallBack);//列表重新加载
			}else{
				sacletableinit2();
			}
		}else{
			$(".scaleTableChangeForm").addClass("hidden");
			$(".scaleTableForm").removeClass("hidden");
			//加载下面的第1个table
			if($.fn.DataTable.isDataTable('#scaleTable')){
				//初始化过
				$("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
			}else{
				sacletableinit();
			}
		}	 
	 
	 setTimeout(function(){
	    $("#projectImagesList").getImagesList({
	    	"projId": $("#projId").val(),
	    	"step": getStepId(),
	    	"projNo": $("#projNo").val(),
	    	"busRecordId": $("#surveyId").val() || '-1'
	    });
	 },300);
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
