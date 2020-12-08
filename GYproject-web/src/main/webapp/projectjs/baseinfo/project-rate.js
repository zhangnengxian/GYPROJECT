var projectRateTable; //费率列表
var projectRateData={};
/**
 * 加载费率列表
 */
/*var handleSubContract = function() {
	'use strict';
    if ($('#projectRateTable').length !== 0) {
    	projectRateTable=$('#projectRateTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$("#projectRateTable tbody tr").click(function(){
    			$("#projectRateDetailform label").text($(this).find("td:eq(0)").text());
    			$("#projectRateDetailform input").val($(this).find("td:eq(1)").text());
    		});
    		$("#projectRateDetailform label").text($(this).find("tbody tr:eq(0) td:eq(0)").text());
			$("#projectRateDetailform input").val($(this).find("tbody tr:eq(0) td:eq(0)").text());
   			//加载右侧页面
    		$('#project_rate_panel_box').cgetContent('projectRate/viewPageProjectRate');
   			//隐藏遮罩
   			$('#projectRateTable').hideMask();
   			$('#projectRateTable_filter input').attr('placeholder','费率类型');
   			//修改监听
   			signMonitor();
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
                 { text: '修改', className: 'btn-sm btn-query business-tool-btn signBtn' }
                 ],
             responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
             select: true,  //支持多选
        });
    }
};*/

//初始化收费信息(应付流水)
var handleSubContract= function() {
	"use strict";
    	
	projectRateTable= $('#projectRateTable').on( 'init.dt',function(){
		
   		//默认选中第一行
  		$(this).bindDTSelected(trSelectBack,true);
    	//加载右侧页面
		$('#project_rate_panel_box').cgetContent('projectRate/viewPageProjectRate');
    	$('#projectRateTable').hideMask();
    	
    	$('#projectRateTable_filter input').attr('placeholder','');
    	signMonitor();

        }).DataTable({
           language: language_CN,
           lengthMenu: [5],
           dom: 'Brftip',
           buttons: [
                   { text: '修改', className: 'btn-sm btn-query business-tool-btn signBtn' }
           ],
          //启用服务端模式，后台进行分段查询、排序
		  // serverSide:true,
		   select: true,  //支持多选
	       ajax: {
	             url: 'projectRate/queryProjectRate',
	             type:'post',
	             data: function(d){
	                   $.each(projectRateData,function(i,k){
	                   		d[i] = k;
	                   	});
	                   	
	                },
	                dataSrc: ''
	            },        
           responsive: {
           	   details: {
	        	   renderer: function ( api, rowIdx, columns ){
	        		   return renderChild(api, rowIdx, columns);
	        	   }
	           }
	       },
           columns: [
                {"data":"id",className:"none never"},
	  			{"data":"name"},
	  			{"data":"value"},
	  			
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			}
			]
       });
   };

function trSelectBack(v, i, index, t, json){
	$("#projectRateDetailform label").text(json.name);
	$("#projectRateDetailform input").val(json.value);
}
//修改监听
var signMonitor=function(){
	$('.signBtn').off('click').on('click',function(){
		if($('#projectRateTable').find('tr.selected').length>0){
			$('#projectRateDetailform').toggleEditState(true);
			$('.editbtn').removeClass('hidden');
		}else{
			alertInfo('请选择要修改的费率类型！');
		}
	});
};



/**
 * 初始化工程列表
 */
var subContract = function () {
	'use strict';
    return {
        //main function
        init: function () {
        	handleSubContract();
        }
    };
}();