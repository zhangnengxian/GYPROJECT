
var searchData={},
	digData = '',
	completionTransferDetail = function(){
$("#completionTransfeTable").cgetDetail('completionTransferForm','','',back);
    
    var back=function(data){
    	console.info("1211");
    	console.info(data);
    	 var auditLevel = $(".auditLevel").val();
    	    console.info(auditLevel);
    	    //console.log("auditLevel:"+auditLevel);
    	    if(auditLevel === "1"){
    	   	 $("#completionTransferForm").toggleEditState(false).styleFit();
    	   	 if(data.govOpinion!==""&& data.govOpinion!==null){
    	   		 $(".toolBtn").addClass("hidden");
    	   		 $(".returnBtn").removeClass("hidden");
    	   	 }else{
    	   		 $(".toolBtn").removeClass("hidden");
    	   		 $(".returnBtn").addClass("hidden");
    	   		 $(".show").toggleEditState(true);
    	   	 }
    	   	 $(".show2").addClass("hidden");
    	   	 $(".show3").addClass("hidden");
    	   	 $(".show4").addClass("hidden");
    	    }else if(auditLevel === "2"){
    	   	 $("#completionTransferForm").toggleEditState(false).styleFit();
    	   	 if(data.acceptanceView!=="" && data.acceptanceView!==null){
    	   		 $(".toolBtn").addClass("hidden");
    	   		 $(".returnBtn").removeClass("hidden");
    	   	 }else{
    	   		 $(".toolBtn").removeClass("hidden");
    	   		 $(".returnBtn").addClass("hidden");
    	   		 $(".show2").toggleEditState(true);
    	   	 }
    	   	 $(".show3").addClass("hidden");
    	   	 $(".show4").addClass("hidden");
    	    }else if(auditLevel === "3"){
    	   	 $("#completionTransferForm").toggleEditState(false).styleFit();
    	   	 if(data.cuSpdView!=="" && data.cuSpdView!==null){
    	   		 $(".toolBtn").addClass("hidden");
    	   		 $(".returnBtn").removeClass("hidden");
    	   	 }else{
    	   		 $(".toolBtn").removeClass("hidden");
    	   		 $(".returnBtn").addClass("hidden");
    	   		 $(".show3").toggleEditState(true);
    	   	 }
    	   	 $(".show4").addClass("hidden");
    	    }else if(auditLevel === "4"){
    	   	 $("#completionTransferForm").toggleEditState(false).styleFit();
    	   	 if(data.ongcView!=="" && data.ongcView!==null){
    	   		 $(".toolBtn").addClass("hidden");
    	   		 $(".returnBtn").removeClass("hidden");
    	   	 }else{
    	   		 $(".toolBtn").removeClass("hidden");
    	   		 $(".returnBtn").addClass("hidden");
    	   		 $(".show4").toggleEditState(true);
    	   	 }
    	 }	 
    };
		 
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
		//$(".toolBtn").addClass("hidden");
		$("#tpId").val(data.tpId);
	}else{
		roadSearchData.tpId = '';
		$('#digTable').cgetData(false,tableCallBack1);
		//未添加碰口方案，可维护
		$("#touchPlanForm").toggleEditState(true).styleFit();
		$(".toolBtn").removeClass("hidden");
		$(".tpId").val("");
	}
	
	
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
	$("#ajax-content").cgetContent("touchPlanAudit/main");
});


var completionTransfer = function () {
	"use strict";
    return {
        init: function () {
        	completionTransferDetail();
        }
    };
}();