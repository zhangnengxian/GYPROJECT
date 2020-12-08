var handleProjectGlobalDetail = function() {
	$("#scaleTab").tab('show');
};
projectGlobalDetail = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleProjectGlobalDetail();
        }
    };
}();

/**工程信息监听*/
$("#scaleTab").on("shown.bs.tab",function(){
	$("#projectScale").cgetContent("/projTS/projectDetailBase",'',projectBaseDetailBack);
});


	var projectBaseDetailBack = function(){
		/*if(!trSData.projectGlobalViewListTable){*/
			var projId = $("#projId").val();
			var f1 = $("#detail_projectForm");
			var f2 = $("#detail_projectForm1");
			$.ajax({
	            type: 'POST',
	            url: '/projectView/projectDetail',
	            data: 'projId='+projId,
	            dataType: 'json',
	            success: function (data) {
	                //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
	                var selects1 = f1.find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
	                selects1.removeAttr("disabled");
	                f1.deserialize(fixNull(data));
	                f1.initFixedNumber();
	                //有disabled属性的下拉菜单元素对象重新添加禁用属性
	                selects1.attr("disabled","disabled");
	                f1.parents(".panel-inverse").find(".panel-heading").hideloading();
	                
	               //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
	                var selects2 = f2.find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
	                selects2.removeAttr("disabled");
	                f2.deserialize(fixNull(data));
	                f2.initFixedNumber();
	                //有disabled属性的下拉菜单元素对象重新添加禁用属性
	                selects2.attr("disabled","disabled");
	                f2.parents(".panel-inverse").find(".panel-heading").hideloading();
	                
					if(data.projLongitude && data.projLatitude){
						$(".view-location").removeClass("hidden");
					}
	                
	            }
	        });
	}
	
	
	
	var accessorySearchData = {};
	accessorySearchData.projId=$("#projId").val();
	var accessoryTableInit= function() {
		"use strict";
		$('#accessoryListTable').on( 'init.dt',function(){
			$('#accessoryListTable').hideMask();
			//资料查看文件
	    	$(".Search_Button").off("click").on("click",function(){
	    	     var data = {};
	    	     data.fpath = $(this).attr("data-row");
	    		 $.ajax({
	    			url:'/accessoryCollect/openFile',
	    			type:'post',
	    			data:data,
	    			success:function(data){
		    		    if(data == "nofile"){
		    		    	$("body").cgetPopup({
			    		    	title: "提示信息",
			    		    	content: "文件不存在! <br>",
			    		    	accept: popClose2,
			    		    	chide: true,
			    		    	icon: "fa-exclamation-circle"
			    		    });  		    	
		    		    }
	    		    }
	    		});
	    	});
	    }).DataTable({
	    	language: language_CN,
	        lengthMenu: [18],
	        dom: 'Brt',
	        buttons: [
	        ],
	        ajax: {
	            url: '/projectView/queryAccessoryList',
	            type:'post',
	            data: function(d){
	               	$.each(accessorySearchData,function(i,k){
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
	        select: true,  //支持多选
	        columns: [
	            {"data":"stepId"},
	  			{"data":"alName"},
	  			{"data":"alTypeId"},
	  			{"data":"alOperateTime"},
	  			{"data":"alOperateCsr"},
	  			{"data":"alId"}
	  			
			],
			columnDefs: [{
				targets: 5,
				render: function (data, type, row, meta) {
					if(type === "display"){
						var  tdcon='<a target="_blank" class="Search_Button" data-row="' + data + '" href="/accessoryCollect/openFile?id='+data+'">查看</a>';
						return tdcon;
					}else{
						return data;
					}
				}
			}]
	   });
	};	