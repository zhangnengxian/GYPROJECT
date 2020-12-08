/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var addrCodeTable;
var addrCodesearchData={};
var addrCodeDesProvince="";//省份
var addrCodeDesCity="";//城市
var addrCodeDesTown="";//区县
var addrCodeDesStreet="";//街道
var addrCodeDesSubdistrict="";//小区
var addrCodeDescFinal="";//拼接后的地址编码
var addrCodeIndex;

var provinceLevel = 1;
var cityLevel = 2;
var townLevel = 3;
var streetLevel = 4;
var subdistrictLevel = 5;
var buildingLevel = 6;

var currCodeLevel;
var currCodeId;

var handleAddressCode = function() {
	"use strict";
    
    if ($('#addrCodeTable').length !== 0) {
    	addrCodeTable= $('#addrCodeTable').DataTable({
        	 language: {
                 url: 'js/dt-chinese.json'
             },
            lengthMenu: [5],
            dom: 'Bfrtip',
            buttons: [
                { text: '增加',  className: 'btn-sm addcode business-tool-btn' },
                { text: '修改', className: 'btn-sm editcode business-tool-btn' },
                { text: '批调',  className: 'btn-sm codebatedit business-tool-btn' }
               
            ],				
            serverSide: true,
            responsive: true,
            ajax: {
            	url: 'addrCode/list',
            	type:'post',
            	data: function(d){
            		$.each(addrCodesearchData,function(i,k){
            			d[i] = k;
            		});
            	},
            	dataSrc: 'data'
            },
            select: true,
            searching: false,
            columns: [
	  			{"data":"addrCodeId"},
				{"data":"addrCodeDes"},
				{"data":"addrCodeLevel",className:"none never"}
			],
			columnDefs: [
                { "name": "addrCodeId",   "targets": 0 , "width": "20%"},
                { "name": "addrCodeDes",  "targets": 1 },
                { "name": "addrCodeLevel",  "targets": 2 }
            ],
			order: [[ 0, "desc" ]]
        });
    }
};

/*
 * 右侧操作区表单展示（元素和数据的动态展示，包括是否展示元素，是否展示数据，数据是否可编辑）
 */
function showAddrCodeDetail(row, formName, isAdd, canEdit, isBat){
	var table = $('#addrCodeTable').DataTable();
	if(table.rows().data().length == 0 && !isAdd) {//无数据操作
		return;
    }
	
	if(!row) {
		row = 0;
	}
	
	var codeLevel;
	if(table.rows().data().length == 0 && isAdd) {//无数据新增
		codeLevel = currCodeLevel + 1;
    }else {
    	var selectedRow = table.row('.selected');
        codeLevel = parseInt(selectedRow.column('addrCodeLevel:name').data()[row]);
    }
	
	var addrCodeForm = $("#" + formName);
	var tar = addrCodeForm.children();
	codeLevelForEach = !isAdd ? codeLevel + 1 : codeLevel;//新增时不展示地址编码id行，其他情况多一行用于展示地址编码id
	for(var i=0; i<codeLevelForEach; i++){
		tar.eq(i).removeClass("hidden");
	}
	
	var inputs = $("#" + formName + " input");
	var length = !canEdit ? codeLevel + 1 : codeLevel;
	length = !isAdd ? length : length - 1;
	length = !isBat ? length : length + 1;
	inputs.eq(length).removeAttr("disabled");
	
	var codeDesc = "";
	if(!isAdd) {
		$('#addrCode_' + formName).val(selectedRow.column('addrCodeId:name').data()[row]);
		codeDesc = selectedRow.column('addrCodeDes:name').data()[row];
		codeDesc = codeDesc.substring(addrCodeDescFinal.length, codeDesc.length);
	}
	
	//操作区表单回显
	setCodeDesc(codeLevel, codeDesc, formName);
	addrCodeForm.styleFit();
	
}

/*
 * 操作区表单回显
 */
function setCodeDesc(codeLevel, codeDesc, formName) {
	if(codeLevel > provinceLevel) {
		$('#province_' + formName).val(addrCodeDesProvince);
	}else if(codeLevel == provinceLevel) {
		$('#province_' + formName).val(codeDesc);
	}
	
	if(codeLevel > cityLevel) {
		$('#city_' + formName).val(addrCodeDesCity);
	}else if(codeLevel == cityLevel) {
		$('#city_' + formName).val(codeDesc);
	}
	
	if(codeLevel > townLevel) {
		$('#town_' + formName).val(addrCodeDesTown);
	}else if(codeLevel == townLevel) {
		$('#town_' + formName).val(codeDesc);
	}
	
	if(codeLevel > streetLevel) {
		$('#street_' + formName).val(addrCodeDesStreet);
	}else if(codeLevel == streetLevel) {
		$('#street_' + formName).val(codeDesc);
	}
	
	if(codeLevel > subdistrictLevel) {
		$('#subdistrict_' + formName).val(addrCodeDesSubdistrict);
	}else if(codeLevel == subdistrictLevel) {
		$('#subdistrict_' + formName).val(codeDesc);
	}
	
	if(codeLevel == buildingLevel) {
		$('#building_' + formName).val(codeDesc);
	}
}


var addressCode = function () {
	"use strict";
    return {
        //main function
        init: function (province, city) {
        	addrCodeDesProvince = province;//省份
        	addrCodeDesCity = city;//城市
        	addrCodeDescFinal = addrCodeDesProvince + addrCodeDesCity;
        	currCodeLevel = cityLevel;
        	
        	handleAddressCode();
        	$('#addrCodeTable').on( 'init.dt',function(){
        		$("#addCodeSelect").prependTo("#mytable_wrapper");
       			$("#province").linkAddrCodeSelect("", provinceLevel);
       			$("#province").change(function(){
       				selectChange($(this), provinceLevel);
       			 });
       			$("#city").linkAddrCodeSelect("", cityLevel);
       			$("#city").change(function(){
       				selectChange($(this), cityLevel);
       			 });
       			$("#town").linkAddrCodeSelect("", townLevel);
       			$("#town").change(function(){
       				selectChange($(this), townLevel);
       			 });
       			$("#street").linkAddrCodeSelect("", streetLevel);
       			$("#street").change(function(){
       				selectChange($(this), streetLevel);
       			 });
       			$("#subdistrict").linkAddrCodeSelect("", subdistrictLevel);
       			$("#subdistrict").change(function(){
       				selectChange($(this), subdistrictLevel);
       			 });
       			$("#addrform").styleFit();
       			
       			$(".addcode").attr("data-c","#addrCode/oper?formid=addCodeForm");
       			$(".editcode").attr("data-c","#addrCode/oper?formid=editCodeForm");
       			$(".codebatedit").attr("data-c","#addrCode/oper?formid=batEditCodeForm");
       			
       			$("#panel_body_box").cgetPart($(".editcode"), "&isInit=true", function() {
       				showAddrCodeDetail(addrCodeIndex, "editCodeForm", false, false, false);
       			});
       			$('#addrCodeTable').hideMask();
       			bindTableSelect();
            });
        	
        }
    };
}();

/*
 * 绑定表格单行选中事件
 */
function bindTableSelect() {
	 var table = $('#addrCodeTable').DataTable();
	 table.on( 'select', function ( e, dt, type, indexes ) {
		 $("#panel_body_box").cgetPart($(".editcode"), "&isInit=true", function() {
				showAddrCodeDetail(addrCodeIndex, "editCodeForm", false, false, false);
			});
		 addrCodeIndex = indexes;
	 });
}

/*
 * 下拉菜单事件绑定，用于地址编码描述拼接和各级地址编码描述缓存
 */
function selectChange(obj, level) {
	currCodeLevel = level;
	var selectObj = obj.find("option:selected");
	addrCodesearchData = {};
	addrCodesearchData.codeLevel = level + 1;
	addrCodesearchData.codeVal = selectObj.val();
	currCodeId = selectObj.val();
	var selectText = selectObj.text();
	
	switch(level) {
		case provinceLevel: 
			addrCodeDesProvince = selectText;
			addrCodeDescFinal = addrCodeDesProvince;
			addrCodeDesCity = "";
			addrCodeDesTown = "";
			addrCodeDesStreet = "";
			addrCodeDesSubdistrict = "";
			break;
		case cityLevel: 
			addrCodeDesCity = selectText; 
			addrCodeDescFinal = addrCodeDesProvince + addrCodeDesCity;
			addrCodeDesTown = "";
			addrCodeDesStreet = "";
			addrCodeDesSubdistrict = "";
			break;
		case townLevel: 
			addrCodeDesTown = selectText; 
			addrCodeDescFinal = addrCodeDesProvince + addrCodeDesCity + addrCodeDesTown;
			addrCodeDesStreet = "";
			addrCodeDesSubdistrict = "";
			break;
		case streetLevel: 
			addrCodeDesStreet = selectText; 
			addrCodeDescFinal = addrCodeDesProvince + addrCodeDesCity + addrCodeDesTown + addrCodeDesStreet;
			addrCodeDesSubdistrict = "";
			break;
		case subdistrictLevel: 
			addrCodeDesSubdistrict = selectText;
			addrCodeDescFinal = addrCodeDesProvince + addrCodeDesCity + addrCodeDesTown + addrCodeDesStreet + addrCodeDesSubdistrict;
			break;
	}
	
	addrCodesearchData.codeDes = addrCodeDescFinal;
	$("#addrCodeTable").cgetData();
}

/*
 * 放弃按钮点击事件，右侧操作区回显列表区选中行数据
 */
$(document).on("click",".addrCodeCancelBtn",function(){
    $("#panel_body_box").cgetPart($(".editcode"), "&isInit=true", function() {
		showAddrCodeDetail(addrCodeIndex, "editCodeForm", false, false, false);
	});
});

/*
 * 表格加载完成后，数据为空，则右侧操作区置空
 */
$('#addrCodeTable').on( 'draw.dt', function () {
	var dataRows = $(this).DataTable().rows().data().length;
    if(dataRows == 0) {
    	$("#panel_body_box").cgetPart($(".editcode"), "&isInit=true", "");
    }else {
		$(this).bindDTSelected("",true);
	}
});

/*
 * 下拉初始化
 */
(function(){
	$.fn.initAddrCodeSelect = function(){
		var t = this;
		var loading = '<span class="sloading">查询中...</span>';
		var failedload = '<span class="sloading">查询失败请重试</span>';
		
		//初始化一级选单
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
				
				$(".default-select2").select2();
				t.prev(".sloading").fadeOut(function(){
					t.prev(".sloading").remove();
				});
            },
            error: function (jqXHR, textStatus, errorThrown) {
            	t.prev(".sloading").remove();
            	t.before(failedload);
            }
        });
		
//		return t;
	};
})(jQuery);

/*
 * 保存按钮点击事件，右侧操作区回显列表区选中行数据（默认第一行，因为列表区数据需要刷新）
 */
$(document).on("click",".addrCodeSaveBtn",function(){
	 var formName = $('#addrCodeFormName').val();
	 var formParam = $("#" + formName).serialize();//序列化表格内容为字符串  
	 
	 var url = "addrCode/";
	 if(formName == 'addCodeForm') {
		 url += "insertAddrCode";
		 formParam += "&codeLevel=" + (currCodeLevel+1) + "&parentCodeId=" + currCodeId;
	 }else if(formName == 'editCodeForm') {
		 url += "modifyAddrCode";
	 }else if(formName == 'batEditCodeForm') {
		 url += "batchModifyAddrCode";
		 formParam += "&codeLevel=" + (currCodeLevel+1);
	 }
	 
	 $.ajax({  
       type:'post',      
       url:url,  
       data:formParam,  
       cache:false,  
       dataType:'json',  
       success:function(data){
    	   if(data.success) {
    		   addrCodesearchData = {};
    		   addrCodesearchData.codeLevel = currCodeLevel + 1;
    		   addrCodesearchData.codeVal = currCodeId;
        	   addrCodesearchData.codeDes = addrCodeDescFinal;
        	   $("#addrCodeTable").cgetData();
        	   var popoptions = {
  					title: '提示',
  					content: '操作成功!',
  					accept: myAcceptAddrCode,
  					chide:true
      		   };
        	   $("body").cgetPopup(popoptions);
    	   }
       }  
   });  
});

function myAcceptAddrCode() {
	
}

/*
 * 批调并入下拉选择事件
 */
$(document).on("change","#combine_batEditCodeForm",function(){
	var selectObj = $(this).find("option:selected");
	if(selectObj.text() == addrCodeDesTown) {
		var popoptions = {
			title: '提示',
			content: '不支持调入当前区县！',
			accept: myAcceptAddrCode,
			chide:true
	    };
	    $("body").cgetPopup(popoptions);
		selectObj.removeAttr("selected");
		$(".default-select2").select2();
	}
});

/*
 * 下拉联动（公共方法细节修改版，地址编码私有方法）
 */
(function(){
	$.fn.linkAddrCodeSelect = function(callback, level){
		var t = this;
		var sub = t.attr("data-sub");
		var s = $("#" + sub);
		if(s.length < 1) return false;
		
		var loading = '<span class="sloading">查询中...</span>';
		var failedload = '<span class="sloading">查询失败请重试</span>';
		
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
						var selected = "";
						if(key == 0 && level < townLevel) {
							selected = "selected = \"selected\"";
						}
						t.append('<option value="' + val.addrCodeId + '" ' + selected + '>' + val.addrCodeDes + '</option>');
					});
					
					$(".default-select2").select2();
					 
					t.prev(".sloading").fadeOut(function(){
						t.prev(".sloading").remove();
					});
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	            	t.prev(".sloading").remove();
	            	t.before(failedload);
	                console.warn("linkSubSelect() -> 初始化查询失败");
	            }
	        });
		}

		
		var v = t.val();
		var l = s.attr("data-level");
		var c = callback;
		var subv = s.find("option");
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
		ss.off("change").on("change", function(){
			var v = ss.val();
			var prev = ss.parents(".select-group").prevAll(".select-group");
			var dvalue = '';
			for(var i=prev.length-1; i>-1; i--){
				dvalue += prev.eq(i).find("select option:selected").text();
			}
			dvalue += ss.parents(".select-group").find("select option:selected").text();
			ss.attr("data-value", dvalue);
			ss.addClass("currentLevel").parents(".select-group").siblings().find(".currentLevel").removeClass("currentLevel");
			c && c(ss);
		});
		
		t.on("change", function(){
			t.prev(".sloading").remove();
			s.prev(".sloading").fadeOut(function(){
				s.prev(".sloading").remove();
			});
        	s.before(loading);
			var v = t.val();
			var prev = t.parents(".select-group").prevAll(".select-group");
			var dvalue = '';
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
						s.prev(".sloading").fadeOut(function(){
							s.prev(".sloading").remove();
						});
						c && c(t);
						//s.change();
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		            	s.prev(".sloading").remove();
		            	s.before(failedload);
		                console.warn("linkSubSelect() -> 查询失败");
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
				s.prev(".sloading").fadeOut(function(){
					s.prev(".sloading").remove();
				});
				c && c(t);
			}
		});
		
		return t;
	};
})(jQuery);