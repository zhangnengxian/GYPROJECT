/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var searchData={},
handleMaterialInspection = function() {
	"use strict";
	searchData.projId = getProjectInfo().projId;
    $('#materialInspectionTable').on( 'init.dt',function(){
    	
    	$("#materialInspectionForm").toggleEditState(false);
    	
    	//隐藏遮罩
    	$("#materialInspectionTable").hideMask();
    	
    	$("#materialInspectionForm").toggleEditState();
    	
    	var dateRanger = '报验日期   <input type="text" class="form-control datepicker-default input-sm miDate" data-parsley-required="true" name="miDate">';
    	$("#materialInspectionTable_filter").html(dateRanger);


	    $('.datepicker-default').datepicker({
	        todayHighlight: true
	    });
    	saveMonitor();
    	$(this).bindInputsChange();
    	queryCheckRole();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [ 18 ],
        dom: 'fBrtip',
        buttons: [
            { text: '保存', className: 'btn-sm btn-query checkRole miSaveBtn' }
        ],
//        serverSide: true, 
		ajax: {
		    url: 'materialInspection/queryMaterialList',
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
			{"data":"inspectionAnum", "className": "text-right"},
			{"data":"flag", "className": "text-right"}
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
			targets: 7,
			/*
			 * render属性
			 * 方法携带四个参数
			 * data: 该单元格的原始数据，也就是默认显示的那些数据
			 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
			 * row: 但前行的所有原始数据
			 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
			 */
			render: function ( data, type, row, meta ) {
				if(type === "display"){
					if(data === null){
						data = "";
					}
					if(row.getAnum === null||row.getAnum === ""){
						row.getAnum = "0";
					}
					if(row.inspectionAnum === null||row.inspectionAnum === ""){
						row.inspectionAnum = "0";
					}
					var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm fixed-number text-right"  data-parsley-max="' + (parseFloat(row.getAnum) - parseFloat(row.inspectionAnum)) + '" name="' + row.materialId + '_miNum" id="' + row.materialId + '_miNum" value="'+ data +'"></div>';
					return tdcon;
				}else{
					return data;
				}
		    }
		}]
    }).on("draw.dt",function(){
    	$("#materialInspectionForm").toggleEditState(false);
    });
};

var saveMonitor = function(){
	$(".miSaveBtn").on("click",function(){
//	       var tableform = $("#materialInspectionForm");
//	       //开启表单验证
//	       if (tableform.parsley().isValid() && $(".miDate").parsley().isValid()) {
//	       		var mdata = tableform.getDTFormData();
//	       		//选中的工程规模明细
//	       		var resultData = [];
//	       		for(var i=0;i<mdata.length;i++){
//	       			var data = mdata[i];
//	       			if(data.miNum !== undefined && data.miNum!==""){
//	       				data.miDate = $(".miDate").val();
//	       				resultData.push(data);
//	       			}
//	       		}
//	       		if(resultData.length<1){
//	       			alertInfo("请填写要报验材料的数量！");
//	       			return false;
//	       		}
//	       		console.log("json:"+JSON.stringify(resultData));
//	       		$.ajax({
//	       			type: 'POST',
//	       			url: 'materialInspection/saveMaterialInspections',
//	       			contentType: "application/json;charset=UTF-8",
//	       			data: JSON.stringify(resultData),
//	       			success: function (data) {
//	       				var content = "数据保存成功！";
//	       				if(data === "false"){
//	       					content = "数据保存失败！";
//	       				}else if(data === "true"){
//	       					searchData.projId = getProjectInfo().projId;
//	       					$("#materialInspectionTable").cgetData(false);  //列表重新加载
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
//	                   console.warn("材料批量报验保存失败！");
//	               }
//	            });
//	       		//如果通过验证, 则移除验证UI
//	       		tableform.parsley().validate();
//	       }else{
//	       	 	//如果未通过验证, 则加载验证UI
//	       	 	tableform.parsley().validate();
//	       }
		
		var t = $('#materialInspectionTable');
		if(t.checkInputs()){
			//console.info(t.getInputsData());
			var data = t.getInputsData({"bmName": "materialName", "projId": "projId", "bmNo": "materialNo", "materialId": "materialId", "bmSpec": "materialSpec", "bmUnit": "materialUnit", "bmPrice": "materialPrice", "miNum": "flag"});

			console.info(data);
			
			var resultData = [];
			for(var i=0;i<data.length;i++){
   			var data1 = data[i];
   				data1.miDate = $(".miDate").val();
   				resultData.push(data1);
   		}
			console.info(resultData);
			
			if(data.length){
	       		$.ajax({
	       			type: 'POST',
	       			url: 'materialInspection/saveMaterialInspections',
	       			contentType: "application/json;charset=UTF-8",
	       			data: JSON.stringify(resultData),
	       			success: function (data) {
	       				var content = "数据保存成功！";
	       				if(data === "false"){
	       					content = "数据保存失败！";
	       					var myoptions = {
	    	                       	title: "提示信息",
	    	                       	content: content,
	    	                       	accept: popClose,
	    	                       	chide: true,
	    	                       	icon: "fa-check-circle"
	    	                   }
	    	                   $("body").cgetPopup(myoptions);
	       				}else{
	       					
	       					var myoptions = {
	    	                       	title: "提示信息",
	    	                       	content: content,
	    	                       	accept: function(){
			                       		setTimeout(function(){
			                       			searchData.projId = getProjectInfo().projId;
			    	       					$("#materialInspectionTable").cgetData(false);  //列表重新加载
			                       		},0);
			                       	},
	    	                       	chide: true,
	    	                       	icon: "fa-check-circle"
	    	                   }
	    	                   $("body").cgetPopup(myoptions);
	       				}
	       				
	               },
	               error: function (jqXHR, textStatus, errorThrown) {
	                   console.warn("材料报验保存失败！");
	               }
	            });
			}else{
				alertInfo("请填写本次报验量！");
			}
		}else{
			console.info("表单校验失败，请检查并修改未通过项目！");
		}
	});
}
//初始化表格
var materialInspection = function() {
	"use strict";
    return {
        init: function() {
        	handleMaterialInspection();
        }
    };
}();