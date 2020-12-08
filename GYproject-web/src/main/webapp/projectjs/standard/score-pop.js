var scoretable;   //资料标准列表

var searchData={};
var type=$("#deptName option:selected").val();
/**
 * 初始化评分标准列表
 */
var handleScoreStandard = function() {
	"use strict";
	searchData.deptId = type;
	searchData.projId=$("#projId").val();
	console.info('projId..'+$("#projId").val());
    if ($('#scoreTable').length !== 0) {
    	scoretable= $('#scoreTable').on( 'init.dt',function(){
   			//默认选中第一行
    		//$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#scoreTable').hideMask();
   			//重新渲染
   			$(this).bindInputsChange(InputChange);
   			//下拉查询监听
   			selectSearch();
   			//保存监听
   			saveMonitor();
   			//求和
   			amount();
   			//输入框change
   			//InputChange();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
                { text: '保存', className: 'btn-sm btn-query business-tool-btn saveBtn' }
            ],
            //启用服务端模式，后台进行分段查询、排序
			//serverSide:true,
            ajax: {
                url: 'scoreStandard/queryGrade',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/standard/json/score.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"ssId","className":"none never"},
	  			{"data":"ssDes"}, 
	  			{"data":"ssDes"},
	  			{"data":"ssScore","className":"text-right"},
	  			{"data":"grade","className":"text-right"}
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
		    },{
	    	targets: 1,
			render: function ( data, type, row, meta ) {
				if(type === "display"){
					if(data === null){
						data = "";
					}else{
						if(row.dept&&row.dept.deptName){
							return row.dept.deptName;
						}
					}
					
				}
				return "";					
				}	
 		    },{
 				targets: 3,
 				render: function ( data, type, row, meta ) {
 					if(data == null){
 						data = "";
 					}
 					if(type === "display"){
 						var tdcon = '<div class="tdInnerInput"><input disabled="disabled" class="form-control input-sm  text-right" data-parsley-type="number"  name="'  + 'scoreStandard" id="' + row.ssId + '_grade" value="'+data+'"></div>';
 						return tdcon;
 					}else{
 						return data;
 					}
 			    }
 			},{
 				targets: 4,
 				render: function ( data, type, row, meta ) {
 					if(data == null){
 						data = "0";
 					}
 					if(type === "display"){
 						var tdcon = '<div class="tdInnerInput"><input data-val='+row.ssScore+'  class="form-control input-sm  text-right"   data-parsley-type="number"  name="'  + 'grade" id="' + row.ssId + '_grade"    value="'+data+'"></div>';
 						return tdcon;
 					}else{
 						return data;
 					}
 			    }
 			}]
        });
    }
};

//下拉查询监听
var selectSearch=function(){
	$('#deptName').on('change',function(){
		var deptId = $('#deptName').val();
		searchData = {};
		searchData.deptId = deptId;
		searchData.projId=$("#projId").val();
		$('#scoreTable').cgetData(true,amount); 
	});
}

//保存监听
var saveMonitor=function(){
	$(".saveBtn").off().on("click",function(){
		var t = $("#scoreTable");
		if(t.checkInputs()){
			var totalRow = 0 
			$("#scoreTable [name='grade']").each(function(){
				totalRow += parseInt($(this).val()); 
			})
			if(totalRow>100){
				$("body").cgetPopup({
					title: '查询',
					content: '评分和不能大于100分,请重新打分！',
					accept: sureDone
				});
				
			}
			
			var dataObj={};
			var data1=[];
			var json=$("#scoreTable").DataTable().data();
			dataObj.list = [];
			$.each(json, function(k,v){
				dataObj.list.push(v);
			})
			
			dataObj.projId=trSData.json.projId;
			dataObj.deptId=$('#deptName').val();
			var data=JSON.stringify(dataObj);
			if(response){
				response.abort();
		    }
			var response = $.ajax({
				url: "scoreStandard/insertGrade",
		          type: "POST",
		          timeout : 5000,
		          contentType: "application/json;charset=UTF-8",
		          data: data,
		          success: function (data) {
		        	  if (data === "true") {
		        		  var myoptions = {
		                      	title: "提示信息",
		                      	content: "保存数据成功！",
		                      	accept: popClose,
		                      	chide: true,
		                      	icon: "fa-check-circle"
		                  }
		                  $("body").cgetPopup(myoptions);
		        		  
		        	  }else{
		        		  $("body").cgetPopup({
		                  	title: "提示信息",
		                  	content: "数据保存失败, 请重试! <br>" + data,
		                  	accept: popClose,
		                  	chide: true,
		                  	icon: "fa-exclamation-circle"
		                  });  
		        	  }
		          }
			})
		}	
	})
}


var successBack=function(){
	
}

var amount=function(){
	
	//分值求和
	var scoreStandard = 0 
	$("#scoreTable [name='scoreStandard']").each(function(){
		scoreStandard += parseInt($(this).val()); 
	})
	//console.info('score..'+scoreStandard);
	$("tfoot .score-amount:visible").last().text(scoreStandard);
	
	//打分求和
	var totalRow = 0 
	$("#scoreTable [name='grade']").each(function(){
		totalRow += parseInt($(this).val()); 
	})
	/*if(totalRow>100){
		$("body").cgetPopup({
			title: '查询',
			content: '评分和不能大于100分,请重新打分！',
			accept: sureDone
		});
		
	}*/
	$("tfoot .total-amount:visible").last().text(totalRow);
	InputChange();
}


var InputChange=function(v,input,t,dt){
	//$('#scoreTable [name=grade]').on("change",function(){
	//当前值
	/*var currentNum=$(this).val();
	console.info('currentNum..'+currentNum);*/
	console.info('v..'+v);
	//上一个td的值
	var lastNum=input.attr("data-val");
	if(v>lastNum){
		$("body").cgetPopup({
			title: '查询',
			content: '评分不能大于原分值,请重新打分！',
			accept: sureDone
		});
	}
	//var lastNum=input.closest("tr").find("input[name=scoreStandard]").val();
	console.info('lastNum..'+lastNum);
	amount();
	//})
}

var sureDone=function(){
	
	var deptId = $('#deptName').val();
	searchData = {};
	searchData.deptId = deptId;
	searchData.projId=$("#projId").val();
	$('#scoreTable').cgetData(true,amount); 
}

/**
 * 初始化评分标准列表
 */
var scoreStandard = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleScoreStandard();
        }
    };
}();