/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var searchdata={},
handleMaterialReceive = function() {
	"use strict";
	//searchdata.projId='1001';
	searchdata.projId = getProjectInfo().projId;
    $('#materialReceiveTable').on( 'init.dt',function(){
    	$("#materialReceiveForm").toggleEditState(false);
    	//隐藏遮罩
    	$("#materialReceiveTable").hideMask();
    	
    	$("#materialReceiveForm").toggleEditState();
    	
    	var dateRanger = '领用日期  <input type="text" class="form-control datepicker-default input-sm mgDate" data-parsley-required="true" name="mgDate">';
    	$("#materialReceiveTable_filter").html(dateRanger);


	    $('.datepicker-default').datepicker({
	        todayHighlight: true
	    });
        
        saveMonitor();
    	
        $(this).bindInputsChange();
        queryCheckRole();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [ 10000 ],
        dom: 'fBrtip',
        buttons: [
            { text: '保存', className: 'btn-sm btn-query checkRole mcSaveBtn' }
        ],
        //serverSide: true, 
        //ajax: 'projectjs/constructmanage/json/material-use.json',
		ajax: {
		    url: 'materialReceive/queryMaterialList',
		    contenttype:"application/json;charset=utf-8",
		    data:function(d){
		        $.each(searchdata,function(i,k){
		            d[i] = k;
		        });
		               	
		    },
		      datasrc: 'data'
		},
        columns: [
            {"data":"projId", "className":"none never"},
            {"data":"materialNo", "className":"none never"},
  			{"data":"materialName", responsivePriority: 2},
  			{"data":"materialSpec", responsivePriority: 9},
			{"data":"materialUnit", responsivePriority: 10},
			{"data":"materialPrice","className":"none never"},
			{"data":"materialNum", "className": "text-right", responsivePriority: 5},//设计总量
			{"data":"getAnum", "className": "text-right", responsivePriority: 4},//领用总量
			{"data":"oweNum", "className": "text-right", responsivePriority: 11},
			/*{"data":"inspectionAnum", "className": "text-right", responsivePriority: 7},*/
			{"data":"useAnum", "className": "text-right", responsivePriority: 6},
			{"data":"flagDes",responsivePriority: 10},
			{"data":"flag", "className": "text-right", responsivePriority: 1},
		],
		order: [[ 0, "asc" ]],
		responsive: true,
//		responsive: {
//        	details: {
//        		renderer: function ( api, rowIdx, columns ){
//        			return renderChild(api, rowIdx, columns);
//        		}
//        	}
//        },
		columnDefs: [{
			targets:0,
			"visible":false
		},{
			targets:1,
			"visible":false
		},{
			targets:2,
			render:function(data,type,row,meta){
				if(data === null){
					data = "";
				}
				if(type === "display"){
					var tdcon = '<div class="tdInnerInput"><input class="form-control input-sm field-not-editable"  name="' + row.materialId + '_bmName" id="' + row.materialId + '_bmName" value="' + data + '">'+
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
			"targets":2,
			//长字符串截取方法
			render: $.fn.dataTable.render.ellipsis({
				length: 10, 	//截取多少字符（或汉字）
				end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
			})
		},{
			targets:3,
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
			targets:4,
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
			targets:5,
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
			targets:8,
			render:function(data,type,row,meta){
				if(data === null){
					data = "";
				}
				if(type === "display"){
					data=row.materialNum-row.getAnum;
					return data;
				}else{
					return data;
				}
			}
		},{
			targets: 11,
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
					var tdcon = '<div class="tdInnerInput"><input  class="form-control input-sm text-right" data-parsley-type="number" name="' + row.materialId + '_mcNum" id="' + row.materialId + '_mcNum" value="'+ data +'"></div>';
					return tdcon;
				}else{
					return data;
				}
		    }
		}]
    }).on("draw.dt",function(){
    	$("#materialReceiveForm").toggleEditState(false);
    });
};
var saveMonitor = function(){
	$(".mcSaveBtn").on("click",function(){
		var t = $('#materialReceiveTable');
		var saveAble = true;
		if(t.checkInputs()){
			//console.info(t.getInputsData());
			var data = t.getInputsData({"bmName": "materialName", "projId": "projId", "bmNo": "materialNo", "materialId": "materialId", "bmSpec": "materialSpec", "bmUnit": "materialUnit", "bmPrice": "materialPrice", "mcNum": "flag","materialNum":"materialNum","getAnum":"getAnum"});

			$.each(data,function(){
				var getNumber,
					totalNumber,
					content;
				if(this.getAnum){
					getNumber=parseFloat(this.mcNum)+parseFloat(this.getAnum);
				}else{
					getNumber=this.mcNum;
				}
				
				if(this.bmUnit=="米"){
					totalNumber = this.materialNum*1.05;
					if(getNumber>totalNumber){
						content="领用材料已超设计总量5%";
						saveAble = false;
					}
				}else{
					totalNumber = this.materialNum;
					console.info(getNumber);
					console.info(totalNumber);
					if(getNumber>totalNumber){
		                content="领用材料已超设计总量";
						saveAble = false;
					}
	                   }
					if(!saveAble){
						var myoptions = {
		                       	title: "提示信息",
		                       	content: content,
		                       	accept: popClose,
		                       	chide: true,
		                       	icon: "fa-check-circle"
					}
						 $("body").cgetPopup(myoptions);
						return false;
				}
				
			});
			if(saveAble){
				var resultData = [];
				for(var i=0;i<data.length;i++){
		   			var data1 = data[i];
	   				data1.mgDate = $(".mgDate").val();
	   				resultData.push(data1);
		   		}
				$("#materialReceiveForm").loadMask("正在保存...", 1, 0.5);
				if(data.length){
		       		$.ajax({
		       			type: 'POST',
		       			url: 'materialReceive/saveMaterialLists',
		       			contentType: "application/json;charset=UTF-8",
		       			data: JSON.stringify(resultData),
		       			success: function (data) {
                            $("#materialReceiveForm").hideMask();
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
		 	                   $("body").cgetPopup({
			                       	title: "提示信息",
			                       	content: content,
			                       	accept: function(){
			                       		setTimeout(function(){
					       					searchdata.projId = getProjectInfo().projId;
					       					$("#materialReceiveTable").cgetData(false);  //列表重新加载
			                       		},0);
			                       	},
			                       	chide: true,
			                       	icon: "fa-check-circle"
			                   });
		       				} 
		               },
		               error: function (jqXHR, textStatus, errorThrown) {
		                   console.warn("材料批量领用保存失败！");
		               }
		            });
				}else{
					alertInfo("请填写要领用材料的数量！");
				}
			}
			
		}else{
			console.info("表单校验失败，请检查并修改未通过项目！");
		}
	});
}
//初始化表格
var materialReceive = function() {
	"use strict";
    return {
        init: function() {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleMaterialReceive();
        	})
        }
    };
}();