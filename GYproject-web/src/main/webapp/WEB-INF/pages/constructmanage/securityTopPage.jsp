<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
<!-- begin #content -->
<div class="form-box">
    <div id="jstreeSafe">
    	
    </div>
</div>
<!-- end #content -->
<script src="assets/plugins/jstree/dist/jstree.min.js"></script>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
   $('#jstreeSafe').jstree({
        'plugins': ["wholerow", "checkbox", "types"],
        'core': {
            "themes": {
                "responsive": false
            }, 
            
            'data': {
                'url': function (node) {
                    return 'securityInspection/querySafetyPunish?corpId='+$("#corpId").val();
                },
                'data': function (node) {
                    return {'id': node.id};
                },
                "dataType": "json"
             }
         },
         
        "types": {
            "default": {
                "icon": "fa fa-folder text-primary fa-lg"
            },
            "file": {
                "icon": "fa fa-file text-success fa-lg"
            }
        }
    }); 
   $('#jstreeSafe').on("ready.jstree",function(){
		$('#jstreeSafe').jstree().select_node(treeArr);
	});
   



  
</script>
<!-- ================== END PAGE LEVEL JS ================== -->