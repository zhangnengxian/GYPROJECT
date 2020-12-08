<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="importtool">
	<form id="fileupload" action="accessoryCollect/uploadFile" method="POST" enctype="multipart/form-data">
	    <input type="hidden" name="alPath" id="alPath" value="0104"/>
	    <input type="hidden" name="caiId" id="caiId" />
	    <input type="hidden" name="projId" id="projId" value="0103"/>
	    <input type="hidden" name="projNo" id="projNo" value="0103"/>
	    <input type="hidden" name="projLtypeId" id="projLtypeId" value="0103"/>
	   
		<div class="fileupload-buttonbar">
	        <div class="pull-right toolBtn">
	            <span class="btn btn-success btn-sm fileinput-button">
	                <i class="fa fa-plus"></i>
	                <span>浏览文件...</span>
	                <input type="file" name="files[]" multiple/>	             	          
	            </span>
	            <button type="submit" class="btn btn-primary btn-sm start">
                    <i class="fa fa-upload"></i>
                    <span>上传</span>
                </button>
	        </div>
	        <!-- The global progress state -->
	        <div class="col-md-12 fileupload-progress fade hidden">
	            <!-- The global progress bar -->
	            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
	                <div class="progress-bar progress-bar-success" style="width:0%;"></div>
	            </div>
	            <!-- The extended global progress state -->
	            <div class="progress-extended">&nbsp;</div>
	        </div>
	    </div>
	    <!-- The table listing the files available for upload/download -->
	    <table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
    </form>
</div>
<div class="p-t-6 p-b-15">
	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
	</div>
    <h4 class="p-t-10"><strong>资料收集标准</strong></h4>
	<table id="dataCollectionSandard" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
           		<th>应收资料名称</th>
            	<th></th>
           	</tr>
       	</thead>
	</table>
    <h4 class="p-t-10"><strong>已收集资料清单</strong></h4>
	<table id="AlreadyDataList" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
     		    <th></th>
           		<th>资料名称</th>
            	<th>签收日期</th>
            	<th>签收人</th>
            	<th>链接</th>
            	<th>删除</th>
           	</tr>
       	</thead>
	</table>
</div> 
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script id="template-upload" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-upload fade">
            <td>
                <p class="name filename">{%=file.name%}</p>
                <strong class="error text-danger"></strong>
            </td>
            <td>
                <p class="size">Processing...</p>
                <div class="progress progress-striped active"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
            </td>
            <td class="hidden">
                {% if (!i && !o.options.autoUpload) { %}
                    <button class="btn btn-primary btn-sm start" disabled>
                        <i class="fa fa-upload"></i>
                        <span>Start</span>
                    </button>
                {% } %}
                {% if (!i) { %}
                    <button class="btn btn-white btn-sm cancel">
                        <i class="fa fa-ban"></i>
                        <span>Cancel</span>
                    </button>
                {% } %}
            </td>
        </tr>
    {% } %}
</script>
<script id="template-download" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-download fade">
            <td>
                <p class="name">
                    {% if (file.url) { %}
                        <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                    {% } else { %}
                        <span>{%=file.name%}</span>
                    {% } %}
                </p>
                {% if (file.error) { %}
                    <div><span class="label label-danger">Error</span> {%=file.error%}</div>
                {% } %}
            </td>
            <td>
                <span class="size">{%=o.formatFileSize(file.size)%}</span>
            </td>
            <td class="hidden">
                {% if (file.deleteUrl) { %}
                    <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                        <i class="glyphicon glyphicon-trash"></i>
                        <span>Delete</span>
                    </button>
                    <input type="checkbox" name="delete" value="1" class="toggle">
                {% } else { %}
                    <button class="btn btn-warning cancel">
                        <i class="glyphicon glyphicon-ban-circle"></i>
                        <span>Cancel</span>
                    </button>
                {% } %}
            </td>
        </tr>
    {% } %}
</script>
<script>
    App.restartGlobalFunction();
    
    //初始化上传文件控件
    $.getScript('projectjs/data/form-multiple-upload.demo.js').done(function() {
    	FormMultipleUpload.init();
    });

	/**
	 * 初始化资料收集(上)
	 */
	var firstdatainit= function() {
		"use strict";
		var data={};
		data.projId = $('.modal-body [name="projId"]').val();
		data.projNo = $('.modal-body [name="projNo"]').val();
		data.projStatusDes = $('.modal-body [name="projStatusDes"]').val();
		data.projLtypeId = $('.modal-body [name="projLtypeId"]').val();
	    if ($('#dataCollectionSandard').length !== 0) {
	    	dataPopTable= $('#dataCollectionSandard').on( 'init.dt',function(){
	   		//默认选中第一行
	  		//$(this).bindDTSelected(trSelectedBack,true);
	    	//seconddatainit();
	    	$("[name='accbox']:checkbox").on('click', function(){  
	    	    var caiId=$(this).attr("data-box");  
// 	    	    console.info("====="+caiId);
	    	    $("#caiId").val(caiId);
	    	});  
	    	$('#dataCollectionSandard').hideMask();
	        }).DataTable({
	        	language: language_CN,
	            lengthMenu: [18],
	            dom: 'Brt',
	            buttons: [],
	            // ajax: 'projectjs/accept/json/data_pop_first.json',
	            ajax: {
	                url: 'accessoryCollect/queryAccessoryItem',
	                type:'post',
	                data: function(d){
	                  	$.each(data,function(i,k){
	                  		d[i] = k;
	                  	});
	               	},
	                dataSrc: ''
	            },
	            responsive: {
	            	details: {
	            		renderer: function ( api, rowIdx, columns ){
	            			return renderChild(api, rowIdx, columns);
	            		}
	            	}
	            },
	            columns: [
		  			{"data":"accessoryName"},
		  			{"data":"caiId",className:"text-right"}
				],
				columnDefs: [{
					targets: 1,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							var  tdcon='<input name="accbox" data-box="'+data+'" type="checkbox">';
							return tdcon;
						}else{
							return data;
						}
					}
				}],
				ordering: false
	       });
	   }
	};

	
	/**
	 * 初始化资料收集(下)
	 */
	var seconddatainit= function() {
		"use strict";
	    if ($('#AlreadyDataList').length !== 0) {
	    	var accessoryData = {};
	    	accessoryData.projNo = $('.modal-body [name="projNo"]').val();
	    	accessoryTable = $('#AlreadyDataList').on( 'draw.dt',function(){
		   		//默认选中第一行
		  		//$(this).bindDTSelected(trSelectedBack,true);
		    	$('#AlreadyDataList').hideMask();
		    	
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
		    	var popClose2 = function(){};
		    	
		    	//删除附件列表记录
		     	$(".del_btn").on("click",function(){
		    		$("body").cgetPopup({
	    				title: '提示',
	    				content: '您确定删除该文件信息吗？',
	    			    accept: {
	    					func: deleteDone,	//函数名
	    					singleArgs: $(this)	//参数，用于向func传入多个参数，不设置该属性或设置为[]，则传入不带参数的函数
	    				}
	    	    	});
		   		});
	        }).DataTable({
	        	language: language_CN,
	           	lengthMenu: [18],
	           	dom: 'Brt',
	           	buttons: [],
	         	//ajax: 'projectjs/accept/json/data_pop.json',
	          	ajax: {
	               	url: 'accessoryCollect/queryAccessoryList',
	               	type:'post',
	               	data: function(d){
	                  	$.each(accessoryData,function(i,k){
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
	           	columns: [
	                {"data":"caiId",className:"none never"},
		  			{"data":"alName"},
		  			{"data":"alOperateTime"},
		  			{"data":"alOperateCsr"},	  			
		  			{"data":"alPath"},
		  			{"data":"alId"}
				],
				columnDefs: [{
					"targets": 0,
					"visible":false
				},{
					targets: 0,
					render: function (data, type, row, meta) {
						$('[type="checkbox"][name="accbox"][data-box="' + data + '"]').attr("checked","checked");
						return data;
					}
				},{
					targets: 4,
					render: function (data, type, row, meta) {
						if(type === "display"){
							var  tdcon='<a class="Search_Button" data-row="' + data + '" href="javascript:;">查看</a>';
							return tdcon;
						}else{
							return data;
						}
					}
				},{
					targets: 5,
					render: function ( data, type, row, meta ) {				
						if(type === "display"){
							var  tdcon='<a class="del_btn btn btn-primary btn-sm btn-icon btn-circle" data-id="'+data+'" data-path="'+row.alPath+'"><i class="fa fa-times"></i></a>';
							return tdcon;
						}else{
							return data;
						}
					}
				}]
	       });
	   }
	};
	function deleteDone(t){
	    var data={};
		data.path=t.attr("data-path");	    
		data.alId=t.attr("data-id");
		$.ajax({
			url:'accessoryCollect/delAccessoryList',
			type:'post',
			data:data,
			success:function(data){
				if(data=="true"){
					$("[name='accbox']:checkbox").attr("checked",false);
						accessoryTable.ajax.reload();	  				
					}	
			    else{
			    	$("body").cgetPopup({
				    	title: "提示信息",
				    	content: "删除失败! <br>",
				    	accept: popClose2,
				    	chide: true,
				    	icon: "fa-exclamation-circle"
				    });  		    	
			    }
			}
		});
	}
</script>
<!-- ================== END PAGE LEVEL JS ================== -->