/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var divisionalAcceptanceTable;
var scaleTable;

var trSelectedJson={};
/**查询条件  默认待勘察*/
var searchData={};
searchData.acceptanceState = $("#haveNotAcceptance").val();
var accessoryData={};
var detailSearchData = {};
var handledivisionalAcceptance = function() {
	"use strict";
    if ($('#divisionalAcceptanceTable').length !== 0) {
    	divisionalAcceptanceTable= $('#divisionalAcceptanceTable').on( 'init.dt',function(){
    		//加载页面
    		var len = $('#divisionalAcceptanceTable').DataTable().ajax.json().data ? $('#divisionalAcceptanceTable').DataTable().ajax.json().data.length : $('#divisionalAcceptanceTable').DataTable().ajax.json().length;
    		if(len<1){     //默认加载每一个公司下的第一个分部验收页面
    			$("#project_accept_panel_box").cgetContent("divisionalAcceptance/viewPage");
    		}
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#divisionalAcceptanceTable_filter input").attr("placeholder","工程编号");
   			//隐藏遮罩
   			$('#divisionalAcceptanceTable').hideMask();
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			//修改监听方法
   			applyMonitor();
            destroyMonitor();
   			setTimeout(function(){
   			    $("#divisionalAcceptanceTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
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
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },         
                { text: '验收', className: 'btn-sm btn-query business-tool-btn applyBtn' },
                { text: '作废', className: 'btn-sm btn-warn business-tool-btn destroyBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'divisionalAcceptance/queryDivisionalAcceptance',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/complete/json/divisional_acceptance_apply.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"daaId",className:"none never"},
                {"data":"projId",className:"none never"},
                {"data":"projNo"},
				{"data":"projName"},
				{"data":"projStateDes"},
				{"data":"acceptanceStateDes"},
				{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				"targets":3,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: showLength, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":4,
				 "orderable":false
			},{
				"targets":5,
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

//弹屏监听方法
var searchMonitor = function(){
	
	//查询按钮弹出屏查询
	$(".searchBtn").on("click",function(){
			var url = "#divisionalAcceptance/acceptanceSearchPopPage";
			var popoptions = {
				title: '查询',
				content: url,
				accept: searchDone
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
	});
	//基础条件查询添加监听
	$("#stateSelect,#divisionalAcceptanceTable_filter input").on("change",function(){
		var projNo = $("#divisionalAcceptanceTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;
		$("#divisionalAcceptanceTable").cgetData(true);  //列表重新加载
		$(".applyBtn").show();
		
	});
	//基础条件查询添加回车事件
	$('#divisionalAcceptanceTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
        	$(this).change();
        }
    });
	
};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
	searchData = $('#searchAcceptancePop').serializeJson();
	var projNo = $('#divisionalAcceptanceTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    if($('#acceptanceState').val()==1){
    	$(".applyBtn").hide();
    	$('#divisionalAcceptanceTable').cgetData(true,queryBack);
    }else{
    	$(".applyBtn").show();
    	$('#divisionalAcceptanceTable').cgetData(true,queryBack);
    }
}


var queryBack=function(){
	var len = $('#divisionalAcceptanceTable').DataTable().ajax.json().data ? $('#divisionalAcceptanceTable').DataTable().ajax.json().data.length : $('#divisionalAcceptanceTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $('#diviaionalAcceptanceDetailform')[0].reset();
		 $(':input','#diviaionalAcceptanceDetailform').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
         $('.editbtn').addClass("hidden");
	  }

}

//修改监听方法
var applyMonitor = function(){
	$(".applyBtn").on("click",function(){
		if($("#divisionalAcceptanceTable").find("tr.selected").length>0){
			detailFlag = true;
			//切换可编辑状态
			$("#diviaionalAcceptanceDetailform").toggleEditState(true);
			getSignStatusByPostId($("#post").val(),"diviaionalAcceptanceDetailform");
			//维护按钮显示出来
			signDate();
			$(".editbtn").removeClass("hidden");
			//验收时间
            if(!$("#acceptanceDate").val()){
                var sysDate = timestamp($("#sysDate").val());
                $("#acceptanceDate").val(format(sysDate,"default"));
            }
            if($("#deptDivide").val()==$("#qualitySafetyGroup").val()){
                $(".entBtn").removeClass("hidden");
            }else{
                $(".entBtn").addClass("hidden");
            }
		}else{
			alertInfo('请选择要修改的记录！');
		}
	});
};

var destroyMonitor=function () {
    $(".destroyBtn").on("click",function(){
        if($("#divisionalAcceptanceTable").find("tr.selected").length<1){
            alertInfo("请选择你要作废得记录!")
			return false;
        }
        var url = "#divisionalAcceptance/acceptanceDestroyPopPage";
        var popoptions = {
            title: '作废原因',
            content: url,
            accept: destroyDone
        };
        //加载弹屏
        $("body").cgetPopup(popoptions);
	})
}



var destroyDone=function () {
    $(".infodetails").hideMask("正在保存...");
    Base.subimt("divisionalAcceptance/saveDestroyDivisiAce","POST",JSON.stringify(trSelectedJson),function (data) {
        var content =data?"保存成功！":"保存失败！";
        titieTips(content);
        $("#divisionalAcceptanceTable").cgetData(true);//列表重新加载
    });
}



function titieTips(content) {
    var myoptions = {
        title: "提示信息",
        content: content,
        accept: popClose,
        chide: true,
        icon: "fa-check-circle"
    };
    $("body").cgetPopup(myoptions);
}
















//判断页面签字之后调用
//获取form中可编辑的日期输入框，给其增加必填属性
var signDate = function(){
	var signDates = $(".signDate");
	$.each(signDates,function(i,e){
		if($(e).attr("readonly")!="readonly"){
			//$(e).attr("required",true);
		}
	});
}

var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
    trSelectedJson=json;
	$(".editbtn").addClass("hidden");
	$(".informSaveBtn").addClass("hidden");
	
	if(json.acceptanceState==$("#alreadyAcceptance").val()){
		$(".applyBtn").addClass("hidden");
	}else{
		$(".applyBtn").removeClass("hidden");
	}
	var menuId = getStepId();
	//根据公司iD,工程类型、菜单Id查找分部验收右侧页面
	$("#project_accept_panel_box").cgetContent("divisionalAcceptance/viewPage",{projId:json.projId,menuId:menuId},callbackPage(v, i, index, t, json));
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	
}
var callbackPage =  function(v, i, index, t, json) {
	detailFlag = false;
	t.cgetDetail('diviaionalAcceptanceDetailform','divisionalAcceptance/viewDivisionalAcceptance','',queryDetailBack);
}
var queryDetailBack = function(data){
	data = fixNull(data);
	var f=$("#diviaionalAcceptanceDetailform");
    //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
    var selects = f.find('select[disabled], .sign-data-input[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
    selects.removeAttr("disabled");
    f.deserialize(data);
    f.initFixedNumber();
    //有disabled属性的下拉菜单元素对象重新添加禁用属性
    selects.attr("disabled","disabled");
	
	var json=trSData.divisionalAcceptanceTable.json;
	
	if(json){
		$("#projNo").val(json.projNo);
		$("#projName").val(json.projName);
		$("#projAddr").val(json.projAddr);
	}
	$('input:radio[name="trAcceptanceSituat"]').change();
	$('input:radio[name="cuAcceptanceSituat"]').change();
	$('input:radio[name="meAcceptanceSituat"]').change();
	
}



var divisionalAcceptance = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handledivisionalAcceptance();
        	});
        }
    };
}();


