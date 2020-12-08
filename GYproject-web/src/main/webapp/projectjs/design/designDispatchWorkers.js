
var searchData={menuId:"1102102"};	 //查询条件

/**
 * 初始化工程列表
 */
var designDispatchWorkers  = function () {
    return {
        init: function () {
            $.getScript("js/ellipsis.js").done(function(){
                handleDesignDispatch();
            })
        }
    };
}();


/**
 * 加载工程列表
 */
var handleDesignDispatch = function() {

        $('#designDispatchTable').on( 'init.dt',function(){
            //默认选中第一行
            $(this).bindDTSelected(trSelectedBack,true);
            //加载页面
            $("#design_dispatch_panel_box").cgetContent("designDispatchWorkers/viewPage",{'projId':null,'menuId':searchData.menuId});
            //隐藏遮罩
            $('#designDispatchTable').hideMask();
            $("#designDispatchTable_filter input").attr("placeholder","工程编号");
            //绑定单击事件 弹出搜索框
            searchMonitor();
            setTimeout(function(){
                $("#designDispatchTable").DataTable().columns.adjust();
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
            buttons: [{ text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }],
            serverSide:true,
            ajax:{
                url:"designDispatchWorkers/queryProject",
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
            select: true,    //支持多选
            columns: [
                {"data":"projId",className:"none never"},
                {"data":"projNo"},
                {"data":"projName"},
                {"data":"projStatusDes"},
                {"data":"workDayDto"},
                {"data":"overdue",className:"none never"}
            ],
            columnDefs: [{
                "targets": 0,
                "visible":false
            },{
                "targets":2,
                //长字符串截取方法
                render: $.fn.dataTable.render.ellipsis({
                    length: 8, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            },{
                "targets":3,
                "orderable":false
            },{
                "targets":4,
                "render":function(data,type,row,meta){
                    if(data!=null){
                        return data.haveDays;
                    }else{
                        return 0;
                    }
                }
            }],
            fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
                if(aData.overdue){
                    $(nRow).addClass("expired-proect");
                }
            }
        });
};




/**
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
    searchData.projId=json.projId;
    $("#design_dispatch_panel_box").cgetContent("designDispatchWorkers/viewPage",{'projId':null,'menuId':searchData.menuId},function () {
        t.cgetDetail('designDispatchForm','designDispatchWorkers/queryProjectDetail','',detailBack);
    });

}

var detailBack=function(){};



/**
 * 派遣成功后刷新设计员表格
 */
var updateDesignerBack=function(){
    if ($.fn.DataTable.isDataTable('#designerTable')) {
        //列表重新加载
        $('#designerTable').cgetData(false,designerTableCallBack);
    }else{
        //初始化设计员表格
        designertableinit();
    }
}

var designerTableCallBack = function(){};

/**
 * 加载设计员表格
 */
var designertableinit= function() {
   $('#designerTable').on( 'init.dt',function(){
        $(this).bindDTSelected(trSelectedBack1,true);
        $('#designerTable').hideMask();
    }).DataTable({
        language: language_CN,
        lengthMenu: [10],
        dom: 'Brtip',
        buttons: [
        ],
        ajax: {
            url: 'designDispatchWorkers/queryDesigner',
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
            {"data":"staffId"},
            {"data":"staffName"},
            {"data":"taskCount"}
        ],
        columnDefs: [{
            "targets": 0,
            "visible":false
        }]
    });
};

var trSelectedBack1=function(v, i, index, t, json){
    $("#designerId").val(json.staffId);
    $("#designer").val(json.staffName);
};






/**
 * 查询弹出屏，点击取消后
 */
var clearForm=function(){
    //清空表单
    $("#searchForm_designDispatch input").val("");
    //清空下拉框
    $("#projLtypeId  option[value=''] ").attr("selected",true);
}

/**
 * 弹屏监听方法
 */
var searchMonitor= function(){
    $(".searchBtn").on("click",function(){
        var url = "#designDispatchSecondary/projectSearchPopPage";
        //加载弹屏
        $("body").cgetPopup({
            title: '查询',
            content: url,
            accept: searchDone
        });
    });
    //基础条件查询添加监听
    $("#designDispatchTable_filter input").on("change",function(){
        var projNo = $("#designDispatchTable_filter input").val();
        searchData.projNo = projNo;
        $("#designDispatchTable").cgetData(true,designDispatchCallBack);  //列表重新加载
    });
    //基础条件查询添加回车事件
    $('#designDispatchTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
            $(this).change();
        }
    });
};

/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
    searchData = $("#searchForm_designDispatch").serializeJson();
    var projNo=$('#designDispatchTable_filter input').val();
    searchData.projNo=projNo;
    $("#designDispatchTable").cgetData(true,designDispatchCallBack);  //列表重新加载
}

var designDispatchCallBack = function(){

}
