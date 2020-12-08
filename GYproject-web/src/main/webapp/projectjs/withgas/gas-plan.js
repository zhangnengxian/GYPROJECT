
var searchData = {},
    detailSearchData={},
    materialdata ={},
 	rowData = {},
 	gasPlanTable,
	materialListTable,
 	gasProjectTable;
detailSearchData.gpId = "-1";
var handleGasPlan = function() {
	"use strict";
    if ($('#gasPlanTable').length !== 0) {
    	gasPlanTable= $('#gasPlanTable').on( 'init.dt',function(){
    		//加载页面
    		$("#material_plan_panel_box").cgetContent("gasPlan/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
			$("#gasPlanTable_filter input").attr("placeholder","计划编号");
   			//隐藏遮罩
   			$('#gasPlanTable').hideMask();
   			//增加监听
   			addMonitor();
   			//查询
   			searchMonotor();
   			//修改
   			modifyMointor();
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
                { text: '增加', className: 'btn-sm btn-query  business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query  business-tool-btn modifyBtn' }
                /*{ text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' }*/
            ],
            //启用服务端模式，后台进行分段查询、排序
            // serverSide:true,
            ajax: {
                url: 'gasPlan/queryGasPlan',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});

                },
                dataSrc: 'data'
            },
            // ajax: 'projectjs/withgas/json/gas_plan.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"gpId",className:"none never"},
                {"data":"gpNo"},
				{"data":"gpName"},
				{"data":"createDate"},
			],
			columnDefs: [
                {
                    "targets": 0,
                    "visible":false
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

var trSelectedBack=function(v, i, index, t, json){
	$(".editbtn").addClass("hidden");
	$(".addDetailBtn").addClass("hidden");
	$(".deleteBtn").addClass("hidden");
	t.cgetDetail('gasPlanForm','','',gasPlanBack);
}

var gasPlanBack=function(){
    detailSearchData.gpId = $("#gpId").val()||"-1";
	if($.fn.DataTable.isDataTable("#gasProjectTable")){
		$("#gasProjectTable").cgetData(false);//列表重新加载
	}else{
        handleProjectTable();
	}
}

var trSelectedBack1 = function (v, i, index, t, json) {
    //审核的不可删除
    // if(json.gasProjStatus=='4'||json.gasProjStatus=='5'||json.gasProjStatus=='6'){
    //     $(".delProjBtn").addClass("hidden");
    // }else{
    //     $(".delProjBtn").removeClass("hidden");
    // }
}
var handleProjectTable=function(){
	"use strict";
	// var json=trSData.gasPlanTable.json;
	// if(json){
	// 	detailSearchData.gpId=json.gpId;
	// }else{
	// 	detailSearchData.gpId="-111";
	// }
    if ($('#gasProjectTable').length !== 0) {
    	gasProjectTable= $('#gasProjectTable').on( 'init.dt',function(){
            //默认选中第一行
            $(this).bindDTSelected(trSelectedBack1,false);
            // $("#gasPlanTable_filter input").attr("placeholder","工程编号");
            //隐藏遮罩
            $('#gasProjectTable').hideMask();
            //增加监听
            addProjMonitor();
            //删除监听
            delProjMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [10],
            dom: 'Brtip',
            buttons: [
                { text: '增加', className: 'btn-sm btn-query business-tool-btn hidden addProjBtn' },
                // { text: '修改', className: 'btn-sm btn-query  business-tool-btn modifyBtn' },
                { text: '删除', className: 'btn-sm btn-warn business-tool-btn hidden delProjBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
            // serverSide:true,
            ajax: {
                url: 'openingPlan/queryGasProject',
                type:'post',
                data: function(d){
                   	$.each(detailSearchData,function(i,k){
                   		d[i] = k;
                   	});

                },
                dataSrc: 'data'
            },
            // ajax: 'projectjs/withgas/json/gas_plan_detail2.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"gprojId",className:"none never"},
                {"data":"projLtypeId",className:"none never"},
	  			{"data":"projectTypeDes", responsivePriority: 3},
                {"data":"projName", responsivePriority: 1},
	  			{"data":"projNo", responsivePriority: 2},
	  			{"data":"custName", responsivePriority: 4},
				{"data":"projAddr", responsivePriority: 5},
				{"data":"gasComLegalRepresent", "className": "text-right", responsivePriority: 6},
				{"data":"cuName", "className": "text-right", responsivePriority: 7},
				{"data":"scNo", "className": "text-right", responsivePriority:8},
				{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}

				],
            columnDefs: [
            	{
                "targets": 0,
                "visible":false
            },{
                    "targets": 1,
                    "visible":false
        	},{
                    "targets":3,
                    //长字符串截取方法
                    render: $.fn.dataTable.render.ellipsis({
                        length: 10, 	//截取多少字符（或汉字）
                        end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                    })
                }],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        })
    }
}

//点击增加按钮
var addMonitor=function(){
	$(".addBtn").off().on('click',function(){
        $('#gasPlanTable').clearSelected();
		$("#gasPlanForm").formReset();
        $("#gpId").val("");
		$("#creater").val($("#createrName").val());
		$(".editbtn").removeClass("hidden");
        $(".addProjBtn,.delProjBtn").removeClass("hidden");
		$("#gasPlanForm").toggleEditState(true);

		detailSearchData.gpId = "-1";
		$("#gasProjectTable").cgetData(false);//列表重新加载
		// if($.fn.DataTable.isDataTable('#gasProjectTable')){
		// }else{
         //    handleProjectTable();
		// }
	})
}

//查询点击确定
var addProjDone=function(){
    var data = gasProjectTablePop.rows('.selected').data(),
        data1 = gasProjectTable.rows().data();
    //去除重复数据
    for(var i = 0;i<data.length;i++){
        for(var j = 0;j<data1.length;j++){
            if(data.length > 0){
                if(data[i].gprojId == data1[j].gprojId){
                    data.splice(i,1);
                }
            }
        }
    }

    $("#gasProjectTable").DataTable().rows.add(data).draw();

}

//点击工程删除按钮
var delProjMonitor=function(){
    $(".delProjBtn").off().on('click',function(){
        $("#gasProjectTable").DataTable().row( '.selected' ).remove().draw();
    })
};

//点击工程增加按钮
var addProjMonitor=function(){
    $(".addProjBtn").off().on('click',function(){
        var url = "#gasPlan/gasProjectPop";
        var popoptions = {
            title: '选择工程',
            content: url,
            accept: addProjDone,
            size:'super'
        };
        //加载弹屏
        $("body").cgetPopup(popoptions);
    })
}

//修改监听
var modifyMointor=function(){
	$(".modifyBtn").off().on("click",function(){
		// var len = $('#gasPlanTable').DataTable().ajax.json().data ? $('#gasPlanTable').DataTable().ajax.json().data.length : $('#gasPlanTable').DataTable().ajax.json().length;
		if($("#gasPlanTable").find("tr.selected").length>0){
			$("#gasPlanForm").toggleEditState(true);
            $("#creater").val($("#createrName").val());
			$(".addProjBtn,.delProjBtn").removeClass("hidden");
			//编辑按钮显示
			$(".editbtn").removeClass("hidden");
		}else{
			alertInfo("请选择要修改的计划！");
		}
	})
}

//点击查询按钮
var searchMonotor=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#gasPlan/gasPlanSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#gasPlanTable_filter input").on("change",function(){
		searchData.gpNo = $("#gasPlanTable_filter input").val();
		//传入false  则不选中行
		$("#gasPlanTable").cgetData(true,tableCallBack); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#gasPlanTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
}

//查询点击确定
var searchDone=function(){
	searchData = $("#searchForm_gasPlan").serializeJson();
	searchData.gpNo = $("#gasPlanTable_filter input").val();
	// searchData.projId = getProjectInfo().projId;
	//列表重新加载
    $("#gasPlanTable").cgetData(true,tableCallBack);  
}
var tableCallBack=function(){
	// var len = $('#gasPlanTable').DataTable().ajax.json().data ? $('#gasPlanTable').DataTable().ajax.json().data.length : $('#gasPlanTable').DataTable().ajax.json().length;
	// //console.log("len..."+len);
	// if(len<1){
	// 	$(".exportBtn").addClass("hidden");
	// 	$("#gasPlanForm input").val("");
	// 	$("#gasPlanForm textarea").val("");
	// 	detailSearchData.projId = "-1";
	// 	$("#gasPlanDetailTable").cgetData(false);
	// }
}

var gasPlan = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){        		
        		handleGasPlan();
        	});
        }
    };
}();