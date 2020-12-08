/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
/**查询条件*/
var visaQualititiesTable,engineeringTable,materialListTable,materialListTablePop;;
var searchData={},projId1,
TsearchData={},mcData = {},materialData={},rowData={},
sum=0;
var menuId = "120206";
searchData.menuId="120206";
var accessoryData = {},accessoryTable;
var histSearchData = {};
var projNoLength;
//如果是手机端显示8个汉字，如果是电脑显示12个汉字
if(_inApk){
	projNoLength=6;
}else{
	projNoLength=8;
}
handleEngineeringRecord = function() {
	"use strict";
	searchData.projId = getProjectInfo().projId;
    if ($('#engineeringTable').length !== 0) {
    	$('#engineeringTable').on( 'init.dt',function(){
    		//搜索
    		$("#engineeringTable_wrapper").prepend('<div class="dateFilter pull-left"><input type="text" class="form-control input-sm " placeholder="签证编号" value=""></div>');
    		//$("#engineeringTable_wrapper").prepend('<div class="dateFilter pull-left"><input type="text" class="form-control input-sm datepicker-default" placeholder="签证日期" value=""></div>');
    		$('.datepicker-default').datepicker({
    			autoclose: true
    		});
    		if($("#loginPost").val().indexOf($("#CONSTRUCTION").val())<0){
    			$(".addBtn").addClass("hidden");
    		}else{
    			$(".addBtn").removeClass("hidden");
    		}
    		if($("#loginPost").val().indexOf($("#CONSTRUCTION").val())<0){
    			$(".pushBtn").addClass("hidden");
    		}else{
    			$(".pushBtn").removeClass("hidden");
    		}
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//隐藏遮罩
   			$('#engineeringTable').hideMask();
   		    //增加监听
   	    	addMonitor();
   	    	//修改监听
   	    	modifyMonitor();
   	    	//查询监听
   	    	searchMonitor();
            resetMonitor();
   	    	//推送监听
   	    	pushSpa1();
   	    	//确认
   	    	confirmMonitor();
   	    	voidMonitor();  //作废监听
   	    	if($("#loginPost").val()==$("#fieldAdministrator").val()){
   				$(".pushButton").removeClass("hidden");
   			}else{
   				$(".pushButton").addClass("hidden");
   			}
   	    	queryCheckRole();
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
            dom: 'Brtip',
            bStateSave:true,
            buttons: [
                      { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
                      { text: '推送', className: 'btn-sm btn-query business-tool-btn pushBtn' },
                      { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' },
                      { text: '作废', className: 'btn-sm btn-warn business-tool-btn  hidden voidBtn'},
					  { text: '重置', className: 'btn-sm btn-warn business-tool-btn hidden resetBtn'},
                	  { text: '重置信息', className: 'btn-sm business-tool-btn hidden showAbandonedRecord'}
                /*{ text: '确认', className: 'btn-sm btn-query business-tool-btn hidden  confirmBtn' }*/
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'engineering/queryEngineeringVisa',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
//            ajax: 'projectjs/constructmanage/json/design-alteration-record.json',
            select: true,  //支持多选
            columns: [
                {"data":"evId",className:"none never"},
          		{"data":"visaDate", responsivePriority: 2},
				{"data":"evNo",responsivePriority: 2},
    			{"data":"projName", responsivePriority: 1},
    			{"data":"auditStateDes", responsivePriority: 3},
    			{"data":"isPass"},
    			{"data":"budgeterAudit"},
    			{"data":"auditState",className:"none never","createdCell": function (td, cellData, rowData, row, col) {
					if(cellData=='3'){
						console.info(cellData);
						$(td).parent().children().css("background", "rgb(255, 219, 219)");
						//$(td).attr("id",row);
						}
					}
				},
				{"data":"isPass",
					className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						console.info("cellData---"+cellData);
						if(cellData=="0"){
							$(td).parent().children().css("background", "rgb(255, 219, 219)");
						}
					}
				},
                {"data":"projId",className:"none never"},
                {"data":"projNo",className:"none never"},
				{"data":"evState",
					className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						console.info("cellData---"+cellData);
						if(cellData=="-1"){
							$(td).parent().children().css("background", "rgb(255, 219, 219)");
						}
					}
				},
                {"data":"quantitiesTotal", responsivePriority: 4},
                {"data":"isBudgetBook", responsivePriority: 5},
                {"data":"todoer", responsivePriority: 6}
			],
			columnDefs: [{
		             "targets": 0, 
		             "visible":false
			},{
				"targets": 1,
				"render":function(data,type,row,meta){
					if(data !=="" && data!==null){
						return format(data);
					}else{
						return data;
					}
				}
			},{
				"targets":3,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: projNoLength, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
			
				"targets":4,
				 "orderable":false
			},{
				"targets":5,
				 "orderable":false
			},{
				"targets":5,
				//长字符串截取方法
				render: function ( data, type, row, meta ) {
					console.info("data---"+data);
					if(type === "display"){
						
						
						if(row.isPass=="0"){
							data='审核未通过';
						}else{
							if(row.suResult=="1"&& row.cmoPrincipalResult=="1"){
								data='审核通过';
							}else{
								data='未审核完成';
							}
						}
						return data;
					}else{
						return data;
					}
			    }
			},
			{
				"targets":13,
				render: function ( data, type, row, meta ) {
						if (row.isBudgetBook == "0") {
							data='未上传';
						} else if(row.IsBudgetBook !=="0") {
							data='已上传(数量:'+row.isBudgetBook+')';
						}
						return data;
					
			    }
			}],
			responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            }
        });
        
    }
},

//查询监听
searchMonitor=function(){
    $('.dateFilter input').off("keyup").on('keyup', function(event) {
	    if (event.keyCode == '13') {
			var evNo = $('.dateFilter input').val();
			searchData = {};
			searchData.evNo = evNo;;
			searchData.projId = getProjectInfo().projId;
			$('#engineeringTable').cgetData(true);  //列表重新加载
	    }
	});
};
voidMonitor = function(){
	$(".voidBtn").off("click").on("click",function(){
		var url = "#engineering/optionPage";
		var popoptions = {
			title: '作废',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
};


 resetMonitor=function () {
     $(".resetBtn").off("click").on("click",function(){
         var json= trSData.engineeringTable.json;
        if (json){
            var popoptions = {
                title: '重置原因',
                content: "#dataTableInfo/reasonPopPage?menuId=120206&businessId="+json.evId,
                accept: reasonDone
            };
            $("body").cgetPopup(popoptions);//加载弹屏

        }else{
            alertInfo("请选择记录")
        }
     });


     $(".showAbandonedRecord").off("click").on("click",function(){
         var param="?businessId="+trSData.engineeringTable.json.evId;
         $('body').cgetPopup({//加载弹屏
             title: '重置记录',
             content: '#dataTableInfo/abandonedRecordPopPageDetail'+param,
             accept: function () {},
             size:'large'
         });
	 })

};



var reasonDone=function () {
	var abRecordData={
		businessId:$("#abBusinessId").val(),
		projId:trSData.engineeringTable.json.projId,
		stepId:$("#abStepId").val(),
		stepName:$("#abStepId").find("option:selected").text(),
		abandonedReason:$("#abReason").val()
	};

    var pf = $("#abandonedRecordPopForm");
    if (pf.parsley().isValid()) { //验证必填是否已填写
         var json= trSData.engineeringTable.json;
        Base.subimt('engineering/updateEngineeringVisa', "POST",{evId:json.evId,auditState:$("#abStepId").val()}, function (data) {
            var content = data ? "重置成功！" : "重置失败！";
            Base.subimt('dataTableInfo/saveResetRecord', "POST", JSON.stringify(abRecordData), function (falg) {
                tips(content);
                $("#engineeringTable").cgetData(true);//列表重新加载
                $("#abandonedRecordPopForm")[0].reset();
			});
        });
    }else {
        pf.parsley().validate();
        return false;
	}
};

function resetBtnControl(json){
	var loginPost = $("#loginPost").val();
	var dataAdminPost = $("#dataAdminPost").val();
	var budgetGroupLeader = $("#budgetGroupLeader").val();
	//已完成-作废的可以重置
    if ((json.auditState=='4' || json.auditState=='-1') && (loginPost.search(dataAdminPost) != -1 || loginPost.search(budgetGroupLeader) != -1)){//审核通过并且有数据管理员职务显示重置按钮
		$(".resetBtn").removeClass("hidden")
    }else {
        $(".resetBtn").addClass("hidden")
	}



    $(".showAbandonedRecord").addClass("hidden");
	var  abRecord={businessId:json.evId};
    Base.subimt('dataTableInfo/queryAbandonedRecord', "POST", JSON.stringify(abRecord), function (data) {
       if (data){
       	$(".showAbandonedRecord").removeClass("hidden")
       }
	})
}



function tips(content) {
    var myoptions = {
        title : "提示信息",
        content : content,
        accept :confirm,
        chide : true,
        icon : "fa-check-circle"
    };
    $("body").cgetPopup(myoptions);
}
var confirm=function () {};


//确定作废
 var searchDone=function(){
	var searchData = $("#searchForm_engineer").serializeJson();
 	var evId=trSData.engineeringTable.json.evId;
 	if(searchData.reason == null || searchData.reason == ""){
 		  $("body").cgetPopup({
          	title: "提示信息",
          	content: "请填写作废理由！",
          	accept: popClose,
          	chide: true,
          	icon: "fa-exclamation-circle",
               newpop: 'new'
          });
 		  return false;
 	}else {
        Base.subimt("engineering/deleteEV", "POST", {evId: evId, reason: searchData.reason}, function (data) {
            if (data) {
                $("body").cgetPopup({
                    title: "提示信息",
                    content: "签证已作废!",
                    accept: visaBackSure,
                    chide: true,
                    icon: "fa-check-circle",
                    newpop: 'new'
                });
            } else if (data === "false") {
                $("body").cgetPopup({
                    title: "提示信息",
                    content: "数据保存失败, 请重试!",
                    accept: popClose,
                    chide: true,
                    icon: "fa-exclamation-circle",
                    newpop: 'new'
                });
            }
        })
    }
 };
 
 var visaBackSure=function(){
		$("#engineeringTable").cgetData();  //重新加载列表
	};

/** 选中行时，查询详述
 */
trSelectedBack = function(v, i, index, t, json){
    resetBtnControl(json);
    
	$("#businessOrderId").val(json.evId);
	if(json.evState=="-1"){
		$(".updateBtn").addClass("hidden");
		$(".pushBtn").addClass("hidden");
		$(".voidBtn").addClass("hidden");
		
	}else{
		if(json.auditState=='1'){//待审核
			$(".updateBtn").addClass("hidden");
			$(".pushBtn").addClass("hidden");
		}else if(json.auditState=='2'||json.auditState=='6'){//待推送
			$(".updateBtn").removeClass("hidden");

			if($("#loginPost").val().indexOf($("#CONSTRUCTION").val())<0){
				$(".pushBtn").addClass("hidden");
			}else{
				$(".pushBtn").removeClass("hidden");
			}

			if(json.isPass=="0"){//审核不通过可以推送
				$(".pushBtn").addClass("hidden");
			}

		}else if(json.auditState=='3' || json.isPass=="0"){//未通过
			$(".updateBtn").removeClass("hidden");
			//if($("#loginPost").val().indexOf($("#builderPostType").val())<0&&$("#loginPost").val().indexOf($("#suJgjPostType").val())<0){
			if($("#loginPost").val().indexOf($("#CONSTRUCTION").val())<0){
				$(".pushBtn").addClass("hidden");
			}else{
				$(".pushBtn").removeClass("hidden");
			}
		}else{//已通过
			$(".updateBtn").addClass("hidden");
			$(".pushBtn").addClass("hidden");
		}
	}
	//传false表示不可编辑
	$("#engineeringForm").toggleEditState(false);
	//维护按钮
	$(".toolBtn").addClass("hidden");
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
	t.cgetDetail('engineeringForm', 'engineering/viewEngineeringVisa?menuDes='+menuDesc+'&menuId='+menuId, '',detailBack);
	//施工单位确认签证审核金额
	if($("#unitType").val()==$("#cuUnitType").val() && $("#loginPost").val().indexOf($("#budgeterPost").val())>=0 && json.cuReState=='-1'){
		$(".confirmBtn").removeClass("hidden");
	}else{
		$(".confirmBtn").addClass("hidden");
	}
	//判断是否是监理或者现场代表以及签证记录是否是推送状态,是则显示作废按钮
	//作废按钮开放，待推送（2）、待调整签证（6）、审核不通过（3）
	//if((($("#loginPost").val().indexOf($("#builderPostType").val()) >=0) ||($("#loginPost").val().indexOf($("#suJgjPostType").val()) >=0) ) && (json.auditState.indexOf('2') >=0)){  
	if((json.auditState.indexOf('2') >=0) || (json.auditState.indexOf('6') >=0) || (json.auditState.indexOf('3') >=0)){
        $(".voidBtn").removeClass("hidden");
	}else{
		 $(".voidBtn").addClass("hidden");
	}
	//待签证调整状态，现场代表可以修改
	if(json.auditState.indexOf('6') >=0 ){
		if($("#loginPost").val().indexOf($("#builderPostType").val())>=0){
			$(".updateBtn").removeClass("hidden");
		}else if ($("#loginPost").val().indexOf($("#suJgjPostType").val())>=0){
			$(".updateBtn").removeClass("hidden");
		}else if($("#unitType").val()==$("#cuUnitType").val()){
			$(".updateBtn").removeClass("hidden");
		}else{
			$(".updateBtn").addClass("hidden");
		}
	}
	//施工单位预算员可修改签证报送金额
	if($("#unitType").val()==$("#cuUnitType").val() && $("#loginPost").val().indexOf('41')>=0){
		if((json.auditState.indexOf('2') >=0) || (json.auditState.indexOf('6') >=0) || (json.auditState.indexOf('3') >=0)){
			$(".updateBtn").removeClass("hidden");
		}else{
			$(".updateBtn").addClass("hidden");
		}
	}
}


function timeFormat(value) {
	if (!checkDateFormat(value) && value!=""){
		return format(value,"all")
	}else {
		return value;
	}
}



//详述回调
var detailBack=function(data){
	 if($("#backReason").val()==""){
		 $(".backReason").addClass("hidden");
	 }else{
		 $(".backReason").removeClass("hidden");
	 }
	 $('#constructionUnit').val(getProjectInfo().cuName);
    $("#alPath").val($("#projNo").val()+"/"+getStepId());  //路径工程编号连接上菜单ID
    
    $(".Load_Button").attr("href","/accessoryCollect/openFile?id="+$("#fileUrl").val());
	 //时间戳转日期
	$("#visaDate").val(format($("#visaDate").val(),"all"));
	$("#custAuditDate").val(timeFormat($("#custAuditDate").val(),"all"));
	$("#suAuditDate").val(timeFormat($("#suAuditDate").val(),"all"));
	$("#builderAuditDate").val(timeFormat($("#builderAuditDate").val(),"all"));


	if(data.drawName){
		$(".hasVal").removeClass("hidden");
		$(".noVal,.del_btn").addClass("hidden");
		$(".noVal+#filePreviews tr").remove();
	}else{
		$(".hasVal").addClass("hidden");
		$(".noVal").removeClass("hidden");
	
	}
	if(data.suResult.length<=0){
		$("#engineeringForm input[name='suResult']").removeAttr("checked");
	}
	if(data.cmoPrincipalResult.length<=0){
		$("#engineeringForm input[name='cmoPrincipalResult']").removeAttr("checked");
	}
	 $("#engineeringForm").toggleEditState(false).styleFit();
	showReport();
	
	histSearchData.projId = getProjectInfo().projId; 
	histSearchData.businessOrderId=$("#evId").val();
	if($.fn.DataTable.isDataTable('#auditHistoryTable')){
		//初始化过
		$("#auditHistoryTable").cgetData(false,function(){
		
		});
	}else{
		handleAuditHistory();
	}
	//签证有异议显示
	if(data.cuReState=='0'){
		$(".cuReState,.cuReReason").removeClass("hidden");
	}else{
		$(".cuReState,.cuReReason").addClass("hidden");
	}
	//上传了附件，已验证显示的推送按钮才显示，否则，不显示
	/*if(!$(".pushBtn").is(":hidden") && !data.isUploadFile ){
		$(".pushBtn").addClass("hidden");
	}*/

	$('#cmoPrincipalResult').val(data.cmoPrincipalResult);


    var post = getProjectInfo().post;
    if((data.cmoPrincipalResult=='1' ||data.suResult=='1') && post.indexOf($('#CONSTRUCTION').val()) != -1){//审核通过后施工员不可修改
        //如果施工单位还没有签完字，则签证内容不可修改，但是按钮修改按钮可见
    	if(data.suPrincipal=='' || data.builder==''){
        	$(".updateBtn").removeClass("hidden");
        	$("#evContent").removeClass("field-editable");
        	$("#evContent").addClass("field-not-editable");
        	$("#evContent").attr("readonly",true);
        }else{
        	$("#evContent").removeClass("field-not-editable");
        	$("#evContent").addClass("field-editable");
        	$("#evContent").attr("readonly",false);
        	$(".updateBtn").addClass("hidden");
        }
    }
    if((data.cmoPrincipalResult=='0' ||data.suResult=='0') && post.indexOf($('#CONSTRUCTION').val()) != -1){//审核不通过后施工员可修改
        $(".updateBtn").removeClass("hidden");
    }

}
//增加监听
addMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		$(".noVal").removeClass("hidden");		 
		$(".hasVal").addClass("hidden");
		$("#drawName").val("");
		$("#evId").val("");
        $("#evNo").val("");
		
		//$('#engineeringTable tr.selected').removeClass("selected");
		$('#engineeringTable').clearSelected();
		//清空
		//$('#engineeringForm').formReset();
		$('#engineeringForm .addClear').val('');
		$('#engineeringForm input[type="radio"]').removeAttr("checked");
		$('.field-editable').val('');
		$(".clear-sign").click();
		
        //根据职务过滤可编辑项
        getSignStatusByPostId(getProjectInfo().post,"engineeringForm");
        //可编辑
		//$("#engineeringForm").toggleEditState(true);
		//$("#engineeringForm").parsley().reset();
		$(".toolBtn").removeClass("hidden");
		//$('ul.nav>li').removeClass("active");
		$('#visaInfo').tab('show');
		//$('#constructionUnit').val($('#constructionUnit1').val());
		
		//获取服务器时间
		var evId=$("#evId").val();
		if(evId==''){
			evId='-1';
		}
		var json={};
		json.id=evId;
		$.ajax({
			type:'post',
			url:'engineering/viewEngineeringVisa',
			data:json,
			dataType: 'json',
	        success:function(data){
	        	if(data!=="false"){
	        		//时间戳转日期
	        		$("#visaDate").val(format(data.visaDate)); 
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            console.warn("获取签证详述失败！");
	        }
		});
		showReport();
		accessoryData.busRecordId ="-1";
	});
}
var pushSpa1 = function(){
	$('.pushBtn').off().on('click',function(){
		var len=$("#engineeringTable").find('tr.selected').length;
		if(len>0){
			var submitAmount = trSData.engineeringTable.json.submitAmount;
			if(submitAmount==''){
				alertInfo("请先填写签证报送金额！");
				return;
			}
           $("body").cgetPopup({
               	title: "提示信息",
               	content: "是否将选中的签证单推送至预算调整？",
               	accept: surePush1,
               	newpop: 'new',
               	icon: "fa-check-circle"
           });
		}else{
			alertInfo('请选择要推送的签证单！');
		}
	});
}
var surePush1 = function(){
	var evId=trSData.engineeringTable.json.evId;
	$.ajax({
		type:'post',
		url:'engineering/pushEvToBudget?menuId='+menuId,
		contentType: "application/json;charset=UTF-8",
        data: evId,
        success:function(data){
        	var content = "推送成功！";
        	if(data=="false"){
        		content = "推送失败！";
        	}else if(data=="IsNotSignComplete"){
        		content = "签证签字未完成，不能推送！";
        	}else if(data=="no"){
        		content = "请先上传预算！";
        	}
        	var myoptions = {
                	title: "提示信息",
                	content: content,
                	accept: tableReload,
                	chide: true,
                	icon: "fa-check-circle",
                	newpop: 'new'
                	
            }
            $("body").cgetPopup(myoptions);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("数据保存失败！");
        }
        
	})
}
//确认事件
var confirmMonitor = function(){
	$('.confirmBtn').off().on('click',function(){
		var len=$("#engineeringTable").find('tr.selected').length;
		if(len>0){
			$('#visaInfo').tab('show');
            //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"engineeringForm");
			// //切换可编辑状态
			// $("#engineeringForm").toggleEditState(true);
			//维护按钮显示出来
			$(".toolBtn").removeClass("hidden");
			$(".Load_Button").removeClass("hidden");
			$(".btn-danger").removeClass("hidden");
			$('#constructionUnit').val(getProjectInfo().cuName);
			//确认
			$(".cuReState").removeClass("hidden");
			if($('input[type="radio"][name="cuReState"]:checked').val()=='0'){
				$(".cuReReason").removeClass("hidden");
			}else{
				$(".cuReReason").addClass("hidden");
			}
		}else{
			alertInfo('请选择要确认的签证单！');
		}
	});
}
/*var pushSpa = function(){
	$('.pushBtn').off().on('click',function(){
		console.info("click");
		var len=$("#engineeringTable").find('tr.selected').length;
		if(len>0){
           $("body").cgetPopup({
               	title: "提示信息",
               	content: "是否将选中的签证单推送至审核？",
               	accept: surePush,
               	newpop: 'new',
               	icon: "fa-check-circle"
           });
		}else{
			alertInfo('请选择要推送的签证单！');
		}
	});
}*/
var surePush=function(){
	var evId=trSData.engineeringTable.json.evId;
	$.ajax({
		type:'post',
		url:'engineering/pushEv',
		contentType: "application/json;charset=UTF-8",
        data: evId,
        success:function(data){
        	var content = "推送成功！";
        	if(data=="false"){
        		content = "推送失败！";
        	}else if(data=="pass"){
        		content = "此类签证金额较少无需上级领导审核，已通过！";
        	}
        	var myoptions = {
                	title: "提示信息",
                	content: content,
                	accept: tableReload,
                	chide: true,
                	icon: "fa-check-circle",
                	newpop: 'new'
                	
            }
            $("body").cgetPopup(myoptions);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("数据保存失败！");
        }
        
	})
}
var tableReload=function(){
	$('#engineeringTable').cgetData(true);
}
//修改监听方法
modifyMonitor = function(){
	$(".updateBtn").off('click').on("click",function(){
		if($("#engineeringTable").find("tr.selected").length>0){
			//$('ul.nav>li').removeClass("active");
			$('#visaInfo').tab('show');
            //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"engineeringForm");
			// //切换可编辑状态
			// $("#engineeringForm").toggleEditState(true);
			//维护按钮显示出来
			$(".toolBtn").removeClass("hidden");
			$(".Load_Button").removeClass("hidden");
			$(".btn-danger").removeClass("hidden");
			$('#constructionUnit').val(getProjectInfo().cuName);

			//施工或竣工中
        	/*if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#engineeringForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#engineeringForm").find(".sign-data-input").toggleSign(false);
        	}*/
			//去掉现场代表可修改签证内容
            /*var post = getProjectInfo().post;
            if (post.indexOf($('#builderPostType').val()) != -1){//现场代表可修改签证内容
                $('.controlField').attr("readonly",false);
            }*/

		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
},

//切换到签证信息
$('#visaInfo').on("show.bs.tab", function(){
	if($("#engineeringTable").find("tr.selected").length>0){
		//$(".toolBtn").addClass("hidden");
		//trSData.t.cgetDetail('engineeringForm','','');
		$("#engineeringForm").toggleEditState(false);
		if(!$.fn.DataTable.isDataTable('#visaQualititiesTable')){
			//visaQuantitiesTableInit();
		}else{
			if($("#evId").val()==""){
				TsearchData.projId="-1";
				TsearchData.evId="-1";
				$("#visaQualititiesTable").cgetData();
			}else{
				TsearchData.projId=($("#projId").val());
				TsearchData.evId=$("#evId").val();
				$("#visaQualititiesTable").cgetData();
			}
		}
	}else{
		//$('#engineeringForm').deserialize(getProjectInfo());
		
		$('.field-editable').val('');
		
		if($("#drawName").val()){
   		 $(".hasVal").removeClass("hidden");
   		 $(".noVal").addClass("hidden");
   		 $(".noVal+#filePreviews tr").remove();
	   	 }else{
	   		 $(".noVal").removeClass("hidden");
	   		 
	   		 $(".hasVal").addClass("hidden");
	   	 }
	}
	setTimeout(function(){
	    $("#projectImagesList").getImagesList({
	    	"projId": getProjectInfo().projId,
	    	"step": getStepId(),
	    	"projNo": getProjectInfo().projNo,
	    	"busRecordId": $("#evId").val() || '-1'
	    });
	},300);
	
});
$('#visaTab').on("show.bs.tab", function(){
	$("#engineeringTable").cgetData();  //列表重新加载
});


var engineeringRecord = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript('js/ellipsis.js').done(function() {
        		$.getScript('assets/plugins/jedate/jedate.js').done(function() {
        			handleEngineeringRecord();
        			showReport();
        		});
        	});
        }
    };
}();



//初始化签证标准列表

var visaQuantitiesTableInit=function() {
	"use strict";
	if($("#evId").val()==""){
		TsearchData.projId="-1";
		TsearchData.evId="-1";
	}else{
		TsearchData.projId=($("#projId").val());
		TsearchData.evId=$("#evId").val();
	}
    if ($('#visaQualititiesTable').length !== 0) {
    	visaQualititiesTable=$('#visaQualititiesTable').on( 'init.dt',function(){
    		//默认选中第一行
    		$(this).bindDTSelected(purgeAuditSelectedBack,true);
   			//隐藏遮罩
   			$('#visaQualititiesTable').hideMask();
   			$(this).bindInputsChange();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:true,
            ajax: {
                url: 'engineering/queryVisaQuantitiesRecord',
                type:'post',
                data: function(d){
                   	$.each(TsearchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/inspection/json/purge.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"id",className:"none never"},
                {"data":"des"},
	  			{"data":"quantitiesNum",className:"text-right"}, 
	  			{"data":"actualNum",className:"text-right"}, 
	  			{"data":"unitDes"}
			],
			columnDefs: [{
					"targets":0,
					"visible":false
				},{
					 'targets':2,
					 "render": function ( data, type, row, meta ) {
							if(data!=="" && data!==null){
								return parseFloat(data).toFixed(2);
							}else{
								return data;
							}
						},
				 	},{
					targets:3,
					render:function(data,type,row,meta){
						if(data === null){
							data = "";
						}
						if(type === "display"){
							var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm text-right"  name="'+row.id+ '_actualNum" id="' + row.id + '_actualNum" value="'+data+'"></div>';
							return tdcon;
						}else{
							return data;
						}
					}
				}]
        }).on("draw.dt",function(){
        	$(this).DataTable().responsive.recalc();
        });
    }
};
var purgeAuditSelectedBack=function(){}



/**切换到材料变更*/
$("#materialChangeTab").on("shown.bs.tab",function(){
	$("#flag").val("0");
	//postLimit();
	//if($("#cmId").val() || $("#state").val()){
	if(trSData.engineeringTable.json){	
		state=2;
		console.info("jsonId..."+trSData.engineeringTable.json.cmId);
	}else{
		state=1;
	}
	if((jsonLength(trSData.engineeringTable))){
		if(!$.fn.DataTable.isDataTable('#materialListTable')){
			handleMChangeList();
		}else{
			 mcData.mcType=$("#mcType").val();
			 mcData.cmId=$("#evId").val();
			 mcData.projId=$("#projId").val();
			 $("#materialListTable").cgetData(true,mcBack("mcBtn"));
		}
	}else{
		 mcData.mcType="-1";	
 	     mcData.cmId="-1";	 
 	     handleMChangeList();
 	     btnHidden("mcBtn");
	}

});



/**
 * 材料变更(下)
 */
var handleMChangeList = function() {
	"use strict";
	if((jsonLength(trSData.engineeringTable))){
		mcData.mcType=$("#mcType").val();	
	    mcData.cmId=$("#evId").val();
	    mcData.projId=$("#projId").val();
	}else{
		 mcData.mcType="-1";	
 	     mcData.cmId="-1";	 
	}
	  
	    console.info(mcData);
	if ($('#materialListTable').length !== 0) {
		if (!$.fn.DataTable.isDataTable('#materialListTable')) {
	        materialListTable= $('#materialListTable').on( 'init.dt',function(){
		    	//默认选中第一行
	    		$(this).bindDTSelected("",true);
	    		rowData=materialListTable.data();
	    		//console.info("-++++---");
	    		//console.info(rowData);
		    	//隐藏遮罩
		    	$("#materialListTable").hideMask();
		    	saveMaterialList();
		    	//导入材料
		    	importMaterial();
		    	checkOut();
		    	btnHidden("mcBtn");
		        mcSelBtn();
		        $(this).bindInputsChange();
		       // materialEditLimits();
		        //postLimit();
		        delFun();//删除
		    }).DataTable({
		    	language: language_CN,
		        lengthMenu: [ 18 ],
		        dom: 'Brtip',
		        buttons: [
		            { text: '下载模板', className: 'btn-sm btn-query checkRole checkOut postLimit'},
		            { text: '导入', className: 'btn-sm btn-query business-tool-btn materialImportBtn checkRole mcBtn postLimit' },
	                { text: '调整', className: 'btn-sm btn-query mcSelBtn checkRole mcBtn postLimit' },
		            { text: '保存', className: 'btn-sm btn-query mcSaveBtn checkRole mcBtn postLimit'}/*,
		            { text: '删除', className: 'btn-sm  btn-warn business-tool-btn right-btn delBtn' }*/
		        ],
		        //serverSide: true, 
				ajax: {
				    url: 'changeRecord/queryMCList',
				    contenttype:"application/json;charset=utf-8",
				    data:function(d){
				        $.each(mcData,function(i,k){
				            d[i] = k;
				        }); 	
				    },
				    datasrc: ''
				},
				select: true,  //支持多选
		        columns: [
		            {"data":"mcId", className:"none never"},
		            {"data":"materialId", className:"none never"},
		  			{"data":"materialName",responsivePriority: 1},
		  			{"data":"materialSpec",responsivePriority: 4},
					{"data":"materialUnit",responsivePriority: 3},
					{"data":"flagDes"},
					{"data":"adjustQuantity", "className": "text-right",responsivePriority: 2}
				],
				order: [[ 0, "asc" ]],
				columnDefs: [{
					targets: 0,
					"visible":false
				},{
					targets: 6,
					render:function(data,type,row,meta){
						if(type === "display"){
							var tdcon = '<div class="tdInnerInput">';
						        tdcon += '<input class="form-control input-sm text-right" data-parsley-type="number" value=' + data + '>';						 					    
						        tdcon += '</div>';
						        return tdcon;
						}else{
							return data;
						}
					}
				}],
				responsive: {
	            	details: {
	            		renderer: function ( api, rowIdx, columns ){
	            			return renderChild(api, rowIdx, columns);
	            		}
	            	}
	            }
		    }).on("draw.dt",function(){
		    	$("#materialListForm").toggleEditState(false).styleFit();
		    });
		}else{
			materialListTable.ajax.reload();
		}
		$('#materialListTable').on("draw.dt", function(){
			rowData=materialListTable.data();
		});
	}
}

/**
 * 下载模版
 */
var checkOut = function(){
	$(".checkOut").on("click",function(){
		$("#exportExcel").submit();
	})
}

function btnHidden(cla){
	 /*console.info("state--"+state);
	 if(state==1){
		 console.info("ca11---"+cla);
      	$("."+cla).addClass("hidden");
     }else{
    	console.info("ca12---"+cla);
      	$("."+cla).removeClass("hidden");
     }
	//alert(trSData.engineeringTable.json.auditState);
	if(trSData.engineeringTable.json.auditState==='3'){
		$(".mcBtn").removeClass("hidden");
	}else{
		$(".mcBtn").addClass("hidden");
	}*/
}

//材料导入
var importMaterial=function(){
	$(".materialImportBtn").off("click").on("click",function(){
		$("#projId1").val($("#projId").val());
		$("#cmId").val($("#evId").val());
		console.info("projId11--"+$("#projId1").val());
		$("body").cgetPopup({
			title: '文件导入',
			content: "#officialDrawing/importPop?url=changeRecord/importExcel",
			accept: importM,
			nocache: true
		});
	});
}
var importM = function(){
	var mcData={};
	mcData.mcType=$("#mcType").val();	
	mcData.cmId=$("#evId").val();
	mcData.projId=$("#projId").val();
	console.info("projId2--"+projId1);
	$("#materialListTable").cgetData();
}

function mcBack(cla){
	btnHidden(cla);
	//console.info("===---Back---");	
	rowData=materialListTable.data();
	//console.info(rowData);
}

//调整
function mcSelBtn(){
	$(".mcSelBtn").on("click",function(){
		materialdata={};
		var url = "#changeRecord/materialList";
		$("body").cgetPopup({
			title: '材料列表',
			content: url,
			accept: mcDone,
			size:"large"
		});
	});
}

//调整点击确定
function mcDone(){
	var jsons=trSData.materialListTablePop.jsons;
	//console.info("--------------");
	//console.info(rowData);
    if(jsons!=undefined){
		for(var i=0;i<rowData.length;i++){
			for(var j=0;j<jsons.length;j++){
				if(jsons[j].materialId == rowData[i].materialId){
					jsons.splice(j,1);
					break;
				}
			}
		}

		for(var i=0;i<jsons.length;i++){
			jsons[i].mcType=$("#mcType").val();
			jsons[i].cmId=$("#evId").val();
			jsons[i].mcId="";
			jsons[i].adjustQuantity='';	
		}
		rowData=rowData.concat(jsons);
		materialListTable.rows.add(jsons).draw();
    }
}

var delFun=function(){
	$(".delBtn").on("click",function(){
		if($("#materialListTable").find("tr.selected").length>0){
			$("body").cgetPopup({
				title: '提示信息',
				content: '确认要删除数据吗？',
				accept: delData,
				icon: 'fa-exclamation-circle',
			});
		}else{
			alertInfo("请选择要删除的数据！");
		}
	});	
}

var delData=function(){
	$("#materialListTable").DataTable().rows( '.selected').remove().draw();// 删除本地数据
}


//弹出i屏
var rowIndex=[];
var handleMaterialList = function() {
	"use strict";

  if(!trSData.engineeringTable.json){
      materialdata.projId=-1;	
  }else{
  	  materialdata.projId=$("#projId").val();
  }
	 console.info(materialdata); 
	if ($('#materialListTablePop').length !== 0) {
		rowIndex=[];
	    materialListTablePop= $('#materialListTablePop').on( 'init.dt',function(){
	    	//默认选中第一行
  		$(this).bindDTSelected(trCmSelectedBack,false);
	    	//隐藏遮罩
	    	$("#materialListTablePop").hideMask();
	    	//搜索
  		$('#materialListTablePop_filter input').attr('placeholder','设备材料汇总表'); 
  		materialListSearchData();
	    	//console.info(".....");
	    	//console.info(rowIndex);
	        for(var m=0;m<rowIndex.length;m++){
	        	materialListTablePop.row(rowIndex[m]).nodes().to$().addClass("selected");
	        }
	    	
	    }).DataTable({
	    	language: language_CN,
	        lengthMenu: [ 18 ],
	        dom: 'fBrtip',
	        buttons: [
              
	           
	        ],
	        serverSide: true, 
			ajax: {
			    url: 'changeRecord/queryMaterialList',
			    contenttype:"application/json;charset=utf-8",
			    data:function(d){
			        $.each(materialdata,function(i,k){
			            d[i] = k;
			        }); 	
			    },
			    datasrc: 'data'
			},
			select: true,  //支持多选
	        columns: [
	            {"data":"materialId", className:"none never"},
	  			{"data":"materialName"},
	  			{"data":"materialSpec"},
				{"data":"flagDes"},
				{"data":"materialNum", "className": "text-right"}
				
			],
			order: [[ 0, "asc" ]],
			columnDefs: [{
				targets: 0,
				"visible":false,
				render:function(data,type,row,meta){
					//console.info(rowData);
					for(var i=0;i<rowData.length;i++){
						
						if(data==rowData[i].materialId){
							rowIndex.push(meta.row);
						}
					}
					return data;
				}
			}
			],
			responsive: {
          	details: {
          		renderer: function ( api, rowIdx, columns ){
          			return renderChild(api, rowIdx, columns);
          		}
          	}
          }
	    });
	}
};

function trCmSelectedBack(){
	
}


var saveMaterialList = function(){	
	$(".mcSaveBtn").on("click",function(){
		var t = $('#materialListTable');
		if(t.checkInputs()){
			var data = t.getInputsData();
			console.info("data--");
			console.info(data);
			if(data.length || materialData != null){
				resultData=[];
				for(var i=0;i<data.length;i++){
		       		var datam = data[i];
					if(datam.adjustQuantity!==""){
						datam.cmId=$("#evId").val();
						datam.projId=$("#projId").val();
		   				resultData.push(datam);
		   			}
				  }
				/*if(materialData != null){
					for(var i=0;i<materialData.result.materialList.length;i++){
						materialData.result.materialList[i].cmId=$("#cmId").val();
						resultData.push(materialData.result.materialList[i]);
					}
				}*/
	       		$.ajax({
	       			type: 'POST',
	       			url: 'changeRecord/saveMaterialChange',
	       			contentType: "application/json;charset=UTF-8",
	       			data: JSON.stringify(resultData),
	       			success: function (data) {
	       				var content = "数据保存成功！";
	       				if(data === "false"){
	       					content = "数据保存失败！";
	       				}else if(data === "true"){
	       				    mcData.cmId=$("#evId").val();
	       				    mcData.projId=projId1;
	       					$("#materialListTable").cgetData(false);  //列表重新加载	            
	       				}
	       				var myoptions = {
	                       	title: "提示信息",
	                       	content: content,
	                       	accept: popClose,
	                       	chide: true,
	                       	icon: "fa-check-circle"
	                   }
	                   $("body").cgetPopup(myoptions);
	               },
	               error: function (jqXHR, textStatus, errorThrown) {
	                   console.warn("材料清单保存失败！");
	               }
	            });
			}else{
				alertInfo("无更新记录！");
			}
		}else{
			console.info("表单校验失败，请检查并修改未通过项目！");
		}
	});
}

var materialListSearchData=function(){
	//基础条件查询添加监听
	$('#materialListTablePop_filter input').on('change',function(){
		var materialName = $('#materialListTablePop_filter input').val();
		materialdata = {};
		if(!trSData.engineeringTable.json){
	    	materialdata.projId=-1;	
	    }else{
	    	materialdata.projId=projId1;
	    }
		materialdata.materialName = materialName;
		console.info(materialdata);
		$('#materialListTablePop').cgetData();  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#materialListTablePop_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

/**
 * 初始化资料
 */
var seconddatainit1= function() {
	"use strict";
    if ($('#dataPopTableSecond').length !== 0) {
        accessoryTable = $('#dataPopTableSecond').on( 'draw.dt',function(){
	   	//默认选中第一行
	    $('#dataPopTableSecond').hideMask();
    	var popClose2 = function(){};
	    	//删除附件列表记录
	     	/*$(".del_btn").on("click",function(){
	    		$("body").cgetPopup({
    				title: '提示',
    				content: '您确定删除该文件信息吗？',
    			    accept: {
    					func: deleteDone,	//函数名
    					singleArgs: $(this)	//参数，用于向func传入多个参数，不设置该属性或设置为[]，则传入不带参数的函数
    				}
    	    	});
	   		});*/
        }).DataTable({
        	language: language_CN,
           	lengthMenu: [8],
           	dom: 'Brtip',
           	buttons: [],
          	ajax: {
               	url: 'accessoryCollect/queryAccessoryList',
               	type:'post',
               	data: function(d){
                  	$.each(accessoryData,function(i,k){
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
           	columns: [
                {"data":"caiId",className:"none never"},
                {"data":"alOperateCsrId",className:"none never"},
	  			{"data":"alName",responsivePriority:2},
	  			{"data":"alTypeId",responsivePriority:5},
	  			{"data":"alOperateTime",responsivePriority:3},
	  			{"data":"alOperateCsr" ,responsivePriority:4},	  			
	  			{"data":"alId",responsivePriority: 1}
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},{
				targets: 0,
				render: function (data, type, row, meta) {
					$('[type="checkbox"][name="accbox"][data-box="' + data + '"]').attr("checked","checked");
					return data;
				}
			},{
				targets: 6,
				render: function (data, type, row, meta) {
					if(type === "display"){
						var  tdcon='<a target="_blank" class="Load_Button btn btn-xs btn-primary" data-row="' + data + '" href="/accessoryCollect/openFile?id='+data+'"><i class="fa fa-eye"></i> 查看</a>';
							if($("#loginId").val() === row.alOperateCsrId){
								var  tdcon1='<a class="m-l-5 del_btn btn btn-warn btn-xs" data-id="'+data+'" data-path="'+row.alPath+'"><i class="fa fa-times"></i> 删除</a>';
							}else{
								var  tdcon1 = '';
							}
						return tdcon+tdcon1;
					}else{
						return data;
					}
				}
			}]
       });
   }
}
function deleteDone1(t){
    var data={};
	data.path=t.attr("data-path");	    
	data.alId=t.attr("data-id");
	console.info(data);
	$.ajax({
		url:'accessoryCollect/delAccessoryList',
		type:'post',
		data:data,
		success:function(data){
			if(data=="true"){
				$("[name='accbox']:checkbox").attr("checked",false);
					accessoryTable.ajax.reload();	  				
				}	
		    else{
		    	$("body").cgetPopup({
			    	title: "提示信息",
			    	content: "删除失败! <br>",
			    	accept: failClose,
			    	chide: true,
			    	icon: "fa-exclamation-circle"
			    });  		    	
		    }
		}
	});
}
function failClose(){
	$("#filePreviews tbody").html("");
}
/**
 * 审核历史
 */
var handleAuditHistory = function() {
	"use strict";
	histSearchData.projId = getProjectInfo().projId; 
	histSearchData.businessOrderId=$("#evId").val();
    if ($('#auditHistoryTable').length !== 0) {
    	$('#auditHistoryTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#auditHistoryTable').hideMask();
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'engineering/queryManageRecord',
                type:'post',
                data: function(d){
                   	$.each(histSearchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/design/json/delay-check-history.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
				{"data":"mrTime"},
				{"data":"mrResult"},
				{"data":"mrAopinion"},
				{"data":"mrCsr"}
			],
			columnDefs: [{
				"targets": 0,
				"render": function ( data, type, row, meta ) {
					if(type === "display"){
						return format(data,'all');
					}else{
						return data;
					}
				},
			},{
				"targets": 1,
				"render": function ( data, type, row, meta ) {
					if(data === "1"){
						return "通过";
					}else if(data === "0"){
						return "不通过";
					}else{
						return "";
					}
				},
			}]
        });
    }
};
