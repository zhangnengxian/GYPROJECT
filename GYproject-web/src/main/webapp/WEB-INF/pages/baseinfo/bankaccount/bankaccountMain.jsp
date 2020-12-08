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
                    <h4 class="panel-title">银行账号列表</h4>
                </div>

                <div class="panel-body">

                    <div class="mask-html text-center">
                        <div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>

                    <div class="tab-content">
                        <div class="tab-pane fade active in btn-top active">
                            <div class="toolBtn f-r p-b-10">
                                <button type="button" onclick="searchClick()" class="btn btn-info btn-sm">查询</button>
                                <button type="button" onclick="commonClick('add')" class="btn btn-success btn-sm">新增</button>
                                <button type="button" onclick="commonClick('modify')" class="btn btn-warning btn-sm">修改</button>
                                <button type="button" onclick="commonClick('del')" class="btn btn-danger btn-sm">删除</button>

                            </div>
                            <table id="bankAccountTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>银行账号</th>
                                    <th>开户名称</th>
                                    <th>开户行</th>
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
			    <div class="panel-body" id="bankAccount_panel_box"></div>
			</div>
        </div>

    </div>
</div>




<script>
    App.restartGlobalFunction();
    App.setPageTitle('银行账号管理 - 工程项目管理系统 ');

    /**
     * 初始化工程列表
     */
    $(function(){
        $.getScript("js/ellipsis.js").done(function(){
            handleBankAccountTable();
        });
    });

    var searchData={};

    /**
     * 加载问题单据列表
     */
    function handleBankAccountTable(){
        $('#bankAccountTable').on( 'init.dt',function(){
           $(this).bindDTSelected(trSelectedBack,true); //默认选中第一行
            $('#bankAccount_panel_box').cgetContent('bankAccount/viewRightPage',{},viewRightCallback);//加载右侧页面
             $("#bankAccountTable_filter input").attr("placeholder","银行账号搜索");
            $('#bankAccountTable').hideMask(); //隐藏遮罩
            searchMonitor();

        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [],
            serverSide:true,//启用服务端模式，后台进行分段查询、排序
            ajax: {
                url: 'bankAccount/queryBankAccountMapList',
                data: function(d){
                    $.each(searchData,function(i,k){
                        d[i] = k;
                    });
                    },
                dataSrc: 'data'
            },
            select: true,  //支持多选
            columns: [
                {'data':'abId',"visible":false},
                {'data':'bankNo'},
                {'data':'bankName'},
                {'data':'bankAdress'}
            ],
            columnDefs: [{
                "targets":2,
                render: $.fn.dataTable.render.ellipsis({
                    length: 8, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            },{
                "targets":3,
                render: $.fn.dataTable.render.ellipsis({
                    length:12, 	//截取多少字符（或汉字）
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
        $('#bankAccountTable_filter input').on('keyup', function(event) {//基础条件查询添加回车事件
            if (event.keyCode == "13") {
                for(var key in searchData){//清空查询条件赋予对象值
                    delete searchData[key];
                }
                searchData.bankNo=$("#bankAccountTable_filter input").val();
                $("#bankAccountTable").cgetData(true);
            }
        });
    };



    /**
     *选中行时，查询详述
     */
    var trSelectedBack = function(v, i, index, t, json){
       searchData.abId=json.abId;
       $('.editBtn').addClass("hidden");
        $("#bankAccountForm").toggleEditState(false);//切换表单可编辑

        //加载右侧页面
        $('#bankAccount_panel_box').cgetContent('bankAccount/viewRightPage',{},function () {
           t.cgetDetail('bankAccountForm','bankAccount/viewBankAccountDetail','',detailCallback);
       });

    };



    function commonClick(paramValue){

         if (paramValue=='del'){//删除
            if (searchData.abId==""){
                alert("请选择要删除的记录");
            }else{
                var tipsHtml="<span style='background: linear-gradient(to right, red, blue);-webkit-background-clip: text;color: transparent;'>是否确认要删除此条记录!</span>"
                $("body").cgetPopup({
                    title: "提示信息",
                    content: tipsHtml,
                    accept:delDone,
                    icon: "fa-warning",
                });
            }
        }


        $("#bankAccountForm").toggleEditState(true);//切换表单可编辑
        $('.editBtn').removeClass("hidden");

        if (paramValue=='add'){//新增
            $("#bankAccountForm")[0].reset();//重置表单
           $('#abId').val("");

        }else if (paramValue=='modify'){//修改
        }

    }




    /**
     * 删除
     */
    var delDone=function () {
        Base.subimt('bankAccount/delBankAccountById','POST',{abId:searchData.abId},function (data) {
            $("#bankAccountTable").cgetData(true);
        });
    };






    /**
     * 条件查询弹出框
     */
    function searchClick(){
        var tipsHtml="<span style='background: linear-gradient(to right, red, blue);-webkit-background-clip: text;color: transparent;'>条件查询</span>";
        $('body').cgetPopup({
            title: tipsHtml,
            content: '#bankAccount/bankAccountPopPage',
            accept:searchDone
        });
    }




    /**
     * 条件查询
     */
    var searchDone=function(){
        searchData=$("#searchForm").serializeJson();
        $("#bankAccountTable").cgetData(true);
    };




    var viewRightCallback=function () {};
    var detailCallback=function () {
    };

</script>
