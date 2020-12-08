var recordListTable, projectCheckListTable,checkListData={},recordData={};
var projId=$('#projId').val();
checkListData.projId=projId;

var recordsIdArry = new Array();
var operate = 0;
var delRowData;
$("input").attr("required",true);
$("textarea").attr("required",true);  //页面上所有的input、textarea框必填
$("#suName").attr("required",false);  //因为有的部门没有监理公司，所以无需必填，清除必填
$(".drawDiv input").attr("required",false);  //文件上传无需必选
$("#inspectionResult").attr("required",false); //查验结果无需必填


console.info(2018,checkListData);
//$('.hiddenSignDate').addClass("hidden");
/**
 * 初始化信息，列表区列表
 */
var handleProjectCheckList = function() {
	"use strict";
	recordsIdArry.length=0;
	projectCheckListTable= $('#projectCheckListTable').on( 'init.dt',function(){
	$(this).bindDTSelected(trSelectedBack,true);
	$('#projectCheckListTable_filter input').attr('placeholder','施工工序');
	updateMonitor();
	//viewMonitor();
	//隐藏遮罩
	$("#projectCheckListTable").hideMask();
	//增加监听
	addMonitor();
	//查询监听
	searchMonitor();
	//完成监听
	completeMonitor();
	//推送监听
    send();
	//删除监听
	deleteMonitor();
	//重置监听
	resetMonitor();
	//加载报表
	showReport();
	queryCheckRole();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [ 15 ],
        dom: 'Bfrtip',
        bStateSave:true,
        buttons: [
            { text: '推送', className: 'btn-sm btn-query  business-tool-btn checkRole  sendBtns' },
            { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole hidden addBtn' },
            { text: '修改', className: 'btn-sm btn-query business-tool-btn  updateBtn hidden' },
            { text: '删除', className: 'btn-sm btn-warn  business-tool-btn checkRole  hidden deleteBtn' },
            { text: '重置', className: 'btn-sm btn-warn  business-tool-btn checkRole  hidden resetBtn' },
            // { text: '完成', className: 'btn-sm btn-query business-tool-btn checkRole  hidden completebtn' },
            //{ text: '详述', className: 'btn-sm btn-query business-tool-btn checkRole viewBtn' },
        ],
        serverSide: true, 
        ajax: {
           url: $("#actionName").val()+'/queryCheckList',
            type:'post',
            data: function(d){
               	$.each(checkListData,function(i,k){
               		d[i] = k;
               	});
               	
            },
            datasrc: 'data'
        },
      // ajax:'projectjs/inspection/json/trench_back_fill.json',
        responsive: {
        	details: {
        		renderer: function ( api, rowIdx, columns ){
        			return renderChild(api, rowIdx, columns);
        		}
        	}
        },
        select: true,
        columns: [
            {"data":"pcId",className:"none never"},       
			{"data":"inspectionDate"},
			{"data":"process"},
			{"data":"inspectionResult"},
			{"data":"flagDes"},
            {"data":"flag"},
		],
		order: [[ 0, "desc" ]],
		columnDefs: [{
			"targets":0,
			"visible":false
		},{
			"targets":4,
			 "orderable":false
		},{
            "targets":5,
            className:"none never",
            "createdCell": function (td, cellData, rowData, row, col) {
                if(cellData=='3'){
                    $(td).parent().children().css("background", "rgb(255, 219, 219)");
                    //$(td).attr("id",row);
                }
            }
		}],
		"fnDrawCallback": function (oSettings, json) {//绘的回调函数
			//addBtnCheck();
		},
		"fnInitComplete": function(oSettings, json){//初始化
			hiddenAddBtn();
			addBtnCheck();
		}
    });
};
/**
 * 列表区增加按钮权限
 */
var addBtnCheck = function(){
	var post=$("#loginPost").val();  //当前登录人职务-施工员、焊工、班组
	if(post.indexOf(',61,')>=0 || post.indexOf(',63,')>=0 || post.indexOf(',64,')>=0 || post.indexOf(',999,')>=0){
		$(".addBtn").removeClass("hidden");
	}else{
		$(".addBtn").addClass("hidden");

		
	}
}
/**
 * 增加隐藏监听
 */
var hiddenAddBtn = function () {
    $.ajax({
        type: 'POST',
        url: 'projectInspectList/findProStatus?projId='+projId,
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
        	if(data=="true"){
        		$(".addBtn").removeClass("hidden");
            	addBtnCheck();
			}else{
            //    $(".addBtn").addClass("hidden");
				$(".addBtn").removeClass("hidden");
            	addBtnCheck();
                
			}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("信息保存失败！");
        }
    });
};

/**
 * 列表区增加监听
 */
var addMonitor = function(){
	$(".addBtn").on("click",function(){
		//清空全局选中记录数组
		recordsIdArry.length=0;
		$("#recordsId").val("");
		operate=1;
		$('.auditEditBtn').removeClass('hidden');
		$("#auditTab").tab("show");
		$('#pcIdNew').val('');
		$('.addClear,#flag,#finishedDate,#finishedOpr,#resetDate,#resetReason,#isFinishSign').val('');
        $('.sysDate').val(getSysDate());//记录区日期
		console.info($('.sysDate').val());
		//清空签字
		$(".clear-sign").click();
		//$("#auditForm").toggleEditState(true);
		getSignStatusByPostId(getProjectInfo().post,"auditForm");
		if($(".drawDiv")){
			$("#drawName").val("");
			$(".hasVal").addClass("hidden");
			$(".noVal").removeClass("hidden");
		}
	});
}
/**
 * 列表区修改监听
 */
function updateMonitor(){
	$(".updateBtn").on("click",function(){
		//清空全局选中记录数组
		recordsIdArry.length=0;
		$("#recordsId").val("");
		var len=$("#projectCheckListTable").find("tr.selected").length;
		console.log(json);
		if(len>0){
			var data=$("#signForm").serializeJson();
			console.info("falg----"+data.flag);
        	if("2"==data.flag || "1"==data.flag ){
        		//审核中的 和已审核过的 不允许再修改
        		alertInfo("请选择未完成的报验单！");
        		return ;
        	}
			operate=2;
			var json=trSData.projectCheckListTable.json;
            console.log(json.flagDes);
			$('#pcIdNew').val(json.pcId);
			$('#auditForm .addClear').val('');
            getSignStatusByPostId(getProjectInfo().post,"auditForm");
			$(".auditEditBtn").removeClass("hidden");
			$("#auditTab").tab("show");
			$(".auditAddBtn2").removeClass("hidden");//隐藏新增按钮
		}else{
			$('#pcIdNew').val('');
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的报验单信息!',
				ahide:true,
				atext:'确定'
			});
		}
		
	});
}
/**
 * 报验列表删除按钮监听
 */
var deleteMonitor=function(){
	  $(".deleteBtn").on("click",function(){
		  var len=$("#projectCheckListTable").find("tr.selected").length;
			if(len>0){
			  $("body").cgetPopup({
					title: '提示',
					content: '点击删除后，该报验记录不可恢复，您确定要删除该报验吗？',
				    accept: delDone   //点击确认以后执行删除函数
			  });
			}else{
				alertInfo("请先选择要删除的报验记录！");
			}
	  })
};
//删除函数
var delDone = function(){
	recordsIdArry.length=0;
    var pcDesId= $("#pcDesId").val();
	$("#projectCheckListTable").cdeleteRow("pcId","electrodeRecord/deleteList/"+pcDesId,queryBack);
}
var queryBack=function(){   //执行函数以后的回调方法，加载列表区和报表
		showReport();	
}
//完成事件
var completeMonitor = function(){
	//完成事件
	  $(".completebtn").on("click",function(){
		  var len=$("#projectCheckListTable").find("tr.selected").length;
			if(len>0){
			  $("body").cgetPopup({
					title: '提示',
					content: '你确定完成该报验记录吗？',
				    accept: completeBack
			  });
			}else{
				alertInfo("请先选择要完成的记录！");
			}
	  })
}

var send = function () {
    //推送事件sendBtn
    $(".sendBtns").off("click").on("click",function(){
        var len=$("#projectCheckListTable").find("tr.selected").length;
        if(len>0){
        	var data=$("#signForm").serializeJson();
        	if("1"==data.flag || "2"==data.flag){
        		alertInfo("请选择未推送的报验单推送！");
        		return ;
        	}
            $("body").cgetPopup({
                title: '提示',
                content: '你确定推送该报验记录吗？',
                accept: completeBack
            });
        }else{
            alertInfo("请先选择要推送的记录！");
        }
    })
}

var completeBack = function(){
       $('#flag').val(1);
	   var dataJson=$("#signForm").serializeJson();
	 	var data={};
	 	data.pcId = dataJson.pcId;
	 	data.flag = 1;
		$.ajax({
            type: 'POST',
            url: $('#actionName').val()+'/completed',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
	               	var content = "数据保存成功！";
	               	if(data === "false"){
                        $('#flag').val(0);
	               	   content = "数据保存失败！";
	               	}else if(data === "0"){
	               		//未完成签字
                        $('#flag').val(0);
                        content = "未完成签字，不允许推送！请检查记录区和签字区是否完成签字！";
	               	}else{
                        $('#projectCheckListTable').cgetData(true,checkListCallBack);  //列表重新加载
					}
	               	var myoptions = {
	                       	title: "提示信息",
	                       	content: content,
	                       	accept: popClose,
	                       	chide: true,
	                       	icon: "fa-check-circle",
	                       	newpop:'new'
	                   }
           
            
                $("body").cgetPopup(myoptions);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("信息保存失败！");
            }
        });
}
//重置事件
var resetMonitor = function(){
	  $(".resetBtn").on("click",function(){
		  var len=$("#projectCheckListTable").find("tr.selected").length;
			if(len>0){
                $('body').cgetPopup({
                    title:"提示",
                    content: '#inspectionData/inspectionResetPopPage',
                    accept:resetBack
                });
			}else{
				alertInfo("请先选择要重置的记录！");
			}
	  })
}



var resetBack = function(){
	  $('#flag').val(0);
	  $("#projectCheckListTable").loadMask("重置中···",1,0.5);
	    var dataJson=$("#signForm").serializeJson();
	 	var data={};
	 	data.pcId = dataJson.pcId;
	 	data.flag = 0;
	 	var resetData = $("#resetForm").serializeJson();
    	data.resetReason = resetData.resetReason;
	 	$.ajax({
          type: 'POST',
          url: 'checkList/resetCheck',
          contentType: "application/json;charset=UTF-8",
          data: JSON.stringify(data),
          success: function (data) {
        	  $("#projectCheckListTable").hideMask();
	               	var content = "数据保存成功！";
	               	if(data === "false"){
	               	   content = "数据保存失败！";
	               	}else{
	               		$('#projectCheckListTable').cgetData(true,checkListCallBack);  //列表重新加载
	               	}
	               	var myoptions = {
	                       	title: "提示信息",
	                       	content: content,
	                       	accept: popClose,
	                       	chide: true,
	                       	icon: "fa-check-circle",
	                       	newpop:'new'
	                   }
         
          
              $("body").cgetPopup(myoptions);
          },
          error: function (jqXHR, textStatus, errorThrown) {
              console.warn("信息保存失败！");
          }
      });
}
/**
 *详述监听
 */
var viewMonitor = function(){
	$(".viewBtn").on("click",function(){
		var len=$("#projectCheckListTable").find("tr.selected").length;
		if(len>0){
			operate=3;
			var json=trSData.projectCheckListTable.json;
			$('#pcIdNew').val(json.pcId);
			//清空记录表单
			$('#auditForm .addClear').val('');
			$('#auditForm').toggleEditState(false);
			$(".auditEditBtn").addClass("hidden");
			$("#auditTab").tab("show");
			
			//施工或竣工中
        	/*if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#signForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#signForm").find(".sign-data-input").toggleSign(false);
        	}*/
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要修改的报验单信息!',
				ahide:true,
				atext:'确定'
			});
		}
		
	});
}

/**
 * 搜索监听
 */
var searchMonitor = function(){
	//基础条件查询添加监听
	$('#projectCheckListTable_filter input').on('change',function(){
		var process = $('#projectCheckListTable_filter input').val();
		var pcDesId=$('#pcDesId').val();
		var projId=$('#projId').val();
		checkListData = {};
		checkListData={'process':process,'pcDesId':pcDesId,'projId':projId};
		$('#projectCheckListTable').cgetData(true,checkListCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#projectCheckListTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

//列表区行点击
var trSelectedBack = function(v, i, index, t, json){
	$("#pcIdNew").val(json.pcId);
	$("#businessOrderId").val(json.pcId);
	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
	if($("#menuDes")){
		$("#menuDes").val(menuDesc);
	}
	if($("#menuId")){
		$("#menuId").val(getStepId());
	}
	var menuId = getStepId();
	t.cgetDetail("signForm",$("#actionName").val()+'/viewProjectCheckList?menuId='+menuId,'',queryBack);
}
//报验单详述回调
var queryBack=function(data){

	$("#insDate").val(data.inspectionDate);
	$("#status").val(data.flag);
    $("#sendDate").val(data.finishedDate);
	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
	if($("#menuDes")){
		$("#menuDes").val(menuDesc);
	}
	if($("#menuId")){
		$("#menuId").val(getStepId());
	}
	$("#flag1").val(data.flag);
	
	
	var post=$("#loginPost").val();  //当前登录人职务
	var builder=$("#builderPost").val(); //得到职务
	var sujgj=$("#sujgjPost").val();
	//已完成的报验
	if(data.flag==1){
		$(".updateBtn,.completebtn").addClass("hidden");
	}else{
		$(".updateBtn,.completebtn").removeClass("hidden");
		if(post.indexOf(builder)>=0 || post.indexOf(',999,')>=0){
			$(".completebtn").attr("disabled",false);
			completeMonitor();
		}else{
			$(".completebtn").attr("disabled",true);//不可点击
			$(".completebtn").off();
		}
	}
	
	if(post.indexOf(',999,')>=0 || post.indexOf(',93,')>=0){   //如果是管理员，显示删除按钮并隐藏增加按钮，否则隐藏删除按钮
		$(".deleteBtn").removeClass("hidden");
//		$(".addBtn").removeClass("hidden");
		if(data.flag==1){     //如果记录是完成状态，显示重置按钮，否则不显示重置按钮但显示完成按钮
			$(".resetBtn").removeClass("hidden");	
		}else{
			$(".completebtn").removeClass("hidden"); 
			$(".resetBtn").addClass("hidden");	 
		}	
	}else{
		$(".deleteBtn").addClass("hidden");	
	}
	
	showDrawFile();
	trSelOtherInfo(data);
	showReport();
	
}
//列表重新加载
var checkListCallBack =function(){
	var len = $('#projectCheckListTable').DataTable().ajax.json().data ? $('#projectCheckListTable').DataTable().ajax.json().data.length : $('#projectCheckListTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.addClear').val('');
		$('#pcIdNew').val('');
		$(".updateBtn,.completebtn").addClass("hidden");
	 }else{
		 var data = trSData.projectCheckListTable.json;
		//已完成的报验
		if(data.flag==1){
			$(".updateBtn,.completebtn").addClass("hidden");
		}else{
			$(".updateBtn,.completebtn").removeClass("hidden");
		}
	 }
	//清空签字
	$(".clear-sign").click();
	showReport();
}

/**
 * 记录区点击事件
 */
/*$("#auditTab").on("click",function(){
	$(".auditEditBtn").addClass("hidden");
	$('#auditForm').toggleEditState(false);
})*/

//签字区放弃功能
$(".giveupbtn").on("click",function(){
	if($("#pcId").val()==""){
		//返回列表区
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
		
		$('#projectCheckListTable').cgetData(true,checkListCallBack);
	}else{
		//返回列表区
		$('ul.nav-tabs>li.active').removeClass("active");
		$('#listTab').tab("show");
	}
});


//记录区添加页面记录
var rows=[];
var rowData=[];
$(".auditAddBtn").on("click",function(){
	var rowsPart=[];
	var t=$("#auditForm");
	//开启表单验证
		if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {   
		    //添加桩号
		    var pcId=$('#pcIdNew').val();
		    //清空记录id
		    $('.recordId').val('');
		    
			var json1=t.serializeJson();
			
			rowsPart.push(json1);
			//判断焊条编号是否重复
			/*var json=$("#recordListTable").DataTable().rows().data();
			for(var i=0;i<json.length;i++){
				for(var j=0;j<rowsPart.length;j++){
					if(json[i].electrodeNo==rowsPart[j].electrodeNo){
						$("body").cgetPopup({
							title: "提示信息",
		                  	content: "焊条编号重复!",
		                  	accept: electrodeNoRepeat,
		                  	chide: true,
		                  	icon: "fa-check-circle",
		                  	newpop: 'new'
		                }); 
						return false;
				    }
				}
			}*/
			//列表添加
		    recordListTable.rows.add(rowsPart).draw();
		   // $('#recordListTable').selectRow(0);
		    rows=$.merge(rows, rowsPart);
		    rowData=rows;
	        //如果通过验证, 则移除验证UI
	        t.parsley().validate();
	    } else {
	        //如果未通过验证, 则加载验证UI
	        t.parsley().validate();
	    };
	
});
var electrodeNoRepeat=function(){};
var inexistence=function(){};

//记录区保存功能
$(".auditSavebtn").on("click",function(){
	var dataObj={};
	//列表的值
	var json=$("#recordListTable").DataTable().rows().data();
	dataObj.list = [];
	//转成list
	$.each(json, function(k,v){
		v.projId = $("#projId").val();
		dataObj.list.push(v);
	})
	//传递 工程ID
	dataObj.projId = $("#projId").val();
	
	var id=$('#pcIdNew').val();
	dataObj.pcId=id;
	
	dataObj.recordsId = delRowData;
	//增加的记录
	var data=JSON.stringify(dataObj);
	dataObj.list = [];//清空了；
	
	 
    var response = $.ajax({
  	url: $("#actionName").val()+"/saveRecords",
  	type: "POST",
  	timeout : 5000,
  	contentType: "application/json;charset=UTF-8",
  	data: data,
  	success: function (data) {
  		if (data === "true") {
  			$("body").cgetPopup({
  				title: "提示信息",
                	content: "数据保存成功!",
                	accept: successBack,
                	chide: true,
                	icon: "fa-check-circle",
                	newpop: 'new'
               }); 
	        }else{
	        	$("body").cgetPopup({
                	title: "提示信息",
                	content: "数据保存失败, 请重试! <br>" + data,
                	accept: popClose,
                	chide: true,
                	icon: "fa-exclamation-circle",
                	newpop: 'new'
              });  
	        }
  	}
  });
});

/**记录区保存回调*/
function successBack(){
	//handleRecord();
	$('#recordListTable').cgetData(true,recordBack);
}

//checkbox选中值组装到recordsId

var handleCheckBox = function(){
	var checkboxs=$(".recordListTableCheckbox");
	var t = $(".recordTable").not(".hidden");
	if(t.length>0){
		checkboxs = $(".recordTable").not(".hidden").find(".recordListTableCheckbox");
	}
 	$.each(checkboxs,function(i,e){
 		if($(e).is(":checked")){
 			//全局数组中不存在记录ID，则push到全局数组中去
 			if($(e).val()!='' && recordsIdArry.indexOf($(e).val()) == -1){
 				//recordsId +=$(e).val()+",";
 				recordsIdArry.push($(e).val());
 			}
		}else{
			//全局数组中存在记录ID，现在不选择，则从全局数组中删除
 			if($(e).val()!='' && recordsIdArry.indexOf($(e).val()) != -1){
 				//recordsId +=$(e).val()+",";
 				//删除该元素
 				recordsIdArry.splice(recordsIdArry.indexOf($(e).val()),1);
 			}
		}
 		
   	});	
 	//var json = recordListTable
 	if(recordsIdArry.length>0){
 		$('.auditInpectBtn').removeClass('hidden');
 	}else{
 		$('.auditInpectBtn').addClass('hidden');
 	}
 	//join()把数组的所有元素放入一个字符串。元素通过指定的分隔符进行分隔。
 	$("#recordsId").val(recordsIdArry.join(","));
}

/**
 * 传递记录id和报验单Id
 * 返回复选框html
 * 已选中的记录组装到全局数组中
 */
var addCheckBox = function(recordId,pcId){
	var html='<input type="checkbox" class="recordListTableCheckbox"' ;
	if(recordId){
		html += 'value="'+recordId+'"'+'id="'+recordId+'"';
	}else{
		html += 'disabled="disabled"';
	}
	if(pcId){
		//html += 'disabled="disabled"';
		html += 'data="'+pcId+'"';
		html += 'checked="checked" ';
		//已选中的记录组装到全局数组中
		if(recordsIdArry.indexOf(recordId) == -1){
			recordsIdArry.push(recordId);
		}
	}
	html += ' />';
	return html;
}
/**
 * checkbox选中事件
 */
var checkboxMonitor = function(){
	$(".recordListTableCheckbox").on("click",function(){
		handleCheckBox();
	})
}
/**
 * 报验事件
 * 到签字区进行报验
 */
$(".auditInpectBtn").on("click",function(){
	
	$(".update-show").removeClass("hidden");
	//$('#signForm').toggleEditState(true);
	$("#signTab").tab("show");
	// $("#signForm").find(".sign-data-input").toggleSign(true);
    //根据职务过滤可编辑项
    getSignStatusByPostId(getProjectInfo().post,"signForm");
	if($("#pcIdNew").val()){
		//已有报验
		$("#pcId").val($("#pcIdNew").val());
	}else{
		//未报验
		$("#pcId").val('');
		//清空要增加的数据项
		$('.addClear').val('');
        $('#inspectionDate').val(getSysDate());
        console.info($('#inspectionDate').val());
		//清空签字
		//('.clear-sign').click();
	}
	if($(".drawDiv")){
		$(".searchButton").removeClass("hidden");
		//$(".Search_Button").removeClass("hidden");
		if($("#drawName").val()){
	 		 $(".hasVal").removeClass("hidden");
	 		 //显示附件删除按钮
	  		 $(".del_btn").removeClass("hidden");
	 		 $(".noVal").addClass("hidden");
	 		 $(".noVal+#filePreviews tr").remove();
	 	 }else{
	 		 $(".noVal").removeClass("hidden");

	 		 $(".hasVal").addClass("hidden");
	 	 }
	}
	

})


 //签字区保存功能
    $(".savebtn").on("click",function(){
    	saveSignInfo();
    	//$("#signForm").cformSave("projectCheckListTable",saveBack,false);
    });
    var saveSignInfo = function(){
    	if($("#signForm").parsley().isValid()){    //验证必填项是否已经填写，若未填写，则不提交表单
    		if($(".noVal+#filePreviews tr").length){
    			if($(".noVal+#filePreviews tr").length>1){
    				$("body").cgetPopup({
    					title: '提示',
	       				content: '一次只能上传一张简图或附件，请重新上传！',
    					accept:surplusAccept,
    					newpop:'second'
    				})
    			}else{
    				$("#width").val("600");
        			$("#height").val("400");
        		 	//序列化
        	       	var menuDesc = $('[data-mid="' + getStepId() + '"] span').text();
        	       	$("#menuDes").val(menuDesc);
        	       	$("#menuId").val(getStepId());
        	       	var dataJson=$("#signForm").serializeJson();
        		 	dataJson.pcId=$('#pcIdNew').val();
        	       	$("#result").val(JSON.stringify(dataJson));
        	       	$(".start").click();   
    			}
    		}else{
    		 	var dataJson=$("#signForm").serializeJson();

    		 	//判断是否已完成签字
                //验证必签签字是否已签
                var signtools = $("#signForm").find(".signature-tool.sign-all"),
                    stl = signtools.length,
                    sBlank = 0;
                for(var i=0; i<stl; i++){
                    var tstool = signtools.eq(i).next("div").find("a.btn-white"),
                        tsinput = tstool.siblings(".sign-data-input");
                    if(!tsinput.val() || tsinput.val().length < 312){
                        tstool.addClass("required-sign");
                        sBlank++;
                    }
                }
                if(sBlank){
                    console.info("未完成签字");
                    dataJson.isFinishSign = "0";
                }else{
                    console.info("已完成签字");
                    dataJson.isFinishSign = "1";
				}
                if(dataJson.flag=='1'){
                	alertInfo("报验单状态为已完成！保存无效");
                	return;
                }else if(dataJson.flag==''){
                    $("#flag").val('0');
                    dataJson.flag='0';
                }
    		 	dataJson.pcId=$('#pcIdNew').val();
    		 	var data={};
    		 	data = dataJson;
    		 	$("#signForm").parent().parent().loadMask("正在保存。。。",1,0.5);
    			$.ajax({
						beforeSend: function(){
							$('.update-show').addClass("hidden");
						},
    	               type: 'POST',
    	               url: $('#actionName').val()+'/saveSign',
    	               contentType: "application/json;charset=UTF-8",
    	               data: JSON.stringify(data),
    	               success: function (data) {
    	            	   $("#signForm").parent().parent().hideMask();
	    	               	var content = "数据保存成功！";
	    	               	if(data === "false"){
                                $('.update-show').removeClass("hidden");
	    	               	   content = "数据保存失败！";
	    	               	}else if(data === "already"){
	    	               		content = "此信息已被修改，请刷新页面！";
	    	               	}else{
	    	               		$("#pcId").val(data);
	    	               		$("#pcIdNew").val(data);
	    	               	}
	    	               	var myoptions = {
	    	                       	title: "提示信息",
	    	                       	content: content,
	    	                       	accept: savedone,
	    	                       	chide: true,
	    	                       	icon: "fa-check-circle",
	    	                       	newpop:'new'
	    	                   }
    	              
    	               
    	                   $("body").cgetPopup(myoptions);
    	               },
    	               error: function (jqXHR, textStatus, errorThrown) {
                           $('.update-show').removeClass("hidden");
    	                   console.warn("信息保存失败！");
    	               }
    	           });
    		}
    	}else{
        	//如果未通过验证, 则加载验证UI
        	$("#signForm").parsley().validate();
        }
    }
    // 附件保存回调1
    function saveBack(data){
        var content = "数据保存成功！";
        if(data.result === "false"){
            content = "数据保存失败！";
        }else if(data.result === "already"){
       		content = "此信息已被修改，请刷新页面！";
       	}else {
            $("#pcId").val(data.operateId);
            $("#pcIdNew").val(data.operateId);
            $("#drawUrl").val(data.drawUrl);
        }
        var myoptions = {
            title: "提示信息",
            content: content,
            accept: savedone,
            chide: true,
            icon: "fa-check-circle"
        }
        $("body").cgetPopup(myoptions);
    }

    //附件保存回调2
    var savedone = function(){
     	$('#signForm').toggleEditState(false);
     	if($(".drawDiv")){
     		$(".searchButton").attr("href","/accessoryCollect/openCoFile?busRecordId="+$("#pcId").val());
     		 if($("#drawName").val()){
           		 $(".hasVal").removeClass("hidden");
           		 $(".del_btn").addClass("hidden");
           		 $(".noVal").addClass("hidden");
           		 $(".noVal+#filePreviews tr").remove();
           	 }else{
           		 $(".noVal").removeClass("hidden");
           		 
           		 $(".hasVal").addClass("hidden");
           	 }
     	}
        var projId = $("#projId").val(); 
		if(_inApk && $(".attach-images-list").find(".new-image").length){
			preImagesUpload($(".attach-images-list .uploadBtn"), projId, '', getStepId(), $("#pcId").val());
		};
        showReport();
        sureClose();
    }
    
    /**
     * 记录区详述回调2
     */
  var recordBack = function(){
  	if(operate==2 || operate ==1){
  		$('.auditEditBtn').removeClass('hidden');
  		//$("#auditForm").toggleEditState(true);
  		getSignStatusByPostId(getProjectInfo().post,"auditForm");
  		if(operate==2 && $(".selectType").val()!="undefined"){
  			//报验单类型选择框不可编辑
  			$(".selectType").attr("disabled", "disabled");
 		}
  	}else{
  		$('.auditEditBtn').addClass('hidden');
  		$("#auditForm").toggleEditState(false);
  	}
  	checkboxMonitor();
  	handleCheckBox();
  }
  
  /**
   * 记录区选中行事件
   */
  var trSelectedBackRecord = function(v, i, index, t, json){
  	//查询记录详述 
  	t.cgetDetail("auditForm",$("#actionName").val()+'/viewRecordById','',queryBackRecord);
  
  }
  //记录区选中行事件回调1
  var queryBackRecord = function(data){
  	//$('#erId1').val(data.erId);
  	//$('#pcId1').val(data.pcId);
  	showRecordPhoto();
  	if(operate==2){
		//隐藏新增按钮
		$(".auditAddBtn2").removeClass("hidden");
	}else{
		$(".auditAddBtn2").removeClass("hidden");
	}
  	recordBack();
  	/*if(data.pcId==null || data.pcId==''){//不存在报验单
		//alert("1====="+operate);
		$(".update-show").removeClass("hidden");
		$('.auditEditBtn').removeClass('hidden');
		$("#auditForm").toggleEditState(true);
		getSignStatusByPostId(getProjectInfo().post,"auditForm");
	}*/
  }
  
  /**
   * 记录区新增事件
  */
  $('.auditAddBtn2').on("click",function(){
	  	//清空录入框
		$("#auditForm .addClear").val('');
		$("#auditForm .clear-sign").click();
		$("#recordListTable").clearSelected();//去掉选中
      	$('.sysDate').val(getSysDate());
		showRecordPhoto();
		//$("#auditForm").formReset();//清空记录区
  })
  /**
   * 页面附件或者缩略图显示
   */
  var showDrawFile = function(){
	  if($(".drawDiv")){
		    $(".searchButton").attr("href","/accessoryCollect/openCoFile?busRecordId="+$("#pcId").val());
			//$(".searchButton").removeClass("hidden");
			$(".searchButton").removeClass("hidden");
			//$(".Search_Button").removeClass("hidden");
			if($("#drawName").val()){
		  		 $(".hasVal").removeClass("hidden");
		  		 //隐藏附件删除按钮
		  		 $(".del_btn").addClass("hidden");
		  		 $(".noVal").addClass("hidden");
		  		 $(".noVal+#filePreviews tr").remove();
		  	 }else{
		  		 $(".noVal").removeClass("hidden");
		  		 
		  		 $(".hasVal").addClass("hidden");
		  	 }
		}
  }
 
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
	//删除回调
  var deleteDone=function(){
	  	$("#drawName").val("");
		$(".hasVal").addClass("hidden");
		$(".noVal").removeClass("hidden");
  }
  
  var sureClose=function(){
	  var cwId=$("#pcId").val();
		$.ajax({
			type:'post',
			url:$('#actionName').val()+'/saveSignNotice',
			contentType: "application/json;charset=UTF-8",
	        data: cwId,
	        success:function(data){
	        	console.info(data);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            console.warn("数据保存失败！");
	        }
		})
	  
  }

  //判断是否为新报验单
  function isUpdate(){
      var date = $("#insDate").val();
      var status = $("#status").val();
      if(date !=''){
          if(Date.parse(date)>Date.parse($("#upgradeDate").val())){
              return true;
          }else{
              if("1"!=status){
                  return true;
              }else{
                  var sendDate = $("#sendDate").val();//完成时间
                  var str= sendDate.split(",");
                  if(Date.parse(str[str.length-2])>Date.parse($("#upgradeDate").val())){
                      return true;
                  }
              }
          }
      }
      return false;
  }


  