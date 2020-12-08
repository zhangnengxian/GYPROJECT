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
                    <h4 class="panel-title">工程列表</h4>
                </div>

                <div class="panel-body">

                    <div class="mask-html text-center">
                        <div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>

                    <div class="tab-content">
                        <div class="tab-pane fade active in btn-top active">
                            <div class="toolBtn f-r p-b-10">
                                <button type="button" onclick="searchClick()" class="btn btn-info btn-sm">查询</button>
                            </div>
                            <table id="projectTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
                                <thead>
                                <tr>
                                    <th>工程ID</th>
                                    <th>工程编号</th>
                                    <th>工程名称</th>
                                    <th>状态</th>
                                    <th>类型</th>
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
			        <h4 class="panel-title">接口列表</h4>
			    </div>
                <div class="panel-body">

                    <div class="mask-html text-center">
                        <div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>

                    <div class="tab-content">
                        <div class="tab-pane fade active in btn-top active">
                            <div class="toolBtn f-r p-b-10">
                                <button type="button" onclick="webSearchClick()" class="btn btn-info btn-sm">查询</button>
                            </div>
                            <table id="webserviceSetTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>编号</th>
                                    <th>名称</th>
                                    <th>状态</th>
                                    <th>公司</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <div id="synchronizedBtn"></div>
                    </div>

                </div>
			</div>
        </div>

    </div>
</div>




<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程信息 - 工程项目管理系统 ');

    /**
     * 初始化工程列表
     */
    $(function(){
        $.getScript("js/ellipsis.js").done(function(){
            handleProjectTable();
            handleWebserviceSetTable();
        });
    });

    var searchData={};


    /**
     * 列表初始化加载数据
     */
    function handleProjectTable(){
        $('#projectTable').on( 'init.dt',function(){
            $(this).bindDTSelected(trSelectedBack,true); //默认选中第一行
             $("#projectTable_filter input").attr("placeholder","工程编号搜索");
            $('#projectTable').hideMask(); //隐藏遮罩
            searchMonitor();

        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [],
            serverSide:true,//启用服务端模式，后台进行分段查询、排序
            ajax: {
                url: 'projectModify/queryProjectMap',
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
                {'data':'projId',"visible":false},
                {'data':'projNo'},
                {'data':'projName'},
                {'data':'projStatusDes'},
                {'data':'projectTypeDes'},
            ],
            columnDefs: [{
                "targets":2,
                render: $.fn.dataTable.render.ellipsis({
                    length:12, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            },{
                "targets":4,
                render: $.fn.dataTable.render.ellipsis({
                    length:3, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            }],
            fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {}
        });
    };


    /**
     *工程编号搜索
     */
    var searchMonitor=function () {
        $('#projectTable_filter input').on('keyup', function(event) {//基础条件查询添加回车事件
            if (event.keyCode == "13") {
                for(var key in searchData){//清空查询条件赋予对象值
                    delete searchData[key];
                }
                searchData.projNo=$("#projectTable_filter input").val();
                $("#projectTable").cgetData(true);
            }
        });
    };



    /**
     *选中行时，查询详述
     */
    var trSelectedBack = function(v, i, index, t, json){

    };



    /**
     * 条件查询弹出框
     */
    function searchClick(){
        var tipsHtml="<span style='background: linear-gradient(to right, #c216dc, #0aff32);-webkit-background-clip: text;color: transparent;'>条件查询</span>";
        $('body').cgetPopup({
            title: tipsHtml,
            content: '#projectModify/projctPopPage',
            accept:searchDone
        });
    }


    /**
     * 条件查询
     */
    var searchDone=function(){
        searchData= $("#projectSearchForm").serializeJson();
        searchData.projNo=$("#projectTable_filter input").val();
        searchData.menuId="201904";
        $("#projectTable").cgetData(true);
    };







    /**
     * 列表初始化加载数据
     */
    function handleWebserviceSetTable(){
        $('#webserviceSetTable').on( 'init.dt',function(){
            $(this).bindDTSelected(webTrSelectedBack,true); //默认选中第一行
            $('#webserviceSetTable_panel_box').cgetContent('projectModify/viewRightPage',{},viewRightCallback);//加载右侧页面
            $("#webserviceSetTable_filter input").attr("placeholder","编号搜索");
            $('#webserviceSetTable').hideMask(); //隐藏遮罩
            webSearchMonitor();
        }).DataTable({
            language: language_CN,
            lengthMenu: [16],
            dom: 'Bfrtip',
            buttons: [],
            serverSide:true,//启用服务端模式，后台进行分段查询、排序
            ajax: {
                url: 'synchronized/querywebserviceSetMap',
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
                {'data':'wsId',"visible":false},
                {'data':'webServiceNo'},
                {'data':'webServiceName'},
                {'data':'webServiceFlag'},
                {'data':'corpId'},
                {'data':'webServiceFlag'},
            ],
            columnDefs: [{
                "targets":3,
                render: function (data, type, row, meta) {
                    if (row.webServiceFlag==='1'){
                       return "开启";
                    }
                    return "关闭";
                }
            },{
                "targets":4,
                render: $.fn.dataTable.render.ellipsis({
                    length:18, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            },{
                targets:5,
                render: function (data, type, row, meta) {
                    var  tdcon="";
                    if (row.webServiceFlag==='1'){
                        tdcon='<a class="m-l-5 btn btn-danger btn-xs" onclick="switchClick(\''+row.wsId+'\')">关闭</a>';
                    }else{
                        tdcon='<a class="m-l-5 btn btn-success btn-xs" onclick="switchClick(\''+row.wsId+'\')">开启</a>';
                    }
                    return tdcon;
                }
            }],
            fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {}
        });
    };


    /**
     *编号搜索
     */
    var webSearchMonitor=function () {
        $('#webserviceSetTable_filter input').on('keyup', function(event) {//基础条件查询添加回车事件
            if (event.keyCode == "13") {
                for(var key in searchData){//清空查询条件赋予对象值
                    delete searchData[key];
                }
                searchData.webServiceNo=$("#webserviceSetTable_filter input").val();
                $("#webserviceSetTable").cgetData(true);
            }
        });
    };


    /**
     *选中行时，查询详述
     */
    var webTrSelectedBack = function(v, i, index, t, json){
        $("#synchronizedBtn button").remove();
        $("#synchronizedBtn").append("<button type=\"button\" onmouseover='setColor(this)' onmouseout='removeColor(this)' onclick=\"synchronizedclick()\" class=\"btn  btn-sm\" style=\"width: 100%;border:0px;background: -webkit-linear-gradient(left,white,white,"+getColor()+","+getColor()+", "+getColor()+", "+getColor()+", "+getColor()+",white,white); \"><strong style='color:white'>选择工程->选择接口->点我开始远程调用同步数据</strong s></button>")
    };



    /**
     * 条件查询弹出框
     */
    function webSearchClick(){
        var tipsHtml="<span style='background: linear-gradient(to right, #c216dc, #0aff32);-webkit-background-clip: text;color: transparent;'>条件查询</span>";
        $('body').cgetPopup({
            title: tipsHtml,
            content: '#synchronized/webPopPage',
            accept:webSearchDone
        });
    }


    /**
     * 条件查询
     */
    var webSearchDone=function(){
        searchData= $("#webSearchForm").serializeJson();
        $("#webserviceSetTable").cgetData(true);
    };


    function switchClick(wsId) {

        Base.subimt("synchronized/switchStatus","POST",{wsId:wsId},function (data) {
            $("#webserviceSetTable").cgetData(true);
        })
    }



    function synchronizedclick(){
        var projData= trSData.projectTable.json;
        var webData= trSData.webserviceSetTable.json;

        if (projData==undefined){
            alertInfo("请选择工程!");
            return false;
        }
        if (webData==undefined){
            alertInfo("请选择要同步的接口!");
            return false;
        }

        if (webData.localUrl==""){
            alertInfo("没找到对应的URL(可在webservice_set表中配置对应的URL，然后写自己的controller调用要同步的远程接口即可)");
            return false;
        }

        Base.subimt(webData.localUrl,"POST",{projId:projData.projId,interfaceNo:webData.webServiceNo},function (data) {
            alertInfo(data)
        })

    };













    var viewRightCallback=function (){};












    function setColor(button) {$(button).css('background', '-webkit-linear-gradient(left,white,white,'+getColor()+','+getColor()+','+getColor()+', '+getColor()+', '+getColor()+', '+getColor()+',white,white)');}
    function removeColor(button) {$(button).css('background', '-webkit-linear-gradient(left,white,white,'+getColor()+','+getColor()+','+getColor()+', '+getColor()+', '+getColor()+', '+getColor()+',white,white)');}
    function getColor(){var r=Math.floor(Math.random()*120)+106,g=Math.floor(Math.random()*120)+106,b=Math.floor(Math.random()*120)+106;return "rgb("+r+','+g+','+b+")";};

</script>
