var roleManageTable;
var roleManageSearchData={};
var menuIdsRole;
var selectedRowRoleManage;
var isInitRoleManage = false;
var roleList = function() {
	"use strict";
	return {
		// main function
		init : function() {
			if ($('#roleListTable').length !== 0) {
				roleManageTable = $('#roleListTable').on("init.dt",function(){
					//隐藏遮罩
					$(this).hideMask();
					isInitRoleManage = true;
					//默认选中第一行
					$(this).bindDTSelected(trSelectedBackRoleManage,true);
					//加载弹屏按钮
					$("#roleListTable_filter").append('<a id="roleListPop" class="asBtn btn btn-default btn-sm m-l-10"> <i class="fa fa-search-plus"></i></a>');
		    		//给类属性是edit的对象的data-c属性赋值
		    		$(".edit").attr("data-c","#noticeManage/viewPage");
		    		//详述区加载
		   			$("#role_manage_panel_box").cgetContent("noticeManage/viewPage", "", function() {
		   				menuTree();
		   			});
		   			$("#roleListTable_filter input").attr("placeholder","角色编号");
					$("#roleListPop").on("click",function(){
		   				var url = '#noticeManage/roleSearchView';
		   				var popoptions = {
		   					title: '查询',
		   					content: url,
		   					accept: roleSearchDone
		   				};
		   				$("body").cgetPopup(popoptions);
		   			});
					//基础条件查询添加监听
					$('#roleListTable_filter input').on('change',function(){
						var nrCode = $('#roleListTable_filter input').val();
						roleManageSearchData = {};
						roleManageSearchData.nrCode = nrCode;
						$('#roleListTable').cgetData();  //列表重新加载
					});
					//基础条件查询添加回车事件
					$('#roleListTable_filter input').on('keyup', function(event) {
					    if (event.keyCode == '13') {
					    	$(this).change();
					    }
					});	
				}).DataTable({
					language : language_CN,
					lengthMenu : [ 18 ],
					dom : 'Bfrtip',
					buttons : [ 
					    {text : '增加',className : 'btn-sm btn-query add business-tool-btn'},
						{text : '修改',className : 'btn-sm btn-query edit business-tool-btn'}, 
						{text : '删除',className : 'btn-sm btn-warn del business-tool-btn'}
					],
					// 启用服务端模式，后台进行分段查询、排序
					serverSide : true,
					ajax : {
						url : 'noticeManage/queryRoleList',
						type : 'post',
						data : function(d) {
							$.each(roleManageSearchData, function(i, k) {
								d[i] = k;
							});
						},
						dataSrc : 'data'
					},
					responsive : true,// 自适应
					select : true, // 支持多选
					columns: [
			  			{"data":"nrId",className:"none never"},
			  			{"data":"nrCode"},
			  			{"data":"nrName"}
			  			//{"data":"createTimeStr"}
					],
					/* 隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉） */
					columnDefs : [ {
						"targets" : 0,
						"visible" : false
					}]
				});
			}
		}
	};
}();

/*
 * 表格加载完成后
 */
$('#roleListTable').on( 'draw.dt', function () {
	if(roleManageTable.rows().data().length > 0) {
		selectedRowRoleManage = 0;
	}else {
		selectedRowRoleManage = -1;
	}
	return false;
});

function roleSearchDone(){
	roleManageSearchData=$("#roleListPopform").serializeJson();
	var nrCode = $("#roleListTable_filter input").val();
	roleManageSearchData.nrCode=nrCode;
	$("#roleListTable").cgetData();
}

var menuTree = function() {
    $('#menuTreeRoleManage').jstree({
        "core": {
            "themes": { "responsive": false },
            "check_callback": true,
            'data': {
                'url': 'menu/getMenuTreeData',
                "dataType": "json"
            }
        },
        "types": {
            "default": { "icon": "fa fa-folder text-warning fa-lg" },
            "file": { "icon": "fa fa-file text-warning fa-lg" }
        },
        "plugins": [ "wholerow","checkbox" ]
    }).bind("loaded.jstree", function(e, data){
    	$("#menuTreeRoleManage").jstree("open_all");
    	if(selectedRowRoleManage >= 0) {
    		$("#roleListTable tr:eq(" + selectedRowRoleManage + ")").cgetDetail('roleManageform','noticeManage/queryRole','',detailBackRoleManage); 
		}
    }); 
};

/** 详述回调函数 */
var trSelectedBackRoleManage = function(v, i, index, t, json){
	selectedRowRoleManage = index;
	//传false表示不可编辑
	$("#roleManageform").toggleEditState(false);
	//隐藏维护按钮  保存和放弃 点修改的时候加载
	$(".scbtn").addClass("hidden");
	//点击单行查看详情
	if(!isInitRoleManage) {
		t.cgetDetail('roleManageform','noticeManage/queryRole','',detailBackRoleManage); 
	}
	isInitRoleManage = false;
}

function menuChecked(menuIds) {
	$("#menuTreeRoleManage").jstree("uncheck_all");
	$("#menuTreeRoleManage").jstree("select_node", menuIds);  
}

/**详述查询完回调函数*/
var detailBackRoleManage = function(data){
	console.info("data.menuIds---------");
	console.info(data.nrMenuIds);
	
	$(".descrip").addClass("hidden");
	var ids = data.menuIds;
	var menuIds = ids!=null ? ids.split(',') : [];
	menuChecked(menuIds);
	menuIdsRole = menuIds;
}

/**右侧维护区【保存】成功后回调函数*/
var saveAddrBack = function(){
	//列表重新加载
	$("#roleListTable").cgetData();
	//不可编辑
	$("#roleManageform").toggleEditState(false);
	//隐藏按钮
	$(".scbtn").addClass("hidden");
}

/**点击列表【修改】按钮时*/
$(document).off("click",".edit").on("click", ".edit", function(){
	var len=$("#roleListTable").find("tr.selected").length;
	if(len>0){
		if($('#nrCode').removeClass('field-editable')){
			$('#nrCode').addClass('field-not-editable');
		}else{
			$('#nrCode').removeClass('field-editable')
			$('#nrCode').addClass('field-not-editable');
		}
		//切换到可编辑状态
		$("#roleManageform").toggleEditState(true);
		//维护按钮显示出来
		$(".scbtn").removeClass("hidden");
		//显示地址信息
		$(".descrip").removeClass("hidden");
		
		menuChecked(menuIdsRole);
	}else{
		var popoptions = {
			title: '提示',
			content: '请选择要修改的角色信息!',
			ahide:true,
			atext:'确定'
	    };
		$("body").cgetPopup(popoptions);
	}
	
});

/**点击列表【删除】按钮时*/
$(document).off("click",".del").on("click", ".del", function(){
	var len=$("#roleListTable").find("tr.selected").length;
	if(len>0){
		var popoptions = {
			title: '提示',
			content: '您确定删除该角色信息吗？',
			accept: deleteDone
	    };
		$("body").cgetPopup(popoptions);
	}else{
		var popoptions = {
			title: '提示',
			content: '请选择要删除的角色信息!',
			ahide:true,
			atext:'确定'
	    };
		$("body").cgetPopup(popoptions);
	}
});

/**点击【删除】确定后*/
var deleteDone=function(){
	$("#roleListTable").deleteRow(0,"noticeManage/deleteRole",roleManageTable);
}

/**右侧维护区【放弃】按钮点击后*/
$(document).off("click",".cancelBtn").on("click",".cancelBtn",function(){
	$("#roleListTable").cgetData();
	//信息隐藏
	$(".descrip").addClass("hidden");
	//恢复到不可编辑状态
	$("#roleManageform").toggleEditState(false);
	//维护按钮隐藏
	$(".scbtn").addClass("hidden");
});


/**点击右侧维护区【保存】按钮时*/
$(document).off("click",".saveBtn").on('click',".saveBtn",function(){
	var menuIds = $('#menuTreeRoleManage').jstree().get_checked();
	//$("#roleManageform").formSave("noticeManage/addOrUpdateRole?menuIds="+menuIds,'roleListTable',roleManageTable,saveAddrBack);
	if($("#roleManageform").parsley().isValid()){
    	var data=$("#roleManageform").serializeJson();
    	$.ajax({
            type: 'POST',
            url: 'noticeManage/addOrUpdateRole?menuIds='+menuIds,
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
            	var content = "数据保存成功！";
            	if(data === "false"){
            		content = "数据保存失败！";
            	}else if(data ===  "exist"){
            		content = "角色编号已存在，请修改！";
            	}else if(data === "true"){
            		//列表重新加载
            		$("#roleListTable").cgetData();
            		//不可编辑
            		$("#roleManageform").toggleEditState(false);
            		//隐藏按钮
            		$(".scbtn").addClass("hidden");
            	}else{
            		$("#roleListTable").cgetData();  //列表重新加载
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
                console.warn("新增或修改角色信息失败！");
            }
        });
    	//如果通过验证, 则移除验证UI
    	$("#roleManageform").parsley().reset();
    }else{
    	//如果未通过验证, 则加载验证UI
    	$("#roleManageform").parsley().validate();
    }
});

/**右侧维护区【保存】成功后回调函数*/
var saveAddrBack = function(){
	//列表重新加载
	$("#roleListTable").cgetData();
	//不可编辑
	$("#roleManageform").toggleEditState(false);
	//隐藏按钮
	$(".scbtn").addClass("hidden");
}

$(document).off("click",".add").on("click", ".add", function(){
	if($('#nrCode').removeClass('field-not-editable')){
		$('#nrCode').addClass('field-editable');
	}else{
		$('#nrCode').removeClass('field-not-editable')
		$('#nrCode').addClass('field-editable');
	}
	//切换到可编辑状态
	$("#roleManageform").toggleEditState(true);
	//维护按钮显示出来
	$(".scbtn").removeClass("hidden");
	//显示信息
	$(".descrip").removeClass("hidden");
	$("#roleManageform")[0].reset();
	
	menuChecked([]);
});

