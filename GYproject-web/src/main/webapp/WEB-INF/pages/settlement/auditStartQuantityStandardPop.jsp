<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->
<!-- begin #content -->
<div class="form-box">
    <div id="jstree-safe">
    	
    </div>
</div>
<!-- end #content -->
<script src="assets/plugins/jstree/dist/jstree.min.js"></script>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    $.getScript("assets/plugins/jstree/dist/jstree.min.js").done(function(){
        $('#jstree-safe').jstree({
            'plugins': ["wholerow", "checkbox", "types"],
            'core': {
                "themes": {
                    "responsive": false
                }, 
                
                'data': {
                    'url': function (node) {
                    	
                        return 'qualitiesDeclaration/queryQuantityStand?id='+trSData.settlementAuditStartTable.json.projId;
                        
                    },
                    'data': function (node) {
                        return {'id': node.id,
                        	    };
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
    	$('#jstree-safe').on("ready.jstree",function(){
    		$('#jstree-safe').jstree().select_node(treeArr);
    	});
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->