
var touchPlanDetail = function(){
		var cmId = $("#cmId").val();
	 	//序列化
       	var menuDes = "变更记录";
	 	var data = "id="+cmId;
	 	var f = $("#designAlterationForm");
		$.ajax({
	         type: 'POST',
	         url: 'designAlteration/viewChangeManagement?menuDes='+menuDes,
	         data: data,
	         dataType: 'json',
	         success: function (data) {
	             //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
	            var selects = f.find('select[disabled]');
	             selects.removeAttr("disabled");
	             //表单反序列化填充值
	             f.deserialize(data);
	             $("#designChangeType").change();
	             if(data.drawName){
	         		$(".hasVal").removeClass("hidden");
	         		$(".noVal+#filePreviews tr").remove();
	         	}else{
	         		$(".hasVal").addClass("hidden");
	         	}
	             $(".searchButton").attr("href","/accessoryCollect/openChangeFile?id="+$("#cmId").val());
	             //有disabled属性的下拉菜单元素对象重新添加禁用属性
	             selects.attr("disabled","disabled");
	             var auditLevel = $(".auditLevel").val();
	             console.log("auditLevel:"+auditLevel);
	             if(auditLevel === "1"){
	            	 $("#designAlterationForm").toggleEditState(true).styleFit();
	            	 $(".otherSign").toggleEditState(false);
	            	 if(data.auditState==="2"){
	            		 $(".toolBtn").removeClass("hidden");
	            		 $(".returnBtn").addClass("hidden");
	            	 }else{
	            		 $(".toolBtn").addClass("hidden");
	            		 $(".returnBtn").removeClass("hidden");
	            	 }
	             }
	         	showReport();
	             //$("#touchPlanForm").toggleEditState(false).styleFit();
	         },
	         error: function (jqXHR, textStatus, errorThrown) {
	             console.warn("cgetDetail() -> 详情查询失败");
	         }
	     });
		 
},
tableCallBack = function(){
	$('#digForm').toggleEditState(false).styleFit();
	showReport();
	
},
tableCallBack1 = function(){
	//$('#digForm').toggleEditState(true).styleFit();
	showReport();
};
//放弃和返回功能
$(".giveupBtn").off("click").on("click",function(){
	$("#ajax-content").cgetContent("designAlterationAudit/main");
});


var designAlteration = function () {
	"use strict";
    return {
        init: function () {
        	touchPlanDetail();
        }
    };
}();