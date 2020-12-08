/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var mytable;
var searchData={};
var handleAddManage = function() {
	"use strict";
    
    if ($('#addrmanagetable').length !== 0) {
    	//初始化表格
    	mytable= $('#addrmanagetable').on( 'init.dt',function(){
    		//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//加载弹屏按钮
    		$("#addrmanagetable_filter").append('<a id="asPop" class="asBtn btn btn-default btn-sm m-l-10"> <i class="fa fa-search-plus"></i></a>');
    		//给类属性是editaddr的对象的data-c属性赋值
    		$(".editaddr").attr("data-c","#addrManagement/viewPage");
    		//地址详述区加载
   			$("#add_manage_panel_box").cgetPart($(".editaddr"));
   			//隐藏遮罩
   			$('#addrmanagetable').hideMask();
   			//查询弹屏绑定事件
   			$("#asPop").on("click",function(){
   				var url = "#addrManagement/addrSearchPopPage";
   				var popoptions = {
   					title: '查询',
   					content: url,
   					accept: myaccept
   				};
   				$("body").cgetPopup(popoptions);
   			});
   			
        }).DataTable({
        	 language: {
                 url: 'js/dt-chinese.json'
             },
            lengthMenu: [ 18 ],
            dom: 'Bfrtip',
            buttons: [
                { text: '修改', className: 'btn-sm editaddr business-tool-btn' },
                { text: '删除', className: 'btn-sm deladdr business-tool-btn' }
               
            ],
            serverSide:true,
            ajax: {
                url: 'addrManagement/queryAddress',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
            },
            responsive: true,//自适应
            select: true,//支持多选 
            columns: [
	  			{"data":"addrId",className:"none never"},
	  			{"data":"userNo"},
	  			{"data":"addrDes"},
				{"data":"openGasDate"},
				{"data":"addrStatusDes"}
			],
			order: [[1,"desc"]],
			columnDefs: [{ 
			      "targets": 0,
			      "visible":false
			}]
        });
        
    }
};

/** 查询弹出屏，点击确定后 */
var myaccept=function(){
	searchData=$("#searchForm_addr").serializeJson();
	$("#addrmanagetable").cgetData();
}


/** 地址详述回调函数 */
var trSelectedBack = function(v, i, index, t, json){
	//传false表示不可编辑
	$("#addmanageform").toggleEditState(false);
	//隐藏维护按钮  保存和放弃 点修改的时候加载
	$(".scbtn").addClass("hidden");
	var addrStatus = v[4];
	if(addrStatus =="在用"){
	//在用状态 不可删除
		$(".deladdr").addClass("disabled");
	}else{
		$(".deladdr").removeClass("disabled");
	}
	//点击单行查看详情
	t.cgetDetail('addmanageform','addrManagement/viewAddress','',detailBack); 
}
/**详述查询完回调函数*/
var detailBack = function(data){
	var arr=data.unitNum.split("@");
	//单元 楼层 房间号
	if(arr.length==6){
	$("#unit").val(arr[0]);
	$("#unitDes").val(arr[0]+arr[1]);
	$("#floor").val(arr[2]);
	$("#floorDes").val(arr[2]+arr[3]);
	$("#room").val(arr[4]);
	$("#roomDes").val(arr[4]+arr[5]);
	}
	$(".descrip").addClass("hidden");
}


/**点击客户列表  修改 按钮时*/
$(document).on("click",".editaddr",function(){
	var len=$("#addrmanagetable").find("tr.selected").length;
	if(len>0){
		//切换到可编辑状态
		$("#addmanageform").toggleEditState(true);
		//维护按钮显示出来
		$(".scbtn").removeClass("hidden");
		//显示地址信息
		$(".descrip").removeClass("hidden");
	}else{
		var popoptions = {
				title: '提示',
				content: '请选择要修改的地址信息!',
				ahide:true,
				atext:'确定'
	    };
		$("body").cgetPopup(popoptions);
	}
	
});

/**点击客户列表  删除 按钮时*/
$(document).on("click",".deladdr",function(){
	var len=$("#addrmanagetable").find("tr.selected").length;
	if(len>0){
		var popoptions = {
				title: '提示',
				content: '您确定删除该地址信息吗？',
				accept: deleteDone
	    };
		$("body").cgetPopup(popoptions);
	}else{
		var popoptions = {
				title: '提示',
				content: '请选择要删除的地址信息!',
				ahide:true,
				atext:'确定'
	    };
		$("body").cgetPopup(popoptions);
	}
});

/**点击删除确定后*/
var deleteDone=function(){
	$("#addrmanagetable").deleteRow(0,"addrManagement/deleteAddress",mytable);
}

/**右侧维护区 放弃 按钮点击后*/
$(document).on("click",".cancelBtn",function(){
	//地址信息隐藏
	$(".descrip").addClass("hidden");
	//恢复到不可编辑状态
	$("#addmanageform").toggleEditState(false);
	//维护按钮隐藏
	$(".scbtn").addClass("hidden");
});


/**点击右侧维护区 保存 按钮时*/
$(document).on('click',".saveBtn",function(){
	// url  table id   
	$("#addmanageform").formSave("addrManagement/updateAddress",'addrmanagetable',mytable,saveAddrBack);
});

/**右侧维护区 保存 成功后回调函数*/
var saveAddrBack = function(){
	//列表重新加载
	$("#addrmanagetable").cgetData();
	//不可编辑
	$("#addmanageform").toggleEditState(false);
	//隐藏按钮
	$(".scbtn").addClass("hidden");
}


/** 地址列表初始化 */
var addManage = function () {
	"use strict";
    return {
        //初始化
        init: function () {
        	handleAddManage();
        }
    };
}();



