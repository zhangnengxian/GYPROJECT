<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<div id="content" class="content">

    <div class="row">

        <div class="col-sm-12 col-xs-12">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">问题单统计</h4>
                </div>

                <div class="panel-body">
                    <div class="mask-html text-center">
                        <div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>

                    <div class="toolBtn f-r p-b-10">
                        <button type="button" onclick="searchClick('条件查询','searchDone')" class="btn btn-info btn-sm">查询 </button>
                        <button type="button" onclick="exportClick()" class="btn btn-primary btn-sm">导出</button>
                    </div>

                    <table id="problemDocumentCountTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th>公司名称</th>
                                <th>优化数</th>
                                <th>Bug数</th>
                                <th>指导数</th>
                                <th>权限调整</th>
                                <th>数据修改</th>
                                <th>新增人</th>
                                <th>数据配置</th>
                                <th>数据过滤</th>
                                <th>新需求</th>
                                <th>其他</th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<script>
    App.restartGlobalFunction();
    App.setPageTitle('问题处理单据统计 - 工程项目管理系统 ');

    /**
     * 初始化工程列表
     */
    $(function () {
        $.getScript("js/ellipsis.js").done(function () {
            handleproblemDocumentCountTable();
        });
    });

    var searchData = {};

    /**
     * 加载问题单据统计列表
     */
    function handleproblemDocumentCountTable() {
        $('#problemDocumentCountTable').on('init.dt', function () {
            $('#problemDocumentCountTable').hideMask(); //隐藏遮罩
        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [],
            serverSide: true,//启用服务端模式，后台进行分段查询、排序
            ajax: {
                url: 'problemDocumentCount/findProblemTypeConut',
                type:'post',
                data: function (d) {
                    $.each(searchData, function (i, k) {
                        d[i] = k;
                    });
                },
                dataSrc: 'data'
            },
            select: true,  //支持多选
            columns: [
                {'data': 'corpName'},
                {'data': 'sysOpzCount'},
                {'data': 'sysBugCount'},
                {'data': 'guidanceCount'},
                {'data': 'authorityAdjustConut'},
                {'data': 'dataModifiyCount'},
                {'data': 'addStaffCount'},
                {'data': 'dataConfigureCount'},
                {'data': 'dataFilterCount'},
                {'data': 'newReqCount'},
                {'data': 'otherCount',className:"none never","createdCell": function (td, cellData, rowData, row, col) {
                        $(td).parent().children().css("background", getColor());
                    }
                }
            ],
            columnDefs: [{
                "targets": 0,
                render: $.fn.dataTable.render.ellipsis({
                    length: 18, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            }],
            fnRowCallback: function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            }
        });
    };



    /**
     * 条件导出弹出框
     */
    function exportClick() {
        searchClick("条件导出", 'exportDone');
    }


    /**
     * 条件查询弹出框
     */
    function searchClick(themeTips, confirmBtn) {
        var tipsHtml = "<span style='background: linear-gradient(to right, red, blue);-webkit-background-clip: text;color: transparent;'>" + themeTips + "</span>";
        $('body').cgetPopup({
            title: tipsHtml,
            content: '#problemDocument/problemDocumentPopPage',
            accept: confirmBtn
        });
    }


    /**
     * 条件查询
     */
    var searchDone = function () {
        searchData = $("#problemDocumentSearchForm").serializeJson();
        $("#problemDocumentCountTable").cgetData(true);
    };

    /**
     * 导出
     */
    var exportDone = function () {
        searchData = $("#problemDocumentSearchForm").serializeJson();
        $("#problemDocumentSearchForm")[0].reset();//重置表单
        var param = "corpId=" + searchData.corpId
            + "&problemTypeCode=" + searchData.problemTypeCode
            + "&problemStateCode=" + searchData.problemStateCode
            + "&proposer=" + searchData.proposer
            + "&preprocessor=" + searchData.preprocessor
            + "&emergencyLevelCode=" + searchData.emergencyLevelCode
            + "&actualHandler=" + searchData.actualHandler
            + "&ltTime="+searchData.ltTime
            + "&gtTime="+searchData.gtTime;
        location.href = "problemDocumentCount/exportExcel?" + param;
        //window.open(url);
    };





    var getColor =function(){
        var r=Math.floor(Math.random()*56)+200;
        var g=Math.floor(Math.random()*56)+200;
        var b=Math.floor(Math.random()*56)+200;
        return "rgb("+r+','+g+','+b+")";
    };
</script>
