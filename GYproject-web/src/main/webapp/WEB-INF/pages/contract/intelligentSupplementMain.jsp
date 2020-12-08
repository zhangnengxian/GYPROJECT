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
                    <ul class="nav nav-tabs ">
                        <li class="active"><a href="#contractList" data-toggle="tab" id="contractTab">智能合同列表</a></li>
                        <li class=""><a href="#supplementList" data-toggle="tab" id="supplementTab">协议列表</a></li>
                    </ul>
                </div>

                <div class="panel-body">
                    <div class="mask-html text-center">
                        <div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>

                    <div class="tab-content">
                        <div class="tab-pane fade active in btn-top active" id="contractList" >
                            <div class="toolBtn f-r p-b-10">
                                <button type="button" onclick="conSearchClick()" id="conSearchBtn" class="btn btn-info btn-sm hidden">查询</button>
                                <button type="button" onclick="addClick()" id="addBtn" class="btn btn-success btn-sm hidden">新增</button>
                            </div>
                            <table id="contractTable" class="table table-hover table-striped table-bordered nowrap"  width="100%" data-attach-table="all">
                                <thead>
                                <tr>
                                    <th>智能表合同ID</th>
                                    <th>工程ID</th>
                                    <th>工程编号</th>
                                    <th>工程名称</th>
                                    <th>工程状态</th>
                                    <th>合同金额</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="tab-pane fade in btn-top" id="supplementList">
                            <div class="toolBtn f-r p-b-10">
                                <button type="button" onclick="signClick()" id="signBtn" class="btn btn-success btn-sm hidden">签订</button>
                                <button type="button" onclick="commonClick(2)" id="pushBtn" class="btn btn-success btn-sm hidden">推送</button>
                                <button type="button" onclick="modifyClick(5)" id="modifyBtn" class="btn btn-success btn-sm hidden">修改申请</button>
                                <button type="button" onclick="commonClick(-1)" id="deleteBtn" class="btn btn-danger btn-sm hidden">作废</button>
                            </div>
                            <table id="supplementTable" class="table table-striped table-bordered nowrap" width="100%">
                                <thead>
                                <tr>
                                    <th>协议ID</th>
                                    <th>协议编号</th>
                                    <th>工程名称</th>
                                    <th>变更日期</th>
                                    <th>调整金额</th>
                                    <th>签订状态Code</th>
                                    <th>签订状态</th>
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
            <div class="panel panel-inverse" >
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">操作区</h4>
                </div>
                <div class="panel-body" id="supplement_panel_box"></div>
            </div>
        </div>

    </div>
</div>




<script>
    App.restartGlobalFunction();
    App.setPageTitle('智能补充协议 - 工程项目管理系统 ');
    var conSearchData={};
    var supSearchData={};

    /**
     * 初始化工程列表
     */
    $(function(){
        $.getScript("js/ellipsis.js").done(function(){
            handleContractTable();
            $("#contractTab,#supplementTab").on("shown.bs.tab",function(){
                if($(this).is($('#contractTab'))){ //智能合同列表Tab
                    if(!$.fn.DataTable.isDataTable('#contractTable')){
                        handleContractTable();
                    }else{
                        $('#contractTable').cgetData(true);
                        $('#supplement_panel_box').cgetContent('intelligentSupplement/contractViewPage',{},function () {});//加载右侧页面
                    }

                }else{//协议列表Tab
                    limitBtn({isStatus:null})
                    if(!$.fn.DataTable.isDataTable('#supplementTable')){
                        handleSupplementTable();
                    }else{
                        $('#supplementTable').cgetData(true);
                        $('#supplement_panel_box').cgetContent('intelligentSupplement/supplementViewPage',{},function () {});//加载右侧页面
                    }
                }
            })
        });
    });

/*******************************************************智能表合同标签页*************************************************************************/
    //智能合同列表初始化
    var handleContractTable = function () {
        $('#contractTable').on( 'init.dt',function(){
            $(this).bindDTSelected(cTrSelectedBack,true); //默认选中第一行
            $('#supplement_panel_box').cgetContent('intelligentSupplement/contractViewPage',{},function () {});//加载右侧页面
            $("#contractTable_filter input").attr("placeholder","工程编号搜索");
            $('#contractTable').hideMask(); //隐藏遮罩
            contractSearchMonitor();

        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [],
            serverSide:true,//启用服务端模式，后台进行分段查询、排序
            ajax: {
                url: 'intelligentSupplement/queryIntelligentContractList',
                type:'post',
                data: function(d){$.each(conSearchData,function(i,k){ d[i] = k;});},
                dataSrc: 'data'
            },
            select: true,  //支持多选
            columns: [
                {'data':'imcId',"visible":false},
                {'data':'projId',"visible":false},
                {'data':'projNo'},
                {'data':'projName'},
                {'data':'projStatusDes'},
                {'data':'totalCost'},
            ],
            columnDefs: [{
                "targets":3,
                render: $.fn.dataTable.render.ellipsis({
                    length: 20, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            }],
            fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {}
        });

    }


    /**
     *智能表合同选中行时，查询详述
     */
    var cTrSelectedBack = function(v, i, index, t, json){
        $("#conSearchBtn").removeClass("hidden").html("查询");
        $("#addBtn").removeClass("hidden").html("增加");

        supSearchData.projId=json.projId;
        //加载右侧页面查详细
        $('#supplement_panel_box').cgetContent('intelligentSupplement/contractViewPage',{},function () {
            t.cgetDetail('supplementApplyForm','intelligentSupplement/viewContractDetail','',function () {});
        });
    };


    function addClick() {
        var json= trSData.contractTable.json;
        if (json.imcId!=null && json.imcId!="") {
            $("#supplementApplyForm").toggleEditState(true);//切换表单可编辑
            $(".editBtn").removeClass("hidden");
        }
    }


    var contractSearchMonitor=function () {
        $('#contractTable_filter input').on('keyup', function(event) {//基础条件查询添加回车事件
            if (event.keyCode == "13") {
                for(var key in conSearchData){//清空查询条件赋予对象值
                    delete conSearchData[key];
                }
                conSearchData.projNo=$("#contractTable_filter input").val();
                $("#contractTable").cgetData(true);
            }
        });
    }



    function conSearchClick(){
        $('body').cgetPopup({//加载弹屏
            title: '查询',
            content: '#intelligentMeterCon/projectSearchPopPage',
            accept: searchDone
        });
    }

    var searchDone = function(){//查询弹出屏，点击确定后
        conSearchData = $('#searchForm_imc').serializeJson();
        //列表重新加载
        $("#contractTable").cgetData(true);
    }








/*************************************************协议标签页************************************************************************/
    //协议列表初始化
    var handleSupplementTable = function () {
        $('#supplementTable').on( 'init.dt',function(){
            $(this).bindDTSelected(sTrSelectedBack,true); //默认选中第一行
            $('#supplement_panel_box').cgetContent('intelligentSupplement/supplementViewPage',{},function () {});//加载右侧页面
            $("#supplementTable_filter input").attr("placeholder","协议号搜索");
            $('#supplementTable').hideMask(); //隐藏遮罩
            supSearchMonitor();

        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [],
            serverSide:true,//启用服务端模式，后台进行分段查询、排序
            ajax: {
                url: 'intelligentSupplement/queryIntelligentSupplementList',
                data: function(d){$.each(supSearchData,function(i,k){ d[i] = k;});},
                dataSrc: 'data'
            },
            select: true,  //支持多选
            columns: [
                {'data':'isId',"visible":false},
                {'data':'isNo'},
                {'data':'projName'},
                {'data':'changeTime'},
                {'data':'isAmount'},
                {'data':'isStatus',"visible":false},
                {'data':'isStatusDes'},
                {'data':'projId',"visible":false},
            ], columnDefs: [{
                "targets":2,
                render: $.fn.dataTable.render.ellipsis({
                    length: 16, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            }],
            fnRowCallback: function( nRow1, aData1, iDisplayIndex1, iDisplayIndexFull1 ) {}
        });
    }



    var supSearchMonitor=function () {
        $('#supplementTable_filter input').on('keyup', function(event) {//基础条件查询添加回车事件
            if (event.keyCode == "13") {
                for(var key in supSearchData){//清空查询条件赋予对象值
                    delete supSearchData[key];
                }
                supSearchData.isNo=$("#supplementTable_filter input").val();
                $("#supplementTable").cgetData(true);
            }
        });
    }

    /**
     *协议选中行时，查询详述
     */
    var sTrSelectedBack = function(v, i, index, t, json){

        //加载右侧页面查详细
        $('#supplement_panel_box').cgetContent('intelligentSupplement/supplementViewPage',{},function () {
            t.cgetDetail('supplementForm','intelligentSupplement/viewSupplementDetail','',supplementDetailCallback);
        });

        limitBtn(json);//按钮控制
    };


    var supplementDetailCallback=function (data) {
        if ($("#signDate").val()=="" || $("#signDate").val()==null){
            $("#signDate").val($("#sysDate").val());
        }
        if ($("#agent").val()=="" || $("#agent").val()==null){
            $("#agent").val($("#loginName").val())
        }

        detailCallbackPayType(data.payType);

        if(!$.fn.DataTable.isDataTable('#auditHistoryTable')){
            handleIsAuditHistory(data);
        }else{
            $('#auditHistoryTable').cgetData(true);
        }
    }




    function signClick() {
        $("#supplementForm").toggleEditState(true);//切换表单可编辑
        $(".editBtn").removeClass("hidden");
    }


    function commonClick(isStatus) {//推送或作废
        var json= trSData.supplementTable.json;
            json.isStatus=isStatus;
            json.flag=isStatus;
            json.menuId=1104101;

        if (json.isId!=null &&json.isId!=""){
            Base.subimt('intelligentSupplement/saveIntelligentSupplement', 'POST', JSON .stringify(json) , function(data) {
                var content = data ? "保存成功！" : "保存失败！"; tips(content);
                $("#supplementForm").hideMask();
                $("#supplementTable").cgetData(true);//列表重新加载
            })
        }
    }



    function modifyClick(isStatus) {
        var json= trSData.supplementTable.json;
        if (json.isId!=""&&json.isId!=null) {
            $('body').cgetPopup({//加载弹屏
                title: '修改',
                content: '#intelligentSupplement/suppModifyPopPage',
                accept: modifyDone
            });
        }
    }


    var modifyDone = function(){//查询弹出屏，点击确定后
        var pf = $("#modifySupplementForm");
        if (pf.parsley().isValid()) { //验证必填是否已填写
            $("#modifySupplementForm").loadMask("保存中···", 1, 0.5);
            var json= trSData.supplementTable.json;
            json.modifyRemark=$("#modifyRemark").val();
            json.isStatus=5;//待修改
            json.flag=5;//修改申请
            Base.subimt('intelligentSupplement/saveIntelligentSupplement', 'POST', JSON .stringify(json), function(data) {
                var content = data? "申请成功！":"申请失败！"; tips(content);
                $('#modifySupplementForm')[0].reset();
                $("#modifyBtn").addClass("hidden");
                $("#supplementTable").cgetData(true);
            })
        } else {
            pf.parsley().validate();
            return false;
        }
    }





    function limitBtn(json){//控制按钮

        if (json.isStatus==0){//0:待签
            $("#deleteBtn").removeClass("hidden").html("作废");
            $("#signBtn").removeClass("hidden").html("签订");
            $("#pushBtn").addClass("hidden");
            $("#modifyBtn").addClass("hidden");

        }else if (json.isStatus==1 || json.isStatus==4) {//待推送或未通过
            $("#deleteBtn").removeClass("hidden").html("作废");
            $("#pushBtn").removeClass("hidden").html("推送");
            $("#signBtn").removeClass("hidden").html("修改");
            $("#modifyBtn").addClass("hidden");

        }else if (json.isStatus==5) {//待修改
            $("#signBtn").removeClass("hidden").html("修改");
            $("#modifyBtn").addClass("hidden");
            $("#pushBtn").addClass("hidden");
            $("#deleteBtn").removeClass("hidden").html("作废");

        }else if (json.isStatus==3){//审核已通过
            $("#modifyBtn").removeClass("hidden").html("申请修改");
            $("#deleteBtn").removeClass("hidden").html("作废");
            $("#signBtn").addClass("hidden");
            $("#pushBtn").addClass("hidden");

        }else if (json.isStatus==2) {//审核中
            $("#deleteBtn").removeClass("hidden").html("作废");
            $("#modifyBtn").addClass("hidden");
            $("#signBtn").addClass("hidden");
            $("#pushBtn").addClass("hidden");

        }else {//作废或没数据的时候
            $("#deleteBtn").addClass("hidden");
            $("#signBtn").addClass("hidden");
            $("#pushBtn").addClass("hidden");
            $("#modifyBtn").addClass("hidden");
        }
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
