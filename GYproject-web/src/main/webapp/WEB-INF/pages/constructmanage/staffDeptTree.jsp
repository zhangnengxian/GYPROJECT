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
	var type=$("#unitType option:selected").attr('data-c'),
		deptId = $("#corpId").val();
	console.info("==="+type);
	console.info("==="+deptId);
	var url='';
	if(type=='0'){
		url='dept/getDeptTreeDataByCorpId?deptId='+$('#corpId').val();
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
                    //return { 'id': node.id };
                },
                "dataType": "json"
            }
        },
        "types": {
            "default": { "icon": "fa fa-folder text-warning fa-lg" },
            "file": { "icon": "fa fa-file text-warning fa-lg" }
        },
        "plugins": [ "types"]
    }).bind('loaded.jstree', function() {
    	$("#deptPopTree").jstree("open_all");
	}).on('changed.jstree', function (e, data) {
		var dataTmp = data.instance.get_node(data.selected[0]);
		$("#selDeptId").val(dataTmp.id);
		$("#selDeptName").val(dataTmp.text);
	});
};

//加载部门树
deptPopTree();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->