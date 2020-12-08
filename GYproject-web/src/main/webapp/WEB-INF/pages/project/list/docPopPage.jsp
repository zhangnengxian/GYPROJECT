<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<!-- <div class="clearboth form-box">
    
</div> -->
<table id="accessoryListTable" class="table table-hover table-bordered nowrap" width="100%">
	<thead>
          <tr>
              <th>工程阶段</th>
              <th>资料名称</th>
              <th>文档类型</th>
              <th>签收日期</th>
              <th>签收人</th>
              <th>链接</th>
          </tr>
      	</thead>
</table>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
	//var accessorySearchData = $('#projectConstructListTable')
	console.log(trSData.projectConstructListTable.json);
	var projId = trSData.projectConstructListTable.json.projId;
	var accessorySearchData = {};
	accessorySearchData.projId = projId;
	$('#accessoryListTable').on( 'init.dt',function(){
		$('#accessoryListTable').hideMask();
		//资料查看文件
    	$(".Search_Button").off("click").on("click",function(){
    	     var data = {};
    	     data.fpath = $(this).attr("data-row");
    		 $.ajax({
    			url:'accessoryCollect/openFile',
    			type:'post',
    			data:data,
    			success:function(data){
	    		    if(data == "nofile"){
	    		    	$("body").cgetPopup({
		    		    	title: "提示信息",
		    		    	content: "文件不存在! <br>",
		    		    	accept: popClose2,
		    		    	chide: true,
		    		    	icon: "fa-exclamation-circle"
		    		    });  		    	
	    		    }
    		    }
    		});
    	});
    }).DataTable({
    	language: language_CN,
        lengthMenu: [18],
        dom: 'Brt',
        buttons: [
        ],
        ajax: {
            url: 'projectView/queryAccessoryList',
            type:'post',
            data: function(d){
               	$.each(accessorySearchData,function(i,k){
               		d[i] = k;
               	});
            },
            dataSrc: 'data'
        },
        responsive: {
        	details: {
        		renderer: function ( api, rowIdx, columns ){
        			return renderChild(api, rowIdx, columns);
        		}
        	}
        },
        select: true,  //支持多选
        columns: [
            {"data":"stepId"},
  			{"data":"alName"},
  			{"data":"alTypeId"},
  			{"data":"alOperateTime"},
  			{"data":"alOperateCsr"},
  			{"data":"alId"}
  			
		],
		columnDefs: [{
			targets: 5,
			render: function (data, type, row, meta) {
				if(type === "display"){
					var  tdcon='<a target="_blank" class="Search_Button" data-row="' + data + '" href="/accessoryCollect/openFile?id='+data+'">查看</a>';
					return tdcon;
				}else{
					return data;
				}
			}
		}]
   });

 	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->