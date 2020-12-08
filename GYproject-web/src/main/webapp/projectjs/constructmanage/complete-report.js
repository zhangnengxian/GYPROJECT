var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var cptType = "";
var completeReportTable;
/**查询条件*/
var searchData={};
var projId=$('#projId').val();
searchData.projId=projId;
var handlecompleteReport = function() {
	"use strict";
    if ($('#completeReportTable').length !== 0) {
    	completeReportTable=$('#completeReportTable').on( 'init.dt',function(){
    		 var len = $('#completeReportTable').DataTable().ajax.json().data ? $('#completeReportTable').DataTable().ajax.json().data.length : $('#completeReportTable').DataTable().ajax.json().length;
	    		if(len<1){     //默认加载每一个公司下的第一种类型cpt
	    			 var json = {} ;
	            	 json.corpId = '';
	            	 json.menuId = '120502'; 
	            	 json.projectType = '';
	            	 queryCptType(json);   //加载报表类型
	    		}
    		$(this).bindDTSelected(trSelectedBack,true);
    		if(!trSData.completeReportTable.json){
    			showReport();
    		}
   			//隐藏遮罩
   			$('#completeReportTable').hideMask();
   			//增加监听
   	    	addMonitor();
   	    	//修改监听
   	    	modifyMonitor();
   	    	queryCheckRole();
   	    	//删除按钮监听
   			delMonitor();
   			setTimeout(function(){
   			    $("#completeReportTable").DataTable().columns.adjust();
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
            dom: 'Brtip',
            buttons: [
                { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' },
                { text: '删除', className: 'btn-sm btn-warn business-tool-btn hidden delBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'completeReport/queryPage',
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
                {"data":"crId",className:"none never"}, 
                {"data":"projNo"}, 
				{"data":"projName"},
                {"data":"scAmount"}
			],
			order: [[ 1, "desc" ]],
			columnDefs: [{
				"targets":0,
				"visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: showLength, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}]
        });
    }
};

/** 选中行时，查询详述*/
var trSelectedBack = function(v, i, index, t, json){
	$("input[type='radio']").removeAttr('checked');
	$("#crId").val(json.crId);
	$(".addBtn").addClass("hidden");
	t.cgetDetail('completeReportForm','completeReport/detailById','',queryBack);
	queryCptType(json);   //查询报表

    if ($('#loginPost').val().indexOf($('#dataAdmin').val())!=-1){//数据处理员
    	$(".updateBtn").removeClass("hidden")
    }
}

//根据公司ID、工程类型、菜单类型查找cpt类型--开始
var queryCptType = function(json){
	var crId = encodeURIComponent(cjkEncode(json.crId));    //解决中文乱码--联合验收Id
	var projId = encodeURIComponent(cjkEncode(json.projId));    //解决中文乱码--工程Id
	var projName = encodeURIComponent(cjkEncode(json.projName));    //解决中文乱码--工程名称
	var param = {} ;  //传递参数
	var menuId = getStepId();
	if(menuId == null || menuId == ''){   //判断菜单ID是否为空
		menuId = "120502";
	}
	param.projId = json.projId;  
	param.menuId = menuId ;
	param.signDate = json.realStartDate;
	$.ajax({
		type:'POST',
		url:'completeReport/queryCptType',
	    data:param,
	    success:function(cptUrl){
	    	if(cptUrl!='' && cptUrl !=null && cptUrl !=undefined){
	    		cptType =cptUrl;
	    	var	imgUrl = $(".imgUrl").val();
	    	var	src = cptPath+"/ReportServer?reportlet=constructmanage/"+cptType+"&crId=" + crId + "&projId=" + projId + "&projName=" + projName;
	    		src = src + "&imgUrl="+imgUrl;
	    		$('#mainFrm').hideMask();   //隐藏罩
	    	    $("#mainFrm").attr("src",src);
	    	}else{
	    		cptType = 'completeReport1.cpt'; //如果查不出cpt类型则默认贵州燃气集团的一个cpt
	    		showReport();
	    	}
	    }
	})
	  
}//根据公司ID、整改类型、菜单类型查找cpt类型--结束
var queryBack=function(data){
	// if($("#completeReportForm option:selected").val()=="1"){
	// 	//工艺--隐藏报警
	// 	$(".alarmProj").addClass("hidden");
	//    	$(".craftWork").removeClass("hidden");
	// 	showReport();
	// }else{
	// 	//报警-隐藏工艺
	//    	$(".craftWork").addClass("hidden");
	//    	$(".alarmProj").removeClass("hidden");
	var loginPost = $("#loginPost").val();
	var builderPost = $("#BUILDER").val();
//	console.info("登录人员职务："+loginPost);
//	console.info("现场代表职务"+builderPost);
	if(loginPost.indexOf(builderPost)>=0 || loginPost.indexOf(',999,')>=0){ //删除按钮对现场代表和管理员显示，对其他人隐藏
		$(".delBtn").removeClass("hidden");
	}
		showReport();
	// }
	if(data.suCse!='' && data.suCse!=null){
		$(".allText construction").attr("readonly","readonly");
	}else{
		$(".allText construction").removeAttr("readonly");
	}
	//施工单位都签完字，施工单位人员就不能修改
	if($("#unitType").val()==$("#cuUnitType").val() 
	   && (data.qualitativeCheckMember!='')
	   && (data.safetyOfficer!='')
	   && (data.construction!='')
	   && (data.cuPm!='') && (data.suJgj!='')){
		$(".updateBtn").addClass("hidden");
	}else{
		$(".updateBtn").removeClass("hidden");
	}
}
var completeReportCallBack=function(){
	var len = $('#completeReportTable').DataTable().ajax.json().data ? $('#completeReportTable').DataTable().ajax.json().data.length : $('#completeReportTable').DataTable().ajax.json().length;
	if(len<1){
		$('.addClear').val('');
		//清空签字
		$(".clear-sign").click();
	}else{
		if($("#completeReportForm option:selected").val()=="1"){
			//工艺--隐藏报警
			$(".alarmProj").addClass("hidden");
		   	$(".craftWork").removeClass("hidden");
			showReport();
		}else{
			//报警-隐藏工艺
		   	$(".craftWork").addClass("hidden");
		   	$(".alarmProj").removeClass("hidden");
			showReport2();
		}
	}
}
//增加监听
var addMonitor=function(){
	$('.addBtn').off('click').on('click',function(){
		$("#flag").val("1");
		$('#completeReportForm').formReset();
		$(".editbtn").removeClass("hidden");
		//默认值
		// $('#completeReportForm').toggleEditState(true);
		$('#signTab').tab("show");
        // $('#crAttach').innerHTML = "1、竣工文字资料：份 2、竣工图：份";
        // $("#suView").val("经初步验收，该工程1、符合我国现行法律、法规要求；" +
			// "2、符合我国现行工程建设标准；" +
			// "3、符合设计要求；" +
			// "4、符合施工合同要求。" +
			// "综上所述，该工程初步验收合格，可以组织正式验收。");
        //根据职务过滤可编辑项
        getSignStatusByPostId(getProjectInfo().post,"completeReportForm");
	});
}
//修改监听
var modifyMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){
		var len=$("#completeReportTable").find("tr.selected").length;
		if(len>0){
			$("#flag").val("2");
			$(".editbtn").removeClass("hidden");
			//切换页签
			$('#signTab').tab("show");
			$("#dataType").removeClass("field-editable");
			$("#dataType").addClass("field-not-editable");
            //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"completeReportForm");
            // 判断是否是数字
           if (checkNumber($("#scPlannedTotalDays").val())) {
        	   $("#scPlannedTotalDays").val((timestamp($("#scPlannedEndDate").val())-timestamp($("#scPlannedStartDate").val()))/(60*60*24)/1000);
           } else {
        	   $("#scPlannedTotalDays").val($("#scPlannedTotalDays").val());
           }
			// $('#completeReportForm').toggleEditState(true);


            if ($('#loginPost').val().indexOf($('#dataAdmin').val())!=-1){//数据处理员
                $(".allText").attr("readonly",false);
            }
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的工程交底信息信息!',
				ahide:true,
				atext:'确定'
			});
		}
	});
}
//初始化表格
var completeReport = function () {
	"use strict";
    return {
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        	
        	handlecompleteReport();
        	$("#listTab,#signTab").on("shown.bs.tab",function(){
    			if($(this).is($('#listTab'))){
    				$("#flag").val("0");
    				if(!$.fn.DataTable.isDataTable('#completeReportTable')){
    					handlecompleteReport();
        			}else{
        				$('#completeReportTable').cgetData(true);
        					showReport();
        			}
    			}else{
    				if($("#flag").val()=="1"){//增加
    					$('.clear-sign').click();
    					$('#completeReportForm').formReset();
    					$(".editbtn").removeClass("hidden");
    					
    					$("#dataType").removeClass("field-not-editable");
    					$("#dataType").addClass("field-editable");
    					// $('#completeReportForm').toggleEditState(true);
    					//默认选中报警
    					$("#completeReportForm option[value='2']").attr("selected","selected");
    					$("input[type='radio']").removeAttr('checked');
    					$("input[type='radio']").attr("disabled",false);
    					$("input[type='radio']").removeClass("disabled");
    					$(".craftWork").addClass("hidden");
    				   	$(".alarmProj").removeClass("hidden");
    					$('#crId').val("");
    					constructionWorkDetail();
    				}else if($("#flag").val()=="2"){
    					$(".editbtn").removeClass("hidden");
    					// $('#completeReportForm').toggleEditState(true);
    					$("input[type='radio']").attr("disabled",false);
    					$("input[type='radio']").removeClass("disabled");
    				}else{
    					$(".editbtn").addClass("hidden");
    					// $('#completeReportForm').toggleEditState(false);
    					$("input[type='radio']").attr("disabled",true);
    				}
    			}
        	});
        }
        )}
    };
}();
//报验区放弃功能
$(".giveupbtn").on("click",function(){
	$("#flag").val("1");
	$('#listTab').tab("show");
});
//验证是否是日期格式
function isDate(dateString){
    if(dateString.trim()==""){
    return false;
    }
    var r=dateString.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/); 
    if(r==null){
    return false;
    }
    var d=new Date(r[1],r[3]-1,r[4]);   
    var num = (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
    if(num==0){
    }
    return true;
 } 
//报验区保存功能
$(".saveBtn").on("click",function(){
	var data=$("#completeReportForm").serializeJsonString();
	 // 竣工日期必须为日期格式
   if (!isDate($("#realEndDate").val())) {
    	$('body').cgetPopup({
			title: '错误信息',
			content: '竣工日期格式不正确！',
			accept: popClose
		});
    	return false;
    }
	var completeReportForm = $("#completeReportForm");
	//开启表单验证
    if (completeReportForm.parsley().isValid()) {
    	$(".saveHiddenBox").loadMask("正在保存...", 1, 0.5);
    	$.ajax({
         type: 'POST',
         url: 'completeReport/saveCompleteReport',
         contentType: 'application/json;charset=UTF-8',
         data:data,
		 beforeSend: function () {
			// 禁用按钮防止重复提交
			$(".saveBtn").attr({ disabled: "disabled" });
		 },
         success: function (data) {
        	 $(".saveHiddenBox").hideMask(); 
         	var content = "数据保存成功！";
         	if(data === "false"){
         		content = "数据保存失败！";
            }else if(data === "already"){
                content = "此信息已被修改，请刷新页面！";
         	}else{
         		$("#crId").val(data);
         			showReport();
         	}
	       	 $("#completeReportForm").toggleEditState(false);
	       	 $("#completeReportForm input[type='radio']").attr("disabled",true);
	       	 $(".editbtn").addClass("hidden");
         	var myoptions = {
                 	title: "提示信息",
                 	content: content,
                 	accept: sureClose,
                 	chide: true,
                 	icon: "fa-check-circle"
             }
             $("body").cgetPopup(myoptions);
         },
			complete: function () {
				$(".saveBtn").removeAttr("disabled");
			},
         error: function (jqXHR, textStatus, errorThrown) {
             console.warn("竣工报告保存失败！");
         }
     	});
 		 //如果通过验证, 则移除验证UI
        completeReportForm.parsley().validate();
    } else {
        //如果未通过验证, 则加载验证UI
        completeReportForm.parsley().validate();
    };
});
//删除监听
var delMonitor=function(){
	$('.delBtn').off('click').on('click',function(){
		//加载弹屏
		if($('#completeReportTable').find('tr.selected').length >0){
			$('body').cgetPopup({
				title: '删除',
				content: '确定要删除竣工报告吗？',
				accept: delDone
			});
		}else{
			$('body').cgetPopup({
				title: '删除',
				content: '请选择要删除的竣工报告！',
				accept: popClose
			});
		}
	});
};

var sureClose=function(){
	var cwId=$("#crId").val();
	$.ajax({
		type:'post',
		url:'completeReport/saveSignNotice',
		contentType: "application/json;charset=UTF-8",
        data: cwId,
        success:function(data){
        	console.info(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("数据保存失败！");
        }
	})
}


//删除---确定后
var delDone = function(){
	$("#completeReportTable").cdeleteRow("crId","completeReport/deleteList",queryBack);
}