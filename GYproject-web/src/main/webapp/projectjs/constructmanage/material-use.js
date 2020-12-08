/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var searchData={},
handleMaterialUse = function() {
	"use strict";
	//searchData.projId="1001";
	searchData.projId = getProjectInfo().projId;
    $('#materialUseTable').on( 'init.dt',function(){
    	
    	$("#materialUseForm").toggleEditState(false);
    	
    	//隐藏遮罩
    	$("#materialUseTable").hideMask();
    	
    	var dateRanger = '使用日期   <input type="text" class="form-control datepicker-default input-sm useDate" data-parsley-required="true"  name="useDate">';
    	$("#materialUseTable_filter").html(dateRanger);


	    $('.datepicker-default').datepicker({
	        todayHighlight: true
	    });
        saveMonitor();
        //本次安装量、本次使用量校验添加
//        checkNum();
        
        $(this).bindInputsChange(inputChanged);
        queryCheckRole();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [ 18 ],
        dom: 'fBrtip',
        buttons: [
            { text: '保存', className: 'btn-sm btn-query checkRole muSaveBtn' }
        ],
//        serverSide: true, 
        ajax: {
            url: 'materialUse/queryMaterialList',
            contenttype:"application/json;charset=utf-8",
            data:function(d){
                $.each(searchData,function(i,k){
                    d[i] = k;
                });
            },
            datasrc: 'data'
        },
        responsive: true,
		/*responsive: {
        	details: {
        		renderer: function ( api, rowIdx, columns ){
        			return renderChild(api, rowIdx, columns);
        		}
        	}
        },*/
        columns: [
  			{"data":"materialName"},
  			{"data":"materialSpec"},
			{"data":"materialUnit"},
			{"data":"materialPrice", "className":"none never"},
			{"data":"materialNum", "className": "text-right"},
			{"data":"getAnum", "className": "text-right"},
			/*{"data":"inspectionAnum", "className": "text-right"},*/
			{"data":"useAnum", "className": "text-right"},
			{"data":"flagDes"},
			{"data":"flag", "className": "text-right"},
			{"data":"aiAnum", "className": "text-right"},
			{"data":"flag1", "className": "text-right"}
		],
		order: [[ 0, "asc" ]],
		columnDefs: [{
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
					var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm field-not-editable"  name="' + row.materialId + '_bmSpec" id="' + row.materialId + '_bmSpec" value="'+data+'"></div>';
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
					var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm field-not-editable"  name="' + row.materialId + '_bmUnit" id="' + row.materialId + '_bmUnit" value="'+data+'"></div>';
					return tdcon;
				}else{
					return data;
				}
			}
		},{
			targets:3,
			render:function(data,type,row,meta){
				if(type === "display"){
					if(data === null){
						data = "";
					}else if(data!==""){
						data = data.toFixed(2);
					}
					var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm text-right field-not-editable"  name="' + row.materialId + '_bmPrice" id="' + row.materialId + '_bmPrice" value="'+data+'"></div>';
					return tdcon;
				}else{
					return data;
				}
			}
		},{
			targets: 8,
			render: function ( data, type, row, meta ) {
				if(type === "display"){
					var getAnum = '0';
					var useAnum = '0';
					//领用总量
					if(row.getAnum){
						getAnum = row.getAnum+'';
					}
					//使用总量
					if(row.useAnum){
						useAnum = row.useAnum+'';
					}
					
					if(row.getAnum === null||row.getAnum === ""){
						row.getAnum = "0";
					}
					if(row.useAnum === null||row.useAnum === ""){
						row.useAnum = "0";
					}
					
					if(data === null){
						data = "";
					}
					var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm text-right useNum miNum" data-parsley-type="number" ' + (row.flag1 > 0 ? 'data-parsley-required="true" ' : ' ') + 'data-parsley-max="' + (parseFloat(row.getAnum) - parseFloat(row.useAnum)) + '" name="' + row.materialId + '_miNum" id="' + row.materialId + '_miNum" value="'+ data +'"></div>';
					return tdcon;
				}else{
					return data;
				}
		    }
		},{
			targets: 10,
			render: function ( data, type, row, meta ) {
				if(type === "display"){
					/*var getAnum = '0';
					var useAnum = '0';
					//领用总量
					if(row.getAnum){
						getAnum = row.getAnum+'';
					}
					//使用总量
					if(row.useAnum){
						useAnum = row.useAnum+'';
					}
					var maxValue=0;
					if(parseInt(getAnum)>parseInt(useAnum)){
						maxValue = parseFloat(getAnum)-parseFloat(useAnum);
					}
					//本次使用量小于等于领用总量减去使用总量
					var maxValueParsley = 'data-parsley-max="'+maxValue+'"';*/
					var maxValueParsley = '';
					if(data === null){
						data = "";
				    }
					var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm text-right aiNum"  ' + (row.flag > 0 ? 'data-parsley-max="' + row.flag + '"' : '') + ' name="' + row.materialId + '_aiNum" id="' + row.materialId + '_aiNum" value="'+ data +'"></div>';
					return tdcon;
				}else{
					return data;
				}
		    }
		}]
    }).on("draw.dt",function(){
    	$("#materialUseForm").toggleEditState(false);
    });
};

var inputChanged = function(v, input, t, dt){
		
//	var t = $('[name="' + input.attr("name") + '"]'),
//	//当前表格值
//	miNum = v || 0;
//	//console.log("ninum onblur value:"+miNum);
//	//本次安装量添加最大值校验
//	t.neighborInpput("aiNum").attr("data-parsley-max",miNum).attr("data-parsley-required",true);
//
//	$(".aiNum").off("blur").on("blur",function(){
//		console.info("111111111");
//		var t = $(this),
//		//当前表格值
//		aiNum = t.val() || 0;
//		//添加必填项校验
//		t.neighborInpput("miNum").attr("data-parsley-required",true);
//	});
	if(input.is(".aiNum")){
		$('[name="' + input.attr("name") + '"]').closest("tr").find(".miNum").trigger("change.ferrinweb");
	}
	if(input.is(".miNum")){
		$('[name="' + input.attr("name") + '"]').closest("tr").find(".aiNum").trigger("change.ferrinweb");
	}
	//console.info(1);
	//dt.draw();
}

var saveMonitor = function(){
	$(".muSaveBtn").on("click",function(){
//	       var tableform = $("#materialUseForm");
//	       //开启表单验证
//	       if (tableform.parsley().isValid() && $(".useDate").parsley().isValid()) {
//	       		var mdata = tableform.getDTFormData();
//	       		//选中的工程规模明细
//	       		var resultData = [];
//	       		for(var i=0;i<mdata.length;i++){
//	       			var data = mdata[i];
//	       			if(data.miNum !== undefined && data.miNum!==""){
//	       				data.useDate = $(".useDate").val();
//	       				resultData.push(data);
//	       			}
//	       		}
//	       		if(resultData.length<1){
//	       			alertInfo("请填写要领用材料的数量！");
//	       			return false;
//	       		}
//	       		console.log("json:"+JSON.stringify(resultData));
//	       		$.ajax({
//	       			type: 'POST',
//	       			url: 'materialUse/saveMaterialUses',
//	       			contentType: "application/json;charset=UTF-8",
//	       			data: JSON.stringify(resultData),
//	       			success: function (data) {
//	       				var content = "数据保存成功！";
//	       				if(data === "false"){
//	       					content = "数据保存失败！";
//	       				}else if(data === "true"){
//	       					searchData.projId = getProjectInfo().projId;
//	       					$("#materialUseTable").cgetData(false,checkNum);  //列表重新加载
//	       				}
//	       				var myoptions = {
//	                       	title: "提示信息",
//	                       	content: content,
//	                       	accept: popClose,
//	                       	chide: true,
//	                       	icon: "fa-check-circle"
//	                   }
//	                   $("body").cgetPopup(myoptions);
//	               },
//	               error: function (jqXHR, textStatus, errorThrown) {
//	                   console.warn("材料批量领用保存失败！");
//	               }
//	            });
//	       		//如果通过验证, 则移除验证UI
//	       		tableform.parsley().validate();
//	       }else{
//	    	    alertInfo("请核实输入数据:<br>" +
//	    	    		"  领用日期必填项;<br>" +
//	    	    		"  本次使用量与使用总量之和必须小于或等于领用总量;<br>" +
//	    	    		"  本次安装量必须小于等于本次使用量！");	    	   
//	       	 	//如果未通过验证, 则加载验证UI
//	       	 	tableform.parsley().validate();
//	       }
							var t = $('#materialUseTable');
							if (t.checkInputs()) {
								// console.info(t.getInputsData());
								var data = t.getInputsData({
									"bmName" : "materialName",
									"projId" : "projId",
									"bmNo" : "materialNo",
									"materialId" : "materialId",
									"bmSpec" : "materialSpec",
									"bmUnit" : "materialUnit",
									"bmPrice" : "materialPrice",
									"miNum" : "flag",
									"aiNum" : "flag1"
								});
							
                                //   保存时间
								var resultData = [];
								for (var i = 0; i < data.length; i++) {
									var data1 = data[i];
									data1.useDate = $(".useDate").val();
									resultData.push(data1);
								}

								if (resultData.length) {
                                    $("#materialUseForm").loadMask("正在保存...", 1, 0.5);
									$.ajax({
											type : 'POST',
											url : 'materialUse/saveMaterialUses',
											contentType : "application/json;charset=UTF-8",
											data : JSON.stringify(resultData),
											success : function(data) {
                                                $("#materialUseForm").hideMask();
												var content = "数据保存成功！";
												if (data === "false") {
													content = "数据保存失败！";
													var myoptions = {
															title : "提示信息",
															content : content,
															accept : popClose,
															chide : true,
															icon : "fa-check-circle"
														}
														$("body").cgetPopup(
																myoptions);
												} else {
													var myoptions = {
															title : "提示信息",
															content : content,
															accept : function(){
									                       		setTimeout(function(){
									                       			searchData.projId = getProjectInfo().projId;
																	$("#materialUseTable").cgetData(false); // 列表重新加载
									                       		},0);
									                       	},
															chide : true,
															icon : "fa-check-circle"
														}
														$("body").cgetPopup(myoptions);
													
												}
												
											},
											error : function(jqXHR,
													textStatus, errorThrown) {
												console.warn("材料批量领用保存失败！");
											}
										});
								}else{
									alertInfo("请填写要领用材料的数量！");
					       			return false;
								}

							} else {
								console.info("表单校验失败，请检查并修改未通过项目！");
							}
						
					

					});
}


//初始化表格
var materialUse = function() {
	"use strict";
    return {
        init: function() {
        	handleMaterialUse();
        }
    };
}();