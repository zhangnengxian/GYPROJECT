
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
    		$("#material_plan_panel_box").cgetContent("materialPlanFeedBack/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#materialPlanTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#materialPlanTable').hideMask();
   			//增加监听
   			feedBackMonitor();
   			//查询
   			searchMonotor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },         
                { text: '反馈', className: 'btn-sm btn-query  business-tool-btn feedBackBtn checkRole' }
                /*{ text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' }*/
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'materialPlanFeedBack/queryMaterialPlan',
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
				{"data":"projNo"},
				{"data":"projName"},
				{"data":"planReceiveDate"},
				{"data":"isFeedBackDes"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
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
    
};

var trSelectedBack=function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	$("#materialPlanForm input").val("");
	if(json.isFeedBack=="1"){
		$(".feedBackBtn").addClass("hidden");
	}else if(json.isFeedBack=="0"){
		$(".feedBackBtn").removeClass("hidden");
	}
	
	t.cgetDetail('materialPlanForm','materialPlan/viewMaterial','',materialPlanBack);
}

var materialPlanBack=function(){
	$("input.notEditable").attr("disabled",true);//不可编辑
	if($.fn.DataTable.isDataTable('#materialPlanDetailTable')){
		//初始化过
		detailSearchData.projId=$("#projId").val();
		if($("#mpId").val()==""){
			$("#mpId").val("-1");
		}
		detailSearchData.mpId=$("#mpId").val();
		$("#materialPlanDetailTable").cgetData(false);//列表重新加载
	}else{
		detailSearchData.projId=$("#projId").val();
		if($("#mpId").val()==""){
			$("#mpId").val("-1");
		}
		detailSearchData.mpId=$("#mpId").val();
		materialPlanDetailTableInit();
	}
}

//初始化材料计划明细列表
var materialPlanDetailTableInit=function(){
	"use strict";
	//detailSearchData.projId=$("#projId").val();
    if ($('#materialPlanDetailTable').length !== 0) {
    	materialPlanDetailTable= $('#materialPlanDetailTable').on( 'init.dt',function(){
    		//默认选中第一行
    		$(this).bindDTSelected(trDetailSelectedBack,true);
   			//隐藏遮罩
   			$('#materialPlanDetailTable').hideMask();
   			rowData=materialPlanDetailTable.data();
   			$(this).bindInputsChange(inputChange);
   			//增加计划明细
   			addDetailMonitor();
   			//inputMonitor();
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [10],
            dom: 'Brtip',
            buttons: [
                { text: '增加', className: 'btn-sm btn-query  business-tool-btn hidden addDetailBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:true,
            ajax: {
                url: 'materialPlan/queryMaterialList',
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
				{"data":"planTotalNum", "className": "text-right", responsivePriority: 9},//计划领用总量
				{"data":"planNum", "className": "text-right", responsivePriority: 4},//本次计划量
				{"data":"oweNum", "className": "text-right", responsivePriority:6},//欠量
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
				targets:6,
				render:function(data,type,row,meta){
					if(type === "display"){
						var tdcon = '<div class="tdInnerInput">';
					        tdcon += '<input class="form-control input-sm text-right thisPlanNum field-not-editable" disabled="disabled" data-parsley-type="number" data-parsley-required="true" value=' + data + '>';						 					    
					        tdcon += '</div>';
					        return tdcon;
					}else{
						return data;
					}
				}
			},
			{
				targets:7,
				render:function(data,type,row,meta){
					if(data === null){
						data = "";
					}
					if(type === "display"){
						/*var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm " disabled="disabled" name="' + row.materialId + '_oweNum" id="' + row.materialId + '_oweNum" value="'+(row.planTotalNum-row.getAnum)+'"></div>';
						return tdcon;*/
						data=row.planTotalNum-row.getAnum;
						return data;
					}else{
						return data;
					}
				}
			},
			{
		    	targets: 8,
		    	/*
				 * render属性
				 * 方法携带四个参数
				 * data: 该单元格的原始数据，也就是默认显示的那些数据
				 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
				 * row: 当前行的所有原始数据
				 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
				 */
		    	render: function ( data, type, row, meta ) {
		    		
		    		if(type === "display"){
						if(data === null||data==="1"){
							console.info("data1......."+data);
							var  tdcon="<div class='tdInnerInput'>" +
									   "<label><input class='radio-checked notEditable' name='"+ row.materialId +"_certificateComplete' type='radio'disabled='disabled' value='1' checked> 是</label>" +
							           "&nbsp;&nbsp;<label><input class='notEditable' name='"+ row.materialId +"_certificateComplete' type='radio'disabled='disabled' value='0'> 否</label></div>";
							return tdcon;
							
						}
						if(data === "0"){
							var  tdcon="<div class='tdInnerInput'><input class='' type='hidden' name = '"+ row.materialId +"_mpdId' id='"+ row.materialId +"_mpdId' value="+row.materialId+">" +
									"<label><input class='notEditable' name='"+ row.materialId +"_certificateComplete' type='radio' disabled='disabled' value='1'> 是</label>" +
					                "&nbsp;&nbsp;<label><input class='radio-checked  notEditable' name='"+ row.materialId +"_certificateComplete'disabled='disabled' type='radio' value='0' checked> 否</label></div>";
					        return tdcon;
						}
					}else{
						return data;
					}}
		    },{
				targets:9,
				render:function(data,type,row,meta){
					if(type === "display"){
						if(data===null){
							data="";
						}
						var tdcon = '<div class="tdInnerInput">';
				        tdcon += '<input class="form-control input-sm text-right datepicker-default notEditable"  disabled="disabled" data-parsley-required="true" value=' + data + '>';						 					    
				        tdcon += '</div>';
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
        }).on("draw.dt",function(){
        	
        	$('.datepicker-default').datepicker({
                todayHighlight: true
            });
   			setTimeout(function(){
   	   			$(':radio[checked]').prop('checked', 'checked');
   			},50);
   			
	    });
    }/*else{
    	materialPlanDetailTable.ajax.reload();
	}*/
    /*$('#materialPlanDetailTable').on("draw.dt", function(){
		rowData=materialPlanDetailTable.data();
	});*/
    
    
    //默认选项
    
    
}
var trDetailSelectedBack=function(){
	$(':radio[checked]').prop('checked', 'checked');
}

//输入框change事件
var inputChange=function(){
	$("input.notEditable").attr("disabled",false);//可编辑
}

/*var inputMonitor=function(){
	$(".tdInnerInput input").on("change",function(){
		
	});
}*/
//点击反馈按钮
var feedBackMonitor=function(){
	$(".feedBackBtn").off().on('click',function(){
		if($("#materialPlanTable").find("tr.selected").length>0){
			$("#materialPlanForm,#materialPlanDetailForm").toggleEditState(true);
			$(".editbtn").removeClass("hidden");
			$("input.notEditable").attr("disabled",false);//可编辑
		}else{
			alertInfo('请选择要反馈的计划信息！');
		}
		
	})
}

//点击查询按钮
var searchMonotor=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#materialPlanFeedBack/materialPlanSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#materialPlanTable_filter input").on("change",function(){
		searchData.projNo = $("#materialPlanTable_filter input").val();
		//传入false  则不选中行
		$("#materialPlanTable").cgetData(true); //列表重新加载
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
	searchData.projNo = $("#materialPlanTable_filter input").val();
	searchData.projId = getProjectInfo().projId;
	//列表重新加载
    $("#materialPlanTable").cgetData(true,tableCallBack);  
}
var tableCallBack=function(){
	var len = $('#materialPlanTable').DataTable().ajax.json().data ? $('#materialPlanTable').DataTable().ajax.json().data.length : $('#materialPlanTable').DataTable().ajax.json().length;
	//console.log("len..."+len);
	if(len<1){
		$(".editbtn").addClass("hidden");
		$("#materialPlanForm input").val("");
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

		/*for(var i=0;i<jsons.length;i++){
			jsons[i].mcType=$("#mcType").val();
			jsons[i].cmId=$("#cmId").val();
			jsons[i].mcId="";
			jsons[i].adjustQuantity='';	
		}*/
		rowData=rowData.concat(jsons);
		console.info("rowData111");
		console.info(rowData);
		console.info("jsons111");
		console.info(jsons);
		materialPlanDetailTable.rows.add(jsons).draw();
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