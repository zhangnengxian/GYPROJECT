<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-12 -->
        <div class="col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">待报验审核列表</h4>
                </div>
                <div class="panel-body">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <input type="hidden" id="projId" name="projId"/>
                    <table id="gasAuditTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                        <tr>
                            <th width="120">工程编号</th>
                            <th width="120">工程名称</th>
                            <th width="120">工程地点</th>
                            <th width="120">监理公司</th>
                            <th width="120">施工单位</th>
                            <th width="120">报验类型</th>
                            <th width="120">确认操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('报验审核 - 工程管理系统');
    var showLength; //显示长度
    //判断是否是手机端，如果是手机端显示8位，不是则显示10位
    if(_inApk){
        showLength=8;
    }else{
        showLength=10;
    }
    if(!getProjectInfo()){
        loadProjectList();
    }else{
        var projJson = getProjectInfo();
        console.log(projJson);
        $('#projId').val(projJson.projId);					   //工程ID
    }
    var searchData = {};
    searchData.projId = projJson.projId;
    var menuId="110107";
    searchData.menuId=menuId;
    var handleConnectGasAudit = function() {
        "use strict";
        if ($('#gasAuditTable').length !== 0) {
            $('#gasAuditTable').on( 'init.dt',function(){
                //默认选中第一行
                $(this).bindDTSelected(trSelectedBack,true);
                //隐藏遮罩
                $('#gasAuditTable').hideMask();
                $("#gasAuditTable_filter input").attr("placeholder","报验类型");
                //搜索监听
                searchMonitor();
                //跳转链接
                if (crossPageBus) {
                    getSidebarMenu(11, true);
                    checkSidebarMenu(crossPageBus.hash)
                    //跳转后销毁对象
                    crossPageBus = null
                }
            }).on( 'draw.dt', function () {
                auditBtnMonitor();
            }).DataTable({
                language: language_CN,
                lengthMenu: [18],
                dom: 'Bfrtip',
                buttons: [
                    /* { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' } */
                ],
                //启用服务端模式，后台进行分段查询、排序
                serverSide:true,
                ajax: {
                    url: 'auditIns/getDataList',
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
                    {"data":"projNo",responsivePriority:2},
                    {"data":"projName",responsivePriority:4},
                    {"data":"projAddr",responsivePriority:7},
                    {"data":"suName",responsivePriority:8},
                    {"data":"constructionUnit",responsivePriority:9},
                    {"data":"process",responsivePriority:6},
                    {"data":"mrAuditLevel",responsivePriority:1}
                ],
                columnDefs: [
            {
                    "targets": 6,
                    "render": function ( data, type, row, meta ) {
                        if(type==="display"){
                            var html = getAuditLevelHtml(data,row.level,row.pcId,"1301201");
                            return html;
                        }else{
                            return data;
                        }
                    },
                },{
                    "targets":1,
                    //长字符串截取方法
                    render: $.fn.dataTable.render.ellipsis({
                        length: showLength, 	//截取多少字符（或汉字）
                        end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                    })
                },{
                    "targets":2,
                    //长字符串截取方法
                    render: $.fn.dataTable.render.ellipsis({
                        length: showLength, 	//截取多少字符（或汉字）
                        end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                    })
                }
                ]

            });
        }
    };

    /**
     * 弹屏监听方法
     */
    var searchMonitor = function(){
        $(".searchBtn").off("click").on("click",function(){
            var url = "#acceptAudit/surveySearchPage";
            var popoptions = {
                title: '查询',
                content: url,
                accept: searchDone
            };
            //加载弹屏
            $("body").cgetPopup(popoptions);
        });

        $("#gasAuditTable_filter input").on("change",function(){
            searchData.projNo = $("#gasAuditTable_filter input").val();
            //传入false  则不选中行
            $("#gasAuditTable").cgetData(false, function(){
                //跳转到审核页面
                auditBtnMonitor();
            });  //列表重新加载
        });
        //基础条件查询添加回车事件
        $('#gasAuditTable_filter input').on('keyup', function(event) {
            if (event.keyCode == "13") {
                $(this).change();
            }
        });
    };

    var searchDone = function(){
        searchData = $("#searchForm_survey").serializeJson();
        searchData.process = $("#gasAuditTable_filter input").val();
        searchData.menuId=menuId;
        $("#gasAuditTable").cgetData(false, function(){
            //跳转到审核页面
            auditBtnMonitor();
        });  //列表重新加载
    }

    /**
     * 加载审核屏
     */
    var auditBtnMonitor = function(){

        $(document).off("click", ".level").on("click", ".level", function(){
            var isAudit = "0";//未审核过
            if($(this).is(".disabled")) return false;
            if($(this).is(".passed, .refused")){
                isAudit = "1";//已审核过
            }
            var projId = $(this).attr("data-rid");
            var currentLevel = $(this).attr("data-level");
            var data = {"projId":projId,"currentLevel":currentLevel,"isAudit":isAudit};
            $("#ajax-content").cgetContent("auditIns/auditPage",data);
        });

    };


    /**
     * 选中行时，查询详述
     */
    var trSelectedBack = function(v, i, index, t, json){
    }

    /**
     * 初始化列表
     */
    $.getScript("js/ellipsis.js").done(function(){
        handleConnectGasAudit();
    })

</script>
<!-- ================== END PAGE LEVEL JS ================== -->