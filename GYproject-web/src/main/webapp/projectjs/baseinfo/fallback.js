var workFlowRecordTable,workFlowRecordData,searchData={},cashFlowData={};
/**
 * 加载工程列表
 */
var handleoperateWorkFlow = function() {
    'use strict';
    if ($('#fallBackTable').length !== 0) {
        $('#fallBackTable').on( 'init.dt',function(){
            $("#fallBackTable_filter input").attr("placeholder","菜单名称");
            //加载右侧页面
            $('#fallBack_panel_box').cgetContent('fallback/viewPage');
            //默认选中第一行
            $(this).bindDTSelected(trSelectedBack,true);
            //隐藏遮罩
            $('#fallBackTable').hideMask();
            //修改监听
            updateMonitor();
            //增加监听
            addMonitor();
            //删除监听
            deleteMonitor();
            //查询监听
            searchBtnMonitor();
        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Brftip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '增加', className: 'btn-sm btn-query business-tool-btn addBtn' },
                { text: '修改', className: 'btn-sm btn-query business-tool-btn updateBtn' },
                {text : '删除',className : 'btn-sm btn-warn deleteBtn business-tool-btn'}
            ],
            //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'fallback/getDataList',
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
                {"data":"mbsId",className:"none never"},
                {"data":"corpName"},
                {"data":"projectTypeDes"},
                {"data":"menuDes"}
            ],
            //order: [[ 1, "des" ]],
            columnDefs: [{
                'targets':0,
                'visible':false
            }],
        });
    }
};
/**
 * 修改按钮监听方法
 */
var updateMonitor = function() {
    $(".updateBtn").off("click").on("click", function() {
        if($("#fallBackTable").find("tr.selected").length>0){
            // 表单可编辑
            $("#fallBackForm").toggleEditState(true);
            // 按钮显示
            $(".editbtn").removeClass("hidden");
        }else{
            alertInfo('请选择要修改的记录！');
        }
    });
};

var searchBtnMonitor=function(){
    $(".searchBtn").off("click").on("click", function() {
        var url = "#fallback/fallbackSetSearchPopPage";
        var popoptions = {
            title: '查询',
            content: url,
            accept: searchDone
        };
        //加载弹屏
        $("body").cgetPopup(popoptions);
        //基础条件查询添加监听
    })
    //基础条件查询添加监听
    $("#stateSelect,#fallBackTable_filter input").on("change",function(){
        var menuName = $("#fallBackTable_filter input").val();
        searchData = {};
        searchData.menuName = menuName;
        console.info(2029,searchData);
        $("#fallBackTable").cgetData(true);  //列表重新加载
    });
    //基础条件查询添加回车事件
    $('#fallBackTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
            $(this).change();
        }
    });
}

var searchDone=function(){
    //查询条件
    searchData = $("#searchForm_fallBack").serializeJson();
    console.info(2019,searchData);
    //列表重新加载
    $("#fallBackTable").cgetData();
}

/**
 * 选中，查详述
 */
var trSelectedBack =function(v, i, index, t, json){
    //清空右侧
    $(".editbtn").addClass("hidden");
    $('#fallBackForm').formReset();
    // 参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
    t.cgetDetail('fallBackForm', 'fallback/getDataById', '',queryBack);
}

var queryBack=function(data){
    if ( !$.fn.DataTable.isDataTable('#workFlowRecordTable')) {
        initStepTable(data);
    }else{
        searchData.mbsId=data.mbsId;
        $("#workFlowRecordTable").cgetData(false);
    }

}

var initStepTable = function() {
    searchData.mbsId=$("#mbsId").val();
    if ($('#workFlowRecordTable').length != 0) {
        "use strict";
        workFlowRecordTable= $('#workFlowRecordTable').on( 'init.dt',function(){
            //隐藏遮罩
            $('#workFlowRecordTable').hideMask();
            addStep();
            deleteStep();
        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                { text: '增加', className: 'btn-sm btn-query hidden business-tool-btn editbtn addStepBtn' },
                { text: '删除', className : 'btn-sm btn-warn hidden deleteStepBtn editbtn business-tool-btn'}
            ],
            ajax: {
                url: 'fallback/getStepDataList',
                type:'post',
                data: function(d){
                    $.each(searchData,function(i,k){
                        d[i] = k;
                    });

                },
                datasrc: ''
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
                {"data":"backStepId"},
                {"data":"backStepDes"}
            ],
            order:[1, "asc" ],
            columnDefs: [{

            }]
        });
    }

};




//增加监听
var addMonitor=function(){
    $('.addBtn').off('click').on('click',function(){
        //清除选中行样式
        $('#fallBackTable tr.selected').removeClass("selected");
        $("#fallBackForm").formReset();
        //清空隐藏属性
        $("#mbsId").val("");
        $("#backStepId").val("");
        $("#backStepDes").val("");
        //切换可编辑状态
        $("#fallBackForm").toggleEditState(true);
        //维护按钮显示出来
        $(".editbtn").removeClass("hidden");
        searchData.mbsId='-1';
        $("#workFlowRecordTable").cgetData(false);
    });
}
//增加步骤监听
var addStep=function(){
    $('.addStepBtn').off('click').on('click',function(){
        if($("#stepId").val()=="-1"){
            $("body").cgetPopup({
                title: "提示信息",
                content: "请先选择步骤",
                accept: popClose,
                chide: true,
                icon: "fa-check-circle",
                newpop: 'new'
            });
            return false;
        }
        var rowsPart=[];
        var t=$("#fallBackForm");
        var json1=t.serializeJson();
        json1.backStepDes=$("#stepId option:selected").text();
        json1.backStepId = $("#stepId option:selected").val();
        rowsPart.push(json1);
        var json=$("#workFlowRecordTable").DataTable().rows().data();
        for(var i=0;i<json.length;i++){
            for(var j=0;j<rowsPart.length;j++){
                if(json[i].backStepId == rowsPart[j].backStepId){
                    $("body").cgetPopup({
                        title: "提示信息",
                        content: "该步骤已存在!",
                        accept: popClose,
                        chide: true,
                        icon: "fa-check-circle",
                        newpop: 'new'
                    });
                    return false;
                }
            }
        }
        workFlowRecordTable.rows.add(rowsPart).draw();
    });
}

//删除步骤监听
var deleteStep=function(){
    $('.deleteStepBtn').off('click').on('click',function(){
        var len=$('#workFlowRecordTable').find('tr.selected').length;
        if(len>0){
            var rows = $("#workFlowRecordTable").DataTable().rows( '.selected' ).remove().draw();
            var json =  $('#workFlowRecordTable').DataTable().rows().data();
            workFlowRecordTable.rows().remove();
            workFlowRecordTable.rows.add(json).draw();
        }else{
            $("body").cgetPopup({
                title: '提示信息',
                content: '请选择要删除的记录!',
                accept: popClose,
                icon: 'fa-exclamation-circle',
            });
        }
    });
}

//删除监听
var deleteMonitor=function(){
    $('.deleteBtn').off('click').on('click',function(){
        //加载弹屏
        if($('#fallBackTable').find('tr.selected').length>0){
            $('body').cgetPopup({
                title: '删除',
                content: '确定要删除选择数据？',
                accept: deleteDone
            });
        }else{
            $('body').cgetPopup({
                title: '删除',
                content: '请选择要删除数据！',
                accept: popClose
            });
        }
    });
};

/**
 * 初始化列表
 */
var operateWorkFlow = function () {
    'use strict';
    return {
        //main function
        init: function () {
            handleoperateWorkFlow();
        }
    };
}();


//删除---确定后
var deleteDone = function(){
    //选中行列的值（第一列）  url  table对象
    $("#fallBackTable").deleteRow(0,"fallback/deleteData",$("#fallBackTable").DataTable());
}



