var searchData={};//查询条件
searchData.menuId="110212";

var raiseMoneyProject = function () {
    return {
        init: function () {
            $.getScript("js/ellipsis.js").done(function(){
                handleProjectAccept();
            });
        }
    };
}();

var handleProjectAccept = function() {
    $('#raiseMoneyProjectTable').on( 'init.dt',function(){
        //默认选中第一行
        $(this).bindDTSelected(SelectedTR,true);
        $("#raiseMoneyProjectTable_filter input").attr("placeholder","工程编号");
        //隐藏遮罩
        $('#raiseMoneyProjectTable').hideMask();
        //绑定单击事件 弹出搜索框
        searchMonitor();
        applayMonitor();//申请监听
        setTimeout(function(){ $("#raiseMoneyProjectTable").DataTable().columns.adjust(); }, 0);
        //跳转链接
		if (crossPageBus) {
			getSidebarMenu(11, true);
			checkSidebarMenu(crossPageBus.hash)
			//跳转后销毁对象
			crossPageBus = null
		}
    }).DataTable({
        language: language_CN,
        lengthMenu: [14],
        dom: 'Bfrtip',
        buttons: [
            { text: '查询', className: 'btn-sm btn-query searchBtn' },
            { text: '申请', className: 'btn-sm btn-query raiseMoneyApplayBtn' }
        ],
        //启用服务端模式，后台进行分段查询、排序
        serverSide:true,
        ajax: {
            url: 'raiseMoneyApplicationController/queryProject',
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
            {data:"projId","visible":false},
            {data:"projNo"},
            {data:"projName"},
            {data:"projStatusDes"},
            {data:"projectTypeDes"},
            {data:"signBack",
                className:"none never",
                "createdCell": function (td, cellData, rowData, row, col) {
                    if(cellData==$("#notThrough").val()){
                        $(td).parent().children().css("background", "rgb(255, 219, 219)");
                    }
                }
            }
        ],
        responsive: {
            details: {
                renderer: function ( api, rowIdx, columns ){
                    return renderChild(api, rowIdx, columns);
                }
            }
        },
        columnDefs: [{
            "targets":2,
            //长字符串截取方法
            render: $.fn.dataTable.render.ellipsis({
                length: 8, 	//截取多少字符（或汉字）
                end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
            })
        }]
    });
};



var SelectedTR = function(v, i, index, t, json){
    //隐藏保存/推送/取消按钮
    $(".editbtn").addClass("hidden");
    //工程详细查询
    t.cgetDetail('raiseMoneyApplayDetailForm','raiseMoneyApplicationController/queryProjectDetail','',formCallback);
};
//回调函数
var formCallback = function(data){};

//申请按钮监听
var applayMonitor=function () {
    $(".raiseMoneyApplayBtn").on("click",function(){
            //表单可编辑
            $("#raiseMoneyApplayDetailForm").toggleEditState(true);
            //将备注切换成可编辑状态
            $("#remark").removeClass("field-not-editable");
            $("#remark").addClass("field-editable");
            //按钮显示
            $(".editbtn").removeClass("hidden");

    });

};


//弹屏监听方法
var searchMonitor = function(){
    //查询按钮弹出屏查询
    $(".searchBtn").on("click",function(){
        var url = "#acceptancePrintController/projectPrintSearchPopPage";
        var popoptions = {
            title: '查询',
            content: url,
            accept: searchDone
        };
        //加载弹屏
        $("body").cgetPopup(popoptions);
    });
    //基础条件查询添加监听
    $("#raiseMoneyProjectTable_filter input").on("change",function(){
        searchData.projNo = $("#raiseMoneyProjectTable_filter input").val();
        $("#raiseMoneyProjectTable").cgetData(true);  //列表重新加载

    });

    //基础条件查询添加回车事件
    $('#raiseMoneyProjectTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
            $(this).change();
        }
    });
};

/** 查询弹出屏，点击确定后 */
var searchDone = function(){
    searchData = $("#acceptancePrintSearchForm").serializeJson();
    searchData.menuId="110212";
    $("#raiseMoneyProjectTable").cgetData(true,function(){
    	var len = $('#raiseMoneyProjectTable').DataTable().ajax.json().data ? $('#raiseMoneyProjectTable').DataTable().ajax.json().data.length : $('#raiseMoneyProjectTable').DataTable().ajax.json().length;
        if(len<1){
            $("#acceptApplyForm")[0].reset();
            $(':input','#scaleTableForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
            $("input[name='projLtype']").attr("checked",false);
            $("input[name='projLtype']").change();
            $(".backReasonshow").addClass("hidden");
        }
    });
};

//点击保存
$(".acceptSaveBtn").on("click",function(){
    var dataForm = $("#raiseMoneyApplayDetailForm").serializeJson();
        //加遮罩
        $(".saveHiddenBox").loadMask("正在保存...", 1, 0.5);
        var response = $.ajax({
            type : "POST",
            url : "raiseMoneyApplicationController/saveRaiseMoney",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(dataForm),
            success : function(data){
                //取消遮罩
                $(".saveHiddenBox").hideMask();
                var content = "数据保存成功！";
                if(data === "false"){
                    content = "数据保存失败！";
                }else if(data === "true"){
                    $(".editbtn").addClass("hidden");
                    $(".right-btn").addClass("hidden");
                    $("#raiseMoneyApplayDetailForm").toggleEditState(false);
                    $("#raiseMoneyProjectTable").cgetData(true);//列表重新加载
                }
                var myoptions = {
                    title: "提示信息",
                    content: content,
                    accept: popClose,
                    chide: true,
                    icon: "fa-check-circle"
                }
                $("body").cgetPopup(myoptions);
            },
            error : function(){
                //取消遮罩
                $(".saveHiddenBox").hideMask();
                console.warn("送审区记录保存失败 ！");
            }
        });
});



//推送保存
$(".pushButton").off().on("click",function(){
    var dataForm = $("#raiseMoneyApplayDetailForm").serializeJson();
        dataForm.stepId = getStepId();

/*    if(dataForm.uploadFlag!='1'){
        alertInfo("请确认是否已上传提资资料！");
        return false;
    }

*/



        //加遮罩
        $(".saveHiddenBox").loadMask("正在推送...", 1, 0.5);
        var response = $.ajax({
            type : "POST",
           url : "raiseMoneyApplicationController/raiseMoneyPush",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(dataForm),
            success : function(data){
                //取消遮罩
                $(".saveHiddenBox").hideMask();
                var content = "数据保存成功！";
                if(data === "false"){
                    content = "数据保存失败！";
                }else if(data === "true"){
                    $(".editbtn").addClass("hidden");
                    $(".right-btn").addClass("hidden");
                    $("#raiseMoneyApplayDetailForm").formReset();
                    $("#raiseMoneyApplayDetailForm").toggleEditState(false);
                    $("#raiseMoneyProjectTable").cgetData(true);//列表重新加载
                }else{
                    content = "请上传提资资料！";
                }
                var myoptions = {
                    title: "提示信息",
                    content: content,
                    accept: popClose,
                    chide: true,
                    icon: "fa-check-circle"
                };
                $("body").cgetPopup(myoptions);
            },
            error : function(){
                //取消遮罩
                $(".saveHiddenBox").hideMask();
                console.warn("保存失败 ！");
            }
        });
});





//放弃按钮
$(".cancelBtn").on("click",function(){
    $("#raiseMoneyApplayDetailForm").toggleEditState(false);
    //隐藏保存/推送/取消按钮
    $(".editbtn").addClass("hidden");
});