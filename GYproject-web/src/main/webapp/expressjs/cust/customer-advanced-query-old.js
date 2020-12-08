/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var mytable;
var searchData={};
var handleAdvancedSearch = function() {
	"use strict";
    if ($('#custAdvancedTable').length !== 0) {
       mytable= $('#custAdvancedTable').on( 'init.dt',function(){
            if($("#custAdvancedTable_filter").find(".asBtn").length<1){
                $("#custAdvancedTable_filter").append('<a id="custPop" class="asBtn btn btn-default btn-sm m-l-10"> <i class="fa fa-search-plus"></i></a>');
            }
            $(this).bindDTSelected(trSelected,true);
            //$("#panel_body_box").cgetPart($("#custAdvancedTable"),'',partback);
        }).DataTable({
        	language: {
                url: 'js/dt-chinese.json'
            },
            lengthMenu: [ 10,15,20 ],
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
      				{"data":"custName"},
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
	
	t.getDetail('custManagement/viewCustomer','custDetailform','',custDetailBack); 
};
var partback = function(){
	$("#custAdvancedTable tr.selected:eq(0)").getDetail('advancedQuery/custdetail','custDetailform','',custDetailBack); 
}

var custDetailBack = function(data){
	var custType = data.custType;
	var custName = data.custName;
	$("#custNamePers").val(custName);
	//工商户
	if(custType == "1"){
		$(".unit").removeClass("hidden");
		$(".personal").addClass("hidden");
	}else {
		$(".unit").addClass("hidden");
		$(".personal").removeClass("hidden");
	}
	$("#custDetailform").styleFit();
}


$(document).on('click',"#custPop",function(){
	
	var popoptions = {
			title: '客户列表检索',
			content: '#advancedQuery/custSearchPop',
			accept: searchDone
		}; 
		$("body").cgetPopup(popoptions);
});

/** 查询弹出屏，点击确定后 */
var searchDone = function(){	
	searchData = $("#searchForm_cust").serializeJson();
    $("#custManageTable").cgetData();
}

$(document).on('shown.bs.collapse',"#accordion",function(){
	var divpart=$("div[aria-expanded='true']");
	if(!divpart.hasClass("hasdata")){
		divpart.find(".panel-body").cgetPart(divpart,'id='+cusid);
	    divpart.addClass("hasdata");
	}
});

var advancedSearch = function () {
	"use strict";
    return {
        //main function
        init: function () {
            handleAdvancedSearch();
        }
    };
}();
