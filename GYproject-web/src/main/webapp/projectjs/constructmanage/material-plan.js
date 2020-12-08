
var searchData = {};
var detailSearchData={};
var materialdata ={};
var materialdataPop={};
var rowData = {};
var materialPlanTable;
var materialListTable;
var materialPlanDetailTable;
var materialListTablePop;//弹出的材料清单
var handleMaterialPlan = function() {
	"use strict";
	searchData.projId=$("#projId1").val();
	console.info("---");
	console.info($("#projId1").val());
    if ($('#materialPlanTable').length !== 0) {
    	materialPlanTable= $('#materialPlanTable').on( 'init.dt',function(){
    		//加载页面
    		$("#material_plan_panel_box").cgetContent("materialPlan/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#materialPlanTable_filter input").attr("placeholder","反馈人");
   			//隐藏遮罩
   			$('#materialPlanTable').hideMask();
   			//增加监听
   			addMonitor();
   			//查询
   			searchMonotor();
   			//修改
   			modifyMointor();
   			//标记监听
   			signMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' }, 
                { text: '标记', className: 'btn-sm btn-query  business-tool-btn signBtn checkRole' }
               /* { text: '增加', className: 'btn-sm btn-query  business-tool-btn addBtn checkRole' },*/
               /* { text: '修改', className: 'btn-sm btn-query  business-tool-btn modifyBtn checkRole' }*/
                /*{ text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' }*/
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'materialPlan/queryMaterialPlan',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/accept/json/project_accept.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"mpId",className:"none never"}, 
				{"data":"feedBacker"},
				{"data":"modifyReceiveDate"},
				{"data":"isExportDes"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
    
};

var trSelectedBack=function(v, i, index, t, json){
	/*if(json.isFeedBack=="0"){
		$(".feedBackShow").addClass("hidden");
		console.info("0..."+json.isFeedBack);
	}else if(json.isFeedBack=="1"){
		console.info("1..."+json.isFeedBack);
		$(".feedBackShow").removeClass("hidden");
	}*/
	$(".editbtn").addClass("hidden");
	$(".addDetailBtn").addClass("hidden");
	$(".deleteBtn").addClass("hidden");
	/*if(json.isFeedBack="1" || json.isFeedBack="2"){
		
	}*/
	//$(".exportBtn").addClass("hidden");
	t.cgetDetail('materialPlanForm','materialFeedBack/viewMaterial','',materialPlanBack);
}

var materialPlanBack=function(){
	
	//0 未反馈 1 已反馈 2 未推送
	/*if($("#isFeedBack").val()=="0" || $("#isFeedBack").val()=="2"){
		$(".feedBackShow").addClass("hidden");
	}else if($("#isFeedBack").val()=="1"){
		//已反馈
		$(".feedBackShow").removeClass("hidden");
	}
	if($("#isFeedBack").val()=="0" || $("#isFeedBack").val()=="1"){
		$(".modifyBtn").addClass("hidden");
		$(".exportBtn").removeClass("hidden");
	}else{
		$(".modifyBtn").removeClass("hidden");
		$(".exportBtn").addClass("hidden");
	}*/
	
	
	$("input.thisPlanNum").attr("disabled",true);//本次计划领量不可编辑
	if($.fn.DataTable.isDataTable('#materialPlanDetailTable')){
		//初始化过
		detailSearchData.projId=$("#projId").val();
		if($("#mpId").val()==""){
			detailSearchData.mpId="-1";
			detailSearchData.projId="-1";
		}else{
			detailSearchData.mpId=$("#mpId").val();
		}
		$("#materialPlanDetailTable").cgetData(false);//列表重新加载
	}else{
		 
		detailSearchData.projId=$("#projId").val();
		if($("#mpId").val()==""){
			detailSearchData.mpId="-1";
			detailSearchData.projId="-1";
		}else{
			detailSearchData.mpId=$("#mpId").val();
		}
		materialPlanDetailTableInit();
	}
	queryCheckRole();
}

//初始化材料计划明细列表
var materialPlanDetailTableInit=function(){
	"use strict";
	//detailSearchData.projId=$("#projId").val();
    if ($('#materialPlanDetailTable').length !== 0) {
    	materialPlanDetailTable= $('#materialPlanDetailTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#materialPlanDetailTable').hideMask();
   			rowData=materialPlanDetailTable.data();
   			$(this).bindInputsChange(inputChange);
   			//增加计划明细
   			addDetailMonitor();
   			//导出计划监听
   			exportMonitor();
   			//删除监听
   			deleteMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [10],
            dom: 'Brtip',
            buttons: [
                /*{ text: '增加', className: 'btn-sm btn-query  business-tool-btn hidden addDetailBtn checkRole' },
                { text: '删除', className: 'btn-sm btn-warn  business-tool-btn hidden deleteBtn checkRole' },*/
                { text: '导出', className: 'btn-sm btn-default  business-tool-btn  exportBtn checkRole' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:true,
            ajax: {
                url: 'materialFeedBack/queryMaterialList',
                type:'post',
                data: function(d){
                   	$.each(detailSearchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/accept/json/project_accept.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	  			{"data":"materialName", responsivePriority: 1},//名称
	  			{"data":"materialSpec", responsivePriority: 7},//型号
				{"data":"materialUnit", responsivePriority: 8},//单位
				{"data":"materialNum", "className": "text-right", responsivePriority: 7},//设计总量
				{"data":"getAnum", "className": "text-right", responsivePriority: 5},//领用总量
				{"data":"oweNum", "className": "text-right", responsivePriority:6},//欠量
				{"data":"planNum", "className": "text-right", responsivePriority: 4},//本次计划量
				{"data":"certificateComplete", responsivePriority: 2},//合格证是否齐全
				{"data":"getGoodsTime",  responsivePriority: 3},//到货时间
			],
			columnDefs: [{
				"targets":0,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				targets:0,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm field-not-editable"  name="' + row.materialId + '_bmName" id="' + row.materialId + '_bmName" value="'+data+'">'+
						'<input class="hidden field-not-editable"  name="' + row.materialId + '_projId" id="' + row.materialId + '_projId" value="'+row.projId+'">'+
						'<input class="hidden field-not-editable"  name="' + row.materialId + '_bmNo" id="' + row.materialId + '_bmNo" value="'+row.materialNo+'">'+
						'<input class="hidden field-not-editable"  name="' + row.materialId + '_materialId" id="' + row.materialId + '_materialId" value="'+row.materialId+'">'+
						'</div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets:1,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm "  disabled="disabled" name="' + row.materialId + '_bmSpec" id="' + row.materialId + '_bmSpec" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				targets:2,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " disabled="disabled" name="' + row.materialId + '_bmUnit" id="' + row.materialId + '_bmUnit" value="'+data+'"></div>';
						return tdcon;
					}else{
						return data;
					}
				}
			},{
				 'targets':3,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							if(row.materialUnit=="米"){
								return parseFloat(data).toFixed(2);
							}else{
								return data;
							}
						}else{
							return data;
						}
					},
			 	},/*{
				 'targets':6,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							if(row.materialUnit=="米"){
								return parseFloat(data).toFixed(2);
							}
							
						}else{
							return data;
						}
					},
			 	},*/
			{
				targets:5,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						/*var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " disabled="disabled" name="' + row.materialId + '_oweNum" id="' + row.materialId + '_oweNum" value="'+(row.planTotalNum-row.getAnum)+'"></div>';
						return tdcon;*/
						data=row.materialNum-row.getAnum;
						return data;
					}else{
						return data;
					}
				}
			},{
				targets: 7,
		    	/*
				 * render属性
				 * 方法携带四个参数
				 * data: 该单元格的原始数据，也就是默认显示的那些数据
				 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
				 * row: 当前行的所有原始数据
				 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
				 */
		    	render: function ( data, type, row, meta ) {
		    		//data = 0;
		    		if(type === "display"){
						var tdcon = '<div class="tdInnerInput">';
					    tdcon+="<select class='form-control input-sm field-editable notEditable' disabled='disabled' name='certificateComplete' >";
	    				tdcon+="<option value=></option>";
	    				tdcon+='<option value="0" ' + (data == 0 ? "selected" : "") + '>有</option>';
	    				tdcon+='<option value="1" ' + (data == 1 ? "selected" : "") + '>无</option>';
	    				tdcon+='<option value="2" ' + (data == 2 ? "selected" : "") + '>不全</option>';
	    				tdcon+="</select></div>";
	    				return tdcon;
					}else{
						return data;
					}
		    	}
		    }],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        })/*.on("draw.dt",function(){
	    	$("#materialPlanForm").toggleEditState(false).styleFit();
	    	alert(1);
	    });*/
        .on("draw.dt", function(){
    		rowData=materialPlanDetailTable.data();
    		$("input.thisPlanNum").attr("disabled",false);//本次计划领量可编辑
    	});
    }
}
//输入框change事件
var inputChange=function(){
	$("input.thisPlanNum").attr("disabled",false);//本次计划领量可编辑
}


//点击增加按钮
var addMonitor=function(){
	$(".addBtn").off().on('click',function(){
		$("#materialPlanForm,#materialPlanDetailForm").toggleEditState(true);
		$(".editbtn").removeClass("hidden");
		$("#mpId").val("");
		$("#planReceiveDate").val("");
		$("#feedBackInputShow").val("");
		$("#isFeedBack").val("0")
		//$(".feedBackShow").addClass("hidden");
		$(".addDetailBtn").removeClass("hidden");
		$(".deleteBtn").removeClass("hidden");
		var url = 'constructDiary/findByProjId';
		var projId = getProjectInfo().projId;
		$.ajax({
            type: 'POST',
            url: 'constructDiary/findByProjId',
            data: 'projId='+projId,
            dataType: 'json',
            success: function (data) {
            	$("#applicationDate").val(data.dlDate);
            	$('#proposer').val(data.dlRecorder);
            	$('#proposerId').val(data.staffId);
            	var cp = data.constructionPlan;
            	if(cp){
            		$("#cuName").val(cp.cuName);
            		$("#cuLegalRepresent").val(cp.cuLegalRepresent);
            		$("#builder").val(cp.builder);
            		$("#projId").val(cp.projId);
            		$("#projNo").val(cp.projNo);
            		$("#projName").val(cp.projName);
            	}
            	materialPlanBack();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("cgetDetail() -> 详情查询失败");
                console.warn(jqXHR);
                console.warn(textStatus);
                console.warn(errorThrown);
            }
        });
	})
}


//修改监听
var modifyMointor=function(){
	$(".modifyBtn").off().on("click",function(){
		
		var len = $('#materialPlanTable').DataTable().ajax.json().data ? $('#materialPlanTable').DataTable().ajax.json().data.length : $('#materialPlanTable').DataTable().ajax.json().length;
		if(len>0){
			$("#materialPlanForm,#materialPlanDetailForm").toggleEditState(true);
			//编辑按钮显示
			$(".editbtn").removeClass("hidden");
			//增加按钮显示
			$(".addDetailBtn").removeClass("hidden");
			$(".exportBtn").removeClass("hidden");
			$(".deleteBtn").removeClass("hidden");
		}else{
			alertInfo("请选择要修改的材料计划！");
		}
	})
}
//标记监听
var signMonitor=function(){
	$(".signBtn").off().on("click",function(){
		var len=$("#materialPlanTable").find('tr.selected').length;
		if(len>0){
			$("body").cgetPopup({
               	title: "提示信息",
               	content: "是否将选中的材料反馈信息标记为已导出？",
               	accept: sureDone,
               	chide: true,
               	icon: "fa-check-circle"
           });
		}else{
			alertInfo("请选择要标记的材料反馈信息！");
		}
	})
	
}
var sureDone=function(){
	var mpId=$("#mpId").val();
	$.ajax({
		type:'post',
		url:'materialPlan/signMaterialPlan',
		contentType: "application/json;charset=UTF-8",
        data: mpId,
        success:function(data){
        	var content = "标记成功！";
        	if(data=="false"){
        		content = "标记失败！";
        	}
        	var myoptions = {
                	title: "提示信息",
                	content: content,
                	accept: tableReload,
                	chide: true,
                	newpop: 'new',
                	icon: "fa-check-circle"
            }
            $("body").cgetPopup(myoptions);
        	
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("数据保存失败！");
        }
        
	})
}
var tableReload=function(){
	searchData.projId = getProjectInfo().projId;
	//列表重新加载
    $("#materialPlanTable").cgetData(true,tableCallBack);
}
//点击查询按钮
var searchMonotor=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#materialPlan/materialPlanSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#materialPlanTable_filter input").on("change",function(){
		searchData.feedBacker = $("#materialPlanTable_filter input").val();
		//传入false  则不选中行
		$("#materialPlanTable").cgetData(true,tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#materialPlanTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}

//查询点击确定
var searchDone=function(){
	searchData = $("#searchForm_maerialPlan").serializeJson();
	searchData.feedBacker = $("#materialPlanTable_filter input").val();
	searchData.projId = getProjectInfo().projId;
	//列表重新加载
    $("#materialPlanTable").cgetData(true,tableCallBack);  
}
var tableCallBack=function(){
	var len = $('#materialPlanTable').DataTable().ajax.json().data ? $('#materialPlanTable').DataTable().ajax.json().data.length : $('#materialPlanTable').DataTable().ajax.json().length;
	//console.log("len..."+len);
	if(len<1){
		$(".exportBtn").addClass("hidden");
		$("#materialPlanForm input").val("");
		$("#materialPlanForm textarea").val("");
		detailSearchData.projId = "-1";
		$("#materialPlanDetailTable").cgetData(false);
	}
}
//增加材料计划
var addDetailMonitor=function(){
	$(".addDetailBtn").off().on('click',function(){
		var url = "#materialPlan/materialList";
		$("body").cgetPopup({
			title: '材料列表',
			content: url,
			accept: mcDone,
			size:"super"
		});
	})
}
//导出材料计划明细
var exportMonitor=function(){
	$(".exportBtn").off().on("click",function(){
		if($("#mpId").val()==""){
			alertInfo("没有要导出的材料列表！");
		}else{
			$("#mpId1").val($("#mpId").val());
			$("#exprotPlanDetailForm").submit();
		}
	})
}

//删除监听
var deleteMonitor=function(){
	$(".deleteBtn").off().on("click",function(){
		var len=$('#materialPlanDetailTable').find('tr.selected').length;
		 if(len>0){
			 var rows = $("#materialPlanDetailTable").DataTable().rows( '.selected' ).remove().draw();
			 $('#materialPlanDetailTable').selectRow(0);
		 }else{
			 $("body").cgetPopup({
			 	title: '提示信息',
				content: '请选择要删除的记录!',
				accept: delPlanDetail,
				icon: 'fa-exclamation-circle',
			 });
		 }
	})
}

var delPlanDetail=function(){
	
}

//点击确定
function mcDone(){
	var jsons=trSData.materialListTablePop.jsons;
	/*console.info("jsons--------------");
	console.info(jsons);
	console.info("rowData--------------");
	console.info(rowData);*/
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
			jsons[i].certificateComplete="1";
			
		}
		rowData=rowData.concat(jsons);
		console.info("rowData111");
		console.info(rowData);
		console.info("jsons111");
		console.info(jsons);
		materialPlanDetailTable.rows.add(jsons).draw();
		$("input.thisPlanNum").attr("disabled",false);
    }
}

//弹出材料清单

var handleMaterialList = function() {
	"use strict";

	if($("#projId").val()==""){
		materialdataPop.projId=-1;	
    }else{
    	materialdataPop.projId=$("#projId").val();
    }
	var rowIndex=[];  
	if ($('#materialListTablePop').length !== 0) {
		rowIndex=[];
		materialListTablePop= $('#materialListTablePop').on( 'init.dt',function(){
	    	//默认选中第一行
    		$(this).bindDTSelected(trCmSelectedBack,false);
	    	//隐藏遮罩
	    	$("#materialListTablePop").hideMask();
	    	//搜索
    		$('#materialListTablePop_filter input').attr('placeholder','设备材料汇总表'); 
    		//materialListSearchData();
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
			        $.each(materialdataPop,function(i,k){
			            d[i] = k;
			        }); 	
			    },
			    datasrc: 'data'
			},
			select: true,  //支持多选
	        columns: [
				/*{"data":"projId", "className":"none never"},*/
				{"data":"materialId", className:"none never"},
				{"data":"materialName", responsivePriority: 1},//名称
				{"data":"materialSpec", responsivePriority: 2},//型号
				{"data":"materialUnit", responsivePriority: 7},//单位
				{"data":"materialNum", "className": "text-right", responsivePriority: 3},//设计总量
				{"data":"getAnum", "className": "text-right", responsivePriority: 4},//领用总量
				{"data":"inspectionAnum", "className": "text-right", responsivePriority: 7},//报验总量
				{"data":"useAnum", "className": "text-right", responsivePriority: 6},//使用总量
				{"data":"planTotalNum", "className": "text-right", responsivePriority: 5}//计划领用总量
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

var trCmSelectedBack=function(){
	
}
var queryBack=function(){
	$(".addDetailBtn").addClass("hidden");
	$(".deleteBtn").addClass("hidden");
	$("#materialPlanForm,#materialPlanDetailForm").toggleEditState(false);
	
}

var materialPlan = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handleMaterialPlan();
        	});
        }
    };
}();