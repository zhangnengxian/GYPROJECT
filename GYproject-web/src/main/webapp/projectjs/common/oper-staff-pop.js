
/**查询条件  默认待勘察*/
var staffData={};
var corpId=$("#corpId option:selected").val();
var handleStaff = function() {
  staffData = $("#staffForm").serializeJson();
  staffData.corpId = corpId;
  "use strict";
    if ($('#staffTable').length !== 0) {
      $('#staffTable').on( 'init.dt',function(){
        $('#staffTable').hideMask();
        //默认选中第一行
        //$(this).bindDTSelected(trSelectedBack,true);
        bindTableSelectRoleListRight();  //多行选中监听
        //查询监听方法
        searchMonitor();
        
        }).DataTable({
          language: language_CN,
            lengthMenu: [20],
            dom: 'Brtip',
            sScrollY:'500px',
            bStateSave:true,
            buttons: [
                { text: '查询', className: 'btn-sm btn-query searchBtn'}
            ],
            //启用服务端模式，后台进行分段查询、排序
      serverSide:false,
            ajax: {
                url: 'popup/queryOperateStaff',
                type:'post',
                data: function(d){
                    $.each(staffData,function(i,k){
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
            select: {
                style: 'multi'
            },
            columns: [
                {"data":"staffId",className:"none never"}, //隐藏
          {"data":"staffNo"}, 
        {"data":"staffName"},
        {"data":"postName",className:"text-right"}
      ],
      columnDefs: [{
        "targets": 0,
          "visible":false
      }],
      
      ordering:false
        });
    }
};
//绑定表格行选中事件
function bindTableSelectRoleListRight() {
  $('#staffTable').DataTable().on( 'select', function ( e, dt, type, indexes ) {
    var data = $('#staffTable').DataTable().rows('.selected').data();
    var staffIds = ',';
    var staffNames='';
    $.each(data,function(key, val){
      if(staffIds.length > 1) {
        staffNames+=",";
      }
      staffIds += val.staffId+",";
      staffNames+=val.staffName;
    });
    $("#opereaterId").val(staffIds);
    $("#opereater").val(staffNames);
    });
}
//弹屏监听方法
var searchMonitor = function(){
  $(".searchBtn").on("click",function(){
    staffData = $("#staffForm").serializeJson();
    staffData.corpId = $("#corpId").val();
      $("#staffTable").cgetData();  //列表重新加载
  });
};

var trSelectedBack = function(v, i, index, t, json){
  
}

var staff = function () {
  "use strict";
    return {
        //main function
        init: function () {
          handleStaff();
        }
    };
}();

