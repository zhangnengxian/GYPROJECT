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
	 	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
		$.ajax({
	         type: 'POST',
	         url: 'touchPlan/touchPlanDetail?menuDes='+menuDesc,
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
	             $('#constructionUnit').val(getProjectInfo().cuName);
	         },
	         error: function (jqXHR, textStatus, errorThrown) {
	             console.warn("cgetDetail() -> 详情查询失败");
	         }
	     });
		 
},
roadSearchData={"tpId":"#"};
handleDigRoad = function() {
	"use strict";
    if ($('#digTable').length !== 0) {
    	$('#digTable').on( 'init.dt',function(){
    		
    		$('#digTable').hideMask();
	        
        }).DataTable({
        	language: language_CN,
            lengthMenu: [15],
            dom: 'Brt',
            buttons: [],
            //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
            	url: 'touchPlan/digRoadDetail',
            	type:'post',
            	data: function(d){
                  	$.each(roadSearchData,function(i,k){
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
	  			{"data":"drRoads", responsivePriority: 1},
	  			{"data":"drLength",className:"text-right", responsivePriority: 3},
	  			{"data":"drWidth",className:"text-right", responsivePriority: 4},
	  			{"data":"drDepth",className:"text-right", responsivePriority: 5},
	  			{"data":"drId",className:"text-right", responsivePriority: 2}
			],
			columnDefs: [{
				targets: 0,
				render: function ( data, type, row, meta ) {
					if(type === "display"){
						if(data === null){
							data = "";
						}
						var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm field-not-editable' name='" + row.drId + "_drRoads' id='" + row.drId + "_drRoads' value="+data+"></div>"
						return tdcon;
					}else{
						return data;
					}}
			    },{
				targets: 1,
				render: function ( data, type, row, meta ) {
					if(type === "display"){
						if(data === null){
							data = "";
						}else{
							data = data.toFixed(2);
						}
						var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.drId + "_drLength' id='" + row.drId + "_drLength'  data-parsley-type='number' value="+data+"></div>"
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
							}else{
								data = data.toFixed(2);
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.drId + "_drWidth' id='" + row.drId + "_drWidth'  data-parsley-type='number' value="+data+"></div>"
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
							}else{
								data = data.toFixed(2);
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.drId + "_drDepth' id='" + row.drId + "_drDepth'  data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}	}
			    },{
			    	targets: 4,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data!==null){
								var  tdcon = "<div class='tdInnerInput'><input class='checkBox field-not-editable'  name='" + row.drId + "_drId' id='" + row.drId + "_drId' type='checkbox' checked value="+data+"></div>";
								return tdcon;
							}
						}else{
							return data;
						}}	
			    }],
			ordering:false
       });
   }
},
detailCallBack = function(data){
	
	if(data.tpId !== "" && data.tpId!== null){
		roadSearchData.tpId = data.tpId;
		$('#digTable').cgetData(false,tableCallBack);
		//将按钮隐藏掉，表单不可改
		$("#touchPlanForm").toggleEditState(false).styleFit();
		$(".saveBtn").addClass("hidden");
		$(".updataBtn").removeClass("hidden");
		$("#tpId").val(data.tpId);
		if($("#drawName").val()){
			 $(".hasVal").removeClass("hidden");
			 $(".noVal").addClass("hidden");
			 $(".noVal+#filePreviews tr").remove();
		 }else{
			 $(".noVal").removeClass("hidden");
			 
			 $(".hasVal").addClass("hidden");
		 }
		
	}else{
		roadSearchData.tpId = '';
		$('#digTable').cgetData(false,tableCallBack1);
		//未添加碰口方案，可维护
		$("#touchPlanForm").toggleEditState(true).styleFit();
		$(".saveBtn").removeClass("hidden");
		$(".updataBtn").addClass("hidden");
		$(".tpId").val("");
		$(".hasVal").removeClass("hidden");
		if($("#drawName").val()){
			 $(".hasVal").removeClass("hidden");
			 $(".noVal").addClass("hidden");
			 $(".noVal+#filePreviews tr").remove();
		 }else{
			 $(".noVal").removeClass("hidden");
			 
			 $(".hasVal").addClass("hidden");
		 }
	}
	queryCheckRole();
	
},
tableCallBack = function(){
	$('#digForm').toggleEditState(false).styleFit();
	showReport();
},
tableCallBack1 = function(){
	//$('#digForm').toggleEditState(true).styleFit();
	showReport();
};

var connectPlan = function () {
	"use strict";
    return {
        init: function () {
        	touchPlanDetail();
        	handleDigRoad();
        }
    };
}();