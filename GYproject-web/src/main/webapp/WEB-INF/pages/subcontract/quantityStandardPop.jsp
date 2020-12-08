<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->
<!-- begin #content -->
<form class="form-horizontal" id="projectStandard" action="">
         <div class="form-group col-md-4 ">
                <label class="control-label" for="versionOfProj">版本</label>
                <div >
                   <select class="form-control input-sm field-editable clearboth" id="versionOfProj"  name="versionOfProj" >
                    <c:forEach var="enums" items="${version}">
                        <option value="${enums.supSql}">${enums.remark}</option>
                    </c:forEach> 
                  </select>
                </div>
            </div>
         <div class="form-group col-md-4 col-sm-4  ">
                <label class="control-label " for="incIncraMode">增收方式</label>
                <div>
                   <select class="form-control input-sm field-editable " id="incIncraMode"  name="incIncraMode" >
                     <c:forEach var="enums" items="${incIncraMode}">
                        <option value="${enums.supSql}">${enums.remark}</option>
                    </c:forEach> 
                  </select>
                </div>
            </div>
    <div id="jstree-safe">
    </div>
<form/>
<!-- end #content -->
<script src="assets/plugins/jstree/dist/jstree.min.js"></script>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    $("#projectStandard").styleFit();
    $("#incIncraMode").find('option[value="1"]').attr("selected",true);
    $('#versionOfProj option:last').attr('selected','selected');
    var  i = 0;   //全局变量,用于加载js时不执行change事件
    $.getScript("assets/plugins/jstree/dist/jstree.min.js").done(function(){
        queryVers();
         
    });
    
    //选择工程量版本
         $('#versionOfProj').change(function() {
        	if(i!=0){  //控制首次进入不刷新树
            	 $('#jstree-safe').jstree(true).refresh();
        	}
        	/*  $('#jstree-safe').jstree("destroy");  //第二种重新生成树的方式
        	 queryVers(); */
         });
    //選擇增收方式
    $('#incIncraMode').change(function() {
    	if(i!=0){ //控制首次进入不刷新树
        	$('#jstree-safe').jstree(true).refresh();
    	}
        	i=i+1;
    	/* $('#jstree-safe').jstree("destroy");//第二种重新生成树的方式
    	queryVers(); */
         });
    var queryVers = function(){
        $('#jstree-safe').data('jstree', false).empty().jstree({
             'plugins': ['checkbox','types','wholerow'], 
            'core': {
                "themes": {
                    "responsive": false
                },
                'data': {
                    'url': function (node) {
                        return 'qualitiesDeclaration/queryQuantityStand/'+$("#projId").val()+"/"+$("#versionOfProj").val()+"/"+$("#incIncraMode").val();
                    }, 
                    "dataType": "json"
                    }
             },
            "types": {
                "default": {
                    "icon": "fa fa-folder text-primary fa-lg",
                    draggable : true //设置节点不可拖拽     
                },
                "file": {
                    "icon": "fa fa-file text-success fa-lg"
                }
            }
        });
        $('#jstree-safe').on("ready.jstree",function(){
            $('#jstree-safe').jstree().select_node(treeArr);
            console.info(treeArr);
        });
    }

</script>
<!-- ================== END PAGE LEVEL JS ================== -->