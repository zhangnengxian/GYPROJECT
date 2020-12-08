<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content" class="content">
    <div class="row">
        <div class="col-sm-6 col-xs-12">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">问题单据列表</h4>
                </div>

                <div class="panel-body">

                    <div class="mask-html text-center">
                        <div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>

                    <div class="tab-content">
                        <div class="tab-pane fade active in btn-top active">
                            <div class="toolBtn f-r p-b-10">
                                <button type="button" onclick="searchClick('条件查询','searchDone')" class="btn btn-info btn-sm">查询</button>
                                <button type="button" onclick="exportClick()" class="btn btn-primary btn-sm">导出</button>
                                <button type="button" onclick="commonClick('add')" class="btn btn-success btn-sm">新增</button>
                                <button type="button" onclick="commonClick('modify')" class="btn btn-warning btn-sm">修改</button>
                                <button type="button" onclick="commonClick('del')" class="btn btn-danger btn-sm">删除</button>

                            </div>
                            <table id="problemDocumentTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
                                <thead>
                                <tr>
                                    <th>问题单据ID</th>
                                    <th>公司名称</th>
                                    <th>类型</th>
                                    <th>紧急</th>
                                    <th>提出人</th>
                                    <th>处理人</th>
                                    <th>状态</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" >
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <h4 class="panel-title">操作区</h4>
			    </div>
			    <div class="panel-body" id="problemDocument_panel_box"></div>
			</div>
        </div>

    </div>
</div>




<script>
    App.restartGlobalFunction();
    App.setPageTitle('问题处理单据 - 工程项目管理系统 ');

    /**
     * 初始化工程列表
     */
    $(function(){
        $.getScript("js/ellipsis.js").done(function(){
            handleProblemDocumentTable();
        });
    });

    var searchData={};

    /**
     * 加载问题单据列表
     */
    function handleProblemDocumentTable(){
        $('#problemDocumentTable').on( 'init.dt',function(){
            $(this).bindDTSelected(trSelectedBack,true); //默认选中第一行
            $('#problemDocument_panel_box').cgetContent('problemDocument/viewRightPage',{},viewRightCallback);//加载右侧页面
             $("#problemDocumentTable_filter input").attr("placeholder","关键字搜索");
            $('#problemDocumentTable').hideMask(); //隐藏遮罩
            searchMonitor();

        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [],
            serverSide:true,//启用服务端模式，后台进行分段查询、排序
            ajax: {
                url: 'problemDocument/queryProblemDocumentList',
                type:'post',
                data: function(d){$.each(searchData,function(i,k){ d[i] = k;});},
                dataSrc: 'data'
            },
            select: true,  //支持多选
            columns: [
                {'data':'pdId',"visible":false},
                {'data':'corpName'},
                {'data':'problemTypeDesc'},
                {'data':'emergencyLevelDesc'},
                {'data':'proposer'},
                {'data':'preprocessorCN'},
                {'data':'problemStateDesc',className:"none never","createdCell": function (td, cellData, rowData, row, col) {
                        if(cellData=='待执行'){
                            $(td).parent().children().css("color", "rgb(255, 0, 0)");
                        }else  if(cellData=='执行中'){
                            $(td).parent().children().css("color", "rgb(0, 255, 0)");
                        }else  if(cellData=='待测试'){
                            $(td).parent().children().css("color", "rgb(0, 0, 255)");
                        }else  if(cellData=='待确认'){
                            $(td).parent().children().css("color", "rgb(255, 0, 255)");
                        }else  if(cellData=='待更新'){
                            $(td).parent().children().css("color", "rgb(0, 255, 255)");
                        }else  if(cellData=='已作废'){
                            $(td).parent().children().css("color", "rgb(200, 200, 200)");
                        }
                    }
                }
            ],
            columnDefs: [{
                "targets":1,
                render: $.fn.dataTable.render.ellipsis({
                    length: 8, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            },{
                "targets":4,
                render: $.fn.dataTable.render.ellipsis({
                    length:4, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            }],
            fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {}
        });
    };


    /**
     *关键字搜索
     */
    var searchMonitor=function () {
        $('#problemDocumentTable_filter input').on('keyup', function(event) {//基础条件查询添加回车事件
            if (event.keyCode == "13") {
                for(var key in searchData){//清空查询条件赋予对象值
                    delete searchData[key];
                }
                searchData.commonField=$("#problemDocumentTable_filter input").val();
                $("#problemDocumentTable").cgetData(true);
            }
        });
    };



    /**
     *选中行时，查询详述
     */
    var trSelectedBack = function(v, i, index, t, json){
        searchData.pdId=json.pdId;
        $('.editBtn').addClass("hidden");
        $("#problemDocumentForm").toggleEditState(false);//切换表单可编辑

        //加载右侧页面
        $('#problemDocument_panel_box').cgetContent('problemDocument/viewRightPage',{},function () {
            t.cgetDetail('problemDocumentForm','problemDocument/viewProblemDocumentDetail','',detailCallback);
        });

    };



    function commonClick(paramValue){

         if (paramValue=='del'){//删除
            if (searchData.pdId==""){
                alert("请选择要删除的记录");
            }else{
                var tipsHtml="<span style='background: linear-gradient(to right, red, blue);-webkit-background-clip: text;color: transparent;'>是否确认要删除此条记录!</span>"
                $("body").cgetPopup({
                    title: "提示信息",
                    content: tipsHtml,
                    accept:confirmBtn,
                    icon: "fa-warning",
                });
            }
        }




        $("#problemDocumentForm").toggleEditState(true);//切换表单可编辑
        $('.editBtn').removeClass("hidden");

        if (paramValue=='add'){//新增
            $("#problemDocumentForm")[0].reset();//重置表单
           $('#pdId').val("");
            $(".w-e-text").html("");

        }else if (paramValue=='modify'){//修改
            //$(".controlEdit").attr("disabled",true);
        }

    }




    /**
     * 删除
     */
    var confirmBtn=function () {
        Base.subimt('problemDocument/deleteProblemDocumentById','POST',{pdId:searchData.pdId},function (data) {
            $("#problemDocumentTable").cgetData(true);
        });
    };



    /**
     * 条件导出弹出框
     */
    function exportClick() {
        searchClick("条件导出",'exportDone');
    }



    /**
     * 条件查询弹出框
     */
    function searchClick(themeTips,confirmBtn){
        var tipsHtml="<span style='background: linear-gradient(to right, red, blue);-webkit-background-clip: text;color: transparent;'>"+themeTips+"</span>";
        $('body').cgetPopup({
            title: tipsHtml,
            content: '#problemDocument/problemDocumentPopPage',
            accept:confirmBtn
        });
    }




    /**
     * 条件查询
     */
    var searchDone=function(){
        searchData=$("#problemDocumentSearchForm").serializeJson();
        console.log(searchData);
        $("#problemDocumentTable").cgetData(true);
    };



    /**
     * 导出
     */
    var exportDone=function(){
        searchData=$("#problemDocumentSearchForm").serializeJson();
        $("#problemDocumentSearchForm")[0].reset();//重置表单
        var param="corpId="+searchData.corpId
            +"&problemTypeCode="+searchData.problemTypeCode
            +"&problemStateCode="+searchData.problemStateCode
            +"&proposer="+searchData.proposer
            +"&level2MenuId="+searchData.level2MenuId
            +"&menuId="+searchData.menuId
            +"&preprocessor="+searchData.preprocessor
            +"&emergencyLevelCode="+searchData.emergencyLevelCode
            +"&actualHandler="+searchData.actualHandler
            +"&registLtTime="+searchData.registLtTime
            +"&registGtTime="+searchData.registGtTime
            +"&preLtTime="+searchData.preLtTime
            +"&preGtTime="+searchData.preGtTime
            +"&actualLtTime="+searchData.actualLtTime
            +"&actualGtTime="+searchData.actualGtTime;

        location.href="problemDocument/exportExcel?"+param;
    };


    var viewRightCallback=function () {};
    var detailCallback=function () {
    };

</script>
