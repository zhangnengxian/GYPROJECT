/*
 * 通用自定义全局变量和公共方法封装文件
 * 维护：ferrinweb
 * 修改请联系前端人员
 */

var baseUrl = $("script[src]").last()[0].src.replace("js/ecloud.js", "");
var redPointFlag = false;

//检测是否为Cordova Apk环境
var _inApk = /ferrinweb/i.test(navigator.userAgent),
csrInfo = JSON.parse(localStorage.getItem("csrInfo") || null);
var loginData = JSON.parse(localStorage.getItem("login") || null);
if(loginData){
	$(".loginId").val(loginData.result.staffId);
}


//登陆状态验证
$.ajaxSetup({   
    contentType:"application/x-www-form-urlencoded;charset=utf-8",   
    complete:function(XMLHttpRequest,textStatus){ 
    	var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); 
      	if(sessionstatus=="timeout"){
      		var basePath = location.protocol + '//' + location.host; 
      		
      		//存储请求路径，当未登陆时，登陆后将跳转到该页
        	var firstUrl = window.location.href;
        	
        	if(window.localStorage){ 
        		localStorage.setItem("firstUrl", firstUrl);
        	}
      		window.location.replace(basePath + "/login.html");
      	}   
   	}   
});

//引入Cordova库, 去除重复引用，引入百度地图api
if(_inApk) {
//	var cordovaJs = document.createElement("script");
//	cordovaJs.type = "text/javascript";
//	cordovaJs.onload = function(){
//		docm.trigger("deviceready");
//	};
//	cordovaJs.src = location.protocol + "//" + location.host + "/js/cordova.js";
//	document.getElementsByTagName("body")[0].appendChild(cordovaJs);

//	var BMapJs = document.createElement("script");
//	BMapJs.type = "text/javascript";
//	BMapJs.onload = function(){};
//	BMapJs.src = "http://api.map.baidu.com/getscript?v=2.0&ak=vxX4nmaMSrjYGS8jC2g8j0F9sp32Wyr3&services=&t=20160513110936";
//	document.getElementsByTagName("body")[0].appendChild(BMapJs);
}

//判断是否为手机和视网膜屏
var _isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Mobile Safari|Opera Mini/i.test(navigator.userAgent),
_isIE = /MSIE 9.0|MSIE 10.0|rv:11.0/i.test(navigator.userAgent) && /Trident/i.test(navigator.userAgent);
//console.info(navigator.userAgent);
if(_isMobile){
	
}else{
	$("#sidebar").removeClass("mobile-s");
	$("body").removeClass("isMobile");
}
var 
//window对象
$wds = $(window),

//document对象
docm = $(document),

aContent = $("#ajax-content"),

//定义panel最小高度
minh = aContent.innerHeight() - 71,
//定义panel最大高度
pmaxh = 0,
//定义timer
lmhTimer, popexpanTimer, eresizeTimer, messageTimer,loginTimer,workMessagesTimer,
//滚动监听基准变量
bescroll = 0,
//定义遮罩
maskhtml = '<div class="mask-html text-center"><div><i class="fa fa-2x"></i><br><p class="text-ellipsis">加载中</p></div></div>',

mobilewidth = $(window).innerWidth(),
//定义pop展开高度查询方法循环数
pophln = 0,
//定义表格行批量轮询操作对象容器
rowLoop = {},
//定义表单联动内容查询路径变量
queryUrl,
//后台菜单根索引
admin_menu_id = "9",
//天气控件设置是flag, 值为1则表示是客户手动点击设置天气，反之表示是系统自动赋值
isclient = 0,
/*
 * 表格选中行临时变量
 * 作用：配合bindDTSelect方法选中指定的行
 * 用法：selectTr["表格的id"] = 要选中的行索引
 */
selectTr = {},

/*
 * 定义表格行选中事件参数全局容器
 * 引用
 * trSData.v		包含选中行（多行选中则匹配其中的第一行）单元格（包含隐藏的列）数据的数组
 * trSData.i		选中行（多行选中则匹配其中的第一行）在该表格Datatable对象中的索引   ----- 包含bug
 * trSData.index	选中行（多行选中则匹配其中的第一行）在表格当前页的索引
 * trSData.t		选中行（多行选中则匹配其中的第一行）的jQuery对象
 * trSData.json		选中行（多行选中则匹配其中的第一行）从服务器返回的所有关联数据的json对象，可直接用于反序列化
 * trSData.jsons 	选中行的所有关联数据的json对象构成的数组
 * 
 * 2016-07-12 新增表格id绑定，目的为解决多表格共用问题，下例中mytable为对应表格的id值
 * trSData.mytable.v		包含选中行（多行选中则匹配其中的第一行）单元格（包含隐藏的列）数据的数组
 * trSData.mytable.i		选中行（多行选中则匹配其中的第一行）在该表格Datatable对象中的索引   ----- 包含bug
 * trSData.mytable.index	选中行（多行选中则匹配其中的第一行）在表格当前页的索引
 * trSData.mytable.t		选中行（多行选中则匹配其中的第一行）的jQuery对象
 * trSData.mytable.json		选中行（多行选中则匹配其中的第一行）从服务器返回的所有关联数据的json对象，可直接用于反序列化
 * trSData.mytable.jsons 	选中行的所有关联数据的json对象构成的数组
 */
trSData = {},

//dataTable中文语言包变量
language_CN = {
    "sProcessing": "处理中...",
    "sLengthMenu": "显示 _MENU_ 项结果",
    "sZeroRecords": "没有匹配结果",
    "sInfo": "显示第 _START_ 至 _END_ 项结果&nbsp;&nbsp;&nbsp;&nbsp;共 _TOTAL_ 项",
    "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    "sInfoPostFix": "",
    "sSearch": "",
    "sUrl": "",
    "sEmptyTable": "表中数据为空",
    "sLoadingRecords": "载入中...",
    "sInfoThousands": ",",
    "oPaginate": {
        "sFirst": "首页",
        "sPrevious": "上页",
        "sNext": "下页",
        "sLast": "末页"
    },
    "oAria": {
        "sSortAscending": ": 以升序排列此列",
        "sSortDescending": ": 以降序排列此列"
    }
},

row_v_x = ".row-v-1,.row-v-2, .row-v-3, .row-v-4, .row-v-5, .row-v-6, .row-v-7, .row-v-8, .row-v-9, .row-v-10, .row-v-11, .row-v-12",

//跨屏操作对象
crossPageBus = null,

crossPageOperate = function(params){
	//传链接或者菜单id
	if (!params.hash && !params.mid) return;
	//对象赋值
	crossPageBus = params;
	var hash = params.hash
	if (!hash) {
		//如果没有传链接地址，则按菜单id获取菜单的url
		hash = menu.menus.find(function(item) {
			return item.menuId === params.mid
		}).url
	}
	if (!hash) return
    //跳转路径赋值
	location.hash = hash;
	//location.hash = hash+"?projNo="+params.projectNo;
	//alert(hash+"?projNo="+params.projectNo);
}

//监听#content内容区宽度变化
handleContentResize = function(){
  	var ac = $("#content"), 
  	contentWidth = ac.width(),
  	contentHeight = ac.height(),
  	listenEResize = function(){
  		clearTimeout(eresizeTimer);
  		if(contentWidth !== ac.width() || contentHeight !== ac.height()){
  			ac.trigger("eresize");
  			contentWidth = ac.width();
  			contentHeight = ac.height();
  		}
  		eresizeTimer = setTimeout(listenEResize, 200);
  	};
  	listenEResize();
},


// ajax请求失败错误输出方法
printXHRError = function(o, msg, j, t, e){
	console.info("----------------- 报错信息 - 开始 -----------------");
    console.error(o + "() -> " + msg + ", 以下为错误详情:");
    //console.error(j);
    console.error(t);
    console.error(e);
	console.info("----------------- 报错信息 - 结束 -----------------");
	errorPrompt(j);
},


/**
 * 后台通过ajax请求异常信息
 * 返回页面进行提示
 * 便于查找问题所在
 * 传入参数data为后台报错信息
 * 出现此异常信息通过F12进行调试
 * 查看相关异常信息。
 * 外部js调用：例如errorPrompt(data)，data为后台传过来的异常信息
 */
errorPrompt = function (data) {
	// data参数暂时不用
	$("body").cgetPopup({
		title: '<h7 style="color:red">错误信息</h7>',
		content: '<h4 style="color:red">网络错误，请稍后重试！</h4>',
		ahide: 'true',
		ctext: '确定',
		newpop: 'new',
		icon: 'fa-warning',
	});
},


/*
 * json对象中null值替换为空字符串
 * 引用方法：var v = fixNull(json);
 */
fixNull = function(json){
	
	"use strict";
	
	if(json === undefined || typeof json !== "object"){
		console.warn("fixNull -> 参数不是 json 或 array 类型");
		return json;
	}
	
	var v;
	if(!$.isPlainObject(json)){
		v = [];
	    $.each(json, function (i, m) {
	        if (m === null) {
	            v.push("");
	        } else if (typeof m === 'object') {
	            v.push(fixNull(m));
	        } else {
	            v.push(m);
	        }
	    });
	}else{
		v = {};
	    $.each(json, function (i, m) {
	        if (m === null) {
	            v[i] = "";
	        } else if (typeof m === 'object') {
	            v[i] = fixNull(m);
	        } else {
	            v[i] = m;
	        }
	    });
	}
    return v;
},

//天气控件初始化
initWeather = function(){
    var v = $('[name="dlWeather"]'),
    vl = v.length;
    for(var i=0; i<vl; i++){
    	var n = v.eq(i);
  	    n.siblings("label").removeClass("wselect").siblings('[weather="' + n.val() + '"]').addClass("wselect");
    }
    v = null;
},

//列表自适应结构渲染方法
renderChild = function(api, rowIdx, columns){
	var pw = $("body").is(".isMobile") ? 32 : 64, detailwidth = $(api.table().node()).parents(".panel-body").innerWidth() - pw,
    data = $.map(columns, function (col, i) {
    	var html = '';
    	if(col.hidden){
    		html += '<div class="rowtdp" data-dt-row="';
    		html += col.rowIndex;
    		html += '" data-dt-column="';
    		html += col.columnIndex;
    		html += '"><em>';
    		html += col.title;
    		html += '</em>: ';
    		html += col.data;
    		html += '</div>';
    	}
        return html;
    }).join('');
	return data ? $('<div class="listDetailBox" style="width: auto"/>').append( data ) : false;
},

/*
 * 执行内容加载
 * @param {jQuery对象} target 容器对象
 * @param {字符串} targetUrl 请求路径
 * @param {字符串} p 请求参数
 * @param {字符串} m 额外的请求参数, 将和p连接, 所以此参数可以合并, 将来处理
 * @returns {Boolean}
 */
cloadPart = function(target, targetUrl, p, callback){
	
	"use strict";
	
    var t = '';
    //如果没有找到设置的容器对象, 说明设置错误或脚本逻辑错误
    if(target.length < 1){
        console.error("cloadPart() -> 未传入正确的容器对象");
        return false;
    }
    t = target;
    t.fadeOut(200,function(){
        $.ajax({
            type: 'POST',
            url: targetUrl,
            data: p,
            dataType: 'html',
            success: function(data) {
            	clearTimeout(lmhTimer);
                //如果容器是 panel-body , 则插入遮罩
                if(t.hasClass("panel-body")){
                    data = maskhtml + data;
                }
                t.html(data).fadeIn(200);
                $('html, body').animate({
                    scrollTop: $("body").offset().top
                }, 250);
                //如果内容过少, 给panel-body设置默认最小高度
                if(!_isMobile) aContent.panelEqualHeight();
                callback && callback();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                t.html('加载失败, 请重试!').fadeIn(200);
                printXHRError("cloadPart", "内容加载失败", jqXHR, textStatus, errorThrown);
               
            }
        });
    });
},


//pop加载完成展开效果 - 脚本开始 
popExpan = function(c, a){
	
	"use strict";
	
	var modal = $("#modal-content"), 
	screen_h = $wds.innerHeight(),
	screen_w = $wds.innerWidth(),
	modalHeader = modal.find(".modal-header"),
	header_h = modalHeader.outerHeight(true),
	header_w = modalHeader.outerWidth(true),
	footer_h = modalHeader.outerHeight(true),
	maxcon_h = screen_h - header_h - footer_h + 7;
	
	if(_isMobile){
		modal.removeClass("modal-mini modal-small modal-large modal-super").parent().addClass("mobilePop").find(".modal-body").css({"max-height": maxcon_h, "overflow-y": "scroll"});
		if(header_w < screen_w){
			modal.addClass("no-full-width");
		}
	}
	
	if(modal.is(".modal-full-width, .modal-projDetail")){
		maxcon_h = modal.is(".modal-projDetail") ? maxcon_h - 21 : maxcon_h - 7;
		modal.parent().css({"padding": 0}).find(".modal-body").css({"height": maxcon_h, "max-height": maxcon_h, "min-height": maxcon_h, "overflow-y": "auto"});
	}
	
	clearTimeout(popexpanTimer);
    //获取插入到popup的内容, 定义内容高度变量conh
    var popcontent = $(".popupcontent"),
    con = popcontent.find(".modal-body").children(":visible"), conh = 0, c = c;
    for (var i=0; i<con.length; i++){
    	var el = con.eq(i);
        if(el.is("div,form,h1,h2,h3,h4,h5,h6,h7,p,ul,ol,article,img,table,header,footer,section,li")){
            //计算内容高度
            conh += el.outerHeight(true);
        }
    }
    if(conh === 0 || pophln < conh){
    	popexpanTimer = setTimeout(function(){
    		pophln = conh;
    		popExpan(c, a);
    	},100);
    }else{
    	setTimeout(function(){
    		//移除加载中样式
    		popcontent.removeClass("popLoading");
    	    //启动回填方法
    	    if(!popcontent.is(".nocache")) popRefillData();
        	//设置popup内容区高度, 配合css实现展开动画
        	//30为内容区域默认内边距, 需计算在内
    	    popcontent.find(".modal-body").css({"height": conh + 30});
    		c && crunFunc(c, a);
    		pophln = 0;

    		//启动拖动
    		(!modal.is(".modal-full-width") && !_isMobile) && modal.dragging({
				move : 'both',
				hander:'.modal-header, .modal-footer'
			});
			
    		setTimeout(listenHeightChange,100);
        },100);
    }
},

currOptions = {},
//默认关闭弹窗的方法
popClose = function(){
    $('#commonModal').modal('hide').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
    	
        //关闭后清空内容
    	$("#second-modal-content").remove();
        $("#modal-content").html("").removeClass("modal-small modal-large modal-mini modal-super modal-full-width modal-projDetail overed");
        $("#modal-content").css({"position":"absolute", "left":"0", "right":"0", "top": "0"});
        currOptions = null;
        $("body").trigger("popclosed.pop");
    });
    
},

//默认关闭弹窗内嵌窗体的方法
innerClose = function(){
    $(".inner-close, .inner-confirm").off("click");

    $("#second-modal-content").removeClass("second-in");
    setTimeout(function(){
        $("#modal-content").removeClass("overed");
        $(".popupcontent .inner-mask").fadeOut(200,function(){
        	$(".popupcontent .inner-mask").remove();
        });
    	$("#second-modal-content").remove();
        $("body").trigger("innerpopclosed.pop");
    },50);
    $("#innercBox").fadeOut(200, function(){
    	$("#innercBox").remove();
        $("body").trigger("innerpopclosed.pop");
	});
},

/*
 * 用于执行方法并传入参数
 * 参数：
 * func, 方法名或方法名字符串或带参数的方法名字符串
 * args, 要传递给方法执行的参数，数组格式：[arg1, arg2,...]
 */
crunFunc = function(func, args){
	
	"use strict";
	
	args = !args ? [] : args;
    if(typeof(func) === "string"){
        //如果是字符串则按照有无括号、有无参数分别处理
        if(func.indexOf("()") >= 0){
            var aFn = func.replace("()","");
            if (typeof window[aFn] === 'function') {
               return window[aFn].apply(null, args);
            }
        }else if(func.match(/\(.*\)/) !== null){
            var ag = func.match(/\(.*\)/)[0];
            var aFn = func.replace(ag,"");
            //参数转为数组
            ag = ag.replace(" ","").replace("(","").replace(")","").split(",");
            ag = ag.concat(args);
            if (typeof window[aFn] === 'function') {
                return window[aFn].apply(null, ag);
            }
        }else{
            if (typeof window[func] === 'function') {
                return window[func].apply(null, args);
            }
        }
    }else{
        //不是字符串则直接执行
        return func.apply(null, args);
    }
},

//轮询监听内容变化调整弹窗高度
listenHeightChange = function(lasth){
    var con = $(".popupcontent .modal-body").children(":visible"), conh = 0;
    if(con.length < 1) return false;
    for (i=0; i<con.length; i++){
        if(con.eq(i).is("div,form,h1,h2,h3,h4,h5,h6,h7,p,ul,ol,article,img,table,header,footer,section,li")){
            //计算内容高度
            conh += con.eq(i).outerHeight(true);
        }
    }
    if(conh !== lasth) $(".popupcontent .modal-body").css({"height": conh + 30});
    setTimeout(function(){
    	listenHeightChange(conh);
    },100);
},

//移动端点击侧边栏后自动收起 - 脚本开始 
sidebarClose = function(){
    $("#page-container").removeClass("page-sidebar-toggled");
},

//轮询监听高度变化同步两列窗体等高
loopMoniHeight = function(){
	var mh = minh;
	pmaxh = mh;
	var pn = $(".panel-inverse > .panel-body");
	if(pn.parents().is(".panel-body")) return false;
	pn.addClass("min-height-auto");
	for(var i=0; i<pn.length; i++){
		var pnh = pn.eq(i).innerHeight();
		if(pnh > pmaxh) pmaxh = pnh;
	}
	if(pmaxh > (mh - 1)){
		pn.css("min-height", pmaxh);
	}
	pn.removeClass("min-height-auto");
},

/*
 * 时间戳日期操作
 */
//十位补零
add0 = function(m){
	return m < 10 ? '0' + m : m;
},

//时间戳转换为日期,不传入时间戳则返回当前时间格式化日期
format = function(timestamp,type){
	 "use strict";
     /*
      * 参数
      * timestamp：参数时间戳
      * type：格式化返回格式：不传或传入空返回日期，传入"all"返回日期+时间，传入"time"返回时间
      *
      */
     var ts = typeof(timestamp) === 'string' ? parseInt(timestamp) : timestamp;
     type = !type || type === "date" ? "default" : type;
     var time = !ts ? new Date() : new Date(ts),
         y = time.getFullYear(),
         m = time.getMonth() + 1,
         d = time.getDate(),
         h = time.getHours(),
         mm = time.getMinutes(),
         s = time.getSeconds(),
         form = {
             //十位补零
             "add0"   : function (m) {
                 return m < 10 ? '0' + m : m;
             },
             "all"    : function () {
                 var rt = "";
                 rt += y;
                 rt += '-';
                 rt += this.add0(m);
                 rt += '-';
                 rt += this.add0(d);
                 rt += ' ';
                 rt += this.add0(h);
                 rt += ':';
                 rt += this.add0(mm);
                 rt += ':';
                 rt += this.add0(s);
                 return rt;
             },
             "time"   : function () {
                 var rt = "";
                 rt += this.add0(h);
                 rt += ':';
                 rt += this.add0(mm);
                 rt += ':';
                 rt += this.add0(s);
                 return rt;
             },
             "MD"     : function () {
                 var rt = "";
                 rt += this.add0(m);
                 rt += '-';
                 rt += this.add0(d);
                 return rt;
             },
             "MDHm"   : function () {
                 var rt = "";
                 rt += this.add0(m);
                 rt += '-';
                 rt += this.add0(d);
                 rt += ' ';
                 rt += this.add0(h);
                 rt += ':';
                 rt += this.add0(mm);
                 return rt;
             },
             "MDHms"  : function () {
                 var rt = "";
                 rt += this.add0(m);
                 rt += '-';
                 rt += this.add0(d);
                 rt += ' ';
                 rt += this.add0(h);
                 rt += ':';
                 rt += this.add0(mm);
                 rt += ':';
                 rt += this.add0(s);
                 return rt;
             },
             "DHm"    : function () {
                 var rt = "";
                 rt += this.add0(d);
                 rt += ' ';
                 rt += this.add0(h);
                 rt += ':';
                 rt += this.add0(mm);
                 return rt;
             },
             "YMDHm"  : function () {
                 var rt = "";
                 rt += y;
                 rt += '-';
                 rt += this.add0(m);
                 rt += '-';
                 rt += this.add0(d);
                 rt += ' ';
                 rt += this.add0(h);
                 rt += ':';
                 rt += this.add0(mm);
                 return rt;
             },
             "YMD"  : function () {
                 var rt = "";
                 rt += y;
                 rt += '-';
                 rt += this.add0(m);
                 rt += '-';
                 rt += this.add0(d);
                 return rt;
             },
             "Hm"     : function () {
                 var rt = "";
                 rt += this.add0(h);
                 rt += ':';
                 rt += this.add0(mm);
                 return rt;
             },
             "default": function () {
                 var rt = "";
                 rt += y;
                 rt += '-';
                 rt += this.add0(m);
                 rt += '-';
                 rt += this.add0(d);
                 return rt;
             }
         };
     return form[type]();
},

//日期转为时间戳
timestamp = function(dateStr){
	
	"use strict";
	
	if(!dateStr || (typeof dateStr === 'number' && (dateStr + "").length !== 13)) {
		console.error("timestamp() -> 未传入有效参数，期望为格式化日期字符串");
		return false;
	}else if(typeof dateStr === 'number' && (dateStr + "").length === 13){
		console.warn("timestamp() -> 参数期望为格式化日期字符串，却传入时间戳，已直接返回");
		return dateStr;
	}
    var puredata = dateStr.replace(/-|年|月/g,'-').replace(/日/g,'').replace(/ /g,'-').replace(/:/g,'-'),
    part = puredata.split("-"),
    date =  new Date(part[0] ? part[0] : '',part[1] ? part[1]-1 : '',part[2] ? part[2] : '',part[3] ? part[3] : '',part[4] ? part[4] : '',part[5] ? part[5] : ''); 
    return date.getTime();
},

/*
 * 获取json对象长度
 */
jsonLength = function(json){
	
	"use strict";
	
	var i = 0;
	if(!json) return i;
	$.each(json,function(){
		i++;
	});
	return i;
},

/*
 * 判断两个json是否相等
 */
isSameJson = function(j1,j2){
	
	"use strict";
	
	if(typeof j1 !== 'object' || typeof j2 !== 'object') return false;
	var l1 = jsonLength(j1), 
	l2 = jsonLength(j2),
	re = true;
	if(l1 !== l2) return false;
	$.each(j1,function(i,n){
		if(typeof n === 'object'){
			re = isSameJson(n, j2[i]);
			if(re){
				return true;
			}else{
				return false;
			}
		}
		if(n === j2[i]){
			return true;
		}else{
			re = false;
			return false;
		}
	});
	return re;
},

//弹窗加载后回填历史记录
popRefillData = function(){
	
	"use strict";
	
	var form = $("#commonModal").find("form");
	if(form.length < 1) return false;
	var page = window.location.hash.replace("#","").replace("/","-"), 
	hid = [], 
	id = [];
	for(var i=0, l=form.length; i<l; i++){
		var fid = form.eq(i).attr("id");
		$(this).refillHistory(page + "_" + fid);
		id.push(fid);
		hid.push(page + "_" + fid);
	}
	var clearBtn = '<button class="btn btn-white pull-left pop-history-clear">清空</button>';
	$("#commonModal").find(".modal-footer").prepend(clearBtn);
	$(".pop-history-clear").off("click").on("click", function(){
		var hidl = hid.length,
		idl = id.length;
		for(var i=0; i<hidl; i++){
			$(this).clearQueryHistory(hid[i]);
		};
		for(var i=0; i<idl; i++){
			$(':input','#' + id[i]).not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected'); 
		};
	});
},

/**
 * 二维码生成
 */
generateQR = function (options) {

    var defaultOptions = {
            render    : "canvas",
            width     : 200,
            height    : 200,
            foreground: "#474747",
            background: "#ffffff",
            text      : 'This is a demo QR image.'
        },
        qrBox = $('<div>');

    $.extend(defaultOptions, options);

    qrBox.qrcode(defaultOptions);

    return qrBox;

},


//删除图片附件
deleteImage = function(t) {
	
	"use strict";
	
	var src = t.closest("li").find(".preview-btn").attr("src-orig").replace("attachments/","");

	$.ajax({
		type: 'post',
        url: 'accessoryCollect/delAccPhoto',
        data: {"alPath": src},
        success: function(data) {
        	if(data === "true"){
        		t.find(".image-del-btn").hide();
        		t.animate({"width": 0, "padding": 0},300,function(){
        			t.remove();
        		});
        	}else{
        		if(_inApk){
        			navigator.notification.alert("图片删除失败，请重试！", null, "提醒", "确定");
        		}else{
        			$("body").cgetPopup({
        				title: '提示',
        				content: '图片删除失败，请重试！',
        				ahide: true,
        				ctext: '确定',
        				icon: 'fa-warning'
        			});
        		}
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
            printXHRError("getImagesList", "图片删除失败", jqXHR, textStatus, errorThrown);
        }
	});
},

/**
 * 审核屏审核级别按钮显示
 * data  json串
 * len   审核按钮级别 
 */
getAuditLevelHtml = function(data,level,id,menuId){
	data = $.parseJSON( data );
	
	var total = [];
	if(menuId== undefined){
		menuId=getStepId();
	}
	var btnsAuth=getBtn(menuId),
	len = parseInt(level),
	waiting = "btn-info waiting-audit disabled",
	refused = "btn-info refused-audit refused",
	passed = "btn-info passed-audit passed",
	current = "btn-info current-audit",
	wtitle = "不可操作,上级审核未完成",
	rtitle = "审核未通过",
	ptitle = "审核通过",
	ctitle = "进入审核",
	btnDes = ["一级","二级","三级","四级","五级","六级","七级","八级","九级","十级"],
	html='';
	
	
	for(var i=1;i<len+1;i++){
		var l = 'btn-white',
		t = '';
		$.each(data, function(lv,st){
			var levels = "level"+i;
			if(lv === levels){
				if(st === "-1"){
					l = waiting;
					t = wtitle;
				}
				if(st === "1"){
					l = passed;
					t = ptitle;
				}
				if(st === "2"){
					l = current;
					t = ctitle;
					if(btnsAuth !== undefined){
						
						if($.inArray(i, btnsAuth) === -1){
							l += " disabled";
						}
					}else{
						l += " disabled";
					}
				}
			}
		});
		var obj = {'l':l,'t':t};
		total.push(obj);
	}
	//目前系统中最高控制为6级
	var tlen = total.length;
	for(var k=0;k<tlen;k++){
		var level = k+1;
		html += '<a class="btn ' + total[k].l + ' btn-xs m-r-10 level" data-rid="' + id + '" data-level="' + level + '" title="' + total[k].t + '">' + btnDes[k] + '</a>';
	}
	return html;
},

//消息获取
lastMessage = [],
currentMsgRequst = '',
getMessages = function(){
	"use strict";
	clearTimeout(messageTimer);
	currentMsgRequst && currentMsgRequst.abort();
	currentMsgRequst = $.ajax({
		type: 'get',
        url: 'notice/queryNotice',
        dataType: 'json',
        success: function(data) {
        	if(typeof data !== 'object') {
        		console.info("getMessages() -> 返回值类型错误, 需要json");
        		return false;
        	}
        	if(data === null) return false;
        	var msg = fixNull(data).data;
        	if(msg.length && !isSameJson(lastMessage, msg)){
        		var msg2 = msg;
        		msg2 = $.grep(msg2, function(n,i){
        			var equal = false;
        			$.each(lastMessage, function(j, k){
        				if(isSameJson(n, k)){
        					equal = true;
        					return false;
        				} 
        			});
        			return equal;
        		}, true);
        		lastMessage = msg;
        		msg2.length && showMessage(msg2);
        		
        		//
        		$.each(msg,function(i,e){
        			if(e.noticeType!='-1'){
        				redPointFlag =true;
        			}
        		})
            	//首页标签增加红点标记
            	 if(redPointFlag && $(".top-menu a").attr("data-mid")=='10'){
            		 $.each($(".top-menu"),function(i,e){
            			 $(e).find("a").eq(0).addClass("red-point");
            		 })
            	 }else{
            		 $.each($(".top-menu"),function(i,e){
            			 $(e).find("a").eq(0).removeClass("red-point");
            		 }) 
            	 }
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	if(jqXHR.readyState === 0 && jqXHR.status === 0 ) {
        		console.info("getMessages() -> 消息服务器无响应");
        		return false;
        	}
            printXHRError("getMessages", "消息查询失败", jqXHR, textStatus, errorThrown);
        }
	});
	messageTimer = setTimeout(getMessages, 150000000);
},

//消息显示
showMessage = function(msg){


	"use strict";
	$.each(msg, function(i, n){
		var date;
		if(n.noticeType=="2"){//工作通知
			date=n.area;		//当前时间显示
		}else if(n.noticeType=="3"){
			date=n.area;		//当前时间显示
		}else{	
			date=n.generateTime;//当前时间显示
		}
		setTimeout(function(){
			$.gritter.add({
				title: '<i class="fa fa-bell-o"></i> ' + n.noticeTitle + '<span class="hidden">' + n.noticeId + '</span>',
				text: n.noticeContent + '<br><span class="pull-right"><i class="fa fa-clock-o"></i> ' + date + '</span>',
				sticky: false,
				time: 900000,
				after_close: function(manual_close){}
			});
		}, 500 * i);
	});
},

loginMsgRequst='';
loginCheck=function(){

	"use strict";
	$(".loginId").val(loginData.result.staffId);
	var id=$(".loginId").val();
	clearTimeout(loginTimer);
	loginMsgRequst && loginMsgRequst.abort();
	loginMsgRequst = $.ajax({
		type:'post',
		url:'login/queryLoginCheck',
		contentType: "application/json;charset=UTF-8",
        data: $(".loginId").val(),
        success:function(data){
        	var json = data;
        	var bool = $.isPlainObject(json); //判断是否为对象  返回Boolean
        	if (!bool) {
        		json=JSON.parse(json);
        	}
        	var id=json.data.staffId;
        	var post=json.data.post;
        	if(post.indexOf(',999,')>=0|| post.indexOf(',93,')>=0){ //如果职务是管理员和数据管理员,显示签字日期并且input控件可以编辑，否则不显示
        		$('.selectSignDate input').removeAttr("readonly");
        		$('.selectSignDate input').attr("disabled",false);
        		$('.selectSignDate').removeClass('hidden');
        		$('#inspectionDate').removeAttr("readonly");   
        		$('#inspectionDate').attr("disabled",false);
        		//管理员对工程报验记录区的日期可编辑--开始
        		$('#recipientDate').removeAttr("readonly"); 
        		$('#recipientDate').attr("disabled",false);
        		$('#bakeDate').removeAttr("readonly"); 
        		$('#bakeDate').attr("disabled",false);
        		$('#weldingDate').removeAttr("readonly"); 
        		$('#weldingDate').attr("disabled",false);
        		$('#grDate').removeAttr("readonly"); 
        		$('#grDate').attr("disabled",false);
        		$('#peWeldingDate').removeAttr("readonly"); 
        		$('#peWeldingDate').attr("disabled",false);
        		$('#dpDate').removeAttr("readonly"); 
        		$('#dpDate').attr("disabled",false);
        		$('#hwDate').removeAttr("readonly"); 
        		$('#hwDate').attr("disabled",false);
        		$('#cuDate').removeAttr("readonly"); 
        		$('#cuDate').attr("disabled",false);
        		$('#inpectionDate').removeAttr("readonly"); 
        		$('#inpectionDate').attr("disabled",false);
        		$('#installDate').removeAttr("readonly"); 
        		$('#installDate').attr("disabled",false);
        		$('#installDate').removeAttr("readonly"); 
        		$('#installDate').attr("disabled",false);
        		$('#inpectDate').removeAttr("readonly"); 
        		$('#inpectDate').attr("disabled",false);
        		//管理员对工程报验记录区的日期可编辑--结束
        		$('.strengDate').addClass('hidden');
        	}else{
        		$('.selectSignDate').addClass('hidden');
        	}
        	if($(".loginId").val()!==id){
        		
        		var popoptions = {
	       				title: '提示',
	       				content: '当前登录账号发生变化,由'+$(".loginId").val()+" 变化为 "+id+" 请刷新页面！",
	       				accept: checkDone,
	       				chide : 'true',
	       		};
	       		$("body").cgetPopup(popoptions);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("数据保存失败！");
        }
	});
	loginTimer = setTimeout(loginCheck, 10000);
}


checkDone=function(){
	var basePath = location.protocol + '//' + location.host;
	window.location.replace(basePath + "/login.html");
}

/**
 * 获取工作流程通知-显示通知红点
 **/
workMsgRequst='';
getWorkMessages = function(){
	"use strict";
	clearTimeout(workMessagesTimer);
	workMsgRequst && workMsgRequst.abort();
	workMsgRequst = $.ajax({
		type: 'get',
        url: 'todoNotice/queryWaitNotice',
        dataType: 'json',
        success: function(data) {
        	if(data.data.length>0 && !redPointFlag){
        		if($(".top-menu a").attr("data-mid")=='10'){
	           		 $.each($(".top-menu"),function(i,e){
	           			 $(e).find("a").eq(0).addClass("red-point");
	           		 });
	           		redPointFlag = true;
          	 	}else{
           	 		$.each($(".top-menu"),function(i,e){
           	 			$(e).find("a").eq(0).removeClass("red-point");
           	 		});
           	 		redPointFlag = false;
           	 	}
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	if(jqXHR.readyState === 0 && jqXHR.status === 0 ) {
        		console.info("getWorkMessages() -> 消息服务器无响应");
        		return false;
        	}
            printXHRError("getWorkMessages", "消息查询失败", jqXHR, textStatus, errorThrown);
        }
	});
	workMessagesTimer = setTimeout(getWorkMessages, 50000);
}




//datatables表格发生错误时，弹框错误提示
alertTableError = function(msg){
	//datatables错误信息会将服务端返回错误信息，拼接错误table id等信息，因此需要截串以获取服务端原始错误信息
	var index = msg.indexOf("-") + 2;
	alertError(msg.substring(index, msg.length));
},

/*
* 项目施工、报验屏获取项目信息方法
*/
getProjectInfo = function(){
	
	"use strict";
	
	var pjson = {},
	projType = $(".nav li.active .top-menu-link").is('[data-mid="12"]') || window.location.hash === '#projectConstructList/main' ? "curProjectConstructSet" : "curProjectInspectSet";
	if(window.localStorage){
		pjson = localStorage.getItem(projType) ? JSON.parse(localStorage.getItem(projType)) : false;  
	}else{
		if($("nav li.active .top-menu-link").is('[data-mid="12"]') || window.location.hash === '#projectConstructList/main'){
			pjson = curProjectConstructSet ? curProjectConstructSet : false;
		}else{
			pjson = curProjectInspectSet ? curProjectInspectSet : false;
		}
	}
	return pjson;
},

/*
* 施工报验屏需要选择项目才能继续
*/
loadProjectList = function(){
	
	"use strict";
	
	var getList = function(){
		var url = $(".nav li.active .top-menu-link").is('[data-mid="12"]') || window.location.hash === '#projectConstructList/main' ? "#projectConstructList/main" : "#projectInspectList/main";
		window.location.hash = url;
		$("#sidebar-nav").find(".expand").removeClass("expand").end().find(".active").removeClass("active").end().find(".has-sub ul").css({"display": ''});
		$('[data-mid="1205"],[data-mid="1313"]').parent().addClass("active");
	};
	if(_inApk){
		window.history.back();
		navigator.notification.alert('需要先选择一个项目才能继续操作！', getList, '请先选择一个项目', '确定');
	}else{
		window.history.back();
		$("body").cgetPopup({
			title: '请先选择一个项目',
			content: "需要先选择一个项目才能继续操作!",
			accept: getList,
			newpop: 'new',
			chide: true,
			icon: 'fa-list'
		});
	}
},

//============= 一级弹框提示 =============//
alertCommonMsg = function(title, icon, msg){
	if(_inApk){
		navigator.notification.alert(msg, myAcceptEcloud, title, '确定');
	}else{
		$("body").cgetPopup({
			title: title,
			content: msg,
			chide:true,
			icon:icon,
			newpop: 'new',
			nocache:false,
			accept:myAcceptEcloud
		});
	}
},

//一级弹框信息提示
alertInfo = function(msg){
	alertCommonMsg('提示', 'ion-information-circled', msg);
},

//一级弹框错误提示
alertError = function(msg){
	alertCommonMsg('错误', 'ion-ios-close', msg);
},

//一级弹框警告提示
alertWarn = function(msg){
	alertCommonMsg('警告', 'ion-alert-circled', msg);
},

//============= 二级弹框提示 =============//
//二级弹框信息提示
alertInfoSecond = function(msg){
	alertCommonMsgSecond('提示', 'ion-information-circled', msg);
},

//二级弹框错误提示
alertErrorSecond = function(msg){
	alertCommonMsgSecond('错误', 'ion-ios-close', msg);
},

//二级弹框警告提示
alertWarnSecond = function(msg){
	alertCommonMsgSecond('警告', 'ion-alert-circled', msg);
},

alertCommonMsgSecond = function(title, icon, msg){
	if(_inApk){
		navigator.notification.alert(msg, myAcceptEcloud, title, '确定');
	}else{
		$("body").cgetPopup({
			title: title,
			content: msg,
			chide:true,
			icon:icon,
			newpop: 'second',
			accept:myAcceptEcloud
		});
	}
	return false;
},

myAcceptEcloud = function() {
	//nothing to do...
},

/**人员选择弹出屏
param {"inputId":"jsonName"},post--职务  unitType--单位类型 deptId--部门
*/
staffPopup = function(param, post, unitType,deptId,corpId){
	var url = '#popup/staffPop?post=' + post;
	if(!unitType){
		unitType = '';
	}
	if(!deptId){
		deptId = '';
	}
	if(!corpId){
		corpId = '';
	}
	url = url + '&unitType=' + unitType;
	$("body").cgetPopup({
		title: '人员选择',
		nocache:true,
	    content: '#popup/staffPop?post=' + post + '&unitType=' + unitType + '&deptId=' + deptId+'&corpId='+corpId,
	    accept: function(){
	    	if($("#staffTable tr.selected").length < 1){
	    		if(_inApk){
	    			navigator.notification.alert('请选择人员！', null, '提示', '确定');
	    		}else{
		    		$("body").cgetPopup({
		    			title: '提示', 
		    			content: "请选择人员！", 
		    			newpop: 'second', 
		    			accept: innerClose
		    		});
	    		}
	    		return false;
	    	}else{
	    		$.each(param, function(k, v){
	  		    	$('[id=' + k + ']').val(trSData.json[v]);
		    	});

	    	}
	    }
	});
},
queryManagementOffice=function(){
	var staffId=$("#builderId").val();
	$.ajax({
		type:'post',
		url:'popup/queryManagementOffice',
		contentType: "application/json;charset=UTF-8",
        data: staffId,
        success:function(data){
        	var json=JSON.parse(data);
        	var name=json.name;
        	var id=json.id;
        	$("#managementId").val(id);
        	$("#managementOffice").val(name);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("数据保存失败！");
        }
        
	})
},
/**部门选择弹出屏* */
deptPopup = function(param, deptType, callback){
	$("body").cgetPopup({
		title: '部门选择',
		nocache:true,
		content: '#popup/deptPop?deptType=' + deptType,
	    accept: function(){
	    	if($("#deptTablePop tr.selected").length < 1){
	    		$("body").cgetPopup({
	    			title: '提示', 
	    			content: "请选择部门！", 
	    			newpop: 'second', 
	    			accept: innerClose
	    		});
	    		return false;
	    	}else{
	    		$.each(param, function(k, v){
	  		    	$('[id=' + k + ']').val(trSData.json[v]);
		    	});
	    		callback && callback();
	    	}
	    }
	});
},

/** 设计院设计所*/
designDeptPopup = function(param, deptType, callback){
	$("body").cgetPopup({
		title: '部门选择',
		nocache:true,
		content: '#designDispatch/deptPop?deptType=' + deptType,
	    accept: function(){
	    	if($("#deptTablePop tr.selected").length < 1){
	    		$("body").cgetPopup({
	    			title: '提示', 
	    			content: "请选择部门！", 
	    			newpop: 'second', 
	    			accept: innerClose
	    		});
	    		return false;
	    	}else{
	    		$.each(param, function(k, v){
	  		    	$('[id=' + k + ']').val(trSData.json[v]);
		    	});
	    		callback && callback();
	    	}
	    }
	});
},

/**业务合作伙伴选择弹出屏
* param {"inputId":"jsonName"}, unitType--单位类型
* */
businessPartnersPopup = function(param, unitType){
	$("body").cgetPopup({
		title: '业务合作伙伴选择',
		nocache:true,
		content: '#popup/businessPartnersPop?unitType=' + unitType,
		size: "large",
	    accept: function(){
	    	if($("#businessPartnersTable tr.selected").length < 1){
	    		$("body").cgetPopup({
	    			title: '提示',
	    			content: "请选择业务合作伙伴记录！",
	    			newpop: 'second',
	    			accept: innerClose
	    		});
	    		return false;
	    	}else{
	    		$.each(param, function(k, v){
	  		    	$('[id=' + k + ']').val(trSData.json[v]);
		    	});
//	    		if(unitType=="5"){
//	    			queryCuLegalRepresentId();
//	    		}
	    	}
	    }
	});
},

queryCuLegalRepresentId=function(){
	var deptId=$("#cuId").val();
	var staffName=$("#cuLegalRepresent").val();
	var data={"deptId":deptId,"staffName":staffName};
	$.ajax({
		type:'post',
		url:'popup/queryCuLegalRepresentId',
        data: data,
        success:function(data){
        	$("#cuLegalRepresentId").val(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("数据保存失败！");
        }
        
	})
	
	
},
//获取步骤id
getStepId = function(){
	
	"use strict";
	
	var id = '',
	hash = location.hash,
	menu = $('[href="' + hash + '"]');
	if(hash && menu.length){
		id = menu.attr("data-mid");
	}else{
		var menu = $("#sidebar-nav").find(".active");
		menu = menu.find(".active").length ? menu.find(".active") : menu;
		menu = menu.find(".active").length ? menu.find(".active") : menu;
		if(menu.length) id = menu.find("a").attr("data-mid");
	}
	//console.info("stepId -> " + id);
	return id;
},
getBtn=function(menuId){
	var newArrs=[];
    $.each(menu.btns,function(k,v){
	   	if(menuId==k){ 
	   		var arrs=v.split(",");
	   		//console.info(arrs);
	   		for(var i=0,l=arrs.length; i<l; i++){
	   			newArrs.push(parseInt(arrs[i]));
	   		}
	   	}
	});
    return newArrs;
},

//窗口尺寸变化时执行方法
doFuncOnResizing = function(){
	
	"use strict";
	
	if(mobilewidth === $(window).innerWidth() && _inApk){
		//return false;
	}else{
		mobilewidth = $(window).innerWidth();
	}
	
	clearTimeout(eresizeTimer);
	var sidebar = $("#sidebar"),
	body = $("body"),
	container = $("#page-container"),
	content = $("#ajax-content");
	if(_isMobile){
    	if($wds.width() > 450){
	    	container.addClass("page-sidebar-minified");
	    }else{
	    	container.removeClass("page-sidebar-minified");
	    }
    	if(!container.is(".page-sidebar-minified")) $(".slimScrollDiv").css({"top": 0});
    	$("li.has-sub").removeClass("hover");

    	if($wds.width() > 767){
    		//$(".mobile-scroll-btn.down").css({"top": $("#header").position().top < 0 ? 0 : $("#header").height()});
    	}else{
    		$(".mobile-scroll-btn.down").css({"top": 0});
    	}
	}else{
		//处理多行布局
	    minh = content.innerHeight() - 71;
	    if(content.find(row_v_x).length > 0){
	    	content.loopMoniHeightMultiRows();
	    }else{
	    	loopMoniHeight();
	    }
	    
	    if($wds.width()>992){
	    	if(content.find(".no-equal-height").length < 1) content.panelMinHeight();
	    }
        eresizeTimer = setTimeout(handleContentResize, 500);
	}
		
	var _dtable = $(".dataTables_wrapper table.dataTable");
	if(_dtable.length > 0 && !_inApk){
		_dtable.dataTableReRender().fixPagenFloat();
    }
    
    if($(".echart-box").length > 0 && !_inApk){
    	$(".echart-box").reRenderEchart();
    }
	
    if($(".form-style-fit").length > 0){
	    var formStyleFit = $(".form-style-fit"),
	    fsfl = formStyleFit.length;
	    for(var i=0; i<fsfl; i++){
	    	formStyleFit.eq(i).styleFit();
	    }
    }
	
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report"),
    	ifrl = fr.length;
    	for(var i=0; i<ifrl; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    }
    
    //移动端导航附到页面底部
    $("#top-menu").menuAdjust();
},

/*
 * 在地图上绘制新的点和标签
 * 参数：
 * map: 百度地图对象 new BMap.Map("allmap"), 
 * point: 百度地图坐标点 new BMap.Point(lng, lat),
 * text: 标签文本,
 * style: {
 *		icon: "1.png", 			//图标图片，自定义图标需要放入images/common/map-icon目录
 *		offsetX: -22,			//标签左偏移
 *		offsetY: -24,			//标签上偏移
 *		width: "60px",			//标签宽度
 *		height: "21px",			//标签高度
 *		lineHeight : "16px",	//标签行高
 *		color: "red",			//标签文本颜色
 *		fontSize: "14px",		//标签文本字号
 *		padding: "2px",			//标签内边距
 *		border: "none"			//标签边框
 * }
 * content: {					//点击坐标标记时弹窗的内容
 *		text: html,				//html代码或静态文本
 *		style: {				//弹窗样式标题等
 *			width: 500,			//弹窗宽度
 *			height: 180,		//弹窗高度
 *			title: "<b>项目简介</b>",	//弹窗标题
 *			enableMessage: false		//是否显示发送信息图标，目前无效
 *		}
 * }
 */
addPosMarker = function(map, point, text, style, content, dbContent){
	
	"use strict";
	
	if(!point){
		console.info("绘制Label标签却没有指定坐标点");
		return false;
	}
	
	style = style || {};
	
	text = text || '记录点';
	
	content = content || {};
	
	var getWidth = function(text){
        var text = text.replace("（","(").replace("）",")"),
        tarr = text.split("");
        return 14 * (text.indexOf("(") > -1 ? tarr.length - 1 : tarr.length);
    },
    cacuWidth = getWidth(text),
    offsetX = style.offsetX || 0 - cacuWidth / 2 + 3,
	offsetY = style.offsetY || -24,
	width = style.width || cacuWidth + 5 + "px",
	height = style.height || '21px',
	lineHeight = style.lineHeight || '16px',
	color = style.color || 'red',
	fontSize = style.fontSize || '14px',
	padding = style.padding || '2px',
	border = style.border || 'red 1px solid',
	icon = style.icon || '',
	myIcon = icon ? new BMap.Icon("images/common/map-icon/" + icon, new BMap.Size(19,13)) : '',
	marker = myIcon ? new BMap.Marker(point, {icon: myIcon}) : new BMap.Marker(point),

	tipCon = '<hr/>' + (content.text || '暂无介绍'),
	tipStyle = content.style || {
		width: 200,
		height: 120,
		title: "<b>记录点详情</b>",
		enableMessage: false
	},
	
	label = new BMap.Label(text,{
		position : point,
		offset:new BMap.Size(offsetX, offsetY)
	});
	label.setStyle({
		color : color,
		fontSize : fontSize,
		height : height,
		width : width,
		lineHeight : lineHeight,
		padding: padding,
		border: border
	});
	//marker.setLabel(label);
	
	label.addEventListener("mouseover", function(e){
		marker.setTop(true);
	});
	label.addEventListener("mouseout", function(e){
		marker.setTop(false);
	});
	label.addEventListener("dblclick", function(e){
		map.setCenter(point);
		map.zoomIn();
	});
	marker.addEventListener("mouseover", function(e){
		marker.setTop(true);
	});
	marker.addEventListener("mouseout", function(e){
		marker.setTop(false);
	});
	
    //添加鼠标点击监听
	marker.addEventListener("click",function(e){
		//var p = e.target,
		//infoWindow = new BMap.InfoWindow(tipCon,tipStyle);  // 创建信息窗口对象
		//map.openInfoWindow(infoWindow,point); //开启信息窗口
		openDetailWindow();
	});
	
	function openDetailWindow(){
		var url = "#projectView/detailPage?projId=" + dbContent.pid;
		//加载弹屏
		$("body").cgetPopup({
			title: '工程信息详述',
			content: url,
			ahide: true,
			ccolor: '#59727D',
			ctext: '确定',
			size:'detail',
			nocache: true,
			icon: 'fa-file-text'
		});
	}
	
	if(dbContent && jsonLength(dbContent)){
		if(dbContent.pid){
			marker.addEventListener("rightclick",function(e){
				openDetailWindow();
			});
			label.addEventListener("click", function(e){
				openDetailWindow();
			})
		}
	}
	
	map.addOverlay(marker);
},

addLabelMarker = function(map, point, text, style, content, dbContent){
	
	"use strict";
	
	if(!point){
		console.info("绘制Label标签却没有指定坐标点");
		return false;
	}
	
	style = style || {};
	
	text = text || '记录点';
	
	content = content || {};
	
	var getWidth = function(text){
        var text = text.replace("（","(").replace("）",")"),
        tarr = text.split("");
        return 14 * (text.indexOf("(") > -1 ? tarr.length - 1 : tarr.length);
    },
    cacuWidth = getWidth(text),
    offsetX = style.offsetX || 0 - cacuWidth / 2 + 3,
	offsetY = style.offsetY || -24,
	width = style.width || cacuWidth + 5 + "px",
	height = style.height || '21px',
	lineHeight = style.lineHeight || '16px',
	color = style.color || 'red',
	fontSize = style.fontSize || '14px',
	padding = style.padding || '2px',
	border = style.border || 'red 1px solid',
	icon = style.icon || '',
	myIcon = icon ? new BMap.Icon("images/common/map-icon/" + icon, new BMap.Size(19,13)) : '',
	marker = myIcon ? new BMap.Marker(point, {icon: myIcon}) : new BMap.Marker(point),

	tipCon = '<hr/>' + (content.text || '暂无介绍'),
	tipStyle = content.style || {
		width: 200,
		height: 120,
		title: "<b>记录点详情</b>",
		enableMessage: false
	},
	
	label = new BMap.Label(text,{
		position : point,
		offset:new BMap.Size(offsetX, offsetY)
	});
	label.setStyle({
		color : color,
		fontSize : fontSize,
		height : height,
		width : width,
		lineHeight : lineHeight,
		padding: padding,
		border: border
	});
	//marker.setLabel(label);
	
	label.addEventListener("mouseover", function(e){
		marker.setTop(true);
	});
	label.addEventListener("mouseout", function(e){
		marker.setTop(false);
	});
	label.addEventListener("dblclick", function(e){
		map.setCenter(point);
		map.zoomIn();
	});
	marker.addEventListener("mouseover", function(e){
		marker.setTop(true);
		var p = e.target,
		infoWindow = new BMap.InfoWindow(tipCon,tipStyle);  // 创建信息窗口对象
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	});
	marker.addEventListener("mouseout", function(e){
		marker.setTop(false);
	});
	
    //添加鼠标点击监听
	marker.addEventListener("click",function(e){
		var p = e.target,
		infoWindow = new BMap.InfoWindow(tipCon,tipStyle);  // 创建信息窗口对象
		map.openInfoWindow(infoWindow,point); //开启信息窗口
		//openSignDetailWindow();
	});
	
	function openSignDetailWindow(){
		//var url = "#projectView/detailPage?projId=" + dbContent.pid;
		//加载弹屏
		$("body").cgetPopup({
			title: '工程信息详述',
			content: url,
			ahide: true,
			ccolor: '#59727D',
			ctext: '确定',
			size:'detail',
			nocache: true,
			icon: 'fa-file-text'
		});
	}
	
	if(dbContent && jsonLength(dbContent)){
		if(dbContent.pid){
			marker.addEventListener("rightclick",function(e){
				openDetailWindow();
			});
			label.addEventListener("click", function(e){
				openDetailWindow();
			})
		}
	}
	
	map.addOverlay(marker);
},
//根据经纬极值计算绽放级别。  
getZoom = function(map, maxLng, minLng, maxLat, minLat) { 
    var zoom = ["50","100","200","500","1000","2000","5000","10000","20000","25000","50000","100000","200000","500000","1000000","2000000"],
    z = 5;
    if(maxLng === minLng && maxLat === minLat){
    	z = 17;
    }else{
	    var pointA = new BMap.Point(maxLng, maxLat),
	    pointB = new BMap.Point(minLng, minLat),
	    distance = map.getDistance(pointA, pointB).toFixed(1);
	    for (var i = 0, zoomLen = zoom.length; i < zoomLen; i++) {
	        if(zoom[i] - distance > 0){
	            z = 18-i+4;
	        }
	    };
	}
    return z+6;
},

//加载关联操作面板
loadAttachPanel = function(){
	
	"use strict";
    if(process){
    	process.abort();
    }
	var process = $.ajax({
        url: "systemSet/findAssociateByMenuId",
        type: "POST",
        data: "menuId=" + getStepId(),
        dataType:"json",
        success: function (data) {
        	if('string' === typeof data) {
        		return;
        	}
        	var option = {};
        	$.each(data, function(k,v){
        		option[v] = {
        			tableid: $('[data-attach-table="' + v + '"]').attr("id") || $('[data-attach-table="all"]').attr("id") || ""
        		};
        	});
        	$(".attach-panel").initAttachOper(option);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("关联操作数据获取失败！", jqXHR);
        }
	});
};

//定义项目缓存变量，用于不支持本地存储的浏览器页面间传递项目信息
if(window.localStorage){
	if(!localStorage.getItem("curProjectConstructSet")) localStorage.setItem("curProjectConstructSet", '');
	if(!localStorage.getItem("curProjectInspectSet")) localStorage.setItem("curProjectInspectSet", '');
}else{
	if(!curProjectConstructSet) var curProjectConstructSet = '';
	if(!curProjectInspectSet) var curProjectInspectSet = '';
}

//窗口尺寸变化监听
var windowResizeTimer;
$wds.resize(function(){
    clearTimeout(windowResizeTimer);
    windowResizeTimer = setTimeout(doFuncOnResizing, 50);
});

(function($, docm){
	
	"use strict";
	
	$(".nav-profile").length && $.ajax({
    	 type: 'post',
    	 url: 'staff/sideBarStaff',
    	 dataType: 'html',
    	 success: function(data){
    		 $(".nav-profile").html(data);
    	 },
    	 error: function(){
    		 
    	 }
     });

	$(".profile-name").length && $.ajax({
    	 type: 'post',
    	 url: 'staff/viewBarStaff',
    	 dataType: 'html',
    	 success: function(data){
    		 $(".profile-name").html(data);
    	 },
    	 error: function(){
    		 
    	 }
     });
	var sidebar = $("#sidebar"),
	body = $("body"),
	container = $("#page-container"),
	content = $("#ajax-content"), 
	frTimer;
	
	if(_isIE){
		body.addClass("IE");
		var newStyle = document.createElement("link");
		newStyle.rel = 'stylesheet';
		newStyle.type = 'text/css';
		newStyle.href = 'css/ecloud-ie.css';
		newStyle.onload = function(){};
		document.getElementsByTagName("head")[0].appendChild(newStyle);
	}
	
	//报表面板缩放自动调整方法
	$.fn.rescaleReportPanel = function(){
		
//		return;
		
		clearTimeout(frTimer);
		var t = this, 
		p = t.parent(), 
		s = 1,
		//打印按钮
		printToolbar = '<div class="print-toolbar">';
		
		printToolbar += '<table class="table" width="100%">';
		printToolbar += '<tbody>';
		printToolbar += '<tr>';
		printToolbar += '<td><span class="print-home"><i class="fa fa-angle-double-left"></i> 首页</span></td>';
		printToolbar += '<td class="sep"> </td>';
		printToolbar += '<td><span class="print-prev"><i class="fa fa-angle-left"></i> 上一页</span></td>';
		printToolbar += '<td class="sep"> </td>';
		printToolbar += '<td class="print-page-shower"><i class="fa fa-files-o"></i> 页码：<span class="page-shower">0</span> / <span class="pages-number">0</span></td>';
		printToolbar += '<td class="sep"> </td>';
		printToolbar += '<td><span class="print-next">下一页 <i class="fa fa-angle-right"></i></span></td>';
		printToolbar += '<td class="sep"> </td>';
		printToolbar += '<td><span class="print-end">末页 <i class="fa fa-angle-double-right"></i></span></td>';
		printToolbar += '<td class="sep"> </td>';
		printToolbar += '<td class="hidden"> </td><td class="hidden"> </td>';
		printToolbar += '<td><span class="print-print"><i class="fa fa-print"></i> 打印</span></td>';
		printToolbar += '<td class="sep"> </td>';
		printToolbar += '<td><span class="print-print"><i class="fa fa-share-square-o"></i> 导出</span></td>';
		printToolbar += '</tr>';
		printToolbar += '</tbody>';
		printToolbar += '</table>';
		printToolbar += '</div>';
		
		if(p.parent().find(".print-toolbar").length < 1) {
			p.before(printToolbar);
			var iframe = $("#mainFrm");
			iframe.load(function(){
				var iframeBody = $(document.getElementById('mainFrm').contentWindow.document.body),
				    iframeToolbar = iframeBody.find(".x-toolbar > table > tbody > tr");
				
				setTimeout(function(){
					$(".page-shower").text(iframeToolbar.children(":eq(" + 4 + ")").find("input").val());
					$(".pages-number").text(iframeToolbar.children(":eq(" + 4 + ")").find("pre").text().replace("\/",""));
				},500);
				p.prev(".print-toolbar").find(".table td").off("click").on("click",function(){
					if($(this).is(".sep")) return;
					var i = $(this).index();
					
					if(i === 12){
						iframeToolbar.find(":contains(打印)").closest(".x-emb-print").click();
						setTimeout(function(){
							var s = 1 / t.data('scale-ratio'),
								exportMenu = iframeToolbar.closest('body').find(".fr-ui-core-menu.menu"),
								menuWidth = exportMenu.outerWidth(true),
								frameWidth = p.width();
							
							exportMenu.css({
								"left": (frameWidth - menuWidth) * s,
								"top": "28px",
								"-moz-transform-origin": "0 0",
								"-ms-transform-origin": "0 0",
								"-webkit-transform-origin": "0 0",
								"transform-origin": "0 0",
							    "-moz-transform": "scale(" + s + ")",
							    "-ms-transform": "scale(" + s + ")",
							    "-webkit-transform": "scale(" + s + ")",
							    "transform": "scale(" + s + ")"
							});
						}, 0);
					}if(i === 14){
						iframeToolbar.find(":contains(输出)").closest(".x-emb-export").click();
						setTimeout(function(){
							var s = 1 / t.data('scale-ratio'),
								exportMenu = iframeToolbar.closest('body').find(".fr-ui-core-menu.menu"),
								menuWidth = exportMenu.outerWidth(true),
								frameWidth = p.width();
							
							exportMenu.css({
								"left": (frameWidth - menuWidth) * s,
								"top": "28px",
								"-moz-transform-origin": "0 0",
								"-ms-transform-origin": "0 0",
								"-webkit-transform-origin": "0 0",
								"transform-origin": "0 0",
							    "-moz-transform": "scale(" + s + ")",
							    "-ms-transform": "scale(" + s + ")",
							    "-webkit-transform": "scale(" + s + ")",
							    "transform": "scale(" + s + ")"
							});
						}, 0);
					}else{						
						iframeToolbar.children(":eq(" + i + ")").children("div").find(".fr-btn-text").click();
					}

					setTimeout(function(){
						$(".page-shower").text(iframeToolbar.children(":eq(" + 4 + ")").find("input").val());
						$(".pages-number").text(iframeToolbar.children(":eq(" + 4 + ")").find("pre").text().replace("\/",""));
					},200);
				});
                var pageContentWrapper = iframeBody.find('#content-container');
                console.info(pageContentWrapper.height())
                pageContentWrapper.height(pageContentWrapper.height() - 20)
			});
		}
		if(t.is(".hidden")) return false;
		if(p.width() < 1){
			frTimer = setTimeout(function(){
				t.rescaleReportPanel();
			},100);
			return false;
		}
		s = p.width()/t.width();
		t.hide().css({
		    "-moz-transform": "scale(" + s + ")",
		    "-ms-transform": "scale(" + s + ")",
		    "-webkit-transform": "scale(" + s + ")",
		    "transform": "scale(" + s + ")",
		    "margin-bottom": t.height() * (s - 1) - (20 * s),
		    "margin-top": 0 - (27 * s)
		}).show().data('scale-ratio', s);
		
	};
    
	if(!_isMobile){		
		docm.on("change", 'input[type="text"], input[type="number"], input[type="date"]', function(){
			//$(this).addTitle();
		});
		
		//查看大图
		docm.on("click",".attach-images-list .preview-btn",function(){
			var uri = $(this).attr("src-orig"),
			imgbox = '<div class="preview-box"><img src="';
			imgbox += uri;
			imgbox += '" class="preview-image"></div>';
			body.cgetPopup({
				title: '图片预览',
				content: imgbox,
				ahide: true,
				ctext: '关闭',
				size: 'large',
				icon: 'fa-photo'
			});
		});
		
		handleContentResize();
	}
    
	if(_isMobile){
		if($wds.width() > 450){
	    	//平板设备最小化侧边栏
	    	container.addClass("page-sidebar-minified");
	    }
		
	    //移动屏下监听屏幕横竖屏切换，触发窗口resize事件
		$wds.on("orientationchange",function(){
	  		setTimeout(function(){
	  			$wds.trigger("resize");
	  		},50);
	  	});

    	//侧边栏点击行为，自动收起展开的三级菜单，通过hover样式控制
    	docm.on("touchstart", ".sidebar li.has-sub", function(){
    		$(this).addClass("hover").siblings().removeClass("hover");
    	});
    	
    	//点击屏幕其他位置，收起侧边栏和关联操作区
    	docm.on("touchstart", "#header, #ajax-content", function(e){
    		$(".attach-panel").removeClass("active");
    		sidebarClose();
    		$(".sidebar li.has-sub").removeClass("hover");
    	});
    	
    	//点击侧边栏展开按钮展开侧边栏
    	docm.on("touchstart",".mobile-sidebar-expand-btn", function(e){
    		e.preventDefault();
    		e = e || window.event;  
    	    if(e.stopPropagation) {
    	        e.stopPropagation();  
    	    } else {  
    	        e.cancelBubble = true;
    	    }
    		container.addClass("page-sidebar-toggled");
    	});
        
        docm.on("touchstart",".header-expand-btn",function(){
        	$(".header-expand-btn").removeClass("active");
        	body.removeClass("header-toggled");
            //$("#header").css("top",0);
            //$(".bottom-mainmenu").css({"bottom": 0});
            //content.css("top",$("#header").outerHeight(true)+"px");
        });
    	
    	docm.on("click", ".sidebar .sub-menu .top-menu-sub-link", function(e){
    		e = e || window.event;  
    	    if(e.stopPropagation) {
    	        e.stopPropagation();  
    	    } else {  
    	        e.cancelBubble = true;
    	    }
    		$(this).parents("li.has-sub").removeClass("hover");
    	});
    	
    	//移动端绑定侧边栏滚动按钮事件
    	docm.on("click", ".page-sidebar-minified .mobile-scroll-btn", function(){
    		var t = $(this),
    		m_menu = $(".slimScrollDiv").length > 0 ? $(".slimScrollDiv") : $('.sidebar > [data-scrollbar="true"]'), 
    		m_top = m_menu.position().top, 
    		h_menu = m_menu.outerHeight(true), 
    		wh = $(".sidebar").height();
    		if(t.is(".down")){
    			if(m_top + 1 > 0) return false;
    			if(m_top + 185 > 0){
    				m_menu.css({"top": 0});
    			}else{
    				m_menu.css({"top": 185 + m_top});
    			}
    		}
    		if(t.is(".up")){
    			if(h_menu - wh + m_top + 1 < 0) return false;
    			if(h_menu - wh + m_top - 185 < 0){
    				m_menu.css({"top": wh - h_menu});
    			}else{
    				m_menu.css({"top": m_top - 185});
    			}
    		}
    	});
    	
    	if($wds.width() > 767){
    		//$(".mobile-scroll-btn.down").css({"top": $("#header").position().top < 0 ? 0 : $("#header").height()});
    	}

    	//移动屏下向下滚动页首收起
        content.scroll(function() {
		    //获取当前滚动偏移量
		    var afscroll = content.scrollTop();
		    //如果值大于零则说明当前向下滚动
		    var res = afscroll - bescroll;
		    if (res > 0) {
		        //滚动距离超过10px将收起header区域
		        if(bescroll > 10){
		            //content.css("top", 0);
		            $(".header-expand-btn").addClass("active");
		            body.addClass("header-toggled");
		            if($wds.width() > 767){
		        		$(".mobile-scroll-btn.down").css("top", 0);
		        		sidebar.css("padding-top", 0);
		        	}
		        }
		    } else if (res < 0) {
				//$("#header").css("top",0);
				//content.css("top",$("#header").outerHeight(true)+"px");
		    }
		    //矫正基准值
		    if(bescroll !== afscroll){
		        bescroll = afscroll;
		    }
    	});
    }
	
	//坐标发生变化时触发locationchanged事件
	docm.on("locationchanged", function(e, position){
		//console.info('location has changed:', position);
		//例：position.coords.latitude
	});
	
	docm.on("click", ".changepassword-pop", function(){
		body.cgetPopup({
			title: "修改密码",
			content: "#staff/changePasswordPop",
			accept: function(){
				if($("#confirmPassword").val()===$("#newPassword").val()&&$("#newPassword").val().length>=6&&$("#newPassword").val().length<=8){
					$.ajax({
			            type: 'POST',
			            url: 'staff/getOldPassword',
			            contentType: "application/json;charset=UTF-8",
			            data: '',
			            success: function (data) {
			            	if($("#oldPassword").val()===data){
								if($("#changePassword").parsley().isValid()){
									var data=$("#changePassword").serializeJson();
								  	$.ajax({
							            type: 'POST',
							            url: 'staff/passwordSave',
							            contentType: "application/json;charset=UTF-8",
							            data: JSON.stringify(data),
							            success: function (data) {
							            	var content = "密码修改成功！";
							            	if(data === "false"){
							            		content = "密码修改失败！";
							            	}
							            	var myoptions = {
							                    	title: "提示信息",
							                    	content: content,
							                    	accept: popClose,
							                    	chide: true,
							                    	icon: "fa-check-circle",
							                    	newpop: 'new'
							                }
							                $("body").cgetPopup(myoptions);
							            },
							            error: function (jqXHR, textStatus, errorThrown) {
							                console.warn("密码修改保存失败！");
							            }
							        });
									//$("#changePassword").parsley().reset();
								}else{
									//如果未通过验证, 则加载验证UI
						        	$("#changePassword").parsley().validate();
								}
			            	}else{
			            		var myoptions = {
				                    	title: "提示信息",
				                    	content: "密码修改失败！请正确填写信息",
				                    	accept: popClose,
				                    	chide: true,
				                    	icon: "fa-check-circle",
				                    	newpop: 'new'
				                }
				                $("body").cgetPopup(myoptions);
			            	}
			            },
			            error: function (jqXHR, textStatus, errorThrown) {
			                console.warn("旧密码比对失败！");
			            }
					}); 
				}else{
					var myoptions = {
	                    	title: "提示信息",
	                    	content: "密码修改失败！请正确填写信息",
	                    	accept: popClose,
	                    	chide: true,
	                    	icon: "fa-check-circle",
	                    	newpop: 'new'
	                }
	                $("body").cgetPopup(myoptions);
				}
			},
			atext: "提交修改",
			nocache: true,
			icon: "fa-key"
		});
	});
	
	
docm.on("click", ".updateInfo-pop", function(){
		
		var url="updateInfo/viewUpdateInfoList";
		
		$("body").cgetPopup({
			title: '更新信息',
			content: "#" + url,
			atext: '确定',
			ahide:'false',
			chide:'false',
			//accept: callback,
			//callback: cinit,
			newpop: 'new',
			size: 'super',
			icon: 'fa-share',
			nocache: true
		});
		
	});


	docm.on("click", ".taskNotice-pop", function(){
		
		var url="taskNotice/main";
		
		$("body").cgetPopup({
			title: '任务门户',
			content: "#" + url,
			atext: '确定',
			ahide:'false',
			chide:'false',
			//accept: callback,
			//callback: cinit,
			newpop: 'new',
			size: 'large',
			icon: 'fa-share',
			nocache: true
		});
		
	});
	
	
	//mask加载后停止系统加载条显示
	docm.on( 'DOMNodeInserted', '#page-container', function (e) {
	    if($(e.target).find(".panel-inverse .mask-html").length > 0){
	        $(".pace .pace-activity").fadeOut(200);
	    }
	});
	
	//点击屏幕其他位置，收起关联操作区
	docm.on("click", "#header, #ajax-content", function(e){
		$(".attach-panel").removeClass("active");
	});
    
    //点击关联操作区按钮展开关联操作区
    docm.on('click', '[data-click="attach-panel-expand"]', function() {
        var targetContainer = '.attach-panel';
        if ($(targetContainer).hasClass("active")) {
            $(targetContainer).removeClass("active");
        } else {
            $(targetContainer).addClass("active");
        }
    });
    
    docm.on("click",".bottom-mainmenu a", function(e){
		e = e || window.event;
	    if(e.stopPropagation) {
	        e.stopPropagation();
	    } else {
	        e.cancelBubble = true;
	    }
		container.addClass("page-sidebar-toggled");
    });

	//删除照片
	docm.on("click",".attach-images-list .image-del-btn",function(){
		var t = $(this).parents(".attach-image-item"),
		uri = t.find(".preview-btn").attr("src-orig");
		body.cgetPopup({
			title: '删除确认',
			content: '<div class="preview-box"><img src="' + uri + '" class="preview-image"><p class="p-t-10">确认删除该图片吗？</p></div>',
			accept: {
				func: deleteImage,
				singleArgs: t
			},
			atext: '删除',
			ctext: '取消',
			icon: 'fa-warning'
		});
	});
    
    //天气控件
    docm.on("click", ".weather-tool > label", function(e){
	    var t = $(this);
	    isclient = 1;
	    if(t.is(".wselect")){
	    	t.removeClass("wselect").siblings('[name="dlWeather"]').val('');
	    	isclient = 0;
	    	return true;
	    }
	    t.addClass("wselect").siblings("label").removeClass("wselect").siblings('[name="dlWeather"]').val(t.attr("weather"));
    	isclient = 0;
    });
    

    //业务操作按钮激活状态切换
    docm.on("click", ".business-tool-btn", function(){
        if($(this).hasClass("active")){
            return false;
        }
        $(this).addClass("active").siblings(".business-tool-btn").removeClass("active");
    });
	
	//签名图片点击查看大图
	docm.on("click",".sign-data-input ~ img, .sign-data-input ~ .img",function(){
		var src = $(this).attr("src");
		body.cgetPopup({
			title: '查看签名',
			content: '<div class="preview-box"><img src="' + src + '" class="preview-image"></div>',
			accept: function(){},
			atext: '关闭',
			chide: true,
			size: 'large',
			icon: 'fa-pencil-square-o'
		});
	});
	
	//清空签名版
	docm.on("click", ".sign-resetBtn", function(){
		var sbox = $(this).parent(".signatureBox");
		sbox.jSignature("reset");
		
		var signCanvas = $(this).parent(".signatureBox").find(".jSignature:visible")[0].getContext("2d");		
		signCanvas.font = '12px Consolas';
		signCanvas.fillStyle = "#999999";
		signCanvas.shadowColor = "#ffffff";
		signCanvas.shadowBlur = 0;
		signCanvas.shadowOffsetX = 0;
		signCanvas.shadowOffsetY = 0;
		signCanvas.lineWidth = 8;
        signCanvas.textAlign = 'left';
        signCanvas.textBaseline = 'top';
	
		if(_inApk){
	        signCanvas.fillText(sbox.data("altitude") || "", 15, 5);
	        signCanvas.fillText(sbox.data("longitude") || "", 15, 24);
	        signCanvas.fillText(sbox.data("latitude") || "", 120, 24);
		}
	});
    
    //表格指定事件后清空对应的trSData值
    docm.on("page.dt search.dt order.dt row-reorder.dt destroy.dt", "table.dataTable", function(e){
    	var id = $(this).attr("id");
    	if(id) trSData[id] = {};
    });
    
    //表格指定事件后清除按钮的选中状态
    docm.on("page.dt", "table.dataTable", function(e){
    	$(this).siblings().find(".business-tool-btn").removeClass("active");
    });

    //监听dataTable表格绘制，重新计算表格自适应列宽
	docm.on("draw.dt", "table.dataTable", function(e){
		var dt = $(this).DataTable();
		if(dt.responsive && !_inApk) dt.responsive.recalc();
	});

	//dataTable表格排序或搜索后如果存在带有选中样式的行则选中该行
	docm.on("search.dt order.dt", "table.dataTable", function(){
		var dt = $(this).DataTable(),
		id = $(this).attr("id"),
		trs = $(this).find("tbody tr.selected");
		if(trs.length > 0 && !trSData[id].t) dt.row('.selected', { page: 'current' }).select();
	});
	
	docm.on("init.dt", "table.dataTable", function(){
	    $(this).fixPagenFloat();
	});
	
	//输入框去除首尾空格
	docm.on("blur", 'input[type="text"], input[type="number"], input[type="date"], input[type="email"], input[type="datetime"]', function(){
		var t = $(this);
		t.val($.trim(t.val()));
	});

	//监听弹窗关闭动作，自动添加历史记录
	docm.on("hide.bs.modal", "#commonModal", function(e){
		if($(e.target).is("#commonModal") && $(e.target).find(".modal-dialog > .nocache").length < 1){
			var form = $(this).find("form");
			if(form.length > 0){
				var page = window.location.hash.replace("#","").replace("/","-"), 
				hid = "",
				id = "", 
				data = {};
				for(var i=0; i<form.length; i++){
					id = form.eq(i).attr("id");
					hid = page + "_" + id;
					data = form.eq(i).serializeJson();
					$(this).setQueryHistory(data,hid);
				}
			}
		}
	});

    //当切换标签页时取消激活状态
    docm.on("hide.bs.tab", 'a[data-toggle="tab"]', function(){
        $(".tab-pane").eq($(this).parent().index()).find(".business-tool-btn").removeClass("active");
    });

    docm.on("shown.bs.tab", 'a[data-toggle="tab"]', function(){
    	var id = $(this).attr("href").replace("#",""),
		table = $("#" + id + ".tab-pane").find("table.dataTable"),
		tl = table.length;
		if(tl){
			for(var i=0; i<tl; i++){
				if(!$.fn.DataTable.isDataTable('#' + table.eq(i).attr("id"))) continue;
				var dt = table.eq(i).DataTable();
				if(dt.responsive) dt.responsive.recalc();
			}
		}
    });
    
    //天气控件赋值监听
    docm.on('change', '[name="dlWeather"]', function(){
    	$(this).siblings("label").removeClass("wselect").siblings('[weather="' + $(this).val() + '"]').addClass("wselect");
    });
    
    //签名控件更新监听
	docm.on("change",".sign-data-input",function(){
		var t = $(this),
		imgdata = t.val();
		if(!imgdata || imgdata === "null"){
			t.parent().find("img, .img").remove();
			return t;
		}
		var tp = t.parent();
		if(!_isIE){
			if(tp.find("img").length > 0){
				tp.find("img").attr({"src": imgdata, "title": "点击查看大图"});
			}else{
				var img = new Image();
				img.src = imgdata;
				img.title = '点击查看大图';
				tp.find(".clear-sign").before($(img));
			}
		}else{
			if(tp.find(".img").length > 0){
				tp.find(".img").css("background", "url(" + imgdata + ") center center").attr({"src": imgdata, "title": "点击查看大图"});
			}else{
				tp.find(".clear-sign").before('<div class="img" title="点击查看大图" src="' + imgdata + '" style="background: url(' + imgdata + ');"></div>');
			}			
		}
	});
	
	//监听时间戳格式化表单元素
	docm.on("change", "input.timestamp:not(.copyedInput)", function(){
		var t = $(this),
		
		type = t.hasClass("all") ? "all" : t.hasClass("time") ? "time" : "";
		t.formatTime(type);
		
	});

	//监听数字保留两位小数格式化表单元素
	docm.on("change", "input.fixed-number", function(){
		var v = $(this).val();
		if(v === undefined || v === "") return false;
		$(this).val(parseFloat(v).toFixed(2));
	});
	
	docm.on("eresize", "#content", function(){
		$(".form-style-fit").styleFit();
	});
	
	/*docm.on('click', 'a[href]', function () {
		var t = $(this);
		var href = t.attr('href')
		if(href.indexOf('javascript:')==-1&href.replace(/ /g,'').length>0){
			window.location = href
		}
		return false;
	});*/
	/*docm.on('click','a[href]',function(e){
        var t = $(this);
        var href = t.attr('href')
        if(href.indexOf('javascript:')==-1&&href.replace(/ /g,'').length>0){
            window.location=href
        }
        return false;
    });*/
})(jQuery, docm);

(function(){
	
	//用于判定元素是否为指定元素或指定元素的从级
	$.fn.__is = function(el){
		if(this.is(el) || this.closest(el).length) return true;
		return false;
	};
	
	//获取给定对象自身或满足条件的祖级对象
	$.fn.__t = function(el){
		if(this.is(el)) return this;
		if(this.closest(el).length) return this.closest(el);
		return {};
	};
	
	//面板最大化列表重新渲染
	$.fn.dataTableReRender = function(){
		
		"use strict";
		
		var t = this,
		l = this.length;
		for(var i=0; i<l; i++){
			var t = this.eq(i),
			dt = t.DataTable();
			if(dt.responsive) dt.responsive.recalc();
		}
		return t;
	};
	//面板最大化数据图表重新渲染
	$.fn.reRenderEchart = function(){
		
		"use strict";
		
		var t = this,
		l = this.length;
		for(var i=0; i<l; i++){
			this.eq(i).trigger("chartrerender");
		}
	};
	//面板点击重新加载按钮列表刷新
	$.fn.panelReload = function(){
		
		"use strict";
		
		var t = this, 
		table = t.find(".dataTables_wrapper table.dataTable"),
		form = t.find(".form-style-fit");
		if(table.length > 0){
			var tl = table.length;
			for(var i=0; i<tl; i++){
				var thist = table.eq(i),
				dt = thist.DataTable();
				dt.ajax.reload(function(){
					thist.trigger("reload.dt");
		            setTimeout(function() {
		                t.removeClass('panel-loading');
		                t.find('.panel-loader').remove();
		            }, 100);
				});
			}
		}else{
            setTimeout(function() {
                t.removeClass('panel-loading');
                t.find('.panel-loader').remove();
            }, 100);
		}

		if(form.length > 0){
			var fl = form.length;
			for(var i=0; i<fl; i++){
				form.eq(i).styleFit();
			}
		}
	};

    /*
     * 内容模块加载函数
     * @param {jQuery对象} t
     * @param {字符串} m 用于传值的额外参数
     * @returns 无
     */
    $.fn.cgetPart = function(t, m, callback) {

    	"use strict";

        //关闭侧边栏，在移动端视图有效
        sidebarClose();
        //必须传入用于获取data-c和data-post属性值的对象
        if(t === ""){
            console.error("cgetPart() -> 未传入取值对象");
            return false;
        }
        //获取data-c和data-post属性的值, 如果无值或者未定义属性则取默认值
        var h = t.attr("data-c") === "" || t.attr("data-c") === undefined ? window.location.hash : t.attr("data-c"),
        p = t.attr("data-post") === "" || t.attr("data-post") === undefined ? "" : t.attr("data-post");
        //对象必须包含data-c属性或者window.location.hash不为空, 否则没有请求页面
        if(h === undefined || h === ""){
            console.error("cgetPart() -> 触发对象未设置data-c属性或值为空");
            return false;
        }
        if(p === ""){
            //console.warn("cgetPart() -> 触发对象data-post属性值为空, 不需要则无影响");
        }
        if(p === undefined){
            p = "";
            //console.warn("cgetPart() -> 触发对象未设置data-post属性, 不需要则无影响");
        }
        //接收m参数，用于额外传值, 最终m参数将与p变量链接
        m = m !== "" && m !== "NaN" && m !== null &&  m !== "undefined" &&  m !== undefined ? m.indexOf("&") !== 0 && p !== "" ? "&" + m : m : "";
        //合并参数
        p += m;
        var targetUrl = h.replace('#','');
        //判断获取的data-c属性中是否设置了容器id并且是否包含传值
        if((targetUrl.indexOf('?') > -1 && targetUrl.indexOf('=') > -1 && targetUrl.indexOf('=') < targetUrl.indexOf('?')) || (targetUrl.indexOf('?') === -1 && targetUrl.indexOf('=') > -1)){
            var tac = new Array();
            tac = targetUrl.split("=");
            //定义p0参数用于保存data-c属性中设置的传值部分
            var p0 = "";
            if(tac.length > 2){
                //在上面的逻辑前提下, 包含两等号即说明包含传值部分, 循环拿出并放入p0变量
                for(imk = 2; imk < tac.length; imk++){
                    p0 += "=" + tac[imk];
                }
            }
            //调用cloadPart方法, 优先使用data-c属性中设置的容器对象, 获取的页面将放入该对象中
            cloadPart($("#"+tac[0]), tac[1] + p0, p, callback);
        }else{
            cloadPart(this, targetUrl, p, callback);
        }
    };
    
    /*
     * cgetPart方法的替代方法
     * 引用方式：容器的jQuery对象直接引用
     * 参数：
     * url：请求的路径
     * p：post传参
     * callback：回调函数
     */
    $.fn.cgetContent = function(url, p, callback) {
    	
    	"use strict";
    	
    	var t = this;
        //关闭侧边栏，在移动端视图有效
        sidebarClose();
        
    	if(t.length < 1){
            console.error("cgetContent() -> 指定的容器不存在");
            return false;
    	}
    	
    	if(!url){
            console.error("cgetContent() -> 没有指定正确的请求路径");
            return false;
    	}
    	
        if(!p){
            p = "";
        }
        
        t.fadeOut(200,function(){
            $.ajax({
                type: 'POST',
                url: url,
                data: p,
                dataType: 'html',
                success: function(data) {
                	clearTimeout(lmhTimer);
                    //如果容器是 panel-body , 则插入遮罩
                    if(t.hasClass("panel-body")){
                        data = maskhtml + data;
                    }
                    t.is(".tab-pane") ? t.html(data).css("display","") : t.html(data).fadeIn(200);
                    $('html, body').animate({
                        scrollTop: $("body").offset().top
                    }, 250);
                    //如果内容过少, 给panel-body设置默认最小高度
                    if(!_isMobile) aContent.panelEqualHeight();
                    callback && callback();
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    t.html('加载失败, 请重试!').fadeIn(200);
                    printXHRError("cgetContent", "内容请求失败", jqXHR, textStatus, errorThrown);
                }
            });
        });
    };

	/*
	 * 表单序列化为JSON
	 * 引用方式: var data = 表单的jQuery对象.serializeJson();
	 * 返回值为表单序列化的json对象
	 * 已关联签名数据取值
	 */
    $.fn.serializeJson = function () {
    	
    	"use strict";
    	
        //获取包含禁用属性的下拉菜单对象
        var selects = this.find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
        //去除其禁用属性, 包含禁用属性的下拉菜单不能被序列化
        selects.removeAttr("disabled");
        var serializeObj = {},
        //表单进行一般序列化
        array = this.serializeArray();
        //数组转为json对象
        $(array).each(function () {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        //重新添加禁用属性
        selects.attr("disabled","disabled");
        
        //结构化签字数据
        var signinputs = this.find(".sign-data-input");
        if(this.is("form")){
        	var id = this.attr("id");
        	signinputs = signinputs.add('.sign-data-input[form="' + id + '"]');
        }
        if(signinputs.length){
        	var sign = [],
        	sl = signinputs.length,
        	stepId = getStepId(),
        	stepDes = $('[data-mid="' + stepId + '"] > span').text() || "";
        	for(var i=0; i<sl; i++){
        		var signinput = signinputs.eq(i),
        		json = {},
        		name = signinput.attr("name"),
        		imgd = signinput.val();
        		//if(!name || !imgd.length) continue;
        		json.fieldName = name;
        		json.signClob = imgd;
        		json.signTime = $('[name="' + name + '_time"]').val() || "";
        		json.altitude = $('[name="' + name + '_altitude"]').val() || "";
        		json.latitude = $('[name="' + name + '_latitude"]').val() || "";
        		json.longitude = $('[name="' + name + '_longitude"]').val() || "";
        		json.postType = $('[name="' + name + '_postType"]').val() || "";
        		json.menuId = stepId;
        		json.menuDes = stepDes;
        		//serializeObj[name] = "";
        		if(serializeObj[name + '_time']) serializeObj[name + '_time'] = "";
        		if(serializeObj[name + '_altitude']) serializeObj[name + '_altitude'] = "";
        		if(serializeObj[name + '_latitude']) serializeObj[name + '_latitude'] = "";
        		if(serializeObj[name + '_longitude']) serializeObj[name + '_longitude'] = "";
        		if(serializeObj[name + '_postType']) serializeObj[name + '_postType'] = "";
        		sign.push(json);
        	}
        }
        if(sign && sign.length) serializeObj.sign = sign;
        //console.info(serializeObj);
        return serializeObj;
    };

	/*
	 * 表单序列化为json字符串
	 * 引用方式: var data = 表单的jQuery对象.serializeJsonString();
	 * 返回值为表单序列化的json字符串
	 */
    $.fn.serializeJsonString = function () {
    	
    	"use strict";
    	
        var jsonString = JSON.stringify(this.serializeJson());
        return jsonString;
    };

	/*
	 * 页面加载渲染过程遮罩
	 * 隐藏遮罩
	 * 各页面初始化完成调用 对象.hideMask() 方法, 其中对象指的是初始化的对象，比如$("table")等, 简单的，你也可以使用$(".mask-html")来调用
	 */
    $.fn.hideMask = function(){
    	
    	"use strict";
    	
    	var t = this,
    	mask = t.find(".mask-html").length ? t.find(".mask-html") : t.parents(".panel-body, #modal-content").find(".mask-html");
    	mask.fadeOut(200,function(){
    		mask.remove();
    	});
        return t;
    };
    
    $.fn.hideloading = function(){
    	
    	"use strict";
    	
    	var t = this,
    	mask = t.find(".loading-html");
    	if(mask.length > 0){
    		mask.fadeOut(200,function(){
    			mask.remove();
    		});
    	}
        return t;
    };

    /*
     * 动态加载遮罩
     * 引用方式: 要添加遮罩的容器jQuery对象.loadMask("");
     * @param {字符串} title 用来自定义提示文字, 默认为"加载中"
     * @param {数字/字符串} zoom 用来自定义加载图标的尺寸, 取值范围：1-4，默认为3
     * @returns 无
     */
    /*$.fn.loadMask = function(title, zoom){
    	
    	"use strict";
    	
        title = title === "" || title === undefined ? "加载中" : title;
        zoom = zoom === "" || zoom === undefined ? "" : " fa-" + zoom + "x";
        var maskhtml2 = '<div class="mask-html text-center"><div><i class="fa' + zoom + '"></i><br><p class="text-ellipsis">' + title + '</p></div></div>';
        if(this.find(".mask-html").length<1){
            this.append(maskhtml2);
        }
        this.find(".mask-html").fadeIn(200);
    }*/;
    /*
     * 加载中特效
     * @param {数字/字符串} zoom 用来自定义加载图标的尺寸, 取值范围：1-4，默认为3
     * @returns 无
     */
    $.fn.loading = function(zoom){
    	
    	"use strict";
    	
        zoom = zoom === "" || zoom === undefined ? "" : " fa-" + zoom + "x";
        var maskhtml2 = '<div class="loading-html text-center"><div><i class="fa' + zoom + '"></i></div>';
        if(this.find(".loading-html").length<1){
            this.append(maskhtml2);
        }
        this.find(".loading-html").fadeIn(200);
    };
 
	/*
	 * 主内容区等高 - 脚本开始 
	 */
    $.fn.panelEqualHeight = function(){
    	
    	"use strict";
    	
        var t = this;
        if($wds.innerWidth()>992){
        	if(t.find(".no-equal-height").length < 1) t.panelMinHeight();
        }
    };

    //设置最小默认高度
    $.fn.panelMinHeight = function () {
    	
    	"use strict";
    	
    	var row_v_x = ".row-v-1,.row-v-2, .row-v-3, .row-v-4, .row-v-5, .row-v-6, .row-v-7, .row-v-8, .row-v-9, .row-v-10, .row-v-11, .row-v-12",
    	pn = this.find(".panel-inverse > .panel-body");
    	if(this.find(row_v_x).length > 0){
    		clearTimeout(lmhTimer);
    		this.children("#content").css({"height":"100%","box-sizing":"border-box"});
    		for(var i=0; i<pn.length; i++){
    			var extrh = pn.eq(i).prev(".panel-heading").length > 0 ? 56 : 16;
    			pn.eq(i).css({"height":pn.eq(i).parents(row_v_x).innerHeight() - extrh});
    		}
    		//alert(1);
    	}else{
//	        if(pn.length < 4){
//	            pn.css("min-height", minh);
//	        }
	        setTimeout(function(){loopMoniHeight();},200);
    	}
    };

	$.fn.loopMoniHeightMultiRows = function(){
		
		"use strict";
		
		var pn = this.find(".panel-inverse > .panel-body"),
		pl = pn.length;
		this.children("#content").css({"height":"100%","box-sizing":"border-box"});
		for(var i=0; i<pl; i++){
			var thispn = pn.eq(i),
			extrh = thispn.prev(".panel-heading").length > 0 ? 56 : 16;
			thispn.css({"height":thispn.parents(row_v_x).innerHeight() - extrh});
		}
	};

    /*
     * 表单元素可编辑状态切换 - 脚本开始 
     * 引用方式: 表单jQuery对象.toggleEditState();
     * @param {布尔值} toggle  不传值表示切换编辑状态，传true或false表示打开或关闭可编辑
     * @param {字符串} target  不传值表示只操作可切换的元素, 传入not表示操作不可切换的元素, 传入all表示操作两者
     * @returns 无
     */
    $.fn.toggleEditState = function(toggle,target){
    	
    	"use strict";
    	
        target = target === undefined || target === "" ? "editable" : target;
        //获取可切换对象
        var fields = this.find(".field-editable"),
        //获取按钮控件
        btns = this.find("label:not(.signature-tool) + div > input ~ .btn:not(.alwaysusable)"),
        //console.info(btns);
        //单独处理select元素
        fields_s = this.find("select.field-editable, [type=checkbox].field-editable, [type=radio].field-editable");
        if(toggle === true && (target === "editable" || target === "all")){
            //开启可编辑
            fields.removeAttr("readonly disabled");
            btns.removeClass("disabled");
            this.find(".sign-data-input").toggleSign(true);
        }else if(toggle === false && (target === "editable" || target === "all")){
            //关闭可编辑
            fields.attr("readonly", true);
            for(var i=0; i<btns.length; i++){
            	if(btns.eq(i).siblings(".field-editable").length > 0 || btns.eq(i).siblings(".field-not-editable").length > 0) btns.eq(i).addClass("disabled");
            }
            //下拉菜单添加disabled属性
            fields_s.attr("disabled","disabled");
            this.find(".sign-data-input").toggleSign(false);
        }else if(target === "editable" || target === "all"){
            if(fields.attr("readonly")){
                fields.removeAttr("readonly disabled");
                btns.removeClass("disabled");
                this.find(".sign-data-input").toggleSign(true);
            }else{
                fields.attr("readonly", true);
                for(var i=0; i<btns.length; i++){
                	if(btns.eq(i).siblings(".field-editable").length > 0 || btns.eq(i).siblings(".field-not-editable").length > 0) btns.eq(i).addClass("disabled");
                }
                fields_s.attr("disabled","disabled");
                this.find(".sign-data-input").toggleSign(false);
            }
        }
        //获取不可切换对象
        var fieldsNotAllowed = this.find(".field-not-editable"),
        //单独处理select元素
        fieldsNotAllowed_s = this.find("select.field-not-editable, [type=checkbox].field-not-editable, [type=radio].field-not-editable");
        if(toggle === true && (target === "not" || target === "all")){
            fieldsNotAllowed.removeAttr("readonly disabled");
        }else{
            fieldsNotAllowed.attr("readonly", true);
            fieldsNotAllowed_s.attr("disabled","disabled");
        }

        return this;
    };
    
	/*
	 * 修正翻页按钮超出 - 脚本开始
	 * 用于修正容器过窄导致的翻页按钮超出的问题
	 */
    $.fn.fixPagenFloat = function(){
    	
    	"use strict";
    	
        var wrapper = this.parents(".dataTables_wrapper"),
        footbar = wrapper.find(".dataTables_paginate, .dataTables_info"),
        headbar = wrapper.find(".dataTables_filter, .dt-buttons"),
        fw = wrapper.find(".pagination").outerWidth() + wrapper.find(".dataTables_info").outerWidth();
        if(fw > wrapper.parent().width() && $wds.innerWidth() > 767){
        	footbar.addClass("clearboth");
        }else{
        	footbar.removeClass("clearboth");
        }
        var tw = wrapper.find(".dataTables_filter").outerWidth() + wrapper.find(".dt-buttons").outerWidth();
        if(tw < (wrapper.parent().width() + 1)){
        	headbar.removeClass("clearboth");
        }else{
        	headbar.addClass("clearboth full-width");
        }
    };

	/*
	 * 更便捷的调用popup
	 * 引用方式: $("body").cgetPopup();
	 * 参数：options
	 * 例子：
	 *  var options = {
	 * 		title		: '提示',						//弹窗标题
	 * 		content		: '#custbat/custBatPop',       	//支持静态文本和远程请求，如果是请求文件，请以#开头
	 * 		accept		: 'someFuncName' || {func: 'someFuncName', args: '' || []},		//点击确定按钮后触发的动作
	 * 		cancel		: 'someFuncName' || {func: 'someFuncName', args: '' || []},		//点击取消按钮后触发的动作
	 * 		atext		: '确定',						//确定按钮的名字，默认为'确认'
	 * 		ctext		: '取消',						//取消按钮的名字，默认为'取消'
	 * 		acolor		: '#348fe2',					//确认按钮的颜色，仅支持#348fe2格式的颜色设置
	 * 		ccolor		: '#607D8B',					//取消按钮的颜色，仅支持#348fe2格式的颜色设置
	 * 		ahide		: 'false',						//是否隐藏确定按钮
	 * 		chide		: 'false',						//是否隐藏取消按钮
	 * 		adisabled	: 'false',						//是否禁用确定按钮
	 * 		cdisabled	: 'false',						//是否禁用取消按钮
	 * 		icon		: 'ion-information-circled'		//弹窗标题图标
	 * 		size		: 'normal',						//弹窗尺寸，默认正常大小
	 * 		callback	: 'someFuncName' || {func: 'someFuncName', args: '' || []},		//回调函数，将在弹窗完全打开后执行，默认为空
	 * 		newpop		: 'inset' || 'second' || 'new',	//new则新窗口等待当前窗口关闭后打开，second为第二窗口，将隐藏当前窗口后打开，inset为内嵌到当前窗口
	 * 		nocache		: 'false'						//设置为true表示该弹窗表单数据不进行历史记录保存，亦不会发生历史数据回填
	 *  }
	 * 
	 * accept、cancel、callback属性参数说明：
	 * 三者参数格式相同，以accept设置为例：
	 * 
	 * 1. 如果参数为函数名则可传入不带参数的函数
	 * accept: myFunction
	 * 
	 * 2. 如果参数为字符串则可传入携带字符串参数函数或不携带参数的函数
	 * accept: 'myFunction('+myArguments+')'
	 * 
	 * 3. 参数可以为json对象
	 * accept: {
	 *     func: myFunction,		//函数名
	 *     singleArgs: myArguments	//参数，用于向func传入单个参数，不设置该属性或设置为空，则传入不带参数的函数
	 * }
	 * 3.1  其参数部分可以设置为数组对象以传入多个参数
	 * accept: {
	 *     func: myFunction,			//函数名
	 *     arrayArgs: [arg1, arg2,...]	//参数，用于向func传入多个参数，不设置该属性或设置为[]，则传入不带参数的函数
	 * }
	 * 3.2 同一设置项的 singleArgs 和 arrayArgs 属性不可同时设置
	 * 
	 * 
	 * 事件
	 * 内置四个自定义事件：
	 * 1、popopened.pop
	 * 2、innerpopopened.pop
	 * 3、popclosed.pop
	 * 4、innerpopclosed.pop
	 * 
	 */
	var popTimer;
    $.fn.cgetPopup = function(options){
    	
    	"use strict";
    	
    	var t = this, 
    	popClass = "modal-small modal-large modal-mini modal-super modal-full-width modal-projDetail";
    	clearTimeout(popTimer);
    	
    	//循环防止页面已加载pop或者尚未移除pop
    	if($(".modal-backdrop").length > 0){
    		if(isSameJson(options, currOptions)){
    			console.warn("cgetPopup -> 该方法被重复引用，请检查相关动作或方法引用是否合理。");
    			console.warn("cgetPopup -> 本次引用中的参数设置：");
    			console.info(currOptions);
    			console.warn("cgetPopup -> 重复引用中的参数设置：");
    			console.info(options);
        		return false;
    		}else{
    			//如果新传入的参数和当前窗体参数不同，则表明为新弹窗
    	        if(typeof(options) === 'string'){
    	        	console.error('cgetPopup() -> 参数格式不正确，请查看函数说明或Express前端开发手册相关条目');
    	        	return false;
    	        }

	        	var s_title = options.title || '提示',
	        	s_content = options.content === '' || options.content === undefined ? '' : options.content,
	        	s_accept = options.accept || '',
	        	s_cancel = options.cancel || '',
	        	s_atext = options.atext || '确定',
	        	s_ctext = options.ctext || '取消',
	        	s_acolor = options.acolor || '#59727D',
	        	s_ccolor = options.ccolor || '#92A5AF',
	        	s_ahide = !options.ahide ? false : true,
	        	s_chide = !options.chide ? false : true,
	        	s_adisabled = !options.adisabled ? false : true,
	        	s_cdisabled = !options.cdisabled ? false : true,
	        	s_icon = options.icon || 'ion-information-circled',
	        	s_size = options.size || 'normal',
	        	s_callback = options.callback || '',
	    	    s_newpop = options.newpop || 'second',
	    	    s_nocache = !options.nocache ? false : true,
	        	s_acceptArgs = [], 
	        	s_cancelArgs = [],
	        	s_callbackArgs = [];

	            if(s_content === ""){
	                console.error('cgetPopup() -> 未设置 content 属性值, 该属性是必须的');
	                return false;
	            }
	            
	            if(s_accept === "" && !s_ahide){
	                console.error('cgetPopup() -> 未设置 accept 属性值, 用于点击确定按钮后触发的动作, 该属性是必须的');
	                return false;
	            }
	            //获取确定按钮参数设置
	            if(typeof s_accept === 'object'){
	            	if(!s_accept.func){
	                    console.error('cgetPopup() -> 未正确设置 accept 属性值, 用于点击确定按钮后触发的动作, 该属性是必须的');
	                    return false;
	            	}
	            	s_acceptArgs = s_accept.singleArgs ? [s_accept.singleArgs] : s_accept.arrayArgs || [];
	                s_accept = s_accept.func;
	            }
	            //获取取消按钮参数设置
	            if(s_cancel === ""){
	            	s_cancel = innerClose;
	            }else{
	            	if(typeof s_cancel === 'object'){
	            		s_cancelArgs = s_cancel.singleArgs ? [s_cancel.singleArgs] : s_cancel.arrayArgs || [];
	            		s_cancel = s_cancel.func || popClose;
	            	}
	            }
	            //获取回调函数参数设置
	        	if(typeof s_callback === 'object'){
	        		s_callbackArgs = s_callback.singleArgs ? [s_callback.singleArgs] : s_callback.arrayArgs || [];
	        		s_callback = s_callback.func || "";
	        	}
	            
	            var s_ahidden = s_ahide ? " hidden" : "",
	            s_chidden = s_chide ? " hidden" : "",
	            s_adisabled = s_adisabled ? " disabled" : "",
	            s_cdisabled = s_cdisabled ? " disabled" : "",
	            s_nocache = s_nocache ? " s_nocache" : "";
	            
    			if(s_newpop === "inset"){
    				//如果newpop设置为inset则直接嵌入本窗口
    				if($("#innercBox").length > 0){
    					console.warn('cgetPopup() -> 本方法最多允许一个内嵌弹窗');
        	        	return false;
    				}
    	        	
    				var innercBox = '<div class="innerc-box' + s_nocache + '" id="innercBox"><header><h4 class="innerc-box-title"><i class="fa ion-information-circled"></i></h4><button type="button" class="innerc-box-close"><span>×</span></button></header><div class="inner-con-box" id="innerConBox"></div><footer></footer></div>';
    				$("#modal_body_box").prepend(innercBox);
    				
    				var ib = $("#innercBox");
    				ib.find(".innerc-box-title").append(s_title).find("i").addClass(s_icon);
    				
    				if(s_content.indexOf("#") < 0){
    					$("#innerConBox").html('<div class="pophtml">' + s_content + '</div>');
    	                $("body").trigger("innerpopopened.pop");
    		        }else if(s_content.indexOf("#") === 0){
    		        	ib.loadMask();
    		            var conArray = s_content.split("?");
    		            $("#innerConBox").cgetContent(conArray[0].replace("#",""), conArray[1], function(){
        	                $("body").trigger("innerpopopened.pop");
    		            	crunFunc(s_callback, s_callbackArgs);
    		            	setTimeout(function(){
    		            		ib.hideMask();
    		            	},300);
    		            });
    		        }
    				
    				var s_buttons = '<button type="button" class="btn btn-sm btn-default inner-close' + s_chidden + s_cdisabled + '" style="background:' + s_ccolor + ';border-color:' + s_ccolor + ';">' + s_ctext + '</button><button type="button" class="btn btn-sm btn-primary inner-confirm' + s_ahidden + s_adisabled + '" style="background:' + s_acolor + ';border-color:' + s_acolor + ';">' + s_atext + '</button>';
    				ib.find("footer").append(s_buttons);
    				
    			}else if(s_newpop === "second"){
    				//如果newpop设置为second则隐藏当前窗口后加载新窗口
    	            $("#modal-content").after('<div class="modal-dialog second-modal-content" id="second-modal-content"></div>');
    	            var smc = $("#second-modal-content");
    	            //定义弹窗窗体结构
    	            smc.removeClass(popClass);
    	            if(s_size === 'mini'){
    	            	smc.addClass("modal-mini");
    	            }else if(s_size === 'small'){
    	            	smc.addClass("modal-small");
    	            }else if(s_size === 'large'){
    	            	smc.addClass("modal-large");
    	            }else if(s_size === 'super'){
    	            	smc.addClass("modal-super");
    	            }else if(s_size === 'full'){
    	            	smc.addClass("modal-full-width");
    	            }else if(s_size === 'detail'){
    	            	smc.addClass("modal-projDetail");
    	            }
    	            
    	            $("#modal-content").addClass("overed");
    	            $(".popupcontent").append('<div class="inner-mask"></div>');
    	            $(".popupcontent .inner-mask").fadeIn(200);
    	            setTimeout(function(){
    	            	smc.addClass("second-in");
    	            },50);
    	            
    	            var s_popBody = '<div class="modal-content' + s_nocache + '" id="content"><div class="modal-header"><button type="button" class="close inner-box-close"><span>&times;</span></button><h4 class="modal-title" id="myModalLabel"><i class="fa"></i> </h4></div><div class="modal-body" id="second_modal_body_box"></div><div class="modal-footer"><span class="loading pull-left"><i class="fa fa-1x ion-load-c"></i> 加载中...</span><button type="button" class="btn btn-default inner-close' + s_chidden + s_cdisabled + '" style="background:' + s_ccolor + ';border-color:' + s_ccolor + ';">' + s_ctext + '</button><button type="button" class="btn btn-primary inner-confirm' + s_ahidden + s_adisabled + '" style="background:' + s_acolor + ';border-color:' + s_acolor + ';">' + s_atext + '</button></div></div>';
	                smc.html(s_popBody);
    	            
    	            var smbb = $("#second_modal_body_box");
    	            
    	            //设置标题
    	            smc.find("h4.modal-title").append(s_title).find("i").addClass(s_icon);
    	            

    	            //判断内容是静态还是路径
    	            if(s_content.indexOf("#") < 0){
    	            	smbb.html('<div class="pophtml">' + s_content + '</div>');
    	                $("body").trigger("innerpopopened.pop");
    	            }else if(s_content.indexOf("#") === 0){
    	                //如果是路径则发起内容加载请求
    	                //拆分路径中的传值
    	                var s_conArray = s_content.split("?");
    	                //加载弹窗内容
    	                smbb.cgetContent(s_conArray[0].replace("#",""),s_conArray[1], function(){
    	                	smbb.hideMask();
    	                	//smbb.find("table").css({"opacity":"1"});
        	                $("body").trigger("innerpopopened.pop");
    		            	crunFunc(s_callback, s_callbackArgs);
    		            });
    	            }
    	            
    			}else{
    				//newpop设置为new则等待当前窗口关闭后再打开
	        		popTimer = setTimeout(function(){
	        			//console.info(options);
	        			t.cgetPopup(options);
	        		},60);
			        
	        		return false;
    			}

		        //去除取消和确定按钮的事件监听
		        $(".inner-close, .inner-confirm, .innerc-box-close").off("click");
		        //重新绑定事件和方法
		        $(".inner-box-close").on("click", innerClose);
		        $(".inner-close").on("click", function(){
		            //绑定的执行方法
		        	if($(this).is(".disabled")) return false;
		        	if(!s_chide || $(".inner-close").is(":visible")) {
		        		if(crunFunc(s_cancel, s_cancelArgs) !== false && s_cancel !== innerClose && s_cancel !== "innerClose") innerClose();
		        	}
		        }); 
		        $(".inner-confirm").on("click", function(){
		        	if($(this).is(".disabled")) return false;
		        	if(!s_ahide || $(".inner-confirm").is(":visible")) {
		        		if(crunFunc(s_accept, s_acceptArgs) !== false && s_accept !== innerClose && s_accept !== "innerClose") innerClose();
		        	}
		        });
		        
        		return false;
    		}
    	}
    	currOptions = options;
        if(typeof(options) === 'string'){
        	console.error('cgetPopup() -> 参数格式不正确，请查看函数说明或Express前端开发手册相关条目');
        	return false;
        }else{
        	//初始化方法自有参数列表
        	var title = options.title || '提示',
        	content = options.content === '' || options.content === undefined ? '' : options.content,
        	accept = options.accept || '',
        	cancel = options.cancel || '',
        	atext = options.atext || '确定',
        	ctext = options.ctext || '取消',
        	acolor = options.acolor || '#59727D',
        	ccolor = options.ccolor || '#92A5AF',
        	ahide = !options.ahide ? false : true,
        	chide = !options.chide ? false : true,
        	adisabled = !options.adisabled ? false : true,
        	cdisabled = !options.cdisabled ? false : true,
        	icon = options.icon || 'ion-information-circled',
        	size = options.size || 'normal',
        	callback = options.callback || '',
    	    nocache = !options.nocache ? false : true,
        	acceptArgs = [],
        	cancelArgs = [],
        	callbackArgs = [];
        }
        if(content === ""){
            console.error('cgetPopup() -> 未设置 content 属性值, 该属性是必须的');
            return false;
        }
        
        if(accept === "" && !ahide){
            console.error('cgetPopup() -> 未设置 accept 属性值, 用于点击确定按钮后触发的动作, 该属性是必须的, 除非隐藏确定按钮');
            return false;
        }
        //获取确定按钮参数设置
        if(typeof accept === 'object'){
        	if(!accept.func){
                console.error('cgetPopup() -> 未正确设置 accept 属性值, 用于点击确定按钮后触发的动作, 该属性的值是必须方法');
                return false;
        	}
        	//区分是单参数还是多参数或是无参
            acceptArgs = accept.singleArgs ? [accept.singleArgs] : accept.arrayArgs || [];
            accept = accept.func;
        }
        //获取取消按钮参数设置
        if(cancel === ""){
            cancel = popClose;
        }else{
        	if(typeof cancel === 'object'){
        		cancelArgs = cancel.singleArgs ? [cancel.singleArgs] : cancel.arrayArgs || [];
        		cancel = cancel.func || popClose;
        	}
        }
        //获取回调函数参数设置
    	if(typeof callback === 'object'){
    		callbackArgs = callback.singleArgs ? [callback.singleArgs] : callback.arrayArgs || [];
    		callback = callback.func || "";
    	}
        
        var ahidden = ahide ? " hidden" : "",
        chidden = chide ? " hidden" : "",
        adisabled = adisabled ? " disabled" : "",
        cdisabled = cdisabled ? " disabled" : "",
        nocache = nocache ? " nocache" : "";
        
        //定义弹窗窗体结构
        var modalcontent = $("#modal-content");
        modalcontent.removeClass(popClass);
        if(size === 'mini'){
        	modalcontent.addClass("modal-mini");
        }else if(size === 'small'){
        	modalcontent.addClass("modal-small");
        }else if(size === 'large'){
        	modalcontent.addClass("modal-large");
        }else if(size === 'super'){
        	modalcontent.addClass("modal-super");
        }else if(size === 'full'){
        	modalcontent.addClass("modal-full-width");
        }else if(size === 'detail'){
        	modalcontent.addClass("modal-projDetail");
        }
        
        var popBody = '<div class="modal-content popupcontent popLoading' + nocache + '" id="content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button><h4 class="modal-title" id="myModalLabel"><i class="fa"></i> </h4></div><div class="modal-body" id="modal_body_box"></div><div class="modal-footer"><span class="loading pull-left"><i class="fa fa-1x ion-load-c"></i> 加载中...</span><button type="button" class="btn btn-default pop-close' + chidden + cdisabled + '" style="background:' + ccolor + ';border-color:' + ccolor + ';" data-dismiss="modal">' + ctext + '</button><button type="button" class="btn btn-primary pop-confirm' + ahidden + adisabled + '" style="background:' + acolor + ';border-color:' + acolor + ';">' + atext + '</button></div></div>';
        modalcontent.html(popBody);
        //设置标题
        $("h4.modal-title").append(title).find("i").addClass(icon);
        //弹出
        $('#commonModal').modal({
            show: true
        }).on('hidden.bs.modal', function (e) {
        	$("#second-modal-content").remove();
            modalcontent.html("").removeClass("modal-small modal-large modal-mini modal-super overed modal-full-width modal-projDetail");
            modalcontent.css({"position":"absolute", "left":"0", "right":"0", "top": "0"});
            currOptions = null;
            $("body").trigger("popclosed.pop");
        });
        //判断内容是静态还是路径
        if(content.indexOf("#") < 0){
            $("#modal_body_box").html('<div class="pophtml">' + content + '</div>');
            $("body").trigger("popopened.pop");
        }else if(content.indexOf("#") === 0){
            //如果是路径则发起内容加载请求
            //拆分路径中的传值
            var conArray = content.split("?");
            //加载弹窗内容
            $("#modal_body_box").cgetContent(conArray[0].replace("#",""),conArray[1],function(){
            	$("body").trigger("popopened.pop");
            });
        }
        //展开内容区域动画
        popExpan(callback, callbackArgs);
        //绑定事件和方法
        docm.off("click", ".pop-close").on("click", ".pop-close", function(){
            //绑定的执行方法
        	if($(this).is(".disabled")) return false;
        	if(!chide || $(".pop-close").is(":visible")) {
        		//确保绑定的方法执行成功并关闭弹窗，绑定方法返回false可阻止弹窗关闭
        		if(crunFunc(cancel, cancelArgs) !== false && cancel !== popClose && cancel !== "popClose") popClose();
        	}
        }); 
        docm.off("click", ".pop-confirm").on("click", ".pop-confirm", function(){
        	if($(this).is(".disabled")) return false;
        	if(!ahide || $(".pop-confirm").is(":visible")) {
        		if(crunFunc(accept, acceptArgs) !== false && accept !== popClose && accept !== "popClose") popClose();
        	}
        });
    };

	//弹窗拖动方法
	$.fn.dragging = function(data){
		
		//"use strict";
		
		var t = $(this),
		xPage,
		yPage,
		X,
		Y,
		xRand = 0,
		yRand = 0,
		father = t.parent(),
		defaults = {
			move : 'both',
			randomPosition : false,
			hander:1
		},
		opt = $.extend({},defaults,data),
		movePosition = opt.move,
		random = opt.randomPosition,
		
		hander = opt.hander;
		
		if(hander == 1){
			hander = t; 
		}else{
			hander = t.find(opt.hander);
		}
		
			
		//---初始化
		father.css({});
		t.css({"position":"relative","margin-left":"auto","margin-right":"auto"});
		hander.css({"cursor":"move"});

		var faWidth = father.width(),
		faHeight = father.height(),
		thisWidth = t.width()+parseInt(t.css('padding-left'))+parseInt(t.css('padding-right')),
		thisHeight = t.height()+parseInt(t.css('padding-top'))+parseInt(t.css('padding-bottom')),
		
		mDown = false,
		positionX,
		positionY,
		moveX,
		moveY;
		
		if(random){
			$thisRandom();
		}
		function $thisRandom(){ //随机函数
			t.each(function(index){
				var randY = parseInt(Math.random()*(faHeight-thisHeight)),
				randX = parseInt(Math.random()*(faWidth-thisWidth));
				if(movePosition.toLowerCase() == 'x'){
					$(this).css({
						left:randX
					});
				}else if(movePosition.toLowerCase() == 'y'){
					$(this).css({
						top:randY
					});
				}else if(movePosition.toLowerCase() == 'both'){
					$(this).css({
						top:randY,
						left:randX
					});
				}
				
			});	
		}
		
		hander.mousedown(function(e){
			father.children().css({"zIndex":"0"});
			t.css({"zIndex":"1"});
			mDown = true;
			X = e.pageX;
			Y = e.pageY;
			positionX = t.position().left;
			positionY = t.position().top;
			return false;
		});
			
		docm.mouseup(function(e){
			mDown = false;
		});
			
		docm.mousemove(function(e){
			xPage = e.pageX;
			moveX = positionX+xPage-X;
			
			yPage = e.pageY;
			moveY = positionY+yPage-Y;
			
			function thisXMove(){ //x轴移动
				if(mDown == true){
					t.css({"left":moveX});
				}else{
					return;
				}
				if(moveX < 0){
					//t.css({"left":"0"});
				}
				if(moveX > (faWidth-thisWidth)){
					t.css({"left":(faWidth-thisWidth)});
				}
				return moveX;
			}
			
			function thisYMove(){ //y轴移动
				if(mDown == true){
					t.css({"top":moveY});
				}else{
					return;
				}
				if(moveY < 0){
					t.css({"top":"0"});
				}
				if(moveY > (faHeight-thisHeight)){
					t.css({"top":faHeight-thisHeight});
				}
				return moveY;
			}

			function thisAllMove(){ //全部移动
				if(mDown == true){
					t.css({"left":moveX,"top":moveY});
				}else{
					return;
				}
				if(moveX < 0){
					//t.css({"left":"0"});
				}
				if(moveX > (faWidth-thisWidth)){
					t.css({"left":(faWidth-thisWidth)});
				}

				if(moveY < 0){
					t.css({"top":"0"});
				}
				if(moveY > (faHeight-thisHeight)){
					t.css({"top":faHeight-thisHeight});
				}
			}
			if(movePosition.toLowerCase() == "x"){
				thisXMove();
			}else if(movePosition.toLowerCase() == "y"){
				thisYMove();
			}else if(movePosition.toLowerCase() == 'both'){
				thisAllMove();
			}
		});
    };

	/*
	 * 移动屏主导航放置到页面底部 
	 */
    $.fn.menuAdjust = function(){
    	
    	"use strict";
    	
        //尺寸小于1025px时视为移动视图，ipad屏幕宽度为1024
        if($wds.width() <= 1025 || _isMobile){
            //复制主导航内容
            var Anav = this.clone(),
            sidebar = $("#sidebar");
            //追加样式、移除不必要的元素
            Anav.addClass("bottom-mainmenu").find(".menu-control ").remove();
            //如果尚未放入侧边栏则放入
            if(sidebar.prev(".bottom-mainmenu").length < 1){
            	sidebar.before(Anav);
                //激活当前页面对应的按钮
                var shash = location.hash ? location.hash : "";
                if(!!issmactive && issmactive(shash)){
                    $('.bottom-mainmenu li').removeClass("active");
                    $('.bottom-mainmenu li a[data-mid="'+ issmactive(shash) +'"]').parent().addClass("active");
                }
            }
        }else{
            //如果页面尺寸非移动视图则移除侧边栏的导航元素
            $("#sidebar").prev(".bottom-mainmenu").remove();
        }
    };
    
    /*
     * 表格选定行数据删除 - 脚本开始
     * 旧方法
     * 引用方式：表格jQuery对象.deleteRow();
     * @param {数字} col 获取参数的列基于0的索引
     * @param {字符串} url 请求的页面路径
     * @param {datatables对象} dtc 表格的datatable对象
     * @param {方法} 删除完成的回调
     * @returns 无
     */
    $.fn.deleteRow = function(col, url, dtc, callback){
    	
    	"use strict";
    	
        //请求路径为必填参数
        if(url === "" || url === undefined){
            console.error('deleteRow() -> 未设置请求路径 url!');
            return false;
        }
        //默认为第一列
        col = col === "" ? 0 : col;
        var t = this;
        dtc = !dtc ? t.DataTable() : dtc;
        //获取选中行的对象
        var s = t.find("tr.selected");
        var m = s.length > 0 ? "id=" : "";
        //如果没有选中行弹出提示
        if (m === "") {
        	if(_inApk){
        		navigator.notification.alert('请选择要删除的数据！', null, '提示信息', '确定');
        	}else{
	            $("body").cgetPopup({
	        		title: '提示信息',
	        		content: '请选择要删除的数据！',
	        		accept: popClose,
	        		icon: 'fa-exclamation-circle',
	        		size: 'small'
	        	});
            }
            return false;
        }
        //获取值
        for( i=0; i<s.length; i++ ){
            //获取当前选中行在表格中的索引
            var rindx = s.eq(i).index();
            //通过dtc获取指定行和列的单元格的值
            m += dtc.column(col).data()[rindx];
            //m = m + s.eq(i).find("td:eq("+ col +")").text();
            m += (s.length - 1 === i ? "" : ",");
        }
        $.ajax({
            type: 'POST',
            url: url,
            data: m,
            dataType: 'html',
            success: function (data) {
                if (data === "true") {
                    //刷新表格
                    dtc.ajax.reload(function (json) {
                        //第一行默认选中
                    	t.selectRow(0);
	                    callback && callback(json);
                    });
                	if(_inApk){
                		navigator.notification.alert('数据删除成功！', null, '提示信息', '确定');
                	}else{
	                    $("body").cgetPopup({
	                    	title: '提示信息',
	                    	content: '数据删除成功！',
	                    	accept: popClose,
	                    	newpop: 'new',
	                    	icon: 'fa-check-circle'
	                    });
                    }
                }else{
                	if(_inApk){
                		navigator.notification.alert('数据删除失败, 请重试！', null, '提示信息', '确定');
                	}else{
	                	$("body").cgetPopup({
	                       	title: '提示信息',
	                        content: '数据删除失败, 请重试!',
	                        accept: popClose,
	                        icon: 'fa-exclamation-circle'
	                    });
                	}
                }
                $('html, body').animate({
                    scrollTop: $("body").offset().top
                }, 250);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                printXHRError("deleteRow", "表格(" + t.attr("id") + ") ajax删除查询失败", jqXHR, textStatus, errorThrown);
            }
        });
    };

    /*
     * deleteRow的替代方法
     * 引用方式：表格jQuery对象.cdeleteRow();
     * @param {字符串} field 查询字段名
     * @param {字符串} url 请求的页面路径
     * @param {方法} callback 删除完成的回调
     * @returns 无
     */
    $.fn.cdeleteRow = function(field, url, callback){
    	
    	"use strict";
    	
        //请求路径为必填参数
        if(!field){
            console.error('cdeleteRow() -> 必须设置查询字段!');
            return false;
        }
        if(!url){
            console.error('cdeleteRow() -> 必须设置请求路径 url!');
            return false;
        }
        var t = this,
        dtc = t.DataTable(),
        id = t.attr("id"),
        rows = t.find("tbody tr.selected").length > 0 && trSData[id].jsons ? trSData[id].jsons : [],
        param = "";
        //如果没有选中行弹出提示
        if(!rows.length){
        	if(_inApk){
        		navigator.notification.alert('请先选择要删除的行！', null, '未选中任何行', '确定');
        	}else{
	            $("body").cgetPopup({
	        		title: '未选中任何行',
	        		content: '请先选择要删除的行！',
	        		accept: popClose,
	        		icon: 'fa-exclamation-circle',
	        		size: 'small'
	        	});
            }
            return false;
        }
        //获取查询参数值
        param = field + "=";
        $.each(rows, function(k, v){
        	param += v[field];
        	param += rows.length - 1 === k ? "" : ",";
        });
        $("#projectCheckListTable").parent().parent().loadMask("正在删除。。。",1,0.5);  //报验列表删除时加载提示
        $.ajax({
            type: 'POST',
            url: url,
            data: param,
            dataType: 'html',
            success: function (data) {
                if (data === "true") {
                    //刷新表格
                    dtc.ajax.reload(function (json) {
                    	$("#projectCheckListTable").parent().parent().hideMask();  ////报验列表删除时隐藏提示
                        //第一行默认选中
                    	t.selectRow(0);
	                    callback && callback(json);
                    });
                	if(_inApk){
                		navigator.notification.alert('数据删除成功！', null, '提示信息', '确定');
                	}else{
	                    $("body").cgetPopup({
	                    	title: '提示信息',
	                    	content: '数据删除成功！',
	                    	accept: popClose,
	                    	icon: 'fa-check-circle'
	                    });
                    }
                }else{
                	if(_inApk){
                		navigator.notification.alert('数据删除失败, 请重试！', null, '提示信息', '确定');
                	}else{
	                	$("body").cgetPopup({
	                       	title: '提示信息',
	                        content: '数据删除失败, 请重试!',
	                        accept: popClose,
	                        icon: 'fa-exclamation-circle'
	                    });
                	}
                }
                $('html, body').animate({
                    scrollTop: $("body").offset().top
                }, 250);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                printXHRError("cdeleteRow", "表格(" + t.attr("id") + ") ajax删除查询失败", jqXHR, textStatus, errorThrown);
            }
        });
    };
    
    
    //行选中中间方法    
    $.fn.selectRow = function(indx){
    	
    	"use strict";
    	
    	var t = this, i = parseInt(indx);
    	if(t.length < 1) return true;
    	if(!t.find('tbody tr:eq(0) td').hasClass("dataTables_empty")){
        	selectTr[t.attr("id")] = i;
            t.DataTable().row(':eq(' + i + ')', { page: 'current' }).select();
    	}
    };
    
    /*
	 * 表单数据上传保存 - 脚本开始
	 * 引用方式：表单jQuery对象.formSave();
     * @param {字符串} url 请求的页面路径
     * @param {字符串} tid 要展示数据的表格的id
     * @param {datatables对象} dtc
     * @param {回调函数} callback 执行后的操作方法
     * @param {boolean} flag true/false 表单保存成功后，是否保留当前选中行，且页面保留在当下
     * @returns 无
     */
    $.fn.formSave = function (url, tid, dtc, callback, flag, pop) {
    	
    	"use strict";
    	
        //请求路径为必填参数
        if (!url) {
            console.error('formSave() -> 未设置请求路径 url!');
            return false;
        }
        var t = this;
        
        //验证必签签字是否已签
        var signtools = t.find(".signature-tool.sign-require"),
        stl = signtools.length,
        sBlank = 0;
        for(var i=0; i<stl; i++){
        	var tstool = signtools.eq(i).next("div").find("a.btn-white"),
        	tsinput = tstool.siblings(".sign-data-input");
        	if(!tsinput.val() || tsinput.val().length < 312){
        		tstool.addClass("required-sign");
        		sBlank++;
        	}
        }
        if(sBlank){
        	if(_inApk){
        		navigator.notification.alert('请完成签字！', null, '提示信息', '确定');
        	}else{
	        	$("body").cgetPopup({
	            	title: "提示信息",
	            	content: "请完成签字!",
	            	accept: popClose,
	            	chide: true,
	            	icon: "fa-warning",
	            	newpop: 'new'
	            });
        	}
        	return false;
        }
        
        //开启表单验证
        if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
            //防止多次提交
            if(response){
                response.abort();
            }
            //表单序列化获取json字符串
            var data = t.serializeJsonString();
            var response = $.ajax({
                url: url,
                type: "POST",
                timeout : 5000,
                contentType: "application/json;charset=UTF-8",
                data: data,
                success: function (data) {
                    if (data === "true") {
                        if (dtc !== "" && dtc !== undefined && (rowLoop[tid] === '' || rowLoop[tid] === undefined)) {
                            dtc.ajax.reload(function(){
                                if(flag !== false){
                                	if(flag === true || flag === "") flag = 0;
                                	if(tid) $('#' + tid).selectRow(flag);
                                }else{
                                	//逻辑待补充，选中保存前列表选中的行
                                	
                                }
                            });
                        }
                        if(pop === '' || pop || pop === undefined){
                        	if(_inApk){
                        		navigator.notification.alert('数据保存成功！', null, '提示信息', '确定');
                        	}else{
		                        $("body").cgetPopup({
		                        	title: "提示信息",
		                        	content: "数据保存成功!",
		                        	accept: popClose,
		                        	chide: true,
		                        	icon: "fa-check-circle",
		                        	newpop: 'new'
		                        });
	                        }
                        }
                    } else {
                        if(pop === '' || pop || pop === undefined){
                        	if(_inApk){
                        		navigator.notification.alert('数据保存失败, 请重试！\n' + data, null, '提示信息', '确定');
                        	}else{
		                        $("body").cgetPopup({
		                        	title: "提示信息",
		                        	content: "数据保存失败, 请重试! <br>" + data,
		                        	accept: popClose,
		                        	chide: true,
		                        	icon: "fa-exclamation-circle",
		                        	newpop: 'new'
		                        });
                        	}
                        }
                    }
                    if(callback !== "" && callback !== undefined){
                        callback(data);
                    }
                },
               error: function (jqXHR, textStatus, errorThrown) {
                    if(textStatus === 'timeout'){
                        response.abort();
                        callback(textStatus);
                    }
                    printXHRError("formSave", "表单(" + t.attr("id") + ") ajax保存查询失败", jqXHR, textStatus, errorThrown);
                }
            });
            //如果通过验证, 则移除验证UI
            t.parsley().validate();
        } else {
            //如果未通过验证, 则加载验证UI
            t.parsley().validate();
        };
    };

    /*
     * ========================
     * formSave的替代方法
     * 引用方法和之前一致
     * 改进：无需再传入url、dtc
     * url将从form的action属性中获取
     * dtc将由表格id自动获取
     * ========================
     * @param {字符串} tid 要展示数据的表格的id
     * @param {回调函数} callback 执行后的操作方法
     * @param {boolean/int} flag true/false/int 表单保存成功后，是否默认选中设置行
     * @param {boolean} pop true/false 表单保存成功后，是否弹窗提示保存结果，不传默认弹出
     * @param {boolean} holdPage true/false 表单保存后，列表是否加载当前页，true当前页，false，重新从第一页加载
     * @param {object} curBtn 当前点击的按钮
     * @returns 无
     */
    $.fn.cformSave = function (tid, callback, flag, pop,holdPage,curBtn) {
    	
    	"use strict";
    	
        var t = this, url = t.attr("action");
        
        //请求路径为必填参数
        if (!url) {
            console.error('formSave() -> 表单未设置action属性');
            return false;
        }
        
        //验证必签签字是否已签
        var signtools = t.find(".signature-tool.sign-require"),
        stl = signtools.length,
        sBlank = 0;
        for(var i=0; i<stl; i++){
        	var tstool = signtools.eq(i).next("div").find("a.btn-white"),
        	tsinput = tstool.siblings(".sign-data-input");
        	if(!tsinput.val() || tsinput.val().length < 312){
        		tstool.addClass("required-sign");
        		sBlank++;
        	}
        }
        if(sBlank){
        	if(_inApk){
        		navigator.notification.alert('请完成签字！', null, '提示信息', '确定');
        	}else{
	        	$("body").cgetPopup({
	            	title: "提示信息",
	            	content: "请完成签字!",
	            	accept: popClose,
	            	chide: true,
	            	icon: "fa-warning",
	            	newpop: 'new'
	            });
        	}
        	return false;
        }
        
        //开启表单验证
        if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
            //防止多次提交
            if(response){
                response.abort();
            }
            t.parent().parent().loadMask("正在保存...", 1, 0.5);
            
            //表单序列化获取json字符串
            var data = t.serializeJsonString();
            var response = $.ajax({
                url: url,
                type: "POST",
               // timeout : 15000,
                contentType: "application/json;charset=UTF-8",
                data: data,
                beforeSend: function () {
  	              // 禁用按钮防止重复提交
                  if(curBtn!='' && curBtn!=undefined &&  curBtn!=null){
                	  curBtn.attr({ disabled: "disabled" });
                  }
  	            },
                success: function (data) {
                	t.parent().parent().hideMask();
                    if (data !== "false") {
                        var table = $('#' + tid);
                        if(table.length > 0){
                        	var dtc = table.DataTable();
                            if (rowLoop[tid] === '' || rowLoop[tid] === undefined) {
                            	 if(holdPage){//加载当前页
                                 	 console.info("save：holdPage==="+holdPage);
                                     dtc.draw(!holdPage);
                                     if(flag !== false){
                                  		if(flag === true || flag === "") flag = 0;
                                      	table.selectRow(flag);
                                      }
                                 }else{
                                	 dtc.ajax.reload(function () {
                                         if(flag !== false){
                                     		if(flag === true || flag === "") flag = 0;
                                         	table.selectRow(flag);
                                         }
                                     });
                                 }
                            }
                        }
                        if(data == "exist"){
                        	 $("body").cgetPopup({
		                        	title: "提示信息",
		                        	content: "该记录已被审批,请刷新页面!",
		                        	accept: popClose,
		                        	chide: true,
		                        	icon: "fa-check-circle",
		                        	newpop: 'new'
		                        });
                        }else if(pop === '' || pop || pop === undefined){
                        	if(_inApk){
                        		navigator.notification.alert('数据保存成功！', null, '提示信息', '确定');
                        	}else{
		                        $("body").cgetPopup({
		                        	title: "提示信息",
		                        	content: "数据保存成功!",
		                        	accept: popClose,
		                        	chide: true,
		                        	icon: "fa-check-circle",
		                        	newpop: 'new'
		                        });
                        	}
                        }
                    } else {
                        if(pop === '' || pop || pop === undefined){
                        	t.parent().parent().hideMask();
                        	if(_inApk){
                        		navigator.notification.alert('数据保存失败, 请重试！\n' + data, null, '提示信息', '确定');
                        	}else{
		                        $("body").cgetPopup({
		                        	title: "提示信息",
		                        	content: "数据保存失败, 请重试! <br>" + data,
		                        	accept: popClose,
		                        	chide: true,
		                        	icon: "fa-exclamation-circle",
			                        newpop: 'new'
		                        });
                        	}
                        }
                    }
                    if(callback !== "" && callback !== undefined){
                        callback(data);
                    }
                },
                complete: function () {
                	//去掉禁用
                	if(curBtn!='' && curBtn!=undefined &&  curBtn!=null){
                		curBtn.removeAttr("disabled");
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    //判断超时
                    if(textStatus === 'timeout'){
                        response.abort();
                        callback(textStatus);
                    }
                    printXHRError("cformSave", "表单(" + t.attr("id") + ") ajax保存查询失败", jqXHR, textStatus, errorThrown);
                }
            });
            //如果通过验证, 则移除验证UI
            t.parsley().validate();
        } else {
            //如果未通过验证, 则加载验证UI
            t.parsley().validate();
        };
        return t;
    };

    /*
     * ========================
     * 通用表单保存方法
     * 引用方法和cformSave方法一致
     * ========================
     * @param {json字符串} data 传递给后台的数据, 如果不设置的将表单序列化json字符串数据传递到后台
     * @param {字符串} tid 要展示数据的表格的id
     * @param {回调函数} callback 执行后的操作方法
     * @param {boolean/int} flag true/false/int 表单保存成功后，是否默认选中设置行
     * @param {boolean} pop new/second/inset/false 表单保存成功后，是否弹窗提示保存结果，false为不弹出, new以新弹窗方式弹出, second以二层弹窗方式弹出, inset以嵌套方式弹出, 默认为二层弹窗方式
     * @returns 无
     */
    $.fn.gformSave = function (data, tid, callback, flag, pop) {
    	
    	"use strict";
    	
        var t = this, url = t.attr("action");
        pop = pop === false ? pop : pop ? pop : 'second';
        //请求路径为必填参数
        if (!url) {
            console.error('formSave() -> 表单未设置action属性');
            return false;
        }
        
        //验证必签签字是否已签
        var signtools = t.find(".signature-tool.sign-require"),
        stl = signtools.length,
        sBlank = 0;
        for(var i=0; i<stl; i++){
        	var tstool = signtools.eq(i).next("div").find("a.btn-white"),
        	tsinput = tstool.siblings(".sign-data-input");
        	if(!tsinput.val() || tsinput.val().length < 312){
        		tstool.addClass("required-sign");
        		sBlank++;
        	}
        }
        if(sBlank){
        	if(_inApk){
        		navigator.notification.alert('请完成签字！', null, '提示信息', '确定');
        	}else{
	        	$("body").cgetPopup({
	            	title: "提示信息",
	            	content: "请完成签字!",
	            	accept: popClose,
	            	chide: true,
	            	icon: "fa-warning",
	            	newpop: 'new'
	            });
        	}
        	return false;
        }
        
        //开启表单验证
        if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
            //防止多次提交
            if(response){
                response.abort();
            }
            //如果设置了data参数则默认传递data, 反之传递表单序列化获取json字符串
            var data = data ? data : t.serializeJsonString();
            var response = $.ajax({
                url: url,
                type: "POST",
                timeout : 5000,
                contentType: "application/json;charset=UTF-8",
                data: data,
                success: function (data) {
                    if (data === "true") {
                        var table = $('#' + tid);
                        if(table.length > 0){
                        	var dtc = table.DataTable();
                            if (rowLoop[tid] === '' || rowLoop[tid] === undefined) {
                                dtc.ajax.reload(function () {
                                    if(flag !== false){
                                        if(flag === true || flag === "") flag = 0;
                                    	table.selectRow(flag);
                                    }
                                });
                            }
                        }
                        if(pop){
                        	if(_inApk){
                        		navigator.notification.alert('数据保存成功！', null, '提示信息', '确定');
                        	}else{
		                        $("body").cgetPopup({
		                        	title: "提示信息",
		                        	content: "数据保存成功!",
		                        	accept: pop === 'new' ? popClose : innerClose,
		                        	chide: true,
		                        	icon: "fa-check-circle",
		                        	newpop: pop
		                        });
                        	}
                        }
                    } else {
                        if(pop){
                        	if(_inApk){
                        		navigator.notification.alert('数据保存失败, 请重试！\n' + data, null, '提示信息', '确定');
                        	}else{
		                        $("body").cgetPopup({
		                        	title: "提示信息",
		                        	content: "数据保存失败, 请重试! <br>" + data,
		                        	accept: pop === 'new' ? popClose : innerClose,
		                        	ahide: true,
		                        	icon: "fa-exclamation-circle",
			                        newpop: pop
		                        });
                        	}
                        }
                    }
                    callback && callback(data);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    //判断超时
                    if(textStatus === 'timeout'){
                        response.abort();
                        callback && callback(textStatus);
                    }
                    printXHRError("gformSave", "表单(" + t.attr("id") + ") ajax保存查询失败", jqXHR, textStatus, errorThrown);
                }
            });
            //如果通过验证, 则移除验证UI
            t.parsley().validate();
        } else {
            //如果未通过验证, 则加载验证UI
            t.parsley().validate();
        };
        return t;
    };

    /*
	 * 表格数据查询返回 - 脚本开始
	 * 引用方式: 表格jQuery对象.cgetData();
     * @param {整数} selectrow 查询后用于自动选定指定行，传入空或true默认选中第一行，传入数字n表示选中表格第一页第n+1行，传入false则不选中任何行
     * @param {方法} callback 回调函数
     * @returns 无
     */
    $.fn.cgetData = function(selectrow, callback, holdPage){
    	
    	"use strict";
    	
    	var t = this, dtc = t.DataTable(), id = t.attr("id"),
        doCallback = function(){
        	if(selectrow === false){
            	callback && callback();
        		return false;
        	}
        	if(selectrow === true || selectrow === "" || selectrow === undefined) selectrow = 0;
        	t.selectRow(selectrow);
        	callback && callback();
        };
        
        if(holdPage === undefined) holdPage = false;
    	console.info("selectrow===="+selectrow);
        //表格重新加载, 同时会刷新查询条件, 将返回新的数据
        if(holdPage){//加载当前页
        	console.info("holdPage==="+holdPage);
            dtc.draw(!holdPage);
            if(selectrow === true || selectrow === "" || selectrow === undefined) selectrow = 0;
            t.selectRow(selectrow);
        }else{
            dtc.ajax.reload(doCallback, !holdPage);
        }
        return t;
    };

    /* 
     * 点击单行查看详情 - 脚本开始
     * 引用方式: 表格选中行的jQuery对象.getDetail();
     * @param {字符串} url 请求的页面路径
     * @param {字符串} fid 用于展示详情的表单id
     * @param {数字} col 查询参数获取的列索引
     * @returns 无
     */
    $.fn.getDetail = function(url, fid, col,callback){
    	
    	"use strict";
    	
        var f = $("#" + fid);
    	f.parents(".panel-inverse").find(".panel-heading").loading(1);
        //移除表单验证UI
		if(typeof f.parsley === 'function' && f.parsley()!=null && f.parsley()!=undefined){
			if (f.parsley().isValid() || !f.parsley().isValid()) {
				f.parsley().reset();
			}
		}
        //切换表单元素可编辑状态
        f.toggleEditState(false, "all");
        
        $("div.savediv").addClass("hidden");
        
        //如果第一行有选中样式且当前行不是第一行
        if (this.parent().find("tr.selected").length === 1 && this.parent().find("tr:eq(0)").hasClass("selected") && this.index() !== 0) {
            //移除第一行的选中样式
            this.parent("table.dataTable").clearSelected();
        }
        
        //默认从第一列获取参数值
        col = col === "" || col === undefined ? 0 : col;
        var m = this.length > 0 ? "id=" : "";
        
        //通过dtc获取指定行和列的单元格的值
        var dtc = this.parents("table").DataTable();
        var rindx = dtc.settings()[0].oFeatures.bServerSide ? this.index() : this.index() + (dtc.page() * dtc.page.len());
        m += dtc.column(col).data()[rindx];
        $.ajax({
            type: 'POST',
            url: url,
            data: m,
            dataType: 'json',
            success: function (data) {
            	data = fixNull(data);
                //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
                var selects = f.find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
                selects.removeAttr("disabled");
                //表单反序列化填充值
                f.deserialize(data);
                f.initFixedNumber();
                //有disabled属性的下拉菜单元素对象重新添加禁用属性
                selects.attr("disabled","disabled");
                //f.addTitle();
                f.fadeIn(200);
                $('html, body').animate({
                    scrollTop: $("body").offset().top
                }, 250);
                if(callback !== "" && callback !== undefined){
                    callback(data);
                }
                f.parents(".panel-inverse").find(".panel-heading").hideloading();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                printXHRError("getDetail", "详情查询失败", jqXHR, textStatus, errorThrown);
                f.parents(".panel-inverse").find(".panel-heading").hideloading();
            }
        });
    };
    
    /*
     * ========================
     * getDetail的替代方法
     * 引用方法和之前一致
     * 需配合bindDTSelect方法使用
     * 参数位置发生变动
     * url可不传，如需查库才需要传递，如果url不传递，col参数也不需要传递
     * 优化点：如不需查库则可直接通过该行的关联数据(trSData.json)进行表单反序列化
     * 否则将会把查库后获取的数据对象与trSData.json合并之后进行表单的反序列化，并在回调中返回查库获得的数据对象。
     * ========================
     * @param {字符串} fid 用于展示详情的表单id
     * @param {字符串} url 请求的页面路径
     * @param {数字} col 查询参数获取的列索引
     * @returns 无
     */
    $.fn.cgetDetail = function(fid, url, col, callback){
    	
    	"use strict";
        var t = this, f = $("#" + fid);
        f.parents(".panel-inverse").find(".panel-heading").loading(1);
        //移除表单验证UI
		if(typeof f.parsley === 'function' && f.parsley()!=null && f.parsley()!=undefined){
			if (f.parsley().isValid() || !f.parsley().isValid()) {
				f.parsley().reset();
			}
		}
        //切换表单元素可编辑状态
        f.toggleEditState(false, "all");
        $("div.savediv").addClass("hidden");
        
        if(!url){
            //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
            var selects = f.find('select[disabled], .sign-data-input[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
            selects.removeAttr("disabled");
            //表单反序列化填充值
            f.deserialize(trSData.json);
            f.initFixedNumber();
            //有disabled属性的下拉菜单元素对象重新添加禁用属性
            selects.attr("disabled","disabled");
            //f.addTitle();
            f.fadeIn(200);
            $('html, body').animate({
                scrollTop: $("body").offset().top
            }, 250);
            callback && callback(trSData.json);
            f.parents(".panel-inverse").find(".panel-heading").hideloading();
        }else{
            //默认从第一列获取参数值
            col = col === "" || col === undefined ? 0 : col;
            var m = t.length > 0 ? "id=" : "",
            tid = t.closest("table").attr("id");
            if(tid) {
            	m += trSData[tid].v[col];
            }else{
            	m += trSData.v[col];
            }
            $.ajax({
                type: 'POST',
                url: url,
                data: m,
                dataType: 'json',
                success: function (data) {
                	data = fixNull(data);
                    //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
                    var selects = f.find('select[disabled], .sign-data-input[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
                    selects.removeAttr("disabled");
                    //表单反序列化填充值
                    var total = jQuery.extend({}, trSData.json, data);
                    f.deserialize(total);
                    f.initFixedNumber();
                    //有disabled属性的下拉菜单元素对象重新添加禁用属性
                    selects.attr("disabled","disabled");
                    //f.addTitle();
                    f.fadeIn(200);
                    $('html, body').animate({
                        scrollTop: $("body").offset().top
                    }, 250);
                    callback && callback(data);
                    f.parents(".panel-inverse").find(".panel-heading").hideloading();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    printXHRError("cgetDetail", "详情查询失败", jqXHR, textStatus, errorThrown);
                    f.parents(".panel-inverse").find(".panel-heading").hideloading();
                }
            });
        }
        return t;
    };
    
    /*
     * 直接请求后端获取表单信息
     * 用法：$("#formId").getFormDetail(url, param, callback);
     * 参数：
     * @param {字符串} param 请求的路径
     * @param {对象} param 请求的参数，json类型
     * @param {方法} callback 查询完成的回调
     * @returns data 查询到的数据
     * 注：表单需要设置action属性，即请求路径
     */
    
    $.fn.getFormDetail = function(url, param, callback){
    	
    	"use strict";
    	
        var f = this, 
        url = url ? url : f.attr("action"),
        param = !param ? {} : param;
        
        //请求路径为必填参数
        if (!url) {
            console.error('getFormDetail() -> 未设置请求url且表单未设置action属性');
            return false;
        }
    	
        $.ajax({
            type: 'POST',
            url: url,
            data: param,
            dataType: 'json',
            success: function (data){
            	data = fixNull(data);
                //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
                var selects = f.find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
                selects.removeAttr("disabled");
                //表单反序列化填充值
                f.deserialize(data);
                f.initFixedNumber();
                //有disabled属性的下拉菜单元素对象重新添加禁用属性
                selects.attr("disabled","disabled");
                //f.addTitle();
                f.fadeIn(200);
                callback && callback(data);
                f.parents(".panel-inverse").find(".panel-heading").hideloading();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                printXHRError("getFormDetail", "数据查询失败", jqXHR, textStatus, errorThrown);
                f.parents(".panel-inverse").find(".panel-heading").hideloading();
            }
        });
    	
    };

	/*
	 * 表单元素动态布局 - 脚本开始
	 * 本脚本用于动态处理元素标签和label的布局关系,不适用于处理表单元素间的布局关系
	 * 约定：表单元素标签和label本不使用栅格布局样式，而其父级元素应当使用
	 * 引用方式：表单jQuery对象.styleFit();
	 */
	var timer = {}, 
	markRequired = {},
	labelWidthCache = {},
	required_mark_item = ['data-parsley-required="true"','required','data-parsley-notblank'];
    $.fn.styleFit = function(){
    	
    	"use strict";
    	
    	if(!this.length){
    		return false;
    	}
    	var t = this.addClass("form-style-fit"),
    	id = t.attr("id");
    	clearTimeout(timer[id]);
    	
        var lw_max = 0,
        l = t.find(".form-group > label"),
        ll = l.length,
        chl = l.eq(0),
        formID;
        
        //如果表单元素尚未显示，则轮询等待
        for(var i=0; i<ll; i++){
        	if(l.eq(i).parents(".form-group").is(".hidden")) continue;
        	chl = l.eq(i);
        	break;
        }
        if(chl.parent(":visible").length < 1){
        	if(_isMobile){
        		timer[id] = setTimeout(function(){t.styleFit();},300);
        	}else{
        		timer[id] = setTimeout(function(){t.styleFit();},150);
        	}
        	return false;
        }
        
        //l.css("display","none");
        
        formID = t.attr("action") || location.hash;
        formID = formID.replace("#","").replace("\/","_") + "_" + id;
        
        if(labelWidthCache[formID]){
        	//console.info("1 -> " + labelWidthCache[formID]);
        	lw_max = labelWidthCache[formID];
        }else{
            //获取label最宽值
            for(var i=0; i<ll; i++){
            	var tl = l.eq(i);
            	if(tl.next("div").length < 1) continue;
            	if(tl.next("label").length > 0) continue;
                var lw = tl.getLabelWidth();
                lw_max = lw_max < lw ? lw : lw_max;
            }
            lw_max = lw_max < 12 ? 0 : lw_max;
            labelWidthCache[formID] = lw_max;
        	//console.info("2 -> " + labelWidthCache);
        }
        
        l.css("width", lw_max);
        
        for(var i=0; i<ll; i++){
        	var tl = l.eq(i);
        	if(tl.next("div").length<1) continue;
        	if(tl.next("label").length>0) continue;
            var b = tl.siblings("div");
        	//b.css("width",pw - lw_max - 10);
//            if(b.find(".diviSpan").length > 0){
//            	b.addClass("input-group-tool");
//            	var bchild = b.children().not(".diviSpan, .required-mark");
//            	var divispanw = b.find(".diviSpan").width();
//            	bchild.css({"width":(b.innerWidth() - divispanw)/2 - 4,"display":"inline-block"});
//            	bchild.eq(0).css({"width":(b.innerWidth() - divispanw)/2 - 5});
//            }
            var slt = b.find("select").eq(0);
            if(slt.hasClass("default-select2")){
            	slt.cinitSelect2();
            }
        }
        for(var i=0; i<ll; i++){
        	var inps = l.eq(i).next().find("input,select,textarea");
        	if(inps.next(".parsley-errors-list").length>0){
        		var pars = inps.siblings(".parsley-errors-list");
        		pars.appendTo(pars.parent()).addClass("hidden");
        	}
//        	if(inps.siblings(".btn:visible").length>0){
//        		var button = inps.siblings(".btn"),
//        		bw = 0;
//        		for(var k=0;k<button.length;k++){
//        			var tb = button.eq(k);
//        			bw += tb.outerWidth() + parseInt(tb.css("margin-left")) + parseInt(tb.css("margin-right"));
//        		}
//        		//console.info("bw->" + bw);
//    			var pw = button.eq(0).parent().innerWidth();
//    			button.eq(0).siblings("input,select,textarea").css({"width": pw - bw - 2, "float": "left"});
//        	}
        }
        for(var i=0; i<ll; i++){
        	if(lw_max !== 0){
        		var tl = l.eq(i);
	        	if(tl.siblings("div > label").length>0){
	        		var tlc = tl.siblings("div > label");
	        		if(tlc.closest(".weather-tool").length){
	        			tlc.show();
	        		}else{	        			
	        			tlc.css({"height":"30px", "margin-top":"0", "margin-bottom":"0", "padding-top":"0", "padding-bottom":"0"}).show();
	        		}
	        		continue;
	        	}
	        	//tl.css("width",lw_max);//.show();
        	}
        }
        
        //标记必填项
        t.initRequiredMark();
        //指定样式的数字保留两位小数
        t.initFixedNumber();
        //初始化天气选择控件
        if(t.find(".weather-tool").length > 0) initWeather();
    };
    //获取label标签宽度
    $.fn.getLabelWidth = function(){
    	
    	"use strict";
    	
        var ltext = this.text().replace("（","(").replace("）",")"),
        label = ltext.split(""),
        fs = parseFloat(this.css("font-size")),
        pl = parseFloat(this.css("padding-left")),
        pr = parseFloat(this.css("padding-right")),
        labelWidth = fs * (ltext.indexOf("(") > -1 ? label.length - 1 : label.length) + pl + pr;
        this.text(ltext);
        return labelWidth;
    };
    //初始化select2选单组件样式
    $.fn.cinitSelect2 = function(){
    	
    	"use strict";
    	
    	var t = this, 
    	id = t.attr("id") || t.attr("name") + "_select2";
    	clearTimeout(timer[id]);
    	var slt = t.siblings(".select2");
    	if(slt.length<1){
    		timer[id] = setTimeout(function(){t.cinitSelect2();},20);
        	return false;
    	}
    	var w = t.show().outerWidth(true);
    	slt.css({"width":w});
    	t.hide();
    };
    //初始化必填项目提示
    $.fn.initRequiredMark = function(){
    	
    	"use strict";
    	
    	var t = this, 
    	id = t.attr("id"),
    	rl = required_mark_item.length;
    	for(var k=0; k<rl; k++){
    		var v = required_mark_item[k],
    		requEl = $("#" + id + " [" + v + "]"),
    		rql = requEl.length;
    		for(var i=0; i<rql; i++){
    			var el = requEl.eq(i);
    			el.nextAll(".required-mark").length < 1 && el.after('<span class="required-mark"></span>');
    			el.nextAll(".required-mark").css({"left": el.offset().left - el.parent().offset().left + 3});
    		};
    	};
    };
    //初始化数字保留两位小数
    $.fn.initFixedNumber = function(){
    	
    	"use strict";
    	
    	var t = this, numbers = t.find("input.fixed-number");
    	for(var i=0; i<numbers.length; i++){
    		var tn = numbers.eq(i);
    		if(!tn.val()) continue;
    		tn.val(parseFloat(tn.val()).toFixed(2)).addClass("fixed");
    	}
//    	t.find("input.fixed-number").off("change.fixnumber").on("change.fixnumber",function(){
//    		var v = $(this).val() || 0;
//    		$(this).val(parseFloat(v).toFixed(2));
//    	});
    };

	/*
	 * select选单联动方法 - 脚本开始
	 */
	$.fn.linkSubSelect = function(callback){
		
		"use strict";
		
		var t = this,
		sub = t.attr("data-sub"),
		s = $("#" + sub);
		if(s.length < 1) return false;
		
		var loading = '<span class="sloading">查询中...</span>',
		failedload = '<span class="sloading">查询失败请重试</span>';
		
		t.parents(".form-group").addClass("select-group");
		s.parents(".form-group").addClass("select-group");
		var curr = $(".select-group").eq(0).find("select");
		if(curr.val() !== "") curr.addClass("currentLevel");
		
		//初始化一级选单
		if(t.attr("data-url")) {
			queryUrl = t.attr("data-url");
        	t.before(loading);
			$.ajax({
	            type: 'POST',
	            url: queryUrl,
	            data: 'codeLevel=' + t.attr("data-level"),
	            dataType: 'json',
	            success: function (data) {
	            	t.find("option").remove();
					t.append('<option></option>');
					$.each(data,function(key, val){
						t.append('<option value="' + val.addrCodeId + '">' + val.addrCodeDes + '</option>');
					});
					
					t.prev(".sloading").fadeOut(100,function(){
						t.prev(".sloading").remove();
					});
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	            	t.prev(".sloading").remove();
	            	t.before(failedload);
                    printXHRError("linkSubSelect", "初始化查询失败", jqXHR, textStatus, errorThrown);
	            }
	        });
		}

		
		var v = t.val(),
		l = s.attr("data-level"),
		c = callback,
		subv = s.find("option");
		if(v === ""){
			for(var i=0; i<subv.length; i++){
				if(subv.eq(i).text() === "") continue;
				subv.eq(i).hide();
			}
		}else{
			subv.show();
			for(var i=0; i<subv.length; i++){
				if(subv.eq(i).text() === "") continue;
				if(subv.eq(i).attr("data-parentId") != v){
					subv.eq(i).hide();
				}
			}
		}
		var ss = t.parents(".select-group").siblings(".select-group").last().find("select");
		if(!ss.attr("data-sub")){
			ss.on("change", function(e){
				var v = ss.val(),
				prev = ss.parents(".select-group").prevAll(".select-group"),
				dvalue = '';
				for(var i=prev.length-1; i>-1; i--){
					dvalue += prev.eq(i).find("select option:selected").text();
				}
				//console.info(ss.attr("name"));
				dvalue += ss.parents(".select-group").find("select option:selected").text();
				ss.attr("data-value", dvalue);
				ss.addClass("currentLevel").parents(".select-group").siblings().find(".currentLevel").removeClass("currentLevel");
				c && c(ss);
			});
		}
		
		t.on("change", function(){
			t.prev(".sloading").remove();
			s.prev(".sloading").fadeOut(100,function(){
				s.prev(".sloading").remove();
			});
        	s.before(loading);
			var v = t.val(),
			prev = t.parents(".select-group").prevAll(".select-group"),
			dvalue = '';
			for(var i=prev.length-1; i>-1; i--){
				dvalue += prev.eq(i).find("select option:selected").text();
			}
			dvalue += t.parents(".select-group").find("select option:selected").text();
			t.attr("data-value", dvalue);
			t.addClass("currentLevel").parents(".select-group").siblings().find(".currentLevel").removeClass("currentLevel");
			
			//清空后面的选单
			var nextall = $("select.currentLevel").parents(".select-group").nextAll(".select-group");
			nextall.find(".select2-selection__rendered").text("").attr("title","");
			
    		var subv = s.find("option");

			if(queryUrl){
				nextall.find("select").val("").find("option").remove();
				$.ajax({
		            type: 'POST',
		            url: queryUrl,
		            data: 'codeLevel=' + l + '&codeVal=' + v,
		            dataType: 'json',
		            success: function (data) {
						//subv.remove();
						s.append('<option></option>');
						$.each(data,function(key, val){
							s.append('<option value="' + val.addrCodeId + '">' + val.addrCodeDes + '</option>');
						});
						s.prev(".sloading").fadeOut(100,function(){
							s.prev(".sloading").remove();
						});
						c && c(t);
						//s.change();
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		            	s.prev(".sloading").remove();
		            	s.before(failedload);
	                    printXHRError("linkSubSelect", "初始化查询失败", jqXHR, textStatus, errorThrown);
		            }
		        });
			}else{
				nextall.find("select").val("").find("option").hide();
				subv.show();
				for(var i=0; i<subv.length; i++){
					if(subv.eq(i).text() === "") continue;
					if(subv.eq(i).attr("data-parentId") != v){
						subv.eq(i).hide();
					}
				}
				s.prev(".sloading").fadeOut(100,function(){
					s.prev(".sloading").remove();
				});
				c && c(t);
				//s.change();
			}
		});
		
		return t;
	};

	/*
	 * 表格循环操作相关方法
	 * 待编写事项
	 * 需要增加操作失败跳过方法 -> 测试中
	 * 修复渲染错误的bug
	 * 在表格datatable初始化完成方法体内引用，例：$(this).bindLoopEdit();
	 */
	$.fn.bindLoopEdit = function(){
		
		"use strict";
		
		var t = this;
		var id = t.attr("id");
		t.on("select.dt deselect.dt",function(){
			var selected = t.find("tr.selected");
			if(selected.length>1){
				rowLoop[id] = {};
			}else{
				rowLoop[id] = '';
				t.loopdone();
				return false;
			}
			for(var i=0; i<selected.length; i++){
				rowLoop[id][i] = selected.eq(i).index();
			}
			//console.info(JSON.stringify(rowLoop));
		})
		return t;
	};
	
	//在自定义的操作中引用，例：var index = $(this).loopnow();
	//返回当前满足执行条件的操作行的索引
	$.fn.loopnow = function(){
		
		"use strict";
		
		//第一个参数为列索引，其他参数为查询项，当前行相应列内容与查询项匹配则跳过这一行
		var t = this, a = arguments, now = '';
		var id = t.attr("id");
		if(rowLoop[id] === '' || rowLoop[id] === undefined) return false;
		//查找当前可执行的记录
		$.each(rowLoop[id],function(i,indx){
			if(indx === '' || indx === 'cannotloop' || indx === 'looperror' || indx === 'loopover') return true;
			//没有传参或没有传入查询项则直接返回这一行索引
			if(a.length > 1){
				var row = t.DataTable().column(a[0]).data()[indx];
				for(var k=1; k<a.length; k++){
					if(row === a[k]){
						t.find("tbody tr:eq(" + indx + ")").addClass("cannotloop");
						rowLoop[id][i] = "cannotloop";
						return true
					}
				}
			}
			now = indx;
			return false;
		});
		//console.info("current -> " + now);
		if(now !== '') t.find("tbody tr:eq(" + now + ")").addClass("currentloop");
		return now;
	};
	
	//在自定义的操作方法中引用，例：var next = $(this).loopnext();
	//返回当前操作行的下一行满足执行条件的操作行的索引
	$.fn.loopnext = function(){
		
		"use strict";
		
		//第一个参数为当前执行行的索引，第二个参数为列索引，其他参数为查询项，当前行相应列内容与查询项匹配则跳过这一行
		var t = this, a = arguments, next = '';
		if(a[0] === '') {
			console.warn("bindLoopEdit().loopnext() -> 使用时似乎没有判断当前索引值为空，可能会引起未知的问题，请检查并修正。");
			return false;
		}
		var id = t.attr("id");
		if(rowLoop[id] === '' || rowLoop[id] === undefined) return false;
		$.each(rowLoop[id],function(i,indx){
			//如果是当前行则查询它的满足执行条件的下一行
			if(indx === a[0]){
				for(var j = parseInt(i)+1;j<jsonLength(rowLoop[id]);j++){
					var idj = rowLoop[id][j];
					//console.info("idj -> " + idj);
					if(idj !== '' && idj !== 'cannotloop' && idj !== 'looperror' && idj !== 'loopover'){
						//没有传入列索引和查询项则直接返回这一行索引
						if(a.length > 2){
							var row = t.DataTable().column(a[1]).data()[idj];
							var cannotloop = 0;
							for(var k=2; k<a.length; k++){
								if(row === a[k]){
									t.find("tbody tr:eq(" + idj + ")").addClass("cannotloop");
									rowLoop[id][j] = "cannotloop";
									cannotloop = 1;
									break;
								}
							}
							//console.info("cannotloop -> " + cannotloop);
							if(cannotloop) continue;
						}
						next = idj;
						break;
					}
				}
			}else{
				return true;
			}
		});
		if(next !== '') t.find("tbody tr:eq(" + next + ")").addClass("nextloopitme");
		//console.info("next -> " + next + " / " + a[0]);
		return next;
	};
	
	//在自定义的执行完成方法中通过当前行的jQuery对象引用，例：$(thisrow).loopover();
	//该方法用于标记该行执行结果，如果自定义方法执行失败需要传入false
	$.fn.loopover = function(result, callback){
		
		"use strict";
		
		var t = this, tid = t.parents("table").attr("id");
		t.removeClass("currentloop");
		t.siblings(".nextloopitme").addClass("currentloop").removeClass("nextloopitme");
		if(rowLoop[tid] === '' || rowLoop[tid] === undefined) return false;
		var classname = result || result === "" || result === undefined ? "loopover" : "looperror";
		t.addClass(classname);
		$.each(rowLoop[tid],function(i,indx){
			if(indx === t.index()){
				rowLoop[tid][i] = classname;
			}
		});
		callback && callback(t);
		//console.info(JSON.stringify(rowLoop));
	};
	
	//在自定义的所有行编辑执行完成方法中引用，例：$(this).loopnext();
	//该方法用于清空各类标记
	$.fn.loopdone = function(callback){
		
		"use strict";
		
		var t = this, tid = t.attr("id");
		t.find("tbody tr").removeClass("cannotloop loopover looperror currentloop nextloopitme");
		rowLoop[tid] = '';
		callback && callback(t);
	};

	/*
	 * 表格列翻译方法
	 * 方法不再推荐使用，请在datatable的render属性内翻译数据
	 * 参数option
	 * 例子
	 * var myoption = {
	 * 	   cid: 3,
	 *     key: {"1":"待用","2":"在用","3":"停用"}
	 * }
	 * $("#mytable").tableDataTrans(myoption);
	 */
	$.fn.tableDataTrans = function(option){
		
		"use strict";
		
		var t = this;
        if(typeof(options) === 'string'){
        	console.error('tableDataTrans() -> 参数格式不正确');
        	return false;
        }else{
        	var cid = option.cid === '' || option.cid === undefined ? '' : option.cid;
        	var key = option.key === '' || option.key === undefined ? '' : option.key;
    		if(cid === '' || key === '' || typeof(cid) === 'string' || typeof(key) === 'string'){
    			console.warn('tableDataTrans() -> 参数设置不正确');
            	return false;
    		}
        }
        
        $.each(key,function(i,v){
            var rows = t.find("tbody tr").not(".transed");
        	for(var j=0;j<rows.length;j++){
        		var td = rows.eq(j).find("td:eq("+cid+")");
        		if(i === td.text()){
        			td.text(v);
        			rows.eq(j).addClass("transed");
        		}
        	}
        });
        
        return t;
	};
	
	/*
	 * 监听datatable列表行选中，并取得参数
	 * 调用方法：table的jQuery对象直接调用
	 * 例子：
	 * $("#mytable").bindDTSelected(mycallbackFunc, selectRowIndex);
	 * 参数
	 * mycallbackFunc：回调函数。
	 * selectRowIndex：默认选中行索引，整数或true/false,传入true表示选中第一行，传入false或不传改参则默认不选中任何行
	 * 回调函数会携带五个参数：
	 * v：选中行（如果选中多行则取索引最小行）的所有单元格值的数组
     * indexes：选中行在datatable中的索引，数组对象，长度为1则表示选中单行
     * indx：选中行（如果选中多行则取索引最小行）在当前页的索引
     * selectedrow：选中行（如果选中多行则取索引最小行）的jQuery对象
     * rowjson：选中行（如果选中多行则取索引最小行）的所有关联数据对象（json）
	 * 回调函数示例：
	 * function mycallbackFunc(v, i, displayIndex){
     *	   alert(v[3] + "/" + i);
     * }
     * 
     * 尚需开发的部分：
     * 多选后取消一行的需要相应动作的问题
     * 改造getDetail方法
     * 
	 */
	$.fn.bindDTSelected = function(c, s){
		
		"use strict";
		
		var t = this, id = t.attr("id");
		selectTr[id] = -1;
		var v = [];
		t.on("order.dt search.dt page.dt destroy.dt",function(){
			t.DataTable().rows('.selected', { page: 'current' }).deselect();
		    if(t.find("tbody tr.selected").length < 1){
			    trSData[id] = {};
		    }
		});
		t.on("deselect.dt", function ( e, dt, type, indexes ) {
		    dt[ "rows" ]( indexes ).nodes().to$().removeClass( 'selected' );
		    if(t.find("tbody tr.selected").length < 1){
			    trSData[id] = {};
		    }else{
		    	dt.rows('.selected', { page: 'current' }).select();
		    }
		});
		t.on("select.dt",function( e, dt, type, indexes ){
			dt = dt ? dt : t.DataTable();
			if(indexes !== undefined && indexes.length>1){
			}
			indexes = indexes ? indexes : selectTr[id] > 0 ? [selectTr[id]] : dt.row(indexes)[0];
			//定义当前页行索引
			var indx = dt.settings()[0].oFeatures.bServerSide ? dt.rows().eq(0).indexOf(dt.row(indexes).index()) : dt.rows().eq(0).indexOf(dt.row(indexes).index()) - (dt.page() * dt.page.len()), 
				col = 0,
				i = dt.rows().eq(0).indexOf(dt.row(indexes).index());
			if(typeof(indexes) === "object" && indexes.length === 1 && indx !== s){
				t.find("tbody tr").removeClass("selected");
				t.find("tbody tr:eq(" + indx + ")").addClass("selected");
			}
			dt.columns().every(function(){
				//循环列，放入数组中
				v[col] = this.data()[i];
				col++;
			});
			//定义请求获得的数据对象，并获取当前行关联数据对象
			var json = dt.data(),rowjson,rowsjson = [];
			if(jsonLength(t.DataTable().data()) < 1){
				t.find("tbody tr.selected").removeClass("selected");
			}else{
			}
			if(selectTr[id] > -1){
				t.find("tbody tr.selected").removeClass("selected");
				t.find("tbody tr:eq(" + selectTr[id] + ")").addClass("selected");
				if(dt.settings()[0].oScroll.sY > 0){
					try{
						dt.row(selectTr[id]).scrollTo();
					}catch(error){
						dt.settings()[0].oFeatures["bPaginate"] = false;
						dt.draw();
						console.warn("需要在页面中引用下列文件以激活滚动跟随效果 -> assets/plugins/DataTables/extensions/Scroller/js/dataTables.scroller.min.js");
					}
				}
			}
			if(indexes.length > 1){
				rowjson = json[0];
				rowsjson = json;
			}else{
				rowjson = json;
				rowsjson.push(json);
			}
			//console.info("json->" + JSON.stringify(json));
			//定义当前行jQuery对象
			var selectedrow = t.find("tbody tr:eq(" + indx + ")"),
			pv = fixNull(v),
			pjson = fixNull(rowjson),
			pjsons = fixNull(rowsjson);
			
			trSData.v = pv;
			trSData.i = indexes;
			trSData.index = indx;
			trSData.t = selectedrow;
			trSData.json = pjson;
			trSData.jsons = pjsons;
			
			//2016-07-12 新增表格id绑定，目的为解决多表格共用问题
			if(!trSData[id]) trSData[id] = {};
			trSData[id].v = pv;
			trSData[id].i = indexes;
			trSData[id].index = indx;
			trSData[id].t = selectedrow;
			trSData[id].json = pjson;
			trSData[id].jsons = pjsons;
			
			c && c(pv, indexes, indx, selectedrow, pjson);
			selectTr[id] = -1;
			
			//换行则相关操作按钮移除选中状态
			t.siblings().find(".business-tool-btn").removeClass("active");
		});
		if(s !== false && s !== "" && s !== undefined){
			s = s === true ? 0 : s;
			selectTr[id] = s > 0 ? s : -1;
			if(!t.find('tbody tr:eq(0) td').hasClass("dataTables_empty")) t.DataTable().row(':eq(' + s + ')', { page: 'current' }).select();
		}
		return t;
	};
	
	//清除选中行
	$.fn.clearSelected = function(){
		
		"use strict";
		
		this.DataTable().rows('.selected', { page: 'current' }).deselect();
		return this;
	};
	
	/*
	 * 定义用于保存全部查询框查询条件历史记录的对象
	 * 历史记录存储对象
	 * 自动保存各检索框内容为历史记录
	 */
	var sHistoryList = {};	
	/*
	 * 保存当前查询条件
	 * 该方法自动将每次查询条件添加到历史记录中。
	 * 例：
	 * $(this).setQueryHistory(data,hid);
	 * 参数：
	 * data，可通过表单序列化为JSON对象的方法获取，也可通过其他方式获取json格式的值。例：var data = $("#formId").serializeJson();
	 * hid，用于唯一区别其他查询框历史记录的标记，请确保唯一性
	 */
	$.fn.setQueryHistory = function(data, hid){
		
		"use strict";
		
		if(!hid){
			console.error("setQueryHistory -> hid参数是必须的，用于唯一区别各历史记录的关系");
			return false;
		}
		var index = jsonLength(sHistoryList[hid]);
		if(!index) sHistoryList[hid] = {};
		if(isSameJson(data, sHistoryList[hid][(index - 1)])){
			//console.info("setQueryHistory -> 相邻且相同值,该组查询条件未记录");
		}else{
			sHistoryList[hid][index] = data;
		}
		var sHistoryListStr = JSON.stringify(sHistoryList);
		if(window.localStorage){
			localStorage.setItem("shls", sHistoryListStr);   
		}else{      
		    console.warn("setQueryHistory -> 浏览器版本过低，不支持保存查询历史记录，建议更换ie9以上版本或谷歌、火狐浏览器") 
		}
		//console.info(sHistoryList);
		return this;
	};

	
	/*
	 * 获取指定hid对应的最近一条查询条件历史记录
	 * 例：
	 * var historydata = $(this).loadQueryHistory(hid);
	 * 参数：
	 * hid，用于唯一区别其他查询框历史记录的标记，请确保唯一性
	 * 方法返回的是表单序列化为Json的对象或其他方式获取的包含存入的属性和值的json对象
	 */
	$.fn.loadQueryHistory = function(hid){
		
		"use strict";
		
		if(!hid){
			console.error("loadQueryHistory -> hid参数是必须的，用于唯一区别各历史记录的关系");
			return false;
		}
		if(!sHistoryList[hid]){
			var index, data;
			if(window.localStorage){
				var sHistoryListStr = localStorage.getItem("shls");
				var shls = JSON.parse(sHistoryListStr);
				if(!shls) return false;
				index = jsonLength(shls[hid]);
				data = index === 0 ? {} : shls[hid][(index - 1)];
				return data;
			}else{  
			    console.warn("setQueryHistory -> 浏览器版本过低，不支持保存查询历史记录，建议更换ie9以上版本或谷歌、火狐浏览器");
				return false;
			} 
		}else{
			var index, data;
			index = jsonLength(sHistoryList[hid]);
			if(index){
				data = index === 0 ? {} : sHistoryList[hid][(index - 1)];
			}
			if(window.localStorage){
				var sHistoryListStr = localStorage.getItem("shls");
				var shls = JSON.parse(sHistoryListStr);
				if(!shls) return false;
				index = jsonLength(shls[hid]);
				data = index === 0 ? {} : shls[hid][(index - 1)];
			}else{      
			    console.warn("setQueryHistory -> 浏览器版本过低，不支持保存查询历史记录，建议更换ie9以上版本或谷歌、火狐浏览器");
			}
			return data;
		}
	};

	/*
	 * 获取指定hid对应的全部查询条件历史记录
	 * 该方法为保留方法，用于列出某个查询框的历史查询记录
	 * 例：
	 * var historydatas = $(this).listQueryHistory(hid, num);
	 * 参数：
	 * hid，用于唯一区别其他查询框历史记录的标记，请确保唯一性
	 * num，用于设定返回历史记录的条数，如果不设置则返回全部
	 * 方法返回的是一组表单序列化为Json的对象或其他方式获取的包含存入的属性和值的json对象，即该查询框全部历史记录的json对象，通过索引可以获取指定的一次查询条件记录。
	 */
	$.fn.listQueryHistory = function(hid, num){
		
		"use strict";
		
		if(!hid){
			console.error("listQueryHistory -> hid参数是必须的，用于唯一区别各历史记录的关系");
			return false;
		}
		num = !num ? 999999 : parseInt(num);
		if(!sHistoryList[hid]){
			var index, data;
			if(window.localStorage){
				var sHistoryListStr = localStorage.getItem("shls");
				var shls = JSON.parse(sHistoryListStr);
				if(!shls) return false;
				index = jsonLength(shls[hid]);
				data = index === 0 ? {} : shls[hid];
				if(index > 0){
					data = {};
					if(index > num){
						for(var i=0; i<num; i++){
							data[i] = shls[hid][index - (i + 1)];
						}
					}else{
						for(var i=0; i<index; i++){
							data[i] = shls[hid][index - (i + 1)];
						}
					}
				}
				return data;
			}else{  
			    console.warn("setQueryHistory -> 浏览器版本过低，不支持保存查询历史记录，建议更换ie9以上版本或谷歌、火狐浏览器");
				return false;
			} 
		}else{
			var index, datal, data;
			index = jsonLength(sHistoryList[hid]);
			if(index){
				datal = index === 0 ? {} : sHistoryList[hid];
			}
			if(window.localStorage){
				var sHistoryListStr = localStorage.getItem("shls");
				var shls = JSON.parse(sHistoryListStr);
				if(!shls) return false;
				index = jsonLength(shls[hid]);
				datal = index === 0 ? {} : shls[hid];
			}else{      
			    console.warn("setQueryHistory -> 浏览器版本过低，不支持保存查询历史记录，建议更换ie9以上版本或谷歌、火狐浏览器");
			}
			if(index > 0){
				data = {};
				if(index > num){
					for(var i=0; i<num; i++){
						data[i] = datal[index - (i + 1)];
					}
				}else{
					for(var i=0; i<index; i++){
						data[i] = datal[index - (i + 1)];
					}
				}
			}else{
				data = {};
			}
			return data;
		}
	};

	/*
	 * 清除指定hid对应的全部查询条件历史记录
	 * 例：
	 * $(this).clearQueryHistory(hid);
	 * 参数：
	 * hid，用于唯一区别其他查询框历史记录的标记，请确保唯一性；警告：不传入 hid 将清除所有查询框的历史记录数据
	 * 注意：该方法将同时清空对应弹窗中查询框的现有值和其全部历史记录
	 */
	$.fn.clearQueryHistory = function(hid){
		
		"use strict";
		
		if(!hid){
			sHistoryList = {};
			if(window.localStorage){
				sHistoryListStr = JSON.stringify(sHistoryList);
				localStorage.setItem("shls", sHistoryListStr);  
			}else{      
			    console.warn("setQueryHistory -> 浏览器版本过低，不支持保存查询历史记录，建议更换ie9以上版本或谷歌、火狐浏览器");
			}
			console.warn("clearQueryHistory -> 警告：未传入 hid，已清除所有查询框的历史记录数据！");
			return false;
		}
		if(!sHistoryList[hid]){
			var index, data;
			if(window.localStorage){
				var sHistoryListStr = localStorage.getItem("shls");
				var shls = JSON.parse(sHistoryListStr);
				if(!shls) return false;
				index = jsonLength(shls[hid]);
				if(index){
					$.each(shls[hid][(index - 1)],function(i,n){
						if($('.modal-body [name="' + i + '"]').length < 1) return true;
						$('.modal-body [name="' + i + '"]').val("");
					});
					shls[hid] = '';
				}
				sHistoryListStr = JSON.stringify(shls);
				localStorage.setItem("shls", sHistoryListStr);  
			}else{      
			    console.warn("setQueryHistory -> 浏览器版本过低，不支持保存查询历史记录，建议更换ie9以上版本或谷歌、火狐浏览器");
				return false;
			}
		}else{
			var index, data, sHistoryListStr;
			index = jsonLength(sHistoryList[hid]);
			//console.info(index);
			if(index){
				$.each(sHistoryList[hid][(index - 1)],function(i,n){
					if($('.modal-body [name="' + i + '"]').length < 1) return true;
					$('.modal-body [name="' + i + '"]').val("");
					//console.info(i);
				});
				sHistoryList[hid] = '';
			}
			if(window.localStorage){
				var sHistoryListStr = localStorage.getItem("shls");
				var shls = JSON.parse(sHistoryListStr);
				if(!shls) return false;
				index = jsonLength(shls[hid]);
				if(index){
					$.each(shls[hid][(index - 1)],function(i,n){
						if($('.modal-body [name="' + i + '"]').length < 1) return true;
						$('.modal-body [name="' + i + '"]').val("");
						//console.info(i);
					});
					shls[hid] = '';
				}
				sHistoryListStr = JSON.stringify(shls);
				localStorage.setItem("shls", sHistoryListStr);   
			}else{      
			    console.warn("setQueryHistory -> 浏览器版本过低，不支持保存查询历史记录，建议更换ie9以上版本或谷歌、火狐浏览器");
			} 
		}
		return this;
	};

	/*
	 * 将最近一条或指定的历史查询条件回填到查询框中
	 * 例：
	 * $(this).refillHistory(hid || historydata);
	 * 如果指定回填某个表单,使用表单的jQuery对象引用。
	 * 参数：
	 * hid，用于唯一区别其他查询框历史记录的标记，请确保唯一性；
	 * historydata，指定的历史查询条件json对象，配合listQueryHistory方法使用
	 * 以上两个参数二选一。
	 */
	$.fn.refillHistory = function(arg){
		
		"use strict";
		
		if(!arg){
			return false;
		}
		var data, t = this, target = $(".modal-body");
		if(typeof arg !== 'object'){
			data = this.loadQueryHistory(arg);
		}else{
			data = arg;
		}
		if(typeof data !== 'object'){
			return false;
		}
		if(t.is("form")) target = t;
		$.each(data,function(i,n){
			var input = target.find('[name="' + i + '"]');
			if(input.length < 1) return true;
			input.is("select") ? input.val(n).change() : input.val(n);
		});
		return this;
	};

	/*
	 * 获取自定义元素列表的序列化json对象
	 * 例：
	 * $(this).getDiyData(name1, name2, name3,...);
	 * 参数：
	 * name，一组元素的name，如果不传入将自动获取当前弹窗的所有表单元素的序列化json对象
	 */
	$.fn.getDiyData = function(){
		
		"use strict";
		
		var a = arguments,
		data = {};
		if(a.length > 0){
			for(var i=0; i<a.length; i++){
				data[a[i]] = $('.modal-body [name="' + a[i] + '"]').val();
			}
		}else{
			var names = $(".modal-body").find("input,textarea,select");
			for(var i=0; i<names.length; i++){
				var n = names.eq(i).attr("name");
				if(!n) continue;
				data[n] = names.eq(i).val();
			}
		}
		
		//console.info(data);
		return data;
	};

	/*
	 * 日期选单时间戳转换为日期型 - 脚本开始
	 */
//	var formatTimeTimer = {};
	$.fn.formatTime = function(type){
		
		"use strict";
		
		var t = this, 
		v = t.val(), 
		id = t.attr("id");
//		clearTimeout(formatTimeTimer[id]);
		if(t.length < 1){
			return false;
		}
		var l = parseInt(v).toString().length;
		if((v.length === l && l > 9) || v.length === 0){
			if(t.parent().find(".copyedInput").length < 1){
				var newInput = t.clone();
				t.removeAttr("data-parsley-required");
				newInput.attr("name",newInput.attr("name") + "_copy").attr("id",newInput.attr("id") + "_copy").attr("data-linkedId",id).addClass("copyedInput");
				t.hide().before(newInput);
				var ntd = $('[data-linkedId="' + id + '"]');
				ntd.off("change.formatStamp").on("change.formatStamp",function(){
					$("#" + id).val(timestamp($(this).val()));
				});
				ntd.datepicker({
		            todayHighlight: true
		        });
			}
			t.parent().find('[data-linkedId="' + id + '"]').val(v.length === 0 ? "" : format(parseInt(v), type));
		}
	};

	/*
	 * 方便的取得Datatable表格行值
	 * 例
	 * var yourValue = $("#yourTableId").cgetColumnValue(属性名, 行索引);
	 */
	$.fn.cgetColumnValue = function(colname,index){
		
		"use strict";
		
		if(!index) index = 0;
		var t = this,
		dt = t.DataTable(),
		r = dt.ajax.json().data ? dt.ajax.json().data[index] : dt.ajax.json()[index],
		v = r[colname];
		if(v === undefined) v = colname + ' 属性不存在';
		return v;
	};

	/*
	 * 获取Datatable表格父表单的数据方法 - 脚本开始
	 */
	$.fn.getDTFormData = function(mode){
		
		"use strict";
		
		mode = !mode ? "json" : mode;
		var t = this, 
		a = [], 
		original, 
		k_k = [];
		original = t.serializeJson();
		if(mode === "array"){
			$.each(original, function(k, v){
				var d = k.indexOf("_");
				if(d < 1) {
					console.warn("getDTFormData -> 表格初始化过程表单元素name设置格式无效，该字段数据将被丢弃：" + k);
					return true;
				}
				k_k[0] = k.substring(0, d);
				k_k[1] = k.substring(d + 1);
				if(a[k_k[0]]){
					a[k_k[0]][k_k[1]] = v;
				}else{
					a[k_k[0]] = {};
					a[k_k[0]][k_k[1]] = v;
				}
			});
		}else{
			$.each(original, function(k, v){
				var d = k.indexOf("_");
				if(d < 1) {
					console.warn("getDTFormData -> 表格初始化过程表单元素name设置格式无效，该字段数据将被丢弃：" + k);
					return true;
				}
				var dk = k.substring(0, d);
				if($.inArray(dk, k_k) === -1) k_k.push(dk);
			});
			for(var i=0; i<k_k.length; i++){
				var j = {}, 
				kk, 
				kv;
				$.each(original, function(k, v){
					var d = k.indexOf("_");
					if(d < 1) {
						return true;
					}
					kk = k.substring(0, d);
					kv = k.substring(d + 1);
					if(k_k[i] === kk){
						if(j[kv]){
							return true;
						}else{
							j[kv] = v;
						}
					}else{
						return true;
					}
				});
				a.push(j);
			}
		}
		console.info(a);
		return a;
	};

	/*
	 * 签名工具方法 handleSignature
	 * 引用：$("#signBtn").handleSignature();
	 * 参数 location 布尔值，用于设定移动端下签字是否需要定位，true：需要，false：不需要，默认为true
	 */
	$.fn.handleSignature = function(location){
		
		"use strict";
		
		//获取签字按钮
		location = location === undefined ? true : location;
		if(!_inApk && location) location = false; 
		var signatures = this,
		la = "", lo = "", al = "";
		//弹出签字屏
		signatures.off("click").click(function(){
			$("body").cgetPopup({
				title: '签字',
				content: '<div class="signatureBox"></div>',
				accept: {
					func: signDone,
					singleArgs: $(this)
				},
				size: _inApk ? "full" : "large",
				icon: "fa-pencil",
				callback: {
					func: intSignBox,
					singleArgs: $(this)
				}
			});
		});
		//完成签名动作
		var signDone = function(t){
//			if(!_inApk){
				//绘制时间戳和坐标
				//获取时间戳
				var time = new Date().getTime(),
				name = t.siblings(".sign-data-input").attr("name"),
				signCanvas = $(".jSignature:visible")[0].getContext("2d");
		        signCanvas.fillText(time, 110, 5);
		        
		        
		        /*console.info("time--"+time);
		        console.info("name--"+name);
		        console.info("格式化--"+format(time,"all"));
		        var s=$("#"+name+"").parents("div:first");
		        console.info("div输出--");
		        console.info(s);
		        var divId=s.a.attr('id');
		        console.info("divId输出--");
		        console.info(divId);*/
		        //.next("div").find('input').attr('id')
		        if($('[name="' + name + '_time"]').length){
		        	$('[name="' + name + '_time"]').val(time);
		        }else{
		        	t.after('<input type="hidden" class="sign_attach_info" id="' + name + '_time" name="' + name + '_time" value="' + time + '">');
		        };
//			}
			t.removeClass("required-sign");
			var t = t.parent(), 
			imgdata = $(".signatureBox").jSignature("getData", "svgbase64"),
			img_data = "data:" + imgdata[0] + "," + imgdata[1];
			if(imgdata[1] === "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+PCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj48c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgdmVyc2lvbj0iMS4xIiB3aWR0aD0iMCIgaGVpZ2h0PSIwIj48L3N2Zz4="){
				//console.info("未成功签字!");
				return true;
			}
			$(".signatureBox").jSignature("reset");
			if(!_isIE){
				if(t.find("img").length > 0){
					t.find("img").attr("src", img_data);
				}else{
					var img = new Image();
					img.src = img_data;
					img.title = '点击查看大图',
					t.find(".clear-sign").before($(img));
				}
			}else{
				if(t.find(".img").length > 0){
					t.find(".img").css("background-image", "url(" + img_data + ")").attr({"src": img_data, "title": "点击查看大图"});
				}else{
					t.find(".clear-sign").before('<div class="img" src="' + img_data + '" title="点击查看大图" style="background: url(' + img_data + ');"></div>');
				}
			}
			t.find("input.sign-data-input").val(img_data);
		},
		//初始化签名面板
		intSignBox = function(t){
			var sbox = $(".signatureBox"),
			sparent = sbox.closest(".modal-body"),
			resetBtn = '<a href="javascript:;" class="btn btn-sm btn-white sign-resetBtn"><i class="fa fa-eraser"> 清除</a>';


			sbox.css({"opacity": 0}).loadMask("正在定位...");
			setTimeout(function(){
				var canvasHeight = sparent.innerHeight() - 36;
				sbox.css({"height": canvasHeight});
				sbox.append(resetBtn);
				sbox.jSignature({'lineWidth':8});
				sbox.css({"opacity": 1});
				
				//绘制时间戳和坐标
				var signCanvas = $(".jSignature:visible")[0].getContext("2d");
				
				signCanvas.font = '12px Consolas';
				signCanvas.fillStyle = "#999999";
				signCanvas.shadowColor = "#ffffff";
				signCanvas.shadowBlur = 0;
				signCanvas.shadowOffsetX = 0;
				signCanvas.shadowOffsetY = 0;
				signCanvas.lineWidth = 8;
		        signCanvas.textAlign = 'left';
		        signCanvas.textBaseline = 'top';
		        //$(".jSignature").css("height","223px");
				if(location){
                    var locationTimer = null;
                    console.info('定位…');
                    var putPosition = function(position){
                        locationTimer && clearTimeout(locationTimer);
                        if(position.latitude === undefined){
                            console.info('百度定位');
                            baidu_location.getCurrentPosition(function(data){
                                console.log(JSON.stringify(data));
                                data.longitude = data.lontitude;
                                delete data.lontitude;
                                putPosition(data);
                                //alert(JSON.stringify(data));
                            }, function(data){
                                alert(JSON.stringify(data));
                            });
                            return
                        }
                        signCanvas.fillText(position.altitude || "", 15, 5);
                        signCanvas.fillText(position.longitude || "", 15, 24);
                        signCanvas.fillText(position.latitude || "", 120, 24);
                        sbox.data("altitude", position.altitude || "");
                        sbox.data("latitude", position.latitude || "");
                        sbox.data("longitude", position.longitude || "");
                        sbox.hideMask();
                        var name = t.siblings(".sign-data-input").attr("name");
                        if ($('[name="' + name + '_altitude"]').length) {
                            $('[name="' + name + '_altitude"]').val(position.altitude);
                        } else {
                            t.after('<input type="hidden" class="sign_attach_info" id="' + name + '_altitude" name="' + name + '_altitude" value="' + position.altitude + '">');
                        }
                        ;
                        if ($('[name="' + name + '_latitude"]').length) {
                            $('[name="' + name + '_latitude"]').val(position.latitude);
                        } else {
                            t.after('<input type="hidden" class="sign_attach_info" id="' + name + '_latitude" name="' + name + '_latitude" value="' + position.latitude + '">');
                        }
                        ;
                        if ($('[name="' + name + '_longitude"]').length) {
                            $('[name="' + name + '_longitude"]').val(position.longitude);
                        } else {
                            t.after('<input type="hidden" class="sign_attach_info" id="' + name + '_longitude" name="' + name + '_longitude" value="' + position.longitude + '">');
                        }
                        ;
                    };


                    //undefined
					//window.currentPosition ? putPosition(window.currentPosition.coords) : cgetLocation(putPosition);
                    cgetLocation(putPosition);

                    locationTimer = setTimeout(function () {
                        putPosition({})
                    }, 5000);
					setTimeout(function(){
						sbox.hideMask();
					}, 10 * 1000);

				}else{
			        sbox.hideMask();
				}
			}, 200);
		};
		//清除按钮事件绑定
		signatures.nextAll(".clear-sign").off("click").click(function(){
			$(this).prevAll("img").remove();
			$(this).prevAll("input").not(".signPost").val("");
			var time = new Date().getTime(),
			name = $(this).siblings(".sign-data-input").attr("name");
	        if($('[name="' + name + '_time"]').length){
	        	$('[name="' + name + '_time"]').val(time);
	        }
		});
		
		$.each(signatures, function(i, el){
			var input = $(el).nextAll('input[type="hidden"].sign-data-input'),
			img = $(el).nextAll('img');
			input.val() === "" ? img.remove() : img.attr("src", input.val());
		});
		
	};
	//清空签名
	$.fn.resetSign = function(){
		
		"use strict";
		
		this.nextAll(".clear-sign").click();
	};
	//关闭或开启签名
	$.fn.toggleSign = function(action){
		"use strict";
		action = action === undefined || action === "" ? "" : action;
		var signTool = this,
		stl = signTool.length;
		for(var i=0; i<stl; i++){
			var t = signTool.eq(i).parent().find(".btn, .clear-sign, input:hidden");
			if(action === ""){
				t.toggleClass('disabled');
			}else if(action){
				t.removeClass('disabled').removeAttr('disabled');
			}else{
				t.addClass('disabled');
			}
		}
	};
	
	/*
	 * 关联操作相关方法 
	 * 初始化关联操作
	 * 引用：$(".attach-panel").initAttachOper(options);
	 * 参数：
	 * var options = {
	 *     //采集按钮的设置, 不设置则表示不在关联操作中显示该功能按钮
	 * 	   collection: {
	 * 	       url		: 'projectAccept/dataPopPage',		//采集页面url, 不设置则使用默认的采集页面
	 * 	       init		: myFunction,						//页面加载完成后需要立即执行的额外的方法, 详细设置见下方, 不设置表示没有额外方法
	 *         callback	: mycallback,						//采集完成后的回调, 将在关闭采集弹窗时执行, 不设置表示没有回调
	 *         tableid  : 'mytableid'						//关联操作涉及列表信息时,例如需要在列表获取选定行对象项目的项目id时，需要传入该列表的id
	 *     },
	 *     //收费按钮
	 * 	   charge: {
	 * 	       url		: 'projectCharge/dataPopPage',
	 * 	       init		: myFunction,
	 *         callback	: mycallback,
	 *         tableid  : 'mytableid'
	 *     },
	 *     //详情按钮
	 * 	   detail: {
	 * 	       url		: 'projectDetail/dataPopPage',
	 * 	       init		: myFunction,
	 *         callback	: mycallback,
	 *         tableid  : 'mytableid'
	 *     },
	 *     //计划按钮
	 * 	   dispatch: {
	 * 	       url		: 'projectDispatch/dataPopPage',
	 * 	       init		: myFunction,
	 *         callback	: mycallback,
	 *         tableid  : 'mytableid'
	 *     }
	 *     //客户按钮
	 * 	   customer: {
	 * 	       url		: 'projectDispatch/dataPopPage',
	 * 	       init		: myFunction,
	 *         callback	: mycallback,
	 *         tableid  : 'mytableid'
	 *     }
	 *     //金额变更明细
	 * 	   settlementchange: {
	 * 	       url		: 'projectDispatch/dataPopPage',
	 * 	       init		: myFunction,
	 *         callback	: mycallback,
	 *         tableid  : 'mytableid'
	 *     }
	 * }
	 * 针对init和callback参数的额外说明
	 * 1. 如果参数为函数名则可传入不带参数的函数
	 * init: myFunction
	 * 
	 * 2. 如果参数为字符串则可传入携带字符串参数函数或不携带参数的函数
	 * init: 'myFunction('+myArguments+')'
	 * 
	 * 3. 参数可以为json对象
	 * init: {
	 *     func: myFunction,		//函数名
	 *     singleArgs: myArguments	//参数，用于向func传入单个参数，不设置该属性或设置为空，则传入不带参数的函数
	 * }
	 * 3.1  其参数部分可以设置为数组对象以传入多个参数
	 * init: {
	 *     func: myFunction,			//函数名
	 *     arrayArgs: [arg1, arg2,...]	//参数，用于向func传入多个参数，不设置该属性或设置为[]，则传入不带参数的函数
	 * }
	 * 3.2 同一设置项的 singleArgs 和 arrayArgs 属性不可同时设置
	 */
	$.fn.initAttachOper = function(options){
		
		"use strict";
		
		if(typeof options !== 'object'){
			console.error("initAttachOper -> 参数格式不正确，期望json对象类型");
			return false;
		}
		var t = this, 
		o = options, 
		a1 = t.find(".attach-btn.collection"),
		a2 = t.find(".attach-btn.charge"),
		a3 = t.find(".attach-btn.detail"),
		a4 = t.find(".attach-btn.dispatch"),
		a5 = t.find(".attach-btn.customer"),
		a6 = t.find(".attach-btn.applydelay"),
		a7 = t.find(".attach-btn.settlementchange"),
		a8 = t.find(".attach-btn.auditInformation"),
		a9 = t.find(".attach-btn.grade"),
		a10 = t.find(".attach-btn.modify"),
		a11 = t.find(".attach-btn.fallBack"),
		a12 = t.find(".attach-btn.rectify"),
		a13 = t.find(".attach-btn.operateWorkFlow");
		setTimeout(function(){
			if(o.collection){
				a1.removeClass("hidden").off("click").on("click",function(){
					var url = o.collection.url ? o.collection.url : 'accessoryCollect/main',
					init = o.collection.init ? o.collection.init : '',
					callback = o.collection.callback ? o.collection.callback : '',
					tid = o.collection.tableid ? o.collection.tableid : '',
					//collectionTable用于采集获取tableID
					tid = $(".collectionTable").length>0?$(".collectionTable").attr('id'):tid,
					pjson = tid ? trSData[tid].json : '',
					innerLtypeId = $('[type="checkbox"][name="projectType"]'),
					getType = function(){
						if(innerLtypeId.length > 0 && innerLtypeId.filter(":checked").length < 1) return false;
						var typeId = '';
						if(innerLtypeId.length > 0){
							$.each(innerLtypeId.filter(":checked"), function(k, v){
								typeId += (k === 0 ? "" : ",") + $(v).val();
					    	});
						}else{
							typeId = pjson ? pjson.projectType : '';
						}
						return typeId;
					},
					projLtypeId = getType(),
					cinit = function(){
						//获取列表中选中的工程id、编号、类别等信息
						$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');
						$('.modal-body [name="projNo"]').val(pjson ? pjson.projNo : '');
						$('.modal-body [name="projStatusId"]').val(pjson ? pjson.projStatusId : '');
						$('.modal-body [name="stepId"]').val($(".has-sub.active > a span").text() || $('[data-mid="' + getStepId() + '"] > span').text());
						$('.modal-body [name="step"]').val(getStepId());//步骤id
						//$('.modal-body [name="alPath"]').val(pjson ? pjson.projNo+"/"+$(".has-sub.active > a span").text() : '');   // 资料归档路径按时按照工程编号
						$('.modal-body [name="alPath"]').val(pjson ? pjson.projNo+"/"+getStepId() : '');   // 资料归档路径按时按照工程编号
						$('.modal-body [name="projStatusDes"]').val(pjson ? pjson.projStatusDes : '');
						$('.modal-body [name="projectType"]').val(projLtypeId);
						$("#projLtypeId").val(projLtypeId);
						$('.modal-body [name="busRecordId"]').val($(".accBusRecordId").val());	//busRecordId需要存储的可以加
						$('.modal-body [name="sourceType"]').val($(".sourceType").val());	//sourceType需要存储的可以加
						$('.corpId').val(pjson ? pjson.corpId : '');
				        //获取初始化函数参数设置
				    	if(typeof init === 'object'){
				    		var initArgs = init.singleArgs ? [init.singleArgs] : init.arrayArgs || [];
				    		init = init.func || "";
				    	}
				    	crunFunc(init, initArgs);
				    	//初始化资料收集标准
				        firstdatainit();
				    	seconddatainit();
					};
					if(!pjson){
						
			        	if(_inApk){
			        		navigator.notification.alert('抱歉，系统没有找到关联项目，请先选择或新增一个项目！', null, '没有指定关联项目', '确定');
			        	}else{
							$("body").cgetPopup({
								title: '没有指定关联项目',
								content: "抱歉，系统没有找到关联项目，请先选择或新增一个项目！",
								ahide: 'true',
								ctext: '确定',
								newpop: 'new',
								icon: 'fa-warning'
							});
			        	}
						return false;
					};
					if(projLtypeId === false){
			        	if(_inApk){
			        		navigator.notification.alert('抱歉，该操作需要指定用气规模！', null, '没有指定用气规模', '确定');
			        	}else{
							$("body").cgetPopup({
								title: '没有指定用气规模',
								content: "抱歉，该操作需要指定用气规模！",
								ahide: 'true',
								ctext: '确定',
								newpop: 'new',
								icon: 'fa-warning'
							});
			        	}
						return false;
					}
					$("body").cgetPopup({
						title: '资料收集',
						content: "#" + url,
						accept: callback,
						ahide: callback ? 'false' : 'true',
						chide: 'true',
						size: 'large',
						callback: cinit,
						newpop: 'new',
						icon: 'fa-upload',
						nocache: true
					});
				});
			}
			
			if(o.charge){
				a2.removeClass("hidden").off("click").on("click",function(){
					var url = o.charge.url ? o.charge.url : 'charge/chargePopPage',
					init = o.charge.init ? o.charge.init : '',
					callback = o.charge.callback ? o.charge.callback : '',
					tid = o.charge.tableid ? o.charge.tableid : '',
					pjson = tid ? trSData[tid].json : '',
					cinit = function(){
						//获取列表中选中的工程id、编号、类别等信息,先注掉
						/*$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');
						$('.modal-body [name="projName"]').val(pjson ? pjson.projName : '');
						$('.modal-body [name="projNo"]').val(pjson ? pjson.projNo : '');
						$('.modal-body [name="projAddr"]').val(pjson ? pjson.projAddr : '');
						$('.modal-body [name="chargeTime"]').val(format(new Date(), "all"));
	*/
				        //获取初始化函数参数设置
				    	if(typeof init === 'object'){
				    		var initArgs = init.singleArgs ? [init.singleArgs] : init.arrayArgs || [];
				    		init = init.func || "";
				    	}
				    	crunFunc(init, initArgs);
				    	cashFlowInit();
					};
					$("body").cgetPopup({
						title: '收费',
						content: "#" + url,
						accept: callback,
						callback: cinit,
						newpop: 'new',
						atext: '收款',
						ctext: '放弃',
						acolor: '#00d159',
						ccolor: '#ff5b57',
						icon: 'fa-rmb',
						nocache:true
					});
				});
				if(a2.prev(".divider").prevAll(".attach-btn").not(".hidden").length > 0) a2.prev(".divider").removeClass("hidden");
			}
			
			if(o.detail){
				a3.removeClass("hidden").off("click").on("click",function(){
					var url = o.detail.url ? o.detail.url : 'projectDetail/dataPopPage',
					init = o.detail.init ? o.detail.init : '',
					callback = o.detail.callback ? o.detail.callback : popClose,
					tid = o.detail.tableid ? o.detail.tableid : '',
					pjson = tid ? trSData[tid].json : '',
					cinit = function(){
						//获取列表中选中的工程id、编号、类别等信息
						$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');
	
				        //获取初始化函数参数设置
				    	if(typeof init === 'object'){
				    		var initArgs = init.singleArgs ? [init.singleArgs] : init.arrayArgs || [];
				    		init = init.func || "";
				    	}
				    	crunFunc(init, initArgs);
					};
					$("body").cgetPopup({
						title: '工程详情',
						content: "#" + url,
						accept: callback,
						callback: cinit,
						newpop: 'new',
						icon: 'fa-book'
					});
				});
				if(a3.prev(".divider").prevAll(".attach-btn").not(".hidden").length > 0) a3.prev(".divider").removeClass("hidden");
			}
			
			if(o.dispatch){
				a4.removeClass("hidden").off("click").on("click",function(){
					var url = o.dispatch.url ? o.dispatch.url : 'projectPlan/dispatch',
					init = o.dispatch.init ? o.dispatch.init : '',
					callback = o.dispatch.callback ? o.dispatch.callback : popClose,
					tid = o.dispatch.tableid ? o.dispatch.tableid : '',
					pjson = tid ? trSData[tid].json : '',
					cinit = function(){
						//获取列表中选中的工程id、编号、类别等信息
						$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');
	
				        //获取初始化函数参数设置
				    	if(typeof init === 'object'){
				    		var initArgs = init.singleArgs ? [init.singleArgs] : init.arrayArgs || [];
				    		init = init.func || "";
				    	}
				    	crunFunc(init, initArgs);
					};
					$("body").cgetPopup({
						title: '工程任务分派情况',
						content: "#" + url,
						accept: callback,
						callback: cinit,
						newpop: 'new',
						size: 'super',
						icon: 'fa-sliders'
					});
				});
				if(a4.prev(".divider").prevAll(".attach-btn").not(".hidden").length > 0) a4.prev(".divider").removeClass("hidden");
			}
			
			if(o.customer){
				a5.removeClass("hidden").off("click").on("click",function(){
					var url = o.customer.url ? o.customer.url : 'popup/custPop',
					init = o.customer.init ? o.customer.init : '',
					callback = o.customer.callback ? o.customer.callback : popClose,
					tid = o.customer.tableid ? o.customer.tableid : '',
					pjson = tid ? trSData[tid].json : '',
					cinit = function(){
						//获取列表中选中的工程id、编号、类别等信息
						//$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');
	
				        //获取初始化函数参数设置
				    	if(typeof init === 'object'){
				    		var initArgs = init.singleArgs ? [init.singleArgs] : init.arrayArgs || [];
				    		init = init.func || "";
				    	}
				    	crunFunc(init, initArgs);
					};
					$("body").cgetPopup({
						title: '客户管理',
						content: "#" + url,
						atext: '确定',
						ahide:'true',
						chide:'true',
						accept: callback,
						callback: cinit,
						newpop: 'new',
						size: 'super',
						icon: 'fa-user',
						nocache: true
					});
				});
				if(a5.prev(".divider").prevAll(".attach-btn").not(".hidden").length > 0) a5.prev(".divider").removeClass("hidden");
			}
			
			if(o.applydelay){
				a6.removeClass("hidden").off("click").on("click",function(){
					var url = o.applydelay.url ? o.applydelay.url : 'applyDelay/main',
					init = o.applydelay.init ? o.applydelay.init : '',
					callback = o.applydelay.callback ? o.applydelay.callback : '',
					tid = o.applydelay.tableid ? o.applydelay.tableid : '',
					pjson = tid ? trSData[tid].json : '',
					cinit = function(){
						//获取列表中选中的工程id、编号、类别等信息
						$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');
						$('.modal-body [name="projNo"]').val(pjson ? pjson.projNo : '');
						$('.modal-body [name="overDay"]').val(pjson ? pjson.overDay : '');	
						$('.modal-body [name="stepId"]').val($("#curStepId").val());
						//如果是设计出图环节，则原定时长从页面拿
						if($("#curStepId").val()=='1201'){
							$('.modal-body [name="originalPeriod"]').val($("#acquisitionPlDays").val());
						}
				        //获取初始化函数参数设置
				    	if(typeof init === 'object'){
				    		var initArgs = init.singleArgs ? [init.singleArgs] : init.arrayArgs || [];
				    		init = init.func || "";
				    	}
				    	crunFunc(init, initArgs);
				    	handleApplyDelayPop();
					};
					if(!pjson){
			        	if(_inApk){
			        		navigator.notification.alert('抱歉，系统没有找到关联项目，请先选择或新增一个项目！', null, '没有指定关联项目', '确定');
			        	}else{
							$("body").cgetPopup({
								title: '没有指定关联项目',
								content: "抱歉，系统没有找到关联项目，请先选择或新增一个项目！",
								ahide: 'true',
								ctext: '确定',
								newpop: 'new',
								icon: 'fa-warning'
							});
			        	}
						return false;
					};
					$("body").cgetPopup({
						title: '延期申请',
						content: "#" + url + '?stepId=' + $("#curStepId").val()+"&projId="+$("#projId").val(),
						accept: callback,
						ahide: callback ? 'false' : 'true',
						chide: 'true',
						size: 'large',
						callback: cinit,
						newpop: 'new',
						icon: 'fa-upload',
						nocache: true
					});
				});
				if(a6.prev(".divider").prevAll(".attach-btn").not(".hidden").length > 0) a6.prev(".divider").removeClass("hidden");
			}

			if(o.settlementchange){
				a7.removeClass("hidden").off("click").on("click",function(){
					var url = o.settlementchange.url ? o.settlementchange.url : 'settlementChange/main',
					init = o.settlementchange.init ? o.settlementchange.init : '',
					callback = o.settlementchange.callback ? o.settlementchange.callback : '',
					tid = o.settlementchange.tableid ? o.settlementchange.tableid : '',
					pjson = tid ? trSData[tid].json : '',
					cinit = function(){
						//获取列表中选中的工程id、编号、类别等信息
						$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');
						$('.modal-body [name="projNo"]').val(pjson ? pjson.projNo : '');
						$('.modal-body [name="overDay"]').val(pjson ? pjson.overDay : '');	
						$('.modal-body [name="stepId"]').val($("#stepId").val());
	
				        //获取初始化函数参数设置
				    	if(typeof init === 'object'){
				    		var initArgs = init.singleArgs ? [init.singleArgs] : init.arrayArgs || [];
				    		init = init.func || "";
				    	}
				    	crunFunc(init, initArgs);
					};
					if(!pjson){
			        	if(_inApk){
			        		navigator.notification.alert('抱歉，系统没有找到关联项目，请先选择或新增一个项目！', null, '没有指定关联项目', '确定');
			        	}else{
							$("body").cgetPopup({
								title: '没有指定关联项目',
								content: "抱歉，系统没有找到关联项目，请先选择或新增一个项目！",
								ahide: 'true',
								ctext: '确定',
								newpop: 'new',
								icon: 'fa-warning'
							});
			        	}
						return false;
					};
					$("body").cgetPopup({
						title: '变更明细',
						content: "#" + url,
						accept: callback,
						ahide: callback ? 'false' : 'true',
						chide: 'true',
						size: 'large',
						callback: cinit,
						newpop: 'new',
						icon: 'fa-upload',
						nocache: true
					});
				});
				if(a7.prev(".divider").prevAll(".attach-btn").not(".hidden").length > 0) a7.prev(".divider").removeClass("hidden");
			}
			
			if(o.auditInformation){
				a8.removeClass("hidden").off("click").on("click",function(){
					var url = o.auditInformation.url ? o.auditInformation.url : 'projectPlan/auditInformation',
					init = o.auditInformation.init ? o.auditInformation.init : '',
					callback = o.auditInformation.callback ? o.auditInformation.callback : popClose,
					tid = o.auditInformation.tableid ? o.auditInformation.tableid : '',
					pjson = tid ? trSData[tid].json : '';

					var projId = $("#projId").val();
                    var stepId = $(".stepIds").val();
                    if(stepId=='undefined' || stepId==undefined || stepId==''){
                        stepId = $("#stepId").val();
                    }
                    var businessOrderId=$("#businessOrderId").val();

					var cinit = function(){
						//获取列表中选中的工程id、编号、类别等信息
						$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');

						$.ajax({
							type: 'POST',
							url: 'projectPlan/queryAuditInformation',
							//contentType: "application/json;charset=UTF-8",
							data : {"projId" : pjson.projId, "StepId" : stepId,"businessOrderId":businessOrderId},
                            dataType : 'json',
							success: function (data) {
                                 $("#auditInformation").val(data.mrAopinion);
                                 $("#mrCsr").val(data.mrCsrName);
                                 $("#mrTime").val(format(data.mrTime,"all"));
							},
							error: function (jqXHR, textStatus, errorThrown) {
								 console.warn("审核结果查询失败！");
							}
						});
						
						//获取初始化函数参数设置
				    	if(typeof init === 'object'){
				    		var initArgs = init.singleArgs ? [init.singleArgs] : init.arrayArgs || [];
				    		init = init.func || "";
				    	}
				    	crunFunc(init, initArgs);
					};

                    url=url+"?projId="+pjson.projId+"&stepId="+stepId;
					$("body").cgetPopup({
						title: '审核信息',
						content: "#" + url,
						accept: callback,
						callback: cinit,
						newpop: 'new',
						size: 'super',
						icon: 'fa-sliders',
                        chide: true,
						nocache: true
					});
				});
				if(a8.prev(".divider").prevAll(".attach-btn").not(".hidden").length > 0) a8.prev(".divider").removeClass("hidden");
			}
			
			if(o.grade){
				a9.removeClass("hidden").off("click").on("click",function(){
					var url = o.grade.url ? o.grade.url : 'scoreStandard/popScore',
					init = o.grade.init ? o.grade.init : '',
					callback = o.grade.callback ? o.grade.callback : popClose,
					tid = o.grade.tableid ? o.grade.tableid : '',
					pjson = tid ? trSData[tid].json : '',
					cinit = function(){
						//获取列表中选中的工程id、编号、类别等信息
						$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');
						var projId = $("#projId").val();
				        //获取初始化函数参数设置
				    	if(typeof init === 'object'){
				    		var initArgs = init.singleArgs ? [init.singleArgs] : init.arrayArgs || [];
				    		init = init.func || "";
				    	}
				    	crunFunc(init, initArgs);
					};
					$("body").cgetPopup({
						title: '评分',
						content: "#" + url,
						accept: callback,
						ahide: true,
						callback: cinit,
						newpop: 'new',
						size: 'super',
						icon: 'fa-sliders',
						nocache: true
					});
				});
				if(a9.prev(".divider").prevAll(".attach-btn").not(".hidden").length > 0) a9.prev(".divider").removeClass("hidden");
			}
			
			if(o.modify){
				a10.removeClass("hidden").off("click").on("click",function(){
					var url = o.modify.url ? o.modify.url : 'projInfoModify/popModifyPage',
					init = o.modify.init ? o.modify.init : '',
					callback = o.modify.callback ? o.modify.callback : popClose,
					tid = o.modify.tableid ? o.modify.tableid : '',
					pjson = tid ? trSData[tid].json : '',
					cinit = function(){
						//获取列表中选中的工程id、编号、类别等信息
						$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');
						var projId = $("#projId").val();
				        //获取初始化函数参数设置
				    	if(typeof init === 'object'){
				    		var initArgs = init.singleArgs ? [init.singleArgs] : init.arrayArgs || [];
				    		init = init.func || "";
				    	}
				    	crunFunc(init, initArgs);
				    	handleModifyPop();
					};
					if(!pjson){
						
			        	if(_inApk){
			        		navigator.notification.alert('抱歉，系统没有找到关联项目，请先选择或新增一个项目！', null, '没有指定关联项目', '确定');
			        	}else{
							$("body").cgetPopup({
								title: '没有指定关联项目',
								content: "抱歉，系统没有找到关联项目，请先选择或新增一个项目！",
								ahide: 'true',
								ctext: '确定',
								newpop: 'new',
								icon: 'fa-warning'
							});
			        	}
						return false;
					};
					$("body").cgetPopup({
						title: '更正信息',
						content: "#" + url,
						accept: callback,
						ahide: true,
						chide:true,
						callback: cinit,
						newpop: 'new',
						size: 'super',
						icon: 'fa-sliders',
						nocache: true
					});
				});
				if(a10.prev(".divider").prevAll(".attach-btn").not(".hidden").length > 0) a10.prev(".divider").removeClass("hidden");
			}
			if(o.fallBack){
				a11.removeClass("hidden").off("click").on("click",function(){
					var tid=o.fallBack.tableid ? o.fallBack.tableid : '',pjson = tid ? trSData[tid].json : '';
					if(!pjson){
						if(_inApk){
			        		navigator.notification.alert('抱歉，系统没有找到关联项目，请先选择或新增一个项目！', null, '没有指定关联项目', '确定');
			        	}else{
							$("body").cgetPopup({
								title: '没有指定关联项目',
								content: "抱歉，系统没有找到关联项目，请先选择或新增一个项目！",
								ahide: 'true',
								ctext: '确定',
								newpop: 'new',
								icon: 'fa-warning'
							});
			        	}
						return false;
					}
                    var projStatusId=trSData[tid].json.projStatusId;
					var projectType=trSData[tid].json.projectType;
					var contributionMode=trSData[tid].json.contributionMode;
					var corpId=trSData[tid].json.corpId;
					var menuId=getStepId();
					var data={"menuId":menuId,"projectType":projectType,"contributionMode":contributionMode,"corpId":corpId,"projStatusId":projStatusId};
					//查后台
					$.ajax({
						type:'post',
						url:'fallbackApply/queryMenuBackSet',
						//contentType: "application/json;charset=UTF-8",
				        data: data,
				        success:function(data){
				        	if(data=="false"){
				        		alertInfo("该节点未进行回退设置，不允许回退！");
				        	}else{
				        		var url = o.fallBack.url ? o.fallBack.url : 'fallbackApply/main',
			    					init = o.fallBack.init ? o.fallBack.init : '',
			    					callback = o.fallBack.callback ? o.fallBack.callback : popClose,
			    					tid = o.fallBack.tableid ? o.fallBack.tableid : '',
			    					pjson = tid ? trSData[tid].json : '',
			    					cinit = function(){
			    						//获取列表中选中的工程id、编号、类别等信息
			    						$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');
			    						var projId = $("#projId").val();
			    				        //获取初始化函数参数设置
			    				    	if(typeof init === 'object'){
			    				    		var initArgs = init.singleArgs ? [init.singleArgs] : init.arrayArgs || [];
			    				    		init = init.func || "";
			    				    	}
			    				    	crunFunc(init, initArgs);
			    				    	handleFallbackApplyPop();
			    					};
			    					if(!pjson){
			    						
			    			        	if(_inApk){
			    			        		navigator.notification.alert('抱歉，系统没有找到关联项目，请先选择或新增一个项目！', null, '没有指定关联项目', '确定');
			    			        	}else{
			    							$("body").cgetPopup({
			    								title: '没有指定关联项目',
			    								content: "抱歉，系统没有找到关联项目，请先选择或新增一个项目！",
			    								ahide: 'true',
			    								ctext: '确定',
			    								newpop: 'new',
			    								icon: 'fa-warning'
			    							});
			    			        	}
			    						return false;
			    					};
			    					var projectType=trSData[tid].json.projectType;
			    					var contributionMode=trSData[tid].json.contributionMode;
			    					$("body").cgetPopup({
			    						title: '回退信息',
			    						content: "#" + url + '?menuId=' + getStepId()+"&projectType="+projectType+"&contributionMode="+contributionMode+"&corpId="+corpId+"&projStatusId="+projStatusId+"&tableId="+tid,
			    						accept: callback,
			    						ahide: true,
			    						chide:true,
			    						callback: cinit,
			    						newpop: 'new',
			    						size: 'super',
			    						icon: 'fa-share',
			    						nocache: true
			    					});
				        	}
				        },
				        error: function (jqXHR, textStatus, errorThrown) {
				            console.warn("数据保存失败！");
				        }
					})
					
					
					
				});
				if(a11.prev(".divider").prevAll(".attach-btn").not(".hidden").length > 0) a11.prev(".divider").removeClass("hidden");
			}
			if(o.rectify){
				a12.removeClass("hidden").off("click").on("click",function(){
					var url = o.rectify.url ? o.rectify.url : 'rectifyNotice/rectifyPop',
							init = o.rectify.init ? o.rectify.init : '',
							callback = o.rectify.callback ? o.rectify.callback : '',
							tid = o.rectify.tableid ? o.rectify.tableid : '',
							pjson = tid ? trSData[tid].json : '',
							cinit = function(){
								//获取列表中选中的工程id、编号、类别等信息
								$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');
								$('.modal-body [name="projNo"]').val(pjson ? pjson.projNo : '');		
								$('.modal-body [name="stepId"]').val($("#curStepId").val());
								$('.modal-body [name="rectifyNoticeType"]').val($("#rectifyNoticeType").val());
								
						        //获取初始化函数参数设置
						    	if(typeof init === 'object'){
						    		var initArgs = init.singleArgs ? [init.singleArgs] : init.arrayArgs || [];
						    		init = init.func || "";
						    	}
						    	crunFunc(init, initArgs);
						    	handleRectifyPop();
							};
							if(!pjson || $('.data-list-table').find('tr.selected').length<1){
					        	if(_inApk){
					        		navigator.notification.alert('抱歉，系统没有找到关联项目或记录，请先选择一个项目或记录！', null, '没有指定关联项目', '确定');
					        	}else{
									$("body").cgetPopup({
										title: '没有指定关联项目',
										content: "抱歉，系统没有找到关联项目或记录，请先选择一个项目或记录！",
										ahide: 'true',
										ctext: '确定',
										newpop: 'new',
										icon: 'fa-warning'
									});
					        	}
								return false;
							};
							$("body").cgetPopup({
								title: '整改信息',
								content: "#" + url + '?stepId=' + $("#curStepId").val()+"&projId="+$("#projId").val()+"&busOrderId="+$(".busOrderId").val(),
								accept: callback,
								ahide: callback ? 'false' : 'true',
								chide: 'true',
								size: 'large',
								callback: cinit,
								newpop: 'new',
								icon: 'fa-upload',
								nocache: true
							});
				});
				if(a12.prev(".divider").prevAll(".attach-btn").not(".hidden").length > 0) a12.prev(".divider").removeClass("hidden");
			}
			if(o.operateWorkFlow){
				a13.removeClass("hidden").off("click").on("click",function(){
					var url = o.operateWorkFlow.url ? o.operateWorkFlow.url : 'operateWorkFlowRecord/main',
					init = o.operateWorkFlow.init ? o.operateWorkFlow.init : '',
					callback = o.operateWorkFlow.callback ? o.operateWorkFlow.callback : popClose,
					tid = o.operateWorkFlow.tableid ? o.operateWorkFlow.tableid : '',
					pjson = tid ? trSData[tid].json : '',
					cinit = function(){
						$('.modal-body [name="projId"]').val(pjson ? pjson.projId : '');
						handleoperateWorkFlowRecordPop();
					};
					
					if(!pjson){
						
			        	if(_inApk){
			        		navigator.notification.alert('抱歉，系统没有找到关联项目，请先选择或新增一个项目！', null, '没有指定关联项目', '确定');
			        	}else{
							$("body").cgetPopup({
								title: '没有指定关联项目',
								content: "抱歉，系统没有找到关联项目，请先选择或新增一个项目！",
								ahide: 'true',
								ctext: '确定',
								newpop: 'new',
								icon: 'fa-warning'
							});
			        	}
						return false;
					};
					$("body").cgetPopup({
						title: '操作历史',
						content: "#" + url,
						accept: callback,
						callback: cinit,
						newpop: 'new',
						size: 'super',
						icon: 'fa-sliders',
                        chide: true,
						nocache: true
					});
				});
				if(a13.prev(".divider").prevAll(".attach-btn").not(".hidden").length > 0) a13.prev(".divider").removeClass("hidden");
			}
			t.removeClass("hidden");
		},300);
	};
	
	$.fn.removeAttachOper = function(){
		
		"use strict";
		
		var t = this;
		t.removeClass("active");
		setTimeout(function(){
			t.addClass("hidden").find(".attach-btn, .divider").addClass("hidden");
		},300);
		t.find(".attach-btn").off("click");
	};

	/*
	 * 获取Datatable对象全部数据对象
	 */
	$.fn.getDTJson = function(index){
		
		"use strict";
		
		var t = this, json = [];
		if(!$.fn.DataTable.isDataTable("#" + t.attr("id"))) return json;
		json = t.DataTable().ajax.json().data || t.DataTable().ajax.json();
		if(index !== undefined && index !== "" && parseInt(index) > -1) json = json[parseInt(index)] || [];
		return json;
	};

	/*
	 * 快速清空表单
	 * 例
	 * $("myform").formReset();
	 * 传入选择器可排除指定的元素：
	 * $("myform").formReset(".myinput","#thisid","[disabled]","select");
	 * 注：隐藏的元素将不能通过本方法重置和清空
	 */
	$.fn.formReset = function(){
		
		"use strict";
		
		var t = this, 
		as = arguments,
		prevent = ':button, :submit, :reset, :hidden',
		s = t.find(".disabled, [disabled]");
		for(var i=0; i<as.length; i++){
			prevent += ", " + as[i];
		}
		s.removeClass("disabled").removeAttr("disabled");
		var inputs = $(':input','#' + t.attr("id")).not(prevent);
		inputs.not(":checkbox, :radio, select").val('');
		inputs.filter(":checkbox, :radio").removeAttr('checked');
		inputs.filter("select").val('').removeAttr('selected');
		s.addClass("disabled").attr("disabled","disabled");
		//如果包含签名控件，则一同清空
		t.find(".clear-sign").click();
		return t;
	};	

	/*
	 * 获取列表内同行input
	 * 例
	 * $(".myInput").neighborInpput("name1");
	 * 参数：
	 * name: 要获取元素的name值;
	 * 提示：如果name为xxx_name的形式，传入下横线后面的部分即可
	 */
	$.fn.neighborInpput = function(){
		
		"use strict";
		
		var t = this, 
		name = arguments,
		tr = this.closest("tr").is(".child") ? this.closest("tr").prev(".parent") : this.closest("tr"),
		table = this.closest("table"),
		inputs = tr.find(".tdInnerInput input"),
		rinputs = inputs;
		if(name.length > 0) {
			$.each(rinputs, function(k, el){
				var n = $(el).attr("name");
				n = n.indexOf("_") === -1 ? n : n.split("_")[1];
				if($.inArray(n, name) > -1){
					inputs = $('[name="' + $(el).attr("name") + '"]');
				}
			});
		}
		return inputs;
	};
	/*
	 * 表单自动保存方法
	 */
	$.fn.autoSave = function(){
		
	};

	//表单输入框添加title属性
	$.fn.addTitle = function(){
		
		"use strict";
		
		var input = this.is("input") ? this : this.is("div, form, fieldset") ? this.find("input") : [];
		$.each(input, function(i, el){
			var t = $(el);
			if(t.is(":checkbox, :radio, :button, :submit, :reset, :password, :hidden")) return true;
			var v = t.val();
			v ? t.attr("title", v) : t.removeAttr("title");
		});
		return this;
	};

	//图片附件控件获取图片列表
	$.fn.getImagesList = function(options){
		
		"use strict";
		
		if(!$.isPlainObject(options)){
			console.info("getImagesList() -> 参数错误，需要json类型");
			return false;
		}
		var t = this.find("ul.row");
		options.sourceType="1";
		$.ajax({
			type: 'post',
	        url: 'accessoryCollect/queryAccListPhoto',
	        dataType: 'json',
	        data: options,
	        success: function(data) {
	        	if(typeof data !== 'object') {
	        		console.info("getMessages() -> 返回值类型错误, 需要json");
	        		return false;
	        	}
	        	var srcs = fixNull(data).srcs;
	        	if(!srcs.length){
	        		t.html('<p>暂无图片!</p>');
	        		return false;
	        	}
	        	t.html('');
	        	t.children(".has-loaded").remove();
	        	$.each(srcs, function(i, src){
	        		var item = '<li class="attach-image-item col-lg-2 col-xs-4">';
	        		item += '<a href="javascript:;" class="preview-btn" src-orig="attachments/';
	        		item += src;
	        		item += '" src-file-uri="attachments/';
	        		item += src;
	        		item += '"><img src="attachments/';
	        		item += src;
	        		item += '"></a>';
	        		item += '<a href="javascript:;" class="btn btn-danger btn-icon btn-circle btn-sm image-del-btn"><i class="fa fa-times"></i></a>';
	        		item += '</li>';
	        		t.append(item);
	        	});
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            printXHRError("getImagesList", "图片列表获取失败", jqXHR, textStatus, errorThrown);
	        }
		});
	};

	/*
	 * 绑定列表表单输入存入列表dataTable数据缓存
	 * 使用方法： $("#tableid").bindInputsChange(callback);
	 * 参数：callback 回调，回调将在每次有输入框发生改变时触发，并携带下列四个参数
	 * v: 修改后的值
	 * input: 值发生改变的元素的jQuery对象
	 * t: 列表的jquery对象
	 * dt: 列表的DataTable对象
	 */
	$.fn.bindInputsChange = function(callback){
		
		"use strict";
		
		if(!this.is(".dataTable")) return false;
		var t = this;
		t.bindChange(callback);
		t.on("draw.dt", function(){
			t.bindChange(callback);
		});
	};
	$.fn.bindChange = function(callback){
		
		"use strict";
		
		var t = this,
		inputs = t.find("tbody").find(":input").not(":button, :submit, :reset"),
		dt = t.DataTable();
		inputs.off("change.ferrinweb").on("change.ferrinweb", function(){
			
			//console.info("changed");
			var input = $(this),
			v = input.is("select") ? input.find("option:selected").val() : input.is(":checkbox, :radio") ? input.filter(":checked").val() : input.val(),
			td = input.closest("td"),
			tr = input.closest("tr");
			
			dt.cell(td).data(v);
			tr.addClass("inputs-changed");
			
			callback && callback(v, input, t, dt);
			dt.draw(false);
		});
	};
	
	
	/*
	 * 滑动面板方法
	 */
	$.fn.slidePanel = function(){
		var t = this,
		slidelength = t.length,
		shownum = t.filter(':visible').length,
		currentindex = function(){
			return t.filter(':visible').eq(0).index();
		},
		singlewidth = t.filter(':visible').eq(0).outerWidth(true),
		method = {
			toleft: function(callback){
				var cindex = currentindex();
				if(cindex + shownum < slidelength){
					var a = t.eq(cindex),
					b = t.eq(cindex + shownum);
					a.animate({"margin-left": -singlewidth}, 200, function(){
						a.fadeOut(100);
						b.css("display", "none").removeClass("hidden").fadeIn(100, function(){
							callback && callback();
						});
					});
				}
			},
			toright: function(callback){
				var cindex = currentindex();
				if(cindex > 0){
					var a = t.eq(cindex + shownum - 1),
					b = t.eq(cindex - 1);
					a.fadeOut(100, function(){
						b.fadeIn(100, function(){
							b.animate({"margin-left": 0}, 200, function(){
								callback && callback();
							});
						});
					});
				}
			}
//			tofirst: function(callback){
//				var cindex = currentindex();
//				if(cindex > 0){
//					var a = t.eq(cindex + shownum - 1),
//					b = t.eq(cindex - 1);
//					a.fadeOut(100, function(){
//						b.fadeIn(100, function(){
//							b.animate({"margin-left": 0}, 200, function(){
//								callback && callback();
//							});
//						});
//					});
//				}
//			},
//			tolast: function(callback){
//				var cindex = currentindex();
//				if(cindex + shownum < slidelength){
//					var a = t.eq(cindex),
//					b = t.eq(cindex + shownum);
//					a.animate({"margin-left": -singlewidth}, 200, function(){
//						a.fadeOut(100);
//						b.css("display", "none").removeClass("hidden").fadeIn(100, function(){
//							callback && callback();
//						});
//					});
//				}				
//			}
		};
		return method;
	}
	
	
	/*
	 * 获取修改过的行数据，返回json数组
	 * 使用方法： $("#tableid").getInputsData();
	 * 
	 * 参数形式1：不传参
	 * 返回已被inputs-changed样式标记的行的全部数据
	 * 
	 * 参数形式2：fieldname1, fieldname2, fieldname3...
	 * 一系列字段名，用于返回仅包含列出字段的数据
	 * 返回形式：[{"fieldname1": "value1", "fieldname2": "value2", "fieldname3": "value3", ...},{"fieldname1": "value1", "fieldname2": "value2", "fieldname3": "value3", ...}...]
	 * 
	 * 参数形式3：{"a1": "fieldname1", "a2": "fieldname2", "a3": "fieldname3"...}
	 * 一系列键-字段名对，用于将字段名转换为键名，并返回仅包含列出字段的数据
	 * 返回形式：[{"a1": "value1", "a2": "value2", "a3": "value3", ...},{"a1": "value1", "a2": "value2", "a3": "value3", ...}...]
	 * 
	 * 参数形式4：fieldname1, fieldname2, {"a3": "fieldname3"}, fieldname4, {"a5": "fieldname5"}...
	 * 一系列字段名或键-字段名对，用于将字段名转换为键名，并返回仅包含列出字段的数据
	 * 注：该形式下，参数中每个{}中只能包含一个键-字段名对
	 * 返回形式：[{"fieldname1": "value1", "fieldname2": "value2", "a3": "value3", "fieldname4": "value4", "a5": "value5", ...},{"fieldname1": "value1", "fieldname2": "value2", "a3": "value3", "fieldname4": "value4", "a5": "value5", ...}...]
	 */
	$.fn.getInputsData = function(){
		
		"use strict";
		
		var t = this,
		as = arguments,
		data = [];
		if(as.length > 1 || (as.length === 1 && !$.isPlainObject(as[0]))){
			var l = as.length,
			datas = t.DataTable().rows('.inputs-changed').data(),
			dl = datas.length;
			for(var i=0; i<dl; i++){
				var json = {},
				v = datas[i];
				for(var j=0; j<l; j++){
					var a = as[j];
					if($.isPlainObject(a)){
						$.each(a, function(k, n){
							json[k] = v[n] !== undefined ? v[n] : function(){
								console.info("字段名 " + n + " 似乎不存在，请检查参数设置是否正确！");
								return "";
							};
						});
					}else{
						json[a] = v[a] !== undefined ? v[a] : function(){
							console.info("字段名 " + n + " 似乎不存在，请检查参数设置是否正确！");
							return "";
						};
					}
				}
				data.push(json);
			};
			return data;
		}else if(as.length === 1 && $.isPlainObject(as[0])){
			var a = as[0],
			datas = t.DataTable().rows('.inputs-changed').data(),
			dl = datas.length;
			for(var i=0; i<dl; i++){
				var json = {},
				v = datas[i];
				$.each(a, function(k, n){
					json[k] = v[n] !== undefined ? v[n] : function(){
						console.info("字段名 " + n + " 似乎不存在，请检查参数设置是否正确！");
						return "";
					};
				});
				data.push(json);
			};
			return data;
		}else{
			data = t.DataTable().rows('.inputs-changed').data();
			var pdata = [];
			$.each(data, function(i, d){
				pdata.push(d);
			});
			return pdata;
		}
	};
	
	//校验列表内嵌表单
	var valided = true;
	//校验列表内嵌表单，返回true/false
	//使用方法： $("#tableid").checkInputs();
	$.fn.checkInputs = function(){
		
		"use strict";
		
		var t = this,
		dt = t.DataTable(),
		p = Math.ceil(dt.data().length / dt.page.len());
		dt.page(0).draw();
		t.checkPage(0, p);
		return valided;
	};
	$.fn.checkPage = function(i, p){
		
		"use strict";
		
		var t = this,
		id = t.attr("id"),
		dt = t.DataTable(),
		inputs = $("#" + id).closest("form");
		if (!inputs.is_valid()) {
			//console.info("page: " + i + " / " + "校验失败");
			valided = false;
		}else{
			i = i + 1;
			if(i < p){
				dt.page(i).draw('page');
				t.checkPage(i, p);
			}else{
				//console.info("校验通过");
				dt.page(0).draw('page');
				valided = true;
			}
		};
	};
	$.fn.is_valid = function(){
		
		"use strict";
		
		var valid = true;
		if (this.parsley().isValid()) {
			valid = true;
		}else{
			valid = false;
		}
		this.parsley().validate();
		return valid;
	};
	
	/*
	 * 生成计划格子
	 * 调用方式：格子容器jQuery对象.renderPlanGrid(data, frequency, unit, gridselected)
	 * 参数：
	 * data：传入的数据，一般是某员工的已有计划安排
	 * days: 
	 */
	$.fn.renderPlanGrid = function(data, frequency, unit, gridselected){
		this.loadMask();
		data = data || [];
		frequency = frequency || 1;
		unit = unit || 2;
		var t = this,
		units = {
				1: 1,
				2: 9,
				3: 12
			},
			gnum = parseInt(frequency) * units[unit],
			gridbox = $('<ul class="plan-grid-box">'),
			getLevelColor = function(num){
				//九个等级, 八个断点
				var level = [100, 200, 300, 400, 500],
					color = ['#ebfaf9', '#b0ece8', '#80e2dc', '#53d2ca', '#37ccc2', '#0db9ae'];
				for(var i=0, l=level.length; i<l; i++){
					if(num < level[i]) return color[i];
				}
				return color[level.length];
			};
		var formatData = [];
		for(var i = data.length - 1; i >= 0; i--){
			var v = data[i];
			formatData[parseInt(v.planDay)] = v;
		}
		t.data("plan-frequency", gnum).attr("data-plan-frequency", gnum).find(".plan-grid-box").remove();
		setTimeout(function(){
			for(var i = gnum; i >= 0; i--){
				d = formatData[i];
				if(i !== 0){
					var grid = $('<li class="plan-grid">').data("plan-day", i);
					if(d){
						grid.css({"background-color": getLevelColor(d.custs)});
						var griditem = $('<dl></dl>');
						griditem.append('<dd class="grid-decorate">( </dd><dd>' + (d.plans || "-") + '</dd><dd class="grid-decorate">, </dd><dd>' + (d.custs || "-") + '</dd><dd class="grid-decorate"> )</dd>').appendTo(grid);	
					}else{
						grid.css({"background-color": getLevelColor(0)});
					}
					gridi = $('<span class="grid-index">'),
					gridi.text("100"+i).appendTo(grid);			
				}else{
					//var grid = $('<li class="plan-grid operator-profile">');
					//avatar = $('<img src="' + (d && d.profile ? d.profile.avatar : "images/common/profile-avatar.png") + '">');
					//grid.append(avatar);
				}
				grid.prependTo(gridbox);
			}
			t.append(gridbox);
			t.hideMask();
			gridbox.find(".plan-grid:not(.operator-profile)").click(function(){
				$(this).toggleClass("selected").siblings().removeClass("selected");
				var day = gridbox.find(".selected").data("plan-day");
				t.data("selected-day", day || "").attr("data-selected-day", day || "");
				gridselected && gridselected(day);
			});
		},0)
		return t;
	};
	queryCheckRole=function(){
	/*	$.ajax({
			type:'post',
			url:'staff/queryCheckRole',
			contentType: "application/json;charset=UTF-8",
	        data: '',
	        success:function(data){
	        	if(data=="true"){
	        		$(".checkRole").removeClass("hidden");
	        		$(".current-audit").addClass("hidden");
	        	}else{
	        		$(".checkRole").addClass("hidden");
	        	}
	        	console.info('data..'+data);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            console.warn("数据查询失败！");
	        }
		})*/
	};
	

	/**
	 * sql语句中的时间函数 根据不同的数据库转换不同的函数
	 * sqlServer没有编写
	 * 
	 * @param sqlType 数据库类型：mysql,oracle,sqlServer
	 * @param date 要转换的时间
	 * @param formatType 时间格式：默认为 yyyy-mm-dd H:i:s 格式 ,值为 ‘1’ 则为 yyyy-mm-dd格式
	 */
	sqlDateConveter = function(sqlType,date,formatType){

		
		var format = "";
		var str="";
		if(sqlType == 'mysql'){
			str = "str_to_date('"+date+"','";
			if(formatType == 1){
				format = "%Y-%m-%d";
			}else{
				format = "%Y-%m-%d %H:%i:%s";
			}
			
		}else  if(sqlType == 'oracle'){
			str = "to_date("+date+"','";
			if(formatType == 1){
				format = "YYYY-MM-DD";
			}else{
				format = "YYYY-MM-DD HH24:mi:ss";
			}
		}else{
			
		}
		str = str + format + "')";
		return  str;
	}
	/**
	 * 将日期带微妙的截取为到妙
	 */
	mysqlDateTimeStr = function(){
		if($('.datetime-default')){
			var o = $('.datetime-default');
			$.each(o,function(i,e){
				if($(e).val()!=''&&$(e).val().length==21){
					$(e).val($(e).val().substring(0,19));
				}
			})
		}
	};
	
	
	 /**
     * 动态加载遮罩
     * 引用方式: 要添加遮罩的容器jQuery对象.loadMask("");
     * @param {string} title 用来自定义提示文字, 默认为"加载中"
     * @param {number|string} zoom 用来自定义加载图标的尺寸, 取值范围：1-4，默认为3
     * @param {number} opacity 透明度，默认不透明
     * @returns {jQuery}
     */
    $.fn.loadMask = function (title, zoom, opacity) {

        title = title === "" || title === undefined ? "加载中" : title;
        zoom = zoom === "" || zoom === undefined ? "" : " fa-" + zoom + "x";

        var maskhtml = '<div class="mask-html text-center" style="background: rgba(249, 249, 249, ' + (opacity ? opacity : 1) + ')"><div><i class="fa' + zoom + '"></i><br><p class="text-ellipsis">' + title + '</p></div></div>';

        if (this.find(".mask-html").length < 1) {
            this.append(maskhtml);
        }
        this.find(".mask-html").fadeIn(200);

        return this;
    };



    getSignStatusByPostId=function(postId,formId){
  
 
    	if(postId!==""&& postId!==undefined){
    		var postTextId = postId.split(",");
            $(".allText").removeClass("field-editable");					//其他人不可编辑
            $(".allText").addClass("field-not-editable");
    		for(var i=0;i<postTextId.length;i++){
    			var postName=getPostByPostId(postTextId[i]);
    			if(postName){
					 $("."+postName).removeClass("field-not-editable");     //自己对应的文本可编辑,
	                 $("."+postName).addClass("field-editable");
                }
    		}
    	}
    	
    	$("#"+formId+"").toggleEditState(true);                            //form表单可编辑
    	if(postId!==""&& postId!==undefined){
            postTextId = postId.split(",");
            $(".allSign").find(".sign-data-input").toggleSign(false);		//其他人不可编辑
    		for(var i=0;i<postTextId.length;i++){
    			var postName=getPostByPostId(postTextId[i]);
                if(postName!=undefined) {
                	$("." + postName).find(".sign-data-input").toggleSign(true);	//自己对应的签字可编辑
                	if(postTextId[i]==="14"){
                		$(".projectLeader").find(".sign-data-input").toggleSign(true);	//自己对应的签字可编辑
                	}
                }else{
                	if(postTextId[i]==="217"||postTextId[i]==="216" || postTextId[i]==="212"||postTextId[i]==="213"||postTextId[i]==="74"||postTextId[i]==="15"){
                		$(".projectLeader").find(".sign-data-input").toggleSign(true);	//自己对应的签字可编辑
                	}
                }
            	
    		}
    		//管理员可进行所有的签字
    		if(postTextId[1]==="999"){
    			$("#"+formId+"").toggleEditState(true);                        //form表单可编辑
    			$(".allSign").find(".sign-data-input").toggleSign(true);		//其他人不可编辑
    		}
    	}
    	
    };

    getPostByPostId=function(postId){


    	var postArry=[
    	    ["999","admin"],      //管理员
    	    ["43","designer"],      //设计员
            ["53","cuPm"],          //项目经理
            ["54","builder"],       //现场代表
            ["55","suJgj"],         //现场监理
            ["61","construction"],  //施工员
            ["13","suCse"],         //总监
            ["58","safetyOfficer"], //安全员
            ["116","projectLeader"],   //项目负责人
            ["121","ceneralEngineer"], //总工程师
            ["59","qualitativeCheckMember"],//质检员
            ["63","welder"],//焊工
            ["56","technician"],    //质量安全员
            ["75","dataHandle"],    //数据处理员
            ["430","productionStatisticians"],//技术员
            ["126","inspector"],            //验收员
            ["122","processTechnician"],    //工艺技术员
            ["64","testLeader"],    //班组长
            ["124","equipmentTechnician"],  //技术装备员
            ["41","budgetMember"],      //预结算员
            ["214","budgetGroupLeader"],    //预结算组长
            ["15","viceMinister"],      //副部长
            ["14","minister"],          //部长
            ["12","viceGeneralManager"],// 副总经理
            ["46","treasurer"],//财务员
            ["11","generalManager"],//总经理
            ["125","accounting"],//会计
            ["47","cashier"],//出纳员
            ["115","checker"],// 检测员
            ["215","groupLeader"],//  组长
            ["26","contract_manager"],// 合同管理员
            ["79","market"],   //市场营销员
            ["74","assoDirector"], //副主任
            ["93","dataManager"]  //数据管理员
        ];
	   	for (var i = 0; i < postArry.length; i++) {
	   		for (var j = 0; j < postArry[i].length; j++) {
	   			if(postId==postArry[i][0]){
	   				return(postArry[i][1]);
	   			}
	   		}
	   	}
    };

    /**
     *fun 获取服务器时间
     */
    getSysDate = function () {
        var sysDate;
        $.ajax({
            type:'post',
            url:'electrodeRecord/getSysDate',
            contentType: "application/json;charset=UTF-8",
            data: {},
            async : false,
            success:function(data){
                sysDate = format(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("数据查询失败！");
            }
        });
        return sysDate;
    };
    
    /**
     * 验证字符串是否是数字
     * 传递参数 theObj
     */
    checkNumber = function(theObj) {
    	  var reg = /^[0-9]+.?[0-9]*$/;
    	  if (reg.test(theObj)) {
    	    return true;
    	  }
    	  return false;
    	}
    /**
     * 时间戳加天数，返回最后日期 格式：yyyy-MM-dd
     * 传递参数 timestamp
     * 传递参数 days
     */
    addDateFunc = function(timestamp,days) {
    	var addTime = new Number(timestamp)+(new Number(days)*24*60*60*1000);
        return addTime;
    }
    /**
     * 利用正则表达式给字符串去空格
     */
    removeAllSpace = function(str) {
        return str.replace(/\s+/g, "");
    }
    /**
     * 验证日期格式：yyyy-mm-dd格式
     * 传递参数 theObj
     */
    checkDateFormat = function(theObj) {
    	  var reg = /^(\d{4})(-)(\d{2})(-)(\d{2})$/;
    	  if (theObj.match(reg)) {
    	    return true;
    	  }
    	  return false;
    	}

})(jQuery);


//改变推送、保存
changeAText = function(){
  $('.saveButton').text("保存");
  $('.pushButton').text('推送');
};



var Base={
    subimt:function (u,t,d,f) {
        if(typeof(d)== "object"){
            $.ajax({async: false,type:t,url: u,data:d,success: function (data) {f(data);},error: function (jqXHR, textStatus, errorThrown) {}});
        }else {
            $.ajax({async: false,type:t,url: u,contentType: "application/json;charset=UTF-8",dataType:"json",data:d,success: function (data){f(data);},error: function (jqXHR, textStatus, errorThrown) {}});
        }
    },
    subimtReturnHTML:function (u,t,d,f) {
        $.ajax({async: false,type:t,url: u,data:d,dataType: 'html',success: function (data) {f(data);},error: function (jqXHR, textStatus, errorThrown) {}});
    }
};