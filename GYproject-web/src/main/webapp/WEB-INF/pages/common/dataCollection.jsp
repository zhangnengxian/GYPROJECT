<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="importtool">

	 <input type="hidden" class="loginCorpId" value="${loginName.corpId}" />
	<form id="fileupload" action="accessoryCollect/uploadFile" method="POST" enctype="multipart/form-data">
	    <input type="hidden" name="alPath" id="alPath" value="0104"/>
	    <input type="hidden" name="encryption" id="encryption" />
	    <input type="hidden" class="corpId" value="${loginName.corpId}" />
	    <input type="hidden" name="caiId" id="caiId" />
	    <input type="hidden" name="stepId" id="stepId" />
	     <input type="hidden" name="step" id="step" />
	    <input type="hidden" name="projId" id="projId" value="0103"/>
	    <input type="hidden" name="projNo" id="projNo" value="0103"/>
	    <input type="hidden" name="projStatusId" id="projStatusId" value=""/>
	    <input type="hidden" name="projStatu" id="projStatu" value="${alreadyCompleted }"/>
	    <input type="hidden" name="projLtypeId" id="projLtypeId" value="0103"/>
	   <input type="hidden" id="loginId" name="loginId" value="${loginName.staffId}"/>
	   <input type="hidden" id="busRecordId" name="busRecordId"/>
	   <input type="hidden" id="sourceType" name="sourceType"/>
	   
	   <input type="hidden" id="aspectRatio" value="1.25" />
		<div class="fileupload-buttonbar">
	        <div class="pull-right toolBtn">
		        <a id="projPicture" class="meterModelBtn btn btn-default btn-sm m-l-10 " title="上传"><i class="fa"></i> 设置工程图片</a> 
	           <!--  <a id="accPhoto" class="btn btn-default btn-sm m-l-10 " title="拍照"><i class="fa fa-camera"></i> 拍照</a>  -->
	            <span class="btn btn-success btn-sm fileinput-button">
	                <i class="fa fa-plus"></i>
	                <span>浏览文件...</span>
	                <input type="file" name="files[]" multiple/>	             	          
	            </span>
	            <button type="submit" class="btn btn-primary btn-sm start hidden">
                   <!--  <i class="fa fa-upload"></i>
                    <span>上传</span> -->
                </button>
                <button type="button" class="btn btn-primary btn-sm upload-btn">
                    <i class="fa fa-upload"></i>
                    <span>上传</span>
                </button>
               <!--  <a href='accessoryCollect1/T-2016081202/www/ceshi1.pdf'>查看</a> -->
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
	<table id="dataPopTableFirst" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
           		<th>应收资料名称</th>
            	<th></th>
           	</tr>
       	</thead>
	</table>
    <h4 class="p-t-10"><strong>已收集资料清单</strong></h4>
	<table id="dataPopTableSecond" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
     			<th></th>
     		    <th></th>
           		<th>资料名称</th>
           		<th>资料类型</th>
            	<th>签收日期</th>
            	<th>签收人</th>
            	<th width='40'>操作</th>
           	</tr>
       	</thead>
	</table>
</div> 
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script id="template-upload" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-upload fade">
            <td class="col-md-1 hidden">
                <span class="preview"></span>
            </td>
            <td width="60%">
                <p class="name filename">{%=file.name%}</p>
                <strong class="error text-danger"></strong>
            </td>
            <td width="20%">
                <p class="size">Processing...</p>
            </td>
            <td width="20%">
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
            <td class="hidden">
                <span class="preview">
                    {% if (file.thumbnailUrl) { %}
                        <!--<a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>-->
                    {% } %}
                </span>
            </td>
            <td width="60%">
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
            <td width="20%">
                <span class="size">{%=o.formatFileSize(file.size)%}</span>
            </td>
            <td width="20%">
                <div class="progress progress-striped text-center"><div class="progress-bar progress-bar-success" style="width:100%;">已上传</div></div>
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
    $.getScript('projectjs/data/form-multiple-upload.demo.js?'+Math.random()).done(function() {
    	FormMultipleUpload.init();
    });
    var dataPopTable;
    var accessoryTable;
    $("#accPhoto").off("click").on("click",function(){
		var url = '#projectConfirm/projPhoto';
		var popoptions = {
			title: '拍照',
			content: url,
			accept: projPhotoDone1,
			chide: true,
			size:'super'
		};
		$("body").cgetPopup(popoptions);
	});
    $("#projPicture").on("click",function(){
		var url = '#accessoryCollect/projPicture';
		var popoptions = {
			title: '工程图片',
			content: url,
			accept: pictureDone,
			callback: function(){
				$('body').one('innerpopclosed.pop', function (e) {
					accessoryTable.ajax.reload();
			    });
			},
			chide: true,
			atext: '关闭',
			size:'super'
		};
		$("body").cgetPopup(popoptions);
	});
    function pictureDone(){
    	
    }
    function projPhotoDone1(){
    	 if(_isIE){
    		 Unload();
  	     }
    	 $("#dataPopTableSecond").DataTable().ajax.reload(function(){
 			$("#dataPopTableSecond").trigger("reload.dt");
 		});
    	
    }
   
	/**
	 * 初始化资料收集(资料标准，标准id和附件的附件项caiId对应)
	 */
	var firstdatainit= function() {
		"use strict";
		var data={};
		data.projId = $('.modal-body [name="projId"]').val();
		var corpId = $('.loginCorpId').val();
		if(corpId==''){
			data.corpId ='1101';
		}else{
			data.corpId = corpId;
		}
		data.projNo = $('.modal-body [name="projNo"]').val();
		data.projStatusDes = $('.modal-body [name="projStatusDes"]').val();
		//data.projLtypeId = $('.modal-body [name="projectType"]').val();
		data.projLtypeId=$("#projLtypeId").val();
		console.info("flw----");
		console.info(data);
	    if ($('#dataPopTableFirst').length !== 0) {
	    	dataPopTable= $('#dataPopTableFirst').on( 'init.dt',function(){
	   		//默认选中第一行
	  		//$(this).bindDTSelected(trSelectedBack,true);
	    	//seconddatainit();
	    	$("[name='accbox']:checkbox").on('click', function(){  
	    	    var caiId=$(this).attr("data-box");  
               if($("#caiId").val()==""){
            	   $("#caiId").val(caiId);
            	 
               }else{
            	   if($("#caiId").val()==caiId){
            		   $("#caiId").val("")
            	   }else{
            	   $("body").cgetPopup({
				    	title: "提示信息",
				    	content: "已选择标准，一次不能选择多项标准! <br>",
				    	accept: failClose,
				    	chide: true,
				    	icon: "fa-exclamation-circle"
				    });  
            	    $(this).removeAttr("checked");
            	   }
            	  
               }
	    	    
	    	});  
	    	
	    	$('#dataPopTableFirst').hideMask();
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
	    if ($('#dataPopTableSecond').length !== 0) {
	    	var accessoryData = {};
	    	accessoryData.projId = $('.modal-body [name="projId"]').val();
	    	//accessoryData.projNo = $('.modal-body [name="projNo"]').val();
	    	accessoryData.busRecordId = $('#busRecordId').val();
	    	accessoryData.sourceType = $("#sourceType").val();
	        accessoryTable = $('#dataPopTableSecond').on( 'draw.dt',function(){
		   	//默认选中第一行
		  	//$(this).bindDTSelected(trSelectedBack,true);
		    $('#dataPopTableSecond').hideMask();
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
	           	lengthMenu: [8],
	           	dom: 'Brtip',
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
	                {"data":"alOperateCsrId",className:"none never"},
		  			{"data":"alName",responsivePriority:2},
		  			{"data":"alTypeId",responsivePriority:5},
		  			{"data":"alOperateTime",responsivePriority:3},
		  			{"data":"alOperateCsr" ,responsivePriority:4},	  			
		  			{"data":"alId",responsivePriority: 1}
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
				} ,{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},{
					targets: 6,
					render: function (data, type, row, meta) {
						if(type === "display"){
							var type=row.alTypeId;
							console.info("type--"+type);
							var  tdcon='<a target="_blank" class="Search_Button btn btn-xs btn-primary" data-row="' + data + '" href="/accessoryCollect/openFile?id='+data+"&type="+type+'"><i class="fa fa-eye"></i> 查看</a>';
								if($("#loginId").val() === row.alOperateCsrId && $("#projStatusId").val()!==$("#projStatu").val()){
									var  tdcon1='<a class="m-l-5 del_btn btn btn-warn btn-xs" data-id="'+data+'" data-path="'+row.alPath+'"><i class="fa fa-times"></i> 删除</a>';
								}else{
									var  tdcon1 = '';
								}
							return tdcon+tdcon1;
						}else{
							return data;
						}
					}
				}/* ,{
					targets: 7,
					render: function ( data, type, row, meta ) {	
						if(type === "display"){
							if($("#loginId").val() === row.alOperateCsrId){
								var  tdcon='<a class="del_btn btn btn-warn btn-xs" data-id="'+data+'" data-path="'+row.alPath+'"><i class="fa fa-times"></i> 删除</a>';
							}else{
								var  tdcon = '';
							}
							return tdcon;
						}else{
							return data;
						}
					}
				} */]
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
				    	accept: failClose,
				    	chide: true,
				    	icon: "fa-exclamation-circle"
				    });  		    	
			    }
			}
		});
	}
	function failClose(){}
</script>
<!-- ================== END PAGE LEVEL JS ================== -->