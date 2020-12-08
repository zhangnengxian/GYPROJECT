/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var navs=[], menu = {};

/*
 *更新侧边栏导航和页面标题 - 脚本开始
 */
$(document).on("click", ".top-menu-sub-link", function(e){
	var tid = $(this).parents(".has-sub").children("a").attr("data-mid"),
	tmenu = $("#top-menu li"), whash = window.location.hash;
	$.each( menu.menus, function(i, n){
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
    	handleLoadPage($(this).attr("href"));
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
        id = $("#sidebar-nav .active .top-menu-sub-link").attr("data-mid");
    }
    id = $(".top-menu .active .top-menu-link").attr("data-mid");
    var title = '<i class="fa hidden ';
    $.each( menu.menus, function(i, n){
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
	$("#sidebar-nav").html("");
	id = id ? id : 0;
	var s_m_id = {};
	//获取二级菜单
    $.each( menu.menus, function(i, n){
    	if(n.type !== admin_menu_id) return true;
    	if(n.parentId == id){
            var sbarmenu = '';
        	sbarmenu += '<li';
    		sbarmenu += ' class="' + (n.menuId == 90 ? 'active expand' : '');
    		sbarmenu += '"><a';
    		sbarmenu += n.url !== null && n.url !== '' && n.url !== '#' ? ' href="' + n.url + '"' : ' href="javascript:;"';
    		sbarmenu += n.menuId !== null && n.menuId !== '' ? ' id="nav_' + n.menuId + '" data-mid="' + n.menuId + '"' : '';
    		sbarmenu += n.url === '#' ? ' class="top-menu-sub-link">' : ' data-toggle="ajax" class="top-menu-sub-link">';
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
	if(jsonLength(s_m_id) < 1){
		window.location.href = location.protocol + "//" + location.host + "/admin-login.html";
		return;
	}
    //获取三级菜单
    $.each( s_m_id, function(j, k){
	    $.each( menu.menus, function(i, n){
	    	if(n.type !== admin_menu_id) return true;
	    	if(n.parentId == k){
	    		var pli = $('#sidebar-nav > li > a[data-mid="' + k + '"]');
	    		if(pli.length < 1) return false;
	    		if(pli.nextAll(".sub-menu").length < 1){
	    			pli.parent().addClass("has-sub").append('<ul class="sub-menu"></ul>');
	    			pli.prepend('<b class="caret pull-right"></b>');
	    		}
	
	            var sbarmenu = '';
	    		sbarmenu += '<li class="';
	            sbarmenu += n.menuName === '客户业务操作' ? 'active"' : '"';
	            sbarmenu += '><a';
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
    	
    	if($("#sidebar-nav > li:eq(0)").is(".has-sub")){
    		defaultPage = $("#sidebar-nav > li:eq(0) > ul > li:eq(0) > [data-toggle=ajax]");
    	}else{
    		defaultPage = $("#sidebar-nav > li:eq(0) > [data-toggle=ajax]");
    	}
    	if( id === "90" ) {
    		handleLoadPage(defaultPage.attr("href"));
            var cli = defaultPage.parent();
            cli.addClass("active").siblings().removeClass("active");
            cli.parents("li").addClass("active").siblings().removeClass("active");
    	}
    }
},
/*
 *获取侧边栏导航 - 脚本结束
 */

sidebarmenu = function(){
    "use strict";
    return {
        //main function
        init: function () {
            var shash = window.location.hash ? window.location.hash : function(){
            	var v = "#dept/main";
            	if(!$('[href="#dept/main"]').length){
            		if($('.sidebar [data-toggle="ajax"]').length) v = $('.sidebar [data-toggle="ajax"]').eq(0).attr("href");
            	}
            	return v;
            }(), pid = 0;
            console.info(shash);
            $.each( menu.menus, function(i, n){
            	if(n.type !== admin_menu_id) return true;
            	if(n.url === shash){
            		if(n.menuLevel === "3"){
            			$.each( menu.menus, function(j, m){
            		    	if(m.type !== admin_menu_id) return true;
            				if(n.parentId === m.menuId){
                    			$.each( menu.menus, function(h, k){
                    		    	if(k.type !== admin_menu_id) return true;
                    				if(m.parentId === k.menuId){
		            		            getSidebarMenu(k.menuId);
		            		            var currentSubMenu = $('.sidebar [href="' + shash + '"][data-toggle=ajax]'),
		            		            cli = currentSubMenu.parent();
		            		            cli.addClass("active").siblings().removeClass("active");
		            		            cli.parents("li").addClass("active").siblings().removeClass("active");
		            		            $('.top-menu-link[data-mid="' + getTopMenuId(currentSubMenu.attr("data-mid")) + '"]').parent().addClass("active").siblings("li").removeClass("active");
		            		            return false;
                    				}
                    			});
                				return false;
            				}
            			});
            		}

            		if(n.menuLevel === "2"){
            			$.each( menu.menus, function(j, m){
            		    	if(m.type !== admin_menu_id) return true;
            				if(n.parentId === m.menuId){
            		            getSidebarMenu(m.menuId);
            		            var currentSubMenu = $('.sidebar [href="' + shash + '"][data-toggle=ajax]'),
            		            cli = currentSubMenu.parent();
            		            cli.addClass("active").siblings().removeClass("active");
            		            cli.parents("li").addClass("active").siblings().removeClass("active");
            		            $('.top-menu-link[data-mid="' + getTopMenuId(currentSubMenu.attr("data-mid")) + '"]').parent().addClass("active").siblings("li").removeClass("active");
            		            return false;
            				}
            			});
            		}
            		
            		if(n.menuLevel === "1"){
            			getSidebarMenu(n.menuId);
    		            var currentSubMenu = $('[data-mid="' + n.menuId + '"]'),
    		            cli = currentSubMenu.parent();
    		            cli.addClass("active").siblings().removeClass("active");
    		            cli.parents("li").addClass("active").siblings().removeClass("active");
    		            $('.top-menu-link[data-mid="' + getTopMenuId(currentSubMenu.attr("data-mid")) + '"]').parent().addClass("active").siblings("li").removeClass("active");
    		        }
            		return false;
            	}
            });
        }
    };
}(),

getTopMenuId = function(sub_menu_id){
	var top_menu = "0";
    $.each( menu.menus, function(i, n){
    	if(n.type !== admin_menu_id) return true;
        if(n.menuId == sub_menu_id){
        	if(n.menuLevel == "1") return false;
            if(n.parentId !== null && n.parentId !== ''){
            	if(n.menuLevel == "2"){
            		top_menu = n.parentId;
            	}
            	if(n.menuLevel == "3"){
            		$.each( menu.menus, function(j, m){
            	    	if(m.type !== admin_menu_id) return true;
            	        if(m.menuId == n.parentId){
                    		top_menu = m.parentId;
                    		return false;
            	        }
            	    });
            	}
            }
            return false;
        }
    });
	return top_menu;
},

issmactive = function(h){
    var ismatch = false;
    $.each( menu.menus, function(i, n){
    	if(n.type !== admin_menu_id) return true;
        if(n.navHref === h){
            if(n.menuId !== null && n.menuId !== ''){
            	ismatch = n.parentNavId !== null && n.parentNavId !== '' ? n.parentNavId : n.menuId;
            }
            return false;
        }
    });
    return ismatch;
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
		        	menu=JSON.parse(data);
		        	//navs=data;
		            /*	menu =data;*/
		        	/*{
		        	    menus : eval(navs.menus)
		        	};*/
		        	if(menu.menus.length < 1){
		        		window.location.href = location.protocol + "//" + location.host + "/admin-login.html";
		        		return;
		        	}
		        	
		            getSidebarMenu("90", location.hash === '');
		            sidebarmenu.init();
		            getPageTitle(90);
		        }
		    });
        }
    };
}();
