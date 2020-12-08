<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editBtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 addBtn" >增加</a>
	</div>
	<div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 modifySaveBtn" >保存</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="clearboth form-box">
		<form id="projModifyForm" data-parsley-validate="true" class="form-horizontal" action="projInfoModify/saveModify">
			<input type="hidden" id="projId" name="projId"/>
			<input type="hidden" id="modifyId" name="modifyId"/>
			<div class="form-group col-md-12">
		     	<label class="control-label" for="modifyDes">更正描述</label>
		     	<div> 
		        	<textarea class="form-control  field-editable" name="modifyDes" id="modifyDes" rows="4" cols="" data-parsley-maxlength="200"  data-parsley-required="true"></textarea>
	        	</div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="proposeDate">提出日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm  field-editable datepicker-default  " id="proposeDate"  name="proposeDate" value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="proposer">提出人</label>
		        <div>
		        	<input type="hidden"  id="proposerId"  name="proposerId" data-parsley-maxlength="100" value="${loginInfo.staffId}">
		           <input type="text" class=" form-control input-sm field-not-editable" id="proposer"  name="proposer" data-parsley-maxlength="20" value="${loginInfo.staffName}">
		        </div>
		    </div>
       	</form>
	</div>
	<div class="p-t-6 p-b-15 p-l-10">
		<table id="attachModifyTable" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
     			<th></th>
           		<th>提出人</th>
           		<th>提出日期</th>
            	<th>更正描述</th>
           	</tr>
       	</thead>
	</table>
	</div>
	
	
</div>

<script>
	App.restartGlobalFunction();
	//隐藏loading
	$(".infodetails").hideMask();
	
	/* $.getScript('projectjs/accept/modify-pop.js').done(function () {
		modifyPop.init();
	}); */
	$("#projModifyForm").toggleEditState(false).styleFit();
	 $('.datepicker-default').datepicker({
	        todayHighlight: true
	    });
	
	//保存
	$(".modifySaveBtn").on("click",function(){
		$("#projModifyForm").cformSave('',saveModifyBack,false);
	});
	
	$(".editbtn").addClass('hidden'); 
	
	var saveModifyBack=function(data){
		var content = "保存成功！";
		if(data === "false"){
			content = "保存失败！";
		}
		var myoptions = {
	          	title: "提示信息",
	          	content: content,
	          	accept: reload,
	          	newpop:'second',
	          	chide: true,
	          	icon: "fa-check-circle"
	    }
	    $("body").cgetPopup(myoptions);
	}
	
	/**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	popClose();
    });
	
    //当前日期
    $("#proposeDate").change();
    
    var reload=function(){
    	$(".editBtn").removeClass('hidden');
		$(".editbtn").addClass('hidden');
    	var searchData={};
    	searchData.projId=$("#projId").val();
    	$('#attachModifyTable').cgetData(true);
    	
    }
    var back=function(){
    	$(".editBtn").removeClass('hidden');
		$(".editbtn").addClass('hidden');
    }
  /*   var detail=function(){
    	var projId=$("#projId").val();
    	$.ajax({
    		type:"post",
    		url:"projInfoModify/viewProjModify",
    		contentType: "application/json;charset=UTF-8",
    		data:projId,
    		success:function(data){
    			console.info(data);
    			$("#projModifyForm").deserialize(data);
    		},
    		error:function(){
    			alert('查询失败');
    		}
    	})
    }(); */
    
    $(".addBtn").off().on("click",function(){
		$(".editbtn").removeClass('hidden');
		$(".editBtn").addClass('hidden');
		$('#modifyId').val('');
		$("#modifyDes").val("");
		$("#proposeDate").val("");
		$("#projModifyForm").toggleEditState(true);
		
	})
    
    var handleModifyPop = function() {
	'use strict';
	searchData.projId=$("#projId").val();
	console.info("projId1-----"+$("#projId1").val());
	var projId2= $('.modal-body [name="projId"]').val();
	console.info("projId2-----"+projId2);
	if ($('#attachModifyTable').length !== 0) {
		attachModifyTable= $('#attachModifyTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			addBtnMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'rtip',
            buttons: [
                 /* { text: '增加', className: 'btn-sm btn-query business-tool-btn  addBtn' },*/
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'projInfoModify/queryProModify',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/accept/json/attach.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"modifyId",className:"none never"}, 
	  			{"data":"proposer"}, 
				{"data":"proposeDate"},
				{"data":"modifyDes"},
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":3,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 20, 	//截取多少字符（或汉字）
					end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
	}	
}


var trSelectedBack = function(v, i, index, t, json){
	t.cgetDetail('projModifyForm','','',scaleDetailRefresh);
}
var scaleDetailRefresh=function(){
	$(".editBtn").removeClass('hidden');
	$(".editbtn").addClass('hidden');
}

var addBtnMonitor=function(){
	
}
</script>