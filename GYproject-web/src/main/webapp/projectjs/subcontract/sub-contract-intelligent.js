var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var subConstractIntelligentTable; //待签分包工程列表
var searchData={};	   //查询条件
var constructionUnitData={};
/**
 * 加载工程列表
 */
var handleSubContractIntelligent = function() {
	'use strict';
    if ($('#subContractIntelligentTable').length !== 0) {
    	subConstractIntelligentTable=$('#subContractIntelligentTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$('#sub_contract_Intelligent_panel_box').cgetContent('subContractIntelligent/viewPage');
   			//$('#sub_safe_panel_box').cgetContent('subContract/subSafePage');
   			//隐藏遮罩
   			$('#subContractIntelligentTable').hideMask();
   			$('#subContractIntelligentTable_filter input').attr('placeholder','工程编号');
   			//搜索监听
   			searchMonitor();
   			//签订监听
   			signMonitor();
   			setTimeout(function(){
   			    $("#subContractIntelligentTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
                 { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                 { text: '签订', className: 'btn-sm btn-query business-tool-btn signBtn' }
                 ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'subContractIntelligent/queryProjects',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
             },
             //ajax: 'projectjs/subcontract/json/sub_contract.json',
             responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
             select: true,  //支持多选
             columns: [
				 {'data':'projId',className:'none never'}, 
	  			 {'data':'projNo'}, 
				 {'data':'projName'},
				 {'data':'projStatusDes'},
				 {'data':'flag'},
				 {"data":"signBack",
						className:"none never",
						"createdCell": function (td, cellData, rowData, row, col) {
							if(cellData==$("#notThrough").val()){
								$(td).parent().find("td").css("background", "rgb(255, 219, 219)");
								//$(td).attr("id",row);
							}
						}
						},
				{"data":"overdue",className:"none never" }
			 ],
			 columnDefs: [{
				 'targets':0,
				 'visible':false
			 },{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: showLength, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},{
					 'targets':4,
					 "render": function ( data, type, row, meta ) {
							if(data=="1"){
								return "已推送";
							}else{
								return "未推送";
							}
						},
				 	}
			 ],
			 fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
					if(aData.overdue){
						$(nRow).addClass("expired-proect");
					}
			}
        });
    }
};

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$('.searchBtn').on('click',function(){
		var url = '#subContractIntelligent/projectSearchPopPage';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#subContractIntelligentTable_filter input').on('change',function(){
		var projNo = $('#subContractIntelligentTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#subContractIntelligentTable').cgetData(true,subTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#subContractIntelligentTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};
/**
 * 查询弹出屏，点击确定后 
 */
var searchDone = function(){
	searchData = $('#searchForm_sub').serializeJson();
	var projNo = $('#subContractIntelligentTable_filter input').val();
	searchData.projNo = projNo;
	console.info(searchData);
	//列表重新加载
    $('#subContractIntelligentTable').cgetData(true,subTableCallBack); 
}

var subTableCallBack=function(){
	var len = $('#subContractIntelligentTable').DataTable().ajax.json().data ? $('#subContractIntelligentTable').DataTable().ajax.json().data.length : $('#subContractIntelligentTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#subContractDetailform')[0].reset();
		 $(':input','#subContractDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}

//签订监听
var signMonitor=function(){
	$('.signBtn').off('click').on('click',function(){
		if($('#subContractIntelligentTable').find('tr.selected').length>0){
			$('#subContractDetailform').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
			
			if($("#flag").val()=='1'){
				alertInfo("该合同已确认过，不可再修改！");
				 $("#subContractDetailform").toggleEditState(false);
				 $(".editbtn").addClass("hidden");
			}
		}else{
			alertInfo('请选择要签订分包的工程记录！');
		}
	});
};

/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$('.editbtn').addClass('hidden');
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	t.cgetDetail('subContractDetailform','subContractIntelligent/viewSubConIntelligent','',cgetDetailBack);
}
/**
 * 根据付款方式不同显示款项
 */
var changePayTypeFunc = function(){
	if($("#payType").val()=='1'){//二次付清
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    	$("#firstPayment").val((new Number($("#totalCost").val())*0.9).toFixed(2));
    	$("#secondPayment").val((new Number($("#totalCost").val())*0.1).toFixed(2));
    	$("#thirdPayment").val("");
    	$("#subContractDetailform").styleFit();
    }else if($("#payType").val()=='2') {//三次付清
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').removeClass("hidden");
    	$("#firstPayment").val((new Number($("#totalCost").val())*0.85).toFixed(2));
    	$("#secondPayment").val((new Number($("#totalCost").val())*0.1).toFixed(2));
    	$("#thirdPayment").val((new Number($("#totalCost").val())*0.05).toFixed(2));
    	$("#subContractDetailform").styleFit();
    }else{
    	$('.firstPayment').addClass("hidden");
    	$('.secondPayment').addClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    }
	var src1=$("#projNo").val();
	if($("#itScNo").val()==""){
		$("#itScNo").val(src1.substring(0,4)+"02"+src1.substring(6,14));
	}
	
	
}
/**详述回调*/
var cgetDetailBack = function(data){
	if(data.itScId==''||data.itScId==null){
		$("#signDate").val(format(data.signDate,null));
	}
    $("#scType,#progressType").empty();    //reset
    $("#scType,#progressType").append('<option value=""></option>');//加空行
	$.each(data.payTypes,function(n, v){
		if(v.scType){
			$("#scType").append('<option value='+v.ptId+'>' + v.scType + '</option>');
		}
		$("#scType").val(data.scType);     //赋值
		if (v.progressType) {
			$("#progressType").append('<option value=' + v.ptId + '>' + v.progressType + '</option>');
		}
		$("#progressType").val(data.progressType);  //赋值
    });
	changePayTypeFunc();
	//税率默认
	if(!$("#increment").val()){
		$("#increment").val("3.00");
	}
}

/**
 * 初始化工程列表
 */
var subContractIntelligent = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handleSubContractIntelligent();
        	});
        }
    };
}();