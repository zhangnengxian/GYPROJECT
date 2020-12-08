var deptTree = function() {
    $('#deptTree').jstree({
        "core": {
            "themes": { "responsive": false },
            "check_callback": true,
            'data': {
                'url': function (node) {
                    return 'dept/getDeptTreeData';
                },
                'data': function (node) {
                    //return { 'deptId': '11' };
                },
                "dataType": "json"
            }
        },
        "types": {
            "default": { "icon": "fa fa-folder text-warning fa-lg" },
            "file": { "icon": "fa fa-file text-warning fa-lg" }
        },
        "plugins": [ "types"]
    }).one('loaded.jstree', function() {
    	$("#deptTree").jstree("open_all");
	});
};


var handleDept = function() {
	"use strict";
	$("#dept_manage_panel_box").cgetPart($("#dept_manage_panel_box"),"", deptRightCallBack);
};

var deptRightCallBack = function(){
	$(".jstree-clicked").click();
};

$(document).off("click", "#deptTree .jstree-node").on("click","#deptTree .jstree-node",function(){
	if($(this).attr("aria-selected")=="true"){
		var level = $(this).attr("aria-level");
		
		$("#parentDeptId").val($(this).attr("id"));
		var deptId = $("#parentDeptId").val();
		viewDept(deptId);
	}
	
});

/** 详述回调函数 */
function viewDept(deptId){
	$("#deptTypeDiv").removeClass("hidden");
	$.ajax({
        type: 'POST',
        url: 'dept/queryDepartment',
        data: 'deptId='+deptId,
        dataType: 'json',
        success: function (data) {
        	$("#deptInnerCode").val(data.deptInnerCode);
        	$("#deptName").val(data.deptName);
        	$("#deptType").val(data.deptType);
        	$("#deptTypeName").val(data.deptTypeName);
        	$("#location").val(data.location);
        	$("#phone").val(data.phone);
        	$("#fax").val(data.fax);
        	$("#principal").val(data.principal);
        	$("#deptId").val(data.deptId);
        	$("#businessType").val(data.businessType);
        	$("#projContructType").val(data.projContructType);
        	$("#deptDivide").val(data.deptDivide);
        	$("#parentDeptType").val(data.deptType);
        	$("#isAcceptDept").val(data.isAcceptDept);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn(jqXHR);
            console.warn(textStatus);
            console.warn(errorThrown);
        }
    });
}

/**点击列表【修改】按钮时*/
function editDept(){
	var parentDeptId = $("#parentDeptId").val();
	if(parentDeptId!=''){
		$("#deptTypeDiv").removeClass("hidden");
		//切换到可编辑状态
		$("#deptManageform").toggleEditState(true);
		//维护按钮显示出来
		$(".scbtn").removeClass("hidden");
		//显示地址信息
		$(".descrip").removeClass("hidden");
		$("#deptTypeName").attr("disabled", "disabled");
	}else{
		var popoptions = {
				title: '提示',
				content: '请选择要修改的部门信息!',
				ahide:true,
				atext:'确定'
	    };
		$("body").cgetPopup(popoptions);
	}
	
}

/**点击列表【删除】按钮时*/
function delDept(){
	var parentDeptId = $("#parentDeptId").val();
	if(parentDeptId!=''){
		var popoptions = {
			title: '提示',
			content: '您确定删除该部门节点吗？',
			accept: deleteDone
	    };
		$("body").cgetPopup(popoptions);
	}else{
		var popoptions = {
			title: '提示',
			content: '请选择要删除的部门节点!',
			chide:true,
			accept: popClose,
			atext:'确定'
	    };
		$("body").cgetPopup(popoptions);
	}
}

/**点击【删除】确定后*/
var deleteDone=function(){
	var deptId = $("#parentDeptId").val();
	$.ajax({
        type: 'POST',
        url: 'dept/deleteDepartment',
        data: 'deptId='+deptId,
        dataType: 'text',
        success: function (data) {
        	if(data){
        		var popoptions = {
    				title: '提示',
    				content: '删除成功!',
    				chide:true,
    				newpop: 'new',
    				accept: popClose,
    				atext:'确定'
        	    };
        		$("body").cgetPopup(popoptions);
        		//重新加载
        		$("#deptTree").jstree("refresh");
        	}else{
        		var popoptions = {
    				title: '提示',
    				content: '删除失败!',
    				chide:true,
    				newpop: 'new',
    				accept: popClose,
    				atext:'确定'
        	    };
        		$("body").cgetPopup(popoptions);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn(jqXHR);
            console.warn(textStatus);
            console.warn(errorThrown);
        }
    });
}

/**右侧维护区【放弃】按钮点击后*/
$(document).off("click",".cancelBtn").on("click",".cancelBtn",function(){
	//信息隐藏
	$(".descrip").addClass("hidden");
	//恢复到不可编辑状态
	$("#deptManageform").toggleEditState(false);
	//维护按钮隐藏
	$(".scbtn").addClass("hidden");
	$("#deptTypeDiv").removeClass("hidden");
});


/**点击右侧维护区【保存】按钮时*/
$(document).off("click",".saveBtn").on('click',".saveBtn",function(){
	$("#deptManageform").formSave("dept/addOrUpdateDepartment",'deptTree','',saveAddrBack);
});

/**右侧维护区【保存】成功后回调函数*/
var saveAddrBack = function(){
	//列表重新加载
	$("#deptTree").jstree("destroy");
	deptTree();
	//不可编辑
	$("#deptManageform").toggleEditState(false);
	//隐藏按钮
	$(".scbtn").addClass("hidden");
}

function addDept(){
	var parentDeptId = $("#parentDeptId").val();
	var parentDeptType = $("#parentDeptType").val();
	if(parentDeptType!=''){
		if(parentDeptType == '4'){
			var popoptions = {
				title: '提示',
				content: '该部门节点不可增加!',
				ahide:true,
				atext:'确定'
		    };
			$("body").cgetPopup(popoptions);
			return;
		}
		$("#deptTypeDiv").addClass("hidden");
		//切换到可编辑状态
		$("#deptManageform").toggleEditState(true);
		//维护按钮显示出来
		$(".scbtn").removeClass("hidden");
		//显示信息
		$(".descrip").removeClass("hidden");
		$("#deptManageform")[0].reset();
		
		//给父节点部门信息赋值
		$("#parentDeptId").val(parentDeptId);
		$("#parentDeptType").val(parentDeptType);
	}else{
		var popoptions = {
			title: '提示',
			content: '请选择一个部门节点!',
			ahide:true,
			atext:'确定'
	    };
		$("body").cgetPopup(popoptions);
	}
}

var deptManage = function () {
	"use strict";
    return {
        init: function () {
        	handleDept();
        	deptTree();
        }
    };
}();