<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="form-box">
	<div class="form-group col-md-12">
		<input type="hidden"  id="selDeptId" name="selDeptId">
		<input type="hidden"  id="selDeptName" name="selDeptName">
		<div id="deptPopTree"></div>
	</div>
</div>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script type="text/javascript">
var deptPopTree = function() {
	var type=$("#unitType option:selected").attr('data-c');
	console.info("==="+type);
	var url='';
	if(type=='0'){
		url='dept/getDeptTreeData';
	}else if(type=='1'){
		url='businessPartners/getBusPartTreeData?unitType='+$('#unitType').val();
	}
	
    $('#deptPopTree').jstree({
        "core": {
            "themes": { "responsive": false },
            "check_callback": true,
            'data': {
                'url': function (node) {
                    return url;
                },
                'data': function (node) {
                },
                "dataType": "json"
            }
        },
        "types": {
            "default": { "icon": "fa fa-folder text-warning fa-lg" },
            "file": { "icon": "fa fa-file text-warning fa-lg" }
        },
        "plugins": [ "wholerow","checkbox" ]
    }).bind('loaded.jstree', function() {
    	$("#deptPopTree").jstree("open_all");
	}).on('changed.jstree', function (e, data) {
        var tree = data.instance;
        var selDeptId="",selDeptName="";
        var ref = $('#deptPopTree').jstree(true).get_selected(false);//获得所有选中节点，返回值为数组
        $.each(ref,function(key,value){  //遍历键值对
            if(!tree.is_parent(value)){
                var nodeObj= data.instance.get_node(value);
                selDeptId+=nodeObj.id+",";
                selDeptName+=nodeObj.text+"，";
			}
        })

		$("#selDeptId").val(selDeptId.substring(0,selDeptId.length-1));//去掉最后一个逗号
		$("#selDeptName").val(selDeptName.substring(0,selDeptName.length-1));//去掉最后一个逗号
	});
};

//加载部门树
deptPopTree();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->