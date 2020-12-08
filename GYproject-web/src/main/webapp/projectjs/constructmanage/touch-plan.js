/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var searchData={},
	digData = '',
	touchPlanDetail = function(){
	 	projId = getProjectInfo().projId;
	 	data = "id="+projId;
	 	f = $("#touchPlanForm");
		$.ajax({
	         type: 'POST',
	         url: 'touchPlan/touchPlanDetail',
	         data: data,
	         dataType: 'json',
	         success: function (data) {
	             //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
	            var selects = f.find('select[disabled]');
	             selects.removeAttr("disabled");
	             //表单反序列化填充值
	             f.deserialize(data);
	             //有disabled属性的下拉菜单元素对象重新添加禁用属性
	             selects.attr("disabled","disabled");
	             detailCallBack(data);
	         },
	         error: function (jqXHR, textStatus, errorThrown) {
	             console.warn("cgetDetail() -> 详情查询失败");
	         }
	     });
		 
	},
detailCallBack = function(data){
	if(data.tpId !== "" && data.tpId!== null){
		//将按钮隐藏掉，表单不可改
		$("#touchPlanForm").toggleEditState(false).styleFit();
		$(".toolBtn").addClass("hidden");
		$(".tpId").val(data.tpId);
	}else{
		//未添加碰口方案，可维护
		$("#touchPlanForm").toggleEditState(true).styleFit();
		$(".toolBtn").removeClass("hidden");
		$(".tpId").val("");
	}
	$.ajax({
        type: 'POST',
        url: 'touchPlan/digRoadDetail',
        data: "id="+data.tpId,
        dataType: 'text',
        success: function (data) {
            //表单反序列化填充值
        	//array.reverse()
        	digData = data;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("cgetDetail() -> 详情查询失败");
        	console.info(errorThrown);
        }
    });
	showReport();
};

$('[href="#dig-road"]').on("shown.bs.tab",function(){
	var data = JSON.parse(digData);
	for(var i=0;i<data.length;i++){
		if(data[i].drRoads==="沥青路面"){
			$("#dig1").deserialize(data[i]);
		}else if(data[i].drRoads==="土路"){
			$("#dig2").deserialize(data[i]);
		}else if(data[i].drRoads === "砼路面"){
			$("#dig3").deserialize(data[i]);
		}else if(data[i].drRoads === "绿土路"){
			$("#dig4").deserialize(data[i]);
		}else if(data[i].drRoads === "花砖面"){
			$("#dig5").deserialize(data[i]);
		}
	}
	$("#dig-road").toggleEditState(false);
	showReport();
});