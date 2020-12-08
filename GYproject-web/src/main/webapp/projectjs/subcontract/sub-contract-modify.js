var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var subConstractTable; //待签分包工程列表
var searchData={};	   //查询条件
var constructionUnitData={};
var menuId ="110603";//施工合同菜单ID
/**
 * 加载工程列表
 */
var handleSubContract = function() {
	'use strict';
    if ($('#subContractTable').length !== 0) {
    	subConstractTable=$('#subContractTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#subContractTable').hideMask();
   			$('#subContractTable_filter input').attr('placeholder','工程编号');
   			//搜索监听
   			searchMonitor();
   			//签订监听
   			updateMonitor();
   			setTimeout(function(){
   			    $("#subContractTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
                 { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                 { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' }
                 ],
             //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
                url: 'subContractModify/querySubContract',
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
				 {'data':'scId',className:'none never'}, 
	  			 {'data':'scNo'}, 
				 {'data':'projName'},
				 {'data':'modifyStateDes'},
				/* {"data":"signBack",
						className:"none never",
						"createdCell": function (td, cellData, rowData, row, col) {
							if(cellData==$("#notThrough").val()){
								$(td).parent().find("td").css("background", "rgb(255, 219, 219)");
								//$(td).attr("id",row);
							}
						}
						},
				{"data":"overdue",className:"none never" },
				{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}*/
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
					"targets":3,
					 "orderable":false
				}],
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
		var url = '#subContractModify/projectSearchPopPage';
		//加载弹屏
		$('body').cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#subContractTable_filter input').on('change',function(){
		var projNo = $('#subContractTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#subContractTable').cgetData(true,subTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#subContractTable_filter input').on('keyup', function(event) {
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
	var projNo = $('#subContractTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#subContractTable').cgetData(true,subTableCallBack); 
}

var subTableCallBack=function(){
	var len = $('#subContractTable').DataTable().ajax.json().data ? $('#subContractTable').DataTable().ajax.json().data.length : $('#subContractTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#subContractDetailform')[0].reset();
		 $(':input','#subContractDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	 }
}

//修改监听
var updateMonitor=function(){
	$('.updateBtn').off('click').on('click',function(){

		if($('#subContractTable').find('tr.selected').length>0){
			$("#contractMethod").removeClass("field-not-editable");
			$("#contractMethod").addClass("field-editable");
			$('#subContractDetailform').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
			if(!$("#contractMethodFlag").val()){
				$("#contractMethodFlag").toggleEditState(false);
			}
			if(!$("#scSignDate").val()){
				var sysDate = timestamp($("#sysDate").val());
                $("#scSignDate").val(format(sysDate,"default"));
			}

			if($("#differenceId").val()=='modify'){//修改时可计划开工日期和竣工日期
				$('#scPlannedStartDate,#scPlannedEndDate').attr("readonly",false);
			}

		}else{
			alertInfo('请选择要修改分包合同！');
		}
	});
};

/**
 *选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$("#contractMethod").val(json.contractMethod);
		$('.editbtn').addClass('hidden');
    //加载右侧页面
    $("#sub_contract_panel_box").cgetContent("subContractModify/viewPage",{projId:json.projId,corpId:json.corpId,menuId:menuId},function(){
        //参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
        t.cgetDetail('subContractDetailform','subContractModify/viewSubContract','',cgetDetailBack);
    });
};

/**详述回调*/
var cgetDetailBack = function(data){
	$("#scNo").val(data.projNo);
	if($("#projLtypeId").val()=="14"){
		$(".artery").removeClass("hidden");
	}else{
        $(".artery").addClass("hidden");
	}
	if($("#projLtypeId").val()=="12"){
        $(".payRate").addClass("hidden");
	}else{
        $(".payRate").removeClass("hidden");
	}
	if(!$("#scAmount").val()){
		$("#scAmount").val($("#quAmount").val());
	}
	if($("#deptDivide").val()==$("#customerServiceCenter").val()){
		//默认选中包工包料
		$("#contractMethod").val("3");
	}
	//修改按钮
	if(data.modifyState=='0'){
		$(".updateBtn").addClass("hidden");
	}else{
		$(".updateBtn").removeClass("hidden");
	}
	console.log(data.contractMethod1);
	if($("#contractMethod option:selected").val()=="3"){
		$("#contractMethod").removeClass("field-editable");
		$("#contractMethod").addClass("field-not-editable");
	}else{
		if(data.contractMethod1){
			$("#contractMethod").val("1");
			$("#contractMethod").removeClass("field-editable");
			$("#contractMethod").addClass("field-not-editable");
		}else{
			if($("#contractMethod option:selected").val()==""){
				$("#contractMethod").val("1");
				}
			$("#contractMethod").removeClass("field-not-editable");
			$("#contractMethod").addClass("field-editable");
		}
	}

    $("#payType,#scType,#progressType").empty();    //reset
    $("#payType,#scType,#progressType").append('<option value=""></option>');//加空行
    console.info(data.payTypes);
	$.each(data.payTypes,function(n, v){
		if (v.payType) {
			$("#payType").append('<option value=' + v.ptId + '>' + v.payType + '</option>');
		} else {
			$(".payType").addClass("hidden");
		}
		 $("#payType").val(data.payType);  //赋值
		if(v.scType){
			$("#scType").append('<option value='+v.ptId+'>' + v.scType + '</option>');
		}
		$("#scType").val(data.scType);     //赋值
		if (v.progressType) {
			$("#progressType").append('<option value=' + v.ptId + '>' + v.progressType + '</option>');
		}
		$("#progressType").val(data.progressType);  //赋值
    });
	
	//税率默认
	if(!$("#increment").val()){
		$("#increment").val("3.00");
	}
	if(!$("#payRate").val()){
		$("#payRate").val("50");
	}
}

/**
 * 初始化工程列表
 */
var subContract = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){     
        		handleSubContract();
        	});
        }
    };
}();