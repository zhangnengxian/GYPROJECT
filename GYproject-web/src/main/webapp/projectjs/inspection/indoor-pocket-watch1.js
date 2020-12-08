/**
 * 初始化记录区列表
 */
/*
var backData={};
var handleRecordList = function(){
	"use strict";
	if($('#pcIdNew').val()!==""){
		recordData.pcId=$('#pcIdNew').val();
	}else{
		recordData.pcId=-1;
	}
	recordData.projId = $('#projId').val();
    if ($('#recordListTable').length !== 0) {
    	recordListTable= $('#recordListTable').on( 'init.dt',function(){
    		$(this).bindDTSelected(trSelectedBackRecord,false);
   			//隐藏遮罩
   			$('#recordListTable').hideMask();
   			handleCheckBox();
   			checkboxMonitor();
   	    	//加载cpt文件
   			showReport();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            destroy:true,
            dom: 'Brtip',
            buttons: [
            ],
            fnServerParams:function(aodata){
            	aodata.push({
            		'name':'nName',
            		'checkType':'1'
            	},{
            		'name':'nName2',
            		'checkType':'2'
            	});
            },
            //serverSide:true,
            ajax: {
                url: $("#actionName").val()+'/queryRecords',
                type:'post',
                data: function(d){
                   	$.each(recordData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: function(data){
                	backData = data;
                	console.info(backData);
                }
            },
            //ajax:'projectjs/inspection/json/trench_back_fill_record.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            sPaginationType : "full_numbers",
            columns : function(data){
            	console.info(data);
            },
            fnHeaderCallback:function(oSettings,aodata){
            	console.info(aodata);
            	alert("开始画");
            	},
            	fnDrawCallback : function(oSettings,aodata) {
            		console.info(aodata);
            		alert(1);
            	
            	},
            	fnInitComplete : function(data) {
            		console.info(data);
            		alert(2);
            	},
            	columnDefs: [{
				"targets":0,
				"render": function ( data, type, row, meta ) {
					return addCheckBox(data,row.pcId);
				}
			}],
			
			ordering:false
        });
    }
}*/
var handleRecordList = function(){
	"use strict";
	if($('#pcIdNew').val()!==""){
		recordData.pcId=$('#pcIdNew').val();
	}else{
		recordData.pcId=-1;
	}
	recordData.projId = $('#projId').val();
	if ($('#recordListTable').length !== 0) {
		recordListTable= $('#recordListTable').on( 'init.dt',function(){
			$(this).bindDTSelected(trSelectedBackRecord,false);
			//隐藏遮罩
			$('#recordListTable').hideMask();
			handleCheckBox();
			checkboxMonitor();
			//加载cpt文件
			showReport();
		}).DataTable({
			language: language_CN,
			lengthMenu: [18],
			destroy:true,
			dom: 'Brtip',
			buttons: [
			          ],
			          //serverSide:true,
			          ajax: {
			        	  url: $("#actionName").val()+'/queryRecords',
			        	  type:'post',
			        	  data: function(d){
			        		  $.each(recordData,function(i,k){
			        			  d[i] = k;
			        		  });
			        		  
			        	  },
			        	  dataSrc: 'data'
			          },
			          //ajax:'projectjs/inspection/json/trench_back_fill_record.json',
			          responsive: {
			        	  details: {
			        		  renderer: function ( api, rowIdx, columns ){
			        			  return renderChild(api, rowIdx, columns);
			        		  }
			        	  }
			          },
			          select: true,  //支持多选
			          columns : function(data){
			        	  console.info(data);
			          },
			           columns: [
			                /*{"data":"IPW_ID"},
				  			{"data":"RISER_NO"}, 
							{"data":"INSTALL_NUM"}*/
	  				  ],
	  				  order: [[ 0, "desc" ]],
			          columnDefs: [{
			        	  "targets":0,
			        	  "render": function ( data, type, row, meta ) {
			        		  return addCheckBox(data,row.pcId);
			        	  }
			          }],
			          fnHeaderCallback : function(oSettings,data){
			        	  //重新绘制表格
			        	  $('#recordListTable tr').eq(0).empty();
			        	  //var html='<tr>';
			        	  var html='';
			        	  $.each(data,function(i,e){
			        		  //对e进行排序
			        		  var objData = {};
			        		  objData.IPW_ID = objData
			        		  console.info(o);
			        		  for(var o in e){
			        			  if('IPW_ID' == o){
			        				  html += '<th>操作</th>';
			        			  }
				        		  if('RISER_NO' == o){
				        			  html += '<th>立管编号</th>';
				        		  }else if('INSTALL_NUM' == o){
				        			  html += '<th>安装户数</th>';
				        		  }else if(o.indexOf('FLOOR_')!=-1){
				        			  html += '<th>'+o+'</th>';
				        		  }
			        		  }
			        	  })
			        	  console.info(html);
			        	 // html += '</tr>';
			        	  $('#recordListTable tr').append(html);
			          },
			          fnDrawCallback : function(oSettings,datas){
			        	  console.info(datas);
			          },
			          ordering:false
		});
	}
}
var trSelectedBackRecord = function(v, i, index, t, json){
	if(json.ipwId){
		t.cgetDetail("auditForm",$("#actionName").val()+'/viewRecordById','',queryBackRecord);
	}else{
		t.cgetDetail(true);
	}


	
}
var queryBackRecord = function(data){
	if(operate){
		$("#auditForm").toggleEditState(true);
	}
}

/**
 * 页面初始化
 */
var indoorPocketWatch = function(){
	"use strict";
    return {
        //main function
        init: function () {
        	handleProjectCheckList();
        	/**
        	 * 切换报验页签
        	 */
        	$("#listTab, #auditTab, #signTab").off("shown.bs.tab").on("shown.bs.tab",function(){
        		
        		if($(this).is($("#listTab,#signTab"))){
    				if($(this).is($('#listTab'))){
    					if(!$.fn.DataTable.isDataTable('#projectCheckListTable')){
    						//初始化列表
    						handleRecordList();
            			}else{
            				$('#projectCheckListTable').cgetData(true);
            				$(".update-show").addClass("hidden");
            				$('#signForm').toggleEditState(false);
            				showReport();
            			}
    				}else{
    					if(trSData.projectCheckListTable.json && $('#pcId').val()!=""){
    						showReport();
        				}else{
        					$('.addClear').val('');
        					//清空签字
        					$('.clear-sign').click();
        					showReport();
        				}
    					/*
    					setTimeout(function(){
        				    $("#projectImagesList").getImagesList({
        				    	"projId": getProjectInfo().projId,
        				    	"stepId": getStepId(),
        				    	"projNo": getProjectInfo().projNo,
        				    	"busRecordId": $("#pcId").val() || '-1'
        				    });
        				},300);*/
    				}
        		}else if($(this).is($("#auditTab"))){
        			
        			handleRecordList();
        			
        		}
        	});
        }
    };
}();
