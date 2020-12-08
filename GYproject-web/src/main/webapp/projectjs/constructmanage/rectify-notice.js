var searchData={},
	recordData={},
	rectifyNoticeTable,
	savestus=0;
//整改列表
var cptType;  //定义类型
var handleRectifyNotice = function() {
	"use strict";
	var projId = $("#projId").val();
	if(projId=='' || projId==undefined ){
		projId="-1";
	}
	searchData.projId = projId;
    if ($('#rectifyNoticeTable').length !== 0) {
    	rectifyNoticeTable=$('#rectifyNoticeTable').on( 'init.dt',function(){
    		//搜索
    		//$('#rectifyNoticeTable_filter input').attr('placeholder','整改编号'); 
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//隐藏遮罩
   			$('#rectifyNoticeTable').hideMask();
   			//弹屏查询
   	    	searchMonitor();
   			//增加监听
   			addMonitor();
   			//修改监听
   	    	modifyMonitor();
   	    	//加载打印屏
  		    showReport1();
   	    	queryCheckRole();
   	    	//推送监听
   	    	pushRectifyNotice();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
           // dom: 'Brtip',
            dom: 'Brt',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '增加', className: 'btn-sm btn-query checkRole addBtn' },
                { text: '修改', className: 'btn-sm btn-query checkRole updateBtn business-tool-btn' },
                { text: '推送', className: 'btn-sm btn-query checkRole pushBtn' }
            ],
          //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'rectifyNotice/queryRectifyNotices',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/constructmanage/json/rectify_notice.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
      			{"data":"rnId",className:"none never"},
      			{"data":"rnNo"},
      			{"data":"limitTime"},
				{"data":"rnDate"},
				{"data":"rnStatusDes"}
				],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},{
				"targets":4,
				 "orderable":false
			}],
        });
    }
}
//增加监听
var addMonitor = function(){
	$(".addBtn").off("click").on("click",function(){
		$(".addClear").val('');
		$('#rectificationInfo').tab('show');
        $("#reviewDiv").addClass("hidden");
        //根据职务过滤可编辑项
        //getSignStatusByPostId(getProjectInfo().post,"rectifiyNoticeForm");
		//$('#rectifiyNoticeForm').toggleEditState(true);
		getSignStatusByPostId(getProjectInfo().post,"rectifiyNoticeForm");
		$(".editbtn").removeClass("hidden");
		$("#signBtn_1,#signBtn_2,#signBtn_3,#signBtn_4,#signBtn_5,#signBtn_6,#signBtn_7,#signBtn_8,#signBtn_9,#signBtn_10").resetSign();
		
	});
}
//修改监听
var modifyMonitor = function(){
	$(".updateBtn").off("click").on("click",function(){
		var len=$("#rectifyNoticeTable").find("tr.selected").length;
		if(len>0){
            //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"rectifiyNoticeForm");
			// $('#rectifiyNoticeForm').toggleEditState(true);
			$(".editbtn").removeClass("hidden");
			$('#rectificationInfo').tab("show");
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的整改条目!',
				ahide:true,
				atext:'确定'
			});
		}
	});
};
/**
 * 查询弹屏监听方法
 */
var searchMonitor=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#rectifyNotice/rectifyNoticeSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	$('#rectifyNoticeTable_filter input').on('change',function(){
		var cmNo = $('#rectifyNoticeTable_filter input').val();
		searchData = {};
		searchData.rnId = rnId;
		searchData.projId = $("#projId").val();
		$('#rectifyNoticeTable').cgetData(true);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#rectifyNoticeTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}
/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_rectifyNotice").serializeJson();
	searchData.projId = $("#projId").val();
	//列表重新加载
    $("#rectifyNoticeTable").cgetData(true,rectifyNoticeCallBack);  
}
/**
 * 推送方法
 */
var pushRectifyNotice = function(){
	$('.pushBtn').off().on('click',function(){
		console.info("click");
		var len=$("#rectifyNoticeTable").find('tr.selected').length;
		if(len>0){
           $("body").cgetPopup({
               	title: "提示信息",
               	content: "是否将选中的整改通知推送至施工单位？",
               	accept: surePush,
               	newpop: 'new',
               	icon: "fa-check-circle"
           });
		}else{
			alertInfo('请选择要推送的整改通知！');
		}
	});
}
var surePush=function(){
	var rnId=trSData.rectifyNoticeTable.json.rnId;
	$.ajax({
		type:'post',
		url:'rectifyNotice/pushRectifyNotice',
		contentType: "application/json;charset=UTF-8",
        data: rnId,
        success:function(data){
        	var content = "推送成功！";
        	if(data=="false"){
        		content = "推送失败！";
        	}else{
        		$('#rectifyNoticeTable').cgetData(true);
        	}
        	var myoptions = {
                	title: "提示信息",
                	content: content,
                	accept: popClose,
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
/**
 * 重新加载整改通知列表
 */
var rectifyNoticeCallBack = function(){
	var len = $('#rectifyNoticeTable').DataTable().ajax.json().data ? $('#rectifyNoticeTable').DataTable().ajax.json().data.length : $('#rectifyNoticeTable').DataTable().ajax.json().length;
	if(len<1){
		//清空信息页签
		$(".addClear").val('');
	}
	//清空签字
	$(".clear-sign").click();
	showReport1();
}
//保存整改信息
$('.saveCustomer').off().on("click",function(){
	$("#rectifiyNoticeForm").cformSave('rectifyNoticeTable',saveBack,false,false);
   	
});
var saveBack = function(data){
 	var content = "数据保存成功！";
 	if(data === "false"){
 		content = "数据保存失败！";
 	}else if(data === "already"){
   		content = "此信息已被修改，请刷新页面！";
   	}else{
   		var rnId = data;
   		$('#rnId').val(rnId);
   		projNo = getProjectInfo().projNo,
   		projId = getProjectInfo().projId;
   		//图片上传
   		if(_inApk && $(".attach-images-list").find(".new-image").length){
   			preImagesUpload($(".attach-images-list .upload-image-btn"), projId, projNo, getStepId(), data);
   		};
   		$('#rectifiyNoticeForm').toggleEditState(false);
   		$(".editbtn").addClass("hidden");
        $('#rectifyNoticeTable').cgetData(true);
   		showReport1();
 	}
 	var myoptions = {
         	title: "提示信息",
         	content: content,
         	accept: popClose,
         	chide: true,
         	icon: "fa-check-circle"
     }
     $("body").cgetPopup(myoptions);
};
//点击放弃
$('.giveUpSave').off().on('click',function(){
	$("#rectifiyNoticeForm").toggleEditState(false);
	//$('ul.nav>li').removeClass("active");
	$('#rectificationList').click();
});

/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
    loadPartContent($("#projId").val(),getStepId(),json.rectifyNoticeType,function () {
        //将查询详述显示到相应的输入框内
        t.cgetDetail('rectifiyNoticeForm','rectifyNotice/viewRectifyNotice','',queryBack);
   });

	if(json.rnStatus=='1'){//待推送
		$(".updateBtn").removeClass("hidden");
		$(".pushBtn").removeClass("hidden");
	}else if(json.rnStatus=='2'){//待回复
		$(".updateBtn").addClass("hidden");
		$(".pushBtn").addClass("hidden");
	}else if(json.rnStatus=='3'){//已回复
		$(".updateBtn").removeClass("hidden");
		$(".pushBtn").addClass("hidden");
	}
	if($("#IsSignature").val().indexOf('true')>=0){
		$(".dataHandle").addClass("hidden");
		$(".productionStatisticians").addClass("hidden");
	}else{
		$(".dataHandle").removeClass("hidden");
		$(".productionStatisticians").removeClass("hidden");
	}

	queryCptType(json);  //查询cpt类型进行显示
	//传false表示不可编辑
	$("#rectifiyNoticeForm").toggleEditState(false);
	$(".editbtn").addClass("hidden");
}

var queryBack=function(json){
    $("#reviewDiv").addClass("hidden");
    console.info(2018,json);
	var rnStatus = json.rnStatus;
    var review = json.review;
	if(rnStatus==4||""!=review){
        $("#reviewDiv").removeClass("hidden");
	}
	//整改类型
	if($("#rectifyNoticeType").val()=='2'){
		// $(".resident").addClass("hidden");
		 //$("#limitTime").val('');
	 }else{
		 $(".resident").removeClass("hidden");
	 }
	if($("#rnStatus").val()=='3'){
		$(".rectifyNoticeBack").removeClass("hidden");
	}else{
		$(".rectifyNoticeBack").addClass("hidden");
	}
	$("#rectifiyNoticeForm").styleFit();
	
}
//根据公司ID、整改类型、菜单类型查找cpt类型--开始
var queryCptType = function(json){   
	var rnId = encodeURIComponent(cjkEncode(json.rnId));    //解决中文乱码
	var projName = $('#projName').val();  //从页面取得工程名称
	var corpName = $('#corpName').val();  //从页面取得公司名称
	projName = encodeURIComponent(cjkEncode(projName));    //解决中文乱码
	corpName = encodeURIComponent(cjkEncode(corpName));    //解决中文乱码
    var param = {} ;
    param.corpId = json.corpId;  //传入分公司ID
    param.menuId = getStepId();  //传入菜单Id;
    param.rnType = json.rectifyNoticeType;  //整改类型
    $.ajax({
    	type:'POST',
    	url:'rectifyNotice/queryCptType',
    	data:param,
    	success:function(cptUrl){
    		if(cptUrl != null && cptUrl !=''){  //如果cpt不为空则显示当前cpt否则显示以前配置的cpt
    			cptType = cptUrl;
    			var src = cptPath +"/ReportServer?reportlet=constructmanage/"+cptType+"&rnId="+rnId+"&projName="+projName+"&corpName="+corpName+"&imgUrl="+$('.imgUrl').val();
    			$("#mainFrm").attr("src",src);
    		}else{
    			cptType = 'rectifyNotice.cpt';
    			showReport1();
    		}
    	}
    });
	
}
//根据公司ID、整改类型、菜单类型查找cpt类型--结束
//整改列表页签
/*$('#rectificationList').on('shown.bs.tab',function(){
	
	$('#rectifyNoticeTable').cgetData(true);
});*/
$('#rectificationInfo').on('shown.bs.tab',function(){
	$('#designAlterationTable tr.selected').removeClass("selected");
});

//页面加载
var rectifyNotice =  function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleRectifyNotice();
        	});
        	$("#rectificationList,#rectificationInfo").on("shown.bs.tab",function(){
        		if($(this).is($("#rectificationList"))){
        			$(".editbtn").addClass("hidden");
    				if(!$.fn.DataTable.isDataTable('#rectifyNoticeTable')){
    					handleRectifyNotice();
        			}else{
        				$('#rectifyNoticeTable').cgetData(true);
        				showReport1();
        			}
        		}else if($(this).is($("#rectificationInfo"))){
        			if(trSData.rectifyNoticeTable.json && $('#rnId').val()!=""){
        			showReport1();
    				}else{
    					$(".addClear").val('');
    					//清空签字
    					$(".clear-sign").click();
    					showReport1();
    				}
					setTimeout(function(){
    				    $("#projectImagesList").getImagesList({
    				    	"projId": getProjectInfo().projId,
    				    	"step": getStepId(),
    				    	"projNo": getProjectInfo().projNo,
    				    	"busRecordId": $("#rnId").val() || '-1'
    				    });
    				},300);
        		}
        	});
        	 var json = {} ;
        	 json.corpId = '';
        	 json.menuId = '120209'; 
        	 queryCptType(json);   //默认加载不同公司下的第一个cpt 
        	
        }
    };
}();