
var projId=$("#projId").val(),sbId = $("#sbId").val(),treeArr=[];
var businessOrderId = $("#businessOrderId").val();
var subSearchData=$("#sbId").val()==''?{"sbId":-1}:{"sbId":sbId};//工程量清单列表查询条件
var histSearchData={"projId":projId,"businessOrderId":businessOrderId};	//历史列表查询条件
var qualitiesTable;
/**
 * 初始化工程量列表
 */
var qualitiesInit = function() {
    "use strict";
    if ($('#qualitiesTable').length !== 0) {
        if(!$.fn.DataTable.isDataTable('#qualitiesTable')){
            qualitiesTable= $('#qualitiesTable').on( 'init.dt',function(){
                addQualities();//添加行
                delFun();//删除
                //导出监听
                exportBtnMonitor();
                selectData();
                $(this).bindInputsChange(amountSum);
                //计算总值
                var totalRow = 0;
                if(qualitiesTable.column(6).data().length){
                    totalRow = qualitiesTable.column(6).data().reduce( function (a,b) {
                        a = a || 0;
                        b = b || 0;
                        return parseFloat(a) + parseFloat(b);
                    });
                }
                $(".total-amount").text(parseFloat(totalRow).toFixed(2));
                changeAText();
                qualitiesHistoryInit();
                if($("#mrAuditLevel").val()==1){
                    if($("#isAudit").val()=="1"){
                        $('#planEstablishDetailform').toggleEditState(false);
                        $(".acceptSaveBtn").addClass("hidden");
                        $(".right-btn").addClass("hidden");
                    }else{
                        $('#planEstablishDetailform').toggleEditState(true);
                        //$(".acceptSaveBtn").removeClass("hidden");
                        //$(".right-btn").removeClass("hidden");
                    }
                }else{
                    $('#planEstablishDetailform').toggleEditState(false);
                    $(".acceptSaveBtn").addClass("hidden");
                    $(".right-btn").addClass("hidden");
                }
            }).DataTable({
                language: language_CN,
//              lengthMenu: [18],
                dom: 'Brtip',
                buttons: [
                    { text: '增加', className: 'btn-sm  btn-query business-tool-btn right-btn addBtn hidden' },
                    { text: '删除', className: 'btn-sm  btn-warn business-tool-btn right-btn delBtn hidden' },
                    { text: '导出', className: 'btn-sm  btn-default business-tool-btn right-btn exportBtn hidden' },
                ],
                /*ajax: 'projectjs/subcontract/json/qualities.json',*/
                //启用服务端模式，后台进行分段查询、排序
                //          serverSide:false,
                ajax: {
                    url: 'qualitiesDeclaration/queryQuantityStandard',
                    type: 'post',
                    data: function(d){
                        $.each(subSearchData,function(i,k){
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
                    {"data":"id", className:"none never"},
                    {"data":"sqStandardId", className:"none never"},
                    {"data":"sqBranchProjName", responsivePriority: 1},
                    {"data":"sqUnit", className:"text-center", responsivePriority: 5},
                    {"data":"sqLabourPrice", className:"text-right", responsivePriority: 4},
                    {"data":"sqNum", className:"text-right", responsivePriority: 3},
                    {"data":"sqAmount", className:"text-right amount", responsivePriority: 2}
                ],
                columnDefs: [{
                    "targets":0,
                    "visible":false
                },{
                    "targets":1,
                    "visible":false
                },{
                    "targets":2,
                    //长字符串截取方法
                    render: $.fn.dataTable.render.ellipsis({
                        length: 10, 	//截取多少字符（或汉字）
                        end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                    })
                },{
                    "targets":4,
                    "render": function ( data, type, row, meta ) {
                        if(type === "display"){
                            if(data!=="" && data!==null){
                                var pdata = parseFloat(data).toFixed(2);
                            }else{
                                var pdata = data;
                            }
                            var tdcon='<div class="tdInnerInput"><input name="' + row.sqStandardId + '_sqLabourPrice" class="form-control input-sm text-right" data-parsley-type="number" readonly value="' + pdata + '"></div>';
                            return tdcon;
                        }else{
                            return data;
                        }
                    }
                },{
                    "targets": 5,
                    "render": function ( data, type, row, meta ) {
                        if(type === "display"){
                            data = data || 0;
                            var tdcon='<div class="tdInnerInput"><input name="' + row.sqStandardId + '_sqNum" class="form-control input-sm text-right numbers" data-parsley-type="number" value="' + data + '"></div>';
                            return tdcon;
                        }else{
                            return data;
                        }
                    }
                },{
                    "targets": 6,
                    "render": function ( data, type, row, meta ) {
                        if(type === "display"){
                            data = data || 0;
                            var tdcon='<div class="tdInnerInput"><input name="' + row.sqStandardId + '_sqAmount" class="form-control input-sm text-right" data-parsley-type="number" readonly value="' + data + '"></div>';
                            return tdcon;
                        }else{
                            return data;
                        }
                    }
                }],
                ordering:false
            });
        }else{
            if(trSData.qualitiesDeclarationTable.t){
                $(".right-btn").removeClass("hidden");
            }else{
                $(".right-btn").addClass("hidden");
                treeArr=[];
            }
        }
    }
}
function selectData(){
    var arr=[];
    var json=$("#qualitiesTable").DataTable().rows().data();
    for(var i=0;i<json.length;i++){
        arr.push(json[i].id);
    }
    treeArr=arr;
}
/**
 * 表格计算
 */
var amountSum=function(v, input, t, dt){
    var td = $('[name=' + input.attr("name") + ']').closest("td"),
        //当前表格值
        qualities = v || 0,
        //上一个td值
        price = dt.cell(td.prev("td")).data() || 0,
        //计算值
        amount = (parseFloat(qualities) * parseFloat(price)).toFixed(2);
    //放入计算结果
    dt.cell(td.next("td")).data(amount);
    td.next("td").find("input").val(amount);

    //计算总值
    var totalRow = 0;
    if(dt.column(6).data().length){
        totalRow = dt.column(6).data().reduce( function (a,b) {
            a = a || 0;
            b = b || 0;
            return parseFloat(a) + parseFloat(b);
        });
    }

    $(".total-amount").text(parseFloat(totalRow).toFixed(2));
    $("#totalAmount").val($(".total-amount").text());
    $("#totalAmount").change();
}
//添加行
var addQualities = function(){
    $(".addBtn").off("click").on("click",function(){
        var url = "#qualitiesDeclaration/standardPop";
        //加载弹屏
        $("body").cgetPopup({
            title: '工程量标准',
            content: url,
            accept: sureDone,
            size:"large"
        });
    });
}
function sureDone(){
    var rows = [],arr=[],addArr=[],removeArr=[];
    arr=$('#jstree-safe').jstree().get_selected();
    addArr=$('#jstree-safe').jstree().get_selected();
    for(var i=0;i<arr.length;i++){
        if(treeArr.length>0){
            for(var j=0;j<treeArr.length;j++){
                if(treeArr[j]==arr[i]){
                    treeArr.splice(j,1,'');
                    arr.splice(i,1,'');
                }
            }
        }
    }
    //添加行
    if(arr.length>0){
        for(var m=0;m<arr.length;m++){
            if(arr[m]!=""){
                if(arr[m].indexOf("@@") === -1) {
                    continue;
                }
                var json = {};
                json.id = arr[m];
                arr[m] = arr[m].split("@@");
                json.sqStandardId = arr[m][0];
                json.sqBranchProjName = arr[m][1];
                json.sqUnit = arr[m][2];
                json.sqLabourPrice = arr[m][3];
                json.sqNum = '';
                json.sqAmount = '';
                rows.push(json);
            }
        }
    }
    //删除行
    if(treeArr.length>0){
        for(var n=0;n<treeArr.length;n++){
            if(treeArr[n]!=""){
                if(treeArr[n].indexOf("@@") === -1) {
                    continue;
                }
                sqStandardId = treeArr[n];
                $("#qualitiesTable").DataTable().rows(function ( idx, data, node ) {
                    return data.id==sqStandardId ? true : false;
                }).remove().draw();
            }
        }
    }
    treeArr=addArr;
    qualitiesTable.rows.add(rows).draw();
    $("#qualitiesForm").toggleEditState(false);
}
//删除行
var delFun =function(){
    $(".delBtn").on("click",function(){
        $("body").cgetPopup({
            title: '提示信息',
            content: '确认要删除数据吗？',
            accept: delData,
            icon: 'fa-exclamation-circle',
        });
    });
}
function delData(){
    // var rows = $("#qualitiesTable").DataTable().rows( '.selected' ).remove().draw();
    $("#qualitiesTable").DataTable().rows( '.selected').remove().draw();// 删除本地数据
    var arr=[];
    var json=$("#qualitiesTable").DataTable().rows().data();
    for(var i=0;i<json.length;i++){
        arr.push(json[i].id);
    }
    var totalRow=0.00;
    if($("#qualitiesTable").DataTable().column(6).data().length){
        totalRow = $("#qualitiesTable").DataTable().column(6).data().reduce( function (a,b) {
            a = a || 0;
            b = b || 0;
            return parseFloat(a) + parseFloat(b);
        });
    }
    $(".total-amount").text(parseFloat(totalRow).toFixed(2));
    $("#totalAmount").val($(".total-amount").text());
    $("#totalAmount").change();
    treeArr=arr;
}

//点击导出
var exportBtnMonitor=function(){
    $('.exportBtn').off().on('click',function(){
        var len=$("#qualitiesTable").DataTable().rows().data().length;
        $('#subQualitiesForm').submit();
    });
}
//点击保存
$(".acceptSaveBtn").off().on("click",function(){
    $("#flag").val("0");
    $("#audit").val("1");
    var viewform = $("#planEstablishDetailform");
    if(viewform.parsley().isValid()){
        var viewdata = viewform.serializeJson();
        if(viewdata.costType==1){
            var trs = $('#qualitiesTable tbody tr');
            for(var i=0,l=trs.length; i<l; i++){
                console.info(trs);
                console.info(trs.eq(i));
                console.info(trs.eq(i).find("input.numbers").val());
                var v = trs.eq(i).find("input.numbers").val();
                // if(v == 0||isNaN(v)) {
                //     alertInfo("请输入正确的数量！");
                //     return false;
                // }
            };
            var json=$("#qualitiesTable").DataTable().data();
            viewdata.list = [];
            $.each(json, function(k,v){
                viewdata.list.push(v);
            })
        }else{
        }
        var data=JSON.stringify(viewdata);
        if(response){
            response.abort();
        }
        var response = $.ajax({
            type : "POST",
            url : "qualitiesDeclaration/saveSubBudget",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(viewdata),
            success : function(data){
                var content = "数据保存成功！";
                if(data === "false"){
                    content = "数据保存失败！";
                }else if(data === "true"){
                    $(".acceptSaveBtn").addClass("hidden");
                    //$(".editbtn").addClass("hidden");
                    //$(".right-btn").addClass("hidden");
                    // $("#planEstablishDetailform").formReset();
                    $("#planEstablishDetailform").toggleEditState(false);
                    $("input[name='mrResult']").removeAttr("disabled");
                    //$("#qualitiesDeclarationTable").cgetData(true,dataBack);//列表重新加载
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
                console.warn("送审区记录保存失败 ！");
            }
        });
    }else{
        //如果未通过验证，则加载验证UI
        viewform.parsley().validate();
    }
});
/**
 * 加载审批历史列表
 */
var qualitiesHistoryInit=function (){
    if ($('#auditHistoryTable').length !== 0) {
        $('#auditHistoryTable').on( 'init.dt',function(){
            //隐藏遮罩
            $('#auditHistoryTable').hideMask();
        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
            ],
            serverSide:true,
            ajax: {
                url: 'budgetFirstAudit/queryManageRecord',
                type:'post',
                data: function(d){
                    $.each(histSearchData,function(i,k){
                        d[i] = k;
                    });
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/subcontract/json/qualities_audit_history.json',
            responsive: {
                details: {
                    renderer: function ( api, rowIdx, columns ){
                        return renderChild(api, rowIdx, columns);
                    }
                }
            },
            select: true,  //支持多选
            columns: [
                {"data":"mrTime"},
                {"data":"mrResult"},
                {"data":"mrAopinion"},
                {"data":"mrCsr"}
            ],
            columnDefs: [{
                "targets": 0,
                "render": function ( data, type, row, meta ) {
                    if(type === "display"){
                        return format(data,'all');
                    }else{
                        return data;
                    }
                },
            },{
                "targets": 1,
                "render": function ( data, type, row, meta ) {
                    if(data === "1"){
                        return "通过";
                    }else if(data === "0"){
                        return "不通过";
                    }else{
                        return "";
                    }
                },
            }]
        });
    }
}

/**
 * 初始化工程量列表
 */
var qualitiesJudgement = function () {
    "use strict";
    return {
        //main function
        init: function () {
            $.getScript("js/ellipsis.js").done(function(){
                qualitiesInit();
            });
        }
    };
}();


