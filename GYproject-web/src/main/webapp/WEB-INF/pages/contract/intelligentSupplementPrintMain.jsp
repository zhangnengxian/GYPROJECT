<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content" class="content">
    <div class="row">
        <div class="col-sm-6 col-xs-12">
            <div class="panel panel-inverse tabs-mixin">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">协议列表</h4>
                </div>

                <div class="panel-body">
                    <div class="mask-html text-center">
                        <div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>

                    <div class="tab-content">
                        <div class="tab-pane fade active in btn-top active" >
                            <input type="hidden" id="imgUrl" value="${imgUrl}"/>
                            <div class="toolBtn f-r p-b-10">
                                <button type="button" onclick="searchClick()" class="btn btn-info btn-sm">查询</button>
                                <button type="button" onclick="signClick()" class="btn btn-success btn-sm">标记</button>
                            </div>
                            <table id="supplementTable" class="table table-striped table-bordered nowrap" width="100%">
                                <thead>
                                <tr>
                                    <th>协议ID</th>
                                    <th>协议编号</th>
                                    <th>工程编号</th>
                                    <th>工程名称</th>
                                    <th>签订日期</th>
                                    <th>调整金额</th>
                                    <th>工程ID</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">预览区</h4>
                </div>
                <div class="panel-body">
                    <div class="iframe-big-box ">
                        <div class="iframe-report-box ">
                            <iframe id="mainFrm" class="iframe-report" style="width: 798px; height: 1200px;border:0; overflow-y:auto;" scrolling="no"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>




<script>
    App.restartGlobalFunction();
    App.setPageTitle('智能补充协议打印 - 工程项目管理系统 ');
    var searchData={isPrint:1};

    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
        var fr = $(".iframe-report");
        for(var i=0; i<fr.length; i++){
            fr.eq(i).rescaleReportPanel();
        }
    }


    /**
     * 初始化工程列表
     */
    $(function(){
        $.getScript("js/ellipsis.js").done(function(){
            handleSupplementTable();
            showReport({cptUrl:"intelligentSupplement.cpt",isId:null});
        });
    });

    //协议列表初始化
    var handleSupplementTable = function () {
        $('#supplementTable').on( 'init.dt',function(){
            $(this).bindDTSelected(trSelectedBack,true); //默认选中第一行
            $("#supplementTable_filter input").attr("placeholder","协议号搜索");
            $('#supplementTable').hideMask(); //隐藏遮罩
            searchMonitor();

        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [],
            serverSide:true,//启用服务端模式，后台进行分段查询、排序
            ajax: {
                url: 'intelligentSupplementPrint/queryAlreadyAuditSupplement',
                type:'post',
                data: function(d){$.each(searchData,function(i,k){ d[i] = k;});},
                dataSrc: 'data'
            },
            select: true,  //支持多选
            columns: [
                {'data':'isId',"visible":false},
                {'data':'isNo'},
                {'data':'projNo'},
                {'data':'projName'},
                {'data':'signDate'},
                {'data':'isAmount'},
                {'data':'projId',"visible":false},
            ], columnDefs: [{
                "targets":3,
                render: $.fn.dataTable.render.ellipsis({
                    length: 16, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            }],
            fnRowCallback: function( nRow1, aData1, iDisplayIndex1, iDisplayIndexFull1 ) {}
        });
    }



    var trSelectedBack=function (v, i, index, t, json) {
        Base.subimt('intelligentSupplementPrint/queryCptUrl', 'POST', {projId:json.projId,menuId:1104103}, function(data) {
            json.cptUrl=cptUrl;
            showReport(json);
        })
    }


    var basePath=window.location.protocol + '//' + window.location.host;
    function showReport(json) {
        var src = basePath+"/ReportServer?reportlet=contract/"+json.cptUrl+"&isId="+json.isId+"&imgUrl="+$("#imgUrl").val();
        $("#mainFrm").attr("src",src);
    }




    function signClick() {//标记
        var json= trSData.supplementTable.json;
        json.isPrint=0;//已打印
        console.log(json)
        Base.subimt('intelligentSupplement/saveIntelligentSupplement', 'POST', JSON .stringify(json), function(data) {
            var content = data ? "标记成功！" : "标记失败！"; tips(content);
            $("#supplementForm").hideMask();
            $("#supplementTable").cgetData(true);//列表重新加载
        })
    }







    var searchMonitor=function () {
        $('#supplementTable_filter input').on('keyup', function(event) {//基础条件查询添加回车事件
            if (event.keyCode == "13") {
                for(var key in searchData){//清空查询条件赋予对象值
                    delete searchData[key];
                }

                for(var key in searchData) {//清空对象值
                    delete searchData[key];
                }
                searchData.isNo=$("#supplementTable_filter input").val();
                $("#supplementTable").cgetData(true);
            }
        });
    }



    function searchClick(){
        $('body').cgetPopup({//加载弹屏
            title: '查询',
            content: '#intelligentMeterConPrint/imcSearchPopPage',
            accept: searchDone
        });
    }

    var searchDone = function(){//查询弹出屏，点击确定后
        searchData = $('#searchForm_imc').serializeJson();
        $("#supplementTable").cgetData(true);
    }





    function tips(content) {
        var myoptions = {
            title: "提示信息",
            content: content,
            accept: popClose,
            chide: true,
            icon: "fa-check-circle"
        };
        $("body").cgetPopup(myoptions);
    }

</script>
