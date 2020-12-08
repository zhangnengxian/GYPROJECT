/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var mytable;
/**查询条件*/
var searchData={};
var handleCustManage = function() {
	"use strict";
    if ($('#custManageTable').length !== 0) {
    	mytable= $('#custManageTable').on( 'init.dt',function(){
    		//console.info(mytable.settings()[0].oFeatures.bServerSide);
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载弹屏按钮
    		$("#custManageTable_filter").append('<a id="custPop"  class="asBtn btn btn-default btn-sm m-l-10"> <i class="fa fa-search-plus"></i></a>');
   			//加载页面
   			$("#cust_manage_panel_box").cgetContent("custManagement/viewPage");
   			//隐藏遮罩
   			$('#custManageTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			custPop();
   			//修改监听方法
   			updateMonitor();
   			//删除监听方法
   			deleteMonitor();
   			
        }).DataTable({
        	 language: {
                 url: 'js/dt-chinese.json'
             },
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '修改', className: 'btn-sm  business-tool-btn updateBtn' },
                { text: '删除',  className: 'btn-sm business-tool-btn deleteBtn' }
               
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'custManagement/queryCustomer',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            responsive: true,//自适应
            select: true,  //支持多选
            columns: [
	  			{"data":"custId",className:"none never"}, //隐藏
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
};

//弹屏监听方法
var custPop = function(){
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
};
//修改监听方法
var updateMonitor = function(){
	
	$(".updateBtn").on("click",function(){
		//切换可编辑状态
		$("#custDetailform").toggleEditState(true);
		//维护按钮显示出来
		$(".editbtn").removeClass("hidden");
	});
};

//点击客户列表  删除 按钮时 
var deleteMonitor = function(){
	$(".deleteBtn").on("click",function(){
		//获取table选中的行数
		var len = $("#custManageTable").find("tr.selected").length;
		if(len>0){
			var popoptions = {
					title: '提示',
					content: '您确定删除该客户信息吗？',
					accept: deleteDone
		    };
			$("body").cgetPopup(popoptions);
		}else{
			var popoptions = {
					title: '提示',
					content: '请选择要删除的客户信息！'
		    };
			$("body").cgetPopup(popoptions);
		}
		
	});
};

/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	searchData = $("#searchForm_cust").serializeJson();
    $("#custManageTable").cgetData();  //列表重新加载
    
}

/** 选中行时，查询详述
	v：选中行（如果选中多行则取索引最小行）的所有单元格值的数组——————类型（选中行的所有数据）数组
*   i：选中行在datatable中的索引，数组对象，长度为1则表示选中单行————  为当前talbe的第多少条
*   index：选中行（如果选中多行则取索引最小行）在当前页的索引——————  当前页的索引
*   t：选中行（如果选中多行则取索引最小行）的jQuery对象——————tr对象
*   json：选中行（如果选中多行则取索引最小行）的所有关联数据对象（json）——————后台方法查询返回的所有数据
*  */
var trSelectedBack = function(v, i, index, t, json){
	//传false表示不可编辑
	$("#custDetailform").toggleEditState(false);
	//custManagementRight.jsp 维护按钮
	$(".editbtn").addClass("hidden");
	var custStatus = $('#custManageTable').DataTable().column(4).data()[index];
	if(custStatus =="正式"){
		//正式不可删除
		$(".deleteBtn").addClass("disabled");
	}else{
		$(".deleteBtn").removeClass("disabled");
	}
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('custDetailform', 'custManagement/viewCustomer', '', custDetailBack);
}

/**
 * 详述内容查询完成后
 */
var custDetailBack = function(data){
	var custType = data.custType;
	var custName = data.custName;
	//客户名称 input id
	$("#custNamePers").val(custName);
	//工商户
	if(custType == "1"){
		//custManagementRight.jsp 法人div
		$(".unit").removeClass("hidden");
		//客户名称
		$(".personal").addClass("hidden");
	}else {
		//居民户
		$(".unit").addClass("hidden");
		$(".personal").removeClass("hidden");
	}
}


//删除---确定后
var deleteDone = function(){
	//选中行列的值（第一列）  url  table对象
	$("#custManageTable").deleteRow(0,"custManagement/deleteCustomer",mytable);
}


/**右侧维护区 保存 成功后回调函数*/
var saveCustBack = function(){
	
	$("#custDetailform").toggleEditState(false);
	 $(".editbtn").addClass("hidden");
}

$("#custType").on("change",function(){
	var custType = $(this).val();
	if(custType == "1"){
		//工商
		$(".unit").removeClass("hidden");
		$(".personal").addClass("hidden");
	}else{
		//居民户
		$(".unit").addClass("hidden");//客户名称custName的div
		$(".personal").removeClass("hidden");
	}
	 $("#custDetailform").styleFit();
});

var custManage = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleCustManage();
        }
    };
}();