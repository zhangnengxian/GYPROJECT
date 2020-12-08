/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
/**查询条件*/
var searchData={},
handleDrawingAudit = function() {
	"use strict";
    if ($('#drawingListTable').length !== 0) {
    	$('#drawingListTable').on( 'init.dt',function(){
   	    	
        }).DataTable({
        	language: language_CN,
            lengthMenu: [10],
            dom: 'rt',
            buttons: [],
            //ajax: 'projectjs/constructmanage/json/drawing_audit.json',
            serverSide:true,
            ajax: {
                url: 'drawingAudit/queryReviewRecord',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            select: true,  //支持多选
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            columns: [
      			{"data":"drawingNo"},
				{"data":"drawingName"},
				{"data":"drawingQuestion"},
				{"data":"answerQuestion"}
			]/*,
			隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉）
			columnDefs: [{
				"targets": 0,
			    "render":function ( data, type, row, meta ){
			    	return meta.row + 1;
			    }
			}]*/
        });
        
    }
};


var drawingReviewDetail = function(){
	var url = 'constructionOrganization/constructionOrganizationDetail';
	var projId = getProjectInfo().projId;
	var data = "id="+projId;
	var f = $("#drawingAuditForm");
	 $.ajax({
         type: 'POST',
         url: url,
         data: data,
         dataType: 'json',
         success: function (data) {
        	 console.info(data);
             //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
            var selects = f.find('select[disabled]');
             selects.removeAttr("disabled");
             //表单反序列化填充值
             f.deserialize(data);
             //有disabled属性的下拉菜单元素对象重新添加禁用属性
             selects.attr("disabled","disabled");
            
             detailCallBack(data);
           //  $("#cuName").val(getProjectInfo().cuName);
         },
         error: function (jqXHR, textStatus, errorThrown) {
             console.warn("cgetDetail() -> 详情查询失败");
         }
     });
}

var detailCallBack = function(data){
    $('#cuName').val(getProjectInfo().cuName);
    $("#deptDivide").val(getProjectInfo().deptDivide);				//部门划分
    $("#stepId").val(getStepId());
    //$("#alPath").val($("#projNo").val()+"/"+$(".has-sub.active > a span").text());
    var type="";
    
    if($("#drawName").val()){
    	var fileName=$("#drawName").val().split(".");
    	var fileName1=fileName[1];
    	console.info("后缀----"+fileName1);
    }
    
    $("#alPath").val($("#projNo").val()+"/开工");
    $(".searchButton").attr("href","/accessoryCollect/openFile?id="+$("#accListId").val())+"&type="+type;
	$(".searchButton").removeClass("hidden");
	$(".Search_Button").addClass("hidden");
    if($("#auditState").val()==='1'){
        $(".duInfo").removeClass("hidden");
    }else{
        $(".duInfo").addClass("hidden");
    }
    if(data.drawName){
        $(".hasVal").removeClass("hidden");
        $(".noVal").addClass("hidden");
        $(".noVal+#filePreviews tr").remove();
    }else{
        $(".hasVal").addClass("hidden");
        $(".noVal").removeClass("hidden");
    }
    
    //客服中心可以隐藏监理
	if($("#deptDivide").val()==$("#customerServiceCenter").val()){
		$(".suUnit").addClass("hidden");
	}else{
		$(".suUnit").removeClass("hidden");
	}
    
    
	if(data.coId !== "" && data.coId!== null){
		//保存按钮隐藏 修改按钮显示
		$("#drawingAuditForm").toggleEditState(false).styleFit();
		$(".drawingReviewSaveBtn,.drEditBtn2,.drEditForm").addClass("hidden");
		$(".drawingReviewUpdataBtn").removeClass("hidden");
		$("#operateId").val(data.coId);
	}else{
        //根据职务过滤可编辑项
        getSignStatusByPostId(getProjectInfo().post,"drawingAuditForm");
		// $("#drawingAuditForm").toggleEditState(true).styleFit();
		$(".drawingReviewUpdataBtn").addClass("hidden");
		$(".drawingReviewSaveBtn,.drEditBtn2,.drEditForm").removeClass("hidden");
		// $("#drId1").val("");
	}
	showReport();
	queryCheckRole();
	//如果已通过且重报标记为空，则隐藏重报按钮
	if(($('input[type="radio"][name="reState"]:checked').val()=='' || $('input[type="radio"][name="reState"]:checked').val()==undefined) && ($('input[type="radio"][name="checkResult"]:checked').val()=='1'|| $('input[type="radio"][name="checkResult"]:checked').val()==undefined||$('input[type="radio"][name="checkResult"]:checked').val()=='')){
		$("#drawingAuditForm .reState").addClass("hidden");
	}else{
		$("#drawingAuditForm .reState").removeClass("hidden");
	}
}
var handleTable = function() {
	"use strict";
	if ($('#constructionOrganizationTable').length !== 0) {
		var data=new Array();
		var projInfo={};
		projInfo.projId = getProjectInfo().projId;
		projInfo.projNo = getProjectInfo().projNo;
		projInfo.projectType = getProjectInfo().projectType;
		data[0]=projInfo;
		console.info(data);
    	$('#constructionOrganizationTable').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBack,true);
    	}).DataTable({
        	language: language_CN,
            //lengthMenu: [18],
            //dom: 'Brtip',
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:false,
//          ajax: 'projectjs/constructmanage/json/design-alteration-record.json',
            //select: true,  //支持多选
            data : data,
            columns: [
                {"data":"projId"},
                {"data":"projNo"}
			]
        });
    }
}
function trSelectedBack(){}

var constructionOrganization = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	
        	$('[href="#drawing-logs"]').on("show.bs.tab", function(){
        	    $("#drawingAuditForm").styleFit();
        	    $('.datepicker-default').datepicker({
        	        todayHighlight: true
        	    });
        	});

        	$('#drawingmenuTable').hideMask();
        	$('#problemdrawTable').hideMask();
   			/*$('#drawingListTable').hideMask();*/

    	    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6,#signBtn_7").handleSignature();	
    	    drawingReviewDetail();

    		 handleTable();
        }
    };
    
}();