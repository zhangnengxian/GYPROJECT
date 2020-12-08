var searchData={},applyPrintTable;

var applyPrint = function () {
    return {
        init: function () {
            $.getScript("js/ellipsis.js").done(function(){
                handleProjectAccept();
            });
        }
    };
}();


var handleProjectAccept = function() {
    applyPrintTable= $('#applyPrintTable').on( 'init.dt',function(){
        //默认选中第一行
        $(this).bindDTSelected(SelectedTR,true);
        $("#applyPrintTable_filter input").attr("placeholder","工程编号");
        //隐藏遮罩
        $('#applyPrintTable').hideMask();
        //绑定单击事件 弹出搜索框
        searchMonitor();
        setTimeout(function(){ $("#applyPrintTable").DataTable().columns.adjust(); }, 0);
    }).DataTable({
        language: language_CN,
        lengthMenu: [18],
        dom: 'Bfrtip',
        buttons: [
            { text: '查询', className: 'btn-sm btn-query searchBtn' }
        ],
        //启用服务端模式，后台进行分段查询、排序
        serverSide:true,
        ajax: {
            url: 'applyPrintController/getpaymentpplyList',
            type:'post',
            data: function(d){
                $.each(searchData,function(i,k){
                    d[i] = k;
                });
            },
            dataSrc: 'data'
        },
        select: true,  //支持多选
        columns: [
            {data:"paId",visible:false},
            {data:"projNo"},
            {data:"projName"},
            {data:"projectTypeDes"},
            {data:"auditState",visible:false}
        ],
        columnDefs: [{
            "targets":2,
            //长字符串截取方法
            render: $.fn.dataTable.render.ellipsis({
                length: 8, 	//截取多少字符（或汉字）
                end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
            })
        }],
        fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
            if(aData.overdue){
                $(nRow).addClass("expired-proect");
            }
        }
    });
};



var SelectedTR = function(v, i, index, t, json){
    json.menuId=getStepId();//将menuId添加到json对象属性中
    queryCptUrl(json);//查询cpt
};

//弹屏监听方法
var searchMonitor = function(){
    //查询按钮弹出屏查询
    $(".searchBtn").on("click",function(){
        var url = "#acceptancePrintController/projectPrintSearchPopPage";
        var popoptions = {
            title: '查询',
            content: url,
            accept: searchDone
        };
        //加载弹屏
        $("body").cgetPopup(popoptions);
    });
    //基础条件查询添加监听
    $("#applyPrintTable_filter input").on("change",function(){
        var projNo = $("#applyPrintTable_filter input").val();
        searchData.projNo = projNo;
        $("#applyPrintTable").cgetData(true);  //列表重新加载

    });
    //基础条件查询添加回车事件
    $('#applyPrintTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
            $(this).change();
        }
    });
};

/** 查询弹出屏，点击确定后 */
var searchDone = function(){
    searchData = $("#acceptancePrintSearchForm").serializeJson();
    $("#applyPrintTable").cgetData(true,function(){
        $('#applyPrintTable').DataTable().ajax.json().data ? $('#applyPrintTable').DataTable().ajax.json().data.length : $('#applyPrintTable').DataTable().ajax.json().length;
    });
};
