

//表型号弹框初始化列表
var mytable3;
var searchData3={};
var meterTypedt = function() {		
	"use strict";
    if ($('#mrTypePop').length !== 0) {
    	 mytable3= $('#mrTypePop').on( 'init.dt',function(){
    		 $('#mrTypePop').hideMask();
     	   
        }).DataTable({
        	 language: {
                 url: 'js/dt-chinese.json'
             },
            lengthMenu: [18,20,30,50,100],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm meterModelSel' }           
               
            ],
         //   serverSide:true,
	            ajax: {
	                url: 'meterOperation/meterModelList',
	                type:'post',
	                /*data: function(d){
	                	$.each(searchData3,function(i,k){
	                		d[i] = k;
	                	});
	                	
	                },*/
	                data: function(){
	                	return searchData3;
	                },
	                dataSrc: ''
	            },
            responsive: true,
            searching: false,
            select: true,
            columns: [
	  					{"data":"meterRange",className:"none never"},
	  					{"data":"manuId.manuId",className:"none never"},
	  					{"data":"meterModelId",className:"none never"},
	  					{"data":"meterType",className:"none never"},
						{"data":"meterModel"},
						{"data":"manuId.manuName"},
						{"data":"meterTypeDes"}
					],
					columnDefs: [ {
					      "targets": 0,
					      "visible":false
					    },{
						      "targets": 1,
						      "visible":false
						    },{
				      "targets": 2,
				      "visible":false
				    },{
					      "targets": 3,
					      "visible":false
					    }]
        });
        
    }
};
$(document).on("click",".meterModelSel",function(){
	//searchData3.meterType=$('#mrTypePop').DataTable().column(3).data()[$('#mrTypePop tr.selected').index()];;
	alert($("#manuIdpop").val());
	searchData3.manuId=$("#manuIdpop").val();
	searchData3.meterModel=$("#meterModel").val();
	$("#mrTypePop").cgetData();
});