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
                                <button type="button" onclick="modifyClick()" class="btn btn-warning btn-sm">修改</button>
                                <button type="button" onclick="showAbandonedRecord()" class="btn  btn-sm">回退记录</button>
                                <button type="button" onclick="recoveryClick()" class="btn  btn-sm">恢复</button>
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
			        <h4 class="panel-title">操作区</h4>
			    </div>
			    <div class="panel-body" id="projectTable_panel_box"></div>
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
        });
    });

    var searchData={};
    searchData.menuId="201904";


    /**
     * 列表初始化加载数据
     */
    function handleProjectTable(){
        $('#projectTable').on( 'init.dt',function(){
            $(this).bindDTSelected(trSelectedBack,true); //默认选中第一行
            $('#projectTable_panel_box').cgetContent('projectModify/viewRightPage',{},viewRightCallback);//加载右侧页面
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
        $("#projectForm").toggleEditState(false);//切换表单可编辑
        //加载右侧页面
        $('#projectTable_panel_box').cgetContent('projectModify/viewRightPage',{},function () {
            t.cgetDetail('projectForm','projectModify/viewProjectDetail','',detailCallback);
        });
    };



    /**
     * 修改
     */
    function modifyClick(){
        $("#projectForm").toggleEditState(true);//切换表单可编辑
        $('.editBtn').removeClass("hidden");
        $('.controlField').attr('readonly',false);
        $('#scaleTableForm').toggleEditState(true);
        $('#scaleChangeTable').toggleEditState(true);
    }


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


    function showAbandonedRecord() {
        var json= trSData.projectTable.json;
        var param="?projId="+json.projId;

        $('body').cgetPopup({//加载弹屏
            title: '回退记录',
            content: '#dataTableInfo/abandonedRecordPopPageDetail'+param,
            accept: function () {},
            size:'large'
        });
    }


    
    function recoveryClick() {
        var json= trSData.projectTable.json;
        var param="?projId="+json.projId;
        $('body').cgetPopup({//加载弹屏
            title: '恢复页面',
            content: '#dataTableInfo/recoveryPopPage'+param,
            accept:function () {
                var pf = $("#recoveryPopForm");
                if (pf.parsley().isValid()) { //验证必填是否已填写
                    var serializeJson = pf.serializeJson();
                    serializeJson.projId=$("#_projId").val();
                    serializeJson.stepId=$("#_stepId").val();
                    Base.subimt('projectModify/recoveryProject','POST',JSON.stringify(serializeJson),function (data) {
                        alertInfo(data.message);$("#projectTable").cgetData(true);//列表重新加载
                    })
                } else {
                    pf.parsley().validate();return false;
                }
            },
            size:'large'
        });
    }





    
    
    
    
    
    
    
    
    


    var viewRightCallback=function (){};
    var detailCallback=function (){
    };

</script>
