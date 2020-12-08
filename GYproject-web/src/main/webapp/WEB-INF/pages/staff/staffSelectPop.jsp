<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<table id="staffSelectPoptable" class="table table-striped table-bordered nowrap" width="100%">
    <thead>
        <tr>
           <th>员工号</th>
           <th>员工姓名</th>
           <th>手机</th>
        </tr>
    </thead>
</table>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
   	var staffSelectPopSearchData = {};
   	var staffSelectPoptable;
   	var postPop = '${post}';
    if ($('#staffSelectPoptable').length !== 0) {
    	staffSelectPoptable = $('#staffSelectPoptable').on( 'init.dt',function(){
   			$('#staffSelectPoptable').hideMask();
   			return false;
        }).DataTable({
        	 language: language_CN,
            lengthMenu: [2],
            dom: 'Bfrtip',
            buttons: [],
         	serverSide: true,
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            destroy: true,
            ajax: {
            	url: 'staff/queryStaffList?post=' + postPop,
            	type:'post',
            	data: function(d){
            		$.each(staffSelectPopSearchData,function(i,k){
            			d[i] = k;
            		});
            	},
            	dataSrc: 'data'
            },
            select: true,
            searching: false,
            columns: [
  				{"data":"staffNo"},
  				{"data":"staffName"},
  				{"data":"mobile"}
  			],
  			columnDefs: [
                { "name": "staffNo",   "targets": 0 },
                { "name": "staffName",  "targets": 1 },
                { "name": "mobile",  "targets": 2}
            ],
  			order: [[ 0, "desc" ]]
        });
    }
    
    /*
     * 表格加载完成后
     */
    $('#staffSelectPoptable').on( 'draw.dt', function () {
    	if(staffSelectPoptable.rows().data().length > 0) {
    		staffSelectPoptable.row(0).select();
    	}
    	return false;
    });
    
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->