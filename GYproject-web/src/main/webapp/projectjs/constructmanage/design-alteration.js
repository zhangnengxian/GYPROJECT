/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
/**查询条件*/
var searchData={menuId:getStepId()},
mcData = {},
materialdata = {},
materialListTable,
state=1,
materialData,
materialListTablePop,
rowData = {},
handleDesignAlteration = function() {
	searchData.projId = getProjectInfo().projId;
	searchData.changeType = "1";//施工变更
	"use strict";
    if ($('#designAlterationTable').length !== 0) {
    	$('#designAlterationTable').on( 'init.dt',function(){
    		//搜索
    		$('#designAlterationTable_filter input').attr('placeholder','变更编号');   
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
    		//隐藏遮罩
   			$('#designAlterationTable').hideMask();
   		    //增加监听
   	    	addMonitor();
   	    	//修改监听
   	    	modifyMonitor();
   	    	//查询监听
   	    	searchMonitor();
   	    	//推送监听
   	    	pushMonitor();
   	    	//废弃监听
   	    	discardMonitor();
   	    	queryCheckRole();
   	   //跳转链接
   			if (crossPageBus) {
   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   					//跳转后销毁对象
   				crossPageBus = null
   			}
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'fBrtip',
            buttons: [
                      { text: '增加', className: 'btn-sm btn-query business-tool-btn checkRole  addBtn hidden' },
                      { text: '修改', className: 'btn-sm btn-query business-tool-btn checkRole updateBtn' },
                      { text: '推送', className: 'btn-sm btn-query pushBtn hidden' },
                      { text: '废弃', className:'btn-sm btn-warn business-tool-btn  discardBtn hidden'},
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'designAlteration/queryDesignAlteration',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
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
                {"data":"cmId",className:"none never"},
      			{"data":"cmNo", responsivePriority: 1},
      			{"data":"changeTypeDes", responsivePriority: 2},
	  			{"data":"cmDate", responsivePriority: 4}, 
	  			{"data":"designChangeTypeDes", responsivePriority: 5},
	  			{"data":"cmState",
					className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("background", "rgb(255, 219, 219)");
							//$(td).attr("id",row);
						}
					}
				},
                {"data":"projId",className:"none never"},
                {"data":"projNo",className:"none never"}
	  			
			],
			/*隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉）*/
			columnDefs: [{
		             "targets": 0, 
		             "visible":false
			},{
	             "targets": 2, 
	             "orderable":false
				}
			,{
	             "targets": 4, 
	             "orderable":false
				}
			],
			 "fnInitComplete": function (oSettings, json) {
				//增加按钮权限
        		if($("#unitType").val()==$("#cuUnitType").val()){
        			$(".addBtn").removeClass("hidden");
        		}else{
        			$(".addBtn").addClass("hidden");
        		}
			}
        });
    }
},

//查询监听
searchMonitor=function(){
	//基础条件查询添加监听
	$('#designAlterationTable_filter input').on('change',function(){
		var cmNo = $('#designAlterationTable_filter input').val();
		searchData = {};
		searchData.cmNo = cmNo;
		searchData.projId = getProjectInfo().projId;
		searchData.changeType = "1";//施工变更
		$('#designAlterationTable').cgetData(true);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#designAlterationTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}

/** 选中行时，查询详述
 */
trSelectedBack = function(v, i, index, t, json){
	/*if(json.auditState==='3'){
		$(".pushBtn").removeClass("hidden");
	}else{
		$(".pushBtn").addClass("hidden");
	}*/
	if(json.designChangeType=="-1"){   //如果此条记录为废弃，隐藏修改按钮、废弃按钮、增加按钮
		$(".discardBtn").addClass("hidden");
	
	}else{
		$(".discardBtn").removeClass("hidden");
	}
	$("#cmId").val(json.cmId);
	//维护按钮
	$(".toolBtn").addClass("hidden");
	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
	
	trSData.t && trSData.t.cgetDetail('designAlterationForm', 'designAlteration/viewChangeManagement?menuDes='+menuDesc, '',rollback);
	//传false表示不可编辑
	$("#designAlterationForm").toggleEditState(false);

},

rollback = function(data){
		if($("#duPrincipal").val()!==""){
			$(".duAudit").removeClass("hidden");
		}else{
			$(".duAudit").addClass("hidden");
		}
	
		var type="";
	    
	    if($("#drawName").val()){
	    	var fileName=$("#drawName").val().split(".");
	    	var fileName1=fileName[1];
	    	console.info("后缀----"+fileName1);
	    }
		
		
	    $('#designChangeType').val(data.designChangeType);
	    $('#constructionUnit').val(getProjectInfo().cuName);
	 	$("#stepId").val(getStepId());
	    //$("#alPath").val($("#projNo").val()+"/"+$(".has-sub.active > a span").text());
	 	$("#alPath").val($("#projNo").val()+"/过程");
	    $(".searchButton").attr("href","/accessoryCollect/openFile?id="+$("#accListId").val())+"&type="+type;
	    
	    
	    
	    
	    if($('#designChangeType').val()=="2"){
	    	$(".cust_change").removeClass("hidden");
	    	$(".proj_change").addClass("hidden");
	    	$(".searchButton").removeClass("hidden");
	    	$(".Search_Button").addClass("hidden");
	    	$(".projChange").addClass("hidden");
	    }else if($('#designChangeType').val()=="1"){
	    	$(".cust_change").addClass("hidden");
	    	$(".proj_change").removeClass("hidden");
//	    	$(".Search_Button").removeClass("hidden");
//	    	$(".searchButton").addClass("hidden");
	    	$(".searchButton").removeClass("hidden");
	    	$(".Search_Button").addClass("hidden");
	    	$(".projChange").removeClass("hidden");
	    }
	    if($("#auditState").val()==='1'){
	    	$(".duInfo").removeClass("hidden");
	    }else{
	    	$(".duInfo").addClass("hidden");
	    }
	if(data.drawName){
		$(".hasVal").removeClass("hidden");
		$(".noVal").addClass("hidden");
		$(".noVal+#filePreviews tr").remove();
	}else{
		$(".hasVal").addClass("hidden");
		$(".noVal").removeClass("hidden");
	
	}
	showReport();
	var post=$("#loginPost").val();
	console.info("post1--"+post);
	var builder=$("#builderPost").val();//现场代表
	console.info("builder--"+builder);
	var sucse=$("#sucse").val();//总监
	console.info("post--"+post.indexOf(builder));
	console.info("designChangeType--"+$('#designChangeType').val());
	if($('#designChangeType').val()=="6"||$('#designChangeType').val()=="7" || $('#designChangeType').val()=="-1"){//审核中
		$(".updateBtn,.pushBtn").addClass("hidden");
	}else{
		//施工单位建立
		if(post.indexOf(sucse)>=0 || ($("#unitType").val()==$("#cuUnitType").val()) || data.materialFlag=='1'){
			//现场监理
			$(".pushBtn").addClass("hidden");
		}else{
			//施工单位、监理单位、现场代表已签字才能推送
			if(post.indexOf(builder)>=0 && 
					(data.cuPm!='' && data.cuPm!=null)&& 
					(data.suCes!='' && data.suCes!=null)&& 
					(data.custLeader!='' && data.custLeader!=null)){
				$(".pushBtn").removeClass("hidden");
			}
		}
		$(".updateBtn").removeClass("hidden");
	}
	 if($("#cancelStaffName").val()==""){   //当废弃申请人为空取当前登录人
    	 $("#cancelStaffName").val($("#loginName").val());
     }
     if($("#cancelDate").val()==""){    //废弃申请时间为空取当前系统时间
         var sysDate = timestamp($("#sysDate").val());
         $("#cancelDate").val(format(sysDate,"default"));
     }
     if($("#designChangeType").val()=="-1"){
    		$(".updateBtn,.pushBtn,.discardBtn").addClass("hidden");
    		$(".cancelRemark").removeClass("hidden"); 
			$("#cancelRemark").attr("readonly",false); 
			$(".cancelDate").removeClass("hidden");
			$(".cancelStaffName").removeClass("hidden");
     }else{
    	 $(".cancelRemark").addClass("hidden"); 
			$("#cancelRemark").attr("readonly",false); 
			$(".cancelDate").addClass("hidden");
			$(".cancelStaffName").addClass("hidden");
     }
},

//增加监听
addMonitor=function(){
	  $('.addBtn').off('click').on('click',function(){
		//移除选中
          $('#designAlterationTable tr.selected').removeClass("selected");
          $(".addClear").val('');//清空form表单录入信息
          $(".clear-sign").click();//清空签字
          $(".noVal").removeClass("hidden");
          $(".hasVal").addClass("hidden");
          $("#drawName").val("");
          $("#duPrincipal").val("");
          $("#cmId").val("");
          $("#cmNo").val("");
          $(".suCse").val("");
          $(".builder").val("");
          $('#AlterationInfo').tab('show');
          $("#designChangeTypeMark").val(""); //废弃标志
          $(".cancelRemark").addClass("hidden");
          $("#cancelRemark").attr("readonly", false);
          $(".cancelDate").addClass("hidden");
          $(".cancelStaffName").addClass("hidden");
          $(".toolBtn").removeClass("hidden");
          $(".duAudit").addClass("hidden");
        //根据职务过滤可编辑项
        getSignStatusByPostId(getProjectInfo().post,"designAlterationForm");
		$.ajax({
            type: 'POST',
            url: 'designAlteration/findByProjId',
            data: 'projId='+$("#projId").val(),
            dataType: 'json',
            success: function (data) {
            	//$("#cmDate").val(data.cmDate);
            	$("#cmDate").val(format(data.cmDate)); 
            	$("#cmAdvanceStaffId").val(data.cmAdvanceStaffId);
            	$("#cmAdvanceStaffName").val(data.cmAdvanceStaffName);
            	if(data.cmAdvanceUnit!='' &&data.cmAdvanceUnit!=null ){
            		$("#cmAdvanceUnit").val(data.cmAdvanceUnit);
            	}else{
            		$("#cmAdvanceUnit").val(getProjectInfo().cuName);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("当前系统时间获取失败");
            }
        });
		
		$("#designChangeType").change();
	});
},

//修改监听方法
modifyMonitor = function(){
	$(".updateBtn").off('click').on("click",function(){
		if($("#designAlterationTable").find("tr.selected").length>0){
			$('#AlterationInfo').tab('show');
			 $("#designChangeTypeMark").val(""); //废弃标志
			 $(".cancelRemark").addClass("hidden"); 
				$("#cancelRemark").attr("readonly",false); 
				$(".cancelDate").addClass("hidden");
				$(".cancelStaffName").addClass("hidden");
			//切换可编辑状态
			$("#designChangeType").addClass("field-not-editable");
			$("#designChangeType").removeClass("field-editable");
            //根据职务过滤可编辑项
            getSignStatusByPostId(getProjectInfo().post,"designAlterationForm");
			// $("#designAlterationForm").toggleEditState(true);
			//维护按钮显示出来
			$(".toolBtn").removeClass("hidden");
			
			/*//施工或竣工中
        	if(getProjectInfo().projStatusId=="1018" || getProjectInfo().projStatusId=="1019" || getProjectInfo().projStatusId=="1020" || getProjectInfo().projStatusId=="1022"|| getProjectInfo().projStatusId=="10221"){
        		//alert("施工或竣工中..."+getProjectInfo().projStatusId);
        		$("#designAlterationForm").find(".sign-data-input").toggleSign(true);
        	}else{
        		//结算中或资料交接中
        		//alert("结算中或资料交接中..."+getProjectInfo().projStatusId);
        		$("#designAlterationForm").find(".sign-data-input").toggleSign(false);
        	}
			*/
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
},
//废弃监听
 discardMonitor = function(){
	
	$(".discardBtn").off("click").on("click",function(){
		var len=$("#designAlterationTable").find("tr.selected").length;
		if(len>0){
			$("#flag").val("3");
			$('#designAlterationForm').toggleEditState(false);
			$("#AlterationInfo").tab("show");
		    $("#designChangeTypeMark").val("-1"); //废弃标志
			$(".cancelRemark").removeClass("hidden"); 
			$("#cancelRemark").attr("readonly",false); 
			$(".cancelDate").removeClass("hidden");
			$(".cancelStaffName").removeClass("hidden");
         
        	$(".toolBtn").removeClass("hidden");
		}else{
			$("body").cgetPopup({
				title: '提示',
				content: '请选择要废弃的变更信息!',
				ahide:true,
				atext:'确定'
			});
		}
	});
}
//推送方法
pushMonitor=function(){
	$('.pushBtn').off('click').on('click',function(){
		 $("#designChangeTypeMark").val(""); //废弃标志
		if($("#designAlterationTable").find("tr.selected").length>0){

			var signBlankCount=0;
            $('.allSign:not(.hidden)').children().find('.sign-data-input').each(function () {
                 if($(this).val()=="") signBlankCount++;
            });

			if (signBlankCount>0){
                alertInfo("未签完字，不允许推送!");
			} else {
				var myoptions = {
						title: "提示信息",
						content: "确认材料导入完毕，变更记录将推送设计院进行审核!",
						accept: pushDone,
						cancel:popClose,
						icon: "fa-check-circle"
				}
				$("body").cgetPopup(myoptions);
            }
		}else{
			alertInfo('请选择要推送的变更记录！');
		}
	});
},
pushDone = function(){
	var cmId = trSData.designAlterationTable.json.cmId;
	$.ajax({
        type: 'POST',
        url: 'designAlteration/pushDesignAlteration',
        data: 'cmId='+cmId,
        success: function (data) {
        	var content = "数据保存成功！";
				if(data === "false"){
					content = "数据保存失败！";
				}else if(data === "true"){
					$("#designAlterationTable").cgetData(true);  //列表重新加载	            
				}
				var myoptions = {
               	title: "提示信息",
               	content: content,
               	accept: popClose,
               	chide: true,
               	newpop:'new',
               	icon: "fa-check-circle"
           }
           $("body").cgetPopup(myoptions);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("推送失败");
        }
    });
};
//切换到变更记录
$('#AlterationInfo').on("show.bs.tab", function(){
	if($("#designAlterationTable").find("tr.selected").length>0){
		$(".toolBtn").addClass("hidden");
		/*var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
		trSData.t && trSData.t.cgetDetail('designAlterationForm', 'designAlteration/viewChangeManagement?menuDes='+menuDesc, '',rollback);*/
		
	}else{
		$('#designAlterationForm').formReset();
		$('#designAlterationForm').deserialize(getProjectInfo());
		$("#designChangeType").addClass("field-editable");
		$("#designChangeType").removeClass("field-not-editable");
		
		$('#constructionUnit').val(getProjectInfo().cuName);
	}
	$(".toolBtn").addClass("hidden");
	$("#designAlterationForm").toggleEditState(false);
	$("#designChangeType").change();
});

//切换到变更列表
$('#AlterationTab').on("show.bs.tab", function(){
	$("#designAlterationTable").cgetData();  //列表重新加载
});
/**切换到材料变更*/
$("#materialChangeTab").on("shown.bs.tab",function(){
	postLimit();
	if($("#cmId").val() || $("#state").val()){
		state=2;
	}
	if((jsonLength(trSData.designAlterationTable))){
		if(!$.fn.DataTable.isDataTable('#materialListTable')){
			handleMChangeList();
		}else{
			 mcData.mcType=$("#mcType").val();
			 mcData.cmId=$("#cmId").val();
			 mcData.projId=$("#projId").val();
			$("#materialListTable").cgetData(true,mcBack("mcBtn"));
		}
	}else{
		 mcData.mcType="-1";	
 	     mcData.cmId="-1";	 
 	     handleMChangeList();
 	     btnHidden("mcBtn");
	}

});
/**
 * 材料变更(下)
 */
var handleMChangeList = function() {
	"use strict";
	if((jsonLength(trSData.designAlterationTable))){
		mcData.mcType=$("#mcType").val();	
	    mcData.cmId=$("#cmId").val();
	    mcData.projId=$("#projId").val();
	}else{
		 mcData.mcType="-1";	
 	     mcData.cmId="-1";	 
	}
	  
	    console.info(mcData);
	if ($('#materialListTable').length !== 0) {
		if (!$.fn.DataTable.isDataTable('#materialListTable')) {
	        materialListTable= $('#materialListTable').on( 'init.dt',function(){
		    	//默认选中第一行
	    		$(this).bindDTSelected("",false);
	    		rowData=materialListTable.data();
	    		//console.info("-++++---");
	    		//console.info(rowData);
		    	//隐藏遮罩
		    	$("#materialListTable").hideMask();
		    	saveMaterialList();
		    	importMaterial();
		    	checkOut();
		    	btnHidden("mcBtn");
		        mcSelBtn();
		        $(this).bindInputsChange();
		        queryCheckRole();
		        materialEditLimits();
		        postLimit();
		    }).DataTable({
		    	language: language_CN,
		        lengthMenu: [ 18 ],
		        dom: 'Brtip',
		        buttons: [
		            { text: '下载模板', className: 'btn-sm btn-query checkRole checkOut postLimit'},
		            { text: '导入', className: 'btn-sm btn-query business-tool-btn materialImportBtn checkRole mcBtn postLimit' },
	                { text: '调整', className: 'btn-sm btn-query mcSelBtn checkRole mcBtn postLimit' },
		            { text: '保存', className: 'btn-sm btn-query mcSaveBtn checkRole mcBtn postLimit'}
		        ],
		        //serverSide: true, 
				ajax: {
				    url: 'changeRecord/queryMCList',
				    contenttype:"application/json;charset=utf-8",
				    data:function(d){
				        $.each(mcData,function(i,k){
				            d[i] = k;
				        }); 	
				    },
				    datasrc: ''
				},
		        columns: [
		            {"data":"mcId", className:"none never"},
		  			{"data":"materialName",responsivePriority: 1},
		  			{"data":"materialSpec",responsivePriority: 4},
					{"data":"materialUnit",responsivePriority: 3},
					{"data":"flagDes"},
					{"data":"adjustQuantity", "className": "text-right",responsivePriority: 2}
				],
				order: [[ 0, "asc" ]],
				columnDefs: [{
					targets: 0,
					"visible":false
				},{
					targets: 5,
					render:function(data,type,row,meta){
						if(type === "display"){
							var tdcon = '<div class="tdInnerInput">';
						        tdcon += '<input class="form-control input-sm text-right" data-parsley-type="number" value=' + data + '>';						 					    
						        tdcon += '</div>';
						        return tdcon;
						}else{
							return data;
						}
					}
				}],
				responsive: {
	            	details: {
	            		renderer: function ( api, rowIdx, columns ){
	            			return renderChild(api, rowIdx, columns);
	            		}
	            	}
	            }
		    }).on("draw.dt",function(){
		    	$("#materialListForm").toggleEditState(false).styleFit();
		    });
		}else{
			materialListTable.ajax.reload();
		}
		$('#materialListTable').on("draw.dt", function(){
			rowData=materialListTable.data();
		});
	}
}
var saveMaterialList = function(){	
	$(".mcSaveBtn").on("click",function(){
		var t = $('#materialListTable');
		if(t.checkInputs()){
			var data = t.getInputsData();
			if(data.length || materialData != null){
				resultData=[];
				for(var i=0;i<data.length;i++){
		       		var datam = data[i];
					if(datam.adjustQuantity!==""){
						datam.cmId=$("#cmId").val();
						datam.mcType=$("#mcType").val();
		   				resultData.push(datam);
		   			}
				  }
				if(materialData != null){
					for(var i=0;i<materialData.result.materialList.length;i++){
						materialData.result.materialList[i].cmId=$("#cmId").val();
						resultData.push(materialData.result.materialList[i]);
					}
				}
	       		$.ajax({
	       			type: 'POST',
	       			url: 'changeRecord/saveMaterialChange',
	       			contentType: "application/json;charset=UTF-8",
	       			data: JSON.stringify(resultData),
	       			success: function (data) {
	       				var content = "数据保存成功！";
	       				if(data === "false"){
	       					content = "数据保存失败！";
	       				}else if(data === "true"){
	       					mcData.mcType=$("#mcType").val();	
	       				    mcData.cmId=$("#cmId").val();
	       				    mcData.projId=$("#projId").val();
	       					$("#materialListTable").cgetData(false);  //列表重新加载	            
	       				}
	       				var myoptions = {
	                       	title: "提示信息",
	                       	content: content,
	                       	accept: popClose,
	                       	chide: true,
	                       	icon: "fa-check-circle"
	                   }
	                   $("body").cgetPopup(myoptions);
	               },
	               error: function (jqXHR, textStatus, errorThrown) {
	                   console.warn("材料清单保存失败！");
	               }
	            });
			}else{
				alertInfo("无更新记录！");
			}
		}else{
			console.info("表单校验失败，请检查并修改未通过项目！");
		}
	});
}
//材料导入
var importMaterial=function(){
	$(".materialImportBtn").off("click").on("click",function(){
		$("#projId1").val($("#projId").val());
		$("body").cgetPopup({
			title: '文件导入',
			content: "#officialDrawing/importPop?url=changeRecord/importExcel",
			accept: importM,
			nocache: true
		});
	});
}
var importM = function(){
	var mcData={};
	mcData.mcType=$("#mcType").val();	
	mcData.cmId=$("#cmId").val();
	mcData.projId=$("#cmId").val();
	$("#materialListTable").cgetData();
}
var checkOut = function(){
	$(".checkOut").on("click",function(){
		console.info("xiazai");
		$("#exportExcel").submit();
	})
}
//调整
function mcSelBtn(){
	$(".mcSelBtn").on("click",function(){
			materialdata={};
			var url = "#changeRecord/materialList";
			$("body").cgetPopup({
				title: '材料列表',
				content: url,
				accept: mcDone,
				size:"large"
			});
		
	});
	}
function btnHidden(cla){
	 if(state==1){
		 //console.info("cla==="+cla);
       	$("."+cla).addClass("hidden");
       }else{
       	 //console.info("clah==="+cla);
       	$("."+cla).removeClass("hidden");
 }
	 postLimit();
}
function mcBack(cla){
	btnHidden(cla);
	//console.info("===---Back---");	
	rowData=materialListTable.data();
	//console.info(rowData);
}

//调整点击确定
function mcDone(){
	var jsons=trSData.materialListTablePop.jsons;
	//console.info("--------------");
	//console.info(rowData);
    if(jsons!=undefined){
		for(var i=0;i<rowData.length;i++){
			for(var j=0;j<jsons.length;j++){
				if(jsons[j].materialId == rowData[i].materialId){
					jsons.splice(j,1);
					break;
				}
			}
		}

		for(var i=0;i<jsons.length;i++){
			jsons[i].mcType=$("#mcType").val();
			jsons[i].cmId=$("#cmId").val();
			jsons[i].mcId="";
			jsons[i].adjustQuantity='';	
		}
		rowData=rowData.concat(jsons);
		materialListTable.rows.add(jsons).draw();
    }
}
/**
 * 材料变更(上)
 */


//弹出i屏
var rowIndex=[];
var handleMaterialList = function() {
	"use strict";

	if($("#projId").val()==""){
    	materialdata.projId=-1;	
    }else{
    	materialdata.projId=$("#projId").val();
    }
	  
	if ($('#materialListTablePop').length !== 0) {
		rowIndex=[];
	    materialListTablePop= $('#materialListTablePop').on( 'init.dt',function(){
	    	//默认选中第一行
    		$(this).bindDTSelected(trCmSelectedBack,false);
	    	//隐藏遮罩
	    	$("#materialListTablePop").hideMask();
	    	//搜索
    		$('#materialListTablePop_filter input').attr('placeholder','设备材料汇总表'); 
    		materialListSearchData();
	    	//console.info(".....");
	    	//console.info(rowIndex);
	        for(var m=0;m<rowIndex.length;m++){
	        	materialListTablePop.row(rowIndex[m]).nodes().to$().addClass("selected");
	        }
	    	
	    }).DataTable({
	    	language: language_CN,
	        lengthMenu: [ 18 ],
	        dom: 'fBrtip',
	        buttons: [
                
	           
	        ],
	        serverSide: true, 
			ajax: {
			    url: 'changeRecord/queryMaterialList',
			    contenttype:"application/json;charset=utf-8",
			    data:function(d){
			        $.each(materialdata,function(i,k){
			            d[i] = k;
			        }); 	
			    },
			    datasrc: 'data'
			},
			select: true,  //支持多选
	        columns: [
	            {"data":"materialId", className:"none never"},
	  			{"data":"materialName"},
	  			{"data":"materialSpec"},
				{"data":"materialUnit"},
				{"data":"materialNum", "className": "text-right"}
			],
			order: [[ 0, "asc" ]],
			columnDefs: [{
				targets: 0,
				"visible":false,
				render:function(data,type,row,meta){
					//console.info(rowData);
					for(var i=0;i<rowData.length;i++){
						
						if(data==rowData[i].materialId){
							rowIndex.push(meta.row);
						}
					}
					return data;
				}
			}
			],
			responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            }
	    });
	}
};

function trCmSelectedBack(){
	
}
var materialListSearchData=function(){
	//基础条件查询添加监听
	$('#materialListTablePop_filter input').on('change',function(){
		var materialName = $('#materialListTablePop_filter input').val();
		materialdata = {};
		if($("#projId").val()==""){
	    	materialdata.projId=-1;	
	    }else{
	    	materialdata.projId=$("#projId").val();
	    }
		materialdata.materialName = materialName;
		console.info(materialdata);
		$('#materialListTablePop').cgetData();  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#materialListTablePop_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
}
var materialEditLimits = function(){
	if($("#auditState").val()==='1'){
	}
}

var postLimit = function(){
	if($("#post").val().indexOf('43')<0){
		//$(".postLimit").addClass("hidden");
	}
}
var designAlteration = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){    
        		handleDesignAlteration();
            	//showReportUser();
        	})
        }
    };
}();