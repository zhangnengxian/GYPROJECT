var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
    showLength=7;
}else{
    showLength=15;
}
var searchData = {};
var menuId="110819";
searchData.menuId= menuId;
var handleAuditSettlementJonintlySign = function() {
    if ($('#auditSettlementJonintlySignTable').length !== 0) {
        $('#auditSettlementJonintlySignTable').on( 'init.dt',function(){
            //默认选中第一行
            //$(this).bindDTSelected(trSelectedBack,true);
            //隐藏遮罩
            $('#auditSettlementJonintlySignTable').hideMask();
            $("#auditSettlementJonintlySignTable_filter input").attr("placeholder","工程编号");
            //搜索监听
            searchMonitor();
            setTimeout(function(){
                $("#auditSettlementJonintlySignTable").DataTable().columns.adjust();
                //$("#workOrderTable").DataTable().responsive.recalc();
            }, 0);
        }).on( 'draw.dt', function () {
            auditBtnMonitor();
        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            bStateSave:true,
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'auditSettlementJonintlySign/queryProject',
                type:'post',
                data: function(d){
                    $.each(searchData,function(i,k){
                        d[i] = k;
                    });

                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/complete/json/divisional_acceptance_audit.json',
            responsive: {
                details: {
                    renderer: function ( api, rowIdx, columns ){
                        return renderChild(api, rowIdx, columns);
                    }
                }
            },
            select: true,  //支持多选
            columns: [
                {"data":"projId",className:"none never"},
                {"data":"projNo"},
                {"data":"projName"},
                {"data":"projStatusDes"},
                {"data":"projectRemark"},
                {"data":"mrAuditLevel",responsivePriority:1}
            ],
            columnDefs: [{
                "targets":0,
                "visible":false
            },{
                "targets":2,
                //长字符串截取方法
                render: $.fn.dataTable.render.ellipsis({
                    length: showLength, 	//截取多少字符（或汉字）
                    end: false	//false从后部开始截取，及从后部开始计数，并裁掉超出的内容,true则相反
                })
            },{
                targets: 4,
                render: function ( data, type, row, meta ) {
/*                    console.info("data---"+row.projectRemark);
                    console.info("dataprojName---"+row.projName);*/
                    if(type === "display"){
                        if(data == '1'){
                            data = '已通过';
                        }else if(data == '2'){
                            data = '审核中';
                        }else if(data == '3'){
                            data = '未通过';
                        }else{
                            data = '未推送'
                        }
                        return data;
                    }else{
                        return data;
                    }
                }
            },{
                "targets":5,
                "render": function ( data, type, row, meta ) {
                    if(type==="display"){
                        var html = getAuditLevelHtml(data,row.level,row.projId);
                        return html;
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
        });
        ;
    }
};

/*var trSelectedBack=function(v, i, index, t, json){

};*/

/**
 * 查询监听方法
 */
var searchMonitor = function(){
    $(".searchBtn").off("click").on("click",function(){
        var url = "#auditSettlementJonintlySign/settlementJonintlySignPopPage";
        //加载弹屏
        $("body").cgetPopup({
            title: '查询',
            content: url,
            accept: searchDone
        });
    });
    //基础条件查询添加监听
    $('#auditSettlementJonintlySignTable_filter input').on('change',function(){
        var applyNo = $('#auditSettlementJonintlySignTable_filter input').val();
        searchData = {};
        searchData.applyNo = applyNo;
        searchData.menuId= menuId;
        $("#auditSettlementJonintlySignTable").cgetData(false, function(){
            //跳转到审核页面
            auditBtnMonitor();
        });
    });
    //基础条件查询添加回车事件
    $('#auditSettlementJonintlySignTable_filter input').on('keyup', function(event) {
        if (event.keyCode == '13') {
            $(this).change();
        }
    });
};

var searchDone = function(){
    searchData = $('#settlementSignForm').serializeJson();
    var applyNo = $('#auditSettlementJonintlySignTable_filter input').val();
    searchData.applyNo = applyNo;
    searchData.menuId= menuId;
    //列表重新加载
    $('#auditSettlementJonintlySignTable').cgetData(false, function(){
        //跳转到审核页面
        auditBtnMonitor();
    });
}
/**
 * 加载审核屏
 */
var auditBtnMonitor = function(){
    $(document).off("click", ".level").on("click", ".level", function(){
        //var paId=trSData.paymentAuditTable.json.paId;
        var isAudit = "0";//未审核过
        if($(this).is(".disabled")) return false;
        if($(this).is(".passed, .refused")){
            isAudit = "1";//已审核过
        }
        var projId = $(this).attr("data-rid");
        var currentLevel = $(this).attr("data-level");
        var data = {"projId":projId,"currentLevel":currentLevel,"isAudit":isAudit};
        $("#ajax-content").cgetContent("auditSettlementJonintlySign/auditPage",data);
    });
};

/**
 * 初始化列表
 */
var auditSettlementJonintlySign = function () {
    "use strict";
    return {
        //main function
        init: function () {
            $.getScript("js/ellipsis.js").done(function(){
                handleAuditSettlementJonintlySign();
            });
        }
    };
}();