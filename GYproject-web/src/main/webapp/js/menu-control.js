/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var menu = {}, tnav = [], snav = [], s1nav = [], s2nav = [], pid = 10, defaultHash, firstload = 1,
/*
 *获取主导航 - 脚本开始
 */
loadTopMenu = function(){
    var navBtn = '<li class="menu-control menu-control-left"><a href="#" data-click="prev-menu"><i class="fa fa-angle-left"></i></a></li><li class="menu-control menu-control-right"><a href="#" data-click="next-menu"><i class="fa fa-angle-right"></i></a></li>',
    tmenu = '<ul class="nav">';
    $.each( tnav, function(i, n){
    	if(n.type === admin_menu_id) return true;
        tmenu += '<li';
        tmenu += ' class="' + (n.menuId == 10 ? 'active' : '');
        tmenu += '"><a';
        tmenu += n.url !== null && n.url !== '' && n.url !== '#' ? ' href="' + n.url + '"' : ' href="javascript:;"';
        tmenu += n.menuId !== null && n.menuId !== '' ? ' id="nav_' + n.menuId + '" data-mid="' + n.menuId + '"' : '';
        tmenu += n.menuLevel !== null && n.menuLevel !== '' ? ' data-mlevel="' + n.menuLevel + '"' : '';
        tmenu += ' class="top-menu-link">';
        tmenu += '<i class="fa ';
        tmenu += n.icon;
        tmenu += '"></i><span>';
        tmenu += n.menuName;
        tmenu += '</span></a>';
        tmenu += '</li>';
    });
    //tmenu += navBtn;
    tmenu += "</ul>";
    $("#top-menu").html(tmenu);
    $("#top-menu").menuAdjust();
},
/*
 *获取主导航 - 脚本结束
 */

checkSidebarMenu = function(hash) {
	hash = hash.indexOf('#') === 0 ? hash.substr(1) : hash;
	console.info("flwhash--"+hash);
	$('#sidebar-nav li:first').removeClass("active");
	$('#sidebar-nav').find('[href="#' + hash + '"]').parent('li').addClass('active').parent('ul').addClass('active').parent('li').addClass('active expand')
}

/*
 *更新侧边栏导航和页面标题 - 脚本开始
 */
$(document).on("click", ".top-menu-sub-link", function(e){
	var tid = $(this).parents(".has-sub").children("a").attr("data-mid"),
	tmenu = $("#top-menu li"), whash = window.location.hash;
	$.each( snav, function(i, n){
    	if(n.type === admin_menu_id) return true;
        if(n.menuId == tid) {
        	tid = n.parentId;
        	return false;
        }
    });
	for(var i=0;i<tmenu.length;i++){
		var id = tmenu.eq(i).children("a").attr("data-mid");
		if(id === tid) tmenu.eq(i).addClass("active").siblings().removeClass("active");
	}
    $(this).parent().addClass("active").siblings().removeClass("active");
    if(whash === $(this).attr("href")){
    	//handleLoadPage($(this).attr("href"));
    }
    if($(this).is('[data-mid="1206"],[data-mid="1313"]')){
    	if($(this).is('[data-mid="1206"]') && window.location.hash === "#projectConstructList/main") return;
    	if($(this).is('[data-mid="1313"]') && window.location.hash === "#projectInspectList/main") return;
    	$(this).parent().siblings("li.has-sub").find("ul").css({"display": ''});
    	$(this).parent().siblings(".expand").removeClass("expand");
    	$("#sidebar-nav li, #sidebar-nav a").addClass("disabled");
    	if(window.localStorage){
    		$(this).is('[data-mid="1206"]') ? localStorage.setItem("curProjectConstructSet", '') : localStorage.setItem("curProjectInspectSet", '');   
    	}else{      
    		$(this).is('[data-mid="1206"]') ? curProjectConstructSet = '' : curProjectInspectSet = '';
    	}
    }
});

$(document).on("click", ".top-menu-link", function(e){
	//if($(this).attr("data-mid") === "14") return true;

    $(this).parent().addClass("active").siblings().removeClass("active");
    firstload = 0;
    if($(this).attr("data-mid")){
    	getSidebarMenu($(this).attr("data-mid"), true);
        getPageTitle($(this).attr("data-mid"));
    }
    firstload = 1;
    if($(this).is('[data-mid="12"],[data-mid="13"]')){
    	$("#projectCommonListTable").DataTable().rows('.selected', { page: 'current' }).deselect();
    }
});
/*
 *更新侧边栏导航和页面标题 - 脚本结束
 */

/*
 *更新页面标题 - 脚本开始
 */
var getPageTitle = function(id){
    if( id === "" || id === undefined){
    	id = $(".top-menu .active .top-menu-link").attr("data-mid");
    }
    var title = '<i class="fa hidden ';
    $.each( tnav, function(i, n){
    	if(n.type === admin_menu_id) return true;
        if(n.menuId == id) {
            title += n.navIcon;
            title += ' m-r-10"></i><span id="d_title">';
            title += n.headTitle;
            title += '</span>';
            return false;
        }
    });
    $(".enesys-cloud-page-title").html(title);
},
/*
 *更新页面标题 - 脚本结束
 */

/*
 *获取侧边栏导航 - 脚本开始
 */
getSidebarMenu = function(id, activeDefault){
	console.info(2019999,id);
	$("#sidebar-nav").html("");
	id = id ? id : 10;
	var s_m_id = {};
	//获取二级菜单
    $.each( s1nav, function(i, n){
    	if(n.type === admin_menu_id) return true;
    	if(n.parentId == id){
            var sbarmenu = '';
        	sbarmenu += '<li';
    		sbarmenu += ' class="' + (n.menuId == 10 ? ' active expand' : n.menuId == 1109 || n.menuId == 1206 || n.menuId == 1313 ? ' charge-btn' : '');
    		sbarmenu += (!proSelected(id) ? ' disabled' : '') + '"><a';
    		sbarmenu += n.url !== null && n.url !== '' && n.url !== '#' ? ' href="' + n.url + '"' : ' href="javascript:;"';
    		sbarmenu += n.menuId !== null && n.menuId !== '' ? ' id="nav_' + n.menuId + '" data-mid="' + n.menuId + '"' : '';
    		sbarmenu += n.url === '#' ? ' class="top-menu-sub-link' + (!proSelected(id) ? ' disabled' : '') + '">' : ' data-toggle="ajax" class="top-menu-sub-link">';
    		sbarmenu += '<i class="fa ';
            sbarmenu += n.icon;
            sbarmenu += '"></i><span>';
            sbarmenu += n.menuName;
            sbarmenu += '</span></a>';
            sbarmenu += '</li>';
    		$("#sidebar-nav").append(sbarmenu);
    		s_m_id[i] = n.menuId;
    	}
    });
    //获取三级菜单
    $.each( s_m_id, function(j, k){
	    $.each( s2nav, function(i, n){
	    	if(n.type === admin_menu_id) return true;
	    	if(n.parentId == k){
	    		var pli = $('#sidebar-nav > li > a[data-mid="' + k + '"]');
	    		if(pli.length < 1) return false;
	    		if(pli.nextAll(".sub-menu").length < 1){
	    			pli.parent().addClass("has-sub").append('<ul class="sub-menu"></ul>');
	    			pli.prepend('<b class="caret pull-right"></b>');
	    		}
	            var sbarmenu = '';
	    		sbarmenu += '<li class="';
	            //sbarmenu += n.menuName === '客户业务操作' ? 'active' : '';
	            sbarmenu += '"><a';
	            sbarmenu += n.url !== null && n.url !== '' && n.url !== '#' ? ' href="' + n.url + '"' : ' href="javascript:;"';
	            sbarmenu += n.menuId !== null && n.menuId !== '' ? ' id="nav_' + n.menuId + '" data-mid="' + n.menuId + '"' : '';
	            sbarmenu += ' data-toggle="ajax" class="top-menu-sub-link">';
	            sbarmenu += '<i class="fa ';
	            sbarmenu += n.icon;
	            sbarmenu += '"></i><span>';
	            sbarmenu += n.menuName;
	            sbarmenu += '</span></a>';
	            sbarmenu += '</li>';
	    		
	            pli.nextAll(".sub-menu").append(sbarmenu);
	    	}
	    });
    });
    
    //页面关联操作初始化
    loadAttachPanel();
    
    //加载按钮图标
    var icon = $(".menu-icon"),
    src = './images/common/menu-icon/';
    $.each( icon, function(i, el){
    	if($(el).find("svg").length > 0) return true;
    	var svg = $(el).attr("class").replace(" ","").replace("fa","").replace(" menu-icon","") + ".svg";
    	$.ajax({
    		url: src + svg,
            type: 'GET',
            dataType: 'html',
            success: function (data) {
            	$(el).html(data);
            }
    	});
    });
    
    if(activeDefault){
    	var defaultPage;
    	if($('[data-mid="' + id + '"]').attr("href") && $('[data-mid="' + id + '"]').attr("href") !== "#" && $('[data-mid="' + id + '"]').attr("href") !== "javascript:;"){
    		defaultPage = $('#sidebar-nav [href="' + $('[data-mid="' + id + '"]').attr("href") + '"][data-toggle="ajax"]');
    	}else{
        	if(defaultHash){
        		defaultPage = $('#sidebar-nav [href="' + defaultHash + '"][data-toggle="ajax"]');
        	}else{
            	if($("#sidebar-nav > li:eq(0)").is(".has-sub")){
            		defaultPage = $("#sidebar-nav > li:eq(0) > ul > li:eq(0) > [data-toggle=ajax]");
            	}else{
            		defaultPage = $("#sidebar-nav > li:eq(0) > [data-toggle=ajax]");
            	}
        	}
    	}
		
		if($('[data-mid="' + id + '"]').attr("href") !== "#projectCommonList/main"){
			if((!window.location.hash || window.location.hash === "#") && id === "10") {
				if($('[href="#taskNotice/main"]').length) {
					handleLoadPage("#taskNotice/main");
				}else{
					handleLoadPage($('[data-toggle="ajax"]').eq(0).attr("href"));
					//window.location.hash = $('[data-toggle="ajax"]').eq(0).attr("href");
					//defaultPage = $('#sidebar-nav [href="' + window.location.hash + '"][data-toggle="ajax"]');
				}
			}else if(!window.location.hash || !firstload) {
				window.location.hash = defaultHash ? defaultHash : defaultPage.attr("href");
			}else{
				if(!window.location.hash || !firstload) window.location.hash = defaultHash ? defaultHash : defaultPage.attr("href");
			}
		}else{
			window.location.hash = window.location.hash === "" || window.location.hash === "#" ? "#projectCommonList/main" : window.location.hash;
			defaultPage = $('#sidebar-nav [href="' + window.location.hash + '"][data-toggle="ajax"]');
		}
		console.info(defaultPage.parent()+"==defaultPage");
    	//if(!window.location.hash || !firstload) defaultPage.trigger("click");
        var cli = defaultPage.parent();
        cli.addClass("active").siblings().removeClass("active");
        cli.parents("li").addClass("active").siblings().removeClass("active");
        $('[data-mid="' + id + '"]').parent().addClass("active").siblings("li").removeClass("active");
    }
    defaultHash = '';
},
/*
 *获取侧边栏导航 - 脚本结束
 */

/*
 * 判断施工报验是否已选择项目
 */
proSelected = function(id){
	if(id != "12" && id != "13") return true;
	var pjson = {},
	projType = id == "12" ? "curProjectConstructSet" : "curProjectInspectSet";
	if(window.localStorage){
		pjson = localStorage.getItem(projType) ? JSON.parse(localStorage.getItem(projType)) : false;  
	}else{
		if(id == "12"){
			pjson = curProjectConstructSet ? curProjectConstructSet : false;
		}else{
			pjson = curProjectInspectSet ? curProjectInspectSet : false;
		}
	}
	return pjson;
},

issmactive = function(h){
    var ismatch = false;
    $.each( menu.menus, function(i, n){
    	if(n.type === admin_menu_id) return true;
        if(n.navHref === h){
            if(n.menuId !== null && n.menuId !== ''){
            	ismatch = n.parentNavId !== null && n.parentNavId !== '' ? n.parentNavId : n.menuId;
            }
            return false;
        }
    });
    return ismatch;
},

// 菜单排序
navSort = function(as){
	Array.prototype.getMin = function(){
		var a = this,
		min = {};
		min.item = a[0];
		min.key = 0;
		if(!this.length) return;
		$.each(a, function(k, v){
			if(min.item.sortNo > v.sortNo){
				min.item = v;
				min.key = k;
			}
		});
		return min;
	};
	var temp = as,
	temp2 = as,
	v = [];
	$.each(temp, function(i, n){
		var item = temp2.getMin();
		v.push(item.item);
		temp2.splice(item.key, 1);
	});
	return v;
},

getMenu = function () {
    "use strict";
    return {
        //main function
        init: function () {
        	$.ajax({
		        type: 'POST',
		        url: "menu/info",
		        success: function(data) {
		        	var bool = $.isPlainObject(data); //判断是否为对象  返回Boolean
		        	// 非对象不用进行转换
		        	 if (!bool) { 
		        		data=JSON.parse(data); 
		        	 }
		        	menu = {
		        	    menus: fixNull(data.menus),
		        	    btns:data.btns
		        	};
		        	if(menu.menus.length < 1){
		        		window.location.href = location.protocol + "//" + location.host + "/login.html";
		        		return;
		        	}
		            $.each( menu.menus, function(i, n){
		            	if(n.type === "9" || n.isValid !== "1") return true;
		            	if(n.menuLevel === "1") tnav.push(n);//一级菜单
		            	if(n.menuLevel !== "1") snav.push(n);//二级、三级菜单
		            	if(n.menuLevel === "2") s1nav.push(n);//二级菜单
		            	if(n.menuLevel === "3") s2nav.push(n);//三级菜单
		            });
		            tnav = navSort(tnav);
		            snav = navSort(snav);
		            s1nav = navSort(s1nav);
		            s2nav = navSort(s2nav);
		            if(window.location.hash && window.location.hash !== "#"){
		            	$.each( menu.menus, function(i, n){
		            		if(n.url === window.location.hash) {
		            			if(n.menuLevel === "1"){
			            			pid = n.menuId;
		            			}
		            			if(n.menuLevel === "2"){
			            			pid = n.parentId;
		            			}
		            			if(n.menuLevel === "3"){
		    		            	$.each(s1nav, function(k,v){
		    		            		if(n.parentId === v.menuId) {
		    		            			pid = v.parentId;
		    			            		return false;
		    		            		}
		    		            	});
		            			}
		            			
			            		defaultHash = n.url;
		            		}
			            });
		            }else{
		            	pid = tnav[0] ? tnav[0].menuId : s1nav[0] ? s1nav[0].menuId : '';//一级菜单，如果该一级菜单下有二级菜单，默认选中二级菜单的排序优先的第一个，如果该二级菜单下有三级菜单，默认选中该三级菜单的排序优先的第一个
		            	if(s1nav){
		            		//console.info(s1nav[0].url+"s1nav[0].url" +"--s1nav[0].menuId=="+s1nav[0].menuId);
			            	if((s1nav[0].url === "" || s1nav[0].url === "#") && s2nav){
				            	for(var i=0; i<s2nav.length; i++){
				            		if(s2nav[i].parentId === s1nav[0].menuId){
						            	defaultHash = s2nav[i].url;
						            	break;
				            		}
				            	}
				            }else{
				            	defaultHash = s1nav[0].url;
				            }
		            	}
		            	$.each(snav, function(i,n){
		            		if(n.url === "#taskNotice/main") {
		            			defaultHash = "#taskNotice/main";
			            		return false;
		            		}
		            	});
		            }
		        	loadTopMenu();
		        	getSidebarMenu(pid, true);
		            getPageTitle(pid);
		        }
		    });
        }
    };
}();
