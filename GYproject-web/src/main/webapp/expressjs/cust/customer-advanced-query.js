/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var mytable;
var searchData={};
var dictionData;
var handleAdvancedSearch = function() {
	"use strict";
    if ($('#custAdvancedTable').length !== 0) {
       mytable= $('#custAdvancedTable').on( 'init.dt',function(){
            if($("#custAdvancedTable_filter").find(".asBtn").length<1){
                $("#custAdvancedTable_filter").append('<a id="custPop"  class="asBtn btn btn-default btn-sm m-l-10"> <i class="fa fa-search-plus"></i></a>');
            }
            $(this).bindDTSelected(trSelected,true);
            
            $("#custPop").on("click",function(){
   				var url = "#custManagement/custSearchPopPage";
   				var popoptions = {
   					title: '查询',
   					content: url,
   					accept: searchDone
   				};
   				//加载弹屏
   				$("body").cgetPopup(popoptions);
   			});
        }).DataTable({
        	language: {
                url: 'js/dt-chinese.json'
            },
            lengthMenu: [ 18 ],
            dom: 'Bfrtip',
            select: true,
            buttons: [
                { text: '导出', extend: 'excel', className: 'btn-primary btn-sm business-tool-btn' },
                { text: '打印', extend: 'print', className: 'btn-primary btn-sm business-tool-btn' }
            ],
            serverSide:true,
            ajax: {
                url: 'custAdvancedQuery/queryCustomer',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            select: true,
            responsive: true,
            /*返回数据为list，list里相应的属性值如下，对应列即可*/
            columns: [
                  	{"data":"custId",className:"none never"},
  					{"data":"custName" },
  					{"data":"custAddr"},
  					{"data":"custMobile"},
  					{"data":"custStatusDes"}
  	         ],
				/*隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉）*/
				columnDefs: [{
				      "targets": 0,
				      "visible":false
				}]
        });
        
    }
    $('#custAdvancedTable').hideMask();
};

//定义为全局变量
var cusid;

var trSelected = function(v, i, index, t, json){
	$(".infodetails .hasdata").removeClass("hasdata");
	var id = v[0];
	cusid = id;
	
	var divpart = $("#accordion").find("a.accordion-toggle-styled").not(".collapsed").parent().parent().next();
	if (divpart.length > 0) {
		divpart.find(".panel-body").cgetPart(divpart, 'id=' + id);
		divpart.addClass("hasdata");
	}else {
		$("#panel_body_box").cgetPart($("#panel_body_box"), 'id=' + id);
		$("#collapseOne").addClass("hasdata");
	}
};

//$(document).on("click","#custAdvancedTable tr",
//		function() {
//
//			if ($(this).hasClass("selected")) {
//				$(".infodetails .hasdata").removeClass("hasdata");
//				$("tr.selected").removeClass("selected");
//				$(this).addClass("selected");
//				//$(".infodetails .panel-collapse .panel-body ").loadMask("");
//				var rindx = $('#custAdvancedTable').find(".selected").index();
//				var id = $('#custAdvancedTable').DataTable().column(0).data()[rindx];
//				var divpart = $("div[aria-expanded='true']");
//				if (divpart.length > 0) {
//					divpart.find(".panel-body").cgetPart(divpart, 'id=' + id);
//					divpart.addClass("hasdata");
//				}else {
//					$("#panel_body_box").cgetPart($("#panel_body_box"), 'id=' + id);
//					$("#collapseOne").addClass("hasdata");
//				}
//			}else{				
//				$("#cus").cgetPart($("#collapseOne"), 'id=unid');
//		
//			}
//		});

var advancedSearch = function () {
	"use strict";
    return {
        //main function
        init: function () {
            handleAdvancedSearch();
        }
    };
}();

/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	
	searchData = $("#searchForm_cust").serializeJson();
    $("#custAdvancedTable").cgetData();  //列表重新加载
    
}


$(document).on('shown.bs.collapse',"#accordion",function(){
	var divpart=$("div[aria-expanded='true']");
	if(!divpart.hasClass("hasdata")){
		divpart.find(".panel-body").cgetPart(divpart,'id='+cusid);
	    divpart.addClass("hasdata");
	}
});