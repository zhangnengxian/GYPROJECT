var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var mytable,//工程列表
searchData={},
flag=1;
/**
 * 加载工程列表
 */
var handlePreinspection = function() {
	"use strict";
    if ($('#preInspectionTable').length !== 0) {
    	$('#preInspectionTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//隐藏遮罩
   			$('#preInspectionTable').hideMask();
   			//查询基础基础条件
   			$("#preInspectionTable_filter input").attr("placeholder","工程编号");
    		//加载右侧页面
    		//$("#pre_inspection_panel_box").cgetContent("preinspection/viewPage");
   			//绑定单击事件 弹出搜索框
   			searchPop();
   			//预验收监听方法
   			registerMonitor();
   		//跳转链接
    		if (crossPageBus) {
    			getSidebarMenu(11, true);
    			checkSidebarMenu(crossPageBus.hash)
    				//跳转后销毁对象
    			crossPageBus = null
    		}
   			setTimeout(function(){
   			    $("#preInspectionTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
				{ text: '查询', className: 'btn-sm btn-query m-l-5 searchBtn' },
				{ text: '预验', className: 'btn-sm btn-query business-tool-btn m-l-5 registerBtn' }
             ],
             //启用服务端模式，后台进行分段查询、排序
		     serverSide:true,
             ajax: {
                url: 'preinspection/queryProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
             },
             //ajax: "projectjs/complete/json/pre_inspection.json",
             responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
             select: true,  //支持多选
             columns: [
                 {"data":"projId",class:'none never'},
				 {"data":"projNo"},
				 {"data":"projName"},
				 {"data":"projStatusDes"},
					{"data":"isSpecialSign",className:"none never",
						"createdCell": function (td, cellData, rowData, row, col) {
							if(cellData=='1'){
								$(td).parent().children().css("color", "rgb(255, 99, 95)");
							}
						}
	  				}
				 /*,
				 {"data":"overdue",className:"none never"}*/
			 ],
			 columnDefs: [{
				 "targets": 0,
				 "visible":false
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
				}
			 ],
			 fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
					if(aData.overdue){
						$(nRow).addClass("expired-proect");
					}
				}
        });
    }
}

/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	flag=1;
	if($("#post").val().indexOf($("#sujgj").val())<0 && $("#post").val().indexOf($("#suCsePost").val())<0){
		$(".CheckSave").addClass("hidden");
	}else{
		$(".CheckSave").removeClass("hidden");
	}
	$('.editbtn').addClass('hidden');
	trSData.t&&trSData.t.cgetDetail('preinspectionForm','preinspection/viewPreInspection','',callback);
	$("input:radio").removeAttr('checked');
	$('form:input').not(":radio").val('');
	$("#materialCheckForm").formReset();
	trSData.t && trSData.t.cgetDetail('materialCheckForm','preinspection/preInspectionRecordMaterial','',detailCallback);
	$("#qualityCheckForm").formReset();
	trSData.t && trSData.t.cgetDetail('qualityCheckForm','preinspection/preInspectionRecordQuqlity','',detailCallback);
	$("#dataCheckForm").formReset();
	trSData.t && trSData.t.cgetDetail('dataCheckForm','preinspection/preInspectionRecordData','',detailCallback);
	$("input:radio").attr("disabled","disabled");
}



var detailCallback = function(){
	if(flag){
		$("input:radio").attr("disabled","disabled");
		$("#materialCheckForm").toggleEditState(false);
		$("#qualityCheckForm").toggleEditState(false);
		$("#dataCheckForm").toggleEditState(false);
	}else{
		$("input:radio").attr("disabled",false);
		$("#materialCheckForm").toggleEditState(true);
		$("#qualityCheckForm").toggleEditState(true);
		$("#dataCheckForm").toggleEditState(true);
	}
	$(".tab-content").hideMask();
}

var callback = function(data){
	$("#actualCompleteDate").val(data.actualCompleteDate);
	$("#actualCompleteDate").attr("disabled","disabled");
	$('.editbtn').addClass('hidden');
	if($('input[name="isBack"]:checked').val()=='1'){
		$(".backRemark").removeClass("hidden");
	}else{
		$(".backRemark").addClass("hidden");
	}
	//现场代表签字
	if($("#isBuilderSign").val()!=''){
		$(".builderSign").removeClass("hidden");
	}else{
		$(".builderSign").addClass("hidden");
	}
	//签字通知标准配置的数据填充签字职务
	if(data.signNoticeStandards){
		$.each(data.signNoticeStandards,function(i,e){
			$("#"+e.fieldName+"_postType").val(e.post);
		})
	}
}
/**
 * 弹屏监听方法
 */
var searchPop = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#preinspection/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone,
			cancel:clearForm
		});
	});
	//基础条件查询添加监听
	$('#preInspectionTable_filter input').on('change',function(){
		var projNo = $('#preInspectionTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#preInspectionTable').cgetData(true,preInspectionTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#preInspectionTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});			
}


//查询弹出屏，点击确定后 
var searchDone = function(){
	searchData = $('#searchForm_preinsection').serializeJson();
	var projNo = $("#preInspectionTable_filter input").val();
	searchData.projNo=projNo;
	$('#preInspectionTable').cgetData(true,preInspectionTableCallBack);  //列表重新加载
}
//查询弹出屏，点击取消后
var clearForm=function(){
}

var preInspectionTableCallBack = function(){
	var len = $('#preInspectionTable').DataTable().ajax.json().data ? $('#preInspectionTable').DataTable().ajax.json().data.length : $('#preInspectionTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $("#preinspectionForm")[0].reset();
		 $("#materialCheckForm").formReset();
		 $("#selfcheckformtitle").formReset();
		 $("#qualityCheckForm").formReset();
		 $(".clear-sign").click();//签名清空
		 $("#preinspectionForm").toggleEditState(false);
		 $('.editbtn').addClass('hidden');
	}
}
//预验收监听,预验收之前先查询是否有竣工报告
var registerMonitor = function(){
	$('.registerBtn').off('click').on('click',function(){
		if($('#preInspectionTable').find('tr.selected').length>0){
			var projId = trSData.preInspectionTable.json.projId;
			var param = {};
			param.projId = projId;
			$.ajax({
				type:'post',
				url:'preinspection/getCompleteReport',
				data:param,
				success:function(data){
					if (data == "true"){
						backCallFunction();
					}else if (data == "false") {
						alertInfo('无竣工报告，请先通知施工员发起竣工报告！');
					}
				}
			});
			
		}else{
			alertInfo('请选择要登记的工程记录！');
		}
	});
}


var backCallFunction = function () {
	flag = 0;
	var sysDate = timestamp($("#sysDate").val());
	console.info(sysDate);
    if(!$("#pmDate").val()) $("#pmDate").val(format(sysDate,"default"));//项目经理签字时间
    if(!$("#cesDate").val()) $("#cesDate").val(format(sysDate,"default"));//现场监理签字时间
	//维护按钮显示出来
	$('.editbtn').removeClass('hidden');
	//切换可编辑状态
	// $('#preinspectionForm').toggleEditState(true);
    //根据职务过滤可编辑项
    getSignStatusByPostId($("#post").val(),"preinspectionForm");
	$("#materialCheckForm").toggleEditState(true);
	$("#qualityCheckForm").toggleEditState(true);
	$("#dataCheckForm").toggleEditState(true);
	//radio可编辑
	$("input:radio").attr("disabled",false);
	$("input:radio").removeClass("disabled");
	$("input:checkbox").attr("disabled",false);
}
/**
 * 初始化工程列表
 */
var preinspection = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){    
        		//handlePreinspection();
        		$('[href="#materialCheck"]').on("shown.bs.tab", function(){
            		//加载资料自检页面
            		if(!$("#pre_materialCheck_panel_box").children().length){
            			$("#pre_materialCheck_panel_box").cgetContent("preinspection/materialCheck",{corpId:trSData.preInspectionTable.json.corpId},materialCheckCallback);
            		}
            	    setTimeout(function(){
            	    	$("#materialCheckForm").styleFit();
            	    },120);
            	});


        		$('[href="#qualityCheck"]').on("shown.bs.tab", function(){
            		//加载质量自检页面
            		if(!$("#pre_qualityCheck_panel_box").children().length){
            			$("#pre_qualityCheck_panel_box").cgetContent("preinspection/qualityCheck",{corpId:trSData.preInspectionTable.json.corpId},qualityCheckCallback);
            		}
            		setTimeout(function(){
            	    	$("#qualityCheckForm").styleFit();
            	    },120);
            	});
        		
        		
        		
        		$('[href="#dataCheck"]').on("shown.bs.tab", function(){
            		//加载质量自检页面
            		if(!$("#pre_dataCheck_panel_box").children().length){
            			$("#pre_dataCheck_panel_box").cgetContent("preinspection/dataCheck",'',dataCheckCallback);
            		}
            		
            		setTimeout(function(){
            	    	$("#dataCheckForm").styleFit();
            	    },120);
            	});
        		
        		
            	$('[href="#checkList"]').on("shown.bs.tab", function(){
            		setTimeout(function(){
            	    	$("#preinspectionForm").styleFit();
            	    },120);
            		$("#preinspectionForm").hideMask();
            	});
        	    $("#preinspectionForm").styleFit();

        	    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5").handleSignature(); 
        	    handlePreinspection();
        	});
        }
    };
}();

var materialCheckCallback = function(){
	if(flag){
		$("input:radio").attr("disabled","disabled");
	}
	$("#materialCheckForm").formReset();
	trSData.preInspectionTable.t && trSData.preInspectionTable.t.cgetDetail('materialCheckForm','preinspection/preInspectionRecordMaterial','',detailCallback);
}
var qualityCheckCallback = function(){
	if(flag){
		$("input:radio").attr("disabled","disabled");
	}
	$("#qualityCheckForm").formReset();
	trSData.preInspectionTable.t && trSData.preInspectionTable.t.cgetDetail('qualityCheckForm','preinspection/preInspectionRecordQuqlity','',detailCallback);
}


var dataCheckCallback=function(){
	if(flag){
		$("input:radio").attr("disabled","disabled");
	}
	$("#dataCheckForm").formReset();
	trSData.preInspectionTable.t && trSData.preInspectionTable.t.cgetDetail('dataCheckForm','preinspection/preInspectionRecordData','',detailCallback);
}
