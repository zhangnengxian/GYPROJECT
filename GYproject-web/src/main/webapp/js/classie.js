/*!
 * classie - class helper functions
 * from bonzo https://github.com/ded/bonzo
 * 
 * classie.has( elem, 'my-class' ) -> true/false
 * classie.add( elem, 'my-new-class' )
 * classie.remove( elem, 'my-unwanted-class' )
 * classie.toggle( elem, 'my-class' )
 */

/*jshint browser: true, strict: true, undef: true */

( function( window ) {

'use strict';

// class helper functions from bonzo https://github.com/ded/bonzo

function classReg( className ) {
  return new RegExp("(^|\\s+)" + className + "(\\s+|$)");
}

// classList support for class management
// altho to be fair, the api sucks because it won't accept multiple classes at once
var hasClass, addClass, removeClass;

if ( 'classList' in document.documentElement ) {
  hasClass = function( elem, c ) {
    return elem.classList.contains( c );
  };
  addClass = function( elem, c ) {
    elem.classList.add( c );
  };
  removeClass = function( elem, c ) {
    elem.classList.remove( c );
  };
}
else {
  hasClass = function( elem, c ) {
    return classReg( c ).test( elem.className );
  };
  addClass = function( elem, c ) {
    if ( !hasClass( elem, c ) ) {
      elem.className = elem.className + ' ' + c;
    }
  };
  removeClass = function( elem, c ) {
    elem.className = elem.className.replace( classReg( c ), ' ' );
  };
}

function toggleClass( elem, c ) {
  var fn = hasClass( elem, c ) ? removeClass : addClass;
  fn( elem, c );
}

window.classie = {
  // full names
  hasClass: hasClass,
  addClass: addClass,
  removeClass: removeClass,
  toggleClass: toggleClass,
  // short names
  has: hasClass,
  add: addClass,
  remove: removeClass,
  toggle: toggleClass
};

})( window );


//Dropdown Menu
var dropdown = document.querySelectorAll('.dropdown');
var dropdownArray = Array.prototype.slice.call(dropdown, 0);
dropdownArray
		.forEach(function(el) {
			var button = el.querySelector('a[data-toggle="dropdown"]'), menu = el
					.querySelector('.dropdown-menu'), arrow = button
					.querySelector('i.icon-arrow-menu');

			button.onclick = function(event) {
				if (!menu.hasClass('show')) {
					menu.classList.add('show');
					menu.classList.remove('hide');
					arrow.classList.add('open');
					arrow.classList.remove('close');
					event.preventDefault();
				} else {
					menu.classList.remove('show');
					menu.classList.add('hide');
					arrow.classList.remove('open');
					arrow.classList.add('close');
					event.preventDefault();
				}
			};
		})

Element.prototype.hasClass = function(className) {
	return this.className
			&& new RegExp("(^|\\s)" + className + "(\\s|$)")
					.test(this.className);
};

//菜单特效
$(document).ready(function() {
	//分辨率超过1366+120时，自动显示
	adjustMenu();
});

var menuLeft = document.getElementById('cbp-spmenu-s1'), leftNenu = document
.getElementById('cbp-spmenu-s1'), body = document.body;

function disableOther(button) {
if (button !== 'showLeft') {
classie.toggle(showLeft, 'disabled');
}
if (button !== 'showLeftPush') {
classie.toggle(showLeftPush, 'disabled');
}
}

var isShowMenu = false;
function mouseMove(ev) {
ev = ev || window.event;
var mousePos = mouseCoords(ev);
//alert(ev.pageX); 
if (mousePos.x <= 5 & !isShowMenu) {
classie.toggle(menuLeft, 'cbp-spmenu-open');
isShowMenu = true;
} else if (isShowMenu & mousePos.x > leftNenu.clientWidth) {
classie.toggle(menuLeft, 'cbp-spmenu-open');
isShowMenu = false;
}
}

function mouseCoords(ev) {
if (ev.pageX || ev.pageY) {
return {
	x : ev.pageX,
	y : ev.pageY
};
}
return {
x : ev.clientX + document.body.scrollLeft
		- document.body.clientLeft,
y : ev.clientY + document.body.scrollTop
		- document.body.clientTop
};
}
document.onmousemove = mouseMove;
function adjustMenu() {
if (window.screen.width > 1600) {
var offsetLeftContainer = $("#bodycontainer")[0].offsetLeft;
var offsetLeftMenu = $("#cbp-spmenu-s1")[0].offsetWidth;
$("#cbp-spmenu-s1")[0].style.left = ''
		+ (offsetLeftContainer - offsetLeftMenu) + "px";
//自动显示
if (!isShowMenu) {
	classie.toggle(menuLeft, 'cbp-spmenu-open');
	isShowMenu = true;
}
//并且移除鼠标浮动事件
document.onmousemove = null;
} else {
if (isShowMenu) {
	$("#cbp-spmenu-s1")[0].style.left = '';
	classie.toggle(menuLeft, 'cbp-spmenu-open');
	isShowMenu = false;
}
	document.onmousemove = mouseMove;
}
}
$(window).resize(function() {
adjustMenu();
});