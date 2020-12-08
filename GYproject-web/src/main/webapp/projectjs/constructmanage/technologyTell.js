var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var listTable,searchData={};
var cptType = ''; 
/**
 * 加载列表区
 */
var handleTableList = function() {
	"use strict";
	searchData.projId = $('#projId').val();
	listTable= $('#listTable').on( 'init.dt',function(){

	/*
	 * 子公司施工员也可以发起
	 */
	if($("#loginPost").val().indexOf($("#fieldPrincipal_postType").val())>=0 || $("#loginPost").val().indexOf($("#cuPm_postType").val())>=0){
		$(".addBtn").removeClass("hidden");
	}else{
		$(".addBtn").addClass("hidden");
	}
	$(this).bindDTSelected(trSelectedBack,true);
	$('#listTable_filter input').attr('placeholder','图号');
	updateMonitor();
	delMonitor();
	//隐藏遮罩
	$("#listTable").hideMask();
	addMonitor();
	//查询监听
	searchMonitor();
	setTimeout(function(){
		    $("#listTable").DataTable().columns.adjust();
		    //$("#workOrderTable").DataTable().responsive.recalc();
		}, 0);
	//queryCheckRole();
	//showReport1();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [ 15 ],
        dom: 'Bfrtip',
        buttons: [
            { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole addBtn' },
            { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' },
            { text: '删除', className: 'btn-sm btn-warn business-tool-btn checkRole hidden delBtn' }
        ],
        serverSide: true, 
        ajax: {
            url: 'technologyTell/queryList',
            type:'post',
            data: function(d){
               	$.each(searchData,function(i,k){
               		d[i] = k;
               	});
               	
            },
            datasrc: 'data'
        },
       // ajax:'projectjs/inspection/json/trench_back_fill.json',
        responsive: {
        	details: {
        		renderer: function ( api, rowIdx, columns ){
        			return renderChild(api, rowIdx, columns);
        		}
        	}
        },
        select: true,
        columns: [
            {"data":"cwId",className:"none never"},       
			{"data":"projName"},
			{"data":"cwDate"},
			{"data":"drawingNo"}
		],
		order: [[ 2, "desc" ]],
		columnDefs: [{
			"targets":0,
			"visible":false
		},{
			"targets":1,
			//长字符串截取方法
			render: $.fn.dataTable.render.ellipsis({
				length: showLength, 	//截取多少字符（或汉字）
				end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
			})
		}] 
    });
};

//列表区行点击
var trSelectedBack = function(v, i, index, t, json){
	searchData.cwId=json.cwId;

    $(".delBtn").removeClass("hidden");
	// var loginPost = $('#loginPost').val();
    // if (loginPost.indexOf($('#cuManager').val()) != -1){//施工员显示删除按钮
    //$(".delBtn").removeClass("hidden");
    // }


	$("#cwId").val(json.cwId);
	t.cgetDetail("technologyTellForm",'technologyTell/findDetailById','',queryBack(v, i, index, t, json));


	if ($('#loginPost').val().indexOf($('#dataAdmin').val())!=-1){
		$(".updateBtn").removeClass("hidden");
	}


}

var queryBack=function(v, i, index, t, json){
	//客服中心可以隐藏监理
	if($("#deptDivide").val()==$("#customerServiceCenter").val()){
		$(".suUnit").addClass("hidden");
	}else{
		$(".suUnit").removeClass("hidden");
	}
	var hiddenValue=$('#hiddenConfig').val();
	if(hiddenValue!=''&& hiddenValue!=undefined){
        $('.userSign').removeClass('hidden');
	}else {
        $('.userSign').addClass('hidden');
	}
	//showReport1();
	 queryCptType(json);   //加载报表类型
	 //显示手机拍照
	 
};

//根据公司ID、工程类型、菜单类型查找cpt类型--开始
var queryCptType = function(json){
	var cwId = encodeURIComponent(cjkEncode(json.cwId));    //解决中文乱码--联合验收Id
	var projId = encodeURIComponent(cjkEncode(json.projId));    //解决中文乱码--工程Id
	var projName = encodeURIComponent(cjkEncode(json.projName));    //解决中文乱码--工程名称
	var param = {} ;  //传递参数
	var	menuId = "120102";
	param.corpId = json.corpId;  
	param.projectType = json.projectType;
	param.menuId = menuId ;
	$.ajax({
		type:'POST',
		url:'technologyTell/queryCptType',
	    data:param,
	    success:function(cptUrl){
	    	if(cptUrl!='' && cptUrl !=null && cptUrl !=undefined){
	    		cptType = cptUrl;
	    		var src=cptPath+'/ReportServer?reportlet=constructmanage/'+cptType+'&projName='+projName;
		    	src=src+'&cwId='+cwId+'&imgUrl='+$(".imgUrl").val();
		    	$("#mainFrm").attr("src",src);
	    	}else{
	    		cptType = 'technologyTell.cpt'; //如果查不出cpt类型则默认贵州燃气集团的一个cpt
	    		showReport1();
	    	}
	    }
	})
	  
}//根据公司ID、整改类型、菜单类型查找cpt类型--结束
/**
 * 列表区增加监听
 */
var addMonitor = function(){
	$(".addBtn").on("click",function(){
		$("#signTab").tab("show");
		$('.editBtn').removeClass("hidden");
		$('.addClear').val('');
		//客服中心可以隐藏监理
		if($("#deptDivide").val()==$("#customerServiceCenter").val()){
			$(".suUnit").addClass("hidden");
		}else{
			$(".suUnit").removeClass("hidden");
		}
		/*$("#technologyTellForm").toggleEditState(true);*/
		var postId=(getProjectInfo().post);
		getSignStatusByPostId(postId,"technologyTellForm");
		$('.clear-sign').click();
		if($("#sysDate").val()){
    		$("#cwDate").val($("#sysDate").val().substring(0,10));					//交底日期
    	}
	});
}

/**
 * 删除
 */
function delMonitor() {
	$('.delBtn').on('click',function () {
        var len=$("#listTable").find("tr.selected").length;
        if(len>0){
            var tipsHtml="<span style='background: linear-gradient(to right, red, blue);-webkit-background-clip: text;color: transparent;'>是否确认要删除此条记录!</span>";
            $("body").cgetPopup({
                title: "提示信息",
                content: tipsHtml,
                accept:confirmDelBtn,
                icon: "fa-warning",
            });
		}else {
            $("body").cgetPopup({
                title: '提示',
                content: '请选择要删除的报验单信息!',
                ahide:true,
                atext:'确定'
            });
		}
    })
}


function confirmDelBtn() {
    Base.subimt('technologyTell/delConstrctWorkById','POST',{cwId:searchData.cwId},function (data) {
        $("#listTable").cgetData(true);
    });
}

/**
 * 列表区修改监听
 */
function updateMonitor(){
	$(".updateBtn").on("click",function(){
		var len=$("#listTable").find("tr.selected").length;
		if(len>0){
			//客服中心可以隐藏监理
			if($("#deptDivide").val()==$("#customerServiceCenter").val()){
				$(".suUnit").addClass("hidden");
			}else{
				$(".suUnit").removeClass("hidden");
			}
			var postId=(getProjectInfo().post);
			getSignStatusByPostId(postId,"technologyTellForm");
			//$('#technologyTellForm').toggleEditState(true);
			$("#signTab").tab("show");
			$('.editBtn').removeClass("hidden");
			//施工或竣工中
        	/*if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#signForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#signForm").find(".sign-data-input").toggleSign(false);
        	}*/

            if ($('#loginPost').val().indexOf($('#dataAdmin').val())!=-1){//数据处理员
                $(".allText").attr("readonly",false);
            }

		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的报验单信息!',
				ahide:true,
				atext:'确定'
			});
		}
		
	});
}



/**
 * 搜索监听
 */
var searchMonitor = function(){
	//基础条件查询添加监听
	$('#listTable_filter input').on('change',function(){
		var drawingNo = $('#listTable_filter input').val();
		var projId=$('#projId').val();
		searchData = {};
		searchData={'drawingNo':drawingNo,'projId':projId};
		$('#listTable').cgetData(true,listCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#listTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}
/**
 * 列表重新加载
 */
var listCallBack = function(){
	var len = $('#listTable').DataTable().ajax.json().data ? $('#listTable').DataTable().ajax.json().data.length : $('#listTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.addClear').val('');
	 }
	//清空签字
	$(".clear-sign").click();
	showReport1();
}

//签字区放弃按钮事件
$(".giveUpBtn").on("click",function(){
	if($('#cwId').val()){
	//返回列表区
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTable').cgetData(true,listCallBack);
		$('#listTab').tab("show");
	}else{
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
	}
});
/**
 * 初始化页面
 */
var technologyTell = function(){
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		var projJson = getProjectInfo();
        		projJson.menuId = '120102';
                queryCptType(projJson);   //加载报表类型
        		handleTableList();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab, #signTab").off("shown.bs.tab").on("shown.bs.tab",function(){
        		
        		if($(this).is($('#listTab'))){
        			if(!$.fn.DataTable.isDataTable('#listTable')){
						//初始化列表
						handleRecordList();
						console.info(1111);
        			}else{
        				$('#listTable').cgetData(true,listCallBack);
        				$(".editBtn").addClass("hidden");
        				$('#technologyTellForm').toggleEditState(false);
        				//showReport1();
        				console.info(1111);
        			}
        			
            	}else if($(this).is($("#signTab"))){
        			if(trSData.listTable.json && $('#cwId').val()!=""){
        				showReport1();
    				}else{
    					showReport1();
    					$('.addClear').val('');
    					//清空签字
    					$('.clear-sign').click();
    					if($("#sysDate").val()){
    			    		$("#cwDate").val($("#sysDate").val().substring(0,10));					//交底日期
    			    	}
    				}
					
        			setTimeout(function(){
        			    $("#projectImagesList").getImagesList({
        			    	"projId": getProjectInfo().projId,
        			    	"step": getStepId(),
        			    	"projNo": getProjectInfo().projNo,
        			    	"busRecordId": $("#cwId").val() || '-1'
        			    });
        			},300);
        		}
        	});
        	})
        }
    };
}();