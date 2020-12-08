var searchData = {};
searchData.isPrint=$("#haveNotPrint").val();
searchData.menuId="110108";
searchData.signs="1";
var handleSupplementContractPrint = function(){
    "use strict";
    if ($('#supplementalContractPrintTable').length !== 0) {
        $('#supplementalContractPrintTable').on( 'init.dt',function(){
            //默认选中第一行
            $(this).bindDTSelected(trSelectedBack,true);
            //隐藏遮罩
            $('#supplementalContractPrintTable').hideMask();
            $("#supplementalContractPrintTable_filter input").attr("placeholder","工程编号");
            //搜索监听
            searchMonitor();
            //标记监听
            signMonitor();
        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }
                //{ text: '标记', className: 'btn-sm btn-query signBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'surveyResultPrint/getSurveyResult',
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
                {"data":"surveyId",className:"none never"},
                {"data":"projNo"},
                {"data":"projName"},
                {"data":"surveyDate"}
            ],
            columnDefs: [{
                "targets":0,
                "visible":false
            },{
                "targets":2,
                //长字符串截取方法
                render: $.fn.dataTable.render.ellipsis({
                    length: 10, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            }]
        })
    }
};
//标记监听
var signMonitor=function(){
    $('.signBtn').off().on('click',function(){
        var len=$("#supplementalContractPrintTable").find('tr.selected').length;
        if(len>0){
            $("body").cgetPopup({
                title: "提示信息",
                content: "是否将选中的踏勘信息标记为已打印？",
                accept: sureDone,
                chide: true,
                newpop: 'new',
                icon: "fa-check-circle"
            });
        }else{
            alertInfo('请选择要标记打印的补充协议！');
        }
    });
}
var sureDone=function(){
    var id=trSData.supplementalContractPrintTable.json.surveyId;
    $.ajax({
        type:'post',
        url:'surveyResultPrint/sign',
        contentType: "application/json;charset=UTF-8",
        data: id,
        success:function(data){
            var content = "标记成功！";
            if(data=="false"){
                content = "标记失败！";
            }
            var myoptions = {
                title: "提示信息",
                content: content,
                accept: tableReload,
                chide: true,
                icon: "fa-check-circle",
                newpop: 'new'

            }
            $("body").cgetPopup(myoptions);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("数据保存失败！");
        }

    })
}
var tableReload=function(){
    searchData.isPrint=$("#haveNotPrint").val();
    $("#supplementalContractPrintTable").cgetData();
}
var showReport1 = function(json){
	
	
	var param = {};
	param.projId = json.projId;
	param.menuId = "110108";
	$.ajax({
        type: 'POST',
        url: 'surveyResultPrint/viewSurveyReport',
        data: param,
        //dataType:'json',
        //contentType: "application/json;charset=UTF-8",
        success: function (cptUrl) {
        	console.info("cptUrl ==== "+cptUrl);
        	if(cptUrl!='' && cptUrl !=null){
        		var src = cptPath+"/ReportServer?reportlet=design/"+cptUrl+".cpt"+"&surveyId="+surveyId+"&imgUrl="+$("#imgUrl").val();
       	 		$("#mainFrm").attr("src",src);
        	}else{
        		//oldshowReport(json);
        	}
        }
	});
}

var trSelectedBack = function(v, i, index, t, json){
    //加载打印预览
    var surveyId = encodeURIComponent(cjkEncode(json.surveyId));
        showReport1(json);
    json.menuId=searchData.menuId;
    queryCpt(json)
};

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
    $(".searchBtn").off("click").on("click",function(){
        var url = "#surveyResultPrint/searchPopPage";
        var popoptions = {
            title: '查询',
            content: url,
            accept: searchDone
        };
        //加载弹屏
        $("body").cgetPopup(popoptions);
    });

    $("#supplementalContractPrintTable_filter input").on("change",function(){
        searchData.projNo = $("#supplementalContractPrintTable_filter input").val();
        //传入false  则不选中行
        $("#supplementalContractPrintTable").cgetData(false, function(){
        }); //列表重新加载
    });
    //基础条件查询添加回车事件
    $('#supplementalContractPrintTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
            $(this).change();
        }
    });
};
var searchDone = function(){
    searchData = $("#searchForm_sub").serializeJson();
    searchData.projNo = $("#supplementalContractPrintTable_filter input").val();
    searchData.menuId="110108";
    //列表重新加载
    $("#supplementalContractPrintTable").cgetData(true, function(){
    });

}

/**
 * 初始化列表
 */
var supplementalContractPrint = function () {
    "use strict";
    return {
        //main function
        init: function () {
            $.getScript("js/ellipsis.js").done(function(){
                handleSupplementContractPrint();
            });
            var obj={"menuId":searchData.menuId,"projId":null};
            queryCpt(obj);
        }
    };
}();