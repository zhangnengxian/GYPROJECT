<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div id="content" class="content">
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">待审协议列表</h4>
                </div>
                <div class="panel-body">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <div class="toolBtn f-r p-b-10">
                        <button type="button" onclick="searchClick()" class="btn btn-info btn-sm">查询</button>
                    </div>
                    <table id="supplementAuditTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                        <tr>
                            <th>协议ID</th>
                            <th>协议编号</th>
                            <th>工程编号</th>
                            <th>工程名称</th>
                            <th>工程类型</th>
                            <th>出资方式</th>
                            <th>签订日期</th>
                            <th>审核</th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>



<script>
    App.restartGlobalFunction();
    App.setPageTitle('智能合同协议审批 - 工程管理系统');
   var menuId="1104102";
    var searchData={};
    
    $(function () {//初始化列表
        $.getScript("js/ellipsis.js").done(function(){
            handleSupplementAudit();
        })
    })




    var handleSupplementAudit = function() {
        "use strict";
        if ($('#supplementAuditTable').length !== 0) {
            $('#supplementAuditTable').on( 'init.dt',function(){
                $(this).bindDTSelected(trSelectedBack,false);//默认选中第一行
                $('#supplementAuditTable').hideMask(); //隐藏遮罩
                $("#supplementAuditTable_filter input").attr("placeholder","工程编号");

                searchMonitor();//搜索监听

                if (crossPageBus) {//跳转链接
                    getSidebarMenu(11, true);
                    checkSidebarMenu(crossPageBus.hash)
                    crossPageBus = null//跳转后销毁对象
                }
            }).on( 'draw.dt', function () {//加载审核屏监听
                auditBtnMonitor();

            }).DataTable({
                language: language_CN,
                lengthMenu: [18],
                dom: 'Bfrtip',
                buttons: [],
                //启用服务端模式，后台进行分段查询、排序
                serverSide:true,
                ajax: {
                    url: 'intelligentSupplementAudit/queryToAuditSupplement',
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
                    {"data":"isId",responsivePriority:1,"visible":false},
                    {"data":"isNo",responsivePriority:1},
                    {"data":"projNo",responsivePriority:7},
                    {"data":"projName",responsivePriority:3},
                    {"data":"projectTypeDes",responsivePriority:4},
                    {"data":"contributionModeDes",responsivePriority:5},
                    {"data":"signDate",responsivePriority:7},
                    {"data":"mrAuditLevel",responsivePriority:2},
                ],
                columnDefs: [{
                    "targets": 7,
                    "render": function ( data, type, row, meta ) {
                        if(type==="display"){
                            var html = getAuditLevelHtml(data,row.level,row.isId,menuId);
                            return html;
                        }else{
                            return data;
                        }
                    },
                    "orderable":false
                },{
                    "targets":4,
                    "orderable":false
                },{
                    "targets":5,
                    "orderable":false
                },{
                    "targets":3,
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
            });
        }
    };



    var trSelectedBack=function (v, i, index, t, json) {}


    var searchMonitor=function () {
        $('#supplementAuditTable_filter input').on('keyup', function(event) {//基础条件查询添加回车事件
            if (event.keyCode == "13") {
                for(var key in searchData){//清空查询条件赋予对象值
                    delete searchData[key];
                }
                searchData.projNo=$("#supplementAuditTable_filter input").val();
                $("#supplementAuditTable").cgetData(true);
            }
        });
    }

    function searchClick(){
        $('body').cgetPopup({//加载弹屏
            title: '查询',
            content: '#intelligentMeterCon/projectSearchPopPage',
            accept: searchDone
        });
    }

    var searchDone = function(){//查询弹出屏，点击确定后
        searchData = $('#searchForm_imc').serializeJson();
        //列表重新加载
        $("#supplementAuditTable").cgetData(true);
    }



    /**
     * 加载审核屏
     */
    var auditBtnMonitor = function(){
        $(document).off("click", ".level").on("click", ".level", function(){
            var isAudit = "0";//未审核过
            if($(this).is(".disabled")) return false;
            if($(this).is(".passed, .refused"))isAudit = "1";//已审核过
            var currentLevel = $(this).attr("data-level");
            var isId = $(this).attr("data-rid");
            var data = {"isId":isId,"currentLevel":currentLevel,"isAudit":isAudit};
            $("#ajax-content").cgetContent("intelligentSupplementAudit/auditPage",data);
        });

    };


</script>
