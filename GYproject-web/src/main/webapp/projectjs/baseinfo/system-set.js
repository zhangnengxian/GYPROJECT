
/**
 * 初始化列表
 */
var menuTree = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	menuTreeInit();
        }
      
    };
}();

var menuTreeInit = function() {
	  $('#menuTreeRoleManage').jstree({
	        "core": {
	            "themes": { "responsive": false },
	            "check_callback": true,
	            'data': {
	                'url': 'menu/getMenuTreeForSet',
	                "dataType": "json"
	               
	            }
	        },
	       
	        "types": {
	            "default": { "icon": "fa fa-folder disabled text-warning fa-lg" },
	            "file": { "icon": "fa fa-file text-warning fa-lg" }
	        },
	        "plugins": ["types"]
	    }); 
	  $('#menuTreeRoleManage').on("ready.jstree",function(){
		  $('#menuTreeRoleManage').on("select_node.jstree", function (e, data) {
			  $("#menuId,#stepId,#timeSpan,#sysId").val("");
			  $("input[name='checkVal']").each(function(){    
				  $(this).attr("checked",false);    
				  });    
			  $("#nodName").val(data.node.text);
			  $("#parentName").val($('#menuTreeRoleManage').jstree(true).get_node(data.node.parent).text);
			  
			  if(data.node.children.length!=0){
				  $(".timeSpan").addClass("hidden");
				  $(".associateType").addClass("hidden");
				  $(".editbtn").addClass("hidden");
				  
			  }else{
				  var manuId;
				  console.info(data.node);
				  if(data.node.id.split("@@")[0]){
					  manuId=data.node.id.split("@@")[0];
				  }else{
					  manuId=data.node.id;
				  }
				  console.info(data.node.id.split("@@")[0]);
				  console.info(manuId);
				  $("#menuId").val(manuId);
				  $.ajax({
			            type: 'POST',
			            url: "systemSet/findDataByMenuId",
			            dataType: 'json',
			            data:'menuId='+manuId,
			            success: function (data) {
			            	$("#systemSetForm").deserialize(data);
			            	if(data.associateType){
			            	  var dataArray=data.associateType.split(",");
			            	  for(var i=0;i<dataArray.length;i++){
			            		  $("input[name='checkVal'][value='"+dataArray[i]+"']").attr("checked","checked");
			            	  }
			            	}
			            	$("#systemSetForm option[value='"+data.corpId+"']").attr("selected","selected");
			            }
				  });
				  $(".editbtn").removeClass("hidden");
				  $('#systemSetForm').toggleEditState(true);
				  $(".associateType").removeClass("hidden");
				  if(data.node.id.split("@@")[1]){
					  $(".timeSpan").removeClass("hidden");
					  $("#stepId").val(data.node.id.split("@@")[1]);
				  }else{
					  $(".timeSpan").addClass("hidden");
				  }
				  
				  
				  
			  }
			 
			
		  });
	  });
      
};

$(".sys-saveBtn").off("click").on("click",function(){
	 var arr=[];
	 var checkVal=$("input[name='checkVal']:checked").each(function(){    
		 arr.push($(this).val());    
	  });    
	 $('#associateType').val(arr.join());
	 $('#systemSetForm').gformSave ("", "", "", false);
});









