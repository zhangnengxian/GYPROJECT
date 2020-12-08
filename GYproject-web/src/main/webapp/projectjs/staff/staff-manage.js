var staffManageTable;
var searchData={};
var staffList = function() {
	"use strict";
	return {
		// main function
		init : function() {
			if ($('#staffList').length !== 0) {
					staffManageTable = $('#staffList').on("init.dt",function(){
					$(this).bindDTSelected(trSelectedBack,true);
						//隐藏遮罩
					$(this).hideMask();
		    		//加载弹屏按钮
					$("#staffList_filter").append('<a id="staffListPop" class="asBtn btn btn-default btn-sm m-l-10"> <i class="fa fa-search-plus"></i></a>');
		    		//给类属性是edit的对象的data-c属性赋值
		    		$(".edit").attr("data-c","#staffInfo/viewPage");
		    		//地址详述区加载
		   			$("#staff_manage_panel_box").cgetPart($(".edit"));
		   			$("#staffList_filter input").attr("placeholder","员工名称");
					/*$("#staffListPop").on("click",function(){
		   				var url = '#staff/staffSearchView';
		   				var popoptions = {
		   					title: '查询',
		   					content: url,
		   					accept: staffSearchDone
		   				};
		   				$("body").cgetPopup(popoptions);
		   			});*/
					//基础条件查询添加监听
					$('#staffList_filter input').on('change',function(){
						var staffName = $('#staffList_filter input').val();
						searchData = {};
						searchData.staffName = staffName;
						$('#staffList').cgetData(true,staffCallBack);  //列表重新加载
					});
					//基础条件查询添加回车事件
					$('#staffList_filter input').on('keyup', function(event) {
					    	$(this).change();
					});	
				}).DataTable({
					language : language_CN,
					lengthMenu : [ 18 ],
					dom : 'Bfrtip',
					buttons : [ 
			            /*{text : '增加',className : 'btn-sm btn-query add business-tool-btn'},*/
			            {text : '修改',className : 'btn-sm btn-query edit business-tool-btn'}/*, 
			            {text : '删除',className : 'btn-sm btn-warn del business-tool-btn'}*/
					],
					// 启用服务端模式，后台进行分段查询、排序
					serverSide : true,
					ajax : {
						url : 'staffInfo/queryStaff',
						type : 'post',
						data : function(d) {
							$.each(searchData, function(i, k) {
								d[i] = k;
							});
						},
						dataSrc : 'data'
					},
					responsive : {
		            	details: {
		            		renderer: function ( api, rowIdx, columns ){
		            			return renderChild(api, rowIdx, columns);
		            		}
		            	}
		            },
					select : true, // 支持多选
					columns: [
			  			{"data":"staffId",className:"none never"},
			  			{"data":"loginAccount"},
			  			{"data":"staffName"},
			  			{"data":"mobile"},
						{"data":"deptName"},
						{"data":"postName"}
					],
					/* 隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉） */
					columnDefs : [ {
						"targets" : 0,
						"visible" : false
					}]
				});
			}
			/*$("#workRole").on("shown.bs.tab",function(){
				if(!$.fn.DataTable.isDataTable('#roleList')){
					//初始化列表
					handleRoleList();
				}else{
					$('#roleList').cgetData();
					//trSData.t && trSData.t.cgetDetail('staffManageform','staff/queryStaffRole','',detailBack);
				}
			}) 

			//切换页签
			$("#noticeRole").on("shown.bs.tab",function(){
			   if(!$.fn.DataTable.isDataTable('#noticeRoleList')){
				   console.info("切换页签noticeRole1");
				 //初始化工作通知
			       workNoticeInit();
			   }else{
				   console.info("切换页签noticeRole2");
			       $("#noticeRoleList").cgetData(false,queryNoticeRoleBack);
				   //trSData.t && trSData.t.cgetDetail('staffManageform','staff/queryStaffRole','',detailBack);
			   } 
			});*/
		}
	};
}();
function staffCallBack(){
	var len = $('#staffList').DataTable().ajax.json().data ? $('#staffList').DataTable().ajax.json().data.length : $('#staffList').DataTable().ajax.json().length;
	if(len<1){
		//清空右侧记录
		$("#staffManageform")[0].reset();
		$("#staffPhoto,#signPicture").addClass("disabled");
		$('#staffManageform').toggleEditState(false);
	}
}
function staffSearchDone(){
	searchData=$("#staffListPopform").serializeJson();
	
	console.info(searchData);
	
	var staffName = $("#staffList_filter input").val();
	searchData.staffName=staffName;
	searchData.corpType=$('#unitTypePop option:selected').attr('data-c');
	$("#staffList").cgetData();
}

/** 详述回调函数 */
var trSelectedBack = function(v, i, index, t, json){
	
	//传false表示不可编辑
	$("#staffPhoto,#signPicture").addClass("disabled");
	$("#staffManageform").toggleEditState(false);
	//隐藏维护按钮  保存和放弃 点修改的时候加载
	$(".scbtn").addClass("hidden");
	//点击单行查看详情
	t.cgetDetail('staffManageform','staff/queryStaffRole','',detailBack);
}

/**详述查询完回调函数*/
var detailBack = function(data){
	
	/*if(!$.fn.DataTable.isDataTable('#roleList')){
		//初始化列表
		handleRoleList();
	}else{
		$('#roleList').cgetData(false,queryRoleBack);
	}
	
	if(!$.fn.DataTable.isDataTable('#noticeRoleList')){
		//初始化列表
		workNoticeInit();
	}else{
		$('#noticeRoleList').cgetData(false,queryNoticeRoleBack);
	}*/
	
	
	console.info("flw2---"+data.constructionQaeUrlPath);
	
	
	$(".descrip").addClass("hidden");
	$('#deptName').val(staffManageTable.row(".selected").data().deptName);
	if($('#roleList').DataTable().rows().data().length > 0) {
		selectedStaffRole(data.roleIds.split(","));
	}
	if($("#photoUrl").val()){
		$('#image11').attr("src","attachments/photo/"+$("#photoUrl").val());
	}else{
		$('#image11').attr("src","");
	}
	/*if(data.constructionQaeUrlPath){
		var a="attachments/signPicture/"+data.constructionQaeUrlPath;
		console.info("111111111111111---"+a);
		$('.imageSign').attr("src",a);
	}else{
		$('.imageSign').attr("src","");
	}*/
	
};

function selectedStaffRole(data) {
	if($('#roleList').length > 0 && $('#roleList').DataTable().rows().data().length > 0) {
		var table = $('#roleList').DataTable();
		table.rows(".selected").deselect();
		$.each(data,function(key, val){
			var tableData = table.rows().data();
			$.each(tableData,function(key1, val1){
				if(val == val1.roleId) {
					table.row(key1).select();
				}
			});
		});
	}
}

function selectedNoticeStaffRole(data){
	console.info("通知角色赋值-------");
	console.info(data);
	
	if($('#noticeRoleList').length > 0 && $('#noticeRoleList').DataTable().rows().data().length > 0) {
		var table = $('#noticeRoleList').DataTable();
		table.rows(".selected").deselect();
		$.each(data,function(key, val){
			var tableData = table.rows().data();
			$.each(tableData,function(key1, val1){
				if(val == val1.nrId) {
					table.row(key1).select();
				}
			});
		});
	}
}

/**点击列表【修改】按钮时*/
$(document).off("click",".edit").on("click",".edit",function(){
	var len=$("#staffList").find("tr.selected").length;
	if(len>0){
		if($('#staffNo').removeClass('field-editable')){
			$('#staffNo').addClass('field-not-editable');
		}else{
			$('#staffNo').removeClass('field-editable')
			$('#staffNo').addClass('field-not-editable');
		}
		 $("#staffPhoto,#signPicture").removeClass("disabled");
		//切换到可编辑状态
		$("#staffManageform").toggleEditState(true);
		
		$('#staffNo').addClass('field-not-editable');
		//维护按钮显示出来
		$(".scbtn").removeClass("hidden");
		//显示地址信息
		$(".descrip").removeClass("hidden");
	}else{
		var popoptions = {
			title: '提示',
			content: '请选择要修改的员工信息!',
			ahide:true,
			atext:'确定'
	    };
		$("body").cgetPopup(popoptions);
	}
	
});

/**点击列表【删除】按钮时*/
$(document).off("click",".del").on("click",".del",function(){
	var len=$("#staffList").find("tr.selected").length;
	if(len>0){
		var popoptions = {
			title: '提示',
			content: '您确定删除该员工信息吗？',
			accept: deleteDone
	    };
		$("body").cgetPopup(popoptions);
	}else{
		var popoptions = {
			title: '提示',
			content: '请选择要删除的员工信息!',
			ahide:true,
			atext:'确定'
	    };
		$("body").cgetPopup(popoptions);
	}
});

/**点击【删除】确定后*/
var deleteDone=function(){
	$("#staffList").deleteRow(0,"staff/deleteStaff",staffManageTable);
}

/**右侧维护区【放弃】按钮点击后*/
$(document).off("click",".cancelBtn").on("click",".cancelBtn",function(){
	//信息隐藏
	$(".descrip").addClass("hidden");
	//列表重新加载
	$("#staffList").cgetData();
	//恢复到不可编辑状态
	$("#staffPhoto,#signPicture").addClass("disabled");
	$("#staffManageform").toggleEditState(false);
	//维护按钮隐藏
	$(".scbtn").addClass("hidden");
});


/**点击右侧维护区【保存】按钮时*/
$(document).off("click",".saveBtn").on('click',".saveBtn",function(){
	var roleIds = '';
	var data = $('#roleList').DataTable().rows('.selected').data();
	$.each(data,function(key, val){
		if(roleIds.length > 0) roleIds +=",";
		roleIds += val.roleId;
	});
	$("#roleIds").val(roleIds);
	
	var noticeRoleIds = '';
	 var data = $('#noticeRoleList').DataTable().rows('.selected').data();
	 $.each(data,function(key, val){
	 	if(noticeRoleIds.length > 0) noticeRoleIds +=",";
	 		noticeRoleIds += val.nrId;
	 		
	 });
	 $("#noticeRoleIds").val(noticeRoleIds);
	
	//$("#staffManageform").formSave("staff/addOrUpdateStaff",'staffList',staffManageTable,saveAddrBack);
	if($("#staffManageform").parsley().isValid()){
    	var data=$("#staffManageform").serializeJson();
    	    data.corpType=$("#unitType option:selected").attr('data-c');
    	$.ajax({
            type: 'POST',
            url: 'staffInfo/updateStaff',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
            	var content = "数据保存成功！";
            	if(data === "false"){
            		content = "数据保存失败！";
            	}else if(data ===  "exist"){
            		content = "员工编号已存在，请修改！";
            	}else if(data ===  "existAccount"){
            		content = "登录账号已存在，请修改！";
            	}else if(data === "true"){
            		//列表重新加载
            		$("#staffList").cgetData();
            		//不可编辑
            		$("#staffPhoto,#signPicture").addClass("disabled");
            		$("#staffManageform").toggleEditState(false);
            		//隐藏按钮
            		$(".scbtn").addClass("hidden");
            	}else{
            		$("#staffList").cgetData();  //列表重新加载
            	}
            	var myoptions = {
                    	title: "提示信息",
                    	content: content,
                    	accept: popClose,
                    	chide: true,
                    	icon: "fa-check-circle"
                }
                $("body").cgetPopup(myoptions);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("新增或修改员工信息失败！");
            }
        });
    	//如果通过验证, 则移除验证UI
    	$("#staffManageform").parsley().reset();
    }else{
    	//如果未通过验证, 则加载验证UI
    	$("#staffManageform").parsley().validate();
    }
});

/**右侧维护区【保存】成功后回调函数*/
var saveAddrBack = function(){
	//列表重新加载
	$("#staffList").cgetData();
	//不可编辑
	$("#staffPhoto,#signPicture").addClass("disabled");
	$("#staffManageform").toggleEditState(false);
	//隐藏按钮
	$(".scbtn").addClass("hidden");
}

$(document).off("click",".add").on("click",".add",function(){
	$("#image11").attr('src','');
	
	$('#signPicturePath').val("");
	$('#constructionQaeUrlPath').val("");
	
	$('#signPictureStr_postType').next().attr('src','').attr('title','');
	
	
	if($('#staffNo').removeClass('field-not-editable')){
		$('#staffNo').addClass('field-editable');
	}else{
		$('#staffNo').removeClass('field-not-editable')
		$('#staffNo').addClass('field-editable');
	}
	//切换到可编辑状态
	$("#staffPhoto,#signPicture").removeClass("disabled");
	$("#staffManageform").toggleEditState(true);
	//维护按钮显示出来
	$(".scbtn").removeClass("hidden");
	//显示信息
	$(".descrip").removeClass("hidden");
	$("#staffManageform")[0].reset();
	//清空员工角色信息
	if($('#roleList').length > 0 && $('#roleList').DataTable().rows().data().length > 0) {
		$('#roleList').DataTable().rows(".selected").deselect();
	}
});

var queryRoleBack=function(){
	if($('#roleList').DataTable().rows().data().length > 0) {
		var data=$("#roleIds").val();
		selectedStaffRole(data.split(","));
	}
}

var roleListTable;
var handleRoleList=function(){
	roleListTable=$('#roleList').on("init.dt", function(){
	    	bindTableSelectRoleListRight();
	    	queryRoleBack();
	    }).DataTable({
			language : language_CN,
			lengthMenu : [ 200 ],
			dom : 'Bfrtip',
			buttons : [],
			paging : false,
			dom : 'Bfrtip',
			info: false,
			// 启用服务端模式
			serverSide : true,
			ajax : {
				url : 'auth/queryAllRole',
				type : 'post',
				data : function(d) {
					$.each(function(i, k) {
						d[i] = k;
					});
				},
				dataSrc : 'data'
			},
			select : true, // 支持多选
			select: {
	            style: 'multi'
	        },
			searching: false,
			columns: [
					{"data":"roleId",className:"none never"},
					{"data":"roleCode"},
					{"data":"roleName"}
			],
			/* 隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉） */
			columnDefs : [ {
				"targets" : 0,
				"visible" : false
			} ]
		});
};

//绑定表格行选中事件
function bindTableSelectRoleListRight() {
	$('#roleList').DataTable().on( 'select', function ( e, dt, type, indexes ) {
		var roleIds = '';
		var data = $('#roleList').DataTable().rows('.selected').data();
		$.each(data,function(key, val){
			if(roleIds.length > 0) roleIds +=",";
			roleIds += val.roleId;
		});
		$("#roleIds").val(roleIds);
   	});
}



var workNoticeInit=function(){
	  $('#noticeRoleList').on("init.dt", function(){
	    	bindTableSelectNoticeRoleListRight();
	    	queryNoticeRoleBack();
	    }).DataTable({
			language : language_CN,
			lengthMenu : [ 200 ],
			dom : 'Bfrtip',
			buttons : [],
			paging : false,
			info: false,
			// 启用服务端模式
			serverSide : true,
			ajax : {
				url : 'noticeManage/queryRoleList',
				type : 'post',
				data : function(d) {
					$.each(function(i, k) {
						d[i] = k;
					});
				},
				dataSrc : 'data'
			},
			select : true, // 支持多选
			select: {
	            style: 'multi'
	        },
			searching: false,
			columns: [
				{"data":"nrId",className:"none never"},
				{"data":"nrCode"},
				{"data":"nrName"}
			],
			/* 隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉） */
			columnDefs : [ {
				"targets" : 0,
				"visible" : false
			} ]
		});
}

var bindTableSelectNoticeRoleListRight=function(){
	  $('#noticeRoleList').DataTable().on( 'select', function ( e, dt, type, indexes ) {
		 var noticeRoleIds = '';
		 var data = $('#noticeRoleList').DataTable().rows('.selected').data();
		 $.each(data,function(key, val){
		 	if(noticeRoleIds.length > 0) noticeRoleIds +=",";
		 		noticeRoleIds += val.nrId;
		 		
		 });
		 $("#noticeRoleIds").val(noticeRoleIds);
  });
}


var queryNoticeRoleBack=function(){
	
	if($('#noticeRoleList').DataTable().rows().data().length > 0) {
		var data=$("#noticeRoleIds").val();
		
		if(data!=""){
			selectedNoticeStaffRole(data.split(","));
		}
	}
}