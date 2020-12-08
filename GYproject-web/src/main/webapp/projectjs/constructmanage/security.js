
//用于区分是新增，还是修改
var saveFlag = "";
var handlePriceSet = function() {
	"use strict";
	$("#price_panel_box").cgetPart($("#price_panel_box"),"",priceRightCallBack);
};

var priceRightCallBack = function(){
	$(".jstree-clicked").click();
};

var handleJstreeAjax = function() {
    $('#jstree-price').jstree({
        "core": {
            "themes": { "responsive": false },
            "check_callback": true,
            'data': {
                'url': function (node) {
                    return 'priceSet/getTreeData';
                },
                'data': function (node) {
                    //return { 'id': node.id };
                },
                "dataType": "json"
            }
        },
        "types": {
            "default": { "icon": "fa fa-folder text-warning fa-lg" },
            "file": { "icon": "fa fa-file text-warning fa-lg" }
        },
        "plugins": [ "dnd", "state", "types" ]
    });
    $('#jstree-price').on("ready.jstree",function(){
    	console.info("Tree is ready.");
    	handlePriceSet();
    });
};

$(document).on("click",".jstree-node",function(){
	var level = $(this).attr("aria-level");
	if(level == "3"){
		$("#deptId").val($(this).attr("id"));
	}
	if(level == "2"){
		$("#corpId").val($(this).attr("id"));
	}
	if($(this).attr("aria-selected")==="true"){
		level = $(this).attr("aria-level");
		//分公司可增加价格
		if(level === "3"){
			$(".editbtn,#jietijia").addClass("hidden");
			$("#priceDetailform,.insertPriceSet,.singlePrice").removeClass("hidden");
			
		}else if(level == "4"){   							//价格名称
			
			$(".editbtn").addClass("hidden");
			$("#priceDetailform,.insertPriceSet,.singlePrice").removeClass("hidden");
			$("#reset").click();
			$("#jietijia").addClass("hidden");
			$("#jietijia").ladderPrice({});
			$("#jietijia").hideMask();
			
		}else if(level == "5"){								//价格版本号
			$("#priceDetailform").removeClass("hidden");
			var strs = $(this).attr("id").split(",");
			//查询详述内容
			var priceId = strs[0];
			var priceVersion = strs[1];
			$("#jietijia").removeClass("hidden");
			priceSetDetail(priceId,priceVersion);
		}else{
			//节点树选中节点为其它时
			$("#priceDetailform").addClass("hidden");
			$("#jietijia").addClass("hidden");
			$(".editbtn").addClass("hidden");
		}
		
	}
	
});
var priceSetDetail = function(priceId,priceVersion){
	var f = $("#priceDetailform");
    //移除表单验证UI
	if(f.parsley()!=null && f.parsley()!=undefined){
		if (f.parsley().isValid() || !f.parsley().isValid()) {
			f.parsley().reset();
		}
	}
    //切换表单元素可编辑状态
    f.toggleEditState(false, "all");
    $("div.savediv").addClass("hidden");
    var m="priceId="+priceId+"&priceVersion="+priceVersion;
    var url = "priceSet/priceSetDetail?"+m;
    $.ajax({
        type: 'POST',
        url: url,
        dataType: 'json',
        success: function (data) {
            //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
            var selects = f.find('select[disabled]');
            selects.removeAttr("disabled");
            //表单反序列化填充值
            f.deserialize(data);
            if(data.priceVersion!==""){
            	$("#priceVersion").val(data.priceVersion.toFixed(1));
            }
			var deptId = data.deptId;
			var priceId = data.priceId;
			//验证是否可调价
			isAdjustPrice(deptId,priceId);
            //有disabled属性的下拉菜单元素对象重新添加禁用属性
            selects.attr("disabled","disabled");
            f.fadeIn(200);
            $('html, body').animate({
                scrollTop: $("body").offset().top
            }, 250);
            $(".editbtn").removeClass("hidden");
            $(".adjustPriceSet,.savePriceSet,.insertPriceSet,.cancelSave,.updatePriceSet,.deletePriceSet").addClass("hidden");
            var releaseFlag = data.releaseFlag;
            if(releaseFlag === "1"){
            	//已发布
            	$(".publishPriceSet").addClass("hidden");
            }else{
            	$(".publishPriceSet,.updatePriceSet,.deletePriceSet").removeClass("hidden");
            	
            }
            billingRules(data);
            
            
        },
        error: function (jqXHR, textStatus, errorThrown) {
            
        }
    });
    
}
/**计费规则不同，展示效果不同*/
var billingRules = function(data){
	
	var billingRulesId = data.billingRulesId;
	if(billingRulesId == "1"){
		
		$("#jietijia,.single").addClass("hidden");
		$("#price").val(data.price1);
		$("#price1").val(data.price1);
		$(".singlePrice").removeClass("hidden");
	}else if(billingRulesId == "2"){
		$(".singlePrice").addClass("hidden");
		$("#jietijia,.single").removeClass("hidden");
		var pricedata = {};
	    var level1 = data.level1;
	    var price1 = data.price1;
	    var level2 = data.level2;
	    var price2 = data.price2;
	    var level3 = data.level3;
	    var price3 = data.price3;
	    var level4 = data.level4;
	    var price4 = data.price4;
	    var price5 = data.price5;
	    //显示出阶梯价
	    if(data.ladderLevel==1){
	    	pricedata[level1] = price1;
	    }else if(data.ladderLevel ==2){
	    	pricedata = {level1:price1,"top":price2};
	    }else if(data.ladderLevel==3){
	    	pricedata[level1] = price1;
	    	pricedata[level2] = price2;
	    	pricedata["top"] = price3;
	    }else if(data.ladderLevel==4){
	    	pricedata[level1] = price1;
	    	pricedata[level2] = price2;
	    	pricedata[level3] = price3;
	    	pricedata["top"] = price4;
	    }else if(data.ladderLevel == 5){
	    	pricedata[level1] = price1;
	    	pricedata[level2] = price2;
	    	pricedata[level3] = price3;
	    	pricedata[level4] = price4;
	    	pricedata["top"] = price5;
	    }
		$("#jietijia").ladderPrice(pricedata);
		$("#jietijia").hideMask();
	}else{
		//非单一计费 阶梯计费
		$("#jietijia,.single").addClass("hidden");
		$("#price").val(data.price1);
		$("#price1").val(data.price1);
		$(".singlePrice").removeClass("hidden");
	}
	
}
/**是否可调价*/
var isAdjustPrice = function(deptId,priceId){
	 $(".infodetails").loadMask();
	 var m="deptId="+deptId+"&priceId="+priceId;
	 var url = "priceSet/isAdjustPrice?"+m;
	 $.ajax({
	        type: 'POST',
	        url: url,
	        dataType: 'json',
	        success: function (data) {
	        	//可调价（不存在未发布的价格版本）
	        	if(data ===true){
	        		$(".adjustPriceSet").removeClass("hidden");
	        	}
	        	$(".infodetails").hideMask();
	        }
	 });
}
/*价格发布**/
$(document).on("click",".publishPriceSet",function(){
	var url = "priceSet/pricePublish";
	var priceId = $("#priceId").val();
	var priceVersion = $("#priceVersion").val();
	priceVersion = priceVersion.substring(0,priceVersion.length-2); 
	url = url+"?priceId="+priceId+"&priceVersion="+priceVersion;
	$("#priceDetailform").formSave(url,'','',publishBack,true,false);
});

var publishBack = function(data){
	var content = "价格发布成功!";
	if(data === "true"){
		$(".publishPriceSet").addClass("hidden");
		$(".deletePriceSet,.updatePriceSet,.savePriceSet,.cancelSave,").addClass("hidden");
	}else{
		content = "价格发布失败!";
	}
	var myoptions = {
        	title: "提示信息",
        	content: content,
        	accept: savePopClose,
        	chide: true,
        	icon: "fa-check-circle"
     }
     $("body").cgetPopup(myoptions);
}

var savePopClose = function(){
	
}

/**增加*/
$(document).on("click",".insertPriceSet",function(){
	saveFlag = "insert";
	$("#reset").click();
	$("#jietijia").ladderPrice({});
	$("#jietijia").hideMask();
	$("#jietijia").removeClass("hidden");
	$("#priceDetailform").toggleEditState(true);
	$(".savePriceSet,.cancelSave").removeClass("hidden");
	$(".insertPriceSet").addClass("hidden");
});

/**调价*/
$(document).on("click",".adjustPriceSet",function(){
	
	$("#priceDetailform").toggleEditState(true);
	$("#priceName").attr("readOnly",'readOnly');
	$("#priceId").val('');
	$(".savePriceSet,.cancelSave,.editbtn").removeClass("hidden");
	$(".adjustPriceSet,.insertPriceSet,.publishPriceSet").addClass("hidden");
});

/**保存*/
$(document).on("click",".savePriceSet ",function(){
	var url = "priceSet/insertPrice";
	if(saveFlag === "update"){
		url = "priceSet/updatePrice";
	}
	//区分是单一计费还是阶梯计费
	var billingRulesId = $("#billingRulesId").val();
	
	if(billingRulesId ==="1"){					//单一计费
		//一阶价格
		$("#ladderLevel").val("1");  
	}else if(billingRulesId ==="2"){     		//阶梯计费
		//数据：
		var ladder = $("#jietijia .pricelist").attr("data-ladder");
		//单引号替换成双引号
		ladder = ladder.replace(/'/g, '"');
		var jsonLadder = JSON.parse(ladder);
	    var i=0;
	    var data ={};
		$.each(jsonLadder,function(num,price){
			i++;
			data["price"+i] = price;
			if(num!=="top"){
				data["level"+i] = num;
			}
		});
		var price1 = data.price1 === undefined?"":data.price1;
		var price2 = data.price2 === undefined?"":data.price2;
		var price3 = data.price3 === undefined?"":data.price3;
		var price4 = data.price4 === undefined?"":data.price4;
		var price5 = data.price5 === undefined?"":data.price5;
		var level1 = data.level1 === undefined?"":data.level1;
		var level2 = data.level2 === undefined?"":data.level2;
		var level3 = data.level3 === undefined?"":data.level3;
		var level4 = data.level4 === undefined?"":data.level4;
		
		//阶数：
		var length = $("#jietijia .pricelist").attr("data-length");
		$("#ladderLevel").val(length);
		$("#price").val(price1);
		$("#price1").val(price1);
		$("#price2").val(price2);
		$("#price3").val(price3);
		$("#price4").val(price4);
		$("#price5").val(price5);
		$("#level1").val(level1);
		$("#level2").val(level2);
		$("#level3").val(level3);
		$("#level4").val(level4);
	}
	
	var priceVersion = $("#priceVersion").val();
	var i = priceVersion.indexOf(".");
	if(i>0){
		priceVersion = priceVersion.substring(0,i);
		$("#priceVersion").val(priceVersion);
	}
	$("#priceDetailform").formSave(url,'','',savePriceBack);
	
});

var savePriceBack = function(data){
	
	if(data === true || data ==="true"){
		
		$("#priceDetailform").toggleEditState(false);
		
		//刷新树结构
		treeReload();
	}
	
}

/**价格修改*/
$(document).on("click",".updatePriceSet",function(){
	saveFlag = "update";
	$("#jietijia").removeClass("hidden");
	$("#priceDetailform").toggleEditState(true);
	$("#priceId,#priceName").attr("readOnly","readOnly");
	//修改按钮隐藏、保存、放弃显示
	$(".updatePriceSet").addClass("hidden");
	$(".savePriceSet,.cancelSave").removeClass("hidden");
});

/**取消价格*/
$(document).on("click",".cancelSave",function(){
	
	$("#priceDetailform").toggleEditState(false);
	$(".savePriceSet,.cancelSave").addClass("hidden");
	if(saveFlag === "update"){
		$(".updatePriceSet").removeClass("hidden");
	}
		
	
});
/**价格删除*/
$(document).on("click",".deletePriceSet",function(){
	var popoptions = {
			title: '提示',
			content: '您确定删除该价格信息吗？',
			accept: deleteDone
    };
	$("body").cgetPopup(popoptions);
});

var deleteDone = function(){
	
	var priceId = $("#priceId").val();
	var priceVersion = $("#priceVersion").val();
	var i = priceVersion.indexOf(".");
	if(i>0){
		priceVersion = priceVersion.substring(0,i);
	}
	var m="priceId="+priceId+"&priceVersion="+priceVersion;
	var url = "priceSet/deletePrice?"+m;
	$.ajax({
	        type: 'POST',
	        url: url,
	        dataType: 'json',
	        success: function (data) {
	        	var content = "";
	        	var accept = popClose;
	        	//可调价（不存在未发布的价格版本）
	        	if(data === true){
	        		content = "删除成功！";
	        		accept = treeReload;
	        	}else{
	        		content = "删除失败！";
	        	}
	        	var myoptions = {
        	        	title: "提示信息",
        	        	content: content,
        	        	accept: accept
        	    }
        	    $("body").cgetPopup(myoptions);
	        	$(".infodetails").hideMask();
	        }
	 });
}
var treeReload = function(){
	
	$('#jstree-price').jstree().refresh();
	
}

/**计费规则*/
$(document).on("change","#billingRulesId",function(){
	var billingRulesId = this.value;
	if(billingRulesId === "1"){				//单一计价
		$("#price,#cycleStartTime,#settlePeriod,settleDate").val("");
		$(".single,#jietijia").addClass("hidden");
		$(".singlePrice").removeClass("hidden");
	}else if(billingRulesId === "2"){			//阶梯计价
		$(".singlePrice").addClass("hidden");
		$("#jietijia,.single").removeClass("hidden");
		$("#jietijia").ladderPrice({});
		$("#jietijia").hideMask();
	}
	
});
var priceSet = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript('assets/plugins/jstree/dist/jstree.min.js').done(function() {
                handleJstreeAjax();
            });
        }
    };
}();