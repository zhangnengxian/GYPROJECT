var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var igniteTable;
/**查询条件*/
var searchData={};
var accessoryData={};
var detailSearchData = {};
var handleIgnite = function() {
    "use strict";
    if ($('#igniteTable').length !== 0) {
        igniteTable= $('#igniteTable').on( 'init.dt',function(){
            //加载页面
            $("#ignite_panel_box").cgetContent("ignite/viewPage");
            //默认选中第一行
            $(this).bindDTSelected(trSelectedBack,true);
            $("#igniteTable_filter input").attr("placeholder","工程编号");
            //隐藏遮罩
            $('#igniteTable').hideMask();
            //绑定单击事件 弹出搜索框
            searchMonitor();
            //登记监听方法
            registerMonitor();
            setTimeout(function(){
   			    $("#igniteTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
          //跳转链接
   			if (crossPageBus) {
   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   					//跳转后销毁对象
   				crossPageBus = null
   			}
        }).DataTable({
            language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn' },
                { text: '签订', className: 'btn-sm btn-query business-tool-btn registerBtn'}
            ],
            //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
                url: 'ignite/queryGasProject',
                type:'post',
                data: function(d){
                    $.each(searchData,function(i,k){
                        d[i] = k;
                    });

                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/accept/json/project_accept.json',
            responsive: {
                details: {
                    renderer: function ( api, rowIdx, columns ){
                        return renderChild(api, rowIdx, columns);
                    }
                }
            },
            select: true,  //支持多选
            columns: [
                {"data":"gprojId",className:"none never"},
                {"data":"projNo"},
                {"data":"projName"},
                {"data":"acceptDate"},
                {"data":"acceptType"},
                {"data":"planGasDate"},
				{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}
            ],
            columnDefs: [{
                "targets": 0,
                "visible":false
            },{
                "targets":2,
                //长字符串截取方法
                render: $.fn.dataTable.render.ellipsis({
                    length: showLength, 	//截取多少字符（或汉字）
                    end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                })
            },/*{
                "targets":3,
                "orderable":false
            },*/{
				"targets": 5,
				"orderable":false,
				"render":function(data,type,row,meta){
					if(data !=="" && data!==null){
						return format(data);
					}else{
						return data;
					}
				}
			}],
            fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
                if(aData.overdue){
                    $(nRow).addClass("expired-proect");
                }
            }
        });
    }

};

//弹屏监听方法
var searchMonitor = function(){

    //查询按钮弹出屏查询
    $(".searchBtn").on("click",function(){
        var url = "#ignite/ignitePopSerach";
        var popoptions = {
            title: '查询',
            content: url,
            accept: searchDone
        };
        //加载弹屏
        $("body").cgetPopup(popoptions);
    });
    //基础条件查询添加监听
    $("#igniteTable_filter input").on("change",function(){
        var projNo = $("#igniteTable_filter input").val();
        searchData = {};
        searchData.projNo = projNo;
        $("#igniteTable").cgetData(true);  //列表重新加载
    });
    //基础条件查询添加回车事件
    $('#igniteTable_filter input').on('keyup', function(event) {
        if (event.keyCode == "13") {
            $(this).change();
        }
    });

};
/** 查询弹出屏，点击确定后 */
var searchDone = function(){
    debugger
    searchData = $("#igniteSearchForm").serializeJson();
    var projNo=$('#igniteTable_filter input').val();
    searchData.projNo=projNo;
    searchData.menuId=getStepId();
    $("#igniteTable").cgetData(true,igniteBack);  //列表重新加载
}

var igniteBack=function(){
    var len = $('#igniteTable').DataTable().ajax.json().data ? $('#igniteTable').DataTable().ajax.json().data.length : $('#igniteTable').DataTable().ajax.json().length;
    if (len<1) {
        // 维护按钮显示出来
        $('#igniteForm')[0].reset();
        $(':input','#igniteForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
        $(".editbtn").addClass("hidden");
        getSignStatusByPostId($("#post").val(),"igniteForm");
    } else {
        $(".editbtn").addClass("hidden");
    };
}
/**
 * 签订按钮监听方法
 */
var registerMonitor = function(){
    $('.registerBtn').off('click').on('click',function(){
        if($('#igniteTable').find('tr.selected').length>0){
            //切换可编辑状态
           //$('#igniteForm').toggleEditState(true);
        	//console.log($("#post").val());
        	getSignStatusByPostId($("#post").val(),"igniteForm");
            //维护按钮显示出来
            $('.editbtn').removeClass('hidden');
            var post=$("#post").val();
        	console.info("post-"+post);
        	var builder=$("#groupLeacerPost").val();
        	console.info("builder-"+builder);
        	
            if(post.indexOf(builder)>=0){
            	$(".pushBtn").removeClass("hidden");
            }else{
            	$(".pushBtn").addClass("hidden");
            }
            
        }else{
            alertInfo('请选择工程记录！');
        }
    });
};
var detailFlag = false;
/** 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	console.info("0---"+json.isSignIgnite);
    if(json.isSignIgnite=="0"){
    	$(".registerBtn").addClass("hidden");
    }else{
    	$(".registerBtn").removeClass("hidden");
    }
	$(".editbtn").addClass("hidden");
    t.cgetDetail('igniteForm','ignite/viewIgnite?stepId='+getStepId(),'',queryDetailBack);
}

var queryDetailBack = function(data){
    if(data.projLtypeId=="11"){
        $(".noUser").removeClass("hidden");
    }else{
        $(".noUser").addClass("hidden");
    }
    $("#planGasEndDate").val(format(data.planGasEndDate,"all"));
    $("#planGasDate").val(format(data.planGasDate,"all"));
    
    $("#uploadFlag1").val(data.uploadFlag);
    
    
}

var ingite = function () {
    "use strict";
    return {
        //main function
        init: function () {
            $.getScript("js/ellipsis.js").done(function(){
                handleIgnite();
            });
        }
    };
}();



